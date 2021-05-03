<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
</head>
<%
ArrayList list=new ArrayList();
HashMap map=new HashMap();
HashMap nextMap=new HashMap();
list=(ArrayList)session.getAttribute("chargeslist");
String mode=(String)request.getAttribute("mode");
if(mode=="" ||mode==null)
{
	mode="";
}
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>
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
                <td valign="top" align="center" class="rounded-corners" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs">Bank Charges Setup</td>
                    </tr>
                    <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
					<tr><td  colspan="10" align="center" >&nbsp;</td></tr>
                    <tr>
                      <td><table width="90%"  cellspacing="0" cellpadding="0" align="center" >
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <form name="myForm" method="post">
                            <table width="80%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">                            
                                <tr>
                                  <td width="35%"  align="right" style="padding-left:15px" height="30">Select Mode of payment <font size="1" color="red" >&nbsp;*</font></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="53%" align="left"><select name="modeOfPayment" style="width:220px" id="riskID" onchange="toggleSubmit1(this.form)">
                                      <option value="select">Select</option>
                                      <option value="1">Cash on Desk</option>
                                      <option value="2">Cash in Bank</option>
                                      <option value="3">Cheque/DD</option>
                                      <option value="4">Online E-Transfer</option>
                                      <option value="5">NEFT/RTGS</option>                                     
                                    </select></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c0" style="display:none"></div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c1" style="display:none;"></div></td>
                                </tr>
								
								<tr>
                                  <td colspan="3"><div id="c2" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                                        <tr>
                                          <td width="35%"  align="right"  height="30">Select Bank<font size="1" color="red" >&nbsp;*</font></td>
                                          <td width="12%"  align="center">:</td>
                                          <td valign="middle" width="53%"><select style="width:220px;" class="style2" id="riskID" NAME="bankName">
                                              
                                              <option value="State Bank of India">State Bank of India </option>
                                              <option value="Punjab National Bank">Punjab National Bank</option>
                                              <option value="HDFC Bank">HDFC Bank</option>
                                              <option value="ICICI Bank">ICICI Bank</option>
                                            </select></td>
                                        </tr>
                                      </table>
                                    </div></td>                               </tr>	
							
								
                                <tr>
                                  <td></td>
                                  <td></td>
                                  <td height="50" align="center" valign="middle" >
                                    <input name="Submitaa" type="button" value="Submit" onclick="submitForm2()" class="cls_btn" />
                                    </td>
                                </tr>
								 <tr>
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
                           
							<div id="div1">
							 <%
                            if(mode.equalsIgnoreCase("Cash on Desk")&& list.size()>0)
                            	{                            	
                            	%>
							<form  name="cashOnDesk" method="post">
                                <table width="80%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" id="border">
                                  <tr>
                                    <td align="center" height="30" class="headtxt" colspan="4">Cash on Desk Charges</td>
                                  </tr>
                                  <tr bgcolor="#c2943c" align="center" class="hd">
                                    <td width="10%">Select</td>
                                    <td width="30%" height="25">Limit</td>                                    
                                    <td width="30%">Charges</td>
                                    <td width="30%">Charges Type</td>
                                  </tr>
                                  <%
                                  for(int i=0;i<list.size();i++){
                      				  
                        				 map=(HashMap)list.get(i);                   				
                                 	
                                  %>
                                  <tr  align="center">
                                    <td><input type="checkbox" name="checkcharge" id="check<%=i %>" value="<%=i%>"/></td>
                                    <td height="25"><input type="text" style="width:180px;" name="limit" id="limit<%=i %>" value="<%=map.get("limit")%>" onkeypress="return digitonly(this,event)" /></td>                                    
                                    <td><input type="text" style="width:80px;" name="charge" id="charge<%=i %>" value="<%=map.get("charge") %>" onkeypress="return digitonly(this,event)"/></td>
                                    <td><select style="width:85px;" name="opt">
                                        <option><%=map.get("chargeType") %></option>                                       
                                      </select>
                                    </td>
                                    <td><input type="hidden" name="sno" value="<%=map.get("sno") %>" /></td>
                                  </tr>	
                                  	<%} %>				
                                  <tr >
                                    <td colspan="5" height="5"></td>
                                  </tr>
                                </table>
                              <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
<td colspan="5" height="40" align="center" valign="bottom"><input type="button" style="width:100px; height:28px;" value="Update" onclick="checkForm2(this.form)"/></td>  </tr>
</table>

                               </form>
                              </div>                           
                              
                              <div id="div2">
                              <%} else if(!mode.equalsIgnoreCase("Cash on Desk")&& list.size()>0)
                              {                	                           		    
                                 	
                              
                              %>
                              <form name="cashOnBank" method="post">
                                <table width="80%"  cellspacing="0" cellpadding="0" align="center" bgcolor="#a74312" id="border">
                                  <tr>
                                    <td align="center" height="30" class="headtxt" colspan="6">Cash In Bank Charges</td>
                                  </tr>
                                  <tr bgcolor="#c2943c" align="center" class="hd">
                                    <td width="8%">Select</td>
                                    <td width="15%" height="25">Limit</td>                                    
                                    <td width="15%">Charges</td>
                                    <td width="13%">Charges Type</td>
                                    <td width="24%">Branch Name</td>
                                    <td width="24%"> Account No</td>
                                  </tr>
                                  <%
                                  int i=0;
                                  for(i=0;i<list.size();i++){
                      				  
                         		     map=(HashMap)list.get(i);  
                                  %>
                                  <tr  align="center">
                                    <td><input type="checkbox" name="checkcharge" id="check<%=i %>" value="<%=i %>"/></td>
                                    <td height="25"><input type="text" style="width:80px;" name="limit" id="limit<%=i %>" value="<%=map.get("limit") %>" onkeypress="return digitonly(this,event)"/></td>                                  
                                    <td><input type="text" style="width:80px;" name="charge" id="charge<%=i %>" value="<%=map.get("charge") %>" onkeypress="return digitonly(this,event)"/></td>
                                    <td><select style="width:85px;" name="opt">
                                        <option><%=map.get("chargeType") %></option>                                        
                                      </select>
                                    </td>
                                    <td><%=map.get("BranchName") %></td>
                                    <td><%=map.get("AccountNo") %></td>
                                    <td><input type="hidden" name="sno" value="<%=map.get("sno") %>" /></td>
                                  </tr>	
                                  <%} %>							
							
                                  <tr >
                                    <td colspan="6" height="5"></td>
                                  </tr>
                                </table>
<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
<td colspan="5" height="40" align="center" valign="bottom"><input type="button" style="width:100px; height:28px;" value="Update" onclick="checkForm(this.form)" /></td>  </tr>
</table> 
</form>       
<%} %>                        
                              </div>							  
							 							  
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

