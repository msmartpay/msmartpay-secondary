<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%>


<% 
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String date = formatter.format(todate);

String PrintMessage="";
String Loginmessage=(String)session.getAttribute("Loginmessage");


if(Loginmessage==null){

PrintMessage="";

}


else

{
PrintMessage=Loginmessage;

}

String password=(String)session.getAttribute("password");


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

<body >
<center>
<body >
<center>
<form name="loginInfo" method="post">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td valign="top"></td>
    <td align="center"><%@ include file="../../header1.jsp" %></td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="90%" align="center">
	<table width="556" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
    
      
    
      <tr>
        <td align="center">
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> <input type="hidden" name="mdId" value="<%=mdId%>">
				  <input type="hidden" name="oldpass" value="<%=password%>">
				 <input type="hidden" name="param" value="changePassemforEmailverify">
              </tr>
			   <tr><td  colspan="7" align="center" style="font-family: arial,sans-serif; font-size: 9pt;"><%=PrintMessage%></td></tr>
              <tr>
                <td colspan="7">&nbsp;</td>
              </tr>
			  <tr><td  align="center" valign="top" class="maintext" width="38%" colspan="7">&nbsp;
				 
				 
				
				 </td></tr>
		<tr>
		<td width="10%" align="right" style="font-family: arial,sans-serif; font-size: 10pt;" colspan="3">New&nbsp;Password&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="left" width="40%" colspan="5"><input type="password" name="password" class="Trebuchet_normal" size="20" ><font size="1" color="red">&nbsp;&nbsp;password length should be of 6 to 10  character.</font>&nbsp;&nbsp;&nbsp;
				 
				 
				 
				 </td></tr>
		<tr><td align="right" style="font-family: arial,sans-serif; font-size: 10pt;" colspan="3">Confirm&nbsp;password&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  <td align="left" colspan="5"><input type="password" name="confirmpass" class="Trebuchet_normal" size="20" />
		    <font size="1" color="red">&nbsp;&nbsp;enter same as above</font>&nbsp;&nbsp; </td>
		</tr>
			   <tr><td align="right" style="font-family: arial,sans-serif; font-size: 10pt;" colspan="3">E-mail&nbsp;Id&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="left"><input type="text" name="emailid" id="email" class="Trebuchet_normal" size="20" ><font size="1" color="red">&nbsp;&nbsp;enter a valid E-mail Id.</font>&nbsp;&nbsp;&nbsp;

				 
				 
				 
				 </td></tr>
		<tr><td colspan="5" align="center" style="font-family: arial,sans-serif; font-size: 10pt;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="check"> I agree to receive service updates and account information through email/SMS.&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px; height:30px;">
          </td></tr> 
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

		 var oldpass=document.loginInfo.oldpass.value;

             
           if (document.loginInfo.password.value=="")
              {
			   alert(" Plz enter password");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			   return false;

                  }

			if (document.loginInfo.password.value==oldpass)
              {
			   alert("New password should be diffrent from existing password");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			   return false;

                  }

				 if (document.loginInfo.password.value.length<=5)
              {
			   alert("password length should be of 6  character or more");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			    return false;

                  }

				 if (document.loginInfo.password.length>10)
              {
			   alert("password length should be of 10  character  or less");
			   document.loginInfo.password.value="";
			   document.loginInfo.password.focus();
			    return false;

                  }

				    if (document.loginInfo.confirmpass.value=="")
              {
			   alert(" Plz enter confirm password");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			   return false;

                  }

			 if (document.loginInfo.password.value!=document.loginInfo.confirmpass.value)
              {
			   alert("password and confirm password is not matching");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			    return false;

                  }

				  
		   

				  
	     document.loginInfo.emailid.value=document.loginInfo.emailid.value.replace(/^\s+|\s+$/, '');	    

		     if(document.loginInfo.emailid.value=="")
                {
			   alert("Plz Enter E-mail Address");
			   document.loginInfo.emailid.value="";
			   document.loginInfo.emailid.focus();
			    return false;

                  }




				 if(!emailCheck(document.loginInfo.emailid.value))
	            {
					
		              return false;
	           }

			   var checkVal=document.loginInfo.check.checked;

			     if(checkVal==false)
	            {
					alert("Plz Select Check Box to agree to receive service")
		              return false;
	           }



                  document.loginInfo.action="checkloginInfo.do";
				  document.loginInfo.submit();
				 
				
			 }

</script>


<script language="javascript">

function emailCheck(emailStr) {
	var checkTLD=1;
	var knownDomsPat=/^(com|net|org|edu|int|mil|gov|arpa|biz|aero|name|coop|info|pro|museum)$/;
	var emailPat=/^(.+)@(.+)$/;
	var specialChars="\\(\\)><@,;:\\\\\\\"\\.\\[\\]";
	var validChars="\[^\\s" + specialChars + "\]";
	var quotedUser="(\"[^\"]*\")";
	var ipDomainPat=/^\[(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\]$/;
	var atom=validChars + '+';
	var word="(" + atom + "|" + quotedUser + ")";
	var	userPat=new RegExp("^" + word + "(\\." + word + ")*$");
	var domainPat=new RegExp("^" + atom + "(\\." + atom +")*$");
	var matchArray=emailStr.match(emailPat);

	if (matchArray==null) 
	{
		alert("Email address seems incorrect (check @ and .'s)");
		return false;
	}
	var user=matchArray[1];
	var domain=matchArray[2];

	for (i=0; i<user.length; i++) 
	{
		if (user.charCodeAt(i)>127) 
		{
			alert("Email address seems incorrect (check @ and .'s)");
			return false;
		}
	}
	for (i=0; i<domain.length; i++) 
	{
		if (domain.charCodeAt(i)>127) 
		{
			alert("The domain name in Email Address contains invalid characters.");
			return false;
		}
	}
	if (user.match(userPat)==null) 
	{
		alert("The email doesn't seem to be valid.");
		return false;
	}
	var IPArray=domain.match(ipDomainPat);
	if (IPArray!=null) 
	{
		for (var i=1;i<=4;i++) 
		{
			if (IPArray[i]>255)
			{
				alert("Destination IP address in Email Address is invalid!");
				return false;
			}
		}
		return true;
	}
 
	var atomPat=new RegExp("^" + atom + "$");
	var domArr=domain.split(".");
	var len=domArr.length;
	for (i=0;i<len;i++) 
	{
		if (domArr[i].search(atomPat)==-1) 
		{
			alert("The domain name in Email Address does not seem to be valid.");
			return false;
	   }
	}
	if (checkTLD && domArr[domArr.length-1].length!=2 &&	domArr[domArr.length-1].search(knownDomsPat)==-1) 
	{
		alert("The Email Address must end in a well-known domain or two letter " + "country.");
		return false;
	}
	if (len<2) 
	{
		alert("This Email Address is missing a hostname!");
		return false;
	}
	return true;
}




function chk_keys(){
if(event.keyCode==17){
alert('invalid action');
}
}

 function alertmessage(){
 alert("mandatory field");
 }
function digitonly(input,evt)

{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) {
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) {
return true;
}

return false;
}





</script>









