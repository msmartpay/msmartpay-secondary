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
			
			$('#datepicker').val(ytoday);
			$('#datepicker2').val(today);
			
		    $( "#datepicker, #datepicker2" ).datepicker({
		        defaultDate: "+1w",
		        maxDate: "0D",
		        dateFormat: 'yy-mm-dd',
		        numberOfMonths: 1,
		        maxDate:'today',
		        
		    });
	
	});
	
	
	function showDateRange(){
		var type=$('#type').val();
		if(type=='bydaterange'){
			$('#dateRange').show();
			$('#datepicker').val('');
			$('#datepicker2').val('');
		}else 
			$('#dateRange').hide();
			
	}
	
	function popupSenderBox(){
		var type=$("input[name=reportOf]:checked").val()
		if(type=='DownloadSenderAccountStatement'){
			$('#senderidRow').show();
			$('#senderId').val('');
		}else 
			$('#senderidRow').hide();
	}
	
	function submitForm(){
		var senderId;
	
		var reporttype=$("input[name=reportOf]:checked").val()
		var type=$('#type').val();
		
		if(reporttype=='DownloadSenderAccountStatement'){
			var senderId=$('#senderId').val();
		}	
		if(reporttype=='DownloadSenderAccountStatement' && senderId==''){
			alert('Please enter sender id.');
			$('#senderId').focus();
		
		}else if(type=='bydaterange'){
			var fromDate=$('#datepicker').val();
			var toDate=$('#datepicker2').val();
			if(fromDate==''){
				alert('Please select from date.');
				$('#datepicker').focus();
			}else if(toDate==''){
				alert('Please select to date.');
				$('#datepicker2').focus();
			}else{
				//$('#resultLoading').show();
				document.AccountStmtReportForm.submit();
			}
		}else{
			//$('#resultLoading').show();
			document.AccountStmtReportForm.submit();
		}
		
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
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
						<div class="col-md-12 form-group"><h3 class="box-title">Download Report</h3></div>
	                    <div class="col-md-6">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
	                            
	                            	
                                        <div class="box" align="center">                                                                        
                                            <form name="AccountStmtReportForm" id="AccountStmtReportForm" method="post" action="doAccountStatement.action?param=downloadReport"> 
                                            	<div  align="center">
													<label  style="color: RED;font-weight: bold;"> <%=message %> </label>
												</div>
												<div class="form-group col-md-12"  align="center">
                                                    <input type="radio" style="width: 45px;height: 16px;" checked="checked" onclick="popupSenderBox()" name="reportOf" value="DownloadAccountStatement">Account Statement
                                                    <input type="radio" style="width: 45px;height: 16px;" name="reportOf" onclick="popupSenderBox()" value="DownloadSenderAccountStatement">Sender report                                              
                                                </div>                                                    
												<div class="form-group col-md-12" id="senderidRow" style="display: none;"  align="center">
                                                    <input type="text" name="senderId" id="senderId" class="form-control" placeholder="Enter Sender Id" >                                                    
                                                                                                 
                                                </div>
												 <div class="form-group col-md-12"  align="center">
													<select name="type" id="type" class="form-control" onchange="showDateRange()" >
														<option value="current">Current Day</option>
														<option value="bydaterange">By Date Range</option>
													</select>
                                                </div> 
                                                <br>
                                                <div id="dateRange" style="display: none;">
                                                <div class="form-group col-md-6"  align="center">
                                                    <input required="required" name="fromDate" type="text" readonly="readonly" id="datepicker" class="form-control" placeholder="Select From Date">
                                                </div>
                                                
                                                <div class="form-group col-md-6"  align="center">
                                                    <input required="required" name="toDate"  type="text" readonly="readonly" id="datepicker2" class="form-control" placeholder="Select To Date">
                                                </div>
                                                
                                                </div>
                                                
                                               <div class="form-group col-md-12">                       
                                               		<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                	<button type="button" id="sub_button" onclick="submitForm()" class="btn btn-success waves-effect waves-light m-r-10">Download Report</button>
                                               </div>
                                            </form>
                                    	</div>
                                    </div>                                    
                                                                
	                        </div>
		               
	                        
	                    </div>
	               		
	               
	            	</div>
                
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