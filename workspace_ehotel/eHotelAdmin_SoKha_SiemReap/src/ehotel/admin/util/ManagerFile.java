package ehotel.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ManagerFile {
	private final static String pathbalance01 = "/core/ehotel01/service/app/Loadbalancer/service01/webapps/ePMS2.0/elcom/resources/image/";
	private final static String pathbalance02 = "/core/ehotel01/service/app/Loadbalancer/service02/webapps/ePMS2.0/elcom/resources/image/";
	private final static String pathbalance03 = "/core/ehotel01/service/app/Loadbalancer/service03/webapps/ePMS2.0/elcom/resources/image/";

	public void copy(String filepath, String pathsave) {
		System.out.println("filepath: " + filepath);
		System.out.println("pathsave: " + pathsave);
		ConfigLoader loader = new ConfigLoader();
		Config config = loader.getConfig();
		String path01 = pathsave.replace(config._pathImage, pathbalance01);
		System.out.println("path01: " + path01);
		String path02 = pathsave.replace(config._pathImage, pathbalance02);
		System.out.println("path02: " + path02);
		String path03 = pathsave.replace(config._pathImage, pathbalance03);
		System.out.println("path03: " + path03);
		transfer(filepath, pathsave);
		transfer(filepath, path01);
		transfer(filepath, path02);
		transfer(filepath, path03);

	}

	public void transfer(String path1, String path2) {
		System.out.println("Transfer---------");
		System.out.println("path1: " + path1);
		System.out.println("path2: " + path2);
		try {
			File filein = new File(path1);
			File fileou = new File(path2);
			if (!filein.exists())
				return;
			String path = path2.substring(0, path2.lastIndexOf("/") + 1);
			File f = new File(path);
			if (!f.exists()) {
				f.mkdir();
			}
			FileInputStream in = new FileInputStream(filein);
			FileOutputStream out = new FileOutputStream(fileou);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deletefile(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	public void clearAll(String path) {
		File file = new File(path);
		File list[] = file.listFiles();
		for (int i = 0; i < list.length; i++) {
			if (list[i].exists()) {
				list[i].delete();
			}
		}
	}

	public boolean checkImage(String path) {
		File file = new File(path);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
}
