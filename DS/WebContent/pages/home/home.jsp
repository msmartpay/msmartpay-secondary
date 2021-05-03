
<!DOCTYPE html>
 <html class="not-ie no-js" lang="en"> 
<head>
	<%@ include file="/globaldata.jsp"%>
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
	<link rel="stylesheet" href="vendor/flexslider/flexslider.css" media="screen">
	<link rel="stylesheet" href="vendor/job-manager/frontend.css" media="screen">

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

	<div class="site-wrapper">

		<%@ include file="/header.jsp" %>
		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Distributor Services At <%=companyName %></h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Slider -->
			<section class="page-content" style="padding: 4% 0 4% 0;background-image: url('images/samples/bg2.jpg');">
			  	<div class="container" style="width: 90%;" align="center" >	
					
					<div class="col-md-12 " >
						<div class="box">
						
							<div class="col-md-12 " >
							
								<div class="col-md-4 " >
									<a href="doAgentDetails.action?param=AgentDetails&checkStatus=Activate" target="_parent"><img src="images/active_agent.png" border="0" /></a><br>
									<a href="doAgentDetails.action?param=AgentDetails&checkStatus=Activate" STYLE="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Active Agents</a><br>
								</div>
								<div class="col-md-4 " >
									<a href="doAgentDetails.action?param=AgentDetails&checkStatus=Deactive" target="_parent"><img src="images/deactive_agent.png" border="0" /></a><br>
									<a href="doAgentDetails.action?param=AgentDetails&checkStatus=Deactive" STYLE="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Deactive Agents</a>
								</div>
								<div class="col-md-4 " >
									<a href="doCommonAction.action?param=regAgent"><img src="images/add_agent.png" border="0" /></a><br>
									<a href="doCommonAction.action?param=regAgent" STYLE="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Add Agents</a>
								</div>
							
							</div>
							<div class="col-md-12 " >
								<div class="col-md-4 " >
								
									<!-- <a href="AgentDepositRequest.action?param=agentDepositRequest" target="_parent"><img src="images/agent_deposite.png" border="0" /></a><br>
									<a href="AgentDepositRequest.action?param=agentDepositRequest" style="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Deposit Request-Agent</a>
								 -->
								 
								 <a href="#" target="_parent"><img src="images/agent_deposite.png" border="0" /></a><br>
								<a href="#" style="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Deposit Request-Agent</a>
								
								</div>
								<div class="col-md-4 " >
									<a href="doCommonAction.action?param=pushBalance"><img src="images/push_balance.png" border="0" /></a><br>
									<a href="doCommonAction.action?param=pushBalance" style="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Push Balance-Agent</a>
									
								</div>
								<div class="col-md-4 " >
									<a href="distributorAccount.action?param=distributorAccountStatement"><img src="images/report.png" border="0" /></a><br>
									<a href="distributorAccount.action?param=distributorAccountStatement" STYLE="TEXT-DECORATION: NONE;color: #000;font-weight: bold;">Reports</a>
								</div>
							</div>
						
						</div>
					</div>
				
				</div>
			</section>
			<!-- Slider / End -->
			<section class="page-content">
			<div class="container">	
        		<div class="spacer-xl"></div>
                <div class="row">
                    <div class="col-md-12">
                        <h2>Welcome to World of <%=companyName %></h2>
                        <p>Safe and Instant Recharges, Billpayment Service And Many more...</p>
                        <p>A unique E-Solution at single platform.</p>
                        <p>We promise to provide you with outstanding service that you can trust for all of your E-Service needs.</p>
                        <a href="#" class="btn btn-primary">Travel Coming Soon</a>
                    </div>
                </div>
			</div>
			</section>
           <!--<div class="spacer-xl"></div>-->
			
	<%@ include file="/footer.jsp" %>		
		</div>
		<!-- Main / End -->
	</div>
	
	<%
	if(bannerStatus.equalsIgnoreCase("Y")){ %>
	<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="welcomemessage" id="welcomemodal">
		  <div class="modal-dialog" style="width:65%" >
		    <div class="modal-content pA10" >  
		      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      
		      <div class="welcome_msg" >
					<p align="center"><img src="http://smartkinda.com/images/Banner.png" height="450" width="100%" border="0"></p>
		      
		      </div>
		      
		      
		      
		    </div>
		  </div>
		</div>
	
	<%} %>
	
	
	
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
	<script type="text/javascript">
    $(window).load(function(){
        $('#welcomemodal').modal('show');
    });
	</script>
	
</body>
</html>