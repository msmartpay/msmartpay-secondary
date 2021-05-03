


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





//----------------------------------------Validation for Pay Premium Commission--------------------






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

var criteria=document.update.SubmitCriteria.value;
var checkvalue;
alert("criteria---"+criteria)

if(criteria=="OtherMD")
{
	checkvalue="MDS";
	if(checkValidation(checkvalue)){
	  document.update.action="UpdateMDOtherWithMDID.action";
      document.update.submit();	
	}else {
	return false;	
		
	}


}
if(criteria=="MDClientID")
{
checkvalue="MDS";
	if(checkValidation(checkvalue)){	
   document.update.action="UpdateMDOtherWithClientID.action";
   document.update.submit();
	}else {
		return false;
	}
}	

if(criteria=="OtherDS")
{
	checkvalue="DS";
	if(checkValidation(checkvalue)){
            document.update.action="updateDSOtherWithDSID.action";
          document.update.submit();
	}else {
		return false;
	}
}	
if(criteria=="DSMDID")
{
	checkvalue="DS";
	if(checkValidation(checkvalue)){
            document.update.action="updateDSOtherWithMDID.action";
            document.update.submit();
	}else {
		return false;
	}
}
if(criteria=="DSClientID")
{
	checkvalue="DS";
	if(checkValidation(checkvalue)){
        document.update.action="updateDSOtherWithclientID.action";
        document.update.submit();
	}else {
		return false;
	}
		
}

if(criteria=="Otheragent")
{
	checkvalue="Agent";
	if(checkValidation(checkvalue)){
          document.update.action="updateAgentOtherWithAgentID.action";
          document.update.submit();
	}else {
		return false;
	}
}	
if(criteria=="AgentDS")
{
	checkvalue="Agent";
	if(checkValidation(checkvalue)){
        document.update.action="updateAgentOtherWithDSID.action";
        document.update.submit();
	}else {
		return false;
	}
}
if(criteria=="AgentMD")
{
	checkvalue="Agent";
	if(checkValidation(checkvalue)){
          document.update.action="updateAgentRailWithMDID.action";
         document.update.submit();
	}else {
		return false;
	}
}

if(criteria=="AgentClient")
{
	checkvalue="Agent";
	if(checkValidation(checkvalue)){
           document.update.action="updateAgentOtherWithClientId.action";
           document.update.submit();
	}else {
		return false;
	}
}
}	
}

