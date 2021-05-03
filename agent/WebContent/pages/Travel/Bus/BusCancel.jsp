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
	
	function checkValidation(k)
	{

		var boxesTicked1 = "";

		for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
		{
			if (document.getElementsByName('checkpartial')[i].checked)
	 		{
				boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n";
			}
		}
		if (boxesTicked1 <1 ) 
		{
			alert("Please Select Atleast One Ticket.");
			return boxesTicked1;
		}



		return true;
	}
	function submitForm(i){
		var k=i;
		if(checkValidation(k))
		{
			document.CancelTicket.action='busCancel.action?param=cancelTicket';
			document.CancelTicket.submit();
		}
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
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center">
						<div class="cta-inner">
							
							<div class="cta-txt">
                                <div class="row">
                                
                                	<c:if test="${requestScope.PNRDetails eq null }">
                                	
	                                <div class="row" align="center">
	                                    <div class="col-md-8 col-md-offset-2" style="width: 50%;margin-left: 25%;">
	                                        <div class="box">                                                                        
	                                            <form name="busCancel" method="post" action="busCancel.action?param=busCancelPNRSearch">
	                                            	<div  align="center">
														<label  style="color: RED;font-weight: bold;">${requestScope.message }</label>
													</div><br>                                                       
	                                                <div class="form-group">
											
														<input name="PNRNumber" onkeypress="return digitonly(this,event)" maxlength="13" type="text" class="form-control" placeholder="Enter PNR Number">
													</div>
	                                               <div class="form-group col-md-12">                                                      
	                                                	<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-primary btn-inline">Get Details</button>
	                                               </div>
	                                            </form>
	                                    </div>
	                                    </div>                                    
	                                                                
	                                </div>
                        			</c:if>
                                	<c:if test="${requestScope.PNRDetails!=null }">
                                	<div class="box" >
                                    	
                                    	<form action="" method="post" name="CancelTicket">
                                    	<div class="col-md-12 myContainer" style="padding: 0%;">
                                    
                                    		<c:if test="${requestScope.CancellationPenaltyDetailsStatus eq 'Y' }">
                                    
	                                    		<div class="col-md-12 myContainer-title" >
													<label class="pull-left "><i class="fa fa-bus" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>TRAVEL DETAILS</strong></label>
												</div>
	                                    
												<div class="col-md-12" style="padding: 1% 2%;background-color: #fd0000;color: #fff;font-weight: bold;">
	
													<input type="hidden" name="PNRNumber" value="${sessionScope.CancellationPenaltyDetails.PNRNumber}" />
													<input type="hidden" name="TransportId" value="24" />
	
													<div class="col-md-3" >															                                               
														<p class="non_padding" align="left">PNR : ${sessionScope.CancellationPenaltyDetails.PNRNumber} ,${requestScope.CancellationPenaltyDetails.PNRStatus}</p>	
													</div>
													
													<div class="col-md-3" >															                                               
														<p class="non_padding" align="left">${sessionScope.CancellationPenaltyDetails.Origin} to ${sessionScope.CancellationPenaltyDetails.Destination}</p>	
													</div>
													
													<div class="col-md-3">															                                               
												
														<p class="non_padding" align="left">${sessionScope.CancellationPenaltyDetails.TravelDate} , ${sessionScope.CancellationPenaltyDetails.DepatureTime}</p>
													</div>
													
													<div class="col-md-3">
														<p class="non_padding" align="left">${sessionScope.CancellationPenaltyDetails.TransportName}</p>
													</div>
													
												</div>
												
												
												<div class="col-md-12 myContainer-title" >
												<label class="pull-left "><i class="fa fa-user" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>PASSENGER INFORMATION</strong></label>
											</div>
											<div class="col-md-12" style="margin-top: 5px;">
											<div class="col-md-12" style="padding: 1% 1%;">
											
												<div class="col-md-2">
													<p class="non_padding" align="left">Cancel ( <i class="fa fa-check" aria-hidden="true"></i> )</p>													
												</div>
											
												<div class="col-md-2">
												
													<p class="non_padding" align="left"><strong>Name</strong></p>												
											      										   
												</div>
												
												<div class="col-md-2">
													<p class="non_padding" align="left"><strong>Ticket No.</strong></p>													
												</div>
												<div class="col-md-1">
													<p class="non_padding" align="left"><strong>Gender</strong></p>													
												</div> 
											
												<div class="col-md-1">
												
													<p class="non_padding" align="left"><strong>Age</strong></p>												
											      										   
												</div>
											
												<div class="col-md-1">
													<p class="non_padding" align="left"><strong>Seat No.</strong></p>													
												</div> 
												
												<div class="col-md-1">
													<p class="non_padding" align="left"><strong>Fare</strong></p>													
												</div>
												<div class="col-md-2">
													<p class="non_padding" align="left"><strong>Penatly Amount</strong></p>													
												</div> 
											</div>	
											<c:forEach var="PaxList" items="${requestScope.CancellationPenaltyDetails.CancelPaxList}" varStatus="loop">
											<div class="col-md-12" style="padding: 1% 1%;">
											
											
												<input type="hidden" name="TicketNumber${loop.index }" value="${PaxList.TicketNumber }" />
												<input type="hidden" name="PenatlyAmount${loop.index }" value="${PaxList.PenatlyAmount }" />
											
												<div class="col-md-2 ">
													<input class="pull-left" type="checkbox" name="checkpartial" id="chek${loop.index }"  value="${loop.index }"/>												
												</div>
											
												<div class="col-md-2">
												
													<p class="non_padding" align="left">${PaxList.PassengerName }</p>												
											      										   
												</div>
												
												<div class="col-md-2">
													<p class="non_padding" align="left">${PaxList.TicketNumber }</p>													
												</div>
												
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.Gender }</p>													
												</div>
												
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.Age }</p>													
												</div> 
											
											
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.SeatNo }</p>													
												</div> 
												
												<div class="col-md-1">
													<p class="non_padding" align="left">${PaxList.Fare }</p>													
												</div>
												
												<div class="col-md-2">
													<p class="non_padding" align="left">${PaxList.PenatlyAmount }</p>
												</div> 
												
											</div>	
																					
											</c:forEach>

											</div>	
													
											<div class="col-md-12 myContainer-title" >
												<label class="pull-left "><i class="fa fa-envelope" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;<strong>Cancellation Policy Details</strong></label>
											</div>
											
											<div class="col-md-12" style="padding: 1% 1%;">
												
												<p>
												
													${requestScope.cancelPolicy }
												
												</p>
												
											</div>

											</c:if>
											<c:if test="${requestScope.CancellationPenaltyDetailsStatus eq 'N' }">
												<div class="col-md-12 " style="margin: 10% 0%;">
													<label class="pull-center " style="color: RED;font-size: 28px;">${requestScope.CancellationPenaltyDetails}</label>
												</div>
											</c:if>
										 

										</div>
										
										<div class="col-md-12" align="center">
										
											<c:if test="${requestScope.CancellationPenaltyDetailsStatus eq 'Y' }">
												<input type="button"  class="btn btn-primary btn-inline" value="Proceed to cancel"  onclick="submitForm()" class="btn-default" />
										
											</c:if>
											<c:if test="${requestScope.CancellationPenaltyDetailsStatus eq 'N' }">
												<a href="busSearch.action?param=BusPage">Go Back</a>
										
											</c:if>
										
											
										</div>
										</form>
									</div>
									</c:if>	
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