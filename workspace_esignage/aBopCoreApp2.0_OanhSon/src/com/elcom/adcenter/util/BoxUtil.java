package com.elcom.adcenter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.elcom.DBI.SubProParam;
import com.elcom.adcenter.dbi.IMBroker;

public class BoxUtil {
	public String os = System.getProperty("os.name").toLowerCase();
	public static IMBroker broker = IMBroker.getInstance();
	private static final Logger logger = LogManager.getLogger(BoxUtil.class);

	public void rebootBox(String str) {
		String[] arr = str.split(",");
		for (int i = 0; i < arr.length; i = i + 2) {
			if (pingIp(arr[i]) && arr[i + 1].equals("1")) {
				// disconnect(arr[i]);
				System.out.println("reboot: " + arr[i]);
				rebootIp(arr[i]);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				disconnect(arr[i]);
			}
		}
	}

	public boolean pingIp(String ip) {
		boolean flag = false;
		if (isWindows()) {
			String lsString = null;
			String[] listCmd = new String[] { "cmd.exe ", "/c",
					" ping -n 1 " + ip };
			Runtime run = Runtime.getRuntime();
			Process runtimeProcess = null;
			try {
				runtimeProcess = run.exec(listCmd);
				BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(runtimeProcess.getInputStream()));
				int i = 0;
				while ((lsString = bufferedReader.readLine()) != null) {
					System.out.println(lsString);
					if (i == 2) {
						if (lsString.indexOf("Destination host unreachable") >= 0) {
							return false;
						} else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						} else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						} else {
							return true;
						}
					}
					i++;
				}
				run.freeMemory();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (isUnix()) {
			if (ip == null) {
				return false;
			} else {
				String lsString = null;
				String[] listCmd = new String[] { "/bin/bash", "-c",
						"ping -c 1 " + ip };
				Runtime run = Runtime.getRuntime();
				Process runtimeProcess = null;
				try {
					runtimeProcess = run.exec(listCmd);
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(
									runtimeProcess.getInputStream()));
					while ((lsString = bufferedReader.readLine()) != null) {
						if (lsString.indexOf("Destination Host Unreachable") >= 0) {
							return false;
						} else if (lsString.indexOf("Request timed out") >= 0) {
							return false;
						} else if (lsString.indexOf("100% loss") >= 0) {
							return false;
						} else {
							flag = true;
						}
					}
					run.freeMemory();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public boolean isWindows() {
		return (os.indexOf("win") >= 0);
	}

	public boolean isUnix() {
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}

	@SuppressWarnings({ "unused" })
	public void rebootIp(String ip) {
		String[] listCmd = new String[] { "cmd.exe ", "/c",
				"D:/Run/reboot.bat " + ip };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		// String lsString = null;
		try {
			runtimeProcess = run.exec(listCmd);
			// BufferedReader bufferedReader = new BufferedReader(
			// new InputStreamReader(runtimeProcess.getInputStream()));
			// while ((lsString = bufferedReader.readLine()) != null) {
			// System.out.println(lsString);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Taskkill /IM adb.exe /F
	public void disconnect(String ip) {
		// String[] listCmd = new String[] { "cmd.exe ", "/c", "adb disconnect "
		// + ip };
		String[] listCmd = new String[] { "cmd.exe ", "/c",
				"Taskkill /IM adb.exe /F" };
		Runtime run = Runtime.getRuntime();
		Process runtimeProcess = null;
		String lsString = null;
		System.out.println("adb disconnect " + ip);
		try {
			runtimeProcess = run.exec(listCmd);
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(runtimeProcess.getInputStream()));
			while ((lsString = bufferedReader.readLine()) != null) {
				System.out.println(lsString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String getListChannel() {
		String result = "";
		Vector<SubProParam> params = new Vector<SubProParam>();
		Vector<String> outParam = new Vector<String>();
		SubProParam subOut = new SubProParam(outParam, "STRING_ARR", 1);
		params.add(subOut);
		try {
			params = broker.executeSubPro(
					"BEGIN ELIVETV.getLinkChannelAdvertise(?); END;", params);
			if ((params != null) & (params.size() > 0)) {
				subOut = (SubProParam) params.get(0);
				outParam = subOut.getVector();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < outParam.size(); i++) {
			if (outParam.get(i).length() > 1)
				result += outParam.get(i);
		}
		result = result.substring(0, result.length() - 1);
		return result;
	}

	private Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					xmlStr)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String convertDocumentToString(Document doc) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			// below code to remove XML declaration
			// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
			// "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String replaceXML(String xmlStr) {
		String rs = "";
		Document doc = convertStringToDocument(xmlStr);
		NodeList nList = doc.getElementsByTagName("item");
		// String url =
		// "udp://239.1.2.10:10010;udp://239.1.2.11:10011;udp://239.1.2.12:10012;udp://239.1.2.13:10013;udp://239.1.2.14:10014;udp://239.1.2.15:10015;udp://239.1.2.16:10016;udp://239.1.2.17:10017;udp://239.1.2.18:10018;udp://239.1.2.19:10019";
		String url = getListChannel();

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element eElement = (Element) nNode;
			String type = eElement.getElementsByTagName("type").item(0)
					.getTextContent();
//			System.out.println(type);
			if (type.equals("1"))
				eElement.getElementsByTagName("url").item(0)
						.setTextContent(url);
		}
		rs = convertDocumentToString(doc);
		return rs;
	}

	@SuppressWarnings("rawtypes")
	public String getListChannels() {
		String rs = "";
		String sql = "select physical_address from bc_channels where status = 1";
		Vector vector = new Vector();
		vector = broker.executeSelect(sql, null);
		if (vector.size() > 2) {
			ResultDB result = new ResultDB(vector);
			while (result.hasNext()) {
				result.next();
				if (result.getParam("PHYSICAL_ADDRESS").length() > 1)
					rs += result.getParam("PHYSICAL_ADDRESS");
			}
			rs = rs.substring(0, rs.length() - 1);
		}
		return rs;
	}

	public static void main(String[] args) {
		// BoxUtil b = new BoxUtil();
		// System.out.println(b.os);
		// System.out.println(b.pingIp("172.16.9.91"));
		// b.rebootBox("172.16.9.91,1,172.16.9.99,1");
		// b.rebootIp("172.16.9.91");
		// b.disconnect();
		// String[] listCmd = new String[] { "cmd.exe ", "/c",
		// "D:/Run/reboot.bat 172.16.9.91" };
		// Runtime run = Runtime.getRuntime();
		// Process runtimeProcess = null;
		// String lsString = null;
		// try {
		// runtimeProcess = run.exec(listCmd);
		// BufferedReader bufferedReader = new BufferedReader(
		// new InputStreamReader(runtimeProcess.getInputStream()));
		// while ((lsString = bufferedReader.readLine()) != null) {
		// System.out.println(lsString);
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		// String t = "1234567890";
		// t = t.substring(0, t.length() - 1);
		BoxUtil b = new BoxUtil();
		System.out.println(b.getListChannels());
	}

}
