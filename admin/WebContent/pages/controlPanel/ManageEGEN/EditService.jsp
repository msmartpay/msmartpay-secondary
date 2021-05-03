
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import = "java.util.HashMap " %>
<%@ page import = "java.util.ArrayList" %> 
<%
String mark="";
String mark1="";
int flag=0;
String ip_address=(String)request.getRemoteAddr() ;
session.setAttribute("ip_address",ip_address);
String stat=(String)request.getAttribute("updateStatus");
String message=(String) request.getAttribute("message");
if(message==null){
message="";
}
ArrayList editservice= (ArrayList)request.getAttribute("Data");
int size=0;
if(editservice==null)
{
size=-1;
}
else
{
size=editservice.size();
}
String corpAgntId="";
String service="";
String umark="";
String cmark="";
String service1="";
int sum=0;
String remark="";
int i=0;
%>

<style>
select{color:#930;}
select option{color:#930;}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
<tr>
<td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
</tr>
<tr>
<td  align="center" width="100%" valign="top">
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
<tr>
<td valign="top" align="center" >
<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="rounded-corners box_heights">
<tr>
<td valign="top" align="center" >
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">

<tr>
<td valign="middle" height="40" align="left" class="heading_mgs" >Edit Service</td>
</tr>
<tr>
<td valign="bottom" align="center" class="dyn_mgs"><%=message%></td>
</tr>
<tr>
<td valign="middle" height="0" align="center"  class="logintxt" >

<form name="clientDetails" method="post" action="EgenCommsion.action?param=getAgentServDetails">
<table class="mydata_tabl" id="border" border="0" cellpadding="0" cellspacing="0" width="86%">
<tbody>
<tr>
<td colspan="4" class="style111" align="center" height="30"></td>
</tr>
<tr>
<td width="19%"></td>
<td align="left" width="24%"><strong>Enter Corporate Agent ID</strong></td>
<td align="left" width="8%">:</td>
<td align="left" width="49%"><input  type="text" name="corpAgentId"  onselectstart="return false" autocomplete="off/" tooltiptext="Type User ID in this box" /></td>
</tr>

<tr>
<td colspan="4" height="7"><span style="padding-left:150px"> </span></td>
</tr>
<tr>
<td colspan="3"></td>
<td align="left" width="49%"><span style="padding-left:0px">
<input name="Submit2" value="Submit" class="cls_btn" id="sms" onclick="submitclientform()" type="button" />
</span></td>
</tr>
</tbody>
</form>
<tr>
<td colspan="4" height="15"></td>
</tr>
<tr>
<td></tbody></td>
</tr>
</table></td>
</tr>
<tr>
<td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
</tr>
<tr>
<td colspan="4" height="10"></td>
</tr>
<tr>
<td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">
<% if(editservice !=null) {


%>
<tr>
<td width="100%" colspan="5" align="center" valign="top">

<table width="96%" border="0" cellspacing="0" cellpadding="0" >
<tbody>
<tr>
<td height="35" align="left" valign="middle" style="padding-left:0px;" class="heading_mgs">Details</td>
</tr>
<tr>
<td align="center"> 	</td></tr>
<tr>
<td align="center"></td></tr>
<tr>
<td valign="top" style="border-left:1px solid #a74312;">
<table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
<tbody style="background:#a74312;">
<tr class="hd" align="center" style="background:#a74312;">
<td width="6%" height="25" class="right_border"><strong>S.N.</strong></td>
<td width="19%" class="right_border"><strong>Corporate Agent ID</strong></td>
<td width="9%" align="center" style="padding-left:0px;" class="right_border"><strong>Service</strong></td>
<td width="12%" class="right_border"><strong>Status</strong></td>
<td width="17%" align="center" class="right_border" style="padding-left:px;"><strong>Remark</strong></td>
<td width="10%" align="center" style="padding-left:px;" class="right_border"><strong>Update</strong></td>
</tr>
 <%		
												
								for(i=0;i<size;i++)
								{	
								  HashMap hm=(HashMap)editservice.get(i);						 
								  			
							  %>
							  <form action="EgenCommsion.action?param=updateAgntServ" name="editservice<%=i%>" id="editservice<%=i%>" method="post" >
<tr bgcolor="#FFFFFF" align="center" class="value_reg">
<td height="25"><%=(i+1)%></td>

<td><%=hm.get("corporateAgntId")%></td> <%
	
							  service=(String)hm.get("service");
							  
							  if(service.equals("mobile"))
							  service1="MOBILE";
							  if(service.equals("dth"))
							  service1="DTH";
							  if(service.equals("billpay"))
							  service1="BILL PAY";
							  if(service.equals("datacard"))
							  service1="DATACARD";
							
							%>
<td align="center" valign="middle" style="padding-left:0px;"><%=service1%></td>
<%	
							mark=String.valueOf(hm.get("status"));

							if(mark.equalsIgnoreCase("y"))
							mark1="N";
							else
							mark1="Y";
								
								%>
<td><span class="style3" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
<select name="mark<%=i%>" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:11px; font-weight:bold;">
								  <option value="<%=mark%>"><%=mark%></option>
								   <option value="<%=mark1%>"><%=mark1%></option>
								  </select>
</span></td>
<td><span class="style3">
<%remark=(String)hm.get("remark");
if(remark==null){remark="";}
%>
<input type="text" name="remark<%=i%>" value="<%=remark%>" maxlength="100" style="width:120px;"/>
</span></td>
<input type="hidden" name="status" value="<%=i%>" />
<input type="hidden" name="corpAgentId" value="<%=hm.get("corporateAgntId")%>" />
<input type="hidden" name="service<%=i%>" value="<%=service%>" />
<td align="center" style="padding-left:px;"><span class="style3">
<input name="submit<%=i%>" type="submit" class="buttonbg" value="Update" />
</span></td>
</tr>
</form>
</tbody>
<%}%>  
</table></td>
</tr>
<tr> </tr>
</tbody>
</table>
</div></td>
</tr>
<%}%>
<tr>
<td height="8" colspan="5" align="center" valign="top"></td>
</tr>
<tr>
<td colspan="5" align="center" valign="top"></td>
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
</table>
</td>
</tr>
<tr>
<td width="100%" valign="top" align="center" height="145"></td>
</tr>
<tr>
<td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
</tr>
</table>
</body>
</html>
<script language="javascript">
function submitclientform()
{
document.clientDetails.submit();
}


</script>