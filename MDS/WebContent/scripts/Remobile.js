function populatecities()
{				
	
	
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	var city;
	if(myState =="AndhraPradesh")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	
	
		"</select>";
	}
/*
	if(myState== "Others")
	{
		city="<input name='city' class='text' type='text' />";
	}
*/
	

if(myState == "Assam")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
		"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
			"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
			"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
			"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
			"<OPTION VALUE='Aircel'>Agra Drop</OPTION>" +	
			
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
		
		"</select>";
	}
if(myState == "Bihar")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +				
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +	
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
		
		"</select>";
	}
if(myState == "Chennai")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "Delhi/NCR")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	
		"</select>";
	}
if(myState == "Gujarat")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	
		"</select>";
	}
if(myState == "Haryana")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
	
		"</select>";
	}
if(myState == "Himachal Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
	
		"</select>";
	}
if(myState == "Jharkhand")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	
	"</select>";
		
	}
if(myState == "Karnataka")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	

		"</select>";
	}
if(myState == "Kerala")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
		"</select>";
	}
if(myState == "Kolkata")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	"</select>";
	}
if(myState == "Madhya Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	
		"</select>";
	}
if(myState == "Mumbai")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "North East")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "Orissa")
	{
		
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA </OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "Punjab")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "Rajasthan")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +
	
		"</select>";
	}
if(myState == "Rest of Maharashtra and Goa")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "Rest of West Bengal")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
			"<OPTION VALUE='Airtel'>Airtel</OPTION>" +				
			"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
			"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
			"<OPTION VALUE='Aircel'>Aircel</OPTION>" +
			"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +
			"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	          "<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "TamilNadu")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "UP (East)")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
	
		"</select>";
	}
if(myState == "UP (WEST)")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
			"</select>";
	}

	if(myState == "Jammu and Kashmir")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select Operator ----------</OPTION>" +
	
	"<OPTION VALUE='IDEA'>IDEA</OPTION>" +	
	"<OPTION VALUE='Airtel'>Airtel</OPTION>" +	
	"<OPTION VALUE='Vodafone'>Vodafone</OPTION>" +	
	"<OPTION VALUE='Reliance GSM'>Reliance GSM</OPTION>" +	
	"<OPTION VALUE='Aircel'>Aircel</OPTION>" +	
	"<OPTION VALUE='Reliance CDMA'>Reliance CDMA</OPTION>" +	
	"<OPTION VALUE='Tata Indicom'>Tata Indicom</OPTION>" +	
	"<OPTION VALUE='BSNL'>BSNL</OPTION>" +	
			"</select>";
	}


if(myState == "Tripura")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='3877'>Agartala</OPTION>" +	
	"<OPTION VALUE='3878'>Amarpur - Tripura</OPTION>" +	
	"<OPTION VALUE='3879'>Ambassa</OPTION>" +	
	"<OPTION VALUE='3880'>Badharghat</OPTION>" +	
	"<OPTION VALUE='3881'>Belonia</OPTION>" +	
	"<OPTION VALUE='3882'>Dharmanagar</OPTION>" +	
	"<OPTION VALUE='3883'>Gakulnagar</OPTION>" +	
	"<OPTION VALUE='3884'>Gandhigram</OPTION>" +	
	"<OPTION VALUE='3885'>Indranagar</OPTION>" +	
	"<OPTION VALUE='3886'>Jogendranagar</OPTION>" +	
	"<OPTION VALUE='3887'>Kailasahar</OPTION>" +	
	"<OPTION VALUE='3888'>Kamalpur</OPTION>" +	
	"<OPTION VALUE='3889'>Kanchanpur</OPTION>" +	
	"<OPTION VALUE='3890'>Khowai</OPTION>" +	
	"<OPTION VALUE='3891'>Kumarghat</OPTION>" +	
	"<OPTION VALUE='3892'>Kunjaban</OPTION>" +	
	"<OPTION VALUE='3893'>Narsingarh</OPTION>" +	
	"<OPTION VALUE='3894'>Pratapgarh - Tripura</OPTION>" +	
	"<OPTION VALUE='3895'>Ranirbazar</OPTION>" +	
	"<OPTION VALUE='3896'>Sabroom</OPTION>" +	
	"<OPTION VALUE='3897'>Sonamura</OPTION>" +	
	"<OPTION VALUE='3898'>Teliamura</OPTION>" +	
	"<OPTION VALUE='3899'>Udaipur - Tripura</OPTION>" +
		"</select>";
	}
