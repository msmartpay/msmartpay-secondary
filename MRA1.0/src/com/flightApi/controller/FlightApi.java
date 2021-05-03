package com.flightApi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class FlightApi
{

	private final static String LoginId="SoftSolution";
	private final static String Password="SoftSolution123";



	private final static String BASE_URL="http://115.248.39.80/HermesDomAir/DomesticAir.svc/JSONService/";



	public ArrayList<HashMap<String, Object>> GetAvailabilityRequest(String origin, String destination,
			String traveldate, String adultCount, String childCount, String infantCount,String classType)
	{
		JSONObject resp=null;

		HashMap< String, Object>Infantmap=new HashMap<>();
		HashMap< String, Object>AdultMap=new HashMap<>();
		ArrayList<HashMap<String, Object>> mainList=new ArrayList<>();

		JSONObject reJson=new JSONObject();

		JSONObject avalJson=null;
		JSONArray jsonArray=new JSONArray();
		JSONObject searchDetail=null;

		try {




			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);

			reJson.put("Authentication",obj);

			avalJson=new JSONObject();

			avalJson.put("BookingType", "O");
			avalJson.put("ClassType", classType);
			avalJson.put("AirlineCode","");
			avalJson.put("AdultCount", adultCount);

			avalJson.put("ChildCount",childCount);
			avalJson.put("InfantCount", infantCount);
			avalJson.put("ResidentofIndia", "1");
			avalJson.put("Optional1", "0");
			avalJson.put("Optional2", "0");
			avalJson.put("Optional3", "0");

			searchDetail=new JSONObject();

			searchDetail.put("Origin", origin);
			searchDetail.put("Destination",destination);
			searchDetail.put("TravelDate",traveldate);


			jsonArray.put(searchDetail);
			avalJson.put("JourneyDetails", jsonArray);
			reJson.put("AvailabilityInput",avalJson );
			System.out.println("Request : "+reJson);

			resp=callFlightApi("GetAvailability", reJson);

			System.out.println("resp :: "+resp);



			int status=resp.getInt("ResponseStatus");
			System.out.println("Request : "+status);


			/*******************************************************************************************************/


			if (status==1)
			{
				HashMap<String , Object>RespMap=new HashMap<>();
				RespMap.put("UserTrackId", resp.getString("UserTrackId"));
				JSONObject AvailabilityOutputJSon=resp.optJSONObject("AvailabilityOutput");
				JSONObject AvailableFlightsJson=AvailabilityOutputJSon.optJSONObject("AvailableFlights");
				HashMap<String, Object> AvailableFlightsMap=new HashMap<>();
				JSONArray OngoingFlightsJArry=AvailableFlightsJson.optJSONArray("OngoingFlights");
				ArrayList<HashMap<String, Object>>OngoingFlightsList=new ArrayList<>();
				if(OngoingFlightsJArry!=null && OngoingFlightsJArry.length()>0)
				{
					for (int i = 0; i < OngoingFlightsJArry.length(); i++) {

						HashMap<String, Object>OngoingFlightsJMap=new HashMap<>();	
						ArrayList<HashMap<String, Object>>AvailSegmentsList=new ArrayList<>();
						JSONObject OngoingFlightsjson=OngoingFlightsJArry.getJSONObject(i);
						JSONArray AvailSegmentsary=OngoingFlightsjson.optJSONArray("AvailSegments");
						if(AvailSegmentsary!=null && AvailSegmentsary.length()>0)
						{
							for (int j = 0; j < AvailSegmentsary.length(); j++) {

								JSONObject AvailSegments=AvailSegmentsary.getJSONObject(j);
								HashMap<String, Object>sinmap=new HashMap<>();
								sinmap.put("FlightId",AvailSegments.getString("FlightId"));
								sinmap.put("AirlineCode",AvailSegments.getString("AirlineCode"));
								sinmap.put("FlightNumber",AvailSegments.getString("FlightNumber"));
								sinmap.put("AirCraftType",AvailSegments.getString("AirCraftType"));
								sinmap.put("Origin",AvailSegments.getString("Origin"));
								sinmap.put("OriginAirportTerminal",AvailSegments.getString("OriginAirportTerminal"));
								sinmap.put("Destination",AvailSegments.getString("Destination"));
								sinmap.put("DestinationAirportTerminal",AvailSegments.getString("DestinationAirportTerminal"));

								sinmap.put("ArrivalDateTime",AvailSegments.getString("ArrivalDateTime"));
								sinmap.put("DepartureDateTime",AvailSegments.getString("DepartureDateTime"));
								sinmap.put("Duration",AvailSegments.getString("Duration"));
								sinmap.put("NumberofStops",AvailSegments.getInt("NumberofStops"));
								sinmap.put("Via",AvailSegments.getString("Via"));
								sinmap.put("CurrencyCode",AvailSegments.getString("CurrencyCode"));
								sinmap.put("Currency_Conversion_Rate",AvailSegments.getString("Currency_Conversion_Rate"));

								/*String DepartureDateTime=AvailSegments.getString("DepartureDateTime");*/
								sinmap.put("SupplierId",AvailSegments.getString("SupplierId"));
								JSONArray AvailPaxFareDetailsArry=AvailSegments.optJSONArray("AvailPaxFareDetails");
								ArrayList<HashMap<String, Object>>AvailPaxFareDetailsList=new ArrayList<>();
								if(AvailPaxFareDetailsArry!=null && AvailPaxFareDetailsArry.length()>0)
								{
									for (int k = 0; k < AvailPaxFareDetailsArry.length(); k++) {

										JSONObject AvailPaxFareDetailsJson=AvailPaxFareDetailsArry.getJSONObject(k);
										HashMap<String, Object>AvailPaxFareDetailsMap=new HashMap<>();
										AvailPaxFareDetailsMap.put("ClassCode", AvailPaxFareDetailsJson.get("ClassCode"));
										AvailPaxFareDetailsMap.put("CancellationCharges", AvailPaxFareDetailsJson.get("CancellationCharges"));
										AvailPaxFareDetailsMap.put("ChangePenalty", AvailPaxFareDetailsJson.get("ChangePenalty"));
										AvailPaxFareDetailsMap.put("Child", AvailPaxFareDetailsJson.get("Child"));
										AvailPaxFareDetailsMap.put("ClassCodeDesc", AvailPaxFareDetailsJson.get("ClassCodeDesc"));
										JSONObject InfantJSon=AvailPaxFareDetailsJson.optJSONObject("Infant");
										if(InfantJSon!=null && InfantJSon.length()>0)
										{
											Infantmap=new HashMap<>();
											Infantmap.put("TotalTaxAmount", InfantJSon.get("TotalTaxAmount"));
											Infantmap.put("Commission", InfantJSon.get("Commission"));
											Infantmap.put("BasicAmount", InfantJSon.get("BasicAmount"));
											Infantmap.put("FareType", InfantJSon.get("FareType"));
											Infantmap.put("GrossAmount", InfantJSon.get("GrossAmount"));
											Infantmap.put("FareBasis", InfantJSon.get("FareBasis"));
											Infantmap.put("YQ", InfantJSon.get("YQ"));
											System.out.println("YQ::::::::::::::::::::::::"+InfantJSon.get("YQ"));
											JSONArray TaxDetailsArry=InfantJSon.optJSONArray("TaxDetails");
											ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
											if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
											{
												for (int l = 0; l < TaxDetailsArry.length(); l++) {
													JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
													HashMap< String, Object>TaxDetailsMap=new HashMap<>();
													TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
													TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
													TaxDetailsList.add(TaxDetailsMap);
												}
											}
											Infantmap.put("TaxDetails", TaxDetailsList);

										}
										JSONObject AdultJson=AvailPaxFareDetailsJson.getJSONObject("Adult");
										if(AdultJson!=null && AdultJson.length()>0)
										{

											AdultMap=new HashMap<>();
											AdultMap.put("TotalTaxAmount", AdultJson.get("TotalTaxAmount"));
											AdultMap.put("Commission", AdultJson.get("Commission"));
											AdultMap.put("BasicAmount", AdultJson.get("BasicAmount"));
											AdultMap.put("FareType", AdultJson.get("FareType"));
											AdultMap.put("GrossAmount", AdultJson.get("GrossAmount"));
											AdultMap.put("FareBasis", AdultJson.get("FareBasis"));
											AdultMap.put("YQ", AdultJson.get("YQ"));

											System.out.println("YQ::::::::::::::::::::::::"+AdultJson.get("YQ"));
											JSONArray TaxDetailsArry=AdultJson.optJSONArray("TaxDetails");
											ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
											if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
											{
												for (int l = 0; l < TaxDetailsArry.length(); l++) {
													JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
													HashMap< String, Object>TaxDetailsMap=new HashMap<>();
													TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
													TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
													TaxDetailsList.add(TaxDetailsMap);
												}
											}
											AdultMap.put("TaxDetails", TaxDetailsList);
										}
										HashMap<String, Object>ChildMap=null;
										JSONObject ChildJson=AvailPaxFareDetailsJson.optJSONObject("Child");
										if(ChildJson!=null && ChildJson.length()>0)
										{

											ChildMap=new HashMap<>();
											ChildMap.put("TotalTaxAmount", ChildJson.get("TotalTaxAmount"));
											ChildMap.put("Commission", ChildJson.get("Commission"));
											ChildMap.put("BasicAmount", ChildJson.get("BasicAmount"));
											ChildMap.put("FareType", ChildJson.get("FareType"));
											ChildMap.put("GrossAmount", ChildJson.get("GrossAmount"));
											ChildMap.put("FareBasis", ChildJson.get("FareBasis"));
											AdultMap.put("YQ", ChildJson.get("YQ"));

											System.out.println("YQ::::::::::::::::::::::::"+ChildJson.get("YQ"));
											JSONArray TaxDetailsArry=ChildJson.optJSONArray("TaxDetails");
											ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
											if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
											{
												for (int l = 0; l < TaxDetailsArry.length(); l++) {
													JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
													HashMap< String, Object>TaxDetailsMap=new HashMap<>();
													TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
													TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
													TaxDetailsList.add(TaxDetailsMap);
												}
											}
											ChildMap.put("TaxDetails", TaxDetailsList);
										}

										AvailPaxFareDetailsMap.put("Adult", AdultMap==null?new JSONObject():AdultMap);
										AvailPaxFareDetailsMap.put("Infant", Infantmap==null?new JSONObject():Infantmap);
										AvailPaxFareDetailsMap.put("Child", ChildMap==null?new JSONObject():ChildMap);
										AvailPaxFareDetailsList.add(AvailPaxFareDetailsMap);

									}


								}
								sinmap.put("AvailPaxFareDetails",AvailPaxFareDetailsList);






								AvailSegmentsList.add(sinmap);

							}
						}


						OngoingFlightsJMap.put("AvailSegments", AvailSegmentsList);
						OngoingFlightsList.add(OngoingFlightsJMap);

					}
					/*return OngoingFlightsList;*/
				}

				AvailableFlightsMap.put("OngoingFlights", OngoingFlightsList);

				RespMap.put("AvailabilityOutput", AvailableFlightsMap);




				mainList.add(RespMap);
				System.out.println("AvailableList :: "+mainList);


				return mainList;

			}
			else {

				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;


	}

	public HashMap<String, Object> getTaxRequest(String userTrackId, String flightId, String classCode,
			String airlineCode, String eTicketFlag, double totleAmount, String supplierId, String gSTNumber,
			String eMailId, String companyName, String contactNumber, String address, String firstName,
			String lastName){


		JSONObject reJson=new JSONObject();
		HashMap<String, Object>taxOutpuMap=null;

		JSONObject avalJson=null;
		JSONArray arrayList=new JSONArray();
		JSONObject texDtails=null;

		try {

			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);

			reJson.put("Authentication",obj);
			reJson.put("UserTrackId", userTrackId);
			texDtails=new JSONObject();

			texDtails.put("FlightId", flightId);
			texDtails.put("ClassCode",classCode);
			texDtails.put("AirlineCode",airlineCode);
			texDtails.put("ETicketFlag",eTicketFlag);
			texDtails.put("BasicAmount",totleAmount);
			texDtails.put("SupplierId",supplierId);

			arrayList.put(texDtails);
			
			JSONObject GSTDetailsjson=new JSONObject();
			
			
			GSTDetailsjson.put("GSTNumber", gSTNumber);
			GSTDetailsjson.put("EMailId",eMailId);
			GSTDetailsjson.put("companyName",companyName);
			GSTDetailsjson.put("ContactNumber",contactNumber);
			GSTDetailsjson.put("Address",address);
			GSTDetailsjson.put("FirstName",firstName);
			GSTDetailsjson.put("LastName",lastName);
			
			
			
			
			avalJson=new JSONObject();
			avalJson.put("TaxReqFlightSegments", arrayList);
			avalJson.put("GSTDetails", GSTDetailsjson);
			reJson.put("TaxInput",avalJson );
			System.out.println("Request : "+reJson);
			JSONObject resp =new JSONObject();
			resp=callFlightApi("GetTax", reJson);

			System.out.println("resp :: "+resp);



			int status=resp.getInt("ResponseStatus");
			if (status==1) 
			{
				JSONObject taxOutputJson=(JSONObject) (resp.getJSONObject("TaxOutput")==null?"":resp.getJSONObject("TaxOutput")); 	

				taxOutpuMap=new HashMap<>();
				taxOutpuMap.put("UserTrackId", resp.get("UserTrackId"));
				taxOutpuMap.put("BaggageAndMealsFlag", taxOutputJson.get("BaggageAndMealsFlag"));
				taxOutpuMap.put("Baggages", taxOutputJson.get("Baggages"));
				taxOutpuMap.put("SpecialServices", taxOutputJson.get("SpecialServices"));
				JSONArray taxResFlightSegmentsArry=taxOutputJson.optJSONArray("TaxResFlightSegments");
				ArrayList<HashMap<String, Object>>TaxResFlightSegmentsList=new ArrayList<>();
				if(taxResFlightSegmentsArry!=null && taxResFlightSegmentsArry.length()>0)
				{
					for (int i = 0; i < taxResFlightSegmentsArry.length(); i++) {
						JSONObject taxResFlightSegmentsJSON=taxResFlightSegmentsArry.getJSONObject(i);
						HashMap<String, Object> taxResFlightSegmentsMap=new HashMap<>();
						taxResFlightSegmentsMap.put("Meals", taxResFlightSegmentsJSON.get("Meals"));
						/*taxResFlightSegmentsMap.put("ChildTax", taxResFlightSegmentsJSON.get("ChildTax"));
						taxResFlightSegmentsMap.put("InfantTax", taxResFlightSegmentsJSON.get("InfantTax"));*/
						taxResFlightSegmentsMap.put("Baggages", taxResFlightSegmentsJSON.get("Baggages"));
						taxResFlightSegmentsMap.put("FlightId", taxResFlightSegmentsJSON.get("FlightId"));
						JSONObject adultTaxjson=taxResFlightSegmentsJSON.getJSONObject("AdultTax");
						HashMap<String, Object>FareBreakUpDetailsMap=null;
						if(adultTaxjson!=null & adultTaxjson.length()>0)
						{
							JSONObject FareBreakUpDetailsJson=adultTaxjson.getJSONObject("FareBreakUpDetails");
							if(FareBreakUpDetailsJson!=null && FareBreakUpDetailsJson.length()>0)
							{
								FareBreakUpDetailsMap=new HashMap<>();
								FareBreakUpDetailsMap.put("TotalTaxAmount", FareBreakUpDetailsJson.get("TotalTaxAmount"));
								FareBreakUpDetailsMap.put("Commission", FareBreakUpDetailsJson.get("Commission"));
								FareBreakUpDetailsMap.put("BasicAmount", FareBreakUpDetailsJson.get("BasicAmount"));
								FareBreakUpDetailsMap.put("BasicCurrencyCode", FareBreakUpDetailsJson.get("BasicCurrencyCode"));
								FareBreakUpDetailsMap.put("TransactionFee", FareBreakUpDetailsJson.get("TransactionFee"));
								FareBreakUpDetailsMap.put("EquivalentFare", FareBreakUpDetailsJson.get("EquivalentFare"));
								FareBreakUpDetailsMap.put("GrossAmount", FareBreakUpDetailsJson.get("GrossAmount"));
								FareBreakUpDetailsMap.put("CurrencyCode", FareBreakUpDetailsJson.get("CurrencyCode"));
								FareBreakUpDetailsMap.put("ServiceCharge", FareBreakUpDetailsJson.get("ServiceCharge"));
								JSONArray TaxDetailsArry=FareBreakUpDetailsJson.optJSONArray("TaxDetails");
								ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
								if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
								{
									for (int l = 0; l < TaxDetailsArry.length(); l++) {
										JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
										HashMap< String, Object>TaxDetailsMap=new HashMap<>();
										TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
										TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
										TaxDetailsList.add(TaxDetailsMap);
									}
								}
								FareBreakUpDetailsMap.put("TaxDetails", TaxDetailsList);



							}
						}
						JSONObject childTaxjson=taxResFlightSegmentsJSON.optJSONObject("ChildTax");
						HashMap<String, Object>FareBreakUpDetailschildMap=null;
						if(childTaxjson!=null && childTaxjson.length()>0)
						{
							JSONObject FareBreakUpDetailsJson=childTaxjson.getJSONObject("FareBreakUpDetails");
							if(FareBreakUpDetailsJson!=null && FareBreakUpDetailsJson.length()>0)
							{
								FareBreakUpDetailschildMap=new HashMap<>();
								FareBreakUpDetailschildMap.put("TotalTaxAmount", FareBreakUpDetailsJson.get("TotalTaxAmount"));
								FareBreakUpDetailschildMap.put("Commission", FareBreakUpDetailsJson.get("Commission"));
								FareBreakUpDetailschildMap.put("BasicAmount", FareBreakUpDetailsJson.get("BasicAmount"));
								FareBreakUpDetailschildMap.put("BasicCurrencyCode", FareBreakUpDetailsJson.get("BasicCurrencyCode"));
								FareBreakUpDetailschildMap.put("TransactionFee", FareBreakUpDetailsJson.get("TransactionFee"));
								FareBreakUpDetailschildMap.put("EquivalentFare", FareBreakUpDetailsJson.get("EquivalentFare"));
								FareBreakUpDetailschildMap.put("GrossAmount", FareBreakUpDetailsJson.get("GrossAmount"));
								FareBreakUpDetailschildMap.put("CurrencyCode", FareBreakUpDetailsJson.get("CurrencyCode"));
								FareBreakUpDetailschildMap.put("ServiceCharge", FareBreakUpDetailsJson.get("ServiceCharge"));
								JSONArray TaxDetailsArry=FareBreakUpDetailsJson.optJSONArray("TaxDetails");
								ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
								if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
								{
									for (int l = 0; l < TaxDetailsArry.length(); l++) {
										JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
										HashMap< String, Object>TaxDetailsMap=new HashMap<>();
										TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
										TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
										TaxDetailsList.add(TaxDetailsMap);
									}
								}
								FareBreakUpDetailsMap.put("TaxDetails", TaxDetailsList);



							}
						}

						JSONObject InfantTaxjson=taxResFlightSegmentsJSON.optJSONObject("InfantTax");
						HashMap<String, Object>FareBreakUpDetailsInfantMap=null;
						if(InfantTaxjson!=null && InfantTaxjson.length()>0)
						{
							JSONObject FareBreakUpDetailsJson=InfantTaxjson.getJSONObject("FareBreakUpDetails");
							if(FareBreakUpDetailsJson!=null && FareBreakUpDetailsJson.length()>0)
							{
								FareBreakUpDetailsInfantMap=new HashMap<>();
								FareBreakUpDetailsInfantMap.put("TotalTaxAmount", FareBreakUpDetailsJson.get("TotalTaxAmount"));
								FareBreakUpDetailsInfantMap.put("Commission", FareBreakUpDetailsJson.get("Commission"));
								FareBreakUpDetailsInfantMap.put("BasicAmount", FareBreakUpDetailsJson.get("BasicAmount"));
								FareBreakUpDetailsInfantMap.put("BasicCurrencyCode", FareBreakUpDetailsJson.get("BasicCurrencyCode"));
								FareBreakUpDetailsInfantMap.put("TransactionFee", FareBreakUpDetailsJson.get("TransactionFee"));
								FareBreakUpDetailsInfantMap.put("EquivalentFare", FareBreakUpDetailsJson.get("EquivalentFare"));
								FareBreakUpDetailsInfantMap.put("GrossAmount", FareBreakUpDetailsJson.get("GrossAmount"));
								FareBreakUpDetailsInfantMap.put("CurrencyCode", FareBreakUpDetailsJson.get("CurrencyCode"));
								FareBreakUpDetailsInfantMap.put("ServiceCharge", FareBreakUpDetailsJson.get("ServiceCharge"));
								JSONArray TaxDetailsArry=FareBreakUpDetailsJson.optJSONArray("TaxDetails");
								ArrayList< HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
								if(TaxDetailsArry!=null && TaxDetailsArry.length()>0)
								{
									for (int l = 0; l < TaxDetailsArry.length(); l++) {
										JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(l);
										HashMap< String, Object>TaxDetailsMap=new HashMap<>();
										TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
										TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
										TaxDetailsList.add(TaxDetailsMap);
									}
								}
								FareBreakUpDetailsMap.put("TaxDetails", TaxDetailsList);



							}
						}
						else{

						}






						taxResFlightSegmentsMap.put("AdultTax", FareBreakUpDetailsMap);
						taxResFlightSegmentsMap.put("ChildTax", FareBreakUpDetailschildMap);
						taxResFlightSegmentsMap.put("InfantTax", FareBreakUpDetailsInfantMap);
						TaxResFlightSegmentsList.add(taxResFlightSegmentsMap);

					}
				}
				JSONObject RequiredFieldsjson=taxOutputJson.optJSONObject("RequiredFields") ;
				HashMap<String, Object>RequiredFieldsMap=null;
				if(RequiredFieldsjson!=null && RequiredFieldsjson.length()>0)
				{
					RequiredFieldsMap=new HashMap<>();
					JSONObject InfantReqFieldsJson=RequiredFieldsjson.getJSONObject("InfantReqFields");
					HashMap<String, Object>InfantReqFieldsMap=null;
					if(InfantReqFieldsJson!=null && InfantReqFieldsJson.length()>0)
					{
						InfantReqFieldsMap=new  HashMap<>();
						InfantReqFieldsMap.put("Age", InfantReqFieldsJson.get("Age"));
						InfantReqFieldsMap.put("DateOfBirth", InfantReqFieldsJson.get("DateOfBirth"));
						InfantReqFieldsMap.put("Gender", InfantReqFieldsJson.get("Gender"));
						InfantReqFieldsMap.put("PassportIssuingCountry", InfantReqFieldsJson.get("PassportIssuingCountry"));
						InfantReqFieldsMap.put("PassportExpiryDate", InfantReqFieldsJson.get("PassportExpiryDate"));
						InfantReqFieldsMap.put("PassportNumber", InfantReqFieldsJson.get("PassportNumber"));
						InfantReqFieldsMap.put("Nationality", InfantReqFieldsJson.get("Nationality"));
						InfantReqFieldsMap.put("IdProof", InfantReqFieldsJson.get("IdProof"));


					}
					JSONObject ChildReqFieldsJson=RequiredFieldsjson.getJSONObject("ChildReqFields");
					HashMap<String, Object>ChildReqFieldsMap=null;
					if(ChildReqFieldsJson!=null && ChildReqFieldsJson.length()>0)
					{
						ChildReqFieldsMap=new  HashMap<>();
						ChildReqFieldsMap.put("Age", ChildReqFieldsJson.get("Age"));
						ChildReqFieldsMap.put("DateOfBirth", ChildReqFieldsJson.get("DateOfBirth"));
						ChildReqFieldsMap.put("Gender", ChildReqFieldsJson.get("Gender"));
						ChildReqFieldsMap.put("PassportIssuingCountry", ChildReqFieldsJson.get("PassportIssuingCountry"));
						ChildReqFieldsMap.put("PassportExpiryDate", ChildReqFieldsJson.get("PassportExpiryDate"));
						ChildReqFieldsMap.put("PassportNumber", ChildReqFieldsJson.get("PassportNumber"));
						ChildReqFieldsMap.put("Nationality", ChildReqFieldsJson.get("Nationality"));
						ChildReqFieldsMap.put("IdProof", ChildReqFieldsJson.get("IdProof"));


					}
					JSONObject AdultReqFieldsJson=RequiredFieldsjson.getJSONObject("AdultReqFields");
					HashMap<String, Object>AdultReqFieldsJsonMap=null;
					if(AdultReqFieldsJson!=null && AdultReqFieldsJson.length()>0)
					{
						AdultReqFieldsJsonMap=new  HashMap<>();
						AdultReqFieldsJsonMap.put("Age", AdultReqFieldsJson.get("Age"));
						AdultReqFieldsJsonMap.put("DateOfBirth", AdultReqFieldsJson.get("DateOfBirth"));
						AdultReqFieldsJsonMap.put("Gender", AdultReqFieldsJson.get("Gender"));
						AdultReqFieldsJsonMap.put("PassportIssuingCountry", AdultReqFieldsJson.get("PassportIssuingCountry"));
						AdultReqFieldsJsonMap.put("PassportExpiryDate", AdultReqFieldsJson.get("PassportExpiryDate"));
						AdultReqFieldsJsonMap.put("PassportNumber", AdultReqFieldsJson.get("PassportNumber"));
						AdultReqFieldsJsonMap.put("Nationality", AdultReqFieldsJson.get("Nationality"));
						AdultReqFieldsJsonMap.put("IdProof", AdultReqFieldsJson.get("IdProof"));


					}

					RequiredFieldsMap.put("InfantReqFields", InfantReqFieldsMap);
					RequiredFieldsMap.put("ChildReqFields", ChildReqFieldsMap);
					RequiredFieldsMap.put("AdultReqFields", AdultReqFieldsJsonMap);

				}




				JSONObject Mealsjson=taxOutputJson.optJSONObject("Meals") ;
				HashMap<String, Object>MealsMap=new HashMap<>();

				if(Mealsjson!=null && Mealsjson.length()>0)
				{
					ArrayList<HashMap<String, Object>> AdultMealList=new ArrayList<>();
					JSONArray AdultMealsArray=Mealsjson.getJSONArray("AdultMeals");

					if(AdultMealsArray!=null && AdultMealsArray.length()>0)
					{
						for (int i = 0; i < AdultMealsArray.length(); i++) {
							JSONObject AdultMealJson=AdultMealsArray.getJSONObject(i);

							HashMap<String, Object>AdultMealMap=new  HashMap<>();
							AdultMealMap.put("MealDescription", AdultMealJson.get("MealDescription"));
							AdultMealMap.put("MealAmount", AdultMealJson.get("MealAmount"));
							AdultMealMap.put("MealCode", AdultMealJson.get("MealCode"));
							AdultMealList.add(AdultMealMap);
						}


					}

					ArrayList<HashMap<String, Object>> ChildMealsList=new ArrayList<>();
					JSONArray ChildMealsArray=Mealsjson.optJSONArray("ChildMeals");

					if(ChildMealsArray!=null && ChildMealsArray.length()>0)
					{
						for (int i = 0; i < ChildMealsArray.length(); i++) {
							JSONObject ChildMealsJson=ChildMealsArray.getJSONObject(i);

							HashMap<String, Object>ChildMealsMap=new  HashMap<>();
							ChildMealsMap.put("MealDescription", ChildMealsJson.get("MealDescription"));
							ChildMealsMap.put("MealAmount", ChildMealsJson.get("MealAmount"));
							ChildMealsMap.put("MealCode", ChildMealsJson.get("MealCode"));
							ChildMealsList.add(ChildMealsMap);
						}


					}

					MealsMap.put("AdultMeals", AdultMealList)	; 
					MealsMap.put("ChildMeals", ChildMealsList==null?"":ChildMealsList)	;



				}


				taxOutpuMap.put("RequiredFieldsMap", MealsMap);
				taxOutpuMap.put("RequiredFieldsMap", RequiredFieldsMap);
				taxOutpuMap.put("TaxResFlightSegments", TaxResFlightSegmentsList);
				taxOutpuMap.put("status", "0");
				taxOutpuMap.put("message", "Success");


				System.out.println();


			}

			else{
				String remark=resp.getString("FailureRemarks");
				String UserTrackId= resp.getString("UserTrackId");
				taxOutpuMap=new HashMap<>();

				taxOutpuMap.put("message", remark);
				taxOutpuMap.put("UserTrackId", UserTrackId);
				taxOutpuMap.put("status", "1");


			}






		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return taxOutpuMap;




		// TODO Auto-generated method stub

	}





	public HashMap<String, Object> getBookRequest(String UserTrackId,String Title,String Name,String Address,
			String City,String CountryId,String ContactNumber,String EmailId,String PinCode,String AirlineCode,String BookingType,
			String AdultCount,String ChildCount
			,String InfantCount,String TotalAmount,net.sf.json.JSONArray flightBookingDetailsary)
	{
		HashMap<String, Object> bookOutputMap=null;
		ArrayList<HashMap<String, Object>>BookedSegmentslist=null;
		ArrayList<HashMap<String, Object>> PassengerDetailsArry=null;

		JSONObject resp=null;
		try {

			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);

			JSONObject reJson=new JSONObject();

			reJson.put("Authentication",obj);
			reJson.put("UserTrackId", UserTrackId);
			JSONObject bookInputjsn=new JSONObject();

			bookInputjsn.put("SpecialRemarks", "");
			bookInputjsn.put("NotifyByMail"," 0");
			bookInputjsn.put("NotifyBySMS","0" );
			bookInputjsn.put("AdultCount",AdultCount );
			bookInputjsn.put("ChildCount",ChildCount );
			bookInputjsn.put("InfantCount",InfantCount);
			bookInputjsn.put("BookingType",BookingType);
			bookInputjsn.put("TotalAmount", TotalAmount);
			bookInputjsn.put("FrequentFlyerRequest",bookInputjsn.optJSONObject("FrequentFlyerRequest"));
			bookInputjsn.put("SpecialServiceRequest",bookInputjsn.optJSONObject("SpecialServiceRequest"));
			bookInputjsn.put("FSCMealsRequest",bookInputjsn.optJSONObject("FSCMealsRequest"));


			JSONObject CustomerDetailsJsn=new JSONObject();

			CustomerDetailsJsn=new JSONObject();
			CustomerDetailsJsn.put("Title",Title);
			CustomerDetailsJsn.put("Name", Name);
			CustomerDetailsJsn.put("Address",Address);
			CustomerDetailsJsn.put("City", City);
			CustomerDetailsJsn.put("CountryId", "91");
			CustomerDetailsJsn.put("ContactNumber", ContactNumber);
			CustomerDetailsJsn.put("EmailId", EmailId);
			CustomerDetailsJsn.put("PinCode", PinCode);
			bookInputjsn.put("CustomerDetails", CustomerDetailsJsn);

			JSONObject FlightBookingDetailsJSN=new JSONObject();
			FlightBookingDetailsJSN.put("AirlineCode",AirlineCode);
			FlightBookingDetailsJSN.put("TourCode", "");





			JSONObject PaymentDetailsJsn=new JSONObject();

			PaymentDetailsJsn.put("CurrencyCode", "INR");
			PaymentDetailsJsn.put("Amount",TotalAmount );

			FlightBookingDetailsJSN.put("PaymentDetails", PaymentDetailsJsn);



			bookInputjsn.put("FlightBookingDetails", flightBookingDetailsary);


			reJson.put("BookInput", bookInputjsn);



			System.out.println("Request : "+reJson);
			resp=FlightApi.callFlightApi("GetBook", reJson);


			System.out.println("resp : "+resp);


			int status=resp.getInt("ResponseStatus");

			if (status==1) {


				HashMap<String, Object> mainMap=new  HashMap<>();
				mainMap.put("UserTrackId", resp.get("UserTrackId"));


				//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......BookOutput Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



				JSONObject bookOutputJsn=resp.getJSONObject("BookOutput");
				bookOutputMap=new HashMap<>();
				bookOutputMap.put("BlockingDetails", bookOutputJsn.get("BlockingDetails"));

				ArrayList<HashMap<String, Object>>FlightTicketDetailsArry=new ArrayList<>();
				JSONArray flightTicketDetailsarry=bookOutputJsn.getJSONArray("FlightTicketDetails");
				if(flightTicketDetailsarry.length()>0)
				{ 
					for (int i = 0; i < flightTicketDetailsarry.length(); i++) {

						JSONObject flightTicketDetailsJsn=flightTicketDetailsarry.getJSONObject(i);

						HashMap<String,Object> flightTicketDetailsMap=new HashMap<>();

						flightTicketDetailsMap.put("HermesPNR", flightTicketDetailsJsn.getString("HermesPNR"));
						flightTicketDetailsMap.put("TransactionId", flightTicketDetailsJsn.getString("TransactionId"));
						flightTicketDetailsMap.put("TotalSegments", flightTicketDetailsJsn.getString("TotalSegments"));
						flightTicketDetailsMap.put("TotalAmount", flightTicketDetailsJsn.getString("TotalAmount"));
						flightTicketDetailsMap.put("OtherCharges", flightTicketDetailsJsn.getString("OtherCharges"));
						flightTicketDetailsMap.put("AdultCount", flightTicketDetailsJsn.getString("AdultCount"));
						flightTicketDetailsMap.put("ChildCount", flightTicketDetailsJsn.getString("ChildCount"));
						flightTicketDetailsMap.put("InfantCount", flightTicketDetailsJsn.getString("InfantCount"));
						flightTicketDetailsMap.put("BookingType", flightTicketDetailsJsn.getString("BookingType"));
						flightTicketDetailsMap.put("TravelType", flightTicketDetailsJsn.getString("TravelType"));
						flightTicketDetailsMap.put("IssueDateTime", flightTicketDetailsJsn.getString("IssueDateTime"));
						flightTicketDetailsMap.put("BaseOrigin", flightTicketDetailsJsn.getString("BaseOrigin"));
						flightTicketDetailsMap.put("BaseDestination", flightTicketDetailsJsn.getString("BaseDestination"));
						flightTicketDetailsMap.put("TourCode", flightTicketDetailsJsn.getString("TourCode"));


						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......CustomerDetails Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

						JSONObject customerDetailsJsn=flightTicketDetailsJsn.getJSONObject("CustomerDetails");
						HashMap<String, Object>customerDetailsMap=null;
						if(customerDetailsJsn!=null && customerDetailsJsn.length()>0)
						{
							customerDetailsMap=new HashMap<>();



							customerDetailsMap.put("Title", customerDetailsJsn.getString("Title")==null?"":customerDetailsJsn.getString("Title"));
							customerDetailsMap.put("Name", customerDetailsJsn.getString("Name"));
							customerDetailsMap.put("Address", customerDetailsJsn.getString("Address"));
							customerDetailsMap.put("City", customerDetailsJsn.getString("City"));
							customerDetailsMap.put("CountryId", customerDetailsJsn.getString("CountryId"));
							customerDetailsMap.put("ContactNumber", customerDetailsJsn.getString("ContactNumber"));
							customerDetailsMap.put("EmailId", customerDetailsJsn.getString("EmailId"));
							customerDetailsMap.put("PinCode", customerDetailsJsn.getString("PinCode"));
						}
						flightTicketDetailsMap.put("CustomerDetails", customerDetailsMap==null?"":customerDetailsMap);


						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......IATADetails Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

						JSONObject IATADetailsJsn=flightTicketDetailsJsn.getJSONObject("IATADetails");
						HashMap<String, Object> airLineMap=null;
						if(IATADetailsJsn!=null && IATADetailsJsn.length()>0)
						{
							airLineMap=new HashMap<>();

							airLineMap.put("IATAAgentName1", IATADetailsJsn.getString("IATAAgentName1"));
							airLineMap.put("IATAAgentName2", IATADetailsJsn.getString("IATAAgentName2"));
							airLineMap.put("IATAAgentNumber", IATADetailsJsn.getString("IATAAgentNumber"));
							airLineMap.put("CRSPNR", IATADetailsJsn.getString("CRSPNR"));
							airLineMap.put("TicketNumber", IATADetailsJsn.getString("TicketNumber")==null?"":IATADetailsJsn.getString("TicketNumber"));

						}
						flightTicketDetailsMap.put(("IATADetails"), airLineMap);


						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......AirLine Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


						ArrayList<HashMap<String, Object>>AirlineDetailsArry=new ArrayList<>();
						JSONArray AirlineDetailsAry=flightTicketDetailsJsn.getJSONArray("AirlineDetails");

						if(AirlineDetailsAry.length()>0)
						{
							for (int j = 0; j < AirlineDetailsAry.length(); j++) {


								JSONObject airlineDetailsJsn=AirlineDetailsAry.getJSONObject(i);

								HashMap<String,Object> airlineDetailsMap=new HashMap<>();


								airlineDetailsMap.put("AirlineCode", airlineDetailsJsn.getString("AirlineCode"));
								airlineDetailsMap.put("AirlinePNR", airlineDetailsJsn.getString("AirlinePNR"));
								airlineDetailsMap.put("AirlineName", airlineDetailsJsn.getString("AirlineName"));
								airlineDetailsMap.put("Address1", airlineDetailsJsn.getString("Address1"));
								airlineDetailsMap.put("Address2", airlineDetailsJsn.getString("Address2"));
								airlineDetailsMap.put("City", airlineDetailsJsn.getString("City"));
								airlineDetailsMap.put("ContactNumber", airlineDetailsJsn.getString("ContactNumber"));
								airlineDetailsMap.put("FaxNumber", airlineDetailsJsn.getString("FaxNumber"));
								airlineDetailsMap.put("EMailId", airlineDetailsJsn.getString("EMailId"));
								AirlineDetailsArry.add(airlineDetailsMap);


							}

						}
						flightTicketDetailsMap.put("AirlineDetails", AirlineDetailsArry);



						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.....PassengerDetails. Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


						PassengerDetailsArry=new ArrayList<>();
						HashMap< String, Object>PassengerDetailsmap=new HashMap<>();

						JSONArray PassengerDetailsAry=flightTicketDetailsJsn.optJSONArray("PassengerDetails");
						System.out.println("PassengerDetailsAry:::::::::::"+PassengerDetailsAry);

						if(PassengerDetailsAry.length()>0)
						{
							for (int j = 0; j < PassengerDetailsAry.length(); j++) {
								
								
								JSONObject pasangerDetailsjson=PassengerDetailsAry.getJSONObject(j);
								JSONObject ladderDetailsJson=pasangerDetailsjson.getJSONObject("LadderDetails");
								HashMap<String, Object>ladderDetailsMap=new HashMap<>();
								if(ladderDetailsJson!=null && ladderDetailsJson.length()>0)
								{
									ladderDetailsMap.put("IssueInExchangeFor", ladderDetailsJson.getString("IssueInExchangeFor"));
									ladderDetailsMap.put("FareCalculation", ladderDetailsJson.getString("FareCalculation"));
									ladderDetailsMap.put("EndorsementRestriction", ladderDetailsJson.getString("EndorsementRestriction"));
									
									
								}
								
								
								
								JSONArray BookedSegmentsArry=pasangerDetailsjson.getJSONArray("BookedSegments");
								BookedSegmentslist=new ArrayList<>();
								if(BookedSegmentsArry!=null && BookedSegmentsArry.length()>0)
								{
									for (int k = 0; k < BookedSegmentsArry.length(); k++) {

										JSONObject BookedSegmentsjson=BookedSegmentsArry.getJSONObject(k);
										HashMap<String, Object>BookedSegmentsMap=new HashMap<>();
										BookedSegmentsMap.put("TicketNumber", BookedSegmentsjson.get("TicketNumber"));
										BookedSegmentsMap.put("FlightNumber", BookedSegmentsjson.get("FlightNumber"));
										BookedSegmentsMap.put("AirCraftType", BookedSegmentsjson.get("AirCraftType"));
										BookedSegmentsMap.put("Origin", BookedSegmentsjson.get("Origin"));
										BookedSegmentsMap.put("OriginAirport", BookedSegmentsjson.get("OriginAirport"));
										BookedSegmentsMap.put("DepartureDateTime", BookedSegmentsjson.get("DepartureDateTime"));
										BookedSegmentsMap.put("Destination", BookedSegmentsjson.get("Destination"));
										BookedSegmentsMap.put("DestinationAirport", BookedSegmentsjson.get("DestinationAirport"));
										BookedSegmentsMap.put("Arrivaldatetime", BookedSegmentsjson.get("Arrivaldatetime"));
										BookedSegmentsMap.put("AirlineCode", BookedSegmentsjson.get("AirlineCode"));
										BookedSegmentsMap.put("ClassCode", BookedSegmentsjson.get("ClassCode"));
										BookedSegmentsMap.put("ClassCodeDesc", BookedSegmentsjson.get("ClassCodeDesc"));
										BookedSegmentsMap.put("FareBasis", BookedSegmentsjson.get("FareBasis"));
										BookedSegmentsMap.put("BaggageAllowed", BookedSegmentsjson.get("BaggageAllowed"));
										BookedSegmentsMap.put("StopOverAllowed", BookedSegmentsjson.get("StopOverAllowed"));
										BookedSegmentsMap.put("FrequentFlyerId", BookedSegmentsjson.get("FrequentFlyerId"));
										BookedSegmentsMap.put("FrequentFlyerNumber", BookedSegmentsjson.get("FrequentFlyerNumber"));
										BookedSegmentsMap.put("SpecialServiceCode", BookedSegmentsjson.get("SpecialServiceCode"));
										BookedSegmentsMap.put("MealCode", BookedSegmentsjson.get("MealCode"));
										BookedSegmentsMap.put("SeatPreferId", BookedSegmentsjson.get("SeatPreferId"));
										BookedSegmentsMap.put("BasicCurrencyCode", BookedSegmentsjson.get("BasicCurrencyCode"));
										BookedSegmentsMap.put("CurrencyCode", BookedSegmentsjson.get("CurrencyCode"));
										BookedSegmentsMap.put("BasicAmount", BookedSegmentsjson.get("BasicAmount"));
										BookedSegmentsMap.put("EquivalentFare", BookedSegmentsjson.get("EquivalentFare"));
										BookedSegmentsMap.put("TotalTaxAmount", BookedSegmentsjson.get("TotalTaxAmount"));
										BookedSegmentsMap.put("TransactionFee", BookedSegmentsjson.get("TransactionFee"));
										BookedSegmentsMap.put("ServiceCharge", BookedSegmentsjson.get("ServiceCharge"));
										BookedSegmentsMap.put("GrossAmount", BookedSegmentsjson.get("GrossAmount"));
										BookedSegmentsMap.put("AdditionalSSRDetails", BookedSegmentsjson.get("AdditionalSSRDetails"));
										ArrayList<HashMap<String,Object>> TaxDetailsArraylist=new ArrayList<>();
										JSONArray TaxDetailsAry=BookedSegmentsjson.getJSONArray("TaxDetails");
										if (TaxDetailsAry.length()>0) {

											for (int l = 0; l < TaxDetailsAry.length(); l++) {

												JSONObject TaxDetailsJsn=TaxDetailsAry.getJSONObject(l);
												HashMap<String, Object> TaxDetailsMap=new HashMap<>();

												TaxDetailsMap.put("Description", TaxDetailsJsn.getString("Description"));
												TaxDetailsMap.put("EquivalentFare", TaxDetailsJsn.getInt("EquivalentFare"));
												TaxDetailsArraylist.add(TaxDetailsMap);

											}



										}
										BookedSegmentsMap.put("TaxDetailsArraylist", TaxDetailsArraylist);
										BookedSegmentslist.add(BookedSegmentsMap);

									}
								}
								
								
								PassengerDetailsmap.put("PassengerType", pasangerDetailsjson.get("PassengerType"));
								PassengerDetailsmap.put("Age", pasangerDetailsjson.get("Age"));
								PassengerDetailsmap.put("IdentityProofNumber", pasangerDetailsjson.get("IdentityProofNumber"));
								PassengerDetailsmap.put("PersonOrgId", pasangerDetailsjson.get("PersonOrgId"));
								PassengerDetailsmap.put("FirstName", pasangerDetailsjson.get("FirstName"));
								PassengerDetailsmap.put("LastName", pasangerDetailsjson.get("LastName"));
								PassengerDetailsmap.put("IdentityProofId", pasangerDetailsjson.get("IdentityProofId"));
								PassengerDetailsmap.put("Title", pasangerDetailsjson.get("Title"));
								PassengerDetailsmap.put("TransmissionControlNo", pasangerDetailsjson.get("TransmissionControlNo"));
								PassengerDetailsmap.put("TicketNumber", pasangerDetailsjson.get("TicketNumber"));
								PassengerDetailsmap.put("BookedSegments", BookedSegmentslist);
								
								
								
								PassengerDetailsArry.add(PassengerDetailsmap);
								
							}
							
							flightTicketDetailsMap.put("PassengerDetails", PassengerDetailsArry);
							
						}
						

						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.....PassengerDetails. End.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......PaymentDetails Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


						JSONObject paymentDetailsJsn=flightTicketDetailsJsn.getJSONObject("PaymentDetails");

						HashMap< String, Object> paymentDetailsMap=new HashMap<>();


						paymentDetailsMap.put("CurrencyCode", paymentDetailsJsn.getString("CurrencyCode"));
						paymentDetailsMap.put("Amount", paymentDetailsJsn.getInt("Amount"));
						paymentDetailsMap.put("Amount", paymentDetailsJsn.getInt("Amount"));

						JSONObject AirlinePaymentDetailsjsn=paymentDetailsJsn.getJSONObject("AirlinePaymentDetails");
						HashMap<String, Object>AirlinePaymentDetailsMap=new HashMap<>();
						JSONObject CreditCardDetailsjsn=AirlinePaymentDetailsjsn.getJSONObject("CreditCardDetails");
						HashMap<String, Object>CreditCardDetailsmap=new HashMap<>();

						CreditCardDetailsmap.put("CardNumber",CreditCardDetailsjsn.get("CardNumber")==null?"":CreditCardDetailsjsn.get("CardNumber"));
						CreditCardDetailsmap.put("CardType",CreditCardDetailsjsn.get("CardType")==null?"":CreditCardDetailsjsn.get("CardType"));
						AirlinePaymentDetailsMap.put("CreditCardDetails", CreditCardDetailsmap);
						AirlinePaymentDetailsMap.put("PaymentType", AirlinePaymentDetailsjsn.getString("PaymentType"));
						AirlinePaymentDetailsMap.put("VendorCode", AirlinePaymentDetailsjsn.getString("VendorCode"));
						AirlinePaymentDetailsMap.put("TicketingPCC", AirlinePaymentDetailsjsn.getString("TicketingPCC"));
						paymentDetailsMap.put("AirlinePaymentDetails", AirlinePaymentDetailsMap);










						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>......PaymentDetails End.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.....TerminalContactDetails. Start.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

						JSONObject TerminalContactDetailsJsn=flightTicketDetailsJsn.getJSONObject("TerminalContactDetails");
						HashMap<String, Object> TerminalContactDetailsMap=new HashMap<>();


						TerminalContactDetailsMap.put("TerminalName", TerminalContactDetailsJsn.getString("TerminalName"));
						TerminalContactDetailsMap.put("Address1", TerminalContactDetailsJsn.getString("Address1"));
						TerminalContactDetailsMap.put("Address2", TerminalContactDetailsJsn.getString("Address2"));
						TerminalContactDetailsMap.put("City", TerminalContactDetailsJsn.getString("City"));
						TerminalContactDetailsMap.put("State", TerminalContactDetailsJsn.getString("State"));
						TerminalContactDetailsMap.put("Country", TerminalContactDetailsJsn.getString("Country"));
						TerminalContactDetailsMap.put("ContactNumber", TerminalContactDetailsJsn.getString("ContactNumber"));
						TerminalContactDetailsMap.put("EmailId", TerminalContactDetailsJsn.getString("EmailId"));

						flightTicketDetailsMap.put("TerminalContactDetails", TerminalContactDetailsMap);





						//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.....TerminalContactDetails. End.............<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



						FlightTicketDetailsArry.add(flightTicketDetailsMap);

					}


				}
				bookOutputMap.put("FlightTicketDetails", FlightTicketDetailsArry);
				bookOutputMap.put("message", "success");
				




			}
			else {
				bookOutputMap=new HashMap<>();
				
				bookOutputMap.put("message", resp.get("FailureRemarks"));
				bookOutputMap.put("UserTrackId", resp.get("UserTrackId"));
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("bookOutputMap"+bookOutputMap);
		return bookOutputMap;
		
		
	}







	public HashMap<String, Object> GetTransactionStatusRequest( String UserTrackId)
	{
		HashMap<String, Object>transactionStatusMap=null;
		JSONObject reJsn=new JSONObject();
		JSONObject obj=new JSONObject();

		try {


			obj.put("LoginId",FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);

			reJsn.put("Authentication", obj);
			reJsn.put("UserTrackId", UserTrackId);


			JSONObject resp=callFlightApi("GetTransactionStatus", reJsn);

			System.out.println("GetTransactionStatusRequest"+reJsn);
			System.out.println("GetTransactionStatusResponse"+resp);

			int statusM=resp.getInt("ResponseStatus");


			if(statusM==1) 
			{

				HashMap<String, Object>mainMap=new HashMap<>();
				mainMap.put("UserTrackId", resp.get("UserTrackId"));
				JSONObject transactionStatusOutputjson=resp.getJSONObject("TransactionStatusOutput");
				JSONObject transactionStatusjson=transactionStatusOutputjson.getJSONObject("TransactionStatus");
				if(transactionStatusjson!=null && transactionStatusjson.length()>0)
				{
					transactionStatusMap=new HashMap<>();
					transactionStatusMap.put("transactionStatus", transactionStatusjson.get("Status"));
					transactionStatusMap.put("Remarks", transactionStatusjson.get("Remarks"));
					/*transactionStatusMap.put("GSTDetails", transactionStatusjson.get("GSTDetails"));*/

					ArrayList<HashMap<String, Object>>FlightTicketDetailsList=new ArrayList<>();
					JSONArray flightTicketDetailsarray=transactionStatusjson.getJSONArray("FlightTicketDetails");
					if(flightTicketDetailsarray!=null && flightTicketDetailsarray.length()>0)
					{
						for (int i = 0; i < flightTicketDetailsarray.length(); i++) {

							JSONObject flightTicketDetailsJson=flightTicketDetailsarray.getJSONObject(i);
							HashMap<String, Object>flightTicketDetailsMap=new HashMap<>();
							flightTicketDetailsMap.put("HermesPNR", flightTicketDetailsJson.get("HermesPNR"));
							flightTicketDetailsMap.put("TransactionId", flightTicketDetailsJson.get("TransactionId"));
							flightTicketDetailsMap.put("TotalSegments", flightTicketDetailsJson.get("TotalSegments"));
							flightTicketDetailsMap.put("TotalAmount", flightTicketDetailsJson.get("TotalAmount"));
							flightTicketDetailsMap.put("OtherCharges", flightTicketDetailsJson.get("OtherCharges"));
							flightTicketDetailsMap.put("AdultCount", flightTicketDetailsJson.get("AdultCount"));
							flightTicketDetailsMap.put("ChildCount", flightTicketDetailsJson.get("ChildCount"));
							flightTicketDetailsMap.put("BookingType", flightTicketDetailsJson.get("BookingType"));
							flightTicketDetailsMap.put("TravelType", flightTicketDetailsJson.get("TravelType"));
							flightTicketDetailsMap.put("BaseDestination", flightTicketDetailsJson.get("BaseDestination"));
							flightTicketDetailsMap.put("TourCode", flightTicketDetailsJson.get("TourCode"));
							flightTicketDetailsMap.put("BookingRemarks", flightTicketDetailsJson.get("BookingRemarks"));
							flightTicketDetailsMap.put("IssueDateTime", flightTicketDetailsJson.get("IssueDateTime"));
							flightTicketDetailsMap.put("BaseOrigin", flightTicketDetailsJson.get("TravelType"));
							JSONObject customerDetailsJson=flightTicketDetailsJson.getJSONObject("CustomerDetails");
							HashMap<String, Object>customerDetailsMap=null;
							if(customerDetailsJson!=null && customerDetailsJson.length()>0)
							{

								customerDetailsMap=new HashMap<>();
								customerDetailsMap.put("Title", customerDetailsJson.get("Title"));
								customerDetailsMap.put("Name", customerDetailsJson.get("Name"));
								customerDetailsMap.put("Address", customerDetailsJson.get("Address"));
								customerDetailsMap.put("City", customerDetailsJson.get("City"));
								customerDetailsMap.put("CountryId", customerDetailsJson.get("CountryId"));
								customerDetailsMap.put("ContactNumber", customerDetailsJson.get("ContactNumber"));
								customerDetailsMap.put("EmailId", customerDetailsJson.get("EmailId"));
								customerDetailsMap.put("PinCode", customerDetailsJson.get("PinCode"));

							}
							flightTicketDetailsMap.put("CustomerDetails", customerDetailsMap);
							JSONArray airlineDetailsArry=flightTicketDetailsJson.getJSONArray("AirlineDetails");
							ArrayList<HashMap<String, Object>>airlineDetailsList=new ArrayList<>();
							if(airlineDetailsArry!=null && airlineDetailsArry.length()>0)
							{
								for (int k = 0; k < airlineDetailsArry.length(); k++) {

									JSONObject airlineDetailsJson=airlineDetailsArry.getJSONObject(k);
									HashMap<String, Object>airlineDetailsMap=new HashMap<>();
									airlineDetailsMap.put("AirlineCode", airlineDetailsJson.get("AirlineCode"));
									airlineDetailsMap.put("AirlinePNR", airlineDetailsJson.get("AirlinePNR"));
									airlineDetailsMap.put("AirlineName", airlineDetailsJson.get("AirlineName"));
									airlineDetailsMap.put("Address1", airlineDetailsJson.get("Address1"));
									airlineDetailsMap.put("Address2", airlineDetailsJson.get("Address2"));
									airlineDetailsMap.put("City", airlineDetailsJson.get("City"));
									airlineDetailsMap.put("ContactNumber", airlineDetailsJson.get("ContactNumber"));
									airlineDetailsMap.put("FaxNumber", airlineDetailsJson.get("FaxNumber"));
									airlineDetailsMap.put("EMailId", airlineDetailsJson.get("EMailId"));
									airlineDetailsList.add(airlineDetailsMap);


								}
							}
							flightTicketDetailsMap.put("AirlineDetails", airlineDetailsList);
							HashMap<String, Object>IATADetailsMap=null;
							JSONObject IATADetailsjson=flightTicketDetailsJson.getJSONObject("IATADetails");
							if(IATADetailsjson!=null && IATADetailsjson.length()>0)
							{
								IATADetailsMap=new HashMap<>();
								IATADetailsMap.put("IATAAgentName1", IATADetailsjson.get("IATAAgentName1"));
								IATADetailsMap.put("IATAAgentName2", IATADetailsjson.get("IATAAgentName2"));
								IATADetailsMap.put("IATAAgentNumber", IATADetailsjson.get("IATAAgentNumber"));
								IATADetailsMap.put("CRSPNR", IATADetailsjson.get("CRSPNR"));
								IATADetailsMap.put("TicketNumber", IATADetailsjson.get("TicketNumber"));


							}
							JSONObject PaymentDetailsJson=flightTicketDetailsJson.getJSONObject("PaymentDetails");
							HashMap<String, Object>PaymentDetailsMap=null;
							if(PaymentDetailsJson!=null && PaymentDetailsJson.length()>0)
							{
								PaymentDetailsMap=new HashMap<>();
								PaymentDetailsMap.put("Amount", PaymentDetailsJson.get("Amount"));
								PaymentDetailsMap.put("CurrencyCode", PaymentDetailsJson.get("CurrencyCode"));
								JSONObject AirlinePaymentDetailsJson=PaymentDetailsJson.getJSONObject("AirlinePaymentDetails");
								HashMap<String, Object>AirlinePaymentDetailsMap=null;
								if (AirlinePaymentDetailsJson!=null && AirlinePaymentDetailsJson.length()>0) {
									AirlinePaymentDetailsMap=new HashMap<>();
									AirlinePaymentDetailsMap.put("PaymentType", AirlinePaymentDetailsJson.get("PaymentType"));
									AirlinePaymentDetailsMap.put("TicketingPCC", AirlinePaymentDetailsJson.get("TicketingPCC"));
									AirlinePaymentDetailsMap.put("VendorCode", AirlinePaymentDetailsJson.get("VendorCode"));
									JSONObject CreditCardDetailsjson=AirlinePaymentDetailsJson.getJSONObject("CreditCardDetails");
									HashMap<String, Object> CreditCardDetailsMap=null;
									if(CreditCardDetailsjson!=null && CreditCardDetailsjson.length()>0)
									{
										CreditCardDetailsMap=new HashMap<>();
										CreditCardDetailsMap.put("CardNumber", CreditCardDetailsjson.get("CardNumber"));
										CreditCardDetailsMap.put("CardType", CreditCardDetailsjson.get("CardType"));


									}
									AirlinePaymentDetailsMap.put("CreditCardDetails", CreditCardDetailsMap);

								}
								PaymentDetailsMap.put("AirlinePaymentDetails", AirlinePaymentDetailsMap);

							}

							JSONObject TerminalContactDetailsJson=flightTicketDetailsJson.getJSONObject("TerminalContactDetails");
							HashMap<String, Object>TerminalContactDetailsMap=null;
							if(TerminalContactDetailsJson!=null && TerminalContactDetailsJson.length()>0)
							{
								TerminalContactDetailsMap=new HashMap<>();
								TerminalContactDetailsMap.put("TerminalName", TerminalContactDetailsJson.get("TerminalName"));
								TerminalContactDetailsMap.put("Address1", TerminalContactDetailsJson.get("Address1"));
								TerminalContactDetailsMap.put("Address2", TerminalContactDetailsJson.get("Address2"));
								TerminalContactDetailsMap.put("City", TerminalContactDetailsJson.get("City"));
								TerminalContactDetailsMap.put("State", TerminalContactDetailsJson.get("State"));
								TerminalContactDetailsMap.put("City", TerminalContactDetailsJson.get("City"));
								TerminalContactDetailsMap.put("ContactNumber", TerminalContactDetailsJson.get("ContactNumber"));
								TerminalContactDetailsMap.put("Country", TerminalContactDetailsJson.get("Country"));
								TerminalContactDetailsMap.put("EmailId", TerminalContactDetailsJson.get("EmailId"));


							}

							JSONArray PassengerDetailsArray=flightTicketDetailsJson.getJSONArray("PassengerDetails");
							ArrayList<HashMap<String, Object>>PassengerDetailsList=new ArrayList<>();
							if(PassengerDetailsArray!=null && PassengerDetailsArray.length()>0)
							{
								for (int o = 0; o < PassengerDetailsArray.length(); o++) {

									JSONObject PassengerDetailsMapJson=PassengerDetailsArray.getJSONObject(o);
									HashMap<String, Object>PassengerDetailsMap=new HashMap<>();
									PassengerDetailsMap.put("TicketNumber", PassengerDetailsMapJson.get("TicketNumber"));
									PassengerDetailsMap.put("TransmissionControlNo", PassengerDetailsMapJson.get("TransmissionControlNo"));
									PassengerDetailsMap.put("PassengerType", PassengerDetailsMapJson.get("PassengerType"));
									PassengerDetailsMap.put("Title", PassengerDetailsMapJson.get("Title"));
									PassengerDetailsMap.put("FirstName", PassengerDetailsMapJson.get("FirstName"));
									PassengerDetailsMap.put("LastName", PassengerDetailsMapJson.get("LastName"));
									PassengerDetailsMap.put("Age", PassengerDetailsMapJson.get("Age"));
									PassengerDetailsMap.put("IdentityProofId", PassengerDetailsMapJson.get("IdentityProofId"));
									PassengerDetailsMap.put("IdentityProofNumber", PassengerDetailsMapJson.get("IdentityProofNumber"));
									PassengerDetailsMap.put("PersonOrgId", PassengerDetailsMapJson.get("PersonOrgId"));
									JSONObject LadderDetails=PassengerDetailsMapJson.getJSONObject("LadderDetails");
									HashMap<String, Object> LadderDetailsMap=null;
									if(LadderDetails!=null && LadderDetails.length()>0)
									{
										LadderDetailsMap=new HashMap<>();
										LadderDetailsMap.put("EndorsementRestriction", LadderDetails.get("EndorsementRestriction"));
										LadderDetailsMap.put("IssueInExchangeFor", LadderDetails.get("IssueInExchangeFor"));
										LadderDetailsMap.put("FareCalculation", LadderDetails.get("FareCalculation"));
									}
									PassengerDetailsMap.put("LadderDetails", LadderDetailsMap);	
									ArrayList<HashMap<String, Object>>BookedSegmentslist=new ArrayList<>();
									JSONArray BookedSegmentsArry=PassengerDetailsMapJson.getJSONArray("BookedSegments");
									if(BookedSegmentsArry!=null && BookedSegmentsArry.length()>0)
									{
										for (int j = 0; j < BookedSegmentsArry.length(); j++) {

											JSONObject BookedSegmentsjson=BookedSegmentsArry.getJSONObject(j);
											HashMap<String, Object>BookedSegmentsMap=new HashMap<>();
											BookedSegmentsMap.put("TicketNumber", BookedSegmentsjson.get("TicketNumber"));
											BookedSegmentsMap.put("FlightNumber", BookedSegmentsjson.get("FlightNumber"));
											BookedSegmentsMap.put("AirCraftType", BookedSegmentsjson.get("AirCraftType"));
											BookedSegmentsMap.put("Origin", BookedSegmentsjson.get("Origin"));
											BookedSegmentsMap.put("OriginAirport", BookedSegmentsjson.get("OriginAirport"));
											BookedSegmentsMap.put("DepartureDateTime", BookedSegmentsjson.get("DepartureDateTime"));
											BookedSegmentsMap.put("Destination", BookedSegmentsjson.get("Destination"));
											BookedSegmentsMap.put("DestinationAirport", BookedSegmentsjson.get("DestinationAirport"));
											BookedSegmentsMap.put("Arrivaldatetime", BookedSegmentsjson.get("Arrivaldatetime"));
											BookedSegmentsMap.put("AirlineCode", BookedSegmentsjson.get("AirlineCode"));
											BookedSegmentsMap.put("ClassCode", BookedSegmentsjson.get("ClassCode"));
											BookedSegmentsMap.put("ClassCodeDesc", BookedSegmentsjson.get("ClassCodeDesc"));
											BookedSegmentsMap.put("FareBasis", BookedSegmentsjson.get("FareBasis"));
											BookedSegmentsMap.put("BaggageAllowed", BookedSegmentsjson.get("BaggageAllowed"));
											BookedSegmentsMap.put("StopOverAllowed", BookedSegmentsjson.get("StopOverAllowed"));
											BookedSegmentsMap.put("FrequentFlyerId", BookedSegmentsjson.get("FrequentFlyerId"));
											BookedSegmentsMap.put("FrequentFlyerNumber", BookedSegmentsjson.get("FrequentFlyerNumber"));
											BookedSegmentsMap.put("SpecialServiceCode", BookedSegmentsjson.get("SpecialServiceCode"));
											BookedSegmentsMap.put("MealCode", BookedSegmentsjson.get("MealCode"));
											BookedSegmentsMap.put("SeatPreferId", BookedSegmentsjson.get("SeatPreferId"));
											BookedSegmentsMap.put("BasicCurrencyCode", BookedSegmentsjson.get("BasicCurrencyCode"));
											BookedSegmentsMap.put("CurrencyCode", BookedSegmentsjson.get("CurrencyCode"));
											BookedSegmentsMap.put("BasicAmount", BookedSegmentsjson.get("BasicAmount"));
											BookedSegmentsMap.put("EquivalentFare", BookedSegmentsjson.get("EquivalentFare"));
											BookedSegmentsMap.put("TotalTaxAmount", BookedSegmentsjson.get("TotalTaxAmount"));
											BookedSegmentsMap.put("TransactionFee", BookedSegmentsjson.get("TransactionFee"));
											BookedSegmentsMap.put("ServiceCharge", BookedSegmentsjson.get("ServiceCharge"));
											BookedSegmentsMap.put("GrossAmount", BookedSegmentsjson.get("GrossAmount"));
											BookedSegmentsMap.put("AdditionalSSRDetails", BookedSegmentsjson.get("AdditionalSSRDetails"));
											JSONArray TaxDetailsArry=BookedSegmentsjson.getJSONArray("TaxDetails");
											ArrayList<HashMap<String, Object>>TaxDetailsList=new ArrayList<>();
											if (TaxDetailsArry!=null && TaxDetailsArry.length()>0) {
												for (int k = 0; k < TaxDetailsArry.length(); k++) {

													JSONObject TaxDetailsJson=TaxDetailsArry.getJSONObject(k);
													HashMap<String, Object>TaxDetailsMap=new HashMap<>();
													TaxDetailsMap.put("Description", TaxDetailsJson.get("Description"));
													TaxDetailsMap.put("Amount", TaxDetailsJson.get("Amount"));
													TaxDetailsList.add(TaxDetailsMap);
												}


											}

											BookedSegmentsMap.put("TaxDetails",TaxDetailsList);
											BookedSegmentslist.add(BookedSegmentsMap);

										}
									}
									PassengerDetailsMap.put("BookedSegments", BookedSegmentslist);
									PassengerDetailsList.add(PassengerDetailsMap);


								}
							}
							flightTicketDetailsMap.put("TerminalContactDetails", TerminalContactDetailsMap);
							flightTicketDetailsMap.put("PassengerDetails", PassengerDetailsList);
							flightTicketDetailsMap.put("CustomerDetails", customerDetailsMap);
							flightTicketDetailsMap.put("AirlineDetails", airlineDetailsList);

							flightTicketDetailsMap.put("IATADetails", IATADetailsMap);
							flightTicketDetailsMap.put( "PaymentDetails", PaymentDetailsMap);
							FlightTicketDetailsList.add(flightTicketDetailsMap);
						}

					}
					transactionStatusMap.put("FlightTicketDetails", FlightTicketDetailsList);
					return transactionStatusMap;


				}




			}




		}catch (Exception e) {
			// TODO: handle exception
		}
		return transactionStatusMap;



	}




	public	ArrayList<HashMap<String, Object>> GetReprintRequest(String hermesPNR)
	{
		JSONObject reJson = null;


		try {
			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);


			JSONObject ReprintInputJsn=new JSONObject();
			ReprintInputJsn.put("HermesPNR", hermesPNR);

			reJson=new JSONObject();




			reJson.put("Authentication",obj);
			reJson.put("ReprintInput",ReprintInputJsn);



			System.out.println("request getPrint::"+reJson);

			JSONObject resp=FlightApi.callFlightApi("GetReprint", reJson);

			System.out.println("resp GetPrint::"+resp);

			int stutas=resp.getInt("ResponseStatus");

			if (stutas==1) {

				HashMap<String, Object> mainMap=new HashMap<>();
				mainMap.put("UserTrackId", resp.get("UserTrackId"));


				JSONObject ReprintOutputJSN=resp.getJSONObject("ReprintOutput");

				JSONObject TicketDetailsJSN=ReprintOutputJSN.getJSONObject("TicketDetails");
				if (TicketDetailsJSN!=null && TicketDetailsJSN.length()>0) {



					HashMap<String, Object> TicketDetailsMap=new HashMap<>();

					TicketDetailsMap.put("HermesPNR", TicketDetailsJSN.getString("HermesPNR"));
					TicketDetailsMap.put("TransactionId", TicketDetailsJSN.getString("TransactionId"));
					TicketDetailsMap.put("TotalSegments", TicketDetailsJSN.getInt("TotalSegments"));
					TicketDetailsMap.put("TotalAmount", TicketDetailsJSN.getInt("TotalAmount"));
					TicketDetailsMap.put("OtherCharges", TicketDetailsJSN.getInt("OtherCharges"));
					TicketDetailsMap.put("AdultCount", TicketDetailsJSN.getInt("AdultCount"));
					TicketDetailsMap.put("ChildCount", TicketDetailsJSN.getInt("ChildCount"));
					TicketDetailsMap.put("InfantCount", TicketDetailsJSN.getInt("InfantCount"));
					TicketDetailsMap.put("BookingType", TicketDetailsJSN.get("BookingType"));
					TicketDetailsMap.put("IssueDateTime", TicketDetailsJSN.get("IssueDateTime"));
					TicketDetailsMap.put("BaseOrigin", TicketDetailsJSN.get("BaseOrigin"));
					TicketDetailsMap.put("TotalAmount", TicketDetailsJSN.get("TotalAmount"));
					TicketDetailsMap.put("BaseDestination", TicketDetailsJSN.get("OtherCharges"));
					TicketDetailsMap.put("TourCode", TicketDetailsJSN.get("TourCode"));


					HashMap<String, Object> CustomerDetailsMap=null;
					JSONObject CustomerDetailsjsn=TicketDetailsJSN.optJSONObject("CustomerDetails");
					if(CustomerDetailsjsn!=null && CustomerDetailsjsn.length()>0){

						CustomerDetailsMap=new HashMap<>();

						CustomerDetailsMap.put("Title",CustomerDetailsjsn.get("Title"));
						CustomerDetailsMap.put("Name",CustomerDetailsjsn.get("Name"));
						CustomerDetailsMap.put("Address",CustomerDetailsjsn.get("Address"));
						CustomerDetailsMap.put("City",CustomerDetailsjsn.get("City"));
						CustomerDetailsMap.put("CountryId",CustomerDetailsjsn.get("CountryId"));
						CustomerDetailsMap.put("ContactNumber",CustomerDetailsjsn.get("ContactNumber"));
						CustomerDetailsMap.put("EmailId",CustomerDetailsjsn.get("EmailId"));
						CustomerDetailsMap.put("PinCode",CustomerDetailsjsn.get("PinCode"));
					}

					JSONArray AirlineDetailsAry=TicketDetailsJSN.getJSONArray("AirlineDetails");

					if(AirlineDetailsAry.length()>0)
					{
						for (int j = 0; j < AirlineDetailsAry.length(); j++) {


							JSONObject AirlineDetailsjsn=AirlineDetailsAry.getJSONObject(j);
							HashMap<String, Object> AirlineDetailsMap=new HashMap<>();

							AirlineDetailsMap.put("AirlineCode",AirlineDetailsjsn.getString("AirlineCode"));
							AirlineDetailsMap.put("AirlinePNR",AirlineDetailsjsn.getString("AirlinePNR"));
							AirlineDetailsMap.put("AirlineName",AirlineDetailsjsn.getString("AirlineName"));
							AirlineDetailsMap.put("Address1",AirlineDetailsjsn.getString("Address1"));
							AirlineDetailsMap.put("Address2",AirlineDetailsjsn.getString("Address2"));
							AirlineDetailsMap.put("City",AirlineDetailsjsn.getString("City"));
							AirlineDetailsMap.put("ContactNumber",AirlineDetailsjsn.getString("ContactNumber"));
							AirlineDetailsMap.put("FaxNumber",AirlineDetailsjsn.getString("FaxNumber"));
							AirlineDetailsMap.put("EMailId",AirlineDetailsjsn.getString("EMailId"));







						}
					}
					JSONObject IATADetailsJsn=TicketDetailsJSN.getJSONObject("IATADetails");

					HashMap<String, Object> IATADetailsMap=new HashMap<>();


					IATADetailsMap.put("CRSPNR", IATADetailsJsn.getString("CRSPNR"));
					IATADetailsMap.put("IATAAgentNumber", IATADetailsJsn.getString("IATAAgentNumber"));
					IATADetailsMap.put("IATAAgentName1", IATADetailsJsn.getString("IATAAgentName1"));
					IATADetailsMap.put("IATAAgentName2", IATADetailsJsn.getString("IATAAgentName2"));
					IATADetailsMap.put("TicketNumber", IATADetailsJsn.getString("TicketNumber"));



					JSONObject PaymentDetailsJSN=TicketDetailsJSN.getJSONObject("PaymentDetails");
					HashMap<String, Object> PaymentDetailsMap=new HashMap<>();


					PaymentDetailsMap.put("CurrencyCode",PaymentDetailsJSN.getString("CurrencyCode") );
					PaymentDetailsMap.put("Amount",PaymentDetailsJSN.getInt("Amount"));



					JSONObject TerminalContactDetailsJsn=TicketDetailsJSN.getJSONObject("TerminalContactDetails");
					HashMap<String, Object> TerminalContactDetailsMap=new HashMap<>();


					TerminalContactDetailsMap.put("TerminalName", TerminalContactDetailsJsn.getString("TerminalName"));
					TerminalContactDetailsMap.put("Address1", TerminalContactDetailsJsn.getString("Address1"));
					TerminalContactDetailsMap.put("Address2", TerminalContactDetailsJsn.getString("Address2"));
					TerminalContactDetailsMap.put("City", TerminalContactDetailsJsn.getString("City"));
					TerminalContactDetailsMap.put("State", TerminalContactDetailsJsn.getString("State"));
					TerminalContactDetailsMap.put("Country", TerminalContactDetailsJsn.getString("Country"));
					TerminalContactDetailsMap.put("ContactNumber", TerminalContactDetailsJsn.getString("ContactNumber"));
					TerminalContactDetailsMap.put("EmailId", TerminalContactDetailsJsn.getString("EmailId"));




					ArrayList<HashMap<String, Object>> PassengerDetailsList=new ArrayList<>();

					JSONArray PassengerDetailsArray=TicketDetailsJSN.getJSONArray("PassengerDetails");

					if (PassengerDetailsArray.length()>0)
					{
						for (int k = 0; k < PassengerDetailsArray.length(); k++) {

							JSONObject PassengerDetailsJsn=PassengerDetailsArray.getJSONObject(k);

							HashMap<String, Object> PassengerDetailsMap=new HashMap<>();

							PassengerDetailsMap.put("TicketNumber",PassengerDetailsJsn.getString("TicketNumber") );
							PassengerDetailsMap.put("TransmissionControlNo",PassengerDetailsJsn.getString("TransmissionControlNo") );
							PassengerDetailsMap.put("PassengerType",PassengerDetailsJsn.getString("PassengerType") );
							PassengerDetailsMap.put("Title",PassengerDetailsJsn.getString("Title") );
							PassengerDetailsMap.put("FirstName",PassengerDetailsJsn.getString("FirstName") );
							PassengerDetailsMap.put("LastName",PassengerDetailsJsn.getString("LastName") );
							PassengerDetailsMap.put("Age",PassengerDetailsJsn.getString("Age") );
							PassengerDetailsMap.put("IdentityProofId",PassengerDetailsJsn.getString("IdentityProofId") );
							PassengerDetailsMap.put("IdentityProofNumber",PassengerDetailsJsn.getString("IdentityProofNumber") );
							PassengerDetailsMap.put("PersonOrgId",PassengerDetailsJsn.getString("PersonOrgId") );

							JSONObject LadderDetailsJsn=PassengerDetailsJsn.getJSONObject("LadderDetails");

							HashMap<String,Object> LadderDetailsMap=new HashMap<>();

							LadderDetailsMap.put("EndorsementRestriction", LadderDetailsJsn.get("EndorsementRestriction"));
							LadderDetailsMap.put("IssueInExchangeFor", LadderDetailsJsn.get("IssueInExchangeFor"));
							LadderDetailsMap.put("EndorsementRestriction", LadderDetailsJsn.get("FareCalculation"));

							PassengerDetailsMap.put("LadderDetails", LadderDetailsMap);


							ArrayList<HashMap<String, Object>> BookedSegmentsList=new ArrayList<>();

							JSONArray BookedSegmentsAry=PassengerDetailsJsn.getJSONArray("BookedSegments");
							if (BookedSegmentsAry.length()>0) {


								for (int l = 0; l < BookedSegmentsAry.length(); l++) {


									JSONObject BookedSegmentsJsn=BookedSegmentsAry.getJSONObject(l);

									HashMap<String, Object> BookedSegmentsMap=new HashMap<>();


									BookedSegmentsMap.put("TicketNumber", BookedSegmentsJsn.getString("TicketNumber"));
									BookedSegmentsMap.put("FlightNumber", BookedSegmentsJsn.getString("FlightNumber"));
									BookedSegmentsMap.put("AirCraftType", BookedSegmentsJsn.getString("AirCraftType"));
									BookedSegmentsMap.put("Origin", BookedSegmentsJsn.getString("Origin"));
									BookedSegmentsMap.put("OriginAirport", BookedSegmentsJsn.getString("OriginAirport"));
									BookedSegmentsMap.put("DepartureDateTime", BookedSegmentsJsn.getString("DepartureDateTime"));
									BookedSegmentsMap.put("Destination", BookedSegmentsJsn.getString("Destination"));
									BookedSegmentsMap.put("DestinationAirport", BookedSegmentsJsn.getString("DestinationAirport"));
									BookedSegmentsMap.put("Arrivaldatetime", BookedSegmentsJsn.getString("Arrivaldatetime"));
									BookedSegmentsMap.put("AirlineCode", BookedSegmentsJsn.getString("AirlineCode"));

									BookedSegmentsMap.put("ClassCode", BookedSegmentsJsn.getString("ClassCode"));
									BookedSegmentsMap.put("ClassCodeDesc", BookedSegmentsJsn.getString("ClassCodeDesc"));
									BookedSegmentsMap.put("FareBasis", BookedSegmentsJsn.getString("FareBasis"));
									BookedSegmentsMap.put("BaggageAllowed", BookedSegmentsJsn.getString("BaggageAllowed"));
									BookedSegmentsMap.put("StopOverAllowed", BookedSegmentsJsn.getString("StopOverAllowed"));
									BookedSegmentsMap.put("FrequentFlyerId", BookedSegmentsJsn.getString("FrequentFlyerId"));
									BookedSegmentsMap.put("FrequentFlyerNumber", BookedSegmentsJsn.getString("FrequentFlyerNumber"));
									BookedSegmentsMap.put("SpecialServiceCode", BookedSegmentsJsn.getString("SpecialServiceCode"));
									BookedSegmentsMap.put("MealCode", BookedSegmentsJsn.getString("MealCode"));
									BookedSegmentsMap.put("SeatPreferId", BookedSegmentsJsn.getString("SeatPreferId"));

									BookedSegmentsMap.put("BasicCurrencyCode", BookedSegmentsJsn.getString("BasicCurrencyCode"));
									BookedSegmentsMap.put("CurrencyCode", BookedSegmentsJsn.getString("CurrencyCode"));
									BookedSegmentsMap.put("BasicAmount", BookedSegmentsJsn.getInt("BasicAmount"));
									BookedSegmentsMap.put("EquivalentFare", BookedSegmentsJsn.getInt("EquivalentFare"));

									BookedSegmentsMap.put("TotalTaxAmount", BookedSegmentsJsn.getString("TotalTaxAmount"));
									BookedSegmentsMap.put("TransactionFee", BookedSegmentsJsn.getString("TransactionFee"));
									BookedSegmentsMap.put("ServiceCharge", BookedSegmentsJsn.getInt("ServiceCharge"));
									BookedSegmentsMap.put("GrossAmount", BookedSegmentsJsn.getInt("GrossAmount"));
									BookedSegmentsMap.put("AdditionalSSRDetails", BookedSegmentsJsn.getInt("AdditionalSSRDetails"));






									ArrayList<HashMap<String, Object>> TesxDetailsList=new ArrayList<>();
									JSONArray TaxDetailsAry=BookedSegmentsJsn.getJSONArray("TaxDetails");
									if (TaxDetailsAry.length()>0) {

										for (int m = 0; m < TaxDetailsAry.length(); m++) {

											JSONObject TaxDetailsJsn=TaxDetailsAry.getJSONObject(m);
											HashMap<String, Object> TaxDetailsMap=new HashMap<>();

											TaxDetailsMap.put("Description", TaxDetailsJsn.getString("Description"));
											TaxDetailsMap.put("EquivalentFare", TaxDetailsJsn.getInt("EquivalentFare"));


											TesxDetailsList.add(TaxDetailsMap);

											System.out.println("TaxDetailsAry"+TesxDetailsList.toString());

										}



									}


									BookedSegmentsMap.put("TaxDetails", TesxDetailsList);



									BookedSegmentsList.add(BookedSegmentsMap);


								}
							}

							PassengerDetailsMap.put("LadderDetails", LadderDetailsMap);
							PassengerDetailsMap.put("BookedSegments", BookedSegmentsList);
							PassengerDetailsList.add(PassengerDetailsMap);



						}


					}

					TicketDetailsMap.put("PassengerDetails", PassengerDetailsList);
					TicketDetailsMap.put("CustomerDetails", CustomerDetailsMap);
					TicketDetailsMap.put("PassengerDetails", PassengerDetailsList);
					TicketDetailsMap.put("PassengerDetails", PassengerDetailsList);
					TicketDetailsMap.put("PassengerDetails", PassengerDetailsList);

				}


			}








		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;



	}

	public	ArrayList<HashMap<String, Object>> GetFareRuleRequest()
	{
		JSONObject reJson = null;


		try {
			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);


			JSONObject FareRuleInputJsn=new JSONObject();
			FareRuleInputJsn.put("AirlineCode", "SG");
			FareRuleInputJsn.put("FlightId", "11");
			FareRuleInputJsn.put("ClassCode", "CCFLEX");
			FareRuleInputJsn.put("SupplierId", "0");

			reJson=new JSONObject();




			reJson.put("Authentication",obj);
			reJson.put("FareRuleInput",FareRuleInputJsn);
			reJson.put("UserTrackId","RMYOI97999869989897984975053644895233236");


			System.out.println("reJson ::"+reJson);

			JSONObject resp=FlightApi.callFlightApi("GetFareRule", reJson);

			System.out.println("Response::"+resp);

			int status=resp.getInt("ResponseStatus");

			if (status==1) {


				HashMap<String, Object> mainMap=new HashMap<>();

				mainMap.put("UserTrackId",resp.get("UserTrackId"));


				JSONObject FareRuleOutputJsn=resp.getJSONObject("FareRuleOutput");

				HashMap<String, Object> FareRuleOutputMap=new HashMap<>();

				FareRuleOutputMap.put("FareRules", FareRuleOutputJsn.get("FareRules"));


				mainMap.put("FareRuleOutput", FareRuleOutputMap);

			}


		}

		catch(Exception e)
		{
			e.printStackTrace();

		}
		return null;


	}

	public	HashMap<String, Object> GetCancellationRequest(String hermesPNR, String airlinePNR, String cancelType)
	{
		JSONObject reJson = null;
		HashMap<String, Object> PartialCancelPNRDetailsMap=null;


		try {
			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);


			JSONObject CancellationInputJsn=new JSONObject();
			CancellationInputJsn.put("HermesPNR", hermesPNR);
			CancellationInputJsn.put("AirlinePNR",airlinePNR);
			CancellationInputJsn.put("CancelType", cancelType);


			reJson=new JSONObject();




			reJson.put("Authentication",obj);
			reJson.put("CancellationInput",CancellationInputJsn);


			System.out.println("reJson ::"+reJson);

			JSONObject resp=FlightApi.callFlightApi("GetCancellation", reJson);

			System.out.println("GetCancellation response::"+resp);


			int status=resp.getInt("ResponseStatus");
			if (status==1) {

				JSONObject  CancellationOutputJsn=resp.getJSONObject("CancellationOutput");

				JSONObject CancellationDetailsJsn=CancellationOutputJsn.optJSONObject("CancellationDetails");

				JSONObject PartialCancelPNRDetailsJsn=CancellationDetailsJsn.optJSONObject("PartialCancelPNRDetails");



				PartialCancelPNRDetailsMap=new HashMap<>();
				PartialCancelPNRDetailsMap.put("AirlineCode", PartialCancelPNRDetailsJsn.get("AirlineCode"));
				PartialCancelPNRDetailsMap.put("HermesPNR", PartialCancelPNRDetailsJsn.get("HermesPNR"));
				PartialCancelPNRDetailsMap.put("AilinePNR", PartialCancelPNRDetailsJsn.get("AilinePNR"));
				PartialCancelPNRDetailsMap.put("CRSPNR", PartialCancelPNRDetailsJsn.get("CRSPNR"));

				ArrayList<HashMap<String, Object>> CancelPassengerDetailsList=new ArrayList<>();

				JSONArray CancelPassengerDetailsArry=PartialCancelPNRDetailsJsn.getJSONArray("CancelPassengerDetails");
				if (CancelPassengerDetailsArry.length()>0) {

					for (int i = 0; i < CancelPassengerDetailsArry.length(); i++) {


						JSONObject CancelPassengerDetailsJsn=CancelPassengerDetailsArry.getJSONObject(i);

						HashMap<String, Object>  CancelPassengerDetailsmap=new HashMap<>();

						CancelPassengerDetailsmap.put("PaxNumber", CancelPassengerDetailsJsn.getString("PaxNumber"));
						CancelPassengerDetailsmap.put("FirstName", CancelPassengerDetailsJsn.getString("FirstName"));
						CancelPassengerDetailsmap.put("LastName", CancelPassengerDetailsJsn.getString("LastName"));
						CancelPassengerDetailsmap.put("PassengerType", CancelPassengerDetailsJsn.getString("PassengerType"));
						CancelPassengerDetailsmap.put("PaxNumber", CancelPassengerDetailsJsn.getString("PaxNumber"));

						ArrayList<HashMap<String, Object>> CancelTicketDetailsList=new ArrayList<>();

						JSONArray CancelTicketDetailsArry=CancelPassengerDetailsJsn.getJSONArray("CancelTicketDetails");
						if (CancelTicketDetailsArry.length()>0) {
							for (int j = 0; j< CancelTicketDetailsArry.length(); j++) {


								JSONObject CancelTicketDetailsJSn=CancelTicketDetailsArry.getJSONObject(j);
								HashMap<String, Object> CancelTicketDetailsMap=new HashMap<>();

								CancelTicketDetailsMap.put("TicketNumber", CancelTicketDetailsJSn.getString("TicketNumber"));
								CancelTicketDetailsMap.put("SegmentId", CancelTicketDetailsJSn.getInt("SegmentId"));
								CancelTicketDetailsMap.put("FlightNumber", CancelTicketDetailsJSn.get("FlightNumber"));
								CancelTicketDetailsMap.put("DepartureDateTime", CancelTicketDetailsJSn.get("DepartureDateTime"));
								CancelTicketDetailsMap.put("Destination", CancelTicketDetailsJSn.get("Destination"));
								CancelTicketDetailsMap.put("ArrivalDateTime", CancelTicketDetailsJSn.get("ArrivalDateTime"));
								CancelTicketDetailsMap.put("ClassCodeDesc", CancelTicketDetailsJSn.get("ClassCodeDesc"));
								CancelTicketDetailsMap.put("BasicAmount", CancelTicketDetailsJSn.get("BasicAmount"));
								CancelTicketDetailsMap.put("TotalTaxAmount", CancelTicketDetailsJSn.get("TotalTaxAmount"));
								CancelTicketDetailsMap.put("GrossAmount", CancelTicketDetailsJSn.get("GrossAmount"));
								CancelTicketDetailsMap.put("TicketStatus", CancelTicketDetailsJSn.get("TicketStatus"));


								CancelTicketDetailsList.add(CancelTicketDetailsMap);
							}
						}


						CancelPassengerDetailsmap.put("CancelTicketDetails", CancelTicketDetailsList);


						CancelPassengerDetailsList.add(CancelPassengerDetailsmap);

					}





				}
				PartialCancelPNRDetailsMap.put("CancelPassengerDetails", CancelPassengerDetailsList);



			}
			else {
				PartialCancelPNRDetailsMap=new HashMap<>();
				PartialCancelPNRDetailsMap.put("remarks", resp.get("FailureRemarks"));
				PartialCancelPNRDetailsMap.put("status", "1");

			}


		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		return PartialCancelPNRDetailsMap;
	}





	public	ArrayList<HashMap<String, Object>> GetCancellationRequest(String HermesPNR)
	{
		JSONObject reJson = null;
		ArrayList<HashMap<String, Object>> mainList = new ArrayList<>();


		try {
			JSONObject obj=new JSONObject();
			obj.put("LoginId", FlightApi.LoginId);
			obj.put("Password", FlightApi.Password);


			JSONObject CancellationInputJsn=new JSONObject();
			CancellationInputJsn.put("HermesPNR", "B6W7B2");
			CancellationInputJsn.put("AirlinePNR", "DD5INE");
			CancellationInputJsn.put("CancelType", "CancelType");


			reJson=new JSONObject();




			reJson.put("Authentication",obj);
			reJson.put("CancellationInput",CancellationInputJsn);
			reJson.put("UserTrackId","RMYOI97999869989897984975053644895233236");


			System.out.println("reJson ::"+reJson);

			JSONObject resp=FlightApi.callFlightApi("GetCancellation", reJson);

			System.out.println("GetCancellation response::"+resp);


			int status=resp.getInt("ResponseStatus");
			if (status==1) {


				JSONObject CancellationOutputJsn=resp.getJSONObject("CancellationOutput");
				JSONObject CancellationDetailsJsn=CancellationOutputJsn.getJSONObject("CancellationDetails");
				HashMap<String, Object> CancellationDetailsMap=new HashMap<>();
				CancellationDetailsMap.put("FullCancellationRemarks", CancellationDetailsJsn.get("FullCancellationRemarks"));

				mainList.add(CancellationDetailsMap);


			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mainList;
	}







	public static void main(String[] args) {
		try {

			FlightApi apiCall=new FlightApi();
			//apiCall.GetAvailabilityRequest("MAA", "BLR","05/26/2017", "1", "0", "0", "Economy");
			//apiCall.getTaxRequest("RMYLN97999919977947982898779364716332053", "11", "CCFLEX", "SG"," 1", "5539","0");
			//apiCall.GetTransactionStatusRequest("RMYOI97999869989897984975053644895233236");
			/*apiCall.GetReprintRequest(null);*/
			//apiCall.GetFareRuleRequest();
			/*apiCall.getBookRequest();*/

		}catch (Exception e) {
			e.printStackTrace();
		}
	}




	public static JSONObject callFlightApi(String callUrl,JSONObject reqJson)  {

		JSONObject jsonObj=null;
		try {

			String finalUrl=FlightApi.BASE_URL+callUrl;
			URL url = new URL(finalUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			String input = reqJson.toString();
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			/*BufferedReader br = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(BASE_URL)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			 */			String output;
			 System.out.println("Output from Server .... \n");
			 StringBuilder sb=new StringBuilder();
			 while ((output = br.readLine()) != null) {
				 sb.append(output);
			 }
			 conn.disconnect();
			 String outputString=sb.toString();
			 //JSONParser parser = new JSONParser();
			 //obj =  (JSONObject) parser.parse(outputString);
			 jsonObj = new JSONObject(outputString);

			 System.out.println("FlightApiCall.callFlightApi() : "+jsonObj);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return jsonObj;
	}


}