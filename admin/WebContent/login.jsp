
<%
	String message = (String) request.getAttribute("message");
	if (message == null)
		message = "";
	String ipAdd = request.getRemoteAddr();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Admin Control</title>
<link href="css/mastercss.css" rel="stylesheet" type="text/css" />
<link rel="icon" href="images/t.jpg" />

<script>
	function submitForm() {

		var userType = document.loginForm.userType.value;
		var userName = document.loginForm.userName.value;
		var password = document.loginForm.password.value;

		if (userName == "" || userName == null) {
			alert("Please Enter User name.");
			document.loginForm.userName.focus();
			return false;

		}

		if (password == "" || password == null) {
			alert("Please enter password.");
			document.loginForm.password.focus();
			return false;
		}

		if (userType == "superadmin") {
			document.loginForm.action = "superAdminLogin.action";
			document.loginForm.submit();

		}

		if (userType == "adminUser") {
			document.loginForm.action = "login.action";
			document.loginForm.submit();
		}

	}
</script>

</head>

<body>

	<table cellpadding="0" cellspacing="0" width="100%" align="center"
		border="0" class="header">
		<tr>
			<td align="center" width="100%" valign="top">
				<table cellpadding="0" cellspacing="0" width="100%" align="center"
					border="0">
					<tr>
						<td align="center" width="100%" valign="middle" height="120" style="border-bottom: 5px solid rgba(129, 129, 129, 0.83);"
							class="big">ADMIN CONTROL</td>
					</tr>
					<tr>
						<td height="15"></td>
					</tr>

					<tr>
						<td valign="middle" align="center" height="420">


							<form name="loginForm" method="post">
								<table cellpadding="0" cellspacing="0" width="50%"
									align="center" border="0"
									class="rounded-corners mydata_tabl bg-color">
									<tr>
										<td colspan="10" align="center" valign="middle" height="15"
											class="dyn_mgs" style="color: #F00;"><%=message%></td>
									</tr>
									<tr>
										<td colspan="10" align="center"></td>
									</tr>
									<tr>
										<td valign="top"
											style="font-weight: bold; color: #930; font-size: 15px; font-family: 'Trebuchet MS'; padding-left: 70px;"
											colspan="4" align="left"></td>
									</tr>
									<tr>
										<td height="10" colspan="4"></td>
									</tr>
									<tr>
										<td width="57"></td>
										<td width="146" align="left">User Login Type</td>
										<td width="18"></td>
										<td width="276" align="left" valign="middle" height="35">
											<select name="userType" >
											<option selected="selected"	value="adminUser">Other User</option>
												<option  value="superadmin" >Super Admin</option></select>
										</td>
									</tr>
									<tr>
										<td width="57"></td>
										<td width="146" align="left">User Name</td>
										<td width="18"></td>
										<td width="276" align="left" valign="middle"><input
											name="userName" required type="text" /> <input type="hidden"
											name="IP" value="<%=ipAdd%>" /></td>
									</tr>
									<tr>
										<td><input name="param" type="hidden" value="login" /></td>
									</tr>

									<tr>
										<td height="10" colspan="4"></td>
									</tr>
									<tr>
										<td></td>
										<td align="left">Password</td>
										<td></td>
										<td align="left" valign="middle" height="35"><input
											name="password" required type="password" /></td>
									</tr>
									<tr>

									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td height="20" align="right" valign="bottom" class="logintxt">
											<div align="left">
												<span>Forgot Password?&nbsp;<a class="cls"
													href="forgetpage.action">click here</a> to Reset.
												</span>
											</div>
										</td>
									</tr>
									<tr>
										<td height="50" valign="middle" align="center"
											style="padding-left: 45px" colspan="4">
											<div align="center">
												&nbsp;&nbsp;&nbsp;
												<input name="Submit" type="button" onclick="submitForm();" value="Login" class="cls_btn" />	
											</div></td>
									</tr>
									<tr>
										<td height="20" colspan="4"></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>

				</table>
			</td>
		</tr>


		<tr>
			<td width="100%" valign="top" align="center"><%@ include
					file="footer.jsp"%></td>
		</tr>
	</table>


</body>
</html>


