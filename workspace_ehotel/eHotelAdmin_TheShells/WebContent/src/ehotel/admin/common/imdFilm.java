package ehotel.admin.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class imdFilm 
{
	private String ID="";
	private String name="";
	private String info="";
	boolean state=false;	
	private HashMap hashMap=new HashMap();		
	public void setID(String _id)
	{
		ID=_id;
		String url= "http://www.imdbapi.com/?i="+_id;
		runHttp(url);
	}	
	public void setName(String _name)
	{
		name=_name;
		name=URLEncoder.encode(_name);
		String url= "http://www.imdbapi.com/?t="+name;
		runHttp(url);
		
	}
	private void runHttp(String _url)
	{
		hashMap.clear();
		state=false;
		try
		{
		    URL url = new URL(_url);
		    URLConnection conn = url.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write("");
		    wr.flush();		    
		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line;
		    while ((line = rd.readLine()) != null) 
		    {		    	
		    	info+=(line);		        
		    }		    
		    wr.close();
		    rd.close();		    
		    analysis(info);		    
		}catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}		
	}
	private void analysis(String st)
	{		
		st=st.substring(2,st.length()-2);
		String []arr= st.split("\",\"");
		
		for(int i=0;i<arr.length;i++)
		{
			int l=arr[i].indexOf(":");
			String key=arr[i].substring(0,l-1);
			String text=arr[i].substring(l+2);
			hashMap.put(key, text);			
		}	
		state=true;
	}
	public boolean getState()
	{
		return state;
	}
	public String getInfo(String key)
	{
		return hashMap.get(key).toString();
	}
	private String Title="Title";
	public String getTitle()
	{
		return getInfo(Title);
	}
	private String Rated="Rated";
	public String getRated()
	{
		return getInfo(Rated);
	}
	private String Released="Released";
	public String getReleased()
	{
		return getInfo(Released);
	}
	private String Genre="Genre";
	public String getGenre()
	{
		return getInfo(Genre);
	}
	private String Director="Director";
	public String getDirector()
	{
		return getInfo(Director);
	}
	
	private String Writer="Writer";
	public String getWriter()
	{
		return getInfo(Writer);
	}
	private String Actors="Actors";
	public String getActors()
	{
		return getInfo(Actors);
	}
	public String getPlot()
	{
		return getInfo(Plot);
	}
	private String Plot="Plot";
	private String Poster="Poster";
	public String getPoster()
	{
		return getInfo(Poster);
	}
	private String Runtime="Runtime";
	public String getRuntime()
	{
		return getInfo(Runtime);
	}
	private String Rating="Rating";
	public String getRating()
	{
		return getInfo(Rating);
	}
	public String getID()
	{
		return getInfo("ID");
	}
	
}
