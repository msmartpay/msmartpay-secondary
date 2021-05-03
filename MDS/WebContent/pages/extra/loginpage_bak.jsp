<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%>


<% 

Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String date = formatter.format(todate);


String PrintMessage="";
String Loginmessage=(String)session.getAttribute("messagepass");


if(Loginmessage==null){

PrintMessage="";

}


else

{
PrintMessage=Loginmessage;

}




String hourtime="";
String minuteTime="";


 hourtime=(String)session.getAttribute("hourtime");


 minuteTime=(String)session.getAttribute("minutTime");

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%></title>
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
    <td><%@ include file="../../header1.jsp" %></td>
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
              <tr><input type="hidden" name="mdId" value="<%=mdId%>" />
				 <input type="hidden" name="param" value="checkloginInfo" />
                <td height="40" class="textnormal" align="center"> 
                  <%=PrintMessage%>
               
                 </td>
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
    <td><%@ include file="../../footer.jsp" %></td>
    <td valign="bottom">&nbsp;</td>
  </tr>
</table>
</form>
</body>
</center>
</html>

<script language="javascript">
	 function validatefrm() {

              

                  document.loginInfo.action="checkloginInfo.do";
				  document.loginInfo.submit();
				 
				 

                 

			 }

</script>



