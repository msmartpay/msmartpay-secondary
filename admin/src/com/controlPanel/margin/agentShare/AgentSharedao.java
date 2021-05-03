package com.controlPanel.margin.agentShare;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;

public class AgentSharedao 
{

	public List<String> fetchMarginServiceList() {

		Session session =null;
		Query qry=null;
		List<String> li=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select distinct Service from Charge_Commission (nolock)  order by Service asc");
			li=qry.list();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(session!=null)
				session.close();
		}

		return li;

	}
	public List fetchAgentMargin(long agentId,String operator) {

		Session session =null;
		Query qry=null;
		List li=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select MDS_id,DS_Id,AgentId,Service,Operator,CommissionMark,Commission,ChargeMark,Charge,startRange,endRange from Charge_Commission (nolock) where AgentId="+agentId+" and Service='"+operator+"'");
			li=qry.list();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(session!=null)
				session.close();
		}

		return li;

	}
	public String updateAgentChargeShareNew(String userType,String userId,String operator,
			String chargeMark,double charge,String commMark,double comm,
			double startRange,double endRange) {
		String status="fail";
		Session session=null;
		Transaction txn=null;
		String sql="";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			if("ByMdId".equalsIgnoreCase(userType)){
				sql="update Charge_Commission set "
						+ ",CommissionMark='"+commMark+"' ,Commission="+comm
						+ ",ChargeMark='"+chargeMark+"' ,Charge="+charge
						+ " where Operator='"+operator+"' and MDS_ID='"+userId+"' and startRange="+startRange+" and endRange="+endRange;
			}else if("ByDsId".equalsIgnoreCase(userType)){
				sql="update Charge_Commission set CommissionMark='"+commMark+"' ,Commission="+comm
						+ ",ChargeMark='"+chargeMark+"' ,Charge="+charge
						+ " where Operator='"+operator+"' and DS_ID='"+userId+"' and startRange="+startRange+" and endRange="+endRange;
			}else if("ByAgentId".equalsIgnoreCase(userType)){
				sql="update Charge_Commission set CommissionMark='"+commMark+"' ,Commission="+comm
						+ ",ChargeMark='"+chargeMark+"' ,Charge="+charge
						+ " where Operator='"+operator+"' and AgentId ="+Long.parseLong(userId)+ " and startRange="+startRange+" and endRange="+endRange;
			}
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
			status="Success";
			txn.commit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			txn.rollback();
			status="fail";
			e.printStackTrace();
		}
		finally
		{
			if(session!=null)
			session.close();
		}
		return status;
	}

	public String getAgentIdByDSId(String dsId) {

		Session session =null;
		Query qry=null;
		String id=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select top 1 agent_id from agent_details (nolock) where DS_ID='"+dsId+"' order by agent_id asc");
			id= qry.uniqueResult().toString();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}

		return id;

	}
	public String getAgentIdByMDSId(String dsId) {

		Session session =null;
		Query qry=null;
		String id=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select top 1 agent_id from agent_details (nolock) where MDS_ID='"+dsId+"' order by agent_id asc");
			id=qry.uniqueResult().toString();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(session!=null)
				session.close();
		}

		return id;

	}
	public List fetchDSMargin(String dsId,String operator) {

		Session session =null;
		Query qry=null;
		List li=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select DS_Id,Service,ChargeMark,Charge,"
					+ "CommissionMark,Commission,startRange,endRange from DS_Charge_Commission (nolock) where DS_Id='"+dsId+"' and Service='"+operator+"' order by Service asc, startRange asc");
			li=qry.list();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(session!=null)
				session.close();
		}

		return li;

	}
	
	
	/*
	public List fetchAgentMarggin(long agentId,String operator) {

		Session session =null;
		Query qry=null;
		List li=null;
		try {

			session=HibernateSession.getSessionFactory().openSession();
			qry=session.createSQLQuery("select MDS_id,DS_Id,AgentId,Service,Operator,ChargeMark,Charge,"
					+ "CommissionMark,Commission,18 as gst,Charge-Commission+(Commission*0.18) as net,KycCommissionMark,KycCommission,KycChargeMark,KycCharge from Agent_Charge_Commission (nolock) where AgentId="+agentId+" and Operator='"+operator+"'");
			li=qry.list();

		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(session!=null)
				session.close();
		}

		return li;

	}
	public String updateAgentChargeShareNew(String userType,String userId,String operator, String chargeMark, String charge,String kycchargeMark, String kyccharge) {
		String status="fail";
		Session session=null;
		Transaction txn=null;
		String sql="";
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			if("ByMdId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set ChargeMark='"+chargeMark+"' ,Charge="+charge+",KycChargeMark='"+kycchargeMark+"' ,KycCharge="+kyccharge+" where Operator='"+operator+"' and MDS_ID='"+userId+"'";
			}else if("ByDsId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set ChargeMark='"+chargeMark+"' ,Charge="+charge+",KycChargeMark='"+kycchargeMark+"' ,KycCharge="+kyccharge+" where Operator='"+operator+"' and DS_ID='"+userId+"'";
			}else if("ByAgentId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set ChargeMark='"+chargeMark+"' ,Charge="+charge+",KycChargeMark='"+kycchargeMark+"' ,KycCharge="+kyccharge+" where Operator='"+operator+"' and AgentId ="+Long.parseLong(userId);
			}
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
			status="Success";
			txn.commit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			txn.rollback();
			status="fail";
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return status;
	}
	
	public String updateAgentCommShareNew(String userType,String userId,String operator, String commMark, String comm, String kyccommMark, String kyccomm) {
		String status="fail";
		Session session=null;
		String sql="";
		Transaction txn=null;
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			
			if("ByMdId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set CommissionMark='"+commMark+"' ,Commission="+comm+",KycCommissionMark='"+kyccommMark+"' ,KycCommission="+kyccomm+" where Operator='"+operator+"' and MDS_ID='"+userId+"'";
			}else if("ByDsId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set CommissionMark='"+commMark+"' ,Commission="+comm+",KycCommissionMark='"+kyccommMark+"' ,KycCommission="+kyccomm+" where Operator='"+operator+"' and DS_ID='"+userId+"'";
			}else if("ByAgentId".equalsIgnoreCase(userType)){
				sql="update Agent_Charge_Commission set CommissionMark='"+commMark+"' ,Commission="+comm+",KycCommissionMark='"+kyccommMark+"' ,KycCommission="+kyccomm+" where Operator='"+operator+"' and AgentId ="+Long.parseLong(userId);
			}
			
			
			System.out.println(sql);
			Query query=session.createSQLQuery(sql);
			query.executeUpdate();
			status="Success";
			
			txn.commit();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			txn.rollback();
			status="fail";
			System.out.println("Exception in WLMDShareDao UpdateShare");
		}
		finally
		{
			session.close();
		}
		return status;
	}
	*/

}
