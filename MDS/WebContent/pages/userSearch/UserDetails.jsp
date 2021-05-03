<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%> 
<%@ page import="javax.servlet.RequestDispatcher" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%

String clientId=(String)session.getAttribute("clientId");
ArrayList AgentList=(ArrayList)request.getAttribute("AgentList"); 
System.out.println("AgentList :"+AgentList);
if(AgentList==null){
AgentList=new ArrayList();
}


String message=(String)request.getAttribute("message");
if(message==null){
message="";
}


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: View Agents</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
</head>

<body><center>
<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1070">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

		  
  <tr>
    <td width="100%" valign="top">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="22%" valign="bottom" height="40" align="left" style="padding-left:20px" class="big">User Details</td>
	  
	   <td width="40%" valign="bottom" height="20" align="center" style="padding-left:20px" class="big"><font color="#FF0000" size="2"><%=message%></font></td><td width="38%" style="padding-right:20px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr><td align="right" valign="bottom" height="40"> 
		
	
	</td></tr>
	</table>
	</td></tr>
	</table>	</td>
    </tr>
	<tr>
    <td width="100%" valign="top"  style="padding-top:20px; padding-bottom:20px">
			</td>
    </tr>
	
	<%
	 int i=0;
	%>
  <form action="viewAgent.do" method="post" name="distform" id="distform">
 <input type="hidden" name="distributorids" id="allDistId1" />
<input type="hidden" name="param" value="" />

  <tr>
    <td width="100%" align="center" valign="top"><table cellspacing="1" cellpadding="1" width="96%" align="center" class="txt"  bgcolor="#b8cbe1">
      <tbody>
        <%
	  System.out.println("hello size is "+AgentList.size());
	   System.out.println("hellosize"+size);
	  
	   if(AgentList.size()>0){%>
        <tr  class="st" align="left" bgcolor="#dbe5f1">         
          <td width="2%" height="25"  align="center"><strong>S.N.</strong></td>
          <td width="6%" align="center"><strong>Agent ID</strong></td>
          <td width="16%"  align="center"><strong>Agent Email ID</strong></td>         
          <td width="7%" align="center"><strong>Mobile No. </strong></td>  
		  <td width="7%" align="center"><strong>MPIN</strong></td>        
          <%if(flag.equals("0")){%>
          <td  width="5%" align="center"><strong>Status</strong></td>
          <%} if(flag.equals("1")){%>
          <td  width="7%" align="center"><strong>Status</strong></td>
          <%} if(flag.equals("2")){%>
          <td  width="7%" align="center"><strong>Status</strong></td>
          <td  width="10%" align="center"><strong>Status</strong></td>
          <%}%>
          
        </tr>
        <%for( i=0;i<size;i++){
      	HashMap temp=(HashMap)AgentList.get(i);
	
	
	
		// 	System.out.println("inside distributor_id Button :" +temp.get("distributor_id") );
	
	
	%>
        <tr bgcolor="#FFFFFF">
          <td align="center" height="25px">
		 
		  <%								
							
								// System.out.println("distributor_id "+temp.get("distributor_id") +"   i is   n"+i);
									 //  System.out.println("login_status "+temp.get("mobileStatus"));
									   
									 String Status=(String)temp.get("login_status");
									 if(Status.equalsIgnoreCase("Activate")){
									 Status="Active";
									 }else{
									 Status="Deactive";
									 }
									 String mpin=(String)temp.get("mpin");
									 if(mpin==null){
									 mpin="NA";
									 }
									   
%>
          <td  align="center"><%=i+1%></td>
          <td  align="center"><%=temp.get("distributor_id")%></td>
          <td  align="center"><%=temp.get("email_id")%></td>                
          <td align="center"><%=temp.get("mobile")%></td>  
		    <td align="center"><%=mpin%></td>          
          <%
		   String mobileStatus=(String)temp.get("mobileStatus");
		   
		   if(mobileStatus==null){
		   mobileStatus="Deactive";
		   }else if(mobileStatus.equalsIgnoreCase("Activate") || mobileStatus.equalsIgnoreCase("Active") ){
		   mobileStatus="Active";
		   }else{
		      mobileStatus="Deactive";
		   }
		   		   		
		   if(flag.equals("0")){%>
          <td align="center"><%=Status%></td>
          <%} if(flag.equals("1")){%>
          <td align="center"><%=mobileStatus%></td>
          <%} if(flag.equals("2")){%>
          <td align="center"><%=Status%></td>
          <td align="center"><%=mobileStatus%></td>
          <%}%>
		 
        </tr>
      </tbody>
      <input type="hidden" name="allDistId" id="allDistId" value="" />
      <%}%>
    </table></td>
  </tr>
  <tr><td height="40" align="center">
 

  </tr>
   	</form>			   

				 
</table>
</td>
</tr>
 <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>

</center>
</body>
</html>


