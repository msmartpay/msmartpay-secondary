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
	var amount=document.wbForm.amount.value;
	if(parseInt(amount)<10){
		alert('Amount Should be greater than 500 .');
		document.wbForm.amount.focus();
		return false;
	}else if(parseInt(amount)>5000){
		alert('Amount Should not be greater than 5000 .');
		document.wbForm.amount.focus();
		return false;
	}else{
		document.getElementById('sub_button').style.display="none";
		document.getElementById("sub_button_loading").style.display="";
		document.wbForm.action="walletTopup.action?param=submitAmountRequest";
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

<%
ArrayList<HashMap<String,String>> transactionDetails=(ArrayList<HashMap<String,String>>)request.getAttribute("transactionDetails");
String mobileNo=(String)request.getAttribute("mobileNo");
String mailId=(String)request.getAttribute("mailId");

if(mobileNo==null)
	 mobileNo="";
if(mailId==null)
	 mailId="";
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
			<section class="page-content"  style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
						<div class="col-md-12 form-group"><h3 class="box-title">Instant Balance Request</h3></div>
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                        <div class="box" align="center">                                                                        
                                            
                                            <form name="wbForm" id="wbForm" method="post" > 
                                            	
												${requestScope.message }
												<div class="spacer"></div>     
												<div class="col-md-3 col-md-offset-0 form-group">
                                              		 <input type="text" name="mailId" required="required" placeholder="Enter Email Id." class="form-control" value="${sessionScope.AgentDetailData.emailID }" />
                                               </div>
												
                                                <div class="col-md-2 col-md-offset-0 form-group">
                                              		  <input type="text" name="mobileNo" placeholder="Enter Mobile Number." onkeypress="return digitonly(this,event)" maxlength="10" required="required" class="form-control" value="${sessionScope.AgentDetailData.agentMobileNo }" />
                                               </div>
                                               
                                               <div class="col-md-3 col-md-offset-0 form-group">
                                              		 <input type="text" name="depositerName" placeholder="Enter Depositer Name." required="required" class="form-control" value="${sessionScope.AgentDetailData.agent_name }" />
                                               	<input type="hidden" name="agencyName" value="<%=agencyName%>">
                                               </div>
                                               
                                               <div class="col-md-2 col-md-offset-0 form-group">
                                              		 <input type="text" name="amount" onkeypress="return digitonly(this,event)" maxlength="4" placeholder="Enter Amount" required="required" class="form-control" value="" />
                                               </div>
                                              
                                               <div class="col-md-2 col-md-offset-0 form-group">
												<input required="required" name="remark"  type="text" class="form-control" placeholder="Enter Remarks">
												</div>
                                               
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="button" id="sub_button" onclick="submitRequest()" class="btn btn-success waves-effect waves-light m-r-10">Proceed</button>
                                               		<img id="sub_button_loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                               </div>
                                            </form>
                                    </div>
                                    
                                                                        
                                                                
                                </div>
                        </div>
					</div>
				</div>
                
                <%
                if(transactionDetails!=null && transactionDetails.size()>0){
                
                %>
                <div class="spacer"></div>
                
                <div class="row">
                    <div class="col-md-12">
                          <div class="table-responsive">
                             <table class="table table-striped table-bordered">
            					  <thead>
                                  <tr>
                                      <th width="4%" align="center">S.No.</th>
										<th width="8%" align="center">Date</th>
										<th width="7%" align="center">Amount</th>
										<th width="7%" align="center">Mobile No</th>
										<th width="7%" align="center">Txn Id</th>
										<th width="7%" align="center">Request Status</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  <%for (int i=0;i<transactionDetails.size();i++){
					  				HashMap<String,String> transactionDetailsmapdata=(HashMap<String,String>)transactionDetails.get(i);
		
					  				%>
									<tr>
										<td align="center"><strong><%=i+1%></strong></td>
										<td align="center" valign="middle"><%=transactionDetailsmapdata.get("date")%></td>
										<td align="center"><%=transactionDetailsmapdata.get("amount")%></td>
										<td align="center"><%=transactionDetailsmapdata.get("mobileNo")%></td>
										<td align="center"><%=transactionDetailsmapdata.get("txnId")%></td>
										<td align="left"><%=transactionDetailsmapdata.get("status")%></td>
									</tr>
									<%}%>
                                              
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