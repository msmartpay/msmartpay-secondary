<%
String poweredBy="";

String copy_right=(String)session.getAttribute("md_footer_copyright");
//String tickerMessage=(String)session.getAttribute("tickerMessage");
HashMap poweredByInfo=(HashMap)session.getAttribute("info");
//System.out.println("Hash Map Ids ::  "+poweredByInfo);
poweredBy=(String)poweredByInfo.get("poweredBy");
String tickerMessage=(String)poweredByInfo.get("md_message");
if(tickerMessage==null){
	tickerMessage=(String)session.getAttribute("tickerMessage");
}
String vmnClientId1=(String)session.getAttribute("clientId");
System.out.println("vmnClientId " +vmnClientId1);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<style>

.marque_wrp_txt{background:#054e99;margin:2px 3px 2px 3px;overflow:hidden;padding:2px !important;}
.marque_wrp_txt a{color:#FFF;font-style:italic;font-weight:bold;text-shadow:1px 1px 0 #333 !important;}

</style>


</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  
  
  
  
 
<tr>
    <td  width="100%" align="center" bgcolor=""class="foot" valign="middle" height="25"> <div class="marque_wrp_txt">
         
         <marquee id='marq6' scrollamount="3">
    <a onmouseover="document.getElementById('marq6').stop();" onmouseout="document.getElementById('marq6').start();" style="text-decoration:none;">
   <%=tickerMessage%>
    </a>
   </marquee>
         
      </div>  </td>
  </tr>
  
  
<tr><td width="100%" height="1" align="right"><img src="images/saperate.gif" height="1" width="1000" border="0" /></td></tr>
  
  
  


  <tr>
    <td  width="100%" align="right" class="foot" valign="middle" height="35"><span style="padding-left:630px">
	
	  <%=poweredBy%></span></td>
		
  </tr>


</table></center>
</body>
</html>
