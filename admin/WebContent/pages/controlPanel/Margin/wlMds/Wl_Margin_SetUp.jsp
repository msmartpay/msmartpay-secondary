<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<!--<SCRIPT type="text/javascript" src="scripts/digitonly.js"></script>-->
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

$("#charge_val").click(function(){

			var a = $(".colr").length;
			//alert("a"+a);
                
				for(var i=0; i<=a; i++)
				{
                
                var val = $("#input_comm").val();
                if(val <= 100)
                {
                $(".input_comm").val(val)
                var mds_val = 100;
                
                var total= mds_val - val;
				
				
                
                //mds margin code start
                var cate_margin = $(".cate_margin"+i).val();
                var client_share= $("#input_comm").val();
                var mdsmargin = cate_margin * (100 - client_share)/100;
                
				//alert (i);
				//alert("cate_margin"+cate_margin);
				//alert("client_share"+client_share);
				//alert("mdsmargin"+mdsmargin);
				
                var k = parseFloat(mdsmargin);
                                var z= Math.round(k * 100) / 100;
                

                $(".mdsmargin"+i).val(z);
                //mds margin code stop
                
                $(".mds_share").val(total)
                
                
                
                }
                
                else
                {
                alert("Check Input Value.");
                return false;
                }
				
				
				
				
				}
				
                })

	
	
	//part of code two decimal
	var txt = document.getElementById('input_charge');
    txt.addEventListener('keyup', myFunc);
    
    function myFunc(e) {
        var val = this.value;
        var re = /^([0-9]+[\.]?[0-9]?[0-9]?|[0-9]+)$/g;
        var re1 = /^([0-9]+[\.]?[0-9]?[0-9]?|[0-9]+)/g;
        if (re.test(val)) {
            //do something here
    
        } else {
            val = re1.exec(val);
            if (val) {
                this.value = val[0];
            } else {
                this.value = "";
            }
        }
    }
	
	//part of code two decimal
	var txt = document.getElementById('input_comm');
    txt.addEventListener('keyup', myFunc);
    
    function myFunc(e) {
        var val = this.value;
        var re = /^([0-9]+[\.]?[0-9]?[0-9]?|[0-9]+)$/g;
        var re1 = /^([0-9]+[\.]?[0-9]?[0-9]?|[0-9]+)/g;

        if (re.test(val)) {
            //do something here
    
        } else {
            val = re1.exec(val);
            if (val) {
                this.value = val[0];
            } else {
                this.value = "";
            }
        }
    }
	
	
});
</script>
<script type="text/javascript">

