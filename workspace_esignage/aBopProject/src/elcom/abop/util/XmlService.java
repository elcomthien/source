package elcom.abop.util;

import java.util.ArrayList;
import java.util.Map;

import com.elcom.eod.util.UnicodeConverter;
import com.opensymphony.xwork2.ActionContext;

import elcom.abop.bean.ObjectABOP;
import elcom.abop.common.ApplyItemConstant;
import elcom.abop.common.Constant;
import elcom.abop.util.UtilBasic;

public class XmlService {

	public static String changeNodeName(String xmlstring) {
		return xmlstring.replaceAll("Group", "root").replaceAll("<name>", "<content><name>").replaceAll("</name>", "</name></content>");
	}

	public static String jsTreeRootNode() {
		return "<root>" + "<item id='0' state='closed'>" + "<content><name>Root</name></content>" + "</item>" + "</root>";
	}

	// cai tien ham get Tree 17.9
	public static String jsTreeRootNode(String value) {
		return "<root>" + "<item id='" + value + "' state='closed'>" + "<content><name>Root</name></content>" + "</item>" + "</root>";
	}

	// cai tien ham get Tree 17.9
	public static String jsTreeRoot(String rel) {
		return "<root>" + "<item id='0' state='closed' rel='" + rel + "'>" + "<content><name>Root</name></content>" + "</item>" + "</root>";
	}

	public static String createLayoutXml(String layoutName, String layoutId) {
		String xStr = "<Layout></Layout>";
		String strResult = xStr.replaceAll("<Layout>", "<Layout name=\'" + layoutName + "\' id=\'" + layoutId + "\'>\r\n");
		return strResult;
	}

	public static String createItemLayout(String xStr, String itemName, String groupId, String type, String desc, String x, String y,
			String w, String h, String order) {

		String strResult = xStr.replaceAll("</Layout>", "<item>\r\n<name>" + itemName + "</name>\r\n</Layout>")
				.replaceAll("</Layout>", "<groupid>" + groupId + "</groupid>\r\n</Layout>")
				.replaceAll("</Layout>", "<type>" + type + "</type>\r\n</Layout>")
				.replaceAll("</Layout>", "<desc>" + desc + "</desc>\r\n</Layout>").replaceAll("</Layout>", "<x>" + x + "</x>\r\n</Layout>")
				.replaceAll("</Layout>", "<y>" + y + "</y>\r\n</Layout>").replaceAll("</Layout>", "<width>" + w + "</width>\r\n</Layout>")
				.replaceAll("</Layout>", "<height>" + h + "</height>\r\n</Layout>")
				.replaceAll("</Layout>", "<order>" + order + "</order>\r\n</item>\r\n</Layout>");
		return strResult;
	}

	public static String createGroupXml(String groupId, String groupName, String creator, String parentCreator) {
		String xStr = "<group><parentgroupid></parentgroupid><groupname></groupname><creator></creator><parentcreator></parentcreator></group>";
		String strResult = xStr.replaceAll("<parentgroupid></parentgroupid>", "<parentgroupid>" + groupId + "</parentgroupid>")
				.replaceAll("<groupname></groupname>", "<groupname>" + groupName + "</groupname>")
				.replaceAll("<creator></creator>", "<creator>" + creator + "</creator>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + parentCreator + "</parentcreator>");
		return strResult;
	}

	public static String addStbIntoGroupXml(String groupId, String stbId) {
		String xStr = "<parameter><groupid></groupid><liststb></liststb></parameter>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>").replaceAll("<liststb></liststb>",
				"<liststb>" + stbId + "</liststb>");
		return strResult;
	}

	public static String renameLayout(String layoutId, String layoutName, String size) {
		String xStr = "<parameter>\r\n<layoutnameid></layoutnameid>\r\n<layoutname></layoutname>\r\n<size_screen></size_screen>\r\n</parameter>";
		String strResult = xStr.replaceAll("<layoutnameid></layoutnameid>", "<layoutnameid>" + layoutId + "</layoutnameid>")
				.replaceAll("<layoutname></layoutname>", "<layoutname>" + layoutName + "</layoutname>")
				.replaceAll("<size_screen></size_screen>", "<size_screen>" + size + "</size_screen>");
		return strResult;
	}

	public static String deleteLayoutXml(String layoutId, String layoutName) {
		String xStr = "<parameter>\r\n<layoutnameid></layoutnameid>\r\n<layoutname></layoutname>\r\n</parameter>";
		String strResult = xStr.replaceAll("<layoutnameid></layoutnameid>", "<layoutnameid>" + layoutId + "</layoutnameid>").replaceAll(
				"<layoutname></layoutname>", "<layoutname>" + layoutName + "</layoutname>");
		return strResult;
	}

