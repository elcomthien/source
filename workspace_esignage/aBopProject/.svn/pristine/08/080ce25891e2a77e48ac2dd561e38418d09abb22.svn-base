package elcom.abop.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import elcom.abop.util.ModelService;
import elcom.abop.util.ParseXmlService;
import elcom.abop.util.XmlService;

public class ConfigFtpServerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ModelService modelService = new ModelService();
	private XmlService xmlService = new XmlService();

	public ConfigFtpServerControl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("getconfig")) {
			String ftpconfig;
			ArrayList<HashMap<String, String>> listconfig = new ArrayList<HashMap<String, String>>();
			try {
				ftpconfig = ModelService.admingetConfig();
				listconfig = ParseXmlService.xmladmingetConfig(ftpconfig);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(new Gson().toJson(listconfig));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("update")) {
			String host = request.getParameter("host");
			String port = request.getParameter("port");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String ipserver = request.getParameter("ipserver");
			String srcfile = request.getParameter("srcfile");
			String srclocal = request.getParameter("srclocal");
			String xml = xmlService.xmladminUpdateConfig(host, port, srcfile, srclocal, ipserver, username, password);
			boolean flag = true;
			try {
				modelService.adminUpdateConfig(xml);
			} catch (Exception e) {
				e.printStackTrace();
				flag = false;
			}
			PrintWriter out = response.getWriter();
			out.print(flag);
		}
	}

}
