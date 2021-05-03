<%@page import="com.controlPanel.setmarkup.SetMarkupForm"%>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);
String checkloginType=(String)session.getAttribute("loginType");
String message="";
message=(String)request.getAttribute("message");
if(message==null){
message="";
}
String flag=(String) request.getAttribute("flag");

if(flag==null){
flag="NA";
}
String panelOption="";

String loginType=(String)session.getAttribute("loginType");

//ArrayList list=(ArrayList)request.getAttribute("ClientList");
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
  $(document).ready(function(){
	 
	 $( "#datepicker, #datepickers" ).datepicker({
        changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2013',
            numberOfMonths: 1,
		
        onSelect: function( selectedDate ) {
			
			var a = document.getElementById('select_option').value;
			
            if(a  == 'select'){
             
			 if(this.id == 'datepicker'){
              var dateMin = $('#datepicker').datepicker("getDate");
			  
              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Max Date = Selected + 31d
              $('#datepickers').datepicker("option","minDate",rMin);
              $('#datepickers').datepicker("option","maxDate",rMax);                    
            } 
		           
            }
			
			 if(a == "select"){
             
			 if(this.id == 'datepicker'){
              var dateMin = $('#datepicker').datepicker("getDate");
			  
              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() + 1); // Min Date = Selected + 1d
              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() + 31); // Max Date = Selected + 31d
              $('#datepickers').datepicker("option","minDate",rMin);
              $('#datepickers').datepicker("option","maxDate",rMax);                    
            }
		           
            }
			

        }
		
    });
	

	 
  });
  


function popUpMarkup()
{
	var panelOption=document.markuppage.panelOption.value;
	
	if(panelOption=="select")
	{
		document.getElementById("clientID").style.display="none";
		document.markuppage.panelOption.focus();
		
	}
	if(panelOption=="client")
	{
		document.getElementById("clientID").style.display="";
	}
}


function submitForm()
{
	var panelOption=document.markuppage.panelOption.value;
	if(panelOption=="select")
	{
		alert("Please Select Panel.");
		document.getElementById("clientID").style.display="none";
		document.markuppage.panelOption.focus();
		return false;
	}
	else if((panelOption!="select")&&(panelOption!="client"))
	{
		var selectOption,selectClient;
		
		var clientID=document.markuppage.clientID.value;
		if(clientID=="")
		{
			alert("Please Enter MD ID .");
			document.markuppage.clientID.focus();
			return false;
		}
	}
	else
	{
		document.markuppage.action="SetMarkup.action?param=getMarkupDetails";
		document.markuppage.submit();
		return true;
	}
	
	
}
function submitClientInfo()
{
	var markup_limit=parseInt(document.updateMarkup.marketLimit.value);
	var cl_m=parseInt(document.updateMarkup.clientMarkup.value);
	var md_m=parseInt(document.updateMarkup.mdsMarkup.value);
	var ds_m=parseInt(document.updateMarkup.dsMarkup.value);
	var ag_m=parseInt(document.updateMarkup.agentMarkup.value);
	
	var tol=cl_m+md_m+ds_m+ag_m;
	if(markup_limit<tol)
	{
		alert('Please check input values.')
		return false;
	}
	
	document.updateMarkup.action="SetMarkup.action?param=saveMarkupDetails";
	document.updateMarkup.submit();
}

