
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.ArrayList "%>

<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>

<% 
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}

String FilePath=(String)session.getAttribute("fileStatus");
%>
 <script language="javascript" type="text/javascript">

 function SubmitForm(){

	 var serachBy=document.Getreport.searchBy.value;

	 if(serachBy=="select"){
	 alert("Please select Search By.")
	 return false;
	 }
	 var searchBy=document.getElementById("searchBy").value;
	 if(searchBy=="1"){
	 document.Getreport.action="AgentReport.action?param=ATTReport";
	 document.Getreport.submit();

	 return true;
	 }
	 if(searchBy=="2"){
	 document.Getreport.action="AgentReport.action?param=LRTReport";
	 document.Getreport.submit();

	 return true;

	 }


	 }

</script>

	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
	
	
	
	<!-- CSS
	================================================== -->
	<!-- Base + Vendors CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="css/fonts/entypo/css/entypo.css">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css" media="screen">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css" media="screen">
	<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen">

	<!-- Theme CSS-->
	<link rel="stylesheet" href="css/theme.css">
	<link rel="stylesheet" href="css/theme-elements.css">
	<link rel="stylesheet" href="css/animate.min.css">

   

  <!-- Head Libs -->
	<script src="vendor/modernizr.js"></script>

	
	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicons/favicon.png">
	<link rel="apple-touch-icon" sizes="120x120" href="images/favicons/favicon-120.png">
	<link rel="apple-touch-icon" sizes="152x152" href="images/favicons/favicon-152.png">
	
	<link rel="stylesheet" href="css/jquery-ui.css" />
	<script src="js/jquery-1.9.1.js"></script>
 	<script src="js/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		  
		var now = new Date();
	    var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		
		var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
		var today = now.getFullYear()  + '-' + mm + '-' + dd;
		
		$("#datepicker").val(today);
	 	
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
	<%@ include file="/header.jsp"%>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Agent Report</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 7% 5% 7% 5%;background: #fff;">
				<div class="container" style="width: 50%;" align="center" >			
					
							<div class="cta-txt" align="center">
                                    <div class="col-md-12">
                                        <div class="box" align="center">           
                                            <form name="MyTransaction" action="AgentReport.action?param=download" method="POST" >                                                       
                                                <div style="color: RED;font-weight: bold;margin-bottom: 10px;"><%=message%></div>
                                                <div class="form-group col-md-6">
                                                    <select class="form-control"   id="searchBy" name="searchBy" >
											              <option value="select" style="font-size:13px;"  selected="selected">Select</option>
											              <option value="DownloadATT" style="font-size:13px;">Agents Account Statement</option>
											              <option value="DownloadLRT" style="font-size:13px;">Recharge Transactions</option>
											              <!--<option value="3">All service</option>-->
											        </select>
                                                </div>
                                                <div class="form-group col-md-6">
                                                    <input type="text" name="date" id="datepicker" required="required" class="form-control" placeholder="Select Date ">
                                                  
                                                </div>
                                                
                                                
                                               <div class="form-group col-md-12"> 
                                                <%if(FilePath!=null && FilePath.length()>0){  %>
                                                <a style="margin-right: 10%;" href="Reportfile/<%=FilePath %>">Download Report</a>
                                                <%} %><button type="submit" class="btn btn-primary btn-inline" onclick="SubmitForm()">Genrate Report</button>
                                               </div>
                                            </form>
                                    	</div>
                                    </div>                                    
                                                                
                        	</div>
                
                <div class="spacer"></div>
                

            </div>
		</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>

		</div>
		<!-- Main / End -->
	</div>
<%@ include file="/footer.jsp" %>

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