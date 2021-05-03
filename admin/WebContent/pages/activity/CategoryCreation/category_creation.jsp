<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
	<%@ page import="java.text.*" %>
	
	<%
	 Date today = new Date();
	 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
	int size=0;
	
	String categoryName=(String)request.getAttribute("commCategoryName");
	String service=(String)request.getAttribute("service");
	String actionUpdate=(String)request.getAttribute("actionUpdate");
	
	 String curdateFrom = formatter1.format(today);
	  String curdateTo = formatter1.format(today);
	  ArrayList<HashMap<String,Object>>getCatDetails=(ArrayList<HashMap<String,Object>>)request.getAttribute("getCatDetails");
	
	 ArrayList<HashMap<String,Object>> getComDetails=(ArrayList<HashMap<String,Object>>)request.getAttribute("commDetails");
	 
	
	if(getCatDetails==null || getCatDetails.size()<1)
	{
	size=-1;
	}
	else
	{
	size=getCatDetails.size();
	
	}
	
	if(categoryName==null)
	{
	categoryName="block";
	}
	
	
	if(service==null)
	{
	service="";
	}
	String message=(String)request.getAttribute("message");
	if(message==null)
	{
	message="";
	
	}
	%>

</script>
<SCRIPT type="text/javascript" src="scripts/CategoryCreationValidation.js"></script>



  <SCRIPT type="text/javascript" src="scripts/dhtmlgoodies_calendar.js?random=20060118"></script>

