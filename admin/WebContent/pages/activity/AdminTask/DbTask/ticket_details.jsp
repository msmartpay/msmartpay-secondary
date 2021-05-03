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
    var ytoday = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + (now.getDate());
	var today = now.getFullYear()  + '-' + (now.getMonth() + 1) + '-' + now.getDate();
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
		  </script>

<%
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

ArrayList<HashMap<String,String>> ticketData=(ArrayList<HashMap<String,String>>)request.getAttribute("ticketData");
int size=-1;
if(ticketData!=null && ticketData.size()>0)
	size=ticketData.size();



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
                      <td  height="40" align="left" valign="middle" class="heading_mgs">Activity > Channel Tickets</td>
                    </tr>
                  
                    <tr>
                      <td colspan="4">
                      <form name="myForm" method="post" action="dbTask.action?param=viewTicketByDate">   
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom:20px;">
                           <tr ><td width="20px;" align="center" class="dyn_mgs"><%=message%></td></tr>
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                   <tr>
                                  	<td colspan="3">
									<div >
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
							
											<input type="text" name="toDate" readonly="readonly"    id="datepickers1" />
									 
									 	</td>
                                		</tr> 
                                		
                                		<tr>
                            
									  	<td   align="left"><strong>Ticket Id</strong></td>
									   	<td  align="center">:</td>
									   	<td >
							
											<input type="text" name="ticketId" placeholder="Optional" />
									 
									 	</td>
                                		</tr>                                                                  
                                                                        
                                        <tr>
		                                  <td></td>
		                                  <td></td>
		                                  <td height="50" align="left" valign="middle">
		                                    <input name="Submit"  type="submit" value="View Tickets" class="cls_btn" id="view_btn" />
		                                    </td>
		                                </tr>
                                      </table>
                                    </div>
									</td>                                                               
                                </tr>
								
                                
                              </table></td>
                          </tr>
                        </table>
                        </form>
                        </td>
                    </tr>
                    <tr>
                      <td colspan="4">
                
              <% if(size>0)
			  {%>
            	  
            	 <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls"  id="mytables" style="margin-bottom:20px;">
					<tbody>
					                                        
					
					<tr class="hd tabulardata" align="center" >
					<td width="7%" align="center" width="20%"><strong>Sl. No.</strong></td>
					<td width="9%"   align="center" width="70%"><strong>Ticket Id</strong></td>
					<td width="7%"   align="center"><strong>Opened Date</strong></td>
					<td width="8%"  align="center" width="20%"><strong>Opened Time</strong></td>
					<td width="7%"   align="center"><strong>Closed Date</strong></td>
					<td width="8%"  align="center" width="70%"><strong>Txn ID</strong></td>
					<td width="8%"  align="center" width="20%"><strong>Status</strong></td>
					<td width="9%"  align="center" width="8%"><strong>Query</strong></td>
					<td width="7%"  align="center" width="20%" ><strong>Solution</strong></td>
					<td width="7%"  align="center" width="20%" ><strong>Action</strong></td>
					
					
					</tr>
					<%
					
						for(int i=0;i<size;i++)
						{
							HashMap<String,String> map=(HashMap<String,String>)ticketData.get(i);
							%>
							<form name="updateTicket" method="post" action="dbTask.action?param=updateTicket">
							<tr class="colr" align="center" style="background:#fff;height:25px;">
								<td  align="center"><%=i+1 %></td>
								<td  align="center"><%=map.get("Ticket_id") %></td>
								<td  align="center"><%=map.get("Opened_Date") %></td>
								<td   align="center"><%=map.get("Opened_Time") %></td>
								<td   align="center"><%=map.get("Closed_Date") %></td>
								<td  align="center"><%=map.get("Txn_id") %></td>
								<td  align="center"><%=map.get("Status") %></td>
								<td   align="center"><%=map.get("Query_Message") %></td>
								<td  align="center">
								<%if(map.get("Solution_Message")!=null && !map.get("Solution_Message").equalsIgnoreCase("NA")){%>
								
								<%=	map.get("Solution_Message")%>
								
								<%}else{%>
									<textarea name="solution" rows="2" cols="" style="width: 120px"></textarea>
									<input type="hidden" name="ticketId" value="<%=map.get("Ticket_id")%>" />
								<%} %>
								</td>
								<td   align="center"><input type="submit" value="Close Ticket" /></td>
							
						  	</tr>
						  	</form>
						 <%}%>
					                                          
					</tbody>
					</table>
            	  
			  <%} %>
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
