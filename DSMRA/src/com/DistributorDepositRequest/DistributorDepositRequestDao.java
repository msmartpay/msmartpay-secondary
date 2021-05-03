package com.DistributorDepositRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.dsamount.DistributorJournalForm;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DistributorDepositRequestDao {

	public JSONArray collectionBank(long dsId) {
		Session session=null;
		JSONArray bankList=new JSONArray();
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createSQLQuery("select bank_name,bank_account,bank_account_name,bnk_ifsc from wb_collection_bank_detils (nolock) a join distributor_details (nolock) b on a.client_id=b.Client_ID and b.distributor_id=:distributor_id and a.status=1").setParameter("distributor_id",dsId);
			List li=query.list();
			Iterator<?> it=li.iterator();
			while(it.hasNext())
			{

				Object[] row = (Object[]) it.next();
				JSONObject temp=new JSONObject(); 
				temp.put("bank_name",row[0]); 
				temp.put("bank_account",row[1]); 
				temp.put("bank_account_name",row[2]);
				temp.put("bnk_ifsc",row[3]);  
				bankList.add(temp);

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
		return bankList;
	}

	public String saveETransferDep(DistributorJournalForm DistributorJournalForm) {
		Session session=null;
		Transaction tran=null;
		String result="";

		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();

			session.save(DistributorJournalForm);
			result="success";
			tran.commit();		
		} 

		catch (Exception e) 
		{
			result="failure";
			e.printStackTrace(); 
			tran.rollback();
		}
		finally {
			try {
				session.close();					
			} 
			catch (Exception e) {
				result="failure";
				e.printStackTrace();
			}
		}

		return result;
	}

	public HashMap<String,String> getMDSMailId(String usrId) {
		HashMap<String,String> map=new HashMap<String,String>();
		Session session=null;

		try 

		{

			session=HibernateSession.getSessionFactory().openSession();
			String sql="select dd.email_id,md.Email_id,dd.DSID from distributor_details dd join MD_Details md on md.MD_id=dd.md_id where dd.distributor_id="+Long.parseLong(usrId);

			SQLQuery query = session.createSQLQuery(sql);
			List list=query.list();
			Iterator it=list.iterator();
			while (it.hasNext()) {
				Object[] row = (Object[]) it.next();
				map.put("MDEmail", row[0]+"");
				map.put("DSEmail", row[1]+"");
				map.put("DSID", row[2]+"");
			}
			

		}	
		catch (Exception ex) 
		{
			ex.printStackTrace();
			return null;

		}
		finally {

			try {
				session.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}

		return map;
	}

}
