<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.HashMap"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("type") %> Balance Push</title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

var message="No right-click allowed";

function clickIE() {if (document.all) {alert(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {alert(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

</script>

</head>
<body>
<%
String type="";
HashMap map=new HashMap();
map=(HashMap)session.getAttribute("userMap");
if(map.size()>0)
{
type=(String)map.get("userType");
}
session.removeAttribute("userMap");
%>
<div>
<form name="tbpush" action="TBTransferPush.action" method="post" onSubmit="return submitForm()">
		  <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" class="mydata_tabl" id="border" style="margin-top:10px;">
		
		 <tr>
		 
       <td  width="50%" valign="bottom" height="40" align="left" class="heading_mgs">
       
	   Push Balance - <input type="text" value="<%=type %>"  id="res" disabled="disabled" style="border:none; background:none; text-align:left; font-weight:bold;" /></td>
	   <td align="left" style="font-size:16px;color: red;"><span id="errField"></span>
              </tr> 
		  <tr><td colspan="5" height="10"></td></tr>		  
 
			   <tr>
			   
                            <td colspan="5" valign="top">
                            <table cellspacing="0" cellpadding="0" width="86%" align="center" class="mydata_tabl" id="border">
                                <tbody>
                                <%if(map.size()>0){ %>
                                  <tr align="left">
                                    <td colspan="4" height="5" align="center">
                                    <input type="hidden" name="viewType" value="<%=request.getAttribute("viewType") %>" />
                                    <input type="hidden" name="type" value="<%=request.getAttribute("type") %>" />
                                    <input type="hidden" name="id" value="<%=request.getAttribute("id") %>" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="5%"></td>
                                    <td align="left" height="25" width="40%"><strong>User ID</strong></td>
                                    <td align="left" width="10%">:</td>
                                    <td align="left" width="45%"><input type="text" name="userId" readonly="readonly" class="logintxt" value="<%=map.get("userId") %>"/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Corporate Name</strong></td>
                                    <td align="left">:</td>
                                    <td align="left"><input type="text" name="company" class="logintxt" readonly="readonly" value="<%=map.get("company") %>"/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Authorised Person</strong></td>
                                    <td align="left">:</td>
                                    <%if(type.equals("portalUser")||type.equals("portalAdmin")||type.equals("activityUser")||type.equals("activityAdmin")){ %>
                                    <td align="left"><input type="text" name="name" class="logintxt" readonly="readonly" value="<%=map.get("firstName") %> <%=map.get("lastName") %>" ></td>
                                    <%} 
                                     if(type.equals("APIClient")){ %>
                                    <td align="left"><input type="text" name="name" class="logintxt" readonly="readonly" value="<%=map.get("firstName") %> <%=map.get("lastName") %>" /></td>
                                    <%} 
                                     if(type.equals("MDS")||type.equals("DS")||type.equals("Agent")){ %>
                                    <td align="left"><input name="name" type="text" class="logintxt" readonly="readonly" value="<%=map.get("name") %>" /></td>
                                    <%} %>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Current Status</strong></td>
                                    <td align="left">:</td>
                                    <td align="left"><input name="status" type="text" readonly="readonly" class="logintxt" value="<%=map.get("status") %>"/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Current Balance</strong></td>
                                    <td align="left">:</td>
                                   
                                    <td align="left"><input type="text" name="balance" readonly="readonly" class="logintxt" value="<%=map.get("balance") %>" /></td>                                    
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Registered Mobile No</strong></td>
                                    <td align="left">:</td>
                                    <td align="left"><input type="text" name="mobileNo" class="logintxt" readonly="readonly" value="<%=map.get("mobileNo") %>"/></td>
                                  </tr>
                                  
                                  <%-- <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Registered Email id</strong></td>
                                    <td align="left">:</td>
                                    <td align="left"><input type="text" name="emailId" class="logintxt" readonly="readonly" value="<%=map.get("emailId") %>"/></td>
                                  </tr> --%>
                                  
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Amount</strong></td>
                                    <td align="left">:</td>
                                    <td align="left"><input type="text" name="amount"  onKeyPress="return digitonly(this,event)" maxlength="7" onselectstart="return false" onpaste="return false;" onCopy="return false" onCut="return false" onDrag="return false" onDrop="return false" autocomplete=off/></td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>Internal Remark</strong></td>
                                    <td align="left">:</td>
                                    <td align="left">
                                    <input type="text" name="intremark" value="<%=map.get("userId") %> <%=map.get("company") %>" />
                                    
                                    </td>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left" height="25"><strong>External Remark</strong></td>
                                    <td align="left">:</td>
                                    <td align="left" valign="middle"><input type="text" value="<%=map.get("userId") %> <%=map.get("company") %>" name="extremark" /></td>
                                  </tr>
                                   <tr>                      
                                      <td align="left"><input type="hidden" name="portalId" value="<%=map.get("portalId") %>"/></td>                      
                                    <td align="left"><input type="hidden" name="userType" value="<%=map.get("userType") %>"/></td>
                                    <%if(type.equals("Agent")){ %>
                                    <td align="left"><input type="hidden" name="userType" value="<%=map.get("clientType") %>"/></td>
                                    <%} %>
                                  </tr>
                                  <tr>
                                    <td></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
                                    <td align="left" height="55"><input type="submit" class="cls_btn" value="Submit"/></td>
                                  </tr>
	                                  <%if(request.getAttribute("message")!=null) {%>
	                                   <tr>
	                                    <td colspan="4" align="center" style="color: RED;font-weight: bold;">
	                                    	<%=request.getAttribute("message") %>
	                                    </td>
	                                  	</tr>
	                                  <%} %>
                                  
                                  <%}else{ %>
                                  <tr>
                                  <td></td>
                                    <td align="left"></td>
                                    <td align="left"></td>
                                    <td align="left" height="55"><p style="color: red; font-size: 16px">No Data found for this ID</p></td>
                                    </tr>
                                  <%} %>
                                </tbody>
                              </table></td>
                              
                          </tr>
			   
			    <tr><td colspan="5" height="20"></td></tr>
			    
            </table>
            </form>
         </div>
         <div id="resultLoading" style="width: 100%; height: 100%; position: fixed; z-index: 10000000; top: 0px; left: 0px;display:none;">
			<div
				style="width: 100%; text-align: center; position: absolute; left: 0px; top: 50%; font-size: 16px; z-index: 10; color: rgb(255, 255, 255);">
				<img
					src="images/ajax-loader.gif">
				<div>Please wait...</div>
			</div>
			<div class="bg"
				style="opacity: 0.7; width: 100%; height: 100%; position: absolute; top: 0px; background: rgb(0, 0, 0);"></div>
		</div>
</body>



</html>
