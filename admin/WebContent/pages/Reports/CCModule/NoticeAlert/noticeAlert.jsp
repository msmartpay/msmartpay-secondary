<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link rel="icon" href="images/t.png" />
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %>
<%
 Date today = new Date();
 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
  String curdate = formatter1.format(today);
ArrayList<HashMap<String,String>> clientIDs=(ArrayList<HashMap<String,String>>)session.getAttribute("clientIDs");


int size=0;
if(clientIDs==null)

{
size=-1;
}
else
{

size=clientIDs.size();
}

String tickerMessageNew=(String)request.getAttribute("NewTickerMessage");

int id=(Integer)request.getAttribute("userID");

String userType=(String)request.getAttribute("userType");

String getMessage=(String)request.getAttribute("getMessage");
if(getMessage==null)
{
getMessage="";
}
if(tickerMessageNew==null)
{
tickerMessageNew="None";
}

String message=(String)request.getAttribute("message");

if(message==null)
{
message="";
}
%>



<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/tcal.css" />
<script type="text/javascript" src="scripts/tcal.js"></script>

</head>
<body>
<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <tr>
    <td  align="center" width="100%" valign="top">
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="100%" align="center" border="0" >
              <tr>
                <td valign="top" align="center" class="rounded-corners box_heights" >
                <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
                    <tr>
                      <td  width="100%" valign="middle" height="40" align="left" class="heading_mgs" >Notice-Alert</td>
                    </tr>
                    <tr>
                      <td colspan="4"  class="dyn_mgs"><%=message%></td>
                    </tr>
                    <tr>
                      <td valign="top" align="center"><table cellpadding="0" cellspacing="0" width="86%" align="center" border="0" id="border">
                          <tr>
                            <td height="20"></td>
                          </tr>
						  <%if(size>0) {%>
						  <form name="getMessageForm" method="post" action="getTickerMessage.action">
                          <tr>
                            <td width="100%" valign="top" align="center"><table cellspacing="0" cellpadding="0" width="98%" align="center" id="border" class="mydata_tabl">
                                <tr>
                                  <td width="20%" style="padding-left:100Px"><strong>Select User ID </strong></td>
                                  <td height="30" width="20%"><select name="userID" style="width:60px" class="style2">
                              <option value="select" >Select</option>
							   <option value="0" >All</option>
                                   <% 
                                for (int i=0;i<clientIDs.size();i++) 
                                     {
									  
                                        HashMap hm=(HashMap)clientIDs.get(i);
                                        int clientId=Integer.parseInt((String)hm.get("clientId"));
									   %>
                                      <option value="<%=clientId%>"><%=clientId%> </option> 
									
									<%}%></select></td>
									
                                  <td width="15%" style="padding-left:40Px"><strong>Select User Type</strong></td>
                                  <td width="25%" align="left" colspan="" style="padding-left:20Px"><select  class="style2"  name="userType" style="width:150px;">
                                      <option  value="select" selected="selected">Select font Type</option>
                                      <option value="MD">Master Distributor</option>
                                      <option value="DS">Distributor</option>
                                      <option value="Agent">Agent</option>
                                  
                                   </select></td>
                                  <td width="15%"><input name="" type="submit" value="Go"  class="cls_btn" onclick="getMessage()"/></td>
                                </tr>
                               
                              </table></td>
                          </tr>
						  
						  </form>
						  <%}%>
                          <tr>
                            <td height="10"></td>
                          </tr>
						  <form name="updateMessage" method="post" action="updateMessage.action">
						<%if(!"None".equals(tickerMessageNew)){%>  
						  
						 <tr>
                            <td width="100%" valign="top" align="center"><table cellspacing="0" cellpadding="0" width="98%" align="center" id="border" class="mydata_tabl">
                                <tr>
                                  <td width="20%" style="padding-left:100Px"><strong>Date From  </strong></td>
                                  <td height="30" width="20%"><input  type="text"  name="uploadedFrom" class="tcal" style="width:115px" value="<%=curdate%>"/>
                                    &nbsp;&nbsp;&nbsp;</td>
                                  <td width="15%" style="padding-left:40Px"><strong>Date To </strong></td>
                                  <td width="25%" align="left" colspan="" style="padding-left:20Px"><input  type="text"  name="uploadedTo" class="tcal" style="width:115px" value="<%=curdate%>"/>
                                    &nbsp;&nbsp;&nbsp;</td>
                                  <td width="15%">&nbsp;</td>
                                </tr>
                               
                              </table></td>
                          </tr> 
						  <input  type="hidden" name="userID" value="<%=id%>">
						    <input  type="hidden" name="userType" value="<%=userType%>">
						  
                          <tr>
                            <td valign="top" align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tbody>
                                  <tr>
                                    <td height="35" align="left" valign="middle" style="padding-left:15px;"  class="big1"></td>
                                  </tr>
                                  <tr>
                                    <td valign="top"><table cellspacing="1" cellpadding="1" width="95%" align="center" class="mydata_tabl"  bgcolor="#a74312">
                                        <tbody>
                                          <tr  class="hd tabulardata" align="center">
                                            <td colspan="3" height="25"><strong style="color: #fff;">Default Message</strong></td>
											</tr>
                                          
                                         
										  <tr bgcolor="#fff" align="center" height="25">
                                            <td align="center" ><strong >Message</strong></td>
                                            <td align="center"><strong>:</strong></td>
                                            <td align="left"><textarea style="width:450px; margin-left:20px;border: 1px solid #ccc;" name="DefaultMessage"><%=getMessage%></textarea></td>
                                          </tr>
                                          
                                        </tbody>
                                      </table></td>
                                  </tr>
								  
                                  <tr>
                                    <td height="35" align="left" valign="middle"  class="big1"></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">
                                    <table cellspacing="1" cellpadding="1" width="95%" align="center" class="mydata_tabl"  >
                                        
                                          <tr class="hd tabulardata" align="center">
                                            <td colspan="3" height="25" ><strong style="color: #fff;">Write New Message</strong></td>
											</tr>
                                          
                                          <tr bgcolor="#fff" align="center" height="25">
                                            <td  align="center"><strong>Message</strong></td>
                                            <td align="center"><strong>:</strong></td>
                                            <td  align="left"><textarea rows="2"  name="NewMessage" style="width:450px; margin-left:20px;border: 1px solid #ccc;"> </textarea></td>
                                          </tr>
                                          
                                        
                                      </table></td>
                                  </tr>
                               
                                  <tr>
                                    <td height="55" align="center" valign="middle"><input type="submit" class="cls_btn" value="Submit"  onclick="update()"/></td>
                                  </tr>
								  
                                </tbody>
                              </table></td>
                          </tr>
                          
                          <%}%>
						  </form>
                          <tr>
                            <td colspan="5" height="20"></td>
                          </tr>
                        </table></td>
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
    <td width="100%" valign="top" align="center" height="70"></td>
  </tr>
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
</table>
</body>
</html>
<script language="javascript">

function getMessage()
{

var UserId=document.getMessageForm.userID.value;
var userType=document.getMessageForm.userType.value;

if(UserId=="select")
{
alert("Please choose any agent id")
document.getMessageForm.userID.focus();
return false;

}

if(userType=="select")
{
alert("Please choose any userType ")
document.getMessageForm.userType.focus();
return false;

}
document.getMessageForm.submit();
}


function update()
{


var newmessage=document.updateMessage.NewMessage.value;


newmessage=newmessage.replace(/^\s+|\s+$/, '');
if(newmessage=="")
{
alert("Please Enter new notice  ")
document.updateMessage.NewMessage.focus();
return false;
}

else
{


document.updateMessage.submit();
}

}

</script>