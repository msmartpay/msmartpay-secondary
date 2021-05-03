package com.msmart.busapi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class BusAPI{

	/*private final static String BASE_URL="http://115.248.39.80/hermesBusV1/Bus.svc/JSONService/";
	private final static String LOGIN_ID="hermes";
	private final static String PASSWORD="hermes123";*/
	
	
	private final static String BASE_URL="http://api.hermes-it.in/BUS_V1/Bus.svc/JSONService/";
	private final static String LOGIN_ID="Softsolution";
	private final static String PASSWORD="apiSoftsolution";

	public static JSONObject callBusApi(String callUrl,JSONObject reqJson)  {

		JSONObject jsonObject=null;
		try {

			String finalUrl=BusAPI.BASE_URL+callUrl;
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
			String output;
			System.out.println("Output from Server .... \n");
			StringBuilder sb=new StringBuilder();
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			conn.disconnect();

			String outputString=sb.toString();
			/*
			JSONParser parser = new JSONParser();
			obj = (JSONObject) parser.parse(outputString);*/
			jsonObject=new JSONObject(outputString);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonObject;
	}


	public static ArrayList<HashMap<String,String>> getSource() {

		ArrayList<HashMap<String, String>> OriginCitiesList=new ArrayList<HashMap<String,String>>();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			System.out.println("Request : "+reJson);

			JSONObject responseJson=callBusApi("GetOrigin", reJson);

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				JSONObject searchOutput=responseJson.getJSONObject("OriginOutput") ;

				JSONArray orgnAr= searchOutput.getJSONArray("OriginCities");
				if(orgnAr.length()>0)
				{					
					for(int i=0;i<orgnAr.length();i++)
					{
						JSONObject orgjson=orgnAr.getJSONObject(i);
						HashMap<String,String> OriginCityMap=new HashMap<String, String>();
						OriginCityMap.put("OriginId",orgjson.getString("OriginId"));
						OriginCityMap.put("OriginName", orgjson.getString("OriginName"));
						OriginCitiesList.add(OriginCityMap);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return OriginCitiesList;
	}



	public static ArrayList<HashMap<String,String>> getDestination(String OriginId ) {

		ArrayList<HashMap<String, String>> DestinationCitiesList=new ArrayList<HashMap<String,String>>();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);
			JSONObject obj2=new JSONObject();
			obj2.put("OriginId",OriginId);

			reJson.put("DestinationInput",obj2);

			System.out.println("Request : "+reJson);
			JSONObject responseJson=BusAPI.callBusApi("GetDestination", reJson);

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				JSONObject DestinationOutput=responseJson.getJSONObject("DestinationOutput") ;

				JSONArray destinationcityArry= DestinationOutput.getJSONArray("DestinationCities");
				if(destinationcityArry.length()>0)
				{					
					for(int i=0;i<destinationcityArry.length();i++)
					{
						JSONObject destinationcityjson=destinationcityArry.getJSONObject(i);
						HashMap<String,String> DestinationCitiesMap=new HashMap<String, String>();
						DestinationCitiesMap.put("DestinationId",destinationcityjson.getString("DestinationId"));
						DestinationCitiesMap.put("DestinationName", destinationcityjson.getString("DestinationName"));
						DestinationCitiesList.add(DestinationCitiesMap);


					}

				}
			}	
			System.out.println("Responce"+DestinationCitiesList.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return DestinationCitiesList;
	}

	//........................Bus Search Request..........................//

	public ArrayList<HashMap<String,Object>> busSearch(String originId, String destinationId, String TravelDate){
		ArrayList<HashMap<String, Object>> faresList=null;
		ArrayList<HashMap<String, Object>> mainList=null;
		ArrayList<HashMap<String, Object>>boardingPointsList=null;

		HashMap<String,Object> hashMap=null;
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);


			JSONObject obj2=new JSONObject();
			obj2.put("OriginId", originId);
			obj2.put("DestinationId",destinationId);
			obj2.put("TravelDate", TravelDate);

			reJson.put("SearchInput",obj2);

			System.out.println("Request : "+reJson);
			JSONObject responseJson=BusAPI.callBusApi("GetSearch", reJson);			



			int status=responseJson.getInt("ResponseStatus");
			if(status==1){
				mainList=new ArrayList<HashMap<String,Object>>();
				HashMap<String, Object> mainMap=new HashMap<String, Object>();
				mainMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject searchOutput=responseJson.getJSONObject("SearchOutput") ;

				ArrayList<HashMap<String, Object>>avbusList=new ArrayList<HashMap<String,Object>>();
				JSONArray avbusArray= searchOutput.getJSONArray("AvailableBuses");
				if(avbusArray.length()>0)
				{
					for(int i=0;i<avbusArray.length();i++){
						JSONObject avbusjsn=avbusArray.getJSONObject(i);
						hashMap=new HashMap<String, Object>();
						hashMap.put("ScheduleId",avbusjsn.getString("ScheduleId"));
						hashMap.put("StationId", avbusjsn.getString("StationId"));
						hashMap.put("BusType", avbusjsn.getString("BusType"));
						hashMap.put("BusName", avbusjsn.getString("BusName"));
						hashMap.put("TransportId", avbusjsn.getString("TransportId"));
						hashMap.put("TransportName", avbusjsn.getString("TransportName"));
						hashMap.put("DepartureTime", avbusjsn.getString("DepartureTime"));
						hashMap.put("ArrivalTime", avbusjsn.getString("ArrivalTime"));
						hashMap.put("AvailableSeatCount", avbusjsn.getString("AvailableSeatCount"));
						hashMap.put("Commission", avbusjsn.getString("Commission"));


						faresList=new ArrayList<HashMap<String,Object>>();
						JSONArray faresAry= avbusjsn.getJSONArray("Fares");	
						if(faresAry.length()>0){
							for (int j = 0; j < faresAry.length(); j++) {
								JSONObject farejson= faresAry.getJSONObject(j);	
								HashMap<String, Object> faresMap=new HashMap<String, Object>();
								faresMap.put("SeatTypeId", farejson.getString("SeatTypeId"));
								faresMap.put("SeatType", farejson.getString("SeatType"));
								faresMap.put("Fare", farejson.getString("Fare"));
								faresMap.put("ServiceTax", farejson.getString("ServiceTax"));
								faresMap.put("ConvenienceFee", farejson.getString("ConvenienceFee"));

								faresList.add(faresMap);
							}
						}


						boardingPointsList=new ArrayList<HashMap<String,Object>>();
						JSONArray boardingPointsAry= avbusjsn.getJSONArray("BoardingPoints");						
						if(boardingPointsAry.length()>0)
						{
							for (int k = 0; k < boardingPointsAry.length(); k++) 
							{

								JSONObject brd=boardingPointsAry.getJSONObject(k);
								HashMap<String, Object> boardingPointsMap=new HashMap<String, Object>();
								boardingPointsMap.put("BoardingId", brd.getString("BoardingId"));
								boardingPointsMap.put("Place", brd.getString("Place"));
								boardingPointsMap.put("Time", brd.getString("Time"));
								boardingPointsMap.put("Address", brd.getString("Address"));
								boardingPointsMap.put("LandMark", brd.getString("LandMark"));
								boardingPointsMap.put("ContactNumber", brd.getString("ContactNumber"));

								boardingPointsList.add(boardingPointsMap);

							}
						}


						ArrayList<HashMap<String,Object>> dropingPointsList=new ArrayList<HashMap<String,Object>>();
						JSONArray dropingPointsAry= avbusjsn.getJSONArray("DroppingPoints");
						if(dropingPointsAry.length()>0)
						{
							for (int k = 0; k < dropingPointsAry.length(); k++) 
							{
								JSONObject dropjson=dropingPointsAry.getJSONObject(k);
								HashMap<String, Object> dropingPointsMap=new HashMap<String, Object>();
								dropingPointsMap.put("DroppingId", dropjson.getString("DroppingId"));
								dropingPointsMap.put("Place", dropjson.getString("Place"));
								dropingPointsMap.put("Time", dropjson.getString("Time"));
								dropingPointsMap.put("Address", dropjson.getString("Address"));
								dropingPointsMap.put("LandMark", dropjson.getString("LandMark"));
								dropingPointsMap.put("ContactNumber", dropjson.getString("ContactNumber"));
								dropingPointsList.add(dropingPointsMap);

							}
						}

						hashMap.put("BoardingPoints", boardingPointsList);
						hashMap.put("Fares", faresList);
						hashMap.put("BoardingPoints", boardingPointsList);
						hashMap.put("DroppingPoints",dropingPointsList);


						avbusList.add(hashMap);
					}


				}




				mainMap.put("AvailableBuses", avbusList);
				mainList.add(mainMap);

			}
			System.out.println("Main-Responce"+mainList);



		}catch (Exception e) {
			e.printStackTrace();
		}

		return mainList;
	}



	//........................Get Seat Map Request ..........................//
	//
	public  HashMap<String, Object> GetSeatMapRequest(String ScheduleId, String StationId, String TransportId, String TravelDate, String UserTrackId) 
	{
		ArrayList<HashMap<String, Object>> bookedSeatsList=null;;	
		ArrayList<HashMap<String, Object>> seatList=null;	
		ArrayList<HashMap<String, Object>> upperSeatList=null;	
		ArrayList<HashMap<String, Object>> seatcolList=null;	
		ArrayList<HashMap<String, Object>> bordingList=null;
		ArrayList<HashMap<String, Object>> dropingList=null;

		ArrayList<HashMap<String, Object>> farList=new ArrayList<>();
		ArrayList<HashMap<String, Object>> totalSeats=new ArrayList<>();
		String uppserStatus="N";
		HashMap<String,Object> seatLayouthasaMap=null;	
		HashMap<String,Object> SeatMapOutMAp=null;
		HashMap<String,Object> hashMap=null;
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject obj2=new JSONObject();
			obj2.put("ScheduleId", ScheduleId);
			obj2.put("StationId",StationId);
			obj2.put("TransportId", TransportId);
			obj2.put("TravelDate", TravelDate);

			reJson.put("SeatMapInput",obj2);
			reJson.put("UserTrackId",UserTrackId);

			//System.out.println("Request : "+reJson);
			JSONObject responseJson=BusAPI.callBusApi("GetSeatMap", reJson);
			//System.out.println("ResponceList"+responseJson.toString());

			/*Request : {"SeatMapInput":{"ScheduleId":"348146","StationId":"2254","TransportId":24,"TravelDate":"10\/17\/2015"},"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 
			Response : {"ResponseStatus":1,"UserTrackId":"RMNZZ97799999989907984974966182","SeatMapOutput":{"BusNumber":"","SeatLayoutDetails":{"SeatLayout":{"SeatColumns":[{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"2","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"1","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,null,null]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"3","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"4","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"5","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"6","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"10","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"9","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"8","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"7","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"11","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"12","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"13","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"14","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"18","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"17","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"16","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"15","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"19","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"20","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"21","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"22","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"26","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"25","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"24","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"23","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"27","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"28","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"29","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"30","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]}]},"BookedSeats":null,"AvailableSeats":[{"SeatNo":"1"},{"SeatNo":"10"},{"SeatNo":"11"},{"SeatNo":"12"},{"SeatNo":"13"},{"SeatNo":"14"},{"SeatNo":"15"},{"SeatNo":"16"},{"SeatNo":"17"},{"SeatNo":"18"},{"SeatNo":"19"},{"SeatNo":"2"},{"SeatNo":"21"},{"SeatNo":"22"},{"SeatNo":"23"},{"SeatNo":"24"},{"SeatNo":"25"},{"SeatNo":"26"},{"SeatNo":"27"},{"SeatNo":"28"},{"SeatNo":"29"},{"SeatNo":"3"},{"SeatNo":"30"},{"SeatNo":"5"},{"SeatNo":"6"},{"SeatNo":"7"},{"SeatNo":"8"},{"SeatNo":"9"}],"LayoutFlag":1},"BoardingPoints":[{"Place":"Central","ContactNumber":"044 25380754","Time":"10:00 PM","Address":"2/1, E.V.R. Road","BoardingId":"5057","LandMark":"Opp.Central Rly Station"}],"Fares":[{"Fare":475,"SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":0,"SeatTypeId":22,"ServiceTax":23}],"DroppingPoints":[{"DroppingId":"5059","Place":"Kalasipalaya","ContactNumber":"","Time":"05:00 AM","Address":"","LandMark":""}]}}
			 */

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				HashMap<String, Object> mainMap=new HashMap<String, Object>();
				mainMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject seatMapOutput=new JSONObject();


				seatMapOutput=responseJson.getJSONObject("SeatMapOutput");	
				SeatMapOutMAp=new HashMap<String, Object>();
				SeatMapOutMAp.put("BusNumber",seatMapOutput.getString("BusNumber"));

				bordingList=new ArrayList<HashMap<String,Object>>();
				JSONArray BoardingPointsArray=seatMapOutput.optJSONArray("BoardingPoints");
				if(BoardingPointsArray!=null && BoardingPointsArray.length()>0){
					for (int i = 0; i < BoardingPointsArray.length(); i++) {
						JSONObject BoardingPointsJSN=BoardingPointsArray.getJSONObject(i);

						HashMap<String, Object>BoardingPointsMap=new HashMap<String, Object>();
						BoardingPointsMap.put("BoardingId",BoardingPointsJSN.getString("BoardingId"));
						BoardingPointsMap.put("Place", BoardingPointsJSN.getString("Place"));
						BoardingPointsMap.put("Time", BoardingPointsJSN.getString("Time"));
						BoardingPointsMap.put("Address", BoardingPointsJSN.getString("Address"));
						BoardingPointsMap.put("LandMark", BoardingPointsJSN.getString("LandMark"));
						BoardingPointsMap.put("ContactNumber", BoardingPointsJSN.getString("ContactNumber"));


						bordingList.add(BoardingPointsMap);


					}
				}
				JSONArray dropArray=new JSONArray();
				if(dropArray!=null)
				{
					dropingList=new ArrayList<HashMap<String,Object>>();
					dropArray= seatMapOutput.optJSONArray("DroppingPoints");			

					if( dropArray!=null &&  dropArray.length()>0){

						for(int j=0;j<dropArray.length();j++){

							if(!dropArray.isNull(j)){
								JSONObject	dropjson=dropArray.getJSONObject(j);
								hashMap=new HashMap<String, Object>();
								hashMap.put("DroppingId",dropjson.getString("DroppingId"));
								hashMap.put("Place", dropjson.getString("Place"));
								hashMap.put("Time", dropjson.getString("Time"));
								hashMap.put("Address", dropjson.getString("Address"));
								hashMap.put("LandMark", dropjson.getString("LandMark"));
								hashMap.put("ContactNumber", dropjson.getString("ContactNumber"));
								dropingList.add(hashMap);	
							}
						}
					}
				}
				JSONArray fareArray=new JSONArray();
				fareArray= seatMapOutput.getJSONArray("Fares");				
				if(fareArray.length()>0){

					for(int k=0;k<fareArray.length();k++){
						JSONObject farejson=new JSONObject();
						farejson=fareArray.getJSONObject(k);
						hashMap=new HashMap<String, Object>();
						hashMap.put("SeatTypeId",farejson.getString("SeatTypeId"));
						hashMap.put("SeatType", farejson.getString("SeatType"));
						hashMap.put("Fare", farejson.optString("Fare"));
						hashMap.put("ServiceTax", farejson.getString("ServiceTax"));
						hashMap.put("ConvenienceFee", farejson.getString("ConvenienceFee"));			
						farList.add(hashMap);					
					}
				}

				//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<  SeatLayoutDetails...............................	

				JSONObject seatLayoutDetailjson=seatMapOutput.getJSONObject("SeatLayoutDetails") ;					
				seatLayouthasaMap=new HashMap<String, Object>();
				seatLayouthasaMap.put("LayoutFlag", seatLayoutDetailjson.getInt("LayoutFlag"));


				bookedSeatsList=new ArrayList<>();
				JSONArray bookedSeatsArray= seatLayoutDetailjson.optJSONArray("BookedSeats");				
				if (bookedSeatsArray!=null && bookedSeatsArray.length()>0) {	

					for(int m=0;m<bookedSeatsArray.length();m++){
						JSONObject bookSeatjson=bookedSeatsArray.getJSONObject(m);
						hashMap=new HashMap<String, Object>();
						hashMap.put("SeatNo",bookSeatjson.getString("SeatNo"));
						hashMap.put("Gender",bookSeatjson.getString("Gender"));
						bookedSeatsList.add(hashMap);

					}

				}

				JSONObject seatLayout=seatLayoutDetailjson.getJSONObject("SeatLayout");
				HashMap<String, Object> SeatLayoutMap=new HashMap<String, Object>();


				seatcolList=new ArrayList<HashMap<String,Object>>();
				JSONArray seatColArray= seatLayout.getJSONArray("SeatColumns");				
				if (seatColArray.length()>0) 
				{

					for(int l=0;l<seatColArray.length();l++)
					{
						JSONObject seatjson=seatColArray.getJSONObject(l);
						HashMap<String,Object> SeatColumnsMap=new HashMap<String, Object>();

						seatList=new ArrayList<HashMap<String,Object>>();
						upperSeatList=new ArrayList<HashMap<String,Object>>();
						JSONArray seatsArray= seatjson.getJSONArray("Seats");						
						if (seatsArray.length()>0) {	
							for(int m=0;m<seatsArray.length();m++){
								JSONObject seatsjson=seatsArray.optJSONObject(m);

								String berthType="";
								
								if(seatsjson!=null && seatsjson.has("SeatNo")){

									hashMap=new HashMap<String, Object>();

									boolean bookedStatus=false;
									boolean ladiesStatus=false;

									if(bookedSeatsArray!=null){

										for(int a=0;a<bookedSeatsArray.length();a++){
											JSONObject bookSeatjson=bookedSeatsArray.getJSONObject(a);

											if(seatsjson.getString("SeatNo").equals(bookSeatjson.getString("SeatNo"))){
												bookedStatus=true;
												if("M".equalsIgnoreCase(bookSeatjson.getString("Gender")))
													ladiesStatus=false;
												if("F".equalsIgnoreCase(bookSeatjson.getString("Gender")))
													ladiesStatus=true;
											}

										}
									}
									if(bookedStatus)
										hashMap.put("SeatStatus","Booked");
									else
										hashMap.put("SeatStatus","Available");

									if(ladiesStatus)
										hashMap.put("Gender","F");
									else
										hashMap.put("Gender","M");

									hashMap.put("SeatNo",seatsjson.getString("SeatNo"));
									hashMap.put("SeatTypeId", seatsjson.getString("SeatTypeId"));
									
									if("U".equalsIgnoreCase(seatsjson.getString("BerthType")))
										uppserStatus="Y";
									
									berthType=seatsjson.getString("BerthType").equalsIgnoreCase("")?"L":seatsjson.getString("BerthType");
									
									hashMap.put("BerthType",berthType);
									
									String seatType=seatsjson.getString("SeatType");
									
									if(seatType!=null){
										
										if(seatType.contains("Seater"))
											seatType="Seater";
										else if(seatType.contains("Berth"))
											seatType="Sleeper";
										else
											seatType="Seater";
										
									}else{
										seatType="Seater";
									}
									
									
									hashMap.put("SeatType", seatType);
									hashMap.put("Fare", seatsjson.getString("Fare"));
									hashMap.put("ServiceTax", seatsjson.getString("ServiceTax"));
									hashMap.put("ConvenienceFee", seatsjson.getString("ConvenienceFee"));

									totalSeats.add(hashMap);



								}else{
									hashMap=new HashMap<>();
								}

								if(seatsjson!=null && seatsjson.has("SeatNo")){
									if("U".equalsIgnoreCase(berthType)){
										upperSeatList.add(hashMap);
										seatList.add(new HashMap<String, Object>());
									}else{
										upperSeatList.add(new HashMap<String, Object>());
										seatList.add(hashMap);
									}
								}else{
										upperSeatList.add(new HashMap<String, Object>());
										seatList.add(new HashMap<String, Object>());
								}
								

							}
							
							
						}else{

						}
						SeatColumnsMap.put("Seats", seatList);
						SeatColumnsMap.put("UpperSeats", upperSeatList);
						seatcolList.add(SeatColumnsMap);
					}
				}
				

				/*avaiSeatsList=new ArrayList<HashMap<String,Object>>();
				JSONArray availableSeatsArray= seatLayoutDetailjson.getJSONArray("AvailableSeats");				
				if (availableSeatsArray.length()>0) {	

					for(int m=0;m<availableSeatsArray.length();m++){
						JSONObject availableSeatjson=availableSeatsArray.getJSONObject(m);
						hashMap=new HashMap<String, Object>();
						hashMap.put("SeatNo",availableSeatjson.getString("SeatNo"));
						avaiSeatsList.add(hashMap);

					}

				}*/


				SeatLayoutMap.put("SeatColumns",seatcolList);
				seatLayouthasaMap.put("SeatLayout", SeatLayoutMap);
				seatLayouthasaMap.put("upperStatus", uppserStatus);
				
				seatLayouthasaMap.put("BoardingPoints",bordingList );
				seatLayouthasaMap.put("DroppingPoints",dropingList);
				seatLayouthasaMap.put("Fares",farList );

			}


		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return seatLayouthasaMap;
	}




	public ArrayList<HashMap<String, Object>> SeatInfo(String originId,String destinationId,String departureTime,String scheduleId,
			String stationId,String transportId,String travelDate,String userTrackId,String 
			date,String Place,String ContactNumber,String Time,String Address,String BoardingId,String LandMark,
			String Fare,String SeatType,String ConvenienceFee,String SeatTypeId,String ServiceTax,String DroppingId,String Details)
	{
		ArrayList<HashMap<String, Object>> SeatInfoList=new ArrayList<>();

		HashMap<String, Object> SeatInfoMap=new HashMap<>();
		SeatInfoMap.put("originId", originId);
		SeatInfoMap.put("destinationId", destinationId);
		SeatInfoMap.put("departureTime", departureTime);
		SeatInfoMap.put("scheduleId", scheduleId);
		SeatInfoMap.put("stationId", stationId);
		SeatInfoMap.put("transportId", transportId);
		SeatInfoMap.put("travelDate", travelDate);
		SeatInfoMap.put("userTrackId", userTrackId);
		SeatInfoMap.put("date", date);
		SeatInfoMap.put("Place", Place);
		SeatInfoMap.put("ContactNumber", ContactNumber);
		SeatInfoMap.put("Time", Time);
		SeatInfoMap.put("Address", Address);
		SeatInfoMap.put("BoardingId", BoardingId);
		SeatInfoMap.put("LandMark", LandMark);
		SeatInfoMap.put("Fare", Fare);
		SeatInfoMap.put("SeatType", SeatType);
		SeatInfoMap.put("ConvenienceFee", ConvenienceFee);
		SeatInfoMap.put("SeatTypeId", SeatTypeId);
		SeatInfoMap.put("ServiceTax", ServiceTax);
		SeatInfoMap.put("DroppingId", DroppingId);


		SeatInfoList.add(SeatInfoMap);


		return SeatInfoList;

	}

	public  HashMap<String,Object> GetSeatBlockRequest(String userTrackId, String title, String name, String address, String contactNumber, String city, String countryId, String emailId, 
			String idProofId, String idProofNumber, int totalTickets,double totalAmount, int transportId,  String scheduleId, String stationId, String travelDate,net.sf.json.JSONArray passengerList) {		

		HashMap<String,Object> blockingOutputMap=null;
		JSONObject BlockingInput = new JSONObject();

		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject bookingCustomerDetails=new JSONObject();
			bookingCustomerDetails.put("Title",title);
			bookingCustomerDetails.put("Name", name);
			bookingCustomerDetails.put("Address", address);
			bookingCustomerDetails.put("ContactNumber", contactNumber);
			bookingCustomerDetails.put("City",  city);
			bookingCustomerDetails.put("CountryId", countryId);
			bookingCustomerDetails.put("EmailId", emailId);
			bookingCustomerDetails.put("IdProofId",idProofId);
			bookingCustomerDetails.put("IdProofNumber", idProofNumber);

			JSONObject bookingDetails=new JSONObject();

			bookingDetails.put("TotalTickets", totalTickets);
			bookingDetails.put("TotalAmount", totalAmount);
			bookingDetails.put("TransportId", transportId);
			bookingDetails.put("ScheduleId", scheduleId);
			bookingDetails.put("StationId", stationId);
			bookingDetails.put("TravelDate", travelDate);


			bookingDetails.put("PassengerList", passengerList);

			BlockingInput.put("BookingDetails", bookingDetails);
			BlockingInput.put("BookingCustomerDetails", bookingCustomerDetails);

			reJson.put("BlockingInput",BlockingInput);	
			reJson.put("UserTrackId", userTrackId);


			System.out.println("Request : "+reJson);
			JSONObject responseJson = BusAPI.callBusApi("GetSeatBlock", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"},"blocking input":{"bookingdetail":{"ScheduleId":"348146 ","StationId":"2254 ","PassengerListDetals":[{"PassengerName ":"Test","BoardingId ":"221","Gender ":"M","SeatNo ":"20","Age ":36,"DroppingId ":"41","SeatTypeId ":22,"Fare ":475}],"TotalAmount":475,"TransportId":24,"TotalTickets":1,"TravelDate":"10\/17\/2015 "},"customer details":{"Name":"Test","ContactNumber":"9944100866","IdProofId":" ","IdProofNumber":" ","Address":"Guindy","CountryId":"91","EmailId":"velmurugan@hermes-it.in","City":"Chennai","Title":"Mr."}}}
			Output from Server .... 

			Response : {"FailureRemarks":"GetSeatBlockInput Required","ResponseStatus":0,"UserTrackId":"RMNZZ97799999989907984974966182"}

			 */

			int status=responseJson.getInt("ResponseStatus");
			System.out.println("ResponceList"+status);
			if(status==1){
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject blockingOutput=responseJson.getJSONObject("BlockingOutput") ;
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("BlockingStatus:",blockingOutput.getString("BlockingStatus"));
				blockingOutputMap.put("Remarks", blockingOutput.getString("Remarks"));
				blockingOutputMap.put("status", "0");

				System.out.println("ResponceList"+blockingOutputMap);
			}else{
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("status", "1");
				blockingOutputMap.put("Remarks", responseJson.getString("FailureRemarks"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return blockingOutputMap;
	}


	//.......................Get Booking Request..........................//

	public net.sf.json.JSONObject GetBookingRequest(String tranId,String userTrackId, String title, String name, String address, String contactNumber, String city, String countryId, String emailId, 
			String idProofId, String idProofNumber, int totalTickets,double totalAmount, int transportId,  String scheduleId, String stationId, String travelDate,net.sf.json.JSONArray passengerList) {		

		net.sf.json.JSONObject	ticketDetailsMap=new net.sf.json.JSONObject();
		JSONObject BlockingInput = new JSONObject();

		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject bookingCustomerDetails=new JSONObject();
			bookingCustomerDetails.put("Title",title);
			bookingCustomerDetails.put("Name", name);
			bookingCustomerDetails.put("Address", address);
			bookingCustomerDetails.put("ContactNumber", contactNumber);
			bookingCustomerDetails.put("City",  city);
			bookingCustomerDetails.put("CountryId", countryId);
			bookingCustomerDetails.put("EmailId", emailId);
			bookingCustomerDetails.put("IdProofId",idProofId);
			bookingCustomerDetails.put("IdProofNumber", idProofNumber);

			JSONObject bookingDetails=new JSONObject();

			bookingDetails.put("TotalTickets", totalTickets);
			bookingDetails.put("TotalAmount", totalAmount);
			bookingDetails.put("TransportId", transportId);
			bookingDetails.put("ScheduleId", scheduleId);
			bookingDetails.put("StationId", stationId);
			bookingDetails.put("TravelDate", travelDate);

			bookingDetails.put("PassengerList", passengerList);

			BlockingInput.put("BookingDetails", bookingDetails);
			BlockingInput.put("BookingCustomerDetails", bookingCustomerDetails);

			reJson.put("BookingInput",BlockingInput);	
			reJson.put("UserTrackId", userTrackId);


			System.out.println("Request : "+reJson);

			BusBookingDao bookingDao=new BusBookingDao();

			//Save Request log 
			bookingDao.saveRequestLog(reJson.toString(), tranId);
			//---------------------------------------------------------------

			JSONObject responseJson = BusAPI.callBusApi("GetBook", reJson);

			System.out.println("Response : "+responseJson);

			//Update Response log
			bookingDao.updateResponseLog(responseJson.toString(), tranId);


			/*Request : {"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"},"blocking input":{"bookingdetail":{"ScheduleId":"348146 ","StationId":"2254 ","PassengerListDetals":[{"PassengerName ":"Test","BoardingId ":"5057","Gender ":"M","SeatNo ":"20","Age ":36,"DroppingId ":"5059","SeatTypeId ":22,"Fare ":475}],"TotalAmount":475,"TransportId":24,"TotalTickets":1,"TravelDate":"10\/17\/2015 "},"customer details":{"Name":"Test","ContactNumber":"9944100866","IdProofId":" ","IdProofNumber":" ","Address":"Guindy","CountryId":"91","EmailId":"velmurugan@hermes-it.in","City":"Chennai","Title":"Mr."}}}
			Output from Server .... 

			Response : {"FailureRemarks":"BookingInput Required","ResponseStatus":0,"UserTrackId":"RMNZZ97799999989907984974966182"}
			 */

			int status=responseJson.getInt("ResponseStatus");
			System.out.println("ResponceList"+status);
			if(status==1){

				HashMap<String, Object> mainMap=new HashMap<String, Object>();
				mainMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject blockingOutput=responseJson.getJSONObject("BookingOutput");


				JSONObject ticketDetails=blockingOutput.getJSONObject("TicketDetails") ;

				ticketDetailsMap.put("ServiceTax",ticketDetails.getString("ServiceTax"));
				ticketDetailsMap.put("Commission", ticketDetails.getString("Commission"));
				ticketDetailsMap.put("CancellationPolicy", ticketDetails.getString("CancellationPolicy"));

				ticketDetailsMap.put("UserTrackId", responseJson.getString("UserTrackId"));
				ticketDetailsMap.put("status", "0");
				ticketDetailsMap.put("Remarks","SUCCESSFULLLY BLOCKED");


				JSONObject pnrDetails=ticketDetails.getJSONObject("PNRDetails");
				HashMap<String, Object>	PNRDetailsMap=new HashMap<String, Object>();
				PNRDetailsMap.put("TransactionId",pnrDetails.getString("TransactionId"));
				PNRDetailsMap.put("TransportPNR", pnrDetails.getString("TransportPNR"));
				PNRDetailsMap.put("TotalAmount", pnrDetails.getString("TotalAmount"));
				PNRDetailsMap.put("TotalTickets", pnrDetails.getString("TotalTickets"));
				PNRDetailsMap.put("BusName", pnrDetails.getString("BusName"));
				PNRDetailsMap.put("Origin", pnrDetails.getString("Origin"));
				PNRDetailsMap.put("Destination", pnrDetails.getString("Destination"));
				PNRDetailsMap.put("TravelDate", pnrDetails.getString("TravelDate"));
				PNRDetailsMap.put("DepartureTime", pnrDetails.getString("DepartureTime"));




				ArrayList<HashMap<String,Object>> paxList=new ArrayList<HashMap<String,Object>>();
				JSONArray paxListArray= pnrDetails.getJSONArray("PaxList");
				if(paxListArray.length()>0){
					for (int i = 0; i < paxListArray.length(); i++) {
						JSONObject paxjson=paxListArray.getJSONObject(i);
						HashMap<String, Object>	paxMap=new HashMap<String, Object>();
						paxMap.put("TicketNumber",paxjson.getString("TicketNumber"));
						paxMap.put("SeatNo", paxjson.getString("SeatNo"));
						paxMap.put("SeatType", paxjson.getString("SeatType"));
						paxMap.put("PassengerName", paxjson.getString("PassengerName"));
						paxMap.put("Gender", paxjson.getString("Gender"));
						paxMap.put("Age", paxjson.getString("Age"));
						paxMap.put("Fare", paxjson.getString("Fare"));
						paxMap.put("ReportingTime", paxjson.getString("ReportingTime"));
						paxMap.put("ServiceCharges", paxjson.getString("ServiceCharges"));	
						paxMap.put("PNR", pnrDetails.getString("TransportPNR"));


						JSONObject boardingPoints=paxjson.getJSONObject("BoardingPoints");
						paxMap.put("BoardingId", boardingPoints.getString("BoardingId"));
						paxMap.put("BoardingPlace", boardingPoints.getString("Place"));
						paxMap.put("BoardingTime", boardingPoints.getString("Time"));
						paxMap.put("BoardingAddress", boardingPoints.getString("Address"));
						paxMap.put("BoardingLandMark", boardingPoints.getString("LandMark"));
						paxMap.put("BoardingContactNumber", boardingPoints.getString("ContactNumber"));	


						JSONObject droppingPoints=paxjson.getJSONObject("DroppingPoints");
						paxMap.put("DroppingId", droppingPoints.getString("DroppingId"));
						paxMap.put("DroppingPlace", droppingPoints.getString("Place"));
						paxMap.put("DroppingTime", droppingPoints.getString("Time"));
						paxMap.put("DroppingAddress", droppingPoints.getString("Address"));
						paxMap.put("DroppingLandMark", droppingPoints.getString("LandMark"));
						paxMap.put("DroppingContactNumber", droppingPoints.getString("ContactNumber"));				
						paxList.add(paxMap);
					}					
				}		


				JSONObject transportDetails=pnrDetails.getJSONObject("TransportDetails");
				HashMap<String, Object>	transportDetailsMap=new HashMap<String, Object>();
				transportDetailsMap.put("TransportName", transportDetails.getString("TransportName"));
				transportDetailsMap.put("Address1", transportDetails.getString("Address1"));
				transportDetailsMap.put("Address2", transportDetails.getString("Address2"));
				transportDetailsMap.put("Address3", transportDetails.getString("Address3"));
				transportDetailsMap.put("CityNamePinCode", transportDetails.getString("CityNamePinCode"));
				transportDetailsMap.put("ContactNumber", transportDetails.getString("ContactNumber"));				
				transportDetailsMap.put("Fax", transportDetails.getString("Fax"));
				transportDetailsMap.put("Website", transportDetails.getString("Website"));
				transportDetailsMap.put("EmailId", transportDetails.getString("EmailId"));	


				ticketDetailsMap.put("TransportDetails", transportDetailsMap);
				ticketDetailsMap.put("PaxList", paxList);
				ticketDetailsMap.put("PNRDetails",PNRDetailsMap);	

				System.out.println("ticketDetailsMap "+ticketDetailsMap.toString());
			}else{
				ticketDetailsMap.put("UserTrackId", responseJson.getString("UserTrackId"));
				ticketDetailsMap.put("status", "1");
				ticketDetailsMap.put("Remarks", responseJson.getString("FailureRemarks"));



			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticketDetailsMap;
	}

	//........................Get Transaction Status Request ..........................//

	JSONObject GetTransactionStatus(String UserTrackId) 
	{
		JSONObject mainJson=null;
		JSONObject response=new JSONObject();
		try {

			JSONObject requestJsn=new JSONObject();
			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			requestJsn.put("Authentication",obj);
			requestJsn.put("UserTrackId", UserTrackId);
			response=BusAPI.callBusApi("GetTransactionStatus", requestJsn);

			int status=response.getInt("ResponseStatus");

			if(status==1)
			{
				mainJson=response.getJSONObject("TransactionStatusOutput");
				HashMap<String, Object> TransactionStatusOutputMap=new HashMap<>();
				TransactionStatusOutputMap.put("TransactionStatus", mainJson.getString("TransactionStatus"));
				TransactionStatusOutputMap.put("Remarks", mainJson.getString("Remarks"));
				JSONObject TicketDetailsJsn=mainJson.getJSONObject("TicketDetails");
				HashMap<String, Object> ticketDetailsmap=new HashMap<>();
				ticketDetailsmap.put("Commission", TicketDetailsJsn.getString("Commission"));
				ticketDetailsmap.put("ConvenienceFee", TicketDetailsJsn.getString("ConvenienceFee"));
				ticketDetailsmap.put("ServiceTax", TicketDetailsJsn.getString("Commission"));
				JSONObject BookingCustomerDetailsJson=TicketDetailsJsn.getJSONObject("BookingCustomerDetails");
				HashMap<String, Object> bookingCustomerDetailsMap=new HashMap<>();
				bookingCustomerDetailsMap.put("Name", BookingCustomerDetailsJson.getString("Name"));
				bookingCustomerDetailsMap.put("ContactNumber", BookingCustomerDetailsJson.getString("ContactNumber"));
				bookingCustomerDetailsMap.put("BookedDate", BookingCustomerDetailsJson.getString("BookedDate"));
				bookingCustomerDetailsMap.put("IdProofId", BookingCustomerDetailsJson.getString("IdProofId"));
				bookingCustomerDetailsMap.put("IdProofNumber", BookingCustomerDetailsJson.getString("IdProofNumber"));
				bookingCustomerDetailsMap.put("Address", BookingCustomerDetailsJson.getString("Address"));
				bookingCustomerDetailsMap.put("CountryId", BookingCustomerDetailsJson.getString("CountryId"));
				bookingCustomerDetailsMap.put("EmailId", BookingCustomerDetailsJson.getString("EmailId"));
				bookingCustomerDetailsMap.put("City", BookingCustomerDetailsJson.getString("City"));
				bookingCustomerDetailsMap.put("Title", BookingCustomerDetailsJson.getString("Title"));
				ticketDetailsmap.put("BookingCustomerDetails", bookingCustomerDetailsMap);
				TransactionStatusOutputMap.put("TicketDetails", ticketDetailsmap);


				System.out.println("Sundaram::::::::::::::"+mainJson);

				return mainJson;

			}


		} catch (Exception e) {
			e.printStackTrace();
		}


		return mainJson;

	}


	//........................Get Reprint Request ..........................//

	public JSONObject GetReprintRequest() {

		JSONObject responseJson=null;
		JSONObject mainObject2=new JSONObject();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject reprintRequest = new JSONObject();

			reprintRequest.put("TransactionId", "EB7NQDU");
			reJson.put("ReprintInput",reprintRequest);

			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetReprint", reJson);

			System.out.println("Response : "+responseJson);

			/*	Request : {"ReprintInput":{"TransactionId":"EB7NQDU"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"ResponseStatus":1,"UserTrackId":"RMNZZ97799999989907984974966182","ReprintOutput":{"ReprintTicketDetails":{"Commission":"0.00","ReprintPNRDetails":{"TransactionId":"EB7NQDU","BusName":"Chennai-Bangalore 10:00 PM 1 SEATER","FareBreakUpDetails":null,"DepartureTime":"10:00 PM","TransportPNR":"EB7NQDU","TransportDetails":{"ContactNumber":"080-26702426","Address3":"Near Bangalore Medical College, Kalasipalayam","Website":"www.sharmatransports.com","Address2":"328, Tippu Sultan Palace Road","Address1":"H.O.: Sangeetha Bhawan","Fax":"","EmailId":"sharma_transports@yahoo.co.in","CityNamePinCode":"Bangalore-560002","TransportName":"Sharma Transports"},"TotalAmount":475,"Origin":"Chennai","TotalTickets":1,"ReprintPaxList":[{"Fare":475,"PassengerName":"Test","ServiceCharges":"0.00","Age":35,"SeatNo":"4","SeatType":"2","TicketStatus":"","Gender":"M","BoardingPoints":{"Place":"Central","ContactNumber":"044 25380754","Time":"21:30:00","Address":"2\/1, E.V.R. Road","BoardingId":null,"LandMark":"Opp.Central Rly Station"},"ReportingTime":"21:30:00","DroppingPoints":{"DroppingId":null,"Place":"Kalasipalaya","ContactNumber":"080 26701028","Time":"05:00 AM","Address":"Tippu Sulthan Road","LandMark":"Near Bangalore Medical College"},"TicketNumber":"EB7NQDU0"}],"TravelDate":"17\/10\/2015","Destination":"Bangalore"},"CancellationPolicy":"","ConvenienceFee":null,"BookingCustomerDetails":{"Name":"test","ContactNumber":"9944100866","BookedDate":"10\/09\/2015  2:55PM","IdProofId":"0","IdProofNumber":"","Address":"guindy","CountryId":null,"EmailId":"velmurugangce@gmail.com","City":null,"Title":null},"ServiceTax":"0"}}}
			 */

			String ResponseStatus=responseJson.getString("ResponseStatus");
			if("1".equalsIgnoreCase(ResponseStatus)){

				mainObject2=new JSONObject();	
				mainObject2.put("UserTrackId", responseJson.getString("UserTrackId"));


				JSONObject reprintOutput=responseJson.getJSONObject("ReprintOutput") ;
				JSONObject reprintTicketDetails=reprintOutput.getJSONObject("ReprintTicketDetails") ;

				reprintTicketDetails.put("ServiceTax", reprintTicketDetails.getString("ServiceTax"));
				reprintTicketDetails.put("Commission", reprintTicketDetails.getString("Commission"));
				reprintTicketDetails.put("CancellationPolicy", reprintTicketDetails.getString("CancellationPolicy"));

				JSONObject bookingCustomerDetails=reprintTicketDetails.getJSONObject("BookingCustomerDetails") ;					
				bookingCustomerDetails.put("Title",bookingCustomerDetails.getString("Title"));
				bookingCustomerDetails.put("Name", bookingCustomerDetails.getString("Name"));
				bookingCustomerDetails.put("Address", bookingCustomerDetails.getString("Address"));
				bookingCustomerDetails.put("ContactNumber", bookingCustomerDetails.getString("ContactNumber"));
				bookingCustomerDetails.put("City", bookingCustomerDetails.getString("City"));
				bookingCustomerDetails.put("CountryId", bookingCustomerDetails.getString("CountryId"));
				bookingCustomerDetails.put("EmailId", bookingCustomerDetails.getString("EmailId"));
				bookingCustomerDetails.put("IdProofId", bookingCustomerDetails.getString("IdProofId"));
				bookingCustomerDetails.put("IdProofNumber", bookingCustomerDetails.getString("IdProofNumber"));
				bookingCustomerDetails.put("BookedDate", bookingCustomerDetails.getString("BookedDate"));
				reprintTicketDetails.put("bookingCustomerDetails", bookingCustomerDetails);

				JSONObject reprintPNRDetails=reprintTicketDetails.getJSONObject("ReprintPNRDetails") ;
				reprintPNRDetails.put("TransactionId",reprintPNRDetails.getString("TransactionId"));
				reprintPNRDetails.put("TransportPNR", reprintPNRDetails.getString("TransportPNR"));
				reprintPNRDetails.put("TotalAmount", reprintPNRDetails.getString("TotalAmount"));
				reprintPNRDetails.put("TotalTickets", reprintPNRDetails.getString("TotalTickets"));
				reprintPNRDetails.put("BusName", reprintPNRDetails.getString("BusName"));
				reprintPNRDetails.put("Origin", reprintPNRDetails.getString("Origin"));
				reprintPNRDetails.put("Destination", reprintPNRDetails.getString("Destination"));
				reprintPNRDetails.put("TravelDate", reprintPNRDetails.getString("TravelDate"));
				reprintPNRDetails.put("DepartureTime", reprintPNRDetails.getString("DepartureTime"));

				JSONArray reprintPaxArray= reprintPNRDetails.getJSONArray("ReprintPaxList");
				JSONObject reprintjson=new JSONObject() ;
				reprintjson.put("TicketNumber",reprintjson.getString("TicketNumber"));
				reprintjson.put("TicketStatus", reprintjson.getString("TicketStatus"));
				reprintjson.put("SeatNo", reprintjson.getString("SeatNo"));
				reprintjson.put("SeatType", reprintjson.getString("SeatType"));
				reprintjson.put("PassengerName", reprintjson.getString("PassengerName"));
				reprintjson.put("Gender", reprintjson.getString("Gender"));
				reprintjson.put("Age", reprintjson.getString("Age"));
				reprintjson.put("Fare", reprintjson.getString("Fare"));


				JSONObject boardingPoints=reprintjson.getJSONObject("BoardingPoints") ;
				boardingPoints.put("BoardingId", boardingPoints.getString("BoardingId"));
				boardingPoints.put("Place", boardingPoints.getString("Place"));
				boardingPoints.put("Time", boardingPoints.getString("Time"));
				boardingPoints.put("Address", boardingPoints.getString("Address"));
				boardingPoints.put("LandMark", boardingPoints.getString("LandMark"));
				boardingPoints.put("ContactNumber", boardingPoints.getString("ContactNumber"));	
				reprintjson.put("boardingPoints", boardingPoints);


				JSONObject droppingPoints=reprintjson.getJSONObject("DroppingPoints") ;
				droppingPoints.put("DroppingId", droppingPoints.getString("DroppingId"));
				droppingPoints.put("Place", droppingPoints.getString("Place"));
				droppingPoints.put("Time", droppingPoints.getString("Time"));
				droppingPoints.put("Address", droppingPoints.getString("Address"));
				droppingPoints.put("LandMark", droppingPoints.getString("LandMark"));
				droppingPoints.put("ContactNumber", droppingPoints.getString("ContactNumber"));				
				reprintjson.put("droppingPoints", droppingPoints);

				reprintjson.put("ReportingTime", reprintjson.getString("ReportingTime"));
				reprintjson.put("ServiceCharges", reprintjson.getString("ServiceCharges"));		

				reprintPaxArray.put(reprintjson);
				reprintPNRDetails.put("reprintPaxArray" , reprintPaxArray);

				JSONObject transportDetails=reprintPNRDetails.getJSONObject("TransportDetails") ;
				transportDetails.put("TransportName", transportDetails.getString("TransportName"));
				transportDetails.put("Address1", transportDetails.getString("Address1"));
				transportDetails.put("Address2", transportDetails.getString("Address2"));
				transportDetails.put("Address3", transportDetails.getString("Address3"));
				transportDetails.put("CityNamePinCode", transportDetails.getString("CityNamePinCode"));
				transportDetails.put("ContactNumber", transportDetails.getString("ContactNumber"));	
				transportDetails.put("Fax", transportDetails.getString("Fax"));
				transportDetails.put("Website", transportDetails.getString("Website"));
				transportDetails.put("EmailId", transportDetails.getString("EmailId"));		
				reprintPNRDetails.put("transportDetails", transportDetails);

				reprintTicketDetails.put("ReprintPNRDetails", reprintPNRDetails);				
				reprintOutput.put("ReprintTicketDetails", reprintTicketDetails);
				mainObject2.put("ReprintOutput", reprintOutput);

			}

			System.out.println("Responce"+responseJson.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mainObject2;


	}


	//........................Get Cancellation Policy Request ..........................//

	public HashMap<String, String> GetCancellationPolicyRequest(int transportId) {

		HashMap<String, String> responseMap=new HashMap<>();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject cancellationPolicyInput = new JSONObject();

			cancellationPolicyInput.put("TransportId", transportId);
			reJson.put("CancellationPolicyRequest",cancellationPolicyInput);

			System.out.println("Request : "+reJson);
			JSONObject responseJson = BusAPI.callBusApi("GetCancellationPolicy", reJson);

			System.out.println("Response : "+responseJson);

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				responseMap.put("status", "Success");

				JSONObject CancellationPolicyOutput=responseJson.getJSONObject("CancellationPolicyOutput");

				responseMap.put("CancelPolicyDetails", CancellationPolicyOutput.getString("CancelPolicyDetails"));
			}else{
				responseMap.put("status", "Failure");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseMap;
	}

	//........................Get Cancellation Penalty Request ..........................//

	public net.sf.json.JSONObject GetCancellationPenaltyRequest(String TransactionId) {

		HashMap<String, Object>ToCancelPNRDetailsMap=null;
		net.sf.json.JSONObject mainList=new net.sf.json.JSONObject() ;
		try {
			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject cancellationPenalty = new JSONObject();

			cancellationPenalty.put("TransactionId", TransactionId);
			reJson.put("CancellationPenaltyInput",cancellationPenalty);

			System.out.println("Request : "+reJson);
			JSONObject responseJson = BusAPI.callBusApi("GetCancellationPenalty", reJson);

			System.out.println("Response : "+responseJson);

			int status=responseJson.getInt("ResponseStatus");
			if(status==1)
			{
				mainList=new net.sf.json.JSONObject();
				HashMap<String, Object> mainMap=new HashMap<String, Object>();
				JSONObject CancellationPenaltyOutput=responseJson.getJSONObject("CancellationPenaltyOutput") ;
				mainMap.put("CancellationPenaltyOutput",responseJson.getString("CancellationPenaltyOutput"));
				JSONObject ToCancelPNRDetails=CancellationPenaltyOutput.getJSONObject("ToCancelPNRDetails");
				ToCancelPNRDetailsMap=new HashMap<>();

				ArrayList<HashMap<String, Object>>ToCancelPaxList=new ArrayList<HashMap<String,Object>>();
				JSONArray ToCancelPaxListArray= ToCancelPNRDetails.getJSONArray("ToCancelPaxList");
				if(ToCancelPaxListArray!=null && ToCancelPaxListArray.length()>0)
				{
					for(int i=0;i<ToCancelPaxListArray.length();i++){
						JSONObject avbusjsn=ToCancelPaxListArray.getJSONObject(i);
						HashMap<String, Object> hashMap = new HashMap<String, Object>();
						hashMap.put("Fare",avbusjsn.getInt("Fare"));
						hashMap.put("PassengerName", avbusjsn.getString("PassengerName"));
						hashMap.put("SeatNo", avbusjsn.getString("SeatNo"));
						hashMap.put("Age", avbusjsn.getString("Age"));
						hashMap.put("PenatlyAmount", avbusjsn.getDouble("PenatlyAmount"));
						hashMap.put("Gender", avbusjsn.getString("Gender"));
						hashMap.put("TicketNumber", avbusjsn.getString("TicketNumber"));
						ToCancelPaxList.add(hashMap);


					}
				}



				ToCancelPNRDetailsMap.put("ToCancelPaxList", ToCancelPaxList);
				ToCancelPNRDetailsMap.put("DepatureTime", ToCancelPNRDetails.get("DepatureTime"));
				ToCancelPNRDetailsMap.put("PNRStatus", ToCancelPNRDetails.get("PNRStatus"));
				ToCancelPNRDetailsMap.put("BookedDate", ToCancelPNRDetails.get("BookedDate"));
				/*ToCancelPNRDetailsMap.put("PenatlyAmount", ToCancelPNRDetails.get("PenatlyAmount"));*/
				ToCancelPNRDetailsMap.put("Origin", ToCancelPNRDetails.get("Origin"));

				ToCancelPNRDetailsMap.put("TravelDate", ToCancelPNRDetails.get("TravelDate"));
				ToCancelPNRDetailsMap.put("Destination", ToCancelPNRDetails.get("Destination"));
				ToCancelPNRDetailsMap.put("TransportName", ToCancelPNRDetails.get("TransportName"));

				mainList.put("ToCancelPNRDetails", ToCancelPNRDetailsMap);

			}



		} catch (Exception e) {
			e.printStackTrace();
		}

		return mainList;
	}	




	//.......................Get Cancellation Request..........................//

	public net.sf.json.JSONObject GetCancellationRequest(String transactionId, int totalticketsToCancel, double penaltyAmount, String ticketNumbers,int transportId) {		JSONObject responseJson=null;
		net.sf.json.JSONObject resMap=new net.sf.json.JSONObject();
		ArrayList<HashMap<String, Object>> cancelpaxList=new ArrayList<>();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject cancellationRequest = new JSONObject();

			cancellationRequest.put("TransactionId", transactionId);
			cancellationRequest.put("TotalTicketsToCancel", totalticketsToCancel);
			cancellationRequest.put("PenaltyAmount", penaltyAmount);
			cancellationRequest.put("TicketNumbers", ticketNumbers);
			cancellationRequest.put("TransportId", transportId);

			reJson.put("CancellationInput",cancellationRequest);

			System.out.println("Request : "+reJson);


			/*BusBookingDao bookingDao=new BusBookingDao();
			//Save Request log 
			bookingDao.saveRequestLog(reJson.toString(), transactionId);*/
			//---------------------------------------------------------------

			responseJson=BusAPI.callBusApi("GetCancellation", reJson);

			//Update Response log
			/*bookingDao.updateResponseLog(responseJson.toString(), transactionId);
			 */
			System.out.println("Response : "+responseJson);

			if(responseJson!=null){

				int ResponseStatus=responseJson.getInt("ResponseStatus");

				if(ResponseStatus==1){

					JSONObject CancellationOutput=responseJson.getJSONObject("CancellationOutput");

					JSONObject CanceledTicketDetails=CancellationOutput.getJSONObject("CanceledTicketDetails");

					JSONObject BookingCustomerDetails=CanceledTicketDetails.getJSONObject("BookingCustomerDetails");

					HashMap<String,Object> BookingCustomerDetailsMap=new HashMap<>();
					BookingCustomerDetailsMap.put("Name", BookingCustomerDetails.get("Name"));
					BookingCustomerDetailsMap.put("ContactNumber", BookingCustomerDetails.get("ContactNumber"));
					BookingCustomerDetailsMap.put("BookedDate", BookingCustomerDetails.get("BookedDate"));
					BookingCustomerDetailsMap.put("IdProofId", BookingCustomerDetails.get("IdProofId"));
					BookingCustomerDetailsMap.put("IdProofNumber", BookingCustomerDetails.get("IdProofNumber"));
					BookingCustomerDetailsMap.put("Address", BookingCustomerDetails.get("Address"));
					BookingCustomerDetailsMap.put("EmailId", BookingCustomerDetails.get("EmailId"));
					BookingCustomerDetailsMap.put("Title", BookingCustomerDetails.get("Title"));
					BookingCustomerDetailsMap.put("City", BookingCustomerDetails.get("City"));
					BookingCustomerDetailsMap.put("CountryId", BookingCustomerDetails.get("CountryId"));
					BookingCustomerDetailsMap.put("EmailId", BookingCustomerDetails.get("EmailId"));




					JSONObject CanceledPNRDetails=CanceledTicketDetails.getJSONObject("CanceledPNRDetails");

					String cancelPNR=CanceledPNRDetails.getString("TransportPNR");
					String CancellationPenalty=CanceledPNRDetails.getString("CancellationPenalty");
					String CancellationNumber=CanceledPNRDetails.getString("CancellationNumber");
					String CancelledDateTime=CanceledPNRDetails.getString("CancelledDateTime");
					String Origin=CanceledPNRDetails.getString("Origin");
					String TotalTickets=CanceledPNRDetails.getString("TotalTickets");
					String TransactionId=CanceledPNRDetails.getString("TransactionId");
					String BusName=CanceledPNRDetails.getString("BusName");
					String DepartureTime=CanceledPNRDetails.getString("DepartureTime");
					String TransportPNR=CanceledPNRDetails.getString("TransportPNR");


					JSONArray cancelPaxArray=CanceledPNRDetails.getJSONArray("CancelPaxList");
					if(cancelPaxArray!=null && cancelPaxArray.length()>0){
						for(int i=0;i<cancelPaxArray.length();i++){
							JSONObject cancelPaxJson=cancelPaxArray.getJSONObject(i);
							HashMap<String,Object> cancelPaxMap=new HashMap<>();
							cancelPaxMap.put("TicketNumber", cancelPaxJson.get("TicketNumber"));
							cancelPaxMap.put("TicketStatus","cancelled" /*cancelPaxJson.get("TicketStatus")*/);
							cancelPaxMap.put("SeatNo", cancelPaxJson.get("SeatNo"));
							cancelPaxMap.put("PassengerName", cancelPaxJson.get("PassengerName"));
							cancelPaxMap.put("Gender", cancelPaxJson.get("Gender"));
							cancelPaxMap.put("Age", cancelPaxJson.get("Age"));
							cancelPaxMap.put("Fare", cancelPaxJson.get("Fare"));

							cancelpaxList.add(cancelPaxMap);
						}
					}
					resMap.put("BookingCustomerDetails", CancelledDateTime);
					resMap.put("cancelPaxList", cancelpaxList);
					resMap.put("cancelPNR", cancelPNR);
					resMap.put("CancellationPenalty", CancellationPenalty);
					resMap.put("CancellationNumber", CancellationNumber);
					resMap.put("Origin", Origin);
					resMap.put("TotalTickets", TotalTickets);
					resMap.put("TransactionId", TransactionId);
					resMap.put("BusName", BusName);
					resMap.put("DepartureTime", DepartureTime);
					resMap.put("TransportPNR", TransportPNR);
					resMap.put("status", "0");
					resMap.put("Remarks", "success");
					resMap.put("BookingCustomerDetails", BookingCustomerDetailsMap);
					
				return	resMap;


				}else{
					resMap.put("status", "1");
					resMap.put("Remarks", responseJson.getString("FailureRemarks"));
					return	resMap;

				}

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return resMap;
	}	



	//.......................Get Booked History Request..........................//

	public JSONObject GetBookedHistoryRequest(String fromDate, String toDate, String recordsBy) {

		JSONObject responseJson = null;
		JSONObject historyJson=new JSONObject();
		try {

			JSONObject reJson=new JSONObject();


			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject bookedHistoryRequest = new JSONObject();

			bookedHistoryRequest.put("FromDate", fromDate);
			bookedHistoryRequest.put("ToDate", toDate);
			bookedHistoryRequest.put("RecordsBy", recordsBy);

			reJson.put("BookedHistoryInput",bookedHistoryRequest);

			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetBookedHistory", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"BookedHistoryInput :":{"ToDate":"09\/10\/2015","FromDate":"09\/10\/2015","RecordsBy":"B"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"FailureRemarks":"BookedHistoryInput Required","ResponseStatus":0}*/

			int status=responseJson.getInt("ResponseStatus");

			if(status==1)
			{
				JSONObject BookedHistoryOutput=responseJson.getJSONObject("BookedHistoryOutput");

				JSONArray bookedTicketsary=BookedHistoryOutput.getJSONArray("BookedTickets");
				ArrayList<HashMap<String, Object>>bookedTicketList=new ArrayList<>();
				if(bookedTicketsary!=null && bookedTicketsary.length()>0)
				{
					for (int i = 0; i < bookedTicketsary.length(); i++) {

						JSONObject bookedTicketsjsn=bookedTicketsary.getJSONObject(i);
						HashMap<String, Object>bookedTicketsMap=new HashMap<>();
						bookedTicketsMap.put("TransactionId", bookedTicketsjsn.get("TransactionId"));
						bookedTicketsMap.put("Amount", bookedTicketsjsn.get("Amount"));
						bookedTicketsMap.put("TicketStatus", bookedTicketsjsn.get("TicketStatus"));
						bookedTicketsMap.put("BookedDateTime", bookedTicketsjsn.get("BookedDateTime"));
						bookedTicketsMap.put("Origin", bookedTicketsjsn.get("Origin"));
						bookedTicketsMap.put("TotalTickets", bookedTicketsjsn.get("TotalTickets"));
						bookedTicketsMap.put("TransportId", bookedTicketsjsn.get("TransportId"));
						bookedTicketsMap.put("PenaltyAmount", bookedTicketsjsn.get("PenaltyAmount"));
						bookedTicketsMap.put("TravelDate", bookedTicketsjsn.get("TravelDate"));
						bookedTicketsMap.put("Destination", bookedTicketsjsn.get("Destination"));
						bookedTicketsMap.put("TransportName", bookedTicketsjsn.get("TransportName"));
						bookedTicketList.add(bookedTicketsMap);

					}

				}
				historyJson.put("BookedTickets", bookedTicketList);
				historyJson.put("ResponseStatus", status);
			}	

			else {
				historyJson.put("FailureRemarks",responseJson.get("FailureRemarks"));
				historyJson.put("ResponseStatus", status);

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return historyJson;
	}	

	//.......................Get Account Statement Request..........................//

	public JSONObject GetAccountStatementRequest(String fromDate, String toDate ) {
		JSONObject acountJson=new JSONObject();

		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject accountStatement = new JSONObject();

			accountStatement.put("FromDate", fromDate);
			accountStatement.put("ToDate", toDate);


			reJson.put("AccountStatementInput",accountStatement);

			System.out.println("Request : "+reJson);
			JSONObject responseJson=BusAPI.callBusApi("GetAccountStatement", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"AccountStatementInput :":{"ToDate":"09\/10\/2015","FromDate":"09\/10\/2015"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"FailureRemarks":"AccountStatementInput Required","ResponseStatus":0} */

			int status=responseJson.getInt("ResponseStatus");
			if(status==1)
			{
				JSONObject AccountStatementOutputJson=responseJson.getJSONObject("AccountStatementOutput");

				JSONArray Transactionsary=AccountStatementOutputJson.getJSONArray("Transactions");
				ArrayList<HashMap<String, Object>>TransactionsList=new ArrayList<>();
				if(Transactionsary!=null && Transactionsary.length()>0)
				{
					for (int i = 0; i < Transactionsary.length(); i++) {

						JSONObject Transactionsjsn=Transactionsary.getJSONObject(i);
						HashMap<String, Object>TransactionsMap=new HashMap<>();
						TransactionsMap.put("DateTime", Transactionsjsn.get("DateTime"));
						TransactionsMap.put("Description", Transactionsjsn.get("Description"));
						TransactionsMap.put("TransactionId", Transactionsjsn.get("TransactionId"));
						TransactionsMap.put("CreditAmount", Transactionsjsn.getDouble("CreditAmount"));
						TransactionsMap.put("DebitAmount", Transactionsjsn.getDouble("DebitAmount"));
						TransactionsMap.put("RemainingAmount", Transactionsjsn.get("RemainingAmount"));
						TransactionsMap.put("Remarks", Transactionsjsn.get("Remarks"));
						TransactionsMap.put("TerminalName", Transactionsjsn.get("TerminalName"));
						TransactionsMap.put("ProductDescription", Transactionsjsn.get("ProductDescription"));
						TransactionsList.add(TransactionsMap);

					}

				}
				acountJson.put("TotalRemainingAmount",AccountStatementOutputJson.get("TotalRemainingAmount"));
				acountJson.put("TravelAgentName",AccountStatementOutputJson.get("TravelAgentName"));
				acountJson.put("TravelAgentId",AccountStatementOutputJson.get("TravelAgentId"));
				acountJson.put("ResponseStatus",status);
				acountJson.put("TotalRemainingAmount",AccountStatementOutputJson.get("TotalRemainingAmount"));

				acountJson.put("BookedTickets", TransactionsList);
			}
			else {

				/*	{"FailureRemarks":"Error occurred on  procToGetAccStmt Cannot find table 0.","ResponseStatus":0}*/

				acountJson.put("FailureRemarks",responseJson.get("FailureRemarks"));
				acountJson.put("ResponseStatus", status);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acountJson;

	}}


//.......................Get Agent Credit Balance Request..........................//

/*public JSONObject GetAgentCreditBalanceRequest() {

		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);


			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetAgentCreditBalance", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"AgentCreditBalanceOutput":{"RemainingAmount":"-890061.16"},"ResponseStatus":1}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJson;
	}


 */


