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
	function resetFunction(){
		$("#resultLoading").show();
		document.Sender.action="smartGetPage.action?param=getPage";
		document.Sender.submit();
	}
	function submitForm(param){
		
		var senderId=document.Sender.mobile.value;
		if(senderId==''){
			alert('Please enter sender id.');
			document.Sender.mobile.focus();
			return false;
		}else{
			$("#resultLoading").show();
			document.Sender.action="smartGetPage.action?param="+param;
			document.Sender.submit();	
		}
		
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
			#popupWait {
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
		
		<script type="text/javascript">
			function popWait(div) {
				document.getElementById(div).style.display = 'block';
			}
			function hideWait(div) {
				document.getElementById(div).style.display = 'none';
				document.SubmitForm.submit();
			}
			
		</script>

</head>
<body  <c:if test="${requestScope.pop eq 'Y' }"> onload="pop('popDiv')" </c:if>>

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
		
		<div id="popDivWait" class="ontop">
			<div class="tabs" id="popupWait">
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="active" style="width:100%;"><a style="height: 60px;width: 100%;background-color: #ff3200;color:#fff" href="#mobile" data-toggle="tab"><img src="images/info.png" height="40" width="40" /> Information</a></li>
			</ul>
			<!-- Tab panes -->
			
				<div class="tab-content" style="height:200px;" >
					<img alt="Wait" src="images/please_wait.gif">
				
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
							<h1></h1>
						<div class="col-md-12">
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-12" style="margin-bottom: 30px;">
                                        <div class="box">
                                            <div style="font-weight: bold;color: RED;">${requestScope.message}</div>
                                            
                                            <c:if test="${requestScope.customerDetailMap!=null }">
                                            
                                            <div class="tabs" >
											<!-- Nav tabs -->
												<ul class="nav nav-tabs">
													<li class="active" style="width:100%;"><a style="width:100%;" href="#" data-toggle="tab"></a></li>
													
												</ul>
												<!-- Tab panes -->
												<div class="tab-content" style="color:#000;padding:0px 0px;">
													<div style="float:left;padding:0px 0px 10px 10px;">
														<Strong style="margin-left:10px;">Available Balance</Strong> : Rs. ${requestScope.balance } , 
													
														<Strong style="margin-left:20px;">Monthly Remaining Amount</Strong> : Rs. ${sessionScope.monthlyBalance } 
													</div>	
													
													<div style="float:right;padding:0px 10px 10px 0px;">
													<Strong style="margin-right:20px;"><a href="initiateTransaction.action?param=cardtransactionStatus">Transaction Status</a></Strong>
													</div>
											
												</div>
											</div>
											</c:if>
										<div class="spacer">
										</div>
									
                                            
											
										<div class="tabs">
												<!-- Nav tabs -->
												<ul class="nav nav-tabs">
													<li class="active"><a href="#" data-toggle="tab">${requestScope.requestAction}</a></li>
													
												</ul>
												<!-- Tab panes -->
												<div class="tab-content">
													<div class="tab-pane fade in active" id="mobile">
														<form action="smartGetPage.action?param=${requestScope.requestParam}" method="POST" name="Sender" >
															<div class="form-group" id="senderId" >
																<c:if test="${requestScope.customerDetailMap == null}">
																
																	<select name="walletType" class="form-control" style="width:150px;float:left;margin-left:10px" required>
																		<option value="23">Book My Show Wallet</option>
																		<option selected="selected" value="24">Udio Wallet</option>
																	</select>
																													
																	<input type="Text" name="mobile" required="required" class="form-control" style="width:150px;float:left;margin-left:10px" placeholder="Enter Customer Mobile Number">
																</c:if>
																<c:if test="${requestScope.customerDetailMap != null}">
																													
																	<input type="Text" value="${requestScope.customerDetailMap.mobile }" readonly="readonly" name="mobile" required="required" style="width:150px;float:left;margin-left:10px" class="form-control" maxlength="10" >
																
																
																	<input type="Text" value="${requestScope.customerDetailMap.name }" readonly="readonly" name="CardHolderName" class="form-control" style="width:250px;float:left;margin-left:10px" >
															
																	<input type="Text" value="${requestScope.customerDetailMap.email }" name="Email" readonly="readonly" class="form-control" style="width:250px;float:left;margin-left:10px" >
																
																	<input type="Text" value="${requestScope.customerDetailMap.customerStatus }" name="Status" readonly="readonly" style="width:150px;float:left;margin-left:10px" class="form-control" >
																	<input type="Text" value="${requestScope.customerDetailMap.walletName }" name="WalletName" readonly="readonly" style="width:250px;float:left;margin-left:10px" class="form-control" >
																
																	<input type="hidden" name="WalletCode" value="${requestScope.customerDetailMap.walletCode }" />
																	<input type="hidden" name="ProductName" value="${requestScope.customerDetailMap.productName }" />
																	<input type="hidden" name="ProductCode" value="${requestScope.customerDetailMap.productCode }" />
																	
																</c:if>
																<button type="button" onclick="submitForm('${requestScope.requestParam}')" class="btn btn-primary btn-inline" style="width: 150px">${requestScope.requestAction}</button> 
																		
															</div>
																	
														</form>
													</div>
													
												</div>
											</div>
											<div class="spacer"></div>
												
											
											
											
                                            
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