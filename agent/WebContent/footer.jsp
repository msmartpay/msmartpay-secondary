<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="resultLoading" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;display:none;">
	<div
		style="width: 100%; text-align: center; position: absolute; left: 0px; top: 50%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
		<img
			src="images/ajax-loader.gif">
		<div>Please wait...</div>
	</div>
	<div class="bg"
		style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: rgb(0, 0, 0);"></div>
</div>

<section class="page-content" style="padding-top: 80px;background-color: #312d30b0;border-top:2px solid #ccc3;">
	<div class="container" style="width:90%;" style="width: 95%;">	
              <div class="row">
                  <div class="col-md-12">
                      <h2 style="color: #fff;">Welcome to World of ${sessionScope.companyName }</h2>
                      <p style="color: #fff;">Safe and Instant Recharges, Bill Payments Service And Many more...</p>
                      <p style="color: #fff;">A unique E-Solution at single platform.</p>
                      <p style="color: #fff;">We promise to provide you with outstanding service that you can trust for all of your E-Service needs.</p>
                      <a href="#" class="btn btn-primary">AEPS coming Soon</a>
                  </div>
              </div>
	</div>
</section>
<footer class="footer" id="footer">
				
				<div class="footer-copyright">
					<div class="container" style="width:90%;" style="width: 95%;">
						<div class="row" style="width: 30%;float: left;">
							<div class="col-sm-12" >
								Copyright &copy; 2016  <a href="#">${sessionScope.companyName }</a> &nbsp;| &nbsp;All Rights Reserved
							</div>
						</div>
						<c:if test="${sessionScope.clientId!=null && sessionScope.clientId eq '10001' }">
						
							<div class="row" style="width: 45%;float: right;">
							<div class="col-sm-12" >
								<a href="http://partner.smartkinda.com/about.jsp" target="_blank">About Us</a>&nbsp;| &nbsp;<a href="http://partner.smartkinda.com/contact.jsp" target="_blank">Contact Us</a>&nbsp;| &nbsp;<a href="http://partner.smartkinda.com/banks.jsp" target="_blank">Bank Details</a>&nbsp;| &nbsp;<a href="https://smartkinda.com/PrivacyPolicy.jsp" target="_blank">Privacy Policy</a>&nbsp;| &nbsp;<a href="https://smartkinda.com/Term-Condition.jsp" target="_blank">Terms & Conditions</a>
							</div>
						</div>
						
						</c:if>
					</div>
				</div>
</footer>