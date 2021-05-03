<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<%
String message=(String)request.getAttribute("message");
if(message==null){message="";}
String userType=(String)session.getAttribute("adminUserType");
%>
</head>
<body>
  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top"><table cellpadding="0" cellspacing="0" width="90%" align="center" border="0">
        <tr>
          <td valign="top" align="center">
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs">Cuttoff Action</td>
                    </tr>
                    <tr><td  colspan="10" align="center" class="dyn_mgs"><%=message%></td></tr>
									
                    <tr><form name="checkAction" method="post">
                      <td colspan="4" valign="top"><table width="86%"  cellspacing="0" cellpadding="0" align="center" id="border">
                          <tr>
                            <td align="center" style="padding:20px 0px 20px 0px">
							  <table width="60%"  cellspacing="0" cellpadding="0" align="center" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Select Cutoff Setup for</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><select name="CutOffFor" class="style2" onchange="CutOffForA()">
                                  <option value="select">Select</option>
								  <%if(userType.equalsIgnoreCase("superadmin")||userType.equalsIgnoreCase("activityAdmin") || userType.equalsIgnoreCase("activityUser") || userType.equalsIgnoreCase("PortalAdmin")){%>
								 
                                  <option value="API">API Client</option>
                                  <option value="Portal">Portal Admin</option>
								  <option value="MD">Master Distributor</option>
                                  <option value="DS">Distributor</option>
                                  <option value="AG">Agent</option>
                                  <%}%>
                                </select></td>
                            </tr>
                            <tr>
                      <td colspan="3"><div id="c0" style="display:none"></div></td>
                    </tr>
                            
                            <tr>
                      <td colspan="3"><div id="API" style="display:none;">
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Set Cutoff for API Client</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><select  class="style2"  NAME="IDSELECTION" id="APIBlock" onchange="IDSELECTIONForm()">
                                  
                                  <option value="select">Select</option>
									 <%if(!userId.equalsIgnoreCase("5172"))
								  {%>
                                  <option value="ByAll">API Client All</option>
									<%}%>
                                  <option value="ById">API Client ID</option>
                                </select></td>
                            </tr>
                            
                             <tr>
                      <td colspan="3"><div id="g0" style="display:none;"></div></td>
                    </tr>
                    
                    <tr>
                      <td colspan="3"><div id="g1" style="display:none;"></div></td>
                    </tr>
  
<tr>
                      <td colspan="3"><div id="EnterApiId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Enter API Client ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="ApiuserId"  type="text" class="style2" onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      
                      </div></td>
                    </tr>  
                   
                            
                          </table>
                        </div></td>
                    </tr>
                            
                            <tr>
                      <td colspan="3"><div id="MD" style="display:none;">
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Set Cutoff for MDS</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><select class="style2"  NAME="IDSELECTION" id="MDBlock" onchange="IDSELECTIONForm()">
							  <option value="select">Select</option>  
									 <%if(!userId.equalsIgnoreCase("5172"))
								  {%>
                                  <option value="ByAll">MDS All</option>
								  <%}%>
                                  <option value="ByClientId">Client ID</option>								  
								    <option value="ById">MD ID</option>
								  
                                </select></td>
                            </tr>
                            
                             <tr>
                      <td colspan="3"><div id="d0" style="display:none;"></div></td>
                    </tr>
  
<tr>
                      <td colspan="3"><div id="d1" style="display:none;"></div></td>
                    </tr>  
                    
                    <tr>
                      <td colspan="3"><div id="EnterMdId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%" align="left" height="30"><strong>Enter User ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="MduserId" type="text"  id="MdsID2" class="style2" onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      </div></td>
                    </tr> 
