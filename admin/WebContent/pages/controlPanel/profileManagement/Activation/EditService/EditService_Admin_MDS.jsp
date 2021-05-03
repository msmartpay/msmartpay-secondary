<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<!--<link href="css/superadmin.css" rel="stylesheet" type="text/css" />-->
<link rel="icon" href="images/t.png" />
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<%@ page import="java.util.*" %>
<% 
String editServiceUserID=(String)request.getAttribute("editUserId"); 
String userType=(String)request.getAttribute("userType");
HashMap serviceData=(HashMap)request.getAttribute("serviceData");
int size=0;
if(serviceData!=null){size=serviceData.size();}else{size=-1;}
System.out.println(serviceData);
String showservice="";
String mark="";
String mark1="";
String message=(String)request.getAttribute("message");
if(message==null)message="";
ArrayList operatorData=(ArrayList)request.getAttribute("operatorData");
System.out.println(operatorData);
int opsize=0;
if(operatorData!=null){opsize=operatorData.size();}else{opsize=-1;}
String displayService=(String)request.getAttribute("displayService");
String DBservice=(String)request.getAttribute("DBservice");
int sum=0;
%>

<script>

function submt_click()
{
	var actn = document.myform.action_forms.value;
	
	if(actn == "select"){
		alert("Please Select the Required Field.");
		return false;
	}
	else if(actn == "Service Control"){
		
		document.myform.action="editServiceMgt.action?param=getEditServiceMDS";
		document.myform.submit();
	}
	else if(actn == "Operator Control"){
		var services = document.myform.edit_services.value;

		if(services == "select"){
			alert("Please Select the Required Field.");
			return false;
		}
		else if(services == "mobile"){
			var opet = document.myform.operatormobile.value;
			if(opet == "select"){
				alert("Please Select the Required Field.");
				return false;
			}
			else{
				document.myform.action="editServiceMgt.action?param=getEditOperatorMDS&subservice=mobile&operator="+opet;
				document.myform.submit();
			}
		}
		else if(services == "dth"){
			var opet = document.myform.operatordth.value;
			if(opet == "select"){
				alert("Please Select the Required Field.");
				return false;
			}
			else{
				document.myform.action="editServiceMgt.action?param=getEditOperatorMDS&subservice=dth&operator="+opet;
				document.myform.submit();
			}
			
		}
		else if(services == "datacard"){
			var opet = document.myform.operatordatacard.value;
			if(opet == "select"){
				alert("Please Select the Required Field.");
				return false;
			}
			else{
				document.myform.action="editServiceMgt.action?param=getEditOperatorMDS&subservice=datacard&operator="+opet;
				document.myform.submit();
			}
		}
		else if(services == "Billpay"){
			var opet = document.myform.operatorbillpay.value;
			if(opet == "select"){
				alert("Please Select the Required Field.");
				return false;
			}
			else{
				document.myform.action="editServiceMgt.action?param=getEditOperatorMDS&subservice=Billpay&operator="+opet;
				document.myform.submit();
			}
		}
	}
}







function first_box()
{
	var actn = document.myform.action_forms.value;
	
	if(actn == "select")
	{
		document.getElementById("operator_service").style.display="none";
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("control_tbl").style.display="none";
		document.getElementById("rech_tbl").style.display="none";
		document.getElementById("utility_tbl").style.display="none";
		return false;
	}
	if(actn == "Service Control")
	{
		document.getElementById("operator_service").style.display="none";
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("control_tbl").style.display="none";
		document.getElementById("rech_tbl").style.display="none";
		document.getElementById("utility_tbl").style.display="none";
		return false;
	}
	if(actn == "Operator Control")
	{
		document.getElementById("operator_service").style.display="";
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("control_tbl").style.display="none";
		document.getElementById("rech_tbl").style.display="none";
		document.getElementById("utility_tbl").style.display="none";
		return false;
	}

}


