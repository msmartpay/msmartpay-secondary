function submitForm()
{
	if(chk2())
	{
		var stmt="";
		var tnxId = document.getElementById("tnxId").value;
		if(tnxId=="")
		{
			stmt="all";
		}
		else{
			stmt="id";
		}
		document.myForm.action="accountStatement.action?stmt="+stmt+"";
		document.myForm.submit();
	}
}

function chk2()
{
var reportId="";
var userType=document.getElementById("userType").value;
var to = document.getElementById("todate").value;
var from = document.getElementById("fromdate").value;

if(userType=="select")
{
alert("Please Select user to view report");
document.getElementById("userType").focus();
return false;
}

if(from=="")
{
alert("Please Select From Date");

document.getElementById("fromdate").focus();
return false;
}
if(to=="")
{
alert("Please Select To Date");

document.getElementById("todate").focus();
return false;
}
return true;

}