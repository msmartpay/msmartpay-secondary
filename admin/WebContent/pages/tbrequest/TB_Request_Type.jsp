<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/stickytooltip.js"></script>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
function request()
{
var a= document.getElementById("req").value;
document.getElementById("res").value =a;
}
//-->
</script>
</head>
<%
String listType="";
String message=(String)request.getAttribute("message");
if(message==null)message="";
String loginType=(String)session.getAttribute("loginType");


String usersId=(String)session.getAttribute("userId");
int portalId=0;
if(!usersId.equalsIgnoreCase("1"))
{
	portalId=Integer.parseInt((String)session.getAttribute("adminUserPortalId"));
}

%>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <form name="updateForm" method="post">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td valign="middle" height="40" class="heading_mgs">Trade Balance Request</td>                      
                   
                    </tr>                   
                    <tr>
                      <td colspan="4">
                      <table width="86%" cellpadding="0" cellspacing="0" align="center" class="mydata_tabl" id="border">
                      <tr><td colspan="100%" height="20"></td></tr>
                          <tr>                   
                              <td width="20%"></td>                              
                            <td width="20%"><strong>View Requesting User</strong></td> 
                                  <td width="10%" align="left">:</td>                     
                              <td><select id="req">             
                                <option value="select" selected="selected">Select</option> 
                                <%
                                if(loginType.equalsIgnoreCase("superadmin"))
                                {
                                	
                                %>	
                                 				<option value="portalUser">Portal User</option> 
                                				<option value="MDS">Master Distributor</option>  
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                               <% }if(loginType.equalsIgnoreCase("activityAdmin"))
                                {%>
                                				<option value="portalUser">Portal User</option> 
                                				<option value="MDS">Master Distributor</option>  
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                                
								
								<% }if(loginType.equalsIgnoreCase("portalAdmin")){%> 
								<option value="MDS">Master Distributor</option> 
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                                <% }%>
                                 
								
								
								
							    <% if(loginType.equalsIgnoreCase("portalUser")){
                                %>                              
                                 
								<option value="MDS">Master Distributor</option> 
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                                <%} %>
                                                            

                              
                                        
                                   
                                  
                              </select></td>                                                       
                             
                          </tr>
                          
                          <tr>
                          <td></td>
                            <td></td>
                            <td></td>
                            <td align="left" height="40">
                           <input type="button" value="Submit" class="cls_btn" onclick="requestTypeList()"/></td>
                          </tr>
                        </table>
                        </td>
                    </tr>
                    
                    <tr>
                      <td colspan="4"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></form></td>
  </tr>
  <tr>
    <td width="100%" valign="top" height="74" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>

<script type="text/javascript">
function submitForm(portalId,tranAmount,bankCharg,acceptedAmount,id,userId,transactionId,Type)
{	
	var act=document.getElementById("status"+id).value;
	var charge=document.getElementById("c"+id).value;
	var limiBankCharge=tranAmount/2;	
	if(charge=="")	{
		alert("Enter Charges ");
		document.getElementById("c"+id).focus;
		return false;
	}
	if(charge<0)	{
		alert("Charges can not be less than 0 ");
		document.getElementById("c"+id).focus;
		return false;
	}
	if(charge>limiBankCharge)	{
		alert("Bank Charges can not be more than half of amount ");
		document.getElementById("c"+id).focus;
		return false;
	}
	if(act=="Pending")	{
		alert("Select action ");
		document.getElementById("status"+id).focus;
		return false;
	}
	
	var remark=document.getElementById("r"+id).value;
	var remarkAdmin=document.getElementById("ra"+id).value;
	var charge=document.getElementById("c"+id).value;
	var status=document.getElementById("status"+id).value;	
	document.getElementById("check"+id).style.visibility="visible";
	document.getElementById("submitbutton"+id).style.visibility="hidden";	
	document.updateForm.action="updateTBRequest.action?transactionId="+transactionId+"&portalId="+portalId+"&tranAmount="+tranAmount+"&bankCharg="+charge+"&acceptedAmount="+acceptedAmount+"&id="+id+"&remarks="+remark+"&userId="+userId+"&userType="+Type+"&remarkAdmin="+remarkAdmin+"&status="+status;
	document.updateForm.submit();
}
function requestTypeList()
{
	var type=document.getElementById("req").value;
	if(chk())
	{
	document.updateForm.action="TBPending.action?type="+type;
	document.updateForm.submit();
	}
	function chk()
	{
		var type=document.getElementById("req").value;
		if(type=="select")
		{
			alert("Please select View Requesting User");
			document.getElementById("req").focus();
			return false;
		}
		return true;
	}
}
</script>
