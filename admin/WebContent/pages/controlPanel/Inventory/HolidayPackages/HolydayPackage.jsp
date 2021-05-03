<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import = "java.util.* "%> 



</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
<%
String loginType=(String)session.getAttribute("loginType");

String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}
%>
  <tr>
    <td valign="top" align="center" height="378">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  >
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
         
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                <strong>Control Panel>Inventory Management>Holiday Packages</strong>
                </td>
              </tr>
              <tr>
                <td  colspan="4" align="center" class="dyn_mgs"><%=message%></td>
              </tr>
              
              <tr>
                <td valign="top" align="center">
                <form  name="userRegistrationForm" method="post">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" id="border" class="mydata_tabl">
                      <tr>
                        <td height="30" colspan="4"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td width="27%" height="30" align="left" valign="middle"><strong>Holidays Package</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><select name="holidayPkg" >
                            
                            <option value="select">Select</option>                            
                            <option value="Domestic">Domestic</option>   
                            <option value="International">International</option> 
                          </Select>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Destination City</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="destinationCity"  />
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Package Price</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="pkgPrice" />
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td height="30" align="left" valign="middle"><strong>Hotel Budget</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <select name="hotelBudget">
                        <option value="select">Select</option>
                        <option value="1000-3000">1000-3000</option>
                        <option value="3000-5000">3000-5000</option>
                        <option value="5000-10000">5000-10000</option>
                        <option value="above 10000">above 10000</option>
                        </select>
			</td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Duration</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <select name="duration">
                        <option value="select">Select</option>
                        <option value="Up to 4 Nights">Up to 4 Nights</option>
                        <option value="5 Nights to 7 Nights">5 Nights to 7 Nights</option>
                        <option value="Above 8 Nights">Above 8 Nights</option>
                         </select>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Meal Preference</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <select name="mealPreference">
                            <option value="select">Select</option>
                            <option value="xyz">XYZ</option>
                                                       
                          </Select>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Sight seeing</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <select name="sigthSeeing" >
                            <option value="select">Select</option>
                            <option value="xyz">XYZ</option>                      
                          </Select>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Special Requirement</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="specialReq" />
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Return Airfare with Airport Transfer</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" >
                        <select name="rtAfWthAptTran" >
                            <option value="select">Select</option>
                             <option value="xyz">XYZ</option>                          
                          </Select>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Package Itinerary - Day 1</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <textarea name="pkgItinerary">
                        </textarea>
						  </td>
                       
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Additional Information - T&C </strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" > 
                        <textarea name="tc">
                        </textarea>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Additional Information - Cancellation Policy</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <textarea name="canPolicy">
                        </textarea>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Inclusions - Hotel</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="hotelIn" />
						  </td>
                        
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td height="30" align="left" valign="middle"><strong>Inclusions - Transport</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="tranportIn" />
						  </td>
                        
                      </tr>
                      
                      <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Inclusions - Others</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle"><input type="text" name="otherIn" />
						  </td>
                        
                      </tr>
                      
                        <tr>
                      <td width="20%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Other Instructions</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle">
                        <textarea name="otherIns" >
                        </textarea>
						  </td>
                        <td width="20%" align="left" valign="middle"></td>
                      </tr>
					 
                      <tr>
                      <td width="20%"></td>
                        <td></td>
                        <td></td>
                        <td align="left" height="40" style=""><input name="Submit" type="button" value="Submit" class="cls_btn" onclick="submitForm()"/></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="4" height="20"></td>
                      </tr>
                    </table>
                  </form></td>
              </tr>
              <tr>
                <td colspan="5" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <!--footer-->

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>
<script type="text/javascript">
// code for risk dropdown 




function submitForm()
{
	var holidayPkg = document.userRegistrationForm.holidayPkg.value;
	if(holidayPkg=="select")
	{
	alert("Please Holiday Package.");
	document.userRegistrationForm.holidayPkg.focus();
	return false;
	}
	var destinationCity = document.userRegistrationForm.destinationCity.value;
	if(destinationCity=="")
	{
	alert("Please Enter Destination City.");
	document.userRegistrationForm.destinationCity.focus();
	return false;
	}
	var pkgPrice = document.userRegistrationForm.pkgPrice.value;
	if(pkgPrice=="")
	{
	alert("Please Enter Package Price.");
	document.userRegistrationForm.pkgPrice.focus();
	return false;
	}
	var hotelBudget = document.userRegistrationForm.hotelBudget.value;
	if(hotelBudget=="select")
	{
	alert("Please Select Hotel Budget.");
	document.userRegistrationForm.hotelBudget.focus();
	return false;
	}
	var duration = document.userRegistrationForm.duration.value;
	if(duration=="select")
	{
	alert("Please Select Duration.");
	document.userRegistrationForm.duration.focus();
	return false;
	}
	var mealPreference = document.userRegistrationForm.mealPreference.value;
	if(mealPreference=="select")
	{
	alert("Please Select Meal Preference.");
	document.userRegistrationForm.mealPreference.focus();
	return false;
	}
	var sigthSeeing = document.userRegistrationForm.sigthSeeing.value;
	if(sigthSeeing=="select")
	{
	alert("Please Select Sigth Seeing.");
	document.userRegistrationForm.sigthSeeing.focus();
	return false;
	}
	
	var rtAfWthAptTran = document.userRegistrationForm.rtAfWthAptTran.value;
	if(rtAfWthAptTran=="select")
	{
	alert("Please Select Return Airfare with Airport Transfer.");
	document.userRegistrationForm.rtAfWthAptTran.focus();
	return false;
	}

		
	document.userRegistrationForm.action="HolidayPage.action?param=saveHolidayDetails";
	document.userRegistrationForm.submit();
		
}

</script>
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

</script>
