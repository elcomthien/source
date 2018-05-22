package elcom.abop.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import elcom.abop.bean.ObjectQMS;

public class ReadWriteFileUtil {
	public static boolean WriteFile(String pathFile, String text, boolean overwrite) {
		System.out.println("write file: " + pathFile);
		try {
			String oldText = "";
			if (!overwrite) {
				oldText = ReadFile(pathFile);
			}
			FileOutputStream fileOutStream = new FileOutputStream(pathFile);
			Writer writer = new OutputStreamWriter(fileOutStream, "Unicode");
			writer.write(oldText + text);
			writer.close();
			return true;
		} catch (IOException io) {
			System.out.println("Khong ghi duoc file" + pathFile);
		}
		return false;
	}

	public static String ReadFile(String pathFile) {
		System.out.println("Read file: " + pathFile);
		try {
			FileInputStream fileInPutStream = new FileInputStream(pathFile);
			Reader reader = new java.io.InputStreamReader(fileInPutStream, "Unicode");
			BufferedReader buffReader = new BufferedReader(reader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = buffReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}

			reader.close();
			return stringBuilder.toString();
		} catch (IOException io) {
			System.out.println("Khong tim thay file " + pathFile);
			io.printStackTrace();
		}
		return "";
	}
	
	public static ArrayList<ObjectQMS> readFileXML(String path){
		ArrayList<ObjectQMS> list = new ArrayList<ObjectQMS>();
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("item");
			ObjectQMS obj = new ObjectQMS();
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String id = eElement.getAttribute("id");
					String col = eElement.getAttribute("col");
					if(id.equals("header")){
						obj.setBgheader(eElement.getElementsByTagName("background").item(0).getTextContent());
						obj.setClheader(eElement.getElementsByTagName("color").item(0).getTextContent());
						obj.setFont(eElement.getElementsByTagName("font").item(0).getTextContent());
						obj.setFzheader(eElement.getElementsByTagName("fontsize").item(0).getTextContent());
						obj.setPosition(eElement.getAttribute("pos"));
					}else if(col.equals("chan")){
						obj.setBgbodyeven(eElement.getElementsByTagName("background").item(0).getTextContent());
						obj.setClbody(eElement.getElementsByTagName("color").item(0).getTextContent());
						obj.setFzbody(eElement.getElementsByTagName("fontsize").item(0).getTextContent());
					}else if(col.equals("le")){
						obj.setBgbodyodd(eElement.getElementsByTagName("background").item(0).getTextContent());
					}
				}
			}
			list.add(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}