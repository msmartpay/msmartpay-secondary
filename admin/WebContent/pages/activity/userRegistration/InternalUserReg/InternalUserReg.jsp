<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import = "java.util.* "%> 



</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
<%
String loginType=(String)session.getAttribute("loginType");
%>
  <tr>
    <td valign="top" align="center" height="378">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg" >
        <tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
          <tr>
                <td colspan="4" height="10"></td>
              </tr>
              <tr>
                <td  width="100%" valign="top" height="20" align="left" class="heading_mgs">
                <strong>Internal User</strong>
                </td>
              </tr>
              <tr>
                <td colspan="4" height="10"></td>
              </tr>
              <tr>
                <td valign="top" align="center">
                <form  name="userRegistrationForm" method="post">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" id="border" class="mydata_tabl">
                      <tr>
                        <td height="30" colspan="4"></td>
                      </tr>
                      <tr>
                        <td width="35%" height="30" align="right" valign="middle"><strong>Select </strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="30%">
						  <select name="selectUser" >
                            
                            <option value="select">Select</option>                            
                            <option value="activityadmin">Activity Admin</option>   
                       
                          </select></td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
					 
                      <tr>
                        <td></td>
                        <td></td>
                        <td align="left" height="40" style=""><input name="Submit" type="button" value="Continue" class="cls_btn" onclick="submitForm()"/></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="4" height="20"></td>
                      </tr>
                    </table>
                  </form></td>
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <!--footer-->

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>
<script type="text/javascript">
// code for risk dropdown 




function submitForm()
{
var selectUser = document.userRegistrationForm.selectUser.value;
if(selectUser=="select")
{
alert("Please Select User.");
return false;
}
else if(selectUser=="activityadmin")
{
	
document.userRegistrationForm.action="adminUserRegistration.action?param=registerAdmin";
document.userRegistrationForm.submit();
}
else if(selectUser=="Egen"){
document.userRegistrationForm.action="apiClientRegistration.action?param=register";
document.userRegistrationForm.submit();
}	
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
