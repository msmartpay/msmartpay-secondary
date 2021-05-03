var xmlHttp;
function showTime()
{
	if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		}
xmlHttp.onreadystatechange = getServerTime;
xmlHttp.open("POST","getIndexPageInfo.do?param=getDate", true);
xmlHttp.send(null);
}

function getServerTime()
{
	if(xmlHttp.readyState == 4)
	{		
		if(xmlHttp.status == 200)
		{				
			var date=xmlHttp.responseText;			
			document.getElementById("serverDate").innerHTML=date;
		}
	}		
}