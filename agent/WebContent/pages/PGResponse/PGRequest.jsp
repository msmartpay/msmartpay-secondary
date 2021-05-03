<%@page import="java.util.HashMap"%>
<%@page import="com.utility.PropertyFile"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Request</title>


</head>
<body onload="javascript:document.PgRequest.submit();">
	<form name="PgRequest" action="<%=request.getAttribute("Action")%>" target="_parent" method="post">	
		<input type="hidden" name="MID" value="<%=request.getAttribute("MID")%>" />
		<input type="hidden" name="requestparameter" value="<%=request.getAttribute("requestparameter")%>">
		<input type="hidden" name="billingDtls" value="<%=request.getAttribute("billingDtls")%>">
		<input type="hidden" name="shippingDtls" value="<%=request.getAttribute("shippingDtls")%>">
	</form>
	<div style="width: 100%;" align="center">
		<div align="center"
			style="width: 30%; background-image: URL('images/wait.gif'); height: 190px; background-repeat: no-repeat; margin-top: 10%;">
		</div>
	</div>
</body>
</html>