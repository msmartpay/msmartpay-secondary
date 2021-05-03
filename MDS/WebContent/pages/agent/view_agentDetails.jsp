<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
HashMap map=(HashMap)session.getAttribute("info");
String flag=(String)map.get("flag");
String did=(String)session.getAttribute("distributorId");
//System.out.println("did :"+did);
String data="";
int size=0;
 String ControlButton=(String)session.getAttribute("ControlButton");
 session.setAttribute("controlButton",ControlButton);
System.out.println("hello we are at jsp page and ControlButton value is "+ControlButton);
ArrayList AgentList=(ArrayList)session.getAttribute("AgentList");
//System.out.println("AgentList: "+AgentList);

 if (AgentList==null)
					 {
					 AgentList=new ArrayList();
					// System.out.println("AgentList: "+AgentList);
						 size=-1;
						 data="NO DATA";
					 }
					    else{
						 size=AgentList.size();
						  }

System.out.println("size ***********: "+size);
//ArrayList distributorIdList=(ArrayList)session.getAttribute("distributorIdList");



/*
//String clientId=(String)session.getAttribute("clientId");
ArrayList ActiveAgentData=(ArrayList)request.getAttribute("ActiveAgentData"); 
ArrayList NotActiveAgentData=(ArrayList)request.getAttribute("NotActiveAgentData");
if(ActiveAgentData==null){
ActiveAgentData=new ArrayList();
}
if(NotActiveAgentData==null){
NotActiveAgentData=new ArrayList();
}
String activatedIds="";
for(int i=0;i<ActiveAgentData.size();i++){ 
 activatedIds=activatedIds+ActiveAgentData.get(i)+",";
}
String noActivatedIds="";
for(int i=0;i<NotActiveAgentData.size();i++){ 
 noActivatedIds=noActivatedIds+NotActiveAgentData.get(i)+",";
}
if(ActiveAgentData.size()>0){
int aLength=activatedIds.length();
activatedIds=activatedIds.substring(0,aLength-1);
}
if(NotActiveAgentData.size()>0){
int nLength=noActivatedIds.length();
noActivatedIds=noActivatedIds.substring(0,nLength-1);
}

*/
//System.out.println("NotActiveAgentData are=================="+noActivatedIds);
//System.out.println("ActiveAgentData are=================="+activatedIds);





String pageno=null;
String maxpagestr=null;
Integer maxpage=0;
	
	pageno=(String)request.getAttribute("page");
	if(pageno==null)
		pageno="0";

String message=(String)request.getAttribute("message");
if(message==null){
message="";
}


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Agents</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

 <SCRIPT language="javascript">
