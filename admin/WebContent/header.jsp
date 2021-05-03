<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="js/bootstrap.min.js"></script>
<%@page import = "com.login.LoginDao" %>
<%@page import = "com.login.superadmin.SuperAdminLoginDao" %>
<%@page import = "java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String userId=(String)session.getAttribute("userId");
HashMap adminServiceDetailsMap=(HashMap)session.getAttribute("adminServiceDetails");
String loginUserType=(String)session.getAttribute("loginType");
int loginPortalId=0;
if(!userId.equalsIgnoreCase("1"))
{
	loginPortalId=Integer.parseInt((String)session.getAttribute("adminUserPortalId"));
}

String adminUserType=(String)session.getAttribute("adminUserType");

String activity=(String)adminServiceDetailsMap.get("activity");
String UserRegistration=(String)adminServiceDetailsMap.get("UserRegistration");
String InternalUserreg=(String)adminServiceDetailsMap.get("InternalUserreg");
String CorporateUserreg=(String)adminServiceDetailsMap.get("CorporateUserreg");
String RetailUserreg=(String)adminServiceDetailsMap.get("RetailUserreg");
String MDReg=(String)adminServiceDetailsMap.get("MDReg");




String AdminTasks=(String)adminServiceDetailsMap.get("AdminTasks");
String ActivityAssignment=(String)adminServiceDetailsMap.get("ActivityAssignment");
String CategoryAssignment=(String)adminServiceDetailsMap.get("CategoryAssignment");
String DBTask=(String)adminServiceDetailsMap.get("DBTask");
String SchedulerRun=(String)adminServiceDetailsMap.get("SchedulerRun");


String TradeBalanceActivity=(String)adminServiceDetailsMap.get("TradeBalanceActivity");
String TBSuperAdmin=(String)adminServiceDetailsMap.get("TBSuperAdmin");
String TBFinance=(String)adminServiceDetailsMap.get("TBFinance");
String TBClient=(String)adminServiceDetailsMap.get("TBClient");


String ControlPanel=(String)adminServiceDetailsMap.get("ControlPanel");
String ProfileManagement=(String)adminServiceDetailsMap.get("ProfileManagement");
String InternaluserProfileMgt=(String)adminServiceDetailsMap.get("InternaluserProfileMgt");
String CorporateUserProfileMgt=(String)adminServiceDetailsMap.get("CorporateUserProfileMgt");
String RetailUserProfileMgt=(String)adminServiceDetailsMap.get("RetailUserProfileMgt");
String SelfProfile=(String)adminServiceDetailsMap.get("SelfProfile");

String CutOffManagement=(String)adminServiceDetailsMap.get("CutOffManagement");
String CutOffSetup=(String)adminServiceDetailsMap.get("CutOffSetup");
String CutOffView=(String)adminServiceDetailsMap.get("CutOffView");


String AccountMgt=(String)adminServiceDetailsMap.get("AccountMgt");
String AccountManagement=(String)adminServiceDetailsMap.get("AccountManagement");
String commTDSmgt=(String)adminServiceDetailsMap.get("commTDSmgt");
String Refund=(String)adminServiceDetailsMap.get("Refund");

String EGENControl=(String)adminServiceDetailsMap.get("EGENControl");
String OperatorManagement=(String)adminServiceDetailsMap.get("OperatorManagement");
String ClientServiceControl=(String)adminServiceDetailsMap.get("ClientServiceControl");
String VendorManagement=(String)adminServiceDetailsMap.get("VendorManagement");
String ClientOperatorControl=(String)adminServiceDetailsMap.get("ClientOperatorControl");
String ClientCutOffView=(String)adminServiceDetailsMap.get("ClientCutOffView");
String EGENRefund=(String)adminServiceDetailsMap.get("EGENRefund");
String EgenTranStatus=(String)adminServiceDetailsMap.get("EgenTranStatus");

String OpsManagement=(String)adminServiceDetailsMap.get("OpsManagement");
String ServiceManagement=(String)adminServiceDetailsMap.get("ServiceManagement");
String TEPvendorManagement=(String)adminServiceDetailsMap.get("TEPvendorManagement");

