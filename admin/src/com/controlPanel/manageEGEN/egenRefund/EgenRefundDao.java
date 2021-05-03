package com.controlPanel.manageEGEN.egenRefund;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

class EgenRefundDao {
	
	
	public final HashMap getTran(String RefrenceId,  String fromDate, String toDate) {
		HashMap map =new HashMap();
		Session session = HibernateSession1.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        RefrenceId.trim();
        try
        {
        	/**
        	 * Please attention all query is database dependent
        	 * so please first change the name of database
        	 * use this for live code db OnlineRechAPI_Live_db
        	 * Testing tran == OnlineRechAPI_Live_db
        	 */
        	
        	sqlQuery="select a.Corporate_Agent_Id,b.Transaction_id,substring(a.Corporate_Transaction_Id,7,20) as CorpoateID,a.Date_of_Transaction,b.MobileDth_No,b.MobileDth_Operator,a.Service,a.Tran_Amount,b.Status,a.Tran_Status,b.Vendor_txn_Id from Rech_API_Agent_Transaction_details a,Rech_API_Agent_RechMobileDth_Details b where a.Refrence_Id=b.Refrence_Id and a.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' and a.Refrence_Id='"+RefrenceId+"'";
        	System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	List list =qry.list();
        	Iterator itr = list.iterator();
        	Object row[]  ;
        	while(itr.hasNext())
        	{
        		row = (Object[])itr.next();
        		map.put("user_id", row[0]);
        		map.put("tran_id", row[1]);
        		map.put("CorpoateID", row[2]);
        		map.put("date_of_recharge", row[3]);
        		map.put("mobile_number", row[4]);
        		map.put("mobile_operator",  row[5]);
        		map.put("service", row[6]);
        		map.put("amount", row[7]);
        		map.put("status", row[8]);
        		map.put("Tran_status", row[9]);
        		map.put("USessionID",  row[10]);
        		map.put("RefrenceId",RefrenceId);
        	}

        }
        catch(Exception e)
        {
        	System.out.println("Exception in EgenRefundDao and method is getTran");
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
				System.out.println("Exception in EgenRefundDao,getTran");
				System.out.println(e.toString());
			}
			
		}
		return map;
	}
	
	
	public final String getTranNo(String TranID, String CorTranId, String vendorID, String fromDate, String toDate) {
		Session session = HibernateSession1.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        String ID="";
        try
        {
        	if(!TranID.equals("")&& !CorTranId.equals("") && !vendorID.equals("")){     		
        		sqlQuery="select a.Refrence_Id from Rech_API_Agent_Transaction_details  a,Rech_API_Agent_RechMobileDth_Details b where substring(a.Corporate_Transaction_Id,7,20)='"+CorTranId+"' and b.Transaction_id='"+TranID+"' and b.Vendor_txn_Id='"+vendorID+"' and a.Refrence_Id=b.Refrence_Id and a.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
        		
        	}
			
			else if(!TranID.equals("")&& !CorTranId.equals("")){
				sqlQuery="select a.Refrence_Id from Rech_API_Agent_Transaction_details  a,Rech_API_Agent_RechMobileDth_Details b where substring(a.Corporate_Transaction_Id,7,20)='"+CorTranId+"' and b.Transaction_id='"+TranID+"' and a.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
				
				
			}
			
			else if(!CorTranId.equals("") && !vendorID.equals("")){
				sqlQuery="select a.Refrence_Id from Rech_API_Agent_Transaction_details  a,OnlineRechAPI_Live_db.dbo.Rech_API_Agent_RechMobileDth_Details b where substring(a.Corporate_Transaction_Id,7,20)='"+CorTranId+"' and b.Vendor_txn_Id='"+vendorID+"' and a.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
				
				
			}
			else if (TranID != null && !TranID.equals("")){
				sqlQuery="select Refrence_Id from Rech_API_Agent_RechMobileDth_Details  where Transaction_id='"+TranID+"' and convert(varchar(10),Date_of_Recharge,120) between '"+fromDate+"' and '"+toDate+"'";
				
				
			}else if (CorTranId != null && !CorTranId.equals("")){
				sqlQuery="select Refrence_Id from Rech_API_Agent_Transaction_details  where substring(Corporate_Transaction_Id,7,20)='"+CorTranId+"' and Date_of_Transaction between '"+fromDate+"' and '"+toDate+"'";
				
				
			}
        	
            qry = session.createSQLQuery(sqlQuery);
            //System.out.println("Sql Query is to take Refrence_Id corresponding parameter :: "+sqlQuery);
            ID=qry.uniqueResult().toString();
            
            

        }
        catch(Exception e)
        {
        	System.out.println("Exception in EgenRefundDao and method is getTranNo");
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
				System.out.println("Exception in EgenRefundDao,getTranNo");
				System.out.println(e.toString());
			}
			
		}
		return ID;
	}
	public String updateTran(String tranID, String curStatus,
			String updatedStatus, String ipAdd, String portalId, String refrenceId, String genId) {
		String status="NotUpdated";
		Session session = HibernateSession1.getSessionFactory().openSession();
        Query qry = null;
        String sqlQuery=null;
        String liveStatus="";
        String agentStatus="";
        Transaction tran=null;
        try
        {
        	tran=session.beginTransaction();
        	
        	sqlQuery="select status from Rech_API_Agent_RechMobileDth_Details where Transaction_id='"+tranID+"' and Refrence_Id='"+refrenceId+"'";
        	
        	
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	liveStatus= qry.uniqueResult().toString();
        	//System.out.println("live status of tran " +liveStatus);
        	sqlQuery="select tran_status from  Rech_API_Agent_Transaction_details where Refrence_Id='"+refrenceId+"'";
        	
        	
        	//System.out.println(sqlQuery);
        	qry = session.createSQLQuery(sqlQuery);
        	agentStatus=qry.uniqueResult().toString();
        	//System.out.println("Agent status of tran "+agentStatus);
        	List list ;
        	Iterator itr ;
        	Object row[];
        	double BalAmount=0.0;
    		double commission=0.0;
    		double refundedAmount=0.0;
    		String RefTranId="";
    		String service="";
    		String corporateAgentId="";
    		String subAgentId="";
        	if(liveStatus.equalsIgnoreCase(updatedStatus) && agentStatus.equalsIgnoreCase(updatedStatus)){
        		
        		status="0";
        	}else if(liveStatus.equalsIgnoreCase("success") && agentStatus.equalsIgnoreCase("success")){
        		
        		
        		status="1";
            }
        	// For prnding Pending and user want to change status Success
            else if(liveStatus.equalsIgnoreCase("Pending")&& agentStatus.equalsIgnoreCase("Pending") && updatedStatus.equalsIgnoreCase("success")){
            	
            	sqlQuery="update Rech_API_Agent_Transaction_details set Tran_status='Success',updated_date=GETDATE(),updated_time=GETDATE(),Updated_IP='"+ipAdd+"',Remark='Tran make success by "+portalId+"' where Refrence_Id='"+refrenceId+"' and Tran_status!='success'";
            	
            	
            	//System.out.println(sqlQuery);
        		qry=session.createSQLQuery(sqlQuery);
        		qry.executeUpdate();
        		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set status='Success' where Transaction_id='"+tranID+"' and Refrence_Id='"+refrenceId+"' and status!='success'";
        		
        		
        		qry=session.createSQLQuery(sqlQuery);
        		//System.out.println(sqlQuery);
        		qry.executeUpdate();
            	status="2";
            }else if(liveStatus.equalsIgnoreCase("Failure")&& (agentStatus.equalsIgnoreCase("Refunded")||agentStatus.equalsIgnoreCase("failure"))){
            	//System.out.println("we are in Failure and Refunded");
               	status="3";
            }else if(liveStatus.equalsIgnoreCase("success") && agentStatus.equalsIgnoreCase("Pending")&& updatedStatus.equalsIgnoreCase("success")){
            		
            		sqlQuery="update Rech_API_Agent_Transaction_details set Tran_status='Success',updated_date=GETDATE(),updated_time=GETDATE(),Updated_IP='"+ipAdd+"',Remark='Tran make success by "+portalId+"' where Refrence_Id='"+refrenceId+"' and Tran_status!='success'";
            		
            		
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set status='Success' where Transaction_id='"+tranID+"' and Refrence_Id='"+refrenceId+"' and status!='success'";
            		
            		
            		qry=session.createSQLQuery(sqlQuery);
            		//System.out.println(sqlQuery);
            		qry.executeUpdate();
            		status="4";
            		
            }
            else if(liveStatus.equalsIgnoreCase("Failure") && agentStatus.equalsIgnoreCase("Pending")&& updatedStatus.equalsIgnoreCase("refund")){
            		//System.out.println("we are in live =fail agent =Pending and action =refund");
            		
            		sqlQuery="update Rech_API_Agent_Transaction_details set Tran_Status='Refunded',Updated_Date=convert(varchar(10),getdate(),120),Updated_Time=convert(varchar(10),getdate(),108),Updated_IP='"+ipAdd+"',Updated_User='"+portalId+"',Remark=convert(varchar(50),'refund on')+' '+convert(varchar(10),getdate(),120) where Refrence_Id='"+refrenceId+"' and tran_status!='refunded'";
            		
            		
            		///System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set Status='Failure' where Refrence_Id='"+refrenceId+"'";
            		
            		
            		//System.out.println(sqlQuery); 
                    qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		//sqlQuery="select @BalAmount=Total_Bal_Amount,@commission=commission,@refundedAmount=Net_Tran_Amt,@RefTranId=Corporate_Transaction_Id,@service=Service,@corporateAgentId=T.Corporate_Agent_Id,@subAgentId=T.Sub_Agent_Id from dbo.Rech_API_Agent_Transaction_details T,dbo.Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id=@Transaction_id
            		sqlQuery="select Total_Bal_Amount,commission,Net_Tran_Amt,Corporate_Transaction_Id,Service,T.Corporate_Agent_Id,T.Sub_Agent_Id from Rech_API_Agent_Transaction_details T,Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id='"+refrenceId+"'";
            		// Live server Query
            		
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		list=qry.list();
            		itr=list.iterator();
            		
            		while(itr.hasNext()){
            			row=(Object[])itr.next();
            			BalAmount=Double.parseDouble(row[0].toString());
            			commission=Double.parseDouble(row[1].toString());
            			refundedAmount=Double.parseDouble(row[2].toString());
            			RefTranId=row[3].toString();
            			service=row[4].toString();
            			corporateAgentId=row[5].toString();
            			subAgentId=row[6].toString();
            		}
            		//refBalAmount = @BalAmount+@refundedAmount
            		//System.out.println("BalAmount is "+BalAmount);
            		//System.out.println("commission is "+commission);
            		//System.out.println("refundedAmount is "+refundedAmount);
            		double refBalAmount=BalAmount+refundedAmount; 
            		//System.out.println("refBalAmount amount is "+refBalAmount);
//            		sqlQuery="insert into OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details" +
//            				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
//            				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
//            				"values(@refundTranId,@RefTranId,@corporateAgentId,@subAgentId,convert(varchar(2),'R')+convert(varchar(20),@service),@refundedAmount,@refundedAmount,'credit',@BalAmount,@BalAmount+@refundedAmount,'success',@BalAmount+@refundedAmount,@updatedId,@updatedUser,@updatedId,convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),@RefTranId))";
//                     
                      
            		sqlQuery="insert into Rech_API_Agent_Transaction_details" +
    				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
    				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
    				"values('"+genId+"','"+RefTranId+"','"+corporateAgentId+"','"+subAgentId+"',convert(varchar(2),'R')+convert(varchar(20),'"+service+"'),'"+refundedAmount+"','"+refundedAmount+"','credit','"+BalAmount+"','"+refBalAmount+"','Success','"+refBalAmount+"','"+ipAdd+"','"+portalId+"','"+ipAdd+"',convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),'"+RefTranId+"'))";
            		
            		
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		
            		//sqlQuery="update  OnlineRechAPI_Live_db.Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-@refundedAmount,Total_Commission=Total_Commission-@commission,Total_Bal_Amount=Total_Bal_Amount+@refundedAmount,Available_Bal_Amount=Available_Bal_Amount+@refundedAmount where Corporate_Agent_Id=@corporateAgentId";
            		
            		sqlQuery="update  Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-"+refundedAmount+",Total_Commission=Total_Commission-"+commission+",Total_Bal_Amount=Total_Bal_Amount+"+refundedAmount+",Available_Bal_Amount=Available_Bal_Amount+"+refundedAmount+" where Corporate_Agent_Id='"+corporateAgentId+"'";
            		
            		
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		
            		status="5";
            	}
            else if(liveStatus.equalsIgnoreCase("Failure") && agentStatus.equalsIgnoreCase("Success")&& updatedStatus.equalsIgnoreCase("Refund")){
            		//System.out.println("we are into live= Failure agent = Success and action =Refund");
            		
            		sqlQuery="update Rech_API_Agent_Transaction_details set Tran_Status='Refunded',Updated_Date=convert(varchar(10),getdate(),120),Updated_Time=convert(varchar(10),getdate(),108),Updated_IP='"+ipAdd+"',Updated_User='"+portalId+"',Remark=convert(varchar(50),'refund on')+' '+convert(varchar(10),getdate(),120) where Refrence_Id='"+refrenceId+"' and tran_status!='refunded'";
            		
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set Status='Failure' where Refrence_Id='"+refrenceId+"'";
                    //System.out.println(sqlQuery); 
                    qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		//sqlQuery="select @BalAmount=Total_Bal_Amount,@commission=commission,@refundedAmount=Net_Tran_Amt,@RefTranId=Corporate_Transaction_Id,@service=Service,@corporateAgentId=T.Corporate_Agent_Id,@subAgentId=T.Sub_Agent_Id from dbo.Rech_API_Agent_Transaction_details T,dbo.Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id=@Transaction_id
            		sqlQuery="select Total_Bal_Amount,commission,Net_Tran_Amt,Corporate_Transaction_Id,Service,T.Corporate_Agent_Id,T.Sub_Agent_Id from Rech_API_Agent_Transaction_details T,Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id='"+refrenceId+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		list=qry.list();
            		itr=list.iterator();
            		
            		while(itr.hasNext()){
            			row=(Object[])itr.next();
            			BalAmount=Double.parseDouble(row[0].toString());
            			commission=Double.parseDouble(row[1].toString());
            			refundedAmount=Double.parseDouble(row[2].toString());
            			RefTranId=row[3].toString();
            			service=row[4].toString();
            			corporateAgentId=row[5].toString();
            			subAgentId=row[6].toString();
            		}
            		//refBalAmount = @BalAmount+@refundedAmount
            		//System.out.println("BalAmount is "+BalAmount);
            		//System.out.println("commission is "+commission);
            		//System.out.println("refundedAmount is "+refundedAmount);
            		double refBalAmount=BalAmount+refundedAmount; 
            		//System.out.println("refBalAmount amount is "+refBalAmount);
//            		sqlQuery="insert into OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details" +
//            				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
//            				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
//            				"values(@refundTranId,@RefTranId,@corporateAgentId,@subAgentId,convert(varchar(2),'R')+convert(varchar(20),@service),@refundedAmount,@refundedAmount,'credit',@BalAmount,@BalAmount+@refundedAmount,'success',@BalAmount+@refundedAmount,@updatedId,@updatedUser,@updatedId,convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),@RefTranId))";
//                     
                      
            		sqlQuery="insert into Rech_API_Agent_Transaction_details" +
    				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
    				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
    				"values('"+genId+"','"+RefTranId+"','"+corporateAgentId+"','"+subAgentId+"',convert(varchar(2),'R')+convert(varchar(20),'"+service+"'),'"+refundedAmount+"','"+refundedAmount+"','credit','"+BalAmount+"','"+refBalAmount+"','Success','"+refBalAmount+"','"+ipAdd+"','"+portalId+"','"+ipAdd+"',convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),'"+RefTranId+"'))";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		
            		//sqlQuery="update  OnlineRechAPI_Live_db.Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-@refundedAmount,Total_Commission=Total_Commission-@commission,Total_Bal_Amount=Total_Bal_Amount+@refundedAmount,Available_Bal_Amount=Available_Bal_Amount+@refundedAmount where Corporate_Agent_Id=@corporateAgentId";
            		
            		sqlQuery="update  Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-"+refundedAmount+",Total_Commission=Total_Commission-"+commission+",Total_Bal_Amount=Total_Bal_Amount+"+refundedAmount+",Available_Bal_Amount=Available_Bal_Amount+"+refundedAmount+" where Corporate_Agent_Id='"+corporateAgentId+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		
            		status="5";
            	}else if (liveStatus.equalsIgnoreCase("pending") && agentStatus.equalsIgnoreCase("Success") && updatedStatus.equalsIgnoreCase("success")){
            		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set status='Success' where Transaction_id='"+tranID+"' and Refrence_Id='"+refrenceId+"' and status!='success'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		status="6";
            	}else if(liveStatus.equalsIgnoreCase("pending") && agentStatus.equalsIgnoreCase("pending") && updatedStatus.equalsIgnoreCase("Refund")){
            		//System.out.println("we are in live =pending agent =Pending and action =refund");
            		
            		sqlQuery="update Rech_API_Agent_Transaction_details set Tran_Status='Refunded',Updated_Date=convert(varchar(10),getdate(),120),Updated_Time=convert(varchar(10),getdate(),108),Updated_IP='"+ipAdd+"',Updated_User='"+portalId+"',Remark=convert(varchar(50),'refund on')+' '+convert(varchar(10),getdate(),120) where Refrence_Id='"+refrenceId+"' and tran_status!='refunded'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		sqlQuery="update Rech_API_Agent_RechMobileDth_Details set Status='Failure' where Refrence_Id='"+refrenceId+"'";
                    //System.out.println(sqlQuery); 
                    qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		//sqlQuery="select @BalAmount=Total_Bal_Amount,@commission=commission,@refundedAmount=Net_Tran_Amt,@RefTranId=Corporate_Transaction_Id,@service=Service,@corporateAgentId=T.Corporate_Agent_Id,@subAgentId=T.Sub_Agent_Id from dbo.Rech_API_Agent_Transaction_details T,dbo.Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id=@Transaction_id
            		sqlQuery="select Total_Bal_Amount,commission,Net_Tran_Amt,Corporate_Transaction_Id,Service,T.Corporate_Agent_Id,T.Sub_Agent_Id from Rech_API_Agent_Transaction_details T,.Rech_API_Corporate_Agent_Amount A where T.Corporate_Agent_Id=A.Corporate_Agent_Id and  Refrence_Id='"+refrenceId+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		list=qry.list();
            		itr=list.iterator();
            		
            		while(itr.hasNext()){
            			row=(Object[])itr.next();
            			BalAmount=Double.parseDouble(row[0].toString());
            			commission=Double.parseDouble(row[1].toString());
            			refundedAmount=Double.parseDouble(row[2].toString());
            			RefTranId=row[3].toString();
            			service=row[4].toString();
            			corporateAgentId=row[5].toString();
            			subAgentId=row[6].toString();
            		}
            		//refBalAmount = @BalAmount+@refundedAmount
            		//System.out.println("BalAmount is "+BalAmount);
            		//System.out.println("commission is "+commission);
            		//System.out.println("refundedAmount is "+refundedAmount);
            		double refBalAmount=BalAmount+refundedAmount; 
            		//System.out.println("refBalAmount amount is "+refBalAmount);
//            		sqlQuery="insert into OnlineRechAPI_Live_db.dbo.Rech_API_Agent_Transaction_details" +
//            				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
//            				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
//            				"values(@refundTranId,@RefTranId,@corporateAgentId,@subAgentId,convert(varchar(2),'R')+convert(varchar(20),@service),@refundedAmount,@refundedAmount,'credit',@BalAmount,@BalAmount+@refundedAmount,'success',@BalAmount+@refundedAmount,@updatedId,@updatedUser,@updatedId,convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),@RefTranId))";
//                     
                      
            		sqlQuery="insert into Rech_API_Agent_Transaction_details" +
    				"(Corporate_Transaction_Id,Refrence_Id,Corporate_Agent_Id,Sub_Agent_Id,service,Tran_Amount,Net_Tran_Amt,action_On_Bal_Amt," +
    				"Previous_Bal_Amt,Updated_Bal_Amt,Tran_Status,Final_Bal_Amt,Tran_IP,Updated_User,Updated_IP,Remark)" +
    				"values('"+genId+"','"+RefTranId+"','"+corporateAgentId+"','"+subAgentId+"',convert(varchar(2),'R')+convert(varchar(20),'"+service+"'),'"+refundedAmount+"','"+refundedAmount+"','credit','"+BalAmount+"','"+refBalAmount+"','Success','"+refBalAmount+"','"+ipAdd+"','"+portalId+"','"+ipAdd+"',convert(varchar(20),'refund of')+' '+CONVERT(varchar(25),'"+RefTranId+"'))";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		
            		//sqlQuery="update  OnlineRechAPI_Live_db.Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-@refundedAmount,Total_Commission=Total_Commission-@commission,Total_Bal_Amount=Total_Bal_Amount+@refundedAmount,Available_Bal_Amount=Available_Bal_Amount+@refundedAmount where Corporate_Agent_Id=@corporateAgentId";
            		
            		sqlQuery="update  Rech_API_Corporate_Agent_Amount set Used_Cash=Used_Cash-"+refundedAmount+",Total_Commission=Total_Commission-"+commission+",Total_Bal_Amount=Total_Bal_Amount+"+refundedAmount+",Available_Bal_Amount=Available_Bal_Amount+"+refundedAmount+" where Corporate_Agent_Id='"+corporateAgentId+"'";
            		//System.out.println(sqlQuery);
            		qry=session.createSQLQuery(sqlQuery);
            		qry.executeUpdate();
            		status="7";
            	}
            else{
            	status="8";
            }
        	tran.commit();
        }
        catch(Exception e)
        {
        	System.out.println("Exception in EgenRefundDao and method is updateTran");
        	System.out.println(e.toString());
        	try{
        		tran.rollback();
        	}catch(Exception ex){
        		System.out.println("Exception in EgenRefundDao and method is updateTran");
            	System.out.println(e.toString());
        	}
        	status="NotUpdated";
        	System.out.println("Exception in EgenRefundDao and method is updateTran");
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
				System.out.println("Exception in EgenRefundDao ,updateTran");
				System.out.println(e.toString());
			}
		}
		return status;
	}
}
