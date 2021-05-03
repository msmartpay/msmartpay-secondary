function populatecities()
{				
	
	var myForm = document.regiestration;
	var myState = myForm.state.value;
	var city;
	if(myState == "Andaman And Nicobar")
	{
	city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Andamans'>Andamans</OPTION>" +	
	"<OPTION VALUE='Nicobar'> Nicobar</OPTION>" +	
	
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
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +	
	"</select>";
	}

if(myState == "Andhra Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +		
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +		
	"<OPTION VALUE='Adilabad'>Adilabad</OPTION>" +	
	"<OPTION VALUE='Anantapur'>Anantapur</OPTION>" +	
	"<OPTION VALUE='Chittoor'>Chittoor</OPTION>" +	
	"<OPTION VALUE='East Godavari'>East Godavari</OPTION>" +	
	"<OPTION VALUE='Guntur'>Guntur</OPTION>" +	
	"<OPTION VALUE='Hyderabad'>Hyderabad</OPTION>" +	
	"<OPTION VALUE='Cuddapah'>Cuddapah</OPTION>" +	
	"<OPTION VALUE='Karimnagar'>Karimnagar</OPTION>" +	
	"<OPTION VALUE='Khammam'>Khammam</OPTION>" +	
	"<OPTION VALUE='Krishna'>Krishna</OPTION>" +	
	"<OPTION VALUE='Kurnool'>Kurnool</OPTION>" +	
	"<OPTION VALUE='Mahbubnagar'>Mahbubnagar</OPTION>" +	
	"<OPTION VALUE='Medak'>Medak</OPTION>" +	
	"<OPTION VALUE='Nalgonda'>Nalgonda</OPTION>" +	
	"<OPTION VALUE='Nellore'>Nellore</OPTION>" +	
	"<OPTION VALUE='Nizamabad'>Nizamabad</OPTION>" +	
	"<OPTION VALUE='Prakasam'>Prakasam</OPTION>" +	
	"<OPTION VALUE='Rangareddi'>Rangareddi</OPTION>" +	
	"<OPTION VALUE='Srikakulam'>Srikakulam</OPTION>" +	
	"<OPTION VALUE='Visakhapatnam'>Visakhapatnam</OPTION>" +	
	"<OPTION VALUE='Vizianagaram'>Vizianagaram</OPTION>" +	
	"<OPTION VALUE='Warangal'>Warangal</OPTION>" +	
	"<OPTION VALUE='West Godavari'>West Godavari</OPTION>" +	
	
		"</select>";
	}
if(myState == "Arunachal Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +				
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +	
	"<OPTION VALUE='Changlang'>Changlang</OPTION>" +	
	"<OPTION VALUE='Dibang Valley'>Dibang Valley</OPTION>" +	
	"<OPTION VALUE='East Kameng'>East Kameng</OPTION>" +	
	"<OPTION VALUE='East Siang'>East Siang</OPTION>" +	
	"<OPTION VALUE='Lohit'>Lohit</OPTION>" +	
	"<OPTION VALUE='Lower Subansiri'>Lower Subansiri</OPTION>" +	
	"<OPTION VALUE='Papum Pare'>Papum Pare</OPTION>" +	
	"<OPTION VALUE='Tawang'>Tawang</OPTION>" +	
	"<OPTION VALUE='Tirap'>Tirap</OPTION>" +	
	"<OPTION VALUE='Upper Siang'>Upper Siang</OPTION>" +	
	"<OPTION VALUE='Upper Subansiri'>Upper Subansiri</OPTION>" +	
	"<OPTION VALUE='West Kameng'>West Kameng</OPTION>" +	
	"<OPTION VALUE='West Siang'>West Siang</OPTION>" +	
	
	"</select>";
	}
if(myState == "Assam")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Barpeta'>Barpeta</OPTION>" +	
	"<OPTION VALUE='Bongaigaon'>Bongaigaon</OPTION>" +	
	"<OPTION VALUE='Cachar'>Cachar</OPTION>" +	
	"<OPTION VALUE='Darrang'>Darrang</OPTION>" +	
	"<OPTION VALUE='Dhemaji'>Dhemaji</OPTION>" +	
	"<OPTION VALUE='Dhubri'>Dhubri</OPTION>" +	
	"<OPTION VALUE='Dibrugarh'>Dibrugarh</OPTION>" +	
	"<OPTION VALUE='Goalpara'>Goalpara</OPTION>" +	
	"<OPTION VALUE='Golaghat'>Golaghat</OPTION>" +	
	"<OPTION VALUE='Hailakandi'>Hailakandi</OPTION>" +	
	"<OPTION VALUE='Jorhat'>Jorhat</OPTION>" +	
	"<OPTION VALUE='Karbi Anglong'>Karbi Anglong</OPTION>" +	
	"<OPTION VALUE='Karimganj'>Karimganj</OPTION>" +	
	"<OPTION VALUE='Kokrajhar'>Kokrajhar</OPTION>" +	
	"<OPTION VALUE='Lakhimpur'>Lakhimpur</OPTION>" +	
	"<OPTION VALUE='Marigaon'>Marigaon</OPTION>" +	
	"<OPTION VALUE='Nagaon'>Nagaon</OPTION>" +	
	"<OPTION VALUE='Nalbari'>Nalbari</OPTION>" +	
	"<OPTION VALUE='North Cachar Hills'>North Cachar Hills</OPTION>" +	
	"<OPTION VALUE='Sibsagar'>Sibsagar</OPTION>" +	
	"<OPTION VALUE='Sonitpur'>Sonitpur</OPTION>" +	
	"<OPTION VALUE='Tinsukia'>Tinsukia</OPTION>" +	
	
		"</select>";
	}
