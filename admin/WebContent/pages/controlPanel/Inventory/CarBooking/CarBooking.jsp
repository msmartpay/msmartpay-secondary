<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import = "java.util.* "%> 

<link media="all" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css" rel="stylesheet" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" ></script>

<script type="text/javascript">
$(function() {
$( "#datepicker" ).datepicker({

minDate: "today",
numberOfMonths: 1,


});
});
</script>


<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>

</head>
<body>
<%
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}


%>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
<%
String loginType=(String)session.getAttribute("loginType");
%>
  <tr>
    <td valign="top" align="center" height="378">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  >
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
          
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                <strong>Control Panel>Inventory Management>Car Booking</strong>
                </td>
              </tr>
              <tr>
                <td colspan="4"  align="center" class="dyn_mgs"><%=message%></td>
              </tr>
              
              <tr>
                <td valign="top" align="center">
                <form  name="userRegistrationForm" method="post">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" id="border" class="mydata_tabl">
                      <tr>
                        <td height="29" colspan="4" align="center" ></td>
                      </tr>
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Car Booking</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><select name="carBooking" >
                            
                            <option value="select" selected="selected">Select</option>                            
                            <option value="Domestic">Domestic</option>   
                            <option value="International">International</option> 
                                               
                       
                          </Select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                      <td width="24%"></td>
                      
                        <td width="17%" height="30" align="left" valign="middle"><strong>Pick Up City</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="pickUpCity" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Booking Type</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><select name="bookingType" >
                            
                            <option value="select" selected="selected">Select</option>                            
                            <option value="Local Usage">Local Usage</option>   
                            <option value="Out Station">Out Station</option> 
                            <option value="Airport & Railway Transfer">Airport & Railway Transfer</option>                        
                       
                          </Select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td height="30" align="left" valign="middle"><strong>Pick Up Date</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" id="datepicker" name="pickUpDate" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Usage Type</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text"  tooltipText="40 Kms/ 4 Hrs" name="usageType" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Segment</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><select name="segment" >
                            
                            <option value="select" selected="selected">Select</option>                            
                            <option value="Economic">Economic</option>   
                            <option value="Standard (Sedan)">Standard (Sedan)</option> 
                            <option value="Full Size (SUV)">Full Size (SUV)</option> 
                            <option value="Luxury">Luxury</option>    
                            <option value="Other">Other</option>                     
                       
                          </Select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Car Name</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="carName" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Vendor Name</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="vendorName" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Trip Price</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="tripPrice" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Seating Capacity</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%">
                        <select name="seatingCapacity">
                        <option value="select" selected="selected">Select</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        </select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Air Condition</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"> <select name="airCondition">
                        <option value="select" selected="selected">Select</option>
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                        </select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Stereo</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><select name="stereo">
                        <option value="select" selected="selected">Select</option>
                        <option value="Y">Y</option>
                        <option value="N">N</option>
                        </select>
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Terms & Conditions - 1</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="tcOne" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td height="30" align="left" valign="middle"><strong>Terms & Conditions - 2</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="tcTwo" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
                      
                      <tr>
                      <td width="24%"></td>
                        <td  height="30" align="left" valign="middle"><strong>Cancellation Policy</strong></td>
                        <td align="center" width="5%">:</td>
                        <td valign="middle" width="28%"><input type="text" name="canPolicy" />
					    </td>
                        <td width="26%" align="left" valign="middle"></td>
                      </tr>
					 
                      <tr>
                      <td width="24%"></td>
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
var carBooking = document.userRegistrationForm.carBooking.value;
if(carBooking=="select")
{
	alert("Please Select Car Booking.");
	document.userRegistrationForm.carBooking.focus();
	return false;
}
var pickUpCity = document.userRegistrationForm.pickUpCity.value;
if(pickUpCity=="")
{
	alert("Please Enter Pick Up City.");
	document.userRegistrationForm.pickUpCity.focus();
	return false;
}
var bookingType = document.userRegistrationForm.bookingType.value;
if(bookingType=="select")
{
	alert("Please Select Booking Type.");
	document.userRegistrationForm.bookingType.focus();
	return false;
}
var pickUpDate = document.userRegistrationForm.pickUpDate.value;
if(pickUpDate=="")
{
	alert("Please Enter Pick Up Date.");
	document.userRegistrationForm.pickUpDate.focus();
	return false;
}
var usageType = document.userRegistrationForm.usageType.value;
if(usageType=="")
{
	alert("Please Enter Usage Type.");
	document.userRegistrationForm.usageType.focus();
	return false;
}
var segment = document.userRegistrationForm.segment.value;
if(segment=="select")
{
	alert("Please Select Segment.");
	document.userRegistrationForm.segment.focus();
	return false;
}
var carName = document.userRegistrationForm.carName.value;
if(carName=="")
{
	alert("Please Enter Car Name.");
	document.userRegistrationForm.carName.focus();
	return false;
}
var vendorName = document.userRegistrationForm.vendorName.value;
if(vendorName=="")
{
	alert("Please Enter Vendor Name.");
	document.userRegistrationForm.vendorName.focus();
	return false;
}
var tripPrice = document.userRegistrationForm.tripPrice.value;
if(tripPrice=="")
{
	alert("Please Enter Trip Price.");
	document.userRegistrationForm.tripPrice.focus();
	return false;
}
var seatingCapacity = document.userRegistrationForm.seatingCapacity.value;
if(seatingCapacity=="select")
{
	alert("Please Select Seating Capacity.");
	document.userRegistrationForm.seatingCapacity.focus();
	return false;
}
var airCondition = document.userRegistrationForm.airCondition.value;
if(airCondition=="select")
{
	alert("Please Select Air Condition.");
	document.userRegistrationForm.airCondition.focus();
	return false;
}
var stereo = document.userRegistrationForm.stereo.value;
if(stereo=="select")
{
	alert("Please Select Stereo.");
	document.userRegistrationForm.stereo.focus();
	return false;
}
var tcOne = document.userRegistrationForm.tcOne.value;
if(tcOne=="")
{
	alert("Please Enter Term Condition First.");
	document.userRegistrationForm.tcOne.focus();
	return false;
}
var tcTwo = document.userRegistrationForm.tcTwo.value;
if(tcTwo=="")
{
	alert("Please Enter Term Condition Second.");
	document.userRegistrationForm.tcTwo.focus();
	return false;
}
var canPolicy = document.userRegistrationForm.canPolicy.value;
if(canPolicy=="")
{
	alert("Please Enter Cancellation Ploicy.");
	document.userRegistrationForm.canPolicy.focus();
	return false;
}

document.userRegistrationForm.action="InventoryMgt.action?param=saveCarDetails";
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
<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEE');
tooltipObj.setCloseMessage('Exit');
tooltipObj.initFormFieldTooltip();
</script>