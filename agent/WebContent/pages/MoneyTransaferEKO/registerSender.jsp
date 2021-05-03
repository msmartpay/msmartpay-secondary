<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>	
	<!-- Basic Page Needs
	================================================== -->
	 <title>E${sessionScope.companyName}</title>

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
		
		var today = (now.getFullYear()-18)+'-'+mm+ '-' +dd;
		$('#dob').val(today);
		
		$(document).ready(function(e) {
			
			$("#dob").datepicker({
	            changeMonth: true,
				changeYear: true,
				dateFormat: 'yy-mm-dd',
				yearRange: '-60y:c+nn',
				maxDate: '-14y'
				
			})
			
		});
		
		
	});
	
	function resendOTP(){
		var SenderId=$('#SenderId').val();

		$('#resultLoading').show();
		
		$.ajax({
		 	url:"regSenderResendOTPeko.action?SenderId="+SenderId,
			type : "GET",
			dataType: "text",
			success: function (response) {
				$('#resultLoading').hide();
				$('#resendMsg').text(respMsg);
				$('#errorMsg').hide();
			},

		 	failure: function (response) {
		 		$('#resultLoading').hide();
		 		$('#resendMsg').text(respMsg);
		 		$('#resendMsg').show();
		    }
			
			
		});
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

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>ERegister Sender</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 10% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            <h5 id="errorMsg" align="center" style="color:red;">${requestScope.message}</h5>
	                            <h5 id="resendMsg" align="center" style="color:red;display: none;"></h5>
	                            <div class="table-responsive">
	                            	
	                            
			                            <c:choose>
			                            <c:when test="${requestScope.verifyStatus!=null }">
			                            	<div class="col-md-12 form-group">
			                                     <h3 class="box-title">Verify Registration</h3>
			                                 </div>
			                            
			                           		 <form class="floating-labels" action="verifyregSendereko.action">
													<div class="col-md-2 form-group">
			                                           <input type="text" class="form-control" value="${requestScope.SenderId }" name="SenderId" id="SenderId" maxlength="10" required placeholder="Enter sender Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
			                                           
			                                       </div>
			                                       <div class="col-md-2 form-group">
			                                           <input type="text" class="form-control" name="SenderName" value="${requestScope.SenderName }"  id="SenderName"  required placeholder="Enter sender name" ><span class="highlight"></span> <span class="bar"></span>
			                                           
			                                       </div>
			                                       <div class="col-md-2 form-group">
			                                           <input type="text" class="form-control" name="dob" value="${requestScope.dob }" readonly="readonly" >
			                                       </div>
			                                       <div class="col-md-2 form-group">
			                                           <input type="text" class="form-control" name="Pincode" value="${requestScope.Pincode }"  id="" maxlength="10" required placeholder="Enter Pincode" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
			                                       </div>
			                                       <div class="col-md-2 form-group">
			                                           <input type="text" class="form-control" name="otp" id="input1" maxlength="10" required placeholder="OTP" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
			                                         </div> 
			                                       <div class="col-md-4 form-group">
			                                       <button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Verify Sender</button>
			                                       <button type="button" onclick="resendOTP()" class="btn btn-success waves-effect waves-light m-r-10">ReSend OTP</button>
			                                       
			                                       </div>
			                                   </form>
			                            </c:when>
			                           <c:otherwise>
	                           
	                           			<div class="col-md-12 form-group">
	                                     <h3 class="box-title">Sender Registration</h3>
	                                 	</div>
	                           
	                                   <form class="floating-labels " action="regSendereko.action">
	                                    <input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
	
	                                       <div class="col-md-2 form-group">
	                                           <input type="text" class="form-control" readonly="readonly" name="SenderId" value="${requestScope.SenderId }" id="input1" maxlength="10" required placeholder="Enter sender Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       
	                                       <div class="col-md-2 form-group">
	                                           <input type="text" class="form-control" name="SenderName" id="input1"  required placeholder="Enter sender name" ><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-2 form-group">
			                                    <input type="text" class="form-control" name="dob" id="dob" readonly="readonly" >
			                               </div>
	                                       <div class="col-md-2 form-group">
	                                           <input type="text" class="form-control" name="Pincode"  maxlength="10" required placeholder="Enter Pincode" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-4 form-group">
	                                       <button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Register</button>
	                                       <button type="submit" class="btn btn-inverse waves-effect waves-light">Reset</button>
	                                       <a href="moneytransfereko.action?param=Money Transfer" class="btn btn-primary btn-inline">Back</a> 
										
	                                       </div>
	                                   </form>
	                                </c:otherwise></c:choose>
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