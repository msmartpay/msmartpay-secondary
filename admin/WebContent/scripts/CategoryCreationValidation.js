function callAjax(val)
  
 {
 
var tttt2=val;
var service="";
createXMLHttpRequest();

var catName=document.newCatForm.newCatName.value;

if(catName=="")
{
alert("Enter Category name")
document.newCatForm.newCatName.focus();
return false;
}


xmlHttp.onreadystatechange = printValues2;
xmlHttp.open("POST","checkCategoryAvailability.action?catType=checkAvail&newCatName="+catName, true);
xmlHttp.send(null);
}


function printValues2()
{
if(xmlHttp.readyState == 4)
		{
			if(xmlHttp.status == 200)
			{
				var response2=xmlHttp.responseText;
		      
				if(response2=="Valid")
				{
					
			   document.getElementById("Availability").innerHTML ="Already avialable";
	           newCatForm.newCatName.focus();
		       return false;
				}
				else
				{
				document.getElementById("Availability").innerHTML ="You can use this name";
	            newCatForm.newCatName.focus();
		        return false;
			    
				}
			
			}
		}
}
 
 
 
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

//------------------------------------------------to create Catogry-----------------------------


function createCategory()
{
var catName=document.newCatForm.newCatName.value;

if(catName=="")
{
alert("Enter Category name")
document.newCatForm.newCatName.focus();
return false;
}

document.newCatForm.submit();
}


//---------------------------------------------To get existence catgory name------------------

function existcategory()
{
	var main=document.existCatForm.existCatName.value;
	
	if(main=="select")
	{
		
	alert("please choose any category name")
	document.existCatForm.existCatName.focus();
	return false;
	}
	document.existCatForm.submit();
	
	
}

//---------------------------------------------------------------getttiing form---------------

function  getDetails()
{
	
	var category=document.serarchStatus.categoryName.value;
	var action=document.serarchStatus.updateaction.value;
    var scommission=document.serarchStatus.serviceComm.value;
	var scharges=document.serarchStatus.scharges.value;


	if(category=="select")
		{
			
			alert("Please Select Category for commission update.")
			document.serarchStatus.categoryName.focus();
			return false;
		}

		if(action=="select")
		{
			
			alert("Please Select Action for commission update.")
			document.serarchStatus.categoryName.focus();
			return false;
		}

		if(action=="commission"){
		
		 if(scommission=="select")
			{
				
				alert("Please Select Service for commission update.")
				document.serarchStatus.serviceComm.focus();
				return false;
			}
              document.serarchStatus.service.value=scommission;

		 }

		 if(action=="service charge"){

			 if(scharges=="select")
				{
					
					alert("Please Select Service for service charge update.")
					document.serarchStatus.scharges.focus();
					return false;
				}
               document.serarchStatus.service.value=scharges;
		 }

	
	
	
		document.serarchStatus.action="getServiceCategoryCommDetail.action";
	    document.serarchStatus.submit();

	
}

function  updateMargin()
{

	       var chks = document.getElementsByName('checkpartial');

			var checkCount=0;
			for (var i = 0; i < chks.length; i++)
			{
			if (chks[i].checked)
			{
			checkCount++;
			}
			}
			if (checkCount <1)
			{
			alert("Please select at least one operator update Commission.");
			return false;
			}


               var x=window.confirm("Are you sure you want to continue ?");
				if (x)
				{
					document.updateAgentMargin.action="updateServiceCategoryCommDetail.action";
	                document.updateAgentMargin.submit();
				}
				else
				{
					
					return false;
				}
	  
		
}

function  updateMarginAir()
{

	       var chks = document.getElementsByName('checkpartial');

			var checkCount=0;
			for (var i = 0; i < chks.length; i++)
			{
			if (chks[i].checked)
			{
			checkCount++;
			}
			}
			if (checkCount <1)
			{
			alert("Please select at least one operator update Commission.");
			return false;
			}


               var x=window.confirm("Are you sure you want to continue ?");
				if (x)
				{
					document.updateAgentMargin.action="updateServiceCategoryCommDetail.action";
	                document.updateAgentMargin.submit();
				}
				else
				{
					
					return false;
				}
	  
		
}

//------------------------------------Rail Update---------------------
