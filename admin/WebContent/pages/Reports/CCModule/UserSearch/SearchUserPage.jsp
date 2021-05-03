<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
ArrayList UserData=(ArrayList)request.getAttribute("UserData");
int size=0;
if(UserData!=null){
size=UserData.size();
}
else{
size=-1;
}
String message="";
message=(String)request.getAttribute("message");
if(message==null)
message="";
String loginType=(String)session.getAttribute("loginType");
System.out.println("loginType is "+loginType);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />



<script language="javascript" type="text/javascript">

function selectService_5()
{
var mainservice_3=document.getElementById('mainService_3').value;

if(mainservice_3=="Secet Search Option")
{

alert("please choose Service Type");
document.getElementById('mainService_3').focus();
return false;
}

else if(mainservice_3=="select_search_by")
{
document.getElementById("r1").style.display="table-row";
document.getElementById("r2").style.display="none";
document.getElementById("r3").style.display="none";
document.getElementById("r4").style.display="none";
document.getElementById('r5').style.display='none';

}

else if(mainservice_3=="registered_mobile_number")
{
document.getElementById("r1").style.display="none";
document.getElementById("r2").style.display="table-row";
document.getElementById("r3").style.display="none";
document.getElementById("r4").style.display="none";
document.getElementById('r5').style.display='none';

}
else if(mainservice_3=="authorized_mobile_number")
{
document.getElementById("r1").style.display="none";
document.getElementById("r2").style.display="none";
document.getElementById("r3").style.display="table-row";
document.getElementById("r4").style.display="none";
document.getElementById('r5').style.display='none';

}
else if(mainservice_3=="registered_email_id")
{
document.getElementById("r1").style.display="none";
document.getElementById("r2").style.display="none";
document.getElementById("r3").style.display="none";
document.getElementById("r4").style.display="table-row";
document.getElementById('r5').style.display='none';

}
else if(mainservice_3=="login_id")
{
document.getElementById("r1").style.display="none";
document.getElementById("r2").style.display="none";
document.getElementById("r3").style.display="none";
document.getElementById("r4").style.display="none";
document.getElementById('r5').style.display='table-row';

}

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
   <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0" class="newbg">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center"  border="0">
		  
		    <tr>
                <td height="40" align="left" valign="middle" class="heading_mgs" >
                User Search
                </td>                
              </tr>
             <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form name="SearchUserForm" method="post">
            
		  <table cellpadding="0" cellspacing="0" width="86%"  border="0"  class="mydata_tabl" id="border">
                 
	            <tr><td style="height:20px;"></td></tr>
					<tr>
					     <td width="30%"></td>
				      	 <td align="left"><strong>Select User</strong></td>
				      	 <td  align="left" width="5%">:</td>
					     <td >
						 <select name="userType" id="getMdsOp">
					       <option value="Select">Select User Type</option>
					       <option value="adminUser">Admin User</option>
					       <option value="mds">Master Distributor</option>
					       <option value="ds">Distributor</option>
						   <option value="agent">Agent</option>
						   
					       </select></td>
					     </tr>
                         
                    
				    <tr>
				      <td ></td>
				      <td   align="left"><strong> Mobile Number</strong></td>
				      <td  align="left">:</td>
				      <td  align="left"><input type="text"  name="mobileNo" /></td>
				      </tr>
					<tr>
				      <td ></td>
				      <td   align="left"><strong> Email ID</strong></td>
				      <td  align="left">:</td>
				      <td  align="left"><input type="text"  name="emailID" /></td>
				      </tr>
                       <tr>
				      <td></td>
				      <td align="left"><strong>User ID</strong></td>
				      <td align="left">:</td>
				      <td align="left"><input type="text"   name="searchUserId" /></td>
				      </tr>
				      
				      <tr>
				      <td ></td>
				      <td   align="left"><strong>Name</strong></td>
				      <td  align="left">:</td>
				      <td  align="left"><input type="text"  name="name" /></td>
				      </tr>
                   
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td align="left" style="padding-left:0px">  <input name="Submit2" type="button" value="Search" class="cls_btn" id="sms" onclick="validate()"/></td>
                </tr>
               <tr><td style="height:20px;"></td></tr> 
			  </table>
              
			  </form>
              
              </td>
			  </tr>
			  
			  
				
			<tr><td colspan="4" height="30"></td></tr>
      </table></td>
  </tr></table></td></tr>
  <tr>
    <td width="100%" align="center" valign="bottom" height="115"></td>
  </tr>
  <tr>
    <td width="100%" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function validate(){
var searchOp=document.SearchUserForm.userType.value;
if(searchOp=="Select"){
alert('Please select Option');
return false;
}
var mobileNo=document.SearchUserForm.mobileNo.value.replace(/^\s+|\s+$/, '');
var emailID=document.SearchUserForm.emailID.value.replace(/^\s+|\s+$/, '');
var searchUserId=document.SearchUserForm.searchUserId.value.replace(/^\s+|\s+$/, '');
var mobilelenght = document.SearchUserForm.mobileNo.value.length;
var name=document.SearchUserForm.name.value.replace(/^\s+|\s+$/, '');
if(mobileNo=="" && emailID=="" && name=="" && searchUserId=="" && mobilelenght < 10)
{
alert('Please Enter any one Vlaue');
return false;
}
document.SearchUserForm.action="CCSearchUser.action?param=getUserDetails"
document.SearchUserForm.submit();

}

</script>