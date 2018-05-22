package ehotel.admin.util;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

import org.apache.log4j.Logger;

import ehotel.admin.model.EPMS_BILL;

public class util {
	private static Logger logger;
	
	public static String getParentPath(String path) {
		String parentPath = path.substring(0, path.length() - 2);
		int i = parentPath.lastIndexOf(System.getProperty("file.separator"));
		parentPath = parentPath.substring(0, i + 1);
		return parentPath;
	}
	
	
/*	public boolean WriteFile(String pathFile, String text) {
		  try {
		   FileOutputStream fileOutStream = new FileOutputStream(pathFile+"iptv.pms");
		   Writer writer = new OutputStreamWriter(fileOutStream, "Unicode");
		   writer.write(text);
		   writer.close();
		   return true;
		  } catch (IOException io) {
		   System.out.println("Khong ghi duoc file: " + pathFile);
		  }
		  return false;
		 }*/
	
/*	public boolean WriteFile(String pathFile, String text, boolean overwrite) {
		  System.out.println("write file: " + pathFile);
		  try {
		   String oldText = "";
		   if (!overwrite) {
		    oldText = ReadFile(pathFile);
		   }
		   FileOutputStream fileOutStream = new FileOutputStream(pathFile);
		   Writer writer = new OutputStreamWriter(fileOutStream, "Unicode");
		   writer.write(oldText + text);
		   writer.close();
		   return true;
		  } catch (IOException io) {
		   System.out.println("Khong ghi duoc file" + pathFile);
		  }
		  return false;
	}*/
	
/*	public boolean WriteFile(String pathFile, List<EPMS_BILL> list_) {
		  System.out.println("write file: " + pathFile);
		  try {
		   FileOutputStream fileOutStream = new FileOutputStream(pathFile+"iptv.ftp");
		   Writer writer = new OutputStreamWriter(fileOutStream, "UTF-8");		 
		   for (EPMS_BILL li : list_){
				  writer.write("XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|"+"\r\n");
		   }
		   writer.close();
		   return true;
		  } catch (IOException io) {
		   System.out.println("Khong ghi duoc file: " + pathFile);
		  }
		  return false;
	}*/
	
/*	public boolean WriteFile(String pathFile, List<EPMS_BILL> list_) {
		  System.out.println("write file: " + pathFile);
		 
		  try {
		   FileOutputStream fileOutStream = new FileOutputStream(pathFile+"iptv.ftp");
		   Writer writer = new OutputStreamWriter(fileOutStream);		  
		   for (EPMS_BILL li : list_){
				  writer.write("XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|"+"\n");
		   }
		   writer.close();
			// FileOutputStream fileOutStream = new FileOutputStream(pathFile+"iptv.ftp");
		    FileWriter fw = new FileWriter(pathFile+"iptv.ftp");
	        BufferedWriter bw = new BufferedWriter(fw);
	        for(EPMS_BILL li : list_){
	        	  bw.write("XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|");
	        	 // bw.newLine();
	        }
	          
	          bw.close();
	  
	            
		   return true;
		  } catch (IOException io) {
		   System.out.println("Khong ghi duoc file: " + pathFile);
		  }
		  return false;
	}*/
	
	
	/*public boolean write(File file_, List<EPMS_BILL> lineToWrite) throws IOException {		 
		  String lineSeparator = System.getProperty("line.separator");
		  OutputStreamWriter osw = null;
		  try {
		   FileOutputStream fos = new FileOutputStream(file_);
		   //FileOutputStream fos = new FileOutputStream(file,append);// dung khi ghi tung line mot
		   BufferedOutputStream bos = new BufferedOutputStream(fos);
		   osw = new OutputStreamWriter(bos, "UTF-8"); //your charset may vary, or use platform's default
		   for(EPMS_BILL li : lineToWrite){
	        	 // bw.write("XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|");
	        	 // bw.newLine();
			   String v="XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|";
			   if(v.contains("?")){
				   String kt="";
				   char[] ch = v.toCharArray();
				     for(int i=ch.length-1;i>=0;i--){
				       String kytu=String.valueOf(ch[i]);
				       if(!kytu.equalsIgnoreCase("?")){
				    	   kt+=kytu;
				       }
				     }
				     if(kt.length()>0){
				    	 v=kt;
				     }
				     System.err.println("testttttt ??:"+ v);
			   }else{
				   System.err.println("testttttt:"+ v);
			   }
	          osw.write(v);
	   		   osw.write(lineSeparator); //if you need to
	        }
		   
		  
		   //log.info("File Written!");
		   return true;
		  }catch(Exception ex){
			  return false;
		  }finally {
			  if (osw != null)
				    osw.close();
		  }
		
		
 }*/
	
