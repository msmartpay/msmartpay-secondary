package com.report.DistributorAccountStatement;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.common.HibernateSession;
import com.common.LogWriter;

public class DistributorAccountStatementDao {

	public ArrayList<HashMap<String, Object>> getDistributorAccountStatementDetails(
			String userId) {
		Logger logger = Logger.getLogger(DistributorAccountStatementDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		ArrayList<HashMap<String,Object>> DistributorAccountStatementArray=new  ArrayList<HashMap<String,Object>>();
		double charge=0.0;
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createSQLQuery("select distributo0_.Transaction_No as col_0_0_, convert(varchar(10),distributo0_.Date_of_Transaction,120) as col_1_0_, distributo0_.Time_of_Transaction as col_2_0_, distributo0_.Service as col_3_0_, convert(decimal(18,2),distributo0_.Tran_amount) as col_4_0_, distributo0_.Commission as col_5_0_, convert(decimal(18,2),distributo0_.Net_Tran_amount) as col_6_0_, convert(decimal(18,2),distributo0_.Updated_Bal_amount) as col_7_0_, distributo0_.Tran_status as col_8_0_, distributo0_.Remarks as col_9_0_, distributo0_.Action_on_Bal_amount as col_10_0_, distributo0_.Service_charge as col_11_0_, distributo0_.Bank_charge as col_12_0_, convert(decimal(18,2),distributo0_.Previous_Bal_amount) as col_13_0_, convert(decimal(18,2),distributo0_.Final_Bal_amount) as col_14_0_, distributo0_.ID_NO as col_15_0_ from distributor_Transaction_details distributo0_ where distributo0_.Distributor_id="+Long.parseLong(userId)+" order by distributo0_.Date_of_Transaction desc,distributo0_.Time_of_Transaction desc");
			log.print("getDistributorDepositReportDetails is "+query, logger);
			query.setFirstResult(0);
			query.setMaxResults(20);

			List list=query.list();
			Iterator<?> it=list.iterator();

			while(it.hasNext())
			{
				HashMap<String,Object> mapInfo=new HashMap<String,Object>();
				Object[] row = (Object[]) it.next();

				mapInfo.put("TransactionNo",row[0]); 
				mapInfo.put("DateOfTransaction",row[1]); 
				mapInfo.put("TimeOfTransaction",row[2]); 
				mapInfo.put("Service",row[3]); 
				mapInfo.put("TransactionAmount",row[4]);
				mapInfo.put("Commission",row[5]);
				mapInfo.put("NetTransactionAmount",row[6]);
				mapInfo.put("UpdatedBalanceAmount",row[7]);
				mapInfo.put("TransactionStatus",row[8]);
				mapInfo.put("Remarks",row[9]);
				mapInfo.put("ActionOnBalanceAmount",row[10]);
				charge=Double.parseDouble(row[11].toString())+Double.parseDouble(row[12].toString());
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
				session.close();

			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return DistributorAccountStatementArray;
	}

	public ArrayList<HashMap<String, Object>> getDistributorAccountStatementReport(
			String toDt, String fromDt, String userId, String reportof,
			String type, int modcount, int start, int end, Map<String, Integer> session) {
		Session session1=null;
		Session session2=HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>> AccountStatementReportArray=new  ArrayList<HashMap<String,Object>>();
		int count=0;
		int count1=0;
		int pagecount=0;
		int index=0;
		CallableStatement CS=null;
		ResultSet rs1=null;
		Connection con=null,con2=null;
		try 
		{
			session1=HibernateSession.getSessionFactory().openSession();
			session1.beginTransaction();
			con=session1.connection();
			CS =con.prepareCall("{call count_distributor_account_statement(?,?,?,?,?)  }");
			CS.setString(1,userId);  
			CS.setString(2,fromDt);
			CS.setString(3,toDt); 
			CS.setString(4,reportof);
			CS.setString(5,type);
			ResultSet rs=CS.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			session.put("count",count);

			session1.getTransaction().commit();

			int modulus=count%50;
			if(modulus==0)
			{
				pagecount=count/50;
			}
			else
			{
				int page=count/50;
				pagecount=page+1;
			}
			session.put("pagecount",pagecount);




			if(modcount==0)
			{
				count1=count;
			}
			else
			{
				count1=modcount;
			}


			if(count1>0)
			{
				if(count1<50)
				{



					session.put("start",start);
					end=start+count1;
					session.put("end",end);

					session2=HibernateSession.getSessionFactory().openSession();
					session2.beginTransaction();
					con2=session2.connection();

					CS=con2.prepareCall("{call New_distributor_account_statement(?,?,?,?,?,?,?)}");
					CS.setString(1,userId);
					CS.setString(2,fromDt);
					CS.setString(3,toDt);
					CS.setString(4,reportof);
					CS.setString(5,type);
					CS.setInt(6,start);
					CS.setInt(7,end);
					rs1=CS.executeQuery();
					while(rs1.next())
					{
						HashMap<String,Object> mapinfo=new HashMap<String,Object>();

						mapinfo.put("TransactionNo",rs1.getString(1));
						mapinfo.put("DateOfTransaction",rs1.getString(2));
						mapinfo.put("TimeOfTransaction",rs1.getString(3));
						mapinfo.put("Service",rs1.getString(4)); 
						mapinfo.put("TransactionAmount",rs1.getString(5)); 
						mapinfo.put("Commission",rs1.getString(6)); 
						mapinfo.put("NetTransactionAmount",rs1.getString(7)); 
						mapinfo.put("UpdatedBalanceAmount",rs1.getString(8)); 
						mapinfo.put("TransactionStatus",rs1.getString(9)); 
						mapinfo.put("Remarks",rs1.getString(10));
						mapinfo.put("ActionOnBalanceAmount",rs1.getString(11));
						mapinfo.put("charge",rs1.getString(12));
						mapinfo.put("PreviousBalanceAmount",rs1.getString(13));
						mapinfo.put("FinalBalanceAmount",rs1.getString(14));
						mapinfo.put("index",index);
						index++;
						AccountStatementReportArray.add(mapinfo);

					}

					session.put("count1",count1);

					session2.getTransaction().commit();
				}
				else
				{



					session.put("start",start);
					end=start+49;

					session2.beginTransaction();
					con2=session2.connection();

					session.put("end",end);
					CS=con.prepareCall("{call New_distributor_account_statement(?,?,?,?,?,?,?)}");
					CS.setString(1,userId);
					CS.setString(2,fromDt);
					CS.setString(3,toDt);
					CS.setString(4,reportof);
					CS.setString(5,type);
					CS.setInt(6,start);
					CS.setInt(7,end);
					rs1=CS.executeQuery();
					while(rs1.next()){
						HashMap<String,Object> mapinfo=new HashMap<String,Object>();

						mapinfo.put("TransactionNo",rs1.getString(1));
						mapinfo.put("DateOfTransaction",rs1.getString(2));
						mapinfo.put("TimeOfTransaction",rs1.getString(3));
						mapinfo.put("Service",rs1.getString(4)); 
						mapinfo.put("TransactionAmount",rs1.getString(5)); 
						mapinfo.put("Commission",rs1.getString(6)); 
						mapinfo.put("NetTransactionAmount",rs1.getString(7)); 
						mapinfo.put("UpdatedBalanceAmount",rs1.getString(8)); 
						mapinfo.put("TransactionStatus",rs1.getString(9)); 
						mapinfo.put("Remarks",rs1.getString(10));
						mapinfo.put("ActionOnBalanceAmount",rs1.getString(11));
						mapinfo.put("charge",rs1.getString(12));
						mapinfo.put("PreviousBalanceAmount",rs1.getString(13));
						mapinfo.put("FinalBalanceAmount",rs1.getString(14));
						mapinfo.put("index",index);
						index++;
						AccountStatementReportArray.add(mapinfo);

					}
					session.put("count1",count1);

					session2.getTransaction().commit();
				}

			}




		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		}
		finally {


			try {
				session1.close();
				session2.close();
				con.close();
				con2.close();
			} 
			catch (Exception e) {

				e.printStackTrace();
			}
		}
		return AccountStatementReportArray;
	}

	public String downloadaccountStatement(String filePath, String toDt,
			String fromDt, String userId, String reportof, String type,
			String distributorInitial) {
		Session session=null;
		Session session1=null;
		String filePath1=""; 
		CallableStatement CS=null;
		ResultSet rs1=null;
		ResultSet rs=null;
		int count=0;
		int start=0;
		String DisCompleteID=distributorInitial+userId;
		Connection con=null,con2=null;
		try
		{
			session=HibernateSession.getSessionFactory().openSession();


			session.beginTransaction();
			con=session.connection();

			CS=con.prepareCall("{call count_distributor_account_statement(?,?,?,?,?)}");
			CS.setString(1,userId);
			CS.setString(2,fromDt);
			CS.setString(3,toDt);
			CS.setString(4,reportof);
			CS.setString(5,type);
			rs1=CS.executeQuery();
			while (rs1.next())
			{
				count=rs1.getInt(1);
			}
			session.getTransaction().commit();

			session1=HibernateSession.getSessionFactory().openSession();
			session1.beginTransaction();
			con2=session1.connection();
			CS=con.prepareCall("{call New_distributor_account_statement(?,?,?,?,?,?,?)}");
			CS.setString(1,userId);
			CS.setString(2,fromDt);
			CS.setString(3,toDt);
			CS.setString(4,reportof);
			CS.setString(5,type);
			CS.setInt(6,start);
			CS.setInt(7,count);

			rs=CS.executeQuery();
			int size =0;   


			WritableWorkbook workbook;
			int i=0;
			workbook = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);


			sheet.addCell(new Label(0,i,"Date"));
			sheet.addCell(new Label(1,i,"Distributor Id"));
			sheet.addCell(new Label(2,i,"Time"));
			sheet.addCell(new Label(3,i,"Transaction No"));
			sheet.addCell(new Label(4,i,"Service"));
			sheet.addCell(new Label(5,i,"Transaction Amount"));
			sheet.addCell(new Label(6,i,"Margin"));
			sheet.addCell(new Label(7,i,"Charge"));
			sheet.addCell(new Label(8,i,"Net Amount"));
			sheet.addCell(new Label(9,i,"Type"));
			sheet.addCell(new Label(10,i,"Current Balance"));
			sheet.addCell(new Label(11,i,"Transaction Status"));


			i = 1;
			if(i<20000)
			{
				while(rs.next()){



					sheet.addCell(new Label(0,i,rs.getString(2)));
					sheet.addCell(new Label(1,i,DisCompleteID));
					sheet.addCell(new Label(2,i,rs.getString(3)));
					sheet.addCell(new Label(3,i,rs.getString(1)));
					sheet.addCell(new Label(4,i,rs.getString(4)));
					sheet.addCell(new Label(5,i,rs.getString(5)));
					sheet.addCell(new Label(6,i,rs.getString(6)));
					sheet.addCell(new Label(7,i,rs.getString(12)));
					sheet.addCell(new Label(8,i,rs.getString(7)));
					sheet.addCell(new Label(9,i,rs.getString(11)));
					sheet.addCell(new Label(10,i,rs.getString(14)));
					sheet.addCell(new Label(11,i,rs.getString(9)));



					i++;
				}
			}
			filePath1=filePath;
			workbook.write();
			workbook.close();

			

			session1.getTransaction().commit();



		}  
		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getaccountStatement========"+ex.toString());
		}
		finally {


			try {
				session.close();
				session1.close();
				con.close();
				con2.close();

			}catch (Exception e) {

				e.printStackTrace();
			}
		}
		return filePath1;
	}

}
