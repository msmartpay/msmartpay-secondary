package com.msmart.travel.bus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.msmart.travel.bus.dao.BusBookingDao;


public class BusAPI{

	private final static String BASE_URL="http://api.hermes-it.in/BUS_V1/Bus.svc/JSONService/";
	private final static String LOGIN_ID="Softsolution";
	private final static String PASSWORD="apiSoftsolution";

	/*private final static String BASE_URL="http://115.248.39.80/hermesBusV1/Bus.svc/JSONService/";
	private final static String LOGIN_ID="hermes";
	private final static String PASSWORD="hermes123";*/
	private JSONObject responseJson=null;

	public static void main(String[] args) {

		try {
			//URL url = new URL("http://api.hermes-it.in/BUS_V1/Bus.svc/JSONService/GetOrigin");

			URL url = new URL("http://http://115.248.39.80/hermesBusV1/Bus.svc/JSONService/GetOrigin");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			String input = "{ \"Authentication\": { \"LoginId\":\"hermes\",\"Password\": \"hermes123\" }}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
			conn.disconnect();

			//new BusAPI().getSource("hermes","hermes123");
			//new BusAPI().getDestination();

			/*DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");		
			java.util.Date today = Calendar.getInstance().getTime();        			
			String reportDate = df.format(today);			
			System.out.println("Report Date: " + reportDate);*/

			//new BusAPI().busSearch("170"," 71", "05/30/2017");

			/*new BusAPI().GetSeatMapRequest("348146","2254","24","06/30/2017","RMNZZ9779999997194798289985398083564626");//
			 */
			//new BusAPI().GetSeatBlockRequest("RMNZZ9779999997194798288449174770552923","Mr.","Test","Guindy","9944100866","Chennai","91","velmurugan@hermes-it.in","","",1,475,24,"348146","2254","05/31/2017","Test","M",36,"25",22,475,"221","41");

			/*new BusAPI().GetBookingRequest("hermes","hermes123","RMNZZ97799999989907984974966182","Mr","Test","Guindy","9944100866","Chennai","91","velmurugan@hermes-it.in","","",1,475,24,"348146","2254","06/30/20117","Test","M",36,"20",22,475,"5057","5059");*/

			//new BusAPI().GetTransactionStatusRequest();

			//new BusAPI().GetReprintRequest();

			//new BusAPI().GetCancellationPolicyRequest(24);

			//new BusAPI().GetCancellationPenaltyRequest("EB7NQDU");

			//new BusAPI().GetCancellationRequest("EB7NQDU", 1, "47.50", "EB7NQDU0,", 24);

			//new BusAPI().GetBookedHistoryRequest("09/10/2015", "09/10/2015", "B");

			//new BusAPI().GetAccountStatementRequest("09/10/2015", "09/10/2015");

			//new BusAPI().GetAgentCreditBalanceRequest();

		}catch (Exception e) {
			e.printStackTrace();
		}

	}

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
			System.out.println("Response : "+responseJson);
			//Response : {"OriginOutput":{"OriginCities":[{"OriginId":71,"OriginName":"Bangalore"},{"OriginId":170,"OriginName":"Chennai"}]},"ResponseStatus":1}




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
			System.out.println("ResponceList"+OriginCitiesList.toString());
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
			System.out.println("Response : "+responseJson);
			//Response : {"DestinationOutput":{"DestinationCities":[{"DestinationId":100,"DestinationName":"Alleppey"},{"DestinationId":331,"DestinationName":"Angamaly"},{"DestinationId":71,"DestinationName":"Bangalore"},{"DestinationId":272,"DestinationName":"Batlagundu"},{"DestinationId":641,"DestinationName":"Chalakkudi"},{"DestinationId":561,"DestinationName":"Cherthala"},{"DestinationId":261,"DestinationName":"Chinnamanur"},{"DestinationId":173,"DestinationName":"Coimbatore"},{"DestinationId":253,"DestinationName":"Ernaakulam"},{"DestinationId":310,"DestinationName":"Kadaiyanallur"},{"DestinationId":248,"DestinationName":"Kaliyakavilai"},{"DestinationId":611,"DestinationName":"Karunkal"},{"DestinationId":562,"DestinationName":"Kayamkulam"},{"DestinationId":185,"DestinationName":"Kovilpatti"},{"DestinationId":368,"DestinationName":"Krishnan Kovil"},{"DestinationId":616,"DestinationName":"Kulasekaram"},{"DestinationId":187,"DestinationName":"Madurai"},{"DestinationId":240,"DestinationName":"Marthandam"},{"DestinationId":619,"DestinationName":"MondayMarket"},{"DestinationId":621,"DestinationName":"Nagercoil"},{"DestinationId":110,"DestinationName":"Palakkad"},{"DestinationId":296,"DestinationName":"Paramankuruchi"},{"DestinationId":390,"DestinationName":"Pavoorchatram"},{"DestinationId":258,"DestinationName":"Periyakulam"},{"DestinationId":309,"DestinationName":"Puliyankudi"},{"DestinationId":196,"DestinationName":"Rajapalayam"},{"DestinationId":198,"DestinationName":"Salem"},{"DestinationId":295,"DestinationName":"Sattur"},{"DestinationId":308,"DestinationName":"Srivilliputhur"},{"DestinationId":628,"DestinationName":"Thakalai"},{"DestinationId":262,"DestinationName":"Theni"},{"DestinationId":280,"DestinationName":"Thiruchendur"},{"DestinationId":661,"DestinationName":"Thirumangalam"},{"DestinationId":201,"DestinationName":"Tirunelveli"},{"DestinationId":204,"DestinationName":"Trichy"},{"DestinationId":114,"DestinationName":"Trivandrum"},{"DestinationId":205,"DestinationName":"Tuticorin"},{"DestinationId":276,"DestinationName":"Udangudi"},{"DestinationId":273,"DestinationName":"Uthamapalayam"},{"DestinationId":297,"DestinationName":"Valliyur"},{"DestinationId":369,"DestinationName":"Vasudeva Nallur"}]},"ResponseStatus":1}

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

