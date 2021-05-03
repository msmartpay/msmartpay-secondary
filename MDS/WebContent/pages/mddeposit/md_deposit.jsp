<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
 Date today = new Date();
 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 String curdate = formatter.format(today);
 int size=0;
 
HashMap hm=new HashMap();
ArrayList list=(ArrayList)session.getAttribute("list");
if (list==null)
{
 	size=-1;
}
else{
 	size=list.size();
}

String result=(String)request.getAttribute("result");
String DepMessage="";
if(result==null)
{
DepMessage="";

}
else
{
DepMessage=result;
}

%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>::T B Request</title>
<link href="images/icon.png" rel="icon" /><link href="css/mob_admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="script/menuscript.js"></script>

<script type="text/JavaScript">
<!--
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
<script language="JavaScript">



var message="No right-click allowed";
///////////////////////////////////
function clickIE() {if (document.all) {(message);return false;}}
function clickNS(e) {if 
(document.layers||(document.getElementById&&!document.all)) {
if (e.which==2||e.which==3) {(message);return false;}}}
if (document.layers) 
{document.captureEvents(Event.MOUSEDOWN);document.onmousedown=clickNS;}
else{document.onmouseup=clickNS;document.oncontextmenu=clickIE;}

document.oncontextmenu=new Function("return false")

</script>
<SCRIPT LANGUAGE="JavaScript">

<!-- Begin
function textCounter(field, countfield, maxlimit) {
if (field.value.length > maxlimit) // if too long...trim it!
field.value = field.value.substring(0, maxlimit);
// otherwise, update 'characters left' counter
else 
countfield.value = maxlimit - field.value.length;
}
// End -->
</script>

<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
<SCRIPT type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>

<style>
.my_text{ font-family:'Trebuchet MS'; font-size:12px;}
.my_text option{font-family:'Trebuchet MS'; font-size:12px;}
</style>

</head>

