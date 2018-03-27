package elcom.com.core.write;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;

import com.elcom.Log.FileEvent;

import elcom.com.apiconnect.FTPConnect;
import elcom.com.cfg.ReaderProperties;
import elcom.com.cfg.eLogger;
import elcom.com.core.common.CMDCommon;
import elcom.com.core.common.Guest;
import elcom.com.core.common.ServiceCharge;
import elcom.com.core.dao.InterfaceDao;
import elcom.com.util.Utils;

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
 * @author Hoavk@
 * @version 1.0
 */
public class PMSCoreWrite {
	private static String host;
	private static String username;
	private static String pass;
	private static String port;
	private static String localDir;
	private static String fptDir;
	private static String FODir;
	public static ReaderProperties configReader = null;

	static FileEvent log = new FileEvent("SEND");

	@SuppressWarnings("static-access")
	public PMSCoreWrite() {
		try {
			configReader = new ReaderProperties();
			this.host = configReader.getProperty("ftpserver.host", "192.168.0.141");
			this.username = configReader.getProperty("ftpserver.user", "123");
			this.pass = configReader.getProperty("ftpserver.pass", "123");
			this.port = configReader.getProperty("ftpserver.port", "21");
			this.localDir = Utils.getRealPath() + "/" + configReader.getProperty("ehotel.dir.up.process", "IPTVUp");
			this.fptDir = configReader.getProperty("ftpserver.iptvdir.up", "IPTV/pms.ftp");
			this.FODir = configReader.getProperty("ehotel.dir.down.process", "E:/Project/Workspace/Eod_App/IHotelPMS/PMSDown");
		} catch (Exception ex) {
			Utils.outScreen(log, ex.getMessage(), false);
		}
	}

	/**
	 * sendCommandToPms : lenh gui request den pms lay thong tin khi su co xay ra
	 * 
	 * @param command
	 *            String
	 * @param folioNum
	 *            String
	 * @param guestID
	 *            String
	 * @return String
	 */
	public static String sendCmdRequestToPms(String command, int folioNum, int guestId) {
		String sendCmd = "";
		try {
			String folio_code = folioNum + "";
			if (folio_code != null) {
				if (folio_code.length() == 1) {
					folio_code = "00" + folio_code;
				} else if (folio_code.length() == 2) {
					folio_code = "0" + folio_code;
				}
			}
			// cau truc lenh send to pms
			sendCmd = command + "|RN" + folio_code.trim() + "|G#" + guestId + "|";
			uploadFile(sendCmd);
			/* Xu ly xoa du lieu lien quan den guest (message hoac transaction,balance) */
			System.out.println("Remove data of guest to resyn data again!.");
			InterfaceDao dao = new InterfaceDao();
			dao.deleteInfoRequest(folioNum, guestId, command);

		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
		}
		return sendCmd;
	}

	/**
	 * changePinCode : gui lenh qua thay doi pincode cua khach qua pms
	 * 
	 * @param command
	 *            String
	 * @param folioNum
	 *            String
	 * @param guestID
	 *            String
	 * @param newPinCode
	 *            String
	 * @return String
	 */
	public static String changePinCode(String command, String folioNum, String guestID, String newPinCode) {
		String sendCmd = "";
		try {
			String folio_code = folioNum + "";
			if (folio_code != null) {
				if (folio_code.length() == 1) {
					folio_code = "00" + folio_code;
				} else if (folio_code.length() == 2) {
					folio_code = "0" + folio_code;
				}
			}
			sendCmd = command + "|RN" + folio_code + "|G#" + guestID + "|GP" + newPinCode + "|";
			uploadFile(sendCmd);
		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
		}
		return sendCmd;
	}

	/**
	 * commandAlertChargeToPms : send command den pms khi dich vu charge su dung
	 * 
	 * @param command
	 *            String
	 * @param params
	 *            Vector<SubProParam> : input=TranID,Output=Vector
	 * @return String
	 */
	public static String sendCmdAlertChargeToPms(String command, int transationID) {
		String sendCmd = "";
		try {
			// lay thong tin vodcharge
			InterfaceDao dao = new InterfaceDao();
			ServiceCharge charge = dao.getServiceCharge(transationID);
			if (charge != null) {
				sendCmd = command + "|RN" + charge.getFolioNum() + "|GP" + charge.getPinCode() + "|P#" + charge.getTranID() + "|TA"
						+ charge.getAmount() + "|PTIPTV " + charge.getDesciption() + "|DA" + charge.getTranDate() + "|TI"
						+ charge.getTranTime() + "|CU" + charge.getAmountUnit() + "|R#" + charge.getIdReservation() + "|";// tam rem phan R#
																															// ,day theo
																															// dung file
																															// chuan
																															// interface
				uploadFile(sendCmd);
			}
		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
			ex.printStackTrace();
		}
		return sendCmd;
	}

