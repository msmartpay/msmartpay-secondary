
//------------------------------for filterartion
function getSubService()
{
var selectServcie="";
selectServcie=document.serarchStatus.service.value;
if(selectServcie=="select")
{
document.getElementById("rechargeService").style.display="none";
document.getElementById("otherserviceDetails").style.display="none";
alert("please choose correct servcie")
return false;
}
if(selectServcie=="rechargeService")
{
document.getElementById("rechargeService").style.display="";
document.getElementById("otherserviceDetails").style.display="none";
}
else
{
document.getElementById("rechargeService").style.display="none";
document.getElementById("otherserviceDetails").style.display="";
}
}

//-----------------------------------Ajax call------------------------
function ok2(val)
 {
var tttt2=val;
var service="";
var mainservice="";
createXMLHttpRequest();
var controlServcie=document.serarchStatus.mainservice.value;

var service=document.serarchStatus.service.value;
if(service=="rechargeService")
{
service=document.serarchStatus.subservice.value;
  document.getElementById("checkRecharge").style.visibility = "visible";
}
else
{
service=document.serarchStatus.Othersubservice.value;
  document.getElementById("checkOther").style.visibility = "visible";
}

if(controlServcie=="EgenServiceControl")
{
mainservice=controlServcie;
}
else(controlServcie=="WLServiceControl")
{
mainservice=controlServcie;
}

xmlHttp.onreadystatechange = printValues2;

xmlHttp.open("POST","getOperator.action?serviceType="+service+"&mainService="+mainservice, true);
xmlHttp.send(null);
}

function printValues2()
{
if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response2=xmlHttp.responseText;
				
		
				if(response2=="Valid")
				{
              solution(tttt2);								
				}
				else
				{
				var opeCheck=document.serarchStatus.Othersubservice.value;
				var userType=document.serarchStatus.userType.value;
			   var service=document.serarchStatus.service.value;
                         if(service=="rechargeService")
                             {
                          document.getElementById("checkRecharge").style.visibility = "hidden";
                             }
                             else
                                    {
                        document.getElementById("checkOther").style.visibility = "hidden";
                      }
		
			    
				if(opeCheck=="air" || opeCheck=="bus" || opeCheck=="rail")
				{
				document.getElementById("operatorName").style.display ="none";
				if(userType=="AllAgent")
				{
				document.getElementById("userIdValue").style.display ="none";
				document.getElementById("userIdName").style.display ="none";
				}else
				{
				document.getElementById("userIdValue").style.display ="";
				document.getElementById("userIdName").style.display ="";
				}
				
				
				}
				else
				{
				
				document.getElementById("operatorName").style.display ="";
				if(userType=="AllAgent")
				{
				document.getElementById("userIdValue").style.display ="none";
				document.getElementById("userIdName").style.display ="none";
				}else
				{
				document.getElementById("userIdValue").style.display ="";
				document.getElementById("userIdName").style.display ="";
				}
				
				document.getElementById("Operator").innerHTML = response2;
				}
			
				}
			}
		}
}
 
 
 
 var xmlHttp;

function createXMLHttpRequest()
{

	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		
		}
}






function GO()
{
	
	var main=document.serarchStatus;
	
	if(main.userType.value=="select")
	{
		alert("Please choose any user id")
		main.userType.focus();
		return false;
		
	}
	var service=main.service.value;
	
	if(service=="select")
	{
		alert("Please choose any service")
		main.service.focus();
		return false;
	}
	if(service=="rechargeService")
	{
		
	if(main.subservice.value=="select")
	{
		alert("Please choose any Sub service")
		main.subservice.focus();
		return false;
	}
		
	}
	else
	{
	if(main.Othersubservice.value=="select")
	{
	    alert("Please choose any Sub service")
		main.Othersubservice.focus();
		return false;
	}}
	
	
	main.submit();
}

//-----------------------------------------------for check all-----------------------
function block12(i)
{

var k=i;

if(checkValidation(k))
{
		
var mainservice=document.update.mainservice.value;


if(mainservice=="EgenServiceControl")
{
document.update.action="updateEgenService.action?actionTake=N";
}
if(mainservice=="WLServiceControl")
{
document.update.action="updateWLService.action?actionTake=N";	
	
}

document.update.submit();	
	
}


}


function Unblock(i)
{

var k=i;
if(checkValidation(k))
{
	
var mainservice=document.update.mainservice.value;

if(mainservice=="EgenServiceControl")
{
document.update.action="updateEgenService.action?actionTake=Y";
}
if(mainservice=="WLServiceControl")
{
document.update.action="updateWLService.action?actionTake=Y";	
	
}

document.update.submit();
}
}




function checkValidation(k)
{

var boxesTicked1 = "";

for (i = document.getElementsByName('updateCheckpartail').length - 1; i >= 0; i--)
{
if (document.getElementsByName('updateCheckpartail')[i].checked)
 {
boxesTicked1 = boxesTicked1 + document.getElementsByName('updateCheckpartail')[i].value + "\n"
}
}
if (boxesTicked1 <1 ) 
{
alert("Please Select Atleast One operator");
return boxesTicked1;
}

var chks = document.getElementsByName('updateCheckpartail');
for (var j=0;j<k;j++)
{

if(chks[j].checked)
	{

 var internal=document.getElementById("internalremark"+j).value;
var response=document.getElementById("response"+j).value;
internal=internal.replace(/^\s+|\s+$/, '');
response=response.replace(/^\s+|\s+$/, '');

	        if(internal=="")
			{
		     alert("Please enter internal remark");
			 return false;
	 
	      	}
			if(response=="")
			{
		     alert("Please enter response message");
			 return false;
	 
	      	}
		
 
     }

 }
return true;
}