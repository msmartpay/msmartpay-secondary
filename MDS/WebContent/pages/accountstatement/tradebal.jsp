<%@ page import="java.util.Date" %>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import = "java.math.BigDecimal" %>
<%


                  Date today = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
String sqldate = formatter.format(today);

HashMap hashmap=(HashMap)session.getAttribute("info");
String header_home_image=(String)hashmap.get("md_header_home_image");
				  
		           java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
					 HashMap mapdate=(HashMap)session.getAttribute("adminToMdInfo");
					 System.out.println("mapdate is"+mapdate);
					String fullid=(String)session.getAttribute("completeId");
					


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("md_page_title")%></title>
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
body{font-family:"Trebuchet MS"; font-size:12px;}
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
	font-weight: bold; font-family:"Trebuchet MS";
}
.style4 {font-size:11px; font-weight:bold; font-family: "Trebuchet MS", Tahoma, Arial, Verdana;}
-->
</style>
</head>
<body  style="text-align:center">
<p>&nbsp;</p>

<table width="80%" border="0" cellpadding="1" cellspacing="1" align="center" bgcolor="#DBE5F1"style="border:solid #000000 1px">
  <tr>
    <td colspan="3" align="center" ><b>Acknowledgement Reciept</b></td>
  </tr>
  <tr>
    <td colspan="3" align="center" >&nbsp;</td>
  </tr>
  <tr>
    <td width="26%" class="Trebuchet_b"></td>
    <td width="30%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Service Name </font></b> </div></td>
    <td width="44%" class="Trebuchet_b"><div align="left">MD  TB-Taken </div></td>
  </tr>
  
  <tr>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Mode Of Payment</font></b> </div></td>
    <td width="46%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Mode_of_payment")%></div></td>
  </tr>
  <tr>
    <% String mode=(String)mapdate.get("Mode_of_payment");
	if(mode==null){
		mode="-";
	}
												
											if(mode.equalsIgnoreCase("Cheque/DD")) {%>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Card/Cheque/DD No.</font></b> </div></td>
    <td width="46%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Cheque_DD_No")%></div></td></tr>
	<tr>
    <% } 
											
											if(mode.equalsIgnoreCase("Online E-Transfer")||mode.equalsIgnoreCase("NEFT/RTGS")) {%>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Transaction ID</font></b> </div></td>
    <td width="46%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Bank_Tran_Id")%></div></td>
    <% } %>
  </tr>
  
  <tr>
    <%  
	mode=(String)mapdate.get("Mode_of_payment");
	if(mode==null){
		mode="-";
	
	}
	if(mode.equalsIgnoreCase("Online E-Transfer")||mode.equalsIgnoreCase("NEFT/RTGS")) {%>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Account Number</font></b> </div></td>
    <td width="46%" class="Trebuchet_b"><div align="left"><%=mapdate.get("Recieving_Bank_Acc_No")%></div></td>
    <% } %>
  </tr>
 
  <tr>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Transaction ID </font></b> </div></td>
    <td width="46%" class="Trebuchet_b"><div align="left"><%=mapdate.get("transactionNo")%>&nbsp;</div></td>
  </tr>
  <tr>
    <td width="19%" class="Trebuchet_b"></td><%
									SimpleDateFormat formatterB = new SimpleDateFormat("dd-MM-yyyy");
									SimpleDateFormat sdfSource=new SimpleDateFormat("yyyy-MM-dd");
								   	String Sdate =(String)mapdate.get("Request_Date");
									Date date = sdfSource.parse(Sdate);	
										String 	datGet=formatterB.format(date);
									/*Calendar c = Calendar.getInstance();
									c.setTime(date); // Now use today date.
									c.add(Calendar.DATE, 2); // Adding 5 days
								String	dateGet = formatterB.format(c.getTime());*/
								
								String Amount=(String)mapdate.get("Amount_To_Credit");
								double DsBalance = Double.parseDouble(Amount);
								BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
								BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	%>
    <td width="35%" height="25" class="trebuchet_font"><div align="left" class="style2" ><span class="Trebuchet_b"><b><font color="#000000" >&nbsp;Requested Date & Time</font></b></span></div></td>
    <td   class="Trebuchet_b"><div align="left"><%=datGet%> &nbsp; <%=mapdate.get("Request_Time")%></div></td>
  </tr>
  
  <tr>
    <td width="19%" class="Trebuchet_b"></td>
    <td width="35%" height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b>&nbsp;Transaction Amount </b></div></td>
    <td   class="Trebuchet_b" ><div align="left"><%=Balance%>
           
											
    </div></td>
  </tr>
  
  
  <tr>
    <td width="19%"   class="Trebuchet_b">&nbsp;</td>
    <td width="35%" height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Status</font></b></div></td>
    <td   class="Trebuchet_b" ><div align="left" >
      <%
												   String str=((String)mapdate.get("Status"));
												   if(str==null){
													   str="";
												   
												   }
												     System.out.println("status is==========="+str);
													
													%>
      <% if(str.equalsIgnoreCase("Accept")){ %>
      <font color="red"><%=str%></font>
      <%}%>
      <% if ( str.equalsIgnoreCase("decline")||( str.equalsIgnoreCase("declined"))) { 
													%>
      <font color="green">Declined</font>
      <%}%>
      <% if (str.equalsIgnoreCase("success")){ %>
      <font  color="#008000">Success</font>
      <%} %>
      <% if (str.equalsIgnoreCase("failure")){ 
													
													%>
      <font  color="blue">Failure</font>
      <%}
													%>
    </div></td>
  </tr>
  
  <tr>
    <td width="26%"   class="Trebuchet_b">&nbsp;</td>
    <td width="30%" height="25"  class="trebuchet_font">&nbsp;</td>
    <td width="44%"   class="Trebuchet_b">&nbsp;</td>
  </tr>
  <tr>
    <td   class="Trebuchet_b" colspan="5" align="center"><a href="javascript:printpage()" style=" border-width::0px;"><img src="images/print.gif" width="74" height="26" border="0px" /></a></td>
  </tr>
  <tr>
    <td width="26%"   class="Trebuchet_b">&nbsp;</td>
    <td width="30%" height="25" class="trebuchet_font">&nbsp;</td>
    <td   class="Trebuchet_b">&nbsp;</td>
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