/*
Class Property :  This class (CutOffAmountAction) is created to get request data for cutoff amount and process it accordingly.

 Created Date   : 7 feb-2012 at 11.47 AM.
 Created By     : Amit Pathak

 Updated Date   :7 feb-2012 at 11.47 AM.
 Update By      :Amit Pathak

*/

package com.controlPanel.cutoffMgt.cutoffSetup;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CutOffAmountAction extends ActionSupport implements ModelDriven<Object>, ServletRequestAware,ServletResponseAware 
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private CutOffAmountForm CutOffAmountForm=new CutOffAmountForm();
	
	public Object getModel()
	{
		return CutOffAmountForm;
	}
	 
	CutoffAmountDao CutoffAmountDao=new CutoffAmountDao();
	@SuppressWarnings("unchecked")
	public String execute() throws Exception 
	{ 
		try 
		{ 
			Map session = ActionContext.getContext().getSession();
			int userClientId=0;
			String result="";
			String param=CutOffAmountForm.getParam();
			String userId=(String)session.get("userId");
			String userType=(String)session.get("loginType");
			
			if(!userType.equalsIgnoreCase("superadmin"))
			{
				userClientId=Integer.parseInt((String)session.get("adminUserPortalId"));
			}
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}
  	   
			if(param.equalsIgnoreCase("cutoffAmountSearch")) 
			{
				//System.out.println("we are here ");
			  return "cutoffAmountSearch";
			}
	
			//-------------------Redirect to a respective page with data according parameter----------------------------------------- 
			if("cheakcuttoff".equalsIgnoreCase(param))
			{
				String cutOffFor=CutOffAmountForm.getCutOffFor(); 
				String cutOffBy=CutOffAmountForm.getCutOffBy(); 
				String EnterUserId=CutOffAmountForm.getEnterUserId(); 
				//-------------------------------------Block for Agent-----------------
				if("AG".equalsIgnoreCase(cutOffFor))
				{
					if("ById".equalsIgnoreCase(cutOffBy))
					{
						if(!userType.equalsIgnoreCase("superadmin")||!userType.equalsIgnoreCase("activityAdmin"))
						{
							String idstatus=CutoffAmountDao.getIdStatus(cutOffFor,EnterUserId,userId,userType,userClientId);
							if(idstatus.equalsIgnoreCase("NotExist"))
							{
								request.setAttribute("message","Please Check Input Value.");	
								return "cutoffAmountSearch"; 
							}
						}
						HashMap<String,Object> getCutOffData= CutoffAmountDao.getdata(EnterUserId,cutOffFor);
						if(getCutOffData!=null)
						{
							request.setAttribute("getCutOffData", getCutOffData);
						}else
						{
							request.setAttribute("message", "Please Check Input Value.") ;
							return "cutoffAmountSearch";
						}
					}
					if(!"ById".equalsIgnoreCase(cutOffBy))
					{
						result=CutoffAmountDao.getStatusByType(cutOffBy,EnterUserId,userType,userClientId,cutOffFor);
						if(result.equalsIgnoreCase("NotExist"))
						{
							request.setAttribute("message","Please Check Input Value.");	
							return "cutoffAmountSearch";  
						}
					}
					request.setAttribute("cutOffBy",cutOffBy);
					request.setAttribute("EnterUserId",EnterUserId);
					request.setAttribute("message", "Set Cutoff Amount for All AG Under "+EnterUserId+"");
					return "agentCutff";
				}
				
				//------------------------------------Block for Portal----------------------------------------			  
				if("Portal".equalsIgnoreCase(cutOffFor))
				{
					if("ById".equalsIgnoreCase(cutOffBy))
					{
						HashMap<String,Object> getCutOffData= CutoffAmountDao.getdataSecond(EnterUserId,cutOffFor);
						if(getCutOffData!=null)
						{
							request.setAttribute("getCutOffData", getCutOffData);
						}else
						{
							request.setAttribute("message", "Please Check Input Value.") ;
							return "cutoffAmountSearch";
						}
					}
					request.setAttribute("cutOffBy",cutOffBy);
					request.setAttribute("EnterUserId",EnterUserId);
					request.setAttribute("message", "Set Cutoff Amount for All AG Under "+EnterUserId+"");
					return "PortalCutOff";
				} 
				  
				//------------------------------------Block for DIstributor----------------------------------------			  
				if("DS".equalsIgnoreCase(cutOffFor))
				{
					if("ById".equalsIgnoreCase(cutOffBy))
					{
						if(!userType.equalsIgnoreCase("superadmin")||!userType.equalsIgnoreCase("activityAdmin"))
						{
							String idstatus=CutoffAmountDao.getIdStatus(cutOffFor,EnterUserId,userId,userType,userClientId);
					 
							if(idstatus.equalsIgnoreCase("NotExist"))
							{
								request.setAttribute("message","Please Check Input Value.");	
								return "cutoffAmountSearch"; 
							}
						}
						HashMap<String,Object> getCutOffData= CutoffAmountDao.getdata(EnterUserId,cutOffFor);
					
						if(getCutOffData!=null)
						{
							request.setAttribute("getCutOffData", getCutOffData);
						}else
						{
							request.setAttribute("message", "Please Check Input Value.") ;
							return "cutoffAmountSearch";
						}
					}
					if(!"ById".equalsIgnoreCase(cutOffBy))
					{
						result=CutoffAmountDao.getStatusByType(cutOffBy,EnterUserId,userType,userClientId,cutOffFor);
						if(result.equalsIgnoreCase("NotExist"))
						{
							request.setAttribute("message","Please Check Input Value.");	
							return "cutoffAmountSearch";  
						}
					}
					request.setAttribute("cutOffBy",cutOffBy);
					request.setAttribute("EnterUserId",EnterUserId);
					request.setAttribute("message", "Set Cutoff Amount for All AG Under "+EnterUserId+"");
					return "distributorCutff";
				}  
				//------------------------------------Block for Master DIstributor----------------------------------------			  
				if("MD".equalsIgnoreCase(cutOffFor))
				{
				 // System.out.println("we are in MD");
				  if("ById".equalsIgnoreCase(cutOffBy)) 
				  {
					  if(!userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
					  {
						  String idstatus=CutoffAmountDao.getIdStatus(cutOffFor,EnterUserId,userId,userType,userClientId);
					  
						  if(idstatus.equalsIgnoreCase("NotExist"))
						  {
							  request.setAttribute("message","Please Check Input Value.");	
							  return "cutoffAmountSearch"; 
						  }
					  }
					
					//------------------------date to show for other related to MDID-------------------------------		
					  HashMap<String,Object> getCutOffData=CutoffAmountDao.getdata(EnterUserId,cutOffFor);
					
					  if(getCutOffData!=null)
					  {
						  request.setAttribute("getCutOffData",getCutOffData);
					  }else
					  {
						  request.setAttribute("message", "Please Check Input Value.") ;
						  return "cutoffAmountSearch";
					  }
				  }
				  if("ByClientId".equalsIgnoreCase(cutOffBy))
				  {
					  String checkClientIdStatus=CutoffAmountDao.getcheckClientIdStatus(EnterUserId);
					  if(checkClientIdStatus.equalsIgnoreCase("notfound"))
					  {
						  request.setAttribute("message", "Please Check Input Value.");
						  return "cutoffAmountSearch";
					  }
				  }
				  if(!"ById".equalsIgnoreCase(cutOffBy))
				  {
					  result=CutoffAmountDao.getStatusByType(cutOffBy,EnterUserId,userType,userClientId,cutOffFor);
					  if(result.equalsIgnoreCase("NotExist"))
					  {
						  request.setAttribute("message","Please Check Input Value.");	
						  return "cutoffAmountSearch";  
					  }
				  }
				  request.setAttribute("cutOffBy",cutOffBy);
				  request.setAttribute("EnterUserId",EnterUserId);
				  request.setAttribute("message", "Set Cutoff Amount for All AG Under "+EnterUserId+"");
				  return "MdCutff";
				}  
			  
				//--------------------------------------------------for API=============================
				//-------------------------------------Block for Agent-----------------
				if("API".equalsIgnoreCase(cutOffFor)) 
				{
					if("ById".equalsIgnoreCase(cutOffBy))
					{
						HashMap<String,Object> getCutOffData= CutoffAmountDao.getdataSecond(EnterUserId,cutOffFor);
						if(getCutOffData.size()>0)
						{
							request.setAttribute("getCutOffData", getCutOffData);
						}else
						{
							request.setAttribute("message", "Please Check Input Value.") ;
							return "cutoffAmountSearch";
						}
					}
					request.setAttribute("cutOffBy",cutOffBy);
					request.setAttribute("EnterUserId",EnterUserId);
					return "APICutOff";
				}
			}
  
			//--------=========================================--------- Block  for  setting cutoff---==================================================------
			//------------------------Block to set cutoff for all agent-------------------------------------
			if("UpdateCutOffAllAG".equalsIgnoreCase(param))
			{
				String enterUserId="";
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				String cuttOffBy=(String)request.getParameter("cutOffBy");
				if(!cuttOffBy.equalsIgnoreCase("ByAll"))
				{
					enterUserId=(String)request.getParameter("EnterUserId");
				}
				String updateAllAGCutoffAmount=CutoffAmountDao.doupdateAllAGCutoffAmount(cutoffAmount,userId,userType,ipAddress,cuttOffBy,enterUserId,userClientId);
				if(updateAllAGCutoffAmount.equalsIgnoreCase("done"))
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				} else 
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}
		  
			//------------------------Block to set cutoff of particular  agent with agent id-------------------------------------
		  
			if("UpdateCutOffAGID".equalsIgnoreCase(param)) 
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
			   String  ipAddress=CutOffAmountForm.getIpAddress();
			   int  AgenID=CutOffAmountForm.getUserId();
			   String  RemarkAdmin=CutOffAmountForm.getRemarkAdmin();
			  
			   String updateAGIDCutoffAmount=CutoffAmountDao.doupdateAGIDCutoffAmount(cutoffAmount,userId,userType,ipAddress,AgenID,RemarkAdmin,userClientId);
			   if(updateAGIDCutoffAmount.equalsIgnoreCase("done"))
			   {
				   request.setAttribute("message","Process has been completed Successfully.");	
				   return "cutoffAmountSearch";  
			   } else
			   {
				   request.setAttribute("message","Process aborted due to Technical Failure.");	
				   return "cutoffAmountSearch";  
			   }
			}
			
			//------------------------Block to set cutoff for all DS-------------------------------------
			if("UpdateCutOffAllDS".equalsIgnoreCase(param))
			{
				String enterUserId="";
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				String checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				double  SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				String cuttOffBy=(String)request.getParameter("cutOffBy");
				
				if(!cuttOffBy.equalsIgnoreCase("ByAll"))
				{
					enterUserId=(String)request.getParameter("EnterUserId");
				}
				String updateAllDSCutoffAmount=CutoffAmountDao.doupdateAllDSCutoffAmount(cutoffAmount,userId,userType,ipAddress,checkBoxCutOff,SecondcutoffAmount,enterUserId,cuttOffBy,userClientId);
				if(updateAllDSCutoffAmount.equalsIgnoreCase("done")) 
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				} else 
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}		  
		  
			//------------------------Block to set cutoff of particular  agent with distributor id-------------------------------------
				  
			if("UpdateCutOffDSID".equalsIgnoreCase(param))
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				int  DSID=CutOffAmountForm.getUserId();
				double SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				String  checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				String  RemarkAdmin=CutOffAmountForm.getRemarkAdmin();
				String  RemarkOther=CutOffAmountForm.getRemarkOther();
				String updateDSIDCutoffAmount=CutoffAmountDao.doupdateDSIDCutoffAmount(cutoffAmount,userId,userType,ipAddress,DSID,SecondcutoffAmount,checkBoxCutOff,RemarkAdmin,RemarkOther,userClientId);
				if(updateDSIDCutoffAmount.equalsIgnoreCase("done")) 
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				} else
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}
		  
			//------------------------Block to set cutoff for all MD-------------------------------------
			if("UpdateCutOffAllMD".equalsIgnoreCase(param))
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				String checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				double  SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				//System.out.println("hi Manoj we are going to call method");
				String updateAllMDCutoffAmount=CutoffAmountDao.doupdateAllMDCutoffAmount(cutoffAmount,userId,userType,ipAddress,checkBoxCutOff,SecondcutoffAmount);
				if(updateAllMDCutoffAmount.equalsIgnoreCase("done"))
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				}else
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}		
				  
			//------------------------Block to set cutoff of particular  agent with distributor id-------------------------------------
			
			if("UpdateCutOffMDByID".equalsIgnoreCase(param)) 
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				int  MdID=CutOffAmountForm.getUserId();
				double SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				String  checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				String  RemarkAdmin=CutOffAmountForm.getRemarkAdmin();
				String  RemarkOther=CutOffAmountForm.getRemarkOther();
				//System.out.println("we are here Manoj how are you?");
				String updateMDIDCutoffAmount=CutoffAmountDao.doupdateMDIDCutoffAmount(cutoffAmount,userId,userType,ipAddress,MdID,SecondcutoffAmount,checkBoxCutOff,RemarkAdmin,RemarkOther);
				
				if(updateMDIDCutoffAmount.equalsIgnoreCase("done"))
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				} else
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}
			//------------------------Block to set cutoff for all MD-------------------------------------
			if("UpdateCutOffByClientId".equalsIgnoreCase(param))
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				String checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				double  SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				int  clientID=CutOffAmountForm.getUserId();
				String UpdateCutOffByClientId=CutoffAmountDao.doUpdateCutOffByClientId(cutoffAmount,userId,userType,ipAddress,checkBoxCutOff,SecondcutoffAmount,clientID);
				if(UpdateCutOffByClientId.equalsIgnoreCase("done"))
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				}else 
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}				  
		  
			//------------------------Block to set cutoff of particular  API with  id-------------------------------------
				  
			if("UpdateCutOffAPIById".equalsIgnoreCase(param))
			{
				double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				String  ipAddress=CutOffAmountForm.getIpAddress();
				int  ApiID=CutOffAmountForm.getUserId();
				String  RemarkAdmin=CutOffAmountForm.getRemarkAdmin();
				String  RemarkOther=CutOffAmountForm.getRemarkOther();
				String updateAPIIDCutoffAmount=CutoffAmountDao.doupdateAPIIDCutoffAmount(cutoffAmount,userId,userType,ipAddress,ApiID,RemarkAdmin,RemarkOther);
				if(updateAPIIDCutoffAmount.equalsIgnoreCase("done"))
				{
					request.setAttribute("message","Process has been completed Successfully.");	
					return "cutoffAmountSearch";  
				} else 
				{
					request.setAttribute("message","Process aborted due to Technical Failure.");	
					return "cutoffAmountSearch";  
				}
			}
	  
			//------------------------Block to set cutoff for all API-------------------------------------
			 if("UpdateCutOffAPIAll".equalsIgnoreCase(param)) 
			 {
				 double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				 String  ipAddress=CutOffAmountForm.getIpAddress();
				 String updateAllAPICutoffAmount=CutoffAmountDao.doupdateAllAPICutoffAmount(cutoffAmount,userId,userType,ipAddress);
				 if(updateAllAPICutoffAmount.equalsIgnoreCase("done"))
				 {
					 request.setAttribute("message","Process has been completed Successfully.");	
					 return "cutoffAmountSearch";  
				 }else
				 {
					 request.setAttribute("message","Process aborted due to Technical Failure.");	
					 return "cutoffAmountSearch";  
				 }
			 }		
				  
			 //------------------------Block to set cutoff of particular   with Portal id-------------------------------------
			 
			 if("UpdateCutOffPortalID".equalsIgnoreCase(param))
			 {
				 double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				 String  ipAddress=CutOffAmountForm.getIpAddress();
				 int  PortalID=CutOffAmountForm.getUserId();
				 double SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				 String  checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				 String  RemarkAdmin=CutOffAmountForm.getRemarkAdmin();
				 /* String  RemarkOther=CutOffAmountForm.getRemarkOther();
					   log.print("checkBoxCutOff value in CutOffAmountForm is "+RemarkOther, logger);*/
				 String updatePortalIDCutoffAmount=CutoffAmountDao.doupdatePortalIDCutoffAmount(cutoffAmount,userId,userType,ipAddress,PortalID,SecondcutoffAmount,checkBoxCutOff,RemarkAdmin);
				 if(updatePortalIDCutoffAmount.equalsIgnoreCase("done"))
				 {
					 request.setAttribute("message","Process has been completed Successfully.");	
					 return "cutoffAmountSearch";  
				 }else 
				 {
					 request.setAttribute("message","Process aborted due to Technical Failure.");	
					 return "cutoffAmountSearch";  
				 }
			 }	
				  
			 //------------------------Block to set cutoff for all API-------------------------------------
			 if("UpdateCutOffPortalAll".equalsIgnoreCase(param))
			 {
				 double cutoffAmount=CutOffAmountForm.getCutoffAmount();
				 String  ipAddress=CutOffAmountForm.getIpAddress();
				 String checkBoxCutOff=CutOffAmountForm.getCheckBoxCutOff();
				 double  SecondcutoffAmount=CutOffAmountForm.getSecondcutoffAmount();
				 String updateAllPortalCutoffAmount="";
				 
				 if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin"))
				 {
					 updateAllPortalCutoffAmount=CutoffAmountDao.doupdateAllPortalCutoffAmount(cutoffAmount,userId,userType,ipAddress,checkBoxCutOff,SecondcutoffAmount);
				 }
				 else
				 {
					 request.setAttribute("message","You are not Authorized for this action.");	
					 return "cutoffAmountSearch";  
				 }
				 if(updateAllPortalCutoffAmount.equalsIgnoreCase("done"))
				 {
					 request.setAttribute("message","Process has been completed Successfully.");	
					 return "cutoffAmountSearch";  
				 }else
				 {
					 request.setAttribute("message","Process aborted due to Technical Failure.");	
					 return "cutoffAmountSearch";  
				 }
			 }					  
		}
		catch(Exception ex)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println(ex.toString());
			System.out.println("Exception in CutOffAmountAction ");
			return "cutoffAmountSearch";
		}
		return "cutoffAmountSearch";
	}
	
	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) 
	{
		this.request = httpServletRequest;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) 
	{
		
	}
}