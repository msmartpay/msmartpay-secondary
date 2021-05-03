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
     /* printDivCSS = new String ('<link href="myprintstyle.css" rel="stylesheet" type="text/css">')
     function printDiv(divId) {
         window.frames["print_frame"].document.body.innerHTML=printDivCSS + document.getElementById(divId).innerHTML;
         window.frames["print_frame"].window.focus();
         window.frames["print_frame"].window.print();
     } */
     function printDiv(divId) 
     {

       var divToPrint=document.getElementById(divId);

       var newWin=window.open('','Print-Window');

       newWin.document.open();

       newWin.document.write('<html><body onload="window.print()">'+divToPrint.innerHTML+'</body></html>');

       newWin.document.close();

       setTimeout(function(){newWin.close();},10);

     }
     function back(){
    		$('#resultLoading').show();
    		document.getElementById('back').href="findSendereko.action?senderId=${requestScope.TransactionDetail.SenderId}";
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
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
                    <div class="col-md-12">
                        <div class="white-box">
                            <div class="table-responsive">
                            
                                <form name="submitForm" method="post" >
								<div id="div1">	
									<div class="table-responsive">
										<div class="col-md-12 form-group" align="center">
											<div class="col-md-12 form-group" align="left">
												 <h3 class="box-title">ETransaction Status</h3>
		
											</div>
											<div class="col-md-12 form-group" align="left">
												<h5 style="color:green;">${requestScope.message}</h5>
		
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Transaction ID :</label>
												<label>${requestScope.TransactionDetail.tranid}</label>
											</div>
											
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Transaction Date :</label>
												<label>${requestScope.TransactionDetail.TxnDate}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Transaction time :</label>
												<label>${requestScope.TransactionDetail.TxnTime}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Bene Name</label>
												<label>${requestScope.TransactionDetail.BeneName}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Bene Mobile :</label>
												<label >${requestScope.TransactionDetail.BeneMobile}</label>
											</div>
											
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">tid :</label>
												<label >${requestScope.TransactionDetail.apiTid}</label>
											</div>
											
											
											
											
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Sender Id :</label>
												<label>${requestScope.TransactionDetail.SenderId}</label>
											</div>
											
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Recipient Name :</label>
												<label>${requestScope.TransactionDetail.SenderId}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Bank Name :</label>
												<label>${requestScope.TransactionDetail.BankName}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">A/C</label>
												<label>${requestScope.TransactionDetail.BeneAccount}</label>
											</div>
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Bank RRN :</label>
												<label >${requestScope.TransactionDetail.BankRRN}</label>
											</div>
											
											<div class="col-md-4 form-group" align="left">
												<label  style="font-weight: bold;">Transaction Amount :</label>
												<label >${requestScope.TransactionDetail.TxnAmount}</label>
											</div>
										</div>
								
								
                            		</div>
									
									
									</div>
									<div id="div2" style="display: none;">	
									<div class="table-responsive">
										<div class="col-md-12 form-group" align="center">
											<div class="col-md-12 form-group" >
												 <img alt="logo" src="images/${sessionScope.dynamicData.headerHomeImage }">
		
											</div>
											<div class="col-md-12 form-group" >
												 <h3 class="box-title">Transaction Status</h3>
		
											</div>
											<div class="col-md-12 form-group" >
												<h5 style="color:green;">${requestScope.message}</h5>
		
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Transaction ID :</label>
												<label>${requestScope.TransactionDetail.tranid}</label>
											</div>
											
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Transaction Date :</label>
												<label>${requestScope.TransactionDetail.TxnDate}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Transaction time :</label>
												<label>${requestScope.TransactionDetail.TxnTime}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Bene Name</label>
												<label>${requestScope.TransactionDetail.BeneName}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Bene Mobile :</label>
												<label >${requestScope.TransactionDetail.BeneMobile}</label>
											</div>
											
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">tid :</label>
												<label >${requestScope.TransactionDetail.apiTid}</label>
											</div>
											
											
											
											
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Sender Id :</label>
												<label>${requestScope.TransactionDetail.SenderId}</label>
											</div>
											
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Recipient Name :</label>
												<label>${requestScope.TransactionDetail.SenderId}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Bank Name :</label>
												<label>${requestScope.TransactionDetail.BankName}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">A/C</label>
												<label>${requestScope.TransactionDetail.BeneAccount}</label>
											</div>
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Bank RRN :</label>
												<label >${requestScope.TransactionDetail.BankRRN}</label>
											</div>
											
											<div class="col-md-4 form-group" >
												<label  style="font-weight: bold;">Transaction Amount :</label>
												<label >${requestScope.TransactionDetail.TxnAmount}</label>
											</div>
										</div>
								
								
                            		</div>
									
									
									</div>	
								</form>
									<div class="col-md-6 form-group" >
										<a href="#" onclick="printDiv('div2');" class="btn btn-primary btn-inline">Print</a>
										<a onclick="back();" id="back" href="#" class="btn btn-primary btn-inline">Back</a> 
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