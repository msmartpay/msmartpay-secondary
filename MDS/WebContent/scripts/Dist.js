function populatecities()
{				
	
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	var District;
	if(myState == "Andaman And Nicobar")
	{
	District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Andamans</OPTION>" +	
	"<OPTION VALUE='02'> Nicobar</OPTION>" +	
	
		"</select>";
	}
/*
	if(myState== "Others")
	{
		city="<input name='city' class='login' type='login' />";
	}
*/
	if(myState == "-1")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +	
	"</select>";
	}

if(myState == "Andhra Pradesh")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +		
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +				
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
	if(myState == "Delhi")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='567'>Delhi</OPTION>" +
		"</select>";
	}
if(myState == "Chandigarh")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='476'>Chandigarh</OPTION>" +
		"</select>";
	}
if(myState == "Chhattisgarh")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='561'>Amli</OPTION>" +	
	"<OPTION VALUE='562'>Naroli</OPTION>" +	
	"<OPTION VALUE='563'>Silvassa</OPTION>" +
		"</select>";
	}


if(myState == "Goa")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>North Goa</OPTION>" +	
	"<OPTION VALUE='02'>South Goa</OPTION>" +	
	
		"</select>";
	}
if(myState == "Gujarat")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
													
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='1676'>Amini</OPTION>" +	
	"<OPTION VALUE='1677'>Kavaratti</OPTION>" +	
	"<OPTION VALUE='1678'>Minicoy</OPTION>" +
		"</select>";
	}
if(myState == "Madhya Pradesh")
	{
		District = "<select name='District' style='width: 150px; height: 18px;font:Verdana,Arial,Helvetica,sans-serif; font-size:9px;' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
			
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Karaikal </OPTION>" +	
	"<OPTION VALUE='02'>Mahe </OPTION>" +	
	"<OPTION VALUE='03'>Pondicherry</OPTION>" +	
	"<OPTION VALUE='04'>Yanam </OPTION>" +
		"</select>";
	}
if(myState == "Punjab")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>East</OPTION>" +	
	"<OPTION VALUE='02'>North</OPTION>" +	
	"<OPTION VALUE='03'>South</OPTION>" +	
	"<OPTION VALUE='04'>West</OPTION>" +	
	
		"</select>";
	}
if(myState == "Tamil Nadu")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select District ----------</OPTION>" +
	"<OPTION VALUE='01'>Dhalai</OPTION>" +	
	"<OPTION VALUE='02'>North Tripura</OPTION>" +	
	"<OPTION VALUE='03'>South Tripura</OPTION>" +	
	"<OPTION VALUE='05'>West Tripura</OPTION>" +	
	
		"</select>";
	}
if(myState == "Uttar Pradesh")
	{
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
				
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
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
		District = "<select name='District'  class='login' onclick='checkStateSelection1()'>" +	
	
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