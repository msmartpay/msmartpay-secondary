<%@ page import = "java.util.* "%> 

<%@ page import = "java.text.DecimalFormat "%> 

<%@ page import="java.util.Date" %>

<%@ page import="java.text.*" %>
<%@ page import="java.util.*"%>
<%

Calendar calendar=Calendar.getInstance();
int currentDt=calendar.get(Calendar.DATE);
int currentMnth=calendar.get(Calendar.MONTH)+1;
int currentYr=calendar.get(Calendar.YEAR);

Date todate = new Date();

SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

String curdate = formatter.format(todate);
String message=(String)session.getAttribute("message");
if(message==null){
message="";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%> :: Distributor Registration</title>
<link href="css/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery.js"></script>
<script language="Javascript" src="scripts/Dist.js"></script>
<script language="Javascript" src="scripts/agentValidation1.js"></script>

<script language="JavaScript" src="scripts/showcity.js"></script>

<script>
function r_1()
{
document.getElementById('tr_1').style.display="table-row";}

function r_2()
{
document.getElementById('tr_1').style.display="none";
}

</script>



<style type="text/css">
.accordion {
	width: 900px;
}
.accordion h3 { 
	background: #dbe5f1; border:#b8cbe1 solid 1px; 
	padding: 7px 15px; color:#000000;
	margin: 0; font-size:13px; font-weight:bold; font-family:"Trebuchet MS"; color:#000000;
	border-bottom: #b8cbe1 1px solid;
	cursor: pointer;
}
.accordion h5 {
	background: #ffffff  no-repeat right -51px; border:#b8cbe1 solid 1px; 
	padding: 7px 13px;
	margin: 0; font-size:12px; font-weight:bold; font-family:"Trebuchet MS"; color:#000000;
	border-bottom: #b8cbe1 1px solid;
	cursor: pointer;
}

.accordion h3.active {
	background-position: right 5px;
}
.accordion h4 {
	background: #ffffff; border:#b8cbe1 solid 1px; 
	
}

.input_txt1{font-family:"Trebuchet MS";}
.input_txt1 option{ font-family:"Trebuchet MS";}

input{ margin:3px 0 3px 0;}
select{ margin:3px 0 3px 0;}

</style>

<link href="../../css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>

<script language="javascript" type="text/javascript">

function selectService_5()
{
var mainservice_3=document.getElementById('mainService_3').value;
if(mainservice_3=="Not Available")
{
document.getElementById("file").style.display="none";
}
else
document.getElementById("file").style.display="block";
}
</script>

<script language="javascript" type="text/javascript">

function selectService_55()
{
var mainservice_3=document.getElementById('mainService_55').value;
if(mainservice_3=="Not Available")
{
document.getElementById("file2").style.display="none";
}
else
document.getElementById("file2").style.display="block";
}
</script>
<script language="javascript" type="text/javascript">
function selectService_66()
{
var mainservice_3=document.getElementById('mainService_66').value;
if(mainservice_3=="Not Available")
{
document.getElementById("file3").style.display="none";
}
else
document.getElementById("file3").style.display="block";
}
</script>

<script>

function changeStyleOccupation(){

if(document.registration.occupation.value=="other"){

document.registration.otherOccupation.style.display="";
}
else{
document.registration.otherOccupation.style.display="none";
}

}

function changeStyleDesignation(){

if(document.registration.designation.value=="other"){

document.registration.otherDesignation.style.display="";
}
else{
document.registration.otherDesignation.style.display="none";
}

}

function changeStyleCompanyType(){

if(document.registration.companyType.value=="other"){

document.registration.otherCompanyType.style.display="";
}
else{
document.registration.otherCompanyType.style.display="none";
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

<!-- Ajax Function for email and mobile --->

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
function checkMobile()
{

document.getElementById('img2').style.visibility="visible";
createXMLHttpRequest();
var type=document.regiestration.mobile.value;
xmlHttp.onreadystatechange = printValuesMobile;


xmlHttp.open("POST","distributorRegistration.do?param=checkMobile&mobile="+type, true);
xmlHttp.send(null);
}


function printValuesMobile()
{
if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response1=xmlHttp.responseText;
//alert("mobile"+response1);
var resultMobile=document.getElementById('resultMobile');
if(response1=="Valid")
{

document.getElementById('img2').style.visibility="hidden";
response1="Mobile Number is available"
resultMobile.innerHTML=response1;
resultMobile.style.color="green"
}
else
{
document.getElementById('img2').style.visibility="hidden";
response1="Mobile Number already exists"
resultMobile.innerHTML=response1;
resultMobile.style.color="red"
document.regiestration.email.value="";
document.regiestration.email.focus();
}
}
}
}
<!-- Ajax Function for email and mobile --->


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
function checkEmail()
{

document.getElementById('img2').style.visibility="visible";
createXMLHttpRequest();
var type=document.regiestration.emailId.value;
xmlHttp.onreadystatechange = printValuesEmail;


xmlHttp.open("POST","distributorRegistration.do?param=checkEmailId&email="+encodeURIComponent(type), true);

xmlHttp.send(null);
}


function printValuesEmail()
{
if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response1=xmlHttp.responseText;
//alert("email"+response1);
var resultEmail=document.getElementById('resultEmail');
if(response1=="Valid")
{

document.getElementById('img2').style.visibility="hidden";
response1="Email id is available"
resultEmail.innerHTML=response1;
resultEmail.style.color="green"
}
else
{
document.getElementById('img2').style.visibility="hidden";
response1="Email Id already exists"
resultEmail.innerHTML=response1;
resultEmail.style.color="red"
document.regiestration.email.value="";
document.regiestration.email.focus();
}
}
}
}

