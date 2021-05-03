package com.common;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.controlPanel.profileMgt.retailUser.agent.AgentProfileDao;
import com.utility.WebServicesUtility;


public class CommonServiceDao {

	public final String getICICIAccountstmt(String fromDate, String toDate, String filePath) {

		String Status = "Norecord";
		try {

			String iciciStatementURL=PropertyFile.ICICI_STATEMENT_URL+"?fromDate="+fromDate+"&toDate="+toDate;

			JSONObject resp=WebServicesUtility.webServiceGetCall(iciciStatementURL);
			if(resp!=null) {

				JSONObject data=(JSONObject) resp.get("data");
				JSONArray statements=(JSONArray) data.get("statements");
				if(statements!=null && statements.size()>0) {

					Status = "Success";
					FileWriter writer = new FileWriter(filePath);

					writer.append("#");
					writer.append('\t');
					writer.append("TransactionId");
					writer.append('\t');
					writer.append("ValueDate");
					writer.append('\t');
					writer.append("TxnDate");
					writer.append('\t');
					writer.append("Amount");
					writer.append('\t');
					writer.append("Type");
					writer.append('\t');
					writer.append("Balance");
					writer.append('\t');
					writer.append("Remarks");
					writer.append('\n'); 
					
					for (int i = 0; i < statements.size(); i++) {

						JSONObject rs=(JSONObject) statements.get(i);

						writer.append(i+1+"");
						writer.append('\t');
						writer.append(rs.get("transactionid")+"");
						writer.append('\t');
						writer.append(rs.get("valuedate")+"");
						writer.append('\t');
						writer.append(rs.get("txndate")+"");
						writer.append('\t');
						writer.append(rs.get("amount")+"");
						writer.append('\t');
						writer.append(rs.get("type")+"");
						writer.append('\t');
						writer.append(rs.get("balance")+"");
						writer.append('\t');
						writer.append(rs.get("remarks")+"");
						writer.append('\n');



					}
					writer.flush();
					writer.close();
				}
			}
		} catch (Exception e) {

			Status = "ERROR";
		} 
		return Status;
	}

	public static HashMap<String,String> getDynamicDetails(String userId)
	{

		HashMap<String,String> mapdata=new HashMap<String,String>();;
		Session session=null;
		try
		{
			session = HibernateSession.getSessionFactory().openSession(); 
			String sql="select Mailer_id,Mailer_password,SMS_id,SMS_password from white_label_details where Client_Id=(select Portal_id from Admin_User_details where User_id="+userId+")";
			Query query2=session.createSQLQuery(sql);
			List list=query2.list();
			Iterator itr=list.iterator();
			Object [] row;

			while(itr.hasNext())
			{
				row=(Object[])itr.next();
				mapdata.put("Mailer_id",row[0].toString());
				mapdata.put("Mailer_password",row[1].toString());
				mapdata.put("SMS_id",row[2].toString());
				mapdata.put("SMS_password",row[3].toString());
			}
		}
		catch(HibernateException e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		catch(Exception e)
		{
			System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				session.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in AgentRegistrationDao,getDynamicDetails ");
				System.out.println(e.toString());
			}
		}
		return mapdata;
	}

	public static void clientnotify(int requesterId,String transactionId,double reqAmount,double charge){
		final String mailerUserId=String.valueOf(requesterId);
		final double agentAmount=reqAmount - charge;
		final String tranId=transactionId;
		try {

			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						AgentProfileDao daoObj=new AgentProfileDao();
						HashMap agentProfileDetails=daoObj.getAgentProfileDetails("AG"+String.valueOf(mailerUserId));

						final double agentBalance=Double.parseDouble(agentProfileDetails.get("agentBalance")!=null?agentProfileDetails.get("agentBalance").toString():"0");


						Date date = new Date();  
						SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");  
						String strDate = formatter.format(date);  


						try {
							String smsTest="Dear "+agentProfileDetails.get("firstName")+", You have successfully deposited Rs."+agentAmount+" in SmartKinda wallet on "+strDate+". Updated a/c balance is Rs. "+agentBalance+". TID: "+tranId+". Thanks, Team SmartKinda";
							SendSmsOnMobile.sendMobileSmsSMSZONE(agentProfileDetails.get("mobileNo")+"", smsTest);
						} catch (Exception e) {
							// TODO: handle exception
						}



						String[] receipient={agentProfileDetails.get("emailID")+""};
						String subject="Payment Receipt "+strDate;
						String message="<div style=\"width:90%;margin:0 auto;padding:18px;border: 3px solid #117de847;;color:grey;font-size:120%;border-radius: 5px 5px 5px 5px;\">"+
								"<p align=\"center\"><img alt=\"SmartKinda logo\" src=\"http://partner.smartkinda.com/FC/images/SmartKinda/Logo.png\" height=\"50px\"></p>"+

									"Dear "+agentProfileDetails.get("firstName")+",<br><br>"+

									"<strong>Wallet balance request for <i class=\"fas fa-rupee-sign\"></i> "+agentAmount+"</strong> has been successfully updated in your SmartKinda account on <strong><span  tabindex=\"0\"><span >"+strDate+"</span></span></strong>. Your current balance is  <strong> <i class=\"fas fa-rupee-sign\"></i> "+agentBalance+"</strong>.<br><br>"+

									"TID for all future support is <strong>"+tranId+"</strong>.<br><br>"+

									"<center><a href=\"https://play.google.com/store/apps/details?id=com.smartkinda\" target=\"_blank\" ><img alt=\"Get it on Google Play\" src=\"https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png\" height=\"60px\" ></a></center>"+
									"</div>";

						SendSmtpMail.sendSSLMessage(receipient, subject, message, "no-reply@smartkinda.com");

					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
			thread.start();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
