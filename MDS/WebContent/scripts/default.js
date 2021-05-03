		function MM_swapImgRestore() 
		{ //v3.0
			var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
		}

		function MM_preloadImages() 
		{ //v3.0
			var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
		    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
			if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
			// selectRoomAllocation();
		}

		function MM_findObj(n, d) 
		{ //v4.01
			var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
			if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
			for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
			if(!x && d.getElementById) x=d.getElementById(n); return x;
		}

		function MM_swapImage() 
		{ //v3.0
			var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
			if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
		}

		function  popUp()
		{
			var win = window.open("OurCities.aspx", "FindCities", "left=350, top=250, width=450, height=300, scrollbars=1, resizable=1");
		}
		
		function selectRoomAllocation()
		{
				//var roomControl = document.mainForm.ddlNoOfRooms;// getElementById("ddlNoOfRooms");
				var roomControl =document.getElementById('searchHotels_ddlHotelRooms');
				var rooms;
				rooms = 1;
				if(roomControl!=null)
				{
					rooms = roomControl.value;
				}
				
				displaySeasons(rooms);

				//var room1Control = document.mainForm.searchHotels_lblRoom1;//getElementById("searchHotels_lblRoom1");
				var room1Control = document.getElementById('searchHotels_lblRoom1');
				if(room1Control!=null)
				{
					if(rooms>1)
						searchHotels_lblRoom1.style.visibility="visible";
					else
						searchHotels_lblRoom1.style.visibility="hidden";
				}
				return;
			}

function validateddA2(){

var dd2 = document.getElementById('searchHotels_ddlAdult2').value;
var dd3 = document.getElementById('searchHotels_ddlAdult3').value;
if(dd2==0 && dd3!=0 ){

alert("your Room2 adults number is 0");
document.getElementById('searchHotels_ddlAdult3').value = 0;
}
}

function MyDate(){


if(document.getElementById('searchHotels_ddCountries').value=="0")
{
alert('Select the Country');
return false;

}

if(document.getElementById('searchHotels_ddCity').value=="0")
{
alert('Select the City');
return false;

}
 if((document.getElementById('searchHotels_txtArrivalDate').value == "")||(document.getElementById('searchHotels_txtArrivalDate').value == "dd-mm-yyyy")){

alert('Fill the CheckIn date');
return false;

}

}



function validateddlRooms(){

var ddlRoomNo = document.getElementById('searchHotels_ddlHotelRooms').value;
if(ddlRoomNo == 1){

 document.getElementById('searchHotels_ddlAdult2').value = 0;
 document.getElementById('searchHotels_ddlAdult3').value = 0;

}
else if(ddlRoomNo == 2){

document.getElementById('searchHotels_ddlAdult3').value = 0;

}

}

function displaySeasons(value)
{
				//debugger;
				value ++;
				value --;
				 switch(value)
				{
					case 1:	
							if(document.getElementById('searchHotels_tblRoom2')!=null)
							{
								(document.getElementById('searchHotels_tblRoom2')).style.display = 'none';
							}
							if(document.getElementById('searchHotels_tblRoom3')!=null)
							{
								(document.getElementById('searchHotels_tblRoom3')).style.display = 'none';
							}
							break;
					case 2:	
							if(document.getElementById('searchHotels_tblRoom2')!=null)
							{
								(document.getElementById('searchHotels_tblRoom2')).style.display = 'inline';
							}
							if(document.getElementById('searchHotels_tblRoom3')!=null)
							{
								(document.getElementById('searchHotels_tblRoom3')).style.display = 'none';
							}
							
							break;
					case 3: 
						   	if(document.getElementById('searchHotels_tblRoom2')!=null)
							{
								(document.getElementById('searchHotels_tblRoom2')).style.display = 'inline';
							}
						   	if(document.getElementById('searchHotels_tblRoom3')!=null)
							{
								(document.getElementById('searchHotels_tblRoom3')).style.display = 'inline';
							}
							break;
				}
			}


