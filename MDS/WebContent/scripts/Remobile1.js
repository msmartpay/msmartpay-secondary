function populateCircle()
	{
		var myForm = document.regiestration;
		var myOperator = myForm.Operator.value;
		var circle;
		if(myOperator=="Aircel")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+                                                
                                                "<option value='Aircel(andhara Pradesh)'>Andhara Pradesh</option>"+
						                        "<option value='AircelAssam'>Assam</option>"+
                                                "<option value='AircelBihar'>Bihar</option>"+
                                                "<option value='AircelChennai'>Chennai</option>"+
                                                "<option value='AircelDelhi/NCR'>Delhi/NCR</option>"+
                                                "<option value='AircelHimachal Pradesh'>Himachal Pradesh</option>"+
                                                "<option value='AircelJammu and Kashmir'>Jammu and Kashmir</option>"+
                                                "<option value='AircelKerala'>Kerala</option>"+
                                                "<option value='AircelKolkata'>Kolkata</option>"+
                                                "<option value='AircelMumbai'>Mumbai</option>"+
                                                "<option value='AircelNorth East'>North East</option>"+
                                                "<option value='AircelOrissa'>Orissa</option>"+
                                                "<option value='AircelWest Bengal'>West Bengal</option>"+
                                                "<option value='AircelUP (East)'>UP East</option>"+
                                                "<option value='AircelUP (WEST)'>UP West</option>"+
												
												  "<option value='AircelPunjab'>Punjab/option>"+
												    "<option value='AircelGujarat'>Gujarat</option>"+
													  "<option value='AircelMadhya Pradesh'>Madhya Pradesh</option>"+
													    "<option value='AircelRajasthan'>Rajasthan</option>"+
														  "<option value='AircelHaryana'>Haryana</option>"+
														   "<option value='AircelTamil Nadu'>Tamil Nadu</option>"+
														   "<option value='AircelJarkhand'>Jarkhand</option>"+
														   "<option value='AircelMaharashtra'>Maharashtra</option>"+
														   "<option value='AircelKarnatka'>Karnatka</option>"+
														    "<option value='AircelBangalore'>Bangalore</option>"+
															 "<option value='AircelChattisgarh'>Chattisgarh</option>"+
                                              "</select>";
			}
			
		if(myOperator=="BSNL")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='BSNLAndhra Pradesh'>Andhra Pradesh</option>"+
                                                "<option value='BSNLAssam'>Assam</option>"+                                               
                                                "<option value='BSNLChennai'>Chennai</option>"+
                                                "<option value='BSNLDelhi/NCR'>Delhi/NCR</option>"+
                                                "<option value='BSNLHaryana'>Haryana</option>"+
												"<option value='BSNLHimachal Pradesh'>Himachal Pradesh</option>"+
												"<option value='BSNLJharkhand'>Jharkhand</option>"+
												"<option value='BSNLGujarat'>Gujarat</option>"+
												"<option value='BSNLKarnataka'>Karnataka</option>"+
                                                "<option value='BSNLKerala'>Kerala</option>"+
                                                "<option value='BSNLKolkata'>Kolkata</option>"+
												"<option value='BSNLMadhya Pradesh'>Madhya Pradesh</option>"+
												"<option value='BSNLMaharashtra'>Maharashtra</option>"+
                                                "<option value='BSNLNorth East'>North East</option>"+
                                                "<option value='BSNLOrissa'>Orissa</option>"+
												"<option value='BSNLPunjab'>Punjab</option>"+
												"<option value='BSNLRajasthan'>Rajasthan</option>"+
												"<option value='BSNLTamilNadu'>TamilNadu</option>"+                                                
                                                "<option value='BSNLUP (East)'>UP (East)</option>"+ 
												"<option value='BSNLUP (WEST)'>UP (WEST)</option>"+
												"<option value='BSNLWest Bengal'>West Bengal</option>"+
												"<option value='BSNLBihar'>Bihar</option>"+
                                              "</select>";
			}
			
		if(myOperator=="Virgin")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='VirginAndhra Pradesh'>Andhra Pradesh</option>"+
                                                "<option value='VirginBihar'>Bihar</option>"+                                               
                                                "<option value='VirginChennai'>Chennai</option>"+
                                                "<option value='VirginDelhi/NCR'>Delhi/NCR</option>"+
                                                "<option value='VirginHaryana'>Haryana</option>"+												
												"<option value='VirginGujarat'>Gujarat</option>"+
												"<option value='VirginKarnataka'>Kerala</option>"+
												"<option value='VirginKarnataka'>Karnataka</option>"+
                                                "<option value='VirginKolkata'>Kolkata</option>"+
												"<option value='VirginMadhya Pradesh'>Madhya Pradesh</option>"+
												"<option value='VirginMaharashtra'>Maharashtra</option>"+												
                                                "<option value='VirginMumbai'>Mumbai</option>"+
                                                "<option value='VirginOrissa'>Orissa</option>"+
												"<option value='VirginPunjab'>Punjab</option>"+
												"<option value='VirginRajasthan'>Rajasthan</option>"+
												"<option value='VirginTamilNadu'>TamilNadu</option>"+                                                
                                                "<option value='VirginUP (East)'>UP (East)</option>"+ 
												"<option value='VirginUP (WEST)'>UP (WEST)</option>"+
												"<option value='VirginWest Bengal'>West Bengal</option>"+
                                              "</select>";
			}
			
			if(myOperator=="Tata")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='TataAll Circle'>All Circle</option>"+
												
                                              "</select>";
			}
			
			if(myOperator=="Vodafone")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='VodafoneAndhra Pradesh'>Andhra Pradesh</option>"+
												"<option value='VodafoneAssam'>Assam</option>"+
                                                "<option value='VodafoneChennai'>Chennai</option>"+
                                                "<option value='VodafoneDelhi/NCR'>Delhi/NCR</option>"+
												"<option value='VodafoneGujarat'>Gujarat</option>"+
												"<option value='VodafoneJharkhand'>Jharkhand</option>"+
												"<option value='VodafoneKarnataka'>Karnataka</option>"+
                                                "<option value='VodafoneKolkata'>Kolkata</option>"+
												"<option value='VodafoneMaharashtra'>Maharashtra</option>"+												
                                                "<option value='VodafoneMumbai'>Mumbai</option>"+
												"<option value='VodafonePunjab'>Punjab</option>"+
												"<option value='VodafoneRajasthan'>Rajasthan</option>"+
												"<option value='VodafoneTamilNadu'>TamilNadu</option>"+  
                                                "<option value='VodafoneUP (East)'>UP (East)</option>"+ 
												"<option value='VodafoneUP (WEST)'>UP (WEST)</option>"+
												"<option value='VodafoneWest Bengal'>West Bengal</option>"+
												"<option value='VodafoneKerela'>Kerela</option>"+
												"<option value='VodafoneMadhya Pradesh'>Madhya Pradesh</option>"+
												"<option value='VodafoneBihar'>Bihar</option>"+
												"<option value='VodafoneHaryana'>Haryana</option>"+
												"<option value='VodafoneHimachal Pradesh'>Himachal Pradesh</option>"+
												"<option value='VodafoneOrrisa'>Orrisa</option>"+
												"<option value='VodafoneBangalore'>Bangalore</option>"+
												"<option value='VodafoneNorth East'>North East</option>"+
												"<option value='VodafoneChattisgarh'>Chattisgarh</option>"+
                                              "</select>";
			}
			
			if(myOperator=="IDEA")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='IDEAAndhra Pradesh'>Andhra Pradesh</option>"+
												"<option value='IDEABihar'>Bihar</option>"+                                                
                                                "<option value='IDEADelhi/NCR'>Delhi/NCR</option>"+
												"<option value='IDEAGujarat'>Gujarat</option>"+
												"<option value='IDEAHaryana'>Haryana</option>"+
												"<option value='IDEAHimachal Pradesh'>Himachal Pradesh</option>"+
												"<option value='IDEAKerala'>Kerala</option>"+
												"<option value='IDEAMadhya Pradesh'>Madhya Pradesh</option>"+
												"<option value='IDEAMaharashtra'>Maharashtra</option>"+												
                                                "<option value='IDEAMumbai'>Mumbai</option>"+
												"<option value='IDEAOrissa'>Orissa</option>"+
												"<option value='IDEAPunjab'>Punjab</option>"+
												"<option value='IDEARajasthan'>Rajasthan</option>"+
												"<option value='IDEATamilNadu'>TamilNadu</option>"+  
                                                "<option value='IDEAUP(East)'>UP (East)</option>"+ 
												"<option value='IDEAUP(WEST)'>UP (WEST)</option>"+		
												"<option value='IDEAKarnatka'>Karnatka</option>"+		
												"<option value='IDEAJarkhand'>Jarkhand</option>"+		
                                              "</select>";
			}
			
			if(myOperator=="Reliance")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='RelianceAll Circle'>All Circle</option>"+
																							
                                              "</select>";
			}
			
				if(myOperator=="RelianceGsm")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='RelianceGsmAndhra Pradesh'>Andhra Pradesh</option>"+
												"<option value='RelianceGsmBihar'>Bihar</option>"+                                                
                                                "<option value='RelianceGsmDelhi/NCR'>Delhi/NCR</option>"+
												"<option value='RelianceGsmGujarat'>Gujarat</option>"+
												"<option value='RelianceGsmHaryana'>Haryana</option>"+
												"<option value='RelianceGsmHimachal Pradesh'>Himachal Pradesh</option>"+
												"<option value='RelianceGsmKerala'>Kerala</option>"+
												"<option value='RelianceGsmMadhya Pradesh'>Madhya Pradesh</option>"+
												"<option value='RelianceGsmMaharashtra'>Maharashtra</option>"+												
                                                "<option value='RelianceGsmMumbai'>Mumbai</option>"+
												"<option value='RelianceGsmOrissa'>Orissa</option>"+
												"<option value='RelianceGsmPunjab'>Punjab</option>"+
												"<option value='RelianceGsmRajasthan'>Rajasthan</option>"+
												"<option value='RelianceGsmTamilNadu'>TamilNadu</option>"+  
                                                "<option value='RelianceGsmUP(East)'>UP (East)</option>"+ 
												
												"<option value='RelianceGsmUP(WEST)'>UP (WEST)</option>"+												
                                              "<option value='RelianceGsmWest Bengal'>West Bengal</option>"+
											  "<option value='RelianceGsmBangalore'>Bangalore</option>"+
											  "<option value='RelianceGsmJammu& Kashmir'>Jammu& Kashmir</option>"+
											  "<option value='RelianceGsmChattisgarh'>Chattisgarh</option>"+
											  "<option value='RelianceGsmKolkata'>Kolkata</option>"+
											  "<option value='RelianceGsmChennai'>Chennai</option>"+
											  "<option value='RelianceGsmJarkhand'>Jarkhand</option>"+
											  "<option value='RelianceGsmAssam'>Assam</option>"+
											  "<option value='RelianceGsmNorth East'>North East</option>"+
											  "<option value='RelianceGsmKarnatka'>Karnatka</option>"+
											  "</select>";
			}
			
			if(myOperator=="Airtel")
			{
				circle="<select name='circle' class='Trebuchet_normal' onChange='populateamount()'  style='width:200px' >"+
                                                "<option value='-1'>---Please Select---</option>"+
												"<option value='AirtelAll Circles'>All Circles</option>"+
												 "</select>";
			}
	
	    document.getElementById("cityId").innerHTML = circle;	
	    populatelocations();
	   
	
	
	}


