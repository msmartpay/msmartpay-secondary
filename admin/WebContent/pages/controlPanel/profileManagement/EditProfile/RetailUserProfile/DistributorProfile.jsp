<%@ page import = "java.util.HashMap "%> 
<%@ page import = "java.util.ArrayList "%> 
<%

HashMap distributorProfileDetailsMap=(HashMap)session.getAttribute("distributorProfileDetails");
System.out.println("We are here profile data is "+distributorProfileDetailsMap);
//ArrayList distributorTurnOverDetails=(ArrayList)session.getAttribute("distributorTurnOverDetails");
//System.out.println("We are here distributorTurnOverDetails is "+distributorTurnOverDetails);
String profileDistributorId=((Integer)session.getAttribute("profileDistributorId")).toString();
String distributorIdFlag=(String)session.getAttribute("distributorIdFlag");
session.setAttribute("profileDistributorId",distributorProfileDetailsMap.get("distributorId"));
session.setAttribute("distributorIdFlag",distributorProfileDetailsMap.get("distributorIdFlag"));
String message=(String)request.getAttribute("message");
if(message==null)message="";


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
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



<link rel="icon" href="images/t.png" />

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script>
$(document).ready(function(){

	$("#email_id").click(function(){
		
		document.getElementById('p_1').style.display="block";
	document.getElementById('p_2').style.display="none";
		})
	
	$("#mobile_id").click(function(){
		
		document.getElementById('p_2').style.display="block";
	document.getElementById('p_1').style.display="none";

		})
		
		
		
		
		$('.toggle2').toggle();
		$("#click_user").click(function(){
			
			 $('.toggle1').toggle();

			
			})
			
		$("#click_turnover").click(function(){
			
			 $('.toggle2').toggle();
			
			})
			
			
			
			$("#show_1").click(function(){
		
		$("#p_text").css("display","");
	$("#show_1").css("display","none");
	
	

		})
		
		
	
	})

</script>


