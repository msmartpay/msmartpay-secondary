

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%


                 
					 HashMap mapdate=(HashMap)session.getAttribute("mapTransafer");
					


%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("page_title")%></title>
<style type="text/css">
<!--body {
	margin-left: 0px;
	margin-top: 0px; 
	margin-right: 0px;
	margin-bottom: 0px;
}
.trebuchet_font{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:11px;}
.trebuchet_heading{font-family:"Trebuchet MS", Tahoma, Arial, Verdana; font-size:12px;}
-->
</style>
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
<link href="css/travel.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
.style2 {font-family: "Trebuchet MS", Tahoma, Arial, Verdana}
.style4 {font-size:11px; font-weight:bold; font-family: "Trebuchet MS", Tahoma, Arial, Verdana;}
-->
</style>
</head>
<body>

<table width="440" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          
											<tr>
											<td ><table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="border">
											<tr>
											  <td class="big"   bgcolor="#dbe5f1" height="31" align="center" colspan="5">Trade Balance Reversal</td>
											</tr>
											
											
											
											
											<tr>
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Reference ID/Journal ID </font></b> </div></td>
											<td width="58%" class="Trebuchet_b"> <div align="left"><%=mapdate.get("transactionId")%>&nbsp;</div></td></tr>
											
											<tr>
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Transaction ID </font></b> </div></td>
											<td width="58%" class="Trebuchet_b"> <div align="left"><%=mapdate.get("transactionId")%>&nbsp;</div></td></tr>
											
											<tr>
											<td width="6%" class="Trebuchet_b"></td>
											 <td width="36%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="Trebuchet_b"><b><font color="#000000" >&nbsp;Date Of Transaction</font></b></span></div></td>
											<td   class="Trebuchet_b">
                                                    <div align="left"><%=mapdate.get("dateOfTransaction")%></div>  </td></tr>
                                   
										<tr>
											<td width="6%" class="Trebuchet_b"></td>
										  <td width="36%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="style4"><b><font color="#000000" >&nbsp;Time Of Transaction </font></b></span></div></td>
											<td   class="Trebuchet_b">
                                                    <div align="left"><%=mapdate.get("timeOfTransaction")%></div>  </td></tr>
													
											<tr>
											<td width="6%" class="Trebuchet_b"></td>
											<td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b>&nbsp;Transaction Amount </b></div></td>
											
											  <td   class="Trebuchet_b" >
                                                     <div align="left"><%=mapdate.get("transactionAmount")%></div>  </td></tr>                                           	<tr>
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
										<td width="36%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><font color="#000000">&nbsp;Commission/Charges</font></div></td>
										<td width="58%"   class="Trebuchet_b">
                                          <div align="left"><%=mapdate.get("commisssion")%></div>                                                 </td>
										</tr>
										<tr>
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
										<td width="36%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Net Amount </font></b></div></td>
										<td   class="Trebuchet_b" >
                                                    <div align="left"><%=mapdate.get("deductedAmount")%></div>                                                  </td>
										</tr>
										<tr>
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
										
										<tr>
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
									      <td width="36%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000">&nbsp;Other Status </font></b></div></td>
											 <td width="58%"   class="Trebuchet_b">
										  <div align="left" ><%=mapdate.get("remark")%>													</div></td>
										  </tr> 
										 
										
										 
										   
													
										
										
										<tr>
										
										<td width="6%"   class="Trebuchet_b">&nbsp;</td>
                                          
										     <td width="36%" height="25"  class="trebuchet_font">&nbsp;</td>
										     <td width="58%"   class="Trebuchet_b">&nbsp;</td>
										  </tr>
										    
											
                                   
										<tr>
										<td   class="Trebuchet_b" colspan="5" align="center"><a href="javascript:printpage()" style=" border-width::0px;"><img src="images/print.gif" width="74" height="26" border="0px" /></a></td>
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