<link href="css/dhtmlgoodies_calendar.css?random=20051112" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="css/dhtmlgoodies_calendar.css?random=20051112" media="screen"></link>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>

  <tr>
    <td valign="top" align="center" height="400">
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" >
        
        <tr>
          <td valign="top" align="center" class="rounded-corners" >
          
            <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs" >Category Creation</td>
              </tr>
              <tr>
                <td  width="100%" valign="bottom" height="40" align="center" class="dyn_mgs" ><%=message%></td>
              </tr>
              <tr>
                <td colspan="4" height="20"></td>
              </tr>
                 <form name="newCatForm" method="post" action="createCategory.action">
                      <tr><td colspan="4" >
					 
					   <table cellpadding="0" cellspacing="0" width="510" align="center" border="0"  class="mydata_tabl" >
					   <tr><td align="left" height="30">Enter Category Name</td>
					   <td><input name="newCatName" type="text" style="width:200px;" id="req" /><span style="padding-left:15px; text-decoration:none;"><a href="#" onclick="return callAjax(this)">Check Availability</a></span><span id="Availability"></span></td></tr>
						
								<tr><td></td><td align="center" height="50"><input type="button" value="Submit" onclick="createCategory()" style="width:80px; height:25px; margin:0 30px 0 0;"/></td></tr>
					   </table>
					  
					  </td></tr>
					  </form>
                      
                    
                      <tr><td colspan="4" >
					   <table cellpadding="0" cellspacing="0" width="510" align="center" border="0"  class="mydata_tabl" >
					   <tr>
					   <td width="100%" height="30"><a href="getExistDetails.action">Click here to View/ Edit Category Margin</a></td></tr>
						
								
					   </table>
					  </td>
					  </tr>
                      
	            </table>
	           </td>
	        </tr>
	        <%if(size>0){ %>
	         <tr>
               <td valign="top" align="center" class="rounded-corners" >
          
                 <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
                  <form name="serarchStatus" method="post">
                 
                       <tr>
                        <td height="30" colspan="4" align="center" bgcolor="#a74312" class="heading_mgs">Category Commission Setup</td>
                      </tr>
                       
          				 <tr><td>
			  			  <table cellpadding="0" cellspacing="0" width="510" align="center" border="0"  class="mydata_tabl" >
			  			  <tr><td align="left" height="30" width="40%">Select Category</td>
			  			     <td><select  name="categoryName" >
								<option value="select" selected="selected">Select</option>
				       			  <% 
              						for (int i=0;i<size;i++) 
					              {
								 HashMap hm=(HashMap)getCatDetails.get(i);
					               
								 %>
				               <option value="<%=hm.get("catName")%>">Category-<%=hm.get("catName")%> </option> 
								<%}%>
								</select>
								</td>
								</tr>
								</table>
								</td>
							  </tr>
						<tr><td>
			  			  <table cellpadding="0" cellspacing="0" width="510" align="center" border="0"  class="mydata_tabl" >
			  			  <tr><td align="left" height="30" width="40%">Select Action</td>
			  			     <td><select  name="updateaction" id="updateactionId" onchange="updateService()">
								 <option value="select" selected="selected">Select</option>
				       			 <option value="commission">Commission</option> 
				       			 <option value="service charge">ServiceCharge</option> 
								 </select>
								</td>
								</tr>
								
							  <tr>
							   
							   <td align="left" height="30" width="40%">Select Service</td>
							  
			  			       <td><table cellpadding="0" cellspacing="0" class="mydata_tabl">
			  			         <tr id="default" style="display:block"><td>
			  			         <select  id="mySelectbox" name="servicedefault" onclick="checkaction()"  >
						        <option value="select" selected="selected">Select</option>
						      
                                </select>
								</td></tr>
								
			  			       <tr id="commission" style="display:none"><td><select  id="mySelectbox" name="serviceComm"  >
						       <option value="select" selected="selected">Select</option>
						       <option value="Air">Air Booking</option>
						       <option value="Pay Bill">Pay Bill</option>
						       <option value="Pay Premium">Pay Premium</option>
						       <option value="Recharge Service">Recharge Service</option>
						       <option value="Others">Others</option>
                                </select>
								</td></tr>
								
							   <tr id="scharge" style="display:none"><td><select  id="scharge" name="scharges"  >
						       <option value="select" selected="selected">Select</option>
						       <option value="Pay Bill">Pay Bill</option>
						       <option value="Pay Premium">Pay Premium</option>
						       <option value="Others">Others</option>
                               </select>
							  </td></tr>
								
								</table>
								</td>
							</tr>
								
						    </table>
								</td>
							  </tr>
							  
							 
							
							  
							  
					  <tr><td>
			  			  <table cellpadding="0" cellspacing="0" width="510" align="center" border="0"  class="mydata_tabl" >
			  			        <tr><td align="left" height="30" width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			  			         <td style="width:200px" align="center"><input type="hidden" name="service"/>
			  			          <input type="button" value="Submit" name="buttoncom" style="width:80px; cursor:pointer; margin:0 30px 0 0;" onclick="getDetails()"/>
			  			         </td>
								</tr>
								</table>
								</td>
							  </tr>
							  
							  </form>
							</table>
			           </td>
			        </tr>
	                <% } %>
	                <%if(getComDetails!=null){ %>
	                <form name="updateAgentMargin" method="post">
	                <tr>
	               
                      <td valign="top" align="center" class="rounded-corners" >
                        
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr><td height="30" width="20%"><font size="-1">Valid From</font>
                                  <input  type="text"  name="validFrom" class="style2" style="width:115px" value="<%=curdateFrom%>" readonly/>
                                    &nbsp;&nbsp;&nbsp;<img src="images/cal.jpg" height="17" width="15" border="0" onclick="displayCalendar(document.updateRechargeService.validFrom,'yyyy-mm-dd',this)"/></td>
			                          <td height="30" colspan="4" align="center" class="logintxt" >Update <%=actionUpdate%> for category : <%=categoryName%> </td>
			                           <td height="30" width="20%"><font size="-1">ValidTo</font>
			                           <input  type="text"  name="validTo" class="style2" style="width:115px" value="<%=curdateTo%>" readonly/>
                                    &nbsp;&nbsp;&nbsp;<img src="images/cal.jpg" height="17" width="15" border="0" onclick="displayCalendar(document.updateRechargeService.validTo,'yyyy-mm-dd',this)"/></td>
                                  
                           </tr>
                          
                          
	                	</table>
			           </td>
			        </tr>
			         <%if(service.equalsIgnoreCase("air")){ %>
			         
			           <tr>
                          <td valign="top" align="center" class="rounded-corners" >
                            <table cellpadding="0" cellspacing="0" width="100%" align="center" border="1" class="mydata_tabl">
                            <tr align="center" height="25"  class="hd">
                            <td width="2%">SL</td>
                            <td width="2%"><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="checkRecharge()" value="" /></td>
                            <td width="10%">Service</td>
                            <td width="10%">Flight Name</td>
                            <td width="10%">Sector</td>
                            <td width="10%">Discount on Basic(%)</td>
                            <td width="10%">Discount on YQ(%)</td>
                            <td width="10%">Discount on Gross(%)</td>
                            <td width="10%">Discount in Rs(Rs)</td>
                            <td width="10%">Max Markup(Rs)</td>
                            <td width="10%">Cancelation Charges(Rs)</td>
                            <td width="10%">Error Comm</td>
                          
                          </tr>
                          <%if(getComDetails!=null && getComDetails.size()>0){
                        	  for(int i=0;i<getComDetails.size();i++){
                            	  HashMap<String,Object> commMap=(HashMap)getComDetails.get(i);
                              %>
                           <tr align="center" height="25"  class="hd">
                            <td><%=i+1%></td>
                            <td><input type="checkbox" name="checkpartial" value="<%=commMap.get("flight")%>"/></td>
                            <td width="10%">Air</td>
                            <td width="10%"><%=commMap.get("flight")%></td>
                            <td width="10%"><%=commMap.get("sector")%><input type=hidden name="sec<%=commMap.get("flight")%>" value="<%=commMap.get("sector")%>" ></td>
                            <td width="10%"><input type="text" name="disb<%=commMap.get("flight")%>" value="<%=commMap.get("disonbasic")%>" ></td>
                            <td width="10%"><input type="text" name="disyq<%=commMap.get("flight")%>" value="<%=commMap.get("disonyq")%>" ></td>
                            <td width="10%"><input type="text" name="disg<%=commMap.get("flight")%>" value="<%=commMap.get("disongros")%>" ></td>
                            <td width="10%"><input type="text" name="disrs<%=commMap.get("flight")%>" value="<%=commMap.get("disinrs")%>" ></td>
                            <td width="10%"><input type="text" name="markup<%=commMap.get("flight")%>" value="<%=commMap.get("maxmarkup")%>" ></td>
                            <td width="10%"><input type="text" name="ccharge<%=commMap.get("flight")%>" value="<%=commMap.get("ccharges")%>" ></td>
                            <td width="10%"><input type="text" name="errorcomm<%=commMap.get("flight")%>" value="<%=commMap.get("errorComm")%>" ></td>
                             </tr>
                          <%} }%>
                           </table> 
			           </td>
			        </tr>
			        
			           <tr>
	               
                      <td valign="top" align="right"   width="850">
                         
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr><td height="30" width="80%">&nbsp;</td>
                            <td height="30" width="20%">
                            <input type="hidden" value="<%=categoryName%>" name="categoryName" />
                            <input type="hidden" value="<%=service%>" name="service" />
                            <input type="hidden" value="<%=actionUpdate%>" name="updateaction" />
                            <input type="button" value="Update" name="updatebuttonair" style="width:80px; cursor:pointer; margin:0 30px 0 0;" onclick="updateMarginAir()"/>
                            </td>
                            </tr>
                               </table>
                               </td>   
                           </tr>
                           
			         <%} else { %>
			         <tr>
                          <td valign="top" align="center" class="rounded-corners" >
                            <table cellpadding="0" cellspacing="0" width="100%" align="center" border="1" class="mydata_tabl">
                            <tr align="center" height="25"  class="hd">
                            <td width="2%">SL</td>
                            <td width="4%"><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="checkRecharge()" value="" /></td>
                            <td width="13%">Main Service</td>
                            <td width="13%">Sub Service</td>
                            <td width="15%">Operator Name</td>
                            <td width="11%">Circle</td>
                            <td width="13%">Commission</td>
                            <td width="13%">Commission Mark(%)</td>
                          
                          </tr>
                          <%if(getComDetails!=null && getComDetails.size()>0){
                        	  for(int i=0;i<getComDetails.size();i++){
                            	  HashMap<String,Object> commMap=(HashMap)getComDetails.get(i);
                              %>
                           <tr align="center" height="25"  class="hd">
                            <td><%=i+1%></td>
                            <td><input type="checkbox" name="checkpartial" value="<%=i%>"/></td>
                            <td><%=commMap.get("service")%><input type="hidden" name="mser<%=i%>" value="<%=commMap.get("service")%>"></td>
                            <td><%=commMap.get("subService")%><input type="hidden" name="ser<%=i%>" value="<%=commMap.get("subService")%>"></td>
                            <td><%=commMap.get("operator")%><input type="hidden" name="opr<%=i%>" value="<%=commMap.get("operator")%>"></td>
                            <td>All</td>
                            <td>
                            <input type="text" style="width:70px;"  id="margin<%=i+1%>" name="margin<%=i%>" value="<%=commMap.get("operatorComm")%>" onkeypress="return digitonly(this,event)" maxlength="5"/></td>
                            <td><select style="width:70px;" id="mark<%=i+1%>"name="mark<%=i%>">
                            <% 
                               String mark=(String)commMap.get("operatorCommMark");
                            
                            	if(mark.equalsIgnoreCase("P")){ %>
                                       <option value="P">P</option><option value="R">R</option></select></td>
                                       <% }else {%>
                                       <option value="R">R</option><option value="P">P</option></select></td>
                                       <%} %>
                            
                          
                          </tr>
                          <%} }%>
                           </table> 
			           </td>
			        </tr>
			        
			           <tr>
	               
                      <td valign="top" align="right"   width="850">
                         
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr><td height="30" width="80%">&nbsp;</td>
                            <td height="30" width="20%">
                            <input type="hidden" value="<%=categoryName%>" name="categoryName" />
                            <input type="hidden" value="<%=service%>" name="service" />
                            <input type="hidden" value="<%=actionUpdate%>" name="updateaction" />
                            <input type="button" value="Update" name="updatebutton" style="width:80px; cursor:pointer; margin:0 30px 0 0;" onclick="updateMargin()"/>
                            </td>
                            </tr>
                               </table>
                               </td>   
                           </tr>
                           <%} %>
			         </form>
	                <%}%>
	                
	               </table>
	      </td>
	  </tr>
  <!--footer-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>
