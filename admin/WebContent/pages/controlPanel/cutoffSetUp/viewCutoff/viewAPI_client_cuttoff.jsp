<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link href="css/stickytooltip.css" rel="stylesheet" type="text/css" />
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
  <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
      <tr>
        <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                   <%if(cutOffBy.equalsIgnoreCase("ByAll")){%>
				 
				 <form name="APIAll" method="post" action="cutoffAmount.action?param=UpdateCutOffAPIAll">
				  <tr>
                    <td><div id="ds1" style="display:block">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Cutoff for All Client</td>
                          </tr>
                          <tr>
						  <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
						  <tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                            <td valign="top"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" class="mydata_tabl" >
                                <tr>
                                  <td width="15%"></td>
                                  <td width="33%" height="35"  align="left"><strong>Set Cutoff Amount for All Client</strong></td>
                                  <td width="15%"  align="center">:</td>
                                  <td class="mdcolor"><input name="cutoffAmount"  type="text" value="" onkeypress="return digitonly(this,event)" maxlength="7" /></td>
                             <input type="hidden" value="<%=ipAddress%>" name="ipAddress" />
							    </tr>
                              </table></td>
                          </tr>
                          
                        </table>
                      </div></td>
                  </tr>
				   <%System.out.println("cutOffBy----------->>2>>>>-");%>
				  </form>
                <%}%>
				  <%if(cutOffBy.equalsIgnoreCase("ById")){%>
				
				<form name="APIById" method="post" action="cutoffAmount.action?param=UpdateCutOffAPIById">
				  <tr>
                    <td>
                        <table style="margin-top:30px;" width="86%" border="0" cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td colspan="" valign="middle" height="40" align="left" class="heading_mgs"><strong>Set Cutoff Amount for Client ID (Client ID: <%=EnterUserId%>)</strong></td>
                          </tr>
                          <tr>
                            <td align="center" width="100%">
                            <table style="margin-top:20px;" width="100%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" class="tbls">
                                <tr align="center" bgcolor="#930">
                                  <td width="12%" height="25" style="color:#FFF;">Client ID</td>
                                  <td width="14%" style="color:#FFF;">Client Name</td>
                                  <td width="8%" style="color:#FFF;">Total Cash</td>
                                  <td width="8%" style="color:#FFF;">Used Cash</td>
                                  <td width="11%" style="color:#FFF;">Cutoff Amount</td>
                                  <td width="11%" style="color:#FFF;">Available balance</td>
                                  <td width="12%" style="color:#FFF;">Total Balance</td>
                                                                 
                                </tr>
                                <tr align="center" bgcolor="#ffffff">
                                  <td height="25" style="text-transform:uppercase;"><%=EnterUserId%></td>
                                  <td class="mdcolor"><%=getData.get("UserName")%></td>
                                  <td class="mdcolor"><%=getData.get("stringTot")%></td>
                                  <td class="mdcolor"><%=getData.get("useString")%></td>
                                  <td class="mdcolor"><%=getData.get("cutString")%></td>
                                  <td class="mdcolor"><%=getData.get("AvilBalance")%></td>
                                  <td class="mdcolor"><%=getData.get("TotalBalance")%></td>                                 
                                
								    <input type="hidden"  value="<%=getData.get("OnlyUserId")%>" name="userId" />
										   <input type="hidden" value="<%=ipAddress%>" name="ipAddress" />
								   
                                </tr>
                              </table></td>
                          </tr>
                          
                        </table>
                   </td>
                  </tr>
                </form>
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
  <td width="100%" valign="top" align="center" height="230">
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

function checkAllValidation()
{

var bal=document.APIAll.cutoffAmount.value;
alert(bal)
if(bal.value=="")
{
alert("Please enter coutoff Amount");

document.APIAll.cutoffAmount.focus();
return false;
}


if( parseFloat(bal)!= parseInt(parseFloat(bal))){

         alert ('Decimal value not allowed');
	     document.APIAll.cutoffAmount.focus();
		 return false;
    }

if (isNaN(bal))
{ 
alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(bal)<0)
{
alert( "- Please enter your quantity as a positive number.");
return false;

}

if(Number(bal)<1000000)
{
alert( "- Please enter Amount less than 10,00000");
return false;

}

document.APIAll.submit();

}



function ValidateApiId()
{

var bal=document.APIById.cutoffAmount.value;
alert(bal)
if(bal.value=="")
{
alert("Please enter coutoff Amount");

document.APIById.cutoffAmount.focus();
return false;
}


if( parseFloat(bal)!= parseInt(parseFloat(bal))){

alert("bal")
         alert ('Decimal value not allowed');
	     document.APIById.cutoffAmount.focus();
		 return false;
    }

if (isNaN(bal))
{ 
alert("is nan")
alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(bal) < 0)
{
alert( "- Please enter your quantity as a positive number.");
return false;
}
if(Number(bal)>1000000)
{
alert( "- Please enter Amount less than 10,00000");
return false;

}

var adminRemark=document.APIById.RemarkAdmin.value;
adminRemark=adminRemark.replace(/^\s+|\s+$/, '');
if(adminRemark=="")
{
alert ('Please enter admin remark');
	     document.APIById.RemarkAdmin.focus();
		 return false;

}
var RemarkOther=document.APIById.RemarkOther.value;
RemarkOther=RemarkOther.replace(/^\s+|\s+$/, '');
if(RemarkOther=="")
{
alert ('Please enter  remark for client');
	     document.APIById.RemarkAdmin.focus();
		 return false;

}
document.APIById.submit();

}
</script>