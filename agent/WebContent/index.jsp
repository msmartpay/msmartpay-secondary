<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta name="Description" content=" MSmart :  India's leading online travel portal offers wide range of online travel services through agents and distributors in India. We also provide b2b travel services, travel portal api, travel portal solution, travel portal development and travel portal white label in India."/>
	<meta name="Keywords" content="MSmart,Recharge Online,Recharge,Billpay online,Billpay, B2B Travel Agency,B2B Travel Company in India,B2B Travel Portal Company India,B2B Travel Portal India,B2B Travel Services,B2B Travel Sites, Online Travel Agency Business Opportunities, Online Travel Companies in India, Travel Portal Development, Travel Portal White Label, Travel Portal API "/>
	<meta name="language" content="EN"/>
	<meta name="copyright" content="MSmart"/>
	<meta name="robots" content="index, follow"/>
	<meta name="revisit-after" content="daily"/>
	<meta name="Robots" content="index, all"/>
	<meta name="allow-search" content="yes"/>
	<meta name="revisit-after" content="daily"/>
	<meta name="Rating" content="General"/>
	<meta name="distribution" content="global"/>


	<%@ page import="java.util.HashMap" %>
	<%
	String clientID=(String)session.getAttribute("clientId");
	HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
	String companyName=dynamicData.get("companyName");
	String mobileNo1=dynamicData.get("mobileNo1");
	String helpEmailID=dynamicData.get("helpEmailID");
	String userType=dynamicData.get("userType");
	String tickerMessage=dynamicData.get("tickerMessage");
	String headerHomeImage=dynamicData.get("headerHomeImage");
	String powerBy=dynamicData.get("powerBy");
	if(powerBy==null){
	powerBy="";}
	
	String message=(String)request.getAttribute("message");
	if(message==null)message="";
	
	
	// Some Important Data That Use so frequesntly
	session.setAttribute("helpEmailID",dynamicData.get("helpEmailID"));
	session.setAttribute("helpBalanceDeskEmailID",dynamicData.get("helpBalanceDeskEmailID"));
	//System.out.println("---------demoPanelFlag---------"+demoPanelFlag);
	System.out.println("---"+request.getServerName());
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
	<link href="images/favicons.png" rel="shortcut icon" />
	
	<style type="text/css">
	 /* do not group these rules */
	*::-webkit-input-placeholder {
	    color: #ddd!important;
	    font-weight:600;
	}
	*:-moz-placeholder {
	    /* FF 4-18 */
	    color: #ddd!important;
	    font-weight:600;
	}
	*::-moz-placeholder {
	    /* FF 19+ */
	    color: #ddd!important;
	    font-weight:600;
	}
	*:-ms-input-placeholder {
	    /* IE 10+ */
	    color: #ddd!important;
	    font-weight:600;
	}
	 </style>
	 <style>
	/* width */
	::-webkit-scrollbar {
	    width: 8px;
	}
	
	/* Track */
	::-webkit-scrollbar-track {
	    box-shadow: inset 0 0 5px grey; 
	    border-radius: 2px;
	}
	 
	/* Handle */
	::-webkit-scrollbar-thumb {
	    background: #35424e73; 
	    border-radius: 2px;
	}
	
	/* Handle on hover */
	::-webkit-scrollbar-thumb:hover {
	    background: #004566; 
	}
	.h5, h5 {
	    font-size: 16px;
	    line-height: 16px;
	}
	</style>
	


