<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
	<%@ include file="../../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="/script.jsp" %>

	<style type="text/css">
.clsRound14 {
	position: relative;
}

.clsRound14 .fareseat {
	visibility: hidden;
	width: 220px;
	background-color: rgba(85, 85, 85, 0.72);
	color: #fff;
	text-align: center;
	border-radius: 4px;
	padding: 8px 0;
	padding-left: 6px;
	/* Position the tooltip */
	margin-top: 10px;
	position: absolute;
	z-index: 1;
}

.clsRound14:hover .fareseat {
	visibility: visible;
}
â€‹
</style>

<script>
	function preloadImages() {
		Image1 = new Image();
		Image1.src = "images/Seat_img/Selected.png";
	}
</script>



<style>
.bus {
	width: 380px;
	float: left;
	min-height: 150px;
	border: 1px solid #CCC;
	padding: 0 0 10px 10px;
}

.seat {
	background: #CCC;
	float: left;
	margin: 10px 10px 0 0;
	cursor: pointer;
	padding: 4;
}

.cancel_book {
	background: #CCC;
}

.green {
	background: green;
}

.red {
	background: red;
}
.non_padding{
	margin: 0 0 0 0;
}
</style>

	

</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="/header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: #fcfcfc;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                
                                	<div class="box" >
                                    	<div class="col-md-12 myContainer" style="padding: 0%;">
                                    
                                    		<!-- <div class="col-md-12 myContainer-title" >
												<label class="pull-left "><i class="fa fa-bus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>TRAVELER DETAILS</strong></label>
											</div> -->
                                    
											<div class="col-md-12" style="padding: 1% 2%; border-bottom: 1px dashed #ddd;border-top: 1px dashed #ddd;background-color: #fd0000;color: #fff;font-weight: bold;">

												<div class="col-md-3" >															                                               
													<p class="non_padding" align="left">PNR : ${bookingResponse.PNRDetails.TransportPNR }</p>	
												</div>
												
												<div class="col-md-3" >															                                               
													<p class="non_padding" align="left">${requestScope.originName} to ${requestScope.destinationName}</p>	
												</div>
												
												<div class="col-md-3">															                                               
											
													<p class="non_padding" align="left">${requestScope.travelDate} , ${requestScope.departureTime}</p>
												</div>
												
												<div class="col-md-3">
													<p class="non_padding" align="left">${requestScope.bookingResponse.TransportDetails.TransportName}</p>
												</div>
												
											</div>


										 


											<!-- <div class="col-md-12 myContainer-title" >
												<label class="pull-left "><i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>PASSENGER INFORMATION</strong></label>
											</div> -->
											<div class="col-md-12" style="border-bottom: 1px dashed #ddd;border-top: 1px dashed #ddd;margin-top: 5px;">
											<div class="col-md-12" style="padding: 1% 1%;">
												<div class="col-md-2">
												
													<p class="non_padding" align="left"><strong>Name</strong></p>												
											      										   
												</div>
												<div class="col-md-1">
												
													<p class="non_padding" align="left"><strong>Gender</strong></p>												
											      										   
												</div>
												
												<div class="col-md-1">
													<p class="non_padding" align="left"><strong>Ticket No.</strong></p>													
												</div> 
											
											
												<div class="col-md-1">
													<p class="non_padding" align="left"><strong>Seat No.</strong></p>													
												</div> 
												
												<div class="col-md-2">
													<p class="non_padding" align="left"><strong>PNR</strong></p>													
												</div>
												<div class="col-md-5">
													<p class="non_padding" align="left"><strong>Boarding point</strong></p>													
												</div> 
											</div>	
											<c:forEach var="PaxList" items="${requestScope.bookingResponse.PaxList }" varStatus="loop">
											<div class="col-md-12" style="padding: 1% 1%;">
												<div class="col-md-2">
												
													<p class="non_padding" align="left">${PaxList.PassengerName }</p>												
											      										   
												</div>
												<div class="col-md-1">
													<c:if test="${PaxList.Gender eq 'M' }">
													<p class="non_padding" align="left">Male</p>												
											      	</c:if>
											      	<c:if test="${PaxList.Gender eq 'F' }">
													<p class="non_padding" align="left">Female</p>												
											      	</c:if>									   
												</div>
												
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.TicketNumber }</p>													
												</div> 
											
											
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.SeatNo }</p>													
												</div> 
												
												<div class="col-md-2">
													<p class="non_padding" align="left">${PaxList.PNR }</p>													
												</div>
												
												<div class="col-md-5">
													<p class="non_padding" align="left">
														<strong>Time : </strong> ${PaxList.BoardingTime }

													</p>
													<p class="non_padding" align="left">
														<strong>Location : </strong> ${PaxList.BoardingPlace }

													</p>
													<p class="non_padding" align="left">
														<strong>Landmark : </strong> ${PaxList.BoardingLandMark }

													</p>
													<p class="non_padding" align="left">
														<strong>Address :</strong> ${PaxList.BoardingAddress }

													</p>
													<p class="non_padding" align="left">
														<strong>Contact :</strong> ${PaxList.BoardingContactNumber }

													</p>												
												</div> 
												
											</div>	
																					
											</c:forEach>

											</div>	
													
												<!-- <div class="col-md-12 myContainer-title" >
													<label class="pull-left "><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>JOURNEY DETAILS</strong></label>
												</div> -->
											
												<div class="col-md-12" style="padding: 1% 1%;border-top: 1px dashed #ddd;margin-top: 5px;">
													
													<div class="col-md-3">
													
														<p class="non_padding" align="left">
															<strong>Transaction Id</strong>

														</p>
														<p class="non_padding" align="left">
															${bookingResponse.PNRDetails.TransactionId }

														</p>
													
													</div>
													
													<div class="col-md-3">
													
														<p class="non_padding" align="left">
															<strong>Bus Name</strong>

														</p>
														<p class="non_padding" align="left">
															${bookingResponse.PNRDetails.BusName }

														</p>
													
													</div>
													
													<div class="col-md-3">
													
														<p class="non_padding" align="left">
															<strong>Total fare</strong>

														</p>
														<p class="non_padding" align="left">
															Rs. ${requestScope.grossTotal }

														</p>
													
													</div>
													
													<div class="col-md-3">
														<p class="non_padding" align="left">
															<strong>Departure Time</strong>

														</p>
														<p class="non_padding" align="left">
															${requestScope.departureTime }

														</p>
													</div>
													
												</div>	
										
					
										
										</div>
										
										<div class="col-md-12" align="center">
										
											<a href="javascript:window.print();">Print</a>	
										
										</div>
										
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