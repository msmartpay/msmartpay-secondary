package com.activity.cwu;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.common.HibernateSession;

final class ClientWalletUpdationDao {

	final String updateClientAmount(String clientName, String clientUrl,double amount, String loginType, String transactionNo, String ipAdd) 
	{
		String status="fail";
		Session session=null;
		Session session1=null;
		Configuration cfg=null;
		SessionFactory factory=null;
		
        Query qry = null;
        String sqlQuery=null;
        Transaction ts=null;
        Transaction ts1=null;
        double admintotalCash=0.0;
		double adminusedCash=0.0;
		double admintotalBalanceAmount=0.0;
		double adminavailableBalanceAmount=0.0;
        try{
        	session = HibernateSession.getSessionFactory().openSession();
        	sqlQuery="select client_DB_name from client_DB_info_tab where client_name='"+clientName+"' and client_URL='"+clientUrl+"'";
        	qry=session.createSQLQuery(sqlQuery);
        	String DBName=qry.uniqueResult().toString();
        	if(DBName==null || DBName==""){
        		status="DBNA";
        	}else{
        		ts=session.beginTransaction();
        		sqlQuery="insert into client_upadate_balnace_tab (user_type,tran_id,client_name,client_URl,client_db_name,amount,user_ip_add,date_time) values ('"+loginType+"','"+transactionNo+"','"+clientName+"','"+clientUrl+"','"+DBName+"','"+amount+"','"+ipAdd+"',getdate())";
        		//System.out.println(sqlQuery);
        		qry=session.createSQLQuery(sqlQuery);
        		qry.executeUpdate();
        		ts.commit();
        		cfg=new Configuration().configure("hibernate4.cfg.xml");
        		factory=cfg.buildSessionFactory();
        		session1=factory.openSession();
        		ts1=session1.beginTransaction();
        		
        		sqlQuery="select auad.Total_cash,auad.Used_cash,auad.Total_balance_Amount,auad.Available_balance_amount from "+DBName+".dbo.SuperAdmin_Amount_Details auad where auad.Super_admin_id=1";
        		qry=session1.createSQLQuery(sqlQuery);
        		//.addScalar("Total_cash",Hibernate.DOUBLE).addScalar("Used_cash",Hibernate.DOUBLE)
        		//.addScalar("Total_balance_Amount",Hibernate.DOUBLE).addScalar("Available_balance_amount",Hibernate.DOUBLE);
        		//System.out.println(qry);
        		Object[] obj=(Object[])qry.uniqueResult();
        		admintotalCash=Double.parseDouble(obj[0].toString());
        		adminusedCash=Double.parseDouble(obj[1].toString());
        		admintotalBalanceAmount=Double.parseDouble(obj[2].toString());
        		adminavailableBalanceAmount=Double.parseDouble(obj[3].toString());	
        		//System.out.println("admintotalCash in while "+admintotalCash);
        		//System.out.println("adminusedCash in while "+adminusedCash);
        		//System.out.println("admintotalBalanceAmount in while "+admintotalBalanceAmount);
        		//System.out.println("adminavailableBalanceAmount in while "+adminavailableBalanceAmount);
        		String adminupdateQuery="update "+DBName+".dbo.SuperAdmin_Amount_Details set Total_cash="+(admintotalCash+amount)+",Total_balance_Amount="+(admintotalBalanceAmount+amount)+",Available_balance_amount="+(adminavailableBalanceAmount+amount)+",Last_updated_amount="+amount+",Updated_date=getDate() where Super_admin_id=1";
        		//System.out.println(adminupdateQuery);
        		qry=session1.createSQLQuery(adminupdateQuery);
        		int i=qry.executeUpdate();
        		//System.out.println(i);
        		ts1.commit();
        		status="Success";
        	}
        }catch (Exception e) {
        	status="fail";
        	System.out.println("Exception in ClientWalletUpdationDao method is updateClientAmount");
        	System.out.println(e.toString());
        }finally
        {
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in ClientWalletUpdationDao while closing connection");
				System.out.println(e.toString());
			}
        }
        return status;
	}
}
