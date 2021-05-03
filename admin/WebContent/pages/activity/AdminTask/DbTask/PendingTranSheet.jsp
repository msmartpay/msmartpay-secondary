<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

<%
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
   <td  align="center" width="100%" valign="top"> <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" style="margin-left:100px" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left" style="font-size:13px; font-family:'Trebuchet MS'; color:#930; font-weight:bold;" >Pending</td>                
              </tr>
             
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="pendingForm">
            
		  <table cellpadding="0" cellspacing="0" width="83%" align="left" border="0"  class="mydata_tabl" id="border">
                 
				    <tr>
				      <td width="20%" height="30"></td>
				      <td width="11%" align="left">DB Type</td>
				      <td width="4%" align="left">:</td>
				      <td colspan="4" align="left"><select  name="dbType" onchange="dumy">
				        <option value="select" selected="selected">Select</option>
				        <option value="TEP">TEP</option>
				        <option value="API">API</option>
				        <option value="client">Client</option>
				        </select></td>
				      </tr>
                      
                       <tr>
				      <td width="20%" height="30"></td>
				      <td width="11%" align="left">Table Type</td>
				      <td width="4%" align="left">:</td>
				      <td colspan="4" align="left"><select  name="tableType" onchange="dumy">
				        <option value="select" selected="selected">Select</option>
                        <option value="LRT">LRT</option>
				        <option value="ATT">ATT</option>
				        </select></td>
				      </tr>
                      
                        <tr>
				      <td width="20%" height="30"></td>
				      <td width="11%" align="left">From Date</td>
				      <td width="4%" align="left">:</td>
				      <td width="17%" align="left"><input type="text"  class="tcal" name="fromDate" readonly="" value="<%=currDate%>"/>
				       </td>
				      <td width="6%" align="left"><strong>To Date</strong></td>
				      <td width="2%" align="left">:</td>
				      <td width="39%" align="left"><input type="text" class="tcal" name="toDate" readonly="" value="<%=currDate%>"/>
                        </td>
	                  </tr>
                    <tr>
                      <td colspan="7" height="20"></td>
                    </tr>
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td colspan="4" align="left" style="padding-left:0px">  <input name="Submit2" type="button" value="Submit" class="cls_btn" id="sms" onclick="validateForm()"/></td>
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
    <td width="100%" height="116" align="center" valign="bottom"></td>
  </tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function validateForm(){
var dbType=document.pendingForm.dbType.value;
var tableType=document.pendingForm.tableType.value;
if(dbType=="select"){

alert("Please select");
return false;
}
if(tableType=="select"){
alert("Please select");
return false;
}
document.pendingForm.action="dbTask.action?param=PendingTran";
document.pendingForm.submit();
}

</script>