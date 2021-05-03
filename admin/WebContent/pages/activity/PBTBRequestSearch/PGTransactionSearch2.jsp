<%@ page import="java.util.Date" %>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.text.*" %>

<%
Date befDate=new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
befDate.setDate(befDate.getDate());

String beforeDate=formatter.format(befDate);
Date todate = new Date();

String curdate = formatter.format(todate);
String Message=(String)request.getAttribute("message");

if(Message==null){
Message="";
}


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
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
           <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >PGTB Transaction Process</td>
              </tr>
				<tr>
				 <c:if test="${requestScope.message ne null}">
				 <td align="center" class="dyn_mgs">
				 <strong>${requestScope.message}</strong>
				 </td>
				 </c:if>
				</tr>
              <tr>
			  
                <td valign="top" align="center" class="rounded-corners box_heights" >
				
				
				   <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td>
					  <form name="SearchTran" method="post" >
                          <table width="100%"  cellspacing="0" cellpadding="0" align="center"  class="mydata_tabl">
                            <tr>
                            	<td colspan="4" align="center" >
                            	</td>
                            </tr>
                        
                            <tr>
                              <td></td>
                              <td align="left" height="30"  class="form11"><strong>Transaction ID</strong></td>
                              <td align="left">:</td>
                              <td align="left"><input name="Tran_id" id="Tran_id" type="text" value="" maxlength="20" placeholder="Type Transaction ID in this box(Optional)" tooltipText="Type Transaction ID in this box(Optional)" /> OR</td>
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
                              <td align="left" height="35" class="form11"><strong>To Date</strong></td>
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
                                    <td align="left" valign="middle"><input name="Submit" class="cls_btn" type="button" value="Search" onclick="submitForm()"/>
                                  </td>
                                  </tr>
                              </table>
                              </td>
                            </tr>
                            </table>
							</form>                          
                          </td>
                    </tr>
                    
                    
					<tr>
                    	<td align="center" width="100%">
							<form name="updateTranForm" method="post">
							
								<table width="100%"  cellspacing="0" cellpadding="0" align="center" class="tbls"   bgcolor="#a74312">
                        	
								<c:if test="${requestScope.tbdetails ne null}">
			                        <tr class="hd" align="center">
			                        	<td width="5%" ><strong>S.No.</strong></td>
										<td width="5%" align="center" style="border-bottom:0px solid #930;">
										<input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check1()" value="" /></td>
										 <td width="13%" ><strong>Date & Time</strong></td>
									  	<td width="5%" ><strong>Agent ID </strong></td>
									  	<td width="12%" ><strong>Ag Txn ID</strong></td>
									  	<td width="10%" ><strong>Connection No</strong></td>
			                          	<td width="5%" ><strong>Txn Amount</strong></td>
									  	<td width="9%" ><strong>Txn Status</strong></td>
									  	<td width="9%" ><strong>Action</strong></td>
									  	
									  
			                        </tr>
			                        <c:forEach var="tblist" items="${requestScope.tbdetails}" varStatus="row">
			                        <tr align="center" bgcolor="#ffffff">
			                          <td>${row.index+1}</td>
			                          <td ><input type="checkbox" name="checkpartial" id="chek${row.index}" value="${row.index}"/></td>
			                          <td><input type="hidden" name="date" value="${tblist.date}"/>${tblist.date}</td>
			                          <td><input type="hidden" name="agentId${row.index}" id="agentId${row.index}" value="${tblist.agentId}"/>${tblist.agentId}</td>
			                          <td><input type="hidden" name="tranId${row.index}" id="tranId${row.index}" value="${tblist.tranId}"/>${tblist.tranId}</td>
			                          <td><input type="hidden" name="mobile${row.index}" id="mobile${row.index}" value="${tblist.mobile}"/>${tblist.mobile}</td>
									  <td><input type="hidden" name="amount${row.index}" id="amount${row.index}" value="${tblist.amount}"/>${tblist.amount}</td>
									  <td><input type="hidden" name="tranStatus${row.index}" id="tranStatus${row.index}" value="${tblist.tranStatus}"/>${tblist.tranStatus}</td>
									 
									<c:choose>
									<c:when test="${tblist.tranStatus eq 'Pending'}">
								   <td bgcolor="#FFFFFF" class="text"style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
								   <select  name="action${row.index}" id="action${row.index}" style="width:80%;padding:1px;margin:5px;height:20px;">
								   		<option value="Select" selected="selected">Select</option>
								   		<option value="Credit">Credit</option>
								   		<option value="Decline">Decline</option>
							   		</select>
							   		</td>
							   		</c:when>
							   		<c:otherwise>
							   		<td bgcolor="#FFFFFF" class="text"style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
							   		</td>
							   		</c:otherwise>
							   		</c:choose>
								</tr>
									</c:forEach>
									 
									<tr>
										<td colspan="8"></td>
										<td align="right"><input style="margin-right:20px;width:64%" class="tabl2" type="button" value="Update" onclick="updateTran();" /></td>  
									</tr>
								</c:if>
                      			</table>
                      		 
                      		 </form>
					 	</td>
					 </tr>
					  
					 
                  </table>
				  
					
                 </td>
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
<script language="javascript">

function check1(){
	var allcheck = document.updateTranForm.checkAll;
	
	if(allcheck.checked == true){
	for(i=0; i<document.updateTranForm.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.updateTranForm.elements[i].type=="checkbox" && document.updateTranForm.elements[i].name!="checkAll")
			{
				document.updateTranForm.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(i=0; i<document.updateTranForm.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.updateTranForm.elements[i].type=="checkbox" && document.updateTranForm.elements[i].name != "checkAll" )
			{
				document.updateTranForm.elements[i].checked=false;
			}
			
		}
	}
}
function submitForm()
{

document.SearchTran.action="PGTBRequest.action?param=getPGTBDetails";
document.SearchTran.submit();

}

function updateTran()
{
	 var checkpartial=document.getElementsByName("checkpartial");
		var checkarray=[];
		 for (var i=0;i<checkpartial.length;i++){
			
			 if (checkpartial[i].checked) {
				 checkarray.push(checkpartial[i].value);
				  	console.log(document.getElementById("action"+i).value);
				    if(document.getElementById("action"+i).value=='Select'){
				    	alert("Please Select the Action Option.");
				    	document.getElementById("action"+i).focus();
				    	return false;
				    	break;
				    }
			  }
			}
		 if(checkarray.length==0){
			 alert("Please Select the Check option");
			 return false;
		 }
		 document.updateTranForm.action="PGTBRequest.action?param=updatePGTBDetails";
		 document.updateTranForm.submit();
	
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

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
  $(document).ready(function(){
	  
	  $("#datepicker,#datepickers").datepicker({
		  
		  changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'1950:2020',
            numberOfMonths: 1,
            maxDate:0,

		  });

  });
  </script>
<!-- <script type="text/javascript">
var tooltipObj = new DHTMLgoodies_formTooltip();
tooltipObj.setTooltipPosition('right');
tooltipObj.setPageBgColor('#EEEEEE');
tooltipObj.setTooltipCornerSize(15);
tooltipObj.initFormFieldTooltip();
</script> -->
</body>
</html>

