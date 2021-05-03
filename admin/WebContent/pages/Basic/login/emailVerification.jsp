


<% 


String emailVerificationMessage=(String)session.getAttribute("emailVerificationMessage");

String loginId=(String)session.getAttribute("userId");


if(emailVerificationMessage==null){

emailVerificationMessage="";

}


String password=(String)session.getAttribute("password");




%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Travel E-Point </title>
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
                <td height="250" align="center" valign="middle">
<form name="loginInfo" method="post">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0"  class="form11" style="padding:15px;">
  
  <tr>
   
      
        <td align="left" colspan="3">
         <%=emailVerificationMessage%> <br><font color="red">In case your email ID is wrong, please enter a valid email ID in the following box.</font>
				 
				 
				 
				<input type="hidden" name="userId" value="<%=loginId%>" />
				<input type="hidden" name="param" value="sendmail" /></td>
             </tr>

				
				 <tr align="left"><td align="left" height="30">E-mail&nbsp;Address&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="text" name="emailId" id="email" style="width:250px;" />
				 
				 
				 
				</td>
      </tr>
      
	  <tr align="left"><td align="center" valign="middle" height="50" style="padding-right:90px;">
				 <input type="button" name="but1" value="Submit" onClick="validatefrm()" style="width:80px;" />
				 
				 
				 
				</td>
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


      
/*function emailCheck(emailStr) {
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

*/
	
	 function validatefrm() {

		

		     if(document.loginInfo.emailId.value=="")
                {
			   alert("Plz Enter E-mail Address");
			   document.loginInfo.emailId.value="";
			   document.loginInfo.emailId.focus();
			    return false;

                  }




				 /* if(!emailCheck(document.loginInfo.emailId.value))
	            {
					
		              return false;
	           }
*/



                  document.loginInfo.action="login.action";
				  document.loginInfo.submit();
				 
				
			 }

</script>



