
<!DOCTYPE html>
<%@page import="java.text.SimpleDateFormat"%>
<html class="not-ie no-js" lang="en">  
<head>
    <%@ include file="/globaldata.jsp"%>
    
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
	
	 <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css" type="text/css" media="all" />
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" type="text/javascript"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(document).ready(function(){
		$("#transfer").hide();
		$("#cash").hide();
		$("#Neft").hide();
		
		$("#transfer").click(function(){
			$("#transfer").show('slow');
			$("#cash").hide();
			$("#Neft").hide();
		});
		$("#cash").click(function(){
			$("#cash").show();
			$("#transfer").hide('slow');
			$("#Neft").hide();
		});
		$("#Neft").click(function(){
			$("#Neft").show('slow');
			$("#cash").hide();
			$("#transfer").hide();
		});
	});
	</script>
	
	<script type="text/javascript">
           $(function () {
		  $('#txtDate').datepicker({
			  changeMonth: true,
			  changeYear: true,
			  dateFormat: 'yy-mm-dd',
			  yearRange:'1950:2020',
			 numberOfMonths: 1,
               }); 
           });
  	</script>
  	
  	<script type="text/javascript">

	function showDiv()
	{

		var mode=document.mydeposit.depositMode.value;

		if(mode=="Cash in Bank")
		{
		document.getElementById("transfer").style.display="none";
		document.getElementById("cash").style.display="";
		document.getElementById("Neft").style.display="none";
		}
		
		if(mode=="E-Transfer")
		{
		document.getElementById("transfer").style.display="";
		document.getElementById("cash").style.display="none";
		document.getElementById("Neft").style.display="none";
		}
		if(mode=="NEFT/RTGS")
		{
		document.getElementById("transfer").style.display="none";
		document.getElementById("cash").style.display="none";
		document.getElementById("Neft").style.display="";
		}
		
		if(mode=="select")
		{
		document.getElementById("transfer").style.display="none";
		document.getElementById("cash").style.display="none";
		document.getElementById("Neft").style.display="none";
		}
	}
	
	function submitFormOnline()
	{
	 var amount=parseInt(document.mydeposit.AmountToCredit.value);

	if(amount<1000){
	alert("Amount Cannot be Less Than Rs.1000 .");
	return false;
	}
		if(submitForm())
		{
		    
		var  depositMode=document.mydeposit.depositMode.value;
		var Amount= document.mydeposit.AmountToCredit.value;
		var payDate= document.mydeposit.Paymentdate.value;
		
		document.etransaferForm.action="distributorDepositRequest.action?param=OnlineE-Transfer&depositMode="+depositMode+"&AmountToCredit="+Amount+"&Paymentdate="+payDate;
		document.etransaferForm.submit();
		}
		
		 
	}

	function submitFormNEFT()
	{
	var amount=parseInt(document.mydeposit.AmountToCredit.value);

	if(amount<1000){
	alert("Amount Cannot be Less Than Rs.1000 .");
	return false;
	}
		if(submitForm())
		{
		    
		var  depositMode=document.mydeposit.depositMode.value;
		var Amount= document.mydeposit.AmountToCredit.value;
		var payDate= document.mydeposit.Paymentdate.value;
		
		document.NeftForm.action="distributorDepositRequest.action?param=NEFT/RTGS&depositMode="+depositMode+"&AmountToCredit="+Amount+"&Paymentdate="+payDate;
		document.NeftForm.submit();
		}
		
		 
	}

	function submitFormCashInBank()
	{
	 var amount=parseInt(document.mydeposit.AmountToCredit.value);

	if(amount<1000){
	alert("Amount Cannot be Less Than Rs.1000 .");
	return false;
	}

		if(submitForm())
		{
		    
		var  depositMode=document.mydeposit.depositMode.value;
		var Amount= document.mydeposit.AmountToCredit.value;
		var payDate= document.mydeposit.Paymentdate.value;
		
		document.CashOnBank.action="distributorDepositRequest.action?param=CashInBank&depositMode="+depositMode+"&AmountToCredit="+Amount+"&Paymentdate="+payDate;
		document.CashOnBank.submit();
		}
		
		 
	}

	function submitForm()
	{

	var mode=document.mydeposit.depositMode.value;

	if(mode=="select")
	{
	alert("Please select Deposit Mode");
	document.mydeposit.depositMode.focus();
	return false;
	}




	var mm= document.mydeposit.AmountToCredit.value;
	   
	     if(mm<1)

			{
			   
				alert("Enter Amount Deposited ");

	      document.mydeposit.AmountToCredit.focus();
	         return false;
		
			}
			/* if(mm<5000)

			{
			   
				alert("Amount can not be less than 5000.");
	document.mydeposit.AmountToCredit.focus();
	return false;

				

			}

		 if(mm>1000000)

			{
			   
				alert("Amount can not be greater than 1000000.");
	document.mydeposit.AmountToCredit.focus();
	return false;

				

			}*/

	   //------------------------------------
	     if(mode=="Cash in Bank")
	     {
	     //alert(mode);
	     if(document.CashOnBank.DepositorName.value=="")
	     {
	     alert("Please enter DepositorName Name");
	     document.CashOnBank.cbDepName.focus();
	     return false;
	     }
	     //alert("hello");
	     if(document.CashOnBank.BankName.value!="cbOther")
	     {
	     /*if(document.CashOnBank.AttachCashFile.value=="")
	     {
	     alert("Please attach cash reciept");
	     return false;
	     }*/
	     }

	     if(document.CashOnBank.BankName.value=="cbOther")
	     {
	     if(document.CashOnBank.AccountNumber.value=="")
	     {
	     alert("Please enter Receiving Bank Account No");
	     document.CashOnBank.AccountNumber.focus();
	     return false;
	     }

	     if(document.CashOnBank.BranchName.value=="")
	     {
	     alert("Please enter Receiving Branch Name");
	     document.CashOnBank.BranchName.focus();
	     return false;
	     }

	     }


	     if(document.CashOnBank.DepositLocation.value=="")
	     {
	     alert("Please enter Deposit Location");
	     document.transferForm.DepositLocation.focus();
	     return false;
	     }
	     if(document.CashOnBank.BankTransactionId.value=="")
	     {
	     alert("Please enter Depositing Branch TID");
	     document.CashOnBank.BankTransactionId.focus();
	     return false;
	     }
	     /*if(document.CashOnBank.remarks.value=="")
	     {
	     alert("Please enter remark");
	     document.CashOnBank.remarks.focus();
	     return false;
	     }*/
	     }

	     //-------------------------Progress----------------
	     if(mode=="Cheque/DD")
	     {
	     if(document.checque.DepositorName.value=="")
	     {
	     alert("Please enter Depositor Name");
	     document.checque.DepositorName.focus();
	     return false;
	     }
	     if(document.checque.ChequeNumber.value=="")
	     {
	     alert("Please enter Cheque No");
	     document.checque.ChequeNumber.focus();
	     return false;
	     }

	     if(document.checque.BankName.value=="checOther")
	     {

	     if(document.checque.AccountNumber.value=="")
	     {
	     alert("Please enter Receiving Bank Account No");
	     document.checque.AccountNumber.focus();
	     return false;
	     }

	     if(document.checque.BranchName.value=="")
	     {
	     alert("Please enter Receiving Branch Name");
	     document.checque.BranchName.focus();
	     return false;
	     }

	     }

	     if(document.checque.DepositLocation.value=="")
	     {
	     alert("Please enter depositing location");
	     document.checque.DepositLocation.focus();
	     return false;
	     }
	     /*if(document.checque.remarks.value=="")
	     {
	     alert("Please enter remark");
	     document.checque.remarks.focus();
	     return false;
	     }*/

	     }

	     //----------------------------
	     if(mode=="Online E-Transfer")
	     {
	     if(document.etransaferForm.DepositorName.value=="")
	     {
	     alert("Please enter Depositor Name");
	     document.etransaferForm.DepositorName.focus();
	     return false;
	     }
	     if(document.etransaferForm.DepositorAccountNumber.value=="")
	     {
	     alert("Please enter Depositor Account Number");
	     document.etransaferForm.DepositorAccountNumber.focus();
	     return false;
	     }
	     if(document.etransaferForm.BankTransactionId.value=="")
	     {
	     alert("Please enter E Transfer Transaction Id");
	     document.etransaferForm.BankTransactionId.focus();
	     return false;
	     }
	     //if(document.etransaferForm.ReferenceNo.value=="")
	     //{
	     //alert("Please enter E Transfer Reference No.");
	     //document.etransaferForm.ReferenceNo.focus();
	     //return false;
	     //}

	     if(document.etransaferForm.BankName.value=="eOther")
	     {
	     if(document.etransaferForm.AccountNumber.value=="")
	     {
	     alert("Please enter Receiving Bank Account No");
	     document.etransaferForm.AccountNumber.focus();
	     return false;
	     }
	     if(document.etransaferForm.BranchName.value=="")
	     {
	     alert("Please enter Receiving Branch Name");
	     document.etransaferForm.BranchName.focus();
	     return false;
	     }
	     }
	     alert("online etransfer");
	     /*if(document.etransaferForm.remarks.value=="")
	     {
	     alert("Please enter remark");
	     document.etransaferForm.remarks.focus();
	     return false;
	     }*/

	     }

	     //-----------------------

	     if(mode=="NEFT/RTGS")
	     {

	     if(document.NeftForm.DepositorName.value=="")
	     {
	     alert("Please enter NEFT/RTGS Sender Name");
	     document.NeftForm.DepositorName.focus();
	     return false;
	     }
	     if(document.NeftForm.DepositorAccountNumber.value=="")
	     {
	     alert("Please enter NEFT/RTGS Sender A/C No.");
	     document.NeftForm.DepositorAccountNumber.focus();
	     return false;
	     }
	     if(document.NeftForm.BankTransactionId.value=="")
	     {
	     alert("Please enter Transfer TID");
	     document.NeftForm.BankTransactionId.focus();
	     return false;
	     }
	     /*if(document.NeftForm.ReferenceNo.value=="")
	     {
	     alert("Please enter Transaction Reference No.");
	     document.NeftForm.ReferenceNo.focus();
	     return false;
	     }*/

	     if(document.NeftForm.BankName.value=="NeftOther")
	     {
	     if(document.NeftForm.AccountNumber.value=="")
	     {
	     alert("Please enter Receiving Bank Account No");
	     document.NeftForm.AccountNumber.focus();
	     return false;
	     }
	     if(document.NeftForm.BranchName.value=="")
	     {
	     alert("Please enter Receiving Branch Name");
	     document.NeftForm.BranchName.focus();
	     return false;
	     }



	     }

	     /*if(document.NeftForm.remarks.value=="")
	     {
	     alert("Please enter remarks ");
	     document.NeftForm.remarks.focus();
	     return false;
	     }*/
	     alert("Trade Balance for NEFT Transfer will be Given on Next Working Day. In Short, NEFT Trade Balance Can Take 48 Hours. We Recommend Online E-Transfer. ");
	     }

	     //-----------------------------------------
	     if(mode=="credit")
	     {
	     if(document.depositMode.DepositorName.value=="")
	     {
	     alert("Please enter Credit Requested By name");
	     document.depositMode.DepositorName.focus();
	     return false;
	     }

	     }


	     return true;

	     }


