<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import="com.utility.UtilityP" %>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="com.utility.UtilityP" %>
<%
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
String date = formatter.format(todate);
String printmsg="";
String msg=(String)session.getAttribute("msg");
if(msg==null)
{
printmsg="";
}
else
printmsg=msg;
String ipadd=(String)request.getRemoteAddr();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px; 
	margin-right: 0px;
	margin-bottom: 0px;
}
.trebuchet_font{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:11px;}
.trebuchet_heading{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:12px;}
-->
</style>
<link href="css/insidecss.css" rel="stylesheet" type="text/css" />
<link href="../../../css/insidecss.css" rel="stylesheet" type="text/css" />
<link href="css/Style.css" rel="stylesheet" type="text/css" />
<link href="../../../css/Style.css" rel="stylesheet" type="text/css" />
<link href="../../css/style_new_pages.css" rel="stylesheet" type="text/css" />
<link href="../../css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<SCRIPT type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>

<style type="text/css">
<!--
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
<style type="text/css">
<!--
.style4 {font-size: 12px}
-->
</style>
<style type="text/css">
<!--
.colstyle {
font-family:Arial, Helvetica, sans-serif;
font-size:12px;

}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background:#01458E;
}
-->
</style>







</head>
<script language="JavaScript">


function validateForm()
{

var txt=document.emailform.email.value;
if(txt=="")
{
alert("Please enter your e-mail address");
document.emailform.email.focus();
return false;
}


else
{

document.emailform.action="forgetpwd.do?param=emailid";
document.emailform.submit();
return true;
}


}


</script>




<body >



<table width="1002" border="0" align="center" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  
  <tr>
    <td valign="top">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
      <td height="8" align="left" valign="top"></td>

  </tr>

  <tr>

    <td align="center" valign="top"><table width="992" height="149" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

      <tr>

        <td height="4" colspan="3" align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#01458E">

          <tr>

            <td height="9" align="center" valign="top"><img src="images/Dist_reg_bg_top.png" width="992" height="9" /></td>

          </tr>

        </table></td>

        </tr>

      <tr>

        <td height="5" colspan="3" align="center" valign="top"><table width="978" border="0" cellspacing="0" cellpadding="0">

          <tr>

             <td height="23" align="right"><div id="main_menu2" style="float:right; width:450px; margin-right:10px;">

         <ul>

           

                    </ul>

        </div>    

        </td>

            </table></td>

		

        </tr>

     <tr>

     <td>     </td>

     </tr>

      <tr>

        <td width="10">&nbsp;</td>

        <td width="972" align="left" valign="middle" ><table width="100%" border="0" cellspacing="0" cellpadding="0">

          <tr>

            <td width="18" rowspan="3" align="left" valign="middle"></td>

            <td width="271" rowspan="3" align="left" valign="middle"><img src="images/logo_new.jpg" width="271" height="110" /></td>

			<!--<td width="415" rowspan="3" align="left" valign="middle"><img src="images/irctc-logo.gif" width="65" height="55" /></td>-->

            <td width="268" align="left" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="new_login">

              <tr>

                <td height="52" align="left" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

                      <tr>

                        <td align="left" valign="top">&nbsp;</td>

                        <td width="122" align="left" valign="top">&nbsp;</td>

                        <td width="0" align="center" class="customercare"s>&nbsp;</td>

                      </tr>

                      <tr>

                        <td height="37" colspan="3" align="left" valign="top" class="customercare">&nbsp;</td>

                        </tr>

    </table></td>

                </tr>

              

            </table></td>

          </tr>

         

          <tr>

            <td height="22" align="right" valign="bottom" class="customer_login">Customer Support: &nbsp;092-122-44-790<br />
             </td>

          </tr>

        </table></td>

        <td width="10">&nbsp;</td>

      </tr>

      <tr>

        <td height="9" colspan="3" align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#01458E">

          <tr>

            <td height="9" align="center" valign="top"><img src="images/Dist_reg_bg_bottom.png" width="992" height="9" /></td>

          </tr>

        </table></td>

        </tr>

    </table></td>

  </tr>

  <tr>

    <td height="18" align="left" valign="top">
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

      <tr>

        <td width="29" height="18" bgcolor="#01458E">&nbsp;</td>

       
      </tr>

    </table></td>
	  
	  
      
    </table></td>
  </tr>
  
  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <!--DWLayoutTable-->
      <tr>
        <td width="1002" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
          <!--DWLayoutTable-->
          <tr>
            <td width="1002" align="center" valign="top"><table width="999" align="center" cellpadding="0" cellspacing="0" class="wrapper">
  <tr>
    <td width="992" align="center" valign="top"><table width="992" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td height="9" colspan="3" align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#01458E">
          <tr>
            <td height="9" align="center" valign="top"><img src="images/Dist_reg_bg_top.png" width="1002" height="9" /></td>
          </tr>
        </table></td>
        </tr>
		<tr>
          <td height="9" colspan="3" align="center" valign="top">&nbsp;</td>
		</tr>
		<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
         <tr>
           <td style="font-family:Arial, Helvetica, sans-serif; font-size:14px; font-weight:bold;">Forget Password? </td>
         </tr>
		 <tr>
		   <td class="colstyle">To get your password, type your email address.</td>
		 </tr>
		 <tr>
		   <td class="colstyle">&nbsp;</td>
		   </tr>
		 <tr><td class="colstyle"><div align="center"><%=printmsg%></div></td></tr>
		 <form name="emailform" method="post">
		 <tr><td><table><tr><td width="163" class="colstyle">Enter your E-mail address
		   <input type="hidden" name="clientID"value="<%=ipadd%>"></td>
		 		 <td width="152"><input type="text" name="email"/></td>
				  <td width="170"><input type="button" name="but1" value="submit" onClick="validateForm()"/></td>
		 </tr>
		 </table></td></tr></form>
		 
	
          <% session.removeAttribute("msg");%>
		  <tr>
            <td align="center" valign="top"><table width="100%" border="0" align="center" cellspacing="0" cellpadding="0" >
                  <tr>
                     <td width="992" align="center" valign="top"><%@ include file="../../../../footer.jsp" %></td>
                  </tr>
                </table></td>
          </tr>
        </table></td>
        
      </tr>
	  <tr>
        <td height="9" colspan="3" align="center" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" bgcolor="#01458E">
          <tr>
            <td height="9" align="center" valign="top"><img src="images/Dist_reg_bg_bottom.png" width="1002" height="8" /></td>
          </tr>
        </table></td>
        </tr>
      
    </table></td>
  </tr>
</table></td>
        </tr>
          </table></td>
  </tr>
      

   </table>   

</table>
</body>
</html>


