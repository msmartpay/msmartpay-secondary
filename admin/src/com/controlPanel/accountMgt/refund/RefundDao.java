package com.controlPanel.accountMgt.refund;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class RefundDao 
{
	
    /**
     *  @author Manoj
     *  This method will give the details of any transaction
     */
	
    public final HashMap getTran(String tranNo, String fromDate, String toDate) 
    {
		HashMap map =new HashMap();
		Session session = null;
        Query qry = null;
        String sqlQuery=null;
        
        try
        {
        	session = HibernateSession.getSessionFactory().openSession();
        	tranNo.trim();
        	String id=" l.tran_id='"+tranNo+"'";
        	//System.out.println(id);
        	StringBuffer st=new StringBuffer();
        	//st.append("select l.tran_id,l.date_of_recharge,l.mobile_number,l.mobile_operator,l.service,l.amount,l.status,ad.agent_initial FROM live_recharge l,agent_transaction_details a where l.tran_id=a.transaction_no");
        	st.append("select top(1) l.tran_id,l.date_of_recharge,l.mobile_number,l.mobile_operator,l.service,l.amount,l.status,l.USessionID,a.transaction_id,ad.agent_initial+convert(varchar,l.user_id) as Agid,a.Tran_status,l.ApiProvider FROM live_recharge l,agent_transaction_details a,agent_details ad where l.tran_id=a.transaction_no and l.user_id=ad.agent_id and l.date_of_recharge between '"+fromDate+"' and '"+toDate+"' and ");
        	st.append(id);
        	sqlQuery=st.toString();
        	qry = session.createSQLQuery(sqlQuery);
        	List list =qry.list();
        	Iterator itr = list.iterator();
        	Object row[]  ;
        	
        	while(itr.hasNext())
        	{
        		row = (Object[])itr.next();
        		map.put("tran_id", row[0]);
        		map.put("date_of_recharge", row[1]);
        		map.put("mobile_number", row[2]);
        		map.put("mobile_operator",  row[3]);
        		map.put("service", row[4]);
        		map.put("amount", row[5]);
        		map.put("status", row[6]);
        		map.put("USessionID",  row[7]);
        		map.put("transaction_id", row[8]);
        		String agent_id=row[9].toString();
        		map.put("user_id", agent_id);
        		map.put("Tran_status", row[10]);
        		map.put("ApiProvider", row[11]);
        	}
        }
        catch(Exception e)
        {
        	System.out.println("Exception in RefundDao,getTran ");
        	System.out.println(e.toString());
        }
        finally
        {
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in RefundDao,getTran ");
				System.out.println(e.toString());
			}
        }
		return map;
	}
    
    public final HashMap getTran(String tranNo) 
    {
		HashMap map =new HashMap();
		Session session = null;
        Query qry = null;
        String sqlQuery=null;
        
        try
        {
        	session = HibernateSession.getSessionFactory().openSession();
        	tranNo.trim();
        	String id= " l.tran_id='"+tranNo+"'";
        	//System.out.println(id);
        	StringBuffer st=new StringBuffer();
        	//st.append("select l.tran_id,l.date_of_recharge,l.mobile_number,l.mobile_operator,l.service,l.amount,l.status,ad.agent_initial FROM live_recharge l,agent_transaction_details a where l.tran_id=a.transaction_no");
        	st.append("select top(1) l.tran_id,l.date_of_recharge,l.mobile_number,l.mobile_operator,l.service,l.amount,l.status,l.USessionID,a.transaction_id,ad.agent_initial+convert(varchar,l.user_id) as Agid,a.Tran_status,l.ApiProvider FROM live_recharge l,agent_transaction_details a,agent_details ad where l.tran_id=a.transaction_no and l.user_id=ad.agent_id and ");
        	st.append(id);
        	sqlQuery=st.toString();
        	qry = session.createSQLQuery(sqlQuery);
        	List list =qry.list();
        	Iterator itr = list.iterator();
        	Object row[]  ;
        	
        	while(itr.hasNext())
        	{
        		row = (Object[])itr.next();
        		map.put("tran_id", row[0]);
        		map.put("date_of_recharge", row[1]);
        		map.put("mobile_number", row[2]);
        		map.put("mobile_operator",  row[3]);
        		map.put("service", row[4]);
        		map.put("amount", row[5]);
        		map.put("status", row[6]);
        		map.put("USessionID",  row[7]);
        		map.put("transaction_id", row[8]);
        		String agent_id=row[9].toString();
        		map.put("user_id", agent_id);
        		map.put("Tran_status", row[10]);
        		map.put("ApiProvider", row[11]);
        	}
        }
        catch(Exception e)
        {
        	System.out.println("Exception in RefundDao,getTran ");
        	System.out.println(e.toString());
        }
        finally
        {
			try
			{
				session.flush();
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in RefundDao,getTran ");
				System.out.println(e.toString());
			}
        }
		return map;
	}

	public String updateSuccessTran(String tranID, String curStatus,String updatedStatus, String ipAdd, String portalId)
	{
		String status="NotUpdated";
		Session session = null;
        Query qry = null;
        String sqlQuery=null;
        String liveStatus="";
        String agentStatus="";
        Transaction tran=null;
        String AGTranId="";
        List list ;
    	Iterator itr ;
    	Object row[];
    	
        try
        {
        	session = HibernateSession.getSessionFactory().openSession();
        	tran=session.beginTransaction();
        	//sqlQuery="select l.status,a.Tran_status from live_recharge l,agent_transaction_details a where l.tran_id=a.Transaction_No and tran_id='"+tranID+"'";
        	sqlQuery="select l.status from live_recharge l where tran_id='"+tranID+"'";
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	liveStatus= qry.uniqueResult().toString();
        	//System.out.println("live status of tran " +liveStatus);
        	sqlQuery="select a.Tran_status,a.Transaction_Id from agent_transaction_details a where transaction_no='"+tranID+"'";
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	list=qry.list();
        	itr=list.iterator();
        	
        	while(itr.hasNext())
        	{
        		row=(Object[])itr.next();
        		agentStatus=row[0].toString();
        		AGTranId=row[1].toString();
        	}
        	//System.out.println("a.Transaction_Id is ::"+AGTranId);
        	//System.out.println("Agent status of tran "+agentStatus);
        	
        	String service="";
    		String DeductedAmt="";
    		String DistComm="";
    		String distributor_id="";
    		String agent_id="";
    		String refundService="";
    		String tranIdInitial="";
    		String balance="";
    		String balAmtARefund="";
    		String refundTranId="";
    		
        	
            if(liveStatus.equalsIgnoreCase("Success") && agentStatus.equalsIgnoreCase("Success")&& updatedStatus.equalsIgnoreCase("SuccessRefund"))
            {
            	//System.out.println("we are in live =fail agent =Pending and action =refund");
            	sqlQuery="SELECT a.service,a.DeductedAmt,b.Dist_Comm,c.agent_id,c.distributor_id FROM agent_transaction_details a,live_recharge b,agent_details c where a.Transaction_no='"+tranID+"' and a.Tran_status!='refunded' and a.Transaction_no=b.tran_id and b.user_id=c.agent_id and a.agent_id=c.agent_id";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	list=qry.list();
            	itr=list.iterator();
            	while(itr.hasNext())
            	{
            		row = (Object[])itr.next();
            		service=row[0].toString();
            		DeductedAmt=row[1].toString();
            		DistComm=row[2].toString();
            		agent_id=row[3].toString();
            		distributor_id=row[4].toString();
            	}
            	//System.out.println(service);
            	//System.out.println(DeductedAmt);
            	//	System.out.println(DistComm);
            	//	System.out.println(agent_id);
            	//	System.out.println(distributor_id);
            	sqlQuery="select totCash-usedcash from agent_amount where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	balance= String.valueOf(qry.uniqueResult());
            	//System.out.println("balance of agent is --- "+balance);
            	balAmtARefund=String.valueOf(Double.parseDouble(balance)+Double.parseDouble(DeductedAmt));
            	//System.out.println("balAmtARefund is ----"+balAmtARefund);
            	sqlQuery="select CONVERT(VARCHAR(8), GETDATE(), 112)";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	String datepart=qry.uniqueResult().toString();
            	//System.out.println("datepart is ----------- "+datepart);
            	if(service.equalsIgnoreCase("liveMobRech"))
            	{
            		tranIdInitial="RMR"+datepart;
            		refundService="RliveMobRech";
            	}
            	else if(service.equalsIgnoreCase("liveDTHRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDTHRech";
            	}
            	else if(service.equalsIgnoreCase("liveDCRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDCRech";
            	}
            	else if(service.equalsIgnoreCase("liveBillpay"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveBillpay";
            	}else {
            		tranIdInitial="R"+datepart;
            		refundService="R"+service;
            	}
            	//System.out.println("tranIdInitial ----- "+tranIdInitial);
            	//System.out.println("refundService ----- "+refundService);
            	sqlQuery="update agent_transaction_details set Tran_status='Refunded',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Bal_amt_b_Refund='"+balance+"',refunded_amount='"+DeductedAmt+"',Bal_Amount_A_upd='"+balAmtARefund+"',Remark='Refunded on "+tranID+"' where Transaction_no='"+tranID+"' and Tran_status!='refunded'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update live_recharge set status='Failure' where tran_id='"+tranID+"' and status!='Failure'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update agent_amount set usedcash=usedcash-'"+DeductedAmt+"',last_amount='"+DeductedAmt+"' where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="insert  into Agent_Refund__Online_Transaction_Details(Tran_Initial,Service,Transaction_id,Refunded_date,Updated_user,Remark) values('"+tranIdInitial+"','"+service+"','"+tranID+"',GETDATE(),'"+portalId+"','Refund')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="select CONVERT(varchar(15),Tran_Initial)+convert(varchar(15),Tran_no)  from Agent_Refund__Online_Transaction_Details where Transaction_id='"+tranID+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	refundTranId=qry.uniqueResult().toString();
            	sqlQuery="insert into agent_Transaction_Details(" +
            	"Transaction_Id,Agent_id,UserType,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession," +
            	"DeductedAmt, Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date, updated_time,Agent_F_balAmt,IpAddress,Remark) values(" +
            	"dbo.getTransactionID(),'"+agent_id+"','agent','"+distributor_id+"','"+refundTranId+"',getdate(),'"+refundService+"','"+balance+"','"+DeductedAmt+"','00.00','"+DeductedAmt+"','"+balAmtARefund+"','credit','Success',GETDATE(),GETDATE(),'"+balAmtARefund+"','"+ipAdd+"','Refunded on "+tranID+"')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	status="5";
            }else
            	{
            		status="9";
            	}
        	tran.commit();
        }
        catch(Exception e)
        {
        	try
        	{
        		tran.rollback();
        	}catch(Exception ex){
        		System.out.println("Exception in RefundDao,updateTran");
				System.out.println(ex.toString());
        	}
        	status="NotUpdated";
        	System.out.println("Exception in RefundDao,updateTran");
        	System.out.println(e.toString());
        }
        finally
        {
        	try
        	{
        		session.flush();
        		session.close();
        	}
        	catch(Exception e)
        	{
        		System.out.println("Exception in RefundDao,updateTran");
				System.out.println(e.toString());
        	}
        }
        return status;
	}
	
	public String updateTran(String tranID, String curStatus,String updatedStatus, String ipAdd, String portalId,String tranRefId)
	{
		String status="NotUpdated";
		Session session = null;
        Query qry = null;
        String sqlQuery=null;
        String liveStatus="";
        String agentStatus="";
        Transaction tran=null;
        String AGTranId="";
        List list ;
    	Iterator itr ;
    	Object row[];
    	
        try
        {
        	session = HibernateSession.getSessionFactory().openSession();
        	tran=session.beginTransaction();
        	//sqlQuery="select l.status,a.Tran_status from live_recharge l,agent_transaction_details a where l.tran_id=a.Transaction_No and tran_id='"+tranID+"'";
        	sqlQuery="select l.status from live_recharge l where tran_id='"+tranID+"'";
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	liveStatus= qry.uniqueResult().toString();
        	//System.out.println("live status of tran " +liveStatus);
        	sqlQuery="select a.Tran_status,a.Transaction_Id from agent_transaction_details a where transaction_no='"+tranID+"'";
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	list=qry.list();
        	itr=list.iterator();
        	
        	while(itr.hasNext())
        	{
        		row=(Object[])itr.next();
        		agentStatus=row[0].toString();
        		AGTranId=row[1].toString();
        	}
        	//System.out.println("a.Transaction_Id is ::"+AGTranId);
        	//System.out.println("Agent status of tran "+agentStatus);
        	
        	String service="";
    		String DeductedAmt="";
    		String DistComm="";
    		String distributor_id="";
    		String agent_id="";
    		String refundService="";
    		String tranIdInitial="";
    		String balance="";
    		String balAmtARefund="";
    		String refundTranId="";
    		
        	if(liveStatus.equalsIgnoreCase(updatedStatus) && agentStatus.equalsIgnoreCase(updatedStatus))
        	{
        		status="0";
        	}else if(liveStatus.equalsIgnoreCase("success") && agentStatus.equalsIgnoreCase("success"))
        	{
        		status="1";
            }
        	// For prnding Pending and user want to change status Success
            else if(liveStatus.equalsIgnoreCase("Pending")&& agentStatus.equalsIgnoreCase("Pending") && updatedStatus.equalsIgnoreCase("success"))
            {
            	sqlQuery="update agent_transaction_details set Tran_status='Success',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Remark='Tran make success by "+portalId+"',Reference_id='"+tranRefId+"' where Transaction_no='"+tranID+"' and Tran_status!='success'";
        		////System.out.println(sqlQuery);
        		qry=session.createSQLQuery(sqlQuery);
        		qry.executeUpdate();
        		sqlQuery="update live_recharge set status='Success',USessionID='"+tranRefId+"' where tran_id='"+tranID+"' and status!='success'";
        		qry=session.createSQLQuery(sqlQuery);
        		//System.out.println(sqlQuery);
        		qry.executeUpdate();
            	status="2";
            	
            }else if(liveStatus.equalsIgnoreCase("Failure")&& (agentStatus.equalsIgnoreCase("Refunded")||agentStatus.equalsIgnoreCase("failure")))
            {
            	//System.out.println("we are in Failure and Refunded");
               	status="3";
            }else if(liveStatus.equalsIgnoreCase("success") && agentStatus.equalsIgnoreCase("Pending")&& updatedStatus.equalsIgnoreCase("success"))
            {
            	sqlQuery="update agent_transaction_details set Tran_status='Success',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Reference_id='"+tranRefId+"' where Transaction_no='"+tranID+"' and Tran_status!='success'";
            	////System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update live_recharge set status='Success',USessionID='"+tranRefId+"' where tran_id='"+tranID+"' and status!='success'";
            	qry=session.createSQLQuery(sqlQuery);
            	//System.out.println(sqlQuery);
            	qry.executeUpdate();
            	status="4";
            }
            else if(liveStatus.equalsIgnoreCase("Failure") && agentStatus.equalsIgnoreCase("Pending")&& updatedStatus.equalsIgnoreCase("refund"))
            {
            	//System.out.println("we are in live =fail agent =Pending and action =refund");
            	sqlQuery="SELECT a.service,a.DeductedAmt,b.Dist_Comm,c.agent_id,c.distributor_id FROM agent_transaction_details a,live_recharge b,agent_details c where a.Transaction_no='"+tranID+"' and a.Tran_status!='refunded' and a.Transaction_no=b.tran_id and b.user_id=c.agent_id and a.agent_id=c.agent_id";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	list=qry.list();
            	itr=list.iterator();
            	while(itr.hasNext())
            	{
            		row = (Object[])itr.next();
            		service=row[0].toString();
            		DeductedAmt=row[1].toString();
            		DistComm=row[2].toString();
            		agent_id=row[3].toString();
            		distributor_id=row[4].toString();
            	}
            	//System.out.println(service);
            	//System.out.println(DeductedAmt);
            	//	System.out.println(DistComm);
            	//	System.out.println(agent_id);
            	//	System.out.println(distributor_id);
            	sqlQuery="select totCash-usedcash from agent_amount where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	balance= String.valueOf(qry.uniqueResult());
            	//System.out.println("balance of agent is --- "+balance);
            	balAmtARefund=String.valueOf(Double.parseDouble(balance)+Double.parseDouble(DeductedAmt));
            	//System.out.println("balAmtARefund is ----"+balAmtARefund);
            	sqlQuery="select CONVERT(VARCHAR(8), GETDATE(), 112)";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	String datepart=qry.uniqueResult().toString();
            	//System.out.println("datepart is ----------- "+datepart);
            	if(service.equalsIgnoreCase("liveMobRech"))
            	{
            		tranIdInitial="RMR"+datepart;
            		refundService="RliveMobRech";
            	}
            	else if(service.equalsIgnoreCase("liveDTHRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDTHRech";
            	}
            	else if(service.equalsIgnoreCase("liveDCRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDCRech";
            	}
            	else if(service.equalsIgnoreCase("liveBillpay"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveBillpay";
            	}
            	else if(service.equalsIgnoreCase("DMR-E-REMIT"))
            	{
            		tranIdInitial="RDMR"+datepart;
            		refundService="RDMR-E-REMIT";
            	}
            	else if(service.equalsIgnoreCase("DMR-E-ACCOUNT"))
            	{
            		tranIdInitial="RAV"+datepart;
            		refundService="RDMR-E-ACCOUNT";
            	}else if(service.equalsIgnoreCase("DMR-IP-REMIT"))
            	{
            		tranIdInitial="RDMR"+datepart;
            		refundService="RDMR-IP-REMIT";
            	}
            	else if(service.equalsIgnoreCase("DMR-IP-ACCOUNT"))
            	{
            		tranIdInitial="RAV"+datepart;
            		refundService="RDMR-IP-ACCOUNT";
            	}else{
            		tranIdInitial="R"+datepart;
            		refundService="R"+service;
            	}
            	//System.out.println("tranIdInitial ----- "+tranIdInitial);
            	//System.out.println("refundService ----- "+refundService);
            	sqlQuery="update agent_transaction_details set Tran_status='Refunded',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Bal_amt_b_Refund='"+balance+"',refunded_amount='"+DeductedAmt+"',Bal_Amount_A_upd='"+balAmtARefund+"',Remark='Refunded on "+tranID+"' where Transaction_no='"+tranID+"' and Tran_status!='refunded'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update live_recharge set status='Failure' where tran_id='"+tranID+"' and status!='Failure'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update agent_amount set usedcash=usedcash-'"+DeductedAmt+"',last_amount='"+DeductedAmt+"' where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="insert  into Agent_Refund__Online_Transaction_Details(Tran_Initial,Service,Transaction_id,Refunded_date,Updated_user,Remark) values('"+tranIdInitial+"','"+service+"','"+tranID+"',GETDATE(),'"+portalId+"','Refund')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="select CONVERT(varchar(15),Tran_Initial)+convert(varchar(15),Tran_no)  from Agent_Refund__Online_Transaction_Details where Transaction_id='"+tranID+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	refundTranId=qry.uniqueResult().toString();
            	sqlQuery="insert into agent_Transaction_Details(" +
            	"Transaction_Id,Agent_id,UserType,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession," +
            	"DeductedAmt, Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date, updated_time,Agent_F_balAmt,IpAddress,Remark) values(" +
            	"dbo.getTransactionID(),'"+agent_id+"','agent','"+distributor_id+"','"+refundTranId+"',getdate(),'"+refundService+"','"+balance+"','"+DeductedAmt+"','00.00','"+DeductedAmt+"','"+balAmtARefund+"','credit','Success',GETDATE(),GETDATE(),'"+balAmtARefund+"','"+ipAdd+"','Refunded on "+tranID+"')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	status="5";
            }
            
            else if(liveStatus.equalsIgnoreCase("Failure") && agentStatus.equalsIgnoreCase("Success")&& updatedStatus.equalsIgnoreCase("Refund"))
            {
            	//System.out.println("we are into live= Failure agent = Success and action =Refund");
            	sqlQuery="SELECT a.Service,a.DeductedAmt,b.Dist_Comm,c.agent_id,c.distributor_id FROM agent_transaction_details a,live_recharge b,agent_details c where a.Transaction_no='"+tranID+"' and a.Transaction_no=b.tran_id and b.user_id=c.agent_id";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	list=qry.list();
            	itr=list.iterator();
            	
            	while(itr.hasNext())
            	{
            		row = (Object[])itr.next();
            		service=row[0].toString();
            		DeductedAmt=row[1].toString();
            		DistComm=row[2].toString();
            		agent_id=row[3].toString();
            		distributor_id=row[4].toString();
            	}
            	sqlQuery="select totCash-usedcash from agent_amount where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	balance=qry.uniqueResult().toString();
            	balAmtARefund=String.valueOf((Double.parseDouble(balance)+Double.parseDouble(DeductedAmt))) ;
            	sqlQuery="select CONVERT(VARCHAR(8), GETDATE(), 112)";
            	//System.out.println("select query is "+sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	String datepart=qry.uniqueResult().toString();
            	//System.out.println("datepart is "+datepart);
            	if(service.equalsIgnoreCase("liveMobRech"))
            	{
            		tranIdInitial="RMR"+datepart;
            		refundService="RliveMobRech";
            	}
            	else if(service.equalsIgnoreCase("liveDTHRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDTHRech";
                		
            	}
            	else if(service.equalsIgnoreCase("liveDCRech"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveDCRech";
            	}
            	else if(service.equalsIgnoreCase("liveBillpay"))
            	{
            		tranIdInitial="RDR"+datepart;
            		refundService="RliveBillpay";
            	}else {
            		tranIdInitial="R"+datepart;
            		refundService="R"+service;
            	}
            	sqlQuery="update agent_transaction_details set Tran_status='Refunded',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Bal_Amt_b_Refund='"+balance+"',refunded_amount='"+DeductedAmt+"',Bal_Amount_A_upd='"+balAmtARefund+"',remark='Refunded on "+tranID+"' where Transaction_no='"+tranID+"' and Tran_status!='refunded'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update live_recharge set status='Failure' where tran_id='"+tranID+"' and status!='Failure'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="update agent_amount set usedcash=usedcash-'"+DeductedAmt+"',last_amount='"+DeductedAmt+"' where agent_id='"+agent_id+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="insert  into Agent_Refund__Online_Transaction_Details(Tran_Initial,Service,Transaction_id,Refunded_date,Updated_user,Remark) values" +
            	"('"+tranIdInitial+"','"+service+"','"+tranID+"',getdate(),'"+portalId+"','Refund')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	sqlQuery="select CONVERT(varchar(15),Tran_Initial)+convert(varchar(15),Tran_no)  from Agent_Refund__Online_Transaction_Details where Transaction_id='"+tranID+"'";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	refundTranId=qry.uniqueResult().toString();
            	//System.out.println("refundTranId is " + refundTranId);
            	sqlQuery="insert into  agent_Transaction_Details(Transaction_Id,Agent_id,UserType,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession,DeductedAmt, Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date, updated_time,Agent_F_balAmt,IpAddress,Remark) values" +
            	"(dbo.getTransactionID(),'"+agent_id+"','agent','"+distributor_id+"','"+refundTranId+"',GETDATE(),'"+refundService+"','"+balance+"','"+DeductedAmt+"','00.00','"+DeductedAmt+"','"+balAmtARefund+"','credit','Success',GETDATE(),GETDATE(),'"+balAmtARefund+"','"+ipAdd+"','Refunded on "+tranID+"')";
            	//System.out.println(sqlQuery);
            	qry=session.createSQLQuery(sqlQuery);
            	qry.executeUpdate();
            	status="5";
            	
            	}else if (liveStatus.equalsIgnoreCase("pending") && agentStatus.equalsIgnoreCase("Success") && updatedStatus.equalsIgnoreCase("success"))
            	{
            		sqlQuery="update live_recharge set status='Success' where tran_id='"+tranID+"' and status='pending'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		status="6";
            	}else if(liveStatus.equalsIgnoreCase("pending") && agentStatus.equalsIgnoreCase("pending") && updatedStatus.equalsIgnoreCase("Refund"))
            	{
            		//System.out.println("we are in live =pending agent =Pending and action =refund");
            		sqlQuery="SELECT a.service,a.DeductedAmt,b.Dist_Comm,c.agent_id,c.distributor_id FROM agent_transaction_details a,live_recharge b,agent_details c where a.Transaction_no='"+tranID+"' and a.Tran_status!='refunded' and a.Transaction_no=b.tran_id and b.user_id=c.agent_id and a.agent_id=c.agent_id";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		list=qry.list();
            		itr=list.iterator();
            		
            		while(itr.hasNext())
            		{
            			row = (Object[])itr.next();
            			service=row[0].toString();
            			DeductedAmt=row[1].toString();
            			DistComm=row[2].toString();
            			agent_id=row[3].toString();
            			distributor_id=row[4].toString();
            		}
//            		System.out.println(service);
//            		System.out.println(DeductedAmt);
//            		System.out.println(DistComm);
//            		System.out.println(agent_id);
//            		System.out.println(distributor_id);
            		sqlQuery="select totCash-usedcash from agent_amount where agent_id='"+agent_id+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		balance= String.valueOf(qry.uniqueResult());
            		//System.out.println("balance of agent is --- "+balance);
            		balAmtARefund=String.valueOf(Double.parseDouble(balance)+Double.parseDouble(DeductedAmt));
            		//System.out.println("balAmtARefund is ----"+balAmtARefund);
            		sqlQuery="select CONVERT(VARCHAR(8), GETDATE(), 112)";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		String datepart=qry.uniqueResult().toString();
            		//System.out.println("datepart is ----------- "+datepart);
            		
            		if(service.equalsIgnoreCase("liveMobRech"))
            		{
            			tranIdInitial="RMR"+datepart;
            			refundService="RliveMobRech";
            		}
            		else if(service.equalsIgnoreCase("liveDTHRech"))
            		{
            			tranIdInitial="RDR"+datepart;
            			refundService="RliveDTHRech";
            		}
            		else if(service.equalsIgnoreCase("liveDCRech"))
            		{
            			tranIdInitial="RDR"+datepart;
            			refundService="RliveDCRech";
            		}
            		else if(service.equalsIgnoreCase("liveBillpay"))
                	{
                		tranIdInitial="RDR"+datepart;
                		refundService="RliveBillpay";
                	}else {
                		tranIdInitial="R"+datepart;
                		refundService="R"+service;
                	}
//            		System.out.println("tranIdInitial ----- "+tranIdInitial);
//            		System.out.println("refundService ----- "+refundService);
            		sqlQuery="update agent_transaction_details set Tran_status='Refunded',updated_date=GETDATE(),updated_time=GETDATE(),UpdIpAddress='"+ipAdd+"',Bal_amt_b_Refund='"+balance+"',refunded_amount='"+DeductedAmt+"',Bal_Amount_A_upd='"+balAmtARefund+"',Remark='Refunded on"+tranID+"' where Transaction_no='"+tranID+"' and Tran_status!='refunded'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update live_recharge set status='Failure' where tran_id='"+tranID+"' and status!='Failure'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update agent_amount set usedcash=usedcash-'"+DeductedAmt+"',last_amount='"+DeductedAmt+"' where agent_id='"+agent_id+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="insert  into Agent_Refund__Online_Transaction_Details(Tran_Initial,Service,Transaction_id,Refunded_date,Updated_user,Remark) values('"+tranIdInitial+"','"+service+"','"+tranID+"',GETDATE(),'"+portalId+"','Refund')";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="select CONVERT(varchar(15),Tran_Initial)+convert(varchar(15),Tran_no)  from Agent_Refund__Online_Transaction_Details where Transaction_id='"+tranID+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		refundTranId=qry.uniqueResult().toString();
            		sqlQuery="insert into agent_Transaction_Details(" +
            		"Transaction_Id,Agent_id,UserType,distributor_id,Transaction_No,Date_of_Transaction,Service,Agent_balAmt_b_Ded,ReqAmount,Commession," +
            		"DeductedAmt, Agent_balAmt_A_Ded,Action_on_bal_amt,Tran_status,Updated_date, updated_time,Agent_F_balAmt,IpAddress,Remark) values(" +
            		"dbo.getTransactionID(),'"+agent_id+"','agent','"+distributor_id+"','"+refundTranId+"',getdate(),'"+refundService+"','"+balance+"','"+DeductedAmt+"','00.00','"+DeductedAmt+"','"+balAmtARefund+"','credit','Success',GETDATE(),GETDATE(),'"+balAmtARefund+"','"+ipAdd+"','Refund on "+tranID+"')";
            		System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		status="7";
            		
            	}else if(liveStatus.equalsIgnoreCase("pending")&& agentStatus.equalsIgnoreCase("Refunded") && updatedStatus.equalsIgnoreCase("Refund"))
            	{
            		sqlQuery="update live_recharge set status='Failure' where tran_id='"+tranID+"' and status!='success'";
            		qry=session.createSQLQuery(sqlQuery);
            		//System.out.println(sqlQuery);
            		qry.executeUpdate();
            		status="8";
            	}
            	else
            	{
            		status="9";
            	}
        	tran.commit();
        }
        catch(Exception e)
        {
        	try
        	{
        		tran.rollback();
        	}catch(Exception ex){
        		System.out.println("Exception in RefundDao,updateTran");
				System.out.println(ex.toString());
        	}
        	status="NotUpdated";
        	System.out.println("Exception in RefundDao,updateTran");
        	System.out.println(e.toString());
        }
        finally
        {
        	try
        	{
        		session.flush();
        		session.close();
        	}
        	catch(Exception e)
        	{
        		System.out.println("Exception in RefundDao,updateTran");
				System.out.println(e.toString());
        	}
        }
        return status;
	}

	public final String getTranNo(String tranID,String fromDate, String toDate) 
	{
		Session session = null;
        Query qry = null;
        String sqlQuery=null;
        String ID="";
        try
        {
        	session = HibernateSession.getSessionFactory().openSession();
			sqlQuery="select a.Transaction_No from agent_transaction_details a join live_recharge b on a.Transaction_No=b.tran_id where (a.Transaction_Id='"+tranID+"' OR a.Transaction_No='"+tranID+"' OR a.Reference_id ='"+tranID+"' or b.USessionID='"+tranID+"')";
        	//sqlQuery="select Transaction_No from agent_transaction_details where Transaction_Id='"+tranID+"'";
            qry = session.createSQLQuery(sqlQuery);
            ID=qry.uniqueResult().toString();
        }
        catch(Exception e)
        {
        	System.out.println("Exception in RefundDao,getTranNo");
			System.out.println(e.toString());
			e.printStackTrace();
        }
        finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
        return ID;
	}
	
	public String RBLRefund(String tranId,String Status,String service,String IpAddress){
		Session session = null;
        Connection con=null;
        String status="";
		try {
			
			session = HibernateSession.getSessionFactory().openSession();
			session.beginTransaction(); 		  
        	con=session.connection(); 		  
        	CallableStatement cstmt =null;
        	
        	cstmt=con.prepareCall("{call SMART_TRANSACTION_REFUND_BY_ADMIN(?,?,?,?,?)}");
        	
        	cstmt.setString(1,tranId);
        	cstmt.setString(2,Status);
        	cstmt.setString(3,service);
        	cstmt.setString(4,IpAddress);
        	cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
        	cstmt.execute();
        	status=cstmt.getString(5);
        	
        	session.getTransaction().commit(); 	 
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Process aborted due to technical failure.";
		}finally{
			try {
				if(con!=null)
					con.close();
				if(session!=null)
					session.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return status;
	}
}
