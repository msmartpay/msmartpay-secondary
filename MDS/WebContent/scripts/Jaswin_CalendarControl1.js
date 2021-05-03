var sysdate = "N";

function positionInfo(object) {

  var p_elm = object;

  this.getElementLeft = getElementLeft;
  function getElementLeft() {
    var x = 0;
    var elm;
    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    while (elm != null) {
      x+= elm.offsetLeft;
      elm = elm.offsetParent;
    }
    return parseInt(x);
  }

  this.getElementWidth = getElementWidth;
  function getElementWidth(){
    var elm;
    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    return parseInt(elm.offsetWidth);
  }

  this.getElementRight = getElementRight;
  function getElementRight(){
    return getElementLeft(p_elm) + getElementWidth(p_elm);
  }

  this.getElementTop = getElementTop;
  function getElementTop() {
    var y = 0;
    var elm;
    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    while (elm != null) {
      y+= elm.offsetTop;
      elm = elm.offsetParent;
    }
    return parseInt(y);
  }

  this.getElementHeight = getElementHeight;
  function getElementHeight(){
    var elm;
    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    return parseInt(elm.offsetHeight);
  }

  this.getElementBottom = getElementBottom;
  function getElementBottom(){
    return getElementTop(p_elm) + getElementHeight(p_elm);
  }
}

