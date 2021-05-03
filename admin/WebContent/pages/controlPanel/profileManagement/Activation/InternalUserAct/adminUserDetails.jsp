<%@ page import = "java.util.ArrayList "%>
<%@ page import = "java.util.HashMap "%>  
<%
String buttonStatus=(String)session.getAttribute("buttonStatus");
String userType=(String)session.getAttribute("userType");
String status=(String)session.getAttribute("status");
String portalId=(String)session.getAttribute("portalId");
int pageNumber=(Integer)session.getAttribute("pageNumber");

int noOfRecords=(Integer)session.getAttribute("noOfRecords");

int pageSize=(Integer)session.getAttribute("pageSize");
String advanceSearchOption=(String)session.getAttribute("advanceSearchOption");
int noOfPages=noOfRecords/pageSize;
int modulus=noOfRecords%pageSize;

if(modulus != 0){
noOfPages=noOfPages+1;
}
ArrayList adminUserSearchList=(ArrayList)session.getAttribute("adminUserSearchList");
int size=0;
if(adminUserSearchList!=null){
size=adminUserSearchList.size();
}else{
size=-1;
}
String Flag="";
String Clientflag="";
String wlBlock="";
String wlStatus="";
String adminBlockStatus="";

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
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/stickytooltip.js"></script>


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
	//var checkFound2=0;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
		{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      			//checkFound2++;
	      	  }
	   	}

 	// validation for single admin user delete
 	
	/*if(checkFound2>1)
   	{
   	   	alert("You cannot Select Multiple Users.");
   	    
   	      document.getElementById("a[i]").focus();
   	    checkFound2=1;
   	    
	    return false;
   	}*/
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	  
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

<div id="mystickytooltip" class="stickytooltip">
<%
if(adminUserSearchList.size()>0){
for(int i=0;i<adminUserSearchList.size();i++){
HashMap userMap=(HashMap)adminUserSearchList.get(i);
%>
<div id="sticky1<%=i%>" class="atip" style="width:288px" align="center">
<table width="288px" align="center" bgcolor="#FFFFFF" class="mydata_tabl">
<!--<tr><td class="left_boldV" >View Client - Profile</td>
</tr>-->
<%
Flag=(String) userMap.get("clientFlag");

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
<tr> <td class="left_boldV" >Company Name</td><td align="left" width="50%"><%=userMap.get("clientCompanyName")%></td></tr>
<tr><td class="left_boldV" >Product Type</td><td><%=Clientflag%></td></tr>
<tr><td class="left_boldV" >Domain</td><td><%=userMap.get("domaiName")%></td></tr>
<tr><td class="left_boldV" >Registration Date</td><td><%=userMap.get("wlRegistrationDate")%></td></tr>
<!-- Code Added By Manoj-->
<tr><td class="left_boldV" >Category</td><td><%=userMap.get("categoryName")%></td></tr>
<tr><td class="left_boldV" >Email ID</td><td><%=userMap.get("agentCellEmailId")%></td></tr>
<tr><td class="left_boldV" >Mobile Number</td><td><%=userMap.get("helpMobileNo")%></td></tr>
</table>
</div>
 <%}}%>
<!--<div class="stickystatus"></div>-->
</div>


