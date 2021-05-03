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
	/* Popup container - can be anything you want */
.popup {
    position: relative;
    display: inline-block;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

/* The actual popup */
.popup .popuptext {
    visibility: hidden;
    width: 800px;
    background-color: rgba(223, 223, 223, 0.70);
    color:#111;
    text-align:justify;
    border-radius: 6px;
    /* padding: 8px 0; */
    position: absolute;
    z-index: 1;
    bottom: 125%;
    left:10%;
    margin-left: -200px;
   /*  box-shadow: 5px 10px #ff3200; */
   
}

/* Popup arrow */
.popup .popuptext::after {
    content: "";
    position: absolute;
    top: 100%;
    left: 45%;
    margin-left: -5px;
    border-width: 5px;
    border-style: solid;
    border-color: #111 transparent transparent transparent;
}

/* Toggle this class - hide and show the popup */
.popup .show {
    visibility: visible;
    -webkit-animation: fadeIn 1s;
    animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
@-webkit-keyframes fadeIn {
    from {opacity: 0;} 
    to {opacity: 1;}
}

@keyframes fadeIn {
    from {opacity: 0;}
    to {opacity:1 ;}
}
</style>

	<script type="text/javascript">
	
	


	/* function validateDataCard(){
		var mobileOp=document.DataCardForm.OperatorDataCrd.value;
		var amount=document.DataCardForm.amountDataCrd.value;
		var mobileNo=document.DataCardForm.datacardno.value;
		if(amount>5000)
		{
		alert("Recharge amount can't be more than Rs 5000");
		return false;
		}
		else if(isNaN(amount))
		{
		alert("Recharge amount be should numeric");
		return false;
		}
		else if( parseFloat(amount)!= parseInt(parseFloat(amount))){
		alert ('Recharge amount decimal value not allowed');
		return false;
		}
		else{
			document.getElementById('submitdatacard').style.display="none";
			document.getElementById("submitdatacardloading").style.display="";
			document.DataCardForm.submit();
		}
	}

	function validateDTH(){

		var mobileOp=document.DTHForm.OperatorDTH.value;
		var amount=document.DTHForm.amountDTH.value;
		var mobileNo=document.DTHForm.DTHNo.value;
		if(parseFloat(amount)!= parseInt(parseFloat(amount)))
		{
			alert ('Recharge amount decimal value not allowed');
			return false;
		}
		else if(isNaN(amount))
		{
			alert("Recharge amount be should numeric");
			return false;
		}
		else if(amount>10000)
		{
			alert("Recharge amount can't be more than Rs 10000");
			return false;
		}
		
		else if( parseFloat(amount)!= parseInt(parseFloat(amount))){
			alert ('Recharge amount decimal value not allowed');
			return false;
		}
		else if(mobileOp=="VIDEOCONDTH" && amount>10000)
		{
			alert("Recharge amount can't be more than Rs 10000");
			return false;
		}
		else if(mobileOp=="TATASKY" && amount<100)
		{
			alert("Recharge amount should be Rs 100 or More ");
			return false;
		}
		else if(mobileOp=="TATASKY" && amount>10000)
		{
			alert("Recharge amount can't be more than Rs. 10000.");
			return false;
		}
		else if(mobileOp=="DISH" && amount<10)
		{
			alert("Recharge amount should be Rs 10 or More");
			return false;
		}
		else{
			document.getElementById('submitdth').style.display="none";
			document.getElementById("submitdthloading").style.display="";
			document.DTHForm.submit();
		}

	} */
	
	function digitonly(input,evt)
	{
		var keyCode = evt.which ? evt.which : evt.keyCode;
				var lisShiftkeypressed = evt.shiftKey;
				if(lisShiftkeypressed && parseInt(keyCode) != 9) {
				return false ;
				}
				if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
				return true;
				}

				return false;

	}
	
	function validateBusForm(){

		document.getElementById('submitmobile').style.display="none";
		document.getElementById("submitmobileloading").style.display="";
		document.BusForm.submit();
	}
	
	</script>

</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="/header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading" style="padding: 1% 2% 0% 3%;">
				<div class="container" style="width:100%;">
					<div class="row">
						<div class="col-md-12">
						<div class="search-content" >
							<form name="modify" action="modify.action?param=modifyOrigin" method="post">
							<div class="col-md-12">
								<div class="form-group col-md-3">															                                               
								<input type="text" name="originId" class="form-control" value="${requestScope.originId}" placeholder="origin" required="required">															 
								</div> 
																
								<div class="form-group col-md-3">															                                               
								<input type="text" name="destinationId" class="form-control" value="${requestScope.destinationId}" placeholder="Destination" required="required">															 
								</div> 		
																
								<div class="form-group col-md-3">															                                          
								<input type="text" name="travelDate" class="form-control" value="${requestScope.travelDate}" placeholder="Enter Email ID" required="required">															
								</div> 
														
								<div class="form-group col-md-1">															                                             
								<font color="black"><strong><input type="number" class="form-control" min="1" max="30" step="1" value ="${requestScope.noofperson}" required="required"/></strong></font>															
								</div> 
														
														
								<div class="form-group col-md-2">
								
															
								<button type="submit" class="btn btn-primary btn-inline" style="padding-top: 3%;padding-bottom: 3%" > Modify Search</button>
								</div>	
								
								</div>
							</form>											
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 1% 5% 1% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-3">
										<div class="box">									
											<div class="tabs">
												<div class="tab-content">
													<div class="search-content" style="padding-left:4%;">
													<label class="pull-left">											
													<a href="#"><font size="2px"><strong>Bus Type</strong></font></a>											
												    </label> <br><br>   
													
													
													<div class="table-responsive" align="left" >
																	
													<input type="checkbox"  name="cheak" value="AC"><strong>A/C</strong><br>
													<input type="checkbox" name="cheak" value="AC"><strong>NON - A/C</strong><br>
													<input type="checkbox"  name="cheak" value="AC"><strong>Sleeper</strong><br>
													<input type="checkbox"  name="cheak" value="AC"><strong>Semi Sleeper</strong><br>
													<input type="checkbox"  name="cheak" value="AC"><strong>Seater</strong><br>
													<input type="checkbox"  name="cheak" value="AC"><strong>Berth</strong><br>
													
													</div>
												
													<label class="pull-left" class="form-group">											
													<a href="#"><font size="2px"><strong>Transport Filter</strong></font></a>											
												    </label> <br><br> 
													
													<div class="table-responsive" align="left" >
																								
													<input type="checkbox" class="form-group" name="cheak" value="AC">Anamika Bus<br>									<input type="checkbox" class="form-group" name="cheak" value="AC">Anand Holiday<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">DBS Holiday<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Delhi tdavels Online<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Fouji Bus<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Goga Ji Bus tdavels<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">HANS tdavels<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Himachal Yatara<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Kalpana Bus<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">RS yadav tdavels<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Rathi Tour and tdavels<br>
													<input type="checkbox" class="form-group" name="cheak" value="AC">Volvo Bus tdavels<br>
													
													</div>
												</div>
												</div>
											</div>
										</div>
									</div>
                                    <div class="col-md-9">	                      			
								<div class="box" >													
								<div class="tabs">	<!-- style="box-shadow: 5px 5px 0px #888888; -->				
									<div class="tab-content" style="padding: 0px; background: red;color: white;"> <!-- style="box-shadow: 5px 5px 0px #888888;"  -->
												
											 <div class="form-group col-md-4" style="margin-bottom: 0px">
												<label class="pull-left"><strong style="color: white;">Transport</strong> </label>    
												</div>
												<div class="form-group col-md-6" style="margin-bottom: 0px;">
												 <div class="form-group col-md-4"style="margin-bottom: 0px;">
												 	<label class="pull-left"><strong style="color: white;">Departure</strong> </label>    
												</div>
												
												 <div class="form-group col-md-4" style="margin-bottom: 0px;">
												<label class="pull-left"><strong style="color: white;">Arrial</strong></label>    
												</div>
												
												 <div class="form-group col-md-2" style="margin-bottom: 0px;">
												<label class="pull-left"><strong style="color: white;">Fare </strong>  </label>    
												</div>
												<div class="form-group col-md-2" style="margin-bottom: 0px;">
													<label class="pull-left"><strong style="color: white;">Seats</strong><small>(Left)</small></label>    
												</div>
												
											</div>
												
										</div>
	
									<!-- ================================================================== -->   
									<c:forEach var="AvalableList" items="${requestScope.AvalableList}" >
									<c:forEach var="AvailableBusesList" items="${AvalableList.AvailableBuses}" >	
									<c:forEach var="FaresList" items="${AvailableBusesList.Fares}" >
									 <c:forEach var="BoardingPointsList" items="${AvailableBusesList.BoardingPoints}" >
									
									<div class="tab-content" style="margin-top:1%; padding: 0px;" >			
										
									<form name="selctSeat" action="busSearch.action?param=searchSeatspage" method="POST" role="form">
										<div class="search-content" style="padding-top:1%;">
										 
										  	
											 	<div class="form-group col-md-4">
												<label class="pull-left">
												<strong>${AvailableBusesList.TransportName}</strong>											
												<br><small>${FaresList.SeatType}</small><!-- <span class="required" style="color:gold;"><br>**** 2rating</span> -->
											    </label>  
												</div>

												<input type="hidden" name="travelDate" value="${requestScope.travelDate}">
												<input type="hidden" name="ScheduleId" value="${AvailableBusesList.ScheduleId}">
												<input type="hidden" name="StationId" value="${AvailableBusesList.StationId}">
												<input type="hidden" name="BusType" value="${AvailableBusesList.BusType}">
												<input type="hidden" name="BusName" value="${AvailableBusesList.BusName}">
												<input type="hidden" name="TransportId" value="${AvailableBusesList.TransportId}">
												<input type="hidden" name="DepartureTime" value="${AvailableBusesList.DepartureTime}">
												<input type="hidden" name="Commission" value="${AvailableBusesList.Commission}">
												<input type="hidden" name="AvailableSeatCount" value="${AvailableBusesList.AvailableSeatCount}">
												<input type="hidden" name="TransportName" value="${AvailableBusesList.TransportName}">
												<input type="hidden" name="ArrivalTime" value="${AvailableBusesList.ArrivalTime}">
												
												<input type="hidden" name="UserTrackId" value="${AvalableList.UserTrackId}">
												
												
											<div class="form-group col-md-6" style="margin-bottom: -9px;">
												
												 <div class="form-group col-md-4">
												
												<strong>${AvailableBusesList.DepartureTime }</strong> <br>
																	
												<div class="popup" onclick="myFunction()">
														<a href="#"><font size="2px">Boarding</font></a>
														 <div class="popuptext" id="myPopup">
														 <table class="table table-hover" style="margin-bottom: 0px;">
	                 									   <thead style="background: #ddd;">
	                       										 <tr>
	                            									<th>Place</th>
																    
																    <th>Time</th>
																    <th>Address</th>
																     <th>Landmark</th>
																    <th>Contact No</th>
	                            						      </tr>
										                    </thead>
										                    <tbody>
										                   <%--  <c:forEach var="DroppingPointsList" items="${AvailableBusesList.DroppingPoints}" > --%>
								                        <tr style="background: white; border: 1px solid red;">
								                            <td class="col-md-1"><em><small>  ${BoardingPointsList.Place}</small></em></td>
								                            <td class="col-md-1"> <small> ${BoardingPointsList.Time}</small> </td>
								                            <td class="col-md-1 "> <small> ${BoardingPointsList.Address}</small></td>
								                            <td class="col-md-1 "> <small>${BoardingPointsList.LandMark}</small></td>
								                             <td class="col-md-1 "> <small>${BoardingPointsList.ContactNumber}</small></td>
								                             
								                             
								                            
								                        </tr>
								                      <%--  </c:forEach> --%>
									                    </tbody>
									                </table>
														 
														 
												</div>
												</div>
											   
												</div>
												
												 <div class="form-group col-md-4">
												
												<strong>${AvailableBusesList.ArrivalTime }</strong><br>
												<div class="popup" onclick="myFunction1()">
														<a href="#"><font size="2px">Dropping </font></a>
														 <span class="popuptext" id="myPopup1">													  
														 
														 <c:forEach var="DroppingPointsList" items="${AvailableBusesList.DroppingPoints}" >
														 <strong>Place</strong>${DroppingPointsList.Place}<br>
														 <strong>Time</strong>${DroppingPointsList.Time}<br>
														 <strong>Address</strong>${DroppingPointsList.Address}<br>
														 <strong>Landmark</strong>${DroppingPointsList.LandMark}<br>
														 <strong>Contactno</strong>${DroppingPointsList.ContactNumber}<br>
														 
														 <input type="text" name="DroppingId" value="${DroppingPointsList.DroppingId}"/>
														 
														 </c:forEach>
														 </span>
												</div>
												
											   
												</div>
												
												 <div class="form-group col-md-2">																		
												
												<strong>Rs.${FaresList.Fare}</strong><br>											
												<div class="popup" onclick="myFunction2()">
														<a href="#"><font size="2px">Fares </font></a>
														<div class="popuptext" id="myPopup2">													
														 
														 
														 <table class="table table-hover" style="margin-bottom: 0px;">
	                 									   <thead style="background: #ddd;">
	                       										 <tr>
	                            									<th>SeatTypeId</th>
																    <th>SeatType</th>
																    <th>Fare</th>
																     <th>ServiceTax</th>
																    <th>ConvenienceFee</th>
	                            						      </tr>
										                    </thead>
										                    <tbody>
										                   <%--  <c:forEach var="DroppingPointsList" items="${AvailableBusesList.DroppingPoints}" > --%>
								                        <tr style="background: white; border: 1px solid red;">
								                            <td class="col-md-1"><em><small>  ${FaresList.SeatTypeId}</small></em></td>
								                            <td class="col-md-1"> <small> ${FaresList.SeatType}</small> </td>
								                            <td class="col-md-1 "> <small> ${FaresList.Fare}</small></td>
								                            <td class="col-md-1 "> <small>${FaresList.ServiceTax}</small></td>
								                             <td class="col-md-1 "> <small>${FaresList.ConvenienceFee}</small></td>
								                            
								                        </tr>
								                      <%--  </c:forEach> --%>
									                    </tbody>
									                </table>
														
														 </div>
														 
														 
														 <script>
																			// When the user clicks on div, open the popup
																			function myFunction() {
																			    var popup = document.getElementById("myPopup");
																			    popup.classList.toggle("show");
																			}
																			
																			function myFunction1() {
																			    var popup = document.getElementById("myPopup1");
																			    popup.classList.toggle("show");
																			}
																			
																			function myFunction2(){
																			    var popup = document.getElementById("myPopup2");
																			    popup.classList.toggle("show");
																			}
																			</script>
														 
												</div>											
																								
											  										   
												</div>
												
												
												<div class="form-group col-md-2">																		
												
												<div class="box" style="background:blue; padding:1px 1px 1px 11px;"><label class="pull-left"><strong style="color: white;">${AvailableBusesList.AvailableSeatCount }</strong> </label></div>												
											      										   
												</div>
												
												
												<input type="hidden" name="originId"  value="${requestScope.originId}">															 																	                                               
												<input type="hidden" name="destinationId"  value="${requestScope.destinationId}" >	
												<input type="hidden" name="originName"  value="${requestScope.originName}">															 																	                                               
												<input type="hidden" name="destinationName"  value="${requestScope.destinationName}" >	
												<input type="hidden" name="date"  value="${requestScope.date}">
												</div>	
												<div class="form-group col-md-2">	
																						
	                                            <button type="submit"  class="btn btn-primary btn-inline" style="padding-top: 3%;padding-bottom: 3%" onclick="searchSeats" class="btn-default"><small>View  Seat</small></button>
												</div>
												
											
											
											
										</div>									
										</form>									
									</div>
									</c:forEach>
									</c:forEach>
									</c:forEach>
									 </c:forEach>
									
										
	
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