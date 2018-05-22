package com.elcom.adcenter.rvcadv.util;

import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;

import javax.xml.xpath.XPathConstants;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.elcom.adcenter.rvcadv.common.SQL;
import com.elcom.adcenter.rvcadv.common.VoContentGroup;
import com.elcom.adcenter.rvcadv.common.VoItem;
import com.elcom.adcenter.rvcadv.common.VoStbSession;
import com.elcom.adcenter.rvcadv.sql.SqlCore;
import com.elcom.adcenter.rvcav.content.ServiceContentDao;

//import ehotel.core.FTPServerStruct;

public class XmlAnalysis {
	
	private static Logger log = Logger.getLogger(XmlAnalysis.class);
	public static Vector<VoContentGroup> getListContentGroup(String xml)
	{
		Vector<VoContentGroup> vecstb = new Vector<VoContentGroup>();
		VoContentGroup item;
		int contentid,groupid;
		
		XPathReader reader = new XPathReader(xml);
		
		NodeList nodes = (NodeList) reader.read("/content/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();					
			groupid = Integer.parseInt(nodelist.item(1).getTextContent());
			contentid = Integer.parseInt(nodelist.item(3).getTextContent());
			
			item = new VoContentGroup();
			item.setContentid(contentid);
			item.setGroupid(groupid);
			
			vecstb.add(item);
		}
		return vecstb;
	}
	//----------------------------------------------------------------------
		public static Vector<VoItem> getListStbPush(String xml,String pathfilename)
		{
			Vector<VoItem> vec = new Vector<VoItem>();
			VoItem item;
			String layoutid,groupid;
			int ii = DateHelper.createFile(pathfilename, xml);
			if (ii == 0) return null;
			XPathReader reader = new XPathReader(pathfilename);
			
			NodeList nodes = (NodeList) reader.read("/parameter/item",
					XPathConstants.NODESET);
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node workflow = (Node) nodes.item(i);
				NodeList nodelist = workflow.getChildNodes();	
				
				layoutid = nodelist.item(1).getTextContent();
				groupid = nodelist.item(3).getTextContent();
				
				
				item = new VoItem();
				item.setP0(layoutid);
				item.setP1(groupid);			
				
				vec.add(item);
			}
			return vec;
		}
	//----------------------------------------------------------------------
	public static Vector<VoItem> getListSchedulePeri(String xml,String pathfilename)
	{
		Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String scheduleid,dailynameid;
		int ii = DateHelper.createFile(pathfilename, xml);
		if (ii == 0) return null;
		XPathReader reader = new XPathReader(pathfilename);
		
		NodeList nodes = (NodeList) reader.read("/Scheduleperio/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();	
			
			dailynameid = nodelist.item(1).getTextContent();
			scheduleid = nodelist.item(3).getTextContent();
			
			
			item = new VoItem();
			item.setP0(dailynameid);
			item.setP1(scheduleid);			
			
			vec.add(item);
		}
		return vec;
	}
	//------------------------------------------------------------------------
	public static Vector<VoItem> adminUpdateScheduleDailyTime(String xml,String pathfilename)
	{
		Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String scheduleitemid,starttime,stoptime;
		int ii = DateHelper.createFile(pathfilename, xml);
		if (ii == 0) return null;
		XPathReader reader = new XPathReader(pathfilename);
		
		NodeList nodes = (NodeList) reader.read("/paramater/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();	
			
			scheduleitemid = nodelist.item(1).getTextContent();
			starttime = nodelist.item(3).getTextContent();
			stoptime = nodelist.item(3).getTextContent();
			
			
			item = new VoItem();
			item.setP0(scheduleitemid);
			item.setP1(starttime);
			item.setP2(stoptime);
			
			vec.add(item);
		}
		return vec;
	}
	
	//----------------------------------------------------------------------
	public static Vector<VoItem> getListPlayListTime(String xml)
	{
		Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String playlistid,starttime,stoptime;
		XPathReader reader = new XPathReader(xml);
		NodeList nodes = (NodeList) reader.read("/parameter/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();					
			playlistid = nodelist.item(1).getTextContent();
			log.info("Node id:" + playlistid);
			starttime = nodelist.item(3).getTextContent();	
			log.info("Node start time:" + starttime);
			stoptime = nodelist.item(5).getTextContent();
			log.info("Node stop time:" + stoptime);
			
			item = new VoItem();
			item.setP0(playlistid);
			//item.setP1(layoutid);
			item.setP1(starttime);
			item.setP2(stoptime);

			vec.add(item);
		}
		return vec;
	}
	
	//----------------------------------------------------------------------
	public static Vector<VoItem> getListPlayListItem(String xml)
	{
		Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String playlistid,starttime,stoptime, name;
		XPathReader reader = new XPathReader(xml);
		NodeList nodes = (NodeList) reader.read("/parameter/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();					
			playlistid = nodelist.item(1).getTextContent();
			log.info("Node id:" + playlistid);
			
			starttime = nodelist.item(3).getTextContent();	
			log.info("Node start time:" + starttime);
			stoptime = nodelist.item(5).getTextContent();
			log.info("Node stop time:" + stoptime);
			name = nodelist.item(7).getTextContent();	
			log.info("Node name:" + name);
			
			item = new VoItem();
			item.setP0(playlistid);
			item.setP1(starttime);
			item.setP2(stoptime);
			item.setP3(name);

			vec.add(item);
		}
		return vec;
	}
	//----------------------------------------------------------------------
		public static Vector<VoItem> getListPlayListItemDel(String xml,String pathfilename)
		{
			Vector<VoItem> vec = new Vector<VoItem>();
			VoItem item;
			String playlistitemid;
			int ii = DateHelper.createFile(pathfilename, xml);
			if (ii == 0) return null;
			XPathReader reader = new XPathReader(pathfilename);
			
			NodeList nodes = (NodeList) reader.read("/parameter/item",
					XPathConstants.NODESET);
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node workflow = (Node) nodes.item(i);
				NodeList nodelist = workflow.getChildNodes();	
				
				playlistitemid = nodelist.item(1).getTextContent();				
				
				item = new VoItem();
				item.setP0(playlistitemid);
				
				
				vec.add(item);
			}
			return vec;
		}
	//--------------------------------------------------------------------------
	public static Vector<VoItem> getListScheduleDaily(String xml)
	{
		/*Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String scheduleid,dailynameid;
		int ii = DateHelper.createFile(pathfilename, xml);
		if (ii == 0) return null;
		XPathReader reader = new XPathReader(pathfilename);
		
		NodeList nodes = (NodeList) reader.read("/Scheduleperio/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();	
			
			dailynameid = nodelist.item(1).getTextContent();
			scheduleid = nodelist.item(3).getTextContent();
			
			
			item = new VoItem();
			item.setP0(dailynameid);
			item.setP1(scheduleid);			
			
			vec.add(item);
		}
		return vec;*/
		Vector<VoItem> vec = new Vector<VoItem>();
		VoItem item;
		String playlistId,dailynameid,namesdaily,starttime,stoptime;		
		
		XPathReader reader = new XPathReader(xml);
		
		NodeList nodes = (NodeList) reader.read("/Scheduledaily/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();	
			
			dailynameid = nodelist.item(1).getTextContent();
			log.info("dailynameid:" + dailynameid);
			playlistId = nodelist.item(3).getTextContent();
			log.info("playlistId:" + playlistId);
			namesdaily = nodelist.item(5).getTextContent();
			log.info("namesdaily:" + namesdaily);
			starttime = nodelist.item(7).getTextContent();
			log.info("starttime:" + starttime);
			stoptime = nodelist.item(9).getTextContent();
			log.info("stoptime:" + stoptime);
			item = new VoItem();
			item.setP0(dailynameid);
			item.setP1(playlistId);
			item.setP2(namesdaily);
			item.setP3(starttime);
			item.setP4(stoptime);
			
			vec.add(item);
		}
		return vec;
	}
	//--------------------------------------------------------------------------
	//--------------------------------------------------------------------------
		public static Vector<VoItem> getListLayoutUpdate(String xml)
		{
			Vector<VoItem> vec = new Vector<VoItem>();
			VoItem item;
			String layoutname, layoutid,xcoor,ycoor,width,height;			
			XPathReader reader = new XPathReader(xml);
			log.info("data:" + xml);
			NodeList nodes = (NodeList) reader.read("/parameter/item",
					XPathConstants.NODESET);
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node workflow = (Node) nodes.item(i);
				NodeList nodelist = workflow.getChildNodes();	
				layoutname = nodelist.item(1).getTextContent();
				layoutid = nodelist.item(3).getTextContent();
				xcoor = nodelist.item(5).getTextContent();
				ycoor = nodelist.item(7).getTextContent();
				width = nodelist.item(9).getTextContent();
				height = nodelist.item(11).getTextContent();
				
				item = new VoItem();
				item.setP0(layoutname);
				item.setP1(layoutid);
				item.setP2(xcoor);
				item.setP3(ycoor);
				item.setP4(width);
				item.setP5(height);
				log.info("xml name:" + layoutname);
				log.info("xml id:" + layoutid);
				log.info("xml x:" + xcoor);
				log.info("xml y:" + ycoor);
				log.info("xml w:" + width);
				log.info("xml h:" + height);
				vec.add(item);
			}
			return vec;
		}
	//--------------------------------------------------------------------------
	public static Vector<VoStbSession> getListStb(String xml)
	{
		Vector<VoStbSession> vecstb = new Vector<VoStbSession>();
		VoStbSession item;
		String serinumber,mac,session,savelocalfilemedia,savelocalfilestb,srcfiles;
		Vector<String> arrsrcfile = new Vector<String>();
		Vector<Integer> arrsrcfileid = new Vector<Integer>();
		
		XPathReader reader = new XPathReader(xml);
		
		NodeList nodes = (NodeList) reader.read("/Stb/item",
				XPathConstants.NODESET);
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList nodelist = workflow.getChildNodes();					
			serinumber = nodelist.item(1).getTextContent();
			mac = nodelist.item(3).getTextContent();
			session = nodelist.item(5).getTextContent();
			savelocalfilemedia = nodelist.item(7).getTextContent();
			savelocalfilestb = nodelist.item(9).getTextContent();
			srcfiles = nodelist.item(13).getTextContent();
			
			
			//Lay id cua content
			System.out.println("Lay id cua tung content");
			StringTokenizer st2 = new StringTokenizer(srcfiles,",");
			arrsrcfileid = new Vector<Integer>();
			arrsrcfile = new Vector<String>();
			while(st2.hasMoreTokens())
			{
				Integer id = new Integer(st2.nextToken());				
				arrsrcfileid.add(id);			
				
				String name = SqlCore.getListContent(id+"");				
				arrsrcfile.add(name);
			}
			
			/*//Lay ten cua content
			StringTokenizer st1 = new StringTokenizer(srcfiless,";");
			arrsrcfile = new Vector<String>();
			while(st1.hasMoreTokens())
			{
				String name = st1.nextToken();	
				System.out.print("name: " +name + " ");
				arrsrcfile.add(name);				
			}*/
			
			item = new VoStbSession();
			item.setMac(mac);
			item.setSerial_num(serinumber);
			item.setSesionid(session);
			item.setDesc_file(savelocalfilemedia);
			item.setSrcfiles(arrsrcfile);
			item.setIdsrcfile(arrsrcfileid);
			
			vecstb.add(item);
			
		}
		
		return  vecstb;
	}
	
}