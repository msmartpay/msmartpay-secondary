<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<%@ page import="java.util.*" %>
<%

String CyberOperators=(String)request.getAttribute("CyberOperators");

String EzyPayOperators=(String)request.getAttribute("EzyPayOperators");
String RootOperators=(String)request.getAttribute("RootOperators");
String BillpaymartOperators=(String)request.getAttribute("BillpaymartOperators");
String serviceType=(String)request.getAttribute("serviceType");
ArrayList<HashMap<String,Object>> AgentService=(ArrayList<HashMap<String,Object>>)request.getAttribute("AgentService");
String type=(String)request.getAttribute("type");
if(type==null)
{
	type="tep";
}

int size=0;

					 if (AgentService==null)
					 {
						 size=-1;
					 }
					 else
					 {
						 size=AgentService.size();
						
					 }
					// System.out.println("Size of AgentService------>>>>>>>>>>>-"+size);
String message=(String)request.getAttribute("message");
if(message==null)
{
message="";

}

%>

<style>

select{color:#930;}
select option{color:#930;}

</style>
<script language="javascript">
function submitForm()
{
	//alert('hello i am here');
	var type=document.getElementById("opsid").value;
	document.OperatorControlForm.submit();

}


function Details()
{

var main=document.OperatorControlForm.serviceType.value;
alert(main);

if(main=="mobile")
{

document.getElementById("mobiledata").style.display="";
document.getElementById("dthdata").style.display="none";
}
else
{
document.getElementById("mobiledata").style.display="none";
document.getElementById("dthdata").style.display="";
}
}



function ok2(val)
  
 {
 	
var tttt2=val;
var service="";
createXMLHttpRequest();

var type=document.getElementById("opsid").value;
var mainservice="web";
var service=document.OperatorControlForm.serviceType.value;
xmlHttp.onreadystatechange = printValues2;
xmlHttp.open("POST","getOperator.action?param="+type+"&serviceType="+service+"&mainService="+mainservice, true);
xmlHttp.send(null);
}


function printValues2()
{
if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response2=xmlHttp.responseText;
				if(response2=="Valid")
				{
				
				
              solution(tttt2);
								
				}
				else
				{
				document.getElementById("operatorName").style.display ="";
				document.getElementById("Operator").innerHTML = response2;				
			
				}
			}
		}
}
 
 
 
 var xmlHttp;

