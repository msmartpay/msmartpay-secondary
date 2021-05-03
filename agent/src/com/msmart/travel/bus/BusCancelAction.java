package com.msmart.travel.bus;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.msmart.travel.bus.dao.BusBookingDao;

public class BusCancelAction {

	Logger logger=Logger.getLogger(BusCancelAction.class);

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

			if("busCancel".equalsIgnoreCase(param)){
				
				returnUrl= "busCancelPage";
				
			}else if("busCancelPNRSearch".equalsIgnoreCase(param)){


				String PNRNumber=request.getParameter("PNRNumber");
				
				BusBookingDao bookingDao=new BusBookingDao();

				String cancelPolicy=bookingDao.getCancellationPolicyDetails(PNRNumber);

				BusServices busServices=new BusServices();
				HashMap<String,Object> CancellationPenaltyDetails= busServices.GetCancellationPenaltyRequest(PNRNumber);
				
				if(CancellationPenaltyDetails!=null && "Success".equalsIgnoreCase(CancellationPenaltyDetails.get("Status").toString())){
					System.out.println("BusCancelAction.execute() :: "+CancellationPenaltyDetails);
					
					request.setAttribute("CancellationPenaltyDetailsStatus", "Y");
					session.setAttribute("CancellationPenaltyDetails", CancellationPenaltyDetails);
				}else{
					request.setAttribute("CancellationPenaltyDetailsStatus", "N");
					request.setAttribute("CancellationPenaltyDetails", CancellationPenaltyDetails.get("FailureRemarks"));
				}
				
				request.setAttribute("cancelPolicy", cancelPolicy);
				request.setAttribute("PNRDetails", "Y");
				
				return "busCancelPage";
				
			}else if("cancelTicket".equalsIgnoreCase(param)){

				try {
					
					String PNRNumber=request.getParameter("PNRNumber");
					String transportId=request.getParameter("TransportId");
					
					int totalTicketToCancel=0;
					
					String[] cancelCheckpartail=request.getParameterValues("checkpartial");
					
					totalTicketToCancel=cancelCheckpartail.length;
					
					String totalTicketNumbers="";
					double totalPenaltyAmount=0;
					
					ArrayList<HashMap<String,String>> cancelPassList=new ArrayList<>();
					
					for(int i=0; i<cancelCheckpartail.length; i++)
					{
						String tokenNumber=cancelCheckpartail[i];
						int Idnumber=Integer.parseInt(tokenNumber);
						
						double pAmount=Double.parseDouble(request.getParameter("PenatlyAmount"+Idnumber));
						totalPenaltyAmount=totalPenaltyAmount+pAmount;
						
						String ticketNumber=request.getParameter("TicketNumber"+Idnumber);
						totalTicketNumbers=totalTicketNumbers+ticketNumber+",";
						
						HashMap<String,String> map=new HashMap<>();
						map.put("TicketNumber", ticketNumber);
						
						cancelPassList.add(map);
						
					}

					BusAPI apiCall=new BusAPI();
					session.removeAttribute("seatDetails");
					HashMap<String, Object> cancelStatus =apiCall.GetCancellationRequest(PNRNumber, totalTicketToCancel, String.valueOf(totalPenaltyAmount), totalTicketNumbers, transportId);

					if(cancelStatus!=null ){
						
						
						//PaneltyAmount,RefundedAmount,UpdateFinalUpdateStatus,UserTrackId,TransactionId,
						
						//PassenderList to cancel
						
						
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
