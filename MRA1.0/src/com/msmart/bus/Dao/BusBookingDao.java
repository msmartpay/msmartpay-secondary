package com.msmart.bus.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.msmart.dbconnection.DBConnection;

public class BusBookingDao {

	public String saveBooking(String TransactionID,String TransactionNo,long agentID,String userTrackId,ArrayList<HashMap<String,String>> passengerList,String customerTitle,String customerName,
			String address,String contactNumber,String city,String countryId,String emailId,String idProofId,String idProofNumber,int totalTickets,
			double reqAmount,String travelDate,String BusType,
			String BusName,String TransportName,String ArrivalTime,String departureTime,String Ipaddress,String originName,String destinationName) {

		String status="";
		Connection con = null;
		PreparedStatement pstmt=null;
		CallableStatement cstmt=null;
		try
		{
			
			String BillPayOperator="Bus-Book",service="Bus",apiProvider="Hermes";
			
			con = DBConnection.getConnection();
			//con.setAutoCommit(false);

			
			String sql="insert into Bus_Passenger_Details (User_Track_Id, Pass_Name, Seat_No, Seat_Type, Gender, Age, Fare, Place, Boarding_Time, "
					+ "Boarding_Address, Boarding_Land_Mark, Boarding_Contact_Number) values (?,?,?,?,?,?,?,?,?,?,?,? )";

			for(HashMap<String,String> passenger : passengerList ){

				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, userTrackId);
				pstmt.setString(2, passenger.get("PassengerName"));
				pstmt.setString(3, passenger.get("SeatNo"));
				pstmt.setString(4, passenger.get("SeatTypeId"));
				pstmt.setString(5, passenger.get("Gender"));
				pstmt.setString(6, passenger.get("Age"));
				pstmt.setString(7, passenger.get("Fare"));
				pstmt.setString(8, passenger.get("Place"));
				pstmt.setString(9, passenger.get("Time"));
				pstmt.setString(10, passenger.get("Address"));
				pstmt.setString(11, passenger.get("LandMark"));
				pstmt.setString(12, passenger.get("ContactNumber"));
				
				pstmt.execute();

			}

			cstmt = con.prepareCall("{call Bus_Booking_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setLong(1, agentID);
			cstmt.setString(2, BillPayOperator);
			cstmt.setString(3, contactNumber);
			cstmt.setDouble(4, reqAmount);
			cstmt.setString(5, TransactionNo);
			cstmt.setString(6, service);
			cstmt.setString(7, Ipaddress);
			cstmt.setString(8, apiProvider);
			cstmt.setString(9, TransactionID);
			cstmt.setString(10, customerTitle);
			cstmt.setString(11, customerName);
			cstmt.setString(12, address);
			cstmt.setString(13, contactNumber);
			cstmt.setString(14, city);
			cstmt.setString(15, countryId);
			cstmt.setString(16, idProofId);
			cstmt.setString(17, idProofNumber);
			cstmt.setString(18, emailId);
			cstmt.setInt(19, totalTickets);
			cstmt.setString(20, BusName);
			cstmt.setString(21, originName);
			cstmt.setString(22, destinationName);
			cstmt.setString(23, travelDate);
			cstmt.setString(24, departureTime);
			cstmt.setString(25, TransportName);
			cstmt.setString(26, userTrackId);
			cstmt.registerOutParameter(27, java.sql.Types.VARCHAR);
			cstmt.execute();
			//con.commit();
			status=cstmt.getString(27);
			
		}catch (Exception ex) {
			
			try {
				//con.rollback();
				ex.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		finally {
			try {
				
				if (pstmt != null)
					pstmt.close();
				if (cstmt != null)
					cstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return status;

	}
	
	public String updateBusBookStatus(HashMap<String,Object> ticketDetailsMap,String UserTrackId) {
		
		String status="";
		
		Connection con = null;
		PreparedStatement pstmt=null;
		try
		{
			
			ArrayList<HashMap<String,Object>> passengerList=(ArrayList<HashMap<String,Object>>)ticketDetailsMap.get("PaxList");
			HashMap<String,Object> pnrDetails=(HashMap<String,Object>)ticketDetailsMap.get("PNRDetails");
			HashMap<String,Object> transportDetails=(HashMap<String,Object>)ticketDetailsMap.get("TransportDetails");
			
			con = DBConnection.getConnection();
			con.setAutoCommit(false);
			
			String sql="update Bus_Booking_Details set Reference_Id=?,Transport_PNR=?,Transport_Name=?,Departure_Time=?,"
					+ "Transport_Address=?,City_PinCode=?,Transport_Fax=?,Website=?,Transport_Email=?,Cancellation_Policy=? where User_Track_Id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pnrDetails.get("TransactionId")+"");
			pstmt.setString(2, pnrDetails.get("TransportPNR")+"");
			
			pstmt.setString(3, transportDetails.get("TransportName")+"");
			pstmt.setString(4, pnrDetails.get("DepartureTime")+"");
			pstmt.setString(5, transportDetails.get("Address1")==null?"":transportDetails.get("Address1").toString()+transportDetails.get("Address2")==null?"":transportDetails.get("Address2").toString());
			pstmt.setString(6, transportDetails.get("CityNamePinCode")==null?"":transportDetails.get("CityNamePinCode").toString());
			pstmt.setString(7, transportDetails.get("Fax")==null?"":transportDetails.get("Fax").toString());
			pstmt.setString(8, transportDetails.get("Website")==null?"":transportDetails.get("Website").toString());
			pstmt.setString(9, transportDetails.get("EmailId")==null?"":transportDetails.get("EmailId").toString());
			pstmt.setString(10, ticketDetailsMap.get("CancellationPolicy")+"");
			pstmt.setString(11, ticketDetailsMap.get("UserTrackId")+"");
			
			pstmt.execute();
			
			
			sql="UPDATE Bus_Passenger_Details SET Ticket_Number=?,Service_Charge=?,Place=?,Boarding_Time=?,Boarding_Address=?,"
					+ "Boarding_Land_Mark=?,Boarding_Contact_Number=?,Droping_Time=?,Droping_Place=?,Droping_Address=?,Droping_Land_Mark=?,Droping_Contact=?"
					+ ",Reporting_Time=? WHERE User_Track_Id=?";

			for(HashMap<String,Object> passenger : passengerList ){

				pstmt=con.prepareStatement(sql);
				pstmt.setString(1,passenger.get("TicketNumber")+"");
				pstmt.setDouble(2, Double.parseDouble((passenger.get("ServiceCharges")!=null && !"".equals(passenger.get("ServiceCharges")))?passenger.get("ServiceCharges").toString():"0"));
				pstmt.setString(3,passenger.get("BoardingPlace")+"");
				pstmt.setString(4, passenger.get("BoardingTime")+"");
				pstmt.setString(5,passenger.get("BoardingAddress")+"");
				pstmt.setString(6, passenger.get("BoardingLandMark")+"");
				pstmt.setString(7,passenger.get("BoardingContactNumber")+"");
				pstmt.setString(8, passenger.get("DroppingTime")+"");
				pstmt.setString(9,passenger.get("DroppingPlace")+"");
				pstmt.setString(10, passenger.get("DroppingAddress")+"");
				pstmt.setString(11,passenger.get("DroppingLandMark")+"");
				pstmt.setString(12, passenger.get("DroppingContactNumber")+"");
				pstmt.setString(13, passenger.get("ReportingTime")+"");
				pstmt.setString(14, UserTrackId);
				
				pstmt.execute();

			}
			
			con.commit();
			
		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}finally{
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return status;
	}
	
	public void saveRequestLog(String request,String tranId) {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			
			con=DBConnection.getConnection();
			String sql="INSERT INTO Bus_Booking_Request_Response_Log (Transaction_Id, Request, Request_DateTime) values (?,?,getdate())";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, tranId);
			pstmt.setString(2, request);
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void updateResponseLog(String response,String tranId) {
		
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			
			con=DBConnection.getConnection();
			String sql="UPDATE Bus_Booking_Request_Response_Log set Response=?,Response_DateTime=getdate() WHERE Transaction_Id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, response);
			pstmt.setString(2, tranId);
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public String getCancellationPolicyDetails(String pnrNumber) {
		
		String cancelPlicyDetail="";
		Connection con = null;
		PreparedStatement pstmt=null;
		try {
			
			con=DBConnection.getConnection();
			String sql= "select Cancellation_Policy from Bus_Booking_Details where Transport_PNR=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pnrNumber);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
				cancelPlicyDetail=rs.getString(1);
			else
				cancelPlicyDetail="Cancellation Policy Not Available.";
			
			if("null".equals(cancelPlicyDetail))
				cancelPlicyDetail="Cancellation Policy Not Available.";
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return cancelPlicyDetail;
	}

}
