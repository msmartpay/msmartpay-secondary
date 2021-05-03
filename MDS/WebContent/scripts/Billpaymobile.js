function populatecities()
{				
	
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	var city;
	if(myState == "Andhra Pradesh")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +	
		"</select>";
	}
	
	if(myState == "Assam")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Bihar")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
	"</select>";
	}
	if(myState == "Chennai")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Delhi/NCR")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Mobile & Land Line)'>Vodafone (Mobile & Land Line)</OPTION>" +
	
		"</select>";
	}
	if(myState == "Gujarat")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Haryana")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "HimachalPradesh")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Jammu and Kashmir")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Jharkhand")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Karnataka")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Kerala")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Kolkata")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Madhya Pradesh")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Mumbai")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "North East")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Orissa")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Punjab")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Rajasthan")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Rest of Maharashtra and Goa")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Rest of West Bengal")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "Tamil Nadu")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "UP (East)")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	if(myState == "UP (WEST)")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select  ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance (Mobile & Land Line)'>Reliance (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='TATA (Mobile & Land Line)'>TATA (Mobile & Land Line)</OPTION>" +	
	"<OPTION VALUE='Airtel (Mobile & Land Line)'>Airtel (Mobile & Land Line)</OPTION>" +
	"<OPTION VALUE='Vodafone (Land Line)'>Vodafone (Land Line)</OPTION>" +
		"</select>";
	}
	
	
	
	document.getElementById("cityId1").innerHTML = city;	
	populatelocations1();
}
function checkStateSelection1()
{  
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	if(myState.value =="-1")
	{
		alert("Please Select State First");
		myState.focus();
	}
	else
	{
		populatelocations1();
	}
}