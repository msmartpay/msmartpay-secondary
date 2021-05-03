package com.SystemAccounting;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.dbConnection.DBConnection;

public class DailyReportDao {

	public ArrayList<HashMap<String,String>> dailyReport(String filePath) {

		ArrayList<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			con=DBConnection.getConnection();

			String sql="select ad.Client_ID,wld.Company_name,count(atd.Agent_id) as _count, sum(atd.ReqAmount) as Total_tran,sum(atd.Commession) as Total_com,"
					+ "sum(atd.Service_charge1+atd.Service_charge2) as Total_Charge,atd.service,atd.Tran_status from agent_transaction_details atd,agent_details ad,white_label_details wld where ad.agent_id=atd.Agent_id and wld.Client_id=ad.Client_ID and Date_of_Transaction =convert(varchar(10),getdate()-1,120) group by atd.Service,atd.Tran_status,ad.Client_ID,wld.Company_name order by ad.Client_ID";
			stmt=con.createStatement();
			rs=stmt.executeQuery(sql);

			if(rs.next()){

				FileOutputStream out = new FileOutputStream(new File(filePath));

				XSSFWorkbook workbook = new XSSFWorkbook(); 
				//Create a blank sheet
				XSSFSheet spreadsheet = workbook.createSheet( 
						" Transaction Info ");
				//Create row object
				XSSFRow row;

				List<Object[]> reportInfo = new ArrayList<Object[]>();
				reportInfo.add(new Object[] { 

						"Client Id", "Client Name","Count","Total Request Amount", "Total Commission", "Total Service Charge", "Service Name","Status"});


				while (rs.next()) { 

					reportInfo.add(new Object[] { 
							rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)});

				}
				int rowid = 0;
				for(Object[] excelarray:reportInfo){
					row = spreadsheet.createRow(rowid++);
					int cellid = 0;
					for (Object obj : excelarray)
					{
						Cell cell = row.createCell(cellid++);

						cell.setCellValue((String) obj);
					}
				}

				workbook.write(out);
				out.close();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				System.out.println("Exception in MobileDthRechargeDao class method (updateStatusTranEGEN) while closing connection");
				e.printStackTrace();
			}
		}
		return list;
	}

}
