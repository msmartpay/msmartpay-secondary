<%@ page import = "java.util.Vector "%> 
<%@ page import = "java.util.HashMap "%>
<%@ page import = "java.util.* "%> 
<%@ page import = "java.text.DecimalFormat "%> 
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />


<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
 
<script type="">
$(document).ready(function(){
	  
	  $("#datepicker").datepicker({
		  
		  	changeMonth: true,
			changeYear: true,
			dateFormat: 'yy-mm-dd',
			yearRange:'2000:2050',
            numberOfMonths: 1,
			defaultDate: "+1w",
			maxDate: "today"
			
		  });
	
  });
function check(){
	var allcheck = document.update.checkAll;
	//alert(allcheck)
	if(allcheck.checked == true){
	for(i=0; i<document.update.elements.length; i++){
		var table = document.getElementsByTagName("input"); 
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name!="checkAll")
			{
				document.update.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.update.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.update.elements[i].type=="checkbox" && document.update.elements[i].name != "checkAll" )
			{
				document.update.elements[i].checked=false;
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
		var k=j+1;
		var wl=document.getElementById("comm"+k).value;
		

		if(wl=="")
		{
			alert("Please Set Commission");
			document.getElementById("comm"+k).focus();
			return false;
		}
	
		
		
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
<script>
$(document).ready(function(){
    $("#user_type").change(function(){
        var val=$(this).val();
        if(val=='DS'){
        	$('#md_row').show();
        }else{
        	$('#md_row').hide();
        	$('#mdId').val('');
        }
    });
});
</script>
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
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights">
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">
                      Pay Out Commission</td>
                    </tr>
                   <tr align="center"><td class="dyn_mgs">${requestScope.message }</td></tr>
                    <tr>
                      <td colspan="4">
                      <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td align="center" >
							<form name ="update" method="post" action="dbTask.action?param=RunPayOutSchedular">
							<table cellpadding="0" cellspacing="0" width="86%" style="margin-bottom:15px;" align="center" id="border">  
							<tr><td colspan="4" valign="top">
							
							       <table cellpadding="0" cellspacing="0" width="750" align="center" class="mydata_tabl">
							           <tr><td colspan="100%" height="20px"></td></tr>
										<tr>
								            <td width="20%"></td>
								          	<td width="15%"><strong>Search for</strong></td>
								    	 	<td width="10%">:</td>
								    	 	<td width="35%">
								    	 		<select name="userType"  class="searchtext" id="user_type" >
											  		<option selected="selected" value="MDS">MDS</option>
											  		<option value="DS">DS</option>
								            	</select></td>
									 		<td>&nbsp;</td>
										</tr>
							            <tr id="md_row" style="display: none;">
							            <td width="20%"></td>
							            <td>MDS Id</td>
							            <td align="right">:</td>
							            <td>
							            	<input name="mdId" id="mdId" type="text" placeholder="Enter short MDS Id/all"  />
							            </td>
							          	</tr>
							            <tr>
							            <td width="20%"></td>
							            <td>User Type Id</td>
							            <td align="right">:</td>
							            <td>
							            	<input name="clientId" type="text" placeholder="Enter short User Type Id/all" />
							            </td>
							          	</tr>
							          	
							          	<tr>
							            <td width="20%"></td>
							            <td>Select Commission Date</td>
							            <td align="right"></td>
							            <td>
							            	<input name="date" type="text" id="datepicker"  />
							            </td>
							          	</tr>
							  
							            <tr>
							            <td width="20%"></td>
							            <td></td>
							            <td align="right"></td>
							            <td>
							            	<input name="Input" type="submit" value="Pay Commission" class="cls_btn" />
							            </td>
							          	</tr>
							          
							          	<tr><td colspan="100%" height="20px"></td></tr>
							             </table>
										
										</td>
							  </tr>
							  </table>	
							  </form>						
							</td>
                          </tr>
                        </table></td>
                    </tr>
                    
                    <tr>
                      <td colspan="4" height="30">                      </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="50px"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
