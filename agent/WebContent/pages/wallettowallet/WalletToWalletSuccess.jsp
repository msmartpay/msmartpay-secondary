<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%
	
	String toAgentId=(String)request.getAttribute("toAgentId");
	String amount=(String)request.getAttribute("amount");
	String transactionId=(String)request.getAttribute("transactionId");
	String agentName=(String)request.getAttribute("agentName");
	
	String message=(String)request.getAttribute("message");
	if(message==null){
	message="";
	}
	
	
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

	<div class="main" role="main">

			<!-- Header -->
		<header class="header header-menu-fullw">

			
			<div class="header-main">
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
							<div class="box" align="center">
								<form name="submitForm" method="post" action="Getpage.action?param=w2w">
									<div class="form-group" align="center">
										<div style="width:18%;" align="center"><label  style="font-size:24px;font-weight:bold;text-align: center;"> Invoice </label><br/></div>
										<br>
										<div style="width:60%;" align="center"><label  style="font-size:18px;font-weight:bold;color: RED;text-align: center;"> <%=message %> </label><br/></div>
										
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;">Transfered To Agent  &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label ><%=agentName %> </label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;">Transfered To Agent Id  &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label > <%=toAgentId %></label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Transaction Id &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label ><%=transactionId %> </label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Amount &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label  style="font-weight: bold;"> <%=amount %> </label>
									</div>
									
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Grand total &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label  style="font-weight: bold;"> <%=amount %> </label>
									</div>
									
									
									<br/>
									<div class="form-group" align="center">
										<button type="button"  class="btn btn-primary btn-inline" onclick="javascript=this.disabled = true;window.print();">Print</button>&nbsp; &nbsp; &nbsp;
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
    
   <div class="spacer-xl"></div>


			
		</div>
	
	
	
	
	
</body>

</html>