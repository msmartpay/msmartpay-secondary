package com.controlPanel.inventoryMgt.carbooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.controlPanel.inventoryMgt.carbooking.CarBookingForm;

public class CarBookingDao 
{
	private static CarBookingDao carBookingDao=null;
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static ResultSet rs = null;
	Session session;
	
	public static CarBookingDao getInstance() 
	{
		if(carBookingDao==null)
			carBookingDao=new CarBookingDao();
		
		return carBookingDao;
	}
	
	public String saveCarBookingDetails(CarBookingForm carBookingForm) 
	{
		String status="failure";
		System.out.println("In Holiday Dao");
		Transaction txn;
		
			try 
			{
				session = HibernateSession.getSessionFactory().openSession();
				con = session.connection();
				txn=session.beginTransaction();
				String sql = "insert into Car_Booking_Details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				psmt=con.prepareStatement(sql);
				
				psmt.setLong(1, Long.parseLong((carBookingForm.getUserId().toString())));
				psmt.setString(2, carBookingForm.getCarBooking());
				psmt.setString(3, carBookingForm.getSubBookingType());
				psmt.setString(4,  carBookingForm.getSegment());
				psmt.setString(5,  carBookingForm.getOriginCity());
				psmt.setString(6,  carBookingForm.getDestinationCity());
				psmt.setString(7, carBookingForm.getDaysavail());
				psmt.setString(8,  carBookingForm.getCarName());
				psmt.setString(9, carBookingForm.getVendorName());
				psmt.setInt(10, carBookingForm.getSeatingCapacity());
				psmt.setDouble(11, carBookingForm.getTripPrice());
				psmt.setString(12, carBookingForm.getUserImageFileName());
				psmt.setString(13, carBookingForm.getAirCondition());
				psmt.setString(14, carBookingForm.getStereo());
				psmt.setString(15, carBookingForm.getChargePerKm());
				psmt.setString(16, carBookingForm.getAdvancePayment());
				psmt.setString(17, carBookingForm.getSpecialOffers());
				psmt.setString(18, carBookingForm.getTermCondition());
				psmt.setString(19, carBookingForm.getCanPolicy());
				psmt.setInt(20, carBookingForm.getBookingId());
				psmt.setString(21, carBookingForm.getFuelType());
				psmt.setString(22, carBookingForm.getTransmissionType());
				psmt.setString(23, carBookingForm.getNofBaggage());
				
				int check = psmt.executeUpdate();
				if(check > 0)
				{
					status="success";
				}
				else
				{
					status="failure";
				}
				txn.commit();
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					psmt.close();
					con.close();
					session.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		return status;
		
	}
	
	public String getCityCode(String cityName) 
	{
		String citycode = "N/A";
		try {
				session = HibernateSession.getSessionFactory().openSession();
				con = session.connection();
				String sql = "select citycode from holiday_pkd_city_details where cityname='"+cityName+"'" ;
				psmt=con.prepareStatement(sql);
				rs=psmt.executeQuery();
				if(rs.next())
				{
					citycode = rs.getString(1);
				}
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					psmt.close();
					con.close();
					session.close();

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			System.out.println("---city code-------"+citycode);
		return citycode;
	}
}
