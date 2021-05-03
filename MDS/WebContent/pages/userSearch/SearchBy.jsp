<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%


String message=(String)request.getAttribute("message");
if(message==null){
message="";
}




//System.out.println("NotActiveAgentData are=================="+noActivatedIds);
//System.out.println("ActiveAgentData are=================="+activatedIds);


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: User Search</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<style>
.my_text{ font-family:"Trebuchet MS"; font-size:12px;}
.my_text option{font-family:"Trebuchet MS"; font-size:12px;}
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
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:0px" class="big">User Search </td>
	  <td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	 <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp; <%=message%></font></td>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">
	
	  <table width="1000" border="0" cellspacing="0" cellpadding="0" class="border">

	    <tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
        <tr>
          <td width="25%"></td>
          <td width="20%" align="left" class="txtt" height="30"><strong>Search By </strong></td>
          <td width="25%" align="left"><select style="width:182px; height:23px;padding-top:2px;" class="txt"  id="searchBy" name="searchBy" >
              <option value="select"  selected="selected">Please Select</option>
              <option  value="Distributor">Distributor</option>
              <option  value="Agent">Agent</option>
            <!--  <option value="3" >All</option>-->
          </select>          </td>
          <td align="left" width="30%">&nbsp;</td>
        </tr>
        <tr>
          <td width="25%"></td>
          <td width="20%" height="30" align="left" class="txtt"><strong>Email ID </strong></td>
          <td width="25%" align="left"><input class="my_text" style="width:180px; height:23px; font-family:"Trebuchet MS";" name="email" type="text" value="" maxlength="40"/></td>
          <td align="left" width="30%"></td>
          <td></td>
        </tr>
		<tr>
          <td width="25%"></td>
          <td width="20%" height="30" align="left" class="txtt"><strong>Mobile Number</strong></td>
          <td width="25%" align="left"><input class="my_text" style="width:180px; height:23px; font-family:"Trebuchet MS";"  name="mobNum" type="text"  maxlength="10" /></td>
          <td align="left" width="30%"></td>
          <td></td>
        </tr>
		<tr>
          <td width="25%"></td>
          <td width="20%" height="30" align="left" class="txtt"><strong>User ID </strong></td>
          <td width="55%" align="left" ><input class="my_text" style="width:180px; height:23px;font-family:"Trebuchet MS";"  name="userId" type="text" value=""  maxlength="15" onkeyup="convertToCaps(this);"/></td>
          <td align="left" width="30%"></td>
          <td></td>
        </tr>
			<tr>
          <td width="25%"></td>
          <td width="20%" height="30" align="left" class="txtt"></td>
          <td width="55%" align="left" ></td>
          <td align="left" width="30%"></td>
          <td></td>
        </tr>
		  <tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;<input name="button" type="button" onclick="return SubmitForm();" value="Submit" style="width:100px;"/> </font></td>   
	   </tr>
			<tr>
          <td width="25%"></td>
          <td width="20%" height="30" align="left" class="txtt"></td>
          <td width="55%" align="left" ></td>
          <td align="left" width="30%"></td>
          <td></td>
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
<script type="text/javascript">
function SubmitForm(){

if(document.distributorlist.searchBy.value == "select")
{
alert("Please select Search By.");
return false;
}
var email=document.distributorlist.email.value;
var mob =document.distributorlist.mobNum.value.replace(/^\s+|\s+$/, '') ;
var userId =document.distributorlist.userId.value.replace(/^\s+|\s+$/, '') ;
if(email==""&& mob=="" && userId==""){
alert("Please enter any one search option");
return false;
}

document.distributorlist.action="userSearch.do?param=getDetails";
document.distributorlist.submit();
return true;

}
function convertToCaps(input)
{

input.value=input.value.toUpperCase();

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

