package  com.activity.userRegistration.corporateUser.whitelableClient;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.common.ConvertUtility;
import com.common.Encryption;
import com.common.SendSmtpMail;
import com.formBean.whiteLabel.WhiteLabelDetailsFormBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * Updated By :Manoj
 * Updated Date : 1 Jun 2013
 * Updated Matter : Optimization of code as well as data base.
 */

public class WhiteLabelRegistrationAction extends ActionSupport implements ModelDriven<Object>,ServletRequestAware,ServletResponseAware
{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private Map session;
	   
	private WhiteLabelDetailsFormBean wlDetails=new WhiteLabelDetailsFormBean();  
	
	public Object getModel()
	{
		return wlDetails;
	}  
	
	public String execute() throws Exception
	{ 
		Map session=ActionContext.getContext().getSession();		
		try
		{
			String userId=(String) session.get("userId");			   
			if(userId==null)
			{
				request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
				return "sessionExpire";
			}	    	 
			
			String param=wlDetails.getParam();
			System.out.println("WhiteLabelRegistrationAction param is "+param);	    	  
			
			if(param.equalsIgnoreCase("register"))
			{	    		  
				WhiteLabelRegistrationDao dao=new WhiteLabelRegistrationDao();
				ArrayList<String> list=dao.getCategoryName();
				request.setAttribute("categoryList", list);
				return "registrationPage";     		  
			}			  
			else if(param.equals("registration"))
			{  
				HashMap<String,String> wlClientDetails =new HashMap<String,String>();
				String clientType=wlDetails.getClientType();
				String companyName=wlDetails.getCompanyName();					
				String agentPanelHelpMobileNo1=wlDetails.getAgentPanelHelpMobileNo1();
				String agentPanelHelpEmailId=wlDetails.getAgentPanelHelpEmailId();
				String domainName=wlDetails.getDomainName();
				domainName=ConvertUtility.getDomainName(domainName);
				String agentCellEmailId=wlDetails.getAgentCellEmailId();
				
				if(agentCellEmailId==null)
				{
					agentCellEmailId=agentPanelHelpEmailId;
				}
				
				String category=wlDetails.getCategoryName();
				String careUrl=wlDetails.getCareUrl();
				if(careUrl==null)
				{
					careUrl="#";
				}
				String agentUserType="";
				String distUserType="";
				String agentLoginUrl="";
				String distributorLoginUrl="";
				String clientFlag="";		
				
				if(clientType.equalsIgnoreCase("TEP"))
				{
					agentLoginUrl="http://"+domainName+"/agent";
					distributorLoginUrl="http:"+domainName+"/DS";
					clientFlag="0";
				}
				else if(clientType.equalsIgnoreCase("REP"))
				{
					agentLoginUrl="http://"+domainName+"/AREP";	
					System.out.println("we are into REP Block and agent login url is "+agentLoginUrl);
					clientFlag="1";
				}
						
				else if(clientType.equalsIgnoreCase("Combo"))
				{
					agentLoginUrl="http://"+domainName+"/agent";							
					clientFlag="2";
					clientType="Combo";
				}
				String busUrl="http://bus."+domainName+"/";
				String airUrl="http://air."+domainName+"/";
				String mdLoginUrl="http://"+domainName+"/MDS";
				distributorLoginUrl="http://"+domainName+"/DS";		
				System.out.println("mdLoginUrl is "+mdLoginUrl);
				System.out.println("distributorLoginUrl is "+distributorLoginUrl);
				File headerHomeImage=wlDetails.getHeaderHomeImage();					  
				String headerHomeImageFileName=wlDetails.getHeaderHomeImageFileName();			  
				String headerHomeEx="";
				int dotPos1= headerHomeImageFileName.lastIndexOf(".");
				headerHomeEx=headerHomeImageFileName.substring(dotPos1+1,headerHomeImageFileName.length());	
				
				if(headerHomeEx.equalsIgnoreCase("xlsx")||headerHomeEx.equalsIgnoreCase("docx")||headerHomeEx.equalsIgnoreCase("pdf")||headerHomeEx.equalsIgnoreCase("txt"))
				{
					request.setAttribute("message","Image File must be .jpg or .png or .gif or .jpeg.");
					return "registrationPage";
				}
				
				String folderName=companyName;
				File forFolder=new File(request.getRealPath("/") +"images"+"/"+folderName);
				boolean flag=forFolder.mkdir();
				if(flag)  
				{
					String file1Path="";
					if(!headerHomeImageFileName.equals(""))
					{ 
						file1Path = request.getRealPath("/")+"images/"+companyName+"";
						File imgFile1 = new File(file1Path, headerHomeImageFileName);
						FileUtils.copyFile(headerHomeImage, imgFile1);
					}
				}
				String headerHomeFileName = companyName+"/"+headerHomeImageFileName;
				wlClientDetails.put("companyName",companyName );
				wlClientDetails.put("headerHomeFileName",headerHomeFileName );
				wlClientDetails.put("agentPanelHelpMobileNo1",agentPanelHelpMobileNo1 );
				wlClientDetails.put("agentPanelHelpEmailId",agentPanelHelpEmailId );
				wlClientDetails.put("domainName",wlDetails.getDomainName() );
				domainName=wlDetails.getDomainName();
				wlClientDetails.put("agentLoginUrl",agentLoginUrl );
				wlClientDetails.put("distributorLoginUrl",distributorLoginUrl );
				wlClientDetails.put("busUrl",busUrl );
				wlClientDetails.put("airUrl",airUrl );
				wlClientDetails.put("agentCellEmailId",agentCellEmailId );
				wlClientDetails.put("agentUserType",agentUserType );
				wlClientDetails.put("distUserType",distUserType );
				wlClientDetails.put("mdLoginUrl", mdLoginUrl);
				wlClientDetails.put("careUrl",careUrl );
				wlClientDetails.put("clientType",clientType );
				wlClientDetails.put("clientFlag",clientFlag );
				wlClientDetails.put("category", category);
				session.put("wlClientDetails", wlClientDetails);	
				return "registrationNextPage";
			}
			else if(param.equalsIgnoreCase("registrationNextPage"))
			{
				HashMap<String,String> wlClientDetails=(HashMap<String,String>)session.get("wlClientDetails");
				String firstName=wlDetails.getFirstName();
				String lastName=wlDetails.getLastName();
				String dateOfBirth=wlDetails.getDob();
				String gender=wlDetails.getGender();
				String designation=wlDetails.getDesignation();
				String emailId=wlDetails.getEmailId();
				String officialNumber=wlDetails.getOfficialNumber();
				String addressLine1=wlDetails.getAddressLine1();
				String addressLine2=wlDetails.getAddressLine2();
				String country=wlDetails.getCountry();
				String state=wlDetails.getState();
				String district=wlDetails.getDistrict();
				String city=wlDetails.getCity();
				String pincode=wlDetails.getPincode();
				String panNo=wlDetails.getPanNo();
				String registerBy=userId;
				WhiteLabelRegistrationDao daoObj=new WhiteLabelRegistrationDao();
				String userNameStatus=daoObj.checkUserName(emailId);
				
				if(userNameStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String userEmailStatus=daoObj.checkUserEmail(emailId); 

				if(userEmailStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Email ID is Duplicate.");
					return "registrationPage";
				}
				String mobileStatus=daoObj.checkMobileNo(officialNumber);
				if(mobileStatus.equalsIgnoreCase("invalid"))
				{
					request.setAttribute("message","Mobile Number is Duplicate.");
					return "registrationPage";
				}
				
				String loginPassword=ConvertUtility.getRandomNumber();
				String password=Encryption.SHA1(loginPassword);
				String companyName=wlClientDetails.get("companyName");
				String headerHomeFileName=wlClientDetails.get("headerHomeFileName");
				String agentPanelHelpMobileNo1=wlClientDetails.get("agentPanelHelpMobileNo1");
				String agentPanelHelpEmailId=wlClientDetails.get("agentPanelHelpEmailId");
				String domainName=wlClientDetails.get("domainName");
				String agentLoginUrl=wlClientDetails.get("agentLoginUrl");
				String distributorLoginUrl=wlClientDetails.get("distributorLoginUrl");
				String busUrl=wlClientDetails.get("busUrl");
				String airUrl=wlClientDetails.get("airUrl");	
				String agentCellEmailId=wlClientDetails.get("agentCellEmailId");
				String agentUserType=wlClientDetails.get("agentUserType");
				String distUserType=wlClientDetails.get("distUserType");
				String mdLoginUrl=wlClientDetails.get("mdLoginUrl");
				String careUrl=wlClientDetails.get("careUrl");
				String clientType=wlClientDetails.get("clientType");
				String clientFlag=wlClientDetails.get("clientFlag");
				String category=wlClientDetails.get("category");
				
				HashMap<String,String> PortalUserDetails=daoObj.whiteLabelClientRegistration
				(companyName,headerHomeFileName,agentPanelHelpMobileNo1,agentPanelHelpEmailId,domainName,agentLoginUrl,distributorLoginUrl,busUrl,airUrl,
						agentCellEmailId,agentUserType,distUserType,mdLoginUrl,careUrl,clientType,clientFlag,category,
						firstName,lastName,dateOfBirth,gender,designation,officialNumber,emailId,addressLine1,addressLine2,country,state,district,pincode,password,city,registerBy,panNo);
				
				if(PortalUserDetails.size()>0)
				{
					String helpEmailId=PortalUserDetails.get("HelpMailID");
					String id=PortalUserDetails.get("UserID");
					String recipients[]={emailId,helpEmailId};
					String adminLoginUrl="http://www.smartkinda.com/SA/";
					String subject="User Registration - Admin";

					String message="<html><BODY cellpadding=\"0\" cellspacing=\"0\"><table border=\"0\"  width=\"100%\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" hspace=\"0\" vspace=\"0\"  bordercolor=\"#FFFFFF\"><tr><td><table height=\"200\" ><tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Dear &nbsp;User,</td></tr>"+
					"<p><tr><td>"+
					"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td colspan='3'>&nbsp;</td></tr><tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Your Admin ID has been registered. Your Login Details are given below.<br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;width:100px;' align='left'>Login URL&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><a href=\""+adminLoginUrl+"/\">"+adminLoginUrl+"</a><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Login ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font><a href=\"mailto:"+emailId+"/\">"+emailId+"</a></font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Password&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+loginPassword+"</font><br></td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;' align='left'>Admin ID&nbsp;</td><td align='center' style='width:50px;font-family:\"Trebuchet MS\";font-size:14px;'>:</td><td align='left' style='font-family:\"Trebuchet MS\";font-size:14px;'><font>"+id+"</font><br></td></tr>"+
					"<tr><td colspan='3'>&nbsp;<br></td></tr>"+
					"<tr><td colspan='3' style='font-family:\"Trebuchet MS\";font-size:14px;'>Please always quote your Admin ID in further communication with us.<br><br></tr>"+
					"</table></td></tr></p>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Regards,</td></tr>"+
					"<tr><td>&nbsp;</td></tr>"+
					"<tr><td style='font-family:\"Trebuchet MS\";font-size:14px;'>Team Activation</td></tr><br>"+				   
					"</tr></td></table></BODY></html>";

					try{
						SendSmtpMail.sendSSLMessage(recipients, subject, message,helpEmailId);
					}catch (Exception e) {
						e.printStackTrace();
						System.out
								.println("WhiteLabelRegistrationAction.execute() Sending Mail Failed");
					}
							   
					request.setAttribute("message","Process has been completed Successfully.");
					request.setAttribute("PortalUserRegDetailsMap",PortalUserDetails );
					return "registrationCompleted"; 
				}else
				{
					request.setAttribute("message","Process aborted due to Technical Failure."); 
					return "registrationPage";
				}

			}else if("checkEmailID".equalsIgnoreCase(param))
			{
				String checkEmailID=request.getParameter("checkEmailID");
				WhiteLabelRegistrationDao daoObj=new WhiteLabelRegistrationDao();
				String userNameStatus=daoObj.checkUserEmail(checkEmailID);
				PrintWriter out=response.getWriter();
				out.print(userNameStatus);
				return null;
			}
			else if("checkUserMobile".equalsIgnoreCase(param))
			{
				String Mobile=request.getParameter("Mobile");
				WhiteLabelRegistrationDao daoObj=new WhiteLabelRegistrationDao();
				String userMobileStatus=daoObj.checkMobileNo(Mobile);
				PrintWriter out=response.getWriter();
				out.print(userMobileStatus);
				return null;
			}
		}
		catch(Exception e)
		{
			request.setAttribute("message","Process aborted due to Technical Failure.");
			System.out.println("Exception in WhiteLabelRegistrationAction");
			System.out.println(e.toString());
			return "registrationPage";
		}
		return "err";   
	}
	
	public void setServletRequest(HttpServletRequest request)
	{
		this.request=request;
	}

	public void setSession(Map session)
	{
		session = this.getSession();
	}

	public Map getSession()
	{
		return session;
	}
	
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response=response;
	}
}
