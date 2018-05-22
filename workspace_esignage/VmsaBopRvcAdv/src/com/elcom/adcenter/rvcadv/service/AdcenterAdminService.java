package com.elcom.adcenter.rvcadv.service;

import java.rmi.RemoteException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.elcom.abopuser.rvcadv.user.UserDAO;
import com.elcom.adcenter.rvcadv.group.GroupDao;
import com.elcom.adcenter.rvcadv.layout.LayoutDao;
import com.elcom.adcenter.rvcadv.playlist.PlaylistDao;
import com.elcom.adcenter.rvcadv.report.ReportaBop;
import com.elcom.adcenter.rvcadv.schedule.ScheduleDao;
import com.elcom.adcenter.rvcadv.util.DAOFactory;
import com.elcom.adcenter.rvcadv.util.DataSocketSesionStb;
import com.elcom.adcenter.rvcadv.util.DateHelper;
import com.elcom.adcenter.rvcav.content.ContentDao;

public class AdcenterAdminService {
	private static GroupDao groupdao = DAOFactory.getInstance().getGroupDao();
	private static LayoutDao layoutdao = DAOFactory.getInstance().getLayoutDao();
	private static ScheduleDao scheduledao = DAOFactory.getInstance().getScheduleDao();
	private static PlaylistDao playlistdao = DAOFactory.getInstance().getPlaylistDao();
	private static ContentDao contentdao = DAOFactory.getInstance().getContentDao();
	private static ReportaBop reportabopdao = DAOFactory.getInstance().ReportaBopDao();
	private static UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
	
	private static final Logger logger = LogManager.getLogger(AdcenterAdminService.class);
	public AdcenterAdminService()
		      throws RemoteException
		  {
			System.out.println(System.getProperty("user.dir"));
		  }//constructor
   //==================================================================
	/**
	 * Chuc nang lay ra danh sach noi dung cua nhom
	 * 
	 * @param xmlparamter 
	 * {@code 
	 *   <?xml version="1.0" encoding="UTF-8"?>
	 *   <group>
	 *     <groupid>1</groupid>
	 *     <creator>khong biet</creator>
	 *   </group>
	 * }  
	 * @return  Chuoi ket qua xml 
	 */
	public String getContentGroup(String xmlparamter)
	{
		  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.getContentGroup(" + xmlparamter+ ")");		  
		  String i = groupdao.getContentGroup(xmlparamter);			  
		  return i;
	}
	public void updateScheduleDaily(String xmlparameter){
		 scheduledao.adminUpdateScheduleDailyName(xmlparameter);
	}
	//==================================================================
	/**
	 * Chuc nang cap nhat huong cua bo cuc
	 * 
	 * @param xmlparamter 1 la huong dung, 0 la huong nam
	 * {@code
	 *     <?xml version="1.0" encoding="UTF-8"?>
	 *     <parameter>
	 *         <groupid>id_group</groupid>
	 *         </direction>1<direction>  
	 *     </parameter>
	 *     
	 * }
	 * @return Chuoi ket qua xml
	 */
	public String adminUpdateDirecGroup(String xmlparamter)
	{		  
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateDirecGroup(" + xmlparamter+ ")");		  
		  String i = groupdao.adminUpdateDirecGroup(xmlparamter);			  
		  return i;
	}
	//===================================================================
	/**
	 * Kiem tra trang thai 1 set top box online hay ofline
	 * @param xmlparamter 
	 * {@code
	 *    <parameter><stbid>id_stb</stbid></parameter>
	 * }
	 * @return Chuoi ket qua xml
	 */
	public String adminCheckStb(String xmlparamter)
	{		  
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.sp_adminCheckStb(" + xmlparamter+ ")");		  
		  String i = groupdao.sp_adminCheckStb(xmlparamter);			  
		  return i;
	}
	//===================================================================
	/**
	 * Xoa 1 stb ca khoi he thong 
	 * @param xmlparamter
	 * {@code
	 *   <parammeter><stbid>123</stbid></parammeter>
	 * }
	 * @return
	 */
	public String adminDelStb(String xmlparamter)
	{		  
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelStb(" + xmlparamter+ ")");		  
		  String i = groupdao.adminDelStb(xmlparamter);			  
		  return i;
	}
	public String adminRemoveBox(String xmlParamter)
	{		  
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminRemoveBox(" + xmlParamter+ ")");		  
		  String i = groupdao.adminRemoveBox(xmlParamter);			  
		  return i;
	}
	
