
<%@page import="ehotel.render.MOD"%>
<%@page import="ehotel.render.DBIGateway"%>
<%@page import="ehotel.inter.AMDMod"%>
<%@page import="java.util.Vector"%>
<%@page import="ehotel.domain.vod.Subject"%>
<%
	MOD  modDBI = DBIGateway.getAMDMod();		
		AMDMod cntMod = modDBI.getAMDCntMod();		
		Vector<Subject> list=cntMod.getSubjects(2);	
%>
<ul>
	<%
	Vector<Subject> m=new Vector();
	Vector<Subject> parent=new Vector();
	for(int i=0;i<list.size();i++)
	{
		if(list.get(i).getParentId()==-1)
		{
			m.add(list.get(i));
			list.remove(i);
			i--;
		}
	}	
	
 for(int j=0;j<m.size();j++)
 {
		parent.add(m.get(j));
		int tagul=0;		
		while(parent.size()>0)
		{
			boolean t=false;
			int i=0;
			int p=parent.lastElement().getId();			
			for(;i<list.size();i++)
			{
				if(list.get(i).getParentId()==p)
				{
					parent.add(list.get(i));
					list.remove(i);
					t=true;
					break;
				}
			}		
			/**
			co cay con 
			*/
			if(t)//co cay con
			{				
				if(parent.size()-1>tagul)
				{
					out.print("<li>");
					out.print(parent.get(parent.size()-2).getName());
					out.print("<ul>");
					tagul++;					
				}		
				
			}else
			{
				/*
					neu no la nyt la
				*/
				if(parent.size()>tagul)
				{
					out.print("<li>");
					out.print(parent.lastElement().getName());
					out.print("</li>");
					parent.remove(parent.size()-1);
					
				}else
				{
					tagul--;
					out.print("</ul>");
					out.print("</li>");
					parent.remove(parent.size()-1);
				}		
				
			}
	  }
 }
	
%>
</ul>