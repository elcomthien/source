package ehotel.admin.obj;

import java.util.Vector;



public class AlbumSubject 
{
	private String idsubalbum;
	private String namealbum="";
	private String parentalbumid="";
	private String lang="VN";
	
	public AlbumSubject()
	{}
	
	public AlbumSubject(String id)
	{	
		idsubalbum=id;
	}
	
	public AlbumSubject(Vector V_rs)
	{
		idsubalbum=V_rs.elementAt(0).toString();
		namealbum=V_rs.elementAt(1).toString();
		parentalbumid=V_rs.elementAt(3).toString();
	}
	
	
	
	public String SUBJECTNAME()
	{
		return idsubalbum;
	}
	
	public String PARENTSUBJECTID()
	{
		return namealbum;
	}
	
	public String SUBJECTID()
	{
		return parentalbumid;
	}
}
