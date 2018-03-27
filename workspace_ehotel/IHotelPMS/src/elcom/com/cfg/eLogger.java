package elcom.com.cfg;

import com.elcom.Log.FileEvent;
import java.io.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class eLogger {
    static boolean debug=false;

    public static synchronized void writeLog(FileEvent log, String msg) {
      if (debug) {
          System.out.println(msg);
      }else{
          log.write(msg);
          log.flush();
     }

  }

  public static synchronized void writeLog(FileEvent log, Exception ex,
                                           String msg) {
       if (debug) {
           System.out.println(msg);
       }else{
           log.write(msg);
           log.flush();
      }

  }

    public eLogger() {
            try {
                ReaderProperties config = new ReaderProperties();
                String adebug = config.getProperty("debug","false");
                if(adebug!=null & adebug.equals("true")){
                    debug=true;
                }
            } catch (IOException ex) {
            }
    }



}
