
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />

<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script>
function submitForm()
{
	if(document.RegLimitpage.userOption.value=="select")
		{
			alert("Please select a user type.");
			document.RegLimitpage.userOption.focus();
			return false;
		}
	else if(document.RegLimitpage.filterOption.value=="select")
		{
				alert("Please select a search category.");
				document.RegLimitpage.filterOption.focus();
				return false;
		}
		else
		{
			if(document.RegLimitpage.filterOption.value=="idwise")
				{
					if(document.RegLimitpage.userid.value=="")
					{
						alert("Please enter a user id.");
						document.RegLimitpage.userid.focus();
						return false;
					}
							
				}
		}
	
	document.RegLimitpage.action="RegistrationEdit.action";
	document.RegLimitpage.submit();
}
</script>
</head>
<%
String message=(String)request.getAttribute("message");
if(message==null)message="";
System.out.println("--------------p1--------------");
ArrayList userData=(ArrayList)request.getAttribute("userDetails");
System.out.println("--------------p2--------------");
int sizeOfuserData=0;

if(userData!=null)
{
	sizeOfuserData=userData.size();
}
else
{
	sizeOfuserData=-1;
}
System.out.println("---sizeOfuserData----"+sizeOfuserData);


String flag = "N/A";

flag = request.getAttribute("flag").toString();
if(flag==null)
{
	flag = "S/A";
}
System.out.println("----flag---------"+flag);
session.setAttribute("flag",flag);
%>

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
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Control Panel > User Registration Controller</td>
                    </tr>
                    <tr>
                      <td colspan="10" align="center" class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr>
                      <td class="dyn_mgs"></td>
                    </tr>
					
                    <tr>
                      <td colspan="4">
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                          <tr>
                            <td align="center" >
					  <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							<form method="post" name="RegLimitpage">
		              			
		              			<input type="hidden" name="param" value="searchDetails"/>        			
			                    <table width="464" border="0" align="center"  cellpadding="0" cellspacing="0" class="mydata_tabl"> 
								   
								   <tr>
				                   <td height="42" ><strong>Select User </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <select name="userOption" style="width:184px;" onchange="popUpMarkup()">
											<option selected="selected" value="select">Select</option>
											<option value="mds">Master Distributor</option>
											<option value="ds">Distributor</option>
										</select>									
									  </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr id="selectOption" style="display:none;">
				                   <td height="42" ><strong>Select </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <select name="filterOption" style="width:184px;" onchange="popUpUserID()">
											<option selected="selected" value="select">Select</option>
											<option value="idwise">ID-Wise</option>
											<option value="all">All</option>
										</select>									
									  </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
								   <tr id="clientID" style="display:none;">
				                   <td height="42" ><strong>User ID  </strong> </td>
				                      <td width="6%"><strong>:</strong></td>
				                      
				                      <td width="57%">
				                        <input value="" id="id" name="userid" style="width:180px" type="text"/>			                         											                                      </td>
				                      
				                      
				                      <td width="0%" class="setcls"> </td>
				                   </tr>
								   
				                   <tr>
				                   		<td width="37%" height="44"> </td>
				                   		<td></td>
			                   		 	<td width="57%"> <input type="button" class="btn_sign" value="Submit" onclick="submitForm()" /> </td>
				                   		<td class="setcls"> </td>
				                   </tr>   
		               		 </table>
		                
		                  </form>
							
							</td>
                          </tr>
						  
                        </table>
						</td>
						</tr><tr ><td height="25"></td></tr>
						<tr ><td >
						
						
						</td></tr>
						
						</table>
					</td>
                    </tr>
					<tr>
                      <td colspan="4">
					                      
					  </td>
                    </tr>
                    <tr>
                      <td colspan="4" height="30">
					  
					  <table width="100%"  cellspacing="0" cellpadding="0" align="center">
                      <tr>
                      <td align="center">
                   <%
                   	  
	                   if(sizeOfuserData > 0)
	                   { 
                   %>   
					<form name="update" method="post" action="RegistrationEdit.action?param=UpdateRegLimit"> 
					 <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls">
                                        <tbody>
                                        
										  <tr class="hd" align="center" style="background:#a74312; height:30px;">
										  	<td></td>
											<td></td>
											<td></td>
											<%if(flag.equalsIgnoreCase("MD")){ %>
											<td></td>
											<%}else{ %>
											<td></td>
											<td></td>
											<%} %>
                                            <td align="right" style="border-right:1px solid #930;"> 
												<input id="input_comm" class="mytextbox" type="text"  maxlength="15" value="0" width="100" style="color:#000;font-size:12px;opacity:0.6;" onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
												<input type="button" value="Go" id="comm_val" /> 
											</td>
										  </tr>
										  
										 <tr class="hd" align="center" style="background:#a74312; height:30px;">
										 	<td width="11%" height="22" align="center"><strong>
									 	   <input type="checkbox" name="checkAll" type="checkbox" onclick="check()"/></strong></td>
                                            <td width="9%" height="22" align="center"><strong>S.N.</strong></td>
                                            <td width="24%" align="center"><strong>Client Id </strong></td>
                                            <td width="23%" align="center"><strong>MDS ID</strong></td>
                                           	<%if(flag.equalsIgnoreCase("MD")) {%>
                                            	<td width="33%" align="center"><strong>DS Registration Limit</strong></td>
										  	<%}else{ %>
										  	<%if(flag.equalsIgnoreCase("DS")) {%>
										  		<td width="23%" align="center"><strong>DS ID</strong></td>
										  		<td width="33%" align="center"><strong>AG Registration Limit</strong></td>
										  	<%} }%>
										  </tr>
                          <%
                          	  for(int i=0;i<sizeOfuserData;i++)
	                          {
	                        		HashMap udata =(HashMap)userData.get(i);
	                          		System.out.println("-------udata----on page---------"+udata);
                          %>  
                                          <tr  align="center" style="background:#fff;height:25px;">
										  	<td align="center"><input type="checkbox" name="checkpartial" id="chek<%=i %>" value="<%=i %>"/></td>
                                            <td align="center"><%=i+1 %></td>
                                            <td align="center">
                                            	<input size="15" width="100" name="clientId" type="text" value="<%=udata.get("ClientId") %>" readonly="readonly"/>
                                            	<input type="hidden" name="client<%=i %>" value="<%=udata.get("ClientId") %>"/>
                                            </td>
                                            <td align="center">
                                            	<input size="15" width="100" name="MdsId" type="text" value="<%=udata.get("MdId") %>" readonly="readonly" />
                                            	<input type="hidden" name="Mds<%=i %>" value="<%=udata.get("MdId") %>"/>
                                            </td>
                                     <%if(flag.equalsIgnoreCase("MD")) { %>
                                            <td align="center">
												<input type="text" name="DSLimit<%=i %>" id="ds<%=i+1 %>" class="input_comm" value="<%=udata.get("DSLimit") %>" width="100" style="color:#000;font-size:12px; opacity:0.9;" onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
											</td>
									 <%}else{ %>
									 <%if(flag.equalsIgnoreCase("DS")) { %>
									 		<td align="center">
                                            	<input size="15" width="100" name="DsId" type="text" value="<%=udata.get("DSId") %>" readonly="readonly" />
                                            	<input type="hidden" name="Ds<%=i %>" value="<%=udata.get("DSId") %>"/>
                                            </td>
                                            <td align="center">
                                            	<input size="10" name="AGLimit<%=i %>" id="ag<%=i+1 %>" class="input_comm" type="text" value="<%=udata.get("AGRegLimit") %>" width="100" style="color:#000;font-size:12px; opacity:0.9;" onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            	
                                            </td>
                                     <%} }%>
                                          </tr>
						  <% } %>	  
                                        </tbody>
                       </table>
                      
                       <table cellspacing="0" cellpadding="0" width="86%" align="center">
                       <tr>
					   		<%if(flag.equalsIgnoreCase("DS")) {%>
						   <td align="right">
							   <input type="button" value="Update" onclick="submitDSClientInfo(<%=sizeOfuserData%>)" />&nbsp;&nbsp;&nbsp;
						   </td>
						   <%}else{ %>
						   <td align="right">
							   <input type="button" value="Update" onclick="submitMDClientInfo(<%=sizeOfuserData%>)" />&nbsp;&nbsp;&nbsp;
						   </td>
						   <%} %>
						   <td align="right" width="4%">
						   		<input type="button" value="Reset" onclick="resetClientInfo()" />
						   </td>
					   </tr>
                       </table>
					 </form> 
					
					<%}%>
						 
                      </td>
                      </tr>
					 </table>
					  
					</td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table>
      </td>
  </tr>
   <tr>
    <td width="100%" valign="top" align="center" height="118"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<script>
