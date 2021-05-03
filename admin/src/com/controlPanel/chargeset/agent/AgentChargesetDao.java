package com.controlPanel.chargeset.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.common.HibernateSession;

public class AgentChargesetDao 
{
	public ArrayList getAgentMarginDetails(String enterUserID,String service,String type,int portalId,String userType) 
	{
		Session session=null;
		Query query=null;
		int id=0; 
		System.out.println("User Type  :  "+userType+"  : "+enterUserID+" : "+service+" : "+type+" : "+portalId);
		HashMap map=new HashMap();
		
		ArrayList mapList=new ArrayList();
		try 
		{
			session=HibernateSession.getSessionFactory().openSession();
		
			String sqlquery="";
			String sql="";
			
			if(!type.equalsIgnoreCase("ByClientId"))
			{
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{			
					if(type.equalsIgnoreCase("ByMdId"))
					{
						sqlquery="select md_id from MD_Details where MD_initial+CONVERT(varchar(14),MD_id)='"+enterUserID+"'";
					}
					
					if(type.equalsIgnoreCase("ByDsId"))
					{
						sqlquery="select distributor_id from distributor_Details where distributor_initial+CONVERT(varchar(14),distributor_id)='"+enterUserID+"'";
					}
						
					if(type.equalsIgnoreCase("ByAgentId"))
					{
						sqlquery="select agent_id from agent_details where agent_initial+convert(varchar(12),agent_id)='"+enterUserID+"'";
					}
				}
				
				if(userType.equalsIgnoreCase("portalAdmin")||userType.equalsIgnoreCase("portalUser"))
				{
						
					if(type.equalsIgnoreCase("ByMdId"))
					{
						sqlquery="select md_id from MD_Details where MD_initial+CONVERT(varchar(14),MD_id)='"+enterUserID+"' and client_id="+portalId+"";	
					}
					
					if(type.equalsIgnoreCase("ByDsId"))
					{
						sqlquery="select distributor_id from distributor_Details dd,md_details md where dd.distributor_initial+CONVERT(varchar(14),dd.distributor_id)='"+enterUserID+"' and dd.md_id=md.md_id and md.client_id="+portalId+"";	
					}
					
					if(type.equalsIgnoreCase("ByAgentId"))
					{
						sqlquery="select ad.agent_id from agent_details ad,distributor_Details dd,md_details md where ad.agent_initial+convert(varchar(12),ad.agent_id)='"+enterUserID+"' and ad.distributor_id=dd.distributor_id and dd.md_id=md.md_id and md.client_id="+portalId+"";
					}
				}
				System.out.println("query is "+sqlquery);
				//System.out.println("enterUserID : "+enterUserID);
				query=session.createSQLQuery(sqlquery);
				
				String mdid=query.uniqueResult().toString();
				id=Integer.parseInt(mdid);
				//System.out.println("Onlyid : "+id);
			}
			System.out.println("no problem here");
			if(type.equalsIgnoreCase("ByClientId"))
			{
				System.out.println("we are into ByClientId");
				
				if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				{							 	
					sqlquery="select client_id from white_label_details where client_id="+enterUserID+"";
				}

				if(userType.equalsIgnoreCase("portalAdmin")||userType.equalsIgnoreCase("portalUser"))
				{							 	
					sqlquery="select client_id from white_label_details where client_id="+enterUserID+" and client_id="+portalId+"";
				}
				
				query=session.createSQLQuery(sqlquery);
				String clientid=query.uniqueResult().toString();
				id=Integer.parseInt(clientid);
				System.out.println("client_id in query is : "+id);
			}
				 
			if(type.equalsIgnoreCase("ByMdId"))
			{
				System.out.println("we are into ByMdId");
				
				if(service.equalsIgnoreCase("recharge"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Recharge' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("Utility"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Utility' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("TestMerit"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='TestMerit' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("Hotel"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Hotel' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("dth"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='DTH-X' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("Ops"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Ops' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("PayCard"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='PayCard' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("Bus"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Bus' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
				
				if(service.equalsIgnoreCase("Air"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm  from Commission_Share cs,SKU_Master skum where  cs.MD_ID='"+id+"' and cs.Main_Service='Flights' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
			}
				
			if(type.equalsIgnoreCase("ByDsId"))
			{
				System.out.println("we are into ByDsId");
					
				if(service.equalsIgnoreCase("recharge"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='recharge' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					
				}
					
				if(service.equalsIgnoreCase("Utility"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='Utility' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("TestMerit"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='TestMerit' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("Hotel"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='Hotel' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("dth"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='DTH-X' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("Ops"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='Ops' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("PayCard"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='PayCard' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";				
				}
					
				if(service.equalsIgnoreCase("Bus"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='Bus' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					
				}
					
				if(service.equalsIgnoreCase("Air"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id  from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd where  cs.MD_ID=md.MD_ID and cs.Main_Service='Flights' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and  md.MD_id=dd.md_id and dd.distributor_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
			}
					
			if(type.equalsIgnoreCase("ByAgentId"))
			{
				System.out.println("we are into ByAgentId");	
					
				if(service.equalsIgnoreCase("recharge"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID   and cs.Main_Service='recharge' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
					
				if(service.equalsIgnoreCase("Utility"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Utility' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					 							
				}
				
				if(service.equalsIgnoreCase("TestMerit"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='TestMerit' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("Hotel"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID and cs.Main_Service='Hotel' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("dth"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='DTH-X' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("Ops"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Ops' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";						
				}
					
				if(service.equalsIgnoreCase("PayCard"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='PayCard' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";				
				}
				
				if(service.equalsIgnoreCase("Bus"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Bus' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category" ;
				}
					
				if(service.equalsIgnoreCase("Air"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Flights' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and ad.Agent_id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";
				}
			}
					
			if(type.equalsIgnoreCase("ByClientId"))
			{
				System.out.println("we are into ByClientId again");
				
				if(service.equalsIgnoreCase("recharge"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='recharge' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";	
				}
				
				if(service.equalsIgnoreCase("Utility"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Utility' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";			
				}
				
				if(service.equalsIgnoreCase("TestMerit"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID   and cs.Main_Service='TestMerit' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category	";				 		
				}
						
				if(service.equalsIgnoreCase("Hotel"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID   and cs.Main_Service='Hotel' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					 		
				}
				
				if(service.equalsIgnoreCase("dth"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='DTH-X' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category	";				 		
				}
				
				if(service.equalsIgnoreCase("Ops"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Ops' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					 		
				}
					
				if(service.equalsIgnoreCase("PayCard"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID and cs.Main_Service='PayCard' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category	";				 		
				}
					
				if(service.equalsIgnoreCase("Bus"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID and cs.Main_Service='Bus' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category	";				 		
				}
					
				if(service.equalsIgnoreCase("Air"))
				{
					sql="select cs.Main_Service,cs.Agent_Share,cs.SKU_Name,cs.MD_Share,cs.Client_Share,cs.Sub_Service,convert(decimal(18,2),skum.SKU_Charge) as SKU_Charge,skum.SKU_Charge_Type,convert(decimal(18,2),skum.SKU_Comm) as SKU_Comm,dd.distributor_id ,md.client_id from Commission_Share cs,SKU_Master skum,MD_Details md,distributor_details  dd,agent_details ad where  cs.MD_ID=md.MD_ID  and cs.Main_Service='Flights' and cs.Sub_Service=skum.Sub_Service and cs.Main_Service=skum.Main_Service and ad.distributor_id=dd.distributor_id and  md.MD_id=dd.md_id and md.Client_Id='"+id+"' and cs.SKU_Name=skum.SKU_Name and cs.Category=skum.Category";					 		
				}
			}
			
			query=session.createSQLQuery(sql);
			List list=query.list();
			Iterator itr=list.iterator();
				
			while(itr.hasNext())
			{
				Object[] row=(Object[])itr.next();				
				HashMap wlmap=new HashMap();
					
				if(type.equalsIgnoreCase("ByAgentId"))
				{
					wlmap.put("Main_Service", row[0]);
					wlmap.put("Sub_Service", row[5]);
					wlmap.put("Category", row[2]);
					wlmap.put("SKU_Charge",row[6]);
					wlmap.put("SKU_Charge_Type",row[7]);
						
					mapList.add(wlmap);
				}
				if(type.equalsIgnoreCase("ByMdId")||type.equalsIgnoreCase("ByDsId"))
				{
					wlmap.put("Main_Service", row[0]);
					wlmap.put("Sub_Service", row[5]);
					wlmap.put("Category", row[2]);
					wlmap.put("SKU_Charge",row[6]);
					wlmap.put("SKU_Charge_Type",row[7]);
						
					mapList.add(wlmap);
						
				}
				if(type.equalsIgnoreCase("ByClientId"))
				{				
					wlmap.put("Main_Service", row[0]);
					wlmap.put("Sub_Service", row[5]);
					wlmap.put("Category", row[2]);
					wlmap.put("SKU_Charge",row[6]);
					wlmap.put("SKU_Charge_Type",row[7]);
						
					mapList.add(wlmap);	
				}
			}
				
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				session.flush();
				session.close();
			} catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return mapList;
	}
		
	public String getUserDetails(String type,String userid) 
	{
		Session session=null;
		String status="invalid";
	       
		Query query=null;
		Transaction tran=null;
		String sql=""; 
		try 
		{
			session = HibernateSession.getSessionFactory().openSession();
			tran=session.beginTransaction();
			
			if(type.equalsIgnoreCase("ByMdId"))
			{
				sql="select md_id from MD_Details where MD_initial+CONVERT(varchar(6),MD_id)='"+userid+"'";	
			}
				
			if(type.equalsIgnoreCase("ByAgentId"))
			{
				sql="select agent_id from agent_details where agent_initial+convert(varchar(12),agent_id)='"+userid+"'";
			}
			
			query=session.createSQLQuery(sql);
			Object obj=query.uniqueResult();
			
			if(obj!=null)
			{
				String id=obj.toString();
				int idonly=Integer.parseInt(id);
				//System.out.println("Onlyid : "+idonly);
				status="valid";
			}		  
		} catch (Exception e) 
		{
			e.printStackTrace();
			tran.rollback();
		}
		finally 
		{
			try 
			{
				session.flush();
				session.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return status;
	}
}
