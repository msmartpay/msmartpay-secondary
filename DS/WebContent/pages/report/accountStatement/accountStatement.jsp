
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.ArrayList "%>

<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>

<% 
String message=(String)request.getAttribute("message");
DecimalFormat dff = new DecimalFormat("0.00");
int  check1=(Integer)session.getAttribute("check1AS");
int  check2=(Integer)session.getAttribute("check2AS");
String TAmount="";
String Marg="";
String ChargeA="";
String NetAm="";
String CurrentBal="";
String dateGet="";
String FilePath=(String)request.getAttribute("fileStatus");
if(message==null)
{
message="";
}
int size=0;
ArrayList DistributorAccountStatement=(ArrayList)request.getAttribute("DistributorAccountStatement");

 if (DistributorAccountStatement==null)
 {
	 size=-1;
 }
 else
 {
	 size=DistributorAccountStatement.size();
	
 }
Date today = new Date();
SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
String curdate = formatter2.format(today);
String particular="";
int a =(Integer)session.getAttribute("start");
int b=(Integer)session.getAttribute("end");
int pagecount=(Integer)session.getAttribute("pagecount"); 
int count=(Integer)session.getAttribute("count");
int count1=(Integer)session.getAttribute("count1");
int pageno=(Integer)session.getAttribute("pno");
%>
 <script language="javascript" type="text/javascript">

function  from_click()
{

	var val_1 = document.MyTransaction.from.value;
	var val_2 = document.MyTransaction.to.value;

	if( val_1 == "" || val_2 == "")
	{
		alert("Please select the date from input");
		return false;
	}
	document.MyTransaction.submit();

}
</script>
<script type="text/javascript">
var t = new SortableTable(document.getElementById('myTable'), 100);

</script>
<script language="javascript" type="text/javascript">
function printpage() {
  window.print();
  }
//----------------------------function for first form to get data--------------------------
function checkAccountForm()
{

var report=document.MyTransaction.reportof.value;
var type=document.MyTransaction.type.value;
var fromdt=document.MyTransaction.fromDate.value;
var toDate=document.MyTransaction.ToDate.value;

var DaysDiff;
var DaysDiff2;
var DaysDiff3;
var DateValue2='2011-01-19';
Date1 = new Date(fromdt);
Date2 = new Date(DateValue2);
Date3 = new Date(toDate);
DaysDiff = Math.floor((Date1.getTime() - Date2.getTime())/(1000*60*60*24));

if(DaysDiff < 0)
{
alert("You can view account statement from 19-01-2011");
return false;
}
DaysDiff2 = Math.floor((Date3.getTime() - Date1.getTime())/(1000*60*60*24));

if(DaysDiff2 < 0)
{
alert("To Date field never less then from date");
return false;
}

DaysDiff3 = Math.floor((Date3.getTime() - Date1.getTime())/(1000*60*60*24));

if(DaysDiff3 >30)
{
alert("You can not view account Statement  for more then month");
return false;
}

if(report=="select")
{
alert("Please select option for Report Of");
document.MyTransaction.reportof.focus();
return false;
}
else if(type=="Please Select")
{
alert("Please select option for type");
document.MyTransaction.type.focus();
return false;
}
else
{

//document.MyTransaction.action="distributorAccount.action?param=accountreport";
document.MyTransaction.submit();
}

}

//---------------------------------End of first function------------------------

function forpopup(transid,service,status,amtstatus)
{

	if(status=="pending" || status=="Pending"){
		alert("Status will be updated within 24 hrs.");

	}
	else
	{

		window.open("checkDetails.action?param=transactionPopUp&TransactionNo="+transid+"&Service="+service+"&TransactionStatus="+status+"&ActionOnBalanceAmount="+amtstatus,'popup','width=450,height=230,resizable=no,scrollbars=no');

	}
}

</script>

	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
	
	
	
	<!-- CSS
	================================================== -->
	<!-- Base + Vendors CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="css/fonts/entypo/css/entypo.css">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css" media="screen">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css" media="screen">
	<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen">

	<!-- Theme CSS-->
	<link rel="stylesheet" href="css/theme.css">
	<link rel="stylesheet" href="css/theme-elements.css">
	<link rel="stylesheet" href="css/animate.min.css">

   

  <!-- Head Libs -->
	<script src="vendor/modernizr.js"></script>

	
	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicons/favicon.png">
	<link rel="apple-touch-icon" sizes="120x120" href="images/favicons/favicon-120.png">
	<link rel="apple-touch-icon" sizes="152x152" href="images/favicons/favicon-152.png">
	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 	<script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		  
		var now = new Date();
	    var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		
		var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
		var today = now.getFullYear()  + '-' + mm + '-' + dd;
		
		$("#datepicker1,#datepicker2").val(today);
		
		$("#datepicker1,#datepicker2").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "0"
		}); 
	});
	
	</script>
	
