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
dateFormat: "yy-mm-dd",
minDate: "today",
numberOfMonths: 1,


});
});
</script>
<script type="text/javascript">
            function updatePackagePriceTextInput(val) {
              document.getElementById('textInput').value=val; 
            }
        </script>

<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script>
function textCounter(field, countfield, maxlimit)
{
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else
countfield.value = maxlimit - field.value.length;
}
</script>
<script>
function textCounter1(field, countfield, maxlimit)
{
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else
countfield.value = maxlimit - field.value.length;
}
</script>
<script>
function textCounter2(field, countfield, maxlimit)
{
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else
countfield.value = maxlimit - field.value.length;
}
</script>
<script language="Javascript" src="js/jquery.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
    	  
    	$('#OutStation').hide();
        $('#Local').hide();
        $('#Transfer').hide();
        
        $('#BookingType').change(function () {
            var sel = $(this).val();
 			
            if (sel == '') {
                $('#OutStation').hide();
                $('#Local').hide();
                $('#Transfer').hide();
            }
            if (sel == 'OutStation') {
                $('#OutStation').show();
                $('#Local').hide();
                $('#Transfer').hide();
            }
            else if (sel == 'Local') {
                $('#Local').show();
                $('#OutStation').hide();
                $('#Transfer').hide();
            }
            else if (sel == 'Transfer') {
                $('#Transfer').show();
                $('#OutStation').hide();
                $('#Local').hide();
            }
        });
 
    }); 
 
    </script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/>
	<script src="//code.jquery.com/jquery-1.9.1.js"></script>
	<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
			<style>
            .ui-autocomplete-loading { background: white url('images/ui-anim_basic_16x16.gif') right center no-repeat; }
            </style>
            <style>
            #my-loder{ display:none;}
            </style>
            <script>
            $(function() {
				function log( message ) {
					$( "<div/>" ).text( message ).prependTo( "#log" );
					$( "#log" ).attr( "scrollTop", 0 );
				}
				var output="";
				$.ajax({
					url: "App-Data/Cities.xml",
					dataType: "xml",
					success: function( xmlResponse ) {
						var data = $( "City", xmlResponse ).map(function() {
						return {
						value: $( "cityname", this ).text() + "",
						id: $( "cityname", this ).text()
						};
						}).get();

						$( "#originCity,#destinationCity" ).autocomplete({
							source: data,
							minLength: 0,
							select: function(event, ui ) {
							log( ui.item ?
							"From: " + ui.item.value + ", City Code: " + ui.item.id :
							"Nothing selected, input was " + this.value );
						}
					});
				}
				});
            });
            </script>
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
					      <td height="35" colspan="4" align="center" class="dyn_mgs"><%=message%></td>
					  </tr>
		              <tr>
		                <td colspan="4"  align="center" class="dyn_mgs"></td>
		              </tr>
		              
		              <tr>
		                <td valign="top" align="center">
		                <form method="post" name="carBookingDetail" action="InventoryMgt.action" enctype="multipart/form-data">
		                	<input type="hidden" name="param" value="saveCarDetails"/>
		                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" id="border" class="mydata_tabl">
		                      <tr>
		                        <td colspan="5" height="20"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td width="18%"  height="30" align="right" valign="middle"><strong>Car Booking Type</strong></td>
		                        <td align="center" width="10%">:</td>
		                        <td valign="right" width="30%" align="right">
		                            <select name="carBooking" id="Nationality" required>
		                                <option value="" selected="selected">Select</option>                            
		                                <option value="Domestic">Domestic</option>   
		                                <option value="International">International</option> 
		                            </select>
								</td>
		                        <td width="27%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td width="18%" height="30" align="right" valign="middle"><strong>Booking Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="BookingType" id="BookingType" required>
		                                <option value="" selected="selected">Select</option>                            
		                                <option value="OutStation">OutStation</option>   
		                                <option value="Local">Local Usage</option>
		                                <option value="Transfer">Air/Rail Transfer</option>
		                            </select>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr id="OutStation">
		                        <td width="20%"></td>
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Trip Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="OutStationBookingType">
		                            	<option value="">Select</option>
		                                <option value="Round">Round</option>   
		                                <option value="OneWay">One Way</option>
		                                <option value="MultiCity">Multi City</option>
		                            </select>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr id="Local">
		                        <td width="20%"></td>
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Trip Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="LocalBookingType">
		                            	<option value="">Select</option>
		                                <option value="FullDay">Full Day</option>   
		                                <option value="HalfDay">Half Day</option>
		                            </select>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr id="Transfer">
		                        <td width="20%"></td>
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Trip Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="TransferBookingType">
		                            	<option value="">Select</option>
		                                <option value="AirPort">AirPort</option>   
		                                <option value="RailwayStation">Railway Station</option>
		                                <option value="Area/Hotel">Area/Hotel</option> 
		                            </select>
									</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Segment</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td  valign="middle" width="30%" align="right">
		                            <select name="segment" required>
		                                <option value="" selected="selected">Select</option>   
		                                <option value="Mini">Mini</option>                         
		                                <option value="Economic">Economic</option>  
		                                <option value="Compact">Compact</option> 
		                                <option value="Van/Mini">Van/Mini Van</option>
		                                <option value="Sedan">Standard (Sedan)/ Intermediate</option> 
		                                <option value="SUV">Full Size (SUV)</option> 
		                                <option value="Luxury">Luxury</option>
		                                <option value="Convertible">Convertible</option>    
		                                <option value="Other">Other</option>                     
		                            </select>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      
		                      <tr>
		                        <td width="20%"></td>
		                      
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Origin City</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <input type="text" name="originCity" id="originCity" placeholder="Enter city name here"/>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                      
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Destination City</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <input type="text" name="destinationCity" id="destinationCity" placeholder="Enter city name here"/>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      
		                      <tr>
		                        <td width="20%"></td>
		                      
		                        <td width="14%" height="30" align="right" valign="middle"><strong>Days of Availability</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="daysavail" class="userdayInput" id="days" required>
		                                <option value="1">1 day</option>
		                                <option value="2">2 days</option>
		                                <option value="3">3 days</option>
		                                <option value="4">4 days</option>
		                                <option value="5">5 days</option>
		                                <option value="6">6 days</option>
		                                <option value="7">7 days</option>
		                                <option value="8">8 days</option>
		                                <option value="9">9 days</option>
		                                <option value="10">10 days</option>
		                                <option value="11">11 days</option>
		                                <option value="12">12 days</option>
		                                <option value="13">13 days</option>
		                                <option value="14">14 days</option>
		                                <option value="15">15 days</option>
		                            </select>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Car Model Name</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <input type="text" name="carName" placeholder="Car Model Name" required/>
								</td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Vendor Name</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                        	<input type="text" name="vendorName" required/>
							    </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Seating Capacity</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <select name="seatingCapacity" required>
		                                <option value="" selected="selected">Select</option>
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
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
								<td width="20%"></td>
								<td height="30" align="right" valign="middle"><strong>Trip Price</strong></td>
								 <td align="center" width="4%">:</td>
								<td valign="middle" width="30%" align="right">
		                        <input type="range" name="rangeInput" min="1000" max="250000" onChange="updatePackagePriceTextInput(this.value);"/>                        </td>
								<td valign="bottom">
		                            <input type="number" pattern="[0-9]*" id="textInput" maxlength="6" name="TripPrice" value="600" style="width:60px;" align="left" required/>																																																							</td>
								<td width="10%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
								<td width="20%"></td>
								<td height="30" align="right" valign="middle"><strong>Upload Image</strong></td>
								<td align="center" width="4%">:</td>
								<td valign="middle" width="30%" align="right">
												 <input type="file" name="userImage" required/>
								</td>
								<td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Air Condition</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right"> 
		                        <select name="airCondition" required>
			                        <option value="" selected="selected">Select</option>
			                        <option value="Y">Y</option>
			                        <option value="N">N</option>
		                        </select>
							    </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Stereo</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
			                        <select name="stereo" required>
				                        <option value="" selected="selected">Select</option>
				                        <option value="Y">Y</option>
				                        <option value="N">N</option>
			                        </select>
							    </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Per/Km. Charge</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <input type="number" name="ChargePerKm" required pattern="[0-9]*" maxlength="4" min="8" max="200" placeholder="Rs./k.m."/>
		                        </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Advance Payment</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <input type="number" name="AdvancePayment" required pattern="[0-9]*" maxlength="6"/>
		                        </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Fuel Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                        	<select name="fuelType" required>
		                            	<option value="" selected="selected">Select</option>
				                        <option value="P">Petrol</option>
				                        <option value="D">Diesel</option>
				                        <option value="CNG">CNG</option>
				                   </select>
		                        </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Transmission Type</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                        	<select name="transmissionType">
		                            	<option value="M" selected="selected">Manual</option>
				                        <option value="A">Automatic</option>
				                    </select>
		                        </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Baggage Quantity</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                        	<select name="NofBaggage">
		                        		<option value="0" selected="selected">0</option>
		                        		<option value="1">1</option>
		                            	<option value="2">2</option>
		                                <option value="3">3</option>
		                                <option value="4">4</option>
		                                <option value="5">5</option>
				                    </select>
		                        </td>
		                        <td width="22%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Special Offers</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <textarea name="SpecialOffers" id="tick_mes" rows="5" cols="16" maxlength="1400" onKeyDown="textCounter(this.form.tick_mes,this.form.remLen,1400);"
										onKeyUp="textCounter(this.form.tick_mes,this.form.remLen,1400);">
		                            </textarea>
								</td>
		                        <td align="left" valign="bottom">
		                        	<input  style="width: 50px;" readonly type="text" name="remLen" size=3 maxlength=3 value="1500"/>characters&nbsp;left</td>
		                        <td width="10%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td height="30" align="right" valign="middle"><strong>Terms & Conditions</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="right" width="30%" align="right">
		                            <textarea name="termCondition" id="tick_mes1"  rows="5" cols="16" maxlength="1400" onKeyDown="textCounter1(this.form.tick_mes1,this.form.remLen1,1400);"
										onKeyUp="textCounter1(this.form.tick_mes1,this.form.remLen1,1400);">
		                            </textarea>
		                        </td>
		                        <td align="left" valign="bottom">
		                        	<input  style="width: 50px;" readonly type="text" name="remLen1" size=3 maxlength=3 value="1500"/>characters&nbsp;left</td>
		                        <td width="10%" align="left" valign="middle"></td>
		                      </tr>
		                      <tr>
		                        <td width="20%"></td>
		                        <td  height="30" align="right" valign="middle"><strong>Cancellation Policy</strong></td>
		                        <td align="center" width="4%">:</td>
		                        <td valign="middle" width="30%" align="right">
		                            <textarea name="canPolicy" id="tick_mes2" rows="5" cols="16" maxlength="1400" onKeyDown="textCounter2(this.form.tick_mes2,this.form.remLen2,1400);"
										onKeyUp="textCounter2(this.form.tick_mes2,this.form.remLen2,1400);">
		                            </textarea>
		                        </td>
		                        <td align="left" valign="bottom"><input  style="width: 50px;" readonly type="text" name="remLen2" size=3 maxlength=3 value="1500"/>characters&nbsp;left</td>
		                        <td width="10%" align="left" valign="middle"></td>
		                      </tr>
													 
		                      <tr>
		                      <td width="20%"></td>
		                        <td></td>
		                        <td></td>
		                        <td align="left" height="40" style=""><input name="Submit" type="submit" value="Submit" class="cls_btn"/></td>
		                        <td></td>
		                      </tr>
		                      <tr>
		                        <td colspan="5" height="20"></td>
		                      </tr>
		                    </table>
		                  </form>
		                  </td>
		              </tr>
		              <tr>
		                <td colspan="5" height="30"></td>
		              </tr>
            </table>
          </td>
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

<script language="text/javascript">

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