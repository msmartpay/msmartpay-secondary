<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<script type="text/JavaScript">

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
/*function request()
{
var a= document.getElementById("req").value;
document.getElementById("res").value =a;
}
*/
function digitonly(input,evt)
{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) 
{
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) 
{
return true;
}
document.getElementById("errField").innerHTML='<p>Enter Number Only.  </p></html>';
document.tbpush.amount.focus();
return false;
}

	function submitRequest()
	{
	var xmlhttp;
	 var type=document.getElementById("requser").value;
	   var id=document.getElementById("reqId").value;
	   if(document.getElementById("reqId").value.replace(/^\s+|\s+$/, '')=="")
	   {
		   alert("Enter ID");
		   document.getElementById("reqId").focus();
		   return false;
	   }
	   var url = "viewUser.action?type="+type+"&id="+id;	  
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
	    document.getElementById("userInfo").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("post",url,true);
	xmlhttp.send();
	}

</script>

</head>
<%
String message="";
String loginType=(String)session.getAttribute("loginType");
if(request.getAttribute("message")!=null)
{
 	message=(String)request.getAttribute("message");
}

%>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
          <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
		   	<tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Trade Balance Transfer (Push)</td>
              </tr>
			 
			  		  <tr><td colspan="4" >
					  
					   <table cellpadding="0" cellspacing="0" width="86%" align="center" class="mydata_tabl" id="border" >
                       <tr><td colspan="100%" height="20"></td></tr>
					   <tr>
                       <td width="20%"></td>
					   <td align="left" width="20%" height="30"><strong>View Requesting User</strong></td> <td width="10%">:</td>
					   <td>
					   <select id="requser"> 
					   <%if(loginType.equalsIgnoreCase("superAdmin")){%>
					   	<option value="portalUser">Portal User</option>
						<option value="MDS">Master Distributor</option>
					   	<option value="DS">Distributor</option>	
						<option value="Agent">Retailer</option>
					   <%}else if(loginType.equalsIgnoreCase("ActivityAdmin")||loginType.equalsIgnoreCase("activityUser")){%>
					  
						
						<option value="portalUser">Portal User</option>
						<option value="MDS">Master Distributor</option>
					   	<option value="DS">Distributor</option>	
						<option value="Agent">Retailer</option>
						
                 	<% }else if(loginType.equalsIgnoreCase("portaladmin")||loginType.equalsIgnoreCase("portaluser")){ %>
						<option value="MDS">Master Distributor</option>
					   	<option value="DS">Distributor</option>	
						<option value="Agent">Retailer</option>
					<% } %>
					</select>
					</td>
					</tr>
						<tr>
                        <td></td>
						<td align="left" height="30"><strong>Enter User ID</strong></td><td>:</td>
					   <td><input id="reqId" type="text"  /><strong style="color: RED;margin-left: 20px;">Complete User ID</strong></td>
					   </tr>
					<tr>
                    <td></td>
                    <td></td>
					<td align="left" height="40"></td><td><input type="button" value="Submit" class="cls_btn"  onclick="submitRequest()"/></td>
					</tr>
					 
                     <tr><td colspan="100%" height="15"></td></tr>
                     
					   </table>
					  
					  </td>
					  </tr>

		  
					  <tr><td valign="top" align="center">
			          <div id="userInfo">
					  <%if(!message.equals("")){ %>
								  <span align="left" height="30"><%=message %></span>
								  <%} %>
			          </div>
			          </td></tr>
					<tr><td colspan="4" height="30"></td></tr>

      	</table>
      	
      	</td>
  		 
  		 </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="74px"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
function submitForm()
{
	var mm=document.tbpush.amount.value
	if(mm<0){			   
document.getElementById("errField").innerHTML='<p>Amount should not be < 1.</p>';
  document.tbpush.amount.focus();
     return false;
	}
	 if(mm<1)	{	   
 document.getElementById("errField").innerHTML='<p>Amount should not be < 1 </p></html>';
document.tbpush.amount.focus();
return false;
	}
 //if(mm>50000){	   
 //document.getElementById("errField").innerHTML='<p>Amount should not be > 50000 </p></html>';	
//document.tbpush.amount.focus();
//return false;
	//}
 
	return true;
}

</script>