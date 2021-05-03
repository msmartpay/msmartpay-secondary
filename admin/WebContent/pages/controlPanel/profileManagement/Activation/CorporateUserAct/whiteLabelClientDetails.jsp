<%@ page import = "java.util.ArrayList "%>
<%@ page import = "java.util.HashMap "%>  
<%

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
ArrayList whiteLabelClientSearchList=(ArrayList)session.getAttribute("whiteLabelClientSearchList");
int size=whiteLabelClientSearchList.size();
String message=(String)request.getAttribute("message");
if(message==null)message="";
String Flag="";
String Clientflag="";
String wlBlock="";
String wlStatus="";
String agentName="";
String clientBlockStatus="";
int count=0;
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
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
		{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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

if(whiteLabelClientSearchList.size()>0){
for(int i=0;i<whiteLabelClientSearchList.size();i++){
HashMap userMap=(HashMap)whiteLabelClientSearchList.get(i);
%>
<div id="sticky1<%=i%>" class="atip" style="width:380px" align="center">
<table width="380px" align="center" class="mydata_tabl" bgcolor="#FFFFFF">
<!--<tr><td height="23" colspan="2" align="left">View Creater - Profile</td>
</tr>-->
<tr><td align="left" width="40%"><strong>Company Name</strong></td><td align="left" width="60%"><%=userMap.get("companyName")%></td></tr>
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
<tr><td align="left"><strong>Product Type</strong></td><td align="left"><%=Clientflag%></td></tr>
<tr><td align="left"><strong>Domain</strong></td><td><%=userMap.get("domainName")%></td></tr>
<tr><td align="left"><strong>Registration Date</strong></td><td><%=userMap.get("dateOfRegistrtion")%></td></tr>
<tr><td align="left"><strong>Category</strong></td><td><%=userMap.get("categoryName")%></td></tr>
<tr><td align="left"><strong>Email ID</strong></td><td><%=userMap.get("emailID")%></td></tr>
<tr><td align="left"><strong>Mobile Number</strong></td><td><%=userMap.get("mobileNo")%></td></tr>
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
	<form name="whiteLabelClientActivationForm" method="post">
     <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                 
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="form11">

                          <tr>
                            <td valign="top" align="center">
                            <div id="#">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                     <td height="40" align="left" valign="middle" class="heading_mgs">
                                     Corporate User
                                     </td>
                                    </tr>
								
                                
									<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									
                                    <tr>
                                      <td valign="top">
									  <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                          <%if(whiteLabelClientSearchList.size()>0){%>
                                          <tr  class="hd" align="center" style="background:#a74312;">
                                            <td width="5%" style="border:1px solid #930; color:#FFF;">S.N.</td>
                                            <td width="3%" style="border:1px solid #930;color:#FFF;">
                                            <input type="hidden" name="size" value="<%=size%>"/>
                                                <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" 
											  /></td>
                                            <td width="7%" align="center" style="border:1px solid #930;color:#FFF;">Client ID</td>
                                            <td width="25%" align="center" style="border:1px solid #930;color:#FFF;">Client Domain</td>                                  
                                         
                                            <td width="25%" align="center" style="border:1px solid #930;color:#FFF;">Company Name</td>
                                            <td width="10%" align="center" style="border:1px solid #930;color:#FFF;">Help Mobile No </td>
                                            <td width="10%" align="center" style="border:1px solid #930;color:#FFF;">Login Status</td>
											<td width="10%" align="center" style="border-right:1px solid #930;border:1px solid #930;color:#FFF;">Block Status</td>
                                          </tr>
                                          <%
											for(int i=0;i<whiteLabelClientSearchList.size();i++){				  
				                             HashMap userMap=(HashMap)whiteLabelClientSearchList.get(i);
				                           %>
				                         
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td style="border-bottom:1px solid #930;" height="25"><%=i+1%></td>
                                            <td style="border-bottom:1px solid #930;">
                                            <input type="checkbox" value="<%=userMap.get("clientId")%>" name="userIds" id="a<%=i%>"/>
                                            </td>
                                           
                                            <td style="border-bottom:1px solid #930;" align="center">
                                            <a href="wlProfile.action?param=viewWlPrfile&clientId=<%=userMap.get("clientId")%>" target="_parent"><%=userMap.get("clientId")%></a></td>
                                           
                                            
                                           
                                            
                                            <td style="border-bottom:1px solid #930;" align="center"><a data-tooltip="sticky1<%=i%>" style="cursor:pointer"><%=userMap.get("domainName")%></a></td>
											
											 <td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("companyName")%></td>
                                            <td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("mobileNo")%></td>  
                                            <td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("status")%></td>
											<%
											clientBlockStatus=(String)userMap.get("blockStatus");
											if(clientBlockStatus==null) {clientBlockStatus="";}else{clientBlockStatus=clientBlockStatus;}%>
                                            <td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center"><%=clientBlockStatus%></td>
                                          </tr>
                                          <%}}%>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                    <tr><td height="7px"></td></tr>
                                    <tr>
									<td align="center"><table width="86%" border="0" cellspacing="0" cellpadding="0">
 
  <tr>
        <td align="right" height="25" valign="middle" class="logintxt">
         <a href="#" style="text-decoration:none;"><input class="cls_btn" style="width:90px; float:left;" type="button" value="Edit Service" /> </a>
		<%
		if(!buttonStatus.equalsIgnoreCase("deactive")){%>
		<a href="javascript:deactivateWhiteLabelClient();") style="text-decoration:none;"><input class="cls_btn" type="button" value="Deactivate" /></a>
		<%}%>&nbsp;
		<%
		if(!buttonStatus.equalsIgnoreCase("Activate")){%>
		<a href="javascript:activateWhiteLabelClient();") style="text-decoration:none;"><input class="cls_btn" type="button" value="Activate" /></a>
		<%}%>&nbsp;&nbsp;
		
		<%if(!buttonStatus.equalsIgnoreCase("PartiallyActive")){%>
		<a style="cursor:pointer"   onclick="clk();"><!--Partially Activate--></a><%}%>
		
		<%if(!buttonStatus.equalsIgnoreCase("blocked")){%>
		<a style="cursor:pointer; text-decoration:none;"  href="javascript:blockWhiteLabelClient();" ><input class="cls_btn" type="button" value="Block" /></a>
		<%}%>&nbsp;
		<%if(!buttonStatus.equalsIgnoreCase("unblocked")){%>
		<a style="cursor:pointer;text-decoration:none;"  href="javascript:unblockWhiteLabelClient();"><input class="cls_btn"  type="button" value="Unblock" /></a>
		<%}%>
		</td>   </tr>
		
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
									
									  <a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber-1%>">Previous</a>
									  <%}%>
									  <%
									  for(int i=1;i<=noOfPages;i++){ 
									  %>
									 &nbsp;
									 
									 <%if(pageNumber==i){%>
									<a  style="color:#930" href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>&nbsp;|&nbsp;
									  <%
									  }
									  
									  else{%>
									  <a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>
									  <%}}%>
									  &nbsp;<%
									  if(pageNumber==noOfPages || noOfPages==1){} else{
									  %><a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber+1%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
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
									
									  <a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber-1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">>Previous</a>
									  <%}
									  for(int i=1;i<=noOfPages;i++){ 
									  if(pageNumber==i){%>
									  <a style="color:#930" href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>&nbsp;|&nbsp;<%}
									  else{%>
									  <a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>
									
									  
									  <%
									  }}
									  if(pageNumber==noOfPages || noOfPages==1){} else{
					 %><a href="whiteLabelClientActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber+1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
                                    </tr>
									
									<%}%>
                                  </tbody>
                                </table>
                              </div></td>
                          </tr>
                          <tr>
                            <td valign="top" align="center">
							
							<!--start of popup-->
							<div id="message" style="display:none">
                <h2>Partially Activate<img src="images/logout11.png" height="20" width="20" onclick="clos();"/></h2>
