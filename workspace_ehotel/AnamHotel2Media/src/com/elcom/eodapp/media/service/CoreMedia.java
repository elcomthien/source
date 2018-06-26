package com.elcom.eodapp.media.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.Log.*;
import com.elcom.RemoteMonitor.RemoteViewServer;
import com.elcom.eodapp.media.cas.CasContent;
import com.elcom.eodapp.media.cfg.Configuration;
import com.elcom.eodapp.media.cfg.ConfigurationLoader;
import com.elcom.eodapp.media.common.Helper;
import com.elcom.eodapp.media.livetv.BrowserProgDao;
import com.elcom.eodapp.media.mod.ModContent;
import com.elcom.eodapp.media.pms.PmsContent;
import com.elcom.eodapp.media.record.RecordContent;
import com.elcom.eodapp.media.util.DAOFactory;
import com.elcom.eodapp.media.util.DateHelper;
import com.elcom.eodapp.media.vod.VodContentDAO2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class CoreMedia
 */
public class CoreMedia extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final FileLog  logger = new FileLog();
	private static VodContentDAO2 voddao = DAOFactory.getInstance().getModContentDAO2();
	private static CasContent casdao = DAOFactory.getInstance().getCasContent();
	private static ModContent moddao = DAOFactory.getInstance().getModService();
	private static BrowserProgDao livedao = DAOFactory.getInstance().getBrowserProgDao();
	private static RecordContent recorddao = DAOFactory.getInstance().getRecordContent();
	private static PmsContent pmsdao = DAOFactory.getInstance().getPmsContent();
	private int vodsubjectid;
	private static RemoteViewServer remoteViewServer = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CoreMedia() {
        super();
        ConfigurationLoader loader = ConfigurationLoader.getInstance();
        Configuration config = loader.getConfiguration();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>" + config.remoteviewport + "<<<<<<<<<<<<<<<<<<<<<<<");
        // TODO Auto-generated constructor stub
        if(config.remoteviewport > 0)
        {   
            System.out.println("Start RemoteViewServer with port " + config.remoteviewport);
            remoteViewServer = new RemoteViewServer(config.remoteviewport, "SERVER APP MODULE");
            remoteViewServer.start();
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//FileLog  log;
		String keystb,subid,current,langid,room,ip,channelid;
		String ipClient = request.getRemoteAddr();
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=60000");
		PrintWriter out = response.getWriter();
		int lenh = new Integer(request.getParameter(Command.command)).intValue();
		keystb = request.getParameter(Param.keystb);
		subid = request.getParameter(Param.subid);
		langid = request.getParameter(Param.langid);
		room = request.getParameter(Param.room);
		ip = request.getParameter(Param.ip);
		channelid = request.getParameter(Param.channelid);
		current = request.getParameter(Param.current);
		
		//logger.write("Ip: " + ipClient + " - keystb: " + keystb + " - curent time stb: ");
		System.out.println("Ip: " + ipClient + " - keystb: " + keystb + " - curent time stb: " + current + " - Command: " + lenh);
		//log = new FileLog(ipClient + ".log");
		//log.write("=> Yeu cau lenh: " + DateHelper.convertLenh(lenh) + " - " + lenh);
		//log.flush();
		//Cac chuc nang danh cho Record
		if (lenh == Command.com_getListRecordStb){			
			  String xml = recorddao.getListRecordStb(keystb);
			  System.out.println(xml);
			  out.println(xml);
		}else 
		if (lenh == Command.com_getListRecordCore){			
			  String xml = recorddao.getListRecordCore();
			  System.out.println(xml);
			  out.println(xml);
		}else
		if (lenh == Command.com_setScheduleStb){	
			  String tenkenh = request.getParameter(Param.tenkenh);
			  String starttime = request.getParameter(Param.starttime);
			  String stoptime = request.getParameter(Param.stoptime);
			  String idkenh = request.getParameter(Param.idkenh);
			  System.out.println(lenh + " | " + tenkenh + " | " + starttime + " | " + stoptime + " | " + idkenh); 
			  recorddao.setScheduleStb(idkenh,tenkenh, keystb, starttime, stoptime);
			  out.println("OK");
		}else
		if (lenh == Command.com_delScheduleStb){	
			  String idrecord = request.getParameter(Param.idrecord);
			  recorddao.delScheduleStb(idrecord);
			  out.println("OK");
		}else
		if (lenh == Command.com_delScheduleCore){	
			  String idrecord = request.getParameter(Param.idrecord);
			  recorddao.delScheduleCore(idrecord);
			  out.println("OK");
		}else
		if (lenh == Command.com_updateStatusStbRecord){	
			  String idrecord = request.getParameter(Param.idrecord);
			  String status_ = request.getParameter(Param.status);
			  int status = 0;
			  if (status_ != null) status = new Integer(status_).intValue();
			  recorddao.updateStatusStbRecord(idrecord, status);
			  out.println("OK");
		}else
		if (lenh == Command.com_updateLinkStbRecord){	
			  String idrecord = request.getParameter(Param.idrecord);
			  String urlrecord = request.getParameter(Param.urlrecord);
			  recorddao.updateLinkStbRecord(idrecord, urlrecord);
			  out.println("OK");
		}else
		if (lenh == Command.com_updateSizeStbRecord){	
			  String idrecord = request.getParameter(Param.idrecord);
			  String size = request.getParameter(Param.size);
			  recorddao.updateSizeStbRecord(idrecord, size);
			  out.println("OK");
		}else
		if (lenh == Command.com_updateStatusCore){	
			  String idrecord = request.getParameter(Param.idrecord);
			  String status = request.getParameter(Param.status);
			  recorddao.updateStatusCore(idrecord, status);
			  out.println("OK");
		}else
		/*if (lenh == Command.com_updateStatusCore){	
			  String idrecord = request.getParameter(Param.idrecord);
			  String status = request.getParameter(Param.status);
			  recorddao.updateStatusCore(idrecord, status);
			  out.println("OK");
		}*/
		//End Cac chuc nang danh cho Record
		
		//Them phan dong bo thoi gian danh cho cac stb khong dung thoi gian
		if (lenh == Command.com_gettiem)
		{	
			String time = DateHelper.format(new Date(),"yyyy-MM-dd HH:mm:ss");
			out.print(time);
		}else
		if (lenh == Command.com_gethelp)
		{	
			System.out.println("Ip: " + ipClient);
			String xml = casdao.getHelp(keystb);
			System.out.println(xml);
			out.println(xml);
		}else	
		//Them phan welcome
		if (lenh == Command.com_getwelcome)
		{	
		      Properties prop = new Properties();
		      FileInputStream inStream = new FileInputStream("Config/welcome.properties");
		      prop.load(inStream);

		      String com_getwelcome = prop.getProperty("welcome"," ");
		      inStream.close();
		      System.out.println(com_getwelcome);
			  out.println(com_getwelcome);
		}else
		//Set lang danh cho phan cas
		if (lenh == Command.com_getlang){			
			  System.out.println("Ip: " + ipClient + " - In setLang(" + keystb + "," + langid + ")");
			  int xml = casdao.setLang(keystb, new Integer(langid).intValue());
			  System.out.println(xml);
			  out.println(xml);
		}else
		if (lenh == Command.com_getlogin){			
			  System.out.println("Ip: " + ipClient + " - In login(" + keystb + ")");
			  
			  String xml = casdao.login(keystb,ip);
			  System.out.println(xml);
			  out.println(xml);
		}else
		if (lenh == Command.com_getreg){			
			  System.out.println("Ip: " + ipClient + " - In register(" + keystb + ","+ room +"," + ip + ")");
			  int xml = casdao.register(keystb, room, ip);
			  System.out.println(xml);
			  out.println(xml);
		}else
		if (lenh == Command.com_getLangs)
		{
			System.out.println("Ip: " + ipClient + " - In getLangs(" + keystb + ")");
			casdao.updateIPbox(keystb, ipClient);
			String xml = casdao.getLangs(keystb);
			System.out.println(xml);
			out.println(xml);
		}else
			if (lenh == Command.com_stbftpapk)
			{
				System.out.println("Ip: " + ipClient + " - SN: " + keystb);
				String xml = casdao.getStbftpapk(keystb);
				System.out.println(xml);
				out.println(xml);
			}else	
				if (lenh == Command.com_updatestatusstbftpapk)
				{
					String idapk = request.getParameter(Param.idapk);
					System.out.println("Ip: " + ipClient + " - SN: " + keystb + " - idapk: " + idapk);
					
					String xml = casdao.updatestatusStbftpapk(keystb,idapk);
					System.out.println(xml);
					out.println(xml);
				}else 	
		//lenh insert cac noi dung da duoc xem.
		if (lenh == Command.com_static){
			  int id_content = new Integer(request.getParameter(Param.contentid)).intValue();
			  String type = request.getParameter(Param.type);
			  System.out.println("Ip: " + ipClient + " - In statics(" + keystb + ","+ id_content +"," + type + ")");
			  int xml = casdao.statics(keystb, id_content, type);
			  System.out.println(xml);
			  out.println(xml);
		}else
		//lenh tich cuoc cac noi dung xem
		if (lenh == Command.com_chargeVod){
			  /*
			   * String keystb ,int idcontent,String price,String namecontent,
			  			   String servicename, String typeprice, String nameguest,String pincode
			   * */
			  int contentid = new Integer(request.getParameter(Param.contentid)).intValue();
			  String price = request.getParameter(Param.price);
			  String namecontent = request.getParameter(Param.namecontent);
			  String servicename = request.getParameter(Param.servicename);
			  String typeprice = request.getParameter(Param.typeprice);
			  String nameguest = request.getParameter(Param.nameguest);
			  String pincode = request.getParameter(Param.pincode);
			  
			  System.out.println("Ip: " + ipClient + " - In chargeVod(" + keystb + ","+ contentid +"," + price + ")");
			  int xml = voddao.chargeVod(keystb, contentid, price, namecontent, servicename, typeprice, nameguest, pincode);
			  System.out.println(xml);
			  out.println(xml);
		}else
		//lenh kiem tra da tinh cuoc chua
		if (lenh == Command.com_checkpaid)
		{
			  String contentid = request.getParameter(Param.contentid);
			  			  
			  System.out.println("Ip: " + ipClient + " - In checkpaid(" + keystb + ","+ contentid + ")");
			  int xml = voddao.checkpaid(keystb,contentid);
			  System.out.println(xml);
			  out.println((xml + "").trim());
		}else
		//lay danh sach chu de phim theo tung  stb
		if (lenh == Command.com_getlistsubjectvod){			
			  System.out.println("Ip: " + ipClient + " - In getAllNorSubjects(" + keystb + ")");
			  String xml = voddao.getAllNorSubjects(keystb);
			  System.out.println(xml);
			  out.println(xml);
		}else
		//Lay danh sach phim thuoc chu 
		if (lenh == Command.com_getlistfileofsubject)
		{
			short subjectid = new Short(request.getParameter(Param.subjectid)).shortValue();
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ipClient + " - In getMODCtnIDsOfSubjectNew(" + keystb + "," + subjectid + "," + fromRow + "," + noRows + ")");
			String xml = voddao.getMODCtnIDsOfSubjectNew(keystb,subjectid,fromRow,noRows);
			System.out.println(xml);
			out.println(xml);
		}else
		//Lay tong so luong cua phim
		if (lenh == Command.com_getountilm)
		{
			vodsubjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			int count = voddao.countFilm(vodsubjectid);
			out.println(count);
		}else
		//Lay danh sach link url ngon ngu 
		if (lenh == Command.com_getlisturlsub)
		{
			System.out.println("Ip: " + ipClient + " - In getUrlSub(" + request.getParameter(Param.contentid) + ")");
			int ctnid = new Integer(request.getParameter(Param.contentid)).intValue();
			String xml = voddao.getUrlSub(ctnid);
			System.out.println(xml);
			out.println(xml);
		}else
		//lay danh sach chu de nhac
		if (lenh == Command.com_getmodsubject)
		{
			System.out.println("Ip: " + ipClient + " - In getModSubject(" + keystb + ")");
			String xml = moddao.getModSubject(keystb);
			System.out.println(xml);
			out.println(xml);
		}else
		//lay danh sach nhac thuoc chu de
		if (lenh == Command.com_getModListSongSubject)
		{
			short subjectid = new Short(request.getParameter(Param.subjectid)).shortValue();
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ipClient + " - In getModListSongSubject(" + keystb + "," + subjectid + "," + fromRow + "," + noRows + ")");
			String xml = moddao.getModListSongSubject(keystb,subjectid,fromRow,noRows);
			System.out.println(xml);
			out.println(xml);
		}else
		//lay tong so luong nhac thuoc chu de
		if (lenh == Command.com_getountmusic)
		{
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			int count = moddao.countMusic(subjectid);
			out.println(count);
		}else
		//lay hinh cua nhac
		if (lenh == Command.com_getUrlImageBack)
		{
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			String url = moddao.getUrlImageBack(subjectid);
			System.out.println(url);
			out.println(url);
		}else
		//Danh sach chu de Livetv
		if (lenh == Command.com_getlivesubject)
		{	
			int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ipClient + " - In getLiveTvSubject(" + keystb  + "," + fromRow + "," + noRows + ")");
			String xml = livedao.getLiveTvSubject(keystb, fromRow, noRows);
			System.out.println(xml);
			out.println(xml);
		}else
			//Danh sach chu de Livetv phugia 
		if (lenh == Command.com_getlivesubjectphugia)
		{	
			//int fromRow = new Integer(request.getParameter(Param.fromRow)).intValue();
			//int noRows = new Integer(request.getParameter(Param.noRows)).intValue();
			System.out.println("Ip: " + ipClient + " - In getLiveTvSubjectPhuGia(" + keystb + ")");
			String xml = livedao.getLiveTvSubjectPhuGia(keystb, -1, -1);
			System.out.println(xml);
			out.println(xml);
		}else
			//Danh sach chu de kenh Livetv ket hop 
		if (lenh == Command.com_getChannelLivetivi)
		{	
			
			System.out.println("Ip: " + ipClient + " - In getChannelLivetivi(" + keystb + ")");
			String xml = livedao.getChannelLivetivi(keystb);
			System.out.println(xml);
			out.println(xml);
		}
		else
		//Danh sach kenh thuoc chu de livetv
		if (lenh == Command.com_getlivechannel)
		{	
			System.out.println("Ip: " + ipClient + " - In getLiveTvSubject(" + channelid  + ")");
			String xml = livedao.getLivetvChannelList(new Integer(channelid).intValue());
			System.out.println(xml);
			out.println(xml);
		}else
		//Tong so luong cua kenh thuoc chu de nao do
		if (lenh == Command.com_getlivecount)
		{	
			int subjectid = new Integer(request.getParameter(Param.subjectid)).intValue();
			System.out.println("Ip: " + ipClient + " - In countLiveTv(" + subjectid + ")");
			int xml = livedao.countLiveTv(subjectid);
			System.out.println(xml);
			out.println(xml);
		}else
		//lenh danh cho pms
		if (lenh == Command.com_getMessageInfo)
		{
			int messId = new Integer(request.getParameter(Param.messId)).intValue();
			String xml = pmsdao.getMessageInfo(messId, keystb);
			System.out.println(xml);
			out.println(xml);
		}else
		if (lenh == Command.com_getConfigInfo)
		{
			String  key  =  request.getParameter(Param.key);
			String xml = casdao.getConfigInfo(key);
			System.out.println(xml);
			out.println(xml);
		}else
		if (lenh == Command.com_getLinkPromotion)
		{
			int proId = new Integer(request.getParameter(Param.proId)).intValue();
			String xml = pmsdao.getLinkPromotion(proId,keystb);
			System.out.println(xml);
			out.println(xml);
		}else
		if (lenh == Command.com_getLocations)
		{
			String serviceid = request.getParameter(Param.serviceid);
			String xml = pmsdao.getLocations(serviceid,keystb);
			System.out.println(xml);
			out.println(xml);
		}else
		if (lenh == Command.com_getLocationPics)
		{
			String locateid = request.getParameter(Param.locateid);
			String xml = pmsdao.getLocationPics(locateid,keystb);
			System.out.println(xml);
			out.println(xml);
		}else
		if (lenh == Command.com_getwelcomelink)
		{
			//WELCOME_LINK
			String typelink = request.getParameter(Param.typelink);
			String xml = voddao.getLinkWelcome(keystb,typelink);
			//xml = xml + "?rand=" + (new Random()).nextFloat();
			System.out.println(xml);
			out.println(xml);
		}else if (lenh == Command.com_getHomeService) {
			//MAIN SERIVCE
			System.out.println("lenh: " + lenh + " - keystb: " + keystb);
			String xml = pmsdao.getHomeService(keystb);
			System.out.println(xml);
			out.println(xml);
			return;
		}else if (lenh == Command.com_getVideoInfo) {
			
			String menuid = request.getParameter(Param.menuid);
			System.out.println("lenh: " + lenh + " - keystb: " + keystb);
			String xml = pmsdao.getVideoInfo(keystb,menuid);
			System.out.println(xml);
			out.println(xml);
			return;
		}
		
	}
}
