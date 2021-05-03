<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
String message=(String) request.getAttribute("message");
if(message==null){
message="";}

ArrayList listData=(ArrayList)request.getAttribute("data");
int sizeOfData=0;
if(listData==null){
sizeOfData=-1;
}
else{
sizeOfData=listData.size();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Transaction Status </title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<SCRIPT type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>
</head>

<body>
<center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
 <%@ include file="../../header.jsp" %></td></tr>
 	
<tr>
    <td width="100%" align="center" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td width="45%" valign="bottom" height="30" align="left" style="padding-left:20px;font-family:'Trebuchet MS';font-weight:bold;" class="big"><strong><font size="3">Transaction Status</font></strong></td></tr>
	<tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	

	<tr><td width="45%" valign="bottom" height="30" align="center" style="padding-left:20px;font-family:'Trebuchet MS';" class="big"><font size="2" color="red"><%=message%></font></td></tr>
	</table>
	
	</td>
    </tr>
 
<tr><td height="20"></td></tr>
           	<tr><td width="100%" valign="top" align="center">
	<form  name="SearchTranForm" action="SearchTran.do?param=SearchTranData" method="post">
	<table cellspacing="0" cellpadding="0" width="100%" height="100px" align="center" class="border">
	<tr><td height="20" width="31%"></td>
	</tr>
	<tr><td width="31%" align="right" style="padding-right:10Px;font-family:'Trebuchet MS';font-size: 14px"><strong>Connection Number :</strong></td>
	  <td height="30" width="30%" align="center"><input name="connectionNo" type="text"  class="small" id="start" style="width:200px;height:30px" maxlength="15"/>	    &nbsp;&nbsp;&nbsp;</td>
	<td width="13%" align="center" style="padding-left:0Px" ><input name="submit" type="submit" value="Search"  style="width:100px;height:25px" class="txt" onclick=" validateForm()"/></td>
		<td width="26%"></td>
	</tr>
		
		<tr><td height="20"></td></tr>
	</table>
	</form>
	</td></tr>
	<table>
  		<tr><td height="0"></td></tr>
	</table>

  <tr><td width="100%" align="center">
    <% if (sizeOfData>0){%>
	<div id="view" ><table cellspacing="0" cellpadding="0" width="100%" align="center">
  <tr><td width="100%" align="center" valign="top"><table cellspacing="2" cellpadding="1" width="100%" align="center" class="txt" bgcolor="#c6d9f1">
    <tbody>
      
      <tr class="st" align="center" bgcolor="#dbe5f1">
        <!--<td width="14%" height="25"><strong>DS ID</strong></td>-->
        <td width="14%" height="25"><strong>AG ID</strong></td>
        <td width="14%"><strong>Date</strong></td>
        <td width="14%"><strong>Time</strong></td>
        <td width="14%" align="left" style="padding-left:5px;"><strong>Operator</strong></td>  
        <td width="14%"><strong>Connection Number</strong></td>
        <td width="14%"><strong>Txn Amount</strong></td>
        <!--<td width="12%"><strong>Operator TID
          </strong></td>-->
        <td width="16%" align="left"style="padding-left:5px;"><strong>Status</strong></td>
        </tr>
      <%  for (int i=0;i<sizeOfData;i++ )
			{
			HashMap map=(HashMap)listData.get(i);
			/*String vendorTranID=(String) map.get("vendorTranID");
			if(vendorTranID==null || vendorTranID==" " || vendorTranID.length()<=0 ){
			vendorTranID="NA";
			}*/
			
			String Amount=(String)map.get("amount");
			double DsBalance = Double.parseDouble(Amount);
			BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
			BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
			%>
      <tr bgcolor="#FFFFFF" align="center">
        <!--<td></td>-->
        <td><%=map.get("agentID")%></td>
        <td><%=map.get("dateOfRecharge")%></td>
        <td><%=map.get("timeOfRecharge")%></td>
        <td align="left"style="padding-left:5px;"><%=map.get("connectionOperator")%></td>
        <td><%=map.get("connectionNo")%></td>
        <td><%=Balance%></td>
        <td><%=map.get("status")%></td>
        <!--<td align="left"style="padding-left:5px;"></td>-->
        </tr>
       <%}%>
      </tbody>
  </table></td></tr>
    
	</table></div>
	<%}%>
	</td></tr>	
<tr><td height="30"></td></tr> 
</table>
</td></tr>
  <tr>
    <td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td>
  </tr>
</table>
</center>
</body>
</html>
<script language="javascript">
function validateForm(){
var connectionNo=document.SearchTranForm.connectionNo.value.replace(/^\s+|\s+$/, '');
if(connectionNo==""){
alert('Please enter Connection number.');
return false;
}
if(connectionNo.length>15 || connectionNo.length<8){
alert('Please enter vlaid Connection number');
return false;
}
if(isNaN(connectionNo){
alert('Please enter valid Connection number');
return false;
}
document.SearchTranForm.submit();
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
