<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Registration Form</title>
<style type="text/css">
body { font-family:"Trebuchet MS";
	background: #ffffff;
	font-size: 12px;
	margin-bottom: 0px;
	border: 0;
	float: none;
	border-top: 0;
	margin: 0;
	bottom: 0px; font-weight:bold;
}
p, div, a, img, ul, li {
	margin: 0px;
	padding: 0px;
}
.big {
	font-size: 16px;
	font-weight: bold;
	color: #000000;
}
.form11 {
	font-family:"Trebuchet MS";
	font-size:12px;
	color:#000000;
	font-weight:bold; padding-left:20px;
}
.buttxt {
	font-family:"Trebuchet MS";
	font-size:12px;
	color:#000000;
	font-weight:bold;
}

.input_txt {
	color: #333333;
	font-family:"Trebuchet MS";
	font-size: 12px; width:280px;
}
.input_txt1 {
	color: #333333;
	font-family:"Trebuchet MS";
	font-size: 12px; width:285px;
}
.input_txt2 {
	color: #333333;
	font-family:"Trebuchet MS";
	font-size: 12px; width:280px;
}

.border{
	border:#000000 solid 1px;
}
</style>
</head>
<body>
<form>
<center>
  <p class="input_txt2">&nbsp;</p>
  <table cellpadding="0" cellspacing="0" border="0" align="center" 	width="800">
  
   <tr><td align="left" height="130" width="1000">
 <%@ include file="header.jsp"%></td></tr>
    <tr>
      <td height="40" align="left" valign="middle" class="big">Registration Form</td>
    </tr>
    <tr>
      <td width="100%" valign="top" align="center" style="padding-bottom:10px"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="border" >
           <tr>
    <td width="100%" align="center" style="padding:10px 0px 20px 0px">
	
<table width="85%" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr><td colspan="3" height="30" valign="middle" align="center" class="big">Personal Details</td></tr>
					  <tr>
                        <td width="38%" height="30" align="left"  class="form11">First Name <font size="1" color="red" >*</font></td>
                        <td width="11%" align="center">:</td>
                        <td width="51%" align="left"><select class="input_txt1" style="width:50px" name="initial" id="initial">
                            <option>Select</option>
                            <option selected="selected">Mr.</option>
                            <option>Ms.</option>
							<option>Mrs.</option>
                         
                        </select>&nbsp;&nbsp;&nbsp;
                        <input name="firstname" id="firstname" type="text" style="width:217px" class="input_txt" value="Sudheer"/></td>
                      </tr>
					 
					  <tr>
                  <td align="left" height="35"   class="form11">Date of Birth <font size="1" color="red" >*</font></td>
                  <td align="center">:</td>
                  <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                      <tr>
                        <td height="35" align="left"><input name="dob" type="hidden" class="maintext" id="start" size="15" readonly="readonly" />
                     <select name="day">
                       <%

for(int i=1;i<=31;i++){
String selected="";

if(i==currentDt){
selected="selected";
}
%>
                       <%if(i<=9){%>
                       <option value="0<%=i%>">0<%=i%></option>
                       <%}else{%>
                       <option value="<%=i%>"><%=i%></option>
                       <%
}}%>
                     </select>
                     <select name="month">
                       <%
for(int j=1;j<13;j++){
String selected="";
if(j==currentMnth){
selected="selected";
}
%>
                       <%if(j<=9){%>
                       <option value="0<%=j%>">0<%=j%></option>
                       <%}else{%>
                       <option value="<%=j%>"><%=j%></option>
                       <%}
}%>
                     </select>
                     <select name="year">
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
                     </select>
&nbsp;&nbsp;                        </td>
                      </tr>
                    </table></td>
                </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Gender <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" name="gender" id="gender">
                            <option>Select Gender</option>
                            <option selected="selected">Male</option>
                            <option>Female</option>
                         
                          </select></td>
                      </tr>
					   <tr>
                        <td align="left" height="30"  class="form11">Marital Status <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" name="maritalStatus" id="marital">
                            <option>Select Marital Status</option>
                            <option>Married</option>
                            <option selected="selected">Unmarried</option>
                         
                          </select></td>
                      </tr>
					  <tr>
                        <td height="30" align="left"  class="form11">Father/Husband Name<font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" style="width:50px" name="initial" id="initial">
                            <option>Select</option>
                            <option selected="selected">Mr.</option>
                            <option>Mrs.</option>
                         
                          </select>&nbsp;&nbsp;&nbsp;<input name="fatherName" id="fathername" type="text" class="input_txt" value="Demo1" style="width:217px" /></td>
                      </tr>
					  <tr>
                        <td height="30" align="left"  class="form11">Mother's Maiden Name<font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" style="width:50px" name="initial" id="initial">
                            <option>Select</option>
                            <option >Mr.</option>
                            <option selected="selected">Mrs.</option>
                         
                          </select>&nbsp;&nbsp;&nbsp;<input name="motherName" id="mothername" type="text" class="input_txt" value="Demo Rani" style="width:217px" /></td>
                      </tr>
				<tr>
                        <td align="left" height="30"  class="form11">Your Occupation <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left">					
						<select class="input_txt1" name="occupation" id="occupation">
                            <option>Select</option>
                
						
