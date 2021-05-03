package com.activity.adminTask.addBank;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.activity.pgtbrequest.PGTBRequestController;
import com.formBean.dynamicDetails.CollectionBankDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CollectionBankAction extends ActionSupport implements ModelDriven<CollectionBankDetailsFormBean> {

	private static Logger log = Logger.getLogger(PGTBRequestController.class);
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	
	private CollectionBankDetailsFormBean bankDetails=new CollectionBankDetailsFormBean(); 
	
	@Override
	public CollectionBankDetailsFormBean getModel() {
		// TODO Auto-generated method stub
		return bankDetails;
	}
	
	public String addbankpage(){
		
		session=ActionContext.getContext().getSession();
		request=ServletActionContext.getRequest();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			long clientId=Long.parseLong(session.get("clientId")+"");
			CollectionBankDao dao=new CollectionBankDao();
			List<CollectionBankDetailsFormBean> list=dao.getBankList(clientId);
			if(list!=null && list.size()>0)
				request.setAttribute("BankList",list);
			
			return "addBankPage";
		}catch(Exception e){
			log.info("Exception in PGTBRequestController");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			log.info(e.toString());
			return "err";
		}

	}
	
	public String addBank(){
		
		session=ActionContext.getContext().getSession();
		request=ServletActionContext.getRequest();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			long clientId=Long.parseLong(session.get("clientId")+"");
			bankDetails.setClientId(clientId);
			bankDetails.setStatus(1);
			CollectionBankDao dao=new CollectionBankDao();
			if(dao.addBank(bankDetails)) {
				request.setAttribute("message", "Bank Added Successfully");
			}else {
				request.setAttribute("message", "Unable to add bank.");
			}
			
			List<CollectionBankDetailsFormBean> list=dao.getBankList(clientId);
			if(list!=null && list.size()>0)
				request.setAttribute("BankList",list);
			
			return "addBank";
		}catch(Exception e){
			log.info("Exception in PGTBRequestController");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			log.info(e.toString());
			return "err";
		}

	}
	
	public String updateBankStatus(){
		
		session=ActionContext.getContext().getSession();
		request=ServletActionContext.getRequest();
		try{
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			long clientId=Long.parseLong(session.get("clientId")+"");
			CollectionBankDao dao=new CollectionBankDao();
			
			String loginType=(String)session.get("loginType");
			
			if(loginType.equalsIgnoreCase("superadmin")||loginType.equalsIgnoreCase("activityAdmin") || loginType.equalsIgnoreCase("PortalAdmin"))
			{
				
				String[] checkOperator=(String[])request.getParameterValues("checkpartial");
				
				if(checkOperator==null) {
					List<CollectionBankDetailsFormBean> list=dao.getBankList(clientId);
					if(list!=null && list.size()>0)
						request.setAttribute("BankList",list);
					request.setAttribute("message", "Please select atleast one row");
					return "updateBank";
				}
				int totpass=checkOperator.length;
				
				for(int i=0;i<totpass;i++)
				{
					String value=checkOperator[i];
					int slNo=Integer.parseInt(request.getParameter("slNo"+value));
					int status=Integer.parseInt(request.getParameter("status"+value));
					String bankName=request.getParameter("bankName"+value);
					String bankAccountHolder=request.getParameter("bankAccountHolder"+value);
					String bankAccount=request.getParameter("bankAccount"+value);
					String bankAccountIfsc=request.getParameter("bankAccountIfsc"+value);
					dao.updateBankStatus(slNo, status,bankName,bankAccountHolder,bankAccount,bankAccountIfsc);
				}
				//System.out.println("we are into 1 if");

				request.setAttribute("message", "Proceess has been completed.");

			}
			else
			{
				request.setAttribute("message","You are Not Authorized for this Service");
			}
			
			
			
			List<CollectionBankDetailsFormBean> list=dao.getBankList(clientId);
			if(list!=null && list.size()>0)
				request.setAttribute("BankList",list);
			return "updateBank";
		}catch(Exception e){
			log.info("Exception in PGTBRequestController");
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			log.info(e.toString());
			e.printStackTrace();
			return "err";
		}

	}
	
}
