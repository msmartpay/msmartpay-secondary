function validateAgent(form) {

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

var IdNo = split[1];
var WLComm;
var RTComm;
var DSComm;
var AgentComm;

if(IdNo=="0"){
	RTComm=document.getElementById("RTAircel").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTAircel").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTAircel").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTAircel").focus();
return false;
}
WLComm=Number(document.getElementById ( "WLAircel" ).textContent);
RTComm=Number(RTComm);
var DSComm=document.getElementById("DS_AIRCEL").value;
var MDComm=Number(document.getElementById("MD_AIRCEL").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for Aircel  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("WLAircel").focus();
return false;
}
}


if(IdNo=="1"){
	RTComm=document.getElementById("RTAIRTEL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTAIRTEL").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTAIRTEL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTAIRTEL").focus();
return false;
}
 WLComm=Number(document.getElementById ( "WLAIRTEL" ).textContent);
 RTComm=Number(RTComm);
 
 var DSComm=document.getElementById("DS_AIRTEL").value;
 var MDComm=Number(document.getElementById("MD_AIRCEL").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for Airtel  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTAIRTEL").focus();
return false;
}
}


if(IdNo=="2"){
	RTComm=document.getElementById("RTBSNLRECH").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTBSNLRECH").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTBSNLRECH").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTBSNLRECH").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLBSNLRECH" ).textContent);

 RTComm=Number(RTComm);
 
 var DSComm=document.getElementById("DS_BSNLRECH").value;
 var MDComm=Number(document.getElementById("MD_BSNLRECH").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for BSNL TOP UP  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTBSNLRECH").focus();
return false;
}
}


if(IdNo=="3"){
	RTComm=document.getElementById("RTBSNL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTBSNL").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTBSNL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTBSNL").focus();
return false;
}
 WLComm=Number(document.getElementById ( "WLBSNL" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_BSNL").value;
 var MDComm=Number(document.getElementById("MD_BSNL").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for BSNL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTBSNL").focus();
return false;
}
}


if(IdNo=="4"){
RTComm=document.getElementById("RTIDEA").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTIDEA").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTIDEA").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTIDEA").focus();
return false;
}		
 WLComm=Number(document.getElementById ( "WLIDEA" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_IDEA").value;
 var MDComm=Number(document.getElementById("MD_IDEA").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for IDEA  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTIDEA").focus();
return false;
}
}

if(IdNo=="5"){
	RTComm=document.getElementById("RTBPL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTBPL").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTBPL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTBPL").focus();
return false;
}
 WLComm=Number(document.getElementById ( "WLBPL" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_BPL").value;
 var MDComm=Number(document.getElementById("MD_BPL").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for LOOP  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTBPL").focus();
return false;
}
}

if(IdNo=="6"){
	RTComm=document.getElementById("RTMTS").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTMTS").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTMTS").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTMTS").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLMTS" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_MTS").value;
 var MDComm=Number(document.getElementById("MD_MTS").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);
if(WLComm<total){
alert("Commision for MTS  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTMTS").focus();
return false;
}
}

if(IdNo=="7"){
	RTComm=document.getElementById("RTMTNLT").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTMTNLT").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTMTNLT").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTMTNLT").focus();
return false;
}
 WLComm=Number(document.getElementById ( "WLMTNLT" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_MTNLT").value;
 var MDComm=Number(document.getElementById("MD_MTNLT").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for MTNL TopUp  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTMTNLT").focus();
return false;
}
}

if(IdNo=="8"){
	RTComm=document.getElementById("RTMTNLV").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTMTNLV").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTMTNLV").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTMTNLV").focus();
return false;
}
 WLComm=Number(document.getElementById ( "WLMTNLV" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_MTNLV").value;
 var MDComm=Number(document.getElementById("MD_MTNLV").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for MTNL Validity  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTMTNLV").focus();
return false;
}
}


if(IdNo=="9"){
	RTComm=document.getElementById("RTRELIANCE").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTRELIANCE").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTRELIANCE").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTRELIANCE").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLRELIANCE" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_RELIANCE").value;
 var MDComm=Number(document.getElementById("MD_RELIANCE").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for RELIANCE CDMA  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTRELIANCE").focus();
return false;
}
}

if(IdNo=="10"){
RTComm=document.getElementById("RTRGSM").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTRGSM").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTRGSM").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTRGSM").focus();
return false;
}		
 WLComm=Number(document.getElementById ( "WLRGSM" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_RGSM").value;
 var MDComm=Number(document.getElementById("MD_RGSM").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for RELIANCE GSM  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTRGSM").focus();
return false;
}
}

if(IdNo=="11"){
	RTComm=document.getElementById("RTSTEL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTSTEL").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTSTEL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTSTEL").focus();
return false;
}	

 WLComm=Number(document.getElementById ( "WLSTEL" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_STEL").value;
 var MDComm=Number(document.getElementById("MD_STEL").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for STEl  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTSTEL").focus();
return false;
}
}

if(IdNo=="12"){
	RTComm=document.getElementById("RTTATADOCOMO").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTTATADOCOMO").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTTATADOCOMO").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTTATADOCOMO").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLTATADOCOMO" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_TATADOCOMO").value;
 var MDComm=Number(document.getElementById("MD_TATADOCOMO").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for TATA DOCOMO  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTTATADOCOMO").focus();
return false;
}
}

if(IdNo=="13"){
	RTComm=document.getElementById("RTTATADOCOMOSP").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTTATADOCOMOSP").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTTATADOCOMOSP").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTTATADOCOMOSP").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLTATADOCOMOSP" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_TATADOCOMOSP").value;
 var MDComm=Number(document.getElementById("MD_TATADOCOMOSP").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for TATA DOCOMO SPECIAL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTTATADOCOMOSP").focus();
return false;
}
}