function service_box()
	{
		var services = document.myform.edit_services.value;
		if(services == "select")
		{
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("dth_tr").style.display="none";
		document.getElementById("datacard_tr").style.display="none";
		return false;
		}
		if(services == "mobile")
		{
		document.getElementById("rech_tr").style.display="";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("dth_tr").style.display="none";
		document.getElementById("datacard_tr").style.display="none";
		return false;
		}
		if(services == "dth")
		{
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("dth_tr").style.display="";
		document.getElementById("datacard_tr").style.display="none";
		return false;	
	
		}
		if(services == "datacard")
		{
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="none";
		document.getElementById("dth_tr").style.display="none";
		document.getElementById("datacard_tr").style.display="";
		return false;
			
		}
		
		if(services == "Billpay")
		{
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="";
		document.getElementById("dth_tr").style.display="none";
		document.getElementById("datacard_tr").style.display="none";
		return false;
		}
	}


</script>

</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="form11">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                    
                      <td width="50%" height="40" align="left" valign="middle" class="heading_mgs">
                      Edit Service - Admin/MDS
                      </td>
                  
                     </tr> 
                    <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
					
                    
                    <tr>
                    <td colspan="4"><form name="myform" method="post"  >
					   <table cellpadding="0" cellspacing="0" width="86%" align="center" id="border" style="padding:20px">
                    <tr>
                    <td>
					   <table cellpadding="0" cellspacing="0" width="100%" align="center"  class="mydata_tabl">
                       
					   <tr>
					    <input type="hidden" name="editUserId" value="<%=editServiceUserID%>"/>
						<input type="hidden" name="userType" value="mds"/>
                       <td width="30%"></td>
                       <td width="10%"><strong>Action</strong></td>
                       <td width="10%" align="center">:</td>
					   <td width="60%">
                       <select name="action_forms" onchange="first_box()">
                        <option value="select">Select</option>
                         <option value="Service Control">Service Control</option>
                          <option value="Operator Control">Operator Control</option>
                          </select>
                          </td></tr>
                          
                           <tr id="tr_1" >
                                       <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>
						                                        
                         <tr id="operator_service" style="display:none;">
                         <td></td>
                         <td><strong>Service</strong></td><td align="center">:</td>
					     <td><select name="edit_services"  onchange="service_box()">
					               <option value="select" selected="selected">Select</option>
                                      <option value="mobile">Recharge - Mobile</option>
									  <option value="dth">Recharge - DTH</option>
									  <option value="datacard">Recharge - Data Card</option>
                                      <option value="Billpay">Utility</option>
                                     
                                        </select></td></tr>
                                        
                                       
                                      <tr id="tr_5" >
                                       <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr>  
									  
									  <!--mobile recharge,dth recharge,datacard recharge tr start --> 
                                        
                       		  <tr id="rech_tr" style="display:none;">
                         <td></td>
                         <td><strong>Operator</strong></td><td align="center">:</td>
					   <td>
					   <select name="operatormobile"  class="cal22">
					  <option value="select">Select</option>
					  <option value="All">All</option>
					   <option value="AIRCEL">Mobile - Aircel</option>
					    <option value="Airtel">Mobile - Airtel</option>
                         <option value="IDEA">Mobile - Idea</option>
					    <option value="VODAFONE">Mobile - Vodafone</option>
                        <option value="BSNLRECH">Mobile - BSNL - Top Up</option>
					    <option value="BSNL">Mobile - BSNL - Validity</option>
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
					    <option value="VIDEOCONSP">Mobile - Videocon - Specia</option>
                         <option value="VIRGINCDMA">Mobile - Virgin - CDMA</option>
					    <option value="VIRGINGSM">Mobile - Virgin - GSM</option>
                        <option value="BPL">Mobile - Loop - BPL</option>
					   </select>
	</td>    
	
	</tr>
	
							  <tr id="dth_tr" style="display:none;">
                         <td></td>
                         <td><strong>Operator</strong></td><td align="center">:</td>
					   <td>
					   <select name="operatordth"  class="cal22">
					   
					  <option value="select">Select</option>
					  <option value="All">All</option>
					    <option value="AIRTELDTH">DTH - Airtel DTH</option>
                         <option value="BIGTV">DTH - BIG TV</option>
					    <option value="DISH">DTH - Dish TV</option>
                        <option value="SUNDIRECT">DTH - Sun Direct</option>
					    <option value="TATASKY">DTH - Tata Sky</option>
                         <option value="VIDEOCONDTH">DTH - Videocon D2H</option>
					   </select>
	</td>    
	
	</tr>
	
							  <tr id="datacard_tr" style="display:none;">
                         <td></td>
                         <td><strong>Operator</strong></td><td align="center">:</td>
					   <td>
					   <select name="operatordatacard"  class="cal22">
					   
					  	<option value="select">Select</option>
					  	<option value="All">All</option>
					    <option value="TATA">Data Card - Tata</option>
                        <option value="RELIANCE">Data Card - Reliance</option>
					    <option value="MTS">Data Card - MTS</option>
                        <option value="AIRCEL">Data Card - Aircel</option>
					    <option value="BSNL">Data Card - BSNL</option>
                        <option value="IDEA">Data Card - Idea</option>
					   </select>
	</td>    
	
	</tr>
	
							<!--mobile recharge,dth recharge,datacard recharge tr stop -->
    
    						  <tr id="utly_tr" style="display:none;">
                         <td></td>
                         <td><strong>Operator</strong></td><td align="center">:</td>
					   <td>
					   <select name="operatorbillpay"  class="cal22">
					   
					  <option value="select">Select</option>
					  <option value="All">All</option>
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
                        <option value="North Delhi Power Limited">North Delhi Power Limited</option>
                        <option value="Reliance Energy (Mumbai)">Reliance Energy (Mumbai)</option>
                        <option value="Reliance landline">Reliance landline</option>
                        <option value="Reliance Mobile">Reliance Mobile</option>
                        <option value="TATA AIA Life">TATA AIA Life</option>
                        <option value="Tata AIG Life">Tata AIG Life</option>
                        <option value="Tata Landline">Tata Indicom Land Line</option>
                        <option value="Tata Landline">Tata Landline</option>
                        <option value="Tata PostPaid">Tata PostPaid</option>
                        <option value="Vodafone Postpaid">Vodafone Postpaid </option>
					   </select>
	</td>    
	
	</tr>
    
           				       <tr id="tr_2">
                                       <td colspan="100%" height="10px"></td>
                                      </tr>               
                                        
								<tr>
                                <td></td><td></td> <td></td><td align="left">
                                <input style="width:90px; margin-left:133px;" type="button" value="Submit" onclick="submt_click()" />
                                </td></tr>
                                <tr>
                                       <td></td><td></td><td></td> <td>&nbsp; </td>
                                      </tr> 
                                
					   </table>
					  
                      
                              </td>
                      </tr>
                        </table>
						</form>
					  </td>
                      </tr>
                      
                      <tr>
                      
                    <td colspan="4" >
                      <!--sertive control table start-->
					  <%if(size>0){%>
                      <form name="editServiceMD" method="post">
                      <table id="control_tbl" cellspacing="0" cellpadding="0" width="45%" align="center" class="tbls" style="padding-top:20px">
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd" align="center" style="background:#a74312;">
                                        <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; color:#fff; font-size:13px;">
                                        Service Control                                        </th>
                                        </tr>
					
                                          <tr  class="hd" align="center" style="background:#ce571b;">
                                          <input type="hidden" name="editUserId" value="<%=editServiceUserID%>"/>
										  <input type="hidden" name="param" value="EditServiceOfMDS"  />
                                            <td width="5%" align="center" style="border-bottom:0px solid #930; color:#fff;"><strong>S.N.</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930; color:#fff;"><strong>Service</strong></td>
                                            <td width="3%" align="center" style="border-bottom:0px solid #930;border-right:0px solid #930;">
                                            <select class="actionclass" name="myselects" onchange="myfunc3()" style="width:60px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>                                            </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930; color:#fff;"><strong>Status</strong></td>
                                          </tr>
                                          
                                          
                                         
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;">1</td>
                                            <td style="border-bottom:0px solid #930;" align="center">Flights</td>
                                            <td style="border-bottom:0px solid #930;"> 
											<% mark=(String)serviceData.get("Flights");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Flights" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select>											</td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>                                            
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;">2</td>
                                            <td style="border-bottom:0px solid #930;" align="center">Bus</td>
                                             <td style="border-bottom:0px solid #930;"> 
											  <% mark=(String)serviceData.get("Bus");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Bus" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select>											 </td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;">3</td>
                                            <td style="border-bottom:0px solid #930;" align="center">Hotel</td>
                                             <td style="border-bottom:0px solid #930;">
											 <% mark=(String)serviceData.get("Hotel");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Hotel" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select>											 </td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;">4</td>
                                            <td style="border-bottom:0px solid #930;" align="center">Recharge</td>
                                             <td style="border-bottom:0px solid #930;">
											 <% mark=(String)serviceData.get("Recharge");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Recharge" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select>											 </td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;">5</td>
                                            <td style="border-bottom:0px solid #930;" align="center">Utility</td>
                                             <td style="border-bottom:0px solid #930;">
											 <% mark=(String)serviceData.get("Utility");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Utility" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select>											 </td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                         <td align="center" style="border-bottom:0px solid #930;">6</td>
                                            <td style="border-bottom:0px solid #930;" align="center">PayCard</td>
                                             <td style="border-bottom:0px solid #930;">
											 <% mark=(String)serviceData.get("PayCard");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="PayCard" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select></td>
                                            <td style="border-bottom:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                          <td align="center" style="border-bottom:0px solid #930;">7</td>
                                            <td style="border-bottom:1px solid #930;" align="center">TestMerit </td>
                                             <td style="border-bottom:1px solid #930;"> <% mark=(String)serviceData.get("TestMerit");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="TestMerit" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select></td>
                                            <td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                          <td align="center" style="border-bottom:0px solid #930;">8</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">DTH-X</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"><% mark=(String)serviceData.get("DTHX");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="DTHX" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select></td>
                                            <td style="border-bottom:1px solid #930;border-right:1px solid #930;border-top:0px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                           <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                           <td align="center" style="border-bottom:0px solid #930;">9</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">Rail</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> <% mark=(String)serviceData.get("rail");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="rail" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select></td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                         <tr bgcolor="#ffffff" align="center" class="value_reg bords">
											 	<td align="center" style="border-bottom:1px solid #930;">10</td>
												<td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">SMS Txn</td>
												 <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> <% mark=(String)serviceData.get("SMS_TXN");
												if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
												else{mark1="Y";showservice="Dective";}%>
												<select class="select1" name="SMS_TXN" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
												</select></td>
                                            	<td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
										  
										  
										 <tr bgcolor="#ffffff" align="center" class="value_reg bords">
											 	<td align="center" style="border-bottom:1px solid #930;">11</td>
												<td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">Holidays</td>
												 <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> <% mark=(String)serviceData.get("Holidays");
												if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
												else{mark1="Y";showservice="Dective";}%>
												<select class="select1" name="Holidays" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
												</select></td>
                                            	<td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr> 
										  
										  <tr bgcolor="#ffffff" align="center" class="value_reg bords">
											 	<td align="center" style="border-bottom:1px solid #930;">12</td>
												<td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">Wallet To Wallet</td>
												 <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> <% mark=(String)serviceData.get("WW");
												if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
												else{mark1="Y";showservice="Dective";}%>
												<select class="select1" name="WW" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
												</select></td>
                                            	<td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
										  
							
                                          
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
											 	<td align="center" style="border-bottom:1px solid #930;">13</td>
												<td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">DMR</td>
												 <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> <% mark=(String)serviceData.get("DMR");
												if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
												else{mark1="Y";showservice="Dective";}%>
												<select class="select1" name="DMR" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
												</select></td>
                                            	<td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                           
										  
                                          </tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
										<input name="button" type="button" style="width:90px;" onclick="submitForm()" value="Update" /></td>
                                        </tr>
                                      </table>
                        </form>  
						<%}%>            
                      <!--sertive control table stop-->
                  
                       <!-- service Management - Recharge table start-->
					   <%if(opsize>0){%>
                       <form name="form1" method="post">
                     <table id="rech_tbl" cellspacing="0" cellpadding="0" width="60%" align="center" class="tbls" style="padding-top:20px">
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd" align="center" style="background:#a74312;">
                                         <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS';color:#fff; font-size:13px;">Operator Control - <%=displayService%></th>
                                        </tr>
					
                                          <tr  class="hd" align="center" style="background:#ce571b;">
                                          
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;color:#fff;"><strong>S.N.</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;color:#fff;"><strong>Service Type</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;color:#fff;"><strong>Service</strong></td>
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;">
                                            <select class="actionclass" name="myselects" onchange="myfunc()" style="width:60px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                            </td>
											<input type="hidden" name="editUserId" value="<%=editServiceUserID%>" />
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;color:#fff;"><strong>Status</strong></td>
                                          </tr>
                                         <%
										 for(int i=0;i<opsize;i++){
										 HashMap map=(HashMap)operatorData.get(i);
										 %>
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                            <td align="center" style="border-bottom:0px solid #930;"><%=i+1%></td>
                                            <td style="border-bottom:1px solid #930;"><%=displayService%></td>
                                            <td style="border-bottom:1px solid #930;" align="center">
											<input type="hidden" name="opName<%=i%>" value="<%=map.get("OperatorName")%>"/>
											<input type="hidden" name="subService<%=i%>" value="<%=map.get("subService")%>"/>
											<%=map.get("OperatorName")%></td>
                                            <td style="border-bottom:1px solid #930;">
                                            <% mark=(String)map.get("status");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
						<select class="select1" name="mark<%=i%>" style="width:45px;padding:1px;margin:5px;height:20px;">
						<option value="<%=mark%>"><%=mark%></option>
						<option value="<%=mark1%>"><%=mark1%></option>
						</select>
                                            </td>
											<%
									sum++;	
									%>
									<input type="hidden" name="service" value="<%=DBservice%>" />
                                            <td style="border-bottom:1px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>                                            
                                          </tr>
                                          <%}%>
                                           
											</tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        <input type="hidden" name="SNo" value="<%=sum%>" />
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input style="width:90px;" type="button" value="Update" onclick="updateOperatorStatus()" /></td>
                                        </tr>
                                      </table>
                      </form>
					  <%}%>
                    
					  </td>
                      </tr>
					  
                    
                    
                    <tr>
                      <td colspan="4" height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      
      
    </td>
  </tr>
   <tr>
    <td colspan="100%"  height="185px" valign="top" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
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

