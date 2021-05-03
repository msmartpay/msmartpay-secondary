<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%@ page import="java.util.HashMap" %>
	<%@ page import="java.util.ArrayList" %>
	<%
	HashMap mapdata=(HashMap) request.getAttribute("AccountDetailsMap");
	HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
	
	String companyName=dynamicData.get("companyName");
	
	String headerHomeImage=dynamicData.get("headerHomeImage");
	String powerBy=dynamicData.get("powerBy");
	if(powerBy==null){
	powerBy="";} 
	
%>

<title>Statement</title>

<link href="images/favicon.gif" rel="shortcut icon" />
<!--<link rel="stylesheet" type="text/css" href="css/style.css"/>-->
<style>
table tr td{ font-family:"Trebuchet MS"; font-size:13px; color:#336699;}


</style>
</head>
 
<body bgcolor="#eff2f7">
<table style="width:75%; height:100px; margin:auto;" border="0" align="center" cellpadding="0" cellspacing="0" >
                       <tr>
                          
                          <td colspan="3" align="center"  valign="middle" ><img height="70" width="200" src="images/<%=headerHomeImage %>" /></td>
                       </tr>
</table>
<table style="width:75%; height:400px; margin:auto;" border="0" align="center" cellpadding="0" cellspacing="0" >
                       <tr>
                          
                          <td colspan="3" align="center"  valign="middle" class="tra"><strong>TRANSACTION DETAILS</strong></td>
                       </tr>
    
                       <tr>
                          
                          <td width="30%"   valign="top"   align="left"><strong class="tra_2">Transaction ID</strong></td>
                          <td width="2%" valign="top" align="left"   class="tra_2">:</td>
						  
                          <td width="40%" valign="top" align="left"   class="tra_2"><%=mapdata.get("AgentTranID")%></td></tr>
    

                       <tr>
                          
                          <td  valign="top" align="left" class="tra_2"><strong>Service Delivery TID</strong></td>
                          <td align="left" valign="top">:</td>
						  <%String serviceTID=(String)mapdata.get("UsessionID");
						  if(serviceTID==null){serviceTID="";}
						  %>
                          <td align="left" valign="top" class="tra_2"><%=serviceTID%></td></tr>
    
                       <tr>
                          
                          <td  valign="top" align="left" class="tra_2"><strong>Service</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
						  <%String service=mapdata.get("service1")+"   (" +mapdata.get("mobileOperator")+ " )";%>
                          <td align="left" valign="top" class="tra_2"><%=service%></td>
                      </tr>
  
                      <tr>
                          
                          <td  valign="top" align="left" class="tra_2"><strong>Description</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="left" valign="top" class="tra_2"><%=mapdata.get("mobileNumber")%></td>
                      </tr>
    

                      <tr>
                          
                          <td  align="left"  valign="top" class="tra_2"><strong>Txn. Amount</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="left" valign="top" class="tra_2"><%=mapdata.get("amount")%></td>
                      </tr>
    
  
                       <tr>  
                          <td  align="left"  valign="top" class="tra_2"><strong>Transaction Date</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="left" valign="top" class="tra_2"><%=mapdata.get("dateOfRec")%></td>
                       </tr>
						<tr>
						   <td  align="left"  valign="top" class="tra_2"><strong>Transaction Time</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="left" valign="top" class="tra_2"><%=mapdata.get("timeOfRec")%></td>
                         </tr>
    
                     <tr>
                         
                          <td  align="left"  valign="top" class="tra_2"><strong>Status</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="left" valign="top" class="tra_2">
                          <%
                          String statusVal=mapdata.get("status")+"";
                          if(statusVal.equalsIgnoreCase("Pending"))
                          	statusVal="In Process";
                          %>
                          
                          <%=statusVal%></td></tr>
    
                    <!--<tr>
                         
                          <td  align="left"  valign="top" class="tra_2"><strong>Remark</strong></td>
                          <td align="left" valign="top" class="tra_2">:</td>
                          <td align="center" valign="top" class="tra_2"><%=mapdata.get("")%></td>
					</tr>-->
    <!--<tr>
                          <td colspan="4" align="center"  valign="middle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						  <input type="button"  value="Close" class="btn_sign" onclick="JavaScript:window.close()"/></td></td>
						  
						  
                    </tr>-->
                    <tr>
                         
                          <td  align="center" colspan="3"  valign="top" class="tra_2"><a style="font-size: 20px;font-weight: bold;" href="javascript:window.print();">Print</a></td>
                     </tr>
                  </table>


</body>
</html>
<script language="javascript">
function close(){
self.close();

}
</script>