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

var IdNo = split[4];
var WLComm;
var RTComm;
var DSComm;
var AgentComm;
var WLCharge;
var RTCharge;
var DSCharge;
var AgentCharge;


if(IdNo=="0"){
	
RTComm=document.getElementById("RM_Aircel_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for Aircel ");
document.getElementById("RM_Aircel_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for Aircel commission ");
document.getElementById("RM_Aircel_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Aircel commission   ");
document.getElementById("RM_Aircel_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_Aircel_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for Aircel Charge  ");
document.getElementById("RM_Aircel_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for Aircel Charge");
document.getElementById("RM_Aircel_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for Aircel Charge");
document.getElementById("RM_Aircel_Charge").focus();
return false;
}
	
WLComm=Number(document.getElementById ( "CM_AircelComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_AircelCharge" ).textContent);
  RTComm=Number(RTComm);
RTCharge=Number(RTCharge);
 var DSComm=document.getElementById("DS_AircelComm").value;
 var DSCharge=document.getElementById("DS_AircelCharge").value;
  var AgentComm=document.getElementById("Agent_AircelComm").value;
var AgentCharge=document.getElementById("Agent_AircelCharge").value;
var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
 var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);
 

if(WLComm<totalComm){
alert("Commision for Aircel  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_Aircel_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Aircel  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_Aircel_Charge").focus();
return false;
}
}





if(IdNo=="1"){
	
RTComm=document.getElementById("RM_AIRTEL_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for AIRTEL ");
document.getElementById("RM_AIRTEL_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for AIRTEL commission ");
document.getElementById("RM_AIRTEL_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for AIRTEL commission   ");
document.getElementById("RM_AIRTEL_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_AIRTEL_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for AIRTEL Charge  ");
document.getElementById("RM_AIRTEL_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for AIRTEL Charge");
document.getElementById("RM_AIRTEL_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for AIRTEL Charge");
document.getElementById("RM_AIRTEL_Charge").focus();
return false;
}	
	
 WLComm=Number(document.getElementById ( "CM_AIRTELComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_AIRTELCharge" ).textContent);
  RTComm=Number(document.getElementById("RM_AIRTEL_Comm").value);
RTCharge=Number(document.getElementById("RM_AIRTEL_Charge").value);
 var DSComm=document.getElementById("DS_AIRTELComm").value;
 var DSCharge=document.getElementById("DS_AIRTELCharge").value;
  var AgentComm=document.getElementById("Agent_AIRTELComm").value;
var AgentCharge=document.getElementById("Agent_AIRTELCharge").value;
var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
 var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);
 

if(WLComm<totalComm){
alert("Commision for Airtel  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_AIRTEL_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Airtel  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_AIRTEL_Charge").focus();
return false;
}
}


if(IdNo=="2"){
		
RTComm=document.getElementById("RM_AIRTELL_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for AIRTELL ");
document.getElementById("RM_AIRTELL_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for AIRTELL commission ");
document.getElementById("RM_AIRTELL_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for AIRTELL commission   ");
document.getElementById("RM_AIRTELL_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_AIRTELL_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for AIRTELL Charge  ");
document.getElementById("RM_AIRTELL_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for AIRTELL Charge");
document.getElementById("RM_AIRTELL_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for AIRTELL Charge");
document.getElementById("RM_AIRTELL_Charge").focus();
return false;
}
 WLComm=Number(document.getElementById ( "CM_AIRTELLComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_AIRTELLCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_AIRTELL_Comm").value);
 RTCharge=Number(document.getElementById("RM_AIRTELL_Charge").value);
 var DSComm=document.getElementById("DS_AIRTELLComm").value;
 var DSCharge=document.getElementById("DS_AIRTELLCharge").value;
 var AgentComm=document.getElementById("Agent_AIRTELLComm").value;
 var AgentCharge=document.getElementById("Agent_AIRTELLCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for Airtel LandLine Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_AIRTELL_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Airtel LandLine  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_AIRTELL_Charge").focus();
return false;
}
}

if(IdNo=="3"){
	
	
RTComm=document.getElementById("RM_BSNL_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for BSNL ");
document.getElementById("RM_BSNL_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for BSNL commission ");
document.getElementById("RM_BSNL_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for BSNL commission   ");
document.getElementById("RM_BSNL_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_BSNL_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for BSNL Charge  ");
document.getElementById("RM_BSNL_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for BSNL Charge");
document.getElementById("RM_BSNL_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for BSNL Charge");
document.getElementById("RM_BSNL_Charge").focus();
return false;
}	
WLComm=Number(document.getElementById ( "CM_BSNLComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_BSNLCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_BSNL_Comm").value);
 RTCharge=Number(document.getElementById("RM_BSNL_Charge").value);
 var DSComm=document.getElementById("DS_BSNLComm").value;
 var DSCharge=document.getElementById("DS_BSNLCharge").value;
 var AgentComm=document.getElementById("Agent_BSNLComm").value;
 var AgentCharge=document.getElementById("Agent_BSNLCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for BSNL  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_BSNL_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for BSNL  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_BSNL_Charge").focus();