<tr>
                      <td colspan="3"><div id="EnterClientId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%" align="left"  height="30"><strong>Enter User ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="ClientuserId" type="text"  id="MdsID2" class="style2" onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      </div></td>
                    </tr> 
   
                          </table>
                        </div></td>
                    </tr>
                            <tr>
                      <td colspan="3"><div id="DS" style="display:none;">
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%" align="left"  height="30"><strong>Set Cutoff for DS</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td valign="middle" width="53%"><select class="style2"  name="IDSELECTION" id="DSBlock" onchange="IDSELECTIONForm()">
                                  
                                  <option value="select">Select</option>
                                  <option value="ByClientId">Client Id </option>
                                  <option value="ByMdId">MD ID</option>
								   <%if(!userId.equalsIgnoreCase("5172"))
								  {%>
                                  <option value="ByAll">DS All </option>
                                  <%}%>
								  <option value="ById">DS ID</option>
                                </select></td>
                            </tr>
                            
                            
                            
                             <tr>
                      <td colspan="3"><div id="e0" style="display:none;"></div></td>
                    </tr>
                             <tr>
                      <td colspan="4"><div id="e1" style="display:none;"></div></td>
                    </tr>
                             <tr>
                      <td colspan="3"><div id="EnterDsId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Enter User ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="DsUserId" type="text" id="DsID2" class="style2" onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      
                      </div></td>
                    </tr> 

                            
							
                          </table>
                        </div></td>
                    </tr> 
                    
                    
                    
                    <tr>
                      <td colspan="3"><div id="AG" style="display:none;">
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Set Cutoff for AG</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td valign="middle" width="53%"><select  class="style2"  NAME="IDSELECTION" id="AGBlock" onchange="IDSELECTIONForm()">
                                  
                                  <option value="select">Select</option>
                                  <option value="ByClientId">Client ID </option>
                                  <option value="ByMdId">MD ID</option>
                                  <option value="ByDsId">DS ID</option>
								   <%if(!userId.equalsIgnoreCase("5172"))
								  {%>
                                  <option value="ByAll">AG All </option>
								  <%}%>
                                  <option value="ById">AG ID</option>
                                </select></td>
                            </tr>
                            
                            
                            
                             <tr>
                      <td colspan="3"><div id="f0" style="display:none;"></div></td>
                    </tr>
  
<tr>
                      <td colspan="3"><div id="f1" style="display:none;"> </div></td>
                    </tr>  
                    
                    <tr>
                      <td colspan="3"><div id="EnterAgId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Enter User ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="AgUserId" type="text" class="style2" id="AgID2"  onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      
                      </div></td>
                    </tr> 

                            
							
                          </table>
                        </div></td>
                    </tr>
					
					<tr>
                      <td colspan="3"><div id="Portal" style="display:none;">
                          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Set Cutoff for Portal</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td valign="middle" width="53%"><select class="style2"  NAME="IDSELECTION" id="PortalBlock" onchange="IDSELECTIONForm()">
                                  
                                  <option value="select">Select</option>
                                  <option value="ByAll"> All Portal </option>
                                  <option value="ById">Portal ID</option>
                                </select></td>
                            </tr>
                            
                            
                            
                             <tr>
                      <td colspan="3"><div id="e0" style="display:none;"></div></td>
                    </tr>
                             <tr>
                      <td colspan="4"><div id="e1" style="display:none;"></div></td>
                    </tr>
                             <tr>
                      <td colspan="3"><div id="EnterPortalId" style="display:none;">
                      <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" class="mydata_tabl">
                            <tr>
                              <td width="35%"  align="left"  height="30"><strong>Enter Portal ID</strong></td>
                              <td width="12%"  align="center">:</td>
                              <td width="53%" align="left"><input name="PortalUserId" type="text" id="DsID2" class="style2" onfocus="if(this.value =='Enter Id' ) this.value=''" onblur="if(this.value=='') this.value='Enter Id'" value="" /></td>
                            </tr>
                          </table>
                      
                      </div></td>
                    </tr> 

                            
							
                          </table>
                        </div></td>
                    </tr> 
                    <tr>
                    <td></td>
                    <td></td>
                  <td height="50" align="left" valign="middle" style=" text-decoration:none;">
                  <input name="" type="button" value="Search" onclick="openstatement()" class="cls_btn" /></td>
             </tr>   
                          </table>
							  </td>
                          </tr>
                        </table></td></form>
                    </tr>
                    <tr>
                      <td colspan="4" height="30"></td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td width="100%" valign="top" height="162" align="center"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
