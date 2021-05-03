
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import="java.util.HashMap" %>
<%

HashMap hashmap=(HashMap)session.getAttribute("hashmap");
String user_type=(String)hashmap.get("user_type");
String DSHeaderImage=(String)hashmap.get("distributor_header_home_image");
session.setAttribute("DSHeaderImage",DSHeaderImage);
String distributor_help_mobile_no1=(String)hashmap.get("distributor_help_mobile_no1");
String distributor_help_email_id1=(String)hashmap.get("distributor_help_email_id1");
String Powered_By=(String)hashmap.get("Powered_By");
String TickerMessage=(String)hashmap.get("TickerMessage");
System.out.println("user_type----"+user_type);
//String LoginMessage=(String)request.getAttribute("Loginmessage");
String Client_Id=(String)session.getAttribute("Client_Id");
System.out.println("Client_Id"+Client_Id);

String message=(String)request.getAttribute("message");
System.out.println("message----"+message);
if(message==null)
{
message="";

}

String ip_address=(String)request.getRemoteAddr();

%>
<script language="javascript">
function Submit(){

	var emailId=document.ForgetPass.agenEmailId.value;
   	//alert(emailId);
	if(emailId=="")
	{
	alert("Please enter your Registered Email ID.");	
	return false;
    }
   else{	
	document.ForgetPass.submit();
	return true;
       }	
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

	<div class="site-wrapper">

		<!-- Header -->
		<header class="header header-menu-fullw">

			<!-- Header Top Bar -->
			<div class="header-top">
				<div class="container">
					<div class="header-top-left">
						<button class="btn btn-sm btn-default menu-link menu-link__secondary">
							<i class="fa fa-bars"></i>
						</button>
						<div class="menu-container">
							<ul class="header-top-nav header-top-nav__secondary">
								<li><a href="#"><i class="fa fa-twitter"></i> <span class="nav-label">Twitter</span></a></li>
								<li><a href="#"><i class="fa fa-facebook"></i> <span class="nav-label">Facebook</span></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i> <span class="nav-label">Google+</span></a></li>
                                <li><a href="#"><i class="fa fa-linkedin"></i> <span class="nav-label"> Linkedin</span></a></li>
							</ul>
						</div>
					</div>
					<div class="header-top-right">
						<button class="btn btn-sm btn-default menu-link menu-link__tertiary">
							<i class="fa fa-user"></i>
						</button>
						<div class="menu-container">
							<ul class="header-top-nav header-top-nav__tertiary">
                                 <li><a><i class="fa fa-hand-rock-o"></i> Support Email : 
                                	<span class="logoclr">support@smartkinda.com</span></a>
                                </li>
                                <li><a><i class="fa fa-bell-slash"></i> Support Contact : <span class="logoclr">+91-9711402774</span></a></li>
                        <!--         <li><a href="page-login.html"><i class="fa fa-user-plus"></i>New User ? Register</a></li> -->
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- Header Top Bar / End -->
			<div class="header-main">
				<div class="container" align="center" style="width: 20%;">
					<!-- Logo -->
					<div class="logo">
						<a href="home.html"><img src="images/logo.png" alt="Handyman"></a>
						<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
					</div>
					<!-- Logo / End -->

				</div>
			</div>
			
		</header>
		<!-- Header / End -->

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Forget Password?</h1>
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
                            <div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="box">
								<form name="ForgetPass" action="changePass.action?param=ForgetPassword" method="POST" role="form">
									<input type="hidden" name="clientID" value="<%=ip_address%>">
									<div class="form-group">
										<label class="pull-left"> To get your password, type your Registered Email ID. <span class="required">*</span></label>
										<br/>
										<input type="text" class="form-control" placeholder="Enter your Registered Email ID" name="agenEmailId" maxlength="100">
									</div>
									<button type="submit"  value="Submit" class="btn btn-primary btn-inline" id="img_btn" onclick="return Submit();">Submit Request</button>&nbsp; &nbsp; &nbsp; 
									
								</form></div>
                                </div>
                                </div>
                                
							</div>
							
						</div>
					</div>

								

				</div>
			</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>


			<!-- Footer -->
			<footer class="footer" id="footer">
				
				<div class="footer-copyright">
					<div class="container">
						<div class="row">
							<div class="col-sm-12">
								Copyright &copy; 2016  <a href="#">SmartKinda</a> &nbsp;| &nbsp;All Rights Reserved
							</div>
						</div>
					</div>
				</div>
			</footer>
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