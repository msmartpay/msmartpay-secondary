<%

String oldPass=(String)request.getAttribute("oldPass");

  String message=(String)request.getAttribute("message");
  if(message==null){
  message="";
  }
  
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Change Password</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script type="text/javascript">

function nospaces(t){

if(t.value.match(/\s/g)){

alert('Space is not Allowed in Password Field.');

t.value=t.value.replace(/\s/g,'');
	return false;	
}

}

</script>
</head>

<body><center>
<form name="loginInfo" method="post">

<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000"><%@ include file="../../header.jsp" %></td>
  </tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

		  
  <tr>
    <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px">Change Password</td>
    </tr>
  <tr>
    <td height="25" colspan="5" align="center"><font color="#FF0000" size="2"><%=message%></font></td>
  </tr>
<tr>
    <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr>
    <td width="100%" align="center"><table width="75%" border="0" cellspacing="0" cellpadding="0" align="center" class="txtt">
  <tr>
    <td height="25" colspan="5" align="center"></td>
  </tr>
   
<tr>
    <td height="10" colspan="5"></td>
  </tr>
    <tr>
    <td width="200"></td><td width="200" align="left">Old Password</td><td align="left" width="100">:</td>
    <td width="280" align="left"><input name="password" type="password" class="password" style="width:250px"  /></td>
    <td width="220"></td>
  </tr>
<input type="hidden" name="oldpass" value="<%=oldPass%>">
<input type="hidden" name="param" value="updateNewPass">


<tr>
    <td height="10" colspan="5"></td>
  </tr>
    <tr>
    <td></td><td align="left">New Password</td><td align="left" >:</td><td  align="left"><input name="newpass" type="password" class="txt" style="width:250px"  maxlength="15" onkeyup="nospaces(this)"/></td><td align="left"><input name="" type="button" class="txt" style="width:60px" onclick="MM_openBrWindow('help_popup.html','help','resizable=yes,width=400,height=230')" value="Help" /></td>
  </tr>
<tr>
    <td height="10" colspan="5"></td>
  </tr>
    <tr>
    <td></td><td align="left">Retype Password</td><td align="left" >:</td><td  align="left"><input name="confirmpass" type="password" class="txt" style="width:250px"  maxlength="15" onkeyup="nospaces(this)"/></td><td ></td>
  </tr>
  <tr>
    <td height="10" colspan="5"></td>
  </tr>
    <tr>
    <td colspan="3"></td><td  align="left" style="padding-right:20px" height="60"><input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px;"></td><td ></td>
  </tr>
    <tr>
    <td height="10" colspan="5"></td>
  </tr>
</table></td>
  </tr>
</table>

</td>
  </tr>
		  

  
    
</table>
</td></tr>
<tr><td height="30"></td></tr>
  <tr>
    <td height="35" width="1000" align="left"><%@ include file="../../footer.jsp" %></td>
  </tr>
  
</table>
</form></center>
</body>
</html>
<script language="javascript">
function validatefrm() {
//alert("we are here");
		 var oldpass=document.loginInfo.oldpass.value;
	     if (document.loginInfo.password.value=="")
              {
			   alert(" Plz enter password");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			   return false;

                  }

			if (document.loginInfo.password.value!==oldpass)
              {
			   alert("Wrong password!!! please enter correct password");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			   return false;

                  }

				 if (document.loginInfo.newpass.value.length<8)
              {
			   alert("Password could not be changed. Please click 'Help'.");
			   document.loginInfo.newpass.value="";
			   document.loginInfo.newpass.focus();
			    return false;

                  }

				 if (document.loginInfo.newpass.value.length>12)
              {
			   alert("Password could not be changed. Please click 'Help'.");
			   document.loginInfo.newpass.value="";
			   document.loginInfo.newpass.focus();
			    return false;

                  }

				    if (document.loginInfo.confirmpass.value=="")
              {
			   alert(" Plz enter confirm password");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			   return false;

                  }

			 if (document.loginInfo.newpass.value!=document.loginInfo.confirmpass.value)
              {
			   alert("password and confirm password is not matching");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			    return false;

                  }

                  document.loginInfo.action="checkloginInfo.do";
				  document.loginInfo.submit();
				 
				
			 }

</script>
