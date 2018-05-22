package elcom.abop.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestClass {
	public static void main(String[] args) {
		try {

			File fXmlFile = new File("D:\\data\\configQMS.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :"
					+ doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("item");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("id : " + eElement.getAttribute("id"));
					System.out.println("col : " + eElement.getAttribute("col"));
					System.out.println("background : "
							+ eElement.getElementsByTagName("background")
									.item(0).getTextContent());
					System.out.println("color : "
							+ eElement.getElementsByTagName("color").item(0)
									.getTextContent());
					System.out.println("font : "
							+ eElement.getElementsByTagName("font").item(0)
									.getTextContent());
					System.out.println("fontsize : "
							+ eElement.getElementsByTagName("fontsize").item(0)
									.getTextContent());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
