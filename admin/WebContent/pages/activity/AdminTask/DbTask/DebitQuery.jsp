<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link type="text/css" rel="stylesheet" href="css/form-field-tooltip.css" media="screen" ></link>
<link type="text/css" rel="stylesheet" href="css/stickytooltip.css" media="screen" ></link>
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>
<script language="Javascript" src="scripts/rounded-corners.js"></script>
<script language="Javascript" src="scripts/form-field-tooltip.js"></script>
<script language="Javascript" src="scripts/validation.js"></script>


</head>
<%
String ipAdd=request.getRemoteAddr();
String message=(String) request.getAttribute("message");
if(message==null){message="";}
%>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
   <td  align="center" width="100%" valign="top"> <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" style="margin-left:100px" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left" style="font-size:13px; font-family:'Trebuchet MS'; color:#930; font-weight:bold;" >Debit</td>                
              </tr>
              
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="DebitQueryForm">
              <table cellpadding="0" cellspacing="0" width="86%" align="left" border="0" class="mydata_tabl" id="border">
                
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">DB Type</td>
                  <td width="6%" align="left">:</td>
                  <td width="55%" colspan="4" align="left"><select  name="dbType" >
                    <option value="select" selected="selected">Select</option>
                    <option value="tep">TEP</option>
                    <option value="api">API</option>
                    <option value="client">Client</option>
                  </select></td>
                </tr>
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">Type of User</td>
                  <td width="6%" align="left">:</td>
                  <td colspan="4" align="left"><select name="typeOfUser" >
                    <option value="select" selected="selected">Select</option>
                    <option value="admin">Admin</option>
                    <option value="mds">MDS</option>
                    <option value="ds">DS</option>
                    <option value="agent">Agent</option>
                  </select></td>
                </tr>
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">User ID</td>
                  <td width="6%" align="left">:</td>
                  <td colspan="4" align="left"><input type="text"  class="tabl2" name="userID"/></td>
                </tr>
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">Amount</td>
                  <td width="6%" align="left">:</td>
                  <td colspan="4" align="left"><input type="text"  class="tabl2" name="amount" onkeypress="return digitonly(this,event)" maxlength="10"/></td>
                </tr>
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">Remark</td>
                  <td width="6%" align="left">:</td>
                  <td colspan="4" align="left"><input type="text"  class="tabl2" name="remark"/></td>
                </tr>
                <tr>
                  <td width="27%" height="30"></td>
                  <td width="12%" align="left">Upload</td>
                  <td width="6%" align="left">:</td>
                  <td colspan="4" align="left"><input name="idproofFile" type="file" class="style2" value="Attach" id="file" size="25"/>
				  <input type="hidden" name="ipAdd" value="<%=ipAdd%>"/>
				  </td>
                </tr>
                <tr>
                  <td colspan="7" height="10"></td>
                </tr>
                <tr>
                  <td></td>
                  <td  align="left"></td>
                  <td align="left"></td>
                  <td colspan="4" align="left" style="padding-left:0px"><input name="Submit2" type="button" value="Submit" 
class="cls_btn" id="sms" onclick="validate()"/>
				  </td>
                </tr>
                
                <tr>
                  <td colspan="7" height="15"></td>
                </tr>
              </table>
            </form>
              
              </td></tr>
			<tr><td colspan="4" height="30"></td></tr>
      </table></td>
  </tr></table></td></tr>
  <tr>
    <td width="100%" height="30" align="center" valign="bottom"></td>
  </tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function validate( ){
var dbType=document.DebitQueryForm.dbType.value;
var typeOfUser=document.DebitQueryForm.typeOfUser.value;
var userID=document.DebitQueryForm.userID.value;
var amount=document.DebitQueryForm.amount.value;
var remark=document.DebitQueryForm.remark.value;
if(dbType=="select"){

alert('Please select value');
return false;
}
if(typeOfUser=="select"){

alert('Please select value');
return false;
}
if(userID==""){

alert('Please Enter value');
return false;
}
if(amount==""){

alert('Please Enter value');
return false;
}
if(remark==""){

alert('Please Enter value');
return false;
}
document.DebitQueryForm.action="dbTask.action?param=debitQuery";
document.DebitQueryForm.submit();
}

function digitonly(input,evt)
{
	var keyCode = evt.which ? evt.which : evt.keyCode;
			var lisShiftkeypressed = evt.shiftKey;
			if(lisShiftkeypressed && parseInt(keyCode) != 9) {
			return false ;
			}
			if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/  || keyCode==9/*TAB*/){
			return true;
			}

			return false;

}
</script>