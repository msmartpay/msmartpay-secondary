<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%
HashMap getData=(HashMap)request.getAttribute("getCutOffData");
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
  <td  align="center" width="100%" valign="top" ><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
      <tr>
        <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td valign="top" align="center" class="rounded-corners box_heights" >
              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                
				 <%if(cutOffBy.equalsIgnoreCase("ByAll")){%>
				  <tr>
				  <form name="ALLPortal"  method="post" action="cutoffAmount.action?param=UpdateCutOffPortalAll">
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Cutoff for All Portal Admin</td>
                          </tr>
                          <tr>
                            <td valign="top">
                            <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl">
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All Portal Admin</strong></td>
                                  <td width="15%"  align="center">:</td>
                                  <td width="37%" align="left"><input name="cutoffAmount" id="cutoffAmount" type="text" class="style2"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                </tr>
							<input type="hidden" value="<%=ipAddress%>" name="ipAddress" >
                                <tr>                                  
                                </tr>
                                <tr>
                                  <td align="center" colspan="4"><div  id="div1" style="display:none;">
                                      <table width="100%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount Limit for All MD</strong></td>
                                          <td width="15%"  align="center">:</td>
                                          <td width="37%" align="left"><input name="SecondcutoffAmount" type="text" class="style2"
										  onkeypress="return digitonly(this,event)" maxlength="5" /></td>
                                        </tr>
                                        </table>
										</div>
                                  </td>
                                </tr>
                              </table></td>
                          </tr>
                         
                        </table>
                    </td>
					</form>
                  </tr>
               
			   
			      <%}%>
				  <%if(cutOffBy.equalsIgnoreCase("ById")){%>
				    <%if(getData!=null){
					
					
					
					%>
			   
			   
			   
			   
			      <tr>
				  <form name="PortalIDFORM" method="post" action="cutoffAmount.action?param=UpdateCutOffPortalID">
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td colspan="" valign="middle" height="50" align="left" class="heading_mgs" >Set Cutoff Amount for Portal Admin ID (<%=EnterUserId%>)</td>
                          </tr>
                          <tr>
                            <td align="center" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" class="tbls">
                                <tr align="center" bgcolor="#930">
                                  <td width="8%" height="25" style="color:#FFF;">Portal ID</td>
                                  <td width="8%" style="color:#FFF;">Portal Name</td>
                                  <td width="6%" style="color:#FFF;">Total Cash</td>
                                  <td width="6%" style="color:#FFF;">Used Cash</td>
                                  <td width="10%" style="color:#FFF;">Cutoff Amount</td>
                                  <td width="8%" style="color:#FFF;">Available Balance</td>
                                  <td width="10%" style="color:#FFF;">Total Balance</td>                                 								                                  
                                </tr>
                                <tr align="center" bgcolor="#ffffff">
                                  <td height="25" style="text-transform:uppercase;"><%=EnterUserId%></td>
                                  <td class="mdcolor"><%=getData.get("UserName")%></td>
                                  <td class="mdcolor"><%=getData.get("stringTot")%></td>
                                  <td class="mdcolor"><%=getData.get("useString")%></td>
                                  <td class="mdcolor"><%=getData.get("cutString")%></td>
                                  <td class="mdcolor"><%=getData.get("AvilBalance")%></td>
                                  <td class="mdcolor"><%=getData.get("TotalBalance")%></td>                                 
					            </tr>
								  <input type="hidden" value="<%=getData.get("OnlyUserId")%>" name="userId" >
						  <input type="hidden" value="<%=ipAddress%>" name="ipAddress" >
                              </table></td>
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
  <td width="100%" valign="top" align="center" height="238">
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
// code for risk dropdown 


function showMe()
{

document.getElementById("div1").style.display='none';
for (i = document.getElementsByName('checkBoxCutOff').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkBoxCutOff')[i].checked)
 {
document.getElementById("div1").style.display='block';
}
}

}


//---------------------------------------------------
function checkAll()
{
var amount=document.getElementById("cutoffAmount").value;

for (i = document.getElementsByName('checkBoxCutOff').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkBoxCutOff')[i].checked)
 {
var checkmds1=document.ALLPortal.SecondcutoffAmount.value;
if(checkmds1=="")
{
alert("Please fill cut off amount for all Agent")
document.ALLPortal.SecondcutoffAmount.focus();
return false;
}
if(isNaN(checkmds1))
{
alert("cut off amount must be numerc for all Agent")
document.ALLPortal.SecondcutoffAmount.focus();
return false;
}
}
}
if(amount=="")
{
alert("Please fill cut off amount for All DS")
document.getElementById("cutoffAmount").focus();
return false;
}
if( parseFloat(amount)!= parseInt(parseFloat(amount)))
{

         alert ('Decimal value not allowed');
	     document.getElementById("cutoffAmount").focus();
         return false;

		}

if(isNaN(amount))
{
alert("cut off amount must be numeric for DS")
document.getElementById("cutoffAmount").focus();
return false;
}
if(Number(amount)<0)
{
alert( "- Please enter your quantity as a positive number.");
return false;
}

document.ALLPortal.submit();

}


//-------------------------------------------------------------

function checkDsId()
{

var main=document.PortalIDFORM;
var cutoffamount=main.cutoffAmount.value;



if(main.RemarkAdmin.value.replace(/^\s+|\s+$/, '')==""){
alert("Plesae insert remark for admin")
main.RemarkAdmin.focus();
return false;

}

if(cutoffamount<0)
{
alert("Cutoff  Amount must be grater then 0");
return false;
}
if(cutoffamount > 1000000)
{
alert("Cutoff Amount must be 10,00000 or less");
return false;
}

if(checkAmountValitationFirst(cutoffamount))
{

document.PortalIDFORM.submit();
}



}

</script>