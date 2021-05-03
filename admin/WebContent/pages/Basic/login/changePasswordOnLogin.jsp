<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%>
<% 
String userId=(String)session.getAttribute("userId");

String changePasswordMessage=(String)session.getAttribute("changePasswordMessage");
if(changePasswordMessage==null){
	changePasswordMessage="";
}




%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%></title>
<!--<title><%=session.getAttribute("headerName") %> :: Agent Cell :: Home</title>-->
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
          <td valign="top" align="center" class="rounded-corners" ><form name="loginInfo" method="post">

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td valign="top"></td>
    <td align="center"></td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="90%" align="center">
	<table width="80%" align="center" cellpadding="0" cellspacing="0"  class="form11" id="border">
    
      
    
      <tr>
        <td align="center">
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr> <input type="hidden" name="userId" value="<%=userId%>">
				  <input type="hidden" name="oldPassword" value="">
				 <input type="hidden" name="param" value="changePassword">
              </tr>
			   <tr><td  colspan="5" align="center"><%=changePasswordMessage%></td></tr>
			  <tr><td  align="center" valign="top" class="maintext" colspan="5" height="20">&nbsp;
				 
				 
				
				 </td></tr>
		<tr>
		<td width="10%" align="right" colspan="3" height="30">New&nbsp;Password&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="left" width="40%" colspan="5"><input type="password" name="password" class="Trebuchet_normal" style="width:200px" / ><font size="1" color="red">&nbsp;&nbsp;password length should be of 6 to 10  character.</font>&nbsp;&nbsp;&nbsp;
				 
				 
				 
				 </td></tr>
		<tr><td align="right" colspan="3" height="30">Confirm&nbsp;password&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  <td align="left" colspan="5"><input type="password" name="confirmpass" class="Trebuchet_normal" style="width:200px" />
		    <font size="1" color="red">&nbsp;&nbsp;enter same as above</font>&nbsp;&nbsp; </td>
		</tr>
			   <tr><td align="right" colspan="3" height="30">E-mail&nbsp;Id&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td align="left"><input type="text" name="emailId" id="email" class="Trebuchet_normal" style="width:200px" ><font size="1" color="red">&nbsp;&nbsp;enter a valid E-mail Id.</font>&nbsp;&nbsp;&nbsp;

				 
				 
				 
				 </td></tr>
		<tr><td colspan="5" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="check"> I agree to receive service updates and account information through email/SMS.
          </td></tr> 
		  <tr><td colspan="5" align="center" height="50" valign="middle"><input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px; height:30px;">
          </td></tr>
		  
            </table></td>
      </tr>
      
    </table></td>
    <td width="1%" rowspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td height="19" valign="bottom">&nbsp;</td>
    <td></td>
    <td valign="bottom">&nbsp;</td>
  </tr>
</table>
</form></td>
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

		 
	var password=document.loginInfo.password.value;
	if(password=="")
	{
		alert("Please enter new password.");
		document.loginInfo.password.focus();
		return false;
	}
	
	var passed = checkPassword(document.loginInfo.password.value, {
	length:   [6, 15],
	lower:    1,
	upper:    1,
	numeric:  1,
	special:  1,
	badWords: ["password"],
	badSequenceLength: 4
		});

		
		
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

function checkPassword(pw,options)
	{
	var o = {
		lower:    0,
		upper:    0,
		alpha:    0, /* lower + upper */
		numeric:  0,
		special:  0,
		length:   [0, Infinity],
		custom:   [ /* regexes and/or functions */ ],
		badWords: [],
		badSequenceLength: 0,
		noQwertySequences: false,
		noSequential:      false
	};

	for (var property in options)
		o[property] = options[property];

	var	re = {
			lower:   /[a-z]/g,
			upper:   /[A-Z]/g,
			alpha:   /[A-Z]/gi,
			numeric: /[0-9]/g,
			special: /[\W_]/g
		},
		rule, i;

	// enforce min/max length
	if (pw.length < o.length[0] || pw.length > o.length[1])
	{
		//alert("wrong length");
		alert("Password length should be of 6 characters min and 15 characters");
		return false;
	}
	// enforce lower/upper/alpha/numeric/special rules
	for (rule in re) 
	{
		
		if ((pw.match(re[rule]) || []).length < o[rule])
		{
			alert("Enter password must contain at least one lower and one upper alpha,one numeric,one special");
			return false;
			
		}
	}

	// enforce word ban (case insensitive)
	for (i = 0; i < o.badWords.length; i++)
	{
		if (pw.toLowerCase().indexOf(o.badWords[i].toLowerCase()) > -1)
		{
			alert("Enforce word can (case insensitive)");
			return false;
		}
	}

	// enforce the no sequential, identical characters rule
	if (o.noSequential && /([\S\s])\1/.test(pw))
	{
		alert("Identical characters rule");
		return false;
	}
	if (document.loginInfo.confirmpass.value=="")
              {
			   alert(" Please enter confirm password");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			   return false;

                  }

			 if (document.loginInfo.password.value!=document.loginInfo.confirmpass.value)
              {
			   alert("Password and confirm password is not matching");
			   document.loginInfo.confirmpass.value="";
			   document.loginInfo.confirmpass.focus();
			    return false;

                  }

				  
		   

				  
	     document.loginInfo.emailId.value=document.loginInfo.emailId.value.replace(/^\s+|\s+$/, '');	    

		     if(document.loginInfo.emailId.value=="")
                {
			   alert("Please Enter E-mail Address");
			   document.loginInfo.emailId.value="";
			   document.loginInfo.emailId.focus();
			    return false;

                  }




				 if(!emailCheck(document.loginInfo.emailId.value))
	            {
					
		              return false;
	           }

			   var checkVal=document.loginInfo.check.checked;

			     if(checkVal==false)
	            {
					alert("Please Select Check Box to agree to receive service")
		              return false;
	           }



                  document.loginInfo.action="login.action";
				  document.loginInfo.submit();
				 
				
}



</script>
