
<!DOCTYPE html>
<html class="not-ie no-js" lang="en">  
<head>
<%@ include file="/globaldata.jsp"%>
<%@ page import = "java.util.ArrayList "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 

<%
		System.out.println("we are into this page");
		DecimalFormat dff = new DecimalFormat("##.00");
	
		String data=null;
		
		String pageno=null;
		String maxpagestr=null;
		Integer maxpage=0;
		String	AgentCurrentStatus =null;
	
	  
	   if(AgentCurrentStatus==null){
					  AgentCurrentStatus =(String)session.getAttribute("AgentCurrentStatus");
				  }
				  session.setAttribute("AgentCurrentStatus", AgentCurrentStatus);
		
	
		pageno=(String)request.getAttribute("page");
		if(pageno==null)
		pageno="0";
		int size=0;
		int i=0;
		ArrayList newAgents=(ArrayList)request.getAttribute("newAgents");
			// size=newAgents.size();
			 if (newAgents==null)
			 {
				 size=-1;
				  data="NO DATA";
			 }
			 else{
	            size=newAgents.size();
				session.setAttribute("newAgents",newAgents);
			 } 
			
String titlePage=(String)session.getAttribute("titlePage");
String message=(String)request.getAttribute("message");

if(message==null){
message="";
}
//ArrayList newAgents=(ArrayList)request.getAttribute("newAgents");
%>
<script  type="text/javascript" language="javascript">

function check()
{
	var allcheck = document.activateAgentForm.checkAll;
	
	if(allcheck.checked == true)
	{
		for(i=0; i<document.activateAgentForm.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.activateAgentForm.elements[i].type=="checkbox" && document.activateAgentForm.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
			{
				document.activateAgentForm.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.activateAgentForm.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.activateAgentForm.elements[i].type=="checkbox" && document.activateAgentForm.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
			{
				document.activateAgentForm.elements[i].checked=false;
			}
			
		}
	}
}

function doDeactiveAgent()
{
var c=document.activateAgentForm.checkpartial.value;

var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked)
 {
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}

if (boxesTicked1 <1 ) 
{
	alert("Please Select Atleast One Agent");
//document.getElementById('bold1').innerHTML =i;
return boxesTicked1;
}
else
{
document.activateAgentForm.action="doAgentDetails.action?param=changeStatusAgent&checkChangeStatus=Deactivate";
document.activateAgentForm.submit();
}

}

function doActiveAgent()
{
var c=document.activateAgentForm.checkpartial.value;

var boxesTicked1 = "";

for (i = document.getElementsByName('checkpartial').length - 1; i >= 0; i--)
{
if (document.getElementsByName('checkpartial')[i].checked)
 {
boxesTicked1 = boxesTicked1 + document.getElementsByName('checkpartial')[i].value + "\n"
}
}

if (boxesTicked1 <1 ) 
{
	alert("Please Select Atleast One Agent");
//document.getElementById('bold1').innerHTML =i;
return boxesTicked1;
}
else
{
document.activateAgentForm.action="doAgentDetails.action?param=changeStatusAgent&checkChangeStatus=Activate";
document.activateAgentForm.submit();
}

}

function SubmitForm(){
var searchBy=document.getElementById("searchBy").value;

if(searchBy=="1"){
	//document.getElementById("deactive_btn").style.display="block";
	//document.getElementById("active_btn").style.display="none";
document.activateAgentForm.action="doAgentDetails.action?param=AgentDetails&checkStatus=Activate";
document.activateAgentForm.submit();
return true;
}
if(searchBy=="2"){
	//document.getElementById("active_btn").style.display="block";
	//document.getElementById("deactive_btn").style.display="none";
document.activateAgentForm.action="doAgentDetails.action?param=AgentDetails&checkStatus=Deactive";
document.activateAgentForm.submit();
return true;

}
if(searchBy=="3"){
	//document.getElementById("deactive_btn").style.display="block";
	//document.getElementById("active_btn").style.display="block";
document.activateAgentForm.action="doAgentDetails.action?param=AgentDetails&checkStatus=All";
document.activateAgentForm.submit();
return true;
}
}

