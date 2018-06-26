package ehotel.admin.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;


public class DBXML
{
	private Document document;
	private String filename="";
	public DBXML(String file)
	{
		this.filename=file;
		document=getdoc(file);		
	}	
	private Document getdoc(String filename)
	{
		Document doc=null;
		try
		{			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File(filename));			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return doc;
	}
}
