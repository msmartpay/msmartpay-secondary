<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%@ page import = "java.math.BigDecimal" %>
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
 String message=(String)request.getAttribute("message");
 if(message==null){
 message="";
 }


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Distributors</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<SCRIPT language="javascript">
$(function(){
 
    // add multiple select / deselect functionality
    $("#selectall").click(function () {
          $('.check_1').attr('checked', this.checked);
		  
		  $(".btn_1").css("display","none");
	
	$(".btn_2").css("display","");
    });
 
 $(".check_click").click(function(){
	
	$(".btn_1").css("display","");
	$(".btn_2").css("display","none");
	
	})
	
	$("#activate_id").click(function(){
	    	//alert("activate");	
	        document.getElementById("distform").param.value="DoAllActivateDistributor"; 
			document.getElementById("distform").submit();
					return true;
	})
	
	$("#deactivate_id").click(function(){
		//	alert("Deactivate");
			document.getElementById("distform").param.value="DoAllDeactivateDistributor";  
			document.getElementById("distform").submit();
					return true;
	})
	
 
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    $(".case").click(function(){
 
        if($(".check_1").length == $(".check_1:checked").length) {
            $("#selectall").attr("checked", "checked");
        } else {
            $("#selectall").removeAttr("checked");
        }
 
    });
});
</SCRIPT>


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
  <tr><td align="left" height="91" width="1070">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<!--<tr><td width="8%" valign="center" height="40" align="left" style="padding-left:20px" class="big">View Distributor</td>
	</tr>-->
	<tr>
	<tr>  <td width="2%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big"></td></tr>

	<tr>  <td width="2%" valign="bottom" height="20" align="left" style="padding-left:20px" class="big">View Distributor</td></tr>
	
	 <tr>
<td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="distributorlist"  method="post">
<tr><td width="100%" colspan="5" align="center" ><font color="#FF0000" size="2"><%=message%></font></td> </tr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%  if(noActivatedIds!=""){
		String UpdatedStatus=(String)request.getAttribute("UpdatedStatus");
		
		%>
	   <tr>
	   <td width="100%" colspan="5" align="center" height="20" valign="top"><font color="#FF0000" size="4">Process aborted due to Technical Failure.</font></td>
	   </tr>
	   <%}%>
	   <%  if(activatedIds!=""){
		   String UpdatedStatus=(String)request.getAttribute("UpdatedStatus");%>
	   <tr>
	   <td colspan="5" align="center" height="20"  valign="top"><font color="#FF0000" size="4">Selected User is <%=UpdatedStatus%></font></td>
	   </tr>
	   <%}%>
	</table>
	</form>		</td></tr>
	 	  <td width="100%" style="padding-right:0px">
	<table width="100%" height="81" border="0" cellpadding="0" cellspacing="0" class="border">
	<tr>
	 <td width="1%" align="center" valign="center" class="txtt">&nbsp;</td>
	  <td width="20%" align="right" valign="right" class="txtt"><strong>Search By</strong></td>
	  <td width="4%" align="right" valign="right" class="txtt">:</td>
	  <td width="37%" align="center" valign="center"><select class="txt" style="width:200px" id="searchBy" name="searchBy" onchange="return SubmitForm();">
          <option value="0">Please Select</option>
          <option value="1">Active Distributor</option>
          <option value="2">Deactive Distributor</option>
          <option value="3" >All</option>
	    </select></td>
		
	  <td width="38%" height="50" align="center" valign="center">   <%
 
	
if(ControlButton.equals("AllActiveDis")){ %>
<input type="button" class="btn_1" value="Activate" onclick="doDistributorAction('Activate')" style="width:100px;"> &nbsp; &nbsp;
<input type="button" class="btn_1" value="Deactivate" onclick="doDistributorAction('Deactivate')" style="width:100px;">

<input style="display:none" type="button" id="activate_id"  class="btn_2" value="Activate">

<input style="display:none" type="button" id="deactivate_id" class="btn_2"  value="Deactivate">

<%}if(ControlButton.equals("active")){ %>

<input style="display:none" type="button" id="deactivate_id" class="btn_2"  value="Deactivate">

<input type="button" class="btn_1" value="Deactivate" onclick="doDistributorAction('Deactivate')" style="width:100px;">

<%} if(ControlButton.equals("deactive")){ %>

<input type="button" class="btn_1" value="Activate" onclick="doDistributorAction('Activate')" style="width:100px;">

<input style="display:none" type="button" id="activate_id"  class="btn_2" value="Activate">


<%}%>
   	</td>
	</tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr>
    
