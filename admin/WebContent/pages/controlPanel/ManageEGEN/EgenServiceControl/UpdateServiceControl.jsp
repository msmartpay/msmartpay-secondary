<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />


<%@ page import="java.util.*" %>
<%

String Operator=(String)request.getAttribute("Operator");
String subservice=(String)request.getAttribute("subservice");
String EnteruserId=(String)request.getAttribute("EnteruserId");
String service=(String)request.getAttribute("service");
String ip_address=(String)request.getRemoteAddr();
String checkStatus="";
String Status="";

ArrayList<HashMap<String,Object>> getDetails=(ArrayList<HashMap<String,Object>>)request.getAttribute("getDetails");

int size=0;

					 if (getDetails==null)
					 {
						 size=-1;
					 }
					 else
					 {
						   size=getDetails.size();
						  System.out.println("size====="+size);
		                  HashMap<String,Object> map=(HashMap<String,Object>)getDetails.get(0);
						 checkStatus=(String)map.get("checkStatus");
					if(checkStatus==null || checkStatus.equalsIgnoreCase("null") || checkStatus.equals("")|| checkStatus=="null" )
		                     {
		    	                checkStatus="";	
		                       }
						 System.out.println("checkStatus====="+checkStatus);
					 }

String message=(String)request.getAttribute("message");
if(message==null)
{
message="";

}


%>
<SCRIPT type="text/javascript" src="scripts/EgenWLServiceControlValidation.js"></script>
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
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Service Manager</td>
              </tr>
			  <%if(size>0){%>
			  <form name="update" method="post">
              <tr>
			  <input type="hidden" name="mainservice" value="EgenServiceControl">
			  <input type="hidden" name="Operator" value="<%=Operator%>">
			  <input type="hidden" name="subservice" value="<%=subservice%>">
			  <input type="hidden" name="EnteruserId" value="<%=EnteruserId%>">
			  <input type="hidden" name="service" value="<%=service%>">
			  <input type="hidden" name="ipAddress" value="<%=ip_address%>">
			  
                <td valign="top" align="center">
                <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0">
                    <tr>
                      <td valign="top">
                      <table cellspacing="0" cellpadding="0" width="100%" align="center" class="tbls" bgcolor="#a74312">
                          <tbody>
                            <tr class="hd" align="center">
                              <td height="25"><strong>Select</strong></td>
                              <td><strong>Service</strong></td>
							 
                              <td><strong>Operator</strong></td>
							
                              <td ><strong>Inetrnal Message</strong> </td>
							  <td><strong>ExternalMessage</strong></td>
							  <%if(checkStatus.equalsIgnoreCase("getStatus")) {%>
							   <td> <strong>Current Status</strong></td>
                              <%}%>
                            </tr>
                            
							  <%  
						          	int i=0;
		 					   for ( i=0;i<size;i++ )
					          {
						       HashMap<String,Object> mapdate=(HashMap<String,Object>)getDetails.get(i);
							   %>
                              <tr bgcolor="#FFFFFF" align="center">
                              <td width="6%"><input type="checkbox" name="updateCheckpartail"  value="<%=mapdate.get("opValue")%>&<%=mapdate.get("service")%>&<%=i%>"/></td>
                              <td width="15%"><%=mapdate.get("service")%></td>
                            
							  <td width="15%"><%=mapdate.get("opName")%></td>
							
                              <td width="25%" align="center"><input type="text"  name="internalremark" style="width:220px"  id="internalremark<%=i%>" value="<%=mapdate.get("InternalRemark")%>" /></td>
                              <td width="25%" align="center"><input type="text"   name="response" style="width:220px" id="response<%=i%>"  value="<%=mapdate.get("ExternalRemark")%>"/></td>
                            <%if(checkStatus.equalsIgnoreCase("getStatus")) {%>
							   <td><%=mapdate.get("Status")%></td>
                              <%}%>
                            </tr>
							
                          <%}%>
							
                          </tbody>
                        </table></td>
                    </tr>
                    <tr>
                      <td align="right" height="25" valign="bottom" ></td>
                    </tr>
					<tr>
                      <td align="right" height="25" valign="bottom" style="font-family:Calibri; font-size:14Px; color:#a74312; padding-right:20px">
					 <input type="button" class="cls_btn" value="Block"  onclick="block12('<%=i%>')"/>
					  <input type="button" class="cls_btn" value="UnBlock" onclick="Unblock('<%=i%>')" /></td>
					 
                    </tr>
                    <tr>
                   
                    </tr>
                    <tr>
                      <td colspan="5" height="20"></td>
                    </tr>
                  </table>
                  </td>
              </tr>
             </form>
			 <%}%>
			  <tr>
                <td colspan="4" height="264"></td>
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
