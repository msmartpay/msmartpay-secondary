package com.flightApi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.apache.log4j.Logger;

import com.msmart.controller.LoginController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Path("flight")
public class FlightApiController {


	Logger logger = Logger.getLogger(LoginController.class);


	/*
	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getSource")

	public JSONObject getSource(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			System.out.println("RequestController.getCity()");
			ArrayList<HashMap<String, String>> city=FlightApi.getSource();

			if(city!=null){
				response.put("City", city);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Failure");
			}
			System.out.println("RequestController.getCity() "+response);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Failure");
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
			ArrayList<HashMap<String, String>> destination=FlightApi.getDestination(OriginId);

			if(destination!=null){
				response.put("City", destination);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Failure");
			}
			System.out.println("RequestController.getCity() "+response);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
			response=new JSONObject();
			response.put("status", "1");
			response.put("message", "Failure");
			return response;
		}

	}
	 */
	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetAvailabilityRequest")

	public JSONObject getflightSearch(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject(); 
		try {

			String origin=jsonObject.getString("Origin");
			String destination=jsonObject.getString("Destination");
			String traveldate=jsonObject.getString("TravelDate");
			String classType=jsonObject.getString("ClassType");
			String infantCount=jsonObject.getString("InfantCount");
			String childCount=jsonObject.getString("ChildCount");
			String adultCount=jsonObject.getString("AdultCount");

			System.out.println("RequestController.GetAvailabilityRequest()");
			FlightApi dao=new FlightApi();
			ArrayList<HashMap<String, Object>> flightList=dao.GetAvailabilityRequest(origin, destination, traveldate, adultCount, childCount, infantCount, classType);

			if(flightList!=null){
				response.put("AvailabilityOutput", flightList);
				response.put("status", "0");
				response.put("message", "Success");
			}else{
				response.put("status", "1");
				response.put("message", "Failure");
			}
			System.out.println("RequestController.getCity() "+response);

			return response;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;


	}


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getTaxRequest")

	public HashMap<String, Object> getTaxRequest(JSONObject jsonObject)	
	{
		net.sf.json.JSONObject response=null;
		
		try {

			String UserTrackId=jsonObject.getString("UserTrackId");
			String FlightId=jsonObject.getString("FlightId");
			String ClassCode=jsonObject.getString("ClassCode");
			String AirlineCode=jsonObject.getString("AirlineCode");
			String ETicketFlag=jsonObject.getString("ETicketFlag");
			String BasicAmount=jsonObject.getString("BasicAmount");
			String SupplierId=jsonObject.getString("SupplierId");
			String AdultCount=jsonObject.getString("AdultCount");
			String ChildCount=jsonObject.getString("ChildCount");
			String InfantCount=jsonObject.getString("InfantCount");
			
			String GSTNumber=jsonObject.getString("GSTNumber");
			String EMailId=jsonObject.getString("EMailId");
			String CompanyName=jsonObject.getString("CompanyName");
			String ContactNumber=jsonObject.getString("ContactNumber");
			String Address=jsonObject.getString("Address");
			String FirstName=jsonObject.getString("FirstName");
			String LastName=jsonObject.getString("LastName");
			


			double adultTotal=(Double.parseDouble(BasicAmount)* Double.parseDouble(AdultCount));
			double childTotal=(Double.parseDouble(BasicAmount)* Double.parseDouble(ChildCount));
			double ifantTotal=(Double.parseDouble(BasicAmount)* Double.parseDouble(InfantCount));

			double totleAmount=adultTotal+childTotal+ifantTotal;
			FlightApi apiCall=new FlightApi();
			HashMap<String, Object> getText=apiCall.getTaxRequest(UserTrackId, FlightId, ClassCode, AirlineCode, ETicketFlag, totleAmount, SupplierId,GSTNumber,EMailId,CompanyName,ContactNumber
					,Address,FirstName,LastName);
			if(getText!=null)
			{
				return getText;
			}
			

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		


	}




	// ---------------------------Get Flight Request-------------------------------//

	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getGetBookRequest")

	public JSONObject getGetBookRequest(JSONObject jsonObject)	
	{
		
		
		JSONObject resp=new JSONObject();
		try{


			String UserTrackId=jsonObject.getString("UserTrackId");
			String Title=jsonObject.getString("Title");
			String Name=jsonObject.getString("Name");
			String Address=jsonObject.getString("Address");
			String City=jsonObject.getString("City");
			String CountryId=jsonObject.getString("CountryId");
			String ContactNumber=jsonObject.getString("ContactNumber");
			String EmailId=jsonObject.getString("EmailId");
			String PinCode=jsonObject.getString("PinCode");
			String AirlineCode=jsonObject.getString("AirlineCode");
			String BookingType=jsonObject.getString("BookingType");
			String AdultCount=jsonObject.getString("AdultCount");
			String ChildCount=jsonObject.getString("ChildCount");
			String InfantCount=jsonObject.getString("InfantCount");
			String TotalAmount=jsonObject.getString("TotalAmount");
			JSONArray FlightBookingDetailsary=jsonObject.getJSONArray("FlightBookingDetails");


			FlightApi apiCall=new FlightApi();

			HashMap<String, Object> Map=apiCall.getBookRequest(UserTrackId, Title, Name, Address, City, CountryId, ContactNumber, EmailId, PinCode, AirlineCode, BookingType, AdultCount, ChildCount, InfantCount, TotalAmount, FlightBookingDetailsary);

			if(  Map.get("message").equals("success"))
			{
				
				
				resp.put("bookingDetails", Map);
				resp.put("message", Map.get("message"));
				resp.put("status", "0");
				
				System.out.println("getBookRequest:::::::::::::::"+resp);
				
				return resp;



			}
			else{
				resp.put("bookingDetails", Map);
				resp.put("message", Map.get("message"));
				resp.put("status", "1");
			}


		} catch (Exception e) {
			e.printStackTrace();
		}



		return resp;




	}


	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetTransactionStatus")

	public JSONObject GetTransactionStatusRequest(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject();
		try{


			String UserTrackId=jsonObject.getString("UserTrackId");
			

			FlightApi apiCall=new FlightApi();

			HashMap<String, Object> Map=apiCall.GetTransactionStatusRequest(UserTrackId);

			if(  Map!=null &&  Map.size()>0)
			{
				response.put("BookOutput", Map);
				response.put("status", "0");
				response.put("message","success" );
				return response;



			}
			else {
				
				response.put("status", "0");
				response.put("message","Due to technical Problems" );
				return response;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}



		return null;




	}

	
	
	@POST 
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/GetReprint")

	public JSONObject GetReprintRequest(JSONObject jsonObject)	
	{
		JSONObject response=new JSONObject();
		try{


			String hermesPNR=jsonObject.getString("HermesPNR");
			

			FlightApi apiCall=new FlightApi();

			ArrayList<HashMap<String, Object>> Map=apiCall.GetReprintRequest(hermesPNR);

			if(  Map!=null &&  Map.size()>0)
			{
				response.put("BookOutput", Map);
				response.put("status", "0");
				response.put("message","success" );
				return response;



			}
			else {
				
				response.put("status", "0");
				response.put("message","Due to technical Problems" );
				return response;
			}


		} catch (Exception e) {
			e.printStackTrace();
		}



		return null;




	}



@POST 
@Produces("application/json")
@Consumes("application/json")
@Path("/GetCancellation")

public HashMap<String, Object> GetCancellationRequest(JSONObject jsonObject)	
{
	try{


		String HermesPNR=jsonObject.getString("HermesPNR");
		String AirlinePNR=jsonObject.getString("AirlinePNR");
		String CancelType=jsonObject.getString("CancelType");

		FlightApi apiCall=new FlightApi();

		HashMap<String, Object> Map=apiCall.GetCancellationRequest(HermesPNR, AirlinePNR, CancelType);

		if(  Map!=null &&  Map.size()>0)
		{
			/*response.put("BookOutput", Map);*/
			return Map;



		}


	} catch (Exception e) {
		e.printStackTrace();
	}



	return null;




}

}




/*
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

			String flightType=jsonObject.getString("flightType");
			String flightName=jsonObject.getString("flightName");
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


			FlightApi apiCall=new FlightApi();

			String TransactionID=RandomStringUtils.randomNumeric(20);
			String tranId=RandomStringUtils.randomNumeric(14);

			flightBookingDao bookingDao=new flightBookingDao();

			//---------------------------------------------------------------


			ArrayList<HashMap<String, String>> PassengerarryList=new ArrayList<>();
			JSONArray PassengerList=jsonObject.getJSONArray("PassengerList");
			if(PassengerList!=null && PassengerList.size()>0)
			{
				for (int i = 0; i < PassengerList.size(); i++) {



					JSONObject  object=PassengerList.getJSONObject(i);
					HashMap<String, String> hashMap=new  HashMap<>();
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

					PassengerarryList.add(hashMap);



				}


			//Save Booking Details
				String saveStatus=bookingDao.saveBooking(TransactionID, TransactionID, Long.parseLong(agentID), newUserTrackId, PassengerarryList, customerTitle, customerName, address, contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets, TotalAmount, travelDate, flightType, flightName, TransportName, ArrivalTime, departureTime, Ipaddress, originName, destinationName);

					//****************************************** Booking Request ****************************************** //

					System.out.println("saveStatus :: "+saveStatus);

				// Block flight seat
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
			FlightApi api=new FlightApi();
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
			FlightApi api=new FlightApi();
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
			FlightApi api=new FlightApi();
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
					object.put("Remarks", response.get("FailureRemarks"));
				}



			}
			else {
				object.put("status", "1");
				object.put("Remarks", "Faliur");
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
			FlightApi api=new FlightApi();
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
					object.put("Remarks", response.get("FailureRemarks"));
				}

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;


	}*/


