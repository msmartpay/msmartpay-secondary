
function test_skill(ruppes,container)
{
	
	var junkVal=ruppes;
	junkVal=Math.floor(junkVal);
	var obStr=new String(junkVal);
	numReversed=obStr.split("");
	actnumber=numReversed.reverse();

	if(Number(junkVal) >=0)
	{
		//do nothing
	}
	else
	{
		alert('wrong Number cannot be converted');
		return false;
	}
	if(Number(junkVal)==0)
	{
		document.getElementById('container').innerHTML=obStr+''+'Rupees Zero Only';
		return false;
	}
	if(actnumber.length>9)
	{
		alert('Oops!!!! the Number is too big to covertes');
		return false;
	}

	var iWords=["ZERO", " ONE", " TWO", " THREE", " FOUR", " FIVE", " SIX", " SEVEN", " EIGHT", " NINE"];
	var ePlace=[' TEN', ' ELEVEN', ' TWELVE', ' THIRTEEN', ' FOURTEEN', ' FIFTEEN', ' SIZTEEN', ' SEVENTEEN', ' EIGHTEEN', ' NINETEEN'];
	var tensPlace=['DUMMY', ' TEN', ' TWENTY', ' THIRTY', ' FOURTY', ' FIFTY', ' SIXTY', ' SEVENTY', ' EIGHTY', ' NINETY' ];

	var iWordsLength=numReversed.length;
	var totalWords="";
	var inWords=new Array();
	var finalWord="";
	j=0;
	for(i=0; i<iWordsLength; i++)
	{
		switch(i)
		{
		case 0:
			if(actnumber[i]==0 || actnumber[i+1]==1 )
			{
				inWords[j]='';
			}
			else
			{
				inWords[j]=iWords[actnumber[i]];
			}
			inWords[j]=inWords[j]+' ONLY';
			break;
		case 1:
			tens_complication();
			break;
		case 2:
			if(actnumber[i]==0)
			{
				inWords[j]='';
			}
			else if(actnumber[i-1]!=0 && actnumber[i-2]!=0)
			{
				inWords[j]=iWords[actnumber[i]]+' HUNDRED AND';
			}
			else
			{
				inWords[j]=iWords[actnumber[i]]+' HUNDRED';
			}
			break;
		case 3:
			if(actnumber[i]==0 || actnumber[i+1]==1)
			{
				inWords[j]='';
			}
			else
			{
				inWords[j]=iWords[actnumber[i]];
			}
			inWords[j]=inWords[j]+" THOUSAND";
			break;
		case 4:
			tens_complication();
			break;
		case 5:
			if(actnumber[i]==0 || actnumber[i+1]==1 )
			{
				inWords[j]='';
			}
			else
			{
				inWords[j]=iWords[actnumber[i]];
			}
			inWords[j]=inWords[j]+" LAKH";
			break;
		case 6:
			tens_complication();
			break;
		case 7:
			if(actnumber[i]==0 || actnumber[i+1]==1 )
			{
				inWords[j]='';
			}
			else
			{
				inWords[j]=iWords[actnumber[i]];
			}
			inWords[j]=inWords[j]+" CORERE";
			break;
		case 8:
			tens_complication();
			break;
		default:
			break;
		}
		j++;
	}

	function tens_complication()
	{
		if(actnumber[i]==0)
		{
			inWords[j]='';
		}
		else if(actnumber[i]==1)
		{
			inWords[j]=ePlace[actnumber[i-1]];
		}
		else
		{
			inWords[j]=tensPlace[actnumber[i]];
		}
	}
	inWords.reverse();
	for(i=0; i<inWords.length; i++)
	{
		finalWord+=inWords[i];
	}
	container.innerHTML=finalWord;
}