<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="java.util.ArrayList"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />

<link rel="icon" href="images/t.png" />

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
    	var ytoday = dd+'-' + mm + '-' +now.getFullYear() ;
		var today = dd+'-' + mm + '-' +now.getFullYear() ;
	 	$('#datepicker').val(ytoday);
		$('#datepickers').val(today);
	  
	  $(".act_type").change(function(){
		  
		  var val =  $(".act_type").val();
		  
		  
		  
		  /* if(val == "icici")
		  {
			  $("#view_btn").css("display","");
		  } */
		  
		  if(val == "icici")
		  {
			  $("#view_btn").css("display","none");
		  }
		  
		  
	 })
	  
	  $("#datepicker, #datepickers").datepicker({
            changeMonth: true,
			changeYear: true,
			dateFormat: 'dd-mm-yy',
            numberOfMonths: 1,
			defaultDate: "+1w",
            maxDate: "0",
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
                           <tr ><td width="20px;" align="center" class="dyn_mgs"><%=message%></td></tr>
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
                            <table width="65%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                                <tr>
                                  <td width="35%"  align="left"  height="30"><strong>Statement Type</strong></td>
                                  <td width="12%"  align="center">:</td>
                                  <td width="53%" align="left"><select name="accountType" class="act_type" id="riskID" onchange="toggleSubmit1(this)">
                                      <option selected="selected" value="icici">ICICI Account Statement</option>
                                    </select></td>
                                </tr>
                                   <tr>
                                  	<td colspan="3">
									<div >
                                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">                               
                                      
                                      <tr>
                            
										<td  align="left" width="35%" ><strong>From Date</strong></td>
									   <td  align="center" width="12%" >:</td>
									   <td width="53%"><input type="text" name="fromDate" id="datepicker" />
                              			</td>
                                	</tr>   
                     
                                       <tr>
                            
									  	<td  align="left"><strong>To Date</strong></td>
									   	<td  align="center">:</td>
									   	<td >
							
										 <input type="text" name="toDate" id="datepickers" />
										 
									 
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
                                    <input name="Submit" style="display: none;" type="button" value="View" class="cls_btn" onclick="submitForm()" id="view_btn" />
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
                
              			<!-- View -->
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