if(myState == "Bihar")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Araria'>Araria</OPTION>" +	
	"<OPTION VALUE='Aurangabad'>Aurangabad</OPTION>" +	
	"<OPTION VALUE='Banka'>Banka</OPTION>" +	
	"<OPTION VALUE='Begusarai'>Begusarai</OPTION>" +	
	"<OPTION VALUE='Bhagalpur'>Bhagalpur</OPTION>" +	
	"<OPTION VALUE='Bhojpur'>Bhojpur</OPTION>" +	
	"<OPTION VALUE='Buxar'>Buxar</OPTION>" +	
	"<OPTION VALUE='Darbhanga'>Darbhanga</OPTION>" +	
	"<OPTION VALUE='Gaya'>Gaya</OPTION>" +	
	"<OPTION VALUE='Gopalganj'>Gopalganj</OPTION>" +	
	"<OPTION VALUE='Jamui'>Jamui</OPTION>" +	
	"<OPTION VALUE='Jehanabad'>Jehanabad</OPTION>" +	
	"<OPTION VALUE='Kaimur'>Kaimur (Bhabua)</OPTION>" +	
	"<OPTION VALUE='Katihar'>Katihar</OPTION>" +	
	"<OPTION VALUE='Khagaria'>Khagaria</OPTION>" +	
	"<OPTION VALUE='Kishanganj'>Kishanganj</OPTION>" +	
	"<OPTION VALUE='Lakhisarai'>Lakhisarai</OPTION>" +	
	"<OPTION VALUE='Madhepura'>Madhepura</OPTION>" +	
	"<OPTION VALUE='Madhubani'>Madhubani</OPTION>" +	
	"<OPTION VALUE='Munger'>Munger</OPTION>" +	
	"<OPTION VALUE='Muzaffarpur'>Muzaffarpur</OPTION>" +	
	"<OPTION VALUE='Nalanda'>Nalanda</OPTION>" +	
	"<OPTION VALUE='Nawada'>Nawada</OPTION>" +	
	"<OPTION VALUE='Pashchim Champaran'>Pashchim Champaran</OPTION>" +	
	"<OPTION VALUE='Patna'>Patna</OPTION>" +	
	"<OPTION VALUE='Purba Champaran'>Purba Champaran</OPTION>" +	
	"<OPTION VALUE='Purnia'>Purnia</OPTION>" +	
	"<OPTION VALUE='Rohtas'>Rohtas</OPTION>" +	
	"<OPTION VALUE='Saharsa'>Saharsa</OPTION>" +	
	"<OPTION VALUE='Samastipur'>Samastipur</OPTION>" +	
	"<OPTION VALUE='Saran'>Saran</OPTION>" +	
	"<OPTION VALUE='Sheikhpura'>Sheikhpura</OPTION>" +	
	"<OPTION VALUE='Sheohar'>Sheohar</OPTION>" +	
	"<OPTION VALUE='Sitamarhi'>Sitamarhi</OPTION>" +	
	"<OPTION VALUE='Siwan'>Siwan</OPTION>" +	
	"<OPTION VALUE='Supaul'>Supaul</OPTION>" +	
	"<OPTION VALUE='Vaishali'>Vaishali</OPTION>" +	
	
		"</select>";
	}
if(myState == "Chandigarh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Chandigarh'>Mohali</OPTION>" +
		"</select>";
	}
if(myState == "Chhattisgarh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Bastar'>Bastar</OPTION>" +	
	"<OPTION VALUE='Bilaspur'>Bilaspur</OPTION>" +	
	"<OPTION VALUE='Dantewada'>Dantewada</OPTION>" +	
	"<OPTION VALUE='Dhamtari'>Dhamtari</OPTION>" +	
	"<OPTION VALUE='Durg'>Durg</OPTION>" +	
	"<OPTION VALUE='Janjgir'>Janjgir-Champa</OPTION>" +	
	"<OPTION VALUE='Jashpur'>Jashpur</OPTION>" +	
	"<OPTION VALUE='Kanker'>Kanker</OPTION>" +	
	"<OPTION VALUE='Kawardha'>Kawardha</OPTION>" +	
	"<OPTION VALUE='Korba'>Korba</OPTION>" +	
	"<OPTION VALUE='Koriya'>Koriya</OPTION>" +	
	"<OPTION VALUE='Mahasamund'>Mahasamund</OPTION>" +	
	"<OPTION VALUE='Raigarh'>Raigarh</OPTION>" +	
	"<OPTION VALUE='Raipur'>Raipur</OPTION>" +	
	"<OPTION VALUE='Rajnandgaon'>Rajnandgaon</OPTION>" +	
	"<OPTION VALUE='Surguja'>Surguja</OPTION>" +	
	
		"</select>";
	}
	if(myState == "Delhi")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='Delhi'>Delhi</OPTION>" +	
	
		"</select>";
	}
if(myState == "Dadra and Nagar Haveli")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select City ----------</OPTION>" +
	"<OPTION VALUE='Amli'>Amli</OPTION>" +	
	"<OPTION VALUE='Naroli'>Naroli</OPTION>" +	
	"<OPTION VALUE='Silvassa'>Silvassa</OPTION>" +
		"</select>";
	}


if(myState == "Goa")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='North Goa'>North Goa</OPTION>" +	
	"<OPTION VALUE='South Goa'>South Goa</OPTION>" +	
	
		"</select>";
	}
if(myState == "Gujarat")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Ahmadabad'>Ahmadabad</OPTION>" +	
	"<OPTION VALUE='Amreli'>Amreli</OPTION>" +	
	"<OPTION VALUE='Anand'>Anand</OPTION>" +	
	"<OPTION VALUE='Banas Kantha'>Banas Kantha</OPTION>" +	
	"<OPTION VALUE='Bharuch'>Bharuch</OPTION>" +	
	"<OPTION VALUE='Bhavnagar'>Bhavnagar</OPTION>" +	
	"<OPTION VALUE='Dohad'>Dohad</OPTION>" +	
	"<OPTION VALUE='Gandhinagar'>Gandhinagar</OPTION>" +	
	"<OPTION VALUE='Jamnagar'>Jamnagar</OPTION>" +	
	"<OPTION VALUE='Junagadh'>Junagadh</OPTION>" +	
	"<OPTION VALUE='Kheda'>Kheda</OPTION>" +	
	"<OPTION VALUE='Kachchh'>Kachchh</OPTION>" +	
	"<OPTION VALUE='Mahesana'>Mahesana</OPTION>" +	
	"<OPTION VALUE='Narmada'>Narmada</OPTION>" +	
	"<OPTION VALUE='Navsari'>Navsari</OPTION>" +	
	"<OPTION VALUE='Panch Mahals'>Panch Mahals</OPTION>" +	
	"<OPTION VALUE='Patan'>Patan</OPTION>" +	
	"<OPTION VALUE='Porbandar'>Porbandar</OPTION>" +	
	"<OPTION VALUE='Rajkot'>Rajkot</OPTION>" +	
	"<OPTION VALUE='Sabar Kantha'>Sabar Kantha</OPTION>" +	
	"<OPTION VALUE='Surat'>Surat</OPTION>" +	
	"<OPTION VALUE='Surendranagar'>Surendranagar</OPTION>" +	
	"<OPTION VALUE='The Dangs'>The Dangs</OPTION>" +	
	"<OPTION VALUE='Vadodara'>Vadodara</OPTION>" +	
	"<OPTION VALUE='Valsad'>Valsad</OPTION>" +	
	
		"</select>";
	}
