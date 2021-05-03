


<% 


String messageAfterVerification=(String)session.getAttribute("message2");

String md_login_url=(String)session.getAttribute("md_login_url");
String page_title=(String)session.getAttribute("md_page_title");
String admin_url=(String)session.getAttribute("admin_login_url");

if(messageAfterVerification==null){

messageAfterVerification="";

}
System.out.println(admin_url);





%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Travel E-Point</title>
<link href="../../css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />



</head>

<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header3.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
		  
		  		  
              
			  
			  <tr>
                <td height="250" colspan="5" align="center" valign="middle">
<form name="loginInfo" method="post">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0"  class="form11" id="border">
  <tr>
    <td valign="top"></td>
    <td>&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="97%" align="center"><table width="556" align="center" cellpadding="0" cellspacing="0">
  
      <tr>
        <td>
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr><td colspan="5" align="center"><%=messageAfterVerification%> &nbsp;&nbsp;&nbsp;&nbsp;<a HREF="<%=admin_url%>">Please click here to Login</A>
				 </td></tr>
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
				</td>
              </tr>
			  
			  
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer2.jsp" %></td>
  </tr>
</table></body>
</html>

<script language="javascript">
	
</script>



