<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	
	<%@ include file="../../wldetails.jsp" %>
	<%
	
	
	ArrayList listData=(ArrayList)request.getAttribute("AccountStmtData");
	int size=0;
	if(listData !=null){
	size=listData.size();
	}else{
	size=0;
	}
	String service="";
	String showService="";
	String actionOnAmount="";
	String deductedAmount="";
	String creditAmount="";
	String status="";
%>
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
	function forpopup(transid,showService,AgenttranNo)
	{
		if(showService=='R-DMR' || showService=='R-DMR-AccountVerification')
			window.open("doAccountStatement.action?param=RDmrAccountStatementDetails&tranNo="+transid+"&AgenttranNo="+AgenttranNo,'popup','width=700,height=500,left=400,top=100,screenX=300,screenY=100,resizable=no,scrollbars=no');
		else
			window.open("doAccountStatement.action?param=AccountStmtInfo&tranNo="+transid+"&service="+showService+"&AgenttranNo="+AgenttranNo,'popup','width=700,height=500,left=400,top=100,screenX=300,screenY=100,resizable=no,scrollbars=no');

	}
	
	</script>
	<script type="text/javascript">
	
    $(function() {
		
		var now = new Date();
	    var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		
		var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
    	
		var ytoday = now.getFullYear() + '-' + mm + '-' + dd;
		var today = now.getFullYear()  + '-' + mm + '-' + dd;
		
		$('#from').val(ytoday);
		$('#to').val(today);
		
	    $( "#from, #to" ).datepicker({
	        defaultDate: "+1w",
	        maxDate: "0D",
	        dateFormat: 'yy-mm-dd',
	        numberOfMonths: 1,
	        maxDate:'today',
	        
	    });

	});
	
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
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
						<div class="col-md-12 form-group"><h3 class="box-title">Account Statement</h3></div>
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                
                        
                        		<div class="box" >
                                    
                                    
                                    <div class="col-md-12 " align="center">
                                    <form name="AccountStmtForm" id="AccountStmtForm" method="post" action="doAccountStatement.action?param=getAccountStmt"> 
                                            
                                          	<div class="form-group col-md-12" style="margin-bottom: 20px;" align="center">
											<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
											</div> 
											
												<table style="width: 80%">
			                                    	<tr>
			                                    	
			                                    		<td width="20%"></td>
			                                    		<td width="20%" align="right">
			                                    			<input required="required" name="fromdate" style="width: 75%" readonly="readonly" type="text" id="from" class="form-control" placeholder="Select From Date">
	                                              
			                                    		</td>
			                                    		<td width="20%" align="center">:</td>
			                                    		<td width="20%" align="left">
			                                    			<input required="required" name="todate" type="text" id="to" readonly="readonly" class="form-control" placeholder="Select To Date">
			                                    		
			                                    		</td>
			                                    		<td width="20%"></td>
			                                    	
			                                    	</tr>
			                                    
			                                    </table>
											
                                          
                                    			<div class="col-md-12" align="center" style="margin-top: 30px;">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                	
                                                	<button type="submit" id="sub_button" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-success waves-effect waves-light m-r-10">View Statement</button>
                                         		</div>
                                    </form>
                                    </div>
                                    
                                    
                                                                        
                                                                
                                </div>
                        
								</div>
	                        </div>
		               
	                        
	                    </div>
	               		
	               
	            	</div>
                
                <div class="spacer"></div>
                
                <%if(size>0){ %>
                <div class="row">
                    <div class="col-md-12">
                                        <div class="table-responsive">
                                           <table class="table table-striped table-bordered" style="background: #dfdfdf;">
							                   <thead>
                                                <tr>
                                                    <th> S. No.</th>
                                                    <th> DateTime </th>
                                                    <th> Txn Id </th>
                                                    <th> Particulars </th>
                                                    <th >Mobile No.</th>
                                                    <th >Operator</th>
                                                    <th> Txn. Amount </th>                                                
                                                    <th> Action</th>
                                                    <th> Comm.</th>
                                                    <th> Fee</th>
                                                    <th> GST</th>
                                                    <th> Net Amt</th>
                                                    
                                                    <th> Balance </th>
                                                    <th> Status </th> 
                                                    <th>Ticket</th>                                               
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <% for(int i=0;i<size;i++){
												  HashMap<String,String> map=(HashMap)listData.get(i);
												  //System.out.println(map+"\n");
												  %>
                                                <tr>
                                                    <td><strong><%=i+1%> </strong></td>
                                                    <td><%=map.get("dot")%> <br/><%=map.get("tot")%></td>
                                                    <td><%=map.get("AgenttranNo")%></td>
                                                    <% 
                                                    String statusVal=map.get("status");
                                                    if(statusVal==null)statusVal="";
                                                    else if(statusVal.equalsIgnoreCase("Pending"))
                                                    	statusVal="In Process";
                                                    
                                                    service=map.get("service");
													System.out.println("service is : ----------------"+service);
													if(service==null){
														service="NA";
													}else if(service.equalsIgnoreCase("liveMobRech")){
														showService="Recharge-MOB";
													}else if(service.equalsIgnoreCase("liveDTHRech")){
														showService="Recharge-DTH";
													}else if(service.equalsIgnoreCase("liveDCRech")){
														showService="Recharge-Data Card";
													}else if(service.equalsIgnoreCase("RliveDCRech")){
														showService="Refund-Data Card";
													}else if(service.equalsIgnoreCase("RliveDTHRech")){
														showService="Refund-DTH";
													}else if(service.equalsIgnoreCase("RliveMobRech")){
														showService="Refund-MOB";
													}else if(service.equalsIgnoreCase("smsBal") || service.equalsIgnoreCase("smsChmpin") || service.equalsIgnoreCase("smsGetmpin") || service.equalsIgnoreCase("smsInvalidKeyword") || service.equalsIgnoreCase("SMSRechActivation") || service.equalsIgnoreCase("smsTxn") || service.equalsIgnoreCase("smsUpdate")){
														showService="SMS Charge";
													}else if(service.equalsIgnoreCase("accountadjustment") || service.equalsIgnoreCase("Account Adjustment-Agent") || service.equalsIgnoreCase("Account Adjustment")){
														showService="Account Adjustment";
													}else if(service.equalsIgnoreCase("DisttoAgent")){
														showService="DS - Agent";
													}else if(service.equalsIgnoreCase("OffLineMobRech")){
														showService="Recharge-MOB-Ops";
													}else if(service.equalsIgnoreCase("ROffLineMobRech")){
														showService="Refund-MOB-Ops";
													}else if(service.equalsIgnoreCase("liveBillpay")){
														showService="Bill-Pay";
													}else if(service.equalsIgnoreCase("RliveBillpay")){
														showService="Refund-Bill-Pay";
													}
													else{
														showService=service;
													}
													
													String AgenttranNo =map.get("AgenttranNo");
													if(AgenttranNo.equalsIgnoreCase("NA")){%>
                                                    <td> <%=showService%></td>
                                                    <%}else{ %>
                                                    <td> <a href="javascript:forpopup('<%=map.get("AgenttranID")%>','<%=showService%>','<%=map.get("AgenttranNo")%>')" style="color:#0000FF"><%=showService%></a>  </td>
                                                    
                                                    <%} %>
                                                    <td ><%=map.get("mobileNo")%></td>
                                                    <td ><%=map.get("operator")%></td>
                                                    <td> <%=map.get("reqAmount")%></td>
                                                    <td> <%=map.get("action") %></td>
                                                    
                                                    <td ><%=map.get("comm")%></td>
                                                    <td> <%=map.get("service_charge")%></td>
                                                    <td> <%=map.get("Tax")%></td>
                                                    <td> <%=map.get("deductedAmount") %></td>
                                                    
                                                    <td> <%=map.get("agentFbal")%></td>
                                                    <td>
                                                    
                                                     <%=statusVal%> </td>
                                                    <td>
                                                    <%if(!AgenttranNo.equalsIgnoreCase("NA") && "Debit".equalsIgnoreCase(map.get("action")) ) {%>
                                                    <a href="ticket.action?param=ticketPage&txnId=<%=AgenttranNo %>" >Generate Ticket</a>
                                                    <%}else{ %>
                                                	
                                                		NA	
                                                	<%} %>
                                                	</td>
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