if(myState == "Haryana")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='925'>Gurgaon</OPTION>" +	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='Ambala'>Ambala</OPTION>" +	
	"<OPTION VALUE='Bhiwani'>Bhiwani</OPTION>" +	
	"<OPTION VALUE='Faridabad'>Faridabad</OPTION>" +	
	"<OPTION VALUE='Fatehabad'>Fatehabad</OPTION>" +	
	"<OPTION VALUE='Gurgaon'>Gurgaon</OPTION>" +	
	"<OPTION VALUE='Hisar'>Hisar</OPTION>" +	
	"<OPTION VALUE='Jhajjar'>Jhajjar</OPTION>" +	
	"<OPTION VALUE='Jind'>Jind</OPTION>" +	
	"<OPTION VALUE='Kaithal'>Kaithal</OPTION>" +	
	"<OPTION VALUE='Karnal'>Karnal</OPTION>" +	
	"<OPTION VALUE='Kurukshetra'>Kurukshetra</OPTION>" +	
	"<OPTION VALUE='Mahendragarh'>Mahendragarh</OPTION>" +	
	"<OPTION VALUE='Panchkula'>Panchkula</OPTION>" +	
	"<OPTION VALUE='Panipat'>Panipat</OPTION>" +	
	"<OPTION VALUE='Rewari'>Rewari</OPTION>" +	
	"<OPTION VALUE='Rohtak'>Rohtak</OPTION>" +	
	"<OPTION VALUE='Sirsa'>Sirsa</OPTION>" +	
	"<OPTION VALUE='Sonipat'>Sonipat</OPTION>" +	
	"<OPTION VALUE='Yamunanagar'>Yamunanagar</OPTION>" +	
	
		"</select>";
	}
if(myState == "Himachal Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Bilaspur'>Bilaspur</OPTION>" +	
	"<OPTION VALUE='Chamba'>Chamba</OPTION>" +	
	"<OPTION VALUE='Hamirpur'>Hamirpur</OPTION>" +	
	"<OPTION VALUE='Kangra'>Kangra</OPTION>" +	
	"<OPTION VALUE='Kinnaur'>Kinnaur</OPTION>" +	
	"<OPTION VALUE='Kullu'>Kullu</OPTION>" +	
	"<OPTION VALUE='Lahaul and Spiti'>Lahaul and Spiti</OPTION>" +	
	"<OPTION VALUE='Mandi'>Mandi</OPTION>" +	
	"<OPTION VALUE='Shimla'>Shimla</OPTION>" +	
	"<OPTION VALUE='Sirmaur'>Sirmaur</OPTION>" +	
	"<OPTION VALUE='Solan'>Solan</OPTION>" +	
	"<OPTION VALUE='Una'>Una</OPTION>" +	
	
		"</select>";
	}
if(myState == "Jammu and Kashmir")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	
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
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Bokaro'>Bokaro</OPTION>" +	
	"<OPTION VALUE='Chatra'>Chatra</OPTION>" +	
	"<OPTION VALUE='Deoghar'>Deoghar</OPTION>" +	
	"<OPTION VALUE='Dhanbad'>Dhanbad</OPTION>" +	
	"<OPTION VALUE='Dumka'>Dumka</OPTION>" +	
	"<OPTION VALUE='Garhwa'>Garhwa</OPTION>" +	
	"<OPTION VALUE='Giridih'>Giridih</OPTION>" +	
	"<OPTION VALUE='Godda'>Godda</OPTION>" +	
	"<OPTION VALUE='Gumla'>Gumla</OPTION>" +	
	"<OPTION VALUE='Hazaribag'>Hazaribag</OPTION>" +	
	"<OPTION VALUE='Kodarma'>Kodarma</OPTION>" +	
	"<OPTION VALUE='Lohardaga'>Lohardaga</OPTION>" +	
	"<OPTION VALUE='Pakaur'>Pakaur</OPTION>" +	
	"<OPTION VALUE='Palamu'>Palamu</OPTION>" +	
	"<OPTION VALUE='Pashchimi Singhbhum'>Pashchimi Singhbhum</OPTION>" +	
	"<OPTION VALUE='Purbi Singhbhum'>Purbi Singhbhum</OPTION>" +	
	"<OPTION VALUE='Ranchi'>Ranchi</OPTION>" +	
	"<OPTION VALUE='Sahibganj'>Sahibganj</OPTION>" +	
	
		"</select>";
	}
if(myState == "Karnataka")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
													
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='Bagalkot'>Bagalkot</OPTION>" +	
	"<OPTION VALUE='Bangalore'>Bangalore</OPTION>" +	
	"<OPTION VALUE='Bangalore Rural'>Bangalore Rural</OPTION>" +	
	"<OPTION VALUE='Belgaum'>Belgaum</OPTION>" +	
	"<OPTION VALUE='Bellary'>Bellary</OPTION>" +	
	"<OPTION VALUE='Bidar'>Bidar</OPTION>" +	
	"<OPTION VALUE='Bijapur'>Bijapur</OPTION>" +	
	"<OPTION VALUE='Chamarajanagar'>Chamarajanagar</OPTION>" +	
	"<OPTION VALUE='Chikmagalur'>Chikmagalur</OPTION>" +	
	"<OPTION VALUE='Chitradurga'>Chitradurga</OPTION>" +	
	"<OPTION VALUE='Dakshina Kannada'>Dakshina Kannada</OPTION>" +	
	"<OPTION VALUE='Davanagere'>Davanagere</OPTION>" +	
	"<OPTION VALUE='Dharwad'>Dharwad</OPTION>" +	
	"<OPTION VALUE='Gadag'>Gadag</OPTION>" +	
	"<OPTION VALUE='Gulbarga'>Gulbarga</OPTION>" +	
	"<OPTION VALUE='Hassan'>Hassan</OPTION>" +	
	"<OPTION VALUE='Haveri'>Haveri</OPTION>" +	
	"<OPTION VALUE='Kodagu'>Kodagu</OPTION>" +	
	"<OPTION VALUE='Kolar'>Kolar</OPTION>" +	
	"<OPTION VALUE='Koppal'>Koppal</OPTION>" +	
	"<OPTION VALUE='Mandya'>Mandya</OPTION>" +	
	"<OPTION VALUE='Mysore'>Mysore</OPTION>" +	
	"<OPTION VALUE='Raichur'>Raichur</OPTION>" +	
	"<OPTION VALUE='Shimoga'>Shimoga</OPTION>" +	
	"<OPTION VALUE='Tumkur'>Tumkur</OPTION>" +	
	"<OPTION VALUE='Udupi'>Udupi</OPTION>" +	
	"<OPTION VALUE='Uttara Kannada'>Uttara Kannada</OPTION>" +	
	
		"</select>";
	}
