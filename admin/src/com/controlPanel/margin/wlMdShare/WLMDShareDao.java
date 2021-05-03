package com.controlPanel.margin.wlMdShare;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class WLMDShareDao {

	final ArrayList getWlMDdata(String ClientId,String MDID,String Service,String shareType){

		ArrayList mapList=new ArrayList();
		Session session=null;
		try
		{
			String sql="";
			session = HibernateSession.getSessionFactory().openSession(); 
			sql="select category_Name from white_label_details where client_id='"+ClientId+"'";
			Query query=session.createSQLQuery(sql);
			String category=query.uniqueResult().toString();		

			if("Commission".equalsIgnoreCase(shareType))
			{
				if(MDID.equalsIgnoreCase("All"))
				{
					sql="select a.Client_ID,a.Category,a.MD_ID,a.Main_Service,a.Sub_Service,a.SKU_Name,a.Client_Share,a.MD_Share,convert(decimal(18,2),b.SKU_Comm) as SKU_Comm  from commission_share a,sku_master b where  a.Client_ID='"+ClientId+"' and a.Client_ID=b.Client_ID and a.md_id in (select top 1 Md_Id from md_details where Client_Id='"+ClientId+"') and a.Category='"+category+"' and a.Main_Service='"+Service+"' and a.Category=b.Category and b.Sub_Service=a.Sub_Service and b.SKU_Name=a.SKU_Name and b.Main_Service=a.Main_Service order by a.MD_ID ";				
				}	
				else
				{
					sql="select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+MDID+"'";
					query=session.createSQLQuery(sql);
					String Md_Id=query.uniqueResult().toString();	

					sql="select a.Client_ID,a.Category,a.MD_ID,a.Main_Service,a.Sub_Service,a.SKU_Name,a.Client_Share,a.MD_Share,convert(decimal(18,2),b.SKU_Comm) as SKU_Comm  from commission_share a,sku_master b where  a.Client_ID='"+ClientId+"' and a.Client_ID=b.Client_ID and a.md_id='"+Md_Id+"' and  a.Category='"+category+"' and a.Main_Service='"+Service+"' and a.Category=b.Category and b.Sub_Service=a.Sub_Service and b.SKU_Name=a.SKU_Name and b.Main_Service=a.Main_Service order by a.MD_ID ";
				}
			}else{
				if(MDID.equalsIgnoreCase("All"))
				{
					sql="select a.Client_ID,a.Category,a.MD_ID,a.Main_Service,a.Sub_Service,a.SKU_Name,a.Client_Share,a.MD_Share,convert(decimal(18,2),b.SKU_Charge) as SKU_Comm  from charge_share a,sku_master b where  a.Client_ID='"+ClientId+"' and a.Client_ID=b.Client_ID and a.md_id in (select top 1 Md_Id from md_details where Client_Id='"+ClientId+"') and a.Category='"+category+"' and a.Main_Service='"+Service+"' and a.Category=b.Category and b.Sub_Service=a.Sub_Service and b.SKU_Name=a.SKU_Name and b.Main_Service=a.Main_Service order by a.MD_ID ";				
				}	
				else
				{
					sql="select md_id from md_details where md_initial+convert(varchar(10),md_id)='"+MDID+"'";
					query=session.createSQLQuery(sql);
					String Md_Id=query.uniqueResult().toString();	

					sql="select a.Client_ID,a.Category,a.MD_ID,a.Main_Service,a.Sub_Service,a.SKU_Name,a.Client_Share,a.MD_Share,convert(decimal(18,2),b.SKU_Charge) as SKU_Comm  from charge_share a,sku_master b where  a.Client_ID='"+ClientId+"' and a.Client_ID=b.Client_ID and a.md_id='"+Md_Id+"' and  a.Category='"+category+"' and a.Main_Service='"+Service+"' and a.Category=b.Category and b.Sub_Service=a.Sub_Service and b.SKU_Name=a.SKU_Name and b.Main_Service=a.Main_Service order by a.MD_ID ";
				}
			}
			Query query2=session.createSQLQuery(sql);
			List list=query2.list();
			Iterator itr=list.iterator();
			Object [] row;
			while(itr.hasNext())
			{
				HashMap mapdata=new HashMap();
				row=(Object[])itr.next();
				mapdata.put("Client_ID", row[0]);
				mapdata.put("Category", row[1]);
				mapdata.put("MD_ID", row[2]);
				mapdata.put("Main_Service", row[3]);
				mapdata.put("Sub_Service", row[4]);
				mapdata.put("SKU_Name", row[5]);	
				mapdata.put("Client_Share", row[6]);
				mapdata.put("SKU_Comm", row[8]);
				mapdata.put("MD_Share", row[7]);
				BigDecimal i=new BigDecimal("100");				
				BigDecimal j=new BigDecimal("1");				
				BigDecimal SKUComm=(BigDecimal)row[8];			
				BigDecimal MDShare=(BigDecimal)row[7];
				BigDecimal MDSMargin=((MDShare.divide(i,2,6).multiply(SKUComm))).divide(j,2,6);				
				mapdata.put("MDSMargin", MDSMargin);				
				mapList.add(mapdata);				
			}			
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in WLMDShareDao,getWlMDdata ");
			e.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("Exception in WLMDShareDao,getWlMDdata ");
			e.printStackTrace();
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in WLMDShareDao,getWlMDdata ");
				e.printStackTrace();
			}
		}
		return mapList;
	}

	public  static ArrayList getMdid(String enterUserID,String userId,String loginType) 
	{
		System.out.println("In Dao");
		Session session=null;

		ArrayList mapList=new ArrayList();

		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=null;
			int id=Integer.parseInt(enterUserID);
			//System.out.println(" Dao id"+id);

			String sql;
			//String client_id=(String)session.createSQLQuery(sql).uniqueResult();
			if(!loginType.equalsIgnoreCase("superadmin"))
			{
				sql="select md_initial+convert(varchar(10),md_id) from md_details where client_id='"+id+"'";
			}
			else
			{
				sql="select md_initial+convert(varchar(10),md_id) from md_details where client_id=(select Portal_id from dbo.Admin_User_details where User_id='"+userId+"' and Portal_id='"+id+"')";
			}
			System.out.println(sql);

			query=session.createSQLQuery(sql);
			List list=query.list();

			Iterator itr=list.iterator();

			while(itr.hasNext())
			{
				String row=(String)itr.next();	
				//System.out.println(row.toString());
				mapList.add(row.toString());	
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				session.close();

			} catch (Exception e2)
			{
			}
		}
		return mapList;
	}

	public String getCategory(String ClientID) {
		String category_Name="";
		Session session=null;
		String sql="";
		try{
			session=HibernateSession.getSessionFactory().openSession();

			sql="select category_Name from white_label_details where Client_Id='"+ClientID+"'";
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);			
			category_Name=query.uniqueResult().toString();	
		}catch (Exception e) {
			e.printStackTrace();			
			System.out.println("Exception in WLMDShareDao getCategory");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return category_Name;
	}
	public String UpdateShareAllMD(String mainService, String subService,
			String SKUname, String clientShare, String mdShare,
			String ClientID, String cat,String shareType) {
		String status="fail";
		Session session=null;
		String sql="";
		try{
			session=HibernateSession.getSessionFactory().openSession();
			
			if("Commission".equalsIgnoreCase(shareType))
				sql="update commission_share set Client_Share='"+clientShare+"',MD_Share='"+mdShare+"' where Client_ID='"+ClientID+"' and Category='"+cat+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+SKUname+"'";
			else
				sql="update Charge_share set Client_Share='"+clientShare+"',MD_Share='"+mdShare+"' where Client_ID='"+ClientID+"' and Category='"+cat+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+SKUname+"'";
			
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			status="Success";
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			status="fail";
			System.out.println("Exception in WLMDShareDao UpdateShare");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	public String UpdateShare(String mainService, String subService,
			String SKUname, String clientShare, String mdShare,
			String ClientID, String md_id, String cat,String shareType) {
		String status="fail";
		Session session=null;
		String sql="";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();

			if("Commission".equalsIgnoreCase(shareType))
				sql="update commission_share set Client_Share='"+clientShare+"',MD_Share='"+mdShare+"' where Client_ID='"+ClientID+"' and MD_ID='"+md_id+"' and Category='"+cat+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+SKUname+"'";
			else
				sql="update Charge_share set Client_Share='"+clientShare+"',MD_Share='"+mdShare+"' where Client_ID='"+ClientID+"' and MD_ID='"+md_id+"' and Category='"+cat+"' and Main_Service='"+mainService+"' and Sub_Service='"+subService+"' and SKU_Name='"+SKUname+"'";
			
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
			status="Success";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			session.getTransaction().rollback();
			status="fail";
			System.out.println("Exception in WLMDShareDao UpdateShare");
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return status;
	}
	

}
