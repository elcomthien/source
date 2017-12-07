package ehotel.admin.common;

import java.util.Vector;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ftpClient
{
	private String Host="";
	private int Port=21;
	private String User="";
	private String Pass="";
	FTPClient client=new FTPClient();
	public ftpClient(String host,String user,String pass,int port)
	{					
		this.Host=host;
		this.Pass=pass;
		this.Port=port;
		this.User=user;			
	}
	public void connect()
	{		
		try
		{
			client.connect(this.Host, this.Port);
			client.login(User,Pass);		
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}		
	}
	public Vector<String> getFiles(String path)
	{
		Vector<String> files=new Vector();
		try
		{		
			FTPFile [] list=	client.listFiles(path);			
			for(int i=0;i<list.length;i++)
			{
				files.add(list[i].getName());
			}
			
		}catch (Exception e) 
		{
			e.printStackTrace();
			
			// TODO: handle exception
		}
		return files;
	}
	public Vector<String> getFolder(String path)
	{
		
		Vector<String> files=new Vector();
		try
		{
			FTPFile [] list=	client.listDirectories(path);
			for(int i=0;i<list.length;i++)
			{
				files.add(list[i].getName());
			}
			
		}catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return files;
	}
	public void close()
	{
		try
		{
			client.disconnect();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