function openstatement()
{
var a = document.getElementById("riskID").value
if(a==0)
{
alert("Please Select any Mode of Payment");
}
for(i=1;i<=7;i++)
{
b = "div"+i
document.getElementById(b).style.display='none';
}
document.getElementById("div"+a).style.display='block';
}

function checkForm(frm)
{
	if(checkValue(frm))
	{
		
	document.cashOnBank.action="updateCharge.action?mode=CashInBank";
	document.cashOnBank.submit();	
	}
}
function checkForm2(frm)
{
	if(checkValue(frm))
{
	
	document.cashOnDesk.action="updateCharge.action?mode=CashOnDesk";
	document.cashOnDesk.submit();
}
}

function checkValue(frm)
{
	var pre;
	var val;
	var nex;
	var i=0;
	var name=frm.name;		
	var len=document.getElementsByName("checkcharge").length;	
	
	
	for(var j=0;j<len;j++)
	{			
		if(document.getElementById("check"+[j]).checked)
		{			
			if(j-1>-1)
			{
				var pre=document.getElementById("limit"+[j-1]).value;
			//var pre=frm.limit[j-1].value;
			}
			var val=document.getElementById("limit"+[j]).value;
			//var val=frm.limit[j].value;
			if(j+1<len)
			{
			var nex=document.getElementById("limit"+[j+1]).value;
			//var nex=frm.limit[j+1].value;
			}
			if(pre>val)
			{
				alert("limit should be gratter than previous limit value");	
				document.getElementById("limit"+[j]).focus();		
				//frm.limit[j].focus();
				return false;
			}
			else if(val>nex)
			{
				alert("limit should be less than next limit value");
				document.getElementById("limit"+[j]).focus();
				//frm.limit[j].focus();
				return false;
			}
			i=i+1;
		}		
	}
	if(i==0)
	{
		alert("select check box for update");
		return false;
	}	
	return true;
}



function submitForm2()
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

function test(frm){
	alert(frm.name);
	alert(frm.hhhhhhhhhhhh);
	alert(frm.hhhhhhhhhhhh.value);
	var len = frm.hhhhhhhhhhhh.length;
	alert(len);
}

</script>
