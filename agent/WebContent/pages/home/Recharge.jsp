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

	<style type="text/css">
		.form-group label{
			font-weight: bold;
		}
		.err_msg{
			margin-top: 10px;
		    background: #0080008a;
		    color: #fff;
		    padding: 5px;
		    border-radius: 5px;
		    display: none;
		    text-align: center;
	    	font-size: 14px;
		}
	</style>
	
	<script type="text/javascript">
	function viewPlan(type){
		var circle,status;
		$('.err_msg').hide();
		if(type=='mobile'){
			circle=$('#circle').find('option:selected').val();
			var opName=$('#OperatorMobile').val();
			if(opName==''){
				$('.err_msg').text("Select operator");
				$('.err_msg').show();
				status= false;
			}else if(circle==''){
				$('.err_msg').text("Select Circle");
				$('.err_msg').show();
				$('#circle_div').show();
				status= false;
			}else{
				status=true;
			}
			
		}else{
			var opName=$('#OperatorDTH').find('option:selected').text();
			if(opName==''){
				$('.err_msg').text("Select operator");
				$('.err_msg').show();
				status= false;
			}else{
				$('#circle_div').hide();
				status= true;
			}
			
		}
		if(status==true){
			$("#plan_view").text('');
			$("#plan_view_content").text('');
			$('#resultLoading').show();
			$.ajax({
				url:"domobileDthRecharge.action?param=viewPlan&opName="+opName+"&circle="+circle+"&type="+type,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var status=response.status;
					if(status==1){
						var records=response.records;
						$.each(records, function(k, v) {
						    //display the key and value pair
						    //alert(k + ' is ' + v);
						    var desc='',validity='',amount='',row='',tab_key='';
						    
						    tab_key=k.replace(/ /g,"_").replace(/\//g,"_");
						    
						    $("#plan_view").append("<li ><a href='#"+tab_key+"' data-toggle='tab'>"+k+"</a></li>");
						    
						    $.each(v, function(kk, vv) {
						    		desc=vv.desc;
						    		amount='';
						    		if(type=='mobile'){
						    			validity=vv.validity;
						    			amount=vv.rs;
						    		}else if(type=='dth'){
						    			$.each(vv.rs, function(kkk, vvv){
						    				amount=amount+kkk+'( Rs. '+vvv+' )<br/>';
						    			});
						    			
						    			
						    		}
						    			
						    	if(type=='mobile'){
							    	row=row+"<div class='row' style='border-bottom: 1px solid #ccc;margin-top: 10px;'>"+
	                        		"<div class='form-group col-md-2 recharge_plan_box' align='center'>"+
	                        		"<label>"+amount+"</label>"+
	                        		"</div>"+
	                        		"<div class='form-group col-md-10' align='left'>"+
	                            		"<label>"+desc+"<br>Validity "+validity+"</label>"+                                                
	                        		"</div></div>";
						    	}else if(type=='dth'){
						    		row=row+"<div class='row' style='border-bottom: 1px solid #ccc;margin-top: 10px;'>"+
	                        		"<div class='form-group col-md-4 dth_plan_box' align='center'>"+
	                        		"<label>"+amount+"</label>"+
	                        		"</div>"+
	                        		"<div class='form-group col-md-8' align='left'>"+
	                            		"<label>"+desc+"</label>"+                                                
	                        		"</div></div>";
						    	}
						    });
						    $('#plan_view_content').append("<div class='tab-pane fade in' id='"+tab_key+"'>"+row+"</div>");
						});
						$("#plan_view li:first").addClass('active');
						$("#plan_view_content div:first").addClass('active');
						
						$('#plan').show();
						
					}else{
						$('.err_msg').text("Error!");
					}
					
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		$('.err_msg').text("Error!");
					$('.err_msg').show();
			    }
				
				
			});
		}
		
	}
	function viewOffer(type){
		var circle,status;
		$('.err_msg').hide();
		if(type=='mobile'){
			circle=$('#circle').find('option:selected').val();
			var opName=$('#OperatorMobile').val();
			var mobile=$('#mobileNo').val();
			if(opName==''){
				$('.err_msg').text("Select operator");
				$('.err_msg').show();
				status= false;
			}else if(circle==''){
				$('.err_msg').text("Select Circle");
				$('.err_msg').show();
				$('#circle_div').show();
				status= false;
			}else if(mobile==''){
				$('.err_msg').text("Enter mobile number");
				$('.err_msg').show();
				$('#circle_div').show();
				status= false;
			}else{
				status=true;
			}
			
		}else{
			var opName=$('#OperatorDTH').find('option:selected').text();
			var mobile=$('#DTHNo').val();
			if(opName==''){
				$('.err_msg').text("Select operator");
				$('.err_msg').show();
				status= false;
			}else if(mobile==''){
				$('.err_msg').text("Enter connection number");
				$('.err_msg').show();
				$('#circle_div').show();
				status= false;
			}else{
				$('#circle_div').hide();
				status= true;
			}
			
		}
		if(status==true){
			$("#plan_view").text('');
			$("#plan_view_content").text('');
			$('#resultLoading').show();
			$.ajax({
				url:"domobileDthRecharge.action?param=viewoffer&opName="+opName+"&circle="+circle+"&type="+type+"&mobile="+mobile,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var status=response.status;
					if(status==1){
						var records=response.records;
						    var desc='',validity='',amount='',row='',tab_key='';
						    
						    if($.isArray(records)){
						    
							    $("#plan_view").append("<li ><a href='#offer' data-toggle='tab'>OFFER</a></li>");
							    
							    $.each(records, function(kk, vv) {
							    		desc=vv.desc;
							    		amount='';
							    		if(type=='mobile'){
							    			amount=vv.rs;
							    		}else if(type=='dth'){
							    			$.each(vv.rs, function(kkk, vvv){
							    				amount=amount+kkk+'( Rs. '+vvv+' )';
							    			});
							    			
							    			
							    		}
							    			
							    	if(type=='mobile'){
								    	row=row+"<div class='row' style='border-bottom: 1px solid #ccc;margin-top: 10px;'>"+
		                        		"<div class='form-group col-md-2 recharge_plan_box' align='center'>"+
		                        		"<label>"+amount+"</label>"+
		                        		"</div>"+
		                        		"<div class='form-group col-md-10' align='left'>"+
		                            		"<label>"+desc+"</label>"+                                                
		                        		"</div></div>";
							    	}else if(type=='dth'){
							    		row=row+"<div class='row' style='border-bottom: 1px solid #ccc;margin-top: 10px;'>"+
		                        		"<div class='form-group col-md-4 dth_plan_box' align='center'>"+
		                        		"<label>"+amount+"</label>"+
		                        		"</div>"+
		                        		"<div class='form-group col-md-8' align='left'>"+
		                            		"<label>"+desc+"</label>"+                                                
		                        		"</div></div>";
							    	}
							    });
							    $('#plan_view_content').append("<div class='tab-pane fade in' id='"+tab_key+"'>"+row+"</div>");
							
							    $("#plan_view li:first").addClass('active');
								$("#plan_view_content div:first").addClass('active');
							
								$('#plan').show();
							
							}else{
								$('.err_msg').text(records.desc);
								$('.err_msg').show();
							}
						
					}else{
						$('.err_msg').text("Error!");
					}
					
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		$('.err_msg').text("Error!");
					$('.err_msg').show();
			    }
				
				
			});
		}
	}
	
	function getCustomerDetails(){
		$('.err_msg').text('');
		var opName=$('#OperatorDTH').find('option:selected').text();
		var mobileNo=document.DTHForm.DTHNo.value;
		$('#resultLoading').show();
		$.ajax({
			url:"domobileDthRecharge.action?param=dthInfo&opName=="+opName+"&mobileNo="+mobileNo,
			type : "GET",
			dataType: "json",
			success: function (response) {
				
				$('#resultLoading').hide();
				var status=response.status;
				if(status==1){
					var records=response.records[0];
					var customerName=records.customerName;
					var status=records.status;
					var Balance=records.Balance;
					var planname=records.planname;
					var lastrechargedate=records.lastrechargedate;
					$('.err_msg').text('Name : '+customerName+', Balance : '+ Balance+', Status :  '+status+', Plan Name : '+planname+', Last recharge : '+lastrechargedate);
					$('.err_msg').show();
				}else{
					$('.err_msg').text("Error!");
				}
				
			},

		 	failure: function (response) {
		 		$('#resultLoading').hide();
		 		
		 		$('.err_msg').text("Error!");
				$('.err_msg').show();
		    }
			
			
		});
	}
	</script>

	<script type="text/javascript">
	
	function validateMobileForm(){

		var opCode=$('#OperatorMobile').find('option:selected').attr('opCode');
		var mobileOp=document.MobileForm.OperatorMobile.value;
		var amount=document.MobileForm.amountMobile.value;
		var mobileNo=document.MobileForm.mobileNo.value;
		var mobileStart=document.MobileForm.mobileNo.value.substring(0,1);	
		if(mobileOp=="Airtel"){

			 if(isNaN(amount))
			{
				$('.err_msg').text("Recharge amount be should numeric");
				$('.err_msg').show();
				return false;
			}
			else if(amount<10){
				$('.err_msg').text("Recharge amount should be Rs 10 or More. ");
				$('.err_msg').show();
				return false;
			}
		}

		if(isNaN(amount))
		{
			$('.err_msg').text("Recharge amount be should numeric");
			$('.err_msg').show();
			return false;
		}else if(!(mobileStart == "9" ||  mobileStart == "8"  || mobileStart == "7" || mobileStart == "6"))
		{
			$('.err_msg').text("Mobile Number should start with 9,8 7 or 6");
			$('.err_msg').show();
			return false;
		} 
		else{
			
			$('#resultLoading').show();
			
			$.ajax({
			 	url:"domobileDthRecharge.action?param=mobileDthRech&Service=mobile&OP="+opCode+"&CN="+mobileNo+"&AMT="+amount,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					$('#amountMobile').val('');
					$('#mobileNo').val('');
					
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					$('#amountMobile').val('');
					$('#mobileNo').val('');
			    }
				
				
			});
			
		}
	}


	function validateDataCard(){
		var opCode=$('#OperatorDataCrd').find('option:selected').attr('opCode');
		var mobileOp=document.DataCardForm.OperatorDataCrd.value;
		var amount=document.DataCardForm.amountDataCrd.value;
		var mobileNo=document.DataCardForm.datacardno.value;
		if(isNaN(amount))
		{
			$('.err_msg').text("Recharge amount be should numeric");
			$('.err_msg').show();
			
			return false;
		}
		else{
			
			$('#resultLoading').show();

			$.ajax({
				url:"domobileDthRecharge.action?param=mobileDthRech&Service=datacard&OP="+opCode+"&CN="+mobileNo+"&AMT="+amount,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					$('#amountDataCrd').val('');
					$('#datacardno').val('');
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					$('#amountDataCrd').val('');
					$('#datacardno').val('');
			    }
				
				
			});
			
		}
	}

	function validateDTH(){

		var opCode=$('#OperatorDTH').find('option:selected').attr('opCode');
		var mobileOp=document.DTHForm.OperatorDTH.value;
		var amount=document.DTHForm.amountDTH.value;
		var mobileNo=document.DTHForm.DTHNo.value;
		if(parseFloat(amount)!= parseInt(parseFloat(amount)))
		{
			$('.err_msg').text('Recharge amount decimal value not allowed');
			$('.err_msg').show();
			return false;
		}
		else if(isNaN(amount))
		{
			$('.err_msg').text("Recharge amount be should numeric");
			$('.err_msg').show();
			return false;
		}
		else if(amount<10)
		{
			$('.err_msg').text("Recharge amount should be Rs 10 or More");
			$('.err_msg').show();
			return false;
		}
		else{

			$('#resultLoading').show();

			$.ajax({
				url:"domobileDthRecharge.action?param=mobileDthRech&Service=dth&OP="+opCode+"&CN="+mobileNo+"&AMT="+amount,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					$('#amountDTH').val('');
					$('#DTHNo').val('');
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					$('#amountDTH').val('');
					$('#DTHNo').val('');
			    }
				
				
			});
			
		}

	}
	function getmobileapps(){
		//alert('Comming Soon.');
	  window.open('mobileappsAction.do?param=ReqMobileapps','popup','width=520,height=520,resizable=yes,scrollbars=no');

	}
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
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
	                            
	                            	<div class="col-md-12 form-group"><h3 class="box-title">Recharges</h3></div>
	                            	<div class="col-md-5">
                                                                        
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#mobile" data-toggle="tab">Mobile</a></li>
                                                <li><a href="#dth" data-toggle="tab">DTH</a></li>
                                                <li><a href="#datacard" data-toggle="tab">DATA CARD</a></li>
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="mobile">
                                                    <form action="#" method="post" name="MobileForm" >
                                                        <div class="form-group">
                                                            
                                                            <select name="OperatorMobile" id="OperatorMobile" class="form-control" required>
                                                                <option value="">------ Select Mobile Operator ------</option>
                                                                
                                                                <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${fn:containsIgnoreCase(opertor.Service , 'mobile') }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
                                                                
                                                                
                                                            </select>
                                                            <a style="float: right;" href="#" onclick="viewPlan('mobile')">View Plan</a>
                                                        </div>
                                                        <div class="form-group" id="circle_div" style="display: none;">
                                                        	<select name="circle" id="circle" class="form-control" required >
                                                                <option value="">---------------- Select circle ----------------</option>
                                                                
						                                        <option value="Andhra Pradesh Telangana">Andhra Pradesh Telangana</option>
                                                                <option value="Assam">Assam</option>
                                                                <option value="Bihar Jharkhand">Bihar Jharkhand</option>
                                                                <option value="Chennai">Chennai</option>
                                                                <option value="Delhi NCR">Delhi NCR</option>
                                                                <option value="Gujarat">Gujarat</option>
                                                                <option value="Haryana">Haryana</option>
                                                                <option value="Himachal Pradesh">Himachal Pradesh</option>
                                                                <option value="Jammu Kashmir">Jammu Kashmir</option>
                                                                <option value="Karnataka">Karnataka</option>
                                                                <option value="Kerala">Kerala</option>
                                                                <option value="Kolkata">Kolkata</option>
                                                                <option value="Madhya Pradesh Chhattisgarh">Madhya Pradesh Chhattisgarh</option>
                                                                <option value="Maharashtra Goa">Maharashtra Goa</option>
                                                                <option value="Mumbai">Mumbai</option>
                                                                <option value="North East">North East</option>
                                                                <option value="Orissa">Orissa</option>
                                                                <option value="Punjab">Punjab</option>
                                                                <option value="Rajasthan">Rajasthan</option>
                                                                <option value="Tamil Nadu">Tamil Nadu</option>
                                                                <option value="UP East">UP East</option>
                                                                <option value="UP West">UP West</option>
                                                                <option value="West Bengal">West Bengal</option>
                                                                
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="Text" required="required" name="mobileNo" id="mobileNo" onkeypress="return digitonly(this,event)" maxlength="10" class="form-control" placeholder="Enter Your 10 Digit Mobile Number">
                                                        	<a style="float: right;" href="#" onclick="viewOffer('mobile')">View Offer</a>
                                                        </div>
                                                        <div class="form-group">
                                                                                                             
                                                            <input type="Text" required="required" name="amountMobile" id="amountMobile" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Amount">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="button" id="submitmobile" onclick="validateMobileForm();" class="btn btn-success waves-effect waves-light m-r-10">Recharge Now</button>
                                                    </form>
                                                </div>
                                                <div class="tab-pane fade" id="dth">
                                                    <form name="DTHForm" method="post" action="#" >
                                                        <div class="form-group">
                                                            
                                                            <select name="OperatorDTH" id="OperatorDTH" class="form-control" required>
                                                                <option value="">------- Select DTH Operator -------</option>
                                                                
                                                                <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${fn:containsIgnoreCase(opertor.Service , 'dth') }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
                                                            </select>
                                                            <a style="float: right;" href="#" onclick="viewPlan('dth')">View Plan</a>
                                                        </div> 
														<div class="form-group">
                                                            <input type="Text" required="required" name="DTHNo" id="DTHNo" onkeypress="return digitonly(this,event)" maxlength="12" class="form-control" placeholder="Enter Your DTH Account Number">
                                                        	<a style="float: right;" href="#" onclick="viewOffer('dth')">View Offer</a> &nbsp;&nbsp;&nbsp;&nbsp;
                                                        	<a style="float: right;margin-right: 20px;" href="#" onclick="getCustomerDetails()">View Customer Details</a>
                                                        </div>														
                                                        <div class="form-group">
                                                                                                           
                                                            <input type="Text" required="required" name="amountDTH" id="amountDTH" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Amount">
                                                        </div>
                                                        
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="button" id="submitdth" onclick="validateDTH()" class="btn btn-success waves-effect waves-light m-r-10">Recharge Now</button>
                                                    </form>
                                                </div>
                                                <div class="tab-pane fade" id="datacard">
                                                    <form name="DataCardForm" method="post" action="#" >
                                                        <div class="form-group">
                                                            
                                                            <select class="form-control" name="OperatorDataCrd" id="OperatorDataCrd" required>
                                                                <option value="">------ Select Datacard Operator ------</option>
                                                                
                                                                <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${fn:containsIgnoreCase(opertor.Service , 'datacard') }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
                                                            </select>
                                                        </div> 
														<div class="form-group">
                                                            <input type="Text" required="required" name="datacardno" id="datacardno" onkeypress="return digitonly(this,event)" maxlength="10" class="form-control" placeholder="Enter Your Data Card Number">
                                                        </div>														
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="amountDataCrd" id="amountDataCrd" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Amount">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="button" id="submitdatacard" onclick="validateDataCard()" class="btn btn-success waves-effect waves-light m-r-10">Recharge Now</button>
                                                    </form>
                                                </div>
                                                <div class="err_msg">
						         			
						         				</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-7" align="right" id="plan" style="display: none;">
                                    	<div class="tabs">
                                    		<!-- Nav tabs -->
                                            <ul class="nav nav-tabs" id="plan_view">
                                                <!-- <li class="active"><a href="#mobile" data-toggle="tab">Mobile</a></li> -->
                                                
                                            </ul>
                                            <!-- Tab panes -->
											<!-- Tab panes -->
                                            <div class="tab-content" id="plan_view_content" style="height: 400px;overflow-y: scroll;">
                                                
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
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>


	
</body>

</html>