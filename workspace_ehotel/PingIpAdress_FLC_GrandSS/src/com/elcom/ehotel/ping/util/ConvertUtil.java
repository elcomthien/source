package com.elcom.ehotel.ping.util;

public class ConvertUtil {
	public static int convertToInteger(String val){
		try{
			int value=Integer.parseInt(val);
			return value;
		}catch(Exception ex){
			
		}
		return 0;
	}
}
