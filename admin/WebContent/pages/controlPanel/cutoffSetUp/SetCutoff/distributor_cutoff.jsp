<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%
HashMap getDistributorData=(HashMap)request.getAttribute("getCutOffData");
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
				  <form name="ALLDS"  method="post" action="cutoffAmount.action?param=UpdateCutOffAllDS">
                    <td>
                
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  width="100%" valign="middle" height="50" align="left" class="heading_mgs" >Cutoff for All DS</td>
                          </tr>
                          <tr>
						  <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                            <td valign="top"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl" >
                            <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"></td>
                                 
                                  <td width="37%" align="left"></td>
                                </tr>
                                
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All DS</strong></td>
                                  
                                  <td width="57%" align="left"><input name="cutoffAmount"  id="cutoffAmount" type="text" class="style2"  onkeypress="return digitonly(this,event)" maxlength="7" /></td>
                                </tr>
							<input type="hidden" value="<%=ipAddress%>" name="ipAddress" >							   
					       <input  type="hidden" value="<%=cutOffBy %>" name="cutOffBy"/>
					       <input  type="hidden" value="<%=EnterUserId %>" name="EnterUserId"/>
                                <tr><td height="50px" colspan="100%"></td></tr>
                                <tr>
                                  <td align="center" colspan="4"><div  id="div1" style="display:none;">
                                      <table width="100%"  cellspacing="0" cellpadding="0" align="center" class="border1">
                                      <tr><td height="50px" colspan="100%"></td></tr>
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount Limit for All AG</strong></td>
                                          
                                          <td width="57%" align="left"><input name="SecondcutoffAmount" type="text" class="cls_btn"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                        </tr>
                                        <tr><td height="50px" colspan="100%"></td></tr>
                                         <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"></td>
                                          
                                          <td width="57%" align="left"><input type="button" class="cls_btn" value="Update"  onclick="checkAll()"/></td>
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
				    <%if(getDistributorData!=null){
					
					
					
					%>
			   
			   
			   
			   
			      <tr>
				  <form name="DSIDFORM" method="post" action="cutoffAmount.action?param=UpdateCutOffDSID">
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td colspan="" valign="middle" height="50" align="left" class="heading_mgs" ><strong>Set Cutoff Amount for DS ID (<%=EnterUserId%>)</strong></td>
                          </tr>
                          <tr>
                            <td align="center" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" class="tbls">
                                <tr align="center" bgcolor="#930">
                                  <td width="10%" height="25" style="color:#FFF">DS ID</td>
                                  <td width="10%" style="color:#FFF">DS Name</td>
                                  <td width="8%" style="color:#FFF">Total Cash</td>
                                  <td width="8%" style="color:#FFF">Used Cash</td>
                                  <td width="11%" style="color:#FFF">Cutoff Amount</td>
                                  <td width="11%" style="color:#FFF">Available Balance</td>
                                  <td width="12%" style="color:#FFF">Total Balance</td>
                                  <td width="12%" style="color:#FFF">Remark - By Admin</td>								                                    
                                 
                                </tr>
                                <tr align="center" bgcolor="#ffffff">
                                  <td height="25"><%=EnterUserId%></td>
                                  <td class="mdcolor"><%=getDistributorData.get("UserName")%></td>
                                  <td class="mdcolor"><%=getDistributorData.get("stringTot")%></td>
                                  <td class="mdcolor"><%=getDistributorData.get("useString")%></td>
                                  <td class="mdcolor"><input name="cutoffAmount" id="cutoffAmountID" type="text" style="width:122px;" value="<%=getDistributorData.get("cutString")%>" onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                  <td class="mdcolor"><%=getDistributorData.get("AvilBalance")%></td>
                                  <td class="mdcolor"><%=getDistributorData.get("TotalBalance")%></td>
                                  <td class="mdcolor"><input type="text" name="RemarkAdmin" maxlength="100"></td>	                                
                                  </tr>
								  <input type="hidden" value="<%=getDistributorData.get("OnlyUserId")%>" name="userId" >
						  <input type="hidden" value="<%=ipAddress%>" name="ipAddress" >
                              </table></td>
                          </tr>
                          <tr>
                            <td width="100%" align="right" height="80" valign="middle" style="padding-right:90px;"><input type="button" onclick="checkDsId()" class="cls_btn" value="Update"/></td>
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
  <td width="100%" valign="top" align="center" height="137">
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
var checkmds1=document.ALLDS.SecondcutoffAmount.value;
if(checkmds1=="")
{
alert("Please fill cut off amount for all Agent")
document.ALLDS.SecondcutoffAmount.focus();
return false;
}
if(isNaN(checkmds1))
{
alert("cut off amount must be numerc for all Agent")
document.ALLDS.SecondcutoffAmount.focus();
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
alert( "- Please enter your quantity as a positive number in cut off Amount");
return false;
}

if(Number(amount)>1000000)
{
alert( "- Please enter Amount less than 10,00000");
return false;
}

//document.ALLDS.action="cutoffAmount.action?param=UpdateCutOffAllDS";
document.ALLDS.submit();

}


//-------------------------------------------------------------

function checkDsId()
{

var main=document.DSIDFORM;
var amount=main.cutoffAmount.value;

if(amount=="")
{

alert("please enter cutoffamount");
return false;

}
if(amount<0)
{
alert("Cutoff  Amount must be grater then 0");
return false;
}
if(amount > 1000000)
{
alert("Cutoff Amount must be 10,00000 or less");
return false;
}

if(main.RemarkAdmin.value.replace(/^\s+|\s+$/, '')==""){
alert("Plesae insert remark for admin")
main.RemarkAdmin.focus();
return false;

}

if(checkAmountValitationFirst(amount))
{


document.DSIDFORM.submit();


}

}

</script>