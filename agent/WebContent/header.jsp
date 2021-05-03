<%@page import="com.hemresdmr.HeremsDMRDao"%>
<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%

String message=(String)request.getAttribute("message");
if(message==null)message="";
HashMap AgentDetailData=(HashMap)session.getAttribute("AgentDetailData");
String completeAgentID=(String)AgentDetailData.get("completeAgentID");
String agentID=(String)AgentDetailData.get("agentID");
String agentBalance=(String)session.getAttribute("AgentBalance");
String userName=(String)AgentDetailData.get("userName");
String agencyName=(String)AgentDetailData.get("agencyName");
String ipaddress=(String)request.getRemoteAddr();
String mob_pass=(String)AgentDetailData.get("mob_pass");

HeremsDMRDao dao=new HeremsDMRDao();

HashMap<String,String> mapdetails=dao.getUserDetails(agentID);

String AgentCode="F"+mapdetails.get("AgentId");
String Userid=mapdetails.get("Pass");
String SecurityID=mapdetails.get("Pass"), MerchantID="180",Terminalid="100180", LoginKey="5386545992";
String credentials=AgentCode+"~"+Userid+"~"+SecurityID+"~"+ MerchantID+"~"+Terminalid+"~"+LoginKey;
System.out.println(" credentials : "+credentials);
//String encodedString=GenerateIdDao.SHAHashing(credentials);
byte[] bytesEncoded = Base64.encodeBase64(credentials .getBytes());

String encodedString=new String(bytesEncoded);
%>

<script>
    $(document).ready(function() {
        function disableBack() {
	$('form').get(0).reset();
	 window.history.forward() 
	}

        window.onload = disableBack();
        window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
    });
</script>


<script type="text/javascript">

document.body.style.zoom="90%";
function activatealert(){
	alert('To activate this service please contact to your Channel Partner.');
}

$(document).ready(function(){
    getBalance();
});

function getBalance(){
	$.ajax({
	    type: "GET",
	    url: "Getpage.action?param=getBalance",
	   success: function (response) {
		   if(response!=null && response!=101){
		   	$('.balance_box').text(response);
		   }
	},
	failure: function (response) {
		
    }
});
}



</script>
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate"/>
		<meta http-equiv="Pragma" content="no-cache"/>

