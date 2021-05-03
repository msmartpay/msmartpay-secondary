<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@page import = "java.util.HashMap" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/jquery.js"></script>
<script language="Javascript" src="scripts/Localitysimple1.js"></script>
<script type="text/javascript" src="scripts/calendar.js?random=20060118"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<style>
.style1{color:#a74312 !important;}
.stylem{color:#a74312 !important;}
</style>

<script type="text/javascript">
function altRows(id){
	if(document.getElementsByTagName){  
		
		var table = document.getElementById(id);  
		var rows = table.getElementsByTagName("tr"); 
		 
		for(i = 0; i < rows.length; i++){          
			if(i % 2 == 0){
				rows[i].className = "evenrowcolor";
			}else{
				rows[i].className = "oddrowcolor";
			}      
		}
	}
}

window.onload=function(){
	altRows('alternatecolor');
}
</script>

<style type="text/css">
table.altrowstable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	font-size:12px;
	color:#a74312;
	border-width: 1px;
	border-color: #a9c6c9;
	border-collapse: collapse;
}
table.altrowstable th {
	border-width: 1px;
	padding: 8px;
	color:#fff;
	border-style: solid;
	border-color: #a74312;
	font-size:16px;
	background-color:#a74312;
	text-align:center;
	
}
table.altrowstable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a74312;
	font-size:12px;
}
.oddrowcolor{
	background-color:#fff;
}
.evenrowcolor{
	background-color:#f4e5b3;
}
</style>

</head>
<body>
<%
String activityPortalUserId=(String)request.getAttribute("activityPortalUserId");
HashMap portalUserServiceMap=(HashMap)request.getAttribute("portalUserServiceMap");

String ActUserRegistration=(String)portalUserServiceMap.get("UserRegistration");
String ActInternalUserreg=(String)portalUserServiceMap.get("InternalUserreg");
String ActCorporateUserreg=(String)portalUserServiceMap.get("CorporateUserreg");
String ActRetailUserreg=(String)portalUserServiceMap.get("RetailUserreg");
String ActMDReg=(String)portalUserServiceMap.get("MDReg");

String ActAdminTasks=(String)portalUserServiceMap.get("AdminTasks");
String ActActivityAssignment=(String)portalUserServiceMap.get("ActivityAssignment");
String ActCategoryAssignment=(String)portalUserServiceMap.get("CategoryAssignment");
String ActDBTask=(String)portalUserServiceMap.get("DBTask");
String ActSchedulerRun=(String)portalUserServiceMap.get("SchedulerRun");

String ActTradeBalanceActivity=(String)portalUserServiceMap.get("TradeBalanceActivity");
String ActTBSuperAdmin=(String)portalUserServiceMap.get("TBSuperAdmin");
String ActTBFinance=(String)portalUserServiceMap.get("TBFinance");
String ActTBClient=(String)portalUserServiceMap.get("TBClient");


String ActControlPanel=(String)portalUserServiceMap.get("ControlPanel");
String ActProfileManagement=(String)portalUserServiceMap.get("ProfileManagement");
String ActInternaluserProfileMgt=(String)portalUserServiceMap.get("InternaluserProfileMgt");
String ActCorporateUserProfileMgt=(String)portalUserServiceMap.get("CorporateUserProfileMgt");
String ActRetailUserProfileMgt=(String)portalUserServiceMap.get("RetailUserProfileMgt");
String ActSelfProfile=(String)portalUserServiceMap.get("SelfProfile");

String ActCutOffManagement=(String)portalUserServiceMap.get("CutOffManagement");
String ActCutOffSetup=(String)portalUserServiceMap.get("CutOffSetup");
String ActCutOffView=(String)portalUserServiceMap.get("CutOffView");

// Start From Here 
String ActAccountMgt=(String)portalUserServiceMap.get("AccountMgt");
String ActAccountManagement=(String)portalUserServiceMap.get("AccountManagement");
String ActcommTDSmgt=(String)portalUserServiceMap.get("commTDSmgt");
String ActRefund=(String)portalUserServiceMap.get("Refund");

