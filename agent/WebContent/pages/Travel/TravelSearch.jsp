<%@page import="com.smartkinda.travel.bus.BusServices"%>
<%@page import="com.smartkinda.travel.bus.BusAPI"%>
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
	
	function validateBusForm(){

		$('#travelbusloading').show();
		document.BusForm.submit();
	}
	
	</script>
	
	<script type="text/javascript">

	function getSourceList()
	{
		alert('Hi...........');
		/* $('#resultLoading').show();
		var xmlhttp;
		var url = "busSearch.action?param=getSource";	  
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
				document.getElementById("OriginId").innerHTML=xmlhttp.responseText;
	    	}
	  		$('#resultLoading').hide();
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send(); */
	}
	
	</script>
	
	<script type="text/javascript">
	$(document).ready(function(e) {
		
		
		
		$("#OriginId").change(function(){
			
			var OriginId=document.getElementById("OriginId").value;
			

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
						document.getElementById("DestinationId").innerHTML=xmlhttp.responseText;
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
		
		
	 	$('#datepicker').val(today);
		$("#datepicker").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'mm/dd/yy',
			yearRange:'2016:2020',
            numberOfMonths: 2,
			defaultDate: "+1w",
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
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>Recharges</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8">
                                        <div class="box">
                                             
                                            <div style="color: RED;"><c:out value="${requestScope.message}"></c:out></div>
                                                                        
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                               <!--  <li class="active"><a href="#flight" data-toggle="tab">FLIGHT</a></li> -->
                                                <li class="active"><a href="#bus" data-toggle="tab"> <i class="fa fa-bus" style="font-size:24px;color:#ff3200;"></i><br>BUS</a></li>
                                               <!--  <li><a href="#hotel" data-toggle="tab">HOTEL</a></li> -->
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade" id="flight">
                                                    <form action="busSearch.action?param=searchBus" method="post" name="BusForm">
                                                        <div class="form-group">
                                                            
                                                            
                                                        </div>
                                                        <div class="form-group">
                                                               <input type="hidden" name="ipaddress" value="<%=ipaddress%>"/>                                             
                                                            <input type="Text" required="required" name="mobileNo" onkeypress="return digitonly(this,event)" maxlength="10" class="form-control" placeholder="Enter Your 10 Digit Mobile Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                             
                                                            <input type="Text" required="required" name="amountMobile" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Amount">
                                                        </div>
                                                        
                                                        <button type="submit" id="submitmobile" onclick="validateBusForm()" class="btn btn-primary btn-inline">Search Bus</button>
                                                    	<img id="submitmobileloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                                <div class="tab-pane fade  in active" id="bus">
                                                   	<form action="busSearch.action?param=searchBus" method="post" name="BusForm">
                                                        	<div class="form-group col-md-6">
											           
														    	<select class="form-control" id="OriginId" name="OriginId" required="required" >
														    		<option value="-1">Select Origin</option>
														    		<c:forEach var="Source" items="${sessionScope.Source }">
														    			<option value="${Source.OriginId}_${Source.OriginName}">${Source.OriginName }</option>
														    		</c:forEach>
														    	</select>
																			 
															</div> 
																				
															<div class="form-group col-md-6">
															 
															 	<select class="form-control"  id="DestinationId" name="DestinationId" required="required">
														    		<option value="-1">Select Destination</option>
														    	</select>            
																			 
															</div> 		
																				
															<div class="form-group col-md-6">
															                            
															<input type="text" id="datepicker" name="travelDate" value="" class="form-control" placeholder="Select Date">
																			
															</div> 
																		
															<div class="form-group col-md-5">
															<br>
															<input id="decreaseNumber1"  type="button" value="-">
															<input id="textBox1" name="noofperson"  type="text" value="1">
															<input id="increaseNumber1"  type="button" value="+" >
															<script>
															var a = document.querySelectorAll('input[type=button]');
														
																for (var i = 1; i<a.length; i++) {
																 a[i].addEventListener('click', addorsub);   
																}
														
																function addorsub () {
																	var b = document.getElementById('textBox1');
																	if (this.id == 'decreaseNumber1') {
																	b.value = parseInt(b.value) - 1;
																	}
																	else if (this.id == 'increaseNumber1') {
																	b.value = parseInt(b.value) + 1;                                      
																	}
																}
															</script>
															</div>
                                                        	<div class="form-group col-md-5" align="center">
                                                        		<input type="submit" id="submitmobile" value="Search Bus" onclick="validateBusForm()" class="btn btn-primary btn-inline" />
                                                    			<img id="submitdthloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                        	</div>
                                                        
                                                        
                                                    </form>
                                                </div>
                                                <div class="tab-pane fade" id="hotel">
                                                    <form name="DataCardForm" method="post" action="domobileDthRecharge.action?param=mobileDthRech&Service=datacard">
                                                        <div class="form-group">
                                                            
                                                            <select class="form-control" name="OperatorDataCrd" required>
                                                                <option value="">------------- Select Datacard Operator -------------</option>
                                                                
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.Service,'datacard') }">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                <!-- <option value="TATA">Docomo Photon+</option>
										                         <option value="TATA">Tata Photon Whiz</option>
										                          <option value="RELIANCE">Reliance Netconnect+</option>
										                         <option value="RELIANCE">Reliance Netconnect</option>
										                         <option value="MTS">MTS MBlaze</option>
										                         <option value="MTS">MTS MBrowse</option>
										                         <option value="AIRCEL">Aircel</option>
										                         <option value="BSNL">BSNL</option>
										                         <option value="IDEA">Idea</option> -->
                                                            </select>
                                                        </div> 
														<div class="form-group">
                                                              <input type="hidden" name="ipaddress" value="<%=ipaddress%>"/>                                               
                                                            <input type="Text" required="required" name="datacardno" onkeypress="return digitonly(this,event)" maxlength="10" class="form-control" placeholder="Enter Your Data Card Number">
                                                        </div>														
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="amountDataCrd" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Amount">
                                                        </div>
                                                        <button type="submit" id="submitdatacard" onclick="validateDataCard()" class="btn btn-primary btn-inline">Recharge</button>
                                                        <img id="submitdatacardloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div class="col-md-4">
                                    	
                                        <div class="spacer-xl"></div>
                                        
                                    	<div class="cta-inner">
                                            <div class="cta-txt">
                                                <h2>${sessionScope.companyName }</h2>
                                                <p>Highest and Best Commission from Market <br> Guaranted on ${sessionScope.companyName } <br> <span style="font-size:14px; font-style:italic; color:#000;"> Book Your Bus Now ... </span></p>
                                                <c:if test="${sessionScope.clientId!=null && !sessionScope.clientId eq '10004' }">
                                                <h3>Android Application</h3>
                                                <p><a  title="Download Android App" href="https://play.google.com/store/apps/details?id=com.ssz" target="_blank"><img height="60" width="150" src="images/androidapp.png" alt="Logo"></a></p>
                                           		</c:if>
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
		<div id="travelbusloading" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;display:none;">
			<div style="width: 100%; text-align: center; position: absolute; left: 0px; top: 25%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
				
				
					<img src="images/loader.gif" width="500">

			</div>
			<div class="bg"
				style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: rgb(0, 0, 0);"></div>
		</div>
		
	<!-- Page Content / End -->
    
			<!-- Footer -->
			<%@ include file="/footer.jsp" %>
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