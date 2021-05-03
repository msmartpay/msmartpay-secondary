<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
	<%@ include file="../../wldetails.jsp" %>
	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><%=companyName %></title>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
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
		document.wbForm.action="w2w.action?param=WBBalanceRequest";
		document.wbForm.submit();
	}
}
</script>
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
<%
ArrayList<HashMap<String,String>> depDetails=(ArrayList<HashMap<String,String>>)request.getAttribute("depDetails");

%>


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
				<div class="container" style="width:100%;">				
					
					<c:if test="${requestScope.bankList!=null && requestScope.bankList.size()>0 }">
					<div class="row">
					<div class="col-md-12 form-group"><h3 class="box-title">Submit Balance Request</h3></div>
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
						<div class="col-md-12 form-group"><h3 class="box-title">Submit Balance Request</h3></div>
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                        <div class="box" align="center">                                                                        
                                            
                                            <form name="wbForm" id="wbForm" method="post" > 
                                            	
												<span style="color: RED;font-size: 16px;">${requestScope.message }</span>
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
                                      		 			<!-- <option value="CHEQUE/DD">CHEQUE/DD</option> -->
                                     				</select>
                                               </div>
                                               
                                               <div class="col-md-2 col-md-offset-0 form-group">
                                              		 <input type="text" name="amount" placeholder="Enter Amount" autocomplete="off" class="form-control" value="" />
                                               </div>
                                               <div class="col-md-3 col-md-offset-0 form-group">
                                              		 <input type="text" name="referenceId" autocomplete="off" placeholder="Enter Reference Id/DD/Cheque No." class="form-control" value="" />
                                               </div>
                                               <div class="col-md-2 col-md-offset-0 form-group">
												<input required="required" name="date" id="datepicker" readonly="readonly" autocomplete="off" type="text" class="form-control" placeholder="Select Date">
												</div>
                                               
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                    <button type="button" id="sub_button" onclick="submitRequest()"  class="btn btn-success waves-effect waves-light m-r-10">Submit Request</button>
                                               		<img id="sub_button_loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                               </div>
                                            </form>
                                    </div>
                                    
                                                                        
                                                                
                                </div>
                        </div>
					</div>
				</div>
                
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