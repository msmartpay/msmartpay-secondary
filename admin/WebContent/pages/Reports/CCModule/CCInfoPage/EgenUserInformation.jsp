<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%
ArrayList listData=(ArrayList)request.getAttribute("listData");

int size=0;
if(listData!=null){
size=listData.size();
}
else{
size=-1;
}
String message="";
message=(String)request.getAttribute("message");
if(message==null)
message="";
// Define all variable
  String flag="";
  String clientType="";
  String blockStatus="";
  String userCategory="";
  String userAVIDBlock="";
  String avidmark="";
  String userAVIDUndertaking="";
  String underTakemark="";
  String userVerifyPan="";
  String verifyemark="";
  String userVerifyAgreement="";
  String agreementmark="";
    String nameOfUser="";
  String typeOfuser=(String)request.getAttribute("userType"); 
 System.out.println("---------------------------"+typeOfuser);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<script type="text/javascript" language="javascript" src="css/pop.js"></script>
<script type="text/javascript" language="javascript" src="css/func.js"></script>
<script type="text/javascript" language="javascript" src="css/function.js"></script>
<script type="text/javascript" language="javascript" src="css/jquery.js"></script>

<style type="text/css"> 

.hide{display:none;}
.popup { 
      background-color: #fff; 
      height: 120px; width: 400px; 
      border: 3px solid #a74312; 
      position: absolute; visibility: hidden; 
      font-family: Verdana, Geneva, sans-serif; 
      font-size: small; text-align: justify; 
      padding: 3px; overflow: auto; 
      z-index: 2; 
} 

.popup_5 { 
      background-color: #fff; 
      height: 150px; width: 380px; 
      border: 3px solid #a74312; 
      position: absolute; visibility: hidden; 
      font-family: Verdana, Geneva, sans-serif; 
      font-size: small; text-align: justify; 
      padding: 3px; overflow: auto; 
      z-index: 2; 
} 

.popup_bg { 
      position: absolute; 
      visibility: hidden; 
      height: 100%; width: 100%; 
      left: 0px; top: 0px; 
      filter: alpha(opacity=70); /* for IE */ 
      opacity: 0.7; /* CSS3 standard */ 
      background-color: #999; 
      z-index: 1; 
} 
.close_button { 
      font-family: Verdana, Geneva, sans-serif; 
      font-size: small; font-weight: bold; 
      float: right; color: #666; 
      display: block; text-decoration: none; 
      border: 1px solid #666; 
      padding: 0px 3px 0px 3px; 
} 
</style>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>
<SCRIPT language="javascript">
$(function(){
    $("#selectall").click(function () {
          $('.case').attr('checked', this.checked);
    });
    $(".case").click(function(){
		
        if($(".case").length == $(".case:checked").length) {
            $("#selectall").attr("checked", "checked");
        } else {
            $("#selectall").removeAttr("checked");
        }
 
    });
});
</SCRIPT>

<script>
$(document).ready(function(){
	
	$('.tid_link').mouseover(function(){
		 
		 $('.show_div').addClass('hide');
		 $(this).next('div').removeClass('hide');
		 
	  });
	
	$('.tid_link').mouseout(function(){
		 
		 //$('.show_div').addClass('hide');
		 $(this).next('div').addClass('hide');
		 
	
	  });
	
	
	
	});
</script>

