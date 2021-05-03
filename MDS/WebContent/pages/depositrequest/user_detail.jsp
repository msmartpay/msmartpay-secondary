<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import = "java.util.HashMap " %>
<%@ page import = "java.util.ArrayList" %> 
<%


HashMap map= (HashMap)request.getAttribute("distributordetails");
HashMap hashmap=(HashMap)session.getAttribute("info");
String header_home_image=(String)hashmap.get("md_header_home_image");

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%></title>
<link href="css/dis.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body{font-family:"Trebuchet MS"; font-size:12px;}

-->

</style>
</head>

<body>
<center>
  <table cellpadding="0" cellspacing="0" border="0" align="center" width="450"  >
    <tr>
      <td height="40" align="left" valign="middle"  style="color:#0033CC;font-size:14px;"><strong>Request Details </strong></td>
    </tr>
    <tr>
      <td align="center"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border">
          <tr>
            <td  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b8cbe1">
                <tr bgcolor="#dbe5f1">
                  <td height="25" align="center"><strong>&nbsp;Distributor details </strong></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
			<tr align="left">
                  <td width="5%"></td>
                  <td align="left" height="25" width="40%"><strong>Distributor Name</strong></td>
                  <td align="left"  width="5%">:</td>
                  <td align="left"  width="50%"><%=map.get("name")%></td>
                </tr>
                <tr align="left">
                  <td width="5%"></td>
                  <td align="left" height="25" width="40%"><strong>Distributor ID</strong></td>
                  <td align="left"  width="5%">:</td>
                  <td align="left"  width="50%"><%=map.get("dist_id")%></td>
                </tr>
                
                <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Company Name</strong></td>
                  <td>:</td>
                  <td><%=map.get("company_name")%></td>
                </tr>
<tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>E-Mail.</strong></td>
                  <td>:</td>
                  <td><%=map.get("email_id")%></td>
                </tr>
                <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Mobile No.</strong></td>
                  <td>:</td>
                  <td><%=map.get("mobile_no")%></td>
                </tr>
 <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Transacation Id </strong></td>
                  <td>:</td>
				  <%
				   String tranId=(String)map.get("tranId");
				  if(tranId==null){
				  tranId="NA";}
				   %>
                  <td><%=tranId%></td>
                </tr>
              <!--  <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Depositor Name</strong></td>
                  <td>:</td>
                  <td></td>
                </tr>-->


              </table></td>
          </tr>
        </table></td>
    </tr>
    <tr><td width="100%" height="10"></td></tr>
    <tr>
      <td align="center"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="border">
          <tr>
            <td  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#b8cbe1">
                <tr bgcolor="#dbe5f1">
                  <td height="25" align="center"><strong>&nbsp;Deposit Details </strong></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="#FFFFFF">
                <tr align="left">
                  <td width="5%"></td>
                  <td align="left" height="25" width="40%"><strong>Mode of Payment</strong></td>
                  <td align="left"  width="5%">:</td>
                  <td align="left"  width="50%"><%=map.get("mode")%></td>
                </tr>
                 <tr align="left">
                  <td width="5%"></td>
                  <td align="left" height="25" width="40%"><strong>Account Holder Name</strong></td>
                  <td align="left"  width="5%">:</td>
                  <td align="left"  width="50%"><%=map.get("depositorName")%></td>
                </tr>
                <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Bank Name</strong></td>
                  <td>:</td>
                  <td><%=map.get("bank_name")%></td>
                </tr>

                <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Depositor Bank Name</strong></td>
                  <td>:</td>
                  <td><%=map.get("bank_name")%></td>
                </tr>

 <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Bank Txn ID/Cheque No</strong></td>
                  <td>:</td>
				  <%
				   String chequeNo=(String)map.get("chequeNo");
				  if(chequeNo==null){
				  chequeNo="";}
				   String Bank_Tran_Id=(String)map.get("Bank_Tran_Id");
				  if(Bank_Tran_Id==null){
				  Bank_Tran_Id="";}
				String deposit_date=(String) map.get("deposit_date");
				String request_date=(String)map.get("request_date");
				  if(deposit_date==null){
				  deposit_date=request_date;
				  }
				  %>
                  <td><%=chequeNo%><%=Bank_Tran_Id%></td>
                </tr>
                <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Deposited Date</strong></td>
                  <td>:</td>
                  <td><%=deposit_date%></td>
                </tr>
<tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong> Requested Date</strong></td>
                  <td>:</td>
                  <td><%=request_date%></td>
                </tr>
				 <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Customer Account No</strong></td>
                  <td>:</td>
				  <% String AcctNo=(String)map.get("depositorAccNo");
				  if(AcctNo==null){
				  AcctNo="--";}
				   %>
                  <td><%=AcctNo%></td>
                </tr>
				<tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Requested Amount</strong></td>
                  <td>:</td>
                  <td><%=map.get("ammount_to_credit")%></td>
                </tr>
				
  <tr align="left">
                  <td height="25"></td>
                  <td align="left"><strong>Remark</strong></td>
                  <td>:</td>
                  <td><%=map.get("remark")%></td>
                </tr>

              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</center>
</body>
</html>
