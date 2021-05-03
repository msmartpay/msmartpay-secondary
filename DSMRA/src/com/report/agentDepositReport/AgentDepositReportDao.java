package com.report.agentDepositReport;

import java.io.File;
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

public class AgentDepositReportDao {

	public ArrayList<HashMap<String, Object>> getAgentDepositReportDetails(
			String userId) {
		 Logger logger = Logger.getLogger(AgentDepositReportDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;
		 ArrayList<HashMap<String,Object>> AgentDepositReportArray=new  ArrayList<HashMap<String,Object>>();

		 try 
		    {
			 session=HibernateSession.getSessionFactory().openSession();
			    Query query=session.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
		   		" AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo" +
		   		" from AgentJournalForm AJF,AgentDetailForm ADF" +
		   		" where AJF.distributorId=:userId and AJF.agentId=ADF.agentId order by AJF.RequestDate desc,AJF.RequestTime desc").setParameter("userId", userId);
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
					     mapInfo.put("agencyName",row[8]);
					     mapInfo.put("agentEmailId",row[9]);
					     mapInfo.put("mobileNo",row[10]);
				    	 AgentDepositReportArray.add(mapInfo);
							       	
					}

			
			 
			} 
		 
		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
		   }
		 finally {
				
			 try {
				 session.flush();
				 session.close();
				
				     } 
				   catch (Exception e) {
					
					e.printStackTrace();
				     }
			     }
		return AgentDepositReportArray;
	}

	public HashMap<String, Object> getAgentVerified(String enterAgentId) {
		 Logger logger = Logger.getLogger(AgentDepositReportDao.class);
		 LogWriter log=new LogWriter();
		 Session session=null;
		 HashMap<String,Object> idStatus=new HashMap<String,Object>();
		 try 
		    {
			 session=HibernateSession.getSessionFactory().openSession();
			 Query IdStatusQuery=session.createQuery("select ADF.agentId  from  AgentDetailForm ADF where ADF.agentInitial+convert(Varchar,ADF.agentId)=:ReceiverID").setParameter("ReceiverID",enterAgentId);
             log.print("AgentDepositReportDao -getIdStatus- query is "+IdStatusQuery, logger);
			 Iterator<?> it=IdStatusQuery.iterate();
			 if(it.hasNext())
			 {
				 Object row = (Object) it.next();
				 idStatus.put("agentId",row);
				 idStatus.put("status","exist");
				 
			}
			 else
			 {
				 idStatus.put("status","Noexist");
 
			 }
			 
			} 
		 
		 
		 catch (Exception ex) 
		 {
			 idStatus.put("status","Noexist");
			ex.printStackTrace();
		   }
		 finally {
			 try {
				 session.flush();
				 session.close();
				
				     } 
				   catch (Exception e) {
					
					e.printStackTrace();
				     }
			     }
		return idStatus;
	}

	public ArrayList<HashMap<String, Object>> getAgentDepositReportSearch(
			String toDt, String fromDt, String userId, int modcount, int start,
			int end, String agentid, Map session,String reportBy) {
		
		 Logger logger = Logger.getLogger(DistributorAccountStatementDao.class);
		   LogWriter log=new LogWriter();
		   Session session1=null;
		   ArrayList<HashMap<String,Object>> AgentDepositReportArray=new  ArrayList<HashMap<String,Object>>();
		   int count=0;
		   int count1=0;
		   int pagecount=0;
		   Query query=null;
		   double longcount=0.0;
		 try 
		    {
			 session1=HibernateSession.getSessionFactory().openSession();
		       if(reportBy.equalsIgnoreCase("ById"))
		       {
				query=session1.createQuery("select count(*) from AgentJournalForm AJF where  AJF.distributorId=:userId  and AJF.agentId=:agentid and AJF.RequestDate between :fromdate and :todate").setParameter("userId", userId).setParameter("agentid",agentid).setParameter("fromdate",fromDt).setParameter("todate",toDt);
 
		       }
		       else
		       {
				query=session1.createQuery("select count(*) from AgentJournalForm AJF where  AJF.distributorId=:userId  and AJF.RequestDate between :fromdate and :todate").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
   
		       }
			 
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

					   
					   
					   if(reportBy.equalsIgnoreCase("ById"))
				       {
						   query=session1.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
				   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo " +
				   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
								   		" where AJF.distributorId=:userId and AJF.agentId=:agentid  and AJF.RequestDate between :fromdate and :todate and  " +
								   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("agentid", agentid).setParameter("fromdate",fromDt).setParameter("todate",toDt);
							  		 
				       }
					   else
					   {
						   query=session1.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
				   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo " +
				   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
								   		" where AJF.distributorId=:userId  and AJF.RequestDate between :fromdate and :todate and  " +
								   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
							    
					   }
					        
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
						     mapInfo.put("agencyName",row[8]);
						     mapInfo.put("agentEmailId",row[9]);
						     mapInfo.put("mobileNo",row[10]);
						     AgentDepositReportArray.add(mapInfo);
								       	
						}
					 
				        session.put("count1",count1);
					 }
					 else
					 {
						     

						      session.put("start",start);
				              end=start+49;
							  session.put("end",end);
							  if(reportBy.equalsIgnoreCase("ById"))
						       {
								   query=session1.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
						   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo " +
						   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
										   		" where AJF.distributorId=:userId and AJF.agentId=:agentid  and AJF.RequestDate between :fromdate and :todate and  " +
										   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("agentid", agentid).setParameter("fromdate",fromDt).setParameter("todate",toDt);
									  		 
						       }
							   else
							   {
								   query=session1.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
						   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo " +
						   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
										   		" where AJF.distributorId=:userId  and AJF.RequestDate between :fromdate and :todate and  " +
										   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
									    
							   }        
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
								     mapInfo.put("agencyName",row[8]);
								     mapInfo.put("agentEmailId",row[9]);
								     mapInfo.put("mobileNo",row[10]);
								     AgentDepositReportArray.add(mapInfo);
										       	
								}
						      
						  }
						    session.put("count1",count1);
					 }
					 
			 
				 
				 
				 
				 
			} 
		 
		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
			System.out.println("Exception in getAgentDepositReportSearch========"+ex.toString());
		   }
		 finally {
				
			 try {
				 session1.flush();
				 session1.close();
				
				     } 
				   catch (Exception e) {
					
					e.printStackTrace();
				     }
			
			     }
		return AgentDepositReportArray;
	}

	public String downloadDepositReport(String filePath, String toDt,
			String fromDt, String userId, String agentid, 
			String reportBy) {
		 Logger logger = Logger.getLogger(DistributorDepositRequestDao.class);
		 LogWriter log=new LogWriter();
		 Session session=HibernateSession.getSessionFactory().openSession();
		  String filePath1=""; 
		  Query query=null;
          String AgentCompleteId="";
		 try 
		    {
			 if(reportBy.equalsIgnoreCase("ById"))
		       {
				   query=session.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
		   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo,ADF.agentId,ADF.agentInitial " +
		   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
						   		" where AJF.distributorId=:userId and AJF.agentId=:agentid  and AJF.RequestDate between :fromdate and :todate and  " +
						   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("agentid", agentid).setParameter("fromdate",fromDt).setParameter("todate",toDt);
					  		 
		       }
			   else
			   {
				   query=session.createQuery("select AJF.AmounToCredit,AJF.remarks,AJF.RequestDate,AJF.RequestTime,AJF.ModeOfPayment,AJF.ChequeNumber," +
		   		              " AJF.Status,AJF.BankName,ADF.agencyName,ADF.agentEmailId,ADF.mobileNo,ADF.agentId,ADF.agentInitial " +
		   		              " from AgentJournalForm AJF,AgentDetailForm ADF" +
						   		" where AJF.distributorId=:userId  and AJF.RequestDate between :fromdate and :todate and  " +
						   		"AJF.agentId=ADF.agentId order by AJF.RequestDate desc ,AJF.RequestTime desc").setParameter("userId", userId).setParameter("fromdate",fromDt).setParameter("todate",toDt);
					    
			   }     
			 
			        log.print("downloadDepositReport is "+query, logger);
                    Iterator<?> it=query.iterate();
                       
                    
                    
                    WritableWorkbook workbook;
        			int i=0;
        			workbook = Workbook.createWorkbook(new File(filePath));
        			WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);
        			
        			
        			sheet.addCell(new Label(0,i,"Agent Id"));
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
        			  
        			  sheet.addCell(new Label(0,i,AgentCompleteId));
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
                    
                    
                    
                   /* HSSFWorkbook wb = new HSSFWorkbook();
       		  		HSSFSheet sheet = wb.createSheet("Excel Sheet");
       		  		HSSFRow rowhead = sheet.createRow((short) 0);
       		  		
       		  		rowhead.createCell((short) 0).setCellValue("Agent Id");
       		        rowhead.createCell((short) 1).setCellValue("Company Name");
       		  		rowhead.createCell((short) 2).setCellValue("Mobile Number");
       		  		rowhead.createCell((short) 3).setCellValue("Email Id");
       		  		rowhead.createCell((short) 4).setCellValue("Date");
       		  		rowhead.createCell((short) 5).setCellValue("Bank Name");
       		  		rowhead.createCell((short) 6).setCellValue("PaymentMode");
       		  		rowhead.createCell((short) 7).setCellValue("status");
       		  		
       	     		int index = 1;
					 while(it.hasNext())
					 {
					      Object[] row1 = (Object[]) it.next();
					  	  HSSFRow row = sheet.createRow((short) index);
                            String onlyagentid=(String)row1[11];
                            String agentInitial=(String)row1[12];
                            AgentCompleteId=agentInitial+onlyagentid;
                            
					        row.createCell((short) 0).setCellValue(AgentCompleteId);
				            row.createCell((short) 1).setCellValue((String)row1[8]);
				  			row.createCell((short) 2).setCellValue((String)row1[10]);
				  			row.createCell((short) 3).setCellValue((String)row1[9]);
				  			row.createCell((short) 4).setCellValue((String)row1[2]);
				  			row.createCell((short) 5).setCellValue((String)row1[7]);
				  			row.createCell((short) 6).setCellValue((String)row1[4]);
				  			row.createCell((short) 7).setCellValue((String)row1[6]);
				  		
				  			index++;
							       	
					}

				  		filePath1=filePath;
				  		FileOutputStream fileOut = new FileOutputStream(filePath1);
				  		wb.write(fileOut);
				  		fileOut.close();*/

			
			 
			} 
		 
		 
		 catch (Exception ex) 
		 {
			ex.printStackTrace();
		   }
		 finally {
				
			 try {
				 session.flush();
				 session.close();
				
				     } 
				   catch (Exception e) {
					
					e.printStackTrace();
				     }
			     }
		return filePath1;
	}

}
