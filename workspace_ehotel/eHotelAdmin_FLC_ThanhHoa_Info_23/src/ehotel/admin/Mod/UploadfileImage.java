package ehotel.admin.Mod;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;

public class UploadfileImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadfileImage() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cmd = "";
		if (request.getParameter("cmd") != null) {
			cmd = request.getParameter("cmd").toString();
		}
		if (cmd.equals("")) {
			showJSPpage(request, response, "/modMgn/content/imageupload.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long start = System.currentTimeMillis();
		String contentType = request.getContentType();
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String ImageName = String.valueOf(start);
		// them vao thay doi duong dan luu tru image 16.1
		String path = config._temp;
		String saveFile = path + ImageName;
		System.out.println("path:" + path);
		System.out.println("saveFile:" + saveFile);
		String err = "";
		String lastFileName = "";
		String boundary = "";
		String pf = null;
		final int BOUNDARY_WORD_SIZE = "boundary=".length();
		if (contentType == null
				|| !contentType.startsWith("multipart/form-data")) {
			err = "Ilegal ENCTYPE : must be multipart/form-data\n";
			err += "ENCTYPE set = " + contentType;
		} else {
			boundary = contentType.substring(contentType.indexOf("boundary=")
					+ BOUNDARY_WORD_SIZE);
			boundary = "--" + boundary;
			try {
				javax.servlet.ServletInputStream sis = request.getInputStream();
				byte[] b = new byte[1024];
				int x = 0;
				int state = 0;
				String name = null, fileName = null, contentType2 = null;
				java.io.FileOutputStream buffer = null;
				while ((x = sis.readLine(b, 0, 1024)) > -1) {
					String s = new String(b, 0, x);
					if (s.startsWith(boundary)) {
						state = 0;
						name = null;
						contentType2 = null;
						fileName = null;
					} else if (s.startsWith("Content-Disposition")
							&& state == 0) {
						state = 1;
						if (s.indexOf("filename=") == -1)
							name = s.substring(
									s.indexOf("name=") + "name=".length(),
									s.length() - 2);
						else {
							name = s.substring(
									s.indexOf("name=") + "name=".length(),
									s.lastIndexOf(";"));
							fileName = s.substring(s.indexOf("filename=")
									+ "filename=".length(), s.length() - 2);
							if (fileName.equals("\"\"")) {
								fileName = null;
							} else {
								String userAgent = request
										.getHeader("User-Agent");
								String userSeparator = "/";
								if (userAgent.indexOf("Windows") != -1)
									userSeparator = "\\";
								if (userAgent.indexOf("Linux") != -1)
									userSeparator = "/";

								fileName = fileName
										.substring(
												fileName.lastIndexOf(userSeparator) + 1,
												fileName.length() - 1);
								if (fileName.startsWith("\""))
									fileName = fileName.substring(1);
							}
						}
						name = name.substring(1, name.length() - 1);
						if (name.equals("file")) {
							if (buffer != null)
								buffer.close();
							lastFileName = fileName;
							fileName = (fileName.substring(fileName
									.lastIndexOf(".")));
							request.setAttribute("fileUpload", "fileupload"
									+ String.valueOf(start).substring(6)
									+ "img" + fileName);
							pf = saveFile + fileName;
							System.out.println("path+fileName=" + pf);
							buffer = new java.io.FileOutputStream(pf);
						}
					} else if (s.startsWith("Content-Type") && state == 1) {
						state = 2;
						contentType2 = s.substring(s.indexOf(":") + 2,
								s.length() - 2);
					} else if (s.equals("\r\n") && state != 3) {
						state = 3;
					} else {
						if (name.equals("file"))
							buffer.write(b, 0, x);
						{
						}
					}
				}
				sis.close();
				buffer.close();
				// gan loai file vao image name
				ImageName += lastFileName.substring(lastFileName
						.lastIndexOf("."));
			} catch (java.io.IOException e) {
				e.printStackTrace();
				err = e.toString();
			}
		}
		// bo sung link cho url cua image 17.1
		request.setAttribute("linksaveimage", config._linksaveimage);
		request.setAttribute("image", ImageName);
		showJSPpage(request, response, "/modMgn/content/imageupload.jsp");
	}

	public void showJSPpage(HttpServletRequest req, HttpServletResponse res,
			String url) {
		try {
			ServletContext sc = getServletContext();

			String strURL = url;
			String realpath = req.getRealPath(strURL);
			RequestDispatcher rd = sc.getRequestDispatcher(strURL);

			rd.forward(req, res);
		} catch (Exception ex) {

		}
	}

	public void init() throws ServletException {
	}
}