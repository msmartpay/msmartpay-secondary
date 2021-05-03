package com.dthConnection;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.common.BackButtonDao;
import com.common.GenerateIdDao;
import com.dthConnection.DthConnectionDao;
import com.opensymphony.xwork2.ActionSupport;
import com.utility.SendSmtpMail;

public final class DthConnectionAction  extends ActionSupport{
	
	
	Logger logger=Logger.getLogger(DthConnectionAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() throws Exception{
		
		HttpServletRequest request;
		HttpSession session;
		HttpServletResponse response;
		String agentID="";
		try{

			request=ServletActionContext.getRequest();
			response=ServletActionContext.getResponse();
			session=request.getSession();
			agentID=(String)session.getAttribute("agentID");
			if(agentID==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			
			String param=request.getParameter("param");
            logger.info("TEP ,Class is DthConnectionAction ,Param is "+param);
			DthConnectionDao daoObj=new DthConnectionDao();
			
			 
		if("DthConnection".equalsIgnoreCase(param)){
				return "DthConnection";
				
		}else if("dthConnectionRequest".equalsIgnoreCase(param)){
			
			Date today = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("HHmmSS");
			String stringTime = formatter.format(today);
			Integer intTime = Integer.valueOf(stringTime);
			
			
			String Location="All Circle"; 
	        String Product=request.getParameter("productName");
	        String Description=request.getParameter("productNameDesc");
	        String Name=request.getParameter("custName");
	        String Address=request.getParameter("address");
	        String State=request.getParameter("stateName");	
	        String City=request.getParameter("city");
	        String PinCode=request.getParameter("pinCode");	
	        String MobNumber=request.getParameter("MobNo");
	        String service="DthConnection";
	        String ipaddress=(String)request.getParameter("ipaddress");
	        	         
				        
	        String Price=daoObj.getPrice(Location,Product,Description);
	        	
	       	
	        HashMap<String,String> serviceStatus=daoObj.getStatusDthConnection(agentID,Price,Location,Product,Description);
	        String Status=serviceStatus.get("status");
	       	String balamt=serviceStatus.get("balance");
       	
       	
	      
       	
	       	if (Status.equalsIgnoreCase("ASB")) {
	        	request.setAttribute("message","You Are Not Authorized To Use This Service. Please Contact Your Channel Partner.");
	            return "DthConnection";
				             
			 }else if (Status.equalsIgnoreCase("OSB")) {
	            request.setAttribute("message", "This Operator is Not Available. Please Try Later.");
	            return "DthConnection";
				
		     }else if(Status.equalsIgnoreCase("SSB")){
				request.setAttribute("message","Transaction Failed Due to Technical Error. Please Try Later.");
		    	return "DthConnection";
				            	
			 }else if(Status.equalsIgnoreCase("ISB")){
				request.setAttribute("message","You have Insufficient Balance.");
		       	return "DthConnection";
				            	
				            	
			 }else if(Status.equalsIgnoreCase("Y")){
				
				
				 HashMap<String,String> KitDetails=daoObj.getKitDetails(Location,Product,Description);
			    
				 String netKitValue = (String) KitDetails.get("netkitvalue");
				 String netKitActivation = (String) KitDetails.get("netactivationvalue");
				 String netkitcustomerValue = (String) KitDetails.get("netkitCustvalue");
				 String netkitactivationcustomerValue = (String) KitDetails.get("netActivationCustvalue");
				
				 double BalAmt=(Double.parseDouble(balamt));
				 double NetKitValue = (Double.parseDouble(netKitValue));
				 double NetKitActivation = (Double.parseDouble(netKitActivation));		
				 double Margin=((NetKitActivation)*(3.0/100.0));
				 double netAmount=NetKitActivation-(Margin);
				 double totaldthamt=NetKitValue+NetKitActivation;
				 double DeductAmt=BalAmt-(totaldthamt);
		        
				 double NetKitCustomerValue = (Double.parseDouble(netkitcustomerValue));
				 double NetKitActivationCustomerValue = (Double.parseDouble(netkitactivationcustomerValue));		
				 double totalDthCustamt=NetKitCustomerValue+NetKitActivationCustomerValue;
		        
				 String transactionId1=daoObj.createTransactionID();
				 String IdNo1 = GenerateIdDao.getIdNo();
				 String IdNo2 = GenerateIdDao.getIdNo();
				 String Status1="";
					       
			
				 if((totaldthamt+DeductAmt)==BalAmt){
					  
					 Location=request.getParameter("district"); 
					 Status1=daoObj.insertDetails(agentID,Location,Product,Description,NetKitValue,NetKitActivation,Margin,Name,Address,State,City,PinCode,MobNumber,service,IdNo1,ipaddress,transactionId1,IdNo2,IdNo1,NetKitCustomerValue,NetKitActivationCustomerValue);
			
									
					 if(Status1.equals("notinserted")){
					        	
					  	request.setAttribute("message","Trasaction Failed due to Technical Problem.");
						return "DthConnection";
					        	
					 }else {
						  
						  HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
						  String agentCellMail=dynamicData.get("helpEmailID");
						  
						  BackButtonDao backButtonDao=new BackButtonDao();
						  
						  String balance=backButtonDao.getAgentBalance(agentID);
						  session.setAttribute("AgentBalance", balance);
       					  
						                 
		                 String recipients[]= {"support@smartkinda.com"};
		    		     String subject = "DTH Connection Sale " + Product
		           						+ "";

		           			
		           		 String Message = "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
									+ "<head>"
									+ "<meta http-equiv='Content-Type' content='text/html'; charset='utf-8' />"
									+ "<title>E - Mailer</title>"
									+ "<style type='text/css' media='all'>"
							
									+ "body{ margin:8px; padding:8px;}"
							
									+ "table{border-collapse:collapse}"
									+ ".col1,.col5,.col9{background:white}"
									+ ".col2,.col6,.col10{background:white}"
									+ ".col3,.col7{background:white}"
									+ ".col4,.col8{background:white}"
									+ "td,th{border:1px solid #000;}"
							
									+ ".txt1{font-family:'Trebuchet MS';font-size:12px;color:#000000;text-decoration:none;}"
									+ ".txt2{font-family:'Trebuchet MS'; font-size:12px; color:#000000; text-decoration:none; font-weight:bold}"
									+ ".txt3{font-family:'Trebuchet MS'; font-size:13px; color:#000000; text-decoration:none; font-weight:bold}"
									+ ".txt4{font-family:'Trebuchet MS'; font-size:11px; color:#000000; text-decoration:none; }"
							
							
									+ "</style>"
							
							
							
									+ "</head>"
							
							
									+ "<body>"
									+ "<table width='400' border='0' align='left' cellpadding='0' cellspacing='0'>"
									+ "<tr>"
									+ "<td colspan='3' align='center' valign='middle' class='txt3' style='padding:3px;'>DTH Connection Sale</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Txn. Date</td>"
									+ "<td align='center' valign='middle' style='width:20px;'>:</td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+today+" "+intTime+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Customer Name</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+Name+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Customer Mobile</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+MobNumber+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Address</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+Address+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>State</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+State+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>City</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+City+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Pin Code</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+PinCode+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Transactions ID</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+IdNo1+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Agent Id</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+agentID+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Product</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+Product+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Product Description</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+Description+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Total MRP</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+totalDthCustamt+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>Voucher MRP </td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+NetKitCustomerValue+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt3' style='padding-left:8px; width:200px;'>Kit Activation </td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt3' style='padding-right:8px;'>"+NetKitActivationCustomerValue+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td colspan='3' align='center' valign='middle' class='txt3' style=' padding:3px;'>Debited from Agent Account</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>DTH Connection Sale</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+NetKitValue+"</td>"
									+ "</tr>"
									+ "<tr>"
									+ "<td align='left' valign='middle' class='txt1' style='padding-left:8px; width:200px;'>DTH Recharge – Kit Activation</td>"
									+ "<td align='center' valign='middle'><span style='width:20px;'>:</span></td>"
									+ "<td align='right' valign='middle' class='txt1' style='padding-right:8px;'>"+netAmount+"</td>"
									+ "</tr>"
									  
									+ "</table>"
							
									+ "</body>"
									+ "</html>";
		           		
		           		 try {
		           			 SendSmtpMail.sendSSLMessage(recipients, subject, Message,agentCellMail);
		           		 } catch (MessagingException e){
		           			 e.printStackTrace();
		           		 }
		           		 if (intTime > 2045000){
		           			 request.setAttribute("message","Request has been submitted and will be processed within next 72 Hours. You can take Acknowledgement Receipt from Account Statement.");
		           			 return "DthConnection";
		          
		           		 }else if (intTime < 830000){
		           			 request.setAttribute("message","Request has been submitted and will be processed within next 72 Hours. You can take Acknowledgement Receipt from Account Statement.");
		           			 return "DthConnection";
		           		 }
		           			 
		           		 request.setAttribute("message","Request has been submitted and will be processed within next 72 Hours. You can take Acknowledgement Receipt from Account Statement.");
		           		 return "DthConnection";
					 }
					 
				 }
							
			 }
			            
   		
       }else if(param.equalsIgnoreCase("productDescription")){
    			
	   			 
    	   
    	   String locationName=request.getParameter("type");
    	   String Product1=request.getParameter("id");
    	   	
    	   ArrayList productDescription=daoObj.getProductDescription(locationName,Product1);
	
    		    
    	   StringBuffer proddescription=new StringBuffer();
    	   String productdesc="";
    	   for(int i=0;i<productDescription.size();i++                                                                                                                                                                                                                                                                                                             )
    	   {
                
    		   productdesc=productDescription.get(i).toString();
    		   
    		   proddescription.append("<option value=\""+productdesc.trim()+"\">"+productdesc.trim()+"</option>");          
    	   }
    			
    	   PrintWriter out=response.getWriter(); 
    	   out.print(proddescription);
    	   session.setAttribute("productdesc",productdesc);
    		
    		 
       }else if(param.equalsIgnoreCase("productPrice")){
    				

    	   logger.info("we are into productPrize");
    	   String locationName=request.getParameter("type");
    	   String Product1=request.getParameter("id");
    	   String ProductDesc=request.getParameter("desc");
	       
    	   ArrayList productPrice=daoObj.getProductPrice(locationName,Product1,ProductDesc);
	
          
    	   StringBuffer prodpricevalue=new StringBuffer();
    				
    	   for(int i=0;i<productPrice.size();i++)
    	   {

    		   String productprice=productPrice.get(i).toString();
    		   
    		   prodpricevalue.append(productprice);
    	   }
    	
    	   logger.info("DthConnectionAction.execute() "+prodpricevalue.toString());
    	   PrintWriter out=response.getWriter();
    	   out.print(prodpricevalue.toString());
    				
       }   

  
		}
				
		catch (Exception e) {
			logger.info("Exception in DthConnectionAction class");
			e.printStackTrace();
			return "ERROR";	
		}
		return null;
		}
}     	
			            	
