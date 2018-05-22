<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@page import="ehotel.admin.util.ReaderLang"%>
<%@page import="ehotel.admin.util.Def"%>
<%@page import="ehotel.admin.util.ConfigLoader"%>
<%@page import="ehotel.admin.util.Config"%>
<%
String path = request.getContextPath();
String iponly = request.getScheme()+"://"+request.getServerName();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String logmodulepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/eHotelAdmin/LogAppletjnlp.jnlp";
ReaderLang readerLang=new ReaderLang();
if(session.getAttribute(Def._lang)!=null)
{	
	readerLang=(ReaderLang)session.getAttribute(Def._lang);
}
int LangID=readerLang.getLangId();
ConfigLoader loader=new ConfigLoader();
Config cf=loader.getConfig();
String linksaveimage = cf._linksaveimage;
String moviedir = cf._movie;
String musicdir = cf._music;
String livetvdir = cf._pathliveTV;
String hoteldir = cf._hotel;
String maindir = cf._main;
%>
<script type="text/javascript">
 	var logmodulepath = "<%=logmodulepath%>";
 	var language = "<%=LangID%>";
 	var basePath="<%=basePath%>"; 
 	var iponly="<%=iponly%>";
 	var linksaveimage="<%=linksaveimage%>";
 	var moviedir="<%=moviedir%>";
 	var musicdir="<%=musicdir%>";
 	var hoteldir="<%=hoteldir%>";
 	var livetvdir="<%=livetvdir%>";
 	var maindir="<%=maindir%>";
 	var CtxCtn="Content/VodCtnMain"
 	var CtxSrv="Service/VodSrvMain";
 	var langMain={
 		ok:"<%=readerLang.getContent("OK")%>",
 		cancel:"<%=readerLang.getContent("Cancel")%>",
 		del:"<%=readerLang.getContent("14")%>",
 		not_role:"<%=readerLang.getContent("notrole")%>",
 		pls_selectsubject:"<%=readerLang.getContent("pls_selectsubject")%>",
 		maxuly: "<%=readerLang.getContent("action")%>",
 		updateorder: "<%=readerLang.getContent("updateorder")%>",
 		updateapkvalue: "<%=readerLang.getContent("updateapkvalue")%>",
 		updatestatus: "<%=readerLang.getContent("updatestatus")%>",
 		updateservice: "<%=readerLang.getContent("updateservice")%>",
 		xemlog: "<%=readerLang.getContent("xemlog")%>"
 	}
 </script>