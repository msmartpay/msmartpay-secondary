<%@ page import = "java.util.HashMap " %>
<%@ page import = "java.util.ArrayList" %> 
<%
String message="";
String msg=(String)request.getAttribute("message");
System.out.println("message is=============="+msg);
if(msg==null)
{
message="";
}

else
{
message= msg;
}

ArrayList depositrequest= (ArrayList)request.getAttribute("requestlist");
//System.out.println("Arraylist in deposit request is"+depositrequest);

String status="";
String clientIp=request.getRemoteAddr();
session.setAttribute("clientIp",clientIp);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Deposit Request</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>



<script type="text/javascript">
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

function accp()
{
alert("TID: 1234567896543, you have successfully accepted TB Request from Distributor ID DS123456.for Rs 000.00, your current balance is Rs. 456789.");
}

</script>

<script type="text/javascript">
function deep()
{
alert("TID: 1234567896543, you have declined TB Request for Rs 000.00 of Distributor ID DS123456. Your current balance is Rs. 456789. ");
}

</script>
</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
<%@ include file="../../header.jsp"%>
  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big">Deposit Request - Distributor</td><td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr><td height="20"  align="center" style="font-family:'Trebuchet MS';color:#FF3300" class="big"><%=message%></td></tr>
	<tr><td height="20"  align="center"></td></tr>
	<tr><td height="20"  align="center"></td></tr>
  <tr>
    <td width="100%" align="center" valign="top">
	<%if(depositrequest.size()==0){%>
						</span>
					<!--	<table width="100%" border="0" cellpadding="1" cellspacing="0" class="text">
						  <tbody id="flightSales1">
						    <tr><td width="100%" bgcolor="#EFF3F5" class="text" align="center"><div align="center" class="Trebuchet_b" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:11px; font-weight:bold;"><strong>No Deposits Request are present</strong></div></td> </tr>
						  </tbody>
			  </table>-->
			  
			  	<table style="border:1px solid #005CB9; width:99.48%; margin-bottom:2.50px;">
				<tr><td height="36" align="center"><font color="#FF0000"  size="2">Data is Not Available.</font></td>
				</tr> 
				</table>
						<%}else{%>
	<table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1">
          <tbody>
           	<tr  class="st" align="left" bgcolor="#dbe5f1">
              <td width="2%" height="25" align="center" valign="middle"><strong>S.N.</strong></td>
			  <td width="6%" align="center" valign="middle"><strong>Txn&nbsp;ID</strong></td>
              <td width="10%" align="left" valign="middle" style="padding-left:5px;"><strong>Distributor&nbsp;ID</strong></td>
              <td width="9%" align="left" valign="middle" style="padding-left:5px;"><strong>Agency&nbsp;Name</strong></td>
			  <td width="5%" align="center" valign="middle"><strong>Amount</strong></td>
			  <td width="12%" align="center" valign="middle"><strong>Date&nbsp;&&nbsp;Time</strong></td>
			  <td width="12%" align="left" valign="middle" style="padding-left:5px;"><strong>Pay. Mode</strong></td>
              <td  width="7%" align="center" valign="middle"><strong>Cheque&nbsp;No.</strong></td>
			  <td width="10%" align="center" valign="middle"><strong>Status</strong></td>
			  <td width="10%" align="center" valign="middle"><strong>Bank&nbsp;Charges</strong></td>
			  <td width="14%" align="center" valign="middle"><strong>Remark</strong></td>
              <td  width="13%" align="center" valign="middle"><strong>Action</strong></td>
             
            </tr>
			 <%		
												
								for(int i=0;i<depositrequest.size();i++)
								{	
								  HashMap hm=(HashMap)depositrequest.get(i);
								 		
							  %>
			    <form action="depositRequest.do" name="deposit<%=i%>" id="deposit<%=i%>" method="post">
							<input type="hidden" name="param" value="updatedeposit">
							
			<tr bgcolor="#FFFFFF">
              <td height="30" align="center" valign="middle"><%=i+1%></td>
			  <td align="center" valign="middle"><%=hm.get("journal_id")%></td>
              <td align="left" valign="middle" style="padding-left:5px;"><a href="javascript:forpopup('<%=hm.get("distributor_id")%>','<%=hm.get("request_date")%>','<%=hm.get("request_time")%>','<%=hm.get("journal_id")%>')"><%=hm.get("distributor_id")%></a></td>
              <td align="left" valign="middle" style="padding-left:5px;"><%=hm.get("company_name")%></td>
              <td align="center" valign="middle"><%=hm.get("ammount_to_credit")%></td>
			  <td align="center" valign="middle"><%=hm.get("request_date")%>&nbsp;<%=hm.get("request_time")%></td>
             <td align="left" valign="middle" style="padding-left:5px;"><%=hm.get("mode")%></td>
			 <%
			 String chequeNo=(String)hm.get("checque_no");
			 if(chequeNo==null){
			 chequeNo="-";
			 }
			 %>
              <td align="center" valign="middle"><%=chequeNo%></td>
			  <td align="center" valign="middle"><select style="width:70px;" class="small"  name="statusid<%=i%>" id="statusid<%=i%>">
			  <option selected="selected" value="pending">Pending</option>
			  <option  value="Accepted">Accept</option><option value="Declined">Decline</option>
			  </select></td>
			  <td align="center" valign="middle"><input type="text" style="width:70px;" class="small" value="" name="bankcharge<%=i%>" onKeyPress="return digitonly(this,event);" id="bankcharge<%=i%>" maxlength="4"/></td>
			  <td align="center" valign="middle"><input type="text" style="width:120px;" class="small" value="" name="bankchargeremark<%=i%>" id="bankchargeremark<%=i%>" maxlength="100"/></td>
              <td align="center" valign="middle"><input name="submit<%=i%>" type="submit" value="Save" class="txt" style="width:70px" onclick="return checkStatus('<%=i%>');" /></td>
             
            </tr>
			<input type="hidden" name="distributorId" value="<%=hm.get("distributor_id")%>" />
						        <input type="hidden" name="tranId" value="<%=hm.get("journal_id")%>" />
						        <input type="hidden" name="date" value="<%=hm.get("request_date")%>" />
						        <input type="hidden" name="time" value="<%=hm.get("request_time")%>" />
						        <input type="hidden" name="status" value="<%=i%>" />
						        <input type="hidden" name="amount" value="<%=hm.get("ammount_to_credit")%>" />
		
			</form>
						        <%}							   							  
							   %>		
			
               </tbody>
        </table>
		   
		<%}%></td>
		                     
  </tr>
   				   


		
				<tr><td height="40"></td></tr> 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%>
</td></tr>
</table>
</center>
</body>
</html>

<script type="text/javascript">

function forpopup(distributorId,date,time,tranId)
{

window.open('depositRequest.do?param=distribotorDetails&did='+distributorId+'&date='+date+'&tranId='+tranId+'&time='+time,'popup','width=550,height=550,resizable=0,scrollbars=1');
}
function checkStatus(i){
var status=document.getElementById("statusid"+i).value;
var bankcharge=document.getElementById("bankcharge"+i).value;
var remark=document.getElementById("bankchargeremark"+i).value;
if(status=="pending"){
alert("Please select status");
return false;
}
if(bankcharge >= 1){
if(remark==""){
alert("Please  enter remark");
return false;
}
}
}
</script>
