package ehotel.admin.Monitor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import ehotel.admin.Service.ServiceParent;
import ehotel.admin.util.Def;



public class Monitor extends ServiceParent {
	/**
	 * Constructor of the object.
	 */
	 private String hostname="192.168.0.141"; 
	 private String username="root";
	 private String password="123456";
	 private int port=22;
	 private String savefile ="video.txt";
	 private String savefileConfig ="eodapp.properties";
	 private String remotedir ="/home/app/SynVideo/Log/";
	 private String rootconfig_video="/home/app/SynVideo/Config/";
	 private String rootconfig_music="/home/app/SynMusic/Config/";
	 
	LibraySSH openSSH = new LibraySSH(hostname, username,password);
	private String startService_video ="/etc/init.d/eod_syn_video start";
	private String stopService_video ="/etc/init.d/eod_syn_video stop";
	
	private String startService_music ="/etc/init.d/eod_syn_music start";
	private String stopService_music ="/etc/init.d/eod_syn_music stop";
	private String statusService="";
	//video
	private String timeout_video ="thread.timeout.vod";
	private String frompage_video="thread.vod.frpage";
	private String topage_video="thread.vod.topage";
	private String processall_video="thread.vod.all";
	//muisc
	private String timeout_music="thread.timeout.mod";
	private String frompage_music="thread.mod.frpage";
	private String topage_music="thread.mod.topage";
	private String modtype_music="thread.mod.TypeMod";
	
