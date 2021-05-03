


<% 



String loginId=(String)session.getAttribute("userId");

System.out.println("-----------------"+loginId);


%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="../../css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />




</head>

<body onload="validatefrm()">
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header3.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
		  
		  		  
              
			  
			  <tr>
                <td height="40" colspan="5" align="center" valign="middle">
<form name="loginInfo" method="post" action="login.action">

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" height="200" class="form11">
  <tr>
    
             <td>
			 <input type="hidden" name="userId" value="<%=loginId%>" />
				 <input type="hidden" name="param" value="checkloginInfo" />
                <td height="40"  align="center"> 
                
                   <h3>Please wait while we are validating your login information</h3></td>
              </tr>
           
      
   
</table>
</form>
				</td>
              </tr>
			  
			  
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer2.jsp" %></td>
  </tr>
</table>
</body>
</html>

<script language="javascript">
	 function validatefrm() {

              

                //  document.loginInfo.action="login.action";
				  document.loginInfo.submit();
				 
				 

                 

			 }

</script>



