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
   	
   	function findSender(){
   		$('#resultLoading').show();
   	}
   	function verifyAccountPage(){
   		$('#resultLoading').show();
   		document.getElementById('verifyAccount').href="verifyAccountpage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}";
   	}
   	function addbene(var1){
   		$('#resultLoading').show();
   		document.getElementById('addbene').href=var1;
   	}
   	function deleteBene(var1,id){
		$('#resultLoading').show();
		document.getElementById('delete'+id).href=var1;
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
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 10% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                            <div class="table-responsive">
	                            <div class="col-md-5 form-group">
	                                   <form class="floating-labels " action="findSender.action" onsubmit="return findSender()">
	                                    	<input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
									 	   <div class="col-md-12 form-group">
	                                           <h3 class="box-title">Domestic Money Transfer</h3>
	                                       </div>
	                                       <c:if test="${requestScope.message!=null}">
	                                       <div class="col-md-12 form-group">
	                                          <h5 style="color:red;">${requestScope.message}</h5>
	                                       </div>
	                                       </c:if>
	                                       <div class="col-md-8 form-group">
	                                           <input type="text" class="form-control" name="senderId" id="input1" maxlength="10" required placeholder="Enter Customer Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
	                                           
	                                       </div>
	                                       <div class="col-md-8 form-group" align="right">
	                                       		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
	                                       		<button type="submit" class="btn btn-success waves-effect waves-light m-r-10">Submit</button>
	                                       </div>
	                                   </form>
	                           </div>
	                           	<c:if test="${requestScope.findSenderlist!=null }">
	                            <div class="col-md-7 form-group" style="padding: 3%;border: 1px solid #ccc;">
									<div class="col-md-6 form-group">
										<p ><strong>Sender Id :</strong>&emsp;&emsp;<span style="color: red;">${requestScope.findSenderlist.sdMap.SenderId}</span></p>
										
									</div>
									<div class="col-md-4 form-group">
										<p><strong>Sender Name :</strong>&emsp;&emsp;<span style="color:red;white-space:pre;">${requestScope.findSenderlist.sdMap.Name }</span></p>
									</div>
									<div class="col-md-4 form-group">
										<p><strong>KYC Status :</strong>&emsp;&emsp;<span style="color:red;white-space:pre;">${requestScope.findSenderlist.slimitMap.KycStatus }</span></p>
									</div>
									<div class="col-md-4 form-group">
										<p ><strong>Sender Limit :</strong>&emsp;&emsp;<span style="color: red;">${requestScope.findSenderlist.slimitMap.SenderLimit}</span></p>
									</div>
									<div class="col-md-4 form-group">
										<p ><strong>Used Limit :</strong>&emsp;&emsp;<span style="color: red;">${requestScope.findSenderlist.slimitMap.UsedLimit}</span></p>
									</div>
									<div class="col-md-4 form-group">
										<p ><strong>Available Limit :</strong>&emsp;&emsp;<span style="color: red;">${requestScope.findSenderlist.slimitMap.AvailableLimit}</span></p>
									</div>
									<div class="col-md-12 form-group">
										<div class="col-md-4 form-group">
		                            		<a id="addbene" onclick="addbene('addbeneficiarypage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}')" href="#">(+)New Beneficiary</a>
		                            	</div>
		                            	<div class="col-md-4 form-group">
											<a onclick="verifyAccountPage()" id="verifyAccount" href="#"><i	class="fa fa-check-square-o" aria-hidden="true"></i>Account
												Verification</a>
										</div>
		                            	<!-- <div class="col-md-4 form-group">
		                            		<a  href="dmrTranStatusPage.action" id="refund">Refund Transaction</a>
		                            	</div> -->
		                            </div>
								</div>
								
	                            </c:if>
	                            
	                            
	                            
	                            </div>
               
               
            		</div>
                
                <c:choose>
               <c:when test="${requestScope.findSenderlist!=null }">  
                <div class="row" style="margin-top: 1%;">
                    
                    <div class="col-md-12">
                        
						<div class="table-responsive">
							<div class="col-md-4 form-group">
							
                            <h3 class="box-title">BENEFICIARY INFO </h3>
							</div>
							<!-- <div class="col-md-8 form-group">
								<a  href="regSenderpage.action" style="float: right;padding-right:20px;" class="">(+)New Sender Registration</a>
									<a id="MainContent_lkKYCLink"
									class="Kyc divlbl"
									href="javascript:__doPostBack(&#39;ctl00$MainContent$lkKYCLink&#39;,&#39;&#39;)"
									style="float: right; padding-right:20px;  text-decoration: underline;">Click
									Here To Update Sender <span style="color: red;">KYC</span></a>
							</div> -->

                           <c:choose><c:when test="${requestScope.findSenderlist.BeneList!=null && requestScope.findSenderlist.BeneList.size()>0}">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                       
                                            <th>Ben.Name</th>
                                            <th>Ben.Mobile</th>
                                            <th>Bank Name</th>
                                            <th>Account No.</th>
                                            <th>IFSC Code</th>
                                            <th>IMPS</th>
                                            <th>NEFT</th>
                                            <th>DeLete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="BeneList" items="${requestScope.findSenderlist.BeneList}" varStatus="loop">
                                        <tr>
                                            <td>${BeneList.BeneName}</td>
                                            <td>${BeneList.BeneMobile}</td>
                                            <td>${BeneList.BankName }</td>
                                             <td>${BeneList.Account }</td>
                                            <td>${BeneList.Ifsc }</td>
                                            <c:if test="${BeneList.Channel eq '0'}">
                                            	<td><a href="InitiateTransactionpage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}&BeneName=${BeneList.BeneName}&SenderName=${requestScope.findSenderlist.sdMap.Name}&BeneMobile=${BeneList.BeneMobile}&IFSC=${BeneList.Ifsc }&BeneAccount=${BeneList.Account }&RecipientId=${BeneList.RecipientId }&TxnType=${BeneList.IMPS }&BankName=${BeneList.BankName }">Pay</a></td>
                                            	<td><a href="InitiateTransactionpage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}&BeneName=${BeneList.BeneName}&SenderName=${requestScope.findSenderlist.sdMap.Name}&BeneMobile=${BeneList.BeneMobile}&IFSC=${BeneList.Ifsc }&BeneAccount=${BeneList.Account }&RecipientId=${BeneList.RecipientId }&TxnType=${BeneList.NEFT }&BankName=${BeneList.BankName }">Pay</a></td>
                                            </c:if>
                                            <c:if test="${BeneList.Channel eq '1'}">
                                            	<td>--</td>
                                            	<td><a href="InitiateTransactionpage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}&BeneName=${BeneList.BeneName}&SenderName=${requestScope.findSenderlist.sdMap.Name}&BeneMobile=${BeneList.BeneMobile}&IFSC=${BeneList.Ifsc }&BeneAccount=${BeneList.Account }&RecipientId=${BeneList.RecipientId }&TxnType=${BeneList.NEFT }&BankName=${BeneList.BankName }">Pay</a></td>
                                            </c:if>
                                            <c:if test="${BeneList.Channel eq '2'}">
                                            	<td><a href="InitiateTransactionpage.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}&BeneName=${BeneList.BeneName}&SenderName=${requestScope.findSenderlist.sdMap.Name}&BeneMobile=${BeneList.BeneMobile}&IFSC=${BeneList.Ifsc }&BeneAccount=${BeneList.Account }&RecipientId=${BeneList.RecipientId }&TxnType=${BeneList.IMPS }&BankName=${BeneList.BankName }">Pay</a></td>
                                            	<td>--</td>
                                            </c:if>
                                            <td><a id="delete${loop.index}" onclick="deleteBene('deleteBeneficiary.action?SenderId=${requestScope.findSenderlist.sdMap.SenderId}&BeneId=${BeneList.RecipientId }','${loop.index}')" href="#">Delete</a></td>
                                        </tr>
                                        </c:forEach>
                                       
                                        <tr>
                                        
                                    </tbody>
                                </table>
                                </c:when><c:otherwise>
                                 <h3 style="color:red; text-align: center;">Beneficiary not found</h3>
                                
                                </c:otherwise></c:choose>
                         </div>      

                    </div>
                </div>
               </c:when>
               <c:otherwise>
                
                </c:otherwise>
                </c:choose>
                
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