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
			<!-- Footer -->
			<footer class="footer" id="footer">
				
				<div class="footer-copyright">
					<div class="container">
						<div class="row" style="width: 35%;float: left;">
							<div class="col-sm-12">
								Copyright &copy; 2016  <a href="#"><%=companyName %></a> &nbsp;| &nbsp;All Rights Reserved

							</div>
						</div>
						<%if(session.getAttribute("Client_Id")!=null && session.getAttribute("Client_Id").equals("10001")) {%>
						<div class="row" style="width: 60%;float: right;">
							<div class="col-sm-12" >
								<a href="http://smartkinda.com/about.html" target="_blank">About Us</a>&nbsp;| &nbsp;<a href="http://smartkinda.com/contact.jsp" target="_blank">Contact Us</a>&nbsp;| &nbsp;<a href="http://smartkinda.com/bank_detail.html" target="_blank">Bank Details</a>&nbsp;| &nbsp;<a href="http://smartkinda.com/Terms-Conditions.html" target="_blank">Privacy Policy</a>&nbsp;| &nbsp;<a href="http://smartkinda.com/Terms-Conditions.html" target="_blank">Terms & Conditions</a>
							</div>
						</div>
						<%} %>
					</div>
				</div>
			</footer>
			<!-- Footer / End -->