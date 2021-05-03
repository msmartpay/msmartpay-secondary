function Search()
{
var mySelectbox=document.rechargeForm.serviceList.value;

 if (mySelectbox == "0") {
  alert("Please select an option below.");
  return false;
 }

 if (mySelectbox == "1") {
  document.getElementById("check").style.visibility = "visible";
  document.rechargeForm.action = "airRetailUserMarginSetup.action?paramValue=goToAirServiceTypePage";
  document.rechargeForm.submit();
 }if(mySelectbox=="3")
{
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetPayBillCommPage.action";
document.rechargeForm.submit();
}
if(mySelectbox=="4")
{
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetPayPremiumCommPage.action";
document.rechargeForm.submit();
}
if(mySelectbox=="5")
{
	
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetRetailRailCommPage.action";
document.rechargeForm.submit();
}
if(mySelectbox=="6")
{
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetRechargCommPage.action";
document.rechargeForm.submit();
}

if(mySelectbox=="7")
{
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetOtherCommPage.action";
document.rechargeForm.submit();
}

}
//-------------------
function commisionCriteria(){
var c=document.rechargeForm.userList.value;
if(c=="select")
{
document.getElementById("userList").innerHTML="Please choose appropriate option";	
return false;
}
if(c=="agent"){
document.getElementById("agentCriteria").style.display="";
document.getElementById("DSCriteria").style.display="none";
document.getElementById("MDCriteria").style.display="none";
}
if(c=="DS"){
document.getElementById("DSCriteria").style.display="";
document.getElementById("agentCriteria").style.display="none";
document.getElementById("MDCriteria").style.display="none";
}
if(c=="MD"){
document.getElementById("MDCriteria").style.display="";
document.getElementById("DSCriteria").style.display="none";
document.getElementById("agentCriteria").style.display="none";
}
//----------------------Pay Bill
if(c=="PayBillagent"){
document.getElementById("PayBillagentCriteria").style.display="";
document.getElementById("PayBillDSCriteria").style.display="none";
document.getElementById("PayBillMDCriteria").style.display="none";
}
if(c=="PayBillDS"){
document.getElementById("PayBillDSCriteria").style.display="";
document.getElementById("PayBillagentCriteria").style.display="none";
document.getElementById("PayBillMDCriteria").style.display="none";
}
if(c=="PayBillMD"){
document.getElementById("PayBillMDCriteria").style.display="";
document.getElementById("PayBillDSCriteria").style.display="none";
document.getElementById("PayBillagentCriteria").style.display="none";
}


if(c=="PayPremiumagent"){
document.getElementById("PayPremiumagentCriteria").style.display="";
document.getElementById("PayPremiumDSCriteria").style.display="none";
document.getElementById("PayPremiumMDCriteria").style.display="none";
}
if(c=="PayPremiumDS"){
document.getElementById("PayPremiumDSCriteria").style.display="";
document.getElementById("PayPremiumagentCriteria").style.display="none";
document.getElementById("PayPremiumMDCriteria").style.display="none";
}
if(c=="PayPremiumMD"){
document.getElementById("PayPremiumMDCriteria").style.display="";
document.getElementById("PayPremiumDSCriteria").style.display="none";
document.getElementById("PayPremiumagentCriteria").style.display="none";
}
if(c=="Railagent"){
	
document.getElementById("RailagentCriteria").style.display="";
document.getElementById("RailDSCriteria").style.display="none";
document.getElementById("RailMDCriteria").style.display="none";
}
if(c=="RailDS"){
		
document.getElementById("RailDSCriteria").style.display="";
document.getElementById("RailagentCriteria").style.display="none";
document.getElementById("RailMDCriteria").style.display="none";
}
if(c=="RailMD"){
	
document.getElementById("RailMDCriteria").style.display="";
document.getElementById("RailDSCriteria").style.display="none";
document.getElementById("RailagentCriteria").style.display="none";
}


if(c=="Otheragent"){
	
document.getElementById("OtheragentCriteria").style.display="";
document.getElementById("OtherDSCriteria").style.display="none";
document.getElementById("OtherMDCriteria").style.display="none";
}
if(c=="OtherDS"){
		
document.getElementById("OtherDSCriteria").style.display="";
document.getElementById("OtheragentCriteria").style.display="none";
document.getElementById("OtherMDCriteria").style.display="none";
}
if(c=="OtherMD"){
	
document.getElementById("OtherMDCriteria").style.display="";
document.getElementById("OtherDSCriteria").style.display="none";
document.getElementById("OtheragentCriteria").style.display="none";
}


}

