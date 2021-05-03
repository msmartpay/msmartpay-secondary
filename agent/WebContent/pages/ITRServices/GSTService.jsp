<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>	
	
	
	<!-- Basic Page Needs
	================================================== -->
	
	<title><%=companyName %></title>	
	<%@ include file="../../script.jsp" %>
	
	
	
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
	                            	<div class="col-md-12 form-group"><h3 class="box-title">GST and Other Business Entity registration</h3></div>
									<div class="col-md-5 ">
										
											<div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#itr" data-toggle="tab">Submit Request</a></li>
                                                
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="itr">
													<form name="ITRServiceForm" method="post" action="GSTService.action?param=GSTServicesubmit">
														<c:if test="${requestScope.message!=null }">
														<div class="form-group" style="color: GREEN;">
														${requestScope.message}
														</div>
														</c:if>
														<div class="form-group">
															<input name="name" type="text" class="form-control" required="required" placeholder="Please Enter  Name"  maxlength="100">
														</div>
														<div class="form-group">
															<input type="text" name="emailID"  type="text"  maxlength="60" class="form-control" placeholder="Please Enter Email">
														</div>
														
														<div class="form-group">
															<input type="text" name="mobileNo" type="text"  maxlength="10" class="form-control" placeholder="Please Enter Mobile No.">
														</div>
														<div class="form-group">
															<input type="text" class="form-control" required="required" placeholder="Please Enter Address" name="address"  type="text"  maxlength="200">
														</div>
														
														<div class="form-group">
														<select class="col-md-6 col-md-offset-0 form-control" name="serviceType"  id="itr" required >
															
					                                      	<option value="">----------------- Select Registration Type -----------------</option>
						                                    <option value="GST Registration">GST Registration</option>
						                                    <option value="Company Registration">Company Registration</option>
						                                    <option value="Partnership Firm Registration">Partnership Firm Registration</option>
						                                    <option value="Proprietor Firm Registration">Proprietor Firm Registration</option>
						                                    
						                                    <option value="PAN Application">PAN Application</option>
						                                    <option value="TAN Application">TAN Application</option>
						                                    <option value="ISO registration">ISO registration</option>
						                                    
						                                    <option value="Trademark Registration">Trademark Registration</option>
						                                    <option value="Patent Registration">Patent Registration</option>
						                                    <option value="Excise Registration">Excise Registration</option>
						                                    
						                                    <option value="Service Tax Registration">Service Tax Registration</option>
						                                    <option value="VAT Registration">VAT Registration</option>
						                                    <option value="CST Registration">CST Registration</option>
						                                    
						                                    <option value="Import Export Code">Import Export Code</option>
						                                    <option value="ESI /PF Registration">ESI /PF Registration</option>
						                                    <option value="SSI/MSME registration">SSI/MSME registration</option>
						                                    
					                                    </select>
														</div>
														<div class="form-group">
														<br>
														<br>
														</div>
														<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-success waves-effect waves-light m-r-10" >Submit Form</button>
														
													</form>
												</div>
											</div>
										</div>
                                	</div>
                                
                                	<div class="col-md-6">
                                    	<div class="spacer-xl"></div>
                                    	<div class="cta-inner" align="right">
                                            <div class="cta-txt" style="width: 90%;" align="left" >
                                                <h3>GST Goods and Service Tax (One Tax-One Nation)</h3>
 
												<p>	>    GST Registrations Support</p>
												<p>	>    GST Statutory Compliances and e-returns</p>
												<p>	>    GST Consultancy for law and Procedures</p>
													
												<h5>	Download PDF for more details <a href="file/GST and Other Registraton.pdf" target="_blank">Click here to download</a></h5>
                                                <h5>For more Details please call on +91-9582262348</h5>
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