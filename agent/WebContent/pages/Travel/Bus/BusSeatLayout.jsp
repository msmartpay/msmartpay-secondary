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
€‹
</style>

<script>
	function preloadImages() {
		Image1 = new Image();
		Image1.src = "images/Seat_img/Selected.png";
	}
</script>




 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
    var allVals = [];
    function selecetSeat(idval) {         
        
        seat=idval;;
        name=$('#'+idval).attr('name');
        if('1'==name){
        	$('#'+seat).attr('src','images/Seat_img/Selected.png');
        	$('#'+idval).attr('name','2');
        	allVals.push(seat);
        }else if('11'==name){
        	$('#'+seat).attr('src','images/Seat_img/SelectedBerth.png');
        	$('#'+idval).attr('name','22');
        	allVals.push(seat);
        }else if('2'==name){
        	$('#'+seat).attr('src','images/Seat_img/Available.png');
        	$('#'+idval).attr('name','1');
        	for(var i = allVals.length - 1; i >= 0; i--) {
        	    if(allVals[i] === seat) {
        	    	allVals.splice(i, 1);
        	    }
        	}
        }else if('22'==name){
        	$('#'+seat).attr('src','images/Seat_img/AvailableBerth.png');
        	$('#'+idval).attr('name','11');
        	for(var i = allVals.length - 1; i >= 0; i--) {
        	    if(allVals[i] === seat) {
        	    	allVals.splice(i, 1);
        	    }
        	}
        }
        
        //$('#seat :checked').each(function() {
        	
        	//background-image: url(images/Seat_img/Available.png)
         /*  var searVal=$(this).val(); 
          var searValArr=searVal.split('_');
          var seatType=searValArr[1];
          var seatNo=searValArr[0]; */
        	
          
          /* if(seatType==1){
        	  $('#seat').css('backgroundImage','url(images/Seat_img/Selected.png)');
        	  $('#seat input').val(seatNo+'_2');
          }else{
        	  $('#seat').css('backgroundImage','url(images/Seat_img/Available.png)');
        	  $('#seat input').val(seatNo+'_1');
        	 
          } */
        //});
       
       document.getElementById('Details').value=allVals;
       
     }
    
    function GoBack(){
    	document.selctSeat.action='busSearch.action?param=backSearchBus';
    	$('#resultLoading').show();
    	document.selctSeat.submit();
    }
    function bookSeat(){
    	$('#resultLoading').show();
    	document.selctSeat.submit();
    }
    
    //document.getElementById('layout').style.zoom="50%";
   
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
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: #fcfcfc;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8">
										<div class="box" style="padding: 20px;">
												<form action="contect_detail.html" method="POST" role="form">
													<div class="col-md-12">

														

														<!-- <input type="text" id="Seats" name="seats"> <input
															type="text" name="fare" class="fare"> -->

														<div class="col-md-3">
															<label class="pull-left">${requestScope.originName}</label>
														</div>
														
														<div class="col-md-3">
															<label class="pull-left">${requestScope.destinationName}</label>
														</div>

														<div class="col-md-3">
															<label class="pull-left">${requestScope.travelDate}</label>
														</div>

														<div class="col-md-3">

															<label class="pull-left">${requestScope.departureTime }</label>

														</div>




														
													</div>
												</form>
										</div>


									<form name="selctSeat" action="busSearch.action?param=seatBookDetails" method="post" >
										<div class="search-content" style="padding-top:1%;">
										 
												<div class="box myContainer" style="padding: 0">
												
														<div class="col-md-12 myContainer-title" style="padding: 20px;">
															<div class="col-md-4">
																Selected Seats
															</div>
															<div class="col-md-8">
																<input type="text" value="" name="Details" id="Details" />
															</div>
															
														
														</div>
														
														<div id="layout" class="col-md-12" align="center" style="padding: 0;">
														
														
															<c:forEach var="SeatsList" 	items="${requestScope.SeatsList}">
																<c:forEach var="SeatMapOutputList" items="${SeatsList.SeatMapOutput}">
																	<div class="col-md-12" style="padding: 4%;zoom:70%;">
																	<c:forEach var="SeatLayoutDetailsList" items="${SeatMapOutputList.SeatLayoutDetails}">
																		
																		<c:forEach var="SeatLayoutList" items="${SeatLayoutDetailsList.SeatLayout}">
																		
																			
																		
																			<div class="col-md-6">
																			
																				<div class="col-md-12" align="right" style="height: 25px;padding: 0 30%;margin-bottom: 15px;">
																						<img src="images/Seat_img/Steer.png" alt="Available" style="cursor: pointer;">
																				</div>
																				
																				<table>
																			<c:forEach var="SeatColumnsList" items="${SeatLayoutList.SeatColumns}">
																			
																				<tr>
																			
																				<c:forEach var="Seats" items="${SeatColumnsList.Seats}">
																				
																				<td>
																				
																					<c:choose>
																						<c:when test="${Seats ne null && Seats.BerthType eq 'L' }">
																							<div id="seat" >
																							
																								<c:if test="${'Available' eq Seats.SeatStatus}">
																									<c:if test="${'F' eq Seats.Gender}">
																										<c:choose>
																											<c:when test="${Seats.SeatType eq 'Seater' || Seats.SeatType eq 'SEATER' }">
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/Available.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="1">
																											</c:when>
																											<c:otherwise>
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/AvailableBerth.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="11">
																											</c:otherwise>
																										</c:choose>
																										
																									</c:if>
																									<c:if test="${'M' eq Seats.Gender}">
																									<c:choose>
																											<c:when test="${Seats.SeatType eq 'Seater' || Seats.SeatType eq 'SEATER'}">
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/Available.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="1">
																											</c:when>
																											<c:otherwise>
																											
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/AvailableBerth.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="11">
																											</c:otherwise>
																										</c:choose>
																										
																									</c:if>
																								</c:if>
																								<c:if test="${'Booked' eq Seats.SeatStatus}">
																									<c:if test="${'F' eq Seats.Gender}">
																										<c:choose>
																											<c:when test="${Seats.SeatType eq 'Seater' || Seats.SeatType eq 'SEATER'}">
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/LadiesBooked.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="3">
																											</c:when>
																											<c:otherwise>
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/LadiesBookedBerth.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="33">
																											</c:otherwise>
																										</c:choose>
																										
																									</c:if>
																									<c:if test="${'M' eq Seats.Gender}">
																										<c:choose>
																											<c:when test="${Seats.SeatType eq 'Seater' || Seats.SeatType eq 'SEATER'}">
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/Booked.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="3">
																											</c:when>
																											<c:otherwise>
																												<img title="${Seats.SeatNo}" alt="seat" src="images/Seat_img/BookedBerth.png" onclick="selecetSeat(this.id)" id="${Seats.SeatNo}" name="33">
																											</c:otherwise>
																										</c:choose>
																										
																									</c:if>
																								</c:if>	
																							</div>
																						</c:when>
																						<c:otherwise>
																							<div id="seat" >
																								<img src="images/Seat_img/blank.png" alt="Available" style="cursor: pointer;">
																							</div>
																						</c:otherwise>
																					</c:choose>
																				     
																				</td>
																				
																				
																				
																				</c:forEach> 
																				</tr>
																			</c:forEach>
																			
																			</table>
																			</div>
																			
																			<c:if test="${SeatLayoutDetailsList.uppserStatus eq 'Y' }">
																			<div class="col-md-6">
																				<div class="col-md-12" align="center" style="height: 25px;padding: 0 0%;margin-bottom: 15px;">
																						<h5>UPPER BERTH</h5>
																				</div>
																				
																				<table>
																				<c:forEach var="SeatColumnsList" items="${SeatLayoutList.SeatColumns}">
																			
																				<tr>
																			
																				<c:forEach var="UpperSeats" items="${SeatColumnsList.UpperSeats}">
																				
																				<td>
																				
																					<c:choose>
																						<c:when test="${UpperSeats ne null && UpperSeats.BerthType eq 'U' }">
																							<div id="seat" >
																							
																								<c:if test="${'Available' eq UpperSeats.SeatStatus}">
																									<c:if test="${'F' eq UpperSeats.Gender}">
																										<img title="${UpperSeats.SeatNo}" alt="seat" src="images/Seat_img/AvailableBerth.png" onclick="selecetSeat(this.id)" id="${UpperSeats.SeatNo}" name="11">
																									</c:if>
																									<c:if test="${'M' eq UpperSeats.Gender}">
																										<img title="${UpperSeats.SeatNo}" alt="seat" src="images/Seat_img/AvailableBerth.png" onclick="selecetSeat(this.id)" id="${UpperSeats.SeatNo}" name="11">
																									</c:if>
																								</c:if>
																								<c:if test="${'Booked' eq UpperSeats.SeatStatus}">
																										<c:if test="${'F' eq UpperSeats.Gender}">
																										<img title="${UpperSeats.SeatNo}" alt="seat" src="images/Seat_img/LadiesBookedBerth.png" onclick="selecetSeat(this.id)" id="${UpperSeats.SeatNo}" name="33">
																									</c:if>
																									<c:if test="${'M' eq UpperSeats.Gender}">
																										<img title="${UpperSeats.SeatNo}" alt="seat" src="images/Seat_img/BookedBerth.png" onclick="selecetSeat(this.id)" id="${UpperSeats.SeatNo}" name="33">
																									</c:if>
																								</c:if>	
																							</div>
																						</c:when>
																						<c:otherwise>
																							<div id="seat" >
																								<img src="images/Seat_img/blank.png" alt="Available" style="cursor: pointer;">
																							</div>
																						</c:otherwise>
																					</c:choose>
																				     
																				</td>
																				
																				
																				
																				</c:forEach> 
																				</tr>
																				</c:forEach>
																				</table>
																			</div>
																			 
																			</c:if> 
																		</c:forEach>
																	</c:forEach>
																	</div>
																	
																	
																	<input type="hidden" name="stationId" value="${requestScope.stationId}">
																	<input type="hidden" name="transportId" value="${requestScope.transportId}">
																	<input type="hidden" name="travelDate" value="${requestScope.travelDate}">
																	<input type="hidden" name="departureTime" value="${requestScope.departureTime}">
																	<input type="hidden" name="userTrackId" value="${requestScope.userTrackId}">
																	<input type="hidden" name="scheduleId" value="${requestScope.scheduleId}">
																	<input type="hidden" name="originId" value="${requestScope.originId}">
																	<input type="hidden" name="destinationId" value="${requestScope.destinationId}">
																	<input type="hidden" name="originName"  value="${requestScope.originName}">															 																	                                               
																	<input type="hidden" name="destinationName"  value="${requestScope.destinationName}" >
																	<input type="hidden" name="droppingId" value="${requestScope.droppingId}">
																	
																	<input type="hidden" name="BusType" value="${requestScope.BusType}">
																	<input type="hidden" name="BusName" value="${requestScope.BusName}">
																	<input type="hidden" name="TransportName" value="${requestScope.TransportName}">
																	<input type="hidden" name="ArrivalTime" value="${requestScope.ArrivalTime}">
																	<input type="hidden" name="noofperson" value="${requestScope.noofperson}">
																	
																	
																	
																	<div class="form-group col-md-12" style="float: right;">	
																						
	                                           							 <button type="button"  class="btn btn-primary btn-inline"  onclick="bookSeat()" class="btn-default"><small>Proceed</small></button>
																		<button type="button"  class="btn btn-primary btn-inline"  onclick="GoBack()" class="btn-default"><small>Back</small></button>
																	</div>
																	
																	
																</c:forEach>
															</c:forEach>
															
														</div>
														
														
														
													</div>	

												
												
											
											
											
										</div>									
										</form>	


										</div>
										
										<div class="col-md-4">
											<div class="box">
												<div class="tabs">
													<div class="tab-content">
														<div class="search-content" style="padding: 3%;">
															<div class="col-md-5" style="padding: 2px;">
																<ul class="list_unstyled"
																	style="list-style: none; padding-left: 0px;">
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/LadiesBooked.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			Ladies
																	</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/Available.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			Available</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/Selected.png" alt="">
																		</div>
																		<div class="row_no1" style="font-size: 12px;">
																			Selected</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/Booked.png" alt="">
																		</div>
																		<div class="row_no1" style="font-size: 12px;">
																			Booked</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/Blocked.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			Blocked</div>
																	</li>

																</ul>
															</div>


															<div class="col-md-6" style="padding: 2px;">
																<ul class="list_unstyled"
																	style="list-style: none; padding-left: 0px;">
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/LadiesBookedBerth.png"
																				alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			LadiesBookedBerth</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/AvailableBerth.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			AvailableBerth</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/SelectedBerth.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			SelectedBerth</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/BookedBerth.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			BookedBerth</div>
																	</li>
																	
																	<li>
																		<div class="form-group">
																			<img src="images/Seat_img/BlockedBerth.png" alt="">
																		</div>
																		<div class="form-group" style="font-size: 12px;">
																			BlockedBerth</div>
																	</li>
																</ul>
															</div>
														</div>
														<!--  ========================================================== -->
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
		
		<section class="page-content" style="padding-top: 80px;background-color: #fff;height: 400px;">
			<div class="container" style="width:90%;" style="width: 95%;">	
                <div class="row">
                    <div class="col-md-12">
                        <h2>Welcome to World of ${sessionScope.companyName }</h2>
                        <p>Safe and Instant Recharges, Bill Payments Service And Many more...</p>
                        <p>A unique E-Solution at single platform.</p>
                        <p>We promise to provide you with outstanding service that you can trust for all of your E-Service needs.</p>
                        <a href="#" class="btn btn-primary">Book Your Bus Now ...</a>
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
	<div id="travelbusloading" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;display:none;">
			<div style="width: 100%; text-align: center; position: absolute; left: 0px; top: 25%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
				
				
					<img src="images/loader.gif" width="500">

			</div>
			<div class="bg"
				style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: rgb(0, 0, 0);"></div>
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