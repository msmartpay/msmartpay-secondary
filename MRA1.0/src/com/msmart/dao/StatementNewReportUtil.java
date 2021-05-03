package com.msmart.dao;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class StatementNewReportUtil {

	public static String createXmlResponseStatement(ArrayList<HashMap<String, String>> data_list)
	{
		HashMap<String, String> data_map;
		int size=0;
		try{
			size=data_list.size();
			String xmlString;
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element root1=doc.createElement("response-Statement");
			doc.appendChild(root1);
			for(int i=0;i<size;i++){
				data_map=data_list.get(i);
				Element  root= doc.createElement("transaction_detail");
				root1.appendChild(root);	

				//        Element id = doc.createElement("Id_No");
				//        root.appendChild(id);
				//        Text id_num = doc.createTextNode(data_map.get("Id_No"));
				//        id.appendChild(id_num);
				//        
				//        Element tran_id = doc.createElement("Tran_Id");
				//        root.appendChild(tran_id);
				//        Text tran_id_name = doc.createTextNode(data_map.get("tran_id"));
				//        tran_id.appendChild(tran_id_name);
				//        
				//        Element service = doc.createElement("Service");
				//        root.appendChild(service);
				//        Text service_txt = doc.createTextNode(data_map.get("service"));
				//        service.appendChild(service_txt);
				//        
				//        
				//        Element mobile_operator = doc.createElement("Optr");
				//        root.appendChild(mobile_operator);
				//        Text mobile_operator_txt = doc.createTextNode(data_map.get("mobile_operator"));
				//        mobile_operator.appendChild(mobile_operator_txt);




				Element mobile_number = doc.createElement("Mob_Num");
				root.appendChild(mobile_number);
				Text mobile_number_txt = doc.createTextNode(data_map.get("mobile_number"));
				mobile_number.appendChild(mobile_number_txt);

				//        Element Action_on_bal_amt = doc.createElement("Action");
				//        root.appendChild(Action_on_bal_amt);
				//        Text Action_on_bal_amt_txt = doc.createTextNode(data_map.get("Action_on_bal_amt"));
				//        Action_on_bal_amt.appendChild(Action_on_bal_amt_txt);


				Element status = doc.createElement("Status");
				root.appendChild(status);
				Text status_text = doc.createTextNode(data_map.get("tran_status"));
				status.appendChild(status_text);

				//        Element amount = doc.createElement("Amt");
				//        root.appendChild(amount);
				//        Text amount_text = doc.createTextNode(data_map.get("DeductedAmt"));
				//        amount.appendChild(amount_text);

				Element net_amout = doc.createElement("Net_Amt");
				root.appendChild(net_amout);
				Text net_amout_text = doc.createTextNode(data_map.get("net_amout"));
				net_amout.appendChild(net_amout_text);

				//        Element dot = doc.createElement("DOT");
				//        root.appendChild(dot);
				//        Text dot_text = doc.createTextNode(data_map.get("dot"));
				//        dot.appendChild(dot_text);
				//        
				//        Element tot = doc.createElement("TOT");
				//        root.appendChild(tot);
				//        Text tot_text = doc.createTextNode(data_map.get("tot"));
				//        tot.appendChild(tot_text);
				//        
				//        Element Agent_balAmt_b_Ded = doc.createElement("Open_Bal");
				//        root.appendChild(Agent_balAmt_b_Ded);
				//        Text Agent_balAmt_b_Ded_text = doc.createTextNode(data_map.get("Agent_balAmt_b_Ded"));
				//        Agent_balAmt_b_Ded.appendChild(Agent_balAmt_b_Ded_text);
				//        
				//        Element Agent_F_balAmt = doc.createElement("Close_Bal");
				//        root.appendChild(Agent_F_balAmt);
				//        Text Agent_F_balAmt_text = doc.createTextNode(data_map.get("Agent_F_balAmt"));
				//        Agent_F_balAmt.appendChild(Agent_F_balAmt_text);
			}

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			transformer.transform(source, result);
			xmlString = sw.toString();
			data_list.clear();
			return xmlString;

		}catch(Exception ex)
		{
			System.out.println("Exception "+ex);
			ex.printStackTrace();
		}
		return null;
	}

}
