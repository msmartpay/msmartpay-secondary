
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
 <%@ include file="/globaldata.jsp"%>
<%
String titlePage=(String)session.getAttribute("titlePage");

Calendar calendar=Calendar.getInstance();

int currentYr=calendar.get(Calendar.YEAR);

String message=(String)request.getAttribute("message");
System.out.println("message-- in jsp--"+message);
if(message==null)
message="";

String profilemessage=(String)session.getAttribute("profilemessage");
System.out.println("profilemessage-- in jsp--"+profilemessage);
if(profilemessage==null)
{
profilemessage="";
}
else
{
message=profilemessage;
}

HashMap DistributorProfile=(HashMap)request.getAttribute("DistributorProfile");


String DistributorMailId=(String)session.getAttribute("DistributorMailId");


%>
<script>
function r_yes()
{
	document.getElementById('tr_1').style.display="";
}

function r_no()
{
	document.getElementById('tr_1').style.display="none";
}

</script>
<script>    
 $(document).ready(function(){
 
 $(".upon").hide();

$("#click_h").click(function () {

$(".upon").slideToggle();

});

 })
 
  </script>
  <script language="javascript">
	
function populatecities(type)
{
var xmlhttp;
var state="";

state=document.getElementById("Ostate").value; 

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
	   
    document.getElementById("officeDist").innerHTML=xmlhttp.responseText;
	 
    }
  }
xmlhttp.open("post",url,true);
xmlhttp.send();
}
</script>

<script language="javascript">

function checkform()
{

var agencyName=document.regiestration.agencyName.value;
if(agencyName==""){
alert("Please Enter Company Name");
document.regiestration.agencyName.focus();
return false;
}
var Address1=document.regiestration.Address1.value.replace(/^\s+|\s+$/, '');
if(Address1==""){
alert("Please Enter Address.");
document.regiestration.Address1.focus();
return false;
}

var state=document.regiestration.state.value.replace(/^\s+|\s+$/, '');
if(state=="-1"){
alert("Please Select State.");
document.regiestration.state.focus();
return false;
}

var city=document.regiestration.city.value.replace(/^\s+|\s+$/, '');
if(city==""){
alert("Please Enter City.");
document.regiestration.city.focus();
return false;
}

var officePinCode=document.regiestration.officePinCode.value.replace(/^\s+|\s+$/, '');
if(officePinCode==""){
alert("Please Enter PinCode.");
document.regiestration.officePinCode.focus();
return false;
}


document.regiestration.submit();
return true;
}


function convertToCaps(input)
{

input.value=input.value.toUpperCase();

} 

function digitonly(input,evt)
{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) 
{
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) 
{
return true;
}

return false;
}


</script>

  

	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
	
	
	
	<!-- CSS
	================================================== -->
	<!-- Base + Vendors CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="css/fonts/entypo/css/entypo.css">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css" media="screen">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css" media="screen">
	<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen">

	<!-- Theme CSS-->
	<link rel="stylesheet" href="css/theme.css">
	<link rel="stylesheet" href="css/theme-elements.css">
	<link rel="stylesheet" href="css/animate.min.css">

   

  <!-- Head Libs -->
	<script src="vendor/modernizr.js"></script>

	
	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicons/favicon.png">
	<link rel="apple-touch-icon" sizes="120x120" href="images/favicons/favicon-120.png">
	<link rel="apple-touch-icon" sizes="152x152" href="images/favicons/favicon-152.png">
	
</head>
<body>

