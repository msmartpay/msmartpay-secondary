<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>

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
		
		$(".act_type").change(function(){
			  
			  var val =  $(".act_type").val();
			  
			  if(val == "select")
			  {
				  $("#view_btn").css("display","");
			  }
			  
			   if(val == "EgenASDetails")
			  {
				  $("#view_btn").css("display","none");
			  }
			   if(val == "EgenAS")
				 {
					  $("#view_btn").css("display","");
				 }
		});
		
	  
		 $("#datepicker, #datepickers").datepicker({
             changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2020',
         	numberOfMonths: 1,
			defaultDate: "+1w",
          	maxDate: "0",
			
        onSelect: function( selectedDate ) {
				 
          
			 if(this.id == 'datepicker'){
           var dateMin = $('#datepicker').datepicker("getDate");
		
           var rMin = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()); // Min Date = Selected + 1d
           var rMax = new Date(dateMin.getFullYear(), dateMin.getMonth(),dateMin.getDate()+14); // Max Date = Selected + 31d
           $('#datepickers').datepicker("option","minDate",rMin);
           $('#datepickers').datepicker("option","maxDate",rMax);  
			 
			                    
         }
		           
         }
			

     })
		  });
		  </script>

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
document.myForm.action="searchAccountStatementEGEN.action";
document.myForm.submit();
}
}



function submitFormDownload()
{
var service=document.myForm.accountType.value;
if(service=="select")
{
	alert("Select the value Report Of");
	document.myForm.accountType.focus();
	return false;
}
else
{
	document.myForm.action="DownloadAccountStatement.action";
	document.myForm.submit();
}
}






function toggleSubmit1(obj){

        count=0;
        while(document.getElementById("c"+count))
        {
        	document.getElementById("c"+count).style.display="none";
        	
        	count++;
        }
        document.getElementById("d1").style.display="none";
        document.getElementById("d2").style.display="none";
        document.getElementById("c"+obj.selectedIndex).style.display="block";
        document.getElementById("c12").style.display="";
        

}

function toggleSubmit2(obj){

		var id=document.getElementById("riskID1").value;
		if(id=='id')
			{
				document.getElementById("d1").style.display="block";
				
			}
		else
			{
				document.getElementById("d1").style.display="none";
			}
		
		document.getElementById("d2").style.display="none";

}
function toggleSubmit3(obj){

	var id=document.getElementById("riskID2").value;
	if(id=='id')
		{
			document.getElementById("d2").style.display="block";
			
		}
	else
		{
			document.getElementById("d2").style.display="none";
		}
	document.getElementById("d1").style.display="none";
    

}











</script>

