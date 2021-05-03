<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />

<script src="//code.jquery.com/jquery-1.9.1.js"></script>
<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<link rel="stylesheet" href="css/bootstrap.min.css" />
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="scripts/bootstrap-notify.js"></script>
<script src="scripts/bootstrap-notify.min.js"></script>
<!-- <link rel="stylesheet" href="css/font-awesome.min.css"> -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<style>
.big1 td{font-family:"Trebuchet MS"; font-size:15px; color:#008B8B; font-weight:bold;}
.short_cut{
	border: 1px solid #3910b7;
    border-radius: 3px;
    padding: 10px;
    background: #ae99ef;
    color: #fbfbfb;
    text-decoration: none;
    cursor: pointer;
    }
   .short_cut_tab{
   	height: 50px;
   }
   .business_tab{
   		border:1px solid #000;padding: 5px;font-size: 30px;border-radius:5px 5px 5px 5px;
   }
   .business_tab_heading{
   		    margin-top: 25px;
   }
</style>
<%
HashMap<String,Object> dashboardMap=(HashMap<String,Object>)request.getAttribute("dashboardMap");

%>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top" height="460"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class="rounded-corners">
          
          <div class="col-md-12" style="padding: 5px;" align="center"><h3>Daily Business</h3></div>	
          
	          <div class="col-md-12" style="padding: 4% 2px 2% 10px;">
          			<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Total Transacting Agents</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalActiveAgent") %></strong>
				  		</div>
          			</div>
          			<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Total Success Transaction</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalSuccessTran") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Total Failed Transaction</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalFailedTran") %></strong>
				  		</div>
          			
          			</div>
          			
          			
          	  </div>
          	  
          	  <div class="col-md-12" style="padding: 0% 0px 6% 10px;">
          	  		<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Success Transaction Count</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("successCount") %></strong>
				  		</div>
          			</div>
          			<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Failed Transaction Count</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("failedCount") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-4 short_cut_tab"> 
          				<div class="business_tab_heading">Pending Transaction Count(5 Days)</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("pendingCount") %></strong>
				  		</div>
          			
          			</div>
          	  </div>
          	  
          	  <div class="col-md-12" style="padding: 0% 0px 6% 10px;">
          	  		<div class="col-md-3 short_cut_tab"> 
          				<div class="business_tab_heading">Recharges Success</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalRecharge") %></strong>
				  		</div>
          			</div>
          			<div class="col-md-3 short_cut_tab"> 
          				<div class="business_tab_heading">Billpay Success</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalBill") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-3 short_cut_tab"> 
          				<div class="business_tab_heading">DMR Success</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalDMT") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-3 short_cut_tab"> 
          				<div class="business_tab_heading">AePS Success</div>
          				<div class="business_tab">
				  			<strong><%=dashboardMap.get("totalAeps") %></strong>
				  		</div>
          			
          			</div>
          	  </div>
          	  
          	  <div class="col-md-12" style="padding: 5px;" align="center"><h3>Available Virtual Balance</h3></div>	
          
	          <div class="col-md-12" style="padding: 4% 2px 6% 10px;">
	          		<div class="col-md-1"></div>
          			<div class="col-md-2 short_cut_tab"> 
          				<div class="business_tab_heading">Agent Balance</div>
          				<div class="business_tab">
				  			<strong style="font-size: 16px;"><%=dashboardMap.get("agentBal") %></strong>
				  		</div>
          			</div>
          			<div class="col-md-2 short_cut_tab"> 
          				<div class="business_tab_heading">DS Balance</div>
          				<div class="business_tab">
				  			<strong style="font-size: 16px;"><%=dashboardMap.get("dsBal") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-2 short_cut_tab"> 
          				<div class="business_tab_heading">MDS Balance</div>
          				<div class="business_tab">
				  			<strong style="font-size: 16px;"><%=dashboardMap.get("mdsBal") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-2 short_cut_tab"> 
          				<div class="business_tab_heading">Admin Balance</div>
          				<div class="business_tab">
				  			<strong style="font-size: 16px;"><%=dashboardMap.get("adminBal") %></strong>
				  		</div>
          			
          			</div>
          			
          			<div class="col-md-2 short_cut_tab"> 
          				<div class="business_tab_heading">Total Balance</div>
          				<div class="business_tab">
          					<strong style="font-size: 16px;"><%=dashboardMap.get("totalBal") %></strong>
				  		</div>
          			
          			</div>
          			<div class="col-md-1"></div>
          			
          			
          	  </div>
            </td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
