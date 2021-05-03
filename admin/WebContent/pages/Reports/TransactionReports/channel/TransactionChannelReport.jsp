<%@ page import="java.util.Vector "%>
<%@ page import="java.util.HashMap "%>
<%@ page import="java.util.* "%>
<%@ page import="java.text.DecimalFormat "%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);
String checkloginType=(String)session.getAttribute("loginType");
System.out.println("hi Manoj how are you "+checkloginType);

String flag=(String) request.getAttribute("flag");
if(flag==null)flag="N";
String showService=(String) request.getAttribute("showService");
if(showService==null)showService="NA";

int size=0;

ArrayList RechargeData =(ArrayList)request.getAttribute("RechargeData");
if (RechargeData==null){
	RechargeData=new ArrayList();
	}
int RechargeDataSize=RechargeData.size();
//System.out.println("  RechargeDataSize "+RechargeDataSize);

String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />


<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>
$(document).ready(function(){
	  
	  var now = new Date();
	  var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		
	 	var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
	  
  	    var ytoday = now.getFullYear() + '-' + mm + '-' + dd;
		var today = now.getFullYear()  + '-' + mm + '-' + dd;
	 	
		$('#datepicker').val(ytoday);
		$('#datepickers').val(today);
	  
		
	  
		 $("#datepicker, #datepickers").datepicker({
	        changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
	           defaultDate: "+1d",
	        maxDate: "today",
				
		 });         
			           
				
				
		 
	  });
  </script>



<script language="javascript">
function validateform()
{
var service=document.reportform.reportOf.value;
if(service=="select"){
	alert("Select the value Report Of");
	document.reportform.reportOf.focus();
	return false;
}
else{
document.reportform.action="reportDownload.action?param=downloadReport";
document.reportform.submit();
}
}
function validateformforView(){
var service=document.reportform.reportOf.value;
if(service=="select"){
	alert("Select the value Report Of");
	document.reportform.reportOf.focus();
	return false;
}
else{
document.reportform.action="reportDownload.action?param=viewReport";
document.reportform.submit();
}

}
</script>
</head>

