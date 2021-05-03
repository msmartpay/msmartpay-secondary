<%@ page import = "java.util.ArrayList "%> 

<%
String loginType=(String)session.getAttribute("loginType");
String searchUserType=(String)session.getAttribute("adminUserType");
String searchUserId=(String)session.getAttribute("userId");
ArrayList searchPortalIdList=(ArrayList)session.getAttribute("searchPortalIdList");
String adminPortalId=(String)session.getAttribute("adminUserPortalId");
HashMap idMap=new HashMap();
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>

<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>



</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>

    <td  align="center" width="100%" valign="top">
     <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center"  border="0">
		  <tr><td colspan="4" height="8"></td></tr>
		   <tr>
                <td  width="100%" valign="bottom" height="15" align="left" class="heading_mgs" >
                Internal User
                </td>                
              </tr>
               <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
			  <tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
			  		  

		  
		  <tr><td valign="top" align="center">
		  <form method="post" name="searchForm">
		  <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="mydata_tabl" id="border">
		  		  
                    <tr>
                      <td colspan="4"  align="center"></td>
                    </tr>
					<tr>
                      <td colspan="4"  align="center">&nbsp;</td>
                    </tr>
					  <tr>
                      <td width="19%"></td>
                      <td  align="left"  width="22%"><strong>Select User Type</strong></td>
                      <td align="left" width="14%">:</td>
                      <td align="left"  width="45%">
					  	<input  type="hidden" name="pageNumber" value="1" />
					  	<select  name="userType" onchange="changeStyle()">
                          <option value="select">Select</option>		  
			              
						   <option value="adminUser">Admin User</option>	
						  
                        </select>						
                        </td>
                    </tr>
                    <tr id="tr_1">
                      <td colspan="4" height="15"></td>
                    </tr>
					<%
					if(searchUserType.equalsIgnoreCase("superadmin")){
					%>
                    <tr id="portalIdDiv">
                      <td></td>
                      <td  align="left"><strong>Select Portal</strong></td>
                      <td align="left">:</td>
                      <td align="left">
					 
					 <select name="portalId"  onchange="changeStylePortalType()">
                    <option value="all">All</option>
					<%
					if(searchPortalIdList!=null)
					{
					if(searchPortalIdList.size()>0){
					
					for(int i=0;i<searchPortalIdList.size();i++){
					idMap=(HashMap)searchPortalIdList.get(i);
					%>
				
				  <option value="<%=idMap.get("portalId")%>"><%=idMap.get("portalId")%>&nbsp;&nbsp;&nbsp;&nbsp;<%=idMap.get("companyName")  %></option>
				  <%}}}%>
                </select>
				
				<select name="portalType" id="portal" style="display:none"><option selected="selected">TEP</option><option>REP</option><option>Both</option>
				</select>
				</td>
                    </tr>
					<%}if(loginType.equalsIgnoreCase("activityAdmin")||loginType.equalsIgnoreCase("activityUser")){
						%>
	                    <tr id="portalIdDiv">
	                      <td></td>
	                      <td  align="left"><strong>Select Portal</strong></td>
	                      <td align="left">:</td>
	                      <td align="left">
						  
						 <select name="portalId"   onchange="changeStylePortalType()">
	                    <option value="all">All</option>
						<%
						if(searchPortalIdList!=null)
						{
						if(searchPortalIdList.size()>0){
						
						for(int i=0;i<searchPortalIdList.size();i++){
						idMap=(HashMap)searchPortalIdList.get(i);
						%>
					
					  <option value="<%=idMap.get("portalId")%>"><%=idMap.get("portalId")%>&nbsp;&nbsp;&nbsp;&nbsp;<%=idMap.get("companyName")  %></option>
					  <%}}}%>
	                </select>
					
				
					</td>
	                    </tr>
						<%}
					else{%>
					<input   type="hidden" name="portalId" value="<%=adminPortalId%>" />
					
					
					<%}%>
                    <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
                    <tr id="uid" >
                      <td width="19%"></td>
                      <td  align="left"><strong>User ID</strong></td>
                      <td align="left">:</td>
                      <td align="left"><input type="text"  name="userId" onselectstart="return false"  autocomplete=off/ tooltiptext="Type User ID in this box"></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
                    <tr id="status" style="block">
                      <td width="19%"></td>
                      <td  align="left"  width="22%"><strong>User Status Type</strong></td>
                      <td align="left" width="14%">:</td>
                      <td align="left"  width="45%"><Select type="text"  name="status">
                          <option value="all">All</option>
                          <option value="Activate">Active</option>
                          <option value="Deactive">Deactive</option>
						  <option value="blocked">Blocked</option>
						  <option value="unblocked">Unblocked</option>
                         
                        </Select></td>
                    </tr>
                    
                    <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
 				<tr>
                <td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td align="left">  <input name="Submit2" type="button" value="Submit" class="cls_btn" id="sms" onclick="submitForm()"/></td>
 </tr>
			  
			   <tr>
                      <td colspan="4" height="30"></td>
                    </tr>
 
			  
			  
            </table>
			</form></td></tr>
						  		  <tr><td colspan="4" height="30"></td></tr>

      </table></td>
  </tr></table></td></tr>
  <tr><td colspan="4" height="59"></td></tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
