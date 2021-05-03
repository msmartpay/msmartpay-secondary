<%HashMap<String,String> dynamicData=(HashMap)session.getAttribute("dynamicData");
	String clientId=dynamicData.get("clientId");
	String companyName=dynamicData.get("companyName");
	String mobileNo1=dynamicData.get("mobileNo1");
	String helpEmailID=dynamicData.get("helpEmailID");
	String userType=dynamicData.get("userType");
	String tickerMessage=dynamicData.get("tickerMessage");
	String headerHomeImage=dynamicData.get("headerHomeImage");
	String powerBy=dynamicData.get("powerBy");
	
	if(powerBy==null){
	powerBy="";} 
	
	String bannerStatus=dynamicData.get("bannerStatus");
	if(bannerStatus==null){
		bannerStatus="N";} 
	
	%>