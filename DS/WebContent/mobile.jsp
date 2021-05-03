<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title> New Document </title>
  <meta name="Generator" content="EditPlus">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
 </head>
<script language="Javascript" src="script/rechargeform.js"></script>
 <body>
  <table width="300" align="center" border="0">
   <form name="RechargeForm" method="post"  id="RechargeForm">

										    

                                        <tr valign="top">

                                          <td height="40" align="left" width="120" valign="middle" class="Trebuchet_b">Mobile&nbsp;Operator&nbsp;&nbsp;<font size="1" color="red" onmouseover="alertmessage()">&nbsp;*</font></td><td  width="180" ><select name="Operator1"  class="Trebuchet_normal" style="width:155px;" onChange="populateform()">
                                            <option value="-1">---Select Operator---</option>
                                            
                                            <option value='AIRCEL'>Aircel</option>
                                            <OPTION VALUE='BSNL'>BSNL TOP UP </OPTION>
											<OPTION VALUE='BSNLRECH'>BSNL Validity</OPTION>
                                            <option value='IDEA'>IDEA</option>
                                            <option value='LOOP'>LOOP</option>
                                            <option value='RELIANCEGSM'>Reliance GSM</option>
                                            <option value='RELIANCECDMA'>Reliance CDMA</option>
											<option value='VIDEOR'>VIDEOCON SPECIAL</option>
											<option value='VIDEOT'>VIDEOCON TOP UP</option>
                                            <option value='VIRGINCDMA'>Virgin CDMA</option>
											<option value='VIRGINGSM'>Virgin GSM</option>
                                            <option value='VODAFONE'>Vodafone</option>
                                            <option value='MTS'>MTS</option>
											<option value='STEL'>S TEL</option>
                                            <option value='TATA'>TATA INDICOM </option>
                                            <option value='TATADOCOMO'>TATADOCOMO</option>
											<option value='TATADOCOMOSP'>TATADOCOMO SPECIAL</option>
											<option value='UNINOR'>UNINOR TOP UP</option>
											<option value='UNINORS'>UNINOR SPECIAL</option>
											<option value='MTNLT'>MTNL TOP UP</option>
											<option value='MTNLV'>MTNL Validity</option>
											
											
                                          </select></td>
                                        </tr>
										
					                 <tr><td colspan="2"><div id="amount" style="width:300px">
									       <table border="0" width="100%">
										   <tr align="left">
                                                <td height="40" width="120" align="left" valign="middle" class="Trebuchet_b"> Amount&nbsp;&nbsp;&nbsp;&nbsp;<font size="1" color="red" onmouseover="alertmessage()">&nbsp;&nbsp;*</font></td><td width="180"><input type="text" name="amount" value="" class="Trebuchet_normal" size="10"   onKeyPress="return digitonly(this,event)" onKeyDown="chk_keys();" maxlength="5" style="width:150px;" /></td>
                                        </tr>


                                        <tr>

                                          <td  width="300" height="8" align="left" valign="middle" class="Trebuchet_b">Mobile&nbsp;Number&nbsp;&nbsp;<font size="1" color="red" onmouseover="alertmessage()">&nbsp;*</font></td><td><input type="text" name="mobileNo" id="mobileNo" class="Trebuchet_normal" size="20" onKeyPress="return digitonly(this,event)" onKeyDown="chk_keys();" maxlength="10" style="width:150px;" />
										  </td>
                                        </tr>
										</table></div></td></tr>
                                       

                                          <tr>
										    <td width="300" height="8" align="right" valign="middle" class="Trebuchet_b">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										      <input type="hidden" name="Service" value="mobile"/><input type="button" name="button2" value="submitForm" onclick="submitForm()">
										   </td>
										  </tr>
                                     </form>
  <table>
 </body>
</html>
