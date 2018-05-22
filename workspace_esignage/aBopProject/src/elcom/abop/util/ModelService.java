package elcom.abop.util;

import java.io.IOException;
import java.io.StringReader;
import java.rmi.RemoteException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeletePlaylistItems;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeleteUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopUpdateUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopAddStbUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetRole;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopCheckUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopCreateUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetListUser;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeleteContentFromSTB;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddNewContentMedia;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddNewSlideContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopSetMonitoring;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddSTBIntoGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminRemoveSTBOutToGroup;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddSubjectContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminCheckStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelContentText;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelItemPlaylist;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelLayOutItem;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelLayoutName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelPlaylist;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelSchduledailyTime;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelScheduleDailyName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelSchedulePeri;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelSchedulePeriItem;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteContentIntoGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteSlideContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteSubjectContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllContentMedia;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllSubjectContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetContentIDByForSlide;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInserContentIntoGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertLayout;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertLayoutName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertPlaylistCore;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminMoveGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewContentLogic;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewContentText;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewContentWithDuration;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewLayoutContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewPlaylistName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewScheduleDaily;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewScheduleDailyName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewSchedulePeri;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewSchedulePeriName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminRemoveStbGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportContentAll;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportDaily;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportLayout;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportPlaylist;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportSchedulePeriod;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminStbPush;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUdateStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateConfig;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateContentText;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateDirecGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateLayoutCoor;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateLayoutName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdatePlayListName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdatePlaylistTime;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateScheduleDailyTime;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateSchedulePeri;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateSlideContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateSubjectContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetConfig;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetContentNoGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetContentStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetItemScheduleDailyForPerio;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutContainContent;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutName;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutNameType;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListGroups;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListStbAlls;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayList;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayListGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayListLayout;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetScheduleDailyGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetScheduleDailyNamGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetSchedulePeriGroup;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteAllContentAllSTBInGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeleteContentFromSTB;

import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.GetContentGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.GetStbGroup;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.PushContentIntoSTBAuto;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.PushContentIntoStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.RemoveContentStb;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.RemoveContentStbEach;
import com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.UpdateScheduleDaily;

import elcom.abop.common.Constant;

public class ModelService {
	/*
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * </group>
	 * 
	 * output: <Group> <item id = '1'> <name>Group01</name>
	 * <typegroup>1</typegroup> <direction>1</direction> </item> <item id = '2'>
	 * <name>Group02</name> <typegroup>1</typegroup> <direction>1</direction>
	 * </item> </Group>
	 */
	public static String admingetGroupList(String xStr) throws RemoteException {
		AdmingetListGroup t = new AdmingetListGroup();
		t.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub().admingetListGroup(t)
				.get_return();
		String result = xmlstring.replaceAll("<item id=",
				"<item state='closed' id=");
		return result;
	}

	public static String admingetListGroups(String xmlparamter)
			throws RemoteException {

		AdmingetListGroups content = new AdmingetListGroups();
		content.setXmlparamter(xmlparamter);
		String xmlstring = Constant.getServiceStub()
				.admingetListGroups(content).get_return();
		String result = xmlstring.replaceAll("<item id=",
				"<item state='closed' id=");
		return result;
	}

	/*
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * </group>
	 * 
	 * output: <Content> <item id = '1'> <name>Content</name>
	 * <duration>1</typegroup> <typecontent>1</direction> </item> </Content>
	 */
	public static String getContentList(String xStr) throws RemoteException {

		GetContentGroup content = new GetContentGroup();
		content.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub().getContentGroup(content)
				.get_return();
		return xmlstring;
	}

	public static String getContentListNoGroup(String xStr)
			throws RemoteException {

		AdmingetContentNoGroup nogroup = new AdmingetContentNoGroup();
		nogroup.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetContentNoGroup(nogroup).get_return();
		return xmlstring;
	}

	// lay ra cac item cua 1 layout chu ko phai lay ra tat ca cac layout
	/*
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * <layoutnameid>123</layoutnameid> </group>
	 */
	public static String getLayout(String xStr) throws RemoteException {

		AdmingetLayoutGroup layout = new AdmingetLayoutGroup();
		layout.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetLayoutGroup(layout).get_return();
		return xmlstring;
	}

	/*
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * </group>
	 * 
	 * output: <Layout> <item id=1> <name>Layout01<name> <date>12/12/1234</date>
	 * </item> <item id=2> <name>Layout02<name> <date>12/12/1234</date> </item>
	 * </Layout>
	 */
	public static String getLayoutName(String xStr) throws RemoteException {
		AdmingetLayoutName layoutName = new AdmingetLayoutName();
		layoutName.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetLayoutName(layoutName).get_return();
		return xmlstring;
	}

	public static String getLayoutNameType(String xStr) throws RemoteException {
		AdmingetLayoutNameType layoutName = new AdmingetLayoutNameType();
		layoutName.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetLayoutNameType(layoutName).get_return();
		return xmlstring;
	}

	/*
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * </group>
	 * 
	 * output: <List> <PlayList id='4' NamePlayList='PlayList02_SJC'
	 * duration='00:46:00'> <item id='61'> <name>Manhinh1_SJCBieuDo_</name>
	 * <starttime>00:00:00</starttime> <stoptime>00:05:00</stoptime>
	 * <status>1</status> </item> </Playlist> </List>
	 */
	public static String admingetAllPlaylist(String xStr)
			throws RemoteException {

		AdmingetPlayListGroup playlist = new AdmingetPlayListGroup();
		playlist.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetPlayListGroup(playlist).get_return();
		return xmlstring;
	}

