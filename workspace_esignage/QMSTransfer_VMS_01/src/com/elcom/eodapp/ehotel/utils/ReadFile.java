package com.elcom.eodapp.ehotel.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import com.elcom.Log.FileLog;

public class ReadFile {
	public void Readfile(String filename,DataOutputStream sendToClient,FileLog log)
	{
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));
			if (sendToClient != null){
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println("eSmile send to Aqs => " + sCurrentLine);
				
				sendToClient.writeUTF(sCurrentLine);
		    	sendToClient.flush();
		    	log.write("eSmile => " + sCurrentLine);
		    	log.flush();
			}
			delteFile(filename);
			}
			

		} catch (IOException e) {
			//e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void ReadFile(String filepath,DataOutputStream sendToClient,FileLog log) {
		  try {
		   File file = new File(filepath);
		   if (!file.exists()) return;
		   FileInputStream fileInPutStream = new FileInputStream(filepath);
		   Reader reader = new java.io.InputStreamReader(fileInPutStream, "Unicode");
		   BufferedReader buffReader = new BufferedReader(reader);
		   StringBuilder stringBuilder = new StringBuilder();
		   String line = null;
		   while ((line = buffReader.readLine()) != null) {
		    stringBuilder.append(line + "\n");
		    sendToClient.writeUTF(line);
	    	sendToClient.flush();
	    	System.out.println("eSmile send to Aqs => " + line);
	    	log.write("eSmile => " + line);
	    	log.flush();
		   }
		   reader.close();
		   delteFile(filepath);
		  } catch (Exception io) {
		   //System.out.println("Khong tim thay file " + filepath);
		   // io.printStackTrace();
		  }
		 
	}
	
	public void delteFile(String namefile)
	{
		try {
		       File file = new File(namefile);
		       if (file.delete()) {
		           System.out.println(file.getName() + " is deleted!");
		       } else {
		           System.out.println("Deleteation is failed.");
		       }
		    } catch (Exception e) {
		       e.printStackTrace();
		    }
	}
	
	public static String ReadFile(String filepath) {
		  System.out.println("Read file: " + filepath);
		  try {
		   FileInputStream fileInPutStream = new FileInputStream(filepath);
		   Reader reader = new java.io.InputStreamReader(fileInPutStream,
		     "Unicode");
		   BufferedReader buffReader = new BufferedReader(reader);
		   StringBuilder stringBuilder = new StringBuilder();
		   String line = null;
		   while ((line = buffReader.readLine()) != null) {
		    stringBuilder.append(line + "\n");
		   }

		   reader.close();
		   return stringBuilder.toString();
		  } catch (Exception io) {
		   System.out.println("Khong tim thay file " + filepath);
		//   io.printStackTrace();
		  }
		  return "";
		 }
	
	public static boolean WriteFile(String pathFile, String text,
			   boolean overwrite) {
			  try {
			   String oldText = "";
			   if (!overwrite) {
			    oldText = ReadFile(pathFile);
			    System.out.println(oldText);
			   }
			   FileOutputStream fileOutStream = new FileOutputStream(pathFile);
			   Writer writer = new OutputStreamWriter(fileOutStream, "Unicode");
			   System.out.println(oldText + text);
			   writer.write(oldText + text);
			   writer.close();
			   return true;
			  } catch (Exception io) {
			   System.out.println("Khong ghi duoc file" + pathFile);
			  }
			  return false;
			 }
	
}
