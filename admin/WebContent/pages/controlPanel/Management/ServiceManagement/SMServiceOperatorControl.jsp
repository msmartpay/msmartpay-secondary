<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page import="java.util.*" %>

<%
String vendor="";
ArrayList list=(ArrayList)request.getAttribute("getDetails");
if(list==null)
{
	list=new ArrayList();
}

//String mdshare="Variable";
//String clientshare="Variable";
int size=0;
if(list!=null)
{
	size=list.size();
}

	// System.out.println("listbncghbgch   tetsing  :");
String mdId="";
if(request.getAttribute("MDId")!=null){
	mdId=(String)request.getAttribute("MDId");
}else{
	mdId="0";
}
	
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
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />



<script>
function check1(){
	var allcheck = document.form1.checkAll;
	
	if(allcheck.checked == true){
	for(i=0; i<document.form1.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.form1.elements[i].type=="checkbox" && document.form1.elements[i].name!="checkAll")
			{
				document.form1.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.form1.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.form1.elements[i].type=="checkbox" && document.form1.elements[i].name != "checkAll" )
			{
				document.form1.elements[i].checked=false;
			}
			
		}
	}
}

function check2(){
	var allcheck = document.form2.checkAll;
	
	if(allcheck.checked == true){
	for(i=0; i<document.form2.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.form2.elements[i].type=="checkbox" && document.form2.elements[i].name!="checkAll")
			{
				document.form2.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.form2.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.form2.elements[i].type=="checkbox" && document.form2.elements[i].name != "checkAll" )
			{
				document.form2.elements[i].checked=false;
			}
			
		}
	}
}

function check3(){
	var allcheck = document.form3.checkAll;
	
	if(allcheck.checked == true){
	for(i=0; i<document.form3.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.form3.elements[i].type=="checkbox" && document.form3.elements[i].name!="checkAll")
			{
				document.form3.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.form3.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.form3.elements[i].type=="checkbox" && document.form3.elements[i].name != "checkAll" )
			{
				document.form3.elements[i].checked=false;
			}
			
		}
	}
}

function otherSubmit(i)
{
	var k=i;
	if(checkValidation(k))
	{
		document.form3.action="OpsServiceManagement.action?param=updateDetails";
		document.form3.submit();
	}
}
function utilitySubmit(i)
{

	var k=i;
	if(checkValidation(k))
	{
		document.form2.action="OpsServiceManagement.action?param=updateDetails";	
		document.form2.submit();
	}
}

function rechargeSubmit(i)
{
	var k=i;
	if(checkValidation(k))
	{
		document.form1.action="OpsServiceManagement.action?param=updateDetails";
		document.form1.submit();
	}
}


function checkValidation(k)
{

	var boxesTicked1 = "";

	for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
	{
		if (document.getElementsByName('checkpartial')[i].checked)
 		{
			boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n";
		}
	}
	if (boxesTicked1=='' || boxesTicked1 <0 ) 
	{
		alert("Please Select Atleast One operator");
		return false;
	}else{
		return true;
	}




}
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
			  
		var mdId = document.myforms.mdId.value;
		if(mdId == "")
		{
			  alert("Please Enter Md Id.");
			  document.myforms.mdId.focus();
			  return false;
		}
  	}
  
  	document.myforms.action="OpsServiceManagement.action?param=getDetails";
	//alert(document.myforms.action);
	document.myforms.submit();
}
</script>

