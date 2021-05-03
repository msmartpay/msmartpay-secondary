package com.report.distributorDepositReport;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import com.DistributorDepositRequest.DistributorDepositRequestDao;
import com.common.HibernateSession;
import com.common.LogWriter;
import com.report.DistributorAccountStatement.DistributorAccountStatementDao;

public class DistributorDepositReportDao {

	public ArrayList<HashMap<String, Object>> getDistributorDepositReportDetails(String userId)
	{


		Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		LogWriter log=new LogWriter();
		Session session=null;
		ArrayList<HashMap<String,Object>> DistributorDepositReportArray=new  ArrayList<HashMap<String,Object>>();

		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
			Query query=session.createQuery("select DJF.AmountToCredit,DJF.remarks,DJF.RequestDate,DJF.RequestTime,DJF.ModeOfPayment,DJF.ChequeNumber" +
					",DJF.Status,DJF.BankName,DDF.companyName,DDF.emailId,DDF.MobileNo,DDF.distributorId,DDF.distributorInitial" +
					" from DistributorJournalForm DJF,DistributorDetailForm DDF" +
					" where DJF.DistributorId=:userId and  DJF.DistributorId=DDF.distributorId order by DJF.RequestDate desc,DJF.RequestTime desc").setParameter("userId", userId);
			log.print("getDistributorDepositReportDetails is "+query, logger);
			query.setFirstResult(0);
			query.setMaxResults(20);
			Iterator<?> it=query.iterate();

			while(it.hasNext())
			{
				HashMap<String,Object> mapInfo=new HashMap<String,Object>();
				Object[] row = (Object[]) it.next();

				mapInfo.put("AmountToCredit",row[0]); 
				mapInfo.put("remarks",row[1]); 
				mapInfo.put("RequestDate",row[2]); 
				mapInfo.put("RequestTime",row[3]); 
				mapInfo.put("ModeOfPayment",row[4]);
				mapInfo.put("ChequeNumber",row[5]);
				mapInfo.put("Status",row[6]);
				mapInfo.put("BankName",row[7]);
				mapInfo.put("companyName",row[8]);
				mapInfo.put("emailid",row[9]);
				mapInfo.put("MobileNo",row[10]);
				String tot=(String)row[11]+(String)row[12];
				mapInfo.put("DistributorCompleteID",tot); 
				DistributorDepositReportArray.add(mapInfo);

			}



		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			session.close();
		}
		return DistributorDepositReportArray;


	}

	public ArrayList<HashMap<String, Object>> getDistributorDepositReportSearch(
			String toDt, String fromDt, String userId, int modcount, int start,
			int end, Map session) {
		Session session1=HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>> DistributorDepositReportArray=new  ArrayList<HashMap<String,Object>>();
		int count=0;
		int count1=0;
		int pagecount=0;
		Query query=null;
		double longcount=0.0;
		try 
		{
			query=session1.createQuery("select count(*) from DistributorJournalForm DJF where  DJF.DistributorId=:userId and DJF.RequestDate between :fromdate and :todate ").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
			Iterator<?> it=query.iterate();
			if(it.hasNext())
			{
				Object row = (Object) it.next();
				longcount=(Long)row;
			}

			count=(int) longcount;

			session.put("count",count);

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
					System.out.println("befor exceuting the query..................................................");
					query=session1.createQuery("select DJF.AmountToCredit,DJF.remarks,DJF.RequestDate,DJF.RequestTime,DJF.ModeOfPayment,DJF.ChequeNumber" +
							",DJF.Status,DJF.BankName,DDF.companyName,DDF.emailId,DDF.MobileNo" +
							" from DistributorJournalForm DJF,DistributorDetailForm DDF" +
							" where DJF.DistributorId=:userId  and DJF.RequestDate between :fromdate and :todate and  " +
							"DJF.DistributorId=DDF.distributorId order by DJF.RequestDate,DJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);

					query.setFirstResult(start);
					query.setMaxResults(end);

					Iterator<?> it1=query.iterate();

					while(it1.hasNext())
					{
						HashMap<String,Object> mapInfo=new HashMap<String,Object>();
						Object[] row = (Object[]) it1.next();

						mapInfo.put("AmountToCredit",row[0]); 
						mapInfo.put("remarks",row[1]); 
						mapInfo.put("RequestDate",row[2]); 
						mapInfo.put("RequestTime",row[3]); 
						mapInfo.put("ModeOfPayment",row[4]);
						mapInfo.put("ChequeNumber",row[5]);
						System.out.println("status is "+row[6]);
						mapInfo.put("Status",row[6]);
						mapInfo.put("BankName",row[7]);
						mapInfo.put("companyName",row[8]);
						mapInfo.put("emailid",row[9]);
						mapInfo.put("MobileNo",row[10]);
						DistributorDepositReportArray.add(mapInfo);

					}

					session.put("count1",count1);

				}
				else
				{



					session.put("start",start);
					end=start+49;
					System.out.println("end in Dao class is"+end);
					session.put("end",end);
					query=session1.createQuery("select DJF.AmountToCredit,DJF.remarks,DJF.RequestDate,DJF.RequestTime,DJF.ModeOfPayment,DJF.ChequeNumber" +
							",DJF.Status,DJF.BankName,DDF.companyName,DDF.emailId,DDF.MobileNo" +
							" from DistributorJournalForm DJF,DistributorDetailForm DDF" +
							" where DJF.DistributorId=:userId  and DJF.RequestDate between :fromdate and :todate and  " +
							"DJF.DistributorId=DDF.distributorId order by DJF.RequestDate desc ,DJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);

					query.setFirstResult(start);
					query.setMaxResults(end);

					Iterator<?> it1=query.iterate();

					while(it1.hasNext())
					{
						HashMap<String,Object> mapInfo=new HashMap<String,Object>();
						Object[] row = (Object[]) it1.next();

						mapInfo.put("AmountToCredit",row[0]); 
						mapInfo.put("remarks",row[1]); 
						mapInfo.put("RequestDate",row[2]); 
						mapInfo.put("RequestTime",row[3]); 
						mapInfo.put("ModeOfPayment",row[4]);
						mapInfo.put("ChequeNumber",row[5]);
						mapInfo.put("Status",row[6]);
						mapInfo.put("BankName",row[7]);
						mapInfo.put("companyName",row[8]);
						mapInfo.put("emailid",row[9]);
						mapInfo.put("MobileNo",row[10]);
						DistributorDepositReportArray.add(mapInfo);

					}

				}
				session.put("count1",count1);
				System.out.println("count1---"+count1);
			}



		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
			System.out.println("Exception in getDistributorAccountStatementReport========"+ex.toString());
		}
		finally {
			if(session1!=null)
				session1.close();

		}
		return DistributorDepositReportArray;
	}

	public String downloadDepositReport(String filePath, String toDt,
			String fromDt, String userId, String distributorInitial) {
		Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		LogWriter log=new LogWriter();
		Session session=HibernateSession.getSessionFactory().openSession();
		String filePath1=""; 

		try 
		{
			Query query=session.createQuery("select DJF.AmountToCredit,DJF.remarks,DJF.RequestDate,DJF.RequestTime,DJF.ModeOfPayment,DJF.ChequeNumber" +
					",DJF.Status,DJF.BankName,DDF.companyName,DDF.emailId,DDF.MobileNo" +
					" from DistributorJournalForm DJF,DistributorDetailForm DDF" +
					" where DJF.DistributorId=:userId  and DJF.RequestDate between :fromdate and :todate and  " +
					"DJF.DistributorId=DDF.distributorId order by DJF.RequestDate,DJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
			log.print("getDistributorDepositReportDetails is "+query, logger);
			Iterator<?> it=query.iterate();

			String completeId=distributorInitial+userId;



			WritableWorkbook workbook;
			int i=0;
			workbook = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);


			sheet.addCell(new Label(0,i,"Distributor Id"));
			sheet.addCell(new Label(1,i,"Company Name"));
			sheet.addCell(new Label(2,i,"Mobile Number"));
			sheet.addCell(new Label(3,i,"Email Id"));
			sheet.addCell(new Label(4,i,"Date"));
			sheet.addCell(new Label(5,i,"Bank Name"));
			sheet.addCell(new Label(6,i,"PaymentMode"));
			sheet.addCell(new Label(7,i,"status"));


			i = 1;
			if(i<20000)
			{
				while(it.hasNext()){

					Object[] row1 = (Object[]) it.next();

					sheet.addCell(new Label(0,i,completeId));
					sheet.addCell(new Label(1,i,(String)row1[8]));
					sheet.addCell(new Label(2,i,(String)row1[10]));
					sheet.addCell(new Label(3,i,(String)row1[9]));
					sheet.addCell(new Label(4,i,(String)row1[2]));
					sheet.addCell(new Label(5,i,(String)row1[7]));
					sheet.addCell(new Label(6,i,(String)row1[4]));
					sheet.addCell(new Label(7,i,(String)row1[6]));



					i++;
				}
			}
			filePath1=filePath;
			workbook.write();
			workbook.close();







		} 


		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally {

			session.close();
		}
		return filePath1;
	}


}
