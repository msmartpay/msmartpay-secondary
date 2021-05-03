package com.distributorregistration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;


import com.common.SendSmtpMaild;
import com.common.SendMailAttachment;
import com.common.SendMailAttachmentWithCC;


import com.distributorregistration.DistributorRegistrationBussinessDelegator;


public class DistributorRegistrationAction extends Action {
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response)
	throws Exception {
		
		PrintWriter out=response.getWriter();	
		HttpSession session=request.getSession(true);
        String clientId=(String)session.getAttribute("clientId");
        System.out.println("clientId is==========="+clientId);

        String mdId=(String)session.getAttribute("mdId");
		System.out.println("mdId is==============================="+mdId);
		if(mdId==null){
			session.setAttribute("message","Your Login session has Expired. Please Login to Continue.");
			return mapping.findForward("sessionexp");
		}
		int val=0;
		Connection con = null;
		try
		{		
			String param=(String)request.getParameter("param");
			String agent_cell_email_id=(String)session.getAttribute("Help_email_id1");
			String distributor_login_url=(String)session.getAttribute("distributor_login_url");
			System.out.println(" param is============== : "+param);

			if(param.equalsIgnoreCase("checkMobile")){						 
				String UserMobile=request.getParameter("mobile");
			//	System.out.println(" UserMobile :"+UserMobile);
				String status=DistributorRegistrationDao.checkMobile(UserMobile);						 
				out=response.getWriter();						  
				out.print(status);
				return null;	  
			}
			if(param.equalsIgnoreCase("checkEmailId")){						 
				String UserEmail=request.getParameter("email");	
			//	System.out.println("Emaial ID IOD ::: "+UserEmail);
				String status=DistributorRegistrationDao.checkEmail(UserEmail);						 
				out=response.getWriter();						  
				out.print(status);
				return null;	  
			}
			if("register".equals(param)){
			//	System.out.println("we are here111");
				return mapping.findForward("register");	
			}
			if("register".equals(param))
            {
			//	System.out.println("page successfully redirected");
				String resultresponse=" ";
            	session.setAttribute("message",resultresponse);
            	return mapping.findForward("register");
            }       	   
			if("DsRegitration".equals(param))
			{         
				String FullMDid=(String)session.getAttribute("completeId");				   
				String firstname=request.getParameter("firstname");			
				String lastname=request.getParameter("lastname");
				String date=request.getParameter("date");
				String month=request.getParameter("month");
				String year=request.getParameter("year");
				String DOB=year+"-"+month+"-"+date;
				String gender=request.getParameter("gender");
				String firmtype=request.getParameter("firmtype");
				if(firmtype.equalsIgnoreCase("Others")){
					firmtype=request.getParameter("firmtype");
				}
				String firmname=request.getParameter("firmname");
				String Address1=request.getParameter("Address1");
				String Address2=request.getParameter("Address2");
				String officeCountry=request.getParameter("officeCountry");
				String state=request.getParameter("state");
				String District=request.getParameter("District");
				String city=request.getParameter("city");
				String officePinCode=request.getParameter("officePinCode");
				String mobile=request.getParameter("mobile");
				String PancardStatus=request.getParameter("PancardStatus");
				String PancardNo="";
				if (PancardStatus.equalsIgnoreCase("Y")){				   
					PancardNo=request.getParameter("PancardNo");
				}else{
					PancardNo="NA";
				}			
				String emailId=request.getParameter("emailId");	
			   
				String emailResponse=DistributorRegistrationDao.chkEmail(emailId);
				System.out.println("emailResponse is>>>>>>>>>>>>>>>>>>>>"+emailResponse);
				if(emailResponse.equals("Email ID is Duplicate.")){
					session.setAttribute("message",emailResponse);
					return mapping.findForward("register");
				}		   
				String  password=DistributorRegistrationBussinessDelegator.getRandomString(10);	 
			//	System.out.println("password "+password);
				DistributorRegistrationBean myForm = (DistributorRegistrationBean)form;
			 
				String addressProof=request.getParameter("addressProof");
				String bussinessProof=request.getParameter("bussinessProof");
				String idProof=request.getParameter("idProof"); 			   
			 
				FormFile myFile = myForm.getIdFile();	
				FormFile myFile1 = myForm.getAddFile(); 
				FormFile myFile2 = myForm.getBusinessFile();
				String idProofFileName    = myFile.getFileName();  
				String addressProofFileName= myFile1.getFileName();  
				String businessProofFileName=myFile2.getFileName();  
				if(addressProof.equalsIgnoreCase("Not Available")){
					addressProofFileName="NA.txt";
				}
				if(bussinessProof.equalsIgnoreCase("Not Available")){
					businessProofFileName="NA.txt";
				}
				if(idProof.equalsIgnoreCase("Not Available")){
					idProofFileName="NA.txt";
				}
                     
				String idExtension="";
				String addExtension="";
				String businessExtension="";          	   
				int id= idProofFileName.lastIndexOf(".");
				int add=addressProofFileName.lastIndexOf(".");
				int biz=businessProofFileName.lastIndexOf(".");
          	   
				idExtension=idProofFileName.substring(id+1,idProofFileName.length());
				addExtension=addressProofFileName.substring(add+1,addressProofFileName.length());
				businessExtension=businessProofFileName.substring(biz+1,businessProofFileName.length());

				HashMap hamdata=DistributorRegistrationDao.saveDistributorDetails(con,firstname,lastname,DOB,
						gender,firmtype,firmname,Address1,Address2,officeCountry,state,District,city,officePinCode,mobile,
						PancardNo,emailId,idProofFileName,addressProofFileName,businessProofFileName,idExtension,addExtension,
						businessExtension,FullMDid,password);         
             
		          	   
						String complteDistId=(String)hamdata.get("complteDistId");
		          	   
						String disEmailId=emailId;
						String mdEmailid=(String)hamdata.get("mdEmailid");
		          	   
						 
						String recipientsagnt[]={disEmailId,mdEmailid};//disEmailId
						String ccrecipients[]={"Rishijangra15@gmail.com"};
				
						String subjectagnt="User Registration – Distributor";
						String Messageagnt="<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
						 "<head>"+
						 "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />"+
						 "<title>Untitled Document</title>"+
						 "<link href=\"http://travelepoint.com/css/travel.css\" rel=\"stylesheet\" type=\"text/css\" />"+
						 "</head><body >"+
						 "<table width=100% border=0 align=center cellpadding=0 cellspacing=0 style=\"font-family:Trebuchet MS\" >"+
						 "<tr><td></td>"+
						 "</tr><tr><td><p>Dear User,<br></p><p>Your Distributor ID has been registered. Your Login Details are given below."+
						 "<table width="+390+"  style=\"font-family:Trebuchet ms\"> "+
						 "<tr>"+
						 "<td width="+93+" height=15 >Login URL :</td>"+
						 "<td width="+285+" height=15 valign=top><a href=\""+distributor_login_url+"\">"+distributor_login_url+"</a><br>"+
						 "</tr>"+
						 " <tr>"+
						 " <td height=15 >User ID :</td>"+
						 "<td height=15 valign=top><a href=\"mailto:"+disEmailId+"\">"+disEmailId+"</a></td>"+
						 "</tr> "+
						 "<tr>"+
						 "<td height=15 >Password :</td>"+
						 "<td height=15 valign=top>"+password+"</td>"+
						 "</tr>"+
						 "<tr>"+
						 "<td height=15 >Agent ID :</td>"+
						 "<td height=15 valign=top>"+complteDistId+"</td>"+
						 "</tr> "+
						 " </table></table><p style=\"font-family:Trebuchet MS\">Please always quote your Distributor ID in further communication with us.<br></p> <p style=\"font-family:Trebuchet MS\">Regards<br></p><p style=\"font-family:Trebuchet MS\">Team Activation<br></p><p align=justify>&nbsp;</p>"+
						 "<div align=justify></td>"+

						 "</body></html>";	
						
						String mailStatus3=SendSmtpMaild.sendSSLMessageBCC(recipientsagnt, ccrecipients, subjectagnt, Messageagnt, agent_cell_email_id);
						
						
						
						request.setAttribute("message", "Thanks for Registering with us. Please check your Email for Information regarding User ID & Password.");
						return mapping.findForward("home");
					
			}			
		}catch(Exception e){
			System.out.println("Exception in DistributorRegistrationAction"+e.toString());
			e.printStackTrace();
			return mapping.findForward("home");
		}
		finally
		{
			try
			{
				if(con!=null)
					con.close();
			}
			catch(Exception e)
			{
				System.out.println("Exception in DistributorRegistrationAction while closing connection"+e.toString());
			}			
		}
		return mapping.findForward("home");
	}	
}