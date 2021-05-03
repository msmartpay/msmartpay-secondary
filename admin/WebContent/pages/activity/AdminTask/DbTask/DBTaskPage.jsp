<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import = "java.util.* "%> 
</head>
<%
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
  <tr>
    <td valign="top" align="center" height="378" ><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >
        <tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs" >DB Tasks</td>
              </tr>
              
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
              <tr>
                <td valign="top" align="center"><form  name="DBForm" method="post">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  id="border">
                      <tr>
                        <td height="20" colspan="4"></td>
                      </tr>
                   
					 <tr></tr>
					 <tr>
					  <td colspan="4">
					  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="mydata_tabl">
                        <tr>
                        <td width="30%"  align="right" valign="middle" ><strong>Select</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%"><select name="Activity" >
                            <option value="select">Select</option>
							<option value="balanceSheet">Balance Info</option>
							<!-- <option value="pendingTran">Pending Transaction</option> -->
                            <option value="remark">Search By Remark</option>
							<!-- <option value="creditPage">Credit Query</option>
							<option value="DebitPage">Debit Query</option>
							<option value="loginIDChange">Login ID Change</option> -->
                               
                          </select></td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      </table>
					  
					  </td>
                      </tr>
 
					  
                      <tr>
                        <td width="30%"></td>
                        <td width="10%"></td>
                        <td  height="50"><input name="Submit" type="button" value="Continue" class="cls_btn" onclick="submitForm()"/></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="4" height="20"></td>
                      </tr>
                    </table>
                  </form></td>
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <!--footer-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>
<script type="text/javascript">
// code for risk dropdown 




function submitForm()
{
var selectActivityType = document.DBForm.Activity.value;
if(selectActivityType=="select")
{
alert("Please select value.");
return false;
}
  document.DBForm.action="dbTask.action?param=getActionPage";
  document.DBForm.submit();
}


</script>
<script language="JavaScript">



var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

</script>
