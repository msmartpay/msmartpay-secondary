<%@page import="com.pgreturn.PGReturnResponse"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Response</title>
<%
String responseparams=request.getParameter("responseparams");
System.out.print("Response : "+responseparams);
String hash=request.getParameter("hash");
System.out.print("hash : "+hash);
//PGReturnResponse dao=new PGReturnResponse();
//dao.parsePGResponse(responseparams,hash);

%>
</head>
<body onload="javascript:document.PgResponse.submit();">
<form name="PgResponse" action="../../walletTopup.do" method="post">
	<input type="hidden" name="responseparams" value="<%=responseparams %>" />
	<input type="hidden" name="hash" value="<%=hash %>" />
	<input type="hidden" name="param" value="topupRequest" />
	
</form>
<div style="width: 100%;" align="center">
	<div align="center"
			style="width: 30%; background-image: URL('images/wait.gif'); height: 190px; background-repeat: no-repeat; margin-top: 10%;">
		</div>
</div>

</body>
</html>