</script>

<script>
var xmlHttp;
 function createXMLHttpRequest() 
	       {
              
                if (window.ActiveXObject)
                {
                 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                  else {
                          if (window.XMLHttpRequest) {  xmlHttp = new XMLHttpRequest();}
					   }
           }
                



function populateDistrict(District){
//alert("hello we are inside populateDistrict method "+District);
document.regiestration.checkstateId.value = District;
var state="";
createXMLHttpRequest();
if(District=="District"){
 state=document.getElementById("state").value;
 //alert("m working inside "+state);
}
if(District=="resDistrict"){
 state=document.getElementById("resstate").value;
}

//alert("state is "+state);
xmlHttp.onreadystatechange=populateDistrictvalue;
xmlHttp.open("POST","StateDistrictListAction.do?state="+state+"&district="+District, true);
xmlHttp.send(null);



	  }


function populateDistrictvalue()
{


	
var districtvalue=document.getElementById("checkstateId").value;

if(xmlHttp.readyState == 4)
{
if(xmlHttp.status == 200)
{
var response=xmlHttp.responseText;
//alert("hello response from java class is "+response);


if(response=="invalid"){
 alert("There is no district in select state,Plz select other state or contact to help portal help support");
}
else{
	if(districtvalue=="District"){
		document.getElementById("District").innerHTML=response;	
	//alert("District is from District");
	}
	else{
		document.getElementById("resDistrict").innerHTML = response;
		
		//alert("District is from resDistrict");	
	
	}
}

}
}
}

</script>



</head>
<script language="Javascript" src="scripts/Localitysimple.js"></script>
<script language="Javascript" src="scripts/Localitysimpletest.js"></script>

<body><center>
 <form name="regiestration" action="distributorRegistration.do" method="post" enctype="multipart/form-data">	
<input type="hidden" name="param"  value="DsRegitration"/>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr>
         <td align="left" valign="top"><%@ include file="../../header.jsp" %></td>
	   </tr>
      <tr>
        <td width="1002" height="11"></td>
        </tr>
     
  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
          <!--  <td width="1003" height="9" valign="top"><img src="images/Dist_reg_bg_top.png" width="1002" height="9" /></td>-->
              </tr>
			   <tr>
        <td height="374" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" background="images/Dist_reg_bg_center.png" style="background-repeat:repeat-y;">
		  
  <tr>
    <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px"><strong>Distributor Registration Form</strong></td>
    </tr>

  <tr>
    <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px">
	<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr>
    <td width="100%" align="center"><%=message%>
	<div class="accordion" style="padding:20px 0px 20px 0px">
<table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11" >

  <tr><td colspan="7"><h3 align="center">Personal Details</h3>
    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
      <tr>
        <td width="31%" align="left" style="padding-left:15px" height="25">First Name<font size="1" color="red" >&nbsp; *</font></td>
        <td width="3%" align="center">:</td>
        <td width="66%" align="left"><input name="firstname" type="text" style="width:245px; height:30px;" class="style2" maxlength="50" value=""  onblur="validatefrm()" onkeyup="convertToCaps(this);"/></td>
      </tr>
      <tr>
        <td align="left" style="padding-left:15px" height="25">Last Name<font size="1" color="red" > &nbsp;*</font></td>
        <td align="center">:</td>
        <td align="left"><input name="lastname" type="text" style="width:245px;height:30px;" class="style2" maxlength="50" value=""  onblur="validatefrm()" onkeyup="convertToCaps(this);"/></td>
      </tr>
      <tr>
        <td align="left" style="padding-left:15px" height="25">Date of Birth <font size="1" color="red" >&nbsp;*</font></td>
        <td align="center">:</td>
        <td align="left"><select class="input_txt1" style="width:80px;height:30px; padding:5px;" name="date" id="date" >
          <option value="date">Date</option>
          <option value="01" >01</option>
          <option value="02" >02</option>
          <option value="03" >03</option>
          <option value="04" >04</option>
          <option value="05" >05</option>
          <option value="06" >06</option>
          <option value="07" >07</option>
          <option value="08" >08</option>
          <option value="09" >09</option>
          <option value="10" >10</option>
          <option value="11" >11</option>
          <option value="12" >12</option>
          <option value="13" >13</option>
          <option value="14" >14</option>
          <option value="15" >15</option>
          <option value="16" >16</option>
          <option value="17" >17</option>
          <option value="18" >18</option>
          <option value="19" >19</option>
          <option value="20" >20</option>
          <option value="21" >21</option>
          <option value="22" >22</option>
          <option value="23" >23</option>
          <option value="24" >24</option>
          <option value="25" >25</option>
          <option value="26" >26</option>
          <option value="27" >27</option>
          <option value="28" >28</option>
          <option value="29" >29</option>
          <option value="30" >30</option>
          <option value="31" >31</option>
        </select>
          <select class="input_txt1" style="width:80px;height:30px;padding:5px;" name="month" id="month">
            <option value="month">Month</option>
            <option value="01" >01</option>
            <option value="02" >02</option>
            <option value="03" >03</option>
            <option value="04" >04</option>
            <option value="05" >05</option>
            <option value="06" >06</option>
            <option value="07" >07</option>
            <option value="08" >08</option>
            <option value="09" >09</option>
            <option value="10" >10</option>
            <option value="11" >11</option>
            <option value="12" >12</option>
          </select>
          <select class="input_txt1" style="width:80px;height:30px;padding:5px;" name="year" id="year">
            <option value="Year">Year</option>
            <%
								    	for(int k=currentYr-4; k>=currentYr-150;k--){
								     	String selected="";
								    	if(k==currentYr){
									   selected="selected";
									   }
								  %>
            <option value="<%=k%>"><%=k%></option>
            <%}
									%>
          </select></td>
      </tr>
      <tr>
        <td align="left" style="padding-left:15px" height="25"  class="form11">Gender <font size="1" color="red" >*</font></td>
        <td align="center">:</td>
        <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td height="35" align="left"><select name="gender" style="width:249px;height:30px;padding:5px;" class="style2 input_txt1">
              <option value="">Gender</option>
              <option value="male">Male</option>
              <option value="female">Female</option>
            </select></td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td  align="left" style="padding-left:15px" height="25">Company/ Firm Type <font size="1" color="red" >&nbsp;*</font></td>
        <td  align="center">:</td>
        <td align="left"><select class="input_txt1" style=" width:249px;height:30px;padding:5px;" name="firmtype" id="firmtype2" onchange="checkfirmtypeothers()">
          <option value="select">----Company/ Firm Type---</option>
          <option value="Non-Registered Entity">Non-Registered Entity</option>
          <option value="Proprietorship">Proprietorship</option>
          <option value="Partnership">Partnership</option>
          <option value="Private Limited">Private Limited</option>
          <option value="Limited">Limited</option>
          <option value="Society">Society</option>
          <option value="NGO">NGO</option>
          <option value="Trust">Trust</option>
        <!--  <option value="Others">Others (Specify)</option>-->
        </select></td>
      </tr>
      <tr>
        <td align="left" style="padding-left:15px" height="25">Company/ Firm/ Shop Name<font size="1" color="red" >&nbsp; *</font></td>
        <td align="center">:</td>
        <td align="left"><input name="firmname" id="firmname2" type="text" style="height:30px; width:245px;" class="style2" value="" maxlength="100"  onkeyup="convertToCaps(this);"/></td>
      </tr>
	     <tr>
         <td  align="left" style="padding-left:15px" height="25">Mobile <font size="1" color="red" >*</font></td>
         <td  align="center">:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
         <tr>
          <td width="360" height="25" align="left"><input name="cod" id="cod" type="text" value="+91" style="width:50px;height:30px;" readonly/>
              &nbsp;&nbsp;&nbsp;<input name="mobile" type="text" style="width:175px;height:30px;" class="style2" value="" maxlength="10" onchange="checkemail();" tooltipText="Type Email Id in this box" onselectstart="return false" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off/>
            <a href="#" onclick="checkMobile()"><font size="1.5" color="red" >Check Availability</font></a> </td>
			<td width="28" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
			<td width="201"><div id="resultMobile"></div></td>
            </tr>
         </table></td>
       </tr>
	   <tr>
         <td align="left" style="padding-left:15px" height="25">Email ID <font size="1" color="red" >*</font></td>
         <td align="center">:</td>
        <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
     <td width="358" height="25" align="left"><input name="emailId" type="text" style="width:245px;height:30px;" class="style2" value=""  onchange="checkemail();" tooltipText="Type Email Id in this box" onselectstart="return false" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off/>
     <a href="#" onclick="checkEmail()"><font size="1.5" color="red" >Check Availability</font></a> </td>
	<td width="28" id="img2" style="visibility:hidden" ><img src="images/ajax-loader.gif" height="20" /></td>
	<td width="203"><div id="resultEmail"></div></td>
 </tr>
              </table></td>
       </tr>
    </table></td>
  </tr>
   
   <tr><td colspan="7"><h3 align="center">Address</h3>
     <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
       <tr>
         <td width="31%" align="left" style="padding-left:15px">Address Line 1<font size="1" color="red" > *</font></td>
         <td width="3%" align="center">:</td>
         <td width="66%" align="left" height="25"><input name="Address1" type="text" style="width:245px;height:30px; font-family:'Trebuchet MS';" class="style2" value="" maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);" /></td>
       </tr>
       <tr>
         <td  align="left" style="padding-left:15px">Address Line 2</td>
         <td align="center">:</td>
         <td align="left" height="25"><input name="Address2" type="text" style="width:245px;height:30px;"  value="" class="style2" maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);"/>
		 <input name="checkstateId" id="checkstateId"  type="hidden" style="width:211px;height:30px;" class="input_txt" /></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px">Country<font size="1" color="red" > &nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td height="25" ><select class="input_txt1" name="officeCountry" id="Ocountry" style=" width:249px;height:30px;padding:5px;">
               <option selected="selected">India</option>
             </select>
               <span style="padding-left:5px"></span></td>
           </tr>
         </table></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px" height="25">State <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left">
           <select name="state" class="style2" id="state" onchange="populateDistrict('District')"  style="width:249px;height:30px;padding:5px;">
             <option value="-1">--------- Select State ---------</option>
          
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
         
             <option value="Others">Others</option> -->