	//check file video 
	 private String logstartstop_video ="/home/app/SynVideo/Config/";
	 private String filename_video ="eod_syn_video";
	 //check file music 
	 private String logstartstop_music ="/home/app/SynMusic/Config/";
	 private String filename_music ="eod_syn_music";
	XmlRpc xmlRpc =new XmlRpc();
	public Monitor() 
	{
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
		//response.setContentType("text/html");
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
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
			request.setAttribute("fileJSP", "../monitor/monitor.jsp");
			this.showJSPpage(request, response, "/include/Mainpage.jsp");
			break;
		case  1:
			response.setContentType("text/xml");
			String st1= showConfigProcess(timeout_video,frompage_video,topage_video,processall_video,0);
			out.print(st1);
			break;
		case 2:
			//start/stop service video
			String str="";
			int check=-1;
			int type=-1;
			if(request.getParameter("srcId")!=null)
			{
				check=Integer.parseInt(request.getParameter("srcId").toString());
			}
			if(request.getParameter("typeId")!=null)
			{
				type=Integer.parseInt(request.getParameter("typeId").toString());
			}
			//type =0 video 1=music
			if(check==0){
				if(type==0){
					str =startService_video;
				}else{
					str =startService_music;
				}
			}else{
				if(type==0){
					str=stopService_video;
				}else{
					str=stopService_music;
				}
			}
			List<ehotelMonitor> list =new ArrayList<ehotelMonitor>();
			if( openSSH.connect() ) 
            {
            		list= openSSH.executeCommand(str);
	                openSSH.logout();
            }
			String chuoi="";
			chuoi =list.get(0).getFullname();
			out.print(chuoi);
			break;
		case 3:
			//view log
			Properties useropt, sysProps;
			sysProps = System.getProperties();
			String  desktopPath = sysProps.getProperty("user.dir", null)+"/" + savefile;
			 String replacePath=desktopPath.replace("\\", "/");
			String date="";
			int id =-1;
			if(request.getParameter("date")!=null)
			{
				date=request.getParameter("date").toString();
			}
			if(request.getParameter("CurrId")!=null)
			{
				id=Integer.parseInt(request.getParameter("CurrId").toString());
			}
			String viewLog ="";
			String st="";
			if(id==0){
				//show ngay khac khong phai ngay hien tai
				//viewLog+="."+date;
				//viewLog ="cat /home/app/SynVideo/Log/daily.log.2012-03-14";
				Sftp sftp =new Sftp(hostname,username,password,port,replacePath,remotedir);
				try {
					String filename="daily.log."+date;
					boolean kq =sftp.downloadFile(filename);
					if(!kq){
						st="f";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					st="f";
				}
				
			}else
			{
				/*
				 viewLog ="cat /home/app/SynVideo/Log/daily.log";
				List<ehotelMonitor> listLog =new ArrayList<ehotelMonitor>();
				if( openSSH.connect() ) 
	            {
					listLog= openSSH.executeCommandLog(viewLog);
		            openSSH.logout();
	            }
				*/
				XmlRpc _xmlRpc =new XmlRpc();
				st=getLogCurrent(_xmlRpc.getCurrentLog("VOD"));	
			}
			viewLog="";
			out.print(st);
			out.close();
			break;
		case 4:
			int timeout =-1;
			int frompage=-1;
			int topage=-1;
			String status="";
			if(request.getParameter("timeout")!=null)
			{
				timeout =Integer.parseInt(request.getParameter("timeout").toString());
			}
			if(request.getParameter("frompage")!=null)
			{
				frompage=Integer.parseInt(request.getParameter("frompage").toString());
			}
			if(request.getParameter("topage")!=null)
			{
				topage=Integer.parseInt(request.getParameter("topage").toString());
			}
			if(request.getParameter("process")!=null)
			{
				status=request.getParameter("process").toString();
			}
			int kq =updateLog(timeout_video,frompage_video,topage_video,processall_video,stopService_video,startService_video,timeout,frompage,topage,status,rootconfig_video);
			if(kq==1){
				out.print("fstop");
			}else if(kq==2){
				out.print("fwlog");
			}else if(kq==3){
				out.print("fstart");
			}else{
				
			}
			break;
		case 5:
			response.setContentType("text/xml");
			String log =getLogCurrent(xmlRpc.getCurrentLog("VOD"));
			out.print(log);
			break;
		case  6:
			response.setContentType("text/xml");
			String st2= showConfigProcess(timeout_music,frompage_music,topage_music,modtype_music,1);
			System.out.print(st2);
			out.print(st2);
			break;
		case 7:
			int timeout1 =-1;
			int frompage1=-1;
			int topage1=-1;
			String modtype="";
			if(request.getParameter("timeout")!=null)
			{
				timeout1 =Integer.parseInt(request.getParameter("timeout").toString());
			}
			if(request.getParameter("frompage")!=null)
			{
				frompage1=Integer.parseInt(request.getParameter("frompage").toString());
			}
			if(request.getParameter("topage")!=null)
			{
				topage1=Integer.parseInt(request.getParameter("topage").toString());
			}
			if(request.getParameter("modtype")!=null)
			{
				modtype=request.getParameter("modtype").toString();
			}
			int kq1 =updateLog(timeout_music,frompage_music,topage_music,modtype_music,stopService_music,startService_music,timeout1,frompage1,topage1,modtype,rootconfig_music);
			if(kq1==1){
				out.print("fstop");
			}else if(kq1==2){
				out.print("fwlog");
			}else if(kq1==3){
				out.print("fstart");
			}else{
				
			}
			break;
			//muisc
		case 8:
			response.setContentType("text/xml");
			String log1 =getLogCurrent(xmlRpc.getCurrentLog("MOD"));
			out.print(log1);
			break;
		case 9:
			String str1="";
			boolean c =checkstopstart(logstartstop_video,filename_video);
			if(!c){
				str1="stop";
			}else{
				str1="run";
			}
			out.print(str1);
		case 10:
			String str2="";
			boolean c1 =checkstopstart(logstartstop_music,filename_music);
			if(!c1){
				str2="stop";
			}else{
				str2="run";
			}
			out.print(str2);
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

		super.doPost(request, response);
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
		case 1:			
			//downloadFile(request, response);
			break;
	
		default:
			break;
		}
	}
	


	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getLog(List<ehotelMonitor> list)
	{
		
		
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
		
		for(int i=0;i<list.size();i++)
		{
			ehotelMonitor item=list.get(i);
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+item.getFullname()+"]]>";
				mData+="</name>";
				mData+="\n";
			mData+="</Item>";
		}	
		mData+="</xml>";	
		
		return mData;
	} 
	private String getLogCurrent(String log)
	{
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
			mData+="<Item>";
				mData+="<name>";
				mData+="<![CDATA["+log+"]]>";
				mData+="</name>";
				mData+="\n";
			mData+="</Item>";	
		mData+="</xml>";	
		
		return mData;
	} 
	public boolean startStopService(String cmd){
		boolean kq =false;
		if( openSSH.connect() ) 
        {
        		openSSH.executeCommand(cmd);
                openSSH.logout();
                kq=true;
        }
		return  kq;
	}
	public int updateLog(String v_timeout,String v_frompage,String v_topage,String v_other,String stopService,String startService,int _timeout,int _frompage,int _topage,String _process,String rootConfig){
		int trangthai=0;//thanh cong
		boolean kq =startStopService(stopService);
		if(!kq){
			//trang thai =1 stop khong thanh cong
			trangthai=1;
			
		}else{
			System.out.print("stop service.....");
			boolean kq2=copyLog(v_timeout,v_frompage,v_topage,v_other,_timeout,_frompage,_topage,_process,rootConfig);
			if(!kq2){
				trangthai=2;//ghi log bi loi
			}
			
		}
		boolean kq1 =startStopService(startService);
		if(!kq1){
			trangthai=3;//start khong thanh cong
		}
		System.out.print("start service ...");
		return trangthai;
	}
	public boolean copyLog(String v_timeout,String v_frompage,String v_topage,String v_other, int _timeout,int _frompage,int _topage,String _process,String rootconfig){
		boolean kq=true;
		Properties useropt, sysProps;
		sysProps = System.getProperties();
		String  desktopPath = sysProps.getProperty("user.dir", null)+"/" + savefileConfig;
		 String replacePath=desktopPath.replace("\\", "/");
		try {
				FileReader reader =null;
				 BufferedWriter writer = null;
		        BufferedReader buffer = null;
		        ArrayList list = new ArrayList();
				JSch jsch = new JSch();
			    Session session = null;
			    session = jsch.getSession(username, hostname, port);
			    session.setConfig("StrictHostKeyChecking", "no");
			    session.setPassword(password);
		        session.connect();
		        Channel channel = session.openChannel("sftp");
		        channel.connect();
		        ChannelSftp sftpChannel = (ChannelSftp) channel;
		        sftpChannel.cd(rootconfig);
		        File file = new File(replacePath);
		        if(!file.exists()){
		        	file.createNewFile();
		        }
		        sftpChannel.get(savefileConfig,replacePath);
		        System.out.print("download log ...");
		        reader = new FileReader(file);
		        buffer = new BufferedReader(reader);   
		        String getLine = "";
		        while ((getLine = buffer.readLine())!=null)
		        {
		        	String slipStr =getLine.split("=")[0];
		        	if(slipStr.trim().equals(v_timeout))
		        	{
		        		String timeout =slipStr +"="+ _timeout;
		        		list.add(timeout);
		        	}else if(slipStr.trim().equals(v_frompage)){
		        		String frompage =slipStr+"="+ _frompage;
		        		list.add(frompage);
		        	}else if(slipStr.trim().equals(v_topage)){
		        		String topage =slipStr +"="+ _topage;
		        		list.add(topage);
		        	}else if(slipStr.trim().equals(v_other)){
		        		String all =slipStr + "=" +_process;
		        		list.add(all);
		        	}else{
		        		if(getLine.trim()!=""){
		        			list.add(getLine);
		        		}
		        	}
		        	 writer = new BufferedWriter(new FileWriter(file));
		        }
		        for (int i = 0; i < list.size(); i++)
		        {
		            writer.write(list.get(i) + "\r\n");
		        }
		        reader.close();
		        writer.close();
		        buffer.close();
		        sftpChannel.put(new FileInputStream(file),savefileConfig);
		        sftpChannel.exit();
		        session.disconnect();
		        System.out.print("write log .....");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			kq=false;
		}
		return kq;
	}
	
