<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%>


<% 

Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String date = formatter.format(todate);



String mdCodeId=(String)session.getAttribute("mdId");
String message2=(String)session.getAttribute("mobilemessage");



%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MD Mobile Verification</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-color: #999999;
}
-->
</style>
<link href="css/travel.css" rel="stylesheet" type="text/css" />
<link href="../../css/travel.css" rel="stylesheet" type="text/css" />
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<link href="../../css/prime.css" rel="stylesheet" type="text/css" />





</head>

<body>
<center>
<form name="loginInfo" method="post">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td valign="top"></td>
    <td>&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="97%" align="center"><table width="556" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
     
      <tr>
        <td></td>
      </tr>
      <tr>
        <td>
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr><td colspan="3" align="center" style="font-family: arial,sans-serif; font-size: 10pt;">
			   <table width="100%" border="0" height="50" align="center" cellpadding="0" cellspacing="0"><%=message2%></table></td>
				 </tr>


				 <tr><table width="100%" border="0" height="50" align="center" cellpadding="0" cellspacing="0"><td align="left" style="font-family: arial,sans-serif; font-size: 10pt;">Enter&nbsp;Varify&nbsp;Code&nbsp;From your mobile number&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="left">
				 <input type="text" name="mobileverifyCode"  class="Trebuchet_normal" size="20" >&nbsp;&nbsp;&nbsp;
				 <input type="hidden" name="param" value="checkMobileVerificationCode" />
				 <input type="hidden" name="mdId" value="<%=mdCodeId%>" />
				  <input type="button" name="but1" value="Submit" onClick="validatefrm()" /></td></table>
				 </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table></td>
      </tr>
      
    </table></td>
    <td width="1%" rowspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="19" valign="bottom">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="bottom">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</center>
</html>

<script language="javascript">
	
 function validatefrm() {
 
 
  if(document.loginInfo.mobileverifyCode.value=="")
                {
			   alert("Plz Enter varify Code from your mobile number");
			   document.loginInfo.mobileverifyCode.value="";
			   document.loginInfo.mobileverifyCode.focus();
			    return false;

                  }


                  document.loginInfo.action="checkloginInfo.do";
				  document.loginInfo.submit();
                 }

</script>



