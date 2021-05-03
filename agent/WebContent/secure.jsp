<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN"
   "http://www.w3.org/TR/html4/frameset.dtd">
<%@ page import="java.util.HashMap" %>
<%@ page import="com.TickerMessage.TickerMessageDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<% 

String message=(String)session.getAttribute("message");
if(message==null)message="";
String ServerName=request.getServerName();
String serverdomain="";
serverdomain=ServerName.replaceAll("agent.","");
serverdomain=serverdomain.trim();
if( serverdomain.equals("localhost") || serverdomain.equals("13.235.68.132")){
	serverdomain="msmartpay.in";
}
TickerMessageDao daoObj=new TickerMessageDao();
HashMap<String,String> dynamicData=daoObj.getDynamicDetails(serverdomain);
session.setAttribute("dynamicData",dynamicData);
String clientId=(String)dynamicData.get("clientId");


session.setAttribute("clientId",clientId);
session.setAttribute("companyName",dynamicData.get("companyName"));

%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ sessionScope.companyName}</title>

<meta name="Description" content=" MSmart :  India's leading online travel portal offers wide range of online travel services through agents and distributors in India. We also provide b2b travel services, travel portal api, travel portal solution, travel portal development and travel portal white label in India."/>
<meta name="Keywords" content="MSmart,Recharge Online,Recharge,Billpay online,Billpay, B2B Travel Agency,B2B Travel Company in India,B2B Travel Portal Company India,B2B Travel Portal India,B2B Travel Services,B2B Travel Sites, Online Travel Agency Business Opportunities, Online Travel Companies in India, Travel Portal Development, Travel Portal White Label, Travel Portal API "/>
<meta name="language" content="EN"/>
<meta name="copyright" content="MSmart"/>
<meta name="robots" content="index, follow"/>
<meta name="revisit-after" content="daily"/>
<meta name="Robots" content="index, all"/>
<meta name="allow-search" content="yes"/>
<meta name="revisit-after" content="daily"/>
<meta name="Rating" content="General"/>
<meta name="distribution" content="global"/>
<%

String accessallowed="allowed";
if(clientId==null || clientId.equalsIgnoreCase("null")){
accessallowed="notallowed";
}
if(accessallowed.equals("allowed")){

%>

</head>

<title>${ sessionScope.companyName}</title>

<frameset rows="100%,*" frameborder=0 border=0 framespacing=0 >
	
	<frame frameborder=0 src="index.jsp" scrolling=auto name="Main" marginwidth=30 marginheight=0 noresize>

</frameset>
<NOFRAMES>
	<P>
	This information on this site is displayed in frames.<BR>
	Either your browser does not support frames, or frame viewing is disabled on your browser.
	</P>
</NOFRAMES>

<%} else {%>
<HEAD>

</head>



<title>Agent Panel</title>

<frameset rows="100%,*" frameborder=0 border=0 framespacing=0 >
<frame frameborder=0 src="AccessBlocked.html" scrolling=auto name="Main" marginwidth=30 marginheight=0 noresize>

<frame src="UntitledFrame-4"></frameset>

<NOFRAMES>
<P>
This information on this site is displayed in frames.<BR>
Either your browser does not support frames, or frame viewing is disabled on your browser.
</P>
</NOFRAMES>

<%}%>


