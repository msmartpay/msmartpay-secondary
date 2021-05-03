<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import = "java.util.HashMap " %>
<%@ page import = "java.util.ArrayList" %> 
<%
String mark="";
String mark1="";
int flag=0;
String ip_address=(String)request.getRemoteAddr() ;
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}
ArrayList editservice= (ArrayList)request.getAttribute("Data");
int sizeofArryList=0;
if(editservice==null)
{
sizeofArryList=-1;
}
else
{
sizeofArryList=editservice.size();
}
String corpAgntId="";
String service="";
String umark="";
String cmark="";
String service1="";
int sum=0;
int i=0;
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<style>
select{color:#930;}
select option{color:#930;}
</style>
<script language="javascript">
function submitclientform()
{
document.clientDetails.submit();
}
function updateAgntOptr(i)
{
document.editservice.action="EgenCommsion.action?param=updateOptrStatusComm&status="+i;
document.editservice.submit();
}
function updateAllOptrStatus()
{
document.editservice.action="EgenCommsion.action?param=UpdateAllOptrStatus";
document.editservice.submit();
}
function updateAllOptrComm()
{
document.editservice.action="EgenCommsion.action?param=UpdateAllOptrComm";
document.editservice.submit();
}
function updateAll()
{
document.editservice.action="EgenCommsion.action?param=UpdateAll";
document.editservice.submit();
}
</script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@include file="/header.jsp"%></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
	<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    
                    <tr>
                      <td valign="middle" height="40" align="left" class="heading_mgs"> Edit Egen Commsion</td>
                    </tr>
                    <tr>
                      <td valign="bottom" align="left" class="dyn_mgs" ><%=message%></td>
                    </tr>
                    <tr>
                      <td valign="middle" height="0" align="center" style="" class="logintxt" >
					  <form name="clientDetails" action="EgenCommsion.action?param=getAgentOptrDetails" method="post">
                       <table class="mydata_tabl" id="border" align="center" border="0" cellpadding="0" cellspacing="0" width="86%">
					   <tbody>
                          <tr>
                            <td colspan="4" class="style111" align="center" height="30"></td>
                          </tr>
                          <tr>
                            <td width="19%"></td>
                            <td align="left" width="24%"><strong>Enter Corporate Agent ID</strong></td>
                            <td align="left" width="8%">:</td>
                            <td align="left" width="49%"><input  name="corpAgentId" onselectstart="return false" autocomplete="off/" tooltiptext="Type User ID in this box" type="text" /></td>
                          </tr>
                          <tr>
                            <td colspan="4" height="15"><span style="padding-left:150px"> </span></td>
                          </tr>
                          <tr id="portalIdDiv">
                            <td></td>
                            <td align="left"><strong>Select Service</strong></td>
                            <td align="left">:</td>
                            <td align="left"><select name="service" class="style2" onchange="abc">
                              <option value="mobile">Mobile</option>
				              <option value="dth">DTH</option>
				              <option value="billpay">Billpay</option>
				              <option value="datacard">Data Card</option>
                              </select>
                              </td>
                          </tr>
                           <tr>
                            <td colspan="4" height="15"><span style="padding-left:150px"> </span></td>
                          </tr>
                           <tr>
                            <td colspan="3"></td>
                            <td align="left" width="49%"><span style="padding-left:0px">
                              <input name="Submit2" value="Submit" class="cls_btn" id="sms" onclick="submitclientform()" type="button" />
                            </span></td>
                          </tr>
                        </tbody>
                        
                        <tr>
                          <td colspan="4" height="15"></td>
                        </tr>
                        <tr>
                          <td></tbody></td>
                        </tr>
						
                      </table>
					  </form>
					  
					  </td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="4" height="10"></td>
                    </tr>
                    <tr>
					<%if(editservice != null ){%>
                      <td valign="top" align="center">
					  <form name="editservice" method="post">
					  <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="form11">

                          <tr>
                            <td width="100%" colspan="5" align="center" valign="top">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td height="35" align="left" valign="middle"  class="heading_mgs1">Commsion and Status Details</td>
                                    </tr>
								
									<tr>
									  <td align="center"> 	</td></tr>
								
									<tr>
									  <td align="center"></td></tr>
                                    <tr>
                                      <td valign="top" style="border-left:1px solid #a74312;">
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                                         
                                          <tr class="hd" align="center" style="background:#a74312;">
                                            <td width="6%" height="25" class="right_border"><strong>S.N.</strong></td>
                                            <td width="19%" class="right_border"><strong>Corporate Agent ID</strong></td>
                                            <td width="13%" class="right_border"><strong>Operator Name</strong></td>
                                            <td width="9%" align="center" style="padding-left:0px;" class="right_border"><strong>Service</strong></td>
                                            <td width="12%" class="right_border"><strong>Status</strong></td>
                                            
                                            <td width="17%" align="center" class="right_border" style="padding-left:px;"><strong><strong>Commission</strong></td>
                                            <td width="14%" align="center" style="padding-left:px;" class="right_border"><strong>Commission Mark</strong></td>
                                            <td width="10%" align="center" style="padding-left:px;" class="right_border"><strong>Update</strong></td>
										  </tr>
                                          
										   <%		
									
									for(i=0;i<sizeofArryList;i++)
									{	
									  HashMap hm=(HashMap)editservice.get(i);	
									%>
                                          <tr bgcolor="#FFFFFF" align="center" class="value_reg">
                                          <td height="25"><%=(i+1)%></td>
                                            <td><%=hm.get("corporateAgntId")%></td>
                                            <td><%=hm.get("operatorName")%></td>
											 <%
							  service=(String)hm.get("service");
							  if(service.equals("mobile"))
							  service1="MOBILE";
							  if(service.equals("dth"))
							  service1="DTH";
							  if(service.equals("billpay"))
							  service1="BILL PAY";
							  if(service.equals("datacard"))
							  service1="DATACARD";
							
							%>
        <td align="center" valign="middle" style="padding-left:0px;"><%=service1%></td>
		<%
							mark=(String)hm.get("status");
							if(mark.equalsIgnoreCase("y")){
							mark1="N";
							}
							else{
							mark1="Y";
							
							}
							
								%>
        <td><span class="style3" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
        <select name="mark<%=i%>">
		<option value="<%=mark%>"><%=mark%></option>
		<option value="<%=mark1%>"><%=mark1%></option>
		</select>
       </span></td>
       <td><span class="style3">
       <input type="text" name="comm<%=i%>" value="<%=hm.get("comm")%>" maxlength="5" style="width:120px;"/>
                                             </span></td>
                                        
                                            <td align="center" style="padding-left:px;"><span class="style3" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
                                              <select name="commMark<%=i%>" >
		 <%
		 cmark=(String)hm.get("commMark");
		 if(cmark.equalsIgnoreCase("p"))
		 umark="R";
		 else
		 umark="p";
		
		 %>
                  <option value="<%=cmark%>" selected="selected"><%=cmark%></option>
				 <option value="<%=umark%>"><%=umark%></option>
                </select>
                                            </td>
									<%
									sum=i+1;	
									%>
									
                                            <td align="center" style="padding-left:px;"><span class="style3">
                                            <input name="operator<%=i%>" type="hidden" value="<%=hm.get("operator")%>" />
    										<input name="submit<%=i%>" type="button" class="buttonbg" value="Update" onclick="updateAgntOptr(<%=i%>)" />
                                            </span><input type="hidden" name="corporateAgntId" value="<%=hm.get("corporateAgntId")%>" /></td>
                                            </tr>
											
											<%}%>
                                        </tbody>
                                      </table>
                                      <input name="service" type="hidden" value="<%=service%>" />
									<input type="hidden" name="SNo" value="<%=sum%>" /></td>
                                    </tr>
                                    <tr>
			
															                                  </tr>
                                  </tbody>
                                </table>
                              </td>
                          </tr>
                          <tr>
                            <td height="8" colspan="5" align="center" valign="top"></td>
                          </tr>
                          <tr>
                            <td colspan="5" align="center" valign="top"></td>
                          </tr>
                          <tr>
                            <td height="20" colspan="9" align="right" valign="middle"><span style="padding-left:0px">
     <input name="submitstatus" type="button" class="buttonbg" value="Update Status"  onclick="updateAllOptrStatus()" style="width:100px;color:#930;" />
     <input name="submitcomm" type="submit" class="buttonbg" value="Update Commission" onclick="updateAllOptrComm()" style="width:135px;color:#930;" />
    <input name="submitall" type="submit" class="buttonbg" value="Update All" onclick="updateAll()" style="width:100px;color:#930;" />
                            </span></td>
                          </tr>
                        </table>
					  </form>
					  </td>
					<% } %>
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
    <td width="100%" valign="top" align="center" height="102"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>

