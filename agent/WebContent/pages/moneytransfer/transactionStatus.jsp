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
		document.ConfirmForm.action=param;
		document.ConfirmForm.submit();
	}
	function submitForm(param){
		var tranRefId=document.ConfirmForm.tranRefId.value;
		if(tranRefId==''){
			
			alert('Please Enter Service Delivery TID.');
			document.ConfirmForm.tranRefId.focus();
			return false;
		}else{
			$("#resultLoading").show();
			document.ConfirmForm.action=param;
			document.ConfirmForm.submit();
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
	<style>
	.form-group {
		margin-bottom: 8px;
	}
	</style>
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
				<li class="active" style="width:100%;"><a style="height: 60px;width: 100%;background-color: #ff3200;color:#fff" href="#mobile" data-toggle="tab"><img src="images/info.png" height="40" width="40" />Information</a></li>
				
				
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
		
		<!-- Main  -->
		
		<div class="main" role="main">

			
			<!-- Page Heading -->
			<%-- <section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
							<h1>${requestScope.requestAction }</h1>
						<div class="col-md-12">
						</div>
					</div>
				</div>
			</section> --%>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content"style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div  style="background: none;border:none;" >
                                        <div align="center" style="width: 100%;height: 380px;">                 
                                            <div class="tabs" style="width: 50%;">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active" style="width:100%;"><a style="width:100%;" href="#mobile" data-toggle="tab">
                                                ${requestScope.requestAction }
                                                </a></li>
                                                
                                                
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="mobile">
                                                    <form action="" name="ConfirmForm" method="post" >
                                                       
                                                       <div class="table-responsive">
                                                   			<div class="table-color">
																<table class="table">
																    
																	
																	<tr>
																		<td style="200px;">
																		<div class="form-group col-md-12"  >
																			<label style="width: 150px;">Enter Service Delivery TID</label>
												   						</div>
												   						</td> 
												   						<td> : </td> 
												   						<td>
																		<div class="form-group col-md-12" >
				                                                            <input style="width: 150px;" type="text" name=tranRefId class="form-control" placeholder="Enter Service Delivery TID">
					                                                     	<input type="hidden" name="SenderId" value="${requestScope.SenderId }" />
					                                                     </div>
																		</td>	
																	</tr>
																	
																				
																</table>                                    
                                                        	</div>
														</div>
                                                       
                                                        
                                                        <div class="form-group col-md-6" align="right">
															<button style="width:150px;" onclick="submitForm('${requestScope.requestParam}')" type="button" class="btn btn-primary btn-inline">
															Search
															</button>
													   </div>
													   
                                                       <div class="form-group col-md-4">
                                                       		
                                                            <button style="width:150px;" onclick="backFunction('${requestScope.backButton}')" type="submit" class="btn btn-primary btn-inline">
                                                            Back
                                                            </button>
                                                        	
                                                        </div>
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