</head>
<body>
	<%@ include file="/header.jsp"%>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>My Report</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;">
				<div class="container" style="width: 100%;" align="center" >			
					
							<div class="cta-txt" align="center">
                                    <div class="col-md-12 ">
                                        <div class="box" align="center">           
                                            <form name="MyTransaction" action="distributorAccount.action?param=download" method="POST" role="form">                                                       
                                                
                                                <div style="color: RED;font-weight: bold;margin-bottom: 10px;"><%=message%></div>
                                                <div class="form-group col-md-2">
                                                    <input type="text" id="datepicker1" name="fromDate" id="from" readonly="readonly" required="required" class="form-control" placeholder="Select from Date ">
                                                  
                                                </div>
                                                <div class="form-group col-md-2">
                                                      <input type="text" id="datepicker2" name="ToDate" readonly="readonly" id="to" required="required" class="form-control" placeholder="Select to Date ">                                               
                                                     
                                               </div>
                                               <div class="form-group col-md-3">
                                                    <select class="form-control"   id="reportof" name="reportof" >
											              <option value="success"  selected="selected">Report For Success</option>
											              <option value="pending" >Report For Pending</option>
											              <option value="refunded" >Report For Refunded</option>
											          </select>
                                                  
                                                </div>
                                                <div class="form-group col-md-3">
                                                      <select class="form-control"  id="type" name="type" >
											              <option value="all"  selected="selected">Report Type ALL</option>
											              <option value="debit" >Report Type Debit</option>
											              <option value="credit" >Report Type Credit</option>
											          </select>
                                               </div> 
                                               <div class="form-group col-md-2"> 
                                               <%if(FilePath!=null && FilePath.length()>0){  %>
                                                <a style="margin-right: 10%;" href="Reportfile/<%=FilePath %>">Download Report</a>
                                                <%} %><button type="submit" class="btn btn-primary btn-inline" onclick="from_click()">Generate Report</button>
                                               </div>
                                            </form>
                                    	</div>
                                    </div>                                    
                                                                
                        	</div>
                
                </div>
                <div class="container" style="width: 100%;" align="center" >	
                <div class="spacer"></div>
                <div class="row">
                    <div class="col-md-12">
                     					
                                        <div class="table-responsive">
                                        <form action="distributorAccount.action" name="pageForm" method="post">
                                           <table class="table table-striped table-bordered">
                                           <% if (size>0)
                           				{
					
						   				 	String AmountO=(String)session.getAttribute("balamtbefded");
		 									double AmountOa=Double.parseDouble(AmountO);
											String   opening=dff.format(AmountOa);
	 										String AmountC=(String)session.getAttribute("finalbalamt");
					     					double AmountOC=Double.parseDouble(AmountC);
					     					String Closing=dff.format(AmountOC);
                          			 	%>
							                   <thead>
                                                <tr>
                                                    <th> S. No.</th>
                                                    <th> Date </th>
                                                  <!--   <th> Time </th> -->
                                                    <th> Particulars </th>
                                                    <th> Txn. Amount </th>                                                
                                                    <th>Charges</th>
                                                    <th>Net Amt</th>
                                                    <th>Action</th>
                                                    <th>Current Bal.</th>
                                                    <th>Txn. Status</th>
                                                    <th>Remark</th>                                                
                                                </tr>
                                                </thead>
                                                <tbody>
                                        <%  if(size>0)
											{
									       		for (int i=0;i<size;i++ )
										 		{
											 		HashMap mapdate=(HashMap)DistributorAccountStatement.get(i);
											 
											 		if (pageno==0) {
											
														/* double Amount1=(Double)mapdate.get("TransactionAmount");
										       			TAmount=dff.format(Amount1);
											     		double Amount2=(Double)mapdate.get("Commission");
										       			Marg=dff.format(Amount2);
											     		double Amount3=(Double)mapdate.get("charge");
												  
										       			ChargeA=dff.format(Amount3);	
											    
											       		double Amount4=(Double)mapdate.get("NetTransactionAmount");
										       			NetAm=dff.format(Amount4);
											     		double Amount5=(Double)mapdate.get("FinalBalanceAmount");
										       			CurrentBal=dff.format(Amount5);
												   		Date date =(Date)mapdate.get("DateOfTransaction");	
												   		
											  			SimpleDateFormat formatterB = new SimpleDateFormat("dd/MM/yyyy");
							                  			dateGet=formatterB.format(date); */
										 
											 		}
											 		else
											 		{
											   			/* double Amount1=Double.parseDouble((String)mapdate.get("TransactionAmount"));
										       			TAmount=dff.format(Amount1);
											     		double Amount2=Double.parseDouble((String)mapdate.get("Commission"));
										       			Marg=dff.format(Amount2);
											     		double Amount3=Double.parseDouble((String)mapdate.get("charge"));
												
										       			ChargeA=dff.format(Amount3);	
											   
											       		double Amount4=Double.parseDouble((String)mapdate.get("NetTransactionAmount"));
										       			NetAm=dff.format(Amount4);
											     		double Amount5=Double.parseDouble((String)mapdate.get("FinalBalanceAmount"));
										       			CurrentBal=dff.format(Amount5); */
											   	
												 		/* String strA=(String)mapdate.get("DateOfTransaction");
													 
							                 			SimpleDateFormat formatterA = new SimpleDateFormat("yyyy-mm-dd"); //please notice the capital M
							                 			Date date = formatterA.parse(strA);	
							                  
							                 			SimpleDateFormat formatterB = new SimpleDateFormat("dd/MM/yyyy");
							                 			dateGet=formatterB.format(date);  */
													}

												%>
                                                <tr>
                                                    <td><%=(i+1)%></td>
                                                    <td><%=mapdate.get("DateOfTransaction")%></td>
                                                    <%
													 String service=(String)mapdate.get("Service");
													 if(service.equalsIgnoreCase("mdtodist")){
													 	particular="DS TB - Taken";
													 }
													 if(service.equalsIgnoreCase("transfertoagent")){
													 	particular="AG TB - Transfer";
													 }
													 if(service.equalsIgnoreCase("transfertodist")){
													 	particular="DS TB - Taken";
													 }
													 if(service.equalsIgnoreCase("accountadjustment")){
													 	particular="Account Adjustment";
													 }
													 if(service.equalsIgnoreCase("PaymentGateway")){
													 	particular="DS TB - PG";
													 }
													
													 if(service.equalsIgnoreCase("admintoagent"))
									                 {
															particular="Push Transfer";
									
												     }
													 if(service.equalsIgnoreCase("AgentCreationCharge"))
									                 {
															particular="Agent Creation Charge";
									
												     }
													 if(service.equalsIgnoreCase("Account Adjustment-DS")){
													 	particular="Account Adjustment-DS";
													 }
												%>
                                                    <td><a  style="text-decoration:none;font-weight: bold;" href="javascript:forpopup('<%=mapdate.get("TransactionNo")%>','<%=mapdate.get("Service")%>','<%=mapdate.get("TransactionStatus")%>','<%=mapdate.get("ActionOnBalanceAmount")%>')" ><%=particular%></a></td>
                                                    <td><%=mapdate.get("TransactionAmount")%></td>
                                                    <td><%=mapdate.get("charge")%></td>
                                                    <td><%=mapdate.get("NetTransactionAmount")%></td>
                                                    
													<td style="text-transform: capitalize;"><%=mapdate.get("ActionOnBalanceAmount") %></td>
															
                                                    <td><%=mapdate.get("FinalBalanceAmount")%></td>
                                                    <td style="text-transform: capitalize;"><%=mapdate.get("TransactionStatus")%></td>
                                                    <td style="text-transform: capitalize;"><%=mapdate.get("Remarks")%></td>
                                                </tr>
                                               		<%}%>
	   											<%}%>
                                                </tbody>
                                                <%} else {%>
                                      		<%}%>
                                           </table>
                                           </form>
                                        </div>
                                    </div>
               				 </div>

            </div>
		</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>

		</div>
		<!-- Main / End -->
	</div>
<%@ include file="/footer.jsp" %>

	<!-- Javascript Files
	================================================== -->
	<script src="vendor/jquery-migrate-1.2.1.min.js"></script>
	<script src="vendor/bootstrap.js"></script>
	<script src="vendor/jquery.flexnav.min.js"></script>
	<script src="vendor/jquery.hoverIntent.minified.js"></script>
	<script src="vendor/jquery.flickrfeed.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.js"></script>
	<script src="vendor/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendor/jquery.fitvids.js"></script>
	<script src="vendor/jquery.appear.js"></script>
	<script src="vendor/jquery.stellar.min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>


	
</body>

</html>