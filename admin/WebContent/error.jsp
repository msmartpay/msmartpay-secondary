<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.*,com.db.DBConnection" %>
<%
HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
String companyName=dynamicData.get("companyName");
String mobileNo1=dynamicData.get("mobileNo1");
String helpEmailID=dynamicData.get("helpEmailID");
String userType=dynamicData.get("userType");
String tickerMessage=dynamicData.get("tickerMessage");
String headerHomeImage=dynamicData.get("headerHomeImage");
String powerBy=dynamicData.get("powerBy");
String clientId=dynamicData.get("clientId");
if(powerBy==null){
powerBy="";}

String message=(String)request.getAttribute("message");
if(message==null)message="";
HashMap AgentDetailData=(HashMap)session.getAttribute("AgentDetailData");
String completeAgentID=(String)AgentDetailData.get("completeAgentID");
String agentID=(String)AgentDetailData.get("agentID");
String userName=(String)AgentDetailData.get("userName");
String agencyName=(String)AgentDetailData.get("agencyName");
String agentBalance=(String)session.getAttribute("AgentBalance");
String Flights=(String)AgentDetailData.get("Flights");
String Bus=(String)AgentDetailData.get("Bus");
String Hotel=(String)AgentDetailData.get("Hotel");
String Recharge=(String)AgentDetailData.get("Recharge");
String DTHX=(String)AgentDetailData.get("DTHX");
String Utility=(String)AgentDetailData.get("Utility");
String PayCard=(String)AgentDetailData.get("PayCard");
String TestMerit=(String)AgentDetailData.get("TestMerit");
String Rail=(String)AgentDetailData.get("Rail");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title></title>
<link href="images/favicon.gif" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="css/pstyle.css"/>

<script>
function submitForm()
{
	
var val1 = document.getElementById("first_val").value;
var val2 = document.getElementById("second_val").value;
if(val2==""){
	alert("Please Set a Markup Value.");
}
if(val1 < val2)
{
alert("Please Enter Low Value to Markup Limit.");

}
else{return true;}

}

function isNumber(evt) {
evt = (evt) ? evt : window.event;
var charCode = (evt.which) ? evt.which : evt.keyCode;
if (charCode > 31 && (charCode < 48 || charCode > 57)) {
return false;
}
return true;

}
</script>

</head>
<%

