package elcom.abop.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import elcom.abop.bean.ObjectABOP;
import elcom.abop.bean.ObjectConfig;
import elcom.abop.bean.User;
import elcom.abop.common.ApplyItemConstant;

public class ParseXmlService extends UtilBasic {
	private static final int NOTE_LENGTH = 25;
	private static final int NAME_LENGTH = 12;
	private static Logger log = Logger.getLogger(ParseXmlService.class);
	// doc direction cua layout 25.9
	public static ArrayList<HashMap<String, String>> getLayoutNameMap1(String xmlstring, int direction) {
		String direct = (direction == 1) ? "l" : "p";
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList dateList = eElement.getElementsByTagName("date").item(0).getChildNodes();
				Node dateValue = (Node) dateList.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("name", nValue.getNodeValue());
				mp.put("date", dateValue.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				mp.put("direction", direct);
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	// get List of STB
	public static ArrayList<ObjectABOP> getStbMap(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList sessionNl = eElement.getElementsByTagName("session").item(0).getChildNodes();
				Node sessionValue = (Node) sessionNl.item(0);

				NodeList statusonoffNl = eElement.getElementsByTagName("statusonof").item(0).getChildNodes();
				Node statusonoffValue = (Node) statusonoffNl.item(0);

				NodeList idGroupNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idGroupValue = (Node) idGroupNl.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList serinumberNl = eElement.getElementsByTagName("serinumber").item(0).getChildNodes();
				Node serinumberValue = (Node) serinumberNl.item(0);

				NodeList macNl = eElement.getElementsByTagName("mac").item(0).getChildNodes();
				Node macValue = (Node) macNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("urlserver").item(0).getChildNodes();
				Node urlValue = (Node) urlNl.item(0);

				NodeList ramNL = eElement.getElementsByTagName("ram").item(0).getChildNodes();
				Node ram = (Node) ramNL.item(0);

				NodeList ramTotalNL = eElement.getElementsByTagName("ramtotal").item(0).getChildNodes();
				Node ramTotal = (Node) ramTotalNL.item(0);

				NodeList ipNL = eElement.getElementsByTagName("ipadress").item(0).getChildNodes();
				Node ip = (Node) ipNL.item(0);

				NodeList sdcardNL = eElement.getElementsByTagName("sdcard").item(0).getChildNodes();
				Node sdcard = (Node) sdcardNL.item(0);

				NodeList sdcardTotalNL = eElement.getElementsByTagName("sdcardtotal").item(0).getChildNodes();
				Node sdcardTotal = (Node) sdcardTotalNL.item(0);

				NodeList statusNl = eElement.getElementsByTagName("giamsatstatus").item(0).getChildNodes();
				Node statusValue = (Node) statusNl.item(0);

				NodeList reqNl = eElement.getElementsByTagName("req").item(0).getChildNodes();
				Node reqValue = (Node) reqNl.item(0);

				NodeList note = eElement.getElementsByTagName("note").item(0).getChildNodes();
				Node noteValue = (Node) note.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setSession(sessionValue.getNodeValue());
				obj.setTime(reqValue.getNodeValue());
				obj.setStatus(statusonoffValue.getNodeValue());
				obj.setStatusMonitor(statusValue.getNodeValue());
				obj.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setIdParent(idGroupValue.getNodeValue());
				obj.setSerinumber(serinumberValue.getNodeValue());
				obj.setMac(macValue.getNodeValue());
				obj.setUrl(urlValue.getNodeValue());
				obj.setNote(UtilBasic.subString(noteValue.getNodeValue(), NOTE_LENGTH));
				obj.setNoteFull(noteValue.getNodeValue());
				obj.setRam(ram.getNodeValue());
				obj.setTotalRam(ramTotal.getNodeValue());
				obj.setIp(ip.getNodeValue());
				obj.setSdCard(sdcard.getNodeValue());
				obj.setTotalSDCard(sdcardTotal.getNodeValue());
				lt.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input <ReportList><item idcontent="1"> <namecontent>Clip01</namecontent> <urlcontent>Big Buck Bunny animation (1080p
	 * HD).mp4</urlcontent> <namegroup>Hcm_Group</namegroup> </item> <item idcontent="12"> <namecontent>Clip02</namecontent>
	 * <urlcontent>Transformers3.mp4</urlcontent> <namegroup>Hcm_Group</namegroup> </item> </ReportList>
	 */
	public static ArrayList<HashMap<String, String>> contentAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("namecontent").item(0).getChildNodes();
				Node namecontent = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("urlcontent").item(0).getChildNodes();
				Node urlcontent = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("namegroup").item(0).getChildNodes();
				Node namegroup = (Node) src_fileNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("namecontent", namecontent.getNodeValue());
				mp.put("urlcontent", urlcontent.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("idcontent", eElement.getAttributeNode("idcontent").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	// get all stb 1.9
	public static ArrayList<ObjectABOP> getAllStbMap(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList sessionNl = eElement.getElementsByTagName("session").item(0).getChildNodes();
				Node sessionValue = (Node) sessionNl.item(0);

				NodeList statusonoffNl = eElement.getElementsByTagName("statusonof").item(0).getChildNodes();
				Node statusonoffValue = (Node) statusonoffNl.item(0);

				NodeList idGroupNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idGroupValue = (Node) idGroupNl.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList serinumberNl = eElement.getElementsByTagName("serinumber").item(0).getChildNodes();
				Node serinumberValue = (Node) serinumberNl.item(0);

				NodeList macNl = eElement.getElementsByTagName("mac").item(0).getChildNodes();
				Node macValue = (Node) macNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("urlserver").item(0).getChildNodes();
				Node urlValue = (Node) urlNl.item(0);

				NodeList ramNL = eElement.getElementsByTagName("ram").item(0).getChildNodes();
				Node ram = (Node) ramNL.item(0);

				NodeList ramTotalNL = eElement.getElementsByTagName("ramtotal").item(0).getChildNodes();
				Node ramTotal = (Node) ramTotalNL.item(0);

				NodeList ipNL = eElement.getElementsByTagName("ipadress").item(0).getChildNodes();
				Node ip = (Node) ipNL.item(0);

				NodeList sdcardNL = eElement.getElementsByTagName("sdcard").item(0).getChildNodes();
				Node sdcard = (Node) sdcardNL.item(0);

				NodeList sdcardTotalNL = eElement.getElementsByTagName("sdcardtotal").item(0).getChildNodes();
				Node sdcardTotal = (Node) sdcardTotalNL.item(0);

				NodeList statusNl = eElement.getElementsByTagName("giamsatstatus").item(0).getChildNodes();
				Node statusValue = (Node) statusNl.item(0);

				NodeList downloadNl = eElement.getElementsByTagName("giamsatstatus").item(0).getChildNodes();
				Node download = (Node) downloadNl.item(0);

				NodeList reqNl = eElement.getElementsByTagName("req").item(0).getChildNodes();
				Node reqValue = (Node) reqNl.item(0);

				NodeList note = eElement.getElementsByTagName("note").item(0).getChildNodes();
				Node noteValue = (Node) note.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setSession(sessionValue.getNodeValue());
				obj.setTime(reqValue.getNodeValue());
				obj.setStatus(statusonoffValue.getNodeValue());
				obj.setStatusMonitor(statusValue.getNodeValue());
				obj.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setIdParent(idGroupValue.getNodeValue());
				obj.setSerinumber(serinumberValue.getNodeValue());
				obj.setMac(macValue.getNodeValue());
				obj.setUrl(urlValue.getNodeValue());
				obj.setNote(UtilBasic.subString(noteValue.getNodeValue(), NOTE_LENGTH));
				obj.setNoteFull(noteValue.getNodeValue());
				obj.setRam(ram.getNodeValue());
				obj.setTotalRam(ramTotal.getNodeValue());
				obj.setIp(ip.getNodeValue());
				obj.setSdCard(sdcard.getNodeValue());
				obj.setTotalSDCard(sdcardTotal.getNodeValue());
				obj.setStatusDownload(download.getNodeValue());
				lt.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getLayoutName(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(ApplyItemConstant.KEY_ITEM);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName(ApplyItemConstant.KEY_NAME).item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList dateList = eElement.getElementsByTagName(ApplyItemConstant.KEY_DATE).item(0).getChildNodes();
				Node dateValue = (Node) dateList.item(0);

				NodeList direction = eElement.getElementsByTagName(ApplyItemConstant.KEY_DIRECTION).item(0).getChildNodes();
				Node directionVal = (Node) direction.item(0);

				NodeList type = eElement.getElementsByTagName(ApplyItemConstant.KEY_TYPE).item(0).getChildNodes();
				Node typeVal = (Node) type.item(0);

				NodeList size = eElement.getElementsByTagName(ApplyItemConstant.KEY_SIZE).item(0).getChildNodes();
				Node sizeScreen = (Node) size.item(0);

				ObjectABOP abop = new ObjectABOP();
				abop.setDirection(directionVal.getNodeValue());
				abop.setName(subString(nValue.getNodeValue(), NAME_LENGTH));
				abop.setNameFull(nValue.getNodeValue());
				abop.setDate(dateValue.getNodeValue());
				abop.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				abop.setType(typeVal.getNodeValue());
				abop.setSize(sizeScreen.getNodeValue());
				lt.add(abop);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	@SuppressWarnings("unused")
	public static ArrayList<ObjectABOP> getContentStb(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName(ApplyItemConstant.KEY_ITEM);
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList stbidNl = eElement.getElementsByTagName("stbid").item(0).getChildNodes();
				Node stbid = (Node) stbidNl.item(0);

				NodeList contentidNl = eElement.getElementsByTagName("contentid").item(0).getChildNodes();
				Node contentid = (Node) contentidNl.item(0);

				NodeList contentnameNl = eElement.getElementsByTagName("contentname").item(0).getChildNodes();
				Node contentname = (Node) contentnameNl.item(0);

				NodeList contenturlNl = eElement.getElementsByTagName("contenturl").item(0).getChildNodes();
				Node contenturl = (Node) contenturlNl.item(0);

				NodeList contenttypeNl = eElement.getElementsByTagName("contenttype").item(0).getChildNodes();
				Node contenttype = (Node) contenttypeNl.item(0);

				// bo sung them attr process 7.9
				NodeList processNl = eElement.getElementsByTagName("process").item(0).getChildNodes();
				Node process = (Node) processNl.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setIdParent(stbid.getNodeValue());
				obj.setId(contentid.getNodeValue());
				obj.setName(UtilBasic.subString(contentname.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(contentname.getNodeValue());
				obj.setUrl(contenturl.getNodeValue());
				obj.setType(contenttype.getNodeValue());
				obj.setStatusDownload(process.getNodeValue());
				lt.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static String pushXml(String xmlSrc, String srcfileid) {
		String xml = null;
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlSrc));

			doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				eElement.removeAttribute("id");

				NodeList idgroupNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idgroupValue = (Node) idgroupNl.item(0);
				eElement.getElementsByTagName("idgroup").item(0).removeChild(idgroupValue);
				eElement.removeChild(eElement.getElementsByTagName("idgroup").item(0));

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);
				eElement.getElementsByTagName("name").item(0).removeChild(nameValue);
				eElement.removeChild(eElement.getElementsByTagName("name").item(0));

				NodeList urlserverNl = eElement.getElementsByTagName("urlserver").item(0).getChildNodes();
				Node urlserverValue = (Node) urlserverNl.item(0);
				eElement.getElementsByTagName("urlserver").item(0).removeChild(urlserverValue);
				eElement.removeChild(eElement.getElementsByTagName("urlserver").item(0));

				NodeList statusNl = eElement.getElementsByTagName("status").item(0).getChildNodes();
				Node statusValue = (Node) statusNl.item(0);
				eElement.getElementsByTagName("status").item(0).removeChild(statusValue);
				eElement.removeChild(eElement.getElementsByTagName("status").item(0));

				NodeList statusonofNl = eElement.getElementsByTagName("statusonof").item(0).getChildNodes();
				Node statusonofValue = (Node) statusonofNl.item(0);
				eElement.getElementsByTagName("statusonof").item(0).removeChild(statusonofValue);
				eElement.removeChild(eElement.getElementsByTagName("statusonof").item(0));

				Element srcfileidChild = doc.createElement("srcfileid");
				eElement.appendChild(srcfileidChild);

				eElement.appendChild(srcfileidChild);
				// add a text element to the child
				Text text = doc.createTextNode(srcfileid);
				srcfileidChild.appendChild(text);

			}
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			Source source = new DOMSource(doc);
			transformer.transform(source, result);
			writer.close();
			xml = writer.toString();
			xml = xml.replaceAll("\r\n\r\n", "").replaceAll("&gt;", "").replaceAll("</srcfileid></item>", "</srcfileid>\r\n</item>")
					.replaceAll("</session><savelocalfilemedia>", "</session>\r\n<savelocalfilemedia>").replaceAll("><Stb>", ">\r\n<Stb>")
					// remove dong nay trong qua trinh reboot de truyen du lieu
					// goi den stb 12.04.13
					.replaceAll("<reboot>NO</reboot>", "").replaceAll("<reboot>YES</reboot>", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	public static ArrayList<HashMap<String, String>> getTypeLayout(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));
			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList idList = eElement.getElementsByTagName("status").item(0).getChildNodes();
				Node idValue = (Node) idList.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("name", nValue.getNodeValue());
				mp.put("status", idValue.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getGroupItemName(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				// them vao xac dinh direction cua group 15.9
				NodeList directionNl = eElement.getElementsByTagName("direction").item(0).getChildNodes();
				Node direction = (Node) directionNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				mp.setNameFull(nameValue.getNodeValue());
				mp.setDirection(direction.getNodeValue());
				mp.setId(eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getGroupItemMap(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList typeContentNl = eElement.getElementsByTagName("typecontent").item(0).getChildNodes();
				Node typeContentValue = (Node) typeContentNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("url").item(0).getChildNodes();
				Node url = (Node) urlNl.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setUrl(url.getNodeValue());
				obj.setTime(nValue.getNodeValue());
				obj.setType(UtilBasic.convertFileType(typeContentValue.getNodeValue()));
				obj.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				lt.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getLayoutItemMap(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			Element firstNode = (Element) doc.getElementsByTagName("Layout").item(0);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList typeNl = eElement.getElementsByTagName("type").item(0).getChildNodes();
				Node type = (Node) typeNl.item(0);

				NodeList nlX = eElement.getElementsByTagName("x").item(0).getChildNodes();
				Node xValue = (Node) nlX.item(0);

				NodeList nlY = eElement.getElementsByTagName("y").item(0).getChildNodes();
				Node yValue = (Node) nlY.item(0);

				NodeList nlW = eElement.getElementsByTagName("width").item(0).getChildNodes();
				Node wValue = (Node) nlW.item(0);

				NodeList nlH = eElement.getElementsByTagName("height").item(0).getChildNodes();
				Node hValue = (Node) nlH.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				ObjectABOP abop = new ObjectABOP();

				abop.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				abop.setNameFull(nameValue.getNodeValue());
				abop.setIdParent(firstNode.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				abop.setType(UtilBasic.convertFileType(type.getNodeValue()));
				abop.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				abop.setLeft(xValue.getNodeValue());
				abop.setTop(yValue.getNodeValue());
				abop.setWidth(wValue.getNodeValue());
				abop.setHeight(hValue.getNodeValue());
				lt.add(abop);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<HashMap<String, String>> getLayoutItemForPlMap(String xmlstring, String layoutName) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList typeNl = eElement.getElementsByTagName("type").item(0).getChildNodes();
				Node type = (Node) typeNl.item(0);

				NodeList nlX = eElement.getElementsByTagName("x").item(0).getChildNodes();
				Node xValue = (Node) nlX.item(0);

				NodeList nlY = eElement.getElementsByTagName("y").item(0).getChildNodes();
				Node yValue = (Node) nlY.item(0);

				NodeList nlW = eElement.getElementsByTagName("width").item(0).getChildNodes();
				Node wValue = (Node) nlW.item(0);

				NodeList nlH = eElement.getElementsByTagName("height").item(0).getChildNodes();
				Node hValue = (Node) nlH.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("layoutName", layoutName);
				mp.put("name", nameValue.getNodeValue());
				mp.put("type", type.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				mp.put("x", xValue.getNodeValue());
				mp.put("y", yValue.getNodeValue());
				mp.put("w", wValue.getNodeValue());
				mp.put("h", hValue.getNodeValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	// get Name List of Playlist
	/*
	 * input <List> <item id='5'> <name>My PL 1</name> <duration>00:00:00</duration> <createdate>2012-07-25</createdate> </item> </List>
	 */
	public static ArrayList<ObjectABOP> admingetAllPlaylist(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList durationNl = eElement.getElementsByTagName("duration").item(0).getChildNodes();
				Node duration = (Node) durationNl.item(0);

				NodeList createdateNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) createdateNl.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setTime(duration.getNodeValue());
				obj.setDate(createdate.getNodeValue());
				obj.setId(eElement.getAttributeNode("id").getValue());
				lt.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}
	
	public static ArrayList<ObjectABOP> admingetPlaylistByUser(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList durationNl = eElement.getElementsByTagName("duration").item(0).getChildNodes();
				Node duration = (Node) durationNl.item(0);

				NodeList createdateNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) createdateNl.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setTime(duration.getNodeValue());
				obj.setDate(createdate.getNodeValue());
				obj.setId(eElement.getAttributeNode("id").getValue());
				lt.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}
	

	public static ArrayList<ObjectABOP> admingetPlaylistByGroupId(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList durationNl = eElement.getElementsByTagName("duration").item(0).getChildNodes();
				Node duration = (Node) durationNl.item(0);

				NodeList createdateNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) createdateNl.item(0);

				ObjectABOP obj = new ObjectABOP();
				obj.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
				obj.setNameFull(nameValue.getNodeValue());
				obj.setTime(duration.getNodeValue());
				obj.setDate(createdate.getNodeValue());
				obj.setId(eElement.getAttributeNode("id").getValue());
				lt.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	// get Item List of Playlist
	public static ArrayList<ObjectABOP> getPlaylistItem(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("List");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList itemNl = eElement.getElementsByTagName("item");
				for (int j = 0; j < itemNl.getLength(); j++) {
					Node nnNode = itemNl.item(j);
					Element eeElement = (Element) nnNode;

					NodeList nameNl = eeElement.getElementsByTagName("name").item(0).getChildNodes();
					Node nameValue = (Node) nameNl.item(0);
					NodeList starttimeNl = eeElement.getElementsByTagName("starttime").item(0).getChildNodes();
					Node starttimeValue = (Node) starttimeNl.item(0);
					NodeList stoptimeNl = eeElement.getElementsByTagName("stoptime").item(0).getChildNodes();
					Node stoptimeValue = (Node) stoptimeNl.item(0);
					NodeList statusNl = eeElement.getElementsByTagName("status").item(0).getChildNodes();
					Node statusValue = (Node) statusNl.item(0);
					NodeList typeNl = eeElement.getElementsByTagName("type").item(0).getChildNodes();
					Node typeValue = (Node) typeNl.item(0);

					ObjectABOP mp = new ObjectABOP();
					mp.setName(subString(nameValue.getNodeValue(), NAME_LENGTH));
					mp.setNameFull(nameValue.getNodeValue());
					mp.setId(eeElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
					mp.setStartTime(starttimeValue.getNodeValue());
					mp.setEndTime(stoptimeValue.getNodeValue());
					mp.setStatus(statusValue.getNodeValue());
					mp.setType(typeValue.getNodeValue());
					lt.add(mp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	// lay process cua STB trong group tai thoi diem refresh 27.2
	public static ArrayList<HashMap<String, String>> getInGroupStbProcess(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList sessionNl = eElement.getElementsByTagName("session").item(0).getChildNodes();
				Node sessionValue = (Node) sessionNl.item(0);

				NodeList statusonoffNl = eElement.getElementsByTagName("statusonof").item(0).getChildNodes();
				Node statusonoffValue = (Node) statusonoffNl.item(0);

				NodeList idGroupNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idGroupValue = (Node) idGroupNl.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList serinumberNl = eElement.getElementsByTagName("serinumber").item(0).getChildNodes();
				Node serinumberValue = (Node) serinumberNl.item(0);

				NodeList macNl = eElement.getElementsByTagName("mac").item(0).getChildNodes();
				Node macValue = (Node) macNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("urlserver").item(0).getChildNodes();
				Node urlValue = (Node) urlNl.item(0);

				NodeList savelocalfilemediaNl = eElement.getElementsByTagName("savelocalfilemedia").item(0).getChildNodes();
				Node savelocalfilemediaValue = (Node) savelocalfilemediaNl.item(0);

				NodeList savelocalfilestbNl = eElement.getElementsByTagName("savelocalfilestb").item(0).getChildNodes();
				Node savelocalfilestbValue = (Node) savelocalfilestbNl.item(0);

				// NodeList statusNl =
				// eElement.getElementsByTagName("status").item(0).getChildNodes();
				// Node statusValue = (Node) statusNl.item(0);
				// goi ham checkContentProcess(int id)

				NodeList rebootNl = eElement.getElementsByTagName("reboot").item(0).getChildNodes();
				Node rebootValue = (Node) rebootNl.item(0);

				NodeList reqNl = eElement.getElementsByTagName("req").item(0).getChildNodes();
				Node reqValue = (Node) reqNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("session", sessionValue.getNodeValue());
				mp.put("req", reqValue.getNodeValue());
				mp.put("statusonoff", statusonoffValue.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				mp.put("name", nameValue.getNodeValue());
				mp.put("idgroup", idGroupValue.getNodeValue());
				mp.put("serinumber", serinumberValue.getNodeValue());
				mp.put("mac", macValue.getNodeValue());
				mp.put("urlserver", urlValue.getNodeValue());

				mp.put("reboot", rebootValue.getNodeValue());

				mp.put("savelocalfilemedia", savelocalfilemediaValue.getNodeValue());
				mp.put("savelocalfilestb", savelocalfilestbValue.getNodeValue());
				// mp.put("status",
				// String.valueOf(checkContentProcess(eElement.getAttributeNode("id").getValue())));
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input: theo thu tu tang dan <Scheduledaily> <item dailyid='1'> <playlistid>3</playlistid> <playlistname>PlayList01_SJC</playlistname>
	 * <startdate>00:00:00</startdate> <stopdate>13:59:00</stopdate></item> <item dailyid='2'> <playlistid>3</playlistid>
	 * <playlistname>PlayList01_SJC</playlistname> <startdate>14:00:00</startdate> <stopdate>16:59:00</stopdate></item> <item dailyid='3'>
	 * <playlistid>3</playlistid> <playlistname>PlayList01_SJC</playlistname> <startdate>17:00:00</startdate>
	 * <stopdate>23:59:00</stopdate></item> </Scheduledaily>
	 */
	public static ArrayList<ObjectABOP> admingetScheduleDailyGroupMap(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList playlistnameNl = eElement.getElementsByTagName("playlistname").item(0).getChildNodes();
				Node playlistname = (Node) playlistnameNl.item(0);

				NodeList playlistidNl = eElement.getElementsByTagName("playlistid").item(0).getChildNodes();
				Node playlistid = (Node) playlistidNl.item(0);

				NodeList startdateNl = eElement.getElementsByTagName("startdate").item(0).getChildNodes();
				Node startdate = (Node) startdateNl.item(0);

				NodeList stopdateNl = eElement.getElementsByTagName("stopdate").item(0).getChildNodes();
				Node stopdate = (Node) stopdateNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setIdItem(eElement.getAttributeNode("dailyid").getValue());
				mp.setId(playlistid.getNodeValue());
				mp.setName(playlistname.getNodeValue());
				mp.setStartTime(startdate.getNodeValue());
				mp.setEndTime(stopdate.getNodeValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input: theo thu tu tang dan <Scheduledaily> <item dailyid='1'> <playlistid>3</playlistid> <playlistname>PlayList01_SJC</playlistname>
	 * <startdate>00:00:00</startdate> <stopdate>13:59:00</stopdate></item> <item dailyid='2'> <playlistid>3</playlistid>
	 * <playlistname>PlayList01_SJC</playlistname> <startdate>14:00:00</startdate> <stopdate>16:59:00</stopdate></item> <item dailyid='3'>
	 * <playlistid>3</playlistid> <playlistname>PlayList01_SJC</playlistname> <startdate>17:00:00</startdate>
	 * <stopdate>23:59:00</stopdate></item> </Scheduledaily>
	 */
	public static ArrayList<ObjectABOP> admingetScheduleDailyName(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node name = (Node) nameNl.item(0);
				
				NodeList nodeTime = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node time = (Node) nodeTime.item(0);
				
				NodeList descNl = eElement.getElementsByTagName("desc").item(0).getChildNodes();

				/*
				 * NodeList descNl = eElement.getElementsByTagName("desc").item(0) .getChildNodes(); >>>>>>> .r1147 Node desc = (Node)
				 * descNl.item(0);
				 */
				NodeList createdateNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) createdateNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setName(subString(name.getNodeValue(), NAME_LENGTH));
				mp.setNameFull(name.getNodeValue());
				/*
				 * mp.setNote(subString(desc.getNodeValue(), NOTE_LENGTH)); mp.setNoteFull(desc.getNodeValue());
				 */
				mp.setTime(time.getNodeValue());
				mp.setDate(createdate.getNodeValue());
				mp.setId(eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input: <Schedule> <item id='1'> <name>SchedulePlayList01</name> <starttime>2012-07-06</starttime> <stoptime>2012-07-06</stoptime>
	 * </item> </Schedule>
	 */
	public static ArrayList<ObjectABOP> admingetSchedulePeriodic(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node name = (Node) nameNl.item(0);
				
				NodeList nodeTime = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node time = (Node) nodeTime.item(0);
				
				NodeList startNl = eElement.getElementsByTagName("starttime").item(0).getChildNodes();
				Node start = (Node) startNl.item(0);

				NodeList stopNl = eElement.getElementsByTagName("stoptime").item(0).getChildNodes();
				Node stop = (Node) stopNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setName(subString(name.getNodeValue(), NAME_LENGTH));
				mp.setNameFull(name.getNodeValue());
				mp.setTime(time.getNodeValue());
				mp.setEndTime(stop.getNodeValue());
				mp.setStartTime(start.getNodeValue());
				mp.setId(eElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input: <Scheduledaily> <item id='1'> <name>Schduledaily01</name> <createdate>2012-07-21 16:32:23</createdate> </item> <item id='1'>
	 * <name>Schduledaily01</name> <createdate>2012-07-21 16:32:23</createdate> </item> <item id='41'> <name>1992</name>
	 * <createdate>2012-07-25 13:15:07</createdate> </item> </Scheduledaily>
	 */
	public static ArrayList<HashMap<String, String>> viewadmingetItemScheduleDailyForPerio(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList createdateList = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) createdateList.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();
				mp.put("name", nValue.getNodeValue());
				mp.put("createdate", createdate.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input <Content> <item id = '1'> <name>Content</name> <duration>1</typegroup> <typecontent>1</direction> </item> </Content>
	 */
	public static ArrayList<HashMap<String, String>> getContentListFollowTypeContent(String xmlstring, String typeContent, String type) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList typeContentNl = eElement.getElementsByTagName("typecontent").item(0).getChildNodes();
				Node typeContentValue = (Node) typeContentNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("url").item(0).getChildNodes();
				Node url = (Node) urlNl.item(0);

				NodeList fonttextNl = eElement.getElementsByTagName("fonttext").item(0).getChildNodes();
				Node fonttext = (Node) fonttextNl.item(0);

				NodeList sizetextNl = eElement.getElementsByTagName("sizetext").item(0).getChildNodes();
				Node sizetext = (Node) sizetextNl.item(0);

				NodeList directrollNl = eElement.getElementsByTagName("direcroll").item(0).getChildNodes();
				Node directroll = (Node) directrollNl.item(0);

				NodeList colorNl = eElement.getElementsByTagName("colortext").item(0).getChildNodes();
				Node colortext = (Node) colorNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				if (typeContent.equals(typeContentValue.getNodeValue())) {
					mp.put("name", nameValue.getNodeValue());
					mp.put("time", nValue.getNodeValue());
					mp.put("url", url.getNodeValue());
					if (type.equals("text")) {
						mp.put("fonttext", fonttext.getNodeValue());
						mp.put("sizetext", sizetext.getNodeValue());
						mp.put("directroll", directroll.getNodeValue());
						mp.put("colortext", colortext.getNodeValue());
					}
					mp.put("typecontent", typeContentValue.getNodeValue());
					mp.put("id", eElement.getAttributeNode("id").getValue());
					lt.add(mp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input <font color="yellow" face="courier" size="4">dsadasdasdas</font> output
	 * <parameter><nameContent></nameContent><urlContent></urlContent ><colorText
	 * ></colorText><fontText></fontText><sizeText></sizeText><direcRoll ></direcRoll></parameter>
	 */
	public static String xmladminNewContentText(String xStr, String name, String fontsize) {
		String temp = "<parameter>\r\n<nameContent></nameContent>\r\n<urlContent></urlContent>\r\n<colorText></colorText>\r\n<fontText></fontText>\r\n<sizeText></sizeText>\r\n<direcRoll></direcRoll>\r\n<typeContent>5</typeContent>\r\n</parameter>";
		String lam = xStr.substring(0, xStr.indexOf(">"));
		String color = "", font = "", size = "", url = "";
		String[] lamArr = lam.split(" ");
		for (int i = 0; i < lamArr.length; i++) {
			if (lamArr[i].indexOf("size") >= 0) {
				size = lamArr[i].substring(6, lamArr[i].length() - 1);
				System.out.println("size=" + size);
			}
			if (lamArr[i].indexOf("color") >= 0) {
				color = lamArr[i].substring(7, lamArr[i].length() - 1);
				color = color.replaceAll("#", "");
				System.out.println("color=" + color);
			}
			if (lamArr[i].indexOf("face") >= 0) {
				font = lamArr[i].substring(6, lamArr[i].length() - 1);
				System.out.println("face=" + font);
			}
		}
		url = xStr.substring(xStr.lastIndexOf("\">") + 2, xStr.indexOf("</font>"));
		String direct = "1";
		String result = temp.replaceAll("<nameContent></nameContent>", "<nameContent>" + name + "</nameContent>")
				.replaceAll("<urlContent></urlContent>", "<urlContent>" + url + "</urlContent>")
				.replaceAll("<colorText></colorText>", "<colorText>#" + color + "</colorText>")
				.replaceAll("<fontText></fontText>", "<fontText>" + font + "</fontText>")
				.replaceAll("<sizeText></sizeText>", "<sizeText>" + fontsize + "</sizeText>")
				.replaceAll("<direcRoll></direcRoll>", "<direcRoll>" + direct + "</direcRoll>");
		return result;
	}

	/*
	 * input <Content> <item> <contentid>6</contentid> <idlayout>103</idlayout> <contentname>web</idlayout>
	 * <contenturl><![CDATA[http://dantri.com]]></contenturl> <contenttype>6</contenttype> </item> <item> <contentid>27</contentid>
	 * <idlayout>104</idlayout> <contentname>VideoSJC03</idlayout> <contenturl><![CDATA[GioiThieuSJC_1.mp4]]></contenturl>
	 * <contenttype>1</contenttype> </item> </Content>
	 */
	public static ArrayList<HashMap<String, String>> getLayoutContainContent(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList contentidNl = eElement.getElementsByTagName("contentid").item(0).getChildNodes();
				Node contentid = (Node) contentidNl.item(0);

				NodeList idlayoutNl = eElement.getElementsByTagName("idlayout").item(0).getChildNodes();
				Node idlayout = (Node) idlayoutNl.item(0);

				NodeList contentnameNl = eElement.getElementsByTagName("contentname").item(0).getChildNodes();
				Node contentname = (Node) contentnameNl.item(0);

				NodeList contenturlNl = eElement.getElementsByTagName("contenturl").item(0).getChildNodes();
				Node url = (Node) contenturlNl.item(0);

				NodeList contenttypeNl = eElement.getElementsByTagName("contenttype").item(0).getChildNodes();
				Node contenttype = (Node) contenttypeNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("idlayout", idlayout.getNodeValue());
				mp.put("contentid", contentid.getNodeValue());
				mp.put("contentname", contentname.getNodeValue());
				mp.put("contenturl", url.getNodeValue());
				mp.put("contenttype", contenttype.getNodeValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * input <Config> <server_ftp>localhost</server_ftp> <server_port>21</server_port> <src_file>Src</src_file>
	 * <local_file>D:\tmp</local_file> <desc_host>localhost</desc_host> <serveruserftp>adcenter</serveruserftp>
	 * <serverpassftp>adcenter</serverpassftp> </Config>
	 */
	public static ArrayList<HashMap<String, String>> xmladmingetConfig(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("Config");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("server_ftp").item(0).getChildNodes();
				Node server_ftp = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("server_port").item(0).getChildNodes();
				Node server_port = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("src_file").item(0).getChildNodes();
				Node src_file = (Node) src_fileNl.item(0);

				NodeList local_fileNl = eElement.getElementsByTagName("local_file").item(0).getChildNodes();
				Node local_file = (Node) local_fileNl.item(0);

				NodeList desc_hostNl = eElement.getElementsByTagName("desc_host").item(0).getChildNodes();
				Node desc_host = (Node) desc_hostNl.item(0);

				NodeList serveruserftpNl = eElement.getElementsByTagName("serveruserftp").item(0).getChildNodes();
				Node serveruserftp = (Node) serveruserftpNl.item(0);

				NodeList serverpassftpNl = eElement.getElementsByTagName("serverpassftp").item(0).getChildNodes();
				Node serverpassftp = (Node) serverpassftpNl.item(0);

				// for abop db 19.9
				NodeList abophostNl = eElement.getElementsByTagName("abophost").item(0).getChildNodes();
				Node abophost = (Node) abophostNl.item(0);
				NodeList abopdbNl = eElement.getElementsByTagName("abopdb").item(0).getChildNodes();
				Node abopdb = (Node) abopdbNl.item(0);
				NodeList abopuserNl = eElement.getElementsByTagName("abopuser").item(0).getChildNodes();
				Node abopuser = (Node) abopuserNl.item(0);
				NodeList aboppwdNl = eElement.getElementsByTagName("aboppwd").item(0).getChildNodes();
				Node aboppwd = (Node) aboppwdNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				// them vai cai cau hinh cho usermanagement 19.9
				mp.put("abophost", abophost.getNodeValue());
				mp.put("abopdb", abopdb.getNodeValue());
				mp.put("abopuser", abopuser.getNodeValue());
				mp.put("aboppwd", aboppwd.getNodeValue());

				// cau hinh cho ftp
				mp.put("server_ftp", server_ftp.getNodeValue());
				mp.put("server_port", server_port.getNodeValue());
				mp.put("src_file", src_file.getNodeValue());
				mp.put("local_file", local_file.getNodeValue());
				mp.put("desc_host", desc_host.getNodeValue());
				mp.put("serveruserftp", serveruserftp.getNodeValue());
				mp.put("serverpassftp", serverpassftp.getNodeValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static String xmlLayRaCacGroupDacBiet(String xmlSrc, String danhsachcangiulai) {
		String xml = null;
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlSrc));

			doc = db.parse(is);
			// lay ra tung node item
			NodeList gnodes = doc.getElementsByTagName("Group");
			String[] danhsach = danhsachcangiulai.split(",");
			int sothutu = 0;
			for (int k = 0; k < gnodes.getLength(); k++) {
				Node gnNode = gnodes.item(k);
				Element geElement = (Element) gnNode;
				NodeList iNodeL = geElement.getElementsByTagName("item");
				Integer[] vitricanxoa = new Integer[iNodeL.getLength()];
				int flag = 0;
				for (int i = 0; i < iNodeL.getLength(); i++) {
					Node nNode = iNodeL.item(i);
					Element eElement = (Element) nNode;

					String id = eElement.getAttributeNode("id").getValue();
					// so sanh id tung node voi danhsachcangiulai, neu ko phai
					// thi remove khoi danh sach ban dau
					for (int j = 0; j < danhsach.length; j++) {
						if (!id.equals(danhsach[j])) {
							// neu ko giong 1 nut thi xet toan bo danhsach
							flag++;
						} else {
							// reset flag truoc khi thoat khoi loop
							flag = 0;
							break;
						}
						// neu kiem tra toan bo danh sach ma van ko khop thi
						// loai bo
						if (j == danhsach.length - 1 && flag == danhsach.length) {
							// eElement.getParentNode().removeChild(nNode);
							vitricanxoa[sothutu] = i;
							sothutu++;
							// reset lai flag de xem cac thang khac co du tieu
							// chuan ko
							flag = 0;
						}
					}
				}

				// xoa cac node trong danh sach
				int sizeB = vitricanxoa.length;
				for (int n = 0; n < vitricanxoa.length; n++) {
					if (vitricanxoa[n] == null) {
						sizeB = sizeB - 1;
					}
				}
				Node gnNode1 = gnodes.item(0);
				Element geElement1 = (Element) gnNode1;
				NodeList iNodeL1 = geElement1.getElementsByTagName("item");
				for (int n = sizeB - 1; n >= 0; n--) {
					Node nNode1 = iNodeL1.item(vitricanxoa[n]);
					gnNode1.removeChild(nNode1);
				}

			}

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			StringWriter writer = new StringWriter();
			Result result = new StreamResult(writer);
			Source source = new DOMSource(doc);
			transformer.transform(source, result);
			writer.close();
			xml = writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}

	/*
	 * <ReportList> <item idlayout="41"> <idgroup>52</idgroup> <namegroup>HaNoi_Group</namegroup> <namelayout>??i t�n TV</namelayout>
	 * <createdate>2012-08-08</createdate> </item> <item idlayout="42"> <idgroup>52</idgroup> <namegroup>HaNoi_Group</namegroup>
	 * <namelayout>LayOutHaNoi_Man2</namelayout> <createdate>2012-08-08</createdate> </item> </ReportList>
	 */
	public static ArrayList<HashMap<String, String>> layoutAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idgroup = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("namelayout").item(0).getChildNodes();
				Node namelayout = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) src_fileNl.item(0);

				NodeList idgroupNl = eElement.getElementsByTagName("namegroup").item(0).getChildNodes();
				Node namegroup = (Node) idgroupNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("idgroup", idgroup.getNodeValue());
				mp.put("namelayout", namelayout.getNodeValue());
				mp.put("createdate", createdate.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("idlayout", eElement.getAttributeNode("idlayout").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * <ReportList><item idplaylist="17"> <nameplaylist>1</nameplaylist> <nameitemplaylist>BieuDoGiaVang</nameitemplaylist>
	 * <starttime>00:00:00</starttime> <startstop>00:05:00</startstop> <createdate>2012-08-08</createdate>
	 * <namegroup>HaNoi_Group</namegroup> </item>
	 */
	public static ArrayList<HashMap<String, String>> playlistAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idgroup = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("nameplaylist").item(0).getChildNodes();
				Node nameplaylist = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) src_fileNl.item(0);

				NodeList starttimeNl = eElement.getElementsByTagName("starttime").item(0).getChildNodes();
				Node starttime = (Node) starttimeNl.item(0);
				NodeList startstopNl = eElement.getElementsByTagName("startstop").item(0).getChildNodes();
				Node startstop = (Node) startstopNl.item(0);

				NodeList idgroupNl = eElement.getElementsByTagName("namegroup").item(0).getChildNodes();
				Node namegroup = (Node) idgroupNl.item(0);

				NodeList nameitemplaylistNl = eElement.getElementsByTagName("nameitemplaylist").item(0).getChildNodes();
				Node nameitemplaylist = (Node) nameitemplaylistNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("idgroup", idgroup.getNodeValue());
				mp.put("nameplaylist", nameplaylist.getNodeValue());
				mp.put("nameitemplaylist", nameitemplaylist.getNodeValue());
				mp.put("createdate", createdate.getNodeValue());
				mp.put("starttime", starttime.getNodeValue());
				mp.put("startstop", startstop.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("idplaylist", eElement.getAttributeNode("idplaylist").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * 8.10 <ReportList> <item iddaily="45"> <namegroup>HaNoi_Group</namegroup> <namedaily>HaNoi</namedaily> <createdate>2012-08-07
	 * 09:06:47</createdate> <starttime>10:16:55</starttime> <stoptime>10:26:55</stoptime> </item> </ReportList>
	 */
	public static ArrayList<HashMap<String, String>> dailyAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("groupid").item(0).getChildNodes();
				Node idgroup = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("namedaily").item(0).getChildNodes();
				Node namedaily = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("createdate").item(0).getChildNodes();
				Node createdate = (Node) src_fileNl.item(0);

				NodeList starttimeNl = eElement.getElementsByTagName("starttime").item(0).getChildNodes();
				Node starttime = (Node) starttimeNl.item(0);
				NodeList startstopNl = eElement.getElementsByTagName("stoptime").item(0).getChildNodes();
				Node stoptime = (Node) startstopNl.item(0);

				NodeList idgroupNl = eElement.getElementsByTagName("namegroup").item(0).getChildNodes();
				Node namegroup = (Node) idgroupNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("idgroup", idgroup.getNodeValue());
				mp.put("namedaily", namedaily.getNodeValue());
				mp.put("createdate", createdate.getNodeValue());
				mp.put("starttime", starttime.getNodeValue());
				mp.put("stoptime", stoptime.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("iddaily", eElement.getAttributeNode("iddaily").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * <item idschedule="37"> <namegroup>Hcm_Group</namegroup> <nameschedule>HCM_Peri</nameschedule> <namedaily>HCM</namedaily>
	 * <startdate>2012-08-17</startdate> <stopdate>2012-08-31</stopdate> <groupid>53</groupid> </item>
	 */
	public static ArrayList<HashMap<String, String>> periAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("groupid").item(0).getChildNodes();
				Node idgroup = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("namedaily").item(0).getChildNodes();
				Node namedaily = (Node) server_portNl.item(0);

				NodeList starttimeNl = eElement.getElementsByTagName("startdate").item(0).getChildNodes();
				Node startdate = (Node) starttimeNl.item(0);
				NodeList startstopNl = eElement.getElementsByTagName("stopdate").item(0).getChildNodes();
				Node stopdate = (Node) startstopNl.item(0);

				NodeList idgroupNl = eElement.getElementsByTagName("namegroup").item(0).getChildNodes();
				Node namegroup = (Node) idgroupNl.item(0);

				NodeList namescheduleNl = eElement.getElementsByTagName("nameschedule").item(0).getChildNodes();
				Node nameschedule = (Node) namescheduleNl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("idgroup", idgroup.getNodeValue());
				mp.put("namedaily", namedaily.getNodeValue());
				mp.put("nameschedule", nameschedule.getNodeValue());
				mp.put("starttime", startdate.getNodeValue());
				mp.put("stoptime", stopdate.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("idschedule", eElement.getAttributeNode("idschedule").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	/*
	 * <item id='4'> <idgroup>-1</idgroup>> <groupname>Group khoitao</groupname>> <name>Set top box 0412_HCM</name>
	 * <serinumber>00:e0:4c:b4:1f:54</serinumber> <mac>00:e0:4c:b4:1f:54</mac> <session>0</session> <urlserver>kobiet</urlserver>
	 * <savelocalfilemedia>kobiet</savelocalfilemedia> <savelocalfilestb>kobiet</savelocalfilestb> <statusonof>Inactive</statusonof>
	 * <status>17</status> <req>14:54:44</req> </item> </item>
	 */
	public static ArrayList<HashMap<String, String>> stbAll(String xmlstring) {
		ArrayList<HashMap<String, String>> lt = new ArrayList<HashMap<String, String>>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("idgroup").item(0).getChildNodes();
				Node idgroup = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node name = (Node) server_portNl.item(0);

				NodeList idgroupNl = eElement.getElementsByTagName("groupname").item(0).getChildNodes();
				Node namegroup = (Node) idgroupNl.item(0);

				NodeList serinumberNl = eElement.getElementsByTagName("serinumber").item(0).getChildNodes();
				Node serinumber = (Node) serinumberNl.item(0);
				NodeList macNl = eElement.getElementsByTagName("mac").item(0).getChildNodes();
				Node mac = (Node) macNl.item(0);
				NodeList sessionNl = eElement.getElementsByTagName("session").item(0).getChildNodes();
				Node session = (Node) sessionNl.item(0);
				NodeList urlserverNl = eElement.getElementsByTagName("urlserver").item(0).getChildNodes();
				Node urlserver = (Node) urlserverNl.item(0);

				NodeList savelocalfilemedianl = eElement.getElementsByTagName("savelocalfilemedia").item(0).getChildNodes();
				Node savelocalfilemedia = (Node) savelocalfilemedianl.item(0);
				NodeList savelocalfilestbnl = eElement.getElementsByTagName("savelocalfilestb").item(0).getChildNodes();
				Node savelocalfilestb = (Node) savelocalfilestbnl.item(0);
				NodeList statusonofnl = eElement.getElementsByTagName("statusonof").item(0).getChildNodes();
				Node statusonof = (Node) statusonofnl.item(0);
				NodeList statusnl = eElement.getElementsByTagName("status").item(0).getChildNodes();
				Node status = (Node) statusnl.item(0);
				NodeList reqnl = eElement.getElementsByTagName("req").item(0).getChildNodes();
				Node req = (Node) reqnl.item(0);

				HashMap<String, String> mp = new HashMap<String, String>();

				mp.put("savelocalfilemedia", savelocalfilemedia.getNodeValue());
				mp.put("savelocalfilestb", savelocalfilestb.getNodeValue());
				mp.put("statusonof", statusonof.getNodeValue());
				mp.put("status", status.getNodeValue());
				mp.put("req", req.getNodeValue());
				mp.put("urlserver", urlserver.getNodeValue());
				mp.put("session", session.getNodeValue());
				mp.put("mac", mac.getNodeValue());
				mp.put("serinumber", serinumber.getNodeValue());
				mp.put("idgroup", idgroup.getNodeValue());
				mp.put("name", name.getNodeValue());
				mp.put("namegroup", namegroup.getNodeValue());
				mp.put("id", eElement.getAttributeNode("id").getValue());
				lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getAllContentText(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList typeContentNl = eElement.getElementsByTagName("typecontent").item(0).getChildNodes();
				Node typeContentValue = (Node) typeContentNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("url").item(0).getChildNodes();
				Node url = (Node) urlNl.item(0);

				NodeList dessNl = eElement.getElementsByTagName("desstbfile").item(0).getChildNodes();
				Node dess = (Node) dessNl.item(0);

				ObjectABOP o = new ObjectABOP();
				o.setId(eElement.getAttributeNode("id").getValue());
				o.setName(nameValue.getNodeValue());
				if (typeContentValue.getNodeValue().equalsIgnoreCase("5")) {
					o.setUrl(UnicodeConverter.decodeUnicode(url.getNodeValue()));
				} else {
					o.setUrl(url.getNodeValue());
				}
				o.setTime(nValue.getNodeValue());
				o.setType(typeContentValue.getNodeValue());
				if (typeContentValue.getNodeValue().equalsIgnoreCase("5")) {
					NodeList fonttextNl = eElement.getElementsByTagName("fonttext").item(0).getChildNodes();
					Node fonttext = (Node) fonttextNl.item(0);

					NodeList sizetextNl = eElement.getElementsByTagName("sizetext").item(0).getChildNodes();
					Node sizetext = (Node) sizetextNl.item(0);

					NodeList directrollNl = eElement.getElementsByTagName("direcroll").item(0).getChildNodes();
					Node directroll = (Node) directrollNl.item(0);

					NodeList colorNl = eElement.getElementsByTagName("colortext").item(0).getChildNodes();
					Node colortext = (Node) colorNl.item(0);
					o.setColor(colortext.getNodeValue());
					o.setFont(fonttext.getNodeValue());
					o.setSize(sizetext.getNodeValue());
					o.setDirecroll(directroll.getNodeValue());
				}

				o.setDesstbfile(dess.getNodeValue());
				lt.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getAllContentMedia(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList nlList = eElement.getElementsByTagName("time").item(0).getChildNodes();
				Node nValue = (Node) nlList.item(0);

				NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList typeContentNl = eElement.getElementsByTagName("typecontent").item(0).getChildNodes();
				Node typeContentValue = (Node) typeContentNl.item(0);

				NodeList urlNl = eElement.getElementsByTagName("url").item(0).getChildNodes();
				Node url = (Node) urlNl.item(0);
				ObjectABOP o = new ObjectABOP();
				o.setId(eElement.getAttributeNode("id").getValue());
				o.setName(UtilBasic.decodeURIComponent(nameValue.getNodeValue()));

				if (o.getId().equalsIgnoreCase("5")) {
					o.setUrl(UtilBasic.decodeURIComponent(url.getNodeValue()));
				} else {
					o.setUrl(url.getNodeValue());
				}
				o.setTime(nValue.getNodeValue());
				o.setType(typeContentValue.getNodeValue());
				lt.add(o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> getPlayListLayouItem(String xMLRESULT) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xMLRESULT));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("List");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList itemNl = eElement.getElementsByTagName("item");
				for (int j = 0; j < itemNl.getLength(); j++) {
					Node nnNode = itemNl.item(j);
					Element eeElement = (Element) nnNode;

					NodeList nameNl = eeElement.getElementsByTagName("name").item(0).getChildNodes();
					Node nameValue = (Node) nameNl.item(0);

					NodeList starttimeNl = eeElement.getElementsByTagName("starttime").item(0).getChildNodes();
					Node starttimeValue = (Node) starttimeNl.item(0);

					NodeList stoptimeNl = eeElement.getElementsByTagName("stoptime").item(0).getChildNodes();
					Node stoptimeValue = (Node) stoptimeNl.item(0);

					NodeList typeNl = eeElement.getElementsByTagName("typecontent").item(0).getChildNodes();
					Node typeValue = (Node) typeNl.item(0);

					NodeList layoutidNl = eeElement.getElementsByTagName("layoutid").item(0).getChildNodes();
					Node layoutid = (Node) layoutidNl.item(0);

					NodeList layoutitemidNl = eeElement.getElementsByTagName("layoutitemid").item(0).getChildNodes();
					Node layoutitemid = (Node) layoutitemidNl.item(0);

					NodeList contentIdNl = eeElement.getElementsByTagName("contentid").item(0).getChildNodes();
					Node contentId = (Node) contentIdNl.item(0);

					NodeList fontNode = eeElement.getElementsByTagName("font").item(0).getChildNodes();
					Node font = (Node) fontNode.item(0);

					NodeList urlContentNode = eeElement.getElementsByTagName("urlcontent").item(0).getChildNodes();
					Node urlContent = (Node) urlContentNode.item(0);

					NodeList sizeNode = eeElement.getElementsByTagName("size").item(0).getChildNodes();
					Node size = (Node) sizeNode.item(0);

					NodeList colorNode = eeElement.getElementsByTagName("color").item(0).getChildNodes();
					Node color = (Node) colorNode.item(0);

					ObjectABOP mp = new ObjectABOP();
					mp.setName(nameValue.getNodeValue());
					// set id of layout
					mp.setIdParent(layoutid.getNodeValue());
					// set id of content
					mp.setId(contentId.getNodeValue());
					// set id sub of layout(layout item)
					mp.setIdSubLayout(layoutitemid.getNodeValue());
					// set id item of layout
					mp.setIdItem(eeElement.getAttributeNode(ApplyItemConstant.KEY_ID).getValue());

					mp.setStartTime(starttimeValue.getNodeValue());
					mp.setEndTime(stoptimeValue.getNodeValue());
					mp.setType(UtilBasic.convertFileType(typeValue.getNodeValue()));
					if (mp.getType() == "Text") {
						mp.setUrl(UtilBasic.decodeURIComponent(urlContent.getNodeValue()));
						// only text
						mp.setColor(color.getNodeValue());
						mp.setFont(font.getNodeValue());
						mp.setSize(size.getNodeValue());
					} else {
						mp.setUrl(urlContent.getNodeValue());
					}

					lt.add(mp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}
	
	public static ArrayList<ObjectABOP> adminGetSubjectByUser(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList idNl = eElement.getElementsByTagName("subjectid").item(0).getChildNodes();
				Node idValue = (Node) idNl.item(0);

				NodeList nameNl = eElement.getElementsByTagName("subjectname").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList descriptionNl = eElement.getElementsByTagName("subjectdescription").item(0).getChildNodes();
				Node descriptionValue = (Node) descriptionNl.item(0);

				NodeList typeNl = eElement.getElementsByTagName("subjecttype").item(0).getChildNodes();
				Node typeValue = (Node) typeNl.item(0);

				NodeList statusNl = eElement.getElementsByTagName("subjectstatus").item(0).getChildNodes();
				Node statusValue = (Node) statusNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setId(idValue.getNodeValue());
				mp.setName(nameValue.getNodeValue());
				mp.setNameFull(subString(nameValue.getNodeValue(), NAME_LENGTH));
				mp.setNote(descriptionValue.getNodeValue());
				mp.setType(typeValue.getNodeValue());
				mp.setStatus(statusValue.getNodeValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> adminGetAllSubjectContent(String xmlstring) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList idNl = eElement.getElementsByTagName("subjectid").item(0).getChildNodes();
				Node idValue = (Node) idNl.item(0);

				NodeList nameNl = eElement.getElementsByTagName("subjectname").item(0).getChildNodes();
				Node nameValue = (Node) nameNl.item(0);

				NodeList descriptionNl = eElement.getElementsByTagName("subjectdescription").item(0).getChildNodes();
				Node descriptionValue = (Node) descriptionNl.item(0);

				NodeList typeNl = eElement.getElementsByTagName("subjecttype").item(0).getChildNodes();
				Node typeValue = (Node) typeNl.item(0);

				NodeList statusNl = eElement.getElementsByTagName("subjectstatus").item(0).getChildNodes();
				Node statusValue = (Node) statusNl.item(0);

				ObjectABOP mp = new ObjectABOP();
				mp.setId(idValue.getNodeValue());
				mp.setName(nameValue.getNodeValue());
				mp.setNameFull(subString(nameValue.getNodeValue(), NAME_LENGTH));
				mp.setNote(descriptionValue.getNodeValue());
				mp.setType(typeValue.getNodeValue());
				mp.setStatus(statusValue.getNodeValue());
				lt.add(mp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ObjectConfig parseXMLConfig(String xmlstring) {
		ObjectConfig obj = new ObjectConfig();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("Config");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;

				NodeList server_ftpNl = eElement.getElementsByTagName("server_ftp").item(0).getChildNodes();
				Node server_ftp = (Node) server_ftpNl.item(0);

				NodeList server_portNl = eElement.getElementsByTagName("server_port").item(0).getChildNodes();
				Node server_port = (Node) server_portNl.item(0);

				NodeList src_fileNl = eElement.getElementsByTagName("src_file").item(0).getChildNodes();
				Node src_file = (Node) src_fileNl.item(0);

				NodeList local_fileNl = eElement.getElementsByTagName("local_file").item(0).getChildNodes();
				Node local_file = (Node) local_fileNl.item(0);

				NodeList desc_hostNl = eElement.getElementsByTagName("desc_host").item(0).getChildNodes();
				Node desc_host = (Node) desc_hostNl.item(0);

				NodeList serveruserftpNl = eElement.getElementsByTagName("serveruserftp").item(0).getChildNodes();
				Node serveruserftp = (Node) serveruserftpNl.item(0);

				NodeList serverpassftpNl = eElement.getElementsByTagName("serverpassftp").item(0).getChildNodes();
				Node serverpassftp = (Node) serverpassftpNl.item(0);

				// for abop db 19.9
				// NodeList abophostNl = eElement.getElementsByTagName("abophost").item(0).getChildNodes();
				// Node abophost = (Node) abophostNl.item(0);
				// NodeList abopdbNl = eElement.getElementsByTagName("abopdb").item(0).getChildNodes();
				// Node abopdb = (Node) abopdbNl.item(0);
				// NodeList abopuserNl = eElement.getElementsByTagName("abopuser").item(0).getChildNodes();
				// Node abopuser = (Node) abopuserNl.item(0);
				// NodeList aboppwdNl = eElement.getElementsByTagName("aboppwd").item(0).getChildNodes();
				// Node aboppwd = (Node) aboppwdNl.item(0);

				// them vai cai cau hinh cho usermanagement 19.9
				// mp.put("abophost", abophost.getNodeValue());
				// mp.put("abopdb", abopdb.getNodeValue());
				// mp.put("abopuser", abopuser.getNodeValue());
				// mp.put("aboppwd", aboppwd.getNodeValue());
				//
				// // cau hinh cho ftp
				// mp.put("server_ftp", server_ftp.getNodeValue());
				// mp.put("server_port", server_port.getNodeValue());
				// mp.put("src_file", src_file.getNodeValue());
				// mp.put("local_file", local_file.getNodeValue());
				// mp.put("desc_host", desc_host.getNodeValue());
				// mp.put("serveruserftp", serveruserftp.getNodeValue());
				// mp.put("serverpassftp", serverpassftp.getNodeValue());
				obj.setServer_ftp(server_ftp.getNodeValue());
				obj.setServer_port(server_port.getNodeValue());
				obj.setSrc_file(src_file.getNodeValue());
				obj.setLocal_file(local_file.getNodeValue());
				obj.setDesc_host(desc_host.getNodeValue());
				obj.setServeruserftp(serveruserftp.getNodeValue());
				obj.setServerpassftp(serverpassftp.getNodeValue());
				// lt.add(mp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static ArrayList<ObjectABOP> getContentText(String xmlstring, String id) {
		ArrayList<ObjectABOP> lt = new ArrayList<ObjectABOP>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(xmlstring));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");
			for (int i = 0; i < nodes.getLength(); i++) {
				Node nNode = nodes.item(i);
				Element eElement = (Element) nNode;
				if (eElement.getAttributeNode("id").getValue() == id) {

					NodeList nlList = eElement.getElementsByTagName("time").item(0).getChildNodes();
					Node nValue = (Node) nlList.item(0);

					NodeList nameNl = eElement.getElementsByTagName("name").item(0).getChildNodes();
					Node nameValue = (Node) nameNl.item(0);

					NodeList typeContentNl = eElement.getElementsByTagName("typecontent").item(0).getChildNodes();
					Node typeContentValue = (Node) typeContentNl.item(0);

					NodeList urlNl = eElement.getElementsByTagName("url").item(0).getChildNodes();
					Node url = (Node) urlNl.item(0);

					NodeList dessNl = eElement.getElementsByTagName("desstbfile").item(0).getChildNodes();
					Node dess = (Node) dessNl.item(0);

					NodeList fonttextNl = eElement.getElementsByTagName("fonttext").item(0).getChildNodes();
					Node fonttext = (Node) fonttextNl.item(0);

					NodeList sizetextNl = eElement.getElementsByTagName("sizetext").item(0).getChildNodes();
					Node sizetext = (Node) sizetextNl.item(0);

					NodeList directrollNl = eElement.getElementsByTagName("direcroll").item(0).getChildNodes();
					Node directroll = (Node) directrollNl.item(0);

					NodeList colorNl = eElement.getElementsByTagName("colortext").item(0).getChildNodes();
					Node colortext = (Node) colorNl.item(0);
					ObjectABOP o = new ObjectABOP();
					o.setId(eElement.getAttributeNode("id").getValue());
					o.setName(decodeURIComponent(nameValue.getNodeValue()));
					o.setUrl(UtilBasic.decodeURIComponent(url.getNodeValue()));
					o.setTime(nValue.getNodeValue());
					o.setType(typeContentValue.getNodeValue());

					o.setColor(colortext.getNodeValue());
					o.setFont(fonttext.getNodeValue());
					o.setSize(sizetext.getNodeValue());
					o.setDirecroll(directroll.getNodeValue());

					o.setDesstbfile(dess.getNodeValue());
					lt.add(o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lt;
	}

	public static ArrayList<ObjectABOP> adminGetContentIDByForSlide(String xmlstring) throws Exception {
		ArrayList<ObjectABOP> list = new ArrayList<ObjectABOP>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlstring));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node nNode = nodes.item(i);
			Element eElement = (Element) nNode;

			NodeList timeN = eElement.getElementsByTagName("slidetime").item(0).getChildNodes();
			Node timeV = (Node) timeN.item(0);

			NodeList orderN = eElement.getElementsByTagName("slideorder").item(0).getChildNodes();
			Node orderV = (Node) orderN.item(0);
			
			NodeList img = eElement.getElementsByTagName("urlimage").item(0).getChildNodes();
			Node imgN = (Node) img.item(0);

			ObjectABOP o = new ObjectABOP();
			o.setId(eElement.getAttributeNode("id").getValue());
			o.setTime(timeV.getNodeValue());
			o.setType(orderV.getNodeValue());
			o.setName(imgN.getNodeValue());
			list.add(o);
		}
		return list;
	}

	public static ArrayList<User> abopGetListUser(String xmlstring) throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xmlstring));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("item");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node nNode = nodes.item(i);
			Element eElement = (Element) nNode;

			NodeList usernameNode = eElement.getElementsByTagName("username").item(0).getChildNodes();
			Node username = (Node) usernameNode.item(0);

			NodeList roleNode = eElement.getElementsByTagName("role").item(0).getChildNodes();
			Node role = (Node) roleNode.item(0);

			NodeList statusNode = eElement.getElementsByTagName("status").item(0).getChildNodes();
			Node status = (Node) statusNode.item(0);

			ArrayList<ObjectABOP> stbs = new ArrayList<ObjectABOP>();
			try {
				NodeList stbNode = eElement.getElementsByTagName("stb");
				for(int j = 0; j < stbNode.getLength(); j++){
					ObjectABOP stb = new ObjectABOP();
					Node node = (Node) stbNode.item(j);
					Element el = (Element) node;
					stb.setId(el.getElementsByTagName("id").item(0).getTextContent());
					stb.setName(el.getElementsByTagName("name").item(0).getTextContent());
					stbs.add(stb);
				}
			} catch(Exception e){}
			User o = new User();
			o.setUsername(username.getNodeValue());
			o.setRole(role.getNodeValue());
			o.setStatus(status.getNodeValue());
			o.setStb(stbs);
			list.add(o);
		}
		return list;
	}

	public static ArrayList<ObjectABOP> abopGetRole(String result) throws ParserConfigurationException, SAXException, IOException {
		ArrayList<ObjectABOP> list = new ArrayList<ObjectABOP>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(result));

		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("role");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node nNode = nodes.item(i);
			Element eElement = (Element) nNode;

			NodeList idNode = eElement.getElementsByTagName("id").item(0).getChildNodes();
			Node id = (Node) idNode.item(0);

			NodeList valueNode = eElement.getElementsByTagName("value").item(0).getChildNodes();
			Node value = (Node) valueNode.item(0);

			ObjectABOP o = new ObjectABOP();
			o.setId(id.getNodeValue());
			o.setName(value.getNodeValue());
			list.add(o);
		}
		return list;
	}

	public static User getUser(String result) {
		User user = new User();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			InputSource is = new InputSource();
			is.setCharacterStream(new StringReader(result));

			Document doc = db.parse(is);
			NodeList nodes = doc.getElementsByTagName("user");
			Node nNode = nodes.item(0);
			Element eElement = (Element) nNode;
			user.setRole(eElement
					.getElementsByTagName("role").item(0).getTextContent());
			user.setCreator(eElement
					.getElementsByTagName("creator").item(0).getTextContent());
			user.setUsername(eElement
					.getElementsByTagName("creator").item(0).getTextContent());
			user.setParentCreator(eElement
					.getElementsByTagName("parentcreator").item(0).getTextContent());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (SAXException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		
		return user;
	}

	public static String getGrouId(String result) {
		if (!result.isEmpty()) {
			result = result.replace("<role>", "").replace("</role>", "");
			String[] tmp = result.split(";");
			if (tmp.length > 0) {
				return tmp[tmp.length - 1];
			}
		}
		return "-1";
	}

}