function CalendarControl() {

  var calendarId = 'CalendarControl';
  var currentYear = 0;
  var currentMonth = 0;
  var currentDay = 0;

  var selectedYear = 0;
  var selectedMonth = 0;
  var selectedDay = 0;


  var selectedYear1 = 0;
  var selectedMonth1 = 0;
  var selectedDay1 = 0;

  var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'];
  var dateField = null;
  var dateField1 = null;

  function getProperty(p_property){
    var p_elm = calendarId;
    var elm = null;

    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    if (elm != null){
      if(elm.style){
        elm = elm.style;
        if(elm[p_property]){
          return elm[p_property];
        } else {
          return null;
        }
      } else {
        return null;
      }
    }
  }

  function setElementProperty(p_property, p_value, p_elmId){
    var p_elm = p_elmId;
    var elm = null;

    if(typeof(p_elm) == "object"){
      elm = p_elm;
    } else {
      elm = document.getElementById(p_elm);
    }
    if((elm != null) && (elm.style != null)){
      elm = elm.style;
      elm[ p_property ] = p_value;
    }
  }

  function setProperty(p_property, p_value) {
    setElementProperty(p_property, p_value, calendarId);
  }

  function getDaysInMonth(year, month) {
    return [31,((!(year % 4 ) && ( (year % 100 ) || !( year % 400 ) ))?29:28),31,30,31,30,31,31,30,31,30,31][month-1];
  }

  function getDayOfWeek(year, month, day) {
    var date = new Date(year,month-1,day)
    return date.getDay();
  }

  this.clearDate = clearDate;
  function clearDate() {
    dateField.value = '';
    hide();
  }

  this.setDate = setDate;
  function setDate(year, month, day) {
    if (dateField) {
		var nextdate = new Date();
	  nextdate.setFullYear(year,(month-1),day);
	  nextdate.setDate(nextdate.getDate()+1);
      if (month < 10) {month = "0" + month;}
      if (day < 10) {day = "0" + day;}

      var dateString = day+"-"+month+"-"+year;
      dateField.value = dateString;
	  if(sysdate=="Y")
		{
		  if(dateField1)
		  {		  
			  if(nextdate.getMonth()<9 && nextdate.getDate()<10)
			  {
				dateField1.value = "0" +  nextdate.getDate() + "-0" + (nextdate.getMonth()+1) + "-" + nextdate.getFullYear();
			  }
			  else if(nextdate.getDate()<10)
			  {
				dateField1.value = "0" + nextdate.getDate() + "-" + (nextdate.getMonth()+1) + "-" + nextdate.getFullYear();
			  }
			  else if(nextdate.getMonth()<9)
			  {
				dateField1.value = nextdate.getDate() + "-0" + (nextdate.getMonth()+1) + "-" + nextdate.getFullYear();
			  }
			  else
			  {
				dateField1.value = nextdate.getDate() + "-" + (nextdate.getMonth()+1) + "-" + nextdate.getFullYear();
			  }
		  }
		}
      hide();
    }
    return;
  }

  this.changeMonth = changeMonth;
  function changeMonth(change) {
    currentMonth += change;
    currentDay = 0;
    if(currentMonth > 12) {
      currentMonth = 1;
      currentYear++;
    } else if(currentMonth < 1) {
      currentMonth = 12;
      currentYear--;
    }

    calendar = document.getElementById(calendarId);
    calendar.innerHTML = calendarDrawTable();
  }

  this.changeYear = changeYear;
  function changeYear(change) {
    currentYear += change;
    currentDay = 0;
    calendar = document.getElementById(calendarId);
    calendar.innerHTML = calendarDrawTable();
  }

  function getCurrentYear() {
    var year = new Date().getYear();
    if(year < 1900) year += 1900;
    return year;
  }

  function getCurrentMonth() {
    return new Date().getMonth() + 1;
  } 

  function getCurrentDay() {
    return new Date().getDate();
  }

  function calendarDrawTable() {

    var dayOfMonth = 1;
    var validDay = 0;
    var startDayOfWeek = getDayOfWeek(currentYear, currentMonth, dayOfMonth);
    var daysInMonth = getDaysInMonth(currentYear, currentMonth);
    var css_class = null; //CSS class for each day
//	alert(sysdate);
	var tempselecteddate = selectedDay1;
	if(sysdate=="Y")
	{
		tempselecteddate = selectedDay1 + 0;
	}
	else
	{
		tempselecteddate = selectedDay1;
	}
		var table = "<table cellspacing='0' cellpadding='0' border='0'>";
		table = table + "<tr class='header'>";
		table = table + "  <td colspan='2' class='previous'>";
		if(currentYear > selectedYear1)
		{
			table = table + " <a href='javascript:changeCalendarControlMonth(-1);'>&lt;Prev</a> ";
		}
		else if(currentYear == selectedYear1 && currentMonth > selectedMonth1)
		{
			table = table + " <a href='javascript:changeCalendarControlMonth(-1);'>&lt;Prev</a>";
		}
		table = table + "</td>"
		
		table = table + "  <td colspan='3' class='title' style='padding: 3px;'>" + months[currentMonth-1] + "-" + currentYear + "</td>";
		table = table + "  <td colspan='2' class='next'><a href='javascript:changeCalendarControlMonth(1);'>Next&gt;</a></td>";
		table = table + "</tr>";
		table = table + "<tr><th>S</th><th>M</th><th>T</th><th>W</th><th>T</th><th>F</th><th>S</th></tr>";
		var curDate = 1;
		for(var week=0; week < 6; week++) {
		  table = table + "<tr>";
		  for(var dayOfWeek=0; dayOfWeek < 7; dayOfWeek++) {
			if(week == 0 && startDayOfWeek == dayOfWeek) {
			  validDay = 1;
			} else if (validDay == 1 && dayOfMonth > daysInMonth) {
			  validDay = 0;
			}

			if(validDay) 
			{
				if(currentYear > selectedYear1)
				{
					curDate = 0;
				}
				else if(currentYear == selectedYear1 && currentMonth > selectedMonth1)
				{
					curDate = 0;
				}
				else if(currentYear == selectedYear1 && currentMonth == selectedMonth1 && dayOfMonth == tempselecteddate)
				{
					curDate = 0;
				}
			
			  if (dayOfMonth == selectedDay1 && currentYear == selectedYear1 && currentMonth == selectedMonth1) {
				css_class = 'current';
			  } else if (dayOfWeek == 0 || dayOfWeek == 6) {
				css_class = 'weekend';
			  } else {
				css_class = 'weekday';
			  }
				if(!curDate)
				{
					table = table + "<td><a class='"+css_class+"' href=\"javascript:setCalendarControlDate("+currentYear+","+currentMonth+","+dayOfMonth+")\">"+dayOfMonth+"</a></td>";
					
				}
				else if(curDate)
				{
					if(dayOfMonth == selectedDay1)
					{
						table = table + "<td class='"+css_class+"'>"+dayOfMonth+"</td>";	
					}
					else
					{
						table = table + "<td class='empty'>"+dayOfMonth+"</td>";	
					}
				}
	//          table = table + "<td><a class='"+css_class+"' href=\"javascript:setCalendarControlDate("+currentYear+","+currentMonth+","+dayOfMonth+")\">"+dayOfMonth+"</a></td>";
			  dayOfMonth++;
			} 
			else 
			{
			  table = table + "<td class='empty'>&nbsp;</td>";
			}
		  }
		  table = table + "</tr>";
		}

		table = table + "<tr class='header'><th colspan='7' style='padding: 3px;'><a href='javascript:clearCalendarControl();'>Clear</a> | <a href='javascript:hideCalendarControl();'>Close</a></td></tr>";
		table = table + "</table>";

		return table;
/*	}
	else
	{
		var table = "<table cellspacing='0' cellpadding='0' border='0'>";
		table = table + "<tr class='header'>";
		table = table + "  <td colspan='2' class='previous'>";
		if(currentYear > selectedYear)
		{
			table = table + " <a href='javascript:changeCalendarControlMonth(-1);'>&lt;Prev</a> ";
		}
		else if(currentYear == selectedYear && currentMonth > selectedMonth)
		{
			table = table + " <a href='javascript:changeCalendarControlMonth(-1);'>&lt;Prev</a>";
		}
		table = table + "</td>"
		
		table = table + "  <td colspan='3' class='title' style='padding: 3px;'>" + months[currentMonth-1] + "-" + currentYear + "</td>";
		table = table + "  <td colspan='2' class='next'><a href='javascript:changeCalendarControlMonth(1);'>Next&gt;</a></td>";
		table = table + "</tr>";
		table = table + "<tr><th>S</th><th>M</th><th>T</th><th>W</th><th>T</th><th>F</th><th>S</th></tr>";
		var curDate = 1;
		for(var week=0; week < 6; week++) {
		  table = table + "<tr>";
		  for(var dayOfWeek=0; dayOfWeek < 7; dayOfWeek++) {
			if(week == 0 && startDayOfWeek == dayOfWeek) {
			  validDay = 1;
			} else if (validDay == 1 && dayOfMonth > daysInMonth) {
			  validDay = 0;
			}

			if(validDay) 
			{
				if(currentYear > selectedYear)
				{
					curDate = 0;
				}
				else if(currentYear == selectedYear && currentMonth > selectedMonth)
				{
					curDate = 0;
				}
				else if(currentYear == selectedYear && currentMonth == selectedMonth && dayOfMonth == selectedDay  )
				{
					curDate = 0;
				}
			
			  if (dayOfMonth == selectedDay && currentYear == selectedYear && currentMonth == selectedMonth) {
				css_class = 'current';
			  } else if (dayOfWeek == 0 || dayOfWeek == 6) {
				css_class = 'weekend';
			  } else {
				css_class = 'weekday';
			  }
				if(!curDate)
				{
					table = table + "<td><a class='"+css_class+"' href=\"javascript:setCalendarControlDate("+currentYear+","+currentMonth+","+dayOfMonth+")\">"+dayOfMonth+"</a></td>";
					
				}
				else if(curDate)
				{
					if(dayOfMonth == selectedDay)
					{
						table = table + "<td class='"+css_class+"'>"+dayOfMonth+"</td>";	
					}
					else
					{
						table = table + "<td class='empty'>"+dayOfMonth+"</td>";	
					}
				}
	//          table = table + "<td><a class='"+css_class+"' href=\"javascript:setCalendarControlDate("+currentYear+","+currentMonth+","+dayOfMonth+")\">"+dayOfMonth+"</a></td>";
			  dayOfMonth++;
			} 
			else 
			{
			  table = table + "<td class='empty'>&nbsp;</td>";
			}
		  }
		  table = table + "</tr>";
		}

		table = table + "<tr class='header'><th colspan='7' style='padding: 3px;'><a href='javascript:clearCalendarControl();'>Clear</a> | <a href='javascript:hideCalendarControl();'>Close</a></td></tr>";
		table = table + "</table>";

		return table;
	
	}*/
  }

  this.show = show;
  function show(field, field1,systemdate) {
    can_hide = 0;
  
    // If the calendar is visible and associated with
    // this field do not do anything.
    if (dateField == field) {
      return;
    } else {
      dateField = field;
	  dateField1= field1;
    }

    if(dateField) {
      try {
        var dateString = new String(dateField.value);
        var dateParts = dateString.split("-");
        
        selectedMonth = parseInt(dateParts[1],10);
        selectedDay = parseInt(dateParts[0],10);
        selectedYear = parseInt(dateParts[2],10);
      } catch(e) {}
    }

	if(systemdate=="Y")
	{
	  selectedMonth1 = getCurrentMonth();
      selectedDay1 = getCurrentDay();
      selectedYear1 = getCurrentYear();	
	}
	else
	{
		 if(dateField1) 
		 {
			try 
			{
		        var dateString = new String(dateField1.value);
//				alert(dateString);
		        var dateParts = dateString.split("-");
                selectedMonth1 = parseInt(dateParts[1],10);
				selectedDay1 = parseInt(dateParts[0],10);
		        selectedYear1 = parseInt(dateParts[2],10);
		     } 
			 catch(e) 
			{}
		}		
	}

	if (!(selectedYear && selectedMonth && selectedDay)) {
      selectedMonth = getCurrentMonth();
      selectedDay = getCurrentDay();
      selectedYear = getCurrentYear();
    }

    currentMonth = selectedMonth;
    currentDay = selectedDay;
    currentYear = selectedYear;

    if(document.getElementById){

      calendar = document.getElementById(calendarId);
      calendar.innerHTML = calendarDrawTable(currentYear, currentMonth);

      setProperty('display', 'block');

      var fieldPos = new positionInfo(dateField);
      var calendarPos = new positionInfo(calendarId);

      var x = fieldPos.getElementLeft();
      var y = fieldPos.getElementBottom();

      setProperty('left', x + "px");
      setProperty('top', y + "px");
 
      if (document.all) {
        setElementProperty('display', 'block', 'CalendarControlIFrame');
        setElementProperty('left', x + "px", 'CalendarControlIFrame');
        setElementProperty('top', y + "px", 'CalendarControlIFrame');
        setElementProperty('width', calendarPos.getElementWidth() + "px", 'CalendarControlIFrame');
        setElementProperty('height', calendarPos.getElementHeight() + "px", 'CalendarControlIFrame');
      }
    }
  }

  this.hide = hide;
  function hide() {
    if(dateField) {
      setProperty('display', 'none');
      setElementProperty('display', 'none', 'CalendarControlIFrame');
      dateField = null;

    }
  }

  this.visible = visible;
  function visible() {
    return dateField
  }

  this.can_hide = can_hide;
  var can_hide = 0;
}

var calendarControl = new CalendarControl();

function showCalendarControl(textField, textField1, systemdate) {
  // textField.onblur = hideCalendarControl;

  sysdate = systemdate;
  calendarControl.show(textField, textField1,systemdate);
}

function clearCalendarControl() {
  calendarControl.clearDate();
}

function hideCalendarControl() {
  if (calendarControl.visible()) {
    calendarControl.hide();
  }
}

function setCalendarControlDate(year, month, day) {
  calendarControl.setDate(year, month, day);
}

function changeCalendarControlYear(change) {
  calendarControl.changeYear(change);
}

function changeCalendarControlMonth(change) {
  calendarControl.changeMonth(change);
}

document.write("<iframe id='CalendarControlIFrame' height='250' width='250' src='javascript:false;' frameBorder='0' scrolling='no'></iframe>");
document.write("<div id='CalendarControl' height='250' width='250'></div>");
