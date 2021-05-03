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


function checkAmountValitationFirst(val)
{

var amount=val;

if(amount.replace(/^\s+|\s+$/, '')=="")
{
alert("Please enter cut off Amount");
return false;
}


if (isNaN(amount))
{ 

alert( "- Please enter your quantity as a number.");
return false;
 } 	
if(Number(amount)<0)
{
alert( "- Please enter your quantity as a positive number.");
return false;

}

return true;
}
function checkAmountValitationSecond(val)
{

var amount=val;

if(amount.replace(/^\s+|\s+$/, '')=="")
{
alert("Please enter cut off Limit");
return false;
}


if( parseFloat(amount)!= parseInt(parseFloat(amount))){
    alert ('Decimal value not allowed for cut off Limit');
	 return false;
    }

if (isNaN(amount))
{ 

alert( "- Please enter your quantity as a number in cut off Limit");
return false;
 } 	
if(Number(amount)<0)
{
alert( "- Please enter your quantity as a positive number in cut off Limit");
return false;

}
return true;

}