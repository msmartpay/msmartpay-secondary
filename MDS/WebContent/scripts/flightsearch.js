function submitForm()
{
	alert("abhishek111");
	if(document.getElementById("from").value==-1)
	{
		alert("please Select Source Place");
		document.getElementById("from").focus();
		return false;
	}
	else if(document.getElementById("to").value==-1)
	{
		alert("please Select Destination");
		document.getElementById("to").focus();
		return false;
	}
	else if(document.getElementById("Checkin").value=="")
	{
		alert("please Departure Date");
		document.getElementById("Checkin").focus();
		return false;
	}
	else if(document.getElementById("from").value==document.getElementById("to").value)
		{
		alert("Source And Destination Should Not Same");
		return false;
		}
		
		else if((document.getElementById("double").checked==true))
		{
		
		 if(Calculate_Form('Checkin','Checkout'))
			{	
				
			}
			else
			{
				return false;
			}
		
		//return false;
		}
		
		
	else if(document.getElementById("adult").value=="0")
	{
		alert("Please note every transation must have an adult passenger");
		return false;
	}
	else if(document.getElementById("infant").value > document.getElementById("adult").value)
	{
		alert("Infant Number Not More Than Adults Number");
		return false;
	}
	else if(document.getElementById("adult").value=="6")
	{
		if(parseInt(document.getElementById("infant").value)>3){
			alert("You cannot select more than 3 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>0){
			alert("You cannot select more than 6 adults");
		return false;}
	}
	else if(document.getElementById("adult").value=="5")
	{
		if(parseInt(document.getElementById("infant").value)>2){
			alert("You cannot select more than 2 infants");
				return false;}
		if(parseInt(document.getElementById("child").value)>1){	
			alert("You cannot select more than 6 adults");
				return false;}
	}
	else if(document.getElementById("adult").value=="4")
	{
		if(parseInt(document.getElementById("infant").value)>2){
			alert("You cannot select more than 2 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>2){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="3")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>3){	
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="2")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>4){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="1")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>5){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	
	else
	{
		alert("Hello Ankit"); 
		return false;
	}

}


function Calculate_Form(a,b) 
{
 alert("abhishek");
 var date1=document.getElementById(a).value;
 var date2=document.getElementById(b).value;
 var mon1=date1.substring(3,5);
 var mon2=date2.substring(3,5);
 if(mon1=="")
 {
  alert("Enter depart Date"); 
  return false;
 }
  
 if(mon2=="")
 {
  alert("Enter return Date");
  return false; 
 }
 
 var day1=date1.substring(0,2);
 var day2=date2.substring(0,2);
 
 var yer1=date1.substring(6,10);
 var yer2=date2.substring(6,10);
 
 var myDate1=new Date();
 var myDate2=new Date();
 myDate1.setFullYear(yer1,mon1-1,day1);
 myDate2.setFullYear(yer2,mon2-1,day2);

  if (myDate2<myDate1)
  {
   
   alert("Return date must occur after depart date");   
   return false;
  }
  else 
  {
   return true;
  }
}


function submitForm1()
{
//alert('hi');
	 if(document.getElementById("fromCity").value=="Origin")
  {
   alert("please Select Source Place");
   document.getElementById("fromCity").focus();
   return false;
  }
  else if(document.getElementById("toCity").value=="Destination")
  {
   alert("please Select Destination");
   document.getElementById("toCity").focus();
   return false;
  }
		else if(document.getElementById("toCity").value=="")
		{
			alert("please Select Destination");
			document.getElementById("toCity").focus();
			return false;
		}else if(document.getElementById("Checkin1").value=="")
		{
			alert("Please select Departure Date");
			document.getElementById("Checkin1").focus();
			return false;
		}
		else if(document.getElementById("fromCity").value==toCity[1])
		{
			alert("Source And Destination Should Not Same");
			return false;
		}
		
		else if((document.getElementById("doubleIntr").checked==true))
		{
		if(Calculate_Form('Checkin1','Checkout1'))
			{	
				
			}
			else
			{
				return false;
			}
		}
	//}

		
	if(document.getElementById("adult").value=="0")
	{
		alert("Please note every transation must have an adult passenger");
		return false;
	}
	else if(document.getElementById("infant").value > document.getElementById("adult").value)
	{
		alert("Infant Number Not More Than Adults Number");
		return false;
	}
	else if(document.getElementById("adult").value=="6")
	{
		if(parseInt(document.getElementById("infant").value)>3){
			alert("You cannot select more than 3 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>0){
			alert("You cannot select more than 6 adults");
		return false;}
	}
	else if(document.getElementById("adult").value=="5")
	{
		if(parseInt(document.getElementById("infant").value)>2){
			alert("You cannot select more than 2 infants");
				return false;}
		if(parseInt(document.getElementById("child").value)>1){	
			alert("You cannot select more than 6 adults");
				return false;}
	}
	else if(document.getElementById("adult").value=="4")
	{
		if(parseInt(document.getElementById("infant").value)>2){
			alert("You cannot select more than 2 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>2){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="3")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>3){	
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="2")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>4){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	else if(document.getElementById("adult").value=="1")
	{
		if(parseInt(document.getElementById("infant").value)>1){
			alert("You cannot select more than 1 infants");
			return false;}
		if(parseInt(document.getElementById("child").value)>5){
			alert("You cannot select more than 6 adults");
			return false;}
	}
	
	else
	{
		alert("Hello Anjani"); 
		return false;
	}

}


function hideReturnDate(vv)
{
	var rtext=document.getElementById("rtext");
	var rcal=document.getElementById("rcal");	
	if(vv==1){
	rtext.style.display="";
	rcal.style.display="";
	}
	else if(vv==2)
	{
	rtext.style.display="none";
	rcal.style.display="none";
	}
}
function hideReturnDateInter(vv)
{
	var rtext=document.getElementById("rtext1");
	var rcal=document.getElementById("rcal1");	
	if(vv==1){
	rtext.style.display="";
	rcal.style.display="";
	}
	else if(vv==2)
	{
	rtext.style.display="none";
	rcal.style.display="none";
	}
}
