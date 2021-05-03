package com.msmart.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.ws.rs.core.MediaType;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.msmart.dao.MobileAppRechargeDao;
import com.msmart.service.CommonServices;
import com.msmart.util.UtilityP;

public class MarsController 
{
	Logger logger=Logger.getLogger(MarsController.class);
	
	HashMap<String, String> GetReqText(String marsResponse,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String, String>();
		String refId="";
		String marsRefId="";
		try 
		{			
			//marsResponse="REQUEST ACCEPTED your ref=1234 mars_reference=123455";
			//marsResponse="REQUEST ERROR your ref=1234 mars_reason=due to technical failure";
			hmap.put("ResoponseXML", marsResponse);
			hmap.put("InputXML", url);
			if(marsResponse!=null)
			{
				ArrayList<String> s1=new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(marsResponse, " //=");
		        while (st.hasMoreTokens()) 
		        {
		        	s1.add(st.nextToken());
		        }
				for (int j = 0; j < s1.size(); j++)
				{
					if (s1.get(j).equalsIgnoreCase("ACCEPTED"))
					{
						for (int k = j+1; k < s1.size(); k++)
						{
							if (s1.get(k).equalsIgnoreCase("ref"))
							{
								refId=s1.get(k+1);
								
							}
							if (s1.get(k).equalsIgnoreCase("mars_reference"))
							{
								marsRefId=s1.get(k+1);
								
							}
						}
						hmap.put("res_code", "00");
						hmap.put("res_msg", "Recharge Request Submitted Successfully");
						hmap.put("refId", refId);
						hmap.put("marsRefId", marsRefId);
						hmap.put("Status", "Success");
					}
					if (s1.get(j).equalsIgnoreCase("ERROR"))
					{
						String[] marsResponsearr=marsResponse.split("\\;");
						String[] refarr=marsResponsearr[1].split("\\=");
						String[] reasonarr=marsResponsearr[2].split("\\=");
						if(refarr.length>1)
							refId=refarr[1];
						
						if(reasonarr.length>1)
							marsRefId=reasonarr[1];
						
						
						hmap.put("Status", "Error");
						hmap.put("refId", refId);
						hmap.put("mars_reason", marsRefId);
						hmap.put("res_code", "02");
						hmap.put("res_msg", "Transaction Failure");
					}
					
					
					
				}
			}
			else
			{
				hmap.put("Status", "Invalid");
				hmap.put("res_code", "144");
				hmap.put("res_msg", "Pending");
			}
			
			 System.out.println("hmap : "+hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			hmap.put("Status", "Invalid");
		}
		return hmap;
	}
	
	HashMap<String, String> GetRespText(String marsResponse,String url)
	{		
		HashMap<String, String> hmap=new HashMap<String, String>();
		String status="";
		String transid="";
		try 
		{			
			//marsResponse="number=7404671701 amount=10 status=SUCCESS transid=100005477702832.Thank  simbal=1054.0";
			//marsResponse="number=9017246040 amount=20 status=SUCCESS transid=  simbal=70.";
			hmap.put("InputXML", url);
			hmap.put("ResoponseXML", marsResponse);
			if(marsResponse!=null)
			{
				ArrayList<String> s1=new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(marsResponse, " //=");
		        while (st.hasMoreTokens()) 
		        {
		        	s1.add(st.nextToken());
		        }
				for (int k = 0; k < s1.size(); k++)
						{
							if (s1.get(k).equalsIgnoreCase("status"))
							{
								status=s1.get(k+1);
								
							}
							if (s1.get(k).equalsIgnoreCase("transid"))
							{
								String txnid=s1.get(k+1);
								if(!txnid.equalsIgnoreCase("")){
									String[] transidarr=txnid.split("\\.");
									transid=transidarr[0];
								}
								
							}
						}
						hmap.put("marsRefId", transid);
						hmap.put("Status", status);
					
			}else{
				hmap.put("Status", "Invalid");
			}
			
			 System.out.println("hmap : "+hmap);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			hmap.put("Status", "Invalid");
		}
		return hmap;
	}

	public  String getCharacterDataFromElement(Element e) 
	{
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) 
		{
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "?";
	}	
	
	public  HashMap<String,String> getMarsRequestStatus(String marsRefId)throws ParserConfigurationException, SAXException, IOException 
	{		
		HashMap<String,String> result =new HashMap<String,String>();
		String searchResponse=null;
		String url="";
		String ip=MobileAppRechargeDao.getVendorIp();
		String requestContentType=MediaType.TEXT_XML;
		try
		{
			url = "http://"+ip+"/MARSrequeststatus/?mars_reference="+marsRefId;
	  
			logger.info("Input request from mars status is: "+url);
	        searchResponse = UtilityP.post(url, null, requestContentType, "GET", null);
	        logger.info("searchResponse for egen :"+searchResponse);
            result=GetRespText(searchResponse,url);
		}
		catch(Exception e)
		{
			searchResponse="number=1111111111 amount=20 status=Error transid=111111111  simbal=70";			
			result=GetRespText(searchResponse,url);
			System.out.println("exception in EgenControler"+e.toString());
		}	      				
	  return result;	  
	}
	
	public  HashMap<String,String> sndMarsRequest(String mobno,String amtrechg,
			String agenttranno,String Opcode) throws ParserConfigurationException, SAXException, IOException 
	{		  
		HashMap<String,String> result =new HashMap<String,String>();
		String url="";	
		String searchResponse=null;
		String ip=MobileAppRechargeDao.getVendorIp();
		String requestContentType=MediaType.TEXT_XML;
		try
		{
			//180.188.239.14:91
			url = "http://"+ip+"/MARSrequest/?operator=" + Opcode + "&number=" + mobno + "&amount=" + amtrechg + "&reqref=" + agenttranno+"&rcacnumber=900&rcacpassword=8285283150&rcacauthno=8285283150";
			
			logger.info("Input request from Mars is: "+url);
			CommonServices.insertEKOLogs("Mars", agenttranno, url, "");
			
			searchResponse = UtilityP.post(url, null, requestContentType, "GET", null);
			logger.info("Out put response from Mars is: "+searchResponse);	
			try {
				CommonServices.updateEKOLogs(agenttranno, searchResponse, "");
			} catch (Exception e) {
				// TODO: handle exception
			}
	        result=GetReqText(searchResponse,url);	      
		}
		catch(Exception e)
		{
			searchResponse="REQUEST ERROR your ref=111111 mars_reason=due to technical failure";
			result=GetReqText(searchResponse,url);
			System.out.println("exception in EgenControler"+e.toString());
		}
		return result;
	}
	
}

