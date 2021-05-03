function openPage()
{
var EnterUserId=document.rechargeForm.EnterUserId.value;
var mySelectbox=document.rechargeForm.mySelectbox.value;
if(EnterUserId==""){
document.getElementById("user").innerHTML ="Please insert userId";
return false;
}

document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetRechargeMarginDetails.action?param="+mySelectbox+"";
document.rechargeForm.submit();
}


function check(){
	var allcheck = document.update.checkAll;
	
	if(allcheck.checked == true){
	for(i=0; i<document.update.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name!="checkAll")
			{
				document.update.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.update.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name != "checkAll" )
			{
				document.update.elements[i].checked=false;
			}
			
		}
	}
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


//----------------------------------------Validation for Pay Bill Commission--------------------


function doUpdatePayBill(form)
{
	
var c=document.update.checkpartial.value;
var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked){
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) {
	alert("Please Select Atleast One Transaction");
//document.getElementById('bold1').innerHTML =i;
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
var WLCharge;
var Operator;


if(IdNo=="0"){
Operator=document.getElementById ( "Aircel" ).textContent;
WLComm=document.getElementById("AircelComm").value;
WLCharge=document.getElementById("AircelCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm=="")
{
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
}
if(IdNo=="1"){
Operator=document.getElementById ( "Airtel" ).textContent;
WLComm=document.getElementById("AirtelComm").value;
WLCharge=document.getElementById("AirtelCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm=="")
{
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
}

//------------------------------2---------------
if(IdNo=="2"){
Operator=document.getElementById ( "AIRTELL" ).textContent;
WLComm=document.getElementById("AIRTELLComm").value;
WLCharge=document.getElementById("AIRTELLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------3------------
if(IdNo=="3"){
Operator=document.getElementById ( "BSNL" ).textContent;
WLComm=document.getElementById("BSNLComm").value;
WLCharge=document.getElementById("BSNLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number  for"+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------4------------
if(IdNo=="4"){
Operator=document.getElementById ( "IDEA" ).textContent;
WLComm=document.getElementById("IDEAComm").value;
WLCharge=document.getElementById("IDEACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------4------------
if(IdNo=="5"){
Operator=document.getElementById ( "Loop" ).textContent;
WLComm=document.getElementById("LoopComm").value;
WLCharge=document.getElementById("LoopCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------4------------
if(IdNo=="6"){
Operator=document.getElementById ( "MTNLGaruda" ).textContent;
WLComm=document.getElementById("MTNLGarudaComm").value;
WLCharge=document.getElementById("MTNLGarudaCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------5------------
if(IdNo=="7"){
Operator=document.getElementById ( "Reliance" ).textContent;
WLComm=document.getElementById("RelianceComm").value;
WLCharge=document.getElementById("RelianceCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------6------------
if(IdNo=="8"){
Operator=document.getElementById ( "RelianceL" ).textContent;
WLComm=document.getElementById("RelianceLComm").value;
WLCharge=document.getElementById("RelianceLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}


if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------6------------
if(IdNo=="9"){
Operator=document.getElementById ( "Spice" ).textContent;
WLComm=document.getElementById("SpiceComm").value;
WLCharge=document.getElementById("SpiceCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}


if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------7------------
if(IdNo=="10"){
Operator=document.getElementById ( "TATA" ).textContent;
WLComm=document.getElementById("TATAComm").value;
WLCharge=document.getElementById("TATACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false; } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------8------------
if(IdNo=="11"){
Operator=document.getElementById ( "TATAL" ).textContent;
WLComm=document.getElementById("TATALComm").value;
WLCharge=document.getElementById("TATALCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number.");
return false;
}
}
//--------------------------------9------------
if(IdNo=="12"){
Operator=document.getElementById ( "TATADOCOMO" ).textContent;
WLComm=document.getElementById("TATADOCOMOComm").value;
WLCharge=document.getElementById("TATADOCOMOCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number.");
return false;
}
}
//--------------------------------10------------
if(IdNo=="13"){
Operator=document.getElementById ( "VODAFONE" ).textContent;
WLComm=document.getElementById("VODAFONEComm").value;
WLCharge=document.getElementById("VODAFONECharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}

}}}

document.getElementById("check2").style.visibility="visible";

document.update.action="UpdateWLPayBillComm.action";
document.update.submit();
}	
}


//----------------------------------------Validation for Pay Premium Commission--------------------


function doUpdatePayPremium(form)
{
	alert("doUpdatePayPremium")
var c=document.update.checkpartial.value;
var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked){
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) {
	alert("Please Select Atleast One Transaction");
//document.getElementById('bold1').innerHTML =i;
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
var WLCharge;
var Operator;

alert("IdNo---"+IdNo)
if(IdNo=="0"){
Operator=document.getElementById ( "AVIVA" ).textContent;
WLComm=document.getElementById("AVIVAComm").value;
WLCharge=document.getElementById("AVIVACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm=="")
{
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
}
//------------
if(IdNo=="1"){
	
Operator=document.getElementById ( "BhartiAxa" ).textContent;
alert("Operator---"+Operator)
WLComm=document.getElementById("BhartiAxaComm").value;
WLCharge=document.getElementById("BhartiAxaCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm=="")
{
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
}
//------------------------------2---------------
if(IdNo=="2"){
Operator=document.getElementById ( "BSunLife" ).textContent;
WLComm=document.getElementById("BSunLifeComm").value;
WLCharge=document.getElementById("BSunLifeCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------3------------
if(IdNo=="3"){
Operator=document.getElementById ( "DLF" ).textContent;
WLComm=document.getElementById("DLFComm").value;
WLCharge=document.getElementById("DLFCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number  for"+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------4------------
if(IdNo=="4"){
Operator=document.getElementById ( "HDFC" ).textContent;
WLComm=document.getElementById("HDFCComm").value;
WLCharge=document.getElementById("HDFCCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}


if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------5------------
if(IdNo=="5"){
Operator=document.getElementById ( "ICICI" ).textContent;
WLComm=document.getElementById("ICICIComm").value;
WLCharge=document.getElementById("ICICICharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------6------------
if(IdNo=="6"){
Operator=document.getElementById ( "Lic" ).textContent;
WLComm=document.getElementById("LicComm").value;
WLCharge=document.getElementById("LicCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}


if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}

//--------------------------------7------------
if(IdNo=="7"){
Operator=document.getElementById ( "MaxNewYork" ).textContent;
WLComm=document.getElementById("MaxNewYorkComm").value;
WLCharge=document.getElementById("MaxNewYorkCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false; } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------8------------
if(IdNo=="8"){
Operator=document.getElementById ( "METLIFE" ).textContent;
WLComm=document.getElementById("METLIFEComm").value;
WLCharge=document.getElementById("METLIFECharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number.");
return false;
}
}
//--------------------------------9------------
if(IdNo=="9"){
Operator=document.getElementById ( "TATAAIG" ).textContent;
WLComm=document.getElementById("TATAAIGComm").value;
WLCharge=document.getElementById("TATAAIGCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number.");
return false;
}
}
//--------------------------------10------------
if(IdNo=="10"){
Operator=document.getElementById ( "VYSHYA" ).textContent;
WLComm=document.getElementById("VYSHYAComm").value;
WLCharge=document.getElementById("VYSHYACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
//--------------------comm-----------------
WLComm=WLComm.replace(/^\s+|\s+$/, '');
if(WLComm==""){
alert("Please enter  Commission for "+Operator);
return false;
}
if( parseFloat(WLComm)!= parseInt(parseFloat(WLComm))){
    alert ("Decimal value not allowed for Commission of "+Operator);
	 return false;
    }
if (isNaN(WLComm)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLComm)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}

}}}

document.getElementById("check2").style.visibility="visible";
document.update.action="UpdateWLPayPremiumComm.action";
document.update.submit();
}	
}


//----------------------------------------Validation for Rail Commission--------------------


function doUpdateRail(form)
{
var c=document.update.checkpartial.value;
var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked){
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) {
	alert("Please Select Atleast One Transaction");
//document.getElementById('bold1').innerHTML =i;
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

var IdNo = split[1];

var WLCharge;
var Operator;


if(IdNo=="0"){
Operator=document.getElementById ( "CC" ).textContent;

WLCharge=document.getElementById("CCCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}



if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}

//------------------------------2---------------
if(IdNo=="1"){
Operator=document.getElementById ( "SL" ).textContent;

WLCharge=document.getElementById("SLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------3------------
if(IdNo=="2"){
Operator=document.getElementById ( "CL_2S" ).textContent;

WLCharge=document.getElementById("CL_2SCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number  for"+Operator);
return false;
}

}
//--------------------------------4------------
if(IdNo=="3"){
Operator=document.getElementById ( "CL_3A" ).textContent;

WLCharge=document.getElementById("CL_3ACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}



if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------5------------
if(IdNo=="4"){
Operator=document.getElementById ( "CL_2A" ).textContent;

WLCharge=document.getElementById("CL_2ACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}

}
//--------------------------------6------------
if(IdNo=="5"){
Operator=document.getElementById ( "CL_1A" ).textContent;
WLCharge=document.getElementById("CL_1ACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}



}}}
document.getElementById("check2").style.visibility="visible";
document.update.action="UpdateWLRailComm.action";
document.update.submit();
}	
}




//----------------------------------------Validation for Recharge Commission--------------------


function doUpdateRecharge(form)
{
var c=document.update.checkpartial.value;
var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked){
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) {
	alert("Please Select Atleast One Transaction");
//document.getElementById('bold1').innerHTML =i;
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

var IdNo = split[1];

var WLCharge;
var Operator;


if(IdNo=="0"){
Operator=document.getElementById ( "AIRCEL" ).textContent;

WLCharge=document.getElementById("AIRCELCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Charge for "+Operator);
return false;
}



if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}

//------------------------------2---------------
if(IdNo=="1"){
Operator=document.getElementById ( "AIRTEL" ).textContent;

WLCharge=document.getElementById("AIRTELCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------3------------
if(IdNo=="2"){
Operator=document.getElementById ( "BSNLRECH" ).textContent;

WLCharge=document.getElementById("BSNLRECHCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number  for"+Operator);
return false;
}

}
//--------------------------------4------------
if(IdNo=="3"){
Operator=document.getElementById ( "BSNL" ).textContent;

WLCharge=document.getElementById("BSNLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}



if (isNaN(WLCharge)){ 

alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------5------------
if(IdNo=="4"){
Operator=document.getElementById ( "IDEA" ).textContent;

WLCharge=document.getElementById("IDEACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}

}
//--------------------------------6------------
if(IdNo=="5"){
Operator=document.getElementById ( "BPL" ).textContent;
WLCharge=document.getElementById("BPLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}

//--------------------------------7------------
if(IdNo=="6"){
Operator=document.getElementById ( "MTS" ).textContent;
WLCharge=document.getElementById("MTSCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------7------------
if(IdNo=="7"){
Operator=document.getElementById ( "MTNLT" ).textContent;
WLCharge=document.getElementById("MTNLTCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------7------------
if(IdNo=="8"){
Operator=document.getElementById ( "MTNLV" ).textContent;
WLCharge=document.getElementById("MTNLVCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="9"){
Operator=document.getElementById ( "RELIANCE" ).textContent;
WLCharge=document.getElementById("RELIANCECharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="10"){
Operator=document.getElementById ( "RGSM" ).textContent;
WLCharge=document.getElementById("RGSMCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="11"){
Operator=document.getElementById ( "STEL" ).textContent;
WLCharge=document.getElementById("STELCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="12"){
Operator=document.getElementById ( "TATADOCOMO" ).textContent;
WLCharge=document.getElementById("TATADOCOMOCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="13"){
Operator=document.getElementById ( "TATADOCOMOSP" ).textContent;
WLCharge=document.getElementById("TATADOCOMOSPCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="14"){
Operator=document.getElementById ( "TATA" ).textContent;
WLCharge=document.getElementById("TATACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="15"){
Operator=document.getElementById ( "UNINORS" ).textContent;
WLCharge=document.getElementById("UNINORSCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="16"){
Operator=document.getElementById ( "UNINOR" ).textContent;
WLCharge=document.getElementById("UNINORCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="17"){
Operator=document.getElementById ( "VODAFONE" ).textContent;
WLCharge=document.getElementById("VODAFONECharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="18"){
Operator=document.getElementById ( "VIDEOCONSP" ).textContent;
WLCharge=document.getElementById("VIDEOCONSPCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="19"){
Operator=document.getElementById ( "VIDEOCONT" ).textContent;
WLCharge=document.getElementById("VIDEOCONTCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="20"){
Operator=document.getElementById ( "VIRGIN" ).textContent;
WLCharge=document.getElementById("VIRGINCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="21"){
Operator=document.getElementById ( "VIRGIN1" ).textContent;
WLCharge=document.getElementById("VIRGIN1Charge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="22"){
Operator=document.getElementById ( "AirtelDTH" ).textContent;
WLCharge=document.getElementById("AirtelDTHCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="23"){
Operator=document.getElementById ( "DTH_BIGTV" ).textContent;
WLCharge=document.getElementById("DTH_BIGTVCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="24"){
Operator=document.getElementById ( "DTH_DISHTV" ).textContent;
WLCharge=document.getElementById("DTH_DISHTVCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="25"){
Operator=document.getElementById ( "DTH_SUNTV" ).textContent;
WLCharge=document.getElementById("DTH_SUNTVCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="26"){
Operator=document.getElementById ( "DTH_TATASKY" ).textContent;
WLCharge=document.getElementById("DTH_TATASKYCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="27"){
Operator=document.getElementById ( "DTH_VIDEOCON" ).textContent;
WLCharge=document.getElementById("DTH_VIDEOCONCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="28"){
Operator=document.getElementById ( "DATACARD_AIRCEL" ).textContent;
WLCharge=document.getElementById("DATACARD_AIRCELCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="29"){
Operator=document.getElementById ( "DATACARD_BSNL" ).textContent;
WLCharge=document.getElementById("DATACARD_BSNLCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="30"){
Operator=document.getElementById ( "DATACARD_MTS" ).textContent;
WLCharge=document.getElementById("DATACARD_MTSCharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="31"){
Operator=document.getElementById ( "DATACARD_RELIENCE" ).textContent;
WLCharge=document.getElementById("DATACARD_RELIENCECharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}
//--------------------------------6------------
if(IdNo=="32"){
Operator=document.getElementById ( "DATACARD_TATA" ).textContent;
WLCharge=document.getElementById("DATACARD_TATACharge").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Charge for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}

}}}
document.getElementById("check2").style.visibility="visible";

document.update.action="UpdateWLRechargeMargin.action";
document.update.submit();
}	
}

//--------------------------------------Other commission-------------------------------------

//----------------------------------------Validation for Rail Commission--------------------


function doUpdateOther(form)
{
	alert("in Update other form")
var c=document.update.checkpartial.value;
var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked){
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) {
	alert("Please Select Atleast One Transaction");
//document.getElementById('bold1').innerHTML =i;
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

var IdNo = split[1];

var WLCharge;
var Operator;


if(IdNo=="0"){
Operator=document.getElementById ( "bus" ).textContent;

WLCharge=document.getElementById("BusComm").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge=="")
{
alert("Please enter  Commission for "+Operator);
return false;
}



if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number. for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;

}

}

//------------------------------2---------------
if(IdNo=="1"){
Operator=document.getElementById ( "EDU" ).textContent;

WLCharge=document.getElementById("EduComm").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number for "+Operator);
return false;
}
}
//--------------------------------3------------
if(IdNo=="2"){
Operator=document.getElementById ( "Pan" ).textContent;

WLCharge=document.getElementById("PancardComm").value;
WLCharge=WLCharge.replace(/^\s+|\s+$/, '');
if(WLCharge==""){
alert("Please enter  Commission for "+Operator);
return false;
}

if (isNaN(WLCharge)){ 
alert( "- Please enter your quantity as a number for "+Operator);
return false;
 } 	
if(Number(WLCharge)<0){
alert( "- Please enter your quantity as a positive number  for"+Operator);
return false;
}

}
//--------------------------------4------------





}}}
document.getElementById("check2").style.visibility="visible";
document.update.action="UpdateWLOtherComm.action";
document.update.submit();
}	
}