if(val == "n")
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

<script language="javascript">
function myfunc3()
{
var val = document.form3.myselects.value;
if(val == "actions")
{
	 return false;
}
if(val == "y")
{
document.editServiceMD.Flights.options[0].selected=true;
document.editServiceMD.Bus.options[0].selected=true;
document.editServiceMD.Hotel.options[0].selected=true;
document.editServiceMD.Recharge.options[0].selected=true;
document.editServiceMD.Utility.options[0].selected=true;
document.editServiceMD.PayCard.options[0].selected=true;
document.editServiceMD.TestMerit.options[0].selected=true;
document.editServiceMD.DTHX.options[0].selected=true;
document.editServiceMD.rail.options[0].selected=true;
document.editServiceMD.SMS_TXN.options[0].selected=true;
}
if(val == "n")
{

document.editServiceMD.Flights.options[1].selected=true;
document.editServiceMD.Bus.options[1].selected=true;
document.editServiceMD.Hotel.options[1].selected=true;
document.editServiceMD.Recharge.options[1].selected=true;
document.editServiceMD.Utility.options[1].selected=true;
document.editServiceMD.PayCard.options[1].selected=true;
document.editServiceMD.TestMerit.options[1].selected=true;
document.editServiceMD.DTHX.options[1].selected=true;
document.editServiceMD.rail.options[1].selected=true;
document.editServiceMD.SMS_TXN.options[1].selected=true;
}
}
function submitForm(){
//var document.editServiceControl..value;
document.editServiceMD.action="editServiceMgt.action";
document.editServiceMD.submit();
}
function updateOperatorStatus(){
document.form1.action="editServiceMgt.action?param=editOperatorOfMds";
document.form1.submit();
}
</script>



