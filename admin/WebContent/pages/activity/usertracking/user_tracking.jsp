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

<script type="text/javascript">
		//https://maps.google.com/?q=23.22,88.32&z=8
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
		 	$('#datepicker1').val(ytoday);
			$('#datepicker2').val(today);
			
			$("#datepicker1, #datepicker2").datepicker({
		            changeMonth: true,
					changeYear: true,
					dateFormat: 'yy-mm-dd',
		            numberOfMonths: 1,
					defaultDate: "+1w",
		            maxDate: "1"
			})
		});
	 
		

</script>
<script type="text/javascript">
function openMap(location)
{
	window.open("https://maps.google.com/?q="+location.latitude+","+location.longitude+"&z=8",'popup','width=700,height=550,left=400,top=50,screenX=300,screenY=100,resizable=no,scrollbars=no');

}
</script>
</head>
<body>


<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="../../../header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >
        <tr>
          <td valign="top" align="center">
          		<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="newbg">
              	<tr>
                	<td valign="top" align="center" class="rounded-corners box_heights" >
                	<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">User Tracking </td>
                    </tr>
                  
                    <tr>
                      <td colspan="4">
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom:20px;">
                           <tr><td width="20px;" align="center" class="dyn_mgs">${requestScope.message }</td></tr>
                           <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            		<form name="usertracking" method="post" action="usertracking.action?param=fetchTrackingDetails">
                            		<table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
	                                <tr>
	                                  <td width="35%"  align="left"  height="30"><strong>User Type</strong></td>
	                                  <td width="12%"  align="center">:</td>
	                                  <td width="53%" align="left">
	                                  	<select name="userType" >
	                                      <option value="agent">Agent</option>
										  <option value="DS">Distributor</option>
	                                    </select>
	                                  </td>
	                                </tr>
	                                <tr>
                            
										<td  align="left" width="35%" ><strong>From Date</strong></td>
									   <td  align="center" width="12%" >:</td>
									   <td width="53%">
									   <input type="text" name="fromDate" readonly="readonly"  id="datepicker1" />
                              			</td>
                                	</tr> 
                                	
                                	<tr>
                            
										<td  align="left" width="35%" ><strong>To Date</strong></td>
									   <td  align="center" width="12%" >:</td>
									   <td width="53%">
									   <input type="text" name="toDate"  readonly="readonly"  id="datepicker2" />
                              			</td>
                                	</tr> 
	                     
	                                <tr>
                                                  <td  width="35%" align="left"  height="30"><strong>User ID</strong></td>
                                                  <td width="12%"  align="center">:</td>
                                                  <td width="53%" align="left">
                                                  <input name="userId" required="required" placeholder="2001" type="text" class="style2 blank_id"  />
                                                  </td>
                                    </tr>                                                                  
                                    <tr>
	                                  <td></td>
	                                  <td></td>
	                                  <td height="50" align="left" valign="middle">
	                                    <input name="Submit" type="submit" value="View" class="cls_btn"  />
	                                    </td>
	                                </tr>                                    
                                        
                                      
                                     </table>
                                    </form>
							</td>                                                               
                           </tr>
                                
                      </table>
                      </td>
                    </tr>
                    </table>
                       
                 	</td>
               </tr>
               <tr>
                 	<td colspan="4">
                
             						<c:if test="${requestScope.TrackingDetails!=null &&  requestScope.TrackingDetails.size()>0}">
                      				<table cellspacing="0" cellpadding="0" width="96%" align="center" class="tbls"  
                      				id="mytables" style="margin:20px; 0">
                                        <tbody>
                                        
                                        <tr class="hd tabulardata" align="center" >
                                        	<td width="10%" align="center" width="20%"><strong>ID</strong></td>
                                            <td width="10%" align="center" width="20%"><strong>User ID</strong></td>
                                            <td width="50%"   align="center" width="70%"><strong>Location</strong></td>
                                            <td width="" align="center"><strong>DateTime</strong></td>
                                             <td width="" align="center"><strong>View Map</strong></td>
                                          
                                          </tr>
                                        <c:forEach var="trackingdetail" items="${requestScope.TrackingDetails }" varStatus="loop">
                                           <tr class="colr" align="center" style="background:#fff;height:25px;">
                                            <td  align="center">${loop.index+1 }</td>
                                            <td  align="center">${trackingdetail.userId} </td>
                                            <td  align="center">${trackingdetail.location}</td>
                                            <td   align="center">${trackingdetail.dateTime}</td>
                                             <td width="" align="center"><strong><a href='javascript:openMap(${trackingdetail.location})' >View</a></strong></td>
                                         	</tr>
                                          	
                                        </c:forEach>  
									      </tbody>
                                      </table>
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
    <td width="100%" valign="top" align="center" height="100px"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>

