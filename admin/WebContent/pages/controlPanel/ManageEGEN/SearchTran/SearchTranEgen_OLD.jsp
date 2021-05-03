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
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<style type="text/css">
#calendarDiv{position:fixed !important;}
#message,#message table{position:fixed;width:400px;}
#message{overflow:hidden;width:400px;}
#message table{clear:both;margin-top:15px;}
#message h2{position:fixed;clear:both;height:30px; width:400px; clear:both;margin:0; padding:0; float:left; line-height:30px; font-size:14px; color:#ffffff; background:#a74312; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h2 img{float:right; margin:3px 3px 0 0}
#message h3{clear:both;height:auto; width:400px; margin:0; padding:0; float:left; line-height:30px; font-size:12px; color:#a74312; background:#ffffff; font-family:Verdana, Arial, Helvetica, sans-serif}
#message h3 input[type=text]{margin:0 0 0 30px;  width:120px}
#message h3 img{float:right; margin:4px 47px 0 0 }
#message p{margin:0;padding:0;position:fixed;margin-top:40px;}
#message p span{position:fixed;float:left; margin:0; width:400px; height:46px; background:#f4e7bd; border-top:2px solid #e6cb7b }
#button{height:26px; width:90px; float:right; margin:10px}
td.right_border{border-right:1px solid #fff;}
.style111 {
	font-size: 13px;
	font-weight: bold;
}
.style1115 {
	font-size: 12px;
	font-weight: bold;
	font-family: "Trebuchet MS";
	color: #a74312;
}

.style1115 font {
	font-size: 12px;
	font-weight: bold;
	font-family: "Trebuchet MS";
	color: #a74312;
}
</style>
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
    <td valign="top" align="center" height="294" class="top_padding">
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" bgcolor="">
         <tr>
               <td valign="top" align="center" class="rounded-corners" >
          
                 <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                 <tr>
                        <td height="30" colspan="4" align="center" class="headtxt"> <%=message%> 
                   </td>
                   </tr>
                 </table>
           </td>
         </tr>
                       
	     
	         <tr>
               <td valign="top" align="center" class="rounded-corners" >
          
                 <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
                  <form name="SearchTranForm" action="SearchTranEgen.action?param=SeachTranData" method="post">
                 
                       <tr>
                        <td height="7" colspan="4" align="center" class="headtxt"></td> 
                      </tr>
                       <tr>
                       
                       </tr>
                       <tr>
                         <td height="35" align="left" valign="middle" style="padding-left:0px;" class="big1">EGEN Transaction Status Check</td>
                       </tr>
                       <tr>
                         <td height="15" colspan="4" align="center" class="headtxt"></td>
                       </tr>
                       <tr>
                         <td height="15" colspan="4" align="center" class="style1115">Connection Number :
                           <input type="text"  name="connectionNo" class="small" style="width:120px"  onkeypress="return digitonly(this,event)" maxlength="15"/>                           &nbsp;&nbsp;&nbsp;
								<input type="submit" value="Search" name="updatebutton" style="width:80px; cursor:pointer; margin:0 30px 0 0;" onclick                                   ="validateForm()"/>
 						 </td>
                       </tr>
                       <tr>
					     <td align="center" valign="middle">&nbsp;</td>
				    </tr>
					     <tr>
					       <td valign="top" style="border-left:1px solid #a74312;">
						   <%if(sizeOfData>0){%>
						   <table cellspacing="0" cellpadding="0" width="100%" align="center" class="form11">
                                        <tbody style="background:#a74312;">
                             <tr class="hd" align="center" style="background:#a74312;">
                             <!--  <td width="10%" class="right_border">Client ID</td>
							 
                               <td width="10%" height="25" class="right_border">MD ID</td>
                                            <td width="12%" class="right_border">DS ID</td>-->
                                            <td width="9%" class="right_border">Corporate Agent ID</td>
                                            <td width="7%" align="center" class="right_border">Date</td>
                                            <td width="12%" align="center" style="padding-left:8px;" class="right_border">Time</td>
                                            <td width="10%" align="left" class="right_border" style="padding-left:8px;">Operator</td>
                                            <td width="12%" align="center" class="right_border">Connection Number</td>
                                            <td width="10%" align="center" class="right_border">Txn Amount</td>
                                           <!-- <td width="11%" align="center" class="right_border">Operator TID</td>-->
                                            <td width="7%" align="left" style="padding-left:8px;">Status</td>
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
                               <td width="9%" align="center" bgcolor="#FFFFFF"><%=map.get("corporateID")%></td>
                               <td width="7%" align="center" bgcolor="#FFFFFF"><%=map.get("dateOfRecharge")%></td>
                               <td width="12%" align="center" style="padding-left:8px;" bgcolor="#FFFFFF"><%=map.get("timeOfRecharge")%></td>
                               <td width="10%" align="left" bgcolor="#FFFFFF" style="padding-left:8px;"><%=map.get("connectionOperator")%></td>
                               <td width="12%" align="center" bgcolor="#FFFFFF"><%=map.get("connectionNo")%></td>
                               <td width="10%" align="center" bgcolor="#FFFFFF"><%=map.get("amount")%></td>
                               <!--<td width="11%" align="center"  bgcolor="#FFFFFF"></td>-->
                               <td width="7%" align="left" style="padding-left:8px;" bgcolor="#FFFFFF"><%=map.get("status")%></td>
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