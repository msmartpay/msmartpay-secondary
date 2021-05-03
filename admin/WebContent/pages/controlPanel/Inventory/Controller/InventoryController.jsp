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
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	 
	 $( "#from, #to" ).datepicker({
        changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2013:2020',
            numberOfMonths: 1
	 });
  });
</script>

<script>
function editRecord(id){
	
	
	    var f = document.form;
	    f.method = "post";
	    f.action = 'InventoryController.action?id='+id+'&param=editRecord';
	    f.submit();
	  
}

function removeRecord(id,i)
{
	var val = parseInt(i);alert(val);
	
	var valueget = 'sno'+val; alert(valueget);
	
	var valuewehave = document.getElementById("sno"+val).value; alert(valuewehave);
	
	var r = confirm("Are you sure to remove no. "+valuewehave+" ?");
	
	if (r == true)
	  {
		var v = document.form;
		v.method = "post";
		v.action = 'InventoryController.action?id='+id+'&param=removeData';
		v.submit();
	  }
	else
	  {
	 	return false;
	  }
}
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

getDetails = (ArrayList)request.getAttribute("detailData");

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
<div id="mystickytooltip" class="stickytooltip" style="width: 500px; overflow-y: scroll; height: 300px;">

<% if (size > 0 )
{ %>    
<% for (int i=0;i<size;i++) 
       { 
           HashMap details = (HashMap) getDetails.get(i);
%>

<div id="sticky1<%=i %>" class="atip" style="width:500px" align="center" >

<table width="100%" align="center"  cellspacing="10"   class="mydata_tabl" >
<tr style="width:100%; height:auto; "><td colspan="2" style="width:100% height:auto;"><h3 class="heading_mgs1">Package Details</h3></td></tr>
<tr style="width:100%; height:auto; "><td style="width:10% height:auto;">Package ID :</td><td style="width:90% height:auto;"><%=details.get("Package_Id") %> </td></tr>
<tr style="width:100%; height:auto; "><td style="width:10% height:auto;"> Package Price Included :</td><td><%=details.get("Package_Price_Included") %> </td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Travel Type :</td><td style="width:70%; height:auto;">><%=details.get("Travel_Type") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Travel By :</td><td style="width:70%; height:auto;"><%=details.get("Travel_By") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Hotel Stay Duration :</td><td style="width:70%; height:auto;"><%=details.get("Hotel_Stay_Duration") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Hotel Name :</td><td style="width:70%; height:auto;"><%=details.get("Hotel_Name") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Hotel Rating :</td><td style="width:70%; height:auto;"><%=details.get("Hotel_Stars_Rating") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Place To View :</td><td style="width:70%; height:auto;"><%=details.get("Place_to_View") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Inclusion Hotel :</td><td style="width:70%; height:auto;"><%=details.get("Inclusion_Hotel") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Inclusion Transport :</td><td style="width:70%; height:auto;"><%=details.get("Inclusion_Transport") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;"> Package Itinerary :</td><td style="width:70%; height:auto;"><%=details.get("Package_Itinerary") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Special Requirements :</td><td style="width:70%; height:auto;"><%=details.get("Special_Requirements") %></td></tr> 
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Term & Conditions :</td><td style="width:70%; height:auto;"><%=details.get("Terms_Conditions") %></td></tr>
<tr style="width:100%; height:auto; "><td style="width:30%; height:auto;">Cancellation Policy :</td><td style="width:70%; height:auto;"><%=details.get("Cancellation_Policy") %></td></tr>
</table>

</div>
<%}	} %>
<div class="stickystatus"></div>
</div>
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
                      <td colspan="4" >
                      
                      <form name="submitForm" method="post" action="InventoryController.action">
                      	<input type="hidden" name="param" value="getDetails"/>
	                      <table cellpadding="0" cellspacing="0" width="500" align="center" border="0" class="mydata_tabl">
	                          <tr>                   
	                                                            
	                            <td width="209"><strong>Select Inventory Type </strong></td>                            
	                            <td width="291">
	                           	      <select id="req">             
			                               <option value="select">Select</option> 
			                               <option selected="selected" value="Holiday">Holiday</option> 
										   <!--<option value="Car Booking">Car Booking</option>--> 
			                           </select>
								</td>                                                        
	                             
	                          </tr>
							  <tr>                   
	                                                            
	                            <td width="209"><strong>From </strong></td>                            
	                            <td width="291">
	                           	      <input type="text" name="from" id="from" required/>
								</td>                                                        
	                             
	                          </tr>
							  <tr>                   
	                                                            
	                            <td width="209"><strong>To </strong></td>                            
	                            <td width="291">
	                           	      <input type="text" name="to" id="to" required/>
								</td>                                                        
	                             
	                          </tr>
	                          <tr>
	                            <td></td>
	                            <td align="left" height="40">
	                           <input type="submit" value="Submit" class="cls_btn" onclick="requestTypeList()"/></td>
	                          </tr>
	                        </table>
                        </form>
                        </td>
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
      <% if (size > 0 ){ %>
      				<form name="form" method="post">
                            <table cellspacing="0" cellpadding="0" width="96%" align="center" class="tbls"  bgcolor="#a74312" style="border-bottom:1px solid #930;border-left:1px solid #930;">
                                <tbody>
                                  <tr align="center" bgcolor="#993300">
                                     <td width="4%" height="25"><strong>S.N.</strong></td>
                                     <td width="13%"><strong>Package Id</strong></td>                                   
                                     <td width="10%"><strong>Arrival City</strong></td>       
                                     <td width="11%"><strong>Departure City</strong></td>  
                                     <td width="11%"><strong>Package Price</strong></td>
                                     <td width="12%"><strong>Hotel Budget</strong></td>
                                     <td width="22%"><strong>Package Title</strong></td> 
									 <td width="5%"><strong>Registration Date</strong></td>
									 <td width="9%"><strong>Edit</strong></td>
									 <td width="8%"><strong>Remove</strong></td> 
								 </tr>
	                       
							
					             <% for (int i=0;i<size;i++) 
					             { 
					             	HashMap details = (HashMap) getDetails.get(i);
					             %>
					                                 
					                                  <tr bgcolor="#FFFFFF" align="center">
					                                    <td ><%=i+1 %><input type="hidden" name="sno" id="sno<%=i+1 %>" value="<%=i+1 %>"/></td>
					                                    <td ><a data-tooltip="sticky1<%=i %>" style="cursor:pointer"><%=details.get("Package_Id") %></a></td>
					                                    <%	
					                                    	String arrivalcity = details.get("Arrival_City").toString(); 
					                                		String city = controlDao.getCityName(arrivalcity);
					                                	%>
					                                    
					                                    <td ><%=city%></td>
					                                    <%	
					                                    	String destcity = details.get("Destination_City").toString(); 
					                                		String des = controlDao.getCityName(destcity);
					                                	%>
					                                    <td ><%=des%></td>
					                                    <td ><%=details.get("Package_Price") %></td>
					                                    <td ><%=details.get("Hotel_Budget") %> </td>
					                                    <td ><%=details.get("Package_Title")%></td>
					                                    <td ><%=details.get("Reg_Date")%><input type="hidden" name="date" value="<%=details.get("Reg_Date") %>"/></td>
					                                    <td ><a href="#" onclick="editRecord(<%=details.get("Package_Id") %>);" ><img src="images/file6149.png" width="35" height="25" alt="Click here to edit" height="25"/></a></td>
					                                    <td ><a href="#" onclick="removeRecord(<%=details.get("Package_Id")%>,<%= i+1%>);"><img src="images/file904.png" alt="Click here to remove"/></a></td>                                    
					                                  </tr>
					             <%} %>      
             			    
                                 </tbody>
                              </table>
                      </form>
             <% } %>
              
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

