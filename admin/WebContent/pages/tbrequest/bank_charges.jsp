<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%
String message="";
 message=(String)request.getAttribute("myDepositMessage");
 if(message==null)
 {
	 message="";
 }
if(message=="")
{
	message="";
}

%>
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
                <td valign="top" align="center" class="rounded-corners" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td   valign="bottom" height="40" align="left" class="heading_mgs" >Bank Charges Setup</td>
                      <td valign="bottom" height="40" align="left" class="dyn_mgs"><%=message %></td>
                    </tr>
                    
                    <tr>
                      <td><table width="90%"  cellspacing="0" cellpadding="0" align="center" class="form11">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <form name="myForm"  method="post">
                            <table width="80%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            
                                <tr>
                                  <td width="35%"  align="right" style="padding-left:15px" height="30">Select Mode of payment <font size="1" color="red" >&nbsp;*</font></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="53%" align="left"><select name="modeOfPayment"  id="riskID" onchange="toggleSubmit1(this.form)">
                                      <option value="select">Select</option>
                                      <option value="1">Cash on Desk</option>
                                      <option value="2">Cash in Bank</option>
                                      <option value="3">Cheque/DD</option>
                                      <option value="4">Online E-Transfer</option>
                                      <option value="5">NEFT/RTGS</option>                                      
                                    </select></td>
                                </tr>
                                                           
								
								<tr>
                                  <td colspan="3"><div id="c2" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                                        <tr>
                                          <td width="35%"  align="right" style="padding-left:15px" height="30">Select Bank<font size="1" color="red" >&nbsp;*</font></td>
                                          <td width="12%"  align="center">:</td>
                                          <td valign="middle" width="53%"><select  class="style2" id="riskID" NAME="bankName">
                                             <option value="State Bank of India">State Bank of India </option>
                                              <option value="Punjab National Bank">Punjab National Bank</option>
                                              <option value="HDFC Bank">HDFC Bank</option>
                                              <option value="ICICI Bank">ICICI Bank</option>
                                            </select></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>				
							
								
							
								 <tr>
                                  <td colspan="3"><div id="c6" style="display:none"></div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c7" style="display:none;"></div></td>
                                </tr>
								
                                <tr>
                                  <td></td>
                                  <td></td>
                                  <td height="50" align="center" valign="middle" style="padding-right:120px;">
                                    <input name="Submitaa" type="button" value="Submit" onclick="submitForm()" class="cls_btn" />
                                    </td>
                                </tr>
								 <tr>
                                  <td></td>
                                  <td></td>
                                 
                                </tr>
                              </table>
                              </form>
                              </td>
                          </tr>
						  
						  
                        </table></td>
                    </tr>
					
					<tr>
                      <td colspan="4"><table width="90%"  cellspacing="0" cellpadding="0" align="center"  class="form11" >
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">                
							
                              					  
						    </td>
                          </tr>
                        </table></td>
                    </tr>
					
                    <tr>
                      <td height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
// code for risk dropdown 


function toggleSubmit1(obj){
var modeOfPayment=obj.modeOfPayment.value;
if(modeOfPayment!=1)
{
document.getElementById("c2").style.display="block";
}
if(modeOfPayment==1)
{
document.getElementById("c2").style.display="none";
}

}



function submitForm(mode)
{
	var mode=document.myForm.modeOfPayment.value;
	if(mode=="select")
	{
		alert("Please select Mode of payment");
		document.myForm.modeOfPayment.focus();
		return false;
	}
	else{
		document.myForm.action="bankChargesList.action";
		document.myForm.submit();
	}
}

</script>
