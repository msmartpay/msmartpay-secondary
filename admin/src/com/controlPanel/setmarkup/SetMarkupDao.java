package com.controlPanel.setmarkup;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



import com.common.HibernateSession;

public class SetMarkupDao 
{
	private static SetMarkupDao setMarkupDao=null;

	public static SetMarkupDao getInstance() 
	{
		if(setMarkupDao==null)
			setMarkupDao=new SetMarkupDao();
		
		return setMarkupDao;
	}
	
	public SetMarkupForm getMarkupDetailForAll(SetMarkupForm setMarkupForm,String initial,String service) 
	{
		HashMap map=null;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			setMarkupForm.setService(service);
			ses=HibernateSession.getSessionFactory().openSession();
			if(initial.equalsIgnoreCase("CL"))
			{
				
				sql="select market_limit,service,client_id,client_markup,mds_markup,ds_markup,agent_markup from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and client_id='"+setMarkupForm.getClientId()+"' and md_id='all' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				int count=0;
				if(list.size()>0)
				{
					while(it.hasNext())
					{
						Object[] row=(Object[])it.next();
						setMarkupForm.setMarketLimit(Double.parseDouble(""+(BigDecimal)row[0]));
						setMarkupForm.setService((String)row[1]);
						setMarkupForm.setClientId((String)row[2]);
						setMarkupForm.setClientMarkup(Double.parseDouble(""+(BigDecimal)row[3]));
						setMarkupForm.setMdsMarkup(Double.parseDouble(""+(BigDecimal)row[4]));
						setMarkupForm.setDsMarkup(Double.parseDouble(""+(BigDecimal)row[5]));
						setMarkupForm.setAgentMarkup(Double.parseDouble(""+(BigDecimal)row[6]));
						setMarkupForm.setClientInitial(initial);
						System.out.println("Success");
					}
				}
				else
				{
					setMarkupForm.setMarketLimit(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					setMarkupForm.setClientId("all");
					setMarkupForm.setClientInitial(initial);
				}
			}
			if(initial.equalsIgnoreCase("MD"))
			{
				sql="select market_limit,service,md_id,client_markup,mds_markup,ds_markup,agent_markup from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and md_id='"+setMarkupForm.getMdId()+"' and client_id='all' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				int count=0;
				if(list.size()>0)
				{
					while(it.hasNext())
					{
						Object[] row=(Object[])it.next();
						setMarkupForm=new SetMarkupForm();
						
						setMarkupForm.setMarketLimit(Double.parseDouble(""+(BigDecimal)row[0]));
						setMarkupForm.setService((String)row[1]);
						setMarkupForm.setMdId((String)row[2]);
						setMarkupForm.setClientMarkup(Double.parseDouble(""+(BigDecimal)row[3]));
						setMarkupForm.setMdsMarkup(Double.parseDouble(""+(BigDecimal)row[4]));
						setMarkupForm.setDsMarkup(Double.parseDouble(""+(BigDecimal)row[5]));
						setMarkupForm.setAgentMarkup(Double.parseDouble(""+(BigDecimal)row[6]));
						setMarkupForm.setClientInitial(initial);
						System.out.println("Success");
					}
				}
				else
				{
					setMarkupForm.setMarketLimit(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					setMarkupForm.setMdId("all");
					setMarkupForm.setClientInitial(initial);
				}
			}
			if(initial.equalsIgnoreCase("DS"))
			{
				sql="select market_limit,service,ds_id,client_markup,mds_markup,ds_markup,agent_markup from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and ds_id='"+setMarkupForm.getDsId()+"' and client_id='"+setMarkupForm.getClientId()+"' and md_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				int count=0;
				if(list.size()>0)
				{
					while(it.hasNext())
					{
						Object[] row=(Object[])it.next();
						setMarkupForm=new SetMarkupForm();
						
						setMarkupForm.setMarketLimit(Double.parseDouble(""+(BigDecimal)row[0]));
						setMarkupForm.setService((String)row[1]);
						setMarkupForm.setDsId((String)row[2]);
						setMarkupForm.setClientMarkup(Double.parseDouble(""+(BigDecimal)row[3]));
						setMarkupForm.setMdsMarkup(Double.parseDouble(""+(BigDecimal)row[4]));
						setMarkupForm.setDsMarkup(Double.parseDouble(""+(BigDecimal)row[5]));
						setMarkupForm.setAgentMarkup(Double.parseDouble(""+(BigDecimal)row[6]));
						setMarkupForm.setClientInitial(initial);
					}
				}
				else
				{
					setMarkupForm.setMarketLimit(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					setMarkupForm.setDsId("all");
					setMarkupForm.setClientInitial(initial);
				}
			}
			if(initial.equalsIgnoreCase("AG"))
			{
				sql="select market_limit,service,agent_id,client_markup,mds_markup,ds_markup,agent_markup from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and agent_id='"+setMarkupForm.getAgentId()+"' and client_id='"+setMarkupForm.getClientId()+"' and md_id='all' and ds_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				int count=0;
				if(list.size()>0)
				{
					while(it.hasNext())
					{
						Object[] row=(Object[])it.next();
						setMarkupForm=new SetMarkupForm();
						
						setMarkupForm.setMarketLimit(Double.parseDouble(""+(BigDecimal)row[0]));
						setMarkupForm.setService((String)row[1]);
						setMarkupForm.setAgentId((String)row[2]);
						setMarkupForm.setClientMarkup(Double.parseDouble(""+(BigDecimal)row[3]));
						setMarkupForm.setMdsMarkup(Double.parseDouble(""+(BigDecimal)row[4]));
						setMarkupForm.setDsMarkup(Double.parseDouble(""+(BigDecimal)row[5]));
						setMarkupForm.setAgentMarkup(Double.parseDouble(""+(BigDecimal)row[6]));
						setMarkupForm.setClientInitial(initial);
					}
				}
				else
				{
					setMarkupForm.setMarketLimit(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					setMarkupForm.setAgentId("all");
					setMarkupForm.setClientInitial(initial);
				}
			}
			ses.flush();
			ses.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return setMarkupForm;
	}
	
	public String saveMarkupDetailForAll(SetMarkupForm setMarkupForm) 
	{
		String status="failure";
		Connection con=null;
		Statement stmt=null;
		String user_id="";
		String client_id="";
		Session ses=null;
		Session ses2=null;
		Transaction tx=null;
		String sql="";
		String sql2="";
		List list;
		Iterator it;
		Query query;
		try 
		{
		
			ses=HibernateSession.getSessionFactory().openSession();
			if(setMarkupForm.getClientInitial().equalsIgnoreCase("CL"))
			{
				sql="select * from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and client_id='"+setMarkupForm.getClientId()+"' and md_id='all' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					
						con=ses.connection();
						tx=ses.beginTransaction();
								
						stmt=con.createStatement();
						String sql3="update markup_details set market_limit="+setMarkupForm.getMarketLimit()+",client_markup="+setMarkupForm.getClientMarkup()+" where setBy_user_id="+setMarkupForm.getSetByUserId()+" and user_id="+setMarkupForm.getUserId()+" and client_id='"+setMarkupForm.getClientId()+"' and md_id='all' and ds_id='all' and agent_id='all' and service='"+setMarkupForm.getService()+"'";
						int rw=stmt.executeUpdate(sql3);
						if(rw>0)
							System.out.println("Update Success");
						else
							System.out.println("Update Failure");
						tx.commit();
						
					
				}
				else
				{
					setMarkupForm.setAgentId("all");
					setMarkupForm.setMdId("all");
					setMarkupForm.setDsId("all");
					
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					ses2=HibernateSession.getSessionFactory().openSession();
												
					tx=ses2.beginTransaction();
					ses2.save(setMarkupForm);
					tx.commit();
				}
				status="success";
								
			}
			
			if(setMarkupForm.getClientInitial().equalsIgnoreCase("MD"))
			{
				
				sql="select * from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and md_id='"+setMarkupForm.getMdId()+"' and client_id='all' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					
						con=ses.connection();
						tx=ses.beginTransaction();
								
						stmt=con.createStatement();
						String sql3="update markup_details set mds_markup="+setMarkupForm.getMdsMarkup()+" where setBy_user_id="+setMarkupForm.getSetByUserId()+" and user_id="+setMarkupForm.getUserId()+" and md_id='"+setMarkupForm.getMdId()+"' and client_id='all' and ds_id='all' and agent_id='all' and service='"+setMarkupForm.getService()+"'";
						int rw=stmt.executeUpdate(sql3);
						if(rw>0)
							System.out.println("Update Success");
						else
							System.out.println("Update Failure");
						tx.commit();
						
					
				}
				else
				{
					setMarkupForm.setAgentId("all");
					setMarkupForm.setClientId("all");
					setMarkupForm.setDsId("all");
					
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					ses2=HibernateSession.getSessionFactory().openSession();
												
					tx=ses2.beginTransaction();
					ses2.save(setMarkupForm);
					tx.commit();
				}
				status="success";
			}
			if(setMarkupForm.getClientInitial().equalsIgnoreCase("DS"))
			{
				sql="select * from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and ds_id='"+setMarkupForm.getDsId()+"' and md_id='all' and client_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					
						con=ses.connection();
						tx=ses.beginTransaction();
								
						stmt=con.createStatement();
						String sql3="update markup_details set ds_markup="+setMarkupForm.getDsMarkup()+" where setBy_user_id="+setMarkupForm.getSetByUserId()+" and user_id="+setMarkupForm.getUserId()+" and ds_id='"+setMarkupForm.getDsId()+"' and md_id='all' and client_id='all' and agent_id='all' and service='"+setMarkupForm.getService()+"'";
						int rw=stmt.executeUpdate(sql3);
						if(rw>0)
							System.out.println("Update Success");
						else
							System.out.println("Update Failure");
						tx.commit();
						
					
				}
				else
				{
					setMarkupForm.setAgentId("all");
					setMarkupForm.setMdId("all");
					setMarkupForm.setClientId("all");
					
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					ses2=HibernateSession.getSessionFactory().openSession();
												
					tx=ses2.beginTransaction();
					ses2.save(setMarkupForm);
					tx.commit();
				}
				status="success";
			}
			if(setMarkupForm.getClientInitial().equalsIgnoreCase("AG"))
			{
				sql="select * from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and agent_id='"+setMarkupForm.getAgentId()+"' and md_id='all' and ds_id='all' and client_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					
						con=ses.connection();
						tx=ses.beginTransaction();
								
						stmt=con.createStatement();
						String sql3="update markup_details set agent_markup="+setMarkupForm.getAgentMarkup()+" where setBy_user_id="+setMarkupForm.getSetByUserId()+" and user_id="+setMarkupForm.getUserId()+" and agent_id='"+setMarkupForm.getAgentId()+"' and md_id='all' and ds_id='all' and client_id='all' and service='"+setMarkupForm.getService()+"'";
						int rw=stmt.executeUpdate(sql3);
						if(rw>0)
							System.out.println("Update Success");
						else
							System.out.println("Update Failure");
						tx.commit();
						
					
				}
				else
				{
					setMarkupForm.setClientId("all");
					setMarkupForm.setMdId("all");
					setMarkupForm.setDsId("all");
					
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setClientMarkup(0);
					ses2=HibernateSession.getSessionFactory().openSession();
												
					tx=ses2.beginTransaction();
					ses2.save(setMarkupForm);
					tx.commit();
				}
				status="success";
			}
		
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return "failure";
		}
		finally
		{
			try
			{
				if(ses!=null)
				{
					ses.flush();
					ses.close();
				}
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(ses2!=null)
					ses2.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public SetMarkupForm getMarkupDetailForIndividual(SetMarkupForm setMarkupForm,String initial,String service,boolean isSuperAdmin) 
	{
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			setMarkupForm.setService(service);
			ses=HibernateSession.getSessionFactory().openSession();
			if(initial.equalsIgnoreCase("CL"))
			{
				String clientId=getSingleClientId(setMarkupForm.getMdId());
				if(isSuperAdmin)
					sql="select market_limit,service,client_id,client_markup,mds_markup,ds_markup,agent_markup,user_id from dbo.markup_details where md_id='"+setMarkupForm.getMdId()+"' and ds_id='all' and agent_id='all' and service='"+setMarkupForm.getService()+"'";
				else
					sql="select market_limit,service,client_id,client_markup,mds_markup,ds_markup,agent_markup,user_id from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and md_id='"+setMarkupForm.getMdId()+"' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				int count=0;
				if(list.size()>0)
				{
					while(it.hasNext())
					{
						Object[] row=(Object[])it.next();
						setMarkupForm.setMarketLimit(Double.parseDouble(""+(BigDecimal)row[0]));
						setMarkupForm.setService((String)row[1]);
						setMarkupForm.setClientId((String)row[2]);
						setMarkupForm.setClientMarkup(Double.parseDouble(""+(BigDecimal)row[3]));
						setMarkupForm.setMdsMarkup(Double.parseDouble(""+(BigDecimal)row[4]));
						setMarkupForm.setDsMarkup(Double.parseDouble(""+(BigDecimal)row[5]));
						setMarkupForm.setAgentMarkup(Double.parseDouble(""+(BigDecimal)row[6]));
						setMarkupForm.setClientInitial(initial);
						setMarkupForm.setUserId((BigInteger)row[7]);
					}
				}
				else
				{
					if(isSuperAdmin){
						System.out.println(" Yes Super admin in Dao >>>>>>>>>>>>>>>>>>>");
						sql="select portal_ID from MD_details where MD_id='"+setMarkupForm.getMdId()+"'";
						query=ses.createSQLQuery(sql);
						list=query.list();
						it=list.iterator();
						if(list.size()>0)
						{
							while(it.hasNext())
							{
								String row=(String)it.next();
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>"+row.toString());
								setMarkupForm.setUserId(new BigInteger(row));
							}
						}
					}
					setMarkupForm.setClientId(clientId);
					setMarkupForm.setMarketLimit(0);
					setMarkupForm.setClientMarkup(0);
					setMarkupForm.setMdsMarkup(0);
					setMarkupForm.setDsMarkup(0);
					setMarkupForm.setAgentMarkup(0);
					setMarkupForm.setClientInitial(initial);
				}
			}
					
			ses.flush();
			ses.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		return setMarkupForm;
	}
	
	public String saveMarkupDetailForindividual(SetMarkupForm setMarkupForm,boolean isSuperAdmin) 
	{
		String status="failure";
		Connection con=null;
		Statement stmt=null;
		String user_id="";
		String client_id="";
		Session ses=null;
		Session ses2=null;
		Transaction tx=null;
		String sql="";
		String sql2="";
		List list;
		Iterator it;
		Query query;
		try 
		{
		
			ses=HibernateSession.getSessionFactory().openSession();
			if(setMarkupForm.getClientInitial().equalsIgnoreCase("CL"))
			{
				if(isSuperAdmin)
					sql="select * from dbo.markup_details where client_id='"+setMarkupForm.getClientId()+"' and md_id='"+setMarkupForm.getMdId()+"' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				else
					sql="select * from dbo.markup_details where setBy_user_id="+setMarkupForm.getSetByUserId()+" and client_id='"+setMarkupForm.getClientId()+"' and md_id='"+setMarkupForm.getMdId()+"' and ds_id='all' and agent_id='all' and user_id="+setMarkupForm.getUserId()+" and service='"+setMarkupForm.getService()+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					
						con=ses.connection();
						tx=ses.beginTransaction();
						String sql3="";		
						stmt=con.createStatement();
							sql3="update markup_details set market_limit="+setMarkupForm.getMarketLimit()+",client_markup="+setMarkupForm.getClientMarkup()+",mds_markup="+setMarkupForm.getMdsMarkup()+",ds_markup="+setMarkupForm.getDsMarkup()+",agent_markup="+setMarkupForm.getAgentMarkup()+" where user_id="+setMarkupForm.getUserId()+" and client_id='"+setMarkupForm.getClientId()+"' and md_id='"+setMarkupForm.getMdId()+"' and ds_id='all' and agent_id='all' and service='"+setMarkupForm.getService()+"'";

						int rw=stmt.executeUpdate(sql3);
						if(rw>0)
							System.out.println("Update Success");
						else
							System.out.println("Update Failure");
						tx.commit();
						
					
					System.out.println("Update transaction in markup_details success and close >>>>>>>>>>>>>>>>>>");		
				}
				else
				{
					ses2=HibernateSession.getSessionFactory().openSession();
												
					tx=ses2.beginTransaction();
					ses2.save(setMarkupForm);
					tx.commit();
				}
				status="success";
								
			}
			
		
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return "failure";
		}
		finally
		{
			try
			{
				if(ses!=null)
				{
					ses.flush();
					ses.close();
				}
				if(con!=null)
					con.close();
				if(stmt!=null)
					stmt.close();
				if(ses2!=null)
					ses2.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return status;
	}
	public boolean getClientStatus(String userId,String clientId) {
		
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select * from admin_user_details where User_id='"+userId+"' and Portal_id='"+clientId+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					status=true;
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return status;
	}
	
	public boolean getMDStatus(String userId,String mdID) {
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select * from admin_user_details where User_id='"+userId+"' and Portal_id=(select Client_Id from MD_Details where MD_id='"+mdID+"')";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					status=true;
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return status;
	}
	
	public String getSingleClientId(String mdID) {
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		String clientId="";
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select Client_Id from MD_Details where MD_id='"+mdID+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					clientId=it.next()+"";
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		return clientId;
	}
	
	public boolean getDSStatus(String userId,String dsId) {
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select * from admin_user_details where User_id='"+userId+"' and Portal_id=(select Client_Id from MD_Details where MD_id=(select md_id from distributor_details where distributor_id='"+dsId+"'))";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					status=true;
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return status;
	}
	
	public boolean getAGStatus(String userId,String agId) {
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select * from admin_user_details where User_id='"+userId+"' and Portal_id=(select Client_Id from MD_Details where MD_id=(select md_id from distributor_details where distributor_id=(select distributor_id from agent_details where agent_id='"+agId+"')))";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				if(list.size()>0)
				{
					status=true;
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return status;
	}
	
	public ArrayList<String> getClientId(String userId) {
		ArrayList<String> clientList=new ArrayList<String>();
		boolean status=false;
		String sql="";
		Session ses=null;
		Query query=null;
		List list=null;
		Iterator it;
		try
		{
			ses=HibernateSession.getSessionFactory().openSession();
			sql="select Portal_id from admin_user_details where user_id='"+userId+"'";
				query=ses.createSQLQuery(sql);
				list=query.list();
				it=list.iterator();
				while (it.hasNext()) {
					Object obj = (Object) it.next();
					clientList.add(obj.toString());
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
		return clientList;
	}
	
}
