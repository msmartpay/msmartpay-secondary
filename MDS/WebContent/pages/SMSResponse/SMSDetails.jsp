<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>

<%

ArrayList getDetailsRMobile=(ArrayList)request.getAttribute("getDetailsRMobile");
//System.out.println(" getDetailsRMobile :"+getDetailsRMobile);
String clientId=(String)session.getAttribute("clientId");
//System.out.println(" Client id on jsp page :"+clientId);

 String message=(String)request.getAttribute("message");
 if(message==null){
 message="";
 }


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: SMS RESPONSE</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>


<script>


function popitup(url) {
	newwindow=window.open(url,'name','height=500,width=550,scrollbars=yes');
	if (window.focus) {newwindow.focus()}
	return false;

}

</script>

</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="91" width="1070">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<!--<tr><td width="8%" valign="center" height="40" align="left" style="padding-left:20px" class="big">View Distributor</td>
	</tr>-->
	<tr>
	<tr>  <td width="2%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big"></td></tr>

	<tr>  <td width="2%" valign="bottom" height="20" align="left" style="padding-left:20px" class="big">SMS Response </td>
	</tr>
	
	 <tr>
<td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">
<tr><td width="100%" colspan="5" align="center" ></td> </tr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

	   <tr>
	   <td width="100%" colspan="5" align="center" ><font color="#FF0000" size="9"><%=message%></font></td>
	   </tr>
	
	   <tr>
	   <td colspan="5" align="center" ></td>
	   </tr>
	
	</table>
	</form>		</td></tr>
	 	  <td width="100%" style="padding-right:0px">
	
	</td></tr>
	</table>	</td>
    </tr>
	<tr>
    
<td width="100%" valign="top"  style="padding-top:6px; padding-bottom:10px">


	<!--<table width="50%" border="0" cellspacing="0" cellpadding="0">
	<td width="100%" valign="top"  style="padding-top:6px; padding-bottom:10px">
	</table>-->
 </td>
    </tr>
	
	<%
	 int i=0;
	%>
  <form action="viewDistributor.do" method="post" name="distform" id="distform">
  <input type="hidden" name="distributorids" id="allDistId1" />
  <input type="hidden" name="param" value="" />
  <tr>
    <td width="100%" align="center" valign="top">
	
	
<table cellspacing="1" cellpadding="1" width="1000px" align="center" class="txt"  bgcolor="#b8cbe1" >
      <tbody>
	   <%
	  
	   if(getDetailsRMobile.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">
        <td width="4%" height="37" align="center" valign="middle" ><strong>S.&nbsp;No.</strong></td>
          <td width="15%" align="center" valign="middle"><strong>SMS Sent By User</strong></td>
          <td width="14%" align="center" valign="middle" style="padding-left:5px;"><strong>Received Date &amp; Time</strong></td>
          <td width="35%" align="center" valign="middle" style="padding-left:5px;"><strong>Response SMS By VMN</strong></td>
          <td width="14%" align="center" valign="middle"><strong>Sent Date &amp; Time</strong></td>
          <td  width="9%" align="center" valign="middle" style="padding-left:5px;"><strong>Keyword Format</strong></td>
        </tr>
		  <%for( i=0;i<getDetailsRMobile.size();i++){
  	HashMap temp=(HashMap)getDetailsRMobile.get(i);
	

		
	%>
        <tr bgcolor="#FFFFFF">
     <td height="27" align="center" valign="middle" >&nbsp;<%=(i+1)%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("RecMsg")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("RecTime")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("SendMsg")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("SendTime")%></td>  
		     <% if(clientId.equalsIgnoreCase("10001") || clientId.equalsIgnoreCase("10003") ) { %>     
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><a href="#" onclick="popitup('pages/SMSResponse/InternalClients.html')">Click Here</a></td>
		 <% }else{ %>
		 <td width="9%" align="center" valign="middle" style="padding:5px 0 5px 0;"><a href="#" onclick="popitup('pages/SMSResponse/WL_Clients.html')">Click Here</a></td>
		<%}%>
        </tr>
		  <input type="hidden" name="allDistId" id="allDistId" value="" />
		 
		
		
		 <%}%>
      </tbody>
    </table>	</td>
  </tr>
  <tr><td height="40" align="center" valign="middle">

   
  </td></tr>
   	</form>			   


		
				<%}else{%>
				 	<table style="border:1px solid #005CB9; width:99.48%; margin-bottom:2.50px;">
				<tr><td height="20" align="center"><font color="#FF0000" size="2">No Details Present.</font></td></tr> 
				</table>
				<%}%> 
				 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>


