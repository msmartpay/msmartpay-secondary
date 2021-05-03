package com.common;

/*
 * Created By   : Manoj 
 * Created Date : 24 may 2012
 * Matter       : This class is used for connection to OnlineRechAPI_db database for Egen report in which We are using Admin_Egen_reports stored Procedure.
 */

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession1 
{
	private static final SessionFactory sessionFactory;
	static 
	{
		try 
		{
			sessionFactory = new Configuration().configure("hibernate2.cfg.xml").buildSessionFactory();
		} 
		catch (Throwable ex) 
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	 
	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
}