</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
      <tr>
        <td valign="top" align="center" class="rounded-corners">
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
        <tr>
            <td width="100%" colspan="100%"  align="center">
            
            <!--text matter of checking mail & mobile number-->
            
            <p id="p_1" style="display:none; font-size:18px; color:#F00; padding:0; margin:auto; font-family:'Trebuchet MS';">Email ID Incurrect</p>
            
            <p id="p_2" style="display:none; font-size:18px; color:#F00;padding:0; margin:auto; font-family:'Trebuchet MS';">Mobile Incurrect</p>
            
            </td>
          </tr>
		   <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
		  <tr>
		  	<td width="100%" colspan="100%" align="center"><div style="width:290px; text-align:center;font-family:'Trebuchet MS'; height:20px;" id="resultEmail"></div></td>
		  </tr>
		  
		  <tr>
            <td width="100%" height="20"  align="center"></td>
          </tr>
          <tr>
            <td width="100%">
            <table width="85%" border="0" cellspacing="0" cellpadding="0" align="center" class="form11">
              <tr>
                <td width="40%" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td width="100%" align="left" valign="top">
                    <table class="mydata_tabl" width="100%" border="0" cellspacing="0" cellpadding="0" align="left" bgcolor="#a74312">
                      <tr class="hd">
                        <td colspan="100%" align="left" height="30" style="padding-left:16px;"><strong style="color:#FFF;">Login Information</strong></td>
                      </tr>
                      
                      <tr bgcolor="#ffffff">
                        <td width="5%"></td>
                        <td width="10%" height="30" align="left">User ID</td>
                        <td width="15%" align="center">:</td>
                        <td width="30%"  align="left"><%=distributorProfileDetailsMap.get("userName")%></td>
                        <td width="30%" align="right"><input class="cls_btn" type="button" value="Reset" /></td>
                        <td width="5%"></td>
                      </tr>
                      
                      <tr bgcolor="#ffffff">
                        <td></td>
                        <td height="30" align="left">Password</td>
                        <td align="center">:</td>
                        <td align="left"><%=distributorProfileDetailsMap.get("password")%></td>
                        <td align="right"><a href="distributorProfile.action?param=resetPassword&distributorId=<%=profileDistributorId%>&distributorIdFlag=<%=distributorIdFlag%>&completeDistributorId=<%=distributorProfileDetailsMap.get("completeDsID")%>">
						<input type="button" class="cls_btn" value="Reset" /></a></td>
                        <td></td>
                      </tr>
                      
					  
                      <tr bgcolor="#ffffff">
                        <td></td>
                        <td height="30" align="left">MPIN</td>
                        <td align="center">:</td>
                        <td align="left">XXXXXX</td>
                        <td align="right"><input type="button" class="cls_btn" value="Reset" /></td>
                        <td></td>
                      </tr>
                      
                      <tr bgcolor="#ffffff">
                        <td></td>
                        <td height="30" align="left">APIN</td>
                        <td align="center">:</td>
                        <td align="left">XXXXXX</td>
                        <td align="right"><input type="button" class="cls_btn" value="Reset" /></td>
                        <td></td>
                      </tr>
                      
                      <tr bgcolor="#ffffff">
                        <td></td>
                        <td height="30" align="left">Get OTP</td>
                        <td align="center">:</td>
                        <td align="left">XXXXXX</td>
                        <td align="right"><input type="button" class="cls_btn" value="Reset" /></td>
                        <td></td>
                      </tr>
                      
                       <tr bgcolor="#ffffff">
                        <td></td>
                        <td height="30" align="left"></td>
                        <td align="center"></td>
                        <td align="left"></td>
                        <td align="right"></td>
                        <td></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="20"></td>
                  </tr>
                  <tr>
                    <td width="100%" align="left" valign="top">
                    <table class="mydata_tabl" width="100%" border="0" cellspacing="0" cellpadding="0" align="left" bgcolor="#a74312">
                      <tr class="hd">
                        <td colspan="3" align="left" height="30" style="padding-left:21px;"><strong style="color:#FFF;">User Account Detail</strong></td>
                        <td colspan="1" align="right" height="30" style="padding-right:21px;color:#FFF; font-size:11px; cursor:pointer; font-family:'Trebuchet MS';" id="click_user"><strong style="color:#FFF;">Click Here</strong></td>
                      </tr>
                      
                      <tr class="toggle1" bgcolor="#ffffff">
                        <td width="5%" align="center"></td>
                        <td width="53%" height="30" align="left">Registration Date</td>
                        <td width="9%" align="center">:</td>
                        <td width="39%" align="left"><%=distributorProfileDetailsMap.get("dateOfJoining")%></td>
                      </tr>
                      
                      <tr class="toggle1" bgcolor="#ffffff">
                        <td width="2%" align="center"></td>
                        <td width="42%" height="30" align="left">Last Date of Transaction</td>
                        <td width="9%" align="center">:</td>
						<%if(distributorProfileDetailsMap.get("lastDateOfTransaction")==null){ %>
                        <td width="39%" align="left">-</td>
                        <%}else{ %>
                         <td width="39%" align="left"><%=distributorProfileDetailsMap.get("lastDateOfTransaction")%></td>
						 <%}%>
                      </tr>
                     
                      <tr class="toggle1" bgcolor="#ffffff">
                        <td width="2%" align="center"></td>
                        <td width="42%" height="30" align="left">Last Balance Date</td>
                        <td width="9%" align="center">:</td>
						<%if(distributorProfileDetailsMap.get("lastDateOfTBTaken")==null){ %>
                        <td width="39%" align="left">-</td>
                        <%}else{ %>
                          <td width="39%" align="left"><%=distributorProfileDetailsMap.get("lastDateOfTBTaken")%></td>
						<%}%>
                      </tr>
                        <tr class="toggle1" bgcolor="#ffffff">
                        <td width="2%" align="center"></td>
                        <td width="42%" height="30" align="left">Last Transaction Value</td>
                        <td width="9%" align="center">:</td>
                          <td width="39%" align="left"><%=distributorProfileDetailsMap.get("lastAmount")%></td>
                      </tr>
                      <tr class="toggle1" bgcolor="#ffffff">
                        <td width="2%" align="center"></td>
                        <td width="42%" height="30" align="left">Current Balance</td>
                        <td width="9%" align="center">:</td>
                        <td width="46%" align="left"><%=distributorProfileDetailsMap.get("distributorBalance")%></td>
                      </tr>
                      
                      <tr class="toggle1" bgcolor="#ffffff">
                        <td width="2%" align="center"></td>
                        <td width="42%" height="30" align="left">Cuttoff Amount</td>
                        <td width="9%" align="center">:</td>
                        <td width="46%" align="left"><%=distributorProfileDetailsMap.get("distributorCuttoffAmount")%></td>
                      </tr>
                       <tr class="toggle1" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td height="10" align="left"></td>
                        <td align="center"></td>
                        <td align="left"></td>
                      </tr>
                    </table></td>
                  </tr>
                  <tr>
                    <td height="20"></td>
                  </tr>
                  <tr>
                    <td width="100%" align="left" valign="top">
                    <table  class="mydata_tabl" width="100%" border="0" cellspacing="0" cellpadding="0" align="left" bgcolor="#a74312">
                      <tr class="hd">
                        <td colspan="3" align="left" height="30" style="padding-left:21px;"><strong style="color:#FFF;">Turover Statistics</strong></td>
                        <td colspan="1" align="right" height="30" style="padding-right:21px;color:#FFF; font-size:11px; cursor:pointer; font-family:'Trebuchet MS';" id="click_turnover"><strong style="color:#FFF;">Click Here</strong></td>
                      </tr>
                      
                      <tr class="toggle2" bgcolor="#cc9933">
                        <td width="5%" align="center"></td>
                        <td width="33%" height="30" align="left" style="color:#FFF; font-size:13px; font-family:'Trebuchet MS'; font-weight:bold;">Services</td>
                        <td width="20%" align="center">:</td>
                        <td width="39%" align="left" style="color:#FFF;font-size:13px; padding-left:30px; font-family:'Trebuchet MS'; font-weight:bold;">Turnover %</td>
                      </tr>
                      
                      <tr class="toggle2" bgcolor="#ffffff">
                        <td  align="center"></td>
                        <td  align="left">Flight</td>
                        <td  align="center">:</td>
                        <td  align="left" style="padding-left:57px;">1%</td>
                      </tr>
                      <tr class="toggle2" bgcolor="#ffffff">
                        <td  align="center"></td>
                        <td   align="left">Bus</td>
                        <td  align="center">:</td>
                        <td  align="left" style="padding-left:57px;">4%</td>
                      </tr>
                      <tr class="toggle2" bgcolor="#ffffff">
                        <td  align="center"></td>
                        <td   align="left">Hotel</td>
                        <td  align="center">:</td>
                        <td align="left" style="padding-left:57px;">0% </td>
                      </tr>
                      <tr class="toggle2" bgcolor="#ffffff">
                        <td  align="center"></td>
                        <td  align="left">Recharge</td>
                        <td align="center">:</td>
                        <td  align="left" style="padding-left:57px;">0%</td>
                      </tr>
                      
                      <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">Utility</td>
                        <td align="center">:</td>
                        <td align="left" style="padding-left:57px;">9%</td>
                      </tr>
                      
                       <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">PayCards</td>
                        <td align="center">:</td>
                        <td align="left" style="padding-left:57px;">2%</td>
                      </tr>
                      
                       <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">TestMerit</td>
                        <td align="center">:</td>
                        <td align="left" style="padding-left:57px;">1%</td>
                      </tr>
                      
                       <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">DTH-X</td>
                        <td align="center">:</td>
                        <td align="left" style="padding-left:57px;">0%</td>
                      </tr>
                      
                       <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">Account Adjustment</td>
                        <td align="center">:</td>
                        <td align="left" style="padding-left:57px;">46%</td>
                      </tr>
                        <tr class="toggle2" bgcolor="#ffffff">
                        <td align="center"></td>
                        <td  align="left">&nbsp;</td>
                        <td align="center">&nbsp;</td>
                        <td align="left" style="padding-left:57px;">&nbsp;</td>
                      </tr>
                      
                    </table>
                    
                    </td>
                  </tr>
                </table></td>
                <td width="3%" valign="top">&nbsp;</td>
                <td width="57%" valign="top">
                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                  <tr>
                    <td width="100%" valign="top">
					<form name="registration" method="post" enctype="multipart/form-data">
                    <table width="100%" class="mydata_tabl" border="0" cellspacing="0" cellpadding="0" align="left" bgcolor="#a74312">
                      <tr bgcolor="#a74312">
                       <th>
                        <td colspan="2" align="left" height="30" style="font-weight:bold; font-size:13px;"> <font color="#FFFFFF">Manage Profile</font></td>
                        <td colspan="2" align="right" height="30">
                        <a href="viewHierarchy.action?type=ds&hierarchy=lower&status=All&userId=<%=distributorProfileDetailsMap.get("distributorId")%>" target="_parent" style="text-decoration:none; margin-right:49px;">
                          <input name="button" type="button" class="cls_btn" value="Hierarchy"  />
                        </a>
                           </td>
                         </th>
                      </tr>
                      
                        <tr  bgcolor="#fff">
                              <td width="7%"></td>
                              <td width="30%"  height="30" align="left"  class="form11">First Name </td>
                              <td width="7%" align="right">:</td>
                              <td width="55%"  align="center"><input type="text" name="firstName" id="firstname" value="<%=distributorProfileDetailsMap.get("firstName")%>" tooltipText="Type First Name in this box" />                                </td>
							</tr>
                            
                            <tr  bgcolor="#fff"s>
                              <td></td>
                              <td height="30" align="left"  class="form11">Last Name </td>
                              <td align="right">:</td>
                              <td align="center"><input type="text" name="lastName" id="lastName" value="<%=distributorProfileDetailsMap.get("lastName")%>" tooltipText="Type Last Name in this box" /> </td>
							</tr>
            
                             <tr bgcolor="#fff">
                              <td></td>
                              <td height="30" align="left"  class="form11">Date of Birth </td>
                              <td align="right">:</td>
                              <td align="center">
                               <!--<input name="dob"  type="text" class="tcal" id="dob" size="15" readonly="readonly"/>-->
                               <input name="dob" type="text"  id="datepicker" value="<%=distributorProfileDetailsMap.get("dateOfBirth")%>" readonly="true"/>
                              </td>
							</tr>
                              
                              <tr bgcolor="#fff">
                              <td></td>
                              <td height="30" align="left"  class="form11">Gender </td>
                              <td align="right">:</td>
                              <td align="center">
                              <select name="gender" id="gender">
	   								<option selected="selected" value="<%=distributorProfileDetailsMap.get("gender")%>" ><%=distributorProfileDetailsMap.get("gender")%></option>
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
    						   </select>
                              </td>
                            </tr>
                            
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">Company/ Firm Type </td>
                              <td align="right">:</td>
                              <td align="center"> <select name="companyType" id="firmtype" onchange="changeStyleCompanyType()" >
                                <option value="<%=distributorProfileDetailsMap.get("companyType")%>" selected="selected"><%=distributorProfileDetailsMap.get("companyType")%></option>
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
                             <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">Company/ Firm/ Shop Name </td>
                              <td align="right">:</td>
                              <td align="center"><input name="companyName" id="firmname" value="<%=distributorProfileDetailsMap.get("companyName")%>" type="text" tooltipText="Type Company/ Firm/ Shop Name in this box" /></td>
                            </tr> 
                            
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">Email ID
                               <a href="#" onclick="checkUserName('<%=distributorProfileDetailsMap.get("distributorId")%>')" >(Check)</a>
                               </td>
                              <td align="right">:</td>
                              <td align="center">
                            <input  name="emailId"  type="email" value="<%=distributorProfileDetailsMap.get("emailID")%>" /> 
                          
                           </td>		
                            </tr>
                               
                            <tr bgcolor="#fff">
                                  <td></td>
                                  <td align="left" height="30"  class="form11">Mobile Number
                                   <a href="#" onclick="checkUserName1('<%=distributorProfileDetailsMap.get("distributorId")%>')" >(Check)</a>
                                    </td>
                                  <td align="right">:</td>
                                  <td align="center">
                             <input name="mobileNo" id="Omobile" type="text" value="<%=distributorProfileDetailsMap.get("mobileNo")%>" onkeypress="return digitonly(this,event)" maxlength="10" />
                             
                                  </td>
                                </tr>
                                <tr bgcolor="#fff">
                            <th colspan="100%" align="center">
                            <h3 align="left" style="background:#993300;padding-top:5px;padding-left:42px;height:24px;color:#FFF;font-size:13px;">Address</h3></th>
                            </tr>
                            <tr bgcolor="#fff">
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 1 </td>
                              <td align="right">:</td>
                              <td align="center">
							  <input name="addressLine1" id="Oaddressline1" type="text"  tooltipText="Type Address 1 in this box" value="<%=distributorProfileDetailsMap.get("address1")%>"/></td>
                            </tr>
                            <tr bgcolor="#fff">
                              <td></td>
                              <td height="30" align="left"  class="form11">Address Line 2 </td>
                              <td align="right">:</td>
                              <td align="center">
							  <input name="addressLine2" id="Oaddressline2" type="text"  tooltipText="Type Address 2 in this box" value="<%=distributorProfileDetailsMap.get("address2")%>"/></td>
                            </tr>
							
							<tr bgcolor="#fff">
                              <td></td>
                              <td height="30" align="left"  class="form11">Country</td>
                              <td align="right">:</td>
                              <td align="center">
                              <select name="country" id="Ocountry" >
                              <option selected="selected">India</option>
                                </select>
                                </td>
							</tr>
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">State </td>
                              <td align="right">:</td>
                              <td align="center">
                             <select name="state" id="Ostate" onchange="populatecities('office')">
                                    <option selected="selected" value="<%=distributorProfileDetailsMap.get("state")%>"><%=distributorProfileDetailsMap.get("state")%></option>
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
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">District </td>
                              <td align="right">:</td>
                              <td align="center">
                              <select name="District" id="officeDist">
                              <option selected="selected" value="0">Select</option>
                                  <option>District</option>
                                  </select>
                                  </td>
                            </tr>
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">City </td>
                              <td align="right">:</td>
                              <td align="center"><input  name="city" type="text" value="<%=distributorProfileDetailsMap.get("city")%>" tooltipText="Type City name in this box" /></td>
                            </tr>
                            <tr bgcolor="#fff">
                              <td></td>
                              <td align="left" height="30"  class="form11">Pin Code </td>
                              <td align="right">:</td>
                              <td align="center">
                              <input type="text" name="pincode" id="Rpin" value="<%=distributorProfileDetailsMap.get("pincode")%>" onkeypress="return digitonly(this,event)" maxlength="6" tooltipText="Type Pin Code in this box" />
							  <input type="hidden" name="UpdateProfileds" value="<%=distributorProfileDetailsMap.get("distributorId")%>"/>
								  <input type="hidden" name="UpdateProfileCompleteds" value="<%=distributorProfileDetailsMap.get("completeDsID")%>"/>
                               </td>
                            </tr>
                            <tr>
                            <th colspan="100%" align="center">
                            <h3 align="left" style="background:#993300;height:24px;padding-left:42px;font-size:13px;padding-top:5px; margin:0;color:#FFF;">PAN Details</h3></th>
                            </tr>
                            
                             
                              <tr bgcolor="#fff">
                                <td></td>
                                <td align="left" height="30"  class="form11">PAN Number </td>
                                <td align="right">:</td>
                                <td align="center"><input name="panNo" id="staxno" value="<%=distributorProfileDetailsMap.get("panNo")%>" maxlength="12" type="text" tooltipText="Type PAN Number in this box" /></td>
                              </tr>
                    
                            <tr bgcolor="#fff">
                            <th colspan="100%" align="center">
							<input type="button" value="Submit" class="cls_btn" style="margin-top:8px;margin-right:70px;"  onclick="submitForm()" /></th>
                            </tr>
                            <tr bgcolor="#fff">
                            <th colspan="100%" align="center">&nbsp;</th>
                            </tr>
                      
                      
                    </table>
                    </form>
                    </td>
                  </tr>
                  <tr>
                    <td width="100%" height="50"></td>
                  </tr>
                  <tr>
                    <td width="100%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
                      <tr>
                        <td width="5%" align="center"></td>
                        <td width="32%" height="30" align="left"><a href="viewUserReport.action?type=ds&userId=<%=distributorProfileDetailsMap.get("distributorId")%>" target="_parent" style="text-decoration:none;">
                        <!--<input name="button" type="button" style="width:150px;" value="View Reports" />--></a></td>
                        <td width="60%" align="left"></td>
                      </tr>
                    </table></td>
                  </tr>
                </table>
				</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td width="100%" height="15"></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">