if(IdNo=="14"){
	RTComm=document.getElementById("RTTATA").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTTATA").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTTATA").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTTATA").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLTATA" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_TATA").value;
 var MDComm=Number(document.getElementById("MD_TATA").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for TATA  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTTATA").focus();
return false;
}
}

if(IdNo=="15"){
	RTComm=document.getElementById("RTUNINORS").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTUNINORS").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTUNINORS").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTUNINORS").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLUNINORS" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_UNINORS").value;
 var MDComm=Number(document.getElementById("MD_UNINORS").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for UNINOR SPECIAL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTUNINORS").focus();
return false;
}
}

if(IdNo=="16"){
	RTComm=document.getElementById("RTUNINOR").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTUNINOR").focus();
return false;
}

if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTUNINOR").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTUNINOR").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLUNINOR" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_UNINOR").value;
 var MDComm=Number(document.getElementById("MD_UNINOR").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for UNINOR TOPUP  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTUNINOR").focus();
return false;
}
}


if(IdNo=="17"){
	RTComm=document.getElementById("RTVODAFONE").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTVODAFONE").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTVODAFONE").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTVODAFONE").focus();
return false;
}		

 WLComm=Number(document.getElementById ( "WLVODAFONE" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_VODAFONE").value;
 var MDComm=Number(document.getElementById("MD_VODAFONE").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for VODAFONE  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("VODAFONE").focus();
return false;
}
}

if(IdNo=="18"){
	RTComm=document.getElementById("RTVIDEOCONSP").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTVIDEOCONSP").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTVIDEOCONSP").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTVIDEOCONSP").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLVIDEOCONSP" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_VIDEOCONSP").value;
 var MDComm=Number(document.getElementById("MD_VIDEOCONSP").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for VIDEOCON SPECIAL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTVIDEOCONSP").focus();
return false;
}
}

if(IdNo=="19"){
	RTComm=document.getElementById("RTVIDEOCONT").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTVIDEOCONT").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTVIDEOCONT").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTVIDEOCONT").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLVIDEOCONT" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_VIDEOCONT").value;
 var MDComm=Number(document.getElementById("MD_VIDEOCONT").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for VIDEOCON TOPUP  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTVIDEOCONT").focus();
return false;
}
}
if(IdNo=="20"){
	RTComm=document.getElementById("RTVIRGIN").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTVIRGIN").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTVIRGIN").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTVIRGIN").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLVIRGIN" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_VIRGIN").value;
 var MDComm=Number(document.getElementById("MD_VIRGIN").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for VIRGIN  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTVIRGIN").focus();
return false;
}
}

if(IdNo=="21"){
	RTComm=document.getElementById("RTVIRGINGSM").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTVIRGINGSM").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTVIRGINGSM").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTVIRGINGSM").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLVIRGINGSM" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_VIRGINGSM").value;
 var MDComm=Number(document.getElementById("MD_VIRGINGSM").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for VIRGIN GSM  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTVIRGINGSM").focus();
return false;
}
}



if(IdNo=="22"){
	RTComm=document.getElementById("RTAIRTELDTH").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTAIRTELDTH").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTAIRTELDTH").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTAIRTELDTH").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLAIRTELDTH" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_AIRTELDTH").value;
 var MDComm=Number(document.getElementById("MD_AIRTELDTH").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for AIRTEL DTH  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTAIRTELDTH").focus();
return false;
}
}

if(IdNo=="23"){
	RTComm=document.getElementById("RTDTH_BIGTV").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDTH_BIGTV").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDTH_BIGTV").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDTH_BIGTV").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDTH_BIGTV" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DTH_BIGTV").value;
 var MDComm=Number(document.getElementById("MD_DTH_BIGTV").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DTH_BIGTV  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDTH_BIGTV").focus();
return false;
}
}