function CutOffForA()
{

var main=document.checkAction;
if(main.CutOffFor.value=="select")
{
alert("Please select any option")
document.checkAction.CutOffFor.focus();
return false;

}
if(main.CutOffFor.value=="API")
{
document.getElementById("API").style.display="block";
document.getElementById("MD").style.display="none";
document.getElementById("DS").style.display="none";
document.getElementById("AG").style.display="none";
document.getElementById("Portal").style.display="none";

}
if(main.CutOffFor.value=="MD")
{
document.getElementById("API").style.display="none";
document.getElementById("MD").style.display="block";
document.getElementById("DS").style.display="none";
document.getElementById("AG").style.display="none";
document.getElementById("Portal").style.display="none";

}
if(main.CutOffFor.value=="DS")
{
document.getElementById("API").style.display="none";
document.getElementById("MD").style.display="none";
document.getElementById("DS").style.display="block";
document.getElementById("AG").style.display="none";
document.getElementById("Portal").style.display="none";

}
if(main.CutOffFor.value=="AG")
{
document.getElementById("API").style.display="none";
document.getElementById("MD").style.display="none";
document.getElementById("DS").style.display="none";
document.getElementById("AG").style.display="block";
document.getElementById("Portal").style.display="none";

}
if(main.CutOffFor.value=="Portal")
{
document.getElementById("API").style.display="none";
document.getElementById("MD").style.display="none";
document.getElementById("DS").style.display="none";
document.getElementById("AG").style.display="none";
document.getElementById("Portal").style.display="block";

}
}
//-----------------------
 function IDSELECTIONForm()
 
 {
 
 var cutoff=document.checkAction.CutOffFor.value;

 
 if(cutoff=="API")
 {

 if(document.getElementById("APIBlock").value!="ByAll")
 {

 document.getElementById("EnterApiId").style.display="block";
  document.getElementById("EnterMdId").style.display="none";
   document.getElementById("EnterDsId").style.display="none";
    document.getElementById("EnterAgId").style.display="none";
	document.getElementById("EnterClientId").style.display="none";
	}
	else
	{
	 document.getElementById("EnterApiId").style.display="none";
	}
 }
 
 if(cutoff=="Portal")
 {

 if(document.getElementById("PortalBlock").value!="ByAll")
 {

 document.getElementById("EnterPortalId").style.display="block";
 document.getElementById("EnterApiId").style.display="none";
  document.getElementById("EnterMdId").style.display="none";
   document.getElementById("EnterDsId").style.display="none";
    document.getElementById("EnterAgId").style.display="none";
	document.getElementById("EnterClientId").style.display="none";
	}
	else
	{
	 document.getElementById("EnterPortalId").style.display="none";
	}
 }
 
  if(cutoff=="MD")
 {
 if(document.getElementById("MDBlock").value=="ById")
 { 
    document.getElementById("EnterApiId").style.display="none";
	document.getElementById("EnterClientId").style.display="none";
	document.getElementById("EnterMdId").style.display="block";
    document.getElementById("EnterDsId").style.display="none";
    document.getElementById("EnterAgId").style.display="none";
	}
	else
	{
	 document.getElementById("EnterMdId").style.display="none";
	}
	 if(document.getElementById("MDBlock").value=="ByClientId")
 { 
    document.getElementById("EnterApiId").style.display="none";
	document.getElementById("EnterClientId").style.display="block";
	document.getElementById("EnterMdId").style.display="none";
    document.getElementById("EnterDsId").style.display="none";
    document.getElementById("EnterAgId").style.display="none";
	}
	else
	{
	 document.getElementById("EnterClientId").style.display="none";
	}
 }
  if(cutoff=="DS")
 {
 if(document.getElementById("DSBlock").value!="ByAll")
 {
   document.getElementById("EnterApiId").style.display="none";
   document.getElementById("EnterMdId").style.display="none";
   document.getElementById("EnterDsId").style.display="block";
    document.getElementById("EnterAgId").style.display="none";
	document.getElementById("EnterClientId").style.display="none";
	}
	else
	{
	  document.getElementById("EnterDsId").style.display="none";
	}
 }
  if(cutoff=="AG")
 {
 if(document.getElementById("AGBlock").value!="ByAll")
 {
 document.getElementById("EnterApiId").style.display="none";
  document.getElementById("EnterMdId").style.display="none";
   document.getElementById("EnterDsId").style.display="none";
    document.getElementById("EnterAgId").style.display="block";
	document.getElementById("EnterClientId").style.display="none";
	}
	else
	{
	
    document.getElementById("EnterAgId").style.display="none";
	}
 }
 }
 
 
 //---------------------------------function to call for submision-------
