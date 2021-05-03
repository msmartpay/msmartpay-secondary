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
	
	
	<style>
	.ontopheader {
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
	#popupheader {
		
	    width: 840px;
	    height: 200px;
	    position: absolute;
	    color: #000000;
	    background-color: #ffffff;
	    top: 25%;
	    left: 50%;
	    margin-top: -145px;
	    margin-left: -425px;

	}
	.table>tbody>tr>td>p{
	text-align: justify;
	}
	
	.data_table>tr>td{
	padding: 2%;
	}
	
</style>
		<script type="text/javascript">
			function pop(div) {
				document.getElementById(div).style.display = 'block';
			}
			function hide(div) {
				if(document.SubmitForm.contractStatus.checked==true){
					document.getElementById(div).style.display = 'none';
					document.SubmitForm.submit();
				}else{
					alert('Please accept the BC E-Contract.');
					document.SubmitForm.contractStatus.focus();
					return false;
				}
				
			}function hide2(div) {
				document.getElementById(div).style.display = 'none';
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
<body <c:if test="${requestScope.signContract eq 'Y' }"> onload="pop('popDivheader')" </c:if>>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">


			<!-- Slider -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
				
					<div class="row" align="center">
						<div class="col-md-12 form-group"><h3 class="box-title">Services At <%=companyName %></h3></div>
	                            
	                    <div class="col-md-12">
	                            
	                            <div class="table-responsive">
                                     		
	                                     <div class="col-md-12">
	                                     
	                                     	<div class="col-md-3">
	                                     		<a href="domobileDthRecharge.action?param=getPage"><img src="images/dashboard/Recharge.png" height="200" width="300" /><br>Prepaid Recharge</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="utilityService.action?param=UtilityPage"><img src="images/dashboard/Utility.png" height="200" width="300" /><br>Utility Payments</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<c:if test="${sessionScope.AgentDetailData.DMR eq 'Y' }">
														<c:if test="${sessionScope.DMRVendor eq 'DMR1' }">
															<a href="dmr.action?param=Money Trasfer"><img src="images/dashboard/Money.png" height="200" width="300" /><br>Domestic Money Transfer</a>
														</c:if>
														<c:if test="${sessionScope.DMRVendor eq 'DMR2' }">
															<a href="dmreko.action?param=Money Trasfer"><img src="images/dashboard/Money.png" height="200" width="300" /><br>Domestic Money Transfer</a>
														</c:if>
														
												</c:if>
												<c:if test="${sessionScope.AgentDetailData.DMR eq 'N' }">
														<a href="#"><img src="images/dashboard/Money.png" height="200" width="300" /><br>Domestic Money Transfer</a>
															
												</c:if>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="dthConnection.action?param=DthConnection"><img src="images/dashboard/airteldthconn.PNG" height="200" width="300" /><br>Airtel DTH Connection</a>
	                                     	</div>
	                                     
	                                     </div>
	                                     
	                                     <div class="col-md-12">
	                                     
	                                     	<div class="col-md-3">
	                                     		<a href="javascript:alert('To use this service contact to channel partner.');"><img src="images/dashboard/flight.png" height="200" width="300" /><br />Flight Booking</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="javascript:alert('To use this service contact to channel partner.');"><img src="images/dashboard/Hotel.png" height="200" width="300" /><br>Hotel Booking</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="javascript:alert('To use this service contact to channel partner.');"><img src="images/dashboard/Pan.png" height="200" width="300" /><br />PAN Application</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="busSearch.action?param=BusPage"><img src="images/dashboard/bus.png" height="200" width="300" /><br>Bus Booking</a>
	                                     	</div>
	                                     
	                                     </div>
	                                     
	                                     <div class="col-md-12">
	                                     
	                                     	<div class="col-md-3">
	                                     		<a href="javascript:alert('To use this service contact to channel partner.');"><img src="images/dashboard/Travel.png" height="200" width="300" /><br>Holiday Packages</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="https://ssup.uidai.gov.in/web/guest/ssup-home" target="_blank"><img src="images/dashboard/aadhaar.png" height="200" width="300" /><br>Aadhaar Updation</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="ITRServices.action?param=ITRService"><img src="images/dashboard/itr.png" height="200" width="300" /><br>ITR Filing</a>
	                                     	</div>
	                                     	<div class="col-md-3">
	                                     		<a href="GSTService.action?param=GSTService"><img src="images/dashboard/gst.png" height="200" width="300" /><br>GST Services</a>
	                                     	</div>
	                                     
	                                     </div>
                                
                                </div>
                        </div>
                    </div>                    
				
				</div>
			
			</section>
			
			
			<!-- Slider / End -->

			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
			<!-- Footer / End -->
			
		</div>
		<!-- Main / End -->
	</div>
	<%if(bannerStatus.equalsIgnoreCase("Y")){ %>
	<!-- <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="welcomemessage" id="welcomemodal">
		  <div class="modal-dialog" style="width:65%" >
		    <div class="modal-content pA10" >  
		      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		      
		      <div class="welcome_msg" >
					<p align="center"><img src="http://smartkinda.com/images/Banner.png" height="450" width="100%" border="0"></p>
		      
		      </div>
		      
		      
		      
		    </div>
		  </div>
		</div> -->
	
	<%} %>
	
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
	<script src="vendor/flexslider/jquery.flexslider-min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>

	<script>
		jQuery(function($){
			$('body').addClass('loading');
		});
		
		$(window).load(function(){
			$('.flexslider').flexslider({
				animation: "fade",
				controlNav: true,
				directionNav: false,
				prevText: "",
				nextText: "",
				start: function(slider){
					$('body').removeClass('loading');
				}
			});
		});
	</script>
	<script type="text/javascript">
    $(window).load(function(){
        $('#welcomemodal').modal('show');
    });
</script>
	
</body>
</html>