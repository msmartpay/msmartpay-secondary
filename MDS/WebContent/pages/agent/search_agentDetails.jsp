<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%



ArrayList distributorIdList=(ArrayList)session.getAttribute("distributorIdList");
//System.out.println("DS ID "+distributorIdList);

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
<title><%=session.getAttribute("md_page_title")%>:: View Agents</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />





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
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:0px" class="big">View Agents</td><td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	 <td colspan="4" align="center" ><font color="#FF0000" size="4">&nbsp; <%=message%></font></td>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">
	
	  <table width="1000" border="0" cellspacing="0" cellpadding="0" class="border">

	    <tr>
	   <td colspan="4" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
        <tr>
          <td width="25%"></td>
          <td width="20%" align="left" class="txtt" height="30"><strong>Distributor Id</strong></td>
          <td width="25%" align="left"><select class="txt" style="width:200px"  name="distributorId" id="distributorId">
              <option value="select" selected="selected">Please Select</option>
              <%
	 
	
	   if(distributorIdList.size()>0){
	for(int j=0;j<distributorIdList.size();j++){
  	HashMap temp=(HashMap)distributorIdList.get(j);
	%>
              <option value="<%=temp.get("distributor_id")%>"><%=temp.get("distributor_id")%></option>
              <%}}%>
            </select>          </td>
          <td align="left" width="30%">&nbsp;</td>
        </tr>
        <tr>
          <td width="25%"></td>
          <td width="20%" align="left" class="txtt" height="30"><strong>Search By</strong></td>
          <td width="25%" align="left"><select class="txt" style="width:200px" id="searchBy" name="searchBy" >
              <option value="select"  selected="selected">Please Select</option>
              <option value="1">Active Agent</option>
              <option value="2">Deactive Agent</option>
              <option value="3" >All</option>
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
	
	<%
	 int i=0;
	%>
  <form action="viewAgent.do" method="post" name="distform" id="distform">
 <input type="hidden" name="distributorids" id="allDistId1" />
<input type="hidden" name="param" value="" />
  <tr>
    </tr>
   	</form>			   


		
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
var dsID=document.distributorlist.distributorId.value;

if(dsID=="select"){
alert("Please select Distributor ID.")
return false;
}
var serachBy=document.distributorlist.searchBy.value;

if(serachBy=="select"){
alert("Please select Search By.")
return false;
}
var searchBy=document.getElementById("searchBy").value;
var distributorid=document.getElementById("distributorId").value;
if(searchBy=="1"){
document.distributorlist.action="viewAgent.do?param=active&did="+distributorid;
document.distributorlist.submit();
return true;
}
if(searchBy=="2"){
document.distributorlist.action="viewAgent.do?param=deactive&did="+distributorid;
document.distributorlist.submit();
return true;

}
if(searchBy=="3"){
document.distributorlist.action="viewAgent.do?param=viewAgentAll&did="+distributorid;
document.distributorlist.submit();
return true;

}
}
</script>