if(myState == "Kerala")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Alappuzha'>Alappuzha</OPTION>" +	
	"<OPTION VALUE='Ernakulam'>Ernakulam</OPTION>" +	
	"<OPTION VALUE='Idukki'>Idukki</OPTION>" +	
	"<OPTION VALUE='Kannur'>Kannur</OPTION>" +	
	"<OPTION VALUE='Kasaragod'>Kasaragod</OPTION>" +	
	"<OPTION VALUE='Kollam'>Kollam</OPTION>" +	
	"<OPTION VALUE='Kottayam'>Kottayam</OPTION>" +	
	"<OPTION VALUE='Kozhikode'>Kozhikode</OPTION>" +	
	"<OPTION VALUE='Malappuram'>Malappuram</OPTION>" +	
	"<OPTION VALUE='Palakkad'>Palakkad</OPTION>" +	
	"<OPTION VALUE='Pathanamthitta'>Pathanamthitta</OPTION>" +	
	"<OPTION VALUE='Thiruvananthapuram'>Thiruvananthapuram</OPTION>" +	
	"<OPTION VALUE='Thrissur'>Thrissur</OPTION>" +	
	"<OPTION VALUE='Wayanad'>Wayanad</OPTION>" +	
	
		"</select>";
	}
if(myState == "Lakshadweep")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Amini'>Amini</OPTION>" +	
	"<OPTION VALUE='Kavaratti'>Kavaratti</OPTION>" +	
	"<OPTION VALUE='Minicoy'>Minicoy</OPTION>" +
		"</select>";
	}
if(myState == "Madhya Pradesh")
	{
		city = "<select name='city' style='width: 150px; height: 18px;font:Verdana,Arial,Helvetica,sans-serif; font-size:9px;' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Ashoknagar'>Ashoknagar</OPTION>" +	
	"<OPTION VALUE='Balaghat'>Balaghat</OPTION>" +	
	"<OPTION VALUE='Barwani'>Barwani</OPTION>" +	
	"<OPTION VALUE='Betul'>Betul</OPTION>" +	
	"<OPTION VALUE='Bhind'>Bhind</OPTION>" +	
	"<OPTION VALUE='Bhopal'>Bhopal</OPTION>" +	
	"<OPTION VALUE='Chhatarpur'>Chhatarpur</OPTION>" +	
	"<OPTION VALUE='Chhindwara'>Chhindwara</OPTION>" +	
	"<OPTION VALUE='Damoh'>Damoh</OPTION>" +	
	"<OPTION VALUE='Datia'>Datia</OPTION>" +	
	"<OPTION VALUE='Dewas'>Dewas</OPTION>" +	
	"<OPTION VALUE='Dhar'>Dhar</OPTION>" +	
	"<OPTION VALUE='Dindori'>Dindori</OPTION>" +	
	"<OPTION VALUE='Guna'>Guna</OPTION>" +	
	"<OPTION VALUE='Gwalior'>Gwalior</OPTION>" +	
	"<OPTION VALUE='Harda'>Harda</OPTION>" +	
	"<OPTION VALUE='Hoshangabad'>Hoshangabad</OPTION>" +	
	"<OPTION VALUE='Indore'>Indore</OPTION>" +	
	"<OPTION VALUE='Jabalpur'>Jabalpur</OPTION>" +	
	"<OPTION VALUE='Jhabua'>Jhabua</OPTION>" +	
	"<OPTION VALUE='Katni'>Katni</OPTION>" +	
	"<OPTION VALUE='East Nimar'>East Nimar</OPTION>" +	
	"<OPTION VALUE='West Nimar'>West Nimar</OPTION>" +	
	"<OPTION VALUE='Mandla'>Mandla</OPTION>" +	
	"<OPTION VALUE='Mandsaur'>Mandsaur</OPTION>" +	
	"<OPTION VALUE='Morena'>Morena</OPTION>" +	
	"<OPTION VALUE='Narsimhapur'>Narsimhapur</OPTION>" +	
	"<OPTION VALUE='Neemuch'>Neemuch</OPTION>" +	
	"<OPTION VALUE='Panna'>Panna</OPTION>" +	
	"<OPTION VALUE='Raisen'>Raisen</OPTION>" +	
	"<OPTION VALUE='Rajgarh'>Rajgarh</OPTION>" +	
	"<OPTION VALUE='Ratlam'>Ratlam</OPTION>" +	
	"<OPTION VALUE='Rewa'>Rewa</OPTION>" +	
	"<OPTION VALUE='Sagar'>Sagar</OPTION>" +	
	"<OPTION VALUE='Satna'>Satna</OPTION>" +	
	"<OPTION VALUE='Sehore'>Sehore</OPTION>" +	
	"<OPTION VALUE='Seoni'>Seoni</OPTION>" +	
	"<OPTION VALUE='Shahdol'>Shahdol</OPTION>" +	
	"<OPTION VALUE='Shajapur'>Shajapur</OPTION>" +	
	"<OPTION VALUE='Sheopur'>Sheopur</OPTION>" +	
	"<OPTION VALUE='Shivpuri'>Shivpuri</OPTION>" +	
	"<OPTION VALUE='Sidhi'>Sidhi</OPTION>" +	
	"<OPTION VALUE='Tikamgarh'>Tikamgarh</OPTION>" +	
	"<OPTION VALUE='Ujjain'>Ujjain</OPTION>" +	
	"<OPTION VALUE='Umaria'>Umaria</OPTION>" +	
	"<OPTION VALUE='Vidisha'>Vidisha</OPTION>" +	
	
		"</select>";
	}