if(myState == "Uttar Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='4386'>Noida</OPTION>" +	
			"<OPTION VALUE='4221'>Kanpur</OPTION>" +
			"<OPTION VALUE='4297'>Lucknow</OPTION>" +				
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='3900'>Achhalda</OPTION>" +	
	"<OPTION VALUE='3901'>Achhnera</OPTION>" +	
	"<OPTION VALUE='3902'>Adari</OPTION>" +	
	"<OPTION VALUE='3903'>Afzalgarh</OPTION>" +	
	"<OPTION VALUE='3904'>Agarwal Mandi</OPTION>" +	
	"<OPTION VALUE='3905'>Agra</OPTION>" +	
	"<OPTION VALUE='3906'>Ahraura</OPTION>" +	
	"<OPTION VALUE='3907'>Ailum</OPTION>" +	
	"<OPTION VALUE='3908'>Air Force Area</OPTION>" +	
	"<OPTION VALUE='3909'>Ajhuwa</OPTION>" +	
	"<OPTION VALUE='3910'>Akbarpur - Ambedaker Nagar</OPTION>" +	
	"<OPTION VALUE='3911'>Akbarpur - Kanpur Dehat</OPTION>" +	
	"<OPTION VALUE='3912'>Aliganj</OPTION>" +	
	"<OPTION VALUE='3913'>Aligarh</OPTION>" +	
	"<OPTION VALUE='3914'>Allahabad</OPTION>" +	
	"<OPTION VALUE='3915'>Allahganj</OPTION>" +	
	"<OPTION VALUE='3916'>Allapur</OPTION>" +	
	"<OPTION VALUE='3917'>Amanpur</OPTION>" +	
	"<OPTION VALUE='3918'>Ambehta</OPTION>" +	
	"<OPTION VALUE='3919'>Amethi</OPTION>" +	
	"<OPTION VALUE='3920'>Amila</OPTION>" +	
	"<OPTION VALUE='3921'>Amilo</OPTION>" +	
	"<OPTION VALUE='3922'>Aminagar Sarai</OPTION>" +	
	"<OPTION VALUE='3923'>Aminagar Urf Bhurbaral</OPTION>" +	
	"<OPTION VALUE='3924'>Amraudha</OPTION>" +	
	"<OPTION VALUE='3925'>Amroha</OPTION>" +	
	"<OPTION VALUE='3926'>Anandnagar</OPTION>" +	
	"<OPTION VALUE='3927'>Anpara</OPTION>" +	
	"<OPTION VALUE='3928'>Antu</OPTION>" +	
	"<OPTION VALUE='3929'>Anupshahr</OPTION>" +	
	"<OPTION VALUE='3930'>Aonla</OPTION>" +	
	"<OPTION VALUE='3931'>Armapur Estate</OPTION>" +	
	"<OPTION VALUE='3932'>Ashrafpur Kichhauchha</OPTION>" +	
	"<OPTION VALUE='3933'>Atarra</OPTION>" +	
	"<OPTION VALUE='3934'>Atasu</OPTION>" +	
	"<OPTION VALUE='3935'>Atrauli</OPTION>" +	
	"<OPTION VALUE='3936'>Atraulia</OPTION>" +	
	"<OPTION VALUE='3937'>Auraiya</OPTION>" +	
	"<OPTION VALUE='3938'>Aurangabad - Bulandshahr</OPTION>" +	
	"<OPTION VALUE='3939'>Aurangabad Bangar</OPTION>" +	
	"<OPTION VALUE='3940'>Auras</OPTION>" +	
	"<OPTION VALUE='3941'>Awagarh</OPTION>" +	
	"<OPTION VALUE='3942'>Ayodhya</OPTION>" +	
	"<OPTION VALUE='3943'>Azamgarh</OPTION>" +	
	"<OPTION VALUE='3944'>Azizpur</OPTION>" +	
	"<OPTION VALUE='3945'>Azmatgarh</OPTION>" +	
	"<OPTION VALUE='3946'>Babarpur Ajitmal</OPTION>" +	
	"<OPTION VALUE='3947'>Baberu</OPTION>" +	
	"<OPTION VALUE='3948'>Babina</OPTION>" +	
	"<OPTION VALUE='3949'>Babrala</OPTION>" +	
	"<OPTION VALUE='3950'>Babugarh</OPTION>" +	
	"<OPTION VALUE='3951'>Bachhraon</OPTION>" +	
	"<OPTION VALUE='3952'>Bachhrawan</OPTION>" +	
	"<OPTION VALUE='3953'>Bad</OPTION>" +	
	"<OPTION VALUE='3954'>Baghpat</OPTION>" +	
	"<OPTION VALUE='3955'>Bah</OPTION>" +	
	"<OPTION VALUE='3956'>Bahadurganj - Ghazipur</OPTION>" +	
	"<OPTION VALUE='3957'>Baheri</OPTION>" +	
	"<OPTION VALUE='3958'>Bahjoi</OPTION>" +	
	"<OPTION VALUE='3959'>Bahraich</OPTION>" +	
	"<OPTION VALUE='3960'>Bahsuma</OPTION>" +	
	"<OPTION VALUE='3961'>Bahuwa</OPTION>" +	
	"<OPTION VALUE='3962'>Bajna</OPTION>" +	
	"<OPTION VALUE='3963'>Bakewar</OPTION>" +	
	"<OPTION VALUE='3964'>Bakiabad</OPTION>" +	
	"<OPTION VALUE='3965'>Baldeo</OPTION>" +	
	"<OPTION VALUE='3966'>Ballia</OPTION>" +	
	"<OPTION VALUE='3967'>Balrampur</OPTION>" +	
	"<OPTION VALUE='3968'>Banat</OPTION>" +	
	"<OPTION VALUE='3969'>Banda</OPTION>" +	
	"<OPTION VALUE='3970'>Bangarmau</OPTION>" +	
	"<OPTION VALUE='3971'>Banki - Barabanki</OPTION>" +	
	"<OPTION VALUE='3972'>Bansdih</OPTION>" +	
	"<OPTION VALUE='3973'>Bansgaon</OPTION>" +	
	"<OPTION VALUE='3974'>Bansi</OPTION>" +	
	"<OPTION VALUE='3975'>Baragaon - Jhansi</OPTION>" +	
	"<OPTION VALUE='3976'>Baragaon - Varanasi</OPTION>" +	
	"<OPTION VALUE='3977'>Baraut</OPTION>" +	
	"<OPTION VALUE='3978'>Bareilly</OPTION>" +	
	"<OPTION VALUE='3979'>Barhalganj</OPTION>" +	
	"<OPTION VALUE='3980'>Barhani Bazar</OPTION>" +	
	"<OPTION VALUE='3981'>Barkhera</OPTION>" +	
	"<OPTION VALUE='3982'>Barsana</OPTION>" +	
	"<OPTION VALUE='3983'>Barua Sagar</OPTION>" +	
	"<OPTION VALUE='3984'>Barwar</OPTION>" +	
	"<OPTION VALUE='3985'>Basti</OPTION>" +	
	"<OPTION VALUE='3986'>Begumabad Budhana</OPTION>" +	
	"<OPTION VALUE='3987'>Behta Hajipur</OPTION>" +	
	"<OPTION VALUE='3988'>Bela Pratapgarh</OPTION>" +	
	"<OPTION VALUE='3989'>Belthara Road</OPTION>" +	
	"<OPTION VALUE='3990'>Beniganj</OPTION>" +	
	"<OPTION VALUE='3991'>Beswan</OPTION>" +	
	"<OPTION VALUE='3992'>Bewar</OPTION>" +	
	"<OPTION VALUE='3993'>Bhadarsa</OPTION>" +	
	"<OPTION VALUE='3994'>Bhadohi</OPTION>" +	
	"<OPTION VALUE='3995'>Bhagwant Nagar</OPTION>" +	
	"<OPTION VALUE='3996'>Bharatganj</OPTION>" +	
	"<OPTION VALUE='3997'>Bhargain</OPTION>" +	
	"<OPTION VALUE='3998'>Bharthana</OPTION>" +	
	"<OPTION VALUE='3999'>Bharuhana</OPTION>" +	
	"<OPTION VALUE='4000'>Bharwari</OPTION>" +	
	"<OPTION VALUE='4001'>Bhatni Bazar</OPTION>" +	
	"<OPTION VALUE='4002'>Bhatpar Rani</OPTION>" +	
	"<OPTION VALUE='4003'>Bhawan Bahadur Nagar</OPTION>" +	
	"<OPTION VALUE='4004'>Bhinga</OPTION>" +	
	"<OPTION VALUE='4005'>Bhogaon</OPTION>" +	
	"<OPTION VALUE='4006'>Bhojpur Dharampur</OPTION>" +	
	"<OPTION VALUE='4007'>Bhokarhedi</OPTION>" +	
	"<OPTION VALUE='4008'>Bhulepur</OPTION>" +	
	"<OPTION VALUE='4009'>Bidhuna</OPTION>" +	
	"<OPTION VALUE='4010'>Bighapur</OPTION>" +	
	"<OPTION VALUE='4011'>Bijnor</OPTION>" +	
	"<OPTION VALUE='4012'>Bijpur</OPTION>" +	
	"<OPTION VALUE='4013'>Bikapur</OPTION>" +	
	"<OPTION VALUE='4014'>Bilari</OPTION>" +	
	"<OPTION VALUE='4015'>Bilariaganj</OPTION>" +	
	"<OPTION VALUE='4016'>Bilaspur - Gautam Buddha Nagar</OPTION>" +	
	"<OPTION VALUE='4017'>Bilaspur - Rampur</OPTION>" +	
	"<OPTION VALUE='4018'>Bilgram</OPTION>" +	
	"<OPTION VALUE='4019'>Bilhaur</OPTION>" +	
	"<OPTION VALUE='4020'>Bilram</OPTION>" +	
	"<OPTION VALUE='4021'>Bilsanda</OPTION>" +	
	"<OPTION VALUE='4022'>Bilsi</OPTION>" +	
	"<OPTION VALUE='4023'>Bindki</OPTION>" +	
	"<OPTION VALUE='4024'>Bisalpur</OPTION>" +	
	"<OPTION VALUE='4025'>Bisanda Buzurg</OPTION>" +	
	"<OPTION VALUE='4026'>Bisauli</OPTION>" +	
	"<OPTION VALUE='4027'>Bisharatganj</OPTION>" +	
	"<OPTION VALUE='4028'>Bisokhar</OPTION>" +	
	"<OPTION VALUE='4029'>Biswan</OPTION>" +	
	"<OPTION VALUE='4030'>Bithoor</OPTION>" +	
	"<OPTION VALUE='4031'>Budaun</OPTION>" +	
	"<OPTION VALUE='4032'>Budhana</OPTION>" +	
	"<OPTION VALUE='4033'>Bugrasi</OPTION>" +	
	"<OPTION VALUE='4034'>Bulandshahr</OPTION>" +	
	"<OPTION VALUE='4035'>Chail</OPTION>" +	
	"<OPTION VALUE='4036'>Chak Imam Ali</OPTION>" +	
	"<OPTION VALUE='4037'>Chakeri</OPTION>" +	
	"<OPTION VALUE='4038'>Chakia - Chandauli</OPTION>" +	
	"<OPTION VALUE='4039'>Chandauli</OPTION>" +	
	"<OPTION VALUE='4040'>Chandausi</OPTION>" +	
	"<OPTION VALUE='4041'>Chandpur - Bijnor</OPTION>" +	
	"<OPTION VALUE='4042'>Charkhari</OPTION>" +	
	"<OPTION VALUE='4043'>Charthaval</OPTION>" +	
	"<OPTION VALUE='4044'>Chaumuhan</OPTION>" +	
	"<OPTION VALUE='4045'>Chhaprauli</OPTION>" +	
	"<OPTION VALUE='4046'>Chharra Rafatpur</OPTION>" +	
	"<OPTION VALUE='4047'>Chhata</OPTION>" +	
	"<OPTION VALUE='4048'>Chhatari</OPTION>" +	
	"<OPTION VALUE='4049'>Chhibramau</OPTION>" +	
	"<OPTION VALUE='4050'>Chhutmalpur</OPTION>" +	
	"<OPTION VALUE='4051'>Chilkana Sultanpur</OPTION>" +	
	"<OPTION VALUE='4052'>Chirgaon</OPTION>" +	
	"<OPTION VALUE='4053'>Chitbara Gaon</OPTION>" +	
	"<OPTION VALUE='4054'>Chitrakoot Dham (Karwi)</OPTION>" +	
	"<OPTION VALUE='4055'>Chopan</OPTION>" +	
	"<OPTION VALUE='4056'>Choubepur Kalan</OPTION>" +	
	"<OPTION VALUE='4057'>Chunar</OPTION>" +	
	"<OPTION VALUE='4058'>Churk Ghurma</OPTION>" +	
	"<OPTION VALUE='4059'>Colonelganj</OPTION>" +	
	"<OPTION VALUE='4060'>Dadri</OPTION>" +	
	"<OPTION VALUE='4061'>Dalmau</OPTION>" +	
	"<OPTION VALUE='4062'>Dankaur</OPTION>" +	
	"<OPTION VALUE='4063'>Dariyabad</OPTION>" +	
	"<OPTION VALUE='4064'>Dasna</OPTION>" +	
	"<OPTION VALUE='4065'>Dataganj</OPTION>" +	
	"<OPTION VALUE='4066'>Daurala</OPTION>" +	
	"<OPTION VALUE='4067'>Dayalbagh</OPTION>" +	
	"<OPTION VALUE='4068'>Deoband</OPTION>" +	
	"<OPTION VALUE='4069'>Deoranian</OPTION>" +	
	"<OPTION VALUE='4070'>Deoria</OPTION>" +	
	"<OPTION VALUE='4071'>Dewa</OPTION>" +	
	"<OPTION VALUE='4072'>Dhampur</OPTION>" +	
	"<OPTION VALUE='4073'>Dhanauha</OPTION>" +	
	"<OPTION VALUE='4074'>Dhanauli</OPTION>" +	
	"<OPTION VALUE='4075'>Dhanaura</OPTION>" +	
	"<OPTION VALUE='4076'>Dharoti Khurd</OPTION>" +	
	"<OPTION VALUE='4077'>Dhaura Tanda</OPTION>" +	
	"<OPTION VALUE='4078'>Dhaurehra</OPTION>" +	
	"<OPTION VALUE='4079'>Dibai</OPTION>" +	
	"<OPTION VALUE='4080'>Dibiyapur</OPTION>" +	
	"<OPTION VALUE='4081'>Dildarnagar Fatehpur Bazar</OPTION>" +	
	"<OPTION VALUE='4082'>Doghat</OPTION>" +	
	"<OPTION VALUE='4083'>Dohrighat</OPTION>" +	
	"<OPTION VALUE='4084'>Dostpur</OPTION>" +	
	"<OPTION VALUE='4085'>Dudhi</OPTION>" +	
	"<OPTION VALUE='4086'>Dulhipur</OPTION>" +	
	"<OPTION VALUE='4087'>Ekdil</OPTION>" +	
	"<OPTION VALUE='4088'>Erich</OPTION>" +	
	"<OPTION VALUE='4089'>Etah</OPTION>" +	
	"<OPTION VALUE='4090'>Etawah</OPTION>" +	
	"<OPTION VALUE='4091'>Etmadpur</OPTION>" +	
	"<OPTION VALUE='4092'>Faizabad</OPTION>" +	
	"<OPTION VALUE='4093'>Faizganj</OPTION>" +	
	"<OPTION VALUE='4094'>Farah</OPTION>" +	
	"<OPTION VALUE='4095'>Faridnagar</OPTION>" +	
	"<OPTION VALUE='4096'>Faridpur</OPTION>" +	
	"<OPTION VALUE='4097'>Fariha</OPTION>" +	
	"<OPTION VALUE='4098'>Farrukhabad-cum-Fatehgarh</OPTION>" +	
	"<OPTION VALUE='4099'>Fatehabad - Agra</OPTION>" +	
	"<OPTION VALUE='4100'>Fatehganj Pashchimi</OPTION>" +	
	"<OPTION VALUE='4101'>Fatehganj Purvi</OPTION>" +	
	"<OPTION VALUE='4102'>Fatehgarh</OPTION>" +	
	"<OPTION VALUE='4103'>Fatehpur - Barabanki</OPTION>" +	
	"<OPTION VALUE='4104'>Fatehpur Chaurasi</OPTION>" +	
	"<OPTION VALUE='4105'>Fatehpur Sikri</OPTION>" +	
	"<OPTION VALUE='4106'>Firozabad</OPTION>" +	
	"<OPTION VALUE='4107'>Gajraula</OPTION>" +	
	"<OPTION VALUE='4108'>Gangaghat</OPTION>" +	
	"<OPTION VALUE='4109'>Gangapur - Varanasi</OPTION>" +	
	"<OPTION VALUE='4110'>Gangoh</OPTION>" +	
	"<OPTION VALUE='4111'>Ganj Dundawara</OPTION>" +	
	"<OPTION VALUE='4112'>Ganj Muradabad</OPTION>" +	
	"<OPTION VALUE='4113'>Garautha</OPTION>" +	
	"<OPTION VALUE='4114'>Garhi Pukhta</OPTION>" +	
	"<OPTION VALUE='4115'>Garhmukteshwar</OPTION>" +	
	"<OPTION VALUE='4116'>Gaura Barhaj</OPTION>" +	
	"<OPTION VALUE='4117'>Gauri Bazar</OPTION>" +	
	"<OPTION VALUE='4118'>Gausganj</OPTION>" +	
	"<OPTION VALUE='4119'>Gawan</OPTION>" +	
	"<OPTION VALUE='4120'>Ghatampur</OPTION>" +	
	"<OPTION VALUE='4121'>Ghaziabad</OPTION>" +	
	"<OPTION VALUE='4122'>Ghazipur</OPTION>" +	
	"<OPTION VALUE='4123'>Ghiraur</OPTION>" +	
	"<OPTION VALUE='4124'>Ghorawal</OPTION>" +	
	"<OPTION VALUE='4125'>Ghosi</OPTION>" +	
	"<OPTION VALUE='4126'>Ghosia Bazar</OPTION>" +	
	"<OPTION VALUE='4127'>Ghughuli</OPTION>" +	
	"<OPTION VALUE='4128'>Gohand</OPTION>" +	
	"<OPTION VALUE='4129'>Gokul</OPTION>" +	
	"<OPTION VALUE='4130'>Gola Bazar</OPTION>" +	
	"<OPTION VALUE='4131'>Gola Gokarannath</OPTION>" +	
	"<OPTION VALUE='4132'>Gonda</OPTION>" +	
	"<OPTION VALUE='4133'>Gopamau</OPTION>" +	
	"<OPTION VALUE='4134'>Gopiganj</OPTION>" +	
	"<OPTION VALUE='4135'>Gorakhpur</OPTION>" +	
	"<OPTION VALUE='4136'>Gosainganj</OPTION>" +	
	"<OPTION VALUE='4137'>Govardhan</OPTION>" +	
	"<OPTION VALUE='4138'>Greater Noida</OPTION>" +	
	"<OPTION VALUE='4139'>Gulaothi</OPTION>" +	
	"<OPTION VALUE='4140'>Gularia Bhindara</OPTION>" +	
	"<OPTION VALUE='4141'>Gulariya</OPTION>" +	
	"<OPTION VALUE='4142'>Gunnaur</OPTION>" +	
	"<OPTION VALUE='4143'>Gursahaiganj</OPTION>" +	
	"<OPTION VALUE='4144'>Gursarai</OPTION>" +	
	"<OPTION VALUE='4145'>Gyanpur</OPTION>" +	
	"<OPTION VALUE='4146'>Hafizpur</OPTION>" +	
	"<OPTION VALUE='4147'>Haidergarh</OPTION>" +	
	"<OPTION VALUE='4148'>Haldaur</OPTION>" +	
	"<OPTION VALUE='4149'>Hamirpur</OPTION>" +	
	"<OPTION VALUE='4150'>Handia</OPTION>" +	
	"<OPTION VALUE='4151'>Hapur</OPTION>" +	
	"<OPTION VALUE='4152'>Hardoi</OPTION>" +	
	"<OPTION VALUE='4153'>Harduaganj</OPTION>" +	
	"<OPTION VALUE='4154'>Hargaon</OPTION>" +	
	"<OPTION VALUE='4155'>Hariharpur</OPTION>" +	
	"<OPTION VALUE='4156'>Harraiya</OPTION>" +	
	"<OPTION VALUE='4157'>Hasanpur</OPTION>" +	
	"<OPTION VALUE='4158'>Hasayan</OPTION>" +	
	"<OPTION VALUE='4159'>Hastinapur</OPTION>" +	
	"<OPTION VALUE='4160'>Hata</OPTION>" +	
	"<OPTION VALUE='4161'>Hathras</OPTION>" +	
	"<OPTION VALUE='4162'>Hyderabad - Unnao</OPTION>" +	
	"<OPTION VALUE='4163'>Ibrahimpur</OPTION>" +	
	"<OPTION VALUE='4164'>Iglas</OPTION>" +	
	"<OPTION VALUE='4165'>Ikauna</OPTION>" +	
	"<OPTION VALUE='4166'>Iltifatganj Bazar</OPTION>" +	
	"<OPTION VALUE='4167'>Indian Telephone Industry, Mankapur (Sp. Village)</OPTION>" +	
	"<OPTION VALUE='4168'>Islamnagar</OPTION>" +	
	"<OPTION VALUE='4169'>Jafarabad</OPTION>" +	
	"<OPTION VALUE='4170'>Jagner</OPTION>" +	
	"<OPTION VALUE='4171'>Jahanabad</OPTION>" +	
	"<OPTION VALUE='4172'>Jahangirabad</OPTION>" +	
	"<OPTION VALUE='4173'>Jahangirpur</OPTION>" +	
	"<OPTION VALUE='4174'>Jais</OPTION>" +	
	"<OPTION VALUE='4175'>Jaithara</OPTION>" +	
	"<OPTION VALUE='4176'>Jalalabad - Bijnor</OPTION>" +	
	"<OPTION VALUE='4177'>Jalalabad - Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='4178'>Jalalabad - Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='4179'>Jalali</OPTION>" +	
	"<OPTION VALUE='4180'>Jalalpur</OPTION>" +	
	"<OPTION VALUE='4181'>Jalaun</OPTION>" +	
	"<OPTION VALUE='4182'>Jalesar</OPTION>" +	
	"<OPTION VALUE='4183'>Jamshila</OPTION>" +	
	"<OPTION VALUE='4184'>Jangipur - Ghazipur</OPTION>" +	
	"<OPTION VALUE='4185'>Jansath</OPTION>" +	
	"<OPTION VALUE='4186'>Jarwal</OPTION>" +	
	"<OPTION VALUE='4187'>Jasrana</OPTION>" +	
	"<OPTION VALUE='4188'>Jaswantnagar</OPTION>" +	
	"<OPTION VALUE='4189'>Jatari</OPTION>" +	
	"<OPTION VALUE='4190'>Jaunpur</OPTION>" +	
	"<OPTION VALUE='4191'>Jewar</OPTION>" +	
	"<OPTION VALUE='4192'>Jhalu</OPTION>" +	
	"<OPTION VALUE='4193'>Jhansi</OPTION>" +	
	"<OPTION VALUE='4194'>Jhansi Rly. Settlement</OPTION>" +	
	"<OPTION VALUE='4195'>Jhinjhak</OPTION>" +	
	"<OPTION VALUE='4196'>Jhinjhana</OPTION>" +	
	"<OPTION VALUE='4197'>Jhusi</OPTION>" +	
	"<OPTION VALUE='4198'>Jhusi Kohna</OPTION>" +	
	"<OPTION VALUE='4199'>Jiyanpur</OPTION>" +	
	"<OPTION VALUE='4200'>Joya</OPTION>" +	
	"<OPTION VALUE='4201'>Jyoti Khuria</OPTION>" +	
	"<OPTION VALUE='4202'>Kabrai</OPTION>" +	
	"<OPTION VALUE='4203'>Kachhauna Patseni</OPTION>" +	
	"<OPTION VALUE='4204'>Kachhla</OPTION>" +	
	"<OPTION VALUE='4205'>Kachhwa</OPTION>" +	
	"<OPTION VALUE='4206'>Kadaura</OPTION>" +	
	"<OPTION VALUE='4207'>Kadipur</OPTION>" +	
	"<OPTION VALUE='4208'>Kailashpur</OPTION>" +	
	"<OPTION VALUE='4209'>Kaimganj</OPTION>" +	
	"<OPTION VALUE='4210'>Kairana</OPTION>" +	
	"<OPTION VALUE='4211'>Kakgaina</OPTION>" +	
	"<OPTION VALUE='4212'>Kakod</OPTION>" +	
	"<OPTION VALUE='4213'>Kakrala</OPTION>" +	
	"<OPTION VALUE='4214'>Kalinagar</OPTION>" +	
	"<OPTION VALUE='4215'>Kalpi</OPTION>" +	
	"<OPTION VALUE='4216'>Kamalganj</OPTION>" +	
	"<OPTION VALUE='4217'>Kampil</OPTION>" +	
	"<OPTION VALUE='4218'>Kandhla</OPTION>" +	
	"<OPTION VALUE='4219'>Kandwa</OPTION>" +	
	"<OPTION VALUE='4220'>Kannauj</OPTION>" +	
	"<OPTION VALUE='4221'>Kanpur</OPTION>" +	
	"<OPTION VALUE='4222'>Kanth - Moradabad</OPTION>" +	
	"<OPTION VALUE='4223'>Kanth - Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='4224'>Kaptanganj</OPTION>" +	
	"<OPTION VALUE='4225'>Karari</OPTION>" +	
	"<OPTION VALUE='4226'>Karhal</OPTION>" +	
	"<OPTION VALUE='4227'>Karnawal</OPTION>" +	
	"<OPTION VALUE='4228'>Kasganj</OPTION>" +	
	"<OPTION VALUE='4229'>Katariya</OPTION>" +	
	"<OPTION VALUE='4230'>Katghar Lalganj</OPTION>" +	
	"<OPTION VALUE='4231'>Kathera</OPTION>" +	
	"<OPTION VALUE='4232'>Katra - Gonda</OPTION>" +	
	"<OPTION VALUE='4233'>Katra - Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='4234'>Katra Medniganj</OPTION>" +	
	"<OPTION VALUE='4235'>Kauriaganj</OPTION>" +	
	"<OPTION VALUE='4236'>Kemri</OPTION>" +	
	"<OPTION VALUE='4237'>Kerakat</OPTION>" +	
	"<OPTION VALUE='4238'>Khadda</OPTION>" +	
	"<OPTION VALUE='4239'>Khaga</OPTION>" +	
	"<OPTION VALUE='4240'>Khailar</OPTION>" +	
	"<OPTION VALUE='4241'>Khair</OPTION>" +	
	"<OPTION VALUE='4242'>Khairabad - Mau</OPTION>" +	
	"<OPTION VALUE='4243'>Khairabad - Sitapur</OPTION>" +	
	"<OPTION VALUE='4244'>Khalilabad</OPTION>" +	
	"<OPTION VALUE='4245'>Khamaria</OPTION>" +	
	"<OPTION VALUE='4246'>Khanpur</OPTION>" +	
	"<OPTION VALUE='4247'>Kharela</OPTION>" +	
	"<OPTION VALUE='4248'>Khargupur</OPTION>" +	
	"<OPTION VALUE='4249'>Khariya</OPTION>" +	
	"<OPTION VALUE='4250'>Kharkhoda - Meerut</OPTION>" +	
	"<OPTION VALUE='4251'>Khatauli</OPTION>" +	
	"<OPTION VALUE='4252'>Khatauli Rural</OPTION>" +	
	"<OPTION VALUE='4253'>Khekada</OPTION>" +	
	"<OPTION VALUE='4254'>Kheragarh</OPTION>" +	
	"<OPTION VALUE='4255'>Kheri</OPTION>" +	
	"<OPTION VALUE='4256'>Kheta Sarai</OPTION>" +	
	"<OPTION VALUE='4257'>Khudaganj</OPTION>" +	
	"<OPTION VALUE='4258'>Khurja</OPTION>" +	
	"<OPTION VALUE='4259'>Khutar</OPTION>" +	
	"<OPTION VALUE='4260'>Kiraoli</OPTION>" +	
	"<OPTION VALUE='4261'>Kiratpur</OPTION>" +	
	"<OPTION VALUE='4262'>Kishni</OPTION>" +	
	"<OPTION VALUE='4263'>Kishunpur</OPTION>" +	
	"<OPTION VALUE='4264'>Kithaur</OPTION>" +	
	"<OPTION VALUE='4265'>Koeripur</OPTION>" +	
	"<OPTION VALUE='4266'>Konch</OPTION>" +	
	"<OPTION VALUE='4267'>Kopaganj</OPTION>" +	
	"<OPTION VALUE='4268'>Kora Jahanabad</OPTION>" +	
	"<OPTION VALUE='4269'>Koraon</OPTION>" +	
	"<OPTION VALUE='4270'>Korwa</OPTION>" +	
	"<OPTION VALUE='4271'>Kosi Kalan</OPTION>" +	
	"<OPTION VALUE='4272'>Kota - Sonbhadra</OPTION>" +	
	"<OPTION VALUE='4273'>Kotra</OPTION>" +	
	"<OPTION VALUE='4274'>Kotwa</OPTION>" +	
	"<OPTION VALUE='4275'>Kul Pahar</OPTION>" +	
	"<OPTION VALUE='4276'>Kunda</OPTION>" +	
	"<OPTION VALUE='4277'>Kundarki</OPTION>" +	
	"<OPTION VALUE='4278'>Kunwargaon</OPTION>" +	
	"<OPTION VALUE='4279'>Kuraoli</OPTION>" +	
	"<OPTION VALUE='4280'>Kurara</OPTION>" +	
	"<OPTION VALUE='4281'>Kursath - Hardoi</OPTION>" +	
	"<OPTION VALUE='4282'>Kursath - Unnao</OPTION>" +	
	"<OPTION VALUE='4283'>Kurthi Jafarpur</OPTION>" +	
	"<OPTION VALUE='4284'>Kushinagar</OPTION>" +	
	"<OPTION VALUE='4285'>Kusmara</OPTION>" +	
	"<OPTION VALUE='4286'>Laharpur</OPTION>" +	
	"<OPTION VALUE='4287'>Lakhimpur</OPTION>" +	
	"<OPTION VALUE='4288'>Lakhna</OPTION>" +	
	"<OPTION VALUE='4289'>Lal Gopalganj Nindaura</OPTION>" +	
	"<OPTION VALUE='4290'>Lalganj - Rae Bareli</OPTION>" +	
	"<OPTION VALUE='4291'>Lalitpur</OPTION>" +	
	"<OPTION VALUE='4292'>Lar</OPTION>" +	
	"<OPTION VALUE='4293'>Lawar NP</OPTION>" +	
	"<OPTION VALUE='4294'>Ledwa Mahua</OPTION>" +	
	"<OPTION VALUE='4295'>Lohta</OPTION>" +	
	"<OPTION VALUE='4296'>Loni</OPTION>" +	
	"<OPTION VALUE='4297'>Lucknow</OPTION>" +	
	"<OPTION VALUE='4298'>Machhlishahr</OPTION>" +	
	"<OPTION VALUE='4299'>Madhoganj</OPTION>" +	
	"<OPTION VALUE='4300'>Madhogarh</OPTION>" +	
	"<OPTION VALUE='4301'>Maghar</OPTION>" +	
	"<OPTION VALUE='4302'>Mahaban</OPTION>" +	
	"<OPTION VALUE='4303'>Mahmudabad</OPTION>" +	
	"<OPTION VALUE='4304'>Mahoba</OPTION>" +	
	"<OPTION VALUE='4305'>Maholi</OPTION>" +	
	"<OPTION VALUE='4306'>Mahrajganj - Azamgarh</OPTION>" +	
	"<OPTION VALUE='4307'>Mahrajganj - Maharajganj</OPTION>" +	
	"<OPTION VALUE='4308'>Mahrajganj - Rae Bareli</OPTION>" +	
	"<OPTION VALUE='4309'>Mahroni</OPTION>" +	
	"<OPTION VALUE='4310'>Mailani</OPTION>" +	
	"<OPTION VALUE='4311'>Mainpuri</OPTION>" +	
	"<OPTION VALUE='4312'>Majhara Pipar Ehatmali</OPTION>" +	
	"<OPTION VALUE='4313'>Majhauli Raj</OPTION>" +	
	"<OPTION VALUE='4314'>Mallawan</OPTION>" +	
	"<OPTION VALUE='4315'>Mandawar - Bijnor</OPTION>" +	
	"<OPTION VALUE='4316'>Manikpur - Pratapgarh</OPTION>" +	
	"<OPTION VALUE='4317'>Manikpur Sarhat</OPTION>" +	
	"<OPTION VALUE='4318'>Maniyar</OPTION>" +	
	"<OPTION VALUE='4319'>Manjhanpur</OPTION>" +	
	"<OPTION VALUE='4320'>Mankapur</OPTION>" +	
	"<OPTION VALUE='4321'>Marehra</OPTION>" +	
	"<OPTION VALUE='4322'>Mariahu</OPTION>" +	
	"<OPTION VALUE='4323'>Maruadih Rly. Settlement</OPTION>" +	
	"<OPTION VALUE='4324'>Maswasi</OPTION>" +	
	"<OPTION VALUE='4325'>Mataundh</OPTION>" +	
	"<OPTION VALUE='4326'>Mathura</OPTION>" +	
	"<OPTION VALUE='4327'>Mau Aima</OPTION>" +	
	"<OPTION VALUE='4328'>Maudaha</OPTION>" +	
	"<OPTION VALUE='4329'>Maunath Bhanjan</OPTION>" +	
	"<OPTION VALUE='4330'>Mauranipur</OPTION>" +	
	"<OPTION VALUE='4331'>Maurawan</OPTION>" +	
	"<OPTION VALUE='4332'>Mawana</OPTION>" +	
	"<OPTION VALUE='4333'>Meerut</OPTION>" +	
	"<OPTION VALUE='4334'>Mehdawal</OPTION>" +	
	"<OPTION VALUE='4335'>Mehnagar</OPTION>" +	
	"<OPTION VALUE='4336'>Mendu</OPTION>" +	
	"<OPTION VALUE='4337'>Milak</OPTION>" +	
	"<OPTION VALUE='4338'>Miranpur</OPTION>" +	
	"<OPTION VALUE='4339'>Mirganj - Bareilly</OPTION>" +	
	"<OPTION VALUE='4340'>Mirzapur-cum-Vindhyachal</OPTION>" +	
	"<OPTION VALUE='4341'>Misrikh-cum-Neemsar</OPTION>" +	
	"<OPTION VALUE='4342'>Modinagar</OPTION>" +	
	"<OPTION VALUE='4343'>Mogra Badshahpur</OPTION>" +	
	"<OPTION VALUE='4344'>Mohammadabad - Farrukhabad</OPTION>" +	
	"<OPTION VALUE='4345'>Mohammadabad - Ghazipur</OPTION>" +	
	"<OPTION VALUE='4346'>Mohammadi</OPTION>" +	
	"<OPTION VALUE='4347'>Mohan</OPTION>" +	
	"<OPTION VALUE='4348'>Mohanpur</OPTION>" +	
	"<OPTION VALUE='4349'>Mohiuddinpur</OPTION>" +	
	"<OPTION VALUE='4350'>Moradabad</OPTION>" +	
	"<OPTION VALUE='4351'>Moth</OPTION>" +	
	"<OPTION VALUE='4352'>Mubarakpur</OPTION>" +	
	"<OPTION VALUE='4353'>Mughalsarai</OPTION>" +	
	"<OPTION VALUE='4354'>Mughalsarai Rly. Settlement</OPTION>" +	
	"<OPTION VALUE='4355'>Muhammadabad</OPTION>" +	
	"<OPTION VALUE='4356'>Mukrampur Khema</OPTION>" +	
	"<OPTION VALUE='4357'>Mundera Bazar</OPTION>" +	
	"<OPTION VALUE='4358'>Mundia</OPTION>" +	
	"<OPTION VALUE='4359'>Muradnagar</OPTION>" +	
	"<OPTION VALUE='4360'>Mursan</OPTION>" +	
	"<OPTION VALUE='4361'>Musafirkhana</OPTION>" +	
	"<OPTION VALUE='4362'>Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='4363'>Nadigaon</OPTION>" +	
	"<OPTION VALUE='4364'>Nagina</OPTION>" +	
	"<OPTION VALUE='4365'>Nai Bazar</OPTION>" +	
	"<OPTION VALUE='4366'>Nainana Jat</OPTION>" +	
	"<OPTION VALUE='4367'>Najibabad</OPTION>" +	
	"<OPTION VALUE='4368'>Nakur</OPTION>" +	
	"<OPTION VALUE='4369'>Nanauta</OPTION>" +	
	"<OPTION VALUE='4370'>Nandgaon - Mathura</OPTION>" +	
	"<OPTION VALUE='4371'>Nanpara</OPTION>" +	
	"<OPTION VALUE='4372'>Naraini</OPTION>" +	
	"<OPTION VALUE='4373'>Narauli</OPTION>" +	
	"<OPTION VALUE='4374'>Naraura</OPTION>" +	
	"<OPTION VALUE='4375'>Naugawan Sadat</OPTION>" +	
	"<OPTION VALUE='4376'>Nautanwa</OPTION>" +	
	"<OPTION VALUE='4377'>Nawabganj - Barabanki</OPTION>" +	
	"<OPTION VALUE='4378'>Nawabganj - Bareilly</OPTION>" +	
	"<OPTION VALUE='4379'>Nawabganj - Gonda</OPTION>" +	
	"<OPTION VALUE='4380'>Nawabganj - Unnao</OPTION>" +	
	"<OPTION VALUE='4381'>Nehtaur</OPTION>" +	
	"<OPTION VALUE='4382'>Nichlaul</OPTION>" +	
	"<OPTION VALUE='4383'>Nidhauli Kalan</OPTION>" +	
	"<OPTION VALUE='4384'>Niwari - Ghaziabad</OPTION>" +	
	"<OPTION VALUE='4385'>Nizamabad - Azamgarh</OPTION>" +	
	"<OPTION VALUE='4386'>Noida</OPTION>" +	
	"<OPTION VALUE='4387'>Noorpur</OPTION>" +	
	"<OPTION VALUE='4388'>Northern Rly. Colony</OPTION>" +	
	"<OPTION VALUE='4389'>Nyoria Husainpur</OPTION>" +	
	"<OPTION VALUE='4390'>Nyotini</OPTION>" +	
	"<OPTION VALUE='4391'>Obra</OPTION>" +	
	"<OPTION VALUE='4392'>Oel Dhakwa</OPTION>" +	
	"<OPTION VALUE='4393'>Orai</OPTION>" +	
	"<OPTION VALUE='4394'>Oran</OPTION>" +	
	"<OPTION VALUE='4395'>Ordnance Factory Muradnagar</OPTION>" +	
	"<OPTION VALUE='4396'>Pachperwa</OPTION>" +	
	"<OPTION VALUE='4397'>Padrauna</OPTION>" +	
	"<OPTION VALUE='4398'>Pahasu</OPTION>" +	
	"<OPTION VALUE='4399'>Paintepur</OPTION>" +	
	"<OPTION VALUE='4400'>Pali - Hardoi</OPTION>" +	
	"<OPTION VALUE='4401'>Pali - Lalitpur</OPTION>" +	
	"<OPTION VALUE='4402'>Palia Kalan</OPTION>" +	
	"<OPTION VALUE='4403'>Parasi</OPTION>" +	
	"<OPTION VALUE='4404'>Parichha</OPTION>" +	
	"<OPTION VALUE='4405'>Parikshitgarh</OPTION>" +	
	"<OPTION VALUE='4406'>Parsadepur</OPTION>" +	
	"<OPTION VALUE='4407'>Patala</OPTION>" +	
	"<OPTION VALUE='4408'>Patiyali</OPTION>" +	
	"<OPTION VALUE='4409'>Patti - Pratapgarh</OPTION>" +	
	"<OPTION VALUE='4410'>Phalauda</OPTION>" +	
	"<OPTION VALUE='4411'>Phaphund</OPTION>" +	
	"<OPTION VALUE='4412'>Phulpur - Allahabad</OPTION>" +	
	"<OPTION VALUE='4413'>Phulpur - Azamgarh</OPTION>" +	
	"<OPTION VALUE='4414'>Phulwaria</OPTION>" +	
	"<OPTION VALUE='4415'>Pihani</OPTION>" +	
	"<OPTION VALUE='4416'>Pilibhit</OPTION>" +	
	"<OPTION VALUE='4417'>Pilkhana</OPTION>" +	
	"<OPTION VALUE='4418'>Pilkhuwa</OPTION>" +	
	"<OPTION VALUE='4419'>Pinahat</OPTION>" +	
	"<OPTION VALUE='4420'>Pipalsana Chaudhari</OPTION>" +	
	"<OPTION VALUE='4421'>Pipiganj</OPTION>" +	
	"<OPTION VALUE='4422'>Pipraich</OPTION>" +	
	"<OPTION VALUE='4423'>Pipri</OPTION>" +	
	"<OPTION VALUE='4424'>Powayan</OPTION>" +	
	"<OPTION VALUE='4425'>Pratapgarh City</OPTION>" +	
	"<OPTION VALUE='4426'>Pukhrayan</OPTION>" +	
	"<OPTION VALUE='4427'>Puranpur</OPTION>" +	
	"<OPTION VALUE='4428'>Purdilnagar</OPTION>" +	
	"<OPTION VALUE='4429'>Purquazi</OPTION>" +	
	"<OPTION VALUE='4430'>Purwa</OPTION>" +	
	"<OPTION VALUE='4431'>Qasimpur Power House Colony</OPTION>" +	
	"<OPTION VALUE='4432'>Rabupura</OPTION>" +	
	"<OPTION VALUE='4433'>Radhakund</OPTION>" +	
	"<OPTION VALUE='4434'>Rae Bareli</OPTION>" +	
	"<OPTION VALUE='4435'>Raja Ka Rampur</OPTION>" +	
	"<OPTION VALUE='4436'>Rajapur - Chitrakoot</OPTION>" +	
	"<OPTION VALUE='4437'>Ramkola</OPTION>" +	
	"<OPTION VALUE='4438'>Ramnagar - Barabanki</OPTION>" +	
	"<OPTION VALUE='4439'>Ramnagar - Varanasi</OPTION>" +	
	"<OPTION VALUE='4440'>Rampur</OPTION>" +	
	"<OPTION VALUE='4441'>Rampur Bhawanipur</OPTION>" +	
	"<OPTION VALUE='4442'>Rampur Karkhana</OPTION>" +	
	"<OPTION VALUE='4443'>Rampur Maniharan</OPTION>" +	
	"<OPTION VALUE='4444'>Rampura - Jalaun</OPTION>" +	
	"<OPTION VALUE='4445'>Ranipur</OPTION>" +	
	"<OPTION VALUE='4446'>Rashidpur Garhi</OPTION>" +	
	"<OPTION VALUE='4447'>Rasra</OPTION>" +	
	"<OPTION VALUE='4448'>Rasulabad</OPTION>" +	
	"<OPTION VALUE='4449'>Rath</OPTION>" +	
	"<OPTION VALUE='4450'>Raya</OPTION>" +	
	"<OPTION VALUE='4451'>Renukoot</OPTION>" +	
	"<OPTION VALUE='4452'>Reoti</OPTION>" +	
	"<OPTION VALUE='4453'>Richha</OPTION>" +	
	"<OPTION VALUE='4454'>Risia Bazar</OPTION>" +	
	"<OPTION VALUE='4455'>Rithora</OPTION>" +	
	"<OPTION VALUE='4456'>Rly. Settlement Roza</OPTION>" +	
	"<OPTION VALUE='4457'>Robertsganj</OPTION>" +	
	"<OPTION VALUE='4458'>Rudauli</OPTION>" +	
	"<OPTION VALUE='4459'>Rudayan</OPTION>" +	
	"<OPTION VALUE='4460'>Rudrapur - Deoria</OPTION>" +	
	"<OPTION VALUE='4461'>Rura</OPTION>" +	
	"<OPTION VALUE='4462'>Rustamnagar Sahaspur</OPTION>" +	
	"<OPTION VALUE='4463'>Sadabad</OPTION>" +	
	"<OPTION VALUE='4464'>Sadat</OPTION>" +	
	"<OPTION VALUE='4465'>Safipur</OPTION>" +	
	"<OPTION VALUE='4466'>Sahanpur</OPTION>" +	
	"<OPTION VALUE='4467'>Saharanpur</OPTION>" +	
	"<OPTION VALUE='4468'>Sahaspur</OPTION>" +	
	"<OPTION VALUE='4469'>Sahaswan</OPTION>" +	
	"<OPTION VALUE='4470'>Sahatwar</OPTION>" +	
	"<OPTION VALUE='4471'>Sahawar</OPTION>" +	
	"<OPTION VALUE='4472'>Sahjanwa</OPTION>" +	
	"<OPTION VALUE='4473'>Sahpau NP</OPTION>" +	
	"<OPTION VALUE='4474'>Saidpur - Budaun</OPTION>" +	
	"<OPTION VALUE='4475'>Saidpur - Ghazipur</OPTION>" +	
	"<OPTION VALUE='4476'>Sainthal</OPTION>" +	
	"<OPTION VALUE='4477'>Saiyad Raja</OPTION>" +	
	"<OPTION VALUE='4478'>Sakhanu</OPTION>" +	
	"<OPTION VALUE='4479'>Sakit</OPTION>" +	
	"<OPTION VALUE='4480'>Salarpur Khadar</OPTION>" +	
	"<OPTION VALUE='4481'>Salempur</OPTION>" +	
	"<OPTION VALUE='4482'>Salon</OPTION>" +	
	"<OPTION VALUE='4483'>Sambhal</OPTION>" +	
	"<OPTION VALUE='4484'>Samdhan</OPTION>" +	
	"<OPTION VALUE='4485'>Samthar</OPTION>" +	
	"<OPTION VALUE='4486'>Sandi</OPTION>" +	
	"<OPTION VALUE='4487'>Sandila</OPTION>" +	
	"<OPTION VALUE='4488'>Sarai Aquil</OPTION>" +	
	"<OPTION VALUE='4489'>Sarai Mir</OPTION>" +	
	"<OPTION VALUE='4490'>Sardhana</OPTION>" +	
	"<OPTION VALUE='4491'>Sarila</OPTION>" +	
	"<OPTION VALUE='4492'>Sarsawan</OPTION>" +	
	"<OPTION VALUE='4493'>Sasni</OPTION>" +	
	"<OPTION VALUE='4494'>Satrikh</OPTION>" +	
	"<OPTION VALUE='4495'>Saunkh</OPTION>" +	
	"<OPTION VALUE='4496'>Saurikh</OPTION>" +	
	"<OPTION VALUE='4497'>Seohara</OPTION>" +	
	"<OPTION VALUE='4498'>Sewalkhas</OPTION>" +	
	"<OPTION VALUE='4499'>Sewarhi</OPTION>" +	
	"<OPTION VALUE='4500'>Shahabad - Hardoi</OPTION>" +	
	"<OPTION VALUE='4501'>Shahabad - Rampur</OPTION>" +	
	"<OPTION VALUE='4502'>Shahganj</OPTION>" +	
	"<OPTION VALUE='4503'>Shahi</OPTION>" +	
	"<OPTION VALUE='4504'>Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='4505'>Shahpur - Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='4506'>Shamli</OPTION>" +	
	"<OPTION VALUE='4507'>Shamsabad - Agra</OPTION>" +	
	"<OPTION VALUE='4508'>Shamsabad - Farrukhabad</OPTION>" +	
	"<OPTION VALUE='4509'>Shankargarh</OPTION>" +	
	"<OPTION VALUE='4510'>Shergarh</OPTION>" +	
	"<OPTION VALUE='4511'>Sherkot</OPTION>" +	
	"<OPTION VALUE='4512'>Shikarpur - Bulandshahr</OPTION>" +	
	"<OPTION VALUE='4513'>Shikohabad</OPTION>" +	
	"<OPTION VALUE='4514'>Shishgarh</OPTION>" +	
	"<OPTION VALUE='4515'>Shivdaspur</OPTION>" +	
	"<OPTION VALUE='4516'>Shivli</OPTION>" +	
	"<OPTION VALUE='4517'>Shivrajpur</OPTION>" +	
	"<OPTION VALUE='4518'>Shohratgarh</OPTION>" +	
	"<OPTION VALUE='4519'>Siana</OPTION>" +	
	"<OPTION VALUE='4520'>Siddhaur</OPTION>" +	
	"<OPTION VALUE='4521'>Sidhauli</OPTION>" +	
	"<OPTION VALUE='4522'>Sidhpura</OPTION>" +	
	"<OPTION VALUE='4523'>Sikanderpur - Ballia</OPTION>" +	
	"<OPTION VALUE='4524'>Sikanderpur - Kannauj</OPTION>" +	
	"<OPTION VALUE='4525'>Sikandra</OPTION>" +	
	"<OPTION VALUE='4526'>Sikandra Rao</OPTION>" +	
	"<OPTION VALUE='4527'>Sikandrabad</OPTION>" +	
	"<OPTION VALUE='4528'>Singahi Bhiraura</OPTION>" +	
	"<OPTION VALUE='4529'>Sirathu</OPTION>" +	
	"<OPTION VALUE='4530'>Sirauli</OPTION>" +	
	"<OPTION VALUE='4531'>Sirsa - Allahabad</OPTION>" +	
	"<OPTION VALUE='4532'>Sirsaganj</OPTION>" +	
	"<OPTION VALUE='4533'>Sirsi - Moradabad</OPTION>" +	
	"<OPTION VALUE='4534'>Sisauli</OPTION>" +	
	"<OPTION VALUE='4535'>Siswa Bazar</OPTION>" +	
	"<OPTION VALUE='4536'>Sitapur</OPTION>" +	
	"<OPTION VALUE='4537'>Som</OPTION>" +	
	"<OPTION VALUE='4538'>Soron</OPTION>" +	
	"<OPTION VALUE='4539'>Suar</OPTION>" +	
	"<OPTION VALUE='4540'>Sukhmalpur Nizamabad</OPTION>" +	
	"<OPTION VALUE='4541'>Sultanpur - Sultanpur</OPTION>" +	
	"<OPTION VALUE='4542'>Sumerpur - Hamirpur</OPTION>" +	
	"<OPTION VALUE='4543'>Suriyawan</OPTION>" +	
	"<OPTION VALUE='4544'>Swamibagh</OPTION>" +	
	"<OPTION VALUE='4545'>Talbehat</OPTION>" +	
	"<OPTION VALUE='4546'>Talgram</OPTION>" +	
	"<OPTION VALUE='4547'>Tambaur-cum-Ahmedabad</OPTION>" +	
	"<OPTION VALUE='4548'>Tanda - Ambedaker Nagar</OPTION>" +	
	"<OPTION VALUE='4549'>Tanda - Rampur</OPTION>" +	
	"<OPTION VALUE='4550'>Tatarpur Lallu</OPTION>" +	
	"<OPTION VALUE='4551'>Tetri Bazar</OPTION>" +	
	"<OPTION VALUE='4552'>Thakurdwara</OPTION>" +	
	"<OPTION VALUE='4553'>Thana Bhawan</OPTION>" +	
	"<OPTION VALUE='4554'>Thiriya Nizamat Khan</OPTION>" +	
	"<OPTION VALUE='4555'>Tikait Nagar</OPTION>" +	
	"<OPTION VALUE='4556'>Tikri</OPTION>" +	
	"<OPTION VALUE='4557'>Tilhar</OPTION>" +	
	"<OPTION VALUE='4558'>Tindwari</OPTION>" +	
	"<OPTION VALUE='4559'>Tirwaganj</OPTION>" +	
	"<OPTION VALUE='4560'>Titron</OPTION>" +	
	"<OPTION VALUE='4561'>Tondi Fatehpur</OPTION>" +	
	"<OPTION VALUE='4562'>Tulsipur</OPTION>" +	
	"<OPTION VALUE='4563'>Tundla</OPTION>" +	
	"<OPTION VALUE='4564'>Tundla Kham</OPTION>" +	
	"<OPTION VALUE='4565'>Tundla Rly. Colony</OPTION>" +	
	"<OPTION VALUE='4566'>Ugu</OPTION>" +	
	"<OPTION VALUE='4567'>Ujhani</OPTION>" +	
	"<OPTION VALUE='4568'>Ujhari</OPTION>" +	
	"<OPTION VALUE='4569'>Umri</OPTION>" +	
	"<OPTION VALUE='4570'>Umri Kalan</OPTION>" +	
	"<OPTION VALUE='4571'>Un - Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='4572'>Unchahar</OPTION>" +	
	"<OPTION VALUE='4573'>Unnao</OPTION>" +	
	"<OPTION VALUE='4574'>Usawan</OPTION>" +	
	"<OPTION VALUE='4575'>Usehat</OPTION>" +	
	"<OPTION VALUE='4576'>Utraula</OPTION>" +	
	"<OPTION VALUE='4577'>Varanasi</OPTION>" +	
	"<OPTION VALUE='4578'>Vijaigarh</OPTION>" +	
	"<OPTION VALUE='4579'>Vrindavan</OPTION>" +	
	"<OPTION VALUE='4580'>Warhapur</OPTION>" +	
	"<OPTION VALUE='4581'>Wazirganj</OPTION>" +	
	"<OPTION VALUE='4582'>Zaidpur</OPTION>" +	
	"<OPTION VALUE='4583'>Zamania</OPTION>" +
		"</select>";
	}
