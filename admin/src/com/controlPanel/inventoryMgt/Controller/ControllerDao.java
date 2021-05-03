package com.controlPanel.inventoryMgt.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.common.HibernateSession;

public class ControllerDao 
{
	private static ControllerDao controlDao = null;
	private Connection con = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	Session session;

	public static ControllerDao getInstance()
	{
		if(controlDao == null)
			controlDao = new ControllerDao();

		return controlDao;
	}

	public ArrayList getDetails(String from,String to) 
	{
		ArrayList details = new ArrayList<String>();
		HashMap detailsmap = null;

		try{

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();


			String query = " select User_Id,Holiday_Package_Type,Arrival_City,Destination_City,Package_Price,Package_Price_Included,Travel_Type,Travel_By,Hotel_Budget,"+	
					"Hotel_Stay_Duration,Hotel_Name,Hotel_Stars_Rating,Meal_Preferences,Sight_Seeing,Meal_Included,Place_to_View,Description_Place,"+
					"Package_Title,Inclusion_Hotel,Inclusion_Transport,Inclusion_Others,Package_Itinerary,Special_Requirements,Other_Instructions,"+	
					"Terms_Conditions,Cancellation_Policy,Package_Id,convert(varchar(10),Reg_Date,120) as Reg_Date from Holiday_Package_Details where convert(varchar(10),Reg_Date,120) between ? and ?";

			psmt = con.prepareStatement(query);
			psmt.setString(1,from);
			psmt.setString(2,to);

			rs = psmt.executeQuery();

			while(rs.next()){
				detailsmap = new HashMap();

				detailsmap.put("User_Id", rs.getString(1));
				detailsmap.put("Holiday_Package_Type", rs.getString(2));
				detailsmap.put("Arrival_City", rs.getString(3));
				detailsmap.put("Destination_City", rs.getString(4));
				detailsmap.put("Package_Price", rs.getString(5));
				detailsmap.put("Package_Price_Included", rs.getString(6));
				detailsmap.put("Travel_Type", rs.getString(7));
				detailsmap.put("Travel_By", rs.getString(8));
				detailsmap.put("Hotel_Budget", rs.getString(9));
				detailsmap.put("Hotel_Stay_Duration", rs.getString(10));
				detailsmap.put("Hotel_Name", rs.getString(11));
				detailsmap.put("Hotel_Stars_Rating", rs.getString(12));
				detailsmap.put("Meal_Preferences", rs.getString(13));
				detailsmap.put("Sight_Seeing", rs.getString(14));
				detailsmap.put("Meal_Included", rs.getString(15));
				detailsmap.put("Place_to_View", rs.getString(16));
				detailsmap.put("Description_Place", rs.getString(17));
				detailsmap.put("Package_Title", rs.getString(18));
				detailsmap.put("Inclusion_Hotel", rs.getString(19));
				detailsmap.put("Inclusion_Transport", rs.getString(20));
				detailsmap.put("Inclusion_Others", rs.getString(21));
				detailsmap.put("Package_Itinerary", rs.getString(22));
				detailsmap.put("Special_Requirements", rs.getString(23));
				detailsmap.put("Other_Instructions", rs.getString(24));
				detailsmap.put("Terms_Conditions", rs.getString(25));
				detailsmap.put("Cancellation_Policy", rs.getString(26));
				detailsmap.put("Package_Id", rs.getString(27));
				detailsmap.put("Reg_Date", rs.getString(28));

				details.add(detailsmap);		
			}		
		}	
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return details;
	}


