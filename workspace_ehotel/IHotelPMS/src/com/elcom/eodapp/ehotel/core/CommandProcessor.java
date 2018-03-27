package com.elcom.eodapp.ehotel.core;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import org.apache.log4j.Logger;

import com.elcom.ehotel.smile.CommandVariables.CMD_GUESTBILL;
import com.elcom.ehotel.smile.CommandVariables.CMD_GUESTDATA;
import com.elcom.ehotel.smile.CommandVariables.CMD_GUESTDATA_EXT;
import com.elcom.ehotel.smile.CommandVariables.CMD_GUESTMESSAGE;
import com.elcom.ehotel.smile.CommandVariables.CMD_POSTING;
import com.elcom.ehotel.smile.FieldVariables.FID;
import com.elcom.eodapp.ehotel.utils.Analysis;
import com.elcom.eodapp.ehotel.utils.CommandProfile;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.Param;

public class CommandProcessor {

	Logger logger = Logger.getLogger(CommandProcessor.class);
	CommandBuilder cmdBuilder = DAOFactory.getInstance().getCommandBuilder();
	DataProcessor dtProcessor = DAOFactory.getInstance().getDataProcessor();

	public String ProcessReceiveCommands(String commands) {
		try {
			Analysis a = new Analysis();
			if (commands != null) {
				if (commands.trim().length() == 0) {
					logger.info("Chuan lenh khong duoc rong <= " + commands);
					return "NOTR";
				}
			}
			CommandProfile pf = a.pareCommandProfile(commands);
			// Guest Data
			if (pf.getCommand().equals(CMD_GUESTDATA.GUEST_CHECK_IN.value)) {
				logger.info("exe guestCheckin: <= " + commands);
				String result = pf.CheckField(new FID[] { FID.GUEST_NUMBER, FID.ROOM_NUMBER, FID.GUEST_NAME, FID.GUEST_ARV_DATE,
						FID.GUEST_DEPT_DATE, FID.RESERVATION_NUMBER });
				if (result.length() > 0)
					return Param.thongbaoPARAM + result;

				dtProcessor.GuestCheckIn(pf);
			} else if (pf.getCommand().equals(CMD_GUESTDATA.GUEST_CHECK_OUT.value)) {
				logger.info("exe guestCheckout: <= " + commands);
				dtProcessor.GuestCheckOut(pf);
			} else if (pf.getCommand().equals(CMD_GUESTDATA.GUEST_CHANGE.value)) {
				logger.info("exe guestChangeData: <= " + commands);
				String result = pf.CheckField(new FID[] { FID.GUEST_NUMBER, FID.GUEST_LANGUAGE, FID.GUEST_FIRST_NAME, FID.GUEST_NAME,
						FID.GUEST_TITLE, FID.GUEST_ARV_DATE, FID.GUEST_DEPT_DATE, FID.RESERVATION_NUMBER });
				if (result.length() > 0)
					return Param.thongbaoPARAM + result;

				dtProcessor.GuestChangeData(pf);
			}
			// Guest room move
			else if (pf.getCommand().equals(CMD_GUESTDATA_EXT.ROOM_MOVE_GUEST.value)) {
				logger.info("exe guestRoomMove: <= " + commands);
				dtProcessor.GuestRoomMove(pf);
			} else if (pf.getCommand().equals(CMD_GUESTDATA_EXT.ROOM_MOVE_ALL.value)) {
				logger.info("exe guestRoomMoveAll: <= " + commands);
				dtProcessor.GuestRoomMoveAll(pf);
			}
			// Guest Message
			else if (pf.getCommand().equals(CMD_GUESTMESSAGE.GUEST_MESSAGE_ONLINE.value)) {
				logger.info("exe guestMessage: <= " + commands);
				dtProcessor.GuestMessageText(pf);
			} else if (pf.getCommand().equals(CMD_GUESTMESSAGE.GUEST_MESSAGE_DELETE.value)) {
				logger.info("exe guestMessageDelete: <= " + commands);
				dtProcessor.GuestMessageDelete(pf);
			}
			// Guest bill
			else if (pf.getCommand().equals(CMD_GUESTBILL.GUEST_BILL_ITEM.value)
					|| pf.getCommand().equals(CMD_GUESTBILL.GUEST_BILL_ONLINE.value)) {
				logger.info("exe guestBillItem: <= " + commands);
				dtProcessor.GuestBillItem(pf);
			} else if (pf.getCommand().equals(CMD_GUESTBILL.GUEST_BILL_BALANCE.value)) {
				logger.info("exe guestBillBalance: <= " + commands);
				dtProcessor.GuestBillBalance(pf);
			}
		} catch (Exception ex) {
			logger.error("Bo lenh chua xu ly duoc: <=" + commands, ex);
			return Param.thongbaoERROR + ex;
		}
		return Param.thongbaoOK;
	}

	@SuppressWarnings({ "null", "unused" })
	public boolean ProcessSendCommands(String command) {
		boolean rs = false;

		try {
			if (command.equals(CMD_GUESTBILL.GUEST_BILL_REQ.value)) {
				logger.info("send guestBillRequest");
				List<String> listCMD = null;// dtProcessor.getBillReqCommand();

				for (String cmd : listCMD) {
					sendRequestToPMServer(cmd);
				}
			} else if (command.equals(CMD_POSTING.POSTING_SIMPLE.value)) {
				logger.info("send guestPostSimple");
				List<String> listCMD = null;// dtProcessor.getPostReqCommand();
				for (String cmd : listCMD) {
					sendRequestToPMServer(cmd);
				}
			}
			return true;
		} catch (Exception ex) {
			logger.error(" Bo lenh chua xu ly duoc: =>" + command, ex);
			return false;
		}
	}

	private boolean sendRequestToPMServer(String cmd) {
		return false;
		// return sendCommandRequest(cmd, MainObject.pmsSocket);
	}

	@SuppressWarnings("unused")
	private boolean sendCommandRequest(String cmd, Socket clientSocket) {
		logger.info("=>" + cmd);
		String ketQua = "";// Cau duoc server xu ly va tra lai la in hoa
		try {
			DataOutputStream sendToServer = new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket
			sendToServer.writeBytes(cmd + '\n');// gui toi server
			sendToServer.flush();
			return true;
		} catch (Exception ex) {
			logger.error("sendCommandRequest error:", ex);
			return false;
		}
	}

	public static void main(String[] args) {
		CommandProcessor c = new CommandProcessor();
		System.out.println(c.ProcessReceiveCommands("GC|RN1234|G#121024|GTDr.|GFKi&#7875;m Tra|GNIT |GLVNM|"));
	}
}