if(myState == "Uttaranchal")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='4584'>Almora</OPTION>" +	
	"<OPTION VALUE='4585'>Badrinathpuri</OPTION>" +	
	"<OPTION VALUE='4586'>Bageshwar</OPTION>" +	
	"<OPTION VALUE='4587'>Bajpur</OPTION>" +	
	"<OPTION VALUE='4588'>Banbasa</OPTION>" +	
	"<OPTION VALUE='4589'>Bandia</OPTION>" +	
	"<OPTION VALUE='4590'>Barkot</OPTION>" +	
	"<OPTION VALUE='4591'>Bharat Heavy Electrical Limited Ranipur</OPTION>" +	
	"<OPTION VALUE='4592'>Bhimtal</OPTION>" +	
	"<OPTION VALUE='4593'>Bhowali</OPTION>" +	
	"<OPTION VALUE='4594'>Chamba - Tehri Garhwal</OPTION>" +	
	"<OPTION VALUE='4595'>Chamoli Gopeshwar</OPTION>" +	
	"<OPTION VALUE='4596'>Champawat</OPTION>" +	
	"<OPTION VALUE='4597'>Dehradun</OPTION>" +	
	"<OPTION VALUE='4598'>Dev Prayag - Pauri Garhwal</OPTION>" +	
	"<OPTION VALUE='4599'>Dev Prayag - Tehri Garhwal</OPTION>" +	
	"<OPTION VALUE='4600'>Dhaluwala</OPTION>" +	
	"<OPTION VALUE='4601'>Dhandera</OPTION>" +	
	"<OPTION VALUE='4602'>Dharchula</OPTION>" +	
	"<OPTION VALUE='4603'>Dharchula Dehat</OPTION>" +	
	"<OPTION VALUE='4604'>Didihat</OPTION>" +	
	"<OPTION VALUE='4605'>Dineshpur</OPTION>" +	
	"<OPTION VALUE='4606'>Dogadda</OPTION>" +	
	"<OPTION VALUE='4607'>Dwarahat</OPTION>" +	
	"<OPTION VALUE='4608'>Gadarpur</OPTION>" +	
	"<OPTION VALUE='4609'>Gangotri</OPTION>" +	
	"<OPTION VALUE='4610'>Gochar</OPTION>" +	
	"<OPTION VALUE='4611'>Haldwani-cum-Kathgodam</OPTION>" +	
	"<OPTION VALUE='4612'>Hardwar</OPTION>" +	
	"<OPTION VALUE='4613'>Jaspur</OPTION>" +	
	"<OPTION VALUE='4614'>Jhabrera</OPTION>" +	
	"<OPTION VALUE='4615'>Joshimath</OPTION>" +	
	"<OPTION VALUE='4616'>Kachnal Gosain</OPTION>" +	
	"<OPTION VALUE='4617'>Kaladungi</OPTION>" +	
	"<OPTION VALUE='4618'>Karn Prayag</OPTION>" +	
	"<OPTION VALUE='4619'>Kashipur</OPTION>" +	
	"<OPTION VALUE='4620'>Kashirampur</OPTION>" +	
	"<OPTION VALUE='4621'>Kedarnath</OPTION>" +	
	"<OPTION VALUE='4622'>Kela Khera</OPTION>" +	
	"<OPTION VALUE='4623'>Khatima</OPTION>" +	
	"<OPTION VALUE='4624'>Kichha</OPTION>" +	
	"<OPTION VALUE='4625'>Kirtinagar</OPTION>" +	
	"<OPTION VALUE='4626'>Kotdwara</OPTION>" +	
	"<OPTION VALUE='4627'>Laksar</OPTION>" +	
	"<OPTION VALUE='4628'>Lalkuan</OPTION>" +	
	"<OPTION VALUE='4629'>Landaura</OPTION>" +	
	"<OPTION VALUE='4630'>Lansdowne</OPTION>" +	
	"<OPTION VALUE='4631'>Lohaghat</OPTION>" +	
	"<OPTION VALUE='4632'>Mahua Dabra Haripura</OPTION>" +	
	"<OPTION VALUE='4633'>Mahua Kheraganj</OPTION>" +	
	"<OPTION VALUE='4634'>Manglaur</OPTION>" +	
	"<OPTION VALUE='4635'>Mohanpur Mohammadpur</OPTION>" +	
	"<OPTION VALUE='4636'>Muni Ki Reti</OPTION>" +	
	"<OPTION VALUE='4637'>Nagla</OPTION>" +	
	"<OPTION VALUE='4638'>Nainital</OPTION>" +	
	"<OPTION VALUE='4639'>Nand Prayag</OPTION>" +	
	"<OPTION VALUE='4640'>Narendra Nagar</OPTION>" +	
	"<OPTION VALUE='4641'>Pauri</OPTION>" +	
	"<OPTION VALUE='4642'>Pithoragarh</OPTION>" +	
	"<OPTION VALUE='4643'>Ramnagar - Nainital</OPTION>" +	
	"<OPTION VALUE='4644'>Ranikhet</OPTION>" +	
	"<OPTION VALUE='4645'>Roorkee</OPTION>" +	
	"<OPTION VALUE='4646'>Rudra Prayag</OPTION>" +	
	"<OPTION VALUE='4647'>Rudrapur - Udham Singh Nagar</OPTION>" +	
	"<OPTION VALUE='4648'>Shaktigarh</OPTION>" +	
	"<OPTION VALUE='4649'>Sitarganj</OPTION>" +	
	"<OPTION VALUE='4650'>Srinagar - Pauri Garhwal</OPTION>" +	
	"<OPTION VALUE='4651'>Sultanpur - Udham Singh Nagar</OPTION>" +	
	"<OPTION VALUE='4652'>Tanakpur</OPTION>" +	
	"<OPTION VALUE='4653'>Tehri</OPTION>" +	
	"<OPTION VALUE='4654'>Uttarkashi</OPTION>" +
		"</select>";
	}