<option>STUDENT</option>
<option>EDUCATED UNEMPLOYED</option>
<option>RETIRED DEFENCE PERSONNEL</option>
<option>TEACHER</option>
<option>RETIRED</option>
<option>HOUSEWIFE</option>
<option>INSURANCE AGENT</option>
<option>POST OFFICE AGENT</option>
<option>MUTUAL FUND AGENT</option>
<option>SHARE BROKER</option>
<option>BANK AGENT/FACILITATOR</option>
<option>MONEY TRANSFER AGENT</option>
<option>GENERAL MERCHANT/KIRANA</option>
<option>CHEMIST SHOP/COSMETICS</option>
<option>TELECOM RETAILER</option>
<option>CYBER CAFÉ</option>
<option>MILK-BREAD-GROCERY</option>
<option>SUPERMARKET</option>
<option>PETROL/DIESEL PUMP</option>
<option>COOKING GAS AGENCY</option>
<option>TRAVEL AGENT</option>
<option>MONEY LENDER</option>
<option>PAAN/ BEEDI / CIGARETTE</option>
<option>BAKERY/ CONFECTIONERY</option>
<option>AGRI PRODUCE BROKER</option>
<option>TAXI DRIVER</option>
<option>AUTO DRIVER</option>
<option>BUS DRIVER/CONDUCTOR</option>
<option>WATCHMAN/SECURITY</option>
<option>SOCIETY/RWA REP</option>
<option>CA/CS/ICWA</option>
<option>CONSULTANT</option>
<option>STD-PCO BOOTH OWNER</option>
<option>ELECTRICIAN</option>
<option>STATIONARY SHOP</option>
<option>OTHERS (SPECIFY):</option></select>						</td>
                  </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Company/ Firm Type <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left">					
						<select class="input_txt1" name="companyType" id="firmtype">
                            <option>Select</option>
                
						
<option>Propritership</option>
<option>Partnership</option>
<option>Society</option>
<option>Pvt. Ltd.</option>
<option>Ltd.</option>
<option>NGO</option>
<option>Trust</option>
<option>Other</option></select>						</td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Company/ Firm Name/ Shop Name <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="companyName" id="firmname" type="text" class="input_txt" value="my company" /></td>
                      </tr>
					  
					  <tr>
                        <td align="left" height="30"  class="form11">Designation <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" name="designation" id="designation">
                            <option>Select</option>
                
						
