package com.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import com.common.CommonDetailDao;
import com.common.LogWriter;
import com.common.SendSmsOnMobile;
import com.common.SendSmtpMail;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class MarketingUserLogin extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware  {

	Logger logger = Logger.getLogger(LoginAction.class);
	LogWriter log=new LogWriter();

	private HttpServletRequest request; 
	private  LoginForm Loginform=new LoginForm();
	public Object getModel(){return Loginform;}


	@SuppressWarnings("unchecked")
	public String execute() throws Exception { 
		try 
		{
			Map session = ActionContext.getContext().getSession();
			LoginDao Logindao=new LoginDao();
			String param=Loginform.getParam();

			log.print("param value in LoginAction is "+param, logger);
			if(param.equals("ForgetPassword"))
			{
				return "ForgetPassword";
			}
			if(param.equalsIgnoreCase("OtherUserlogin")){
				String Client_Id="";
				String userid="";
				String password="";
				String userType="";
				CommonDetailDao obj=new CommonDetailDao();
				String FlagType=request.getParameter("flag");
				System.out.println("FLag Is ::"+FlagType);

				if (FlagType.equalsIgnoreCase("REP")){

					Client_Id="10003";			  
					userid ="demorechargeepoint@gmail.com";
					session.put("DistributorMailId",userid);
					password="DEMO@123rep";
					session.put("password",password);
					userType="distributor";
					System.out.println(userid+ "......"+password+"........."+userType +"REP" +Client_Id);		 
					session.put("userType",userType);

					String domainName="www.rechargeepoint.com";

					HashMap info=obj.getDynamicDetails(domainName);

					//  session.put("info",info);		
					session.put("DSHeaderImage", info.get("distributor_header_home_image"));
					session.put("TickerMessage", info.get("TickerMessage"));
					session.put("Powered_By", info.get("Powered_By"));
					session.put("Client_Id",Client_Id);

				}	
				else{
					Client_Id="10001";		  
					userid="demotravelepoint@gmail.com";
					session.put("DistributorMailId",userid);
					password="DEMO@123tep";
					session.put("password",password);
					userType  ="D";
					System.out.println(userid+ "......"+password+"........."+userType+"TEP");	 
					session.put("userType",userType);
					session.put("Client_Id",Client_Id);
					String domainName="www.travelepoint.com";
					HashMap info=obj.getDynamicDetails(domainName);
					session.put("DSHeaderImage", info.get("distributor_header_home_image"));
					session.put("TickerMessage", info.get("TickerMessage"));
					session.put("Powered_By", info.get("Powered_By"));
					session.put("Client_Id",Client_Id);
				}
				String userStatus=(String)Logindao.checkUserLoginDetails(userid,password,userType,session);
				log.print("userType>>>>>>>>>>>>>>>>>"+userType, logger);

				if(userStatus.equalsIgnoreCase("invalid")){
					String message="Invalid User ID and/or Password. Please Try Again.";

					request.setAttribute("Loginmessage",message);
					return "invaliduser";
				}
				if(userStatus.equalsIgnoreCase("Deactivate")){
					String message="Your Account is Not Activated.";

					request.setAttribute("Loginmessage",message);
					return "invaliduser";
				}
				if(userStatus.equalsIgnoreCase("Deactive")){

					String message="Your Account is Not Activated.";
					request.setAttribute("Loginmessage",message);
					return "Deactiveuser";

				}
				if(userStatus.equalsIgnoreCase("Activate")){

					HashMap DisDetailsmapInfo=(HashMap)Logindao.checkUserProfileStatus(userid,password,userType);
					String profileStatus=(String)DisDetailsmapInfo.get("Status");

					if(profileStatus.equalsIgnoreCase("password")|| profileStatus.equalsIgnoreCase("email")){

						session.put("odpass",DisDetailsmapInfo.get("odpass"));
						session.put("distributorId",DisDetailsmapInfo.get("distributorId"));
						session.put("distributorInitial",DisDetailsmapInfo.get("distributorInitial"));
						session.put("companyName",DisDetailsmapInfo.get("companyName"));
						session.put("clientId",DisDetailsmapInfo.get("clientId"));
						session.put("loginUrl",DisDetailsmapInfo.get("loginUrl"));
						session.put("innerHeaderImage",DisDetailsmapInfo.get("innerHeaderImage"));
						session.put("helpEmailId",DisDetailsmapInfo.get("helpEmailId"));
						session.put("helpMobileNo",DisDetailsmapInfo.get("helpMobileNo"));
						session.put("titlePage",DisDetailsmapInfo.get("titlePage"));
						session.put("copyRight",DisDetailsmapInfo.get("copyRight"));
						session.put("poweredBy",DisDetailsmapInfo.get("poweredBy"));
						session.put("panelType",DisDetailsmapInfo.get("panelType"));
						session.put("totalCash",DisDetailsmapInfo.get("totalCash"));
						session.put("usedCash",DisDetailsmapInfo.get("usedCash"));
						session.put("cutoffAamount",DisDetailsmapInfo.get("cutoffAamount"));
						session.put("domainName",DisDetailsmapInfo.get("domainName"));
						session.put("mdID",DisDetailsmapInfo.get("mdId"));
						return "email";
					}
					if(profileStatus.equalsIgnoreCase("mobile")){
						String userId=(String)DisDetailsmapInfo.get("distributorId");
						HashMap mapInfo=(HashMap)Logindao.getVerifyMobilrcode(userId);

						String mobilecode=(String)mapInfo.get("mobileCode");
						String mobilenumber=(String)mapInfo.get("mobilenumber");
						SendSmsOnMobile SendSmsOnMobile=new SendSmsOnMobile();
						String message="Dear User,Your mobile authentication code for distributor panel is"+mobilecode;
						log.print(message,logger);
						//SendSmsOnMobile.sendMobileSmsSMSZONE(mobilenumber,message);
						request.setAttribute("mobilecodemessage", "For Mobile Verification we have Send a Code at your Registered Number. Enter that Code to Verify Your Mobile Number After that your Account will be Activate.");
						session.put("distributorId",userId);
						return "verifyMobileCode";
					}

					if(profileStatus.equalsIgnoreCase("profile")){

						return "profile";
					}

					if(profileStatus.equalsIgnoreCase("login")){
						session.put("odpass",DisDetailsmapInfo.get("odpass"));
						session.put("distributorId",DisDetailsmapInfo.get("distributorId"));
						session.put("distributorInitial",DisDetailsmapInfo.get("distributorInitial"));
						session.put("companyName",DisDetailsmapInfo.get("companyName"));
						session.put("clientId",DisDetailsmapInfo.get("clientId"));
						session.put("loginUrl",DisDetailsmapInfo.get("loginUrl"));
						session.put("innerHeaderImage",DisDetailsmapInfo.get("innerHeaderImage"));
						session.put("helpEmailId",DisDetailsmapInfo.get("helpEmailId"));
						session.put("helpMobileNo",DisDetailsmapInfo.get("helpMobileNo"));
						session.put("titlePage",DisDetailsmapInfo.get("titlePage"));
						session.put("copyRight",DisDetailsmapInfo.get("copyRight"));
						session.put("poweredBy",DisDetailsmapInfo.get("poweredBy"));
						session.put("panelType",DisDetailsmapInfo.get("panelType"));
						session.put("totalCash",DisDetailsmapInfo.get("totalCash"));
						session.put("usedCash",DisDetailsmapInfo.get("usedCash"));
						session.put("cutoffAamount",DisDetailsmapInfo.get("cutoffAamount"));
						session.put("domainName",DisDetailsmapInfo.get("domainName"));
						session.put("mdID",DisDetailsmapInfo.get("mdId"));
						session.put("BalanceDesk",DisDetailsmapInfo.get("BalanceDesk"));
						session.put("WhitelableCompanyName",DisDetailsmapInfo.get("WhitelableCompanyName"));
						session.put("DistributorName",DisDetailsmapInfo.get("DistributorName"));
						session.put("MobileNo",DisDetailsmapInfo.get("MobileNo"));
						session.put("agentLoginUrl",DisDetailsmapInfo.get("agentLoginUrl"));
						session.put("agentCellEmailId",DisDetailsmapInfo.get("agentCellEmailId"));

						return "login";
					}
				}
				if(userStatus.equalsIgnoreCase("Blocked")){
					String message="You are not Allowed to Login. Your Account is Blocked.";

					request.setAttribute("Loginmessage",message);
					return "error";

				}
				if(userStatus.equalsIgnoreCase("blockedUpperHirerarchy")){
					String message="Login Disallowed. Please Contact Your Channel Partner.";

					request.setAttribute("Loginmessage",message);
					return "error";

				}
			}


		}
		catch(Exception ex){
			ex.printStackTrace();
			request.setAttribute("Loginmessage", "Due to some technical issue we are unable to proceed your request");
			return "error";
		}
		return "error";

	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}


	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub

	}


}
