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
function selectSearchBy(){

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
</script>
    </head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="86%" align="center" border="0">
        <tr>
          <td valign="top" align="center" class=""><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" bgcolor="">
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" ><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  height="40" align="left" valign="middle" class="heading_mgs" >
                      Prepaid Margin Master (PMM)
                      </td>
                    </tr>
                    <tr>
                      <td colspan="4" align="right"  valign="middle"></td>
                    </tr>
                    <tr>
                      <td colspan="4" >
					  <form name="SubmitForm" method="post">
					   <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  class="mydata_tabl" id="border">
                       
                       
                        <tr>
                       <td width="25%"></td>
					     <td  height="30" align="left"></td>
					     <td  align="left"></td>
					     <td ></td>
					     </tr>
                       
                       
					   <tr>
                       <td></td>
					     <td  height="30" align="left"><strong>Select User Type</strong></td>
					     <td  align="left">:</td>
					     <td ><select name="userType"  id="getMdsOp" onchange="selectSearchBy()">
					       <option value="Select">Select User Type</option>
					       <option value="adminUser">Portal Admin</option>
					       <option value="mds">Master Distributor</option>
					       <option value="egen">E-Gen API</option>
					       </select></td>
					     </tr>
                        
                                <tr id="otherOption" style="display:none">
                                 <td></td>
                                  <td height="30"><strong>Search By</strong></td>
                                  <td height="30">:</td>
                                  <td height="30" align="left" valign="middle">
								  <select name="searchBy1" id="mainService_3" onChange="selectService_5()">
								  <option value="Select" selected="selected">Select</option>
								  <option value="all" >All</option>
                                  
                                    <option value="id">User ID</option>
                                  </select></td>
                                </tr>
								<tr id="mdOption" style="display:none">
                                 <td></td>
                                  <td height="30"><strong>Search By</strong></td>
                                  <td height="30">:</td>
                                  <td height="30" align="left" valign="middle">
								  <select name="searchBy2" id="mainService_4" onChange="selectService_6()">
								  <option value="Select" selected="selected">Select</option>
                                  <option value="all" >All</option>
                                    <option value="id">User ID</option>
                                  </select></td>
                                </tr>
                                
                            <tr id="r1" style="display:none">
                             <td></td>
                            <td height="30"><strong>Enter User ID</strong></td>
                            <td height="30">:</td>
                            <td height="30" align="left" valign="middle" class="tabl2">
                            <input type="text"  class="tabl2" name="userID"/>
                            </td>
                                </tr>
                                <tr>
                                  <td colspan="3"></td>
                                  <td height="45" align="left" valign="middle"> 
                                  <input type="button" value="Search" class="cls_btn" onclick="validate()"/></td>
                                </tr>
					   </table>
					  </form>
				    </td></tr>
                    <tr>
                      <td valign="top" align="center">
					<form name="EditSlabForm" method="post">
					<%if(listData !=null){%>
					  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                          <tr>
                            <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                  <tr>
                                    <td height="15" align="left" valign="middle"   class="big1"></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">
									
                                    <table cellspacing="0" cellpadding="0" width="86%" align="center" class="tbls"  bgcolor="#a74312">
                                        <tbody>
                                          <tr align="center" height="20" class="hd">
                                            <td colspan="1" rowspan="2" align="center"><strong style="color:#FFF;">S.N</strong>.</td>
                                             <td align="center"  rowspan="2"><input type="checkbox"  id="selectall"  /></td>
                                            <td width="15%" rowspan="2" align="center"><strong style="color:#FFF;">User ID</strong></td>
                                            <td width="13%" rowspan="2" align="center"><strong style="color:#FFF;">Category</strong></td>
                                            <td height="20" colspan="6" align="center"><strong style="color:#FFF;">Prepaid Commission</strong></td>
                                          </tr>
                                          <tr align="center" height="20" class="hd">
                                            <td width="10%" align="center" height="25"><strong style="color:#FFF;">Deposit-1</strong></td>
                                            <td width="10%" align="center"><strong style="color:#FFF;">Rate-1</strong></td>
                                            <td width="11%" align="center"><strong style="color:#FFF;">Deposit-2</strong></td>
                                            <td width="10%" align="center"><strong style="color:#FFF;">Rate-2</strong></td>
                                            <td width="10%" align="center"><strong style="color:#FFF;">Deposit-3</strong></td>
                                            <td width="10%" align="center"><strong style="color:#FFF;">Rate-3</strong></td>
                                          </tr>
										  <% for(int i=0;i<size;i++){
										  HashMap map=(HashMap)listData.get(i);
										  
										  %>
                                          <tr align="center" bgcolor="#ffffff">
                                            <td width="3%" height="25" align="center" valign="middle"><%=i+1%></td>
                                            
                                            <td width="4%" align="center" valign="middle">
											<input type="checkbox" class="case" name="checkpartial" id="chek<%=i %>" value="<%=i%>" /></td>
                                            <td height="25" align="center" style="cursor:pointer"><a class="tid_link">
				<input type="hidden" name="userID<%=i%>" value="<%=map.get("userID").toString()%>"/><%=map.get("userID").toString()%></a> 
                                         <div  class="show_div hide" style="position:absolute">
                                         <table width="380" border="0" cellspacing="0" cellpadding="0" style="background-color:#fbf7eb; border:3px solid #a74312">
                                         <tr>
                                            <th colspan="3" align="center" valign="middle" bgcolor="" style="padding-left:5px" scope="row"><strong>System Detail</strong></th>
                                            </tr>
                                            <tr>
                                            <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
                                         </tr>
										 <%
	
	blockStatus=(String)map.get("blockStatus");
	if(blockStatus==null)blockStatus="";
	%>
                                         <tr>
										 
                                           <th width="140" align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Corporate ID</strong></th>
											<td width="15" bgcolor="#fbf7eb">:</td>
                                            <td width="156" bgcolor="#fbf7eb"><%=map.get("userID").toString()%></td>
                                         </tr>
                                        <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Company Name</strong></th>
                                          <td bgcolor="#FBF7EB">:</td>
                                          <td bgcolor="#FBF7EB"><%=map.get("companyName").toString()%></td>
                                        </tr>
                                        
                                        
                                       <tr>
                                          <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
                                       </tr>
                                       
                                       
                                      <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>User Name</strong></th>
                                          <td bgcolor="#FBF7EB">:</td><%nameOfUser=(String)map.get("nameOfUser");%>
                                          <td bgcolor="#FBF7EB"><%=nameOfUser%></td>
                                      </tr>
                                      <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Current Status</strong></th>
                                          <td bgcolor="#FBF7EB">:</td>
                                          <td bgcolor="#FBF7EB"><%=map.get("loginStatus").toString()%></td>
                                      </tr>
                                      <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Block Status</strong></th>
                                          <td bgcolor="">:</td>
                                          <td bgcolor=""><%=blockStatus%></td>
                                      </tr>
                                      <tr>
                                          <td height="5" colspan="3" align="center" valign="middle" style="padding-left:0px"><img src="images/bg_image24.jpg" width="278" height="1" /></td>
                                      </tr>
                                      <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Total Cash</strong></th>
                                          <td bgcolor="#FBF7EB">:</td>
                                          <td bgcolor="#FBF7EB">Rs <%=map.get("totalCash").toString()%></td>
                                       </tr>
                                       <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Used Cash</strong></th>
                                          <td bgcolor="#FBF7EB">:</td>
                                          <td bgcolor="#FBF7EB">Rs <%=map.get("usedCash").toString()%></td>
                                        </tr>
                                        <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Cutoff</strong></th>
                                          <td bgcolor="#FBF7EB">:</td>
                                          <td bgcolor="#FBF7EB">Rs <%=map.get("cutOff").toString()%></td>
                                       </tr>
                                       <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Total Balance</strong></th>
                                          <td bgcolor="">:</td>
                                          <td bgcolor="">Rs <%=map.get("totalBal").toString()%></td>
                                       </tr> 
                                       <tr>
                                          <th align="left" bgcolor="" style="padding-left:5px" scope="row"><strong>Available Balance</strong></th>
                                          <td bgcolor="">:</td>
                                          <td bgcolor="">Rs <%=map.get("availableBal").toString()%></td>
                                       </tr> 
                                       </table>
  
                                      </div> </td>
                                          <td align="center" valign="middle" style="padding-left:0px"><span class="style31" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:12px;font-weight:bold;">
      <select name="userCategory<%=i%>" id="select11" style="font-family:'Trebuchet MS', Tahoma, Arial, Verdana; font-size:11px; font-weight:bold;" >
		<%
		 userCategory=(String)map.get("userCategory");
		 %>
		 <option value="<%=userCategory%>" selected="selected"><%=userCategory%></option>
		 <%
		 if(userCategory.equalsIgnoreCase("Prepaid")){
		%>  
         <option value="Postpaid">Postpaid</option>
         <option value="Free">Free</option>
		 <%}else if(userCategory.equalsIgnoreCase("Postpaid")){%>
		 <option value="Prepaid">Prepaid</option>
         <option value="Free">Free</option>
		 <%}else if(userCategory.equalsIgnoreCase("Free")){%>
		 <option value="Prepaid">Prepaid</option>
		 <option value="Postpaid">Postpaid</option>
		 <%}%>
                                          </select>
                                          </span></td>
                                          <td align="center" valign="middle" style="padding-left:0px"><span class="style31">
            <input type="text" name="deposite1<%=i%>" size="10"  onkeypress="retet" id="deposite1<%=i+1%>" value="<%=map.get("userDepositeOne").toString()%>"/>
                                          </span></td>
                                          <td align="center" valign="middle" style="padding-left:0px"><span class="style31">
            <input type="text" name="rate1<%=i%>" size="10"  onkeypress="retet" id="rate1<%=i+1%>" value="<%=map.get("userRateOne").toString()%>"/>
                                          </span></td>
                                          <td align="center"><span class="style31">
            <input type="text" name="deposite2<%=i%>" size="10"  onkeypress="retet" id="deposite2<%=i+1%>" value="<%=map.get("userDepositetwo").toString()%>"/>
                                          </span></td>
                                          <td align="center"><span class="style31">
            <input type="text" name="rate2<%=i%>" size="10"  onkeypress="retet" id="rate2<%=i+1%>" value="<%=map.get("userRateTwo").toString()%>"/>
                                          </span></td>
                                          <td align="center"><span class="style31">
            <input type="text" name="deposite3<%=i%>" size="10"  onkeypress="retet" id="deposite3<%=i+1%>" value="<%=map.get("userDepositeThree").toString()%>"/>
                                          </span></td>
                                          <td align="center"><span class="style31">
            <input type="text" name="rate3<%=i%>" size="10"  onkeypress="retet" id="rate3<%=i+1%>" value="<%=map.get("userRateThree").toString()%>"/>
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
                            <td height="10" colspan="5" align="right" style="padding-right:80px;"> <input type="hidden" name="typeOfuser" value="<%=typeOfuser%>" />
							<input type="button" value="Save" class="cls_btn" onclick="editSlab(<%=size%>)"/></td>
                            
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
                        </table>
						<%}%>
						</form>
					</td>
                    </tr>
                    <tr>
                      <td colspan="4" height="65"></td>
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
function editSlab(i){
var count=0;
for(var j=0;j<i;j++)
{	
	var ck=document.getElementById("chek"+j).checked;
	
	if(ck == true)
	{  
		count=count+1;
		var k=j+1;
		var deposite1=document.getElementById("deposite1"+k).value;
		var rate1=document.getElementById("rate1"+k).value;
		var deposite2=document.getElementById("deposite2"+k).value;
		var rate2=document.getElementById("rate2"+k).value;
		var deposite3=document.getElementById("deposite3"+k).value;
		var rate3=document.getElementById("rate3"+k).value;
		
		
		
		if(deposite1=="")
		{
		alert("Please enter value");
		return false;
		}
		if(rate1=="")
		{
		alert("Please enter value");
		return false;
		}
		if(deposite2=="")
		{
		alert("Please enter value");
		return false;
		}
		if(rate2=="")
		{
		alert("Please enter value");
		return false;
		}
		if(deposite3=="")
		{
		alert("Please enter value");
		return false;
		}
		if(rate3=="")
		{
		alert("Please enter value");
		return false;
		}
	}
}
if(count==0)
{
	alert("Select check box for update CC info");
	return false;
}
document.EditSlabForm.action="ccInfoAction.action?param=updateSlabInfo";
document.EditSlabForm.submit();
}

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
document.SubmitForm.action="ccInfoAction.action?param=editSlabInfo";
document.SubmitForm.submit();
}
</script>

