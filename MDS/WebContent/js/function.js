// JavaScript Document

$(document).ready(function(e) {
   $('.heading_tabs p').click(function(){
	  targ_id=$(this).attr('id');
	  $('.heading_tabs p').removeClass('active_tab');
	  $(this).addClass('active_tab'); 
	  $('.login_form_wrp').addClass('hide');
	  $('#'+targ_id+'_1').removeClass('hide');
	}); 

  $(".trip_type").each(function(){
      $(this).click(function(){
        var tabeId1 = $(this).attr('id');
		$(".trip_type").removeAttr('checked');
		if ($(this).attr('checked')==false){
		 $(this).attr('checked', true);
		}
        $(".table_fgt").addClass("hide");
        $("#"+tabeId1+"_1").removeClass("hide");
      });
    });  
	
	$(".inter_fgt").each(function(){
      $(this).click(function(){
        var tabeId2 = $(this).attr('id');
		$(".inter_fgt").removeAttr('checked');
		if ($(this).attr('checked')==false){
		 $(this).attr('checked', true);
		}
        $(".international_fgt").addClass("hide");
        $("#"+tabeId2+"_1").removeClass("hide");
      });
    });  
	
	/**rooms selection**/
	$('p.no_rooms select').change(function(e) {
       var value_room=$(this).val();
	    var sec_room=$(this).parent('p.no_rooms').parent('td').parent('tr').next('tr').next('tr').next('tr.second_rooms');
	    var thr_room=$(this).parent('p.no_rooms').parent('td').parent('tr').next('tr').next('tr').next('tr').next('tr.third_rooms'); 
		var frth_room=$(this).parent('p.no_rooms').parent('td').parent('tr').next('tr').next('tr').next('tr').next('tr').next('tr.forth_rooms');
		
		
		if(value_room=='1')
		{
			sec_room.addClass('hide');
			thr_room.addClass('hide');
			frth_room.addClass('hide');
		}
		
		if(value_room=='2')
		{
			sec_room.removeClass('hide');
			thr_room.addClass('hide');
			frth_room.addClass('hide');
		}
		
		if(value_room=='3')
		{
			sec_room.removeClass('hide');
			thr_room.removeClass('hide');
			frth_room.addClass('hide');
	
		}
		
		if(value_room=='4')
		{
			sec_room.removeClass('hide');
			thr_room.removeClass('hide');
			frth_room.removeClass('hide');
	
		}
		
    });
	
	/**end of no_of_rooms selection**/
	
	
	/**for child selection**/
	
	$('p.onchild select').change(function(e) {
       var value_child=$(this).val();
	   var first_childd=$(this).parent('p.onchild').parent('td').next('td').children('p.first_child');
	   var second_childd=$(this).parent('p.onchild').parent('td').next('td').next('td').children('p.second_child');     
	   if(value_child=='0')
	   {
		 first_childd.addClass('hide');
		 second_childd.addClass('hide');  
	   }  
	   if(value_child=='1')
	   {
		 first_childd.removeClass('hide');
		 second_childd.addClass('hide');    
	   }  
	   if(value_child=='2')
	   {
		 first_childd.removeClass('hide');
		 second_childd.removeClass('hide');       
	   }  
    });
	
	
	/**for flight panel**/
	$('.upload_logo').click(function(e) {
		$(this).next('span.file_logo').removeClass('hide'); 
    });
	
	
	/**end of flight panel**/
	
	
	/**bus ticket detail value**/
	$('.ticekt_detailBtn').click(function(e) {
        $('.full_dtl').removeClass('hide');
		$('.last_10').addClass('hide');
    });
	
	
	/**view seat**/
   
   
   /**start of serial data**/
    $('.serial_number_top').change(function(e) {
        if($(this).is(':checked'))
		{
		  $('.serial_number').attr('checked',true);
		}
		else{
		 $('.serial_number').attr('checked',false);
		}
		
    });
	
   /**end of serial data**/	
   
   
   /**transfer request page**/
   
   $('.payment_mode_options').change(function(e) {
     var select_val_top=$(this).val();
     if(select_val_top=='CashDeposite')
	 {
      $('.cash_deposit_fields').removeClass('hide');
	  $('.cheque_deposit_fields').addClass('hide');
	  $('.online_deposit_fields').addClass('hide');
	   $('.NEFT_RTGS_deposit_fields').addClass('hide');
	 }
	 else if(select_val_top=='Cheque/DD')
	 {
      $('.cash_deposit_fields').addClass('hide');
	  $('.cheque_deposit_fields').removeClass('hide');
	  $('.online_deposit_fields').addClass('hide');
	   $('.NEFT_RTGS_deposit_fields').addClass('hide');
	 }
	 
	 else if(select_val_top=='OnlineETransfer')
	 {
      $('.cash_deposit_fields').addClass('hide');
	  $('.cheque_deposit_fields').addClass('hide');
	  $('.online_deposit_fields').removeClass('hide');
	   $('.NEFT_RTGS_deposit_fields').addClass('hide');
     
	 }
	  else if(select_val_top=='NEFT/RTGS')
	 {
      $('.cash_deposit_fields').addClass('hide');
	  $('.cheque_deposit_fields').addClass('hide');
	  $('.online_deposit_fields').addClass('hide');
	  $('.NEFT_RTGS_deposit_fields').removeClass('hide');
     
	 }
	 else
	 {
      $('.cash_deposit_fields').addClass('hide');
	  $('.cheque_deposit_fields').addClass('hide');
	  $('.online_deposit_fields').addClass('hide');
	  $('.NEFT_RTGS_deposit_fields').addClass('hide');
     
	 }
	 
   });
   
   
   $('.bank_cash').change(function(e) {
      var select_val=$(this).val();
	  var acc_no=$(this).parent('td').parent('tr').next('tr.acc_no_other'); 
      var acc_branch=acc_no.next('tr.acc_branchna_other');
	  var file_att=acc_branch.next('tr.file_attach');
	  if(select_val=='Other')
	  {
	
	   acc_no.removeClass('hide');
	   acc_branch.removeClass('hide');
	   file_att.addClass('hide');
	  }
	  else
	  {
	   
	   acc_no.addClass('hide');
	   acc_branch.addClass('hide');
	   file_att.removeClass('hide');
	  }
	  
	  

	  
   });
   
   /**end of transfer request page**/
   
   
 
   
   
   
   
   

});



