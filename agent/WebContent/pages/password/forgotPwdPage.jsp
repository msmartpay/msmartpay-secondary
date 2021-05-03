<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%
	HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
	String clientId=dynamicData.get("clientId");
	String companyName=dynamicData.get("companyName");
	String mobileNo1=dynamicData.get("mobileNo1");
	String helpEmailID=dynamicData.get("helpEmailID");
	String userType=dynamicData.get("userType");
	String tickerMessage=dynamicData.get("tickerMessage");
	String headerHomeImage=dynamicData.get("headerHomeImage");
	String powerBy=dynamicData.get("powerBy");
	if(powerBy==null){
	powerBy="";} 
	String message="";
	if(request.getAttribute("message")!=null){
		message=(String)request.getAttribute("message");
	}else{
		message="";
	}
	%>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
	<script type="text/javascript">
	
		function changePassword(){
			var pass=document.ForgotPasswordForm.EmailID.value;
			if(pass==''){
				alert('Please Enter a Valid Password.');
				document.ForgotPasswordForm.EmailID.focus();
			}else{
				document.getElementById('submitpass').style.display="none";
				document.getElementById("submitpassloading").style.display="";
				document.ForgotPasswordForm.submit();
			}
			
		}
	
	</script>
	
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<header class="header header-menu-fullw">
			<!-- Header Top Bar -->
			<div class="header-top">
				<div class="container" style="width:90%;">
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
                                	<span class="logoclr"><%=helpEmailID %></span></a>
                                </li>
                                <li><a><i class="fa fa-bell-slash"></i> Support Contact : <span class="logoclr"><%=mobileNo1 %></span></a></li>
                            </ul>
						</div>
					</div>
				</div>
			</div>
			
			<div class="header-main">
				<div class="container" style="width:90%;"  >
					<!-- Logo -->
					<div class="logo" >
						<a href="#"><img src="images/<%=headerHomeImage %>" alt="Logo"></a>
						<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
					</div>
					

				</div>
			</div>
			
		</header>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>Forget Password?</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content"style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="box">
								<form name="ForgotPasswordForm" action="doForgotPwd.action?param=getPassword" method="post">
									
									<div  align="center">
										<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
									</div>
									<div class="form-group">
										<label class="pull-left"> To get your password, type your Registered Email ID. <span class="required">*</span></label>
										<br/><br/>
										<input type="text" class="form-control" required="required" name="EmailID" placeholder="Enter your Registered Mobile or Email ID">
									</div><br/>
									
									<button type="button" id="submitpass" onclick="changePassword()" class="btn btn-primary btn-inline">Submit Request</button>&nbsp; &nbsp; &nbsp; 
									<img id="submitpassloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    
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