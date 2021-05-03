
<!DOCTYPE html>
<html class="not-ie no-js" lang="en"> 
<%@ page import="java.util.HashMap" %>
<%
HashMap hashmap=(HashMap)session.getAttribute("hashmap");
String page_title=(String)hashmap.get("distributor_page_title");
session.setAttribute("page_title",page_title);
String user_type=(String)hashmap.get("user_type");
String HeaderImage=(String)hashmap.get("distributor_header_home_image");
session.setAttribute("DSHeaderImage",HeaderImage);
String distributor_help_mobile_no1=(String)hashmap.get("distributor_help_mobile_no1");
String distributor_help_email_id1=(String)hashmap.get("distributor_help_email_id1");
String Powered_By=(String)hashmap.get("Powered_By");
String TickerMessage=(String)hashmap.get("TickerMessage");
System.out.println("user_type----"+user_type);
//String LoginMessage=(String)request.getAttribute("Loginmessage");
String Client_Id=(String)hashmap.get("Client_Id");
String companyName=(String)hashmap.get("company_name");
session.setAttribute("company_name",companyName);
System.out.println("Client_Id"+Client_Id);
%>

<head>
 <script language="javascript">
   function submitForm(){
	   var userName=document.LoginForm.loginId.value;
	   var pwd=document.LoginForm.password.value;
	   if(userName==""){
	   alert('Please enter user name');
	   return false;
	   }
	   else if(pwd==""){
	   alert('Please enter password');
	   return false;
	   }else{
		   $('#loginsubmit').prop('disabled', true);
	   	document.LoginForm.submit();
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
                                	<span class="logoclr"><%=distributor_help_email_id1 %></span></a>
                                </li>
                                <li><a><i class="fa fa-bell-slash"></i> Support Contact : <span class="logoclr"><%=distributor_help_mobile_no1 %></span></a></li>
                                
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
						<a href="home.html"><img src="images/<%=HeaderImage %>" alt="Handyman"></a>
						<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
					</div>
					<!-- Logo / End -->

				</div>
			</div>
			
		</header>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			
			<!-- Page Content -->
			<section class="page-content">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5">
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="box">
								<form name="LoginForm" action="doLogin.action?param=login" method="POST" role="form">
									 <input type="hidden" name="type" value="<%=user_type%>" />
									 
						               <%if(request.getAttribute("Loginmessage")!=null)
						               {
						               String Loginmessage=(String)request.getAttribute("Loginmessage");
						             
						               %>
						              <div style="margin-bottom: 5px;color: RED;" class="form_row form-error"><%=Loginmessage %></div>
						     
						               <%
						               }
						             %>
									<div class="form-group">
										<input type="text" class="form-control" value="" placeholder="Please Enter Email" name="loginId" id="userName">
									</div>
									<div class="form-group">
										<div class="clearfix">
											
											<span class="pull-right"><a href="doLogin.action?param=ForgetPassword">Lost password?</a></span>
										</div>
										<input type="password" value="" class="form-control" placeholder="Please Enter Password" name="password" id="pass">
									</div>
									<button type="submit" id="loginsubmit" class="btn btn-primary btn-inline" onclick="submitForm()">Login</button>&nbsp; &nbsp; &nbsp; 
									<%if(Client_Id.equalsIgnoreCase("10001")) {%>
                                	<a href="http://smartkinda.com/distributor_registration.jsp" target="_blank"><i class="fa fa-user-plus"></i>New User ? Register here.</a>
									<%} %>
								<!--<label for="remember" class="checkbox-inline">
										<input type="checkbox" name="remember" id="remember"> Remember me
									</label>
									
								--></form></div>
                                </div>
                                </div>
                                
							</div>
							
						</div>
					</div>

								

				</div>
			</section>
	<!-- Page Content / End -->
    
   		<div class="spacer" style="margin-top: 4%;"><b><marquee><%=TickerMessage %></marquee></b></div>
		<div class="spacer-xl" style="margin: 1% 0 0 10%;">
	
			<!--slider_wrap-->
		    <div  style="border:0px solid #ccc;width:auto;">
		    		
		             	<!-------------------------Don't delete scroller script ----------------------------->
		             	 <script type="text/javascript" src="js/scroller.js"></script> 
		            <!--slider_content closed -->
		    </div>
		    <!--end slider wrap-->

		</div>

			<!-- Footer -->
			
			<%@ include file="/footer.jsp" %>
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