<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
	<form name="adminUserActivationForm" method="post">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    
                    
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">

                          <tr>
                            <td valign="top" align="center">
                            <div>
                                <table width="96%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                   <tr>
                                   <td height="40" align="left" valign="middle" class="heading_mgs1">Internal User > Admin User</td>
                                    </tr>
								
									<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                                    
                                      
                                    <tr>
                                      <td valign="top" style="border-left:1px solid #a74312;">
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                          <%if(size>0){%>
                                          <tr align="center" style="background:#a74312;">
                                            <td width="5%" style="border-right:1px solid #930; color:#FFF;">S.N.</td>
                                            <td width="3%" style="border-top:1px solid #930;color:#FFF;">
                                            <input type="hidden" name="size" value="<%=size%>"/>
                                            <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" /></td>
                                            <td width="10%" align="center" style="border-top:1px solid #930;color:#FFF;">Client ID</td>
                                            <td width="10%" align="center" style="border-top:1px solid #930;color:#FFF;">Admin ID</td>
                                            <td width="30%" align="center" style="border-top:1px solid #930;color:#FFF;"> Admin Name</td>
                                            
                                            <td width="10%" align="center" style="border-top:1px solid #930;color:#FFF;">Balance</td>
                                            
                                            <td width="10%" align="center" style="border-top:1px solid #930;color:#FFF;">Login Status</td>
											<td width="10%" align="center" style="border-right:1px solid #930;border-top:1px solid #930;color:#FFF;">Block Status</td>
                                          </tr>
                                          <%
											for(int i=0;i<adminUserSearchList.size();i++){				  
				                             HashMap userMap=(HashMap)adminUserSearchList.get(i);
				                           %>
                                          <tr bgcolor="#ffffff" align="center">
                                            <td style="border-bottom:1px solid #930;" height="25"><%=i+1%></td>
											 <td style="border-bottom:1px solid #930;"><input type="checkbox" value="<%=userMap.get("userId")%>" name="userIds" id="a<%=i%>"/></td>
											<td style="border-bottom:1px solid #930;" align="center">
											<a data-tooltip="sticky1<%=i%>" style="cursor:pointer"><%=userMap.get("portalId")%></a></td>
                                           
											<td style="border-bottom:1px solid #930;" align="center">
                                            <a style="text-decoration:none;" href="adminUserProfile.action?param=viewAdminUserProfile&adminUserId=<%=userMap.get("userId")%>" target="_parent"><%=userMap.get("userId")%></a></td>
											<td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("userName")%></td>
											<td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("userBalance")%></td>
											<td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("loginStatus")%></td>
											<%
											adminBlockStatus=(String)userMap.get("userBlockStatus");
											if(adminBlockStatus==null) {adminBlockStatus="";}else{adminBlockStatus=adminBlockStatus;}%>
											<td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center"><%=adminBlockStatus%></td>
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
        <td align="right" height="25" valign="middle"  class="logintxt">
        <a href="#" style="text-decoration:none;"><input class="cls_btn" style="width:90px; float:left;" type="button" value="Edit Service" /> </a>
        
		<%
		if(!buttonStatus.equalsIgnoreCase("deactive")){%>
		<a href="javascript:deactivateAdminUser();") style="text-decoration:none;"><input class="cls_btn" type="button" value="Deactivate" /></a>
		<%}%>&nbsp;
		<%
		if(!buttonStatus.equalsIgnoreCase("Activate")){%>
		<a href="javascript:activateAdminUser();") style="text-decoration:none;"><input class="cls_btn" type="button" value="Activate" /></a>&nbsp;&nbsp;
        <a style="cursor:pointer; text-decoration:none;"  href="javascript:blockAdminUser();"><input class="cls_btn" type="button" value="Block" /></a>&nbsp;
        <a style="cursor:pointer;text-decoration:none;"  href="javascript:unblockAdminUser();"> <input class="cls_btn"  type="button" value="Unblock" /> </a>
		<%}%>
        </td>  </tr>
		
