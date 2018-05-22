package ehotel.admin.util;

import java.io.File;
import java.util.HashMap;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReaderLang
{
	private String fileName=getPath();	
	private String langID="EN";
	private String nameContent="content";
	private String contentId="id";		
	private HashMap hashMap=null;
	public ReaderLang()
	{			
	}
	public  void init(int lang)
	{			
		setLang(lang);
	}		
	public void SetPath(String path)
	{
		fileName=path;
	}
	public void  setLangCode(String lang)
	{
		langID=lang;
	}	
	public void  setLang(int lang)
	{
		hashMap=new HashMap();
		if(lang==1)langID="VN";
		if(lang==2)langID="EN";	
		if(lang==3)langID="CH";	
		loadFile();		
	}	
	public HashMap getMap()
	{
		return hashMap;
	}
	public String getLang()
	{		
		return langID;
	}
	public String getCodeLang()
	{		
		return langID;
	}
	public int getLangId()
	{				
		if(langID.equals("VN"))return 1;
		if(langID.equals("EN"))return 2;
		if(langID.equals("CH"))return 3;
		return -1;
	}	
	private Document getdoc()
	{
		Document doc=null;
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			doc = docBuilder.parse(new File(fileName));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	private Document getdoc(String path)
	{
		Document doc=null;
		try
		{
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();			
			doc = docBuilder.parse(new File(path));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return doc;
	}	
	public  String getContent(String ID)
	{				
		if(hashMap.containsKey(ID))
		return hashMap.get(ID).toString();	
		return "N#A";
	}		
	public String getContent(int ID)
	{	
		//return "N#A";
		 return hashMap.get(String.valueOf(ID)).toString();	
	}		
	@SuppressWarnings("unchecked")
	public void loadFile()
	{
		Document doc=getdoc();		
		NodeList listContent = doc.getElementsByTagName(nameContent);		
		String st="";				
		try {
		   	for(int s=0; s<listContent.getLength() ; s++)
		  	{
		   		Node firstPersonNode = listContent.item(s);
		  		Element firsElement = (Element)firstPersonNode; 	  				
		  		String numId=  firsElement.getAttribute(contentId);	 
			  	NodeList firstNameList = firsElement.getElementsByTagName(langID);
			  	if(firstNameList.getLength()>0)
			  	{
			  		Element firstNameElement = (Element)firstNameList.item(0);		    
			  		NodeList textFNList = firstNameElement.getChildNodes();		   
			  		st="";		  		
			  		st=	textFNList.item(0).getNodeValue();
			  		hashMap.put(numId, st);	
			  	} else {
			  		hashMap.put(numId, "");
			  	}		  	
		  	}	   	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}		
	private String getPath()
	{			
		Properties properties = System.getProperties();
		String path=properties.getProperty("user.dir");			
//		System.out.println(path+Def._langPms);
		return path+ Def._langPms;			
	}
	
	public static void main(String[] args) {
		ReaderLang r = new ReaderLang();
		r.getPath();
	}
}
