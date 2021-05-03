package com.activity.adminTask.categoryCreation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;
import com.formBean.category.CategoryNameDetails;


public class CategoryCreationDao 
{

	public String getAvailability(String newCatName) 
	{
		
		Session session=HibernateSession.getSessionFactory().openSession();
		Query qry=null;
	    String  rs="notValid";
		try 
		{
			qry=session.createQuery("select CND.categoryName from  CategoryNameDetails CND where CND.categoryName=:name")
			.setParameter("name", newCatName);
			Iterator<?>it=qry.iterate();
			if(it.hasNext()){
				rs="Valid";
			
		}
		} 
		catch (Exception e) 
		{
			rs="notValid";
			System.out.println("Exception in CategoryCreationdDao,getAvailability");
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e2) 
			{
				System.out.println("Exception in CategoryCreationdDao,getAvailability");
				System.out.println(e2.toString());
			}
		}
		return rs;
	}

	public String createcategory(String updateBy, String newCatName) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		String UpdateStatus="NotUpdate";
		Transaction tran=null;
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		try {
			tran=session.beginTransaction();
			CategoryNameDetails form=(CategoryNameDetails)session.get(CategoryNameDetails.class,newCatName);
			if(form==null){
				CategoryNameDetails formNew=new CategoryNameDetails();
				formNew.setCategoryName(newCatName);
				formNew.setUpdatedUser(updateBy);
				formNew.setUpdatedDate(sqlDate);
				session.save(formNew);
				UpdateStatus="success";
			} 
			tran.commit();	 
		} catch (Exception e) {
			UpdateStatus="notUpdate";
			tran.rollback();
			System.out.println("Exception in CategoryCreationdDao,createcategory");
			System.out.println(e.toString());
		}
		finally{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,createcategory");
				System.out.println(e2.toString());
			}
		}
		return UpdateStatus;
	}

	public ArrayList<HashMap<String, Object>> getCatDetails() 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		ArrayList<HashMap<String,Object>> dataArray=new ArrayList<HashMap<String,Object>>();
		Query qry=null;
		try 
		{
			qry=session.createQuery("select distinct  CND.categoryName  from  CategoryNameDetails CND");
			Iterator<?> it=qry.iterate();
			while(it.hasNext()){
				Object row=(Object)it.next();
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("catName", row);
				dataArray.add(map);
			}
		} catch (Exception e) {
			System.out.println("Exception in CategoryCreationdDao,getCatDetails");
			System.out.println(e.toString());
		}
		finally{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,getCatDetails");
				System.out.println(e2.toString());
			}
		}
		return dataArray;
	}

	public ArrayList<HashMap<String,Object>> getCommDetails(String categoryName,String service,String actionUpdate) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		HashMap<String,Object> map=new HashMap<String,Object>();
		DecimalFormat dff = new DecimalFormat("##.##");
		ArrayList<HashMap<String,Object>> commList=new ArrayList();
		String servicein="";
		try {
			if(actionUpdate.equalsIgnoreCase("commission")){
				if(service.equalsIgnoreCase("Others")){
					servicein="Rail','Request Pan Card','Bus','Edu Package','Air";
				}
				else {
					servicein=service;
				}
				if(servicein.equalsIgnoreCase("Air")){
					Query query1=session.createQuery("select cca.carrier,cca.discountOnBasicInPer,cca.discountOnYQInPer,cca.discountOnGrossInPer,cca.adiscountInRS,cca.markupMaxRs,cca.cancelationChargeMaxRs,cca.errorComm,cca.fromAirPortList,cca.validFromDate,cca.validToDate from CategoryCommFormBeanAir cca where cca.categoryName=:categoryName")
					.setParameter("categoryName",categoryName);
					for(Iterator it=query1.iterate();it.hasNext();){
						Object[] row = (Object[]) it.next();		     
						HashMap<String,Object> commMap=new HashMap();
						commMap.put("flight", row[0]);
						commMap.put("disonbasic", row[1]); 
						commMap.put("disonyq", row[2]); 
						commMap.put("disongros", row[3]); 
						commMap.put("disinrs", row[4]); 
						commMap.put("maxmarkup", row[5]); 
						commMap.put("ccharges", row[6]);
						commMap.put("errorComm", row[7]); 
						commMap.put("sector", row[8]); 
						commMap.put("validfrom", row[9]);
						commMap.put("validto", row[10]);
						commList.add(commMap);
					}
				}
				else {
					Query query1=session.createQuery("select cc.categoryName,cc.service,cc.subService,cc.operator," +
							"cc.operatorComm,cc.operatorCommMark from CategoryCommFormBean cc where cc.service in('"+servicein+"') and cc.categoryName=:categoryName")
							.setParameter("categoryName",categoryName);
					for(Iterator it=query1.iterate();it.hasNext();){
						Object[] row = (Object[]) it.next();		     
						HashMap<String,Object> commMap=new HashMap();
						commMap.put("categoryName", row[0]);
						commMap.put("service", row[1]); 
						commMap.put("subService", row[2]); 
						commMap.put("operator", row[3]); 
						commMap.put("operatorComm", row[4]); 
						commMap.put("operatorCommMark", row[5]); 
						commList.add(commMap);
					}
				}
			}
			else {
				if(service.equalsIgnoreCase("Others")){
					servicein="Rail','Request Pan Card";
				}
				else {
					servicein=service;
				}
				Query query1=session.createQuery("select cc.categoryName,cc.service,cc.subService,cc.operator," +
						"cc.operatorCharge,cc.operatorChargeMark from CategoryServiceChargeFormBean cc where cc.service in('"+servicein+"') and cc.categoryName=:categoryName")
						.setParameter("categoryName",categoryName);
				for(Iterator it=query1.iterate();it.hasNext();){
					Object[] row = (Object[]) it.next();		     
					HashMap<String,Object> commMap=new HashMap();
					commMap.put("categoryName", row[0]);
					commMap.put("service", row[1]); 
					commMap.put("subService", row[2]); 
					commMap.put("operator", row[3]); 
					commMap.put("operatorComm", row[4]); 
					commMap.put("operatorCommMark", row[5]); 
					commList.add(commMap);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception in CategoryCreationdDao,getCommDetails");
			System.out.println(e.toString());
		}
		finally{
			try {
				session.flush();
				session.close();
			} catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,getCommDetails");
				System.out.println(e2.toString());
			}
		}
		return commList;
	}
	
	public void updateCategoryMargin(String categoryName,String service,String subService,String margin,String mark,String operator,String actionUpdate) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		double marginValue=0;
		try 
		{
			marginValue=Double.parseDouble(margin);
			tran=session.beginTransaction();
			if(actionUpdate.equalsIgnoreCase("commission"))
			{
				Query query1=session.createQuery("update CategoryCommFormBean cc set cc.operatorComm=:operatorComm,cc.operatorCommMark=:operatorCommMark where  cc.service=:service and cc.categoryName=:categoryName and cc.subService=:subService and cc.operator=:operator")
				.setParameter("operatorComm",marginValue)
				.setParameter("operatorCommMark",mark)
				.setParameter("service",service)
				.setParameter("categoryName",categoryName)
				.setParameter("subService",subService)
				.setParameter("operator",operator);
				query1.executeUpdate();
			}
			else 
			{
				Query query1=session.createQuery("update CategoryServiceChargeFormBean cc set cc.operatorCharge=:operatorCharge,cc.operatorChargeMark=:operatorChargeMark where  cc.service=:service and cc.categoryName=:categoryName and cc.subService=:subService and cc.operator=:operator")
				.setParameter("operatorCharge",marginValue)
				.setParameter("operatorChargeMark",mark)
				.setParameter("service",service)
				.setParameter("categoryName",categoryName)
				.setParameter("subService",subService)
				.setParameter("operator",operator);
				query1.executeUpdate();
			}
			tran.commit(); 
		} catch (Exception e) 
		{
			if(tran!=null)
			{
				tran.rollback();
			}
			System.out.println("Exception in CategoryCreationdDao,updateCategoryMargin");
			System.out.println(e.toString());
		}
		finally{
			try {
				session.flush();
				session.close();
			} 
			catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,updateCategoryMargin");
				System.out.println(e2.toString());
			}
		}
	}
	
	public void updateCategoryMarginAir(String categoryName,String flight,String sector,float disbasic,float disYQ,float disgros,float disrs,float markup,float ccharge,float errorComm ) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
	    Transaction tran=null;
		System.out.println("category is >>>>>>>>>>>>"+categoryName);
		System.out.println("flight is >>>>>>>>>>>>"+flight);
		System.out.println("sector is >>>>>>>>>>>>"+sector);
		try {
			tran=session.beginTransaction();
			Query query1=session.createQuery("update CategoryCommFormBeanAir cca set cca.discountOnBasicInPer=:discountOnBasicInPer,cca.discountOnYQInPer=:discountOnYQInPer,cca.discountOnGrossInPer=:discountOnGrossInPer,cca.adiscountInRS=:adiscountInRS,cca.markupMaxRs=:markupMaxRs,cca.cancelationChargeMaxRs=:cancelationChargeMaxRs,cca.errorComm=:errorComm where  cca.categoryName=:categoryName and cca.fromAirPortList=:fromAirPortList and cca.carrier=:carrier")
			.setParameter("discountOnBasicInPer",disbasic)
			.setParameter("discountOnYQInPer",disYQ)
			.setParameter("discountOnGrossInPer",disgros)
			.setParameter("adiscountInRS",disrs)
			.setParameter("markupMaxRs",markup)
			.setParameter("cancelationChargeMaxRs",ccharge)
			.setParameter("errorComm",errorComm)
			.setParameter("categoryName",categoryName)
			.setParameter("fromAirPortList",sector)
			.setParameter("carrier",flight);
			query1.executeUpdate(); 
			tran.commit(); 
		} catch (Exception e) {
			if(tran!=null){
				tran.rollback();
			}
			System.out.println("Exception in CategoryCreationdDao,updateCategoryMarginAir");
			System.out.println(e.toString());
		}
		finally{
			try {
				session.flush();
				session.close();
			} 
			catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,updateCategoryMarginAir");
				System.out.println(e2.toString());
			}
		}
	}
	
	public void updateClinetCatcategory(String categoryName,String clientId) 
	{
		Session session=HibernateSession.getSessionFactory().openSession();
		Transaction tran=null;
		int client_id;
		try 
		{
			client_id=Integer.parseInt(clientId);
	    	tran=session.beginTransaction();
	    	Query query1=session.createSQLQuery("update White_label_details set category_Name='"+categoryName+"' where Client_Id='"+client_id+"'");
	    	
	    	
	    	query1.executeUpdate();
	    	tran.commit(); 
	    	tran=null;
	    	tran=session.beginTransaction();
	    	query1=session.createSQLQuery("update commission_share set Category='"+categoryName+"' where Client_ID='"+client_id+"'");
	    	
	    	query1.executeUpdate();
	    	tran.commit(); 
		} 
		catch (Exception e) 
		{
			if(tran!=null)
			{
				tran.rollback();
			}
			System.out.println("Exception in CategoryCreationdDao,updateClinetCatcategory");
			System.out.println(e.toString());
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e2) 
			{
				System.out.println("Exception in CategoryCreationdDao,updateClinetCatcategory");
				System.out.println(e2.toString());
			}
		}
	}
	
	public ArrayList<ArrayList<Object>> getWLClientDetails()
	{
		Logger logger=Logger.getLogger(CategoryCreationDao.class);
		
		Session session=null;
		ArrayList<ArrayList<Object>>outresult=new ArrayList<ArrayList<Object>>();
		try{
			session=HibernateSession.getSessionFactory().openSession();
			Query query1=session.createQuery("select dd.clientId,dd.categoryName,dd.companyName,clientFlag,dd.domainName from DynamicDetailsFormBean dd");
			for(Iterator it=query1.iterate();it.hasNext();){
				Object[] row = (Object[]) it.next();		     
				ArrayList<Object>result=new ArrayList<Object>();
				result.add(row[0]);
				result.add(row[1]);
				result.add(row[2]);
				result.add(row[3]);
				result.add(row[4]);
				outresult.add(result);
			}
		}
		catch(Exception ex){
		}
		finally{
			try {
				session.flush();
				session.close();
			} 
			catch (Exception e2) {
				System.out.println("Exception in CategoryCreationdDao,getWLClientDetails");
				System.out.println(e2.toString());
			}
		}
		return outresult;
	}
	
	public static void main(String srt[])
	{
		CategoryCreationDao CategoryCreationdao=new CategoryCreationDao();
		CategoryCreationdao.updateCategoryMargin("GOLD1","Recharge Service", "datacard","1","R","IDEA", "commission");
	}
}
