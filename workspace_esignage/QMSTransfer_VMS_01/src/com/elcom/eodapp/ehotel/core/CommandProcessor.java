package com.elcom.eodapp.ehotel.core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;


import com.elcom.eodapp.ehotel.common.Command;
import com.elcom.eodapp.ehotel.main.MainObject;
import com.elcom.eodapp.ehotel.utils.DAOFactory;
import com.elcom.eodapp.ehotel.utils.DataHelper;
import com.elcom.eodapp.ehotel.utils.Param;
import com.elcom.eodapp.ehotel.utils.ReadFile;

public class CommandProcessor {
	
	Logger logger = Logger.getLogger(CommandProcessor.class);
	CoreInterface dtProcessor = DAOFactory.getInstance().getCoreInterface();
	ReadFile file = new ReadFile();
	
	
	public String ProcessReceiveCommands(String message) throws IOException {
		try {
			System.out.println("CHUOI NHAN DUOC TU QMS <= : " + message.trim());
			int dangnhap = 0;
			//dangnhap = message.trim().indexOf("SI");
			if (message.trim().substring(0,2).equals("SI"))  
			{
				return Param.thongbaoLoginSUCSESS; 
			}
			if (message.trim().substring(0,2).equals("SO"))  
			{
				return Param.thongbaoLoginERROR; 
			}
			
			if ((message.trim()).length() == 0) return Param.thongbaoERROR; 
			String[] items = DataHelper.getMessageData(message.trim());
			
			System.out.println("PHAN TICH CHUOI");
			for (int i = 0 ; i < items.length ; i++)
			{
				if (i == 0)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Massge Code I,O*/ ");
				}else
				if (i == 1)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ngon ngu*/ ");
				}else
				if (i == 2)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*So thu tu*/ ");
				}else
				if (i == 3)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ma dich vu*/ ");
				}else
				if (i == 4)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ten dich vu*/ ");
				}else
				if (i == 5)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Loai Khach hang*/ ");
				}else
				if (i == 6)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ma quay goi STT*/ ");
				}else
				if (i == 7)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ten Quay goi STT*/ ");
				}else
				if (i == 8)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ma Nhan vien goi STT*/ ");
				}else
				if (i == 9)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ten Nhan Vien GOi STT*/ ");
				}else
				if (i == 10)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Thoi gian lay STT*/ ");
				}else
				if (i == 11)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Thoi GIan Goi*/ ");
				}else
				if (i == 12)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Thoi Gian Xong*/ ");
				}else
				if (i == 13)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ma quay chuyen STT di*/ ");
				}else
				if (i == 14)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ten quay chuyen STT di*/ ");
				}else
				if (i == 15)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ma dich vu chuyen STT*/ ");
				}else
				if (i == 16)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Ten dich vu chuyen STT*/ ");
				}else
				if (i == 17)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Du phong 1*/ ");
				}
				if (i == 18)
				{
				    System.out.println("i = " + i + " - values = " + items[i] +  " /*Du phong 2*/ ");
				}
			}
			System.out.println("=======XU LY CHUOI CHO VAO HE THONG eSmile items.length ====== : " + items.length);
			//message_code,ngon_ngu, stt, ma_phim_nhan, ten_phim_nhan, ma_dich_vu, ten_dich_vu, loai_khach_hang, ma_quay_goi_stt, ten_quay_goi_stt, ma_nhan_vien_goi_stt, ten_nhan_vien_goi_stt, thoi_gian_lay_stt, thoi_gian_goi, thoi_gian_xong,ma_quay_chuyen_stt,ten_quay_chuyen_stt,chuoi_nhan_duoc
			//0            1         2    15            16                 3           4            5                6                 7                8                     9                      10                  11            12             13                  14                  
			if (!items[0].equals("C"))
			{
				dtProcessor.sp_qmsinsert_rawdata(items[0],items[1],items[2]
					, items[15], items[16], items[3], items[4], items[5], items[6], 
					items[7], items[8], items[9], items[10], items[11], items[12],items[13],items[14],message.trim());
				System.out.println("=======HOAN THANH TAC VU CHUYEN VAO HE THONG DB======");
			}else 
			{
				file.WriteFile("Config/ketqua/ketqua.txt", items[2], true);
			}
			
		} 
		catch (Exception ex) {			
			logger.error("Bo lenh chua xu ly duoc: <=", ex);
			return Param.thongbaoERROR + ex.getMessage();
		}
		return Param.thongbaoOK;
	}
	
	public boolean ProcessSendCommands(String command) {
		boolean rs = false;
		
		try {			
			/*if(command.equals(CMD_GUESTBILL.GUEST_BILL_REQ.value)) {	
				logger.info("send guestBillRequest");
				List<String> listCMD = dtProcessor.getBillReqCommand();	
				
				for(String cmd : listCMD) {
					sendRequestToPMServer(cmd);
				}
			}*/	
			
			return true;
		} catch (Exception ex) {
			logger.error(" Bo lenh chua xu ly duoc: =>" + command, ex);
			return false;
		}		
	}
	
	private boolean sendRequestToPMServer(String cmd) {
		return sendCommandRequest(cmd, MainObject.pmsSocket);
	}
	
	private boolean sendCommandRequest(String cmd, Socket clientSocket)
	{
		logger.info("=>" + cmd);
        String ketQua = "";//Cau duoc server xu ly va tra lai la in hoa 
        try{        	             
            DataOutputStream sendToServer= new DataOutputStream(clientSocket.getOutputStream());// Tao output stream ket noi toi socket            
            sendToServer.writeBytes(cmd + '\n');// gui toi server  
            sendToServer.flush();
            return true;
        }
        catch(Exception ex)
        {
        	logger.error("sendCommandRequest error:", ex);
        	return false;
        }        
	}		
}	
