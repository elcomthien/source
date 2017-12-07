package elcom.com.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import ebop.com.config.eBopProperties;
import ebop.com.response.eBaseResponse;

public abstract class eBaseXML {
	public String xmlFile;
	Marshaller marshallerObj;
	protected String version;
	protected int cache;// cache image
	protected String provider;
	protected String ebopCode;// code trong file xml gianh cho xac dinh cac dich
								// vu se export cho tung stb

	public eBaseXML(String fileName) {
		xmlFile = fileName;
	}

	protected void createNew(Object obj) {
		JAXBContext contextObj;
		FileOutputStream stream = null;
		try {
			contextObj = JAXBContext.newInstance(obj.getClass());
			marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshallerObj.setProperty(CharacterEscapeHandler.class.getName(),
					new CharacterEscapeHandler() {
						@Override
						public void escape(char[] ac, int i, int j,
								boolean flag, Writer writer) throws IOException {
							// do not escape
							writer.write(ac, i, j);
						}
					});
			System.out.println("xml-------------------: "+ marshallerObj.getClass());
			stream = new FileOutputStream(xmlFile);
			marshallerObj.marshal(obj, stream);
			System.out.println("Export[xml] is finished.");
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	protected String createFolder(String folderName) {
		Properties properties = System.getProperties();

		File aFile = new File(folderName);
		// tao thu muc neu chua ton tai
		String parentFolder = aFile.getParent();
		System.out.println("createFolder.parentFolder =>" + parentFolder);
		File folder = new File(parentFolder);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		try {
			eBaseResponse.grant(parentFolder);
			if (!aFile.exists()) {
				System.out.println("createFolder.createNewFile =>" + folderName);
				aFile.createNewFile();
			}
		} catch (Exception e) {
			if (!folder.exists()) {
				folder.mkdirs();
			}
			System.out.println("@HOAVK.Log(" + e.getMessage() + ")");
		}
		return parentFolder;
	}

	public static void grant(String filename) {
		try {
			Runtime.getRuntime().exec("chmod 777 " + filename);
			System.out.println("grant " + filename);
		} catch (IOException e) {
			System.out.println("@HOAVK.Log(" + e.getMessage() + ")");
		}
	}

	public String getPath(String fileName) {
		Properties properties = System.getProperties();
		String path = properties.getProperty("user.dir") + "/" + fileName;
		return path;
	}

	public int getCache(String att) {
		int cache = 1;
		try {
			eBopProperties configReader = new eBopProperties(
					"Config/cache.properties");
			cache = Integer.parseInt(configReader.getProperty(att, "1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cache;
	}

	public abstract void export_xml();

}
