package com.reports.ccModule.noticeAlert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class NoticeAlertDao 
{

	public ArrayList<HashMap<String, String>> getclientDetails(String userId,String UserType)
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String,String>> data=new ArrayList<HashMap<String,String>>();	
		Query qry=null;
        String sql="";
		try 
		{
			if(!UserType.equalsIgnoreCase("SuperAdmin"))
			{
				sql="select distinct convert(varchar(10),Client_Id),Company_name from white_label_details where Client_Id=(select Portal_id from Admin_User_details where User_id='"+userId+"')";
			}
			else
			{
				sql="select distinct convert(varchar(10),Client_Id),Company_name from white_label_details";
				
			}
			qry=session.createSQLQuery(sql);
			List list=qry.list();
			Iterator it=list.iterator();
		
			while(it.hasNext())
			{
        		Object[] row=(Object[])it.next();
        		HashMap<String,String> map=new HashMap<String,String>();
				map.put("clientId",row[0].toString());
				map.put("companyName",row[1].toString());
				data.add(map);
        	}
        } catch (Exception e)
		{
			System.out.println("Exception in NoticeAlertDao,getclientDetails");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.flush();
				session.close();
			} catch (Exception e2) 
			{
				System.out.println("Exception in NoticeAlertDao,getclientDetails");
				System.out.println(e2.toString());
			}
		}
		return data;
	}	

	public String getMessage(int id, String userType, String portalUserType, String userId) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		Query qry=null;
		String message="";
		String sql="";
		try 
		{
			if("Agent".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="select agent_Ticker from white_label_details where client_id='"+id+"'";
				}else
				{
					sql="select agent_Ticker from white_label_details where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}
			
			if("DS".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="select distributor_Ticker from white_label_details where client_id='"+id+"'";
				}else
				{
					sql="select distributor_Ticker from white_label_details where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}	
			}
			
			if("MD".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="select MD_Ticker from white_label_details where client_id='"+id+"'";
				}else
				{
					sql="select MD_Ticker from white_label_details where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}
			//System.out.println(sql);
			qry=session.createSQLQuery(sql);
			message=qry.uniqueResult().toString();
		} catch (Exception e) 
		{
			System.out.println("Exception in NoticeAlertDao,getMessage");
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} catch (Exception e2)
			{
				System.out.println("Exception in NoticeAlertDao,getMessage");
				System.out.println(e2.toString());
			}
		}
		return message;
	}

	public String updateMessage(int id, String userType, String NewMessage, String portalUserType, String userId) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
        String UpdateStatus="NotUpdate";
        Transaction tran=null;
        Query qry=null;
        String sql="";
		try 
		{
			if("Agent".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="update white_label_details set agent_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id='"+id+"'";
				}
				else
				{
					sql="update white_label_details set agent_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}
			if("DS".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="update white_label_details set distributor_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id='"+id+"'";
				}else
				{
					sql="update white_label_details set distributor_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}	
			}
			if("MD".equalsIgnoreCase(userType))
			{
				if(portalUserType.equalsIgnoreCase("SuperAdmin")|| portalUserType.equalsIgnoreCase("activityAdmin")|| portalUserType.equalsIgnoreCase("activityUser"))
				{
					sql="update white_label_details set MD_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id='"+id+"'";
				}else
				{
					sql="update white_label_details set MD_Ticker='"+NewMessage+"',Ticker_updated_date=getdate(),Ticker_updated_by='"+userId+"' where client_id in (select portal_id from admin_user_details where user_id='"+userId+"')";
				}
			}
			//System.out.println(sql);
			qry=session.createSQLQuery(sql);
			tran=session.beginTransaction();
			int flag=qry.executeUpdate();
			if(flag>0) 
			{
				UpdateStatus="Update"; 
			}
			else
			{
				UpdateStatus="NotUpdate";
			}
			tran.commit();
	         
		} catch (Exception e)
		{
			UpdateStatus="notUpdate";
			tran.rollback();
			System.out.println("Exception in NoticeAlertDao,updateMessage");
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} catch (Exception e2) 
			{
				System.out.println("Exception in NoticeAlertDao,updateMessage");
				System.out.println(e2.toString());
			}
		}
		return UpdateStatus;
	}
}
