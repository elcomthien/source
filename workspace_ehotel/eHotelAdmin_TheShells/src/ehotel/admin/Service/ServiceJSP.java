package ehotel.admin.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.domain.vod.Subject;
import ehotel.domain.vod.Vod;
import ehotel.inter.AMDVod;
import ehotel.render.DBIGateway;
import ehotel.render.VOD;

public class ServiceJSP extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public ServiceJSP() {
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
		super.doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		int id=-1;
		if(request.getParameter("ID")!=null)
		{
			try{
				id=Integer.parseInt(request.getParameter("ID").toString());
			}catch (Exception e) {
				// TODO: handle exception
			}		
		}
		switch (id)
		{
		case 1://
			
			this.showJSPpage(request, response, Def._AddMoiveToService);
			break;
		case 2://
		{
			int VodId=-1;
			if(request.getParameter("VodId")!=null)
			{
				VodId=Integer.parseInt(request.getParameter("VodId").toString().trim());
			}
			VOD  VodDBI = DBIGateway.getAMDVod();
			AMDVod ctnMod = VodDBI.getAMDCntVod();
			Vod vod= ctnMod.getVodInfo(-1, VodId, LangID);
			request.setAttribute("Movie", vod);
			
			//bo sung link cho url cua image 17.1
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			request.setAttribute("linksaveimage", config._linksaveimage);
			
			System.out.println("show jsp:"+Def._CtnDetailMovie);
			this.showJSPpage(request, response, Def._CtnDetailMovie);
			break;
		}
		case 3://
		{
			int VodId=-1;
			int Subid=-1;
			if(request.getParameter("VodId")!=null)
			{
				VodId=Integer.parseInt(request.getParameter("VodId").toString().trim());
			}
			if(request.getParameter(Def.SubId)!=null)
			{
				Subid=Integer.parseInt(request.getParameter(Def.SubId).toString().trim());
			}
			VOD  VodDBI = DBIGateway.getAMDVod();
			AMDVod ctnMod = VodDBI.getAMDCntVod();
			Vod vod= ctnMod.getVodInfo(-1, VodId, LangID);
			Subject sub1=ctnMod.getSubjectInfo(Subid, LangID);
			Vector<Subject> list=ctnMod.getSubjects(LangID);
			request.setAttribute("VOD",vod);
			request.setAttribute("SUB", sub1);
			request.setAttribute("LIST", list);
			System.out.println("show jsp:"+Def._CtnSubjectMovie);
			this.showJSPpage(request, response, Def._CtnSubjectMovie);
			break;
			
		}
		case 4:
		{
			System.out.println("show jsp:"+Def._CtnTrailerMovie);
			int VodId=-1;
			if(request.getParameter("VodId")!=null)
			{
				VodId=Integer.parseInt(request.getParameter("VodId").toString().trim());
			}
			
			VOD  VodDBI = DBIGateway.getAMDVod();
			AMDVod ctnMod = VodDBI.getAMDCntVod();
			Vod vod= ctnMod.getVodInfo(-1, VodId, LangID);
			request.setAttribute("movie",vod.getTitle());
			request.setAttribute("trailer","trailer");
			this.showJSPpage(request, response, Def._CtnTrailerMovie);
			break;
		}
		case 5:
		{
			System.out.println("show jsp:"+Def._CtnSubTitleMovie);
			int VodId=-1;
			if(request.getParameter("VodId")!=null)
			{
				VodId=Integer.parseInt(request.getParameter("VodId").toString().trim());
			}
			VOD  VodDBI = DBIGateway.getAMDVod();
			AMDVod ctnMod = VodDBI.getAMDCntVod();
			Vod vod= ctnMod.getVodInfo(-1, VodId, LangID);	
			System.out.println("VodId:"+VodId);
			request.setAttribute("VOD", vod);			
			this.showJSPpage(request, response, Def._CtnSubTitleMovie);
			break;
		}
		case 6:
		{
			System.out.println("show jsp:"+Def._CtnAddMovie);
			this.showJSPpage(request, response, Def._CtnAddMovie);
			break;
		}
		case 7:
		{
			System.out.println("show jsp:"+Def._CtnAddMovie);
			this.showJSPpage(request, response, "/system/formAddBackgroundVideo.jsp");
			break;
		}
		default:
			break;
		}
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
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
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
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}