
function changeFlag(i)
{
  var i;
  
  if (i == "from")
  {
    var b = 0;

    for (b = 0; b < 6 ; b++)
    {
      if (fromFlag[b] == document.calcForm.from.selectedIndex)
      {
        return;
      }
    }
    fromFlagNew = new Array(document.calcForm.from.selectedIndex, fromFlag[0], fromFlag[1], fromFlag[2], fromFlag[3], fromFlag[4] );
    fromFlag = fromFlagNew;
    drawFlags();
  }

  if (i == "to")
  {
    var b = 0;
    for (b = 0; b < 6 ; b++)
    {
      if (toFlag[b] == document.calcForm.to.selectedIndex)
      {
        return;
      }
    }
    toFlagNew = new Array(document.calcForm.to.selectedIndex, toFlag[0], toFlag[1], toFlag[2], toFlag[3], toFlag[4] );
	toFlag = toFlagNew;
  }
  
 }

function drawFlags()
{
    document.fromFlag0.src = "/img/flag/i/" + country[fromFlag[0]] + ".png";
    document.fromFlag1.src = "/img/flag/i/" + country[fromFlag[1]] + ".png";
    document.fromFlag2.src = "/img/flag/i/" + country[fromFlag[2]] + ".png";
    document.fromFlag3.src = "/img/flag/i/" + country[fromFlag[3]] + ".png";
    document.fromFlag4.src = "/img/flag/i/" + country[fromFlag[4]] + ".png";
    document.fromFlag5.src = "/img/flag/i/" + country[fromFlag[5]] + ".png";
    document.toFlag0.src = "/img/flag/i/" + country[toFlag[0]] + ".png";
    document.toFlag1.src = "/img/flag/i/" + country[toFlag[1]] + ".png";
    document.toFlag2.src = "/img/flag/i/" + country[toFlag[2]] + ".png";
    document.toFlag3.src = "/img/flag/i/" + country[toFlag[3]] + ".png";
    document.toFlag4.src = "/img/flag/i/" + country[toFlag[4]] + ".png";
    document.toFlag5.src = "/img/flag/i/" + country[toFlag[5]] + ".png";
}

function setFromDrop(i)
{
  document.calcForm.from.selectedIndex = fromFlag[i];  
  Cvalue();
}

function setToDrop(i)
{
  document.calcForm.to.selectedIndex = toFlag[i];  
  Cvalue();
}

function popupCalc()
{
  window.open('jscalc.html','newWin','width=380,height=180');
}

function graphThese()
{
  var newurl = "http://www.gocurrency.com/d/" +
  currency[document.calcForm.to.selectedIndex] +
  "/" +
  currency[document.calcForm.from.selectedIndex] +
  "/graph120.html";

  var version = navigator.appVersion;

  if (version.indexOf("MSIE") != -1)
  {
    window.location.href = newurl
  }else
      window.open( newurl , target="_self")

}

function Cvalue()
{
  // missing relevant digits
  
  var fromR, toR, resultV;
  //take this out ASAP
  //alert(document.calcForm.from.selectedIndex);
 	  
fromR = rate[document.calcForm.from.selectedIndex];
  toR = rate[document.calcForm.to.selectedIndex];
  nVal = document.calcForm.inV.value;
  
  if ( IsNumeric(nVal) == false ) {
    alert("amount to multiply is not a number\n\nyou can only use\n\n1234567890 and . (dot)");
  }
  
  resultV = nVal * ( toR / fromR );

  // 6 relevant digits only, or integer 
  if ( (resultV == parseInt(resultV)) || (resultV > 99999) )
  {
    // mostly integer
    resultV = parseInt( resultV );
  }
  else
  {
    if (resultV > 1)
    {
	resultV = resultV.toString();
	resultV = resultV.substring(0,7);
    } else {
	resultV = resultV.toString();
	resultV = resultV.substring(0,8);
    }
  }

 
  document.calcForm.outV.value = "   " + comma(resultV) + " " + currency[document.calcForm.to.selectedIndex];
}

function Cvalue2(fromAuto,toAuto)
{
  //yay, it's definitely being called!
  // building a costumized version of this function
  //fromAuto is the default from currency 
  //toAuto is the default to currency
  //WILL BE ADDED LATER: number is the default value
alert("cvalue2");
  //Integers are used for the values	

   var fromR, toR, resultV, nVal;
  //take this out ASAP
  
  fromR = rate[document.calcForm.from.selectedIndex];
  toR = rate[document.calcForm.to.selectedIndex];
  nVal = document.calcForm.inV.value;
  
 	
  if ( IsNumeric(nVal) == false ) {
    alert("amount to multiply is not a number\n\nyou can only use\n\n1234567890 and . (dot)");
  }

  resultV = nVal * ( toR / fromR );

  // 6 relevant digits only, or integer
  if ( (resultV == parseInt(resultV)) || (resultV > 99999) )
  {
    // mostly integer
    resultV = parseInt( resultV );
  }
  else
  {
    if (resultV > 1)
    {
        resultV = resultV.toString();
        resultV = resultV.substring(0,7);
    } else {
        resultV = resultV.toString();
        resultV = resultV.substring(0,8);
    }
  }


  document.calcForm.outV.value = "   " + comma(resultV) + " " + currency[document.calcForm.to.selectedIndex];
}
  

function invertCurr()
{
  var i;
  i = document.calcForm.from.selectedIndex;
  document.calcForm.from.selectedIndex = document.calcForm.to.selectedIndex;
  document.calcForm.to.selectedIndex = i;
  changeFlag('from');
  changeFlag('to');
  Cvalue();
}

function comma(num)
{
 var n = Math.floor(num);
 var myNum = num + "";
 var myDec = ""
 
 if (myNum.indexOf('.',0) > -1){
  myDec = myNum.substring(myNum.indexOf('.',0),myNum.length);
 }
 var arr=new Array('0'), i=0; 
 while (n>0) 
   {arr[i]=''+n%1000; n=Math.floor(n/1000); i++;}
 arr=arr.reverse();
 for (var i in arr) if (i>0) //padding zeros
   while (arr[i].length<3) arr[i]='0'+arr[i];
 return arr.join() + myDec;
}

function IsNumeric(strString)
{
   var strValidChars = "0123456789.";
   var strChar;
   var blnResult = true;

   for (i = 0; i < strString.length && blnResult == true; i++)
   {
      strChar = strString.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
      {
         blnResult = false;
      }
   }
   return blnResult;
}

function sendForm()
{
    if (currency[document.calcForm.from.selectedIndex] == "TRY" || currency[document.calcForm.to.selectedIndex] == "TRY")
    {
      alert("Please make sure the From & To fields are filled!");
    }
    else {
    window.open( 'http://www.gocurrency.com/cgi-bin/fxcmcalc?value=' + nVal + '&base=' + currency[document.calcForm.from.selectedIndex] + '&target=' + currency[document.calcForm.to.selectedIndex] );
    }
    return true;
}

