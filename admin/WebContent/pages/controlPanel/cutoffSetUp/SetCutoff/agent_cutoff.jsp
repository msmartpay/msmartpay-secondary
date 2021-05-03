<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<link rel="icon" href="images/t.png" />


<%
HashMap getAgentData=(HashMap)request.getAttribute("getCutOffData");
String cutOffBy=(String)request.getAttribute("cutOffBy");
if(cutOffBy==null)
{
cutOffBy="";
}
String EnterUserId=(String)request.getAttribute("EnterUserId");
if(EnterUserId==null)
{
EnterUserId="";
}

String ipAddress=(String)request.getRemoteAddr();
String message=(String)request.getAttribute("message");
if(message==null)message="";
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
        <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                <%if(!cutOffBy.equalsIgnoreCase("ById")){%>
				
				  <tr>
				  <form name="AllAgent" method="post">
                    <td valign="top">
					<input type="hidden" value="<%=ipAddress%>" name="ipAddress" />
					<input  type="hidden" value="<%=cutOffBy %>" name="cutOffBy"/>
					<input  type="hidden" value="<%=EnterUserId %>" name="EnterUserId"/>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" ><strong>Cutoff for All AG</strong></td>
                          </tr>
                          <tr>
						  <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                            <td valign="top">
                            <table width="86%"   cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl" >
                            <tr><td height="50px" colspan="100%"></td></tr>
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left" class="dyn_mgs"><%=message %></td>
                                  
                                  <td width="37%" align="left"><input name="cutoffAmount" type="text"  id="cutoffamountAll" class="style2" onkeypress="return digitonly(this,event)" maxlength="7"   /></td>
                                  
                                </tr>
                                
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"></td>
                                  
                                  <td width="37%" align="left"><input type="button" class="cls_btn" value="Update"  onclick="checkAll()"/></td>
                                  
                                </tr>
                                <tr><td height="50px" colspan="100%"></td></tr>
                              </table></td>
                              
                          </tr>
                         
                        </table>
                     </td>
					 </form>
                  </tr>
                <%}%>
				  <%if(cutOffBy.equalsIgnoreCase("ById")){%>
				    <%if(getAgentData!=null){					
					%>
				 <tr>
				  <form name="AgentID" method="post">
                    <td valign="top">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td colspan="" valign="middle" height="50" align="left" class="heading_mgs"><strong>Set Cutoff Amount for AG ID (<%=EnterUserId%>)</strong></td>
                          </tr>
                          <tr>
                            <td align="center" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" class="tbls"  bgcolor="#a74312">
                        
                        <tr align="center" bgcolor="#930">
                          <td width="14%" height="25" style="color:#FFF;">AG ID</td>
                          <td width="12%" style="color:#FFF;">AG Name</td>
                          <td width="10%" style="color:#FFF;">Total Cash</td>
                          <td width="10%" style="color:#FFF;">Used Cash</td>
                          <td width="13%" style="color:#FFF;">Cutoff Amount</td>
                          <td width="13%" style="color:#FFF;">Available Balance</td>
                          <td width="14%" style="color:#FFF;">Total Balance</td>
                          <td width="14%" style="color:#FFF;">Remark - By Admin</td>
                        </tr>
                        
                        
                         <tr align="center" bgcolor="#ffffff">
                          <td height="25"><%=EnterUserId%></td>
                          <td><%=getAgentData.get("UserName")%></td>
                          <td><%=getAgentData.get("stringTot")%></td>
                          <td><%=getAgentData.get("useString")%></td>
                          <td><input type="text" style="width:122px;" name="cutoffAmount"  id="cutoffAmountAGID"  value="<%=getAgentData.get("cutString")%>"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                          <td><%=getAgentData.get("AvilBalance")%></td>
                          <td><%=getAgentData.get("TotalBalance")%></td>
                          <td><input type="text" name="RemarkAdmin"></td>                        
                        </tr>
                                    
                      </table></td>
                          </tr>
						  <input type="hidden" value="<%=getAgentData.get("OnlyUserId")%>" name="userId" >
						  <input type="hidden" value="<%=ipAddress%>" name="ipAddress" >
                          <tr>
                            <td width="100%" align="right" height="80" valign="middle" style="padding-right:90px;"><input type="button" onclick="checkAgID()" class="cls_btn" value="Update"/></td>
                          </tr>
                        </table>
                     </td>
					 </form>
                  </tr>
				  <%}%>
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
  <td width="100%" valign="top" height="137" align="center">
</td>
</tr>
<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %>
</td>
</tr>
</table>
</body>
</html>
<script type="text/javascript">

function checkAll()
{


var amount=document.getElementById("cutoffamountAll").value;

if(amount=="")
{

alert("please enter cutoffamount")
document.getElementById("cutoffamountAll").focus();
return false;

}
if(amount<0)
{
alert("Cutoff  Amount must be grater then 0");
document.getElementById("cutoffamountAll").focus();
return false;
}
if(amount > 1000000)
{
alert("Cutoff Amount must be 10,0000 or less");
document.getElementById("cutoffamountAll").focus();
return false;
}

if( parseFloat(amount)!= parseInt(parseFloat(amount)))
{

         alert ('Decimal value not allowed');
	     document.getElementById("cutoffamountAll").focus();
         return false;

		}

if (isNaN(amount))
{ 
alert( "- Please enter your quantity as a number.");
return false;
 } 	
else
{

document.AllAgent.action="cutoffAmount.action?param=UpdateCutOffAllAG";
document.AllAgent.submit();
return true;
}		


}

function checkAgID()
{

var amount=document.getElementById("cutoffAmountAGID").value;

if(amount=="")
{

alert("please enter cutoffamount")
document.getElementById("cutoffAmountAGID").focus();
return false;

}
if(amount<0)
{
alert("Cutoff  Amount must be grater then 0");
document.getElementById("cutoffAmountAGID").focus();
return false;
}
if(amount > 1000000)
{
alert("Cutoff Amount must be 1000000 or less");
document.getElementById("cutoffAmountAGID").focus();
return false;
}

if( parseFloat(amount)!= parseInt(parseFloat(amount)))
{

         alert ('Decimal value not allowed');
	     document.getElementById("cutoffAmountAGID").focus();
         return false;

		}

if (isNaN(amount))
{ 
alert( "- Please enter your quantity as a number.");
return false;
 } 	
else
{

document.AgentID.action="cutoffAmount.action?param=UpdateCutOffAGID";
document.AgentID.submit();
return true;
}		


}

</script>