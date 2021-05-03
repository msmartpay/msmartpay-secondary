
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.* "%> 

<%
String company_name=(String)session.getAttribute("company_name");
String header1Image=(String)session.getAttribute("header1_image"); 


                  Date today = new Date();
String sqldate = formatter.format(today);
				  
		           java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
					 HashMap mapdate=(HashMap)session.getAttribute("mapTransafer");
					


%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=company_name%></title>
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
<link href="css/insidecss.css" rel="stylesheet" type="text/css" />
<link href="css/Style.css" rel="stylesheet" type="text/css" />
<link href="css/style_new_pages.css" rel="stylesheet" type="text/css" />
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
.style3 {
	font-size: 18px;
	font-weight: bold;
}
.style4 {font-size:11px; font-weight:bold; font-family: "Trebuchet MS", Tahoma, Arial, Verdana;}
-->
</style>
</head>
<body>

<table width="600" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">
                          
											<tr>
											<td ><table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="border">
											<tr><td width="15%"  height="31" align="left" class="trebuchet_font" ><img src="images/<%=header1Image%>" height="55" width="160"  border="0" /></td>
											<td width="85%" colspan="4"><table width="430" align="right" >
											  <tr valign="top" ><td width="204" valign="top"><b><%=sqldate%>&nbsp;<%=sqltime%></b></td>
											  </tr>
											  <tr><td>&nbsp; </td></tr>
											   <tr><td>&nbsp; </td>
											   <td width="83"><b>Agent Id</b></td>
											<td width="154"><%=session.getAttribute("agent_colmplete_id")%></td>
</tr>
											</table></td></tr></table>
											
											  <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center">
                                                <tr>
                                                  <td colspan="3" align="center" valign="middle" height="35" ><b>Acknowledgement Reciept</b></td>
                                                </tr>
                                                <tr>
                                                  <td colspan="3" align="center" >&nbsp;</td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Service Name </font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left">Trade Balance - Taken</div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Bank</font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left">
                                                    <% String bankname=(String)mapdate.get("bankName");
													
											if(bankname==""|| bankname.equalsIgnoreCase("null")) { %>
                                                    --
                                                    <% } else { %>
                                                    <%=bankname%>
                                                      <%}%>
                                                  </div></td>
                                                </tr>
												
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Mode Of Payment</font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left"><%=mapdate.get("mode")%></div></td>
                                                </tr>
                                                <tr>
                                                  <% String mode=(String)mapdate.get("modeOfPayment");
											if(mode.equalsIgnoreCase("cheque deposit")) {%>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Card/Cheque/DD No.</font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Number")%></div></td></tr><tr>
                                                  <% } 
											
											if(mode.equalsIgnoreCase("E-Transfer")||mode.equalsIgnoreCase("NEFT")) {%>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Account No.</font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Number")%></div></td>
                                                  <% } %>
                                                </tr>
                                                <tr>
                                                  <td class="Trebuchet_b"></td>
                                                  <td height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Updated By </font></b> </div></td>
                                                  <td class="Trebuchet_b"><div align="left"><%=mapdate.get("fullid")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Reference ID/Journal ID </font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left"><%=request.getAttribute("tran_id")%>&nbsp;</div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Transaction ID </font></b> </div></td>
                                                  <td width="56%" class="Trebuchet_b"><div align="left"><%=mapdate.get("tid")%>&nbsp;</div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="Trebuchet_b"><b><font color="#000000" >&nbsp;Date Of Transaction</font></b></span></div></td>
                                                  <td   class="Trebuchet_b"><div align="left"><%=mapdate.get("dot")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="style4"><b><font color="#000000" >&nbsp;Time Of Transaction </font></b></span></div></td>
                                                  <td   class="Trebuchet_b"><div align="left"><%=mapdate.get("tot")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%" class="Trebuchet_b"></td>
                                                  <td width="34%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b>&nbsp;Transaction Amount </b></div></td>
                                                  <td   class="Trebuchet_b" ><div align="left"><%=mapdate.get("reqamt")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
                                                  <td width="34%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><font color="#000000">&nbsp;Commission/Charges</font></div></td>
                                                  <td width="56%"   class="Trebuchet_b"><div align="left"><%=mapdate.get("comm")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
                                                  <td width="34%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Net Amount </font></b></div></td>
                                                  <td   class="Trebuchet_b" ><div align="left"><%=mapdate.get("dedamt")%></div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
                                                  <td width="34%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Status</font></b></div></td>
                                                  <td   class="Trebuchet_b" ><div align="left" >
                                                    <%
												   String str=((String)mapdate.get("status"));
													
													%>
                                                      <% if(str.equalsIgnoreCase("pending")){ %>
                                                      <font color="yellow"><%=str%></font>
                                                    <%}%>
                                                      <% if ( str.equalsIgnoreCase("refund")||( str.equalsIgnoreCase("refunded"))) { 
													%>
                                                      <font color="#FF0000">Refunded</font>
                                                    <%}%>
                                                      <% if (str.equalsIgnoreCase("success")){ %>
                                                      <font  color="#008000"><%=str%></font>
                                                    <%} %>
                                                      <% if (str.equalsIgnoreCase("failure")){ 
													
													%>
                                                      <font  color="blue">Failure</font>
                                                      <%}
													%>
                                                  </div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
                                                  <td width="34%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000">&nbsp;Other Status </font></b></div></td>
                                                  <td width="56%"   class="Trebuchet_b"><div align="left" >
                                                    <%String remark=(String)mapdate.get("remark");
										
										       if(remark==""|| remark==null) {%>
                                                    
                                                    <%} else { %>
                                                    <%=remark%>
                                                    <%}%>
                                                  </div></td>
                                                </tr>
                                                <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
                                                  <td width="34%" height="25"  class="trebuchet_font">&nbsp;</td>
                                                  <td width="56%"   class="Trebuchet_b">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                  <td   class="Trebuchet_b" colspan="5" align="center"><a href="javascript:printpage()" style=" border-width::0px;"><img src="images/print.gif" width="74" height="26" border="0px" /></a></td>
                                                </tr>
											    <tr>
                                                  <td width="10%"   class="Trebuchet_b">&nbsp;</td>
											      <td width="34%" height="25" class="trebuchet_font">&nbsp;</td>
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