<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />


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

var val_1 = document.Getreport.from.value;
var val_2 = document.Getreport.to.value;
document.getElementById("from").readOnly=true;
document.getElementById("to").readOnly=true;
if( val_1 == "" || val_2 == "")
{
alert("Please select the date from input");
return false;
}
document.Getreport.submit();

}

</script>




<title><%=session.getAttribute("md_page_title")%>:: DS Report</title>
<link href="../CSS/dis.css" rel="stylesheet" type="text/css" />



<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>

<style>
.mydates{ border:0px solid #FF0000; width:100%; margin:0;  height:50px; background:#F3F3F3;}

.mydates tr td strong{ font-family:"Trebuchet MS"; color:#484848; font-size:13px; font-weight:bold; padding:10px;}
.mydates tr td input{font-family:"Trebuchet MS"; color:#484848; font-size:13px; box-shadow:0 0 2px #484848; height:25px; width:180px; border:none;border-radius:5px;}
</style>

</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1070">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:0px" class="big">Distributor Account Statement </td>
	  <td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	
	</td>
    </tr>
	<tr><td align="center"><font color="#FF0000" size="4"><%=message%></font></td></tr>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
		<form name="Getreport"  method="post" action="DownloadReport.do">
	
	
	  	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="border">

	    	<tr>
	   		<td colspan="4" align="center" ><p>&nbsp;</p>
	     	<p>&nbsp;</p></td>
	   		</tr>
        
        
		
		  <tr>
		   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
		   </tr>
			
			<tr > 
			<td align="center" colspan="100%">
			
			<!--<A style="text-decoration:none;" HREF="DownloadReport.do?param=downloadDSAccountDetails"><input type="button" value="Download Report" /></A> -->
			
			<input type="hidden" name="param" value="downloadDSAccountDetails" />
			<table class="mydates">
			
			<tr>
			<td><strong>From</strong></td>
			<td><input readonly id="from" type="text" name="from" /></td>
			<td>&nbsp;</td>
			<td><strong>To</strong></td>
			<td><input readonly  type="text" id="to" name="to" /></td>
			<td align="right" valign="middle" height="50" class="hh" style="padding-right:55px">&nbsp;<input type="button" style="font-weight:bold;" value="Download Report" onclick="from_click()" />
				
				</td>
			</tr>
			</table>
		
		 	</td> 
			 </tr>
		

		
		
		  	<tr>
	  		 <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   		</tr>
		
        
		
      </table>
	

  	</form>
  	
  	</td>
  	</tr>
  	<tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	 </tr>

</tr>
 <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>
<script type="text/javascript">
function SubmitForm(){

var serachBy=document.Getreport.searchBy.value;

if(serachBy=="select"){
alert("Please select Search By.")
return false;
}
var searchBy=document.getElementById("searchBy").value;
if(searchBy=="1"){
//document.Getreport.action="reportAction.do?param=getReportPage";
//document.Getreport.submit();
document.getElementById("td_1").style.display="";
document.getElementById("td_2").style.display="none";
return true;
}
if(searchBy=="2"){
//document.Getreport.action="rechargeTxn.do?param=getReport";
//document.Getreport.submit();
document.getElementById("td_2").style.display="";
document.getElementById("td_1").style.display="none";

return true;

}

else
{
document.getElementById("td_1").style.display="none";
document.getElementById("td_2").style.display="none";
}

}
</script>

