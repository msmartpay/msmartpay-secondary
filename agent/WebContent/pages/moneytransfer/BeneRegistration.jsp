<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>

	<script type="text/javascript">
	
	$(document).ready(function(){
		  
	    var now = new Date();
		var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	 	
		$("#datepicker").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2010',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		  
	});
	
	</script>
	<script type="text/javascript">
	function backFunction(param){
		$("#resultLoading").show();
		document.Beneficiary.action=param;
		document.Beneficiary.submit();
	}
	function submitForm(param){
		$("#resultLoading").show();
		document.Beneficiary.action=param;
		document.Beneficiary.submit();
	}
	</script>
	<style>
			.ontop {
				z-index: 999;
				width: 100%;
				height: 100%;
				top: 0;
				left: 0;
				display: none;
				position: absolute;				
				color: #aaaaaa;
				opacity: 1;
				filter: alpha(opacity = 50);
			}
			#popup {
				width: 300px;
				height: 200px;
				position: absolute;
				color: #000000;
				background-color: #ffffff;
				/* To align popup window at the center of screen*/
				top: 50%;
				left: 50%;
				margin-top: -100px;
				margin-left: -150px;
			}
		</style>
		<script type="text/javascript">
			function pop(div) {
				document.getElementById(div).style.display = 'block';
			}
			function hide(div) {
				document.getElementById(div).style.display = 'none';
				document.SubmitForm.submit();
			}
			//To detect escape button
			document.onkeydown = function(evt) {
				evt = evt || window.event;
				if (evt.keyCode == 27) {
					hide('popDiv');
				}
			};
		</script>

</head>
<body <c:if test="${requestScope.pop eq 'Y' }"> onload="pop('popDiv')" </c:if>>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->

		<!-- POP Information Box -->
		
		<div id="popDiv" class="ontop">
			<div class="tabs" id="popup">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="active" style="width:100%;"><a style="height: 60px;width: 100%;background-color: #ff3200;color:#fff" href="#mobile" data-toggle="tab"><img src="images/info.png" height="40" width="40" /> Information</a></li>
			</ul>
			<!-- Tab panes -->
			
				<div class="tab-content" style="height:200px;" >
					<form name="SubmitForm" action="${requestScope.requestParam }" method="post" >
					<div  align="center">
						
							<div class="table-color" align="center">
							<table class="table " >
								<tr>
									<td align="center" >${requestScope.message }</td> 
																						
								</tr>
								<tr>
									<td align="center" ></td> 
																						
								</tr>
								<tr>
									<td align="center" >
										<button type="button" onClick="hide('popDiv')" class="btn btn-primary btn-inline">OK</button>
									</td> 
																						
								</tr>
								</table>
							</div>
						
					</div>
					</form>
				
				</div>
				
			
																
			</div>
		</div>
		
		<!-- POPUP Information Box End -->

		<!-- Main -->
		<div class="main" role="main">

			
			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
							<h1>ADD BENEFICILARY</h1>
						<div class="col-md-12">
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content"style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="box">
                                                         
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active" style="width:100%;"><a style="width:100%;" href="#mobile" data-toggle="tab">
                                                <strong>${requestScope.message }</strong>
                                                </a></li>
                                                
                                                
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="mobile">
                                                    <form action="" name="Beneficiary" method="post" >
                                                    <div class="table-responsive" align="center">
                                                           <table class="table" >
																	    
																<tr>
																	<td align="left" >Transfer Type</td> <td> : </td> 
																	<td style="width: 25%;">
																	<select name="transactionType" class="form-control">
																		<option selected="selected" value="NEFT">NEFT</option>
																		<option  value="IMPS">IMPS</option>
																		</select>
																	</td>
																	<td align="left" >Account Type</td> <td> : </td> 
																	<td >
																		<select name="accountType" class="form-control">
																			<option value="saving" >Saving Account</option>
																			<option value="current">Current Account</option>
																		</select>
																	</td>	
																														
																</tr>
																<tr style="display:none;">
																	<td align="left">City Name</td> <td> : </td>  <td>
																		<input name="searchCity" type="text" class="form-control" style="width: 40%;float:left;" >
																		<button type="submit" class="btn btn-primary btn-inline" style="width: 55%;float:right;" >Get Cities</button>
																	
																		</td>
																</tr>
																<tr style="">
																	<td align="left">IFSC Code</td> <td> : </td>  <td>
																		<input name="bankIfscode" type="text" class="form-control" style="" >
																		<!-- <button type="submit" class="btn btn-primary btn-inline" style="width: 55%;float:right;" >Get Bank Details</button> -->
																	
																		</td>
																</tr>																		
																<tr style="display: none;">
																	<td align="left">Select City</td> <td></td> : <td>
																		<select name="cityName" class="form-control" id="cityName">
																			<option value="-1">.....Select....</option>
																		</select>
																	</td>
																</tr>	
																<tr style="display: none;">
																	<td align="left">Bank Name</td> <td> : </td> <td>
																	<select  name="bankName"class="form-control">
																		<option value="#">.....Select....</option>
																		<option value="#">Punjab National Bank</option>
																		<option value="#">State Bank</option>
																		<option value="#">ICICI bank</option>
																		</select>
																	</td>
																		<td align="left" >Branch Name</td> <td> : </td> <td>
																		<select  name="branch" class="form-control">
																		<option value="#">.....Select....</option>
																		<option value="#">Ganaganagar </option>
																		<option value="#">Govindpuram</option>
																		<option value="#">Rajendar Place</option>
																		</select>
																	</td>													
																</tr>	
																<tr style="display: none;">
																	<td align="left">Bank Address </td> <td> : </td> <td>
																	<input type="text"  name="amount" class="form-control"></td>
																	<td align="left">IFSC Code </td> <td> : </td> <td>
																	<input type="text"  name="ifsc" class="form-control"></td> 
																</tr>
																<tr>
																	<td align="left">Account Number *</td> <td> : </td> <td>
																	<input type="text" required="required" name="bankAccountNumber" class="form-control"></td>
																	<td align="left">Confirm Account Number * </td> <td> : </td> <td>
																	<input type="text" required="required" name="conBankAccountNumber" class="form-control"></td> 
																</tr>
																<tr>
																	<td align="left">Beneficiary name</td> <td> : </td> <td>
																	<input type="Text" required="required" name="name" class="form-control" ></td>	
																	<td align="left">Beneficiary Mobile No * </td> <td> : </td> <td>
																	<input type="text" name="mobile" required="required" class="form-control"></td> 
																</tr>
																
															</table>                                   
                                                           
														</div>
                                                       
														<button type="button" onclick="submitForm('${requestScope.requestParam}')" class="btn btn-primary btn-inline">Add Beneficiary</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="reset" class="btn btn-primary btn-inline">Reset</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" onclick="backFunction('${requestScope.backButton}')" class="btn btn-primary btn-inline">Back</button>
													</form>
													</div>
                                                
												</div>
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