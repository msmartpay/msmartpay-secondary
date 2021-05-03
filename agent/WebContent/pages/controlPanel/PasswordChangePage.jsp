<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<%@ include file="../../script.jsp" %>
	
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
	                    <div class="col-md-6">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">

								<form name="ChangePasswordForm" action="docontrolPanel.action?param=changePass" method="post">
									<div  align="center">
										<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
									</div>
									
									<div class="col-md-12 form-group ">
										<h3 class="box-title">Change Password</h3>
									</div>
									<div class="form-group col-md-12">
										
										<input type="password" name="currentPass" class="form-control" placeholder="Please Enter Current Password">
									</div>
									<div class="form-group col-md-12">
										
										<input type="password" name="newPass" class="form-control" placeholder="Please Enter New Password">
									</div>
									<div class="form-group col-md-12">
										
										<input type="password" name="confirmPass" class="form-control" placeholder="Please Confirm Password">
									</div>
									<div class="form-group col-md-12">
									<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                    <button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-success waves-effect waves-light m-r-10">Change Password</button>&nbsp; &nbsp; &nbsp; 
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