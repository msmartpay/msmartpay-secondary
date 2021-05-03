package com.controlPanel.inventoryMgt.holidayPackg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.hibernate.Session;
import com.common.HibernateSession;


public class HolidayPackageDao
{
	private static HolidayPackageDao hPackageDao=null;
	private static Connection con = null;
	private static PreparedStatement psmt = null;
	private static ResultSet rs = null;
	Session session;

	public static HolidayPackageDao getInstance() 
	{
		if(hPackageDao==null)
			hPackageDao=new HolidayPackageDao();

		return hPackageDao;
	}
	public String saveHolidayDetails(HolidayPackageForm holidayPackageForm) 
	{
		String status="failure";
		System.out.println("In Holiday Dao");


		try 
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "insert into Holiday_Package_Details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			psmt=con.prepareStatement(sql);

			psmt.setLong(1, Long.parseLong((holidayPackageForm.getUserId().toString())));
			psmt.setString(2, holidayPackageForm.getPackageType());
			psmt.setString(3, holidayPackageForm.getArrivalCity());
			psmt.setString(4,  holidayPackageForm.getDepartCity());
			psmt.setString(5,  holidayPackageForm.getPackagePrice());
			psmt.setString(6,  holidayPackageForm.getPackagePriceIncludes());
			psmt.setString(7, holidayPackageForm.getTravelType());
			psmt.setString(8,  holidayPackageForm.getTravelBy());
			psmt.setString(9, holidayPackageForm.getHotelBudget());
			psmt.setString(10, holidayPackageForm.getDuration());
			psmt.setString(11, holidayPackageForm.getHotelName());
			psmt.setString(12, holidayPackageForm.getStar2());
			psmt.setString(13, holidayPackageForm.getMealPref());
			psmt.setString(14, holidayPackageForm.getSightSeen());
			psmt.setString(15, holidayPackageForm.getMealDishes());
			psmt.setString(16, holidayPackageForm.getUserImageFileName());
			psmt.setString(17, holidayPackageForm.getPlaceToView());
			psmt.setString(18, holidayPackageForm.getPlaceDescription());
			psmt.setString(19, holidayPackageForm.getPackageTitle());
			psmt.setString(20, holidayPackageForm.getInclusionHotel());
			psmt.setString(21, holidayPackageForm.getInclusionTransport());
			psmt.setString(22, holidayPackageForm.getInclusionOthers());
			psmt.setString(23, holidayPackageForm.getPackageInitierary());
			psmt.setString(24, holidayPackageForm.getSpecialReqt());
			psmt.setString(25, holidayPackageForm.getOtherInst());
			psmt.setString(26, holidayPackageForm.getTermCond());
			psmt.setString(27, holidayPackageForm.getCancelPolicy());
			psmt.setInt(28, holidayPackageForm.getPkgId());
			psmt.setString(29, holidayPackageForm.getDatetime());

			int check = psmt.executeUpdate();
			if(check > 0)
			{
				status="success";
			}
			else
			{
				status="failure";
			}
		} 
		catch (Exception e) 
		{
			status="failure";

			e.printStackTrace();
			return status;
		}
		finally
		{

			try {
				psmt.close();
				con.close();
				session.close();
			} catch (Exception e2) {
				// TODO: handle exception
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
				// TODO: handle exception
			}
		}
		System.out.println("---city code-------"+citycode);
		return citycode;
	}

	public ArrayList<HashMap<String, String>> cityDetails()
	{

		ArrayList cityList = new ArrayList();

		HashMap cL = new HashMap();

		try
		{
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			String sql = "select distinct cityname,citycode from holiday_pkd_city_details order by cityname " ;
			psmt=con.prepareStatement(sql);
			rs=psmt.executeQuery();

			while(rs.next())
			{
				cL = new HashMap();
				cL.put("citylist", rs.getString(1));
				cL.put("citycode", rs.getString(2));
				cityList.add(cL);
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
				// TODO: handle exception
			}
		}

		return cityList;
	}

	public String saveImagePath(String path,int randomInt)
	{
		System.out.println("----------image path-----------"+path);
		String status="N/A";
		String sql="update table holiday_package_details set Image_Path=? where Package_Id=?";
		try {
			session = HibernateSession.getSessionFactory().openSession();
			con = session.connection();
			psmt=con.prepareStatement(sql);
			psmt.setString(1, path);
			psmt.setInt(2, randomInt);
			int i = psmt.executeUpdate();

			if(i>0)
			{
				status="success";
			}
			else
			{
				status="error";
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("-Exception in saving image path---");
			System.out.println("-Problem in saving Image path---");
		}finally {
			try {
				rs.close();
				psmt.close();
				con.close();
				session.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return status;
	}
}
