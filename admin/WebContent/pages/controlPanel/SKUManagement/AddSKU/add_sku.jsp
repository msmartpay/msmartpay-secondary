<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
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


	
	$("#charge_val").click(function(){
	
	var val = $("#input_comm1").val();
	var vals = $("#input_comm2").val();
	
	
	
	if(val <= 10 && vals <= 10  )
	{
	//$(".input_charge").val(val)
	return true;
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
	var val =document.rechargeForm.EnterUserId.value;
	var usertype= document.rechargeForm.usertype.value;
	
	
	
	if(usertype == "Select")
	{
		alert("Please Select the User Type");
		return false;

	}
	else{
	document.getElementById('mytables').style.display="";
	}
}

function newPopup(url) {
	popupWindow = window.open(
url,'popUpWindow','height=300,width=400,left=200,top=10,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no,status=yes')
}

</script>

<style>
select{ color:#930;}
select option{ color:#930;}
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
               Add SKU
              </td>
            </tr>
			
			<form name="rechargeForm" method="post">
            <tr>
              <td colspan="4">
              
              
                
                </td>
            </tr>
			</form>
                   	
			 <tr>
              <td colspan="4">
              
             <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                  <tr>
                    <td align="center">
					<table width="90%"   cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					<tr><td height="25px" colspan="100%"></td></tr>
					    <tr>
                         <td width="30%"></td>
                          <td><strong>SKU Name</strong></td>
                          <td>:</td>
                          <td>
                         <input type="text"   />
                         </td>
                        </tr>
                        <tr>
                       <td width="30%"></td>
					  <td width="10%"><strong>Comm Type</strong></td>
					  <td width="10%">:</td>
					      <td width="60%">
                         <select>
                          <option>P</option>
                          <option>R</option>
                          </select>
                          
						  </td>
                      </tr>
                      <tr>
                         <td width="30%"></td>
                          <td><strong>Comm</strong></td>
                          <td>:</td>
                          <td>
                      <input type="text" class="mytextbox" maxlength="2" id="input_comm1"   />
                         </td>
                        </tr>
                        
					    <tr>
                        <td width="30%"></td>
                          <td><strong>Charge Type</strong></td>
                          <td>:</td>
                          <td>
                           <select>
                          <option>P</option>
                          <option>R</option>
                          </select>
                            </td>
                        </tr>
                        
                        <tr>
                        <td width="30%"></td>
                          <td><strong>Charge</strong></td>
                          <td>:</td>
                          <td>
                          <input type="text" class="mytextbox" maxlength="2" id="input_comm2"  />
                            </td>
                        </tr>
                        
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Category</strong></td>
                          <td>:</td>
                          <td>
                           <select>
                          <option selected="All">All</option>
                          </select>
                          <p style="float:right;padding:0;margin:0 235px 0 0;  font-size:12px;">
                          <a href="JavaScript:newPopup('addcategory.html');">Add Category</a>
                          </p>
                            </td>
                        </tr>
                        
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Main Service</strong></td>
                          <td>:</td>
                          <td>
                           <select>
                          <option selected="All">All</option>
                          </select>
                          <p style="float:right;padding:0;margin:0 212px 0 0;  font-size:12px;">
                          <a href="JavaScript:newPopup('addmainservice.html');">Add Main Service</a>
                          </p>
                            </td>
                        </tr>
                        
                         <tr>
                        <td width="30%"></td>
                          <td><strong>Sub Service</strong></td>
                          <td>:</td>
                          <td>
                           <select>
                          <option selected="All">All</option>
                          </select>
                           <p style="float:right;padding:0;margin:0 220px 0 0;; font-size:12px;">
                           <a href="JavaScript:newPopup('addsubservice.html');">Add Sub Service</a>
                           </p>
                            </td>
                        </tr>
                        
                        <tr align="center">
                        <td width="30%"></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button" id="charge_val" value="Submit" class="cls_btn" style="cursor:pointer;" onclick="sbmt()" /></td>
                        </tr>
                        <tr><td height="25px" colspan="100%"></td></tr>
                      </table>
                      
                     
					  </td>
                  </tr>
                </table>
                                      
             </td>
            </tr>
            <tr>
              <td colspan="4" valign="top">
              
              <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" style=" margin-top:15px;">
                                        <tbody>
                                   <tr class="hd" align="center" style="background:#a74312;height:25px;">
                                          <td><input name="checkAll" type="checkbox" id="selectall"   /></td>
                                          <td><strong>S.N.</strong></td>
                                            <td  align="center"><strong>Main Service</strong></td>
                                            <td  align="center"><strong>Sub Service</strong></td>
                                            <td  align="center"><strong>Category</strong></td>
                                            <td  align="center"><strong>SKU Name</strong></td>
                                            <td align="center"><strong>Commission</strong></td>
                                            <td  align="center"><strong>Comm Type</strong></td>
                                            <td align="center"><strong>Charge</strong></td>
                                            <td align="center" style="border-right:1px solid #930;"><strong>Charge Type</strong></td>
                                          </tr>
                                        
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>1</td>
                                            <td  align="center">Flights</td>
                                            <td  align="center">Domestic</td>
                                            <td  align="center">Postpaid</td>
                                            <td  align="center">DomesticAll</td>
                                            <td  align="center">
                                             <input type="text" class="input_comm" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td align="center"><select style="width:50px;height:21px;"><option>P</option><option>R</option></select></td>
                                            <td align="center">
                                            <input type="text" class="input_charge" value="0" style="width:65px; height:15px; color:#930;font-size:12px; opacity:0.8;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            </td>
                                            <td align="center" style="border-right:1px solid #930;">
                                            <select style="width:50px;height:21px;"><option>P</option><option>R</option></select>
                                            </td>
                                            
                                          </tr>
                                          
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>2</td>
                                            <td  align="center">Flights</td>
                                            <td  align="center">International</td>
                                            <td  align="center">Postpaid</td>
                                            <td  align="center">InternationalAll</td>
                                            <td  align="center">
                                            <input class="input_comm" type="text" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td align="center"><select style="width:50px;height:21px;"><option>P</option><option>R</option></select></td>
                                            <td align="center">
                                             <input type="text" class="input_charge" value="0" style="width:65px; height:15px; color:#930;font-size:12px; opacity:0.8;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            </td>
                                            <td align="center" style="border-right:1px solid #930;">
                                           <select style="width:50px;height:21px;"><option>P</option><option>R</option></select>
                                            </td>
                                          </tr>
                                            
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" class="case" /></td>
                                            <td>3</td>
                                            <td  align="center" >Utility</td>
                                            <td  align="center" >billpay</td>
                                            <td  align="center" >Postpaid</td>
                                            <td  align="center" >Tata DOCOMO</td>
                                            <td  align="center" >
                                            <input class="input_comm" type="text" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td align="center" ><select style="width:50px;height:21px;"><option>P</option><option>R</option></select></td>
                                            <td align="center">
                                            <input type="text" value="0" class="input_charge" style="width:65px; height:15px; color:#930;font-size:12px; opacity:0.8;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" />
                                            </td>
                                            <td align="center"  style="border-right:1px solid #930;">
                                            <select style="width:50px; height:21px;"><option>P</option><option>R</option></select>
                                            </td>
                                          </tr>
                                          
                                            <tr class="hd colr" align="center" style="background:#fff;height:25px;">
                                            <td style="border-bottom:1px solid #930;"><input type="checkbox" class="case" /></td>
                                            <td style="border-bottom:1px solid #930;">4</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >Utility</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >billpay</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >Postpaid</td>
                                            <td style="border-bottom:1px solid #930;" align="center" >Loop</td>
                                            
                                            <td style="border-bottom:1px solid #930;" align="center" >
                                            <input class="input_comm" type="text" value="0" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                            <td style="border-bottom:1px solid #930;" align="center" >
                                            <select style="width:50px;height:21px;"><option>P</option><option>R</option></select></td>
                                            <td align="center" style="border-bottom:1px solid #930;">
                                            <input type="text" class="input_charge" value="0" style="width:65px; height:15px; color:#930;font-size:12px; opacity:0.8;" 
                                            onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            <td  align="center" style="border-bottom:1px solid #930;border-right:1px solid #930;">
                                            <select style="width:50px;height:21px;"><option>P</option><option>R</option></select>
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
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"></td>
                                            <td style="border:none;" align="center"><input style="width:90px;color:#930;" type="button" value="Update" /></td>
                                            <td  align="center" style="border:none;"><input style="width:90px;color:#930;" type="button" value="Reset" /></td>
                                        </tr>
                                        </tbody>
                                      </table>
              
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