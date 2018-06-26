package ehotel.admin.util;

public class UtilString
{
	public static String langVN="VN";
	public static String langEN="EN";
	public static String subId="subId";
	public static String VodId="VodId";
	public static String VodName="VodName";
	
	public static String uni2ent2ndTry(String srcTxt)
	{
		if(srcTxt.length()==0)return "";
		   String entTxt = "";
		   int  code;
		   String c;
		   int hi,lo;
		   int len = 0;
		   for (int i=0 ; i<srcTxt.length(); i++)
		   {
			   code=srcTxt.charAt(i);
		      // need to convert to HTML entity
		      if (code > 255)
		      {
		         // values in this range are surrogate pairs
		         if (0xD800 <= code && code <= 0xDBFF) 
		         {
		            hi = code;
		            lo = srcTxt.charAt(i+1);
		            lo &= 0x03FF;
		            hi &= 0x03FF;
		            hi = hi << 10;
		            code = (lo + hi) + 0x10000;
		         }
		         // wrap it up as a Hex entity
		         
		         c = "&#x" +  String.valueOf(code) + ";";
		      }
		      // smaller values can be used raw
		      else
		      {
		         c =String.valueOf((srcTxt.charAt(i)));
		      }
		      entTxt += c;
		   }
		 //  entTxt=entTxt.replaceAll("&#x","&#");		
		 //  entTxt=entTxt.replaceAll("\'","\''");
		 //  entTxt=entTxt.replaceAll("amp;", "&amp;");
		   return entTxt;
	}
	public static String convertToHTML(String st)
	{
		st=st.replaceAll("&amp;", "&");
		st=st.replaceAll("&nbsp;", " ");	
		st=st.replaceAll("&lt;", "<");
		st=st.replaceAll("&gt;", ">");		
		st=st.replaceAll("&quot;", "\"");
		return st;
	}
	
	public static void main(String a[])
	{
		//System.out.print(UtilString.convertToHTML("<br/>Slimming and Firming treatment.");
	}
	
	
}