function submitForm(){
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
document.registration.action="distributorProfile.action?param=updateDSProfile";
document.registration.submit();
}



function checkUserName(ds)
{
createXMLHttpRequest();
var type=document.registration.emailId.value;
xmlHttp.onreadystatechange = printValuesEmail;


xmlHttp.open("POST","distributorProfile.action?param=checkEmailID&dsid="+ds+"&loginUserName="+type, true);
xmlHttp.send(null);
}

function checkUserName1(ds)
{
createXMLHttpRequest();
var type=document.registration.mobileNo.value;
xmlHttp.onreadystatechange = printValuesEmails;


xmlHttp.open("POST","distributorProfile.action?param=checkMobileNo&dsid="+ds+"&MobileNo="+type, true);
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
response1="Email ID is Unique."
resultEmail.innerHTML=response1;
resultEmail.style.color="green"
}
else
{
response1="Email ID is Duplicate."
resultEmail.innerHTML=response1;
resultEmail.style.color="red"
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

var resultEmail=document.getElementById('resultEmail');
if(response2=="valid")
{
response2="Mobile Number is Unique."
resultEmail.innerHTML=response2;
resultEmail.style.color="green"


}
else
{
response2="Mobile Number is Duplicate."
resultEmail.innerHTML=response2;
resultEmail.style.color="red"
}
}
}
}

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
</script>