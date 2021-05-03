var xmlHttp;
function createXMLHttpRequest() {
if (window.ActiveXObject) {
xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
}
else if (window.XMLHttpRequest) {
xmlHttp = new XMLHttpRequest();
}
}
function startRequest() {
createXMLHttpRequest();
xmlHttp.onreadystatechange = handleStateChange;
xmlHttp.open("GET", "xmlFiles/cityName.xml", true);
xmlHttp.send(null);
}
function handleStateChange()
	{
		if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response=xmlHttp.responseXML;
			
				var northNode = response.getElementsByTagName("City");
				for(var i =0; i < northNode.length; i++)
				{
					if(northNode[i].getAttribute("CountryCode")=="IN")
					{

						var optionCity=document.createElement('option') ;
						var codeV=northNode[i].getAttribute("Code");
						optionCity.setAttribute("value",codeV);
						var content=northNode[i].childNodes[0].nodeValue;
						var textNode=document.createTextNode(content);
						optionCity.appendChild(textNode);
						document.getElementById("from").appendChild(optionCity);
						
					}
			}
			for(var i =0; i < northNode.length; i++)
				{
					if(northNode[i].getAttribute("CountryCode")=="IN")
					{

						var optionCity=document.createElement('option') ;
						var codeV=northNode[i].getAttribute("Code");
						optionCity.setAttribute("value",codeV);
						var content=northNode[i].childNodes[0].nodeValue;
						var textNode=document.createTextNode(content);
						optionCity.appendChild(textNode);
						
						document.getElementById("to").appendChild(optionCity);
						
					}
			}
		MM_preloadImages('images/home_h.jpg','images/hotels_h.jpg','images/holiday_h.jpg','images/travel_h.jpg','images/contactus_h.jpg');
		}
	}
}