package com.msmart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.msmart.api.controller.CyberHubController;
import com.msmart.api.controller.VenFone;
import com.msmart.service.PrepaidService;

@Path("APIResponse")
public class ResponseController {


	Logger logger = Logger.getLogger(ResponseController.class);


	@Context HttpServletRequest request;

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Path("/cyberHubApiResponse")

	public void raviApiResponse()	
	{
		try {
			CyberHubController raviTechnology=new CyberHubController();
			raviTechnology.raviresponseHandler(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Path("/vastwebindia")

	public void vastwebindia()	
	{
		try {
			VenFone venFone=new VenFone();
			venFone.vastwebindia(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Path("/venafone")

	public void venafone()	
	{
		try {
			VenFone venFone=new VenFone();
			venFone.venafone(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@POST
	@Consumes(MediaType.TEXT_HTML)
	@Path("/mrobotics")

	public void mrobotics()	
	{
		try {
			VenFone venFone=new VenFone();
			venFone.mrobotics(request);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@GET
	@Path("/vens-callback")
	public void venusCallback() {
		
		//ResponseStatus=FAILURE&OperatorTxnID=NA&OrderNo=&MerTxnID=0003200724164232&AccountNo=9582153858
		String response=request.getQueryString();
		String requestURI=request.getRequestURI();
		String requestIP=request.getRemoteAddr();
		
		String status=request.getParameter("ResponseStatus");
		String OperatorTxnID=request.getParameter("OperatorTxnID");
		String OrderNo=request.getParameter("OrderNo");
		String refId=request.getParameter("MerTxnID");
		
		logger.info("Callback Response : "+response);
		logger.info("Callback requestURI : "+requestURI);
		logger.info("Callback requestIP : "+requestIP);
		
		PrepaidService service=new PrepaidService();
		service.venusCallback(status,OperatorTxnID,OrderNo,refId);
		
	}

}
