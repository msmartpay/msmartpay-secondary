
package com.pendingTransaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.util.EkoAPICall;
import com.util.PropertyFile;

import net.sf.json.JSONObject;


public class UpdateEkoAEPSPendingTransaction  implements Job {


	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("UpdateEkoAEPSPendingTransaction.execute() >>>>>>>>>>>>>>>>>>>>>>>>>>>  started");
		System.out.println(new Date());
		updateEkoAEPSPendingTransaction();
		System.out.println("UpdateEkoAEPSPendingTransaction.execute() >>>>>>>>>>>>>>>>>>>>>>>>>>>  Stoped");
		System.out.println(new Date());
	}

	public void updateEkoAEPSPendingTransaction() {

		System.out.println("UpdateEkoAEPSPendingTransaction.updateEkoAEPSPendingTransaction() started ............");
		UpdateRechPendingTransactionDao dao=new UpdateRechPendingTransactionDao();

		try {
			HashMap<String, String> map=null;
			ArrayList<HashMap<String,Object>> newlist=new ArrayList<HashMap<String,Object>>();
			ArrayList<HashMap<String,String>> list=dao.fetchEkoAEPSTransactions();
			System.out.println("UpdateEkoAEPSPendingTransaction Pending List >>>>>>>>>>>>>>>>>>>>>>> "+list);
			if(list!=null && list.size()>0){



				for (int i = 0; i < list.size(); i++) {
					map=list.get(i);
					String user_code=map.get("user_code");
					String client_ref_id=map.get("client_ref_id");
					
					EkoAPICall apiCall=new EkoAPICall();
					String url = PropertyFile.ekotranurl +"client_ref_id:"+ client_ref_id + "?initiator_id=" + PropertyFile.initiator_id;
					
					JSONObject ekoResp=apiCall.ekoCall(url, null, "GET");
					System.out.println("ekoResp : "+ekoResp);
					try {
						String bank_ref_num="",updateStatus="",ip="Manual Schedular";
						
						if(ekoResp!=null) {
							JSONObject data=ekoResp.getJSONObject("data");
							String tx_status=data.getString("tx_status");
							
							if("0".equalsIgnoreCase(tx_status)) {
								
								bank_ref_num=data.getString("bank_ref_num");
								String tid=data.getString("tid");
								String amount=data.getString("amount");
								updateStatus="Success";
								String creditStatus=dao.aepsCredit(tid, client_ref_id, bank_ref_num, user_code, 0, 0, Double.parseDouble(amount),  ip,updateStatus);
								System.out.println("AepsCredit Status :"+creditStatus);
							}else if("1".equalsIgnoreCase(tx_status)) {
								updateStatus="Failure";
								int status=dao.updateAepsLogStatus(client_ref_id, updateStatus);
								System.out.println("status "+status);
							}else {
								
							}
							
						}
						
						

					} catch (Exception e) {
						// TODO: handle exception
					}

				}



				// Success list
				System.out.println("UpdateEkoPendingTransaction.UpdateEkoPendingTransaction() :: "+newlist.toString());
			}

			System.out.println("UpdateEkoPendingTransaction.UpdateEkoPendingTransaction() MARS End ............");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject liveStatus(String senderId,String tranRefId) {

		JSONObject jsonobj=null;
		try {


			JSONObject fsJson=new JSONObject();
			fsJson.put("Key", "21b8d575871379e74ae336f3cb5a8561a5dfb5bf");
			fsJson.put("AgentID", "2002");
			fsJson.put("SenderId", senderId);
			fsJson.put("TransactionRefNo", tranRefId);
			System.out.println("fsJson : "+fsJson);
			jsonobj =callApi("TransStatus", fsJson);

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
		return jsonobj;
	}


	public JSONObject callApi(String url,JSONObject obj) {

		JSONObject jsonobj;
		try {

			String output;
			Client client = Client.create();

			System.err.println("Data : "+url);

			String finalUrl="http://api.softsolutionzone.in/resources/SKDMR/"+url;
			WebResource webResource = client.resource(finalUrl);

			ClientResponse response = webResource.type("application/json").post(ClientResponse.class, obj==null?obj:obj.toString());

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			output = response.getEntity(String.class);
			JSONParser jsonParser=new JSONParser();
			jsonParser.parse(output);
			jsonobj =(JSONObject)jsonParser.parse(output);;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
		return jsonobj;
	}

}
