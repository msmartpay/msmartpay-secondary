package com.report.DistributorAccountStatement;

import java.util.Iterator;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;
import com.common.LogWriter;

public class DistributorAccountStatementDao {

	public JSONArray getDistributorAccountStatementDetails(
			String userId) {
		Logger logger = Logger.getLogger(DistributorAccountStatementDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		JSONArray DistributorAccountStatementArray=new JSONArray();
		double charge=0.0;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createSQLQuery("select top 100 distributo0_.Transaction_No as col_0_0_, convert(varchar(10),distributo0_.Date_of_Transaction,120) as col_1_0_, distributo0_.Time_of_Transaction as col_2_0_, distributo0_.Service as col_3_0_, convert(decimal(18,2),distributo0_.Tran_amount) as col_4_0_, distributo0_.Commission as col_5_0_, convert(decimal(18,2),distributo0_.Net_Tran_amount) as col_6_0_, convert(decimal(18,2),distributo0_.Updated_Bal_amount) as col_7_0_, distributo0_.Tran_status as col_8_0_, distributo0_.Remarks as col_9_0_, distributo0_.Action_on_Bal_amount as col_10_0_, distributo0_.Service_charge as col_11_0_, distributo0_.Bank_charge as col_12_0_, convert(decimal(18,2),distributo0_.Previous_Bal_amount) as col_13_0_, convert(decimal(18,2),distributo0_.Final_Bal_amount) as col_14_0_, distributo0_.ID_NO as col_15_0_ from distributor_Transaction_details (nolock) distributo0_ where distributo0_.Distributor_id="+Long.parseLong(userId)+" order by distributo0_.Date_of_Transaction desc,distributo0_.Time_of_Transaction desc");
			log.print("getDistributorDepositReportDetails is "+query, logger);

			List list=query.list();
			Iterator<?> it=list.iterator();

			while(it.hasNext())
			{
				JSONObject mapInfo=new JSONObject();
				Object[] row = (Object[]) it.next();

				mapInfo.put("TransactionNo",row[0]+""); 
				mapInfo.put("DateOfTransaction",row[1]+""); 
				mapInfo.put("TimeOfTransaction",row[2]+""); 
				mapInfo.put("Service",row[3]+""); 
				mapInfo.put("TransactionAmount",row[4]+"");
				mapInfo.put("Commission",row[5]);
				mapInfo.put("NetTransactionAmount",row[6]+"");
				mapInfo.put("UpdatedBalanceAmount",row[7]+"");
				mapInfo.put("TransactionStatus",row[8]+"");
				mapInfo.put("Remarks",row[9]+"");
				mapInfo.put("ActionOnBalanceAmount",row[10]+"");
				charge=Double.parseDouble(row[11].toString())+Double.parseDouble(row[12].toString());
				mapInfo.put("charge",charge); 
				mapInfo.put("PreviousBalanceAmount",row[13]+"");
				mapInfo.put("FinalBalanceAmount",row[14]+"");
				mapInfo.put("IDNO",row[15]+"");

				DistributorAccountStatementArray.add(mapInfo);

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
		return DistributorAccountStatementArray;
	}


	public JSONArray getDistributorAccountStatementDetailsByDate(
			String userId ,String fromDate,String toDate) {
		Logger logger = Logger.getLogger(DistributorAccountStatementDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		JSONArray DistributorAccountStatementArray=new JSONArray();
		double charge=0.0;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			/*Query query=session.createSQLQuery("select distributo0_.Transaction_No as col_0_0_, distributo0_.Date_of_Transaction as col_1_0_, distributo0_.Time_of_Transaction as col_2_0_, distributo0_.Service as col_3_0_, distributo0_.Tran_amount as col_4_0_, distributo0_.Commission as col_5_0_, distributo0_.Net_Tran_amount as col_6_0_, distributo0_.Updated_Bal_amount as col_7_0_, distributo0_.Tran_status as col_8_0_, distributo0_.Remarks as col_9_0_, distributo0_.Action_on_Bal_amount as col_10_0_, distributo0_.Service_charge as col_11_0_, distributo0_.Bank_charge as col_12_0_, distributo0_.Previous_Bal_amount as col_13_0_, distributo0_.Final_Bal_amount as col_14_0_, distributo0_.ID_NO as col_15_0_ from distributor_Transaction_details distributo0_ where distributo0_.Distributor_id="+Long.parseLong(userId)+" and distributo0_.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by distributo0_.Date_of_Transaction desc");*/

			Query query=session.createSQLQuery("select distributo0_.Transaction_No as col_0_0_, convert(varchar(10),distributo0_.Date_of_Transaction,120) as col_1_0_, distributo0_.Time_of_Transaction as col_2_0_, distributo0_.Service as col_3_0_, convert(decimal(18,2),distributo0_.Tran_amount) as col_4_0_, distributo0_.Commission as col_5_0_, convert(decimal(18,2),distributo0_.Net_Tran_amount) as col_6_0_, convert(decimal(18,2),distributo0_.Updated_Bal_amount) as col_7_0_, distributo0_.Tran_status as col_8_0_, distributo0_.Remarks as col_9_0_, distributo0_.Action_on_Bal_amount as col_10_0_, distributo0_.Service_charge as col_11_0_, distributo0_.Bank_charge as col_12_0_, convert(decimal(18,2),distributo0_.Previous_Bal_amount) as col_13_0_, convert(decimal(18,2),distributo0_.Final_Bal_amount) as col_14_0_, distributo0_.ID_NO as col_15_0_ from distributor_Transaction_details (nolock) distributo0_ where distributo0_.Distributor_id="+Long.parseLong(userId)+" and distributo0_.Date_of_Transaction between '"+fromDate+"' and '"+toDate+"' order by distributo0_.Date_of_Transaction desc");
			log.print("getDistributorDepositReportDetails is "+query, logger);

			List list=query.list();
			Iterator<?> it=list.iterator();

			while(it.hasNext())
			{
				JSONObject mapInfo=new JSONObject();
				Object[] row = (Object[]) it.next();

				mapInfo.put("TransactionNo",row[0]); 
				mapInfo.put("DateOfTransaction",row[1]+""); 
				mapInfo.put("TimeOfTransaction",row[2]+""); 
				mapInfo.put("Service",row[3]); 
				mapInfo.put("TransactionAmount",row[4]);
				mapInfo.put("Commission",row[5]);
				mapInfo.put("NetTransactionAmount",row[6]);
				mapInfo.put("UpdatedBalanceAmount",row[7]);
				mapInfo.put("TransactionStatus",row[8]);
				mapInfo.put("Remarks",row[9]);
				mapInfo.put("ActionOnBalanceAmount",row[10]);
				charge=Double.parseDouble(row[11]+"")+Double.parseDouble(row[12]+"");
				mapInfo.put("charge",charge); 
				mapInfo.put("PreviousBalanceAmount",row[13]);
				mapInfo.put("FinalBalanceAmount",row[14]);
				mapInfo.put("IDNO",row[15]);

				DistributorAccountStatementArray.add(mapInfo);

			}



		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			try {
				if(session!=null)
					session.close();

			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return DistributorAccountStatementArray;
	}

}
