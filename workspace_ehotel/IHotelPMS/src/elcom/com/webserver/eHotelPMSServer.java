package elcom.com.webserver;

import com.elcom.Log.FileEvent;

import elcom.com.core.read.PMSFile;
import elcom.com.core.write.PMSCoreWrite;
import elcom.com.util.Utils;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author hoavk
 * @version 1.0
 */
public class eHotelPMSServer {
    FileEvent log = new FileEvent("eHotelPMSServer");
    /**
     * sendRequestToPms : dung lenh nay khi can send command dang request ve message,bill lay lai thong toan bo
     * @return String : tra ve command gui sang pms.
     */
    @SuppressWarnings("static-access")
	public String notifyRequestToPms(String command, int folioNum,int guestId) {
       Utils.outScreen(log,"eHotelPMSServer.notifyRequestToPms params input[folioNum="+folioNum +",guestId="+ guestId+"]",true);
       PMSCoreWrite core = new PMSCoreWrite();
       String cmd = core.sendCmdRequestToPms(command,folioNum,guestId);
       Utils.outScreen(log, "--->notifyRequestToPms=[cmd=" + cmd+ "] is completed!",false);
       return cmd;
    }
    /**
     * notifySendServiceCharge : thong bao cho ehotel khi dich vu tinh phi cua iptv duoc su dung qua pms
     * @param command String
     * @param transationID int
     * @return String
     */
    @SuppressWarnings("static-access")
	public String notifySendServiceCharge(String command,int transationID){
        Utils.outScreen(log,"EPG notify vodcharge  params input[transationID="+ transationID+"]",true);
        PMSCoreWrite core = new PMSCoreWrite();
        String cmd= core.sendCmdAlertChargeToPms(command,transationID);
        Utils.outScreen(log, "--->notifySendServiceCharge =[cmd=" + cmd+ "] is completed!",false);
        return cmd;
    }

    @SuppressWarnings("static-access")
	public String notifySendCommandCommon(String command,int objId){
        Utils.outScreen(log,"EPG notify notifySendCommandCommon  params input[objId="+ objId+"] and command="+ command,true);
        PMSCoreWrite core = new PMSCoreWrite();
        String cmd= core.sendCmdCommonToPms(command,objId);
        Utils.outScreen(log, "--->notifySendCommandCommon=[cmd=" + cmd+ "] is completed!",false);
        return cmd;
    }

    /**
     * notifyFileDownload : thong bao cho Bo xu ly eHotel biet co file download.
     * @param fileNamePath String : ten duong dan day du cua file
     */
    public void notifyFileDownload(String fileNamePath){
        Utils.outScreen(log,"Recived notify from IHotelPMSDown[fileName="+fileNamePath+"]",true);
        try{
            PMSFile pms = new PMSFile(fileNamePath);
            pms.readFile();
        }catch(Exception ex){
            Utils.outScreen(log,ex.getMessage(),false);
        }
        Utils.outScreen(log,"The end processed [fileNamePath=" + fileNamePath+ "] is completed!",false);

    }
    /**
     * notifyChangePinCode : thong bao ve thong tin thay doi pincode cua khach cho pms
     * @param folioNum String
     * @param guestId String
     * @param newPinCode String
     */
    @SuppressWarnings("static-access")
	public  void notifyChangePinCode(String folioNum,String guestId,String newPinCode){
        Utils.outScreen(log,"eHotelPMSServer.notifyChangePinCode params input[folioNum="+folioNum +",guestId="+guestId+"]",true );
        PMSCoreWrite core = new PMSCoreWrite();
        String command="GC";
        String cmd = core.changePinCode(command,folioNum,guestId,newPinCode);
        Utils.outScreen(log, "--->notifyChangePinCode=[cmd=" + cmd+ "] is completed!",false);
    }
    /**
     * notifyCheckInFO : nhan thong tin check-in phia FO tren giao dien admin
     * @param command String
     */
    @SuppressWarnings("static-access")
	public void notifyCheckInFO(String command){
        Utils.outScreen(log,"eHotelPMSServer.notifyCheckInFO params input[command="+command+"]" ,true);
        PMSCoreWrite core = new PMSCoreWrite();
        String result= core.sendCheckInFO(command);
        Utils.outScreen(log, "--->notifyCheckInFO=[File=" + result+ "] is completed!",false);
    }
    /**
     * dung de send mot lenh den pms
     * @param command String
     */
    @SuppressWarnings("static-access")
	public void notifyCommand(String command){
       Utils.outScreen(log,"eHotelPMSServer.notifyCommand params input[command="+command+"]",true );
       PMSCoreWrite core = new PMSCoreWrite();
       core.sendCommand(command);
       Utils.outScreen(log, "--->notifyCommand=[command=" + command+ "] is completed!",false);
   }
    
}