	//==================================================================
	/**
	 * Lay danh sach noi dung khong thuoc group
	 * @param xmlparamter
	 * {@code
	 *   <parammeter><groupid>123</groupid></parammeter>
	 * }
	 * @return
	 */
	public String admingetContentNoGroup(String xmlparamter)
	{
		  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.getContentGroup(" + xmlparamter+ ")");
		  
		  String i = groupdao.admingetContentNoGroup(xmlparamter);			  
		  return i;
	}
	
	//==================================================================
	/**
	 * Lay danh sach cac item  layout thuoc nhom
	 * @param xmlparamter
	 * {@code
	 *     <parammeter>
	 *        <groupid>123</groupid>
	 *        <layoutnameid>123</layoutnameid>
	 *     </parammeter>
	 *     
	 * }
	 * @return
	 */
	public String admingetLayoutGroup(String xmlparamter)
	{
		  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.sp_admingetLayoutGroup(" + xmlparamter+ ")");
		  String i = groupdao.getLayoutGroup(xmlparamter);		  
		  return i;
	}
	//==================================================================
	/**
	 * Uodate cac thong so toa do
	 * @param xmlparamter
	 * {@code
	 *    <parameter>
			  <item>
					<layoutid>1</layoutid>
					<xcoor></xcoor>
					<ycoor></ycoor>
					<width></width>
					<height></height>
			  </item>
		 </parameter>
	 * }
	 * @return
	 */
	public String adminUpdateLayoutCoor(String xmlparamter)
	{
		  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateLayoutCoor(" + xmlparamter+ ")");
		  String i = layoutdao.adminUpdateLayoutCoor(xmlparamter);	  
		  return i;
	}
	//==================================================================
	/**
	 * Update lai ten cua playlist
	 * @param xmlparamter
	 * {@code
	 *    <parameter><playlistid></playlistid><nameplaylist></nameplaylist></parameter>
	 * }
	 * @return
	 */
	public String adminUpdatePlayListName(String xmlparamter)
	{
		  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdatePlayListName(" + xmlparamter+ ")");
		  String i = playlistdao.adminUpdatePlayListName(xmlparamter);		  
		  return i;
	}
	//==================================================================
	/**
	 * Update cac item cua 1 danh sach
	 * @param xmlparamter
	 * {@code
	 *   <parameter>
	 *     <item>
	 *       <playlistitemid>111</playlistitemid>
	 *       <startdate>00:00:00</startdate>
	 *       <stopdate>00:01:00</stopdate>
	 *       <item>
	 *     </parameter>
	 * }
	 * @return
	 */
	public String adminUpdatePlaylistTime(String xmlparamter)
	{
			  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdatePlaylistTime(" + xmlparamter+ ")");
		String i = playlistdao.adminUpdatePlaylistTime(xmlparamter);		  
		return i;
	}
	public String abopDeletePlaylistItems(String xmlparamter)
	{
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopDeletePlaylistItems(" + xmlparamter+ ")");
		String i = playlistdao.abopDeletePlaylistItems(xmlparamter);		  
		return i;
	}
	//==================================================================
	/**
	 * Xoa 1 hay nhieu item cua Playlist
	 * @param xmlparamter
	 * {@code
	 *    <parameter>
	 *       <item>
	 *         <playlistitemid>111</playlistitemid>
	 *       <item>
	 *     </parameter>
	 * }
	 * @return
	 */
	public String adminDelItemPlaylist(String xmlparamter)
	{		
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelItemPlaylist(" + xmlparamter+ ")");
		String i = playlistdao.adminDelItemPlaylist(xmlparamter);		  
		return i;
	}
	