if(myState == "Maharashtra")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
			
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	
	"<OPTION VALUE='Ahmadnagar'>Ahmadnagar</OPTION>" +	
	"<OPTION VALUE='Akola'>Akola</OPTION>" +	
	"<OPTION VALUE='Amravati'>Amravati</OPTION>" +	
	"<OPTION VALUE='Aurangabad'>Aurangabad</OPTION>" +	
	"<OPTION VALUE='Bid'>Bid</OPTION>" +	
	"<OPTION VALUE='Bhandara'>Bhandara</OPTION>" +	
	"<OPTION VALUE='Buldana'>Buldana</OPTION>" +	
	"<OPTION VALUE='Chandrapur'>Chandrapur</OPTION>" +	
	"<OPTION VALUE='Dhule'>Dhule</OPTION>" +	
	"<OPTION VALUE='Gadchiroli'>Gadchiroli</OPTION>" +	
	"<OPTION VALUE='Gondiya'>Gondiya</OPTION>" +	
	"<OPTION VALUE='Hingoli'>Hingoli</OPTION>" +	
	"<OPTION VALUE='Jalgaon'>Jalgaon</OPTION>" +	
	"<OPTION VALUE='Jalna'>Jalna</OPTION>" +	
	"<OPTION VALUE='Kolhapur'>Kolhapur</OPTION>" +	
	"<OPTION VALUE='Latur'>Latur</OPTION>" +	
	"<OPTION VALUE='Mumbai City'>Mumbai City</OPTION>" +	
	"<OPTION VALUE='Mumbai suburban'>Mumbai suburban</OPTION>" +	
	"<OPTION VALUE='Nagpur'>Nagpur</OPTION>" +	
	"<OPTION VALUE='Nanded'>Nanded</OPTION>" +	
	"<OPTION VALUE='Nandurbar'>Nandurbar</OPTION>" +	
	"<OPTION VALUE='Nashik'>Nashik</OPTION>" +	
	"<OPTION VALUE='Osmanabad'>Osmanabad</OPTION>" +	
	"<OPTION VALUE='Parbhani'>Parbhani</OPTION>" +	
	"<OPTION VALUE='Pune'>Pune</OPTION>" +	
	"<OPTION VALUE='Raigad'>Raigad</OPTION>" +	
	"<OPTION VALUE='Ratnagiri'>Ratnagiri</OPTION>" +	
	"<OPTION VALUE='Sangli'>Sangli</OPTION>" +	
	"<OPTION VALUE='Satara'>Satara</OPTION>" +	
	"<OPTION VALUE='Sindhudurg'>Sindhudurg</OPTION>" +	
	"<OPTION VALUE='Solapur'>Solapur</OPTION>" +	
	"<OPTION VALUE='Thane'>Thane</OPTION>" +	
	"<OPTION VALUE='Wardha'>Wardha</OPTION>" +	
	"<OPTION VALUE='Washim'>Washim</OPTION>" +	
	"<OPTION VALUE='Yavatmal'>Yavatmal</OPTION>" +	
	
		"</select>";
	}
if(myState == "Manipur")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Bishnupur'>Bishnupur</OPTION>" +	
	"<OPTION VALUE='Chandel'>Chandel</OPTION>" +	
	"<OPTION VALUE='Churachandpur'>Churachandpur</OPTION>" +	
	"<OPTION VALUE='Imphal East'>Imphal East</OPTION>" +	
	"<OPTION VALUE='Imphal West'>Imphal West</OPTION>" +	
	"<OPTION VALUE='Senapati'>Senapati</OPTION>" +	
	"<OPTION VALUE='Tamenglong'>Tamenglong</OPTION>" +	
	"<OPTION VALUE='Thoubal'>Thoubal</OPTION>" +	
	"<OPTION VALUE='Ukhrul'>Ukhrul</OPTION>" +	
	
		"</select>";
	}
if(myState == "Meghalaya")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='East Garo Hills'>East Garo Hills</OPTION>" +	
	"<OPTION VALUE='East Khasi Hills'>East Khasi Hills</OPTION>" +	
	"<OPTION VALUE='Jaintia Hills'>Jaintia Hills</OPTION>" +	
	"<OPTION VALUE='Ri Bhoi'>Ri Bhoi</OPTION>" +	
	"<OPTION VALUE='South Garo Hills'>South Garo Hills</OPTION>" +	
	"<OPTION VALUE='West Garo Hills'>West Garo Hills</OPTION>" +	
	"<OPTION VALUE='West Khasi Hills'>West Khasi Hills</OPTION>" +	
	
		"</select>";
	}
if(myState == "Mizoram")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Aizawl'>Aizawl</OPTION>" +	
	"<OPTION VALUE='Champhai'>Champhai</OPTION>" +	
	"<OPTION VALUE='Kolasib'>Kolasib</OPTION>" +	
	"<OPTION VALUE='Lawngtlai'>Lawngtlai</OPTION>" +	
	"<OPTION VALUE='Lunglei'>Lunglei</OPTION>" +	
	"<OPTION VALUE='Mamit'>Mamit</OPTION>" +	
	"<OPTION VALUE='Saiha'>Saiha</OPTION>" +	
	"<OPTION VALUE='Serchhip'>Serchhip</OPTION>" +	
	
		"</select>";
	}
if(myState == "Nagaland")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Mon'>Mon</OPTION>" +	
	"<OPTION VALUE='Dimapur'>Dimapur</OPTION>" +	
	"<OPTION VALUE='Kohima'>Kohima</OPTION>" +	
	"<OPTION VALUE='Mokokchung'>Mokokchung</OPTION>" +	
	"<OPTION VALUE='Phek'>Phek</OPTION>" +	
	"<OPTION VALUE='Tuensang'>Tuensang</OPTION>" +	
	"<OPTION VALUE='Wokha'>Wokha</OPTION>" +	
	"<OPTION VALUE='Zunheboto'>Zunheboto</OPTION>" +
		"</select>";
	}