function checkValidation(k)
{
	alert("inside checkValidation")
	var checkValue=k;
	alert("checkValue"+checkValue)

var namelist = "";
with(document.update) {
for(var i = 0; i < checkpartial.length; i++){
if(checkpartial[i].checked) {
namelist = checkpartial[i].value;

var split=namelist.split("&");

var IdNo = split[2];

var WLComm;
var DSComm;
var MDComm;
var AgentComm;
var WLCharge;
var limitComm;
var RTComm;
if(IdNo=="0"){
	WLComm=document.getElementById("CM_BusComm").value;
	DSComm=document.getElementById("DS_BusComm").value;
	AgentComm=document.getElementById("Agent_BusComm").value;
	MDComm=document.getElementById("MD_BusComm").value;
	RTComm=document.getElementById("BusComm").value;
    RTComm=RTComm.replace(/^\s+|\s+$/, '');
    if(RTComm==""){
    alert("Please enter  Commission for Bus ");
   document.getElementById("BusComm").focus();
   return false;
   }
   if (isNaN(RTComm)){ 
  alert( "- Please enter your quantity as a number for Bus ");
  document.getElementById("BusComm").focus();
  return false;
  } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for BUS   ");
document.getElementById("BusComm").focus();
return false;
}	
	
	if(checkValue=="MDS"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
	}
	 if(checkValue=="DS"){
	   var totalComm=Number(MDComm)+Number(RTComm)+Number(AgentComm);
	   limitComm=Number(WLComm)-Number(MDComm)-Number(AgentComm);	
	 }
	if(checkValue=="Agent"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(MDComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(MDComm);
	}
if(Number(WLComm)<Number(totalComm)){
alert("Commision for BUS  Service  is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("BusComm").focus();
return false;
}
}
//-----------------------------------	
if(IdNo=="1"){
	WLComm=document.getElementById("CM_EduComm").value;
	DSComm=document.getElementById("DS_EduComm").value;
	AgentComm=document.getElementById("Agent_EduComm").value;
	MDComm=document.getElementById("MD_EduComm").value;
	RTComm=document.getElementById("EduComm").value;
	RTComm=RTComm.replace(/^\s+|\s+$/, '');
    if(RTComm==""){
    alert("Please enter  Commission for Education ");
   document.getElementById("EduComm").focus();
   return false;
   }
   if (isNaN(RTComm)){ 
  alert( "- Please enter your quantity as a number for Education ");
  document.getElementById("EduComm").focus();
  return false;
  } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Education   ");
document.getElementById("EduComm").focus();
return false;
}	
	
	if(checkValue=="MDS"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
	}
	 if(checkValue=="DS"){
	   var totalComm=Number(MDComm)+Number(RTComm)+Number(AgentComm);
	   limitComm=Number(WLComm)-Number(MDComm)-Number(AgentComm);	
	 }
	if(checkValue=="Agent"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(MDComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(MDComm);
	}
if(Number(WLComm)<Number(totalComm)){
alert("Commision for Education  Service  is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("EduComm").focus();
return false;
}
}
//------------------
if(IdNo=="2"){
	var limitCharge;
	var totalCharge;
	WLComm=document.getElementById("CM_PancardComm").value;
	DSComm=document.getElementById("DS_PancardComm").value;
	AgentComm=document.getElementById("Agent_PancardComm").value;
	MDComm=document.getElementById("MD_PancardComm").value;
	RTComm=document.getElementById("PancardComm").value;
	
	WLCharge=document.getElementById("CM_PancardCharge").value;
	DSCharge=document.getElementById("DS_PancardCharge").value;
	AgentCharge=document.getElementById("Agent_PancardCharge").value;
	MDCharge=document.getElementById("MD_PancardCharge").value;
	RTCharge=document.getElementById("PancardCharge").value;
	RTComm=RTComm.replace(/^\s+|\s+$/, '');
    if(RTComm==""){
    alert("Please enter  Commission for Pancard commission ");
   document.getElementById("PancardComm").focus();
   return false;
   }
   if (isNaN(RTComm)){ 
  alert( "- Please enter your quantity as a number for Pancard commission  ");
  document.getElementById("PancardComm").focus();
  return false;
  } 	
if(Number(RTComm)<0){
alert( "- Please enter your quantity as a positive number for Pancard commission    ");
document.getElementById("PancardComm").focus();
return false;
}	

RTCharge=RTCharge.replace(/^\s+|\s+$/, '');
    if(RTCharge==""){
    alert("Please enter  Commission for Pancard Charge ");
   document.getElementById("PancardCharge").focus();
   return false;
   }
   if (isNaN(RTCharge)){ 
  alert( "- Please enter your quantity as a number for Pancard Charge ");
  document.getElementById("PancardCharge").focus();
  return false;
  } 	
if(Number(RTCharge)<0){
alert( "- Please enter your quantity as a positive number for Pancard Charge   ");
document.getElementById("PancardCharge").focus();
return false;
}	

	
	if(checkValue=="MDS"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(AgentComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(AgentComm);
		  var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(AgentCharge);
		 limitCharge=Number(WLCharge)-Number(DSCharge)-Number(AgentCharge);
	}
	 if(checkValue=="DS"){
	   var totalComm=Number(MDComm)+Number(RTComm)+Number(AgentComm);
	   limitComm=Number(WLComm)-Number(MDComm)-Number(AgentComm);
	     var totalCharge=Number(MDCharge)+Number(RTCharge)+Number(AgentCharge);
		 limitCharge=Number(WLCharge)-Number(MDCharge)-Number(AgentCharge);
	 }
	if(checkValue=="Agent"){
		 var totalComm=Number(DSComm)+Number(RTComm)+Number(MDComm);
		 limitComm=Number(WLComm)-Number(DSComm)-Number(MDComm);
		   var totalCharge=Number(DSCharge)+Number(RTCharge)+Number(MDCharge);
		 limitCharge=Number(WLCharge)-Number(DSCharge)-Number(MDCharge);
	}
if(Number(WLComm)<Number(totalComm)){
alert("Commision for Pancard   commission  is not permitted to set  more than "+limitComm.toFixed(2))
document.getElementById("PancardComm").focus();
return false;
}
	
if(Number(WLCharge)<Number(totalCharge)){
alert("Commision for Pancard  Service charge  is not permitted to set  more than "+limitCharge.toFixed(2))
document.getElementById("PancardCharge").focus();
return false;
}	
}

}}}	
	
	
	
	
return true;	
	
}
