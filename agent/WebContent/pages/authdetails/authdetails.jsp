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
	                            
	                            	<div class="col-md-6">
                                                                        
                                             <div class="form-group"><h3>API Auth Details</h3></div>
                                          	<table class="table table-striped table-bordered" style="background: #dfdfdf;">
                                          		<tr>
                                          			<th>Name</th>
                                          			<th></th>
                                          			<th>value</th>
                                          		</tr>
                                          		<tr>
                                          			<td>Agent Id</td>
                                          			<td>:</td>
                                          			<td>${sessionScope.agentID }</td>
                                          		</tr>
                                          		<tr>
                                          			<td>Txn key</td>
                                          			<td>:</td>
                                          			<td>${sessionScope.TxnKey }</td>
                                          		</tr>
                                          	</table>
                                          	<div class="form-group">
                                                 <a href="common.action?param=download-api-doc" class="btn btn-success waves-effect waves-light m-r-10">Download API Document</a>                                                 
                                             </div>
                                             <br>
                                             <div class="form-group">${requestScope.message }</div>
	                                    	  <div class="form-group"><h3>Update Callback URL</h3></div>
	                                          <form action="common.action?param=update-callback" method="post" name="update" >                              
	                                             <div class="form-group">
	                                             	<input type="text" autocomplete="off" name="call_back" class="form-control" placeholder="Enter Call back URL" />
	                                             </div>
	                                              <div class="form-group">
	                                                 <button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Update Callback URL</button>
	                                              </div>
	                                          </form>
                                    </div>
                                    
                                    <div class="col-md-6">
                                    	  
                                          <div class="form-group">
                                          	<div class="form-group"><h3>Callback URL Parameters</h3></div>
                                          	
                                          	<table class="table table-striped table-bordered" style="background: #dfdfdf;">
                                          		<tr>
                                          			<td>Method Type</td>
                                          			<td>Get</td>
                                          		</tr>
                                          		<tr>
                                          			<td>Content Type</td>
                                          			<td>text/html</td>
                                          		</tr>
                                          	
                                          	</table>
                                          	<br>
                                          	<table class="table table-striped table-bordered" style="background: #dfdfdf;">
                                          		<tr>
                                          			<th>Parameter Name</th>
                                          			<th></th>
                                          			<th>Possible value</th>
                                          		</tr>
                                          		<tr>
                                          			<td>txnStatus</td>
                                          			<td>:</td>
                                          			<td>Success,Pending,Failure,Refunded</td>
                                          		</tr>
                                          		<tr>
                                          			<td>txn_id</td>
                                          			<td>:</td>
                                          			<td>Your Generated Reference ID which are <br> getting from your request</td>
                                          		</tr>
                                          		<tr>
                                          			<td>mobile_operator</td>
                                          			<td>:</td>
                                          			<td>Operator Name</td>
                                          		</tr>
                                          		<tr>
                                          			<td>mobile_number</td>
                                          			<td>:</td>
                                          			<td>Mobile Recharged</td>
                                          		</tr>
                                          		
                                          		<tr>
                                          			<td>Api_txn_id</td>
                                          			<td>:</td>
                                          			<td>our System generated Orderid</td>
                                          		</tr>
                                          		
                                          		<tr>
                                          			<td>OperatorTxnId</td>
                                          			<td>:</td>
                                          			<td>Operator Provided Transaction Id</td>
                                          		</tr>
                                          	
                                          	</table>
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