<script language="javascript"> 
function openpopup(id){ 
      //Calculate Page width and height 
      var pageWidth = window.innerWidth; 
      var pageHeight = window.innerHeight; 
      if (typeof pageWidth != "number"){ 
      if (document.compatMode == "CSS1Compat"){ 
            pageWidth = document.documentElement.clientWidth; 
            pageHeight = document.documentElement.clientHeight; 
      } else { 
            pageWidth = document.body.clientWidth; 
            pageHeight = document.body.clientHeight; 
      } 
      }  
      //Make the background div tag visible... 
      var divbg = document.getElementById('bg'); 
      divbg.style.visibility = "visible"; 
        
      var divobj = document.getElementById(id); 
      divobj.style.visibility = "visible"; 
      if (navigator.appName=="Microsoft Internet Explorer") 
      computedStyle = divobj.currentStyle; 
      else computedStyle = document.defaultView.getComputedStyle(divobj, null); 
      //Get Div width and height from StyleSheet 
      var divWidth = computedStyle.width.replace('px', ''); 
      var divHeight = computedStyle.height.replace('px', ''); 
      var divLeft = (pageWidth - divWidth) / 2; 
      var divTop = (pageHeight - divHeight) / 2; 
      //Set Left and top coordinates for the div tag 
      divobj.style.left = divLeft + "px"; 
      divobj.style.top = divTop + "px"; 
      //Put a Close button for closing the popped up Div tag 
      if(divobj.innerHTML.indexOf("closepopup('" + id +"')") < 0 ) 
      divobj.innerHTML = "<a href=\"#\" onclick=\"closepopup('" + id +"')\"><span class=\"close_button\">X</span></a>" + divobj.innerHTML; 
} 
function closepopup(id){ 
      var divbg = document.getElementById('bg'); 
      divbg.style.visibility = "hidden"; 
      var divobj = document.getElementById(id); 
      divobj.style.visibility = "hidden"; 
} 
</script> 

<script language="javascript">
    function showOrHide() 
    {
        var div = document.getElementById("showOrHideDiv");
        if (div.style.display == "block") 
        {
            div.style.display = "none";
        }
        else 
        {
            div.style.display = "block";
        }
    }
	

	</script>
    
    
    
<script language="javascript" type="text/javascript">

function openTr()
{
	var getMdsOp=document.getElementById('getMdsOp').value;

	if(getMdsOp=="mds")
	{

	document.getElementById("mdOption").style.display="table-row";
	document.getElementById("otherOption").style.display="none";
	}

	else if(getMdsOp!="mds")
	{
	document.getElementById("otherOption").style.display="table-row";
	document.getElementById("mdOption").style.display="none";

	}
	else
	{
	document.getElementById("otherOption").style.display="none";
	document.getElementById("mdOption").style.display="none";
	}
}

function selectService_5()
{
var mainservice_3=document.getElementById('mainService_3').value;
if(mainservice_3=="Select")
{
document.getElementById('mainService_3').focus();
document.getElementById("r1").style.display="none";

}

else if(mainservice_3=="id")
{
document.getElementById("r1").style.display="table-row";


}
else
{
document.getElementById("r1").style.display="none";
}



}

