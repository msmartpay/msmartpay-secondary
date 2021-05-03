/*
Class Property : This class (LoginDao) is created for fetching data from database.

Created Date : 11-Nov-2011 at 9:45 PM.
Created By : Bharatveer Singh.

Updated Date : 02-Dec-2011.
Update By : Bharatveer Singh

 */

package com.login;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.common.HibernateSession;
import com.common.LogWriter;

public class LoginDao {

	Logger logger = Logger.getLogger(LoginAction.class);
	LogWriter log=new LogWriter();

	public String checkUserLoginDetails(String loginId,String password,String userType){

		String Status="invalid";
		Session session = null;
		String dataPass="";
		try{
			session = HibernateSession.getSessionFactory().openSession();
			Query query=session.createSQLQuery("select b.login_status,b.password,b.client_type,b.Block_status,b.user_id from distributor_details a join distributor_login_details b on a.distributor_id=b.user_id where (b.user_name=:loginId or a.mobile_no=:mobile_no) and b.password=:password").setParameter("loginId",loginId).setParameter("mobile_no",loginId).setParameter("password",password);
			log.print("distributor Login query is "+query, logger);
			List<?> list=query.list();
			Iterator<?> it=list.iterator();
			if(it.hasNext())
			{

				Object[] row = (Object[]) it.next();

				dataPass=(String)row[1];

				System.out.println(">>>>>>>>>>>>>>> : "+dataPass);
				if(dataPass.equals(password))
				{
					String loginStatus=(String)row[0];
					String blockStatus=(String)row[3];
					String userId=row[4]+"";
					System.out.println("blockStatus "+blockStatus);
					if (blockStatus==null){
						blockStatus="";					
					}				
					if(loginStatus.equalsIgnoreCase("Activate")|| loginStatus.equalsIgnoreCase("PartiallyActive")){

						if (blockStatus.equalsIgnoreCase("blocked")){
							Status="blocked";			
						}				
						else{
							String sql11="select Block_Status from white_label_details where client_id in (select client_id from md_details where md_id in (select md_id from distributor_details where distributor_id ='"+userId+"'))";
							System.out.println("sql for client  "+sql11);							
							Query qry22 = session.createSQLQuery(sql11);
							String	client_block_status=(String)qry22.uniqueResult();
							if (client_block_status==null){
								client_block_status="";					
							}if(client_block_status.equalsIgnoreCase("Blocked")){
								Status="blockedUpperHirerarchy";	
							}else{
								String sql1="select Block_status from md_login_details where user_id in(select md_id from distributor_details where distributor_id='"+userId+"')";
								System.out.println("sql for client  "+sql1);
								Query qry2 = session.createSQLQuery(sql1);
								String md_block_status=(String)qry2.uniqueResult();
								if (md_block_status==null){
									md_block_status="";	
									System.out.println("md_block_status "+md_block_status);
								}
								if(md_block_status.equalsIgnoreCase("Blocked")){
									Status="blockedUpperHirerarchy";	
								}else{
									
									Status="Activate";
									System.out.println("client_block_status "+md_block_status);
									System.out.println("md_block_status "+md_block_status);
									System.out.println("Status :"+Status);
								}
							}								
						}					

					}					
					else
					{
						Status="Deactivate";
					}
				}
				else
				{
					Status="invalid";
				}
			}
			log.print("login distributor Status is "+Status, logger);
		}
		catch(Exception ex){
			Status="invalid";
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
		return Status;
	}

	public HashMap<String,Object> checkUserProfileStatus(String loginId,String password){

		Session session = HibernateSession.getSessionFactory().openSession();

		String Status="";
		HashMap<String,Object> DisDetailsmapInfo=new HashMap<String,Object>();


		try{

			Query query=session.createQuery("select dsd.distributorId,dsd.distributorInitial,dsd.companyName,dsp.clientId," +
					"dsp.loginUrl,dsp.innerHeaderImage,dsp.poweredBy,dsp.panelType," +
					"dsa.totalCash,dsa.usedCash,dsa.cutoffAamount,dsa.lastAmount,dsm.mdId,dsm.mdInitial,dsp.domainName," +
					"dsp.WhitelableCompanyName,dsd.DistributorName,dsd.MobileNo,dsp.agentLoginUrl,dsp.Help_Desk_EmailID," +
					"dsd.DistributorName,dsl.oldPass,dsp.distributor_Ticker,dsl.emailVerified from LoginForm dsl,DistributorDetailForm dsd,PanelDetailForm dsp," +
					"DistributorAmountForm dsa,MdsDetailsForm dsm where dsl.userId=convert(varchar(10),dsd.distributorId) " +
					"and dsd.distributorId=dsa.distributorId and dsd.mdId=dsm.mdId and dsm.clientId=dsp.clientId and (dsl.loginId=:loginId or dsd.MobileNo=:MobileNo)" +
					"and dsl.password=:password").setParameter("loginId",loginId).setParameter("MobileNo",loginId).setParameter("password",password);

			log.print("Geting distributor details after Login ", logger);
			Iterator<?> it=query.iterate();

			if(it.hasNext()){

				Object[] row = (Object[]) it.next();

				DisDetailsmapInfo.put("distributorId",row[0]);
				DisDetailsmapInfo.put("distributorInitial",row[1]);
				DisDetailsmapInfo.put("companyName",row[2]);
				DisDetailsmapInfo.put("clientId",row[3]);
				DisDetailsmapInfo.put("loginUrl",row[4]);
				DisDetailsmapInfo.put("innerHeaderImage",row[5]);
				DisDetailsmapInfo.put("poweredBy",row[6]);
				DisDetailsmapInfo.put("panelType",row[7]);
				DisDetailsmapInfo.put("balance",Double.parseDouble(row[8].toString())-Double.parseDouble(row[9].toString()));
				DisDetailsmapInfo.put("cutoffAamount",row[10]);
				DisDetailsmapInfo.put("lastAmount",row[11]);
				DisDetailsmapInfo.put("mdId",row[12]);
				DisDetailsmapInfo.put("mdInitial",row[13]);
				DisDetailsmapInfo.put("domainName",row[14]);
				DisDetailsmapInfo.put("WhitelableCompanyName",row[15]);
				DisDetailsmapInfo.put("DistributorName",row[16]);
				DisDetailsmapInfo.put("MobileNo",row[17]);
				DisDetailsmapInfo.put("agentLoginUrl",row[18]);
				DisDetailsmapInfo.put("agentCellEmailId",row[19]);
				DisDetailsmapInfo.put("DistributorName",row[20]); 
				DisDetailsmapInfo.put("odpass",row[21]);
				DisDetailsmapInfo.put("TickerMessage",row[22]);
				
				//emailVerified=(String) row[23];

				/*if(emailVerified.equalsIgnoreCase("N")){
					Status="email";
				}else{*/
					//Status="login";
				//}

				Status="login";
			}
			log.print("Distributor profile status is "+Status, logger);

			DisDetailsmapInfo.put("Status",Status);
		}
		catch(Exception ex){
			Status="invaliduser";
			DisDetailsmapInfo.put("Status",Status);
			ex.printStackTrace();
		}
		finally {
			try {

				session.close();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return DisDetailsmapInfo;
	}

	public String checkEmailID(String emailID,String distributorId,String userType){
		Session session = HibernateSession.getSessionFactory().openSession();
		String Status="";
		try{

			Query query=session.createQuery("select ds.status from LoginForm ds where ds.loginId=:loginId and convert(varchar,ds.userId)!=:userId and ds.type=:type").setParameter("loginId",emailID).setParameter("userId",distributorId).setParameter("type",userType);

			Iterator<?> it=query.iterate();
			if(it.hasNext()){
				Status="exit";
			}
			else {

				Status="notexit";
			}

		}

		catch(Exception ex){
			Status="exit";
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

		return Status;
	}
	public String updateTxnKey(long distributorId,String txnKey){

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String emailID=null;
		try{
			tran=session.beginTransaction();
			String sqlquery="update distributor_login_details set txn_key='"+txnKey+"' where user_id="+distributorId;
			Query query = session.createSQLQuery(sqlquery);		
			int result = query.executeUpdate();	
			logger.info("result >  :: "+result);
			tran.commit();
		}
		catch(Exception ex){
			tran.rollback();
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
		return emailID;
	}
	
	public boolean varifyTxnKey(long distributorId,String oldtxnKey){

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		try{
			tran=session.beginTransaction();
			String sqlquery="select txn_key from distributor_login_details where user_id="+distributorId;
			Query query = session.createSQLQuery(sqlquery);		
			String result =(String)query.uniqueResult();
			
			if(oldtxnKey.equals(result))
				return true;
			else 
				return false;
			
		}
		catch(Exception ex){
			tran.rollback();
			ex.printStackTrace();
			return false;
		}
		finally {
			try {

				session.close();
			}
			catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
	public String updatePassGetEmailid(String distributorId,String varificationcode){

		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String emailID=null;
		try{
			tran=session.beginTransaction();
			String sqlquery="update distributor_login_details set email_verified_code='"+varificationcode+"' where user_name='"+distributorId+"'";
			Query query = session.createSQLQuery(sqlquery);		
			int result = query.executeUpdate();	
			tran.commit();
			System.out.println(" result is ::"+result);
			if(result>0){	
				String qry="select email_id from distributor_details where email_id='"+distributorId+"'";
				query = session.createSQLQuery(qry);
				emailID=(String) query.uniqueResult();
			}
		}
		catch(Exception ex){
			tran.rollback();
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
		return emailID;
	}

	public HashMap<String,Object> verifyEmail(String distributorId,String VerifiedCode){
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String code="";
		String Status="invalidcode";
		String mobileCode="";
		String mobilenumber="";
		String mobileStatus="Y";
		HashMap<String,Object> mapInfo=new HashMap<String,Object>();
		try{

			Query query=session.createQuery("select dsl.mobileVerified,dsd.AuthorizedMobile from LoginForm dsl,DistributorDetailForm dsd where dsl.userId=:userId and dsl.emailVerifiedCode=:emailVerifiedCode and dsl.userId=convert(varchar,dsd.distributorId)").setParameter("userId",distributorId).setParameter("emailVerifiedCode",VerifiedCode);

			Iterator<?> it=query.iterate();
			if(it.hasNext()){
				Object[] row = (Object[]) it.next();
				code="exit";
				mobileStatus=(String) row[0];
				mobilenumber=(String) row[1];
			}
			if(code.equals("exit")){
				mobileCode=getRandomCode();
				Status=mobileCode;
				tran=session.beginTransaction();
				LoginForm Loginform = (LoginForm)session.get(LoginForm.class,distributorId);
				if(Loginform!=null){
					Loginform.setEmailVerified("Y");
					Loginform.setMobileVerifiedCode(mobileCode);
					session.update(Loginform);
				}
				tran.commit();
			}

			else{
				Status="invalidcode";
			}

			mapInfo.put("Status", Status);
			mapInfo.put("mobilenumber", mobilenumber);
			mapInfo.put("mobileStatus", mobileStatus);
		}

		catch(Exception ex){
			tran.rollback();
			mapInfo.put("Status","invalidcode");
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

		return mapInfo;
	}
	public String verifyMobile(String distributorId,String VerifiedCode){
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String CodeStatus="";
		String Status="invalidcode";

		try{

			//Query query=session.createQuery("select dsl.EmailVerifiedCode from LoginForm dsl where dsl.userId=:userId and dsl.EmailVerifiedCode=:VerifiedCode").setParameter("userId",distributorId).setParameter("VerifiedCode",VerifiedCode);
			Query query=session.createSQLQuery("select email_verified_code from distributor_login_details where user_id='"+distributorId+"' and  email_verified_code='"+VerifiedCode+"' ");
			String Vcode =(String) query.uniqueResult();
			//String Code=(String)query.uniqueResult();
			if (Vcode.equalsIgnoreCase(VerifiedCode)){
				CodeStatus="exit";
			}
			if(CodeStatus.equals("exit")){
				Status="verified";
				System.out.println("we are heere ");
				tran=session.beginTransaction();
				LoginForm Loginform=(LoginForm)session.get(LoginForm.class,distributorId);
				if(Loginform!=null){
					Loginform.setEmailVerified("Y");
					session.update(Loginform);
				}
				tran.commit();
			}

			else{
				Status="invalidcode";
			}


		}

		catch(Exception ex){
			tran.rollback();
			Status="invalidcode";
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

		return Status;
	}

	public HashMap<String,Object> getVerifyMobilrcode(String distributorId){
		Session session = HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		String mobileCode="";
		String mobilenumber="";
		HashMap<String,Object> mapInfo=new HashMap<String,Object>();
		try{

			Query query=session.createQuery("select dsl.mobileVerified,dsd.AuthorizedMobile from LoginForm dsl,DistributorDetailForm dsd where dsl.userId=:userId and dsl.userId=convert(varchar,dsd.distributorId)").setParameter("userId",distributorId);

			Iterator<?> it=query.iterate();
			if(it.hasNext()){
				Object[] row = (Object[]) it.next();
				mobilenumber=(String) row[1];
			}

			mobileCode=getRandomCode();

			tran=session.beginTransaction();
			LoginForm Loginform = (LoginForm)session.get(LoginForm.class,distributorId);
			if(Loginform!=null){
				Loginform.setMobileVerifiedCode(mobileCode);
				session.update(Loginform);
			}
			tran.commit();


			mapInfo.put("mobileCode", mobileCode);
			mapInfo.put("mobilenumber", mobilenumber);

		}

		catch(Exception ex){
			tran.rollback();
			mapInfo.put("mobileCode","mobileCode");
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

		return mapInfo;
	}


	public String getRandomCode() {


		String verificationcode="";
		try
		{

			int n = 8;
			Random randGen = new Random();
			int startNum = (int) Math.pow(10, n-1);
			int range = (int) (Math.pow(10, n) - startNum);
			int randomNum = randGen.nextInt(range) + startNum;
			verificationcode = String.valueOf(randomNum);



		}catch(Exception e)
		{
			System.out.println("Exception in egnrating random number"+e.toString());
		}

		return verificationcode;
	}

}