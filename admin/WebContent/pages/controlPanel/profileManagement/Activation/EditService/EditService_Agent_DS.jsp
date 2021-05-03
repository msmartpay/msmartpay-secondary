<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<%@ page import="java.util.*" %>
<% 


String userType=(String)request.getAttribute("userType");
System.out.println(userType);
HashMap serviceData=(HashMap)request.getAttribute("serviceData");
System.out.println(serviceData);
String showservice="";
String mark="";
String mark1="";
String message=(String)request.getAttribute("message");
if(message==null)message="";
%>


<script>

function submt_click()
{
	var actn = document.myform.action_forms.value;
	
	if(actn == "select")
	{
		alert("Please Select the Required Field.");
		return false;
	}
	
	
	if(actn == "Service Control")
	{
		document.getElementById("control_tbl").style.display="";
		document.getElementById("rech_tbl").style.display="none";
		document.getElementById("utility_tbl").style.display="none";
		
	}
	
	
	
	if(actn == "Operator Control")
	{
		
		var opets = document.myform.operators.value;
		
		var services = document.myform.edit_services.value;
		if(services == "select")
		{
			alert("Please Select the Required Field.");
			return false;
		}
		if(services == "recharge")
		{
			var opet = document.myform.operator.value;
			if(opet == "select")
			{
			alert("Please Select the Required Field.");
			return false;
			}else{		document.getElementById("control_tbl").style.display="none";
		document.getElementById("rech_tbl").style.display="";
		document.getElementById("utility_tbl").style.display="none";}
			
		}else if(services == "utility")
		{
			var opets = document.myform.operators.value;
			if(opets == "select")
			{
			alert("Please Select the Required Field.");
			return false;
			}else{document.getElementById("control_tbl").style.display="none";
		document.getElementById("rech_tbl").style.display="none";
		document.getElementById("utility_tbl").style.display="";
		//document.getElementById("tr_1").style.display="";
		//document.getElementById("tr_1").style.display="";
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
		return false;
		}
		if(services == "recharge")
		{
		document.getElementById("rech_tr").style.display="";
		document.getElementById("utly_tr").style.display="none";
		return false;	
		}
		if(services == "utility")
		{
		document.getElementById("rech_tr").style.display="none";
		document.getElementById("utly_tr").style.display="";
		return false;
		}
	}


</script>


<script>
function myfunc3()
{
var val = document.editServiceControl.myselects.value;
if(val == "actions")
{
	 return false;
}

if(val == "y")
{
document.editServiceControl.Flights.options[0].selected=true;
document.editServiceControl.Bus.options[0].selected=true;
document.editServiceControl.Hotel.options[0].selected=true;
document.editServiceControl.Recharge.options[0].selected=true;
document.editServiceControl.Utility.options[0].selected=true;
document.editServiceControl.PayCard.options[0].selected=true;
document.editServiceControl.TestMerit.options[0].selected=true;
document.editServiceControl.DTHX.options[0].selected=true;
document.editServiceControl.rail.options[0].selected=true;
document.editServiceControl.SMS_TXN.options[0].selected=true;
}
if(val == "n")
{

document.editServiceControl.Flights.options[1].selected=true;
document.editServiceControl.Bus.options[1].selected=true;
document.editServiceControl.Hotel.options[1].selected=true;
document.editServiceControl.Recharge.options[1].selected=true;
document.editServiceControl.Utility.options[1].selected=true;
document.editServiceControl.PayCard.options[1].selected=true;
document.editServiceControl.TestMerit.options[1].selected=true;
document.editServiceControl.DTHX.options[1].selected=true;
document.editServiceControl.rail.options[1].selected=true;
document.editServiceControl.SMS_TXN.options[1].selected=true;
}
}
</script>



<script language="javascript">

function submitForm(){
//var document.editServiceControl..value;
document.editServiceControl.action="editServiceMgt.action";
document.editServiceControl.submit();
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
    
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                    
                      <td width="50%" height="40" align="left" valign="middle" class="heading_mgs">
                      Edit Service - Agent
                      </td>
                    </tr>
                      
                    <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
					<tr>
                    <td colspan="4">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" id="border" style="padding:20px">
                    <tr>
                    <td>
                    
                      <!--sertive control table start-->
                      <form name="editServiceControl">
                      <table id="control_tbl" cellspacing="0" cellpadding="0" width="45%" align="center" class="tbls" ><!--style="display:none;"-->
                                        <tbody style="background:#a74312;">
                                         
										<tr  class="hd tabulardata" align="center" >
                                        <th colspan="100%" height="30px" align="center" style="font-family:'Trebuchet MS'; font-size:13px;color:#FFF;">
                                        Service Control
                                        </th>
                                        </tr>
					
                                          <tr  class="hd tabulardata" align="center" >
                                          
										  <input type="hidden" name="param" value="EditServiceOfAgent"  />
                                            <td width="5%" align="center" style="border-bottom:0px solid #930;color:#FFF;"><strong>S.N.</strong></td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;color:#FFF;"><strong>Service</strong></td>
                                            <td width="3%" align="center" style="border-bottom:0px solid #930;border-right:0px solid #930;color:#FFF;">
                                            <select class="actionclass" name="myselects" onchange="myfunc3()" style="width:60px;padding:1px;margin:5px;height:20px;">
                                            <option selected="selected" value="actions">Action</option>
                                            <option value="y">Y</option>
                                            <option value="n">N</option>
                                            </select>
                                            
                                            </td>
                                            <td width="10%" align="center" style="border-bottom:0px solid #930;border-right:1px solid #930;color:#FFF;"><strong>Status</strong></td>
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
											</select>
											</td>
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
											</select>
											</td>
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
											</select></td>
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
											</select></td>
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
											</select></td>
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
                                             <td style="border-bottom:1px solid #930;"> 
											 <% mark=(String)serviceData.get("TestMerit");
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
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("DTHX");
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
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("rail");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="rail" style="width:45px;padding:1px;margin:5px;height:20px;">
											<option value="<%=mark%>"><%=mark%></option>
											<option value="<%=mark1%>"><%=mark1%></option>
											</select></td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center">
											<%=showservice%></td>
                                          </tr>
                                         <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                         	<td align="center" >10</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">SMS Txn</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("SMS_TXN");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="SMS_TXN" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
											</select>
											</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
										  
										  <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                         	<td align="center" >11</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">Holidays</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("Holidays");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="Holidays" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
											</select>
											</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
										  
										  
										 <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                         	<td align="center" >12</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">Wallet To Wallet</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("WW");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="WW" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
											</select>
											</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
										  
                                          <tr bgcolor="#ffffff" align="center" class="value_reg bords">
                                         	<td align="center" style="border-bottom:1px solid #930;">12</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;" align="center">DMR</td>
                                             <td style="border-bottom:1px solid #930;border-top:0px solid #930;"> 
											 <% mark=(String)serviceData.get("DMR");
											if(mark.equalsIgnoreCase("Y")){mark1="N";showservice="Active";}
											else{mark1="Y";showservice="Dective";}%>
											<select class="select1" name="DMR" style="width:45px;padding:1px;margin:5px;height:20px;">
												<option value="<%=mark%>"><%=mark%></option>
												<option value="<%=mark1%>"><%=mark1%></option>
											</select>
											</td>
                                            <td style="border-bottom:1px solid #930;border-top:0px solid #930;border-right:1px solid #930;" align="center"><%=showservice%></td>
                                          </tr>
                                          
                                          
                                          </tbody>
                                           <tr style="background:none; border:none;">
                                        <td style="background:none; border:none; height:10px;" colspan="100%" align="right">
                                        </td>
                                        </tr>
                                         <tr style="background:none; border:none;">
                                        <td style="background:none; border:none;" colspan="100%" align="right">
                                        <input class="cls_btn" type="button" value="Update" onclick="submitForm()" /></td>
                                        </tr>
                                      </table>
                        </form> 
                        
                           </td>
                      </tr>
                        </table>  
                                     
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