	public HashMap<String,Object> busSearch(String originId, String destinationId, String TravelDate){
		ArrayList<HashMap<String, Object>> faresList=null;
		ArrayList<HashMap<String, Object>> avbusList=null;
		ArrayList<HashMap<String, Object>> boardingPointsList=null;

		HashMap<String,Object> hashMap=null;
		HashMap<String,Object> availableBusDetails=null;
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
			System.out.println("Response : "+responseJson);
			//Response : {"DestinationOutput":{"DestinationCities":[{"DestinationId":100,"DestinationName":"Alleppey"},{"DestinationId":331,"DestinationName":"Angamaly"},{"DestinationId":71,"DestinationName":"Bangalore"},{"DestinationId":272,"DestinationName":"Batlagundu"},{"DestinationId":641,"DestinationName":"Chalakkudi"},{"DestinationId":561,"DestinationName":"Cherthala"},{"DestinationId":261,"DestinationName":"Chinnamanur"},{"DestinationId":173,"DestinationName":"Coimbatore"},{"DestinationId":253,"DestinationName":"Ernaakulam"},{"DestinationId":310,"DestinationName":"Kadaiyanallur"},{"DestinationId":248,"DestinationName":"Kaliyakavilai"},{"DestinationId":611,"DestinationName":"Karunkal"},{"DestinationId":562,"DestinationName":"Kayamkulam"},{"DestinationId":185,"DestinationName":"Kovilpatti"},{"DestinationId":368,"DestinationName":"Krishnan Kovil"},{"DestinationId":616,"DestinationName":"Kulasekaram"},{"DestinationId":187,"DestinationName":"Madurai"},{"DestinationId":240,"DestinationName":"Marthandam"},{"DestinationId":619,"DestinationName":"MondayMarket"},{"DestinationId":621,"DestinationName":"Nagercoil"},{"DestinationId":110,"DestinationName":"Palakkad"},{"DestinationId":296,"DestinationName":"Paramankuruchi"},{"DestinationId":390,"DestinationName":"Pavoorchatram"},{"DestinationId":258,"DestinationName":"Periyakulam"},{"DestinationId":309,"DestinationName":"Puliyankudi"},{"DestinationId":196,"DestinationName":"Rajapalayam"},{"DestinationId":198,"DestinationName":"Salem"},{"DestinationId":295,"DestinationName":"Sattur"},{"DestinationId":308,"DestinationName":"Srivilliputhur"},{"DestinationId":628,"DestinationName":"Thakalai"},{"DestinationId":262,"DestinationName":"Theni"},{"DestinationId":280,"DestinationName":"Thiruchendur"},{"DestinationId":661,"DestinationName":"Thirumangalam"},{"DestinationId":201,"DestinationName":"Tirunelveli"},{"DestinationId":204,"DestinationName":"Trichy"},{"DestinationId":114,"DestinationName":"Trivandrum"},{"DestinationId":205,"DestinationName":"Tuticorin"},{"DestinationId":276,"DestinationName":"Udangudi"},{"DestinationId":273,"DestinationName":"Uthamapalayam"},{"DestinationId":297,"DestinationName":"Valliyur"},{"DestinationId":369,"DestinationName":"Vasudeva Nallur"}]},"ResponseStatus":1}



			int status=responseJson.getInt("ResponseStatus");
			if(status==1){
				availableBusDetails=new HashMap<>();
				avbusList=new ArrayList<HashMap<String, Object>>();
				availableBusDetails.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject searchOutput=responseJson.getJSONObject("SearchOutput") ;

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
						JSONArray boardingPointsAry= avbusjsn.optJSONArray("BoardingPoints");						
						if(boardingPointsAry!=null && boardingPointsAry.length()>0)
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
						JSONArray dropingPointsAry= avbusjsn.optJSONArray("DroppingPoints");
						if(dropingPointsAry!=null && dropingPointsAry.length()>0)
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


				availableBusDetails.put("BusDetails", avbusList);


			}else{
				return null;
			}

		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return availableBusDetails;
	}



