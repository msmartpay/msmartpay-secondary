package com.controlPanel.inventoryMgt.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ControllerAction extends ActionSupport
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerDao controlDao = null;
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	private HttpSession session = null;
	private PrintWriter out = null;
	
	
	public String execute() throws IOException
	{
		request=ServletActionContext.getRequest();
		response=ServletActionContext.getResponse();
		session=request.getSession();
		out=response.getWriter();
		ArrayList getDetails = new ArrayList<String>();
		
		String userId=(String)session.getAttribute("userId");
		if(userId==null) 
		{
			request.setAttribute("message","Your Login Session has Expired. Please Login Again.");
			return "sessionExpire";
		}
		
		String param=(String)request.getParameter("param");	System.out.println("param-----"+param);
		String message = "message";
		
		controlDao = ControllerDao.getInstance();
		String fromdate = "";
		String todate = "";
		if("getInventoryControllerPage".equalsIgnoreCase(param))
		{
			return "ControlPage";
		}
		else
		if("getDetails".equalsIgnoreCase(param))
		{
			 fromdate = request.getParameter("from");	session.setAttribute("from", fromdate);
			 todate = request.getParameter("to");	session.setAttribute("to", todate);
			
			try
			{
				getDetails = controlDao.getDetails(fromdate, todate);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute(message, "Problem in fetching data!");
				return "ControlPage";
			}
			if(getDetails.size() <= 0 || getDetails == null)
			{
				request.setAttribute(message, "No data available for the selected dates.");
				return "ControlPage";
			}
			int size = getDetails.size();	System.out.println("size-----"+size);
			
			request.setAttribute("detailData", getDetails);
			return "ControlPage";
			
		}
		else
		if("removeData".equalsIgnoreCase(param))
		{
			String id = request.getParameter("id"); 	System.out.println("id---"+id);
			
			String status = "N/A";
			try
			{
				status = controlDao.removeData(id);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute(message, "Problem in removal of data!");
				return "ControlPage";
			}
			if(status.equalsIgnoreCase("success"))
			{
				request.setAttribute(message, "Deleted successfully!");
				return "ControlPage";
			}
			else
			if  (status.equalsIgnoreCase("failure"))
				{
					request.setAttribute(message, "Problem in deletion of record. Please try after some time!");
					return "ControlPage";
				}
		}
		else
		if("editRecord".equalsIgnoreCase(param))
		{
			String id = request.getParameter("id"); 	System.out.println("id---"+id);
			String date = request.getParameter("date");	System.out.println("date---"+date);
			
			try{
				getDetails = controlDao.getEditDetails(id,date);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute(message, "Problem in fetching data!");
				return "ControlPage";
			}
			if(getDetails.size() <= 0 || getDetails == null)
			{
				request.setAttribute(message, "No data available for the selected dates.");
				return "ControlPage";
			}
			int size = getDetails.size();	System.out.println("size-----"+size);
			
			request.setAttribute("editdetailData", getDetails);
			
			return "editControlPage";
			
		}
		else
		if("updateDetails".equalsIgnoreCase(param))
		{ 
			String packagetype = ""; 
			String packId = ""; 
			String packprice = "";
			String packtitle = "";
			String travelBy = "";
			String traveltype = "";
			String arrivalcity = "";
			String departcity = "";
			String hotelBudget = "";
			String hotelDuration = "";
			String hotelName = "";
			String hotelRating = "";
			
			String mealPrefe = "";
			String sightSeeing = "";
			String mealIncluded = "";
			String placeToView = "";
			String placeDescription = "";
			String inclHotel = "";
			String inclTransport = "";
			String incluOthers = "";
			String packageItinerary = "";
			String spclReqt = "";
			String termNCond = "";
			String cancPolicy = "";
			
			packagetype = request.getParameter("packtype");
			packId  = request.getParameter("pid");
			packprice  = request.getParameter("packprice");
			packtitle  = request.getParameter("packtitle");
			travelBy  = request.getParameter("travelby");
			traveltype  = request.getParameter("ttype");
			
			arrivalcity  = request.getParameter("arrivalcity");
			
			String arrivalcitycode = controlDao.getCityCode(arrivalcity);
			
			departcity  = request.getParameter("departcity");
			
			String departcitycode = controlDao.getCityCode(departcity);
			
			hotelBudget  = request.getParameter("budgethotel");
			hotelDuration  = request.getParameter("hotelstayduration");
			hotelName  = request.getParameter("hotelname");
			hotelRating  = request.getParameter("hotelrating");
			mealPrefe = request.getParameter("meal");
			sightSeeing = request.getParameter("sightseeing");
			mealIncluded  = request.getParameter("mealincluded");
			placeToView  = request.getParameter("palcetoview");
			placeDescription  = request.getParameter("placedescription");
			inclHotel  = request.getParameter("inclusionhotel");
			inclTransport  = request.getParameter("inclutransport");
			incluOthers  = request.getParameter("incluother");
			packageItinerary  = request.getParameter("packItinerary");
			spclReqt  = request.getParameter("spclReqt");
			termNCond  = request.getParameter("termnCond");
			cancPolicy = request.getParameter("canclpolicy");
			
			String status = "N/A";
			try
			{
				status = controlDao.updateDetails(packagetype, packId,	packprice, packtitle, travelBy, traveltype, arrivalcitycode, departcitycode, hotelBudget, 
					hotelDuration, hotelName, hotelRating, mealPrefe,sightSeeing, mealIncluded, placeToView, placeDescription, inclHotel, inclTransport,
					incluOthers, packageItinerary, spclReqt, termNCond, cancPolicy);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				request.setAttribute(message, "Problem in updation of data!");
				return "ControlPage";
			}
			if(status.equalsIgnoreCase("failure"))
			{
				request.setAttribute(message, "Problem in updation of record. Please try after some time!");
				return "ControlPage";
			}
			else
			{
				request.setAttribute(message, "Successfully updated!");
				return "ControlPage";
			}
		}
		
		
		return null;
	}
}
