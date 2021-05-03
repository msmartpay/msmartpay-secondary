<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta name="Description" content=" Smart Kinda :  India's leading online travel portal offers wide range of online travel services through agents and distributors in India. We also provide b2b travel services, travel portal api, travel portal solution, travel portal development and travel portal white label in India."/>
	<meta name="Keywords" content="Smart Kinda,Recharge Online,Recharge,Billpay online,Billpay, B2B Travel Agency,B2B Travel Company in India,B2B Travel Portal Company India,B2B Travel Portal India,B2B Travel Services,B2B Travel Sites, Online Travel Agency Business Opportunities, Online Travel Companies in India, Travel Portal Development, Travel Portal White Label, Travel Portal API "/>
	<meta name="language" content="EN"/>
	<meta name="copyright" content="Smart Kinda"/>
	<meta name="robots" content="index, follow"/>
	<meta name="revisit-after" content="daily"/>
	<meta name="Robots" content="index, all"/>
	<meta name="allow-search" content="yes"/>
	<meta name="revisit-after" content="daily"/>
	<meta name="Rating" content="General"/>
	<meta name="distribution" content="global"/>
	
	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>

	<script >

	function populatecities()
	{
		var xmlhttp;
		var state="";
		state=document.getElementById("state").value; 
		var url = "distDetails.action?state="+state;	  
		if (window.XMLHttpRequest)
	  	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  		xmlhttp=new XMLHttpRequest();
	  	}
		else
	  	{// code for IE6, IE5
	  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  	}
		xmlhttp.onreadystatechange=function()
	  	{
	  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    	{
				document.getElementById("distirct").innerHTML=xmlhttp.responseText;
	    	}
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send();
	}
	
	
</script>

	<script>
	function getmobileapps(){
		//alert('Comming Soon.');
	  window.open('mobileappsAction.do?param=ReqMobileapps','popup','width=520,height=520,resizable=yes,scrollbars=no');

	}
	function digitonly(input,evt)
	{
		var keyCode = evt.which ? evt.which : evt.keyCode;
				var lisShiftkeypressed = evt.shiftKey;
				if(lisShiftkeypressed && parseInt(keyCode) != 9) {
				return false ;
				}
				if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
				return true;
				}

				return false;

	}
	
	
	
	function submitForm()
	{
	   
		if(mandatoryfill()){
			document.getElementById('submitdthconn').style.display="none";
			document.getElementById("submitdthconnloading").style.display="";
			document.DTHConnection.submit();
		}
		else{
			return false;
		}
		
	}
	function mandatoryfill()
	{	
		
		var f1=document.DTHConnection;
		 var mobileStart=document.getElementById("txtmobile").value.substring(0,1);	
		
	   if(f1.productName.value=="-1")
		{
			alert("Select Product");
			f1.state.focus();
			return false;
		} 
		
		if(f1.productNameDesc.value=="-1")
		{
			alert("Select Product Description");
  			f1.mobile.focus();
 			return false;
		}
	  
	   
		if(f1.custName.value=="")
		{
			alert("Enter your Name");
			f1.accNo.focus();
			return false;
		} 
		
		var iChars = "!@#$%^&*()+=-[]\\\';,/{}|\":<>?";
            for (var i = 0; i <f1.custName.value.length; i++) {
               if (iChars.indexOf(f1.custName.value.charAt(i)) != -1) {
                alert ("The Customer has special characters. \nThese are not allowed.\n");
                  return false;
        		}
         	}
		if(f1.address.value=="")
		{
			alert("Enter Address");
  			f1.amt.focus();
 			return false;
		}
		if(f1.stateName.value=="-1")
		{
			alert("Select State");
  			f1.mobile.focus();
 			return false;
		}
		if(f1.district.value=="select")
		{
			alert("Select District");
  			f1.mobile.focus();
 			return false;
		}
		
		if(f1.city.value=="")
		{
			alert("Enter City");
  			f1.amt.focus();
 			return false;
		}
		
		if(f1.pinCode.value=="")
		{
			alert("Enter Pin Code");
  			f1.amt.focus();
 			return false;
		}
		
		if(f1.MobNo.value=="")
		{
			alert("Enter Mobile No.");
  			f1.amt.focus();
 			return false;
		}
		
		if(!(mobileStart == "9" ||  mobileStart == "8"  || mobileStart == "7"))
		{
			alert("Mobile Number should start with 9,8 or 7");
			document.getElementById("txtmobile").value="";
			document.getElementById("txtmobile").focus();
			return false;
		} 
		 
	  	var x=window.confirm("Are you sure you want to continue ?")
		if (x)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	function funChange(){

		document.getElementById('reqId').selectedIndex=0;
		document.getElementById('userInfo').selectedIndex=0;
		document.getElementById('txtname').value="";
		document.getElementById('textarea').value="";
		document.getElementById('ddlstate').selectedIndex=0;
		document.getElementById('txtcity').value="";
		document.getElementById('txtpincode').value="";
		document.getElementById('txtmobile').value="";
		document.getElementById('amount').value="";
	}
	
	function digitonly(input,evt)
	{
		var keyCode = evt.which ? evt.which : evt.keyCode;
				var lisShiftkeypressed = evt.shiftKey;
				if(lisShiftkeypressed && parseInt(keyCode) != 9) {
				return false ;
				}
				if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
				return true;
				}

				return false;

	}

	function funChange1(){

	document.getElementById('userInfo').selectedIndex=0
	}
	
	
	function submitRequest()
	{
		var xmlhttp;
	 	var type='All Circle';

	   	var id=document.getElementById("reqId").value;
	   	if(document.getElementById("reqId").value.replace(/^\s+|\s+$/, '')=="")
	   	{
		   	alert("Enter ID");
		   	document.getElementById("reqId").focus();
		   	return false;
	   	}
	   	var url = "dthConnection.action?param=productDescription&type="+encodeURIComponent(type)+"&id="+encodeURIComponent(id);	  
		if (window.XMLHttpRequest)
	  	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  		xmlhttp=new XMLHttpRequest();
	  	}
		else
	  	{// code for IE6, IE5
	  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  	}
		xmlhttp.onreadystatechange=function()
	  	{
	  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    	{
	    		document.getElementById("userInfo").innerHTML="<option value=-1>---------------------  Select Product --------------------</option>"+xmlhttp.responseText;
	    	}
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send();
	}


	function populatePrice()
	{
		var xmlhttp;
		var type='All Circle';

	   	var id=document.getElementById("reqId").value;
	  	var desc=document.getElementById("userInfo").value;
	   	if(document.getElementById("userInfo").value.replace(/^\s+|\s+$/, '')=="")
	   	{
		   	alert("select product type");
		   	document.getElementById("userInfo").focus();
		   	return false;
	   	}
		
		var url = "dthConnection.action?param=productPrice&type="+encodeURIComponent(type)+"&id="+encodeURIComponent(id)+"&desc="+encodeURIComponent(desc);	  
	   	if (window.XMLHttpRequest)
	  	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  		xmlhttp=new XMLHttpRequest();
	  	}
		else
	  	{// code for IE6, IE5
	  		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  	}
		xmlhttp.onreadystatechange=function()
	  	{
	  		if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    	{
	  			document.DTHConnection.amount.value=xmlhttp.responseText;
	    	}
	  	}
		xmlhttp.open("post",url,true);
		xmlhttp.send();
	}
	
	</script>

	<%

	ArrayList totalOptrLocation=(ArrayList)session.getAttribute("operatorListDthConLoc");
	ArrayList totalOptrProduct=(ArrayList)session.getAttribute("operatorListDthConProduct");