if(myState == "West Bengal")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection()'>" +	
	"<OPTION VALUE='4885'>Kolkata</OPTION>" +	
			"<OPTION VALUE='4787'>Durgapur - Barddhaman</OPTION>" +	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='4655'>Adra</OPTION>" +	
	"<OPTION VALUE='4656'>Ahmadpur - Birbhum</OPTION>" +	
	"<OPTION VALUE='4657'>Aiho</OPTION>" +	
	"<OPTION VALUE='4658'>Aistala</OPTION>" +	
	"<OPTION VALUE='4659'>Alipurduar</OPTION>" +	
	"<OPTION VALUE='4660'>Alipurduar Rly.Jnc.</OPTION>" +	
	"<OPTION VALUE='4661'>Alpur</OPTION>" +	
	"<OPTION VALUE='4662'>Amkula</OPTION>" +	
	"<OPTION VALUE='4663'>Amodghata</OPTION>" +	
	"<OPTION VALUE='4664'>Amtala</OPTION>" +	
	"<OPTION VALUE='4665'>Andul</OPTION>" +	
	"<OPTION VALUE='4666'>Anksa</OPTION>" +	
	"<OPTION VALUE='4667'>Ankurhati</OPTION>" +	
	"<OPTION VALUE='4668'>Anup Nagar</OPTION>" +	
	"<OPTION VALUE='4669'>Arambag</OPTION>" +	
	"<OPTION VALUE='4670'>Argari</OPTION>" +	
	"<OPTION VALUE='4671'>Arra</OPTION>" +	
	"<OPTION VALUE='4672'>Asansol</OPTION>" +	
	"<OPTION VALUE='4673'>Ashoknagar Kalyangarh</OPTION>" +	
	"<OPTION VALUE='4674'>Aurangabad - Murshidabad</OPTION>" +	
	"<OPTION VALUE='4675'>Bablari Dewanganj</OPTION>" +	
	"<OPTION VALUE='4676'>Badhagachhi</OPTION>" +	
	"<OPTION VALUE='4677'>Baduria</OPTION>" +	
	"<OPTION VALUE='4678'>Bagnan</OPTION>" +	
	"<OPTION VALUE='4679'>Baharampur</OPTION>" +	
	"<OPTION VALUE='4680'>Bahirgram</OPTION>" +	
	"<OPTION VALUE='4681'>Bahula</OPTION>" +	
	"<OPTION VALUE='4682'>Baidyabati</OPTION>" +	
	"<OPTION VALUE='4683'>Bairatisal</OPTION>" +	
	"<OPTION VALUE='4684'>Balaram Pota</OPTION>" +	
	"<OPTION VALUE='4685'>Balarampur - 24 Parganas</OPTION>" +	
	"<OPTION VALUE='4686'>Balarampur - Puruliya</OPTION>" +	
	"<OPTION VALUE='4687'>Balichak</OPTION>" +	
	"<OPTION VALUE='4688'>Ballavpur</OPTION>" +	
	"<OPTION VALUE='4689'>Bally</OPTION>" +	
	"<OPTION VALUE='4690'>Balurghat</OPTION>" +	
	"<OPTION VALUE='4691'>Bamunari</OPTION>" +	
	"<OPTION VALUE='4692'>Banarhat Tea Garden</OPTION>" +	
	"<OPTION VALUE='4693'>Bangaon</OPTION>" +	
	"<OPTION VALUE='4694'>Bankra</OPTION>" +	
	"<OPTION VALUE='4695'>Bankura</OPTION>" +	
	"<OPTION VALUE='4696'>Bansberia</OPTION>" +	
	"<OPTION VALUE='4697'>Banshra</OPTION>" +	
	"<OPTION VALUE='4698'>Banupur</OPTION>" +	
	"<OPTION VALUE='4699'>Bara Bamonia</OPTION>" +	
	"<OPTION VALUE='4700'>Barabazar</OPTION>" +	
	"<OPTION VALUE='4701'>Baranagar</OPTION>" +	
	"<OPTION VALUE='4702'>Barasat</OPTION>" +	
	"<OPTION VALUE='4703'>Barddhaman</OPTION>" +	
	"<OPTION VALUE='4704'>Barijhati</OPTION>" +	
	"<OPTION VALUE='4705'>Barjora</OPTION>" +	
	"<OPTION VALUE='4706'>Barrackpur</OPTION>" +	
	"<OPTION VALUE='4707'>Barrackpur Cantonment</OPTION>" +	
	"<OPTION VALUE='4708'>Baruihuda</OPTION>" +	
	"<OPTION VALUE='4709'>Baruipur</OPTION>" +	
	"<OPTION VALUE='4710'>Basirhat</OPTION>" +	
	"<OPTION VALUE='4711'>Baska</OPTION>" +	
	"<OPTION VALUE='4712'>Begampur</OPTION>" +	
	"<OPTION VALUE='4713'>Beldanga</OPTION>" +	
	"<OPTION VALUE='4714'>Beldubi</OPTION>" +	
	"<OPTION VALUE='4715'>Belebathan</OPTION>" +	
	"<OPTION VALUE='4716'>Beliatore</OPTION>" +	
	"<OPTION VALUE='4717'>Bhadreswar</OPTION>" +	
	"<OPTION VALUE='4718'>Bhandardaha</OPTION>" +	
	"<OPTION VALUE='4719'>Bhangar Raghunathpur</OPTION>" +	
	"<OPTION VALUE='4720'>Bhangri Pratham Khanda</OPTION>" +	
	"<OPTION VALUE='4721'>Bhanowara</OPTION>" +	
	"<OPTION VALUE='4722'>Bhatpara</OPTION>" +	
	"<OPTION VALUE='4723'>Bholar Dabri</OPTION>" +	
	"<OPTION VALUE='4724'>Bidhan Nagar</OPTION>" +	
	"<OPTION VALUE='4725'>Bikihakola</OPTION>" +	
	"<OPTION VALUE='4726'>Bilandapur</OPTION>" +	
	"<OPTION VALUE='4727'>Bilpahari</OPTION>" +	
	"<OPTION VALUE='4728'>Bipra  Noapara</OPTION>" +	
	"<OPTION VALUE='4729'>Birlapur</OPTION>" +	
	"<OPTION VALUE='4730'>Birnagar</OPTION>" +	
	"<OPTION VALUE='4731'>Bishnupur - 24 Parganas</OPTION>" +	
	"<OPTION VALUE='4732'>Bishnupur - Bankura</OPTION>" +	
	"<OPTION VALUE='4733'>Bolpur</OPTION>" +	
	"<OPTION VALUE='4734'>Bowali</OPTION>" +	
	"<OPTION VALUE='4735'>Budge Budge</OPTION>" +	
	"<OPTION VALUE='4736'>Cart Road</OPTION>" +	
	"<OPTION VALUE='4737'>Chachanda</OPTION>" +	
	"<OPTION VALUE='4738'>Chak Bankola</OPTION>" +	
	"<OPTION VALUE='4739'>Chak Bansberia</OPTION>" +	
	"<OPTION VALUE='4740'>Chak Enayetnagar</OPTION>" +	
	"<OPTION VALUE='4741'>Chak Kashipur</OPTION>" +	
	"<OPTION VALUE='4742'>Chakapara</OPTION>" +	
	"<OPTION VALUE='4743'>Chakdaha</OPTION>" +	
	"<OPTION VALUE='4744'>Champdani</OPTION>" +	
	"<OPTION VALUE='4745'>Chamrail</OPTION>" +	
	"<OPTION VALUE='4746'>Chandannagar</OPTION>" +	
	"<OPTION VALUE='4747'>Chandpur - 24 Parganas</OPTION>" +	
	"<OPTION VALUE='4748'>Chandrakona</OPTION>" +	
	"<OPTION VALUE='4749'>Chapari</OPTION>" +	
	"<OPTION VALUE='4750'>Chapui</OPTION>" +	
	"<OPTION VALUE='4751'>Char Brahmanagar</OPTION>" +	
	"<OPTION VALUE='4752'>Char Maijdia</OPTION>" +	
	"<OPTION VALUE='4753'>Charka</OPTION>" +	
	"<OPTION VALUE='4754'>Chata Kalikapur</OPTION>" +	
	"<OPTION VALUE='4755'>Chechakhata</OPTION>" +	
	"<OPTION VALUE='4756'>Chelad</OPTION>" +	
	"<OPTION VALUE='4757'>Chhora</OPTION>" +	
	"<OPTION VALUE='4758'>Chikrand</OPTION>" +	
	"<OPTION VALUE='4759'>Chittaranjan</OPTION>" +	
	"<OPTION VALUE='4760'>Contai</OPTION>" +	
	"<OPTION VALUE='4761'>Dafahat</OPTION>" +	
	"<OPTION VALUE='4762'>Dainhat</OPTION>" +	
	"<OPTION VALUE='4763'>Dakshin Baguan</OPTION>" +	
	"<OPTION VALUE='4764'>Dakshin Jhapardaha</OPTION>" +	
	"<OPTION VALUE='4765'>Dakshin Rajyadharpur</OPTION>" +	
	"<OPTION VALUE='4766'>Dalkhola</OPTION>" +	
	"<OPTION VALUE='4767'>Dalurband</OPTION>" +	
	"<OPTION VALUE='4768'>Darappur</OPTION>" +	
	"<OPTION VALUE='4769'>Darjiling</OPTION>" +	
	"<OPTION VALUE='4770'>Debipur</OPTION>" +	
	"<OPTION VALUE='4771'>Deuli</OPTION>" +	
	"<OPTION VALUE='4772'>Dhakuria</OPTION>" +	
	"<OPTION VALUE='4773'>Dhandadihi</OPTION>" +	
	"<OPTION VALUE='4774'>Dhanyakuria</OPTION>" +	
	"<OPTION VALUE='4775'>Dharmapur</OPTION>" +	
	"<OPTION VALUE='4776'>Dhatrigram</OPTION>" +	
	"<OPTION VALUE='4777'>Dhuilya</OPTION>" +	
	"<OPTION VALUE='4778'>Dhulian</OPTION>" +	
	"<OPTION VALUE='4779'>Dhupguri</OPTION>" +	
	"<OPTION VALUE='4780'>Dhusaripara</OPTION>" +	
	"<OPTION VALUE='4781'>Diamond Harbour</OPTION>" +	
	"<OPTION VALUE='4782'>Dignala</OPTION>" +	
	"<OPTION VALUE='4783'>Dinhata</OPTION>" +	
	"<OPTION VALUE='4784'>Domjur</OPTION>" +	
	"<OPTION VALUE='4785'>Dubrajpur</OPTION>" +	
	"<OPTION VALUE='4786'>Dumdum</OPTION>" +	
	"<OPTION VALUE='4787'>Durgapur - Barddhaman</OPTION>" +	
	"<OPTION VALUE='4788'>Durllabhganj</OPTION>" +	
	"<OPTION VALUE='4789'>Egra</OPTION>" +	
	"<OPTION VALUE='4790'>Eksara</OPTION>" +	
	"<OPTION VALUE='4791'>English Bazar</OPTION>" +	
	"<OPTION VALUE='4792'>Falakata</OPTION>" +	
	"<OPTION VALUE='4793'>Farrakka Barrage Township</OPTION>" +	
	"<OPTION VALUE='4794'>Fatellapur</OPTION>" +	
	"<OPTION VALUE='4795'>Gabberia</OPTION>" +	
	"<OPTION VALUE='4796'>Gairkata</OPTION>" +	
	"<OPTION VALUE='4797'>Gangarampur</OPTION>" +	
	"<OPTION VALUE='4798'>Garalgachha</OPTION>" +	
	"<OPTION VALUE='4799'>Garshyamnagar</OPTION>" +	
	"<OPTION VALUE='4800'>Garulia</OPTION>" +	
	"<OPTION VALUE='4801'>Gayespur</OPTION>" +	
	"<OPTION VALUE='4802'>Ghatal</OPTION>" +	
	"<OPTION VALUE='4803'>Ghorsala</OPTION>" +	
	"<OPTION VALUE='4804'>Goaljan</OPTION>" +	
	"<OPTION VALUE='4805'>Goasafat</OPTION>" +	
	"<OPTION VALUE='4806'>Gobardanga</OPTION>" +	
	"<OPTION VALUE='4807'>Gopalpur - Nadia</OPTION>" +	
	"<OPTION VALUE='4808'>Gopinathpur</OPTION>" +	
	"<OPTION VALUE='4809'>Gora Bazar</OPTION>" +	
	"<OPTION VALUE='4810'>Guma</OPTION>" +	
	"<OPTION VALUE='4811'>Guriahati</OPTION>" +	
	"<OPTION VALUE='4812'>Guskara</OPTION>" +	
	"<OPTION VALUE='4813'>Habra</OPTION>" +	
	"<OPTION VALUE='4814'>Haldia</OPTION>" +	
	"<OPTION VALUE='4815'>Haldibari</OPTION>" +	
	"<OPTION VALUE='4816'>Halisahar</OPTION>" +	
	"<OPTION VALUE='4817'>Haora</OPTION>" +	
	"<OPTION VALUE='4818'>Harharia Chak</OPTION>" +	
	"<OPTION VALUE='4819'>Haripur</OPTION>" +	
	"<OPTION VALUE='4820'>Harishpur</OPTION>" +	
	"<OPTION VALUE='4821'>Hatgachha</OPTION>" +	
	"<OPTION VALUE='4822'>Hatsimla</OPTION>" +	
	"<OPTION VALUE='4823'>Hijuli</OPTION>" +	
	"<OPTION VALUE='4824'>Hindusthan Cables Town</OPTION>" +	
	"<OPTION VALUE='4825'>Hugli-Chinsurah</OPTION>" +	
	"<OPTION VALUE='4826'>Ichhapur Defence  Estate</OPTION>" +	
	"<OPTION VALUE='4827'>Islampur - Uttar Dinajpur</OPTION>" +	
	"<OPTION VALUE='4828'>Jafarpur</OPTION>" +	
	"<OPTION VALUE='4829'>Jagadanandapur</OPTION>" +	
	"<OPTION VALUE='4830'>Jagadishpur</OPTION>" +	
	"<OPTION VALUE='4831'>Jagtaj</OPTION>" +	
	"<OPTION VALUE='4832'>Jala Kendua</OPTION>" +	
	"<OPTION VALUE='4833'>Jalpaiguri</OPTION>" +	
	"<OPTION VALUE='4834'>Jamuria</OPTION>" +	
	"<OPTION VALUE='4835'>Jangipur - Murshidabad</OPTION>" +	
	"<OPTION VALUE='4836'>Jaygaon</OPTION>" +	
	"<OPTION VALUE='4837'>Jaynagar Mazilpur</OPTION>" +	
	"<OPTION VALUE='4838'>Jemari</OPTION>" +	
	"<OPTION VALUE='4839'>Jemari  (J.K. Nagar Township)</OPTION>" +	
	"<OPTION VALUE='4840'>Jetia</OPTION>" +	
	"<OPTION VALUE='4841'>Jhalda</OPTION>" +	
	"<OPTION VALUE='4842'>Jhargram</OPTION>" +	
	"<OPTION VALUE='4843'>Jhorhat</OPTION>" +	
	"<OPTION VALUE='4844'>Jiaganj Azimganj</OPTION>" +	
	"<OPTION VALUE='4845'>Jot Kamal</OPTION>" +	
	"<OPTION VALUE='4846'>Kachu Pukur</OPTION>" +	
	"<OPTION VALUE='4847'>Kajora</OPTION>" +	
	"<OPTION VALUE='4848'>Kakdihi</OPTION>" +	
	"<OPTION VALUE='4849'>Kalara</OPTION>" +	
	"<OPTION VALUE='4850'>Kaliaganj</OPTION>" +	
	"<OPTION VALUE='4851'>Kalimpong</OPTION>" +	
	"<OPTION VALUE='4852'>Kalna</OPTION>" +	
	"<OPTION VALUE='4853'>Kalyani</OPTION>" +	
	"<OPTION VALUE='4854'>Kamarhati</OPTION>" +	
	"<OPTION VALUE='4855'>Kanaipur</OPTION>" +	
	"<OPTION VALUE='4856'>Kanchrapara</OPTION>" +	
	"<OPTION VALUE='4857'>Kandi</OPTION>" +	
	"<OPTION VALUE='4858'>Kankuria</OPTION>" +	
	"<OPTION VALUE='4859'>Kantlia</OPTION>" +	
	"<OPTION VALUE='4860'>Kanyanagar</OPTION>" +	
	"<OPTION VALUE='4861'>Karimpur</OPTION>" +	
	"<OPTION VALUE='4862'>Kasba - Uttar Dinajpur</OPTION>" +	
	"<OPTION VALUE='4863'>Kasim Bazar</OPTION>" +	
	"<OPTION VALUE='4864'>Katwa</OPTION>" +	
	"<OPTION VALUE='4865'>Kaugachhi</OPTION>" +	
	"<OPTION VALUE='4866'>Kenda</OPTION>" +	
	"<OPTION VALUE='4867'>Kendra Khottamdi</OPTION>" +	
	"<OPTION VALUE='4868'>Kendua</OPTION>" +	
	"<OPTION VALUE='4869'>Kesabpur</OPTION>" +	
	"<OPTION VALUE='4870'>Khagrabari</OPTION>" +	
	"<OPTION VALUE='4871'>Khalia</OPTION>" +	
	"<OPTION VALUE='4872'>Khalor</OPTION>" +	
	"<OPTION VALUE='4873'>Khandra</OPTION>" +	
	"<OPTION VALUE='4874'>Khantora</OPTION>" +	
	"<OPTION VALUE='4875'>Kharagpur - Medinipur</OPTION>" +	
	"<OPTION VALUE='4876'>Kharagpur Rly. Settlement</OPTION>" +	
	"<OPTION VALUE='4877'>Kharar - Medinipur</OPTION>" +	
	"<OPTION VALUE='4878'>Khardaha</OPTION>" +	
	"<OPTION VALUE='4879'>Kharimala Khagrabari</OPTION>" +	
	"<OPTION VALUE='4880'>Kharsarai</OPTION>" +	
	"<OPTION VALUE='4881'>Khodarampur</OPTION>" +	
	"<OPTION VALUE='4882'>Koch Bihar</OPTION>" +	
	"<OPTION VALUE='4883'>Kodalia</OPTION>" +	
	"<OPTION VALUE='4884'>Kolaghat</OPTION>" +	
	"<OPTION VALUE='4885'>Kolkata</OPTION>" +	
	"<OPTION VALUE='4887'>Konardihi</OPTION>" +	
	"<OPTION VALUE='4888'>Konnagar</OPTION>" +	
	"<OPTION VALUE='4889'>Krishnanagar</OPTION>" +	
	"<OPTION VALUE='4890'>Krishnapur</OPTION>" +	
	"<OPTION VALUE='4891'>Kshidirpur</OPTION>" +	
	"<OPTION VALUE='4892'>Kshirpai</OPTION>" +	
	"<OPTION VALUE='4893'>Kulihanda</OPTION>" +	
	"<OPTION VALUE='4894'>Kulti</OPTION>" +	
	"<OPTION VALUE='4895'>Kunustara</OPTION>" +	
	"<OPTION VALUE='4896'>Kurseong</OPTION>" +	
	"<OPTION VALUE='4897'>Madanpur</OPTION>" +	
	"<OPTION VALUE='4898'>Madhusudanpur</OPTION>" +	
	"<OPTION VALUE='4899'>Madhyamgram</OPTION>" +	
	"<OPTION VALUE='4900'>Maheshtala</OPTION>" +	
	"<OPTION VALUE='4901'>Mahiari</OPTION>" +	
	"<OPTION VALUE='4902'>Mahira</OPTION>" +	
	"<OPTION VALUE='4903'>Mainaguri</OPTION>" +	
	"<OPTION VALUE='4904'>Makardaha</OPTION>" +	
	"<OPTION VALUE='4905'>Mal</OPTION>" +	
	"<OPTION VALUE='4906'>Mandarbani</OPTION>" +	
	"<OPTION VALUE='4907'>Manikpur - Haora</OPTION>" +	
	"<OPTION VALUE='4908'>Mansinhapur</OPTION>" +	
	"<OPTION VALUE='4909'>Maslandapur</OPTION>" +	
	"<OPTION VALUE='4910'>Mathabhanga</OPTION>" +	
	"<OPTION VALUE='4911'>Medinipur</OPTION>" +	
	"<OPTION VALUE='4912'>Mekliganj</OPTION>" +	
	"<OPTION VALUE='4913'>Memari</OPTION>" +	
	"<OPTION VALUE='4914'>Mirik</OPTION>" +	
	"<OPTION VALUE='4915'>Monoharpur</OPTION>" +	
	"<OPTION VALUE='4916'>Mrigala</OPTION>" +	
	"<OPTION VALUE='4917'>Muragachha</OPTION>" +	
	"<OPTION VALUE='4918'>Murgathaul</OPTION>" +	
	"<OPTION VALUE='4919'>Murshidabad</OPTION>" +	
	"<OPTION VALUE='4920'>Nabadwip</OPTION>" +	
	"<OPTION VALUE='4921'>Nabagram</OPTION>" +	
	"<OPTION VALUE='4922'>Nabagram Colony</OPTION>" +	
	"<OPTION VALUE='4923'>Nabgram</OPTION>" +	
	"<OPTION VALUE='4924'>Nachhratpur Katabari</OPTION>" +	
	"<OPTION VALUE='4925'>Naihati</OPTION>" +	
	"<OPTION VALUE='4926'>Nasra</OPTION>" +	
	"<OPTION VALUE='4927'>Natibpur</OPTION>" +	
	"<OPTION VALUE='4928'>Naupala</OPTION>" +	
	"<OPTION VALUE='4929'>Nebadhai Duttapukur</OPTION>" +	
	"<OPTION VALUE='4930'>New Barrackpur</OPTION>" +	
	"<OPTION VALUE='4931'>Nibra</OPTION>" +	
	"<OPTION VALUE='4932'>Nokpul</OPTION>" +	
	"<OPTION VALUE='4933'>North Barrackpur</OPTION>" +	
	"<OPTION VALUE='4934'>North Dumdum</OPTION>" +	
	"<OPTION VALUE='4935'>Old Maldah</OPTION>" +	
	"<OPTION VALUE='4936'>Ondal</OPTION>" +	
	"<OPTION VALUE='4937'>Pairagachha</OPTION>" +	
	"<OPTION VALUE='4938'>Palashban</OPTION>" +	
	"<OPTION VALUE='4939'>Panchla</OPTION>" +	
	"<OPTION VALUE='4940'>Panchpara</OPTION>" +	
	"<OPTION VALUE='4941'>Pandua</OPTION>" +	
	"<OPTION VALUE='4942'>Pangachhiya (B)</OPTION>" +	
	"<OPTION VALUE='4943'>Paniara</OPTION>" +	
	"<OPTION VALUE='4944'>Panihati</OPTION>" +	
	"<OPTION VALUE='4945'>Panuhat</OPTION>" +	
	"<OPTION VALUE='4946'>Par Beliya</OPTION>" +	
	"<OPTION VALUE='4947'>Parashkol</OPTION>" +	
	"<OPTION VALUE='4948'>Parasia</OPTION>" +	
	"<OPTION VALUE='4949'>Parbbatipur</OPTION>" +	
	"<OPTION VALUE='4950'>Paschim  Jitpur</OPTION>" +	
	"<OPTION VALUE='4951'>Paschim Punropara</OPTION>" +	
	"<OPTION VALUE='4952'>Pattabong Tea Garden</OPTION>" +	
	"<OPTION VALUE='4953'>Patuli</OPTION>" +	
	"<OPTION VALUE='4954'>Patulia</OPTION>" +	
	"<OPTION VALUE='4955'>Phulia</OPTION>" +	
	"<OPTION VALUE='4956'>Podara</OPTION>" +	
	"<OPTION VALUE='4957'>Prayagpur</OPTION>" +	
	"<OPTION VALUE='4958'>Pujali</OPTION>" +	
	"<OPTION VALUE='4959'>Purba Tajpur</OPTION>" +	
	"<OPTION VALUE='4960'>Puruliya</OPTION>" +	
	"<OPTION VALUE='4961'>Raghudebbati</OPTION>" +	
	"<OPTION VALUE='4962'>Raghunathchak</OPTION>" +	
	"<OPTION VALUE='4963'>Raghunathpur - Hugli</OPTION>" +	
	"<OPTION VALUE='4964'>Raghunathpur - Puruliya</OPTION>" +	
	"<OPTION VALUE='4965'>Raigachhi</OPTION>" +	
	"<OPTION VALUE='4966'>Raiganj</OPTION>" +	
	"<OPTION VALUE='4967'>Rajarhat Gopalpur</OPTION>" +	
	"<OPTION VALUE='4968'>Rajpur Sonarpur</OPTION>" +	
	"<OPTION VALUE='4969'>Ramchandrapur</OPTION>" +	
	"<OPTION VALUE='4970'>Ramjibanpur</OPTION>" +	
	"<OPTION VALUE='4971'>Ramnagar - Barddhaman</OPTION>" +	
	"<OPTION VALUE='4972'>Rampurhat</OPTION>" +	
	"<OPTION VALUE='4973'>Ranaghat</OPTION>" +	
	"<OPTION VALUE='4974'>Raniganj</OPTION>" +	
	"<OPTION VALUE='4975'>Ratibati</OPTION>" +	
	"<OPTION VALUE='4976'>Rishra</OPTION>" +	
	"<OPTION VALUE='4977'>Ruiya</OPTION>" +	
	"<OPTION VALUE='4978'>S.T. Power Project Town</OPTION>" +	
	"<OPTION VALUE='4979'>Sadpur</OPTION>" +	
	"<OPTION VALUE='4980'>Sahajadpur</OPTION>" +	
	"<OPTION VALUE='4981'>Sahapur</OPTION>" +	
	"<OPTION VALUE='4982'>Sainthia</OPTION>" +	
	"<OPTION VALUE='4983'>Salap</OPTION>" +	
	"<OPTION VALUE='4984'>Sankarpur</OPTION>" +	
	"<OPTION VALUE='4985'>Sankrail</OPTION>" +	
	"<OPTION VALUE='4986'>Santipur</OPTION>" +	
	"<OPTION VALUE='4987'>Santoshpur</OPTION>" +	
	"<OPTION VALUE='4988'>Sarenga</OPTION>" +	
	"<OPTION VALUE='4989'>Sarpi</OPTION>" +	
	"<OPTION VALUE='4990'>Satigachha</OPTION>" +	
	"<OPTION VALUE='4991'>Serampore</OPTION>" +	
	"<OPTION VALUE='4992'>Serpur</OPTION>" +	
	"<OPTION VALUE='4993'>Shankhanagar</OPTION>" +	
	"<OPTION VALUE='4994'>Siduli</OPTION>" +	
	"<OPTION VALUE='4995'>Siliguri - Darjiling</OPTION>" +	
	"<OPTION VALUE='4996'>Siliguri - Jalpaiguri</OPTION>" +	
	"<OPTION VALUE='4997'>Simla</OPTION>" +	
	"<OPTION VALUE='4998'>Singur</OPTION>" +	
	"<OPTION VALUE='4999'>Sirsha</OPTION>" +	
	"<OPTION VALUE='5000'>Sobhaganj</OPTION>" +	
	"<OPTION VALUE='5001'>Sonamukhi</OPTION>" +	
	"<OPTION VALUE='5002'>Sonatikiri</OPTION>" +	
	"<OPTION VALUE='5003'>South Dumdum</OPTION>" +	
	"<OPTION VALUE='5004'>Srikantabati</OPTION>" +	
	"<OPTION VALUE='5005'>Srirampur</OPTION>" +	
	"<OPTION VALUE='5006'>Sukdal</OPTION>" +	
	"<OPTION VALUE='5007'>Suri</OPTION>" +	
	"<OPTION VALUE='5008'>Taherpur</OPTION>" +	
	"<OPTION VALUE='5009'>Taki</OPTION>" +	
	"<OPTION VALUE='5010'>Talbandha</OPTION>" +	
	"<OPTION VALUE='5011'>Tamluk</OPTION>" +	
	"<OPTION VALUE='5012'>Tarakeswar</OPTION>" +	
	"<OPTION VALUE='5013'>Tentulkuli</OPTION>" +	
	"<OPTION VALUE='5014'>Titagarh</OPTION>" +	
	"<OPTION VALUE='5015'>Tufanganj</OPTION>" +	
	"<OPTION VALUE='5016'>Ukhra</OPTION>" +	
	"<OPTION VALUE='5017'>Uluberia</OPTION>" +	
	"<OPTION VALUE='5018'>Uttar Bagdogra</OPTION>" +	
	"<OPTION VALUE='5019'>Uttar Durgapur</OPTION>" +	
	"<OPTION VALUE='5020'>Uttar Goara</OPTION>" +	
	"<OPTION VALUE='5021'>Uttar Kalas</OPTION>" +	
	"<OPTION VALUE='5022'>Uttar Kamakhyaguri</OPTION>" +	
	"<OPTION VALUE='5023'>Uttar Latabari</OPTION>" +	
	"<OPTION VALUE='5024'>Uttar Mahammadpur</OPTION>" +	
	"<OPTION VALUE='5025'>Uttar Pirpur</OPTION>" +	
	"<OPTION VALUE='5026'>Uttar Raypur</OPTION>" +	
	"<OPTION VALUE='5027'>Uttarpara Kotrung</OPTION>" +
		"</select>";
	}
	
	document.getElementById("cityId").innerHTML = city;	
	populatelocations();
	populatecities1()
}
function checkStateSelection()
{  
	var myForm = document.regiestration;
	var myState = myForm.state;
	if(myState.value =="-1")
	{
		alert("Please Select State First");
		myState.focus();
	}
	else
	{
		populatelocations();
	}
}
function populatecities1()
{				
	
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	var city;
	if(myState == "Andaman And Nicobar")
	{
		
	city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Andamans</OPTION>" +	
	"<OPTION VALUE='02'> Nicobar</OPTION>" +	
	
		"</select>";
	}
/*
	if(myState== "Others")
	{
		city="<input name='city' class='text' type='text' />";
	}
*/
	if(myState == "-1")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +	
	"</select>";
	}

