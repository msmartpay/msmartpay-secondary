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

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
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



<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="script/jquery.js"></script>
<%--<SCRIPT type="text/javascript" src="scripts/calendar.js?random=20060118"></script>--%>
<script language="Javascript" src="scripts/validation.js"></script>
<%--<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>--%>
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<script type="text/JavaScript">

<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script type="text/javascript">
function confirmHappy()
{
var hh=confirm("....This process will create the Distributor ID. On activation of Distributor ID, Rs.1000 will be charged from your account.");
if (hh==true)
{
alert("You have successfully registration");
}
else
{
confirm("You have cancelled the submission of form.");
}
}

function msg()
{
var a = confirm("You have to login first to use this service \nDo you want to continue......");
if (a ==1) 
{
  window.open("http://bus.travelepoint.co.in/booking/buslogin.aspx?user_id=demo@TRAVELEPOINT.IN", "_blank");
  }
else if(a!=1){window.open("reg.html", "_self"); }
}


function r_yes()
{
	document.getElementById('tr_1').style.display="";
}

function r_no()
{
	document.getElementById('tr_1').style.display="none";
}


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
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Agent Registration</td>
              </tr>
			
              <tr>
			  
                <td width="100%" align="center">
                <div class="accordion" style="padding:20px;" id="border">
                <form name="registration" method="post" enctype="multipart/form-data">
               
				  <table width="100%"  cellspacing="2" cellpadding="0" align="center" >
                    <tr>
                      <td><h3 align="center" class="hd tabulardata">Personal Details</h3>
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl" >
                            <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
							<tr><td  colspan="10" align="center">&nbsp;</td></tr>
							<tr>
                              <td width="22%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Distributor ID <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><input type="text" name="distributorId"  value="" tooltipText="Type Distributor ID in this box" />
							  <a href="#" style="text-decoration:none; font-size:10px; font-family:'Trebuchet MS';" onclick="checkDSID()">
                             <font size="1.5" color="red" >Check Availability</font></a></td>
                              <td width="27" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="192"><div style="width:190px;font-family:'Trebuchet MS'; height:20px;" id="resultDSID"></div>
							 </td>
							</tr>
							<tr>
                              <td width="22%"></td>
                              <td width="33%" height="30" align="left"  class="form11">First Name <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><input type="text" name="firstName" id="firstname" value="" tooltipText="Type First Name in this box" />                                </td>
							</tr>
                            
                            <tr>
                              <td width="22%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Last Name <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><input type="text" name="lastName" id="lastName" value="" tooltipText="Type Last Name in this box" /> </td>
							</tr>
                            
							<tr>
                              <td width="22%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Date of Birth <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left">
                              <input name="dob" style="width:220px; background:#FFF;" type="text"  id="datepicker" readonly="true"/>
                               </td>
							</tr>
							
							
							<tr>
                              <td width="22%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Gender <font size="1" color="red" >*</font></td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left">
                              <select  name="gender" id="gender">
	   								<option value="-1">Select</option>
                                    <option>Male</option>
                                    <option>Female</option>
    						   </select>
                              </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Company/ Firm Type <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left"> <select name="companyType" id="firmtype" >
                                <option value="-1">Select</option>
                                <option value="Non-Registered Entity">Non-Registered Entity</option>
                                <option value="Proprietorship">Proprietorship</option>
                                <option value="Partnership">Partnership</option>
                                <option value="Private Limited">Private Limited</option>
                                <option value="Limited">Limited</option>
                                <option value="Society">Society</option>
                                <option value="NGO">NGO</option>
                                <option value="Trust">Trust</option>
                            
                                </select>
							</td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Company/ Firm/ Shop Name <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left"><input name="companyName" id="firmname" type="text" tooltipText="Type Company/ Firm/ Shop Name in this box" /></td>
                            </tr>
                            
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Email ID <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                             <input name="emailId"  type="text" tooltipText="Type Email ID in this box" onchange="checkemail();" />
                             <a href="#" style="text-decoration:none; font-size:10px; font-family:'Trebuchet MS';" onclick="checkUserName()">
                             <font size="1.5" color="red" >Check Availability</font></a>
                             </td>
                              <td width="27" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="192"><div style="width:190px;font-family:'Trebuchet MS'; height:20px;" id="resultEmail"></div></td>
                            </tr>
                            
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Mobile Number <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                              <input name="mobileNo" id="Omobile" type="text" onkeypress="return digitonly(this,event)" maxlength="10" tooltipText="Type 10 Digits Mobile Number in this box" />
                              <a href="#" style="text-decoration:none; font-size:10px; font-family:'Trebuchet MS';" onclick="checkUserName1()">
                             <font size="1.5" color="red" >Check Availability</font></a>
                              </td>
                               <td width="27" id="img3" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="192"><div style="width:190px; font-family:'Trebuchet MS'; height:20px;" id="resultEmails"></div></td>
                            </tr>
                            
                            <tr>
                            <td colspan="100%" align="center"><h3 class="hd tabulardata">Address</h3></td>
                            </tr>
                            
                            
                            <tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 1 <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left"><input name="addressLine1" id="Oaddressline1" type="text"  tooltipText="Type Address 1 in this box" /></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 2 <font size="1" color="red" ></font></td>
                              <td align="left">:</td>
                              <td align="left"><input name="addressLine2" id="Oaddressline2" type="text"  tooltipText="Type Address 2 in this box" /></td>
                            </tr>
							
							<tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Country <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                              <select name="country" id="Ocountry" >
                              <option selected="selected">India</option>
                                </select>
                                </td>
							</tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">State <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                           <select name="state" id="Ostate" onchange="populatecities('office')">
                                    <option selected="selected" value="0">Select</option>
                                    <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
                                            <option value="Andhra Pradesh">Andhra Pradesh </option>
                                            <option value="Arunachal Pradesh">Arunachal Pradesh </option>
                                            <option value="Assam">Assam </option>
                                            <option value="Bihar">Bihar </option>
                                            <option value="Chandigarh">Chandigarh </option>
                                            <option value="Chhattisgarh">Chhattisgarh </option>
                                            <option value="Delhi">Delhi </option>
                                            <option value="Goa">Goa </option>
                                            <option value="Gujarat">Gujarat </option>
                                            <option value="Haryana">Haryana </option>
                                            <option value="Himachal Pradesh">Himachal Pradesh </option>
                                            <option value="Jammu and Kashmir">Jammu and Kashmir </option>
                                            <option value="Jharkhand">Jharkhand </option>
                                            <option value="Karnataka">Karnataka </option>
                                            <option value="Kerala">Kerala </option>
                                            <option value="Madhya Pradesh">Madhya Pradesh </option>
                                            <option value="Maharashtra">Maharashtra </option>
                                            <option value="Manipur">Manipur </option>
                                            <option value="Meghalaya">Meghalaya </option>
                                            <option value="Mizoram">Mizoram </option>                                            
                                            <option value="Nagaland">Nagaland </option>
                                            <option value="Orissa">Orissa </option>
                                            <option value="Pondicherry">Pondicherry</option>
                                            <option value="Punjab">Punjab </option>
                                            <option value="Rajasthan">Rajasthan </option>
                                            <option value="Sikkim">Sikkim </option>
                                            <option value="Tamil Nadu">Tamil Nadu </option>
                                            <option value="Tripura">Tripura </option>
                                            <option value="Uttarakhand">Uttarakhand </option>
                                            <option value="Uttar Pradesh">Uttar Pradesh </option>
                                            <option value="West Bengal">West Bengal</option>
                                </select>                             </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">District <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                              <select name="District" id="officeDist">
                              <option selected="selected" value="0">Select</option>
                                  </select>
                                  </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">City <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left"><input name="city" type="text" tooltipText="Type City name in this box" /></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Pin Code <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                                 <input type="text" name="pincode" id="Opin" onkeypress="return digitonly(this,event)" maxlength="6" tooltipText="Type 6 Digits Pin Code in this box" /></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">API Status <font size="1" color="red" >*</font></td>
                              <td align="left">:</td>
                              <td align="left">
                              		<select name="apiStatus" id="apiStatus">
                              			<option selected="selected" value="0">No</option>
                                  		<option value="1">Yes</option>
                                  </select>
                              </td>
                            </tr>
                          </table>                          
                          </td>
                    </tr>
                    <tr>
                      <td><h3 align="center" class="hd tabulardata">PAN Details</h3>
                          
                            <table width="100%"  cellspacing="0" cellpadding="0" align="left"  class="form11">
                              <tr>
                                <td width="15%"></td>
                                <td align="left" height="30" width="27%"  class="form11">PAN Card Status (Available on Your Name) <font size="1" color="red" >*</font></td>
                                <td align="left" width="2%">:</td>
                                <td align="left" width="56%">
                    		         <span style="margin-left:0px; color:#3E3E3E;"> <input onclick="r_yes()" type="radio" name="same"  checked="checked" />&nbsp;Yes</span> 
                  			         <span style="margin-left:50px; color:#3E3E3E;"> <input onclick="r_no()" type="radio" name="same"  /> &nbsp;No</span>
                                </td>
                              </tr>
                              <tr id="tr_1">
                                <td></td>
                                <td align="left" height="30"  class="form11">PAN Number <font size="1" color="red" >*</font></td>
                                <td align="left">:</td>
                                <td align="left"><input name="addressLine2" id="Oaddressline2" maxlength="12" type="text" tooltipText="Type PAN Number in this box" /></td>
                              </tr>
                            </table>
                          </td>
                    </tr>
                    
              
                    
                    <tr>
                      <td height="60" align="center" valign="middle" style="padding-left:50px">
                      <input name="Submit"  type="button" value="Submit"  onclick="submitForm()"/>
                          <input name="Submit"  type="reset" value="Reset" />                      
                          </td>
                    </tr>
                  </table>
				  
				</form>	
					
					
					
					
					
                  </div></td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
            </table></td>
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
