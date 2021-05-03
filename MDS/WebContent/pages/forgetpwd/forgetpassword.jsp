<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.HashMap" %>
<%
HashMap map=(HashMap)session.getAttribute("info");
String md_footer_copyright=(String)map.get("md_footer_copyright");
String tickerMessage=(String)map.get("md_message");
session.setAttribute("md_footer_copyright",md_footer_copyright);
session.setAttribute("tickerMessage",tickerMessage);
String poweredBy=(String)map.get("poweredBy");
String Help_mobileNo1=(String)map.get("Help_mobileNo1");
String md_header_home_image=(String)map.get("md_header_home_image");
String Help_email_id1=(String)map.get("Help_email_id1");


String clientIp=(String)request.getRemoteAddr();
String message=(String)request.getAttribute("msg");
if(message==null){
message="";
}


%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%>:: Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="css/style_ie.css" media="all" /> 
<![endif]-->
<link href="images/favicon.gif" rel="shortcut icon" />
</head>
<body>
<div id="page_wrap">
 
  <div id="container_page" class="box_shadow">
    
   <div class="heder_wrap">
    <div class="header"> 
       
      <div class="wrap_logo"><a href="#" title="logo"><p><img width="163px" height="69px" src="images/<%=md_header_home_image%>" /></p></a></div><!--end of logo-->
      <div class="right_hder_wrap">
          <div class="customer_cnt_lnk">
            <p><%=Help_mobileNo1%> | <a href="mailto:<%=Help_email_id1%>;"><%=Help_email_id1%></a></p>
          </div>
          <!--end of wrap_lnk-->
      </div>
      <!--end of rght_hder_wrap-->      
            
    </div>
    <!--end of header-->
    <div class="clear"></div>
   
    <div class="wrap_menu box_shadow1"> 
      <ul>
        <li><span class="special_offer">&nbsp;</span></li>  
      </ul>   
    </div>
    <!--end of menu_wrap--> 
   
   
   </div>
   <!--end of header_wrap-->
    
   <div class="clear"></div>
   
   <!--start of middle part-->
  <div style="margin:9px auto 4px auto; font-weight:bold; font-size:15px; text-align:center; color:#FF0000;"><%=message%></div>
   <div class="middle_partWrap"> 
     
      <!--start of middle_login_contain-->
      <div class="login_wrap_container box_shadow1">
        
        <div class="login_wrp">
           
           <div class="login_con box_shadow1">
              
              <div class="login_heading"><p>Forget Password?</p></div>
              <div class="clear"></div>
              
              <div class="login_form_wrp">
             <form name="ForgetPass" action="forgetPassword.do?param=sendEmail" method="post">
				<input type="hidden" name="clientIp" value="<%=clientIp%>">
                <table class="login_tble">
                   <tr>   
                      <td colspan="2" style="color:#333;"><strong>To get your password, type your Registered Email ID.</strong></td>
                      
                      
                   </tr>   
                   
                   <tr><td colspan="2"></td></tr>
                   
                    <tr>   
                      <td colspan="2">Enter your Registered Email ID</td>
                      
                      
                   </tr>   
                   
                   
                   
                    <tr>   
                      <td colspan="2">
                       <input type="text" style="width:320px;" name="emailId" maxlength="100"/>
                      </td>
                   </tr>   
                   
                   
                   <tr>   
                     
                     
                      <td colspan="2">
                       <input type="button" value="Submit" class="btn_sign" id="img_btn" onclick="return Submit();"/>					
                      </td>
                   </tr>   
                   
                </table>
                </form>
              </div>
              <!--end of login_form-->
              
              
              
              
              
              
           </div>
           <!--end of login_con-->
        
        </div>
        <!--end of login_wrp-->
        
      <!--start of forgot-->
        <div class="login_wrp" style="width:370px; float:right; padding-right:30px;">
          <div class="login_con box_shadow1" style="width:330px;">
            <div class="login_heading">
              <p>Quick Help</p>
            </div>
            <div class="login_form_wrp" style="width:330px; height:100px;">
             <div style="width:310px; height:auto; margin-top:10px; margin-bottom:auto; margin-left:9px; margin-right:5px;">
             
             <p><a href="forgetPassword.do?param=forgetPwd"><img src="images/forgot_password.png" /></a> </p> 
             <br /><a href="Banner.html"><img src="images/get_mobile_app_now.png" /></a></p>
             
            </div>
               
            </div>
            <!--end of login_form-->
            
            
            
          </div>
          <!--end of login_con-->
          
          
        </div>
         <!--end of forgot-->
           
     </div>
      <!--end of middle_login_contain-->
      
      <div class="clear"></div>
      
      <div class="marque_wrp_txt">
         
         <marquee id='marq6' scrollamount="3">
    <a onmouseover="document.getElementById('marq6').stop();" onmouseout="document.getElementById('marq6').start();" style="text-decoration:none">
   <%=tickerMessage%>
    </a>
   </marquee>
         
      </div>  
      <!--end of marquess text--> 
       
   </div>
   <!--end of middle-->
   
   <div class="clear"></div>
   
    <!--start of footer part-->
   
   <div class="footer_wrap1">
   
 <div class="footer1">
   
 <!--slider_wrap-->
    <div class="slider_container" style="border:0px solid #ccc;width:auto;">
    		
             	<!-------------------------Don't delete scroller script ----------------------------->
             	 <script type="text/javascript" src="scroller/scroller.js"></script> 
            <!--slider_content closed -->
    </div>
    <!--end slider wrap-->
   
 
   
  </div>
   
 
   <p align="right"><%=poweredBy%></p>
   </div>

   <!--end of footer_wrap--> 
   
   
    
  </div>
  <!--end of container_page-->

</div>
<!--end of page wrap-->
</body>
</html>
<script language="javascript">
function Submit(){

	var emailId=document.ForgetPass.emailId.value;
   	//alert(emailId);
	if(emailId=="")
	{
	alert("Please enter your Registered Email ID.");	
	return false;
    }
   else{	
	document.ForgetPass.submit();
	return true;
       }	
   }
</script>