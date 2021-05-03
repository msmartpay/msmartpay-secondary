<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">
<head>
<%@ include file="/globaldata.jsp"%>
<%
ArrayList<HashMap<String,String>> depDetails=(ArrayList<HashMap<String,String>>)request.getAttribute("depDetails");


String message=(String)request.getAttribute("message");
if(message==null)message="";
%>
<script type="text/javascript">
function noBack() 
{ 
	window.history.forward(); 
}

</script>
<script type="text/javascript">

	function submitRequest() {
		var date=document.wbForm.date.value;
		var paymentMode=document.wbForm.paymode.value;
		var bankName=document.wbForm.bank.value;
		var amount=document.wbForm.amount.value;
		var referenceId=document.wbForm.referenceId.value;
		if(date==''){
			alert('Please Select Date .');
			document.wbForm.date.focus();
			return false;
		}else if(paymentMode=='-1'){
			alert('Please Select Payment Mode.');
			document.wbForm.paymode.focus();
			return false;
		}else if(bankName=='-1'){
			alert('Please Select Bank Name.');
			document.wbForm.bank.focus();
			return false;
		}else if(amount==''){
			alert('Please Enter Amount.');
			document.wbForm.amount.focus();
			return false;
		}else if(parseInt(amount)<1){
			alert('Amount Should be greater than 500 .');
			document.wbForm.amount.focus();
			return false;
		}else{
			document.getElementById('sub_button').style.display="none";
			document.getElementById("sub_button_loading").style.display="";
			document.wbForm.action="distributorDepositRequest.action?param=saveDeposit";
			document.wbForm.submit();
		}
	}
</script>
	<script type="text/javascript">
	
	function digitonly(input,evt)
	{
		var keyCode = evt.which ? evt.which : evt.keyCode;
				var lisShiftkeypressed = evt.shiftKey;
				if(lisShiftkeypressed && parseInt(keyCode) != 9) {
				return false ;
				}
				if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
				return true;
				}

				return false;

	}
	
	</script>

<!-- Basic Page Needs
	================================================== -->
<meta charset="utf-8">
<title><%=companyName %></title>

<!-- Mobile Specific Metas
	================================================== -->
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">



<!-- CSS
	================================================== -->
<!-- Base + Vendors CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="css/fonts/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="css/fonts/entypo/css/entypo.css">
<link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css"
	media="screen">
<link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css"
	media="screen">
<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css"
	media="screen">

<!-- Theme CSS-->
<link rel="stylesheet" href="css/theme.css">
<link rel="stylesheet" href="css/theme-elements.css">
<link rel="stylesheet" href="css/animate.min.css">



<!-- Head Libs -->
<script src="vendor/modernizr.js"></script>


<!-- Favicons
	================================================== -->
<link rel="shortcut icon" href="images/favicons/favicon.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="images/favicons/favicon-120.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="images/favicons/favicon-152.png">
	
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 	<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		  
	    var now = new Date();
		var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	 	
		$("#datepicker").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2016:2020',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		  
	});
	
	</script>

