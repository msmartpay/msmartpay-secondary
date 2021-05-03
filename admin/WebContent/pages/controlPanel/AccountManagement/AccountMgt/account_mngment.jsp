<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="java.util.HashMap"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>


<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<%   
HashMap AccountManagentDetail=(HashMap)request.getAttribute("AccountManagentDetail");

String RequestUserId=(String)request.getAttribute("RequestUserId");
String UserType=(String)request.getAttribute("UserType");
String ipAddress=(String)request.getRemoteAddr();
String message=(String)request.getAttribute("message");
if(message==null)
{
message="";
}
System.out.println("message------>>>>>>>"+message);
%>
<SCRIPT type="text/javascript" src="scripts/digitonly.js"></script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Account Management</td>
                    </tr>
                   <tr>
                      <td  width="100%" valign="bottom" align="left" class="dyn_mgs" ><%=message%></td>
                    </tr>
                    <tr>
                      <td colspan="4"  valign="top">
                      <form name="checkUserType" method="post">
                      <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="mydata_tabl" id="border">
                       <tr>
                          <td width="25%"></td>
                            <td align="left" height="30"></td>
                            <td></td>
                          </tr>
                          <tr>
                           <td></td>
                            <td align="left" height="30"><strong>View Requesting User</strong></td>
                            <td><select   name="UserType">
							 <option value="select">Select</option>
                                <option value="Agent">Agent</option>
                                <option value="Distributor">Distributor</option>
                                <option value="Master Distributor">Master Distributor</option>
                                <!-- <option value="API/EGEN Partner">API/EGEN Partner</option> -->
                                <option value="Portal">Portal</option>
                              </select></td>
                          </tr>
                          <tr>
                          <td></td>
                            <td align="left" height="30"><strong>Enter User ID</strong></td>
                            <td><input type="text" name="RequestUserId"  maxlength="12"/></td>
                          </tr>
                          <tr>
                          <td></td>
                            <td></td>
                            <td align="left" height="40"><input type="button" value="Submit" class="cls_btn" onclick="checkAgent()"/><span id="checkRecharge" style="visibility:hidden"><img src="images/loading2.gif" height="30" width="30"/></span></td>
                          </tr>
                           <tr>
                          <td></td>
                            <td align="left" height="30"></td>
                            <td></td>
                          </tr>
                        </table>
                        </form>
                        </td>
						
                    </tr>
					
					<%if (AccountManagentDetail!=null) {%>
					
					
                    <tr>
                      <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" style="margin-top:20px;" id="border">
                          <tr>
                            <td  width="100%" valign="bottom" height="30" align="left" class="heading_mgs"><strong>Account Management of</strong> - <strong style="color:#930;"><%=UserType%></strong>
                            </td>
                          </tr>
                          <tr>
                            <td colspan="5" height="10"></td>
                          </tr>
						  
                          <tr>
                            <td colspan="5" valign="top">
                            <form name="updateAccount" method="post">
                            <table cellspacing="0" cellpadding="0" width="86%" align="center" class="mydata_tabl" id="border" >
                                <tbody>
                                  <tr align="left">
								   
                                    <td colspan="4" height="5" align="center"><input type="hidden" name="ipAddress" value="<%=ipAddress%>"/></td>
                                  </tr>
                                  <tr>
                                    <td width="5%"></td>
                                    <td align="left" height="25" width="40%"><strong>User ID</strong></td>
                                    <td align="left" width="10%"><strong>:</strong></td>
                                    <td align="left" width="45%"><%=RequestUserId%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Corporate Name</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><%=AccountManagentDetail.get("agencyName")%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Authorised Person</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><%=AccountManagentDetail.get("Name")%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Current Status</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><%=AccountManagentDetail.get("Status")%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Current Balance</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><%=AccountManagentDetail.get("AvilableBalance")%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Registered Mobile No</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><%=AccountManagentDetail.get("mobileNo")%></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Action Type</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><select style="width:195px;" name="actionType">
                                        <option value="select">Select Action Type</option>
                                        <option value="credit">Credit Note</option>
                                        <option value="debit">Debit Note</option>
                                      </select>
                                    </td>
                                  </tr>								
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Amount</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><input type="text" style="width:190px;"  name="amount" autocomplete="off"  onkeypress="return digitonly(this,event)" maxlength="10"/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Internal Remark</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left"><input type="text" style="width:190px;" name="internalRemark" maxlength="100" /></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>External Remark</strong></td>
                                    <td align="left"><strong>:</strong></td>
                                    <td align="left" valign="middle"><input type="text" style="width:190px;"  name="externalRemark" maxlength="100"/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
									
                                    <td align="left" height="55" id="submitbutton" style="display:block">
                                    <input type="button" class="cls_btn" value="Submit"  onclick="update('<%=UserType%>','<%=RequestUserId%>','<%=AccountManagentDetail.get("userId")%>')"/></td>
									<td align="left" style="visibility:hidden" id="imageCheck" ><img src="images/loading2.gif" height="30" width="30"/></td>
                                  </tr>
                                </tbody>
                              </table>
                              </form>
                              </td>
                          </tr>
						  
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                        </table></td>
                    </tr>
					<%}%>
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
    <td width="100%" valign="top" align="center" height="158">
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript" type="text/javascript">
function checkAgent()
{
 var UserType=document.checkUserType.UserType.value;
 if(UserType=="select")
 {
 alert("Please choose correct userType");
 document.checkUserType.UserType.focus();
 return false;
 
 }
 

 var RequestUserId=document.checkUserType.RequestUserId.value;
 if(RequestUserId=="")
 {
 alert("Please enter userId");
 document.checkUserType.RequestUserId.focus();
 return false;
 }
 document.getElementById("checkRecharge").style.visibility="visible";
 document.checkUserType.action="AccountAdjustment.action?param=AccountAdjustmentDetail";
 document.checkUserType.submit();
}

function update(type,id,userOnlyId)
{


var main=document.updateAccount;
var amount=main.amount.value;
if(main.actionType.value=="select")
{
alert("Please choose any action")
main.actionType.focus();
return false;

}

if(main.internalRemark.value.replace(/^\s+|\s+$/, '')=="")
{
alert("Please insert Internal Remark")
main.internalRemark.focus();
return false;

}
if(main.externalRemark.value.replace(/^\s+|\s+$/, '')=="")
{
alert("Please insert External Remark")
main.externalRemark.focus();
return false;

}
if(checkAmountValitationFirst(amount))
{
document.getElementById('submitbutton').style.display="none";
document.getElementById("imageCheck").style.visibility="visible";
document.updateAccount.action="AccountAdjustment.action?param=updateAccount&UserType="+type+"&RequestUserId="+id+"&userOnlyId="+userOnlyId;
document.updateAccount.submit();
}




}

</script>