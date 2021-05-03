<%@ page import = "java.math.BigDecimal" %>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


String curdate=(String)formatter.format(todate);
String displayMessage=(String)request.getAttribute("message");
if(displayMessage==null){
displayMessage="";}
Vector vector=(Vector)request.getAttribute("vector");
int size=0;
 if (vector==null || vector.size()<=0)
	 {
		 size=-1;
	 }
	    else{
		 size=vector.size();
						
}

			
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Account Statement Report</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon" /> 

<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>

<!--================-->

<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css" type="text/css" media="all" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" type="text/javascript"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js"
        type="text/javascript"></script>
         <script type="text/javascript">

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
       	  
       		$('#from').val(ytoday);
     		$('#to').val(today);
       		
   			  
  			  $("#from,#to").datepicker({
  				  
  				  changeMonth: true,
  					changeYear: true,
  					dateFormat: 'yy-mm-dd',
  					yearRange:'2018:2020',
  		            numberOfMonths: 1,
  		            maxDate:0,

  			});

   		
       		
       		  
       	});	 
    </script>

<script language="javascript" type="text/javascript">

function  from_click()
{

var val_1 = document.accountstatement.from.value;
var val_2 = document.accountstatement.to.value;

if( val_1 == "" || val_2 == "")
{
alert("Please select the date from input");
return false;
}
document.accountstatement.submit();

}

</script>




