package elcom.com.webserver;

import org.apache.xmlrpc.WebServer;

import com.elcom.Log.FileEvent;

import elcom.com.cfg.ReaderProperties;
import elcom.com.cfg.eLogger;
import elcom.com.core.read.PMSFile;
import elcom.com.core.write.PMSCoreWrite;

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
 * @author hoavk
 * @version 1.0
 */
public class eHotelPMSServer_bk {
	FileEvent log = new FileEvent("eIPTVPMSServer");

	/**
	 * sendRequestToPms : dung lenh nay khi can send command dang request ve message,bill lay lai thong toan bo
	 * 
	 * @return String : tra ve command gui sang pms.
	 */
	@SuppressWarnings("static-access")
	public String notifyRequestToPms(String command, int folioNum, int guestId) {
		System.out.println("eHotelPMSServer.notifyRequestToPms params input[folioNum=" + folioNum + ",guestId=" + guestId + "]");
		eLogger.writeLog(log, "eHotelPMSServer.notifyRequestToPms params input[folioNum=" + folioNum + ",guestId=" + guestId + "]");
		PMSCoreWrite core = new PMSCoreWrite();
		String cmd = core.sendCmdRequestToPms(command, folioNum, guestId);
		eLogger.writeLog(log, "Server proccesed notifyRequestToPms=[cmd=" + cmd + "] is completed!");
		System.out.println("Server proccesed notifyRequestToPms= [cmd=" + cmd + "]");
		return cmd;
	}

	/**
	 * notifySendServiceCharge : thong bao cho ehotel khi dich vu tinh phi cua iptv duoc su dung qua pms
	 * 
	 * @param command
	 *            String
	 * @param transationID
	 *            int
	 * @return String
	 */
	@SuppressWarnings("static-access")
	public String notifySendServiceCharge(String command, int transationID) {
		System.out.println("EPG notify vodcharge params input[transationID=" + transationID + "]");
		eLogger.writeLog(log, "EPG notify vodcharge  params input[transationID=" + transationID + "]");

		PMSCoreWrite core = new PMSCoreWrite();
		String cmd = core.sendCmdAlertChargeToPms(command, transationID);
		eLogger.writeLog(log, "Server proccesed notifySendServiceCharge=[cmd=" + cmd + "] is completed!");
		System.out.println("Server proccesed notifySendServiceCharge= [cmd=" + cmd + "]");
		return cmd;
	}

	@SuppressWarnings("static-access")
	public String notifySendCommandCommon(String command, int objId) {
		System.out.println("EPG notify notifySendCommandCommon  params input[objId=" + objId + "]");
		eLogger.writeLog(log, "EPG notify notifySendCommandCommon  params input[objId=" + objId + "] and command=" + command);
		PMSCoreWrite core = new PMSCoreWrite();
		String cmd = core.sendCmdCommonToPms(command, objId);
		eLogger.writeLog(log, "Server proccesed notifySendCommandCommon=[cmd=" + cmd + "] is completed!");
		System.out.println("Server proccesed notifySendCommandCommon= [cmd=" + cmd + "] is completed!");
		return cmd;
	}

	/**
	 * notifyFileDownload : thong bao cho Bo xu ly eHotel biet co file download.
	 * 
	 * @param fileNamePath
	 *            String : ten duong dan day du cua file
	 */
	public void notifyFileDownload(String fileNamePath) {
		System.out.println("Recived notify from IHotelPMSDown params input[fileName=" + fileNamePath + "]");
		eLogger.writeLog(log, "Recived notify from IHotelPMSDown[fileName=" + fileNamePath + "]");
		PMSFile pms = new PMSFile(fileNamePath);
		pms.readFile();
		System.out.println("Server proccesed notifyFileDownload= [fileNamePath=" + fileNamePath + "] is completed!");
	}

	/**
	 * notifyChangePinCode : thong bao ve thong tin thay doi pincode cua khach cho pms
	 * 
	 * @param folioNum
	 *            String
	 * @param guestId
	 *            String
	 * @param newPinCode
	 *            String
	 */
	@SuppressWarnings("static-access")
	public void notifyChangePinCode(String folioNum, String guestId, String newPinCode) {
		System.out.println("eHotelPMSServer.notifyChangePinCode params input[folioNum=" + folioNum + ",guestId=" + guestId + "]");
		eLogger.writeLog(log, "eHotelPMSServer.notifyChangePinCode params input[folioNum=" + folioNum + ",guestId=" + guestId + "]");

		PMSCoreWrite core = new PMSCoreWrite();
		String command = "GC";
		String cmd = core.changePinCode(command, folioNum, guestId, newPinCode);
		eLogger.writeLog(log, "Server proccesed notifyChangePinCode=[cmd=" + cmd + "] is completed!");
		System.out.println("Server proccesed notifyChangePinCode= [cmd=" + cmd + "] is completed!");
	}

	public void notifyCheckInFO(String command) {
		System.out.println("eHotelPMSServer.notifyChangePinCode params input[command=" + command + "]");
		eLogger.writeLog(log, "eHotelPMSServer.notifyChangePinCode params input[command=" + command + "]");
	}

	public static void main(String[] args) {
		try {
			ReaderProperties configReader = new ReaderProperties();
			int port = Integer.parseInt(configReader.getProperty("xmlrcp.webserver.port", "60006"));
			System.out.println("Attempting to start XML-RPC Server port:" + port + ".....");
			WebServer server = new WebServer(port);
			server.addHandler("ehotelPms", new eHotelPMSServer());
			server.start();
			System.out.println("Started successfully.");
			System.out.println("Accepting requests. (Halt program to stop.)");
		} catch (Exception exception) {
			System.err.println("eHotelPMSServer: " + exception);
		}
	}

}
