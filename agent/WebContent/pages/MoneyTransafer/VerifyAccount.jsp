<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title>${sessionScope.companyName}</title>

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	<script type="text/javascript">
	$(document).ready(function(e) {
		
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
			yearRange:'1924:2020',
            numberOfMonths: 1,
			
			
		})
		
	});
	
	
	
	function submitForm(senderid){
		
		var bankName=$('#bankname option:selected').text();
		$('#BankName').val(bankName);
		$('#resultLoading').show();
		document.verifyAccount.action='verifyAccount.action';
		document.verifyAccount.submit();
		return true;

	}
	function setBankCode(){
		
		var bankCode=$('#bankname option:selected').val();
		$('#IFSC').val(bankCode);
		getBankStatus(bankCode);
		
		return true;

	}
	function back(var1){
		$('#resultLoading').show();
		document.getElementById('back').href=var1;
 	}
	
	function getBankStatus(BankCode){
		var SenderId=$('#SenderId').val();
		if(BankCode==''){
			alert('Please select Bank.');
			$('#bankname').focus();
		}else{
			
			$('#resultLoading').show();
			
			$.ajax({
			 	url:"bankVerificationStatus.action?BankCode="+BankCode+"&SenderId="+SenderId,
				type : "GET",
				dataType: "text",
				success: function (response) {
					
					if(response=='Available'){
						$('#verifyAccount').removeAttr('disabled');
						$('#verifyAccount').attr('title','Verify Account');
						$('#jerror_msg').html('Bank available for Account Verification.');
						$('#verifyAccount').attr('readonly','readonly');
						
					}else if(response=='IFSCRequired'){
						$('#verifyAccount').removeAttr('disabled');
						$('#verifyAccount').attr('title','Verify Account');
						$('#jerror_msg').html('Account Verification Available. IFSC required.');
						$('#IFSC').attr('placeholder','IFSC Mandatory');
						$('#IFSC').val('');
						$('#IFSC').removeAttr('readonly');
						$('#IFSC').attr('required','required');
						
					}else{
						$('#verifyAccount').attr('disabled','disabled');
						$('#verifyAccount').attr('title',response);
						$('#jerror_msg').html('Bank available for Account Verification.');
						$('#verifyAccount').attr('readonly','readonly');
						$('#IFSC').removeAttr('required');
					}
					$('#error_msg').html('');
					$('#AccountNo').val();
					$('#resultLoading').hide();
				},

			 	failure: function (response) {
			 		$('#error_msg').html('');
			 		$('#AccountNo').val();
			 		$('#jerror_msg').html(response);
			 		$('#verifyAccount').attr('readonly','readonly');
			 		$('#IFSC').removeAttr('required');
					$('#resultLoading').hide();
					
			    }
				
				
			});
			return false;
		}
		
		
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
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
	                            
	                            	<div class="col-md-6">
	                                   <form class="floating-labels" name="verifyAccount" onsubmit="return submitForm();">
	                                    <input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
	
											<div class="col-md-12 form-group">
	                                       		<h3 class="box-title">Account Verification</h3>
	                            				<h5 id="error_msg" style="color:red;">${requestScope.message}</h5>
	                            				<h5 id="jerror_msg" style="color:red;"></h5>
	                                       </div>
	
	                                       <div class="col-md-12 form-group">
	                                           <input type="text" class="form-control" name="SenderId" id="SenderId" value="${requestScope.SenderId}" required readonly="readonly" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                       </div>
	                                      <div class="col-md-12 form-group">
												
												<select name="bankname" id="bankname" class="form-control" onchange="setBankCode()" required>
													<option selected="selected" value="">Select bank name</option>
													<c:if test="${requestScope.bankList!=null}">
													<c:forEach var="banklist" items="${requestScope.bankList}">
															
														<option value="${banklist.bcode}">${banklist.bname}</option>
															
													</c:forEach>
													</c:if>
												</select><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                        </div>
	                                       <div class="col-md-12 form-group">
	                                       		<input type="hidden" name="BankName" id="BankName">
	                                           <input type="text" class="form-control" name="AccountNo" id="AccountNo"  required placeholder="Bank Account" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       
	                                       <div class="col-md-12 form-group">
	                                           <input type="text" class="form-control" id="IFSC" name="IFSC" readonly="readonly"  required placeholder="Enter IFSC (Optional)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-12 form-group">
	                                       		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
	                                       		<a id="back" href="#" onclick="back('findSender.action?senderId=${requestScope.SenderId}')" class="btn btn-primary btn-inline">Back</a> 
												<button type="submit"  id="verifyAccount" disabled="disabled" class="btn btn-success waves-effect waves-light m-r-10" >Submit</button>
	                                       		
											</div>
	                                   </form>
	                                </div>   
	                                <c:if test="${requestScope.verifyResult!=null }">
	                               	<div class="col-md-5" style="float: right;border: 1px solid #ccc;margin-top: 80px;padding: 2%;">
					                    	
					                    		<div class="col-md-6 form-group">
					                    			<strong>Beneficiary Name</strong>
					                    		</div>
					                    		
					                    		<div class="col-md-6 form-group">
					                    			${requestScope.verifyResult.BeneName}
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			<strong>Bank Name</strong>
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			${requestScope.verifyResult.BankName}
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			<strong>Account No</strong>
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			${requestScope.verifyResult.AccountNo}
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			<strong>Status</strong>
					                    		</div>
					                    		<div class="col-md-6 form-group">
					                    			${requestScope.verifyResult.message}
					                    		</div>
					                    	
	                               	</div>
	                                </c:if>   
	                           </div>
	                        </div>
		               
	                        
	                    </div>
	               		
	               
	            	</div>
                
            	</div>
			</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>


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