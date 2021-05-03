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
   	
   	function refundeko(reqType){
   		if(reqType=='OTP'){
   			document.refund.action="refundeko.action";
   		}else{
   			if($('#OTP').val()!=''){
   				document.refund.action="confirmrefundeko.action";
   			}else{
   				alert('Please enter OTP sent on customer mobile.');
   				return false;
   			}
   			
   		}
   		return true;
   		$('#resultLoading').show();
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
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 10% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                            <div class="table-responsive">
	                            <div class="col-md-6 form-group">
	                                   <form name="refund" class="floating-labels" action="#" onsubmit="return refundeko('${requestScope.requestType}')">
	                                    	<input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
									 	   <div class="col-md-12 form-group">
	                                           <h3 class="box-title">ERefund Transaction</h3>
	                                       </div>
	                                       <c:if test="${requestScope.message!=null}">
	                                       <div class="col-md-12 form-group">
	                                          <h5 style="color:red;">${requestScope.message}</h5>
	                                       </div>
	                                       </c:if>
	                                       <div class="col-md-12 form-group">
	                                       	<input type="hidden" name="SenderId" value="${requestScope.SenderId}" />
	                                           <input type="text" class="form-control" name="tranId" id="tranId" value="${requestScope.tranId}" required placeholder="Enter Service Delivery Tid" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <c:if test="${requestScope.OTP eq 'Y' }">
	                                       
		                                       <div class="col-md-12 form-group">
		                                           <input type="text" class="form-control" name="OTP" id="OTP" maxlength="15" placeholder="Enter OTP"><span class="highlight"></span> <span class="bar"></span>
		                                           
		                                       </div>
		                                       
		                                       <div class="col-md-12 form-group" align="right">
		                                       		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
		                                       		<a id="back" href="#" onclick="back('findSendereko.action?senderId=${requestScope.SenderId}')" class="btn btn-primary btn-inline">Back</a> 
		                                       		<button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Confirm Refund</button>
		                                       </div>
	                                       
	                                       </c:if>
	                                       <c:if test="${requestScope.OTP eq null ||  requestScope.OTP eq ''}">
		                                       <div class="col-md-12 form-group" align="right">
		                                       		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
		                                       		<a id="back" href="#" onclick="back('findSendereko.action?senderId=${requestScope.SenderId}')" class="btn btn-primary btn-inline">Back</a> 
		                                       		<button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Proceed to Refund</button>
		                                       </div>
	                                       </c:if>
	                                   </form>
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