<script type="text/javascript">




	function updateService(){
		var action = document.getElementById("updateactionId").value;
		
	 var serviceform="";

	if(action=="select"){
		    alert("Please select action for comission update");
		    document.getElementById("scharge").style.display='none';
			document.getElementById("commission").style.display='none';
			document.getElementById("default").style.display='block';	
		}

		if(action=="commission"){
			document.getElementById("scharge").style.display='none';
			document.getElementById("commission").style.display='block';
			document.getElementById("default").style.display='none';
			}

		  if(action=="service charge"){
			    document.getElementById("scharge").style.display='block';
				document.getElementById("commission").style.display='none';
				document.getElementById("default").style.display='none';
		  }

		 
	}

	function checkaction(){
		var action = document.getElementById("updateactionId").value;

		           if(action=="select"){
				    alert("Please select action first for comission update");
				    document.getElementById('updateactionId').focus();
				    return false;
				  }
		    }
	
function openstatement()
{
var a = document.getElementById("riskID").value
if(a==0)
{
alert("Please Select Services");
}

}

function request()
{
var a= document.getElementById("req").value;
document.getElementById("res").value =a;
}


function category_margin()
{
document.getElementById("show_newcategory").style.display='block';
	
}

function openPage()
{
var a = document.getElementById('mySelectbox').value;
if(a==0)
{
	 alert("Please select any option in the sub Service");
	 document.getElementById('mySelectbox').focus();
}

else 
{
	window.open(a,'_self')
}



}


