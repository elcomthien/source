package ehotel.test;

import java.io.File;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.StringTokenizer;

public class TestUtils {
	public void testencode() {
		try {// T%E1%BA%A5t+c%E1%BA%A3
			String encodedURL = URLEncoder.encode("Bên lăng Bác Hồ", "UTF-8");
			System.out.println(encodedURL);
			File a = new File("D:/Hotel");
			// if (!a.exists()) {
			System.out.println(a.mkdir());
			// }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected static boolean createFolder(String pathfolder) {
		boolean ismkdir = false;
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		String currentFolder = null;
		currentFolder = path;
		File aFolder = new File(currentFolder + "/" + pathfolder);
		if (!aFolder.exists()) {
			if (pathfolder.indexOf("/") == -1) {
				aFolder.mkdir();
				ismkdir = true;
			} else {
				StringTokenizer token = new StringTokenizer(pathfolder, "/");
				while (token.hasMoreTokens()) {
					String aToken = token.nextToken();
					currentFolder += "/" + aToken;
					aFolder = new File(currentFolder);
					if (!aFolder.exists()) {
						aFolder.mkdir();
					}
				}
				ismkdir = true;
			}
		}
		return ismkdir;
	}

	protected static boolean checkExistFile(String aFilePath) {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir");
		File aFolder = new File(path + "/" + aFilePath);
		if (!aFolder.exists()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestUtils test = new TestUtils();
		test.testencode();
		test.createFolder("hoa/demo/mama");
		System.out.println(test.checkExistFile("hoa/demo/mama/hoavk(2).jpg"));
		// test.getMenus();
	}
}
