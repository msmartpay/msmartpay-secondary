<% 
String mobVerUserId=(String)session.getAttribute("userId");
String mobileVerificationMessage=(String)session.getAttribute("mobileVerificationMessage");

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Mobile Verification</title>
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
        <td></td>
      </tr>
      <tr>
        <td>
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr><td colspan="3" align="center">
			   <table width="100%" border="0" height="50" align="center" cellpadding="0" cellspacing="0"><%=mobileVerificationMessage%></table></td>
				 </tr>


				 <tr><table width="100%" border="0" height="50" align="center" cellpadding="0" cellspacing="0"><td align="left">Enter&nbsp;Varify&nbsp;Code&nbsp;From your mobile number&nbsp;&nbsp;</td><td align="left">
				 <input type="text" name="mobileVerificationCode"  class="Trebuchet_normal" style="width:150px" >&nbsp;&nbsp;&nbsp;
				 <input type="hidden" name="param" value="checkMobileVerificationCode" />
				 <input type="hidden" name="userId" value="<%=mobVerUserId%>" />
				  </td></table>
				 </tr>
              <tr>
                <td align="center" height="60" valign="middle"><input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px;" /></td>
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
</table>
</body>

</html>

<script language="javascript">
	
 function validatefrm() {
 
 
  if(document.loginInfo.mobileVerificationCode.value=="")
                {
			   alert("Plz Enter varify Code from your mobile number");
			   document.loginInfo.mobileVerificationCode.value="";
			   document.loginInfo.mobileVerificationCode.focus();
			    return false;

                  }


                  document.loginInfo.action="login.action";
				  document.loginInfo.submit();
                 }

</script>



