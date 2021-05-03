package com.tradebalanceactivity.self;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.ConvertUtility;
import com.common.SendSmtpMail;
import com.formBean.EGEN.ApiClientJournalFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *Created Date=5/App/2012,
 *
 * Created BY=Vibhor Kumar, 
 * 
 * Purpose=Action Class,
 * 
 * Save Deposit Information of Portal User ,
 * 
 * Updated Date="", Updated By=""
 */
public class MyDepositAction extends ActionSupport implements ModelDriven,
ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;

	private TradeBlanceDepositFormBean FormBean = new TradeBlanceDepositFormBean();

	public Object getModel() {
		return FormBean;
	}



	ArrayList myAccount = null;

	/**	 Method provide the deposit details for confirmation	*/
	public String MyDepositInfo()
	{
		String result = "err";
		TradeBalanceFormBean TBBean = new TradeBalanceFormBean();
		String depositMode = request.getParameter("depositMode");


		Map session = ActionContext.getContext().getSession();
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}

			FormBean.setUserid(Integer.parseInt(userId));

			TBBean = ConvertUtility.convertTBDFormBeanToTBFormBean(FormBean);

			if ( depositMode.equals("Cash in Bank")) {
				File recieptFile = FormBean.getRecieptFile();
				String fileName = FormBean.getRecieptFileFileName();
				System.out.println("file name is..." + fileName);
				String addFilePath = request.getRealPath("/")
						+ "documents/deposit/recipet";

				File addFile = new File(addFilePath, fileName);
				FileUtils.copyFile(recieptFile, addFile);
				String fileLocation = addFilePath + "/" + fileName;
				TBBean.setRecieptFileLocation(fileLocation);
			}

			TBBean.setTransactionId(ConvertUtility.transactionId());
			TBBean.setDepositDate(ConvertUtility
					.convertStringToDate((String) request
							.getParameter("Paymentdate")));
			if (depositMode.equals("Cheque/DD"))
			{
				if(null!=request.getParameter("Chequedate"));
				{
					TBBean.setChequedate(ConvertUtility.convertStringToDate((String)request.getParameter("Chequedate")));
				}
			}			


			TBBean.setAmountToCredit(Double.parseDouble(request
					.getParameter("depositAmount")));
			TBBean.setModeofPayment(depositMode);			
			TBBean.setStatus("Pending");
			TBBean.setDepositTime(FormBean.getDepositTime());

			String paymentMode=	TBBean.getModeofPayment();	
			String bankName="";
			if(paymentMode.equals("Cash in Bank"))
			{
				bankName=TBBean.getRecievingBankName();
			}
			if(paymentMode.equals("Cheque/DD"))
			{
				bankName=TBBean.getRecievingBankName();
			}
			if(paymentMode.equals("Online E-Transfer")||paymentMode.equals("NEFT/RTGS"))
			{
				bankName=TBBean.getRecievingBankName();
			}
			double charge = TradeBalanceSelfDAO.getBankCharges(paymentMode,TBBean.getAmountToCredit(),bankName);

			TBBean.setBankcharges(charge);
			TBBean.setAcceptedAmount(TBBean.getAmountToCredit()-charge);


			session.put("DepositInfo", TBBean);
			/*result = TradeBalanceSelfDAO.MyDeposit(TBBean);
			String message = "Request Successful.";
			request.setAttribute("myDepositMessage", message);*/

			return "success";

		} catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return "err";
		}

	}

	/**	 Method for save deposit details or edit deposit details	 */
	public String myDepositSaveOrEdit()
	{
		String param=request.getParameter("param");
		Map session = ActionContext.getContext().getSession();
		String result="err";
		try {
			String userId = (String) session.get("userId");
			if (userId == null) {
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
			if(param.equals("saveDeposit"))
			{
				String loginType=(String)session.get("loginType");
				TradeBalanceFormBean bean=(TradeBalanceFormBean)session.get("DepositInfo");
				ApiClientJournalFormBean ApiBean=new ApiClientJournalFormBean();			
				bean.setLoginType(loginType);
				bean.setPortalId(Integer.parseInt((String)session.get("adminUserPortalId")));
				bean.setRequestDate(new Date());
				bean.setRequestTime(new Date());
				result = TradeBalanceSelfDAO.MyDepositSave(bean);			
				session.remove("DepositInfo");

				if(!loginType.equalsIgnoreCase("superadmin"))
				{
					String emailFrom = "noreply@eservices.net.in";
					String recipients[] = {
							"update@epoint.net.in"
					};
					String bccrecipients[] = {
							"manoj.k@commsoft.in"
					};
					String BankName = bean.getRecievingBankName();
					if(BankName == null)
						BankName = BankName;
					String BankAcctNo = bean.getRecievingBankAccNo();
					if(BankAcctNo == null)
						BankAcctNo = BankAcctNo;
					String subject = "Super Admin Fund Request - Mr. Rishi Jangra  (expressrecharge.net)";
					String Message = (new StringBuilder((new StringBuilder("<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td >Dear &nbsp;Update,</td></tr><br><tr><td><p> Please get the below Super Admin fund transfer detail & take action accordingly. </p> </td></tr><br><p><tr><td><table border=\"1\" ><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Client Name</td><td> &nbsp;<font color=\"black\" >Mr. Rishi Jangra (ExpressRecharge) </font><br></td></tr><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Portal ID </td><td>")).append(bean.getPortalId()).append("</td></tr><tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Payment Mode&nbsp; </td><td><font color=\"black\" >").toString())).append(bean.getModeofPayment()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cheque DD No.&nbsp;</td><td><font color=\"black\" >").append(bean.getChequeDDNo()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Transfer Amount&nbsp;</td><td><font color=\"black\" >").append(bean.getAmountToCredit()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Transfer Transaction ID&nbsp;</td><td><font color=\"black\" >").append(bean.getBankTranId()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receipt No.&nbsp;</td><td><font color=\"black\" >").append(bean.getRecieptNo()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Transfer Transaction ID&nbsp;</td><td><font color=\"black\" >").append(bean.getBankTranId()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiving Bank Name&nbsp;</td><td><font color=\"black\" >").append(bean.getRecievingBankName()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Receiving Bank A/C Number&nbsp;</td><td><font color=\"black\" >").append(bean.getRecievingBankAccNo()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Remark &nbsp;</td><td><font color=\"black\" >").append(bean.getRemark()).append("<br></td></tr>").append("<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Payment Date &nbsp;</td><td><font color=\"black\" >").append(bean.getDepositDate()).append(bean.getDepositTime()).append("<br></td></tr>").append("</table></td></tr></p><br>").append("<tr><td ><p>Regards</p></td></tr>").append("<tr><td><p>Support Desk</p></td></tr>").append("<tr><td><p>support@commsoft.in</p><br></td></tr></table></td></tr>").append("</tr></td></table></BODY></html>").toString();
					System.out.println("before sending mail ");
					SendSmtpMail addmaail = new SendSmtpMail();
					//String mailStatus = SendSmtpMail.sendSSLMessageBCC(recipients, bccrecipients, subject, Message, emailFrom);
					//System.out.println((new StringBuilder("mailStatus-----")).append(mailStatus).toString());
				}


				request.setAttribute("message", "Process has been completed Successfully.");
				return result;
			}
			else{
				result="edit";
				return result;	
			}

		} catch (Exception e) {
			request.setAttribute("message", "Process aborted due to Technical Failure.");
			System.out.println(e.toString());
			return result;
		}

	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}




}
