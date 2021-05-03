<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="script/jquery.js"></script>
<SCRIPT type="text/javascript" src="scripts/calendar.js?random=20060118"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>


<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
<script type="text/javascript">

function submitForm()
{
var clientType=document.registration.clientType.value;
if(clientType=="-1")
{
alert("Your Form is Incomplete.");
document.registration.clientType.focus();
return false;
}

var categoryName=document.registration.categoryName.value;
if(categoryName=="-1")
{
alert("Your Form is Incomplete.");
document.registration.categoryName.focus();
return false;
}

var companyName=document.registration.companyName.value.replace(/^\s+|\s+$/, '');
if(companyName==null || companyName=="")
{
alert("Your Form is Incomplete.");
document.registration.companyName.focus();
return false;
}


var domainName=document.registration.domainName.value.replace(/^\s+|\s+$/, '');
if(domainName==null || domainName=="")
{
alert("Your Form is Incomplete.");
document.registration.domainName.focus();
return false;
}


var agentPanelHelpEmailId=document.registration.agentPanelHelpEmailId.value.replace(/^\s+|\s+$/, '');

if(agentPanelHelpEmailId== null || agentPanelHelpEmailId=="")
{
alert("Your Form is Incomplete.");
document.registration.agentPanelHelpEmailId.focus();
return false;
}


var agentPanelHelpMobileNo1=document.registration.agentPanelHelpMobileNo1.value.replace(/^\s+|\s+$/, '');

if(agentPanelHelpMobileNo1== null || agentPanelHelpMobileNo1=="")
{
alert("Your Form is Incomplete." );
document.registration.agentPanelHelpMobileNo1.focus();
return false;
}

var agentCellEmailId=document.registration.agentCellEmailId.value.replace(/^\s+|\s+$/, '');

if(agentCellEmailId== null || agentCellEmailId=="")
{
alert("Your Form is Incomplete.");
document.registration.agentCellEmailId.focus();
return false;
}

var careUrl=document.registration.careUrl.value.replace(/^\s+|\s+$/, '');

if(careUrl== null || careUrl=="")
{
alert("Your Form is Incomplete.");
document.registration.careUrl.focus();
return false;
}
var headerHomeImage=document.registration.headerHomeImage.value.replace(/^\s+|\s+$/, '');

if(headerHomeImage== null || headerHomeImage=="")
{
alert("Your Form is Incomplete. ");
document.registration.headerHomeImage.focus();
return false;
}
document.registration.action="whiteLabelRegistration.action?param=registration";
document.registration.submit();
}


function checkemail(){

	var str=document.registration.agentPanelHelpEmailId.value;
	var filter=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

	if (filter.test(str))
	testresults=true;
	else {
	alert("Please input a valid email address!");
	document.registration.agentPanelHelpEmailId.focus();
	testresults=false;
	}
	return testresults;

}

function checkemail7(){

	var str=document.registration.agentCellEmailId.value;
	var filter=/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

	if (filter.test(str))
	testresults=true;
	else {
	alert("Please input a valid email address!");
	document.registration.agentCellEmailId.focus();
	testresults=false;
	}
	return testresults;

	}

function checkHelp()
{
	
alert("Category - Define your Channel Margin Category. "+
      "Postapid - This Category recieves Real Time Margin as per the % set for the category. "+
	  "Prepaid - This Category is of Zero Margin if Margin is paid with the deposit of Trade Balance. ");
	  return false;
}

</script>
<%
ArrayList<String> list=(ArrayList)request.getAttribute("categoryList");
%>
<script language="Javascript" src="scripts/validation.js"></script>


</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">White Label Registration</td>
              </tr>
              <tr>
                <td width="100%" align="center"><div class="accordion" style="padding:20px;" id="border">
				
				<form name="registration" method="post" enctype="multipart/form-data">
                    <table class="mydata_tabl form11" width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                      <tr>
                        <td><h3 align="center" class="hd tabulardata">Product Details</h3>
                          <h4>
                            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
							
							<tr>
                                <td  colspan="10" align="center" class="dyn_mgs"><%=message%></td>
                              </tr>
							  
							  <tr>
                                <td  colspan="10" align="center">&nbsp;</td>
                               
                              </tr>
                              
                              <tr>
                                <td width="22%"></td>
                                <td width="33%" height="30" align="left">Client Type<font size="1" color="red" >&nbsp;*</font></td>
                                <td width="5%" align="left">:</td>
                                <td width="46%" align="left">
                                
                                      <select name="clientType">
                                          <option value="-1">Select</option>
                                          <option value="TEP">TEP</option>
                                          <option value="REP">REP</option>
										  <option value="Combo">Combo</option>
                                    </select>
                                  </td>
                              </tr>
                              
                              	<tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Category<font size="1" color="red" >&nbsp;*</font></td>
                              <td  align="left">:</td>
                              <td  align="left">
                              <select name="categoryName">
                            	 <option value="-1">Select</option>
					        
					        <%if(list!=null)
					        	{
					        	for(int i=0;i<list.size();i++)
					        	{
					        		
					        	%>
					        	 <option value="<%=list.get(i) %>"><%=list.get(i) %></option>
					        <%}} %>
                                         </select>
                               &nbsp;&nbsp; <a style="color:#FF0000;font-size:22;" href="#" onclick="checkHelp()"><b>Help</b></a>
								
								</td>
							</tr>
                            
                            
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Company/Firm Name <font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left"><input type="text" class="style2" name="companyName" tooltipText="Type Company/Firm Name in this box" /> </td>
                              </tr>
                           
							  <tr>
                                <td></td>
                                <td  align="left" height="30">Domain Name<font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left"><input type="text"  class="style2"  name="domainName" tooltipText="Type Domain Name in this box" />
                                 
                                 </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Help Desk - Email ID<font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left">
                                    <input type="text"  class="style2" name="agentPanelHelpEmailId" onchange="checkemail();" tooltipText="Type Help Desk - Email ID in this box"   />
                                     </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Help Desk - Mobile Number<font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left">
                                 <input type="text"  class="style2"  name="agentPanelHelpMobileNo1" tooltipText="Type Help Desk -10 Digits Mobile Number in this box"  onkeypress="return digitonly(this,event)" maxlength="10" />
                                     </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30">TXN Notification - Email ID<font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left"><input type="text"  class="style2"  name="agentCellEmailId" onchange="checkemail7();" tooltipText="Type TXN Notification - Email ID in this box"  />
                                     </td>
                              </tr>
                              <tr>
                                <td></td>
                                <td  align="left" height="30">User Info URL <font size="1" color="red" > &nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td align="left">
                                      <input type="text"  class="style2" name="careUrl" tooltipText="Type User Info - URL in this box"  />
                                    </td>
                              </tr>
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Logo Image (169px X 63px) *<font size="1" color="red" >&nbsp;*</font></td>
                                <td  align="left">:</td>
                                <td  align="left"><input type="file" style="height:25px;" name="headerHomeImage" /></td>
                              </tr>
                        
                            </table>
                          </h4></td>
                      </tr>
                      
                      <tr>
                      <td height="60" align="center" valign="middle" style="padding-left:50px">
                      		<input name="Submit" class="form11 cls_btn" type="button" value="Submit" onclick="submitForm()"/>
                          	<input name="Submit" class="form11 cls_btn" type="reset" value="Reset" />                      
                          </td>
                    </tr>
                    </table>
					</form>
                  </div></td>
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
	width: 90%;
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

</script>
