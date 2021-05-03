<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<%@ page import="java.util.*" %>
<%

String CyberOperators=(String)request.getAttribute("CyberOperators");

String RinOperators=(String)request.getAttribute("RinOperators");
String GoalOperators=(String)request.getAttribute("GoalOperators");
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
					 System.out.println("Size of AgentService------>>>>>>>>>>>-"+size);
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


</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                    <%if(type.equalsIgnoreCase("tep")){ %>
                      <td  width="100%" valign="bottom" height="40" align="left" style="padding-left:105px" class="logintxt" >Recharge Operator Control<div align="center"><%=message%></div></td>
                   <%} %>
                    <%if(type.equalsIgnoreCase("egan")){ %>
                      <td  width="100%" valign="bottom" height="40" align="left" style="padding-left:105px" class="logintxt" >Egan Recharge Operator Control<div align="center"><%=message%></div></td>
                   <%} %>
                    </tr>
                    <tr>
                      <td colspan="4" height="20"></td>
                    </tr>
                    <form name="OperatorControlForm" method="post" action="getOperatorControlDetail.action" >
                    <tr><td colspan="4" >
					  
					   <table cellpadding="0" cellspacing="0" width="500" align="center" border="0"  class="form11" >
					   <tr><td align="left" height="30">Select Service Type</td>
					   	<td>
					   		<select name="">
                        	<option value="">Recharge Services</option>
                       		</select>
                       	</td>
                       </tr>
						<tr>
						<td><input type="hidden" name="param" id="opsid" value="<%=type%>" />
						</td>
						</tr>
                                        
                         <tr><td align="left" height="30">Select Sub-Service</td>
					     <td><select style="width:300px" id="req" name="serviceType"  onchange="return ok2(this)">
					               <option value="select">Select</option>
                                      <option value="mobile">Mobile Recharge</option>
                                      <option value="dth">DTH Recharge</option>
                                      <option value="datacard">Data Card Recharge</option>
                                        </select></td></tr>
                                        
           <!--                             <tr ><td align="left" height="30">Select Operator</td>
					   <td >
					   <select name="Operator"  class="cal22" style="width:300px"  >
					   
					  <option value="AIRCEL">AIRCEL</option>
					  <option value="BSNL ">BSNL </option>
					  <option value="IDEA">IDEA</option>
					  <option value="RELIANCEGSM">RELIANCEGSM</option>
					    <option value="ALL">ALL</option>
					   </select>
	</td>    
	
	</tr-->
            <tr id="operatorName" style="display:none"><td align="left" height="30">Select Operator</td>
					   <td><select name="Operator"  id="Operator"  style="width:300px"  ></select>
	</td></tr>                     
                                        
								<tr><td></td><td align="right" height="40"><input type="button" value="Submit" style="width:110px; height:25px; margin:0 70px 0 0" onclick="request()"/></td></tr>
					   </table>
					  
					  </td></tr>
					  
                    </form>
                    <tr>
                      <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">
                          
                          <%if(size>0) {%>
                          <form name="update" method="post" action="updateOperatorControl.action">
                          <tr>
                            <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                  <tr>
                                    <td height="10" align="left" valign="middle" style="padding-left:15px;"  class="big1"></td>
                                  </tr>
                                  <tr>
								  <input type="hidden" value="<%=serviceType%>" name="serviceType">
                                    <td valign="top"><table cellspacing="1" cellpadding="1" width="98%" align="center" class="form11"  bgcolor="#a74312">
                                        <tbody>
                                          <tr  class="hd" align="center">
                                            <td  rowspan="2">Sl.</td>
                                            <td rowspan="2">Operator</td>
                                            <td  valign="middle" colspan="5" height="25">Vendor Availability for Respective Operator</td>
                                            <td><input type="hidden" name="param" value="<%=type%>"></inp></td>
                                          </tr>
                                            <tr align="center" height="20" class="hd">
                                                  <td width="16%">RIN</td>
                                                    <td width="16%">Cyberplat</td>
                                                     <td width="16%">GPIPL</td>
													  <td width="16%">Tata Sky</td>
													   <td width="16%">VideoCon Dth</td>
                                                </tr>
												
							<%  
							int i=0;
		 					   for ( i=0;i<size;i++ )
					          {
						       HashMap<String,Object> mapdate=(HashMap<String,Object>)AgentService.get(i);
							   String ope=(String)mapdate.get("operator");
							   System.out.println("mapdate is "+mapdate);
							   System.out.println("ope-------"+ope);
							    String Vendor=(String)mapdate.get("Vendor");
								 System.out.println("Vendor-------"+Vendor);
							   
						      %>
                                   <tr align="center" bgcolor="#ffffff">
                                        <td height="25"><%=i+1%></td>
                                      <td><%=ope%></td>
                                      <td>
									  <%  
									  boolean rin = RinOperators.contains(ope); 
									  if(rin){   
										  System.out.println("inside RIN-------");  
									  if("RIN".equalsIgnoreCase(Vendor))
									  {
										  System.out.println("inside RIN-------1");
									  %>
									  <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  checked="checked" value="<%=ope%>&RIN"/>
									  <%} else {
										  System.out.println("inside RIN-------2");
									  %>
									 
									  <input type="radio" name="radioname<%=i%>" id="rinradio<%=i%>"  value="<%=ope%>&RIN" />
									  <%}%>
									  <%} else {
										  System.out.println("inside RIN-------3");
									  %>
									  <input type="radio" disabled="disabled"  value="disable"/>
									  <%}%>
									   </td>
									   
                                      <td> <%  
									  boolean cyber = CyberOperators.contains(ope); 
									  if(cyber){
									  if("CYBERPLAT".equalsIgnoreCase(Vendor))
									  {System.out.println("we are inside cyberpalt Manoj");
									  %>
									  <input type="radio" name="radioname<%=i%>" id="cyberradio<%=i%>"  checked="checked" value="<%=ope%>&CYBERPLAT"/>
									  <%} else {System.out.println("we are inside cyberpalt else Manoj");%>
									  <input type="radio" name="radioname<%=i%>" id="cyberradio<%=i%>"  value="<%=ope%>&CYBERPLAT"/>
									  <%}%>
									 
									  <%} else {System.out.println("we are inside cyberpalt 2nd else Manoj");%>
									  <input type="radio" disabled="disabled"   value="disable"/>
									  <%}%></td>
									  
									  
                                      <td> <%  
									  boolean goal = GoalOperators.contains(ope); 
									  if(goal){ 
									  if("GOPROCESS".equalsIgnoreCase(Vendor))
									  {
									  %>
									  <input type="radio" name="radioname<%=i%>" id="goalradio<%=i%>"  checked="checked" value="<%=ope%>&GOPROCESS"/>
									  <%} else {%>
									  <input type="radio" name="radioname<%=i%>" id="goalradio<%=i%>"  value="<%=ope%>&GOPROCESS" />
									  <%}%>
									  <%} else {%>
									  <input type="radio" disabled="disabled"  value="disable" />
									  <%}%></td>
                                   
								    <td> <%  
									
									  if("TATASKY".equalsIgnoreCase(ope)){
									   System.out.println("Vendor-----2--");
									     if("TATASKY".equalsIgnoreCase(Vendor)){									    	
									    	 
									  %>									 
									  
									  <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"  checked="checked" value="<%=ope%>&TATASKY"/>
									  <%}else{%>
									  <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"   value="<%=ope%>&TATASKY"/>
									  
									     <%} }else {%>
									    <% System.out.println("Vendor----3---");%>
									  <input type="radio" name="radioname<%=i%>" id="TATASKYradio<%=i%>"  value="<%=ope%>&TATASKY"  disabled="disabled"/>
									  <%}%>
									 
									</td>
									  <td> <%  
									
									  if("VIDEOCONDTH".equalsIgnoreCase(ope)){
									   System.out.println("Vendor-----2--");
									   if("VIDEOCOND2H".equalsIgnoreCase(Vendor))
									   {
									  %>
									  <% System.out.println("Vendor----1---");%>
									  <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  checked="checked" value="<%=ope%>&VIDEOCOND2H"/>
									  <%}else{ %>
									   <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  value="<%=ope%>&VIDEOCOND2H"/>
									  <%}} else {%>
									    <% System.out.println("Vendor----3---");%>
									  <input type="radio" name="radioname<%=i%>" id="VIDEOCOND2Hradio<%=i%>"  disabled="disabled" value="<%=ope%>&VIDEOCOND2H"/>
									  <%}%>
									 
									  </td>
									  
                                     </tr>
                                  <%}%>

                                        </tbody>
                                      </table></td>
                                  </tr>
                                  <tr>
                                    <td align="right" height="50" valign="middle" style="padding-right:10px;">
                                    <input type="submit"  style="width:80px; height:28px;" value="Update" />
</td>
                                  </tr>
                                  <tr>
                                    <td height="26" valign="bottom" align="center" class="hh1" ><a href="#"></a></td>
                                  </tr>
                                </tbody>
                              </table></td>
                          </tr>
                          </form>
                          <%}%>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                        </table></td>
                    </tr>
                    <tr>
                      <td colspan="4" height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">
function request()
{
var type=document.getElementById("opsid").value;
document.OperatorControlForm.submit();

}


function Details()
{

var main=document.OperatorControlForm.serviceType.value;
alert(main)

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