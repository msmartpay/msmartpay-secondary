<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String messge=(String)request.getAttribute("message");
if(messge==null)messge="";
%>
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
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
   <td  align="center" width="100%" valign="top"> 
   <table cellpadding="0" cellspacing="0"  width="90%" align="center" border="0">
        
		<tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
		  
		    <tr>
                <td  width="100%" valign="bottom" height="40" align="left"  style="font-size:13px; font-family:'Trebuchet MS'; color:#930; font-weight:bold;">Start Scheduler</td>                
              </tr>
              <tr>
                 <td  width="100%" valign="bottom" height="18" align="center" class="dyn_mgs"><%=messge%></td>
              </tr>
		     <tr>
             
               <td valign="top" align="center">
               
            <form method="post" name="SchedularForm" action="runSchedular.action?param=runSchedular">
            
		  <table cellpadding="0" cellspacing="0" width="100%" align="left" border="0"  class="mydata_tabl" id="border">
                 <tr>
                   <td colspan="4" height="10"></td>
                 </tr>
	            
				    <tr>
				      <td width="25%"></td>
				      <td width="15%" align="left">Scheduler Name</td>
				      <td width="7%" align="left">:</td>
				      <td width="53%" align="left"><select  name="type">
				        <option value="select" selected="selected">Select</option>
                        <option value="webSchedular">Web Scheduler</option>
				        <option value="ApiSchedular">API Scheduler</option>
				        <option value="MappSchedular">MAPP Scheduler</option>
				        </select></td>
				      </tr>
                    <tr>
                      <td colspan="4" height="20"></td>
                    </tr>
                <tr><td></td>
                <td  align="left"></td>
				<td align="left"></td>
				<td align="left" style="padding-left:0px">  <input name="Submit2" type="submit" value="Submit" class="cls_btn" id="sms" onclick="validate()"/></td>
                </tr>
                 
                    
                    <tr>
                      <td colspan="4" height="10"></td>
                    </tr>
			  </table>
              
			  </form>
              
              </td></tr>
			<tr><td colspan="4" height="30"></td></tr>
      </table>
      </td>
  </tr></table></td></tr>
  <tr>
    <td width="100%" height="193" align="center" valign="bottom"></td>
  </tr>
  <tr>
    <td width="100%" height="100" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function validate(){
var op=document.SchedularForm.type.value;
if(op=="select"){
alert('Please select Option');
return false;
}
document.SchedularForm.submit();


}

</script>