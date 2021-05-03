<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import = "java.math.BigDecimal" %>
<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.* "%> 
<%@ page import="java.util.Date" %>

<%
HashMap hashmap=(HashMap)session.getAttribute("info");
String header_home_image=(String)hashmap.get("md_header_home_image");

                  Date today = new Date();
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
String sqldate = formatter.format(today);
				  
		           java.sql.Time sqltime = new java.sql.Time(new java.util.Date().getTime());
					 HashMap mapdate=(HashMap)session.getAttribute("mdToDistInfo");
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
.border{ border:#b8cbe1 solid 2px;}
-->
</style>
<link href="css/insidecss.css" rel="stylesheet" type="text/css" />
<link href="css/Style.css" rel="stylesheet" type="text/css" />
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
body{font-size:12px; font-family:"Trebuchet MS" ; text-align:center}
.style2 {font-family: "Trebuchet MS", Tahoma, Arial, Verdana}
.style3 {font-size: 18px;font-weight: bold; font-family:"Trebuchet MS";}
.style4 {font-size:11px; font-weight:bold; font-family: "Trebuchet MS", Tahoma, Arial, Verdana;}
.style4 a{text-decoration:underline}
.style4 a:hover{text-decoration:none}
-->
</style>
</head>
<body style="text-align:center; margin-top:10px">

<table width="500" border="0" cellpadding="0" cellspacing="0" align="center" style="border-bottom:1px solid #033D7D">
		<tr><td width="30%"  height="31" align="left" class="trebuchet_font" ></td>
			<td width="70%" colspan="4">
					
		
		    <p>&nbsp;</p></td>
		</tr>
</table>
											
		
						
						
<table width="500" border="0" cellpadding="0" cellspacing="0" align="center" class="border" bgcolor="#DBE5F1">
  <tr>
    <td colspan="4" align="center" style="height:30px; border-bottom:2px solid #b8cbe1;" class="style3"><b>Acknowledgement Reciept</b></td>
  </tr>
  <tr>
    <td colspan="4" align="center" >&nbsp;</td>
  </tr>
  <tr>
    <td width="10%" class="Trebuchet_b"></td>
    <td  width="35%" height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Service Name </font></b> </div></td>
	<td width="5%">:</td>
    <td width="50%" class="Trebuchet_b"><div align="left">DS TB- Transfer</div></td>
  </tr>
  <tr>
    <td class="Trebuchet_b"></td>
    <td height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >Transaction ID </font></b> </div></td>
    <td>:</td>
	<td class="Trebuchet_b"><div align="left"><%=mapdate.get("transactionId")%></div></td>
  </tr>
  <tr>
    <td class="Trebuchet_b"></td>
    <td height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Distributor ID</font></b> </div></td>
	<td>:</td>
    <td class="Trebuchet_b"><div align="left"><%=mapdate.get("DSID")%></div></td>
  </tr>

  <tr>
    <td class="Trebuchet_b"></td>
    <td height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Distributor Name</font></b> </div></td>
	<td>:</td>
    <td class="Trebuchet_b"><div align="left"><%=mapdate.get("DSName")%>&nbsp;</div></td>
  </tr>
  <tr>
    <td class="Trebuchet_b"></td>
	<%
				SimpleDateFormat formatterB = new SimpleDateFormat("dd-MM-yyyy");
              	Date Sdate =(Date) mapdate.get("Request_Date");  
					String 	datGet=formatterB.format(Sdate);  	
				/*Calendar c = Calendar.getInstance();
				c.setTime(Sdate); // Now use today date.
				c.add(Calendar.DATE, 2); // Adding 2 days
				String dateGet = formatterB.format(c.getTime());*/
				
								String Amount=(String)mapdate.get("Amount_To_Credit");
								double DsBalance = Double.parseDouble(Amount);
								BigDecimal DsBlanceAmount = new BigDecimal(DsBalance);
								BigDecimal Balance = DsBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	%>
    <td height="25" class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b><font color="#000000" >&nbsp;Requested Date And Time </font></b> </div></td>
	<td>:</td> 
    <td class="Trebuchet_b"><div align="left"><%=datGet%>&nbsp;<%=mapdate.get("Request_Time")%></div></td>
  </tr>

  <tr>
    <td class="Trebuchet_b"></td>
    <td height="25"  class="trebuchet_font"><div align="left" class="Trebuchet_b" ><b>&nbsp;Transaction Amount </b></div></td>
	<td>:</td>
    <td   class="Trebuchet_b" ><div align="left"><%=Balance%>
    </div></td>
  </tr>
  
  
  <tr>
    <td  class="Trebuchet_b">&nbsp;</td>
    <td height="25"  class="Trebuchet_b" ><div align="left" class="trebuchet_font"><b><font color="#000000">&nbsp;Status</font></b></div></td>
	<td>:</td>
    <td   class="Trebuchet_b" ><div align="left" >
      <%
												   String str=((String)mapdate.get("Status"));
												   System.out.println("status is==========="+str);
												   if(str.equalsIgnoreCase("accepted")){
												   str="Accepted";
												   }else if(str.equalsIgnoreCase("declined")){
												    str="Declined";
												   }else{
												   str="Success";
												   }
													
													%>
      <% if(str.equalsIgnoreCase("Accepted")){ %>
      <font color="red"><%=str%></font>
      <%}%>
      <% if ( str.equalsIgnoreCase("decline")||( str.equalsIgnoreCase("declined"))) { 
													%>
      <font color="green">Declined</font>
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
    <td class="Trebuchet_b">&nbsp;</td>
    <td height="25"  class="trebuchet_font">&nbsp;</td>
	<td></td>
    <td class="Trebuchet_b">&nbsp;</td>
  </tr>
  <tr>
    <td   class="Trebuchet_b" colspan="5" align="center"><a href="javascript:printpage()" style=" border-width::0px;"><img src="images/print1.png" width="60" height="20" border="0px" /></a></td>
  </tr>
  <tr>
     <td   class="Trebuchet_b" colspan="3">&nbsp;</td>
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