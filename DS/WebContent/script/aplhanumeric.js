	
	function checkPassword(pw,options)
	{
	var o = {
		
		special:  0,
		
		custom:   [ /* regexes and/or functions */ ],
		badWords: [],
	
		noQwertySequences: false,
		noSequential:      false
	};

	for (var property in options)
		o[property] = options[property];

	var	re = {
		
			special: /[\W_]/g
		},
		rule, i;


	for (rule in re) {
		
		if ((pw.match(re[rule]) || []).length < o[rule]){
			alert(" Donot nsert one special");
			return false;
			
			}
	}


		

	}
	

	
	function validatefrm() {

	var passed = checkPassword(document.changePasswordForm.NewPassword.value, {
	
	
	special:  1,
	
		});
		}