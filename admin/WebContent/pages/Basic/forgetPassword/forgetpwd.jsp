<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Travel E-Point </title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>
</head>

<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" id="headerbg11">
<tr><td  align="center" width="100%" valign="top">
<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">


<tr><td align="center" width="100%" valign="middle" height="95" class="forget">SUPER ADMIN</td></tr>
<tr><td height="15"></td></tr>


<tr><td valign="top" align="center" style="padding-top:30px; padding-bottom:0px"><form name="emailform" method="post">

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="rounded-corners" >

<tr>
<td colspan="100%" >

<table width="86%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">

<tr>
<td height="35" colspan="3" class="heading_mgs">Forgot password</td>
</tr>

<tr>
<td height="35" colspan="3" class="dyn_mgs"><%=message%></td>
</tr>

<tr>
<td colspan="3" height="35">Please enter your Registered E-Mail ID to retrieve your password</td>
</tr>

  
  
  <tr align="left" height="40">
    <td>Email Address</td>
    <td>:</td>
    <td><input type="text"  name="emailID" maxlength="100"/></td>
  </tr>

  <tr align="left" height="65">
    <td></td>
    <td></td>
    <td><input type="button" class="cls_btn" value="Submit" onClick="validateForm()" /><span id="checkRecharge" style="visibility:hidden"><img src="images/loading2.gif" height="30" width="30"/></span></td>
  </tr>

  
</table>




</td></tr>



<tr><td height="20"></td></tr>


</table></form>
</td></tr>

</table></td></tr>
<tr><td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td></tr>
</table>


</body>
</html>
<script language="javascript">
function validateForm()
{


var txt=document.emailform.emailID.value;
if(txt=="")
{
alert("Please enter your e-mail address");
document.emailform.emailID.focus();
return false;
}


else
{



var status = false;     
var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
     if (document.emailform.emailID.value.search(emailRegEx) == -1) 
	 {
          alert("Please enter a valid email address.");
		  return false;
		  
     }
   else
   {
  document.getElementById("checkRecharge").style.visibility = "visible"; 
document.emailform.action="forgetPass.action";
document.emailform.submit();
return true;
}
}


}


</script>