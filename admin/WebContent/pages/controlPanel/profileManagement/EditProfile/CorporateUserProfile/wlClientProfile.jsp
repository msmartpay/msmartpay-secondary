<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.formBean.dynamicDetails.DynamicDetailsFormBean"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<%
DynamicDetailsFormBean bean=(DynamicDetailsFormBean)request.getAttribute("wlDetailsFormBean");
String message=(String)request.getAttribute("message");
if(message==null)message="";
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
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                Corporate User > White Label Client
                </td>
              </tr>
              <tr>
                <td width="720px" align="center"><div class="accordion" style="padding:20px;" id="border">
				
				
				<form name="registration" method="post">
                
                    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                  
                      <tr>
                        <td>
                            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl" >
							
							<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
							
                              
                    
                      <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>Client Id</strong></td>
                                <td  align="left">:</td>
                                <td align="left"><%=bean.getClientId() %> </td>
                              </tr>
                           
							  
                             
                              
                              <tr>
                                <td width="15%"></td>
                                <td width="33%" height="30" align="left"><strong>Client Type</strong></td>
                                <td width="5%" align="left">:</td>
                                <td width="46%" align="left">
                                <%
								String Flag=(String) bean.getClientFlag();
								String Clientflag="";
								if(Flag.equalsIgnoreCase("0")){
								Clientflag="TEP";
								}
								else if(Flag.equalsIgnoreCase("1")){
								Clientflag="REP";
								}
								else{
								Clientflag="Combo";
								}
								%>
								<%=Clientflag%>
								</td>
                              </tr>
                              
                              	<tr>
                              <td></td>
                              <td height="30" align="left"><strong>Category</strong></td>
                              <td  align="left">:</td>
                              <td  align="left">
                             <%=bean.getCategoryName()%>
                                </td>
							</tr>
                            
                            
                              <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>Company/Firm Name</strong></td>
                                <td  align="left">:</td>
                                <td align="left">
								<input type="text" class="style2" name="companyName" tooltipText="Type Company/Firm Name in this box" value="<%=bean.getCompanyName() %>"/> </td>
                              </tr>
                           
							  <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>Domain Name</strong></td>
                                <td  align="left">:</td>
                                <td align="left">
								<input type="text"  class="style2"  name="domainName" tooltipText="Type Domain Name in this box" value="<%=bean.getDomainName() %>"/>
                                 
                                 </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>Help Desk - Email ID</strong></td>
                                <td  align="left">:</td>
                                <td align="left">
                                <input type="text"  class="style2" name="HelpEmailId" onchange="checkemail();" value="<%=bean.getHelpEmailId()%>" tooltipText="Type Help Desk - Email ID in this box"   />
                                     </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>Help Desk - Mobile Number</strong></td>
                                <td  align="left">:</td>
                                <td align="left">
                                 <input type="text"  class="style2"  name="HelpMobileNo" value="<%=bean.getHelpMobileNo()%>" tooltipText="Type Help Desk - Mobile Number in this box"  onkeypress="return digitonly(this,event)" maxlength="10" />
                                     </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>TXN Notification - Email ID</strong></td>
                                <td  align="left">:</td>
                                <td align="left"><input type="text"  class="style2"  name="txnNotificationEmailID" value="<%=bean.getTxnNotificationID()%>" onchange="checkemail7();" tooltipText="Type TXN Notification - Email ID in this box"  />
                                     </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30"><strong>User Info - URL</strong></td>
                                <td  align="left">:</td>
                                <td align="left">
								<input type="hidden" name="clientID" value="<%=bean.getClientId()%>"/>
                                      <input type="text"  class="style2" name="careUrl" value="<%=bean.getCareUrl()%>" tooltipText="Type User Info - URL in this box"   />
                                    </td>
                              </tr>
                           
                            </table>
                          </h4></td>
                      </tr>
                    
                      <tr>
                      <td height="60" align="center" valign="middle" style="padding-left:20px">
                      <input name="Submit"  type="button" value="Submit" class="cls_btn" onclick="submitForm()"/>
                                              
                          </td>
                    </tr>
                    </table>
                    
					</form>
                    
                    
                  </div>
                  
                  </td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
</body>
</html>
<style type="text/css">
.accordion {
	width: 1000px;
}
.accordion h3 {
	border:#a74312 solid 1px; 
	padding: 7px 15px;
	margin: 0; font-size:14px; font-weight:bold; font-family:Calibri; color:#a74312;
	

}

.accordion h4 {
	
}
</style>


<script language="JavaScript">



var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

function submitForm()
{

document.registration.action="wlProfile.action?param=updatewlProfile";
document.registration.submit();
}
		
	
	

	function checkemail(){

		var str=document.registration.agentPanelHelpEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.agentPanelHelpEmailId.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail1(){

		var str=document.registration.balanceDeskEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.balanceDeskEmailId.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail2(){

		var str=document.registration.busTicketEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.busTicketEmailId.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail3(){

		var str=document.registration.rechargeEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.rechargeEmailId.focus();
		testresults=false;
		}
		return testresults;
		
		}
	function checkemail4(){

		var str=document.registration.offlineServiceEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.offlineServiceEmailId.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail5(){

		var str=document.registration.distributorHelpEmailId1.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.distributorHelpEmailId1.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail6(){

		var str=document.registration.distributorHelpEmailId2.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.distributorHelpEmailId2.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail7(){

		var str=document.registration.agentCellEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.agentCellEmailId.focus();
		testresults=false;
		}
		return testresults;

		}
	function checkemail8(){

		var str=document.registration.railTicketEmailId.value;
		var filter=/^.+@.+\..{2,3}$/

		if (filter.test(str))
		testresults=true;
		else {
		alert("Please input a valid email address!");
		document.registration.railTicketEmailId.focus();
		testresults=false;
		}
		return testresults;

		}

</script>
