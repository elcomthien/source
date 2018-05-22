package com.elcom.adcenter.serverlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.adcenter.common.Method;
import com.elcom.adcenter.common.Param;
import com.elcom.adcenter.store.ProgDao;
import com.elcom.adcenter.util.BoxUtil;
import com.elcom.adcenter.util.CreateFile;
import com.elcom.adcenter.util.DAOFactory;
import com.elcom.adcenter.util.DateHelper;

public class CoreProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ProgDao progdao = DAOFactory.getInstance().getProgDao();
	private BoxUtil boxUtil = new BoxUtil();
	private static final Logger logger = LogManager.getLogger(CoreProcess.class);	
       
    public CoreProcess() {
        super();
    }

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sernumber,mac;
		String ipClient = request.getRemoteAddr();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		String lenh = request.getParameter(Method.command);
		sernumber = request.getParameter(Param.sernumber);
		mac = request.getParameter(Param.mac);
		String current = request.getParameter(Param.current);
		
		logger.info("Ip: " + ipClient + " - Method: " + lenh + " - curent time stb: " + current + " - VER: " + request.getParameter("ver"));
		//mac = mac.replaceAll("@", "+");
		//mac =  AesCtr.decrypt(mac, Param.key, 256);
		sernumber = mac;
		
		
		
		if (lenh.equals(Method.getChanngeTemplate)){			
			  logger.info("Ip: " + ipClient + " - In getChanngeTemplate(" + sernumber + "," + mac + ")");
			  int xml = progdao.spgetChanngeTemplate(sernumber, mac);
			  logger.info(xml);			  
			  out.println(xml + "");
		}
		
		if (lenh.equals(Method.getChanngeTemplates)){
			String temp = progdao.updateIpAdressBox(ipClient, mac);
			System.out.println(temp);
			boxUtil.rebootBox(temp);
			String ramtotal = request.getParameter(Param.ramtotal);			
			String hddtotal = request.getParameter(Param.hddtotal);
			String sdcardtotal = request.getParameter(Param.sdcardtotal);
			String cputotal = request.getParameter(Param.cputotal);
			String ram = request.getParameter(Param.ram);
			String cpu = request.getParameter(Param.cpu);
			String hdd = request.getParameter(Param.hdd);
			String sdcard = request.getParameter(Param.sdcard);
			String cursizefile = request.getParameter(Param.cursizefile);
			String totalsizefile = request.getParameter(Param.totalsizefile);
			//logger.info("Ip: " + ipClient + " - In getChanngeTemplates(sernumber:" + sernumber + ",mac:" + mac + ",ramtotal:" + ramtotal + ",ram free: " + ram + ",cup total: " + cputotal + ",cpu free:" + cpu + ",hddtotal: " + hddtotal + ",hdd free:" + hdd + ")");
			
			if (cursizefile == null) cursizefile = "0";
			if (totalsizefile == null) totalsizefile = "0";
			String xml = progdao.spgetChanngeTemplates(ipClient,sernumber, mac,current,ramtotal,ram,cputotal,cpu,hddtotal,hdd,sdcardtotal,sdcard,cursizefile,totalsizefile);	
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.getDownloadFileStb)){
			logger.info("Ip: " + ipClient + " - In getDownloadFileStb(" + sernumber + "," + mac + ")");
			String xml = progdao.getDownloadFileStb(sernumber, mac);
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		//------------------------------------------------------------------------------
		if (lenh.equals(Method.downloadComplate)){
			String contentid = request.getParameter(Param.contentid);
			String process = request.getParameter(Param.process);
			logger.info("Ip: " + ipClient + " - In downloadComplate(" + sernumber + "," + mac + "," + contentid + "," + process + ")");
			String xml = progdao.downloadComplate(sernumber, mac,contentid,process);
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		//----------------------------------------------------------------------
		if (lenh.equals(Method.capturestb)){
			logger.info("Ip: " + ipClient + " - In capturestb(" + sernumber + "," + mac + ")");
			String xml = progdao.getCaptureCounterStb(sernumber, mac);	
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		//------------------------------------------------------------------------------
		if (lenh.equals(Method.delcontentstb)){			
			logger.info("Ip: " + ipClient + " - In deleteContentstb(" + sernumber + "," + mac + ")");
			String xml = progdao.deleteContentstb(sernumber, mac);	
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		//------------------------------------------------------------------------------
		if (lenh.equals(Method.getContentLayout)){
			int idplaylist = new Integer(request.getParameter(Param.idplaylist)).intValue();
			int changecontent = new Integer(request.getParameter(Param.changecontent)).intValue();
			logger.info("Ip: " + ipClient + " - In getContentLayout(" + sernumber + "," + mac + "," + idplaylist + ")");
			String xml = progdao.spgetContentLayout(sernumber, mac,idplaylist,changecontent);
			String xml1 = DateHelper.stringToHTMLString(xml); 
			
			/*CreateFile file = new CreateFile("c:\\Java\\apache-tomcat-6.0.35\\bin\\Config\\file\\",ipClient);
			file.createFile(xml1);*/
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml1);
			
		}
		
		if (lenh.equals(Method.getContentLayouts)){
			String xml = "<Playlists>\r\n";
			String idplaylists = request.getParameter(Param.idplaylist);
			int changecontent = new Integer(request.getParameter(Param.changecontent)).intValue();
			logger.info("Ip: " + ipClient + " - In getContentLayout(" + sernumber + "," + mac + "," + idplaylists + ")");
			Vector<Integer> keys = DateHelper.getPlaylistid(idplaylists);
			for (int i = 0 ; i < keys.size() ; i++)
			{
				Integer playlistid = keys.get(i);
				xml = xml + progdao.spgetContentLayout(sernumber, mac,playlistid,changecontent);
			}
			
			//String xml1 = DateHelper.stringToHTMLString(xml); 
			String xml1 = DateHelper.stringToHTMLString((DateHelper.decodeURIComponent(xml)));
			
			xml1 = xml1 + "\r\n</Playlists>";
			
			logger.info(xml1);
			logger.info("-----------------------------------------------");
			String save_xml = request.getParameter("save_xml");
			/*if (save_xml.equals("save_xml")){
				CreateFile file = new CreateFile("/Users/macbookair/Downloads/tmp","playlist");
				file.createFile(xml1);
			}*/
			out.println(xml1);
			
		}
		if (lenh.equals(Method.getInfoStb)){
			logger.info("Ip: " + ipClient + " - In getInfoStb(" + sernumber + "," + mac + ")");			  
			String xml = progdao.spgetInfoStb(sernumber, mac);
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.getSchedule)){
			String schduelid = request.getParameter(Param.scheduleid);
			logger.info("Ip: " + ipClient + " - In getSchedule(" + schduelid +  "," + sernumber + "," + mac + ")");			  
			String xml = progdao.spgetSchedule(schduelid,sernumber, mac);
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.loginstb)){
			logger.info("Ip: " + ipClient + " - In loginstb(" + sernumber + "," + mac + ")");			  
			String xml = progdao.sploginstb(sernumber, mac);
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.getSchedulePeri)){
			logger.info("Ip: " + ipClient + " - In getSchedulePeri(" + sernumber + "," + mac + ")");			  
			String xml = progdao.getSchedulePeri(sernumber, mac);
			String save_xml = request.getParameter("save_xml");
			//if (save_xml.equals("save_xml")){
				CreateFile file = new CreateFile("/Users/macbookair/Downloads/tmp","periodic");
				file.createFile(xml);
			//}
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.regstb)){
			String savelocalmedia = request.getParameter(Param.savelocalmedia);
			String savelocalfile = request.getParameter(Param.savelocalfile);
			logger.info("Ip: " + ipClient + " - In sp_regstb(" + sernumber + "," + mac + "," + savelocalmedia + "," + savelocalfile +  ")");
			if (savelocalmedia != null) savelocalmedia = savelocalmedia.replace("@", "/");
			if (savelocalfile != null) savelocalfile = savelocalfile.replace("@", "/");
			//logger.info("Ip: " + ipClient + " - In sp_regstb(" + sernumber + "," + mac + ")");			  
			String xml = progdao.sp_regstb(ipClient,sernumber, mac,savelocalmedia,savelocalfile);
			xml = "1";
			
			logger.info(xml);
			logger.info("-----------------------------------------------");
			out.println(xml);
		}
		
		if (lenh.equals(Method.getHomeDefault)){			
			  logger.info("Ip: " + ipClient + " - In spgetDefauleHome(" + sernumber + "," + mac + ")");
			  String xml = progdao.spgetDefauleHome(sernumber, mac);
			  
			  logger.info(xml);			  
			  out.println(xml + "");
		}
		
		if (lenh.equals(Method.iptvSubject)){			
			  logger.info("Ip: " + ipClient + " - In spiptvSubject()");
			  String xml = progdao.spiptvSubject();
			 
			  logger.info(xml);			  
			  out.println(xml + "");
		}
		
		if (lenh.equals(Method.iptvContentSubject)){
			  String idsubject = request.getParameter(Param.idsubject); 
			  logger.info("Ip: " + ipClient + " - In spiptvSubject("+idsubject+")");
			  String xml = progdao.spiptvContentSubject(idsubject);
			  
			  logger.info(xml);			  
			  out.println(xml + "");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
