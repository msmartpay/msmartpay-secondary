<table width="100%" border="0" cellspacing="0" cellpadding="0">
                                  <tbody>
                                    <tr>
                                      <td height="35" align="left" valign="middle" style="padding-left:0px;" class="big1">View LRT </td>
                                    </tr>
								
									<tr>
									  <td align="center"> 								</td></tr>
								
									<tr>
									  <td align="center"></td></tr>
                                    <tr>
                                      <td valign="top" style="border-left:1px solid #a74312;">
									  
									  <table cellspacing="0" cellpadding="0" width="100%" align="center" class="form11">
                                        <tbody style="background:#a74312;">
                                          <%if(agentSearchList.size()>0){
					
					%>
                                          <tr class="hd" align="center" style="background:#a74312;">
                                            <td width="4%" height="25" class="right_border">SL No.</td>
                                          
                                           
                                            <td width="8%" class="right_border">Agent ID</td>
                                            <td width="18%" align="center" style="padding-left:8px;" class="right_border">Mobile Operator</td>
                                            <td width="15%" align="center" style="padding-left:8px;" class="right_border">Mobile Number</td>
                                            <td width="9%" align="center" style="padding-left:8px;" class="right_border">Txn Amount</td>
											<td width="18%" align="center" style="padding-left:8px;" class="right_border">Date Of Recharge</td>
											 <td width="8%" align="center" style="padding-left:8px;" class="right_border">Reference ID</td>
                                            <td width="10%" align="center" style="padding-left:8px;" class="right_border" >Service On</td>
                                            <td width="7%" align="center" style="padding-left:8px;" class="right_border">Status</td>
                                            <td width="10%" align="center" style="padding-left:8px;" class="right_border">Remark</td>
                                          </tr>
                                          <%
											for(int i=0;i<agentSearchList.size();i++){				  
				                             HashMap agentMap=(HashMap)agentSearchList.get(i);				                      
				                            
				                           %>
                                          <tr bgcolor="#ffffff" align="center" class="value_reg">
                                            <td height="25"><%=i+1%></td>
											
                                            <td><a href="agentProfile.action?param=viewAgentProfile&agentId=<%=agentMap.get("Agent_id")%>" target="_parent"><%=agentMap.get("Agent_id")%></a></td>
                                            <td align="center" style="padding-left:8px;"><%=agentMap.get("mobile_operator")%></td>                                            
											<td align="center" style="padding-left:8px;"><%=agentMap.get("mobile_number")%></td>
                                            <td align="center" style="padding-left:8px;"><%=agentMap.get("amount")%></td>
											<td align="center" style="padding-left:8px;"><%=agentMap.get("date_of_recharge")%></td>
											<td align="center" style="padding-left:8px;"><%=agentMap.get("tran_id")%></td>
											 <td align="center" style="padding-left:8px;"><%=agentMap.get("mdId")%></td>											
                                            <td align="center" style="padding-left:8px;"><%=agentMap.get("service")%></td>
											<td align="center" style="padding-left:8px;"><%=agentMap.get("status")%></td>
                                          </tr>
                                          <%}}%>
                                        </tbody>
                                      </table></td>
                                    </tr>
                                    <tr>
									<td align="center"><table width="100%" border="0" cellspacing="0" cellpadding="0">
									
  <tr>
        <td align="right" height="25" valign="middle" style="padding-right:15px;" class="logintxt">
       
		
		<!--<a href="javascript:deleteAgent();">Flush/Delete/Archive</a>--></td>  </tr>
		
</table></td>
                                    </tr>
									
									
									
									
									<tr>
                                      
                                    </tr>
									
								
                                  </tbody>
                                </table>