	public static String admingetPlaylistByUser(String xStr)
			throws RemoteException {

		AdmingetPlayListGroup playlist = new AdmingetPlayListGroup();
		playlist.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetPlayListGroup(playlist).get_return();
		return xmlstring;
	}

	/*
	 * input: <group><groupid>1</groupid><namegroup>elcome</namegroup></group>
	 * input: <group> <groupid>1</groupid> <namegroup>elcome</namegroup>
	 * </group>
	 * 
	 * output: <Stb> <item id='1'> <idgroup>1</idgroup>> <name>Stb01</name>
	 * <serinumber>2001</serinumber> <mac>00:00:01</mac>
	 * <urlserver>http://localhost
	 * :8080/AdcenterService/services/AdcenterService</urlserver>
	 * <savelocalfilemedia>/mnt</savelocalfilemedia>
	 * <savelocalfilestb>/mnt</savelocalfilestb> <status>1</status> </item>
	 * </Stb>
	 */
	public static String getStbList(String xStr) throws RemoteException {

		GetStbGroup stb = new GetStbGroup();
		stb.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub().getStbGroup(stb)
				.get_return();
		return xmlstring;
	}

	/*
	 * input: <parameter><groupid></groupid><stbid></stbid></parameter>
	 */
	public static String addStbIntoGroup(String xStr) throws RemoteException {
		AdminAddSTBIntoGroup stbIntoGroup = new AdminAddSTBIntoGroup();
		stbIntoGroup.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.adminAddSTBIntoGroup(stbIntoGroup).get_return();
		return xmlstring;
	}

	/*
	 * input: <parameter><groupid></groupid><stbid></stbid></parameter>
	 */
	public static String removeStb(String xStr) throws RemoteException {

		AdminRemoveStbGroup removeStb = new AdminRemoveStbGroup();
		removeStb.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub()
				.adminRemoveStbGroup(removeStb).get_return();
		return xmlstring;
	}

	/*
	 * input: <Layout name='Layout01' id='1'> <item> <name>Clip 01</name>
	 * <groupid>1</groupid> <type>1</type> <desc>Video quan cao 01</desc>
	 * <x>180</x> <y>0</y> <width>1100</width> <height>620</height>
	 * <order>1</order> </item> <item> <name>Logo</name> <groupid>1</groupid>
	 * <type>3</type> <desc>Logo quang cao 01</desc> <x>0</x> <y>0</y>
	 * <width>180</width> <height>100</height> <order>2</order> </item>
	 * </Layout>
	 */
	public static int addLayout(String xStr) throws RemoteException {

		AdminInsertLayout insertLayout = new AdminInsertLayout();
		insertLayout.setXmlparamter(xStr);
		int result = Constant.getServiceStub().adminInsertLayout(insertLayout)
				.get_return();
		return result;
	}

	/*
	 * input:
	 * <parameter>\r\n<layoutnameid></layoutnameid>\r\n<layoutname></layoutname
	 * >\r\n</parameter>
	 */
	public static int renameLayout(String xStr) throws RemoteException {

		AdminUpdateLayoutName updateLayout = new AdminUpdateLayoutName();
		updateLayout.setXmlparamter(xStr);
		int result = Constant.getServiceStub()
				.adminUpdateLayoutName(updateLayout).get_return();
		return result;
	}

	/*
	 * input:
	 * <parameter>\r\n<layoutnameid></layoutnameid>\r\n<layoutname></layoutname
	 * >\r\n</parameter>
	 */
	public static int deleteLayout(String xStr) throws RemoteException {

		AdminDelLayoutName delLayout = new AdminDelLayoutName();
		delLayout.setXmlparamter(xStr);
		int result = Constant.getServiceStub().adminDelLayoutName(delLayout)
				.get_return();
		return result;
	}

	// TODO
	/*
	 * input:
	 * <group><parentgroupid></parentgroupid><groupname></groupname></group>
	 */
	public static String addNewGroup(String xStr) throws RemoteException {

		AdminNewGroup newGroup = new AdminNewGroup();
		newGroup.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub().adminNewGroup(newGroup)
				.get_return();
		return xmlstring;
	}

	// lay ra typeLayout
	/*
	 * no input output: <typelayout> <item id='1'> <name>Clip</name>
	 * <status>1</status> </item> <item id='2'> <name>Logo</name>
	 * <status>1</status> </item> </typelayout>
	 */
	// public static String getTypeLayout() throws RemoteException {
	//
	// String xmlstring =
	// Constant.getServiceStub().admingetTypeLayout().get_return();
	// return xmlstring;
	// }

	/*
	 * input <group><groupid></groupid><groupname></groupname></group> output: 1
	 * success 0 unsuccess
	 */
	public int deleteGroup(String xStr) throws RemoteException {

		AdminDelGroup delGroup = new AdminDelGroup();
		delGroup.setXmlparamter(xStr);
		int xmlstring = Constant.getServiceStub().adminDelGroup(delGroup)
				.get_return();
		return xmlstring;
	}

	/*
	 * input <group><groupid></groupid><groupname></groupname></group> output: 1
	 * success 0 unsuccess
	 */
	public static int renameGroup(String xStr) throws RemoteException {

		AdminUpdateGroup renameGroup = new AdminUpdateGroup();
		renameGroup.setXmlparamter(xStr);
		int xmlstring = Constant.getServiceStub().adminUpdateGroup(renameGroup)
				.get_return();
		return xmlstring;
	}

	/*
	 * input <playlist><playlistid></playlistid></playlist>
	 */
	public static String getPlaylistItemFromPlaylist(String xStr)
			throws RemoteException {

		AdmingetPlayList playlist = new AdmingetPlayList();
		playlist.setXmlparamter(xStr);
		String xmlstring = Constant.getServiceStub().admingetPlayList(playlist)
				.get_return();
		return xmlstring;
	}

