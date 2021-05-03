<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" src="scripts/digitonly.js"></script>
<script type="text/javascript" src="scripts/commissionSearch.js"></script>

<script>
$(document).ready(function(){

	$('#fill_btn').click(function(){

		$('.wl_text').val($('#fill_text').val());     
	       
	}); 
	
});

function setComm(j)
{
var value1=document.getElementById('wl'+j).value;
if(value1>100)
{
document.getElementById('wl'+j).value=100;
document.getElementById('md'+j).value=0;
alert('Total persenteg can not be gratter than 100');
return false;
}
var value2=100 - parseInt(value1);
document.getElementById('md'+j).value=value2;
}

</script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
 <SCRIPT language="javascript">
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
	if(val <= 10)
	{
	$(".input_comm").val(val)
	}
	
	else
	{
	alert("Check Input Value.");
	return false;
	}
	})	
	
	$("#charge_val").click(function(){
	
	var val = $("#input_charge").val();
	if(val <= 10)
	{
	$(".input_charge").val(val)
	}
	else
	{
	alert("Check Input Value.");
	return false;
	}
	});	
	
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
</SCRIPT>
<script>
function sbmt()
{
	document.rechargeForm.action="SKUaction.action?param=getSKUData";
	document.rechargeForm.submit();
}
</script>

<style>


