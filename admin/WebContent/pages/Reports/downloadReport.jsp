
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
Date today = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String curdate = formatter.format(today);

String message=(String) request.getAttribute("message");

if(message==null){
message="";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("headerName")%></title>
<link href="images/icon.png" rel="icon" /><link href="css/mob_admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/menuscript.js"></script>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>

</head>

<body><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td width="100%" height="146"><%@ include file="/header.jsp" %></td></tr>

  <tr>
    <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    
	
    <td align="center" width="79%" valign="top" style="padding-top:20px"><table width="100%" border="0" cellpadding="0" cellspacing="0" height="50"><tr>
			
			<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
         
			 
                
				   <tr>
    <td style="text-align:center;font-size:18px;color:#333;"><%=message%>
	 </td></tr>
	 <tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
         <tr><td>&nbsp;</td></tr>
		 <form name='myForm' >
		 <input type='hidden' name='param' value='viewReport' />
				<tr><td style="text-align:center;"><input type='submit' value='Back' onclick="submitForm()" /></td></tr>
				</form>
					<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
         
			 
		  <tr><td>&nbsp;</td></tr>
		  <tr><td>&nbsp;</td></tr>
         
         
          
          </table>
	</td></tr></table></td></tr></form>

				<tr><td height="10"></td></tr> 
</table>


</td></tr>

  <tr><td height="10" class="indbot">&nbsp;</td></tr>

</table>
</td>
  </tr>
</table>
</td>
  </tr>
  <tr><td height="25"></td></tr>
    <tr><td width="100%" height="50"><%@ include file="../../footer.jsp" %></td></tr>

</table>
</body>
</html>
<script language="javascript">
function submitForm()
{
		document.myForm.action="commonAction.action?param=home";
		document.myForm.submit();
	
}
</script>
