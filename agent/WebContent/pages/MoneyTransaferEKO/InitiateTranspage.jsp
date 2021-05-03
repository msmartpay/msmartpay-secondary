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
		
		var Amount=$('#Amount').val();
		
		
		 if(Amount==''){
			alert('Please enter Amount.');
			$('#Amount').focus();
			return false;
		}
		 else if(Amount<100){
				alert('Minimum balance Rs. 100.');
				$('#Amount').focus();
				return false;
			}
		else{
			$('#resultLoading').show();
			 if(confirm("Please confirm Transfer balance"))
				return true;
			else
				return false;

		}

	}
	function back(var1){
		$('#resultLoading').show();
		document.getElementById('back').href=var1;
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
			<section class="page-content" style="padding: 2% 5% 2% 5%;background: #fff;border-top:2px solid #ccc;">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                        	
	                            <div class="table-responsive">
	                            
	    
	                                   <form class="floating-labels " action="InitiateTransactioneko.action" onsubmit="return submitForm();">
	                                    <input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
										   <div class="col-md-12 form-group" style="margin-bottom: 7px;">
	                                           <h3 class="box-title">EInitial Transaction
					                        	<span>
					                        	<c:if test="${requestScope.TxnType eq '1'}">
					                        	(NEFT)
					                        	</c:if>
												<c:if test="${requestScope.TxnType eq '2'}">
					                        	(IMPS)
					                        	</c:if>
												</span>
												</h3>
												<h5 style="color:red;">${requestScope.message}</h5>
	                                       </div>
										   <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>SenderId</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;" >
	                                           <input type="text" class="form-control" name="SenderId" id="SenderId" value="${requestScope.SenderId}" required placeholder="Enter sender Mobile" readonly="readonly" ><span class="highlight"></span> <span class="bar"></span>
	                                       </div>
	                               			<div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Sender Name</label>
	                                       </div>	
	                                      <div class="col-md-5 form-group" style="margin-bottom: 7px;">
												<input type="text" class="form-control" name="SenderName" id="SenderName" value="${requestScope.SenderName}" required placeholder="Enter Sender Name" readonly="readonly" ><span class="highlight"></span> <span class="bar"></span>
	                                        </div>
	                                        <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Bene Mobile</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;">
	                                           <input type="text" class="form-control" readonly="readonly" name="BeneMobile" id="Bene Mobile" value="${requestScope.BeneMobile}" required placeholder="Bank Accountt" ><span class="highlight"></span> <span class="bar"></span>
				
	                                       </div>
	                                       <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Bank Name</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;" >
	                                           <input type="text" class="form-control" name="BankName" id="BankName" value="${requestScope.BankName}" required placeholder="Enter sender Mobile" readonly="readonly" ><span class="highlight"></span> <span class="bar"></span>
	                                       </div>
	                                       <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Bene Account</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;">
	                                           <input type="text" class="form-control" name="BeneAccount" id="Bene Account" readonly="readonly" value="${requestScope.BeneAccount}"  required placeholder="Bene Account" ><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Bene Name</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;">
	                                           <input type="text" class="form-control" name="BeneName" id="BeneName" readonly="readonly" value="${requestScope.BeneName}"  required placeholder="Bene Name" ><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                        	<input type="hidden" class="form-control" name="RecipientId" id="RecipientId"  required placeholder="Txn Types" value="${requestScope.RecipientId}" ><span class="highlight"></span> <span class="bar"></span>
	                                           <input type="hidden" class="form-control" name="TxnType" id="TxnType"  required placeholder="Txn Types" value="${requestScope.TxnType}" ><span class="highlight"></span> <span class="bar"></span>
	                                       <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>IFSC</label>
	                                       </div>
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;">
	                                           <input type="text" class="form-control" name="IFSC" required placeholder="Enter IFSC" readonly="readonly" value="${requestScope.IFSC}" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       
	                                        <div class="col-md-4 form-group" style="margin-bottom: 7px;" >
	                                           <label> Amount</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;">
	                                           <input type="text" class="form-control" name="Amount" id="Amount" value=""  required placeholder="Enter Amount (100-25000)" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                        <div class="col-md-4 form-group" style="margin-bottom: 7px;">
	                                           <label>Remarks</label>
	                                       </div>	
	                                       <div class="col-md-5 form-group" style="margin-bottom: 7px;" align="center">
	                                           <input type="text" class="form-control" name="Remark" id="Remark"   required placeholder="Remarks" ><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-6 form-group" align="right">
	                                       <a id="back" href="#" onclick="back('findSendereko.action?senderId=${requestScope.SenderId}')" class="btn btn-primary btn-inline">Back</a> 
											<button type="submit" class="btn btn-success waves-effect waves-light m-r-10" >Submit</button>
	                                       </div>
	                                   </form>
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