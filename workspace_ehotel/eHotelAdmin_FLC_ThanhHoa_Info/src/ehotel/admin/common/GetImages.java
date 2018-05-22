package ehotel.admin.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetImages
{
	public String read(String local,String link)
	{
		long start=System.currentTimeMillis();
		String ImageName=String.valueOf(start);
		String type=link.substring(link.lastIndexOf("."));
		ImageName=ImageName+type;
		try
		{
			
			File file=new File(local+ImageName);
			URL url = new URL(link);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);		    
		    InputStream input=conn.getInputStream();
		    FileOutputStream  out=new FileOutputStream(file);		    
		    byte []b=new byte[1024*3];
		    int n=input.read(b);
		    int length=0;		    
		    while(n>0)
		    {
		    	out.write(b,0,n);
		    	n=input.read(b);
		    } 
		   out.close();
		   input.close();	   
		    
		}catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return ImageName;
	}
}
