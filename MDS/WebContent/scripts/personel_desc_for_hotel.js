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

function convertToCaps(input)
{
 
 input.value=input.value.toUpperCase();

}


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
			
			if(parseInt(keyCode)==32 || parseInt(keyCode)==13 || 

parseInt(keyCode)==46 || keyCode==9/*TAB*/ || keyCode==8/*BCKSPC*/ || 

keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ ){
				return true;
			}
			input.focus();
			return false;

}

function digitonly(input,evt)//onkeypress
{
	var keyCode = evt.which ? evt.which : evt.keyCode;
			var lisShiftkeypressed = evt.shiftKey;
			if(lisShiftkeypressed && parseInt(keyCode) != 9) {
			return false ;
			}
			if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || 

			keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || 
			
			keyCode==46/*DEL*/ || keyCode==9/*TAB*/){
			return true;
			}

			return false;

}




function mandatoryfill()
	{	
		var f1=document.bookhotel;	
		if(f1.Cust_title.value=="")
		{
			alert("Please select Title");
			f1.Cust_title.focus();			
			return false;
		}
		else if((f1.Cust_fname.value==null)||(f1.Cust_fname.value==""))
		{
			alert("First Name is mandatory");
			f1.Cust_fname.focus();
			return false;
		}
		else if((f1.Cust_lname.value==null)||(f1.Cust_lname.value==""))
		{
			alert("Last Name is mandatory");
			f1.Cust_lname.focus();
			return false;
		}
	 	 else if((f1.Cust_contact_no1.value==null)||(f1.Cust_contact_no1.value==""))
		{
			alert("Mobile No. can not be blank");
			f1.Cust_contact_no1.focus();
			return false;
		}	
		
		else if((f1.Cust_email.value==null)||(f1.Cust_email.value==""))
		{
			alert("Email can not be blank");
			f1.Cust_email.focus();
			return false;
		}
		else if((f1.Cust_address1.value==null)||(f1.Cust_address1.value==""))
		{
			alert("Address1 is mandatory");
			f1.Cust_address1.focus();
			return false;
		}
		
		else if(f1.Cust_city.value=="value")
		{
			alert("Please select city");
			f1.Cust_city.focus();
			return false;
		}
		else if((f1.Cust_state.value==null)||(f1.Cust_state.value==""))
		{
			alert("State is mandatory");
			f1.Cust_state.focus();
			return false;
		}
		else if(f1.Cust_country.value=="")
		{
			alert("Please select country");
			f1.Cust_country.focus();
			return false;
		}
		else if((f1.Cust_pincode.value==null)||(f1.Cust_pincode.value==""))
		{
			alert("Pin code is mandatory");
			f1.Cust_pincode.focus();
			return false;
		}
		else if(f1.Lead_title.value=="")
		{
			alert("Please select Title in Lead Costomer");
			f1.Lead_title.focus();			
			return false;
		}
		else if((f1.Lead_fname.value==null)||(f1.Lead_fname.value==""))
		{
			alert("First Name of Lead Customer is mandatory");
			f1.Lead_fname.focus();
			return false;
		}
		else if((f1.Lead_lname.value==null)||(f1.Lead_lname.value==""))
		{
			alert("Last Name of Lead Customers is mandatory");
			f1.Lead_lname.focus();
			return false;
		}
		return true;
 	}
function formvalidate()
{
	if((mandatoryfill()) && (checkEmail()))
	{	
		document.bookhotel.submit();
	}
	
}