<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String ipAddress=(String)request.getRemoteAddr();
System.out.println("ipAddress is "+ipAddress);
String message=(String)request.getAttribute("message");
if(message==null)message="";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<link type="text/css" rel="stylesheet" href="css/stickytooltip.css" media="screen" ></link>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<script type="text/javascript" src="scripts/digitonly.js"></script>


</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
   <td  align="center" width="100%" valign="top"> <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" style="margin-left:100px" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs">Client Wallet Updation</td>                
              </tr>
              <tr>
                 <td  width="100%" valign="bottom" height="18" align="center" style="padding-left:105px" class="style111" ></td>
              </tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="ClientAmountForm">
            
		  <table cellpadding="0" cellspacing="0" width="83%" align="left" border="0"  
class="mydata_tabl"id="border">
                 <tr>
                   <td colspan="4" height="15" class="dyn_mgs"><%=message%></td>
                 </tr>
	            <tr>
	              <td colspan="4" height="15"></td>
	              </tr>
				    <tr>
				      <td colspan="4" height="15"></td>
				      </tr>
				    <tr>
				      <td width="25%" height="30"></td>
				      <td width="14%" align="left">Client Name</td>
				      <td width="8%" align="left">:</td>
				      <td width="53%" align="left"><span class="tabl2">
				        <input type="text" style="width:244px" class="tabl2" name="clientName"/>
						<input type="hidden" name="ipAdd" value="<%=ipAddress%>" />
				      </span></td>
				      </tr>
                      
                      <tr>
				      <td width="25%" height="30"></td>
				      <td width="14%" align="left">Client URL</td>
				      <td width="8%" align="left">:</td>
				      <td width="53%" align="left"><span class="tabl2">
				        <input type="text" style="width:244px" class="tabl2" name="clientUrl"/>
				      </span></td>
				      </tr>
                      
                       <tr>
				      <td width="25%" height="30"></td>
				      <td width="14%" align="left">Amount</td>
				      <td width="8%" align="left">:</td>
				      <td width="53%" align="left"><span class="tabl2">
				        <input type="text" style="width:244px" class="tabl2" name="amount" maxlength="7" onkeypress="return digitonly(this,event)"/>
				      </span></td>
				      </tr>
                      <tr>
                      <td colspan="4" height="20"></td>
                    </tr>
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td align="left" style="padding-left:0px"><input name="dumy" type="submit" value="Submit" 
class="cls_btn" id="demo" onclick="updateAmount()"/></td>
                </tr>
                 <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
                    
                    <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
                    
                    <tr>
                      <td colspan="4" height="15"></td>
                    </tr>
			  </table>
              
			  </form>
              
              </td></tr>
			<tr><td colspan="4" height="30"></td></tr>
      </table></td>
  </tr></table></td></tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function updateAmount(){
var clientName=document.ClientAmountForm.clientName.value;
var clientURl=document.ClientAmountForm.clientUrl.value;
var amount=document.ClientAmountForm.amount.value;
if(clientName==""){
alert('Please eneter the client name');
return false;

}
if(clientURl==""){
alert('Please eneter the client URl');
return false;

}
if(amount==""){
alert('Please eneter the amount');
return false;

}
if(amount<1)
{
		
		alert('Amount must be > 1');
		return false;
}
if(amount>2500000)
{
		alert('Amount must be < 25,00000  ');
		return false;
}
document.ClientAmountForm.action="cwpActivity.action?param=updateAmount";
document.ClientAmountForm.submit();
}
</script>