<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>

	
	<script type="text/javascript">
	
	function submitForm(){
		$("#resultLoading").show();
		document.SubmitForm.submit();
	}
	</script>
	
	
	

</head>
<body >

		

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->
		
		<!-- Main  -->
		
		<div class="main" role="main">

			
			<!-- Page Heading -->
			<!-- <section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
							<h1>Confirm OTP</h1>
						<div class="col-md-12">
						</div>
					</div>
				</div>
			</section> -->
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content"style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box" style="height:350px;width: 96%;align:center;background: none;border:none;" >
                                        <div align="center" style="width: 100%;">                 
                                            <div class="tabs" style="width: 50%;">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active" style="width:100%;"><a style="width:100%;" href="#mobile" data-toggle="tab">Status</a></li>
                                                
                                                
                                            </ul>
                                            <div class="tab-content" style="padding:0px 28px 28px 28px;">
                                                <div class="tab-pane fade in active" id="mobile">
                                                    <form name="SubmitForm" action="${requestScope.requestParam }" method="post" >
														<div  align="center">
															
																<div class="table-color" align="center">
																<table class="table " >
																	<tr>
																		<td align="center" >${requestScope.message }</td> 
																															
																	</tr>
																	<tr>
																		<td align="center" ></td> 
																															
																	</tr>
																	<tr>
																		<td align="center" >
																			<button type="button" onclick="submitForm()"  class="btn btn-primary btn-inline">OK</button>
																		</td> 
																															
																	</tr>
																	</table>
																</div>
															
														</div>
														</form>
                                                </div>
											
											</div>
																								
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


			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
			<!-- Footer / End -->
			
		</div>
		<!-- Main / End -->
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