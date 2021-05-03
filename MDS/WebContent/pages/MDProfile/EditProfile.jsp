<%@ page import = "java.util.* "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*"%>
<%

Calendar calendar=Calendar.getInstance();
int currentDt=calendar.get(Calendar.DATE);
int currentMnth=calendar.get(Calendar.MONTH)+1;
int currentYr=calendar.get(Calendar.YEAR);

HashMap hm=new HashMap();
hm=(HashMap)request.getAttribute("mdInfo");
//System.out.println("hm :"+hm);

Date todate = new Date();

SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

String curdate = formatter.format(todate);
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}

String clientId=(String)session.getAttribute("clientId");


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%> :: Edit Profile</title>
<link href="css/dis.css" rel="stylesheet" type="text/css" />
 <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
 
 <script>
 $(document).ready(function(){
 
 $(".upon").hide();

$("#click_h").click(function () {

$(".upon").slideToggle("slow");

});

 })
 </script>
 
<script type="text/javascript" src="css/jquery.js"></script>
<script language="Javascript" src="scripts/Dist.js"></script>
<script language="Javascript" src="scripts/agentValidation1.js"></script>

<script language="JavaScript" src="scripts/showcity.js"></script>



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

.form_contents{ width:60%; margin:auto; }
.form_contents tr td{ border:0px solid #00CCCC;font-family:"Trebuchet MS"; font-size:13px;}
.form_contents tr td input{ width:270px; border:none; margin-left:100px; height:23px; padding:4px; color:#292929;font-family:"Trebuchet MS";}
.form_contents tr td select{ border:1px solid #A8A8A8; width:275px; height:40px; border:none; margin-left:100px; height:23px; padding:4px; color:#292929; font-family:"Trebuchet MS";}


.shadow1 tr td select,.shadow1 tr td input{ border:1px solid #A8A8A8;margin:5px; border-radius:5px;-webkit-border-radius:5px;-o-border-radius:5px;-moz-border-radius:5px;}

select{font-size:13px; font-family:"Trebuchet MS";}
option{ font-size:13px; font-family:"Trebuchet MS";}

tr.upon{ border:0px solid #900;}
tr.upon td{ border:0px solid #900;  padding:7px; }

.tblsb{ width:100%;}

.tblsb tr td{border:0px solid #000;font-family:"Trebuchet MS"; font-size:13px;}
.tblsb tr td strong{border:0px solid #000;font-family:"Trebuchet MS"; font-size:13px;}




</style>

<link href="../../css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
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
document.editProfile.checkstateId.value = District;
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

function convertToCaps(input)
{

input.value=input.value.toUpperCase();

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
}

</script>

</head>
<script language="Javascript" src="scripts/Localitysimple.js"></script>
<script language="Javascript" src="scripts/Localitysimpletest.js"></script>

<body><center>
 <form name="editProfile" action="mdProfile.do" method="post" >	
<input type="hidden" name="param"  value="saveProfile"/>
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
<% System.out.println("mdId :  in my deposit ::"+mdId);	
if(!mdId.equalsIgnoreCase("10095") && !mdId.equalsIgnoreCase("10096") ){	  %>
  <tr>
    <td colspan="1" height="40" width="50%" align="left" ><strong><font size="3">Edit Profile</font> </strong> <strong style="float:right;"><a href="checkloginInfo.do?param=changePwd" style="text-decoration:none;"><input name="" type="button" value="Change Password" style="width:140px; height:30px; margin-right:25px; font-size:13px; font-family:'Trebuchet MS';" class="txt"/>
                          </a></strong></td>
		
    </tr>
	<%     }%>

<tr>
<td  width="100%" valign="top" align="center" style="padding:7px 0 7px 0;"><font color="#FF0000" size="4"><%=message%></font>
</tr>

  <tr>
    
	 <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px">
	<table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
	
  <tr>
    <td width="100%" align="center">
	<div class="accordion" style="padding:20px 0px 20px 0px">
<table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11" >

  <tr><td colspan="3"><h3 align="left">Edit Details</h3>
   </td>
  </tr>
   
   <tr><td >
     <table   cellspacing="0" cellpadding="0" align="center"  class="form11 form_contents shadow1" border="0">
	 <tr>
        <td width="31%" height="25" align="left" style="padding-left:15px">Company Name<font size="1" color="red" >&nbsp; *</font></td>
        <td width="3%" align="center">:</td>
        <td width="66%" align="left"><input name="firmname" style="font-size:13px;" id="firmname2" type="text"  value="<%=hm.get("company_name")%>" maxlength="100"  onkeyup="convertToCaps(this);"/></td>
      </tr>
       <tr>
         <td width="31%" align="left" style="padding-left:15px">Address Line 1<font size="1" color="red" > *</font></td>
         <td width="3%" align="center">:</td>
         <td width="66%" align="left" height="25"><input name="Address1" type="text" style="font-size:13px;"  value="<%=hm.get("address1")%>" maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);" /></td>
       </tr>
       <tr>
         <td  align="left" style="padding-left:15px">Address Line 2</td>
         <td align="center">:</td>
         <td align="left" height="25"><input name="Address2" type="text" style="font-size:13px;"  value="<%=hm.get("address2")%>"  maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);"/>
		 <input name="checkstateId" id="checkstateId"  type="hidden"  class="input_txt" style="font-size:13px;" /></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px">Country<font size="1" color="red" > &nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
           <tr>
             <td><select style="height:30px; font-size:13px;"   name="officeCountry" id="Ocountry">
               <option selected="selected">India</option>
             </select>
               <span style="padding-left:5px"></span></td>
           </tr>
         </table></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px"  >State <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left">
           <select name="state" style="height:30px; font-size:13px;"  id="state" onchange="populateDistrict('District')"  >
			 <option value="<%=hm.get("state")%>"> <%=hm.get("state")%></option> 
             <option value="Select">---Select----</option>
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
             <!-- <option value="Others">Others</option> -->
           </select>       </td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px" >District <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left"><div id="District">
           <select name="District" style="height:30px; font-size:13px;">
             <option value="Select" selected="selected"><%=hm.get("district")%></option>
           </select>
         </div></td>
       </tr>
       <tr>
         <td align="left" style="padding-left:15px">City<font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td align="left">  <div id="city">
           <input name="city" type="text" style="font-size:13px;"   value="<%=hm.get("city")%>" onkeyup="convertToCaps(this);"/>
         </div></td>
       </tr>
       <tr>
         <td  align="left" style="padding-left:15px" height="25">Pin <font size="1" color="red" >&nbsp;*</font></td>
         <td align="center">:</td>
         <td  align="left"><span style="padding-left:0px;"></span>
           <input name="officePinCode" type="text" style="font-size:13px;"  value="<%=hm.get("pin_code")%>"  maxlength="6" onkeypress="return digitonly(this,event)" onselectstart="return false" /></td>
       </tr>
	   <tr>
         <td width="31%" align="left" style="padding-left:15px">Mobile Number<font size="1" color="red" > </font></td>
         <td width="3%" align="center">:</td>
         <td width="66%" align="left" height="25"><input name="mobile" style="font-size:13px;" type="text"  value="<%=hm.get("Mobile_no")%>" maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);" /></td>
       </tr>
	   <tr>
         <td width="31%" align="left" style="padding-left:15px">Email ID<font size="1" color="red" > </font></td>
         <td width="3%" align="center">:</td>
         <td width="66%" align="left" height="25"><input name="email" style="font-size:13px;" type="text"  value="<%=hm.get("Email_id")%>" maxlength="100"  onselectstart="return false" onkeyup="convertToCaps(this);" /></td>
       </tr>

	     
     </table>
     <p>&nbsp;</p></td>
   </tr>
   <tr>
  <td width="100" height="20" align="center" valign="left"><input name="Submit" class="txt" type="button" value="Submit" style="width:100px" onclick="return submitForm();"/>
    <p>&nbsp;</p>
  </td>

  </tr>
   <tr>
   <td colspan="3"><h3 align="left">Account Information</h3> <img style=" cursor:pointer; float:right; position:relative; top:-20px; right:15px;" id="click_h" src="images/down.gif" />
   </td>
   
  </tr>
 
  <table class="tblsb" cellpadding="0" cellspacing="0" border="0">
 <tr class="upon">
         <td width="55%"  align="left" style="padding-left:190px;"><strong>MD Login ID</strong></td>
         <td width="3%" align="right">:</td>
         <td width="66%" align="left" height="25" ><%=hm.get("user_name")%></td>
       </tr>
       <tr class="upon">
         <td width="55%" align="left" style="padding-left:190px;"><strong>MD Name</strong></td>
         <td width="3%" align="right">:</td>
         <td width="66%" align="left" height="25" ><%=hm.get("MD_name")%></td>
       </tr>
       <tr class="upon">
         <td width="55%" align="left" style="padding-left:190px;"><strong>Client ID</strong></td>
         <td width="3%" align="right">:</td>
         <td width="66%" align="left">  
                                <%=clientId%>
             </td>
       </tr>
      </table>

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
function submitForm(){

var firmname=document.editProfile.firmname.value;
if(firmname==""){
alert("please enter your Firm Name.");
document.editProfile.year.focus();
return false;
}

var Address1=document.editProfile.Address1.value;
if(Address1==""){
alert("please enter your Address line 1.");
document.editProfile.Address1.focus();
return false;
}


var city=document.editProfile.city.value;
if(city==""){
alert("please enter your City name.");
document.editProfile.city.focus();
return false;
}


var officePinCode=document.editProfile.officePinCode.value;
if(city==""){
alert("please enter your PinCode.");
document.editProfile.officePinCode.focus();
return false;
}


document.editProfile.submit();
return true;
}


</script>