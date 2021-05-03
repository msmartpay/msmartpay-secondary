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
	
	<style type="text/css">
	.alert_box{
		color: #000;
	    width: 260px;
	    /* height: 100px; */
	    background: #ccc;
	    margin: 2% 43%;
	    padding: 2%;
	    text-align: center;
	    border-radius: 3px;
	}
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
	
	
	
	function submitForm(){
		
		var BankAccount=$('#BankAccount').val();
		var CBankAccount=$('#CBankAccount').val();
		
		 if(BankAccount==''){
			alert('Please enter BankAccount.');
			$('#BankAccount').focus();
			return false;
		}else if(CBankAccount==''){
			alert('Please enter confirm password.');
			$('#CBankAccount').focus();
			return false;
		}else if(BankAccount!=CBankAccount){
			alert('BankAccount and Confirm BankAccount  should be same.');
			$('#BankAccount').val('');
			$('#CBankAccount').val('');
			$('#BankAccount').focus();
			return false;
		
		
		}else{
			$('#resultLoading').show();
			return true;
		}

	}
	
	function setBankCode(){
		
		$('#ajax_error').hide();
		var bankCode=$('#bankcode option:selected').val();
		$('#BankName').val($('#bankcode option:selected').text());
		getBankStatus(bankCode);
		
		return true;

	}
	function back(var1){
		$('#resultLoading').show();
		document.getElementById('back').href=var1;
 	}
	
	function getBankStatus(BankCode){
		
		$('#ajax_error').hide();
		var SenderId=$('#SenderId').val();

		$('#resultLoading').show();
		
		$.ajax({
		 	url:"bankIfscStatus.action?BankCode="+BankCode+"&SenderId="+SenderId,
			type : "GET",
			dataType: "text",
			success: function (response) {
				
				if(response=='IFSCRequired'){
					$('#IFSC').attr('required','required');
					$('#IFSC').attr('placeholder','IFSC Mendetory');
				}else{
					$('#IFSC').removeAttr('required');
					
				}
				$('#resultLoading').hide();
			},

		 	failure: function (response) {
		 		$('#IFSC').removeAttr('required');
				$('#resultLoading').hide();
		    }
			
			
		});
		return false;
		
		
	}
	
	function verifyAcount(){
	    
		if ($('#verify').is(':checked')) {
	        
			var BankAccount=$('#BankAccount').val();
			var SenderId=$('#SenderId').val();
			var IFSC=$('#IFSC').val();
			var bankCode=$('#bankcode option:selected').val();
			var bankName=$('#bankcode option:selected').text();
			if(bankCode==''){
				$('#bankcode').focus();
			}else if(BankAccount==''){
				$('#BankAccount').focus();
				return false;
			}else{
				$('#resultLoading').show();
				
				$.ajax({
				 	url:"ajaxverifyAccount.action?SenderId="+SenderId+"&AccountNo="+BankAccount+"&BankName="+bankName+"&IFSC="+IFSC+"&bankCode="+bankCode,
					type : "GET",
					dataType: "json",
					success: function (data) {
						var statusCode=data.Status;
						if(statusCode=='0'){
							$('#BeneName').val(data.BeneName);
						}
						$('#ajax_error').text(data.message);
						$('#ajax_error').show();
						$('#resultLoading').hide();
					},
	
				 	failure: function (response) {
				 		$('#ajax_error').text('Unable to verify. try later.');
	
				    }
					
					
				});
			}
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
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5%; ">
				<div class="container" style="width:100%;" >				
					
					<div class="col-md-6" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row" align="center">
                                    <div class="table-responsive">
                            
		                            	<form class="floating-labels" name="AddAccount" action="AddBeneAfterVerify.action">
												<div class="col-md-12 form-group" align="left">
		                                           <h3 class="box-title">Add Beneficiary</h3>
		                                       </div>
		                                       <c:if test="${requestScope.message!=null}">
		                                       <div class="col-md-12 form-group">
		                                          <h5 style="color:red;">${requestScope.message}</h5>
		                                       </div>
		                                       </c:if>
		                                       <h5 class="ajax_error" style="color:red;display: none;"></h5>
		                                       <div class="col-md-6 form-group">
		                                           <input type="text" class="form-control" name="SenderId" id="SenderId" value="${requestScope.SenderId}" required placeholder="Enter sender Mobile" readonly="readonly" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
		                                       </div>
		                                      <div class="col-md-6 form-group">
													
													<select name="bankcode" id="bankcode" class="form-control" required onchange="setBankCode()">
														<option selected="selected" value="">Select bank name</option>
														<c:if test="${requestScope.bankList!=null}">
														<c:forEach var="banklist" items="${requestScope.bankList}">
																
																	<option value="${banklist.bcode}">${banklist.bname}</option>
																
														</c:forEach>
														</c:if>
													</select><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                        </div>
		                                       <div class="col-md-6 form-group">
		                                       		<input name="BankName" id="BankName" type="hidden">
		                                           <input type="password" class="form-control" name="BankAccount" id="BankAccount"  required placeholder="Bank Account" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       
		                                       <div class="col-md-6 form-group">
		                                           <input type="text" class="form-control" name="CBankAccount" id="CBankAccount"  required placeholder="Confirm Bank Account" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       <div class="col-md-12 form-group">
		                                       		<div class="col-md-1" style="padding: 0;width: 4%;">
		                                       			<input type="checkbox" onclick="verifyAcount()" style="height: 18px;width: 100%;" name="verify" id="verify" >
		                                       		</div>
		                                       		<div class="col-md-9" align="left">
		                                       			Verify Account
		                                       		</div>
		                                       		 
		                                       </div>
		                                       <div class="col-md-6 form-group">
		                                           <input type="text" class="form-control" name="BeneName" id="BeneName"  required placeholder="Bene Name" ><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       <div class="col-md-6 form-group">
		                                           <input type="text" class="form-control" name="BeneMobile" id="BeneMobile"  required placeholder="Beneficiary Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       <div class="col-md-6 form-group">
		                                           <input type="text" class="form-control" name="IFSC"  id="IFSC" required placeholder="Enter IFSC (Optional)" ><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       <div class="col-md-12 form-group" align="left">
			                                       <button type="submit" class="btn btn-inverse waves-effect waves-light">Reset</button>
			                                       <a id="back" href="#" onclick="back('findSender.action?senderId=${requestScope.SenderId}')" class="btn btn-primary btn-inline">Back</a> 
													<button type="submit" class="btn btn-success waves-effect waves-light m-r-10" onclick="return submitForm();">Submit</button>
			                                       
		                                       </div>
		                                   </form>
		                                   
		                           </div>                                    
                                                                
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
	
	<!-- <div id="addbeneAlert" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;">
		<div style="width: 100%; text-align: center; position: absolute; left: 0px; top: 50%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
			<div style="margin: 2% 43%;">
				<div style="border-radius:25%;background: #000;color: #fff; padding: 2%;" align="right">X</div>
				<div id="addbeneAlert_msg" class="alert_box">Verify Successful</div>
			</div>
			
		</div>
		<div class="bg" style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: #fff;"></div>
	</div> -->
	
	
	
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