</table></td>
                                    </tr>
									
									<%
									if(advanceSearchOption.equals("N")){
									%>
                                    <tr>
                                      <td height="26" valign="bottom" align="center" class="hh1" ><a href="#">&lt;&lt;</a>&nbsp;
									  
									 <%  if(noOfPages==1 || pageNumber==1){
									  } 
									
									else{%>
									
									  <a href="adminUserActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber-1%>">Previous</a>
									  <%}%>
									  <%
									  for(int i=1;i<=noOfPages;i++){ 
									  %>
									 &nbsp;
									 
									 <%if(pageNumber==i){%>
									<a  style="color:#930" href="adminUserActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>&nbsp;|&nbsp;
									  <%
									  }
									  
									  else{%>
									  <a href="adminUserActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>
									  <%}}%>
									  &nbsp;<%
									  if(pageNumber==noOfPages || noOfPages==1){} else{
									  %><a href="adminUserActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber+1%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
                                    </tr>
									
									<%} if(advanceSearchOption.equals("Y")){
									
									String panNo=(String)session.getAttribute("panNo");
			                        String dateOfBirth=(String)session.getAttribute("dateOfBirth");
			                        String pincode=(String)session.getAttribute("pincode");
			                        String firstName=(String)session.getAttribute("firstName");
			                        String lastName=(String)session.getAttribute("lastName");
			                        String alternateMobileNo=(String)session.getAttribute("alternateMobileNo");
			                        String state=(String)session.getAttribute("state");
			                        String city=(String)session.getAttribute("city");
			                        String district=(String)session.getAttribute("district");
			                        String fatherName=(String)session.getAttribute("fatherName");
			                        String motherName=(String)session.getAttribute("motherName");
									
									
									
									
									%>
									
									<tr>
                                      <td height="26" valign="bottom" align="center" class="hh1" ><a href="#">&lt;&lt;</a>&nbsp;
									  
									 <%  if(noOfPages==1 || pageNumber==1){
									  } 
									
									else{%>
									
									  <a href="adminUserActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber-1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">>Previous</a>
									  <%}
									  for(int i=1;i<=noOfPages;i++){ 
									  if(pageNumber==i){%>
									  <a style="color:#930" href="adminUserActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>&nbsp;|&nbsp;<%}
									  else{%>
									  <a href="adminUserActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>
									
									  
									  <%
									  }}
									  if(pageNumber==noOfPages || noOfPages==1){} else{
					 %><a href="adminUserActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber+1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
                                    </tr>
									
									<%}%>
                                  </tbody>
                                </table>
                              </div></td>
                          </tr>
                          <tr>
                            <td valign="top" align="center">
							
							<div id="message" style="display:none">
                <h2>Partially Activate<img src="images/logout11.png" height="20" width="20" onclick="clos();"/></h2>
<br/>
 <table align="center" bgcolor="#fff">
<td align="right">Auto Deactivation Date</td>
<td align="left" valign="middle">
<input name="dateOfActivation" type="text" class="style3" id="dateOfActivation" size="15" readonly="readonly"  />
                                        <span class="Trebuchet_b" style="position:fixed;"><img src="images/cal.png" alt="Calendar" width="15" height="17" align="absmiddle" onclick="displayCalendar(document.adminUserActivationForm.dateOfActivation,'yyyy-mm-dd',this)"  /></span></td></tr>
</table>
<p><span><input type="button" value="Submit"  id="button" onclick="partiallyActivateDistributor();"/></span></p>

</div>
</td>
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
                      <td colspan="4" height="41"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
	  
	  </form></td>
  </tr>
   <tr>
    <td width="100%" height="160" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>

<script type="text/javascript">

function activateAdminUser(){
	var checkFound = false;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
	{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This Action will Activate the User.");
if (hh==true)
{
 document.adminUserActivationForm.action="adminUserActivation.action?param=activateAdminUser";
 document.adminUserActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}
}

function checkedAllCheckBoxes(){
var masterCheckBox=document.adminUserActivationForm.masterCheckBox.checked;
var size=document.adminUserActivationForm.size.value;
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
function deactivateAdminUser(){
	
	var checkFound = false;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
		{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      	  }
	   	}
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	  
	   document.getElementById("a[i]").focus();
	   return false;  
	}
	var hh=confirm("This Action will Deactivate the User.");
if (hh==true)
{
 document.adminUserActivationForm.action="adminUserActivation.action?param=deactivateAdminUser";
 document.adminUserActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}

}

function partiallyActivateDistributor(){
	var hh=confirm("This Action will Partially Activate the User.");
if (hh==true)
{
document.adminUserActivationForm.action="adminUserActivation.action?param=partiallyActivateAdminUser";
 document.adminUserActivationForm.submit();
}
}

function blockAdminUser(){
	var checkFound = false;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
		{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;	
	      	  }
	   	}
	
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	 
	     document.getElementById("a[i]").focus();
	   return false;
	 }
var hh=confirm("This Action will Block the User.");
if (hh==true)
{
 document.adminUserActivationForm.action="adminUserActivation.action?param=blockAdminUser";
 document.adminUserActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}

function unblockAdminUser(){
	var checkFound = false;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
		{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      			
	      	  }
	   	}
	
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	 
	     document.getElementById("a[i]").focus();
	   return false;
	 }
var hh=confirm("This Action will Unblock the User.");	

if (hh==true)
{
 document.adminUserActivationForm.action="adminUserActivation.action?param=unblockAdminUser";
 document.adminUserActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}

function deleteAdminUser(){
	var checkFound = false;
	//var checkFound2=0;
	for (var counter=0; counter < document.adminUserActivationForm.length; counter++) 
		{
	  	 if ((document.adminUserActivationForm.elements[counter].name == "userIds") && (document.adminUserActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      			//checkFound2++;
	      	  }
	   	}

   	// validation for single admin user delete
   	
/*	if(checkFound2>1)
   	{
   	   	alert("You cannot Select Multiple Users.");
   	    
   	      document.getElementById("a[i]").focus();
   	    checkFound2=1;
   	    
	    return false;
   	}*/
	if (checkFound != true) 
	{
	   alert ("Please Select the User.");
	  
	     document.getElementById("a[i]").focus();
	   return false;
	   
	 }
	var hh=confirm("This Action will Delete the User.");
if (hh==true)
{
 /*document.adminUserActivationForm.action="adminUserActivation.action?param=deleteAdminUser";
 document.adminUserActivationForm.submit();*/
}
else
{
confirm("You have Cancelled the Process.");
}


}
var message="No right-click allowed";

function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")
</script>
