<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);

/*String fileStatus=(String) request.getAttribute("fileStatus");
System.out.println(fileStatus);
if(fileStatus==null){
fileStatus="";
}*/
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
	 
	 $( "#datepicker, #datepickers" ).datepicker({
        changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2013',
            numberOfMonths: 1,
		
        onSelect: function( selectedDate ) {
			
			var a = document.getElementById('select_option').value;
			
            if(a  == 'select'){
             
			 if(this.id == 'datepicker'){
              var dateMin = $('#datepicker').datepicker("getDate");
			  
              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Max Date = Selected + 31d
              $('#datepickers').datepicker("option","minDate",rMin);
              $('#datepickers').datepicker("option","maxDate",rMax);                    
            } 
		           
            }
			
			 if(a == "select"){
             
			 if(this.id == 'datepicker'){
              var dateMin = $('#datepicker').datepicker("getDate");
			  
              var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() + 1); // Min Date = Selected + 1d
              var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate() + 31); // Max Date = Selected + 31d
              $('#datepickers').datepicker("option","minDate",rMin);
              $('#datepickers').datepicker("option","maxDate",rMax);                    
            }
		           
            }
			

        }
		
    });
	

	 
  });
  </script>
  
  
</head>
<script language="javascript">
function validateform()
{
var reportOf=document.reportform.reportOf.value;
if(reportOf=="select"){
alert('Please select the report ');
return false;
}
else{
document.reportform.action="reportDownload.action?param=downloadReport";
document.reportform.submit();
}
}
</script>
<body>
<form name ="reportform" method="post" >
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top" ><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">
                      Management Report > Channel</td>
                    </tr>
                    
                    <tr>
                      <td colspan="4">
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<div>
<table cellpadding="0" cellspacing="0" width="750" style="margin-bottom:15px;" align="center">  
<tr><td colspan="4">

        <table cellpadding="0" cellspacing="0" width="750" align="center" class="mydata_tabl">

			<tr>
            <td width="7%"></td>
    <td width="22%"><strong>Report Type</strong></td>
    <td width="7%" align="left">:</td>
    <td width="35%">
	<select name="reportofegen" class="searchtext" id="select_option">
	<option value="select">Select</option>
	</select>
	</td>
	</tr>
			
			<tr>
             <td width="7%"></td>
    <td width="22%"><strong>From Date</strong></td>
    <td width="7%" align="left">:</td>
    <td width="35%">
	<input type="text"  class="tcal" id="datepicker"  name="fromDate" />
	</td>
	</tr>
			
            <tr>
             <td width="7%"></td>
    <td width="22%"><strong>To Date</strong></td>
    <td width="7%" align="left">:</td>
    <td width="35%">
	<input type="text"  class="tcal" id="datepickers"  name="toDate" />
	</td>
	</tr>
    
   		    <tr>
             <td width="7%"></td>
    <td width="22%"></td>
    <td width="7%" align="left"></td>
    <td width="35%">
	<input name="Input" type="button" value="Submit" class="cls_btn" onclick="validateform()" />
	</td>
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
                      <td colspan="4" height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
    <tr>
    <td width="100%" valign="top" align="center" height="118"></td>
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