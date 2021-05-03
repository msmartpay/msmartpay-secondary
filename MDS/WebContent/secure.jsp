<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN"
   "http://www.w3.org/TR/html4/frameset.dtd">
<%@ page import="java.util.*" %>
<%@ page import="com.login.*" %>

<%

String message=(String)request.getAttribute("message");

request.setAttribute("message",message);


String ServerName=request.getServerName();
String domainname=ServerName.replaceAll("mds.", "");
domainname=domainname.trim();

if(domainname.equals("localhost") ){
	domainname="msmartpay.in";
} 


HashMap hashmap=LoginDao.getDynamicDetails(domainname);
System.out.println("Hmp---"+hashmap);
String client_Id=(String)hashmap.get("clientId");
String page_title=(String)hashmap.get("md_page_title");
String tickerMessage=(String)session.getAttribute("md_message");
System.out.print(client_Id);
String user_type=(String)hashmap.get("user_type");
String mdsFlag=(String)hashmap.get("flag");

session.setAttribute("mdsFlag",mdsFlag);
session.setAttribute("clientId",client_Id);
session.setAttribute("userType",user_type);
session.setAttribute("info",hashmap);

System.out.println("client_Id---"+client_Id+" user_type :: "+user_type);

String accessallowed="allowed";

if(client_Id==null || client_Id.equalsIgnoreCase("null")){
	accessallowed="notallowed";
}

if(accessallowed.equals("allowed")){

%>

<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%= page_title%></title>

<meta name="Description" content=" Payenething :  India's leading online travel portal offers wide range of online travel services through agents and distributors in India. We also provide b2b travel services, travel portal api, travel portal solution, travel portal development and travel portal white label in India."/>
<meta name="Keywords" content="Payenething,Recharge Online,Recharge,Billpay online,Billpay, B2B Travel Agency,B2B Travel Company in India,B2B Travel Portal Company India,B2B Travel Portal India,B2B Travel Services,B2B Travel Sites, Online Travel Agency Business Opportunities, Online Travel Companies in India, Travel Portal Development, Travel Portal White Label, Travel Portal API "/>
<meta name="language" content="EN"/>
<meta name="copyright" content="Company name"/>
<meta name="robots" content="index, follow"/>
<meta name="revisit-after" content="daily"/>
<meta name="Robots" content="index, all"/>
<meta name="allow-search" content="yes"/>
<meta name="revisit-after" content="daily"/>
<meta name="Rating" content="General"/>
<meta name="distribution" content="global"/>

</head>


<frameset rows="100%,*" frameborder=0 border=0 framespacing=0 >
	
	<frame frameborder=0 src="login.jsp" scrolling=auto name="Main" marginwidth=30 marginheight=0 noresize>
<frame src="UntitledFrame-4"></frameset>
<NOFRAMES>
	<P>
	This information on this site is displayed in frames.<BR>
	Either your browser does not support frames, or frame viewing is disabled on your browser.
	</P>
</NOFRAMES>
<%} else {%>
</head>



<title>MDS Panel</title>

<frameset rows="100%,*" frameborder="0" border=0 framespacing=0 >
<frame frameborder=0 src="AccessBlocked.html" scrolling=auto name="Main" marginwidth=30 marginheight=0 noresize>

<frame src="UntitledFrame-4"></frameset>

<NOFRAMES>
<P>
This information on this site is displayed in frames.<BR>
Either your browser does not support frames, or frame viewing is disabled on your browser.
</P>
</NOFRAMES>

<%}%>