if(myState == "Andhra Pradesh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +		
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +		
	"<OPTION VALUE='01'>Adilabad</OPTION>" +	
	"<OPTION VALUE='02'>Anantapur</OPTION>" +	
	"<OPTION VALUE='03'>Chittoor</OPTION>" +	
	"<OPTION VALUE='04'>East Godavari</OPTION>" +	
	"<OPTION VALUE='05'>Guntur</OPTION>" +	
	"<OPTION VALUE='06'>Hyderabad</OPTION>" +	
	"<OPTION VALUE='07'>Cuddapah</OPTION>" +	
	"<OPTION VALUE='08'>Karimnagar</OPTION>" +	
	"<OPTION VALUE='09'>Khammam</OPTION>" +	
	"<OPTION VALUE='10'>Krishna</OPTION>" +	
	"<OPTION VALUE='11'>Kurnool</OPTION>" +	
	"<OPTION VALUE='12'>Mahbubnagar</OPTION>" +	
	"<OPTION VALUE='13'>Medak</OPTION>" +	
	"<OPTION VALUE='14'>Nalgonda</OPTION>" +	
	"<OPTION VALUE='15'>Nellore</OPTION>" +	
	"<OPTION VALUE='16'>Nizamabad</OPTION>" +	
	"<OPTION VALUE='17'>Prakasam</OPTION>" +	
	"<OPTION VALUE='18'>Rangareddi</OPTION>" +	
	"<OPTION VALUE='19'>Srikakulam</OPTION>" +	
	"<OPTION VALUE='20'>Visakhapatnam</OPTION>" +	
	"<OPTION VALUE='21'>Vizianagaram</OPTION>" +	
	"<OPTION VALUE='22'>Warangal</OPTION>" +	
	"<OPTION VALUE='23'>West Godavari</OPTION>" +	
	
		"</select>";
	}