	public boolean copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	    	//FileUtils.copyFile(source, dest);
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	        return true; 
	    }catch(Exception ex){
			  return false;
		}finally{
			if(is!=null){
				is.close();
			}if(os!=null){
				os.close();
			}
		}
	}
	
	public boolean write(File file, List<EPMS_BILL> lineToWrite) throws IOException {
	    //boolean append = file.exists();
	    //dung de tach cac dong voi nhau (hay xuong dong) trong qua trinh ghi file
	    String lineSeparator = System.getProperty("line.separator");
	    OutputStreamWriter osw = null;
	    try {
	     FileOutputStream fos = new FileOutputStream(file);
	     //FileOutputStream fos = new FileOutputStream(file,append);// dung khi ghi tung line mot
	     BufferedOutputStream bos = new BufferedOutputStream(fos);
	     osw = new OutputStreamWriter(bos, "ISO-8859-1"); //your charset may vary, or use platform's default
	     for(EPMS_BILL li : lineToWrite){
	    	/* if(li.getFolionum().equalsIgnoreCase("1") || li.getFolionum().equalsIgnoreCase("2")||li.getFolionum().equalsIgnoreCase("3")||li.getFolionum().equalsIgnoreCase("4")
	    			 ||li.getFolionum().equalsIgnoreCase("5")||li.getFolionum().equalsIgnoreCase("6")||li.getFolionum().equalsIgnoreCase("7")||li.getFolionum().equalsIgnoreCase("8")||li.getFolionum().equalsIgnoreCase("9")){
	    		 String v2="XR|RN00"+li.getFolionum()+"|G#"+li.getClient_id()+"|\r\n";	
	    		 char[] test_char = v2.toCharArray();
	    		 osw.write(test_char);*/
	    	 if(Integer.parseInt(li.getFolionum())<10){
	    		 String v2="XR|RN00"+li.getFolionum()+"|G#"+li.getClient_id()+"|\r\n";	
	    		 char[] test_char = v2.toCharArray();
	    		 osw.write(test_char);	    		 
	    	 }else if(Integer.parseInt(li.getFolionum())>=10 && Integer.parseInt(li.getFolionum())<=99){
	    		 String v2="XR|RN0"+li.getFolionum()+"|G#"+li.getClient_id()+"|\r\n";	
	    		 char[] test_char = v2.toCharArray();
	    		 osw.write(test_char);	  
	    	 }
	    	 else{
	    		 String v="";	
		    	 v="XR|RN"+li.getFolionum()+"|G#"+li.getClient_id()+"|\r\n";	    
		    	 char[] test_char = v.toCharArray();	    	 
		    	 
		    	 osw.write(test_char);
		    	
	    	 }    	 
	    	
	     }
	     
	     //log.info("File Written!");
	     return true;
	    }catch(Exception ex){
			  return false;
		  } finally {
	     if (osw != null)
	      osw.close();
	    }
	   }
		  

	
	public static int parseInt(String str) {
		  int rs = 0;
		  try {
		   rs = Integer.parseInt(str);
		  } catch (Exception e) {
		   
		  }
		  return rs;
		 }
	
	public String ReadFile(String pathFile) {
		System.out.println("Read file: " + pathFile);
		try {
			FileInputStream fileInPutStream = new FileInputStream(pathFile);
			Reader reader = new java.io.InputStreamReader(fileInPutStream, "Unicode");
			BufferedReader buffReader = new BufferedReader(reader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = buffReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}

			reader.close();
			return stringBuilder.toString();
		} catch (IOException io) {
			System.out.println("Khong tim thay file " + pathFile);
			io.printStackTrace();
		}
		return "";
	}
	 public void copyDirectory(File sourceLocation , File targetLocation)
	    	    throws IOException {

	    	        if (sourceLocation.isDirectory()) {
	    	            if (!targetLocation.exists()) {
	    	                targetLocation.mkdir();
	    	            }

	    	            String[] children = sourceLocation.list();
	    	            for (int i=0; i<children.length; i++) {
	    	                copyDirectory(new File(sourceLocation, children[i]),
	    	                        new File(targetLocation, children[i]));
	    	            }
	    	        } else {

	    	            InputStream in = new FileInputStream(sourceLocation);
	    	            OutputStream out = new FileOutputStream(targetLocation);

	    	            // Copy the bits from instream to outstream
	    	            byte[] buf = new byte[1024];
	    	            int len;
	    	            while ((len = in.read(buf)) > 0) {
	    	                out.write(buf, 0, len);
	    	            }
	    	            in.close();
	    	            out.close();
	    	        }
	    	    }

	
}
