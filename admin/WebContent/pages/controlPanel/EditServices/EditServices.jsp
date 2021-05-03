
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script>

</script></head>
<%
String message=(String)request.getAttribute("message");
if(message==null)message="";

ArrayList<HashMap<String,String>> alt = new ArrayList<HashMap<String,String>>();

alt = (ArrayList) request.getAttribute("service");
if(alt == null || alt.size() < 0)
{
	alt = new ArrayList<HashMap<String,String>>();
}
%>

<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td height="853" align="center" valign="top"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td height="302" align="center" valign="top"  class="rounded-corners box_heights">
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Control Panel > Service Management</td>
                    </tr>
                    <tr>
                      <td colspan="10" align="center" class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr>
                      <td class="dyn_mgs"></td>
                    </tr>
					
                    <tr>
                      <td colspan="4">
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td align="center" >
					  <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<form method="post" name="EditServicepage">
		              			
		              			<input type="hidden" name="param" value="saveDetails"/>        			
			                    <table width="464" border="0" align="center"  cellpadding="0" cellspacing="0" class="mydata_tabl"> 
								   
								   <tr>
				                   <td height="42" ><strong>Select Service</strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <select name="serviceOption" style="width:184px;">
											<option selected="selected" value="select">Select</option>
											
												<option value="LIVE RECHARGE">LIVE RECHARGE</option>
												<option value="LIVE BILLPAY">LIVE BILLPAY</option>
										</select>									  </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr>
				                   <td height="42" ><strong>Select </strong><strong>Status </strong></td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
					                      <select name="filterOption" style="width:184px;">
	                                          <option selected="selected" value="select">Select</option>
	                                          <option value="Y">Activate</option>
	                                          <option value="N">Deactivate</option>
	                                      </select>
	                                  </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
				                   <tr>
				                   		<td width="37%" height="44"> </td>
				                   		<td></td>
			                   		 	<td width="57%"> <input type="button" class="btn_sign" value="Submit" onclick="submitForm()" /> </td>
				                   		<td class="setcls"> </td>
				                   </tr>   
		               		 </table>
		                
		                  </form>
							
							</td>
                          </tr>
						  
                        </table>
						</td>
						</tr><tr ><td height="25"></td></tr>
						<tr ><td height="2" >
						
						
						</td>
						</tr>
						
						</table>
					</td>
                    </tr>
					<tr>
                      <td colspan="4">
					                      
					  </td>
                    </tr>
                    <tr>
                      <td colspan="4" height="3">
					  
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
					 </table>
					  
					</td>
                    </tr>
                </table></td>
              </tr>
          </table></td>
        </tr>
      </table>
      </td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="118"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>


<script>
function submitForm()
{
	var service = document.EditServicepage.serviceOption.value;
	var filter = document.EditServicepage.filterOption.value;
	
	if(service == "select")
		{
			alert("Please select a service");
			document.EditServicepage.serviceOption.focus();
			return false;
		}
	if(filter == "select")
		{
			alert("Please select operation");
			document.EditServicepage.filterOption.focus();
			return false;
		}
	document.EditServicepage.action = "blockServicesPage.action";
	document.EditServicepage.submit();
	return true;
}
</script>