function resetfunction()
{
document.update.action="WLMergin.action?param=getwlMdsMarginPage";
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
</script>

<script>
function myfunc()
{

var val = document.update.myselects.value;
//alert(val)
if(val == "commtype")
{
	 return false;
}
if(val == "P")
{
document.update.myoption1.options[0].selected=true;
document.update.myoption2.options[0].selected=true;
document.update.myoption3.options[0].selected=true;
document.update.myoption4.options[0].selected=true;

}

if(val == "R")
{
document.update.myoption1.options[1].selected=true;
document.update.myoption2.options[1].selected=true;
document.update.myoption3.options[1].selected=true;
document.update.myoption4.options[1].selected=true;
}

}
</script>

<script>
function myfunc1()
{
var val = document.update.myselectss.value;
if(val == "chargetype")
{
	
	 return false;
}
if(val == "P")
{
document.update.myoption5.options[0].selected=true;
document.update.myoption6.options[0].selected=true;
document.update.myoption7.options[0].selected=true;
document.update.myoption8.options[0].selected=true;

}

if(val == "R")
{
document.update.myoption5.options[1].selected=true;
document.update.myoption6.options[1].selected=true;
document.update.myoption7.options[1].selected=true;
document.update.myoption8.options[1].selected=true;
}

}
</script>
<script language="javascript">
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

function getMDID()
{
	
	var xmlhttp;
	var type=document.rechargeForm.EnterUserId.value;
	if(type=="")
		{
			alert('Please enter a valid Client ID.');
			document.rechargeForm.EnterUserId.focus();
			return false;
		}
	
	
	var url = "WLMergin.action?param=getMdid&Id="+type;
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
//alert(xmlhttp.responseText)
document.getElementById("getVal").innerHTML=xmlhttp.responseText;
	}
}
	xmlhttp.open("post",url,true);
	xmlhttp.send();
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
		var wl=document.getElementById("clientshare_1"+k).value;
		//var md=document.getElementById("md"+k).value;		
		
		

		if(wl=="")
		{
			alert("Please Set Commission");
			document.getElementById("clientshare_1"+k).focus();
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
function sbmt()
{
	var id=document.rechargeForm.MDSid.value;
	if(id=="select")
		{
			alert('Please enter a valid Client ID.');
			document.rechargeForm.EnterUserId.focus();
			return false;
		}
	document.rechargeForm.action="WLMergin.action?param=getWLMDDetails";
	document.rechargeForm.submit();
}
function download()
{
	var id=document.rechargeForm.MDSid.value;
	if(id=="select")
		{
			alert('Please enter a valid Client ID.');
			document.rechargeForm.EnterUserId.focus();
			return false;
		}
	document.rechargeForm.action="WLMergin.action?param=downloadWLMDDetails";
	document.rechargeForm.submit();
}
</script>



</head>
<%
//ArrayList CategoryName=(ArrayList) session.getAttribute("CategoryName");
//ArrayList MainServiceName=(ArrayList) session.getAttribute("MainServiceName");
//ArrayList SubServiceName=(ArrayList) session.getAttribute("SubServiceName");
ArrayList SKUData=(ArrayList) request.getAttribute("getDetails");
//System.out.println("SKUData  :"+SKUData);
int sizeOfSkuData=0;
if(SKUData!=null){sizeOfSkuData=SKUData.size();}
else{sizeOfSkuData=-1;}
System.out.println("sizeOfSkuData is "+sizeOfSkuData);
String Displaycategory=(String)request.getAttribute("Category");
//String DisplaymainService=(String)request.getAttribute("DisplaymainService");
String DisplayMDSid=(String)request.getAttribute("MDSid");
String shareType=(String)request.getAttribute("shareType");
String DisplayClientID=(String)request.getAttribute("ClientID");

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
  <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
      <tr>
        <td valign="top" align="center" class="rounded-corners" >
        
        <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
            <tr>
              <td  height="40" align="left" valign="middle" class="heading_mgs">
              Margin Set > Client-MDS
              </td>
            </tr>
            
			<tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
			<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
                   	
			 <tr>
              <td colspan="4">
              
             <table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border" style="margin-bottom: 25px;">
                  <tr>
                    <td align="center">
                    <form name="rechargeForm" method="post">
					  
					<table width="90%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
					   
                      	<tr>
                          <td width="25%"></td>
                          <td valign="middle" height="25px"></td>
                          <td valign="middle"></td>
                          <td valign="middle"></td>
                          <td align="left"></td>
                        </tr>
					    <tr>
                    		<td align="center"></td>
                    		<td width="10%" valign="middle"><strong>Client ID</strong></td>
						  	<td width="7%" valign="middle">:</td>
					      	<td width="32%" valign="middle" ><input type="text" name="EnterUserId" style="width: 135px;"   />
						  	&nbsp;&nbsp;<input type="button" style="color:#930;width: 50px" value="Go" onclick="getMDID()" />
					      	</td>
                          	<td width="26%" align="left"></td>
                      </tr>
                      
					    <tr>
                        <td width="25%" height="42"></td>
                          <td valign="middle"><strong>MDS ID</strong></td>
                          <td valign="middle">:</td>
                          <td valign="middle">
						  
						  <select name="MDSid" id="getVal">
                              <option value="select">------- Select -------</option>
                            </select>
							
							</td>
                            <td align="left"></td>
                        </tr>
                        
                        <tr>
                         <td width="25%" height="29"></td>
                          <td valign="middle"><strong>Service</strong></td>
                          <td valign="middle">:</td>
                          <td valign="middle">
                          <select name="serviceType">
                          <option selected="selected" value="Recharge">Recharge</option>
                          <option value="Utility">Utility</option>
                          <option value="DMR">DMR</option>
                          <option value="R-DMR">R-DMR</option>
                          <option value="BEDMR">BEDMR</option>
                          <option value="Flights">Flights</option>
                          <option value="Bus">Bus</option>
                          <option value="Hotel">Hotel</option>
                          <option value="PanCard">PayCard</option>
                          <option value="TestMerit"> TestMerit</option>
                          <option value="DTH">DTH-X</option>
                          </select>
                         </td>
                         <td width="26%" align="left"></td>
                        </tr>
                        
                        <tr>
                         <td width="25%" height="29"></td>
                          <td valign="middle"><strong>Select Share Type</strong></td>
                          <td valign="middle">:</td>
                          <td valign="middle">
                          <select name="shareType">
                          
                          		<option selected="selected" value="Commission">Commission</option>
                          		<option value="Surcharge">Surcharge</option>
                          
                          </select>
                         </td>
                         <td width="26%" align="left"></td>
                        </tr>
                        
                        <tr align="center">
                        <td width="25%" height="35" ></td>
                        <td align="right"></td>
						<td align="center"></td>
                        <td align="left" valign="middle">
                        <input type="button" value="Submit" class="cls_btn" onclick="sbmt()" />
                        <input type="button" value="Download" class="cls_btn" onclick="download()" />
                        </td>
                        </tr>
                        
                        <tr>
                          <td width="25%"></td>
                          <td valign="middle" height="25px"></td>
                          <td valign="middle"></td>
                          <td valign="middle"></td>
                          <td align="left"></td>
                        </tr>
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
              <form name="update" method="post" action="WLMergin.action?param=UpdateShare">
              <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls" id="mytables">
                                        <tbody>
                                        
                                         <tr class="hd tabulardata" align="center" >
                                            <td width="5%"  align="center"><strong>Category</strong></td>
							                 <td width="5%"  align="center"><strong><%=Displaycategory%><input type="hidden" name="Displaycategory" value="<%=Displaycategory%>"/></strong></td>
							                 <td width="10%"  align="center"><strong>Client ID</strong></td>
							                 <td width="10%"  align="center"><strong><%=DisplayClientID%><input type="hidden" name="DisplayClientID" value="<%=DisplayClientID%>"/>
							                 	<input type="hidden" name="shareType" value="<%=shareType%>"/></strong></td>
							                 <td width="10%" align="center"><strong>MD ID</strong></td>
							                 <td width="10%" align="center"><strong><%=DisplayMDSid%><input type="hidden" name="DisplayMDID" value="<%=DisplayMDSid%>"/></strong></td>
                                             <td width="7%" align="center"><strong>Client Share %</strong></td>
                                             <td width="7%" style="border-right:1px solid #930;"><input id="input_comm" class="input_comm mytextbox" type="text"  maxlength="5" value="0" style="width:75px; height:15px; color:#000;font-size:12px; opacity:0.6;" 
                                             onfocus="if(this.value =='0') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                             <td width="7%" align="center"><input type="button" value="Go" id="charge_val"  />  </td>
                                          </tr>
                                   <tr class="hd tabulardata" align="center">
                                          <td><input name="checkAll" type="checkbox" class="Trebuchet_normal" onclick="check()" value="" /></td>
                                          <td ><strong style="color:#FFF;">S.N.</strong></td>
                                    
                                            <td  align="center"><strong style="color:#FFF;">Service</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Sub-Service</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Operator</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Category Margin</strong></td>
                                            <td  align="center"><strong style="color:#FFF;">Client Share %</strong></td>
                                            <td align="center"><strong style="color:#FFF;">MDS Share %</strong></td>
                                            <td align="center" style="border-right:1px solid #930;"><strong style="color:#FFF;">MDS Margin</strong></td>
                                          </tr>
										  <%
										  for(int i=0;i<sizeOfSkuData;i++){
										  HashMap map=(HashMap)SKUData.get(i);
										 // System.out.println(" siz e is :"+map);
										  %>
										  <tr class="colr" align="center" style="background:#fff;height:25px;">
                                            <td><input type="checkbox" name="checkpartial" id="chek<%=i %>"  value="<%=i%>"/></td>
                                            <td><%=i+1%>
                                            <input type="hidden" name="md_id<%=i%>" value="<%=map.get("MD_ID")%>" />
                                            <input type="hidden" name="cat<%=i%>" value="<%=map.get("Category")%>" />
                                            </td>
                                     	
											
                                            <td  align="center"><%=map.get("Main_Service")%> <input type="hidden" name="mser<%=i%>" value="<%=map.get("Main_Service")%>" /></td>
                                            <td  align="center"><%=map.get("Sub_Service")%> <input type="hidden" name="ser<%=i%>" value="<%=map.get("Sub_Service")%>" /></td>
                                            <td  align="center"><%=map.get("SKU_Name")%> <input type="hidden" name="sku<%=i%>" value="<%=map.get("SKU_Name")%>" /></td>
                                            <td  align="center" ><%=map.get("SKU_Comm")%>
											 <input class="cate_margin<%=i%>" type="hidden" value="<%=map.get("SKU_Comm")%>" style="width:50px; height:15px; color:#930;font-size:12px; opacity:0.9;"  />  </td>   
											
											<td  align="center"><input type="text" name="clientShare<%=i%>" id="clientshare_1<%=i+1%>" maxlength="3" class="input_comm mytextbox1" value="<%=map.get("Client_Share") %>" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" 
                                            onfocus="if(this.value =='') this.value=''"  onblur="if(this.value=='') this.value='0'" /></td>
                                            
                                             <td align="center" id="mds_share1" ><input style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;" type="text" class="mds_share" name="MD_Share<%=i%>" value="<%=map.get("MD_Share") %>" /></td>
                                           <td align="center"   style="border-right:1px solid #930;">
										    <input type="text" id="mdsmargin<%=i+1%>" class="mdsmargin<%=i%>" value="<%=map.get("MDSMargin")%>" style="width:40px; height:15px; color:#930;font-size:12px; opacity:0.9;"  /></td>
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
                                            <td style="border:none;" align="center"><input style="color: #fff;"  type="button" value="Update" onclick="checkMargin(<%=sizeOfSkuData %>)" /></td>
                                            <td style="border:none;" align="center"><input style="color: #fff;" type="button" value="Reset" onclick="resetfunction()" /></td>
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




