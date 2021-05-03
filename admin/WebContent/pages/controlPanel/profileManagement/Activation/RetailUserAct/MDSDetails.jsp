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




String message=(String) request.getAttribute("message");
if(message==null)message="";
ArrayList mdsSearchList=(ArrayList)session.getAttribute("mdsSearchList");
int size=mdsSearchList.size();
String Flag="";
String Clientflag="";
String wlBlock="";
String wlStatus="";
String MdName="";
String mdBlockStatus="";
int count=0;
String editServiceUserID="";
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
//	var checkFound2=0;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      			
	      	  }
	   	}
 	// validation for single admin user delete
 	
/*	if(checkFound2>1)
   	{
   	   	alert("You can not select more than one Master Distributor.");
   	    
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

function forpopup(type,id)
{
	window.open("viewUser.action?type="+type+"&id="+id+"&viewType=Chield",'popup','width=700,height=550,left=400,top=50,screenX=300,screenY=100,resizable=no,scrollbars=no');

}
</script>

</head>
<body>

<div id="mystickytooltip" class="stickytooltip">
<%

					if(mdsSearchList.size()>0){
					
					for(int i=0;i<mdsSearchList.size();i++){
				  
				 HashMap mdsMap=(HashMap)mdsSearchList.get(i);
%>
<div id="sticky1<%=i%>" class="atip" style="width:280px" align="center">
<table width="280px" align="center" class="mydata_tabl" bgcolor="#FFFFFF">


<% wlStatus=(String)mdsMap.get("WLstatus");
if(wlStatus==null){
wlStatus="";
}
else{
wlStatus=wlStatus;
}
%>
<% wlBlock=(String)mdsMap.get("wlBlockStatus");
if(wlBlock==null){
wlBlock="";
}
else{
wlBlock=wlBlock;
}
%>
<%
Flag=(String) mdsMap.get("clientFlag");

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
<tr> <td class="left_boldV" >Company Name</td><td align="left" width="50%"><%=mdsMap.get("clientCompanyName")%></td></tr>
<tr><td class="left_boldV" >Product Type</td><td><%=Clientflag%></td></tr>
<tr><td class="left_boldV" >Domain</td><td><%=mdsMap.get("domaiName")%></td></tr>
<tr><td class="left_boldV" >Registration Date</td><td><%=mdsMap.get("wlRegistrationDate")%></td></tr>
<tr><td class="left_boldV" >Category</td><td><%=mdsMap.get("categoryName")%></td></tr>
<!--Code added By Manoj -->
<tr><td class="left_boldV" >Email ID</td><td><%=mdsMap.get("agentCellEmailId")%></td></tr>

<tr><td class="left_boldV" >Mobile Number</td><td><%=mdsMap.get("helpMobileNo")%></td></tr>


<!--<tr><td class="left_boldV" >Client Block Status</td><td><--%=wlBlock%></td></tr>-->
</table>
</div>
 <%}}%>
<!--<div class="stickystatus"></div>-->
</div>


<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
	<form name="mdsActivationForm" method="post">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                 
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">

                          <tr>
                            <td valign="top" align="center"><div id="#" >
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td height="40" align="left" valign="middle" class="heading_mgs1" >Retail User > Master Distributor</td>
                                    </tr>
								
									<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                                    <tr>
                                      <td valign="top" >
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                          <%if(mdsSearchList.size()>0){%>
                                          <tr  class="hd" align="center" style="background:#a74312;">
                                          
                                            <td width="5%"   style="border-bottom:border:1px solid #930; color:#FFF;">S.N.</td>
                                            
                                            <td width="3%" style="border-bottom:border:1px solid #930;color:#FFF;">
                                            <input type="hidden" name="size" value="<%=size%>"/>
                                             <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" /></td>
                                             
                                             <td width="7%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">Client ID</td>
                                            <td width="7%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">Portal ID</td>
                                            <td width="21%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">Portal Name</td>
                                            <td width="8%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;"> MDS ID</td>
                                            <td width="21%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">MDS Name</td>
                                            <td width="8%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">Balance</td>
                                            <td width="10%" align="center" style="border-bottom:border:1px solid #930;color:#FFF;">Login Status</td>
											<td width="10%" align="center" style="border-right:1px solid #930;border-bottom:border:1px solid #930;color:#FFF;">Block Status</td>
                                          	<td width="8%" align="center" style="border-right:1px solid #930;border-bottom:border:1px solid #930;color:#FFF">Pay</td>
                                          </tr>
										  <%
										  if(pageNumber==1){
											 count =0;
											 }else if(pageNumber==2){
											 count=100;
											 }else{
											 count=(pageNumber-1)*100;
											 }
										  %>
                                          <%
											for(int i=0;i<mdsSearchList.size();i++){				  
				                             HashMap mdsMap=(HashMap)mdsSearchList.get(i);
											count=count+1;
											 editServiceUserID=(String)mdsMap.get("mdId");
				                           %>
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td  style="border-bottom:1px solid #930;"><%=count%></td>
                                            <td style="border-bottom:1px solid #930;"><input type="checkbox" value="<%=mdsMap.get("mdId")%>" name="mdIds" id="a<%=i%>"/></td>
                                            <td style="border-bottom:1px solid #930;"><a data-tooltip="sticky1<%=i%>" style="cursor:pointer"><%=mdsMap.get("portalId")%></a></td>
                                            <td style="border-bottom:1px solid #930;" align="center" ><%=mdsMap.get("mdPortalID")%></td>
                                            <td style="border-bottom:1px solid #930;"><%=mdsMap.get("clientCompanyName")%></td>
                                           
                                            
                                           
                                            <td style="border-bottom:1px solid #930;" align="center" ><a href="mdsProfile.action?param=viewMdsProfile&mdId=<%=mdsMap.get("mdId")%>" target="_parent"><%=mdsMap.get("mdId")%></a></td>                                            
                                            <%
											MdName=(String)mdsMap.get("mdsName");
											if(MdName==null) {MdName="";}else{MdName=MdName;}%>
											<td style="border-bottom:1px solid #930;" align="center" ><%=MdName%></td>
											<td style="border-bottom:1px solid #930;" align="center" ><%=mdsMap.get("mdsBalance")%></td>
                                            <td style="border-bottom:1px solid #930;" align="center" ><%=mdsMap.get("loginStatus")%></td>
                                            <%
											mdBlockStatus=(String)mdsMap.get("mdsBlockStatus");
											if(mdBlockStatus==null) {mdBlockStatus="";}else{mdBlockStatus=mdBlockStatus;}%>
											<td style="border-bottom:1px solid #930;" align="center" ><%=mdBlockStatus%></td>
											<td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center">
											<input type="button" onclick="forpopup('MDS','<%=mdsMap.get("mdId") %>')" value="Pay Now" />
											</td>
											
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
        <a href="javascript:submitGetPage();" style="text-decoration:none;">
		<input style="width:90px; float:left;" class="cls_btn" type="button" value="Edit Service" /> </a>
		<%
		if(!buttonStatus.equalsIgnoreCase("deactive")){%>
		<a href="javascript:deactivateDistributor();") style="text-decoration:none;"> <input class="cls_btn" type="button" value="Deactivate" /> </a>
		<%}%>&nbsp;
		<%
		if(!buttonStatus.equalsIgnoreCase("Activate")){%>
		<a href="javascript:activateDistributor();") style="text-decoration:none;"><input class="cls_btn" type="button" value="Activate" /></a>
		<%}%>&nbsp;&nbsp;
		
		<%if(!buttonStatus.equalsIgnoreCase("PartiallyActive")){%>
		<a style="cursor:pointer; text-decoration:none;"   onclick="clk();"><!--Partially Activate--></a><%}%>
		
		<%if(!buttonStatus.equalsIgnoreCase("blocked")){%>
		<a style="cursor:pointer;text-decoration:none;"  href="javascript:blockDistributor();"><input class="cls_btn" type="button" value="Block" /></a>
		<%}%>&nbsp;
		<%if(!buttonStatus.equalsIgnoreCase("unblocked")){%>
		<a style="cursor:pointer;text-decoration:none;"  href="javascript:unblockDistributor();"><input class="cls_btn" type="button" value="Unblock" /></a>
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
									
									else{ %>
									
									  <a href="mdsActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber-1%>">Previous</a>
									  <%}%>
									  <%
									  for(int i=1;i<=noOfPages;i++){ 
									  %>
									 &nbsp;
									 
									 <%if(pageNumber==i){%>
									<a  style="color:#930" href="mdsActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>&nbsp;|&nbsp;
									  <%
									  }
									  
									  else{%>
									  <a href="mdsActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=i%>"><%=i%></a>
									  <%}}%>
									  &nbsp;<%
									  if(pageNumber==noOfPages || noOfPages==1){} else{
									  %><a href="mdsActivation.action?param=searchUserType&portalId=<%=portalId%>&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&status=<%=status%>&pageNumber=<%=pageNumber+1%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
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
									
									  <a href="mdsActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber-1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">>Previous</a>
									  <%}
									  for(int i=1;i<=noOfPages;i++){ 
									  if(pageNumber==i){%>
									  <a style="color:#930" href="mdsActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>&nbsp;|&nbsp;<%}
									  else{%>
									  <a href="mdsActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=i%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>"><%=i%></a>
									
									  
									  <%
									  }}
									  if(pageNumber==noOfPages || noOfPages==1){} else{
					 %><a href="mdsActivation.action?param=searchUserType&userType=<%=userType%>&advanceSearchOption=<%=advanceSearchOption%>&pincode=<%=pincode%>&pageNumber=<%=pageNumber+1%>&firstName=<%=firstName%>&lastName=<%=lastName%>&alternateMobileNo=<%=alternateMobileNo%>&state=<%=state%>&district=<%=district%>&city=<%=city%>&fatherName=<%=fatherName%>&motherName=<%=motherName%>">Next</a><%}%>&nbsp;<a href="#">&gt;&gt;</a></td>
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
<input name="dateOfActivation" type="text" class="style3" id="dateOfActivation" size="15" readonly="readonly"  value=""/>
                                        <span class="Trebuchet_b" style="position:fixed;"><img src="images/cal.png" alt="Calendar" width="15" height="17" align="absmiddle" onclick="displayCalendar(document.mdsActivationForm.dateOfActivation,'yyyy-mm-dd',this)"  /></span></td></tr>
</table>
<p><span><input type="button" value="Submit"  id="button" onclick="partiallyActivateDistributor();"/></span></p>

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
    <td width="100%" height="160" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>

<script  type="text/javascript">

function submitGetPage()
{
	var checkFound = false;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
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
	else
	{
		document.mdsActivationForm.action="editServiceMgt.action?param=getEditServicePage&userType=mds";
 		document.mdsActivationForm.submit();
	}

}




function activateDistributor(){

	var checkFound = false;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
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
 document.mdsActivationForm.action="mdsActivation.action?param=activateMds";
 document.mdsActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}




}

function checkedAllCheckBoxes(){

var masterCheckBox=document.mdsActivationForm.masterCheckBox.checked;

var size=document.mdsActivationForm.size.value;



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


function deactivateDistributor(){

	var checkFound = false;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
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
 document.mdsActivationForm.action="mdsActivation.action?param=deactivateMds";
 document.mdsActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}

}

function partiallyActivateDistributor(){


	var hh=confirm("This process will Partially Activate the Master Distributor.");
if (hh==true)
{
document.mdsActivationForm.action="mdsActivation.action?param=partiallyActivateMds";
 document.mdsActivationForm.submit();
}

}

function blockDistributor(){
	var checkFound = false;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
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
 document.mdsActivationForm.action="mdsActivation.action?param=blockMds";
 document.mdsActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}

function unblockDistributor(){
	var checkFound = false;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
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
 document.mdsActivationForm.action="mdsActivation.action?param=unblockMds";
 document.mdsActivationForm.submit();
}
else
{
confirm("You have Cancelled the Process.");
}


}

function deleteDistributor(){
	
	var checkFound = false;
	//var checkFound2=0;
	for (var counter=0; counter < document.mdsActivationForm.length; counter++) 
		{
	  	 if ((document.mdsActivationForm.elements[counter].name == "mdIds") && (document.mdsActivationForm.elements[counter].checked == true))
		  	  {
	      			checkFound = true;
	      			//checkFound2++;
	      	  }
	   	}
 	// validation for single admin user delete
 	/*
	if(checkFound2>1)
   	{
   	   	alert("You can not select more than one Master Distributor.");
   	    
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
 /*document.mdsActivationForm.action="mdsActivation.action?param=deleteMds";
 document.mdsActivationForm.submit();*/
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

