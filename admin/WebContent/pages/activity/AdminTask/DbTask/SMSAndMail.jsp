<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import = "java.util.* "%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=session.getAttribute("headerName")%></title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.png" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="//code.jquery.com/jquery-1.9.1.js"></script>
 <script src="//code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<script>

function validateForm(){
	var Activity=document.SMSAndMailForm.Activity.value;
	var message;
	if(Activity=="bulksms"){
		message=document.SMSAndMailForm.bulksmsmessage.value;
	}
	else if(Activity=="bulkmail"){
		message=document.SMSAndMailForm.bulkmailmessage.value;
		var subject=document.SMSAndMailForm.subject.value;
		if(subject==""){
			alert("Please enter subject");
			return false;
		}
	}
	if(message==""){
		alert("Please Enter Message");
		return false;
	}else{
		document.SMSAndMailForm.action="dbTask.action?param=sendSmsAndMail";
		document.SMSAndMailForm.submit();
	}
	
}

</script>

<script type="text/javascript">
$(document).ready(function(){

    $("#Activity").change(function(){
    	var val=$("#Activity").val();
    	if(val=='bulksms'){
    		$("#bulksms").css("display", "");
    		$("#bulkmail").css("display", "none");
    		$("#subject").css("display", "none");
    	}
    	if(val=='bulkmail'){
    		$("#subject").css("display", "");
    		$("#bulksms").css("display", "none");
    		$("#bulkmail").css("display", "");
    	}
        
    });
});
</script>

</head>
<%
String message=(String) request.getAttribute("message");
if(message==null)message="";
%>
<body>

<table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
  <!--header-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/header.jsp" %></td>
  </tr>
  <!--header-->
  <tr>
    <td valign="top" align="center" height="378" >
    <table cellpadding="0" cellspacing="0" width="90%" align="center" border="0" class="newbg">
        <tr>
          <td valign="top" align="center" class="rounded-corners" >
          <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0">
              <tr>
                <td  width="100%" valign="bottom" height="40" align="left" class="heading_mgs" >Bulk SMS and Mail</td>
              </tr>
              
			   <tr><td  colspan="10" align="center"  class="dyn_mgs"><%=message%></td></tr>
				<tr><td  colspan="10" align="center" style="font-size:13px; font-weight:bold;">&nbsp;</td></tr>
              <tr>
                <td valign="top" align="center">
                <form  name="SMSAndMailForm"  method="post">
                    <table cellpadding="0" cellspacing="0" width="86%" align="center" border="0"  id="border">
                      <tr>
                        <td height="20" colspan="4"></td>
                      </tr>
                   
					 <tr></tr>
					 <tr>
					  <td colspan="4">
					  <table cellpadding="0" cellspacing="0" width="100%" align="center" border="0"  class="mydata_tabl">
                        <tr>
                        <td width="30%"  align="right" valign="middle" ><strong>Select Alert Type</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%">
                        <select id="Activity" name="Activity" >
							<option value="bulksms">Bulk SMS</option>
                            <option selected="selected" value="bulkmail">Bulk Mail</option>
                               
                          </select></td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      <tr>
                        <td width="30%"  align="right" valign="middle" ><strong>Select Alert For</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%">
                        <select id="activityFor" name="activityFor" >
							<option selected="selected" value="agent">Agent</option>
                            <option  value="ds">Distributor</option>
                            <option  value="wl">White Label</option>
                               
                          </select></td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      <tr id="subject" >
                        <td width="30%"  align="right" valign="middle" ><strong>Enter Mail Subject</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%">
                        	<input name="subject" type="text" required />
						</td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      <tr id="bulkmail">
                        <td width="30%"  align="right" valign="middle" ><strong>Enter Mail Message</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%">
                        	<textarea name="bulkmailmessage" style="width: 64%;" rows="5" cols="250"></textarea>
						</td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      <tr style="display: none;" id="bulksms">
                        <td width="30%"  align="right" valign="middle" ><strong>Enter SMS Text</strong></td>
                        <td align="center" width="10%">:</td>
                        <td valign="middle" width="36%">
                        	<textarea name="bulksmsmessage" style="width: 64%;" rows="5" cols="250"></textarea>
						</td>
                        <td width="34%" align="left" valign="middle"></td>
                      </tr>
                      </table>
					  
					  </td>
                      </tr>
 
					  
                      <tr>
                        <td width="30%"></td>
                        <td width="10%"></td>
                        <td  height="50"><input name="Submit" type="button" onclick="validateForm()" value="Send" class="cls_btn" /></td>
                        <td></td>
                      </tr>
                      <tr>
                        <td colspan="4" height="20"></td>
                      </tr>
                    </table>
                  </form></td>
              </tr>
              <tr>
                <td colspan="4" height="30"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <!--footer-->
  <tr>
    <td width="100%" valign="top" align="center"><%@ include file="/footer.jsp" %></td>
  </tr>
  <!--footer-->
</table>
</body>
</html>

