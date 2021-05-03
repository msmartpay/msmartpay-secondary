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
                            <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Cutoff for All DS</td>
                          </tr>
                          <tr>
                            <td valign="top"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl">
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All DS</strong></td>
                                  <td width="15%"  align="center">:</td>
                                  <td width="37%" align="left"><input name="cutoffAmount"  id="cutoffAmount" type="text" class="style2" style="width:70px"   onkeypress="return digitonly(this,event)" maxlength="7" /></td>
                                </tr>
							<input type="hidden" value="<%=ipAddress%>" name="ipAddress" >							   
					       <input  type="hidden" value="<%=cutOffBy %>" name="cutOffBy"/>
					       <input  type="hidden" value="<%=EnterUserId %>" name="EnterUserId"/>
                                <tr>
                                  <td align="left" colspan="4"><table width="86%"  cellspacing="0" cellpadding="0" align="center" >
                                      
                                    </table></td>
                                </tr>
                                <tr>
                                  <td align="center" colspan="4"><div  id="div1" style="display:none;">
                                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount Limit for All AG</strong></td>
                                          <td width="15%"  align="center">:</td>
                                          <td width="37%" align="left"><input name="SecondcutoffAmount" type="text" class="style2"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>
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
                            <td colspan="" valign="middle" height="50" align="left" class="heading_mgs" ><strong>Set Cutoff Amount for DS ID </strong>(<%=EnterUserId%>)</td>
                          </tr>
                          <tr>
                            <td align="center" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" class="tbls">
                                <tr align="center" bgcolor="#930">
                                  <td width="10%" height="25" style="border-right:1px solid #930;color:#FFF">DS ID</td>
                                  <td width="20%" style="border-right:1px solid #930;color:#FFF">DS Name</td>
                                  <td width="8%" style="border-right:1px solid #930;color:#FFF">Total Cash</td>
                                  <td width="8%" style="border-right:1px solid #930;color:#FFF">Used Cash</td>
                                  <td width="11%" style="border-right:1px solid #930;color:#FFF">Cutoff Amount</td>
                                  <td width="11%" style="border-right:1px solid #930;color:#FFF">Available Balance</td>
                                  <td width="12%" style="border-right:1px solid #930;color:#FFF">Total Balance</td>                                								                                    
                                 
                                </tr>
                                <tr align="center" bgcolor="#ffffff">
                                  <td height="25" style="border-right:1px solid #930;" style="text-transform:uppercase;"><%=EnterUserId%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("UserName")%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("stringTot")%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("useString")%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("cutString")%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("AvilBalance")%></td>
                                  <td class="mdcolor" style="border-right:1px solid #930;"><%=getDistributorData.get("TotalBalance")%></td>
                                 	                                
                                  </tr>
								  <input type="hidden" value="<%=getDistributorData.get("OnlyUserId")%>" name="userId" >
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
  <td width="100%" valign="top" align="center" height="220">
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