package elcom.com.webserver;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClient;

/**
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * 
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class eHotelPMSClient {
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public static void main(String[] args) {
		try {

			XmlRpcClient server = new XmlRpcClient("http://localhost:60006/RPC2");
			// Test TH send vodcharge
			Vector param01 = new Vector();

			Vector params = new Vector();
			params.addElement("PS");
			params.addElement(new Integer(408));
			// Object result01 = server.execute("ehotelPms.notifySendServiceCharge", params);

			// test TH send request message hoac bill
			Vector param02 = new Vector();
			param02.addElement("XM");
			param02.addElement(new Integer(3));
			param02.addElement(new Integer(17));
			Object result02 = server.execute("ehotelPms.notifyRequestToPms", param02);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println("eHotelPMSClient: " + ex);
		}
	}

}
