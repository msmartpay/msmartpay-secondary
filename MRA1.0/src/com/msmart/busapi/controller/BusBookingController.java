package com.msmart.busapi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

import com.msmart.controller.LoginController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("BUS")
public class BusBookingController {


	Logger logger = Logger.getLogger(LoginController.class);



	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getSource")

	public JSONObject getSource(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			System.out.println("RequestController.getCity()");
			ArrayList<HashMap<String, String>> city=BusAPI.getSource();

			if(city!=null){
				response.put("City", city);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Trip Not Available");
			}

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Trip Not Available");
			return response;
		}

	}

	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getDestination")

	public JSONObject getDestination(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			String OriginId=jsonObject.getString("OriginId");
			System.out.println("RequestController.getDestination()");
			ArrayList<HashMap<String, String>> destination=BusAPI.getDestination(OriginId);

			if(destination!=null){
				response.put("City", destination);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Trip Not Available");
			}

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Trip Not Available");
			return response;
		}

	}

	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getBusList")

	public JSONObject getBusSearch(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			String OriginId=jsonObject.getString("OriginId");
			String DestinationId=jsonObject.getString("DestinationId");
			String TravelDate=jsonObject.getString("TravelDate");

			System.out.println("RequestController.getDestination()");
			BusAPI dao=new BusAPI();
			ArrayList<HashMap<String, Object>> busList=dao.busSearch(OriginId,DestinationId,TravelDate);

			if(busList!=null){
				response.put("City", busList);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Trip Not Available");
			}
			System.out.println("RequestController.getCity() "+response);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Trip Not Available");
			return response;
		}

	}


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetSeatMapRequest")

	public JSONObject getSeatMapRequest(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			String ScheduleId=jsonObject.getString("ScheduleId");
			String StationId=jsonObject.getString("StationId");
			String TravelDate=jsonObject.getString("TravelDate");
			String TransportId=jsonObject.getString("TransportId");
			String UserTrackId=jsonObject.getString("UserTrackId");

			System.out.println("RequestController.getSeatMapRequest()");
			BusAPI dao=new BusAPI();
			HashMap<String, Object> seatMaprequest=dao.GetSeatMapRequest( ScheduleId,  StationId,  TransportId,  TravelDate,  UserTrackId);

			if(seatMaprequest!=null){
				response.put("SeatMapDetails", seatMaprequest);
				response.put("status", "0");
				response.put("message", "Success");

				return response;
			}else{
				response.put("status", "1");
				response.put("message", "Trip Not Available");
			}
			System.out.println("RequestController.getCity() "+response);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Trip Not Available");
			return response;
		}


	}



	// ---------------------------Get SeatBlock Request-------------------------------//

	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getSeatBlockRequest")

	public HashMap<String, Object> GetSeatBlockRequest(JSONObject jsonObject)	
	{
		HashMap<String, Object> hasmap=null;
		try {

			String customerTitle="", customerName="";

			String userTrackId=jsonObject.getString("UserTrackId");
			customerName=jsonObject.getString("Name");
			String address=jsonObject.getString("Address");
			String agentID=jsonObject.getString("agentID");
			String contactNumber=jsonObject.getString("ContactNumber");
			String city=jsonObject.getString("City");
			String countryId=jsonObject.getString("CountryId");
			String emailId=jsonObject.getString("EmailId");
			String idProofId=jsonObject.getString("IdProofType");
			String idProofNumber=jsonObject.getString("IdProofNumber");

			String BusType=jsonObject.getString("BusType");
			String BusName=jsonObject.getString("BusName");
			String TransportName=jsonObject.getString("TransportName");
			int transportId=Integer.parseInt(jsonObject.getString("TransportId"));
			String scheduleId=jsonObject.getString("ScheduleId");
			String stationId=jsonObject.getString("StationId");
			String travelDate=jsonObject.getString("TravelDate");
			String ArrivalTime=jsonObject.getString("ArrivalTime");
			String departureTime=jsonObject.getString("DepartureTime");
		/*	String place=jsonObject.getString("place");*/
			

			String originName=jsonObject.getString("OriginName");
			String destinationName=jsonObject.getString("DestinationName");

			double totalAmount=0,grossTotal=0;
			double totalServiceTax=Double.parseDouble(jsonObject.getString("TotalServiceTax"));
			double totalConvenienceFee=Double.parseDouble(jsonObject.getString("TotalConvenienceFee"));

			JSONArray PassengerList=jsonObject.getJSONArray("PassengerList");

			int totalTickets=jsonObject.getInt("TotalTickets");




			grossTotal=totalAmount+totalServiceTax+totalConvenienceFee;

			double avalBal=jsonObject.getDouble("avalBal");

			if(avalBal>=grossTotal){
				BusAPI apiCall=new BusAPI();

				// Block Bus seat
				hasmap =apiCall.GetSeatBlockRequest(userTrackId,customerTitle,customerName,address,contactNumber,city,countryId,emailId,idProofId,idProofNumber,totalTickets,totalAmount,transportId,scheduleId,stationId, travelDate,PassengerList);
				if (hasmap!=null && hasmap.size()>0) {

					String TransactionID=RandomStringUtils.randomNumeric(20);
					String tranId=RandomStringUtils.randomNumeric(14);

					BusBookingDao bookingDao=new BusBookingDao();
					
					//---------------------------------------------------------------

					
					//Save Booking Details
					String saveStatus=bookingDao.saveBooking(TransactionID, tranId, Long.parseLong(agentID), userTrackId, PassengerList, customerTitle, customerName, address, contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets, grossTotal, travelDate, BusType, BusName, TransportName+"$"+transportId, ArrivalTime, departureTime,"",originName, destinationName,transportId);

					return hasmap;
				}
				else{
					hasmap.put("Remarks", "Trip Not AvailableRemarks");
					hasmap.put("status", "1");
				}
				
			}
			else{
				hasmap=new HashMap<>();
				hasmap.put("Remarks", "insufficient balance in your wallet");
				hasmap.put("status", "1");
			}
		}catch (Exception e) {
			hasmap=new HashMap<>();
			hasmap.put("Remarks", "Trip Not AvailableRemarks");
			hasmap.put("status", "1");
			return hasmap;
		}
		return hasmap;



	}


	// ---------------------------Get Booking Request-------------------------------//


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getBookingRequest")

	public JSONObject GetBookingRequest(JSONObject jsonObject)	
	{
		JSONObject hasmap=null;
		String customerTitle="", customerName="";
		try{

			String newUserTrackId=jsonObject.getString("UserTrackId");
			String name=jsonObject.getString("Name");
			String address=jsonObject.getString("Address");
			String contactNumber=jsonObject.getString("ContactNumber");
			String city=jsonObject.getString("City");
			String countryId=jsonObject.getString("CountryId");
			String emailId=jsonObject.getString("EmailId");
			String idProofId=jsonObject.getString("IdProofType");
			String idProofNumber=jsonObject.getString("IdProofNumber");

			String BusType=jsonObject.getString("BusType");
			String BusName=jsonObject.getString("BusName");
			String TransportName=jsonObject.getString("TransportName");
			int transportId=jsonObject.getInt("TransportId");
			String scheduleId=jsonObject.getString("ScheduleId");
			String stationId=jsonObject.getString("StationId");
			String travelDate=jsonObject.getString("TravelDate");
			String agentID=jsonObject.getString("agentID");
			String originName=jsonObject.getString("OriginName");
			String destinationName=jsonObject.getString("DestinationName");
			String departureTime=jsonObject.getString("DepartureTime");
			String ArrivalTime=jsonObject.getString("ArrivalTime");
			String title=jsonObject.getString("Title");
			String Ipaddress="";
			int TotalAmount=jsonObject.getInt("TotalAmount");



			double totalServiceTax=Double.parseDouble(jsonObject.getString("TotalServiceTax"));
			double totalConvenienceFee=Double.parseDouble(jsonObject.getString("TotalConvenienceFee"));



			int totalTickets=jsonObject.getInt("TotalTickets");


			double avalBal=jsonObject.getDouble("avalBal");
			
			System.out.println("avalBal"+avalBal);


			BusAPI apiCall=new BusAPI();

			String TransactionID=RandomStringUtils.randomNumeric(20);
			String tranId=RandomStringUtils.randomNumeric(14);

			BusBookingDao bookingDao=new BusBookingDao();

			//---------------------------------------------------------------


			JSONArray PassengerarryList=new JSONArray();
			JSONArray PassengerList=jsonObject.getJSONArray("PassengerList");
			if(PassengerList!=null && PassengerList.size()>0)
			{
				for (int i = 0; i < PassengerList.size(); i++) {



					JSONObject  object=PassengerList.getJSONObject(i);
					JSONObject hashMap=new  JSONObject();
					hashMap.put("PassengerName",(String) object.get("PassengerName") );
					hashMap.put("Gender",(String) object.get("Gender") );
					hashMap.put("Age",(String) object.get("Age") );
					hashMap.put("SeatNo",(String) object.get("SeatNo") );
					hashMap.put("Fare",(String) object.get("Fare") );
					hashMap.put("BoardingId",(String) object.get("BoardingId") );
					hashMap.put("DroppingId",(String) object.get("DroppingId") );
					hashMap.put("Time",(String) object.get("SeatNo") );
					hashMap.put("Address",(String) object.get("Fare") );
					hashMap.put("LandMark",(String) object.get("BoardingId") );
					hashMap.put("ContactNumber",(String) object.get("DroppingId") );
					hashMap.put("SeatTypeId", (String)object.get("SeatTypeId"));
					hashMap.put("place", "");

					PassengerarryList.add(hashMap);



				}

				
			//Save Booking Details
				String saveStatus=bookingDao.saveBooking(TransactionID, TransactionID, Long.parseLong(agentID), newUserTrackId, PassengerarryList, customerTitle, customerName, address, contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets, TotalAmount, travelDate, BusType, BusName, TransportName, ArrivalTime, departureTime, Ipaddress, originName, destinationName,transportId);

					//****************************************** Booking Request ****************************************** //

					System.out.println("saveStatus :: "+saveStatus);
				
				// Block Bus seat
				hasmap =apiCall.GetBookingRequest(tranId, newUserTrackId, title, name, address, contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets, TotalAmount, transportId, scheduleId, stationId, travelDate, PassengerList);
				{


					return hasmap;
				}

			}

		}catch (Exception e) {
			e.printStackTrace();
		}
		return hasmap;

	}



	// ---------------------------.GetCancellationPenalty-------------------------------//


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetCancellationPenalty")

	public JSONObject GetCancellationPenalty(JSONObject jsonObject)
	{
		JSONObject object=new JSONObject();
		String TransactionId=jsonObject.getString("TransactionId");
		try{
			BusAPI api=new BusAPI();
			JSONObject response=api.GetCancellationPenaltyRequest(TransactionId);
			if (response!=null) {

				object.put("CancellationPenaltyOutput", response.toString());
				object.put("status", "0");
				object.put("Remarks", "succuss");

				System.out.println("Sundaram Response"+object);

				return object;
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;


	}



	//---------------------------.GetCancellationRequest-------------------------------//


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetCancellationRequest")

	public JSONObject GetCancellationRequest(JSONObject jsonObject)
	{
		JSONObject object=new JSONObject();
		String transactionId=jsonObject.getString("TransactionId");
		int totalticketsToCancel=jsonObject.getInt("TotalTicketsToCancel");
		double penaltyAmount=jsonObject.getDouble("PenaltyAmount");
		String ticketNumbers=jsonObject.getString("TicketNumbers");
		int transportId=jsonObject.getInt("TransportId");


		try{
			BusAPI api=new BusAPI();
			JSONObject response=api.GetCancellationRequest(transactionId, totalticketsToCancel, penaltyAmount, ticketNumbers, transportId);
			if (response!=null && response.size()>0) {
				

					return response;
				}
				else{
					return response;
					
				}
			
			



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;


	}

	//---------------------------.GetBookedHistoryRequest-------------------------------//


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetBookedHistoryRequest")

	public JSONObject GetBookedHistoryRequest(JSONObject jsonObject)
	{
		JSONObject object=new JSONObject();
		String fromDate=jsonObject.getString("FromDate");
		String toDate=jsonObject.getString("FromDate");
		String recordsBy=jsonObject.getString("RecordsBy");



		try{
			BusAPI api=new BusAPI();
			org.json.JSONObject response=api.GetBookedHistoryRequest(fromDate, toDate, recordsBy);
			if (response!=null && response.length()>0) {

				if (response.getInt("ResponseStatus")==1) {
					object.put("status", "0");
					object.put("Remarks", "succuss");
					object.put("CancellationPenaltyOutput", response.toString());
					System.out.println("Sundaram Response"+object);
					return object;

				}
				else{

					object.put("status", "1");
					object.put("Remarks", response.get("Booked History Not Available"));
				}



			}
			else {
				object.put("status", "1");
				object.put("Remarks", "Booked History Not Available");
			}



		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;


	}

	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetAccountStatement")

	public JSONObject GetAccountStatementRequest(JSONObject jsonObject)
	{
		JSONObject object=new JSONObject();
		String fromDate=jsonObject.getString("FromDate");
		String toDate=jsonObject.getString("FromDate");




		try{
			BusAPI api=new BusAPI();
			org.json.JSONObject response=api.GetAccountStatementRequest(fromDate, toDate);
			if (response!=null && response.length()>0) {
				if(response.getInt("ResponseStatus")==1)
				{

					object.put("status", "0");
					object.put("Remarks", "succuss");
					object.put("AccountStatementOutput", response.toString());

					System.out.println("Sundaram Response"+object);

					return object;
				}
				else{

					object.put("status", "1");
					object.put("Remarks", response.get("Statement Not Available"));
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;


	}


}