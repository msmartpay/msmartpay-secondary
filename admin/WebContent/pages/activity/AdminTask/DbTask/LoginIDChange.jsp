<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<link type="text/css" rel="stylesheet" href="css/stickytooltip.css" media="screen" ></link>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>

<script language="javascript" type="text/javascript">

function on_change()
{
var a=	document.getElementById('id_1').value;

if(a== "agent")
{
document.getElementById('show_1').style.display="";	
}

if(a== "mds" || a== "ds" || a== "select" )
{
document.getElementById('show_1').style.display="none";	
}

	
}

</script>


</head>
<%
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
   <td  align="center" width="100%" valign="top"> <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" style="margin-left:100px" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left" style="font-size:13px; font-family:'Trebuchet MS'; color:#930; font-weight:bold;" >Login ID Change</td>                
              </tr>
            
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="LoginIDChangeForm">
            
		  <table cellpadding="0" cellspacing="0" width="86%" align="left" border="0" class="mydata_tabl" id="border">
                
				    <tr>
				      <td colspan="7" height="15"></td>
				      </tr>
				    <tr>
				      <td width="24%" height="30"></td>
				      <td width="12%" align="left">DB Type</td>
				      <td width="5%" align="left">:</td>
				      <td width="59%" colspan="4" align="left">
                      <select  name="dbType">
				        <option value="select" selected="selected">Select</option>
                        <option value="tep">TEP</option>
				        <option value="api">API</option>
				        <option value="client">Client</option>
				        </select></td>
                        
				      </tr>
                      
                     
                      <!-- =========================-->
                       <tr>
				      <td width="24%" height="30"></td>
				      <td width="12%" align="left">User </td>
				      <td width="5%" align="left">:</td>
				      <td width="59%" colspan="4" align="left"><select  id="id_1" name="user" onchange="on_change()">
				        <option value="select" selected="selected">Select</option>
                        <option value="mds">MDS</option>
				        <option value="ds">DS</option>
				        <option value="agent">Agent</option>
				        </select></td>
                        
				      </tr>
                       <tr style="display:none;" id="show_1">
				      <td width="24%" height="30"></td>
				      <td width="12%" align="left">User Type </td>
				      <td width="5%" align="left">:</td>
				      <td width="59%" colspan="4" align="left"><select  name="userType" >
				        <option value="select" selected="selected">Select</option>
                        <option value="tepUser">TEP</option>
				        <option value="repUser">REP</option>
				        
				        </select></td>
                        
				      </tr>
                      
                      
                     <!-- =========================-->
                      
                      
                      <tr>
				      <td width="24%" height="30"></td>
				      <td width="12%" align="left">Old Login ID</td>
				      <td width="5%" align="left">:</td>
				      <td colspan="4" align="left"><input type="text"  class="tabl2" name="oldLoginID"/></td>
				      </tr>
                      
                       <tr>
					  <td  height="30" align="center" valign="middle" width="24%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="12%">New Login ID</td>
						<td  height="5" align="left" valign="middle" width="5%">:</td>
						 <td  height="" colspan="2" align="left" valign="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td width="267" height="25" align="left">
									  <input name="newLoginID" type="text"  class="style3" />
                                        <a href="#" onclick="checkUserName()" style="text-decoration:none;"><font size="1.5" color="red">Check Availability</font></a> </td>
										<td width="27" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="217"><div id="resultEmail"></div></td>
                                    </tr>
                                </table></td>
					    </tr>
                      
                    <tr>
                      <td colspan="7" height="20"></td>
                    </tr>
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td colspan="4" align="left" style="padding-left:0px">  <input name="Submit2" type="button" value="Submit" class="cls_btn"  id="sms" onclick="validateForm()"/></td>
                </tr>
                 
                    <tr>
                      <td colspan="7" height="15"></td>
                    </tr>
			  </table>
              
			  </form>
              
              </td></tr>
			<tr><td colspan="4" height="30"></td></tr>
      </table></td>
  </tr></table></td></tr>
  <tr>
    <td width="100%" height="68" align="center" valign="bottom"></td>
  </tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">

function validateForm(){
var dbType=document.LoginIDChangeForm.dbType.value;
var user=document.LoginIDChangeForm.user.value;

var oldLoginID=document.LoginIDChangeForm.oldLoginID.value;
var newLoginID=document.LoginIDChangeForm.newLoginID.value;
if(dbType=="select"){
alert('Please select ');
return false;
}
if(user=="select"){
alert('Please select ');
return false;
}
if(user=="agent"){
var userType=document.LoginIDChangeForm.userType.value;
if(userType=="select"){
alert('Please select ');
return false;
}
}
if(oldLoginID==""){
alert('Please select ');
return false;
}
if(newLoginID==""){
alert('Please select ');
return false;
}
document.LoginIDChangeForm.action="dbTask.action?param=loginIDChange";
document.LoginIDChangeForm.submit();
}
</script>