<td width="100%" valign="top"  style="padding-top:6px; padding-bottom:10px">


	<table width="50%" border="0" cellspacing="0" cellpadding="0">
	
	</table>
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
	
	
<table cellspacing="1" cellpadding="1" width="100%" align="center" class="txt"  bgcolor="#b8cbe1">
      <tbody>
	   <%
	  
	   if(distributorList.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">
          <td width="3%" height="37" align="center" valign="middle"> <input name="" type="checkbox" value="all" id="selectall" /></td>
          <td width="10%" align="center" valign="middle"><strong>Distributor ID</strong></td>
          <td width="27%" align="left" valign="middle" style="padding-left:5px;"><strong>Firm Name</strong></td>
          <td width="17%" align="left" valign="middle" style="padding-left:5px;"><strong>Authorised Person </strong></td>
          <td width="8%" align="center" valign="middle"><strong>Mobile No. </strong></td>
          <td width="10%" align="center" valign="middle"><strong>Current Balance</strong></td>
          <td  width="9%" align="left" valign="middle" style="padding-left:5px;"><strong>Current Status</strong></td>
          <td  width="9%" align="center" valign="middle" style="padding-left:5px;"><strong>Action</strong></td>
        </tr>
		  <%for( i=0;i<distributorList.size();i++){
  	HashMap temp=(HashMap)distributorList.get(i);
	
	
String status=(String)temp.get("login_status");
if(status.equalsIgnoreCase("Deactivate")){
status="Deactive";
}
else if( status.equalsIgnoreCase("Activate") ){
status="Active";
}
else{
status=status;
}	
		String Amount=(String)temp.get("balance");
		double DsBalance = Double.parseDouble(Amount);
		BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
		BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
	%>
        <tr bgcolor="#FFFFFF">
          <td height="27" align="center" valign="middle">
          <input type="hidden" name="allDistId" id="allDistId" value="" />
          <input name="distid<%=i%>" class="check_1 check_click" type="checkbox" value="<%=temp.get("distributor_id")%>" onclick="distIdAction()" id="distId"/></td>
          <td align="center" valign="middle"><%=temp.get("distributor_id")%></td>
          <td align="left" valign="middle" style="padding-left:5px;"><%=temp.get("company_name")%></td>
          <td align="left" valign="middle" style="padding-left:5px;"><%=temp.get("contact_person")%></td>
          <td align="center" valign="middle"><%=temp.get("mobile")%></td>
          <td align="center" valign="middle"><%=Balance%></td>
          <td align="left" valign="middle" style="padding-left:5px;"><%=status%></td>
          <td align="center"><a href="pushBalance.do?param=pushBalance&distId=<%=temp.get("distributor_id")%>"><strong>Push</strong></a></td>
        </tr>
		  
		 
		
		
		 <%}%>
      </tbody>
    </table>	</td>
  </tr>
  <tr><td height="40" align="center" valign="middle">

   
  </td></tr>
   	</form>			   


		
				<%}else{%>
				 	<table style="border:1px solid #005CB9; width:99.48%; margin-bottom:2.50px;">
				<tr><td height="20" align="center"><font color="#FF0000" size="2">No Distributor Present.</font></td></tr> 
				</table>
				<%}%> 
				 
</table>
</td></tr>
  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
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

		var x=window.confirm("Please confirm the Action.");

	if (x)
	{

			if(type=='Activate')
				{
					
					//alert("inside activate");
					document.getElementById("distform").param.value="activateDistributor";
					document.getElementById("distform").distributorids.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);
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

