<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%@ include file="../../wldetails.jsp" %>
	<%
	
	String txnId=(String)request.getAttribute("txnId");
	ArrayList listData=(ArrayList)request.getAttribute("ticketData");
	int size=0;
	if(listData !=null){
	size=listData.size();
	}else{
	size=0;
	}
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
	
	$(function() {
	    $( "#from, #to" ).datepicker({
	        defaultDate: "+1w",
	        maxDate: "0D",
	        numberOfMonths: 1,
	        onSelect: function( selectedDate ) {
	            if(this.id == 'from'){
	              var dateMin = $('#from').datepicker("getDate");
	              var val=new Date().getDate()-dateMin.getDate();
	              var monthVal=new Date().getMonth()-dateMin.getMonth();
	              if(val>29)
	            	  val=29;
	              else if((val<29 && val>0) && monthVal==0)
	            	  val=val-1;
	              else if(val==0)
	            	  val=0;
	              else
	            	  val=29
				  
			
	              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() ); // Min Date = Selected + 1d
	              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() + val); // Max Date = Selected + 31d
	              $('#to').datepicker("option","minDate",rMin);
	              $('#to').datepicker("option","maxDate",rMax);  
	              //$('#to').val(rMax);
	            }
	
	        }
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
						<div class="col-md-12 form-group"><h3 class="box-title"><%if(txnId!=null && txnId.length()>0){ %>Generate Ticket<%}else{ %>View Ticket Status <%} %></h3></div>
	                    <div class="col-md-6">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                        <div class="box" align="center">                                                                        
                                            
                                            <%if(txnId!=null && txnId.length()>0){ %>    
                                            <form name="TicketForm" id="AccountStmtForm" method="post" action="ticket.action?param=submitTicket"> 
                                            	
                                                <div class="form-group">
                                                     <%if(txnId!=null && txnId.length()>0){ %>                                                
                                                     <input type="text" name="txnId" readonly="readonly" class="form-control" value="<%=txnId%>" />
                                              		 <%}else{ %>
                                              		 
                                              		 <input type="text" name="txnId" placeholder="Enter Transaction No." class="form-control" value="" />
                                              		 <%} %>
                                               </div>
                                               <div class="form-group"> 
                                                     <textarea name="ticketmessage" required class="form-control" placeholder="Enter your query here."></textarea>
                                               </div> 
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                	<button type="submit" id="sub_button"  class="btn btn-success waves-effect waves-light m-r-10">Generate Ticket</button>
                                               </div>
                                            </form>
                                            <%}else{ %>
                                            	<form name="TicketForm" id="AccountStmtForm" method="post" action="ticket.action?param=ticketPageByDate"> 
                                            	<div  align="center">
													<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
												</div>  
												<div  align="center">
													<label  style="color: RED;font-weight: bold;"> Select Date Range</label>
												</div>                                            
                                                
                                                <div class="form-group">
                                                     <input required="required" name="fromDate" id="from" type="text" class="form-control" placeholder="Select Date">
                                               </div>
                                               <div class="form-group"> 
                                                    <input required="required" name="toDate" id="to" type="text" class="form-control" placeholder="Select Date">
                                               </div> 
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                	<button type="submit" id="sub_button"  class="btn btn-success waves-effect waves-light m-r-10">View Tickets</button>
                                               </div>
                                            </form>
                                            <%} %>
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
                                           <table class="table table-striped table-bordered">
							                   <thead>
                                                <tr>
                                                    <th> S. No.</th>
                                                    <th> Ticket Id </th>
                                                    <th> Opened Date </th>
                                                    <th> Closed Date </th>
                                                    <th> Status </th>                                                
                                                    <th> Query Remark</th>
                                                    <th width="30%"> Solution Remark </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <% for(int i=0;i<size;i++){
												  HashMap<String,String> map=(HashMap)listData.get(i);
												  //System.out.println(map+"\n");
												  String status=map.get("Status");
												  %>
                                                <tr>
                                                    <td><strong><%=i+1%> </td>
                                                    <td><%=map.get("Ticket_id")%></td>
                                                    <td><%=map.get("Opened_Date")%></td>
                                                    <td ><%=map.get("Closed_Date")%></td>
                                                    
                                                    <%if(status.equalsIgnoreCase("Opened")) {%>
                                                    <td style="color: RED;font-weight: bold;"><%=status %> </td>
                                                    <%}else{ %>
                                                    <td style="color: GREEN;font-weight: bold;"> <%=status %></td>
                                                    <%} %>
                                                    <td> <%=map.get("Query_Message")%></td>
                                                    <td> <%=map.get("Solution_Message")%> </td>
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