return false;
}
}


if(IdNo=="4"){
	
RTComm=document.getElementById("RM_IDEA_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for IDEA ");
document.getElementById("RM_IDEA_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for IDEA commission ");
document.getElementById("RM_IDEA_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for IDEA commission   ");
document.getElementById("RM_IDEA_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_IDEA_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for IDEA Charge  ");
document.getElementById("RM_IDEA_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for IDEA Charge");
document.getElementById("RM_IDEA_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for IDEA Charge");
document.getElementById("RM_IDEA_Charge").focus();
return false;
}		
	
 WLComm=Number(document.getElementById ( "CM_IDEAComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_IDEACharge" ).textContent);
 RTComm=Number(document.getElementById("RM_IDEA_Comm").value);
 RTCharge=Number(document.getElementById("RM_IDEA_Charge").value);
 var DSComm=document.getElementById("DS_IDEAComm").value;
 var DSCharge=document.getElementById("DS_IDEACharge").value;
 var AgentComm=document.getElementById("Agent_IDEAComm").value;
 var AgentCharge=document.getElementById("Agent_IDEACharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for IDEA  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_IDEA_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for IDEA  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_IDEA_Charge").focus();
return false;
}
}


if(IdNo=="5"){
	
RTComm=document.getElementById("RM_Loop_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for Loop ");
document.getElementById("RM_Loop_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for Loop commission ");
document.getElementById("RM_Loop_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Loop commission   ");
document.getElementById("RM_Loop_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_Loop_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for Loop Charge  ");
document.getElementById("RM_Loop_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for Loop Charge");
document.getElementById("RM_Loop_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for Loop Charge");
document.getElementById("RM_Loop_Charge").focus();
return false;
}		
	
 WLComm=Number(document.getElementById ( "CM_LoopComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_LoopCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_Loop_Comm").value);
 RTCharge=Number(document.getElementById("RM_Loop_Charge").value);
 var DSComm=document.getElementById("DS_LoopComm").value;
 var DSCharge=document.getElementById("DS_LoopCharge").value;
 var AgentComm=document.getElementById("Agent_LoopComm").value;
 var AgentCharge=document.getElementById("Agent_LoopCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for Loop  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_Loop_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Loop  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_Loop_Charge").focus();
return false;
}
}

if(IdNo=="6"){
	
RTComm=document.getElementById("RM_MTNLGaruda_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for MTNLGaruda ");
document.getElementById("RM_MTNLGaruda_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for MTNLGaruda commission ");
document.getElementById("RM_MTNLGaruda_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for MTNLGaruda commission   ");
document.getElementById("RM_MTNLGaruda_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_MTNLGaruda_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for MTNLGaruda Charge  ");
document.getElementById("RM_MTNLGaruda_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for MTNLGaruda Charge");
document.getElementById("RM_MTNLGaruda_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for MTNLGaruda Charge");
document.getElementById("RM_MTNLGaruda_Charge").focus();
return false;
}		
	
 WLComm=Number(document.getElementById ( "CM_MTNLGarudaComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_MTNLGarudaCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_MTNLGaruda_Comm").value);
 RTCharge=Number(document.getElementById("RM_MTNLGaruda_Charge").value);
 var DSComm=document.getElementById("DS_MTNLGarudaComm").value;
 var DSCharge=document.getElementById("DS_MTNLGarudaCharge").value;
 var AgentComm=document.getElementById("Agent_MTNLGarudaComm").value;
 var AgentCharge=document.getElementById("Agent_MTNLGarudaCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for MTNLGaruda  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_MTNLGaruda_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for MTNLGaruda  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_MTNLGaruda_Charge").focus();
return false;
}
}




