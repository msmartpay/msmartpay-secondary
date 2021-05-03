<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<% 
String status=(String)request.getAttribute("apiResponse");
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
    <td valign="top" align="center" height="293">
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
	         <tr>
               <td valign="top" align="center" class="rounded-corners box_heights" >
          			<form name="SearchTranForm" action="SearchTran.action?param=SeachTranData" method="post">
	                 <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
	                  
	                 
	                       <tr>
	                         <td height="40" align="left" valign="middle" class="heading_mgs">Transaction Status Check</td>
	                       </tr>
	                      
						   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=status%></td></tr>
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
  <!--footer-->
</table>
</body>
</html>

