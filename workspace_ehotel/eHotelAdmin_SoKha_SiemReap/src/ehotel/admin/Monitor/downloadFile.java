package ehotel.admin.Monitor;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.Service.ServiceParent;

public class downloadFile extends ServiceParent{
	 private String savefile ="video.txt";
	/**
	 * Constructor of the object.
	 */
	public downloadFile() {
		super();
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cmd=-1;
		if(request.getParameter("CMD")!=null)
		{
			try{
				cmd=Integer.parseInt(request.getParameter("CMD").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}
		switch (cmd) {
		case 1:			
			downloadFile(request, response);
			break;
	
		default:
			break;
		}
	}
	private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Properties useropt, sysProps;
		sysProps = System.getProperties();
		String  desktopPath = sysProps.getProperty("user.dir", null)+"/" + savefile;
		 String replacePath=desktopPath.replace("\\", "/");
		File  f = new File(replacePath);
		String filename =f.getName();
		int length = 0;
		response.setContentType("text/plain");
		response.setContentLength( (int)f.length() );
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + filename + "\"" );
		ServletOutputStream  out=response.getOutputStream();
		byte[] bbuf = new byte[1024];
		DataInputStream in = new DataInputStream(new FileInputStream(f));	        
       while ((in != null) && ((length = in.read(bbuf)) != -1))
       {
       	out.write(bbuf,0,length);
       }	     
       in.close();
       out.flush();
       out.close();
	}
	/**
	 * The doPost method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to post.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
	}
}