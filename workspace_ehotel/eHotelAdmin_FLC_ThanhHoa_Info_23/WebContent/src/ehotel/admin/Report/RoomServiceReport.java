package ehotel.admin.Report;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import ehotel.abs.report.OrderReport;
import ehotel.abs.report.VideoReport;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;
import ehotel.domain.report.DataReport;

public class RoomServiceReport extends ServiceParent {

	/**
	 * Constructor of the object.
	 */
	public RoomServiceReport() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
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
		case -1:
			int subId=-1;
			int menuid=-1;
			if(request.getParameter(Def.MenuId)!=null)
			{
				menuid=Integer.parseInt(request.getParameter(Def.MenuId).toString());
			}
			if(request.getParameter(Def.SubId)!=null)
			{
				subId=Integer.parseInt(request.getParameter(Def.SubId).toString());
			}			
			request.setAttribute(Def.MenuId, menuid);
			request.setAttribute(Def.SubId, subId);		
			request.setAttribute("fileJSP", "../report/roomserviceReport.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");			
			break;
		case 1:
		{
			PrintWriter out = response.getWriter();
			System.out.println("Get static roomservice");
			String frDate="";
			String toDate="";
			int index=0;
			int page=6;
			if(request.getParameter("from")!=null)
			{
				frDate=(request.getParameter("from").toString());
			}
			if(request.getParameter("to")!=null)
			{
				toDate=(request.getParameter("to").toString());
			}
			if(request.getParameter("pageIndex")!=null)
			{
				index=Integer.parseInt(request.getParameter("pageIndex").toString().trim());
			}
			if(request.getParameter("page")!=null)
			{
				page=Integer.parseInt(request.getParameter("page").toString().trim());
			}			
			response.setContentType("text/xml");
			String st=roomsrv(frDate, toDate);	
			out.print(st);
			break;		
		}
		case 2:
		{
			response.setContentType("image/jpeg");
			//String title="hello";
			//CategoryDataset dataset = createDataset();
			//JFreeChart chart = createChart(dataset, title);
			//BufferedImage image= chart.createBufferedImage(100, 100);
		    BufferedImage bufferedImage = new BufferedImage(200, 200,   
		    	    BufferedImage.TYPE_INT_RGB);  
		    Graphics g = bufferedImage.getGraphics();  
		    g.setColor(Color.blue);  
		    g.fillOval(0, 0, 199,199);  
		    g.dispose(); 
			ImageIO.write(bufferedImage, "jpg", response.getOutputStream());  
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
	
	private String roomsrv(String frDate,String toDate )
	{
		OrderReport rmi=new OrderReport();
		String mData="";	
		Vector<DataReport> v_rs= rmi.getRoomserviceRpt(frDate, toDate, LangID);
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		System.out.println("from:"+frDate);
		System.out.println("to:"+toDate);
		System.out.println("size:"+v_rs.size());
		for(int i=0;i<v_rs.size();i++)
		{
			DataReport item=v_rs.get(i);
			mData+="<Item>";
			mData+="<name>";
			mData+="<![CDATA["+item.getName()+"]]>";
			mData+="</name>";
			mData+="<id>\n";
			mData+=(i+1);
			mData+="</id>";
			mData+="<price>\n";
			mData+=item.getPrice();
			mData+="</price>";
			mData+="<quantity>\n";
			mData+=item.getQuanlity();
			mData+="</quantity>";
			mData+="<Amount>\n";
			mData+=item.getAmount();
			mData+="</Amount>";
			mData+="</Item>";
		}	
		mData+="</xml>";
		return mData;
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private static CategoryDataset createDataset() {

        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";

        // column keys...
        String category1 = "Category 1";
        String category2 = "Category 2";
        String category3 = "Category 3";
        String category4 = "Category 4";
        String category5 = "Category 5";

        // create the dataset...
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);

        dataset.addValue(5.0, series2, category1);
        dataset.addValue(7.0, series2, category2);
        dataset.addValue(6.0, series2, category3);
        dataset.addValue(8.0, series2, category4);
        dataset.addValue(4.0, series2, category5);

        dataset.addValue(4.0, series3, category1);
        dataset.addValue(3.0, series3, category2);
        dataset.addValue(2.0, series3, category3);
        dataset.addValue(3.0, series3, category4);
        dataset.addValue(6.0, series3, category5);

        return dataset;

    }
	 private JFreeChart createChart(CategoryDataset  dataset, String title) {
	        
	        JFreeChart chart = ChartFactory.createBarChart(
	        	title,
	            title,  				// chart title
	            title,
	            dataset,                // data
	            PlotOrientation.VERTICAL,
	            true,                   // include legend
	            true,
	            false
	        );
	        
	        return chart;
	        
	    }
}
