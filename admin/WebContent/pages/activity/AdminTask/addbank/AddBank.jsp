<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.text.*" %>

<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	  
	  $("#datepicker").datepicker({
		  
		  changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2013',
            numberOfMonths: 1,

		  });

  });
  </script>

</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center">   <%@include file="/header.jsp"%> </td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td  valign="middle" height="40" align="left" class="heading_mgs">Add Bank Details</td>
              </tr>
			
              <tr>
			  
                <td  align="center">
                <div class="accordion" style="padding:20px;" id="border">
                <form name="addbank" method="post" action="addBank.action">
               
				  <table width="100%"  cellspacing="2" cellpadding="0" align="center" >
				  	<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
                    <tr>
                      <td><h3 align="center" class="hd tabulardata">Bank Detail</h3>
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl" >
                            
							<tr><td  colspan="10" align="center">&nbsp;</td></tr>
							
							<tr>
                              <td width="20%"></td>
                              <td width="30%" height="30" align="left"  class="form11">Bank Name <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="40%" align="left"><input type="text" name="bankName"  value="" tooltipText="Type Bank Name in this box" />                                </td>
							</tr>
                            
                            <tr>
                              <td ></td>
                              <td  height="30" align="left"  class="form11">Bank Account<font size="1" color="red" >*</font></td>
                              <td  align="left">:</td>
                              <td  align="left"><input type="text" name="bankAccount"  value="" tooltipText="Type Bank Account in this box" /> </td>
							</tr>
							
							<tr>
                              <td ></td>
                              <td  height="30" align="left"  class="form11">Bank Account IFSC<font size="1" color="red" >*</font></td>
                              <td  align="left">:</td>
                              <td  align="left"><input type="text" name="bankIFSC"  value="" tooltipText="Type Bank IFSC in this box" /> </td>
							</tr>
							
							<tr>
                              <td ></td>
                              <td  height="30" align="left"  class="form11">Bank Account Holder Name<font size="1" color="red" >*</font></td>
                              <td  align="left">:</td>
                              <td  align="left"><input type="text" name="bankAccountName"  value="" tooltipText="Type Bank Account Holder Name in this box" /> </td>
							</tr>
                            
							
                          </table>                          
                          </td>
                    </tr>
                    
                    <tr>
                      <td height="60" align="center" valign="middle" style="padding-left:50px">
                          <input name="Submit"  type="reset" value="Reset" />
                          <input name="Submit"  type="submit" value="Submit" />                      
                      </td>
                    </tr>
                  </table>
				  
				</form>	
					
					
					
					
					
                  </div></td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
              <tr>
                         <td align="center" width="100%">
                            <c:if test="${requestScope.BankList!=null && requestScope.BankList.size()>0 }">
                            <form name="updateBank" method="post" action="updateBankStatus.action" >
                            	<table width="90%"  cellspacing="0" cellpadding="0" align="center" class="tbls"   bgcolor="#a74312">
                        		<tr class="hd tabulardata" align="center">
						
								  <td width="5%" ><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></td>
								  <td width="3%" ><strong>S. No.</strong></td>
								  <td width="20%" ><strong>Bank Name</strong></td>
								  <td width="20%" ><strong>Bank Account Holder</strong></td>
								  <td width="20%" ><strong>BANK Account</strong></td>
								  <td width="10%" ><strong>Bank IFSC</strong></td>
								  <td width="15%" ><strong>Status</strong></td>
						  
                        		</tr>
                        		<c:forEach var="bank" items="${requestScope.BankList }" varStatus="loop">
                        			<tr class="colr" align="center" style="background:#fff;height:25px;" align="center">
						
									  <td><input type="checkbox" name="checkpartial" id="chek${loop.index }"  value="${loop.index }"/></td>
									  <td>${bank.slNo }
									  		<input  type="hidden" name="slNo${loop.index }" value="${bank.slNo }" />
									  </td>
									  <td><input  type="text" name="bankName${loop.index }" value="${bank.bankName }" /></td>
			                          <td><input  type="text"  name="bankAccountHolder${loop.index }" value="${bank.bankAccountName }" /></td>
			                          <td><input  type="text"  name="bankAccount${loop.index }" value="${bank.bankAccount }" /></td>
							  		  <td><input type="text"  name="bankAccountIfsc${loop.index }" value="${bank.bankIFSC }" /></td>
	                        		  <td>
			                          	<select name="status${loop.index }">
                          
			                          		<option selected="selected" value="${bank.status }">
			                          		<c:if test="${bank.status eq 1}">
			                          			Available
			                          		</c:if>
			                          		<c:if test="${bank.status eq 0}">
			                          			Not Available
			                          		</c:if>
			                          		</option>
			                          		<option value="1">Available</option>
			                          		<option value="0">Not Available</option>
			                          
			                           </select>
			                          </td>	
	                        		</tr>
                        		</c:forEach>
                                    <tr height="40px">
                                        <td style="border:none;"></td>
                                        <td style="border:none;"></td>
                                    	<td style="border:none;"></td>
                                        <td style="border:none;"></td>
                                        <td style="border:none;"></td>
                                        <td style="border:none;"></td>
                                        <td style="border:none;" align="center"><input style="color: #fff;"  type="submit" value="Update" /></td>
                                      </tr>
                      			</table>
                      		</form>
                            </c:if>
					     </td>
                   </tr>
              <tr>
                <td height="20"></td>
              </tr>
            </table>
            </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="left"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>





