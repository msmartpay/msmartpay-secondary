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
   <td  align="center" width="100%" valign="top"> 
   <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" style="margin-left:100px" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left" style="font-size:13px; font-family:'Trebuchet MS'; color:#930; font-weight:bold;">Balances</td>                
              </tr>
            
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="BalanceForm">
            
		  <table cellpadding="0" cellspacing="0" width="86%" align="left" border="0"  class="mydata_tabl" id="border">
                
                  <tr>
				      <td width="26%" height="20"></td>
				      <td width="13%" align="left"></td>
				      <td width="6%" align="left"></td>
				      <td width="55%" colspan="4" align="left"></td>
				      </tr>
	            
				  
				    <tr>
				      <td width="26%" height="30"></td>
				      <td width="13%" align="left">DB Type</td>
				      <td width="6%" align="left">:</td>
				      <td width="55%" colspan="4" align="left"><select  name="dbType" >
				        <option value="select" selected="selected">Select</option>
                        <option value="tep">TEP</option>
				        <!-- <option value="api">API</option> -->
				        <!-- <option value="client">Client</option> -->
				        </select></td>
                        
				      </tr>
                      
                       <tr>
				      <td width="26%" height="30"></td>
				      <td width="13%" align="left">Parent User</td>
				      <td width="6%" align="left">:</td>
				      <td colspan="4" align="left"><select  name="parentUser" >
				        <option value="select" selected="selected">Select</option>
						<option value="superadmin">Super Admin</option>
				        <option value="admin">Admin</option>
				        <option value="mds">MDS</option>
				        <option value="ds">DS</option>
				        <option value="agent">Agents</option>
				        </select></td>
                        
				      </tr>
                      
                      <tr>
				      <td width="26%" height="30"></td>
				      <td width="13%" align="left">Parent User ID</td>
				      <td width="6%" align="left">:</td>
				      <td colspan="4" align="left"><input type="text"  class="tabl2" name="parentID"/></td>
				      </tr>
                      
                      <tr>
				      <td width="26%" height="30"></td>
				      <td width="13%" align="left">Type of User</td>
				      <td width="6%" align="left">:</td>
				      <td colspan="4" align="left"><select  name="child" >
				        <option value="select" selected="selected">Select</option>
                        <option value="admin">Admin</option>
				        <option value="mds">MDS</option>
                        <option value="ds">DS</option>
                        <option value="agent">Agents</option>
				        </select></td>
				      </tr>
                      
                      <tr>
                      <td colspan="7" height="10"></td>
                    </tr>
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td colspan="4" align="left" style="padding-left:0px">  <input name="Submit2" type="button" value="Submit" class="cls_btn" onclick="validateForm()"/></td>
                </tr>
                 
                    
                    <tr>
                      <td colspan="7" height="10"></td>
                    </tr>
			  </table>
              
			  </form>
              
              </td></tr>
			<tr><td colspan="4" height="30"></td></tr>
      </table></td>
  </tr></table></td></tr>
   <tr>
    <td width="100%" height="80" align="center" valign="bottom"></td>
  </tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function validateForm(){
var dbType=document.BalanceForm.dbType.value;
var parentUser=document.BalanceForm.parentUser.value;
var parentID=document.BalanceForm.parentID.value;
var child=document.BalanceForm.child.value;
if(dbType=="select"){
alert("Please select DB Type");
return false;
}
if(parentUser=="select"){
alert("Please select Parent User");
return false;
}
if(parentID==""){
alert("Please Enter Parent ID");
return false;
}
if(child=="select"){
alert("Please select child");
return false;
}
document.BalanceForm.action="dbTask.action?param=balanceSheet";
document.BalanceForm.submit();
}
</script>