//-------------------

function searchId()
{

var id="";
var userType=document.rechargeForm.userType.value;
var userId=document.rechargeForm.userIdNew.value;
var char=document.rechargeForm.userList.value;
if(char=="agent"){
 id=document.getElementById("AgentcriteriaSelectbox").value;
}
if(char=="DS")
{

 id=document.getElementById("DScriteriaSelectbox").value;
}
if(char=="MD")
{
 id=document.getElementById("MDcriteriaSelectbox").value;
}
//---------------------Pay BIll----------------------
if(char=="PayBillagent")
{
 id=document.getElementById("PayBillAgentcriteriaSelectbox").value;
}
if(char=="PayBillDS")
{

 id=document.getElementById("PayBillDScriteriaSelectbox").value;
}
if(char=="PayBillMD")
{
 id=document.getElementById("PayBillMDcriteriaSelectbox").value;
}

if(id=="clientID")
{

if(userType=="SuperAdmin" || userId=="1" || userId=="10001")
{
document.getElementById("userID").style.display="";
}
else
{
document.getElementById("userID").style.display="none";
}
}
else
{
document.getElementById("userID").style.display="";
}
}

//---------------------get Details function-=-- came in existence after hit submit buotton-----------
function getDetails()
{

var c=document.rechargeForm.userList.value;

var id=document.rechargeForm.enterUserID.value;

if(id=="" || id==null || id=="null"){
alert("please enter user ID")
document.rechargeForm.enterUserID.focus();
return false;
}
if(c=="select"){
document.getElementById("userList").innerHTML ="Please choose any option";
return false;
}

if(c=="agent"){
document.rechargeForm.action="GetDetailsRechargeAgent.action";
document.rechargeForm.submit();
}
if(c=="DS"){
document.rechargeForm.action="GetDetailsRechargeDS.action";
document.rechargeForm.submit();
}

if(c=="MD"){
document.rechargeForm.action="GetDetailsRechargeMD.action";
document.rechargeForm.submit();
}



if(c=="PayBillagent")
{

document.rechargeForm.action="GetDetailsPayBillAgent.action";
document.rechargeForm.submit();
}

if(c=="PayBillDS")
{

document.rechargeForm.action="GetDetailsPayBillDS.action";
document.rechargeForm.submit();
}

if(c=="PayBillMD")
{

document.rechargeForm.action="GetDetailsPayBillMD.action";
document.rechargeForm.submit();
}


if(c=="PayPremiumagent")
{

document.rechargeForm.action="GetDetailsPayPremiumAgent.action";
document.rechargeForm.submit();
}

if(c=="PayPremiumDS")
{

document.rechargeForm.action="GetDetailsPayPremiumDS.action";
document.rechargeForm.submit();
}

if(c=="PayPremiumMD")
{

document.rechargeForm.action="GetDetailsPayPremiumMD.action";
document.rechargeForm.submit();
}


if(c=="Railagent")
{

document.rechargeForm.action="GetDetailsRailAgent.action";
document.rechargeForm.submit();
}

if(c=="RailDS")
{

document.rechargeForm.action="GetDetailsRailDS.action";
document.rechargeForm.submit();
}

if(c=="RailMD")
{

document.rechargeForm.action="GetDetailsRailMD.action";
document.rechargeForm.submit();
}



if(c=="Otheragent")
{

document.rechargeForm.action="GetDetailsOtherAgent.action";
document.rechargeForm.submit();
}

if(c=="OtherDS")
{

document.rechargeForm.action="GetDetailsOtherDS.action";
document.rechargeForm.submit();
}

if(c=="OtherMD")
{

document.rechargeForm.action="GetDetailsOtherMD.action";
document.rechargeForm.submit();
}


}





function check()
{
	
	var allcheck = document.update.checkAll;
	
	if(allcheck.checked == true)
	{
		for(i=0; i<document.update.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
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
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
			{
				document.update.elements[i].checked=false;
			}
			
		}
	}
}
