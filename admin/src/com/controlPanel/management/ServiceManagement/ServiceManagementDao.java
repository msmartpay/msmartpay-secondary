package com.controlPanel.management.ServiceManagement;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.common.HibernateSession1;

public class ServiceManagementDao 
{

	public ArrayList getserviceManagementDetails(String service,String vertical,String Operator,long mdLongId) 
	{
		Connection conn=null;
		Session session=null;
		Statement stmt=null;
		Query query=null;
		//int id=0;
		//System.out.println("vertical  :  "+vertical);
		HashMap map=new HashMap();
		
		ArrayList mapList=new ArrayList();
		String str="";
		String sqlquery="";
		String sql="";
		List list=null;
		Iterator itr=null;
		HashMap wlmap=null;
			
			if("web".equalsIgnoreCase(vertical))
			{
				if(service.equalsIgnoreCase("flight"))
				{
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='flight' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("bus"))
				{
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='bus' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("hotel"))
				{
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='hotel' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("recharge"))
				{
					
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='Live_Recharge' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("utility"))
				{

					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='Live_Billpay' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("paycard"))
				{

					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='paycard' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("testmerit"))
				{

					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='testmerit' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				
				else if(service.equalsIgnoreCase("dth"))
				{

					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='Live_Recharge' and MD_id="+mdLongId+" order by Sub_service";
				}
				
				else if(service.equalsIgnoreCase("ops"))
				{

					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='Live_Recharge' and MD_id="+mdLongId+" order by Sub_service";
				}
				
				else if(service.equalsIgnoreCase("sms"))
				{
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='Live_Recharge' and MD_id="+mdLongId+" order by Sub_service asc";
				}
				else
				{
					sql="select Sub_Service,operator,vendor,Active from newagentservice where service='"+service+"' and MD_id="+mdLongId+" order by Sub_Service asc";
				}
				
				try 
				{
					session=HibernateSession.getSessionFactory().openSession();
					conn=session.connection();
					System.out.println("Sql : "+sql);
					stmt=conn.createStatement();
					ResultSet rs=stmt.executeQuery(sql);
					while(rs.next())
					{

						wlmap=new HashMap();				
						wlmap.put("Sub_Service",rs.getObject("Sub_Service"));
						wlmap.put("SKU_Name",rs.getObject("operator"));
						wlmap.put("Vendor",rs.getObject("vendor"));
						wlmap.put("Vendor_Status",rs.getObject("Active"));
						wlmap.put("Main_Service",service);
						mapList.add(wlmap);
						//System.out.println(wlmap);
					}
					
					//System.out.println(mapList);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				finally
				{
					try 
					{
						session.flush();
						session.close();
						query=null;
					} catch (Exception e2) 
					{
						e2.printStackTrace();
					}
				}
				
			}
			
			else if("sms".equalsIgnoreCase(vertical))
			{
				//System.out.println(Operator);
				
				
				 if(service.equalsIgnoreCase("recharge"))
				 {
					 if(Operator.equalsIgnoreCase("all"))
					 {
						 sql="select Services_Type,Op_Code,OperatorName,OP_Status, cyber_plat_status,GP_Status,Vd2h_Status,Tsky_Status,Billpay_Status,RootAPI,EasyPay from REP_Check_Mobile_Code"
							 +" where Services_Type IN ('Mobile','DTH','datacard') order by Services_Type";
					 }
					 else
					 {
						 sql="select Services_Type,Op_Code,OperatorName,OP_Status, cyber_plat_status,GP_Status,Vd2h_Status,Tsky_Status,Billpay_Status,RootAPI,EasyPay from REP_Check_Mobile_Code"
							 +" where Op_Code='"+Operator+"'";
					 }
					
				 }
				
				 else if(service.equalsIgnoreCase("utility"))
				 {
					 if(Operator.equalsIgnoreCase("all"))
					 {
						 sql="select Services_Type,Op_Code,OperatorName,OP_Status, cyber_plat_status,GP_Status,Vd2h_Status,Tsky_Status,Billpay_Status,RootAPI,EasyPay from REP_Check_Mobile_Code"
							 +" where Services_Type='Billpay' order by Services_Type";
					 }
					
					 else
					 {
						 sql="select Services_Type,Op_Code,OperatorName,OP_Status, cyber_plat_status,GP_Status,Vd2h_Status,Tsky_Status,Billpay_Status,RootAPI,EasyPay from REP_Check_Mobile_Code"
							 +" where Op_Code='"+Operator+"'";
					 }
				 }
				
				 try 
					{
						session=HibernateSession.getSessionFactory().openSession();
						conn=session.connection();
						int opr;
						int vendor;
						System.out.println("Sql : "+sql);
						stmt=conn.createStatement();
						ResultSet rs=stmt.executeQuery(sql);
						while(rs.next())
						{
						//System.out.println(itr);
							Object[] row=(Object[])itr.next();				  

							wlmap=new HashMap();				
							wlmap.put("Sub_Service",rs.getObject(1));
							wlmap.put("Op_Code",rs.getObject(2));
							wlmap.put("SKU_Name",rs.getObject(3));
							vendor=(Integer)row[3];
							if(vendor==1)
							{
								wlmap.put("Vendor_Status","Y");
							}
							else
							{
								wlmap.put("Vendor_Status","N");
							}
							opr=(Integer)row[4];
							if(opr==1)
							{
								wlmap.put("Vendor","CYBERPLAT");
								wlmap.put("Main_Service",service);
								mapList.add(wlmap);
								continue;
							}
							else
							{
								opr=(Integer)row[5];
								if(opr==1)
								{
									wlmap.put("Vendor","GOPROCESS");
									wlmap.put("Main_Service",service);
									mapList.add(wlmap);
									continue;
								}
								else
								{
									opr=(Integer)row[6];
									if(opr==1)
									{
										wlmap.put("Vendor","VIDEOCOND2H");
										wlmap.put("Main_Service",service);
										mapList.add(wlmap);
										continue;
									}
									else
									{
										opr=(Integer)row[7];
										if(opr==1)
										{
											wlmap.put("Vendor","TATASKY");
											wlmap.put("Main_Service",service);
											mapList.add(wlmap);
											continue;
										}
										else
										{
											opr=(Integer)row[8];
											if(opr==1)
											{
												wlmap.put("Vendor","BILLPAY");
												wlmap.put("Main_Service",service);
												mapList.add(wlmap);
												continue;
											}
											else
											{
												opr=(Integer)row[9];
												if(opr==1)
												{
													wlmap.put("Vendor","ROOT");
													wlmap.put("Main_Service",service);
													mapList.add(wlmap);
													continue;
												}
												else
												{
													opr=(Integer)row[10];
													if(opr==1)
													{
														wlmap.put("Vendor","EASYPAY");
														wlmap.put("Main_Service",service);
														mapList.add(wlmap);
														continue;
													}
													else
													{
														wlmap.put("Vendor","NA");
														wlmap.put("Main_Service",service);
														mapList.add(wlmap);
														continue;
													}
												}
											}
										}
									}
								}
							}
							
							
							//System.out.println(wlmap);
						}
						
						//System.out.println(mapList);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					
					finally
					{
						try 
						{
							session.flush();
							session.close();
							query=null;
						} catch (Exception e2) 
						{
							e2.printStackTrace();
						}
					}
			}
			
		
		return mapList;
	}
	public String updateServiceManagement(ArrayList update,String updateService,long mdLongId) 
	{
		Session session=null;
		Transaction tx=null;
		Query query=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		Statement stmt=null;
		String sql="";
		//System.out.println("ArrayList : "+update);
		String key="";
		int updateSize=update.size();
		System.out.println("updateSize : "+updateSize);
		HashMap hm=null;
		try
		{
			session=HibernateSession.getSessionFactory().openSession();
			tx=session.beginTransaction();
			con=session.connection();
			
			if(updateService.equalsIgnoreCase("web"))
			{
				if(mdLongId==-1)
					sql="update newagentservice set Vendor=?,Active=? where Sub_Service=? and operator=?";
				else
					sql="update newagentservice set Vendor=?,Active=? where Sub_Service=? and operator=? and MD_id="+mdLongId+"";
				
				System.out.println("Query : "+sql);
				
				pstmt=con.prepareStatement(sql);
				for(int i=0;i<updateSize;i++)
				{
					hm=(HashMap)update.get(i);
					System.out.println("MAP : "+hm);
					String sub_service=(String)hm.get("sub_service");
					String sku_name=(String)hm.get("sku_name");
					String vendor_status=(String)hm.get("vendor_status");
					String vendor=(String)hm.get("vendor");
					
					pstmt.setString(1, vendor);
					pstmt.setString(2, vendor_status);
					pstmt.setString(3, sub_service);
					pstmt.setString(4, sku_name);
					
					int count=pstmt.executeUpdate();
					if(count>0)
					{
						System.out.println("Update success.");
					}
					else
					{
						System.out.println("Update failure.");
					}
					
				}
			}
			if(updateService.equalsIgnoreCase("sms"))
			{
				for(int i=0;i<updateSize;i++)
				{
					hm=(HashMap)update.get(i);
					System.out.println("MAP : "+hm);
					int status=0;
					String Op_Code=(String)hm.get("Op_Code");
					String vendor_status=(String)hm.get("vendor_status");
					if(vendor_status.equalsIgnoreCase("Y"))
					{
						status=1;
					}
					String vendor=(String)hm.get("vendor");
					if(vendor.equalsIgnoreCase("CYBERPLAT"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+1+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("ROOT"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+1+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("EasyPay"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+1+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("GOPROCESS"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+1+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("VIDEOCOND2H"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+1+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("BILLPAY"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+1+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("TATASKY"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+1+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else if(vendor.equalsIgnoreCase("NA"))
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					else
					{
						sql="update REP_Check_Mobile_Code set OP_Status="+status+", cyber_plat_status="+0+",GP_Status="+0+",Vd2h_Status="+0+",Tsky_Status="+0+",Billpay_Status="+0+",RootAPI="+0+",EasyPay="+0+" where Op_Code='"+Op_Code+"'";
					}
					System.out.println("SQL : "+sql);
					stmt=con.createStatement();
					int count=stmt.executeUpdate(sql);
					if(count>0)
					{
						System.out.println("Update success.");
					}
					else
					{
						System.out.println("Update failure.");
					}
					
					
				}
			}
			
			tx.commit();
			key="success";
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			tx.rollback();
			return "failure";
			
		}
		finally
		{
			try
			{
				if(session!=null)
					session.close();
				if(con!=null)
					con.close();
				if(pstmt!=null)
					pstmt.close();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return key;
	}
	
	public String addVendorIP(String ipDetails) throws SQLException
	{
		String key="fail";
		Session session=null;
		Transaction tx=null;
		Connection con=null;
		Statement stmt=null;
		try
		{	
			session = HibernateSession.getSessionFactory().openSession();
			con=session.connection();
			String sql="update Vendor_IP_Details set IP='"+ipDetails+"'";
			stmt=con.createStatement();
			stmt.executeUpdate(sql);
			con.commit();
			key="success";
		}
		catch(Exception e)
		{
			key="fail";
			con.rollback();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(session!=null)
				{
					session.flush();
					session.close();
					con.close();
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("addVendorIP Status >>>>>>>>>>>>>>>>>>> "+key);
		return key;
	}

	public int updateVendor(String service,String vendor){
		Session session=null;
		Transaction txn=null;
		int count=0;
		try {
			
			session=HibernateSession.getSessionFactory().openSession();
			txn=session.beginTransaction();
			String sql="update Vendor_Management set Vendor='"+vendor+"' where Service='"+service+"'";
			count=session.createSQLQuery(sql).executeUpdate();
			txn.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			txn.rollback();
		}finally{
			if(session!=null)
				session.close();
		}
		return count;
	}
	
	public String fetchVendor(String service){
		Session session=null;
		String vendorName="";
		try {
			session=HibernateSession.getSessionFactory().openSession();
			String sql="select Vendor from Vendor_Management where Service='"+service+"'";
			vendorName=(String)session.createSQLQuery(sql).uniqueResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(session!=null)
				session.close();
		}
		return vendorName;
	}
	
}
