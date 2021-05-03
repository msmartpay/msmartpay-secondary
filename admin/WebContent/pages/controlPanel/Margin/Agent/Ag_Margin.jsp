<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<!--<SCRIPT type="text/javascript" src="scripts/digitonly.js"></script>-->
<script type="text/javascript" src="scripts/commissionSearch.js"></script>

<script>
$(document).ready(function(){

	$('#fill_btn').click(function(){

		$('.wl_text').val($('#fill_text').val());     
	       
	}); 
	
});

function setComm(j)
{
var value1=document.getElementById('wl'+j).value;
if(value1>100)
{
document.getElementById('wl'+j).value=100;
document.getElementById('md'+j).value=0;
alert('Total persenteg can not be gratter than 100');
return false;
}
var value2=100 - parseInt(value1);
document.getElementById('md'+j).value=value2;
}

</script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 <script language="javascript">
function viewDetails(){
	var agentId=document.update.userId.value;
	var operator=document.update.operator.value;
	if(agentId!='' && operator!=''){
		document.update.action="AgentMergin.action?param=viewAgentShare&agentId="+agentId+"&operator="+operator;
		document.update.submit();
	}else{
		alert('Please enter agent Id and select Operator');
		document.update.EnterUserId.focus();
	}
	
}
</script>
<script>
function sbmt()
{
	if(confirm('Do you want to proceed.')){
		return true;
	}else{
		return false;
	}
}
function check(){
	var allcheck = document.updatePricing.checkAll;
	//alert(allcheck)
	if(allcheck.checked == true){
	for(i=0; i<document.updatePricing.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.updatePricing.elements[i].type=="checkbox" && document.updatePricing.elements[i].name!="checkAll")
			{
				document.updatePricing.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.updatePricing.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.updatePricing.elements[i].type=="checkbox" && document.updatePricing.elements[i].name != "checkAll" )
			{
				document.updatePricing.elements[i].checked=false;
			}
			
		}
	}
}

function checkMargin(i)
{
var count=0;
for(var j=0;j<i;j++)
{	
	var ck=document.getElementById("chek"+j).checked;

	
	if(ck == true)
	{  
		count=count+1;
	}
}
if(count==0)
{
	alert("Select check box for update margin");
	return false;
}
document.update.submit();

}
</script>



</head>
<%
//ArrayList CategoryName=(ArrayList) session.getAttribute("CategoryName");
//ArrayList MainServiceName=(ArrayList) session.getAttribute("MainServiceName");
//ArrayList SubServiceName=(ArrayList) session.getAttribute("SubServiceName");
ArrayList SKUData=(ArrayList) request.getAttribute("getDetails");
//System.out.println("SKUData  :"+SKUData);
int sizeOfSkuData=0;
if(SKUData!=null){sizeOfSkuData=SKUData.size();}
else{sizeOfSkuData=-1;}
System.out.println("sizeOfSkuData is "+sizeOfSkuData);
String Displaycategory=(String)request.getAttribute("Category");
//String DisplaymainService=(String)request.getAttribute("DisplaymainService");
String DisplayMDSid=(String)request.getAttribute("MDSid");
String DisplayClientID=(String)request.getAttribute("ClientID");

String message=(String)request.getAttribute("message");
if(message==null)message="";
%>


<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
</tr>