%>

</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container" style="width:90%;">
					<div class="row">
						<div class="col-md-12">
							<h1>DTH Connection Request</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content">
				<div class="container" style="width:90%;">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg2.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="box">
                                                                        
                                            
                                            <%if(request.getAttribute("message")!=null){ %>
                                            <div class="tabs" style="color: RED; font-weight: bold;">
                                            <%=request.getAttribute("message") %>
                                            </div>
                                            <%} %> 
                                            
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#mobile" data-toggle="tab">Airtel Dth Connection Request</a></li>
                                                <!-- <li><a href="#dth" data-toggle="tab">DTH</a></li>
                                                <li><a href="#datacard" data-toggle="tab">DATA CARD</a></li> -->
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div class="tab-pane fade in active" id="mobile">
                                                    <form name="DTHConnection" method="post" action="dthConnection.action?param=dthConnectionRequest">
                                                        <%-- <div class="form-group">
                                                            
                                                            <select class="form-control" name="locationName" id="requser" onChange="funChange();" >
								                          	<option selected="selected" value="all">All Circle</option>
								                          	<%
																	if(totalOptrLocation.size()>0){
																	//Collections.sort(totalOptrLocation);
																   	int length=totalOptrLocation.size();
																   	String Location="";
															  	   	for(int i=length-1;i>-1;i--) {
															  		 	Location=(String)totalOptrLocation.get(i);
															  		 	Location=Location.trim();  
															 %>
								                         		<option value="<%=Location%>"><%=Location%></option>
								                         	<%}} %>
								                         </select>
                                                        </div> --%>
                                                        <div class="form-group">
                                                               <select class="form-control" name="productName"  id="reqId" onChange="funChange1(); submitRequest();" >
                         											<option selected="selected" value="-1">---------------------- Select Product Name ----------------------</option>
										                         <%
																	 	if(totalOptrProduct.size()>0){
																		//Collections.sort(totalOptrProduct);
																		int length=totalOptrProduct.size();
																		String Product="";
																	  	for(int i=length-1;i>-1;i--) {
																	  	Product=(String)totalOptrProduct.get(i);
																	  	Product=Product.trim();  
															
																%>
																	 
																	    <option value="<%=Product%>"><%=Product%></option>
										
																<% }}%>
										                         
										                         </select>
                                                        </div>
                                                        <div class="form-group">
															<select class="form-control" name="productNameDesc"  id="userInfo" onChange="populatePrice()" >
														 		<option selected="selected" value="-1">-------------------------- Select Product --------------------------</option> 
								                                    
								                        	</select>                                                        
								                        	
								                        </div>
								                        <div class="form-group">
                                                        	<input type="text" class="form-control" readonly="readonly"  placeholder="Enter Product Price" id="amount" name="Amount"  >                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<input type="text" class="form-control" placeholder="Enter Customer Name" name="custName" maxlength="35" id="txtname"  >                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<textarea name="address" placeholder="Enter Full Address" class="form-control" id="textarea" cols="20" rows="1"   maxlength="100"></textarea>                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<select class="form-control" name="stateName"  onchange="populatecities()" id="state"  >
							                          				<option value="-1">---------------------- Select State ----------------------</option>
								                                    <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
								                                    <option value="Andhra Pradesh">Andhra Pradesh </option>
								                                    <option value="Arunachal Pradesh">Arunachal Pradesh </option>
								                                    <option value="Assam">Assam </option>
								                                    <option value="Bihar">Bihar </option>
								                                    <option value="Chandigarh">Chandigarh </option>
								                                    <option value="Chhattisgarh">Chhattisgarh </option>
								                                    <option value="Delhi">Delhi </option>
								                                    <option value="Goa">Goa </option>
								                                    <option value="Gujarat">Gujarat </option>
								                                    <option value="Haryana">Haryana </option>
								                                    <option value="Himachal Pradesh">Himachal Pradesh </option>
								                                    <option value="Jammu and Kashmir">Jammu and Kashmir </option>
								                                    <option value="Jharkhand">Jharkhand </option>
								                                    <option value="Karnataka">Karnataka </option>
								                                    <option value="Kerala">Kerala </option>
								                                    <option value="Madhya Pradesh">Madhya Pradesh </option>
								                                    <option value="Maharashtra">Maharashtra </option>
								                                    <option value="Manipur">Manipur </option>
								                                    <option value="Meghalaya">Meghalaya </option>
								                                    <option value="Mizoram">Mizoram </option>                                    
								                                    <option value="Nagaland">Nagaland </option>
								                                    <option value="Orissa">Orissa </option>
								                                    <option value="Pondicherry">Pondicherry</option>
								                                    <option value="Punjab">Punjab </option>
								                                    <option value="Rajasthan">Rajasthan </option>
								                                    <option value="Sikkim">Sikkim </option>
								                                    <option value="Tamil Nadu">Tamil Nadu </option>
								                                    <option value="Tripura">Tripura </option>
								                                    <option value="Uttarakhand">Uttarakhand </option>
								                                    <option value="Uttar Pradesh">Uttar Pradesh </option>
								                                    <option value="West Bengal">West Bengal</option>
							                            	</select>                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<select class="form-control" name="district"  id="distirct" >
                          
                          										<option value="">---------------------- Select District ----------------------</option>
                          									</select>                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<input type="text" name="city" placeholder="Enter City Name" maxlength="30" id="txtcity" class="form-control"   >                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<input type="text" onKeyPress="return digitonly(this,event)" placeholder="Enter Pincode" maxlength="6" name="pinCode"  id="txtpincode"  class="form-control"  >                                                     
                                                        </div>
                                                        <div class="form-group">
                                                        	<input type="text" onKeyPress="return digitonly(this,event)" placeholder="Enter Contact Number" maxlength="10" name="MobNo"  id="txtmobile" class="form-control"  >                                                     
                                                        </div>
                                                        <input type="hidden" name="ipaddress" value="<%=ipaddress%>"/> 
                                                        <button type="submit" id="submitdthconn" onclick="submitForm()" class="btn btn-primary btn-inline">Submit Request</button>
                                                    	<img id="submitdthconnloading" alt="Loaging...." style="display: none;" height="100" width="300" src="images/please_wait.gif">
                                                    </form>
                                                </div>
                                                
                                            </div>
                                        </div>
                                    </div>
                                    </div>
                                    <div class="col-md-6">
                                    	
                                        
                                    	<div class="cta-inner">
                                            <div class="cta-txt">
                                            
                                            	<img alt="Connection Process" height="500" width="600" src="images/installation-process.jpg">    
                                                
                                            </div>							
                                        </div>
                                    </div>
                                </div>
                        </div>
					</div>
				</div>
                
                <div class="spacer-xl"></div>
                <div class="row">
                    <div class="col-md-12">
                       <h2>Welcome to Word of <%=companyName %></h2>
                        <p>Safe and Instant Recharge, Billpayment Service And Many more...</p>
                        <p>A unique E-Solution at single platform.</p>
                        <p>We promise to provide you with outstanding service that you can trust for all of your E-Service needs.</p>
                        <a href="#" class="btn btn-primary">Travel Coming Soon</a>
                    </div>
                </div>

                
            </div>
		</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>


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