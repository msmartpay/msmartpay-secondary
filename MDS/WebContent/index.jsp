<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Home</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style5 {
	font-size:50px;
	color: #000000;
	font-weight: bold;
}

-->
</style>
</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
 <%@ include file="header.jsp"%></td></tr>
 
 <%
 String message=(String)request.getAttribute("message");
 if(message==null){
 message="";
 }
 
 %>
 
 
 <table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
    <tr>
	<td height="10"></td>
	</tr>
	<tr>
	<td height="20" valign="bottom"  style="font-size:15px; text-align:center; color:#FF0000;"><%=message%></td>
	</tr>
   
    <tr>
      <td height="100" width="100%" align="center" valign="middle"><span class="style5">Welcome to Master Distributor Panel</span></td>
    </tr>
    
    <tr>
      <td height="80" width="100%" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td align="center" width="12%"></td>
            <td align="center" width="19%"><a href="viewAgent.do?param=viewAgent" target="_parent"><img src="images/active_agent.png" border="0" /></a></td>
            <td align="center" width="19%"><a href="viewDistributor.do?param=viewDistributor" target="_parent"><img src="images/deactive_agent.png" border="0" /></a></td>
            <!--<td align="center" width="19%"><a href="distributorRegistration.do?param=register"><img src="images/add_agent.png" border="0" /></a></td>-->
            <td align="center" width="12%"></td>
          </tr>
          <tr class="hdtxt">
            <td align="center" class="hh"></td>
            <td align="center" class="hh"><a href="viewAgent.do?param=viewAgent" STYLE="TEXT-DECORATION: NONE">View Agents</a></td>
            <td align="center" class="hh"><a href="viewDistributor.do?param=viewDistributor" STYLE="TEXT-DECORATION: NONE">View Distributor</a></td>
            <!--<td align="center" class="hh"><a href="distributorRegistration.do?param=register" STYLE="TEXT-DECORATION: NONE">Add Distributor</a></td>-->
          </tr>
        </table></td>
    </tr>
    <tr>
      <td height="80" width="100%" align="center">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td align="center" width="12%">&nbsp;</td>
            <td align="center" width="19%"><a href="depositRequest.do?param=depositRequest" target="_parent"><img src="images/agent_deposite.png" border="0" /></a></td>
            <td align="center" width="19%"><a href="pushBalance.do?param=transferRequest"><img src="images/push_balance.png" border="0" /></a></td>
            <td align="center" width="19%"><a href="accountStatement.do?param=accountStatement" ><img src="images/report.png" border="0" /></a></td>
           <td align="center" width="12%"></td>
          </tr>
          <tr class="hdtxt">
            <td align="center" class="hh">&nbsp;</td>
            <td align="center" class="hh"><a href="depositRequest.do?param=depositRequest" style="TEXT-DECORATION: NONE">Deposit Request-Distributor</a></td>
            <td align="center" class="hh"><a href="pushBalance.do?param=transferRequest" style="TEXT-DECORATION: NONE">Push Balance-Distributor</a></td>
            <td align="center" class="hh"><a href="accountStatement.do?param=accountStatement" STYLE="TEXT-DECORATION: NONE">Reports</a></td>
          </tr>
        </table></td>
    </tr>
    
    
    
    
    <tr>
      <td height="40"></td>
    </tr>
   
  </table>
  
  
  
  
  
  
   <tr><td height="35" width="1000" align="left">&nbsp;</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="footer.jsp"%></td></tr>
</table></center>
</body>
</html>
<script language="javascript">
function commingSoon()
{
alert("Coming Soon!  We are working on it.");

}

	
</script>