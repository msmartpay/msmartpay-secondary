<% String message=(String)request.getAttribute("message");
if(message==null)
{
message="";
}

String user_id=(String)session.getAttribute("user_id");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%=session.getAttribute("md_page_title")%></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;

}
-->
</style>
<link href="../../../css/style_new_pages.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">
<!--
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}
//-->
</script>
<link href="textstyle.css" rel="stylesheet" type="text/css" />
<link href="../css/layout.css" rel="stylesheet" type="text/css" />
</head>

<body>
<center>
<table width="1002" border="0" align="center" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  
  
  
       <tr align="center">
        <td width="1002" align="center" valign="top"><%@ include file="../../header.jsp" %></td>
        </tr>
 
        <tr>
                <td align="center" valign="top">&nbsp;</td>
                </tr> 
				<tr>
                <td align="center" valign="top">&nbsp;</td>
                </tr>
                <tr>
                <td align="center" valign="top"><font color="#FF0000" size="2"><%=message%></font></td>
                </tr>
				 <tr>
                <td align="center" valign="top">&nbsp;</td>
                </tr> 
				<tr>
                <td align="center" valign="top">&nbsp;</td>
                </tr>
           
                        <tr>
                          <td align="center" valign="middle" class="footer_index"><%@ include file="../../footer.jsp" %></td>
                        </tr>
                    </table>
</center>
</body>
</html>
<script language="javascript">

function termuseWindow()
{
 window.open('terms_use.do','popup','scrollbars=1,width=500,height=500,resizable=1');
}
function useSameEmail()
{
	document.getElementById("agencyLogin").value=document.getElementById("email").value
}

function submitForm()
{
	if(mandatoryfill()){
	document.regiestration.submit();
	}
}
</script>

<script language="javascript">

var xmlHttp;

function createXMLHttpRequest()
{
	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		}
}
var tttt="";
function chkEmail(val)
{
tttt=val;
createXMLHttpRequest();
var type=document.regiestration.email.value;
xmlHttp.onreadystatechange = printValues;
xmlHttp.open("POST","agentAction.do?param=checkEmail&email="+type, true);
xmlHttp.send(null);
}


function printValues()
{
if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response1=xmlHttp.responseText;
				if(response1=="Valid")
				{
				
					checkEmail(tttt);	
					useSameEmail();			
				}
				else
				{
				alert("This email ID already exists");
				document.regiestration.email.value="";
				document.regiestration.email.focus();
				}
			}
		}
}

function checkInputBox(vv)
{
	
		if(document.getElementById("ADL"+vv).checked==true )
		{
			document.getElementById("ADT"+vv).disabled=false;
		}
		else{
			document.getElementById("ADT"+vv).disabled=true;
			document.getElementById("ADT"+vv).value="";
			}
	
		
}	

function checkCeoEmail(vale,eve)
{

vale=document.getElementById("ceoEmail");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		} 
}

function checkSalesEmail(vale,eve)//onchange
{

vale=document.getElementById("salesEmail");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		} 


}

function checkstaffEmail1(vale, eve)
{
	vale=document.getElementById("staffEmail1");

	if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		}
}

function checkstaffEmail2(vale,eve)//onchange
{

vale=document.getElementById("staffEmail2");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		} 
}

function checkstaffEmail3(vale,eve)//onchange
{

vale=document.getElementById("staffEmail3");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		} 


}

function checkstaffEmail4(vale,eve)
{

vale=document.getElementById("staffEmail4");

if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(vale.value))
           {
				return true;
		   }
		else
		{
			alert("Invalid E-mail Address! Please re-enter From Address.")
			vale.value="";
			vale.focus();
			vale.select();
			return false;
		} 


}

function openImageWindow(image_no)
{
 
window.open('agentAction.do?param=openImagewindow&from=addAgentImage_Reg&image_no='+image_no,'popup','width=500,height=200,scrollbars=no,resizable=no,toolbar=no,directories=no,location=no,menubar=no,status=no,left=0,top=0');
}


</script>