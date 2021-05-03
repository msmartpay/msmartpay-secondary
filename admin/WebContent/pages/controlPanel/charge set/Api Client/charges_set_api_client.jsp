<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*" %>

<%@ page import = "java.text.DecimalFormat "%> 
<%
DecimalFormat dff = new DecimalFormat("##.##");


List list=(List)request.getAttribute("getDetails");
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
	var val =document.rechargeForm.EnterUserId.value;
	if(val == "")
	{
		return false;
			}
	else{
	document.getElementById('mytables').style.display="";
	}
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
               Charges Set > API Client
              </td>
            </tr>
			
			
            <tr>
              <td colspan="4">
              <form name="rechargeForm" method="post">
                
                <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                  <tr>
                    <td align="center">
                    
					<table width="90%" cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					<tr><td colspan="100%"></td></tr>
                    	<tr>
                       <td width="20%"></td>
					  <td width="10%"><strong>User Type</strong></td>
					  <td width="10%">:</td>
					      <td width="60%">
                          <select name="usertype">
                              <option value="Select">Select</option>
                              <option value="Recharge API Client">Recharge API Client</option>
                              <option value="Bill API Client">Bill API Client</option>
                              <option value="Flight API Client">Flight API Client</option>
                              <option value="Hotel API Client">Hotel API Client</option>
                              <option value="Bus API Client">Bus API Client</option>
                              
                            </select>
                          
						  </td>
                      </tr>
					   <tr>
                       <td width="20%"></td>
					  <td width="10%"><strong>Client ID</strong></td>
					  <td width="10%">:</td>
					      <td width="60%"><input type="text" name="EnterUserId"  onkeypress="return digitonly(this,event)" />
						  </td>
                      </tr>
					  
                        <tr>
                         <td width="20%"></td>
                          <td><strong>Service</strong></td>
                          <td>:</td>
                          <td>
                          <select>
                          <option selected="selected">Recharge</option>
                          <option>Utility</option>
                           <option>Flights</option>
                            <option>Bus</option>
                             <option>Hotel</option>
                          </select>
                         </td>
                        </tr>
                        
                        <tr align="center">
                        <td width="20%"></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button" value="Submit" class="cls_btn" onclick="sbmt()" /></td>
                        </tr>
                        
                        <tr><td colspan="100%"></td></tr>
                        
                      </table>
                      
                     
					  </td>
                  </tr>
                </table>
                
                </form>
                
                </td>
            </tr>
			
               <form name="rechargeForm1" method="post">
                   	
			 <tr>
              <td colspan="4">
              <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style="display:none;margin-top:10px;" id="mytables">
                                        <tbody>
                                        
                                         <tr class="hd" align="center" style="background:#a74312; height:40px;">
                                            <td width="10%"  align="center"><strong style="color:#FFF;">Category</strong></td>
                                            <td width="10%"  align="center"><span style="color:#FFF;">Postpaid</span></td>
                                            <td width="18%" align="center"><strong style="color:#FFF;">Client ID</strong></td>
                                            <td width="18%" align="center"><span style="color:#FFF;">100011</span></td>
                                    		<td width="18%" align="center"><strong style="color:#FFF;">Charges</strong></td>
                                            <td width="15%" align="center">
                                            <input type="text" id="input_comm" maxlength="3" class="mytextbox" value="0" style="width:75px; height:15px; color:#930;font-size:12px; opacity:0.6;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td width="15%" style="border-right:1px solid #930;"> <input type="button" value="Go" id="comm_val" /> </td>
                                          </tr>
                                   <tr class="hd" align="center" style="background:#e7611e;height:25px;">
                                          <td><input name="checkAll" type="checkbox" id="selectall"   /></td>
                                          <td><strong style="color:#FFF;">S.N.</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Service</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Sub-Service</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Operator</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Charges</strong></td>
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select name="myselects" onchange="myfunc()"  style="width:100px; height:20px; padding:0;">
                                            <option value="chargemode">Charges Mode</option>
                                            <option value="p">P</option>
                                            <option value="r">R</option>
                                            </select>
                                            </td>
                                          </tr>
                                        
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>1</td>
                                            <td  align="center">Recharge</td>
                                            <td  align="center">Data Card</td>
                                            <td  align="center">AIRCEL</td>
                                            <td  align="center"><input type="text" class="input_comm" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center"  style="border-right:1px solid #930;">
                                            <select style="width:40px; height:22px;" name="myoption1">
                                            <option>P</option>
                                             <option>R</option>
                                            </select>
                                            </td>
                                          </tr>
                                          
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>2</td>
                                            <td  align="center">Recharge</td>
                                            <td  align="center">Data Card</td>
                                            <td  align="center">BSNL</td>
                                            <td  align="center"> <input type="text" class="input_comm" value="0" id="input_1" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select style="width:40px; height:22px;" name="myoption2">
                                            <option>P</option>
                                             <option>R</option>
                                            </select>
                                            </td>
                                          </tr>
                                            
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>3</td>
                                            <td  align="center" >Recharge</td>
                                            <td  align="center" >Data Card</td>
                                            <td  align="center" >IDEA</td>
                                            <td  align="center" ><input type="text" class="input_comm" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center"  style="border-right:1px solid #930;">
                                            <select style="width:40px; height:22px;" name="myoption3">
                                            <option>P</option>
                                             <option>R</option>
                                            </select>
                                            </td>
                                          </tr>
                                          
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td style="border-bottom:1px solid #930;"><input type="checkbox" class="case" /></td>
                                            <td style="border-bottom:1px solid #930;">4</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >Recharge</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >Data Card</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >MTS</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >
                                            <input type="text" class="input_comm" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                        
                                            <td  align="center" style="border-bottom:1px solid #930;border-right:1px solid #930;">
                                            <select style="width:40px;height:22px;" name="myoption4">
                                            <option>P</option>
                                             <option>R</option>
                                            </select>
                                            </td>
                                          </tr>
                                          
                                        </tbody>
                                        
                                        <tbody>
                                        <tr height="40px">
                                        <td style="border:none;"></td>
                                            <td style="border:none;"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>

                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"><input class="cls_btn" type="button" value="Update"  /></td>
                                            <td  align="center" style="border:none;"><input class="cls_btn" type="button" value="Reset" /></td>
                                        </tr>
                                        </tbody>
                                      
                                      </table>
             </td>
            </tr>
            
            </form>
            <tr>
              <td colspan="4" valign="top"></td>
            </tr>           
          </table></td>
      </tr>
      
      
    </table></td>
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
var val = document.rechargeForm1.myselects.value;
if(val == "chargemode")
{
	
	 return false;
}
if(val == "p")
{
document.rechargeForm1.myoption1.options[0].selected=true;
document.rechargeForm1.myoption2.options[0].selected=true;
document.rechargeForm1.myoption3.options[0].selected=true;
document.rechargeForm1.myoption4.options[0].selected=true;

}

if(val == "r")
{
document.rechargeForm1.myoption1.options[1].selected=true;
document.rechargeForm1.myoption2.options[1].selected=true;
document.rechargeForm1.myoption3.options[1].selected=true;
document.rechargeForm1.myoption4.options[1].selected=true;
}

}
</script>