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

.poptab{ height:90px; width:400px; margin:auto;}
.poptab tr td{ color:#930; font-size:13px; font-family:"Trebuchet MS"; border:0px solid #009;}
.poptab tr td strong{ color:#930;}
input{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select{color:#930; font-size:13px; font-family:"Trebuchet MS";}
select option{color:#930; font-size:13px; font-family:"Trebuchet MS";}
</style>

</head>
<form name="addcategory" method="post" >
<body>

<p>&nbsp;</p>
<p align="center" style="font-size:16px;color:#FF3300"><%=message%></p>
<p>&nbsp;</p>
<table class="poptab">
<tr>
<td><strong>Category Name</strong></td> <td><input name="category" style="width:180px;" type="text" maxlength="20" /></td>
</tr>


<tr>
<td></td><td><input type="button" value="Submit" onclick="submitForm();" style="width:100px;" /></td>
</tr>
</table>

</body>

<form>
</html>
<script type="text/javascript">
function submitForm(){
var Category=document.addcategory.category.value;
alert(Category);

if(Category=="")
{
	alert("Enter the Category Name.");
	return false;

}

//document.addcategory.submit();*/

document.addcategory.action="SKUaction.action?param=AddCategory";
document.addcategory.submit();
}

</script>