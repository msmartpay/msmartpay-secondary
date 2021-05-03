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
​
</style>

<script>
	function preloadImages() {
		Image1 = new Image();
		Image1.src = "images/Seat_img/Selected.png";
	}
</script>




 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
    function Book() {         
        var allVals = [];
        $('#seat :checked').each(function() {
        	
        	//background-image: url(images/Seat_img/Available.png)
         /*  var searVal=$(this).val(); 
          var searValArr=searVal.split('_');
          var seatType=searValArr[1];
          var seatNo=searValArr[0]; */
        	
          allVals.push($(this).val());
          /* if(seatType==1){
        	  $('#seat').css('backgroundImage','url(images/Seat_img/Selected.png)');
        	  $('#seat input').val(seatNo+'_2');
          }else{
        	  $('#seat').css('backgroundImage','url(images/Seat_img/Available.png)');
        	  $('#seat input').val(seatNo+'_1');
        	 
          } */
        });
       
       document.getElementById('Details').value=allVals;
       
     }
    $(function() {
      $('#seat input').click(Book);
     
      Book();
    });
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
</style>

	

</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="/header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<!-- <section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>Recharges</h1>
						</div>
					</div>
				</div>
			</section> -->
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 4% 2% 4%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:95%;" >				
					
					<div class="" align="center">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row" style="background: #fff;padding: 2% 1%;">
                                
                                <form name="selctSeat" action="busBook.action?param=seatBlockRequest" method="POST" role="form">
                                
                                    <div class="col-md-9">
                                    
                                    
													<div class="col-md-12 myContainer" style="padding: 0%;">

														<div class="col-md-12 myContainer-title" >
															<label class="pull-left "><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>CONTACT INFORMATION</strong></label>
														</div>
														
														<div class="col-md-12" style="padding: 2% 0%;">
														
															<div class="form-group col-md-6">															                                               
														
																<input type="text" name="emailId" class="form-control" placeholder="Enter Email Id" required="required">
															</div>
	
															<div class="form-group col-md-6">															                                               
															
																<input type="text" name="contactNumber" class="form-control" placeholder="ContactNumber"  maxlength="10" required="required" />
															</div> 
															
															<div class="form-group col-md-4">
																<select class="form-control" name="countryId" id="countryId">
																	<option selected="selected" value="91">India</option>
																</select>															                                               
															</div>
	
															<div class="form-group col-md-4">
																<input type="text" name="address"  class="form-control" placeholder="Address" required="required">													
															</div>
															
															
															
															<div class="form-group col-md-4">
																<input type="text" name="city"  class="form-control" placeholder="City" required="required">													
															</div>
															
															
															<div class="form-group col-md-6">															                                               
															
																<select class="form-control" name="idProofType" id="idProofType">
																	<option selected="selected" value="3">VoterId</option>
																	<option  value="1">Passport</option>
																	<option  value="4">Driving License</option>
																	<option  value="5">PAN Card</option>
																</select>
															</div> 	
															<div class="form-group col-md-6">															                                               
															
																<input type="text" name="idProofNumber" class="form-control" placeholder=" Id proof Number ">
															</div>

														</div>
														
													</div>


										 

													<div class="col-md-12 myContainer" style="padding: 0%;">

														<div class="col-md-12 myContainer-title" >
															<label class="pull-left "><i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>PASSENGER INFORMATION</strong></label>
														</div>
														
														<div class="col-md-12" style="padding: 2% 0%;">
															<c:forEach var="SeatDetails" items="${sessionScope.seatDetails }" varStatus="loop">
	
																<div class="col-md-1">
																
																	<div class="box" style="background: #a94442;padding:8px 4px 2px 1px;">
																		<input class="pull-left" name="passenger" type="checkbox" checked="checked"  />
																		<input  name="SeatNo${loop.index}" type="hidden" value="${SeatDetails.SeatNo}" />
																		<input  name="SeatTypeId${loop.index}" type="hidden" value="${SeatDetails.SeatTypeId}" />
																		<input  name="Fare${loop.index}" type="hidden" value="${SeatDetails.Fare}" />
																		<label class="pull-center"><strong style="color: white;">${SeatDetails.SeatNo}</strong> </label>
																	</div>												
															      										   
																</div>
																<div class="form-group col-md-2">															                                               
																	<select class="form-control" name="passenderTitle${loop.index}" id="passenderTitle">
																		<option selected="selected" value="Mr.">Mr.</option>
																		<option  value="Mrs.">Mrs.</option>
																		<option  value="Miss.">Miss</option>
																	</select>	
																</div>
																
																<div class="form-group col-md-2">
																	<input type="text" name="passengerName${loop.index}"  class="form-control" placeholder="Passenger Name" required="required">													
																</div> 
															
															
																<div class="form-group col-md-1">
																	<input type="text" name="age${loop.index}" class="form-control" placeholder="Age" required="required">													
																</div> 
																<div class="col-sm-2 form-group">
																	<select class="form-control" name="gender${loop.index}" id="gender">
																		<option selected="selected" value="M">Male</option>
																		<option value="F">Female</option>
																	</select>
																</div>
																
																<div class="col-sm-2 form-group">
																	<select class="form-control" name="boardingPoint${loop.index}" id="gender">
																		<option  value="-1">Select Boarding</option>
																		<c:forEach var="BoardingPoints" items="${sessionScope.BoardingPoints }">
																			
																				<option  value="${BoardingPoints.BoardingId }">${BoardingPoints.Place }</option>
																			
																		</c:forEach>
																	</select>
																</div>
																
																<div class="col-sm-2 form-group">
																	
																	<select class="form-control" name="droppingPoint${loop.index}" id="gender">
																		<option  value="-1">Select Dropping</option>
																		<c:forEach var="DroppingPoints" items="${sessionScope.DroppingPoints }">
																			<option  value="${DroppingPoints.DroppingId }">${DroppingPoints.Place }</option>
																		</c:forEach>
																	</select>
																	
																</div>
																									
															</c:forEach>
														</div>
													
										
										
												
							
															<input type="hidden" name="originId" value="${requestScope.originId }" />
															<input type="hidden" name="destinationId" value="${requestScope.destinationId }" />
															<input type="hidden" name="departureTime" value="${requestScope.departureTime }" />
															<input type="hidden" name="scheduleId" value="${requestScope.scheduleId }" />
															<input type="hidden" name="stationId" value="${requestScope.stationId }" />
															<input type="hidden" name="transportId" value="${requestScope.transportId }" />
															<input type="hidden" name="droppingId" value="${requestScope.droppingId }" />
															<input type="hidden" name="travelDate" value="${requestScope.travelDate }" />
															<input type="hidden" name="userTrackId" value="${requestScope.userTrackId }" />	
															<input type="hidden" name="originName"  value="${requestScope.originName}">															 																	                                               
															<input type="hidden" name="destinationName"  value="${requestScope.destinationName}" >
															
															<input type="hidden" name="BusName"  value="${requestScope.BusName}">															 																	                                               
															<input type="hidden" name="TransportName"  value="${requestScope.TransportName}" >
													</div>


										</div>
										
										<div class="col-md-3">
														<div class="col-md-12 myContainer" style="padding: 0%;" >
														
															<div class="col-md-12 myContainer-title" >
																	<label class="pull-center"><strong>JOURNEY DETAILS</strong></label>
															</div>
															
															<div class="col-md-12" style="padding: 6% 0%;">
														
															<div class="col-md-12" >
																
																<div class="col-md-6">
																
																	<label class="pull-left">
																		${requestScope.originName}

																	</label>
																
																</div>
																
																<div class="col-md-6">
																	<label class="pull-left">
																		${requestScope.destinationName}

																	</label>
																</div>
																
																<div class="col-md-6">
																
																	<label class="pull-left">
																		${requestScope.travelDate}

																	</label>
																
																</div>
																
																<div class="col-md-6">
																
																	<label class="pull-left">
																		${requestScope.departureTime}

																	</label>
																
																</div>
																
															</div>
															
															
															
															<div class="col-md-12" >
																<c:set var="totalFare" value="0" />
																<c:set var="totalServiceTax" value="0" />
																<c:set var="totalConvenienceFee" value="0" />
																<c:forEach var="SeatDetails" items="${sessionScope.seatDetails }" varStatus="loop">
																
																	<div class="col-md-12" >
																
																		<c:set var="totalFare" value="${totalFare+SeatDetails.Fare}" />
																		<c:set var="totalServiceTax" value="${totalServiceTax+SeatDetails.ServiceTax}" />
																		<c:set var="totalConvenienceFee" value="${totalConvenienceFee+SeatDetails.ConvenienceFee}" />
																	
																	</div>
																	
																</c:forEach>
															
															
															</div>

															</div>

															<div class="col-md-12 myContainer-title" >
																<label class="pull-center"><strong>FARE DETAILS</strong></label>  
															</div>

															<div class="col-md-12" style="padding: 6% 0%;">
															
																	<div class="col-md-8">
																		<label class="pull-left">You are paying</label>
																	</div>
																	<div class="col-md-4">
																		<input type="hidden" name="totalAmount" value="${totalFare}" />
																		<label class="pull-right">${totalFare}</label>
																	</div>
																
																	<div class="col-md-8">
																		<label class="pull-left">GST</label>
																	</div>
																	<div class="col-md-4">
																		<input type="hidden" name="totalServiceTax" value="${totalServiceTax}" />
																		<label class="pull-right">${totalServiceTax}</label>
																	</div>
																
																	<div class="col-md-8">
																	
																		<label class="pull-left">Convenience Fee</label>
																	</div>
																	<div class="col-md-4">
																		<input type="hidden" name="totalConvenienceFee" value="${totalConvenienceFee}" />
																		<label class="pull-right">${totalConvenienceFee}</label>
																	</div>
																	
																	<div class="col-md-8">
																		<label class="pull-left">Service Charge</label>
																	</div>
																	<div class="col-md-4">
																		<label class="pull-right">0.0</label>
																	</div>
																	
																	
																	<div class="col-md-8">
																		<label class="pull-left"><strong>Total Amount</strong></label>
																	</div>
																	<div class="col-md-4">
																		<label class="pull-right"><strong>${ totalFare+totalServiceTax+totalConvenienceFee}</strong></label>
																	</div>
																
															</div>
															
															</div>

														
														<!--  ========================================================== -->
										</div>
                                
                                
                                	<div class="col-md-12" style="padding: 2% 2% ;">
										<div class="col-md-8" style="padding: 0%;">
										
										</div>
										<div class="col-md-4" align="right" style="padding: 0%;">
											<input type="submit"  class="btn btn-primary btn-inline" value="Block Seat"  onclick="searchSeats" class="btn-default" />
										</div>
										
									
									</div>
                                
                                
                                </form>
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