package com.elcom.adcenter.util;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;

public class CreateFile {
	private String path = "";
	private String name = "";
	
	public CreateFile (String path_,String name_) 
	{
		this.path = path_;
		//this.name = DateHelper.format(new Date(),"ddMMyyyyhhmmss") + "_" + name_ + ".xml";
		this.name = name_ + ".xml";
	}
	
	public int createFile(String xml)
	{		
		try{	
			 System.out.println("B1:===========" + path + "/" + name);
			 FileOutputStream fos= new FileOutputStream(path + "/" + name ,false); 
	         PrintWriter pw= new PrintWriter(fos); 
	         pw.println(xml); 	             
	         pw.close(); 			
		}catch (Exception ex){ex.printStackTrace();}
		
		return 1;
	}
	
	
	public static void main(String[] arg)
	{
		System.out.println(DateHelper.format(new Date(),"ddMMyyyyhhmmss"));
	}
	
}
