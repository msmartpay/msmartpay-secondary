
<%@page import="com.login.LoginDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instant kyc Processing</title>

    
    <style type="text/css">
		.form-group label{
			font-weight: bold;
		}
		.err_msg{
			margin-top: 10px;
		    background: #0080008a;
		    color: #fff;
		    padding: 5px;
		    border-radius: 5px;
		    display: none;
		    text-align: center;
	    	font-size: 14px;
		}
	</style>
<%
	
	/* StringBuffer url = request.getRequestURL();
	String uri = request.getRequestURI();
	String ctx = request.getContextPath();
	String base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/"; */

	String senderId=request.getParameter("SenderId"); 
	String agentId=request.getParameter("agentId");
	String AutKey=request.getParameter("AutKey");

%>
</head>
<body onload="document.kyc.submit();">


<form action="instantEkoCustomerKyc.action" method="post" name="kyc">
	<input type="hidden" name="SenderId" value="<%=senderId %>" />
	<input type="hidden" name="agentId" value="<%=agentId %>" />
	<input type="hidden" name="TxnKey" value="<%=AutKey %>" />
</form>

</body>
</html>