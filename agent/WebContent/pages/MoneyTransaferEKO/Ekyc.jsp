<%@page import="com.utility.PropertyFile"%>
<%@page import="org.apache.commons.lang.RandomStringUtils"%>
<%@page import="java.security.NoSuchAlgorithmException"%>
<%@page import="java.security.InvalidKeyException"%>
<%@page import="javax.crypto.spec.SecretKeySpec"%>
<%@page import="javax.crypto.Mac"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>E-kyc Processing</title>

<script type="text/javascript">
function submitEkyc(){
	document.ekyc.submit();
	return true;
}
</script>

</head>
<body onload="submitEkyc()">

<div class="col-md-12 col-md-offset-3" style="padding-top: 10%;">
	<center><img alt="loading" src="images/please_wait.gif"></center>
	
</div>


</body>
</html>