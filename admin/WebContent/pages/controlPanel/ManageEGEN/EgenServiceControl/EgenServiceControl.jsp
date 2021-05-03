<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import="java.util.*" %>
<%

ArrayList<HashMap<String,Object>> getEgenIdDetails=(ArrayList<HashMap<String,Object>>)request.getAttribute("getEgenIdDetails");


int size=0;
if(getEgenIdDetails==null)

{
size=-1;
}
else
{

size=1;
}

String message=(String)request.getAttribute("message");
if(message==null)
{

message="";
}
%>
<SCRIPT type="text/javascript" src="scripts/EgenWLServiceControlValidation.js"></script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
		  
          <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0">
              <tr>
                <td colspan="100%" valign="middle" height="40" align="left" class="heading_mgs1">Service Manage </td>
              </tr>
              <tr>
                <td colspan="100%" align="center"><%=message%></td>
              </tr>
              <tr>
              
                <td valign="top" align="center" class="border">
				
                <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >
                    
                    
				    <tr>
                      <td colspan="100%">
					  
                      <form name="serarchStatus" method="post" action="getUpdateEgenServiceDetails.action">
                    
                     
                       <table cellspacing="0" cellpadding="0" width="100%" align="center"  class="mydata_tabl">
					 
                          <tr>
                            <td>&nbsp;</td>
                            <td  height="30">&nbsp;</td>
                            <td height="30"><input type="hidden" name="mainservice" value="EgenServiceControl" /></td>
                            <td></td>
                            <td></td>
                          </tr>
                          <tr>
                            <td width="22%">&nbsp;</td>
                            <td width="22%"  height="30"><strong> Select User</strong> </td>
                            <td height="30" width="25%"><Select name="userType" style="width:400px">
                                <option value="select">Select</option>
                                <option value="All">All</option>
								 <% 
                                for (int i=0;i<getEgenIdDetails.size();i++) 
                                     {
									  
                                        HashMap hm=(HashMap)getEgenIdDetails.get(i);
									   %>
								<option value="<%=hm.get("corporateAgentId")%>">API<%=hm.get("corporateAgentId")%>--<%=hm.get("companyName")%></option>
							<%}%>
                            </Select></td><td width="11%"></td><td width="11%"></td>
                          </tr>
							
						<tr>
						  <td width="16%">&nbsp;</td>
						  <td width="16%"><strong>Select Service</strong> </td>
                            <td width="26%" align="left" colspan="" >
							 <select style="width:400px" name="service"  id="serviceID" onchange="getSubService()">
					   				<option value="select">Select</option>
                                      <option value="rechargeService">Recharge Service</option>
                                     <option value="otherService">Others Service</option>
                                        </select>							</td><td width="11%"></td><td width="11%"></td></tr>
                            
                         
                          <tr id="rechargeService" style="display:none">
                            <td width="22%">&nbsp;</td>
                            <td width="22%"  height="30"><strong>Select SubService</strong></td>
                               <td>       <select style="width:400px"  name="subservice" id="serviceID" onchange="return ok2(this)">
					   				<option value="select">Select</option>
                                      <option value="mobile">Mobile Recharge</option>
                                      <option value="dth">DTH Recharge</option>
                                      <option value="datacard">Data Card Recharge</option>
									  <option value="AllRechargeService">All</option>
                                     </select> </td>
                            <td width="16%" align="left"><span id="checkRecharge" style="visibility:hidden"><img src="images/loading2.gif" height="30" width="30"/></span></td>
                            <td width="26%" align="left" colspan="" style="padding-left:20Px">&nbsp;</td>
                          </tr>
						
						<tr id="otherserviceDetails" style="display:none">
						  <td width="22%">&nbsp;</td>
                            <td width="22%"  height="30"><strong>Select SubService</strong></td>
                               <td>       <select style="width:400px"  name="Othersubservice" onchange="return ok2(this)">
					   				   <option value="select">Select</option>
                                       <option value="billpay">Bill Payment</option>
									 
                                     </select></td>
                          
                            <td width="11%" align="left"><span id="checkOther" style="visibility:hidden"> <img src="images/loading2.gif" height="30" width="30"/>
                            </span></td><td width="11%"></td>
						</tr>
						  <tr id="operatorName" style="display:none">
						    <td align="left">&nbsp;</td>
                           <td align="left" height="30"><strong>Select Operator</strong></td>
					   <td><select name="Operator"  id="Operator"  style="width:400px"  ></select>	</td>
                            <td width="16%" style="padding-left:40Px">&nbsp;</td>
                            <td width="26%" align="left" colspan="" style="padding-left:20Px">&nbsp;</td>
                          </tr>
						  
						  <tr>
						    <td align="left" id="userIdName" style="display:none">&nbsp;</td>
                           <td align="left" height="30" id="userIdName" style="display:none">&nbsp;</td>
					   
                            <td width="16%" style="padding-left:40Px">&nbsp;</td>
                            <td id="userIdValue" style="display:none" align="left">
					     <input name="Input2" type="button" value="Go"  style="width:50px; color:#930;" onclick="GO()" /></td>
                            <td width="26%" align="left" colspan="" style="padding-left:20Px"></td>
                          </tr>
</table>
					   
                      </form>
                      
                      </td>
                    </tr>
					
                    <tr>
                      <td height="10" align="right" valign="middle" style="padding-right:15px"></td>
                    </tr>
					
					
                  </table>
				  
				  </td>
                  
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table>
			
			</td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" height="144px" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>





