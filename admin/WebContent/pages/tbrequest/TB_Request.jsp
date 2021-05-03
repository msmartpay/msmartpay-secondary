<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
<style type="text/css">
#res{background:none; border:none; padding:5px 0 0 0; color:#a74312; font-family:"Trebuchet MS"; font-weight:bold;}

</style>
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
String message="";
String loginType=(String)session.getAttribute("loginType");
System.out.print(loginType);
ArrayList list=(ArrayList)session.getAttribute("list");
String usersId=(String)session.getAttribute("userId");
listType=(String)request.getAttribute("ListType");
if(listType==null)
{
	listType="";
}
int portalId=0;
if(!usersId.equalsIgnoreCase("1"))
	portalId=Integer.parseInt((String)session.getAttribute("adminUserPortalId"));

message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}
session.removeAttribute("list");
String userId1=(String)session.getAttribute("userId");
LoginDao getBal=new LoginDao();
String balance1=getBal.getPortalAdminBalance(userId1);
%>
<body>
<div id="mystickytooltip" class="stickytooltip">
<%
                    if(list!=null)
                    {
					if(list.size()>0){
					
					for(int i=0;i<list.size();i++){
				  
				 HashMap userMap=(HashMap)list.get(i);
%>
<div id="sticky1<%=i%>" class="atip" style="width:500px" align="center">
<table width="100%" align="center" class="mydata_tabl">
<tr><td colspan="2"><h3 class="heading_mgs1">ID Account Info</h3></td></tr>
<tr><td>User ID</td><td><%=userMap.get("initial")%><%=userMap.get("userId") %> </td></tr>
<tr><td>Email ID</td><td><%=userMap.get("emailId")%> </td></tr>
<tr><td>portal ID</td><td><%=userMap.get("portalId") %> </td></tr>
<tr><td>Company Name</td><td><%=userMap.get("companyName") %></td></tr>
<tr><td width="50%">Payment Mode</td><td width="50%"><%=userMap.get("paymentMode") %></td></tr>
<%if(userMap.get("BankName")!=null) {%>
<tr><td>Bank Name</td><td><%=userMap.get("BankName")%></td></tr>
<%} %>
<%if(userMap.get("TxnId")!=null) {%>
<tr><td>Bank Txn No.</td><td><%=userMap.get("TxnId") %></td></tr>
<%} %>
<tr><td>Transaction Amount </td><td>Rs.<%=userMap.get("amount") %></td></tr>
<tr><td>Accepted Amount </td><td>Rs.<%=userMap.get("AcceptedAmount") %></td></tr>
<%if(userMap.get("BankTranId")!=null) {%>
<tr><td>TID</td><td><%=userMap.get("BankTranId") %></td></tr>
<%} %>
<tr><td>Current Status</td><td><%=userMap.get("status") %></td></tr> 
<%if(userMap.get("RecievingBranchName")!=null) {%>
<tr><td>Recieving Branch Name.</td><td><%=userMap.get("RecievingBranchName") %></td></tr>
<%} %>

<tr><td>Deposit Date</td><td><%=userMap.get("depositDate") %></td></tr>


<tr><td>Cheque/DD No.</td><td><%=userMap.get("ChequeDDNo") %></td></tr>

<tr><td>Remarks.</td><td><%=userMap.get("remarks") %></td></tr>

</table>
</div>
 <%}}}%>
