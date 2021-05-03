function alphaonly(input,event)
{
  var keyCode = event.which ? event.which : event.keyCode;
			//Small Alphabets
			if(parseInt(keyCode)>=97 && parseInt(keyCode)<=122){
				return true;
			}
			//Caps Alphabets
			if(parseInt(keyCode)>=65 && parseInt(keyCode)<=90){
				return true;
			}
			//Space-Return-Dot<!-- Changes made by Sanjiv for ELTOPUSPRD-18498 -->
			if(parseInt(keyCode)==32 || parseInt(keyCode)==13 || parseInt(keyCode)==46 || keyCode==9/*TAB*/ || keyCode==8/*BCKSPC*/ || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ ){
				return true;
			}
			input.focus();
			return false;

}

function convertToCaps(input)
{
 
 input.value=input.value.toUpperCase();

}

function digitonly(input,evt)
{
	var keyCode = evt.which ? evt.which : evt.keyCode;
			var lisShiftkeypressed = evt.shiftKey;
			if(lisShiftkeypressed && parseInt(keyCode) != 9) {
			return false ;
			}
			if((parseInt(keyCode)>=48 && parseInt(keyCode)<=57) || keyCode==37/*LFT ARROW*/ || keyCode==39/*RGT ARROW*/ || keyCode==8/*BCKSPC*/ || keyCode==46/*DEL*/ || keyCode==9/*TAB*/ || keyCode==46/*DOT*/){
			return true;
			}

			return false;
}