if(myState == "Orissa")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	
	"<OPTION VALUE='Anugul'>Anugul</OPTION>" +	
	"<OPTION VALUE='Baleshwar'>Baleshwar</OPTION>" +	
	"<OPTION VALUE='Bargarh'>Bargarh</OPTION>" +	
	"<OPTION VALUE='Bhadrak'>Bhadrak</OPTION>" +	
	"<OPTION VALUE='Balangir'>Balangir</OPTION>" +	
	"<OPTION VALUE='Baudh'>Baudh</OPTION>" +	
	"<OPTION VALUE='Cuttack'>Cuttack</OPTION>" +	
	"<OPTION VALUE='Debagarh'>Debagarh</OPTION>" +	
	"<OPTION VALUE='Dhenkanal'>Dhenkanal</OPTION>" +	
	"<OPTION VALUE='Gajapati'>Gajapati</OPTION>" +	
	"<OPTION VALUE='Ganjam'>Ganjam</OPTION>" +	
	"<OPTION VALUE='Jagatsinghapur'>Jagatsinghapur</OPTION>" +	
	"<OPTION VALUE='Jajapur'>Jajapur</OPTION>" +	
	"<OPTION VALUE='Jharsuguda'>Jharsuguda</OPTION>" +	
	"<OPTION VALUE='Kalahandi'>Kalahandi</OPTION>" +	
	"<OPTION VALUE='Kandhamal'>Kandhamal</OPTION>" +	
	"<OPTION VALUE='Kendrapara'>Kendrapara</OPTION>" +	
	"<OPTION VALUE='Kendujhar'>Kendujhar</OPTION>" +	
	"<OPTION VALUE='Khordha'>Khordha</OPTION>" +	
	"<OPTION VALUE='Koraput'>Koraput</OPTION>" +	
	"<OPTION VALUE='Malkangiri'>Malkangiri</OPTION>" +	
	"<OPTION VALUE='Mayurbhanj'>Mayurbhanj</OPTION>" +	
	"<OPTION VALUE='Nabarangapur'>Nabarangapur</OPTION>" +	
	"<OPTION VALUE='Nayagarh'>Nayagarh</OPTION>" +	
	"<OPTION VALUE='Nuapada'>Nuapada</OPTION>" +	
	"<OPTION VALUE='Puri'>Puri</OPTION>" +	
	"<OPTION VALUE='Rayagada'>Rayagada</OPTION>" +	
	"<OPTION VALUE='Sambalpur'>Sambalpur</OPTION>" +	
	"<OPTION VALUE='Sonapur'>Sonapur</OPTION>" +	
	"<OPTION VALUE='Sundargarh'>Sundargarh</OPTION>" +	
	
		"</select>";
	}
if(myState == "Pondicherry")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Karaikal'>Karaikal </OPTION>" +	
	"<OPTION VALUE='Mahe'>Mahe </OPTION>" +	
	"<OPTION VALUE='Pondicherry'>Pondicherry</OPTION>" +	
	"<OPTION VALUE='Yanam'>Yanam </OPTION>" +
		"</select>";
	}
if(myState == "Punjab")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Amritsar'>Amritsar</OPTION>" +	
	"<OPTION VALUE='Bathinda'>Bathinda</OPTION>" +	
	"<OPTION VALUE='Faridkot'>Faridkot</OPTION>" +	
	"<OPTION VALUE='Fatehgarh Sahib'>Fatehgarh Sahib</OPTION>" +	
	"<OPTION VALUE='Firozpur'>Firozpur</OPTION>" +	
	"<OPTION VALUE='Gurdaspur'>Gurdaspur</OPTION>" +	
	"<OPTION VALUE='Hoshiarpur'>Hoshiarpur</OPTION>" +	
	"<OPTION VALUE='Jalandhar'>Jalandhar</OPTION>" +	
	"<OPTION VALUE='Kapurthala'>Kapurthala</OPTION>" +	
	"<OPTION VALUE='Ludhiana'>Ludhiana</OPTION>" +	
	"<OPTION VALUE='Mansa'>Mansa</OPTION>" +	
	"<OPTION VALUE='Moga'>Moga</OPTION>" +	
	"<OPTION VALUE='Muktsar'>Muktsar</OPTION>" +	
	"<OPTION VALUE='Nawanshahr'>Nawanshahr</OPTION>" +	
	"<OPTION VALUE='Patiala'>Patiala</OPTION>" +	
	"<OPTION VALUE='Rupnagar'>Rupnagar</OPTION>" +	
	"<OPTION VALUE='Sangrur'>Sangrur</OPTION>" +	
	
		"</select>";
	}
if(myState == "Rajasthan")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Ajmer'>Ajmer</OPTION>" +	
	"<OPTION VALUE='Alwar'>Alwar</OPTION>" +	
	"<OPTION VALUE='Banswara'>Banswara</OPTION>" +	
	"<OPTION VALUE='Baran'>Baran</OPTION>" +	
	"<OPTION VALUE='Barmer'>Barmer</OPTION>" +	
	"<OPTION VALUE='Bharatpur'>Bharatpur</OPTION>" +	
	"<OPTION VALUE='Bhilwara'>Bhilwara</OPTION>" +	
	"<OPTION VALUE='Bikaner'>Bikaner</OPTION>" +	
	"<OPTION VALUE='Bundi'>Bundi</OPTION>" +	
	"<OPTION VALUE='Chittaurgarh'>Chittaurgarh</OPTION>" +	
	"<OPTION VALUE='Churu'>Churu</OPTION>" +	
	"<OPTION VALUE='Dausa'>Dausa</OPTION>" +	
	"<OPTION VALUE='Dhaulpur'>Dhaulpur</OPTION>" +	
	"<OPTION VALUE='Dungarpur'>Dungarpur</OPTION>" +	
	"<OPTION VALUE='Ganganagar'>Ganganagar</OPTION>" +	
	"<OPTION VALUE='Hanumangarh'>Hanumangarh</OPTION>" +	
	"<OPTION VALUE='Jaipur'>Jaipur</OPTION>" +	
	"<OPTION VALUE='Jaisalmer'>Jaisalmer</OPTION>" +	
	"<OPTION VALUE='Jalor'>Jalor</OPTION>" +	
	"<OPTION VALUE='Jhalawar'>Jhalawar</OPTION>" +	
	"<OPTION VALUE='Jodhpur'>Jodhpur</OPTION>" +	
	"<OPTION VALUE='Jhunjhunun'>Jhunjhunun</OPTION>" +	
	"<OPTION VALUE='Karauli'>Karauli</OPTION>" +	
	"<OPTION VALUE='Kota'>Kota</OPTION>" +	
	"<OPTION VALUE='Nagaur'>Nagaur</OPTION>" +	
	"<OPTION VALUE='Pali'>Pali</OPTION>" +	
	"<OPTION VALUE='Rajsamand'>Rajsamand</OPTION>" +	
	"<OPTION VALUE='Sawai Madhopur'>Sawai Madhopur</OPTION>" +	
	"<OPTION VALUE='Sikar'>Sikar</OPTION>" +	
	"<OPTION VALUE='Sirohi'>Sirohi</OPTION>" +	
	"<OPTION VALUE='Tonk'>Tonk</OPTION>" +	
	"<OPTION VALUE='Udaipur'>Udaipur</OPTION>" +	
	
		"</select>";
	}
