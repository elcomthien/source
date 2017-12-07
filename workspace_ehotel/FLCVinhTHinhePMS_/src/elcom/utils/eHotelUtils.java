package elcom.utils;

import java.io.InputStream;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ebop.com.xpath.XPathReader;

public class eHotelUtils {
	public static String getPathOfService(InputStream stream, String service,
			String android) {
		String path = null;
		XPathReader reader = new XPathReader(stream);
		NodeList nodes = (NodeList) reader.read("/ehotel/item[@code='"
				+ service + "']", XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {
			Element workflow = (Element) nodes.item(i);
			if (android != null && android.substring(0, 1).equals("4"))
				path = workflow.getAttribute("path4");
			else
				path = workflow.getAttribute("path");
		}
		return path;
	}

	public static String getVersionOfService(InputStream stream,
			String service, String android) {
		String path = null;
		XPathReader reader = new XPathReader(stream);
		NodeList nodes = (NodeList) reader.read("/ehotel/item[@code='"
				+ service + "']", XPathConstants.NODESET);

		for (int i = 0; i < nodes.getLength(); i++) {
			Element workflow = (Element) nodes.item(i);
			path = workflow.getAttribute("version");
		}
		return path;
	}
}