<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>

</body>
</html>

<script><!--

function submitForm()
{
var distributorId=document.registration.distributorId.value.replace(/^\s+|\s+$/, '');
if(distributorId== null || distributorId=="" )
{
alert("Your Form is Incomplete.");
document.registration.distributorId.focus();
return false;
}
var firstName=document.registration.firstName.value.replace(/^\s+|\s+$/, '');
if(firstName== null || firstName=="")
{
alert("Your Form is Incomplete.");
document.registration.firstName.focus();
return false;
}

var lastName=document.registration.lastName.value.replace(/^\s+|\s+$/, '');
if(lastName== null || lastName=="")
{
alert("Your Form is Incomplete.");
document.registration.lastName.focus();
return false;
}


var gender=document.registration.gender.value;
if(gender=="-1")
{
alert("Your Form is Incomplete.");
document.registration.gender.focus();
return false;
}
var companyType=document.registration.companyType.value;
if(companyType=="-1")
{
alert("Your Form is Incomplete.");
document.registration.companyType.focus();
return false;
}
var companyName=document.registration.companyName.value;
if(companyName== null || companyName=="")
{
alert("Your Form is Incomplete.");
document.registration.companyName.focus();
return false;
}
var mobileNo=document.registration.mobileNo.value.replace(/^\s+|\s+$/, '');
if(mobileNo== null || mobileNo=="" )
{
alert("Your Form is Incomplete.");
document.registration.mobileNo.focus();
return false;
}

var addressLine1=document.registration.addressLine1.value.replace(/^\s+|\s+$/, '');
if(addressLine1== null || addressLine1=="" )
{
alert("Your Form is Incomplete.");
document.registration.addressLine1.focus();
return false;
}
var state=document.registration.state.value;
if(state== "0" )
{
alert("Your Form is Incomplete.");
document.registration.state.focus();
return false;
}
var city=document.registration.city.value.replace(/^\s+|\s+$/, '');
if(city== null || city=="" )
{
alert("Your Form is Incomplete.");
document.registration.city.focus();
return false;
}

var pincode=document.registration.pincode.value.replace(/^\s+|\s+$/, '');
if(pincode== null || pincode=="" )
{
alert("Your Form is Incomplete.");
document.registration.pincode.focus();
return false;
}
 document.registration.action="agentRegistration.action?param=registration";
 document.registration.submit();
 }

var testresults
function checkemail(){

var str=document.registration.emailId.value;
var filter=/^.+@.+\..{2,3}$/

if (filter.test(str))
testresults=true;
else {
alert("Please input a valid email address!");
document.registration.emailId.focus();
testresults=false;
}
return testresults;

}

