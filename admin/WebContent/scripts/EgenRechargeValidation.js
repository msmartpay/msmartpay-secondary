function validate(form) {

var c=document.update.checkpartial.value;

var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked)
 {
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}}
if (boxesTicked1 <1 ) 
{
alert("Please Select Atleast One Agent");
return boxesTicked1;
}
else
{

var namelist = "";
with(document.update) {
for(var i = 0; i < checkpartial.length; i++){
if(checkpartial[i].checked) {
namelist = checkpartial[i].value;

var split=namelist.split("&");

var IdNo = split[2];
var WLComm;
var RTComm;
var MDComm;
var DSComm;
var WLCharge;
var RTCharge;
var MDCharge;
var DSCharge;

else
{

document.update.action="UpdateAgentPayBillWithClientId.action";
document.update.submit();


	

}
}


}
}
}
}