	//==================================================================
	/**
	 * Lay ra tat ca danh sach cua nhom
	 * @param xmlparamter creator: neu la 'elcom' thi se hien thi tat ca cac nhom
	 *                    groupid: mac dinh la -1
	 * {@code
	 *    <parameter>
	 *        <groupid>111</groupid>
	 *        <creator></creator>
	 *    </parameter>
	 * }
	 * @return
	 */
	public String admingetListGroup(String xmlparamter)
	{
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetListGroup("+xmlparamter+")");
		String i = groupdao.getListGroup(xmlparamter);		  
		return i;
	}
	//==================================================================
	/**
	 * Lay ten danh sach cua nhom
	 * @param xmlparamter
	 * {@code
	 *   <parameter>
	 *      <groupid>1,2,3,4</groupid>
	 *      <creator>nothing</creator>
	 *   </parameter>
	 * }
	 * @return
	 */
		public String admingetListGroups(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetListGroups("+xmlparamter+")");
			String i = groupdao.getListGroups(xmlparamter);		  
			return i;
		}
	//==================================================================
		/**
		 * Tao nhom moi
		 * @param xmlparamter creator: la user dang nhap tao nhom
		 * {@code
		 *    <parameter>
		 *      <groupname>ten_group</groupname>
		 *      <parentgroupid>-1</parentgroupid>
		 *      <creator>elcom</creator>
		 *    </parameter>
		 * }
		 * @return
		 */
	public String adminNewGroup(String xmlparamter) 
	{
		//<group><parentgroupid>0</parentgroupid><groupname>HuuTest</groupname></group>
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewGroup("+xmlparamter+")");
		String i = groupdao.adminNewGroup(xmlparamter);		  
		return i;
	}
	//==================================================================
	/**
	 * Xoa 1 nhom (khi xoa 1 nhom thi tat ca cac thong tin thuoc nhom se bij huy het)
	 * @param xmlparamter
	 * {@code
	 * 	<parameter>
	 *     <groupid>111</groupid>
	 * 	</parameter>
	 * }
	 * @return
	 */
	public int adminDelGroup(String xmlparamter) 
	{
		//<group><groupid>0</groupid><groupname>HuuTest</groupname></group>
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelGroup("+xmlparamter+")");
		String i = groupdao.adminDelGroup(xmlparamter);		  
		return new Integer(i).intValue();
	}
	//==================================================================
	/**
	 * 
	 * @param xmlparamter
	 * @return
	 */
		public String adminMoveGroup(String xmlparamter) 
		{			
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminMoveGroup("+xmlparamter+")");
			String i = groupdao.adminMoveGroup(xmlparamter);		  
			return i;
		}
	
	//==================================================================
	/**
	 * Update lai ten cua group
	 * @param xmlparamter
	 * <parameter>
	 *   <groupid>111</groupid>
	 *   <groupname>ten_group_cap_nhat</groupname>
	 * </parameter>
	 * @return
	 */
	public int adminUpdateGroup(String xmlparamter)
	{
		//<group><groupid>0</groupid><groupname>HuuTest</groupname></group>
		logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateGroup("+xmlparamter+")");
		String i = groupdao.adminUpdateGroup(xmlparamter);		  
		return new Integer(i).intValue();
	}	
	//==================================================================
	/**
	 * Lay ra danh sach cac stb thuoc nhom
	 * @param xmlparamter
	 * <parameter>
	 *    <groupid>111</groupid>
	 * </parameter>
	 * @return
	 */
		public String getStbGroup(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.getStbGroup("+xmlparamter+")");
			String i = groupdao.getStbGroup(xmlparamter);		  
			return i;
		}
	//======================================================================
	/**
	 * 	Lay ra tat ca danh sach cac stb
	 * @param xmlparamter groupid = -1 mac dinh
	 * {@code
	 *   <parameter>
	 *      <groupid>-1</groupid>
	 *   </parameter>
	 * }
	 * @return
	 */
		public String admingetListStbAll(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetListStbAll("+xmlparamter+")");
			String i = groupdao.admingetListStbAll(xmlparamter);		  
			return i;
		}
		//======================================================================
	/**
	 * Lay ra tat ca danh sach cac stb nhung phan quyen muc cao nhat 'elcom'
	 * @param xmlparamter
	 *    <parameter>
	 *      <groupid>111</groupid>
	 *      <creator>elcom</creator>
	 *    </parameter>
	 * @return
	 */
		//TODO
		public String admingetListStbAlls(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetListStbAlls("+xmlparamter+")");
			String i = groupdao.admingetListStbAlls(xmlparamter);		  
			return i;
		}
		//==================================================================
	/**
	 * Update thong tin cua 1 stb
	 * @param xmlparamter
	 * {@code
	 *   <parameter>
	 *     <stbid>0</stbid>
	 *     <name>haha</name>
	 *     <urlserver></urlserver>
	 *     <savelocalmedia></savelocalmedia>
	 *     <savelocalfile></savelocalfile>
	 *   </parameter>
	 * }
	 * @return
	 */
		public String adminUdateStb(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUdateStb("+xmlparamter+")");
			String i = groupdao.adminUdateStb(xmlparamter);		  
			return i;
		}	
		//==================================================================
		/**
		 * day noi dung len stb
		 * @param xmlparamter
		 * {@code
		 *    <parameter>
	     *  	<item>
		 * 			<stbid>111</stbid>
		 * 			<groupid>111</groupid>
		 *  	</item>
		 *  </parameter>
		 * }
		 * @return
		 */
		public String adminStbPush(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminStbPush("+xmlparamter+")");
			String i = groupdao.adminStbPush(xmlparamter);		  
			return i;
		}
		
