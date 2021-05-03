<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%


ArrayList agentList=(ArrayList)session.getAttribute("agentListMPIN");

if(agentList==null){
agentList=new ArrayList();
}

System.out.println("agentList is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+agentList);
ArrayList distributorIdList=null;
String message=(String)session.getAttribute("resendMpinMsg");
if(message==null){
message="";
}

String clientId=(String)session.getAttribute("clientId");
ArrayList ActiveAgentData=(ArrayList)request.getAttribute("ActiveAgentData"); 
ArrayList NotActiveAgentData=(ArrayList)request.getAttribute("NotActiveAgentData");






//System.out.println("NotActiveAgentData are=================="+noActivatedIds);
//System.out.println("ActiveAgentData are=================="+activatedIds);


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Agents</title>
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
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="45%" valign="bottom" height="40" align="left" style="padding-left:0px" class="big">Resend MPIN</td><td width="55%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40">
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
	<form name="agentlist"  method="post">
	  <table width="1000" border="0" cellspacing="0" cellpadding="0" class="border">
	
	
	   <tr>
	   <td colspan="5" align="center" ><font color="#FF0000" size="2"></font></td>
	   </tr>
	 
	   <tr>
	   <td colspan="5" align="center" ><%=message%></td>
	   </tr>
	 
	    <tr>
	   <td colspan="5" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
        
        <tr>
          <td width="30%"></td>
          <td width="10%" align="left" class="txtt"><strong>Search By</strong></td>
          <td width="5%" align="left" class="txtt">:</td>
          <td width="25%" align="left"><select class="txt" style="width:200px" id="searchBy" name="searchBy" n onchange="viewOnChange();">
              <option value="0">Please Select</option>
			   <option value="1">Agent List</option>
              <option value="2">Agent Id</option>
             
             
          </select></td>
          <td width="30%" height="33" align="left"><input name="button" type="button" onclick="return SubmitForm();" value="Submit" style="width:100px;"/></td>
          <td width="0%"></td>
        </tr>
		
		
		<tr id="list" style="display:none;">
          <td width="30%"></td>
          <td width="10%" align="left" class="txtt"><strong>Agent Id</strong></td>
          <td width="5%" align="left" class="txtt">:</td>
          <td width="25%" height="32" align="left"><input type="text" name="agentIdText" id="agentIdText"  /></td>
          <td width="30%" height="33" align="left">&nbsp;</td>
        </tr>
		  <tr>
	   <td colspan="5" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
      </table>
	</form>		</td>
    </tr>
	
	<%
	int i=0;
	%>
  <form action="resendMPIN.do" method="post" name="distform" id="distform">
 <input type="hidden" name="agentIds" id="allDistId1" />
<input type="hidden" name="param" value="" />

  <tr >
    <td width="100%" align="center" valign="top">
	
	
<table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1" id="tabAgent" >
      <tbody>
	   <%
	  //System.out.println("hello size is "+AgentList.size());
	  if(agentList.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">
          <td width="4%" height="25" align="center"><input name="" type="checkbox" value="" onclick="" id=""/></td>
          <td width="10%"><strong>Agent ID</strong></td>
          <td width="27%"><strong>Agency Name</strong></td>
          <td width="17%"><strong>Agent Name</strong></td>
          
          <td width="8%"><strong>Mobile No. </strong></td>
		  <td width="8%"><strong>MPIN</strong></td>
          
		   <td  width="9%"><strong>Mobile Status</strong></td>
        </tr>
		  <%
		 
		  for( i=0;i<agentList.size();i++){
  HashMap temp=(HashMap)agentList.get(i);
	
	
	
	
	
	
	%>
        <tr bgcolor="#FFFFFF">
          <td align="center"><input name="agentId<%=i%>" type="checkbox" value="<%=temp.get("agentId")%>" onclick="distIdAction()" id="distId"/></td>
          <td align="left"><%=temp.get("agentId")%></td>
          <td align="left"><%=temp.get("agencyName")%></td>
          <td><%=temp.get("agentName")%></td>
         
          <td align="left"><%=temp.get("mobileNo")%></td>
       <td align="left"><%=temp.get("mpin")%></td>
		   <td align="left"><%=temp.get("mobileStaus")%></td>
        </tr>
		  <input type="hidden" name="allDistId" id="allDistId" value="" />
		 
		
		
		 <%}%>
      </tbody>
    </table>	</td>
  </tr>
  <tr><td height="40" align="center">
  
<input type="button" value="Resend MPIN" onclick="doDistributorAction()">


  </td></tr>
   	</form>			   


		
				<%}else{%>
				<tr><td height="20" align="center">No Agent Found</td></tr> 
				<%}%> 
				<tr bgcolor="#FFFFFF"><td height="20" align="center">&nbsp;</td></tr> 
</table>
</td>
<tr>
	   <td colspan="5" align="center" ><font color="#FF0000" size="2">&nbsp;</font></td>
	   </tr>
</tr>
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
			if(typeA=='agentId'+j && table[i].checked==true)
			{
				//alert("Hi");
				allIdsSelected+=table[i].value+"_";
			}
			if(typeA=='agentId'+j && table[i].checked==false)
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





function doDistributorAction(){
	
var allDiffId=selectIds.split('_');
	
		if(selectIds=="")
		{
			if((allDiffId.length-1)==0){
				alert("Please Select Atleast One agent");
				return false;
			}
		}

		var x=window.confirm("Are you sure you want to send MPIN to these agent(s) ,if yes OK to continue else click on CANCEL.");

	if (x)
	{
		
	

			
					
					//alert("inside activate");
					document.getElementById("distform").param.value="sendMpin";
					document.getElementById("distform").agentIds.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);
					document.getElementById("distform").submit();
					return true;
				
					
					
					
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


document.agentlist.action="resendMPIN.do?param=viewAgentList";
document.agentlist.submit();
return true;

}
if(searchBy=="2"){
var agentId=document.getElementById("agentIdText").value;

document.agentlist.action="resendMPIN.do?param=viewAgentInfo&aid="+agentId;
document.agentlist.submit();
return true;

}



}



function viewOnChange(){
var option=document.getElementById("searchBy").value;
if(option=="1")
{

document.getElementById("list1").style.display="none";

}
if(option=="2")
{
document.getElementById("list").style.display="";
}
}
</script>