	/**
	 * sendCmdCommonToPms:
	 * 
	 * @param command
	 *            String
	 * @param params
	 *            Vector
	 * @return String
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static String sendCmdCommonToPms(String command, int objId) {
		String sendCmd = "";
		try {

			InterfaceDao dao = new InterfaceDao();
			if (command != null && command.equals("XM")) {
				Vector vGuest = dao.getGuestOfFolioNum(objId);
				if (vGuest != null) {
					String folio_code = objId + "";
					if (folio_code != null) {
						if (folio_code.length() == 1) {
							folio_code = "00" + folio_code;
						} else if (folio_code.length() == 2) {
							folio_code = "0" + folio_code;
						}
					}

					FTPConnect conn = new FTPConnect();
					FTPClient ftp = conn.connect(host, port, username, pass);
					File aFile = conn.checkExistFile(ftp, fptDir, localDir); // kiem tra ve download file
					for (int i = 0; i < vGuest.size(); i++) {
						Guest guest = (Guest) vGuest.get(i);
						sendCmd = command + "|RN" + folio_code + "|G#" + guest.getGuestId() + "|";
						PMSWriteData.write(aFile, sendCmd);
					}
					try {
						// up file vua write len fpt
						conn.upload(ftp, localDir, fptDir);
					} catch (Exception ex1) {
						eLogger.writeLog(log, "Open connect ftp again!");
						ftp = conn.connect(host, port, username, pass);
						conn.upload(ftp, localDir, fptDir);
					}
					eLogger.writeLog(log, "Iptv send data = [" + sendCmd + "]");
					// xu ly xong xoa file
					aFile.delete();
				}
			} else {// XU
				CMDCommon common = dao.getInfoReadMessage(command, objId);
				if (common != null) {
					sendCmd = command + "|RN" + common.getFolioNum() + "|MI" + common.getCmdId() + "|G#" + common.getClientId() + "|MS2|DA"
							+ common.getDate() + "|TI" + common.getTime() + "|";
					uploadFile(sendCmd);
				}
			}

		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
		}
		return sendCmd;

	}

	public static String sendCheckInFO(String contentCMD) {
		// ghi file thang vao thu muc xu ly cua iptv là PMSDown.
		String fileName = "FO" + Utils.convertDateToFullString(new Date()) + ".ftp";
		System.out.println(FODir);
		String filePath = FODir + "/" + fileName;
		System.out.println(filePath);
		try {
			File aFile = new File(filePath);
			PMSWriteData.write(aFile, contentCMD);

		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
		}
		return fileName;
	}

	public static void sendCommand(String content) {
		uploadFile(content);
	}

	@SuppressWarnings("static-access")
	private static void uploadFile(String sendCmd) {
		try {
			/*
			 * Kiem tra file(pms.ftp) cua Iptv co dang ton tai( hay chua duoc xu ly) tren FTP server? Neu file chua duoc xu ly thi ghi tiep
			 * theo vao file. Nguoc lai tao moi
			 */
			FTPConnect con = new FTPConnect();
			FTPClient ftp = con.connect(host, port, username, pass);
			File aFile = con.checkExistFile(ftp, fptDir, localDir); // kiem tra ve download file
			PMSWriteData.write(aFile, sendCmd);
			// up file vua write len fpt
			con.upload(ftp, localDir, fptDir);
			// ghi log lai lenh send qua pms
			eLogger.writeLog(log, "Iptv send data = [" + sendCmd + "]");
			System.out.println("IHotelPMS up file len PMS:[FileName=" + fptDir + "]");
			// xu ly xong xoa file local
			aFile.delete();
		} catch (Exception ex) {
			eLogger.writeLog(log, ex.getMessage());
		}
	}

	@SuppressWarnings({ "static-access", "unused" })
	public static void main(String[] args) {
		PMSCoreWrite write = new PMSCoreWrite();
		write.sendCmdAlertChargeToPms("PS", 1696);
		// write.sendCmdRequestToPms("XR",216,10);
		String sendCMD = "GI|R#1234|G#8874|GA110327|GD110329|GTMr.|GFTiep|GNDo Viet|RN7425|GP9876|";
		// write.sendCheckInFO(sendCMD);
		// write.sendCmdCommonToPms("XM",1);

	}
}