</head>
<body>

	<div class="site-wrapper">


		<!-- Main -->
		<div class="main" role="main">

			
			<!-- Page Content -->
			<section class="page-content" style="background:#fff; padding: 5% 3% 2% 3%;">
				<div class="container" style="width:100%;">				
					
					
					<div class="col-md-6">
					
						<div class="row">
							<div class="col-md-12" style="margin: 30% 0;">
							
								<%-- <div class="col-md-12" align="center" style="padding: 5% 0;">
									<!-- Logo -->
									<div class="logo" >
										<a href="#"><img src="images/<%=headerHomeImage %>" alt="Logo"></a>
										<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
									</div>
								</div> --%>
								<div class="col-md-12 " >
									<div class="help" align="center">
											<p><i class="fa fa-hand-rock-o"></i> Support Email : <span class="logoclr"><%=helpEmailID %></span></p>
				                            <p><i class="fa fa-bell-slash"></i> Support Contact : <span class="logoclr"><%=mobileNo1 %></span></p>
				                                
									</div>
								</div>	
				
							</div>
								
							
		                  	<!-- <div class="col-md-12" align="center">
		                      	<h4>Available Services</h4>
		                      	
								
									<div class="our_facts two" align="center">
            
							            
							            <ul>
										
											<li>
							                    <div class="icon"><h6>Prepaid Recharges</h6></div>
							                    
							                </li>
							                <li>
							                    <div class="icon"><h6>Utility Payments</h6></div>
							                    
							                </li>
										
							                <li>
							                    <div class="icon"><h6>Domestic Money Transfer</h6></div>
							                    
							                </li>
							                
							                <li class="last">
							                    <div class="icon"><h6>&nbsp;&nbsp;Rail &nbsp;&nbsp; Ticket Booking</h6></div>
							                    
							                </li>
							             </ul>
										
									</div>
									
									<div class="our_facts two" align="center">
            
							            
							            <ul>
										
											<li>
							                    <div class="icon"><h6>&nbsp;&nbsp;Car &nbsp;&nbsp; Booking</h6></div>
							                    
							                </li>
							                <li>
							                    <div class="icon"><h6>&nbsp;&nbsp;Bus &nbsp;&nbsp; Booking</h6></div>
							                    
							                </li>
										
							                <li>
							                    <div class="icon"><h6>&nbsp;&nbsp;&nbsp;Hotel &nbsp;&nbsp;&nbsp; Coming <br> Soon </h6></div>
							                    
							                </li>
							                
							                <li class="last">
							                    <div class="icon"><h6>&nbsp;&nbsp;&nbsp;&nbsp;AEPS &nbsp;&nbsp;&nbsp;&nbsp; Coming <br> Soon</h6></div>
							                    
							                </li>
							             </ul>
										
									</div>

		                  	</div> -->
						</div>
					</div>
					<div class="col-md-6 login_div" style="padding: 1% 3% 5% 3%;">
					
						<div class="row">
							<div class="col-md-12">
								
								<div class="col-md-12" align="center" style="padding: 0 0 10% 0;">
									<!-- Logo -->
									<div class="logo" >
										<a href="#"><img src="images/<%=headerHomeImage %>" alt="Logo"></a>
										<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
									</div>
								</div>
								
								<div class="col-md-12" align="center" >
								<form action="doLoginAction.action?param=login" method="post" >
									
									<div class="form-group" style="padding: 0 0% 6% 0;">
									<h4 style="float: left;">Retailer Login</h4>
									</div>
									<label  style="color: RED;font-weight: bold;">${requestScope.message}</label>
									<div class="form-group" >
										<input type="hidden" name="userType" value="<%=userType %>" />
										<input type="text" name="userName" id="userName" class="form-control" required="required" placeholder="Please Enter Mobile Or Email">
									</div>
									<div class="form-group" >
										<div class="clearfix">
											
											<span class="pull-right"><a href="doForgotPwd.action?param=forgotPwdPage">Lost password?</a></span>
										</div>
										<input type="password" value=""  name="pass" id="pass" required="required" class="form-control" placeholder="Please Enter Password">
									</div>
									<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-primary btn-inline">Login</button>&nbsp; &nbsp; &nbsp; 
									<%if(session.getAttribute("clientId")!=null && session.getAttribute("clientId").equals("10001")) {%>
									<a href="http://smartkinda.com/retailer_registration.jsp" target="_blank"><i class="fa fa-user-plus"></i>New User ? Register</a>
									<%} %>
									<!--<label for="remember" class="checkbox-inline">
									<input type="checkbox" name="remember" id="remember"> Remember me
									</label>
									-->	
								</form>
								</div>
							</div>
						</div>
						<%if(session.getAttribute("clientId")!=null && session.getAttribute("clientId").equals("10001")) {%>
						<div class="row">
							<div class="col-md-12" align="center" style="padding: 4% 2%;">
								<a href="https://play.google.com/store/apps/details?id=com.smartkinda" target="_blank" >
								<img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" height="80px" ></a>
							</div>
						</div>
						<%} %>
                            
                            <%-- <%if(session.getAttribute("clientId")!=null && session.getAttribute("clientId").equals("10001")) {%>
							<div  class="col-md-6" style="margin-bottom: 10px;">
							<div class="box">
									<!--<div class="form-group" style="padding: 2%;">
										<a  href="http://smartkinda.com/retailer_registration.jsp" target="_blank"><img  alt="Registration" title="New User? Register Now" height="100" width="200" src="images/register.png"></a>
									</div>
									--><div class="clearfix"></div>
									<h4><%=companyName %> Android Application</h4>
									<div class="form-group" style="border-top: 1px solid #DDD;border-bottom: 1px solid #DDD;padding: 7.8%;">
										<a  title="Download Android App" href="https://play.google.com/store/apps/details?id=com.ssz" target="_blank"><img height="60" width="150" src="images/androidapp.png" alt="Logo"></a>
									</div>
									<h4><a href="http://smartkinda.com/retailer_registration.jsp" target="_blank">New User? Register Now</a></h4>
							</div>
							</div>
						 <%} %> --%>
					
					</div>
					
					
				</div>
			</section>
			<!-- Page Content / End -->
    	<c:if test="${sessionScope.dynamicData!=null && sessionScope.dynamicData.tickerMessage!=null }"></c:if>
   		<div style="padding: 1%;"><b><marquee style="color: #000;"><img alt="" src="images/new.gif" style="margin-right: 10px;height: 30px;width: 50px;">${sessionScope.dynamicData.tickerMessage}</marquee></b></div>

		<footer class="footer" id="footer">
				
				<div class="footer-copyright">
					<div class="container" style="width:90%;" style="width: 95%;">
						<div class="row" style="width: 30%;float: left;">
							<div class="col-sm-12" >
								Copyright &copy; 2016  <a href="#">${sessionScope.companyName }</a> &nbsp;| &nbsp;All Rights Reserved
							</div>
						</div>
						<c:if test="${sessionScope.clientId!=null && sessionScope.clientId eq '10001' }">
						
							<div class="row" style="width: 45%;float: right;">
								<div class="col-sm-12" >
									<a href="http://partner.smartkinda.com/about.jsp" target="_blank">About Us</a>&nbsp;| &nbsp;<a href="http://partner.smartkinda.com/contact.jsp" target="_blank">Contact Us</a>&nbsp;| &nbsp;<a href="http://partner.smartkinda.com/banks.jsp" target="_blank">Bank Details</a>&nbsp;| &nbsp;<a href="https://smartkinda.com/PrivacyPolicy.jsp" target="_blank">Privacy Policy</a>&nbsp;| &nbsp;<a href="https://smartkinda.com/Term-Condition.jsp" target="_blank">Terms & Conditions</a>
								</div>
							</div>
						
						</c:if>
					</div>
				</div>
		</footer>

		<!-- Main / End -->
		</div>
	
	</div>
	
	
	
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