	/*
	 * input <paramter> <stbid>1</stbid> </paramter>
	 * 
	 * output
	 */
	public static String admingetContentStb(String xStr) throws RemoteException {

		AdmingetContentStb stbContent = new AdmingetContentStb();
		stbContent.setXmlparam(xStr);
		String xmlstring = Constant.getServiceStub()
				.admingetContentStb(stbContent).get_return();
		return xmlstring;
	}

	/*
	 * push content to stb and remove content from stb input <Stb> <item>
	 * <serinumber>2001</serinumber> <mac>00:00:01</mac>
	 * <session>341ae790-47b5-953e-4a38-86816ea9d7e7</session>
	 * <savelocalfilemedia>/mnt/flash/huuln/</savelocalfilemedia>
	 * <savelocalfilestb>/mnt/flash/huuln</savelocalfilestb>
	 * <srcfileid>32,33,34</srcfileid> </item> </Stb>
	 */
	public static void pushXml(String xStr) throws RemoteException {

		PushContentIntoStb pushContent = new PushContentIntoStb();
		pushContent.setXmlparam(xStr);
		Constant.getServiceStub().pushContentIntoStb(pushContent);
	}

	public void removeXml(String xStr) throws RemoteException {

		RemoveContentStb contentRemove = new RemoveContentStb();
		contentRemove.setXmlparam(xStr);
		Constant.getServiceStub().removeContentStb(contentRemove);
	}

	public static void removeXml1(String group, String srcfileid)
			throws RemoteException {

		GetStbGroup stb = new GetStbGroup();
		stb.setXmlparamter(group);
		String xmlstring = Constant.getServiceStub().getStbGroup(stb)
				.get_return();
		System.out.println("============================== \n" + xmlstring
				+ "\n ============================");
		while (xmlstring.indexOf("<name>") >= 0) {
			int s1 = xmlstring.indexOf("<idgroup>");
			String idgroup = "";
			if (s1 >= 0) {
				int e1 = xmlstring.indexOf("</idgroup>") + 10;
				idgroup = xmlstring.substring(s1, e1);
				System.out.println("1 ==== " + idgroup);
			}

			String name = "";
			int s2 = xmlstring.indexOf("<name>");
			if (s2 >= 0) {
				int e2 = xmlstring.indexOf("</name>") + 7;
				name = xmlstring.substring(s2, e2);
				System.out.println("2 ==== " + name);
			}

			String urlserver = "";
			int s3 = xmlstring.indexOf("<urlserver>");
			if (s3 >= 0) {
				int e3 = xmlstring.indexOf("</urlserver>") + 12;
				urlserver = xmlstring.substring(s3, e3);
				System.out.println("3 ==== " + urlserver);
			}

			String statusonof = "";
			int s4 = xmlstring.indexOf("<statusonof>");
			if (s4 >= 0) {
				int e4 = xmlstring.indexOf("</statusonof>") + 13;
				statusonof = xmlstring.substring(s4, e4);
				System.out.println("4 ==== " + statusonof);
			}

			String status = "";
			int s5 = xmlstring.indexOf("<status>");
			if (s5 >= 0) {
				int e5 = xmlstring.indexOf("</status>") + 9;
				status = xmlstring.substring(s5, e5);
				System.out.println("5 ==== " + status);
			}

			String reboot = "";
			int s6 = xmlstring.indexOf("<reboot>");
			if (s6 >= 0) {
				int e6 = xmlstring.indexOf("</reboot>") + 9;
				reboot = xmlstring.substring(s6, e6);
				System.out.println("6 ==== " + reboot);
			}

			xmlstring = xmlstring
					.replace(
							"</item>",
							"<srcfileid>" + srcfileid
									+ "</srcfileid>\r\n</item>")
					.replaceAll(idgroup, "").replaceAll(name, "")
					.replaceAll(urlserver, "").replaceAll(statusonof, "")
					.replaceAll(status, "").replaceAll(reboot, "");
		}
		System.out.println("removeContent= " + xmlstring);
		RemoveContentStb contentRemove = new RemoveContentStb();
		contentRemove.setXmlparam(xmlstring);
		Constant.getServiceStub().removeContentStb(contentRemove);
	}

