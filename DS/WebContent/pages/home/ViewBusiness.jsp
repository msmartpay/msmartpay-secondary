
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>


<%
String message=(String)request.getAttribute("message");
System.out.println("message---"+message);
if(message==null)
{
message="";

}
HashMap<String,String> currentMonth=null;
HashMap<String,String> previousMonth=null;
ArrayList<HashMap<String,String>>  businessDetails=( ArrayList<HashMap<String,String>>)request.getAttribute("businessDetails");

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
							<%if(message.length()>1){ %>
									<h1 style="color: red;font-weight: bolder;"><%=message %></h1>
								<%}
							else {%>
									<h1 style="font-weight: bolder;">Your Business Details</h1>
							<%} %>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="height: 425px;">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5">
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row">
						<div class="col-md-12 col-md-offset-0">
							<div class="box">
								<!-- <form name="changePasswordForm" method="POST" role="form"> -->
								<div class="row">
										<div class="col-md-12 col-md-offset-1" >
											<div class="col-md-10">
											<h5>Current Month Business Details</h5>
											</div>
											<c:if test="${businessDetails!=null && businessDetails.size()>0 }">
												<%for(HashMap<String,String> current:businessDetails){
												
												if(current!=null&&(current.get("Month-Type").equalsIgnoreCase("CurrentMonth"))){
												
												%>
											
											<div class="col-md-3" style="background-color: #0000ff14; margin: 10px;border-radius: 15px;padding-top: 20px;">
												<h6 style="color: darkgreen;"><%=current.get("Service-Name") %></h6>
												<h6 style="color: blueviolet;">Rs.&nbsp;<%=current.get("Trans-Amount") %></h6>
											</div>
											<%} }%>
											</c:if>
											
										</div>
									</div>
									
									<!-- PREVIOUS MONTH DETAILS -->
									
									<div class="row">
										<div class="col-md-12 col-md-offset-1">
											<div class="col-md-10">
											<h5 style="padding-top: 16px;color:darkblue;text-decoration: blink;">Previous Month Business Details</h5>
											</div>
											<c:if test="${businessDetails!=null && businessDetails.size()>0 }">
											<%for(HashMap<String,String> current:businessDetails){
												
												if(current!=null&&(current.get("Month-Type").equalsIgnoreCase("PreviousMonth"))){
												
												%>
											
											<div class="col-md-3" style="background-color: #0000ff14; margin: 10px;border-radius: 15px;padding-top: 20px;">
												<h6 style="color: darkgreen;"><%=current.get("Service-Name") %></h6>
												<h6 style="color: blueviolet;">Rs.&nbsp;<%=current.get("Trans-Amount") %></h6>
											</div>
											<%} 
											
											}%>
											</c:if>
										</div>
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
    
   <div class="spacer-xl"></div>
			
		</div>
		<!-- Main / End -->
	</div>
	
<%@ include file="/footer.jsp" %>	

	
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