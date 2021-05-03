<%
String loginUserName=(String)session.getAttribute("loginUserName");
String message=(String)request.getAttribute("message");
if(message==null)message="";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs" >Change Password</td>
              </tr>
              <tr>
                <td colspan="4" height="20"></td>
              </tr>
              <tr>
                <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="form11" id="border">
                    <tr>
                      <td width="95%" height="20"></td>
                    </tr>
                    <tr>
                      <td height="20">
					  <form name="changePasswordForm" method="post"><table width="76%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  colspan="10" align="center" class="dyn_mgs"><%=message%></td>
                          </tr>
                          <tr>
 				 			<td  colspan="10" align="center" style="font-size:18px; font-weight:bold; color:#FF0000; font-family:'Trebuchet MS';"></td>
						 </tr>
                          <tr>
                            <td></td>
                            <td align="left"><strong>Login Name</strong></td>
                            <td align="left" >:</td>
                            <td  align="left"><%=loginUserName%></td>
                            <td ></td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5"></td>
                          </tr>
                          <tr>
                            <td width="74"></td>
                            <td width="126" align="left"><strong>Old Password</strong></td>
                            <td align="left" width="25">:</td>
                            <td width="261" align="left"><input name="oldPassword" type="password" class="txtt" style="width:250px" value="" /></td>
                            <td width="131"></td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5"></td>
                          </tr>
                          <tr>
                            <td></td>
                            <td align="left"><strong>New Password</strong></td>
                            <td align="left" >:</td>
                            <td  align="left"><input name="newPassword" type="password" class="txtt" style="width:250px" /></td>
                            <td align="left"><a href="" onclick="checkHelp()">Help</a></td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5"></td>
                          </tr>
                          <tr>
                            <td></td>
                            <td align="left"><strong>Retype Password</strong></td>
                            <td align="left" >:</td>
                            <td  align="left"><input name="password" type="password" class="txtt" style="width:250px" /></td>
                            <td ></td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5"></td>
                          </tr>
                          <tr>
                            <td colspan="3"></td>
                            <td  align="left"><input name="" type="button" class="txtt" value="Change Password" style="width:130px"  onclick="submitForm()" /></td>
                            <td ></td>
                          </tr>
                        </table>
					  </form></td>
						
						
                    </tr>
                    <tr>
                      <td height="20"></td>
                    </tr>
                    <tr>
                      <td colspan="5" height="20"></td>
                    </tr>
                  </table></td>
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" height="72" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script>
function checkHelp()
{
alert("Your password must contain at least one of below \n 1 Capital Alpha Character i.e. [A, B,C,Z] \n 1 Small Alpha Character i.e. [a, b,c-z] \n 1 Numeric Character i.e. [0, 1,2-9] \n 1 Special Character from list as given [!, @, #, $, %, ^, &, *, _, +, ~ Spaces not Allowed]");
}

function submitForm(){
	var oldPassword=document.changePasswordForm.oldPassword.value;
	if(oldPassword=="")
	{
		alert("Please enter old password");
		document.changePasswordForm.oldPassword.focus();
		return false;
	}
	
	var passed = checkPassword(document.changePasswordForm.newPassword.value, {
	length:   [6, 15],
	lower:    1,
	upper:    1,
	numeric:  1,
	special:  1,
	badWords: ["password"],
	badSequenceLength: 4
		});


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
			special: /[!@#$%^&*_+~]/g
		},
		rule, i;

	// enforce min/max length
	if (pw.length < o.length[0] || pw.length > o.length[1])
	{
		//alert("wrong length");
		alert("password length should be of 6 characters min and 15 characters");
		return false;
	}
	// enforce lower/upper/alpha/numeric/special rules
	for (rule in re) 
	{
		
		if ((pw.match(re[rule]) || []).length < o[rule])
		{
			alert("Your password must contain at least one of below \n 1 Capital Alpha Character i.e. [A, B,C,Z] \n 1 Small Alpha Character i.e. [a, b,c-z] \n 1 Numeric Character i.e. [0, 1,2-9] \n 1 Special Character from list as given [!, @, #, $, %, ^, &, *, _, +, ~ Spaces not Allowed]");
			return false;
			
		}
	}

	// enforce word ban (case insensitive)
	for (i = 0; i < o.badWords.length; i++)
	{
		if (pw.toLowerCase().indexOf(o.badWords[i].toLowerCase()) > -1)
		{
			alert("// enforce word ban (case insensitive)");
			return false;
		}
	}

	// enforce the no sequential, identical characters rule
	if (o.noSequential && /([\S\s])\1/.test(pw))
	{
		alert("identical characters rule");
		return false;
	}
	var newPassword=document.changePasswordForm.newPassword.value;
	if(newPassword=="")
	{
		alert("Please enter New password");
		document.changePasswordForm.newPassword.focus();
		return false;
	}
	
	var password=document.changePasswordForm.password.value;
	if(password=="")
	{
		alert("Please Retype new password");
		document.changePasswordForm.password.focus();
		return false;
	}
	if(document.changePasswordForm.newPassword.value!=document.changePasswordForm.password.value)
	{
		alert("Password entered in New Password and Retype Password does not match.Please enter correct information carefully to change your password.");
		document.registration.confirmPassword.focus();
		return false;
	}
	document.changePasswordForm.action="changePassword.action?param=changePassword";
	document.changePasswordForm.submit();
		
}
</script>

<script language="JavaScript">



var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

</script>