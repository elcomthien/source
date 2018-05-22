package com.elcom.adcenter.rvcadv.util;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathReader {
	private String xmlString;
	private Document xmlDocument;
	private XPath xPath;

	public XPathReader(String xmlString) {
		this.xmlString = xmlString;
		initObjects();
	}

	private void initObjects() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputSource is = new InputSource(new StringReader(xmlString));
		    xmlDocument =  builder.parse(is);
			xPath = XPathFactory.newInstance().newXPath();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (SAXException ex) {
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		}
	}

	public Object read(String expression, QName returnType) {
		try {
			XPathExpression xPathExpression = xPath.compile(expression);
			return xPathExpression.evaluate(xmlDocument, returnType);
		} catch (XPathExpressionException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		XPathReader reader = new XPathReader("Config/Layout.xml");

		NodeList nodes = (NodeList) reader.read("/Layout/item",
				XPathConstants.NODESET);
		for (int i = 0; i < nodes.getLength(); i++) {
			Node workflow = (Node) nodes.item(i);
			NodeList name = workflow.getChildNodes();					
			System.out.println(name.item(1).getTextContent());
			System.out.println(name.item(3).getTextContent());
			System.out.println(name.item(5).getTextContent());
			System.out.println(name.item(7).getTextContent());
			System.out.println(name.item(9).getTextContent());	
			System.out.println(name.item(11).getTextContent());
			System.out.println(name.item(13).getTextContent());
			System.out.println(name.item(15).getTextContent());
			System.out.println(name.item(17).getTextContent());
			
			;
		}
		
	}
}