	//........................Get Seat Map Request ..........................//
	//
	public  ArrayList<HashMap<String,Object>> GetSeatMapRequest(String ScheduleId, String StationId, String TransportId, String TravelDate, String UserTrackId,HttpSession session) {
		ArrayList<HashMap<String, Object>> seatLayoutList=null;
		ArrayList<HashMap<String, Object>> avaiSeatsList=null;
		ArrayList<HashMap<String, Object>> bookedSeatsList=null;;	
		ArrayList<HashMap<String, Object>> seatList=null;	
		ArrayList<HashMap<String, Object>> upperSeatList=null;	
		ArrayList<HashMap<String, Object>> seatcolList=null;	
		ArrayList<HashMap<String, Object>> mainList=null;
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
			responseJson=BusAPI.callBusApi("GetSeatMap", reJson);
			System.out.println("ResponceList"+responseJson.toString());

			/*Request : {"SeatMapInput":{"ScheduleId":"348146","StationId":"2254","TransportId":24,"TravelDate":"10\/17\/2015"},"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 
			Response : {"ResponseStatus":1,"UserTrackId":"RMNZZ97799999989907984974966182","SeatMapOutput":{"BusNumber":"","SeatLayoutDetails":{"SeatLayout":{"SeatColumns":[{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"2","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"1","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,null,null]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"3","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"4","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"5","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"6","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"10","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"9","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"8","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"7","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"11","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"12","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"13","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"14","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"18","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"17","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"16","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"15","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"19","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"20","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"21","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"22","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"26","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"25","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"24","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"23","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]},{"Seats":[{"Fare":"475.00","BerthType":"","SeatNo":"27","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"28","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},null,{"Fare":"475.00","BerthType":"","SeatNo":"29","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"},{"Fare":"475.00","BerthType":"","SeatNo":"30","SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":"0","SeatTypeId":22,"ServiceTax":"23.00"}]}]},"BookedSeats":null,"AvailableSeats":[{"SeatNo":"1"},{"SeatNo":"10"},{"SeatNo":"11"},{"SeatNo":"12"},{"SeatNo":"13"},{"SeatNo":"14"},{"SeatNo":"15"},{"SeatNo":"16"},{"SeatNo":"17"},{"SeatNo":"18"},{"SeatNo":"19"},{"SeatNo":"2"},{"SeatNo":"21"},{"SeatNo":"22"},{"SeatNo":"23"},{"SeatNo":"24"},{"SeatNo":"25"},{"SeatNo":"26"},{"SeatNo":"27"},{"SeatNo":"28"},{"SeatNo":"29"},{"SeatNo":"3"},{"SeatNo":"30"},{"SeatNo":"5"},{"SeatNo":"6"},{"SeatNo":"7"},{"SeatNo":"8"},{"SeatNo":"9"}],"LayoutFlag":1},"BoardingPoints":[{"Place":"Central","ContactNumber":"044 25380754","Time":"10:00 PM","Address":"2/1, E.V.R. Road","BoardingId":"5057","LandMark":"Opp.Central Rly Station"}],"Fares":[{"Fare":475,"SeatType":"2+2 Hi- tech Non AC Semi Sleeper","ConvenienceFee":0,"SeatTypeId":22,"ServiceTax":23}],"DroppingPoints":[{"DroppingId":"5059","Place":"Kalasipalaya","ContactNumber":"","Time":"05:00 AM","Address":"","LandMark":""}]}}
			 */

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				mainList=new ArrayList<HashMap<String,Object>>();
				HashMap<String, Object> mainMap=new HashMap<String, Object>();
				mainMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject seatMapOutput=new JSONObject();
				ArrayList<HashMap<String, Object>>SeatMapoutList=new ArrayList<HashMap<String,Object>>();


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

				seatLayoutList=new ArrayList<HashMap<String,Object>>();
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
				ArrayList<HashMap<String, Object>> SeatLayoutList=new ArrayList<HashMap<String,Object>>();
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
									System.out.println("BusAPI.GetSeatMapRequest() >>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+seatsjson.getString("SeatType"));
									hashMap.put("SeatType", seatsjson.getString("SeatType").equalsIgnoreCase("")?"Seater":seatsjson.getString("SeatType"));
									hashMap.put("Fare", seatsjson.getString("Fare"));
									hashMap.put("ServiceTax", seatsjson.getString("ServiceTax"));
									hashMap.put("ConvenienceFee", seatsjson.getString("ConvenienceFee"));

									totalSeats.add(hashMap);



								}else{
									hashMap=null;
								}

								if(seatsjson!=null && seatsjson.has("SeatNo")){
									if("U".equalsIgnoreCase(berthType)){
										upperSeatList.add(hashMap);
										seatList.add(null);
									}else{
										upperSeatList.add(null);
										seatList.add(hashMap);
									}
								}else{
										upperSeatList.add(hashMap);
										seatList.add(hashMap);
								}
								

							}
							