select{color:#930;font-size:13px;}
select option{color:#930;font-size:13px;}
input["type=text"]{color:#930;font-size:13px;}
span{color:#fff;font-size:13px;}
</style>

</head>
<%

ArrayList SKUData=(ArrayList) request.getAttribute("SKUData");
int sizeOfSkuData=0;
if(SKUData!=null){sizeOfSkuData=SKUData.size();}
else{sizeOfSkuData=-1;}
System.out.println("sizeOfSkuData is "+sizeOfSkuData);
String Displaycategory=(String)request.getAttribute("Displaycategory");
String DisplaymainService=(String)request.getAttribute("DisplaymainService");
String DisplaysubService=(String)request.getAttribute("DisplaysubService");
String message=(String)request.getAttribute("message");
if(message==null)message="";
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
        <td valign="top" align="center" class="rounded-corners box_heights">
        
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
            <tr>
              <td height="40" align="left" valign="middle" class="heading_mgs">
               Edit SKU
              </td>
            </tr>
            
			<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
			
                   	
			 <tr>
              <td colspan="4" valign="top">
              
             <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom: 20px;">
                  <tr>
                    <td align="center">
                    <form name="rechargeForm" method="post">
					  
					<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					<tr><td height="20px" colspan="100%"></td></tr>
					   <tr>
                       <td width="30%" height="30px"></td>
					  <td width="10%"><strong>Category</strong></td>
					  <td width="10%">:</td>
					      <td width="60%">
					      
                         <select name="category">
                         
                          <c:forEach var="CategoryName" items="${sessionScope.CategoryName }">
                          	<option value="${CategoryName }">${CategoryName }</option>
                          </c:forEach>
                          </select>
                          
						  </td>
                      </tr>
                      <tr>
                         <td width="30%" height="30px"></td>
                          <td><strong>Main Service</strong></td>
                          <td>:</td>
                          <td>
                       <select name="mainService">
                          <option selected="selected" value="All">All</option>
                          <c:forEach var="MainServiceName" items="${sessionScope.MainServiceName }">
                          	<option value="${MainServiceName }">${MainServiceName }</option>
                          </c:forEach>
                          </select>
                         </td>
                        </tr>
					    <tr>
                        <td width="30%" height="30px"></td>
                          <td><strong>Sub Service</strong></td>
                          <td>:</td>
                          <td>
                           <select name="subService">
                          <option selected="selected" value="All">All</option>
                          <c:forEach var="SubServiceName" items="${sessionScope.SubServiceName }">
                          	<option value="${SubServiceName }">${SubServiceName }</option>
                          </c:forEach>
                          </select>
                            </td>
                        </tr>
                        
                        <tr>
                        <td width="30%" height="30px"></td>
                          <td><strong>Client Id</strong></td>
                          <td>:</td>
                          <td>
                           <select name="clientId">
	                          <option selected="selected" value="All">All</option>
	                          <c:forEach var="clientDetails" items="${sessionScope.clientDetails }">
                          		<option value="${clientDetails.portalId }">${clientDetails.companyName }</option>
                          	</c:forEach>
							  
	                         </select>
                            </td>
                        </tr>
                        
                       
                        
                        <tr align="center">
                        <td width="30%" height="30px"></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button" value="Submit" class="cls_btn" style="cursor:pointer;" onclick="sbmt()" /></td>
                        </tr>
                        <tr><td height="20px" colspan="100%"></td></tr>
                      </table>
                      </form>
                     
					  </td>
                  </tr>
                </table>
                                      
             </td>
            </tr>
            <tr>
              <td colspan="4" valign="top">
			  <%if(sizeOfSkuData>0){%>
              <form name="update" method="post" action="SKUaction.action?param=updateSKUdata">
              <table cellspacing="0" cellpadding="0" width="90%" align="center" class="tbls" id="mytables" style="margin-top:10px;">
                                        <tbody>
                                        
                                         <tr class="hd" align="center" style="background:#a74312; height:40px;">
                                            <td width="5%"  align="center"><strong>Category</strong></td>
                 <td width="5%"  align="center"><strong><%=Displaycategory%><input type="hidden" name="Displaycategory" value="<%=Displaycategory%>"/></strong></td>
                 <td width="10%"  align="center"><strong>Main Service</strong></td>
                 <td width="10%"  align="center"><strong><%=DisplaymainService%><input type="hidden" name="DisplaymainService" value="<%=DisplaymainService%>"/></strong></td>
                 <td width="10%" align="center"><strong>Sub Service</strong></td>
                 <td width="10%" align="center"><strong><%=DisplaysubService%><input type="hidden" name="DisplaysubService" value="<%=DisplaysubService%>"/></strong></td>
                                            <td width="7%" align="center">
                                            <input id="input_comm" class="mytextbox" type="text"  maxlength="2" value="0" style="width:75px; height:15px; color:#930;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td width="7%" style="border-right:1px solid #930;"> <input type="button" value="Go" id="comm_val" /> </td>
                                            <td width="7%" align="center">
                                            <input type="text" id="input_charge" maxlength="2" class="mytextbox" value="0" style="width:75px; height:15px; color:#930;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td width="7%" style="border-right:1px solid #930;"> <input type="button" value="Go" id="charge_val" /> </td>
                                          </tr>
                                   <tr class="hd" align="center" style="background:#e7611e;height:25px;">
                                          <td><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></td>
                                          <td><strong>S.N.</strong></td>
                                            <td  align="center"><strong>Main Service</strong></td>
                                            <td  align="center"><strong>Sub Service</strong></td>
                                            <td  align="center"><strong>Category</strong></td>
                                            <td  align="center"><strong>SKU Name</strong></td>
                                            <td align="center"><strong>Commission</strong></td>
                                            <td  align="center">
                                            <select style="width:85px; height:20px; padding:0;" name="myselects" onchange="myfunc()">
                                            <option value="commtype">Comm Type</option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select>
                                            </td>
                                            <td align="center"><strong>Charge</strong></td>
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select style="width:90px; height:20px; padding:0;" name="myselectss" onchange="myfunc1()">
                                            <option value="chargetype">Charge Type</option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select>
                                            
                                            </td>
                                          </tr>
										  <%
										  for(int i=0;i<sizeOfSkuData;i++){
										  HashMap map=(HashMap)SKUData.get(i);
										  %>
										  <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" name="checkpartial" id="chek<%=i %>" value="<%=i%>"/></td>
                                            <td><%=i+1%></td>
                                            <td  align="center"><%=map.get("MainService")%><input type="hidden" name="mser<%=i%>" value="<%=map.get("MainService")%>"></td>
                                            <td  align="center"><%=map.get("SubService")%><input type="hidden" name="ser<%=i%>" value="<%=map.get("SubService")%>"></td>
                                            <td  align="center"><%=map.get("Category")%><input type="hidden" name="cat<%=i%>" value="<%=map.get("Category")%>"></td>
                                            <td  align="center"><%=map.get("SKUName")%><input type="hidden" name="sku<%=i%>" value="<%=map.get("SKUName")%>"></td>
                                            <td  align="center">
            <input type="text" name="SKUComm<%=i%>" id="wl<%=i+1%>" class="input_comm" value="<%=map.get("SKUComm")%>" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center">
                                            <select style="width:50px;height:21px;color: #000;" name="SKUCommType<%=i%>">
											<option selected="selected" value="<%=map.get("SKUCommType")%>"><%=map.get("SKUCommType")%></option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select></td>
                                            <td align="center">
         <input type="text" name="SKUCharge<%=i%>" id="md<%=i+1%>" class="input_charge" value="<%=map.get("SKUCharge")%>" style="width:65px; height:15px; color:#930;font-size:12px; opacity:0.8;" 
                 onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            </td>
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select style="width:50px;height:21px;color: #000;" name="SKUChargeType<%=i%>">
											<option selected="selected" value="<%=map.get("SKUChargeType")%>"><%=map.get("SKUChargeType")%></option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select>
                                            </td>
                                            
                                          </tr>
										  <%}%>
                                        </tbody>
                                        
                                        <tbody>
                                        <tr height="40px">
                                        <td style="border:none;"></td>
                                            <td style="border:none;"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"><input style="width:90px;color:#930;" type="button" value="Update" onclick="checkMargin(<%=sizeOfSkuData %>)" /></td>
                                            <td  align="center" style="border:none;"><input style="width:90px;color:#930;" type="button" value="Reset" /></td>
                                        </tr>
                                        </tbody>
                                      
                                      </table>
              </form>
			  <%}%>
              </td>
            </tr>  
                    
          </table>
          
          </td>
      </tr>
    </table>
    </td>
</tr>

<tr>
  <td width="100%" valign="top" align="center" height="162px">
</td>
</tr>

<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %>
</td>
</tr>

</table>
</body>
</html>
<script type="text/javascript">

function resetfunction()
{
document.getElementById("check3").style.visibility="visible";
document.update.action="GetRechargeMarginDetails.action";
document.update.submit();

}


function showdiv()
{
alert("hii");
var selectType=document.getElementById("mdselect").value;
alert(selectType);
if(selectType=="ByAll")
{
document.getElementById("EnterwlId").style.display="";
}
if(selectType=="ById")
{
	document.getElementById("EntermdId").style.display="";
	document.getElementById("EnterwlId").style.display="";
}

}
</script>

<script>
function myfunc()
{
var val = document.rechargeForm.myselects.value;
if(val == "commtype")
{
	
	 return false;
}
if(val == "p")
{
document.rechargeForm.myoption1.options[0].selected=true;
document.rechargeForm.myoption2.options[0].selected=true;
document.rechargeForm.myoption3.options[0].selected=true;
document.rechargeForm.myoption4.options[0].selected=true;

}

if(val == "r")
{
document.rechargeForm.myoption1.options[1].selected=true;
document.rechargeForm.myoption2.options[1].selected=true;
document.rechargeForm.myoption3.options[1].selected=true;
document.rechargeForm.myoption4.options[1].selected=true;
}

}
</script>

<script>
function myfunc1()
{
var val = document.rechargeForm.myselectss.value;
if(val == "chargetype")
{
	
	 return false;
}
if(val == "p")
{
document.rechargeForm.myoption5.options[0].selected=true;
document.rechargeForm.myoption6.options[0].selected=true;
document.rechargeForm.myoption7.options[0].selected=true;
document.rechargeForm.myoption8.options[0].selected=true;

}

if(val == "r")
{
document.rechargeForm.myoption5.options[1].selected=true;
document.rechargeForm.myoption6.options[1].selected=true;
document.rechargeForm.myoption7.options[1].selected=true;
document.rechargeForm.myoption8.options[1].selected=true;
}

}
</script>
<script language="javascript">
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
		var wl=document.getElementById("wl"+k).value;
		var md=document.getElementById("md"+k).value;		
		
		

		if(wl=="")
		{
			alert("Please Set Commission");
			document.getElementById("wl"+k).focus();
			return false;
		}
		if(md=="")
		{
			alert("Please Set Service Charge");
			document.getElementById("wl"+k).focus();
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