<tr>
  <td  align="center" width="100%" valign="top">
  <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
      <tr>
        <td valign="top" align="center" class="rounded-corners" >
        
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td  height="40" align="left" valign="middle" class="heading_mgs">
              Margin Set > Agent
              </td>
            </tr>
            
			<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
			<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                   	
			 <tr>
              <td colspan="4">
              
             <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom: 25px;">
                  <tr>
                    <td align="center">
                    <form name="update" method="post" action="#" onsubmit="return viewDetails();">
					  
					<table width="90%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					   
					 <tr >
                      <td width="20%" height="25px"></td>
					  <td width="25%"></td>
					  <td width="8%"></td>
					  <td width="30%"></td>
                      </tr>
					  <tr>
                      <td  ></td>
					  <td ><strong>User Type</strong></td>
					  <td >:</td>
					      <td >
                          <select name="usertype" required >
                          
                              <option value="">Select</option>
                              <!-- <option value="ByClientId">Client ID</option> -->
                              <option value="ByMdId">MDS ID</option>
                              <option value="ByDsId">DS ID</option>
                              <option value="ByAgentId">Agent ID</option>
                              
                          </select>
                          
						  </td>
                      </tr>
                      <tr>
                         <td  height="36"></td>
                          <td><strong>User ID</strong></td>
                          <td>:</td>
                          <td>
                         <input type="text" name="userId" required placeholder="MDUP4001 or DSUP33273001, 10001"  />
                         </td>
                      </tr>
                      <tr>
                         <td  height="29"></td>
                          <td valign="middle"><strong>Select Operator</strong></td>
                          <td valign="middle">:</td>
                          <td valign="middle">
                          
                          <select name="operator" required>
                          	<option   selected="selected" value="">Select Service</option>
                          	<c:forEach var="service" items="${sessionScope.ServiceList }">
                          		<option   value="${service }">${service }</option>
                          	</c:forEach>
                          	
                          	
                          </select>
                         </td>
                         <td  align="left"></td>
                      </tr>
                      <tr align="center">
                        <td  height="35" ></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="submit"  id="View" value="View" class="cls_btn" />
                        </td>
                        </tr>
                      </table>
                     </form>
                     
					  </td>
                  </tr>
                  <tr>
                         <td align="center" width="100%">
                            <c:if test="${requestScope.MarginDetails!=null && requestScope.MarginDetails.size()>0 }">
                            	<form name="updatePricing" method="post" action="AgentMergin.action?param=UpdateAgentShare" onsubmit="return checkMargin(${requestScope.MarginDetails.size()});">
                            	<table width="90%"  cellspacing="0" cellpadding="0" align="center" class="tbls"   bgcolor="#a74312">
                        		
                        		<tr class="hd tabulardata" align="center">
						
								  <td width="5%" >
								  <input type="hidden" value="${requestScope.usertype }" name="usertype" />
								  <input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></td>
								  <td width="5%" ><strong>UserId</strong></td>
								  <td width="5%" ><strong>Start Range<br/>End Range</strong></td>
								  <td width="5%" ><strong>Operator</strong></td>
		                          <td width="5%" ><strong>Agent Charge Type<br/>Agent Charge</strong></td>
		                          <td width="5%" ><strong>Agent Commission Type<br/>Agent Commission</strong></td>
                        		</tr>
                        		<c:forEach var="MarginDetails" items="${requestScope.MarginDetails }" varStatus="loop">
                        			<tr class="colr" align="center" style="background:#fff;height:25px;" align="center">
						
									  <td width="5%" ><input type="checkbox" name="checkpartial" id="chek${loop.index }"  value="${loop.index }"/></td>
									  <td width="5%" ><input style="width: 50px;" type="text" readonly="readonly" name="searchId${loop.index }" value="${requestScope.searchId }" /></td>
									  <td width="10%" >
									  <input style="width: 70px;" type="text" readonly="readonly" name="StartRange${loop.index }" value="${MarginDetails.StartRange }" />
									  <input style="width: 70px;" type="text" readonly="readonly" name="EndRange${loop.index }" value="${MarginDetails.EndRange }" />
									  </td>
									  <td width="10%" ><input style="width: 130px;" type="text" readonly="readonly" name="Operator${loop.index }" value="${MarginDetails.Operator }" /></td>
									  
			                          <td width="10%" >
			                          	<select style="width: 70px;" name="ChargeType${loop.index }">
                          
			                          		<option selected="selected" value="${MarginDetails.ChargeType }">${MarginDetails.ChargeType }</option>
			                          		<option value="P">P</option>
			                          		<option value="R">R</option>
			                          
			                           </select>
			                           <input style="width: 70px;" type="text" name="Charge${loop.index }" value="${MarginDetails.Charge }" />
			                          </td>
			                          <td width="10%" >
				                          	<select style="width: 70px;" name="CommissionType${loop.index }">
	                          
				                          		<option selected="selected" value="${MarginDetails.CommissionType }">${MarginDetails.CommissionType }</option>
				                          		<option value="P">P</option>
				                          		<option value="R">R</option>
				                          
				                           </select>
				                           <input type="text" style="width: 70px;" name="Commission${loop.index }" value="${MarginDetails.Commission }" />
			                          </td>
	                        		</tr>
                        		</c:forEach>
                                    <tr >
                                        <td colspan="12" align="right"><input style="color: #fff;"  type="submit" value="Update" /></td>
                                    </tr>
                      			</table>
                      		</form>
                            </c:if>
					     </td>
                   </tr>
                </table>
                                      
             </td>
            </tr>
                    
          </table>
          
          </td>
      </tr>
    </table>
    </td>
</tr>

<tr>
  <td width="100%" valign="top" align="center" height="162px">
</td>
</tr>

<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %>
</td>
</tr>

</table>
</body>
</html>
