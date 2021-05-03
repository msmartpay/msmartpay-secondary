<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%

 int size=0;
 
 HashMap hm=(HashMap)session.getAttribute("hamdata");
 session.setAttribute("hamdata",hm);
 
 String payMode=(String)hm.get("payMode");



%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>::T B Request</title>
<link href="../../images/icon.png" rel="icon" /><link href="../../css/mob_admin.css" rel="stylesheet" type="text/css" />
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
   <tr><td width="100%" height="146"><%@ include file="../../header.jsp" %></td></tr>

  <tr>
    <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" align="center" width="11%" style="padding-left:20px">&nbsp;</td>
    <td align="center" width="89%" valign="top" style="padding-top:20px"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr><td height="38" class="indtop1" valign="middle"  align="left"><span class="big" style="padding-left:25px">TB Transfer Request</span></td></tr>
    <tr><td height="38" class="indcen" valign="top" width="1000" align="left" style="padding-left:25px">
	
	<table width="1000" border="0" cellspacing="0" cellpadding="0" align="left">

		  
  
<tr><td height="25"></td></tr>
           	
  <tr><td width="100%" align="center" valign="top">
  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr><td width="100%" align="center">
  <form  name="transferForm" method="post"> <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" >
  <tr><td height="25" align="center" class="txt1" bgcolor="#b8cbe1" ><span><strong>Acknowledgement Slip</strong></span></td></tr>
  <tr><td align="center"><table width="70%" border="0" cellspacing="0" cellpadding="0" align="center" class="txtt">
  <tr><td align="center" colspan="4"><font color="red">Confirm Your Detail</font></td></tr>
  <tr><td colspan="4">&nbsp;</td>
  </tr>
  <tr><td align="center">
  <% if(payMode.equalsIgnoreCase("Cash in Bank")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="28%">&nbsp;</td><td width="28%">Depositer Name</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbDepName")%></td>
 
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Amount</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Payment Mode</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Payment Time</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbDepTime")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Depositing Location</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbDepLocation")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Depositing Branch TID</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbDepBrTid")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%">Receiving Bank Name</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbRecBrName")%></td>
  </tr>
  <%
   String cbRecBankName=(String)hm.get("cbRecBankName");
   
   if(cbRecBankName.equalsIgnoreCase("other")){%>
   <tr><td width="28%">&nbsp;</td><td width="28%">Receiving Bank A/C Number</td>
   
  <td width="6%">:</td>
  <td width="38%">
 
  <%=hm.get("accNo")%>
  
  </td>
  </tr>
  <%} else{%>
   <tr><td width="28%">&nbsp;</td><td width="28%">Receiving Bank A/C Number</td>
   
  <td width="6%">:</td>
  <td width="38%">
 
  <%=hm.get("accNo")%>
  
  </td>
  </tr>
  <%}%>
   <tr><td width="28%">&nbsp;</td><td width="28%">Receiving Branch Name</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbRecBrName")%></td>
  </tr>
   <tr><td width="28%">&nbsp;</td><td width="28%">Cash Receiving Charges</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("charges")%></td>
  </tr>
   <tr><td width="28%">&nbsp;</td><td width="28%">You will Get</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("acceptAmt")%></td>
  </tr>
  <tr><td width="28%">&nbsp;</td><td width="28%"> Remarks</td>
  <td width="6%">:</td>
  <td width="38%"><%=hm.get("cbremarks")%></td>
  </tr>
  </table>
  <% } %>
  
  <% if(payMode.equalsIgnoreCase("Cash on Desk")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="26%">&nbsp;</td><td width="24%"> Payer Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payerName")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Amount</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Payment Mode</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Payment Time</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payTime")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Receipt No</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("reciept")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Reciever Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("recieverName")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Reciever Office</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("recieveOff")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Remarks</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("remarks")%></td>
  </tr>
   </table>
  <% } %>
   <% if(payMode.equalsIgnoreCase("Cheque/DD")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="26%">&nbsp;</td><td width="24%">Depositer Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checDepName")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Amount</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Payment Mode</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Cheque No</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("chequeNo")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Bank Name as on Cheque/DD</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checBankName")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Cheque/DD Date</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checDate")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Deposit Time</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checDepTime")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Depositing Location</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checLocation")%></td>
  </tr>
  
  <tr><td width="26%">&nbsp;</td><td width="24%">Receiving Bank Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checRecBankName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Receiving Bank A/C Number</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("accNo")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Receiving Branch Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checRecBrName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Cash Receiving Charges</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("charges")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">You will Get</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("acceptAmt")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Remarks</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("checRemarks")%></td>
  </tr>
  </table>
  <% } %>
   <% if(payMode.equalsIgnoreCase("Online E-Transfer")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="26%">&nbsp;</td><td width="24%">Transferee Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("ETransfereeName")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transferee A/C No.</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("ETransfereeAccNo")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Amount</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Payment Mode</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transfer TID</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("ETransferTid")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transaction Reference No.</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("ETransferRefNo")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transferee Bank Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("EtransferBankName")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transfer Date</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("EtransferDate")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transfer Time</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("EtransferTime")%></td>
  </tr>
  
  <tr><td width="26%">&nbsp;</td><td width="24%">Transferred to Bank Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("eRecBankName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Transferred to A/C Number</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("accNo")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Transferred to Branch</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("eRecBrName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Cash Receiving Charges</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("charges")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">You will Get</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("acceptAmt")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Remarks</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("eremarks")%></td>
  </tr>
  </table>
  <% } %>
  
   <% if(payMode.equalsIgnoreCase("NEFT/RTGS")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Sender Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftDepName")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Sender A/C No</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftDepAccNo")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Amount</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Payment Mode</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transfer TID</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftTransferTid")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Transaction Reference No.</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftRefNo")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Sent via Bank Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftDepBank")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Date</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftDepDate")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Time</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftDepTime")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Benificiary Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("Neftbenificiary")%></td>
  </tr>
  
  <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Sent to Bank Name</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftRecBankName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS Sent for A/C Number</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("accNo")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">NEFT/RTGS sent for Branch</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("NeftRecBrName")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">Cash Receiving Charges</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("charges")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">You will Get</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("acceptAmt")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Remarks</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("neftremarks")%></td>
  </tr>
  </table>
  <% } %>
  
   <% if(payMode.equalsIgnoreCase("Credit")) {%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">  <tr><td width="26%">&nbsp;</td><td width="24%">Credit Requested By</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("creditName")%></td>
 
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Amount</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("amount")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Payment Mode</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("payMode")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Credit Required for Days</td>
  <td width="8%">:</td>
  <td width="42%"> <%=hm.get("creditdaysReq")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Credit Required on Date</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("creditdateReq")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%">Payment Comitment Date</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("paymentDate")%></td>
  </tr>
  
 
   <tr><td width="26%">&nbsp;</td><td width="24%">Credit Charges </td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("charges")%></td>
  </tr>
   <tr><td width="26%">&nbsp;</td><td width="24%">You will Get</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("acceptAmt")%></td>
  </tr>
  <tr><td width="26%">&nbsp;</td><td width="24%"> Remarks</td>
  <td width="8%">:</td>
  <td width="42%"><%=hm.get("creditremarks")%></td>
  </tr>
  </table>
  <% } %>
  
  </td></tr>
  <tr><td width="26%" colspan="4" align="center"><input type="button" value="Back" onclick="backForm()"/>&nbsp;&nbsp;
    <input name="button" type="button" onclick="continueForm()" value="Continue"/></td>
  </tr>
  </table></td></tr></table></form></td></tr></table></td></tr>
  
  
  
 
   				   


		
				<tr><td height="20"></td></tr> 
</table>


</td></tr>

 

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
function backForm()
{
document.transferForm.action="mdDepositRequest.do?param=amountRequest";
document.transferForm.submit();
}
function continueForm()
{
document.transferForm.action="mdDepositRequest.do?param=saveDeposit";
document.transferForm.submit();
}
</script>