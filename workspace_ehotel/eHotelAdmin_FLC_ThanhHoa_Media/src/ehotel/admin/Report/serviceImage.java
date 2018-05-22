package ehotel.admin.Report;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import ehotel.abs.report.OrderReport;
import ehotel.abs.report.VideoReport;
import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Config;
import ehotel.admin.util.ConfigLoader;
import ehotel.admin.util.Def;
import ehotel.admin.util.ManagerFile;
import ehotel.admin.util.ReaderLang;
import ehotel.domain.report.DataReport;

public class serviceImage extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	public serviceImage() {
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
			} catch (Exception e) {
			}		
		}
		switch (cmd) {
		case 1:			
			exportMonth(request, response);
			break;
		case 2:
			exportGenres(request, response);
			break;
		case 3:
			exportStaticfilm(request, response);
			break;
		case 4:
			staticroomservice(request, response);
			break;
		case 5:
			exportTransport(request, response);
			break;
		default:
			break;
		}
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
	private void exportMonth(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session=request.getSession();
			ReaderLang readerLang=new ReaderLang();
			if(session.getAttribute(Def._lang)!=null)
			{	
				readerLang=(ReaderLang)session.getAttribute(Def._lang);
			}
			int year=2012;
			if(request.getParameter("year")!=null)
			{
				year=Integer.parseInt(request.getParameter("year").toString().trim());
			}			
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();
			String path1=config._temp;
			Date date=new Date();
			String filename="";
			filename=date.getMinutes()+date.getHours()+"-"+date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+".xls";
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path1+filename)); 		
			WritableSheet sheet = workbook.createSheet("First Sheet", 0); 

			sheet.setColumnView(0,40);
			sheet.setColumnView(1,40);

			sheet.mergeCells(0, 0, 1, 1);		
			WritableCell cell = sheet.getWritableCell(0, 0); 	
			
			WritableCellFormat cellFormat = new WritableCellFormat(); 

			cellFormat.setAlignment(Alignment.CENTRE);
			WritableFont font=new WritableFont(WritableFont.ARIAL);
			font.setPointSize(18);				
			cellFormat.setBackground(Colour.SEA_GREEN);
			cellFormat.setFont(font);
			cell.setCellFormat(cellFormat);			
			Label label = new Label(0, 0,readerLang.getContent("static_month"),cellFormat);		
			sheet.addCell(label);		

			WritableCellFormat cellFormatcol = new WritableCellFormat(); 
			cellFormatcol.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormatcol.setAlignment(Alignment.CENTRE);
			font=new WritableFont(WritableFont.ARIAL);
			font.setBoldStyle(WritableFont.BOLD);
			cellFormatcol.setFont(font);
			label = new Label(0, 4, readerLang.getContent("Monthly"),cellFormatcol);	
			sheet.addCell(label);		
			label = new Label(1, 4, readerLang.getContent("numberl_service"),cellFormatcol);
			sheet.addCell(label);		
			VideoReport rmi=new VideoReport();		
			Vector<DataReport> v_rs= rmi.getUsedFrequency_monthly(year);		
			for(int i=0;i<v_rs.size();i++)
			{
				WritableCellFormat cf1 = new WritableCellFormat();
				cf1.setBorder(Border.ALL, BorderLineStyle.THIN);
				cf1.setAlignment(Alignment.CENTRE);
				label = new Label(0, (i+5), v_rs.get(i).getName(),cf1);	
				sheet.addCell(label);
				WritableCellFormat cf2 = new WritableCellFormat();
				cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				cf2.setAlignment(Alignment.CENTRE);
				label = new Label(1, (i+5),String.valueOf(v_rs.get(i).getQuanlity()),cf2);
				sheet.addCell(label);
			}					
			String t=readerLang.getContent("note_report");
			label = new Label(0, v_rs.size()+13, t);
			sheet.addCell(label);		
			workbook.write(); 
			workbook.close(); 
			File  f = new File(path1+filename);
			int length = 0;
			response.setContentType( "application/octet-stream" );
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
	        file.clearAll(path1);
			} catch (Exception e) {
			}
	}
	private void exportGenres(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
		HttpSession session=request.getSession();
		ReaderLang readerLang=new ReaderLang();
		if(session.getAttribute(Def._lang)!=null)
		{	
				readerLang=(ReaderLang)session.getAttribute(Def._lang);
		}
		String from="";
		if(request.getParameter("from")!=null)
		{
			from=request.getParameter("from").toString();
		}	
		String to="";
		if(request.getParameter("to")!=null)
		{
			to=request.getParameter("to").toString();
		}
		ConfigLoader loader=new ConfigLoader();
		Config config=loader.getConfig();
		ManagerFile file=new ManagerFile();
		//sua lai duong dan cua viec luu file excel 25.1
		String path1=config._temp;
		Date date=new Date();
		String filename="";
		filename=date.getMinutes()+date.getHours()+"-"+date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+".xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(path1+filename)); 		
		WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
		
		sheet.setColumnView(0,40);
		sheet.setColumnView(1,40);
		
		sheet.mergeCells(0, 0, 1, 1);		
		WritableCell cell = sheet.getWritableCell(0, 0); 	
		WritableCellFormat cellFormat = new WritableCellFormat(); 
		cellFormat.setAlignment(Alignment.CENTRE);
		WritableFont font=new WritableFont(WritableFont.ARIAL);
		font.setPointSize(18);				
		cellFormat.setBackground(Colour.SEA_GREEN);
		cellFormat.setFont(font);
		cell.setCellFormat(cellFormat);			
		Label label = new Label(0, 0, readerLang.getContent("static_genres"),cellFormat);		
		sheet.addCell(label);
		
		WritableCellFormat cellFormatcol = new WritableCellFormat(); 
		cellFormatcol.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormatcol.setAlignment(Alignment.CENTRE);
		font=new WritableFont(WritableFont.ARIAL);
		font.setBoldStyle(WritableFont.BOLD);
		cellFormatcol.setFont(font);
		label = new Label(0, 4, "Gener",cellFormatcol);	
		sheet.addCell(label);		
		label = new Label(1, 4, readerLang.getContent("numberl_service"),cellFormatcol);
		sheet.addCell(label);
		VideoReport rmi=new VideoReport();
		Vector<DataReport> v_rs= rmi.getUsedFrequency_gener(from, to, LangID);
		
		for(int i=0;i<v_rs.size();i++)
		{
			WritableCellFormat cf1 = new WritableCellFormat();
			cf1.setBorder(Border.ALL, BorderLineStyle.THIN);
			cf1.setAlignment(Alignment.CENTRE);
			label = new Label(0, (i+5), v_rs.get(i).getName(),cf1);	
			sheet.addCell(label);
			WritableCellFormat cf2 = new WritableCellFormat();
			cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
			cf2.setAlignment(Alignment.CENTRE);
			label = new Label(1, (i+5),String.valueOf(v_rs.get(i).getQuanlity()),cf2);
			sheet.addCell(label);
		}
		String t=readerLang.getContent("note_report");
		label = new Label(0, v_rs.size()+13, t);
		sheet.addCell(label);		
		workbook.write(); 
		workbook.close(); 
			File                f        = new File(path1+filename);
			 int                 length   = 0;
			response.setContentType( "application/octet-stream" );
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
      file.clearAll(path1);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void exportStaticfilm(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		ReaderLang readerLang=new ReaderLang();
		if(session.getAttribute(Def._lang)!=null)
		{	
			readerLang=(ReaderLang)session.getAttribute(Def._lang);
		}
		try
		{
			String from="";
			if(request.getParameter("from")!=null)
			{
				from=request.getParameter("from").toString();
			}	
			String to="";
			if(request.getParameter("to")!=null)
			{
				to=request.getParameter("to").toString();
			}
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();
			//sua lai duong dan cua viec luu file excel 25.1
			String path1=config._temp;
			Date date=new Date();
			String filename="";		
			filename=date.getMinutes()+date.getHours()+"-"+date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+".xls";
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path1+filename)); 		
			WritableSheet sheet = workbook.createSheet("First Sheet", 0); 			
			
			sheet.setColumnView(0,30);
			sheet.setColumnView(1,10);
			sheet.setColumnView(2,40);
			sheet.setColumnView(3,20);
			sheet.mergeCells(0, 0, 3, 1);		
			sheet.mergeCells(0, 3, 3, 3);		
			
			WritableCell cell = sheet.getWritableCell(0, 0); 				
			WritableCellFormat cellFormat = new WritableCellFormat(); 
			cellFormat.setAlignment(Alignment.CENTRE);
			WritableFont font=new WritableFont(WritableFont.ARIAL);
			font.setPointSize(18);					
			cellFormat.setBackground(Colour.SEA_GREEN);
			cellFormat.setFont(font);
			cell.setCellFormat(cellFormat);			
			Label label = new Label(0, 0, readerLang.getContent("static_of_movies"),cellFormat);		
			sheet.addCell(label);			
			WritableCellFormat cellFormatcol = new WritableCellFormat(); 
			cellFormatcol.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormatcol.setAlignment(Alignment.CENTRE);
			font=new WritableFont(WritableFont.ARIAL);
			font.setBoldStyle(WritableFont.BOLD);
			cellFormatcol.setFont(font);
			
			label = new Label(0, 3, "From "+from+" - To "+to,cellFormatcol);
			sheet.addCell(label);
			label = new Label(0, 6, readerLang.getContent("movies_name"),cellFormatcol);	
			sheet.addCell(label);		
			label = new Label(1, 6,readerLang.getContent("Price"),cellFormatcol);
			sheet.addCell(label);
			label = new Label(2, 6, readerLang.getContent("numberl_service"),cellFormatcol);
			sheet.addCell(label);
			label = new Label(3, 6, readerLang.getContent("Amount"),cellFormatcol);
			sheet.addCell(label);
			
			VideoReport rmi=new VideoReport();
			Vector<DataReport> v_rs= rmi.getUsedFrequency_name(from, to, LangID,-1,-1);				
			for(int i=0;i<v_rs.size();i++)
			{
				WritableCellFormat cf1 = new WritableCellFormat();
				cf1.setBorder(Border.ALL, BorderLineStyle.THIN);
				cf1.setAlignment(Alignment.CENTRE);					
				label = new Label(0, (i+7), v_rs.get(i).getName(),cf1);	
				sheet.addCell(label);				
				label = new Label(1, (i+7), v_rs.get(i).getPrice(),cf1);	
				sheet.addCell(label);
				label = new Label(2, (i+7),String.valueOf(v_rs.get(i).getQuanlity()),cf1);	
				sheet.addCell(label);
				label = new Label(3, (i+7),String.valueOf(v_rs.get(i).getAmount()),cf1);	
				sheet.addCell(label);				
			}
			if(v_rs.size()>1)
			{				
				WritableCellFormat cf3 = new WritableCellFormat();
				cf3.setAlignment(Alignment.CENTRE);	
				font=new WritableFont(WritableFont.ARIAL);
				font.setBoldStyle(WritableFont.BOLD);
				cf3.setFont(font);				
				Formula formua = new Formula(3,v_rs.size()+7, "SUM(D8,D"+(v_rs.size()+7)+")",cf3);
				sheet.addCell(formua);	
				formua = new Formula(2,v_rs.size()+7, "SUM(C8,C"+(v_rs.size()+7)+")",cf3);
				sheet.addCell(formua);			
				sheet.mergeCells(0, v_rs.size()+7, 1, v_rs.size()+7);			
				label = new Label(0, v_rs.size()+7, "Total",cf3);
				sheet.addCell(label);				
			}				
			workbook.write(); 
			workbook.close(); 			
			File                f        = new File(path1+filename);
			int                 length   = 0;
			response.setContentType( "application/octet-stream" );
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
	      file.clearAll(path1);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private void staticroomservice(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session=request.getSession();
			ReaderLang readerLang=new ReaderLang();
			if(session.getAttribute(Def._lang)!=null)
			{	
				readerLang=(ReaderLang)session.getAttribute(Def._lang);
			}
		String from="";
		if(request.getParameter("from")!=null)
		{
			from=request.getParameter("from").toString();
		}	
		String to="";
		if(request.getParameter("to")!=null)
		{
			to=request.getParameter("to").toString();
		}
		ConfigLoader loader=new ConfigLoader();
		Config config=loader.getConfig();
		ManagerFile file=new ManagerFile();
		//sua lai duong dan cua viec luu file excel 25.1
		String path1=config._temp;
		Date date=new Date();
		String filename="";		
		filename=date.getMinutes()+date.getHours()+"-"+date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+".xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(path1+filename)); 		
		WritableSheet sheet = workbook.createSheet("First Sheet", 0); 		
		
		sheet.setColumnView(0,40);
		sheet.setColumnView(1,40);		
		sheet.mergeCells(0, 0, 1, 1);
		sheet.mergeCells(0, 3, 1, 3);
		
		WritableCell cell = sheet.getWritableCell(0, 0); 	
		WritableCellFormat cellFormat = new WritableCellFormat(); 
		cellFormat.setAlignment(Alignment.CENTRE);
		WritableFont font=new WritableFont(WritableFont.ARIAL);
		font.setPointSize(18);				
		cellFormat.setBackground(Colour.SEA_GREEN);
		cellFormat.setFont(font);
		cell.setCellFormat(cellFormat);			
		Label label = new Label(0, 0, readerLang.getContent("static_roomservice"),cellFormat);		
		sheet.addCell(label);
		
		WritableCellFormat cellFormatcol = new WritableCellFormat(); 
		cellFormatcol.setBorder(Border.ALL, BorderLineStyle.THIN);
		cellFormatcol.setAlignment(Alignment.CENTRE);
		font=new WritableFont(WritableFont.ARIAL);
		font.setBoldStyle(WritableFont.BOLD);
		cellFormatcol.setFont(font);
		label = new Label(0, 3, "From "+from+" - To "+to,cellFormatcol);
		sheet.addCell(label);
		label = new Label(0, 5,readerLang.getContent("roomcode"),cellFormatcol);	
		sheet.addCell(label);		
		label = new Label(1, 5, readerLang.getContent("numberl_service"),cellFormatcol);
		sheet.addCell(label);
		OrderReport rmi=new OrderReport();
		Vector<DataReport> v_rs= rmi.getRoomserviceRpt(from, to, LangID);
		for(int i=0;i<v_rs.size();i++)
		{
			WritableCellFormat cf1 = new WritableCellFormat();
			cf1.setBorder(Border.ALL, BorderLineStyle.THIN);
			cf1.setAlignment(Alignment.CENTRE);
			label = new Label(0, (i+6), v_rs.get(i).getName(),cf1);	
			sheet.addCell(label);
			WritableCellFormat cf2 = new WritableCellFormat();
			cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
			cf2.setAlignment(Alignment.CENTRE);
			label = new Label(1, (i+6),String.valueOf(v_rs.get(i).getQuanlity()),cf2);
			sheet.addCell(label);			
		}
		String t=readerLang.getContent("note_report");
		label = new Label(0, v_rs.size()+13, t);
		sheet.addCell(label);	
		workbook.write(); 
		workbook.close(); 
		File  f = new File(path1+filename);
		int                 length   = 0;
		response.setContentType( "application/octet-stream" );
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
        file.clearAll(path1);
	    } catch (Exception e) {
			e.printStackTrace();
	    }
	}
	private void exportTransport(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session=request.getSession();
			ReaderLang readerLang=new ReaderLang();
			if(session.getAttribute(Def._lang)!=null)
			{	
				readerLang=(ReaderLang)session.getAttribute(Def._lang);
			}
			int year=2012;
			if(request.getParameter("year")!=null)
			{
				year=Integer.parseInt(request.getParameter("year").toString().trim());
			}	
			String from="";
			if(request.getParameter("from")!=null)
			{
				from=request.getParameter("from").toString();
			}	
			String to="";
			if(request.getParameter("to")!=null)
			{
				to=request.getParameter("to").toString();
			}
			ConfigLoader loader=new ConfigLoader();
			Config config=loader.getConfig();
			ManagerFile file=new ManagerFile();
			//sua lai duong dan cua viec luu file excel 25.1
			String path1=config._temp;
			Date date=new Date();
			String filename="";
			filename=date.getMinutes()+date.getHours()+"-"+date.getDate()+"-"+date.getMonth()+"-"+date.getYear()+".xls";
			WritableWorkbook workbook = Workbook.createWorkbook(new File(path1+filename));
			WritableSheet sheet = workbook.createSheet("First Sheet", 0);
			sheet.setColumnView(0,40);
			sheet.setColumnView(1,40);
			sheet.mergeCells(0, 0, 1, 1);
			WritableCell cell = sheet.getWritableCell(0, 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setAlignment(Alignment.CENTRE);
			WritableFont font=new WritableFont(WritableFont.ARIAL);
			font.setPointSize(18);
			cellFormat.setBackground(Colour.SEA_GREEN);
			cellFormat.setFont(font);
			cell.setCellFormat(cellFormat);
			Label label = new Label(0, 0,readerLang.getContent("transportstatic"),cellFormat);
			sheet.addCell(label);
			WritableCellFormat cellFormatcol = new WritableCellFormat();
			cellFormatcol.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormatcol.setAlignment(Alignment.CENTRE);
			font=new WritableFont(WritableFont.ARIAL);
			font.setBoldStyle(WritableFont.BOLD);
			cellFormatcol.setFont(font);
			label = new Label(0, 4, readerLang.getContent("Name"),cellFormatcol);
			sheet.addCell(label);
			label = new Label(1, 4, readerLang.getContent("numberl_service"),cellFormatcol);
			sheet.addCell(label);
			OrderReport rmi=new OrderReport();
			Vector<DataReport> v_rs= rmi.getTransportationRpt(from, to, LangID);
			for(int i=0;i<v_rs.size();i++)
			{
				WritableCellFormat cf1 = new WritableCellFormat();
				cf1.setBorder(Border.ALL, BorderLineStyle.THIN);
				cf1.setAlignment(Alignment.CENTRE);
				label = new Label(0, (i+5), v_rs.get(i).getName(),cf1);
				sheet.addCell(label);
				WritableCellFormat cf2 = new WritableCellFormat();
				cf2.setBorder(Border.ALL, BorderLineStyle.THIN);
				cf2.setAlignment(Alignment.CENTRE);
				label = new Label(1, (i+5),String.valueOf(v_rs.get(i).getQuanlity()),cf2);
				sheet.addCell(label);
			}
			String t=readerLang.getContent("note_report");
			label = new Label(0, v_rs.size()+13, t);
			sheet.addCell(label);
			workbook.write();
			workbook.close();
			File                f        = new File(path1+filename);
			int                 length   = 0;
			response.setContentType( "application/octet-stream" );
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
	        file.clearAll(path1);
			} catch (Exception e) {
			}
	}
}