<link href="images/favicons.png" rel="shortcut icon" />

	<header class="header header-menu-fullw">

	<!-- Header Top Bar -->
	<div class="header-top">
		<div class="container" style="width:90%;">
			
			<div class="header-top-right">
				<button class="btn btn-sm btn-default menu-link menu-link__tertiary">
					<i class="fa fa-user"></i>
				</button>
				<div class="menu-container">
					<ul class="header-top-nav header-top-nav__tertiary">
                            <li><a><i class="fa fa-hand-rock-o"></i> Welcome : 
                              	<span class="logoclr">${sessionScope.AgentDetailData.agencyName }</span></a>
                            </li>
                            <li><a><i class="fa fa-bell-slash"></i> Agent ID : <span class="logoclr"> ${sessionScope.AgentDetailData.completeAgentID } </span></a></li>
                            <li><a><i class="fa fa-rupee"></i> Balance : <span class="logoclr balance_box"> ${sessionScope.AgentBalance } </span></a></li>                               								
							<li><a href="doLoginAction.action?param=logout"><i class="fa fa-sign-out"></i> Log Out</a></li>
                              
                              
                              <!--<li><a href="page-login.html"><i class="fa fa-user-plus"></i> Register</a></li>-->
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- Header Top Bar / End -->
	<div class="header-main">
		<div class="container" style="width:90%;">
			<!-- Logo -->
			<div class="logo">
				<a href="Getpage.action?param=getPage"><img src="images/${sessionScope.dynamicData.headerHomeImage }" alt="Logo" height="61" width="180"></a>
				<!-- <h1><a href="index.html"><span>Handy</span>Man</a></h1> -->
			</div>
			<!-- Logo / End -->

			<button type="button" class="navbar-toggle">
				<i class="fa fa-bars"></i>
			</button>

			<!-- Navigation -->
			<nav class="nav-main">
				<div class="nav-main-inner">
					<ul data-breakpoint="992" class="flexnav">
						<li><a href="Getpage.action?param=getPage">Home</a></li>
						
						<c:if test="${sessionScope.api_status eq 0 }">
						
							<c:if test="${sessionScope.AgentDetailData.Recharge eq 'Y'}">
								<li><a href="domobileDthRecharge.action?param=getPage">RECHARGE</a></li>
							</c:if>
							<c:if test="${sessionScope.AgentDetailData.Utility eq 'Y' }">
								<li><a href="utilityService.action?param=UtilityPage">UTILITY</a></li>
							</c:if>
							<c:if test="${sessionScope.AgentDetailData.Rail eq 'Y' }">
								<li><a href="aepsAtivation.action">AEPS</a></li>
							</c:if>
							<c:if test="${sessionScope.AgentDetailData.DMR eq 'Y' }">
								<c:if test="${sessionScope.DMRVendor eq 'DMR1' }">
									<li ><a href="dmr.action?param=Money Trasfer">DMR1</a></li>
								</c:if>
								<c:if test="${sessionScope.DMRVendor eq 'DMR2' }">
									<li ><a href="dmreko.action?param=Money Trasfer">DMR2</a></li>
								</c:if>
								<!-- <li ><a href="dmr.action?param=Money Trasfer">DMR1</a></li>
								<li ><a href="dmreko.action?param=Money Trasfer">DMR2</a></li> -->
								
							</c:if>
							<c:if test="${sessionScope.AgentDetailData.DMR eq 'N' }">
								<li><a href="javascript:alert('To use this service contact to channel partner.');" >DMR </a></li>
							</c:if>
									
							<c:if test="${sessionScope.AgentDetailData.DTHX eq 'Y' }">
								<li><a href="dthConnection.action?param=DthConnection">DTH Connection</a></li>	
							</c:if>
							<li><a href="common.action?param=GTOC">G2C</a></li>
							<li><a href="#">TRAVEL</a>
							
							
								<ul>
									<c:if test="${sessionScope.AgentDetailData.Flights eq 'Y' || sessionScope.AgentDetailData.Flights eq 'N' }">
										<li><a href="javascript:alert('Coming Soon');"> Flight</a></li>
									</c:if>
									<c:if test="${sessionScope.AgentDetailData.Bus eq 'Y' || sessionScope.AgentDetailData.Bus eq 'N'  }">
										<li><a href="javascript:alert('Coming Soon');"> Bus</a></li>
									</c:if>
									<c:if test="${sessionScope.AgentDetailData.Hotel eq 'Y' || sessionScope.AgentDetailData.Hotel eq 'N'  }">
										<li><a href="javascript:alert('Coming Soon');"> Hotel</a></li>
									</c:if>
									<c:if test="${sessionScope.AgentDetailData.Hotel eq 'Y' || sessionScope.AgentDetailData.Hotel eq 'N'  }">
										<li><a href="javascript:alert('Coming Soon');"> Tour Packages</a></li>
									</c:if>
	                                      								
								</ul>
							</li>
						</c:if>
						
						<li><a href="#">CONTROL PANEL</a>
                        	<ul>
								<li><a href="docontrolPanel.action?param=editProfile"> View / Edit Profile</a></li>
                                <li><a href="docontrolPanel.action?param=pwdChangePage"> Password Change</a></li>
                                <li><a href="w2w.action?param=wbgetpage"><strong> Balance Request</strong></a></li>
                                 
                                <c:if test="${sessionScope.AgentDetailData.W2W eq 'Y' }">
                                	<li><a href="w2w.action?param=getpage"> Wallet Transfer</a></li>
                                </c:if>
                                <li><a href="doAccountStatement.action?param=accountStatementPage"> Account Statement </a></li>
								 <li><a href="doAccountStatement.action?param=SenderAccountStatementPage">Sender Account Statement </a></li>
                                <li><a href="doTranStatusCheck.action?param=transactionStatusPage"> Transaction Status </a></li>	
                                <li><a href="doAccountStatement.action?param=downloadReportPage"> Download Report </a></li>	
                                <li><a href="ticket.action?param=ticketPage">View Ticket Status</a></li>
                                <c:if test="${sessionScope.api_status eq 1 }">
                                	 <li><a href="authdetails.action">API Details</a></li>
                                	 <li><a href="api_business.action">API Business</a></li>
                                </c:if>								
							</ul>
                       </li>								
						
					</ul>
				</div>
			</nav>
			<!-- Navigation / End -->

		</div>
	</div>
	
</header>