package com.elcom.adcenter.xml;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class eAutoXML {
	protected XMLOutputFactory factory;
	protected String xmlFile;
	protected String productName;
	protected XMLStreamWriter writer;

	public eAutoXML(String productName, String fileName) {
		try {
			factory = XMLOutputFactory.newInstance();
			this.xmlFile = fileName;
			this.productName = productName;
			writer = factory.createXMLStreamWriter(new FileWriter(xmlFile));
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void init_xml() {
		try {
			writer.writeStartDocument("utf-8", "1.0");
			writer.setPrefix("elcom", "http://www.w3.org/TR/REC-html40");
			writer.writeStartElement("http://www.w3.org/TR/REC-html40", "title");
			writer.writeCharacters(productName + " copyright ELCOM-HCM");
			writer.writeEndElement();

			writer.writeStartElement(productName);
			writer.writeAttribute("version", "1.0");
			writer.writeAttribute("code", "A0002");

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void end_xml() {
		try {
			writer.writeEndDocument();
			writer.flush();
			writer.close();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void export_xml(Vector<XMLData[]> values) {
		try {
			for (int i = 0; i < values.size(); i++) {
				XMLData[] data_arr = (XMLData[]) values.get(i);
				for (XMLData item : data_arr) {
					if (item.isAttribute()) {
						writer.writeStartElement("item");
						writer.writeAttribute(item.getTagName(),
								item.getTagValue());

					} else {
						writer.writeStartElement(item.getTagName());
						writer.writeCData(item.getTagValue());
						writer.writeEndElement();
					}
				}
				writer.writeEndElement();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		eAutoXML xml = new eAutoXML("adCenter", "demo.xml");
		String[] tags = new String[] { "EN", "VN", "Demo" };

		Vector<XMLData[]> vXML = new Vector<XMLData[]>();

		XMLData[] xmldata_arr = new XMLData[] {
				new XMLData("id", "1", true, true),
				new XMLData("EN", "Tay Du Ki", true, false),
				new XMLData("VN", "TDK", true, false),
				new XMLData("Demo", "description", true, false) };

		vXML.add(xmldata_arr);

		xmldata_arr = new XMLData[] { new XMLData("id", "2", true, true),
				new XMLData("EN", "Vong cau ty", true, false),
				new XMLData("VN", "TDK", true, false),
				new XMLData("Demo", "description", true, false),
				new XMLData("huu", "huuln", true, false), };

		vXML.add(xmldata_arr);

		xml.init_xml();
		xml.export_xml(vXML);
		xml.end_xml();

	}
}
