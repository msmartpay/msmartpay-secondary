package com.controlPanel.profileMgt.corporateUser.whitelabelClient;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.ConvertUtility;
import com.common.HibernateSession;
import com.formBean.adminUser.AdminUserFormBean;
import com.formBean.dynamicDetails.DynamicDetailsFormBean;

 class WhiteLabelClientProfileDao {
	
	final DynamicDetailsFormBean  viewWLClientDetails(String clientID){
		Session session=null;
		Transaction ts=null;
		String sql="";
		Query query=null;
		DynamicDetailsFormBean formBean=null;
		AdminUserFormBean AdminBean=null;
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			int Id=Integer.parseInt(clientID);
			formBean=(DynamicDetailsFormBean)session.get(DynamicDetailsFormBean.class,Id);
			//System.out.println("Successfully get DynamicDetailsFormBean........");
			return formBean;
							
		}catch(Exception E){		
			System.out.println("Exception in WhiteLabelClientProfileDao,viewWLClientDetails");
			System.out.println(E.toString());
			ts.rollback();
			return formBean;
			
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in WhiteLabelClientProfileDao,viewWLClientDetails");
				System.out.println(e.toString());		
			}			
		}
	}
	public String updateWLClientDetails(String clientID, String companyName,
			String domainName, String helpEmailId, String helpMobileNo,
			String txnNotificationEmailID, String careUrl) {
		Session session=null;
		Transaction ts=null;
		String status="fail";
		try{
			//System.out.println("we are into dao method");
			
			session = HibernateSession.getSessionFactory().openSession(); 
			session.beginTransaction();
			String flagQuery="select flag from white_label_details where Client_Id='"+clientID+"'";
			String flag=session.createSQLQuery(flagQuery).uniqueResult().toString();
			String newdomainName=ConvertUtility.getDomainName(domainName);
			String mdLoginUrl="http://mds."+newdomainName+"/mds";
			String distributorLoginUrl="http://ds."+newdomainName+"/distributor";	
			String agentLoginUrl="";
			if(flag.equalsIgnoreCase("0") || flag.equalsIgnoreCase("2")){
				agentLoginUrl="http://agent."+newdomainName+"/agent";	
			}else{
				agentLoginUrl="http://agent."+newdomainName+"/arep";	
			}
			String sql="update white_label_details set Company_name='"+companyName+"', Help_Desk_MobileNo='"+helpMobileNo+"',Help_Desk_EmailID='"+helpEmailId+"',Domain_name='"+domainName+"',TXN_Notification_EmailID='"+txnNotificationEmailID+"',Care_Url='"+careUrl+"',Distributor_login_url='"+distributorLoginUrl+"',Agent_login_url='"+agentLoginUrl+"',MD_login_url='"+mdLoginUrl+"' where client_id='"+clientID+"'";
//			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
			session.getTransaction().commit();
			//System.out.println("Successfully Updated........");
			status="success";
			return status;
							
		}catch(Exception E){
			System.out.println("Exception in WhiteLabelClientProfileDao,viewWLClientDetails");
			System.out.println(E.toString());
			session.getTransaction().rollback();
			status="fail";
			return status;
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in WhiteLabelClientProfileDao,viewWLClientDetails");
				System.out.println(e.toString());	
			}			
		}
	}
}