</script>

	<!-- Basic Page Needs
	================================================== -->
	<meta charset="utf-8">
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
	
	
	
	<!-- CSS
	================================================== -->
	<!-- Base + Vendors CSS -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/fonts/font-awesome/css/font-awesome.css">
	<link rel="stylesheet" href="css/fonts/entypo/css/entypo.css">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.carousel.css" media="screen">
	<link rel="stylesheet" href="vendor/owl-carousel/owl.theme.css" media="screen">
	<link rel="stylesheet" href="vendor/magnific-popup/magnific-popup.css" media="screen">

	<!-- Theme CSS-->
	<link rel="stylesheet" href="css/theme.css">
	<link rel="stylesheet" href="css/theme-elements.css">
	<link rel="stylesheet" href="css/animate.min.css">

   

  <!-- Head Libs -->
	<script src="vendor/modernizr.js"></script>

	
	<!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="images/favicons/favicon.png">
	<link rel="apple-touch-icon" sizes="120x120" href="images/favicons/favicon-120.png">
	<link rel="apple-touch-icon" sizes="152x152" href="images/favicons/favicon-152.png">
	
</head>
<body>
<%@ include file="/header.jsp" %>
	<div class="site-wrapper">

		<!-- Main -->
		<div class="main" role="main">

			<!-- Page Heading -->
			<section class="page-heading">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1>View Agent</h1>
						</div>
					</div>
				</div>
			</section>
			<!-- Page Heading / End -->

			<!-- Page Content -->
			<section class="page-content">
				<div class="container">				
					
					<div class="call-to-action centered cta__fullwidth cta__overlay cta__overlay-opacity-75 cta-overlay-color__dark cta-bg2" data-stellar-background-ratio="0.5" style="background-image: url(images/samples/bg1.jpg);">
						<div class="cta-inner">
							<div class="cta-txt">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-2">
                                        <div class="box">                                                                
                                          <form name="activateAgentForm" method="POST" id="activateAgentForm" role="form">
                                             <input type="hidden" name="d" value="changeStatusAgent" />                                                        
                                                <div class="form-group">
													<select id="searchBy" name="searchBy" onchange="return SubmitForm();" class="form-control" >
														 <option value="0">Please Select Search Type</option>
														  <option value="1">Active Agents</option>
														  <option value="2">Deactive Agents</option>
														  <option value="3">All</option>
													 
													</select>
												</div> 
                                               <div class="form-group col-md-12">
                                           																														                                                  
                                                	<button type="submit" id="active_btn" class="btn btn-primary btn-inline" onclick="doActiveAgent()">Activate</button><button id="deactive_btn" style="margin-left:2%;" type="submit" class="btn btn-primary btn-inline" onclick="doDeactiveAgent()">Deactive</button>
                                               </div>
											   
                                            </form>
                                    </div>
                                    </div>                                    
                                                                
                                </div>
                        </div>
					</div>
				</div>
               <% if (size<0 && data.equals("NO DATA") ){%>
               <div><font color="#FF0000" size="3">Data is Not Available.</font></div>
               	<% } else{%>
                <div class="spacer" style="height:50px;">
						<div class="form-group" style="width:13%;float:right;margin:10px; ">
							<label style="float:left;">Page No.</label><input readonly type="text" style="width:30%;float:left;margin-left:15%;height:30px;" class="form-control" value="<%out.print(Integer.parseInt(pageno)+1);%>">
						</div>
				</div>
                <div class="row">
                    <div class="col-md-12">
                                        <div class="table-responsive">
                                           <table class="table table-striped table-bordered">
							                   <thead>
                                                <tr>
                                                	<th><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></th>
                                                    <th>S.No.</th>
                                                    <th>Agent ID</th>
                                                    <th>Agency Name</th>
                                                    <th>Email ID </th>
                                                    <th>Mobile No.</th>                                                
                                                    <th>Current Balance</th>
                                                    <th>MPIN</th>
                                                    <th>Status</th>
                                                    <th align="center">Balance Push</th>                                                
                                                </tr>
                                                </thead>
						                        <% 
													System.out.println("we are here  be "+newAgents.size());
						                        	HashMap temp=new HashMap();
											
													for( i=0;i<size;i++)
													
													{						
														temp=(HashMap)newAgents.get(i); 
														
														maxpage=(Integer)temp.get("MaxPage");
														//System.out.println("maxpage :"+maxpage);
														maxpagestr=maxpage.toString();
														if(maxpagestr==null)
														maxpagestr="0";
														//System.out.println("newAgents"+newAgents.size());	
													
														String status=(String)temp.get("status");
													if(status.equalsIgnoreCase("Activate")){
													status="Active";
													}
													else{
													status="Deactive";
													}
													String mpin=(String)temp.get("mpin");
													if(mpin==null){
													mpin="NA";
													}
												%>
                                                <tbody>
                                                <tr>
                                                    <td><INPUT TYPE="CHECKBOX" name="checkpartial" id="a" value="<%=temp.get("AgentId")%>" /></td>
                                                    <td><%=temp.get("S.No.")%></td>
                                                    <td><%=temp.get("AgentId")%></td>
                                                    <td><%=temp.get("agentName")%></td>
                                                    <td><%=temp.get("agentEmailId")%></td>
                                                    <td><%=temp.get("mobileNo")%></td>
                                                    <td><%=temp.get("amount")%></td>
                                                    <td>XXXXXX</td>
                                                    <td><%=status%></td>
                                                    <td align="center"><a style="background-color: #ff3200;padding: 2px 15px;border-radius: 25px;color: #fff;" href="PushBalance.action?param=getAgentInfo&IpAdress=<%=request.getRemoteAddr()%>&id=<%=temp.get("AgentId")%>">Push</a></td>
                                                </tr>
                                                </tbody>
                                                <%}%>
                                           </table>
                                        </div> 
                       <table width="100%">  
						<tr>
							<td width="40%">
								<div class="form-group" >
								<a href="doAgentDetails.action?param=AgentDetails&checkStatus=<%=AgentCurrentStatus%>&page=0" style="text-decoration:none;">	
								<button type="submit" class="btn btn-primary btn-inline" name="previous" id="previous"  value="Start" <% if(pageno.equals("0"))out.print("disabled=\"disabled\""); else out.print(""); %>>Start</button>
								</a>
							</div>
							</td>
							<td width="10%">
								<div class="form-group"  >
								<a href="doAgentDetails.action?param=AgentDetails&checkStatus=<%=AgentCurrentStatus%>&page=<% out.print(Integer.parseInt(pageno)-1); %>" style="text-decoration:none;">		
								<button type="submit" class="btn btn-primary btn-inline" name="previous" id="previous" value="Previous" <% if(pageno.equals("0"))out.print("disabled=\"disabled\""); else out.print(""); %>>Previous</button>
								</a>
							</div>
							</td>
							<td width="10%">
								<div class="form-group" >
								<a href="doAgentDetails.action?param=AgentDetails&checkStatus=<%=AgentCurrentStatus%>&page=<% out.print(Integer.parseInt(pageno)+1);%>"  style="text-decoration:none;">		
								<button type="submit"  class="btn btn-primary btn-inline" name="next" id="next" value="Next" <% if(pageno.equals(maxpagestr))out.print("disabled=\"disabled\"");else  out.print("");%> >Next</button>
								</a>
							</div>
							</td>
							<td width="40%">
							<div class="form-group" style="float:right;">
								<a href="doAgentDetails.action?param=AgentDetails&checkStatus=<%=AgentCurrentStatus%>&page=<% out.print(Integer.parseInt(maxpagestr));%>"  style="text-decoration:none;">		
								<button type="submit"  class="btn btn-primary btn-inline" name="next" id="next" value="End" <% if(pageno.equals(maxpagestr))out.print("disabled=\"disabled\"");else  out.print("");%>>End</button>
								</a>
							</div>
							</td>
						</tr>					   
						</table>	
						<%}%>					
					</div>
                </div>

                
            </div>
		</section>
	<!-- Page Content / End -->
    
   <div class="spacer-xl"></div>

		</div>
		<!-- Main / End -->
	</div>
	<%@include file="/footer.jsp" %>

	<!-- Javascript Files
	================================================== -->
	<script src="vendor/jquery-1.11.0.min.js"></script>
	<script src="vendor/jquery-migrate-1.2.1.min.js"></script>
	<script src="vendor/bootstrap.js"></script>
	<script src="vendor/jquery.flexnav.min.js"></script>
	<script src="vendor/jquery.hoverIntent.minified.js"></script>
	<script src="vendor/jquery.flickrfeed.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.js"></script>
	<script src="vendor/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendor/jquery.fitvids.js"></script>
	<script src="vendor/jquery.appear.js"></script>
	<script src="vendor/jquery.stellar.min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>


	
</body>

</html>