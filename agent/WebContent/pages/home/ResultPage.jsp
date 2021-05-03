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
	
	String message=(String)request.getAttribute("message");
	if(message==null){
	message="";
	}
	String service=(String)request.getAttribute("service");
	if(service==null){
		service="getPage";
	}
	String mobileNo=(String)request.getAttribute("mobileNo");
	if(mobileNo==null){
		mobileNo="NA";
	}
	
	String amt=(String)request.getAttribute("amt");
	if(amt==null){
		amt="NA";
	}
	
	String Operator=(String)request.getAttribute("Operator");
	if(Operator==null){
		Operator="NA";
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
								<form name="submitForm" method="post" action="Getpage.action?param=<%=service%>">
									<div class="form-group" align="center">
										<div style="width:18%;" align="center"><label  style="font-size:24px;font-weight:bold;text-align: center;"> Invoice </label><br/></div>
										<br>
										<div style="width:60%;" align="center"><label  style="font-size:18px;font-weight:bold;color: RED;text-align: center;"> <%=message %> </label><br/></div>
										
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Name  &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label ><%=agencyName %> </label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Account/Mobile No  &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label > <%=mobileNo %></label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Operator &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label ><%=Operator %> </label>
									</div>
									<div class="form-group"  align="center">
										<label style="font-weight: bold;"> Service &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label ><%=service %> </label>
									</div>
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Amount &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label  style="font-weight: bold;"> <%=amt %> </label>
									</div>
									
									<div class="form-group" align="center">
										<label  style="font-weight: bold;"> Grand total &nbsp; &nbsp; &nbsp; : &nbsp; </label>
										<label  style="font-weight: bold;"> <%=amt %> </label>
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