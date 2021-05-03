<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<% 

String message=(String)request.getAttribute("message");
if(message==null){
message="";
}
%>
<style>

.poptab{ height:150px; width:400px; margin:auto;}
.poptab tr td{ color:#930; font-size:13px; font-family:"Trebuchet MS"; border:0px solid #009;}
.poptab tr td strong{ color:#930;}
input{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select option{color:#930; font-size:13px; font-family:"Trebuchet MS";}
</style>

</head>
<form name="addmainservice" method="post" >
<body>

<p>&nbsp;</p>
<p align="center" style="font-size:16px;color:#FF3300"><%=message%></p>
<p>&nbsp;</p>
<table class="poptab">
<tr>
<td><strong>Main Service</strong></td> <td><input name="mainservice" style="width:180px;" type="text"  /></td>
</tr>
<tr>
<td><strong>Sub Service</strong></td> <td><input name="subservice" style="width:180px;" type="text"  /></td>
</tr>


<tr>
<td></td><td><input type="button" value="Submit"  onclick="submitForm();" style="width:100px;" /></td>
</tr>
</table>

</body>
</form>
</html>
<script type="text/javascript">
function submitForm(){
var mainservice=document.addmainservice.mainservice.value;
if(mainservice=="")
{
	alert("Enter the Main Service Name.");
	return false;

}
var subservice=document.addmainservice.subservice.value;
alert(subservice);

if(subservice=="")
{
	alert("Enter the Sub Service Name.");
	return false;

}

//document.addcategory.submit();*/

document.addmainservice.action="SKUaction.action?param=AddCategory";
document.addmainservice.submit();
}

</script>