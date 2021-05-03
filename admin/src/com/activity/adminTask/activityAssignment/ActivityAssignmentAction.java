package com.activity.adminTask.activityAssignment;

/**
 * 	Updated By : Manoj
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.login.LoginDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ActivityAssignmentAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	   
	public String execute() throws Exception{ 
        	
		Map session=ActionContext.getContext().getSession();
		try{
			
			String userId=(String)session.get("userId");
			if(userId==null){
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
	    	   
			String param=request.getParameter("param");
			System.out.println("ActivityAssignmentAction and Param is "+param);
			if(param.equals("assignActivitySearch"))
			{
				return "searchPortalUser";
			}
			
			else if(param.equals("assignActivity")){

				String userType=(String)session.get("adminUserType");
				String portalUserId=request.getParameter("portalUserId");
				if(portalUserId.equals(userId))
				{
					request.setAttribute("message","Please Check Input Value.");
					return "searchPortalUser";
				}
				LoginDao daoObj=new LoginDao();
				ActivityAssignmentDao activityObj=new ActivityAssignmentDao();
				request.setAttribute("activityPortalUserId",portalUserId);
				String portalUserIdStatus="";
				if(!userType.equalsIgnoreCase("superadmin"))
				{
					portalUserIdStatus= activityObj.getPortalUserIdStatus(portalUserId,userId);
					if(portalUserIdStatus.equals("valid"))
					{
                    	
                    	HashMap serviceMap=daoObj.getUserServiceAuthenticationDetails(portalUserId);
                    	request.setAttribute("portalUserServiceMap",serviceMap);
                    	return "activityAssignmentPage";
					} 
					else
					{
                    	request.setAttribute("message","Please Check Input Value.");
                    	return "searchPortalUser";
                    }
				}
				else
				{
					portalUserIdStatus= activityObj.getPortalUserIdStatus(portalUserId);
					if(portalUserIdStatus.equals("valid")){
						HashMap serviceMap=daoObj.getUserServiceAuthenticationDetails(portalUserId);
						request.setAttribute("portalUserServiceMap",serviceMap);
						return "activityAssignmentPage";
					}else{
						request.setAttribute("message","Please Check Input Value.");
						return "searchPortalUser";
					}
				}
			}
			else if(param.equals("updateActivity")){

				String portalUserId=request.getParameter("activityPortalUserId");
				request.setAttribute("activityPortalUserId",portalUserId);
                  
				String userReg=request.getParameter("userReg");
				if(userReg==null){userReg="N";}else if(userReg.equals("on")){userReg="Y";}
				  
				  
				String internalUserReg=request.getParameter("internalUserReg");
				if(internalUserReg==null){internalUserReg="N";} else if(internalUserReg.equals("on")){internalUserReg="Y";} 
				  
				String corporateUserReg=request.getParameter("corporateUserReg");
				if(corporateUserReg==null){corporateUserReg="N";}else if(corporateUserReg.equals("on")){corporateUserReg="Y";}
				  
				String retailUserReg=request.getParameter("retailUserReg");
				if(retailUserReg==null){retailUserReg="N";}else if(retailUserReg.equals("on")){retailUserReg="Y";}
				  
				String mdsReg=request.getParameter("mdsReg");
				if(mdsReg==null){mdsReg="N";}else if(mdsReg.equals("on")){mdsReg="Y";} 
				  
		// Admin Task
				String adminTask=request.getParameter("adminTask");
				if(adminTask==null){adminTask="N";}else if(adminTask.equals("on")){adminTask="Y";} 
				  
				String categoryAssign=request.getParameter("categoryAssign");
				if(categoryAssign==null){categoryAssign="N";}else if(categoryAssign.equals("on")){categoryAssign="Y";}
				  
				String dbTask=request.getParameter("dbTask");
				if(dbTask==null){dbTask="N";}else if(dbTask.equals("on")){dbTask="Y";} 
				
				String schedularRun=request.getParameter("schedularRun");
				if(schedularRun==null){schedularRun="N";}else if(schedularRun.equals("on")){schedularRun="Y";} 
				  
	// Trade Balance Activity
				String tradeBalanceActivity=request.getParameter("tradeBalanceActivity");
				if(tradeBalanceActivity==null){tradeBalanceActivity="N";}else if(tradeBalanceActivity.equals("on")){tradeBalanceActivity="Y";}
				  
				String tbFinanceAdmin=request.getParameter("tbFinanceAdmin");
				if(tbFinanceAdmin==null){tbFinanceAdmin="N";}else if(tbFinanceAdmin.equals("on")){tbFinanceAdmin="Y";}
				  
				String tbClientAdmin=request.getParameter("tbClientAdmin");
				if(tbClientAdmin==null){tbClientAdmin="N";}else if(tbClientAdmin.equals("on")){tbClientAdmin="Y";}
	
	// Profile Management
			
				String profileMgt=request.getParameter("profileMgt");
				if(profileMgt==null){profileMgt="N";}else if(profileMgt.equals("on")){profileMgt="Y";} 
				  
				String selfProfile=request.getParameter("selfProfile");
				if(selfProfile==null){selfProfile="N";}else if(selfProfile.equals("on")){selfProfile="Y";} 
				
				
				String internal_profile_mgt=request.getParameter("internal_profile_mgt");
				if(internal_profile_mgt==null){internal_profile_mgt="N";}else if(internal_profile_mgt.equals("on")){internal_profile_mgt="Y";} 
				
				String corporate_profile_mgt=request.getParameter("corporate_profile_mgt");
				if(corporate_profile_mgt==null){corporate_profile_mgt="N";}else if(corporate_profile_mgt.equals("on")){corporate_profile_mgt="Y";} 
				
				String retail_profile_mgt=request.getParameter("retail_profile_mgt");
				if(retail_profile_mgt==null){retail_profile_mgt="N";}else if(retail_profile_mgt.equals("on")){retail_profile_mgt="Y";} 
				  
	// Transaction Report
				String transactionReport=request.getParameter("transactionReport");
				if(transactionReport==null){transactionReport="N";}else if(transactionReport.equals("on")){transactionReport="Y";} 
				  
				String tranChannelreport=request.getParameter("tranChannelreport");
				if(tranChannelreport==null){tranChannelreport="N";}else if(tranChannelreport.equals("on")){tranChannelreport="Y";} 
				  
				String tranEgenreport=request.getParameter("tranEgenreport");
				if(tranEgenreport==null){tranEgenreport="N";}else if(tranEgenreport.equals("on")){tranEgenreport="Y";} 
				  
	//Management Report
				String managementReport=request.getParameter("managementReport");
				if(managementReport==null){managementReport="N";}else if(managementReport.equals("on")){managementReport="Y";} 
				  
				String mgtChannelReport=request.getParameter("mgtChannelReport");
				if(mgtChannelReport==null){mgtChannelReport="N";}else if(mgtChannelReport.equals("on")){mgtChannelReport="Y";} 
				  
				String mgtEgenReport=request.getParameter("mgtEgenReport");
				if(mgtEgenReport==null){mgtEgenReport="N";}else if(mgtEgenReport.equals("on")){mgtEgenReport="Y";} 
				  
	// Finance Report
				String financeReport=request.getParameter("financeReport");
				if(financeReport==null){financeReport="N";}else if(financeReport.equals("on")){financeReport="Y";} 
				  
				String finChannelReport=request.getParameter("finChannelReport");
				if(finChannelReport==null){finChannelReport="N";}else if(finChannelReport.equals("on")){finChannelReport="Y";} 
				  
				String finEgenReport=request.getParameter("finEgenReport");
				if(finEgenReport==null){finEgenReport="N";}else if(finEgenReport.equals("on")){finEgenReport="Y";} 
				  
	// Statement
				String statement=request.getParameter("statement");
				if(statement==null){statement="N";}else if(statement.equals("on")){statement="Y";} 
				  
				  
				String channelStatement=request.getParameter("channelStatement");
				if(channelStatement==null){channelStatement="N";}else if(channelStatement.equals("on")){channelStatement="Y";} 
				  
				String egenStatement=request.getParameter("egenStatement");
				if(egenStatement==null){egenStatement="N";}else if(egenStatement.equals("on")){egenStatement="Y";} 
				  
	// CC Module
				String ccModule=request.getParameter("ccModule");
				if(ccModule==null){ccModule="N";}else if(ccModule.equals("on")){ccModule="Y";} 
				  
				String transactionStatus=request.getParameter("transactionStatus");
				if(transactionStatus==null){transactionStatus="N";}else if(transactionStatus.equals("on")){transactionStatus="Y";} 
				  
				String notice=request.getParameter("notice");
				if(notice==null){notice="N";}else if(notice.equals("on")){notice="Y";} 
				  
				String ccInfo=request.getParameter("ccInfo");
				if(ccInfo==null){ccInfo="N";}else if(ccInfo.equals("on")){ccInfo="Y";}
	// Client Report
				String clientReport=request.getParameter("clientReport");
				if(clientReport==null){clientReport="N";}else if(clientReport.equals("on")){clientReport="Y";} 
				  
				String clientTranReport=request.getParameter("clientTranReport");
				if(clientTranReport==null){clientTranReport="N";}else if(clientTranReport.equals("on")){clientTranReport="Y";} 
				 
				String CCuserSearch=request.getParameter("CCuserSearch");
				if(CCuserSearch==null){CCuserSearch="N";}else if(CCuserSearch.equals("on")){CCuserSearch="Y";} 
				
	// Control panel -1
	
		//Cutoff
				String cutOffMangt=request.getParameter("cutOffMangt");
				if(cutOffMangt==null){cutOffMangt="N";}else if(cutOffMangt.equals("on")){cutOffMangt="Y";} 
				  
				String cutOffSetup=request.getParameter("cutOffSetup");
				if(cutOffSetup==null){cutOffSetup="N";}else if(cutOffSetup.equals("on")){cutOffSetup="Y";} 
				  
				  
				String cutOffView=request.getParameter("cutOffView");
				if(cutOffView==null){cutOffView="N";} else if(cutOffView.equals("on")){cutOffView="Y";} 
				  
		// Account management
				String accountMgt=request.getParameter("accountMgt");
				if(accountMgt==null){accountMgt="N";} else if(accountMgt.equals("on")){accountMgt="Y";} 
				  
				String accountmanagement=request.getParameter("accountmanagement");
				if(accountmanagement==null){accountmanagement="N";}else if(accountmanagement.equals("on")){accountmanagement="Y";} 
				  
				String commTdsMgt=request.getParameter("commTdsMgt");
				if(commTdsMgt==null){commTdsMgt="N";}else if(commTdsMgt.equals("on")){commTdsMgt="Y";} 
				  
				String refund=request.getParameter("refund");
				if(refund==null){refund="N";}else if(refund.equals("on")){refund="Y";} 
				
		// SKU Management
				
				String SKUmgt=request.getParameter("SKUmgt");
				if(SKUmgt==null){SKUmgt="N";}else if(SKUmgt.equals("on")){SKUmgt="Y";} 
				  
				String editSKU=request.getParameter("editSKU");
				if(editSKU==null){editSKU="N";}else if(editSKU.equals("on")){editSKU="Y";} 
				  
				String addSKU=request.getParameter("addSKU");
				if(addSKU==null){addSKU="N";}else if(addSKU.equals("on")){addSKU="Y";}
				
		//Ops Management		
				String opsMgt=request.getParameter("opsMgt");
				if(opsMgt==null){opsMgt="N";}else if(opsMgt.equals("on")){opsMgt="Y";} 
				  
				String serviceMgt=request.getParameter("serviceMgt");
				if(serviceMgt==null){serviceMgt="N";}else if(serviceMgt.equals("on")){serviceMgt="Y";} 
				
				String tepVendorMgt=request.getParameter("tepVendorMgt");
				if(tepVendorMgt==null){tepVendorMgt="N";}else if(tepVendorMgt.equals("on")){tepVendorMgt="Y";} 
				
				
		// Margin setup
				String marginSetup=request.getParameter("marginSetup");
				if(marginSetup==null){marginSetup="N";}else if(marginSetup.equals("on")){marginSetup="Y";} 
				  
				String marginWL=request.getParameter("marginWL");
				if(marginWL==null){marginWL="N";}else if(marginWL.equals("on")){marginWL="Y";} 
				  
				String marginAG=request.getParameter("marginAG");
				if(marginAG==null){marginAG="N";}else if(marginAG.equals("on")){marginAG="Y";} 
				  
				String marginEGEN=request.getParameter("marginEGEN");
				if(marginEGEN==null){marginEGEN="N";}else if(marginEGEN.equals("on")){marginEGEN="Y";} 
				
		// Charges Set
				
				String chargeSet=request.getParameter("chargeSet");
				if(chargeSet==null){chargeSet="N";}else if(chargeSet.equals("on")){chargeSet="Y";} 
				  
				String agentCharge=request.getParameter("agentCharge");
				if(agentCharge==null){agentCharge="N";}else if(agentCharge.equals("on")){agentCharge="Y";} 
				  
				String APIcharge=request.getParameter("APIcharge");
				if(APIcharge==null){APIcharge="N";}else if(APIcharge.equals("on")){APIcharge="Y";} 
		// Egen Control
				String egenControl=request.getParameter("egenControl");
				if(egenControl==null){egenControl="N";}else if(egenControl.equals("on")){egenControl="Y";} 
				  
				String operatorMgt=request.getParameter("operatorMgt");
				if(operatorMgt==null){operatorMgt="N";}else if(operatorMgt.equals("on")){operatorMgt="Y";} 
				  
				String clientServiceControl=request.getParameter("clientServiceControl");
				if(clientServiceControl==null){clientServiceControl="N";}else if(clientServiceControl.equals("on")){clientServiceControl="Y";} 
				  
				String vedorMgt=request.getParameter("vedorMgt");
				if(vedorMgt==null){vedorMgt="N";}else if(vedorMgt.equals("on")){vedorMgt="Y";} 
				  
				String clientOperatorControl=request.getParameter("clientOperatorControl");
				if(clientOperatorControl==null){clientOperatorControl="N";}else if(clientOperatorControl.equals("on")){clientOperatorControl="Y";} 
				  
				String clientCutOffView=request.getParameter("clientCutOffView");
				if(clientCutOffView==null){clientCutOffView="N";}else if(clientCutOffView.equals("on")){clientCutOffView="Y";} 
				  
				String egenRefund=request.getParameter("egenRefund");
				if(egenRefund==null){egenRefund="N";}else if(egenRefund.equals("on")){egenRefund="Y";} 
				  
				String egenTranStatus=request.getParameter("egenTranStatus");
				if(egenTranStatus==null){egenTranStatus="N";}else if(egenTranStatus.equals("on")){egenTranStatus="Y";} 
				
				
				String inventoryMgmt=request.getParameter("inventoryMgmt");
				if(inventoryMgmt==null){inventoryMgmt="N";}else if(inventoryMgmt.equals("on")){inventoryMgmt="Y";} 
				  
				String carBooking=request.getParameter("carBooking");
				if(carBooking==null){carBooking="N";}else if(carBooking.equals("on")){carBooking="Y";} 
				  
				String holidayPkg=request.getParameter("holidayPkg");
				if(holidayPkg==null){holidayPkg="N";}else if(holidayPkg.equals("on")){holidayPkg="Y";} 
				  
				String IMController=request.getParameter("IMController");
				if(IMController==null){IMController="N";}else if(IMController.equals("on")){IMController="Y";} 
				  
				String setMarkup=request.getParameter("setMarkup");
				if(setMarkup==null){setMarkup="N";}else if(setMarkup.equals("on")){setMarkup="Y";} 
				  
				String apiAssignment=request.getParameter("apiAssignment");
				if(apiAssignment==null){apiAssignment="N";}else if(apiAssignment.equals("on")){apiAssignment="Y";} 
				
				ActivityAssignmentDao daoObj=new ActivityAssignmentDao();
				
				String activityAssignStatus=daoObj.updateUserService(portalUserId,userReg,internalUserReg,corporateUserReg,retailUserReg,mdsReg,
						profileMgt,selfProfile,internal_profile_mgt,corporate_profile_mgt,retail_profile_mgt,
						adminTask,categoryAssign,dbTask,schedularRun,
						tradeBalanceActivity,tbFinanceAdmin,tbClientAdmin,
						transactionReport,tranChannelreport,tranEgenreport,managementReport,mgtChannelReport,mgtEgenReport,financeReport,finChannelReport,finEgenReport,statement,channelStatement,egenStatement,
						ccModule,transactionStatus,notice,ccInfo,clientReport,clientTranReport,
						cutOffMangt,cutOffSetup,cutOffView,
						accountMgt,accountmanagement,commTdsMgt,refund,
						egenControl,operatorMgt,clientServiceControl,vedorMgt,clientOperatorControl,clientCutOffView,egenRefund,egenTranStatus,
						marginSetup,marginWL,marginAG,marginEGEN,CCuserSearch,
						SKUmgt,editSKU,addSKU,opsMgt,serviceMgt,tepVendorMgt,
						chargeSet,agentCharge,APIcharge,inventoryMgmt,carBooking,holidayPkg,IMController,setMarkup,apiAssignment
					);
				if(activityAssignStatus.equalsIgnoreCase("valid"))
				{
					LoginDao LogindaoObj=new LoginDao();
					HashMap serviceMap=LogindaoObj.getUserServiceAuthenticationDetails(portalUserId);
					request.setAttribute("portalUserServiceMap",serviceMap);
					request.setAttribute("message","Process has been completed Successfully.");
					return "activityAssignmentPage";
					 
				}

				else{
					request.setAttribute("message","Process aborted due to Technical Failure.");
				}
				return "searchPortalUser";
			}
		}catch(Exception e){
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in ActivityAssignmentAction");
			return "searchPortalUser";
		}
		return "err";   
	}
	
	public void setServletRequest(HttpServletRequest request){
		this.request=request;
		
	}

	public void setSession(Map session){
		session = this.getSession();
	}

	public Map getSession(){
		return session;
	}
	
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
 			
	}
}