if(myState == "Arunachal Pradesh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +				
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +	
	"<OPTION VALUE='01'>Changlang</OPTION>" +	
	"<OPTION VALUE='02'>Dibang Valley</OPTION>" +	
	"<OPTION VALUE='03'>East Kameng</OPTION>" +	
	"<OPTION VALUE='04'>East Siang</OPTION>" +	
	"<OPTION VALUE='05'>Lohit</OPTION>" +	
	"<OPTION VALUE='06'>Lower Subansiri</OPTION>" +	
	"<OPTION VALUE='07'>Papum Pare</OPTION>" +	
	"<OPTION VALUE='08'>Tawang</OPTION>" +	
	"<OPTION VALUE='09'>Tirap</OPTION>" +	
	"<OPTION VALUE='10'>Upper Siang</OPTION>" +	
	"<OPTION VALUE='11'>Upper Subansiri</OPTION>" +	
	"<OPTION VALUE='12'>West Kameng</OPTION>" +	
	"<OPTION VALUE='13'>West Siang</OPTION>" +	
	
	"</select>";
	}
if(myState == "Assam")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Barpeta</OPTION>" +	
	"<OPTION VALUE='02'>Bongaigaon</OPTION>" +	
	"<OPTION VALUE='03'>Cachar</OPTION>" +	
	"<OPTION VALUE='04'>Darrang</OPTION>" +	
	"<OPTION VALUE='05'>Dhemaji</OPTION>" +	
	"<OPTION VALUE='06'>Dhubri</OPTION>" +	
	"<OPTION VALUE='07'>Dibrugarh</OPTION>" +	
	"<OPTION VALUE='08'>Goalpara</OPTION>" +	
	"<OPTION VALUE='09'>Golaghat</OPTION>" +	
	"<OPTION VALUE='10'>Hailakandi</OPTION>" +	
	"<OPTION VALUE='11'>Jorhat</OPTION>" +	
	"<OPTION VALUE='12'>Karbi Anglong</OPTION>" +	
	"<OPTION VALUE='13'>Karimganj</OPTION>" +	
	"<OPTION VALUE='14'>Kokrajhar</OPTION>" +	
	"<OPTION VALUE='15'>Lakhimpur</OPTION>" +	
	"<OPTION VALUE='16'>Marigaon</OPTION>" +	
	"<OPTION VALUE='17'>Nagaon</OPTION>" +	
	"<OPTION VALUE='18'>Nalbari</OPTION>" +	
	"<OPTION VALUE='19'>North Cachar Hills</OPTION>" +	
	"<OPTION VALUE='20'>Sibsagar</OPTION>" +	
	"<OPTION VALUE='21'>Sonitpur</OPTION>" +	
	"<OPTION VALUE='22'>Tinsukia</OPTION>" +	
	
		"</select>";
	}
if(myState == "Bihar")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Araria</OPTION>" +	
	"<OPTION VALUE='02'>Aurangabad</OPTION>" +	
	"<OPTION VALUE='03'>Banka</OPTION>" +	
	"<OPTION VALUE='04'>Begusarai</OPTION>" +	
	"<OPTION VALUE='05'>Bhagalpur</OPTION>" +	
	"<OPTION VALUE='06'>Bhojpur</OPTION>" +	
	"<OPTION VALUE='07'>Buxar</OPTION>" +	
	"<OPTION VALUE='08'>Darbhanga</OPTION>" +	
	"<OPTION VALUE='09'>Gaya</OPTION>" +	
	"<OPTION VALUE='10'>Gopalganj</OPTION>" +	
	"<OPTION VALUE='11'>Jamui</OPTION>" +	
	"<OPTION VALUE='12'>Jehanabad</OPTION>" +	
	"<OPTION VALUE='13'>Kaimur (Bhabua)</OPTION>" +	
	"<OPTION VALUE='14'>Katihar</OPTION>" +	
	"<OPTION VALUE='15'>Khagaria</OPTION>" +	
	"<OPTION VALUE='16'>Kishanganj</OPTION>" +	
	"<OPTION VALUE='17'>Lakhisarai</OPTION>" +	
	"<OPTION VALUE='18'>Madhepura</OPTION>" +	
	"<OPTION VALUE='19'>Madhubani</OPTION>" +	
	"<OPTION VALUE='20'>Munger</OPTION>" +	
	"<OPTION VALUE='21'>Muzaffarpur</OPTION>" +	
	"<OPTION VALUE='22'>Nalanda</OPTION>" +	
	"<OPTION VALUE='23'>Nawada</OPTION>" +	
	"<OPTION VALUE='24'>Pashchim Champaran</OPTION>" +	
	"<OPTION VALUE='25'>Patna</OPTION>" +	
	"<OPTION VALUE='26'>Purba Champaran</OPTION>" +	
	"<OPTION VALUE='27'>Purnia</OPTION>" +	
	"<OPTION VALUE='28'>Rohtas</OPTION>" +	
	"<OPTION VALUE='29'>Saharsa</OPTION>" +	
	"<OPTION VALUE='30'>Samastipur</OPTION>" +	
	"<OPTION VALUE='31'>Saran</OPTION>" +	
	"<OPTION VALUE='32'>Sheikhpura</OPTION>" +	
	"<OPTION VALUE='33'>Sheohar</OPTION>" +	
	"<OPTION VALUE='34'>Sitamarhi</OPTION>" +	
	"<OPTION VALUE='35'>Siwan</OPTION>" +	
	"<OPTION VALUE='36'>Supaul</OPTION>" +	
	"<OPTION VALUE='37'>Vaishali</OPTION>" +	
	
		"</select>";
	}
if(myState == "Chandigarh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='476'>Chandigarh</OPTION>" +
		"</select>";
	}
if(myState == "Chhattisgarh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Bastar</OPTION>" +	
	"<OPTION VALUE='02'>Bilaspur</OPTION>" +	
	"<OPTION VALUE='03'>Dantewada</OPTION>" +	
	"<OPTION VALUE='04'>Dhamtari</OPTION>" +	
	"<OPTION VALUE='05'>Durg</OPTION>" +	
	"<OPTION VALUE='06'>Janjgir-Champa</OPTION>" +	
	"<OPTION VALUE='07'>Jashpur</OPTION>" +	
	"<OPTION VALUE='08'>Kanker</OPTION>" +	
	"<OPTION VALUE='09'>Kawardha</OPTION>" +	
	"<OPTION VALUE='10'>Korba</OPTION>" +	
	"<OPTION VALUE='11'>Koriya</OPTION>" +	
	"<OPTION VALUE='12'>Mahasamund</OPTION>" +	
	"<OPTION VALUE='13'>Raigarh</OPTION>" +	
	"<OPTION VALUE='14'>Raipur</OPTION>" +	
	"<OPTION VALUE='15'>Rajnandgaon</OPTION>" +	
	"<OPTION VALUE='16'>Surguja</OPTION>" +	
	
		"</select>";
	}
if(myState == "Dadra and Nagar Haveli")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='561'>Amli</OPTION>" +	
	"<OPTION VALUE='562'>Naroli</OPTION>" +	
	"<OPTION VALUE='563'>Silvassa</OPTION>" +
		"</select>";
	}


if(myState == "Goa")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>North Goa</OPTION>" +	
	"<OPTION VALUE='02'>South Goa</OPTION>" +	
	
		"</select>";
	}
