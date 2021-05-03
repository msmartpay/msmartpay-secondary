// Mod: Date Range for Xin Calendar 2X (In-Page/Popup-Window)
// Copyright 2004  Xin Yang    All Rights Reserved.

function setRange(ca,he,eo){if(xc_cl(he,eo)){xc_dv(ca,"du","gp",[he,eo],0)}};
function setWeekDays(ca,sun,mon,tue,wed,thu,fri,sat){xc_dv(ca,"du","gs",{"hz":"ej","bz":[sun,mon,tue,wed,thu,fri,sat]},1)};
function setDays(ca,sun,wk,sat){setWeekDays(ca,sun,wk,wk,wk,wk,wk,sat)};
function enableRange(ca,he,eo){if(xc_cl(he,eo)){xc_dv(ca,"du","gs",{"hz":"gr","bz":[he,eo]},1)}};
function disableRange(ca,he,eo){if(xc_cl(he,eo)){xc_dv(ca,"du","gs",{"hz":"gq","bz":[he,eo]},1)}};
function enableDates(ca,ea){var ds=xc_fe(ea);for(var i=0;i<ds.length;i++){xc_dv(ca,"du",ds[i],false,0)}};
function disableDates(ca,ea){var ds=xc_fe(ea);for(var i=0;i<ds.length;i++){xc_dv(ca,"du",ds[i],true,0)}};
function daysBefore(n){return xc_bq(getCurrentDate(),-n)};
function daysAfter(n){return xc_bq(getCurrentDate(),n)};
function dayOffset(dl,n){return xc_bq(toCalendarDate(toJSDate(dl||"")),n)};
function getWeekBegin(dl,n){var d=toJSDate(dl||"");d.setTime(d.getTime()+xc_dk(7*(n||0)-d.getDay()+xcWeekStart));return toCalendarDate(d)};
function getWeekEnd(dl,n){var d=toJSDate(dl||"");d.setTime(d.getTime()+xc_dk(7*(n||0)-d.getDay()+6+xcWeekStart));return toCalendarDate(d)};
function getMonthBegin(dl,n){var d=toJSDate(dl||""),hi=new Date(d.getFullYear(),d.getMonth()+(n||0),1);return toCalendarDate(hi)};
function getMonthEnd(dl,n){var d=toJSDate(dl||""),gg=new Date(d.getFullYear(),d.getMonth()+(n||0)+1,1);gg.setTime(gg.getTime()-xc_dk(1));return toCalendarDate(gg)};
function getYearBegin(n){return xc_bz((new Date()).getFullYear()+(n||0),0,1)};
function getYearEnd(n){return xc_bz((new Date()).getFullYear()+(n||0),11,31)};function xc_cl(he,eo){var bo=xc_by();return((he==""||bo.test(he))&&(eo==""||bo.test(eo)))};
function xc_bq(date,n)
{
	var d=toJSDate(date||"");
	d.setTime(d.getTime()+xc_dk(n));
	return toCalendarDate(d)
};

 
function xc_fe(ea){return ea.match(new RegExp(ae(),"g"))};
function xc_dk(n){return 86400000*n};function xc_ax(em){var gp=this.ff("du","gp");if(gp!=null){var ev=new Date(this.il,this.month,1);if(em<=0&&gp[0]!=""){var fs=new Date(this.il,this.month,xc_cr(ev));if(compareDates(toCalendarDate(fs),gp[0])<0){var he=toJSDate(gp[0]);this.il=he.getFullYear();this.month=he.getMonth()}};if(em>=0&&gp[1]!=""){if(compareDates(toCalendarDate(ev),gp[1])>0){var eo=toJSDate(gp[1]);this.il=eo.getFullYear();this.month=eo.getMonth()}}}};
function xc_bg(date){var aa=new Date(this.il,this.month,date),ab=aa.getDay(),bg=toCalendarDate(aa);var gp=this.ff("du","gp");if(gp){if(gp[0]!=""&&compareDates(gp[0],bg)>0||gp[1]!=""&&compareDates(bg,gp[1])>0){return true}};var dv=this.ff("du",bg);if(dv!=null){return dv};var dt=false,gs=this.ff("du","gs");if(gs){for(var i=0;i<gs.length;i++){if(gs[i]["hz"]=="ej"){dt=gs[i]["bz"][ab]==0?true:false}else if(gs[i]["hz"]=="gr"){if(compareDates(gs[i]["bz"][0],bg)<=0&&compareDates(bg,gs[i]["bz"][1])<=0){dt=false}}else if(gs[i]["hz"]=="gq"){if(compareDates(gs[i]["bz"][0],bg)<=0&&compareDates(bg,gs[i]["bz"][1])<=0){dt=true}}}};return dt};

function isWithinRange(startDate,endDate)
{
	var start = toJSDate(startDate||"");
	var end = toJSDate(endDate||"");
	if((end.getTime() - start.getTime()) < 31 * 86400000)
	return true;
	else
	return false;
}