<style>
select{color:#930;}
select option{color:#930;}

</style>


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
			border="0" class="newbg">
			<tr>
				<td valign="top" align="center" class="rounded-corners box_heights" >
				<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  >
					<tr>
						<td valign="top" align="center">
						<table cellpadding="0" cellspacing="0" width="100%" align="center">
							<tr>
								<td height="40" align="left" valign="middle" class="heading_mgs">Service Management</td>
							</tr>
							<tr>
								<td  align="center" valign="middle" class="dyn_mgs"><%=message%></td>
							</tr>
							<tr>
								<td colspan="4" align="center" valign="top">
								
								<table width="86%" cellpadding="0" cellspacing="0" id="border" >
								<tr>
								<td colspan="100%" align="center">
								
								<form name="myforms" method="post" >
								<table width="90%" class="mydata_tabl" cellpadding="0" cellspacing="0" >
								<tr><td height="20px" colspan="100%"></td></tr>
                                      <tr>
                                      <td width="30%"></td>
                                      <td  width="10%"><strong>Vertical</strong></td><td width="10%" align="center">:</td>
                                      <td width="60%"><select name="vartical" onchange="click_chng()">
                                      <option value="select" >Select</option>
                                      <option value="web" selected="selected">Web</option> 
                                      <!--<option value="sms">SMS</option>
                                      --></select></td>
                                      </tr>
                                      
                                      <tr id="space_1">
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr id="web_service">
                                      <td></td>
                                      <td><strong>Service</strong></td><td align="center">:</td>
                                      <td><select name="webservice">
                                      <option value="select" >Select</option>
                                      <option value="all">All</option>
                                      <option value="recharge" selected="selected">Recharge</option>
                                      <option value="utility">Utility</option>
                                      <option value="DMR">Money Transfer</option>
                                     <!--  <option value="dth">DTH-X</option>
                                      <option value="ops">Ops</option>
                                      <option value="sms">SMS Txn</option> -->
                                      </select></td>
                                      </tr>
                                      
                                      <tr id="mdisrow">
                                      <td></td>
                                      <td><strong>Md Id</strong></td><td align="center">:</td>
                                      <td><input name="mdId" type="text" />
                                      </td>
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
                                      	<option value="AC">Mobile - Aircel</option>
                                        <option value="ART">Mobile - Airtel</option>
                                        <option value="ID">Mobile - Idea</option>
                                        <option value="VF">Mobile - Vodafone</option>
                                        <option value="BS">Mobile - BSNL - Top Up</option>
                                        <option value="BSV">Mobile - BSNL - Validity</option>
                                        <option value="MMT">Mobile - MTNL - Top Up</option>
                                        <option value="MMV">Mobile - MTNL - Validity</option>
                                        <option value="MT">Mobile - MTS</option>
                                        <option value="RC">Mobile - Reliance - CDMA</option>
                                        <option value="RG">Mobile - Reliance - GSM</option>
                                        <option value="TI">Mobile - Tata Indicom</option>
                                        <option value="VG">Mobile - Docomo - Top Up</option>
                                        <option value="TDS">Mobile - Docomo - Special</option>
                                        <option value="UN">Mobile - Uninor - Top Up</option>
                                        <option value="UNS">Mobile - Uninor - Special</option>
                                        <option value="VCT">Mobile - Videocon - Top Up</option>
                                        <option value="VCS">Mobile - Videocon - Special</option>
                                        <option value="VGC">Mobile - Virgin - CDMA</option>
                                        <option value=VG>Mobile - Virgin - GSM</option>
                                        <option value="LP">Mobile - Loop - BPL</option>
                                       
                                        <option value="VFJK">Mobile - Vodafone JNK</option>
                                        <option value="BSJK">Mobile - Bsnl JNK</option>
                                        <option value="IDJK">Mobile - Idea JNK</option>
                                        <option value="ARTJK">Mobile - Airtel JNK</option>
                                         <option value="TW">Mobile - Tata WALKY</option>
                                        
                                        <option value="ARD">DTH - Airtel DTH</option>
                                        <option value="BT">DTH - BIG TV</option>
                                        <option value="DT">DTH - Dish TV</option>
                                        <option value="SD">DTH - Sun Direct</option>
                                        <option value="TS">DTH - Tata Sky</option>
                                        <option value="VC">DTH - Videocon D2H</option>
                                        
                                        <option value="PWZ">Data Card - Tata Photon Whiz</option>
                                        <option value="PHP">Data Card - Tata Photon+</option>
                                        <option value="DPP">Data Card - Docomo Photon+</option>
                                        <option value="RNC">Data Card - Reliance Netconnect</option>
                                        <option value="RNCP">Data Card - Reliance Netconnect+</option>
                                        <option value="MBZ">Data Card - MTS Blaze</option>
                                        <option value="MBR">Data Card - MTS Browser</option>
                                        <option value="ACDC">Data Card - Aircel</option>
                                        <option value="BSDC">Data Card - BSNL</option>
                                        <option value="IDDC">Data Card - Idea</option>
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
                                        <option value="ACCB">Aircel chennai</option>
                                        <option value="AILB">Airtel Landline</option>
                                        <option value="AIMB">Airtel Postpaid</option> 
                                        <option value="BSRB">BSES Rajdhani</option>
                                        <option value="BSYB">BSES Yamuna</option>
                                        <option value="BSNB">BSNL PostPaid</option>
                                        <option value="BSMB">Cellone PostPaid</option>
                                        <option value="DLFB">DLF Pramerica</option>
                                        <option value="TDMB">Docomo Postpaid</option>
                                        <option value="ICPB">ICICI Pru Life</option>
                                        <option value="IDMB">IDEA Postpaid</option>
                                        <option value="INGB">ING Vysya Life</option>
                                        <option value="LTGB">L&T General</option>
                                        <option value="LICB">Life Insurance Corporation</option>
                                        <option value="LPMB">Loop Mobile PostPaid</option>
                                        <option value="MGLB">Mahanagar Gas Limited</option>
                                        <option value="MTDB">MTNL Delhi</option>
                                        <option value="NPCB">Noida Power Co. Ltd.</option>
                                        <option  value="North Delhi Power Limited">North Delhi Power Limited</option>
                                        <option value="RLEB">Reliance Energy (Mumbai)</option>
                                        <option value="RCLB">Reliance landline</option>
                                        <option value="RCMB">Reliance Mobile</option>
                                        <option value="TAAB">TATA AIA Life</option>
                                        <option value="TALB">Tata AIG Life</option>
                                        <option value="TILB">Tata Indicom Land Line</option>
                                        <option value="TIMB">Tata PostPaid</option>
                                        <option value="VDMB">Vodafone Postpaid </option>
                                      </select></td>
                                      </tr>
                                      
                                      <tr id="space_5" style="display:none;">
                                       <td></td> <td></td> <td></td> <td>&nbsp; </td>
                                      </tr>
                                      
                                      <tr>
                                      <td></td><td></td> <td></td>
                                      <td align="left"><input class="cls_btn" type="button" value="Submit" onclick="sbmt_click()" /> </td>
                                      </tr>
                                      <tr>
                                      <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
                                      <tr><td height="20px" colspan="100%"></td></tr>
                                  </table>
                                </form>   
                                      </td>
								</tr>
								</table>
								
								</td>
							</tr>

							
							<tr>
								<td colspan="4" align="center">
                     
                      <!-- service Management - Recharge table start-->
					    <% if(flag.equalsIgnoreCase("Y"))
   				           {
						   int i=0;
					    	int j=0;
    		          	%>
              				
                     <!-- service Management - Utility table stop-->
                     <!-- service control - table start-->
					    <%if(vertical.equalsIgnoreCase("web")){
					    
					    %> 
					 
                     <form name="form3" method="post">
                    
                     <table id="control_tbl" cellspacing="0" cellpadding="0" width="60%" align="center" class="tbls" style="margin-top:10px;">
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd tabulardata" align="center">
                                        <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; font-size:13px;color:#fff;">
                                        Service Control  (<%=mdId %>) <input type="hidden" name="mdId" value="<%=mdId %>" />
                                        </th>
                                        </tr>
					
                                          <tr  class="hd tabulardata" align="center">
                                          
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;"><strong>S.N.</strong></td>
											<td width="5%" align="center" style="border-bottom:0px solid #930;">
											<input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check3()" value="" /></td>
											<td width="10%" align="center" style="border-bottom:0px solid #930;"><strong>Service Type</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;"><strong>Service</strong></td>
                                            <td width="3%" align="center" style="border-bottom:0px solid #930;border-right:0px solid #930;">
         	             <select class="actionclass" id="myselects" name="myselects" onclick="test(<%= size%>)" style="width:80px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                           </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;"><strong>Vendor</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;"><strong>Status</strong></td>
                                          </tr>
										      
                                         <%
											for(i=0;i<size;i++)
											{
												j=i+1;
											HashMap map=(HashMap)list.get(i);
										%>
                                                         
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                          <td align="center" style="border-bottom:0px solid #930;"><%=j%></td>
										  <td align="center" style="border-bottom:0px solid #930;">
										  <input type="checkbox" name="checkpartial" id="chek<%=i %>"  value="<%=i%>"/></td>
										  <td width="10%" align="center" style="border-bottom:0px solid #930;"><input type="text" name="sub_service<%=i%>" value="<%=map.get("Sub_Service")%>" style="border:none;" /></td>
                                          <td style="border-bottom:0px solid #930;" align="center"><input type="text" name="sku_name<%=i%>" value="<%=map.get("SKU_Name")%>" style="border:none;" /></td>
                     					 <td style="border-bottom:0px solid #930;"> 
                     					 	<select class="select1" id="myoption<%=j%>" name="myoption1<%=i%>" 
                     					 	style="width:45px;padding:1px;margin:5px;height:20px;">
											
                                           <option selected="selected" value="<%=map.get("Vendor_Status")%>"><%=map.get("Vendor_Status")%></option>
											
                                           <%
                                           	String sub_service=(String)map.get("Sub_Service");
                                           String status=(String)map.get("Vendor_Status");
                                            //char status=(Character)map.get("SMS_Vendor_Status");
                                            if(status.equalsIgnoreCase("Y"))
                                            {
                                            %>
                                            
                                            <option value="N">N</option>
                                            <%}
                                            else{
                                            %>
                                             <option value="Y">Y</option>
                                             <%} %>
											</select>
											</td>
											<td style="border-bottom:0px solid #930;">
                                            <select style="width:100px;padding:1px;margin:5px;height:20px;" name="vendor<%=i%>">
                                            <%
											String vendorString=(String)map.get("Vendor");
											if(vendorString.equalsIgnoreCase("CYBERPLAT"))
												vendorString="MARS1";
											else
												vendorString=(String)map.get("Vendor");
											%>
                                            <option selected="selected" value="<%=map.get("Vendor")%>"><%=vendorString%></option>
                                            <% 
                                            if((sub_service.equalsIgnoreCase("mobile"))||(sub_service.equalsIgnoreCase("datacard"))||(sub_service.equalsIgnoreCase("dth")))
                                            {
                                            
			                                vendor=(String)map.get("Vendor");
			                                if(vendorString.equalsIgnoreCase("MARS")) {%>
			                                 <option value="airtelthanks">airtelthanks</option>
			                                <option value="jiolite">jiolite</option>
			                                <option value="VastIndia">VastIndia</option>
			                               <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
											<option value="MSMART">MSMART</option>
											<option value="CyberHub">CyberHub</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("MSMART")) {%>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                            <option value="VastIndia">VastIndia</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("CyberHub")) {%>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                            <option value="VastIndia">VastIndia</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MARS">MARS</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                             <%}else if(vendorString.equalsIgnoreCase("Venfone")) {%>
                                             <option value="Venfone2">Venfone2</option>
                                             <option value="mrobotics2">mrobotics2</option>
                                              <option value="airtelthanks">airtelthanks</option>
                                             <option value="jiolite">jiolite</option>
                                             <option value="VastIndia">VastIndia</option>
                                             <option value="mrobotics">mrobotics</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("Venfone2")) {%>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="Venfone">Venfone</option>
                                              <option value="airtelthanks">airtelthanks</option>
                                             <option value="jiolite">jiolite</option>
                                             <option value="VastIndia">VastIndia</option>
                                             <option value="mrobotics">mrobotics</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("mrobotics")) {%>
                                            <option value="mrobotics2">mrobotics2</option>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                            <option value="VastIndia">VastIndia</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("mrobotics2")) {%>
                                            <option value="mrobotics">mrobotics</option>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                            <option value="VastIndia">VastIndia</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("jiolite")) {%>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("airtelthanks")) {%>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MARS">MARS</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="MSMART">MSMART</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            
                                            <%}else if(vendorString.equalsIgnoreCase("VastIndia")) {%>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                           <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MSMART">MSMART</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="WINUS">VENUS</option>
											<option value="Self">Self</option>
                                            <option value="NA">NA</option>
                                            <%}else if(vendorString.equalsIgnoreCase("NA")) {%>
                                             <option value="airtelthanks">airtelthanks</option>
                                            <option value="jiolite">jiolite</option>
                                            <option value="VastIndia">VastIndia</option>
                                            <option value="MARS">MARS</option>
                                            <option value="Venfone">Venfone</option>
                                            <option value="Venfone2">Venfone2</option>
                                            <option value="mrobotics">mrobotics</option>
                                            <option value="mrobotics2">mrobotics2</option>
                                            <option value="MSMART">MSMART</option>
                                            <option value="CyberHub">CyberHub</option>
                                            <option value="Self">Self</option>
                                            <%}else{%>
	                                            <option value="airtelthanks">airtelthanks</option>
	                                            <option value="jiolite">jiolite</option>
	                                            <option value="VastIndia">VastIndia</option>
	                                            <option value="MARS">MARS</option>
	                                            <option value="Venfone">Venfone</option>
	                                            <option value="Venfone2">Venfone2</option>
	                                            <option value="mrobotics">mrobotics</option>
	                                            <option value="mrobotics2">mrobotics2</option>
	                                            <option value="MSMART">MSMART</option>
	                                            <option value="CyberHub">CyberHub</option>
	                                            <option value="Self">Self</option>
	                                            <option value="NA">NA</option>
                                            <%} 
			                                }else if(sub_service.equalsIgnoreCase("Billpay")){
                                            
                                            	vendor=(String)map.get("Vendor");
				                                if(vendorString.equalsIgnoreCase("SmartKinda"))
	                                            {%>
													<option value="Self">Self</option>
													<option value="MSMART">MSMART</option>
													<option value="mrobotics">mrobotics</option>
		                                            <option value="NA">NA</option>
	                                            <%}else if(vendorString.equalsIgnoreCase("mrobotics")) {%>
		                                             <option value="SmartKinda">SmartKinda</option>
		                                            <option value="MSMART">MSMART</option>
		                                            <option value="NA">NA</option>
													<option value="Self">Self</option>
		                                        <%}else if(vendorString.equalsIgnoreCase("Self")) {%>
		                                            <option value="SmartKinda">SmartKinda</option>
		                                            <option value="MSMART">MSMART</option>
		                                            <option value="mrobotics">mrobotics</option>
		                                            <option value="NA">NA</option>
	                                            <%}else if(vendorString.equalsIgnoreCase("NA")) {%>
		                                            <option value="SmartKinda">SmartKinda</option>
		                                            <option value="mrobotics">mrobotics</option>
		                                            <option value="MSMART">MSMART</option>
													<option value="Self">Self</option>
	                                            <%}else if(vendorString.equalsIgnoreCase("MSMART")) {%>
		                                            <option value="SmartKinda">SmartKinda</option>
		                                            <option value="mrobotics">mrobotics</option>
													<option value="Self">Self</option>
													<option value="NA">NA</option>
	                                            <%}else{%>
	                                            	<option value="SmartKinda">SmartKinda</option>
		                                            <option value="MSMART">MSMART</option>
		                                            <option value="mrobotics">mrobotics</option>
		                                            <option value="Self">Self</option>
		                                            <option value="NA">NA</option>
	                                            
	                                            <%}}else if("DMR".equalsIgnoreCase(sub_service)){%>
	                                            	
	                                            	<option value="icici">ICICI</option>
		                                            <option value="eko">EKO</option>
	                                            	
	                                            <%} %>
                                            </select>
                                         	</td>
                                          	<td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=map.get("Vendor_Status")%>
                                          	</td>                                            
                                          </tr>
												<%} %>
                                          </tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input style="width:90px;color: #fff;" type="button" value="Update" onclick="otherSubmit(<%=i%>)"  /><input type="hidden" name="count" value="<%=j%>"  /><input type="hidden" name="updateService" value="web"  /></td>
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


function rechargeSubmit1()
{
	var vendor_status=document.form1.myoption1.value;
	if((vendor_status=="select")||(vendor_status==""))
	{
		alert("Select vandor status.");
		document.form1.myoption1.focus();
		return false;
	}
	var vendor=document.form1.vendor.value;
	if((vendor=="select")||(vendor==""))
	{
		alert("Select vandor status.");
		document.form1.vendor.focus();
		return false;
	}
	document.form1.action="OpsServiceManagement.action?param=updateDetails";
	document.form1.submit();
}

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