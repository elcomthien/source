package elcom.abop.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xuggle.xuggler.IContainer;

import ehotel.core.FTPGatewayInterface;
import ehotel.core.FTPServerStruct;
import elcom.abop.bean.ObjectBean;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.BasicAction;
import elcom.abop.common.Constant;
import elcom.abop.util.Ftp4jClientUitil;
import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.XmlService;

public class MediaAction implements BasicAction, ModelDriven<ObjectBean> {
	private static final Logger logger = Logger.getLogger(MediaAction.class);

	private ObjectBean object = new ObjectBean();
	private HttpServletRequest request;

	@Override
	public ObjectBean getModel() {
		Constant.setObjectBean(new ObjectBean());
		return Constant.getObjectBean();
	}

	public String getAllMedia() throws Exception {
		logger.info("get all content media");
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get(ApplyItemConstant.KEY_USER);
		String object = Constant.getObjectBean().getData();
		String idsubject = "";
		try {
			JSONObject jsonObj = new JSONObject(object);
			idsubject = jsonObj.getString("idsubject");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String xml = XmlService.getAllContent("", idsubject, user.getCreator(),
				user.getParentCreator());
		String xmlResult = ModelService.adminGetAllContentMedia(xml);
		Constant.getObjectBean().setContent(
				ParseXmlService.getAllContentMedia(xmlResult));
		return SUCCESS;
	}

	public String deleteMedia() {
		logger.info("delete content media");
		System.out.println("delete content media");
		String id = "";
		String name = "";
		String object = Constant.getObjectBean().getData();
		try {
			JSONObject jsonObj = new JSONObject(object);
			id = jsonObj.getString("id");
			name = jsonObj.getString("name");
			String xml = XmlService.xmladminDelContentText(id);
			ModelService.adminDelContentText(xml);
			// get path save file in server local
			String pathluufile = "";
			String config = ModelService.admingetConfig();
			String valuestart = "<local_file>";
			String valueend = "</local_file>";
			int start = config.indexOf(valuestart) + valuestart.length();
			int end = config.indexOf(valueend);
			pathluufile = config.substring(start, end);

			String filepath = pathluufile + File.separator + name;
			File temp = new File(filepath);
			if (temp.exists()) { // check file exists
				logger.info("Delete file " + filepath);
				temp.delete();

			} else {
				logger.info("File not exists");
			}
			Constant.getObjectBean().setData("true");
		} catch (Exception e) {
			Constant.getObjectBean().setData("false");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String getFileFtpServer() throws Exception {
		logger.info("Get list file from FTP server!");
		String path = "";
		String object = Constant.getObjectBean().getData();
		JSONObject jsonObj = new JSONObject(object);
		path = jsonObj.getString("path");
		String xmlconfig = ModelService.admingetConfig();
		String host = xmlconfig.substring(xmlconfig.indexOf("<server_ftp>")
				+ "<server_ftp>".length(), xmlconfig.indexOf("</server_ftp>"));
		int port = Integer.parseInt(xmlconfig.substring(
				xmlconfig.indexOf("<server_port>") + "<server_port>".length(),
				xmlconfig.indexOf("</server_port>")));
		String user = xmlconfig.substring(xmlconfig.indexOf("<serveruserftp>")
				+ "<serveruserftp>".length(),
				xmlconfig.indexOf("</serveruserftp>"));
		String pass = xmlconfig.substring(xmlconfig.indexOf("<serverpassftp>")
				+ "<serverpassftp>".length(),
				xmlconfig.indexOf("</serverpassftp>"));
		Constant.getObjectBean().setContent(
				Ftp4jClientUitil.getListFile(host, port, user, pass, path));
		return SUCCESS;
	}

	public String updateMedia() throws Exception {
		logger.info("update content media");
		return SUCCESS;
	}

	public String newMediaContent() throws Exception {
		String object = Constant.getObjectBean().getData();
		String filename = "";
		String idsubject = "";
		JSONObject jsonObj = new JSONObject(object);
		filename = jsonObj.getString("name");
		idsubject = jsonObj.getString("idsubject");
		String filetype = filename.substring(filename.lastIndexOf(".") + 1,
				filename.length());
		int typecontent = 0;
		typecontent = checkFileTypeContent(filetype);
		if (typecontent != 0) {
			UUID id = transferFile(filename);
			if (id != null) {
				Map<String, Object> session = ActionContext.getContext()
						.getSession();
				User user = (User) session.get(ApplyItemConstant.KEY_USER);
				String savename = filename.substring(
						filename.lastIndexOf("/") + 1, filename.length());
				String duration = getLengthMediaFile(savename);
				String xml = XmlService.adminNewContent(savename, savename,
						typecontent + "", duration, idsubject,
						user.getCreator(), user.getParentCreator());
				ModelService.adminAddNewContentMedia(xml);
				Constant.getObjectBean().setData("true");
			} else {
				Constant.getObjectBean().setData("false");
			}
		}
		return SUCCESS;
	}

	public int checkFileTypeContent(String filetype) {
		if (filetype.equalsIgnoreCase("mp4")
				|| filetype.equalsIgnoreCase("mkv")
				|| filetype.equalsIgnoreCase("3gp")) {
			return 1;
		}
		if (filetype.equalsIgnoreCase("mp3")
				|| filetype.equalsIgnoreCase("m4a")
				|| filetype.equalsIgnoreCase("wav")
				|| filetype.equalsIgnoreCase("aac")
				|| filetype.equalsIgnoreCase("flac")) {
			return 4;
		}
		if (filetype.equalsIgnoreCase("png")
				|| filetype.equalsIgnoreCase("jpg")
				|| filetype.equalsIgnoreCase("gif")
				|| filetype.equalsIgnoreCase("bmp")
				|| filetype.equalsIgnoreCase("webp")) {
			return 3;
		}
		return 0;
	}

	public UUID transferFile(String filename) throws Exception {
		String xmlconfig = ModelService.admingetConfig();
		String hostAc = xmlconfig.substring(xmlconfig.indexOf("<server_ftp>")
				+ "<server_ftp>".length(), xmlconfig.indexOf("</server_ftp>"));
		int portAc = Integer.parseInt(xmlconfig.substring(
				xmlconfig.indexOf("<server_port>") + "<server_port>".length(),
				xmlconfig.indexOf("</server_port>")));
		String userAc = xmlconfig.substring(
				xmlconfig.indexOf("<serveruserftp>")
						+ "<serveruserftp>".length(),
				xmlconfig.indexOf("</serveruserftp>"));
		String passAc = xmlconfig.substring(
				xmlconfig.indexOf("<serverpassftp>")
						+ "<serverpassftp>".length(),
				xmlconfig.indexOf("</serverpassftp>"));
		String hostSt = xmlconfig.substring(xmlconfig.indexOf("<desc_host>")
				+ "<desc_host>".length(), xmlconfig.indexOf("</desc_host>"));
		String srcfile = xmlconfig.substring(xmlconfig.indexOf("<src_file>")
				+ "<src_file>".length(), xmlconfig.indexOf("</src_file>"));
		String localfile = xmlconfig.substring(
				xmlconfig.indexOf("<local_file>") + "<local_file>".length(),
				xmlconfig.indexOf("</local_file>"));

		logger.info("Transfer file to " + hostSt);
		// filename = filename.substring(1, filename.length());
		UUID id = null;
		try {
			FTPGatewayInterface ftpgateway;
			ftpgateway = (FTPGatewayInterface) Naming.lookup("rmi://" + hostSt
					+ ":2099/elc_ftpgateway");
			logger.info("rmi://" + hostSt + ":2099/elc_ftpgateway");
			FTPServerStruct server = new FTPServerStruct(hostAc, portAc,
					userAc, passAc, filename);
			logger.info("file server: " + filename);
			logger.info("file path download: " + localfile + filename);
			String savename = filename.substring(filename.lastIndexOf("/") + 1,
					filename.length());
			id = ftpgateway
					.download(server, hostSt, localfile + savename, 5000);
			while (ftpgateway.transfered(id) < ftpgateway.fileSize(id)) {
				logger.info("transfering " + srcfile + filename + " : "
						+ ftpgateway.transfered(id) * 100
						/ ftpgateway.fileSize(id) + "%");
			}
			logger.info("after download");

			if (ftpgateway.getStatus(id) == FTPServerStruct.STATUS_COMPLETED) {
				logger.info("download completed");
			} else if (ftpgateway.getStatus(id) == FTPServerStruct.STATUS_NOT_COMPLETED) {
				logger.info("download didn't complete");
			}
		} catch (Exception e) {
			logger.error("Transfer file error", e);
		}
		return id;
	}

	public String getDurationMedia(String filename) throws Exception {
		String xmlconfig = ModelService.admingetConfig();
		String localfile = xmlconfig.substring(
				xmlconfig.indexOf("<local_file>") + "<local_file>".length(),
				xmlconfig.indexOf("</local_file>"));
		String info = null;
		String[] listCmd = new String[] { "/bin/bash", "-c",
				"ffmpeg -i " + localfile + filename + " 2>&1 | grep 'Duration'" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String duration = "00:00:00";
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(runtimeProcess.getInputStream()));
			while ((info = bufferedReader.readLine()) != null) {
				String text = "Duration";
				duration = info.substring(info.indexOf(text) + text.length()
						+ 2, info.indexOf("."));
			}
			run.freeMemory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return duration;
	}

	public String getLengthMediaFile(String filename) throws Exception {
		String xmlconfig = ModelService.admingetConfig();
		String localfile = xmlconfig.substring(
				xmlconfig.indexOf("<local_file>") + "<local_file>".length(),
				xmlconfig.indexOf("</local_file>"));
		IContainer container = IContainer.make();
		int result = container.open(localfile + filename, IContainer.Type.READ,
				null);
		if (result < 0)
			throw new RuntimeException("Failed to open media file");

		long duration = container.getDuration();
		System.out.println(duration);
		duration = (duration / 1000) / 1000;
		System.out.println(duration);
		long ss = duration % 60;
		long mm = (duration / 60) % 60;
		long hh = ((duration / 60) / 60) % 60;
		return checkNumber(hh) + ":" + checkNumber(mm) + ":" + checkNumber(ss);
	}

	public String checkNumber(long number) {
		if (number < 10)
			return "0" + number;
		else
			return number + "";
	}

	public static void main(String[] args) throws Exception {
		MediaAction m = new MediaAction();
		m.getDurationMedia("AgribankTheVisa.mp4");

	}

}
