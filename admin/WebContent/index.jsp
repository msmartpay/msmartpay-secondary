<!DOCTYPE html>
<html>
<head>
<title>companyName</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="icon" type="image/png" href="images/favicon-pearl.png">

<link rel="icon" type="image/png" href="images/favicon-pearl.png">

<!--main file-->
<link href="css/pearl-hotel.css" rel="stylesheet" type="text/css">

<!--Medical Guide Icons-->
<link href="fonts/pearl-icons.css" rel="stylesheet" type="text/css">

<!-- Default Color-->
<link href="css/default-color.css" rel="stylesheet" id="color"  type="text/css">

<!--bootstrap-->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css">

<!--Dropmenu-->
<link href="css/dropmenu.css" rel="stylesheet" type="text/css">

<!--Sticky Header-->
<link href="css/sticky-header.css" rel="stylesheet" type="text/css">

<!--Sticky Countdown-->
<link href="css/countdown.css" rel="stylesheet" type="text/css">

<!--revolution-->
<link href="css/settings.css" rel="stylesheet" type="text/css">
<link href="css/extralayers.css" rel="stylesheet" type="text/css">

<!--Owl Carousel-->
<link href="css/owl.carousel.css" rel="stylesheet" type="text/css">

<!--Date Picker-->
<link href="css/date-pick.css" rel="stylesheet" type="text/css">

<!--Form Dropdown-->
<link href="css/form-dropdown.css" rel="stylesheet" type="text/css">

<!-- Mobile Menu -->
<link rel="stylesheet" type="text/css" href="css/jquery.mmenu.all.css" />

<!--PreLoader-->
<link href="css/loader.css" rel="stylesheet" type="text/css">

<!--switcher-->
<link href="css/switcher.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body> 
<div id="wrap"> 
  
  <!--Start PreLoader-->
  <div id="preloader">
    <div id="status">&nbsp;</div>
    <div class="loader">
      <h1>Loading...</h1>
      <span></span> <span></span> <span></span> </div>
  </div>
  
 <section style="background:#337ab7;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-4">
          <ul class="list-inline white" style="padding-top:8px;">
            <li><a href="#"><i class="icon-facebook-1"></i> <span class="nav-label">Facebook</span></a></li>
            <li><a href="#"><i class="icon-twitter-1"></i> <span class="nav-label">Twitter</span></a></li>
            <li><a href="#"><i class="icon-google-plus"></i> <span class="nav-label">Google+</span></a></li>
            <li><a href="#"><i class="icon-linkedin2"></i> <span class="nav-label"> Linkedin</span></a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>
  </div>
  
  <!--Start Header-->
  <div id="header-2" align="center">
    <header  class="header" >
    <div style="width: 20%">
    <a href="#"><img class="logo" src="images/<%=headerHomeImage %>" alt=""></a>
    </div>
    
           
    </header>
  </div>
  
  <!--End Header--> 
  
   <!--Start Content-->
  <div class="content"> 
    <!--Start Welcome Hotel-->
    <div class="welcome-hotel" style="background:url(imgs/MyImages/busbg.jpg) no-repeat fixed;">
   
       
            
      <div class="parallax"> <div class="text" style="margin-top: 80px;">
             <h2 style="padding: 10px; background: #273856; position: relative; margin-top: -10px; z-index:9999; color:#fff; ">AGENT LOGIN</h2>
           </div>     
        <div class="container-fluid">        
          <div class="welcome-detail"> 
                 
            <div class="row" style="margin-top: 60px; margin-bottom: 40px;">            
              <div class="col-md-5  col-md-offset-1">
              <div class="box">
                <div class="text" style="    margin-top: 0px;;">
                  <div class="tab-content">
                      <form action="doLoginAction.action?param=login" method="post">
			            <div style="color: RED;font-weight: bold;"><%=message %></div>
			              <div class="form-group">
			                <label class="pull-left"> Email <span class="required">*</span></label>
			                <input type="hidden" required="required" name="userType" value="<%=userType %>" />
			                <input type="text" class="form-control" name="userName" id="userName" placeholder="Please Enter Email">
			              </div>
			              <div class="form-group">
			                <div class="clearfix">
			                  <label class="pull-left"> Password <span class="required">*</span> </label>
			                  <span class="pull-right"><a href="doForgotPwd.action?param=forgotPwdPage">Lost password?</a></span> </div>
			                <input type="password" required="required" name="pass" id="pass" class="form-control" placeholder="Please Enter Password">
			              </div>
			              <button type="submit" class="btn btn-primary btn-inline btn-lg">Login</button>
			              &nbsp; &nbsp; &nbsp;
			              <!-- <label for="remember" class="checkbox-inline">
			                <input type="checkbox" name="remember" id="remember">
			                Remember me </label> -->
			            </form>
                   	  </div>                   
                  	</div>
              	  </div>
            	  </div>
                </div>
              </div>
             </div>
            </div>
          </div>
                      
              
  <footer class="footer-hotel-dark" id="footer">
    <div class="copyrights">
      <div class="container">
        <p>Copyright © 2016 Recharge. All rights reserved.</p>
      </div>
    </div>
  </footer>
  <!--End Footer--> 
  
  <a href="#0" class="cd-top"></a> </div>
