<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Agents</title>
<link href="../CSS/dis.css" rel="stylesheet" type="text/css" />



<%
String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>

<script type="text/javascript">
function SubmitForm(){

var serachBy=document.Getreport.searchBy.value;

	if(serachBy=="select"){
		alert("Please select Search By.")
		return false;
	}
	else var searchBy=document.getElementById("searchBy").value;
	if(searchBy=="1"){
		document.Getreport.action="DownloadReport.do?param=ATTReport";
		document.Getreport.submit();
	
		return true;
	}
	else if(searchBy=="2"){
		document.Getreport.action="DownloadReport.do?param=LRTReport";
		document.Getreport.submit();
	
		return true;
	
	}else if(searchBy=="3"){
		document.Getreport.action="DownloadReport.do?param=DMRReport";
		document.Getreport.submit();
	
		return true;
	
	}else{
		return false;
	}


}
</script>

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
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:0px" class="big">Agent Report</td>
	  <td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
		<tr><td align="center"><font color="#FF0000" size="4"><%=message%></font></td></tr>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="Getreport"  method="post">
	
	
	  <table width="1000" border="0" cellspacing="0" cellpadding="0" class="border">

	    <tr>
	   <td colspan="4" align="center" ><p>&nbsp;</p>
	     <p>&nbsp;</p></td>
	   </tr>
        
        <tr>
          <td width="25%" ></td>
          <td width="20%" align="left" class="txtt" height="30"><strong style="font-size:13px;">Search By :    </strong></td>
          <td width="25%" align="left" height="80"><select class="txt" style="width:200px; height:25px; padding:3px;"  id="searchBy" name="searchBy" >
              <option value="select" style="font-size:13px;"  selected="selected">Select</option>
              <option value="1" style="font-size:13px;">Agents Account Statement</option>
              <option value="2" style="font-size:13px;">Recharge Transactions</option>
              <option value="3" style="font-size:13px;">DMR Transactions</option>
              <!-- <option value="4">All service</option> -->
          </select></td>
          <td align="left" width="30%"><input name="button" type="button" onclick="return SubmitForm();" value="Submit" style="width:100px;"/></td>
          <td></td>
        </tr>
		
		  <tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
		
        
		
      </table>
	</form>		</td>
    </tr>
  			   


		
				<tr bgcolor="#FFFFFF"><td height="20" align="center">&nbsp;</td></tr> 
</table>
</td>

  <tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>

</tr>
 <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>


