<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	
		var now = new Date();
	    var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		
		var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
		var today = now.getFullYear()  + '-' + mm + '-' + (dd-1);
		$('#from').val(today);
		$('#to').val(today);
	
		$(document).ready(function(){
			$("#from,#to").datepicker({
	            changeMonth: true,
				changeYear: true,
				dateFormat: 'yy-mm-dd',
	            numberOfMonths: 1,
				defaultDate: "+1w",
				maxDate: "0",
			});
			
			
		});
	
		
	</script>
	<script type="text/javascript">
	function viewbusiness(){
		$('#history').hide();
		var from =$('#from').val();
		var to =$('#to').val();
		if(from=='' ){
			
			$('#from').focus();
			return false;
			
		}else if(to ==''){
			
			$('#to').focus();
			return false;
		}else{
			
			$.ajax({
			 	url:"chart.action?param=todayBusinessOperatorWise&fromDate="+from+"&toDate="+to,
				type : "GET",
				dataType: "json",
				success: function (response) {
					if(response!=null && response.length>0){
						
						drawTable(response);
						$('#history').show(1000);
					}else{
						$('#err_msg').text('Data not available');
					}
				},
		
			 	failure: function (response) {
			 		$('.ajax_error').text('Unable to verify. try later.');
		
			    }
				
			});
			
		}
		
	}
	

	function drawTable(data) {
	    for (var i = 0; i < data.length; i++) {
	        drawRow(data[i],(i+1));
	    }
	}
	
	function drawRow(rowData,index) {
	    var row = $("<tr />")
	    $("#bsinessTable").append(row); //this will append tr element to table... keep its reference for a while since we will add cels into it
	    row.append($("<td>" + rowData.operator +"</td>"));
	    row.append($("<td>" + rowData.tot_amount + "</td>"));
	    row.append($("<td>" + rowData.comm + "</td>"));
	}
	</script>
	
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
						<div class="col-md-12 form-group"><h3 class="box-title">Business Reports</h3></div>
						<div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                
                        
                        		<div class="box" >
                                    
                                    
                                    <div class="col-md-12 " align="center">
                                    <form name="business" id="business" method="post" action="#"> 
                                            
                                          	<div class="form-group col-md-12" style="margin-bottom: 20px;" align="center">
											<label  style="color: RED;font-weight: bold;" id="err_msg"> <%=message %> </label>
											</div> 
											
												<table style="width: 80%">
			                                    	<tr>
			                                    	
			                                    		<td width="20%"></td>
			                                    		<td width="20%" align="right">
			                                    			<input required="required" name="fromdate" style="width: 75%" readonly="readonly" type="text" id="from" class="form-control" placeholder="Select From Date">
	                                              
			                                    		</td>
			                                    		<td width="20%" align="center">:</td>
			                                    		<td width="20%" align="left">
			                                    			<input required="required" name="todate" type="text" id="to" readonly="readonly" class="form-control" placeholder="Select To Date">
			                                    		
			                                    		</td>
			                                    		<td width="20%"></td>
			                                    	
			                                    	</tr>
			                                    
			                                    </table>
											
                                          
                                    			<div class="col-md-12" align="center" style="margin-top: 30px;">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                	
                                                	<button type="button" id="sub_button" onclick="viewbusiness()" class="btn btn-success waves-effect waves-light m-r-10">View Statement</button>
                                         		</div>
                                    </form>
                                    </div>
                                    
                                    
                                                                        
                                                                
                                </div>
                        
								</div>
	                        </div>
		               
	                        
	                    </div>
					</div>
					 <div class="spacer"></div>
					<div class="row" id="history" style="display: none;">
	                    <div class="col-md-12">
                          <div class="table-responsive">
                             <table class="table table-striped table-bordered" id="bsinessTable">
                                  <tr>
                                      <th style="background-color: #ff3200;color: #fff;">Operator</th>
                                      <th style="background-color: #ff3200;color: #fff;">Amount</th>
                                      <th style="background-color: #ff3200;color: #fff;">Commission</th>
                                  </tr>
                                  
                             </table>
                             
                          </div>
                      </div>
	               		
	               
	            	</div>
					
				</div>
                
		</section>
		
		
	<!-- Page Content / End -->
    
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