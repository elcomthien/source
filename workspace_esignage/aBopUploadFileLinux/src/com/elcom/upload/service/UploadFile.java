package com.elcom.upload.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;

public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String UPLOAD_DIRECTORY = "D:/data/";

	public UploadFile() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if ("save".equalsIgnoreCase(action)) {
			System.out.println("upload file media!");
			String info = "";
			try {
				String config = UploadService.admingetConfig();
				System.out.println("config: " + config);
				UPLOAD_DIRECTORY = config.substring(config.indexOf("<local_file>") + "<local_file>".length(),
						config.indexOf("</local_file>"));
				String uploadPath = UPLOAD_DIRECTORY;
				System.out.println("path save file upload: " + uploadPath);
				MultipartParams param = new MultipartParams(request);
				FileItem avatar = param.getFile("uploadfile");
				if (avatar != null) {
					String filename = new File(avatar.getName()).getName();
					filename = filename.replaceAll(" ", "_");
					String type = FileUtil.getType(filename).substring(1);
					String path = uploadPath + File.separator + filename;
					System.out.println("upload file media to: " + path);
					File storeFile = new File(path);
					avatar.write(storeFile);
					info = param.getParameter("info");
					String[] arr = info.split("#");
					String username = arr[0];
					System.out.println("username: " + username);
					String parent = arr[1];
					System.out.println("parent: " + parent);
					String idsubject = arr[2];
					System.out.println("idsubject: " + idsubject);
					int typecontent = 0;
					typecontent = checkFileTypeContent(type);
					String duration = getDurationMedia(filename);
					String xml = adminNewContent(filename, filename, typecontent + "", duration, idsubject, username, parent);
					System.out.println("xml add media content: " + xml);
					UploadService.adminAddNewContentMedia(xml);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}
	}

	public String getDurationMedia(String filename) throws Exception {
		System.out.println("Get duration file: " + filename);
		String xmlconfig = UploadService.admingetConfig();
		String localfile = xmlconfig.substring(xmlconfig.indexOf("<local_file>") + "<local_file>".length(),
				xmlconfig.indexOf("</local_file>"));
		System.out.println("localfile: " + localfile);
		String info = null;
		System.out.println("path file: " + localfile + filename);
		System.out.println("command: ffmpeg -i " + localfile + "/" + filename + " 2>&1 | grep 'Duration'");
		String[] listCmd = new String[] { "/bin/bash", "-c", "ffmpeg -i " + localfile + "/" + filename + " 2>&1 | grep 'Duration'" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String duration = "00:00:00";
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
			while ((info = bufferedReader.readLine()) != null) {
				String text = "Duration";
				duration = info.substring(info.indexOf(text) + text.length() + 2, info.indexOf("."));
				System.out.println("info" + info);
				System.out.println("duration: " + duration);
			}
			run.freeMemory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return duration;
	}

	// public String getLengthMedia(String filename) throws Exception {
	// String xmlconfig = UploadService.admingetConfig();
	// String localfile = xmlconfig.substring(xmlconfig.indexOf("<local_file>") + "<local_file>".length(),
	// xmlconfig.indexOf("</local_file>"));
	// String length = "00:00:00";
	// IContainer container = IContainer.make();
	// System.out.println("path name get duration: " + localfile + filename);
	// int result = container.open(localfile + filename, IContainer.Type.READ, null);
	// System.out.println("result" + result);
	// if (result < 0)
	// return "00:00:00";
	// long duration = container.getDuration();
	// System.out.println("duration number: " + duration);
	// duration = ((duration / 1000) / 1000);
	// long ss = duration % 60;
	// long mm = (duration / 60) % 60;
	// long hh = ((duration / 60) / 60) % 60;
	// length = checkSyntax(hh) + ":" + checkSyntax(mm) + ":" + checkSyntax(ss);
	// return length;
	// }

	public String checkSyntax(long input) {
		if (input >= 10)
			return input + "";
		else
			return "0" + input;
	}

	public int checkFileTypeContent(String filetype) {
		if (filetype.equalsIgnoreCase("mp4") || filetype.equalsIgnoreCase("mkv") || filetype.equalsIgnoreCase("3gp")) {
			return 1;
		}
		if (filetype.equalsIgnoreCase("mp3") || filetype.equalsIgnoreCase("m4a") || filetype.equalsIgnoreCase("wav")
				|| filetype.equalsIgnoreCase("aac") || filetype.equalsIgnoreCase("flac")) {
			return 4;
		}
		if (filetype.equalsIgnoreCase("png") || filetype.equalsIgnoreCase("jpg") || filetype.equalsIgnoreCase("gif")
				|| filetype.equalsIgnoreCase("bmp") || filetype.equalsIgnoreCase("webp")) {
			return 3;
		}
		return 0;
	}

	public static String adminNewContent(String name, String url, String type, String duration, String idsubject, String username,
			String parent) {
		name = encodeURIComponent(name);
		String xml = " <parameter><nameContent>" + name + "</nameContent><urlContent>" + url + "</urlContent><typeContent>" + type
				+ "</typeContent><durationContent>" + duration + "</durationContent><subjectcontent>" + idsubject
				+ "</subjectcontent><creator>" + username + "</creator><parentcreator>" + parent + "</parentcreator></parameter>";
		return xml;
	}

	public static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

	public static String encodeURIComponent(String input) {
		if (StringUtils.isEmpty(input)) {
			return input;
		}

		int l = input.length();
		StringBuilder o = new StringBuilder(l * 3);
		try {
			for (int i = 0; i < l; i++) {
				String e = input.substring(i, i + 1);
				if (ALLOWED_CHARS.indexOf(e) == -1) {
					byte[] b = e.getBytes("utf-8");
					o.append(getHex(b));
					continue;
				}
				o.append(e);
			}
			return o.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}

	private static String getHex(byte buf[]) {
		StringBuilder o = new StringBuilder(buf.length * 3);
		for (int i = 0; i < buf.length; i++) {
			int n = (int) buf[i] & 0xff;
			o.append("%");
			if (n < 0x10) {
				o.append("0");
			}
			o.append(Long.toString(n, 16).toUpperCase());
		}
		return o.toString();
	}

	public static void main(String[] args) throws Exception {
		String text = "Duration";
		String info = " Duration: 00:01:06.08, start: 0.000000, bitrate: 1763 kb/s";
		String duration = info.substring(info.indexOf(text) + text.length() + 2, info.indexOf("."));
		System.out.println(duration);
	}

}
