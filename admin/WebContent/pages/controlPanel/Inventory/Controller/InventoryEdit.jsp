<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList,com.controlPanel.inventoryMgt.Controller.ControllerDao"%>
<%@page import="java.util.Iterator"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
<style type="text/css">
#res{background:none; border:none; padding:5px 0 0 0; color:#a74312; font-family:"Trebuchet MS"; font-weight:bold;}

</style>

<script type="text/javascript" src="css/jquery.min.js"></script>
<script type="text/javascript" src="css/stickytooltip.js"></script>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
function request()
{
var a= document.getElementById("req").value;
document.getElementById("res").value =a;
}
//-->
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

						$( "#sourceCity,#destinationCity" ).autocomplete({
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
<%
String message="";
int portalId;
String usersId=(String)session.getAttribute("userId");

if(!usersId.equalsIgnoreCase("1"))
	portalId=Integer.parseInt((String)session.getAttribute("adminUserPortalId"));

message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

ArrayList getDetails = new ArrayList();

getDetails = (ArrayList)request.getAttribute("editdetailData");

if(getDetails == null)
{
	getDetails = new ArrayList();
}
int size = 0;
size = getDetails.size();	System.out.println("size-----"+size);

final ControllerDao controlDao;

	controlDao = ControllerDao.getInstance();

%>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    
    
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
                    <tr>
                      <td  width="50%" valign="bottom" height="40" class="heading_mgs">Controller Inventory Package </td>                      
                   
                    </tr>                   
                    
                    <tr>
                      <td valign="top" align="center">
                      <table cellpadding="0" cellspacing="0" width="96%" align="center" border="0"  id="border">
                          <tr>
                         
                            <td  width="50%" valign="bottom" height="31" class="heading_mgs" >&nbsp;</td>
                             
                              <td  width="50%" valign="bottom" height="31" class="heading_mgs">&nbsp;</td>
                             
                          </tr>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                          <tr>
						  <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
   
                            <td colspan="5" valign="top">
                            <form name="formData" method="post" action="InventoryController.action?param=updateDetails">
      <% if (size > 0 ){ %>
      				<% for (int i=0;i<size;i++) 
					             { 
					             	HashMap details = (HashMap) getDetails.get(i);
					             %>
					             <%	
					                      String arrivalcity = details.get("Arrival_City").toString(); 
					                      String city = controlDao.getCityName(arrivalcity);
					             %>
					             <%	
					                      String departcity = details.get("Destination_City").toString(); 
					                      String depart = controlDao.getCityName(departcity);
					             %>
                            <table cellspacing="0" cellpadding="0" height="auto" width="80%" align="center" class="tbls"  bgcolor="#a74312" style="border-bottom:1px solid #930;border-left:1px solid #930;">
                                <tbody>
								   <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Holiday Package Type</strong></td>
                                     <td align="center" width="4%" style="background-color: white;">:</td>
                                     <td width="54%" style="background-color: white;" align="left"><input type="text" name ="packtype" value="<%=details.get("Holiday_Package_Type") %>" required/></td>                                  
                                  </tr>
								  
                                  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Package Id</strong></td>
                                     <td align="center" width="4%" style="background-color: white;">:</td>
                                     <td width="54%" style="background-color: white;" align="left"><input type="text" name ="pid" value="<%=details.get("Package_Id") %>" readonly="readonly" required/></td>                                  </tr>
									 
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Package Price</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="packprice" value="<%=details.get("Package_Price") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td  width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Package Title</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="packtitle" value="<%=details.get("Package_Title") %>" required/></td>
								  </tr>
								  
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Travel By</strong></td>
                                     <td align="center" width="4%" style="background-color: white;">:</td>
                                     <td width="54%" style="background-color: white;" align="left"><input type="text" name ="travelby" value="<%=details.get("Travel_By") %> required"/></td>                                  
                                  </tr>
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Travel Type</strong></td>
                                     <td align="center" width="4%" style="background-color: white;">:</td>
                                     <td width="54%" style="background-color: white;" align="left"><input type="text" name ="ttype" value="<%=details.get("Travel_Type") %> required"/></td>                                  
                                  </tr>
								  
                                  <tr  align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Arrival City</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td style="background-color: white;" align="left"><input type="text" id="destinationCity" name ="arrivalcity" value="<%=city %>" required/></td>    
                                  </tr>
                                  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Departure City</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td style="background-color: white;" align="left"><input type="text" id="sourceCity" name ="departcity" value="<%=depart %>" required/></td> 
                                  </tr>
                                 
                                  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Hotel Budget</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="budgethotel" value="<%=details.get("Hotel_Budget") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Hotel Stay Duration</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="hotelstayduration" value="<%=details.get("Hotel_Stay_Duration") %>" required/></td>
                                  </tr>
								  
                                  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Hotel Name</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="hotelname" value="<%=details.get("Hotel_Name") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Hotel Rating</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="hotelrating" value="<%=details.get("Hotel_Stars_Rating") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Meal Preferences</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="meal" value="<%=details.get("Meal_Preferences") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Sight Seeing</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="sightseeing" value="<%=details.get("Sight_Seeing") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Meal Included</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="mealincluded" value="<%=details.get("Meal_Included") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Place to View</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><input type="text" name ="palcetoview" value="<%=details.get("Place_to_View") %>" required/></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Place Description</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;">
									   <textarea name ="placedescription" ><%=details.get("Description_Place") %></textarea>
									 </td>
								  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Inclusion Hotel</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="inclusionhotel" ><%=details.get("Inclusion_Hotel") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Inclusion Transport</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="inclutransport" ><%=details.get("Inclusion_Transport") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Inclusion Others</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="incluother" ><%=details.get("Inclusion_Others") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Package Itinerary</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="packItinerary" ><%=details.get("Package_Itinerary") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Special Requirements</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="spclReqt" ><%=details.get("Special_Requirements") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Terms & Conditions</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="termnCond" ><%=details.get("Terms_Conditions") %></textarea></td>
                                  </tr>
								  
								  <tr align="center">
                                     <td width="42%" align="center" style="background-color: white;"><strong style="color: #993300;">Cancellation Policy</strong></td>
                                     <td align="center" style="background-color: white;">:</td>
									 <td align="left" style="background-color: white;"><textarea name ="canclpolicy"><%=details.get("Cancellation_Policy") %></textarea></td>
                                  </tr>
								                   
					             <%} %>      
                                 </tbody>
                                 <tr>
		                            <td></td><td style="border:0px; "></td>
		                            <td align="left" height="40">
		                           <input type="submit" value="Update Details" class="cls_btn" style="width:30%;"/></td>
	                          	</tr>
                              </table>
             <% } %>
              				</form>
                             </td>
                          </tr>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                          		
								
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="74"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
						
</body>
</html>

