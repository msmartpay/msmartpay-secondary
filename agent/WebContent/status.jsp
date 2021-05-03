<%@page import="java.util.HashMap"%>
<%@page import="com.mobDthDataCardRecharge.MarsController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Status</title>
</head>
<%
HashMap<String, String> mapResult=null;;
String status="N";
if(request.getParameter("marsId")!=null && request.getParameter("marsId").length()>0){
	String marsRefId=request.getParameter("marsId");
	MarsController marsCl=new MarsController();
	mapResult = marsCl.getMarsRequestStatus(marsRefId.trim());
	System.out.println(" Thread call forstatus End Backend2 start ...." +mapResult);
	status="Y";
}

%>
<body>
<form action="status.jsp">
<input type="text" name="marsId">
<input type="submit" value="submit">
</form>
<%if(status.equalsIgnoreCase("Y")) {%>
<div>
<%=mapResult %>
</div>
<%} %>

</body>
</html>