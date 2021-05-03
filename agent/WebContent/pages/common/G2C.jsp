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

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>G2C And Aadhaar</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Slider -->
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;background-size: cover;background-position: center;">
				<table cellspacing="5" class="data_table" >
					<tr>
					<td><a href="https://digitallocker.gov.in/public/register" target="_blank">DigiLocker </a></td>
					<td><a href="https://portal2.passportindia.gov.in/AppOnlineProject/user/RegistrationBaseAction?request_locale=en" target="_blank">Passport Seva </a></td>
					<td><a href="https://jeevanpramaan.gov.in/ppouser/login" target="_blank">Jeevan Parmaan</a></td>
					<td><a href="http://orf.gov.in/copp/appointment.jsp" target="_blank">Hospitals Appointment</a> </td>
					</tr>
					
					<tr>
					<td><a href="http://web3.kar.nic.in/bwssb_application/PublicUser.aspx" target="_blank">Water Connection-Bangalore</a></td>
					<td><a href="https://incometaxindiaefiling.gov.in/e-Filing/Registration/RegistrationControl.html" target="_blank">e-Filling Income-Tax</a></td>
					<td><a href="https://www.hyderabadwater.gov.in/en/index.php/services/prospective-consumer-services/apply-water-and-sewarage-connection" target="_blank">Water Connection-Hyderabad</a></td>
					<td><a href="http://www.nvsp.in/forms/form6.html" target="_blank">Apply Voter ID</a></td>
					</tr>
					
					<tr>
					<td><a href="https://resident.uidai.net.in/check-aadhaar-status" target="_blank">Check Aadhar Status</a></td>
					<td><a href="https://eaadhaar.uidai.gov.in/" target="_blank">Print e-Aadhaar Letter</a></td>
					<td><a href="https://resident.uidai.net.in/find-uid-eid" target="_blank">Find your Lost Enrolment ID (EID)/UID on registered mobile no.</a></td>
					<td><a href="https://resident.uidai.net.in/web/resident/get-aadhaar-no" target="_blank">Get your Aadhaar Number (using EID) on registered mobile no.</a> </td>

					</tr>
					
					


				</table>
			</section>
			<!-- Slider / End -->

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
	<script src="vendor/flexslider/jquery.flexslider-min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>

	<script>
		jQuery(function($){
			$('body').addClass('loading');
		});
		
		$(window).load(function(){
			$('.flexslider').flexslider({
				animation: "fade",
				controlNav: true,
				directionNav: false,
				prevText: "",
				nextText: "",
				start: function(slider){
					$('body').removeClass('loading');
				}
			});
		});
	</script>
	
</body>
</html>