	/**
	 * 
	 * @param groupId
	 * @param creator
	 * @return <group><groupid></groupid><creator></creator></group>
	 */
	public static String getLayoutFromGroup(String groupId, String creator, String parentCreator) {
		String xStr = "<group>\r\n<groupid></groupid>\r\n<creator></creator>\r\n<parentcreator></parentcreator>" + "</group>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>")
		.replaceAll("<creator></creator>","<creator>" + creator + "</creator>")
		.replaceAll("<parentcreator></parentcreator>","<parentcreator>" + parentCreator + "</parentcreator>");
		return strResult;
	}

	public static String getLayoutNameType(String type, String creator, String parentCreator) {
		String xStr = "<group>\r\n<typelayout></typelayout>\r\n<creator></creator>\r\n<parentcreator></parentcreator>" + "</group>";
		String strResult = xStr.replaceAll("<typelayout></typelayout>", "<typelayout>" + type + "</typelayout>")
		.replaceAll("<creator></creator>", "<creator>" + creator + "</creator>")
		.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + parentCreator + "</parentcreator>");
		return strResult;
	}

	public static String getAllGroup() {
		return "<group><groupid>-2</groupid><namegroup>elcome</namegroup> </group>";
	}

	public static String getGroupByUser(String creator, String parentCreator) {
		return "<group><creator>" + creator + "</creator>"
				+"<parentcreator>"
				+ parentCreator
				+ "</parentcreator><namegroup>Root</namegroup></group>";
	}

	public static String getItemFromGroupAndLayout(String groupId, String layoutId, String layoutName) {
		String xStr = "<group>\r\n<groupid></groupid>\r\n<" + TextObj.GROUP_NAME + "></" + TextObj.GROUP_NAME
				+ ">\r\n<layoutnameid></layoutnameid>\r\n</group>";
		String strResult = xStr
				.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>")
				.replaceAll("<" + TextObj.GROUP_NAME + "></" + TextObj.GROUP_NAME + ">",
						"<" + TextObj.GROUP_NAME + ">" + layoutName + "</" + TextObj.GROUP_NAME + ">")
				.replaceAll("<layoutnameid></layoutnameid>", "<layoutnameid>" + layoutId + "</layoutnameid>");
		return strResult;
	}

	public static String getAllStb() {
		return "<group><groupid>id</groupid><creator>user</creator></group>";
	}

	public static String getStbList(String groupId, String username, String parentCreator) {
		return "<group><groupid>"+groupId+"</groupid><creator>" +username+"</creator><parentcreator>" +parentCreator+"</parentcreator></group>";
	}

	public static String deleteGroup(String groupId, String groupName) {
		String xStr = "<group>" + "<groupid></groupid>" + TextObj.GROUP_NAME + ">" + "</" + TextObj.GROUP_NAME + "></group>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>").replaceAll(
				"<" + TextObj.GROUP_NAME + "></" + TextObj.GROUP_NAME + ">",
				"<" + TextObj.GROUP_NAME + ">" + groupName + "</" + TextObj.GROUP_NAME + ">");
		return strResult;
	}

	public static String renameGroup(String groupId, String groupName) {
		String xStr = "<group>\r\n<groupid></groupid>\r\n<" + TextObj.GROUP_NAME + "></" + TextObj.GROUP_NAME + ">\r\n</group>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>").replaceAll(
				"<" + TextObj.GROUP_NAME + "></" + TextObj.GROUP_NAME + ">",
				"<" + TextObj.GROUP_NAME + ">" + groupName + "</" + TextObj.GROUP_NAME + ">");
		return strResult;
	}

	public static String createXmlForPlaylistItem(String playlistId) {
		String xStr = "<playlist>\r\n<playlistid></playlistid>\r\n</playlist>";
		String strResult = xStr.replaceAll("<playlistid></playlistid>", "<playlistid>" + playlistId + "</playlistid>");
		return strResult;
	}

	public static String createXmlForContentGroup(String groupId, String contentId) {
		String xStr = "<content>\r\n<item>\r\n<groupid></groupid>\r\n<contentid></contentid>\r\n</item>\r\n</content>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>").replaceAll(
				"<contentid></contentid>", "<contentid>" + contentId + "</contentid>");
		return strResult;
	}

	public static String admingetScheduleDaily(String username, String pcreator) {
		String xStr = "<parameter>\r\n<item>\r\n<creator></creator>\r\n<parentcreator></parentcreator>\r\n</item>\r\n</parameter>";
		String strResult = xStr.replaceAll("<creator></creator>", "<creator>" + username + "</creator>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>");
		return strResult;
	}

	public static String admingetSchedulePeriodic(String groupId) {
		String xStr = "<parameter>\r\n<item>\r\n<groupid></groupid>\r\n</item>\r\n</parameter>";
		String strResult = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>");
		return strResult;
	}

	/*
	 * output: <paramter> <groupid></groupid> <layoutname>layoutname</layoutname> </paramter>
	 */
	public static String createLayoutName(String groupId, String layoutName, String direction, String size, String creator, String pcreator) {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String xStr = "<paramter>\r\n<groupid></groupid>\r\n<layoutname></layoutname>\r\n<creator></creator>\r\n<direction></direction>\r\n<size_screen></size_screen>\r\n<parentcreator></parentcreator>\r\n</paramter>";
		String result = xStr.replaceAll("<layoutname></layoutname>", "<layoutname>" + layoutName + "</layoutname>")
				.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>")
				.replaceAll("<creator></creator>", "<creator>" + creator + "</creator>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>")
				.replaceAll("<direction></direction>", "<direction>" + direction + "</direction>")
				.replaceAll("<size_screen></size_screen>", "<size_screen>" + size + "</size_screen>");
		return result;
	}

	public static String admingetContentStb(String stbId) {
		String xStr = "<parameter>\r\n<stbid></stbid>\r\n</parameter>";
		String strResult = xStr.replaceAll("<stbid></stbid>", "<stbid>" + stbId + "</stbid>");
		return strResult;
	}

	/*
	 * output <Stb> <item> <serinumber>2001</serinumber> <mac>00:00:01</mac> <session>341ae790-47b5-953e-4a38-86816ea9d7e7</session>
	 * <savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia> <savelocalfilestb>/mnt/flash/huuln</savelocalfilestb>
	 * <srcfileid>32,33,34</srcfileid> </item> </Stb>
	 */
	public static String xmlPushStbEach(String groupId, String namedaily, String desc) {
		String xStr = "<parameter>\r\n<item>\r\n<groupid></groupid>\r\n<namedailyin></namedailyin>\r\n<desc></desc>\r\n</item>\r\n</parameter>";
		String result = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>")
				.replaceAll("<namedailyin></namedailyin>", "<namedailyin>" + namedaily + "</namedailyin>")
				.replaceAll("<desc></desc>", "<desc>" + desc + "</desc>");
		return result;
	}

	/*
	 * output: <parameter> <item> <dailynameid></dailynameid> </item> </parameter>
	 */
	public static String xmlDailyScheduleItem(String dailyId) {
		String xStr = "<parameter>\r\n<item>\r\n<dailynameid></dailynameid>\r\n</item>\r\n</parameter>";
		String result = xStr.replaceAll("<dailynameid></dailynameid>", "<dailynameid>" + dailyId + "</dailynameid>");
		return result;
	}

	/*
	 * output <parameter> <item> <groupid></groupid> <namedaily></namedaily> <desc></desc> </item> </parameter>
	 */
	public static String xmlDailyScheduleName(String username, String pcreator, String namedaily, String desc) {
		String xStr = "<parameter>\r\n<item>\r\n<groupid></groupid>\r\n<namedailyin></namedailyin>\r\n<desc></desc>\r\n<creator></creator>\r\n<parentcreator></parentcreator></item>\r\n</parameter>";
		String result = xStr.replaceAll("<groupid></groupid>", "<groupid>-1</groupid>")
				.replaceAll("<namedailyin></namedailyin>", "<namedailyin>" + namedaily + "</namedailyin>")
				.replaceAll("<desc></desc>", "<desc>" + desc + "</desc>")
				.replaceAll("<creator></creator>", "<creator>" + username + "</creator>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>");
		return result;
	}

	/*
	 * output <parameter> <item> <dailynameid></dailynameid> </item> </parameter>
	 */
	public static String xmlRemoveDailyName(String dailynameid) {
		String xStr = "<parameter>\r\n<item>\r\n<dailynameid></dailynameid>\r\n</item>\r\n</parameter>";
		String result = xStr.replaceAll("<dailynameid></dailynameid>", "<dailynameid>" + dailynameid + "</dailynameid>");
		return result;
	}

	public static String xmlScheduleDaily(String dailyId, ArrayList<ObjectABOP> abop) {
		int length = abop.size();
		String parent = "<Scheduledaily></Scheduledaily>";
		String item = "<item>\r\n<dailynameid></dailynameid>\r\n<playlistid></playlistid>\r\n<namesdaily></namesdaily>"
				+ "\r\n<starttime></starttime>\r\n<stoptime></stoptime>\r\n</item>";
		String items = "";
		for (int i = 0; i < length; i++) {
			items = items
					+ item.replaceAll("<dailynameid></dailynameid>", "<dailynameid>" + dailyId + "</dailynameid>")
							.replaceAll("<playlistid></playlistid>", "<playlistid>" + abop.get(i).getId() + "</playlistid>")
							.replaceAll("<namesdaily></namesdaily>", "<namesdaily>" + abop.get(i).getName() + "</namesdaily>")
							.replaceAll("<starttime></starttime>", "<starttime>" + abop.get(i).getStartTime() + "</starttime>")
							.replaceAll("<stoptime></stoptime>", "<stoptime>" + abop.get(i).getEndTime() + "</stoptime>");
		}
		parent = parent.replaceAll("<Scheduledaily></Scheduledaily>", "<Scheduledaily>\r\n" + items + "\r\n</Scheduledaily>");
		return parent;
	}

	public static String xmlAdminNewScheduleDaily(String source, String xml) {
		String result = source.replaceAll("</Scheduledaily>", xml);
		return result;
	}

	public static String xmlCloseAdminNewScheduleDaily(String xml) {
		String result = "<Scheduledaily>\r\n" + xml;
		return result;
	}

	/*
	 * output <Playlist> <item> <groupid></groupid> <nameplaylist></nameplaylist> <desc></desc> </item> </Playlist>
	 */
	public static String xmladminNewPlaylistName(String groupid, String nameplaylist, String desc, String username, String parentCreator) {
		String xStr = "<Playlist>\r\n<item>\r\n<groupid></groupid>\r\n<nameplaylist></nameplaylist>\r\n<desc></desc>\r\n<creator></creator>\r\n<parentcreator></parentcreator></item>\r\n</Playlist>";
		String result = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupid + "</groupid>")
				.replaceAll("<nameplaylist></nameplaylist>", "<nameplaylist>" + nameplaylist + "</nameplaylist>")
				.replaceAll("<desc></desc>", "<desc>" + desc + "</desc>")
				.replaceAll("<creator></creator>", "<creator>" + username + "</creator>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + parentCreator + "</parentcreator>");
		return result;
	}

	/*
	 * input <paramter> <scheduleid>1</scheduleid> </paramter>
	 */
	public static String xmladmingetItemScheduleDailyForPerio(String scheduleid) {
		String xStr = "<paramter>\r\n<scheduleid></scheduleid>\r\n</paramter>";
		String result = xStr.replaceAll("<scheduleid></scheduleid>", "<scheduleid>" + scheduleid + "</scheduleid>");
		return result;
	}

	/*
	 * input <layoutcontent> <playlistitemid></playlistitemid> <contentid></contentid> <layoutid></layoutid> </layoutcontent>
	 */
	public static String xmladminNewLayoutContent(String playlistitemid, String contentid, String layoutid) {
		String xStr = "<layoutcontent>\r\n<playlistitemid></playlistitemid>\r\n<contentid></contentid>\r\n<layoutid></layoutid>\r\n</layoutcontent>";
		String result = xStr.replaceAll("<playlistitemid></playlistitemid>", "<playlistitemid>" + playlistitemid + "</playlistitemid>")
				.replaceAll("<contentid></contentid>", "<contentid>" + contentid + "</contentid>")
				.replaceAll("<layoutid></layoutid>", "<layoutid>" + layoutid + "</layoutid>");
		return result;
	}

	/*
	 * output: <playlistitem> <item> <layoutitemid></layoutitemid> <contentid></contentid> <name></name> <starttime></starttime>
	 * <stoptime></stoptime> </item> </playlistitem>
	 */
	public static String adminCreatePlaylist(int playlistId, ArrayList<ObjectABOP> abop) {
		String xStr = "<playlistitem>\r\n</playlistitem>";
		String item = "";
		for (int i = 0; i < abop.size(); i++) {
			item = xmlSubItemPlaylist(playlistId, abop.get(i).getIdSubLayout(), abop.get(i).getId(), abop.get(i).getName(), abop.get(i)
					.getStartTime(), abop.get(i).getEndTime());
			xStr = xStr.replaceAll("</playlistitem>", item);
		}
		return xStr;
	}

	public static String xmlSubItemPlaylist(int playlistId, String layoutId, String idContent, String layoutName, String starttime,
			String stoptime) {
		String xStr = "<item>\r\n<playlistid></playlistid>\r\n<layoutitemid></layoutitemid>\r\n" + "<contentid></contentid>\r\n"
				+ "<name></name>\r\n<starttime></starttime>\r\n" + "<stoptime></stoptime>\r\n</item>\r\n</playlistitem>";
		String result = xStr.replaceAll("<playlistid></playlistid>", "<playlistid>" + playlistId + "</playlistid>")
				.replaceAll("<layoutitemid></layoutitemid>", "<layoutitemid>" + layoutId + "</layoutitemid>")
				.replaceAll("<contentid></contentid>", "<contentid>" + idContent + "</contentid>")
				.replaceAll("<name></name>", "<name>" + layoutName + "</name>")
				.replaceAll("<starttime></starttime>", "<starttime>" + starttime + "</starttime>")
				.replaceAll("<stoptime></stoptime>", "<stoptime>" + stoptime + "</stoptime>");
		return result;
	}

	public static String xmlMoveGroup(String groupId, String groupmoveid) {
		String xStr = "<parameter>\r\n<groupid></groupid>\r\n<groupmoveid></groupmoveid>\r\n</parameter>";
		String result = xStr.replaceAll("<groupid></groupid>", "<groupid>" + groupId + "</groupid>").replaceAll(
				"<groupmoveid></groupmoveid>", "<groupmoveid>" + groupmoveid + "</groupmoveid>");
		return result;
	}

	public static String xmladminUpdatePlayListName(String playlistId, String playlistName, String desc) {
		String xStr = "<parameter>\r\n<playlistid></playlistid>\r\n<nameplaylist></nameplaylist>\r\n<desc></desc>\r\n</parameter>";
		String result = xStr.replaceAll("<playlistid></playlistid>", "<playlistid>" + playlistId + "</playlistid>")
				.replaceAll("<nameplaylist></nameplaylist>", "<nameplaylist>" + playlistName + "</nameplaylist>")
				.replaceAll("<desc></desc>", "<desc>" + desc + "</desc>");
		return result;
	}

	/*
	 * output <parameter><item><layoutid></layoutid><xcoor></xcoor><ycoor></ycoor ><width></width><height></height></item></parameter>
	 */
	public static String adminUpdateLayout(ArrayList<ObjectABOP> abop) {
		String xStr = "<parameter>\r\n</parameter>";
		String item = "";
		int length = abop.size();
		for (int i = 0; i < length; i++) {
			item = itemAdminUpdateLayout(abop.get(i).getName(), abop.get(i).getId(), abop.get(i).getLeft(), abop.get(i).getTop(),
					abop.get(i).getWidth(), abop.get(i).getHeight());
			xStr = xStr.replaceAll("</parameter>", item);
		}
		return xStr;
	}

	private static String itemAdminUpdateLayout(String nameItem, String layoutItemId, String xCoor, String yCoor, String w, String h) {
		String xStr = "<item>\r\n<namelayout></namelayout>\r\n<layoutid></layoutid>\r\n<xcoor></xcoor>\r\n"
				+ "<ycoor></ycoor>\r\n<width></width>\r\n<height></height>\r\n" + "</item>\r\n</parameter>";
		String result = xStr.replaceAll("<namelayout></namelayout>", "<namelayout>" + nameItem + "</namelayout>")
				.replaceAll("<layoutid></layoutid>", "<layoutid>" + layoutItemId + "</layoutid>")
				.replaceAll("<xcoor></xcoor>", "<xcoor>" + xCoor + "</xcoor>")
				.replaceAll("<ycoor></ycoor>", "<ycoor>" + yCoor + "</ycoor>").replaceAll("<width></width>", "<width>" + w + "</width>")
				.replaceAll("<height></height>", "<height>" + h + "</height>");
		return result;
	}

	/*
	 * output <parameter><item><playlistitemid></playlistitemid><startdate></startdate ><stopdate></stopdate><item></parameter>
	 */
	public static String xmladminUpdatePlaylistTime(ArrayList<ObjectABOP> abop) {
		String xStr = "<parameter>\r\n</parameter>";
		String item = "";
		int size = abop.size();
		for (int i = 0; i < size; i++) {
			item = xmlItemadminUpdatePlaylistTime(abop.get(i).getIdItem(), abop.get(i).getStartTime(), abop.get(i).getEndTime());
			xStr = xStr.replaceAll("</parameter>", item);
		}
		return xStr;
	}

	public static String xmlItemadminUpdatePlaylistTime(String playlistItemId, String start, String stop) {
		String xStr = "<item>\r\n<playlistitemid></playlistitemid>\r\n<startdate></startdate>\r\n" + "<stopdate></stopdate>\r\n"
				+ "</item>\r\n</parameter>";
		String result = xStr.replaceAll("<playlistitemid></playlistitemid>", "<playlistitemid>" + playlistItemId + "</playlistitemid>")
				.replaceAll("<startdate></startdate>", "<startdate>" + start + "</startdate>")
				.replaceAll("<stopdate></stopdate>", "<stopdate>" + stop + "</stopdate>");
		return result;
	}

	/*
	 * output <stb><stbid>0</stbid><name>haha</name><urlserver></urlserver><savelocalmedia
	 * ></savelocalmedia><savelocalfile></savelocalfile></stb>
	 */
	public static String xmladminUPdateStb(String stbId, String stbName, String ip) {
		String xStr = "<stb>\r\n<stbid></stbid>\r\n<name></name>\r\n<urlserver></urlserver>\r\n<savelocalmedia>kobiet</savelocalmedia>\r\n<savelocalfile>kobiet</savelocalfile>\r\n</stb>";
		String result = xStr.replaceAll("<stbid></stbid>", "<stbid>" + stbId + "</stbid>")
				.replaceAll("<name></name>", "<name>" + stbName + "</name>")
				.replaceAll("<urlserver></urlserver>", "<urlserver>" + ip + "</urlserver>");
		return result;
	}

	/*
	 * output: <Scheduleperio><item><groupid></groupid><nameschedule></nameschedule
	 * ><startdate></startdate><stopdate></stopdate></item></Scheduleperio >
	 */
	public static String xmladminNewSchedulePeri(String group, String name, String start, String stop) {
		String xStr = "<Scheduleperio>\r\n<item>\r\n<groupid></groupid>\r\n<nameschedule></nameschedule>\r\n<startdate></startdate>\r\n<stopdate></stopdate>\r\n</item>\r\n</Scheduleperio>";
		String result = xStr.replaceAll("<groupid></groupid>", "<groupid>" + group + "</groupid>")
				.replaceAll("<nameschedule></nameschedule>", "<nameschedule>" + name + "</nameschedule>")
				.replaceAll("<startdate></startdate>", "<startdate>" + start + "</startdate>")
				.replaceAll("<stopdate></stopdate>", "<stopdate>" + stop + "</stopdate>");
		return result;
	}

	/*
	 * output <Scheduleperio><item><dailynameid></dailyhnameid><scheduleid></scheduleid ></item></Scheduleperio>
	 */
	public static String xmladminNewSchedulePeriItem(String dailynameid, String scheduleid) {
		String xStr = "<Scheduleperio>\r\n<item>\r\n<dailynameid></dailynameid>\r\n<scheduleid></scheduleid>\r\n"
				+ "</item>\r\n</Scheduleperio>";

		String result = xStr.replaceAll("<dailynameid></dailynameid>", "<dailynameid>" + dailynameid + "</dailynameid>").replaceAll(
				"<scheduleid></scheduleid>", "<scheduleid>" + scheduleid + "</scheduleid>");
		return result;
	}

	public static String xmlItemadminNewSchedulePeri(String dailynameid, String scheduleid) {
		String xStr = "<item>\r\n<dailynameid></dailynameid>\r\n<scheduleid></scheduleid>\r\n" + "</item>\r\n</Scheduleperio>";
		String result = xStr.replaceAll("<dailynameid></dailynameid>", "<dailynameid>" + dailynameid + "</dailynameid>").replaceAll(
				"<scheduleid></scheduleid>", "<scheduleid>" + scheduleid + "</scheduleid>");
		return result;
	}

	/*
	 * output <parameter><layoutid>1</layoutid></parameter>
	 */
	public static String xmladminDelLayOutItem(String layoutId) {
		String xStr = "<parameter>\r\n<layoutid></layoutid>\r\n</parameter>";
		String result = xStr.replaceAll("<layoutid></layoutid>", "<layoutid>" + layoutId + "</layoutid>");
		return result;
	}

	/**
	 * <paramater> <item> <scheduleid></scheduleid> <namedailyin></namedailyin></item> </paramater> </item> </paramater>
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public static String adminUpdateScheduleDailyName(String id, String name, String desc) {
		String item = "<paramater> <item> <scheduleid>" + id + "</scheduleid> <namedailyin>" + name + "</namedailyin><desc>" + desc
				+ "</desc></item> </paramater>";
		return item;
	}

	/*
	 * output <parameter><item><scheduleitemid></scheduleitemid><starttime></starttime ><stoptime></stoptime></item></parameter>
	 */
	public static String xmladminUpdateScheduleDailyTime(ArrayList<ObjectABOP> abop) {
		String xStr = "<parameter>\r\n</parameter>";
		String item = "";
		for (int i = 0; i < abop.size(); i++) {
			item = xmlItemadminUpdateScheduleDailyTime(abop.get(i).getIdItem(), abop.get(i).getStartTime(), abop.get(i).getEndTime(), abop.get(i).getName());
			xStr = xStr.replaceAll("</parameter>", item);
		}
		return xStr;
	}

	public static String xmlItemadminUpdateScheduleDailyTime(String scheduleitemid, String starttime, String stoptime, String name) {
		String xStr = "<item>\r\n<scheduleitemid></scheduleitemid>\r\n<starttime></starttime>\r\n<stoptime></stoptime>\r\n<name></name>\r\n"
				+ "</item>\r\n</parameter>";
		String result = xStr.replaceAll("<scheduleitemid></scheduleitemid>", "<scheduleitemid>" + scheduleitemid + "</scheduleitemid>")
				.replaceAll("<starttime></starttime>", "<starttime>" + starttime + "</starttime>")
				.replaceAll("<stoptime></stoptime>", "<stoptime>" + stoptime + "</stoptime>")
				.replaceAll("<name></name>", "<name>" + name + "</name>");
		return result;
	}

	/*
	 * output adminDelSchduledailyTime <parameter><scheduleitemid></scheduleitemid></parameter>
	 */
	public static String xmladminDelSchduledailyTime(String scheduleitemid) {
		String xStr = "<parameter>\r\n<scheduleitemid></scheduleitemid>\r\n</parameter>";
		String result = xStr.replaceAll("<scheduleitemid></scheduleitemid>", "<scheduleitemid>" + scheduleitemid + "</scheduleitemid>");
		return result;
	}

	/*
	 * output <parameter><id_Content></id_Content></parameter>
	 */
	public static String xmladminDelContentText(String contentId) {
		String temp = "<parameter>\r\n<id_Content></id_Content>\r\n</parameter>";
		String result = temp.replaceAll("<id_Content></id_Content>", "<id_Content>" + contentId + "</id_Content>");
		return result;
	}

	/*
	 * input <font color="yellow" face="courier" size="4">dsadasdasdas</font> output
	 * <parameter><id_Content></id_Content><nameContent></nameContent ><urlContent
	 * ></urlContent><colorText></colorText><fontText></fontText><sizeText ></sizeText><direcRoll></direcRoll></parameter>
	 */
	public static String xmladminUpdateContentText(String name, String url, String font, String size, String color, String contentId) {
		String temp = "<parameter>\r\n<id_Content></id_Content>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>5</typeContent>\r\n</parameter>";
		// String lam = xStr.substring(0, xStr.indexOf(">"));
		// String color = "", font = "", size = "", url = "";
		// String[] lamArr = lam.split(" ");
		// for (int i = 0; i < lamArr.length; i++) {
		// if (lamArr[i].indexOf("size") >= 0) {
		// size = lamArr[i].substring(6, lamArr[i].length() - 1);
		// System.out.println("size=" + size);
		// }
		// if (lamArr[i].indexOf("color") >= 0) {
		// color = lamArr[i].substring(7, lamArr[i].length() - 1);
		// color = color.replaceAll("#", "");
		// System.out.println("color=" + color);
		// }
		// if (lamArr[i].indexOf("face") >= 0) {
		// font = lamArr[i].substring(6, lamArr[i].length() - 1);
		// System.out.println("face=" + font);
		// }
		// }
		// url = xStr.substring(xStr.lastIndexOf("\">") + 2, xStr.indexOf("</font>"));
		url = UnicodeConverter.encodeUnicode(url);
		String direct = "1";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<colorText></colorText>", "<colorText>#" + color + "</colorText>")
				.replaceAll("<fontText></fontText>", "<fontText>" + font + "</fontText>")
				.replaceAll("<sizeText></sizeText>", "<sizeText>" + size + "</sizeText>")
				.replaceAll("<direcRoll></direcRoll>", "<direcRoll>" + direct + "</direcRoll>")
				.replaceAll("<id_Content></id_Content>", "<id_Content>" + contentId + "</id_Content>");
		return result;
	}

	public static String xmladminUpdateContentWeb(String url, String contentId, String name) {
		String temp = "<parameter>\r\n<id_Content></id_Content>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>6</typeContent>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<id_Content></id_Content>", "<id_Content>" + contentId + "</id_Content>");
		return result;
	}

	public static String xmladminNewContentWeb(String url, String name) {
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText>12</sizeText>\r\n<direcRoll>1</direcRoll>\r\n<typeContent>6</typeContent>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>").replaceAll(
				"<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>");
		return result;
	}

	// co chu de
	public static String xmladminNewContentWebSubject(String url, String name, String idsubject, String username, String pcreator) {
		// name = UtilBasic.encodeURIComponent(name);
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<subjectContent></subjectContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText>12</sizeText>\r\n<direcRoll>1</direcRoll>\r\n<typeContent>6</typeContent>\r\n<username></username>\r\n<parentcreator></parentcreator>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<subjectContent></subjectContent>", "<subjectContent>" + idsubject + "</subjectContent>")
				.replaceAll("<username></username>", "<username>" + username + "</username>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>");
		return result;
	}

	public static String xmladminUpdateContentBg(String url, String contentId, String name) {
		String temp = "<parameter>\r\n<id_Content></id_Content>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>7</typeContent>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<id_Content></id_Content>", "<id_Content>" + contentId + "</id_Content>");
		return result;
	}

	public static String xmladminNewContentBg(String idsubject, String url, String name, String username, String pcreator) {
		name = UtilBasic.encodeURIComponent(name);
		String temp = "<parameter>\r\n<subjectContent></subjectContent>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>7</typeContent>\r\n<username></username>\r\n<parentcreator></parentcreator>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<subjectContent></subjectContent>", "<subjectContent>" + idsubject + "</subjectContent>")
				.replaceAll("<username></username>", "<username>" + username + "</username>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>");
		return result;
	}

	/*
	 * output //public static String adminStbPush(String xmlparamter) dang lam o day
	 * <parameter><item><stbid></stbid><groupid></groupid></item></parameter>
	 */
	public static String xmladminStbPushAll(String stbid, String groupid) {
		String temp = "<parameter>\r\n<item>\r\n<stbid></stbid>\r\n<groupid></groupid>\r\n</item>\r\n</parameter>";
		String result = temp.replaceAll("<stbid></stbid>", "<stbid>" + stbid + "</stbid>").replaceAll("<groupid></groupid>",
				"<groupid>" + groupid + "</groupid>");
		return result;
	}

	public static String xmladminStbPush(String stbid, String groupid) {
		String xStr = "<parameter>\r\n</parameter>";
		String item = "";
		String[] temp = stbid.split(",");
		for (int i = 0; i < temp.length; i++) {
			item = xmlItemadminStbPush(temp[i], groupid);
			xStr = xStr.replaceAll("</parameter>", item);
		}
		return xStr;
	}

	public static String xmlItemadminStbPush(String stbid, String groupid) {
		String xStr = "<item>\r\n<stbid></stbid>\r\n<groupid></groupid>\r\n" + "</item>\r\n</parameter>";
		String result = xStr.replaceAll("<stbid></stbid>", "<stbid>" + stbid + "</stbid>").replaceAll("<groupid></groupid>",
				"<groupid>" + groupid + "</groupid>");
		return result;
	}

	/*
	 * output <parameter><layoutid></layoutid></parameter>
	 */
	public static String xmladmingetLayoutContainContent(String layoutId) {
		String xStr = "<parameter>\r\n<layoutid></layoutid>\r\n</parameter>";
		String result = xStr.replaceAll("<layoutid></layoutid>", "<layoutid>" + layoutId + "</layoutid>");
		return result;
	}

	/*
	 * output <parameter><item><playlistitemid></playlistitemid><item></parameter>
	 */
	public static String xmladminDelItemPlaylist(String playlistitemid) {
		String xml = "<parameter>\r\n<item>\r\n<playlistitemid></playlistitemid>\r\n</item>\r\n</parameter>";
		String result = xml.replaceAll("<playlistitemid></playlistitemid>", "<playlistitemid>" + playlistitemid + "</playlistitemid>");
		return result;
	}

	/*
	 * Dung cho ca 2 ham la del peri va del peri item output <parameter><scheduleid></scheduleid></parameter>
	 */
	public static String xmladminDelSchedulePeri(String id) {
		String xml = "<parameter>\r\n<scheduleid></scheduleid>\r\n</parameter>";
		String result = xml.replaceAll("<scheduleid></scheduleid>", "<scheduleid>" + id + "</scheduleid>");
		return result;
	}

	/*
	 * output public static String adminUpdateSchedulePeri(String xmlparam) <parameter><
	 * scheduleid></scheduleid><nameschedule></nameschedule><stopdate ></stopdate><startdate></startdate></parameter>
	 */
	public static String xmladminUpdateSchedulePeri(String scheduleid,String time, String nameschedule, String stopdate, String startdate) {
		String xml = "<parameter>\r\n<scheduleid></scheduleid>\r\n<nameschedule></nameschedule>\r\n<stopdate></stopdate>\r\n<startdate></startdate>\r\n</parameter>";
		String result = xml.replaceAll("<scheduleid></scheduleid>", "<scheduleid>" + scheduleid + "</scheduleid>")
				.replaceAll("<nameschedule></nameschedule>", "<nameschedule>" + nameschedule + "</nameschedule>")
				.replaceAll("<time></time>", "<time>" + time + "</time>")
				.replaceAll("<stopdate></stopdate>", "<stopdate>" + stopdate + "</stopdate>")
				.replaceAll("<startdate></startdate>", "<startdate>" + startdate + "</startdate>");
		return result;
	}

	/*
	 * output public static String adminDelPlaylist(String xmlparamter) <parameter><playlistid></playlistid></parameter>
	 */
	public static String xmladminDelPlaylist(String playlistid) {
		String xml = "<parameter>\r\n<playlistid></playlistid>\r\n</parameter>";
		String result = xml.replaceAll("<playlistid></playlistid>", "<playlistid>" + playlistid + "</playlistid>");
		return result;
	}

	/*
	 * output public static String adminNewContent(String xmlparam) <parameter><nameContent
	 * ></nameContent><urlContent></urlContent><typeContent ></typeContent></parameter>
	 */
	public static String xmladminNewContent(String nameContent, String urlContent, String typeContent) {
		String xml = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<typeContent></typeContent>\r\n</parameter>";
		String result = xml.replaceAll("<nameContent></nameContent>", "<nameContent>" + nameContent + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + urlContent + "</urlContent>")
				.replaceAll("<typeContent></typeContent>", "<typeContent>" + typeContent + "</typeContent>");
		return result;
	}

	/*
	 * output public static String adminNewContent(String xmlparam) <parameter><nameContent
	 * ></nameContent><urlContent></urlContent><typeContent ></typeContent></parameter>
	 */
	public static String xmladminNewContentWithDuration(String nameContent, String urlContent, String typeContent, String duration) {
		String xml = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<typeContent></typeContent>\r\n<lengthContent></lengthContent>\r\n</parameter>";
		String result = xml.replaceAll("<nameContent></nameContent>", "<nameContent>" + nameContent + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + urlContent + "</urlContent>")
				.replaceAll("<typeContent></typeContent>", "<typeContent>" + typeContent + "</typeContent>")
				.replaceAll("<lengthContent></lengthContent>", "<lengthContent>" + duration + "</lengthContent>");
		return result;
	}

	/*
	 * output //public static String admingetPlayListLayout(String xmlparamter) <parameter><playlistid>12</playlistid></parameter>
	 */
	public static String xmladmingetPlayListLayout(String playlistid) {
		String xml = "<parameter>\r\n<playlistid></playlistid>\r\n</parameter>";
		String result = xml.replaceAll("<playlistid></playlistid>", "<playlistid>" + playlistid + "</playlistid>");
		return result;
	}

	/*
	 * output public void adminUpdateConfig(String xmlparamter) <Config> <server_ftp>localhost</server_ftp> <server_port>21</server_port>
	 * <src_file>Src</src_file> <local_file>D:\tmp</local_file> <desc_host>localhost</desc_host> <serveruserftp>adcenter</serveruserftp>
	 * <serverpassftp>adcenter</serverpassftp> </Config>
	 */
	// <<<<<<< .mine
	public static String xmladminUpdateConfig(String server_ftp, String server_port, String src_file, String local_file, String desc_host,
	// =======
	// public String xmladminUpdateConfig(String server_ftp, String server_port,
	// String src_file, String local_file, String desc_host,
	// >>>>>>> .r1081
			String serveruserftp, String serverpassftp) {
		String xml = "<Config>\r\n<server_ftp></server_ftp>\r\n<server_port></server_port>\r\n<src_file></src_file>\r\n<local_file></local_file>\r\n<desc_host></desc_host>\r\n<serveruserftp></serveruserftp>\r\n<serverpassftp></serverpassftp>\r\n</Config>";
		String result = xml.replaceAll("<server_ftp></server_ftp>", "<server_ftp>" + server_ftp + "</server_ftp>")
				.replaceAll("<server_port></server_port>", "<server_port>" + server_port + "</server_port>")
				.replaceAll("<local_file></local_file>", "<local_file>" + local_file + "</local_file>")
				.replaceAll("<desc_host></desc_host>", "<desc_host>" + desc_host + "</desc_host>")
				.replaceAll("<serveruserftp></serveruserftp>", "<serveruserftp>" + serveruserftp + "</serveruserftp>")
				.replaceAll("<serverpassftp></serverpassftp>", "<serverpassftp>" + serverpassftp + "</serverpassftp>")
				.replaceAll("<src_file></src_file>", "<src_file>" + src_file + "</src_file>");
		return result;
	}

	/*
	 * public static String adminUpdateDirecGroup(String xmlparamter) landscape = 1, portrait = 2
	 * <parameter><groupid></groupid></direction><direction></parameter>
	 */
	public static String xmladminUpdateDirecGroup(String groupid, String direction) {
		String xml = "<parameter>\r\n<groupid></groupid>\r\n<direction></direction>\r\n</parameter>";
		String result = xml.replaceAll("<groupid></groupid>", "<groupid>" + groupid + "</groupid>").replaceAll("<direction></direction>",
				"<direction>" + direction + "</direction>");
		return result;
	}

	/*
	 * public static String adminCheckStb(String xmlparamter) <parameter><stbid>0</stbid></parameter>
	 */
	public static String xmladminCheckStb(String stbid) {
		String xml = "<parameter>\r\n<stbid></stbid>\r\n</parameter>";
		String result = xml.replaceAll("<stbid></stbid>", "<stbid>" + stbid + "</stbid>");
		return result;
	}

	/*
	 * output //public static String adminDelStb(String xmlparamter) <parammeter><stbid>123</stbid></parammeter>
	 */
	public static String xmladminDelStb(String stbid) {
		String xml = "<parameter>\r\n<stbid></stbid>\r\n</parameter>";
		String result = xml.replaceAll("<stbid></stbid>", "<stbid>" + stbid + "</stbid>");
		return result;
	}

	/*
	 * search content 3.10 public static String adminReportContentAll(String xmlparamter) <parameter><groupname>-1</groupname></parameter>
	 */
	public static String xmladminReportContentAll(String groupname) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>");
		return result;
	}

	/*
	 * search layout 3.10 public static String adminReportLayout(String xmlparamter) <parameter
	 * ><groupname>-1</groupname><createdatestart>-1</createdatestart>< createdatestop></createdatestop></parameter>
	 */
	public static String xmladminReportLayout(String groupname, String createdatestart, String createdatestop) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n<createdatestart></createdatestart>\r\n<createdatestop></createdatestop>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>")
				.replaceAll("<createdatestart></createdatestart>", "<createdatestart>" + createdatestart + "</createdatestart>")
				.replaceAll("<createdatestop></createdatestop>", "<createdatestop>" + createdatestop + "</createdatestop>");
		return result;
	}

	/*
	 * search playlist 5.10 public static String adminReportPlaylist(String xmlparam) <parameter
	 * ><groupname></groupname><createdatestart></createdatestart ><createdatestop ></createdatestop></parameter>
	 */
	public static String xmladminReportPlaylist(String groupname, String createdatestart, String createdatestop) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n<createdatestart></createdatestart>\r\n<createdatestop></createdatestop>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>")
				.replaceAll("<createdatestart></createdatestart>", "<createdatestart>" + createdatestart + "</createdatestart>")
				.replaceAll("<createdatestop></createdatestop>", "<createdatestop>" + createdatestop + "</createdatestop>");
		return result;
	}

	/*
	 * search daily 3.10 public static String adminReportDaily(String xmlparamter) <parameter
	 * ><groupname>-1</groupname><createdatestart>-1</createdatestart>< createdatestop></createdatestop></parameter>
	 */
	public static String xmladminReportDaily(String groupname, String createdatestart, String createdatestop) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n<createdatestart></createdatestart>\r\n<createdatestop></createdatestop>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>")
				.replaceAll("<createdatestart></createdatestart>", "<createdatestart>" + createdatestart + "</createdatestart>")
				.replaceAll("<createdatestop></createdatestop>", "<createdatestop>" + createdatestop + "</createdatestop>");
		return result;
	}

	/*
	 * search peri 9.10 public static String adminReportSchedulePeriod(String xmlparamter) <parameter><groupname>-1</groupname></parameter>
	 */
	public static String xmladminReportSchedulePeriod(String groupname) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>");
		return result;
	}

	/*
	 * search stb 9.10 public static String adminReportStb(String xmlparamter) <parameter><groupname>-1</groupname></parameter>
	 */
	public static String xmladminReportStb(String groupname) {
		String xml = "<parameter>\r\n<groupname></groupname>\r\n</parameter>";
		String result = xml.replaceAll("<groupname></groupname>", "<groupname>" + groupname + "</groupname>");
		return result;
	}

	// new ad_content with typeContent = 8
	public static String xmladminNewContentSlide(String url, String name) {
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>8</typeContent>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>").replaceAll(
				"<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>");
		return result;
	}

	// new ad_content with typeContent = 9
	public static String xmladminNewVideoContentSlide(String url, String name) {
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>9</typeContent>\r\n</parameter>";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>").replaceAll(
				"<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>");
		return result;
	}

	/*
	 * push each 16.11 output: <Stb> <item> <serinumber>2001</serinumber> <mac>00:00:01</mac>
	 * <session>341ae790-47b5-953e-4a38-86816ea9d7e7</session> <savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia>
	 * <savelocalfilestb>/mnt/flash/huuln</savelocalfilestb> <req>32,33,34</req> <srcfileid>32,33,34</srcfileid> </item> </Stb>
	 */
	public static String xmlPushEach(String serinumber, String mac, String session, String srcfileid, String req) {
		String xStr = "<Stb>\r\n<item>\r\n<serinumber></serinumber>\r\n<mac></mac>\r\n<session></session>\r\n<savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia>\r\n<savelocalfilestb>/mnt/flash/huuln</savelocalfilestb>\r\n<req></req>\r\n<srcfileid></srcfileid>\r\n</item>\r\n</Stb>";
		String result = xStr.replaceAll("<serinumber></serinumber>", "<serinumber>" + serinumber + "</serinumber>")
				.replaceAll("<mac></mac>", "<mac>" + mac + "</mac>").replaceAll("<req></req>", "<req>" + req + "</req>")
				.replaceAll("<session></session>", "<session>" + session + "</session>")
				.replaceAll("<srcfileid></srcfileid>", "<srcfileid>" + srcfileid + "</srcfileid>");
		return result;
	}

	/*
	 * del each 09052013 output: <Stb> <item> <serinumber>2001</serinumber> <mac>00:00:01</mac>
	 * <session>341ae790-47b5-953e-4a38-86816ea9d7e7</session> <savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia>
	 * <savelocalfilestb>/mnt/flash/huuln</savelocalfilestb> <req>32,33,34</req> <srcfileid>32,33,34</srcfileid> </item> </Stb>
	 */
	public static String xmlDelEach(String serinumber, String mac, String session, String srcfileid, String req) {
		String xStr = "<Stb>\r\n<item>\r\n<serinumber></serinumber>\r\n<mac></mac>\r\n<session></session>\r\n<savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia>\r\n<savelocalfilestb>/mnt/flash/huuln</savelocalfilestb>\r\n<req></req>\r\n<srcfileid></srcfileid>\r\n</item>\r\n</Stb>";
		String result = xStr.replaceAll("<serinumber></serinumber>", "<serinumber>" + serinumber + "</serinumber>")
				.replaceAll("<mac></mac>", "<mac>" + mac + "</mac>").replaceAll("<req></req>", "<req>" + req + "</req>")
				.replaceAll("<session></session>", "<session>" + session + "</session>")
				.replaceAll("<srcfileid></srcfileid>", "<srcfileid>" + srcfileid + "</srcfileid>");
		return result;
	}

	public static String getStbUnGroup(String username, String parentCreator) {
		return "<group><groupid>-1</groupid><creator>" +username+"</creator><parentcreator>" +parentCreator+"</parentcreator></group>";
	}

	public static String getAllContent(String type, String idsubject, String username, String pcreator) {
		String xml = "<group><typecontent>" + type + "</typecontent><subjectcontent>" + idsubject + "</subjectcontent><creator>" + username
				+ "</creator><parentcreator>" + pcreator + "</parentcreator></group>";
		return xml;
	}

	public static String admingetAllPlaylist() {
		String xml = "<group><groupid>" + "-1" + "</groupid><" + "groupname" + ">khong biet</" + "groupname" + "></group>";
		return xml;
	}

	public static String admingetPlaylistByUser(String creator, String parentCreator) {
		String xml = "<group><creator>" + creator + "</creator><parentcreator>" + parentCreator + "</parentcreator></group>";
		return xml;
	}

	public static String admingetContentByUser(String creator, String parentCreator) {
		String xml = "<group><groupid>-1</groupid><creator>" + creator + "</creator><parentcreator>" + parentCreator + "</parentcreator></group>";
		return xml;
	}

	public static String adminNewContent(String name, String url, String type, 
			String duration, String idsubject, String username, String pcreator) {
		name = UtilBasic.encodeURIComponent(name);
		String xml = " <parameter><nameContent>" + name + "</nameContent><urlContent>" + url + "</urlContent><typeContent>" + type
				+ "</typeContent><durationContent>" + duration + "</durationContent><subjectcontent>" + idsubject
				+ "</subjectcontent><creator>" + username + "</creator><parentcreator>" + pcreator + "</parentcreator></parameter>";
		return xml;
	}

	public static String adminGetAllSubjectContent(String username, String pcreator) {
		String xml = "<parameter><creator>" + username + "</creator><parentcreator>" + pcreator + "</parentcreator>";
		return xml;
	}

	public static String adminGetSubjectByUser(String username, String parentCreator) {
		String xml = "<parameter><creator>" + username + "</creator><parentcreator>" + parentCreator + "</parentcreator></parameter>";
		return xml;
	}

	public static String xmlAdminAddSubjectContent(String name, String description, String username, String pcreator) {
		String xml = "<parameter><namesubject>" + name + "</namesubject><descriptionsubject>" + description
				+ "</descriptionsubject><username>" + username + "</username><parentcreator>" + pcreator + "</parentcreator></parameter>";
		return xml;
	}

	public static String xmlAdminUpdateSubjectContent(String id, String name, String description) {
		String xml = "<parameter><idsubject>" + id + "</idsubject><namesubject>" + name + "</namesubject><descriptionsubject>"
				+ description + "</descriptionsubject></parameter>";
		return xml;
	}

	public static String xmlAdminDeleteSubjectContent(String id) {
		String xml = "<parameter><idsubject>" + id + "</idsubject></parameter>";
		return xml;
	}

	public static String xmlPushContentIntoSTB(String dailyId, String groupId) {
		String xml = "<parameter><dailyid>" + dailyId + "</dailyid><groupid>" + groupId + "</groupid></parameter>";
		return xml;
	}

	public static String abopDeleteContentFromSTB(String idPlayer, String idContent) {
		String xml = "<parameter><idstb>" + idPlayer + "</idstb><idcontent>" + idContent + "</idcontent></parameter>";
		return xml;
	}

	public static String adminDeleteAllContentInSTB(String groupId) {
		String xml = "<parameter><groupid>" + groupId + "</groupid></parameter>";
		return xml;
	}

	public static String xmladminUpdateLayoutCoor(ArrayList<ObjectABOP> objUpdate) {
		String xStr = "<parameter>\r\n</parameter>";
		String item = "";
		int size = objUpdate.size();
		for (int i = 0; i < size; i++) {
			item = xmlItemadminUpdateLayoutCoor(objUpdate.get(i).getName(), objUpdate.get(i).getId(), objUpdate.get(i).getLeft(), objUpdate
					.get(i).getTop(), objUpdate.get(i).getWidth(), objUpdate.get(i).getHeight());
			xStr = xStr.replaceAll("</parameter>", item);
		}
		return xStr;
	}

	private static String xmlItemadminUpdateLayoutCoor(String name, String layoutItemId, String xCoor, String yCoor, String w, String h) {
		String xStr = "<item>\r\n<name></name>\r\n<layoutid></layoutid>\r\n<xcoor></xcoor>\r\n"
				+ "<ycoor></ycoor>\r\n<width></width>\r\n<height></height>\r\n" + "</item>\r\n</parameter>";
		String result = xStr.replaceAll("<layoutid></layoutid>", "<layoutid>" + layoutItemId + "</layoutid>")
				.replaceAll("<xcoor></xcoor>", "<xcoor>" + xCoor + "</xcoor>")
				.replaceAll("<ycoor></ycoor>", "<ycoor>" + yCoor + "</ycoor>").replaceAll("<width></width>", "<width>" + w + "</width>")
				.replaceAll("<height></height>", "<height>" + h + "</height>").replaceAll("<name></name>", "<name>" + name + "</name>");
		return result;
	}

	public static String xmladminNewContentText(String name, String url, String font, String size, String color, String idsubject,
			String username, String pcreator) {
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>5</typeContent>\r\n<subjectContent></subjectContent>\r\n<username></username>\r\n<parentcreator></parentcreator>\r\n</parameter>";
		// String lam = str.substring(0, str.indexOf(">"));
		// color = ""; font = ""; size = "";
		// String[] lamArr = lam.split(" ");
		// for (int i = 0; i < lamArr.length; i++) {
		// if (lamArr[i].indexOf("size") >= 0) {
		// size = lamArr[i].substring(6, lamArr[i].length() - 1);
		// System.out.println("size=" + size);
		// }
		// if (lamArr[i].indexOf("color") >= 0) {
		// color = lamArr[i].substring(7, lamArr[i].length() - 1);
		// color = color.replaceAll("#", "");
		// System.out.println("color=" + color);
		// }
		// if (lamArr[i].indexOf("face") >= 0) {
		// font = lamArr[i].substring(6, lamArr[i].length() - 1);
		// System.out.println("face=" + font);
		// }
		// }
		// String url = str.substring(str.lastIndexOf("\">") + 2, str.indexOf("</font>"));
		// name = UnicodeConverter.encodeUnicode(name);
		// name = UtilBasic.encodeURIComponent(name);
//		url = UtilBasic.encodeURIComponent(url);
		url = UnicodeConverter.encodeUnicode(url);
		// System.out.println(url);
		String direct = "1";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<colorText></colorText>", "<colorText>#" + color + "</colorText>")
				.replaceAll("<fontText></fontText>", "<fontText>" + font + "</fontText>")
				.replaceAll("<sizeText></sizeText>", "<sizeText>" + size + "</sizeText>")
				.replaceAll("<direcRoll></direcRoll>", "<direcRoll>" + direct + "</direcRoll>")
				.replaceAll("<subjectContent></subjectContent>", "<subjectContent>" + idsubject + "</subjectContent>")
				.replaceAll("<username></username>", "<username>" + username + "</username>")
				.replaceAll("<parentcreator></parentcreator>", "<parentcreator>" + pcreator + "</parentcreator>");
		return result;
	}

	public static String adminRemoveSTBOutToGroup(String playerId) {
		String xml = "<parameter><liststb>" + playerId + "</liststb></parameter>";
		return xml;
	}

	public static String adminAddNewSlideContent(String idsubject, String name, 
			String effect, String lstord, String lstcnt,
			String lsttime, String username, String pcreator) {
		String xml = "<parameter><subjectContent>" + idsubject + "</subjectContent><nameContent>" + name + "</nameContent><effectContent>"
				+ effect + "</effectContent>" + "<listOrder>" + lstord + "</listOrder><listContent>" + lstcnt + "</listContent><listTime>"
				+ lsttime + "</listTime><username>" + username + "</username><parentcreator>" + pcreator + "</parentcreator></parameter>";
		return xml;
	}

	public static String abopDeletePlaylistItems(String idPlaylistDelete) {
		String xml = "<parameter><itemplaylistid>" + idPlaylistDelete + "</itemplaylistid></parameter>";
		return xml;
	}

	public static String abopSetMonitoring(String flag, String idstb) {
		String xml = "<parameter><monitoring>" + flag + "</monitoring><idstb>" + idstb + "</idstb></parameter>";
		return xml;
	}

	public static String getUser(String username, String password) {
		String xml = "<parameter><username>" + username + "</username><password>" + password + "</password></parameter>";
		return xml;
	}

	public static String getListUser(String str, String pcreator) {
		String xml = "<parameter><creator>" + str + "</creator><parentcreator>" + pcreator + "</parentcreator></parameter>";
		return xml;
	}
}