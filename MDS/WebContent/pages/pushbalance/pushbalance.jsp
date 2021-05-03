<%@ page import = "java.util.HashMap "%> 

<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


String curdate=(String)formatter.format(todate);

String ip_address=request.getRemoteAddr();
session.setAttribute("ip_address",ip_address);
String message="";
if(session.getAttribute("message")!=null)
	 message=(String)session.getAttribute("message");
else 
	message="";

session.removeAttribute("message");

HashMap mapdata=(HashMap)session.getAttribute("distInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Push Balance</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery.js"></script>

<script type="text/javascript" language="javascript">
$(document).ready(function(){
	
	$(".accordion h3:first").addClass("active");
	$(".accordion h4:not(:first)").hide();

	$(".accordion h3").click(function(){
		$(this).next("h4").slideToggle("fast")
		.siblings("h4:visible").slideUp("fast");
		$(this).toggleClass("active");
		$(this).siblings("h3").removeClass("active");
	});

});
</script>

<style type="text/css">
.accordion {
	width: 700px;
}
.accordion h3 {
	border:#b8cbe1 solid 1px; 
	padding: 7px 15px;
	margin: 0; font-size:12px; font-weight:bold; font-family:"Trebuchet MS"; color:#000000;
	border-bottom: #b8cbe1 1px solid;
	cursor: pointer;
}
.accordion h3:hover {
	background-color: #ffffff;
}
.accordion h3.active {
	background-position: right 5px;
}
.accordion h4 {
	background: #ffffff;
	
}
</style>
<script language="Javascript" src="scripts/Localitysimple.js"></script>
<script language="Javascript" src="scripts/Localitysimpletest.js"></script>



</head>

<body><center>
 <form name="pushbalance"  action="pushBalance.do">
 <input type="hidden" value="updateBalance"  name="param" />	

<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

		  
  <tr>
    <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px">Push  Transfer To Distributor</td>
    </tr>
 	<tr>
	<td  height="32" align="center" valign="middle" colspan="5"><%=message%></td>
	</tr>
<tr>
    <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr>
        <td width="100%" align="center">	
<table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
                    <tr>
					  <td  height="32" align="center" valign="middle" colspan="5"></td>
                       
                     </tr>
					 <tr>
					  <td  height="32" align="center" valign="middle" width="28%">&nbsp;</td>
                        <td  height="32" align="left" valign="middle" width="19%">To</td>
						 <td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="32" align="left" valign="middle" width="29%">Distributor</td>
						  <td  height="32" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
				
                     <tr>
					  <td  height="32" align="center" valign="middle" width="28%">&nbsp;</td>
                        <td  height="32" align="left" valign="middle" width="19%">Distributor Id</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="32" align="left" valign="middle" width="29%"><%=session.getAttribute("completeDistId")%></td>
						  <td  height="32" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
					 <tr>
					  <td  height="32" align="center" valign="middle" width="28%">&nbsp;</td>
                        <td  height="32" align="left" valign="middle" width="19%">Distributor Name</td>
						 <td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="32" align="left" valign="middle" width="29%"><%=mapdata.get("name")%></td>
						  <td  height="32" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
				
					  <tr>
					  <td  height="30" align="center" valign="middle" width="28%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="19%">Amount</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="" align="left" valign="left" width="29%"><input name="amount" type="text" value="" style="width:100px;" class="txt"  onkeypress="return digitonly(this,event);"/></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
					
					  <tr>
					  <td  height="30" align="center" valign="middle" width="28%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="19%">Payment&nbsp;Information</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="" align="left" valign="middle" width="29%"><textarea name="remark" class="Trebuchet_normal" maxlength="300"  id="remarks"  style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:11px; width:160px;" onkeypress="return checkChar(event);"
></textarea></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                      <tr>
        <td colspan="4" height="10"></td>
  </tr>
  <tr><td colspan="3"></td>
	     <td align="left" id="submitbutton" style="display:block">
	   <input name="" type="button" value="Update" style="width:100px;" class="txt"  onclick="return validateFrm()"/>
	   
	   <td align="left" style="visibility:hidden" id="imagececk" ><img src="images/loading2.gif" height="30" width="30"/></td>
                        </tr>
                    </table></td>
                </tr>
  
  
  
</table>

</td>
  </tr>
		  

  
    
</table>
</td></tr>
<tr><td height="30"></td></tr>

  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>
</form>
</center>
</body>
</html>
<script language="javascript">
function convertToCaps(input)
{
 
 input.value=input.value.toUpperCase();

}
function checkChar(e)
{
var k;
document.all ? k = e.keyCode : k = e.which;
return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
}

function validateFrm()
	{
			
			if(submitForm())
			{     
			document.getElementById('submitbutton').style.display="none";
			document.getElementById("imagececk").style.visibility="visible";
			document.pushbalance.submit();
			
			}

	}

function submitForm(){

var amount=document.pushbalance.amount.value;
var remark=document.pushbalance.remark.value;



if(amount==""){
alert("Please enter amount");
return false;
}
if(isNaN(amount)){
alert("Amount must be numeric.");
document.pushbalance.amount.value="";
document.pushbalance.amount.focus();
return false;
}
//if(amount<1000){
//alert("Amount can not be less than one thousand");
//return false;
//}
if(amount<100){
alert("Amount can not be less than 100 ");
return false;
}
//if (amount>50000){
//alert("Amount can not be more than 50000");
//return false;
//}

if(remark==""){
alert("Please enter remark");
return false;
}
 var x=window.confirm("Are you sure, you wish to transfer Rs. "+amount+" to  DS ID <%=session.getAttribute("completeDistId")%>.")
	if (x)
	{
		return true;
	}
	else
	{
		return false;
	}
}


function digitonly(input,evt)

{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) {
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) {
return true;
}

return false;
}

 


</script>
