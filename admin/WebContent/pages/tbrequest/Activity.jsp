<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
</head>
<%
String message=(String)request.getAttribute("message");
if(message=="")
{
	message="";
}

 %>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
  <tr>
    <td valign="top" align="center" height="378">
   
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">   
        <tr>
          <td valign="top" align="center" class="rounded-corners box_heights" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  valign="middle" height="40" align="left" class="heading_mgs" >Self Trade Balance</td>  
                          
              </tr>             
              <tr>               
                 <%if(message!=null)
                { %>
                <td valign="bottom"  align="center"  class="logintxt"><%=message %></td>
                <%} else{%>  
                 <td colspan="4"></td>
                <%} %>
              </tr>
              <tr>
                <td valign="top" align="center"><form name="mydeposit"  method="post" name="formRequest">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="mydata_tabl" id="border">
                      <tr>
                        <td height="10" colspan="4"></td>
                      </tr>
                      <tr>
                        <td width="38%" align="right" height="55"><strong>Enter Trade Balance Amount</strong></td>                        
                        <td align="center" width="10%">:</td>
                        <td><input type="text" name="AmountToCredit" onkeypress="return digitonly(this,event)" maxlength="7"/>
                            </td> <td><span id="amountErr"></span></td> 
                            </tr>
                                           
                      
                      <tr>   
					  <td></td>  
					   <td></td>                       
					<td align="left" height="55" id="submitbutton" style="display:block"><input type="button" class="cls_btn" value="Submit"  onclick="submitForm()"/></td>
					<td align="left" style="visibility:hidden" id="imageCheck" ><img src="images/loading2.gif" height="30" width="30"/></td>
                    </tr>
                      
                    </table>
                    
                  </form></td>
              </tr>
              
            </table></td>
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
<script type="text/javascript">
function submitForm()
{
	var amount=document.mydeposit.AmountToCredit.value;
	
	if(amount=="")
	{
		document.getElementById("amountErr").innerHTML='<p>Amount must be selected  </p></html>';
		document.mydeposit.AmountToCredit.focus();
		return false;
}
	if(amount<1)
	{
		document.getElementById("amountErr").innerHTML='<p>Amount must be > 1  </p></html>';
		document.mydeposit.AmountToCredit.focus();
		return false;
	}

	if(amount>5000000)
	{
		document.getElementById("amountErr").innerHTML='<p>Amount must be < 50,00000  </p></html>';
		document.mydeposit.AmountToCredit.focus();
		return false;
	}
	
	document.getElementById('submitbutton').style.display="none";
	document.getElementById("imageCheck").style.visibility="visible";
	document.mydeposit.action="TBRequestSelf.action?amount="+amount;	
	document.mydeposit.submit();
}

</script>