<%@ page import = "java.util.ArrayList "%>
<%@ page import = "java.util.HashMap "%>  
<%
String deleteButtonStatus="";
String buttonStatus=(String)session.getAttribute("buttonStatus");



String userType=(String)session.getAttribute("userType");
String status=(String)session.getAttribute("status");

int pageNumber=(Integer)session.getAttribute("pageNumber");
int noOfRecords=(Integer)session.getAttribute("noOfRecords");
int pageSize=(Integer)session.getAttribute("pageSize");
String advanceSearchOption=(String)session.getAttribute("advanceSearchOption");
int noOfPages=noOfRecords/pageSize;
int modulus=noOfRecords%pageSize;
if(modulus != 0){
noOfPages=noOfPages+1;
}
ArrayList apiClientSearchList=(ArrayList)session.getAttribute("apiClientSearchList");
int size=apiClientSearchList.size();
String blockStatus="";
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#message h2{height:30px; width:380px; margin:0; padding:0 0 0 20px; float:left; line-height:30px; font-size:14px; color:#ffffff; background:#a74312; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h2 img{float:right; margin:3px 3px 0 0}
#message h3{height:auto; width:380px; margin:10px 0 0 0; padding:0px 0 0 20px; float:left; line-height:30px; font-size:12px; color:#a74312; background:#ffffff; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h3 input[type=text]{margin:0 0 0 30px;  width:120px}
#message h3 img{float:right; margin:4px 47px 0 0 }
#message span{float:left; margin:0; width:400px; height:46px; background:#f4e7bd; border-top:2px solid #e6cb7b }
#button{height:26px; width:90px; float:right; margin:10px}

.poptab tr td{ color:#930;}
.poptab tr td strong{ color:#930;}
</style>
<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/stickytooltip.js"></script>


<script type="text/javascript" src="scripts/calendar.js?random=20060118"></script>

<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script language="javascript" type="text/javascript">
window.onload = msfg;
function clk()
{
	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}

 	
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	  
	     document.getElementById("a[i]").focus();
	   return false;
	   
	 }
var a = document.getElementById("message");
a.style.display='block';
var w = 400;
var h = "auto";
var left = (screen.width/2)-(w/2);
//alert(left);
var top = (screen.height/2)-(200);
//alert(top);
a.style.top= top+"px";
a.style.left= left+"px";
a.style.width=w+"px";
a.style.height=h+"px";
a.style.background="#ffffff";
a.style.border="1px solid #a74312"
a.style.position="absolute";



}

function clos()
{
var a = document.getElementById("message");
a.style.display='none';
msfg();
}

</script>
</head>
<body>



<form name="apiForm" method="post">
<div id="mystickytooltip" class="stickytooltip">
<%

					if(apiClientSearchList.size()>0){
					
					for(int i=0;i<apiClientSearchList.size();i++){
				  
				 HashMap userMap=(HashMap)apiClientSearchList.get(i);
%>
<div id="sticky1<%=i%>" class="atip" style="width:240px" align="center">
<table width="240px" align="center" class="popup_table poptab">
<tr><td align="left"><strong>Mobile Number</strong></td><td><%=userMap.get("userMobileNo")%></td></tr>
<tr><td align="left"><strong>E-Mail ID</strong></td><td><%=userMap.get("userEmailId")%></td></tr>
<tr><td align="left"><strong>Status</strong></td><td><%=userMap.get("loginStatus")%></td></tr>
</table>
</div>
<div id="sticky2<%=i%>" class="atip" style="width:240px" align="center">

<table width="240px" align="center" class="popup_table">
<tr><td align="left">First Authorized IP</td><td>
<input type="text" value="<%=userMap.get("ip1")%>" name="authorizedIp1" id="ip1<%=i %>" onBlur="isIP(this)"/></td></tr>
<tr><td align="left">Second Authorized IP</td><td><input type="text" value="<%=userMap.get("ip2")%>" name="authorizedIp2" id="ip2<%=i %>" onblur="isIP1(this)"/></td></tr>
<tr><td align="left">Status</td><td>
<select name="status"  style="width:313px" id="status<%=i %>"> 
<option value="<%=userMap.get("status")%>" selected="selected"><%=userMap.get("status")%></option>                                             
<option value="Activate">Activate</option>
<option value="Deactive">Deactive</option>                                              
</select>
</td></tr>
<tr><td align="left"></td><td><input type="hidden" id="hid<%=i %>" value="<%=userMap.get("corporateAgentId")%>"/></td></tr>
<tr><td align="left"></td><td><input type="button" id="b1" name="editbutton" value="Update" onclick="updateIP('<%=i %>')"/></td></tr>
</table>

</div>

 <%}}%>