	public ArrayList process(String v_timeout,String v_frompage,String v_topage,String v_other,String rootConfig){
		Properties useropt, sysProps;
		sysProps = System.getProperties();
		String  desktopPath = sysProps.getProperty("user.dir", null)+"/" + savefileConfig;
		 String replacePath=desktopPath.replace("\\", "/");
		 ArrayList list = new ArrayList();
		try {
				FileReader reader =null;
				 BufferedWriter writer = null;
		        BufferedReader buffer = null;
				JSch jsch = new JSch();
			    Session session = null;
			    session = jsch.getSession(username, hostname, port);
			    session.setConfig("StrictHostKeyChecking", "no");
			    session.setPassword(password);
		        session.connect();
		        Channel channel = session.openChannel("sftp");
		        channel.connect();
		        ChannelSftp sftpChannel = (ChannelSftp) channel;
		        sftpChannel.cd(rootConfig);
		        File file = new File(replacePath);
		        if(!file.exists()){
		        	file.createNewFile();
		        }
		        sftpChannel.get(savefileConfig,replacePath);
		        System.out.print("download log ...");
		        reader = new FileReader(file);
		        buffer = new BufferedReader(reader);   
		        String getLine = "";
		        while ((getLine = buffer.readLine())!=null)
		        {
			        	String slipStr =getLine.split("=")[0];
			        	if(slipStr.trim().equals(v_timeout))
			        	{
			        		list.add(getLine.split("=")[1]);
			        	}else if(slipStr.trim().equals(v_frompage)){
			        		list.add(getLine.split("=")[1]);
			        	}else if(slipStr.trim().equals(v_topage)){
			        		list.add(getLine.split("=")[1]);
			        	}else if(slipStr.trim().equals(v_other.trim())){
			        		list.add(getLine.split("=")[1]);
			        	}
		        	}
		        reader.close();
		        buffer.close();
		        sftpChannel.exit();
		        session.disconnect();
		        System.out.print("write log .....");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return list;
	}
	public String showConfigProcess(String v_timeout,String v_frompage,String v_topage,String v_other,int loai){
		ArrayList _list =new ArrayList();
		String root="";
		if(loai==0){
			root =rootconfig_video;
		}else{
			root =rootconfig_music;
		}
		_list =process(v_timeout,v_frompage,v_topage,v_other,root);
		System.out.println("ddddddddddddddddddddddd:"+_list.size());
		String mData="";
		mData+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\n";
		mData+="<xml>";
			if(_list.get(0)!=""){
			mData+="<Item>";
			mData+="<timeout>";
			mData+=_list.get(0);
			mData+="</timeout>\n";
			mData+="</Item>";
			}
			if(_list.get(1)!=""){
				mData+="<Item>";
				mData+="<frompage>";
				mData+=_list.get(1);
				mData+="</frompage>\n";
				mData+="</Item>";
			}
			if(_list.get(2)!=""){
				mData+="<Item>";
				mData+="<topage>";
				mData+=_list.get(2);
				mData+="</topage>\n";
				mData+="</Item>";
			}
			if(_list.get(3)!=""){
					mData+="<Item>";
					mData+="<process>";
					mData+=_list.get(3);
					mData+="</process>\n";
					mData+="</Item>";
			}
		mData+="</xml>";
		return mData;
	}
	public boolean checkstopstart(String logstartstop,String filename){
		 
		Properties useropt, sysProps;
		sysProps = System.getProperties();
		String  desktopPath = sysProps.getProperty("user.dir", null)+"/" + filename;
		 String replacePath=desktopPath.replace("\\", "/");
	
		boolean kq=true;
		Sftp sftp =new Sftp(hostname,username,password,port,replacePath,logstartstop);
		try {
			kq=sftp.downloadFile(filename);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			kq =false;
		}
		return kq;
	}
}