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
	
	<script src="chartjs/Chart.min.js" type="text/javascript"/></script>
	<script src="chartjs/utils.js" type="text/javascript"/></script>
	
	<script type="text/javascript">
		var barChartData;
		$.ajax({
		 	url:"chart.action?param=dayAndServiceWise",
			type : "GET",
			dataType: "json",
			success: function (data) {
				if(data!=null){
					var color = Chart.helpers.color;
					//barChartData=data;
					
					
					var a=data.datasets
					for (i = 0; i < a.length; i++) {
				  		var dataset;
				  		dataset={
			  				data:a[i].data,
					  		label:a[i].label,
					  		backgroundColor: a[i].backgroundColor,
					  		borderColor:a[i].borderColor,
					  		borderWidth:1
				  		}
				  		
				  		var b=[];
				  		b.push(dataset);
				  		barChartData={
								labels:data.labels,
								datasets:b
						}
				  		var ctx = document.getElementById(a[i].label).getContext('2d');
						window.myBar = new Chart(ctx, {
							type: 'bar',
							data: barChartData,
							options: {
								responsive: true,
								legend: {
									position: 'top',
								},
								title: {
									display: true,
									text: 'Service and day wise business'
								}
							}
						});
					}
					
					
					
				}
				
			},
	
		 	failure: function (response) {
		 		$('.ajax_error').text('Unable to verify. try later.');
	
		    }
			
		});
		
	</script>
	
	<script type="text/javascript">
		var barChartData;
		$.ajax({
		 	url:"chart.action?param=todayServiceWise",
			type : "GET",
			dataType: "json",
			success: function (response) {
				if(response!=null){
					var color = Chart.helpers.color;
					
					var background=[];
					background.push(window.chartColors.red);
					background.push(window.chartColors.orange);
					background.push(window.chartColors.yellow);
					background.push(window.chartColors.green);
					background.push(window.chartColors.blue);
					background.push(window.chartColors.purple);
					background.push(window.chartColors.grey);
					
					
					var labels=[],data=[],backgroundColor=[];
					
					for (i = 0; i < response.length; i++) {
				  		var a=response[i];
				  		labels.push(a.service);
				  		data.push(a.amount);
				  		backgroundColor.push(background[i])
					}
					
					var config = {
						type: 'pie',
						data: {
							datasets: [{
								data: data,
								backgroundColor: backgroundColor,
								label: 'Dataset 1'
							}],
							labels: labels
						},
						options: {
							responsive: true
						}
					};
					var ctx = document.getElementById('chart-area').getContext('2d');
					window.myPie = new Chart(ctx, config);
					
					var a=data.datasets
					for (i = 0; i < a.length; i++) {
				  		var dataset;
				  		dataset={
			  				data:a[i].data,
					  		label:a[i].label,
					  		backgroundColor: a[i].backgroundColor,
					  		borderColor:a[i].borderColor,
					  		borderWidth:1
				  		}
				  		
				  		var b=[];
				  		b.push(dataset);
				  		barChartData={
								labels:data.labels,
								datasets:b
						}
				  		var ctx = document.getElementById(a[i].label).getContext('2d');
						window.myBar = new Chart(ctx, {
							type: 'bar',
							data: barChartData,
							options: {
								responsive: true,
								legend: {
									position: 'top',
								},
								title: {
									display: true,
									text: 'Service and day wise business'
								}
							}
						});
					}
					
					
					
				}
				
			},
	
		 	failure: function (response) {
		 		$('.ajax_error').text('Unable to verify. try later.');
	
		    }
			
		});
		
	</script>
	
	
	
</head>
<body >

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">


			<!-- Slider -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
				
					<div class="row" align="center">
	                    <div class="col-md-12 form-group" align="left"><h4 class="box-title">Last 10 Days Business</h4></div>       
	                    <div class="col-md-8">
	                    	<div class="tabs">
                                 <!-- Nav tabs -->
                                 <ul class="nav nav-tabs">
                                 	 <li class="active"><a href="#Mobile_tab" data-toggle="tab">Mobile</a></li>
                                     <li><a href="#DTH_tab" data-toggle="tab">DTH</a></li>
                                     <li><a href="#Billpayment_tab" data-toggle="tab">Billpayment</a></li>
                                     <li ><a href="#Withdrawal_tab" data-toggle="tab">Withdrawal</a></li>
                                     <li><a href="#Verification_tab" data-toggle="tab">Verification</a></li>
                                     <li><a href="#Remittance_tab" data-toggle="tab">Remittance</a></li>
                                 </ul>
                                 <!-- Tab panes -->
                                 <div class="tab-content">
                                 	
                                 	<div class="tab-pane fade" id="Withdrawal_tab">
                                 		<canvas id="Withdrawal" style="height: 400px;"></canvas>
                                 	</div>
                                 	
                                 	<div class="tab-pane fade" id="Verification_tab">
                                 		<canvas id="Verification" style="height: 400px;"></canvas>
                                 	</div>
                                 	
                                 	<div class="tab-pane fade" id="Remittance_tab">
                                 		<canvas id="Remittance" style="height: 400px;"></canvas>
                                 	</div>
                                 	
                                 	<div class="tab-pane fade  in active" id="Mobile_tab">
                                 		<canvas id="Mobile" style="height: 400px;"></canvas>
                                 	</div>
                                 	
                                 	<div class="tab-pane fade" id="DTH_tab">
                                 		<canvas id="DTH" style="height: 400px;"></canvas>
                                 	</div>
                                 	
                                 	<div class="tab-pane fade" id="Billpayment_tab">
                                 		<canvas id="Billpayment" style="height: 400px;"></canvas>
                                 	</div>
                                 
                                 </div>
                            	
                            	<!-- Tab panes End -->
                            
                            </div>
	                    
	                            
                        </div>
                       
                        <div class="col-md-4" align="center" style="padding: 5% 1%;">
                                        
                               <h4>Today Business </h4>
                               <canvas id="chart-area" style="height: 400px;"></canvas>
                        </div>
                    </div>                    
				
				</div>
			
			</section>
			
			
			<!-- Slider / End -->

			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
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
	<script src="vendor/flexslider/jquery.flexslider-min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>

	<script>
		jQuery(function($){
			$('body').addClass('loading');
		});
		
		$(window).load(function(){
			$('.flexslider').flexslider({
				animation: "fade",
				controlNav: true,
				directionNav: false,
				prevText: "",
				nextText: "",
				start: function(slider){
					$('body').removeClass('loading');
				}
			});
		});
	</script>
	<script type="text/javascript">
    $(window).load(function(){
        $('#welcomemodal').modal('show');
    });
</script>
	
</body>
</html>