/*
	function displaySeasons(value)
			{
				
				value ++;
				value --;
				 switch(value)
				{
				
				
				
				
					case 1:	(eval("tdRoom2")).style.display = 'inline';
							(eval("document.all.divRoom2" )).style.visibility = 'hidden';

							(eval("tdRoom3")).style.display = 'inline';
							(eval("document.all.divRoom3" )).style.visibility = 'hidden';
							//searchHotels_lblRoom1.style.visibility="hidden";

							break;
					case 2:	(eval("tdRoom2")).style.display = 'inline';
							(eval("document.all.divRoom2" )).style.visibility = 'visible';

							(eval("tdRoom3")).style.display = 'inline';
							(eval("document.all.divRoom3" )).style.visibility = 'hidden';
							//searchHotels_lblRoom1.style.visibility="visible";

							break;
					case 3: (eval("tdRoom2")).style.display = 'inline';
							(eval("document.all.divRoom2" )).style.visibility = 'visible';

							(eval("tdRoom3")).style.display = 'inline';
						    (eval("document.all.divRoom3" )).style.visibility = 'visible';
						   // searchHotels_lblRoom1.style.visibility="visible";

							break;
							
				}
			}


*/







		
/*function setSignInDate(conf_name,target_field,ref_field,default_date,hoder_id,dx,dy,mode,target_feild)
			{
				
				xcModPath="../JS";
				xcWindowTemplate="../JS/script/xc2_template.html";
				xcDateFormat ='dd/mm/yyyy';
				setRange("conf",daysAfter(14),"");
				
				showCustomCalendar("conf",target_field,ref_field,default_date,hoder_id,dx,dy,mode,target_feild);
			}
			
			function callCalendar(conf_name,target_field,ref_field,default_date,hoder_id,dx,dy,mode)
			{
				setRange("conf",daysAfter(14),"");
				var txtbox = document.getElementById(target_field)
				var strDate = document.getElementById('searchHotels_txtArrivalDate').value;
				if(strDate != "" && target_field == 'searchHotels_txtDepartureDate')
				{
					setRange("conf", dayOffset(strDate, 1), "");
				}
				showCalendar("conf",txtbox,ref_field,default_date,hoder_id,dx,dy,mode);
			}
			*/
			
			function setSignInDate(conf_name,target_field,ref_field,default_date,hoder_id,dx,dy,mode,target_feild)
			{
				//xcModPath="script";
				xcModPath = "../../JS";
				xcWindowTemplate="/script/xc2_template.html";
				xcDateFormat ='dd-mm-yyyy';
				//alert();
				//Jaswin Kumar Prusty choose after change day after
				setRange("conf",daysAfter(0),"");
				//alert();
				//showCustomCalendar("conf",target_field,ref_field,default_date,hoder_id,dx,dy,mode,target_feild);
				showCustomCalendar("conf",target_field,ref_field,default_date,hoder_id,dx,dy,mode,target_feild)
				document.getElementById(target_field.id).className="date";
		
				if(ref_field!=null)
				{
					ref_field.className="date";
				}
			}
			
			function callCalendar(conf_name,target_field,ref_field,default_date,hoder_id,dx,dy,mode)
			{
				setRange("conf",daysAfter(5),"");
				var txtbox = document.getElementById(target_field)
				var strDate = document.getElementById('searchHotels_txtArrivalDate').value;
				if(strDate != "" && target_field == 'searchHotels_txtDepartureDate')
				{
					setRange("conf", dayOffset(strDate, 1), "");
				}
				showCalendar("conf",txtbox,ref_field,default_date,hoder_id,dx,dy,mode);
				document.getElementById(target_field).className="date";
			}
			function textChange(object)
			{


				document.getElementById(object).className="date";
			}
			
			function focusout(object)
			{


				if(document.getElementById(object).value=="")
				{
					document.getElementById(object).value="dd-mm-yyyy"
					document.getElementById(object).className="datetext";
				}
			}

