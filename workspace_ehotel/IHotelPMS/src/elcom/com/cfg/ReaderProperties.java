package elcom.com.cfg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReaderProperties {
	private Properties props;
	private static final String FILE_NAME = "eodapp.properties";
	public static boolean fileExist = false;

	 public ReaderProperties() throws IOException
	 {
	   init(null);
	 }

	 public ReaderProperties(String pathConfigFile) throws IOException
	 {
	   init(pathConfigFile);
	 }
	 private void init(String pathConfigFile) throws IOException
	 {
	   String pathCF = "";
	   if(props == null)
	   {
	     if (pathConfigFile == null)
	     {
	       //pathCF = this.getPathHome()  + File.separator + "Config\\eodapp.properties";
	       pathCF = this.getPathHome()  + File.separator + "Config/eodapp.properties";
	       //System.out.println(pathCF);
	     }
	     else
	     {
	       pathCF = pathConfigFile;
	     }

	     File fconf = new File(pathCF);
	     if( fconf.exists())
	     {
	       fileExist = true;
	       Properties properties = new Properties();
	       try
	       {
	         FileInputStream fileinputstream = new FileInputStream(fconf);
	         properties.load(fileinputstream);
	         props = properties;
	       }
	       catch(IOException ioexception)
	       {
	         ioexception.printStackTrace();
	         throw ioexception;
	       }
	     }
	     else
	     {
	       fileExist = false;
	     }
	   }
	 }


	 public String getProperty(String s)
	 {
	  return props.getProperty(s);
	 }

	 public String getProperty(String key, String defauldValue)
	 {
	   String value = getProperty(key);
	   if(value == null) return defauldValue;
	   else  return value;
	 }


	public static String getFILE_NAME() {
		return FILE_NAME;
	}
	public String getPathHome()
	{
	  java.util.Properties properties = System.getProperties();
	  return properties.getProperty("user.dir");
	}

	public Properties getProperties() {
		return props;
	}

	public void setProperties(Properties properties) {
		this.props = properties;
	}


}
