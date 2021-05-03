package com.activity.adminTask.addBank;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.formBean.dynamicDetails.CollectionBankDetailsFormBean;

public class CollectionBankDao {
	
	public boolean addBank(CollectionBankDetailsFormBean bankDetailsFormBean) {
		boolean status=false;
		Session session=null;
		Transaction txn;
		try {
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			session.save(bankDetailsFormBean);
			
			txn.commit();
			status=true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}
	
	public boolean updateBank(CollectionBankDetailsFormBean bankDetailsFormBean) {
		boolean status=false;
		Session session=null;
		Transaction txn=null;
		try {
			CollectionBankDetailsFormBean oldbankDetailsFormBean=null;
			session=HibernateSession.getSessionFactory().openSession();
			
			oldbankDetailsFormBean=(CollectionBankDetailsFormBean) session.get(CollectionBankDetailsFormBean.class, bankDetailsFormBean.getSlNo());
			if(oldbankDetailsFormBean!=null) {
				
				oldbankDetailsFormBean.setBankAccountName(bankDetailsFormBean.getBankAccountName());
				oldbankDetailsFormBean.setBankAccount(bankDetailsFormBean.getBankAccount());
				oldbankDetailsFormBean.setBankIFSC(bankDetailsFormBean.getBankIFSC());
				oldbankDetailsFormBean.setBankName(bankDetailsFormBean.getBankName());
				
				txn=session.beginTransaction();
				session.update(oldbankDetailsFormBean);
				status=true;
				txn.commit();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			txn.rollback();
			e.printStackTrace();
			
		}finally {
			session.close();
		}
		return status;
	}
	public List<CollectionBankDetailsFormBean> getBankList(long clientId) {
		Session session=null;
		List<CollectionBankDetailsFormBean> list=null;
		try {
			session=HibernateSession.getSessionFactory().openSession();
			String hql="from CollectionBankDetailsFormBean where clientId=:clientId";
			Query qry=session.createQuery(hql).setLong("clientId", clientId);
			list=qry.list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			session.close();
		}
		return list;
	}
	public boolean updateBankStatus(int slNo,int status,String bankName,String bankAccountHolder,String bankAccount,String bankAccountIfsc) {
		Session session=null;
		boolean updateStatus=false;
		Transaction txn=null;
		try {
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			CollectionBankDetailsFormBean bankDetailsFormBean=(CollectionBankDetailsFormBean)session.get(CollectionBankDetailsFormBean.class, slNo);
			
			bankDetailsFormBean.setStatus(status);
			bankDetailsFormBean.setBankAccount(bankAccount);
			bankDetailsFormBean.setBankAccountName(bankAccountHolder);
			bankDetailsFormBean.setBankIFSC(bankAccountIfsc);
			bankDetailsFormBean.setBankName(bankName);
			session.update(bankDetailsFormBean);
			txn.commit();
			updateStatus=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			txn.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}
}