String ActEGENControl=(String)portalUserServiceMap.get("EGENControl");
String ActOperatorManagement=(String)portalUserServiceMap.get("OperatorManagement");
String ActClientServiceControl=(String)portalUserServiceMap.get("ClientServiceControl");
String ActVendorManagement=(String)portalUserServiceMap.get("VendorManagement");
String ActClientOperatorControl=(String)portalUserServiceMap.get("ClientOperatorControl");
String ActClientCutOffView=(String)portalUserServiceMap.get("ClientCutOffView");
String ActEGENRefund=(String)portalUserServiceMap.get("EGENRefund");
String ActEgenTranStatus=(String)portalUserServiceMap.get("EgenTranStatus");

String ActOpsManagement=(String)portalUserServiceMap.get("OpsManagement");
String ActServiceManagement=(String)portalUserServiceMap.get("ServiceManagement");
String ActTEPvendorManagement=(String)portalUserServiceMap.get("TEPvendorManagement");

String ActSKUMgt=(String)portalUserServiceMap.get("SKUMgt");
String ActEditSKU=(String)portalUserServiceMap.get("EditSKU");
String ActAddSKU=(String)portalUserServiceMap.get("AddSKU");

String ActmarginSetup=(String)portalUserServiceMap.get("marginSetup");
String ActWLMargin=(String)portalUserServiceMap.get("WLMargin");
String ActAgentMargin=(String)portalUserServiceMap.get("AgentMargin");
String ActEGENClientMargin=(String)portalUserServiceMap.get("EGENClientMargin");

String ActTransactionReport=(String)portalUserServiceMap.get("TransactionReport");
String ActTranChannel=(String)portalUserServiceMap.get("TranChannel");
String ActTranEGEN=(String)portalUserServiceMap.get("TranEGEN");
String ActManagementReport=(String)portalUserServiceMap.get("ManagementReport");
String ActMgtChannel=(String)portalUserServiceMap.get("MgtChannel");

String ActMgtEGEN=(String)portalUserServiceMap.get("MgtEGEN");
String ActFinanceReport=(String)portalUserServiceMap.get("FinanceReport");
String ActFinanceChannel=(String)portalUserServiceMap.get("FinanceChannel");
String ActFinanceEgen=(String)portalUserServiceMap.get("FinanceEgen");
String ActStatements=(String)portalUserServiceMap.get("Statements");
String ActStmtChannel=(String)portalUserServiceMap.get("StmtChannel");
String ActstmtEGEN=(String)portalUserServiceMap.get("stmtEGEN");
String ActCCModule=(String)portalUserServiceMap.get("CCModule");
String ActTransactionStatus=(String)portalUserServiceMap.get("TransactionStatus");
String ActNoticeAlert=(String)portalUserServiceMap.get("NoticeAlert");
String ActCCInfo=(String)portalUserServiceMap.get("CCInfo");
String ActClientReport=(String)portalUserServiceMap.get("ClientReport");
String ActTranclientreport=(String)portalUserServiceMap.get("Tranclientreport");
String ActCCSearchUser=(String)portalUserServiceMap.get("CCSearchUser");

// New  Add
String ActChargesSet=(String)portalUserServiceMap.get("ChargesSet");
String ActAgentCharges=(String)portalUserServiceMap.get("AgentCharges");
String ActAPIClientCharges=(String)portalUserServiceMap.get("APIClientCharges");

String InventoryMgmta=(String)portalUserServiceMap.get("InventoryMgmt");
String InventoryMgmtControllera=(String) portalUserServiceMap.get("InventoryMgmtController");
String CarBookinga=(String) portalUserServiceMap.get("CarBooking");
String HolidayPkga=(String) portalUserServiceMap.get("HolidayPkg");
String SetMarkupa=(String) portalUserServiceMap.get("SetMarkup");
String APIAssignmenta=(String) portalUserServiceMap.get("APIAssignment");


