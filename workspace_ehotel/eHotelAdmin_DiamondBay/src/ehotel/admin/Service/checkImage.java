package ehotel.admin.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.util.ManagerFile;

public class checkImage extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public checkImage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
	
		String path=request.getRealPath("resource/images/");
		String filename=request.getPathInfo();
		filename=path+filename;
		ManagerFile manager=new ManagerFile();
		if(!manager.checkImage(filename))
		{
			filename=path+"/noimage.gif";
		}			
		File file = new File(filename);			
		response.setContentType("image/png");
		FileInputStream in = new FileInputStream(file);			
		OutputStream out = response.getOutputStream();
		byte[] buf = new byte[1024*3];			
		int count = -1;			
		while ((count = in.read(buf)) >= 0)
		{
		        out.write(buf, 0, count);
		}				
		in.close();
		out.close();
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
