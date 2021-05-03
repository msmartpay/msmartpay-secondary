<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import = "java.util.HashMap " %>
<%@ page import = "java.util.ArrayList" %> 
<%
int flag=0;
String ip_address=(String)request.getRemoteAddr() ;

System.out.println("we are on this Fund Transfer page");
ArrayList fundData= (ArrayList)request.getAttribute("fundData");
int size=0;
if(fundData !=null){
size=fundData.size();
}else{
size=-1;
}
System.out.println("Size is "+size);
 String service1="hello";
//System.out.println("In JSP"+fundtransfer);
String status="";
status= (String)request.getAttribute("status");
if(status==null){
status="";
}
String message="";
message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="useractivation-22 Nov/css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="useractivation-22 Nov/images/t.png" />
<link href="useractivation-22 Nov/css/stickytooltip.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#calendarDiv{position:fixed !important;}
#message,#message table{position:fixed;width:400px;}
#message{overflow:hidden;width:400px;}
#message table{clear:both;margin-top:15px;}
#message h2{position:fixed;clear:both;height:30px; width:400px; clear:both;margin:0; padding:0; float:left; line-height:30px; font-size:14px; color:#ffffff; background:#a74312; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h2 img{float:right; margin:3px 3px 0 0}
#message h3{clear:both;height:auto; width:400px; margin:0; padding:0; float:left; line-height:30px; font-size:12px; color:#a74312; background:#ffffff; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h3 input[type=text]{margin:0 0 0 30px;  width:120px}
#message h3 img{float:right; margin:4px 47px 0 0 }
#message p{margin:0;padding:0;position:fixed;margin-top:40px;}
#message p span{position:fixed;float:left; margin:0; width:400px; height:46px; background:#f4e7bd; border-top:2px solid #e6cb7b }
#button{height:26px; width:90px; float:right; margin:10px}
td.right_border{border-right:1px solid #fff;}
.style111 {
	font-size: 13px;
	font-weight: bold;
}
.atip,.atip table{
font-family: "Trebuchet MS";
color: #a74312;font-size: 12px;
}
.atip table tr td h3,.atip table tr td.left_boldV{font-weight:bold;}
.style1 {font-size: 14px}
</style>
<link type="text/css" rel="stylesheet" href="useractivation-22 Nov/css/dhtmlgoodies_calendar.css?random=20051112" media="screen"/>
<style type="text/css">
<!--
.style3 {font-size: 11px}
-->
</style>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top" class="top_padding2">
	<table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="bottom" height="20" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    
                    
                    <tr>
                      <td valign="bottom" height="2" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >Fund Transfer Request</td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="1" align="left" style="padding-left:61px" class="logintxt" ><%=status%></td>
                    </tr>
					<tr>
                      <td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
					<tr>
                      <td valign="bottom" height="0" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="1" align="left" style="padding-left:61px" class="logintxt" ><%=message%></td>
                    </tr>
                    <tr>
                      <td valign="bottom" height="1" align="left" style="padding-left:61px" class="logintxt" >&nbsp;</td>
                    </tr>
                    <tr>
                      <td colspan="4" height="10"></td>
                    </tr>
                    <tr>
                      <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="form11">

                          <tr>
                            <td valign="top" align="center">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    
								
									<tr>
									  <td align="center"> 	</td></tr>
								
									<tr>
									  <td align="center"></td></tr>
									  <%if(size>0){%>
                                    <tr>
                                      <td valign="top" style="border-left:1px solid #a74312;">
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="form11">
                                        <tbody style="background:#a74312;">
                                         
                                          <tr class="hd" align="center" style="background:#a74312;">
                                            <td width="3%" height="25" class="right_border">SL No.</td>
                                            <td width="4%" class="right_border">Trans Id</td>
                                            <td width="5%" class="right_border">Agent Id</td>
                                            <td width="8%" align="center" style="padding-left:0px;" class="right_border">Previous Amt</td>
                                            <td width="10%" class="right_border">Transfer Amt</td>
                                            
                                            <td width="8%" align="left" class="right_border" style="padding-left:8px;">Bal Amt</td>
                                            <td width="9%" align="left" style="padding-left:8px;" class="right_border">Date of Transfer</td>
                                            <td width="7%" align="left" style="padding-left:8px;" class="right_border">From Service</td>
											<td width="9%" align="left" style="padding-left:8px;" class="right_border">To Service</td>
											
											<td width="8%" align="left" style="padding-left:8px;" class="right_border">Updated Date</td>
                                            <td width="12%" align="center" style="padding-left:0px;" class="right_border">Status</td>
											<td width="9%" align="center" style="padding-left:0px;">Action</td>
                                          </tr>
                                          <%
										  String colour="";
										  for(int i=0;i<fundData.size();i++){
										   HashMap hm=(HashMap)fundData.get(i);	
										  service1=(String)hm.get("Transfer_from_service");
								 
										  if(i%2==0)
											{
											colour="#FFFFFF";
										}
										else
										{
											colour="#EFF3F5";
										}	
										  %>
										  <form action="FundTransfer.action" name="deposit<%=i%>" id="deposit<%=i%>" method="post" onsubmit="return validateform('<%=i%>','<%=hm.get("Transfer_amount")%>','<%=hm.get("Transfer_from_service")%>');">
										   <input type="hidden" name="param" value="updatefundtansfer" />
							 			   <input type="hidden" name="service1<%=i%>" value="<%=service1%>" />
										   <input type="hidden" name="ip_address<%=i%>" value="<%=ip_address%>" />
                                          <tr bgcolor="<%=colour%>" align="center" class="value_reg">
                                            <td height="25"><%=(i+1)%></td>
                                            <td><%=hm.get("TranSection_No")%></td>
											<input type="hidden" name="transaction_no<%=i%>" value="<%=hm.get("TranSection_No")%>" />
                                            <td><%=hm.get("agent_id")%></td>
                                            <td align="center" valign="middle" style="padding-left:0px;"><span class="style3">
											<% String service=(String)hm.get("Transfer_from_service");
								     if(service.equalsIgnoreCase("air")){
									   request.setAttribute("service",service);
									 %><input type="text" name="prevamount<%=i%>" size="10"  onkeypress="return digitonly(this,event)" id="<%=i%>"/> 
								  <% } else {
								 
								   %>
								  
								   <input type="hidden" name="prevamount<%=i%>" size="10"   value="<%=hm.get("provious_Amount")%>" />
								 
								  <%=hm.get("provious_Amount")%>
								  <%}%>
                                            </span></td>
                                          
                                      
                                            <td><%=hm.get("Transfer_amount")%></td>
                                        <input type="hidden" name="transferamount<%=i%>" value="<%=hm.get("Transfer_amount")%>"  />
                                             <td>
											 <%               // service=(String)hm.get("Transfer_from_service");
								     if(service.equalsIgnoreCase("air")){
									 
									 %>
				   
				   <% } else { 
								  
								  %><input type="hidden" name="balamt<%=i%>" size="10" value="<%=hm.get("balance_amount")%>"/>
											 
											<%=hm.get("balance_amount")%> </td>
                                        <%} %>
                                            <td align="left" style="padding-left:8px;"><%=hm.get("Date_of_Transfer")%></td>
											<input type="hidden" name="date_of_transfer<%=i%>" value="<%=hm.get("Date_of_Transfer")%>"  />
                                            <td align="left" style="padding-left:8px;"><%=hm.get("Transfer_from_service")%></td>
                                            <td align="left" style="padding-left:8px;"><%=hm.get("Transfer_to_service")%></td>
										
                                            <td align="left" style="padding-left:8px;"><%=hm.get("Updated_Date")%></td>
											<input type="hidden" name="updated_date<%=i%>" value="<%=hm.get("Updated_Date")%>"  />
                                            <td align="center" style="padding-left:0px;"><span class="style3" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px; font-weight:bold;">
                                              <select name="statusid<%=i%>" id="statusid<%=i%>" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:11px; font-weight:bold;" > 
											  <option value="Select">Select</option>
											  <option value="success">Success</option>
                                                <option value="failure">Failure</option>
                                              </select>
                                            </span></td>
                                            <td align="center" style="padding-left:0px;"><span class="style3">
                                              <input name="submit<%=i%>" type="submit" class="buttonbg" value="Save" />
											  <input type="hidden" name="agentid<%=i%>" value="<%=hm.get("agent_id")%>" />
						       				 <input type="hidden" name="date<%=i%>" value="<%=hm.get("Date_of_Booking")%>" />
						     
						       				 <input type="hidden" name="status" value="<%=i%>" />
						       				 <input type="hidden" name="amount<%=i%>" value="<%=hm.get("Transfer_amount")%>" />
                                            </span></td>
											</tr>
												</form>
											<%}%>
										
                                        </tbody>
                                      </table></td>
                                    </tr>
									<%}%>
                                    <tr> </tr>
                                  </tbody>
                                </table>
                              </div></td>
                          </tr>
                          <tr>
                            <td valign="top" align="center">&nbsp;</td>
                          </tr>
                          <tr>
                            <td valign="top" align="center"></td>
                          </tr>
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
      </table>
	  
	 </td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
<script type="text/javascript">
function validateform(i,transferamt,service){
var status=document.getElementById("statusid"+i).value;


if(status=="pending"){
alert("Please select status");
return false;
}
if(service=="Air" && status=="success"){

var tamt=parseInt(transferamt);
var prev=document.getElementById(i).value;
var prevamt=parseInt(prev);
if(prev==""){
alert("please enter air balance");
document.getElementById(i).focus();
return false;
}
if(prevamt<tamt){
alert("Air balance amount must be greater than transfer amount");
return false;
}
}

}

function digitonly(input,evt)

{


var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) {
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) {
return true;
}

return false;
}
</script>
