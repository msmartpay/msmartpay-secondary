function validateform()
{


var main=document.regiestration;

if(main.AuthurizedType.value=="select")
{
	alert("please select Authorised Person Name block");
	main.AuthurizedType.focus();
	return false;
}

var name=main.firstname.value;
name=name.replace(/^\s+|\s+$/, '');
if(name=="")
{
alert("Please enter your first name");
main.firstname.focus();
return false;
}



if (name != "") {
    if ( /[^A-Za-z\d]/.test(name)) {
        alert("Please enter only letter and numeric characters in First Name ");
        main.firstname.focus();
        return (false);
    }
}





var Lastname=main.lastname.value;
Lastname=Lastname.replace(/^\s+|\s+$/, '');
if(Lastname=="")
{
alert("Please enter your last name");
main.lastname.focus();
return false;
}

if (Lastname != "") {
    if ( /[^A-Za-z\d]/.test(Lastname)) {
        alert("Please enter only letter and numeric characters in Lastname");
        main.lastname.focus();
        return (false);
    }
}



if(main.date.value=="date")
{
alert("Please Date filed in DateOfBirth");
main.date.focus();
return false;
}

if(main.month.value=="month")
{
alert("Please month filed in DateOfBirth");
main.month.focus();
return false;
}

if(main.year.value=="Year")
{
alert("Please year filed in DateOfBirth");
main.year.focus();
return false;
}

var gender=main.Gender.value;
if(gender=="select")
{
alert("Please  select your gender");
main.Gender.focus();
return false;
}

var marital=main.maritalStatus.value;
if(marital=="select")
{
alert("Please  select your marital status");
main.maritalStatus.focus();
return false;
}



var fathername=main.fathername.value;
fathername=fathername.replace(/^\s+|\s+$/, '');
if(fathername=="" || fathername=="-")
{
alert("Please  enter your father name");
main.fathername.focus();
return false;
}






var mothername=main.mothername.value;
mothername=mothername.replace(/^\s+|\s+$/, '');
if(mothername=="")
{
alert("Please  enter your mother name");
main.mothername.focus();
return false;
}




var occupation=main.occupation.value;
if(occupation=="select")
{
alert("Please  select your occupation ");
main.occupation.focus();
return false;
}
var firmtype=main.CompanyType.value;
if(firmtype=="select"  || firmtype=="")
{
alert("Please  select your Company/ Firm Type ");
main.CompanyType.focus();
return false;
}
var agencyName=main.agencyName.value;
agencyName=agencyName.replace(/^\s+|\s+$/, '');
if(agencyName=="")
{
alert("Please  enter your Company/ Firm Name/ Shop Name *");
main.agencyName.focus();
return false;
}

if (agencyName != "") {
    if ( /[^A-Za-z\d]/.test(agencyName)) {
        alert("Please enter only letter and numeric characters in company name");
        main.agencyName.focus();
        return (false);
    }
}



var Designation=main.Designation.value;
if(Designation=="select")
{
alert("Please  select your Designation ");
main.Designation.focus();
return false;
}



var officeAddress1=main.Address1.value;
var state=main.state.value;
var officeDistrict=main.District.value;
var city=main.city.value;


var officePinCode=main.officePinCode.value;
var officeMobileNo=main.officeMobileNo.value;


officeAddress1=officeAddress1. replace(/^\s+|\s+$/, '');
if(officeAddress1==""){
alert("please enter office address");
document.regiestration.Address1.focus();
return false;
}




if(state=="select"){
alert("please select your state ");
document.regiestration.state.focus();
return false;
}


if(officeDistrict=="select"){
alert("please select your District ");
document.regiestration.officeDistrict.focus();
return false;
}
if(city=="select"){
alert("please select your state ");
document.regiestration.city.focus();
return false;
}


officePinCode=officePinCode. replace(/^\s+|\s+$/, '');
if(officePinCode==""){
alert("please enter  PinCode no");
document.regiestration.officePinCode.focus();
return false;
}
if(officePinCode.length<6){
alert("PinCode must be of 6 digit");
document.regiestration.officePinCode.focus();
return false;
}

if (officePinCode != "") {
    if ( /[^A-Za-z\d]/.test(officePinCode)) {
        alert("Please enter only letter and numeric characters");
        document.regiestration.officePinCode.focus();
        return (false);
    }
}




officeMobileNo=officeMobileNo. replace(/^\s+|\s+$/, '');
if(officeMobileNo==""){
alert("please enter mobile no");
document.regiestration.officeMobileNo.focus();
return false;
}

if (officeMobileNo!= "") {
    if ( /[^A-Za-z\d]/.test(officeMobileNo)) {
        alert("Please enter only letter and numeric characters");
        document.regiestration.officeMobileNo.focus();
        return (false);
    }
}


var officeAlternateNo=main.officeAlternateNo.value;
officeAlternateNo=officeAlternateNo. replace(/^\s+|\s+$/, '');
if(officeAlternateNo==""){
alert("please enter office Alternate mobile no");
document.regiestration.officeAlternateNo.focus();
return false;
}

if (officeAlternateNo!= "") {
    if ( /[^A-Za-z\d]/.test(officeAlternateNo)) {
        alert("Please enter only letter and numeric characters");
        document.regiestration.officeMobileNo.focus();
        return (false);
    }
}


var resAdd1=document.regiestration.ResAddress1.value;
var resState=document.regiestration.resState.value;
var resDistrict=document.regiestration.resDistrict.value;
var resCity=document.regiestration.resCity.value;

var MobileNo=document.regiestration.Rmobile.value;

var altMobileNo=document.regiestration.Ralternatemobile.value;

var Rphone=document.regiestration.Rphone.value;

var Rfax=document.regiestration.Rfax.value;

resAdd1=resAdd1.replace(/^\s+|\s+$/, '');;
if(resAdd1=="")
{
alert("Please enter residential Address");
document.regiestration.ResAddress1.focus();
return false;
}



if(resState=="select"){
alert("please select your state ");
document.regiestration.resState.focus();
return false;
}


if(resDistrict=="select"){
alert("please select your District ");
document.regiestration.resDistrict.focus();
return false;
}
if(resCity=="select"){
alert("please select your state ");
document.regiestration.resCity.focus();
return false;
}

MobileNo=MobileNo.replace(/^\s+|\s+$/, '');
if(MobileNo=="")
{
alert("Please enter Residence Mobile No");
document.regiestration.Rmobile.focus();
return false;
}
if (MobileNo!= "") {
    if ( /[^A-Za-z\d]/.test(MobileNo)) {
        alert("Please enter only letter and numeric characters");
        document.regiestration.Rmobile.focus();
        return (false);
    }
}


altMobileNo=altMobileNo.replace(/^\s+|\s+$/, '');
if(altMobileNo=="")
{
alert("Please enter Residence Alternate Mobile No");
document.regiestration.Ralternatemobile.focus();
return false;
}

if (altMobileNo!= "") {
    if ( /[^A-Za-z\d]/.test(altMobileNo)) {
        alert("Please enter only letter and numeric characters");
        document.regiestration.Ralternatemobile.focus();
        return (false);
    }
}

if( Rphone==""){

document.regiestration.Rphone.value="000000";
}
if( Rfax==""){

document.regiestration.Rfax.value="000000";
}




document.regiestration.action="AgentRegistration.action?param=AgentregistrationfirstForm";
document.regiestration.submit();
return true;



}








function digitonly(input,evt)

{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) 
{
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 ) ) 
{
return true;
}

return false;
}

function alpha(e) {
    var k;
    document.all ? k = e.keyCode : k = e.which;
    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || k == 32 || (k >= 48 && k <= 57));
	
}

function checkoccupation()
{


var occupation=document.regiestration.occupation.value;




if(occupation=="others")
{
document.getElementById("otherOccupation").style.display="";

}
else
{
document.getElementById("otherOccupation").style.display="none";
}
}
function changecompanytype()
{


var companytype=document.regiestration.CompanyType.value;



if(companytype=="Other")
{
document.getElementById("otherCompanyType").style.display="";

}
else
{
document.getElementById("otherCompanyType").style.display="none";
}
}

function changeotherDesignation()
{


var Designation=document.regiestration.Designation.value;




if(Designation=="Other")
{
document.getElementById("otherdesignation").style.display="";

}
else
{
document.getElementById("otherdesignation").style.display="none";
}
}





function validateform2()
{
	
document.regiestration.action="AgentRegistration.action?param=AgentregistrationfirstForm";
document.regiestration.submit();
return true;	
	
}

