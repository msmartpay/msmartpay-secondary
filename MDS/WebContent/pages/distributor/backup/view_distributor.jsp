<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
 String ControlButton=(String)request.getAttribute("ControlButton");

 System.out.println("hello ControlButton is  "+ControlButton);
ArrayList distributorList=(ArrayList)request.getAttribute("distributorList");
String clientId=(String)session.getAttribute("clientId");

ArrayList ActiveDistData=(ArrayList)request.getAttribute("ActiveDistData"); 
ArrayList NotActiveDistData=(ArrayList)request.getAttribute("NotActiveDistData");
if(ActiveDistData==null){
ActiveDistData=new ArrayList();
}
if(NotActiveDistData==null){
NotActiveDistData=new ArrayList();
}
String activatedIds="";
for(int i=0;i<ActiveDistData.size();i++){ 
 activatedIds=activatedIds+ActiveDistData.get(i)+",";
}
String noActivatedIds="";
for(int i=0;i<NotActiveDistData.size();i++){ 
 noActivatedIds=noActivatedIds+NotActiveDistData.get(i)+",";
}
if(ActiveDistData.size()>0){
int aLength=activatedIds.length();
activatedIds=activatedIds.substring(0,aLength-1);
}
if(NotActiveDistData.size()>0){
int nLength=noActivatedIds.length();
noActivatedIds=noActivatedIds.substring(0,nLength-1);
}

%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Distributors</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function confirmHappy()
{
var hh=confirm("Click OK to Activate the selected user/s.");
if (hh==true)
{
alert("Congratulations! you have successefully Activate User ID 1111898978 via TID-1111111111111111");
}
else
{
alert("You have cancelled the process");
}
}
</script>

<script type="text/javascript">
function confirmHappy2()
{
var hh=confirm("Click OK to Deactivate the selected user/s.");
if (hh==true)
{
alert("Congratulations! you have successefully Deactivate User ID 1111898978 via TID-1111111111111111");
}
else
{
alert("You have cancelled the process");
}
}
</script>


</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1070">
 <%@ include file="../../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big">Distributors</td><td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%  if(noActivatedIds!=""){
		String UpdatedStatus=(String)request.getAttribute("UpdatedStatus");
		
		%>
	   <tr>
	   <td colspan="5" align="center" ><font color="#FF0000" size="2">Distributor Id(<%=noActivatedIds%>) are not <%=UpdatedStatus%> due to some reason.</font></td>
	   </tr>
	   <%}%>
	   <%  if(activatedIds!=""){
		   String UpdatedStatus=(String)request.getAttribute("UpdatedStatus");%>
	   <tr>
	   <td colspan="5" align="center" ><font color="#00ec00" size="2">Distributor Id (<%=activatedIds%>) has been <%=UpdatedStatus%> successfully.</font></td>
	   </tr>
	   <%}%>

	<tr><td width="30%"></td><td width="8%" align="left" class="txtt"><strong>Search By</strong></td>
	  <td width="7%" align="left" class="txtt">:</td>
	  <td width="25%" align="left"><select class="txt" style="width:200px" id="searchBy" name="searchBy" onchange="return SubmitForm();">
	<option value="0">Please Select</option>
    <option value="1">Activate Distributor</option>
	<option value="2">Deactivate Distributor</option>
	<option value="3" >All</option></select></td>
	  <td align="left" width="30%">&nbsp;</td>
	</tr>
	</table>
	</form>
		</td>
    </tr>
	
	<%
	 int i=0;
	%>
  <form action="viewDistributor.do" method="post" name="distform" id="distform">
  <input type="hidden" name="distributorids" id="allDistId1" />
  <input type="hidden" name="param" value="" />
  <tr>
    <td width="100%" align="center" valign="top">
	
	
