function chkForm()
{	
	var initial=document.prifileForm.initial.value;
	
	if(initial=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Initial </p></html>';	
		document.prifileForm.initial.focus();
		return false;
	}
	var fName=document.prifileForm.fName.value;
	if(fName.replace(/^\s+|\s+$/, '')=="")
	{	
		document.getElementById("errField").innerHTML='<p>Enter First Name </p></html>';	
		document.prifileForm.fName.focus();
		return false;
	}
	var lName=document.prifileForm.lName.value;
	if(lName.replace(/^\s+|\s+$/, '')=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter Last Name </p></html>';	
		document.prifileForm.lName.focus();
		return false;
	}
	var userName=document.prifileForm.userName.value;
	if(userName.replace(/^\s+|\s+$/, '')=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter UserName </p></html>';	
		document.prifileForm.userName.focus();
		return false;
	}
	var mobileNo=document.prifileForm.mobileNo.value;
	if(mobileNo.replace(/^\s+|\s+$/, '')=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter Primary Mobile No </p></html>';	
		document.prifileForm.mobileNo.focus();
		return false;
	}
	var altMobileNo=document.prifileForm.altMobileNo.value;
	if(altMobileNo.replace(/^\s+|\s+$/, '')=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter Secondary Mobile No </p></html>';	
		document.prifileForm.altMobileNo.focus();
		return false;
	}
	var emailId=document.prifileForm.emailId.value;
	if(emailId.replace(/^\s+|\s+$/, '')=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter Emai-Id </p></html>';	
		document.prifileForm.emailId.focus();
		return false;
	}
	return true;
}