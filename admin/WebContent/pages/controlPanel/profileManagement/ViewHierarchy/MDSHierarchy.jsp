<%@ page import = "java.util.ArrayList "%>
<%@ page import = "java.util.HashMap "%>  
<%
String message=(String) request.getAttribute("message");
if(message==null)message="";
ArrayList mdsSearchList=(ArrayList)request.getAttribute("mdsSearchList");
int size=0;
if(mdsSearchList!=null){
size=mdsSearchList.size();
}else{
size=-1;
}
String Flag="";
String Clientflag="";
String wlBlock="";
String wlStatus="";
String MdName="";
String mdBlockStatus="";
String id=request.getAttribute("id").toString();
String type=request.getAttribute("type").toString();

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
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

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  
  <tr>
    <td  align="center" width="100%" valign="top">
	
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
                            <td valign="top" align="center"><div id="#" >
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td height="40" align="left" valign="middle" class="heading_mgs" >Hierarchy</td>
                                    </tr>
									<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
								
                                      
                                      <tr>
                                    <td colspan="100%">
									<form name="selectForm" method="post">
                                    <table class="mydata_tabl" cellpadding="0" cellspacing="0" width="100%">
									
                                      <tr>
                                      <td width="30%"></td>
                                      <td  width="10%"><strong>Hierarchy</strong></td><td width="10%" align="center">:</td>
                                      <td width="60%"><select name="hierarchy">
                                      <option value="select">Select</option>
                                      <option value="upper">Upper Hierarchy</option>
                                       <option value="lower">Lower Hierarchy</option>
                                      </select></td>
                                      </tr>
                                      
                                       <tr>
                                       <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr>
                                      <td></td>
                                      <td><strong>Type</strong></td><td align="center">:</td>
                                      <td><select name="status">
                                      <option selected="selected" value="all">All</option>
                                      <option value="Activate">Active</option>
                                       <option value="Deactive">Deactive</option>
                                      
                                      </select></td>
                                      </tr>
                                       <tr>
                                      <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td> <td></td><td align="left">
									  <input style="width:90px; margin-left:133px;" type="button" value="Submit" onclick="submitForm('<%=id %>','<%=type %>')"/> </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      </table>
									 </form>
                                    
                                    </td>
                                    </tr>
                                      
                                    <tr>
                                      <td valign="top" >
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                          <%if(size>0){%>
                                           <tr  class="hd" align="center" style="background:#a74312;">
                                          
                                            <td width="5%"   style=":border:1px solid #930; color:#FFF;">S.N.</td>
                                            
                                            <td width="3%" style="border:1px solid #930;">
                                            <input type="hidden" name="size" value="<%=size%>"/>
                                             <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" /></td>
                                             
                                             <td width="7%" align="center" style="border:1px solid #930;color:#FFF;">Client ID</td>
                                            <td width="7%" align="center" style="border:1px solid #930;color:#FFF;">Portal ID</td>
                                            <td width="21%" align="center" style="border:1px solid #930;color:#FFF;">Portal Name</td>
                                            <td width="8%" align="center" style="border:1px solid #930;color:#FFF;"> MDS ID</td>
                                            <td width="21%" align="center" style="border:1px solid #930;color:#FFF;">MDS Name</td>
                                            <td width="8%" align="center" style="border:1px solid #930;color:#FFF;">Balance</td>
                                            <td width="10%" align="center" style="border:1px solid #930;color:#FFF;">Login Status</td>
											<td width="10%" align="center" style="border-right:1px solid #930;border-bottom:border:1px solid #930;color:#FFF;">Block Status</td>
                                          </tr>
                                         <%
											for(int i=0;i<mdsSearchList.size();i++){				  
				                             HashMap mdsMap=(HashMap)mdsSearchList.get(i);
				                             
				                           %>
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td  style="border-bottom:1px solid #930;"><%=i+1%></td>
                                            <td style="border-bottom:1px solid #930;"><input type="checkbox" value="<%=mdsMap.get("mdId")%>" name="mdIds" id="a<%=i%>"/></td>
                                            
                                            <td style="border-bottom:1px solid #930;"><%=mdsMap.get("portalId")%></td>
                                            
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

											
                                          </tr>
                                          <%}}%>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                    <tr>
									<td align="center">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
 									<tr><td height="7px"></td></tr>
  
		
</table></td>
                                    </tr>
									
									
                                  </tbody>
                                </table>
                              </div></td>
                          </tr>
                          <tr>
                            <td valign="top" align="center">
							
							<!--start of popup-->
							
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
	  
	  </td>
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

function submitForm(id,type){
var hierarchy=document.selectForm.hierarchy.value;
if(hierarchy=="select")
{
alert('Please select');
return false;
}
document.selectForm.action="viewHierarchy.action?type="+type+"&userId="+id;
document.selectForm.submit();

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

