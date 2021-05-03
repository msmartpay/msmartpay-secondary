
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MD panel ::log out</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url();
	background-color: #999999;
}
-->
</style>
<link href="css/travel.css" rel="stylesheet" type="text/css" />
<link href="../../css/travel.css" rel="stylesheet" type="text/css" />
<script src="scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<link href="../../css/prime.css" rel="stylesheet" type="text/css" />
<SCRIPT type="text/javascript">

function backButtonOverride()
{
setTimeout("backButtonOverrideBody()", 1);

document.common.action="secure.jsp";
document.common.submit();

}

function backButtonOverrideBody()
{

try {
history.forward();
}

catch (e) {

}

setTimeout("backButtonOverrideBody()", 500);


}
</SCRIPT> 


<script language="javascript">

function subAction()
{  
	document.common.action="secure.jsp";
	document.common.submit();
}
</script>


</head>

<body onload="backButtonOverride()">

<table width="64%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  <tr>
    <td valign="top">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="2%" height="151" rowspan="2">&nbsp;</td>
    <td width="97%"><table width="556" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td width="554"></td>
      </tr>
      
      <tr>
        <td><form  method="post" name="common">
          <input type="hidden" name="param" value="logout1" />
        </form></td>
      </tr>
      <tr>
        <td><div align="center">
            <div align="center">
              <table width="40%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="center"></td>
                </tr>
              </table>
            </div>
        </div>
          <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="40" class="textnormal"> 
                  <div align="center">
                    <p class="heading"></p>
                  </div></td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
            </table></td>
      </tr>
      
    </table></td>
    <td width="1%" rowspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="1" cellpadding="0">
      <tr>
        <td height="119" align="center" valign="top" bgcolor="#FFFFFF">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="19" valign="bottom">&nbsp;</td>
    <td>&nbsp;</td>
    <td valign="bottom">&nbsp;</td>
  </tr>
</table>
</body>
</html>