/* 
	     function textCounter(field, countfield, maxlimit)
	      {
	     if (field.value.length > maxlimit) // if too long...trim it!
	     field.value = field.value.substring(0, maxlimit);
	     // otherwise, update 'characters left' counter
	     else 
	     countfield.value = maxlimit - field.value.length;
	     }
 */


	     function inputData1()
	     {
	     var bankName=document.CashOnBank.BankName.value;
	     if(!(bankName=="cbOther"))
	     {
	     document.getElementById("attachment").style.display="";
	     }
	     if(bankName=="cbOther")
	     {
	     document.getElementById("attachment").style.display="none";

	     document.getElementById("other1").style.display="";
	     document.getElementById("other11").style.display="";

	     }
	     else
	     {
	     document.getElementById("other1").style.display="none";
	     document.getElementById("other11").style.display="none";


	     }
	     }


	     function inputData2()
	     {

	     var bankName=document.checque.BankName.value;
	     if(bankName=="checOther")
	     {
	     document.getElementById("other2").style.display="";
	     document.getElementById("other22").style.display="";

	     }
	     else
	     {
	     document.getElementById("other2").style.display="none";
	     document.getElementById("other22").style.display="none";

	     }
	     }


	     function inputData3()
	     {
	     var bankName=document.etransaferForm.BankName.value;
	     if(bankName=="eOther")
	     {
	     document.getElementById("other3").style.display="";
	     document.getElementById("other33").style.display="";

	     }
	     else
	     {
	     document.getElementById("other3").style.display="none";
	     document.getElementById("other33").style.display="none";


	     }
	     }

	     function inputData4()
	     {
	     var bankName=document.NeftForm.BankName.value;

	     if(bankName=="NeftOther")
	     {
	     document.getElementById("other4").style.display="";
	     document.getElementById("other5").style.display="";

	     }
	     else
	     {
	     document.getElementById("other4").style.display="none";
	     document.getElementById("other5").style.display="none";

	     }}
	     // for remark issue
	     function checkChar(e)
	     {
	     var k;
	         document.all ? k = e.keyCode : k = e.which;
	         return ((k > 33 && k < 59) || (k > 59 && k < 126) || k == 8 || k == 32 || (k >= 48 && k <= 57));


	     }
	</script>
	
	<%
	
	
	Date today = new Date();
	SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	String curdate = formatter1.format(today);
	String message=(String)request.getAttribute("message");
	System.out.println("message----"+message);
	if(message==null)
	{
	message="";

	}
	HashMap UpdateProfile=(HashMap)session.getAttribute("UpdateProfile");
	int size=0;
	if(UpdateProfile== null){
	size=-1;
	}
	else{
	size=UpdateProfile.size();
	}
	System.out.println("size is----"+size);
	%>