<div class="stickystatus"></div>
</div>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <form name="updateForm" method="post"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
                    <tr>
                      <td  width="50%" valign="bottom" height="40" class="heading_mgs">Trade Balance Request</td>                      
                   
                    </tr> 
                    <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>                  
                    <tr>
                      <td colspan="4" >
                      <table cellpadding="0" cellspacing="0" width="500" align="center" border="0" class="mydata_tabl">
                          <tr>                   
                                                            
                            <td><strong>View Requesting User</strong></td>                            
                           <td><select id="req">             
                                <option value="select" selected="selected">Select</option> 
                                <%if(loginType.equalsIgnoreCase("superadmin")){%>
                                				<option value="portalUser">Portal User</option> 
								<option value="MDS">Master Distributor</option>	
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                               <%}else if(loginType.equalsIgnoreCase("activityAdmin") || loginType.equalsIgnoreCase("activityUser")){%>
                               					<option value="portalUser">Portal User</option> 
                                				<option value="MDS">Master Distributor</option>  
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option> 
								<%} if(loginType.equalsIgnoreCase("portalAdmin") || loginType.equalsIgnoreCase("portalUser")){%> 
								<option value="MDS">Master Distributor</option>
								<option value="DS">Distributor</option> 
								<option value="Agent">Retailer</option>
                                <%}%>
                                
                              </select></td>                                                        
                             
                          </tr>
                          <tr>
                            <td></td>
                            <td align="left" height="40">
                           <input type="button" value="Submit" class="cls_btn" onclick="requestTypeList()"/></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  id="border">
                          <tr>
                          <%if(listType==""){ %>
                            <td  width="50%" valign="bottom" height="40" class="heading_mgs" > Trade Balance -
                              <input type="text" value="Portal User" class="logintxt" id="res" disabled="disabled" />
                              </td>
                              <%} else{%>
                              <td  width="50%" valign="bottom" height="40" class="heading_mgs"> Trade Balance -
                              <input type="text" value="<%=listType %>" class="logintxt" id="res" disabled="disabled" />
                              </td>
                              <%} %>
                          </tr>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                          <tr>
						  

                            <%
                            if(list!=null)
                            {
                            if(list.size()>0){
                            
                            %>
                                 
                              <input type="hidden" name="type" value="<%=listType %>">	  
                                  
                            <td colspan="5" valign="top">
                            <table cellspacing="0" cellpadding="0" width="96%" align="center" class="tbls"   style="border-bottom:1px solid #930;">
                                <tbody>
                                  <tr align="center" class="hd tabulardata">
                                    <td width="2%" height="25" style="color:#FFF;">S.N.</td>
                                    <td width="13%" style="color:#FFF;">Request Date & Time</td>                                   
                                     <td width="7%" style="color:#FFF;">Txn ID</td>  
                                     <td width="5%" style="color:#FFF;">Reference No.</td>
                                     <td width="9%" style="color:#FFF;">Agency Name</td>
                                     <td  width="11%" style="color:#FFF;">Payment Mode</td>  
                                     <td width="7%" style="color:#FFF;">Mobile</td>                         
                                    <td width="10%" style="color:#FFF;">User ID</td>                                    
                                    <td width="9%" style="color:#FFF;">Txn Amount</td>                                   
                                    <td width="7%" style="color:#FFF;">Charges</td>
                                    <td  width="10%" style="color:#FFF;">Remark</td>
                                    <td  width="9%" style="color:#FFF;">Action</td>
                                    <td  width="7%" style="color:#FFF;">Update</td>
                                  </tr>
                                  <%}} %>
                                  <% 
                                  if(list!=null)
                                  {
                                  if(list.size()>0){
                  					
                  					for(int i=0;i<list.size();i++){
                  				  
                  				 HashMap map=(HashMap)list.get(i);
                  				
                                      String id=map.get("transactionId")+"";                                      
                                      String refNo="";
                                      if(map.get("referenceNo")!=null)
                                    	  refNo=map.get("referenceNo")+"";
                                      else if(map.get("BankTranId")!=null)
                                    	  refNo=map.get("BankTranId")+"";
                                      else
                                    	  refNo="NA";
                                      
                                      String time="";
                                      int indexdot=-1;
                                      if(map.get("time")!=null){
                                    	  time=map.get("time")+"";
                                      
                                      	indexdot=time.indexOf(".");
                                      	if(indexdot!=-1)
                                      		time=time.substring(0, indexdot);
                                      	else
                                      		time="NA";
                                      }else{
                                    	  time="NA";
                                      }
                                      
                                    %>
                                  <tr bgcolor="#FFFFFF" align="center">
                                    <td align="center" height="30" rowspan="2"><%=i+1 %></td>
                                    <%if(map.get("date")==null) {%>
                                    <td rowspan="2"> </td>
                                    <%}else{ %>
                                    <td rowspan="2"><%=map.get("date")%>  <%=time %></td>
                                    <%} %>
                                    <td rowspan="2"><a data-tooltip="sticky1<%=i%>" style="cursor:pointer"><%=id %></a></td>
                                    <td rowspan="2"><%= refNo%></td>
                                    <td rowspan="2"><%=map.get("companyName") %></td>
                                    <td rowspan="2"><%=map.get("paymentMode") %></td>
                                     <%if(map.get("mobileNo")==null|| map.get("mobileNo").equals("null")) {%>
                                    <td rowspan="2"> </td>
                                    <%}else{ %>
                                    <td rowspan="2"><%=map.get("mobileNo") %></td>
                                    <%} %>
                                    <td rowspan="2"><%=map.get("initial") %><%=map.get("userId") %></td>
                                    <td rowspan="2"><%=map.get("amount") %></td>                                    
                                    <td rowspan="2"><input id="c<%=i %>" type="text" class="style2" style="width:60px" value='<%= map.get("charges") %>' onkeypress="return digitonly(this,event)" onselectstart="return false" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off/></td>
                                    <td>
                                    <input id="ra<%=i %>" type="text" class="style2" style="width:90px"  value="<%=map.get("userId") %> <%=map.get("companyName") %>"/><br />
                                      
									</td>
                                    <td rowspan="2">
                                    	<select style="width:70px;" id="status<%=i %>">
                                        <option selected="selected"><%=map.get("status") %></option>
                                        <option value="Accept">Accept</option>
                                        <option value="Decline">Decline</option>
                                      </select>
                                     </td>
                                      
                                    
                                       <td align="center" rowspan="2">                       
				                          <input name="Input" type="button"  id="submitbutton<%=i%>" style="visibility:visible" onclick="submitForm(<%=map.get("portalId") %>,<%=map.get("amount") %>,<%=map.get("charges") %>,<%=map.get("AcceptedAmount") %>,<%=i %>,<%=map.get("userId") %>,'<%=id %>','<%=map.get("userType") %>','<%=balance1%>')" value="Submit"   class="txt" />
										  <div id="check<%=i%>" style="visibility:hidden"><img src="images/loading2.gif" height="20" width="30"/></div>
				                       
				                    </td>
                                  </tr>
                                  
                                  <tr bgcolor="#ffffff" align="center">
                                  <td><input id="r<%=i %>" type="text" class="style2" style="width:90px" value='<%=map.get("userId") %> <%=map.get("companyName") %>'/></td>
                                  </tr>
                              
        
                                      <%
                                         }
                                        }
                                  }
                                         %>
                              
                                </tbody>
                              </table>
                             </td>
                          </tr>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="74"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </form></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>

<script type="text/javascript">
function submitForm(portalId,tranAmount,bankCharg,acceptedAmount,id,userId,transactionId,Type,bal)
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
	var status=document.getElementById("status"+id).value;	
	var remark=document.getElementById("r"+id).value;
	var remarkAdmin=document.getElementById("ra"+id).value;
	var charge=document.getElementById("c"+id).value;
	
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
