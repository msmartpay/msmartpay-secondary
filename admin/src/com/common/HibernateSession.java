package com.common;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 
public class HibernateSession {
	
private static final SessionFactory sessionFactory;

static {
	
	try {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	} catch (Throwable ex) {
		System.err.println("Initial SessionFactory creation failed." );
		ex.printStackTrace();
		throw new ExceptionInInitializerError(ex);
	}
}
	public static SessionFactory getSessionFactory() 
	{
		return sessionFactory;
	}
}
