<%@ page import = "java.util.HashMap "%> 
<%
HashMap map=(HashMap)request.getAttribute("distributorInfo");
session.setAttribute("distributorDetails",map);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Distributor Profile</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery.js"></script>

<script type="text/javascript" language="javascript">
$(document).ready(function(){
	
	$(".accordion h3:first").addClass("active");
	$(".accordion h4:not(:first)").hide();

	$(".accordion h3").click(function(){
		$(this).next("h4").slideToggle("fast")
		.siblings("h4:visible").slideUp("fast");
		$(this).toggleClass("active");
		$(this).siblings("h3").removeClass("active");
	});

});
</script>

<style type="text/css">
.accordion {
	width: 700px;
}
.accordion h3 {
	background: #ffffff url(images/arrow-square.gif) no-repeat right -51px; border:#b8cbe1 solid 1px; 
	padding: 7px 15px;
	margin: 0; font-size:12px; font-weight:bold; font-family:"Trebuchet MS"; color:#000000;
	border-bottom: #b8cbe1 1px solid;
	cursor: pointer;
}
.accordion h3:hover {
	background-color: #dbe5f1; color:#000000;
}
.accordion h3.active {
	background-position: right 5px;
}
.accordion h4 {
	background: #ffffff;
	
}
</style>








</head>
<body>
<center>
  <table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
    <tr>
      <td align="left" height="130" width="1000"><%@ include file="../../header.jsp"%></td>
    </tr>
    <tr>
      <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px">Profile Management</td>
          </tr>
          <tr>
            <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
                <tr>
                  <td width="100%" align="center">	<div class="accordion" style="padding:20px 0px 20px 0px">
<table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11">
          <tr><td>           
                      
                           <h3>Personal Details</h3>
                              <h4><table width="100%"  cellspacing="2" cellpadding="0" align="center" style="border:1px solid #b8cbe1; border-top:0px">
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Authorised Person Name</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("distributor_name")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Date of Birth</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String dob=(String)map.get("Date_Of_Birth");
									if(dob==null){
									dob="-";
									}
									
									%>
                                      <td height="25"><%=dob%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Gender</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String gender=(String)map.get("Gender");
									if(gender==null){
									gender="-";
									}
									
									%>
                                      <td height="25"><%=gender%> </td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Merital Status</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String maritalStatus=(String)map.get("Marital_Status");
									if(maritalStatus==null){
									maritalStatus="-";
									}
									
									%>
                                      <td height="25" width="65%"><%=maritalStatus%><span style="padding-left:14px"></span></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Your Occupation</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String occupation=(String)map.get("Occupation");
									if(occupation==null){
									occupation="-";
									}
									
									%>
                                      <td height="25" width="65%"><%=occupation%><span style="padding-left:14px"></span></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Company / Firm Name</td>
                                <td align="center">:</td>
                                <td ><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("company_name")%><span style="padding-left:15px"></span></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Designation</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String designation=(String)map.get("Designation");
									if(designation==null){
									designation="-";
									}
									
									%>
                                      <td height="25" width="65%"><%=designation%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">PAN/ S. Tax/ TIN  Number</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String panNum=(String)map.get("PAN_TIN_No");
									if(panNum==null){
									panNum="-";
									}
									
									%>
                                      <td height="25" width="65%"><%=panNum%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                            </table></h4>
                     
                      <!--personal-->
                      <h3>Office Address</h3><h4><table width="100%"  cellspacing="2" cellpadding="0" align="center" style="border:1px solid #b8cbe1; border-top:0px">
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Address Line 1</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("address1")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Address Line 2</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("address2")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Country & State</font></td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25" width="65%"><%=map.get("country")%>,<%=map.get("state")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
								
                                <td align="left" style="padding-left:15px">City </td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25" width="65%"><%=map.get("city")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Nearby Land Mark</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String landmark=(String)map.get("landmark");
									if(landmark==null){
									landmark="-";
									}
									
									%>
                                      <td height="25"><%=landmark%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Mobile</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("mobile_no")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Phone & Fax</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("office_phone1")%>,<%=map.get("fax_no")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Email ID</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
                                      <td height="25"><%=map.get("email_id")%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Website</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String website=(String)map.get("Website");
									if(website==null){
									website="-";
									}
									
									%>
                                      <td height="25"><%=website%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                            </table></h4>
                     
                      <!--office-->
                     <h3>Residential Address Detail</h3><h4> <table width="100%"  cellspacing="2" cellpadding="0" align="center" style="border:1px solid #b8cbe1; border-top:0px">
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Address Line 1</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String resAdd1=(String)map.get("Res_Address1");
								    if(resAdd1==null){
									resAdd1="-";
									}
									
									%>
                                      <td height="25"><%=resAdd1%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Address Line 2</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>	<%
									String resAdd2=(String)map.get("Res_Address2");
								    if(resAdd2==null){
									resAdd2="-";
									}
									
									%>
                                      <td height="25"><%=resAdd2%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Country & State</font></td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String resCountry=(String)map.get("Res_Country");
									String resState=(String)map.get("Res_State");
								    if(resCountry==null){
									resCountry="-";
									}
									if(resState==null){
									resState="-";
									}
									%>
									
                                      <td height="25" width="65%"><%=resCountry%>,<%=resState%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">City </td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String resCity=(String)map.get("Res_City");
									
									if(resCity==null){
									resCity="-";
									}
									%>
                                      <td height="25" width="65%"><%=resCity%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Nearby Land Mark</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String resLandmark=(String)map.get("Res_Landmark");
									
									if(resLandmark==null){
									resLandmark="-";
									}
									%>
                                      <td height="25"><%=resLandmark%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Mobile</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String resMobNo=(String)map.get("Res_Mobile_No");
									if(resMobNo==null){
									resMobNo="-";
									}
									else{
									resMobNo="+91-"+resMobNo;
									}
									
									%>
                                      <td height="25"><%=resMobNo%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Phone & Fax</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String resPhoneNo=(String)map.get("Res_Phone_No");
									String resfaxNo=(String)map.get("Res_Fax_No");
									if(resPhoneNo==null){
									resPhoneNo="-";
									}
									if(resfaxNo==null){
									resfaxNo="-";
									}
									%>
                                      <td height="25"><%=resPhoneNo%>,<%=resfaxNo%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                            </table></h4>
                      <!--residen-->
                     <h3>Other Details</h3><h4> <table width="100%"  cellspacing="2" cellpadding="0" align="center" style="border:1px solid #b8cbe1; border-top:0px">
                              <tr>
                                <td width="10%"></td>
                                <td width="40%" align="left" style="padding-left:15px">Father/Husband Name</td>
                                <td width="10%" align="center">:</td>
                                <td width="40%" align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr>
									<%
									String fathername=(String)map.get("Father_Husband_Name");
								    if(fathername==null){
									fathername="-";
									}
									
									%>
                                      <td height="25"><%=fathername%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Mother's Maiden Name</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String motherName=(String)map.get("Mother_Maiden_Name");
								    if(motherName==null){
									motherName="-";
									}
									
									%>
                                      <td height="25"><%=motherName%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Spouse Name</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String spousename=(String)map.get("Spouse_Name");
								    if(spousename==null){
									spousename="-";
									}
									
									%>
                                      <td height="25"><%=spousename%></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Authorized Mobile No.</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String authMobileNo=(String)map.get("Authorized_Mobile_No");
								    if(authMobileNo==null){
									authMobileNo="-";
									}
									
									%>
                                      <td height="25" width="65%">+91 -<%=authMobileNo%><span style="padding-left:14px"></span></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Select Categoty</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String category=(String)map.get("Category_name");
								    if(category==null){
									category="-";
									}
									
									%>
                                      <td height="25" width="65%"><%=category%><span style="padding-left:14px"></span></td>
                                    </tr>
                                  </table></td>
                              </tr>
                            </table> </h4>  </td></tr>
                      <tr>
                        <td  height="60" align="center" valign="middle" width="50%">
                          <a href="viewDistributor.do?param=updateProfile"><input name="" type="button" value="Edit" style="width:100px;" class="txt" />
                          </a></td>
                      </tr>
                    </table></div></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
	<tr><td height="30"></td></tr>

    <tr>
      <td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td>
    </tr>
  </table>