<%
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}
  
			  String showService=(String) request.getAttribute("showService");
			  System.out.println("showService" +showService);
				if(showService==null)showService="NA";
				
				
              
            	  ArrayList RechargeData =(ArrayList)request.getAttribute("RechargeData");
            	  if (RechargeData==null)
				  {
            	  	RechargeData=new ArrayList();
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
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">Statement > API</td>
                    </tr>
                   
                    <tr>
                      <td colspan="4">
                      <form name="myForm" method="post">
					  
					  <table width="86%"  cellspacing="0" cellpadding="0" align="center"  id="border" style="margin-bottom:25px;">
                      <tr><td width="20px" align="center" class="dyn_mgs"><%=message%></td>
                      </tr>
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <table width="58%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                <tr>
                                  <td width="30%"  align="left"  height="30"><strong>Statement Type</strong></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="58%" align="left"><select class="act_type" name="accountType" id="riskID" onchange="toggleSubmit1(this)">
                                      <option value="select">Select</option>
                                      <option value="EgenAS">API Account Statement</option>
									  <!-- <option value="EgenASDetails">EGEN Account Statement Detailed</option> -->
                                      
                                    </select></td>
                                </tr>
                                   <tr>
                                  <td colspan="3"><div id="c12" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">                               

                                      <tr>
                            
                          <td width="30%" align="left" ><strong>From Date</strong></td>
                           <td width="12%" align="center">:</td>
                           <td width="58%" align="left"><input type="text" name="fromDate"  readonly="readonly"  id="datepicker" />                              </td>
                                </tr>   
                                       
                                       <tr>
                            
                          <td width="30%" align="left" ><strong>To Date</strong></td>
                           <td width="12%" align="center">:</td>
                           <td width="58%" align="left">
                              <input type="text" name="toDate" readonly="readonly"   id="datepickers" /></td>
                                </tr>                                                                   
                                      </table>
                                    </div></td>                                                               
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c0" style="display:none"></div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c1" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="30%"   align="left" height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td  width="58%" align="left">
                                          <select  class="style2" id="riskID1" name="stmt" onchange="toggleSubmit2(this)">
                                              
                                              <option value="all" selected="selected">All</option>
											  <option style="display:none;"></option>
                                              <option value="id">ID-Wise</option>
                                          </select>											</td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d1" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="30%"  align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="58%" align="left"><input name="adminId" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
								<tr>
                                  <td colspan="3"><div id="c2" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="30%"  align="left"  height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td width="58%" align="left"><select  class="style2" id="riskID2" name="stmt" onchange="toggleSubmit3(this)">
                                            	<option value="all" selected="selected">All</option>
											  	<option style="display:none;"></option>
                                              	<option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="30%"  align="left"  height="30"><strong>Select</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="58%" align="left"><input name="adminIddet" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                <tr>
                                	<td align="center">&nbsp;</td>
                                	<td align="center">&nbsp;</td>
                                    <td>
                                    	<input name="Submit2"  type="button" value="View" class="cls_btn" onclick="submitForm()" id="view_btn" />
                                        <input name="Submit" type="button" value="Download" class="cls_btn" onclick="submitFormDownload()" />
                                	</td>
										
								</tr>
                              </table>                            	
									  </td>
                          </tr>
                                <tr>
								
                                  <td height="50" align="center"></td>
                               
								</tr>
                        </table>
					  
					  </form>
                       </td>
                    </tr>
                    <tr>
                      <td colspan="4">
                
              <% 
			
            	  int RechargeDataSize=RechargeData.size();
              
              				if(RechargeDataSize>0)
							{
                                          
											
										%>
                      <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style="margin-bottom:25px;margin-top:25px;">
                                        <tbody>
                                        
                                         <!--Admin tr start-->
                                          <%if(showService.equalsIgnoreCase("EGEN")){%>
                                        <tr class="hd" align="center" style="background:#a74312; height:30px;">
                                           <td width="4%" height="25" align="center"><strong>S.N.</strong></td>
                                           <td width="8%" align="center"><strong>API ID</strong></td>
                                            <td width="8%" align="center"><strong>Txn Date</strong></td>
                                            <td width="7%" align="center"><strong>Txn Time</strong></td>
                                            <td width="9%" align="center"><strong>Txn ID</strong></td>
                                            <td width="9%" align="center"><strong>Service</strong></td>
                                            <td width="10%" align="center"><strong>Txn Amount</strong></td>
                                            <td width="9%" align="center"><strong>Commission</strong> </td>
                                            <td width="7%" align="center"><strong>Charges</strong> </td>
                                            <td width="9%" align="center"><strong>Net Amount</strong></td>
                                            <td width="9%" align="center"><strong>Action</strong></td>
                                            <td width="11%" align="center"><strong>Txn Status</strong> </td>
                                            </tr>
  
                                       <%}
										for(int i=0;i<RechargeDataSize;i++)
											{
											HashMap map=(HashMap)RechargeData.get(i);
											//System.out.println("map is ::"+map);
											%>
                                        
                                          <tr class="colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center"><%=(i+1)%></td>
                                            <td  align="center"><%=map.get("APIagentId") %></td>
                                            <td  align="center"><%=map.get("date") %></td>
                                            <td   align="center"><%=map.get("transactiontime") %></td>
                                            <td  align="center"><%=map.get("Corporate_Transaction_Id") %></td>
                                            <td  align="center"><%=map.get("Service") %></td>
                                            <td   align="center"><%=map.get("Tran_Amount") %></td>
                                            <td  align="center"><%=map.get("Commission") %></td>
                                            <td  align="center"><%=map.get("Service_Charges") %></td>
                                            <td  align="center"><%=map.get("Net_Tran_Amt") %></td>
                                            <td  align="center"><%=map.get("Action_On_Bal_Amt") %></td>
                                            <td align="center" style="border-right:1px solid #930;"><%=map.get("Tran_Status") %></td>
                                          </tr>
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
    <td width="100%" valign="top" align="center" height="70px"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
