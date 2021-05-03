function chkForm()
{		
	
	var fname=document.profileForm.firstName.value.replace(/^\s+|\s+$/, '');
	if(fname=="")
	{
	alert("Please enter first name");
	document.profileForm.firstName.focus();
	return false;
	}
	if (fname != "") {
if ( /[^A-Za-z\.]/.test(fname)) {
alert("Please enter only letter and numeric characters in First Name.");
document.profileForm.firstName.focus();
return (false);
}
}
	var lname=document.profileForm.lastName.value.replace(/^\s+|\s+$/, '');
	if(lname=="")
	{
	alert("Please enter last name");
	document.profileForm.lastName.focus();
	return false;
	}
if (lname != "") {
if ( /[^A-Za-z\.]/.test(lname)) {
alert("Please enter only letter and numeric characters in Last Name.");
document.profileForm.lastName.focus();
return (false);
}
}
	
	var dob=document.profileForm.dob.value;
	if(dob=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter dob </p></html>';	
		document.profileForm.dob.focus();
		return false;
	}
	var gender=document.profileForm.gender.value;
	if(gender=="")
	{
		document.getElementById("errField").innerHTML='<p>Select gender </p></html>';	
		document.profileForm.gender.focus();
		return false;
	}
	var maritalStatus=document.profileForm.maritalStatus.value;
	if(maritalStatus=="")
	{
		document.getElementById("errField").innerHTML='<p>Select marital Status </p></html>';	
		document.profileForm.maritalStatus.focus();
		return false;
	}

	var father=document.profileForm.fatherName.value.replace(/^\s+|\s+$/, '');
	if(father=="")
	{
	alert("Please enter father/Husband name.");
	document.profileForm.fatherName.focus();
	return false;
	}
	for (var i = 0; i < father.length; i++) {
	    if (father.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in Father name\n");
	    document.profileForm.fatherName.focus();
	    return false;
	        }
	    }
	
	if (father != "") {
		if ( /[^A-Za-z\s\.]/.test(father))  {
alert("Please enter only letter,space and numeric characters in Father Name.");
document.profileForm.fatherName.focus();
return (false);
}
}

	var mname=document.profileForm.motherName.value.replace(/^\s+|\s+$/, '');
	if(mname=="")
	{
	alert("Please enter Mother's name.");
	document.profileForm.motherName.focus();
	return false;
	}
	for (var i = 0; i < mname.length; i++) {
	    if (fname.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in Mother Name\n");
	    document.profileForm.motherName.focus();
	    return false;
	        }
	    }

	if (mname != "") 
	{
	if ( /[^A-Za-z\s\.]/.test(mname) && mname.replace(/^\s+|\s+$/, '')=="") 
	{
	alert("Please enter only letter and numeric characters in Mother Name.");
	document.profileForm.motherName.focus();
	return (false);
	}
	}
	if(document.profileForm.occupation.value=="select")
	{
	alert("Please select occupation.");
	document.profileForm.occupation.focus();
	return false;
	}
	
	if(document.profileForm.occupation.value=="other")
	{
	var occ=document.profileForm.otherOccupation.value.replace(/^\s+|\s+$/, '');			
	if(occ=="")
		{
			alert("Please enter occupation.");
			document.profileForm.otherOccupation.focus();
			return false;
		}
	}
var occp=document.profileForm.otherOccupation.value.replace(/^\s+|\s+$/, '');	
if (occp ==null) {
	alert('Please enter Occupation Name.');
	return false;
}

if(document.profileForm.companyType.value=="select")
{
alert("Please select company type.");
document.profileForm.companyType.focus();
return false;
}
if(document.profileForm.companyType.value=="other")
{
	var com=document.profileForm.otherCompanyType.value.replace(/^\s+|\s+$/, '');	
	
		if(com == " ")
		{
		alert("Please enter other company type.");
		document.profileForm.otherCompanyType.focus();
		return false;
		}
}
var compname=document.profileForm.companyName.value.replace(/^\s+|\s+$/, '');
if(compname=="")
{
alert("Please enter company name.");
document.profileForm.companyName.focus();
return false;

}

if(document.profileForm.designation.value=="select"){
	alert("Please select designation.");
	document.profileForm.designation.focus();
	return false;

	}
	if(document.profileForm.designation.value=="other"){
		var design=document.profileForm.otherDesignation.value.replace(/^\s+|\s+$/, '');
	if(design==""){
	alert("Please enter designation.");
	document.profileForm.otherDesignation.focus();
	return false;
	}
	}
	var design=document.profileForm.otherDesignation.value.replace(/^\s+|\s+$/, '');
	if (design != "") {
		alert("Please enter designation name.");
	
	return false;
	}
	var add1=document.profileForm.addressLine1.value.replace(/^\s+|\s+$/, '');
	if(add1=="")
	{
	alert("Please enter address.");
	document.profileForm.addressLine1.focus();
	return false;
	}
	
	if(document.profileForm.addressLine2.value==""){
	document.profileForm.addressLine2.value="";

	}	
	
	var country=document.profileForm.country.value;
	if(country=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Office Country </p></html>';	
		document.profileForm.country.focus();
		return false;
	}
	
	var state=document.profileForm.state.value;
	if(state=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Office State </p></html>';	
		document.profileForm.state.focus();
		return false;
	}
	var district=document.profileForm.district.value;
	if(district=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Office district </p></html>';	
		document.profileForm.district.focus();
		return false;
	}
	if(document.profileForm.city.value.replace(/^\s+|\s+$/, '')=="")
	{
		alert("Please select City.");
		document.profileForm.city.focus();
		return false;

	}
	var city=document.profileForm.city.value;
	for (var i = 0; i <city.length; i++) {
	    if (city.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in City\n");
	    document.profileForm.city.focus();
	    return false;
	        }
	    }

	if ( /[^A-Za-z\s]/.test(city))  
	{
	alert("Please enter only letter in city.");
	document.profileForm.city.focus();
	return (false);
	}
	var landmark=document.profileForm.landmark.value;
	for (var i = 0; i <landmark.length; i++) {
	    if (landmark.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in Landmark\n");
	    document.profileForm.landmark.focus();
	    return false;
	        }
	    }


	if ( /[^A-Za-z_#,.\/\s\d]/.test(landmark))
	{
		alert("Please enter only letter,numbers and (#_,./) in LandMark.");
		document.profileForm.landmark.focus();
		return (false);
	}
		

	if(document.profileForm.landmark.value==""){
	document.profileForm.landmark.value="";

	}

	
	
	
	var pincode=document.profileForm.pincode.value;
	if(pincode=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter office pinCode</p></html>';	
		document.profileForm.pincode.focus();
		return false;
	}
	var mobileNo=document.profileForm.mobileNo.value.replace(/^\s+|\s+$/, '');

	if(mobileNo==""){
	alert("Please enter  mobile number.");
	document.profileForm.mobileNo.focus();
	return false;
	}
	
	if(mobileNo.length<10){
		alert("Invalid mobile number.");
		document.profileForm.mobileNo.focus();
		return false;

		}
	var alternateMobileNo=document.profileForm.alternateMobileNo.value.replace(/^\s+|\s+$/, '');

	if(alternateMobileNo==""){
	alert("Please enter alternate mobile number.");
	document.profileForm.alternateMobileNo.focus();
	return false;

	}
	if(alternateMobileNo.length<10){
	alert("Invalid alternate mobile number.");
	document.profileForm.alternateMobileNo.focus();
	return false;

	}
	
	var resadd=document.profileForm.residentialAddressLine1.value.replace(/^\s+|\s+$/, '');
	if(resadd=="")
	{
	alert("Please enter residential address.");
	document.profileForm.residentialAddressLine1.focus();
	return false;

	}

	var add2=document.profileForm.residentialAddressLine2.value;
	
	
	if(document.profileForm.residentialAddressLine2.value==""){
	document.profileForm.residentialAddressLine2.value="";

	}
	
	
	
	var resCountry=document.profileForm.resCountry.value;
	if(resCountry=="")
	{
		document.getElementById("errField").innerHTML='<p>select Residential country</p></html>';	
		document.profileForm.resCountry.focus();
		return false;
	}
	var resState=document.profileForm.resState.value;
	if(resState=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Residential State</p></html>';	
		document.profileForm.resState.focus();
		return false;
	}
	var resDistrict=document.profileForm.resDistrict.value;
	if(resDistrict=="")
	{
		document.getElementById("errField").innerHTML='<p>Select Residential District</p></html>';	
		document.profileForm.resDistrict.focus();
		return false;
	}
	if(document.profileForm.resCity.value.replace(/^\s+|\s+$/, '')=="")
	{
		alert("Please select City.");
		document.profileForm.resCity.focus();
		return false;

	}
	var city=document.profileForm.resCity.value;
	for (var i = 0; i <city.length; i++) {
	    if (city.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in Res City\n");
	    document.profileForm.resCity.focus();
	    return false;
	        }
	    }

	if ( /[^A-Za-z\s]/.test(city))  
	{
	alert("Please enter only letter in city.");
	document.profileForm.resCity.focus();
	return (false);
	}
	var landmark=document.profileForm.resLandmark.value;
	for (var i = 0; i <landmark.length; i++) {
	    if (landmark.indexOf("  ") != -1) {
	    alert ("no more than one space is allowed in Res Landmark\n");
	    document.profileForm.resLandmark.focus();
	    return false;
	        }
	    }
	if ( /[^A-Za-z_#,.\/\s\d]/.test(landmark))
	{
		alert("Please enter only letter,numbers and (#_,./) in Res LandMark.");
		document.profileForm.resLandmark.focus();
		return (false);
	}

	if(document.profileForm.resLandmark.value==""){
	document.profileForm.resLandmark.value="";

	}
	if(document.profileForm.resPincode.value==""){
		alert("Please enter residential pincode.");
		document.profileForm.resPincode.focus();
		return false;

		}


		var resMobileNo=document.profileForm.resMobileNo.value.replace(/^\s+|\s+$/, '');

		if(resMobileNo=="")
		{
		alert("Please enter residential mobile number.");
		document.profileForm.resMobileNo.focus();
		return false;

		}
		if(resMobileNo.length<10)
		{
		alert("Invalid residential mobile number.");
		document.profileForm.resMobileNo.focus();
		return false;

		}


		var resAlternateNo=document.profileForm.resAlternateNo.value.replace(/^\s+|\s+$/, '');

		if(resAlternateNo=="")
		{
		alert("Please enter residential alternate mobile number.");
		document.profileForm.resAlternateNo.focus();
		return false;

		}
		if(resAlternateNo.length<10)
		{
		alert("Invalid residential alternate mobile number.");
		document.profileForm.resAlternateNo.focus();
		return false;

		}
		if(document.profileForm.emailId.value=="")
		{
		alert("Please enter official email id.");
		document.profileForm.emailId.focus();
		return false;

		}
		if(document.profileForm.alternateEmailId.value==""){
		document.profileForm.alternateEmailId.value="";
		}


		var authorizedMobileNo=document.profileForm.authorizedMobileNo.value.replace(/^\s+|\s+$/, '');

		if(authorizedMobileNo=="")
		{
		alert("Please enter authorized mobile number.");
		document.profileForm.authorizedMobileNo.focus();
		return false;

		}
		if(authorizedMobileNo.length<10){
		alert("Invalid authorized mobile number.");
		document.profileForm.authorizedMobileNo.focus();
		return false;

		}
	var authorizedMobileNo=document.profileForm.authorizedMobileNo.value;
	if(authorizedMobileNo=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter authorized Mobile No </p></html>';	
		document.profileForm.authorizedMobileNo.focus();
		return false;
	}
	return true;
}

var testresults
function checkemail(){

var str=document.profileForm.emailId.value;
var filter=/^.+@.+\..{2,3}$/

if (filter.test(str))
testresults=true;
else {
alert("Please input a valid email address!");
document.profileForm.emailId.focus();
testresults=false;
}
return testresults;

}

function checkemail1(){

var str=document.profileForm.alternateEmailId.value;
var filter=/^.+@.+\..{2,3}$/

if (filter.test(str))
testresults=true;
else {
alert("Please input a valid email address!");
document.profileForm.alternateEmailId.focus();
testresults=false;
}
return testresults;

}

function chkAPIForm()
{
	
	var fname=document.profileForm.firstName.value.replace(/^\s+|\s+$/, '');
	if(fname=="")
	{
	alert("Please enter first name");
	document.profileForm.firstName.focus();
	return false;
	}
	var lname=document.profileForm.lastName.value.replace(/^\s+|\s+$/, '');
	if(lname=="")
	{
	alert("Please enter last name");
	document.profileForm.lastName.focus();
	return false;
	}
	
	var dob=document.profileForm.dob.value;
	if(dob=="")
	{
		document.getElementById("errField").innerHTML='<p>Enter dob </p></html>';	
		document.profileForm.dob.focus();
		return false;
	}
	
	var father=document.profileForm.fatherName.value.replace(/^\s+|\s+$/, '');
	if(father=="")
	{
	alert("Please enter father/Husband name.");
	document.profileForm.fatherName.focus();
	return false;
	}
	var mname=document.profileForm.motherName.value.replace(/^\s+|\s+$/, '');
	if(mname=="")
	{
	alert("Please enter Mother's name.");
	document.profileForm.motherName.focus();
	return false;
	}
	var compname=document.profileForm.companyName.value.replace(/^\s+|\s+$/, '');
	if(compname=="")
	{
	alert("Please enter company name.");
	document.profileForm.companyName.focus();
	return false;

	}
	
	if(document.profileForm.designation.value=="select"){
		alert("Please select designation.");
		document.profileForm.designation.focus();
		return false;

		}
		if(document.profileForm.designation.value=="other"){
			var design=document.profileForm.otherDesignation.value.replace(/^\s+|\s+$/, '');
		if(design==""){
		alert("Please enter designation.");
		document.profileForm.otherDesignation.focus();
		return false;
		}
		}
		var add1=document.profileForm.addressLine1.value.replace(/^\s+|\s+$/, '');
		if(add1=="")
		{
		alert("Please enter address.");
		document.profileForm.addressLine1.focus();
		return false;
		}
		
			
		
		var country=document.profileForm.country.value;
		if(country=="")
		{
			document.getElementById("errField").innerHTML='<p>Select Office Country </p></html>';	
			document.profileForm.country.focus();
			return false;
		}
		
		var state=document.profileForm.state.value;
		if(state=="")
		{
			document.getElementById("errField").innerHTML='<p>Select Office State </p></html>';	
			document.profileForm.state.focus();
			return false;
		}
		var district=document.profileForm.district.value;
		if(district=="")
		{
			document.getElementById("errField").innerHTML='<p>Select Office district </p></html>';	
			document.profileForm.district.focus();
			return false;
		}
		var pincode=document.profileForm.pincode.value;
		if(pincode=="")
		{
			document.getElementById("errField").innerHTML='<p>Enter office pinCode</p></html>';	
			document.profileForm.pincode.focus();
			return false;
		}
		var mobileNo=document.profileForm.mobileNo.value.replace(/^\s+|\s+$/, '');

		if(mobileNo==""){
		alert("Please enter  mobile number.");
		document.profileForm.mobileNo.focus();
		return false;
		}
		
		if(mobileNo.length<10){
			alert("Invalid mobile number.");
			document.profileForm.mobileNo.focus();
			return false;

			}
		
		
		var resadd=document.profileForm.residentialAddressLine1.value.replace(/^\s+|\s+$/, '');
		if(resadd=="")
		{
		alert("Please enter residential address.");
		document.profileForm.residentialAddressLine1.focus();
		return false;

		}
		var resCountry=document.profileForm.resCountry.value;
		if(resCountry=="")
		{
			document.getElementById("errField").innerHTML='<p>select Residential country</p></html>';	
			document.profileForm.resCountry.focus();
			return false;
		}
		var resState=document.profileForm.resState.value;
		if(resState=="")
		{
			document.getElementById("errField").innerHTML='<p>Select Residential State</p></html>';	
			document.profileForm.resState.focus();
			return false;
		}
		var resDistrict=document.profileForm.resDistrict.value;
		if(resDistrict=="")
		{
			document.getElementById("errField").innerHTML='<p>Select Residential District</p></html>';	
			document.profileForm.resDistrict.focus();
			return false;
		}
		if(document.profileForm.resPincode.value==""){
			alert("Please enter residential pincode.");
			document.profileForm.resPincode.focus();
			return false;

			}


			var resMobileNo=document.profileForm.resMobileNo.value.replace(/^\s+|\s+$/, '');

			if(resMobileNo=="")
			{
			alert("Please enter residential mobile number.");
			document.profileForm.resMobileNo.focus();
			return false;

			}
			if(resMobileNo.length<10)
			{
			alert("Invalid residential mobile number.");
			document.profileForm.resMobileNo.focus();
			return false;
			}

			if(document.profileForm.emailId.value=="")
			{
			alert("Please enter official email id.");
			document.profileForm.emailId.focus();
			return false;

			}
			var authorizedMobileNo=document.profileForm.authorizedMobileNo.value.replace(/^\s+|\s+$/, '');

			if(authorizedMobileNo=="")
			{
			alert("Please enter authorized mobile number.");
			document.profileForm.authorizedMobileNo.focus();
			return false;

			}
			if(authorizedMobileNo.length<10){
			alert("Invalid authorized mobile number.");
			document.profileForm.authorizedMobileNo.focus();
			return false;

			}
		
		return true;
	}
