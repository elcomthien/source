package com.elcom.upload.service;

public class FileUtil {
	public static String getType(String fileName){
		int index=fileName.lastIndexOf(".");
		String type=fileName.substring(index, fileName.length());
		return type;
	}
	
	public static void main(String[] args) {
		System.out.println(getType("dadsa.aaaa"));
	}
}
