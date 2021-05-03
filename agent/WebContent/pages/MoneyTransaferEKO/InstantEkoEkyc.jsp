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
	                            <div class="col-md-5 form-group">
	                                   <form action="submitEkoCustomerKyc.action" method="post" name="aadhaarKyc" enctype="multipart/form-data">
		                                     <div class="form-group">
		                                         <label style="color: RED;font-weight: 600;font-size: 20px;">${requestScope.message }</label>
		                                     </div>
		                                     <div class="form-group">
		                                         <input type="text" required="required" name="zipPass" id="zipPass" class="form-control" placeholder="Enter zip password">
		                                     </div>
		                                     <div class="form-group">
		                                         <input type="text" readonly="readonly" name="senderId" class="form-control" value="${requestScope.senderId}" />
		                                     </div>
		                                     <%-- <div class="form-group">
		                                          <input type="text" readonly="readonly" name="transactionId" class="form-control" value="${requestScope.transactionId}" />
		                                     </div> --%>
		                                     <div class="form-group">
		                                         <input type="file" style="border: none;" required="required" name="aadhaarzip" id="aadhaarzip" class="form-control">
		                                     </div>
		                                     <div class="form-group" align="right">
		                                     	<a href="https://resident.uidai.gov.in/offlineaadhaar" target="_blank" >Click here to generate zip</a>
		                                     	<button type="button" onclick="window.close();" class="btn btn-inverse waves-effect waves-light">Close</button>
		                                     	<button type="submit" id="submitmobile" class="btn btn-success waves-effect waves-light m-r-10">Submit KYC</button>
		                             		</div>
		                             </form>
	                           </div>
	                            <div class="col-md-7 form-group" style="padding: 3%;border: 1px solid #ccc;">
	                            
	                            	<h4>Follow below instant customer kyc process</h4>
									<div class="table-responsive">
					                    <table class="table table-sm">
					                      <tbody>
					                        <tr>
					                          <td><strong>1.</strong> Generate Aadhaar XML zip file <a href="https://resident.uidai.gov.in/offlineaadhaar" target="_blank" >Go to "https://resident.uidai.gov.in/offlineaadhaar" to generate zip</a></td>
					                        </tr>
					                         <tr>
					                          <td><strong>2.</strong> Enter customer's 12 digit Aadhar Number and the security code displayed.</td>
					                        </tr>
					                         <tr>
					                          <td><strong>3.</strong> Click on Send OTP button; the customer will receive an OTP on the mobile number which is linked to his/her Aadhar number</td>
					                        </tr>
					                         <tr>
					                          <td><strong>4.</strong> The OTP needs to be entered and set the zip password.</td>
					                        </tr>
					                         <tr>
					                          <td><strong>5.</strong> Click on download to download zip file.</td>
					                        </tr>
					                         <tr>
					                          <td><strong>6.</strong> Upload this zip file along with zip password (shared on UIDAI website).</td>
					                        </tr>
					                      </tbody>
					                    </table>
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