if(myState == "Sikkim")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='East'>East</OPTION>" +	
	"<OPTION VALUE='North'>North</OPTION>" +	
	"<OPTION VALUE='South'>South</OPTION>" +	
	"<OPTION VALUE='West'>West</OPTION>" +	
	
		"</select>";
	}
if(myState == "Tamil Nadu")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='3156'>Chennai</OPTION>" +	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='Chennai'>Chennai</OPTION>" +	
	"<OPTION VALUE='Coimbatore'>Coimbatore</OPTION>" +	
	"<OPTION VALUE='Cuddalore'>Cuddalore</OPTION>" +	
	"<OPTION VALUE='Dharmapuri'>Dharmapuri</OPTION>" +	
	"<OPTION VALUE='Dindigul'>Dindigul</OPTION>" +	
	"<OPTION VALUE='Erode'>Erode</OPTION>" +	
	"<OPTION VALUE='Kancheepuram'>Kancheepuram</OPTION>" +	
	"<OPTION VALUE='Kanniyakumari'>Kanniyakumari</OPTION>" +	
	"<OPTION VALUE='Kapur'>Kapur</OPTION>" +	
	"<OPTION VALUE='Madurai'>Madurai</OPTION>" +	
	"<OPTION VALUE='Nagapattinam'>Nagapattinam</OPTION>" +	
	"<OPTION VALUE='Namakkal'>Namakkal</OPTION>" +	
	"<OPTION VALUE='Perambalur'>Perambalur</OPTION>" +	
	"<OPTION VALUE='Pudukkottai'>Pudukkottai</OPTION>" +	
	"<OPTION VALUE='Ramanathapuram'>Ramanathapuram</OPTION>" +	
	"<OPTION VALUE='Salem'>Salem</OPTION>" +	
	"<OPTION VALUE='Sivaganga'>Sivaganga</OPTION>" +	
	"<OPTION VALUE='Tanjore'>Tanjore</OPTION>" +	
	"<OPTION VALUE='The Nilgiris'>The Nilgiris</OPTION>" +	
	"<OPTION VALUE='Theni'>Theni</OPTION>" +	
	"<OPTION VALUE='Thiruvallur'>Thiruvallur</OPTION>" +	
	"<OPTION VALUE='Thiruvarur'>Thiruvarur</OPTION>" +	
	"<OPTION VALUE='Toothukudi'>Toothukudi</OPTION>" +	
	"<OPTION VALUE='Tiruchirappalli'>Tiruchirappalli</OPTION>" +	
	"<OPTION VALUE='Tirunelveli'>Tirunelveli</OPTION>" +	
	"<OPTION VALUE='Tiruvanamalai'>Tiruvanamalai</OPTION>" +	
	"<OPTION VALUE='Vellore'>Vellore</OPTION>" +	
	"<OPTION VALUE='Viluppuram'>Viluppuram</OPTION>" +	
	"<OPTION VALUE='Virudhunagar'>Virudhunagar</OPTION>" +	
	
		"</select>";
	}
if(myState == "Tripura")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Dhalai'>Dhalai</OPTION>" +	
	"<OPTION VALUE='North Tripura'>North Tripura</OPTION>" +	
	"<OPTION VALUE='South Tripura'>South Tripura</OPTION>" +	
	"<OPTION VALUE='West Tripura'>West Tripura</OPTION>" +	
	
		"</select>";
	}
