<%@ page import = "java.util.HashMap "%> 
<%
HashMap map=(HashMap)session.getAttribute("distributorInfo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%>:: Distributor Profile</title>
<link href="CSS/dis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery.js"></script>


<style type="text/css">
.accordion {
	width: 700px;
}
.accordion h3 {
	border:#b8cbe1 solid 1px; 
	padding: 7px 15px; background:#dbe5f1; text-align:center;
	margin: 0; font-size:13px; font-weight:bold; font-family:"Trebuchet MS"; color:#000000;
	border-bottom: #b8cbe1 1px solid;
	}


</style>




</head>
<script language="Javascript" src="scripts/Localitysimple.js"></script>
<script language="Javascript" src="scripts/Localitysimpletest.js"></script>
<body><center>
 <form name="regiestration"  action="viewDistributor.do">
 <input type="hidden" value="saveProfile"  name="param" />	

<table cellpadding="0" cellspacing="0" border="0" align="center" width="1000">
  <tr><td align="left" height="130" width="1000">
 <%@ include file="../../header.jsp"%></td></tr>

  <tr>
    <td width="100%" align="center" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">

		  
  <tr>
    <td class="big" width="100%" valign="bottom" height="40" align="left" style="padding-left:20px">Distributor Profile - Update  </td>
    </tr>

<tr>
    <td width="100%" valign="top" align="center" style="padding:20px 0px 20px 0px"><table width="96%" border="0" cellspacing="0" cellpadding="0" align="center" class="border">
  <tr>
                  <td width="100%" align="center">	<div class="accordion" style="padding:20px 0px 20px 0px">
<table width="100%"  cellspacing="2" cellpadding="0" align="center"  class="form11">
                 <tr><td>   
                     <h3>Residential  Address Detail</h3><h4> <table width="100%"  cellspacing="2" cellpadding="0" align="center" style="border:1px solid #b8cbe1; border-top:0px; padding:10px 0px 10px 0px">
                              <tr>
                                <td width="10%"></td>
                                <td width="30%" align="left" style="padding-left:15px">Address Line 1</td>
                                <td width="10%" align="center">:</td>
                                <td width="50%"  align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<%
									String resAdd1=(String)map.get("Res_Address1");
								    if(resAdd1==null){
									resAdd1="-";
									}
									
									%>
              <tr>
                <td height="25"><input name="resAddress1" type="text" style="width:310px" class="style3" value="<%=resAdd1%>"  maxlength="60"/></td>
              </tr>
          </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Address Line 2</td>
                                <td  align="center">:</td>
                                <td  align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
								<% String resAdd2=(String)map.get("Res_Address2");
								    if(resAdd2==null){
									resAdd2="-";
									}
									
									%>
              <tr>
                <td height="25"><input name="resAdd2" type="text" style="width:310px" class="style3" value="<%=resAdd2%>"   maxlength="60"/></td>
              </tr>
          </table></td>
                              </tr>
                              <tr>
                                <td></td>
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
                                <td align="left" style="padding-left:15px">Country</font></td>
                                <td align="center">:</td>
                                <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td height="25" ><select name="resCountry" style="width:150px" class="style2">
                    <option>India</option>
                    
                  </select>
				  </td></tr>
          </table></td>
		  
                              </tr>
							  <tr>
                                <td></td>
							
                                <td align="left" style="padding-left:15px">State</font></td>
                                <td align="center">:</td>
                                <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
             <tr>
                <td height="25" ><select name="state" class="cbox" onchange="populatecities()" style="width:185px;">
                                            <!--  <option value="-1">--------- Select State ---------</option>-->
											<option value="<%=map.get("Res_State")%>" selected="selected"><%=map.get("Res_State")%></option>
                                              <option value="Andaman And Nicobar">Andaman and Nicobar Islands</option>
                                              <option value="Andhra Pradesh">Andhra Pradesh </option>
                                              <option value="Arunachal Pradesh">Arunachal Pradesh </option>
                                              <option value="Assam">Assam </option>
                                              <option value="Bihar">Bihar </option>
                                              <option value="Chandigarh">Chandigarh </option>
                                              <option value="Chhattisgarh">Chhattisgarh </option>
                                              
                                             
                                           
                                              <option value="Goa">Goa </option>
                                              <option value="Gujarat">Gujarat </option>
                                              <option value="Haryana">Haryana </option>
                                              <option value="Himachal Pradesh">Himachal Pradesh </option>
                                              <option value="Jammu and Kashmir">Jammu and Kashmir </option>
                                              <option value="Jharkhand">Jharkhand </option>
                                              <option value="Karnataka">Karnataka </option>
                                              <option value="Kerala">Kerala </option>
                                              <option value="Madhya Pradesh">Madhya Pradesh </option>
                                              <option value="Maharashtra">Maharashtra </option>
                                              <option value="Manipur">Manipur </option>
                                              <option value="Meghalaya">Meghalaya </option>
                                              <option value="Mizoram">Mizoram </option>
                                              <option value="NewDelhi">NewDelhi </option>
                                              <option value="Nagaland">Nagaland </option>
                                              <option value="Orissa">Orissa </option>
                                              <option value="Pondicherry">Pondicherry</option>
                                              <option value="Punjab">Punjab </option>
                                              <option value="Rajasthan">Rajasthan </option>
                                              <option value="Sikkim">Sikkim </option>
                                              <option value="Tamil Nadu">Tamil Nadu </option>
                                              <option value="Tripura">Tripura </option>
                                              <option value="Uttaranchal">Uttaranchal </option>
                                              <option value="Uttar Pradesh">Uttar Pradesh </option>
                                              <option value="West Bengal">West Bengal</option>
											  <!-- <option value="Others">Others</option> -->
                                            </select>                      </td>
              </tr>
          </table></td>
		  
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px"> City </td>
                                <td align="center">:</td>
                                <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr><%
									String resCity=(String)map.get("Res_City");
									
									if(resCity==null){
									resCity="-";
									}
									%>
                <td height="25" ><div id="cityId">
										  <select name="city" class="cbox"  onclick="checkStateSelection1()" style="width:185px;">
                                            <option value="-1" selected="selected">------------Select City -----------</option>
											 <option value='<%=map.get("code")%>' selected="selected"><%=map.get("Res_City")%></option>
                                          </select>
										  </div>          </td>
              </tr>
          </table></td>
                              </tr>
							  
							   <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px"> Pincode </td>
                                <td align="center">:</td>
                                <td align="left"><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td height="25" ><input name="resPinCode" type="text" style="width:50px" value="<%=map.get("Res_Pincode")%>" class="style3" maxlength="6" />            </td>
              </tr>
          </table></td>
                              </tr>
							  
							  
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Nearby Land Mark</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String resLandmark=(String)map.get("Res_Landmark");
									
									if(resLandmark==null){
									resLandmark="-";
									}
									%>
                                      <td height="25"><input name="resLandMark" type="text" style="width:313px" class="style3"  value="<%=resLandmark%>" maxlength="60"/></td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td></td>
                                <td align="left" style="padding-left:15px">Mobile</td>
                                <td align="center">:</td>
                                <td><table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                                    <tr><%
									String resMobNo=(String)map.get("Res_Mobile_No");
									String resAlterNateNo=(String)map.get("Res_Alternate_Mobile_No");
									if(resMobNo==null){
									resMobNo="-";
									}
									else{
									resMobNo=resMobNo;
									}
									%>
                                      <td height="25"><input name="code" type="text" style="width:33px" value="+91"  class="style2" /><span style="padding-left:15px"></span>
									  <input name="resMobileNo" type="text" style="width:117px" value="<%=resMobNo%>" class="style3"  maxlength="10"/><span style="padding-left:15px"></span>
									  <input name="resAlternateNo" type="text" style="width:127px" value="<%=resAlterNateNo%>" class="style3"  maxlength="10"/>  </td>
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
                                      <td height="25"><input name="resSTDCode" type="text" style="width:48px" value="<%=map.get("stdcode")%>" class="style3"  maxlength="4"/><span style="padding-left:15px"></span>                                      <input name="resPhone" type="text" style="width:110px" value="<%=map.get("phoneNo")%>" class="style3" maxlength="8"/>
									  <span style="padding-left:15px"></span>
									  <input name="resFax" type="text" style="width:120px" value="<%=map.get("faxNo")%>" class="style3" maxlength="8" />   </td>
                                    </tr>
                                  </table></td>
                              </tr>
                            </table></h4></td></tr>
                      <tr>
                        <td  height="60" align="center" valign="middle" width="50%"><input name="" type="submit" value="Update" style="width:100px;" class="txt" /></td>
                      </tr>
                    </table></div></td>
                </tr>
  
  
  
</table>

</td>
  </tr>
		  

  
    
</table>
</td></tr>
<tr><td height="30"></td></tr>

  <tr><td height="35" width="1000" align="left"><%@ include file="../../footer.jsp"%></td></tr>
</table>
</form>
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
