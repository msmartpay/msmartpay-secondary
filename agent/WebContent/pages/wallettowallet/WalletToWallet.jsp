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

function sendMoney() {
	var toAgentId=document.w2wForm.toAgentId.value;
	var amount=document.w2wForm.amount.value;
	var remark=document.w2wForm.remark.value;
	if(toAgentId==''){
		alert('Enter Valid Agent Id.');
		document.w2wForm.toAgentId.focus();
		return false;
	}else if(amount==''){
		alert('Enter Valid Amount.');
		document.w2wForm.amount.focus();
		return false;
	}else if(parseInt(amount)<1){
		alert('Amount Should be greater than 100 .');
		document.w2wForm.amount.focus();
		return false;
	}else if(remark==''){
		alert('Enter Valid Remark.');
		document.w2wForm.remark.focus();
		return false;
	}else{
		document.getElementById('sub_button').style.display="none";
		document.getElementById("sub_button_loading").style.display="";
		document.w2wForm.action="w2w.action?param=WtoWTransfer";
		document.w2wForm.submit();
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
			<section class="page-content"style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;">						
					
					<div class="row">
						<div class="col-md-12 form-group"><h3 class="box-title">Wallet to Wallet Transfer</h3></div>
	                    <div class="col-md-6">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                        <div class="box" align="center">                                                                        
                                            
                                            <form name="w2wForm" id="w2wForm" method="post" > 
                                            	${requestScope.message }  
												<div  align="center">
													<label  style="font-weight: bold;"> Fill Required Details </label>
												</div>
                                                <div class="form-group">
                                              		 <input type="text" name="toAgentId" placeholder="Enter To Agent Id" class="form-control" value="" />
                                               </div>
                                               <div class="form-group">
                                              		 <input type="text" name="amount" placeholder="Enter Amount" class="form-control" value="" />
                                               </div>
                                               
                                               <div class="form-group"> 
                                                     <textarea name="remark" class="form-control" placeholder="Enter your remark."></textarea>
                                               </div> 
                                              
                                               <div class="form-group col-md-12">                                                      
                                                	<button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                    <button type="button" id="sub_button" onclick="sendMoney()"  class="btn btn-success waves-effect waves-light m-r-10">Send Money</button>
                                               		<img id="sub_button_loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                               </div>
                                            </form>
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