if(myState == "Uttar Pradesh")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
				
	"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	
	"<OPTION VALUE='Agra'>Agra</OPTION>" +	
	"<OPTION VALUE='Aligarh'>Aligarh</OPTION>" +	
	"<OPTION VALUE='Allahabad'>Allahabad</OPTION>" +	
	"<OPTION VALUE='Ambedaker Nagar'>Ambedaker Nagar</OPTION>" +	
	"<OPTION VALUE='Auraiya'>Auraiya</OPTION>" +	
	"<OPTION VALUE='Azamgarh'>Azamgarh</OPTION>" +	
	"<OPTION VALUE='Budaun'>Budaun</OPTION>" +	
	"<OPTION VALUE='Baghpat'>Baghpat</OPTION>" +	
	"<OPTION VALUE='Bahraich'>Bahraich</OPTION>" +	
	"<OPTION VALUE='Ballia'>Ballia</OPTION>" +	
	"<OPTION VALUE='Balrampur'>Balrampur</OPTION>" +	
	"<OPTION VALUE='Banda'>Banda</OPTION>" +	
	"<OPTION VALUE='Barabanki'>Barabanki</OPTION>" +	
	"<OPTION VALUE='Bareilly'>Bareilly</OPTION>" +	
	"<OPTION VALUE='Basti'>Basti</OPTION>" +	
	"<OPTION VALUE='Bijnor'>Bijnor</OPTION>" +	
	"<OPTION VALUE='Bulandshahr'>Bulandshahr</OPTION>" +	
	"<OPTION VALUE='Chandauli'>Chandauli</OPTION>" +	
	"<OPTION VALUE='Chitrakoot'>Chitrakoot</OPTION>" +	
	"<OPTION VALUE='Deoria'>Deoria</OPTION>" +	
	"<OPTION VALUE='Etah'>Etah</OPTION>" +	
	"<OPTION VALUE='Etawah'>Etawah</OPTION>" +	
	"<OPTION VALUE='Faizabad'>Faizabad</OPTION>" +	
	"<OPTION VALUE='Farrukhabad'>Farrukhabad</OPTION>" +	
	"<OPTION VALUE='Fatehpur'>Fatehpur</OPTION>" +	
	"<OPTION VALUE='Firozabad'>Firozabad</OPTION>" +	
	"<OPTION VALUE='Gautam Buddha Nagar'>Gautam Buddha Nagar</OPTION>" +	
	"<OPTION VALUE='Ghaziabad'>Ghaziabad</OPTION>" +	
	"<OPTION VALUE='Ghazipur'>Ghazipur</OPTION>" +	
	"<OPTION VALUE='Gonda'>Gonda</OPTION>" +	
	"<OPTION VALUE='Gorakhpur'>Gorakhpur</OPTION>" +	
	"<OPTION VALUE='Hamirpur'>Hamirpur</OPTION>" +	
	"<OPTION VALUE='Hardoi'>Hardoi</OPTION>" +	
	"<OPTION VALUE='Jalaun'>Jalaun</OPTION>" +	
	"<OPTION VALUE='Jaunpur'>Jaunpur</OPTION>" +	
	"<OPTION VALUE='Jhansi'>Jhansi</OPTION>" +	
	"<OPTION VALUE='Jyotiba Phule Nagar'>Jyotiba Phule Nagar</OPTION>" +	
	"<OPTION VALUE='Kannauj'>Kannauj</OPTION>" +	
	"<OPTION VALUE='Kanpur Dehat'>Kanpur Dehat</OPTION>" +	
	"<OPTION VALUE='Kanpur Nagar'>Kanpur Nagar</OPTION>" +	
	"<OPTION VALUE='Kaushambi'>Kaushambi</OPTION>" +	
	"<OPTION VALUE='Kushinagar'>Kushinagar</OPTION>" +	
	"<OPTION VALUE='Kheri'>Kheri</OPTION>" +	
	"<OPTION VALUE='Lalitpur'>Lalitpur</OPTION>" +	
	"<OPTION VALUE='Lucknow'>Lucknow</OPTION>" +	
	"<OPTION VALUE='Mahamaya Nagar'>Mahamaya Nagar</OPTION>" +	
	"<OPTION VALUE='Maharajganj'>Maharajganj</OPTION>" +	
	"<OPTION VALUE='Mahoba'>Mahoba</OPTION>" +	
	"<OPTION VALUE='Mainpuri'>Mainpuri</OPTION>" +	
	"<OPTION VALUE='Mathura'>Mathura</OPTION>" +	
	"<OPTION VALUE='Mau'>Mau</OPTION>" +	
	"<OPTION VALUE='Meerut'>Meerut</OPTION>" +	
	"<OPTION VALUE='Mirzapur'>Mirzapur</OPTION>" +	
	"<OPTION VALUE='Moradabad'>Moradabad</OPTION>" +	
	"<OPTION VALUE='Muzaffarnagar'>Muzaffarnagar</OPTION>" +	
	"<OPTION VALUE='Pilibhit'>Pilibhit</OPTION>" +	
	"<OPTION VALUE='Pratapgarh'>Pratapgarh</OPTION>" +	
	"<OPTION VALUE='Rae Bareli'>Rae Bareli</OPTION>" +	
	"<OPTION VALUE='Rampur'>Rampur</OPTION>" +	
	"<OPTION VALUE='Saharanpur'>Saharanpur</OPTION>" +	
	"<OPTION VALUE='Sant Kabir Nagar'>Sant Kabir Nagar</OPTION>" +	
	"<OPTION VALUE='Sant Ravidas Nagar'>Sant Ravidas Nagar</OPTION>" +	
	"<OPTION VALUE='Shahjahanpur'>Shahjahanpur</OPTION>" +	
	"<OPTION VALUE='Shrawasti'>Shrawasti</OPTION>" +	
	"<OPTION VALUE='Siddharthnagar'>Siddharthnagar</OPTION>" +	
	"<OPTION VALUE='Sitapur'>Sitapur</OPTION>" +	
	"<OPTION VALUE='Sonbhadra'>Sonbhadra</OPTION>" +	
	"<OPTION VALUE='Sultanpur'>Sultanpur</OPTION>" +	
	"<OPTION VALUE='Unnao'>Unnao</OPTION>" +	
	"<OPTION VALUE='Varanasi'>Varanasi</OPTION>" +	
	
		"</select>";
	}
if(myState == "Uttaranchal")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	"<OPTION VALUE='-1' selected='selected'>----------- Select city ----------</OPTION>" +
	"<OPTION VALUE='Almora'>Almora</OPTION>" +	
	"<OPTION VALUE='Bageshwar'>Bageshwar</OPTION>" +	
	"<OPTION VALUE='Chamoli'>Chamoli</OPTION>" +	
	"<OPTION VALUE='Champawat'>Champawat</OPTION>" +	
	"<OPTION VALUE='Dehradun'>Dehradun</OPTION>" +	
	"<OPTION VALUE='Hardwar'>Hardwar</OPTION>" +	
	"<OPTION VALUE='Nainital'>Nainital</OPTION>" +	
	"<OPTION VALUE='Garhwal'>Garhwal</OPTION>" +	
	"<OPTION VALUE='Pithoragarh'>Pithoragarh</OPTION>" +	
	"<OPTION VALUE='Rudraprayag'>Rudraprayag</OPTION>" +	
	"<OPTION VALUE='Tehri Garhwal'>Tehri Garhwal</OPTION>" +	
	"<OPTION VALUE='Udham Singh Nagar'>Udham Singh Nagar</OPTION>" +	
	"<OPTION VALUE='Uttarkashi'>Uttarkashi</OPTION>" +	
	
		"</select>";
	}
if(myState == "West Bengal")
	{
		city = "<select name='city'  class='text' onclick='checkStateSelection1()'>" +	
	
			"<OPTION VALUE=''>----------------------------------------</OPTION>" +
	"<OPTION VALUE='Bankura'>Bankura</OPTION>" +	
	"<OPTION VALUE='Barddhaman'>Barddhaman</OPTION>" +	
	"<OPTION VALUE='Birbhum'>Birbhum</OPTION>" +	
	"<OPTION VALUE='Koch Bihar'>Koch Bihar</OPTION>" +	
	"<OPTION VALUE='Dakshin Dinajpur'>Dakshin Dinajpur</OPTION>" +	
	"<OPTION VALUE='Darjiling'>Darjiling</OPTION>" +	
	"<OPTION VALUE='Hugli'>Hugli</OPTION>" +	
	"<OPTION VALUE='Haora'>Haora</OPTION>" +	
	"<OPTION VALUE='Jalpaiguri'>Jalpaiguri</OPTION>" +	
	"<OPTION VALUE='Kolkata'>Kolkata</OPTION>" +	
	"<OPTION VALUE='Maldah'>Maldah</OPTION>" +	
	"<OPTION VALUE='Medinipur'>Medinipur</OPTION>" +	
	"<OPTION VALUE='Murshidabad'>Murshidabad</OPTION>" +	
	"<OPTION VALUE='Nadia'>Nadia</OPTION>" +	
	"<OPTION VALUE='North Twentyfour Parganas'>North Twentyfour Parganas</OPTION>" +	
	"<OPTION VALUE='Puruliya'>Puruliya</OPTION>" +	
	"<OPTION VALUE='South Twentyfour Parganas'>South Twentyfour Parganas</OPTION>" +	
	"<OPTION VALUE='Uttar Dinajpur'>Uttar Dinajpur</OPTION>" +	
	
		"</select>";
	}
	
	document.getElementById("cityId").innerHTML = city;	
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