function openstatement()
{
var a = document.getElementById("riskID").value
if(a==0)
{
alert("Please Select Services");
}

}

function request()
{
var a= document.getElementById("req").value;
document.getElementById("res").value =a;
}

function open_creation()
{
document.getElementById("show_div").style.display='block'
}

function openPage()
{
var a = document.getElementById('mySelectbox').value
if(a==0)
{
	alert("Please select any option in the sub Service");
	 document.getElementById('mySelectbox').focus()
}
for(i=1;i<=3;i++)
{
	document.getElementById('d'+i).style.display= 'none'
}
	document.getElementById('d'+a).style.display= 'block'
	


}

function checkRecharge()
{

	
	var allcheck = document.updateRechargeService.checkAll;
	
	if(allcheck.checked == true)
	{
		for(i=0; i<document.updateRechargeService.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.updateRechargeService.elements[i].type=="checkbox" && document.updateRechargeService.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
			{
				document.updateRechargeService.elements[i].checked=true;
			}
			
		}
		
	}
	else
	{
		for(i=0; i<document.updateRechargeService.elements.length; i++)
		{
			var table = document.getElementsByTagName("input"); 
			if(document.updateRechargeService.elements[i].type=="checkbox" && document.updateRechargeService.elements[i].name != "checkAll" &&  table[i].getAttribute("name")!="checkbox1")
			{
				document.updateRechargeService.elements[i].checked=false;
			}
			
		}
	}
}


function digitonly(input,evt)

{
var keyCode = evt.which ? evt.which : evt.keyCode;
var lisShiftkeypressed = evt.shiftKey;
if (lisShiftkeypressed && parseInt(keyCode) != 9) 
{
return false;
}
if ((parseInt(keyCode) >= 48 && parseInt(keyCode) <= 57 || parseInt(keyCode) == 8 || parseInt(keyCode) == 9 || parseInt(keyCode) == 37 || parseInt(keyCode) == 39 || parseInt(keyCode) == 46) ) 
{
return true;
}

return false;
}

</script>

