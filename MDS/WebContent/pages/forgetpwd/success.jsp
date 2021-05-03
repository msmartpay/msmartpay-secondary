<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
 
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String date = formatter.format(todate);
String printmsg="";
String msg=(String)session.getAttribute("msg");
if(msg==null)
{
printmsg="";
}
else
printmsg=msg;


%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>::Forget Password</title>
<link href="images/icon.png" rel="icon" /><link href="css/mob_admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/menuscript.js"></script>

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>

<SCRIPT LANGUAGE="JavaScript">

<!-- Begin
function textCounter(field, countfield, maxlimit) {
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else 
countfield.value = maxlimit - field.value.length;
}
// End -->
</script>

<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<SCRIPT type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>
</head>

<body>
<form name="ForgetPass" >


 	
	<TABLE style="margin:auto;" cellSpacing=0 cellPadding=0 width=980 border=0 align="center" >
	<tr><td width="100%" height="50" align="center"><%@ include file="../../header1.jsp" %></td></tr>
  <TBODY>
 <p>&nbsp;</p>
 <TABLE style="margin:auto;" cellSpacing=0 cellPadding=0 width=980 border=0 align="center" >
 
 	<tr><td width="100%" height="50" align="center"><%=printmsg%></td></tr>
	
	<p>&nbsp;</p>
	<tr><td width="100%" height="50" align="center"><a href="secure.jsp">Click here to login</a></td></tr>
	
	<p>&nbsp;</p>
	
	<tr><td width="100%" height="50" align="center"></td></tr>
		<p>&nbsp;</p>
	</TBODY>
	
	</TABLE>
	
	
	<TABLE style="margin:auto;" cellSpacing=0 cellPadding=0 width=980 border="0" align="center" >

	 <tr>
	<td width="100%" height="50" align="center"><%@ include file="../../footer.jsp" %></td>
	</tr>
	</TABLE>
   

</form>


</body>
</html>