function ClearRdb(){

var txtrdbAmsterdam=document.getElementById('searchHotels_rdbAmsterdam');

var txtrdbBarcelona=document.getElementById('searchHotels_rdbBarcelona');

var txtrdbDublin=document.getElementById('searchHotels_rdbDublin');
var txtrdbEdinburgh=document.getElementById('searchHotels_rdbEdinburgh');
var txtrdbLasVegas=document.getElementById('searchHotels_rdbLasVegas');
var txtrdbLondon=document.getElementById('searchHotels_rdbLondon');
var txtrdbMadrid=document.getElementById('searchHotels_rdbMadrid');
var txtrdbNewyork=document.getElementById('searchHotels_rdbNewyork');
var txtrdbParis=document.getElementById('searchHotels_rdbParis');
var txtrdbPrague=document.getElementById('searchHotels_rdbPrague');
var txtrdbRome=document.getElementById('searchHotels_rdbRome');
var txtrdbVenice=document.getElementById('searchHotels_rdbVenice');
txtrdbAmsterdam.checked=false;
txtrdbBarcelona.checked=false;
txtrdbDublin.checked=false;
txtrdbEdinburgh.checked=false;
txtrdbLasVegas.checked=false;
txtrdbLondon.checked=false;
txtrdbMadrid.checked=false;
txtrdbNewyork.checked=false;
txtrdbParis.checked=false;
txtrdbPrague.checked=false;
txtrdbRome.checked=false;
txtrdbVenice.checked=false;

popUp();
            
			}
	function ClearRdb1(){

var txtrdbAmsterdam=document.getElementById('searchHotels_rdbAmsterdam');

var txtrdbBarcelona=document.getElementById('searchHotels_rdbBarcelona');

var txtrdbDublin=document.getElementById('searchHotels_rdbDublin');
var txtrdbEdinburgh=document.getElementById('searchHotels_rdbEdinburgh');
var txtrdbLasVegas=document.getElementById('searchHotels_rdbLasVegas');
var txtrdbLondon=document.getElementById('searchHotels_rdbLondon');
var txtrdbMadrid=document.getElementById('searchHotels_rdbMadrid');
var txtrdbNewyork=document.getElementById('searchHotels_rdbNewyork');
var txtrdbParis=document.getElementById('searchHotels_rdbParis');
var txtrdbPrague=document.getElementById('searchHotels_rdbPrague');
var txtrdbRome=document.getElementById('searchHotels_rdbRome');
var txtrdbVenice=document.getElementById('searchHotels_rdbVenice');
txtrdbAmsterdam.checked=false;
txtrdbBarcelona.checked=false;
txtrdbDublin.checked=false;
txtrdbEdinburgh.checked=false;
txtrdbLasVegas.checked=false;
txtrdbLondon.checked=false;
txtrdbMadrid.checked=false;
txtrdbNewyork.checked=false;
txtrdbParis.checked=false;
txtrdbPrague.checked=false;
txtrdbRome.checked=false;
txtrdbVenice.checked=false;		
	}	
	
	
		function Today()
		{
			var dd = new Date();
			return( dd.getDate() + "-" + (dd.getMonth()+1) + "-" + dd.getFullYear());
		}
		
		function CheckCheckInDate()
		{
			if(document.getElementById('searchHotels_txtArrivalDate')!= null)
			{
				var CheckInDate =document.getElementById('searchHotels_txtArrivalDate');
			    var today=Today();
			    var sd1 = CheckInDate.value.split("-");
				var sd2 = today.split("/");
				
				var y = new Date(sd1[2] ,sd1[1] -1 ,sd1[0] );
				var x = new Date(sd2[2] ,sd2[1] -1 ,sd2[0] );

				var difference = Date.UTC(y.getYear(),y.getMonth(),y.getDate(),0,0,0) - Date.UTC(x.getYear(),x.getMonth(),x.getDate(),0,0,0);
				var nights = difference/1000/60/60/24;
				if( nights < 3 )
				{
					alert("CheckIn date should be after 3 days from today");
					return false;
				}else
					return true;
			}
			else
			{
				return false;
			}
			
		}
		
		function CheckCheckOutDate()
		{
			if((document.getElementById('searchHotels_txtArrivalDate')!= null)&&(document.getElementById('searchHotels_txtDepartureDate')!= null))
			{
				var sd2 = document.getElementById('searchHotels_txtArrivalDate').value.split("-");
				var sd1 = document.getElementById('searchHotels_txtDepartureDate').value.split("-");
				
				var y = new Date(sd1[2] ,sd1[1] -1 ,sd1[0] );
				var x = new Date(sd2[2] ,sd2[1] -1 ,sd2[0] );

				var difference = Date.UTC(y.getYear(),y.getMonth(),y.getDate(),0,0,0) - Date.UTC(x.getYear(),x.getMonth(),x.getDate(),0,0,0);
				var nights = difference/1000/60/60/24;
				if( nights < 1 )
				{
					alert("CheckIn date can't be less than CheckOut date");
					return false;
				}else
					return true;
				
			}
			else
			{
				return false;
			}
		}
			
		function CheckDate()
		{	
		
			if((document.getElementById('searchHotels_txtArrivalDate') != null)&&(document.getElementById('searchHotels_txtArrivalDate').value == ""))
			{
				alert("Please Insert CheckIn Date");
				return false;
			}
			else
			{
				if(!CheckCheckInDate())
				{
					return false;
				}
			}
			
			if((document.getElementById('searchHotels_txtDepartureDate') != null)&&(document.getElementById('searchHotels_txtDepartureDate').value == ""))
			{
				alert("Please Insert CheckOut Date");
			}
			else
			{
				if(!CheckCheckOutDate())
				{
					return false;
				}
			}
			return true;
		}
			
			