String SKUMgt=(String)adminServiceDetailsMap.get("SKUMgt");
String EditSKU=(String)adminServiceDetailsMap.get("EditSKU");
String AddSKU=(String)adminServiceDetailsMap.get("AddSKU");

String marginSetup=(String)adminServiceDetailsMap.get("marginSetup");
String WLMargin=(String)adminServiceDetailsMap.get("WLMargin");
String AgentMargin=(String)adminServiceDetailsMap.get("AgentMargin");
String EGENClientMargin=(String)adminServiceDetailsMap.get("EGENClientMargin");

String Reports=(String)adminServiceDetailsMap.get("Reports");
String TransactionReport=(String)adminServiceDetailsMap.get("TransactionReport");
String TranChannel=(String)adminServiceDetailsMap.get("TranChannel");
String TranEGEN=(String)adminServiceDetailsMap.get("TranEGEN");
String ManagementReport=(String)adminServiceDetailsMap.get("ManagementReport");
String MgtChannel=(String)adminServiceDetailsMap.get("MgtChannel");

String MgtEGEN=(String)adminServiceDetailsMap.get("MgtEGEN");
String FinanceReport=(String)adminServiceDetailsMap.get("FinanceReport");
String FinanceChannel=(String)adminServiceDetailsMap.get("FinanceChannel");
String FinanceEgen=(String)adminServiceDetailsMap.get("FinanceEgen");
String Statements=(String)adminServiceDetailsMap.get("Statements");
String StmtChannel=(String)adminServiceDetailsMap.get("StmtChannel");
String stmtEGEN=(String)adminServiceDetailsMap.get("stmtEGEN");
String CCModule=(String)adminServiceDetailsMap.get("CCModule");
String TransactionStatus=(String)adminServiceDetailsMap.get("TransactionStatus");
String NoticeAlert=(String)adminServiceDetailsMap.get("NoticeAlert");
String CCInfo=(String)adminServiceDetailsMap.get("CCInfo");

String ClientReport=(String)adminServiceDetailsMap.get("ClientReport");
String Tranclientreport=(String)adminServiceDetailsMap.get("Tranclientreport");
String SchedularRun=(String) adminServiceDetailsMap.get("SchedularRun");
String CCSearchUser=(String) adminServiceDetailsMap.get("CCSearchUser");
String ChargesSet=(String) adminServiceDetailsMap.get("ChargesSet");
String AgentCharges=(String) adminServiceDetailsMap.get("AgentCharges");
String APIClientCharges=(String) adminServiceDetailsMap.get("APIClientCharges");



String balance="";
String userName="";
HashMap dynamicDetailsMap=new HashMap();
String helpMobileNo="";
String headerImage="";
String helpEmailId="";
String clientUserType="";



if(adminUserType.equalsIgnoreCase("superadmin")){

 balance=SuperAdminLoginDao.getSuperAdminBalance(userId); 
 userName="Super Admin";
 headerImage="tep/logo.jpg";
 clientUserType="TEP";


}

else
{

 
 balance=LoginDao.getPortalAdminBalance(userId);

  userName=(String)session.getAttribute("userName");


 dynamicDetailsMap=(HashMap)session.getAttribute("dynamicDetails");

 helpMobileNo=(String)dynamicDetailsMap.get("helpMobileNo");

 headerImage=(String)dynamicDetailsMap.get("mdHeaderHomeImage");
 helpEmailId=(String)dynamicDetailsMap.get("helpEmailId");
  clientUserType=(String)dynamicDetailsMap.get("ClientUserType");

 if(clientUserType==null){
 clientUserType="NoOne";
 }


}

%>

<script type="text/javascript" language="javascript">

function change()
{
document.getElementById("chang").style.display='block';
}
function block()
{
document.getElementById("chang").style.display='none';
}

function checkSession()
{

createXMLHttpRequest();
xmlHttp.onreadystatechange = printValues;
//alert("beforer call ")
xmlHttp.open("POST","commonAction.action?param=checkSession", true);
xmlHttp.send(null);


}


