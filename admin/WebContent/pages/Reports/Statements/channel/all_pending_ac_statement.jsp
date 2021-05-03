<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<link rel="icon" href="images/t.png" />

<script type="text/javascript" src="scripts/accountStatement.js"></script>
<script type="text/javascript" src="scripts/accountStatement2.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script>

  $(document).ready(function(){
	  
	    var now = new Date();
	    var mm=now.getMonth() + 1;
		if(parseInt(mm)<10)
			mm='0'+mm;
		var dd=now.getDate() ;
		if(parseInt(dd)<10)
			dd='0'+dd;	
		
    var ytoday = now.getFullYear() + '-' + mm + '-' + dd;
	var today = now.getFullYear()  + '-' + mm + '-' + dd;
	 $('#datepicker').val(ytoday);
	$('#datepickers').val(today);
	$('#datepickers1').val(today);
	  
	  $(".act_type").change(function(){
		  
		  var val =  $(".act_type").val();
		  
		  if(val == "select")
		  {
			  $("#view_btn").css("display","");
		  }
		  
		   if(val == "adminUser")
		  {
			  $("#view_btn").css("display","");
		  }
		  
		   if(val == "adminUserdetail")
		  {
			  $("#view_btn").css("display","none");
		  }
		  
		  
		   if(val == "mds")
		  {
			  $("#view_btn").css("display","");
		  }
		  
		   if(val == "mdsdetail")
		  {
			  $("#view_btn").css("display","none");
		  }
		  
		  
		   if(val == "ds")
		  {
			  $("#view_btn").css("display","");
		  }
		  
		   if(val == "dsdetail")
		  {
			  $("#view_btn").css("display","none");
		  }
		  
		   if(val == "agent")
		  {
			  $("#view_btn").css("display","");
		  }
		  
		   if(val == "agentDetail")
		  {
			  $("#view_btn").css("display","none");
		  }
		  
		  
		  })
	  
	  
	  
	  
	   $("#datepicker, #datepickers").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
            numberOfMonths: 1,
			defaultDate: "+1w",
            maxDate: "0",
			
           onSelect: function( selectedDate ) 
		   {
				 
             
				if(this.id == 'datepicker')
				{
				  var dateMin = $('#datepicker').datepicker("getDate");
				  
				  var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
				  var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()+14); // Max Date = Selected + 31d
				  $('#datepickers').datepicker("option","minDate",rMin);
				  $('#datepickers').datepicker("option","maxDate",rMax); 
			 
			                    
				}
		           
            }
			

        })
		
		$("#datepickers1").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
		})
		  
	});
		  
  
  function instantBillPayStatusCheck(tid){
		$('#result').text('');
		$.ajax({
		    type: "GET",
		    url: "commonAction.action?param=InstantBillpayStatusCheck&REQUEST_ID="+tid,
		   	success: function (response) {
			if(response==null)
			{
				alert("Status not found");
				return ;
			}else{
				$('#result').text(response);
			}
				

		},

		 failure: function (response) {

		 }
		});

	}
  
  </script>

<%
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}
String flag=(String) request.getAttribute("flag");
if(flag==null)flag="N";

int size=0;

%>

</head>
<body>


