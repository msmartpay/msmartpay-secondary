<%@page import="com.common.PropertyFile"%>
<%@page import="com.utility.HmacSHA256"%>
<%@page import="com.msmart.eko.MoneyTransferDao"%>
<%@page import="java.util.Date"%>
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
	$(document).ready(function(e) {
		
		
		var now = new Date();
		var dd=now.getDate();
		var mm=(now.getMonth() + 1)
		
		if(dd<10)
			dd='0'+dd;
		
		if(mm<10)
			mm='0'+mm;
		
		var today = now.getFullYear()+'-'+mm  + '-' +dd ;
		
		
	 	$('#dob').val(today);
		$("#dob").datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2010',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		
	});
	function populatecities()
	{
		var xmlhttp;
		var state="";
		state=document.getElementById("state").value; 
		var url = "distDetails.action?state="+state;	  
		
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
				document.getElementById("district").innerHTML=xmlhttp.responseText;
		    }
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send();
	}
	function populatecities2()
	{
		var xmlhttp;
		var state="";
		state=document.getElementById("shop_state").value; 
		var url = "distDetails.action?state="+state;	  
		
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
				document.getElementById("shop_district").innerHTML=xmlhttp.responseText;
		    }
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send();
	}
	
	</script>
	<script type="text/javascript">
	
	function ActivateAeps(){
		$('#resultLoading').show();
		document.activateAeps.submit();
		return true;
	}
	
	function sendOTP(){
		
		var mobile=$('#mobile').val();
		if(mobile=='')
		{
			$('#respMessage').text('Please endter registered mobile.');
			$('#respMessage').show();
			return;
		}
		$('#resultLoading').show();
		
		$.ajax({
		 	url:"reuestOTP.action?param=reuestOTP&mobile="+mobile,
			type : "GET",
			contentType: "application/json",
	        dataType: "json",
			success: function (data) {
				$('#resultLoading').hide();
				if(data.status==0 && data.response_type_id==1282){
					$('#respMessage').text(data.message);
					$('#respMessage').show();
					$('#otp').val(data.otp);
					$('#otp_div').show();
					$('#heading').text('Verify OTP');
					$('#verify_btn').show();
					$('#request_btn').hide();
					$('#onboard_btn').hide();
				}else if(data.status==0 && data.response_type_id==1317){
					$('#respMessage').text(data.message);
					$('#respMessage').show();
					$('#heading').text('Submit Onboard Form');
					$('#otp_div').hide();
					$('#onboard_fields').show();
					$('#verify_btn').hide();
					$('#request_btn').hide();
					$('#onboard_btn').show();
				}else{
					$('#respMessage').text(data.message);
					$('#respMessage').show();
					$('#verify_btn').hide();
					$('#request_btn').show();
					$('#onboard_btn').hide();
				}
				
			},

		 	failure: function (data) {
		 		$('#resultLoading').hide();
		 		$('#respMessage').text('Technical failure. Please try latter.');
				$('#respMessage').show();
				$('#verify_btn').hide();
				$('#request_btn').show();
				$('#onboard_btn').hide();
		    }
			
			
		});
		return false;
	}
	</script>
	<script type="text/javascript">
	function vrifyOTP(){
		var mobile=$('#mobile').val();
		var otp=$('#otp').val();
		if(mobile==''){
			$('#respMessage').text('Enter registered Moile no');
			$('#respMessage').show();
		}else if(otp==''){
			$('#respMessage').text('Enter valid OTP');
			$('#respMessage').show();
		}else{
			$('#resultLoading').show();
			
			$.ajax({
			 	url:"reuestOTP.action?param=VerifyOTP&mobile="+mobile+"&otp="+otp,
				type : "GET",
				contentType: "application/json",
		        dataType: "json",
				success: function (data) {
					$('#resultLoading').hide();
					if(data.status==0 && data.response_type_id==876){
						$('#respMessage').text(data.message);
						$('#respMessage').show();
						$('#heading').text('Submit Onboard Form');
						$('#otp_div').hide();
						$('#onboard_fields').show();
						$('#verify_btn').hide();
						$('#request_btn').hide();
						$('#onboard_btn').show();
					}else{
						$('#respMessage').text(data.message);
						$('#respMessage').show();
						$('#verify_btn').show();
						$('#request_btn').hide();
						$('#onboard_btn').hide();
					}
					
				},
	
			 	failure: function (data) {
			 		$('#respMessage').text(data.message);
			 		$('#respMessage').show();
			 		$('#verify_btn').show();
					$('#request_btn').hide();
					$('#onboard_btn').hide();
			    }
				
				
			});
			return false;
		}
	}
	
	</script>
	<script type="text/javascript">
	function onboard(){
		var mobile=$('#mobile').val();
		var email=$('#email').val();
		var pan=$('#pan').val();
		var shop_name=$('#shop_name').val();
		var f_name=$('#f_name').val();
		var l_name=$('#l_name').val();
		var dob=$('#dob').val();
		var address=$('#address').val();
		var state=$('#state').val();
		var district=$('#district').val();
		var pincode=$('#pincode').val();
		
		if(mobile==''){
			$('#respMessage').text('Enter registered Moile no');
			$('#respMessage').show();
		}else if(email==''){
			$('#respMessage').text('Enter valid email address');
			$('#respMessage').show();
		}else if(pan==''){
			$('#respMessage').text('Enter valid PAN number');
			$('#respMessage').show();
		}else if(f_name==''){
			$('#respMessage').text('Enter first name');
			$('#respMessage').show();
		}else if(l_name==''){
			$('#respMessage').text('Enter last name');
			$('#respMessage').show();
		}else if(dob==''){
			$('#respMessage').text('select date of birth');
			$('#respMessage').show();
		}else if(shop_name==''){
			$('#respMessage').text('Enter shop name');
			$('#respMessage').show();
		}else if(address==''){
			$('#respMessage').text('Enter Residence address');
			$('#respMessage').show();
		}else if(state==''){
			$('#respMessage').text('Select state');
			$('#respMessage').show();
		}else if(district==''){
			$('#respMessage').text('Select city');
			$('#respMessage').show();
		}else if(pincode==''){
			$('#respMessage').text('Ener pincode');
			$('#respMessage').show();
		}else{
			$('#resultLoading').show();
			
			$.ajax({
			 	url:"reuestOTP.action?param=onboard&mobile="+mobile+"&email="+email+"&pan="+pan+"&f_name="+f_name+"&l_name="+l_name+"&shop_name="+
			 			shop_name+"&dob="+dob+"&state="+state+"&district="+district+"&address="+address+"&pincode="+pincode,
				type : "POST",
				contentType: "application/json",
		        dataType: "json",
				success: function (data) {
					$('#resultLoading').hide();
					if((data.status==0 && data.response_type_id==1290) || data.status==0 && data.response_type_id==1307){
						$('#respMessage').text(data.message);
						$('#respMessage').show();
						$('#user_code').val(data.data.user_code);
						$('#heading').text('Activate AEPS');
						$('#onboard_fields').hide();
						$('#activate_aeps_fields').show();
						$('#verify_btn').hide();
						$('#request_btn').hide();
						$('#onboard_btn').hide();
						$('#activate_btn').show();
					}else{
						$('#respMessage').text(data.message);
						$('#respMessage').show();
						$('#verify_btn').hide();
						$('#request_btn').hide();
						$('#onboard_btn').show();
						$('#activate_btn').hide();
					}
					
				},
	
			 	failure: function (data) {
			 		$('#respMessage').text(data.message);
					$('#respMessage').show();
					$('#verify_btn').hide();
					$('#request_btn').hide();
					$('#onboard_btn').show();
					$('#activate_btn').hide();
			    }
				
				
			});
			return false;
		}
	}
	</script>

	<%
	MoneyTransferDao daoo=new MoneyTransferDao();
	String userCode=daoo.fetchEKOAEPSUserCode((String)session.getAttribute("agentID"));	
	
	%>
	 
	 <script type="text/javascript">
		function openEkoGateway() {
			
			$.ajax({
	    		url: "getSecretKey.action",
	    		type: 'get',
	            dataType: 'json',
	            contentType: 'application/json',
	            timeout:60000,
	   			success: function (response) {
				if(response.status==true)
				{
					  var params = response.params;
						
					  var url = '<%=PropertyFile.EKO_AEPS_LANDING_URL %>';
					  var form = document.createElement("form");
					  form.setAttribute('method', 'post');
					  form.setAttribute('action', url);
					
					  form.setAttribute('target', 'ekogateway');
					  popup = window.open("", "ekogateway");
					
					  for(const prop in params) {
					
					    	if(params.hasOwnProperty(prop)) {
					    		var input = document.createElement('input');
					    		input.type = 'hidden';
					    		input.name = prop;
					    		input.value = params[prop];
					    		form.appendChild(input);
					    	}
					
					  }
					
					  document.body.appendChild(form);
					  form.submit();
				}
			},

	 		failure: function (response) {

	    	}
		  });
		}
	</script>
	   
	<script>
	window.addEventListener('message', function(e) {
		
	  	if(event.origin === 'https://stagegateway.eko.in'
	  	|| event.origin === 'https://gateway.eko.in') {
	
	    	
	    	var datajson=e.data;
	  		var action = datajson.eko_gateway_response.action;
	  		
	  		if( action === 'debit-hook') {
	  		  // Add Logic to decide if the transaction can be proceeded  and make a note of transaction
		  		
	  		    var amount = e.data.eko_gateway_response.detail.data.amount;
		    	var customer_id = e.data.eko_gateway_response.detail.data.customer_id;
		    	var bank_code = e.data.eko_gateway_response.detail.data.bank_code;
		    	var user_code = e.data.eko_gateway_response.detail.data.user_code;
		    	var type = e.data.eko_gateway_response.detail.data.type;
		    	var client_ref_id = e.data.eko_gateway_response.detail.client_ref_id;
		  		var request_hash_params = e.data.eko_gateway_response.detail.request_hash_params
	  		  
		  		$.ajax({
		    		url: "validateAepsTransaction.action?amount="+amount+"&customer_id="+customer_id+"&bank_code="+bank_code+"&user_code="+user_code+"&type="+type+"&client_ref_id="+client_ref_id+"&request_hash_params="+request_hash_params,
		    		type: 'get',
		            dataType: 'json',
		            contentType: 'application/json',
		            timeout:60000,
		   			success: function (response) {
		   				var ekoReq =response.data;
		   				popup.postMessage(ekoReq, '*');
					},
	
			 		failure: function (response) {
			 			var ekoReq = {
								"eko_gateway_request": {
								  	"action": "go",
								  	"allow":false,
								    "message": "Failure"
								  }
								}
						popup.postMessage(ekoReq, '*');
			    	}
	  		  
	  			});
	  		
	  		}
	  		if(action=='response'){
	  			
	  			var http_status = e.data.eko_gateway_response.detail.http_status;
		    	var interaction_type_id  = e.data.eko_gateway_response.detail.interaction_type_id ;
		    	
		    	if(http_status==200 && interaction_type_id ==344){
		    		
		    		var client_ref_id = e.data.eko_gateway_response.detail.client_ref_id;
		    		var status = e.data.eko_gateway_response.detail.response.status;
		    		if(status==0){
		    			var response_status_id=e.data.eko_gateway_response.detail.response.response_status_id;
		    			var data = e.data.eko_gateway_response.detail.response.data;
				    	var tx_status = data.tx_status;
				    	var amount = data.amount;
				    	var tds = data.tds;
				    	var sender_name = data.sender_name;
				    	var tid = data.tid;
				    	var user_code = data.user_code;
				    	var bank_ref_num = data.bank_ref_num;
				    	var commission = data.commission;
				    	//Transaction successful
			    		if(tx_status==0){
			    			$.ajax({
					    		url: "updateAepsTransaction.action?amount="+amount+"&sender_name="+sender_name+"&tid="+tid+"&bank_ref_num="+bank_ref_num
					    				+"&user_code="+user_code+"&commission="+commission+"&client_ref_id="+client_ref_id+"&tds="+tds+"&tx_status="+tx_status,
					    		type: 'get',
					            dataType: 'json',
					            contentType: 'application/json',
					            timeout:60000,
					   			success: function (response) {
								},
				
						 		failure: function (response) {
						    	}
				  		  
				  			});
			    		}
			    		
		    		}
		    		
		    	}
		    	
	  		}
	  		
	  	}
	
	});
	
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
                 
						<c:if test="${sessionScope.eko_aeps eq 2 || sessionScope.eko_aeps eq 4 }">
						
							<div class="col-lg-6">
			                	<div class="card">
					                <div class="card-header d-flex align-items-center">
					                  <h4 id="heading">AEPS Transaction Process</h4><br>
					                </div>
				                	<div class="card-body">
				                		<p><strong>Status :</strong>${requestScope.service_desc }</p>
				                		<p><strong>Description :</strong>${requestScope.message }</p>
				                	</div>
						        </div>
			                </div>
	
						</c:if>
						<c:if test="${sessionScope.eko_aeps eq 1 }">
							
							<div class="col-lg-6">
			                	<div class="card">
					                <div class="card-header d-flex align-items-center">
					                  <h4 id="heading">AEPS Transaction Process</h4><br>
					                </div>
				                	<div class="card-body">
				                		<ol>
				                		<li value="1">Proceed to use AESP Service</li>
										<li value="2">Don not close main ${sessionScope.companyName } tab</li>
										<li value="3">Don not refresh browser</li>
										</ol>
										<br>
										<br>
										<div class="form-group">
											<button type="button" class="btn btn-success waves-effect waves-light m-r-10" onclick="openEkoGateway()">Proceed for AEPS</button>
			                            </div>
				                	</div>
						        </div>
			                </div>
			                <div class="col-lg-6">
			                	<div class="card">
							                <div class="card-header d-flex align-items-center">
							                  <h4 id="heading">AEPS Support</h4><br>
							                </div>
						                	<div class="card-body">
						                		<!-- <ol>
						                		<li ><strong>Slab 1</strong> : 100-3000 @ 0.2%</li>
						                		<br>
						                		<li ><strong>Slab 2</strong> : Above 3000 @ Rs. 8/-</li>
												<br>
												
												</ol> -->
												<div class="col-md-12">
													<h1><strong>Device support : </strong> <span style="color: #c5ef00;">+91-7834931562</span></h1>
												</div>
						                	</div>
						        </div>
			                </div> 
	
						</c:if>
						<c:if test="${sessionScope.eko_aeps eq 0 || sessionScope.eko_aeps eq 5 }">
					
							<div class="col-lg-6">
									
									<h5 id="respMessage" style="color:red;">${requestScope.message }</h5>	  
				                    <c:if test="${sessionScope.eko_aeps eq 5 }">
										<p><strong>Status :</strong>${requestScope.service_desc }</p>
				                		<p><strong>Description :</strong>${requestScope.message }</p>
									</c:if>	 
									<div class="card">
							                <div class="card-header d-flex align-items-center">
							                  <h4 id="heading">Verify Agent Mobile</h4><br>
							                </div>
						                	<div class="card-body">
				                  
				                  
				                          	<form class="floating-labels" name="activateAeps" action="activateAeps.action" method="post"  enctype="multipart/form-data" >
				
				                              <div class="form-group">
				                                  <input type="text" class="form-control"  name="mobile" value="${sessionScope.AgentDetailData.agentMobileNo }" id="mobile" maxlength="10" required placeholder="Enter Agent Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
				                                  
				                              </div>
				                              <div class="form-group" id="otp_div" style="display: none;">
				                                  <input type="text" class="form-control" name="otp" id="otp"  required placeholder="Enter otp" ><span class="highlight"></span> <span class="bar"></span>
				                                  
				                              </div>
				                              <div id="onboard_fields" style="display: none;">
				                              	<div class="form-group">
					                                  <input type="text" class="form-control" name="email" id="email"  required placeholder="Enter emil address" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="pan" id="pan"  required placeholder="Enter PAN Number" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="f_name"  id="f_name" required placeholder="Enter First Name" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="l_name" id="l_name" required placeholder="Enter Last Name" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="dob" autocomplete="off"  required placeholder="Select DOB" id="dob" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="shop_name" id="shop_name" required placeholder="Enter Shop Name" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
					                                  <input type="text" class="form-control" name="address"  id="address" required placeholder="Enter Residence Address" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
														<select class="form-control" name="state"  onchange="populatecities()" id="state" required >
					                                      	<option value="">------ Select State -----</option>
						                                    <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
						                                    <option value="Andhra Pradesh">Andhra Pradesh </option>
						                                    <option value="Arunachal Pradesh">Arunachal Pradesh </option>
						                                    <option value="Assam">Assam </option>
						                                    <option value="Bihar">Bihar </option>
						                                    <option value="Chandigarh">Chandigarh </option>
						                                    <option value="Chhattisgarh">Chhattisgarh </option>
						                                    <option value="Delhi">Delhi </option>
						                                    <option value="Goa">Goa </option>
						                                    <option value="Gujarat">Gujarat </option>
						                                    <option value="Haryana">Haryana </option>
						                                    <option value="Himachal Pradesh">Himachal Pradesh </option>
						                                    <option value="Jammu and Kashmir">Jammu and Kashmir </option>
						                                    <option value="Jharkhand">Jharkhand </option>
						                                    <option value="Karnataka">Karnataka </option>
						                                    <option value="Kerala">Kerala </option>
						                                    <option value="Madhya Pradesh">Madhya Pradesh </option>
						                                    <option value="Maharashtra">Maharashtra </option>
						                                    <option value="Manipur">Manipur </option>
						                                    <option value="Meghalaya">Meghalaya </option>
						                                    <option value="Mizoram">Mizoram </option>                                    
						                                    <option value="Nagaland">Nagaland </option>
						                                    <option value="Orissa">Orissa </option>
						                                    <option value="Pondicherry">Pondicherry</option>
						                                    <option value="Punjab">Punjab </option>
						                                    <option value="Rajasthan">Rajasthan </option>
						                                    <option value="Sikkim">Sikkim </option>
						                                    <option value="Tamil Nadu">Tamil Nadu </option>
						                                    <option value="Tripura">Tripura </option>
						                                    <option value="Uttarakhand">Uttarakhand </option>
						                                    <option value="Uttar Pradesh">Uttar Pradesh </option>
						                                    <option value="West Bengal">West Bengal</option>
					                                    </select>
													</div>
													
													<div class="form-group">
														<select class="form-control" name="district"  id="district" required>
				                                      		 <option value="">----- Select District -----</option>
				                                     	</select>
													</div>
													<div class="form-group">
						                                  <input type="text" class="form-control" name="pincode"  id="pincode" required placeholder="Enter pincode" ><span class="highlight"></span> <span class="bar"></span>
						                                  
						                             </div>
												  
												
				                              </div>
				                              <div id="activate_aeps_fields" style="display: none;">
				                              		<input type="hidden" class="form-control" name="userCode" id="user_code" required >
						                             <div class="form-group">
														<select class="form-control" name="modelName"  id="modelName" required>
				                                      		 <option>----- Select Device Model -----</option>
				                                      		 <option value="Morpho">Morpho</option>
				                                      		 <option value="Mantra">Mantra</option>
				                                      		 <option value="Startek">Startek</option>
				                                      		 <option value="TMF20">TMF20</option>
				                                     	</select>
													</div>
													
													<div class="form-group">
						                                  <input type="text" class="form-control" name="devicenumber"  id="devicenumber" required placeholder="Device Id" ><span class="highlight"></span> <span class="bar"></span>
						                                  
						                             </div>
						                             
						                             <div class="form-group">
					                                  <input type="text" class="form-control" name="shop_address"  id="shop_address" required placeholder="Enter Shop Address" ><span class="highlight"></span> <span class="bar"></span>
					                                  
					                              </div>
					                              <div class="form-group">
														<select class="form-control" name="shop_state"  onchange="populatecities2()" id="shop_state" required >
					                                      	<option value="">------ Select State -----</option>
						                                    <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
						                                    <option value="Andhra Pradesh">Andhra Pradesh </option>
						                                    <option value="Arunachal Pradesh">Arunachal Pradesh </option>
						                                    <option value="Assam">Assam </option>
						                                    <option value="Bihar">Bihar </option>
						                                    <option value="Chandigarh">Chandigarh </option>
						                                    <option value="Chhattisgarh">Chhattisgarh </option>
						                                    <option value="Delhi">Delhi </option>
						                                    <option value="Goa">Goa </option>
						                                    <option value="Gujarat">Gujarat </option>
						                                    <option value="Haryana">Haryana </option>
						                                    <option value="Himachal Pradesh">Himachal Pradesh </option>
						                                    <option value="Jammu and Kashmir">Jammu and Kashmir </option>
						                                    <option value="Jharkhand">Jharkhand </option>
						                                    <option value="Karnataka">Karnataka </option>
						                                    <option value="Kerala">Kerala </option>
						                                    <option value="Madhya Pradesh">Madhya Pradesh </option>
						                                    <option value="Maharashtra">Maharashtra </option>
						                                    <option value="Manipur">Manipur </option>
						                                    <option value="Meghalaya">Meghalaya </option>
						                                    <option value="Mizoram">Mizoram </option>                                    
						                                    <option value="Nagaland">Nagaland </option>
						                                    <option value="Orissa">Orissa </option>
						                                    <option value="Pondicherry">Pondicherry</option>
						                                    <option value="Punjab">Punjab </option>
						                                    <option value="Rajasthan">Rajasthan </option>
						                                    <option value="Sikkim">Sikkim </option>
						                                    <option value="Tamil Nadu">Tamil Nadu </option>
						                                    <option value="Tripura">Tripura </option>
						                                    <option value="Uttarakhand">Uttarakhand </option>
						                                    <option value="Uttar Pradesh">Uttar Pradesh </option>
						                                    <option value="West Bengal">West Bengal</option>
					                                    </select>
													</div>
													
													<div class="form-group">
														<select class="form-control" name="shop_district"  id="shop_district" required>
				                                      		 <option value="">----- Select District -----</option>
				                                     	</select>
													</div>
													<div class="form-group">
						                                  <input type="text" class="form-control" name="shop_pincode"  id="shop_pincode" required placeholder="Enter pincode" ><span class="highlight"></span> <span class="bar"></span>
						                                  
						                             </div>
													
						                             <div class="form-group">
						                                  <label>Upload PAN copy</label>
						                             </div>
						                             <div class="form-group">
						                                  <input type="file" class="form-control" name="panCard"  required ><span class="highlight"></span> <span class="bar"></span>
						                             </div>
						                             <div class="form-group">
						                                  <label>Upload Aadhaar Front</label>
						                             </div>
						                             <div class="form-group">
						                                  <input type="file" class="form-control" name="aadhaarFront" ><span class="highlight"></span> <span class="bar"></span>
						                             </div>
						                             <div class="form-group">
						                                  <label>Upload Aadhaar Back</label>
						                             </div>
						                             <div class="form-group">
						                                  <input type="file" class="form-control" name="aadhaarBack"  placeholder="Browse Aadhaar Back image" ><span class="highlight"></span> <span class="bar"></span>
						                             </div>
						                             
						                             <div class="form-group">
						                                  <label>Upload Voter Id Front copy (Optional)</label>
						                             </div>
						                             <div class="form-group">
						                                  <input type="file" class="form-control" name="voterIdFront"  placeholder="Browse Aadhaar Front image" ><span class="highlight"></span> <span class="bar"></span>
						                             </div>
						                             <div class="form-group">
						                                  <label>Upload Voter Id Back copy (Optional)</label>
						                             </div>
						                             <div class="form-group">
						                                  <input type="file" class="form-control" name="voterIdBack"  placeholder="Browse Aadhaar Back image" ><span class="highlight"></span> <span class="bar"></span>
						                             </div>
												</div>
				                              <div class="form-group">
				                              	<a href="Getpage.action?param=getPage" class="btn btn-primary btn-inline">Back</a> 
				                              	<button type="button" id="request_btn"  class="btn btn-success waves-effect waves-light m-r-10" onclick="sendOTP()">Request OTP</button>
				                              	<button type="button" id="verify_btn" style="display: none;" class="btn btn-success waves-effect waves-light m-r-10" onclick="vrifyOTP()">Verify Mobile</button>
												<button type="button" id="onboard_btn" style="display: none;" class="btn btn-success waves-effect waves-light m-r-10" onclick="onboard()">Submit Onboard Form</button>
												<button type="button" id="activate_btn" style="display: none;" class="btn btn-success waves-effect waves-light m-r-10" onclick="ActivateAeps()">Activate AEPS</button>
				                              </div>
				                          </form>
				                          </div >           
			                         	</div>
				                  			
			                </div>
			                <div class="col-lg-6">
			                	<div class="card">
							                <div class="card-header d-flex align-items-center">
							                  <h4 id="heading">AEPS Activation Process</h4><br>
							                </div>
						                	<div class="card-body">
						                		<ol>
						                		<li value="1">Activation of Rs. <%=PropertyFile.AEPS_CHARGE %>/- will be deducted at the time of Activation. If you have already paid it will be refunded by support team mannually.</li>
						                		<br>
						                		<li value="2">Verify Mobile with OTP</li>
						                		<br>
												<li value="3">Fill Onboarding form with valid <strong>PAN card, Shop Address and Aadhaar front and Back copy</strong></li>
												<br>
												<li value="4">Fill Activation form with your <strong>device number</strong></li>
												<br>
												<li value="5">On successfully submitted it will take <strong>2-3 days</strong> to Activate</li>
												</ol>
						                	</div>
						        </div>
			                </div>  
	                
	                	</c:if>
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