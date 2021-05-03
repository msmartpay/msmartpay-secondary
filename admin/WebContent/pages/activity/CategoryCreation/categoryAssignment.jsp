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
	
	
	 ArrayList<HashMap<String,Object>>getCatDetails=(ArrayList<HashMap<String,Object>>)request.getAttribute("getCatDetails");
	
	 ArrayList<ArrayList<Object>> getWlClinetDetails=(ArrayList<ArrayList<Object>>)request.getAttribute("getWlClinetDetails");
	 
	
	if(getCatDetails==null || getCatDetails.size()<1)
	{
	size=-1;
	}
	else
	{
	size=getCatDetails.size();
	
	}


	String message=(String)request.getAttribute("message");
	if(message==null)
	{
	message="";
	
	}
	%>


<script>

  function updateAssignCategory(){

	var category=document.assignCategory.categoryName.value;
	


	if(category=="select")
		{
			
			alert("Please Select Category for Assign New Category. ")
			document.assignCategory.categoryName.focus();
			return false;
		}
		 var chks = document.getElementsByName('checkclient');

			var checkCount = 0;
			for (var i = 0; i < chks.length; i++)
			{
			if (chks[i].checked)
			{
			checkCount++;
			}
			}
			if (checkCount <1)
			{
			alert("Please select at least Client Id for Assign New Category.");
			return false;
			}

			 var x=window.confirm("Are you sure you want to continue ?");
				if (x)
				{
					document.assignCategory.action="categoryAssignment.action?action=update";
					document.assignCategory.submit();
				}
				else
				{
					
					return false;
				}
}

</script>

<script type="text/javascript">


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




</script>
</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">

  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>

  <tr>
    <td valign="top" align="center" height="400" >
       <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0"  class="rounded-corners box_heights">
       
       <tr>
         <td height="40" valign="middle" colspan="100%" align="left" class="heading_mgs">Category Assignment </td>
          </tr>
                       
         <%if(size<=0) {%>
		 <tr>
               <td valign="top" align="center" >
          
                 <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                 
                       <tr>
                        <td  colspan="4" align="center" class="dyn_mgs"><%=message%></td>
                       </tr>
                  </table>
                </td>
                </tr>
                       
	        <% }if(size>0){ %>
			<tr><td colspan="3" align="center" class="dyn_mgs"><%=message%></td></tr>
	         <tr>
               <td valign="top" align="center"  >
               <form name="assignCategory" method="post">
                 <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" class="mydata_tabl" style="margin:10px 0 20px 0; border-bottom:1px solid #930;">
                  
                  
                  
                  
                       <tr>
                        <td colspan="100%" align="center" valign="top" >
                        
                        <table cellpadding="0" cellspacing="0" class="mydata_tabl" width="100%" height="100px" id="border" style="margin-bottom:10px;margin-top:-10px;">
                        
                        <tr>
                        <td width="25%"> </td>
                        <td></td>
                        <td> </td>
                        </tr>
                        
                        <tr>
                        <td> </td>
                        <td><strong>Assign Category</strong> </td>
                        <td> <select style="width:200px" name="categoryName" >
								<option value="select" selected="selected">Select</option>
				       			  <% 
              						for (int i=0;i<size;i++) 
					              {
								 HashMap hm=(HashMap)getCatDetails.get(i);
					               
								 %>
				               <option value="<%=hm.get("catName")%>">Category-<%=hm.get("catName")%> </option> 
								<%}%>
								</select></td>
                        </tr>
                        <tr>
                        <td> </td>
                        <td> </td>
                        <td valign="top"> <input type="button" value="Update" name="updatebutton" class="cls_btn" onclick="updateAssignCategory()"/></td>
                        </tr>
                        </table>
                        
						</td> 
                        
                      </tr>
                  
                  
                       
							  
							  <%if(getWlClinetDetails!=null) { %>
							  
					     <tr><td>
			  			  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="tbls" >
			  			     <tr bgcolor="#a74312"><td align="center" width="5%" style=" height:30px; border-left:1px solid #930;"><strong style="color:#fff;">S.N.</strong></td>
			  			     <td align="center" width="3%">
                             <input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="checkRecharge()" value="" /></td>
			  			     <td align="center" width="10%"><strong style="color:#fff;">Client Id</strong></td>
			  			      <td align="center" width="10%" ><strong style="color:#fff;">Category</strong></td>
			  			     <td align="center" width="40%" ><strong style="color:#fff;">Client Name</strong></td>
			  			     <td align="center" width="10%"><strong style="color:#fff;">Client Type</strong></td>
			  			     <td align="center" width="30%"><strong style="color:#fff;">Client Domain</strong></td>
			  			     
								</tr>
								  <% 
              						for(int j=0;j<getWlClinetDetails.size();j++) 
					              {
              					   ArrayList<Object>result=(ArrayList)getWlClinetDetails.get(j);
              					 
              					   if(result!=null && result.size()>0);{
              					  %>
					             <tr bgcolor="#FFFFFF"><td align="center" width="5%" style="border-left:1px solid #930;"><%=j+1%></td>
                                 
					              <td align="center" width="3%"><input  type="checkbox" id="checkclient" name="checkclient" value="<%=result.get(0)%>"/></td>
				  			     <td align="center" width="10%"><%=result.get(0)%></td>
				  			     <td align="center" width="10%"><%=result.get(1)%></td>
				  			      <td align="center" width="40%"><%=result.get(2)%></td>
				  			     <% String status=(String)result.get(3); 
				  			     
				  			   if(status==null || status.equalsIgnoreCase("null") ){
				  			    	status="Tep"; 
				  			     }
				  			   
				  			     if(status.equalsIgnoreCase("0")){
				  			    	status="Tep"; 
				  			     }
				  			   if(status.equalsIgnoreCase("1")){
				  			    	status="Rep"; 
				  			     }
				  			 if(status.equalsIgnoreCase("2")){
				  			    	status="Tep-Rep"; 
				  			     }
				  			    %>
				  			     <td align="center" width="10%" style="text-transform:uppercase;"><%=status%></td>
				  			     <td align="center" width="30%"><%=result.get(4)%></td>
						             
							  </tr>
								 <%}
              					   
					              }%>
								 
								</table>
								</td>
							  </tr>
							  <%} %>
						 
							  
							 
							</table>
							 </form>
			   	</td>
			 	</tr>
	        <% } %>
	                
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



