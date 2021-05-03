// JavaScript Document

var xmlHttp;
var xmlHttp2;
var xmlHttp3;
var xmlHttp4;
var xmlHttp5;
var xmlHttp6;
var xmlHttp7;
//var xmlHttp8;

function getAllDetails()
{
getTopHotelsDetails();	
getMostBookedFlightsDetails();	
getLeastAgentDetails();
getMostFlightSaleAgentsDetails();
getMostHotelSaleAgentsDetails();
getMostBusSalesAgentsDetails();
}



//////////////////// Fetch Least Agent Details ////////////////////////////////

function getLeastAgentDetails()
{	
if (window.ActiveXObject) 
		{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp = new XMLHttpRequest();
		}
xmlHttp.onreadystatechange = showLeastAgentDetail;
xmlHttp.open("POST","getIndexPageInfo.do?param=leastAgent", true);
xmlHttp.send(null);
}

function showLeastAgentDetail()
{
	if(xmlHttp.readyState == 4)
		{		
			if(xmlHttp.status == 200)
			{				
				var response1=xmlHttp.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');					
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');
						if(k==1)
							td.setAttribute('align','center');
						if(k==2)
							td.setAttribute('align','right');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}
					
					document.getElementById("leastAgent").appendChild(tr);					
				}															

			}
		}
		
}

//////////////////// Fetch Top Booked Hotel's List ////////////////////////////////

function getTopHotelsDetails()
{
	if (window.ActiveXObject) 
		{
		xmlHttp2 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp2 = new XMLHttpRequest();
		}
xmlHttp2.onreadystatechange = showTopHotelsDetail;
xmlHttp2.open("POST","getIndexPageInfo.do?param=topHotels", true);
xmlHttp2.send(null);

}
function showTopHotelsDetail()
{
	if(xmlHttp2.readyState == 4)
		{		
			if(xmlHttp2.status == 200)
			{				
				var response1=xmlHttp2.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');
					//alert(allHotelDetails[i]);
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');
						if(k==1)
							td.setAttribute('align','center');
						if(k==2)
							td.setAttribute('align','right');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}
					
					document.getElementById("topHotels").appendChild(tr);					
				}															

			}
		}		
}

//////////////////// Fetch Most Booked Flights ////////////////////////////////

function getMostBookedFlightsDetails()
{
if (window.ActiveXObject) 
	{
	xmlHttp3 = new ActiveXObject("Microsoft.XMLHTTP");
	}
else if (window.XMLHttpRequest) 
	{
	xmlHttp3 = new XMLHttpRequest();
	}
xmlHttp3.onreadystatechange = showMostBookedFlightsDetail;
xmlHttp3.open("POST","getIndexPageInfo.do?param=MostBookedFlights", true);
xmlHttp3.send(null);
}

function showMostBookedFlightsDetail()
{
	if(xmlHttp3.readyState == 4)
		{		
			if(xmlHttp3.status == 200)
			{				
				var response1=xmlHttp3.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');					
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');						
						if(k==1)
							td.setAttribute('align','center');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}
					
					document.getElementById("MostBookedFlights").appendChild(tr);					
				}															

			}
		}
		
}

//////////////////// Fetch Most Booked Flights ////////////////////////////////
function getExtraDetails()
{
	if (window.ActiveXObject) 
		{
		
		xmlHttp4 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		
		xmlHttp4 = new XMLHttpRequest();
		}

xmlHttp4.onreadystatechange = showExtraDetail;
xmlHttp4.open("POST","getIndexPageInfo.do?param=ExtraDetails", true);
xmlHttp4.send(null);
}

function showExtraDetail()
{
	if(xmlHttp4.readyState == 4)
		{		
			if(xmlHttp4.status == 200)
			{							
				var response = xmlHttp4.responseText;					
				var agentDetails=response.split('_');
				document.getElementById("totalAgents").innerHTML=agentDetails[0];
				document.getElementById("totalsAgents").innerHTML=agentDetails[0];
				document.getElementById("activeAgents").innerHTML=agentDetails[1];;
				document.getElementById("nonActiveAgents").innerHTML=agentDetails[2];;
			}
		}		
}

/////////////////////////////////////////////////////////////////////////////////////////