if(IdNo=="7"){
		
RTComm=document.getElementById("RM_Reliance_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for Reliance ");
document.getElementById("RM_Reliance_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for Reliance commission ");
document.getElementById("RM_Reliance_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Reliance commission   ");
document.getElementById("RM_Reliance_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_Reliance_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for Reliance Charge  ");
document.getElementById("RM_Reliance_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for Reliance Charge");
document.getElementById("RM_Reliance_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for Reliance Charge");
document.getElementById("RM_Reliance_Charge").focus();
return false;
}
 WLComm=Number(document.getElementById ( "CM_RelianceComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_RelianceCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_Reliance_Comm").value);
 RTCharge=Number(document.getElementById("RM_Reliance_Charge").value);
 var DSComm=document.getElementById("DS_RelianceComm").value;
 var DSCharge=document.getElementById("DS_RelianceCharge").value;
 var AgentComm=document.getElementById("Agent_RelianceComm").value;
 var AgentCharge=document.getElementById("Agent_RelianceCharge").value;
var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for Reliance  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_Reliance_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Reliance  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_Reliance_Charge").focus();
return false;
}
}

if(IdNo=="8"){
	RTComm=document.getElementById("RM_RelianceL_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for RelianceL ");
document.getElementById("RM_RelianceL_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for RelianceL commission ");
document.getElementById("RM_RelianceL_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for RelianceL commission   ");
document.getElementById("RM_RelianceL_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_RelianceL_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for RelianceL Charge  ");
document.getElementById("RM_RelianceL_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for RelianceL Charge");
document.getElementById("RM_RelianceL_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for RelianceL Charge");
document.getElementById("RM_RelianceL_Charge").focus();
return false;
}
 WLComm=Number(document.getElementById ( "CM_RelianceLComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_RelianceLCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_RelianceL_Comm").value);
 RTCharge=Number(document.getElementById("RM_RelianceL_Charge").value);
 var DSComm=document.getElementById("DS_RelianceLComm").value;
 var DSCharge=document.getElementById("DS_RelianceLCharge").value;
 var AgentComm=document.getElementById("Agent_RelianceLComm").value;
 var AgentCharge=document.getElementById("Agent_RelianceLCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for Reliance LandLine  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_RelianceL_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Reliance LandLine  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_RelianceL_Charge").focus();
return false;
}
}
if(IdNo=="9"){
		RTComm=document.getElementById("RM_Spice_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for Spice ");
document.getElementById("RM_Spice_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for Spice commission ");
document.getElementById("RM_Spice_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Spice commission   ");
document.getElementById("RM_Spice_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_Spice_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for Spice Charge  ");
document.getElementById("RM_Spice_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for Spice Charge");
document.getElementById("RM_Spice_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for Spice Charge");
document.getElementById("RM_Spice_Charge").focus();
return false;
}
WLComm=Number(document.getElementById ( "CM_SpiceComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_SpiceCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_Spice_Comm").value);
 RTCharge=Number(document.getElementById("RM_Spice_Charge").value);
 var DSComm=document.getElementById("DS_SpiceComm").value;
 var DSCharge=document.getElementById("DS_SpiceCharge").value;
 var AgentComm=document.getElementById("Agent_SpiceComm").value;
 var AgentCharge=document.getElementById("Agent_SpiceCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for Spice  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_Spice_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for Spice  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_Spice_Charge").focus();
return false;
}
}
if(IdNo=="10"){
		RTComm=document.getElementById("RM_TATA_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for TATA ");
document.getElementById("RM_TATA_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for TATA commission ");
document.getElementById("RM_TATA_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for TATA commission   ");
document.getElementById("RM_TATA_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_TATA_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for TATA Charge  ");
document.getElementById("RM_TATA_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for TATA Charge");
document.getElementById("RM_TATA_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for TATA Charge");
document.getElementById("RM_TATA_Charge").focus();
return false;
}
WLComm=Number(document.getElementById ( "CM_TATAComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_TATACharge" ).textContent);
 RTComm=Number(document.getElementById("RM_TATA_Comm").value);
 RTCharge=Number(document.getElementById("RM_TATA_Charge").value);
 var DSComm=document.getElementById("DS_TATAComm").value;
 var DSCharge=document.getElementById("DS_TATACharge").value;
 var AgentComm=document.getElementById("Agent_TATAComm").value;
 var AgentCharge=document.getElementById("Agent_TATACharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for TATA  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_TATA_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for TATA  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_TATA_Charge").focus();
return false;
}
}

