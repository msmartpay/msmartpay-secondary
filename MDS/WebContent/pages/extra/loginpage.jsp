<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.HashMap" %>
<%
String message=(String)session.getAttribute("message");
session.removeAttribute("message");
if(message==null)
message="";
HashMap map=(HashMap)session.getAttribute("info");
String md_footer_copyright=(String)map.get("md_footer_copyright");
String tickerMessage=(String)map.get("md_message");
session.setAttribute("md_footer_copyright",md_footer_copyright);
session.setAttribute("tickerMessage",tickerMessage);
String poweredBy=(String)map.get("poweredBy");
String Help_mobileNo1=(String)map.get("Help_mobileNo1");
String md_header_home_image=(String)map.get("md_header_home_image");
String Help_email_id1=(String)map.get("Help_email_id1");
String mdId=(String)session.getAttribute("completeId");

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%></title>
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
       
      <div class="wrap_logo"><a href="#" target="_blank" title="logo"><p><img width="163px" height="69px" src="images/<%=md_header_home_image%>" /></p></a></div><!--end of logo-->
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
         
    </div>
    <!--end of menu_wrap--> 
   
   
   </div>
   <!--end of header_wrap-->
    
   <div class="clear"></div>
   
   <!--start of middle part-->
   <div style="margin:9px auto 4px auto; font-size:15px; font-weight:bold; text-align:center; color:#FF0000;"><%=message%></div>
   <div class="middle_partWrap"> 
     
     <!--start of registration-->
      <div class="reg_wrapp">
        
         <div class="heading_regg"><p>Email Verification </p></div>
         <div class="clear"></div>
         <!--start of verficayion mobile-->
         <form class="regis_form" name="EmailVerifyForm" action="checkloginInfo.do?param=VarifyEmailcode" method="post">
         <div class="personal_detail_t"> 
           <table cellpadding="0" cellspacing="0" class="pesonl_detals_wrap chng_pass vrification">
                             <tr><td colspan="3" height="150">Please enter the Email Verification Code sent on your Registered Email ID.</td></tr>
							  <tr><td height="100" colspan="3">&nbsp;</td></tr>   
							 <tr>
                                <td style="width:220px;" height="50" align="left">Verification Code :</td>
                                <td align="left" style="width:150px;"><input type="text" name="varifyCode"  class="Trebuchet_normal" size="20" style="width:150px;"/></td>
                                <td align="left">
								<tr><td height="100" colspan="3">&nbsp;</td></tr>   
                               
							   <!--<img  id="search_img" src="images/serach.gif" />-->
							   <input type="hidden" name="mdId" value="<%=mdId%>"/></td>
                              </tr>
                                   
                      <tr><td height="100" colspan="3" align="left" style="width:150px; padding-left:200px; border:0px solid #0033FF" >&nbsp;
                        <input type="button" name="but1" value="Submit" class="btn_sign" id="img_btn" onclick="validatefrm()" style="margin-left:30px;"/></td>
                      </tr>   
					  <tr><td height="100" colspan="3">&nbsp;</td></tr>   
          </table>
		
         </div>
       
         </form>
         <!--end of verfication mobile-->   
      </div>
     <!--end of registration-->  
      
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
             	 <script type="text/javascript" src="../../js/scroller.js"></script> 
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
function validatefrm(){
var code=document.EmailVerifyForm.varifyCode.value.replace(/^\s+|\s+$/, '');
if(code==""){
alert('Please enter verify code');
return false;
}else{
on_click();
document.EmailVerifyForm.submit();
}
}
function on_click()
   {
	   document.getElementById('img_btn').style.display="none";
	 //  document.getElementById('search_img').style.display="block";
   }
</script>