<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%></title>
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

</head>
<script language="Javascript" src="scripts/Localitysimple.js"></script>
<script language="Javascript" src="scripts/Localitysimpletest.js"></script>
<body><center>
 <form name="profileChange"  action="profileChange.do">
 <input type="hidden" value="profileChange"  name="param" />	

<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
<%

String message=(String)request.getAttribute("message");
if(message==null){
message="";
}

%>
		  
  <tr>
    <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px">Profile Change</td>
    </tr>
<tr><td height="20"> <%=message%></td></tr>
<tr>
    <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr>
        <td width="100%" align="center">	
   <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="form11">
                    <tr>
					  <td  height="32" align="center" valign="middle" colspan="5"></td>
                       
                     </tr>
				     <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">User ID</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						<td  height="" align="left" valign="left" width="29%"><input name="userId" type="text" value="" style="width:200px;" class="txt"  /></td>
						<td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                     
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">Name</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						<td  height="" align="left" valign="left" width="29%"><input name="usernNme" type="text" value="" style="width:200px;" class="txt"  /></td>
						<td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                     
                     
                      <tr>
					 <!-- <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                      <td  height="" align="left" valign="middle" width="17%">Address</td>
					  <td  height="5" align="center" valign="middle" width="3%">:</td>
					  <td  height="" align="left" valign="left" width="29%"><input name="Address" type="text" value="" style="width:200px;" class="txt"  />					    </textarea></td>
					  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                     
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">District</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						<td  height="" align="left" valign="left" width="29%"><select style="width:206px;" onchange="selctVal();" id="selctVal1" name="userType">
						   <option value="selected" selected="selected">Select District</option>
						   
						   </select></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">City</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						<td  height="" align="left" valign="left" width="29%"><select style="width:206px;" onchange="selctVal();" id="userType" name="userType2">
						   <option value="selected" selected="selected">Select City</option>
					      </select></td>
						<td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                      </tr>
                     
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">State</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						<td  height="" align="left" valign="left" width="29%"><select style="width:206px;" onchange="selctVal();" id="userType2" name="userType3">
						   <option value="selected" selected="selected">Select State</option>
					      </select></td>
						 <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                     </tr>
                     -->
                     
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
             <!-- <option value="Others">Others</option> -->
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
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">Pin Code</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="" align="left" valign="left" width="29%"><input name="distId" type="text" value="" style="width:200px;" class="txt"  /></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                      </tr>
                     
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">PAN Card</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="" align="left" valign="left" width="29%"><input name="distId" type="text" value="" style="width:200px;" class="txt"  /></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                      </tr>
                     
                     
                      <tr>
					  <td  height="30" align="center" valign="middle" width="30%">&nbsp;</td>
                        <td  height="" align="left" valign="middle" width="17%">Other</td>
						<td  height="5" align="center" valign="middle" width="3%">:</td>
						 <td  height="" align="left" valign="left" width="29%"><input name="distId" type="text" value="" style="width:200px;" class="txt"  /></td>
						  <td  height="" align="center" valign="middle" width="21%">&nbsp;</td>
                      </tr>
                     
                     <tr>
       <td  height="60" align="center" valign="middle" colspan="5" style="padding-left:105px;">
	   <input name="" type="button" value="Submit" style="width:100px;" class="txt"  onclick="return validateFrm()"/></td>
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
function validateFrm(){

var distid=document.profileChange.distId.value;

if(distid==""){
alert("Please enter complete distributor id");
return false;
}


document.profileChange.submit();
return true;
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
