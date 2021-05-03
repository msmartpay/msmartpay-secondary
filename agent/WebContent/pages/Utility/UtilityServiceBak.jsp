<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
	<script type="text/javascript">
	
	function MobileBillFormValidate(){
		var billpayOp=document.MobileBillForm.billpayOp.value;
		var connectionNo=document.MobileBillForm.connectionNo.value;
		var billAmount=document.MobileBillForm.billAmount.value;
		var billContactNo=document.MobileBillForm.billContactNo.value;
		
		if(billpayOp==""){
			return false;
		}else if(connectionNo==""){
			return false;
		}
		else if(billAmount==""){
			return false;
		}
		else if(billContactNo==""){
			return false;
		}else if(isNaN(billAmount)){
			alert('Please enter correct Amount');
			return false;
		}
		else if(parseInt(billAmount)>10000){
			alert('Amount should be less than 10000.');
			return false;
		}
		
		else{
			document.getElementById('submitMobileBillpay').style.display="none";
			document.getElementById("submitMobileBillpayloading").style.display="";
			//document.MobileBillForm.submit();
		}

	}
	function MobileLandlineFormValidate(){
		var billpayOp=document.LandlineBillForm.billpayOp.value;
		var connectionNo=document.LandlineBillForm.connectionNo.value;
		var billAmount=document.LandlineBillForm.billAmount.value;
		var billContactNo=document.LandlineBillForm.billContactNo.value;
		
		if(billpayOp==""){
			return false;
		}else if(connectionNo==""){
			return false;
		}
		else if(billAmount==""){
			return false;
		}
		else if(billContactNo==""){
			return false;
		}else if(isNaN(billAmount)){
			alert('Please enter correct Amount');
			return false;
		}
		else if(parseInt(billAmount)>10000){
			alert('Amount should be less than 10000.');
			return false;
		}
		
		else{
			document.getElementById('submitlandlineBillpay').style.display="none";
			document.getElementById("submitlandlineBillpayloading").style.display="";
			//document.MobileBillForm.submit();
		}

	}

	function Gas(){
		var billpayOp=document.GasBillForm.billpayOp.value;
		var connectionNo=document.GasBillForm.connectionNo.value;
		var billAmount=document.GasBillForm.billAmount.value;
		var billContactNo=document.GasBillForm.billContactNo.value;
		
		if(billpayOp==""){
			return false;
		}else if(connectionNo==""){
			return false;
		}
		else if(billAmount==""){
			return false;
		}
		else if(billContactNo==""){
			return false;
		}else if(isNaN(billAmount)){
			alert('Please enter correct Amount');
			return false;
		}
		else if(parseInt(billAmount)>10000){
			alert('Amount should be less than 10000.');
			return false;
		}
		
		else{
			document.getElementById('GasBillpay').style.display="none";
			document.getElementById("GasBillpayloading").style.display="";
			document.GasBillForm.submit();
		}

	}
	function Water(){
		var billpayOp=document.WaterBillForm.billpayOp.value;
		var connectionNo=document.WaterBillForm.connectionNo.value;
		var billAmount=document.WaterBillForm.billAmount.value;
		var billContactNo=document.WaterBillForm.billContactNo.value;
		
		if(billpayOp==""){
			return false;
		}else if(connectionNo==""){
			return false;
		}
		else if(billAmount==""){
			return false;
		}
		else if(billContactNo==""){
			return false;
		}else if(isNaN(billAmount)){
			alert('Please enter correct Amount');
			return false;
		}
		else if(parseInt(billAmount)>10000){
			alert('Amount should be less than 10000.');
			return false;
		}
		
		else{
			document.getElementById('WaterBillpay').style.display="none";
			document.getElementById("WaterBillpayloading").style.display="";
			document.WaterBillForm.submit();
		}

	}
	function InsuranceFormValidate(){

		var insurancePayOperator=document.InsuranceForm.insurancePayOperator.value;
		var policyHolderName=document.InsuranceForm.policyHolderName.value;
		var policyNumber=document.InsuranceForm.policyNumber.value;
		var dbo=document.InsuranceForm.dob.value;
		var premiumAmount=document.InsuranceForm.premiumAmount.value;
		var insuranceContactNo=document.InsuranceForm.insuranceContactNo.value;
		
		if(insurancePayOperator=="select"){
		alert('Please select Value');
		return false;
		}
		else if(policyHolderName==""){
		alert('Please Enter Value');
		return false;
		}
		
		else if(policyNumber==""){
		alert('Please Enter Value');
		return false;
		}
		else if(dbo==""){
		alert('Please select the Date of birth');
		return false;
		}
		
		else if(premiumAmount==""){
		alert('Please Enter Value');
		return false;
		}
		else if(isNaN(premiumAmount)){
		alert('Please enter correct Amount');
		return false;
		}
		else if(parseInt(premiumAmount)<10){
		alert('Amount should be 10 or greater than 10.');
		return false;
		}
		else if(parseInt(premiumAmount)>10000){
		alert('Amount should be less than 10000.');
		return false;
		}
		else if(insuranceContactNo==""){
		alert('Please Enter Value');
		return false;
		}
		else{
			document.getElementById('insurenceBillpay').style.display="none";
			document.getElementById("insurenceBillpayloading").style.display="";
			document.InsuranceForm.action="utilityService.do?param=InsurencePay";
			document.InsuranceForm.submit();
		}

	}

	function ElectricityFormValidate(){
		var ElectrictyOP=document.ElectricityForm.ElectrictyOP.value;
		var ElecContactNo=document.ElectricityForm.ElecContactNo.value;
		var ElectAccountNo=document.ElectricityForm.ElectAccountNo.value;
		var ElecBillAmount=document.ElectricityForm.ElecBillAmount.value;
		
		
		if(ElectrictyOP==""){
			return false;
		}else if(connectionNo==""){
			return false;
		}
		else if(ElecBillAmount==""){
			return false;
		}
		else if(ElecContactNo==""){
			return false;
		}else if(isNaN(ElecBillAmount)){
			alert('Please enter correct Amount');
			return false;
		}else if(ElectrictyOP=='MSEB (Maharashtra)')
		{
			var CycleNo=document.ElectricityForm.CycleNo.value;
			var BillUnit=document.ElectricityForm.BillUnit.value;
			if(CycleNo=""){
				alert('Enter Cycle No. Eg. (4631)');
				return false;
			}
			if(BillUnit=""){
				alert('Enter Bill Unit. Eg. (03)');
				return false;
			}
		}else if(ElectrictyOP=='Reliance Energy (Mumbai)')
		{
			var CycleNo=document.ElectricityForm.CycleNo.value;
			
			if(CycleNo=""){
				alert('Enter Cycle No. Eg. (4631)');
				return false;
			}
			
		}
		else if(parseInt(ElecBillAmount)>15000){
			alert('Amount should be less than 15000.');
		return false;
		}
		else{
			document.getElementById('submitElectricity').style.display="none";
			document.getElementById("submitElectricityloading").style.display="";
			document.ElectricityForm.submit();
		}
	}

	function populate()
	{
		
		var op=document.ElectricityForm.ElectrictyOP.value;
		//alert('here'+op);
		if(op=='UTTAR PRADESH POWER CORPORATION LTD')
		{
			document.getElementById('mseb1').style.display="none";
			document.getElementById('mseb2').style.display="none";
		}
		else if(op=='MSEB (Maharashtra)')
		{
			document.getElementById('mseb1').style.display="";
			document.getElementById('mseb2').style.display="";
		}else if(op=='Reliance Energy (Mumbai)'){
			document.getElementById('mseb1').style.display="";
			document.getElementById('mseb2').style.display="none";
		}
		else
		{
			document.getElementById('mseb1').style.display="none";
			document.getElementById('mseb2').style.display="none";
		}
		
	}
	
	</script>
		
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">


			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                
                                	<div class="col-md-12 form-group"><h3 class="box-title">Utility Payments</h3></div>
                                    <div class="col-md-6">
                                                                        
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#billpay" data-toggle="tab">Mobile Bill</a></li>
                                                <li><a href="#landline" data-toggle="tab">Landline</a></li>
                                                <li><a href="#electricity" data-toggle="tab"> Electricity </a></li>
												<li><a href="#insurance" data-toggle="tab"> Insurance </a></li>
												<li><a href="#gas" data-toggle="tab"> Gas </a></li>
                                                <li><a href="#water" data-toggle="tab"> Water </a></li>
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="billpay">
                                                    <form name="MobileBillForm" method="post" onsubmit="MobileBillFormValidate()" action="utilityService.action?param=MobileBillpay">
                                                        <div class="form-group">
                                                           
                                                            <select name="billpayOp" class="form-control" required>
                                                                <option value="">-------------- Select Postpaid Mobile Operator Name -------------</option>
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'POSTPAID')}">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                        <!-- <div class="form-group">
                                                            
                                                            <select class="form-control" name="billpayCircle" required>
                                                                <option value="">-- Select --</option>
                                                                <option selected="selected">All</option>
                                                            </select>
                                                        </div> -->
                                                        <input type="hidden"  name="billpayCircle" value="All" >
                                                        
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="connectionNo" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="billAmount" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Bill Amount">
                                                        </div>
                                                        <div class="form-group">
                                                             <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />                                               
                                                            <input type="Text" required="required"  name="billContactNo" onkeypress="return digitonly(this,event)" maxlength="12" class="form-control" placeholder="Enter Contact Number">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" id="submitMobileBillpay" class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                    	<img id="submitMobileBillpayloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                                
                                                <div class="tab-pane fade" id="landline">
                                                    <form name="LandlineBillForm" method="post" onsubmit="MobileLandlineFormValidate()" action="utilityService.action?param=MobileBillpay">
                                                        <div class="form-group">
                                                           
                                                            <select name="billpayOp" class="form-control" required>
                                                                <option value="">-------------- Select Postpaid Mobile Operator Name -------------</option>
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'LANDLINE') }">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                <!-- <option value="Airtel Postpaid">Mobile - Airtel</option>
																 <option value="Airtel Landline">Landline - Airtel</option>
																 <option value="BSNL PostPaid">Mobile - BSNL</option>
																 <option value="Cellone PostPaid">Landline - BSNL</option>
																 <option value="Vodafone Postpaid">Mobile - Vodafone</option>
																 <option value="IDEA Postpaid">Mobile - Idea</option>
																 <option value="Docomo Postpaid">Mobile - Docomo</option>
																 <option value="Reliance Mobile">Mobile - Reliance</option> -->
                                                            </select>
                                                        </div>
                                                        <!-- <div class="form-group">
                                                            
                                                            <select class="form-control" name="billpayCircle" required>
                                                                <option value="">-- Select --</option>
                                                                <option selected="selected">All</option>
                                                            </select>
                                                        </div> -->
                                                        <input type="hidden"  name="billpayCircle" value="All" >
                                                        
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="connectionNo" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" required="required" name="billAmount" onkeypress="return digitonly(this,event)" maxlength="5" class="form-control" placeholder="Enter Bill Amount">
                                                        </div>
                                                        <div class="form-group">
                                                             <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />                                               
                                                            <input type="Text" required="required"  name="billContactNo" onkeypress="return digitonly(this,event)" maxlength="12" class="form-control" placeholder="Enter Contact Number">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" id="submitlandlineBillpay"  class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                    	<img id="submitlandlineBillpayloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                                
                                                <div class="tab-pane fade" id="insurance">
                                                    <form name="InsuranceForm" method="post" onsubmit="InsuranceFormValidate();" action="utilityService.action?param=InsurencePay">
                                                        <div class="form-group">
                                                            
                                                            <select class="form-control" name="insurancePayOperator" required>
                                                                <option value="">------------ Select Insurance Company ------------</option>
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'INSURANCE')}">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                <!-- <option value="Bajaj Allianz Life">Bajaj Allianz Life</option>
																 <option value="ICICI Pru Life">ICICI Pru Life</option>
																  <option value="TATA AIA Life">TATA AIA Life</option>
																 <option value="Tata AIG Life">Tata AIG Life</option>
																 <option value="IndiaFirst Life">IndiaFirst Life</option>
																 <option value="Birla Sun Life">Birla Sun Life</option>
																  <option value="DLF Pramerica">DLF Pramerica</option>
																  <option value="ING Vysya Life">ING Vysya Life</option>
																  <option value="L&T General">L&T General</option>
																  <option value="Life Insurance Corporation">LIC</option>
                                                            --></select>
                                                        </div>
                                                        <div class="form-group">
                                                                                                             
                                                            <input type="Text" class="form-control" name="policyHolderName" placeholder="Enter Policy Holder Name">
                                                        </div>
                                                        <div class="form-group">
                                                                                                           
                                                            <input type="Text" class="form-control" name="policyNumber" placeholder="Enter Policy Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="text" class="form-control" name="dob" placeholder="Enter Date of Birth in DD/MM/YY Format">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" class="form-control" name="premiumAmount" placeholder="Enter Premium Amount">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" class="form-control" name="insuranceContactNo" placeholder="Enter Your Contact Number">
                                                        </div>
                                                        <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" id="insurenceBillpay"  class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                        <img id="insurenceBillpayloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                                <div class="tab-pane fade" id="electricity">
                                                    <form name="ElectricityForm" method="post" onsubmit="ElectricityFormValidate()" action="utilityService.action?param=ElectricityPay">
                                                        <div class="form-group">
                                                            
                                                            <select name="ElectrictyOP" onchange="populate()" class="form-control" required>
                                                                <option value="">----------- Select Electricity Operator Name ------------</option>
                                                                
                                                                 <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'ELECTRICITY')}">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                
							                                   	 
																 
                                                            </select>
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input name="ElectAccountNo" maxlength="15" required="required" type="Text" class="form-control" placeholder="Enter Your Account ID">
                                                        </div>
                                                        <div class="form-group" id="mseb1" style="display: none;">
                                                           <input name="CycleNo" maxlength="4"  type="Text" class="form-control" placeholder="Enter Cycle No. Eg. (4631)">
                                                        </div> 
                                                        <div class="form-group" id="mseb2" style="display: none;">
                                                            <input name="BillUnit" id="BillUnit" maxlength="2"  type="Text" class="form-control" placeholder="Enter Bill Unit Eg. (02)">
                                                        </div>
                                                        <div class="form-group">
                                                            <input name="ElecBillAmount" required="required" type="Text" class="form-control" placeholder="Enter Bill Amount">
                                                        </div>
                                                        <div class="form-group">
                                                            <input type="Text" maxlength="12" name="ElecContactNo" required="required" class="form-control" placeholder="Enter Your Contact Number">
                                                        </div>
                                                        <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button  type="submit" id="submitElectricity"  class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                        <img id="submitElectricityloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
												
												<div class="tab-pane fade" id="water">
                                                    <form  method="POST" name="WaterBillForm" onsubmit="Water()" action="utilityService.action?param=MobileBillpay">
                                                        <div class="form-group">
                                                            
                                                            <select class="form-control" name="billpayOp" required>
                                                                <option value="">-- Select Operator Name --</option>
                                                                
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'WATER') }">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" name="connectionNo" class="form-control" placeholder="Enter Your Account Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                           
                                                            <input type="Text" name="billAmount" onkeypress="return digitonly(this,event)" class="form-control" placeholder="Enter Bill Amount">
                                                        </div>
                                                        <div class="form-group">
                                                             <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />                                                
                                                            <input type="Text" class="form-control" name="billContactNo" onkeypress="return digitonly(this,event)" placeholder="Enter Your Contact Number">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" id="WaterBillpay" class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                        <img id="WaterBillpayloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
												
												
												<div class="tab-pane fade" id="gas">
                                                    <form  name="GasBillForm" method="POST" action="utilityService.action?param=MobileBillpay">
                                                        <div class="form-group">
                                                            
                                                            <select class="form-control" name="billpayOp" required>
                                                                <option value="">-- Select Operator Name --</option>
                                                                
                                                                <c:forEach var="serviceoperatorlist" items="${sessionScope.operatorList}">
																	<c:if
																		test="${fn:containsIgnoreCase(serviceoperatorlist.SubService,'GAS')}">
																		<option value="${serviceoperatorlist.OperatorName}">${serviceoperatorlist.DisplayName}</option>
																	</c:if>
																</c:forEach>
                                                                
                                                            </select>
                                                        </div>
                                                        
                                                        <div class="form-group">
                                                                                                             
                                                            <input type="Text" name="connectionNo" class="form-control" placeholder="Enter Your Account Number">
                                                        </div>
                                                        <div class="form-group">
                                                                                                            
                                                            <input type="Text" name="billAmount" onkeypress="return digitonly(this,event)" class="form-control" placeholder="Enter Bill Amount">
                                                        </div>
                                                        <div class="form-group">
                                                              <input type="hidden" name="ipaddress" value="<%=ipaddress%>" />
								   							<input type="hidden" name="Service" value="BillPay" />                                                
                                                            <input type="Text" class="form-control" name="billContactNo" onkeypress="return digitonly(this,event)" placeholder="Enter Your Contact Number">
                                                        </div>
                                                        <button type="reset" class="btn btn-inverse waves-effect waves-light">Reset</button>
                                                        <button type="submit" id="GasBillpay" onclick="Gas()" class="btn btn-success waves-effect waves-light m-r-10">Pay Bill</button>
                                                        <img id="GasBillpayloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6" align="right">
                                    
                                        <div class="spacer-xl"></div>
                                        
                                    	<div class="cta-inner">
                                            <div class="cta-txt">
                                                <h2>${sessionScope.companyName }</h2>
                                                <p>Additional Rs.4 charged on Any Bill Payments my be applied. <br> <span style="font-size:14px; font-style:italic; color:#000;"> AEPS Coming Soon </span></p>
                                                
                                                <c:if test="${sessionScope.clientId!=null && !sessionScope.clientId eq '10004' }">
                                                <h3>Android Application</h3>
                                                <p><a  title="Download Android App" href="https://play.google.com/store/apps/details?id=com.smartkinda" target="_blank"><img height="60" width="150" src="images/androidapp.png" alt="Logo"></a></p>
                                           		</c:if>
                                            </div>												
                                        </div>
                                    </div>
                                </div>
                        </div>
					</div>
				</div>
                </div>
         	</section>
         	
         	
	<!-- Page Content / End -->
    
			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
			<!-- Footer / End -->
			
		</div>
		<!-- Main / End -->
	</div>
	
	
	
	
	
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

	<script src="js/custom.js"></script>


	
</body>

</html>