package elcom.abop.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import elcom.abop.bean.ObjectSchedule;
import elcom.abop.common.ApplyItemConstant;

public class Task<T> {

	private synchronized Document readXMLFile(String urlFile)
			throws ParserConfigurationException, SAXException, IOException {
		File fXmlFile = new File(urlFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		return doc;
	}

	private synchronized boolean saveXMLFile(Document doc, String filePath) {
		// write the content into xml file
		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filePath));
			transformer.transform(source, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			return false;
		} catch (TransformerException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public synchronized ArrayList<ObjectSchedule> readItem() {
		ArrayList<ObjectSchedule> result = new ArrayList<ObjectSchedule>();
		try {
			File f = new File(ApplyItemConstant.KEY_PATH_XML_FILE);
			if (!f.exists()) {
				return result;
			}
			Document doc = readXMLFile(ApplyItemConstant.KEY_PATH_XML_FILE);
			NodeList nList = doc.getElementsByTagName("item");
			int length = nList.getLength();
			for (int i = 0; i < length; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ObjectSchedule schedule = new ObjectSchedule();
					schedule.setTimer(Integer.parseInt(eElement.getAttribute("timer")));
					schedule.setIdPeriodic(eElement.getAttribute("idperiodic"));
					schedule.setIdDaily(eElement.getAttribute("iddaily"));
					schedule.setIdGroup(eElement.getAttribute("idgroup"));
					result.add(schedule);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public synchronized boolean addNewItem(ObjectSchedule schedule) {
		try {
			Document doc = readXMLFile(ApplyItemConstant.KEY_PATH_XML_FILE);
			Element root = doc.getDocumentElement();
			Element item = doc.createElement("item");
			
			// create attribute
			Attr attrId = doc.createAttribute("idperiodic");
			attrId.setValue(schedule.getIdPeriodic());
			item.setAttributeNode(attrId);
			
			attrId = doc.createAttribute("timer");
			attrId.setValue(String.valueOf(schedule.getTimer()));
			item.setAttributeNode(attrId);
			
			attrId = doc.createAttribute("iddaily");
			attrId.setValue(schedule.getIdDaily());
			item.setAttributeNode(attrId);
			
			attrId = doc.createAttribute("idgroup");
			attrId.setValue(schedule.getIdGroup());
			item.setAttributeNode(attrId);
			
			root.appendChild(item);
			
			return saveXMLFile(doc, ApplyItemConstant.KEY_PATH_XML_FILE);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public synchronized boolean removeItem(String id) throws Exception {
		try {
			Document doc = readXMLFile(ApplyItemConstant.KEY_PATH_XML_FILE);
			NodeList nList = doc.getElementsByTagName("item");
			int length = nList.getLength();
			for (int i = 0; i < length; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if (id.equals(eElement.getAttribute("idperiodic"))) {
						// remove node
						eElement.getParentNode().removeChild(eElement);
						break;
					}
				}
			}
			return saveXMLFile(doc, ApplyItemConstant.KEY_PATH_XML_FILE);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public synchronized boolean updateItem(ObjectSchedule schedule) {
		try {
			Document doc = readXMLFile(ApplyItemConstant.KEY_PATH_XML_FILE);
			NodeList nList = doc
					.getElementsByTagName("item");
			int length = nList.getLength();
			for (int i = 0; i < length; i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String id = schedule.getIdPeriodic();
					if (id.equals(eElement
							.getAttribute("idperiodic"))) {
						eElement.setAttribute("timer", String.valueOf(schedule.getTimer()));
						eElement.setAttribute("iddaily", schedule.getIdDaily());
						eElement.setAttribute("idgroup", schedule.getIdGroup());
					}
				}
			}
			return saveXMLFile(doc, ApplyItemConstant.KEY_PATH_XML_FILE);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public synchronized void insert(ObjectSchedule obj){
		ArrayList<ObjectSchedule> list = readItem();
		int length = list.size();
		boolean flag = false;
		if( length == 0){
			System.out.println("add into file");
			addNewItem(obj);
		} else {
			for(int i = 0; i < length; i++){
				if(list.get(i).getIdPeriodic().equals(obj.getIdPeriodic())){
					flag = true;
					break;
				}
			}
			if(flag){
				System.out.println("update item");
				updateItem(obj);
			} else {
				System.out.println("add new file");
				addNewItem(obj);
			}
		}
	}
}
