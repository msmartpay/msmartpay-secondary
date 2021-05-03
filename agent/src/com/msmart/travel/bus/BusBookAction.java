package com.msmart.travel.bus;

import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.msmart.travel.bus.dao.BusBookingDao;

public class BusBookAction {

	Logger logger=Logger.getLogger(BusBookAction.class);

	private HttpServletRequest request;

	public String execute()
			throws Exception {


		String agentID="",returnUrl="";

		request=ServletActionContext.getRequest();
		HttpSession session=request.getSession(false);

		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			if(param==null)param="";
			logger.info("TEP ,Class is BusSearchAction ,Param is "+param);

			if("seatBlockRequest".equalsIgnoreCase(param)){

				try {

					String customerTitle="", customerName="";

					String userTrackId=request.getParameter("userTrackId");
					String address=request.getParameter("address");
					String contactNumber=request.getParameter("contactNumber");
					String city=request.getParameter("city");
					String countryId=request.getParameter("countryId");
					String emailId=request.getParameter("emailId");
					String idProofId=request.getParameter("idProofType");
					String idProofNumber=request.getParameter("idProofNumber");

					String BusType=request.getParameter("BusType");
					String BusName=request.getParameter("BusName");
					String TransportName=request.getParameter("TransportName");
					String ArrivalTime=request.getParameter("ArrivalTime");

					String transportId=request.getParameter("transportId");
					String scheduleId=request.getParameter("scheduleId");
					String stationId=request.getParameter("stationId");
					String travelDate=request.getParameter("travelDate");
					String departureTime=request.getParameter("departureTime");

					String originName=request.getParameter("originName");
					String destinationName=request.getParameter("destinationName");

					double totalAmount=0,grossTotal=0;
					double totalServiceTax=Double.parseDouble(request.getParameter("totalServiceTax"));
					double totalConvenienceFee=Double.parseDouble(request.getParameter("totalConvenienceFee"));


					ArrayList<HashMap<String, String>> passengerArrayList=new ArrayList<>();

					JSONArray PassengerList=new JSONArray();


					String[] checkOperator=(String[])request.getParameterValues("passenger");

					int totalTickets=checkOperator.length;

					ArrayList<HashMap<String, Object>> boardingList=(ArrayList<HashMap<String, Object>>)session.getAttribute("BoardingPoints");

					for(int i=0;i<checkOperator.length;i++){
						HashMap<String, String> map=new HashMap<>();
						JSONObject PassengerDetails=new JSONObject();

						if(i==0){
							customerName=request.getParameter("passengerName"+String.valueOf(i));
							customerTitle=request.getParameter("passenderTitle"+String.valueOf(i));
						}
						String passenderTitle=request.getParameter("passenderTitle"+String.valueOf(i));
						String passengerName=request.getParameter("passengerName"+String.valueOf(i));
						String gender=request.getParameter("gender"+String.valueOf(i));
						int age=Integer.parseInt(request.getParameter("age"+String.valueOf(i)));
						int seatNo=Integer.parseInt(request.getParameter("SeatNo"+String.valueOf(i)));
						int seatTypeId=Integer.parseInt(request.getParameter("SeatTypeId"+String.valueOf(i)));
						double fare=Double.parseDouble(request.getParameter("Fare"+String.valueOf(i)));

						totalAmount=totalAmount+fare;

						String boardingId=request.getParameter("boardingPoint"+String.valueOf(i));
						String droppingId=request.getParameter("droppingPoint"+String.valueOf(i));

						PassengerDetails.put("Title", passenderTitle);
						PassengerDetails.put("PassengerName", passengerName);
						PassengerDetails.put("Gender", gender);
						PassengerDetails.put("Age", age);
						PassengerDetails.put("SeatNo", seatNo);
						PassengerDetails.put("SeatTypeId", seatTypeId);
						PassengerDetails.put("Fare", fare);
						PassengerDetails.put("BoardingId", boardingId);
						PassengerDetails.put("DroppingId", droppingId);
						PassengerList.put(PassengerDetails);

						map.put("Title", passenderTitle);
						map.put("PassengerName", passengerName);
						map.put("Gender", gender);
						map.put("Age", String.valueOf(age));
						map.put("SeatNo", String.valueOf(seatNo));
						map.put("SeatTypeId", String.valueOf(seatTypeId));
						map.put("Fare", String.valueOf(fare));
						map.put("BoardingId", boardingId);
						map.put("DroppingId", droppingId);

						for(HashMap<String,Object> boarding:boardingList){

							String BoardingId=(String)boarding.get("BoardingId");
							if(BoardingId.equalsIgnoreCase(boardingId)){
								map.put("Place", boarding.get("Place")+"");
								map.put("Time", boarding.get("Time")+"");
								map.put("Address", boarding.get("Address")+"");
								map.put("LandMark", boarding.get("LandMark")+"");
								map.put("ContactNumber", boarding.get("ContactNumber")+"");
							}

						}

						passengerArrayList.add(map);

					}

					String blockStatus="",newUserTrackId="",Remarks="";
					grossTotal=totalAmount+totalServiceTax+totalConvenienceFee;
					
					double avalBal=Double.parseDouble(session.getAttribute("AgentBalance").toString());
					
					if(avalBal>=grossTotal){
						BusAPI apiCall=new BusAPI();
						
						// Block Bus seat
						HashMap<String,Object> hasmap =apiCall.GetSeatBlockRequest(userTrackId,customerTitle,customerName,address,contactNumber,city,countryId,emailId,idProofId,idProofNumber,totalTickets,totalAmount,Integer.parseInt(transportId),scheduleId,stationId, travelDate,PassengerList);

						if(hasmap!=null){

							blockStatus=(String) hasmap.get("Status");
							newUserTrackId=(String) hasmap.get("UserTrackId");
							Remarks=(String) hasmap.get("Remarks");

							if("Success".equalsIgnoreCase(blockStatus) && !"SEAT NO ALREADY BOOKED".equalsIgnoreCase(Remarks)){

								//****************************************** Storing booking request in Data Base ***************************** //

								String Ipaddress=request.getRemoteHost();
								String TransactionID=RandomStringUtils.randomNumeric(20);
								String tranId=RandomStringUtils.randomNumeric(14);

								BusBookingDao bookingDao=new BusBookingDao();
								
								//---------------------------------------------------------------

								
								//Save Booking Details
								String saveStatus=bookingDao.saveBooking(TransactionID, tranId, Long.parseLong(agentID), userTrackId, passengerArrayList, customerTitle, customerName, address, contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets, grossTotal, travelDate, BusType, BusName, TransportName+"$"+transportId, ArrivalTime, departureTime, Ipaddress, originName, destinationName);

								//****************************************** Booking Request ****************************************** //

								System.out.println("saveStatus :: "+saveStatus);
								
								if("inserted".equalsIgnoreCase(saveStatus)){
									
									HashMap<String, Object> bookingResponse= apiCall.GetBookingRequest(tranId,userTrackId, customerTitle,customerName, address,contactNumber, city, countryId, emailId, idProofId, idProofNumber, totalTickets,totalAmount, Integer.parseInt(transportId),  scheduleId,stationId, travelDate, PassengerList);
									if(bookingResponse!=null){

										int responseStatus=(Integer)bookingResponse.get("ResponseStatus");
										
										if(responseStatus==1){
											
											//Update Booking response
											
											String updateStatus=bookingDao.updateBusBookStatus(bookingResponse, userTrackId);
											
											System.out.println("updateStatus :: "+updateStatus);
											
											request.setAttribute("originName",originName );
											request.setAttribute("destinationName", destinationName);
											request.setAttribute("travelDate",travelDate );
											request.setAttribute("departureTime", departureTime);
											
											request.setAttribute("BusType",BusType );
											request.setAttribute("BusName", BusName);
											request.setAttribute("ArrivalTime", ArrivalTime);
											request.setAttribute("grossTotal", grossTotal);
											
											request.setAttribute("bookingResponse", bookingResponse);
											
											request.setAttribute("message", "Your booking has been confirmed.");
											
											return "BusBookResult";
											
										}else{
											
											String updatestatus=bookingDao.updateBusBookStatus(tranId, userTrackId,Long.parseLong(agentID));
											
											System.out.println("BusBookAction.execute() :: "+updatestatus);
											
											request.setAttribute("message", "Sorry! we are unable to book your Bus. Please try latter.");
										
										}

									}else{
										request.setAttribute("message", "Sorry! we are unable to book your Bus. Please try latter.");
									}

								}else{

									request.setAttribute("message", "Sorry! we are unable to book your Bus. Please try latter.");
									
								}


							}else{

								if(Remarks!=null && Remarks.length()>0)
									request.setAttribute("message", Remarks);	
								else
									request.setAttribute("message", "Sorry! we are unable to book your Bus. Please try latter.");	

							}
							
							return "BusErrorResult";

						}else{
							blockStatus="Failure";
							newUserTrackId=userTrackId;
							Remarks= "Seat Not Available. Try for another seat.";
						}

						session.setAttribute("passengerArrayList", passengerArrayList);
						session.setAttribute("PassengerList", PassengerList);
						request.setAttribute("totalTickets", totalTickets);

						request.setAttribute("userTrackId", newUserTrackId);
						request.setAttribute("customerTitle", customerTitle);
						request.setAttribute("customerName", customerName);
						request.setAttribute("address", address);
						request.setAttribute("contactNumber", contactNumber);
						request.setAttribute("city", city);
						request.setAttribute("countryId", countryId);
						request.setAttribute("emailId", emailId);
						request.setAttribute("idProofId", idProofId);
						request.setAttribute("idProofNumber", idProofNumber);
						request.setAttribute("transportId", transportId);
						request.setAttribute("scheduleId", scheduleId);
						request.setAttribute("stationId", stationId);
						request.setAttribute("travelDate", travelDate);
						request.setAttribute("totalAmount", totalAmount);
						request.setAttribute("blockStatus", blockStatus);
						request.setAttribute("Remarks", Remarks);
					}else{
						
						request.setAttribute("message", "You have not sufficient balance to book this ticket.");
						return "BusErrorResult";
					}


				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return "BusBlockResult";

			}else if("seatBlockRequest".equalsIgnoreCase(param)){

				try {

					String userTrackId=request.getParameter("userTrackId");
					String customerTitle=request.getParameter("customerTitle");
					String customerName=request.getParameter("customerName");
					String address=request.getParameter("address");
					String contactNumber=request.getParameter("contactNumber");
					String city=request.getParameter("city");
					String countryId=request.getParameter("countryId");
					String emailId=request.getParameter("emailId");
					String idProofId=request.getParameter("idProofType");
					String idProofNumber=request.getParameter("idProofNumber");


					int transportId=Integer.parseInt(request.getParameter("transportId"));
					String scheduleId=request.getParameter("scheduleId");
					String stationId=request.getParameter("stationId");
					String travelDate=request.getParameter("travelDate");
					double totalAmount=Double.parseDouble(request.getParameter("totalAmount"));
					String boardingId=request.getParameter("boardingId");
					String droppingId=request.getParameter("droppingId");


					JSONArray PassengerList=new JSONArray();


					int totalTickets=Integer.parseInt(request.getParameter("totalTickets"));


					String blockStatus="",newUserTrackId="",Remarks="";

					BusAPI apiCall=new BusAPI();
					HashMap<String,Object> hasmap =apiCall.GetSeatBlockRequest(userTrackId,customerTitle,customerName,address,contactNumber,city,countryId,emailId,idProofId,idProofNumber,totalTickets,totalAmount,transportId,scheduleId,stationId, travelDate,PassengerList);

					if(hasmap!=null){

						blockStatus=(String) hasmap.get("Status");
						newUserTrackId=(String) hasmap.get("UserTrackId");
						Remarks=(String) hasmap.get("Remarks");

					}else{
						blockStatus="Failure";
						newUserTrackId=userTrackId;
						Remarks= "Seat Not Available. Try for another seat.";
					}

					/*					request.setAttribute("passengerArrayList", passengerArrayList);
					request.setAttribute("PassengerList", PassengerList);
					 */					
					request.setAttribute("userTrackId", newUserTrackId);
					request.setAttribute("customerTitle", customerTitle);
					request.setAttribute("customerName", customerName);
					request.setAttribute("address", address);
					request.setAttribute("contactNumber", contactNumber);
					request.setAttribute("city", city);
					request.setAttribute("countryId", countryId);
					request.setAttribute("emailId", emailId);
					request.setAttribute("idProofId", idProofId);
					request.setAttribute("idProofNumber", idProofNumber);
					request.setAttribute("transportId", transportId);
					request.setAttribute("scheduleId", scheduleId);
					request.setAttribute("stationId", stationId);
					request.setAttribute("travelDate", travelDate);
					request.setAttribute("totalAmount", totalAmount);
					request.setAttribute("boardingId", boardingId);
					request.setAttribute("droppingId", droppingId);

					request.setAttribute("blockStatus", blockStatus);
					request.setAttribute("Remarks", Remarks);


				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "BusErrorResult";

				}
				return "BusBookResult";

			}

		}catch(Exception e){
			e.printStackTrace();
		}


		return returnUrl;
	}

}