function openpopup(id){ 
     
      var pageWidth = window.innerWidth; 
      var pageHeight = window.innerHeight; 
      if (typeof pageWidth != "number"){ 
      if (document.compatMode == "CSS1Compat"){ 
            pageWidth = document.documentElement.clientWidth; 
            pageHeight = document.documentElement.clientHeight; 
      } else { 
            pageWidth = document.body.clientWidth; 
            pageHeight = document.body.clientHeight; 
      } 
      }  
      //Make the background div tag visible... 
      var divbg = document.getElementById('bg'); 
      divbg.style.visibility = "visible"; 
        
      var divobj = document.getElementById(id); 
      divobj.style.visibility = "visible"; 
      if (navigator.appName=="Microsoft Internet Explorer") 
      computedStyle = divobj.currentStyle; 
      else computedStyle = document.defaultView.getComputedStyle(divobj, null); 
      //Get Div width and height from StyleSheet 
      var divWidth = computedStyle.width.replace('px', ''); 
      var divHeight = computedStyle.height.replace('px', ''); 
      var divLeft = (pageWidth - divWidth) / 2; 
      var divTop = (pageHeight - divHeight) / 2; 
      //Set Left and top coordinates for the div tag 
      divobj.style.left = divLeft + "px"; 
      
      //Put a Close button for closing the popped up Div tag 
      if(divobj.innerHTML.indexOf("closepopup('" + id +"')") < 0 ) 
      divobj.innerHTML = "<a href=\"#\" onclick=\"closepopup('" + id +"')\"><span class=\"close_button\">X</span></a>" + divobj.innerHTML; 
	  
} 
function closepopup(id){ 
      var divbg = document.getElementById('bg'); 
      divbg.style.visibility = "hidden"; 
      var divobj = document.getElementById(id); 
      divobj.style.visibility = "hidden"; 
} 

function show_pending(){
   var list_pending=document.getElementById('listType').value;
   if(list_pending=='booking')
    { 
	  document.getElementById('pending_booking').style.display='block';
	  document.getElementById('pending_cancel').style.display='none';	
	}
   else if(list_pending=='cancel')
    {
	document.getElementById('pending_booking').style.display='none';
	  document.getElementById('pending_cancel').style.display='block';	
	}
	else
	{
	document.getElementById('pending_booking').style.display='none';
	  document.getElementById('pending_cancel').style.display='none';	
	}	
}

/* Function for Utility */
$(document).ready(function(){
	
	$("#icon_1").hover(function(){
		
		$(".icon_1").show();
		
		}).mouseout(function(){
			
			$(".icon_1").hide();
			})
		
		
		$("#icon_2").hover(function(){
		
		$(".icon_2").show();
		
		}).mouseout(function(){
			
			$(".icon_2").hide();
			})
		
		
		
		
		$("#icon_3").hover(function(){
		
		$(".icon_3").show();
		
		}).mouseout(function(){
			
			$(".icon_3").hide();
			})
		
		
		
		$("#icon_4").hover(function(){
		
		$(".icon_4").show();
		
		}).mouseout(function(){
			
			$(".icon_4").hide();
			})
		
		
		$("#icon_5").hover(function(){
		
		$(".icon_5").show();
		
		}).mouseout(function(){
			
			$(".icon_5").hide();
			})
		
		
		$("#icon_6").hover(function(){
		
		$(".icon_6").show();
		
		}).mouseout(function(){
			
			$(".icon_6").hide();
			})
		
		
		
		$("#icon_9").hover(function(){
		
		$(".icon_9").show();
		
		}).mouseout(function(){
			
			$(".icon_9").hide();
			})
		
		
		
		
		
		$("#reliance_energy").change(function(){
			
			var val = $("#reliance_energy").val();
			
			if(val == "Reliance Energy (Mumbai)")
			{
			$("#circle_tr").css("display","");
			}
			
			else
			{
				$("#circle_tr").css("display","none");
			}
			
			})
		
		
		$("#change_td").change(function(){
										
				var val = $("#change_td").val();
				
				if(val == "Aircel chennai")
				{
					$("#con_no").css("display","none");
					$("#sub_id").css("display","block");
					$(".icon_9").css("display","none");
					$("#icon_9").css("display","block");
					
				}
				
				else
				{
					$("#con_no").css("display","block");
					$("#sub_id").css("display","none");
					$(".icon_9").css("display","none");
					$("#icon_9").css("display","none");
				}
										
										
										
										})		
	
	})

