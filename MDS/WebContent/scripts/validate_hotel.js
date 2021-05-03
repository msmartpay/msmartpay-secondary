// JavaScript Document

function dateDiff(date1,date2)
{
var mon1=date1.substring(3,5);
var mon2=date2.substring(3,5);
if(mon1=="")
{
	alert("Enter CheckOut Date");	
	return false;
}
	
if(mon2=="")
{
	alert("Enter CheckIn Date");
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

	if (myDate2>=myDate1)
	{
		alert("You Can't Take CheckOut Date less Then CheckIn Date");			
		return false;
	}
	else
	{	
		document.hotel.noofdays.value=(myDate1-myDate2)/(1000*60*60*24);		
		return true;		
	}
}
function hidechildinfo()
{
	if(document.hotel.child.value==0)
	{
	document.getElementById("child1").style.visibility='hidden';
	document.getElementById("child1").style.display = 'none';
	document.getElementById("child1val").style.visibility='hidden';
	document.getElementById("child1val").style.display = 'none';
	document.getElementById("child2").style.visibility='hidden';
	document.getElementById("child2").style.display = 'none';
	document.getElementById("child2val").style.visibility='hidden';
	document.getElementById("child2val").style.display = 'none';
	document.getElementById("message").innerHTML="";
	}
	else if(document.hotel.child.value==1)
	{
	document.getElementById("child1").style.visibility='visible';
	document.getElementById("child1").style.display = '';
	document.getElementById("child1val").style.visibility='visible';
	document.getElementById("child1val").style.display = '';
	document.getElementById("child2").style.visibility='hidden';
	document.getElementById("child2").style.display = 'none';
	document.getElementById("child2val").style.visibility='hidden';
	document.getElementById("child2val").style.display = 'none';
	document.getElementById("message").innerHTML="";
	}
	else
	{
	document.getElementById("child1").style.visibility='visible';
	document.getElementById("child1").style.display = '';
	document.getElementById("child1val").style.visibility='visible';
	document.getElementById("child1val").style.display = '';
	document.getElementById("child2").style.visibility='visible';
	document.getElementById("child2").style.display = '';
	document.getElementById("child2val").style.visibility='visible';
	document.getElementById("child2val").style.display = '';
	document.getElementById("message").innerHTML="";
	}
}

function hidechild()
{
	//alert("inside hidechild;
	document.getElementById("child1").style.visibility='hidden';
	document.getElementById("child1").style.display ='none';
	document.getElementById("child1val").style.visibility='hidden';
	document.getElementById("child1val").style.display ='none';
	document.getElementById("child2").style.visibility='hidden';
	document.getElementById("child2").style.display ='none';
	document.getElementById("child2val").style.visibility='hidden';
	document.getElementById("child2val").style.display ='none';
	
}
function searching()
{
	//alert("inside serching");
	var adult=document.hotel.adult.value;
	
	var child=document.hotel.child.value;
	if(adult==1 && child==0)
	{
		//alert(" inside 1 and 0"); 
	document.hotel.singleroom.value=1;
	document.hotel.doubleroom.value=0;
	document.hotel.extraperson.value=0;
	document.hotel.childwithbed.value=0;
	document.hotel.childwithoutbed.value=0;
	return true;
	}
	else if((adult==1) && (child==1))
	{
		//alert(" inside 1 and 1"); 
		if(document.hotel.agechild1.value>6)
		{
			document.hotel.singleroom.value=0;
			document.hotel.doubleroom.value=1;
			document.hotel.extraperson.value=0;
			document.hotel.childwithbed.value=0;
			document.hotel.childwithoutbed.value=1;
			return true;
		}
		else
		{
		document.hotel.singleroom.value=1;
		document.hotel.doubleroom.value=0;
		document.hotel.extraperson.value=0;
		document.hotel.childwithbed.value=1;
		document.hotel.childwithoutbed.value=0;
		return true;
		}
	}
	else if((adult==1)&&(child==2))					 
		{
			//alert(" inside 1 and 2"); 
			if((document.hotel.agechild1.value<6)&&(document.hotel.agechild2.value<6))
			{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=0;
				document.hotel.childwithoutbed.value=2;
				return true;
			}
			 if((document.hotel.agechild1.value>6)||(document.hotel.agechild2.value>6))
			{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=1;
				document.hotel.childwithoutbed.value=1;
				return true;
			}
		}
	else if((adult==2)&&(child==0))
		{
				//alert(" inside 2 and 0"); 
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=0;
				document.hotel.childwithoutbed.value=0;
				return true;
		}
	else if((adult==2)&&(child==1))
		{
				//alert(" inside 2 and 1"); 
				if(document.hotel.agechild1.value>6)
				{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=1;
				document.hotel.childwithoutbed.value=0;
				return true;
				}
				else
				{
					document.hotel.singleroom.value=0;
					document.hotel.doubleroom.value=1;
					document.hotel.extraperson.value=0;
					document.hotel.childwithbed.value=0;
					document.hotel.childwithoutbed.value=1;
					return true;
					
				}
		}
				
	else if((adult==2)&&(child==2))
		{
				//alert(" inside 2 and 2"); 
				if((document.hotel.agechild1.value>6)||(document.hotel.agechild2.value>6))
				{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=1;
				document.hotel.childwithoutbed.value=1;
				return true;
				}
				if((document.hotel.agechild1.value<6)&&(document.hotel.agechild2.value<6))
				{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=0;
				document.hotel.childwithbed.value=0;
				document.hotel.childwithoutbed.value=2;
				return true;
				}
				
		}
	else if((adult==3)&&(child==0))
		{
				//alert(" inside 3 and 0"); 
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=1;
				document.hotel.childwithbed.value=0;
				document.hotel.childwithoutbed.value=0;
				return true;
		}
		else ((adult==3)&&(child==1))
		{	
				//alert(" inside 3 and 1"); 
				if(document.hotel.agechild1.value<6)
				{
				document.hotel.singleroom.value=0;
				document.hotel.doubleroom.value=1;
				document.hotel.extraperson.value=1;
				document.hotel.childwithbed.value=0;
				document.hotel.childwithoutbed.value=1;
				return true;
				}
		}
}


function dosearch()
{
	/*var adult=document.hotel.adult.value;
	//var adult= document.hotel.adult").value;
	var child=document.hotel.child.value;
	//var child=document.hotel.child").value;
	
	if(document.hotel.cityName.value == "1")
	{
		document.hotel.cityName.focus();
		return false;
	}
		
else if((adult==1)&&(child==2)&&(document.hotel.agechild1.value > 6)&&(document.hotel.agechild2.value > 6))
		{	//alert("inside condition");
			//if((document.hotel.agechild1").value > 6)&&(document.hotel.agechild2").value > 6))
			{
	document.getElementById("message").innerHTML="Please note ' A Single Adult, cannot check in with two children ,aged 6 years and above in a Single Room '";
		document.hotel.adult.focus();
			return false;
			}			
		}
	else if((adult==2)&&(child==2)&&(document.hotel.agechild1.value>6)&&(document.hotel.agechild2.value>6))
	{	//alert("inside condition");
		//if((document.hotel.agechild1").value>6)&&(document.hotel.agechild2").value>6))
		{
	document.getElementById("message").innerHTML="Please note '2 Adults cannot check in with two children 6 years and above in a single room'";
	document.hotel.adult.focus();
		return false;
		}
	}
	else if((adult==3)&&(child==2))
		{		
		document.getElementById("message").innerHTML="Please note '3 Adults cannot check in with two children in a single room '";
		document.hotel.adult.focus();
		return false;		
	}
	else if(!dateDiff(document.hotel.Checkout.value,document.hotel.Checkin.value))
	{
		document.hotel.Checkout.value="";
		document.hotel.Checkin.value="";
		document.hotel.Checkin.focus();	
		return false;				
	}
	else
	{
		searching();
		//alert("Adult- "+adult);
		//alert("Child- "+child);
		//alert("singel room- "+document.hotel.singleroom.value);
		//alert("Double room- "+document.hotel.doubleroom.value);
		//alert("Extra person- "+document.hotel.extraperson.value);
		//alert("Child With Bed- "+document.hotel.childwithbed.value);
		//alert("Child Without Bed- "+document.hotel.childwithoutbed.value);
		//alert("inside dosearch");
		document.hotel.submit();	
	}*/
	if(dateDiff(document.getElementById("Checkout").value,document.getElementById("Checkin").value))
	{
		document.hotel.submit();	
	}
	else
	{
		document.getElementById("Checkout").value="";
		document.getElementById("Checkin").value="";
		document.getElementById("Checkin").focus();
		
	}
}

function dateDiff(date1,date2)
{
var mon1=date1.substring(3,5);
var mon2=date2.substring(3,5);

var day1=date1.substring(0,2);
var day2=date2.substring(0,2);

var yer1=date1.substring(6,10);
var yer2=date2.substring(6,10);

var myDate1=new Date();
var myDate2=new Date();
myDate1.setFullYear(yer1,mon1-1,day1);
myDate2.setFullYear(yer2,mon2-1,day2);

	if (myDate2>=myDate1)
		{
			alert("You Can't Take CheckOut Date less Then CheckIn Date");
			return false;
		}
	else
	{
	
		document.getElementById("noofdays").value=(myDate1-myDate2)/(1000*60*60*24);
		//alert(document.getElementById("noofdays").value);
		return true;
		
	}

}