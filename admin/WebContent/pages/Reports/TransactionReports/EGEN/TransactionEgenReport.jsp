<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);

String flag=(String) request.getAttribute("flag");
if(flag==null)flag="N";
String showService=(String)request.getAttribute("showService");
if(showService==null)showService="NA";



ArrayList RechargeData =(ArrayList)request.getAttribute("RechargeData");
if (RechargeData==null)
{
	RechargeData=new ArrayList();
}
int RechargeDataSize=RechargeData.size();


//System.out.println("  RechargeDataSize "+RechargeDataSize);

String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

//String apiList=(String) request.getAttribute("apiList");
String checkloginType=(String)session.getAttribute("loginType");
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
    var ytoday = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + (now.getDate());
	var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
	 $('#datepicker').val(ytoday);
	$('#datepickers').val(today);
	  
	  
	  $("#select_option").change(function(){
		  
		   var a = document.getElementById('select_option').value;
	  //alert(a);
		  
		    
	if(a  == 'liverechargefirst'){
	 
	 $("#datepicker, #datepickers").datepicker({
        changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
            defaultDate: "+1d",
               maxDate: "today",
			
        onSelect: function( selectedDate ) 
        {
			
			
			
			 if(this.id == 'datepicker')
			 {
	              var dateMin = $('#datepicker').datepicker("getDate");
				  
	              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
	              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Max Date = Selected + 31d
	              $('#datepickers').datepicker("option","minDate",rMin);
	              $('#datepickers').datepicker("option","maxDate",rMax);                    
            } 
			
		}
			
			 })
		           
            }
			
			
			 if(a == "BillpayReport" || a == "B2BDOMFlight" || a == "B2BHotel" || a == "B2BBus"){
				 
				 
				  $("#datepicker, #datepickers").datepicker({
                changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
            numberOfMonths: 1,
			 defaultDate: "+1w",
             maxDate: "0",
			
           onSelect: function( selectedDate ) {
				 
             
			 if(this.id == 'datepicker'){
              var dateMin = $('#datepicker').datepicker("getDate");
			  
			  
			 
              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()+14); // Max Date = Selected + 31d
              $('#datepickers').datepicker("option","minDate",rMin);
              $('#datepickers').datepicker("option","maxDate",rMax);  
			 
			                    
            }
		           
            }
			

        })
		
    }
		  
		  
		  })
	
	 
  });
  </script>
<script language="javascript">
function validateform1()
{
var reportofegen=document.reportform.reportofegen.value;
if(reportofegen=="Select"){
alert('Please select the report ');
return false;
}
else{
document.reportform.action="egenReportDownload.action?param=egenReport";
document.reportform.submit();
}
}

function validateform()
{
var reportofegen=document.reportform.reportofegen.value;
if(reportofegen=="Select"){
alert('Please select the report ');
return false;
}
else{
document.reportform.action="egenReportDownload.action?param=egenReportView";
document.reportform.submit();
}
}



</script>
</head>

<body>

<form name ="reportform" method="post" >
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td height="334" align="center" valign="top">
         <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td height="332" align="center" valign="top"  class="rounded-corners">
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" style="margin-bottom:25px;">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                      Transaction Report > EGEN
                      </td>
                    </tr>
                   <tr align="center"><td height="0" class="dyn_mgs"><%=message%></td></tr>
                    <tr>
                      <td colspan="4"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<div>
<table cellpadding="0" cellspacing="0" width="750" style="margin-bottom:15px;" align="center">  
<tr><td colspan="4">