function createXMLHttpRequest()
{
if (window.ActiveXObject)
{
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest)
{
xmlHttp = new XMLHttpRequest();
}
}

/*check availablity code start*/

function checkDSID()
{
document.getElementById('img2').style.visibility="visible";
document.getElementById('img3').style.visibility="hidden";
createXMLHttpRequest();
var type=document.registration.distributorId.value;
xmlHttp.onreadystatechange = printValuesDSID;


xmlHttp.open("POST","agentRegistration.action?param=checkDSID&dsID="+type, true);
xmlHttp.send(null);
}

function checkUserName()
{
document.getElementById('img2').style.visibility="visible";
document.getElementById('img3').style.visibility="hidden";
createXMLHttpRequest();
var type=document.registration.emailId.value;
xmlHttp.onreadystatechange = printValuesEmail;


xmlHttp.open("POST","agentRegistration.action?param=checkUserName&loginUserName="+type, true);
xmlHttp.send(null);
}

function checkUserName1()
{

document.getElementById('img3').style.visibility="visible";
document.getElementById('img2').style.visibility="hidden";
createXMLHttpRequest();
var type=document.registration.mobileNo.value;
xmlHttp.onreadystatechange = printValuesEmails;


xmlHttp.open("POST","agentRegistration.action?param=checkMobileNo&mobileNo="+type, true);
xmlHttp.send(null);
}

function printValuesDSID()
{
if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response1=xmlHttp.responseText;
var resultDSID=document.getElementById('resultDSID');
if(response1=="Valid")
{
document.getElementById('img2').style.visibility="hidden";
response1="Valid Distributor ID."
resultDSID.innerHTML=response1;
resultDSID.style.color="green"


}
else
{
document.getElementById('img2').style.visibility="hidden";
response1="Invalid Distributor ID."
resultDSID.innerHTML=response1;
resultDSID.style.color="red"
document.regiestration.distributorId.value="";
document.regiestration.distributorId.focus();
}
}
}
}



function printValuesEmail()
{
if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response1=xmlHttp.responseText;

var resultEmail=document.getElementById('resultEmail');
if(response1=="valid")
{

document.getElementById('img2').style.visibility="hidden";
response1="Email ID is Unique."
resultEmail.innerHTML=response1;
resultEmail.style.color="green"


}
else
{


document.getElementById('img2').style.visibility="hidden";
response1="Email ID is Duplicate."
resultEmail.innerHTML=response1;
resultEmail.style.color="red"
document.regiestration.email.value="";
document.regiestration.email.focus();
}
}
}
}

/*===============*/

function printValuesEmails()
{
if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response2=xmlHttp.responseText;

var resultEmails=document.getElementById('resultEmails');
if(response2=="valid")
{

document.getElementById('img3').style.visibility="hidden";
response2="Mobile Number is Unique."
resultEmails.innerHTML=response2;
resultEmails.style.color="green"

}
else
{

document.getElementById('img3').style.visibility="hidden";
response2="Mobile Number is Duplicate."
resultEmails.innerHTML=response2;
resultEmails.style.color="red"
document.regiestration.mobileNo.value="";
document.regiestration.mobileNo.focus();
}
}
}
}

/*check availablity code stop*/

--></script>
<style type="text/css">
.accordion {
	width: 90%;
}

</style>


<script language="JavaScript">

var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")
function populatecities(type)
{
var xmlhttp;
var state="";
if(type=='office')
{
state=document.getElementById("Ostate").value; 
}
else if(type=='res')
{
state=document.getElementById("Rstate").value; 
}
   var url = "distDetails.action?state="+state;	  
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
	    if(type=='office')
	    {
    document.getElementById("officeDist").innerHTML=xmlhttp.responseText;
	    }
	    else{
		    
	    	 document.getElementById("resDist").innerHTML=xmlhttp.responseText;
	    }
    }
  }
xmlhttp.open("post",url,true);
xmlhttp.send();
}
function submitForm1()
{	
	
	if(chkForm())
	{	
	document.profileForm.action="updateProfile.action";
	document.profileForm.submit();
	}
}
</script>
