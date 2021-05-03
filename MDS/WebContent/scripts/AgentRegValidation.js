// JavaScript Document
function no()
{

if(event.keyCode == 39)
		{		
			event.keyCode=null;    
			alert("Single quatation mark is not valid");				   
			return false;
		}	
		
}
function digitonly()
{
if(event.keyCode < 48 || event.keyCode > 58)
		{
		if(event.keyCode != 8)
		  {
			event.keyCode=null;
			alert("Please Enter Only Digits");
			
			return false;
		   }
		}
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

function alphaonly(input,event)//onkeypress
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

function turnoverOnly()
{
if(event.keyCode < 46 || event.keyCode > 58)
		{
		if(event.keyCode != 8)
		  {
			event.keyCode=null;
			alert("Please Enter Only Digits and .");
			
			return false;
		   }
		}
}

function PhoneOnly()
{
if(event.keyCode < 43 || event.keyCode > 58)
		{
		if(event.keyCode != 8)
		  {
			event.keyCode=null;
			alert("Please Enter Only Digits");
			
			return false;
		   }
		}
}

function alphaonly()
{
  if(event.keyCode < 65 || event.keyCode > 90)
       {
       	if(event.keyCode < 97 || event.keyCode > 122)
       	{
       		if(event.keyCode != 32)
       		{
       	 		if(event.keyCode != 8)
		  		{
      			  event.keyCode=null;
      			  alert("Please Enter Only Alphabets");
				  return false;
		  		}
			}
		 }
	  }

}	


function checkEmail()
{

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(document.regiester.email.value))
           {
				return true;
			}
		else
		{
			alert("Invalid E-mail Address! Please re-enter correct email ID.")
			document.regiester.email.select();
			document.regiester.email.focus();
			return false;
		} 
}

function mandatoryfill()
	{	
		var f1=document.regiester;	
		
		if((f1.aname.value==null)||(f1.aname.value==""))
		{
			alert("Agency Name is mandatory");
			f1.aname.focus();
			return false;
		}
		else if((f1.user_name.value==null)||(f1.user_name.value==""))
		{
			alert("User Name is mandatory");
			f1.user_name.focus();
			return false;
		}
	 	 else if((f1.password.value==null)||(f1.password.value==""))
		{
			alert("Password can not be blank");
			f1.password.focus();
			return false;
		}
		
		else if((f1.c_password.value==null)||(f1.c_password.value==""))
		{
			alert("Confirm Password can not be blank");
			f1.c_password.focus();
			return false;
		}
		else if((f1.street.value==null)||(f1.street.value==""))
		{
			alert("Address is mandatory");
			f1.street.focus();
			return false;
		}
		else if(f1.country.value=="")
		{
			alert("Please select country");
			f1.country.focus();
			return false;
		}
		else if(f1.state.value=="-1")
		{
			alert("Please select state");
			f1.state.focus();
			return false;
		}
		else if(f1.city.value=="-1")
		{
			alert("Please select city");
			f1.city.focus();
			return false;
		}		
		else if((f1.pincode.value==null)||(f1.pincode.value==""))
		{
			alert("Pincode is mandatory");
			f1.pincode.focus();
			return false;
		}
		else if((f1.phone.value==null)||(f1.phone.value==""))
		{
			alert("Phone is mandatory");
			f1.phone.focus();
			return false;
		}
		else if((f1.mobile.value==null)||(f1.mobile.value==""))
		{
			alert("Mobile is mandatory");
			f1.mobile.focus();
			return false;
		}
		else if((f1.email.value==null)||(f1.email.value==""))
		{
			alert("Email is mandatory");
			f1.email.focus();
			return false;
		}
		
		else if(f1.password.value!=f1.c_password.value)
		{
			alert("Confirm passwords doesn't match");
  			f1.c_password.focus();
 			return false;
		}
		
		return true;
 	}
function enableText(){
	var f1=document.regiester;	
	f1.member11.disabled=false;

}
function enableText1(){
	var f1=document.regiester;	
	f1.member21.disabled=false;
}
function enableText2(){
	var f1=document.regiester;	
	f1.member31.disabled=false;
}
function enableText3(){
	var f1=document.regiester;	
	f1.member41.disabled=false;
}
function submitForm()
{
	var f1=document.regiester;
	f1.submit();
}