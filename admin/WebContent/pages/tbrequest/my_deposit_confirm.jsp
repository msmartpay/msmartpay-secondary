<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.text.DecimalFormat "%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@page import="com.tradebalanceactivity.self.TradeBalanceFormBean"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%


TradeBalanceFormBean bean=(TradeBalanceFormBean)session.getAttribute("DepositInfo");
//session.setAttribute("hamdata",hm);

String depositMode=bean.getModeofPayment();
System.out.println("depositMode   in jsp page"+depositMode);




%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="css/jquery.min.js"></script>
<link rel="icon" href="images/t.png" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
</head>
<body onLoad="hideLoadingPage()">
<center>
  <table cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
    <!--Header-->
    <!--Header-->
    <tr>
      <td align="left"  width="1000"><%@ include file="/header.jsp" %></td>
    </tr>
    <!--Header-->
    <!--Menu Acount-->
    <!--Menu Acount-->
	<tr>
    <td  align="center" width="100%" valign="top">
    <form name="updateForm" method="post"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="bottom" height="40" class="heading_mgs">Trade Balance Request</td>                      
                    </tr>

    <tr>
      <td width="100%" align="center"  valign="top"><table width="90%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" valign="top" align="center"><table width="86%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
                <tr>
                  <td width="100%" align="center" style="padding:20px 0px 20px 0px">
                  <table width="70%" border="0" cellspacing="0" cellpadding="0" align="center" class="txtt">
                      <tr>
                        <td align="center" colspan="4" valign="middle"  height="40" class="heading_mgs">Request Confirmation</td>
                      </tr>
                      <tr>
                        <td align="center" colspan="4" class="heading_mgs">Confirm Your Detail</td>
                      </tr>
                      <tr>
                        <td colspan="4">&nbsp;</td>
                      </tr>
                      <tr>
                        <td align="center"><% if(depositMode.equalsIgnoreCase("Cash in Bank")) {%>
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Depositer Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositerName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Amount</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAmountToCredit()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Payment Mode</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getModeofPayment()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Payment Time</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositTime()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Depositing Location</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositLocation()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Depositing Branch TID</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankTranId()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankName()%></td>
                            </tr>
                            <%
                                                                                      String BankName=bean.getRecievingBankName();
   
                                                                                       if(BankName.equalsIgnoreCase("other")){%>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Bank A/C Number</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankAccNo()%> </td>
                            </tr>
                            <%} else{%>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Bank A/C Number</td>
                              <td width="8%">:</td>
                              <td width="42%"><%=bean.getRecievingBankAccNo()%> </td>
                            </tr>
                            <%}%>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Branch Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBranchName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cash Receiving Charges</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankcharges()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">You will Get</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAcceptedAmount()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Remarks</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRemark()%></td>
                            </tr>
                          </table>
                          <% } %>
                          <% if(depositMode.equalsIgnoreCase("Cash on Desk")) {%>
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Payer Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositerName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Amount</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAmountToCredit()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Payment Mode</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getModeofPayment()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Payment Time</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositTime()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Receipt No</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecieptNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Reciever Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getReceiverName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Reciever Office</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getReceiverOfficeLocation()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cash Receiving Charges</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankcharges()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">You will Get</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAcceptedAmount()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Remarks</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRemark()%></td>
                            </tr>
                          </table>
                          <% } %>
                          <% if(depositMode.equalsIgnoreCase("Cheque/DD")) {%>
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Depositer Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositerName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Amount</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAmountToCredit()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Payment Mode</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getModeofPayment()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cheque No</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getChequeDDNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Bank Name as on Cheque/DD</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getChequebankname()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cheque/DD Date</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getChequedate()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Deposit Time</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositTime()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Depositing Location</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositLocation()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Bank A/C Number</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankAccNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Receiving Branch Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBranchName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cash Receiving Charges</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankcharges()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">You will Get</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAcceptedAmount()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Remarks</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRemark()%></td>
                            </tr>
                          </table>
                          <% } %>
                          <% if(depositMode.equalsIgnoreCase("Online E-Transfer")) {%>
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferee Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositerName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferee A/C No.</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankAccNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Amount</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAmountToCredit()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Payment Mode</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getModeofPayment()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transfer TID</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankTranId()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transaction Reference No.</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRefrenceId()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferee Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositBankName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transfer Date</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositDate()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transfer Time</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositTime()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferred to Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferred to A/C Number</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%"><%=bean.getRecievingBankAccNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transferred to Branch</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBranchName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cash Receiving Charges</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankcharges()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">You will Get</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAcceptedAmount()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Remarks</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRemark()%></td>
                            </tr>
                          </table>
                          <% } %>
                          <% if(depositMode.equalsIgnoreCase("NEFT/RTGS")) {%>
                          <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Sender Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositerName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Amount</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAmountToCredit()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Payment Mode</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getModeofPayment()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transfer TID</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankTranId()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Transaction Reference No.</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRefrenceId()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Sent via Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositBankName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Date</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositDate()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Time</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getDepositTime()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Sent to Bank Name</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS Sent for A/C Number</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBankAccNo()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">NEFT/RTGS sent for Branch</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRecievingBranchName()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">Cash Receiving Charges</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getBankcharges()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b">You will Get</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getAcceptedAmount()%></td>
                            </tr>
                            <tr>
                              <td width="26%">&nbsp;</td>
                              <td width="24%" class="Trebuchet_b"> Remarks</td>
                              <td width="8%" class="Trebuchet_b">:</td>
                              <td width="42%" class="Trebuchet_b"><%=bean.getRemark()%></td>
                            </tr>
                          </table>
                          <% } %>
                        </td>
                      </tr>
                      <tr>
                        <td width="26%" colspan="4" align="center" valign="middle" height="50"><a href="MyDeposit.action?param=MyDeposit">
                          <input type="button" value="Back" id="edit" style="width:100px" class="cls_btn" onClick="check()"/>
                          </a>&nbsp;&nbsp; <a href="myDepositRequest.action?param=saveDeposit">
                          <input name="button" id="save" type="button" value="Continue"  style="width:100px" class="cls_btn" onClick="check()"/>
                          </a>
                          <div id="check" style="visibility:hidden"><img src="images/loading.gif" height="20" width="30"/></div></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td height="20"></td>
          </tr>
        </table></td>
    </tr>
	
	
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></form></td>
  </tr>

    <tr>
      <td  width="1000" align="left"><%@ include file="/footer.jsp" %></td>
    </tr>
  </table>
</center>
<div id="check" style="visibility:hidden"><img src="images/loading.gif" height="20" width="30"/></div>
</body>
<script type="text/javascript">
function check() {
	document.getElementById("save").style.visibility="hidden";
	document.getElementById("check").style.visibility="visible";	
	
}
</script>
</html>
