<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<% 
ArrayList listdata=(ArrayList) request.getAttribute("data");
int sizeOfData=0;
if(listdata==null){
sizeOfData=-1;
}
else{
sizeOfData=listdata.size();}
String message=(String) request.getAttribute("message");
if(message==null){
message="";}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script language="Javascript" src="scripts/validation.js"></script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>

  <tr>
    <td valign="top" align="center" height="293">
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
	         <tr>
               <td valign="top" align="center" class="rounded-corners box_heights" >
          			<form name="SearchTranForm" action="SearchTran.action?param=SeachTranData" method="post">
	                 <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
	                  
	                 
	                       <tr>
	                         <td height="40" align="left" valign="middle" class="heading_mgs">Transaction Status Check</td>
	                       </tr>
	                      
						   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
	                        <tr>
	                        <td colspan="100%">
	                        
	                        <table cellpadding="0" cellspacing="0" width="86%" align="center" id="border" class="mydata_tabl">
	                              
	                              <tr>
	                         <td height="23" align="center" > 						 </td>
	                         </tr>
	                        
	                           <tr>
	                           <td width="20%" height="15" align="center"><strong>Connection Number : 
	                               <input type="text"  name="connectionNo" style="width:170px;" class="small"  onkeypress="return digitonly(this,event)" maxlength="15"/> &nbsp;&nbsp;&nbsp;
	                               <input type="submit" value="Search" name="updatebutton" class="cls_btn" onclick="validateForm()"/>
	                           </strong></td>
	                         </tr>
	                        
	                        
	                       
	                       
	                      		   <tr>
						     <td align="center" valign="middle">&nbsp;</td>
					    </tr>
						    	<tr>
						       <td valign="top" style="border-left:0px solid #a74312;">
							   <%if(sizeOfData>0){%>
							   <table cellspacing="0" cellpadding="0" width="96%" align="center" class="tbls">
	                              
	                             <tr class="hd tabulardata" align="center">
	                                           <td width="10%" align="center" style="color:#FFF;border-right:1px solid #930;">Agent ID</td>
	                                            <td width="10%" align="center" style="color:#FFF;border-right:1px solid #930;">Date</td>
	                                            <td width="10%" align="center" style="color:#FFF;border-right:1px solid #930;">Time</td>
	                                            <td width="12%" align="center" style="color:#FFF;border-right:1px solid #930;">Txn No</td>
	                                            <td width="12%" align="center" style="color:#FFF;border-right:1px solid #930;">Vendor Txn ID</td>
	                                            <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("SuperAdmin")||loginUserType.equalsIgnoreCase("activityUser")){ %>	                                            
	                                            <td width="12%" align="center" style="color:#FFF;border-right:1px solid #930;">Api Provider</td>
	                                            <%} %>
	                                            <td width="10%" align="center" style="color:#FFF;border-right:1px solid #930;">Operator</td>
	                                            <td width="8%" align="center" style="color:#FFF;border-right:1px solid #930;">Connection Number</td>
	                                            <td width="5%" align="center" style="color:#FFF;border-right:1px solid #930;">Amount</td>
	                                            <td width="12%" align="center" style="color:#FFF;border-right:1px solid #930;">Status</td>
	                             </tr>
	                            <% for(int i=0;i<sizeOfData;i++){
								HashMap map=(HashMap)listdata.get(i);
								
								%>
	                            
	                            <tr bgcolor="#ffffff" align="center" class="value_reg">
	
	                               <td width="9%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("agentID")%></td>
	                               <td width="7%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("dateOfRecharge")%></td>
	                               <td width="12%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><%=map.get("timeOfRecharge")%></td>
	                               <td width="12%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><%=map.get("TranId")%></td>
	                               <%
	                               	String vendortxnid=map.get("USessionID")+"";
	                               
	                               if(vendortxnid.equalsIgnoreCase("NA")){ %>
	                               <td width="12%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><%=map.get("USessionID")%></td>
	                               <%}else{ %>
	                               <td width="12%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><a href="http://www.smartkinda.com/AG/status.jsp?marsId=<%=map.get("USessionID")%>" target="_blank"><%=map.get("USessionID")%></a></td>
	                               <%} %>
	                               <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("activityUser")||loginUserType.equalsIgnoreCase("SuperAdmin")){ %>
	                              <td width="12%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><%=map.get("ApiProvider")%></td>
	                              <%} %>
	                               <td width="10%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("connectionOperator")%></td>
	                               <td width="12%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("connectionNo")%></td>
	                               <td width="10%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("amount")%></td>
	                               <td width="7%" align="center" style="border-bottom:1px solid #930;" bgcolor="#FFFFFF"><%=map.get("status")%></td>
	                             </tr>
	                             
								 <%}%>
	                           </table>
							   <%}%>						  
							    </td>
				        		</tr>
						<tr>
						     <td height="26" align="center" valign="middle">&nbsp;</td>
					    </tr>
	                    </table>
	                    
						      </td>
						    </tr> 
							<tr><td height="30"  colspan="10" align="center"  ></td></tr>
						
					  
								  
					
					 </table>
				  </form>
	           </td>
         </tr>
	           
	                
      </table>
    </td>
  </tr>
  <!--footer-->
  <tr>
    <td width="100%" valign="top" align="center" height="84"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>

<script language="javascript">
function validateForm(){
var connectionNo=document.SearchTranForm.connectionNo.value.replace(/^\s+|\s+$/, '');
if(connectionNo==""){
alert('Please enter connection number.');
return false;
}
if(connectionNo.length>15 || connectionNo.length<8){
alert('Please enter vlaid connection number');
return false;
}
if(isNaN(connectionNo)){
alert('Please enter valid connection number');
return false;
}
document.SearchTranForm.submit();

}
</script>