<table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1">
      <tbody>
	   <%
	  
	   if(distributorList.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">
          <td width="4%" align="center">&nbsp;</td>
          <td width="4%" height="25"><strong>SL</strong></td>
          <td width="10%"><strong>Distributor ID</strong></td>
          <td width="27%"><strong>Firm Name</strong></td>
          <td width="17%"><strong>Authorised Person </strong></td>
          <td width="11%"><strong>Email ID </strong></td>
          <td width="8%"><strong>Mobile No. </strong></td>
          <td width="10%"><strong>Current Balance</strong></td>
          <td  width="9%"><strong>Current Status</strong></td>
        </tr>
		  <%for( i=0;i<distributorList.size();i++){
  	HashMap temp=(HashMap)distributorList.get(i);
	
	
	
	
	
	
	%>
        <tr bgcolor="#FFFFFF">
          <td align="center"><input name="distid<%=i%>" type="checkbox" value="<%=temp.get("distributor_id")%>" onclick="distIdAction()" id="distId"/></td>
          <td align="left"><%=i+1%></td>
          <td align="left"><a href="viewDistributor.do?param=distributorid&did=<%=temp.get("distributor_id")%>"><%=temp.get("distributor_id")%></a></td>
          <td align="left"><%=temp.get("company_name")%></td>
          <td><%=temp.get("contact_person")%></td>
          <td align="left"><%=temp.get("email_id")%></td>
          <td align="left"><%=temp.get("mobile")%></td>
          <td align="left"><%=temp.get("balance")%></td>
          <td align="left"><%=temp.get("login_status")%></td>
        </tr>
		  <input type="hidden" name="allDistId" id="allDistId" value="" />
		 
		
		
		 <%}%>
      </tbody>
    </table>	
	
	</td>
  </tr>
  <tr><td height="40" align="center">
  <%
 if(clientId.equals("10003") || clientId.equals("10001") ){
if(ControlButton.equals("AllActiveDis")){ %>

<input type="button" value="Deactivate" onclick="doDistributorAction('Deactivate')">
<%}if(ControlButton.equals("active")){ %>

<input type="button" value="Deactivate" onclick="doDistributorAction('Deactivate')">

<%} if(ControlButton.equals("deactive")){ %>

<input type="hidden" value="Deactivate" >

<%}} else{
	
if(ControlButton.equals("AllActiveDis")){ %>
<input type="button" value="Activate" onclick="doDistributorAction('Activate')">
<input type="button" value="Deactivate" onclick="doDistributorAction('Deactivate')">
<%}if(ControlButton.equals("active")){ %>

<input type="button" value="Deactivate" onclick="doDistributorAction('Deactivate')">

<%} if(ControlButton.equals("deactive")){ %>

<input type="button" value="Activate" onclick="doDistributorAction('Activate')">

<%}}%>
   
  </td></tr>
   	</form>			   


		
				<%}else{%>
				<tr><td height="20" align="center">No Distributor Present</td></tr> 
				<%}%> 
				<tr bgcolor="#FFFFFF"><td height="20" align="center">&nbsp;</td></tr> 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>
<script type="text/javascript">
var selectIds="";
var deselectedIds="";
function distIdAction(){


    var table = document.getElementsByTagName("input"); 
	var allIdsSelected="";
	var allIdsdeSelected="";
	//alert(table.length);
	//alert(table.width);
	for (var i = 0; i < table.length; i++)
	{

	var typeA=table[i].getAttribute("name");
	//alert('name'+typeA);
	//alert('agentsssss'+'agentId'+i);
		for (var j = 0; j < table.length; j++)
		{
			if(typeA=='distid'+j && table[i].checked==true)
			{
				//alert("Hi");
				allIdsSelected+=table[i].value+"_";
			}
			if(typeA=='distid'+j && table[i].checked==false)
			{
				allIdsdeSelected+=table[i].value+"_";
			}
		}
	}
	selectIds=allIdsSelected;
	deselectedIds=allIdsdeSelected;
	//alert('selectIds'+selectIds);
	//alert(selectIds);
}





function doDistributorAction(type){

var allDiffId=selectIds.split('_');
	
		if(selectIds=="")
		{
			if((allDiffId.length-1)==0){
				alert("Please Select Atleast One Distributor");
				return false;
			}
		}

		var x=window.confirm("Are you sure you want to change status of these distributor if yes OK to continue else click on CANCEL.");

	if (x)
	{

			if(type=='Activate')
				{
					
					//alert("inside activate");
					document.getElementById("distform").param.value="activateDistributor";
					document.getElementById("distform").distributorids.value=selectIds;
					alert(document.getElementById("distform").distributorids.value);
					document.getElementById("distform").submit();
					return true;
				}
		      if(type=='Deactivate')
				{
					
					//alert("inside deactivate");
					document.getElementById("distform").param.value="deactivateDistributor";
					document.getElementById("distform").distributorids.value=selectIds;

					document.getElementById("distform").submit();
					return true;
					
		}	
	
 				
	}
 else
	{
		return false;
	}

}




// code for risk dropdown 
function toggleSubmit(obj){

        count=0
        while(document.getElementById("d"+count)){
        document.getElementById("d"+count).style.display="none"
        count++
        }
        document.getElementById("d"+obj.selectedIndex).style.display="block"

}
function SubmitForm(){
var searchBy=document.getElementById("searchBy").value;
if(searchBy=="1"){
document.distributorlist.action="viewDistributor.do?param=active";
document.distributorlist.submit();
return true;
}
if(searchBy=="2"){
document.distributorlist.action="viewDistributor.do?param=deactive";
document.distributorlist.submit();
return true;

}
if(searchBy=="3"){
document.distributorlist.action="viewDistributor.do?param=viewDistributor";
document.distributorlist.submit();
return true;

}
}
</script>

