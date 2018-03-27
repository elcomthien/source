package elcom.com.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Properties;
import com.elcom.Log.FileEvent;
import elcom.com.cfg.eLogger;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p> *
 * <p>Company: </p> *
 * @author not attributable
 * @version 1.0
 */
public class Utils {
    public static double parseDouble(String value) {
        double aValue = 0.0;
        if (value != null) {
            aValue = Double.parseDouble(value);
        }
        return aValue;
    }

    public static int parseInt(String value) {
        int aValue = 0;
        if (value != null) {
            aValue = Integer.parseInt(value);
        }
        return aValue;
    }



    public static String format(Date date, String pattern) {

        // returns a String representation of the date argument,
        // formatted according to the pattern argument, which
        // has the same syntax as the argument of the SimpleDateFormat
        // class1E

        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static java.util.Date parseDate(String strDate) {

        try {
            // return a Date
            // Parse the previous string back into a Date.
            java.text.SimpleDateFormat formatter = new java.text.
                    SimpleDateFormat(
                            "dd/MM/yyyy HH:mm:ss");
            java.text.ParsePosition pos = new java.text.ParsePosition(0);
            java.util.Date d = formatter.parse(strDate, pos);

            return d;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getRealPath(){
        Properties properties = System.getProperties();
        String path=properties.getProperty("user.dir");
        return path;
    }
    public static String convertDateToFullString(Date date) {
       String fmt = "yyMMddhhmmss"; //"dd_MM_yyyy_hh_mm_ss";
       String d = null; ;
       SimpleDateFormat sdm = new SimpleDateFormat(fmt);
       try {
           d = sdm.format(date);
       } catch (Exception e) {
           // TODO: handle exception
       }
       return d;
   }

   public static void outScreen(FileEvent e,String params,boolean isdaungachngang){
        if(isdaungachngang){
         eLogger.writeLog(e,"!------------------------------------------------------------------------------------------------");
        }
        System.out.println(params);
        eLogger.writeLog(e,params);
    }


}
