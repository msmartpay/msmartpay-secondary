<%@ page import="com.login.LoginDao "%>
<%@ page import="java.util.* "%>
<%@ page import="java.math.BigDecimal"%>

<%
	String mdId = (String) session.getAttribute("mdId");
	System.out.print(mdId);
	String balance = LoginDao.getMDBalance(mdId, request);
	HashMap hashmap = (HashMap) session.getAttribute("info");
	String clientFlag = (String) hashmap.get("flag");
	String header_home_image = (String) hashmap.get("md_header_home_image");

	//String Blalance=(String)session.getAttribute("balance");
	double mdBlalance = Double.parseDouble(balance);
	BigDecimal MdBlanceAmount = new BigDecimal(mdBlalance);
	BigDecimal FinalBalance = MdBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
	System.out.println("FinalBalance::" + FinalBalance);

	String mdName = (String) session.getAttribute("mdName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><%=session.getAttribute("md_page_title")%></title>
<link rel="stylesheet" type="text/css" href="css/dis.css" />
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<script src="css/menu.js" type="text/javascript"></script>

<style type="text/css">
<!--
.style5 {
	font-size: 50px;
	color: #000000;
	font-weight: bold;
}

/**Admin panel**/
.wrap_userDetail {
	margin: 4px;
	border: 1px solid #ccc;
	width: 500px;
	float: right;
	box-shadow: 0 0 5px #bbb inset;
	background: #f8fdff;
}

.user_detl {
	width: 450px;
	padding-bottom: 5px;
}

table.user_detl tr td p {
	font-weight: bold;
	float: right;
	font-size: 12px;
	margin-top: 5px;
}

table.user_detl tr td p span {
	color: #333;
	font-weight: bold;
	margin-left: 5px;
	font-size: 12px;
}

p.wel_txt {
	color: #333;
}

p.wel_txt span {
	color: #aa2433 !important;
}

ul.menu_top_rgt {
	float: right;
	margin-right: 10px;
	margin-top: 4px;
}

ul.menu_top_rgt li {
	float: left;
	list-style-type: none;
	margin-right: 30px;
}

ul.menu_top_rgt li p {
	cursor: pointer;
}

ul.menu_top_rgt li p a {
	text-decoration: none;
}

ul.menu_top_rgt li p a:hover {
	text-decoration: underline;
}

ul.menu_top_rgt li p span.arrow_img_span {
	background: url(../images/master_img.png) -169px -12px no-repeat;
	width: 10px;
	height: 7px;
	line-height: 10px;
	padding: 0 5px 0 5px;
	margin: 6px 0 0 5px;
	position: absolute;
}

ul.menu_top_rgt div.subMenu_div {
	position: absolute;
	padding: 5px;
	width: 129px;
	background: rgb(245, 245, 245);
	overflow: hidden;
	box-shadow: 0 0 3px #333;
	-moz-box-shadow: 0 0 3px #333;
	-webkit-box-shadow: 0 0 3px #333;
}

ul.menu_top_rgt ul.sub_menu_top {
	list-style-type: none;
}

ul.menu_top_rgt ul.sub_menu_top li {
	text-align: left;
	border-bottom: 1px dotted #aaa;
}

ul.menu_top_rgt ul.sub_menu_top li p {
	width: 300px;
	padding-bottom: 2px;
}

ul.menu_top_rgt ul.sub_menu_top li p a {
	text-decoration: none;
	padding: 2px 100px 0 0 !important;
}

ul.menu_top_rgt ul.sub_menu_top li p a:hover {
	text-decoration: underline;
	color: #b42636;
}

ul.menu_top_rgt li:hover p span.arrow_img_span {
	background: url(../images/master_img.png) -169px -2px no-repeat;
}

ul.menu_top_rgt li div.subMenu_div {
	display: none;
}

ul.menu_top_rgt li:hover div.subMenu_div {
	display: block;
}

.add_main p {
	background: url(../images/master_img.png) -1px -217px no-repeat;
	width: 363px;
	height: 157px;
}

.blue {
	color: #00468e;
	text-decoration: none;
}

/**start of tabs**/
-->
</style>

<script language="javascript">
function commingSoon()
{
alert("Coming Soon!  We are working on it.");

}

	
</script>
</head>

<body>
	<center>
		<table cellpadding="0" cellspacing="0" border="0" align="center"
			width="1000">
			<tr>
				<td width="100%"><table cellpadding="0" cellspacing="0"
						border="0" align="center" width="100%">
						<tr>
							<td width="75%" align="left" valign="middle"
								style="padding-left: 20px" height="98"><img
								src="images/<%=header_home_image%>" width="163px" height="69px" /></td>
							<td width="25%" valign="top">
								<!--start of user detail-->
								<div class="wrap_userDetail">
									<table class="user_detl" cellpadding="0" cellspacing="0">
										<tr>
											<td><p class="blue">
													MDS ID : <span><strong><%=session.getAttribute("completeId")%></strong></span>
												</p></td>
											<td><p class="blue">
													Available Balance : <span><strong>Rs.<span><strong><%=FinalBalance%></strong></span></strong></span>
												</p></td>
										</tr>

										<tr>
											<td>&nbsp;</td>
											<td><p class="wel_txt">
													Welcome <span><%=session.getAttribute("mdName")%></span>
												</p></td>
										</tr>
									</table>

								</div> <!--end of user_detail-->
							</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td width="100%" height="1" align="right"><img
					src="images/saperate.gif" height="1" width="1000" border="0" /></td>
			</tr>

			<tr>
				<td valign="middle" align="left" style="padding-left: 5px">
					<ul id="nav">
						<li class="top"><a href="doLoginAction.do?param=home"
							class="top_link" target="_parent"><span>Home</span></a></li>

						<li class="top"><a class="top_link"><span class="down">&nbsp;&nbsp;I&nbsp;&nbsp;</span></a></li>

						<li class="top"><a href="#" class="top_link"><span
								class="down">Channel</span></a>
							<ul class="sub">
								<li style="border-bottom: #00448d 1px solid;"><a
									href="distributorRegistration.do?param=register"
									target="_parent" class="fly">Add Distributor</a></li>
								<li style="border-bottom: #00448d 1px solid;"><a
									href="viewDistributor.do?param=viewDistributor"
									target="_parent" class="fly">View Distributor</a></li>
								<li style="border-bottom: #00448d 1px solid;"><a
									href="viewAgent.do?param=viewAgent" target="_parent"
									class="fly">View Agents</a></li>
								<li style="border-bottom: #00448d 1px solid;"><a
									href="userSearch.do?param=userSearch" target="_parent"
									class="fly">User Search</a></li>
								<!--<li><a href="accessDocuments.do?param=agentId" target="_parent"class="fly">Agent Documents</a></li>-->
								<!--<li><a href="distributorRegistration.do?param=register" target="_parent" class="fly">Add Distributor</a></li>-->

							</ul></li>
						<li class="top"><a class="top_link"><span class="down">&nbsp;&nbsp;I&nbsp;&nbsp;</span></a></li>

						<li class="top"><a href="#" class="top_link"><span
								class="down">Funds</span></a>
							<ul class="sub">
								<li><a href="pushBalance.do?param=transferRequest">Push
										Balance - Distributor</a></li>
								<li><a href="depositRequest.do?param=depositRequest"
									target="_parent">Deposit Request - Distributor</a></li>
								<li><a href="mdDepositRequest.do?param=amountRequest"
									target="_parent">My Deposit</a></li>

							</ul></li>
						<li class="top"><a class="top_link"><span class="down">&nbsp;&nbsp;I&nbsp;&nbsp;</span></a></li>

						<!--Start Service Request-->
						<!-- <li class="top"><a href="#" class="top_link"><span
								class="down">Requests</span></a>

							<ul class="sub">
								<li style="border-bottom:#00448d 1px solid;"><a href="#" onClick="commingSoon()"  class="fly">Mobile Number Change</a></li>
		   	<li style="border-bottom:#00448d 1px solid;"><a href="#" onClick="commingSoon()" class="fly">E-Mail ID Change</a></li>
								<li style="border-bottom: #00448d 1px solid;"><a href="#"
									onclick="commingSoon()" class="fly">Ticker Upload</a></li>
								<li style="border-bottom:#00448d 1px solid;"><a href="#" onClick="commingSoon()" class="fly">Balance Reversal</a>
								<li style="border-bottom: #00448d 1px solid;"><a href="#"
									onclick="profileChange.do?param=profileChange" class="fly">Profile
										Change</a></li>
								<li style="border-bottom: #00448d 1px solid;"><a href="#"
									onclick="commingSoon()" class="fly">Web/SMS Activation</a></li>
								 <li style="border-bottom:#00448d 1px solid;"><a href="resendMPIN.do?param=requestResendMpin" class="fly">Resend MPIN</a>

								<li style="border-bottom: #00448d 1px solid;"><a
									href="http://www.mgencash.com/" target="_blank" class="fly">Mobile
										App</a></li>
								<li><a href="http://www.dndcheck.in/" target="_blank"
									class="fly">DND Check </a></li>

							</ul></li> -->
						<li class="top"><a class="top_link"><span class="down">&nbsp;&nbsp;I&nbsp;&nbsp;</span></a></li>
						<li class="top"><a href="#" class="top_link"><span
								class="down">Reports</span></a>

							<ul class="sub">


								<li><a href="accountStatement.do?param=accountStatement">My Report</a></li>
								<li><a href="DownloadReport.do?param=DSReport">Distributors</a></li>
								<!--   <li><a href="transferReport.do?param=distToAgent" >Distributors 2</a></li>-->
								<li><a href="getAgentReport.do?param=AgentReport">Agents</a></li>
								<li><a href="SearchTran.do?param=SearchTranPage">Transaction
										Status</a></li>


								<!--
				   <li><a href="searchSuspectReport.do?param=getReportPage" target="_parent">Agent Recharge Statement</a></li>
				   <li><a href="accountStatement.do?param=accountStatement" target="_parent">MDS Account Statement</a></li>
				   <li><a href="transferReport.do?param=mdToDistributor" target="_parent">MDS to DS Transfer</a></li>
				   <li><a href="transferReport.do?param=distToAgent"  target="_parent">DS to Agent Transfer </a></li>
				   <li><a href="agentTranReport.do?param=agentTransaction" target="_parent">Agent Transaction</a></li>-->

								<%
									if (clientFlag.equals("1") || clientFlag.equals("2")) {
								%>


								<%}%>

							</ul></li>
						<!--End Service Request-->


						<li class="top"><a href="mdProfile.do?param=myProfile"
							target="_parent" class="top_link"><span
								style="padding-left: 480px;">My Profile</span></a></li>



						<li class="top"><a class="top_link"><span class="down">&nbsp;&nbsp;I&nbsp;&nbsp;</span></a></li>

						<li class="top"><a href="doLoginAction.do?param=logout"
							target="_parent" class="top_link"><span>Log&nbsp;Out</span></a></li>

					</ul>
				</td>
			</tr>




			<tr>
				<td width="100%" height="1" align="right"><img
					src="images/saperate.gif" height="1" width="1000" border="0" /></td>
			</tr>

		</table>
	</center>
</body>
</html>
