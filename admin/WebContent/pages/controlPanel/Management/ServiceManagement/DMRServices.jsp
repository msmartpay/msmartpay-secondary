<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page import="java.util.*" %>

<%

String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Super Admin Interface</title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />




<script>
function sbmt_click()
{
  	var vendorip = document.myforms.vendorIp.value;
  	if(vendorip == "")
  	{
	  	alert("Please Enter Vendor IP.");
	  	return false;
  	}
  	else{
	  document.myforms.action="OpsServiceManagement.action?param=vendorIpUpdate";
		//alert(document.myforms.action);
	 	document.myforms.submit();
	}
}
</script>

<style>
select{color:#930;}
select option{color:#930;}

</style>


</head>

<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center"
	border="0">
	<tr>
		<td width="100%" valign="top" align="center"><%@ include file="/header.jsp"%></td>
	</tr>
	<tr>
		<td align="center" width="100%" valign="top">
		<table cellpadding="0" cellspacing="0" width="90%" align="center"
			border="0">
			<tr>
				<td valign="top" align="center" class="rounded-corners box_heights" >
				<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  >
					<tr>
						<td valign="top" align="center">
						<table cellpadding="0" cellspacing="0" width="100%" align="center">
							<tr>
								<td height="40" align="left" valign="middle" class="heading_mgs">DMR Service Management</td>
							</tr>
							<tr>
								<td  align="center" valign="middle" class="dyn_mgs"><%=message%></td>
							</tr>
							<tr>
								<td colspan="4" align="center" valign="top">
								
								<table width="86%" cellpadding="0" cellspacing="0" id="border" >
								<tr>
								<td colspan="100%" align="center">
								
								<form name="myforms" method="post" action="ServiceManagement.action?param=DMRServiceUpdate">
									<table width="90%" class="mydata_tabl" cellpadding="0" cellspacing="0" >
									<tr><td height="20px" colspan="100%"></td></tr>
										<!-- <tr>
	                                      <td width="30%"></td>
	                                      <td width="10%"><strong>Select Service</strong></td><td width="10%" align="center">:</td>
	                                      <td width="60%">
	                                      
											<select name="vendor">
												<option selected="selected" value="DMR1">DMR1</option>
												<option value="DMR2">DMR2</option>
											</select>

	                                      </td>
	                                      </tr> -->
	                                      <tr>
	                                      <td width="30%"></td>
	                                      <td width="10%"><strong>Select Vendor</strong></td><td width="10%" align="center">:</td>
	                                      <td width="60%">
	                                      
											<select name="vendor">
												<option selected="selected" value="${requestScope.Vendor }">${requestScope.Vendor }</option>
												<option value="DMR1">DMR1</option>
												<option value="DMR2">DMR2</option>
											</select>

	                                      </td>
	                                      </tr>
	                                      <tr>
	                                      <td></td><td></td> <td></td>
	                                      <td align="left"><input class="cls_btn" type="submit" value="Submit" /> </td>
	                                      </tr>
	                                       <tr>
	                                      <td></td><td></td><td></td> <td>&nbsp; </td>
	                                      </tr>
	                                      <tr><td height="20px" colspan="100%"></td></tr>
	                                  </table>
                                </form>   
                                      </td>
								</tr>
								</table>
								
								</td>
							</tr>

							
							<tr>
								<td colspan="4" align="center">
                     
                      
					   
								</td>
							</tr>
                            
							<tr>
								<td colspan="4" height="30"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
    <tr>
		<td width="100%" height="174px" valign="top" align="center">
		
        </td>
	</tr>
	<tr>
		<td width="100%" valign="top" align="center">
		<%@ include file="/footer.jsp"%>
        </td>
	</tr>
</table>
</body>
</html>

<script>


function rechargeSubmit1()
{
	var vendor_status=document.form1.myoption1.value;
	if((vendor_status=="select")||(vendor_status==""))
	{
		alert("Select vandor status.");
		document.form1.myoption1.focus();
		return false;
	}
	var vendor=document.form1.vendor.value;
	if((vendor=="select")||(vendor==""))
	{
		alert("Select vandor status.");
		document.form1.vendor.focus();
		return false;
	}
	document.form1.action="OpsServiceManagement.action?param=updateDetails";
	document.form1.submit();
}

function myfunc()
{
	var val = document.form1.myselects.value;
if(val == "actions")
{
	 return false;
}
if(val == "y")
{
document.form1.myoption1.options[0].selected=true;
document.form1.myoption2.options[0].selected=true;
document.form1.myoption3.options[0].selected=true;
document.form1.myoption4.options[0].selected=true;
document.form1.myoption5.options[0].selected=true;
document.form1.myoption6.options[0].selected=true;
document.form1.myoption7.options[0].selected=true;
document.form1.myoption8.options[0].selected=true;
document.form1.myoption9.options[0].selected=true;
document.form1.myoption10.options[0].selected=true;
document.form1.myoption11.options[0].selected=true;
document.form1.myoption12.options[0].selected=true;
document.form1.myoption13.options[0].selected=true;
document.form1.myoption14.options[0].selected=true;
document.form1.myoption15.options[0].selected=true;
document.form1.myoption16.options[0].selected=true;
document.form1.myoption17.options[0].selected=true;
document.form1.myoption18.options[0].selected=true;
document.form1.myoption19.options[0].selected=true;
document.form1.myoption20.options[0].selected=true;
document.form1.myoption21.options[0].selected=true;
document.form1.myoption22.options[0].selected=true;
document.form1.myoption23.options[0].selected=true;
document.form1.myoption24.options[0].selected=true;
document.form1.myoption26.options[0].selected=true;
document.form1.myoption27.options[0].selected=true;
document.form1.myoption28.options[0].selected=true;
document.form1.myoption29.options[0].selected=true;
document.form1.myoption30.options[0].selected=true;
document.form1.myoption31.options[0].selected=true;
document.form1.myoption32.options[0].selected=true;
document.form1.myoption33.options[0].selected=true;
document.form1.myoption34.options[0].selected=true;
}

if(val == "n")
{
document.form1.myoption1.options[1].selected=true;
document.form1.myoption2.options[1].selected=true;
document.form1.myoption3.options[1].selected=true;
document.form1.myoption4.options[1].selected=true;
document.form1.myoption5.options[1].selected=true;
document.form1.myoption6.options[1].selected=true;
document.form1.myoption7.options[1].selected=true;
document.form1.myoption8.options[1].selected=true;
document.form1.myoption9.options[1].selected=true;
document.form1.myoption10.options[1].selected=true;
document.form1.myoption11.options[1].selected=true;
document.form1.myoption12.options[1].selected=true;
document.form1.myoption13.options[1].selected=true;
document.form1.myoption14.options[1].selected=true;
document.form1.myoption15.options[1].selected=true;
document.form1.myoption16.options[1].selected=true;
document.form1.myoption17.options[1].selected=true;
document.form1.myoption18.options[1].selected=true;
document.form1.myoption19.options[1].selected=true;
document.form1.myoption20.options[1].selected=true;
document.form1.myoption21.options[1].selected=true;
document.form1.myoption22.options[1].selected=true;
document.form1.myoption23.options[1].selected=true;
document.form1.myoption24.options[1].selected=true;
document.form1.myoption26.options[1].selected=true;
document.form1.myoption27.options[1].selected=true;
document.form1.myoption28.options[1].selected=true;
document.form1.myoption29.options[1].selected=true;
document.form1.myoption30.options[1].selected=true;
document.form1.myoption31.options[1].selected=true;
document.form1.myoption32.options[1].selected=true;
document.form1.myoption33.options[1].selected=true;
document.form1.myoption34.options[1].selected=true;

}

}
</script>

<script>

function myfunc2()
{
	var val = document.form2.myselects.value;
if(val == "actions")
{
	 return false;
}
	if(val == "y")
{
document.form2.myoption1.options[0].selected=true;
document.form2.myoption2.options[0].selected=true;
document.form2.myoption3.options[0].selected=true;
document.form2.myoption4.options[0].selected=true;
document.form2.myoption5.options[0].selected=true;
document.form2.myoption6.options[0].selected=true;
document.form2.myoption7.options[0].selected=true;
document.form2.myoption8.options[0].selected=true;
document.form2.myoption9.options[0].selected=true;
document.form2.myoption10.options[0].selected=true;
document.form2.myoption11.options[0].selected=true;
document.form2.myoption12.options[0].selected=true;
document.form2.myoption13.options[0].selected=true;
document.form2.myoption14.options[0].selected=true;
document.form2.myoption15.options[0].selected=true;
document.form2.myoption16.options[0].selected=true;
document.form2.myoption17.options[0].selected=true;
document.form2.myoption18.options[0].selected=true;
document.form2.myoption19.options[0].selected=true;
document.form2.myoption20.options[0].selected=true;
document.form2.myoption21.options[0].selected=true;
document.form2.myoption22.options[0].selected=true;
document.form2.myoption23.options[0].selected=true;
document.form2.myoption24.options[0].selected=true;
document.form2.myoption26.options[0].selected=true;
document.form2.myoption27.options[0].selected=true;
document.form2.myoption28.options[0].selected=true;
document.form2.myoption29.options[0].selected=true;
document.form2.myoption30.options[0].selected=true;
document.form2.myoption31.options[0].selected=true;
document.form2.myoption32.options[0].selected=true;
document.form2.myoption33.options[0].selected=true;
document.form2.myoption34.options[0].selected=true;
}
	if(val == "n1")
{
document.form2.myoption1.options[1].selected=true;
document.form2.myoption2.options[1].selected=true;
document.form2.myoption3.options[1].selected=true;
document.form2.myoption4.options[1].selected=true;
document.form2.myoption5.options[1].selected=true;
document.form2.myoption6.options[1].selected=true;
document.form2.myoption7.options[1].selected=true;
document.form2.myoption8.options[1].selected=true;
document.form2.myoption9.options[1].selected=true;
document.form2.myoption10.options[1].selected=true;
document.form2.myoption11.options[1].selected=true;
document.form2.myoption12.options[1].selected=true;
document.form2.myoption13.options[1].selected=true;
document.form2.myoption14.options[1].selected=true;
document.form2.myoption15.options[1].selected=true;
document.form2.myoption16.options[1].selected=true;
document.form2.myoption17.options[1].selected=true;
document.form2.myoption18.options[1].selected=true;
document.form2.myoption19.options[1].selected=true;
document.form2.myoption20.options[1].selected=true;
document.form2.myoption21.options[1].selected=true;
document.form2.myoption22.options[1].selected=true;
document.form2.myoption23.options[1].selected=true;
document.form2.myoption24.options[1].selected=true;
document.form2.myoption26.options[1].selected=true;
document.form2.myoption27.options[1].selected=true;
document.form2.myoption28.options[1].selected=true;
document.form2.myoption29.options[1].selected=true;
document.form2.myoption30.options[1].selected=true;
document.form2.myoption31.options[1].selected=true;
document.form2.myoption32.options[1].selected=true;
document.form2.myoption33.options[1].selected=true;
document.form2.myoption34.options[1].selected=true;

}

}
</script>

<script>
function test(size)
{
	var val = document.form3.myselects.value;
	if(val == "actions")
	{
		 return false;
	}

	if(val == "y")
	{
		for(var i=0;i<size;i++)
		{
			var j=i+1;
			//alert(j);
			document.form3.getElementById("myoption"+j).option[0].selected=true;
			document.form3.getElementById("myoption"+j).option[1].selected=false;
		}
	}
	
	if(val == "n")
	{
		for(var i=0;i<size;i++)
		{
			var j=i+1;
			//alert(j);
			document.form3.getElementById("myoption"+j).option[1].selected=true;
			document.form3.getElementById("myoption"+j).option[0].selected=false;
		}
	}
}
</script>