
<%
String message=(String)request.getAttribute("message");

if(message==null){
message="";
}
HashMap egenRegDetails=(HashMap)session.getAttribute("egenRegDetails");

%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
               <strong> User Details - Recharge API Client</strong>
                </td>
              </tr>
              <tr>
                <td width="100%" align="center"><div class="accordion" style="padding:20px;" id="border">
				
				<form name="registration" method="post" enctype="multipart/form-data">
                    <table width="100%"  cellspacing="0" cellpadding="0" align="center"  >
                      <tr>
                        <td>
                            <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
							
							<tr>
                                <td  colspan="10" align="center" class="dyn_mgs"><%=message%></td>
                               
                              </tr>
							  
							  <tr>
                                <td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td>
                               
                              </tr>
							   
                              <tr>
                                <td width="15%"></td>
                                <td width="33%" height="30" align="left" style="">Client ID</td>
                                <td width="5%" align="left">:</td>
                                <td width="46%" align="left">
                                
                                      <%=egenRegDetails.get("corpAgentId")%>
                                   
                                </td>
                              </tr>
                              
                              	<tr>
                              <td></td>
                              <td height="30" align="left"  class="form11">Header Username</td>
                              <td  align="left">:</td>
                              <td  align="left">
                             <%=egenRegDetails.get("headerUserName")%>
                                </td>
							</tr>
                            
                            
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Header Password</td>
                                <td  align="left">:</td>
                                <td align="left"><%=egenRegDetails.get("headerPassword")%> </td>
                              </tr>
                           
							  
                              
                              <tr>
                                <td></td>
                                <td  align="left" height="30">Admin Panel Login ID</td>
                                <td  align="left">:</td>
                                <td align="left">
								<%=egenRegDetails.get("loginId")%>
                                 
                                 </td>
                              </tr>
                              
                               <tr>
                                <td></td>
                                <td  align="left" height="30">Admin Panel Password</td>
                                <td  align="left">:</td>
                                <td align="left">
								<%=egenRegDetails.get("loginPassword")%>
                                 </td>
                              </tr>
                              
                               <tr>
                                <td></td>
                                <td  align="left" height="30">Sub-Agent ID </td>
                                <td  align="left">:</td>
                                <td align="left">
								<%=egenRegDetails.get("subAgentId")%>
                                 </td>
                              </tr>
                              
                               <tr>
                                <td></td>
                                <td  align="left" height="30">Sub-Agent Password</td>
                                <td  align="left">:</td>
                                <td align="left">
								<%=egenRegDetails.get("subAgentPassword")%>
                                 </td>
                              </tr>
                             
                            </table>
                           </td>
                      </tr>
                      
                    </table>
					</form>
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

</body>
</html>


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