if(myState == "Gujarat")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Ahmadabad</OPTION>" +	
	"<OPTION VALUE='02'>Amreli</OPTION>" +	
	"<OPTION VALUE='03'>Anand</OPTION>" +	
	"<OPTION VALUE='04'>Banas Kantha</OPTION>" +	
	"<OPTION VALUE='05'>Bharuch</OPTION>" +	
	"<OPTION VALUE='06'>Bhavnagar</OPTION>" +	
	"<OPTION VALUE='07'>Dohad</OPTION>" +	
	"<OPTION VALUE='08'>Gandhinagar</OPTION>" +	
	"<OPTION VALUE='09'>Jamnagar</OPTION>" +	
	"<OPTION VALUE='10'>Junagadh</OPTION>" +	
	"<OPTION VALUE='11'>Kheda</OPTION>" +	
	"<OPTION VALUE='12'>Kachchh</OPTION>" +	
	"<OPTION VALUE='13'>Mahesana</OPTION>" +	
	"<OPTION VALUE='14'>Narmada</OPTION>" +	
	"<OPTION VALUE='15'>Navsari</OPTION>" +	
	"<OPTION VALUE='16'>Panch Mahals</OPTION>" +	
	"<OPTION VALUE='17'>Patan</OPTION>" +	
	"<OPTION VALUE='18'>Porbandar</OPTION>" +	
	"<OPTION VALUE='19'>Rajkot</OPTION>" +	
	"<OPTION VALUE='20'>Sabar Kantha</OPTION>" +	
	"<OPTION VALUE='21'>Surat</OPTION>" +	
	"<OPTION VALUE='22'>Surendranagar</OPTION>" +	
	"<OPTION VALUE='23'>The Dangs</OPTION>" +	
	"<OPTION VALUE='24'>Vadodara</OPTION>" +	
	"<OPTION VALUE='25'>Valsad</OPTION>" +	
	
		"</select>";
	}
if(myState == "Haryana")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='925'>Gurgaon</OPTION>" +	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='01'>Ambala</OPTION>" +	
	"<OPTION VALUE='02'>Bhiwani</OPTION>" +	
	"<OPTION VALUE='03'>Faridabad</OPTION>" +	
	"<OPTION VALUE='04'>Fatehabad</OPTION>" +	
	"<OPTION VALUE='05'>Gurgaon</OPTION>" +	
	"<OPTION VALUE='06'>Hisar</OPTION>" +	
	"<OPTION VALUE='07'>Jhajjar</OPTION>" +	
	"<OPTION VALUE='08'>Jind</OPTION>" +	
	"<OPTION VALUE='09'>Kaithal</OPTION>" +	
	"<OPTION VALUE='10'>Karnal</OPTION>" +	
	"<OPTION VALUE='11'>Kurukshetra</OPTION>" +	
	"<OPTION VALUE='12'>Mahendragarh</OPTION>" +	
	"<OPTION VALUE='13'>Panchkula</OPTION>" +	
	"<OPTION VALUE='14'>Panipat</OPTION>" +	
	"<OPTION VALUE='15'>Rewari</OPTION>" +	
	"<OPTION VALUE='16'>Rohtak</OPTION>" +	
	"<OPTION VALUE='17'>Sirsa</OPTION>" +	
	"<OPTION VALUE='18'>Sonipat</OPTION>" +	
	"<OPTION VALUE='19'>Yamunanagar</OPTION>" +	
	
		"</select>";
	}
if(myState == "Himachal Pradesh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Bilaspur</OPTION>" +	
	"<OPTION VALUE='02'>Chamba</OPTION>" +	
	"<OPTION VALUE='03'>Hamirpur</OPTION>" +	
	"<OPTION VALUE='04'>Kangra</OPTION>" +	
	"<OPTION VALUE='05'>Kinnaur</OPTION>" +	
	"<OPTION VALUE='06'>Kullu</OPTION>" +	
	"<OPTION VALUE='07'>Lahaul and Spiti</OPTION>" +	
	"<OPTION VALUE='08'>Mandi</OPTION>" +	
	"<OPTION VALUE='09'>Shimla</OPTION>" +	
	"<OPTION VALUE='10'>Sirmaur</OPTION>" +	
	"<OPTION VALUE='11'>Solan</OPTION>" +	
	"<OPTION VALUE='12'>Una</OPTION>" +	
	
		"</select>";
	}
if(myState == "Jammu and Kashmir")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	
	"<OPTION VALUE='01'>Anantnag</OPTION>" +	
	"<OPTION VALUE='02'>Badgam</OPTION>" +	
	"<OPTION VALUE='03'>Bandipore</OPTION>" +	
	"<OPTION VALUE='04'>Baramula</OPTION>" +	
	"<OPTION VALUE='05'>Doda</OPTION>" +	
	"<OPTION VALUE='06'>Jammu</OPTION>" +	
	"<OPTION VALUE='07'>Kargil</OPTION>" +	
	"<OPTION VALUE='08'>Kathua</OPTION>" +	
	"<OPTION VALUE='09'>Kupwara</OPTION>" +	
	"<OPTION VALUE='10'>Leh (Ladakh)</OPTION>" +	
	"<OPTION VALUE='11'>Punch</OPTION>" +	
	"<OPTION VALUE='12'>Pulwama</OPTION>" +	
	"<OPTION VALUE='13'>Rajauri</OPTION>" +	
	"<OPTION VALUE='14'>Srinagar</OPTION>" +	
	"<OPTION VALUE='15'>Udhampur</OPTION>" +	
	
		"</select>";
	}
if(myState == "Jharkhand")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Bokaro</OPTION>" +	
	"<OPTION VALUE='02'>Chatra</OPTION>" +	
	"<OPTION VALUE='03'>Deoghar</OPTION>" +	
	"<OPTION VALUE='04'>Dhanbad</OPTION>" +	
	"<OPTION VALUE='05'>Dumka</OPTION>" +	
	"<OPTION VALUE='06'>Garhwa</OPTION>" +	
	"<OPTION VALUE='07'>Giridih</OPTION>" +	
	"<OPTION VALUE='08'>Godda</OPTION>" +	
	"<OPTION VALUE='09'>Gumla</OPTION>" +	
	"<OPTION VALUE='10'>Hazaribag</OPTION>" +	
	"<OPTION VALUE='11'>Kodarma</OPTION>" +	
	"<OPTION VALUE='12'>Lohardaga</OPTION>" +	
	"<OPTION VALUE='13'>Pakaur</OPTION>" +	
	"<OPTION VALUE='14'>Palamu</OPTION>" +	
	"<OPTION VALUE='15'>Pashchimi Singhbhum</OPTION>" +	
	"<OPTION VALUE='16'>Purbi Singhbhum</OPTION>" +	
	"<OPTION VALUE='17'>Ranchi</OPTION>" +	
	"<OPTION VALUE='18'>Sahibganj</OPTION>" +	
	
		"</select>";
	}
if(myState == "Karnataka")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
													
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='01'>Bagalkot</OPTION>" +	
	"<OPTION VALUE='02'>Bangalore</OPTION>" +	
	"<OPTION VALUE='03'>Bangalore Rural</OPTION>" +	
	"<OPTION VALUE='04'>Belgaum</OPTION>" +	
	"<OPTION VALUE='05'>Bellary</OPTION>" +	
	"<OPTION VALUE='06'>Bidar</OPTION>" +	
	"<OPTION VALUE='07'>Bijapur</OPTION>" +	
	"<OPTION VALUE='08'>Chamarajanagar</OPTION>" +	
	"<OPTION VALUE='09'>Chikmagalur</OPTION>" +	
	"<OPTION VALUE='10'>Chitradurga</OPTION>" +	
	"<OPTION VALUE='11'>Dakshina Kannada</OPTION>" +	
	"<OPTION VALUE='12'>Davanagere</OPTION>" +	
	"<OPTION VALUE='13'>Dharwad</OPTION>" +	
	"<OPTION VALUE='14'>Gadag</OPTION>" +	
	"<OPTION VALUE='15'>Gulbarga</OPTION>" +	
	"<OPTION VALUE='16'>Hassan</OPTION>" +	
	"<OPTION VALUE='17'>Haveri</OPTION>" +	
	"<OPTION VALUE='18'>Kodagu</OPTION>" +	
	"<OPTION VALUE='19'>Kolar</OPTION>" +	
	"<OPTION VALUE='20'>Koppal</OPTION>" +	
	"<OPTION VALUE='21'>Mandya</OPTION>" +	
	"<OPTION VALUE='22'>Mysore</OPTION>" +	
	"<OPTION VALUE='23'>Raichur</OPTION>" +	
	"<OPTION VALUE='24'>Shimoga</OPTION>" +	
	"<OPTION VALUE='25'>Tumkur</OPTION>" +	
	"<OPTION VALUE='26'>Udupi</OPTION>" +	
	"<OPTION VALUE='27'>Uttara Kannada</OPTION>" +	
	
		"</select>";
	}
if(myState == "Kerala")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Alappuzha</OPTION>" +	
	"<OPTION VALUE='02'>Ernakulam</OPTION>" +	
	"<OPTION VALUE='03'>Idukki</OPTION>" +	
	"<OPTION VALUE='04'>Kannur</OPTION>" +	
	"<OPTION VALUE='05'>Kasaragod</OPTION>" +	
	"<OPTION VALUE='06'>Kollam</OPTION>" +	
	"<OPTION VALUE='07'>Kottayam</OPTION>" +	
	"<OPTION VALUE='08'>Kozhikode</OPTION>" +	
	"<OPTION VALUE='09'>Malappuram</OPTION>" +	
	"<OPTION VALUE='10'>Palakkad</OPTION>" +	
	"<OPTION VALUE='11'>Pathanamthitta</OPTION>" +	
	"<OPTION VALUE='12'>Thiruvananthapuram</OPTION>" +	
	"<OPTION VALUE='13'>Thrissur</OPTION>" +	
	"<OPTION VALUE='14'>Wayanad</OPTION>" +	
	
		"</select>";
	}
if(myState == "Lakshadweep")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='1676'>Amini</OPTION>" +	
	"<OPTION VALUE='1677'>Kavaratti</OPTION>" +	
	"<OPTION VALUE='1678'>Minicoy</OPTION>" +
		"</select>";
	}
if(myState == "Madhya Pradesh")
	{
		city = "<select name='District' style='width: 150px; height: 18px;font:Verdana,Arial,Helvetica,sans-serif; font-size:9px;' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Ashoknagar</OPTION>" +	
	"<OPTION VALUE='02'>Balaghat</OPTION>" +	
	"<OPTION VALUE='03'>Barwani</OPTION>" +	
	"<OPTION VALUE='04'>Betul</OPTION>" +	
	"<OPTION VALUE='05'>Bhind</OPTION>" +	
	"<OPTION VALUE='06'>Bhopal</OPTION>" +	
	"<OPTION VALUE='07'>Chhatarpur</OPTION>" +	
	"<OPTION VALUE='08'>Chhindwara</OPTION>" +	
	"<OPTION VALUE='09'>Damoh</OPTION>" +	
	"<OPTION VALUE='10'>Datia</OPTION>" +	
	"<OPTION VALUE='11'>Dewas</OPTION>" +	
	"<OPTION VALUE='12'>Dhar</OPTION>" +	
	"<OPTION VALUE='13'>Dindori</OPTION>" +	
	"<OPTION VALUE='14'>Guna</OPTION>" +	
	"<OPTION VALUE='15'>Gwalior</OPTION>" +	
	"<OPTION VALUE='16'>Harda</OPTION>" +	
	"<OPTION VALUE='17'>Hoshangabad</OPTION>" +	
	"<OPTION VALUE='18'>Indore</OPTION>" +	
	"<OPTION VALUE='19'>Jabalpur</OPTION>" +	
	"<OPTION VALUE='20'>Jhabua</OPTION>" +	
	"<OPTION VALUE='21'>Katni</OPTION>" +	
	"<OPTION VALUE='22'>East Nimar</OPTION>" +	
	"<OPTION VALUE='23'>West Nimar</OPTION>" +	
	"<OPTION VALUE='24'>Mandla</OPTION>" +	
	"<OPTION VALUE='25'>Mandsaur</OPTION>" +	
	"<OPTION VALUE='26'>Morena</OPTION>" +	
	"<OPTION VALUE='27'>Narsimhapur</OPTION>" +	
	"<OPTION VALUE='28'>Neemuch</OPTION>" +	
	"<OPTION VALUE='29'>Panna</OPTION>" +	
	"<OPTION VALUE='30'>Raisen</OPTION>" +	
	"<OPTION VALUE='31'>Rajgarh</OPTION>" +	
	"<OPTION VALUE='32'>Ratlam</OPTION>" +	
	"<OPTION VALUE='33'>Rewa</OPTION>" +	
	"<OPTION VALUE='34'>Sagar</OPTION>" +	
	"<OPTION VALUE='35'>Satna</OPTION>" +	
	"<OPTION VALUE='36'>Sehore</OPTION>" +	
	"<OPTION VALUE='37'>Seoni</OPTION>" +	
	"<OPTION VALUE='38'>Shahdol</OPTION>" +	
	"<OPTION VALUE='39'>Shajapur</OPTION>" +	
	"<OPTION VALUE='40'>Sheopur</OPTION>" +	
	"<OPTION VALUE='41'>Shivpuri</OPTION>" +	
	"<OPTION VALUE='42'>Sidhi</OPTION>" +	
	"<OPTION VALUE='43'>Tikamgarh</OPTION>" +	
	"<OPTION VALUE='44'>Ujjain</OPTION>" +	
	"<OPTION VALUE='45'>Umaria</OPTION>" +	
	"<OPTION VALUE='46'>Vidisha</OPTION>" +	
	
		"</select>";
	}
if(myState == "Maharashtra")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
			
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	
	"<OPTION VALUE='01'>Ahmadnagar</OPTION>" +	
	"<OPTION VALUE='02'>Akola</OPTION>" +	
	"<OPTION VALUE='03'>Amravati</OPTION>" +	
	"<OPTION VALUE='04'>Aurangabad</OPTION>" +	
	"<OPTION VALUE='05'>Bid</OPTION>" +	
	"<OPTION VALUE='06'>Bhandara</OPTION>" +	
	"<OPTION VALUE='07'>Buldana</OPTION>" +	
	"<OPTION VALUE='08'>Chandrapur</OPTION>" +	
	"<OPTION VALUE='09'>Dhule</OPTION>" +	
	"<OPTION VALUE='10'>Gadchiroli</OPTION>" +	
	"<OPTION VALUE='11'>Gondiya</OPTION>" +	
	"<OPTION VALUE='12'>Hingoli</OPTION>" +	
	"<OPTION VALUE='13'>Jalgaon</OPTION>" +	
	"<OPTION VALUE='14'>Jalna</OPTION>" +	
	"<OPTION VALUE='15'>Kolhapur</OPTION>" +	
	"<OPTION VALUE='16'>Latur</OPTION>" +	
	"<OPTION VALUE='17'>Mumbai City</OPTION>" +	
	"<OPTION VALUE='18'>Mumbai suburban</OPTION>" +	
	"<OPTION VALUE='19'>Nagpur</OPTION>" +	
	"<OPTION VALUE='20'>Nanded</OPTION>" +	
	"<OPTION VALUE='21'>Nandurbar</OPTION>" +	
	"<OPTION VALUE='22'>Nashik</OPTION>" +	
	"<OPTION VALUE='23'>Osmanabad</OPTION>" +	
	"<OPTION VALUE='24'>Parbhani</OPTION>" +	
	"<OPTION VALUE='25'>Pune</OPTION>" +	
	"<OPTION VALUE='26'>Raigad</OPTION>" +	
	"<OPTION VALUE='27'>Ratnagiri</OPTION>" +	
	"<OPTION VALUE='28'>Sangli</OPTION>" +	
	"<OPTION VALUE='29'>Satara</OPTION>" +	
	"<OPTION VALUE='30'>Sindhudurg</OPTION>" +	
	"<OPTION VALUE='31'>Solapur</OPTION>" +	
	"<OPTION VALUE='32'>Thane</OPTION>" +	
	"<OPTION VALUE='33'>Wardha</OPTION>" +	
	"<OPTION VALUE='34'>Washim</OPTION>" +	
	"<OPTION VALUE='35'>Yavatmal</OPTION>" +	
	
		"</select>";
	}
if(myState == "Manipur")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Bishnupur</OPTION>" +	
	"<OPTION VALUE='02'>Chandel</OPTION>" +	
	"<OPTION VALUE='03'>Churachandpur</OPTION>" +	
	"<OPTION VALUE='04'>Imphal East</OPTION>" +	
	"<OPTION VALUE='05'>Imphal West</OPTION>" +	
	"<OPTION VALUE='06'>Senapati</OPTION>" +	
	"<OPTION VALUE='07'>Tamenglong</OPTION>" +	
	"<OPTION VALUE='08'>Thoubal</OPTION>" +	
	"<OPTION VALUE='09'>Ukhrul</OPTION>" +	
	
		"</select>";
	}
if(myState == "Meghalaya")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>East Garo Hills</OPTION>" +	
	"<OPTION VALUE='02'>East Khasi Hills</OPTION>" +	
	"<OPTION VALUE='03'>Jaintia Hills</OPTION>" +	
	"<OPTION VALUE='04'>Ri Bhoi</OPTION>" +	
	"<OPTION VALUE='05'>South Garo Hills</OPTION>" +	
	"<OPTION VALUE='06'>West Garo Hills</OPTION>" +	
	"<OPTION VALUE='07'>West Khasi Hills</OPTION>" +	
	
		"</select>";
	}
if(myState == "Mizoram")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Aizawl</OPTION>" +	
	"<OPTION VALUE='02'>Champhai</OPTION>" +	
	"<OPTION VALUE='03'>Kolasib</OPTION>" +	
	"<OPTION VALUE='04'>Lawngtlai</OPTION>" +	
	"<OPTION VALUE='05'>Lunglei</OPTION>" +	
	"<OPTION VALUE='06'>Mamit</OPTION>" +	
	"<OPTION VALUE='07'>Saiha</OPTION>" +	
	"<OPTION VALUE='08'>Serchhip</OPTION>" +	
	
		"</select>";
	}
