<% 

String verificationUserId=(String)session.getAttribute("userId");
String verificationMessage=(String)session.getAttribute("verificationMessage");

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MD Account Verification</title>
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
<form name="loginInfo" method="get">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0"  class="form11" id="border">
  <tr>
    <td valign="top"></td>
    <td height="50">&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="97%" align="left"><table width="556" align="center" cellpadding="0" cellspacing="0">
     
      <tr>
        <td></td>
      </tr>
      <tr>
        <td>
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
			  <td colspan="3" align="center" height="30"><%=verificationMessage%></td>
			  </tr>


				 <tr><td>&nbsp;</td><td align="right">Enter&nbsp;Varify&nbsp;Code&nbsp;From your mailId&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				   <td align="left"><input type="text" name="verificationCode"  class="Trebuchet_normal" style="width:200px;" />				     &nbsp;&nbsp;&nbsp;
				     
				     <input type="hidden" name="param" value="checkVerificationCode" />
				     <input type="hidden" name="userId" value="<%=verificationUserId%>" /></td><td>&nbsp;</td>

				 
				 </tr>
				 
				 <tr><td colspan="4" height="50" valign="middle" align="center" style="padding-left:80px"><input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px" /></td></tr>
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
</table>
</body>

</html>

<script language="javascript">
	
 function validatefrm() {
 
 
  if(document.loginInfo.verificationCode.value=="")
   
  {
			   alert("Plz Enter varify Code from your E-mail id");
			   document.loginInfo.verificationCode.value="";
			   document.loginInfo.verificationCode.focus();
			    return false;

  }


                  document.loginInfo.action="login.action";
				  document.loginInfo.submit();
  }

</script>