</center>
</body>
</html>
<script language="javascript" type="text/javascript">

function personal(){
document.getElementById("personal").style.display="block";
document.getElementById("plus1").style.display="none";
document.getElementById("minus1").style.display="block";

}

function personal1(){
document.getElementById("personal").style.display="none";
document.getElementById("plus1").style.display="block";
document.getElementById("minus1").style.display="none";

}

function address(){
document.getElementById("address").style.display="block";
document.getElementById("plus2").style.display="none";
document.getElementById("minus2").style.display="block";

}

function address1(){
document.getElementById("address").style.display="none";
document.getElementById("plus2").style.display="block";
document.getElementById("minus2").style.display="none";

}

function cor_address(){
document.getElementById("cor_address").style.display="block";
document.getElementById("plus3").style.display="none";
document.getElementById("minus3").style.display="block";


}

function cor_address1(){

document.getElementById("cor_address").style.display="none";
document.getElementById("plus3").style.display="block";
document.getElementById("minus3").style.display="none";

}






function al_address(){
document.getElementById("al_address").style.display="block";
document.getElementById("plus4").style.display="none";
document.getElementById("minus4").style.display="block";

}

function al_address1(){
document.getElementById("al_address").style.display="none";
document.getElementById("plus4").style.display="block";
document.getElementById("minus4").style.display="none";

}





function ot_detail(){
document.getElementById("ot_detail").style.display="block";
document.getElementById("plus5").style.display="none";
document.getElementById("minus5").style.display="block";

}

function ot_detail1(){

document.getElementById("ot_detail").style.display="none";
document.getElementById("plus5").style.display="block";
document.getElementById("minus5").style.display="none";

}



</script>
