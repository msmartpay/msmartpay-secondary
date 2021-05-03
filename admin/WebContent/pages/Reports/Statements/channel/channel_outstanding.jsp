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
<!-- <script>

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
	  
	  
	  
	  
	  /*  $("#datepicker, #datepickers").datepicker({
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
			

        }) */
		
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
		  </script> -->

<%
String message=(String)request.getAttribute("message");
if(message==null)
{
	message="";
}

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
                      <td  height="40" align="left" valign="middle" class="heading_mgs">Statement > Channel</td>
                    </tr>
                  
                    <tr>
                      <td colspan="4">
                      <form name="myForm" action="reportDownload.action?param=ChanelOutStandingDownload" method="post">   
                      <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom:20px;">
                           	<tr ><td width="20px;" align="center" class="dyn_mgs"><%=message%></td></tr>
                          	<tr>
                            	<td align="center" style="padding:20px 0px 20px 0px">
	                            <table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
	                                <tr>
	                                  <td width="35%"  align="left"  height="30"><strong>Select Account Type</strong></td>
	                                  <td width="12%"  align="center">:</td>
	                                  <td width="53%" align="left"><select name="accountType" class="act_type" >
	                                      <option value="adminUser">Admin Account Balance</option>
	                                      <option value="mds">MD Account Balance</option>
										  <option value="ds">Distributor Account Balance</option>
	                                      <option value="agent" selected="selected">Agent Account Balance</option>
	                                    </select></td>
	                                </tr>
	                            </table>
                            	</td> 
                            </tr>
                            <tr>     
                                  <td></td>
                                  <td></td>
                                  <td height="50" align="left" valign="middle">
                                    <input name="Submit"  type="submit" value="Download" class="cls_btn"  id="view_btn" />
                                  </td>
                            </tr>
                       	</table>
                          
                        </form>
                        </td>
                    </tr>
                    <tr>
                      <td colspan="4">
                
              
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
