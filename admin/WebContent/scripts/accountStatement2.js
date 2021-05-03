function submitForm()
{
	var userType="";
	var stmt="";
	var id="";	
	var userType=document.myForm.accountType.value;

	if(userType=="select")
	{
   
		alert("Select Account Statement Type");
		return false;		
	
	}
	if(userType=="mds")
	{
		var fromdate=document.getElementById("fromdate").value;	
		var todate=document.getElementById("todate").value;		
		stmt=document.myForm.mdsstmt.value;
		id=document.myForm.mdsId.value;
		if(fromdate=="")
		{
			alert("Select the From Date");
			return false;
		}
		if(todate=="")
		{
			alert("Select the To Date");
			return false;
		}
		if(stmt=="select")
		{
			alert("select MDS Account Statement Type");
			return false;
		}
		if(stmt=="id")
		{
			if(id=="")
			{
				alert("Enter MD Account Statement ID");
				return false; 
			}
		}
	
	}
	if(userType=="mdsdetail")
	{
		var fromdate=document.getElementById("fromdate").value;	
		var todate=document.getElementById("todate").value;		
		 stmt=document.myForm.mdsstmtdet.value;
		 id=document.myForm.mdsIddet.value;
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select MDS Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter MD Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="ds")
	{
	     
		 stmt=document.myForm.dsstmt.value;
		 id=document.myForm.dsId.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Distributor Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter DS Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="dsdetail")
	{
	     
		 stmt=document.myForm.dsstmtdet.value;
		 id=document.myForm.dsIddet.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Distributor Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter DS Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="agent")
	{
	     
		 stmt=document.myForm.agentstmt.value;
		 id=document.myForm.agentId.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Agent Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter Agent Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="agentDetail")
	{
	     
		 stmt=document.myForm.agentstmtdet.value;
		 id=document.myForm.agentIddet.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Agent Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter Agent Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="adminUser")
	{
	     
		 stmt=document.myForm.adminstmt.value;
		 id=document.myForm.adminId.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Admin User Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter Admin Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	if(userType=="adminUserdetail")
	{
	     
		 stmt=document.myForm.adminstmtdet.value;
		 id=document.myForm.adminIddet.value;
		 var fromdate=document.getElementById("fromdate").value;	
			var todate=document.getElementById("todate").value;	
		 if(fromdate=="")
		 {
		 	alert("Select the From Date");
		 	return false;
		 }
		 if(todate=="")
		 {
		 	alert("Select the To Date");
		 	return false;
		 }
		 if(stmt=="select")
		 {
			 alert("select Admin User Account Statement Type");
			 return false;
		 }
		 if(stmt=="id")
		 {
			 if(id=="")
			 {
				 alert("Enter Admin Account Statement ID");
				 return false; 
				 }
		 }
		
	}
	
	

	document.myForm.action="accountStatementofAll.action?userType="+userType+"&stmt="+stmt+"&id="+id;
	document.myForm.submit();

}