</head>
<body>

	<div class="site-wrapper">

	<%@ include file="/header.jsp" %>
		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>My Deposit</h1>
						</div>
					</div>
					
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg1.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-2" >
                                        <div class="box">  
                                        
                                        	<div id="deposit" style="height: 225px;">                                                                      
                                            <form action=""  method="post" name="mydeposit">
											
												<div> <%=message%></div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Distributor Name <span class="required">*</span>
                                                    </label>                                                 
                                                  	<%=UpdateProfile.get("DistributorName")%>
                                                </div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    	Current Balance Amount <span class="required">*</span>
                                                    </label>                                                 
                                                   <%=FinalBalance%>
                                                </div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Request for Amount <span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="AmountToCredit" onkeypress="return digitonly(this,event)" maxlength="7" >
                                                </div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Payment Date <span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="date" class="form-control" name="Paymentdate" >
                                                </div>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Payment Mode <span class="required">*</span>
                                                    </label>
													<select class="form-control" name="depositMode" onchange="showDiv()" style="float:left;width:55%;" required>
														<option selected disabled="disabled">----------------------- Select -------------------</option>
														<option value="Cash in Bank">Cash in Bank</option>                                       
														<option value="NEFT/RTGS">NEFT/RTGS</option>
														<option value="E-Transfer" >Online E-Transfer</option>
																							
													</select>

												</div>

												</form>
												</div>
												
												<!--  E-Transfer Payment Modes Starts -->
												<div id="transfer" >
												<form action="" name="etransaferForm" method="post">
												<div class="col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fcf8e3;color:#c00;margin-bottom:2%;">
													<h5 style="color:#c00;">Online E-Transfer</h5>
												</div><br><br>
												<div class="form-group" align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferee Name <span class="required">*</span>
                                                    </label>  
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorName" value="" maxlength="25" >
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferee Account Number <span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorAccountNumber" onkeypress="return digitonly(this,event)" value="" maxlength="20">
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferee Bank Name <span class="required">*</span>
                                                    </label>
													<select class="form-control" name="DepositorBankName" style="float:left;width:55%;" required>
														<option>----------------------- Select -------------------</option>
														 <option>Allahabad Bank</option>
                                                        <option>Andhra Bank</option>
                                                        <option>Bank of Baroda</option>
                                                        <option>Bank of India</option>
                                                        <option>Bank of Maharashtra</option>
                                                        <option>Canara Bank</option>
                                                        <option>Central Bank of India</option>
                                                        <option>Corporation Bank</option>
                                                        <option>Dena Bank</option>
                                                        <option>IDBI Bank</option>
                                                        <option>Indian Bank</option>
                                                        <option>Indian Overseas Bank</option>
                                                        <option>Oriental Bank of Commerce</option>
                                                        <option>Punjab National Bank</option>
                                                        <option>Punjab & Sind Bank</option>
                                                        <option>Syndicate Bank</option>
                                                        <option>Union Bank of India</option>
                                                        <option>UCO Bank</option>
                                                        <option>United Bank of India</option>
                                                        <option>Vijaya Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>State Bank Bikaner & Jaipur</option>
                                                        <option>State Bank of Hyderabad</option>
                                                        <option>State Bank of Mysore</option>
                                                        <option>State Bank of Patiala</option>
                                                        <option>State Bank of Travancore</option>
                                                        <option>Axis Bank</option>
                                                        <option>HDFC Bank</option>
                                                        <option>ICICI Bank</option>
                                                        <option>Kotak Mahindra Bank</option>
                                                        <option>Karnataka Bank</option>
                                                        <option>Yes Bank</option>
                                                        <option>IndusInd Bank</option>
                                                        <option>The Nainital Bank Ltd</option>
                                                        <option>ING Vysya Bank</option>
                                                        <option>South Indian Bank</option>
                                                        <option>Other</option>
																							
													</select>
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transfer Date <span class="required">*</span>
                                                    </label>             
                                                    <input style="float:left;width:55%;" type="date" name="RequestDate" class="form-control" >
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    	Transfer TID <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BankTransactionId" value="" onkeypress="return digitonly(this,event)" maxlength="20">
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferred to Bank Name <span class="required">*</span>
                                                    </label>
													<select class="form-control" name="BankName" onChange="inputData3()" style="float:left;width:55%;" required>
														<option>----------------------- Select -------------------</option>
														<option>Punjab National Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>ICICI Bank</option>
                                                        <option  value="eOther">Other</option>
																							
													</select>
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferred&nbsp;to&nbsp;A/C&nbsp;Number<span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="AccountNumber" value="" onkeypress="return digitonly(this,event)" maxlength="20">
                                                </div><br>
                                                <div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferred&nbsp;to&nbsp;Branch<span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BranchName" maxlength="40">
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Remarks <span class="required">*</span>
                                                    </label>
                                                    <textarea name="remarks" style="float:left;width:55%;" type="text" class="form-control" ></textarea>
                                                </div><br><br><br>
                                                <div class="form-group ">  
                                                	<button type="submit"  name="CashObDesk" onclick="submitFormOnline()" class="btn btn-primary btn-inline">Submit Request</button>
                                               </div>
                                                </form>
												</div>
												<!--  E-Transfer Payment Modes End -->
												
												<!--  Cash In Bank Payment Modes Starts -->
												
												<div id="cash" >
												 <form name="CashOnBank" method="post" enctype="multipart/form-data" >
												<div class="col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fcf8e3;color:#c00;margin-bottom:2%;">
													<h5 style="color:#c00;">Cash In Bank</h5>
												</div><br><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Depositor Name <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorName" maxlength="30" >
                                                </div><br><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Receiving Bank Name <span class="required">*</span>
                                                    </label>
													<select class="form-control" name="BankName" onChange="inputData1()" style="float:left;width:55%;" required>
														<option>----------------------- Select -------------------</option>
														<option>Punjab National Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>ICICI Bank</option>
                                                        <option value="cbOther">Other</option>
																							
													</select>
												</div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                   Receiving&nbsp;Bank&nbsp;A/C&nbsp;Number<span class="required">*</span>
                                                    </label>  
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="AccountNumber"   onkeypress="return digitonly(this,event)" maxlength="25" value="">
                                                    <input type="hidden" name="AttachCashFile" class="txt" style="width:175px"   />
                                                </div>
                                                <div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                   Receiving&nbsp;Branch&nbsp;Name<span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BranchName">
                                                    
                                                </div>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Depositing Location <span class="required">*</span>
                                                    </label>     
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositLocation" value="" >
                                                </div><br><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Depositing Branch TID <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BankTransactionId" onkeypress="return digitonly(this,event)" maxlength="20" >
                                                </div><br><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Remarks <span class="required">*</span>
                                                    </label> 
                                                    <textarea name="remarks" style="float:left;width:55%;" type="text" class="form-control" ></textarea>
                                                </div><br><br><br>
                                                <div class="form-group ">
                                                	<button type="submit" name="CashObDesk" onclick="submitFormCashInBank()" class="btn btn-primary btn-inline" style="margin-top: 3%">Submit Request</button>
                                               </div>
                                                </form>
												</div>
												
												
												<!--  Cash In Bank Payment Modes End -->
												
												<!--  NEFT/RTGS Payment Modes Starts -->
												
												<div id="Neft" >
												 <form name="NeftForm" method="post">
												<div class="col-md-12" style="padding:1.5% 0 0 1.5%;background-color:#fcf8e3;color:#c00;margin-bottom:2%;">
													<h5 style="color:#c00;">NEFT/RTGS</h5>
												</div><br><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    NEFT/RTGS Sender Name <span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorName" value=""  maxlength="25">
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Transferee A/C No. <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorAccountNumber" maxlength="20" onkeypress="return digitonly(this,event)">
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    NEFT/RTGS Sent via Bank Name <span class="required">*</span>
                                                    </label>
													<select name="DepositorBankName" class="form-control" style="float:left;width:55%;" required>
														<option>----------------------- Select -------------------</option>
													 	<option>Allahabad Bank</option>
                                                        <option>Andhra Bank</option>
                                                        <option>Bank of Baroda</option>
                                                        <option>Bank of India</option>
                                                        <option>Bank of Maharashtra</option>
                                                        <option>Canara Bank</option>
                                                        <option>Central Bank of India</option>
                                                        <option>Corporation Bank</option>
                                                        <option>Dena Bank</option>
                                                        <option>IDBI Bank</option>
                                                        <option>Indian Bank</option>
                                                        <option>Indian Overseas Bank</option>
                                                        <option>Oriental Bank of Commerce</option>
                                                        <option>Punjab National Bank</option>
                                                        <option>Punjab & Sind Bank</option>
                                                        <option>Syndicate Bank</option>
                                                        <option>Union Bank of India</option>
                                                        <option>UCO Bank</option>
                                                        <option>United Bank of India</option>
                                                        <option>Vijaya Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>State Bank Bikaner & Jaipur</option>
                                                        <option>State Bank of Hyderabad</option>
                                                        <option>State Bank of Mysore</option>
                                                        <option>State Bank of Patiala</option>
                                                        <option>State Bank of Travancore</option>
                                                        <option>Axis Bank</option>
                                                        <option>HDFC Bank</option>
                                                        <option>ICICI Bank</option>
                                                        <option>Kotak Mahindra Bank</option>
                                                        <option>Karnataka Bank</option>
                                                        <option>Yes Bank</option>
                                                        <option>IndusInd Bank</option>
                                                        <option>The Nainital Bank Ltd</option>
                                                        <option>ING Vysya Bank</option>
                                                        <option>South Indian Bank</option>
                                                        <option>Other</option>	
													</select>
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    NEFT/RTGS Date <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="date" class="form-control" name="RequestDate" >
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    	Transfer TID <span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BankTransactionId" value="" onkeypress="return digitonly(this,event)" maxlength="20" >
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    NEFT/RTGS Sent to Bank Name <span class="required">*</span>
                                                    </label>
													<select class="form-control" style="float:left;width:55%;" name="BankName" onChange="inputData4()" required>
														<option>----------------------- Select -------------------</option>
														<option>Punjab National Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>ICICI Bank</option>
                                                        <option value="NeftOther">Other</option>		
													</select>
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    	NEFT/RTGS&nbsp;Sent&nbsp;for&nbsp;A/C&nbsp;Number<span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="AccountNumber" maxlength="20" onkeypress="return digitonly(this,event)">
                                                </div><br>
                                                	<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    	NEFT/RTGS&nbsp;sent&nbsp;for&nbsp;Branch<span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="BranchName" maxlength="40" >
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;font-weight: bold;">
                                                    Remarks <span class="required">*</span>
                                                    </label>
                                                    <textarea name="remarks" style="float:left;width:55%;" type="text" class="form-control" ></textarea>
                                                </div><br><br><br>
                                                <div class="form-group "> 
                                                	<button type="submit" name="CashObDesk" onclick="submitFormNEFT()" class="btn btn-primary btn-inline" style="margin-top: 3%">Submit Request</button>
                                               </div>
                                                </form>
												</div>
												
												<!--  NEFT/RTGS Payment Modes End -->
												
												
												<!--  cheque -->
												
												<!-- <div id="cheque" >
												<form name="checque" method="post" >
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                   Depositor Name<span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="DepositorName" value="" maxlength="25" >
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                    Bank Name as on Cheque/DD<span class="required">*</span>
                                                    </label>
                                                   <select class="form-control" style="float:left;width:55%;" name="ChequeBankName" required>
														<option>----------------------- Select -------------------</option>
														 <option>Allahabad Bank</option>
                                                        <option>Andhra Bank</option>
                                                        <option>Bank of Baroda</option>
                                                        <option>Bank of India</option>
                                                        <option>Bank of Maharashtra</option>
                                                        <option>Canara Bank</option>
                                                        <option>Central Bank of India</option>
                                                        <option>Corporation Bank</option>
                                                        <option>Dena Bank</option>
                                                        <option>IDBI Bank</option>
                                                        <option>Indian Bank</option>
                                                        <option>Indian Overseas Bank</option>
                                                        <option>Oriental Bank of Commerce</option>
                                                        <option>Punjab National Bank</option>
                                                        <option>Punjab & Sind Bank</option>
                                                        <option>Syndicate Bank</option>
                                                        <option>Union Bank of India</option>
                                                        <option>UCO Bank</option>
                                                        <option>United Bank of India</option>
                                                        <option>Vijaya Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>State Bank Bikaner & Jaipur</option>
                                                        <option>State Bank of Hyderabad</option>
                                                        <option>State Bank of Mysore</option>
                                                        <option>State Bank of Patiala</option>
                                                        <option>State Bank of Travancore</option>
                                                        <option>Axis Bank</option>
                                                        <option>HDFC Bank</option>
                                                        <option>ICICI Bank</option>
                                                        <option>Kotak Mahindra Bank</option>
                                                        <option>Karnataka Bank</option>
                                                        <option>Yes Bank</option>
                                                        <option>IndusInd Bank</option>
                                                        <option>The Nainital Bank Ltd</option>
                                                        <option>ING Vysya Bank</option>
                                                        <option>South Indian Bank</option>
                                                        <option>Other</option>			
													</select>
                                                </div><br>
                                                <div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                    Cheque/DD Number<span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="ChequeNumber" maxlength="10"  onkeypress="return digitonly(this,event)">
                                                </div><br>
                                                 <div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                    Cheque/DD Date<span class="required">*</span>
                                                    </label> 
                                                    <input style="float:left;width:55%;" type="text" class="form-control" name="ChequeDDDate" size="18">
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                   Receiving Bank Name<span class="required">*</span>
                                                    </label>
													<select class="form-control" style="float:left;width:55%;" name="BankName" onChange="inputData2()" required>
														<option>----------------------- Select -------------------</option>
														 <option>Punjab National Bank</option>
                                                        <option>State Bank of India</option>
                                                        <option>HDFC Bank</option>
                                                        <option>ICICI Bank</option>
                                                        <option  value="checOther">Other</option>		
													</select>
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                   Receiving Bank A/C Number<span class="required">*</span>
                                                    </label>
                                                    <input style="float:left;width:55%;" type="text" name="AccountNumber" class="form-control" maxlength="25" onkeypress="return digitonly(this,event)">
                                                </div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                    Receiving Branch Name<span class="required">*</span>
                                                    </label>  
                                                    <input name="BranchName" style="float:left;width:55%;" type="text" class="form-control" maxlength="30">
                                                </div><br>
												<div class="form-group" align="left">
													<label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                   Depositing Location <span class="required">*</span>
                                                    </label>
													<input name="DepositLocation" style="float:left;width:55%;" type="text" class="form-control" maxlength="25" value="">
												</div><br>
												<div class="form-group " align="left">
                                                    <label class="pull-left" style="float:left;margin-right:5%;width:40%;">
                                                    Remarks <span class="required">*</span>
                                                    </label>  
                                                    <textarea name="remarks" style="float:left;width:55%;" type="text" class="form-control" ></textarea>
                                                </div><br><br>
												 <div class="form-group ">                                                      
                                                	<button type="submit" class="btn btn-primary btn-inline">Submit Request</button>
                                               </div>
                                               </form>
												 Demo
												</div> -->
												  
                                               
                                           
                                    </div>
                                    </div>                                    
                                                                
                                </div>
                        </div>
					</div>
				</div>
                
                <div class="spacer"></div>
                <div class="row">
                    <div class="col-md-12">
                    <%
							int i=0;
                    		
							ArrayList getDSJournalData=(ArrayList)request.getAttribute("getDSJournalData");
							System.out.println("My deposit details are :"+getDSJournalData);
									 size=getDSJournalData.size();
									 if (getDSJournalData==null)
										 size=-1;
										
									 else{
							            size=getDSJournalData.size();
										session.setAttribute("getDSJournalData",getDSJournalData);
									 } 
							if(size>0){
						%>
                                        <div class="table-responsive">
                                           <table class="table table-striped table-bordered">
							                   <thead>
                                                <tr>
                                                    <th>S.No.</th>
                                                    <th>Date </th>
                                                    <th>Time </th>
                                                    <th>Amount</th>
                                                    <th>Mode Of Payment</th>                                                
                                                    <th>Request Status </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                   <% 
						System.out.println("DS journal data "+getDSJournalData.size());
                        HashMap temp=new HashMap();
					
							for( i=0;i<size;i++)
							
							{						
								temp=(HashMap)getDSJournalData.get(i); 
								System.out.println("TB Request details are :"+temp);
								String status=(String)temp.get("status");	
								if(status.equalsIgnoreCase("accepted") || status.equalsIgnoreCase("success")){
								status="Success";
								}
								else if(status.equalsIgnoreCase("declined")){
								status="Declined";
								}
								else{
									status="Pending";
								}
								
								String MOP=(String)temp.get("mode_of_payment");
								if(MOP.equalsIgnoreCase("pushtransfer") || MOP.equalsIgnoreCase("PUSH TRANSFER") || MOP.equalsIgnoreCase("Push_Transfer") ){
								MOP="Push Transfer";
								}
								else if(MOP.equalsIgnoreCase("Cash in Bank")){
								MOP="Cash In Bank";
								}
								else if(MOP.equalsIgnoreCase("Cash on Desk")){
								MOP="Cash On Desk";
								}	
								else if(MOP.equalsIgnoreCase("cash deposit")){
								MOP="Cash Deposit";
								}	
								else if(MOP.equalsIgnoreCase("cheque deposit")){
								MOP="Cheque Deposit";
								}
							
							 Date date =(Date)temp.get("date");	
							// System.out.println("date ::"+date);
						    SimpleDateFormat formatterB = new SimpleDateFormat("dd-MM-yyyy");
		             String    dateGet=formatterB.format(date);
					 
						/* Calendar c = Calendar.getInstance();
						c.setTime(date); // Now use today date.
						c.add(Calendar.DATE, 2); // Adding 5 days
						String	dateGet = formatterB.format(c.getTime());
						//System.out.println("date is ::::::"+dateGet);*/
												
								%>
                                                <tr>
                                                    <td><%=(i+1)%></td>
                                                    <td><%=dateGet%></td>
                                                    <td><%=temp.get("time")%></td>
                                                    <td><%=temp.get("amount")%></td>
                                                    <td><%=MOP%></td>
                                                    <td><%=status%></td>
                                                 
                                                </tr>
                                               <%}%>
					
                                                </tbody>
                                           </table>
                                        </div>
                                        <%}else{%>
                                        <table style="border:1px solid #005CB9; width:99.48%; margin-bottom:2.50px;" class="border">
										<tr><td height="20"></td></tr>
										<tr><td height="20" align="center"><font color="#FF0000" size="3">Data is Not Available.</font></td></tr> 
										<tr><td height="20"></td></tr>
										</table> 
										<%}%>
                                    </div>
                				</div>
            			</div>
		</section>
		</div>
		</div>
		
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>
	
	<%@ include file="/footer.jsp" %>
	
	<!-- Javascript Files
	================================================== -->
	<script src="vendor/jquery-1.11.0.min.js"></script>
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

	
</body>

</html>