$(function(){
 
    // add multiple select / deselect functionality
    $("#sellect_all").click(function () {
          $('.checks_1').attr('checked', this.checked);
		  
	$(".btn_1").css("display","none");
	
	$(".btn_2").css("display","");

    });
	
	$(".check_click").click(function(){
	
	$(".btn_1").css("display","");
	$(".btn_2").css("display","none");
	
	})
	
	$("#activate_id").click(function(){
	
	                document.getElementById("distform").param.value="DoAllActivateAgent";
				//	document.getElementById("distform").distributorids.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);
				//	var Type="Activate";

					document.getElementById("distform").submit();
					return true;
	
	
	})
	
	$("#deactivate_id").click(function(){
	
	document.getElementById("distform").param.value="DoAllDeactivateAgent";
					//document.getElementById("distform").distributorids.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);
					//var Type="Deactivate";
					
					document.getElementById("distform").submit();
					return true;
	
	
	})
	
 
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    $("#checks_1").click(function(){
 
        if($(".distId").length == $(".case:checked").length) {
            $("#sellect_all").attr("checked", "checked");
        } else {
            $("#sellect_all").removeAttr("checked");
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
  <tr><td align="left" height="130" width="1070">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="22%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big">Agents</td>
	  
	   <td  valign="bottom" height="20" align="center" style="padding-left:80px" ><font color="#FF0000" size="4"><%=message%></font></td><td width="40%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40"> <%
  //System.out.println("ControlButton ---------------->>>>"+ControlButton);
	//ControlButton="viewAgentAll";
if(ControlButton.equals("viewAgentAll")){ %>
<input type="button"  class="btn_1" value="Activate" onclick="doDistributorAction('Activate')" style="width:100px;">&nbsp; &nbsp;
<input type="button" class="btn_1"  value="Deactivate" onclick="doDistributorAction('Deactivate')" style="width:100px;">

<input style="display:none;" type="button" id="activate_id"  class="btn_2" value="Activate">

<input style="display:none" type="button" id="deactivate_id" class="btn_2"  value="Deactivate">

<%}if(ControlButton.equals("active")){ %>

<input type="button" class="btn_1" value="Deactivate" onclick="doDistributorAction('Deactivate')" style="width:100px;">

<input style="display:none" type="button" id="deactivate_id" class="btn_2"  value="Deactivate">

<%} if(ControlButton.equals("deactive")){ %>

<input type="button" class="btn_1" value="Activate" onclick="doDistributorAction('Activate')" style="width:100px;">

<input style="display:none" type="button" id="activate_id"  class="btn_2" value="Activate">

<%}%>
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr><td colspan="100%" style="height:5px;"></td></tr>
	<tr><td width="100%" align="right" style="padding-right:25px;padding-top:5px;size:5;color:#FF0000">Page No.<input readonly type="text" style="width:20px;padding-right:4px;padding-left:4px;" value="<%out.print(Integer.parseInt(pageno)+1);%>"/>
	
	</td></tr>
	<tr><td colspan="100%" style="height:10px;"></td></tr>
	
	<%
	 int i=0;
	%>
  <form action="viewAgent.do" method="post" name="distform" id="distform">
 <input type="hidden" name="distributorids" id="allDistId1" />
<input type="hidden" name="param" value="" />

  <tr>
    <td width="100%" align="center" valign="top"><table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1">
      <tbody>
        <%
	  System.out.println("hello size is "+AgentList.size());
	/*   System.out.println("hellosize"+size);
	   System.out.println("data"+data);
	    System.out.println("flag"+flag);*/
		if(size<=0 || data.equalsIgnoreCase("NO DATA") ){%>
				<!--<tr style="border:none; background-color:#FFFFFF"><td height="80" colspan="100%" bgcolor="#FFFFFF" style="border:none;">dsfdsf</td></tr>-->
				<table style="border:1px solid #005CB9; width:99.48%; margin-bottom:2.50px;">
				<tr><td height="20"></td></tr>
				<tr><td height="20" align="center"><font color="#FF0000" size="3">Data is Not Available.</font></td></tr> 
					<tr><td height="20"></td></tr>
				</table>
				<tr><td height="80"></td></tr>
				<%}else{%> 
	   

        <tr  class="st" align="left" bgcolor="#dbe5f1">
          <td width="2%" align="center"><input type="checkbox" class="checks_1"  name="input_1" id="sellect_all"  /></td>
          <td width="2%" height="25"  align="center"><strong>S.N.</strong></td>
          <td width="6%" align="center"><strong>Agent ID</strong></td>
          <td width="16%"  align="center"><strong>Agent Email ID</strong></td>         
          <td width="7%" align="center"><strong>Mobile No. </strong></td>  
          <%if(flag.equals("0")){%>
          <td  width="5%" align="center"><strong>Status</strong></td>
          <%} if(flag.equals("1")){%>
          <td  width="7%" align="center"><strong>Status</strong></td>
          <%} if(flag.equals("2")){%>
          <td  width="7%" align="center"><strong>Status</strong></td>
          <%}%>
          
        </tr>
        <%for( i=0;i<size;i++){
      	HashMap temp=(HashMap)AgentList.get(i);
	//	System.out.println("temp :"+temp);
		
		maxpage=(Integer)temp.get("MaxPage");
	//System.out.println("maxpage :"+maxpage);
		maxpagestr=maxpage.toString();
		if(maxpagestr==null)
		maxpagestr="0";
	
		// 	System.out.println("inside distributor_id Button :" +temp.get("distributor_id") );
	
	
	%>
        <tr bgcolor="#FFFFFF">
          <td align="center" height="25px">
		  <input name="distid<%=i%>" type="checkbox" class="checks_1 check_click" value="<%=temp.get("distributor_id")%>" onclick="distIdAction()" id="distId"/></td>
		  <%								
							
								// System.out.println("distributor_id "+temp.get("distributor_id") +"   i is   n"+i);
									 //  System.out.println("login_status "+temp.get("mobileStatus"));
									   
									 String Status=(String)temp.get("login_status");
									 if(Status.equalsIgnoreCase("Activate")){
									 Status="Active";
									 }else{
									 Status="Deactive";
									 }
									 String mpin=(String)temp.get("mpin");
									 if(mpin==null){
									 mpin="NA";
									 }
									   
%>
          <td  align="center"><%=temp.get("SN.o")%></td>
          <td  align="center"><%=temp.get("distributor_id")%></td>
          <td  align="center"><%=temp.get("email_id")%></td>                
          <td align="center"><%=temp.get("mobile")%></td>  
          <%
		   String mobileStatus=(String)temp.get("mobileStatus");
		   
		   if(mobileStatus==null){
		   mobileStatus="Deactive";
		   }else if(mobileStatus.equalsIgnoreCase("Activate") || mobileStatus.equalsIgnoreCase("Active") ){
		   mobileStatus="Active";
		   }else{
		      mobileStatus="Deactive";
		   }
		   		   		
		   if(flag.equals("0")){%>
          <td align="center"><%=Status%></td>
          <%} if(flag.equals("1")){%>
          <td align="center"><%=mobileStatus%></td>
          <%} if(flag.equals("2")){%>
          <td align="center"><%=Status%></td>
          <%}%>
		 
        </tr>
      </tbody>
      <input type="hidden" name="allDistId" id="allDistId" value="" />
      <%}%>
    </table></td>
  </tr>
    <tr>
  
  
  <td height="20" align="center"></td>

			  
  
  </tr>
  <tr>
		
  
  <td align="center" colspan="10" >
  
  <table width="96%" cellpadding="0" cellspacing="0" border="0">
  <tr>
  <td width="40%">
	<a href="viewAgent.do?param=<%=ControlButton%>&page=0" style="text-decoration:none;">
	<input style="width:90px;" type="button" name="previous" id="previous"  value="Start" <% if(pageno.equals("0"))out.print("disabled=\"disabled\""); else out.print(""); %>  /></a>
	</td>
	<td>
	
	<a href="viewAgent.do?param=<%=ControlButton%>&page=<% out.print(Integer.parseInt(pageno)-1); %>" style="text-decoration:none;">
<input style="width:90px;" type="button" name="previous" id="previous" value="Previous" <% if(pageno.equals("0"))out.print("disabled=\"disabled\""); else out.print(""); %>  />
</a>
</td>
<td>

<a href="viewAgent.do?param=<%=ControlButton%>&page=<% out.print(Integer.parseInt(pageno)+1);%>"  style="text-decoration:none;">
<input style="width:90px;" type="button" name="next" id="next" value="Next" <% if(pageno.equals(maxpagestr))out.print("disabled=\"disabled\"");else  out.print("");%>  />
</a>
	</td>
	<td width="40%" align="right">
	
	 <a href="viewAgent.do?param=<%=ControlButton%>&page=<% out.print(Integer.parseInt(maxpagestr));%>"  style="text-decoration:none;">
<input style="width:90px;" type="button" name="next" id="next" value="End" <% if(pageno.equals(maxpagestr))out.print("disabled=\"disabled\"");else  out.print("");%>  />
</a>

</td>
</tr>
</table>
	 </td>
	 
 

 
 </tr>
   <tr>
  
  
  <td height="40" align="center"></td>

			  
  
  </tr>
   	</form>			   


		
				<%} %>
				 <% request.removeAttribute("message"); %>
</table>
</td>
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
	//alert('kkkkk'+deselectedIds);
}





function doDistributorAction(type){

//alert(type);
	
var allDiffId=selectIds.split('_');
	
		if(selectIds=="")
		{
			if((allDiffId.length-1)==0){
				alert("Please Select Atleast One agent");
				return false;
			}
		}

		var x=window.confirm("Please confirm the Action.");

	if (x)
	{
		
	

			if(type=='Activate')
				{
					
					//alert("inside activate");
					document.getElementById("distform").param.value="activateAgent";
					document.getElementById("distform").distributorids.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);
					document.getElementById("distform").submit();
					return true;
				}
		      if(type=='Deactivate')
				{
					
					//alert("inside deactivate");
					document.getElementById("distform").param.value="deactivateAgent";
					document.getElementById("distform").distributorids.value=selectIds;
					//alert(document.getElementById("distform").distributorids.value);

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
var distributorid=document.getElementById("distributorId").value;
//alert("distributorid"+distributorid);
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

