package com.elcom.eodapp.ehotel.main;

import com.elcom.eodapp.ehotel.utils.ReadFile;

public class MainSendComand extends Thread{
	@Override
    public void run() 
    { 
    	//CreateServer();
		ReadFile file = new ReadFile();
		while(true) {
			//System.out.println("Send commanf to Aqs");
        	file.ReadFile("Config/testing.txt", MainObject.sendToClient,MainObject.logger_);	
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}       
    }    
}