<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">Statement > Channel</td>
                    </tr>
                  
                    <tr>
                      <td colspan="4">
                      <form name="myForm" method="post">   
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom:20px;">
                           <tr ><td width="20px;" align="center" class="dyn_mgs"><%=message%></td></tr>
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                <tr>
                                  <td width="35%"  align="left"  height="30"><strong>Statement Type</strong></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="53%" align="left"><select name="accountType" class="act_type" id="riskID" onchange="toggleSubmit1(this)">
                                     <!--  <option value="adminUser">Admin Account Statement</option>
                                      <option value="mds">MD Account Statement</option>
									  <option value="dsdetail">Distributor Account Statement - Detailed</option> -->
                                      <option selected="selected" value="agent">Agent Account Statement</option>
                                    </select></td>
                                </tr>
                                   <tr>
                                  	<td colspan="3">
									<div id="c12" >
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">                               
                                      
                                      <tr>
                            
										<td  align="left" width="35%" ><strong>From Date</strong></td>
									   <td  align="center" width="12%" >:</td>
									   <td width="53%"><input type="text" name="fromDate"  readonly="readonly"  id="datepicker" />
                              			</td>
                                	</tr>   
                     
                                       <tr>
                            
									  	<td   align="left"><strong>To Date</strong></td>
									   	<td  align="center">:</td>
									   	<td >
							
										 <%if(userId.equalsIgnoreCase("5101")) {%>
											<input type="text" name="toDate" readonly="readonly"    id="datepickers1" />
										 <%}else{ %> 
											<input type="text" name="toDate" readonly="readonly"    id="datepickers" />
										 <%} %>
									 
									 	</td>
                                		</tr>                                                                   
                                                                        
                                        
                                      </table>
                                    </div>
									</td>                                                               
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c0" style="display:none"></div></td>
                                </tr>
                                
                                <tr>
                                  <td></td>
                                  <td></td>
                                  <td height="50" align="left" valign="middle">
                                    <input name="Submit"  type="button" value="View" class="cls_btn" onclick="submitForm()" id="view_btn" />
                                    </td>
                                </tr>
                                
                                <tr>
                                  <td colspan="3" style="border: 1px solid #ccc;padding: 10px;"><div id="result" style="font-size: 16px;font-weight: bold;"></div></td>
                                </tr>
                                
                              </table></td>
                          </tr>
                        </table>
                        </form>
                        </td>
                    </tr>
                    <tr>
                      <td colspan="4">
                
              <% if(flag.equalsIgnoreCase("Y"))
			  { 
			  
			  String showService=(String) request.getAttribute("showService");
				if(showService==null)showService="NA";
              
            	  ArrayList RechargeData =(ArrayList)request.getAttribute("RechargeData");
            	  if (RechargeData==null)
				  {
            	  	RechargeData=new ArrayList();
            	  }
            	  int RechargeDataSize=RechargeData.size();
              
              				if(RechargeDataSize>0)
							{
           							%>
                      <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls"  id="mytables" style="margin-bottom:20px;">
                                        <tbody>
                                        
                                         <!--Admin tr start-->
                                          
                                               <!--Agent tr start-->
                                                <%if(showService.equalsIgnoreCase("Agent")){%>
                                        <tr class="hd tabulardata" align="center">
                                            <td align="center" width="10%"><strong>User ID</strong></td>
                                            <td   align="center" width="10%"><strong>Txn Date</strong></td>
                                            <td   align="center" width="10%"><strong>Txn Time</strong></td>
                                            <td  align="center" width="10%"><strong>Service</strong></td>
                                            <td  align="center" width="10%" ><strong>Mobile</strong></td>
                                            <td  align="center" width="12%" ><strong>Operator</strong></td>
                                            <td  align="center" width="12%"><strong>Txn No</strong></td>
                                            <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("SuperAdmin")||loginUserType.equalsIgnoreCase("activityUser")){ %>	                                            
	                                            <td width="10%" align="center" style="color:#FFF;border-right:1px solid #930;">Api Provider</td>
	                                        <%} %>
                                    		<td  align="center" width="10%"><strong>Txn Amount</strong></td>
                                            <td  align="center" width="5%" ><strong>Action</strong></td>
                                            <td  align="center" width="10%" ><strong>Txn Status </strong></td>
                                            <td  align="center" width="10%" ><strong>Action </strong></td>
                                          	<td  align="center" width="10%" ><strong>Live Status </strong></td>
                                          </tr>
                                           <%
										  }%>
                                           <!--Agent tr stop-->
                            
                                        <%
										for(int i=0;i<RechargeDataSize;i++)
											{
											HashMap map=(HashMap)RechargeData.get(i);
											String service=(String)map.get("service");
											String ApiProvider=(String)map.get("ApiProvider");
											%>
                                        
                                            <tr class="colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center"><%=map.get("agentId") %></td>
                                            <td  align="center"><%=map.get("dor") %></td>
                                            <td  align="center"><%=map.get("time") %></td>
                                            <td   align="center"><%=service %></td>
                                            <td  align="center"><%=map.get("mobile_number") %></td>
                                            <td  align="center"><%=map.get("Operator") %></td>
                                            <td  align="center"><%=map.get("tran_no") %></td>
                                            <%if(loginUserType.equalsIgnoreCase("activityAdmin")||loginUserType.equalsIgnoreCase("SuperAdmin")||loginUserType.equalsIgnoreCase("activityUser")){ %>	                                            
	                                            <td align="center" ><%=ApiProvider %></td>
	                                        <%} %>
                                            <td  align="center"><%=map.get("amt") %></td>
                                            <td  align="center"><%=map.get("action") %></td>
                                            <td  align="center"><%=map.get("transtatus") %></td>
                                            <td  align="center"><a target="_blank" class="cls_btn" href="Refund.action?param=getTranByTranNo&Tran_no=<%=map.get("tran_no") %>"><input name="Submit" class="cls_btn" type="button" value="Proceed" /></a></td>
                                            <td>
                                            <%if("liveBillpay".equalsIgnoreCase(service) && "InstantPay".equalsIgnoreCase(ApiProvider)){ %>
                                            	<input name="Submit" class="cls_btn" onclick="instantBillPayStatusCheck('<%=map.get("tranid") %>')" type="button" value="Check Status" />
                                            <%} else{%>
                                            
                                            <%} %>
                                            </td>
                                          </tr>
                                          <%}%>
                                          
									      </tbody>
                                      </table>
                      <%}} %>
                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="100px"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
