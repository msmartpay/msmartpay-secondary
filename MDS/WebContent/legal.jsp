<%


String legalDomainName=(String)session.getAttribute("domain_name");
String legalCompanyName=(String)session.getAttribute("company_name");


%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%></title>


<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px; 
	margin-right: 0px;
	margin-bottom: 0px;
}
.trebuchet_font{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:12px;}
.trebuchet_heading{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:12px;}
-->
</style>

<link href="CSS/dis.css" rel="stylesheet" type="text/css" />


<script language="JavaScript" src="scripts/admin_home.js"></script>
<script language="JavaScript" src="/../scripts/Datecalendar.js?random=20060118"></script>
<script language="JavaScript">





 function chk_keys(){
if(event.keyCode==30){
alert('invalid action');
}
}


</script>
<style type="text/css">
<!--
.style1 {font-family: "Trebuchet MS", Tahoma, Arial, Verdana; font-size: smaller; }
-->
</style>
</head>
<body><center>

<table width="1000" border="0" align="center" cellpadding="0" cellspacing="0">
  <!--header-->
  <tr><td align="left" height="130" width="1000">
<%@ include file="../../header.jsp" %></td></tr>

  <!--header-->
  <!--center-->
  <tr>
  <td width="100%" align="center" height="20"></td>
  </tr>
  <tr>
    <td valign="top" width="100%" align="center"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
                <tr>
                  <td width="100%" align="center" style="padding:20px 0px 20px 0px">

    <table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="form11">
                    <tr>
                    <td class="big" align="center" height="30" valign="middle">Safe Online Transactions:</td>
                    </tr>  
                    <tr>
                    <td class="ulicon2" align="left" valign="middle" height="35" style="font-size:14px; font-weight:normal"><u>Notice for urgent attention for Users of <strong><%=legalDomainName%></strong></u></td>
                    </tr>
                    
                    <tr>
        <td align="left" height="30" valign="middle" style="font-size:13px; font-weight:normal; line-height:25px; text-align:justify"><%=legalCompanyName%> will never send you e-mails asking for confidential details of your account/ PIN/ Password or personal parameters such as date of birth, mother's maiden name etc. Beware of anyone asking you for such info on behalf of the <%=legalCompanyName%> through e-mails or phone calls. While it is our constant endeavour to provide you with the best of online services & facilities, <%=legalCompanyName%> is not responsible for any erroneous or wrong transactions made by you. </td>
                    </tr>
					
                     
                    <tr>
        <td align="left" height="70" valign="middle" style="font-size:13px; font-weight:normal; line-height:25px; text-align:justify"><%=legalCompanyName%> shall also not be responsible for misuse of your account arising from any wrong, inadvertent or other kind of disclosure of such details by you.</td>
                    </tr>
                    <tr>
                    <td height="25" align="right" valign="middle"></td>
                    </tr>
                    
                  </table>
				  </td>
                </tr>
              </table>
				  
				  </td>
                </tr>
				
				<tr>
  <td width="100%" align="center" height="20"></td>
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
  <!--center-->
  <!--footer-->
  <tr>
    <td width="1000" valign="top" align="center"><%@ include file="../../footer.jsp" %></td>
  </tr>
  <!--footer-->
</table></center>
</body>
</html>
