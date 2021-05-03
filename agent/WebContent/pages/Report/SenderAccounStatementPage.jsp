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
		window.open("doAccountStatement.action?param=AccountStmtInfo&tranNo="+transid+"&service="+showService+"&AgenttranNo="+AgenttranNo,'popup','width=700,height=500,left=400,top=100,screenX=300,screenY=100,resizable=no,scrollbars=no');
	//document.MyTransaction.submit();

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
                                  <form name="AccountStmtForm" id="AccountStmtForm" method="post" action="doAccountStatement.action?param=SenderAccountStatement"> 
                                   	<input type="hidden" class="form-control" name="Service" id="input1" value="mobile">
							 	   <div class="col-md-12 form-group">
                                          <h3 class="box-title">Sender History</h3>
                                      </div>
                                      <c:if test="${requestScope.message!=null}">
                                      <div class="col-md-12 form-group">
                                         <h5 style="color:red;">${requestScope.message}</h5>
                                      </div>
                                      </c:if>
                                      <div class="col-md-7 form-group">
                                          <input type="text" class="form-control" name="senderId" id="senderId" maxlength="10" required placeholder="Enter Sender Mobile" onkeypress="return digitonly(this,event)"><span class="highlight"></span> <span class="bar"></span>
                                          
                                      </div>
                                      <div class="col-md-7 form-group" align="right">
                                      		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                      		<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-success waves-effect waves-light m-r-10">Submit</button>
                                      </div>
                                  </form>
                                 </div>
						</div>
					</div>
					
					
					<%-- <div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-2" align="center">
                                        <div class="box" align="center" style="    height: 220px;">                                                                        
                                            <form name="AccountStmtForm" id="AccountStmtForm" method="post" action="doAccountStatement.action?param=SenderAccountStatement"> 
                                            	<div  align="center">
													<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
												</div>     <br>                                                 
                                                
                                                <div class="form-group col-md-6"  align="center">
                                                    <input required="required" name="senderId" style="width: 75%" type="text"  class="form-control" placeholder="Enter Customer Mobile Number">
                                                </div>
                                               <div class="form-group col-md-4">                                                      
                                                	<button type="submit" id="sub_button" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-primary btn-inline">View Statement</button>
                                               </div>
                                            </form>
                                    </div>
                                    </div>                                    
                                                                
                                </div>
                        </div>
					</div>
				</div> --%>
                
                <div class="spacer"></div>
                
                <%if(size>0){ %>
                <div class="row">
                    <div class="col-md-12">
                                        <div class="table-responsive" style="overflow-y:scroll;height: 600px; ">
                                           <table class="table table-striped table-bordered" style="background: #dfdfdf;">
							                   <thead>
                                                <tr>
                                                    <th> S. No.</th>
                                                    <th> Date </th>
                                                    <th> Time </th>
                                                    <th> Service Delivery Tid </th>
                                                    <th> Request Amount </th>
                                                    <th> Beneficiary Name </th>
                                                    <th> Bank Name </th>
                                                    <th >Bank Account</th>
                                                    <th> Bank IFSC </th>                                                
                                                    <th> UTR</th>
                                                    <th> Txn. Type</th>
                                                    <th> Status</th>
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
                                                    <td><%=map.get("dot")%></td>
                                                    <td><%=map.get("tot")%></td>
                                                    <td><%=map.get("TranNo")%></td>
                                                     <td ><%=map.get("reqAmount")%></td>
                                                    <td> <%=map.get("BeneName")%></td>
                                                    <td> <%=map.get("BankName")%></td>
                                                    <td> <%=map.get("BeneAcount") %></td>
                                                    <td> <%=map.get("BankIfsc") %></td>
                                                    <td> <%=map.get("BankReferenceId") %></td>
                                                    <td> <%=map.get("TransactionType") %></td>
                                                    <% 
                                                    String AgenttranNo="";
                                                    
                                                    AgenttranNo=map.get("TranNo");
                                                    
                                                    String statusVal=map.get("status");
                                                    if(statusVal==null)statusVal="";
                                                    else if(statusVal.equalsIgnoreCase("Pending"))
                                                    	statusVal="In Process";
                                                    
                                                   
													
													%>
                                                    
                                                    <td>
                                                    
                                                     <%=statusVal%> </td>
                                                    <td>
                                                    <%if(AgenttranNo!=null && !AgenttranNo.equalsIgnoreCase("NA") ) {%>
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