
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
Date today = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String curdate = formatter.format(today);


String  filepath=(String)session.getAttribute("fileStatus");
System.out.println(filepath);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%></title>
<link href="images/icon.png" rel="icon" /><link href="css/mob_admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/menuscript.js"></script>
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<SCRIPT type="text/javascript" src="script/dhtmlgoodies_calendar.js?random=20060118"></script>
</head>

<body><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr><td width="100%" height="146"><%@ include file="../../header.jsp" %></td></tr>

  <tr>
    <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    
	
    <td align="center" width="79%" valign="top" style="padding-top:20px"><table width="100%" border="0" cellpadding="0" cellspacing="0" height="50"><tr>
			
			<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
					<tr><td>&nbsp;</td></tr>
						<tr><td>&nbsp;</td></tr>
         <tr>
                     <td>&nbsp;&nbsp;&nbsp;</td>
					 <td>&nbsp;</td>
					 <td align="center"><font size="5" color="#000000">File Successfully Received</font>
                   </td> 
				   <td>  </td>  <td>&nbsp;&nbsp;&nbsp;</td>
				   <td>&nbsp;</td> 
             </tr>
			 <tr>
                     <td>&nbsp;&nbsp;&nbsp;</td>
					 <td>&nbsp;</td>
					 <td align="center">
                   </td> 
				   <td>  </td>  <td>&nbsp;&nbsp;&nbsp;</td>
				   <td>&nbsp;</td> 
             </tr>
                
				   <tr>
                     <td>&nbsp;&nbsp;&nbsp;</td>
					 <td>&nbsp;</td>
					 <td align="center"><A HREF="Reportfile/<%=filepath%>"><IMG SRC="images/download.gif" WIDTH="153" HEIGHT="77" BORDER="0" ALT="Click here "></A>                   </td> 
				   <td>  </td>  <td>&nbsp;&nbsp;&nbsp;</td>
				   
				   <td>&nbsp;</td> 
             </tr>
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
<script type="text/javascript">
// code for risk dropdown 
function toggleSubmit(){

 var searchOption=document.tranSearchForm.option.value;
 if(searchOption=="")
 {
 alert("Please select a search option");
 }
 if(searchOption=="operator")
 {
 document.getElementById("d1").style.display="";
  document.getElementById("d2").style.display="none";
   document.getElementById("d3").style.display="none";
 
 }
 if(searchOption=="tranId")
 {
 document.getElementById("d1").style.display="none";
  document.getElementById("d2").style.display="";
   document.getElementById("d3").style.display="none";
 
 }
 if(searchOption=="date")
 {
 document.getElementById("d1").style.display="none";
  document.getElementById("d2").style.display="none";
   document.getElementById("d3").style.display="";
 
 }

}
function validatefrm()
{
var searchOption=document.tranSearchForm.option.value;
 if(searchOption=="")
 {
 alert("Please select a search option");
 document.tranSearchForm.option.focus();
 return false;
 }
 if(searchOption=="operator")
 {
  var seloperator=document.tranSearchForm.selOperator.value;
  if(seloperator=="select")
  {
  alert("Please select an operator");
  document.tranSearchForm.selOperator.focus();
 return false;
  
  }
 }
 if(searchOption=="tranId")
 {
 var transactionId=document.tranSearchForm.transactionId.value;
 if(transactionId=="")
 {
 alert("Please enter transaction Id");
  document.tranSearchForm.transactionId.focus();
 return false;
 }
 }
 document.tranSearchForm.action="tranreport.do?param=tranreport";
 document.tranSearchForm.submit();
 
}
</script>