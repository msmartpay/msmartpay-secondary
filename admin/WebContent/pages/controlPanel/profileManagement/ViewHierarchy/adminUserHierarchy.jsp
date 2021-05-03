<%@ page import = "java.util.ArrayList "%>
<%@ page import = "java.util.HashMap "%>  
<%
String message=(String) request.getAttribute("message");
if(message==null)message="";
ArrayList portalSearchList=(ArrayList)request.getAttribute("portalSearchList");
int size=0;
if(portalSearchList!=null){
size=portalSearchList.size();
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
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                   <tr>
                                   <td height="40" align="left" valign="middle" class="heading_mgs">Hierarchy</td>
                                    </tr>
								
									<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                                      
                                    <tr>
                                    <td colspan="100%">
                                    <table cclass="mydata_tabl" cellpadding="0" cellspacing="0" width="100%">
                                      <tr>
                                      <td width="30%"></td>
                                      <td  width="10%"><strong>Hierarchy</strong></td><td width="10%" align="center">:</td>
                                      <td width="60%"><select>
                                      <option>Select</option>
                                      <option>Upper Hierarchy</option>
                                       <option>Lower Hierarchy</option>
                                      </select></td>
                                      </tr>
                                      
                                       <tr>
                                       <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr>
                                      <td></td>
                                      <td><strong>Type</strong></td><td align="center">:</td>
                                      <td><select>
                                      <option selected="selected">All</option>
                                      <option>Active</option>
                                       <option>Deactive</option>
                                      
                                      </select></td>
                                      </tr>
                                       <tr>
                                      <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td> <td></td><td align="left">
									  <input class="cls_btn" type="button" value="Submit" onclick="submitForm('<%=id %>','<%=type %>')"/> </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      </table>
                                    
                                    </td>
                                    </tr>
                                      
                                    <tr>
                                      <td valign="top" style="border-left:1px solid #a74312;">
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="form11 tbls">
                                        <tbody style="background:#a74312;">
                                          <%if(size>0){
					
											%>
                                          <tr class="hd" align="center" style="background:#a74312;">
                                            <td width="5%" style="border-right:1px solid #930; color:#FFF;">S.N.</td>
                                            <td width="3%" style="border-top:1px solid #930; color:#FFF;">
                                            <input type="hidden" name="size" value="<%=size%>"/>
                                                <input type="checkbox"  onchange="checkedAllCheckBoxes()" name="masterCheckBox" 
											  /></td>
                                            <td width="10%" align="center" style="border-top:1px solid #930; color:#FFF;">Client ID</td>
                                            <td width="10%" align="center" style="border-top:1px solid #930; color:#FFF;">Admin ID</td>
                                            <td width="30%" align="center" style="border-top:1px solid #930; color:#FFF;"> Admin Name</td>
                                            
                                            <td width="10%" align="center" style="border-top:1px solid #930; color:#FFF;">Balance</td>
                                            
                                            <td width="10%" align="center" style="border-top:1px solid #930; color:#FFF;">Login Status</td>
											<td width="10%" align="center" style="border-right:1px solid #930;border-top:1px solid #930; color:#FFF;">Block Status</td>
                                          </tr>
                                          <%
											for(int i=0;i<size;i++){				  
				                             HashMap userMap=(HashMap)portalSearchList.get(i);
				                          %>
                                          <tr bgcolor="#ffffff" align="center" class="value_reg">
                                            <td style="border-bottom:1px solid #930;" height="25"><%=i+1%></td>
                                            <td style="border-bottom:1px solid #930;"><input type="checkbox" value="<%=userMap.get("portalId")%>" name="userIds" id="a<%=i%>"/></td>
                                            <td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("portalId")%></td>
											<td style="border-bottom:1px solid #930;" align="center">
											<a href="adminUserProfile.action?param=viewAdminUserProfile&adminUserId=<%=userMap.get("userID")%>" target="_parent">
                                            <%=userMap.get("userID")%></a></td>
                                            <td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("name")%></td>
                                          
                                           	<td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("Balance")%></td>
											<td style="border-bottom:1px solid #930;" align="center"><%=userMap.get("loginStatus")%></td>
											<%String blockStatus=(String)userMap.get("BlockStatus");if(blockStatus==null)blockStatus="";%>
											<td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center" ><%=blockStatus%></td>
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
							
							<div id="message" style="display:none">
                <h2>Partially Activate<img src="images/logout11.png" height="20" width="20" onclick="clos();"/></h2>
<br/>
 <table align="center" bgcolor="#fff">
<td align="right">Auto Deactivation Date</td>
<td align="left" valign="middle">
<input name="dateOfActivation" type="text" class="style3" id="dateOfActivation" size="15" readonly="readonly" />
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
