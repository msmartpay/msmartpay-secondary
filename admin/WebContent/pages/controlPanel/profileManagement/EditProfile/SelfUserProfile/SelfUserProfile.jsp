<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.text.*" %>
<%@page import = "java.util.HashMap" %>
<%
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String curdate = formatter.format(todate);

HashMap map=(HashMap)request.getAttribute("selfProfileData");
String message=(String)request.getAttribute("message");
if(message==null)message="";
%>
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
            numberOfMonths: 1
	
		  
		  });

  });
  </script>


<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="script/jquery.js"></script>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->

function submitForm()
{
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
else{
 document.registration.action="selfUserProfile.action?param=updateSelfUser";
 document.registration.submit();
}
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

function checkemail1(){

var str=document.registration.alternateEmailId.value;
var filter=/^.+@.+\..{2,3}$/

if (filter.test(str))
testresults=true;
else {
alert("Please input a valid email address!");
document.registration.alternateEmailId.focus();
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
function checkEmailID()
{
document.getElementById('img2').style.visibility="visible";
document.getElementById('img3').style.visibility="hidden";
createXMLHttpRequest();
var type=document.registration.emailId.value;
xmlHttp.onreadystatechange = printValuesEmail;


xmlHttp.open("POST","selfUserProfile.action?param=checkEmailID&checkEmailID="+type, true);
xmlHttp.send(null);
}

function checkMobileNo()
{
document.getElementById('img3').style.visibility="visible";
document.getElementById('img2').style.visibility="hidden";
createXMLHttpRequest();
var type=document.registration.mobileNo.value;
xmlHttp.onreadystatechange = printValuesEmails;


xmlHttp.open("POST","selfUserProfile.action?param=checkUserMobile&Mobile="+type, true);
xmlHttp.send(null);
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
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
                <td width="100%" valign="bottom" align="center" ></td>
                
              </tr>
              <tr>
                <td width="100%" valign="middle" height="40" align="left" class="heading_mgs">Self Profile </td>
                
              </tr>
			  	 <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
              <tr>
                <td width="100%" align="center" valign="top">
                <div class="accordion" style="padding:20px;" id="border">
                
                    <form name="registration" method="post" enctype="multipart/form-data">
				  <table width="100%"  cellspacing="2" cellpadding="0" align="center">
                    <tr>
                      <td><h3 style="background:#993300; color:#FFF; text-align:center;">Personal Details</h3>
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
                          
                            <tr><td colspan="4" align="center"><!--message--></td></tr>
                            <tr>
                              <td width="15%"></td>
                              <td width="33%" height="30" align="left"  class="form11">First Name</td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><input type="text" name="firstName"  id="firstname"  value="<%=map.get("fName")%>"/> </td>
							</tr>
                            
                            <tr>
                              <td width="15%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Last Name</td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><input type="text" name="lastName" id="lastName" value="<%=map.get("lName")%>" /> </td>
							</tr>
                            
							<tr>
                              <td width="15%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Date of Birth</td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left"><%System.out.println("my dob is "+map.get("dob"));%>
                              <input name="dob" id="datepicker"  type="text"    value="<%=map.get("dob")%>" readonly="true"/></td>
                              
							</tr>
							
							
							<tr>
                              <td width="15%"></td>
                              <td width="33%" height="30" align="left"  class="form11">Gender</td>
                              <td width="5%" align="left">:</td>
                              <td width="46%" align="left">
                              <select name="gender" id="gender">
	   								<option selected="selected" value="<%=map.get("gender")%>"><%=map.get("gender")%></option>
                                    <option>Male</option>
                                    <option>Female</option>
    						   </select>
                              </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Company/ Firm Type</td>
                              <td align="left">:</td>
                              <td align="left"> <select name="companyType" id="firmtype">
                                <option value="<%=map.get("companyType")%>"><%=map.get("companyType")%></option>
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
                              <td align="left" height="30"  class="form11">Company/ Firm/ Shop Name</td>
                              <td align="left">:</td>
                              <td align="left"><input name="companyName" id="firmname" type="text" value="<%=map.get("companyName")%>"/></td>
                            </tr>
                            
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Email ID </td>
                              <td align="left">:</td>
                              <td align="left">
                             <input name="emailId"  type="email" onchange="checkemail();" value="<%=map.get("emailId")%>" />
                             <a href="#" style="text-decoration:none; font-size:10px; font-family:'Trebuchet MS';" onclick="checkEmailID()">
                             <font size="1.5" color="red" >Check Availability</font></a>
                             </td>
                              <td width="27" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="192"><div style="width:190px;font-family:'Trebuchet MS'; height:20px;" id="resultEmail"></div></td>
                            </tr>
                            
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Mobile Number </td>
                              <td align="left">:</td>
                              <td align="left">
                              <input name="mobileNo" id="Omobile" type="text" onkeypress="return digitonly(this,event)" maxlength="10" value="<%=map.get("mobileNo")%>" />
                              <a href="#" style="text-decoration:none; font-size:10px; font-family:'Trebuchet MS';" onclick="checkMobileNo()">
                             <font size="1.5" color="red" >Check Availability</font></a>
                              </td>
                               <td width="27" id="img3" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
										<td width="192"><div style="width:190px; font-family:'Trebuchet MS'; height:20px;" id="resultEmails"></div></td>
                            </tr>
                            
                            <tr>
                            <td colspan="100%" align="center"><h3 style="background:#993300; color:#FFF;">Address</h3></td>
                            </tr>
                            
                            
                            <tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 1</td>
                              <td align="left">:</td>
                              <td align="left"><input name="addressLine1" id="Oaddressline1" type="text"  value="<%=map.get("address1")%>" /></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 2</td>
                              <td align="left">:</td>
                              <td align="left"><input name="addressLine2" id="Oaddressline2" type="text" value="<%=map.get("address2")%>" /></td>
                            </tr>
							
							<tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Country</td>
                              <td align="left">:</td>
                              <td align="left">
                              <select name="country" id="Ocountry" >
                              <option selected="selected">India</option>
                                </select>
                                </td>
							</tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">State</td>
                              <td align="left">:</td>
                              <td align="left">
                             <select name="state" id="Ostate" onchange="populatecities('office')">
                                    <option selected="selected" value="<%=map.get("state")%>"><%=map.get("state")%></option>
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
                                </select>                              </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">District</td>
                              <td align="left">:</td>
                              <td align="left"><select name="District" id="officeDist">
                              <option selected="selected" value="<%=map.get("district")%>"><%=map.get("district")%></option>
                                  <option>District</option>
                                  </select>
                                  </td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">City</td>
                              <td align="left">:</td>
                              <td align="left"><input  name="city" type="text"  value="<%=map.get("city")%>"/></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Pin Code</td>
                              <td align="left">:</td>
                              <td align="left">
                                  <input type="text" name="pincode" id="Rpin"  onkeypress="return digitonly(this,event)" maxlength="6" tooltipText="Type Pin Code in this box" 
								  value="<%=map.get("pincode")%>"/></td>
                            </tr>
                          </table>                          
                          </td>
                    </tr>
                    <tr>
                      <td><h3 align="center" style="background:#993300; color:#FFF;">PAN Details</h3>
                          
                            <table width="100%"  cellspacing="0" cellpadding="0" align="left"  class="mydata_tabl">
                              
                              <tr>
                                <td width="105px"></td>
                                <td align="left" height="30"  class="form11">PAN Number</td>
                                <td align="right" width="98px" >: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td align="left"><input name="panNo" id="staxno" maxlength="12" type="text" value="<%=map.get("panNo")%>" /></td>
                              </tr>
                    
                            </table>
                            
                          </td>
                    </tr>
                    
                              
                             
                              
                    <tr>
                      <td height="60" align="center" valign="middle">
                      
                      <input name="Submit" type="button" value="Submit" class="cls_btn" onclick="submitForm()"/>
                      
                       </td>
                    </tr>
                  </table>
				</form>	
                    
                  </div>
                </td>
              </tr>
			  
              <tr>
                <td height="20"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
                <td height="60"></td>
              </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<style type="text/css">
.accordion {
	width: 1000px;
}
.accordion h3 {
	border:#a74312 solid 1px; 
	padding: 7px 15px;
	margin: 0; font-size:14px; font-weight:bold; font-family:Calibri; color:#a74312;
	

}

.accordion h4 {
	
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
