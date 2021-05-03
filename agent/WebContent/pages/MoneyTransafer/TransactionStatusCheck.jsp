<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


	<%@ include file="../../wldetails.jsp" %>	

	<%
	
	
	ArrayList tranStatusData=(ArrayList)request.getAttribute("tranStatusData");
	int size=0;
	if(tranStatusData!=null){
	size=tranStatusData.size();
	}
	String status="";
	
	%>
	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		 <%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>Transaction Status</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row" align="center">
                                    <div class="col-md-8 col-md-offset-2" style="width: 50%;margin-left: 25%;">
                                        <div class="box">                                                                        
                                            <form name="TransactionStatusCheckForm" method="post" action="doTranStatusCheck.action?param=checkTranStatus">
                                            	<div  align="center">
													<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
												</div>     <br>                                                       
                                                <div class="form-group">
										
													<input name="connectionNo" onkeypress="return digitonly(this,event)" maxlength="13" type="text" class="form-control" placeholder="Enter Connection Number">
												</div>
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="submit" onclick="javascript=this.disabled = true; form.submit();" class="btn btn-primary btn-inline">Proceed</button>
                                               </div>
                                            </form>
                                    </div>
                                    </div>                                    
                                                                
                                </div>
                        </div>
					</div>
				</div>
                
                <div class="spacer"></div>
                <%if(size>0){%>
                <div class="row" align="center">
                    <div class="col-md-12">
                                        <div class="table-responsive">
                                           <table class="table table-striped table-bordered">
							                   <thead>
                                                <tr>
                                                    <th align="center"> Date</th>
                                                    <th align="center"> Time </th>
                                                    <th align="left"> Operator </th>
                                                    <th align="center"> A/C Number </th>                                                
                                                    <th align="center"> Txn Amount </th>
                                                    <th align="left"> Status </th>                                                
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <% for(int i=0;i<size;i++){
												  HashMap<String,String> map=(HashMap)tranStatusData.get(i);
												  %>
                                                <tr>
                                                    <td align="center"><%=map.get("dor")%></td>
                
									                <td align="center" valign="middle"><%=map.get("tor")%></td>
									
									                <td align="left"><%=map.get("mobileOperator")%></td>
									                <td align="center"><%=map.get("mobileNo")%></td>
									                <td align="center"><%=map.get("amount")%></td>
									                <td align="left"><%=map.get("status")%></td>
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
    
   <div class="spacer-xl"></div>


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