package com.msmart.travel.bus;

import java.util.ArrayList;
import java.util.HashMap;

import com.msmart.travel.bus.dao.BusBookingDao;

public class BusServices {

	
	public String getSource() {
		
		String res="<option value=''>Select Origin</option>";
		ArrayList<HashMap<String,String>> list=null;
		try {
			
			list=BusAPI.getSource();
			
			StringBuilder sb=new StringBuilder();
			
			if(list!=null && list.size()>0){
				
				for (HashMap<String,String> map:list) {
					String option="<option value='"+map.get("OriginId")+"_"+map.get("OriginName")+"'>"+map.get("OriginName")+"</option>";
					sb.append(option);
				}
				res=sb.toString();
				System.out.println("BusServices.getSource()"+res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public ArrayList<HashMap<String,String>> getSourceList() {
		
		ArrayList<HashMap<String,String>> list=null;
		try {
			
			list=BusAPI.getSource();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getDistination(String sourceId) {
		
		String res="<option value=''>Select Distination</option>";
		ArrayList<HashMap<String,String>> list=null;
		try {
			
			list=BusAPI.getDestination(sourceId);
			
			StringBuilder sb=new StringBuilder();
			
			if(list!=null && list.size()>0){
				
				for (HashMap<String,String> map:list) {
					String option="<option value='"+map.get("DestinationId")+"_"+map.get("DestinationName")+"'>"+map.get("DestinationName")+"</option>";
					sb.append(option);
				}
				res=sb.toString();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public HashMap<String,Object> GetCancellationPenaltyRequest(String pnrNumber) {
		BusAPI busAPI=new BusAPI();
		return busAPI.GetCancellationPenaltyRequest(pnrNumber);
	}
}