	//==================================================================
	/**
	 * Them 1 stb vao nhom
	 * @param xmlparamter
	 * {@code
	 *     <parameter>
	 *         <stbid>111</stbid>
	 *         <groupid>111</groupid>
	 *     </parameter>
	 * }
	 * @return
	 */
//		public String adminAddStbIntoGroup(String xmlparamter)
//		{
//			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminAddStbIntoGroup("+xmlparamter+")");
//			String i = groupdao.adminAddStbIntoGroup(xmlparamter);		  
//			return i;			
//		}
	//==================================================================
	/**
	 * Lay cac item cua playlist
	 * @param xmlparamter
	 * {@code
	 *    <parameter>
	 *    	<playlistid>111</playlistid>
	 *   </parameter>
	 * } 
	 * @rdeturn
	 */
		public String admingetPlaylistItem(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetPlaylistItem("+xmlparamter+")");
			String i = playlistdao.admingetPlaylistItem(xmlparamter);		  
			return i;	
		}
	//==================================================================
		/** Lay danh sach item cua playlist chay trong bo cuc
		 * <parameter>
		 * 	<playlistid>12</playlistid>	
		 * </parameter>
		 */
		/**
		 * Lay danh sach item cua playlist chay trong bo cuc
		 * @param xmlparamter
		 * {@code
		 *   <parameter>
		 * 	    <playlistid>12</playlistid>	
		 *    </parameter>
		 * }
		 * @return
		 */
		public String admingetPlayListLayout(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetPlayListLayout("+xmlparamter+")");
			String i = playlistdao.admingetPlayListLayout(xmlparamter);		  
			return i;	
		}
		
	//==================================================================	
	/**
	 * Di chuyen stb ra khoi nhom
	 * @param xmlparamter
	 * {@code
	 *     <parameter>
	 *     	  <stbid>111</stbid>
	 *     </parameter>
	 * }
	 * @return
	 */
		public String adminRemoveStbGroup(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminRemoveStbGroup("+xmlparamter+")");
			String i = groupdao.adminRemoveStbGroup(xmlparamter);		  
			return i;
		}
	//==================================================================
	/**
	 * Lay ra danh sach playlist thuoc nhom
	 * @param xmlparamter
	 * {@code
	 * 	  <parameter>
	 *       <groupid>111</groupid>
	 *    </parameter>
	 * } 
	 * @return
	 */
		public String admingetPlayListGroup(String xmlparamter)
		{
			  //logger.info("Ip: " + DateHelper.getIpRemote() + " - In MediaService.checkPlayMedia(" + keystb + "," + idfilm + ")");
			  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.sp_admingetPlayListGroup(" + xmlparamter+ ")");
			  String i = playlistdao.getPlayListGroup(xmlparamter);		  
			  return i;
		}
	//===================================================================
	/**
	 * 
	 * @param xmlparamter
	 * @return
	 */
		public String admingetPlayList(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetPlayList(" +xmlparamter + ")");	
			String i = playlistdao.admingetPlayList(xmlparamter);		  
			return i;
		}
	//===================================================================
	/**
	 * Chuc nang tao cac item cho bo cuc 
	 * @param xmlparamter
	 * {@code
	 *    <?xml version='1.0' encoding='UTF-8'?>
	 *		<Layout>
	 *			<item>
	 *			<name>Clip 01</name>
	 *			<groupid>1</groupid>
	 *			<type>1</type>
	 *			<desc>Video quan cao 01</desc>
	 *			<x>180</x>
	 *			<y>0</y>
	 *			<width>1100</width>
	 *			<height>620</height>
	 *			<order>1</order>
	 *			</item>
	 *			</Layout>
	 * }
	 * @return
	 */
		public int adminInsertLayout(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminInsertLayout(" +xmlparamter + ")");	
			int i = layoutdao.adminInsertLayout(xmlparamter);		  
			return i;
		}
	//===================================================================
		public String adminInsertLayoutName(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminInsertLayoutName(" +xmlparamter + ")");	
			String layoutid = layoutdao.adminNewLayoutName(xmlparamter);
			return layoutid;
		}	
	//===================================================================
		public String admingetLayoutContainContent(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetLayoutContainContent(" +xmlparamter + ")");	
			return layoutdao.admingetLayoutContainContent(xmlparamter);			
		}
		
