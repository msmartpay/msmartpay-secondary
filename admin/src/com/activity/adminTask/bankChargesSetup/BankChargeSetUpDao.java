package com.activity.adminTask.bankChargesSetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class BankChargeSetUpDao {
	
	public ArrayList listOfBankCharges(String mode,String bankName)
	{
		Session session=null;
		Query query=null;
		Object[] obj=null;
		String sql=null;
		Transaction ts=null;
		ArrayList chargesList=new ArrayList();
		try{
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			if(mode.equalsIgnoreCase("Cash on Desk"))
			{
				sql="select mod.S_No,mod.Limit_Amount,mod.charges,mod.charges_Type from MD_mode_of_payment mod where mode_of_payment='"+mode+"'";
			}
			else
			{
				sql="select mod.S_No,mod.Limit_Amount,mod.charges,mod.charges_Type,mod.BranchName,mod.Account_No from MD_mode_of_payment mod where mode_of_payment='"+mode+"' And mod.Bank_Name='"+bankName+"'";
			}
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				obj=(Object[])itr.next();
				HashMap map=new HashMap();
				map.put("sno", obj[0]);
				map.put("limit", obj[1]);
				map.put("charge", obj[2]);
				map.put("chargeType", obj[3]);
				if(!mode.equalsIgnoreCase("Cash on Desk"))
				{
					map.put("BranchName", obj[4]);
					map.put("AccountNo", obj[5]);				
				}				
				chargesList.add(map);
			}
			return chargesList;
		}catch(Exception E){	
			System.out.println("Exception in BankChargeSetUpDao,listOfBankCharges");
			System.out.println(E.toString());
			ts.rollback();
			return chargesList;
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in BankChargeSetUpDao,listOfBankCharges");
				System.out.println(e);				
			}			
		}	
	}
	
	public String updateBankCharges(ArrayList list)
	{	
		Session session=null;
		Query query=null;
		Object[] obj=null;
		String sql=null;
		Transaction ts=null;
		try{
			int result;
			HashMap map=new HashMap();
			session = HibernateSession.getSessionFactory().openSession(); 
			ts=session.beginTransaction();
			Iterator itr=list.iterator();
			while(itr.hasNext())
			{
				map=(HashMap)itr.next();
				sql="update MD_mode_of_payment set Limit_Amount="+map.get("limit")+",charges="+map.get("charge")+",charges_Type='"+map.get("chargeType")+"' where S_No="+map.get("sno");
				query=session.createSQLQuery(sql);
				result=query.executeUpdate();		
			}
			ts.commit();
			return "success";
		}catch(Exception E){
			System.out.println("Exception in BankChargeSetUpDao,updateBankCharges");
			System.out.println(E.toString());
			ts.rollback();
			return "err";
			
		}finally{
			try{
				session.flush();
				session.close();
			}catch(Exception e)	{
				System.out.println("Exception in BankChargeSetUpDao,updateBankCharges");
				System.out.println(e);				
			}			
		}	
	}
}