function getMostFlightSaleAgentsDetails()
{
	if (window.ActiveXObject) 
		{
		xmlHttp5 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp5 = new XMLHttpRequest();
		}

xmlHttp5.onreadystatechange = showMostFlightSaleAgentsDetail;
xmlHttp5.open("POST","getIndexPageInfo.do?param=MostFlightSaleAgents", true);
xmlHttp5.send(null);
}

function showMostFlightSaleAgentsDetail()
{
	if(xmlHttp5.readyState == 4)
		{		
			if(xmlHttp5.status == 200)
			{				
				var response1=xmlHttp5.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');					
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');
						if(k==1)
							td.setAttribute('align','center');
						if(k==2)
							td.setAttribute('align','right');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}					
					document.getElementById("flightSales").appendChild(tr);		
				}															

			}
		}	
}

/////////////////////////////////////////////////////////////////////////////////////////


function getMostHotelSaleAgentsDetails()
{
	if (window.ActiveXObject) 
		{
		xmlHttp6 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp6 = new XMLHttpRequest();
		}

xmlHttp6.onreadystatechange = showMostHotelSaleAgentsDetail;
xmlHttp6.open("POST","getIndexPageInfo.do?param=MostHotelSaleAgents", true);
xmlHttp6.send(null);
}

function showMostHotelSaleAgentsDetail()
{
	if(xmlHttp6.readyState == 4)
		{		
			if(xmlHttp6.status == 200)
			{				
				var response1=xmlHttp6.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');					
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');
						if(k==1)
							td.setAttribute('align','center');
						if(k==2)
							td.setAttribute('align','right');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}			
					
					document.getElementById("HotelSales").appendChild(tr);		
				}				
			}
		}	
}

/////////////////////////////////////////////////////////////////////////////////////////


function getMostBusSalesAgentsDetails()
{
	if (window.ActiveXObject) 
		{
		xmlHttp7 = new ActiveXObject("Microsoft.XMLHTTP");
		}
	else if (window.XMLHttpRequest) 
		{
		xmlHttp7 = new XMLHttpRequest();
		}

xmlHttp7.onreadystatechange = showMostBusSalesAgentsDetail;
xmlHttp7.open("POST","getIndexPageInfo.do?param=MostBusSaleAgents", true);
xmlHttp7.send(null);
}

function showMostBusSalesAgentsDetail()
{
	if(xmlHttp7.readyState == 4)
		{		
			if(xmlHttp7.status == 200)
			{				
				var response1=xmlHttp7.responseText;	
				var allHotelDetails=response1.split('|');
				for(i=0;i<allHotelDetails.length;i++)
				{
					var tr=document.createElement('tr');					
					hotelDetails=allHotelDetails[i].split('_');
					for(k=0;k<hotelDetails.length;k++)
					{
					
						var td=document.createElement('td');
						td.setAttribute('class','text');
						if(k==0)
							td.setAttribute('align','left');
						if(k==1)
							td.setAttribute('align','center');
						if(k==2)
							td.setAttribute('align','right');
							
						var nodeValue=document.createTextNode(hotelDetails[k]);
						td.appendChild(nodeValue);
						tr.appendChild(td);
					}					
					document.getElementById("BusSales").appendChild(tr);		
				}				
			}
		}	
}

///////////////////////////////////////////////////////////////////////////////////////



function getAgencyName()
{
xmlHttp8=GetXmlHttpObject();
if (xmlHttp8==null)
  {
  alert ("Your browser does not support AJAX!");
  return;
  } 
var url="adminReport.do";
xmlHttp8.onreadystatechange=stateChanged;
xmlHttp8.open("GET",url,true);
xmlHttp8.send(null);
} 

function stateChanged() 
{ 
if (xmlHttp8.readyState==4)
{ 
	//alert(xmlHttp8.responseText);
document.getElementById("agencyname").innerHTML=xmlHttp8.responseText;
}
}

function GetXmlHttpObject()
{
var xmlHttp8=null;
try
  {
  // Firefox, Opera 8.0+, Safari
  xmlHttp8=new XMLHttpRequest();
  }
catch (e)
  {
  // Internet Explorer
  try
    {
    xmlHttp8=new ActiveXObject("Msxml2.XMLHTTP");
    }
  catch (e)
    {
    xmlHttp8=new ActiveXObject("Microsoft.XMLHTTP");
    }
  }
return xmlHttp8;
}