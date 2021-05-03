package com.msmart.travel.bus;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class BusSearchAction {

	Logger logger=Logger.getLogger(BusSearchAction.class);

	private HttpServletRequest request;
	private HttpServletResponse response;

	public String execute()
			throws Exception {


		String agentID="",returnUrl="";

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
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

			if("BusPage".equalsIgnoreCase(param)){

				BusServices busServices=new BusServices();

				ArrayList<HashMap<String,String>> origin=busServices.getSourceList();
				session.setAttribute("Source", origin);

				returnUrl= "BusPage";

			}else if("searchBus".equalsIgnoreCase(param)){


				String originIdNew=request.getParameter("OriginId");
				String destinationIdNew=request.getParameter("DestinationId");
				String travelDate=request.getParameter("travelDate");
				String noofperson=request.getParameter("noofperson");
				BusAPI apiCall=new BusAPI();

				String[] originIdArr=originIdNew.split("_");
				String[] destinationIdArr=destinationIdNew.split("_");

				String destinationId="",originId="",originName="",destinationName="";

				originId=originIdArr[0];
				originName=originIdArr[1];
				destinationId=destinationIdArr[0];
				destinationName=destinationIdArr[1];

				HashMap<String,Object> busDetails =apiCall.busSearch(originId,destinationId ,travelDate );

				if(busDetails!=null){

					session.setAttribute("availBusDetails", busDetails);
					returnUrl="BusSearchResult";

				}else{

					request.setAttribute("message", "No Bus Available For Selected Date");
					returnUrl= "BusPage";

				}

				request.setAttribute("originName", originName);
				request.setAttribute("destinationName", destinationName);	

				request.setAttribute("originId", originId);
				request.setAttribute("destinationId", destinationId);	
				request.setAttribute("travelDate", travelDate);
				request.setAttribute("noofperson", noofperson);


			}else if("modifySearchBus".equalsIgnoreCase(param)){


				String originId=request.getParameter("originId");
				String destinationId=request.getParameter("destinationId");
				String originName=request.getParameter("originName");
				String destinationName=request.getParameter("destinationName");
				String travelDate=request.getParameter("travelDate");
				String noofperson=request.getParameter("noofperson");
				BusAPI apiCall=new BusAPI();


				HashMap<String,Object> busDetails =apiCall.busSearch(originId,destinationId ,travelDate );

				if(busDetails!=null){

					session.setAttribute("availBusDetails", busDetails);
					returnUrl="BusSearchResult";

				}else{

					request.setAttribute("message", "No Bus Available For Selected Date");
					returnUrl= "BusPage";

				}

				request.setAttribute("originName", originName);
				request.setAttribute("destinationName", destinationName);	

				request.setAttribute("originId", originId);
				request.setAttribute("destinationId", destinationId);	
				request.setAttribute("travelDate", travelDate);
				request.setAttribute("noofperson", noofperson);


			}else if("backSearchBus".equalsIgnoreCase(param)){
				String originId="",destinationId="";

				originId=request.getParameter("originId");
				destinationId=request.getParameter("destinationId");
				String originName=request.getParameter("originName");
				String destinationName=request.getParameter("destinationName");
				String travelDate=request.getParameter("travelDate");
				String noofperson=request.getParameter("noofperson");
				
				if(session.getAttribute("availBusDetails")==null){

					BusAPI apiCall=new BusAPI();


					HashMap<String,Object> busDetails =apiCall.busSearch(originId,destinationId ,travelDate );

					if(busDetails!=null){

						session.setAttribute("availBusDetails", busDetails);
						returnUrl="BusSearchResult";

					}else{

						request.setAttribute("message", "No Bus Available For Selected Date");
						returnUrl= "BusPage";

					}

					
				}else{
					returnUrl= "BusSearchResult";
				}
				
				request.setAttribute("originName", originName);
				request.setAttribute("destinationName", destinationName);	

				request.setAttribute("originId", originId);
				request.setAttribute("destinationId", destinationId);	
				request.setAttribute("travelDate", travelDate);
				request.setAttribute("noofperson", noofperson);

			}else if("searchSeatspage".equalsIgnoreCase(param)){

				try {

					String BusType=request.getParameter("BusType");
					String BusName=request.getParameter("BusName");
					String TransportName=request.getParameter("TransportName");
					String departureTime=request.getParameter("DepartureTime");
					String ArrivalTime=request.getParameter("ArrivalTime");
					String noofperson=request.getParameter("noofperson");


					String originId=request.getParameter("originId");
					String destinationId=request.getParameter("destinationId");

					String originName=request.getParameter("originName");
					String destinationName=request.getParameter("destinationName");


					String scheduleId=request.getParameter("ScheduleId");
					String stationId=request.getParameter("StationId");
					String transportId=request.getParameter("TransportId");
					String travelDate=request.getParameter("travelDate");
					String userTrackId=request.getParameter("UserTrackId");
					String droppingId=request.getParameter("DroppingId");

					BusAPI apiCall=new BusAPI();
					session.removeAttribute("seatDetails");
					ArrayList<HashMap<String, Object>> arrayList =apiCall.GetSeatMapRequest(scheduleId, stationId, transportId,travelDate,userTrackId ,session);

					if(arrayList!=null && arrayList.size()>0){
						request.setAttribute("SeatsList", arrayList);
						System.out.println("SeatsList"+ arrayList);


						request.setAttribute("originId", originId);
						request.setAttribute("destinationId", destinationId);

						request.setAttribute("originName", originName);
						request.setAttribute("destinationName", destinationName);	
						request.setAttribute("noofperson", noofperson);

						request.setAttribute("BusType", BusType);
						request.setAttribute("BusName", BusName);
						request.setAttribute("TransportName", TransportName);
						request.setAttribute("ArrivalTime", ArrivalTime);

						request.setAttribute("departureTime", departureTime);

						request.setAttribute("scheduleId", scheduleId);
						request.setAttribute("stationId", stationId);				
						request.setAttribute("transportId", transportId);
						request.setAttribute("droppingId", droppingId);
						request.setAttribute("travelDate", travelDate);
						request.setAttribute("userTrackId", userTrackId);

						returnUrl="SeatLayout";

						return returnUrl;
					}else{


						request.setAttribute("message", "Seat Not Available  ");
					}


				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

				return "BusPage";

			}else if("seatBookDetails".equalsIgnoreCase(param)){


				try {

					String originId=request.getParameter("originId");
					String destinationId=request.getParameter("destinationId");
					String BusName=request.getParameter("BusName");
					String TransportName=request.getParameter("TransportName");
					String originName=request.getParameter("originName");
					String destinationName=request.getParameter("destinationName");

					String departureTime=request.getParameter("departureTime");
					String scheduleId=request.getParameter("scheduleId");
					String stationId=request.getParameter("stationId");
					String transportId=request.getParameter("transportId");
					String travelDate=request.getParameter("travelDate");
					String userTrackId=request.getParameter("userTrackId");
					String droppingId=request.getParameter("DroppingId");

					String Details=request.getParameter("Details");



					if(userTrackId!=null && travelDate!=null && transportId!=null && scheduleId!=null && stationId!=null){


						request.setAttribute("originId", originId);
						request.setAttribute("destinationId", destinationId);

						request.setAttribute("BusName", BusName);
						request.setAttribute("TransportName", TransportName);

						request.setAttribute("originName", originName);
						request.setAttribute("destinationName", destinationName);

						request.setAttribute("departureTime", departureTime);

						request.setAttribute("scheduleId", scheduleId);
						request.setAttribute("stationId", stationId);				
						request.setAttribute("transportId", transportId);
						request.setAttribute("droppingId", droppingId);
						request.setAttribute("travelDate", travelDate);
						request.setAttribute("userTrackId", userTrackId);


						ArrayList<HashMap<String,String>> seatDetails=(ArrayList<HashMap<String,String>>)session.getAttribute("seatDetails");
						ArrayList<HashMap<String,String>> newseatDetails=new ArrayList<>();
						for(HashMap<String,String> map: seatDetails ){

							String seatNo=map.get("SeatNo");

							for(String seat:Details.split(",")){

								if(seat.equalsIgnoreCase(seatNo)){
									newseatDetails.add(map);
								}

							}

						}


						session.removeAttribute("seatDetails");
						session.setAttribute("seatDetails",newseatDetails);



						returnUrl="blockSeats";



					}else{

						BusAPI apiCall=new BusAPI();

						session.removeAttribute("seatDetails");
						ArrayList<HashMap<String, Object>> seatMapList =apiCall.GetSeatMapRequest(scheduleId, stationId, transportId,travelDate,userTrackId ,session);

						if(seatMapList!=null && seatMapList.size()>0){
							request.setAttribute("SeatsList", seatMapList);


							request.setAttribute("originId", originId);
							request.setAttribute("destinationId", destinationId);	
							request.setAttribute("originName", originName);
							request.setAttribute("destinationName", destinationName);

							request.setAttribute("departureTime", departureTime);

							request.setAttribute("scheduleId", scheduleId);
							request.setAttribute("stationId", stationId);				
							request.setAttribute("transportId", transportId);
							request.setAttribute("droppingId", droppingId);
							request.setAttribute("travelDate", travelDate);
							request.setAttribute("userTrackId", userTrackId);

							returnUrl="SeatLayout";


						}else{
							request.setAttribute("message", "Seat Not Available  ");

							returnUrl= "BusPage";
						}

					}

					return returnUrl;


				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}


			}else if("getSource".equalsIgnoreCase(param)){

				PrintWriter pw=response.getWriter();

				BusServices busServices=new BusServices();

				String origin=busServices.getSource();

				pw.print(origin);
				pw.close();
				return null;
			}else if("getDistination".equalsIgnoreCase(param)){

				PrintWriter pw=response.getWriter();

				String OriginId=request.getParameter("OriginId");

				BusServices busServices=new BusServices();

				String origin=busServices.getDistination(OriginId);

				pw.print(origin);
				pw.close();
				return null;
			}

		}catch(Exception e){
			e.printStackTrace();
		}


		return returnUrl;
	}

}
