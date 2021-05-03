<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
</head>
<%
String message=(String)request.getAttribute("message");
if(message=="")message="";
%>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
  <tr>
    <td valign="top" align="center" height="400" class="top_padding">
   
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >   
        <tr>
          <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="66%" valign="bottom" height="40" align="left" style="padding-left:105px" class="logintxt" ></td>  
                          
              </tr>             
             
              <tr>
                <td valign="top" align="center">
                    <table cellpadding="0" cellspacing="0" width="83%" align="center" border="0"  class="form11" id="border">
                      <tr>
                        <td height="30" colspan="4"></td>
                      </tr>
                      <tr>
                        <td width="30%" height="35" align="right" valign="middle" style="padding-right:50px;"></td>                        
                        <td align="left"></td>
                        <td  colspan="10" align="center" style="font-size:18px; font-weight:bold; color:#FF0000; font-family:'Trebuchet MS';">
						<%=message%></td>
                          
                            </tr>
                         
                      <tr>
                        <td></td>
                        <td></td>
                        <td align="right" height="30"></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="4" height="20"></td>
                      </tr>
                    </table>
                  </td>
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table>
     
      </td>
  </tr>
  <!--footer-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>