	public void removeXml2(String group, String srcfileid, String stbname)
			throws RemoteException {

		GetStbGroup stb = new GetStbGroup();
		stb.setXmlparamter(group);
		String xmlstring = Constant.getServiceStub().getStbGroup(stb)
				.get_return();
		System.out.println("============================== \n" + xmlstring
				+ "\n ============================");
		while (xmlstring.indexOf("<name>") >= 0) {
			int s1 = xmlstring.indexOf("<idgroup>");
			String idgroup = "";
			if (s1 >= 0) {
				int e1 = xmlstring.indexOf("</idgroup>") + 10;
				idgroup = xmlstring.substring(s1, e1);
				System.out.println("1 ==== " + idgroup);
			}

			String name = "";
			int s2 = xmlstring.indexOf("<name>");
			if (s2 >= 0) {
				int e2 = xmlstring.indexOf("</name>") + 7;
				name = xmlstring.substring(s2, e2);
				System.out.println("2 ==== " + name);
			}

			String urlserver = "";
			int s3 = xmlstring.indexOf("<urlserver>");
			if (s3 >= 0) {
				int e3 = xmlstring.indexOf("</urlserver>") + 12;
				urlserver = xmlstring.substring(s3, e3);
				System.out.println("3 ==== " + urlserver);
			}

			String statusonof = "";
			int s4 = xmlstring.indexOf("<statusonof>");
			if (s4 >= 0) {
				int e4 = xmlstring.indexOf("</statusonof>") + 13;
				statusonof = xmlstring.substring(s4, e4);
				System.out.println("4 ==== " + statusonof);
			}

			String status = "";
			int s5 = xmlstring.indexOf("<status>");
			if (s5 >= 0) {
				int e5 = xmlstring.indexOf("</status>") + 9;
				status = xmlstring.substring(s5, e5);
				System.out.println("5 ==== " + status);
			}

			String reboot = "";
			int s6 = xmlstring.indexOf("<reboot>");
			if (s6 >= 0) {
				int e6 = xmlstring.indexOf("</reboot>") + 9;
				reboot = xmlstring.substring(s6, e6);
				System.out.println("6 ==== " + reboot);
			}

			xmlstring = xmlstring
					.replace(
							"</item>",
							"<srcfileid>" + srcfileid
									+ "</srcfileid>\r\n</item>")
					.replaceAll(idgroup, "").replaceAll(name, "")
					.replaceAll(urlserver, "").replaceAll(statusonof, "")
					.replaceAll(status, "").replaceAll(reboot, "");
		}
		System.out.println("removeContent= " + xmlstring);
		RemoveContentStbEach contentRemove = new RemoveContentStbEach();
		contentRemove.setXmlparam(xmlstring);
		Constant.getServiceStub().removeContentStbEach(contentRemove);
	}