<body>
	<form name="reportform" method="post">
		<table cellpadding="0" cellspacing="0" width="100%" align="center"
			border="0">
			<tr>
				<td width="100%" valign="top" align="center"><%@ include
						file="/header.jsp"%></td>
			</tr>
			<tr>
				<td align="center" width="100%" valign="top">
					<table cellpadding="0" cellspacing="0" width="90%" align="center"
						border="0" class="newbg">
						<tr>
							<td valign="top" align="center">
								<table cellpadding="0" cellspacing="0" width="100%"
									align="center" border="0">
									<tr>
										<td valign="top" align="center"
											class="rounded-corners box_heights">
											<table cellpadding="0" cellspacing="0" width="100%"
												align="center" border="0">
												<tr>
													<td height="40" align="left" valign="middle"
														class="heading_mgs">Transaction Report > Channel</td>
												</tr>
												<tr align="center">
													<td class="dyn_mgs"><%=message%></td>
												</tr>
												<tr>
													<td colspan="4">
														<table width="100%" cellspacing="0" cellpadding="0"
															align="center">
															<tr>
																<td align="center">

																	<table cellpadding="0" cellspacing="0" width="86%"
																		style="margin-bottom: 15px;" align="center"
																		id="border">
																		<tr>
																			<td colspan="4" valign="top">

																				<table cellpadding="0" cellspacing="0" width="750"
																					align="center" class="mydata_tabl">
																					<tr>
																						<td colspan="100%" height="20px"></td>
																					</tr>
																					<tr>
																						<td width="20%"></td>
																						<td width="15%"><strong>Report Type</strong></td>
																						<td width="10%">:</td>
																						<td width="35%"><select name="reportOf"
																							class="searchtext" id="select_option">
																								<option value="all" selected="selected">All Transaction</option>
																								<option value="liveRechargeTransactionReport" >Live Recharges Transaction</option>
																								<option value="liveDMRTransactionReport">Live DMR Report</option>
																								<option value="liveAepsTransactionReport">Live AePS Report</option>
																								<option value="agentAmountReport">Agent Amount Report</option>
																								<option value="agentDetailsReport">Agent Detail Report</option>
																								<option value="agentRegistrationReport">Agent Registration Report</option>
																								<option value="distributorAmountReport">DS Amount Report</option>
																								<option value="distributorDetailsReport">DS Detail Report</option>
																								
																								
																								
																								<!-- <option value="busTransactionReport">Booking Bus</option>	
																								  <option value="B2BDomFlight">Booking Domestic Flights</option>
																								  <option value="B2BInterFlight">Booking International Flights</option>
																					              <option value="bookinghotel">Booking Hotel</option>
																								  <option value="opstransaction">Ops Transaction</option> -->
																						</select></td>
																						<td>&nbsp;</td>
																					</tr>

																					<tr>
																						<td width="20%"></td>
																						<td><strong>From </strong></td>
																						<td>:</td>
																						<td><input type="text" id="datepicker"
																							name="fromDate" /></td>
																					</tr>

																					<tr>
																						<td width="20%"></td>
																						<td><strong>To </strong></td>
																						<td>:</td>
																						<td><input type="text" id="datepickers"
																							name="toDate" /></td>
																					</tr>
																					
																					<tr id="agentId" >
																						<td width="20%"></td>
																						<td><strong>Agent Id </strong></td>
																						<td>:</td>
																						<td><input type="text" value="All" placeholder="Full agent Id (AG2001) or all" name="agentId" style="text-transform: uppercase;" /></td>
																					</tr>

																					<tr>
																						<td width="20%"></td>
																						<td></td>
																						<td align="right"></td>
																						<td><input name="Input" type="button"
																							value="View" class="cls_btn"
																							onclick="validateformforView()" /> <input
																							name="Input" type="button" value="Download"
																							class="cls_btn" onclick="validateform()" /></td>
																					</tr>

																					<tr>
																						<td colspan="100%" height="20px"></td>
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
														<%if(flag.equalsIgnoreCase("Y")){%>
														<table cellspacing="0" cellpadding="0" width="90%"
															align="center" class="tbls" id="mytables">
															<tbody>
																<!--live recharge tr start-->
																<%if(showService.equalsIgnoreCase("Recharge")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent
																			ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Connection
																			Number</strong></td>
																	<td align="center"><strong>Operator</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Operator ID</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Charges</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<c:if
																		test="${fn:containsIgnoreCase(sessionScope.adminUserType,'ActivityAdmin') || fn:containsIgnoreCase(sessionScope.adminUserType,'superadmin')}">
																		<td align="center"
																			style="border-right: 1px solid #930;"><strong>Service
																				Provider</strong></td>

																	</c:if>
																</tr>
																<!--live recharge tr stop-->
																<%}%>

																<!--bus booking tr start-->
																<%if(showService.equalsIgnoreCase("BusData")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Ticket Number</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Can. Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Refund Amount</strong>
																	</td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<td align="center"
																		style="border-right: 1px solid #930;"><strong>Txn
																			Type</strong></td>
																</tr>
																<!--bus booking tr stop-->
																<%}%>

																<!--Domestic,international flight or hotel ops transaction tr start-->
																<%if(showService.equalsIgnoreCase("B2BDomFlight")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Reference No.</strong></td>
																	<td align="center"><strong>Ticket PNR</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Can. Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<!--<td align="center" style="border-right:1px solid #930;"><strong>Remark</strong> </td>-->
																</tr>
																<!--Domestic,international flight or hotel ops transaction  tr stop-->
																<%}%>


																<!--bookinghotal tr start-->
																<%if(showService.equalsIgnoreCase("bookinghotel")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Reference No.</strong></td>
																	<td align="center"><strong>Hotal Name</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Can. Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<td align="center"
																		style="border-right: 1px solid #930;"><strong>Remark</strong>
																	</td>
																</tr>
																<!--bookinghotal tr stop-->
																<%}%>

																<!--Ops Transcation tr start-->
																<%if(showService.equalsIgnoreCase("opstransaction")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Reference No.</strong></td>
																	<td align="center" width="10%"><strong>Service</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Can. Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<td align="center"
																		style="border-right: 1px solid #930;"><strong>Remark</strong>
																	</td>
																</tr>
																<!--Ops Transcation tr stop-->
																<%}%>

																<!--international flight tr start-->
																<%if(showService.equalsIgnoreCase("B2BInterFlight")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Txn Date</strong></td>
																	<td align="center"><strong>Txn Time</strong></td>
																	<td align="center"><strong>Reference No.</strong></td>
																	<td align="center"><strong>Ticket PNR</strong></td>
																	<td align="center"><strong>Txn ID</strong></td>
																	<td align="center"><strong>Txn Amount</strong></td>
																	<td align="center"><strong>Commission</strong></td>
																	<td align="center"><strong>Can. Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																	<!--   <td align="center" style="border-right:1px solid #930;"><strong>Remark</strong> </td>-->
																</tr>
																<!--international flight  tr stop-->
																<%}%>

																<!--   DMR Transaction Details    -->

																<%if(showService.equalsIgnoreCase("DMR")){%>
																<tr class="hd tabulardata" align="center">
																	<td align="center"><strong>Agent ID</strong></td>
																	<td align="center"><strong>Sender Id</strong></td>
																	<td align="center"><strong> Date</strong></td>
																	<td align="center"><strong> Time</strong></td>
																	<td align="center"><strong>Txn. Id.</strong></td>
																	<td align="center"><strong>UTR No.</strong></td>
																	<td align="center"><strong>Txn. Amount</strong></td>
																	<td align="center"><strong>Charges</strong></td>
																	<td align="center"><strong>Net Amount</strong></td>
																	<td align="center"><strong>Bene. Name</strong></td>
																	<td align="center"><strong>Bene. Bank</strong></td>
																	<td align="center"><strong>Bene. Account</strong>
																	</td>
																	<td align="center"><strong>Bene. Bank
																			IFSC</strong></td>
																	<td align="center"><strong>Txn Status</strong></td>
																</tr>
																<!--international flight  tr stop-->
																<%}%>
																<c:if test="${requestScope.RechargeData.size()>0 }">

																	<c:if
																		test="${requestScope.showService  eq 'Recharge' }">



																		<c:forEach var="RechargeData"
																			items="${requestScope.RechargeData }">


																			<tr align="center"
																				style="background: #fff; height: 25px;">
																				<td align="center">${RechargeData.agentId}</td>
																				<td align="center">${RechargeData.dor}</td>
																				<td align="center">${RechargeData.time}</td>
																				<td align="center">${RechargeData.mobno}</td>
																				<td align="center">${RechargeData.operator}</td>
																				<td align="center">${RechargeData.TepTranId}</td>
																				<td align="center">${RechargeData.amt}</td>
																				<td align="center">${RechargeData.OperatorTranId}</td>
																				<td class="comm_cls" align="center">${RechargeData.comm}</td>
																				<td align="center">${RechargeData.Service_charge1}</td>
																				<td align="center">${RechargeData.transtatus}</td>

																				<c:choose>

																					<c:when
																						test="${requestScope.showService  eq 'B2BInterFlight' &&  requestScope.showService  eq 'B2BDomFlight'}">

																					</c:when>
																					<c:otherwise>
																						<c:if
																							test="${fn:containsIgnoreCase(sessionScope.adminUserType,'ActivityAdmin') || fn:containsIgnoreCase(sessionScope.adminUserType,'superadmin')}">
																							<td align="center"
																								style="border-right: 1px solid #930;">${RechargeData.ServiceProvider}</td>

																						</c:if>
																					</c:otherwise>

																				</c:choose>
																			</tr>

																		</c:forEach>



																	</c:if>
																	<c:if test="${requestScope.showService eq 'DMR' }">

																		<c:forEach var="RechargeData"
																			items="${requestScope.RechargeData }">


																			<tr align="center"
																				style="background: #fff; height: 25px;">
																				<td align="center">${RechargeData.agentId}</td>
																				<td align="center">${RechargeData.senderId}</td>
																				<td align="center">${RechargeData.dor}</td>
																				<td align="center">${RechargeData.time}</td>
																				<td align="center">${RechargeData.tranId}</td>
																				<td align="center">${RechargeData.bankRefId}</td>
																				<td align="center">${RechargeData.reqAmount}</td>
																				<td align="center">${RechargeData.ServiceCharge}</td>
																				<td align="center">${RechargeData.netAmount}</td>
																				<td align="center">${RechargeData.beneName}</td>
																				<td align="center">${RechargeData.beneAccount}</td>
																				<td align="center">${RechargeData.beneBankName}</td>
																				<td align="center">${RechargeData.beneBankIfsc}</td>
																				<td align="center">${RechargeData.status}</td>
																			</tr>

																		</c:forEach>

																	</c:if>

																</c:if>

															</tbody>
														</table> <%}%>
													</td>
												</tr>

												<tr>
													<td colspan="4" height="30"></td>
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
				<td width="100%" valign="top" align="center" height="50px"></td>
			</tr>
			<tr>
				<td width="100%" valign="top" align="center"><%@ include
						file="/footer.jsp"%></td>
			</tr>
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
function abcd(){
	document.getElementById("view_edit").style.display='block';
}
function abcd1(){
	document.getElementById("view_edit").style.display='none';
}
</script>
<script type="text/javascript">
// code for risk dropdown
function toggleSubmit(obj){

        count=0
        while(document.getElementById("d"+count)){
        document.getElementById("d"+count).style.display="none"
        count++
        }
        document.getElementById("d"+obj.selectedIndex).style.display="block"
}
function reportforOption()
{
var report=document.reportform.reportfor.value;

if(report=="adminreport")
{

document.getElementById("r1").style.display="table-row";
document.getElementById("r2").style.display="none";
}
if(report=="egenreport")
{
 document.getElementById("r1").style.display="none";
 document.getElementById("r2").style.display="table-row";
 getAPIClient();
 }
 }
  function getAPIClient()
  {
  var xmlhttp;
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("clientID").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("post",'egenReportDownload.action?param=getApi',true);
xmlhttp.send();
}
</script>