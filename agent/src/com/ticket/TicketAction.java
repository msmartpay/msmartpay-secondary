package com.ticket;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.TickerMessage.TickerMessageDao;
import com.common.GenerateIdDao;
import com.opensymphony.xwork2.ActionSupport;
import com.report.accountStmt.AccountStatementDao;
import com.zip.ZipCreator;

public class TicketAction extends ActionSupport{

	Logger logger=Logger.getLogger(TicketAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public String execute()
			throws Exception {

		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		HttpSession session=request.getSession(false);

		String agentID="";
		try
		{

			if(session.getAttribute("agentID")==null){
				request.setAttribute("message", "Your Login Session has Expired. Please Login Again.");
				return "sessionExp";
			}
			agentID=(String)session.getAttribute("agentID");
			String param=request.getParameter("param");
			logger.info("TEP ,Class is AccountStatementAction ,Param is "+param);

			if(param.equalsIgnoreCase("ticketPage")){
				String txnId=request.getParameter("txnId");
				if(txnId!=null)
					txnId.trim();
				
				TicketDao daoObj=new TicketDao();
				if(daoObj.checkTicketExistance(Long.parseLong(agentID), txnId)){
					request.setAttribute("txnId",txnId);
					
					ArrayList<HashMap<String,String>> ticketData=daoObj.getTicketDetails(Long.parseLong(agentID));
					int size=ticketData.size();
					logger.info("Size of Data is : "+size);
					if(ticketData!=null && ticketData.size()>0){
						request.setAttribute("ticketData", ticketData);

					}else{
						request.setAttribute("message", "There is No Ticket Available.");
					}
				}else{
					request.setAttribute("message", "Ticket already exist for this transaction Id. Please view ticket Status.");
					AccountStatementDao dao=new AccountStatementDao();
					ArrayList<HashMap<String,String>> listData=dao.getAccountStmtOfAgentOnClick(agentID);
					if(listData!=null && listData.size()>0)
						request.setAttribute("AccountStmtData", listData);
					return "AccounStmtPage";
				}
				return "ticketPage";
			}else if(param.equalsIgnoreCase("ticketPageByDate")){
				String fromDate=request.getParameter("fromDate");
				String toDate=request.getParameter("toDate");
				TicketDao daoObj=new TicketDao();
				ArrayList<HashMap<String,String>> ticketData=daoObj.getTicketDetails(Long.parseLong(agentID),fromDate,toDate);
				int size=ticketData.size();
				logger.info("Size of Data is : "+size);
				if(ticketData!=null && ticketData.size()>0){
					request.setAttribute("ticketData", ticketData);

				}else{
					request.setAttribute("message", "There is No Ticket Available.");
				}
				return "ticketPage";
			}
			else if(param.equalsIgnoreCase("submitTicket")){
				try {
					String txnId=request.getParameter("txnId");
					String ticketmessage=request.getParameter("ticketmessage");
					TicketDao daoObj=new TicketDao();

					if(daoObj.checkTicketExistance(Long.parseLong(agentID), txnId)){
						String ticketid=GenerateIdDao.getTicketId();

						HashMap<String,String> temp=daoObj.getAccountWIndowInfoForRecharge(txnId);
						if(temp!=null && !temp.isEmpty()){
							boolean status=daoObj.submitTicket(ticketid,txnId,Long.parseLong(agentID),"AG",ticketmessage);
							if(status){
								request.setAttribute("message","Your Ticket <label  style=\"color: BLACK;font-weight: bold;\">"+ticketid+"</label> Has Been Submitted Successfully and Will Be Resolved Within 24 hours.");
							}else{
								request.setAttribute("message","Ticket Not Submitted. Please Try Again.");
							}

							ArrayList<HashMap<String,String>> ticketData=daoObj.getTicketDetails(Long.parseLong(agentID));
							int size=ticketData.size();
							logger.info("Size of Data is : "+size);
							if(ticketData!=null && ticketData.size()>0){
								request.setAttribute("ticketData", ticketData);

							}else{
								request.setAttribute("message", "There is No Ticket Available.");
							}
						}else{
							request.setAttribute("message", "There is No Transaction Available with this transaction No.");
						}
					}else{
						request.setAttribute("message", "Ticket already exist for this transaction Id. Please view ticket Status.");

					}



					return "ticketPage";

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (Exception ex) {
			logger.info("TEP :: Exception in AccountStatementAction class ");
			ex.printStackTrace();
		}
		return "ERROR";
	}
}