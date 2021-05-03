<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.formBean.adminUser.UserAmountFormBean"%>
<head>
<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=utf-8" />
<%@page import = "com.tradebalanceactivity.self.TradeBalanceSelfActivity" %>
<%@page import = "com.tradebalanceactivity.self.TradeBalanceFormBean" %>
<%@page import="com.login.superadmin.*" %>
<%@page import = "java.util.*" %> 
<title><%=session.getAttribute("headerName")%></title>
<script type="text/javascript" src="scripts/digitonly.js"></script>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>

  $(document).ready(function(){
	  
	    var now = new Date();
	    var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
		
     	$('#datepicker').val(today);
 		$("#datepicker").datepicker({
        	changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		  
	});
		  </script>


</head>
<body>
<%
UserAmountFormBean bean=(UserAmountFormBean)session.getAttribute("AccountInfo");
String username=(String)session.getAttribute("userName");
Date today = new Date();
SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
String curdate = formatter1.format(today);
String message=(String)request.getAttribute("myDepositMessage");
if(message=="")
{
	message="";
}


 %>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
</tr>
<tr>
  <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
      <tr>
        <td valign="top" align="center" class="rounded-corners box_heights">
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td  width="50%" valign="middle" height="40" align="left" class="heading_mgs">My Deposit</td>
              <%if(message!=null)
                { %>
                <td valign="bottom" height="40" align="left" class="dyn_mgs"><%=message %></td>
                <%} %>
            </tr>
            <tr>
              <td colspan="4">
              <table width="86%" border="0" cellspacing="0" cellpadding="0" align="center" class="border" style="margin-bottom:20px;">
                <tr>
                  <td width="100%" align="center" style="padding:20px 0px 20px 0px">
				<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="form11">
                      <tr>
                        <td width="35%" align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" bgcolor="#a74312" class="mydata_tabl">
                                  <tr>
                                 
                                    <th align="center" height="30" colspan="2" class="com_clr">Account Information</th>
                                  </tr>
                                  <tr bgcolor="#ffffff">
                                        <td height="30" width="55%"  align="left" style="padding-left:10px">Total Deposit  </td>
                                        <td align="center" width="45%"><%=bean.getTotalCash() %></td>
                                       </tr>
                                        <tr bgcolor="#ffffff">
                                          <td height="30" align="left"  style="padding-left:10px">Used</td>
                                          <td align="center"><%=bean.getUsedCash() %></td>
                                        </tr>
                                        
                                        <tr bgcolor="#ffffff">
                                        
                                          <td height="30" align="left"  style="padding-left:10px">Balance</td>
                                          <td align="center"><%=bean.getAvailableBalanceAmount() %></td>
                                        </tr>
                                        
                                        <tr bgcolor="#ffffff">
                                       
                                          <td height="30" align="left"  style="padding-left:10px">Last Updated Amount</td>
                                          <td align="center"><%=bean.getLastUpdateAmount() %></td>
                                        </tr>
                                        
                                        <tr bgcolor="#ffffff">
                                       
                                          <td height="30" align="left"  style="padding-left:10px">Cut Off</td>
                                          <td align="center"><%=bean.getCutOffAmount() %></td>
                                        </tr>
                                        
                                </table>
                            </td>
                        <td width="1%" valign="top">&nbsp;</td>
                        <td width="64%" valign="top" align="right">
                        <table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border" >
                            <tr>                                                      
                              <td  align="center">
                              <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                  <tbody>
                                  
                                    <tr>
                                      <td colspan="3" height="10"></td>
                                    </tr>
                                       <tr>
                                       <td>
                                       <div>
                                       <form name="mydeposit" method="post"> 
                                       <table style="width: 90%;" border="0" cellspacing="0" cellpadding="0" class="mydata_tabl">              
                                                                                                             
                                     <tr> 
                                                                        
                                      <td width="15%"></td>
                                      <td align="left" width="40%" height="25"><strong>Portal Admin Name </strong></td>
                                      <td align="left" width="45%"><%=username %></td>
                                    </tr>
                                    <tr>
                                      <td></td>
                                      <td align="left" height="25"><strong>Current Balance Amount</strong></td>
                                      <td align="left"><%=bean.getAvailableBalanceAmount() %></td>
                                    </tr>
                                    <tr>
                                      <td></td>
                                      <td align="left" height="25"><strong>Request for Amount</strong></td>
                                      <td align="left"><input type="text"  class="small" name="AmountToCredit" onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                    </tr>
                                    <tr>
                                      <td></td>
                                      <td height="25" align="left"><strong>Payment Date</strong></td>
                                      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                          <tr>
                                          <td>                                            
                                          <input type="text" id="datepicker" name="DepositDate" readonly="readonly" />
                                          </td>
                                          </tr>
                                        </table></td>
                                    </tr>
                                    <tr>
                                      <td></td>
                                      <td height="25" align="left"><strong>Payment Mode</strong></td>
                                      <td align="left"><select id="riskID" name="ModeofPayment" onchange="showDiv()" >
                                          <option value="select">Select</option>
                                          <!--<option value="Cash on Desk">Cash on Desk</option>-->
                                          <option value="Cash in Bank">Cash in Bank</option>
                                          <option value="Cheque/DD">Cheque/DD</option>
                                          <option value="Online E-Transfer">Online E-Transfer</option>
                                          <option value="NEFT/RTGS">NEFT/RTGS</option>
                                          </select></td>                        
                                    </tr>
                                    <tr>
                                    <td align="left" height="25" align="left"></td>
                                    <td align="left" height="25" align="left"></td>
                                    <td align="left" style="width:200px; font-size: 18px;color: red;"><span id="errField"></span>
                                    </td></tr>
                                    </table>
                                     </form>
                                    </div>
                                    </td>
                                    </tr>
                                  
                                    <tr>
                                      <td colspan="3"><div id="d0" style="display:none"></div>
                                      
                                        <div id="cashdesk" style="display:none">                            
                                        <form name="CashOnDesk" method="post" enctype="multipart/form-data">
                                          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                          
                                            <tr>
                                              <td align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                  <tr>
                                                    <td height="25" align="center" class="bg_color">Cash on Desk</td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td ><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="mydata_tabl">
                                                  <tr>
                                                    <td width="10%"></td>
                                                    <td align="left" height="25" width="40%"><strong>Payer Name</strong></td>
                                                    <td align="left"  width="45%"><input type="text"  class="small" name="DepositerName" maxlength="20"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Payment Time</strong></td>
                                                    <td align="left"><select class="small" name="DepositTime" >
                                                        <option value="5">5:00:00 AM</option>
                                                        <option value="6">6:00:00 AM</option>
                                                        <option value="7">7:00:00 AM</option>
                                                        <option value="8">8:00:00 AM</option>
                                                        <option value="9">9:00:00 AM</option>
                                                        <option value="10">10:00:00 AM</option>
                                                        <option value="11" selected="selected">11:00:00 AM</option>
                                                        <option value="12">12:00:00 PM</option>
                                                        <option value="13">1:00:00 PM</option>
                                                        <option value="14">2:00:00 PM</option>
                                                        <option value="15">3:00:00 PM</option>
                                                        <option value="16">4:00:00 PM</option>
                                                        <option value="17">5:00:00 PM</option>
                                                        <option value="18">6:00:00 PM</option>
                                                        <option value="19">7:00:00 PM</option>
                                                        <option value="20">8:00:00 PM</option>
                                                        <option value="21">9:00:00 PM</option>
                                                        <option value="22">10:00:00 PM</option>
                                                        <option value="23">11:00:00 PM</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Receiver Name</strong></td>
                                                    <td align="left"><input type="text" class="small" name="ReceiverName" maxlength="30"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Receiver Office Location</strong></td>
                                                    <td align="left"><input type="text" class="small" name="ReceiverOfficeLocation" maxlength="30"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Reciept Number</strong></td>
                                                    <td align="left"><input type="text"  class="small" name="RecieptNo" maxlength="10" onkeypress="return digitonly(this,event)"/></td>
                                                  </tr>
                                                  
                                                  <tr align="left">
                                                    <td></td>
                                                    <td height="25" align="left"><strong>Remarks</strong></td>
                                                    <td>
													  <textarea name="remark"   class="txt" onKeyDown="textCounter(this.form.remark,this.form.remLen2,165);" onKeyUp="textCounter(this.form.remark,this.form.remLen,165);"></textarea>
													<br>
                                                     <input style="width:30px" readonly="readonly" type="text" name="remLen2" size=3 maxlength=3 value="165">characters&nbsp;left 
                                                  </span>
													  
													  
													  </td>
                                                  </tr>
                                                  <tr><td colspan="2"></td>
                                                  <td align="left" valign="middle" height="40">
											
										        	<input class="cls_btn"  name="CashObDesk" type="button" onclick="submitFormCashOnDesk()" value="Submit">
									      		  </td></tr>
                                                </table></td>
                                            </tr>
                                          </table>
                                           </form> 
                                        </div>
                                                                             
                                        
                                       
                                        <div id="cashbank" style="display:none">
                                         <form name="CashOnBank" method="post" enctype="multipart/form-data" >
                                          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td align="center" ><table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                  <tr>
                                                    <td height="25" align="center" class="bg_color">Cash in Bank</td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td ><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="mydata_tabl">
                                                  <tr>
                                                    <td width="10%"></td>
                                                    <td align="left" height="25" width="27%"><strong>Depositer Name</strong></td>
                                                    <td align="left"  width="45%"><input type="text"  class="small" name="DepositerName" /></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Deposit Time</strong></td>
                                                    <td align="left"><select  class="small" name="DepositTime" >
                                                    <option></option>
                                                         <option value="5">5:00:00 AM</option>
                                                        <option value="6">6:00:00 AM</option>
                                                        <option value="7">7:00:00 AM</option>
                                                        <option value="8">8:00:00 AM</option>
                                                        <option value="9">9:00:00 AM</option>
                                                        <option value="10">10:00:00 AM</option>
                                                        <option value="11" selected="selected">11:00:00 AM</option>
                                                        <option value="12">12:00:00 PM</option>
                                                        <option value="13">1:00:00 PM</option>
                                                        <option value="14">2:00:00 PM</option>
                                                        <option value="15">3:00:00 PM</option>
                                                        <option value="16">4:00:00 PM</option>
                                                        <option value="17">5:00:00 PM</option>
                                                        <option value="18">6:00:00 PM</option>
                                                        <option value="19">7:00:00 PM</option>
                                                        <option value="20">8:00:00 PM</option>
                                                        <option value="21">9:00:00 PM</option>
                                                        <option value="22">10:00:00 PM</option>
                                                        <option value="23">11:00:00 PM</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>Receiving Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select class="small" name="RecievingBankName" onchange="inputData1()">
                                                       <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <option value="State Bank of India">State Bank of India</option>
                                                        <option value="HDFC Bank">HDFC Bank</option>
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="cbOther">Other</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Depositing Location </strong></td>
                                                    <td align="left"><input type="text"  class="small" name="DepositLocation" maxlength="30"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Depositing Branch TID </strong></td>
                                                    <td align="left"><input type="text" class="small" name="BankTranId" onkeypress="return digitonly(this,event)" maxlength="20" /></td>
                                                  </tr>                                                 
                                                  <tr align="left">
                                                    <td></td>
                                                    <td height="25"><strong>Remarks</strong></td>
                                                   <td>
													  <textarea name="remark"   class="txt"  onKeyDown="textCounter(this.form.remark,this.form.remLen2,165);" onKeyUp="textCounter(this.form.remark,this.form.remLen,165);"></textarea>
													<br>
                                                     <input style="width:30px"  readonly="readonly" type="text" name="remLen2" size=3 maxlength=3 value="165">characters&nbsp;left 
                                                  </span>											  
													  
													  </td>
                                                  </tr>
                                                  <tr><td colspan="2"></td><td align="left" height="40" valign="middle">
											
										        <input name="CashObDesk" type="button" onclick="submitFormCashInBank()" value="Submit" class="cls_btn">
									        </td></tr>
                                                </table></td>
                                            </tr>
                                          </table>
                                          </form>
                                        </div>
                                        
                                        
                                        
                                        <div id="checque" style="display:none">
                                        <form name="checque" method="post" >
                                          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                                            <tr>
                                              <td  align="center"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                  <tr>
                                                    <td height="25" align="center" class="bg_color">Cheque/DD</td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td ><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="mydata_tabl">
                                                  <tr>
                                                    <td width="10%"></td>
                                                    <td align="left" height="25" width="27%"><strong>Depositer Name</strong></td>
                                                    <td align="left"  width="45%"><input type="text" class="small" name="DepositerName"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Deposit Time</strong></td>
                                                    <td align="left"><select class="small" name="DepositTime">
                                                        <option></option>
                                                         <option value="5">5:00:00 AM</option>
                                                        <option value="6">6:00:00 AM</option>
                                                        <option value="7">7:00:00 AM</option>
                                                        <option value="8">8:00:00 AM</option>
                                                        <option value="9">9:00:00 AM</option>
                                                        <option value="10">10:00:00 AM</option>
                                                        <option value="11" selected="selected">11:00:00 AM</option>
                                                        <option value="12">12:00:00 PM</option>
                                                        <option value="13">1:00:00 PM</option>
                                                        <option value="14">2:00:00 PM</option>
                                                        <option value="15">3:00:00 PM</option>
                                                        <option value="16">4:00:00 PM</option>
                                                        <option value="17">5:00:00 PM</option>
                                                        <option value="18">6:00:00 PM</option>
                                                        <option value="19">7:00:00 PM</option>
                                                        <option value="20">8:00:00 PM</option>
                                                        <option value="21">9:00:00 PM</option>
                                                        <option value="22">10:00:00 PM</option>
                                                        <option value="23">11:00:00 PM</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>Bank Name as on Cheque/DD</strong></td>
                                                    <td align="left"  width="45%"><select class="small" name="Chequebankname">
                                                        <option value="Allahabad Bank">Allahabad Bank</option>
                                                        <option value="Andhra Bank">Andhra Bank</option>
                                                        <option value="Bank of Baroda">Bank of Baroda</option>
                                                        <option value="Bank of India">Bank of India</option>
                                                        <option value="Bank of Maharashtra">Bank of Maharashtra</option>
                                                        <option value="Canara Bank">Canara Bank</option>
                                                        <option value="Central Bank of India">Central Bank of India</option>
                                                        <option value="Corporation Bank">Corporation Bank</option>
                                                        <option value="Dena Bank">Dena Bank</option>
                                                        <option value="IDBI Bank">IDBI Bank</option>
                                                        <option value="Indian Bank">Indian Bank</option>
                                                        <option value="Indian Overseas Bank">Indian Overseas Bank</option>
                                                        <option value="Oriental Bank of Commerce">Oriental Bank of Commerce</option>
                                                        <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <option value="Punjab & Sind Bank">Punjab & Sind Bank</option>
                                                        <option value="Syndicate Bank">Syndicate Bank</option>
                                                        <option value="Union Bank of India">Union Bank of India</option>
                                                        <option value="UCO Bank">UCO Bank</option>
                                                        <option value="United Bank of India">United Bank of India</option>
                                                        <option value="Vijaya Bank">Vijaya Bank</option>
                                                        <option value="State Bank of India">State Bank of India</option>
                                                        <option value="State Bank Bikaner & Jaipur">State Bank Bikaner & Jaipur</option>
                                                        <option value="State Bank of Hyderabad">State Bank of Hyderabad</option>
                                                        <option value="State Bank of Mysore">State Bank of Mysore</option>
                                                        <option value="State Bank of Patiala">State Bank of Patiala</option>
                                                        <option value="State Bank of Travancore">State Bank of Travancore</option>
                                                        <option value="Axis Bank">Axis Bank</option>
                                                        <option value="HDFC Bank">HDFC Bank</option>
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="Kotak Mahindra Bank">Kotak Mahindra Bank</option>
                                                        <option value="Karnataka Bank">Karnataka Bank</option>
                                                        <option value="Yes Bank">Yes Bank</option>
                                                        <option value="IndusInd Bank">IndusInd Bank</option>
                                                        <option value="The Nainital Bank Ltd">The Nainital Bank Ltd</option>
                                                        <option value="ING Vysya Bank">ING Vysya Bank</option>
                                                        <option value="South Indian Bank">South Indian Bank</option>
                                                        <option value="">Other</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Cheque/DD Number</strong></td>
                                                    <td align="left"><input type="text"  class="small" name="ChequeDDNo" onkeypress="return digitonly(this,event)"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Cheque/DD Date</strong></td>
                                                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                         <tr>
                                            <td align="left"><input type="text" name="Chequedate"  readonly="readonly"  /></td>
                                          </tr>
                                                      </table></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>Receiving Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select class="small" name="RecievingBankName" onchange="inputData2()">
                                                       <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <!-- <option value="State Bank of India">State Bank of India</option>
                                                        <option value="HDFC Bank">HDFC Bank</option> -->
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="cbOther">Other</option>
                                                      </select></td>
                                                  </tr>
                                                  
                                                  
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Depositing Location </strong></td>
                                                    <td align="left"><input type="text" class="small" name="DepositLocation"/></td>
                                                  </tr>                                                                                                
                                                  <tr align="left">                                                    <td></td>
                                                  
                                                    <td height="25"><strong>Remarks</strong></td>
                                                    <td>
													  <textarea name="remark"   class="txt" onKeyDown="textCounter(this.form.remark,this.form.remLen2,165);" onKeyUp="textCounter(this.form.remark,this.form.remLen,165);"></textarea>
													<br>
                                                     <input style="width:30px"  readonly="readonly" type="text" name="remLen2" size=3 maxlength=3 value="165">characters&nbsp;left 
                                                  </span>					  
													  </td>
                                                  </tr>
                                                  <tr><td colspan="2"></td><td align="left" height="40" valign="middle">
											
										        <input  name="CashObDesk" type="button"  onclick="submitFormChecque()" value="Submit"  class="cls_btn">
									        </td></tr>
                                                </table></td>
                                            </tr>
                                          </table>
                                          </form>
                                        </div>
                                        
                                        <div id="etransfer" style="display:none">
                                        <form name="etransaferForm" method="post">
                                          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td align="center" ><table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                  <tr>
                                                    <td height="25" align="center"  class="bg_color">Online E-Transfer</td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td ><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="mydata_tabl">
                                                  <tr>
                                                    <td width="10%"></td>
                                                    <td align="left" height="25" width="27%"><strong>Transferee Name</strong></td>
                                                    <td align="left"  width="45%"><input type="text"  class="small" name="DepositerName" /></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transferee A/C No.</strong></td>
                                                    <td align="left"><input type="text"  class="small" name="RecievingBankAccNo" onkeypress="return digitonly(this,event)" value=""/></td>
                                                  </tr>
                                                  
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transfer Time</strong></td>
                                                    <td align="left"><select  class="small" name="DepositTime" >
                                                     <option></option>
                                                        <option value="5">5:00:00 AM</option>
                                                        <option value="6">6:00:00 AM</option>
                                                        <option value="7">7:00:00 AM</option>
                                                        <option value="8">8:00:00 AM</option>
                                                        <option value="9">9:00:00 AM</option>
                                                        <option value="10">10:00:00 AM</option>
                                                        <option value="11" selected="selected">11:00:00 AM</option>
                                                        <option value="12">12:00:00 PM</option>
                                                        <option value="13">1:00:00 PM</option>
                                                        <option value="14">2:00:00 PM</option>
                                                        <option value="15">3:00:00 PM</option>
                                                        <option value="16">4:00:00 PM</option>
                                                        <option value="17">5:00:00 PM</option>
                                                        <option value="18">6:00:00 PM</option>
                                                        <option value="19">7:00:00 PM</option>
                                                        <option value="20">8:00:00 PM</option>
                                                        <option value="21">9:00:00 PM</option>
                                                        <option value="22">10:00:00 PM</option>
                                                        <option value="23">11:00:00 PM</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>Transferee Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select  class="small" name="DepositBankName">
                                                        <option value="Allahabad Bank">Allahabad Bank</option>
                                                        <option value="Andhra Bank">Andhra Bank</option>
                                                        <option value="Bank of Baroda">Bank of Baroda</option>
                                                        <option value="Bank of India">Bank of India</option>
                                                        <option value="Bank of Maharashtra">Bank of Maharashtra</option>
                                                        <option value="Canara Bank">Canara Bank</option>
                                                        <option value="Central Bank of India">Central Bank of India</option>
                                                        <option value="Corporation Bank">Corporation Bank</option>
                                                        <option value="Dena Bank">Dena Bank</option>
                                                        <option value="IDBI Bank">IDBI Bank</option>
                                                        <option value="Indian Bank">Indian Bank</option>
                                                        <option value="Indian Overseas Bank">Indian Overseas Bank</option>
                                                        <option value="Oriental Bank of Commerce">Oriental Bank of Commerce</option>
                                                        <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <option value="Punjab & Sind Bank">Punjab & Sind Bank</option>
                                                        <option value="Syndicate Bank">Syndicate Bank</option>
                                                        <option value="Union Bank of India">Union Bank of India</option>
                                                        <option value="UCO Bank">UCO Bank</option>
                                                        <option value="United Bank of India">United Bank of India</option>
                                                        <option value="Vijaya Bank">Vijaya Bank</option>
                                                        <option value="State Bank of India">State Bank of India</option>
                                                        <option value="State Bank Bikaner & Jaipur">State Bank Bikaner & Jaipur</option>
                                                        <option value="State Bank of Hyderabad">State Bank of Hyderabad</option>
                                                        <option value="State Bank of Mysore">State Bank of Mysore</option>
                                                        <option value="State Bank of Patiala">State Bank of Patiala</option>
                                                        <option value="State Bank of Travancore">State Bank of Travancore</option>
                                                        <option value="Axis Bank">Axis Bank</option>
                                                        <option value="HDFC Bank">HDFC Bank</option>
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="Kotak Mahindra Bank">Kotak Mahindra Bank</option>
                                                        <option value="Karnataka Bank">Karnataka Bank</option>
                                                        <option value="Yes Bank">Yes Bank</option>
                                                        <option value="IndusInd Bank">IndusInd Bank</option>
                                                        <option value="The Nainital Bank Ltd">The Nainital Bank Ltd</option>
                                                        <option value="ING Vysya Bank">ING Vysya Bank</option>
                                                        <option value="South Indian Bank">South Indian Bank</option>
                                                        <option value="">Other</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transfer Date</strong></td>
                                                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>                                            
                                           <td align="left"><input type="text" name="DepositDate" readonly="readonly"/></td>
                                          </tr>
                                                      </table></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transfer TID</strong></td>
                                                    <td align="left"><input type="text" class="small" name="BankTranId"  /></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transaction Reference No.</strong></td>
                                                    <td align="left"><input type="text" class="small" name="RefrenceId"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>Transferred to Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select class="small" name="RecievingBankName" onchange="inputData3()">
                                                       <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <!-- <option value="State Bank of India">State Bank of India</option>
                                                        <option value="HDFC Bank">HDFC Bank</option> -->
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="cbOther">Other</option>
                                                      </select></td>
                                                  </tr>
                                               
                                                  <tr align="left">
                                                    <td></td>
                                                    <td height="25"><strong>remark</strong></td>
                                                    <td>
													  <textarea name="remark"   class="txt" onKeyDown="textCounter(this.form.remark,this.form.remLen2,165);" onKeyUp="textCounter(this.form.remark,this.form.remLen,165);"></textarea>
													<br>
                                                     <input style="width:30px" readonly="readonly" type="text" name="remLen2" size=3 maxlength=3 value="165">characters&nbsp;left 
                                                  </span>
													  
													  
													  </td>
                                                  </tr>
                                                  <tr><td colspan="2"></td><td align="left" height="40" valign="middle">
											
										        <input  name="CashObDesk" type="button" onclick="submitFormOnline()" value="Submit" class="cls_btn">
									        </td></tr>
                                                </table></td>
                                            </tr>
                                          </table>
                                          </form>
                                        </div>
                                        
                                        <div id="Neft" style="display:none">
                                        <form name="NeftForm" method="post">
                                          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                                            <tr>
                                              <td align="center" >
                                              <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                  <tr>
                                                    <td height="25" align="center" class="bg_color">NEFT/RTGS</td>
                                                  </tr>
                                                </table></td>
                                            </tr>
                                            <tr>
                                              <td ><table width="100%" border="0" align="center" cellpadding="2" cellspacing="0" class="mydata_tabl">
                                                  <tr>
                                                    <td width="10%"></td>
                                                    <td align="left" height="25" width="27%"><strong>NEFT/RTGS Sender Name</strong></td>
                                                    <td align="left"  width="45%"><input type="text"  class="small" name="DepositerName"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>NEFT/RTGS Sender A/C No.</strong></td>
                                                    <td align="left"><input type="text" class="small" name="RecievingBankAccNo" maxlength="20" onkeypress="return digitonly(this,event)"/></td>
                                                  </tr>
                                                  
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>NEFT/RTGS Time</strong></td>
                                                    <td align="left"><select  class="small" name="DepositTime">
                                                         <option></option>
                                                        <option value="5">5:00:00 AM</option>
                                                        <option value="6">6:00:00 AM</option>
                                                        <option value="7">7:00:00 AM</option>
                                                        <option value="8">8:00:00 AM</option>
                                                        <option value="9">9:00:00 AM</option>
                                                        <option value="10">10:00:00 AM</option>
                                                        <option value="11" selected="selected">11:00:00 AM</option>
                                                        <option value="12">12:00:00 PM</option>
                                                        <option value="13">1:00:00 PM</option>
                                                        <option value="14">2:00:00 PM</option>
                                                        <option value="15">3:00:00 PM</option>
                                                        <option value="16">4:00:00 PM</option>
                                                        <option value="17">5:00:00 PM</option>
                                                        <option value="18">6:00:00 PM</option>
                                                        <option value="19">7:00:00 PM</option>
                                                        <option value="20">8:00:00 PM</option>
                                                        <option value="21">9:00:00 PM</option>
                                                        <option value="22">10:00:00 PM</option>
                                                        <option value="23">11:00:00 PM</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td ></td>
                                                    <td align="left" height="25" ><strong>NEFT/RTGS Sent via Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select  class="small" name="DepositBankName">
                                                        <option value="Allahabad Bank">Allahabad Bank</option>
                                                        <option value="Andhra Bank">Andhra Bank</option>
                                                        <option value="Bank of Baroda">Bank of Baroda</option>
                                                        <option value="Bank of India">Bank of India</option>
                                                        <option value="Bank of Maharashtra">Bank of Maharashtra</option>
                                                        <option value="Canara Bank">Canara Bank</option>
                                                        <option value="Central Bank of India">Central Bank of India</option>
                                                        <option value="Corporation Bank">Corporation Bank</option>
                                                        <option value="Dena Bank">Dena Bank</option>
                                                        <option value="IDBI Bank">IDBI Bank</option>
                                                        <option value="Indian Bank">Indian Bank</option>
                                                        <option value="Indian Overseas Bank">Indian Overseas Bank</option>
                                                        <option value="Oriental Bank of Commerce">Oriental Bank of Commerce</option>
                                                        <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <option value="Punjab & Sind Bank">Punjab & Sind Bank</option>
                                                        <option value="Syndicate Bank">Syndicate Bank</option>
                                                        <option value="Union Bank of India">Union Bank of India</option>
                                                        <option value="UCO Bank">UCO Bank</option>
                                                        <option value="United Bank of India">United Bank of India</option>
                                                        <option value="Vijaya Bank">Vijaya Bank</option>
                                                        <option value="State Bank of India">State Bank of India</option>
                                                        <option value="State Bank Bikaner & Jaipur">State Bank Bikaner & Jaipur</option>
                                                        <option value="State Bank of Hyderabad">State Bank of Hyderabad</option>
                                                        <option value="State Bank of Mysore">State Bank of Mysore</option>
                                                        <option value="State Bank of Patiala">State Bank of Patiala</option>
                                                        <option value="State Bank of Travancore">State Bank of Travancore</option>
                                                        <option value="Axis Bank">Axis Bank</option>
                                                        <option value="HDFC Bank">HDFC Bank</option>
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="Kotak Mahindra Bank">Kotak Mahindra Bank</option>
                                                        <option value="Karnataka Bank">Karnataka Bank</option>
                                                        <option value="Yes Bank">Yes Bank</option>
                                                        <option value="IndusInd Bank">IndusInd Bank</option>
                                                        <option value="The Nainital Bank Ltd">The Nainital Bank Ltd</option>
                                                        <option value="ING Vysya Bank">ING Vysya Bank</option>
                                                        <option value="South Indian Bank">South Indian Bank</option>
                                                        <option value="">Other</option>
                                                        <option>Other</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>NEFT/RTGS Date</strong></td>
                                                    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                          <td align="left"><input type="text" name="DepositDate" class="tcal" value="<%=curdate %>" readonly="readonly" /></td>
                                                        </tr>
                                                      </table></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transfer TID</strong></td>
                                                    <td align="left"><input type="text" class="small" name="BankTranId" onkeypress="return digitonly(this,event)"/></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>Transaction Reference No.</strong></td>
                                                    <td align="left"><input type="text"  class="small" name="RefrenceId" /></td>
                                                  </tr>
                                                  <tr>
                                                    <td width="15%"></td>
                                                    <td align="left" height="25" width="40%"><strong>NEFT/RTGS Sent to Bank Name</strong></td>
                                                    <td align="left"  width="45%"><select class="small" name="RecievingBankName" onchange="inputData4()">
                                                       <option value="Punjab National Bank">Punjab National Bank</option>
                                                        <!-- <option value="State Bank of India">State Bank of India</option>
                                                        <option value="HDFC Bank">HDFC Bank</option> -->
                                                        <option value="ICICI Bank">ICICI Bank</option>
                                                        <option value="cbOther">Other</option>
                                                      </select></td>
                                                  </tr>
                                                  <tr>
                                                    <td></td>
                                                    <td align="left" height="25"><strong>NEFT/RTGS Benifishary Name</strong></td>
                                                    <td align="left"><input type="text" class="small" name="ReceiverName"/></td>
                                                  </tr>
                                                  
                                                  
                                                  
                                                  <tr align="left">
                                                    <td></td>
                                                    <td height="25"><strong>Remarks</strong></td>
                                                    <td>
													  <textarea name="remark"   class="txt" onKeyDown="textCounter(this.form.remark,this.form.remLen2,165);" onKeyUp="textCounter(this.form.remark,this.form.remLen,165);"></textarea>
													<br>
                                                     <input readonly="readonly" style="width:30px" type="text" name="remLen2" size=3 maxlength=3 value="165" />characters&nbsp;left 
                                                  </span>
													  
													  
													  </td>
                                                  </tr>
                                                   <tr><td colspan="2"></td><td align="left" height="40" valign="middle">
											
										        <input  name="CashObDesk" type="button" onclick="submitFormNEFT()" value="Submit" class="cls_btn" />
									        </td></tr>
                                                </table></td>
                                            </tr>
                                          </table>
                                          </form>
                                        </div>
                                       
                                       </td>
                                    </tr>                                    
                                  </tbody>
                                </table></td>
                            </tr>
                          </table>
                          </td>
                      </tr>
                    </table>
                    </td>
                </tr>
              </table>
              </td>
            </tr> 
            
            
             <tr>
              <td colspan="4">
              <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style="margin-bottom:20px;">
                                        <tbody>
                                         <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                            <td align="center"><strong style="color:#FFF;">S.N.</strong></td>
                                            <td align="center"><strong style="color:#FFF;">Date</strong></td>
                                            <td align="center"><strong style="color:#FFF;">Time</strong></td>
                                            <td align="center"><strong style="color:#FFF;">Amount</strong></td>
                                            <td align="center"><strong style="color:#FFF;">Mode Of Payment</strong></td>
                                            <td align="center" style="border-right:1px solid #930;"><strong style="color:#FFF;">Request Status</strong> </td>
                                          </tr>
                                   
                                          <tr class="hd" align="center" style="background:#fff; height:30px;">
                                            <td align="center">1</td>
                                            <td align="center">2013-07-24</td>
                                            <td align="center">00:47:10</td>
                                            <td align="center">10000.00</td>
                                            <td align="center">Cash In Bank</td>
                                            <td align="center" style="border-right:1px solid #930;">Cash In Bank</td>
                                          </tr>
                                        
										  
                                        
                                          
                                        </tbody>
                                      </table>
              </td>
            </tr>  
                      
          </table></td>
      </tr>
    </table></td>
</tr>
<tr>
  <td width="100%" valign="top" height="88" align="center"></td>
</tr>
<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %>
</td>
</tr>
</table>
</body>
</html>

<script type="text/javascript">
// code for risk dropdown 
function showDiv()
{

var mode=document.mydeposit.ModeofPayment.value;


if(mode=="Cash on Desk")
{
document.getElementById("cashdesk").style.display="";
document.getElementById("cashbank").style.display="none";
document.getElementById("checque").style.display="none";
document.getElementById("etransfer").style.display="none";
document.getElementById("Neft").style.display="none";
}
if(mode=="Cash in Bank")
{
document.getElementById("cashdesk").style.display="none";
document.getElementById("cashbank").style.display="";
document.getElementById("checque").style.display="none";
document.getElementById("etransfer").style.display="none";
document.getElementById("Neft").style.display="none";
}
if(mode=="Cheque/DD")
{
document.getElementById("cashdesk").style.display="none";
document.getElementById("cashbank").style.display="none";
document.getElementById("checque").style.display="";
document.getElementById("etransfer").style.display="none";
document.getElementById("Neft").style.display="none";
}
if(mode=="Online E-Transfer")
{
document.getElementById("cashdesk").style.display="none";
document.getElementById("cashbank").style.display="none";
document.getElementById("checque").style.display="none";
document.getElementById("etransfer").style.display="";
document.getElementById("Neft").style.display="none";
}
if(mode=="NEFT/RTGS")
{
document.getElementById("cashdesk").style.display="none";
document.getElementById("cashbank").style.display="none";
document.getElementById("checque").style.display="none";
document.getElementById("etransfer").style.display="none";
document.getElementById("Neft").style.display="";
}
if(mode=="select")
{
document.getElementById("cashdesk").style.display="none";
document.getElementById("cashbank").style.display="none";
document.getElementById("checque").style.display="none";
document.getElementById("etransfer").style.display="none";
document.getElementById("Neft").style.display="none";
}
}

function submitFormCashOnDesk()
{
		if(submitForm())
	{
		
	var  depositMode=document.mydeposit.ModeofPayment.value;
	var Amount= document.mydeposit.AmountToCredit.value;
	var payDate= document.mydeposit.DepositDate.value;		
	document.CashOnDesk.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount;
	//alert(document.CashOnDesk.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount);
	document.CashOnDesk.submit();
		}
	 
}
function submitFormCashInBank()
{
  
	if(submitForm())
	{
		
	var  depositMode=document.mydeposit.ModeofPayment.value;
	var Amount= document.mydeposit.AmountToCredit.value;
	var payDate= document.mydeposit.DepositDate.value;
	
	document.CashOnBank.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount;
	//alert(document.CashOnBank.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount);
	document.CashOnBank.submit();
	}	
	 
}



function submitFormChecque()
{
 
	if(submitForm())
	{
	  
	var  depositMode=document.mydeposit.ModeofPayment.value;
	var Amount= document.mydeposit.AmountToCredit.value;
	var payDate= document.mydeposit.DepositDate.value;
	
	document.checque.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount;
	//alert(document.checque.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount);
	document.checque.submit();
	}
	
	 
}


function submitFormOnline()
{
  	if(submitForm())
	{
		
	var  depositMode=document.mydeposit.ModeofPayment.value;
	var Amount= document.mydeposit.AmountToCredit.value;
	var payDate= document.mydeposit.DepositDate.value;
	
	document.etransaferForm.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount;
	//alert(document.etransaferForm.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount);
	document.etransaferForm.submit();
	}
	
	 
}

function submitFormNEFT()
{
    	if(submitForm())
	{
			var  depositMode=document.mydeposit.ModeofPayment.value;
	var Amount= document.mydeposit.AmountToCredit.value;
	var payDate= document.mydeposit.DepositDate.value;
	
	document.NeftForm.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount;
	//alert(document.NeftForm.action="depositInfo.action?Paymentdate="+payDate+"&depositMode="+depositMode+"&depositAmount="+Amount);
	document.NeftForm.submit();
	}
	
	 
}

function submitForm()
{

var mode=document.mydeposit.ModeofPayment.value;
if(mode=="select")
{
document.getElementById("errField").innerHTML='<p>Mode of Payment must be selected </p></html>';
document.mydeposit.ModeofPayment.focus();
return false;
}


var mm= document.mydeposit.AmountToCredit.value;
    if(mm<1)

		{
    			   
   document.getElementById("errField").innerHTML='You have entered wrong amount, please enter amount between Rs. 1,00 to Rs. 50,000 only.';

      document.mydeposit.AmountToCredit.focus();
         return false;
	
		}
		 if(mm<100)

		{
		   
			 document.getElementById("errField").innerHTML='<p>Amount should not be <100 </p></html>';
document.mydeposit.AmountToCredit.focus();
return false;

			

		}

	 if(mm>1000000)

		{
		   
		 document.getElementById("errField").innerHTML='<p>Amount should not be >10,00000 </p></html>';	
document.mydeposit.AmountToCredit.focus();
return false;

			

		}




//--------------complete 
if(mode=="Cash on Desk")
{

if(document.CashOnDesk.DepositerName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Depositer Name </p></html>';
document.CashOnDesk.DepositorName.focus();
return false;
}
if(document.CashOnDesk.ReceiverName.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Receiver Name </p></html>';
document.CashOnDesk.ReceiverName.focus();
return false;
}
if(document.CashOnDesk.ReceiverOfficeLocation.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Receiver office location Name </p></html>';
document.CashOnDesk.ReceiverOfficeLocation.focus();
return false;
}
if(document.CashOnDesk.RecieptNo.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Receipt no  </p></html>';
document.CashOnDesk.RecieptNo.focus();
return false;
}
if(document.CashOnDesk.remark.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Remark  </p></html>';
document.CashOnDesk.remark.focus();
return false;
}


}

//------------------------------------
if(mode=="Cash in Bank")
{
//alert(mode);
if(document.CashOnBank.DepositerName.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Depositer Name </p></html>';
document.CashOnBank.DepositerName.focus();
return false;
}
//alert("hello");
if(document.CashOnBank.RecievingBankName.value!="cbOther")
{
if(document.CashOnBank.RecieptFile.value=="")
{

return false;
}
}


if(document.CashOnBank.RecievingBankAccNo.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter RecievingBankAccNo </p></html>';
document.CashOnBank.RecievingBankAccNo.focus();
return false;
}

if(document.CashOnBank.RecievingBranchName.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter RecievingBranchName  </p></html>';
document.CashOnBank.RecievingBranchName.focus();
return false;
}
if(document.CashOnBank.DepositLocation.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter DepositLocation </p></html>';
document.transferForm.DepositLocation.focus();
return false;
}
if(document.CashOnBank.BankTranId.value=="")
{
document.getElementById("errField").innerHTML='<p>Enter BankTranId  </p></html>';
document.CashOnBank.BankTranId.focus();
return false;
}
if(document.CashOnBank.remark.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Remark  </p></html>';
document.CashOnDesk.remark.focus();
return false;
}
if(!fileTypes2())
{
	
	document.CashOnBank.RecieptFile.focus();
	return false;
}

}

//-------------------------Progress----------------
if(mode=="Cheque/DD")
{
	
if(document.checque.DepositerName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Depositer Name </p></html>';
document.checque.DepositerName.focus();
return false;
}
if(document.checque.ChequeDDNo.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Cheque/DD No </p></html>';
document.checque.ChequeDDNo.focus();
return false;
}


if(document.checque.RecievingBankAccNo.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Recieving Bank Acc No </p></html>';
document.checque.RecievingBankAccNo.focus();
return false;
}

if(document.checque.RecievingBranchName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Receiving Branch Name </p></html>';
document.checque.RecievingBranchName.focus();
return false;
}

if(document.checque.DepositLocation.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Deposit Location </p></html>';
document.checque.DepositLocation.focus();
return false;
}
if(document.checque.remark.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Remark  </p></html>';
document.checque.remark.focus();
return false;
}

}

//----------------------------
if(mode=="Online E-Transfer")
{
	
if(document.etransaferForm.DepositerName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transferee Name </p></html>';
document.etransaferForm.DepositerName.focus();
return false;
}

if(document.etransaferForm.BankTranId.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transfer TID </p></html>';
document.etransaferForm.BankTranId.focus();
return false;
}
if(document.etransaferForm.RefrenceId.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transaction Reference No.</p></html>';
document.etransaferForm.RefrenceId.focus();
return false;
}
if(document.etransaferForm.ReceiverName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transferred Benifishary Name </p></html>';
document.etransaferForm.ReceiverName.focus();
return false;
}

if(document.etransaferForm.RecievingBankAccNo.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transferred to A/C Number </p></html>';
document.etransaferForm.RecievingBankAccNo.focus();
return false;
}
if(document.etransaferForm.RecievingBranchName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transferred to Branch </p></html>';
document.etransaferForm.RecievingBranchName.focus();
return false;
}

if(document.etransaferForm.remark.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Remark  </p></html>';
document.etransaferForm.remark.focus();
return false;
}

}

//-----------------------

if(mode=="NEFT/RTGS")
{
if(document.NeftForm.DepositerName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter DNEFT/RTGS Sender Name </p></html>';
document.NeftForm.DepositerName.focus();
return false;
}


if(document.NeftForm.BankTranId.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transfer TID</p></html>';
document.NeftForm.BankTranId.focus();
return false;
}
if(document.NeftForm.RefrenceId.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter Transaction Reference No</p></html>';
document.NeftForm.RefrenceId.focus();
return false;
}
if(document.NeftForm.ReceiverName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter NEFT/RTGS Benifishary Name </p></html>';
document.etransaferForm.ReceiverName.focus();
return false;
}
if(document.NeftForm.RecievingBankAccNo.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>Enter NEFT/RTGS Sent for A/C Number</p></html>';
document.NeftForm.RecievingBankAccNo.focus();
return false;
}
if(document.NeftForm.RecievingBranchName.value.replace(/^\s+|\s+$/, '')=="")
{
	document.getElementById("errField").innerHTML='<p>NEFT/RTGS sent for Branch</p></html>';
document.NeftForm.RecievingBranchName.focus();
return false;
}

if(document.NeftForm.remark.value.replace(/^\s+|\s+$/, '')=="")
{
document.getElementById("errField").innerHTML='<p>Enter Remark  </p></html>';
document.CashOnDesk.remark.focus();
return false;
}
alert("NEFT transfer normally takes place on the same day or at the most the next working day depending upon the time of requesting / effecting such funds transfers. The customer should confirm this aspect from his bank at the time of requesting the funds transfer.");
}

return confirm("Do You Want TO Continue");

}






function textCounter(field, countfield, maxlimit)
 {
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else 
countfield.value = maxlimit - field.value.length;
}





function inputData1()
{
var bankName=document.CashOnBank.RecievingBankName.value;
if(!(bankName=="cbOther"))
{
document.getElementById("attachment").style.display="";
}
if(bankName=="cbOther")
{
document.getElementById("attachment").style.display="none";

document.getElementById("other1").style.display="";
document.getElementById("other11").style.display="";

}
else
{
document.getElementById("other1").style.display="none";
document.getElementById("other11").style.display="none";


}
}


function inputData2()
{

var bankName=document.checque.BankName.value;
if(bankName=="checOther")
{
document.getElementById("other2").style.display="";
document.getElementById("other22").style.display="";

}
else
{
document.getElementById("other2").style.display="none";
document.getElementById("other22").style.display="none";

}
}


function inputData3()
{
var bankName=document.etransaferForm.BankName.value;
if(bankName=="eOther")
{
document.getElementById("other3").style.display="";
document.getElementById("other33").style.display="";

}
else
{
document.getElementById("other3").style.display="none";
document.getElementById("other33").style.display="none";


}
}

function inputData4()
{
var bankName=document.NeftForm.BankName.value;

if(bankName=="NeftOther")
{
document.getElementById("other4").style.display="";
document.getElementById("other5").style.display="";

}
else
{
document.getElementById("other4").style.display="none";
document.getElementById("other5").style.display="none";

}
}
function fileTypes()
{ 
	
  var id_value = document.CashOnDesk.RecieptFile.value;
  var valid_extensions = /(.jpg|.jpeg|.gif|.png|.pdf)$/i;   
  if(id_value == '')
  {
	  document.getElementById("errField").innerHTML='<p>Invalid file Plz select jpg/jpeg/gif/png or pdf file </p></html>';
	  document.CashOnDesk.RecieptFile.focus();
	  return false; 
  }
  else{
	  document.getElementById("errField").innerHTML="";
  }

	  
  if(!valid_extensions.test(id_value))
  {
	 document.getElementById("errField").innerHTML='<p>Invalid file Plz select jpg/jpeg/gif/png or pdf file </p></html>';
	document.CashOnDesk.RecieptFile.focus();	
	return false;  
  }

 return true;
}
function fileTypes2()
{ 

	 var id_value = document.CashOnBank.RecieptFile.value;
	  var valid_extensions = /(.jpg|.jpeg|.gif|.png|.pdf)$/i;   
	  if(id_value == '')
	  {
		  document.getElementById("errField").innerHTML='<p>Invalid file Plz select jpg/jpeg/gif/png or pdf file </p></html>';
		  document.CashOnBank.RecieptFile.focus();
		  return false; 
	  }
	  else{
		  document.getElementById("errField").innerHTML="";
	  }
	  if(!valid_extensions.test(id_value))
	  {
		  document.getElementById("errField").innerHTML='<p>Invalid file Plz select jpg/jpeg/gif/png or pdf file </p></html>';
		document.CashOnBank.RecieptFile.focus();	
		return false;  
	  }
	  return true;
}
</script>