
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

System.out.println("inside jsp page ");
HashMap map=new HashMap();
map=(HashMap)session.getAttribute("agData");
System.out.println("map::"+map);
int size=0;
				 if (map==null)
					 {
						 size=-1;
					 }
					 else
					 {
						 size=map.size();
						
					 }
				String AgID=(String)session.getAttribute("AgID"); 

%>
<script type="text/javascript">
function noBack() 
{ 
	window.history.forward(); 
}

</script>
<script type="text/javascript">
function validateFrm(){

	if(submitForm())
	{     
		document.getElementById('balpush').style.display="none";
		document.getElementById("loading").style.display="";
		document.pushbalance.action="PushBalance.action?param=pushBalance";
		document.pushbalance.submit();
		return true;
	
	}
}
function submitForm()
{
	var mm=document.pushbalance.TransaferAmount.value;
	var remark=document.pushbalance.PaymentRemark.value;
	//alert(mm);
	
	//var ID=document.getElementById("reqId").value;
	//alert(ID);
	if(mm==""){
	alert("Please Mention Transfer Amount.");
	 document.pushbalance.TransaferAmount.focus();
     return false;
	}
		
	else if(mm<100){
	alert("Amount should be more than Rs. 100");
	 document.pushbalance.TransaferAmount.focus();
     return false;
	}
	else if(mm>50000){
	alert("Amount can not be more than Rs. 50000");
	 document.pushbalance.TransaferAmount.focus();
     return false;
	}
	else if(remark==""){
	 alert("Please put Remark");
	 document.pushbalance.PaymentRemark.focus();
     return false;
	}else{
		var x=window.confirm("Confirm the Action.");
		if (x)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}

function checkChar(e)
{
var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 33 && k < 59) || (k > 59 && k < 126) || k == 8 || k == 32 );
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
	
	<script type="text/javascript">
	
	function digitonly(input,evt)
	{
		var keyCode = evt.which ? evt.which : evt.keyCode;
				var lisShiftkeypressed = evt.shiftKey;
				if(lisShiftkeypressed && parseInt(keyCode) != 9) {
				return false ;
				}
				if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
				return true;
				}

				return false;

	}
	
	</script>
	
</head>
<body>
<%@ include file="/header.jsp" %>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>Push  Transfer  To Agent</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->
			<!-- Page Content -->
			<section class="page-content">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg1.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-2" style="width:50%;margin-left:25%;">
                                        <div class="box" style="padding-left: 10%;">    
                                             <div style="color: RED;font-weight: bold;margin-bottom: 5%;"><%=message %></div>                                                            
                                            <form name="pushbalance" method="POST" action="PushBalance.action?param=pushBalance">
												
												<div class="form-group">
                                                    <label style="width: 40%;text-align: left;font-weight: bold;" class="pull-left">
                                                    	To<span class="required">*</span>
                                                    </label>                                                 
                                                   <label style="width: 40%;text-align: left;">Agent</label>
                                                </div>
												<div class="form-group ">
                                                    <label style="width: 40%;text-align: left;font-weight: bold;" class="pull-left">
                                                    	Agent ID<span class="required">*</span>
                                                    </label>                                                 
                                                     <label style="width: 40%;text-align: left;"><%=AgID%></label>  
                                                </div>
												<div class="form-group ">
                                                    <label style="width: 40%;text-align: left;font-weight: bold;" class="pull-left">
                                                    Agent Name <span class="required">*</span>
                                                    </label>                                                 
                                                    <label  style="width: 40%;text-align: left;"><%=map.get("agency_name") %></label> 
                                                     <input type="hidden" name="IpAdress" value="<%=ip_address%>">
                                                </div>
												<div class="form-group ">
                                                    <label style="width: 50%;text-align: left;font-weight: bold;" class="pull-left">
                                                    Amount <span class="required">*</span>
                                                    </label>  
                 									  <input style="width: 40%;text-align: left;" type="text" class="form-control" name="TransaferAmount" value="" required="required" placeholder="Enter the Amount" onkeypress="return digitonly(this,event)" maxlength="10">                                 
                                                </div>
												<div class="form-group ">
                                                    <label style="width: 50%;text-align: left;font-weight: bold;" class="pull-left">
                                                    	Payment Information <span class="required">*</span>
                                                    </label>   
                                                    <input style="width: 40%;text-align: left;" readonly="readonly" class="form-control" name="PaymentRemark" required="required" value="<%=AgID%> <%=map.get("agency_name") %>"  />
                                                </div>
                                                <div class="form-group ">  
                                                	<button type="button" id="balpush" class="btn btn-primary btn-inline" onclick="validateFrm()">Push Balance</button>
                                               		<img id="loading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
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
	
		</div>
		<!-- Main / End -->
	</div>
	
<%@ include file="/footer.jsp" %>	

	
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