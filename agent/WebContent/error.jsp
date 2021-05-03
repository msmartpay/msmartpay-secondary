<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Result Page</title>
<link href="css/B2CStyle.css" rel="stylesheet" type="text/css" />




</head>

<body>


<div class="container_div">



<div class="middle_div">



<div class="order_status">
<p>${requestScope.message }</p>
<p>

ERROR:404
</p>
</div>
<form name="submitForm" method="post" action="Getpage.do?param=getPage">
<table border="0" cellpadding="0" cellspacing="0">

  <tr>
    <td align="center" valign="middle">
	  
      <input type="button" onclick="window.close()" style="width:80px; height:28px;" value="Close" />
	  
    </td>
  </tr>
  
</table>
</form>



</div>

</div>




</body>
</html>