<!--================-->
<style>
.mydates{ border:0px solid #FF0000; width:96%; margin:0 0 10px 20px;  height:50px; background:#F3F3F3;}

.mydates tr td strong{ font-family:"Trebuchet MS"; color:#484848; font-size:13px; font-weight:bold; padding:10px;}
.mydates tr td input{font-family:"Trebuchet MS"; color:#484848; font-size:13px; box-shadow:0 0 2px #484848; height:25px; width:180px; border:none;border-radius:5px;}
</style>

</head>

<body><center>

<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr>
    <td align="left" height="130" width="1000"><%@ include file="../../header.jsp"%></td>
  </tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

	<tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td width="45%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big">My Account Statement</td></td>
	</tr>
	<tr>
	<td width="45%" valign="bottom" align="center" height="40" align="left" style="padding-left:20px" class="big"><font size="4" color="red"><%=displayMessage%></font></td>
	</tr>
	</table>
	
	</td>
    </tr>	  
 
	<tr>
	<td>
		<form name="accountstatement" action="accountStatement.do">
		<input readonly type="hidden" name="param" value="download" />
		<table class="mydates">
	
			<tr>
				<td><strong>From</strong></td>
				<td><input readonly id="from" type="text" name="from"  /></td>
				<td>&nbsp;</td>
				<td><strong>To</strong></td>
				<td><input readonly type="text" id="to" name="to"   /></td>
				<td align="right" valign="middle" height="50" class="hh" style="padding-right:55px" class="txt">&nbsp;<input type="button" style="font-weight:bold;" value="Download Report" onclick="from_click()" />
				
				</td>
			</tr>
		</table>
		</form>
	</td>
	</tr>


    <tr>
    	<td width="100%" valign="top" align="center"></td>
    </tr>
   							<% if (size>0){ %>
  <tr><td width="100%" align="center"><div id="view" ><table cellspacing="0" cellpadding="0" width="100%" align="center">
  
	
  <tr><td width="100%" align="center" valign="top"><table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1">
          <tbody>
           	<tr  class="st" align="center" bgcolor="#dbe5f1">
              <td width="2%"><strong>S.N.</strong></td>
              <td width="8%" height="37"><strong>Date </strong></td>
             
              <td width="20%" align="center" style="padding-left:5px;"><strong>Service</strong></td>
			   
              <td width="10%"><strong>Txn. Amt (Rs.)</strong></td>
            
			  <td width="8%"><strong>Charge (Rs.)</strong></td>
		
              <td  width="10%"><strong>Net Amt (Rs.)</strong></td>
              <td width="4%"><strong>Type</strong></td>
              <td width="12%"><strong>Current Bal. (Rs.)</strong></td>
              <td width="8%" align="center" style="padding-left:5px;"><strong>Txn. Status</strong></td>
            </tr>
			  <%  if(size>0)
			{
				       for (int i=0;i<size;i++ )
					 {
					 String amountstatus="";
						 HashMap map=(HashMap)vector.elementAt(i);
						
								String Amount=(String)map.get("finalBalance");
								double DsBalance = Double.parseDouble(Amount);
								BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
								BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);	 
						 
						 		String trxAmt=(String)map.get("tranAmount");
								double MdsTxn = Double.parseDouble(trxAmt);
								BigDecimal MdsBaln = new BigDecimal(MdsTxn);
								BigDecimal MdsBalnAmt = MdsBaln.setScale(2, BigDecimal.ROUND_HALF_UP);	 
						 
						 	 	String NetAmt=(String)map.get("netTranAmount");
								double NetTxn = Double.parseDouble(NetAmt);
								BigDecimal MdsNetBaln = new BigDecimal(NetTxn);
								BigDecimal MdsNetAmt = MdsNetBaln.setScale(2, BigDecimal.ROUND_HALF_UP);	 
								
								String charge=(String)map.get("charge");
								double Netcharge = Double.parseDouble(charge);
								BigDecimal MdsCharge = new BigDecimal(Netcharge);
								BigDecimal MdCharge = MdsCharge.setScale(2, BigDecimal.ROUND_HALF_UP);
								
								String Status=(String)map.get("status");
								if(Status.equalsIgnoreCase("success")){
								Status="Success";
								}else{
								Status="Failed";
								}
								
								String Type=(String)map.get("actionOnBal");
								if(Type.equalsIgnoreCase("debit")){
								Type="Debit";
								}else{
								Type="Credit";
								}
								String dateGet="";
									
						 			SimpleDateFormat formatterB = new SimpleDateFormat("dd-MM-yyyy");
									SimpleDateFormat sdfSource=new SimpleDateFormat("yyyy-MM-dd");
								   	String Sdate =(String)map.get("tranDate");							
									Date date = sdfSource.parse(Sdate);	
									String 	datGet=formatterB.format(date);
										
									
									 %>
			<tr bgcolor="#FFFFFF">
              <td align="center" height="27"><%=(i+1)%></td>
              <td align="center"><%=datGet%>&nbsp;</td>
            
              <%

							 String service=(String)map.get("service");
							 if(service.equalsIgnoreCase("AdmintoMD")){ 
							  service="Push Transfer";
							 
							 }
							 else{
							 service=(String)map.get("service");
							 }
							 
			 %>
			  
			  <td align="center" style="padding-left:5px;"> <%=service%></td>
			  
              <td align="center"><%=MdsBalnAmt%></td>
              
			  <td align="center"><%=MdCharge%></td>
			 
              <td align="center"><%=MdsNetAmt%></td>
              <td align="center"><%=Type%></td>
              <td align="center"><%=Balance%></td>
              <td  align="center" style="padding-left:5px;"><%=Status%></td>
            </tr>
			
			
			
              <%}}
			  }
			  else if(vector==null){
			  
			  }else{%>
			  <tr bgcolor="#FFFFFF" >
              <td align="center" height="25" valign="bottom" style="font-family:'Trebuchet MS'; font-size:14Px; color:#000000; padding-right:20px" colspan="12"><b>No transaction found</b></td>
		 </tr> 
			 <% }%>
               </tbody>
        </table></td></tr>
  
	</table></div></td></tr>	
<tr><td height="30"></td></tr> 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>
</form></center>
</body>
</html>

<script type="text/javascript">
function ACstatement(){
		var fromdate=document.accountstatement.fromDate.value;
        var todate=document.accountstatement.toDate.value;

	if(Date.parse(todate)-Date.parse(fromdate)<0 )
	{
	alert("To date should be greater then or equal From date");
	
   }

 else
	 {
	if(Date.parse(todate)-Date.parse(fromdate)>2592000000)
	{
	alert("You can select only one month transaction at a time,Plz select date within one month.");
	
    }
   else{

document.accountstatement.action="accountStatement.do";
document.accountstatement.submit();
       }
	 }
}
function forpopup(transid,service)
{


window.open("accountStatement.do?param=transactionid&tran_id="+transid+"&service="+service,'popup','width=600,height=400,resizable=no,scrollbars=no');
//document.MyTransaction.submit();

}

function forprint()
{
window.print();
}
</script>