function printValues()
{

	
if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response=xmlHttp.responseText;
				//alert("reponse is>>"+response);
				
				if(response=="valid")
				{
			
								
				}
				else
				{
				
			
				sessionExpire();

				}
			}
		}

		return true;
}

function createXMLHttpRequest()
{
	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		}
}

function sessionExpire(){

document.sessionForm.action="sessionExpire.action";
document.sessionForm.submit();
}
</script>
<script>
window.history.forward();
function noBack()
{
    window.history.forward();
}
</script>

<script type="text/javascript" language="javascript">
	 setInterval(function(){ 
		 var today = new Date().getHours();
		 if (today >= 7 && today <= 22) {
			$.ajax({
	    		type: "GET",
	    		url: "dbTask.action?param=balanceRequest",
	   			success: function (response) {
					if(response=='Y')
					{
						var l = window.location;
						var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
						$.notify({
							title: "<strong>Balance Request : </strong> ",
							message: "Hi Support, New balance request arrived. Please check !",
							url: base_url+"/TradeBalActivity.action?param=TBclientAdmin",
							target: "_self"
						});
					}
				},
	
		 		failure: function (response) {
	
		    	}
			});
		 }

	}, 5000);

	setInterval(function(){ 
		 var today = new Date().getHours();
		 if (today >= 7 && today <= 22) {
			$.ajax({
	    		type: "GET",
	    		url: "dbTask.action?param=TicketStatus",
	   			success: function (response) {
					if(response=='Y')
					{
						var l = window.location;
						var base_url = l.protocol + "//" + l.host + "/" + l.pathname.split('/')[1];
						$.notify({
							title: "<strong>Ticket Request : </strong> ",
							message: "Hi Support, New commplaint has arrived. Please check !",
							url: base_url+"/dbTask.action?param=viewTicket",
							target: "_self"
						});
					}
				},
	
		 		failure: function (response) {
	
		    	}
			});
		 }

	}, 60000);
</script>


</head>

<body onload="checkSession();">
<form name="sessionForm">

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="header headertext">
<tr><td  align="center" width="100%" valign="top">
<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">



<tr><td align="left" style="height: 30;width:100 ">
<!-- it code for logo id  onmouseover="change();" onmouseout="block();"-->
<a href="commonAction.action?param=home" target="_parent"><img style="height: 70px;width:180px " src="images/<%=headerImage%>" /></a><!--<span style="font-size:12px;">Change Logo</span>-->
		

</td>

<td width="30%" align="right" height="102" valign="top" style="padding:1px 0 0 0;">

<%if(adminUserType.equalsIgnoreCase("superadmin")){%>

<table cellpadding="0" cellspacing="0" width="100%" align="right" border="0" class="headertext">
<tr align="right">
<td width="30%" height="18" valign="middle">User Name</td>
<td width="20%" align="center" valign="middle">:</td>
<td width="50%" valign="middle"><%=userName%></td>
</tr>



<tr align="right">
<td valign="middle" height="18">Current Balance</td>
<td valign="middle" align="center">:</td>
<td valign="middle">Rs. <%=balance%></td>
</tr>

<!--<tr align="right">
<td height="18" valign="middle">Call Us</td>
<td valign="middle" align="center">:</td>
<td valign="middle"><%=helpMobileNo%></td>
</tr>

<tr align="right">
<td valign="middle" height="18">Write Us</td>
<td valign="middle" align="center">:</td>
<td valign="middle"><a href="mailto:<%=helpEmailId%>"><%=helpEmailId%></a></td>
</tr>


-->

</table>

<%}else{%>


<table cellpadding="0" cellspacing="0" width="100%" align="right" border="0" class="headertext">
<tr align="right">
<td width="30%" height="18" valign="middle">User Name</td>
<td width="20%" align="center" valign="middle">:</td>
<td width="50%" valign="middle"><%=userName%></td>
</tr>

<tr align="right">
<td valign="middle" height="18">User ID</td>
<td valign="middle" align="center">:</td>
<td valign="middle"><%=userId%></td>
</tr>

<tr align="right">
<td valign="middle" height="18">Current Balance</td>
<td valign="middle" align="center">:</td>
<td valign="middle">Rs. <%=balance%></td>
</tr>

<tr align="right">
<td height="18" valign="middle">Call Us</td>
<td valign="middle" align="center">:</td>
<td valign="middle"><%=helpMobileNo%></td>
</tr>

<tr align="right">
<td valign="middle" height="18">Write Us</td>
<td valign="middle" align="center">:</td>
<td valign="middle"><a href="mailto:<%=helpEmailId%>"><%=helpEmailId%></a></td>
</tr>




</table>

<%}%>