if(IdNo=="24"){
	RTComm=document.getElementById("RTDTH_DISHTV").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDTH_DISHTV").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDTH_DISHTV").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDTH_DISHTV").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDTH_DISHTV" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DTH_DISHTV").value;
 var MDComm=Number(document.getElementById("MD_DTH_DISHTV").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DTH_DISHTV  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDTH_DISHTV").focus();
return false;
}
}
if(IdNo=="25"){
	RTComm=document.getElementById("RTDTH_SUNTV").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDTH_SUNTV").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDTH_SUNTV").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDTH_SUNTV").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDTH_SUNTV" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DTH_SUNTV").value;
 var MDComm=Number(document.getElementById("MD_DTH_SUNTV").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DTH_SUNTV  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDTH_SUNTV").focus();
return false;
}
}
if(IdNo=="26"){
	RTComm=document.getElementById("RTDTH_TATASKY").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDTH_TATASKY").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDTH_TATASKY").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDTH_TATASKY").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDTH_TATASKY" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DTH_TATASKY").value;
 var MDComm=Number(document.getElementById("MD_DTH_TATASKY").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DTH_TATASKY  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDTH_TATASKY").focus();
return false;
}
}
if(IdNo=="27"){
	RTComm=document.getElementById("RTDTH_VIDEOCON").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDTH_VIDEOCON").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDTH_VIDEOCON").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDTH_VIDEOCON").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDTH_VIDEOCON" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DTH_VIDEOCON").value;
 var MDComm=Number(document.getElementById("MD_DTH_VIDEOCON").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DTH_VIDEOCON  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDTH_VIDEOCON").focus();
return false;
}
}

if(IdNo=="28"){
	RTComm=document.getElementById("RTDATACARD_AIRCEL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDATACARD_AIRCEL").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDATACARD_AIRCEL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDATACARD_AIRCEL").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDATACARD_AIRCEL" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DATACARD_AIRCEL").value;
 var MDComm=Number(document.getElementById("MD_DATACARD_AIRCEL").value);
 var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
  var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DATACARD_AIRCEL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDATACARD_AIRCEL").focus();
return false;
}
}

if(IdNo=="29"){
	RTComm=document.getElementById("RTDATACARD_BSNL").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDATACARD_BSNL").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDATACARD_BSNL").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDATACARD_BSNL").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDATACARD_BSNL" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DATACARD_BSNL").value;
 var MDComm=Number(document.getElementById("MD_DATACARD_BSNL").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DATACARD_BSNL  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDATACARD_BSNL").focus();
return false;
}
}
if(IdNo=="30"){
	RTComm=document.getElementById("RTDATACARD_MTS").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDATACARD_MTS").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDATACARD_MTS").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDATACARD_MTS").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDATACARD_MTS" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DATACARD_MTS").value;
 var MDComm=Number(document.getElementById("MD_DATACARD_MTS").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DATACARD_MTS  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDATACARD_MTS").focus();
return false;
}
}

if(IdNo=="31"){
	RTComm=document.getElementById("RTDATACARD_RELIENCE").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDATACARD_RELIENCE").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDATACARD_RELIENCE").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDATACARD_RELIENCE").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDATACARD_RELIENCE" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DATACARD_RELIENCE").value;
 var MDComm=Number(document.getElementById("MD_DATACARD_RELIENCE").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DATACARD_RELIENCE  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDATACARD_RELIENCE").focus();
return false;
}
}

if(IdNo=="32"){
	RTComm=document.getElementById("RTDATACARD_TATA").value;
RTComm=RTComm.replace(/^\s+|\s+$/, '');
if(RTComm==""){
alert("Please enter  Commission for ");
document.getElementById("RTDATACARD_TATA").focus();
return false;
}
if (isNaN(RTComm)){ 
alert( "- Please enter your quantity as a number for ");
document.getElementById("RTDATACARD_TATA").focus();
return false;
 } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for ");
document.getElementById("RTDATACARD_TATA").focus();
return false;
}	
 WLComm=Number(document.getElementById ( "WLDATACARD_TATA" ).textContent);
 RTComm=Number(RTComm);
 var DSComm=document.getElementById("DS_DATACARD_TATA").value;
 var MDComm=Number(document.getElementById("MD_DATACARD_TATA").value);
var total=Number(MDComm)+Number(RTComm)+Number(DSComm);
 var limit=Number(WLComm)-Number(MDComm)-Number(DSComm);

if(WLComm<total){
alert("Commision for DATACARD_TATA  Service Recharge is not permitted to set  more than "+limit.toFixed(2))
document.getElementById("RTDATACARD_TATA").focus();
return false;
}
}

}

}
}

var criteria=document.update.AgentCommCriteria.value;
var user=document.getElementById("userTypeForComm").value;

if(user=="Agent")
{
if(criteria=="agent")
{
document.update.action="UpdateAgentRechargeCommWithAgentId.action";
document.update.submit();
}	
if(criteria=="DS")
{
document.update.action="UpdateAgentRechargeCommWithDSId.action";
document.update.submit();
}
if(criteria=="MD")
{
document.update.action="UpdateAgentRechargeCommWithMdId.action";
document.update.submit();
}
if(criteria=="clientID")
{
document.update.action="UpdateAgentRechargeCommWithClientID.action";
document.update.submit();
}
	
}
}
}





function resetfunctionAgent()
{

document.update.action="GetDetailsRechargeAgent.action";
document.update.submit();

}