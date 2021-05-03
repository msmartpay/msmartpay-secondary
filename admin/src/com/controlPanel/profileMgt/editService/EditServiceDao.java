package com.controlPanel.profileMgt.editService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.common.HibernateSession;

public class EditServiceDao {

	public HashMap<String,String> getAgentService(String editUserId) {
		Session session=null;
		HashMap<String,String> map=new HashMap<String,String>();
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select Flights,Bus,Hotel,Recharge,DTHX,Utility,PayCard,TestMerit,SmsTxn_Status,rail,Wallet_to_Wallet,Holidays,DMR from agent_details where agent_initial+convert(varchar(10),agent_id)='"+editUserId+"'";
			//System.out.println(sql);
			List list=session.createSQLQuery(sql).list();
			
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				Object[] obj=(Object[])itr.next();
				//System.out.println("we are inside");
				map.put("Flights", obj[0]==null?"N":obj[0].toString());
				map.put("Bus", obj[1]==null?"N":obj[1].toString());
				map.put("Hotel", obj[2]==null?"N":obj[2].toString());
				map.put("Recharge", obj[3]==null?"N":obj[3].toString());
				map.put("DTHX", obj[4]==null?"N":obj[4].toString());
				map.put("Utility", obj[5]==null?"N":obj[5].toString());
				map.put("PayCard", obj[6]==null?"N":obj[6].toString());
				map.put("TestMerit", obj[7]==null?"N":obj[7].toString());
				map.put("SMS_TXN", obj[8]==null?"N":obj[8].toString());
				map.put("rail", obj[9]==null?"N":obj[9].toString());
				map.put("WW", obj[10]==null?"N":obj[10].toString());
				map.put("Holidays",obj[11]==null?"N":obj[11].toString());
				map.put("DMR", obj[12]==null?"N":obj[12].toString());
				
				
			}
			//System.out.println(map);
		}catch (Exception e) {
			System.out.println("Exception in EditServiceDao,getAgentService");
			System.out.println(e.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return map;
	}

	public String updateAgentService(List<String> editUserId, String flights,String bus, String hotel, String recharge, String utility,
			String payCard, String testMerit, String dTHX, String rail,
			String sMS_TXN,String WW,String Holidays,String DMR) 
	{
		
		Session session=null;
		String status="fail";
		PreparedStatement pstmt=null;
		Connection con=null;
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			session.beginTransaction();
			String sql="update agent_details set Flights='"+flights+"',Bus='"+bus+"',Hotel='"+hotel+"',Recharge='"+recharge+"',DTHX='"+dTHX+"',Utility='"+utility+"',PayCard='"+payCard+"',TestMerit='"+testMerit+"',SmsTxn_Status='"+sMS_TXN+"',rail='"+rail+"',Wallet_to_Wallet='"+WW+"',Holidays='"+Holidays+"',DMR='"+DMR+"' where agent_initial+convert(varchar(10),agent_id)=?";
			pstmt=con.prepareStatement(sql);
			System.out.println(sql);
			for (int i = 0; i < editUserId.size(); i++) 
			{
				System.out.println("Statment added >>>>>> : "+i);
				pstmt.setString(1, editUserId.get(i));
				pstmt.addBatch();
			}
			//session.createSQLQuery(sql).executeUpdate();
			
			int[] affectedRow=pstmt.executeBatch();
			System.out.println("Update  : "+affectedRow+" has been updated.");
			session.getTransaction().commit();
			status="success";
			
		}
		catch (Exception e) 
		{
			status="fail";
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.close();
				session.flush();
				session.close();
				pstmt.clearBatch();
				pstmt.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return status;
	}

	public HashMap<String, String> getDSService(String editUserId) {
		Session session=null;
		HashMap<String,String> map=new HashMap<String,String>();
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="SELECT COUNT(DISTINCT Flights) as Flights,COUNT(DISTINCT Bus) as Bus, COUNT(DISTINCT Hotel) as Hotel, COUNT(DISTINCT Recharge) as Recharge, COUNT(DISTINCT DTHX) as DTHX, COUNT(DISTINCT Utility) as Utility, COUNT(DISTINCT PayCard) as PayCard, COUNT(DISTINCT TestMerit) as TestMerit, COUNT(DISTINCT SmsTxn_Status) as SmsTxn_Status, COUNT(DISTINCT rail) as rail FROM agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
			//System.out.println(sql);
			List list=session.createSQLQuery(sql).list();
			
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				Object[] obj=(Object[])itr.next();
				//System.out.println("we are inside");
				String Flights=obj[0].toString();
				//System.out.println(Flights);
				if(Flights.equalsIgnoreCase("1")){
					sql="select top 1 Flights from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("Flights", status);
				}else{
					map.put("Flights", "Select");
				}
				String Bus=obj[1].toString();
				//System.out.println(Bus);
				if(Bus.equalsIgnoreCase("1")){
					sql="select top 1 Bus from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("Bus", status);
				}else{
					map.put("Bus", "Select");
				}
				String Hotel=obj[2].toString();
				//System.out.println(Hotel);
				if(Bus.equalsIgnoreCase("1")){
					sql="select top 1 Hotel from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("Hotel", status);
				}else{
					map.put("Hotel", "Select");
				}
				String Recharge=obj[3].toString();
				//System.out.println(Recharge);
				if(Recharge.equalsIgnoreCase("1")){
					sql="select top 1 Recharge from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("Recharge", status);
				}else{
					map.put("Recharge", "Select");
				}
				String DTHX=obj[4].toString();
				//System.out.println(DTHX);
				if(DTHX.equalsIgnoreCase("1")){
					sql="select top 1 DTHX from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("DTHX", status);
				}else{
					map.put("DTHX", "Select");
				}
				String Utility=obj[5].toString();
				//System.out.println(Utility);
				if(Utility.equalsIgnoreCase("1")){
					sql="select top 1 Utility from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("Utility", status);
				}else{
					map.put("Utility", "Select");
				}
				String PayCard=obj[6].toString();
				//System.out.println(PayCard);
				if(PayCard.equalsIgnoreCase("1")){
					sql="select top 1 PayCard from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("PayCard", status);
				}else{
					map.put("PayCard", "Select");
				}
				String TestMerit=obj[7].toString();
				//System.out.println(TestMerit);
				if(PayCard.equalsIgnoreCase("1")){
					sql="select top 1 TestMerit from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("TestMerit", status);
				}else{
					map.put("TestMerit", "Select");
				}
				String SMS_TXN=obj[8].toString();
				//System.out.println(SMS_TXN);
				if(PayCard.equalsIgnoreCase("1")){
					sql="select top 1 SmsTxn_Status from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("SMS_TXN", status);
				}else{
					map.put("SMS_TXN", "Select");
				}
				String rail=obj[9].toString();
				//System.out.println(rail);
				if(rail.equalsIgnoreCase("1")){
					sql="select top 1 rail from agent_details where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
					String status=session.createSQLQuery(sql).uniqueResult().toString();
					map.put("rail", status);
				}else{
					map.put("rail", "Select");
				}
			}
			//System.out.println(map);
		}catch (Exception e) {
			System.out.println("Exception in EditServiceDao,getDSService");
			System.out.println(e.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return map;
	}

	public String updateDSService(String editUserId, String flights,String bus, String hotel, String recharge, String utility,
			String payCard, String testMerit, String dTHX, String rail,
			String sMS_TXN) {
		Session session=null;
		String status="fail";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			session.beginTransaction();
			String sql="update agent_details set Flights='"+flights+"',Bus='"+bus+"',Hotel='"+hotel+"',Recharge='"+recharge+"',DTHX='"+dTHX+"',Utility='"+utility+"',PayCard='"+payCard+"',TestMerit='"+testMerit+"',SmsTxn_Status='"+sMS_TXN+"',rail='"+rail+"' where agent_id in (select agent_id from agent_details where distributor_id in (select distributor_id from distributor_details where distributor_initial+convert(varchar(10),distributor_id)='"+editUserId+"'))";
			//System.out.println(sql);
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
			status="success";
			
		}catch (Exception e) {
			status="fail";
			session.getTransaction().rollback();
			System.out.println("Exception in EditServiceDao,updateDSService");
			System.out.println(e.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return status;
	}

	public HashMap<String, String> getMDSService(String editUserId) {
		Session session=null;
		HashMap<String,String> map=new HashMap<String,String>();
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select Flights,Bus,Hotel,Recharge,DTHX,Utility,PayCard,TestMerit,SmsTxn_Status,rail,Wallet_to_Wallet,Holidays,DMR from md_details where MD_initial+convert(varchar(10),MD_id)='"+editUserId+"'";
			//String sql="select Flights,Bus,Hotel,Recharge,DTHX,Utility,PayCard,TestMerit,SmsTxn_Status,rail,Wallet_to_Wallet,Holidays,DMR from agent_details where agent_initial+convert(varchar(10),agent_id)='"+editUserId+"'";
			//System.out.println(sql);
			List list=session.createSQLQuery(sql).list();
			
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				Object[] obj=(Object[])itr.next();
				//System.out.println("we are inside");
				map.put("Flights", obj[0]==null?"N":obj[0].toString());
				map.put("Bus", obj[1]==null?"N":obj[1].toString());
				map.put("Hotel", obj[2]==null?"N":obj[2].toString());
				map.put("Recharge", obj[3]==null?"N":obj[3].toString());
				map.put("DTHX", obj[4]==null?"N":obj[4].toString());
				map.put("Utility", obj[5]==null?"N":obj[5].toString());
				map.put("PayCard", obj[6]==null?"N":obj[6].toString());
				map.put("TestMerit", obj[7]==null?"N":obj[7].toString());
				map.put("SMS_TXN", obj[8]==null?"N":obj[8].toString());
				map.put("rail", obj[9]==null?"N":obj[9].toString());
				map.put("WW", obj[10]==null?"N":obj[10].toString());
				map.put("Holidays",obj[11]==null?"N":obj[11].toString());
				map.put("DMR", obj[12]==null?"N":obj[12].toString());
				
			}
			//System.out.println(map);
		}
		catch (Exception e) 
		{
			System.out.println("Exception in EditServiceDao getMDSService ()");
			System.out.println(e.toString());
		}
		finally
		{
			session.flush();
			session.close();
		}
		return map;
	}

	public ArrayList<HashMap<String, String>> getMDSOperatorStatus(String editUserId, String subservice, String operator) {
		Session session=null;
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		String service="Billpay";
		
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="";
			if(subservice.equalsIgnoreCase("mobile")|| subservice.equalsIgnoreCase("dth")|| subservice.equalsIgnoreCase("datacard")){
				service="Recharge";
			}
			if(operator.equalsIgnoreCase("All")){
				sql="select Sub_service,Operator_Name,Status from Operator_Status_MD where service='"+service+"' and Sub_service='"+subservice+"' and md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+editUserId+"')";
			}else{
				sql="select Sub_service,Operator_Name,Status from Operator_Status_MD where service='"+service+"' and Sub_service='"+subservice+"' and Operator_Name='"+operator+"' and md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+editUserId+"')";
			}
			System.out.println(sql);
			List list=session.createSQLQuery(sql).list();
			
			Iterator itr=list.iterator();
			while(itr.hasNext()){
				HashMap<String,String> map=new HashMap<String,String>();
				Object[] obj=(Object[])itr.next();
				map.put("subService", obj[0].toString());
				map.put("OperatorName", obj[1].toString());
				map.put("status", obj[2].toString());
				listData.add(map);
			}
			//System.out.println(listData);
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
		return listData;
	}

	public String updateMDSService(List<String> editUserId, String flights,
			String bus, String hotel, String recharge, String utility,
			String payCard, String testMerit, String dTHX, String rail,
			String sMS_TXN ,String WW,String Holidays,String DMR) {
		Session session=null;
		String status="fail";
		PreparedStatement pstmt=null;
		Connection con=null;
		
		try{
			session=HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			session.beginTransaction();
			String sql="update md_details set Flights='"+flights+"',Bus='"+bus+"',Hotel='"+hotel+"',Recharge='"+recharge+"',DTHX='"+dTHX+"',Utility='"+utility+"',PayCard='"+payCard+"',TestMerit='"+testMerit+"',SmsTxn_Status='"+sMS_TXN+"',rail='"+rail+"',Wallet_to_Wallet='"+WW+"',Holidays='"+Holidays+"',DMR='"+DMR+"' where MD_initial+convert(varchar(10),MD_id)=?";
			pstmt=con.prepareStatement(sql);
			for (int i = 0; i < editUserId.size(); i++) 
			{
				System.out.println("Statment added >>>>>> : "+i);
				pstmt.setString(1, editUserId.get(i));
				pstmt.addBatch();
			}
			
			int[] affectedRow=pstmt.executeBatch();
			System.out.println("Update  : "+affectedRow+" has been updated.");
			status="success";
			session.getTransaction().commit();
		}
		catch (Exception e) 
		{
			status="fail";
			session.getTransaction().rollback();
			System.out.println("Exception in EditServiceDao updateMDSService ()");
			e.printStackTrace();
		}
		finally
		{
			try {
				pstmt.close();
				session.flush();
				session.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return status;
	}
	public String updateMDSOperator(String editUserId, String service, String opName, String mark, String subService) {
		Session session=null;
		String status="fail";
		try{
			session=HibernateSession.getSessionFactory().openSession();
			session.beginTransaction();
			String sql="update Operator_Status_MD set Status='"+mark+"' where service='"+service+"' and Sub_service='"+subService+"' and Operator_Name='"+opName+"' and  md_id in (select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+editUserId+"')";
			//System.out.println(sql);
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
			status="success";
			
		}catch (Exception e) {
			status="fail";
			session.getTransaction().rollback();
			System.out.println("Exception in EditServiceDao updateMDSService ()");
			e.printStackTrace();
		}
		finally
		{
			session.flush();
			session.close();
		}
		return status;
	}
}
