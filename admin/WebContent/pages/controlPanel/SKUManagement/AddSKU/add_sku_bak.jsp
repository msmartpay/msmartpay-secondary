<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/superadmin.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<!--<SCRIPT type="text/javascript" src="scripts/digitonly.js"></script>-->
<SCRIPT type="text/javascript" src="scripts/commissionSearch.js"></script>

<script src="//code.jquery.com/jquery-1.9.1.js"></script>


<script>
$(document).ready(function(){

	$('#fill_btn').click(function(){

		$('.wl_text').val($('#fill_text').val());     
	       
	}); 
	
	
	
	$("#mainselect").change(function(){
	
	var val = $("#mainselect").val();
	if(val == "other")
	{
	$("#input_1").css("display","");
	$("#input_2").css("display","");
	}
	else
	{
	$("#input_1").css("display","none");
	$("#input_2").css("display","none");
	}
	});
	
	
	$("#mainsubservic").change(function(){
	
	var val = $("#mainsubservic").val();
	if(val == "Other")
	{
	$("#input_2").css("display","");
	}
	else
	{
	$("#input_2").css("display","none");
	}
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
	//alert("Check Input Value.");
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
	//alert("Check Input Value.");
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
	document.rechargeForm.action="SKUaction.action?param=addSKUDetails";
	document.rechargeForm.submit();
}
</script>

<style>
.textShow{border:none;outline:none;background:none;}
.tbls{ margin:20px auto 25px auto;}
.tbls tbody tr td{ border:1px solid #930; border-right:none;border-bottom:none;  height:25px; font-family:"Trebuchet MS"; font-size:13px;}
.tbls tbody tr td strong{ font-weight:bold;font-size:13px;}
.tbls tbody tr td span{ font-weight:normal;font-size:12px;}
.bords td{border-bottom:1px solid #930; font-family:"Trebuchet MS"; font-size:13px;}
.hr_tbls{ width:100%;}
.hr_tbls tr td{border:0px solid #930;font-family:"Trebuchet MS"; font-size:13px; height:43px;}
.hr_tbls tr td strong{ margin-left:30px;}
select{ height:25px; padding:3px 0 3px 3px; width:225px;font-size:13px; font-family:"Trebuchet MS";}
option{font-size:13px; font-family:"Trebuchet MS";}
input[type='text']{width:220px;font-size:13px; font-family:"Trebuchet MS";}
.colr td{ color:#a74312; font-weight:normal;}
</style>

</head>
<%
ArrayList CategoryName=(ArrayList) session.getAttribute("CategoryName");
ArrayList MainServiceName=(ArrayList) session.getAttribute("MainServiceName");
ArrayList SubServiceName=(ArrayList) session.getAttribute("SubServiceName");
ArrayList SmsVendor=(ArrayList) session.getAttribute("SmsVendor");
ArrayList WebVendor=(ArrayList) session.getAttribute("WebVendor");

ArrayList SKUData=(ArrayList) request.getAttribute("SKUData");
System.out.println("WebVendor  :"+WebVendor);
System.out.println("SmsVendor  :"+SmsVendor);
int sizeOfSkuData=0;
if(SKUData!=null){sizeOfSkuData=SKUData.size();}
else{sizeOfSkuData=-1;}
System.out.println("sizeOfSkuData is "+sizeOfSkuData);
String Displaycategory=(String)request.getAttribute("Displaycategory");
String DisplaymainService=(String)request.getAttribute("DisplaymainService");
String DisplaysubService=(String)request.getAttribute("DisplaysubService");
String message=(String)request.getAttribute("message");
if(message==null)message="";
System.out.println("message in jsp page:"+message);
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
        <td valign="top" align="center" class="rounded-corners">
        
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td  height="40" align="left" valign="middle" style="font-size:13px; font-weight:bold; padding-left:60px;" class="logintxt">
               Add SKU
              </td>
            </tr>
			
			<form name="rechargeForm" method="post">
			<tr><td  colspan="10" align="center" style="font-size:18px; font-weight:bold; color:#FF0000; font-family:'Trebuchet MS';"><%=message%></td></tr>
            <tr>
              <td colspan="4">
              
              
                
                </td>
            </tr>
			
                   	
			 <tr>
              <td colspan="4">
              
             <table width="90%"  cellspacing="0" cellpadding="0" align="center" class="form11">
                  <tr>
                    <td align="center">
					<table  cellspacing="0" cellpadding="0" align="center" class="hr_tbls">
					    <tr>
                         <td width="30%"></td>
                          <td><strong>SKU Name</strong></td>
                          <td>:</td>
                          <td>
                         <input type="text"   name="SKUName" />                         </td>
                        </tr>
                        <tr>
                       <td width="30%"></td>
					  <td width="10%"><strong>Comm Type</strong></td>
					  <td width="10%">:</td>
					      <td width="60%">
                         <select name="CommType">
                          <option>P</option>
                          <option>R</option>
                          </select>						  </td>
                      </tr>
                      <tr>
                         <td width="30%"></td>
                          <td><strong>Comm</strong></td>
                          <td>:</td>
                          <td>
                      <input type="text" name="Comm" class="mytextbox" maxlength="2" id="input_comm1"   />                         </td>
                        </tr>
                        
					    <tr>
                        <td width="30%"></td>
                          <td><strong>Charge Type</strong></td>
                          <td>:</td>
                          <td>
                           <select name="ChargeType">
                          <option>P</option>
                          <option>R</option>
                          </select>                            </td>
                        </tr>
                        <tr>
                        <td width="30%"></td>
                          <td><strong>Charge</strong></td>
                          <td>:</td>
                          <td>
                          <input type="text" name="Charge" class="mytextbox" maxlength="2" id="input_comm2"  />                            </td>
                        </tr>
                        
                        
                        <tr>
                        <td width="30%"></td>
                          <td><strong>Web Vendor</strong></td>
                          <td>:</td>
                          <td>
                         <!-- <select><option>Select</option></select>-->
						   <select name="WebVendor">
                          <option selected="Select">Select</option>
						  <%if(WebVendor!=null){
						  	for(int i=0;i<WebVendor.size();i++){
						  %>
						  <option value="<%=WebVendor.get(i)%>"><%=WebVendor.get(i)%></option>
						  <%}}%>
                          </select>                            </td>
                        </tr>
                        <tr>
                        <td width="30%"></td>
                          <td><strong>SMS Vendor</strong></td>
                          <td>:</td>
                          <td>
                        <!--   <select><option>Select</option></select>-->
						    <select name="SmsVendor">
                          <option selected="Select">Select</option>
						  <%if(SmsVendor!=null){
						  	for(int i=0;i<SmsVendor.size();i++){
						  %>
						  <option value="<%=SmsVendor.get(i)%>"><%=SmsVendor.get(i)%></option>
						  <%}}%>
                          </select>                            </td>
                        </tr>
                        <tr>
                        <td width="30%"></td>
                          <td><strong>SMS Code</strong></td>
                          <td>:</td>
                          <td>
                          <input type="text"  name="SMSCode" maxlength="10"/>                            </td>
                        </tr>
                        
                        
                        
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Category</strong></td>
                          <td>:</td>
                          <td>
                           <select name="category">
                          <option selected="Select">Select</option>
						  <%if(CategoryName!=null){
						  	for(int i=0;i<CategoryName.size();i++){
						  %>
						  <option value="<%=CategoryName.get(i)%>"><%=CategoryName.get(i)%></option>
						  <%}}%>
                          </select>
                          <p style="float:right;padding:0;margin:0 235px 0 0;  font-size:12px;">
                       <!--   <a href="SKUaction.action?param=AddCategoryPage">Add Category</a>-->
					    <a href='#' onclick='javascript:window.open("SKUaction.action?param=AddCategoryPage", "_blank", "scrollbars=0,resizable=1,height=300,width=450");'>Add Category</a>                          </p>                            </td>
                        </tr>
                
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Main Service</strong></td>
                          <td>:</td>
                          <td valign="top">
                           <select name="mainService" id="mainselect" onchange="getSubService()">
                          <option selected="Select">Select</option>
						  <%if(MainServiceName!=null){
						  	for(int i=0;i<MainServiceName.size();i++){
						  %>
						  <option value="<%=MainServiceName.get(i)%>"><%=MainServiceName.get(i)%></option>
						  <%}}%>
						  <option value="other">Other</option>
                          </select>
                           <input type="text" name="otherMainService" style="float:right; margin:0 85px 0 0; display:none;" id="input_1" /></td>
                        </tr>
                        
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Sub Service</strong></td>
                          <td>:</td>
                          <td>
                           <select name="subService" id="mainsubservic">
                          <option selected="Select">Select</option>
						  <%if(SubServiceName!=null){
						  	for(int i=0;i<SubServiceName.size();i++){
						  %>
						  <option value="<%=SubServiceName.get(i)%>"><%=SubServiceName.get(i)%></option>
						  <%}}%>
						  <option value="other">Other</option>
                          </select>
                          
						   <input type="text" name="otherSubService" style="float:right; margin:0 85px 0 0; display:none;" id="input_2" />
                                                  </td>
                        </tr>
                        
                        <tr align="center">
                        <td width="30%"></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button"  value="Submit" style="width:80px; cursor:pointer; margin-left:145px;" onclick="sbmt()" /></td>
                        </tr>
                      </table>
                      
                     
					  </td>
                  </tr>
                </table>
                                      
             </td>
            </tr>
			</form>
            <tr>
              <td colspan="4" valign="top">
               <%if(sizeOfSkuData>0){%>
			    <form name="update" method="post" action="SKUaction.action?param=updateAddSKUdata">
              <table cellspacing="0" cellpadding="0" width="90%" align="center" class="form11 tbls" id="mytables">
                                        <tbody>
                                        
                                         <tr class="hd" align="center" style="background:#a74312; height:40px;">
                                            <td width="5%"  align="center"><strong>Category</strong></td>
                 <td width="5%"  align="center"><span><%=Displaycategory%><input type="hidden" name="Displaycategory" value="<%=Displaycategory%>"/></span></td>
                 <td width="10%"  align="center"><strong>Main Service</strong></td>
                 <td width="10%"  align="center"><span><%=DisplaymainService%><input type="hidden" name="DisplaymainService" value="<%=DisplaymainService%>"/></span></td>
                 <td width="10%" align="center"><strong>Sub Service</strong></td>
                 <td width="10%" align="center"><span><%=DisplaysubService%><input type="hidden" name="DisplaysubService" value="<%=DisplaysubService%>"/></span></td>
                                            <td width="7%" align="center">
                                            <input id="input_comm" class="mytextbox" type="text"  maxlength="2" value="0" style="width:75px; height:15px; color:#000;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td width="7%" style="border-right:1px solid #930;"> <input type="button" value="Go" id="comm_val" /> </td>
                                            <td width="7%" align="center">
                                            <input type="text" id="input_charge" maxlength="2" class="mytextbox" value="0" style="width:75px; height:15px; color:#000;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td width="7%" style="border-right:1px solid #930;"> <input type="button" value="Go" id="charge_val" /> </td>
                                          </tr>
                                   <tr class="hd" align="center" style="background:#e7611e;height:25px;">
                                          <td><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></td>
                                          <td >S.N.</td>
                                            <td  align="center">Main Service</td>
                                            <td  align="center">Sub Service</td>
                                            <td  align="center">Category</td>
                                            <td  align="center">SKU Name</td>
                                            <td align="center">Commission</td>
                                            <td  align="center">
                                            <select style="width:85px; height:20px; padding:0;" name="myselects" onchange="myfunc()">
                                            <option value="commtype">Comm Type</option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select>
                                            </td>
                                            <td align="center">Charge</td>
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
										//  System.out.println(" Charge is :"+map.get("SKUCharge"));
										  %>
										  <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" name="checkpartial" id="chek<%=i %>" value="<%=i%>"/></td>
                                            <td><%=i+1%></td>
                                            <td  align="center"><%=map.get("MainService")%><input type="hidden" name="mser<%=i%>" value="<%=map.get("MainService")%>"></td>
                                            <td  align="center"><%=map.get("SubService")%><input type="hidden" name="ser<%=i%>" value="<%=map.get("SubService")%>"></td>
                                            <td  align="center"><%=map.get("Category")%><input type="hidden" name="cat<%=i%>" value="<%=map.get("Category")%>"></td>
                                            <td  align="center"><%=map.get("SKUName")%><input type="hidden" name="sku<%=i%>" value="<%=map.get("SKUName")%>"></td>
                                            <td  align="center">
            <input type="text" name="SKUComm<%=i%>" id="wl<%=i+1%>" class="input_comm" value="<%=map.get("SKUComm")%>" style="width:40px; height:15px; color:#000;font-size:12px; opacity:0.9;" onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center">
                                            <select style="width:50px;height:21px;" name="SKUCommType<%=i%>">
											<option selected="selected" value="<%=map.get("SKUCommType")%>"><%=map.get("SKUCommType")%></option>
                                            <option value="P">P</option>
                                            <option value="R">R</option>
                                            </select></td>
                                            <td align="center">
         <input type="text" name="SKUCharge<%=i%>" id="md<%=i+1%>" class="input_charge" value="<%=map.get("SKUCharge")%>" style="width:65px; height:15px; color:#000;font-size:12px; opacity:0.8;" 
                 onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            </td>
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select style="width:50px;height:21px;" name="SKUChargeType<%=i%>">
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
                                            <td style="border:none;" align="center"><input style="width:90px" type="button" value="Update" onclick="checkMargin(<%=sizeOfSkuData %>)" /></td>
                                            <td  align="center" style="border:none;"><input style="width:90px" type="button" value="Reset" /></td>
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
document.update.action="SKUaction.action?Param=addSKUDetails";
document.update.submit();

}


function showdiv()
{
//alert("hii");
var selectType=document.getElementById("mdselect").value;
//alert(selectType);
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

function checkMargin(i)
{
var count=0;

for(var j=0;j<i;j++){
	
	var ck=document.getElementById("chek"+j).checked;
	
	if(ck == true)	{  
		count=count+1;
		var k=j+1;
		var fix=100;
		var wl=document.getElementById("wl"+k).value;
		var md=document.getElementById("md"+k).value;		
		var total=parseInt(wl)+parseInt(md);

		if(parseInt(wl) < 0)
		{
			alert("Client Share can not be less than than 0");
			document.getElementById("wl"+k).focus();
			return false;
		}
		if(parseInt(md) < 0)
		{
			alert("MD Share can not be less than than 0");
			document.getElementById("wl"+k).focus();
			return false;
		}
		
		if(parseInt(wl) > parseInt(fix))
		{
			alert("Client Share can not be gratter than 100");
			document.getElementById("wl"+k).focus();
			return false;
		}
		if(parseInt(md) > parseInt(fix))
		{
			alert("MD Share can not be gratter than 100");
			document.getElementById("wl"+k).focus();
			return false;
		}
				
		if(parseInt(total)!=parseInt(fix))
		{
		alert("Total persenteg should be 100");
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

<script language="javascript">

function getSubService()
{
var xmlhttp;
var mainService="";

mainService=document.getElementById("mainselect").value; 

var url = "SKUaction.action?param=getSubService&mainService="+mainService;	  
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200) 
    {
	    
    document.getElementById("mainsubservic").innerHTML=xmlhttp.responseText;
	   
    }
  }
xmlhttp.open("post",url,true);
xmlhttp.send();
}



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