<table cellpadding="0" cellspacing="0" width="750" align="center" class="mydata_tabl">
			
			
			<tr >
	<td width="10%"></td>
          <td width="27%"><strong>Report Type</strong></td>
    <td width="5%" align="center">:</td>
    <td width="45%">
	
	
	
	<select name="reportofegen"  class="searchtext" id="select_option">
			  <option value="Select">Select</option>

			 
			  <option value="BillpayReport">Bill Pay</option>
			  <option value="liverechargefirst">Recharge Transaction</option>
			  <option value="B2BDOMFlight">B2B Domestic Flight</option>
			  <option value="B2BHotel">B2B Hotel</option>
			  <option value="B2BBus">B2B Bus</option>
            </select></td>
			
			</tr>
			
			
			<tr>
    <td></td>
          <td ><strong>From Date</strong></td>
    <td  align="center">:</td>
    <td ><input type="text"  id="datepicker"  name="fromDate" /></td>
  </tr>
 	<tr>
    <td></td>
    <td ><strong>To Date</strong></td>
    <td  align="center">:</td>
    <td ><input type="text"   id="datepickers"  name="toDate" /></td>
  </tr>
     <tr>
     <td></td>
    <td >&nbsp;</td>
	<td></td>
    <td height="63" valign="middle"><input name="Input" type="button" value="View" class="cls_btn" onclick="validateform()" />
      <input name="Input2" type="button" value="Download" class="cls_btn" onclick="validateform1()" /></td>
    <td width="23%" height="63" valign="middle">&nbsp;</td>
  </tr>
			
			</table>
			
			</td>
  </tr>
  
  </table></div>
							</td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="30" align="center">
                       <%if(flag.equalsIgnoreCase("Y")){%>
                      <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style="margin-top:20px;">
                                        <tbody>
                                         
                                           <!--liverechargefirstView  tr start-->
										  <%if(showService.equalsIgnoreCase("Recharge")){%>
	                                         <tr  align="center" style="background:#a74312; height:30px;">
	                                            <td width="9%" align="center"><strong>Client ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Date</strong></td>
	                                            <td width="9%" align="center"><strong>Txn Time</strong></td>
	                                            <td width="14%" align="center"><strong>Connection Number</strong></td>
	                                            <td width="10%" align="center"><strong>Operator</strong></td>
	                                    		<td width="16%" align="center"><strong>Txn ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Amount</strong></td>
	                                            <td width="10%" align="center"><strong>Commission</strong> </td>
												<td width="12%" align="center"><strong>Txn Status</strong> </td>
	                                            
	                                          </tr>
                                          
										  <%} %>
										  <!--DOM Flight View tr stop-->
                                          <%if(showService.equalsIgnoreCase("B2BDOMFlight")){%>
	                                         <tr  align="center" style="background:#a74312; height:30px;">
	                                            <td width="9%" align="center"><strong>Client ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Date</strong></td>
	                                            <td width="9%" align="center"><strong>Txn Time</strong></td>
	                                            <td width="14%" align="center"><strong>Reference No.</strong></td>
	                                            <td width="10%" align="center"><strong>Booking TXN</strong></td>
	                                    		<td width="16%" align="center"><strong>Txn ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Amount</strong></td>
	                                            <td width="10%" align="center"><strong>Commission</strong> </td>
												<td width="12%" align="center"><strong>Txn Status</strong> </td>
	                                            
	                                          </tr>
                                          
										  <%} %>
                                          
                                          <!--Hotel View tr stop-->
                                          <%if(showService.equalsIgnoreCase("B2BHotel")){%>
	                                         <tr  align="center" style="background:#a74312; height:30px;">
	                                            <td width="9%" align="center"><strong>Client ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Date</strong></td>
	                                            <td width="9%" align="center"><strong>Txn Time</strong></td>
	                                            <td width="14%" align="center"><strong>Reference No.</strong></td>
	                                            <td width="10%" align="center"><strong>Booking TXN</strong></td>
	                                    		<td width="16%" align="center"><strong>Txn ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Amount</strong></td>
	                                            <td width="10%" align="center"><strong>Commission</strong> </td>
												<td width="12%" align="center"><strong>Txn Status</strong> </td>
	                                            
	                                          </tr>
                                         
										  <%} %>
										  
										  <!--Bus View tr stop-->
                                          <%if(showService.equalsIgnoreCase("B2BBus")){%>
	                                         <tr  align="center" style="background:#a74312; height:30px;">
	                                            <td width="9%" align="center"><strong>Client ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Date</strong></td>
	                                            <td width="9%" align="center"><strong>Txn Time</strong></td>
	                                            <td width="14%" align="center"><strong>Tentative Booking</strong></td>
	                                            <td width="10%" align="center"><strong>Ticket PNR</strong></td>
	                                    		<td width="16%" align="center"><strong>Txn ID</strong></td>
	                                            <td width="10%" align="center"><strong>Txn Amount</strong></td>
	                                            <td width="10%" align="center"><strong>Commission</strong> </td>
												<td width="12%" align="center"><strong>Txn Status</strong> </td>
	                                            
	                                          </tr>
                                         
										  <%} %>
                                          
                                          <%if(RechargeDataSize>0)
										  {
                                          
											for(int i=0;i<RechargeDataSize;i++)
											{
											HashMap map=(HashMap)RechargeData.get(i);
											//System.out.println("map is ::"+map);
											
										%>
                                          <tr bgcolor="#FFFFFF"  align="center">
                                          <% if(showService.equalsIgnoreCase("B2BDOMFlight") || showService.equalsIgnoreCase("B2BHotel") || showService.equalsIgnoreCase("B2BBus")){
                                           String Partner_Ref_id=(String)map.get("Partner_Ref_id");
                                           if(Partner_Ref_id==null || Partner_Ref_id.equalsIgnoreCase(""))
                                        	   Partner_Ref_id="NA";
                                           
                                           String Booking_Trn=(String)map.get("Booking_Trn");
                                           if(Booking_Trn==null || Booking_Trn.equalsIgnoreCase(""))
                                        	   Booking_Trn="NA";
                                          %>
                                          
                                          		<td align="center"><%=map.get("agentId")%></td>
												<td align="center"><%=map.get("dor")%></td>
												<td align="center"><%=map.get("time")%></td>
												<td align="center"><%=Partner_Ref_id %></td>
												<td align="center"><%=Booking_Trn%></td>
												<td align="center"><%=map.get("transaction_id")%></td>
												<td align="center"><%=map.get("amt")%></td>
												<td align="center"><%=map.get("OperatorTranId")%></td>
												<td  align="center"><%=map.get("status")%></td>
												
                                          <%}else{ %>
												<td align="center"><%=map.get("agentId")%></td>
												<td align="center"><%=map.get("dor")%></td>
												<td align="center"><%=map.get("time")%></td>
												<td align="center"><%=map.get("mobno") %></td>
												<td align="center"><%=map.get("Operator")%></td>
												<td align="center"><%=map.get("TepTranId")%></td>
												<td align="center"><%=map.get("amt")%></td>
												<td align="center"><%=map.get("OperatorTranId")%></td>
												<td  align="center"><%=map.get("comm")%></td>
											<%} %>	

													</tr>

                                          <%}}%>
                      </tbody>
                      </table>
                      <%}%>
                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="30"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
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
 if(report=="CDSR")
{
document.reportform.action="CashDepositeReport.action";
document.reportform.submit();
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