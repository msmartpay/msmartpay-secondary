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
 
<script>
function check(){
	var allcheck = document.update.checkAll;
	//alert(allcheck)
	if($('#checkAll').is(':checked')){
	for(i=1; i<=3; i++){
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
		var wl=document.getElementById("comm"+j).value;
		

		if(wl=="")
		{
			alert("Please Set Commission");
			document.getElementById("comm"+j).focus();
			return false;
		}
		
	}
}
if(count==0)
{
	alert("Select check box for update margin");
	return false;
}
document.update2.submit();

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
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
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
							<form name ="update" method="post" action="dbTask.action?param=viewPayOutDetails">
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
											  		<option  value="DS">DS</option>
								            	</select></td>
									 		<td>&nbsp;</td>
										</tr>
										
										<tr id="md_row" style="display: none;">
							            <td width="20%"></td>
							            <td>MDS Id</td>
							            <td align="right">:</td>
							            <td>
							            	<input name="mdId" id="mdId" type="text" placeholder="Enter short MDS Id"  />
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
							            <td></td>
							            <td align="right"></td>
							            <td>
							            	<input name="Input" type="submit" value="View" class="cls_btn" />
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
                    <c:if test="${requestScope.pageStatus eq 'Y' }">
                    
                    <tr>
                    <td colspan="4">
                    		<form action="dbTask.action?param=updatePayOutDetails" name="update2" method="post">
                     				<table cellspacing="0" cellpadding="0" width="90%" align="center" class="tbls"  id="mytables">
                                        <tbody>
                                          <!--live recharge tr start-->
                                         <tr class="hd tabulardata" align="center" >
                                         	<td align="center">
                                         	<!-- <input name="checkAll" id="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /> -->
                                         	</td>
                                            <td align="center"><strong>User Id</strong></td>
                                            <td align="center"><strong>User Type</strong></td>
                                            <td align="center"><strong>Service Type</strong></td>
                                            <td align="center"><strong>Comm mark</strong></td>
                                            <td align="center"><strong>Commission</strong></td>
                                          </tr>
                                          <!--live recharge tr stop-->
                                          
                                          <c:if test="${requestScope.PayoutDetailsList!=null }">
                                          <c:forEach var="PayoutDetails" items="${requestScope.PayoutDetailsList }" varStatus="loop"> 
                                          <tr  align="center" style="background:#fff;height:25px;">
                                          	<td align="center"><input type="checkbox" name="checkpartial" id="chek${loop.index }"  value="${loop.index }" /></td>
                                            <td  align="center">
												<input name="userId${loop.index }" readonly="readonly" type="text" value="${PayoutDetails.Client_Id }" />
												<input name="mdId" readonly="readonly" type="hidden" value="${requestScope.mdId }" />
												<input name="userType" readonly="readonly" type="hidden" value="${requestScope.userType }" />
											</td>
                                            <td  align="center">
												<input name="userType${loop.index }" readonly="readonly" type="text" value="${PayoutDetails.User_Type }" />
											</td>
                                            <td  align="center">
												<input name="serviceType${loop.index }" readonly="readonly" type="text" value="${PayoutDetails.Service_Type }" />
											</td>
                                            <td  align="center">
												<select name="CommMark${loop.index }" id="CommMark${loop.index }"  class="searchtext" id="select_option" >
													<option selected="selected" value="${PayoutDetails.Comm_mark }">${PayoutDetails.Comm_mark }</option>
											  		<option value="P">P</option>
											  		<!-- <option value="R">R</option> -->
								            	</select>
											</td>
                                            <td  align="center">
												<input name="comm${loop.index }" id="comm${loop.index }" type="text" value="${PayoutDetails.Commission }" />
											</td>
                                          </tr>
                                          </c:forEach>
										  </c:if>
										  <c:if test="${requestScope.PayoutDetailsList==null }">
                                          <tr  align="center" style="background:#fff;height:25px;">
                                          	<td align="center"><input type="checkbox" name="checkpartial" id="chek0"  value="0" /></td>
                                            <td  align="center">
												<input name="userId0" readonly="readonly" type="text" value="${requestScope.clientId }" />
												<input name="mdId" readonly="readonly" type="hidden" value="${requestScope.mdId }" />
											</td>
                                            <td  align="center">
												<input name="userType" readonly="readonly" type="text" value="${requestScope.userType }" />
											</td>
                                            <td  align="center">
												<input name="serviceType0" readonly="readonly" type="text" value="Recharge" />
											</td>
                                            <td  align="center">
												<select name="CommMark0"  class="searchtext"  >
											  		<option value="P">P</option>
								            	</select>
											</td>
                                            <td  align="center">
												<input name="comm0" id="comm0" type="text" value="" />
											</td>
                                          </tr>
                                          <tr  align="center" style="background:#fff;height:25px;">
                                          	<td align="center"><input type="checkbox" name="checkpartial" id="chek1"  value="1"   /></td>
                                            <td  align="center">
												<input name="userId1" readonly="readonly" type="text" value="${requestScope.clientId }" />
											</td>
                                            <td  align="center">
												<input name="userType" readonly="readonly" type="text" value="${requestScope.userType }" />
											</td>
                                            <td  align="center">
												<input name="serviceType1" readonly="readonly" type="text" value="Billpay" />
											</td>
                                            <td  align="center">
												<select name="CommMark1"  class="searchtext" >
											  		<option value="P">P</option>
								            	</select>
											</td>
                                            <td  align="center">
												<input name="comm1" id="comm1" type="text" value="" />
											</td>
                                          </tr>
                                          <tr  align="center" style="background:#fff;height:25px;">
                                          	<td align="center"><input type="checkbox" name="checkpartial" id="chek2"  value="2"  /></td>
                                            <td  align="center">
												<input name="userId2" readonly="readonly" type="text" value="${requestScope.clientId }" />
											</td>
                                            <td  align="center">
												<input name="userType" readonly="readonly" type="text" value="${requestScope.userType }" />
											</td>
                                            <td  align="center">
												<input name="serviceType2" readonly="readonly" type="text" value="DMR" />
											</td>
                                            <td  align="center">
												<select name="CommMark2"  class="searchtext"  >
											  		<option value="P">P</option>
								            	</select>
											</td>
                                            <td  align="center">
												<input name="comm2" id="comm2" type="text" value="" />
											</td>
                                          </tr>
										  </c:if>
										  
                                          
                                        </tbody>
                                        <tbody>
                                        <tr height="40px">
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="right"><input  type="button" value="Update" onclick="checkMargin(3)" /></td>
                                          </tr>
                                        </tbody>
                                      </table>
                     		</form>
                     	</td>
                    </tr>
                    </c:if>
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