function selectService_6()
{
var mainservice_3=document.getElementById('mainService_4').value;
if(mainservice_3=="Select")
{
document.getElementById('mainService_3').focus();
document.getElementById("r1").style.display="none";

}

else if(mainservice_3=="id")
{
document.getElementById("r1").style.display="table-row";


}
else
{
document.getElementById("r1").style.display="none";
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
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs">
                      User Information Master (UIM)
                      </td>
                    </tr>
                    <tr>
                      <td align="center" valign="middle" class="dyn_mgs"><%=message%></td>
                    </tr>
                    
                    <tr>
                      <td colspan="4" >
					  <form name="SubmitForm" method="post">
					   <table width="86%" height="206" border="0" align="center" cellpadding="0" cellspacing="0"  class="mydata_tabl" id="border">
                       
                       
                        <tr>
                          <td width="28%"></td>
                        <td width="17%"></td>
					     <td width="3%"  height="30" align="left"></td>
					     <td width="52%"  align="left"></td>
					     </tr>
                       
                       
					   <tr>
					     <td>&nbsp;</td>
                       <td><strong>Select User Type</strong></td>
					     <td  height="30" align="left">:</td>
					     <td  align="left"><select name="userType" id="getMdsOp" onchange="openTr()">
                           <option value="Select">Select User Type</option>
                           <option value="adminUser">Portal Admin</option>
                           <option value="mds">Master Distributor</option>
                           <option value="egen">E-Gen API</option>
                         </select></td>
					     </tr>
                        
								<tr id="otherOption" style="display:none">
								  <td>&nbsp;</td>
                                  <td height="30"><strong>Search By</strong></td>
                                  <td height="30">:</td>
                                  <td height="30" align="left" valign="middle">
								  <select name="searchBy1" id="mainService_3" onChange="selectService_5()">
								  <option value="Select" selected="selected">Select</option>
								  <option value="all">All</option>
                                    <option value="id">User ID</option>
                                  </select></td>
                                </tr>
						
                                <tr id="mdOption" style="display:none">
                                  <td>&nbsp;</td>
                                  <td height="30"><strong>Search By</strong></td>
                                  <td height="30">:</td>
                                  <td height="30" align="left" valign="middle">
								  <select name="searchBy2" id="mainService_4" onChange="selectService_6()">
								  <option value="Select" selected="selected">Select</option>
                                  <option value="all">All</option>
                                    <option value="id">User ID</option>
                                  </select></td>
                                </tr>
                                
                                <tr id="r1" style="display:none">
                                  <td>&nbsp;</td>
                                  <td><strong>Enter User ID</strong></td>
                                  <td height="30">:</td>
                                  <td height="30"><span class="tabl2">
                                    <input type="text"  class="tabl2" name="userID"/>
                                  </span></td>
                                </tr>
                            <tr >
                              <td></td>
                            <td></td>
                            <td height="37">&nbsp;</td>
                            <td height="37"><input name="button" type="button" class="cls_btn" onclick="validate()" value="Search"/></td>
                            </tr>
                                <tr>
                                  <td height="19" colspan="4" align="center"></td>
                                </tr>
					   </table>
					  </form>
				    </td></tr>
                    
                    <tr>
                      <td valign="top" align="center">
					  <form name="EditCCinfoForm"  method="post">
					  <%if(listData !=null){
					  %>
					  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="form11">
                          <tr>
                            <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                  <tr>
                                    <td height="15" align="left" valign="middle"   class="big1"></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">
									
									<table cellspacing="1" cellpadding="1" width="86%" align="center" class="tbls"  bgcolor="#a74312">
                                        <tbody>
                                          <tr align="center" height="20" class="hd">
                                            <td  rowspan="2" align="center"><strong style="color:#FFF;">S.N.</strong></td>
                                            <td align="center" rowspan="2"><input type="checkbox"  id="selectall"  /></td>
                                            <td width="13%" rowspan="2" align="center"><strong style="color:#FFF;">User ID</strong></td>
                                            <td width="9%" rowspan="2" align="center"><strong style="color:#FFF;">Category</strong></td>
                                            <td height="20" colspan="3" align="center" style="padding-left:0px"><strong style="color:#FFF;">Prepaid Commission</strong></td>
                                            <td colspan="2" align="center"><strong style="color:#FFF;">AVID Status</strong></td>
                                            <td colspan="2" align="center"><strong style="color:#FFF;">Legal Verification</strong></td>
                                            <td align="center" style="padding-left:8px"><strong style="color:#FFF;">Remark</strong></td>
                                          </tr>
                                          <tr align="center" height="20" class="hd">
                                            <td width="10%" align="center"><strong style="color:#FFF;">Min Deposit</strong></td>
                                            <td width="7%" align="center"><strong style="color:#FFF;">Min Rate</strong></td>
                                            <td width="7%" align="center"><strong style="color:#FFF;">Slab</strong></td>
                                            <td width="6%" align="center"><strong style="color:#FFF;">Block</strong></td>
                                            <td width="10%" align="center"><strong style="color:#FFF;">Undertaking</strong></td>
                                            <td width="6%" align="center"><strong style="color:#FFF;">PAN</strong></td>
                                            <td width="8%" align="center"><strong style="color:#FFF;">Agreement</strong></td>
                                            <td align="center" valign="middle" style="padding-left:0px"><strong style="color:#FFF;">Remember</strong></td>
                                          </tr>
										  <% for(int i=0;i<listData.size();i++){
										  HashMap map=(HashMap)listData.get(i);
										  
										  %>
                                          <tr align="center" bgcolor="#ffffff">
  <td width="3%" height="25" align="center" valign="middle"><%=i+1%></td>
  <td width="3%" align="center" valign="middle"><input type="checkbox" class="case" name="checkpartial" id="chek<%=i %>" value="<%=i%>" /></td>
  <td height="25" align="center" style="cursor:pointer"><a class="tid_link">
  <input type="hidden" name="userID<%=i%>" value="<%=map.get("userID").toString()%>"/><%=map.get("userID").toString()%></a> 
  
  
<div  class="show_div hide" style="position:absolute">
 <table width="380" border="0" cellspacing="0" cellpadding="0" style="background-color:#fbf7eb; border:3px solid #a74312">
    <tr>
      <th colspan="3" align="center" valign="middle" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>System Detail</strong></th>
    </tr>
     <tr>
      <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px; border:none;"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
  </tr>
    <tr>
	<%
	
	blockStatus=(String)map.get("blockStatus");
	if(blockStatus==null)blockStatus="";
	%>
      <th width="140" align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Corporate Agent ID</strong></th>
      <td width="15" bgcolor="#fbf7eb" style="border:none;">:</td>
      <td width="156" bgcolor="#fbf7eb" style="border:none;"><%=map.get("userID").toString()%></td>
    </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Portal Name</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td>
      <td bgcolor="#FBF7EB" style="border:none;"><%=map.get("companyName").toString()%></td>
  </tr>
   <tr>
      <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px;border:none;"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
  </tr>
    
    
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>User Name</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td><%nameOfUser=(String)map.get("nameOfUser");%>
      <td bgcolor="#FBF7EB" style="border:none;"><%=nameOfUser%></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Current Status</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td>
      <td bgcolor="#FBF7EB" style="border:none;"><%=map.get("loginStatus").toString()%></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Block Status</strong></th>
      <td bgcolor="" style="border:none;">:</td>
      <td bgcolor="" style="border:none;"><%=blockStatus%></td>
  </tr>
   <tr>
      <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px;border:none;"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Total Cash</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td>
      <td bgcolor="#FBF7EB" style="border:none;">Rs <%=map.get("totalCash").toString()%></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Used Cash</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td>
      <td bgcolor="#FBF7EB" style="border:none;">Rs <%=map.get("usedCash").toString()%></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Cutoff</strong></th>
      <td bgcolor="#FBF7EB" style="border:none;">:</td>
      <td bgcolor="#FBF7EB" style="border:none;">Rs <%=map.get("cutOff").toString()%></td>
  </tr>
    <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Total Balance</strong></th>
      <td bgcolor="" style="border:none;">:</td>
      <td bgcolor="" style="border:none;">Rs <%=map.get("totalBal").toString()%></td>
  </tr> 
  <tr>
      <th align="left" bgcolor="" style="padding-left:5px;border:none;" scope="row"><strong>Available Balance</strong></th>
      <td bgcolor="" style="border:none;">:</td>
      <td bgcolor="" style="border:none;">Rs <%=map.get("availableBal").toString()%></td>
  </tr> 
</table>
 
</div> 




</td>
  <td align="center" valign="middle" style="padding-left:0px"><span class="style31" style="font-family:'Trebuchet MS'; font-size:12px;                                                font-weight:bold;">
  <%=map.get("userCategory")%>
    
  </span></td>
  <td align="center" valign="middle" style="padding-left:0px"><span class="style31">
    <input name="userMinDeposite<%=i%>" type="text" id="lkk"  onkeypress="retet" value="<%=map.get("userMinDeposite").toString()%>" size="10"/>
  </span></td>
  <td align="center" valign="middle" style="padding-left:0px">
  <input type="text" name="userMinDepositeRate<%=i%>" value="<%=map.get("userMinRate").toString()%>"/></td>
  <td height="25" align="center" style="cursor:pointer"><a class="tid_link">Check</a> 
<div  class="show_div hide" style="position:absolute">
 <table width="293" border="1" cellspacing="0" cellpadding="0" style="background-color:#fbf7eb; border:3px solid #a74312">
    <tr>
      <th colspan="3" align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row"><strong>Prepaid Commission Rate</strong></th>
    </tr>
     
    <tr>
      <th width="70" align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row"><strong>Slab</strong></th>
      <th width="70" align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row">Deposit</th>
      <th width="70" align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row">Rate</th>
    </tr>
    <tr>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row">1</td>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row"><%=map.get("userDepositeOne").toString()%></td>
      <td align="center" valign="middle" bgcolor="#FBF7EB"><%=map.get("userRateOne").toString()%>%</td>
  </tr>
    <tr>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row">2</td>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row"><%=map.get("userDepositetwo").toString()%></td>
      <td align="center" valign="middle" bgcolor="#FBF7EB"><%=map.get("userRateTwo").toString()%>%</td>
  </tr>
    <tr>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row">3</td>
      <td align="center" valign="middle" bgcolor="" style="padding-left:0px" scope="row"><%=map.get("userDepositeThree").toString()%></td>
      <td align="center" valign="middle" bgcolor="#FBF7EB"><%=map.get("userRateThree").toString()%>%</td>
  </tr> 
</table>
  
</div> </td>
  <td align="center"><span class="style31" style="font-family:'Trebuchet MS'; font-size:12px;                                                font-weight:bold;"><input type="hidden" name="typeOfuser" value="<%=typeOfuser%>"/>
    <select name="AVIDBlock<%=i%>" id="select2" style="font-family:'Trebuchet MS';color:#930; font-size:12px; font-weight:bold;" >      
	<%
		 userAVIDBlock=(String)map.get("userAVIDBlock");
		 if(userAVIDBlock.equalsIgnoreCase("Y"))
		 avidmark="N";
		 else
		 avidmark="Y";
		
		 %>                                        
	  <option value="<%=userAVIDBlock%>" selected="selected"><%=userAVIDBlock%></option>
	  <option value="<%=avidmark%>"><%=avidmark%></option>
    </select>
  </span></td>
  <td align="center"><span class="style31" style="font-family:'Trebuchet MS'; font-size:12px;                                                font-weight:bold;">
    <select name="AVIDUndertaking<%=i%>" id="select3" style="font-family:'Trebuchet MS';color:#930; font-size :12px; font-weight:bold;" >
		<%
		 userAVIDUndertaking=(String)map.get("userAVIDUndertaking");
		 if(userAVIDUndertaking.equalsIgnoreCase("Y"))
		 underTakemark="N";
		 else
		 underTakemark="Y";
		
		 %> 
      <option value="<%=userAVIDUndertaking%>" selected="selected"><%=userAVIDUndertaking%></option>
	  <option value="<%=underTakemark%>"><%=underTakemark%></option>
    </select>
  </span></td>
  <td align="center"><span class="style31" style="font-family:'Trebuchet MS'; font-size:12px;                                                font-weight:bold;">
    <select name="VerifyPan<%=i%>" id="select5" style="font-family:'Trebuchet MS';color:#930; font-size:12px; font-weight:bold;" >
	<%
		 userVerifyPan=(String)map.get("userVerifyPan");
		 if(userVerifyPan.equalsIgnoreCase("Y"))
		 verifyemark="N";
		 else
		 verifyemark="Y";
		
		 %> 
      <option value="<%=userVerifyPan%>" selected="selected"><%=userVerifyPan%></option>
	  <option value="<%=verifyemark%>"><%=verifyemark%></option>
    </select>
  </span></td>
  <td align="center"><span class="style31" style="font-family:'Trebuchet MS'; font-size:12px;                                                font-weight:bold;">
    <select name="VerifyAgreement<%=i%>" id="select7" style="font-family:'Trebuchet MS'; font-size:12px;color:#930; font-weight:bold;" >
	<%
		 userVerifyAgreement=(String)map.get("userVerifyAgreement");
		 if(userVerifyAgreement.equalsIgnoreCase("Y"))
		 agreementmark="N";
		 else
		 agreementmark="Y";
		
		 %> 
     <option value="<%=userVerifyAgreement%>" selected="selected"><%=userVerifyAgreement%></option>
	  <option value="<%=agreementmark%>"><%=agreementmark%></option>
      </select>
  </span></td>
  <td width="18%" align="center" valign="middle" style="padding-left:0px"><span class="style31">
    <input type="text" name="remark<%=i%>" size="25"  value="<%=map.get("userRemark").toString()%>" id="remark<%=i+1%>"/>
  </span></td>
  </tr>
 										  
										<%}%>
  </tbody>
                           </table>

						   		</td>
                                    </tr>
                           </tbody>
                           </table></td>
                          </tr>
                          <tr>
                            <td colspan="5" height="15"></td>
                          </tr>
                          <tr>
                            <td height="10" colspan="5" align="right" style="padding-right:80px;">
							<input type="button" value="Edit Slab" class="cls_btn" onclick="editSlabFunction()"/>
                             
							<input type="button" value="Update" class="cls_btn" onclick="editInfo(<%=size%>)"/></td>
                          </tr>
                          <tr>
                            <td td valign="top"></td>
                          </tr>
                          <tr>
                            <td valign="top"></td>
                          </tr>
                          <tr>
                               <td valign="top">
                                    
                              </td>
                           </tr>
                           <tr>
                            <td colspan="5" height="10"></td>
                          </tr><tr>
                            <td height="10" colspan="5" align="right" valign="middle">&nbsp;</td>
                          </tr>
                          <tr>
                            <td colspan="5" height="10"></td>
                          </tr>
                          
                        </table>
						<%}%>
						</form>
						</td>
                    </tr>
                    <tr>
                      <td colspan="4" height="80"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" height="106" align="center" valign="bottom"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>

</body>
</html>
<script language="javascript">
function validate(){
	var userType=document.SubmitForm.userType.value;
	if(userType=="Select"){
	alert('Please select User type');
	return false;
	}
	var searchBy="";
	if(userType=="mds"){
	searchBy=document.SubmitForm.searchBy2.value;
	}else{
	searchBy=document.SubmitForm.searchBy1.value;
	}
	if(searchBy=="Select"){
	alert('Please select User type');
	return false;
	}
	if(searchBy=="id"){
	var userID=document.SubmitForm.userID.value;
	if(userID==""){
	alert('Please select User type');
	return false;
	}
	}
	document.SubmitForm.action="ccInfoAction.action?param=getCCInfo";
	document.SubmitForm.submit();
}
function editInfo(i)
{
var count=0;
for(var j=0;j<i;j++)
{	
	var ck=document.getElementById("chek"+j).checked;
	
	if(ck == true)
	{  
		count=count+1;
		var k=j+1;
		var remark=document.getElementById("remark"+k).value;
		
		
		if(remark=="")
		{
		alert("Please enter Remark");
		return false;
		}
	}
}
if(count==0)
{
	alert("Select check box for update CC info");
	return false;
}
document.EditCCinfoForm.action="ccInfoAction.action?param=updateCCInfo";
document.EditCCinfoForm.submit();


}
function editSlabFunction(){
document.EditCCinfoForm.action="ccInfoAction.action?param=editSlab";
document.EditCCinfoForm.submit();
}
</script>
