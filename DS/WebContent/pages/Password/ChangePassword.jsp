
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>

<script type="text/javascript">

function nospaces(t){

if(t.value.match(/\s/g)){

alert('Space is not Allowed in Password Field.');

t.value=t.value.replace(/\s/g,'');
	return false;	
}

}

</script>
<%
String message=(String)request.getAttribute("message");
System.out.println("message---"+message);
if(message==null)
{
message="";

}
%>
<script type="text/javascript">
    
	
	
	function checkPassword(pw,options)
	{
		var o = {
			lower:    0,
			upper:    0,
			alpha:    0, /* lower + upper */
			numeric:  0,
			special:  0,
			length:   [0, Infinity],
			custom:   [ /* regexes and/or functions */ ],
			badWords: [],
			badSequenceLength: 0,
			noQwertySequences: false,
			noSequential:      false
		};
	
		for (var property in options)
			o[property] = options[property];
	
		var	re = {
				lower:   /[a-z]/g,
				upper:   /[A-Z]/g,
				alpha:   /[A-Z]/gi,
				numeric: /[0-9]/g,
				special: /[\W_]/g
			},
			rule, i;
	
		// enforce min/max length
		if (pw.length < o.length[0] || pw.length > o.length[1]){
			//alert("wrong length");
			alert("Password Length Should be of 6 Characters Min & 15 characters.");
			return false;
			}
		// enforce lower/upper/alpha/numeric/special rules
		for (rule in re) {
			
			if ((pw.match(re[rule]) || []).length < o[rule]){
				alert("Entered Password Must Contain at Least One Lower & One Upper Alpha,One Numeric,One Special Character.");
				return false;
				
				}
		}
	
		// enforce word ban (case insensitive)
		for (i = 0; i < o.badWords.length; i++) {
			if (pw.toLowerCase().indexOf(o.badWords[i].toLowerCase()) > -1){
				alert("// Enforce World Ban (Case Insensitive).");
				return false;
				}
		}
	
		// enforce the no sequential, identical characters rule
		if (o.noSequential && /([\S\s])\1/.test(pw)){
			alert("Identical Characters Rule");
			return false;
			}
			
		
		
		  
	}
	
	function validatefrm() {
	
		var check=document.changePasswordForm;
		if(check.oldPassword.value=="")
		{
		
		alert("Please Enter Your Current Password.")
		check.oldPassword.focus();
		return false;
		}
		else if(check.NewPassword.value=="")
		{
		
		alert("Please Enter Your New Password.")
		check.NewPassword.focus();
		return false;
		}
		
		else if(check.ReTypePassword.value=="")
		{
		
		alert("Please Enter your Re Type Password.")
		check.ReTypePassword.focus();
		return false;
		}
		else if(check.NewPassword.value!=check.ReTypePassword.value)
		{
			alert("Your New Password and Confirm Password are did not match");
			check.NewPassword.focus();
		    return false;	
		}
		else if(check.NewPassword.value==check.oldPassword.value)
		{
			alert("New Password must be Different from Old Password ");
			check.NewPassword.focus();
		    return false;	
		}else{
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
							<h1>Change Password</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="height: 425px;">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5">
						<div class="cta-inner">
							<div class="cta-txt">
                            <div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="box">
								<form name="changePasswordForm" method="POST" action="changePass.action?param=changePassword" onsubmit="return validatefrm();">
								<%if(request.getAttribute("message")!=null)
						               {
						               String Loginmessage=(String)request.getAttribute("message");
						             
						               %>
						              <div style="margin-bottom: 5px;color: RED;" class="form_row form-error"><%=Loginmessage %></div>
						     
						               <%
						               }
						             %>
									<div class="form-group">
										<input type="text" required="required" class="form-control" placeholder="Please Enter Old Password" name="oldPassword"  maxlength="15">
									</div>
									<div class="form-group">
										<input type="password" required="required" class="form-control" placeholder="Please Enter New Password" name="NewPassword" maxlength="15" onkeyup="nospaces(this)">
									</div>
									<div class="form-group">
										<input type="text" required="required" class="form-control" placeholder="Please Confirm Password" name="ReTypePassword"  maxlength="15" onkeyup="nospaces(this)">
									</div>
									<button type="submit" id="changepass" class="btn btn-primary btn-inline" >Change Password</button>&nbsp; &nbsp; &nbsp; 
									
								</form></div>
                                </div>
                                </div>
                                
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