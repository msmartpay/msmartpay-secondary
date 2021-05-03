function alphaonly(input,event)
{
  var keyCode = event.which ? event.which : event.keyCode;
			//Small Alphabets
			if(parseInt(keyCode)>=97 && parseInt(keyCode)<=122){
				return true;
			}
			//Caps Alphabets
			if(parseInt(keyCode)>=65 && parseInt(keyCode)<=90){
				return true;
			}
			//Space-Return-Dot<!-- Changes made by Sanjiv for ELTOPUSPRD-18498 -->
			if(parseInt(keyCode)==32 || parseInt(keyCode)==13 || parseInt(keyCode)==46 || keyCode==9/*TAB*/ || keyCode==8/*BCKSPC*/ || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ ){
				return true;
			}
			input.focus();
			return false;

}

function convertToCaps(input)
{
 
 input.value=input.value.toUpperCase();

}

function digitonly(input,evt)
{
	var keyCode = evt.which ? evt.which : evt.keyCode;
			var lisShiftkeypressed = evt.shiftKey;
			if(lisShiftkeypressed && parseInt(keyCode) != 9) {
			return false ;
			}
			if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
			return true;
			}

			return false;

		}

function checkPre1(val,evt)
{
var p1=document.getElementById("stdCode1").value;
if(p1=="")
	{
		alert("Enter STD code");
		document.getElementById("stdCode1").focus();
		val.value="";;
		return false;
	}
	else
	{

		return digitonly(val,evt);
}
}

function checkPre2(val,evt)
{
var p1=document.getElementById("stdCode2").value;
if(p1=="")
	{
		alert("Enter STD code");
		document.getElementById("stdCode2").focus();
		val.value="";;
		return false;
	}
	else
	{

		return digitonly(val,evt);
}
}

function checkPre3(val,evt)
{
var p1=document.getElementById("stdCode3").value;
if(p1=="")
	{
		alert("Enter STD code");
		document.getElementById("stdCode3").focus();
		val.value="";;
		return false;
	}
	else
	{

		return digitonly(val,evt);
}
}

function checkPre4(val,evt)
{
var p1=document.getElementById("faxStd").value;
if(p1=="")
	{
		alert("Enter STD code");
		document.getElementById("faxStd").focus();
		val.value="";;
		return false;
	}
	else
	{

		return digitonly(val,evt);
}
}

function checkEmail(val)
{



if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,10})+$/.test(val.value))
           {
				return true;
		   }
		else
		{
			
			alert("Invalid E-mail Address! Please re-enter correct email ID.");
			val.value="";
			val.focus();
			val.select();
			return false;
		} 


}	
function checkCeoEmail()
{

//alert("hi ceo");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.regiestration.ceoEmail.value))
           {
				return true;
			}
		else
		{
			alert("Invalid E-mail Address! Please re-enter correct email ID.")
			document.regiestration.ceoEmail.value="";
			//document.regiestration.ceoEmail.select();
			document.regiestration.ceoEmail.focus();
			return false;
		} 

}	


function mandatoryfill()
	{	
		var f1=document.regiestration;
		
		 if(f1.distributorName.value=="")
		{
			alert("Enter Agency Name");
			f1.distributorName.focus();
			return false;
		}
		 if(f1.distributorName.value.length<3)
		{
			alert("Agency Name should not be less than 3 characters ");
			f1.distributorName.focus();
			return false;
		}
		
	    if(f1.address1.value=="")
		{
			alert("Enter Address");
			f1.address1.focus();
			return false;
		}
	     if(f1.state.value=="-1")
		{
			alert("Select State Value");
			f1.state.focus();
			return false;
		}
		 if(f1.city.value=="-1")
		{
			alert("Select City Name");
			f1.city.focus();
			return false;
		}
		
		 if(f1.pincode.value=="")
		{
			alert("Enter Pin Code");
			f1.pincode.focus();
			return false;
		}
		 if(f1.pincode.value.length<6)
		{
			alert("Pin Code length should not be less than 6 digits");
			f1.pincode.focus();
			return false;
		}
		
		
		 if(f1.email.value=="")
		{
			alert("Enter Email ID");
			f1.email.focus();
			return false;
		}
		 if(f1.officePhone1.value=="")
		{
			alert("Enter Office Phone No");
			f1.officePhone1.focus();
			return false;
		}
		 if(f1.mobilePhone.value=="")
		{
			alert("Enter Mobile No");
			f1.mobilePhone.focus();
			return false;
		}
		 if(f1.mobilePhone.value.length<10)
		{
			alert("Mobile No should not be less than 10 digits");
			f1.mobilePhone.focus();
			return false;
		}
		 if(f1.officePhone1.value.length<4)
		{
			alert("Office Phone No is not correct");
			f1.officePhone1.focus();
			return false;
		}
		
		
		 
		
		
		 if(f1.LoginId.value=="")
		{
			alert("Enter Agency Login");
			f1.LoginId.focus();
			return false;
		}
		 if(f1.pass.value=="")
		{
			alert("Enter Password");
			f1.pass.focus();
			return false;
		}
		 if(f1.conPass.value=="")
		{
			alert("Enter confirm password");
			f1.conPass.focus();
			return false;
		}
		 if(f1.pass.value!=f1.conPass.value)
		{
			alert("Confirm passwords doesn't match");
  			f1.conPass.focus();
 			return false;
		}
		 if(f1.terms.checked!=true)
		{
			alert("Please Accept Agency Terms of Service");
			return false;
		}
		
		 
		return true;
	}