							if(upperSeatList!=null)
								System.out.println("upperSeatList >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+upperSeatList.size());
							
							if(seatList!=null)
								System.out.println("upperSeatList >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+seatList.size());
							
							
						}else{

						}
						SeatColumnsMap.put("Seats", seatList);
						SeatColumnsMap.put("UpperSeats", upperSeatList);
						seatcolList.add(SeatColumnsMap);
					}
				}
				
				System.out.println("______________________________________________________________________________");
				
				System.out.println("seatcolList :: "+seatcolList.toString());

				System.err.println("______________________________________________________________________________");
				//////////////////////////////////////////////////////////////


				avaiSeatsList=new ArrayList<HashMap<String,Object>>();
				JSONArray availableSeatsArray= seatLayoutDetailjson.getJSONArray("AvailableSeats");				
				if (availableSeatsArray.length()>0) {	

					for(int m=0;m<availableSeatsArray.length();m++){
						JSONObject availableSeatjson=availableSeatsArray.getJSONObject(m);
						hashMap=new HashMap<String, Object>();
						hashMap.put("SeatNo",availableSeatjson.getString("SeatNo"));
						avaiSeatsList.add(hashMap);

					}

				}


				SeatLayoutMap.put("SeatColumns",seatcolList);
				SeatLayoutList.add(SeatLayoutMap);
				seatLayouthasaMap.put("SeatLayout", SeatLayoutList);
				seatLayouthasaMap.put("uppserStatus", uppserStatus);
				seatLayoutList.add(seatLayouthasaMap);

				SeatMapOutMAp.put("BoardingPoints",bordingList );
				SeatMapOutMAp.put("DroppingPoints",seatMapOutput);
				SeatMapOutMAp.put("Fares",farList );
				SeatMapOutMAp.put("SeatLayoutDetails",seatLayoutList );

				SeatMapoutList.add(SeatMapOutMAp);
				mainMap.put("SeatMapOutput", SeatMapoutList);
				mainMap.put("AvailableSeats", avaiSeatsList);
				mainMap.put("BookedSeats", bookedSeatsList);
				mainList.add(mainMap);

				

				session.setAttribute("seatDetails", totalSeats);
				session.setAttribute("BoardingPoints", bordingList);
				session.setAttribute("DroppingPoints", dropingList);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return mainList;
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

	//.......................Get SeatBlock Request..........................//

	public  HashMap<String,Object> GetSeatBlockRequest(String userTrackId, String title, String name, String address, String contactNumber, String city, String countryId, String emailId, 
			String idProofId, String idProofNumber, int totalTickets,double totalAmount, int transportId,  String scheduleId, String stationId, String travelDate,JSONArray PassengerList) {		

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


			bookingDetails.put("PassengerList", PassengerList);

			BlockingInput.put("BookingDetails", bookingDetails);
			BlockingInput.put("BookingCustomerDetails", bookingCustomerDetails);

			reJson.put("BlockingInput",BlockingInput);	
			reJson.put("UserTrackId", userTrackId);


			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetSeatBlock", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"},"blocking input":{"bookingdetail":{"ScheduleId":"348146 ","StationId":"2254 ","PassengerListDetals":[{"PassengerName ":"Test","BoardingId ":"221","Gender ":"M","SeatNo ":"20","Age ":36,"DroppingId ":"41","SeatTypeId ":22,"Fare ":475}],"TotalAmount":475,"TransportId":24,"TotalTickets":1,"TravelDate":"10\/17\/2015 "},"customer details":{"Name":"Test","ContactNumber":"9944100866","IdProofId":" ","IdProofNumber":" ","Address":"Guindy","CountryId":"91","EmailId":"velmurugan@hermes-it.in","City":"Chennai","Title":"Mr."}}}
			Output from Server .... 

			Response : {"FailureRemarks":"GetSeatBlockInput Required","ResponseStatus":0,"UserTrackId":"RMNZZ97799999989907984974966182"}*/



			int status=responseJson.getInt("ResponseStatus");
			System.out.println("ResponceList"+status);
			if(status==1){
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("UserTrackId",responseJson.getString("UserTrackId"));

				JSONObject blockingOutput=responseJson.getJSONObject("BlockingOutput") ;
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("BlockingStatus",blockingOutput.getString("BlockingStatus"));
				blockingOutputMap.put("Remarks", blockingOutput.getString("Remarks"));
				blockingOutputMap.put("Status", "Success");

				System.out.println("ResponceList"+blockingOutputMap);
			}else{
				blockingOutputMap=new HashMap<String, Object>();
				blockingOutputMap.put("Status", "Failure");
				blockingOutputMap.put("Remarks", responseJson.getString("FailureRemarks"));
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return blockingOutputMap;
	}


	//.......................Get Booking Request..........................//

	public HashMap<String, Object> GetBookingRequest(String tranId,String userTrackId, String title, String name, String address, String contactNumber, String city, String countryId, String emailId, 
			String idProofId, String idProofNumber, int totalTickets,double totalAmount, int transportId,  String scheduleId, String stationId, String travelDate,JSONArray PassengerList) {		

		HashMap<String, Object>	ticketDetailsMap=new HashMap<String, Object>();
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

			bookingDetails.put("PassengerList", PassengerList);

			BlockingInput.put("BookingDetails", bookingDetails);
			BlockingInput.put("BookingCustomerDetails", bookingCustomerDetails);

			reJson.put("BookingInput",BlockingInput);	
			reJson.put("UserTrackId", userTrackId);


			System.out.println("Request : "+reJson);

			BusBookingDao bookingDao=new BusBookingDao();

			//Save Request log 
			bookingDao.saveRequestLog(reJson.toString(), tranId);
			//---------------------------------------------------------------

			responseJson=BusAPI.callBusApi("GetBook", reJson);

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

				ticketDetailsMap.put("ServiceTax:",ticketDetails.getString("ServiceTax"));
				ticketDetailsMap.put("Commission:", ticketDetails.getString("Commission"));
				ticketDetailsMap.put("CancellationPolicy:", ticketDetails.getString("CancellationPolicy"));

				ticketDetailsMap.put("UserTrackId", responseJson.getString("UserTrackId"));
				ticketDetailsMap.put("ResponseStatus", status);


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
				ticketDetailsMap.put("ResponseStatus", status);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticketDetailsMap;
	}

	//........................Get Transaction Status Request ..........................//

	/*public JSONObject GetTransactionStatusRequest() {

	try {

		JSONObject reJson=new JSONObject();

		JSONObject obj=new JSONObject();
		obj.put("LoginId", BusAPI.LOGIN_ID);
		obj.put("Password", BusAPI.PASSWORD);

		reJson.put("Authentication",obj);

		reJson.put("UserTrackId","RMNZZ97799999989907984974966182");

		System.out.println("Request : "+reJson);
		responseJson=BusAPI.callBusApi("GetTransactionStatus", reJson);

		System.out.println("Response : "+responseJson);

		/*Request : {"UserTrackId":"RMNZZ97799999989907984974966182","Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"TransactionStatusOutput":{"TransactionStatus":2,"Remarks":"STATUS- UNKNOWN. PLEASE TRY TILL YOU RECEIVE THE  STATUS  RESPONSE CODE 1 OR 3. ","TicketDetails":{"Commission":null,"CancellationPolicy":null,"ConvenienceFee":null,"BookingCustomerDetails":null,"PNRDetails":null,"ServiceTax":null}},"ResponseStatus":1,"UserTrackId":"RMNZZ97799999989907984974966182"}
	 */


	/*int ResponseStatus=responseJson.getInt("ResponseStatus");
		if(ResponseStatus==1){

			JSONObject mainObject2=new JSONObject();	
			JSONObject transactionStatusOutput=mainObject2.getJSONObject("TransactionStatusOutput") ;
			transactionStatusOutput.put("TransactionStatus", mainObject2.getString("TransactionStatus"));


			JSONObject blockingOutput=responseJson.getJSONObject("BlockingOutput");
			HashMap<String, Object>	blockingOutputMap=new HashMap<String, Object>();

			JSONObject ticketDetails=blockingOutput.getJSONObject("TicketDetails") ;
			HashMap<String, Object>	ticketDetailsMap=new HashMap<String, Object>();
			ticketDetailsMap.put("ServiceTax:",ticketDetails.getString("ServiceTax"));
			ticketDetailsMap.put("Commission:", ticketDetails.getString("Commission"));
			ticketDetailsMap.put("CancellationPolicy:", ticketDetails.getString("CancellationPolicy"));


			JSONObject bookingCustomerDetails=ticketDetails.getJSONObject("BookingCustomerDetails") ;					
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
			ticketDetails.put("bookingCustomerDetails", bookingCustomerDetails);

			JSONObject pnrDetails=ticketDetails.getJSONObject("PNRDetails") ;
			pnrDetails.put("TransactionId",pnrDetails.getString("TransactionId"));
			pnrDetails.put("TransportPNR", pnrDetails.getString("TransportPNR"));
			pnrDetails.put("TotalAmount", pnrDetails.getString("TotalAmount"));
			pnrDetails.put("TotalTickets", pnrDetails.getString("TotalTickets"));
			pnrDetails.put("BusName", pnrDetails.getString("BusName"));
			pnrDetails.put("Origin", pnrDetails.getString("Origin"));
			pnrDetails.put("Destination", pnrDetails.getString("Destination"));
			pnrDetails.put("TravelDate", pnrDetails.getString("TravelDate"));
			pnrDetails.put("DepartureTime", pnrDetails.getString("DepartureTime"));

			JSONArray paxListArray= pnrDetails.getJSONArray("PaxList");
			JSONObject paxjson=new JSONObject() ;
			paxjson.put("TicketNumber",paxjson.getString("TicketNumber"));
			paxjson.put("SeatNo", paxjson.getString("SeatNo"));
			paxjson.put("SeatType", paxjson.getString("SeatType"));
			paxjson.put("PassengerName", paxjson.getString("PassengerName"));
			paxjson.put("Gender", paxjson.getString("Gender"));
			paxjson.put("Age", paxjson.getString("Age"));
			paxjson.put("Fare", paxjson.getString("Fare"));


			JSONObject boardingPoints=paxjson.getJSONObject("BoardingPoints") ;
			boardingPoints.put("BoardingId", boardingPoints.getString("BoardingId"));
			boardingPoints.put("Place", boardingPoints.getString("Place"));
			boardingPoints.put("Time", boardingPoints.getString("Time"));
			boardingPoints.put("Address", boardingPoints.getString("Address"));
			boardingPoints.put("LandMark", boardingPoints.getString("LandMark"));
			boardingPoints.put("ContactNumber", boardingPoints.getString("ContactNumber"));	
			paxjson.put("boardingPoints", boardingPoints);


			JSONObject droppingPoints=paxjson.getJSONObject("DroppingPoints") ;
			droppingPoints.put("DroppingId", droppingPoints.getString("DroppingId"));
			droppingPoints.put("Place", droppingPoints.getString("Place"));
			droppingPoints.put("Time", droppingPoints.getString("Time"));
			droppingPoints.put("Address", droppingPoints.getString("Address"));
			droppingPoints.put("LandMark", droppingPoints.getString("LandMark"));
			droppingPoints.put("ContactNumber", droppingPoints.getString("ContactNumber"));				
			paxjson.put("droppingPoints", droppingPoints);

			paxjson.put("ReportingTime", paxjson.getString("ReportingTime"));
			paxjson.put("ServiceCharges", paxjson.getString("ServiceCharges"));		

			paxListArray.put(paxjson);
			pnrDetails.put("paxListArray" , paxListArray);

			JSONObject transportDetails=pnrDetails.getJSONObject("TransportDetails") ;
			transportDetails.put("TransportName", transportDetails.getString("TransportName"));
			transportDetails.put("Address1", transportDetails.getString("Address1"));
			transportDetails.put("Address2", transportDetails.getString("Address2"));
			transportDetails.put("Address3", transportDetails.getString("Address3"));
			transportDetails.put("CityNamePinCode", transportDetails.getString("CityNamePinCode"));
			transportDetails.put("ContactNumber", transportDetails.getString("ContactNumber"));	
			transportDetails.put("Fax", transportDetails.getString("Fax"));
			transportDetails.put("Website", transportDetails.getString("Website"));
			transportDetails.put("EmailId", transportDetails.getString("EmailId"));		
			pnrDetails.put("transportDetails", transportDetails);

			ticketDetails.put("pnrDetails", pnrDetails);
			transactionStatusOutput.put("Remarks", transactionStatusOutput.getString("Remarks"));

			bookingOutput.put("ticketDetails", ticketDetails);
			transactionStatusOutput.put("bookingOutput", bookingOutput);
			mainObject2.put("transactionStatusOutput", transactionStatusOutput);
		}

		System.out.println("Responce"+responseJson.toString());
	} catch (Exception e) {
		e.printStackTrace();
	}

	return responseJson;
}

//........................Get Reprint Request ..........................//

	/*public JSONObject GetReprintRequest() {

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

		Request : {"ReprintInput":{"TransactionId":"EB7NQDU"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"ResponseStatus":1,"UserTrackId":"RMNZZ97799999989907984974966182","ReprintOutput":{"ReprintTicketDetails":{"Commission":"0.00","ReprintPNRDetails":{"TransactionId":"EB7NQDU","BusName":"Chennai-Bangalore 10:00 PM 1 SEATER","FareBreakUpDetails":null,"DepartureTime":"10:00 PM","TransportPNR":"EB7NQDU","TransportDetails":{"ContactNumber":"080-26702426","Address3":"Near Bangalore Medical College, Kalasipalayam","Website":"www.sharmatransports.com","Address2":"328, Tippu Sultan Palace Road","Address1":"H.O.: Sangeetha Bhawan","Fax":"","EmailId":"sharma_transports@yahoo.co.in","CityNamePinCode":"Bangalore-560002","TransportName":"Sharma Transports"},"TotalAmount":475,"Origin":"Chennai","TotalTickets":1,"ReprintPaxList":[{"Fare":475,"PassengerName":"Test","ServiceCharges":"0.00","Age":35,"SeatNo":"4","SeatType":"2","TicketStatus":"","Gender":"M","BoardingPoints":{"Place":"Central","ContactNumber":"044 25380754","Time":"21:30:00","Address":"2\/1, E.V.R. Road","BoardingId":null,"LandMark":"Opp.Central Rly Station"},"ReportingTime":"21:30:00","DroppingPoints":{"DroppingId":null,"Place":"Kalasipalaya","ContactNumber":"080 26701028","Time":"05:00 AM","Address":"Tippu Sulthan Road","LandMark":"Near Bangalore Medical College"},"TicketNumber":"EB7NQDU0"}],"TravelDate":"17\/10\/2015","Destination":"Bangalore"},"CancellationPolicy":"","ConvenienceFee":null,"BookingCustomerDetails":{"Name":"test","ContactNumber":"9944100866","BookedDate":"10\/09\/2015  2:55PM","IdProofId":"0","IdProofNumber":"","Address":"guindy","CountryId":null,"EmailId":"velmurugangce@gmail.com","City":null,"Title":null},"ServiceTax":"0"}}}


		String ResponseStatus=responseJson.getString("ResponseStatus");
		if("1".equalsIgnoreCase(ResponseStatus)){

			JSONObject mainObject2=new JSONObject();	
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

	return responseJson;
}*/


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
			responseJson=BusAPI.callBusApi("GetCancellationPolicy", reJson);

			System.out.println("Response : "+responseJson);

			int status=responseJson.getInt("ResponseStatus");
			if(status==1){

				responseMap.put("Status", "Success");

				JSONObject CancellationPolicyOutput=responseJson.getJSONObject("CancellationPolicyOutput");

				responseMap.put("CancelPolicyDetails", CancellationPolicyOutput.getString("CancelPolicyDetails"));
			}else{
				responseMap.put("Status", "Failure");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseMap;
	}

	//........................Get Cancellation Penalty Request ..........................//

	public HashMap<String,Object> GetCancellationPenaltyRequest(String transactionId) {


		HashMap<String,Object> PNRDetails=new HashMap<>();
		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject cancellationPenalty = new JSONObject();

			cancellationPenalty.put("TransactionId", transactionId);
			reJson.put("CancellationPenaltyInput",cancellationPenalty);

			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetCancellationPenalty", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"CancellationPenaltyInput":{"TransactionId":"EB7NQDU"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"FailureRemarks":"Ticket already cancelled","ResponseStatus":0}*/

			ArrayList<HashMap<String, Object>> paxList=new ArrayList<>();
			if(responseJson!=null){

				int status=responseJson.getInt("ResponseStatus");
				if(status==1){
					PNRDetails.put("Status", "Success");

					JSONObject CancellationPenaltyOutput=responseJson.getJSONObject("CancellationPenaltyOutput");
					JSONObject ToCancelPNRDetails=CancellationPenaltyOutput.getJSONObject("ToCancelPNRDetails");

					PNRDetails.put("PNRNumber", transactionId);
					PNRDetails.put("TransportName", ToCancelPNRDetails.getString("TransportName"));
					PNRDetails.put("Origin", ToCancelPNRDetails.getString("Origin"));
					PNRDetails.put("Destination", ToCancelPNRDetails.getString("Destination"));
					PNRDetails.put("BookedDate", ToCancelPNRDetails.getString("BookedDate"));
					PNRDetails.put("TravelDate", ToCancelPNRDetails.getString("TravelDate"));
					PNRDetails.put("DepatureTime", ToCancelPNRDetails.getString("DepatureTime"));
					PNRDetails.put("PNRStatus", ToCancelPNRDetails.getString("PNRStatus"));

					JSONArray paxListArray=ToCancelPNRDetails.getJSONArray("ToCancelPaxList");
					if(paxListArray!=null){
						for(int i=0;i<paxListArray.length();i++){
							JSONObject paxObj=paxListArray.getJSONObject(i);
							HashMap<String,Object> paxMap=new HashMap<>();
							paxMap.put("TicketNumber", paxObj.getString("TicketNumber"));
							paxMap.put("PassengerName", paxObj.getString("PassengerName"));
							paxMap.put("Gender", paxObj.getString("Gender"));
							paxMap.put("Age", paxObj.getString("Age"));
							paxMap.put("SeatNo", paxObj.getString("SeatNo"));
							paxMap.put("Fare", paxObj.getString("Fare"));
							paxMap.put("PenatlyAmount", paxObj.getString("PenatlyAmount"));
							paxList.add(paxMap);
						}
					}

					PNRDetails.put("PNRDetails", PNRDetails);
					PNRDetails.put("CancelPaxList", paxList);

				}else{
					PNRDetails.put("Status", "Failure");

					PNRDetails.put("FailureRemarks",responseJson.getString("FailureRemarks"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return PNRDetails;
	}	


	//.......................Get Cancellation Request..........................//

	public HashMap<String, Object> GetCancellationRequest(String transactionId, int totalticketsToCancel, String penaltyAmount, String ticketNumbers,String transportId) {

		HashMap<String, Object> resMap=new HashMap<String, Object>();
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


			BusBookingDao bookingDao=new BusBookingDao();
			//Save Request log 
			bookingDao.saveRequestLog(reJson.toString(), transactionId);
			//---------------------------------------------------------------

			responseJson=BusAPI.callBusApi("GetCancellation", reJson);

			//Update Response log
			bookingDao.updateResponseLog(responseJson.toString(), transactionId);

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
					BookingCustomerDetailsMap.put("Address", BookingCustomerDetails.get("Address"));
					BookingCustomerDetailsMap.put("EmailId", BookingCustomerDetails.get("EmailId"));
					BookingCustomerDetailsMap.put("BookedDate", BookingCustomerDetails.get("BookedDate"));


					JSONObject CanceledPNRDetails=CanceledTicketDetails.getJSONObject("CanceledPNRDetails");

					String cancelPNR=CanceledPNRDetails.getString("transportPNR");
					String CancellationPenalty=CanceledPNRDetails.getString("CancellationPenalty");
					String CancellationNumber=CanceledPNRDetails.getString("CancellationNumber");
					String CancelledDateTime=CanceledPNRDetails.getString("CancelledDateTime");

					JSONArray cancelPaxArray=CanceledPNRDetails.getJSONArray("CancelPaxList");
					if(cancelPaxArray!=null && cancelPaxArray.length()>0){
						for(int i=0;i<cancelPaxArray.length();i++){
							JSONObject cancelPaxJson=cancelPaxArray.getJSONObject(i);
							HashMap<String,Object> cancelPaxMap=new HashMap<>();
							cancelPaxMap.put("TicketNumber", cancelPaxJson.get("TicketNumber"));
							cancelPaxMap.put("TicketStatus", cancelPaxJson.get("TicketStatus"));
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
					resMap.put("CancelledDateTime", CancelledDateTime);


				}else{

				}

			}else{

			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		return resMap;
	}	


	//.......................Get Booked History Request..........................//

	public JSONObject GetBookedHistoryRequest(String fromDate, String toDate, String recordsBy) {

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

			reJson.put("BookedHistoryInput :",bookedHistoryRequest);

			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetBookedHistory", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"BookedHistoryInput :":{"ToDate":"09\/10\/2015","FromDate":"09\/10\/2015","RecordsBy":"B"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"FailureRemarks":"BookedHistoryInput Required","ResponseStatus":0}*/



		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJson;
	}	

	//.......................Get Account Statement Request..........................//

	public JSONObject GetAccountStatementRequest(String fromDate, String toDate ) {

		try {

			JSONObject reJson=new JSONObject();

			JSONObject obj=new JSONObject();
			obj.put("LoginId", BusAPI.LOGIN_ID);
			obj.put("Password", BusAPI.PASSWORD);

			reJson.put("Authentication",obj);

			JSONObject accountStatement = new JSONObject();

			accountStatement.put("FromDate", fromDate);
			accountStatement.put("ToDate", toDate);


			reJson.put("AccountStatementInput :",accountStatement);

			System.out.println("Request : "+reJson);
			responseJson=BusAPI.callBusApi("GetAccountStatement", reJson);

			System.out.println("Response : "+responseJson);

			/*Request : {"AccountStatementInput :":{"ToDate":"09\/10\/2015","FromDate":"09\/10\/2015"},"Authentication":{"LoginId":"hermes","Password":"hermes123"}}
			Output from Server .... 

			Response : {"FailureRemarks":"AccountStatementInput Required","ResponseStatus":0}*/


		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJson;
	}	

	//.......................Get Agent Credit Balance Request..........................//

	public JSONObject GetAgentCreditBalanceRequest() {

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

			Response : {"AgentCreditBalanceOutput":{"RemainingAmount":"-890061.16"},"ResponseStatus":1}*/


		} catch (Exception e) {
			e.printStackTrace();
		}

		return responseJson;
	}





}