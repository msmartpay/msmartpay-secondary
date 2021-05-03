package com.activity.admin.moveEntity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.login.LoginAction;

public class MoveEnityDao {

	private static Logger log = Logger.getLogger(MoveEnityDao.class);
	public String movedistributoragent(String dsid,String agid,String movedBy)
	{
		String status="";


		Session session=null; 
		Transaction txn=null;
		Connection con=null;
		CallableStatement cstmt =null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			con=session.connection();
			
			cstmt=con.prepareCall("{call Agent_MoveEnity(?,?,?,?)}");
			cstmt.setLong(1,Long.parseLong(dsid));
			cstmt.setLong(2,Long.parseLong(agid));
			cstmt.setString(3,movedBy);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(4);
			//System.out.println(status);
			txn.commit();


		}
		catch(Exception e)
		{
			if(txn!=null)
			{
				try
				{
					txn.rollback();
				}catch(Exception ex){}
			}
			//System.out.println("Exception in movedistributoragent");
			//System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(cstmt!=null)
					cstmt.close();
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();
			}
			catch(Exception e)
			{
				//System.out.println("Exception in movedistributoragent");
				//System.out.println(e.toString());
			}
		}


		return status;
	}

	public String moveMdsToDs(String mdsid,String dsid)
	{
		String status="";


		Session session=null; 
		Transaction txn=null;
		Connection con=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			txn=session.beginTransaction();
			con=session.connection();
			CallableStatement cstmt =null;
			cstmt=con.prepareCall("{call Distributor_MoveEnity(?,?,?)}");
			cstmt.setLong(1,Long.parseLong(mdsid));
			cstmt.setLong(2,Long.parseLong(dsid));
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			cstmt.execute();
			status=cstmt.getString(3);
			//System.out.println(status);
			txn.commit();


		}
		catch(Exception e)
		{
			if(txn!=null)
			{
				try
				{
					txn.rollback();
				}catch(Exception ex){}
			}
			//System.out.println("Exception in movedistributoragent");
			//System.out.println(e.toString());
		}
		finally
		{
			try
			{
				if(con!=null)con.close();;
				session.close();
			}
			catch(Exception e)
			{
				//System.out.println("Exception in movedistributoragent");
				//System.out.println(e.toString());
			}
		}


		return status;
	}

}
