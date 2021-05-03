<%@page import="com.controlPanel.setmarkup.SetMarkupForm"%>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 

<%@ page import="java.text.*" %>
<%
String message="";
message=(String)request.getAttribute("message");
if(message==null){
message="";
}
String flag=(String) request.getAttribute("flag");

if(flag==null){
flag="N";
}

ArrayList clientNameList=(ArrayList)request.getAttribute("clientList");
if(clientNameList==null)
{
	clientNameList=new ArrayList();
	message="Client details not available. Add client detail first.";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
function submitClientInfo()
{
	var clientName=document.getApi.clientName.value;
	var apiId=document.getApi.apiId.value;
	if(clientName=="select")
	{
		alert("Please Select Client Name.");
		document.getApi.clientName.focus();
		return false;
	}
	else if(apiId=="")
	{
		alert("Please Enter API ID");
		document.getApi.apiId.focus();
		return false;
	}
	else
	{
		document.getApi.action="apiTask.action?param=getAPIDetails";
		document.getApi.submit();
	}
}
  
function submitForm()
{
	var dbName=document.assignAPI.dbName.value;
	var serverIP=document.assignAPI.serverIP.value;
	var agentId=document.assignAPI.agentId.value;
	var agentHeaderId=document.assignAPI.agentHeaderId.value;
	var agentHeaderPassword=document.assignAPI.agentHeaderPassword.value;
	var subAgentId=document.assignAPI.subAgentId.value;
	var subAgentPassword=document.assignAPI.subAgentPassword.value;
	var subAgentMobile=document.assignAPI.subAgentMobile.value;
	if(dbName=="")
	{
		alert("DB Name Required.");
		document.assignAPI.dbName.focus();
		return false;
	}
	else if(serverIP=="")
	{
		alert("DB Server IP Required.");
		document.assignAPI.serverIP.focus();
		return false;
	}
	else if(agentId=="")
	{
		alert("API Id Required.");
		document.assignAPI.agentId.focus();
		return false;
	}
	else if(agentHeaderId=="")
	{
		alert("Agent Header Id Required.");
		document.assignAPI.agentHeaderId.focus();
		return false;
	}
	else if(agentHeaderPassword=="")
	{
		alert("Agent Header Password Required.");
		document.assignAPI.agentHeaderPassword.focus();
		return false;
	}
	else if(subAgentId=="")
	{
		alert("Sub Agent Id Required.");
		document.assignAPI.subAgentId.focus();
		return false;
	}
	else if(subAgentPassword=="")
	{
		alert("Sub Agent Password Required.");
		document.assignAPI.subAgentPassword.focus();
		return false;
	}
	else if(subAgentMobile=="")
	{
		alert("Sub Agent Mobile No Required.");
		document.assignAPI.subAgentPassword.focus();
		return false;
	}
	else
	{
		document.assignAPI.action="apiTask.action?param=assignAPI";
		document.assignAPI.submit();
	}
	
}


</script>
<%


%>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Activity > API Task > Assign API </td>
                    </tr>
                    <tr>
                      <td align="center" class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr valign="top">
                      <td colspan="4">
					  <table width="100%" height="204" align="center" cellpadding="0"  cellspacing="0">
                          <tr valign="top">
                            <td height="177" align="center" >
					  <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<form method="post" name="getApi">
		              
		              			        			
			                    <table width="464" border="0" align="center"  cellpadding="0" cellspacing="0" class="mydata_tabl"> 
								   
								   <tr >
				                   <td width="37%" height="42" ><strong>Select Client </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <select name="clientName" style="width:184px;" >
										<option value="select" selected="selected">--------- Select --------</option>	
										<%if(clientNameList.size()>0)
										{
										for(int i=0;i<clientNameList.size();i++){%>
										<option value="<%=clientNameList.get(i) %>"><%=clientNameList.get(i) %></option>
										<%}}%>
										</select>									</td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr >
				                   <td height="42" ><strong>Enter API ID  </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                      <input value=""  name="apiId" style="width:180px" type="text" required="true"/></td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr> 
								   
								   <tr >
				                   <td height="42" > </td>
				                      <td width="6%"></td>
				                      
				                      <td width="57%"><input type="button" value="Submit" onclick="submitClientInfo()" /></td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>  
		               		 </table>
		                
		                  </form>
							
							</td>
                          </tr>
						  
                        </table>
						</td>
						</tr><tr ><td height="25"></td></tr>
						<tr ><td >
						
						
						</td></tr>
						
						</table>
					</td>
                    </tr>
					<tr>
                      <td colspan="4">
					                      
					  </td>
                    </tr>
                    <tr>
                      <td colspan="4" height="30">
					  
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center" style="margin-bottom:30px;">
                      
                      
                      <%if(flag.equalsIgnoreCase("Y"))
                     	{
                     	
                    	  HashMap clientList=(HashMap)request.getAttribute("apiDetailMap");
                     	
                     	%>
						<tr>
                      <td align="center">
						<table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">	
                      <form name="assignAPI" method="post">
                        <table width="48%" height="378" align="center" cellpadding="0" cellspacing="0" class="mydata_tabl">
						<tr>
					   		<td width="37%" align="left"></td>
							<td width="6%" align="left"></td>
					   		<td width="57%" align="left">							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42" ><strong>DB Name</strong></td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("dbName") %>"  name="dbName" style="width:180px" type="text" required="true"/></td>
					   	</tr>
					   	
					   	<tr>
					   		<td width="37%" align="left" height="42" ><strong>DB Name</strong></td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("serverIP") %>"  name="serverIP" style="width:180px" type="text" required="true"/></td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42" ><strong>Corporate Agent ID</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("apiId") %>"  name="agentId" style="width:180px" type="text" readonly="readonly"  />                       		
							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42"><strong>Agent Header ID</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("AgentHeaderId") %>"  name="agentHeaderId" style="width:180px" type="text" readonly="readonly"/>                       		
							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42"><strong>Agent Header Password</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("AgentHeaderPassword") %>"  name="agentHeaderPassword" style="width:180px" type="text" readonly="readonly"/>                       		
							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42"><strong>Sub Agent ID</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("SubAgentId") %>"  name="subAgentId" style="width:180px" type="text" readonly="readonly"/>                       		
							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42"><strong>Sub Agent Password</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("SubAgentPassword") %>"  name="subAgentPassword" style="width:180px" type="text" readonly="readonly"/>                       		
							</td>
					   	</tr>
						
						<tr>
					   		<td width="37%" align="left" height="42"><strong>Sub Agent Mobile No</strong> </td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left"><input value="<%=clientList.get("SubAgentMobileNo") %>"  name="subAgentMobile" style="width:180px" type="text" readonly="readonly"/>                       		
							</td>
					   	</tr>
						
                       	<tr>
					   		<td width="37%" align="left" height="42"></td>
							<td width="6%" align="left"><strong>:</strong></td>
					   		<td width="57%" align="left">
                       		<input type="button" value="Update" onclick="submitForm()" />
                       		</td>
					   	</tr>
                       </table>
					   </form>
                      	
							</td>
							</tr>
						</table>
					  </td>
                      </tr>
					  <%}else{ %>
					  <tr>
                      <td align="center" height="130">
					  </td>
					  </tr>
					  
					  <%}%>
                      
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
    <td width="100%" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>