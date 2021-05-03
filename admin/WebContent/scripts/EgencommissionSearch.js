function EgenopenPage()
{

var EnterUserId=document.rechargeForm.enterUserId.value;
var mySelectbox=document.rechargeForm.MainService.value;
var userType=document.rechargeForm.userType.value;
var userId=document.rechargeForm.userIdNew.value;

if(userId!="10001"&&(userId!="1"&&!userType.equalsIgnoreCase("superAdmin"))){
if(EnterUserId==""){
 document.getElementById("user").innerHTML ="Please insert userId";
 return false;
}
if( parseFloat(EnterUserId)!= parseInt(parseFloat(EnterUserId))){
        document.getElementById("user").innerHTML ="Decimal value not allowed";
	     rechargeForm.EnterUserId.focus();
		  return false;

}

if (isNaN(EnterUserId)){ 
document.getElementById("user").innerHTML ="Please enter your quantity as a number";
return false;
 } 
if(parseInt(EnterUserId)<0){
document.getElementById("user").innerHTML ="Please enter correct user id";
return false;
}
}
if(mySelectbox=="0"){
document.getElementById("option").innerHTML ="Please choose any option";
 return false;
 }

if(mySelectbox=="billpay")
{
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetEgenRechargeCommDetails.action";
document.rechargeForm.submit();
}
if(mySelectbox=="RechargeService")
{
	
var service=document.rechargeForm.SubService.value;

if(service=="0")
{
alert("Please choose correct option")
document.rechargeForm.SubService.focus();
return false;
}
	
document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetEgenRechargeCommDetails.action";
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
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name!="checkAll" &&  table[i].getAttribute("name")!="checkbox1")
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





//------------------------------------------------check for validation--------------------
function checkService()
{
var service=document.rechargeForm.MainService.value;
var userType=document.rechargeForm.userType.value;
var userId=document.rechargeForm.userIdNew.value;

if(service=="0")
{
alert("Please choose correct option")
document.rechargeForm.MainService.focus();
return false;
}
	
if(service=="RechargeService")
{
document.getElementById("rechargeOption").style.display="";
}	
else
{
document.getElementById("rechargeOption").style.display="none";
}
if(userType=="SuperAdmin" || userId=="1" || userId=="10001")
{
document.getElementById("userID").style.display="none";
document.getElementById("ChooseID").style.display="";
}
else
{
document.getElementById("ChooseID").style.display="none";
document.getElementById("userID").style.display="";
}



}

//------------------------get iD

function getID()
{
	var id=document.getElementById("IDOption").value;
	

if(id=="0")
{
document.getElementById("userID").style.display="none";
alert("please choose correct option")

return false;
}
	if(id=="All")
	{
		document.getElementById("userID").style.display="none";
	}
	else
	{
			document.getElementById("userID").style.display="";
	}
	

}

//-------------------------------------block to  update commission---------------

function doUpdate(i)
{

var boxesTicked1 = "";
var t=i;
for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked)
 {
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) 
{
alert("Please Select Atleast One transaction");
return boxesTicked1;
}



var chks = document.getElementsByName('checkpartial');

for (var j=0;j<t;j++)
{

if(chks[j].checked)
	{
		
var namelist = chks[j].value;
var split=namelist.split("&");
var operatorName = split[0];


 var masterComm=Number(document.getElementById("mComm"+j).value);

var ApiComm=document.getElementById("upadteCommission"+j).value;

ApiComm=ApiComm.replace(/^\s+|\s+$/, '');

	        if(ApiComm=="")
			{
		     alert("Please enter commiision.");
			 return false;
	 
	      	}
		 if(isNaN(ApiComm))
		  {
		  alert("Please enter only digit in amount");
		  return false;
		  }
		
   if( parseFloat(ApiComm)!= parseInt(parseFloat(ApiComm))){

         alert ('Decimal value not allowed');
	     return false;

		}
 
	if(masterComm<Number(ApiComm))	
	{
	alert("Commission can be set more than the master commission for   "+operatorName)
	return false;
	} 
     }

 }

document.update.action="UpdateEgen.action";
document.update.submit();	
	
}

//---------------------function for reset-------------
function resetfunction()
{

document.getElementById("check").style.visibility="visible";
document.rechargeForm.action="GetEgenRechargeCommDetails.action";
document.rechargeForm.submit();
	
	
}