String message=(String)request.getAttribute("message");
if(message==null)message="";
%>
<center>


<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
	<form name="activityAssignmentForm" method="post" action="activityAssignment.action?param=updateActivity">
	<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="rounded-corners">
              <tr>
                <td   height="40" align="left" valign="middle" style="font-size:13px; font-weight:bold; padding-left:60px;" class="logintxt" >Activity Assignment </td>
              </tr>
			  <tr><td  colspan="10" align="center" style="font-size:18px; font-weight:bold; color:#FF0000; font-family:'Trebuchet MS';"><%=message%></td></tr>
					<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
				<tr><td width="100%" align="center"><div class="accordion" style="padding:20px;">
                  <table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11">
                    <tr>
                      <td>
                      <table width="1000"  cellspacing="0" cellpadding="0" align="center"  class="form11">
                              <tr>
                                <td width="25%" align="left" valign="top"><table width="250" class="altrowstable" id="alternatecolor3">
                                  <tr>
                                    <th colspan="3" style="border-right:1px solid #930; border-left:2px solid #a74312;">Activity</th>
                                  </tr>
                                  <tr>
								  <input type="hidden" name="activityPortalUserId"  value="<%=activityPortalUserId%>"/>
                                    <td width="75%" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;font-size:13px;">User Registration</td>	
									<%if(ActUserRegistration.equalsIgnoreCase("Y")){%>
                                    <td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									<input type="checkbox" name="userReg" id="checkbox" checked="checked"/></td>
									<%}else{%>
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									<input type="checkbox" name="userReg" id="checkbox" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Internal User</td>
                                    
									<%if(ActInternalUserreg.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="internalUserReg" id="checkbox4" cha jiecked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="internalUserReg" id="checkbox4" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Corporate User</td>
                                    
									<%if(ActCorporateUserreg.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="corporateUserReg" id="checkbox4" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="corporateUserReg" id="checkbox4" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Retail User</td>
                                    
									<%if(ActRetailUserreg.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="retailUserReg" id="checkbox4" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="retailUserReg" id="checkbox4" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Master Distributor</td>
                                    
									<%if(ActMDReg.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mdsReg" id="checkbox4" checked="checked" /></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mdsReg" id="checkbox4" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312; font-size:13px;">Admin Tasks</td>
                                    
									<%if(ActAdminTasks.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="adminTask" id="checkbox4" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="adminTask" id="checkbox4" /></td>
									<%}%>

                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Category Assignment</td>
                                    
									<%if(ActCategoryAssignment.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="categoryAssign" id="checkbox14" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="categoryAssign" id="checkbox14" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Scheduler Run</td>
                                    
									<%if(ActSchedulerRun.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="schedularRun" id="checkbox16" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="schedularRun" id="checkbox16" /></td>
									<%}%>
									
									
                                  </tr>
                                  
                                  
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">DB Tasks</td>
                                    
									<%if(ActDBTask.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="dbTask" id="checkbox16" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="dbTask" id="checkbox16" /></td>
									<%}%>
									
									
                                  </tr>
                                  
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">-----</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="schedularRun" id="checkbox16" /></td>
									</tr>
                                  
                                  <tr>
								  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">-----</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="schedularRun" id="checkbox16" /></td>
									</tr>
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312; font-size:13px;">Trade Balance Activity</td>
                                    
									<%if(ActTradeBalanceActivity.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="tradeBalanceActivity" id="checkbox4" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="tradeBalanceActivity" id="checkbox4" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Finance Admin</td>
                                    
									<%if(ActTBFinance.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbFinanceAdmin" id="checkbox18" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbFinanceAdmin" id="checkbox18" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Client Activity</td>
                                    
									<%if(ActTBClient.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbClientAdmin" id="checkbox19" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbClientAdmin" id="checkbox19" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td width="75%" style="background-color:#e8621e;color:#FFF;border-left:2px solid #a74312; font-size:13px;">Profile Management</td>
                                    
									<%if(ActProfileManagement.equalsIgnoreCase("Y")){%>
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									<input type="checkbox" name="profileMgt" id="checkbox5" checked="checked"/></td>
									<%}else{%>
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									<input type="checkbox" name="profileMgt" id="checkbox5" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Self Profile</td>
                                    
									<%if(ActSelfProfile.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="selfProfile" id="checkbox23" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="selfProfile" id="checkbox23" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Internal User</td>
                                    
									<%if(ActInternaluserProfileMgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="internal_profile_mgt" id="checkbox23" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="internal_profile_mgt" id="checkbox23" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Corporate User</td>
                                    
									<%if(ActCorporateUserProfileMgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="corporate_profile_mgt" id="checkbox23" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="corporate_profile_mgt" id="checkbox23" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;border-left:2px solid #a74312; padding-left:20px;">Retail User</td>
                                    
									<%if(ActRetailUserProfileMgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="retail_profile_mgt" id="checkbox23" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="retail_profile_mgt" id="checkbox23" /></td>
									<%}%>
									
                                  </tr>
                                  
                                </table></td>
                                <td width="25%" align="left" valign="top">
                                <table width="250" class="altrowstable" id="alternatecolor">
                                  <tr>
                                    <th colspan="2" style="border-right:1px solid #930;">Reports</th>
                                    </tr>
                                  <tr>
                                    <td width="75%" style="background-color:#e8621e;color:#FFF; font-size:13px;">Transaction Report</td>
                                    
                                      <%if(ActTransactionReport.equalsIgnoreCase("Y")){%>
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									  <input type="checkbox" name="transactionReport" id="checkbox2" checked="checked"/></td>
									  <%}else{%>
									  <td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;" >
									  <input type="checkbox" name="transactionReport" id="checkbox2" /></td>
									  <%}%>
                                   
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Channel</td>
                                   
                                      <%if(ActTranChannel.equalsIgnoreCase("Y")){%>
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									  <input type="checkbox" name="tranChannelreport" id="checkbox3" checked="checked"/></td>
									  <%}else{%>
									   <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									  <input type="checkbox" name="tranChannelreport" id="checkbox3" /></td>
									  <%}%>
                                      
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">EGEN</td>
                                    
									<%if(ActTranEGEN.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tranEgenreport" id="checkbox21" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tranEgenreport" id="checkbox21" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Management Reports</td>
                                    
									<%if(ActManagementReport.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="managementReport" id="checkbox3" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="managementReport" id="checkbox3" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Channel</td>
                                    
									<%if(ActMgtChannel.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mgtChannelReport" id="checkbox22" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mgtChannelReport" id="checkbox22" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">EGEN</td>
                                    
									<%if(ActMgtEGEN.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mgtEgenReport" id="checkbox24" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="mgtEgenReport" id="checkbox24" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Finance Reports</td>
                                    
									<%if(ActFinanceReport.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="financeReport" id="checkbox3" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="financeReport" id="checkbox3" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Channel</td>
                                    
									<%if(ActFinanceChannel.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="finChannelReport" id="checkbox26" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="finChannelReport" id="checkbox26" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">EGEN</td>
                                    
									<%if(ActFinanceEgen.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="finEgenReport" id="checkbox25" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="finEgenReport" id="checkbox25" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Statements</td>
                                    
									<%if(ActStatements.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="statement" id="checkbox3" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="statement" id="checkbox3" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Channel</td>
                                    
									<%if(ActStmtChannel.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="channelStatement" id="checkbox27" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="channelStatement" id="checkbox27" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">EGEN</td>
                                    
									<%if(ActstmtEGEN.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenStatement" id="checkbox28" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenStatement" id="checkbox28" /></td>
									<%}%>
									
                                  </tr>
                                  
                                        
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; padding-left:8px;">Client Report</td>
                                    
									<%if(ActClientReport.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientReport" id="checkbox30" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientReport" id="checkbox30" /></td>
									<%}%>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Transaction Report</td>
									<%if(ActTranclientreport.equalsIgnoreCase("Y")){%>
                                    <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientTranReport" id="checkbox32" checked="checked"/></td>
									<%}else{%>
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientTranReport" id="checkbox32" /></td>
									<%}%>
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Statements</td>
                                    <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="checkbox7" id="checkbox33" /></td>
									<!--<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="checkbox7" id="checkbox33" /></td>-->
                                  </tr>
                                  
                                  
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">CC Module</td>
                                    
									<%if(ActCCModule.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="ccModule" id="checkbox3" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="ccModule" id="checkbox3" /></td>
									<%}%>
								  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Transaction Status</td>
                                    
									<%if(ActTransactionStatus.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="transactionStatus" id="checkbox29" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="transactionStatus" id="checkbox29" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Notice-Alert</td>
                                    
									<%if(ActNoticeAlert.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="notice" id="checkbox30" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="notice" id="checkbox30" /></td>
									<%}%>
									
                                  </tr>
								  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">CC Info Page</td>
                                    
									<%if(ActCCInfo.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="ccInfo" id="checkbox30" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="ccInfo" id="checkbox30" /></td>
									<%}%>
									
                                  </tr>
								  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">User Search</td>
                                    
									<%if(ActCCSearchUser.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="CCuserSearch" id="checkbox30" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="CCuserSearch" id="checkbox30" /></td>
									<%}%>
									
                                  </tr>
                                </table></td>
                                <td width="25%" align="left" valign="top"><table width="250" class="altrowstable" id="alternatecolor2">
                                  <tr>
                                    <th colspan="2" style="border-right:1px solid #930;">Control Panel - 1</th>
                                  </tr>
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Cut-Off Management</td>
                                    
									<%if(ActCutOffManagement.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffMangt" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffMangt" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Cut-Off Setup</td>
                                    
									<%if(ActCutOffSetup.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffSetup" id="checkbox38" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffSetup" id="checkbox38" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Cut-Off View</td>
                                    
									<%if(ActCutOffView.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffView" id="checkbox39" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="cutOffView" id="checkbox39" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Account Management</td>
                                    
									<%if(ActAccountMgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="accountMgt" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="accountMgt" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Account Management</td>
                                    
									<%if(ActAccountManagement.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="accountmanagement" id="checkbox40" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="accountmanagement" id="checkbox40" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Commission-TDS</td>
                                    
									<%if(ActcommTDSmgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="commTdsMgt" id="checkbox41" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="commTdsMgt" id="checkbox41" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Refund</td>
                                    
									<%if(ActRefund.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="refund" id="checkbox42" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="refund" id="checkbox42" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">SKU Management</td>
                                    
									<%if(ActSKUMgt.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="SKUmgt" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="SKUmgt" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Edit SKU</td>
                                    
									<%if(ActEditSKU.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="editSKU" id="checkbox40" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="editSKU" id="checkbox40" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Add SKU</td>
                                    
									<%if(ActAddSKU.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="addSKU" id="checkbox41" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="addSKU" id="checkbox41" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  
                                   <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Ops Management</td>
                                    
									<%if(ActOpsManagement.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="opsMgt" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="opsMgt" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Service Management</td>
                                    
									<%if(ActServiceManagement.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="serviceMgt" id="checkbox40" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="serviceMgt" id="checkbox40" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Vendor Management</td>
                                    
									<%if(ActTEPvendorManagement.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tepVendorMgt" id="checkbox41" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tepVendorMgt" id="checkbox41" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  
                                   <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Margin Set</td>
                                    
									<%if(ActmarginSetup.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginSetup" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginSetup" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Client-MDS</td>
                                    
									<%if(ActWLMargin.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginWL" id="checkbox40" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginWL" id="checkbox40" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Agent</td>
                                    
									<%if(ActAgentMargin.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginAG" id="checkbox41" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginAG" id="checkbox41" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">API Client</td>
                                    
									<%if(ActEGENClientMargin.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginEGEN" id="checkbox42" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="marginEGEN" id="checkbox42" /></td>
									<%}%>
									
                                  </tr>

                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Charges Set</td>
                                    
									<%if(ActChargesSet.equalsIgnoreCase("Y")){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="chargeSet" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="chargeSet" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Agent</td>
                                    
									<%if("Y".equalsIgnoreCase(ActAgentCharges)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="agentCharge" id="checkbox40" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="agentCharge" id="checkbox40" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">API Client</td>
                                    
									<%if("Y".equalsIgnoreCase(ActAPIClientCharges)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="APIcharge" id="checkbox41" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="APIcharge" id="checkbox41" /></td>
									<%}%>
									
                                  </tr>
                                 
                                  
                                </table></td>
                                <%-- <td width="25%" align="left" valign="top"><table width="250" class="altrowstable" id="alternatecolor4">
                                  <tr>
                                    <th colspan="2" style="border-right:1px solid #930;">Control Panel - 2</th>
                                  </tr>
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">EGEN Control</td>
                                    
									<%if("Y".equalsIgnoreCase(ActEGENControl)){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenControl" id="checkbox6" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenControl" id="checkbox6" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Operator Management</td>
                                    
									<%if("Y".equalsIgnoreCase(ActOperatorManagement)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="operatorMgt" id="checkbox43" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="operatorMgt" id="checkbox43" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930;padding-left:20px;">Client Service Control</td>
                                    
									<%if("Y".equalsIgnoreCase(ActClientServiceControl)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientServiceControl" id="checkbox44" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientServiceControl" id="checkbox44" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Vendor Management</td>
                                    
									<%if("Y".equalsIgnoreCase(ActVendorManagement)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="vedorMgt" id="checkbox45" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="vedorMgt" id="checkbox45" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Client Operator Control</td>
                                    
									<%if("Y".equalsIgnoreCase(ActClientOperatorControl)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientOperatorControl" id="checkbox46" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientOperatorControl" id="checkbox46" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Client Cut-Off View</td>
                                    
									<%if("Y".equalsIgnoreCase(ActClientCutOffView)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientCutOffView" id="checkbox47" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="clientCutOffView" id="checkbox47" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Transaction Refund</td>
                                    
									<%if("Y".equalsIgnoreCase(ActEGENRefund)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenRefund" id="checkbox48" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenRefund" id="checkbox48" /></td>
									<%}%>
									
                                  </tr>
								  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Transaction Status </td>
                                    
									<%if("Y".equalsIgnoreCase(ActEgenTranStatus)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenTranStatus" id="checkbox48" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="egenTranStatus" id="checkbox48" /></td>
									<%}%>
									
                                  </tr>
                                  
								  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Inventory Manaement</td>
                                    
									<%if("Y".equalsIgnoreCase(InventoryMgmta)){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="inventoryMgmt" id="checkbox49" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="inventoryMgmt" id="checkbox49" /></td>
									<%}%>
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Car Booking</td>
                                    
									<%if("Y".equalsIgnoreCase(CarBookinga)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="carBooking" id="checkbox50" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="carBooking" id="checkbox50" /></td>
									<%}%>
									
                                  </tr>
								  
								  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Holiday Pkg</td>
                                    
									<%if("Y".equalsIgnoreCase(HolidayPkga)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="holidayPkg" id="checkbox51" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="holidayPkg" id="checkbox51" /></td>
									<%}%>
									
                                  </tr>
								  
								  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Inventory Mgmt Controller</td>
                                    
									<%if("Y".equalsIgnoreCase(InventoryMgmtControllera)){%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="IMController" id="checkbox52" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="IMController" id="checkbox52" /></td>
									<%}%>
									
                                  </tr>
								  
								  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">Set Markup</td>
                                    
									<%if("Y".equalsIgnoreCase(SetMarkupa)){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="setMarkup" id="checkbox53" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="setMarkup" id="checkbox53" /></td>
									<%}%>
									
                                  </tr>
                                  
                                  <tr>
                                    <td style="background-color:#e8621e;color:#FFF; font-size:13px;">API Assignment</td>
                                    
									<%if("Y".equalsIgnoreCase(APIAssignmenta)){%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="apiAssignment" id="checkbox54" checked="checked"/></td>
									<%}else{%>
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="apiAssignment" id="checkbox54" /></td>
									<%}%>
									
                                  </tr>
								  
                                  <!--
								  <tr>
                                    <td width="75%" style="background-color:#e8621e;color:#FFF; font-size:13px;">Service Management</td>
                                   
									
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312; border-right:2px solid #a74312;" >
									<input type="checkbox" name="serviceMgt" id="checkbox7" checked="checked"/></td>
									
									<td width="25%" align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312; border-right:2px solid #a74312;" >
									<input type="checkbox" name="serviceMgt" id="checkbox7" /></td>
									
								
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Web Operator Control</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="webOperatorControl" id="checkbox71" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="webOperatorControl" id="checkbox71" /></td>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">SMS Operator Control</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="smsOperatorControl" id="checkbox72" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="smsOperatorControl" id="checkbox72" /></td>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">SMS-WEB Service Control</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="smsWebServiceControl" id="checkbox73" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="smsWebServiceControl" id="checkbox73" /></td>
								
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Manage Service Error</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="manageServiceError" id="checkbox74" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930;border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="manageServiceError" id="checkbox74" /></td>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Operator Status</td>
                                   
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="operatorStatus" id="checkbox75" checked="checked"/></td>
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="operatorStatus" id="checkbox75" /></td>
								
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Agent Service Control</td>
                                   
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="agentServiceControl" id="checkbox76" checked="checked"/></td>
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="agentServiceControl" id="checkbox76" /></td>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">WL Service Control</td>
                                   
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="wlServiceControl" id="wlServiceControl" checked="checked"/></td>
									
									 <td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="wlServiceControl" id="wlServiceControl" /></td>
									
									
                                  </tr>
                                  <tr>
                                    <td style="background-color:#fff;color:#930; padding-left:20px;">Web-SMS User Control</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="webSmsUserControl" id="webSmsUserControl" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312; border-right:2px solid #a74312;">
									<input type="checkbox" name="webSmsUserControl" id="webSmsUserControl" /></td>
								
									
                                  </tr>
                                  <tr>
                                   <td width="75%" style="background-color:#e8621e;color:#FFF; font-size:13px;">Admin Task</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbLimitSetup" id="checkbox15" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#e8621e;color:#FFF;border-right:2px solid #a74312; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbLimitSetup" id="checkbox15" /></td>
									
									
                                  </tr>
                                  <tr>
                                 
                                    <td style="background-color:#fff;color:#930; border-left:2px solid #a74312; padding-left:20px;">Trade Balance Limit Setup</td>
                                    
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbLimitSetup" id="checkbox15" checked="checked"/></td>
									
									<td align="center" valign="middle" style="background-color:#fff;color:#930;border-right:2px solid #a74312; border-left:2px solid #a74312;">
									<input type="checkbox" name="tbLimitSetup" id="checkbox15" /></td>
									
									
                                  </tr>
                                  
								  -->
                                  
                              
                                 </table></td> --%>
                                </tr>
                              <tr>
                                <td colspan="3"></td>
                                <td></td>
                              </tr>
                              </table>
                          </td>
                    </tr>
                    <tr>
                      <td height="60" align="center" valign="middle" style="padding-left:0px"><a href="#" style="text-decoration:none">
                        <input name="Submit" class="form11" type="button" value="Submit" style="width:100px" onclick="submitForm()"/>
                        </a> 
                      </td>
                    </tr>
                  </table>
				  </div></td>
              </tr>
              <tr>
                <td width="100%" align="center"></td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
            </table></td>
        </tr>
      </table>
	  </form>
	  </td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</center>
</body>
</html>
<script language="javascript">
function submitForm(){
document.activityAssignmentForm.submit();
}
</script>

