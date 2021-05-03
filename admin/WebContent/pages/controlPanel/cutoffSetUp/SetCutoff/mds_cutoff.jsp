<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
<%
HashMap getMdData=(HashMap)request.getAttribute("getCutOffData");
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
              <td valign="top" align="center" class="rounded-corners box_heights" >
              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                 <%if(cutOffBy.equalsIgnoreCase("ByAll")){%>
				  <tr>
				  				  <form name="ALLMD"  method="post" action="cutoffAmount.action?param=UpdateCutOffAllMD">

                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  width="100%" valign="middle" height="50" align="left" class="heading_mgs">Cutoff for MDS of All Client</td>
                          </tr>
                          <tr>
						  <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
							<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                            <td valign="top" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl" >
                            <tr><td height="50px" colspan="100%"></td></tr>
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All MDS</strong></td>
                                 
                                  <td width="57%" align="left"><input name="cutoffAmount" type="text" id="cutoffAmountID" class="style2"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                </tr>
							<input type="hidden" value="<%=ipAddress%>" name="ipAddress" >
                                       <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"></td>
                                  
                                  <td width="37%" align="left"><input type="button" class="cls_btn" value="Update" onclick="checkClientId()"/>
                                  </td>
                                </tr>
                                <tr>
                                  <td align="center" colspan="4"><div  id="div1" style="display:none;">
                                      <table width="100%"  cellspacing="0" cellpadding="0" align="center" class="border1">
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount Limit for All DS</strong></td>
                                          
                                          <td width="57%" align="left"><input name="SecondcutoffAmount" id="SecondcutoffAmountMD" type="text" class="style2" style="width:70px" onkeypress="return digitonly(this,event)" maxlength="7" /></td>
                                        </tr>
                                         <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"></td>
                                  
                                  <td width="37%" align="left"><input type="button" class="cls_btn" value="Update" onclick="checkClientId()"/>
                                  </td>
                                </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                
                                <tr>
                                  <td align="center" colspan="4"><div  id="div1" style="display:none;">
                                      <table width="100%"  cellspacing="0" cellpadding="0" align="center" class="border1">
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left"></td>
                                          <td   align="center"></td>
                                          <td width="57%" align="left"><input type="button" class="cls_btn" value="Update"   onclick="checkAll()"/></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                
                                <tr><td height="50px" colspan="100%"></td></tr>
                              </table></td>
                          </tr>
                          
                          
                        </table>
                     </td>
					 </form>
					 
                  </tr>
				  <%}%>
                 <%if(cutOffBy.equalsIgnoreCase("ByClientId")){%>
				
				  <tr>
				  	  <form name="ClientMD"  method="post" action="cutoffAmount.action?param=UpdateCutOffByClientId">

                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                          <tr>
                            <td  width="100%" valign="middle" height="50" align="left"><strong>Cutoff for MDS of Client ID (<%=EnterUserId%>)</strong> </td>
                          </tr>
                          <tr>
                            <td valign="top"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border"  class="mydata_tabl" >
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All MDS</strong></td>
                                  
                                  <td width="37%" align="left">
                                  <input name="cutoffAmount" id="cutoffAmountClient" type="text" class="style2" onkeypress="return digitonly(this,event)" maxlength="5"/></td>
                                </tr>
                                
                                 <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"></td>
                                  
                                  <td width="37%" align="left"><input type="button" class="cls_btn" value="Update" onclick="checkClientId()"/>
                                  </td>
                                </tr>
                                
                                <tr>
                                  <td align="center" colspan="4"><div  id="div2" style="display:none;">
                                      <table width="100%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left" ><strong>Set Cutoff Amount Limit for All DS</strong></td>
                                          
                                          <td width="37%" align="left"><input name="SecondcutoffAmount"  id="cutoffAmountClient"type="text" class="style2"  onkeypress="return digitonly(this,event)" maxlength="7"/></td>                                <input type="hidden"  value="<%=EnterUserId%>" name="userId" />
										   <input type="hidden" value="<%=ipAddress%>" name="ipAddress" />
                                        </tr>
                                        
                                        <tr>
                                          <td width="15%"></td>
                                          <td width="33%" height="35"  align="left" ></td>
                                          
                                          <td width="37%" align="left"><input type="button" class="cls_btn" value="Update" onclick="checkClientId()"/></td>                               
										   
                                        </tr>
                                        
                                      </table>
                                    </div></td>
                                </tr>
                              </table></td>
                          </tr>
                          <tr>
                            <td width="100%" align="center" height="40" valign="middle"></td>
                          </tr>
                        </table>
                    </td>
					  </form>
					 
                  </tr>
                <%}%>
			   
			     <%if(cutOffBy.equalsIgnoreCase("ById")){%>
			      <tr>
				    <form name="MDIDFORM" method="post" action="cutoffAmount.action?param=UpdateCutOffMDByID">
                    <td><div id="mds3" style="display:block">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td colspan="" valign="middle" height="50" align="left" class="heading_mgs" ><strong>Select MDS ID (MDS <%=EnterUserId%>)</strong></td>
                          </tr>
                          <tr>
                            <td align="center" width="100%"><table width="86%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" class="tbls">
                                <tr align="center" bgcolor="#930">
                                  <td height="25" width="8%" style="color:#FFF">Client ID</td>
                                  <td width="9%" style="color:#FFF">MDS ID</td>
                                  <td width="7%" style="color:#FFF">MDS Name</td>
                                  <td width="7%" style="color:#FFF">Total Cash</td>
                                  <td width="8%" style="color:#FFF">Used Cash</td>
                                  <td width="10%" style="color:#FFF">Cutoff Amount</td>
                                  <td width="11%" style="color:#FFF">Available balance</td>
                                  <td width="11%" style="color:#FFF">Total Balance</td>
                                  <td width="11%" style="color:#FFF">Remark - By Admin</td>								  
                                </tr>
                                <tr bgcolor="#ffffff" align="center">
                                  <td height="25"><%=userId%></td>
                                  <td><%=EnterUserId%></td>
                                  <td><%=getMdData.get("UserName")%></td>
                                  <td><%=getMdData.get("stringTot")%></td>
                                  <td><%=getMdData.get("useString")%></td>
                                     <td class="mdcolor"><input name="cutoffAmount" id="cutoffAmountID" type="text"  style="width:122px;" value="<%=getMdData.get("cutString")%>" onkeypress="return digitonly(this,event)" maxlength="7"/></td>
                                  <td><%=getMdData.get("AvilBalance")%></td>
                                  <td><%=getMdData.get("TotalBalance")%></td>
                                   <td class="mdcolor"><input type="text" name="RemarkAdmin" maxlength="100"></td>								   
                                </tr>
								  <input type="hidden" value="<%=getMdData.get("OnlyUserId")%>" name="userId" />
						  <input type="hidden" value="<%=ipAddress%>" name="ipAddress" />
                              </table></td>
                          </tr>
                          <tr>
                            <td width="100%" align="right" height="40" valign="middle" style="padding-right:90px;"><input type="button" onclick="validateById()" class="cls_btn" value="Update"/></td>
                          </tr>
                        </table>
                      </div></td>
					  </form>
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

function showMeClientId()
{


document.getElementById("div2").style.display='none';
for (i = document.getElementsByName('checkBoxCutOffID').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkBoxCutOffID')[i].checked)
 {
document.getElementById("div2").style.display='block';
}
}



}

function checkAll()
{

var main=document.ALLMD;
if(main.cutoffAmount.value=="")
{
alert("Please enter cut off Amount");

main.cutoffAmount.focus();
return false;
}


if( parseFloat(main.cutoffAmount.value)!= parseInt(parseFloat(main.cutoffAmount.value))){


         alert ('Decimal value not allowed');
	     main.cutoffAmount.focus();
		 return false;
    }

if (isNaN(main.cutoffAmount.value))
{ 

alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(main.cutoffAmount.value)<0)
{
alert( "- Please enter your quantity as a positive number.");
return false;

}



for (i = document.getElementsByName('checkBoxCutOff').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkBoxCutOff')[i].checked)
 {

if(main.SecondcutoffAmount.value=="")
{
alert("Please enter cut off Amount");

main.SecondcutoffAmount.focus();
return false;
}


if( parseFloat(main.SecondcutoffAmount.value)!= parseInt(parseFloat(main.SecondcutoffAmount.value))){


         alert ('Decimal value not allowed');
	     main.SecondcutoffAmount.focus();
		 return false;
    }

if (isNaN(main.SecondcutoffAmount.value))
{ 

alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(main.SecondcutoffAmount.value)<0)
{

alert( "- Please enter your quantity as a positive number.");
return false;

}
}
}

document.ALLMD.submit();

}


//------------------------check validation for clint id
function checkClientId()
{

var main=document.ClientMD;
if(main.cutoffAmount.value=="")
{
alert("Please enter cut off Amount");

main.cutoffAmount.focus();
return false;
}


if( parseFloat(main.cutoffAmount.value)!= parseInt(parseFloat(main.cutoffAmount.value))){


         alert ('Decimal value not allowed');
	     main.cutoffAmount.focus();
		 return false;
    }

if (isNaN(main.cutoffAmount.value))
{ 

alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(main.cutoffAmount.value)<0)
{
alert( "- Please enter your quantity as a positive number.");
return false;

}



for (i = document.getElementsByName('checkBoxCutOffID').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkBoxCutOffID')[i].checked)
 {

if(main.SecondcutoffAmount.value=="")
{
alert("Please enter cut off Amount");

main.SecondcutoffAmount.focus();
return false;
}


if( parseFloat(main.SecondcutoffAmount.value)!= parseInt(parseFloat(main.SecondcutoffAmount.value))){


         alert ('Decimal value not allowed');
	     main.SecondcutoffAmount.focus();
		 return false;
    }

if (isNaN(main.SecondcutoffAmount.value))
{ 

alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(main.SecondcutoffAmount.value)<0)
{

alert( "- Please enter your quantity as a positive number.");
return false;

}
}
}

document.ClientMD.submit();


}



//---------------------------check validate b y id-------------------

function validateById()
{


var main=document.MDIDFORM;
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
alert("Plesae insert remark for admin");
main.RemarkAdmin.focus();
return false;

}
if(checkAmountValitationFirst(amount))
{
document.MDIDFORM.submit();
}
}

</script>