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

<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<script language="Javascript" src="scripts/validation.js"></script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>

  <tr>
    <td valign="top" align="center" height="378">
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" bgcolor="">
         
	         <tr>
               <td valign="top" align="center" class="rounded-corners" >
          
                 <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="mydata_tabl">
                  <form name="SearchTranForm" action="SearchTranEgen.action?param=SeachTranData" method="post">
                       <tr>
                      <td height="40" align="left" valign="middle" class="heading_mgs1">
                      EGEN Transaction Status Check
                      </td>
                       </tr>
                      
					   <tr><td height="10"  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
						<tr><td  colspan="10" align="center">&nbsp;</td></tr>
                       <tr>
                         <td height="15" colspan="4" align="center">Connection Number :
                           <input type="text"  name="connectionNo" class="small"  onkeypress="return digitonly(this,event)" maxlength="15"/>                           &nbsp;&nbsp;&nbsp;
								<input type="submit" value="Search" name="updatebutton" style="width:80px; cursor:pointer; margin:0 30px 0 0;" onclick                                   ="validateForm()"/>
 						 </td>
                       </tr>
                       <tr>
					     <td align="center" valign="middle">&nbsp;</td>
				    </tr>
					     <tr>
					       <td valign="top" style="border-left:1px solid #a74312;">
						   <%if(sizeOfData>0){%>
						   <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls">
                                        <tbody style="background:#a74312;">
                             <tr class="hd" align="center" style="background:#a74312;">
                             <!--  <td width="10%" class="right_border">Client ID</td>
							 
                               <td width="10%" height="25" class="right_border">MD ID</td>
                                            <td width="12%" class="right_border">DS ID</td>-->
                                            <td width="9%" align="center" class="right_border">Corporate Agent ID</td>
                                            <td width="7%" align="center" class="right_border">Date</td>
                                            <td width="12%" align="center" class="right_border">Time</td>
                                            <td width="10%" align="center" class="right_border">Operator</td>
                                            <td width="12%" align="center" class="right_border">Connection Number</td>
                                            <td width="10%" align="center" class="right_border">Txn Amount</td>
                                           <!-- <td width="11%" align="center" class="right_border">Operator TID</td>-->
                                            <td width="7%" align="center">Status</td>
                             </tr>
                            <% for(int i=0;i<sizeOfData;i++){
							HashMap map=(HashMap)listdata.get(i);
							/*String vendorTranID=(String) map.get("vendorTranID");
							if(vendorTranID==null || vendorTranID==" " || vendorTranID.length()<=0){
							vendorTranID="NA" ;
							}*/
							%>
                              <tr bgcolor="#ffffff" align="center" class="value_reg">
                                <!--<td width="10%" align="center" bgcolor="#FFFFFF"></td>
                               <td width="10%" align="center" bgcolor="#FFFFFF"></td>
                               <td width="12%" align="center" bgcolor="#FFFFFF"></td>-->
                               <td width="9%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("corporateID")%></td>
                               <td width="7%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("dateOfRecharge")%></td>
                               <td width="12%" align="center"  bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("timeOfRecharge")%></td>
                               <td width="10%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("connectionOperator")%></td>
                               <td width="12%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("connectionNo")%></td>
                               <td width="10%" align="center" bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("amount")%></td>
                               <!--<td width="11%" align="center"  bgcolor="#FFFFFF"></td>-->
                               <td width="7%" align="center"  bgcolor="#FFFFFF" style="border-bottom:1px solid #930;"><%=map.get("status")%></td>
                             </tr>
							 <%}%>
							 
							 
							 
							 
                           </table>
						   <%}%>
						   </td>
			        </tr>
					     <tr>
					       <td align="center" valign="middle">&nbsp;</td>
			        </tr>
					     <tr>
					       <td align="center" valign="middle">&nbsp;</td>
			        </tr>
					     
					
				   </form>
							  
				
				 </table>
	           </td>
         </tr>
	           
	                
      </table>
    </td>
  </tr>
  <!--footer-->
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
if(isNaN(connectionNo){
alert('Please enter valid connection number');
return false;
}
document.SearchTranForm.submit();

}
</script>