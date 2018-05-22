package ehotel.admin.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.Naming;

import ehotel.core.MediaFileParserInterface;
import ehotel.core.MetadataVideo;
import ehotel.req.connect.URLConstant;

public class VlcService {
	//class khoi tao
	public VlcService() {
		super();
	}
	
	public static void main(String[] args) {
		readMetaDataVideo("D:/Lam_Download/sample3.mkv");
	}
	
	public static MetadataVideo readMetaDataVideo(String fileName) {
		  System.out.println("Reading metadata video[fileName=" + fileName + "url=" + URLConstant.MEDIAPARSE + "]");
		  MetadataVideo video = null;
		  try {
			   MediaFileParserInterface mediaParse = (MediaFileParserInterface) Naming.lookup("rmi://172.16.9.202:2101/elc_mediafileparser");
			   video = mediaParse.readMetadataVideo(fileName);
			   if (video != null)
				   System.out.println("Completed Reading metadata video[fileName="
			      + fileName + "] and result[" + video.toString() + "]");
			   else
				   System.out.println("mediaParse return value null.Maybe not found file");
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
		  return video;
	}
	
	public String playVlc(String path) throws IOException, InterruptedException {
		//goi vlc bang command line 12.1
		String result = "";
		if ( isWindows() ) {
            String executeCmd = "cmd.exe /c vlc " + path;
            System.out.println("run vlc=" + executeCmd);
     		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
     		int processComplete = runtimeProcess.waitFor();
     		if(processComplete == 0){
     			result = "ok";
     		} else {
     			result = "error";
     		}
		}
		if (isMac()) {
			String executeCmd = "cmd.exe /c vlc " + path;
            System.out.println("run vlc=" + executeCmd);
     		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
     		int processComplete = runtimeProcess.waitFor();
     		if(processComplete == 0){
     			result = "ok";
     		} else {
     			result = "error";
     		}
		}
		if ( isUnix() ) {
            String[] executeCmd = new String[] {"/bin/bash","-c","vlc " + path};
     		Runtime run = Runtime.getRuntime();
     		String lsString = null;
     		Process runtimeProcess = null;
 		    try {
 		    	runtimeProcess = run.exec(executeCmd); 
 		    	try
		         {
 		    		int processComplete = runtimeProcess.waitFor();
 		    		if(processComplete == 0){
	 	     			result = "ok";
	 	     		} else {
	 	     			result = "error";
	 	     		}
		         }
		         catch (InterruptedException e)
		         {
		                e.printStackTrace();
		         }
	       }
	       catch (IOException e)
	       {
	             e.printStackTrace();
	       } 
 		   BufferedReader stdError = new BufferedReader(new InputStreamReader(runtimeProcess.getErrorStream()));
 			while ((lsString = stdError.readLine()) != null) {
 			     System.out.println("stdError: " + lsString);
 			}
		}
		return result;
	}
	
	//get system time 3.10
	public String getSystemTime() throws IOException, InterruptedException {
		String callback = "";
		//for windows
		//tim xem thang mysqld nam o thu muc nao
		if ( isWindows() ) {
			String executeCmd = "cmd.exe /c time/t";
			Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
     		InputStream is = runtimeProcess.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        while ((line = br.readLine()) != null) {
	        	callback = line;
	        }
		}
		//linux only 3.10
		if ( isUnix() ) {
            String[] executeCmd = new String[] {"/bin/bash","-c","date +%r"};
     		Runtime run = Runtime.getRuntime();
     		String lsString = null;
     		Process runtimeProcess = null;
 		    try {
 		    	runtimeProcess = run.exec(executeCmd); 
 		    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
 		    	while ((lsString = bufferedReader.readLine()) != null)
		        {
	 		    	callback = lsString;
		        }
	       }
	       catch (IOException e)
	       {
	             e.printStackTrace();
	       } 
 		}
		
		return callback;
	}
	
	//check os 12.1
	public boolean isWindows() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("win") >= 0);
	}
	public boolean isMac() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("mac") >= 0);
	}
	public boolean isUnix() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0);
	}
	public boolean isSolaris() {
		String os = System.getProperty("os.name").toLowerCase();
		return (os.indexOf("sunos") >= 0);
	}
}