function openstatement()
{
 
 var cutoff=document.checkAction.CutOffFor.value;
 var main=document.checkAction;
if(main.CutOffFor.value=="select")
{
alert("Please select any option")
document.checkAction.CutOffFor.focus();
return false;

}
 
if(cutoff=="API")
 {
 var Cut=document.getElementById("APIBlock").value;
 var id=document.checkAction.ApiuserId.value;
if(Cut=="ById")
 {

if(id=="") 
{
alert("Please Enter API userId")
document.checkAction.ApiuserId.focus();
return false;
}
}

document.checkAction.action="cutoffAmount.action?param=cheakcuttoff&cutOffBy="+Cut+"&EnterUserId="+id;

document.checkAction.submit();
 }
 

if(cutoff=="Portal")
 {
 var Cut=document.getElementById("PortalBlock").value;
 var id=document.checkAction.PortalUserId.value;
if(Cut=="ById")
 {

if(id=="") 
{
alert("Please Enter Portal userId")
document.checkAction.PortalUserId.focus();
return false;
}
}

document.checkAction.action="cutoffAmount.action?param=cheakcuttoff&cutOffBy="+Cut+"&EnterUserId="+id;

document.checkAction.submit();
 }

 
 
  if(cutoff=="MD")
 {

var Cut=document.getElementById("MDBlock").value;
var id1=document.checkAction.MduserId.value;
var id2=document.checkAction.ClientuserId.value;

var MDBlock=document.getElementById("MDBlock").value;

if(MDBlock=="select")
{
alert("please select appropriate option")
document.getElementById("MDBlock").focus();
return false;

}
  var id="";
 
if(Cut=="ById")
 {
 

id=id1;
if(id1=="") 
{
alert("Please Enter MD user id userId")
document.checkAction.MduserId.focus();
return false;
}
}
if(Cut=="ByClientId")
 {
 
id=id2;
if(id2=="") 
{
alert("Please Enter MD user idk userId")
document.checkAction.ClientuserId.focus();
return false;
}
}


document.checkAction.action="cutoffAmount.action?param=cheakcuttoff&cutOffBy="+Cut+"&EnterUserId="+id;

document.checkAction.submit();
 }
  
  
  if(cutoff=="DS")
 {
var Cut=document.getElementById("DSBlock").value;
 var id=document.checkAction.DsUserId.value;
if(Cut=="ById")
 {

if(id=="") 
{
alert("Please Enter DS user idk userId")
document.checkAction.DsUserId.focus();
return false;
}
}

document.checkAction.action="cutoffAmount.action?param=cheakcuttoff&cutOffBy="+Cut+"&EnterUserId="+id;

document.checkAction.submit();
 }
  if(cutoff=="AG")
 {
 var Cut=document.getElementById("AGBlock").value;
  var id=document.checkAction.AgUserId.value;
if(Cut!="ByAll")
 {

if(id=="") 
{
alert("Please Enter AG user idk userId")
document.checkAction.AgUserId.focus();
return false;
}
}

document.checkAction.action="cutoffAmount.action?param=cheakcuttoff&cutOffBy="+Cut+"&EnterUserId="+id;

document.checkAction.submit();
 }

}
</script>