	private static Document convertStringToDocument(String xmlStr)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
		return doc;
	}

	/*
	 * add and remove content into group input:
	 * <content>\r\n<item>\r\n<groupid></groupid>\r\n<contentid
	 * ></contentid>\r\n</item>\r\n</content>
	 */
	public static int addContentIntoGroup(String xStr) throws RemoteException {

		AdminInserContentIntoGroup content = new AdminInserContentIntoGroup();
		content.setXmlparam(xStr);
		int result = Constant.getServiceStub()
				.adminInserContentIntoGroup(content).get_return();
		return result;
	}

	public static int removeContentIntoGroup(String xStr)
			throws RemoteException {

		AdminDeleteContentIntoGroup content = new AdminDeleteContentIntoGroup();
		content.setXmlparam(xStr);
		int result = Constant.getServiceStub()
				.adminDeleteContentIntoGroup(content).get_return();
		return result;
	}

	/*
	 * admingetScheduleDailyNameGroup input:
	 * <paramter><groupid>1</groupid></paramter> admingetScheduleDailyGroup
	 */
	public static String admingetScheduleDaily(String xStr)
			throws RemoteException {

		AdmingetScheduleDailyNamGroup content = new AdmingetScheduleDailyNamGroup();
		content.setXmlparam(xStr);
		String result = Constant.getServiceStub()
				.admingetScheduleDailyNamGroup(content).get_return();
		return result;
	}

	/*
	 * admingetScheduleDailyItem input:
	 * <paramater><item><dailynameid></dailynameid></item></paramater>
	 * admingetScheduleDailyItem
	 */
	public static String admingetScheduleDailyItem(String xStr)
			throws RemoteException {

		AdmingetScheduleDailyGroup content = new AdmingetScheduleDailyGroup();
		content.setXmlparam(xStr);
		String result = Constant.getServiceStub()
				.admingetScheduleDailyGroup(content).get_return();
		return result;
	}

	public static String admingetSchedulePeriodic(String xStr)
			throws RemoteException {

		AdmingetSchedulePeriGroup content = new AdmingetSchedulePeriGroup();
		content.setXmlparam(xStr);
		String result = Constant.getServiceStub()
				.admingetSchedulePeriGroup(content).get_return();
		return result;
	}

	/*
	 * new layout content input <paramter> <groupid></groupid>
	 * <layoutname>layoutname</layoutname> </paramter>
	 */
	public static String adminInsertLayoutName(String xStr)
			throws RemoteException {

		AdminInsertLayoutName content = new AdminInsertLayoutName();
		content.setXmlparamter(xStr);
		String result = Constant.getServiceStub()
				.adminInsertLayoutName(content).get_return();
		return result;
	}

	/*
	 * input <parameter> <item> <groupid></groupid> <namedaily></namedaily>
	 * <desc></desc> </item> </parameter>
	 */
	public static String adminNewScheduleDailyName(String xStr)
			throws RemoteException {

		AdminNewScheduleDailyName content = new AdminNewScheduleDailyName();
		content.setXmlparam(xStr);
		return Constant.getServiceStub().adminNewScheduleDailyName(content)
				.get_return();
	}

	/*
	 * input <parameter> <item> <dailynameid></dailynameid> </item> </parameter>
	 */
	public static void adminDelScheduleDailyName(String xStr)
			throws RemoteException {

		AdminDelScheduleDailyName content = new AdminDelScheduleDailyName();
		content.setXmlparam(xStr);
		Constant.getServiceStub().adminDelScheduleDailyName(content);
	}

	public static String adminNewScheduleDaily(String xStr)
			throws RemoteException {

		AdminNewScheduleDaily content = new AdminNewScheduleDaily();
		content.setXmlparam(xStr);
		return Constant.getServiceStub().adminNewScheduleDaily(content).get_return();
	}

	/*
	 * input <Playlist> <item> <groupid></groupid> <nameplaylist></nameplaylist>
	 * <desc></desc> </item> </Playlist>
	 */
	public static int adminNewPlaylistName(String xStr) throws RemoteException {

		AdminNewPlaylistName content = new AdminNewPlaylistName();
		content.setXmlparamter(xStr);
		int id = Constant.getServiceStub().adminNewPlaylistName(content)
				.get_return();
		return id;
	}

	/*
	 * input: <paramter> <scheduleid>1</scheduleid> </paramter> output:
	 * <Scheduledaily> <item id='1'> <name>Schduledaily01</name>
	 * <createdate>2012-07-21 16:32:23</createdate> </item> <item id='2'>
	 * <name>Schduledaily02</name> <createdate>2012-07-23 14:14:09</createdate>
	 * </item> <item id='3'> <name>100</name> <createdate>2012-07-24
	 * 13:36:04</createdate> </item> </Scheduledaily>
	 */
	public static String admingetItemScheduleDailyForPerio(String xStr)
			throws RemoteException {

		AdmingetItemScheduleDailyForPerio content = new AdmingetItemScheduleDailyForPerio();
		content.setXmlparam(xStr);
		return Constant.getServiceStub()
				.admingetItemScheduleDailyForPerio(content).get_return();
	}

	/*
	 * input: <layoutcontent> <playlistitemid></playlistitemid>
	 * <contentid></contentid> <layoutid></layoutid> </layoutcontent>
	 */
	public static String adminNewLayoutContent(String xStr)
			throws RemoteException {

		AdminNewLayoutContent content = new AdminNewLayoutContent();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminNewLayoutContent(content)
				.get_return();
	}

	/*
	 * input: <playlistitem> <item> <playlistid></playlistid>
	 * <layoutitemid></layoutitemid> <name></name> <starttime></starttime>
	 * <stoptime></stoptime> </item> </playlistitem>
	 */
	public static int adminCreatePlaylist(String xStr) throws RemoteException {

		AdminInsertPlaylistCore content = new AdminInsertPlaylistCore();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminInsertPlaylistCore(content)
				.get_return();
	}

	/*
	 * input
	 * <parameter><groupid>0</groupid><groupmoveid>1</groupmoveid></parameter>
	 */
	public static String adminMoveGroup(String xStr) throws RemoteException {

		AdminMoveGroup content = new AdminMoveGroup();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminMoveGroup(content).get_return();
	}

	/*
	 * input
	 * <parameter><playlistid></playlistid><nameplaylist></nameplaylist></parameter
	 * >
	 */
	public static String adminUpdatePlayListName(String xStr)
			throws RemoteException {

		AdminUpdatePlayListName content = new AdminUpdatePlayListName();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminUpdatePlayListName(content)
				.get_return();
	}

	/*
	 * input
	 * <parameter><item><namelayout></namelayout><layoutid></layoutid><xcoor
	 * ></xcoor><ycoor></ycoor> <width></width><height
	 * ></height></item></parameter>
	 */
	public static String adminUpdateLayout(String xStr) throws RemoteException {

		AdminUpdateLayoutCoor content = new AdminUpdateLayoutCoor();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminUpdateLayoutCoor(content)
				.get_return();
	}

	/*
	 * input
	 * <parameter><item><playlistitemid></playlistitemid><startdate></startdate
	 * ><stopdate></stopdate ><item></parameter>
	 */
	public static String adminUpdatePlaylistTime(String xStr)
			throws RemoteException {

		AdminUpdatePlaylistTime content = new AdminUpdatePlaylistTime();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminUpdatePlaylistTime(content)
				.get_return();
	}

	/*
	 * input
	 * <stb><stbid>0</stbid><name>haha</name><urlserver></urlserver><savelocalmedia
	 * ></savelocalmedia ><savelocalfile></savelocalfile></stb>
	 */
	public static String adminUPdateStb(String xStr) throws RemoteException {

		AdminUdateStb content = new AdminUdateStb();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminUdateStb(content).get_return();
	}

	/*
	 * input
	 * <Scheduleperio><item><groupid></groupid><nameschedule></nameschedule
	 * ><startdate></startdate ><stopdate></stopdate></item></Scheduleperio>
	 */
	public static String adminNewSchedulePeriName(String xStr)
			throws RemoteException {

		AdminNewSchedulePeriName content = new AdminNewSchedulePeriName();
		content.setXmlparam(xStr);
		return Constant.getServiceStub().adminNewSchedulePeriName(content)
				.get_return();
	}

	/*
	 * input
	 * <Scheduleperio><item><dailynameid></dailyhnameid><scheduleid></scheduleid
	 * ></item></Scheduleperio >
	 */
	public static void adminNewSchedulePeri(String xStr) throws RemoteException {

		AdminNewSchedulePeri content = new AdminNewSchedulePeri();
		content.setXmlparam(xStr);
		Constant.getServiceStub().adminNewSchedulePeri(content);
	}

	/*
	 * input: <parameter><layoutid>1</layoutid></parameter>
	 */
	public static String adminDelLayOutItem(String xStr) throws RemoteException {

		AdminDelLayOutItem content = new AdminDelLayOutItem();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminDelLayOutItem(content)
				.get_return();
	}

	/**
	 * 
	 * @param <paramater>
	 *            <item> <dailynameid></dailynameid> <namedaily></namedaily>
	 *            </item> </paramater>
	 * @throws RemoteException
	 */
	public static void adminUpdateScheduleDailyName(String xml)
			throws RemoteException {
		UpdateScheduleDaily content = new UpdateScheduleDaily();
		content.setXmlparameter(xml);
		Constant.getServiceStub().updateScheduleDaily(content);
	}

	/*
	 * input
	 * <parameter><item><scheduleitemid></scheduleitemid><starttime></starttime
	 * ><stoptime></stoptime ></item></parameter>
	 */
	public static String adminUpdateScheduleDailyTime(String xStr)
			throws RemoteException {

		AdminUpdateScheduleDailyTime content = new AdminUpdateScheduleDailyTime();
		content.setXmlparam(xStr);
		return Constant.getServiceStub().adminUpdateScheduleDailyTime(content)
				.get_return();
	}

	/*
	 * input <parameter><scheduleitemid></scheduleitemid></parameter>
	 */
	public static String adminDelSchduledailyTime(String xStr)
			throws RemoteException {

		AdminDelSchduledailyTime content = new AdminDelSchduledailyTime();
		content.setXmlparam(xStr);
		return Constant.getServiceStub().adminDelSchduledailyTime(content)
				.get_return();
	}

	/*
	 * input:
	 * <parameter><nameContent></nameContent><urlContent></urlContent><colorText
	 * ></colorText><
	 * fontText></fontText><sizeText></sizeText><direcRoll></direcRoll
	 * ></parameter>
	 */
	public static void adminNewContentText(String xStr) throws Exception {

		AdminNewContentText content = new AdminNewContentText();
		content.setXmlparam(xStr);
		Constant.getServiceStub().adminNewContentText(content);
	}

	/*
	 * input <parameter><id_Content></id_Content></parameter>
	 */
	public static void adminDelContentText(String xStr) throws Exception {

		AdminDelContentText content = new AdminDelContentText();
		content.setXmlparam(xStr);
		Constant.getServiceStub().adminDelContentText(content);
	}

	/*
	 * input
	 * <parameter><id_Content></id_Content><nameContent></nameContent><urlContent
	 * ></urlContent> <colorText
	 * ></colorText><fontText></fontText><sizeText></sizeText
	 * ><direcRoll></direcRoll></parameter>
	 */
	public static void adminUpdateContentText(String xStr) throws Exception {

		AdminUpdateContentText content = new AdminUpdateContentText();
		content.setXmlparam(xStr);
		Constant.getServiceStub().adminUpdateContentText(content);
	}

	/*
	 * input
	 * <parameter><item><stbid></stbid><groupid></groupid></item></parameter>
	 */
	public static String adminStbPush(String xStr) throws Exception {

		AdminStbPush content = new AdminStbPush();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminStbPush(content).get_return();
	}

	/*
	 * input <parameter><layoutid></layoutid></parameter>
	 */
	public static String admingetLayoutContainContent(String xStr)
			throws Exception {

		AdmingetLayoutContainContent content = new AdmingetLayoutContainContent();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().admingetLayoutContainContent(content)
				.get_return();
	}

	/*
	 * input
	 * <parameter><item><playlistitemid></playlistitemid><item></parameter>
	 */
	public static String adminDelItemPlaylist(String xml) throws Exception {

		AdminDelItemPlaylist content = new AdminDelItemPlaylist();
		content.setXmlparamter(xml);
		return Constant.getServiceStub().adminDelItemPlaylist(content)
				.get_return();
	}

	/*
	 * input <parameter><scheduleid></scheduleid></parameter>
	 */
	public static void adminDelSchedulePeri(String xml) throws Exception {

		AdminDelSchedulePeri content = new AdminDelSchedulePeri();
		content.setXmlparam(xml);
		Constant.getServiceStub().adminDelSchedulePeri(content);

	}

	/*
	 * input <parameter><scheduleid></scheduleid></parameter>
	 */
	public void adminDelSchedulePeriItem(String xml) throws Exception {

		AdminDelSchedulePeriItem content = new AdminDelSchedulePeriItem();
		content.setXmlparam(xml);
		Constant.getServiceStub().adminDelSchedulePeriItem(content);

	}

	/*
	 * input
	 * <parameter><scheduleid></scheduleid><nameschedule></nameschedule><stopdate
	 * ></stopdate><startdate ></startdate></parameter>
	 */
	public static void adminUpdateSchedulePeri(String xml) throws Exception {

		AdminUpdateSchedulePeri content = new AdminUpdateSchedulePeri();
		content.setXmlparam(xml);
		Constant.getServiceStub().adminUpdateSchedulePeri(content);
	}

	/*
	 * input <parameter><playlistid></playlistid></parameter>
	 */
	public static String adminDelPlaylist(String xml) throws Exception {

		AdminDelPlaylist content = new AdminDelPlaylist();
		content.setXmlparamter(xml);
		return Constant.getServiceStub().adminDelPlaylist(content).get_return();
	}

	// lay tat ca stb 1.9
	public static String admingetListStbAll(String xml) throws Exception {

		AdmingetListStbAlls content = new AdmingetListStbAlls();
		content.setXmlparamter(xml);
		return Constant.getServiceStub().admingetListStbAlls(content)
				.get_return();
	}

	// lay config cua ftp upload 4.9
	/*
	 * output: <Config> <server_ftp>localhost</server_ftp>
	 * <server_port>21</server_port> <src_file>Src</src_file>
	 * <local_file>D:\tmp</local_file> <desc_host>localhost</desc_host>
	 * <serveruserftp>adcenter</serveruserftp>
	 * <serverpassftp>adcenter</serverpassftp> </Config>
	 */
	public static String admingetConfig() throws Exception {
		AdmingetConfig content = new AdmingetConfig();
		content.setXmlparam("");
		return Constant.getServiceStub().admingetConfig(content).get_return();
	}

	/*
	 * public static String adminNewContent(String xmlparam)
	 * <parameter><nameContent></nameContent><urlContent
	 * ></urlContent><typeContent></typeContent></parameter>
	 */
	public static String adminNewContent(String xmlparam) throws Exception {

		AdminNewContentLogic content = new AdminNewContentLogic();
		content.setXmlparam(xmlparam);
		Constant.getServiceStub().adminNewContentLogic(content);
		return "";
	}

	/*
	 * public static String adminNewContentWithDuration(String xmlparam)
	 * <parameter><nameContent></nameContent
	 * ><urlContent></urlContent><typeContent></typeContent></parameter>
	 */
	public void adminNewContentWithDuration(String xmlparam) throws Exception {

		AdminNewContentWithDuration content = new AdminNewContentWithDuration();
		content.setXmlparamter(xmlparam);
		Constant.getServiceStub().adminNewContentWithDuration(content);
	}

	/*
	 * input public static String admingetPlayListLayout(String xmlparamter)
	 * <parameter><playlistid>12</playlistid></parameter>
	 */
	public static String admingetPlayListLayout(String xmlparamter)
			throws Exception {

		AdmingetPlayListLayout content = new AdmingetPlayListLayout();
		content.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().admingetPlayListLayout(content)
				.get_return();
	}

	/*
	 * update config ftp 7.9 output: <Config> <server_ftp>localhost</server_ftp>
	 * <server_port>21</server_port> <src_file>Src</src_file>
	 * <local_file>D:\tmp</local_file> <desc_host>localhost</desc_host>
	 * <serveruserftp>adcenter</serveruserftp>
	 * <serverpassftp>adcenter</serverpassftp> </Config>
	 */
	public static void adminUpdateConfig(String xmlparamter) throws Exception {

		AdminUpdateConfig content = new AdminUpdateConfig();
		content.setXmlparamter(xmlparamter);
		Constant.getServiceStub().adminUpdateConfig(content);
	}

	/*
	 * public static String adminUpdateDirecGroup(String xmlparamter) landscape
	 * = 1, portrait = 2 input
	 * <parameter><groupid></groupid></direction><direction></parameter>
	 */
	public static String adminUpdateDirecGroup(String xmlparamter)
			throws Exception {

		AdminUpdateDirecGroup content = new AdminUpdateDirecGroup();
		content.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminUpdateDirecGroup(content)
				.get_return();
	}

	/*
	 * public static String adminCheckStb(String xmlparamter)
	 * <parameter><stbid>0</stbid></parameter>
	 */
	public static String adminCheckStb(String xmlparamter) throws Exception {

		AdminCheckStb content = new AdminCheckStb();
		content.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminCheckStb(content).get_return();
	}

	/*
	 * public static String adminCheckStb(String xmlparamter)
	 * <parameter><stbid>0</stbid></parameter>
	 */
	public static String adminDelStb(String xmlparamter) throws Exception {

		AdminDelStb content = new AdminDelStb();
		content.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminDelStb(content).get_return();
	}

	/*
	 * public static String adminReportContentAll(String xmlparamter)
	 * <parameter><groupname>-1</groupname></parameter>
	 */
	public static String adminReportContentAll(String xmlparamter)
			throws Exception {

		AdminReportContentAll content = new AdminReportContentAll();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportContentAll(content)
				.get_return();
	}

	/*
	 * public static String adminReportLayout(String xmlparamter)
	 * <parameter><groupname
	 * >-1</groupname><createdate>-1</createdate></parameter>
	 */
	public static String adminReportLayout(String xmlparamter) throws Exception {

		AdminReportLayout content = new AdminReportLayout();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportLayout(content)
				.get_return();
	}

	/*
	 * public static String adminReportPlaylist(String xmlparam)
	 * <parameter><groupname></groupname><createdatestart
	 * ></createdatestart><createdatestop></createdatestop></parameter>
	 */
	public static String adminReportPlaylist(String xmlparamter)
			throws Exception {

		AdminReportPlaylist content = new AdminReportPlaylist();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportPlaylist(content)
				.get_return();
	}

	/*
	 * public static String adminReportDaily(String xmlparam)
	 * <parameter><groupname></groupname><createdatestart
	 * ></createdatestart><createdatestop></createdatestop></parameter>
	 */
	public static String adminReportDaily(String xmlparamter) throws Exception {

		AdminReportDaily content = new AdminReportDaily();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportDaily(content).get_return();
	}

	/*
	 * public static String adminReportSchedulePeriod(String xmlparam)
	 * <parameter><groupname></groupname></parameter>
	 */
	public static String adminReportSchedulePeriod(String xmlparamter)
			throws Exception {
		AdminReportSchedulePeriod content = new AdminReportSchedulePeriod();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportSchedulePeriod(content)
				.get_return();
	}

	/*
	 * public static String adminReportStb(String xmlparam)
	 * <parameter><groupname></groupname></parameter>
	 */
	public static String adminReportStb(String xmlparamter) throws Exception {
		AdminReportStb content = new AdminReportStb();
		content.setXmlparam(xmlparamter);
		return Constant.getServiceStub().adminReportStb(content).get_return();
	}

	public static String adminGetAllContent(String xmlparamter)
			throws Exception {
		AdminGetAllContent content = new AdminGetAllContent();
		content.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminGetAllContent(content)
				.get_return();
	}

	public static String adminGetAllContentMedia(String xmlparamter)
			throws Exception {

		AdminGetAllContentMedia media = new AdminGetAllContentMedia();

		media.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminGetAllContentMedia(media).get_return();

	}

	public static void adminDeleteGroup(String xMLCREATE)
			throws RemoteException {
		AdminDelGroup group = new AdminDelGroup();
		group.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().adminDelGroup(group);

	}

	public static String adminAddSubjectContent(String xmlparamter)
			throws Exception {
		AdminAddSubjectContent subject = new AdminAddSubjectContent();
		subject.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminAddSubjectContent(subject)
				.get_return();
	}

	public static String adminGetSubjectByUser(String xmlparamter)
			throws Exception {
		AdminGetAllSubjectContent subject = new AdminGetAllSubjectContent();
		subject.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminGetAllSubjectContent(subject)
				.get_return();
	}

	public static String adminUpdateSubjectContent(String xmlparamter)
			throws Exception {
		AdminUpdateSubjectContent subject = new AdminUpdateSubjectContent();
		subject.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminUpdateSubjectContent(subject)
				.get_return();
	}

	public static String adminDeleteSubjectContent(String xmlparamter)
			throws Exception {
		AdminDeleteSubjectContent subject = new AdminDeleteSubjectContent();
		subject.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminDeleteSubjectContent(subject)
				.get_return();
	}

	public static void pushContentIntoSTBAuto(String xMLCREATE)
			throws RemoteException {
		PushContentIntoSTBAuto content = new PushContentIntoSTBAuto();
		content.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().pushContentIntoSTBAuto(content);
	}

	public static void abopDeleteContentFromSTB(String xMLCREATE)
			throws RemoteException {
		AbopDeleteContentFromSTB abop = new AbopDeleteContentFromSTB();
		abop.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().abopDeleteContentFromSTB(abop);
	}
	
	public static void adminAddNewContentMedia(String xmlparamter) throws Exception{
		AdminAddNewContentMedia content = new AdminAddNewContentMedia();
		content.setXmlparamter(xmlparamter);
		Constant.getServiceStub().adminAddNewContentMedia(content);
	}
	
	public static void adminAddNewSlideContent(String xmlparamter) throws Exception{
		AdminAddNewSlideContent content = new AdminAddNewSlideContent();
		content.setXmlparamter(xmlparamter);
		Constant.getServiceStub().adminAddNewSlideContent(content);
	}
	
	public static void adminDeleteSlideContent(String xmlparamter) throws Exception{
		AdminDeleteSlideContent content = new AdminDeleteSlideContent();
		content.setXmlparamter(xmlparamter);
		Constant.getServiceStub().adminDeleteSlideContent(content);
	}
	
	public static String adminGetContentIDByForSlide(String xmlparamter) throws Exception{
		AdminGetContentIDByForSlide slide = new AdminGetContentIDByForSlide();
		slide.setXmlparamter(xmlparamter);
		return Constant.getServiceStub().adminGetContentIDByForSlide(slide).get_return();
	}
	
	public static void adminUpdateSlideContent(String xmlparamter) throws Exception{
		AdminUpdateSlideContent slide = new AdminUpdateSlideContent();
		slide.setXmlparamter(xmlparamter);
		Constant.getServiceStub().adminUpdateSlideContent(slide);
	}

	public static void adminDeleteAllContentAllSTBInGroup(String xMLCREATE)
			throws RemoteException {
		AdminDeleteAllContentAllSTBInGroup abop = new AdminDeleteAllContentAllSTBInGroup();
		abop.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().adminDeleteAllContentAllSTBInGroup(abop);

	}

	/*
	 * input
	 * <parameter><item><layoutid></layoutid><xcoor></xcoor><ycoor></ycoor>
	 * <width></width><height ></height></item></parameter>
	 */
	public static String adminUpdateLayoutCoor(String xStr)
			throws RemoteException {
		AdminUpdateLayoutCoor content = new AdminUpdateLayoutCoor();
		content.setXmlparamter(xStr);
		return Constant.getServiceStub().adminUpdateLayoutCoor(content)
				.get_return();
	}

	public static void adminRemoveSTBOutToGroup(String xMLCREATE) throws RemoteException {
		AdminRemoveSTBOutToGroup stb = new AdminRemoveSTBOutToGroup();
		stb.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().adminRemoveSTBOutToGroup(stb);
		
	}

	public static void abopDeletePlaylistItems(String xMLCREATE) throws RemoteException {
		AbopDeletePlaylistItems stb = new AbopDeletePlaylistItems();
		stb.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().abopDeletePlaylistItems(stb);
	}

	public static void abopSetMonitoring(String xMLCREATE) throws RemoteException {
		AbopSetMonitoring stb = new AbopSetMonitoring();
		stb.setXmlparamter(xMLCREATE);
		Constant.getServiceStub().abopSetMonitoring(stb);
		
	}

	public static String getUser(String xml) throws RemoteException {
		AbopGetUser user = new AbopGetUser();
		user.setXmlparamter(xml);
		return Constant.getServiceStub().abopGetUser(user).get_return();
	}

	public static String getListUser(String xml) throws RemoteException {
		AbopGetListUser user = new AbopGetListUser();
		user.setXmlparamter(xml);
		return Constant.getServiceStub().abopGetListUser(user).get_return();
	}

	public static String abopCreateUser(String xml) throws RemoteException {
		AbopCreateUser user = new AbopCreateUser();
		user.setXmlparamter(xml);
		return Constant.getServiceStub().abopCreateUser(user).get_return();
	}

	public static String abopGetRole() throws RemoteException {
		AbopGetRole role = new AbopGetRole();
		return Constant.getServiceStub().abopGetRole(role).get_return();
	}

	public static String abopCheckUser(String username) throws RemoteException {
		AbopCheckUser user = new AbopCheckUser();
		user.setUsername(username);
		return Constant.getServiceStub().abopCheckUser(user).get_return();
	}

	public static String abopUpdateUser(String xml) throws RemoteException {
		AbopUpdateUser user = new AbopUpdateUser();
		user.setXmlparamter(xml);
		return Constant.getServiceStub().abopUpdateUser(user).get_return();
	}
	public static String abopAddStbUser(String xml) throws RemoteException {
		AbopAddStbUser user = new AbopAddStbUser();
		user.setXmlparamter(xml);
		return Constant.getServiceStub().abopAddStbUser(user).get_return();
	}
	public static String abopDeleteUser(String username) throws RemoteException {
		AbopDeleteUser user = new AbopDeleteUser();
		user.setXmlparamter(username);
		return Constant.getServiceStub().abopDeleteUser(user).get_return();
	}

}