
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.text.DecimalFormat "%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%

 
 HashMap hm=(HashMap)session.getAttribute("acknowledgementData");
 session.setAttribute("hamdata",hm);
 
 String depositMode=(String)hm.get("depositMode");
 System.out.println("depositMode   in jsp page"+depositMode);



%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=companyName %></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/distributor.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->



.tbls tr td{ font-size:12px; font-family:"Trebuchet MS"; border:0px solid #00F;}



</style>
</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
    <!--Header-->
   <!--Header-->
    <tr>
      <td align="left"  width="1000"><%@ include file="/header.jsp" %></td>
    </tr>
    <!--Header--> 
    <!--Menu Acount-->
    <tr>
      <td align="left"  width="1000"><%@ include file="/menu_acount_detail.jsp" %></td>
    </tr>
    <!--Menu Acount-->
    
    <tr>
      <td width="100%" align="center" valign="top">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" valign="top">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td width="45%" valign="bottom" height="40" align="left" class="big">Amount Deposit Request</td>
                  <td width="55%" >
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td align="right" valign="bottom" height="40"></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="10" align="center"></td>
          </tr>
          <tr>
            <td width="100%" valign="top" align="center" style="padding-top:20px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
                <tr>
                  <td width="100%" align="center" >
				
																		  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="txtt tbls">
                                                                             
																			 <tr>
   <td align="center" colspan="4" valign="middle"  height="25" class="big" style="font-size:12px;text-align:center;border:1px solid rgb(184, 203, 225);background:#b8cbe1;"><strong>Acknowledgement Slip</strong></td>
                                                                            </tr>
																			  <tr>
                                                                                <td align="center" colspan="4"><font color="red">Confirm Your Detail</font></td>
                                                                              </tr>
                                                                              <tr>
                                                                                <td colspan="4">&nbsp;</td>
                                                                              </tr>
                                                                              <tr>
                                                                                <td align="center"><% if(depositMode.equalsIgnoreCase("Cash in Bank")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%">&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b">Depositer Name</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td class="Trebuchet_b">Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td class="Trebuchet_b">Payment Time</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Depositing Location</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositLocation")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Depositing Branch TID</td>
                                                                                      <td class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankTransactionId")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankName")%></td>
                                                                                    </tr>
                                                                                    <%
   																							String BankName=(String)hm.get("BankName");
   
   																							if(BankName.equalsIgnoreCase("other")){%>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Bank A/C Number</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td class="Trebuchet_b"><%=hm.get("AccountNumber")%> </td>
                                                                                    </tr>
                                                                                    <%} else{%>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Bank A/C Number</td>
                                                                                      <td >:</td>
                                                                                      <td ><%=hm.get("AccountNumber")%> </td>
                                                                                    </tr>
                                                                                    <%}%>
                                                                                    <tr>

                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Branch Name</td>
                                                                                      <td class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BranchName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Cash Receiving Charges</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("charges")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">You will Get</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("acceptAmt")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                  <% if(depositMode.equalsIgnoreCase("Cash on Desk")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%">&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b"> Payer Name</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Payment Time</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Receipt No</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("recieptNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td class="Trebuchet_b"> Reciever Name</td>
                                                                                      <td class="Trebuchet_b">:</td>
                                                                                      <td class="Trebuchet_b"><%=hm.get("recieverName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td>&nbsp;</td>
                                                                                      <td class="Trebuchet_b"> Reciever Office</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("ReceiverOfficeLocation")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                  <% if(depositMode.equalsIgnoreCase("Cheque/DD")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%">&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b">Depositer Name</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td class="Trebuchet_b">Cheque No</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td class="Trebuchet_b"><%=hm.get("ChequeNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Bank Name as on Cheque/DD</td>
                                                                                      <td class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("ChequeBankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td class="Trebuchet_b">Cheque/DD Date</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("ChequeDDDate")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Deposit Time</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Depositing Location</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositLocation")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td class="Trebuchet_b"><%=hm.get("BankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Bank A/C Number</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("AccountNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Receiving Branch Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BranchName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Cash Receiving Charges</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("charges")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">You will Get</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("acceptAmt")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                  <% if(depositMode.equalsIgnoreCase("Online E-Transfer")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%" >&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b">Transferee Name</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transferee A/C No.</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositorAccountNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transfer TID</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankTransactionId")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transaction Reference No.</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transferee Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositorBankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transfer Date</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("RequestDate")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transfer Time</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transferred to Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transferred to A/C Number</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td ><%=hm.get("accNo")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transferred to Branch</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BranchName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Cash Receiving Charges</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("charges")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">You will Get</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("acceptAmt")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                  <% if(depositMode.equalsIgnoreCase("NEFT/RTGS")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%">&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b">NEFT/RTGS Sender Name</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Sender A/C No</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositorAccountNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transfer TID</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankTransactionId")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Transaction Reference No.</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Sent via Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("DepositorBankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Date</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("RequestDate")%></td>
                                                                                    </tr>
                                                                                    <!--<tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Time</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"></td>
                                                                                    </tr>-->
                                                                                    <!--<tr><td >&nbsp;</td><td  class="Trebuchet_b">NEFT/RTGS Benificiary Name</td>
  <td  class="Trebuchet_b">:</td>
  <td  class="Trebuchet_b"><%=hm.get("Neftbenificiary")%></td>
  </tr>-->
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Sent to Bank Name</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BankName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS Sent for A/C Number</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("AccountNumber")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">NEFT/RTGS sent for Branch</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("BranchName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Cash Receiving Charges</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("charges")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">You will Get</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("acceptAmt")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                  <% if(depositMode.equalsIgnoreCase("Credit")) {%>
                                                                                  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                                                                    <tr>
                                                                                      <td width="30%">&nbsp;</td>
                                                                                      <td width="18%" class="Trebuchet_b">Credit Requested By</td>
                                                                                      <td width="6%" class="Trebuchet_b">:</td>
                                                                                      <td width="38%" class="Trebuchet_b"><%=hm.get("DepositorName")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Amount</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("amount")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Mode</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("depositMode")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Credit Required for Days</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("creditdaysReq")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Credit Required on Date</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("creditdateReq")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Payment Comitment Date</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("PaymentCommitmentDate")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">Credit Charges </td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("charges")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b">You will Get</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("acceptAmt")%></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                      <td >&nbsp;</td>
                                                                                      <td  class="Trebuchet_b"> Remarks</td>
                                                                                      <td  class="Trebuchet_b">:</td>
                                                                                      <td  class="Trebuchet_b"><%=hm.get("remarks")%></td>
                                                                                    </tr>
                                                                                  </table>
                                                                                  <% } %>
                                                                                </td>
                                                                              </tr>
                                                                              <tr>
                                                                                <td  colspan="4" align="center" valign="middle" height="50"><a href="distributorDepositRequest.action?param=MyDeposit">
                                                                                  <input type="button" value="Back"   class="txt"/>
                                                                                  </a>&nbsp;&nbsp;
                                                                                  <a href="distributorDepositRequest.action?param=saveDeposit"> <input name="button" type="button" value="Continue"   class="txt"/></a></td>
                                                                              </tr>
                    </table>
				  </td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="20"></td>
          </tr>
        </table></td>
    </tr>
    <tr><td height="25"></td></tr>
      <tr>
      <td  width="1000" align="left"><%@ include file="/footer.jsp" %></td>
    </tr>
 
  </table></center>
</body>

</html>
