<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="java.io.IOException"%>
<%@page import="org.xml.sax.SAXException"%>
<%@page import="javax.xml.parsers.ParserConfigurationException"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.mobDthDataCardRecharge.MarsController"%>
<%@page import="com.mobDthDataCardRecharge.MobileDthRechargeDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
function sendreponse(){
	document.sendResponse.submit();
}

</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%
//ref=RC305158349110193069&number=7082829376&amount=60&status=SUCCESS&transid=910634843.%20&yourref=MBHuhXRlTIKHvG&balance=152817200.579
String transactionId="";
System.out.print("Mars Response >>>>>>>>>>> "+request.getQueryString());
String marsRefId=request.getParameter("ref");
String Status=request.getParameter("status");
String transid=request.getParameter("transid");
String yourref=request.getParameter("yourref");
String mobile=request.getParameter("number");
String amount=request.getParameter("amount");
String updateStatus="";
if(transid==null)
	transid="NA";
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>. Response page work start ................... marsRefId : "+marsRefId+"  :   Status : "+Status+"  : transactionId : "+transactionId+" yourref : "+yourref);
String ERROR="",TranStatus="",agentID="",checkVendor="MARS",ResoponseXML="transactionId : "+" : marsRefId : "+marsRefId+" : Status : "+Status,InputXML="";


try {
	
	MobileDthRechargeDao daoObj=new MobileDthRechargeDao();
	daoObj.saveAPIResponse(yourref, "Mars", request.getQueryString());
		
		if((marsRefId!=null && !marsRefId.equalsIgnoreCase(""))&& (Status!=null && !Status.equalsIgnoreCase("")))
		{
			System.out.println(" JSP Backend Start....");
			MarsController marsCl=new MarsController();
			HashMap<String, String> mapResult;
			marsRefId=marsRefId.trim();
			HashMap<String, Object> map=(HashMap<String, Object>)daoObj.getTranDetails(yourref);
			System.out.println(">>>>>>>>>>>>>> Mars getTranDetails map .................................."+map);
			if(map!=null){
				
				transactionId=(String)map.get("tran_id");
				String client_id=(String)map.get("client_id");
				agentID=(String)map.get("user_id");

				
					System.out.println(" >>>>>>>>>>>>>>>. Response page work start .................................."+agentID);
					if(Status.equalsIgnoreCase("Success"))
					{
						ERROR="00";
						TranStatus="Success";
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else if(Status.equalsIgnoreCase("Invalid"))
					{
						ERROR="01";
						TranStatus="Pending";
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else if(Status.equalsIgnoreCase("Faild"))
					{
									ERROR="02";
									TranStatus="Failure";
									updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else if(Status.equalsIgnoreCase("ABORTED"))
					{
									ERROR="02";
									TranStatus="Failure";
									updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else if(Status.equalsIgnoreCase("Failure"))
					{
									ERROR="02";
									TranStatus="Failure";
									updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else if(Status.equalsIgnoreCase("Pending")|| Status.equalsIgnoreCase("RESPWAIT") || Status.equalsIgnoreCase("SUSPENSE"))
					{
						ERROR="01";
						TranStatus="Pending";
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					
					}
					else if(Status.equalsIgnoreCase("Error"))
					{
						ERROR="02";
						TranStatus="Failure";
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					else 
					{
						ERROR="01";
						TranStatus="Pending";
						updateStatus=daoObj.updateStatusTranEGEN(transactionId,TranStatus,agentID,ERROR,transid,checkVendor,InputXML,ResoponseXML);
					}
					//************************** Removing Context ***************************
					
					
					System.out.println(">>>>>>>>>>>>>>>>>> Transaction not updated  .................................. "+updateStatus);
					
					String url=daoObj.getAPIClientResponseURL(agentID);
					
					
				
			
			}else{



			}
			agentID=null;
			System.out.println(">>>>>>>>>>>>>>>>>>>>> Response page work Stop .................................. "+updateStatus);
			System.out.println(">>>>>>>>>>>>>>>> JSP Backend End....");
		}else{
			System.out.println(">>>>>>>>>>>>>>>>>>>> Mars Ref Id NULL >>>>>>>>>>>>>>>>>>>>>>>");
			%><h2>You Cannot access this page</h2>
			<%
		}
} catch (Exception e) {
	e.printStackTrace();
}

%>

</head>
<body>
<form name="sendResponse" action="http://smartkinda.com/AG/MarsResponse.jsp" method="get">
	<input name="ref" value="<%=marsRefId%>" />
	<input name="status" value="<%=Status%>" />
</form>
</body>
</html>