function populateamount()
{  
	var myForm = document.regiestration;
	var myState = myForm.Operator;
	if(myState.value =="-1")
	{
		alert("Please Select Operator First");
		myState.focus();
	}
	else
	{
		populatelocations();
	}
}

function populateamount()
	{
		var myForm = document.regiestration;
		var mycircle = myForm.circle.value;
		var amount;
		if(mycircle=="VirginAndhra Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='49'>49</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='301'>301</option>"+							
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginBihar")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginChennai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='45'>45</option>"+							
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginDelhi/NCR")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='60'>60</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='202'>202</option>"+
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginHaryana")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='26'>26</option>"+
							"<option value='27'>27</option>"+
							"<option value='28'>28</option>"+
							"<option value='29'>29</option>"+
							"<option value='31'>31</option>"+
							"<option value='32'>32</option>"+
							"<option value='39'>39</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='84'>84</option>"+
							"<option value='85'>85</option>"+
							"<option value='99'>99</option>"+
							"<option value='100'>100</option>"+
							"<option value='168'>168</option>"+
							"<option value='169'>169</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='249'>249</option>"+
							"<option value='250'>250</option>"+							
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='333'>333</option>"+
							"<option value='334'>334</option>"+
							"<option value='345'>345</option>"+
							"<option value='346'>346</option>"+
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
					   "</select>";
			}
		if(mycircle=="VirginGujarat")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+													
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginKarnataka")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}		
		if(mycircle=="VirginKolkata")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+													
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginMadhya Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+
							"<option value='202'>202</option>"+
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='301'>301</option>"+
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginMaharashtra")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='46'>46</option>"+
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='169'>169</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='349'>349</option>"+
							"<option value='350'>350</option>"+
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginMumbai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='46'>46</option>"+
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='169'>169</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='349'>349</option>"+
							"<option value='350'>350</option>"+
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginOrissa")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+							
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginPunjab")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='41'>41</option>"+
							"<option value='50'>50</option>"+	
							"<option value='88'>88</option>"+
							"<option value='89'>89</option>"+	
							"<option value='100'>100</option>"+							
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+							
							"<option value='301'>301</option>"+
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginRajasthan")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+							
							"<option value='110'>110</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+	
							"<option value='202'>202</option>"+	
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+							
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginTamilNadu")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='45'>45</option>"+							
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+							
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginUP (East)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='71'>71</option>"+							
							"<option value='72'>72</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+		
							"<option value='301'>301</option>"+		
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginUP (WEST)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+
							"<option value='71'>71</option>"+							
							"<option value='72'>72</option>"+
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+		
							"<option value='301'>301</option>"+		
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="VirginWest Bengal")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+							
							"<option value='50'>50</option>"+							
							"<option value='100'>100</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='201'>201</option>"+							
							"<option value='249'>249</option>"+							
							"<option value='250'>250</option>"+
							"<option value='295'>295</option>"+
							"<option value='296'>296</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+								
							"<option value='499'>499</option>"+
							"<option value='500'>500</option>"+
							"<option value='750'>750</option>"+
							"<option value='2000'>2000</option>"+
					   "</select>";
			}
		if(mycircle=="AircelAssam")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='47'>47</option>"+
							"<option value='70'>70</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+							
							"<option value='220'>220</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
							"<option value='1000'>1000</option>"+
					   "</select>";
			}
		if(mycircle=="AircelBihar")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+	
							"<option value='220'>220</option>"+
							"<option value='300'>300</option>"+
					   "</select>";
			}
		if(mycircle=="AircelChennai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='120'>120</option>"+
							"<option value='198'>198</option>"+
							"<option value='200'>200</option>"+
							"<option value='220'>220</option>"+
							"<option value='300'>300</option>"+
							"<option value='330'>330</option>"+
							"<option value='500'>500</option>"+
							"<option value='550'>550</option>"+
							"<option value='750'>750</option>"+
							"<option value='888'>888</option>"+
							"<option value='999'>999</option>"+
							"<option value='1100'>1100</option>"+
					   "</select>";
			}
		if(mycircle=="AircelDelhi/NCR")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='200'>200</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
							"<option value='1000'>1000</option>"+							
					   "</select>";
			}
		if(mycircle=="AircelHimachal Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+
							"<option value='220'>220</option>"+													
					   "</select>";
			}
		if(mycircle=="AircelJammu and Kashmir")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='111'>111</option>"+							
							"<option value='200'>200</option>"+							
							"<option value='220'>220</option>"+
							"<option value='325'>325</option>"+
							"<option value='399'>399</option>"+
							"<option value='500'>500</option>"+					
					   "</select>";
			}
		if(mycircle=="AircelKerala")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='51'>51</option>"+
							"<option value='100'>100</option>"+
							"<option value='201'>201</option>"+
							"<option value='301'>301</option>"+
							"<option value='501'>501</option>"+
							"<option value='1101'>1101</option>"+							
					   "</select>";
			}
		if(mycircle=="AircelKolkata")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+	
							"<option value='200'>200</option>"+
							"<option value='300'>300</option>"+							
					   "</select>";
			}
		if(mycircle=="AircelMumbai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='30'>30</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+													
					   "</select>";
			}
		if(mycircle=="AircelNorth East")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='47'>47</option>"+
							"<option value='50'>50</option>"+
							"<option value='70'>70</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+
							"<option value='220'>220</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
							"<option value='1000'>1000</option>"+							
					   "</select>";
			}
		if(mycircle=="AircelOrissa")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='61'>61</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+
							"<option value='220'>220</option>"+
							"<option value='299'>299</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
					   "</select>";
			}
		if(mycircle=="AircelWest Bengal")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='91'>91</option>"+
							"<option value='100'>100</option>"+
							"<option value='110'>110</option>"+
							"<option value='200'>200</option>"+
							"<option value='220'>220</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+							
					   "</select>";
			}
		if(mycircle=="AircelUP (East)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+												
					   "</select>";
			}
		if(mycircle=="AircelUP (WEST)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='25'>25</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLAndhra Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLAssam")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLChennai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLDelhi/NCR")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLHaryana")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLHimachal Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLJharkhand")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLGujarat")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='80'>80</option>"+
							"<option value='110'>110</option>"+
							"<option value='170'>170</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='350'>350</option>"+
							"<option value='390'>390</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLKarnataka")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLKerala")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLKolkata")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLMadhya Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+							
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLMaharashtra")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+							
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLNorth East")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+							
							"<option value='220'>220</option>"+							
							"<option value='550'>550</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLOrissa")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+							
							"<option value='220'>220</option>"+							
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLPunjab")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLRajasthan")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='30'>30</option>"+
							"<option value='31'>31</option>"+
							"<option value='55'>55</option>"+
							"<option value='66'>66</option>"+
							"<option value='110'>110</option>"+
							"<option value='111'>111</option>"+
							"<option value='220'>220</option>"+
							"<option value='222'>222</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+							
					   "</select>";
			}
		if(mycircle=="BSNLTamilNadu")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="BSNLUP (East)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='29'>29</option>"+
							"<option value='30'>30</option>"+
							"<option value='31'>31</option>"+
							"<option value='45'>45</option>"+
							"<option value='48'>48</option>"+
							"<option value='49'>49</option>"+
							"<option value='55'>55</option>"+
							"<option value='66'>66</option>"+
							"<option value='72'>72</option>"+
							"<option value='95'>95</option>"+
							"<option value='109'>109</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='222'>222</option>"+
							"<option value='280'>280</option>"+
							"<option value='300'>300</option>"+
							"<option value='330'>330</option>"+
							"<option value='500'>500</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLUP (WEST)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='280'>280</option>"+
							"<option value='330'>330</option>"+
							"<option value='550'>550</option>"+
							"<option value='1100'>1100</option>"+
							"<option value='3300'>3300</option>"+
					   "</select>";
			}
		if(mycircle=="BSNLWest Bengal")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='10'>10</option>"+
							"<option value='20'>20</option>"+
							"<option value='55'>55</option>"+
							"<option value='110'>110</option>"+
							"<option value='220'>220</option>"+
							"<option value='550'>550</option>"+	
					   "</select>";
			}
		if(mycircle=="VodafoneAndhra Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='150'>150</option>"+
							"<option value='97'>97</option>"+
							"<option value='222'>222</option>"+
							"<option value='999'>999</option>"+
							"<option value='555'>555</option>"+
							"<option value='825'>825</option>"+
							"<option value='250'>250</option>"+
							"<option value='400'>400</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneAssam")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='90'>90</option>"+
							"<option value='222'>222</option>"+
							"<option value='670'>670</option>"+
							"<option value='150'>150</option>"+
							"<option value='170'>170</option>"+
							"<option value='200'>200</option>"+
							"<option value='300'>300</option>"+
							"<option value='400'>400</option>"+
							"<option value='500'>500</option>"+
							"<option value='700'>700</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneChennai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='1001'>1001</option>"+
							"<option value='498'>498</option>"+
							"<option value='351'>351</option>"+
							"<option value='99'>99</option>"+
							"<option value='200'>200</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneDelhi/NCR")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='310'>310</option>"+
							"<option value='351'>351</option>"+
							"<option value='222'>222</option>"+
							"<option value='610'>610</option>"+
							"<option value='501'>501</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneGujarat")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='300'>300</option>"+
							"<option value='400'>400</option>"+
							"<option value='223'>223</option>"+
							"<option value='503'>503</option>"+
							"<option value='601'>601</option>"+
							"<option value='700'>700</option>"+
							"<option value='800'>800</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneJharkhand")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='60'>60</option>"+
							"<option value='100'>100</option>"+
							"<option value='150>150</option>"+
							"<option value='225'>225</option>"+
							"<option value='300'>300</option>"+
							"<option value='400'>400</option>"+
							"<option value='500'>500</option>"+
							"<option value='600'>600</option>"+
							"<option value='899'>899</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneKarnataka")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='47'>47</option>"+
							"<option value='98'>98</option>"+
							"<option value='99'>99</option>"+
							"<option value='175'>175</option>"+
							"<option value='201'>201</option>"+
							"<option value='300'>300</option>"+
							"<option value='400'>400</option>"+
							"<option value='500'>500</option>"+
							"<option value='550'>550</option>"+
							"<option value='750'>750</option>"+
							"<option value='900'>900</option>"+
							"<option value='950'>950</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneKolkata")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='40'>40</option>"+
							"<option value='99'>99</option>"+
							"<option value='100'>100</option>"+
							"<option value='145'>145</option>"+
							"<option value='190'>190</option>"+
							"<option value='249'>249</option>"+
							"<option value='300'>300</option>"+
							"<option value='351'>351</option>"+
							"<option value='750'>750</option>"+
							"<option value='1001'>1001</option>"+
							"<option value='250'>250</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneMaharashtra")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='150'>150</option>"+
							"<option value='200'>200</option>"+
							"<option value='222'>222</option>"+
							"<option value='351'>351</option>"+
							"<option value='444'>444</option>"+
							"<option value='550'>550</option>"+
							"<option value='600'>600</option>"+
							"<option value='900'>900</option>"+
							"<option value='1000'>1000</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneMumbai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='222'>222</option>"+
							"<option value='350'>350</option>"+
							"<option value='450'>450</option>"+
							"<option value='550'>550</option>"+
							"<option value='700'>700</option>"+
							"<option value='750'>750</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafonePunjab")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='96'>96</option>"+
							"<option value='100'>100</option>"+
							"<option value='224'>224</option>"+
							"<option value='333'>333</option>"+
							"<option value='555'>555</option>"+
							"<option value='1111'>1111</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneRajasthan")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='99'>99</option>"+
							"<option value='100'>100</option>"+
							"<option value='151'>151</option>"+
							"<option value='200'>200</option>"+
							"<option value='302'>302</option>"+
							"<option value='500'>500</option>"+
							"<option value='700'>700</option>"+
							"<option value='800'>800</option>"+
							"<option value='900'>900</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneTamilNadu")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='45'>45</option>"+
							"<option value='160'>160</option>"+
							"<option value='199'>199</option>"+
							"<option value='200'>200</option>"+
							"<option value='295'>295</option>"+
							"<option value='299'>299</option>"+
							"<option value='444'>444</option>"+
							"<option value='501'>501</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneUP (East)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='280'>280</option>"+
							"<option value='333'>333</option>"+
							"<option value='400'>400</option>"+
							"<option value='500'>500</option>"+
							"<option value='550'>550</option>"+
							"<option value='700'>700</option>"+
							"<option value='800'>800</option>"+
							"<option value='900'>900</option>"+
							"<option value='1000'>1000</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneUP (WEST)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='222'>222</option>"+
							"<option value='350'>350</option>"+
							"<option value='444'>444</option>"+
							"<option value='550'>550</option>"+
							"<option value='650'>650</option>"+
							"<option value='750'>750</option>"+
							"<option value='800'>800</option>"+
							"<option value='888'>888</option>"+
							"<option value='950'>950</option>"+							
					   "</select>";
			}
		if(mycircle=="VodafoneWest Bengal")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='85'>85</option>"+
							"<option value='149'>149</option>"+
							"<option value='150'>150</option>"+
							"<option value='200'>200</option>"+
							"<option value='250'>250</option>"+
							"<option value='395'>395</option>"+
							"<option value='444'>444</option>"+
							"<option value='501'>501</option>"+							
					   "</select>";
			}			
		if(mycircle=="All Circles")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='100'>100</option>"+
							"<option value='200'>200</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
							"<option value='1000'>1000</option>"+
							"<option value='333'>333-F</option>"+
							"<option value='444'>444-F</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAAndhra Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='326'>326</option>"+
							"<option value='331'>331</option>"+
							"<option value='104'>104</option>"+
							"<option value='110'>110</option>"+
							"<option value='113'>113</option>"+
							"<option value='126'>126</option>"+
							"<option value='176'>176</option>"+
							"<option value='197'>197</option>"+
							"<option value='201'>201</option>"+
							"<option value='222'>222</option>"+
							"<option value='250'>250</option>"+
							"<option value='299'>299</option>"+
							"<option value='334'>334</option>"+
							"<option value='252'>252</option>"+
					   "</select>";
			}
		if(mycircle=="IDEABihar")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='111'>111</option>"+
							"<option value='142'>142</option>"+
							"<option value='300'>300</option>"+
							"<option value='111'>111</option>"+
							"<option value='200'>200</option>"+
							"<option value='1000'>1000</option>"+							
					   "</select>";
			}
		if(mycircle=="IDEADelhi/NCR")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='262'>262</option>"+
							"<option value='298'>298</option>"+
							"<option value='103'>103</option>"+
							"<option value='122'>122</option>"+
							"<option value='131'>131</option>"+
							"<option value='152'>152</option>"+
							"<option value='171'>171</option>"+
							"<option value='191'>191</option>"+
							"<option value='199'>199</option>"+
							"<option value='202'>202</option>"+
							"<option value='217'>217</option>"+
							"<option value='224'>224</option>"+
							"<option value='249'>249</option>"+
							"<option value='330'>330</option>"+
							"<option value='226'>226</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAGujarat")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='55'>55</option>"+
							"<option value='58'>58</option>"+
							"<option value='12'>12</option>"+
							"<option value='15'>15</option>"+
							"<option value='20'>20</option>"+
							"<option value='30'>30</option>"+
							"<option value='32'>32</option>"+
							"<option value='34'>34</option>"+
							"<option value='36'>36</option>"+
							"<option value='41'>41</option>"+
							"<option value='45'>45</option>"+
							"<option value='48'>48</option>"+
							"<option value='53'>53</option>"+
							"<option value='62'>62</option>"+
							"<option value='50'>50</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAHaryana")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='501'>501</option>"+
							"<option value='556'>556</option>"+
							"<option value='16'>16</option>"+
							"<option value='32'>32</option>"+
							"<option value='41'>41</option>"+
							"<option value='58'>58</option>"+
							"<option value='74'>74</option>"+
							"<option value='100'>100</option>"+
							"<option value='148'>148</option>"+
							"<option value='206'>206</option>"+
							"<option value='250'>250</option>"+
							"<option value='289'>289</option>"+
							"<option value='445'>445</option>"+
							"<option value='334'>334</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAHimachal Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='223'>223</option>"+
							"<option value='275'>275</option>"+
							"<option value='20'>20</option>"+
							"<option value='26'>26</option>"+
							"<option value='48'>48</option>"+
							"<option value='51'>51</option>"+
							"<option value='58'>58</option>"+
							"<option value='64'>64</option>"+
							"<option value='101'>101</option>"+
							"<option value='103'>103</option>"+
							"<option value='127'>127</option>"+
							"<option value='147'>147</option>"+
							"<option value='201'>201</option>"+
							"<option value='300'>300</option>"+
							"<option value='199'>199</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAKerala")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='50'>50</option>"+
							"<option value='111'>111</option>"+
							"<option value='502'>502</option>"+
							"<option value='851'>851</option>"+
							"<option value='99'>99</option>"+
							"<option value='199'>199</option>"+
							"<option value='699'>699</option>"+
							"<option value='1000'>1000</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAMadhya Pradesh")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='110'>110</option>"+
							"<option value='125'>125</option>"+
							"<option value='196'>196</option>"+
							"<option value='220'>220</option>"+
							"<option value='297'>297</option>"+
							"<option value='303'>303</option>"+
							"<option value='330'>330</option>"+
							"<option value='525'>525</option>"+
							"<option value='650'>650</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAMaharashtra")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='100'>100</option>"+
							"<option value='345'>345</option>"+
							"<option value='750'>750</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAMumbai")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='255'>255</option>"+
							"<option value='300'>300</option>"+
							"<option value='101'>101</option>"+
							"<option value='112'>112</option>"+
							"<option value='141'>141</option>"+
							"<option value='146'>146</option>"+
							"<option value='150'>150</option>"+
							"<option value='152'>152</option>"+
							"<option value='157'>157</option>"+
							"<option value='183'>183</option>"+
							"<option value='192'>192</option>"+
							"<option value='223'>223</option>"+
							"<option value='307'>307</option>"+
							"<option value='204'>204</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAOrissa")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='111'>111</option>"+
							"<option value='222'>222</option>"+
							"<option value='333'>333</option>"+
							"<option value='400'>400</option>"+
							"<option value='445'>445</option>"+
							"<option value='556'>556</option>"+
							"<option value='667'>667</option>"+
							"<option value='778'>778</option>"+
							"<option value='889'>889</option>"+							
					   "</select>";
			}
		if(mycircle=="IDEAPunjab")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='540'>540</option>"+
							"<option value='101'>101</option>"+
							"<option value='107'>107</option>"+
							"<option value='112'>112</option>"+
							"<option value='135'>135</option>"+
							"<option value='152'>152</option>"+
							"<option value='160'>160</option>"+
							"<option value='196'>196</option>"+
							"<option value='200'>200</option>"+
							"<option value='203'>203</option>"+
							"<option value='222'>222</option>"+
							"<option value='350'>350</option>"+
							"<option value='225'>225</option>"+							
					   "</select>";
			}
		if(mycircle=="IDEARajasthan")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='197'>197</option>"+
							"<option value='203'>203</option>"+
							"<option value='101'>101</option>"+
							"<option value='103'>103</option>"+
							"<option value='109'>109</option>"+
							"<option value='111'>111</option>"+
							"<option value='117'>117</option>"+
							"<option value='121'>121</option>"+
							"<option value='125'>125</option>"+
							"<option value='137'>137</option>"+
							"<option value='146'>146</option>"+
							"<option value='150'>150</option>"+
							"<option value='176'>176</option>"+
							"<option value='210'>210</option>"+
							"<option value='158'>158</option>"+
					   "</select>";
			}
		if(mycircle=="IDEATamilNadu")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+							
					   "</select>";
			}
		if(mycircle=="IDEAUP(East)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='333'>333</option>"+
							"<option value='350'>350</option>"+
							"<option value='102'>102</option>"+
							"<option value='120'>120</option>"+
							"<option value='149'>149</option>"+
							"<option value='152'>152</option>"+
							"<option value='180'>180</option>"+
							"<option value='195'>195</option>"+
							"<option value='199'>199</option>"+
							"<option value='201'>201</option>"+
							"<option value='210'>210</option>"+
							"<option value='223'>223</option>"+
							"<option value='251'>251</option>"+
							"<option value='299'>299</option>"+
							"<option value='401'>401</option>"+
					   "</select>";
			}
		if(mycircle=="IDEAUP(WEST)")
			{
				amount="<select name='amount' class='Trebuchet_normal' '-1' selected='selected'  style='width:200px'>"+
							"<option value='-1'>---Please Select---</option>"+
							"<option value='274'>274</option>"+
							"<option value='300'>300</option>"+
							"<option value='110'>110</option>"+
							"<option value='151'>151</option>"+
							"<option value='199'>199</option>"+
							"<option value='201'>201</option>"+
							"<option value='251'>251</option>"+
							"<option value='300'>300</option>"+
							"<option value='500'>500</option>"+
							"<option value='124'>124</option>"+
							"<option value='164'>164</option>"+
							"<option value='249'>249</option>"+
							"<option value='500'>500</option>"+
							"<option value='199'>199</option>"+
					   "</select>";
			}
	  document.getElementById("cityId1").innerHTML = amount;	
	    populatelocations1();
	
	
	
	
	}


function populateamount1()
{  
	var myForm = document.regiestration;
	var myState = myForm.amount;
	if(myState.value =="-1")
	{
		alert("Please Select Cricle First");
		myState.focus();
	}
	else
	{
		populatelocations1();
	}
}