<!--<div class="stickystatus"></div>-->
</div>
</form>



<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top" >
	<form name="apiClientActivationForm" method="post"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">

                          <tr>
                            <td valign="top" align="center"><div id="#">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td height="40" align="left" valign="middle" class="heading_mgs">Internal User > Recharge API Client
</td>                                    </tr>
							<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
							<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                                          <tr>
                                      <td height="20" align="left" valign="middle" style="padding-left:0px; font-size:13px; font-weight:bold;" 											                                       class="logintxt"></td>   </tr>
								
									<tr>
									  <td align="center"></td></tr>
								
									<tr>
									  <td align="center"></td></tr>
                                    <tr>
                                      <td valign="top">
                                      <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls"  bgcolor="#a74312">
                                        <tbody>
                                          <%if(apiClientSearchList.size()>0){
					
					%>
                                          <tr  class="hd" align="center">
                                            <td width="5%">S.N.</td>
                                            <td width="3%">
											<input type="hidden" name="size" value="<%=size%>"/>
                                                <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" /></td>
                                            <td width="8%">Client ID</td>
                                            <td width="15%">Client Name</td>                                        
                                            <td width="15%">Company Name</td>
                                            <td width="12%">IP Address</td>
                                            <td width="10%">Balance</td>
                                             <td width="10%">Login Status</td>
                                            <td width="10%" style="border-right:1px solid #a74312">Block Status</td>
                                          </tr>
                                          <%
											for(int i=0;i<apiClientSearchList.size();i++){				  
				                             HashMap userMap=(HashMap)apiClientSearchList.get(i);
				                           %>
                                          <tr bgcolor="#ffffff" align="center">
                                            <td  style="border-bottom:1px solid #a74312;"><%=i+1%></td>
                                            <td style="border-bottom:1px solid #a74312;">
											<input type="checkbox" value="<%=userMap.get("corporateAgentId")%>" name="userIds" id="a<%=i%>"/></td>
                                            <td style="border-bottom:1px solid #a74312;">
											<a data-tooltip="sticky1<%=i%>" style="cursor:pointer"><%=userMap.get("corporateAgentId")%></a></td>
                                           <td style="border-bottom:1px solid #a74312;" align="center" class="txt">
										   <a href="apiClientProfile.action?param=viewApiClientProfile&apiClientId=<%=userMap.get("corporateAgentId")%>" target="_parent">
										   <%=userMap.get("userName")%></a></td>
                                           <td style="border-bottom:1px solid #a74312;"><%=userMap.get("companyName")%></td>
										 	<td style="border-bottom:1px solid #a74312;"><a href="javascript:forpopup('<%=userMap.get("corporateAgentId")%>')" ><%=userMap.get("ip1")%></a></td>
										 	<td style="border-bottom:1px solid #a74312;"><%=userMap.get("userBalance") %> </td>
										 	<td style="border-bottom:1px solid #a74312;"><%=userMap.get("loginStatus")%></td>
											<%blockStatus=(String )userMap.get("blockStatus");if(blockStatus==null)blockStatus="";%>
										 	<td style="border-right:0px solid #a74312;border-bottom:1px solid #a74312;"><%=blockStatus%></td>
                                          </tr>
                                          
                                          
                                          <%}}%>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                <tr>
									<td align="center">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
 									<tr><td height="7px"></td></tr>
  <tr>
        <td align="right"  valign="middle"  class="logintxt">
        <a href="#" style="text-decoration:none;"><input style="width:90px; float:left;" type="button" value="Edit Service" /> </a>
		
		<a href="javascript:deactivateApiClient();" style="text-decoration:none;"> <input style="width:90px;" type="button" value="Deactivate" /> </a>
		&nbsp;
		
		
		<a href="javascript:activateApiClient();") style="text-decoration:none;"><input style="width:90px;" type="button" value="Activate" /></a>
		&nbsp;&nbsp;
		
		
		
		<a style="cursor:pointer;text-decoration:none;"  href="javascript:blockApiClient();"><input style="width:90px;" type="button" value="Block" /></a>
		&nbsp;
		
		<a style="cursor:pointer;text-decoration:none;"  href="javascript:unblockApiClient();"><input style="width:90px;" type="button" value="Unblock" /></a>	
		</td>  </tr>
		
