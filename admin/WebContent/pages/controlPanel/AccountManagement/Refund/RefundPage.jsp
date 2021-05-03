<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.text.*" %>

<%
Date befDate=new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
befDate.setDate(befDate.getDate()-3);

String beforeDate=formatter.format(befDate);
Date todate = new Date();

String curdate = formatter.format(todate);
String Message=(String)request.getAttribute("message");

if(Message==null){
Message="";
}
HashMap map=(HashMap)request.getAttribute("TranData");
int size=0;
if(map==null){
size=-1;
}
else{
size=map.size();
}
int i=0;
String ipAdd=request.getRemoteAddr();

String vendorid="";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script type="text/JavaScript">

<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
  <script src="//code.jquery.com/jquery-1.9.1.js"></script>
  <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	  
	  $("#datepicker,#datepickers").datepicker({
		  
		  changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2013',
            numberOfMonths: 1,
            maxDate:0,

		  });

  });
</script>

<script type="text/javascript">
function confirmHappy()
{
	var hh=confirm("....This process will create the Distributor ID. On activation of Distributor ID, Rs.1000 will be charged from your account.");
	if (hh==true)
	{
		alert("You have successfully registration");
	}
	else
	{
		confirm("You have cancelled the submission of form.");
	}
}


function instantBillPayStatusCheck(tid){
	$('#result_td').text('');
	$('#resultLoading').show();
	$.ajax({
	    type: "GET",
	    url: "commonAction.action?param=InstantBillpayStatusCheck&REQUEST_ID="+tid,
	   	success: function (response) {
		if(response==null)
		{
			$('#resultLoading').hide();
			alert("Status not found");
			return ;
		}else{
			$('#resultLoading').hide();
			$('#result_td').text(response);
			$('#result').show();
		}
			

	},

	 failure: function (response) {
		 $('#resultLoading').hide();
	 }
	});

}
function statusCheck(tid,service,date_){
	$('#result_td').text('');
	$('#resultLoading').show();
	$.ajax({
	    type: "GET",
	    url: "commonAction.action?param=iciciStatusCheck&REQUEST_ID="+tid,
	   	success: function (response) {
		if(response==null)
		{
			$('#resultLoading').hide();
			alert("Status not found");
			return ;
		}else{
			$('#resultLoading').hide();
			$('#result_td').text(response);
			$('#result').show();
		}
			

	},

	 failure: function (response) {
		 $('#resultLoading').hide();
	 }
	});

}


