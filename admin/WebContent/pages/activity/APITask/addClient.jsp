<%@page import="com.controlPanel.setmarkup.SetMarkupForm"%>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%Date todate = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String currDate = formatter.format(todate);
String checkloginType=(String)session.getAttribute("loginType");
String message="";
message=(String)request.getAttribute("message");
if(message==null){
message="";
}
String flag=(String) request.getAttribute("flag");

if(flag==null){
flag="NA";
}
String panelOption="";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>
  
function submitForm()
{
	var clientName=document.addclient.clientName.value;
	var dbName=document.addclient.dbName.value;
	var serverIP=document.addclient.serverIP.value;
	if(clientName=="")
	{
		alert("Please Enter Client Name.");
		document.addclient.clientName.focus();
		return false;
	}
	
	else if(dbName=="")
	{
		alert("Please Enter DB Server IP.");
		document.addclient.dbName.focus();
		return false;
	}
	else if(serverIP=="")
	{
		alert("Please Enter DB Name.");
		document.addclient.serverIP.focus();
		return false;
	}
	else
	{
		document.addclient.action="apiTask.action?param=addClient";
		document.addclient.submit();
	}
}


</script>
</head>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Activity > API Task > Add Client</td>
                    </tr>
                    <tr>
                      <td align="center" class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr valign="top">
                      <td colspan="4" >
					  <table width="100%" height="336" align="center" cellpadding="0"  cellspacing="0">
                          <tr>
                            <td align="center" valign="top" >
					          <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<form method="post" name="addclient">
		              
		              			        			
			                    <table width="464" border="0" align="center"  cellpadding="0" cellspacing="0" class="mydata_tabl"> 
								   
								  
								   <tr  >
				                   <td height="42" ><strong>Client Name  </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <input value="" id="id" name="clientName" style="width:180px" type="text" required="true"/>			                         											                                      </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr  >
				                   <td height="42" ><strong>DB Name  </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <input value="" id="id" name="dbName" style="width:180px;" type="text" required="true"/>			                         											                                      </td>
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr  >
				                   <td height="42" ><strong>DB Server IP  </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <select name="serverIP" style="width:184px;" >
										<option value="198.57.187.247" >198.57.187.247</option>	
										<option value="198.57.185.89" selected="selected">198.57.185.89</option>
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
						</tr>
						<tr ><td >
						
						
						</td></tr>
						
						</table>
					</td>
                    </tr>
					<tr>
                      
                    </tr>
                    <tr>
                      <td colspan="4" height="30">
					  
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                      <tr>
                      <td align="center">
                      
                      
                      </td>
                      </tr>
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
    <td width="100%" valign="top" align="center" ></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
