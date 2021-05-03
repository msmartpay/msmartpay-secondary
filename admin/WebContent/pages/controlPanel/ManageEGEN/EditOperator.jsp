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
String stat=(String)request.getAttribute("status");
if(stat==null)
{
stat="";
}
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}

ArrayList editservice= (ArrayList)request.getAttribute("Data");
int sizeagent=0;
if(editservice==null)
{
	sizeagent=-1;
	message="Data not available.";
}
else
{
sizeagent=editservice.size();
}


 String service="";
 String service1="";
 String vendor1="";
 String vendor2="";
 String vendor3="";
 String vendor="";
//System.out.println("In JSP"+fundtransfer);
String status="";
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
<td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
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
<td valign="bottom" height="40" align="left" class="heading_mgs">Edit Service</td>
</tr>
<tr>
<td valign="bottom" height="10" align="center" class="dyn_mgs" ><%=message%></td>
</tr>



<tr>
<td valign="top" align="center">
<table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" class="border" style="margin-bottom:20px;">
<tr>
<td width="100%" colspan="5" align="center" valign="top">
<%if(sizeagent>0){ %>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td height="20" align="left" valign="middle" class="heading_mgs1">This Details</td>
</tr>

<td valign="middle" style="border-left:1px solid #a74312;">

<table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
<tbody style="background:#a74312;">
<tr align="center" style="background:#a74312;">
<td width="6%" align="center"><strong>S.N.</strong></td>
<td width="10%" align="center"><strong>Service</strong></td>
<td width="12%" align="center"><strong>Operator</strong></td>
<td width="12%" align="center"><strong>Active</strong></td>
<td width="17%" align="center"><strong>Vendor</strong></td>
<td width="12%" align="center"><strong>Action</strong></td>
</tr>

 <%		
								String colour="";	
								System.out.println("wew are here ");
								System.out.println(	editservice);			
								for(int i=0;i<sizeagent;i++)
								{	
								  HashMap hm=(HashMap)editservice.get(i);	
								 							 
								  if(i%2==0)
								{
									colour="#FFFFFF";
								}
								else
								{
									colour="#EFF3F5";
								}				
							  %>
	<form action="EgenCommsion.action?param=updateService" name="editservice<%=i%>" id="editservice<%=i%>" method="post" >						  
<tr bgcolor="#ffffff" align="center" class="value_reg">
<td height="25"><%=(i+1)%></td>
<%
							  service=String.valueOf(hm.get("Type"));
							  if(service.equals("mobile"))
							  service1="MOBILE";
							  if(service.equals("dth"))
							  service1="DTH";
							  if(service.equals("billpay"))
							  service1="BILL PAY";
							  if(service.equals("datacard"))
							  service1="DATACARD";
							
							%>
<td><%=service1%></td>
<td align="center" valign="middle"><%=hm.get("operator")%></td>
<%
							mark=String.valueOf(hm.get("status"));
							if(mark.equalsIgnoreCase("y")){
							mark1="N";
							}
							else{
							mark1="Y";
							
							}
								
								%>
<td>
								<select name="mark<%=i%>">
								  <option value="<%=mark%>"><%=mark%></option>
								   <option value="<%=mark1%>"><%=mark1%></option>
								  </select>
</td>
<td><%=String.valueOf(hm.get("vendor"))%></td>
<input name="operator<%=i%>" type="hidden" value="<%=hm.get("operator")%>" />
<input name="service<%=i%>" type="hidden" value="<%=hm.get("Type")%>" />
<input type="hidden" name="status" value="<%=i%>" />
<td align="center">
<input name="submit<%=i%>" type="submit" class="buttonbg" value="Update" />
</td>
</tr>
</form>
<%}%>  
</tbody>
</table>

</td>
</tr>

</tbody>
</table>
<%} %>
</td>
</tr>
</table>
</td>
</tr>

</table></td>
</tr>
</table></td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="100%" valign="top" align="center" height="240"></td>
</tr>
<tr>
<td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
</tr>
</table>
</body>
</html>

