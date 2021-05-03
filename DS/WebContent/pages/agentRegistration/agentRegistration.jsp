
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
     <%@ include file="/globaldata.jsp"%>
     <%
		String titlePage=(String)session.getAttribute("titlePage");
		
		Calendar calendar=Calendar.getInstance();
		
		int currentYr=calendar.get(Calendar.YEAR);
		String message=(String)request.getAttribute("message");
		
		if(message==null)
		{
		message="";
		}

	%>  
       
    
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
	
	
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css" type="text/css" media="all" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" type="text/javascript"></script>
       
    <script type="text/javascript">
	
	$(document).ready(function(){
		  
	    var now = new Date();
		var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	 	
		$("#datepicker").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2020',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		  
	});
	
	</script>
	
	<script language="javascript">
	
	function createXMLHttpRequest()
	{
	if (window.ActiveXObject)
	{
	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest)
	{
	xmlHttp = new XMLHttpRequest();
	}
	}
	
	function checkEmail()
	{
	
	//document.getElementById('img2').style.visibility="visible";
	createXMLHttpRequest();
	var type=document.regiestration.email.value;
	xmlHttp.onreadystatechange = printValuesEmail;
	
	
	xmlHttp.open("POST","AgentRegistration.action?param=CheckEmail&email="+encodeURIComponent(type), true);
	
	xmlHttp.send(null);
	}
	
	
	function printValuesEmail()
	{
	if(xmlHttp.readyState == 4)
	{
	if(xmlHttp.status == 200)
	{
	var response1=xmlHttp.responseText;
	//alert("email"+response1);
	var resultEmail=document.getElementById('resultEmail');
	if(response1=="valid")
	{
	
	//document.getElementById('img2').style.visibility="hidden";
	response1="Email id is Unique"
	resultEmail.innerHTML=response1;
	resultEmail.style.color="green"
	}
	else
	{
	//document.getElementById('img2').style.visibility="hidden";
	response1="Email ID is Duplicate."
	resultEmail.innerHTML=response1;
	resultEmail.style.color="red"
	document.regiestration.email.value="";
	document.regiestration.email.focus();
	}
	}
	}
	}
	
	</script>
	<script language="javascript">
	
	function createXMLHttpRequest()
	{
	if (window.ActiveXObject)
	{
	xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest)
	{
	xmlHttp = new XMLHttpRequest();
	}
	}
	function checkMobile()
	{
	
	//document.getElementById('img2').style.visibility="visible";
	createXMLHttpRequest();
	var type=document.regiestration.AthoMobile.value;
	xmlHttp.onreadystatechange = printValuesMobile;
	
	
	xmlHttp.open("POST","AgentRegistration.action?param=CheckMob&mob="+type, true);
	xmlHttp.send(null);
	}
	
	
	function printValuesMobile()
	{
	if(xmlHttp.readyState == 4)
	{
	if(xmlHttp.status == 200)
	{
	var response1=xmlHttp.responseText;
	//alert("mobile"+response1);
	var resultMobile=document.getElementById('resultMobile');
	if(response1=="valid")
	{
	
	//document.getElementById('img2').style.visibility="hidden";
	response1="Mobile Number is Unique"
	resultMobile.innerHTML=response1;
	resultMobile.style.color="green"
	}
	else
	{
	//document.getElementById('img2').style.visibility="hidden";
	response1="Mobile Number is Duplicate."
	resultMobile.innerHTML=response1;
	resultMobile.style.color="red"
	document.regiestration.AthoMobile.value="";
	document.regiestration.AthoMobile.focus();
	}
	}
	}
	}
	</script>
	
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
		var firstname=document.regiestration.firstname.value.replace(/^\s+|\s+$/, '');
		if(firstname==""){
			alert("Please Enter First Name.");
			document.regiestration.firstname.focus();
			return false;
		}
		var lastname=document.regiestration.lastname.value.replace(/^\s+|\s+$/, '');
		if(lastname==""){
			alert("Please Enter Last Name.");
			document.regiestration.lastname.focus();
			return false;
		}
		var dateofbirth=document.regiestration.dateofbirth.value;
		if(dateofbirth==""){
			alert("Please Enter DOB.");
			document.regiestration.dateofbirth.focus();
			return false;
		}
	
		var Gender=document.regiestration.Gender.value.replace(/^\s+|\s+$/, '');
		if(Gender==""){
			alert("Please Select Gender.");
			document.regiestration.focus();
			return false;
		}
	
		var CompanyType=document.regiestration.CompanyType.value.replace(/^\s+|\s+$/, '');
		if(CompanyType=="Select"){
			//alert("Please Select Company Type.");
			document.regiestration.CompanyType.focus();
			return false;
		}
	
		var agencyName=document.regiestration.agencyName.value;
		if(agencyName==""){
			//alert("Please Enter Company Name");
			document.regiestration.agencyName.focus();
			return false;
		}
		var email=document.regiestration.email.value;
		if(email==""){
			//alert("Please Enter Email ID.");
			document.regiestration.email.focus();
			return false;
		}
		var AthoMobile=document.regiestration.AthoMobile.value.replace(/^\s+|\s+$/, '');
		if(AthoMobile==""){
			//alert("Please Enter Mobile Number.");
			document.regiestration.AthoMobile.focus();
			return false;
		}
		var Address1=document.regiestration.Address1.value.replace(/^\s+|\s+$/, '');
		if(Address1==""){
			//alert("Please Enter Address.");
			document.regiestration.Address1.focus();
			return false;
		}
		
		var state=document.regiestration.state.value.replace(/^\s+|\s+$/, '');
			if(state=="-1"){
			//alert("Please Select State.");
			document.regiestration.state.focus();
			return false;
		}
		
		var city=document.regiestration.city.value.replace(/^\s+|\s+$/, '');
		if(city==""){
			//alert("Please Enter City.");
			document.regiestration.city.focus();
			return false;
		}
		
		var officePinCode=document.regiestration.officePinCode.value.replace(/^\s+|\s+$/, '');
		if(officePinCode==""){
			//alert("Please Enter PinCode.");
			document.regiestration.officePinCode.focus();
			return false;
		}
	
		document.getElementById('regagent').style.display="none";
		document.getElementById("loading").style.display="";
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
							<h1>Add Agent</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->
			
			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: #fff;">
				<div class="container" style="width: 90%;" align="center" >	
					
						<div class="col-md-12 " >
							<%if(!message.equalsIgnoreCase("")){ %>
							<div style="color: RED;font-weight: bold;"><%=message%></div>
							<%} %>
						</div>	
						<div class="col-md-12 " >
							<div class="box">
								<form name="regiestration" action="AgentRegistration.action" method="POST" role="form">
									   
								   	<div class="col-md-6 " >
								   		<input type="hidden" name="param" value="AgentRegistration">
									   
										<div class="form-group col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fafafa;color:#c00;margin-bottom:2%;">
											<h5 style="color:#c00;">Personal Details</h5>
										</div>
										<div class="form-group col-md-12" >
											<input type="text" name="firstname" required="required" class="form-control" placeholder="First Name" onkeyup="convertToCaps(this);">
										</div>
										<div class="form-group col-md-12">
											<input type="text" name="lastname" required="required" class="form-control" placeholder="Last Name" onkeyup="convertToCaps(this);">
										</div>
										
										
										<div class="form-group col-md-6" >
											<input type="text" name="dateofbirth" id="datepicker" required="required" class="form-control" placeholder="Select Date Of Birth">
										</div>
										<div class="form-group col-md-6">
											
											<select class="form-control" name="Gender" required >
												  <option selected="selected" value="Male">Male</option>
												  <option value="Female">Female</option>
																					
											</select>
										</div>
										<div class="form-group col-md-12" >
											
											<select class="form-control" name="CompanyType"  required >
													  <option selected="selected" value="Non-Registered Entity">Non-Registered Entity</option>
													  <option value="Proprietorship">Proprietorship</option>
													  <option value="Partnership">Partnership</option>
													 <option value="Private Limited">Private Limited</option>
													 <option value="Limited">Limited</option>
													 <option value="Society">Society</option>
													<option value="NGO">NGO</option>
													 <option value="Trust">Trust</option>
											</select>
										</div>
										
										<div class="form-group col-md-12" >
											
											<input type="text" name="agencyName" required="required" class="form-control" placeholder="Company/ Firm/ Shop Name" onkeyup="convertToCaps(this);">
										</div>
										<div class="form-group col-md-8" >
									
											<input type="text" id="email" name="EmailId" required="required" class="form-control" placeholder="Email ID *">
										</div>
										<div class="form-group col-md-4" >
									
											<h6 id="resultEmail"></h6><a href="#" onclick="checkEmail()" style="text-decoration:underline;float:right"><font style="font-size:11px;" color="red" >Check Availability</font></a>
										</div>
										<div class="form-group col-md-8" >
											
											<input type="text" name="AthoMobile"  required="required" class="form-control" placeholder="Enter 10 Digit Mobile Number"  maxlength="10" onkeypress="return digitonly(this,event)">
										</div>
										<div class="form-group col-md-4" >
											
											<h6 id="resultMobile"></h6><a href="#" onclick="checkMobile()" style="text-decoration:underline;float:right"><font style="font-size:11px;" color="red">Check Availability</font></a>
										</div>
								   	</div>
									   
									
									<div class="col-md-6">
									
										<div class="col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fafafa;color:#c00;margin-bottom:2%;">
											<h5 style="color:#c00;">Address Details</h5>
										</div>
										
										<div class="form-group col-md-12" >
											
											<input name="Address1" type="text" required="required" class="form-control" placeholder="Address Line 1 *" onkeyup="convertToCaps(this);">
										</div>
										<div class="form-group col-md-12">
											
											<input type="text" name="Address2" class="form-control" placeholder="Address Line 2" onkeyup="convertToCaps(this);">
										</div>
										<div class="form-group col-md-12">
											
											<select name="officeCountry" class="form-control" >
												<option value="India">India</option>
													 
											</select>
										</div>
										
										<div  class="form-group col-md-6" required>
											
											<select class="form-control" name="state" id="Ostate" onchange="populatecities('office')">
												<option value="">--------- Select State ---------</option>
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
										
										<div class="form-group col-md-6" required>
											
											<select class="form-control "name="District"  id="officeDist">
												<option value="">--------------- Select District --------------</option>
													 
											</select>
										</div>
										
										<div class="form-group col-md-6" >
									
											<input type="text" name="city" required="required" class="form-control" placeholder="City *" onkeyup="convertToCaps(this);">
										</div>
										<div class="form-group col-md-6" required="required" >
	
											<input type="text" name="officePinCode" class="form-control" placeholder="Pin Code *" maxlength="6" onkeypress="return digitonly(this,event)">
										</div>
										<div class="col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fafafa;color:#c00;margin-bottom:2%;">
											<h5 style="color:#c00;">PAN Details</h5>
										</div>
										<div class="form-group col-md-12" >
											
											<input type="text" name="PanNo" class="form-control" placeholder="PAN Number (Optional)" onkeyup="convertToCaps(this);" maxlength="14">
										</div>
									
									</div>
									
									
									<div class="form-group col-md-12" align="center">
									<button type="submit" id="regagent" class="btn btn-primary btn-inline" onclick="checkform()">Add Agent</button>&nbsp; &nbsp; &nbsp; 
									<img id="loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                    </div>           	
								</form>

							</div>
                        </div>
                                

								

				</div>
			</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>
			
		</div>
		<!-- Main / End -->
	</div>
	
	<%@ include file="/footer.jsp" %>
	
	<!-- Javascript Files
	================================================== -->
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