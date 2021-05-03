package com.report;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.db.DBConnection;

public class DistributorReportDao {


	public final String downloadLRTReport(Connection con,String mdId,String filePath){
		  ResultSet rs=null;
		  String Status="DownloadNotAllowed";
		    int i=0;
			int j=1;

			try
			{
				   con=DBConnection.getConnection();
					
			String		sql="select a.agent_initial+convert(varchar(10),l.user_id) as AgentID," +
					"d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID," +
					"m.md_initial+convert(varchar(10),d.md_id) as MDID,l.Agent_tran_id ,l.mobile_number,l.mobile_operator," +
					"l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.service,l.mob_commission," +
					"l.status from live_recharge l,agent_details a ,distributor_details d,md_details m " +
					"where a.agent_id=l.user_id and a.distributor_id=d.distributor_id and d.md_id=m.md_id and " +
					"l.user_id in (select agent_id from agent_details where distributor_id in " +
					"(select distributor_id from distributor_details where md_id='"+mdId+"')) and " +
					"date_of_recharge=convert(varchar(10),getdate()-1,120)";
					System.out.println(sql);
					Statement stmt = con.createStatement();
					rs=stmt.executeQuery(sql);
				//	rs=cstmt.executeQuery();
				    WritableWorkbook workbook;
					workbook=Workbook.createWorkbook(new File(filePath));
					WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);
					
					sheet.addCell(new Label(0,i,"S.No."));
					sheet.addCell(new Label(1,i,"Agent Id"));
					sheet.addCell(new Label(2,i,"Distributor Id"));
					sheet.addCell(new Label(3,i,"Master distributor ID"));
					sheet.addCell(new Label(4,i,"User Type"));
					sheet.addCell(new Label(5,i,"Agent Txn ID"));
					sheet.addCell(new Label(6,i,"Connection Number"));
				    sheet.addCell(new Label(7,i,"Connection Operator"));
				    sheet.addCell(new Label(8,i,"Date of Recharge"));
				    sheet.addCell(new Label(9,i,"Time of Recharge"));				 
				    sheet.addCell(new Label(10,i,"Transaction Amount"));				   
				    sheet.addCell(new Label(11,i,"Service"));		    
				    sheet.addCell(new Label(12,i,"Commission"));
				    sheet.addCell(new Label(13,i,"Status"));
				 
					int index = 1;
					
					
			/*		writer.append("SNo");
					writer.append(';');
					writer.append("Agent ID");
					writer.append(';');
					writer.append("Distributor ID");
					writer.append(';');
					writer.append("Master distributor ID");
					writer.append(';');
					writer.append("Connection Number");
					writer.append(';');
					writer.append("Connection Operator");
					writer.append(';');
					writer.append("Date of Recharge");
					writer.append(';');
					writer.append("Time of Recharge");
					writer.append(';');
					writer.append("Gross Amount");
					writer.append(';');
					writer.append("Commission");
					writer.append(';');
					writer.append("Status");
					writer.append('\n');
					*/
				    while(rs.next()){
				    	
				        String indexvalue=""+index;
				        sheet.addCell(new Label(0,j,indexvalue));
						sheet.addCell(new Label(1,j,rs.getString(1)));
						sheet.addCell(new Label(2,j,rs.getString(2)));
						sheet.addCell(new Label(3,j,rs.getString(3)));
						sheet.addCell(new Label(4,j,"Agent"));
					    sheet.addCell(new Label(5,j,","+rs.getString(4)));
					    sheet.addCell(new Label(6,j,","+rs.getString(5)));
					    sheet.addCell(new Label(7,j,rs.getString(6)));
					    sheet.addCell(new Label(8,j,rs.getString(7)));
					    sheet.addCell(new Label(9,j,rs.getString(8)));
					    sheet.addCell(new Label(10,j,rs.getString(9)));
					    sheet.addCell(new Label(11,j,rs.getString(10)));
					    sheet.addCell(new Label(12,j,rs.getString(11)));
					    sheet.addCell(new Label(13,j,rs.getString(12)));
					   
					    index++;
						j++;
					}
				    System.out.println("Data is saved in excel file at path and j is "+j);
					if(j>1){
						Status="DownloadAllowed";
						
					}

					
				    workbook.write();
				    workbook.close();
		            System.out.println("Data is saved in excel file at path and status is "+Status);
					
				
					
				}catch(Exception e)
					{
						System.out.println("Exception in getAccountStatement"+e.toString());
					}
				finally
				{
					try
					{
						if(rs!=null)
							rs.close();
						
						if(con!=null)
							con.close();
					}
					catch(Exception e)
					{
						System.out.println("Exception in getAccountStatement while closing connection"+e.toString());
					}
					
				}
				return Status;
			
		}
	public final String downloadATTReport(Connection con,String mdId,String filePath){
		  ResultSet rs=null;
		  String Status="DownloadNotAllowed";
		    int i=0;
			int j=1;

			try
			{
				   con=DBConnection.getConnection();
					
			String		sql="select a.agent_initial+convert(varchar(10),l.user_id) as AgentID," +
					"d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID," +
					"m.md_initial+convert(varchar(10),d.md_id) as MDID,,l.Agent_tran_id ,l.mobile_number,l.mobile_operator," +
					"l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.service,l.mob_commission," +
					"l.status from live_recharge l,agent_details a ,distributor_details d,md_details m " +
					"where a.agent_id=l.user_id and a.distributor_id=d.distributor_id and d.md_id=m.md_id and " +
					"l.user_id in (select agent_id from agent_details where distributor_id in " +
					"(select distributor_id from distributor_details where md_id='"+mdId+"')) and " +
					"date_of_recharge=convert(varchar(10),getdate()-1,120)";
					System.out.println(sql);
					Statement stmt = con.createStatement();
					rs=stmt.executeQuery(sql);
				//	rs=cstmt.executeQuery();
				    WritableWorkbook workbook;
					workbook=Workbook.createWorkbook(new File(filePath));
					WritableSheet sheet=workbook.createSheet("Excel Sheet", 0);
					
					sheet.addCell(new Label(0,i,"S.No."));
					sheet.addCell(new Label(1,i,"Agent Id"));
					sheet.addCell(new Label(2,i,"Distributor Id"));
					sheet.addCell(new Label(3,i,"Master distributor ID"));
					sheet.addCell(new Label(4,i,"User Type"));
					sheet.addCell(new Label(5,i,"Agent Txn ID"));
					sheet.addCell(new Label(6,i,"Connection Number"));
				    sheet.addCell(new Label(7,i,"Connection Operator"));
				    sheet.addCell(new Label(8,i,"Date of Recharge"));
				    sheet.addCell(new Label(9,i,"Time of Recharge"));				 
				    sheet.addCell(new Label(10,i,"Transaction Amount"));				   
				    sheet.addCell(new Label(11,i,"Service"));		    
				    sheet.addCell(new Label(12,i,"Commission"));
				    sheet.addCell(new Label(13,i,"Status"));
				 
					int index = 1;
					
					
			/*		writer.append("SNo");
					writer.append(';');
					writer.append("Agent ID");
					writer.append(';');
					writer.append("Distributor ID");
					writer.append(';');
					writer.append("Master distributor ID");
					writer.append(';');
					writer.append("Connection Number");
					writer.append(';');
					writer.append("Connection Operator");
					writer.append(';');
					writer.append("Date of Recharge");
					writer.append(';');
					writer.append("Time of Recharge");
					writer.append(';');
					writer.append("Gross Amount");
					writer.append(';');
					writer.append("Commission");
					writer.append(';');
					writer.append("Status");
					writer.append('\n');
					*/
				    while(rs.next()){
				    	
				        String indexvalue=""+index;
				        sheet.addCell(new Label(0,j,indexvalue));
						sheet.addCell(new Label(1,j,rs.getString(1)));
						sheet.addCell(new Label(2,j,rs.getString(2)));
						sheet.addCell(new Label(3,j,rs.getString(3)));
						sheet.addCell(new Label(4,j,"Agent"));
					    sheet.addCell(new Label(5,j,","+rs.getString(4)));
					    sheet.addCell(new Label(6,j,","+rs.getString(5)));
					    sheet.addCell(new Label(7,j,rs.getString(6)));
					    sheet.addCell(new Label(8,j,rs.getString(7)));
					    sheet.addCell(new Label(9,j,rs.getString(8)));
					    sheet.addCell(new Label(10,j,rs.getString(9)));
					    sheet.addCell(new Label(11,j,rs.getString(10)));
					    sheet.addCell(new Label(12,j,rs.getString(11)));
					    sheet.addCell(new Label(13,j,rs.getString(12)));
					   
					    index++;
						j++;
					}
				    System.out.println("Data is saved in excel file at path and j is "+j);
					if(j>1){
						Status="DownloadAllowed";
						
					}

					
				    workbook.write();
				    workbook.close();
		            System.out.println("Data is saved in excel file at path and status is "+Status);
					
				
					
				}catch(Exception e)
					{
						System.out.println("Exception in getAccountStatement"+e.toString());
					}
				finally
				{
					try
					{
						if(rs!=null)
							rs.close();
						
						if(con!=null)
							con.close();
					}
					catch(Exception e)
					{
						System.out.println("Exception in getAccountStatement while closing connection"+e.toString());
					}
					
				}
				return Status;
			
		}
	
	


}