<br/>
 <table align="center" bgcolor="#fff">
<td align="right">Auto Deactivation Date</td>
<td align="left" valign="middle">
<input name="dateOfActivation" type="text" class="style3" id="dateOfActivation" size="15" readonly="readonly" />
                                        <span class="Trebuchet_b" style="position:fixed;"><img src="images/cal.png" alt="Calendar" width="15" height="17" align="absmiddle" onclick="displayCalendar(document.whiteLabelClientActivationForm.dateOfActivation,'yyyy-mm-dd',this)"  /></span></td></tr>
</table>
<p><span><input type="button" value="Submit"  id="button" onclick="partiallyActivateWhiteLabelClient();"/></span></p>

</div>
<!--end of popup-->
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

<script>

function activateWhiteLabelClient(){

	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
		 {
	      			checkFound = true;
	      }
	 }
	if (checkFound != true) 
	{
	   alert ("Please select White Label Client.");
	   document.getElementById("a[i]").focus();
	   return false;
	}
var hh=confirm("This Action will Activate the User.");
if (hh==true)
{
 document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=activateWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}




}

function checkedAllCheckBoxes(){

var masterCheckBox=document.whiteLabelClientActivationForm.masterCheckBox.checked;

var size=document.whiteLabelClientActivationForm.size.value;



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


function deactivateWhiteLabelClient(){
	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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
 document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=deactivateWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}

}

function partiallyActivateWhiteLabelClient(){

	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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
var hh=confirm("This Action will Partially Activate the User.");

if (hh==true)
{
document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=partiallyActivateWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}

}

function blockWhiteLabelClient(){
	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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
 document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=blockWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}

function unblockWhiteLabelClient(){
	
	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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
 document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=unblockWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}


function deleteWhiteLabelClient(){
	
	var checkFound = false;
	for (var counter=0; counter < document.whiteLabelClientActivationForm.length; counter++) 
	{
	  	 if ((document.whiteLabelClientActivationForm.elements[counter].name == "userIds") && (document.whiteLabelClientActivationForm.elements[counter].checked == true))
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
var hh=confirm("This Action will Delete the User.");
if (hh==true)
{
 document.whiteLabelClientActivationForm.action="whiteLabelClientActivation.action?param=deleteWhiteLabelClient";
 document.whiteLabelClientActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}
</script>