<script type="text/javascript" src="js/jquery.js"></script> 

<!-- SMOOTH SCROLL --> 
<script type="text/javascript" src="js/scroll-desktop.js"></script> 
<script type="text/javascript" src="js/scroll-desktop-smooth.js"></script> 

<!-- START REVOLUTION SLIDER --> 
<script type="text/javascript" src="js/jquery.themepunch.revolution.min.js"></script> 
<script type="text/javascript" src="js/jquery.themepunch.tools.min.js"></script> 

<!-- Countdown --> 
<script type="text/javascript" src="js/countdown.js"></script> 

<!-- Owl Carousel --> 
<script type="text/javascript" src="js/owl.carousel.js"></script> 
<script type="text/javascript" src="js/cart-detail.js"></script> 

<!-- Mobile Menu --> 
<script type="text/javascript" src="js/jquery.mmenu.min.all.js"></script> 

<!-- Form Drop Down --> 
<script type="text/javascript" src="js/form-dropdown.js"></script> 

<!-- Date Picker and input hover --> 
<script type="text/javascript" src="js/classie.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.10.3.custom.js"></script> 

<!-- Booking Accordion --> 
<script type="text/javascript" src="js/booking-accordiion.js"></script> 

<!-- All Scripts --> 
<script type="text/javascript" src="js/custom.js"></script> 

<!-- Switcher --> 
<script type="text/javascript" src="js/switcher-hotel.js"></script> 

<!-- Date Picker --> 
<script type="text/javascript">
[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
// in case the input is already filled..

// events:
inputEl.addEventListener( 'focus', onInputFocus );
inputEl.addEventListener( 'blur', onInputBlur );
} );

function onInputFocus( ev ) {
classie.add( ev.target.parentNode, 'input--filled' );
}

function onInputBlur( ev ) {
if( ev.target.value.trim() === '' ) {
classie.remove( ev.target.parentNode, 'input--filled' );
}
}

//date picker
jQuery("#datepicker").datepicker({
inline: true
});

jQuery("#datepicker2").datepicker({
inline: true
});



</script> 

<!-- Revolution Slider --> 
<script type="text/javascript">
jQuery('.tp-banner').show().revolution(
{
dottedOverlay:"none",
delay:16000,
startwidth:1170,
startheight:900,
hideThumbs:200,

thumbWidth:100,
thumbHeight:50,
thumbAmount:5,

navigationType:"nexttobullets",
navigationArrows:"solo",
navigationStyle:"preview",

touchenabled:"on",
onHoverStop:"on",

swipe_velocity: 0.7,
swipe_min_touches: 1,
swipe_max_touches: 1,
drag_block_vertical: false,

parallax:"mouse",
parallaxBgFreeze:"on",
parallaxLevels:[7,4,3,2,5,4,3,2,1,0],

keyboardNavigation:"off",

navigationHAlign:"center",
navigationVAlign:"bottom",
navigationHOffset:0,
navigationVOffset:20,

soloArrowLeftHalign:"left",
soloArrowLeftValign:"center",
soloArrowLeftHOffset:20,
soloArrowLeftVOffset:0,

soloArrowRightHalign:"right",
soloArrowRightValign:"center",
soloArrowRightHOffset:20,
soloArrowRightVOffset:0,

shadow:0,
fullWidth:"on",
fullScreen:"off",

spinner:"spinner4",

stopLoop:"off",
stopAfterLoops:-1,
stopAtSlide:-1,

shuffle:"off",

autoHeight:"off",						
forceFullWidth:"off",						



hideThumbsOnMobile:"off",
hideNavDelayOnMobile:1500,						
hideBulletsOnMobile:"off",
hideArrowsOnMobile:"off",
hideThumbsUnderResolution:0,

hideSliderAtLimit:0,
hideCaptionAtLimit:0,
hideAllCaptionAtLilmit:0,
startWithSlide:0,
videoJsPath:"rs-plugin/videojs/",
fullScreenOffsetContainer: ""	
});
</script>
</body>

<!-- Mirrored from wahabali.com/work/pearl-demo/index-hotel.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 27 Jun 2016 07:40:28 GMT -->
</html>