</td>

      
  </tr>
        <tr>
          <td colspan="2" height="3" bgcolor="c2943c"></td>
        </tr>
        <tr>
          <td colspan="2" height="35" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td align="left" valign="top" width="72%">
                 <ul id="nav">
				 <% if(activity.equalsIgnoreCase("Y")){%>
                 	<li class="top"><a href="#" class="top_link" target="_parent"><span class="down">Activity</span></a>
                      <ul class="sub">
					   <% if(UserRegistration.equalsIgnoreCase("Y")){%>
					  <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					  <a href="#" class="fly">User Registration</a>
					  <ul>
					 
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserRegistartionActivity.action?param=internalUser" class="fly">Internal User</a></li>
					
						<% if(CorporateUserreg.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserRegistartionActivity.action?param=CorporateUser" class="fly">Corporate User</a></li>
						<%}%>
						<% if(RetailUserreg.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserRegistartionActivity.action?param=RetailUser" class="fly">Retail User</a></li>
						<%}%>
               		  </ul>
                 </li>
				 <%}%>
				<% if(AdminTasks.equalsIgnoreCase("Y") || userId.equalsIgnoreCase("5101")){%>
				<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				 <a href="userActivation.action?param=searchUser" class="fly">Admin Tasks</a>
				 <ul>
				 <% if(ActivityAssignment.equalsIgnoreCase("Y")){%>
				 	<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="activityAssignment.action?param=assignActivitySearch" class="fly">Activity Assignment</a></li>
				<%}%>	
				<% if(CategoryAssignment.equalsIgnoreCase("Y")){%>	
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="categoryAssignment.action?action=view" class="fly">Category Assignment</a></li>
				<%}%>
				<% if(SchedulerRun.equalsIgnoreCase("Y")){%>	
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="runSchedular.action?param=getPage" class="fly">Scheduler Run</a></li>
				<%}%>
				<% if(DBTask.equalsIgnoreCase("Y") ){%>	
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="dbTask.action?param=getPage" class="fly">DB Tasks</a>
					</li>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="dbTask.action?param=PayOutSetup" class="fly">Pay Out Setup</a></li>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="dbTask.action?param=PayOutSchedular" class="fly">Run Commission Pay Out</a></li>
				<%}%>
				<% if(DBTask.equalsIgnoreCase("Y")){%>	
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="addbankpage.action" class="fly">Add Collection Bank</a></li>
				<%}%>
                 </ul>
                </li>
				 <%}%>
				 <% if(TradeBalanceActivity.equalsIgnoreCase("Y")){%>
				 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="" class="fly">Trade Balance Activity</a>
					<ul>
				 	<% if(TBSuperAdmin.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="TradeBalActivity.action?param=TBsuperAdmin" class="fly">Super Admin</a></li>
				 	<%}%>
				 		<% if(TBFinance.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="TradeBalActivity.action?param=TBactivityAdmin" class="fly">Finance Admin</a></li>
				 	<%}%>
				 	<% if(TBClient.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="TradeBalActivity.action?param=TBclientAdmin" class="fly">Client Activity</a></li>
				 	<%}%>
					</ul>
				</li>
				 <%}%>
				 <% if(userId.equalsIgnoreCase("1")){%>
				 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="#" class="fly">Alert Activity</a>
					<ul>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="dbTask.action?param=bulksmsandmail" class="fly">Bulk SMS and Mail</a></li>
					</ul>
				</li>
				 <%}%>
				 <% if(userId.equalsIgnoreCase("5101") || userId.equalsIgnoreCase("1")){ %>	
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="dbTask.action?param=viewTicket" class="fly">View Tickets</a>
						</li>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="#" class="fly">Move Entity</a>
					 	<ul>
							<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
							<a href="Moveentity.action?param=moveentity" class="fly">Agent Mapping</a>
							</li>
							<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
								<a href="Moveentity.action?param=moveentityds" class="fly">DS Mapping</a>
							</li>
						</ul>
					</li>
				<%}%>
				 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="usertracking.action?param=page" class="fly">User Tracking</a>
				</li>
				 </ul>
				 </li>
				  <%}%>
                 <li class="top"><a class="top_link"><span class="down">I</span></a></li>
				 <% if(ControlPanel.equalsIgnoreCase("Y")){%>
                 <li class="top"><a href="#"  class="top_link"><span class="down">Control&nbsp;Panel</span></a>
                 <ul class="sub">
				 <% if(ProfileManagement.equalsIgnoreCase("Y")){%>
				 <li><a href="#">Profile Management</a>
					<ul>
					<% if(SelfProfile.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="selfUserProfile.action?param=viewSelfProfile" class="fly">Self Profile</a></li>
						<%}%>
						<% if(InternaluserProfileMgt.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserActivationActivity.action?param=internalUser" class="fly">Internal User</a></li>
						<%}%>
						<% if(CorporateUserProfileMgt.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserActivationActivity.action?param=CorporateUser" class="fly">Corporate User</a></li>
						<%}%>
						<% if(RetailUserProfileMgt.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="UserActivationActivity.action?param=RetailUser" class="fly">Retail User</a></li>
						<%}%>
						<!--<li><a href="FindUser.action" class="fly">View User Details</a></li>-->
				  </ul>
				</li>
				<%}%>
				
				<% if(OpsManagement.equalsIgnoreCase("Y")){%>
				<li><a href="#">Ops Management</a>
					<ul>
	                	<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
		                <a href="ServiceManagement.action?param=DMRService" class="fly">DMR Vendor Management</a></li>
		                <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
		                <a href="ServiceManagement.action?param=SMSService" class="fly">SMS Vendor Management</a></li>
		                <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
		                <a href="ServiceManagement.action?param=SMPage" class="fly">Vendor Management</a></li>
		                <%if(userId.equalsIgnoreCase("1") || userId.equalsIgnoreCase("5101"))
		                { %>
		                <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
		                <a href="ServiceManagement.action?param=vendorIp" class="fly">Change Vendor IP</a></li>
		                <%} %>
	                </ul>
				</li>
				<%}%>
				<% if(SKUMgt.equalsIgnoreCase("Y")){%>
				<li><a href="#">SKU Management</a>
                    <ul>
					<% if(EditSKU.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="SKUaction.action?param=getSKUPage" class="fly">Edit SKU</a></li>
					<%}%>
					<% if(AddSKU.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="SKUaction.action?param=addSKUPage" class="fly">Add SKU</a></li>
					<%}%>
					</ul>
                </li>
				<%}%>
				<% if(EGENControl.equalsIgnoreCase("Y")){%>
				<li><a href="#">Manage API</a>
                    <ul>
					<% if(OperatorManagement.equalsIgnoreCase("Y")){%>
                        <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="EgenCommsion.action?param=blockOperator" class="fly">Operator Management</a></li>
					 <%}%>
					<% if(ClientServiceControl.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="EgenCommsion.action?param=blockServAgent" class="fly">Client Service Control</a></li>
					 <%}%>
					<% if(VendorManagement.equalsIgnoreCase("Y")){%>
                        <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="OperatorControlAction.action?param=egen" class="fly">Vendor Management</a></li>
					 <%}%>
					<% if(ClientOperatorControl.equalsIgnoreCase("Y")){%>
                        <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="EgenServiceControl.action" class="fly">Client Operator Control</a></li>
					 <%}%>
					<% if(EGENRefund.equalsIgnoreCase("Y")){%>
                        <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="EgenRefund.action?param=getEgenRefundPage" class="fly">Transaction Refund</a></li>
					 <%}%>
					<% if(EgenTranStatus.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="SearchTranEgen.action?param=SearchTranpage" class="fly">API Transaction Status</a></li>
					 <%}%>
					 
						<!--<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="dbTask.action?param=getPage" class="fly">DB Tasks</a></li>-->
					 
                     </ul>
                  </li>
				  <%}%>
				  <% if(marginSetup.equalsIgnoreCase("Y")){%>
				<li><a href="#">Margin Set</a>
                   <ul>
				   <% if(WLMargin.equalsIgnoreCase("Y")){%>
				  	 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				 	 <a href="WLMergin.action?param=getwlMdsMarginPage" class="fly">Client- MDS</a></li>
					 <%}%>
					<% if(AgentMargin.equalsIgnoreCase("Y")){%>
				  	 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				   	 <a href="AgentMergin.action?param=getAgentMargin" class="fly">Agent</a></li>
					<%}%>
					<% if(EGENClientMargin.equalsIgnoreCase("Y")){%>
				   	 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					 <a href="EgenCommsion.action?param=EgenCommPage" class="fly">API Client</a></li>
					<%}%>
				   </ul>
                </li>
				<%}%>
				<% if(ChargesSet.equalsIgnoreCase("Y")){%>
				<li><a href="#">Charges Set</a>
                   <ul>
				   <% if(AgentCharges.equalsIgnoreCase("Y")){%>
				  	 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				 	 <a href="AgentCharge.action" class="fly">Agent</a></li>
					 <%}%>
					<% if(APIClientCharges.equalsIgnoreCase("Y")){%>
				  	 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				   	 <a href="#" class="fly">API Client</a></li>
					<%}%>
				   </ul>
                </li>
				<%}%>
				<% if(CutOffManagement.equalsIgnoreCase("Y")){%>
				<li><a href="#" target="_parent">Cut-Off Management</a>
					<ul>
					<% if(CutOffSetup.equalsIgnoreCase("Y")){%>
                        <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="cutoffAmount.action?param=cutoffAmountSearch" class="fly">Cut-Off Setup</a></li>
					<%}%>
					<% if(CutOffView.equalsIgnoreCase("Y")){%>
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="viewCutoffAmount.action?param=cutoffAmountSearch" class="fly">View Cut-Off</a></li>
					<%}%>
                    </ul>
                </li>
				<%}%>
				<% if(AccountMgt.equalsIgnoreCase("Y")){%>
				<li><a href="#" target="_parent">Account Management</a>
                     <ul>
					 <% if(AccountManagement.equalsIgnoreCase("Y")){%>
                         <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						 <a href="AccountAdjustment.action?param=AccountAdjustment" class="fly">Account Management </a></li>
					<%}%>
					<% if(commTDSmgt.equalsIgnoreCase("Y")){%>
						 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						 <a href="CommTDSAdjustment.action?param=CommTdsAdjustment" class="fly">Commision/TDS Management </a></li>
					<%}%>
					<% if(Refund.equalsIgnoreCase("Y")){%>
						 <li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						 <a href="Refund.action?param=getRefundPage" class="fly">Refund</a></li>
					<%}%>
					</ul>
                </li>
				<%}%>
				
                
				<%if(userId.equalsIgnoreCase("1")){ %>
				<li style="border-bottom-color:ae4913;border-top-width:1px;border-top-style:solid; border-bottom-width:1px; border-bottom-style:solid">
					<a href="RegistrationEdit.action?param=LimitUser" class="fly">User Registration Limit</a>
				</li>
				<%}%>
				<% if(userId.equalsIgnoreCase("1")){ %>	
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="deductionPage.action?param=getPage" class="fly">Deduction Query</a></li>
				<%}%>
				<% if(userId.equalsIgnoreCase("1")){ %>	
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="deductionPage.action?param=getPage" class="fly">Credit Query</a></li>
				<%}%>
				<% if(userId.equalsIgnoreCase("1")){ %>	
						<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
						<a href="blockServicesPage.action?param=getPage" class="fly">Service Management</a></li>
				<%}%>
			</ul>
			
          </li>
		  		<%}%>
		  <li class="top"><a class="top_link"><span class="down">I</span></a></li>
		  <% if(Reports.equalsIgnoreCase("Y")){%>
		  <li class="top"><a href="#"  class="top_link"><span class="down">Reports</span></a>
             <ul class="sub">
			 <% if(TransactionReport.equalsIgnoreCase("Y")){%>
			 <li><a href="#">Transaction Reports </a>
                 <ul>
				  <% if(TranChannel.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="reportDownload.action?param=TranChannelReport" class="fly">Channel</a> </li>
				 <%}%>
				 <% if(TranEGEN.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="egenReportDownload.action?param=TranEGENReport" class="fly">API</a> </li>
				 <%}%>
				 </ul>
             </li>
			 <%}%>
			 <% if(ManagementReport.equalsIgnoreCase("Y")){%>
			<li><a href="#">Management Reports </a>
                <ul>
				 <% if(MgtChannel.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="reportDownload.action?param=MgtChannelReport" class="fly">Channel</a> </li>
				<%}%>
				 <% if(MgtEGEN.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="egenReportDownload.action?param=MgtEGENReport" class="fly">API</a> </li>
				<%}%>
				</ul>
            </li>
			<%}%>
			<% if(FinanceReport.equalsIgnoreCase("Y")){%>
			<li><a href="#">Finance Reports </a>
                <ul>
				<% if(FinanceChannel.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="reportDownload.action?param=FinanceChannelReport" class="fly">Channel</a> </li>
				<%}%>
				<% if(FinanceEgen.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="egenReportDownload.action?param=FinanceEGENReport" class="fly">API</a> </li>
				<%}%>
				</ul>
            </li>
			<%}%>
			<% if(Statements.equalsIgnoreCase("Y")){%>
			<li><a href="#">Statements</a>
                <ul>
				<% if(StmtChannel.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="searchAccountStatement.action" class="fly">Channel</a> </li>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="searchPendingAccountStatement.action" class="fly">Channel Pending</a> </li>
				<%}%>
				
				<%if(stmtEGEN.equalsIgnoreCase("N")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="iciciAccountStatement.action" class="fly">ICICI Statement</a> </li>
				<%}%>
				</ul>
            </li>
			<%}%>
			<% if(ClientReport.equalsIgnoreCase("Y")){%>
			<li><a href="#">Client Reports </a>
                <ul>
				<% if(Tranclientreport.equalsIgnoreCase("Y")){%>
				<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
				<a href="ClientTranReport.action?param=ClientReport" class="fly">Transaction Report</a> </li>
				<%}%>
				</ul>
            </li>
			<%}%>
			<% if(CCModule.equalsIgnoreCase("Y")){%>
			<li><a href="#">CC Module</a>
                <ul>
				<% if(TransactionStatus.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="SearchTran.action?param=SearchTranpage" class="fly">Transaction Status</a> </li>
				<%}%> 
				<% if(NoticeAlert.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="NoticeAlert.action" class="fly">Notice-Alert</a> </li>
				<%}%> 
				<% if(CCInfo.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="ccInfoAction.action?param=ccinfopage" class="fly">CC-Information</a> </li>
				<%}%>
				<% if(CCSearchUser.equalsIgnoreCase("Y")){%>
					<li style="border-bottom-color:ae4913; border-bottom-width:1px; border-bottom-style:solid">
					<a href="CCSearchUser.action?param=getSearchUserPage" class="fly">User Search</a> </li>
				<%}%>
				</ul>
            </li>			
			<%}%> 
        </ul>
        </li>
		<%}%>
			
		</ul>
				  
				  </td>
                <td align="right" width="22%">
                <ul id="nav">
                    <li class="top"><a href="commonAction.action?param=home" class="top_link" target="_parent"><span>Home</span></a></li>
                    <li class="top"><a class="top_link"><span class="down">I</span></a></li>
                    <li class="top"><a href="changePassword.action?param=resetPassword" class="top_link" target="_parent"><span class="down">Change&nbsp;Password</span></a></li>
                    <li class="top"><a class="top_link"><span class="down">I</span></a></li>
                    <li class="top"><a href="commonAction.action?param=logout" target="_parent"class="top_link"><span class="down">Log&nbsp;Out</span></a></li>
                    
                  </ul></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</form>
</body>
</html>