<script language="javascript" type="text/javascript">


function divoo()
{

//alert("value before is>>>>>>>>>>>>>>"+document.searchForm.advanceSearchOption.value);
document.searchForm.advanceSearchOption.value="Y";
//alert("value after is>>>>>>>>>>>>>>"+document.searchForm.advanceSearchOption.value);
document.getElementById("advance").style.display='block';
document.getElementById("adv1").style.display='none';
document.getElementById("adv2").style.display='block';
document.getElementById("sms").style.display='none';

document.getElementById("uid").style.display='none';
document.getElementById("status").style.display='none';

}
function divoo2()
{
//alert("value before is>>>>>>>>>>>>>>"+document.searchForm.advanceSearchOption.value);

document.searchForm.advanceSearchOption.value="N"; 
//alert("value after is>>>>>>>>>>>>>>"+document.searchForm.advanceSearchOption.value);
document.getElementById("advance").style.display='none';
document.getElementById("adv1").style.display='block';
document.getElementById("adv2").style.display='none';
document.getElementById("sms").style.display='block';

document.getElementById("uid").style.display="";
document.getElementById("status").style.display="";
}


function changeStyle(){
var userType=document.searchForm.userType.value;
if(userType=="adminUser")
{
document.searchForm.fatherName.disabled=true;
document.searchForm.motherName.disabled=true;
document.searchForm.panNo.disabled=true;
}
else
{
document.searchForm.fatherName.disabled=false;
document.searchForm.motherName.disabled=false;
document.searchForm.panNo.disabled=false;
}
if(document.searchForm.userType.value=="wlClient" || document.searchForm.userType.value=="apiClient" ){
document.getElementById("portalIdDiv").style.display="none";
document.getElementById("tr_1").style.display="none";
}
else{
document.getElementById("portalIdDiv").style.display="";
document.getElementById("tr_1").style.display="";
document.getElementById("portal").style.display="none";
}
if(document.searchForm.userType.value=="agent")
{
document.getElementById("portal").style.display="";
}

}

function submitForm(){
	var userType=document.searchForm.userType.value;
	 if(userType=="select")
	 {
		 alert("Please Select User Type.");
		 document.searchForm.userType.focus();
		 return false;
	 }
if(userType=='adminUser')
{

document.searchForm.action="adminUserActivation.action?param=searchUserType";
document.searchForm.submit();
}
if(userType=='apiClient')
{

document.searchForm.action="apiClientActivation.action?param=searchUserType";
document.searchForm.submit();
}

}


function changeStylePortalType(){

var portalId=document.searchForm.portalId.value;

if(portalId=="all"){
document.searchForm.portalType.style.display="";

}

else{
document.searchForm.portalType.style.display="none";
}


}
</script>
