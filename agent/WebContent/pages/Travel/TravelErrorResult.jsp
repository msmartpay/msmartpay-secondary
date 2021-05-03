<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%
	
	HashMap AgentDetailData=(HashMap)session.getAttribute("AgentDetailData");
	String completeAgentID=(String)AgentDetailData.get("completeAgentID");
	String agentID=(String)AgentDetailData.get("agentID");
	String agentBalance=(String)session.getAttribute("AgentBalance");
	String userName=(String)AgentDetailData.get("userName");
	String agencyName=(String)AgentDetailData.get("agencyName");
	String ipaddress=(String)request.getRemoteAddr();
	
	%>

	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
</head>
<body>
	<div class="site-wrapper">
	<div class="main" role="main">

			<!-- Header -->
		<header class="header header-menu-fullw">

			
			<div class="header-main" style="padding: 0px;">
				<div class="container" style="width:90%;" align="center" style="width: 15%;">
					<!-- Logo -->
					<div class="logo">
						<a href="#"><img src="images/<%=headerHomeImage %>" alt="Logo"></a>
						<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
					</div>
					<!-- Logo / End -->

				</div>
			</div>
			
		</header>
		<!-- Header / End -->
			<!-- Page Content -->
			<section class="page-content" style="height: 320px;">
				<div class="container" style="width:90%;">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5">
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row">
						<div class="col-md-6 col-md-offset-3" style="width: 50%;">
							<div class="box" align="center" style="    padding: 15% 0%;">
								<form name="submitForm" method="post" action="Getpage.action?param=Travel">
									
									<div class="form-group" align="center" style="color: RED;">
										<%=request.getAttribute("message") %>
									</div>
									
									
									<div class="form-group" align="center">
										<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-primary btn-inline">Back</button>&nbsp; &nbsp; &nbsp; 
									</div>
									
									
								</form></div>
                                </div>
                                </div>
                                
							</div>
							
						</div>
					</div>

								

				</div>
			</section>
		<!-- Page Content / End -->
    
		<!-- Footer -->
			<%@ include file="/footer.jsp" %>
		<!-- Footer / End -->
			
	</div>
	
	</div>
	
	
	
</body>

</html>