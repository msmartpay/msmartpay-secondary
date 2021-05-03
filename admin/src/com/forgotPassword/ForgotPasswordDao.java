package com.forgotPassword;

import java.util.HashMap;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.formBean.superAdmin.SuperAdminLoginInfoFormBean;
import com.login.LoginInfoFormBean;

public class ForgotPasswordDao 
{
	
	public HashMap<String,Object> getStatus(String emailId) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object> map=new HashMap<String,Object>();
		int id=0;
		Query qry=null;
		try {
			qry=session.createQuery("select AFB.userId,AFB.portalId from  AdminUserFormBean AFB where AFB.emailId=:emailID")
			.setParameter("emailID", emailId);
			Iterator<?>it=qry.iterate();
			if(it.hasNext())
			{
				Object[] row=(Object[])it.next();
				map.put("userId", row[0]);
				map.put("portalID", row[1]);
				id=(Integer)row[1];
			}
			if(id>1){
				qry=session.createQuery("select DDF.adminLoginUrl from  DynamicDetailsFormBean DDF where DDF.clientId=:id")
				.setParameter("id",id);	
				Iterator<?>it1=qry.iterate();
				if(it1.hasNext())
				{
					Object row=(Object)it1.next();
					map.put("adminLoginUrl",row);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
			}
		}
		return map;
	}

	public HashMap<String, Object> getDetails(int existenceId,String pass) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object> details=new HashMap<String, Object>();
		Query qry=null;
		Transaction tran=null;
		try {
			if(existenceId==1){
				qry=session.createQuery("select SDF.userName from  SuperAdminLoginInfoFormBean SDF where SDF.userId=:id")
				.setParameter("id",1);
			}else {
				qry=session.createQuery("select LFB.userName from  LoginInfoFormBean LFB where LFB.userId=:id")
				.setParameter("id",existenceId);
			}
			Iterator<?>it=qry.iterate();
			if(it.hasNext()){
				Object row=(Object)it.next();
				details.put("userName", row);
			}
			tran=session.beginTransaction();
			if(existenceId==1){
				SuperAdminLoginInfoFormBean superForm=(SuperAdminLoginInfoFormBean)session.get(SuperAdminLoginInfoFormBean.class, existenceId);
				if(superForm!=null){
					superForm.setPassword(pass);
					session.update(superForm);
				}
			}
			else{
				LoginInfoFormBean Form=(LoginInfoFormBean)session.get(LoginInfoFormBean.class, existenceId);
				if(Form!=null){
					Form.setPassword(pass);
					session.update(Form);
				}
			}	
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		}
		finally{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
			}
		}
		return details;
	}
}
