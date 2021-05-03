<%@page import="java.util.HashMap"%>
<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="script/jquery.js"></script>
<SCRIPT type="text/javascript" src="scripts/calendar.js?random=20060118"></script>


<script language="Javascript" src="scripts/validation.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>


<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
<%
HashMap map=(HashMap) request.getAttribute("distributorRegistrationDetails");
%>

<script language="Javascript" src="scripts/validation.js"></script>


</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="newbg">
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                User Details - Distributor</td>
              </tr>
              <tr>
                <td width="100%" align="center"><div class="accordion" style="padding:20px;" id="border">
				
				
                    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                      <tr>
                        <td><!--<h3 align="center" style="background:#993300; color:#FFF;">Product Details</h3>
                          <h4>-->
                            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
							
							 <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
							<tr><td  colspan="10" align="center">&nbsp;</td></tr>
							   
                              <tr>
                                <td width="15%"></td>
                                <td width="33%" height="30" align="left" style="">User ID</td>
                                <td width="5%" align="left">:</td>
                                <td width="46%" align="left">
                                
                                    <%=map.get("completeDistributorId")%>
                                   
                                  </td>
                              </tr>
                              
                              	<tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Login ID</td>
                              <td  align="left">:</td>
                              <td  align="left">
                            <%=map.get("emailId")%>
                                </td>
							</tr>
                            
                            
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Password</td>
                                <td  align="left">:</td>
                                <td align="left"><%=map.get("password")%> </td>
                              </tr>
                           
							  <tr>
                                <td></td>
                                <td  align="left" height="30">Login URL</td>
                                <td  align="left">:</td>
                                <td align="left">
								<%=map.get("distributorLoginUrl")%>
                                 
                                 </td>
                              </tr>
                              
                             
                              
                            </table>
                           </td>
                      </tr>
                      
                    </table>

                  </div></td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
			  <tr>
                <td height="95"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
</body>
</html>
<style type="text/css">
.accordion {
	width: 90%;
}

</style>


<script language="JavaScript">



var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {alert(message);return false;}}

function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

</script>
