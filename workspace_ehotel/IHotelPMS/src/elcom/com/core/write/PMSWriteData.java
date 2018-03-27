package elcom.com.core.write;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 03/2011</p>
 *
 * <p>Company: </p>
 *
 * @author Hoavk@
 * @version 1.0
 */
public class PMSWriteData {

    public static boolean write(File file, String lineToWrite) throws IOException {
        boolean append = file.exists();
        System.out.println("Append continue file is exist :" + append);
        String lineSeparator = System.getProperty("line.separator");
        OutputStreamWriter osw = null;
        try {
          if (append) {
            /*  System.out.println(
                  "Output File Already Exists Adding New Output To File");*/
          }
          FileOutputStream fos = new FileOutputStream(file, append);
          BufferedOutputStream bos = new BufferedOutputStream(fos);
          //your charset may vary, or use platform's default
          osw = new OutputStreamWriter(bos, "UTF-8");
          osw.write(lineToWrite);
          osw.write(lineSeparator); //if you need to
          //System.out.println("File Written!");

        }
        catch (Exception ex) {
          return false;
        }
        finally {
          if (osw != null) {
            osw.close();
          }
        }
        return true;
      }

      public static boolean writeUTF16(File file, String lineToWrite) throws IOException {
       boolean append = file.exists();
       System.out.println("Append continue file is exist :" + append);
       String lineSeparator = System.getProperty("line.separator");
       OutputStreamWriter osw = null;
       try {
         if (append) {
           /*  System.out.println(
                 "Output File Already Exists Adding New Output To File");*/
         }
         FileOutputStream fos = new FileOutputStream(file, append);
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         //your charset may vary, or use platform's default
         osw = new OutputStreamWriter(bos, "UTF-16");
         osw.write(lineToWrite);
         osw.write(lineSeparator); //if you need to
         //System.out.println("File Written!");

       }
       catch (Exception ex) {
         return false;
       }
       finally {
         if (osw != null) {
           osw.close();
         }
       }
       return true;
     }


}
