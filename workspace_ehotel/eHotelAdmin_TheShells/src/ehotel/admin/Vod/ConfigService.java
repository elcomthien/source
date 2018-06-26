package ehotel.admin.Vod;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class ConfigService {
	public boolean WriteFile(String pathFile, String text, boolean overwrite) {
		System.out.println("write file: " + pathFile);
		try {
			String oldText = "";
			if (!overwrite) {
				oldText = ReadFile(pathFile);
			}
			FileOutputStream fileOutStream = new FileOutputStream(pathFile);
			Writer writer = new OutputStreamWriter(fileOutStream, "Unicode");
			writer.write(oldText + text);
			writer.close();
			return true;
		} catch (IOException io) {
			System.out.println("Khong ghi duoc file" + pathFile);
		}
		return false;
	}

	public String ReadFile(String pathFile) {
		System.out.println("Read file: " + pathFile);
		try {
			FileInputStream fileInPutStream = new FileInputStream(pathFile);
			Reader reader = new java.io.InputStreamReader(fileInPutStream, "Unicode");
			BufferedReader buffReader = new BufferedReader(reader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = buffReader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}

			reader.close();
			return stringBuilder.toString();
		} catch (IOException io) {
			System.out.println("Khong tim thay file " + pathFile);
			io.printStackTrace();
		}
		return "";
	}
	
	
	public static void main(String[] args) {
		ConfigService c = new ConfigService();
		System.out.println(c.ReadFile("D:\\data.txt"));
	}
}
