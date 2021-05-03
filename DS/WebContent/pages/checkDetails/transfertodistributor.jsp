<%@ include file="/globaldata.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 

<%


 HashMap mapdate=(HashMap)session.getAttribute("mapTransafer");
					


%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=companyName %></title>
<link href="css/distributor.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">
<!--



function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}
//-->
</script>
<style type="text/css">
<!--
a:link {
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
-->
</style>
<style type="text/css">
<!--
.style2 {font-family: "Trebuchet MS", Tahoma, Arial, Verdana}
.style4 {font-size:11px; font-weight:bold; font-family: "Trebuchet MS", Tahoma, Arial, Verdana;}
-->
</style>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px; 
	margin-right: 0px;
	margin-bottom: 0px;
	background-color:white;
}
.trebuchet_font{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:11px;}
.trebuchet_heading{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:12px;}

</style>
</head>
<body >

<table width="440" border="0" cellspacing="0" cellpadding="0">
                          
											<tr>
											<td >
											<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center"  class="border">
											<tr >
											  <td class="trebuchet_font"  height="39" align="left" colspan="5" bgcolor="#dbe5f1">
											  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" background="images/red_middle.jpg">
			  <tr>
          
             <td align="center" valign="middle" class="big">DS TB Taken</td>
    
            </tr>
			  </table></td>
											
											
											
											
											</tr>
											
											
											
											
											
										<tr bgcolor="#FFFFFF">
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Distributor Id </font></b> </div></td>
											<td width="58%" class="Trebuchet_b"> <div align="left"><%=mapdate.get("userId")%>&nbsp;</div></td></tr>
											
											<tr bgcolor="#FFFFFF">
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Amount</font></b> </div></td>
											<td width="58%" class="Trebuchet_b"> <div align="left"><%=mapdate.get("transactionAmount")%></div></td></tr>
											
											
											<tr bgcolor="#FFFFFF">
											<td width="6%" class="Trebuchet_b"></td>
											 <td width="36%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="Trebuchet_b"><b><font color="#000000" >&nbsp;</font></b></span><span class="style4"><b><font color="#000000" >Bank Charges </font></b></span></div></td>
											<td   class="Trebuchet_b">
                                                    <div align="left"><%=mapdate.get("bankCharges")%></div>  </td></tr>
                                   
										<tr bgcolor="#FFFFFF">
											<td width="6%" class="Trebuchet_b"></td>
										  <td width="36%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="style4"><b><font color="#000000" >Net Amount </font></b></span></div></td>
											<td   class="Trebuchet_b">
                                                    <div align="left"><%=mapdate.get("deductedAmount")%></div>  </td></tr>
													
											<tr bgcolor="#FFFFFF">
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b>&nbsp;Mode Of payment </b></div></td>
											
											  <td   class="Trebuchet_b" >
                                                     <div align="left"><%=mapdate.get("mode")%></div>  </td></tr>                                             
											
											
											
											
											
											
											
											
											
											
										
										<tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
										<td width="36%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><font color="#000000">&nbsp;Bank Name </font></div></td>
										<td width="58%"   class="Trebuchet_b">
                                          <div align="left"><%=mapdate.get("bankName")%></div>                                                 </td>
										</tr>
										<tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
										<td width="36%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">Bank Tranx Id </font></b></div></td>
										<td   class="Trebuchet_b" >
                                                    <div align="left"><%=mapdate.get("bankTransactionId")%></div>                                                  </td>
										</tr>
										<tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
									      <td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000">&nbsp;Request Date</font></b></div></td>
											 <td width="58%"   class="Trebuchet_b">
										  <div align="left" ><%=mapdate.get("requestDate")%>													</div></td>
										  </tr> 
										  <tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
									      <td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000">&nbsp;Response Date</font></b></div></td>
											 <td width="58%"   class="Trebuchet_b">
										  <div align="left" ><%=mapdate.get("dateOfTransaction")%>													</div></td>
										  </tr> 
										<tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
										<td width="36%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Status</font></b></div></td>
										<td   class="Trebuchet_b" >
                                                    <div align="left" ><%
												   String str=((String)mapdate.get("status"));
													
													%>
													
													<% if(str.equalsIgnoreCase("pending")){ %>
													<font color="yellow"><%=str%></font><%}%>
													<% if ( str.equalsIgnoreCase("refund")||( str.equalsIgnoreCase("refunded"))) { 
													%>
													<font color="#FF0000">Refunded</font><%}%>
													<% if (str.equalsIgnoreCase("success")){ %>
													<font  color="#008000"><%=str%></font><%} %>
													
													<% if (str.equalsIgnoreCase("failure")){ 
													
													%>
													<font  color="blue">Failure</font>
													<%}
													%>
													</div>					                                                  </td>
										</tr>
										
										<tr bgcolor="#FFFFFF">
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
									      <td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000">&nbsp;Remark </font></b></div></td>
											 <td width="58%"   class="Trebuchet_b">
										  <div align="left" ><%=mapdate.get("remark")%>													</div></td>
										  </tr> 
										 
										<%
										System.out.println("remark is==============="+mapdate.get("remark"));%>
										 
										   
													
										
										
										<tr bgcolor="#FFFFFF">
										
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
										     <td width="36%" height="25"  class="trebuchet_font">&nbsp;</td>
										     <td width="58%"   class="Trebuchet_b">&nbsp;</td>
										  </tr>
										    
											
                                   
										<tr bgcolor="#FFFFFF">
										<td   class="Trebuchet_b" colspan="5" align="center"><a href="javascript:printpage()" style="border-width::0px;"><img src="images/print.gif" width="74" height="26" border="0px" /></a></td></tr>

											  <tr>
													<td width="6%"   class="Trebuchet_b">&nbsp;</td>
													<td width="36%" height="25" class="trebuchet_font">&nbsp;</td>
													<td   class="Trebuchet_b">&nbsp;</td>
											  </tr>
													
											
											
											
											
											
											
											
											</table></td>
											</tr>
                                         
                                      </table>
									  </body>
	</html>
	<script language="javascript">								  
  <!--
  function printpage() {
  window.print();
  }
  //-->
</script>