<%@ include file="/header.jsp" %>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Edit Profile</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5">
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row" align="centre">
                            
						<div class="col-md-6 col-md-offset-3">
							<div class="box">
							<form name="regiestration" method="post" action="doProfileAction.action"  >
    						<input type="hidden" name="param" value="DoUpdateDisProfile">
									<div class="form-group">
										<label class="pull-left"> Agency Name <span class="required">*</span></label>
										<input type="text" name="agencyName" value="<%=DistributorProfile.get("companyName")%>" class="form-control" onkeyup="convertToCaps(this);">
									</div>
									<div class="form-group">
										<label class="pull-left">Address Line 1<span class="required">*</span></label>
										<input type="text" name="Address1" value="<%=DistributorProfile.get("Address")%>" class="form-control" onkeyup="convertToCaps(this);">
									</div>
									<%
										String Adr2=(String)DistributorProfile.get("Address2");
										if(Adr2==null){
										Adr2="";
										}	
									%>
									<div class="form-group">
										<label class="pull-left">Address Line 2<span class="required">*</span></label>
										<input type="text" name="Address2" value="<%=Adr2%>" class="form-control" onkeyup="convertToCaps(this);">
									</div>
									<div class="form-group">
										<label class="pull-left">Country<span class="required">*</span></label>
										<select name="country" class="form-control">
          								<option>India</option>    
          								</select>
									</div>
									<div class="form-group">
									<label class="pull-left">State<span class="required">*</span></label>
									
									<select class="form-control" name="state" id="Ostate" onchange="populatecities('office')">
										<option value="<%=DistributorProfile.get("State")%>" selected="selected"><%=DistributorProfile.get("State")%></option>
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
                                    </select>
									</div>
									<div class="form-group">
									<label class="pull-left"> District <span class="required">*</span></label>	
										<select class="form-control" name="District"  id="officeDist">
															 <option><%=DistributorProfile.get("District")%></option>
                                                             
                                        </select>
									</div>
									
									<div class="form-group">
										<label class="pull-left"> City <span class="required">*</span></label>
										<input type="text"  name="city" class="form-control" value="<%=DistributorProfile.get("City")%>" title="Enter Your City Name" onkeyup="convertToCaps(this);">
									</div>
									
									<div class="form-group">
										<label class="pull-left"> Pin Code <span class="required">*</span></label>
										<input name="officePinCode" type="text" class="form-control" value="<%=DistributorProfile.get("PinCode")%>" title="Enter Your Pin Code" maxlength="6" onkeypress="return digitonly(this,event)">
									</div>
									
									<div class="form-group">
										<label class="pull-left"> Email <span class="required">*</span></label>
										<input readonly name="email" type="text" class="form-control" value="<%=DistributorProfile.get("emailId")%>" title="Enter Your Email ID"  maxlength="30">
									</div>
								 <%
										String Mob=(String)DistributorProfile.get("MobileNo");
										if(Mob==null){
										Mob="";
										}	
									%>
									<div class="form-group">
										<label class="pull-left"> Mobile No. <span class="required">*</span></label>
										<input readonly type="text" name="moblie" class="form-control" value="<%=Mob%>" title="Enter Your Mobile number" maxlength="10" onkeypress="return digitonly(this,event)">
									</div>
									<button type="submit" class="btn btn-primary btn-inline" onclick="checkform()">Update</button>&nbsp; &nbsp; &nbsp; 
									
								</form>
								<div >
								<table style="width: 100%;margin-top: 10px;">
								<tr>
								<th colspan="3" align="center"><h3> Account Information</h3>
								</th>
								</tr>
								<tr class="upon" >
							    <td style="width: 45%;" align="left"><strong>DS Login ID</strong> </td>
							    <td align="right">:</td>
							    <td align="left" style="padding-left:50px;"><%=DistributorMailId%></td>
							    </tr>
								<tr class="upon" >
							    <td style="width: 10%;" align="left"><strong>Distributor Name</strong> </td>
							    <td align="right">:</td>
							    <td align="left" style="padding-left:50px;"><%=DistributorProfile.get("DistributorName")%></td>
							    </tr>
								<tr class="upon" >
							    <td style="width: 45%;" align="left"><strong>MD ID</strong> </td>
							    <td align="right">:</td>
							    <td align="left" style="padding-left:50px;"><%=DistributorProfile.get("MDID")%></td>
							    </tr>
								
								</table>
								</div>
								
								</div>
                                </div>
                                </div>
                                
							</div>
							
						</div>
					</div>

								

				</div>
			</section>
	<!-- Page Content / End -->
    <%session.removeAttribute("profilemessage");
	%>
   <div class="spacer-xl"></div>
			
		</div>
		<!-- Main / End -->
	</div>
	
	<%@ include file="/footer.jsp"%>	
	
	
	<!-- Javascript Files
	================================================== -->
	<script src="vendor/jquery-1.11.0.min.js"></script>
	<script src="vendor/jquery-migrate-1.2.1.min.js"></script>
	<script src="vendor/bootstrap.js"></script>
	<script src="vendor/jquery.flexnav.min.js"></script>
	<script src="vendor/jquery.hoverIntent.minified.js"></script>
	<script src="vendor/jquery.flickrfeed.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.js"></script>
	<script src="vendor/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendor/jquery.fitvids.js"></script>
	<script src="vendor/jquery.appear.js"></script>
	<script src="vendor/jquery.stellar.min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>


	
</body>

</html>