function popUpMarkup()
{
	var panelOption=document.RegLimitpage.userOption.value;
	
	if(panelOption=="select")
	{
		document.getElementById("selectOption").style.display="none";
		
		document.RegLimitpage.userOption.focus();
		
	}
	else 
	{
		document.getElementById("selectOption").style.display="";
	}
	
}
function popUpUserID()
{
	var selectOption=document.RegLimitpage.filterOption.value;
	document.getElementById("clientID").style.display="none";
	if(selectOption=="idwise")
	{
		document.getElementById("clientID").style.display="";
	}

}
</script>
<script language="javascript">
$(function(){
 
    // add multiple select / deselect functionality
    $("#selectall").click(function () {
          $('.case').attr('checked', this.checked);
    });
 
    // if all checkbox are selected, check the selectall checkbox
    // and viceversa
    $(".case").click(function(){
 
        if($(".case").length == $(".case:checked").length) {
            $("#selectall").attr("checked", "checked");
        } else {
            $("#selectall").removeAttr("checked");
        }
    });

$("#comm_val").click(function(){
	
	var val = $("#input_comm").val();
	if(val <= 5000)
	{
	$(".input_comm").val(val);
	}
	
	else
	{
	alert("Check Input Value.");
	return false;
	}
	})	
	
	
	$(".mytextbox").keydown(function(event) {
        // Allow: backspace, delete, tab, escape, and enter
        if ( event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 27 || event.keyCode == 13 || 
             // Allow: Ctrl+A
            (event.keyCode == 65 && event.ctrlKey === true) || 
             // Allow: home, end, left, right
            (event.keyCode >= 35 && event.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        		}
        else {
            // Ensure that it is a number and stop the keypress
            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                event.preventDefault(); 
            }   
        }
    });
	
	
	
});
</script>


<script>

function check(){
	var allcheck = document.update.checkAll;
	
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

function submitMDClientInfo(i)
{
	var count=0;

	for(var j=0;j<i;j++)
	{	
		var ck=document.getElementById("chek"+j).checked;
	
		
		if(ck == true)
		{  
			count=count+1;
			var k=j+1;
			var ds=document.getElementById("ds"+k).value;		
			if(ds=="")
			{
				alert("Please set DS creation limit.");
				document.getElementById("ds"+k).focus();
				return false;
			}
			
		}
	}
	if(count==0)
	{
		alert("Select check box to set DS creation limit.");
		return false;
	}
	
	document.update.submit();

}

function submitDSClientInfo(i)
{
	var count=0;

	for(var j=0;j<i;j++)
	{	
		var ck=document.getElementById("chek"+j).checked;
	
		
		if(ck == true)
		{  
			count=count+1;
			var k=j+1;
			var ag=document.getElementById("ag"+k).value;		
			if(ag=="")
			{
				alert("Please set AG creation limit.");
				document.getElementById("ag"+k).focus();
				return false;
			}
			
		}
	}
	if(count==0)
	{
		alert("Select check box to set AG creation limit.");
		return false;
	}
	
	document.update.submit();

}
</script>