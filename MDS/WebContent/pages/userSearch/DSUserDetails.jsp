<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import = "java.math.BigDecimal" %>
<%


ArrayList distributorList=(ArrayList)request.getAttribute("distributorList"); 
//System.out.println("distributorList ::"+distributorList);
 String message=(String)request.getAttribute("message");
 if(message==null){
 message="";
 }


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: User Search</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

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

	<tr>  <td width="2%" valign="bottom" height="20" align="left" style="padding-left:20px" class="big">User Search</td></tr>
	
	 <tr>
<td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">
<tr><td width="100%" colspan="5" align="center" ><font color="#FF0000" size="2"><%=message%></font></td> </tr>
	
	</form>		</td>
	</table>	</td>
    </tr>
	<tr>
    
<td width="100%" valign="top"  style="padding-top:6px; padding-bottom:10px">


	<table width="50%" border="0" cellspacing="0" cellpadding="0">
	
	</table>
 </td>
    </tr>

  <form action="viewDistributor.do" method="post" name="distform" id="distform">
  <input type="hidden" name="distributorids" id="allDistId1" />
  <input type="hidden" name="param" value="" />
  <tr>
    <td width="100%" height="59" align="center" valign="top">
	
	
<table cellspacing="1" cellpadding="1" width="100%" align="center" class="txt"  bgcolor="#b8cbe1">
      <tbody>
	   <%
	  
	   if(distributorList.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">
          
          <td width="19%" align="center" valign="middle" style="padding:5px 0 5px 0;"><strong>Distributor ID</strong></td>
          <td width="25%" align="center" valign="middle" style="padding:5px 0 5px 0;"><strong>Firm Name</strong></td>
          <td width="20%" align="center" valign="middle"style="padding:5px 0 5px 0;"><strong>Authorised Person </strong></td>
          <td width="11%" align="center" valign="middle" style="padding:5px 0 5px 0;"><strong>Mobile No. </strong></td>
          <td width="13%" align="center" valign="middle" style="padding:5px 0 5px 0;"><strong>Current Balance</strong></td>
          <td  width="12%" align="center" valign="middle" style="padding:5px 0 5px 0;"><strong>Current Status</strong></td>
        </tr>
		  <% }for(int i=0;i<distributorList.size();i++){
  	HashMap temp=(HashMap)distributorList.get(i);
	
	
String status=(String)temp.get("login_status");
if(status.equalsIgnoreCase("Deactivate")){
status="Deactive";
}
else if( status.equalsIgnoreCase("Activate") ){
status="Active";
}
else{
status=status;
}	
		String Amount=(String)temp.get("balance");
		double DsBalance = Double.parseDouble(Amount);
		BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
		BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
	%>
        <tr bgcolor="#FFFFFF">
       
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("distributor_id")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("cname")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("contact_person")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=temp.get("mobile")%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=Balance%></td>
          <td align="center" valign="middle" style="padding:5px 0 5px 0;"><%=status%></td>
        </tr>
		
		 
		
		
		 <%}%>
      </tbody>
    </table>	</td>
  </tr>
  <tr><td height="40" align="center" valign="middle">

   
  </td></tr>
   	</form>			   

				 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>


