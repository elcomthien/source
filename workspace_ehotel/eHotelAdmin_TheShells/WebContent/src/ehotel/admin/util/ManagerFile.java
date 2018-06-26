package ehotel.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ManagerFile
{
	/**
	 * 
	 * 
	 * @param path1 duocng dan file goc
	 * @param path2 duong dan file copy
	 */
	public void copy(String path1,String path2)
	{
		try
		{
		File filein=new File(path1);
		File fileou=new File(path2);
		if(!filein.exists())return;
		FileInputStream in = new FileInputStream(filein);	
		FileOutputStream  out = new FileOutputStream(fileou); 
		byte[] buf = new byte[1024];
	    int len;
	      while ((len = in.read(buf)) > 0)
	      {
	        out.write(buf, 0, len);
	      }
	      in.close();
	      out.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void deletefile(String path)
	{
		File file=new File(path);
		if(file.exists())
		{
			file.delete();
		}
	}
	public void clearAll(String path)
	{
		File file=new File(path);
		File list[]= file.listFiles();
		for(int i=0;i<list.length;i++)
		{
			if(list[i].exists())
			{
				list[i].delete();
			}
		}
	}
}