if(myState == "Nagaland")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Mon</OPTION>" +	
	"<OPTION VALUE='02'>Dimapur</OPTION>" +	
	"<OPTION VALUE='03'>Kohima</OPTION>" +	
	"<OPTION VALUE='04'>Mokokchung</OPTION>" +	
	"<OPTION VALUE='05'>Phek</OPTION>" +	
	"<OPTION VALUE='06'>Tuensang</OPTION>" +	
	"<OPTION VALUE='07'>Wokha</OPTION>" +	
	"<OPTION VALUE='08'>Zunheboto</OPTION>" +
		"</select>";
	}
if(myState == "Orissa")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	
	"<OPTION VALUE='01'>Anugul</OPTION>" +	
	"<OPTION VALUE='02'>Baleshwar</OPTION>" +	
	"<OPTION VALUE='03'>Bargarh</OPTION>" +	
	"<OPTION VALUE='04'>Bhadrak</OPTION>" +	
	"<OPTION VALUE='05'>Balangir</OPTION>" +	
	"<OPTION VALUE='06'>Baudh</OPTION>" +	
	"<OPTION VALUE='07'>Cuttack</OPTION>" +	
	"<OPTION VALUE='08'>Debagarh</OPTION>" +	
	"<OPTION VALUE='09'>Dhenkanal</OPTION>" +	
	"<OPTION VALUE='10'>Gajapati</OPTION>" +	
	"<OPTION VALUE='11'>Ganjam</OPTION>" +	
	"<OPTION VALUE='12'>Jagatsinghapur</OPTION>" +	
	"<OPTION VALUE='13'>Jajapur</OPTION>" +	
	"<OPTION VALUE='14'>Jharsuguda</OPTION>" +	
	"<OPTION VALUE='15'>Kalahandi</OPTION>" +	
	"<OPTION VALUE='16'>Kandhamal</OPTION>" +	
	"<OPTION VALUE='17'>Kendrapara</OPTION>" +	
	"<OPTION VALUE='18'>Kendujhar</OPTION>" +	
	"<OPTION VALUE='19'>Khordha</OPTION>" +	
	"<OPTION VALUE='20'>Koraput</OPTION>" +	
	"<OPTION VALUE='21'>Malkangiri</OPTION>" +	
	"<OPTION VALUE='22'>Mayurbhanj</OPTION>" +	
	"<OPTION VALUE='23'>Nabarangapur</OPTION>" +	
	"<OPTION VALUE='24'>Nayagarh</OPTION>" +	
	"<OPTION VALUE='25'>Nuapada</OPTION>" +	
	"<OPTION VALUE='26'>Puri</OPTION>" +	
	"<OPTION VALUE='27'>Rayagada</OPTION>" +	
	"<OPTION VALUE='28'>Sambalpur</OPTION>" +	
	"<OPTION VALUE='29'>Sonapur</OPTION>" +	
	"<OPTION VALUE='30'>Sundargarh</OPTION>" +	
	
		"</select>";
	}
if(myState == "Pondicherry")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Karaikal </OPTION>" +	
	"<OPTION VALUE='02'>Mahe </OPTION>" +	
	"<OPTION VALUE='03'>Pondicherry</OPTION>" +	
	"<OPTION VALUE='04'>Yanam </OPTION>" +
		"</select>";
	}
if(myState == "Punjab")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Amritsar</OPTION>" +	
	"<OPTION VALUE='02'>Bathinda</OPTION>" +	
	"<OPTION VALUE='03'>Faridkot</OPTION>" +	
	"<OPTION VALUE='04'>Fatehgarh Sahib</OPTION>" +	
	"<OPTION VALUE='05'>Firozpur</OPTION>" +	
	"<OPTION VALUE='06'>Gurdaspur</OPTION>" +	
	"<OPTION VALUE='07'>Hoshiarpur</OPTION>" +	
	"<OPTION VALUE='08'>Jalandhar</OPTION>" +	
	"<OPTION VALUE='09'>Kapurthala</OPTION>" +	
	"<OPTION VALUE='10'>Ludhiana</OPTION>" +	
	"<OPTION VALUE='11'>Mansa</OPTION>" +	
	"<OPTION VALUE='12'>Moga</OPTION>" +	
	"<OPTION VALUE='13'>Muktsar</OPTION>" +	
	"<OPTION VALUE='14'>Nawanshahr</OPTION>" +	
	"<OPTION VALUE='15'>Patiala</OPTION>" +	
	"<OPTION VALUE='16'>Rupnagar</OPTION>" +	
	"<OPTION VALUE='17'>Sangrur</OPTION>" +	
	
		"</select>";
	}
if(myState == "Rajasthan")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Ajmer</OPTION>" +	
	"<OPTION VALUE='02'>Alwar</OPTION>" +	
	"<OPTION VALUE='03'>Banswara</OPTION>" +	
	"<OPTION VALUE='04'>Baran</OPTION>" +	
	"<OPTION VALUE='05'>Barmer</OPTION>" +	
	"<OPTION VALUE='06'>Bharatpur</OPTION>" +	
	"<OPTION VALUE='07'>Bhilwara</OPTION>" +	
	"<OPTION VALUE='08'>Bikaner</OPTION>" +	
	"<OPTION VALUE='09'>Bundi</OPTION>" +	
	"<OPTION VALUE='10'>Chittaurgarh</OPTION>" +	
	"<OPTION VALUE='11'>Churu</OPTION>" +	
	"<OPTION VALUE='12'>Dausa</OPTION>" +	
	"<OPTION VALUE='13'>Dhaulpur</OPTION>" +	
	"<OPTION VALUE='14'>Dungarpur</OPTION>" +	
	"<OPTION VALUE='15'>Ganganagar</OPTION>" +	
	"<OPTION VALUE='16'>Hanumangarh</OPTION>" +	
	"<OPTION VALUE='17'>Jaipur</OPTION>" +	
	"<OPTION VALUE='18'>Jaisalmer</OPTION>" +	
	"<OPTION VALUE='19'>Jalor</OPTION>" +	
	"<OPTION VALUE='20'>Jhalawar</OPTION>" +	
	"<OPTION VALUE='21'>Jodhpur</OPTION>" +	
	"<OPTION VALUE='22'>Jhunjhunun</OPTION>" +	
	"<OPTION VALUE='23'>Karauli</OPTION>" +	
	"<OPTION VALUE='24'>Kota</OPTION>" +	
	"<OPTION VALUE='25'>Nagaur</OPTION>" +	
	"<OPTION VALUE='26'>Pali</OPTION>" +	
	"<OPTION VALUE='27'>Rajsamand</OPTION>" +	
	"<OPTION VALUE='28'>Sawai Madhopur</OPTION>" +	
	"<OPTION VALUE='29'>Sikar</OPTION>" +	
	"<OPTION VALUE='30'>Sirohi</OPTION>" +	
	"<OPTION VALUE='31'>Tonk</OPTION>" +	
	"<OPTION VALUE='32'>Udaipur</OPTION>" +	
	
		"</select>";
	}
if(myState == "Sikkim")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>East</OPTION>" +	
	"<OPTION VALUE='02'>North</OPTION>" +	
	"<OPTION VALUE='03'>South</OPTION>" +	
	"<OPTION VALUE='04'>West</OPTION>" +	
	
		"</select>";
	}
if(myState == "Tamil Nadu")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='3156'>Chennai</OPTION>" +	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='01'>Chennai</OPTION>" +	
	"<OPTION VALUE='02'>Coimbatore</OPTION>" +	
	"<OPTION VALUE='03'>Cuddalore</OPTION>" +	
	"<OPTION VALUE='04'>Dharmapuri</OPTION>" +	
	"<OPTION VALUE='05'>Dindigul</OPTION>" +	
	"<OPTION VALUE='06'>Erode</OPTION>" +	
	"<OPTION VALUE='07'>Kancheepuram</OPTION>" +	
	"<OPTION VALUE='08'>Kanniyakumari</OPTION>" +	
	"<OPTION VALUE='09'>Kapur</OPTION>" +	
	"<OPTION VALUE='10'>Madurai</OPTION>" +	
	"<OPTION VALUE='11'>Nagapattinam</OPTION>" +	
	"<OPTION VALUE='12'>Namakkal</OPTION>" +	
	"<OPTION VALUE='13'>Perambalur</OPTION>" +	
	"<OPTION VALUE='14'>Pudukkottai</OPTION>" +	
	"<OPTION VALUE='15'>Ramanathapuram</OPTION>" +	
	"<OPTION VALUE='16'>Salem</OPTION>" +	
	"<OPTION VALUE='17'>Sivaganga</OPTION>" +	
	"<OPTION VALUE='18'>Tanjore</OPTION>" +	
	"<OPTION VALUE='19'>The Nilgiris</OPTION>" +	
	"<OPTION VALUE='20'>Theni</OPTION>" +	
	"<OPTION VALUE='21'>Thiruvallur</OPTION>" +	
	"<OPTION VALUE='22'>Thiruvarur</OPTION>" +	
	"<OPTION VALUE='23'>Toothukudi</OPTION>" +	
	"<OPTION VALUE='24'>Tiruchirappalli</OPTION>" +	
	"<OPTION VALUE='25'>Tirunelveli</OPTION>" +	
	"<OPTION VALUE='26'>Tiruvanamalai</OPTION>" +	
	"<OPTION VALUE='27'>Vellore</OPTION>" +	
	"<OPTION VALUE='28'>Viluppuram</OPTION>" +	
	"<OPTION VALUE='29'>Virudhunagar</OPTION>" +	
	
		"</select>";
	}
if(myState == "Tripura")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Dhalai</OPTION>" +	
	"<OPTION VALUE='02'>North Tripura</OPTION>" +	
	"<OPTION VALUE='03'>South Tripura</OPTION>" +	
	"<OPTION VALUE='05'>West Tripura</OPTION>" +	
	
		"</select>";
	}
if(myState == "Uttar Pradesh")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
				
	"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	
	"<OPTION VALUE='01'>Agra</OPTION>" +	
	"<OPTION VALUE='02'>Aligarh</OPTION>" +	
	"<OPTION VALUE='03'>Allahabad</OPTION>" +	
	"<OPTION VALUE='04'>Ambedaker Nagar</OPTION>" +	
	"<OPTION VALUE='05'>Auraiya</OPTION>" +	
	"<OPTION VALUE='06'>Azamgarh</OPTION>" +	
	"<OPTION VALUE='07'>Budaun</OPTION>" +	
	"<OPTION VALUE='08'>Baghpat</OPTION>" +	
	"<OPTION VALUE='09'>Bahraich</OPTION>" +	
	"<OPTION VALUE='10'>Ballia</OPTION>" +	
	"<OPTION VALUE='11'>Balrampur</OPTION>" +	
	"<OPTION VALUE='12'>Banda</OPTION>" +	
	"<OPTION VALUE='13'>Barabanki</OPTION>" +	
	"<OPTION VALUE='14'>Bareilly</OPTION>" +	
	"<OPTION VALUE='15'>Basti</OPTION>" +	
	"<OPTION VALUE='16'>Bijnor</OPTION>" +	
	"<OPTION VALUE='17'>Bulandshahr</OPTION>" +	
	"<OPTION VALUE='18'>Chandauli</OPTION>" +	
	"<OPTION VALUE='19'>Chitrakoot</OPTION>" +	
	"<OPTION VALUE='20'>Deoria</OPTION>" +	
	"<OPTION VALUE='21'>Etah</OPTION>" +	
	"<OPTION VALUE='22'>Etawah</OPTION>" +	
	"<OPTION VALUE='23'>Faizabad</OPTION>" +	
	"<OPTION VALUE='24'>Farrukhabad</OPTION>" +	
	"<OPTION VALUE='25'>Fatehpur</OPTION>" +	
	"<OPTION VALUE='26'>Firozabad</OPTION>" +	
	"<OPTION VALUE='27'>Gautam Buddha Nagar</OPTION>" +	
	"<OPTION VALUE='28'>Ghaziabad</OPTION>" +	
	"<OPTION VALUE='29'>Ghazipur</OPTION>" +	
	"<OPTION VALUE='30'>Gonda</OPTION>" +	
	"<OPTION VALUE='31'>Gorakhpur</OPTION>" +	
	"<OPTION VALUE='32'>Hamirpur</OPTION>" +	
	"<OPTION VALUE='33'>Hardoi</OPTION>" +	
	"<OPTION VALUE='34'>Jalaun</OPTION>" +	
	"<OPTION VALUE='35'>Jaunpur</OPTION>" +	
	"<OPTION VALUE='36'>Jhansi</OPTION>" +	
	"<OPTION VALUE='37'>Jyotiba Phule Nagar</OPTION>" +	
	"<OPTION VALUE='38'>Kannauj</OPTION>" +	
	"<OPTION VALUE='39'>Kanpur Dehat</OPTION>" +	
	"<OPTION VALUE='40'>Kanpur Nagar</OPTION>" +	
	"<OPTION VALUE='41'>Kaushambi</OPTION>" +	
	"<OPTION VALUE='42'>Kushinagar</OPTION>" +	
	"<OPTION VALUE='43'>Kheri</OPTION>" +	
	"<OPTION VALUE='44'>Lalitpur</OPTION>" +	
	"<OPTION VALUE='45'>Lucknow</OPTION>" +	
	"<OPTION VALUE='46'>Mahamaya Nagar</OPTION>" +	
	"<OPTION VALUE='47'>Maharajganj</OPTION>" +	
	"<OPTION VALUE='48'>Mahoba</OPTION>" +	
	"<OPTION VALUE='49'>Mainpuri</OPTION>" +	
	"<OPTION VALUE='50'>Mathura</OPTION>" +	
	"<OPTION VALUE='51'>Mau</OPTION>" +	
	"<OPTION VALUE='52'>Meerut</OPTION>" +	
	"<OPTION VALUE='53'>Mirzapur</OPTION>" +	
	"<OPTION VALUE='54'>Moradabad</OPTION>" +	
	"<OPTION VALUE='55'>Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='56'>Pilibhit</OPTION>" +	
	"<OPTION VALUE='57'>Pratapgarh</OPTION>" +	
	"<OPTION VALUE='58'>Rae Bareli</OPTION>" +	
	"<OPTION VALUE='59'>Rampur</OPTION>" +	
	"<OPTION VALUE='60'>Saharanpur</OPTION>" +	
	"<OPTION VALUE='61'>Sant Kabir Nagar</OPTION>" +	
	"<OPTION VALUE='62'>Sant Ravidas Nagar</OPTION>" +	
	"<OPTION VALUE='63'>Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='64'>Shrawasti</OPTION>" +	
	"<OPTION VALUE='65'>Siddharthnagar</OPTION>" +	
	"<OPTION VALUE='66'>Sitapur</OPTION>" +	
	"<OPTION VALUE='67'>Sonbhadra</OPTION>" +	
	"<OPTION VALUE='68'>Sultanpur</OPTION>" +	
	"<OPTION VALUE='69'>Unnao</OPTION>" +	
	"<OPTION VALUE='70'>Varanasi</OPTION>" +	
	
		"</select>";
	}
if(myState == "Uttaranchal")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Almora</OPTION>" +	
	"<OPTION VALUE='02'>Bageshwar</OPTION>" +	
	"<OPTION VALUE='03'>Chamoli</OPTION>" +	
	"<OPTION VALUE='04'>Champawat</OPTION>" +	
	"<OPTION VALUE='05'>Dehradun</OPTION>" +	
	"<OPTION VALUE='06'>Hardwar</OPTION>" +	
	"<OPTION VALUE='07'>Nainital</OPTION>" +	
	"<OPTION VALUE='08'>Garhwal</OPTION>" +	
	"<OPTION VALUE='09'>Pithoragarh</OPTION>" +	
	"<OPTION VALUE='10'>Rudraprayag</OPTION>" +	
	"<OPTION VALUE='11'>Tehri Garhwal</OPTION>" +	
	"<OPTION VALUE='12'>Udham Singh Nagar</OPTION>" +	
	"<OPTION VALUE='13'>Uttarkashi</OPTION>" +	
	
		"</select>";
	}
if(myState == "West Bengal")
	{
		city = "<select name='District'  class='text' onclick='checkStateSelection1()'>" +	
	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='01'>Bankura</OPTION>" +	
	"<OPTION VALUE='02'>Barddhaman</OPTION>" +	
	"<OPTION VALUE='03'>Birbhum</OPTION>" +	
	"<OPTION VALUE='04'>Koch Bihar</OPTION>" +	
	"<OPTION VALUE='05'>Dakshin Dinajpur</OPTION>" +	
	"<OPTION VALUE='06'>Darjiling</OPTION>" +	
	"<OPTION VALUE='07'>Hugli</OPTION>" +	
	"<OPTION VALUE='08'>Haora</OPTION>" +	
	"<OPTION VALUE='09'>Jalpaiguri</OPTION>" +	
	"<OPTION VALUE='10'>Kolkata</OPTION>" +	
	"<OPTION VALUE='11'>Maldah</OPTION>" +	
	"<OPTION VALUE='12'>Medinipur</OPTION>" +	
	"<OPTION VALUE='13'>Murshidabad</OPTION>" +	
	"<OPTION VALUE='14'>Nadia</OPTION>" +	
	"<OPTION VALUE='15'>North Twentyfour Parganas</OPTION>" +	
	"<OPTION VALUE='16'>Puruliya</OPTION>" +	
	"<OPTION VALUE='17'>South Twentyfour Parganas</OPTION>" +	
	"<OPTION VALUE='18'>Uttar Dinajpur</OPTION>" +	
	
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