<option>Individual</option>
<option>Partner</option>
<option>Director</option>
<option>Propriter</option>
<option>Other</option></select></td>
                      </tr>
					  
					  <tr><td colspan="3" height="30" valign="middle" align="center" class="big">Office Address</td></tr>
					  
					 
					  
					  <tr>
                        <td align="left" height="30"  class="form11">Office Address  Line 1 <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="officeAddress1" id="Oaddressline1" type="text" class="input_txt" value="#1/2, 1st Floor"/></td>
                      </tr>
                      <tr>
                        <td align="left" height="30"  class="form11">Office Address Line 2</td>
                        <td align="center">:</td>
                        <td align="left"><input name="officeAddress2" id="Oaddressline2" type="text" class="input_txt" value="9th, Croos, 5th main"/></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"   class="form11">Country <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" name="officeCountry" id="Ocountry">
                            <option>Select Country</option>
                            <option selected="selected">India</option>
                          </select></td>
                      </tr>
					   <tr>
                        <td align="left" height="30"   class="form11">State <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select name="state" class="style2" onchange="populatecities()" style="width:200px;">
						
						
						<option value="<%=map.get("Res_State")%>" selected="selected"><%=map.get("Res_State")%></option>
                                            
                                              <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
                                              <option value="Andhra Pradesh">Andhra Pradesh </option>
                                              <option value="Arunachal Pradesh">Arunachal Pradesh </option>
                                              <option value="Assam">Assam </option>
                                              <option value="Bihar">Bihar </option>
                                              <option value="Chandigarh">Chandigarh </option>
                                              <option value="Chhattisgarh">Chhattisgarh </option>
                                              
                                             
                                           
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
                                              <option value="NewDelhi">NewDelhi </option>
                                              <option value="Nagaland">Nagaland </option>
                                              <option value="Orissa">Orissa </option>
                                              <option value="Pondicherry">Pondicherry</option>
                                              <option value="Punjab">Punjab </option>
                                              <option value="Rajasthan">Rajasthan </option>
                                              <option value="Sikkim">Sikkim </option>
                                              <option value="Tamil Nadu">Tamil Nadu </option>
                                              <option value="Tripura">Tripura </option>
                                              <option value="Uttaranchal">Uttaranchal </option>
                                              <option value="Uttar Pradesh">Uttar Pradesh </option>
                                              <option value="West Bengal">West Bengal</option>
											  <!-- <option value="Others">Others</option> -->
                                            </select> </td>
                      </tr>
					  <tr>
                        <td align="left" height="30"   class="form11">District <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td height="25" ><div id="cityId1">
				<select name="District" class="style2"  onclick="checkStateSelection1()" style="width:275px;">
                                            <option value="-1" selected="selected">--- Select District------</option>
                          </select></div>										     </td>
                      </tr>
                      <tr>
                        <td align="left" height="30"   class="form11">City <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                          <td height="25" >              
										
										  <div id="cityId">
										    <select name="city" class="style2"  onclick="checkStateSelection1()" style="width:275px;">
                                              <option value="-1" selected="selected">------------Select City -----------</option>
                                            </select>
										  </div>									          </td>
                      </tr>
                      <tr>
                        <td height="35" align="left"  class="form11">Nearby Land Mark</td>
                        <td align="center">:</td>
                        <td align="left"><input name="officeLandMark" id="Olandmark" type="text" class="input_txt" value="Bus Stand"/></td>
                      </tr>
					  <tr>
                        <td height="35" align="left"  class="form11">Pin <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="Opin" id="officePinCode" type="text" class="input_txt" value="560018"/></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Office Mobile No. <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="officeMobileNo" id="Omobile" type="text" class="input_txt" value="9999999999" style="width:217px" /></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Alternate Mobile No <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="alternateNo" id="Oalternatemobile" type="text" class="input_txt" value="9999999999" style="width:217px" /></td>
                      </tr>
					  <tr>
                        <td height="35" align="left"  class="form11">Office Phone</td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="Ophone" id="officePhone" type="text" class="input_txt" value="26507722" style="width:217px" /></td>
                      </tr>
					  
					                      
                      
					  
					  <tr>
                        <td align="left" height="30"  class="form11">Office Fax</td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="officefax" id="officefax" type="text" class="input_txt"  value="26677676" style="width:217px" /></td>
                      </tr>
					   <tr>
                        <td align="left" height="30"  class="form11">Website</td>
                        <td align="center">:</td>
                        <td align="left"><input name="website" id="website" type="text" class="input_txt"  value="www.mycompany.com"/></td>
                      </tr>
					    <tr><td colspan="3" height="30" valign="middle" align="center" class="big">Residence Address</td></tr>
					 <tr>
                        <td align="left" height="30"  class="form11">Residential Address  Line 1 <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="residentialAddress1" id="Raddressline1" type="text" class="input_txt" value="#1/2, 1st Floor"/></td>
                  </tr>
                      <tr>
                        <td align="left" height="30"  class="form11">Residential Address Line 2</td>
                        <td align="center">:</td>
                        <td align="left"><input name="residentialAddress1" id="Raddressline2" type="text" class="input_txt" value="9th, Croos, 5th main"/></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"   class="form11">Country <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select class="input_txt1" name="Rcountry" id="Rcountry">
                            <option>Select Country</option>
                            <option selected="selected">India</option>
                          </select></td>
                      </tr>
					   <tr>
                        <td align="left" height="30"   class="form11">State <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><select name="resState" class="style2" onchange="populatecitiesres()" style="width:200px;">
                                           <option value="<%=map.get("Res_State")%>" selected="selected"><%=map.get("Res_State")%></option>
                                            
                                              <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
                                              <option value="Andhra Pradesh">Andhra Pradesh </option>
                                              <option value="Arunachal Pradesh">Arunachal Pradesh </option>
                                              <option value="Assam">Assam </option>
                                              <option value="Bihar">Bihar </option>
                                              <option value="Chandigarh">Chandigarh </option>
                                              <option value="Chhattisgarh">Chhattisgarh </option>
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
                                              <option value="NewDelhi">NewDelhi </option>
                                              <option value="Nagaland">Nagaland </option>
                                              <option value="Orissa">Orissa </option>
                                              <option value="Pondicherry">Pondicherry</option>
                                              <option value="Punjab">Punjab </option>
                                              <option value="Rajasthan">Rajasthan </option>
                                              <option value="Sikkim">Sikkim </option>
                                              <option value="Tamil Nadu">Tamil Nadu </option>
                                              <option value="Tripura">Tripura </option>
                                              <option value="Uttaranchal">Uttaranchal </option>
                                              <option value="Uttar Pradesh">Uttar Pradesh </option>
                                              <option value="West Bengal">West Bengal</option>
											  <!-- <option value="Others">Others</option> -->
                                            </select>       </td>
                      </tr>
					  <tr>
                        <td align="left" height="30"   class="form11">District <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
						
					
                        <td height="25" ><div id="resId1"><select name="resDistrict" class="style2"  onclick="checkStateSelection11()" style="width:275px;">
                                            <option value="-1" selected="selected">--------- Select District ---------</option>
                                          </select></div>									          </td>
                      </tr>
                      <tr>
                        <td align="left" height="30"   class="form11">City <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
						<%
									String resCity=(String)map.get("Res_City");
									
									if(resCity==null){
									resCity="-";
									}
									%>
                        <td height="25" >
										  <div id="resId"><select name="resCity" class="style2"  onclick="checkStateSelection()" style="width:275px;">
                                           
											<option value='<%=map.get("code")%>' selected="selected"><%=map.get("Res_City")%></option>
                                          </select></div>         </td>
                      </tr>
                      <tr>
                        <td height="35" align="left"  class="form11">Nearby Land Mark</td>
                        <td align="center">:</td>
                        <td align="left"><input name="residentialLandmark" id="Rlandmark" type="text" class="input_txt" value="Bus Stand"/></td>
                      </tr>
					  <tr>
                        <td height="35" align="left"  class="form11">Pin <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="residentialPinCode" id="Rpin" type="text" class="input_txt" value="560018"/></td>
                      </tr>
					  
					    <tr>
                        <td align="left" height="30"  class="form11">Residential Mobile No. <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="residentialMobileNo" id="Rmobile" type="text" class="input_txt" value="9999999999" style="width:217px" /></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Alternate Mobile No <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="Ralternatemobile" id="residentialAlternateNo" type="text" class="input_txt" value="9999999999" style="width:217px" /></td>
                      </tr>
					  <tr>
                        <td height="35" align="left"  class="form11">Residential Phone</td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="residentialPhoneNo" id="Rphone" type="text" class="input_txt" value="26507722" style="width:217px" /></td>
                      </tr>
					  
					                      
                      
					  
					  <tr>
                        <td align="left" height="30"  class="form11">Residential Fax</td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="residentialFaxNo" id="Rfax" type="text" class="input_txt"  value="26677676" style="width:217px" /></td>
                      </tr>
					  
					   <tr><td colspan="3" height="30" valign="middle" align="center" class="big">Choose One for your Correspondence Address </td></tr>
					   
					    <tr><td colspan="3" height="30" valign="middle" align="center">
					      <label>
					        <input name="correspondenceCheck" type="radio" value="radiobutton" />
					        Office Address</label><span style="padding-left:50px"></span>
							<label>
					        <input name="correspondenceCheck" type="radio" value="radiobutton" />
					        Residence Address</label>
						
					    </td></tr>
					  
					  
					  
					  <tr><td colspan="3" height="30" valign="middle" align="center" class="big">Primary Details </td></tr>
					  <tr>
                        <td align="left" height="30"  class="form11">PAN/ S. Tax/ TIN Number </td>
                        <td align="center">:</td>
                        <td align="left"><input name="panNo" id="staxno" type="text" class="input_txt" value="AZUP2KST" /></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Email ID <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="email" id="emailId" type="text" class="input_txt" value="team@irctc.co.in" /></td>
                      </tr>
					  
					   <tr>
                        <td align="left" height="30"  class="form11">Alternate Email ID</td>
                        <td align="center">:</td>
                        <td align="left"><input name="alternateEmailId" id="altemail" type="text" class="input_txt" value="team@irctc.co.in" /></td>
                      </tr>
					  <tr>
                        <td align="left" height="30"  class="form11">Authorized Mobile No <font size="1" color="red" >*</font></td>
                        <td align="center">:</td>
                        <td align="left"><input name="cod" id="cod" type="text" class="input_txt" value="+91" style="width:50px"/>&nbsp;&nbsp;&nbsp;<input name="authorizedMobileNo" id="authmobile" type="text" class="input_txt" value="9999999999"/></td>
                      </tr>
					 			  
					
					  
					  <tr><td colspan="2"></td>
  <td height="60" align="left" valign="middle"><input name="Submit" class="buttxt" type="button" value="Submit" style="width:100px"/></td>
  </tr>
                </table>
			</td></tr>
					
					
        </table></td>
    </tr>
	 <tr>
	 <td align="left" height="130" width="1000">
      <%@ include file="footer.jsp"%>
	  </td>
	  </tr>
  </table>

</center>
</form>
</body>
</html>
