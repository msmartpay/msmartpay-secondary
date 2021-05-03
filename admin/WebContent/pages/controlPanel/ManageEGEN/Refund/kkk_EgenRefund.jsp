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
System.out.println("remote ip Address "+ipAdd);
String vendorid="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="script/jquery.js"></script>
<SCRIPT type="text/javascript" src="scripts/calendar.js?random=20060118"></script>
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script language="Javascript" src="scripts/validation.js"></script>
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<script type="text/JavaScript">

<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
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

function msg()
{
var a = confirm("You have to login first to use this service \nDo you want to continue......");
if (a ==1) 
{
  window.open("http://bus.travelepoint.co.in/booking/buslogin.aspx?user_id=demo@TRAVELEPOINT.IN", "_blank");
  }
else if(a!=1){window.open("reg.html", "_self"); }
}

</script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
        <tr>
          <td valign="top" align="center" >
          <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="rounded-corners">
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs"> &nbsp;&nbsp;Egen Refund Transaction Process</td>
              </tr>
			
              <tr>
			  
                <td width="100%" align="center"><div class="accordion" style="padding:20px; margin-bottom:40px;" id="border">
				
				
				  <table width="100%"  cellspacing="0" cellpadding="0" align="center" >
                    <tr>
                      <td>
					  <form name="SearchTran" method="post" >
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl" border="0">
                            <tr><td colspan="4" align="center"><%=Message%></td></tr>
							<tr>
                              <td width="20%"></td>
                              <td align="left" height="30"  class="form11">Transaction No</td>
                              <td width="8%" align="left">:</td>
                              <td align="left"><input name="TranID" id="TranID" type="text"  value="" maxlength="20" tooltipText="Type Transaction Number in this box" onkeypress="return digitonly(this,event)"/></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Corporate Txn ID</td>
                              <td align="left">:</td>
                              <td align="left"><input name="CorTranId" id="CorTranId" type="text"  value="" maxlength="20" tooltipText="Type Transaction ID in this box" onkeypress="return digitonly(this,event)"/></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11">Vendor Transaction ID</td>
                              <td align="left">:</td>
                              <td align="left"><input name="vendorID" id="vendorID" type="text"  value="" maxlength="20" tooltipText="Type vendor transaction ID in this box" onkeypress="return digitonly(this,event)"/></td>
                            </tr>
                            <tr>
                              <td></td>
                              <td align="left" height="35"   class="form11">From Date</td>
                              <td align="left">:</td>
                              <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" class="mydata_tabl">
                                  <tr>
                                    <td align="left" valign="middle"><input name="fromDate" type="text" class="tcal" id="dob" size="15" readonly="readonly"  value="<%=beforeDate%>"/>
                                        </td>
                                  </tr>
                              </table></td>
                            </tr>
							<tr>
                              <td></td>
                              <td align="left" height="35"   class="form11">To Date</td>
                              <td align="left">:</td>
                              <td align="left">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
                                  <tr>
                                    <td align="left" valign="middle"><input name="toDate" type="text" class="tcal" id="dob" size="15" readonly="readonly"  value="<%=curdate%>"/>
                                        </td>
                                  </tr>
                              </table></td>
                            </tr>
                            
                            
                            <tr>
                              <td></td>
                              <td align="left" height="35"   class="form11"></td>
                              <td align="left"></td>
                              <td align="left">
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left">
                                  <tr>
                                    <td align="left" valign="middle"><input name="Submit" class="cls_btn"  type="button" value="Submit" onclick="submitForm()"/>
                                        </td>
                                  </tr>
                              </table></td>
                            </tr>
                            
                            
                  
                          
                          
                    
                            
                            </table>
							</form>                          
                          </td>
                    </tr>
                    
                    
					<tr>
                            <td align="center" width="100%">
							<form name="updateTranForm" method="post">
							<table width="90%"  cellspacing="1" cellpadding="1" align="center" class="tbls"  bgcolor="#a74312">
                        <%if(size>0){%>
                        <tr class="hd" align="center">
						<td width="5%" class="sortableheader">Tick</td>
						  <td width="5%" class="sortableheader">Agent&nbsp;ID</td>
                          <td width="12%" class="sortableheader">Txn&nbsp;Ref&nbsp;No</td>
						  <td width="12%" class="sortableheader">Ag&nbsp;Txn&nbsp;ID</td>
						  <td width="12%" class="sortableheader">Vendor&nbsp;Txn&nbsp;ID</td>
						  <td width="13%" class="sortableheader">Txn&nbsp;Date</td>
						  <td width="10%" class="sortableheader">Connection&nbsp;No</td>
                          <td width="10%" class="sortableheader">Operator</td>
                           <td width="10%" class="sortableheader">Service</td>
                          <td width="5%" class="sortableheader">Txn&nbsp;Amount</td>
						  <td width="9%" class="sortableheader">Live&nbsp;Status</td>
						  <td width="9%" class="sortableheader">Txn&nbsp;Status</td>
						  <td width="9%" class="sortableheader">Action</td>
						  
                        </tr>
                        <tr align="center" bgcolor="#ffffff">
                          <td ><input type="checkbox" name="checkstatus" /></td>
                          <td><%=map.get("user_id")%></td>
						  <%String tran_id=(String)map.get("tran_id");%>
                          <td><%=tran_id%></td>
						   <td><%=map.get("CorpoateID")%></td>
						   <% vendorid=(String)map.get("USessionID");
						   if(vendorid==null){vendorid="";}
						   %>
						    <td><%=vendorid%></td>
                          <td><%=map.get("date_of_recharge")%></td>
                          <td><%=map.get("mobile_number")%></td>
                          <td><%=map.get("mobile_operator")%></td>
                          <td><%=map.get("service")%></td>
						  <td><%=map.get("amount")%><input type="hidden" name="ipAdd" value="<%=ipAdd%>" /></td>
						  <% String Currstatus=(String)map.get("status"); %>
						  <td><%=Currstatus%><input type="hidden" name="status"value="<%=Currstatus%>"></td>
						   <td><%=map.get("Tran_status")%></td>

							<input type="hidden" name="RefrenceId" value="<%=map.get("RefrenceId")%>">
							<input type="hidden" name="userId" value="<%=map.get("user_id")%>">
				   <td bgcolor="#FFFFFF" class="text"style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;"><select  name="updatedStatus">
				   <option value="selected">Select</option>
				   <option value="Refund">Refund</option>
				   <option value="Success">Success</option>
				   </select></td>
				   
		</tr>
                     
				    
                      </table>
					  <tr>
					  <td><input  class="tabl2" type="button" value="Update" onclick="updateTran('<%=tran_id%>')" /></td>  
					  </tr>
					  <%}%>
					  </form>
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
    <td width="100%" valign="top" align="left" height="31"></td>
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
.accordion {
	width: 1000px;
}
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
var Tran_no=document.SearchTran.TranID.value.replace(/^\s+|\s+$/, '');
var Tran_id=document.SearchTran.CorTranId.value.replace(/^\s+|\s+$/, '');
var vendor_id=document.SearchTran.vendorID.value.replace(/^\s+|\s+$/, '');
if(Tran_no=="" && Tran_id =="" && vendor_id==""){
alert("Please enter any one ID");
return false;
}
else{
document.SearchTran.action="EgenRefund.action?param=getTran";
document.SearchTran.submit();
}
}

function updateTran(tran_Id)
{
	if(document.updateTranForm.checkstatus.checked== false){
	alert("Please check checkedbox for this action");
	return false;
	}
	var action=document.updateTranForm.updatedStatus.value;
	if(action=="selected"){
	alert("Please select Action to be taken");
	return false;
	}
	else{
	document.updateTranForm.action="EgenRefund.action?param=UpdateTran&tranID="+tran_Id;
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