<!--  <option value="Abra">	Abra	</option>
																	<option value="Agusan del Norte">	Agusan del Norte	</option>
																	<option value="Aklan">	Aklan	</option>
																	<option value="Albay">	Albay	</option>
																	<option value="Antique">	Antique	</option>
																	<option value="Apayao">	Apayao	</option>
																	<option value="Aurora">	Aurora	</option>
																	<option value="Basilan">	Basilan	</option>
																	<option value="Bataan">	Bataan	</option>
																	<option value="Batanes">	Batanes	</option>
																	<option value="Batangas">	Batangas	</option>
																	<option value="Benguet">	Benguet	</option>
																	<option value="Biliran">	Biliran	</option>
																	<option value="Bohol">	Bohol	</option>
																	<option value="Bukidnon">	Bukidnon	</option>
																	<option value="Bulacan">	Bulacan	</option>
																	<option value="Cagayan">	Cagayan	</option>
																	<option value="Camarines Norte">	Camarines Norte	</option>
																	<option value="Camarines Sur">	Camarines Sur	</option>
																	<option value="Camiguin">	Camiguin	</option>
																	<option value="Capiz">	Capiz	</option>
																	<option value="Catanduanes">	Catanduanes	</option>
																	<option value="Cavite">	Cavite	</option>
																	<option value="Cebu">	Cebu	</option>
																	<option value="Compostella Valley">	Compostella Valley	</option>
																	<option value="Cotabato">	Cotabato	</option>
																	<option value="Davao del Norte">	Davao del Norte	</option>
																	<option value="Davao del Sur">	Davao del Sur	</option>
																	<option value="Davao Oriental">	Davao Oriental	</option>
																	<option value="Dinagat Islands">	Dinagat Islands	</option>
																	<option value="Eastern Samar">	Eastern Samar	</option>
																	<option value="Guimaras">	Guimaras	</option>
																	<option value="Ifugao">	Ifugao	</option>
																	<option value="ilo - ilo">	ilo - ilo	</option>
																	<option value="Ilocos Norte">	Ilocos Norte	</option>
																	<option value="Ilocos Sur">	Ilocos Sur 	</option>
																	<option value="Isabela">	Isabela	</option>
																	<option value="Kalinga">	Kalinga	</option>
																	<option value="La Union">	La Union	</option>
																	<option value="Laguna">	Laguna	</option>
																	<option value="Lanao del Norte">	Lanao del Norte	</option>
																	<option value="Lanao del Sur">	Lanao del Sur	</option>
																	<option value="Leyte">	Leyte	</option>
																	<option value="Maguindanao">	Maguindanao	</option>
																	<option value="Marinduque">	Marinduque	</option>
																	<option value="Metro Manila">	Metro Manila	</option>
																	<option value="Misamis Occidental">	Misamis Occidental	</option>
																	<option value="Misamis Oriental">	Misamis Oriental	</option>
																	<option value="Mountain Province">	Mountain Province	</option>
																	<option value="Negros Occidental">	Negros Occidental	</option>
																	<option value="Negros Oriental">	Negros Oriental	</option>
																	<option value="Northern Samar">	Northern Samar	</option>
																	<option value="Nueva Ecija">	Nueva Ecija	</option>
																	<option value="Nueva Vizcaya">	Nueva Vizcaya 	</option>
																	<option value="Occidental Mindoro">	Occidental Mindoro	</option>
																	<option value="Oriental Mindoro">	Oriental Mindoro	</option>
																	<option value="Palawan">	Palawan	</option>
																	<option value="Pampanga">	Pampanga	</option>
																	<option value="Pangasinan">	Pangasinan	</option>
																	<option value="Quezon">	Quezon	</option>
																	<option value="Quirino">	Quirino	</option>
																	<option value="Rizal">	Rizal	</option>
																	<option value="Romblon">	Romblon	</option>
																	<option value="Samar">	Samar	</option>
																	<option value="Sarangani">	Sarangani	</option>
																	<option value="Shariff Kabunsuan">	Shariff Kabunsuan	</option>
																	<option value="Siquijor">	Siquijor	</option>
																	<option value="Sorsogon">	Sorsogon	</option>
																	<option value="South Cotabato">	South Cotabato	</option>
																	<option value="Southern Leyte">	Southern Leyte	</option>
																	<option value="Sultan Kudarat">	Sultan Kudarat	</option>
																	<option value="Surigao del Norte">	Surigao del Norte	</option>
																	<option value="Surigao del Sur">	Surigao del Sur	</option>
																	<option value="Tarlac">	Tarlac	</option>
																	<option value="Tawi - Tawi">	Tawi - Tawi	</option>
																	<option value="Zambales">	Zambales	</option>
																	<option value="Zamboanga del Norte">	Zamboanga del Norte	</option>
																	<option value="Zamboanga del Sur">	Zamboanga del Sur	</option>
																	<option value="Zamboanga Sibugay">	Zamboanga Sibugay	</option>-->
           </select>
       </td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px" height="25">District <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left"><div id="District">
           <select name="District" class="style2"  style="width:249px;height:30px;padding:5px;">
             <option value="select" selected="selected">--- Select District------</option>
             
           </select>
         </div></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px" height="25">City<font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left">  <div id="city">
           <input name="city" type="text" class="style2"  style="width:245px;height:30px;" value="" onkeyup="convertToCaps(this);"/>
         </div></td>
       </tr>
       <tr>
         <td  align="left" style="padding-left:15px" height="25">Pin <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td  align="left"><span style="padding-left:0px;"></span>
           <input name="officePinCode" type="text" style="width:245px;height:30px;" value="" class="style2" maxlength="6" onkeypress="return digitonly(this,event)" onselectstart="return false" /></td>
       </tr>
       
     </table></td>
   </tr>
   
   <tr><td colspan="7"><h3 align="center">PAN  &nbsp; Details </h3>
       <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
       <tr>
         <td width="31%" align="left" style="padding-left:15px">PAN Card Status (Available on Your Name)<font size="1" color="red" > &nbsp;*</font></td>
         <td width="3%" align="center">:</td>
         <td width="66%"><label onclick="r_1()">
           <input name="PancardStatus"  type="radio" value="Y" checked="checked" onclick="checkstatuspancard()" />
           Yes</label>
           <span style="padding-left:50px"></span>
           <label  onclick="r_2()">
             <input name="PancardStatus" type="radio" value="N" onclick="checkstatuspancard1()" />
             No</label></td>
       </tr>
       <tr id="tr_1">
         <td align="left" style="padding-left:15px">PAN Number</td>
         <td align="center">:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td height="25"><input name="PancardNo" type="text" style="width:245px;height:30px;" class="style2" maxlength="50" value=""  onblur="validatefrm()" onkeyup="convertToCaps(this);"/></td>
           </tr>
         </table></td>
       </tr>
       
     </table></td>
   </tr>
   
   <tr><td colspan="7"><h3 align="center">Document to be Attached</h3>
     <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
       <tr>
         <td width="30%" align="left" style="padding-left:15px">ID Proof<font size="1" color="red" >&nbsp; *</font></td>
         <td width="3%" align="center">:</td>
         <td width="67%"  align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td height="25" width="35%"><select name="idProof" style="width:249px;height:30px;padding:5px;margin-left:8px;" class="style2" id="mainService_3" onchange="selectService_5()">
               <option value="Select">Select</option>
               <option value="Driving Licence">Driving Licence</option>
               <option value="Voter ID Card">Voter ID Card</option>
               <option value="PAN Card">PAN Card</option>
               <option value="Passport">Passport</option>
               <option value="Govtidcard">Government ID Card</option>
               <option value="Bankpassbook">Bank Passbook (With Photo)</option>
               <option value="Rationcard">Ration Card (With Photo)</option>
               <option value="Not Available">Not Available</option>
             </select></td>
             <td width="65%" align="left"><input style="height:30px;" type="file" name="idFile" class="style2" value="Attach" id="file" size="25"/></td>
           </tr>
         </table></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px">Address Proof <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td height="25" width="35%"><select name="addressProof" style="width:249px;height:30px;padding:5px;margin-left:8px;" class="style2" id="mainService_55" onchange="selectService_55()">
               <option value="Select">Select</option>
               <option value="Electricitybill">Electricity</option>
               <option value="Waterbill">Water</option>
               <option value="Telbill">Telephone</option>
               <option value="HouseTaxBill">House Tax Bill</option>
               <option value="LicBond">LIC Bond</option>
               <option value="Rentagreement">Rent Agreement</option>
               <option value="Rationcard">Ration Card</option>
               <option value="Passport">Passport</option>
               <option value="Regcertificate">Service/Sales Tax Registration Certificate</option>
               <option value="Certifiedletter">Certified / Attested Letter from Patwari/Gazetted Officer/BDO</option>
               <option value="Not Available">Not Available</option>
             </select></td>
             <td width="65%" align="left"><input name="addFile" style="height:30px;" type="file" class="style2" value="Attach" id="file2"  size="25"  /></td>
           </tr>
         </table></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px">Business/Firm Proof <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center" >:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td height="25" width="35%"><select name="bussinessProof"  style="width:249px;height:30px;padding:5px; margin-left:8px;" class="style2" id="mainService_66" onchange="selectService_66()">
               <option value="Select">Select</option>
               <option value="Regcertificate">Registration Certificate</option>
               <option value="Memorandum">Memorandum of Association</option>
               <option value="Not Available">Not Available</option>
             </select></td>
             <td width="65%" align="left"><input name="businessFile" type="file" style="height:30px;" class="style2" value="Attach" id="file3"  size="25"  />
			 
			 </td>
           </tr>
         </table></td>
       </tr>
     </table></td>
   </tr>
	<tr>
  <td width="100" height="20" colspan="5" align="center" valign="middle"><input name="Submit" class="txt" type="button" value="Submit" style="width:100px" onclick="return submitForm();"/></td>
  </tr>
      </table>
      
    </div>
    
