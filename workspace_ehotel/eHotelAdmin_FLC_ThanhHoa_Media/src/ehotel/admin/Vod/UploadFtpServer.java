package ehotel.admin.Vod;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elcom.adcenter.upload.util.ContentCore;

//@WebServlet("/UploadFtpServer")
public class UploadFtpServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// static XmlService xmlService = new XmlService();
	// static ModalService modalService = new ModalService();

	public UploadFtpServer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("upload".equalsIgnoreCase(action)) {
			UUID result = null;
			try {
				// String ftpconfig = modalService.admingetConfig();
				// ArrayList<HashMap<String, String>> ftpxml = xmlService
				// .xmladmingetConfig(ftpconfig);

				String filename = "catsanddogsrevengeofkittygalore_trlr_01_1080p_dl.mov";
				String user = "vod";
				String pwd = "vod";
				String dest = "";
				String host = "10.10.16.80";
				String port = "21";
				String desc = "";
				String src = "Cats.and.Dogs.The.Revenge.of.Kitty.Galore.720p.BluRay.x264-CROSSBOW";
				String local = "/";
				if (checkFolder(local)) {
					result = uploadFTP(dest, filename, user, pwd, host, port, desc, src);
				} else
					result = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
			PrintWriter out = response.getWriter();
			out.print(result);
			out.flush();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public String readProperFtpServer() {
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/ftpconfig.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty("abop.ftp");
	}

	public UUID uploadFTP(String destination, String filename, String user, String pwd, String host, String port, String desc, String src) {
		UUID result = null;
		String xmlparamter = "<transfer>\r\n" + "<server_ftp>" + host + "</server_ftp>\r\n" + "<server_port>" + port + "</server_port>\r\n"
				+ "<serveruserftp>" + user + "</serveruserftp>\r\n" + "<serverpassftp>" + pwd + "</serverpassftp>\r\n" + "<src_file>" + src
				+ File.separator + filename + "</src_file>\r\n" + "<desc_host>" + desc + "</desc_host>\r\n" + "<local_file>" + destination
				+ "</local_file>\r\n" + "<timeout>10<timeout>\r\n" + "</transfer>";

		String hostname_rmi = readProperFtpServer();
		int port_rmi = 1622;
		String ServiceName = "elc_ftpgateway";
		ContentCore content = new ContentCore(hostname_rmi, port_rmi, ServiceName);
		try {
			result = content.download(xmlparamter);
			System.out.println("UUID=" + result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean checkFolder(String path) {
		boolean flag = false;
		File temp = new File(path);
		System.out.println(temp);
		System.out.println(temp.isDirectory());
		if (temp.isDirectory())
			flag = true;
		return flag;
	}

}
