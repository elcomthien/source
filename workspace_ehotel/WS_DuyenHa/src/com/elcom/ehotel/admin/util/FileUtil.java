package com.elcom.ehotel.admin.util;

public class FileUtil {
	public static String checkFileType(String filename) {
		String extension = "";
		extension = filename.substring(filename.lastIndexOf(".") + 1);
		if (extension.equals("mp3") || extension.equals("m4a")
				|| extension.equals("wav") || extension.equals("aac")
				|| extension.equals("flac") || extension.equals("webm")) {
			return "audio";
		} else if (extension.equals("mp4") || extension.equals("mkv")
				|| extension.equals("3gp") || extension.equals("mov")
				|| extension.equals("ts")) {
			return "video";
		}
		return "";
	}
	
}
