
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
  <%
String ip_address=(String)request.getRemoteAddr();
String message=(String)request.getAttribute("message");

if(message==null)
{
message="";

}
System.out.println("message ::"+message);
%>

<script type="text/javascript">
function digitonly(input,evt)
{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) 
{
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) 
{
return true;
}
document.getElementById("errField").innerHTML='<p>Enter Number Only.  </p></html>';
document.tbpush.amount.focus();
return false;
}

function noBack() 
{ 
	window.history.forward(); 
}

function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}

	 function submitRequest()
	{
	var agID=document.Pushbalance.id.value;
	if(agID==""){
	alert('Please enter the agent id');
	return false;
	}
	else{
	document.Pushbalance.submit();
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
							<h1>Push Balance - Agent</h1>
						</div>
						
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content">
				<div class="container" >				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg1.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row" style="margin-bottom: 10%">
                                    <div class="col-md-8 col-md-offset-2" style="width:50%;margin-left:25%;">
                                        
                                        <div class="box">     
                                        <div style="color: RED;font-weight: bold;;"><%=message %></div> 
                                            <form name="Pushbalance" action="PushBalance.action" method="POST" role="form">
                                             <input type="hidden" name="param" value="getAgentInfo">
                                             <input type="hidden" name="IpAdress" value="<%=ip_address%>">                                                       
                                                <div class="form-group">
													<input type="text" id="reqId" name="id" class="form-control" value="" maxlength="12" onkeyup="convertToCaps(this);" placeholder="Enter Full Agent ID (Eg. AG2001)">
												</div>
												<button name="button" type="submit" class="btn btn-primary btn-inline" onclick="submitRequest();">Submit</button>&nbsp; &nbsp; &nbsp;
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