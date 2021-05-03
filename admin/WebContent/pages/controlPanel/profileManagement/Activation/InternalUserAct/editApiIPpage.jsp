<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page import="java.util.HashMap"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<style>

.poptab{ height:150px; width:400px; margin:auto;}
.poptab tr td{ color:#930; font-size:13px; font-family:"Trebuchet MS"; border:0px solid #009;}
.poptab tr td strong{ color:#930;}
input{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select option{color:#930; font-size:13px; font-family:"Trebuchet MS";}
</style>

</head>

<body>
<%
HashMap map=(HashMap)request.getAttribute("data")  ;
String message=(String)request.getAttribute("message");
if(message==null)message="";
%>
<form name="updateIp" action="apiClientActivation.action?param=updateApiIP" method="post">
<table class="poptab">
<tr><td  colspan="10" align="center" style="font-size:18px; font-weight:bold; color:#FF0000; font-family:'Trebuchet MS';"><%=message%></td></tr>
<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
<tr>

<td><strong>First Authorized IP</strong></td> <td><input style="width:180px;" type="text" name="authorizedIp1" value="<%=map.get("IP1")%>"/></td>
</tr>
<tr>
<td><strong>Second Authorized IP</strong></td> <td><input style="width:180px;" type="text" name="authorizedIp2" value="<%=map.get("IP2")%>" /></td>
</tr>
<input type="hidden" name="agentId" value="<%=map.get("corporateAgentId")%>" />
<tr>
<td><strong>Status</strong></td> <td>
<select style="width:185px;" name="status">
<option value="<%=map.get("status")%>" selected="selected"><%=map.get("status")%></option>
<option value="Activate">Activate</option>
<option value="Deactive">Deactivate</option></select></td>
</tr>

<tr>
<td></td><td><input type="button" value="Update" style="width:100px;" onclick="Update()"/></td>
</tr>
</table>
</form>
</body>
</html>
<script language="javascript">

function Update(){
var ip1=document.updateIp.authorizedIp1.value;
if(ip1==null || ip1==""){
alert('Incomplete Form');
return false;
}
var ip2=document.updateIp.authorizedIp2.value;
if(ip2==null || ip2==""){
alert('Incomplete Form');
return false;
}
else{
document.updateIp.submit();
}
}
</script>