</script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
  </tr>
  
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="newbg">
        <tr>
          <td width="100%"  valign="top" align="center" >
          <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="rounded-corners box_heights">
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Refund Transaction Process</td>
              </tr>
				<tr><td align="center" class="dyn_mgs"><%=Message%></td></tr>
              <tr>
			  
                <td width="100%" align="center">
                <div class="accordion" style="padding:20px; margin-bottom:40px;" id="border">
				
				
				  <table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11">
                    <tr>
                      <td>
					  <form name="SearchTran" method="post" >
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
                            <tr><td colspan="4" align="center" ></td></tr>
							<tr>
                              <td width="20%"></td>
                              <td align="left" height="30"  class="form11"><strong>Transaction No</strong></td> 
                                                            <td align="left" width="10%">:</td>
                              <td align="left"><input name="Tran_no" id="Tran_no" type="text"  value="" maxlength="50" tooltipText="Type Transaction Number in this box" onkeypress="return digitonly(this,event)"/></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="35"   class="form11"><strong>From Date</strong></td>
                              <td align="left">:</td>
                              <td align="left">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="mydata_tabl">
                                  <tr>
                                    <td align="left" valign="middle">
                                    <input name="fromDate" type="text"  size="15" readonly="readonly"  id="datepicker" value="<%=beforeDate%>" />
                                        </td>
                                  </tr>
                              </table></td>
                            </tr>
                            
							<tr>
                              <td></td>
                              <td align="left" height="35"   class="form11"><strong>To Date</strong></td>
                              <td align="left">:</td>
                              <td align="left">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="mydata_tabl">
                                  <tr>
                                    <td align="left" valign="middle">
                                    <input name="toDate" type="text"  size="15" readonly="readonly"  id="datepickers" value="<%=curdate%>"  />
                                        </td>
                                  </tr>
                              </table>
                              </td>
                            </tr>
                            
                            <tr>
                              <td></td>
                              <td align="left" height="35"   class="form11"></td>
                              <td align="left"></td>
                              <td align="left">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
                                  <tr>
                                    <td align="left" valign="middle"><input name="Submit" class="cls_btn" type="button" value="Submit" onclick="submitForm()"/>
                                        </td>
                                  </tr>
                              </table>
                              </td>
                            </tr>
                            <tr >
                            	<td align="center" colspan="4" id="result" style="display: none;"> 
                                  <div id="result_td" align="center" style="border: 1px solid #ccc;padding: 10px;font-size: 16px;font-weight: bold;width: 1000px;overflow-x: scroll;"></div>
                            	</td>
                            </tr>
                            </table>
							</form>                          
                          </td>
                    </tr>
                    
                    
					<tr>
                         <td align="center" width="100%">
                            <%if(size>0){%>
							<form name="updateTranForm" method="post">
							<div style="width: 1000px;overflow-x: scroll;">
							<table width="90%"  cellspacing="0" cellpadding="0" align="center" class="tbls"   bgcolor="#a74312">
                        		
                        		<tr class="hd tabulardata" align="center">
						
								  <td width="5%" ><strong>Agent ID</strong></td>
		                          <td width="12%" ><strong>TxnRefNo<br/>TransactionId/ReferenceId<br/></strong></td>
								  <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("activityUser")||loginUserType.equalsIgnoreCase("SuperAdmin")){ %>
								  <td width="12%" ><strong>ApiProvider</strong></td>
								  <%} %>
								  <td width="13%" ><strong>Txn Date</strong></td>
								  <td width="10%" ><strong>Connection No</strong></td>
		                          <td width="10%" ><strong>Operator</strong></td>
		                           <td width="10%" ><strong>Service</strong></td>
		                          <td width="5%" ><strong>Txn Amount</strong></td>
								  <td width="9%" ><strong>Live Status</strong></td>
								  <td width="9%" ><strong>Txn Status</strong></td>
								  <td width="9%" ><strong>Action</strong></td>
								  <td width="9%" ><strong>Check Status</strong></td>
						  
                        		</tr>
                        		<tr align="center" bgcolor="#ffffff">
                         
		                          <td><%=map.get("user_id")%></td>
								  <%
								  	vendorid=(String)map.get("USessionID");
								   if(vendorid==null){vendorid="";}
								  %>
		                          <td><%=map.get("tran_id")%><br/><%=map.get("transaction_id")%><br/><%=vendorid%></td>
								   <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("activityUser")||loginUserType.equalsIgnoreCase("SuperAdmin")){ %>
								    <td><%=map.get("ApiProvider")%></td>
								    <%} %>
		                          <td><%=map.get("date_of_recharge")%></td>
		                          <td><%=map.get("mobile_number")%></td>
		                          <td><%=map.get("mobile_operator")%></td>
		                          <td><%=map.get("service")%>  <input name="service" type="hidden" value="<%=map.get("service")%>" /></td>
								  <td><%=map.get("amount")%><input type="hidden" name="ipAdd" value="<%=ipAdd%>" /></td>
								  <% String Currstatus=(String)map.get("status"); %>
								  <td><%=Currstatus%><input type="hidden" name="status"value="<%=Currstatus%>" /></td>
								   <td><%=map.get("Tran_status")%></td>
						  

				   					<td class="text"style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;padding: 2px;">
									   <select  name="updatedStatus" style="width: 140px;">
									   <option value="selected">Select</option>
									   <option value="Refund">Refund</option>
									   <option value="Success">Success</option>
									   <%if(loginUserType.equalsIgnoreCase("activityUser") || loginUserType.equalsIgnoreCase("activityAdmin") || loginUserType.equalsIgnoreCase("superadmin")){ %>
									   <option value="SuccessRefund">SuccessRefund</option>
									   <%} %>
									   </select>
										<input type="text" name="tranRefId" placeholder="Enter Ref Id" style="width: 118px;" />   
									</td>
				   					<td>
                                         <%
                                         String service=map.get("service")+"";
                                         String ApiProvider=map.get("ApiProvider")+"";
                                         if("Billpay".equalsIgnoreCase(service) ){ %>
                                         	<input name="Submit" class="cls_btn" onclick="instantBillPayStatusCheck('<%=map.get("transaction_id") %>')" type="button" value="Check Status" />
                                         <%}else if( "ICICI".equalsIgnoreCase(ApiProvider)){ %>
                                   			<input name="Submit" class="cls_btn" onclick="statusCheck('<%=map.get("tran_id") %>','icici','<%=map.get("date_of_recharge") %>')" type="button" value="Check Status" />
                                   		<%} else{%>
                                         
                                         <%} %>
                                    </td>
								</tr>
                     
				    
                      		
						  		<tr>
						  			<td colspan="14" align="right"><input style="margin-right:16px;" class="manualButton" type="button" value="Update" onclick="updateTran('<%=map.get("tran_id")%>')" /></td>  
						  		</tr>
					  		</table>
					  		</div>
					  		</form>
					  		<%}%>
					     </td>
                   </tr>
                  </table>
				  
				
					
					
					
					
					
                  </div></td>
              </tr>
              <tr>
                <td height="20"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="left" height="23"></td>
  </tr>
  
  <tr>
    <td width="100%" valign="top" align="left"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
<script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script>
</body>
</html>

<style type="text/css">

.accordion h3 {
	border:#a74312 solid 1px; 
	padding: 7px 15px;
	margin: 0; font-size:14px; font-weight:bold; font-family:Calibri; color:#a74312;
	

}

.accordion h4 {
	
}
</style>
<script language="javascript">
function submitForm()
{
var Tran_no=document.SearchTran.Tran_no.value.replace(/^\s+|\s+$/, '');
if(Tran_no=="" ){
alert("Please enter any one ID");
return false;
}
else{
document.SearchTran.action="Refund.action?param=getTran";
document.SearchTran.submit();
}
}

function updateTran(tran_Id)
{
	
	var action=document.updateTranForm.updatedStatus.value;
	if(action=="selected"){
	alert("Please select Action to be taken");
	return false;
	}
	else{
	document.updateTranForm.action="Refund.action?param=UpdateTran&tranID="+tran_Id;
	document.updateTranForm.submit();
	}
}

function digitonly(input,evt)

{


var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) {
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) {
return true;
}

return false;
}
</script>