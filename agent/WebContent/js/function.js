function digitonly(input,evt)
{
	var keyCode = evt.which ? evt.which : evt.keyCode;
			var lisShiftkeypressed = evt.shiftKey;
			if(lisShiftkeypressed && parseInt(keyCode) != 9) {
			return false ;
			}
			if((parseInt(keyCode)>=46 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==9/*TAB*/ || keyCode==45/*DASH*/){
			return true;
			}

			return false;

}




