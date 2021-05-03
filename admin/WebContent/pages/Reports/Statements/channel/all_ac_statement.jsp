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
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="newbg">
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">Statement > Channel</td>
                    </tr>
                  
                    <tr>
                      <td colspan="4">
                      <form name="myForm" method="post">   
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom:20px;">
                           <tr ><td width="20px;" align="center" class="dyn_mgs"><%=message%></td>
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                <tr>
                                  <td width="35%"  align="left"  height="30"><strong>Statement Type</strong></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="53%" align="left"><select name="accountType" class="act_type" id="riskID" onchange="toggleSubmit1(this)">
                                      <option value="select">Select</option>
                                      <option value="adminUser">Admin Account Statement</option>
									  <option value="adminUserdetail">Admin Account Statement - Detailed</option>
                                      <option value="mds">MD Account Statement</option>
									  <option value="mdsdetail">MD Account Statement - Detailed</option>
                                      <option value="ds">Distributor Account Statement</option>
									  <option value="dsdetail">Distributor Account Statement - Detailed</option>
                                      <option value="agent">Agent Account Statement</option>
									  <option value="agentDetail">Agent Account Statement - Detailed</option>
                                    </select></td>
                                </tr>
                                   <tr>
                                  	<td colspan="3">
									<div id="c12" style="display:none;">
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
							
										 <%if(userId.equalsIgnoreCase("5108") || userId.equalsIgnoreCase("5172") || userId.equalsIgnoreCase("5202")) {%>
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
                                  <td colspan="3"><div id="c1" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="35%"   align="left" height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td  width="53%" align="left"><select  class="style2" id="riskID" name="stmt" onchange="toggleSubmit(this)">
                                              
                                              <option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>
                                              <!--<option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>-->
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d1" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td  width="35%" align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="adminId" type="text" class="style2 blank_id"  /></td>
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
                                          <td width="35%"  align="left"  height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td width="53%" align="left"><select  class="style2" id="riskID" name="adminstmtdet" onchange="toggleSubmit(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All Admin Account Statement</option>
                                              <option value="id">Admin Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d1" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="d2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="35%"  align="left"  height="30"><strong>Select</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="adminIddet" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c3" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="35%"  align="left"  height="30"><strong>Select</strong></td>
                                          <td width="12%" align="center">:</td>
                                          <td width="53%" valign="middle"><select  class="style2" id="riskID" name="mdsstmt" onchange="toggleSubmit3(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All MD Account Statement</option>
                                              <option value="id">MD Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              
 											  <option style="display:none;"></option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="e0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="4"><div id="e1" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="e2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="35%"  align="left"  height="30"><strong>User Id</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="mdsId" type="text" class="style2 blank_id" /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
								<tr>
                                  <td colspan="3"><div id="c4" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td  align="left" width="35%"  height="30"><strong>Select</strong></td>
                                          <td  align="center" width="12%">:</td>
                                          <td valign="middle" width="53%"><select  class="style2" id="riskID" name="mdsstmtdet" onchange="toggleSubmit3(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All MD Account Statement</option>
                                              <option value="id">MD Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="e0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="4"><div id="e1" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="e2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td  width="35%" align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="mdsIddet" type="text" value="me" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c5" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="35%" align="left"  height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td width="53%" valign="middle"><select  class="style2" id="riskID" name="dsstmt" onchange="toggleSubmit2(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All DS Account Statement</option>
                                              <option value="id">DS Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              
                                               <option style="display:none;"></option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f1" style="display:none;"> </div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="35%"  align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="dsId" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
								<tr>
                                  <td colspan="3"><div id="c6" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td  align="left" width="35%"  height="30"><strong>Select</strong></td>
                                          <td width="12%" align="center">:</td>
                                          <td width="53%" valign="middle" ><select  class="style2" id="riskID" name="dsstmtdet" onchange="toggleSubmit2(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All DS Account Statement</option>
                                              <option value="id">DS Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f1" style="display:none;"> </div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="f2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="35%" align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%" align="center">:</td>
                                                  <td width="53%" align="left"><input name="dsIddet" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                <tr>
                                  <td colspan="3"><div id="c7" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td   align="left" width="35%"  height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td width="53%" valign="middle"><select  class="style2" id="riskID" name="agentstmt" onchange="toggleSubmit4(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All AG Account Statement</option>
                                              <option value="id">AG Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              <option style="display:none;"></option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g1" style="display:none;"> </div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td width="35%"  align="left" height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left"><input name="agentId" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
								<tr>
                                  <td colspan="3"><div id="c8" style="display:none;">
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                        <tr>
                                          <td width="35%"  align="left" height="30"><strong>Select</strong></td>
                                          <td width="12%"  align="center">:</td>
                                          <td width="53%" valign="middle"><select  class="style2" id="riskID" name="agentstmtdet" onchange="toggleSubmit4(this)">
                                              <!--<option value="select">Select</option>
                                              <option value="all">All AG Account Statement</option>
                                              <option value="id">AG Account Statement by ID</option>-->
                                              <option value="all" selected="selected">All</option>
                                              <option value="id">ID-Wise</option>
                                            </select></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g0" style="display:none;"></div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g1" style="display:none;"> </div></td>
                                        </tr>
                                        <tr>
                                          <td colspan="3"><div id="g2" style="display:none;">
                                              <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                                                <tr>
                                                  <td  width="35%" align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%" align="center">:</td>
                                                  <td width="53%" align="left"><input name="agentIddet" type="text" class="style2 blank_id"  /></td>
                                                </tr>
                                              </table>
                                            </div></td>
                                        </tr>
                                      </table>
                                    </div></td>
                                </tr>
                                <tr>
                                  <td></td>
                                  <td></td>
                                  <td height="50" align="left" valign="middle">
                                    <input name="Submit"  type="button" value="View" class="cls_btn" onclick="submitForm()" id="view_btn" />
                                    <input name="Submit" type="button" value="Download" class="cls_btn" onclick="submitFormDownload()" />
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
                                          <%if(showService.equalsIgnoreCase("Admin")){%>
                                        <tr class="hd tabulardata" align="center" >
                                            <td width="7%" align="center" width="20%"><strong>User ID</strong></td>
                                            <td width="9%"   align="center" width="70%"><strong>Txn Date</strong></td>
                                            <td width="7%"   align="center"><strong>Txn Time</strong></td>
                                            <td width="8%"  align="center" width="20%"><strong>Service</strong></td>
                                            <td width="16%"  align="center" width="70%"><strong>Txn ID</strong></td>
                                    		<td width="10%"  align="center" width="20%"><strong>Txn Amount</strong></td>
                                            <td width="9%"  align="center" width="8%"><strong>Commission</strong></td>
                                            <td width="7%"  align="center" width="20%" ><strong>Charges</strong></td>
											<td width="9%"   align="center" width="12%"><strong>Net Amount </strong></td>
                                            <td width="10%"  align="center" width="20%" ><strong>User Type</strong></td>
                                            <td width="8%"  align="center" width="20%" ><strong>Txn Status </strong></td>
                                            <td width="8%"  align="center" width="20%" ><strong>Remark </strong></td>
                                          
                                          </tr>
                                          <%
										  }%>
                                           <!--Admin tr stop-->
                                           
                                            <!--MD tr start-->
                                            
                                             <%if(showService.equalsIgnoreCase("MD")){%>
                                        <tr class="hd tabulardata" align="center" >
                                            <td align="center" width="20%"><strong>User ID</strong></td>
                                            <td   align="center" width="20%"><strong>Txn Date</strong></td>
                                            <td   align="center"><strong>Txn Time</strong></td>
                                            <td  align="center" width="20%"><strong>Service</strong></td>
                                            <td  align="center" width="60%"><strong>Txn ID</strong></td>
                                    		<td  align="center" width="20%"><strong>Txn Amount</strong></td>
                                            <td  align="center" width="8%"><strong>Commission</strong></td>
                                            <td  align="center" width="20%"><strong>Charges</strong></td>
											<td  align="center" width="20%" ><strong>Net Amount </strong></td>
                                            <td  align="center" width="20%" ><strong>Action</strong></td>
                                            <td   align="center" width="20%"><strong>Txn Status </strong></td>
                                            <td width="8%"  align="center" width="20%" ><strong>Remark </strong></td>
                                          </tr>
                                           <%
										  }%>
                                           <!--MD tr stop-->
                                           
                                           
                                            <!--DS tr start-->
                                             <%if(showService.equalsIgnoreCase("DS")){%>
                                        <tr class="hd tabulardata" align="center" >
                                            <td align="center" width="20%"><strong>User ID</strong></td>
                                            <td   align="center" width="30%"><strong>Txn Date</strong></td>
                                            <td   align="center" width="20%"><strong>Txn Time</strong></td>
                                            <td  align="center" width="20%"><strong>Service</strong></td>
                                            <td  align="center" width="60%"><strong>Txn ID</strong></td>
                                    		<td  align="center" width="20%"><strong>Txn Amount</strong></td>
                                            <td  align="center" width="8%"><strong>Commission</strong></td>
                                            <td  align="center" width="20%" ><strong>Charges</strong></td>
											<td  align="center" width="20%" ><strong>Net Amount</strong></td>
                                            <td  align="center" width="20%" ><strong>Action</strong></td>
                                            <td  align="center" width="20%" ><strong>Txn Status </strong></td>
                                          	<td width="8%"  align="center" width="20%" ><strong>Remark </strong></td>
                                          </tr>
                                           <%
										  }%>
                                           <!--DS tr stop-->
                                           
                                               <!--Agent tr start-->
                                                <%if(showService.equalsIgnoreCase("Agent")){%>
                                        <tr class="hd tabulardata" align="center" >
                                            <td align="center" width="20%"><strong>User ID</strong></td>
                                            <td   align="center" width="20%"><strong>Txn Date</strong></td>
                                            <td   align="center"><strong>Txn Time</strong></td>
                                            <td  align="center" width="20%"><strong>Service</strong></td>
                                            <td  align="center" width="500%"><strong>Txn ID</strong></td>
                                    		<td  align="center" width="20%"><strong>Txn Amount</strong></td>
                                            <td  align="center" width="20%"><strong>Commission</strong></td>
                                            <td  align="center" width="20%" ><strong>Fee</strong></td>
                                            <td  align="center" width="20%" ><strong>GST</strong></td>
											<td  align="center" width="20%" ><strong>Net Amount </strong></td>
                                            <td  align="center" width="20%" ><strong>Action</strong></td>
                                            <td  align="center" width="20%" ><strong>Txn Status </strong></td>
                                            <td  align="center" width="20%" ><strong>Remark </strong></td>
                                          	
                                          </tr>
                                           <%
										  }%>
                                           <!--Agent tr stop-->
                            
                                        <%if(showService.equalsIgnoreCase("Agent")){
											for(int i=0;i<RechargeDataSize;i++)
											{
												HashMap map=(HashMap)RechargeData.get(i);
											%>
                                        
	                                            <tr class="colr" align="center" style="background:#fff;height:25px;">
		                                            <td  align="center"><%=map.get("agentId") %></td>
		                                            <td  align="center"><%=map.get("dor") %></td>
		                                            <td  align="center"><%=map.get("time") %></td>
		                                            <td   align="center"><%=map.get("mobno") %></td>
		                                            <td  align="center"><%=map.get("TepTranId") %></td>
		                                            <td  align="center"><%=map.get("amt") %></td>
		                                            <td   align="center"><%=map.get("comm") %></td>
		                                            <td  align="center"><%=map.get("Service_charge1") %></td>
		                                            <td  align="center"><%=map.get("GST") %></td>
		                                            <td  align="center"><%=map.get("netamount") %></td>
		                                            <td  align="center"><%=map.get("action") %></td>
		                                            <td  align="center"><%=map.get("transtatus") %></td>
		                                            <td  align="center"><%=map.get("Remarks") %></td>
	                                          	</tr>
                                          	<%}%>
                                          <%}else{ 
                                        	  for(int i=0;i<RechargeDataSize;i++)
											{
												HashMap map=(HashMap)RechargeData.get(i);
											%>
                                        
	                                            <tr class="colr" align="center" style="background:#fff;height:25px;">
		                                            <td  align="center"><%=map.get("agentId") %></td>
		                                            <td  align="center"><%=map.get("dor") %></td>
		                                            <td  align="center"><%=map.get("time") %></td>
		                                            <td   align="center"><%=map.get("mobno") %></td>
		                                            <td  align="center"><%=map.get("operator") %></td>
		                                            <td  align="center"><%=map.get("TepTranId") %></td>
		                                            <td   align="center"><%=map.get("amt") %></td>
		                                            <td  align="center"><%=map.get("OperatorTranId") %></td>
		                                            <td  align="center"><%=map.get("comm") %></td>
		                                            <td  align="center"><%=map.get("Service_charge1") %></td>
		                                            <td  align="center"><%=map.get("transtatus") %></td>
		                                            <%if(!showService.equalsIgnoreCase("Agent")){%>
		                                            <td  align="center"><%=map.get("Remarks") %></td>
		                                            <%} %>
	                                          	</tr>
                                          	<%}%>
                                          
                                          <%} %>
                                          
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
document.myForm.action="AccountStatement.action";
document.myForm.submit();
}
}



function submitFormDownload(){
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
}

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
