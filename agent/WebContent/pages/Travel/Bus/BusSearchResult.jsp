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
	.inner_box{
		padding:0;
	    border: 1px solid #ccc;
	    z-index: 999;
	    background-color: #fff;
	
	}
	.inner_box .inner_box_header{
		color:#fff;
		background:#ff3200;
	    height: 50px;
	    border-bottom: 1px solid #ccc;
	    padding: 15px 15px 1px 15px;
	
	}
	.inner_box .inner_box_content{
	
	    padding: 15px 15px 15px 15px;
    	min-height: 0.5em;
	
	}
.popup {
    position: relative;
    width: 650px;
}

/* The actual popup */
.popup .popuptext {
    display:none;
    color:#111;
    text-align:justify;
    border-radius: 6px;
    position: relative;
    z-index: 1;
   
}

</style>

	<script>
		// When the user clicks on div, open the popup
		function myFunction(idd) {
		    $('#'+idd).show('100');
		}
		function myClose(idd) {
		    $('#'+idd).hide('100');
		}
			
	</script>

	<script type="text/javascript">
	
	
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
	
	function submitForm(){

		$('#SeatMapLoading').show();
		document.selctSeat.submit();
	}
	function modifySearch(){
    	$('#resultLoading').show();
		document.modify.submit();
    }
	
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(e) {
		
		
		
		$("#originId").change(function(){
			
			var OriginId=document.getElementById("originId").value;
			

			if(OriginId != "")
	           {
	           	 			
				var OriginIdArr=OriginId.split('_');
				var val=OriginIdArr[0];
				$('#resultLoading').show();
				var xmlhttp;
				var url = "busSearch.action?param=getDistination&OriginId="+val;	  
				if (window.XMLHttpRequest)
			  	{// code for IE7+, Firefox, Chrome, Opera, Safari
			  		xmlhttp=new XMLHttpRequest();
			  	}
				else
			  	{// code for IE6, IE5
			  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
			  	}
				xmlhttp.onreadystatechange=function()
			  	{
			  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
			    	{
						document.getElementById("destinationId").innerHTML=xmlhttp.responseText;
			    	}
			  		$('#resultLoading').hide();
			  	}
				xmlhttp.open("post",url,true);
				xmlhttp.send();
	                                                      
	           }
	        });
		
		
		var now = new Date();
		var dd=now.getDate();
		var mm=(now.getMonth() + 1)
		
		if(dd<10)
			dd='0'+dd;
		
		if(mm<10)
			mm='0'+mm;
		
		var today = mm  + '/' +dd +'/'+now.getFullYear();
		
		
	 	//$('#datepicker').val(today);
		$("#datepicker").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'mm/dd/yy',
			yearRange:'2016:2020',
            numberOfMonths: 2,
			defaultDate: "+1w",
			maxDate: "today",
		})
		
	});
	
	
	
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
							<form name="modify" action="busSearch.action?param=modifySearchBus"  method="post" onsubmit="modifySearch()">
								<div class="col-md-12">
									<div class="form-group col-md-3">															                                               
									<input type="hidden" name="originName" class="form-control" value="${requestScope.originName}" placeholder="origin" required="required">															 
									<select class="form-control" id="originId" name="originId" required="required" >
							    		<option value="${requestScope.originId}" selected="selected">${requestScope.originName}</option>
							    		<c:forEach var="Source" items="${sessionScope.Source }">
							    			<option value="${Source.OriginId}_${Source.OriginName}">${Source.OriginName }</option>
							    		</c:forEach>
							    	</select>
									</div> 
									<div class="form-group col-md-3">															                                               
									<input type="hidden" name="destinationName" class="form-control" value="${requestScope.destinationName}" placeholder="Destination" required="required">															 
									<select class="form-control"  id="destinationId" name="destinationId" required="required">
							    		<option selected="selected" value="${requestScope.destinationId}">${requestScope.destinationName}</option>
							    	</select>
									</div> 		
																	
									<div class="form-group col-md-3">															                                          
									<input type="text" name="travelDate" id="datepicker" class="form-control" value="${requestScope.travelDate}" placeholder="Enter Email ID" required="required">															
									</div> 
															
									<div class="form-group col-md-1">															                                             
									<input type="text" name="noofperson" class="form-control" min="1" maxlength="1"  value ="${requestScope.noofperson}" required="required"/>													
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
			<section class="page-content" style="padding: 3% 5% 3% 5%;background: #f5f5f5;">
				<div class="container" style="width:100%;" >				
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <!-- <div class="col-md-3">
										<div class="box">									
											<div class="col-md-12">
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
									</div> -->
                                    <div class="col-md-12">	                      			
										<div class="box" >													
										<div class="col-md-12" style="padding: 10px 2px 0px 0px; background: red;color: white;"> <!-- style="box-shadow: 5px 5px 0px #888888;"  -->
												
											 	<div class="form-group col-md-3" style="margin-bottom: 0px">
													<label class="pull-left"><strong style="color: white;">Transport</strong> </label>    
												</div>
												 <div class="form-group col-md-2"style="margin-bottom: 0px;">
												 	<label class="pull-left"><strong style="color: white;">Departure</strong> </label>    
												</div>
												
												 <div class="form-group col-md-2" style="margin-bottom: 0px;">
												<label class="pull-left"><strong style="color: white;">Arrial</strong></label>    
												</div>
												
												 <div class="form-group col-md-2" style="margin-bottom: 0px;">
												<label class="pull-left"><strong style="color: white;">Fare </strong>  </label>    
												</div>
												<div class="form-group col-md-1" style="margin-bottom: 0px;">
													<label class="pull-left"><strong style="color: white;">Seats (Left)</strong></label>    
												</div>
												
												
										</div>
	
									<!-- ================================================================== -->   
									<c:forEach var="AvailableBusesList" items="${sessionScope.availBusDetails.BusDetails}" >	
									
									
									<div class="col-md-12" style="margin-top:1%; padding: 0px;border-bottom: 1px solid #ccc;" >			
										
									<form name="selctSeat" action="busSearch.action?param=searchSeatspage" method="POST" onsubmit="submitForm()">
										<div class="search-content" style="padding-top:1%;">
										 
										  	
											 	<div class="form-group col-md-3">
												<label class="pull-left">
												<strong>${AvailableBusesList.TransportName}</strong>											
												<br><small>${FaresList.SeatType}</small><!-- <span class="required" style="color:gold;"><br>**** 2rating</span> -->
											    </label>  
												</div>

												<input type="hidden" name="noofperson" value="${requestScope.noofperson}">
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
												
												<input type="hidden" name="UserTrackId" value="${sessionScope.availBusDetails.UserTrackId}">
												
												
												
												 <div class="form-group col-md-2">
												
												<strong>${AvailableBusesList.DepartureTime }</strong> <br>
													
															
												<div class="popup" >
														<a href="#" onclick="myFunction('${AvailableBusesList.TransportId}_Boarding')"><font size="2px">Boarding</font></a>
														 <div class="popuptext col-md-12" id="${AvailableBusesList.TransportId}_Boarding">
														 
														 
														 	 <div class="col-md-12 inner_box" >
														 	 	<div class="inner_box_header col-md-12">
															 	 		<h5 style="width: 95%;float: left;color: #fff;font-family: 'Noto Sans', sans-serif;">Boarding Details</h5><a style="width: 5%;float: left;color: #fff;" href="#" onclick="myClose('${AvailableBusesList.TransportId}_Boarding')">X</a>
															 	 	</div>
														 	 	<div class="col-md-12 inner_box_content">
														 	 		<select name="dropingPoints" class="col-md-11 form-group">
														 	 			<c:forEach var="BoardingPointsList" items="${AvailableBusesList.BoardingPoints}" >	
														 	 			<option>${BoardingPointsList.Time }, ${BoardingPointsList.Place }, ${BoardingPointsList.ContactNumber }</option>
														 	 			
														 	 			</c:forEach>
														 	 		</select>
														 	 		
														 	 		<div class="col-md-12">
														 	 		<strong>Place :</strong> ${AvailableBusesList.BoardingPoints[0].Place }  
														 	 		</div>
														 	 		<div class="col-md-12">
														 	 		<strong>Time :</strong> ${AvailableBusesList.BoardingPoints[0].Time } 
														 	 		</div>
														 	 		<div class="col-md-12">
														 	 		<strong>Land Mark :</strong> ${AvailableBusesList.BoardingPoints[0].LandMark } 
														 	 		</div>
														 	 		<div class="col-md-12">
														 	 		<strong>Mobile :</strong> ${AvailableBusesList.BoardingPoints[0].ContactNumber }  
														 	 		</div>
														 	 	</div>
														 	 
														 	 </div>
														 
														 
														 
														 <%-- <table class="table table-hover" style="margin-bottom: 0px;">
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
										              	
								                        		<tr style="background: white; border: 1px solid red;">
										                            <td class="col-md-1"><em><small>  ${BoardingPointsList.Place}</small></em></td>
										                            <td class="col-md-1"> <small> ${BoardingPointsList.Time}</small> </td>
										                            <td class="col-md-1 "> <small> ${BoardingPointsList.Address}</small></td>
										                            <td class="col-md-1 "> <small>${BoardingPointsList.LandMark}</small></td>
										                             <td class="col-md-1 "> <small>${BoardingPointsList.ContactNumber}</small></td>
								                             
								                             
								                            
								                        		</tr>
									                    		</tbody>
									                	</table> --%>
														 
														 
														</div>
												</div>
												
											   
												</div>
												
												 <div class="form-group col-md-2">
												
												<strong>${AvailableBusesList.ArrivalTime }</strong><br>
												
												
												<div class="popup" >
														<a href="#" onclick="myFunction('${AvailableBusesList.TransportId}_Dropping')"><font size="2px">Dropping </font></a>
														 <div class="popuptext col-md-12" id="${AvailableBusesList.TransportId}_Dropping">													  
														 
														 
															<div class="col-md-12 inner_box" >
														 	 		<div class="inner_box_header col-md-12">
															 	 		<h5 style="width: 95%;float: left;color: #fff;font-family: 'Noto Sans', sans-serif;">Dropping Details</h5><a style="width: 5%;float: left;color: #fff;" href="#" onclick="myClose('${AvailableBusesList.TransportId}_Dropping')">X</a>
															 	 	</div>
															 	 	<div class="col-md-12 inner_box_content">
															 	 		<select name="dropingPoints" class="col-md-11 form-group">
															 	 			<c:forEach var="DroppingPointsList" items="${AvailableBusesList.DroppingPoints}" >	
															 	 			<option>${DroppingPointsList.Place }, ${DroppingPointsList.LandMark }, ${DroppingPointsList.ContactNumber }</option>
															 	 			
															 	 			</c:forEach>
															 	 		</select>
															 	 		
															 	 		<div class="col-md-12">
															 	 		<strong>Place :</strong> ${AvailableBusesList.DroppingPoints[0].Place }  
															 	 		</div>
															 	 		<div class="col-md-12">
															 	 		<strong>Time :</strong> ${AvailableBusesList.DroppingPoints[0].Time } 
															 	 		</div>
															 	 		<div class="col-md-12">
															 	 		<strong>Land Mark :</strong> ${AvailableBusesList.DroppingPoints[0].LandMark } 
															 	 		</div>
															 	 		<div class="col-md-12">
															 	 		<strong>Mobile :</strong> ${AvailableBusesList.DroppingPoints[0].ContactNumber }  
															 	 		</div>
															 	 		<div class="col-md-12">
															 	 		<strong>Land Mark :</strong> ${AvailableBusesList.DroppingPoints[0].LandMark } 
															 	 		</div>
															 	 		<div class="col-md-12">
															 	 		<strong>Mobile :</strong> ${AvailableBusesList.DroppingPoints[0].ContactNumber }  
															 	 		</div>
															 	 	</div>
															 	 
															 </div>
														 
														 
														 </div>
												</div>
												
												
											   
												</div>
												<div class="form-group col-md-2">
												
												<c:forEach var="FaresList" items="${AvailableBusesList.Fares}" >
												
												
												<div class="popup" >
														
														<div class="popuptext" id="${AvailableBusesList.TransportId}_Seat">													
														 
														 
														 <table class="table table-hover" style="margin-bottom: 0px;">
	                 									   <thead style="background: #ddd;">
	                       										 <tr>
	                            									<th>SeatTypeId</th>
																    <th>SeatType</th>
																    <th>Fare</th>
																     <th>ServiceTax</th>
																    <th>ConvenienceFee</th>
																    <th><a style="color: #fff;" href="#" onclick="myClose('${AvailableBusesList.TransportId}_Seat')">X</a></th>
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
								                              <td class="col-md-1 "> <small></small></td>
								                            
								                        </tr>
								                      <%--  </c:forEach> --%>
									                    </tbody>
									                </table>
														
														 </div>
														 
														 
												</div>											
																								
											  	<strong>Rs. ${FaresList.Fare}</strong>&nbsp;&nbsp;<a href="#" onclick="myFunction('${AvailableBusesList.TransportId}_Seat')"><font size="2px">Fares </font></a><br>										
																					   
												
												</c:forEach>
												</div>
												<div class="form-group col-md-1">																		
												
												<div class="box" style="background:blue; padding: 2px 1px 0px 25px;"><label class="pull-left"><strong style="color: white;">${AvailableBusesList.AvailableSeatCount }</strong> </label></div>												
											      										   
												</div>
												
												
												<input type="hidden" name="originId"  value="${requestScope.originId}">															 																	                                               
												<input type="hidden" name="destinationId"  value="${requestScope.destinationId}" >	
												<input type="hidden" name="originName"  value="${requestScope.originName}">															 																	                                               
												<input type="hidden" name="destinationName"  value="${requestScope.destinationName}" >	
												<input type="hidden" name="date"  value="${requestScope.date}">
												<div class="form-group col-md-2" align="right">	
																						
	                                            <button type="submit"  class="btn btn-primary btn-inline" style="padding-top: 3%;padding-bottom: 3%" onclick="searchSeats" class="btn-default"><small>View  Seat</small></button>
												</div>
												
											
											
											
										</div>									
										</form>									
									</div>
									</c:forEach>
									
										
	
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
	
	<div id="SeatMapLoading" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;display:none;">
		<div
			style="width: 100%; text-align: center; position: absolute; left: 0px; top: 50%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
			<img
				src="images/ajax-loader.gif">
			<div>Please wait getting Seat Map ...</div>
		</div>
		<div class="bg"
			style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: rgb(0, 0, 0);"></div>
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