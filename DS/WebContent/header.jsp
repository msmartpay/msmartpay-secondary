<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
document.body.style.zoom="90%";
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=session.getAttribute("page_title") %></title>
</head>
<body>
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
						<div class="menu-container" style="margin-left: -2%">
							<ul class="header-top-nav header-top-nav__tertiary">
                                 <li><a><i class="fa fa-hand-rock-o"></i> Welcome : 
                                	<span class="logoclr"><%=companyName %></span></a>
                                </li>
                                <li><a><i class="fa fa-bell-slash"></i>Distributor ID : <span class="logoclr"><%=distributorInitial%><%=distributorId%></span></a></li>
                                <li><a><i class="fa fa-rupee"></i>Balance : <span><strong>Rs.<span><strong><%=FinalBalance%></strong></span></strong></span></a></li>                               								
								<li><a style="margin-right: -10%" href="doCommonAction.action?param=logout"><i class="fa fa-sign-out"></i> Log Out</a></li>
                                
                                
                                <!--<li><a href="page-login.html"><i class="fa fa-user-plus"></i> Register</a></li>-->
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- Header Top Bar / End -->
			<div class="header-main">
				<div class="container">
					<!-- Logo -->
					<div class="logo">
						<a href="home.html"><img src="images/<%=HeaderImage %>" alt="Handyman" height="69"></a>
						<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
					</div>
					<!-- Logo / End -->

					<button type="button" class="navbar-toggle">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Navigation -->
					<nav class="nav-main">
						<div class="nav-main-inner">
							<ul data-breakpoint="992" class="flexnav">
																
								<li><a href="doCommonAction.action?param=home" class="top_link" target="_parent">Home</a></li>
                                <li><a href="#" class="top_link" target="_parent"><span class="down">Agents</span></a>
                               	 	<ul>
										<li><a href="doCommonAction.action?param=regAgent" target="_parent">Add Agents</a></li>
										<li><a href="doAgentDetails.action?param=AgentDetails&checkStatus=All" target="_parent">View Agents</a></li>			
									</ul>
								</li>								
								<li><a href="#"  class="top_link"><span class="down">Funds</span></a>
								 	<ul class="sub">
										<li><a href="doCommonAction.action?param=pushBalance" target="_parent">Push Balance-Agent</a></li>
		            					<!-- <li><a href="AgentDepositRequest.action?param=agentDepositRequest" target="_parent">Deposit Request-Agent</a></li> -->
		            					<li><a href="distributorDepositRequest.action?param=MyDeposit" target="_parent">Balance Request</a></li>
		            				</ul>
								</li>
                               <!--  <li><a href="#">TRAVEL</a>
                                	<ul>
										<li><a href="#">Flight</a></li>
                                        <li><a href="#">Hotel</a></li>		
										<li><a href="#">Bus</a></li>						
									</ul>
                                </li> -->
								<li><a href="#"  class="top_link"><span class="down">Reports</span></a>
									<ul class="sub">
                						<li><a href="distributorAccount.action?param=distributorAccountStatement">My Reports</a></li>
                 						<li><a href="AgentReport.action?param=getReportPage">Retailer Report</a></li>
							<!-- 			<li><a href="distributorAccount.action?param=distributorAccountStatement" target="_parent">Transaction Report</a></li> -->
       									<li><a href="SearchTran.action?param=SearchTranpage">Transaction Status</a></li>
										<!--<li><a href="distributorDeposit.action?param=distributorDepositSearch" target="_parent">Transaction Status</a></li>-->
            							<!--<li><a href="AgentTransaction.action?param=getAgentId" target="_parent">Download Report</a></li>-->
              						</ul>
        						</li>
								
								<li>
								<a href="doCommonAction.action?param=viewBusiness"  class="top_link"><span class="down">View Business</span></a>
									
        						</li>
								
								
                                <li><a href="#">CONTROL PANEL</a>
                                	<ul>
										<li><a class="top_link" href="doProfileAction.action?param=DisProfile">My Profile</a></li>
                                        <li><a href="doCommonAction.action?param=changePassword">Reset Password</a></li>	
															
									</ul>
                                </li>								
								
							</ul>
						</div>
					</nav>
					<!-- Navigation / End -->

				</div>
			</div>
			
		</header>
		<!-- Header / End -->
</body>
</html>