</script>
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
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Control Panel > Set Markup</td>
                    </tr>
                    <tr>
                      <td align="center" class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr>
                      <td class="dyn_mgs"></td>
                    </tr>
					
                    <tr>
                      <td colspan="4">
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td align="center" >
					  <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<form method="post" name="markuppage">
		              
		              			        			
			                    <table width="493" border="0" align="center"  cellpadding="0" cellspacing="0" class="mydata_tabl"> 
								   
								   <tr >
				                   <td height="42" ><strong>Select Service </strong> </td>
				                      <td width="5%"><strong>:</strong></td>
				                      
				                      <td width="41%">
				                        <select name="selectService" style="width:184px;">
											<option selected="selected" value="flight">Flight</option>
											<option value="bus">Bus</option>
											<option value="hotel">Hotel</option>
										</select>									</td>
				                      
				                      
				                      <td width="24%" class="setcls"> </td>
				                   </tr>
								   
								   <tr >
				                   <td height="42" ><strong>Select Panel </strong> </td>
				                      <td width="5%"><strong>:</strong></td>
				                      
				                      <td width="41%">
				                        <select name="panelOption" style="width:184px;" onchange="popUpMarkup()">
											<option selected="selected" value="select">Select</option>
											<option value="client">Client</option>
										 </select>									</td>
				                      
				                      
				                      <td width="24%" class="setcls"> </td>
				                   </tr>
								   
								   <tr id="clientID" style="display:none;">
				                   	<td height="42" ><strong>Enter MD ID  </strong> </td>
				                      <td width="5%"><strong>:</strong></td>
				                      
				                      <td width="41%">
				                        <input value="" id="id" name="id" style="width:180px" type="text" required="true"/>
				                      <td width="24%" class="setcls"> (e.g. 10004)</td>
				                   </tr>
								   
				                   <tr>
				                   		<td width="30%" height="44"> </td>
				                   		<td></td>
			                   		 <td width="41%"> <input type="button" class="btn_sign" value="Submit" onclick="submitForm()" /> </td>
				                   		<td class="setcls"> </td>
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
					  
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                      <tr>
                      <td align="center">
                      
                      <%if(flag.equalsIgnoreCase("Y"))
                     	{%>
                      <form name="updateMarkup" method="post">
					 
					 <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls">
                                        <tbody>
                                          <!--live recharge tr start-->
										  
                                        	<%
                                        	
                                        	panelOption=(String)request.getAttribute("panelOption");
                                        	if(panelOption==null)
                                        	{
                                        		panelOption="NA";
                                        	}
                                        	SetMarkupForm setMarkupForm=(SetMarkupForm)request.getAttribute("MarkupDetails");
                                        	
                                        	%>
                                          
                                          <!--bus booking tr start-->
										  <%if(panelOption.equalsIgnoreCase("client"))
										  {%>
                                          <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                            <td width="6%" height="22" align="center"><strong>S.N.</strong></td>
                                            <td width="11%" align="center"><strong>Markup Limit </strong></td>
                                            <td width="9%" align="center"><strong>Service</strong></td>
                                            <td width="9%" align="center"><strong>Initial</strong></td>
                                            <td width="16%" align="center"><strong>MD ID</strong></td>
                                            <td width="13%" align="center"><strong>Client Markup</strong></td>
                                    		<td width="12%" align="center"><strong>MDS Markup</strong></td>
                                            <td width="10%" align="center"><strong>DS Markup</strong></td>
                                            <td width="14%" align="center"><strong>Agent Markup</strong> </td>
										  </tr>
                                          <!--bus booking tr stop-->
										   <%}%>
                                          
                                            <!--Domestic,international flight or hotel ops transaction tr start-->
											
                                           
                                           
                                            <!--bookinghotal tr start-->
                                              	<%if(panelOption.equalsIgnoreCase("mds"))
                                              	{%>
                                          <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                            <td align="center"><strong>S. N. </strong></td>
                                            <td align="center"><strong>Markup Limit </strong></td>
                                            <td align="center"><strong>Service</strong></td>
                                            <td align="center"><strong>Initial</strong></td>
                                            <td align="center"><strong>MDS ID</strong></td>
                                            <td align="center"><strong>Client Markup</strong></td>
                                    		<td align="center"><strong>MDS Markup</strong></td>
                                            <td align="center"><strong>DS Markup</strong></td>
                                            <td align="center"><strong>Agent Markup</strong> </td>
										  </tr>
                                          <!--bookinghotal tr stop-->
										   <%}%>
                                           
                                             <!--Ops Transcation tr start-->
                                           <%if(panelOption.equalsIgnoreCase("ds"))
                                           {%>
                                          <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                            <td align="center"><strong>S. N. </strong></td>
                                            <td align="center"><strong>Markup Limit </strong></td>
                                            <td align="center"><strong>Service</strong></td>
                                            <td align="center"><strong>Initial</strong></td>
                                            <td align="center"><strong>DS ID</strong></td>
                                            <td align="center"><strong>Client Markup</strong></td>
                                    		<td align="center"><strong>MDS Markup</strong></td>
                                            <td align="center"><strong>DS Markup</strong></td>
                                            <td align="center"><strong>Agent Markup</strong> </td>
										  </tr>
                                          <!--Ops Transcation tr stop-->
										   <%}%>
                                           <%if(panelOption.equalsIgnoreCase("agent"))
                                           {%>
                                          <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                            <td align="center"><strong>S. N. </strong></td>
                                            <td align="center"><strong>Markup Limit </strong></td>
                                            <td align="center"><strong>Service</strong></td>
                                            <td align="center"><strong>Initial</strong></td>
                                            <td align="center"><strong>Agent ID</strong></td>
                                            <td align="center"><strong>Client Markup</strong></td>
                                    		<td align="center"><strong>MDS Markup</strong></td>
                                            <td align="center"><strong>DS Markup</strong></td>
                                            <td align="center"><strong>Agent Markup</strong> </td>
										  </tr>
                                          <!--Ops Transcation tr stop-->
										   <%}%>
                                          
                                           
										  
                                          
                                          <%if(panelOption.equalsIgnoreCase("client")) 
                                          {%>
                                          
                                          	<tr  align="center" style="background:#fff;height:25px;">
                                            <td  align="center">1</td>
                                            <td  align="center"><input size="10" name="marketLimit" type="text" value="<%=setMarkupForm.getMarketLimit()%>" /></td>
                                            <td  align="center"><input size="10" name="service" type="text" value="<%=setMarkupForm.getService()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="clientInitial" type="text" value="<%=setMarkupForm.getClientInitial()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="mdId" type="text" value="<%=setMarkupForm.getMdId()%>" readonly="readonly" />
                                            					<input size="10" name="clientId" type="hidden" value="<%=setMarkupForm.getClientId()%>" readonly="readonly" /></td>
                                            					<%if(loginType.equalsIgnoreCase("superadmin")){ %>
				                        						<input value="<%=setMarkupForm.getUserId() %>"  name="userId"  type="hidden" />
				                        						<%} %>
                                            <td  align="center"><input size="10" name="clientMarkup" type="text" value="<%=setMarkupForm.getClientMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="mdsMarkup" type="text" value="<%=setMarkupForm.getMdsMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="dsMarkup" type="text" value="<%=setMarkupForm.getDsMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="agentMarkup" type="text" value="<%=setMarkupForm.getAgentMarkup()%>" /></td>
                                          	</tr>
											
                                          
                                          <%} %>
                                          
                                          <%if(panelOption.equalsIgnoreCase("mds"))
                                          {%>
                                          
                                          	<tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center">1</td>
                                            <td  align="center"><input size="10" name="marketLimit" type="text" value="<%=setMarkupForm.getMarketLimit()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="service" type="text" value="<%=setMarkupForm.getService()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="clientInitial" type="text" value="<%=setMarkupForm.getClientInitial()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="mdId" type="text" value="<%=setMarkupForm.getMdId()%>" readonly="readonly" /><input size="10" name="clientId" type="hidden" value="<%=setMarkupForm.getClientId()%>" /></td>
                                            <td  align="center"><input size="10" name="clientMarkup" type="text" value="<%=setMarkupForm.getClientMarkup()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="mdsMarkup" type="text" value="<%=setMarkupForm.getMdsMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="dsMarkup" type="text" value="<%=setMarkupForm.getDsMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="agentMarkup" type="text" value="<%=setMarkupForm.getAgentMarkup()%>" /></td>
                                          	</tr>
                                          	
                                          <%} %>
                                          
                                          <%if(panelOption.equalsIgnoreCase("ds"))
                                          { %>
                                          
                                          	<tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center">1</td>
                                            <td  align="center"><input size="10" name="marketLimit" type="text" value="<%=setMarkupForm.getMarketLimit()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="service" type="text" value="<%=setMarkupForm.getService()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="clientInitial" type="text" value="<%=setMarkupForm.getClientInitial()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="dsId" type="text" value="<%=setMarkupForm.getDsId()%>" readonly="readonly" /><input size="10" name="clientId" type="hidden" value="<%=setMarkupForm.getClientId()%>" /><input size="10" name="mdId" type="hidden" value="<%=setMarkupForm.getMdId()%>" /></td>
                                            <td  align="center"><input size="10" name="clientMarkup" type="text" value="<%=setMarkupForm.getClientMarkup()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="mdsMarkup" type="text" value="<%=setMarkupForm.getMdsMarkup()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="dsMarkup" type="text" value="<%=setMarkupForm.getDsMarkup()%>" /></td>
                                            <td  align="center"><input size="10" name="agentMarkup" type="text" value="<%=setMarkupForm.getAgentMarkup()%>" /></td>
                                          	</tr>
                                          	
                                          <%} %>
                                          
                                          <%if(panelOption.equalsIgnoreCase("agent")) 
                                          {%>
                                          
                                          	<tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center">1</td>
                                            <td  align="center"><input size="10" name="marketLimit" type="text" value="<%=setMarkupForm.getMarketLimit()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="service" type="text" value="<%=setMarkupForm.getService()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="clientInitial" type="text" value="<%=setMarkupForm.getClientInitial()%>" readonly="readonly" /></td>
                                            <td  align="center"><input size="10" name="agentId" type="text" value="<%=setMarkupForm.getAgentId()%>" readonly="readonly" /><input size="10" name="clientId" type="hidden" value="<%=setMarkupForm.getClientId()%>" /><input size="10" name="mdId" type="hidden" value="<%=setMarkupForm.getMdId()%>" /><input size="10" name="dsId" type="hidden" value="<%=setMarkupForm.getDsId()%>" /></td>
                                            <td  align="center"><input size="10" name="clientMarkup" type="text" value="<%=setMarkupForm.getClientMarkup()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="mdsMarkup" type="text" value="<%=setMarkupForm.getMdsMarkup()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="dsMarkup" type="text" value="<%=setMarkupForm.getDsMarkup()%>" readonly="readonly"  /></td>
                                            <td  align="center"><input size="10" name="agentMarkup" type="text" value="<%=setMarkupForm.getAgentMarkup()%>" /></td>
                                          	</tr>
                                          	
                                          
                                          <%}%>
										  
                                        </tbody>
                       </table>
                       
                       <table cellspacing="0" cellpadding="0" width="86%" align="center">
                       <tr><td align="right">
                       <input type="button" value="Update" onclick="submitClientInfo()" />&nbsp;&nbsp;&nbsp;
                       </td></tr>
                       </table>
					   </form>
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
    <td width="100%" valign="top" align="center" height="118"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