function createXMLHttpRequest()
{

	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		
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
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
<tr>
<td valign="top" align="center" >
<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="rounded-corners box_heights">
<tr>
<td valign="top" align="center" >

		<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
			
                    <tr>
                    <%if(type.equalsIgnoreCase("tep")){ %>
                      <td  width="100%" valign="bottom" height="40" class="heading_mgs">Recharge Operator Control<div align="center"></div></td>
                   <%} %>
                    <%if(type.equalsIgnoreCase("egan")){ %>
                      <td  width="100%" valign="bottom" height="40" class="heading_mgs">Egan Recharge Operator Control<div align="center"></div></td>
                   <%} %>
                    </tr>
                    <tr>
                      <td valign="bottom" height="10" align="center" class="dyn_mgs" ><%=message%></td>
                    </tr>
                    
                    <tr>
                    <td colspan="4" >
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" class="border" style="margin-bottom:20px;">
                    <tr>
					<td width="100%" colspan="5" align="center" valign="top">
					<form name="OperatorControlForm" method="post" action="getOperatorControlDetail.action" >  
					   <table cellpadding="0" cellspacing="0" width="500" align="center" border="0"  class="mydata_tabl" >
					   <tr><td align="left" height="30"><strong>Select Service Type</strong></td>
					   	<td>
					   		<select name="" style="width:250px">
                        	<option value="">Recharge Services</option>
                       		</select>
                       	</td>
                       </tr>
						<tr>
						<td><input type="hidden" name="param" id="opsid" value="<%=type%>" />
						</td>
						</tr>
                                        
                         <tr><td align="left" height="30"><strong>Select Sub-Service</strong></td>
					     <td><select style="width:250px" id="req" name="serviceType"  onchange="return ok2(this)">
					               <option value="select">Select</option>
                                      <option value="mobile">Mobile Recharge</option>
                                      <option value="dth">DTH Recharge</option>
                                      <option value="datacard">Data Card Recharge</option>
                              </select></td></tr>
                                        
           
            <tr id="operatorName" style="display:none"><td align="left" height="30"><strong>Select Operator</strong></td>
					   <td><select name="Operator"  id="Operator"  style="width:250px"  ></select>
			</td></tr>                     
                                        
			<tr><td></td><td align="left" height="40"><input type="button" value="Submit" style="width:110px; height:25px;" onclick="submitForm()" /></td></tr>
					   </table>
					  
					 </form>
					 </td></tr>
					 </table>
					  </td></tr>
					  
                    
                    <tr>
                      <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">
                          
                          
                          <tr>
                            <td valign="top" align="center">
                           <%if(size>0) {%>
                          <form name="update" method="post" action="updateOperatorControl.action">
                            <table width="100%" border="0" cellspacing="0" cellpadding="0" >
                             
                                <tr>
                                  <td height="10" align="left" valign="middle" style="padding-left:15px;"  class="big1"><input type="hidden" value="<%=serviceType%>" name="serviceType" /></td>
                                </tr>
                                <tr>
                                  
                                  <td valign="top">
								  <table cellspacing="0" cellpadding="0" width="96%" align="center" class="tbls"  bgcolor="#a74312">
                                     
                                        <tr  class="hd" align="center">
                                          <td width="8%"  rowspan="2"><strong>Sl.No.</strong>
                                            <input type="hidden" name="param" value="<%=type%>" /></td>
                                          <td width="12%" rowspan="2"><strong>Operator</strong></td>
                                          <td  valign="middle" colspan="6" height="25"><strong>Vendor Availability for Respective Operator</strong></td>
                                         
                                        </tr>
                                        <tr align="center" height="20" class="hd">
                                          <td width="13%"><strong>EzyPay</strong></td>
                                          <td width="13%"><strong>Cyberplat</strong></td>
                                          <td width="12%"><strong>Root Compark</strong></td>
                                          <td width="13%"><strong>Tata Sky</strong></td>
                                          <td width="14%"><strong>VideoCon Dth</strong></td>
										  <td width="26%"><strong>Billpaymart</strong></td>
                                        </tr>
                                        <%  
							int i=0;
		 					   for ( i=0;i<size;i++ )
					          {
						       HashMap<String,Object> mapdate=(HashMap<String,Object>)AgentService.get(i);
							   String ope=(String)mapdate.get("operator");
							   //System.out.println("mapdate is "+mapdate);
							   //System.out.println("ope-------"+ope);
							    String Vendor=(String)mapdate.get("Vendor");
								 //System.out.println("Vendor-------"+Vendor);
							   
						      %>
                                        <tr class="value_reg" align="center" bgcolor="#ffffff">
                                          <td height="25"><%=i+1%></td>
                                          <td><%=ope%></td>
                                          <td><%  
									  boolean rin = EzyPayOperators.contains(ope); 
									  if(rin){   
										  //System.out.println("inside EzyPay-------");  
									  if("EzyPay".equalsIgnoreCase(Vendor))
									  {
										  //System.out.println("inside EzyPay-------1");
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  checked="checked" value="<%=ope%>&EzyPay"/>
                                              <%} else {
										 // System.out.println("inside EzyPay-------2");
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  value="<%=ope%>&EzyPay" />
                                              <%}%>
                                              <%} else {
										  //System.out.println("inside EzyPay-------3");
									  %>
                                              <input type="radio" disabled="disabled"  value="disable"/>
                                              <%}%>                                          </td>
                                          <td><%  
									  boolean cyber = CyberOperators.contains(ope); 
									  if(cyber){
									  if("CYBERPLAT".equalsIgnoreCase(Vendor))
									  {//System.out.println("we are inside cyberpalt Manoj");
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="cyberradio<%=i%>"  checked="checked" value="<%=ope%>&CYBERPLAT"/>
                                              <%} else {//System.out.println("we are inside cyberpalt else Manoj");%>
                                              <input type="radio" name="radioname<%=i%>" id="cyberradio<%=i%>"  value="<%=ope%>&CYBERPLAT"/>
                                              <%}%>
                                              <%} else {//System.out.println("we are inside cyberpalt 2nd else Manoj");%>
                                              <input type="radio" disabled="disabled"   value="disable"/>
                                              <%}%></td>
                                          <td><%  
									  boolean goal = RootOperators.contains(ope); 
									  if(goal){ 
									  if("Root".equalsIgnoreCase(Vendor))
									  {
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="goalradio<%=i%>"  checked="checked" value="<%=ope%>&Root"/>
                                              <%} else {%>
                                              <input type="radio" name="radioname<%=i%>" id="goalradio<%=i%>"  value="<%=ope%>&Root" />
                                              <%}%>
                                              <%} else {%>
                                              <input type="radio" disabled="disabled"  value="disable" />
                                              <%}%></td>
                                          <td><%  
									
									  if("TATASKY".equalsIgnoreCase(ope)){
									   //System.out.println("Vendor-----2--");
									     if("TATASKY".equalsIgnoreCase(Vendor)){									    	
									    	 
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"  checked="checked" value="<%=ope%>&TATASKY"/>
                                              <%}else{%>
                                              <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"   value="<%=ope%>&TATASKY"/>
                                              <%} }else {%>
                                              <% //System.out.println("Vendor----3---");%>
                                              <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"  value="<%=ope%>&TATASKY"  disabled="disabled"/>
                                              <%}%>                                          </td>
                                       <td><%  
									
									  if("VIDEOCONDTH".equalsIgnoreCase(ope)){
									   //System.out.println("Vendor-----2--");
									   if("VIDEOCOND2H".equalsIgnoreCase(Vendor))
									   {
									  %>
                                              <% //System.out.println("Vendor----1---");%>
                                              <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  checked="checked" value="<%=ope%>&VIDEOCOND2H"/>
                                              <%}else{ %>
                                              <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  value="<%=ope%>&VIDEOCOND2H"/>
                                              <%}} else {%>
                                              <% //System.out.println("Vendor----3---");%>
                                              <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  disabled="disabled" value="<%=ope%>&VIDEOCOND2H"/>
                                              <%}%>                                          
											  </td>
											  <td><%  
									  boolean billpaymart = BillpaymartOperators.contains(ope); 
									  if(billpaymart){   
										 // System.out.println("inside billpaymart-------");  
									  if("billpaymart".equalsIgnoreCase(Vendor))
									  {
										  //System.out.println("inside billpaymart-------1");
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  checked="checked" value="<%=ope%>&Billpaymart"/>
                                              <%} else {
										  //System.out.println("inside billpaymart-------2");
									  %>
                                              <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  value="<%=ope%>&Billpaymart" />
                                              <%}%>
                                              <%} else {
										  //System.out.println("inside billpaymart-------3");
									  %>
                                              <input type="radio" disabled="disabled"  value="disable"/>
                                              <%}%>                                          </td>
                                                                                </tr>
                                        <%}%>
                                      
                                  </table></td>
                                </tr>
                                <tr>
                                  <td align="right" height="35" valign="middle" style="padding-right:20px;"><input name="submit" type="submit"  style="width:80px; height:28px;" value="Update" />                                  </td>
                                </tr>
                                <tr>
                                  <td height="19" valign="bottom" align="center" class="hh1" ><a href="#"></a></td>
                                </tr>
                            
                            </table></form>
                          <%}%></td>
                          </tr>
                          
                          <tr>
                            <td colspan="5" height="3"></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="28"></td>
                    </tr>
                  </table></td>
</tr>
</table></td>
</tr>
</table>
</td>
</tr>
<tr>
<td width="100%" valign="top" align="center" height="240"></td>
</tr>
<tr>
<td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
</tr>
</table>
</body>
</html>