</td></tr></table>
</td>
  </tr>
		  
</table>
</td></tr>
<tr><td height="30"></td></tr>

  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table></td></tr></table>
<%session.removeAttribute("message");%>
</form></center>
</body>
</html>

<script language="javascript">

function clearText(form1)
{
	form1.value="";
	form1.className="style2";
}
function view1()
{
if(document.regiestration.occupation.value=="other")
{
document.regiestration.otherOccupation.style.display="";
document.regiestration.otherOccupation.focus();
}
else
{
document.regiestration.otherOccupation.style.display="none";

}
}
function view2()
{
if(document.regiestration.companyType.value=="other")
{
document.regiestration.otherCompanyType.style.display="";
document.regiestration.otherCompanyType.focus();
}
else
{
document.regiestration.otherCompanyType.style.display="none";

}
}
function view3()
{
if(document.regiestration.designation.value=="other")
{
document.regiestration.otherDesignation.style.display="";
document.regiestration.otherDesignation.focus();
}
else
{
document.regiestration.otherDesignation.style.display="none";

}
}


function convertToCaps(input)
{

input.value=input.value.toUpperCase();

} 
</script>

<script language="javascript">
function submitForm(){

var firstname=document.regiestration.firstname.value.replace(/^\s+|\s+$/, '');
if(firstname==""){
alert("please enter First Name.");
document.regiestration.firstname.focus();
return false;
}
var lastname=document.regiestration.lastname.value.replace(/^\s+|\s+$/, '');
if(lastname==""){
alert("please enter Last Name.");
document.regiestration.lastname.focus();
return false;
}
var date=document.regiestration.date.value.replace(/^\s+|\s+$/, '');
if(date=="date"){
alert("please select Date in date of birth field.");
document.regiestration.date.focus();
return false;
}
var month=document.regiestration.month.value.replace(/^\s+|\s+$/, '');
if(month=="date"){
alert("please select month in date of birth field.");
document.regiestration.month.focus();
return false;
}
var year=document.regiestration.year.value.replace(/^\s+|\s+$/, '');
if(year=="Year"){
alert("please select month in date of birth field.");
document.regiestration.year.focus();
return false;
}

var gender=document.regiestration.gender.value.replace(/^\s+|\s+$/, '');
if(year==""){
alert("please Select Gender.");
document.regiestration.gender.focus();
return false;
}
var firmtype=document.regiestration.firmtype.value.replace(/^\s+|\s+$/, '');
if(firmtype=="select"){
alert("please select Firm Type.");
document.regiestration.gender.focus();
return false;
}
var firmname=document.regiestration.firmname.value.replace(/^\s+|\s+$/, '');
if(firmname==""){
alert("please enter your Firm Name.");
document.regiestration.year.focus();
return false;
}

var Address1=document.regiestration.Address1.value.replace(/^\s+|\s+$/, '');
if(Address1==""){
alert("please enter your Address line 1.");
document.regiestration.Address1.focus();
return false;
}


var city=document.regiestration.city.value.replace(/^\s+|\s+$/, '');
if(city==""){
alert("please enter your City name.");
document.regiestration.city.focus();
return false;
}


var officePinCode=document.regiestration.officePinCode.value.replace(/^\s+|\s+$/, '');
if(city==""){
alert("please enter your PinCode.");
document.regiestration.officePinCode.focus();
return false;
}

var mobile=document.regiestration.mobile.value.replace(/^\s+|\s+$/, '');
if(mobile==""){
alert("please enter your Mobile number.");
document.regiestration.mobile.focus();
return false;
}
var emailId=document.regiestration.emailId.value.replace(/^\s+|\s+$/, '');
if(emailId==""){
alert("please enter your Email Id.");
document.regiestration.emailId.focus();
return false;
}



document.regiestration.submit();
return true;
}


function convertToCaps(input)
{

input.value=input.value.toUpperCase();

} 
</script>