<body><table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr><td width="100%" height="146"><%@ include file="../../header.jsp" %></td></tr>

  <tr>
    <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td valign="top" align="center" width="11%" style="padding-left:20px">&nbsp;</td>
    <td align="center" width="89%" valign="top" style="padding-top:20px"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr>
    <td height="20" class="indtop1" valign="middle"  align="left"><span class="big" style="padding-left:25px">My Deposit</span></td>
  </tr>
    <tr><td height="38" class="indcen" valign="top" width="1000" align="left" style="padding-left:25px">
	
					<table width="1000" border="0" cellspacing="0" cellpadding="0" align="left">
 					<tr><td colspan="4" height="10" align="center"><font color="red" size="2"><%=DepMessage%></font></td></tr>
		  
  
						<tr>
							<td height="25"></td></tr>
           	
								  <tr><td width="100%" align="center" valign="top">
								  <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="border" >
								  		<tr>
								  			<td width="100%" align="center">
  											<form  name="transferForm" method="post" action="mdDepositRequest.do?param=saveDeposit" id="transferForm" >
  											<table width="80%" border="0" align="center" style="font-family:'Trebuchet MS'"  cellpadding="0" cellspacing="0" class="txtt">
                                              <tbody>
											  <tr><td colspan="4" height="10" align="center"></td></tr>
                                                <tr><td width="15%"></td>
                                                  <td align="left" width="30%" height="25"><strong>Master Distributor  Name </strong></td>
                                                  <td align="left" width="4%">:</td>
                                                  <td align="left" width="51%"><%=session.getAttribute("mdName")%></td>
                                                </tr>
                                                 <tr><td></td>
                                                  <td height="25" align="left"><strong>Current Balance Amount</strong></td>
                                                  <td height="25" align="left">:</td>
                                                  <td align="left">Rs. <%=FinalBalance%></td>
                                                </tr>
                                                <tr><td></td>
                                                  <td height="25" align="left"><strong>Request for Amount</strong></td>
                                                  <td height="25" align="left">:</td>
                                                  <td align="left">
                                                  <input required type="text" style="width:155px;font-family:'Trebuchet MS'; font-size:12px; color:#000000;"  class="txt2b my_text"  name="amount" onKeyPress="return digitonly(this,event)" maxlength="10" /></td>
                                                </tr>
                                                
                                                <tr bgcolor="#FFFFFF"><td></td>
                                                  <td height="25" align="left"><strong>Payment Date</strong></td>
                                                  <td height="25" align="left">:</td>
                                                  <td align="left">
												  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                      <tr>
                                                        <td><input type="text" value="<%=curdate%>"  name="cashDate" style="width:155px;font-family:'Trebuchet MS'; font-size:12px; color:#000000;"  class="txt2b my_text" id="cashDate" readonly="" />
                                                            <img src="images/cal.jpg" alt="Calendar" width="15" height="17" onClick="displayCalendar(document.transferForm.cashDate,'yyyy-mm-dd',this)" />                                                               </td>
                                                    </tr>
                                                  </table></td>
                                                </tr>
                                                <tr><td></td>
                                                  <td height="25" align="left"><strong>Payment Mode</strong></td>
                                                  <td height="25" align="left">:</td>
                                                  <td align="left">
                                                  <select style="width:155px;font-family:'Trebuchet MS'; font-size:12px; color:#000000;"  class="txt2b my_text"   NAME="payMode" >
													<option selected="selected" value="Cash">Cash</option>
													<option value="IMPS">IMPS</option>
													<option value="NEFT">NEFT</option>
													<option value="RTGS">RTGS</option>
													<option value="UPI">UPI</option>
                                                  </select>
                                                  </td>
                                                </tr>
                                                <tr><td width="15%"></td>
                                                  <td width="30%" height="25" align="left"><strong>Receiving Bank Name</strong></td>
                                                  <td width="4%" height="25" align="left">:</td>
                                                  <td align="left"  width="51%">
                                                  <select required style="width:155px;font-family:'Trebuchet MS'; font-size:12px; color:#000000;" class="txt2b my_text"  name="cbRecBankName" onchange="inputData1()">
												  
												  	<%
												  	ArrayList<HashMap<String,String>> collectionBanks=(ArrayList<HashMap<String,String>>)request.getAttribute("collectionBanks");
												  	if(collectionBanks!=null && collectionBanks.size()>0){
												  	 
												  		for(int j=0;j<collectionBanks.size();j++){
												  			HashMap<String,String> bank=collectionBanks.get(j);
												  	 %>
												  		<option value="<%=bank.get("bank_name")%>"><%=bank.get("bank_name")%> <%=bank.get("bank_account")%> <%=bank.get("bnk_ifsc")%></option>
												  
												  	<%}}%>
												  </select>
												  </td>
                                                </tr>	
												<tr>
													<td></td>
                                                  <td height="25" align="left"><strong>Remarks</strong></td>
                                                  <td height="25" align="left">:</td>
                                                  <td align="left">
                                                    <textarea name="cdremark"   class="txt2b" style="width:150px;" onKeyDown="textCounter(this.form.cdremark,this.form.remLen1,100);" onKeyUp="textCounter(this.form.message,this.form.remLen,100);" onkeypress="return checkChar(event);"></textarea>
													<br>
													<input readonly style="width:15px;font-family:'Trebuchet MS'; font-size:12px; color:#000000;"  type="text" name="remLen1" size=3 maxlength=3 value="165"> characters left

                                                  </span></td>
                                                </tr>
                                                
												
                                               
												<tr>
												    <td></td>
                                                 	<td height="25" align="left"></td>
	                                                  <td height="25" align="left"></td>
	                                                  <td align="left">
	                                                  <a href="#" style="text-decoration:none" >
	                                                  <input  type="submit" value="Submit" class="txtt" onclick="submitfrm()" style="width:100px" /> 
	                                                  </a>
	                                                  </td>
                                                </tr>
                                           
											</tbody>
											</table>
											</form>
											</td>
										</tr>
								</table>
							</td>
						</tr>
   	
				   
					<tr><td height="10" colspan="100%"></td></tr> 
					<tr><td height="30" colspan="100%" style="font-family:'Trebuchet MS';font-size:14px"> <strong>My Deposit Details</strong></td></tr> 
					<tr><td height="10" colspan="100%"></td></tr> 
					<tr><td colspan="100%">
					<table cellspacing="1" cellpadding="1" width="100%" align="center" class="txt"  bgcolor="#b8cbe1">
      				<tbody>
       				<%
					ArrayList getMdJournal=(ArrayList)request.getAttribute("getMdJournal");
					String data="";
					int i=0;
 					if (getMdJournal==null)
				 	{
						 size=-1;
						 data="NO DATA";
				 	}
				    else{
						 size=getMdJournal.size();
				  	}

	
					if(size<=0 || data.equalsIgnoreCase("NO DATA") ){%>
						<tr><td height="20"></td></tr>
						<tr><td height="20" align="center"><font color="#FF0000" size="3">Data is Not Available.</font></td></tr> 
						<tr><td height="20"></td></tr>
						<tr><td height="80"></td></tr>
					<%}else{%> 
	   

			        <tr  class="st" align="left" bgcolor="#dbe5f1">        
			          <td width="2%" height="35"  align="center"><strong>S.N.</strong></td>
			          <td width="6%" align="center"><strong>Date</strong></td>
			          <td width="10%"  align="center"><strong>Time</strong></td>         
			          <td width="10%" align="center"><strong>Amount</strong></td>  
					  <td width="10%" align="center"><strong>Mode Of Payment</strong></td>        
					    <td width="10%" align="center"><strong>Request Status</strong></td>        
			       
			        </tr>
			        <%
			        	for( i=0;i<size;i++){
				      	HashMap temp=(HashMap)getMdJournal.get(i);
						String ModeOfPayement=(String)temp.get("Mode_of_payment");
						
						String Status=(String)temp.get("Status");
						
						if(Status.equalsIgnoreCase("Accepted") || Status.equalsIgnoreCase("success") || Status.equalsIgnoreCase("Accept")){
						Status="Success";
						}
						else if(Status.equalsIgnoreCase("Decline") ){
						Status="Declined";
						}
						else{
						Status=Status;
						}
					
					 
					%>
			        <tr bgcolor="#FFFFFF">
			         
			          <td height="35" align="center"><%=(i+1)%></td>
			          <td  align="center"><%=temp.get("date")%></td>
			          <td  align="center"><%=temp.get("time")%></td>                
			          <td align="center"><%=temp.get("amount")%></td>  
					    <td align="center"><%=ModeOfPayement%></td>           
						   <td align="center"><%=Status%></td>           
			        </tr>
			      </tbody>
			      <%}%>
				  <%}%>
    			</table>
				</td></tr>
		
				<tr><td height="20"></td></tr> 
		</table>


	</td></tr>

 

</table>
</td>
  </tr>
</table>
</td>
  </tr>
  <tr><td height="25"></td></tr>
    <tr><td width="100%" height="50"><%@ include file="../../footer.jsp" %></td></tr>

</table>

</body>
</html>
