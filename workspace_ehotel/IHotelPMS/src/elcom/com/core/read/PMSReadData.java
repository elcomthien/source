package elcom.com.core.read;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import com.elcom.Log.FileEvent;
import com.elcom.eod.util.UnicodeConverter;
import com.elcom.eodapp.ehotel.core.CommandProcessor;
import com.elcom.eodapp.ehotel.utils.DAOFactory;

import elcom.com.core.write.PMSWriteData;
import elcom.com.util.Utils;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: </p> *
 * @author not attributable
 * @version 1.0
 */
public class PMSReadData {
    static int countParamIn=0;
    public static CommandProcessor cmdProcessor = DAOFactory.getInstance().getCommandProcessor();
    
    @SuppressWarnings({ "unused", "rawtypes" })
	public static String processCommom(String aLine, String proName,
                                       int pamramNum, FileEvent log) {
        StringBuffer buffer = new StringBuffer();
        String strData = "";
        String strValue = "";
        String sqlQuery="";
        StringBuffer bufferValue = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(aLine, "|");
        Vector params = new Vector();
        int i = 0;
        // bo di token dau tien chua command
        int countToken =(tokenizer.countTokens())- 1;
        countParamIn=0;
        String command = tokenizer.nextToken();
        //kiem tra du lieu dau vao truoc khi xu ly
        boolean checkDataInput = checkDataCondition(log,aLine,proName);
        if(checkDataInput=true){
        	String cmdLine = aLine.replaceAll("<CR><LF>","</br>");
        	cmdLine=cmdLine.replace("'","@");// cho cac truong hop tieng anh co dau '
        	cmdLine = UnicodeConverter.encodeUnicode(cmdLine);
            try {               
            	cmdProcessor.ProcessReceiveCommands(cmdLine);
               // phan kiem tra check request lai bill khi balance co do chenh lech voi tong tien cua khach
               /*if(proName.equals("recievTransaction") && command.equals("XO")){
                    Utils.outScreen(log,"Check request bill again with XO?",false);
                    dao.LoadInfoRequest(log,"XR");
               }*/
            }
            catch (Exception ex) {//moi bo sung vao 29/07/2011 bat truong dbi chet
                Utils.outScreen(log, ex.getMessage(),false);
                FileEvent logs = new FileEvent("RECOVERY");
                String fileName = Utils.getRealPath()+"/PMSDown/PMS" + Utils.convertDateToFullString(new Date()) +".ftp";
                File afile = new File(fileName);
                try {
                    PMSWriteData.write(afile, aLine);
                    Utils.outScreen(logs, aLine,false);
                } catch (IOException ex1) {
                    Utils.outScreen(logs, ex1.getMessage(),false);
                }
            }
        }
        return sqlQuery;
    }

