package com.controlPanel.SKUmgt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import com.common.HibernateSession;
import com.controlPanel.userActivation.UserActivationAction;
import com.controlPanel.userActivation.UserActivationDao;

public class SKUManagementDao {

	public ArrayList getClientId() 
	{

		UserActivationDao daoObj=new UserActivationDao();
		return daoObj.getPortalIdList();
	}
	
	public ArrayList<String> getCategory() 
	{
		ArrayList data=new ArrayList<String>();
		Session session=null;
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select distinct Category from SKU_MASTER ";
			Query query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				String obj=(String)itr.next();
				data.add(obj);
			}
			System.out.println(data);
		}catch (Exception e) {
			System.out.println("Exception in SKUManagementDao getCategory");
			System.out.println(e.toString());
		}finally{
			session.flush();
			session.close();
		}
		return data;
	}

	public ArrayList<String> getMainService() 
	{
		ArrayList data=new ArrayList<String>();
		Session session=null;
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select distinct Main_Service from SKU_MASTER ";
			Query query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				String obj=(String)itr.next();
				data.add(obj);
			}
			System.out.println(data);
		}catch (Exception e) {
			System.out.println("Exception in SKUManagementDao getMainService");
			System.out.println(e.toString());
		}finally{
			session.flush();
			session.close();
		}
		return data;
	}

	public ArrayList<String> getSubService() {
		ArrayList data=new ArrayList<String>();
		Session session=null;
		try{
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select distinct Sub_Service from SKU_MASTER ";
			Query query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				String obj=(String)itr.next();
				data.add(obj);
			}
			System.out.println(data);
		}catch (Exception e) {
			System.out.println("Exception in SKUManagementDao getSubService");
			System.out.println(e.toString());
		}finally{
			session.flush();
			session.close();
		}
		return data;
		
	}

	public ArrayList getSKUdata(String category, String mainService,String subService,String clientId) 
	{
		ArrayList data=new ArrayList<String>();
		Session session=null;
		String sql="";
		try{
			StringBuilder sb=new StringBuilder();
			session=HibernateSession.getSessionFactory().openSession();
			
			if (!category.equalsIgnoreCase("ALL") && mainService.equalsIgnoreCase("All")&& subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER " +
						"where Category='"+category+"'";
			}
			
			else if (!category.equalsIgnoreCase("ALL") && !mainService.equalsIgnoreCase("All")&& subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER " +
						"where Category='"+category+"' and Main_Service='"+mainService+"'";
			}
			
			else if(category.equalsIgnoreCase("ALL") && mainService.equalsIgnoreCase("All")&& !subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER where " +
						"Sub_Service='"+subService+"'";
			}
			
			else if(category.equalsIgnoreCase("ALL") && !mainService.equalsIgnoreCase("All")&& !subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER where " +
						"Main_Service='"+mainService+"' and Sub_Service='"+subService+"'";
			}
			
			else if(category.equalsIgnoreCase("ALL") && !mainService.equalsIgnoreCase("All")&& subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER " +
						"where Main_Service='"+mainService+"'";
			}
			
			else if(!category.equalsIgnoreCase("ALL") && !mainService.equalsIgnoreCase("All")&& !subService.equalsIgnoreCase("All"))
			{
				sql="select Category,Main_Service,Sub_Service,SKU_Name,SKU_Comm_Type,SKU_Comm,SKU_Charge_Type,SKU_Charge from SKU_MASTER " +
						"where Category='"+category+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"'";
			}
			
			if("All".equalsIgnoreCase(clientId)){
				sb.append(sql).append(" and client_id='10001'");
			}else{
				sb.append(sql).append(" and client_id="+Long.parseLong(clientId));
			}
			sql=sb.toString();
			
			Query query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();
				HashMap map=new HashMap();
				map.put("Category", row[0]);
				map.put("MainService", row[1]);
				map.put("SubService", row[2]);
				map.put("SKUName", row[3]);
				map.put("SKUCommType", row[4]);
				map.put("SKUComm", row[5]);
				map.put("SKUChargeType", row[6]);
				map.put("SKUCharge", row[7]);
				data.add(map);
			}
			System.out.println(data);
		}catch (Exception e) {
			System.out.println("Exception in SKUManagementDao getSubService");
			System.out.println(e.toString());
		}finally{
			session.flush();
			session.close();
		}
		return data;
	}
	
	public String updateSKU(String clientId,String mainService, String subService,String category, String sKUname, String sKUComm,String sKUCommType, String sKUCharge,
			 String sKUChargeType,String userId) 
	{
		String status="";
		Session session=null;
		String sql="";
		try{
			session=HibernateSession.getSessionFactory().openSession();
			
			if("All".equalsIgnoreCase(clientId)){
			
			sql="update SKU_MASTER set SKU_Comm_Type='"+sKUCommType+"',SKU_Comm='"+sKUComm+"',SKU_Charge_Type='"+sKUChargeType+"'," +
					"SKU_Charge='"+sKUCharge+"',updated_date=getdate(),updated_user='"+userId+"' where Category='"+category+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+sKUname+"'";
			}else{
				sql="update SKU_MASTER set SKU_Comm_Type='"+sKUCommType+"',SKU_Comm='"+sKUComm+"',SKU_Charge_Type='"+sKUChargeType+"'," +
						"SKU_Charge='"+sKUCharge+"',updated_date=getdate(),updated_user='"+userId+"' where Category='"+category+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+sKUname+"' and client_id='"+clientId+"'";
				
			}
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			status="Success";
		}catch (Exception e) {
			session.getTransaction().rollback();
			status="fail";
			System.out.println("Exception in SKUManagementDao getSubService");
			System.out.println(e.toString());
		}finally{
			session.close();
		}
		return status;
	}
}