// code for risk dropdown 

function submitForm(){
var service=document.myForm.accountType.value;
if(service=="select"){
	alert("Select the value Report Of");
	document.myForm.accountType.focus();
	return false;
}
else{
document.myForm.action="PendingAccountStatement.action";
document.myForm.submit();
}
}



/* function submitFormDownload(){
var service=document.myForm.accountType.value;
if(service=="select"){
	alert("Select the value Report Of");
	document.myForm.accountType.focus();
	return false;
}
else{
document.myForm.action="DownloadAccountStatement.action";
document.myForm.submit();
}
} */

function toggleSubmit4(obj){

        count=0
        while(document.getElementById("b"+count)){
        document.getElementById("b"+count).style.display="none"
        count++
        }
        document.getElementById("b"+obj.selectedIndex).style.display="block"

}

function toggleSubmit1(obj){

        count=0
        while(document.getElementById("c"+count)){
        document.getElementById("c"+count).style.display="none"
        count++
        }
        document.getElementById("c"+obj.selectedIndex).style.display="block";
        document.getElementById("c12").style.display="";
        

}

function toggleSubmit(obj){

        count=0
        while(document.getElementById("d"+count)){
        document.getElementById("d"+count).style.display="none"
        count++
        }
        document.getElementById("d"+obj.selectedIndex).style.display="block"

}

function toggleSubmit3(obj){

        count=0
        while(document.getElementById("e"+count)){
        document.getElementById("e"+count).style.display="none"
        count++
        }
        document.getElementById("e"+obj.selectedIndex).style.display="block"

}

function toggleSubmit2(obj){

        count=0
        while(document.getElementById("f"+count)){
        document.getElementById("f"+count).style.display="none"
        count++
        }
        document.getElementById("f"+obj.selectedIndex).style.display="block"

}

function toggleSubmit4(obj){

        count=0
        while(document.getElementById("g"+count)){
        document.getElementById("g"+count).style.display="none"
        count++
        }
        document.getElementById("g"+obj.selectedIndex).style.display="block"

}
</script>