if(IdNo=="11"){
RTComm=document.getElementById("RM_TATAL_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for TATA Land Line ");
document.getElementById("RM_TATAL_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for TATA Land Line commission ");
document.getElementById("RM_TATAL_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for TATA Land commission   ");
document.getElementById("RM_TATAL_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_TATAL_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for TATA Land Charge  ");
document.getElementById("RM_TATAL_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for TATA Land Charge");
document.getElementById("RM_TATAL_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for TATA Land Charge");
document.getElementById("RM_TATAL_Charge").focus();
return false;
}
 WLComm=Number(document.getElementById ( "CM_TATALComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_TATALCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_TATAL_Comm").value);
 RTCharge=Number(document.getElementById("RM_TATAL_Charge").value);
 var DSComm=document.getElementById("DS_TATALComm").value;
 var DSCharge=document.getElementById("DS_TATALCharge").value;
 var AgentComm=document.getElementById("Agent_TATALComm").value;
 var AgentCharge=document.getElementById("Agent_TATALCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for TATA LandLine  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_TATAL_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for TATA LandLine  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_TATAL_Charge").focus();
return false;
}
}

if(IdNo=="12"){
	RTComm=document.getElementById("RM_TATADOCOMO_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for TATADOCOMO ");
document.getElementById("RM_TATADOCOMO_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for TATADOCOMO commission ");
document.getElementById("RM_TATADOCOMO_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for TATADOCOMO commission   ");
document.getElementById("RM_TATADOCOMO_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_TATADOCOMO_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for TATADOCOMO Charge  ");
document.getElementById("RM_TATADOCOMO_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for TATADOCOMO Charge");
document.getElementById("RM_TATADOCOMO_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for TATADOCOMO Charge");
document.getElementById("RM_TATADOCOMO_Charge").focus();
return false;
}
WLComm=Number(document.getElementById ( "CM_TATADOCOMOComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_TATADOCOMOCharge" ).textContent);
 RTComm=Number(document.getElementById("RM_TATADOCOMO_Comm").value);
 RTCharge=Number(document.getElementById("RM_TATADOCOMO_Charge").value);
 var DSComm=document.getElementById("DS_TATADOCOMOComm").value;
 var DSCharge=document.getElementById("DS_TATADOCOMOCharge").value;
 var AgentComm=document.getElementById("Agent_TATADOCOMOComm").value;
 var AgentCharge=document.getElementById("Agent_TATADOCOMOCharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for TATADOCOMO  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_TATADOCOMO_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for TATADOCOMO  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_TATADOCOMO_Charge").focus();
return false;
}
}

if(IdNo=="13"){
		RTComm=document.getElementById("RM_VODAFONE_Comm").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for VODAFONE ");
document.getElementById("RM_VODAFONE_Comm").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for VODAFONE commission ");
document.getElementById("RM_VODAFONE_Comm").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for VODAFONE commission   ");
document.getElementById("RM_VODAFONE_Comm").focus();
return false;
}	
	
RTCharge=document.getElementById("RM_VODAFONE_Charge").value;
RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
if(RTCharge==""){
alert("Please enter  Commission for VODAFONE Charge  ");
document.getElementById("RM_VODAFONE_Charge").focus();
return false;
}
if (isNaN(RTCharge)){ 
alert( "- Please enter your quantity as a number for VODAFONE Charge");
document.getElementById("RM_VODAFONE_Charge").focus();
return false;
 } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for VODAFONE Charge");
document.getElementById("RM_VODAFONE_Charge").focus();
return false;
}
 WLComm=Number(document.getElementById ( "CM_VODAFONEComm" ).textContent);
 WLCharge=Number(document.getElementById ( "CM_VODAFONECharge" ).textContent);
 RTComm=Number(document.getElementById("RM_VODAFONE_Comm").value);
 RTCharge=Number(document.getElementById("RM_VODAFONE_Charge").value);
 var DSComm=document.getElementById("DS_VODAFONEComm").value;
 var DSCharge=document.getElementById("DS_VODAFONECharge").value;
 var AgentComm=document.getElementById("Agent_VODAFONEComm").value;
 var AgentCharge=document.getElementById("Agent_VODAFONECharge").value;

var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
var limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
var limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);

if(WLComm<totalComm){
alert("Commision for VODAFONE  Service Recharge is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("RM_VODAFONE_Comm").focus();
return false;
}
if(WLCharge<totalCharge){
alert("Charge for VODAFONE  Service Recharge is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("RM_VODAFONE_Charge").focus();
return false;
}
}

}


}
}
var criteria=document.update.PayBillMDCommCriteria.value;

var user=document.getElementById("userTypeForComm").value;


if(criteria=="PayBillMD")
{
document.update.action="UpdateMDPayBillWithMDID.action";
document.update.submit();
}	
if(criteria=="clientID")
{
document.update.action="UpdateMDPayBillWithClientId.action";
document.update.submit();
}

}
}

function resetfunction()
{
document.getElementById("check3").style.visibility="visible";
document.update.action="GetDetailsPayBillMD.action";
document.update.submit();

}