	//====================================================================
		 public String getSessionid(String server,int port)
		 {
			String result = "";
			DataSocketSesionStb data = new DataSocketSesionStb(server, port);
		    try{    		
		    		result = data.getListSessionStb(data,6);    		
		    }catch(Exception ex){ex.printStackTrace();}
		    return result;
		 }
	//=======================================================================
		 public int adminInsertPlaylistCore(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminInsertPlaylistCore(" + xmlparamter+ ")");
			 int i = playlistdao.adminInsertItemPlaylist(xmlparamter);		  
			 return i;
		 }
	//=======================================================================
		 public String adminDelPlaylist(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelPlaylist(" + xmlparamter+ ")");
			 String re = playlistdao.adminDelPlaylist(xmlparamter);		  
			 return re;
		 }
	//=======================================================================
		 public int adminNewPlaylistName(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewPlaylistName(" + xmlparamter+ ")");
			 int result = playlistdao.adminNewPlaylistName(xmlparamter);		  
			 return result;
		 }	 
	//=======================================================================
		 public String admingetLayoutName(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetLayoutName(" + xmlparamter+ ")");
			 String i = layoutdao.admingetLayoutName(xmlparamter);		  
			 return i;
		 }
		//=======================================================================
		   public String admingetLayoutNameType(String xmlparamter)
		   {
		    logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetLayoutNameType(" + xmlparamter+ ")");
		    String i = layoutdao.admingetLayoutNameType(xmlparamter);    
		    return i;
		   }
	//======================================================================
		/* public int adminNewLayoutName(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewLayoutName(" + xmlparamter+ ")");
			 String i = layoutdao.adminNewLayoutName(xmlparamter);		  
			 return new Integer(i).intValue();
		 }*/
		//======================================================================
		 @SuppressWarnings("unused")
		public int adminUpdateLayoutName(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateLayoutName(" + xmlparamter+ ")");
			 String i = layoutdao.adminUpdateLayoutName(xmlparamter);		  
			 return 1;
		 }	
		 //======================================================================
		 public String adminNewLayoutContent(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewLayoutContent(" + xmlparamter+ ")");
			 String i = layoutdao.adminNewLayoutContent(xmlparamter);		  
			 return i;
		 }	
		 //======================================================================
		 
		 public String adminDelLayOutItem(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelLayOutItem(" + xmlparamter+ ")");
			 String i = layoutdao.adminDelLayOutItem(xmlparamter);		  
			 return i;
		 }
		 //======================================================================
		 @SuppressWarnings("unused")
		public int adminDelLayoutName(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelLayoutName(" + xmlparamter+ ")");
			 String i = layoutdao.adminDelLayoutName(xmlparamter);		  
			 return 1;
		 }
		 //======================================================================
		 
