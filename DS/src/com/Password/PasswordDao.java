package com.Password;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.PushBalance.PushBalanceDao;
import com.agentDetails.AgentLoginForm;
import com.agentDetails.REPAgentLoginForm;
import com.common.HibernateSession;
import com.common.LogWriter;
import com.login.LoginForm;

public class PasswordDao 

{

	public String changePassword(String userId, String newPassword,
			String clientType) {
		Session session=HibernateSession.getSessionFactory().openSession();
		String   password=""; 	
		Transaction tran=null;
		try 
		{

			tran=session.beginTransaction();

			LoginForm LoginForm =(LoginForm)session.get(LoginForm.class,userId);
			if(LoginForm!=null)
			{
				LoginForm.setPassword(newPassword);
				session.update(LoginForm);
			}


			tran.commit();
		}

		catch (Exception ex) 
		{
			ex.printStackTrace();
			tran.rollback();
		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return password;
	}

	public String CheckOldPassword(String userId, String clientType) {
		Logger logger = Logger.getLogger(PasswordDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String   password=""; 
		Query passwordQuery=null;
		try 
		{



			passwordQuery=session.createQuery("select LF.password from LoginForm LF where LF.userId=:disId").setParameter("disId",userId);



			log.print("passwordQuery -- query is "+passwordQuery, logger);
			Iterator<?> it=passwordQuery.iterate();
			if(it.hasNext())
			{
				Object row = (Object) it.next();
				password=(String)row;
			}


		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {
			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return password;
	}

	public String getRandomString(int length) {
		Random rand = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		String charset = "0123456789abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < length; i++) {
			int pos = rand.nextInt(charset.length());
			sb.append(charset.charAt(pos));
		}
		return sb.toString();
	}

	public String setPassword(String password, String agentOnlyId,
			String clientType) {
		Session session=HibernateSession.getSessionFactory().openSession();
		String   passwordstatus="notreset"; 	
		Transaction tran=null;
		try 
		{

			tran=session.beginTransaction();

			if(clientType.equalsIgnoreCase("TEP"))
			{

				AgentLoginForm AgentLoginForm =(AgentLoginForm)session.get(AgentLoginForm.class,agentOnlyId);
				if(AgentLoginForm!=null)
				{
					AgentLoginForm.setPassword(password);
					session.update(AgentLoginForm);
					passwordstatus="reset";
				}
			}
			if(clientType.equalsIgnoreCase("REP"))
			{

				REPAgentLoginForm REPAgentLoginForm =(REPAgentLoginForm)session.get(REPAgentLoginForm.class,agentOnlyId);
				if(REPAgentLoginForm!=null)
				{
					REPAgentLoginForm.setReppassword(password);
					session.update(REPAgentLoginForm);
					passwordstatus="reset";
				}

			} 
			tran.commit();
		}

		catch (Exception ex) 
		{
			passwordstatus="notreset"; 
			ex.printStackTrace();
			System.out.println("exception in in reset password---"+ex.toString());
			tran.rollback();
		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return passwordstatus;
	}

	public HashMap<String,Object> forgetPassword(String email, String Client_Type) {
		Logger logger = Logger.getLogger(PushBalanceDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object> idStatus=new HashMap<String,Object>();
		Query IdStatusQuery=null;
		String status="invalid";
		try 
		{



			IdStatusQuery=session.createQuery("select DDF.distributorId,DDF.distributorInitial,LF.password from DistributorDetailForm DDF,LoginForm LF where DDF.distributorId=LF.userId and LF.loginId=:email").setParameter("email",email);


			log.print("PushBalanceDao -getIdStatus- query is "+IdStatusQuery, logger);
			Iterator<?> it=IdStatusQuery.iterate();
			if(it.hasNext())
			{
				status="valid";
				Object[] row = (Object[]) it.next();

				idStatus.put("distributorId",row[0]);
				idStatus.put("distributorInitial",row[1]);
				idStatus.put("password",row[2]);
				idStatus.put("status",status);
			}
			else
			{
				idStatus.put("status",status); 
			}

		} 


		catch (Exception ex) 
		{
			idStatus.put("status",status);
			ex.printStackTrace();
		}
		finally {
			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return idStatus;
	}



}

