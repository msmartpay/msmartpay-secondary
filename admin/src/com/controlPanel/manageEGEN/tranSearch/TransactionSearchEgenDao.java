package com.controlPanel.manageEGEN.tranSearch;


/**	Created By  	: 	Manoj
 * 	Created Date 	:	12/02/2013
 * 	Created Matter	: 	This class will give the details of All data according to a connection number For Egen
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.common.HibernateSession;
import com.common.HibernateSession1;
import com.formBean.API.APICustomerTransactionDetails;

public final class TransactionSearchEgenDao {
	public final ArrayList<HashMap<String,String>> getTranData(String connectionNo) {
		String sql="";
		ArrayList<HashMap<String,String>> listData=new ArrayList<HashMap<String,String>>();
		Session session=HibernateSession1.getSessionFactory().openSession();
		Query query=null;
		try{
			// Live Db Name = OnlineRechAPI_Live_db
			// Test DB name=  OnlineRechAPI_db_2013Jan13
				sql="select top(5) Corporate_Agent_Id,mobiledth_no,mobiledth_operator,convert(varchar(10),date_of_recharge,120) as DOR,convert(varchar(10),date_of_recharge,108) as DOT,str(recharge_Amount,5,2),status from Rech_API_Agent_RechMobileDth_Details where mobiledth_no='"+connectionNo+"' order by date_of_recharge desc";
				//sql="select top(5)a.agent_initial+convert(varchar(10),l.user_id) as AgentID,d.distributor_initial+convert(varchar(10),a.distributor_id) as DSID,m.md_initial+convert(varchar(10),d.md_id) as MDID,m.client_id,l.mobile_number,l.mobile_operator,l.date_of_recharge,convert(varchar(10),l.date_time,108) as time,l.amount,l.status,l.USessionID from live_recharge l,agent_details a,distributor_details d,md_details m where a.agent_id=l.user_id and l.mobile_number='"+connectionNo+"' and a.distributor_id=d.distributor_id and d.md_id=m.md_id order by l.date_time desc";
			    Criteria cr=session.createCriteria(APICustomerTransactionDetails.class);
			    cr.add(Restrictions.eq("accountNoMobileNo", connectionNo));
			    List listTemp= cr.list();
			//System.out.println(sql);
			//query=session.createSQLQuery(sql);
			//List list=query.list();
			Iterator itr=listTemp.iterator();
			HashMap<String,String> mapdata;
			APICustomerTransactionDetails obj;
			//Object [] row;
			while(itr.hasNext()){
				
				mapdata=new HashMap<String,String>();
				obj=(APICustomerTransactionDetails)itr.next();
				mapdata.put("corporateID", obj.getAesTransactionId());
				//mapdata.put("distributorID", row[1].toString());
				//mapdata.put("mdID", row[2].toString());
				//mapdata.put("clientID", row[3].toString());
				mapdata.put("connectionNo",obj.getAccountNoMobileNo());
				mapdata.put("connectionOperator" ,"NA");//mapdata.put("connectionOperator" );
				mapdata.put("dateOfRecharge",obj.getDateOfTransaction()+"" );
				mapdata.put("timeOfRecharge",obj.getTimeOfTransaction()+"" );
				mapdata.put("amount", obj.getRequestAmount()+"");
				mapdata.put("status", obj.getTranStatus());
				//mapdata.put("vendorTranID", (String)row[10]);
				listData.add(mapdata);
			}
		}catch(Exception e){
			System.out.println("Exception in TransactionSearchEgenDao method getTranData");
			System.out.println(e.toString());
		}
		finally{
			try{
				session.flush();
				session.close();
			}catch(Exception ex){
				System.out.println("Exception in TransactionSearchEgenDao method getTranData ");
				System.out.println(ex.toString());
			}
		}
		return listData;
	}

}
