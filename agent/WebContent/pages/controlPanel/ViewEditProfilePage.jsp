<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>	

	<%
	
	HashMap profilemap=(HashMap) request.getAttribute("EditProfileData");
	%>

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<%@ include file="../../script.jsp" %>
	
	<script type="text/javascript">
	
	function populatecities()
	{
	var xmlhttp;
	var state="";
	state=document.getElementById("state").value; 
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
			document.getElementById("distirct").innerHTML=xmlhttp.responseText;
	    }
	  }
	xmlhttp.open("post",url,true);
	xmlhttp.send();
	}
	
	</script>
	
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">


			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
	                          
										<form name="EditProfileForm" method="post" action="docontrolPanel.action?param=updateProfile">
											<div class="col-md-12 form-group">
												<h3 class="box-title">Edit Profile</h3>
											</div>
											<div align="center">
												<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Agency Name <span class="required">*</span></label>
												<input name="AgencyName" type="text" class="form-control" required="required" placeholder="Please Enter Agency Name" value="<%=profilemap.get("AgencyName")%>" maxlength="40">
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Email <span class="required">*</span></label>
												<input type="text" name="emailID"  type="text" value="<%=profilemap.get("emailID")%>" maxlength="60" class="form-control" placeholder="Please Enter Email">
											</div>
											
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Mobile No. <span class="required">*</span></label>
												<input type="text" name="mobileNo" type="text" value="<%=profilemap.get("mobileNo")%>" maxlength="10" class="form-control" placeholder="Please Enter Mobile No.">
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Address <span class="required">*</span></label>
												<input type="text" class="form-control" required="required" placeholder="Please Enter Address" name="address"  type="text" value="<%=profilemap.get("address")%>" maxlength="200">
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
											<label class="pull-left"> State <span class="required">*</span></label>	
											<select class="col-md-6 col-md-offset-0 form-control" name="state"  onchange="populatecities()" id="state" required >
												<option value="<%=profilemap.get("state")%>" selected="selected"><%=profilemap.get("state")%></option>
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
											<div class="col-md-6 col-md-offset-0 form-group">
											<label class="pull-left"> District <span class="required">*</span></label>	
												<select class="form-control" name="district"  id="distirct" required>
		                                             <option value="<%=profilemap.get("district")%>" selected="selected"><%=profilemap.get("district")%></option>
		                                      		 <option value="">---------- Select District ------------</option>
		                                     </select>
											</div>
											
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> City <span class="required">*</span></label>
												<input type="text" class="form-control" name="city" required="required" type="text" value="<%=profilemap.get("city")%>" maxlength="25" placeholder="Please Enter City">
											</div>
											
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Pin Code <span class="required">*</span></label>
												<input type="text" name="pinCode" type="text" value="<%=profilemap.get("pinCode")%>" maxlength="6" class="form-control" placeholder="Please Enter Pin Code">
											</div>
											
											
											
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Agent Login ID <span class="required">*</span></label>
												<input type="text"  type="text" value="<%=AgentDetailData.get("userName")%>"  class="form-control" readonly="readonly">
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Agent Name <span class="required">*</span></label>
												<input type="text" readonly="readonly" type="text" value="<%=profilemap.get("agentName")%>"  class="form-control" >
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Agent ID <span class="required">*</span></label>
												<input type="text" readonly="readonly" type="text" value="<%=completeAgentID%>"  class="form-control" >
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
												<label class="pull-left"> Distributor ID <span class="required">*</span></label>
												<input type="text" readonly="readonly" type="text" value="<%=AgentDetailData.get("completeDSID")%>"  class="form-control" >
											</div>
											<div class="col-md-6 col-md-offset-0 form-group">
											
											<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-success waves-effect waves-light m-r-10">Update</button>&nbsp; &nbsp; &nbsp; 
											</div>
										</form>
                                </div>
                            </div>
                                
							
						</div>
					</div>

								

				</div>
			</section>
	<!-- Page Content / End -->
    
			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
			<!-- Footer / End -->
			
		</div>
		<!-- Main / End -->
	</div>
	
	
	
	
	
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