</table></td>
                                    </tr>
                                  </tbody>
                                </table>
                              </div></td>
                          </tr>
                          
                          <tr>
                            <td valign="top" align="center"></td>
                          </tr>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
	  
	  </form></td>
  </tr>
   <tr>
    <td width="100%" height="220px" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>

<script type="text/javascript">

function forpopup(i)
{	
window.open("apiClientActivation.action?param=getApiApi&corporateAgentId="+i,'popup','width=450,height=230,resizable=no,scrollbars=no');
}


function newPopup(url) {
	popupWindow = window.open(
url,'popUpWindow','height=300,width=400,left=200,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
}
</script>

<script type="text/javascript">

function activateApiClient(){

	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This process will Activate the Api Client");
if (hh==true)
{
 document.apiClientActivationForm.action="apiClientActivation.action?param=activateApiClient";
 document.apiClientActivationForm.submit();
}
else
{
confirm("You have cancelled the submission of form.");
}
}

function checkedAllCheckBoxes(){

var masterCheckBox=document.apiClientActivationForm.masterCheckBox.checked;

var size=document.apiClientActivationForm.size.value;



if(masterCheckBox){

for(var i=0; i<size; i++){


document.getElementById("a"+i).checked=true;
}
}

else{
for(var i=0; i<size; i++){
document.getElementById("a"+i).checked=false;
}
}

}


function deactivateApiClient(){

	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
	var hh=confirm("This process will Deactivate the Api Client");
if (hh==true)
{
 document.apiClientActivationForm.action="apiClientActivation.action?param=deactivateApiClient";
 document.apiClientActivationForm.submit();
}
else
{
confirm("You have cancelled the submission of form.");
}

}


function partiallyActivateApiClient(){
	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
	var hh=confirm("This process will Partially Activate the Api Client.");
if (hh==true)
{
document.apiClientActivationForm.action="apiClientActivation.action?param=partiallyActivateApiClient";
 document.apiClientActivationForm.submit();
}
}

function blockApiClient(){
	
	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This process will Block the Api client.");
if (hh==true)
{
 document.apiClientActivationForm.action="apiClientActivation.action?param=blockApiClient";
 document.apiClientActivationForm.submit();
}
else
{
confirm("You have cancelled the submission of form.");
}
}

function unblockApiClient(){
	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This process will Unblock the Api client.");	

if (hh==true)
{
 document.apiClientActivationForm.action="apiClientActivation.action?param=unblockApiClient";
 document.apiClientActivationForm.submit();
}
else
{
confirm("You have cancelled the submission of form.");
}


}



function deleteApiClient(){
	var checkFound = false;
	for (var counter=0; counter < document.apiClientActivationForm.length; counter++) 
	{
	  	 if ((document.apiClientActivationForm.elements[counter].name == "userIds") && (document.apiClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select Api Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This process will Delete the Api Client.");
if (hh==true)
{
/* document.apiClientActivationForm.action="apiClientActivation.action?param=deleteApiClient";
 document.apiClientActivationForm.submit();*/
}
else
{
confirm("You have cancelled the submission of form.");
}
}
</script>