    //check du lieu dau vao cua cac field rang buoc chua?
    public static boolean checkDataCondition(FileEvent log,String data,String command){
        System.out.println("Begin check datainput before process.");
        boolean check=true;
        StringBuffer fieldName=new StringBuffer();
        if(command!=null && (command.equals("checInGuest"))){// bat buoc R#,G#
            if(data.indexOf("R#")<0){
                check=false;
                fieldName.append("R#");
            }else if(data.indexOf("G#")<0){
                check=false;
                fieldName.append("G#");
            }
        }else if(command!=null && command.equals("changeGuestInfo")){// truong R# bat buoc,G# doi voi thay doi profile
            if(data.indexOf("R#")<0){//neu ko co R# cho truong hop chi thay doi profile
                if(data.indexOf("G#")<0){
                    check=false;
                    fieldName.append("G# or R#");
                }
            }
        }
        else if(command!=null && command.equals("cancalCheckIn")){
            if(data.indexOf("R#")<0){
               check=false;
               fieldName.append("R#");
            }
        }else if(command!=null && command.equals("cancelCheckOut")){
            if (data.indexOf("R#") < 0) {
                check = false;
                fieldName.append("R#");
            }
        } else if(command!=null && command.equals("checOutGuest")){//bat buoc R#
            if(data.indexOf("R#")<0){
               check=false;
               fieldName.append("R#");
            }
        }else if(command!=null && command.equals("moveGuestRoom")){
            if(data.indexOf("R#")<0){
                 check=false;
                 fieldName.append("R#");
            }
        }else if(command!=null && command.equals("recievMessage")){
            if(data.indexOf("G#")<0){
                check=false;
                fieldName.append("G#");
            }else if(data.indexOf("MI")<0){
                 check=false;
                 fieldName.append("M#");
             }else if(data.indexOf("RN")<0){
                 check=false;
                 fieldName.append("RN");
             }
        }else if(command!=null && command.equals("recievTransaction")){
            if(data.indexOf("G#")<0){
               check=false;
               fieldName.append("G#");
           }else if(data.indexOf("RN")<0){
               check=false;
               fieldName.append("RN");
            }
        }else if(command!=null && command.equals("deleteTransaction")){
            if(data.indexOf("T#")<0){
              check=false;
              fieldName.append("T#");
          }else if(data.indexOf("G#")<0){
              check=false;
              fieldName.append("G#");
          }else if(data.indexOf("BA")<0){// cap nhat lai balance hien tai
             check=false;
             fieldName.append("BA");
          }

        }


       if(check==false){
           Utils.outScreen(log,
                            "Error DataInput[" + command + "] is not enought main field =[" +
                            fieldName.toString() + "]",false);
           System.out.println("Error DataInput[" + command + "] is not enought main field =[" +
                            fieldName.toString() + "]");
       }else{
           Utils.outScreen(log,"DataInput[" + command + "] is  enought main field.",false);
       }
        return check;
    }

    //loc cac truong can thiet vao bo lenh xu ly cua hoavk@
    public static boolean getFieldCommand(FileEvent log,String data,String command){
        boolean isCorrect =false;
        if(data.equals("-900009")){
             countParamIn++;
            return true;
        }
        String key= data.substring(0,2);
        if(command!=null){
            if (command.equals("checInGuest") || command.equals("changeGuestInfo")) {
                if(ParamMap.ICHECKIN_GC_MAP.get(key)!=null){
                    isCorrect = true;
                     countParamIn++;
                }
            } else if (command.equals("cancelCheckIn") || command.equals("cancelCheckOut")) {
                if(ParamMap.IRECHECHINOUT_MAP.get(key)!=null){
                    isCorrect = true;
                    countParamIn++;
                }
            }else if (command.equals("moveGuestRoom")) {
                if(ParamMap.IMOVEGUEST_MAP.get(key)!=null){
                   isCorrect = true;
                    countParamIn++;
                }
            }else if (command.equals("checOutGuest")) {
               if(ParamMap.ICHECKOUT_MAP.get(key)!=null){
                  isCorrect = true;
                   countParamIn++;
               }
            }else if (command.equals("recievTransaction")) {
                if(ParamMap.IBILL_MAP.get(key)!=null){
                 isCorrect = true;
                  countParamIn++;
                }
            }else  if(command.equals("balanceTransaction")){
               if(ParamMap.IBALANCE_MAP.get(key)!=null){
                   isCorrect = true;
                    countParamIn++;
              }
           } else if (command.equals("deleteTransaction")) {
                if(ParamMap.IBILL_MAP.get(key)!=null){
                 isCorrect = true;
                  countParamIn++;
                }
            }
            else  if(command.equals("deleteMessage")){
                if(ParamMap.IMESSAGE_DEL_MAP.get(key)!=null){
                    isCorrect = true;
                     countParamIn++;
               }
            }
            else  if(command.equals("recievMessage")){
               if(ParamMap.IMESSAGE_MAP.get(key)!=null){
                   isCorrect = true;
                    countParamIn++;
              }
           }
           else  if(command.equals("updatePostTransaction")){
              if(ParamMap.IANSWERTRANSACTION.get(key)!=null){
                  isCorrect = true;
                   countParamIn++;
             }
          }
        }
        System.out.println("data :[" + data + "] is necessary =" + isCorrect + " in command");
        Utils.outScreen(log,"data :[" + data + "] is necessary =" + isCorrect + " in command",false);
        return isCorrect;
    }

}