	public String getCityName(String cityCode) 
	{
		String cityName = "N/A";
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();

			String sql = "select upper(cityname) from holiday_pkd_city_details where citycode='"+cityCode+"'" ;
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();
			if(rs.next())
			{
				cityName = rs.getString(1);
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		//	System.out.println("---city code-------"+cityName);
		return cityName;
	}

	public String removeData(String id) 
	{
		String status = "N/A";
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();


			String sql = "delete from Holiday_Package_Details where Package_Id='"+id+"'" ;
			psmt=con.prepareStatement(sql);

			int i = psmt.executeUpdate();
			System.out.println("status---i-"+i);
			if(i > 0)
			{
				status = "success";
				return status;
			}
			else
			{
				status = "failure";
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return status;
	}

	public ArrayList getEditDetails(String id,String date) 
	{
		ArrayList details = new ArrayList<String>();
		HashMap detailsmap = null;

		try{

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();




			String query = " select User_Id,Holiday_Package_Type,Arrival_City,Destination_City,Package_Price,Package_Price_Included,Travel_Type,Travel_By,Hotel_Budget,"+	
					"Hotel_Stay_Duration,Hotel_Name,Hotel_Stars_Rating,Meal_Preferences,Sight_Seeing,Meal_Included,Place_to_View,Description_Place,"+
					"Package_Title,Inclusion_Hotel,Inclusion_Transport,Inclusion_Others,Package_Itinerary,Special_Requirements,Other_Instructions,"+	
					"Terms_Conditions,Cancellation_Policy,Package_Id,convert(varchar(10),Reg_Date,120) as Reg_Date from Holiday_Package_Details "+
					"where Package_Id = ? and convert(varchar(10),Reg_Date,120)  = ?";

			psmt = con.prepareStatement(query);
			psmt.setString(1,id);
			psmt.setString(2,date);

			rs = psmt.executeQuery();

			while(rs.next()){
				detailsmap = new HashMap();

				detailsmap.put("User_Id", rs.getString(1));
				detailsmap.put("Holiday_Package_Type", rs.getString(2));
				detailsmap.put("Arrival_City", rs.getString(3));
				detailsmap.put("Destination_City", rs.getString(4));
				detailsmap.put("Package_Price", rs.getString(5));
				detailsmap.put("Package_Price_Included", rs.getString(6));
				detailsmap.put("Travel_Type", rs.getString(7));
				detailsmap.put("Travel_By", rs.getString(8));
				detailsmap.put("Hotel_Budget", rs.getString(9));
				detailsmap.put("Hotel_Stay_Duration", rs.getString(10));
				detailsmap.put("Hotel_Name", rs.getString(11));
				detailsmap.put("Hotel_Stars_Rating", rs.getString(12));
				detailsmap.put("Meal_Preferences", rs.getString(13));
				detailsmap.put("Sight_Seeing", rs.getString(14));
				detailsmap.put("Meal_Included", rs.getString(15));
				detailsmap.put("Place_to_View", rs.getString(16));
				detailsmap.put("Description_Place", rs.getString(17));
				detailsmap.put("Package_Title", rs.getString(18));
				detailsmap.put("Inclusion_Hotel", rs.getString(19));
				detailsmap.put("Inclusion_Transport", rs.getString(20));
				detailsmap.put("Inclusion_Others", rs.getString(21));
				detailsmap.put("Package_Itinerary", rs.getString(22));
				detailsmap.put("Special_Requirements", rs.getString(23));
				detailsmap.put("Other_Instructions", rs.getString(24));
				detailsmap.put("Terms_Conditions", rs.getString(25));
				detailsmap.put("Cancellation_Policy", rs.getString(26));
				detailsmap.put("Package_Id", rs.getString(27));
				detailsmap.put("Reg_Date", rs.getString(28));

				details.add(detailsmap);		
			}		
		}	
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return details;
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

	public String updateDetails(String packagetype,String packId,String	packprice,String packtitle,String travelBy,String traveltype,String arrivalcitycode,String departcitycode,String hotelBudget, 
			String	hotelDuration,String hotelName,String hotelRating,String mealPrefe,String sightSeeing,String mealIncluded,String placeToView,String placeDescription,String inclHotel,String inclTransport,
			String	incluOthers,String packageItinerary,String spclReqt,String termNCond,String cancPolicy)

	{

		try{

			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();



			String query = " update Holiday_Package_Details set Holiday_Package_Type = ?,Arrival_City = ?,Destination_City = ?,Package_Price = ?," +
					"Travel_Type = ?,Travel_By = ?,Hotel_Budget = ?,Hotel_Stay_Duration = ?,Hotel_Name = ?," +
					"Hotel_Stars_Rating = ?,Meal_Preferences = ?,Sight_Seeing = ?,Meal_Included = ?,Place_to_View = ?,Description_Place = ?,"+
					"Package_Title = ?,Inclusion_Hotel = ?,Inclusion_Transport = ?,Inclusion_Others = ?,Package_Itinerary = ?,Special_Requirements = ?," +
					"Terms_Conditions = ?,Cancellation_Policy = ? where Package_Id = ? ";

			psmt = con.prepareStatement(query);

			psmt.setString(1,packagetype);
			psmt.setString(2,arrivalcitycode);
			psmt.setString(3,departcitycode);
			psmt.setString(4,packprice);
			psmt.setString(5,traveltype);
			psmt.setString(6,travelBy);
			psmt.setString(7,hotelBudget);
			psmt.setString(8,hotelDuration);
			psmt.setString(9,hotelName);
			psmt.setString(10,hotelRating);
			psmt.setString(11,mealPrefe);
			psmt.setString(12,sightSeeing);
			psmt.setString(13,mealIncluded);
			psmt.setString(14,placeToView);
			psmt.setString(15,placeDescription);
			psmt.setString(16,packtitle);
			psmt.setString(17,inclHotel);
			psmt.setString(18,inclTransport);
			psmt.setString(19,incluOthers);
			psmt.setString(20,packageItinerary);
			psmt.setString(21,spclReqt);
			psmt.setString(22,termNCond);
			psmt.setString(23,cancPolicy);
			psmt.setString(24,packId);



			int i = psmt.executeUpdate();

			if(i>0)
			{
				return "success";
			}
			else
			{
				return "failure";
			}

		}	
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();

			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ""	;
	}
}
