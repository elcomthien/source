package ehotel.admin.LiveTV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdditionLiveTvService {
	
	//class khoi tao
	public AdditionLiveTvService() {
		super();
	}
	
	//export dbadcenter du lieu su dung exec
	public String showSoLuongNguoiXemTv(String port) throws IOException, InterruptedException {
		String result = "";
		if ( isWindows() ) { }
		if ( isUnix() ) {
            String[] executeCmd = new String[] {"/bin/bash","-c","netstat -an | grep :" + port + " | wc -l"};
     		
     		Runtime run = Runtime.getRuntime();
     		String lsString = null;
     		Process runtimeProcess = null;
 		    try {
 		    	runtimeProcess = run.exec(executeCmd); 
 		    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runtimeProcess.getInputStream()));
 		    	while ((lsString = bufferedReader.readLine()) != null)
		         {
		                System.out.println(lsString);
		                result = lsString;
		                int temp = Integer.parseInt(result);
		                if (temp >=2) temp-=2;
		                result = Integer.toString(temp);
		         }
 		    }
 		    catch (IOException e)
 		    {
	             e.printStackTrace();
 		    }
 		    BufferedReader stdError = new BufferedReader(new InputStreamReader(runtimeProcess.getErrorStream()));
 		    while ((lsString = stdError.readLine()) != null) {
 			     System.out.println("stdError3: " + lsString);
 		    }
		}
		return result;
	}
	
	//check os 21.9
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