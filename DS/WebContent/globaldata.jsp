<%@ page import="java.util.*" %>
<%@ page import="com.common.CommonDetailDao" %>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.text.NumberFormat" %>
<%@ page import = "java.math.BigDecimal" %>
<%

NumberFormat formatter = new DecimalFormat("#0.00");
String distributorInitial=(String)session.getAttribute("distributorInitial");
String distributorId=(String)session.getAttribute("distributorId");
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+distributorId);
String companyName=(String)session.getAttribute("page_title");
String odpass=(String)session.getAttribute("odpass");
double totalCash=(Double)session.getAttribute("totalCash");
double usedCash=(Double)session.getAttribute("usedCash");
double cutoffAamount=(Double)session.getAttribute("cutoffAamount");
String HeaderImage=(String)session.getAttribute("DSHeaderImage");
System.out.println("HeaderImage>>>>>>>>>>>>>>>>>>>>>>>>>>"+HeaderImage);
String helpEmailId=(String)session.getAttribute("helpEmailId");
String helpMobileNo=(String)session.getAttribute("helpMobileNo");
String copyRight=(String)session.getAttribute("copyRight");
String poweredBy=(String)session.getAttribute("poweredBy");
String titlePageheader=(String)session.getAttribute("titlePage");
String DscompleteID=distributorInitial+distributorId;

String bannerStatus="";
if(session.getAttribute("bannerStatus")==null)
	bannerStatus="N";
else
	bannerStatus=(String)session.getAttribute("bannerStatus");

System.out.println("bannerStatus : "+bannerStatus);

String page_title=(String)session.getAttribute("page_title");

CommonDetailDao CommonDetailDao=new CommonDetailDao();

String distributorbalanceAmount=(String)CommonDetailDao.getDsbalance(distributorId);
if(distributorbalanceAmount==null){
distributorbalanceAmount="";
}
		double mdBlalance = Double.parseDouble(distributorbalanceAmount);
		BigDecimal MdBlanceAmount = new BigDecimal(mdBlalance);
		BigDecimal FinalBalance = MdBlanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("FinalBalance::"+FinalBalance);

System.out.println("distributorbalanceAmount"+distributorbalanceAmount);
%>
<script type="text/javascript" language="javascript">

var xmlHttp;
function createXMLHttpRequest()
{
	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		}
}

function checksession()
{
createXMLHttpRequest();
xmlHttp.onreadystatechange=printValues;
xmlHttp.open("POST","doCommonAction.action?param=checksession",true);
xmlHttp.send(null);
}

function printValues()
{
	if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response1=xmlHttp.responseText;
              

				if(response1=="valid")
				{	
				}
				else
				{
					sessionExpireAction();
				}
			}
		}
	}

function sessionExpireAction(){
document.headerfom.action="dosessionout.action";
document.headerfom.submit();
  
   }
</script>