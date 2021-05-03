
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
ArrayList listData=(ArrayList)request.getAttribute("data");
int sizeOfData=0;
if(listData==null){
sizeOfData=-1;
}
else{
sizeOfData=listData.size();
}
%>
<script language="javascript">
function valiadteForm(){
var connectionNo=document.MyTransactionForm.connectionNo.value.replace(/^\s+|\s+$/, '');

if(connectionNo==""){
alert('Please enter valid connection number');
return false;
}
if(connectionNo.length>15 || connectionNo.length<8){
alert('Please enter vlaid connection number');
return false;
}
if(isNaN(connectionNo){
alert('Please enter valid connection number');
return false;
}
document.MyTransactionForm.submit();
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
							<h1> Transaction Status</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 7% 5% 7% 5%;background: #fff;">
				<div class="container" style="width: 50%;" align="center" >			
					
							<div class="cta-txt" align="center">
                                    <div class="col-md-12 ">
                                        <div class="box" align="center">
                                            <form name="MyTransactionForm" action="SearchTran.action?param=SeachTranData" method="POST" role="form">                                                       
                                                <div class="form-group">
													<input type="text" name="connectionNo" class="form-control" maxlength="15"  placeholder="Enter Connection Number">
												</div>
                                               <div class="form-group col-md-12">  
                                                	<button type="submit" name="Input" class="btn btn-primary btn-inline" onclick="valiadteForm();">Search</button>
                                               </div>
                                            </form>
                                    </div>
                                    </div>                                    
                                                                
                        	</div>
                
                <div class="spacer"></div>
                <div class="row">
                    <div class="col-md-12">
                                        <div class="table-responsive">
                                           <table class="table table-striped table-bordered">
                                           	<%if(sizeOfData>0){%>
							                   <thead>
                                                <tr>
                                                    <th> Agent ID</th>
                                                    <th> Date </th>
                                                    <th> Time </th>
                                                    <th> Operator</th>
                                                    <th> Connection Number</th>                                                
                                                    <th>Txn Amount</th>
                                                    <th> Status </th>                                                
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <% for(int i=0;i<sizeOfData;i++){
	 											HashMap map=(HashMap) listData.get(i);
	 											%>
                                                <tr>
                                                    <td><%=map.get("agentID")%></td>
                                                    <td><%=map.get("dateOfRecharge")%></td>
                                                    <td><%=map.get("timeOfRecharge")%></td>
                                                    <td><%=map.get("connectionOperator")%></td>
                                                    <td><%=map.get("connectionNo")%></td>
                                                    <td><%=map.get("amount")%></td>
                                                    <td><%=map.get("status")%></td>
                                                </tr>
                                                 <%}%>
                                                </tbody>
                                                <%}%>
                                           </table>
                                        </div> 
                                    </div>
                </div>

                
            </div>
		</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>
			
		</div>
		<!-- Main / End -->
	</div>
<%@ include file="/footer.jsp"%>	
	
	
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