System.out.println("AG ID---- 1 >>>>>>>>>>>>>>>>>>>>>>>>>>"+agentID);
float mklimit=0;
Connection con=null;
ResultSet rs=null;
Statement smt=null;
try{
	
	DBConnection dbcon=new DBConnection();
	
	con=dbcon.getConnection();
	
	
	String query="select MarketLimit from service_markup where id in (select mds_id from agent_details where agent_id='"+agentID+"')";
	
	smt=con.createStatement();
	
	
	rs=smt.executeQuery(query);
	if(rs.next()){
		mklimit=rs.getFloat("MarketLimit");
		
	}else{
		System.out.println("Can not set value.");
	}
	
	

%>

<body>
	
	<div id="page_wrap">
 
 		 <div id="container_page" class="box_shadow">
						
				<div class="heder_wrap">
    <div class="header"> 
      <div class="wrap_logo"><a href="domobileDthRecharge.do?param=getPage" title="logo"><p><img width="163px" height="69px" src="images/<%=headerHomeImage%>" /></p></a></div><!--end of logo-->
      <div class="right_hder_wrap" style="margin:0;">
         <!--start of user detail--> 
         <div class="wrap_userDetail">
            <table class="user_detl" cellpadding="0" cellspacing="0">
               <tr>
                 <td>
                   <p>Partner ID :  <span><%=completeAgentID%></span></p>
                 </td>
                 <td>
                  <p>Available Balance :  <span><%=agentBalance%></span></p>
                 </td>
               </tr>
               
               <tr>
                  <td colspan="2"><p class="wel_txt">Welcome <span><%=agencyName%></span></p></td>
               </tr>     
            </table>
            
         </div>
         <!--end of user_detail-->
         
         <div class="clear"></div>
         

         
      </div>
      <!--end of rght_hder_wrap-->      
            
    </div>
    <!--end of header-->
    <div class="clear"></div>
   
    <div class="wrap_menu"> 
    
      <ul>
      <%if("Y".equalsIgnoreCase(Recharge)){%>
        <li><a href="domobileDthRecharge.do?param=getPage">Home</a></li> </li>
		<%}%>
		<%if("Y".equalsIgnoreCase(Recharge)){%>
        <li class="mobile_id "><a href="domobileDthRecharge.do?param=getPage">Recharge</a></li>
	  <%}%>
        <%if("Y".equalsIgnoreCase(Flights)){%>
      	<li class="mobile_id "><a href="http://intlair.travelepoint.in/User/signin.aspx?user_id=<%=userName%>">Flights</a></li>
	  <%}%>
        <%if("Y".equalsIgnoreCase(Bus)){%>
		<li class="mobile_id "><a href="http://bus.travelepoint.in/booking/buslogin.aspx?user_id=<%=userName%>">Bus</a></li>
	  <%}%>
        <%if("Y".equalsIgnoreCase(Hotel)){%>
		<li class="mobile_id "><a href="http://hotel.travelepoint.in/hotelbooking/Hotellogin.aspx?user_id=<%=userName%>">Hotel</a></li>
	  <%}%>
         <%if("Y".equalsIgnoreCase(Utility)){%>
		<li class="mobile_id "><a href="utilityService.do?param=UtilityPage">Utility</a></li>
	  <%}%>
	   <%if("Y".equalsIgnoreCase(PayCard)){%>
		<li class="mobile_id "><a href="PrepaidCard.do?param=PayCardPage">PayCard</a></li>
	    <%}%>
	    <%if("Y".equalsIgnoreCase(TestMerit)){%>
		<li class="mobile_id "><a href="TestMeritaction.do?param=TestMeritPage">TestMerit</a></li>
	    <%}%>
	    <%if("Y".equalsIgnoreCase(DTHX)){%>
        <li><a href="dthConnection.do?param=DthConnection">DTH-X</a></li> 
	    <%}%>
        <li><a href="doLoginAction.do?param=logout">Sign Out</a></li> 
      </ul> 
      
        
    </div>
    <!--end of menu_wrap--> 
   </div>
				
		<div class="clear"></div>
      <!--start of middle_login_contain-->
      <div class="login_wrap_container">
        
		       <div class="login_wrp">
		                  
			       <ul>
			        <li> <a href="#" class="icon1">Affiliate Report</a>
			        
			         <ul class="submenu1">
			        <li><a href="#">Visitor</a></li>
			        <li><a href="#">Booked</a></li>
			        <li><a href="#">Earning</a></li>
			        <li><a href="#">Stayed</a></li>
			        </ul>
			        
			        </li>
			        <li> <a href="#" class="icon2">Report</a>
			        
			         <ul class="submenu2">
			        <li><a href="doAccountStatement.do?param=accountStatementPage">Account Statement</a></li>
			        <li><a href="doTranStatusCheck.do?param=transactionStatusPage">Transaction Status</a></li>
			        <li><a href="doAccountStatement.do?param=downloadReportPage">Download Report</a></li>
			        </ul>
			        
			        </li> 
			        
			        <li><a href="#" class="icon3">Transfer Request</a>
			        
			        <ul class="submenu3">
			        <li><a href="doTransferRequest.do?param=transferRequest">Transfer Request</a></li>
			        </ul>
			        
			        </li>
			         <li><a href="#" class="icon4">Control Panel</a>
			         
			         <ul class="submenu4">
			        <%if(!"AGUP281303".equalsIgnoreCase(completeAgentID)){%>
			                    <li><p><a href="docontrolPanel.do?param=editProfile">View/Edit Profile</a></p></li>
							  <%}%>	
			        <li><a href="docontrolPanel.do?param=pwdChangePage">Password Change</a></li>
			        </ul>
			         
			         </li>
			          <li><a href="#" class="icon5">Edit Markup</a>
			          
			          <ul class="submenu5">
			        
			        <li><a href="doMarkupAction.do?param=markup">Set Markup</a></li>
			        </ul>
			          
			          </li>
			        
			        
			       </ul>  
        
        	</div>
        
        <!--end of login_wrp-->
     
		      <div class="right_div"> <!--right_div start-->
		   
		            <h1>Set Markup </h1>
		                  <p align="center"><font color="#FF0000" size="3">You can not set Markup right now. Please try after sometime.</font></p>
		               <div class="login_form_wrp">
		              
		              	   <form action="doMarkupAction.do" method="post" name="markuppage">
		              
		              			<input type="hidden" name="param" value="SetMarkup" />
		              			
			                    <table class="form_tble"  cellpadding="0" cellspacing="0" align="center" border="0">
				                  
				                   <tr>
				                   <td class="markupcls"> </td>
				                      <td width="15%"><strong>Your Market Limit</strong></td>
				                      
				                      <td width="63%">
				                        <input readonly="readonly" id="first_val" value="<%=mklimit %>" name="marketlimit" style="width:180px" type="text" required="true"/>
				                      </td>
				                      
				                      
				                      <td class="setcls"> </td>
				                   </tr>  
				                   
				                    <tr>
				                     <td width="30%"> </td>
				                      <td width="15%"><strong>Set Markup</strong></td>
				                      
				                      <td class="markupcls">
				                        <input name="mkup" id="second_val" style="width:180px" type="text" required="true"/>
				                      </td>
				                      
				                      <td class="setcls"> </td>
				                   </tr>
				                   <tr>
				                   		<td width="30%"> </td>
				                   		<td></td>
				                   		<td width="15%"> <input type="submit" class="btn_sign" value="Submit" onclick="submitForm()" /> </td>
				                   		<td class="setcls"> </td>
				                   </tr>   
			              
			               		 </table>
		                
		                  </form>
		               
		               </div>
		               <!--end of login_form-->
		        	 </div>
		        	 <!--end right_div-->
		        </div>
		  <!--end of container_page-->

	</div>
	<!--end of page wrap-->
	
  </div>     
  
 </body>

</html>
<%

}catch(Exception e){
	e.printStackTrace();
}finally{
	if(rs!=null){
		rs.close();
	}
	if(rs!=null){
		smt.close();
	}
	if(rs!=null){
		con.close();
	}
}

%>