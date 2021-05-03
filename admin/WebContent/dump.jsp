<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page import="java.util.*" %>

<%
String vendor="";
ArrayList list=(ArrayList)request.getAttribute("getDetails");

//String mdshare="Variable";
//String clientshare="Variable";
int size=0;
if(list!=null)
{
	size=list.size();
}
else
{
	size=-1;
}
	// System.out.println("listbncghbgch   tetsing  :");
	String service=(String)request.getAttribute("service");
	
	if((service=="") || (service==null))
	{
		service="NA";
	}
	
String vertical=(String)request.getAttribute("vertical");
if((vertical=="") || (vertical==null))
{
	vertical="NA";
}

String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

String flag=(String) request.getAttribute("flag");
if(flag==null)flag="N";

%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<!--<link href="css/superadmin.css" rel="stylesheet" type="text/css" />-->
<link rel="icon" href="images/t.png" />

<style>

.tbls tbody tr td{ border:1px solid #930; border-right:none;   height:25px; font-family:"Trebuchet MS"; font-size:13px;}
.bords td{border-bottom:0px solid #930; font-family:"Trebuchet MS"; font-size:13px;}

.hr_tbls tr td{border:0px solid #930;font-family:"Trebuchet MS"; font-size:13px;}
.hr_tbls tr td strong{ margin-left:30px;}
select{ height:25px; padding:3px 0 3px 3px; width:225px;font-size:13px; font-family:"Trebuchet MS";}
option{font-size:13px; font-family:"Trebuchet MS";}

</style>

<script>
function click_chng()
{
	var vartical = document.myforms.vartical.value;
	
	if(vartical == "web")
	{
		document.getElementById("sms_service").style.display="none"; 
		document.getElementById("web_service").style.display="";
		document.getElementById("space_1").style.display=""; 
		document.getElementById("space_4").style.display="none"; 
		document.getElementById("operatorlist").style.display="none"; 
		document.getElementById("utilityoperatorlist").style.display="none"; 
	}
	
	if(vartical == "sms")
	{
		document.getElementById("sms_service").style.display=""; 
		document.getElementById("web_service").style.display="none"; 
		document.getElementById("space_1").style.display="none"; 
		document.getElementById("space_4").style.display="";
		document.getElementById("operatorlist").style.display="none"; 
		document.getElementById("utilityoperatorlist").style.display="none"; 
	}
	
	if(vartical == "select")
	{
		document.getElementById("sms_service").style.display=""; 
		document.getElementById("web_service").style.display="none"; 
		document.getElementById("space_1").style.display="none"; 
		document.getElementById("space_4").style.display="";
		document.getElementById("operatorlist").style.display="none"; 
		document.getElementById("utilityoperatorlist").style.display="none"; 
	}
}

function serviceclick()
{
	var smsservice = document.myforms.smsservice.value;
	if(smsservice == "recharge")
	{
		document.getElementById("operatorlist").style.display=""; 
		document.getElementById("utilityoperatorlist").style.display="none"; 
		document.getElementById("space_3").style.display=""; 
		document.getElementById("space_5").style.display="none";
	}
	
	if(smsservice == "utility")
	{
		document.getElementById("operatorlist").style.display="none"; 
		document.getElementById("space_3").style.display="none"; 
		document.getElementById("utilityoperatorlist").style.display=""; 
		document.getElementById("space_5").style.display="";
	}
	
	if(smsservice == "select")
	{
		document.getElementById("operatorlist").style.display="none"; 
		document.getElementById("utilityoperatorlist").style.display="none"; 
		document.getElementById("space_3").style.display="none"; 
		document.getElementById("space_5").style.display="none";
	}
}

function dublevents()
{
	
	 var vartical = document.myforms.vartical.value;
     var webservice = document.myforms.webservice.value;
     var smsservice = document.myforms.smsservice.value;
     var operator = document.myforms.operator.value;
	 
	 if(vartical == "web")
	 {
		document.getElementById("control_tbl").style.display=""; 
		document.getElementById("utility_tbl").style.display="none"; 
		document.getElementById("rech_tbl").style.display="none"; 
	 }
	 
	  if(vartical == "sms")
	 {
		 if(smsservice == "recharge")
	 {
		document.getElementById("control_tbl").style.display="none"; 
		document.getElementById("utility_tbl").style.display="none"; 
		document.getElementById("rech_tbl").style.display=""; 
	 }
	 
	 }
	 
	 if(vartical == "sms")
	 {
		 if(smsservice == "utility")
	 {
		document.getElementById("control_tbl").style.display="none"; 
		document.getElementById("utility_tbl").style.display=""; 
		document.getElementById("rech_tbl").style.display="none"; 
	 }
	 
	 }
}

</script>

<script>
function sbmt_click()
{
  var vartical = document.myforms.vartical.value;
  
  if(vartical == "select")
  {
	  alert("Please Select the Required Field.");
	  return false;
  }
  
  if(vartical == "sms")
  {
	   var smsservice = document.myforms.smsservice.value;
   	  	if(smsservice == "select")
	  	{
		  alert("Please Select the Required Field.");
		  return false;
	  	}
	 
	  if(smsservice == "utility")
	  {
		   var utilityoperatorlist = document.myforms.utility_operatorlist.value;   
		  if(utilityoperatorlist == "select")
		  {
			  alert("Please Select the Required Field.");
		  
			  return false;
		  }
	  }
	  
	 if(smsservice == "recharge")
 	 {
		 var operator = document.myforms.operator.value;
		if(operator == "select")
		{
			 alert("Please Select the Required Field.");
			  return false;  
		}
 	 }
  }
  
  if(vartical == "web")
  {
	   var webservice = document.myforms.webservice.value;
		  if(webservice == "select")
	  {
		  alert("Please Select the Required Field.");
		  return false;
	  }
  }
  
  document.myforms.action="OpsServiceManagement.action?param=getDetails";
	//alert(document.myforms.action);
	 document.myforms.submit();
}
</script>

</head>

<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center"
	border="0">
	<tr>
		<td width="100%" valign="top" align="center"><%@ include file="/header.jsp"%></td>
	</tr>
	<tr>
		<td align="center" width="100%" valign="top">
		<table cellpadding="0" cellspacing="0" width="90%" align="center"
			border="0">
			<tr>
				<td valign="top" align="center" class="rounded-corners">
				<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="form11" >
					<tr>
						<td valign="top" align="center">
						<table cellpadding="0" cellspacing="0" width="100%" align="center">
							<tr>
								<td height="40" align="left" valign="middle" style="font-size:13px; padding-left:60px; font-weight:bold;" class="logintxt">Service Management</td>
							</tr>
							
							<form name="myforms" method="post" >
							<tr>
								<td colspan="4">

								<table class="hr_tbls" cellpadding="0" cellspacing="0" width="100%" >
                                      <tr>
                                      <td width="30%"></td>
                                      <td  width="10%"><strong>Vertical</strong></td><td width="10%" align="center">:</td>
                                      <td width="60%"><select name="vartical" onchange="click_chng()">
                                      <option value="select" selected="selected">Select</option>
                                      <option value="web">Web</option> 
                                      <option value="sms">SMS</option>
                                      </select></td>
                                      </tr>
                                      
                                      <tr id="space_1">
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr id="web_service">
                                      <td></td>
                                      <td><strong>Service</strong></td><td align="center">:</td>
                                      <td><select name="webservice">
                                      <option value="select" selected="select">Select</option>
                                      <option value="all">All</option>
                                      <option value="flight">Flights</option>
                                      <option value="bus">Bus</option>
                                      <option value="hotel">Hotel</option>
                                      <option value="recharge">Recharge</option>
                                      <option value="utility">Utility</option>
                                      <option value="paycard">PayCard</option>
                                      <option value="testmerit">TestMerit</option>
                                      <option value="dth">DTH-X</option>
                                      <option value="ops">Ops</option>
                                      <option value="sms">SMS Txn</option>
                                      </select></td>
                                      </tr>
                                      <tr id="space_2">
                                      <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr id="sms_service" style="display:none;">
                                      <td></td>
                                      <td><strong>Service</strong></td><td align="center">:</td>
                                      <td><select name="smsservice" onchange="serviceclick()">
                                      <option value="select" selected="select">Select</option>
                                      <option value="recharge">Recharge</option>
                                      <option value="utility">Utility</option>
                                      </select></td>
                                      </tr>
                                      <tr id="space_3" style="display:none;">
                                      <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr id="operatorlist" style="display:none;">
                                      <td></td>
                                      <td><strong>Operator</strong></td><td align="center">:</td>
                                      <td><select name="operator">
                                      <option value="select"  selected="select">Select</option>
                                      <option value="all">All</option>
                                      <option value="Aircel">Mobile - Aircel</option>
                                        <option value="Airtel">Mobile - Airtel</option>
                                        <option value="IDEA">Mobile - Idea</option>
                                        <option value="VODAFONE">Mobile - Vodafone</option>
                                        <option value="BSNL">Mobile - BSNL - Top Up</option>
                                        <option value="BSNLRECH">Mobile - BSNL - Validity</option>
                                        <option value="MTNLT">Mobile - MTNL - Top Up</option>
                                        <option value="MTNLV">Mobile - MTNL - Validity</option>
                                        <option value="MTS">Mobile - MTS</option>
                                        <option value="RELIANCECDMA">Mobile - Reliance - CDMA</option>
                                        <option value="RELIANCEGSM">Mobile - Reliance - GSM</option>
                                        <option value="TATA">Mobile - Tata Indicom</option>
                                        <option value="TATADOCOMO">Mobile - Docomo - Top Up</option>
                                        <option value="TATADOCOMOSP">Mobile - Docomo - Special</option>
                                        <option value="UNINOR">Mobile - Uninor - Top Up</option>
                                        <option value="UNINORS">Mobile - Uninor - Special</option>
                                        <option value="VIDEOCONT">Mobile - Videocon - Top Up</option>
                                        <option value="VIDEOCONSP">Mobile - Videocon - Special</option>
                                        <option value="VIRGINCDMA">Mobile - Virgin - CDMA</option>
                                        <option value="VIRGINGSM">Mobile - Virgin - GSM</option>
                                        <option value="BPL">Mobile - Loop - BPL</option>
                                        <option value="Airteldth">DTH - Airtel DTH</option>
                                        <option value="BIGTV">DTH - BIG TV</option>
                                        <option value="DISH">DTH - Dish TV</option>
                                        <option value="SUNDIRECT">DTH - Sun Direct</option>
                                        <option value="TATASKY">DTH - Tata Sky</option>
                                        <option value="VIDEOCONDTH">DTH - Videocon D2H</option>
                                        <option value="datacardtata">Data Card - Tata</option>
                                        <option value="datacardreliance">Data Card - Reliance</option>
                                        <option value="datacardmts">Data Card - MTS</option>
                                        <option value="datacardaircel">Data Card - Aircel</option>
                                        <option value="dtatcardbsnl">Data Card - BSNL</option>
                                        <option value="datacardidea">Data Card - Idea</option>
                                      </select></td>
                                      </tr>
                                      
                                      <tr id="space_4" style="display:none;">
                                      <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr id="utilityoperatorlist" style="display:none;">
                                      <td></td>
                                      <td><strong>Operator</strong></td><td align="center">:</td>
                                      <td><select name="utility_operatorlist">
                                      <option value="select"  selected="select">Select</option>
                                      <option value="all">All</option>
                                        <option value="Aircel chennai">Aircel chennai</option>
                                        <option value="Airtel Landline">Airtel Landline</option>
                                        <option value="Airtel Postpaid">Airtel Postpaid</option> 
                                        <option value="BSES Rajdhani">BSES Rajdhani</option>
                                        <option value="BSES Yamuna">BSES Yamuna</option>
                                        <option value="BSNL PostPaid">BSNL PostPaid</option>
                                        <option value="Cellone PostPaid">Cellone PostPaid</option>
                                        <option value="DLF Pramerica">DLF Pramerica</option>
                                        <option value="Docomo Postpaid">Docomo Postpaid</option>
                                        <option value="ICICI Pru Life">ICICI Pru Life</option>
                                        <option value="IDEA Postpaid">IDEA Postpaid</option>
                                        <option value="ING Vysya Life">ING Vysya Life</option>
                                        <option value="L&T General">L&T General</option>
                                        <option value="Life Insurance Corporation">Life Insurance Corporation</option>
                                        <option value="Loop Mobile PostPaid">Loop Mobile PostPaid</option>
                                        <option value="Mahanagar Gas Limited">Mahanagar Gas Limited</option>
                                        <option value="MTNL Delhi">MTNL Delhi</option>
                                        <option value="Noida Power Co. Ltd.">Noida Power Co. Ltd.</option>
                                        <option  value="North Delhi Power Limited">North Delhi Power Limited</option>
                                        <option value="Reliance Energy (Mumbai)">Reliance Energy (Mumbai)</option>
                                        <option value="reliancelandline">Reliance landline</option>
                                        <option value="Reliance Mobile">Reliance Mobile</option>
                                        <option value="TATA AIA Life">TATA AIA Life</option>
                                        <option value="Tata AIG Life">Tata AIG Life</option>
                                        <option value="TATA-Mobile">Tata Indicom Land Line</option>
                                        <option value="Tata Landline">Tata Landline</option>
                                       <!-- <option value="tatapostpaid">Tata PostPaid</option>-->
                                        <option value="Vodafone Postpaid">Vodafone Postpaid </option>
                                      </select></td>
                                      </tr>
                                      
                                      <tr id="space_5" style="display:none;">
                                       <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr>
                                      <td></td><td></td> <td></td>
                                      <td align="left"><input style="width:90px; margin-left:133px;" type="button" value="Submit" onclick="sbmt_click()" /> </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      </table>

								</td>
							</tr>

							</form>
							<tr>
								<td colspan="4" align="center">
                     
                      <!-- service Management - Recharge table start-->
					    <% if(flag.equalsIgnoreCase("Y"))
   				           {
				 
    		          	%>
              				<%if(vertical.equalsIgnoreCase("sms"))
              				{
              				
              				%>
					    	<%if(service.equalsIgnoreCase("recharge")){
					    	
					    	%>
                    	 <form name="form1">
					                      
                     <table id="rech_tbl" cellspacing="0" cellpadding="0" width="60%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd" align="center" style="background:#a74312;">
                                         <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; font-size:13px;">Operator Control - Recharge</th>
                                        </tr>
					
                                          <tr  class="hd" align="center" style="background:#ce571b;">
                                          
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;">S.N</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Service Type</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Service</td>
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;">
                                            <select class="actionclass" name="myselects" onchange="myfunc()" style="width:80px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                            </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Vendor</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;">Status</td>
                                          </tr>
										
           			  					 <%
											for(int i=0;i<size;i++)
											{
												int j=i+1;
											HashMap map=(HashMap)list.get(i);
										%>
                                         
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;"><%=j%></td>
                                            <td style="border-bottom:1px solid #930;"><%=map.get("Sub_Service")%></td>
                                            <td style="border-bottom:1px solid #930;" align="center"><%=map.get("SKU_Name")%></td>
                                            <td style="border-bottom:1px solid #930;">
                                            <select class="select1" name="myoption1" style="width:45px;padding:1px;margin:5px;height:20px;">
                                            
                                            <option selected="selected" value="<%=map.get("SMS_Vendor_Status")%>"><%=map.get("SMS_Vendor_Status")%></option>
                                            <%
                                            String main_service=(String)map.get("Main_Service");
                                            char status=(Character)map.get("SMS_Vendor_Status");
                                            //char status=(Character)map.get("SMS_Vendor_Status");
                                            if(status=='Y')
                                            {
                                            %>
                                            
                                            <option value="N">N</option></select>
                                            <%}
                                            else{
                                            %>
                                             <option value="N">Y</option></select>
                                             <%} %>
                                            </td>
                                            <td style="border-bottom:1px solid #930;" align="center">
                                            <select  style="width:100px;padding:1px;margin:5px;height:20px;">
                                            <option value="select"></option>
                                            <option selected="selected" value="<%=map.get("Sms_Vendor")%>"><%=map.get("Sms_Vendor")%></option>
                                            <%
                                            vendor=(String)map.get("Sms_Vendor");
                                            if(vendor.equalsIgnoreCase("CYBERPLAT"))
                                            {
                                            %>
                                            <option value="ROOT">ROOT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="RIN">RIN</option>
                                            <option value="SELF">SELF</option>
                                            <%}else if(vendor.equalsIgnoreCase("ROOT")) {%>
                                            
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="RIN">RIN</option>
                                            <option value="SELF">SELF</option>
                                            <%}else if(vendor.equalsIgnoreCase("EZYPAY")) {%>
                                            
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                           	<option value="ROOT">ROOT</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="RIN">RIN</option>
                                            <option value="SELF">SELF</option>
                                            <%} else if(vendor.equalsIgnoreCase("GOAL")) {%>
                                            
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                           	<option value="ROOT">ROOT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="RIN">RIN</option>
                                            <option value="SELF">SELF</option>
                                            <%} else if(vendor.equalsIgnoreCase("RIN")) {%>
                                            
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                           	<option value="ROOT">ROOT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="SELF">SELF</option>
                                            <%}else if(vendor.equalsIgnoreCase("SELF")) {%>
                                            
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                           	<option value="ROOT">ROOT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="RIN">RIN</option>
                                            <%} else{%>
                                            
                                            
                                            <option value="NA">NA</option>
                                            <%} %>
                                            </select>
                                            </td>
                                            <td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center"><%=map.get("SMS_Vendor_Status")%></td>                                            
                                          </tr>
                                          
                                           <%}%>
                                          </tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input style="width:90px;" type="button" value="Update"  /></td>
                                        </tr>
                                      </table>
                                      
                     </form>
                      <%}%>
                     <!-- service Management - Recharge table stop-->
                     
                      <!-- service Management - Utility table start-->
                       <%if(service.equalsIgnoreCase("utility")){
                       
                       %>
                      
                     <form name="form2">
                     <table id="utility_tbl" cellspacing="0" cellpadding="0" width="60%" align="center" class="tbls" >
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd" align="center" style="background:#a74312;">
                                        <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; font-size:13px;">
                                        Operator Control - Utility 
                                        </th>
                                        </tr>
					
                                          <tr  class="hd" align="center" style="background:#ce571b;">
                                          <td width="5%" align="center" style="border-bottom:0px solid #930;">S.N.</td>
                                          <td width="10%" align="center" style="border-bottom:0px solid #930;">Service Type</td>
                                          <td width="10%" align="center" style="border-bottom:0px solid #930;">Service</td>
                                          <td width="5%" align="center" style="border-bottom:0px solid #930;border-right:0px solid #930;">
                                            <select class="actionclass" name="myselects" onchange="myfunc2()" style="width:80px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                          </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Vendor</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;">Status</td>
                                          </tr>
                                          
                                           
                         				<%
											for(int i=0;i<size;i++)
											{
												int j=i+1;
											HashMap map=(HashMap)list.get(i);
										%>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                          <td align="center" style="border-bottom:0px solid #930;"><%=j%></td>
                                          <td style="border-bottom:0px solid #930;"><%=map.get("Sub_Service")%></td>
                                          <td style="border-bottom:0px solid #930;" align="center"><%=map.get("SKU_Name")%></td>
											
                                         <td style="border-bottom:0px solid #930;"><select class="select1" name="myoption1" style="width:45px;padding:1px;margin:5px;height:20px;"> 
                                            <option selected="selected" value="<%=map.get("SMS_Vendor_Status")%>"><%=map.get("SMS_Vendor_Status")%></option>
                                            <%
                                            String main_service=(String)map.get("Main_Service");
                                            char status=(Character)map.get("SMS_Vendor_Status");
                                            //char status=(Character)map.get("SMS_Vendor_Status");
                                            if(status=='Y')
                                            {
                                            %>
                                            
                                            <option value="N">N</option></select>
                                            <%}
                                            else{
                                            %>
                                             <option value="N">Y</option></select>
                                             <%} %>
										 </td>
                                            <td style="border-bottom:0px solid #930;">
                                            <select style="width:100px;padding:1px;margin:5px;height:20px;">
                                            <option value="select">Select</option>
                                            <option selected="selected" value="<%=map.get("Sms_Vendor")%>"><%=map.get("Sms_Vendor")%></option>
                                            <%
                                            	vendor=(String)map.get("Sms_Vendor");
                                            if(vendor.equalsIgnoreCase("CYBERPLAT")){
                                            %>
                                        
                                            <option value="SELF">SELF</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendor.equalsIgnoreCase("SELF")) {%>
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                            <option value="NA">NA</option>
                                            <%} %>
                                            </select>
                                         </td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=map.get("SMS_Vendor_Status")%></td>                                            
                                          </tr>
										   <% }%>
                                          </tbody>
                                          
                                        <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input style="width:90px;" type="button" value="Update"  /></td>
                                        </tr>
                                      </table>
                     </form>
					  <%}}%>
                     <!-- service Management - Utility table stop-->
                     
                     
                     <!-- service control - table start-->
					    <%if(vertical.equalsIgnoreCase("web")){
					    
					    %> 
					 
                     <form name="form3">
                     <table id="control_tbl" cellspacing="0" cellpadding="0" width="60%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd" align="center" style="background:#a74312;">
                                        <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; font-size:13px;">
                                        Service Control
                                        </th>
                                        </tr>
					
                                          <tr  class="hd" align="center" style="background:#ce571b;">
                                          
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;">S.N.</td>
											<td width="10%" align="center" style="border-bottom:0px solid #930;">Service Type</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Service</td>
                                            <td width="3%" align="center" style="border-bottom:0px solid #930;border-right:0px solid #930;">
         	             <select class="actionclass" id="myselects" name="myselects" onclick="test(<%= size%>)" style="width:80px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                           </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;">Vendor</td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;">Status</td>
                                          </tr>
										      
                                         <%
											for(int i=0;i<size;i++)
											{
												int j=i+1;
											HashMap map=(HashMap)list.get(i);
										%>
                                                         
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                          <td align="center" style="border-bottom:0px solid #930;"><%=j%></td>
										  <td width="10%" align="center" style="border-bottom:0px solid #930;"><input type="text" id="sub_service" value="<%=map.get("Sub_Service")%>" style="border: none;" /></td>
                                          <td style="border-bottom:0px solid #930;" align="center"><input type="text" id="sku_name" value="<%=map.get("SKU_Name")%>" style="border: none;" /></td>
                      <td style="border-bottom:0px solid #930;"> <select class="select1" id="myoption<%=j%>" name="myoption" style="width:45px;padding:1px;margin:5px;height:20px;">
											
                                            <option selected="selected" value="<%=map.get("Tep_Vendor_Status")%>"><%=map.get("Tep_Vendor_Status")%></option>
											
                                           <%
                                           	String sub_service=(String)map.get("Sub_Service");
                                            char status=(Character)map.get("Tep_Vendor_Status");
                                            //char status=(Character)map.get("SMS_Vendor_Status");
                                            if(status=='Y')
                                            {
                                            %>
                                            
                                            <option value="N">N</option></select>
                                            <%}
                                            else{
                                            %>
                                             <option value="N">Y</option></select>
                                             <%} %>
											
											
											<td style="border-bottom:0px solid #930;">
                                            <select style="width:100px;padding:1px;margin:5px;height:20px;">
                                            <option value="select">Select</option>
                                            <option selected="selected" value="<%=map.get("Sms_Vendor")%>"><%=map.get("Tep_Vendor")%></option>
                                            <% 
                                            if((sub_service.equalsIgnoreCase("mobile"))||(sub_service.equalsIgnoreCase("datacard"))||(sub_service.equalsIgnoreCase("dth")))
                                            {
                                            
                                            %>
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                            <option value="ROOT">ROOT</option>
                                            <option value="EZYPAY">EZYPAY</option>
                                            <option value="GOAL">GOAL</option>
                                            <option value="RIN">RIN</option>
                                            <option value="SELF">SELF</option>
                                            <%} 
                                            else if(sub_service.equalsIgnoreCase("billpay"))
                                            {
                                            %>
                                            <option value="CYBERPLAT">CYBERPLAT</option>
                                            <option value="SELF">SELF</option>
                                            <%} else
                                            {%>
                                            <option value="NA">NA</option>
                                            <%} %>
                                            </select>
                                         </td>
                                            
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=map.get("Tep_Vendor_Status")%></td>                                            
                                          </tr>
												<%} %>
                                          </tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input style="width:90px;" type="button" value="Update"  /></td>
                                        </tr>
                                      </table>
									  
                     </form>
					  <%}}%>
					  
					 
                     <!-- service control - table stop-->
					   
								</td>
							</tr>
                            
							<tr>
								<td colspan="4" height="30"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
    <tr>
		<td width="100%" height="174px" valign="top" align="center">
		
        </td>
	</tr>
	<tr>
		<td width="100%" valign="top" align="center">
		<%@ include file="/footer.jsp"%>
        </td>
	</tr>
</table>
</body>
</html>

<script>
function myfunc()
{
	var val = document.form1.myselects.value;
if(val == "actions")
{
	 return false;
}
if(val == "y")
{
document.form1.myoption1.options[0].selected=true;
document.form1.myoption2.options[0].selected=true;
document.form1.myoption3.options[0].selected=true;
document.form1.myoption4.options[0].selected=true;
document.form1.myoption5.options[0].selected=true;
document.form1.myoption6.options[0].selected=true;
document.form1.myoption7.options[0].selected=true;
document.form1.myoption8.options[0].selected=true;
document.form1.myoption9.options[0].selected=true;
document.form1.myoption10.options[0].selected=true;
document.form1.myoption11.options[0].selected=true;
document.form1.myoption12.options[0].selected=true;
document.form1.myoption13.options[0].selected=true;
document.form1.myoption14.options[0].selected=true;
document.form1.myoption15.options[0].selected=true;
document.form1.myoption16.options[0].selected=true;
document.form1.myoption17.options[0].selected=true;
document.form1.myoption18.options[0].selected=true;
document.form1.myoption19.options[0].selected=true;
document.form1.myoption20.options[0].selected=true;
document.form1.myoption21.options[0].selected=true;
document.form1.myoption22.options[0].selected=true;
document.form1.myoption23.options[0].selected=true;
document.form1.myoption24.options[0].selected=true;
document.form1.myoption26.options[0].selected=true;
document.form1.myoption27.options[0].selected=true;
document.form1.myoption28.options[0].selected=true;
document.form1.myoption29.options[0].selected=true;
document.form1.myoption30.options[0].selected=true;
document.form1.myoption31.options[0].selected=true;
document.form1.myoption32.options[0].selected=true;
document.form1.myoption33.options[0].selected=true;
document.form1.myoption34.options[0].selected=true;
}

if(val == "n")
{
document.form1.myoption1.options[1].selected=true;
document.form1.myoption2.options[1].selected=true;
document.form1.myoption3.options[1].selected=true;
document.form1.myoption4.options[1].selected=true;
document.form1.myoption5.options[1].selected=true;
document.form1.myoption6.options[1].selected=true;
document.form1.myoption7.options[1].selected=true;
document.form1.myoption8.options[1].selected=true;
document.form1.myoption9.options[1].selected=true;
document.form1.myoption10.options[1].selected=true;
document.form1.myoption11.options[1].selected=true;
document.form1.myoption12.options[1].selected=true;
document.form1.myoption13.options[1].selected=true;
document.form1.myoption14.options[1].selected=true;
document.form1.myoption15.options[1].selected=true;
document.form1.myoption16.options[1].selected=true;
document.form1.myoption17.options[1].selected=true;
document.form1.myoption18.options[1].selected=true;
document.form1.myoption19.options[1].selected=true;
document.form1.myoption20.options[1].selected=true;
document.form1.myoption21.options[1].selected=true;
document.form1.myoption22.options[1].selected=true;
document.form1.myoption23.options[1].selected=true;
document.form1.myoption24.options[1].selected=true;
document.form1.myoption26.options[1].selected=true;
document.form1.myoption27.options[1].selected=true;
document.form1.myoption28.options[1].selected=true;
document.form1.myoption29.options[1].selected=true;
document.form1.myoption30.options[1].selected=true;
document.form1.myoption31.options[1].selected=true;
document.form1.myoption32.options[1].selected=true;
document.form1.myoption33.options[1].selected=true;
document.form1.myoption34.options[1].selected=true;

}

}
</script>

<script>

function myfunc2()
{
	var val = document.form2.myselects.value;
if(val == "actions")
{
	 return false;
}
	if(val == "y")
{
document.form2.myoption1.options[0].selected=true;
document.form2.myoption2.options[0].selected=true;
document.form2.myoption3.options[0].selected=true;
document.form2.myoption4.options[0].selected=true;
document.form2.myoption5.options[0].selected=true;
document.form2.myoption6.options[0].selected=true;
document.form2.myoption7.options[0].selected=true;
document.form2.myoption8.options[0].selected=true;
document.form2.myoption9.options[0].selected=true;
document.form2.myoption10.options[0].selected=true;
document.form2.myoption11.options[0].selected=true;
document.form2.myoption12.options[0].selected=true;
document.form2.myoption13.options[0].selected=true;
document.form2.myoption14.options[0].selected=true;
document.form2.myoption15.options[0].selected=true;
document.form2.myoption16.options[0].selected=true;
document.form2.myoption17.options[0].selected=true;
document.form2.myoption18.options[0].selected=true;
document.form2.myoption19.options[0].selected=true;
document.form2.myoption20.options[0].selected=true;
document.form2.myoption21.options[0].selected=true;
document.form2.myoption22.options[0].selected=true;
document.form2.myoption23.options[0].selected=true;
document.form2.myoption24.options[0].selected=true;
document.form2.myoption26.options[0].selected=true;
document.form2.myoption27.options[0].selected=true;
document.form2.myoption28.options[0].selected=true;
document.form2.myoption29.options[0].selected=true;
document.form2.myoption30.options[0].selected=true;
document.form2.myoption31.options[0].selected=true;
document.form2.myoption32.options[0].selected=true;
document.form2.myoption33.options[0].selected=true;
document.form2.myoption34.options[0].selected=true;
}
	if(val == "n1")
{
document.form2.myoption1.options[1].selected=true;
document.form2.myoption2.options[1].selected=true;
document.form2.myoption3.options[1].selected=true;
document.form2.myoption4.options[1].selected=true;
document.form2.myoption5.options[1].selected=true;
document.form2.myoption6.options[1].selected=true;
document.form2.myoption7.options[1].selected=true;
document.form2.myoption8.options[1].selected=true;
document.form2.myoption9.options[1].selected=true;
document.form2.myoption10.options[1].selected=true;
document.form2.myoption11.options[1].selected=true;
document.form2.myoption12.options[1].selected=true;
document.form2.myoption13.options[1].selected=true;
document.form2.myoption14.options[1].selected=true;
document.form2.myoption15.options[1].selected=true;
document.form2.myoption16.options[1].selected=true;
document.form2.myoption17.options[1].selected=true;
document.form2.myoption18.options[1].selected=true;
document.form2.myoption19.options[1].selected=true;
document.form2.myoption20.options[1].selected=true;
document.form2.myoption21.options[1].selected=true;
document.form2.myoption22.options[1].selected=true;
document.form2.myoption23.options[1].selected=true;
document.form2.myoption24.options[1].selected=true;
document.form2.myoption26.options[1].selected=true;
document.form2.myoption27.options[1].selected=true;
document.form2.myoption28.options[1].selected=true;
document.form2.myoption29.options[1].selected=true;
document.form2.myoption30.options[1].selected=true;
document.form2.myoption31.options[1].selected=true;
document.form2.myoption32.options[1].selected=true;
document.form2.myoption33.options[1].selected=true;
document.form2.myoption34.options[1].selected=true;

}

}
</script>

<script>
function test(size)
{
	var val = document.form3.myselects.value;
	if(val == "actions")
	{
		 return false;
	}

	if(val == "y")
	{
		for(var i=0;i<size;i++)
		{
			var j=i+1;
			//alert(j);
			document.form3.getElementById("myoption"+j).option[0].selected=true;
			document.form3.getElementById("myoption"+j).option[1].selected=false;
		}
	}
	
	if(val == "n")
	{
		for(var i=0;i<size;i++)
		{
			var j=i+1;
			//alert(j);
			document.form3.getElementById("myoption"+j).option[1].selected=true;
			document.form3.getElementById("myoption"+j).option[0].selected=false;
		}
	}
}
</script>