		 public String admingetTypeLayout()
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetTypeLayout()");
			 String i = layoutdao.admingetTypeLayout();		  
			 return i;
		 }
		 //========================================================================
		 public void pushContentIntoStb(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.pushContentIntoStb(" + xmlparam+ ")");
			 groupdao.pushContentIntoStb(xmlparam);				 
		 }
		 //=========================================================================
		 public int removeContentStb(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.removeContentStb(" + xmlparam+ ")");
			 return groupdao.removeContentStb(xmlparam);	
		 }
		 //=========================================================================each 09052013
		 public int removeContentStbEach(String xmlparam, String mac)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.removeContentStbEach(" + xmlparam+ ")");
			 return groupdao.removeContentStbEach(xmlparam, mac);	
		 }
		 //=========================================================================
		 public String admingetContentStb(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetContentStb(" + xmlparam+ ")");
			 return groupdao.admingetContentStb(xmlparam);				 
		 }
		 //========================================================================		 
		 public int adminInserContentIntoGroup(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminInserContentIntoGroup(" + xmlparam+ ")");
			 groupdao.adminInserContentIntoGroup(xmlparam);		
			 return 1;
		 }
		//========================================================================		 
		 public int adminDeleteContentIntoGroup(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDeleteContentIntoGroup(" + xmlparam+ ")");
			 groupdao.adminDeleteContentIntoGroup(xmlparam);		
			 return 1;
		 }
		//=========================================================================
		 public String admingetScheduleDailyGroup(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetScheduleGroup(" + xmlparam+ ")");
			 return scheduledao.admingetScheduleDailyGroup(xmlparam);	
			 
		 }
		 //========================================================================
		 public String adminUpdateScheduleDailyTime(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateScheduleDailyTime(" + xmlparam+ ")");
			 return scheduledao.adminUpdateScheduleDailyTime(xmlparam);	
			 
		 }
		 //========================================================================		 
		 public String adminDelSchduledailyTime(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelSchduledailyTime(" + xmlparam+ ")");
			 return scheduledao.adminDelSchduledailyTime(xmlparam);	
			 
		 }
		 //========================================================================
		 public String admingetSchedulePeriGroup(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetSchedulePeriGroup(" + xmlparam+ ")");
			 return scheduledao.admingetSchedulePeriGroup(xmlparam);		
			 
		 }
		//========================================================================
		 public String admingetItemScheduleDailyForPerio(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetItemScheduleDailyForPerio(" + xmlparam+ ")");
			 return scheduledao.admingetItemScheduleDailyForPerio(xmlparam);	
			 
		 }		 
		 //=========================================================================
		 public String adminNewScheduleDaily(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewScheduleDaily(" + xmlparam+ ")");
			 return scheduledao.adminNewScheduleDaily(xmlparam);
		 }
		 //==========================================================================
		 public void adminNewSchedulePeri(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewSchedulePeri(" + xmlparam+ ")");
			 scheduledao.adminNewSchedulePeri(xmlparam);
		 }
		 //==========================================================================
		 public String adminNewSchedulePeriName(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewSchedulePeriName(" + xmlparam+ ")");
			 return scheduledao.adminNewSchedulePeriName(xmlparam);
		 }
		 //==========================================================================
		 public void adminDelSchedulePeri(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelSchedulePeri(" + xmlparam+ ")");
			 scheduledao.adminDelSchedulePeri(xmlparam);
		 }
		//==========================================================================
		 public void adminDelSchedulePeriItem(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelSchedulePeri(" + xmlparam+ ")");
			 scheduledao.adminDelSchedulePeriItem(xmlparam);
		 }
		 //==========================================================================
		 public void adminUpdateSchedulePeri(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateSchedulePeri(" + xmlparam+ ")");
			 scheduledao.adminUpdateSchedulePeri(xmlparam);
		 }
		 
		 //==========================================================================
		 public String admingetScheduleDailyNamGroup(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetScheduleDailyNamGroup(" + xmlparam+ ")");
			 return scheduledao.admingetScheduleDailyNamGroup(xmlparam);
		 }
		 //===========================================================================
		 @SuppressWarnings("unused")
		public String admingetConfig(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetConfig()");
			 String tmp = groupdao.admingetConfig();
			 
			 return groupdao.admingetConfig();
		 }
		 //===========================================================================		 
		 public String adminNewContent(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.admingetConfig()");
			 return contentdao.adminNewContent(xmlparam);
		 }
		 //===========================================================================
		 public String adminNewScheduleDailyName(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewScheduleDailyName(" + xmlparam+ ")");
			 return scheduledao.adminNewScheduleDailyName(xmlparam);
		 }
		 //===========================================================================
		 public String adminDelScheduleDailyName(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelScheduleDailyName(" + xmlparam+ ")");
			 return scheduledao.adminDelScheduleDailyName(xmlparam);
		 }
		 //=======================Content logic=======================================
		 public void adminNewContentLogic(String xmlparam) throws RemoteException
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewContent(" + xmlparam+ ")");
			 groupdao.adminNewContent(xmlparam);	 
		 }
		 //===================Content Text=====================================
		 public void adminNewContentText(String xmlparam)
		 {
			 try{
				 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewContentText(" + xmlparam+ ")");
				 contentdao.adminNewContentText(xmlparam);	 
			 }catch(Exception ex)
			 {
				 ex.printStackTrace();
			 }
		 }
		//==================================================================
		 public void adminUpdateConfig(String xmlparamter)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateConfig(" + xmlparamter+ ")");
			 groupdao.adminUpdateConfig(xmlparamter);
		 }
		//===================================================================
		 public void adminDelContentText(String xmlparam) 
		 {
			 try{
				 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDelContentText(" + xmlparam+ ")");
				 contentdao.adminDelContentText(xmlparam);	 
			 }catch(Exception ex){}
		 }
		//===================================================================
		 public void adminUpdateContentText(String xmlparam)
		 {
			 try{
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateContentText(" + xmlparam+ ")");
			 contentdao.adminUpdateContentText(xmlparam);	
			 }catch(Exception ex){}
		 }		 
		 //==================================================================		 
		 public String adminReportLayout(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminReportLayout("+xmlparam+")");
			 String tmp = reportabopdao.adminReportLayout(xmlparam);
			 logger.info(tmp);
			 return tmp;
		 }		
		//===================================================================
		 public String adminReportContentAll(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminReportContentAll("+xmlparam+")");
			 String tmp = reportabopdao.adminReportContentAll(xmlparam);
			 
			 return tmp;
		 }
		//===================================================================
		 public String adminReportPlaylist(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminReportPlaylist("+xmlparam+")");
			 String tmp = reportabopdao.adminReportPlaylist(xmlparam);
			 
			 return tmp;
		 } 
		//===================================================================
		 public String adminReportDaily(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminReportDaily(" + xmlparam + ")");
			 String tmp = reportabopdao.adminReportDaily(xmlparam);
			 
			 return tmp;
		 }  
		//===================================================================
		 public String adminReportSchedulePeriod(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminReportSchedulePeriod(" + xmlparam + ")");
			 String tmp = reportabopdao.adminReportSchedulePeriod(xmlparam);
			 
			 return tmp;
		 } 
		//===================================================================
		 public String adminReportStb(String xmlparam)
		 {
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.sp_adminReportStb(" + xmlparam + ")");
			 String tmp = reportabopdao.sp_adminReportStb(xmlparam);
			 
			 return tmp;
		 }
		 /*
		  	adminUpdateStbDefaultHome 10.10
		  */
		 public void adminUpdateStbDefaultHome(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateStbDefaultHome(" +xmlparamter + ")");	
			groupdao.adminUpdateStbDefaultHome(xmlparamter);			
		 }
		 
		 /*
		  	adminNewContentWithDuration 28.2.2013
		  */
		 public void adminNewContentWithDuration(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminNewContentWithDuration(" +xmlparamter + ")");	
			contentdao.adminNewContentWithDuration(xmlparamter);
		 }
		 
		 public String adminGetAllContent(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetAllContent(" +xmlparamter + ")");
			String mp = groupdao.getAllContent(xmlparamter);
			return mp;
		 }
		 
		 public String adminGetAllContentMedia(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetAllContentMedia(" +xmlparamter + ")");
			String mp = groupdao.getAllContentMedia(xmlparamter);
			return mp;
		 }
		 
		 public String adminGetFullConFig(){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetFullConfig");
			 String c = groupdao.admingetConfig();
			 return c;
		 }
		 
		 public String pushContentIntoSTBAuto(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.pushContentIntoSTBAuto(" +xmlparamter + ")");
			 String result = groupdao.pushContentIntoSTBAuto(xmlparamter);
			 return result;
		 }
		 
		 public String adminAddSubjectContent(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminAddSubjectContent(" +xmlparamter + ")");
			 String result = groupdao.adminAddSubjectContent(xmlparamter);
			 return result;
		 }
		 
		 public String adminGetAllSubjectContent(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetAllSubjectContent(" +xmlparamter + ")");
			 String result = groupdao.adminGetAllSubjectContent(xmlparamter);
			 return result;
		 }
		 
		 public String adminUpdateSubjectContent(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateSubjectContent(" +xmlparamter + ")");
			 String result = groupdao.adminUpdateSubjectContent(xmlparamter);
			 return result;
		 }
		 
		 public String adminDeleteSubjectContent(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDeleteSubjectContent(" +xmlparamter + ")");
			 String result = groupdao.adminDeleteSubjectContent(xmlparamter);
			 return result;
		 }
		 
		 public String abopDeleteContentFromSTB(String xmlparamter){
			 logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopDeleteContentFromSTB(" +xmlparamter + ")");
			 String result = ContentDao.abopDeleteContentFromSTB(xmlparamter);
			 return result;
		 }
		 
		 public String adminDeleteAllContentAllSTBInGroup(String xmlparamter){
			  logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDeleteAllContentAllSTBInGroup(" +xmlparamter + ")");
			 String result = groupdao.adminDeleteAllContentAllSTBInGroup(xmlparamter);
			 return result;
		 }
		 
		public String adminAddSTBIntoGroup(String xmlparamter) {
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminAddSTBIntoGroup(" + xmlparamter + ")");
			String result = groupdao.adminAddSTBIntoGroup(xmlparamter);
			return result;
		}
		 
		public String adminGetPlaylistByGroupId(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetPlaylistByGroupId(" + xmlparamter + ")");
			String result = groupdao.adminGetPlaylistByGroupId(xmlparamter);
			return result;
		}
		
		public String adminAddNewContentMedia(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminAddNewContentMedia(" + xmlparamter + ")");
			String result = groupdao.adminAddNewContentMedia(xmlparamter);
			return result;
		}
		
		public String adminRemoveSTBOutToGroup(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminRemoveSTBOutToGroup(" + xmlparamter + ")");
			String result = groupdao.adminRemoveSTBOutToGroup(xmlparamter);
			return result;
		}
		
		public String adminAddNewSlideContent(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminAddNewSlideContent(" + xmlparamter + ")");
			String result = groupdao.adminAddNewSlideContent(xmlparamter);
			return result;
		}
		
		public String adminDeleteSlideContent(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminDeleteSlideContent(" + xmlparamter + ")");
			String result = groupdao.adminDeleteSlideContent(xmlparamter);
			return result;
		}
		
		public String adminGetContentIDByForSlide(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminGetContentIDByForSlide(" + xmlparamter + ")");
			String result = groupdao.adminGetContentIDByForSlide(xmlparamter);
			return result;
		}
		
		public String adminUpdateSlideContent(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.adminUpdateSlideContent(" + xmlparamter + ")");
			String result = groupdao.adminUpdateSlideContent(xmlparamter);
			return result;
		}
		public String abopSetMonitoring(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopSetMonitoring("+xmlparamter+")");
			String i = contentdao.abopSetMonitoring(xmlparamter);		  
			return i;	
		}
		public String abopGetAllBox(String xmlParamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In abopGetAllBox("+xmlParamter+")");
			String i = userDAO.abopGetAllBox(xmlParamter);		  
			return i;	
		}
		public String abopGetUser(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopGetUser("+xmlparamter+")");
			String i = userDAO.abopGetUser(xmlparamter);		  
			return i;	
		}
		public String abopGetListUser(String xmlparamter)
		{
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopGetListUser("+xmlparamter+")");
			String i = userDAO.abopGetListUser(xmlparamter);		  
			return i;	
		}
		public String abopCreateUser(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopCreateUser("+xmlparamter+")");
			String i = userDAO.abopCreateUser(xmlparamter);		  
			return i;	
		}
		public String abopUpdateUser(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopUpdateUser("+xmlparamter+")");
			String i = userDAO.abopUpdateUser(xmlparamter);		  
			return i;	
		}
		public String abopAddStbUser(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopAddStbUser("+xmlparamter+")");
			String i = userDAO.abopAddStbUser(xmlparamter);		  
			return i;	
		}
		public String abopDeleteUser(String xmlparamter){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopDeleteUser("+xmlparamter+")");
			String i = userDAO.abopDeleteUser(xmlparamter);		  
			return i;	
		}
		public String abopGetRole(){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopGetRole()");
			String i = userDAO.abopGetRole();		  
			return i;	
		}
		public String abopCheckUser(String username){
			logger.info("Ip: " + DateHelper.getIpRemote() + " - In AdcenterAdminService.abopCheckUser("+username+")");
			String i = userDAO.abopCheckUser(username);		  
			return i;	
		}
		public static void main(String[] args) throws Exception{
			AdcenterAdminService a = new AdcenterAdminService();
			String xml = "<parameter><creator>elcom</creator></parameter>";
			System.out.println(a.abopGetListUser(xml));
		}
		
		
}