</head>
<body>
	<%@ include file="/header.jsp"%>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Bank Details</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->
			<!-- Page Content -->
			<section class="page-content">
				<div class="container">

					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg1.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
							
								<c:if test="${requestScope.bankList!=null && requestScope.bankList.size()>0 }">
								<div class="row">
				                    <div class="col-md-12">
				                          <div class="table-responsive">
				                             <table class="table table-striped table-bordered">
				            					  <thead>
				                                  <tr>
				                                      <th>S.No</th>
				                                      <th>Bank Name </th>
				                                      <th>Bank Account Holder Name </th>
													  <th>Bank Account</th>                                      
				                                      <th>Bank IFSC</th>
				                                  </tr>
				                                  </thead>
				                                  <tbody>
				                                  <c:forEach var="bank" items="${requestScope.bankList }" varStatus="loop">
				                                  	<tr>
				                                      <td><strong>${loop.index+1}</strong></td>
				                                      <td>${bank.bank_name }</td>
				                                      <td >${bank.bank_account_name }</td>
				                                      <td>${bank.bank_account }</td>
				                                      <td >${bank.bnk_ifsc }</td>
				                                  	</tr>
				                                  </c:forEach>
				                                  </tbody>
				                             </table>
				                          </div>
				                      </div>
				                </div>
								</c:if>
                                <div class="row">
                                
                                    <div class="spacer"></div>
                                
                                    <div class="col-md-12 col-md-offset-0" align="center">
                                        <div class="box" align="center">                                                                        
                                            
                                            <form name="wbForm" id="wbForm" method="post" > 
                                            	
												<div  align="center" style="background-color: #f2f2f2;height: 44px;padding: 1% 1%;">
													
													<label style="font-weight: bold;color: #c00;" > <%if(message!=null && message.length()>0 ){ %><%=message %> <%}else{ %>Submit Balance Request<%} %> </label>
												</div>
												<div class="spacer"></div>     
												<div class="col-md-3 col-md-offset-0 form-group">
                                              		 <select class="form-control" name="bank" >
                                             			<option value="-1" selected="selected">Select Bank Name</option>
                                             			<c:forEach var="bank" items="${requestScope.bankList }">
                                             				<option value="${bank.bank_name }">${bank.bank_name }</option>
                                             			</c:forEach>
                                     				</select>
                                               </div>
												
                                                <div class="col-md-2 col-md-offset-0 form-group">
                                              		 <select class="form-control" name="paymode">
                                             			<option value="-1" selected="selected">Select Payment Mode</option>
                                      		 			<option value="NEFT">NEFT</option>
                                      		 			<option value="IMPS">IMPS</option>
                                      		 			<option value="RTGS">RTGS</option>
                                      		 			<option value="CASH">CASH</option>
                                      		 			<option value="CHEQUE/DD">CHEQUE/DD</option>
                                     				</select>
                                               </div>
                                               
                                               <div class="col-md-2 col-md-offset-0 form-group">
                                              		 <input type="text" name="amount" onkeypress="return digitonly(this,event)" maxlength="10" placeholder="Enter Amount" class="form-control" value="" />
                                               </div>
                                               <div class="col-md-3 col-md-offset-0 form-group">
                                              		 <input type="text" name="referenceId"  placeholder="Enter Reference Id/DD/Cheque No." class="form-control" value="" />
                                               </div>
                                               <div class="col-md-2 col-md-offset-0 form-group">
												<input required="required" name="date"  type="text" id="datepicker" class="form-control" placeholder="Select Date">
												</div>
                                               
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="button" id="sub_button" onclick="submitRequest()"  class="btn btn-primary btn-inline">Submit Request</button>
                                               		<img id="sub_button_loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                               </div>
                                            </form>
                                    </div>
                                    </div>
                                    
                                                                        
                                                                
                                </div>
                        </div>
					</div>
				</div>

				<div class="spacer"></div>

				<%
                if(depDetails!=null && depDetails.size()>0){
                
                %>
                <div class="spacer"></div>
                
                <div class="row">
                    <div class="col-md-12">
                          <div class="table-responsive">
                             <table class="table table-striped table-bordered">
            					  <thead>
                                  <tr>
                                      <th> S.No</th>
                                      <th> Payment Mode </th>
                                      <th>Amount </th>
									  <th>Request Date</th>                                      
                                      <th>Cheque/DD/TransNo/Cash</th>
                                      <th>ApprovedDate</th>
                                      <th>Remarks</th>
                                      <th>Status</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  <%
                                  for(int i=0;i<depDetails.size();i++){
                                	  HashMap<String,String> map=depDetails.get(i);
                                  %>
                                  <tr>
                                      <td><strong><%=i+1 %></strong></td>
                                      <td><%=map.get("mod") %></td>
                                      <td><%=map.get("amount") %></td>
                                      <td><%=map.get("reqDate") %></td>
                                      <td ><%=map.get("refId") %></td>
                                      <td><%=map.get("appDate") %></td>
                                      <td ><%=map.get("remark") %></td>
                                      <td><%=map.get("status") %></td>
                                      
                                  </tr>
                                  <%} %>
                                              
                                  </tbody>
                             </table>
                          </div>
                      </div>
                </div>

				<%} %>

				</div>
			</section>
			<!-- Page Content / End -->

			<div class="spacer-xl"></div>

		</div>
		<!-- Main / End -->
	</div>

	<%@ include file="/footer.jsp"%>


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