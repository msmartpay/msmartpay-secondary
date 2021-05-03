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
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
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

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>Download Report</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content"style="padding: 5% 5% 5% 5%;background: url(images/samples/bg2.jpg) no-repeat;background-size: cover;background-position: center;">
				<div class="container" style="width:100%;" >				
					
					<div class="" align="center" >
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-2" align="center">
                                        <div class="box" align="center">                                                                        
                                            <form name="AccountStmtReportForm" id="AccountStmtReportForm" method="post" action="doAccountStatement.action?param=downloadReport"> 
                                            	<div  align="center">
													<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
												</div>                                                      
                                                <div class="form-group col-md-6" style="width: 100%" align="center">
                                                    <label >
                                                    Select Date <span class="required">*</span>
                                                    </label>                                               
                                                    <input required="required" name="date" style="width: 75%" type="text" id="datepicker" class="form-control" placeholder="Select Date">
                                                </div>
                                                <!--<div class="form-group col-md-6">
                                                    <label class="pull-left">
                                                     To date <span class="required">*</span>
                                                     </label>                                                 
                                                     <input type="date" class="form-control" placeholder="Enter Bill Amount">
                                               </div> 
                                               -->
                                               <div class="form-group col-md-12">                       
                                               		<button type="submit" id="sub_button" class="btn btn-primary btn-inline">Download Report</button>
                                               </div>
                                            </form>
                                    </div>
                                    </div>                                    
                                                                
                                </div>
                        </div>
					</div>
				</div>
                
                <div class="spacer"></div>
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