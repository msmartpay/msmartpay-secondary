<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*" %>

<%

List list=(List)request.getAttribute("getDetails");
	 System.out.println("list :"+list);
String mdshare="Variable";
String clientshare="Variable";
int size=0;
if(list!=null)
{
size=list.size();
}
else
{

size=-1;
}
	 System.out.println("listbncghbgch   tetsing  :");
String service=(String)request.getAttribute("service");
String enterUserID=(String)request.getAttribute("enterUserID");
if(enterUserID==null)
{

enterUserID="";
}
String message=(String)request.getAttribute("message");
if(message==null)
{

message="";
}

String flag=(String) request.getAttribute("flag");
if(flag==null)flag="N";


%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName") %></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<SCRIPT type="text/javascript" src="scripts/digitonly.js"></script>
<SCRIPT type="text/javascript" src="scripts/commissionSearch.js"></script>

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
	if(val <= 100)
	{
	$(".input_comm").val(val)
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
</SCRIPT>
<script>
function sbmt()
{
//alert("heloo")
	var val =document.rechargeForm.EnterUserId.value;
	var usertype= document.rechargeForm.usertype.value;

	if(usertype == "Select")
	{			   
		//alert("hiiiii")
		alert("AgentChargeSearch"+usertype);
		document.rechargeForm.focus();
		return false;

	}
	
	document.rechargeForm.action="AgentChargeSearch.action?param=getDetails";
	//alert(document.rechargeForm.action);
	document.rechargeForm.submit();
}

function updates()
{
	var val = document.getElementById("input_1").value;
	alert(val);
}

</script>

<style>
select{color:#930;font-size:13px;}
select option{color:#930;font-size:13px;}
</style>

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
        <td valign="top" align="center" class="rounded-corners box_heights" >
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td  height="40" align="left" valign="middle" class="heading_mgs">
               Charges Set > Agent  </td>
            </tr>
			
			<form name="rechargeForm" method="post">
            <tr>
              <td colspan="4">
              
              <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                  <tr>
                    <td align="center">
                    
					<table width="90%"   cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					<tr><td colspan="100%" height="25px"></td></tr>
					  <tr>
                      <td width="20%"></td>
					  <td width="10%"><strong>User Type</strong></td>
					  <td width="10%">:</td>
					      <td width="60%">
                          <select name="usertype">
                          
                              <option value="Select">Select</option>
                              <option value="ByClientId">Client ID</option>
                              <option value="ByMdId">MDS ID</option>
                              <option value="ByDsId">DS ID</option>
                              <option value="ByAgentId">Agent ID</option>
                          </select>						  </td>
                      </tr>
                      <tr>
                         <td width="20%"></td>
                          <td><strong>User ID</strong></td>
                          <td>:</td>
                          <td>
                          <input type="text" name="EnterUserId"   />                         </td>
                        </tr>
					    <tr>
                        <td width="20%"></td>
                          <td><strong>Service</strong></td>
                          <td>:</td>
                          <td>
                             <select name="selectService">
                          <option selected="selected" value="recharge">Recharge</option>
                          <option value="Utility">Utility</option>
                           <option value="Air">Flights</option>
                            <option value="Bus">Bus</option>
                             <option value="Hotel">Hotel</option>
                             <option value="PayCard">PayCard</option>
                           <option value="TestMerit">TestMerit</option>
                            <option value="dth">DTH-X</option>
                             <option value="Ops">Ops</option>
                          </select>                            </td>
                        </tr>
                        
                        
                        <tr align="center">
                        <td width="20%"></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button" value="Submit" class="cls_btn" onclick="sbmt()" /></td>
                        </tr>
                        <tr><td colspan="100%" height="25px"></td></tr>
                      </table>					  </td>
                  </tr>
                </table>                </td>
            </tr>
			
                   	
			 <tr>
              <td colspan="4">
			   <% if(flag.equalsIgnoreCase("Y"))
              {
				 
              %>
			   <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style="margin-top:10px;">
                 <tbody>
                   <tr class="hd" align="center" style="background:#a74312; height:40px;">
                     <td width="10%"  align="center"><strong>Category</strong></td>
                     <td width="10%"  align="center"><strong>Postpaid</strong></td>
                     <td width="18%" align="center"><strong>Agent ID</strong></td>
                     <td width="18%" align="center"><strong><%=request.getAttribute("enterUserID")%></strong></td>
                     <td width="18%" align="center"><strong>Charges</strong></td>
                     <td width="15%" align="center"><input name="text" type="text" class="mytextbox" id="input_comm" style="width:75px; height:15px; color:#930;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" value="0" maxlength="3" /></td>
                     <td width="15%" style="border-right:1px solid #930;"><input name="button" type="button" id="comm_val" value="Go" />                     </td>
                   </tr>
                   <tr class="hd" align="center" style="background:#e7611e;height:25px;">
                     <td><input name="checkAll" type="checkbox" id="selectall"   /></td>
                     <td ><strong>S.N.</strong></td>
                     <td  align="center"><strong>Service</strong></td>
                     <td  align="center"><strong>Sub-Service</strong></td>
                     <td  align="center"><strong>Operator</strong></td>
                     <td  align="center"><strong>Charges</strong></td>
                     <td align="center" style="border-right:1px solid #930;"><select name="myselects" onchange="myfunc()"  style="width:100px; height:20px; padding:0;">
                         <option value="chargemode">Charges Mode</option>
                         <option value="p">P</option>
                         <option value="r">R</option>
                       </select>                     </td>
                   </tr>
                   <%
											if(size>0)
											{
												for(int i=0;i<size;i++)
												{
													int j=i+1;
													HashMap map=(HashMap)list.get(i);
											%>				 
                   <tr  align="center" style="background:#fff;height:25px;">
                     <td><input name="checkbox" type="checkbox" class="case" /></td>
                     <td><%=j%></td>
                     <td  align="center"><%=map.get("Main_Service")%></td>
                     <td  align="center"><%=map.get("Sub_Service")%></td>
                     <td  align="center"><%=map.get("Category")%></td>
				
                     <td  align="center"><input name="text" type="text" class="input_comm mytextbox1" id="clientshare_1" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='') this.value=''"  onblur="if(this.value=='') this.value='0'" value="<%=map.get("SKU_Charge")%>" maxlength="3" />                     </td>
											 <td  align="center"><%=map.get("SKU_Charge_Type")%></td>
                   </tr>
                   <%}} %>
                 </tbody>
                 <tbody>
                   <tr height="40px">
                     <td style="border:none;"></td>
                     <td style="border:none;"></td>
                     <td style="border:none;" align="center"></td>
                     <td style="border:none;" align="center"></td>
                     <td style="border:none;" align="center"></td>
                     <td style="border:none;" align="center"><input name="button" type="button" class="cls_btn" value="Update"  /></td>
                     <td  align="center" style="border:none;"><input name="button" type="button" class="cls_btn" value="Reset" /></td>
                   </tr>
                 </tbody>
               </table>
			   <%  }%>             </td>
            </tr>
            <tr>
              <td colspan="4" valign="top"></td>
            </tr>           
          </table>          </td>
      </tr>
    </table></td>
</tr>
<tr>
  <td width="100%" valign="top" align="center" height="162px"></td>
</tr>
<tr>
  <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
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

<script>
function myfunc()
{
var val = document.rechargeForm.myselects.value;
if(val == "chargemode")
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