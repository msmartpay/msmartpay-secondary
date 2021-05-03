<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<%@ include file="../../wldetails.jsp" %>	

	<!-- Basic Page Needs
	================================================== -->
	<title><%=companyName %></title>	

	<!-- Mobile Specific Metas
	================================================== -->
	
	<%@ include file="../../script.jsp" %>
	
	<style type="text/css">
	.form-group label{
		font-weight: bold;
	}
	.err_msg{
		margin-top: 10px;
	    background: #0080008a;
	    color: #fff;
	    padding: 5px;
	    border-radius: 5px;
	    display: none;
	    text-align: center;
    	font-size: 14px;
	}
</style>
	
	<script language="javascript">

		$(document).ready(function(){
		 	
			$("#i_ad1").datepicker({
		        changeMonth: true,
				changeYear: true,
				dateFormat: 'dd-mm-yy',
				yearRange:'1950:2018',
		        numberOfMonths: 1,
				defaultDate: "+1w",
				maxDate: "today"
			})
			  
		});

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
		
		function payBill(service){
			
			var ElecContactNo='',opname='',opCode='',ElectAccountNo='',ElecBillAmount='';
			var opname = '',REQUEST_ID='',reference_id='';
			REQUEST_ID = $("#REQUEST_ID").val();
			reference_id = $("#reference_id").val();
			
			if(service=='POSTPAID'){
				opname = $("#billpayOp option:selected").val();
				opCode=$('#billpayOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#ConnectionNo").val();
				ElecBillAmount = $("#postpaidAmount").val();
				ElecContactNo=ElectAccountNo;
			}
			else if(service=='ELECTRICITY'){
				opname = $("#ElectrictyOP option:selected").val();
				opCode=$('#ElectrictyOP').find('option:selected').attr('opCode');
				ElectAccountNo = $("#ElectAccountNo").val();
				ElecBillAmount = $("#ElecBillAmount").val();
				ElecContactNo = $("#ElecContactNo").val();
				
				
			}else if(service=='INSURANCE'){
				opname = $("#insurancePayOperator option:selected").val();
				opCode=$('#insurancePayOperator').find('option:selected').attr('opCode');
				ElectAccountNo = $("#policyNumber").val();
				ElecBillAmount = $("#premiumAmount").val();
				
			}else if(service=='LANDLINE'){
				opname = $("#landlineOp option:selected").val();
				opCode=$('#landlineOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#landlineNo").val();
				ElecBillAmount = $("#landlineAmount").val();
				
			}else if(service=='BROADBAND'){
				opname = $("#broadbandOp option:selected").val();
				opCode=$('#broadbandOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#broadbandNo").val();
				ElecBillAmount = $("#broadbandAmount").val();
				
				
			}else if(service=='WATER'){
				opname = $("#waterOp option:selected").val();
				opCode=$('#waterOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#waterNo").val();
				ElecBillAmount = $("#waterAmount").val();
				
				
			}else if(service=='GAS'){
				opname = $("#gasOp option:selected").val();
				opCode=$('#gasOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#gasNo").val();
				ElecBillAmount = $("#gasAmount").val();
				
			}
				
			
			var AD1='',AD2='';
			
			if(opname=='Torrent Power'|| opname=='MSEDC - MAHARASHTRA')
			{
				AD1=$('#BillUnit').val();
				if(AD1==''){
					$("#BillUnit").focus();
					return false;
				}
			}else if(opname=='Reliance Energy'){
				AD1=$('#CycleNo').val();
				if(AD1==''){
					$("#CycleNo").focus();
					return false;
				}
			}else if(opname=='Mahanagar Gas'){
				AD1=$('#g_ad1').val();
				if(AD1==''){
					$("#g_ad1").focus();
					return false;
				}
			}
			else if(opname=='ICICI Prudential Life Insurance' || opname=='Tata AIG General Insurance'){
				AD1=$('#i_ad1').val();
				if(AD1==''){
					$("#i_ad1").focus();
					return false;
				}
			}else if(opname=='Airtel Landline' || opname=='BSNL Landline' || opname=='MTNL - Delhi' || opname=='MTNL - Mumbai' || opname=='Tata Docomo'){
				AD1=$('#l_ad1').val();
				if(AD1==''){
					$("#l_ad1").focus();
					return false;
				}
			}else if(opname=='Municipal Corporation of Gurugram'){
				AD1=$('#w_ad1').val();
				if(AD1==''){
					$("#w_ad1").focus();
					return false;
				}
				AD2=$('#w_ad2').val();
				if(AD2==''){
					$("#w_ad2").focus();
					return false;
				}
			}

			$('#resultLoading').show();
			
			$.ajax({
			 	url:"viewBill.action?param=payBillpay&OPName="+opname+"&OP="+opCode+"&CN="+
			 			ElectAccountNo+"&AMT="+ElecBillAmount+"&AD1="+AD1+"&AD2="+AD2+"&AD4="+ElecContactNo+
			 			"&reference_id="+reference_id+"&REQUEST_ID="+REQUEST_ID+"&Service="+service,
				type : "GET",
				dataType: "json",
				success: function (response) {
					
					$('#resultLoading').hide();
					var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
					if(service=='POSTPAID'){
						 $("#ConnectionNo").val('');
						 $("#postpaidAmount").val('');
					}
					else if(service=='ELECTRICITY'){
						$('#ElecBillAmount').val('');
			        	$('#ElecContactNo').val('');
			        	$("#ElectAccountNo").val('')
			        	$("#BillUnit").val('')
			        	$("#CycleNo").val('')
			        	
			        	$('.e_view_details').hide();
						$('#e_customerName').val('');
			        	$('#e_duedate').val('');
			        	
			        	$('#view_ele').show();
			        	$('#ele').hide();
			        	$('#ElecBillAmount').removeAttr('readonly');
					}else if(service=='INSURANCE'){
						$("#policyNumber").val('');
						$("#premiumAmount").val('');
						$("#i_ad1").val('');
						
						$('.i_view_details').hide();
						$('#i_customerName').val('');
			        	$('#i_duedate').val('');
			        	
			        	$('#view_insurance').show();
			        	$('#insurance').hide();
			        	$('#premiumAmount').removeAttr('readonly');
					}else if(service=='LANDLINE'){
						$("#landlineNo").val('');
						$("#landlineAmount").val('');
						$("#l_ad1").val('');
						
						$('.l_view_details').hide();
						$('#l_customerName').val('');
			        	$('#l_duedate').val('');
			        	
			        	$('#view_land').show();
			        	$('#land').hide();
			        	$('#landlineAmount').removeAttr('readonly');
					}else if(service=='BROADBAND'){
						$("#broadbandNo").val('');
						$("#broadbandAmount").val('');
						
						$('.b_view_details').hide();
						$('#b_customerName').val('');
			        	$('#b_duedate').val('');
			        	
			        	$('#view_broadband').show();
			        	$('#broadband').hide();
			        	
			        	$('#broadbandAmount').removeAttr('readonly');
						
					}else if(service=='WATER'){
						$("#waterNo").val('');
						$("#waterAmount").val('');
						$("#w_ad1").val('');
						$("#w_ad2").val('');
						
						$('.w_view_details').hide();
						$('#w_customerName').val('');
			        	$('#w_duedate').val('');
			        	
			        	$('#view_water').show();
			        	$('#water').hide();
			        	
			        	$('#waterAmount').removeAttr('readonly');
						
					}else if(service=='GAS'){
						$("#gasNo").val('');
						$("#gasAmount").val('');
						$("#g_ad1").val('');
						
						$('.g_view_details').hide();
						$('#g_customerName').val('');
			        	$('#g_duedate').val('');
			        	
			        	$('#view_gas').show();
			        	$('#gas').hide();
			        	
			        	$('#gasAmount').removeAttr('readonly');
					}
					
				},

			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		
			 		var message=response.Description;
					$('.err_msg').text(message);
					$('.err_msg').show();
					
		        	if(service=='POSTPAID'){
						 $("#ConnectionNo").val('');
						 $("#postpaidAmount").val('');
					}
					else if(service=='ELECTRICITY'){
						$('#ElecBillAmount').val('');
			        	$('#ElecContactNo').val('');
			        	$("#ElectAccountNo").val('')
			        	$("#BillUnit").val('')
			        	$("#CycleNo").val('')
			        	
			        	$('.e_view_details').hide();
						$('#e_customerName').val('');
			        	$('#e_duedate').val('');
			        	
			        	$('#view_ele').show();
			        	$('#ele').hide();
			        	$('#ElecBillAmount').removeAttr('readonly');
					}else if(service=='INSURANCE'){
						$("#policyNumber").val('');
						$("#premiumAmount").val('');
						$("#i_ad1").val('');
						
						$('.i_view_details').hide();
						$('#i_customerName').val('');
			        	$('#i_duedate').val('');
			        	
			        	$('#view_insurance').show();
			        	$('#insurance').hide();
			        	$('#premiumAmount').removeAttr('readonly');
					}else if(service=='LANDLINE'){
						$("#landlineNo").val('');
						$("#landlineAmount").val('');
						$("#l_ad1").val('');
						
						$('.l_view_details').hide();
						$('#l_customerName').val('');
			        	$('#l_duedate').val('');
			        	
			        	$('#view_land').show();
			        	$('#land').hide();
			        	$('#landlineAmount').removeAttr('readonly');
					}else if(service=='BROADBAND'){
						$("#broadbandNo").val('');
						$("#broadbandAmount").val('');
						
						$('.b_view_details').hide();
						$('#b_customerName').val('');
			        	$('#b_duedate').val('');
			        	
			        	$('#view_broadband').show();
			        	$('#broadband').hide();
			        	
			        	$('#broadbandAmount').removeAttr('readonly');
						
					}else if(service=='WATER'){
						$("#waterNo").val('');
						$("#waterAmount").val('');
						$("#w_ad1").val('');
						$("#w_ad2").val('');
						
						$('.w_view_details').hide();
						$('#w_customerName').val('');
			        	$('#w_duedate').val('');
			        	
			        	$('#view_water').show();
			        	$('#water').hide();
			        	
			        	$('#waterAmount').removeAttr('readonly');
						
					}else if(service=='GAS'){
						$("#gasNo").val('');
						$("#gasAmount").val('');
						$("#g_ad1").val('');
						
						$('.g_view_details').hide();
						$('#g_customerName').val('');
			        	$('#g_duedate').val('');
			        	
			        	$('#view_gas').show();
			        	$('#gas').hide();
			        	
			        	$('#gasAmount').removeAttr('readonly');
					}
			    }
				
				
			});
		}
	
		
	
	</script>
	
	<script type="text/javascript">
	
		function viewBill(service){
			
			var ElecContactNo='',opname='',opCode='',ElectAccountNo='',ElecBillAmount='',AD1='',AD2='';
			if(service=='ELECTRICITY'){
				opname = $("#ElectrictyOP option:selected").val();
				opCode=$('#ElectrictyOP').find('option:selected').attr('opCode');
				ElectAccountNo = $("#ElectAccountNo").val();
				ElecBillAmount = $("#ElecBillAmount").val();
				ElecContactNo = $("#ElecContactNo").val();
				
				if(opname==''){
					$("#ElectrictyOP").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#ElectAccountNo").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#ElecBillAmount").focus();
					return false;
				}
				if(ElecContactNo==''){
					$("#ElecContactNo").focus();
					return false;
				}
			}else if(service=='INSURANCE'){
				opname = $("#insurancePayOperator option:selected").val();
				opCode=$('#insurancePayOperator').find('option:selected').attr('opCode');
				ElectAccountNo = $("#policyNumber").val();
				ElecBillAmount = $("#premiumAmount").val();
				
				if(opname==''){
					$("#insurancePayOperator").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#policyNumber").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#premiumAmount").focus();
					return false;
				}
			}else if(service=='LANDLINE'){
				opname = $("#landlineOp option:selected").val();
				opCode=$('#landlineOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#landlineNo").val();
				ElecBillAmount = $("#landlineAmount").val();
				
				if(opname==''){
					$("#landlineOp").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#landlineNo").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#landlineAmount").focus();
					return false;
				}
			}else if(service=='BROADBAND'){
				opname = $("#broadbandOp option:selected").val();
				opCode=$('#broadbandOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#broadbandNo").val();
				ElecBillAmount = $("#broadbandAmount").val();
				
				if(opname==''){
					$("#broadbandOp").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#broadbandNo").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#broadbandAmount").focus();
					return false;
				}
				
			}else if(service=='WATER'){
				opname = $("#waterOp option:selected").val();
				opCode=$('#waterOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#waterNo").val();
				ElecBillAmount = $("#waterAmount").val();
				
				if(opname==''){
					$("#waterOp").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#waterNo").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#waterAmount").focus();
					return false;
				}
				
			}else if(service=='GAS'){
				opname = $("#gasOp option:selected").val();
				opCode=$('#gasOp').find('option:selected').attr('opCode');
				ElectAccountNo = $("#gasNo").val();
				ElecBillAmount = $("#gasAmount").val();
				
				if(opname==''){
					$("#gasOp").focus();
					return false;
				}
				if(ElectAccountNo==''){
					$("#gasNo").focus();
					return false;
				}
				if(ElecBillAmount==''){
					$("#gasAmount").focus();
					return false;
				}
				
			}else{
				opname = $("#billpayOp option:selected").val();
				opCode=$('#billpayOp').find('option:selected').attr('opCode');
				
				ElectAccountNo = $("#ConnectionNo").val();
				ElecBillAmount = $("#postpaidAmount").val();
			}
			
			if(opname=='Torrent Power'|| opname=='MSEDC - MAHARASHTRA')
			{
				AD1=$('#BillUnit').val();
				if(AD1==''){
					$("#BillUnit").focus();
					return false;
				}
			}else if(opname=='Reliance Energy'){
				AD1=$('#CycleNo').val();
				if(AD1==''){
					$("#CycleNo").focus();
					return false;
				}
			}else if(opname=='Mahanagar Gas'){
				AD1=$('#g_ad1').val();
				if(AD1==''){
					$("#g_ad1").focus();
					return false;
				}
			}
			else if(opname=='ICICI Prudential Life Insurance' || opname=='Tata AIG General Insurance'){
				AD1=$('#i_ad1').val();
				if(AD1==''){
					$("#i_ad1").focus();
					return false;
				}
			}else if(opname=='Airtel Landline' || opname=='BSNL Landline' || opname=='MTNL - Delhi' || opname=='MTNL - Mumbai' || opname=='Tata Docomo'){
				AD1=$('#l_ad1').val();
				if(AD1==''){
					$("#l_ad1").focus();
					return false;
				}
			}else if(opname=='Municipal Corporation of Gurugram'){
				AD1=$('#w_ad1').val();
				if(AD1==''){
					$("#w_ad1").focus();
					return false;
				}
				AD2=$('#w_ad2').val();
				if(AD2==''){
					$("#w_ad2").focus();
					return false;
				}
			}
			
	
			$('#resultLoading').show();
			
			$.ajax({
			 	url:"viewBill.action?param=viewBillpay&OPName="+opname+"&OP="+opCode+"&CN="+ElectAccountNo+"&AMT="+ElecBillAmount+"&AD1="+AD1+"&AD2="+AD2+"&AD4="+ElecContactNo+"&Service="+service,
				type : "GET",
				dataType: "json",
				success: function (response) {
					$('#resultLoading').hide();
					var status=response.Status;
					var message=response.Description;
					if(status==0){
						
						$('#REQUEST_ID').val(response.REQUEST_ID);
						$('#reference_id').val(response.reference_id);
						
			        	if(service=='ELECTRICITY'){
			        		$('.e_view_details').show();
							$('#e_customerName').val(response.customername);
				        	$('#e_duedate').val(response.duedate);
				        	$('#ElecBillAmount').val(response.dueamount);
				        	$('#ElecBillAmount').attr('readonly','readonly');
				        	$('#view_ele').hide();
				        	$('#ele').show();
						}else if(service=='INSURANCE'){
							$('.i_view_details').show();
							$('#i_customerName').val(response.customername);
				        	$('#i_duedate').val(response.duedate);
				        	$('#premiumAmount').val(response.dueamount);
				        	$('#premiumAmount').attr('readonly','readonly');
				        	$('#view_insurance').hide();
				        	$('#insurance').show();
						}else if(service=='LANDLINE'){
							$('.l_view_details').show();
							$('#l_customerName').val(response.customername);
				        	$('#l_duedate').val(response.duedate);
				        	$('#landlineAmount').val(response.dueamount);
				        	$('#landlineAmount').attr('readonly','readonly');
				        	$('#view_land').hide();
				        	$('#land').show();
						}else if(service=='BROADBAND'){
							$('.b_view_details').show();
							$('#b_customerName').val(response.customername);
				        	$('#b_duedate').val(response.duedate);
				        	$('#broadbandAmount').val(response.dueamount);
				        	$('#broadbandAmount').attr('readonly','readonly');
				        	$('#view_broadband').hide();
				        	$('#broadband').show();
						}else if(service=='WATER'){
							$('.w_view_details').show();
							$('#w_customerName').val(response.customername);
				        	$('#w_duedate').val(response.duedate);
				        	$('#waterAmount').val(response.dueamount);
				        	$('#waterAmount').attr('readonly','readonly');
				        	$('#view_water').hide();
				        	$('#water').show();
						}else if(service=='GAS'){
							$('.g_view_details').show();
							$('#g_customerName').val(response.customername);
				        	$('#g_duedate').val(response.duedate);
				        	$('#gasAmount').val(response.dueamount);
				        	$('#gasAmount').attr('readonly','readonly');
				        	$('#view_gas').hide();
				        	$('#gas').show();
						}else if(service=='POSTPAID'){
				        	$('#view_mobilebill').hide();
				        	$('#mobilebill').show();
						}
						
					}else{
						
						if(service=='POSTPAID'){
			        		$("#ConnectionNo").val('');
							$("#postpaidAmount").val('');
							$('#view_mobilebill').show();
				        	$('#mobilebill').hide();
			        	}else if(service=='ELECTRICITY'){
							$('#ElecBillAmount').val('');
				        	$('#ElecContactNo').val('');
				        	$("#ElectAccountNo").val('')
				        	$("#BillUnit").val('')
				        	$("#CycleNo").val('')
				        	
				        	$('.e_view_details').hide();
							$('#e_customerName').val('');
				        	$('#e_duedate').val('');
				        	
				        	$('#view_ele').show();
				        	$('#ele').hide();
						}else if(service=='INSURANCE'){
							$("#policyNumber").val('');
							$("#premiumAmount").val('');
							$("#i_ad1").val('');
							
							$('.i_view_details').hide();
							$('#i_customerName').val('');
				        	$('#i_duedate').val('');
				        	
				        	$('#view_insurance').show();
				        	$('#insurance').hide();
						}else if(service=='LANDLINE'){
							$("#landlineNo").val('');
							$("#landlineAmount").val('');
							$("#l_ad1").val('');
							
							$('.l_view_details').hide();
							$('#l_customerName').val('');
				        	$('#l_duedate').val('');
				        	
				        	$('#view_land').show();
				        	$('#land').hide();
						}else if(service=='BROADBAND'){
							$("#broadbandNo").val('');
							$("#broadbandAmount").val('');
							
							$('.b_view_details').hide();
							$('#b_customerName').val('');
				        	$('#b_duedate').val('');
				        	
				        	$('#view_broadband').show();
				        	$('#broadband').hide();
							
						}else if(service=='WATER'){
							$("#waterNo").val('');
							$("#waterAmount").val('');
							$("#w_ad1").val('');
							$("#w_ad2").val('');
							
							$('.w_view_details').hide();
							$('#w_customerName').val('');
				        	$('#w_duedate').val('');
				        	
				        	$('#view_water').show();
				        	$('#water').hide();
							
						}else if(service=='GAS'){
							$("#gasNo").val('');
							$("#gasAmount").val('');
							$("#g_ad1").val('');
							
							$('.g_view_details').hide();
							$('#g_customerName').val('');
				        	$('#g_duedate').val('');
				        	
				        	$('#view_gas').show();
				        	$('#gas').hide();
						}
					}
					
					$('.err_msg').text(message);
					$('.err_msg').show();
					
				},
	
			 	failure: function (response) {
			 		$('#resultLoading').hide();
			 		var message=response.Description;
			 		$('.err_msg').text(message);
					$('.err_msg').show();
					
					if(service=='INSURANCE'){
		        		$('#ElecBillAmount').val('');
			        	$('#ElecContactNo').val('');
			        	$("#ElectAccountNo").val('')
			        	$("#BillUnit").val('')
			        	$("#CycleNo").val('')
					}
					else if(service=='ELECTRICITY'){
						$('#ElecBillAmount').val('');
			        	$('#ElecContactNo').val('');
			        	$("#ElectAccountNo").val('')
			        	$("#BillUnit").val('')
			        	$("#CycleNo").val('')
					}
			    }
				
				
			});
		}
		

	
	</script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			    
				
				$("select#ElectrictyOP").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#ElectrictyOP option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#ElectAccountNo').attr('placeholder','Consumer Number');
			        $('#ElectAccountNo').attr('maxlength','20');
			        $('#mseb1').hide();
		        	$('#mseb2').hide();
		        	$('.e_view_details').hide();
		        	$('#e_customerName').val('');
		        	$('#e_duedate').val('');
		        	$('#ElecBillAmount').val('');
		        	$('#ElectAccountNo').val('');
		        	$('#ElecContactNo').val('');
		        	$('#view_ele').hide();
		        	$('#ele').hide();
		        	$('#ElecBillAmount').removeAttr('readonly');
		        	
			        if(op=='UPPCL (RURAL) - UTTAR PRADESH' || op=='UPPCL (URBAN) - UTTAR PRADESH')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
					}
			        else if(op=='Ajmer Vidyut Vitran Nigam - RAJASTHAN' || op =='BESL - BHARATPUR' || op=='BkESL - BIKANER' || op=='Jaipur Vidyut Vitran Nigam - RAJASTHAN' 
			        		|| op=='Jodhpur Vidyut Vitran Nigam - RAJASTHAN' || op=='Kota Electricity Distribution - RAJASTHAN' || op=='TPADL - AJMER')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
						$('#ElectAccountNo').attr('placeholder','K Number');
					}
			        else if(op=='APDCL - ASSAM' || op=='CESC - WEST BENGAL' || op=='MEPDCL - MEGHALAYA' || op=='TSECL - TRIPURA')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
						$('#ElectAccountNo').attr('placeholder','Consumer ID');
					}
			        else if(op=='APEPDCL - ANDHRA PRADESH' || op=='APSPDCL - ANDHRA PRADESH')
					{
			        	$('#ElectAccountNo').attr('maxlength','20');
						$('#ElectAccountNo').attr('placeholder','Service Number');
					}
			        else if(op=='BESCOM - BENGALURU')
					{
			        	$('#ElectAccountNo').attr('maxlength','10');
						$('#ElectAccountNo').attr('placeholder','Customer ID / Account ID');
					}
			        else if(op=='CSPDCL - CHHATTISGARH' || op=='JUSCO - JAMSHEDPUR')
					{
			        	$('#ElectAccountNo').attr('maxlength','10');
						$('#ElectAccountNo').attr('placeholder','Business Partner Number');
					}
			        else if(op=='Daman and Diu Electricity' || op=='DHBVN - HARYANA' ||op=='PSPCL - PUNJAB' || op=='UHBVN - HARYANA')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
						$('#ElectAccountNo').attr('placeholder','Account Number');
					}
			        else if(op=='DNHPDCL - DADRA & NAGAR HAVELI')
					{
			        	$('#ElectAccountNo').attr('maxlength','20');
						$('#ElectAccountNo').attr('placeholder','Service Connection Number');
					}
			        else if(op=='NBPDCL - BIHAR' || op=='SBPDCL - BIHAR')
					{
			        	$('#ElectAccountNo').attr('maxlength','11');
						$('#ElectAccountNo').attr('placeholder','CA Number');
					}
			        else if(op=='APDCL - ASSAM')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
						$('#ElectAccountNo').attr('placeholder','K Number');
					}
					else if(op=='UPCL - UTTARAKHAND')
					{
						$('#ElectAccountNo').attr('maxlength','13');
						$('#ElectAccountNo').attr('placeholder','Service Connection Number');
					}
					else if(op=='Torrent Power')
					{
			        	$('#ElectAccountNo').attr('maxlength','6');
			        	$('#ElectAccountNo').attr('placeholder','Service Number');
			        	$('#mseb2').show();
			        	$('#BillUnit').attr('placeholder','Ahmedabad/Surat/Agra/Bhiwandi');
			        	$('#e_ad2_label').text('City Name Ahmedabad/Surat/Agra/Bhiwandi');
					}
					else if(op=='MSEDC - MAHARASHTRA')
					{
			        	$('#ElectAccountNo').attr('maxlength','12');
			        	
			        	$('#mseb2').show();
			        	$('#BillUnit').attr('placeholder','Billing Unit');
			        	$('#BillUnit').attr('maxlength','4');
			        	$('#e_ad2_label').text('Bill Unit');
					}else if(op=='Reliance Energy'){
						$('#ElectAccountNo').attr('maxlength','12');
						
						$('#mseb1').show();
			        	$('#CycleNo').attr('placeholder','Cycle Number');
			        	$('#CycleNo').attr('maxlength','4');
			        	$('#ElectAccountNo').attr('placeholder','Account Number');
					}
		
			        if(op!=''){
				        if(fetchStatus=='Yes'){
				        	$('#view_ele').show();
				        	$('#ele').hide();
				        }else{
				        	$('#view_ele').hide();
				        	$('#ele').show();
				        }
			        }
			    });
				
				$("select#billpayOp").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#billpayOp option:selected").val();
			        if(op!=''){
			        	$('#mobilebill').show();
			        }else{
			        	$('#mobilebill').hide();
			        }
			        
				});
				
				$("select#landlineOp").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#landlineOp option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#land').hide();
			        $('#view_land').hide();
			        $('#l_ad1_div').hide();
			        $('#l_ad2_div').hide();
			        
			        $('#landlineNo').val('')
		        	$('#landlineAmount').val('')
		        	$('#l_ad1').val('')
		        	
		        	$('.l_view_details').hide();
		        	$('#l_customerName').val('');
		        	$('#l_duedate').val('');
		        	$('#landlineAmount').removeAttr('readonly');
		        	
			        
			        if(op=='Airtel Landline')
					{
			        	$('#landlineNo').attr('maxlength','8');
			        	$('#landlineNo').attr('placeholder','Landline Number');
			        	$('#l_ad1_div').show();
			        	$('#l_ad1').attr('placeholder','STD Code');
			        	$('#l_ad1_label').text('STD Code');
					}
					else if(op=='BSNL' || op=='Reliance' || op=='Tata Docomo')
					{
						$('#landlineNo').attr('maxlength','8');
			        	$('#landlineNo').attr('placeholder','Landline Number');
			        	$('#l_ad1_div').show();
			        	$('#l_ad1').attr('placeholder','STD Code');
			        	$('#l_ad1_label').text('STD Code');
			        	
			        	$('#l_ad2_div').show();
			        	$('#l_ad2').attr('placeholder','STD Code');
			        	$('#l_ad2_label').text('STD Code');
			        	
					}else if(op=='MTNL - Delhi'){
						$('#landlineNo').attr('maxlength','8');
			        	$('#landlineNo').attr('placeholder','Landline Number');
			        	$('#l_ad1_div').show();
			        	$('#l_ad1').attr('placeholder','CA Number');
			        	$('#l_ad1_label').text('CA Number');
					}
					else if(op=='MTNL - Mumbai'){
						$('#landlineNo').attr('maxlength','8');
			        	$('#landlineNo').attr('placeholder','Landline Number');
			        	$('#l_ad1_div').show();
			        	$('#l_ad1').attr('placeholder','Account Number');
			        	$('#l_ad1_label').text('Account Number');
					}
		
			        if(op!=''){
			        	$('#land').show();
				        /* if(fetchStatus=='Yes'){
				        	$('#view_land').show();
				        	$('#land').hide();
				        }else{
				        	$('#view_land').hide();
				        	$('#land').show();
				        } */
			        }
			        
				});
				
				$("select#broadbandOp").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#broadbandOp option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#broadband').hide();
			        $('#view_broadband').hide();
			        $('#b_ad1_div').hide();
		        	$('.b_view_details').hide();
		        	$('#b_customerName').val('');
		        	$('#b_duedate').val('');
		        	
		        	$('#broadbandNo').val('')
		        	$('#broadbandAmount').val('')
		        	$('#b_ad1').val('')
		        	$('#broadbandAmount').removeAttr('readonly');
			        
			        if(op=='Connect Broadband')
					{
			        	$('#broadbandNo').attr('maxlength','11');
			        	$('#broadbandNo').attr('placeholder','Directory Number');
					}
					else if(op=='Hathway Broadband')
					{
						$('#broadbandNo').attr('maxlength','15');
			        	$('#broadbandNo').attr('placeholder','Customer ID');
			        	
			        	
					}
					else if(op=='Tikona Broadband'){
						$('#broadbandNo').attr('maxlength','10');
			        	$('#broadbandNo').attr('placeholder','Service ID');
					}
		
			        if(op!=''){
				        if(fetchStatus=='Yes'){
				        	$('#view_broadband').show();
				        	$('#broadband').hide();
				        }else{
				        	$('#view_broadband').hide();
				        	$('#broadband').show();
				        }
			        }
			        
				});
				
				$("select#gasOp").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#gasOp option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#view_gas').hide();
		        	$('#gas').hide();
			        $('#g_ad1_div').hide();
			        $('#gasNo').val('')
		        	$('#gasAmount').val('')
		        	$('#g_ad1').val('')
		        	$('.g_view_details').hide();
		        	$('#g_customerName').val('');
		        	$('#g_duedate').val('');
		        	$('#gasAmount').removeAttr('readonly');
			        
			        if(op=='Mahanagar Gas')
					{
			        	$('#gasNo').attr('maxlength','12');
			        	$('#gasNo').attr('placeholder','CA Number');
			        	$('#g_ad1_div').show();
			        	$('#g_ad1').attr('placeholder','Bill Group Number');
			        	$('#g_ad1_label').text('Bill Group Number');
					}
					else if(op=='Adani Gas - GUJARAT' || op=='Adani Gas - HARYANA' || op=='Gujarat Gas' || op=='Sabarmati Gas')
					{
						$('#gasNo').attr('maxlength','15');
			        	$('#gasNo').attr('placeholder','Customer ID');
			        	
					}
					else if(op=='Haryana City Gas'){
						$('#gasNo').attr('maxlength','10');
			        	$('#gasNo').attr('placeholder','CRN Number');
					}else if(op=='Indraprastha Gas'){
						$('#gasNo').attr('maxlength','10');
			        	$('#gasNo').attr('placeholder','BP Number');
					}else if(op=='Siti Energy'){
						$('#gasNo').attr('maxlength','9');
			        	$('#gasNo').attr('placeholder','ARN Number');
					}else if(op=='Tripura Natural Gas'){
						$('#gasNo').attr('maxlength','20');
			        	$('#gasNo').attr('placeholder','Consumer Number');
					}else if(op=='Unique Central Piped Gases'){
						$('#gasNo').attr('maxlength','8');
			        	$('#gasNo').attr('placeholder','Customer No');
					}else if(op=='Vadodara Gas'){
						$('#gasNo').attr('maxlength','7');
			        	$('#gasNo').attr('placeholder','Consumer Number');
					}
		
			        if(op!=''){
				        if(fetchStatus=='Yes'){
				        	$('#view_gas').show();
				        	$('#gas').hide();
				        }else{
				        	$('#view_gas').hide();
				        	$('#gas').show();
				        }
			        }
			        
				});
				
				$("select#waterOp").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#waterOp option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#w_ad1_div').hide();
			        $('#w_ad2_div').hide();
			        $('#view_water').hide();
		        	$('#water').hide();
		        	$('#waterNo').val('')
		        	$('#waterAmount').val('')
		        	$('#w_ad1').val('')
		        	$('#w_ad1').val('')
		        	$('.w_view_details').hide();
		        	$('#w_customerName').val('');
		        	$('#w_duedate').val('');
		        	
		        	$('#waterAmount').removeAttr('readonly');
			        
			        if(op=='Delhi Jal Board')
					{
						$('#waterNo').attr('maxlength','10');
			        	$('#waterNo').attr('placeholder','K No');
			        	
					}
					else if(op=='Municipal Corporation of Gurugram'){
						$('#waterNo').attr('maxlength','20');
			        	$('#waterNo').attr('placeholder','K No');
			        	$('#w_ad1_div').show();
			        	$('#w_ad2_div').show();
					}else if(op=='Urban Improvement Trust (UIT) - BHIWADI'){
						$('#waterNo').attr('maxlength','20');
			        	$('#waterNo').attr('placeholder','Customer ID');
					}else if(op=='Uttarakhand Jal Sansthan'){
						$('#waterNo').attr('maxlength','7');
			        	$('#waterNo').attr('placeholder','Consumer Number (Last 7 Digits)');
					}
		
			        if(op!=''){
				        if(fetchStatus=='Yes'){
				        	$('#view_water').show();
				        	$('#water').hide();
				        }else{
				        	$('#view_water').hide();
				        	$('#water').show();
				        }
			        }
			        
				});
				
				$("select#insurancePayOperator").change(function(){
					$('.err_msg').text('');
					$('.err_msg').hide();
			        var op = $("#insurancePayOperator option:selected").val();
			        var fetchStatus = $(this).find('option:selected').attr('viewStatus');
			        $('#insurance').hide();
			        $('#i_ad1_div').hide();
			        $('#i_ad2_div').hide();
			        $('#view_insurance').hide();
		        	$('#insurance').hide();
		        	$('#policyNumber').val('')
		        	$('#premiumAmount').val('')
		        	$('#i_ad1').val('')
		        	$('.i_view_details').hide();
		        	$('#i_customerName').val('');
		        	$('#i_duedate').val('');
		        	
		        	$('#premiumAmount').removeAttr('readonly');
			        
			        if(op=='ICICI Prudential Life Insurance'){
						$('#policyNumber').attr('maxlength','8');
			        	$('#policyNumber').attr('placeholder','Policy Number');
			        	
			        	$('#i_ad1_div').show();
			        	$('#i_ad1_label').text('Date of Birth');
					}else if(op=='Tata AIA Life Insurance'){
						$('#policyNumber').attr('maxlength','10');
			        	$('#policyNumber').attr('placeholder','Policy Number');
					}else if(op=='Tata AIG General Insurance'){
						$('#policyNumber').attr('maxlength','10');
			        	$('#policyNumber').attr('placeholder','Policy Number');
			        	
			        	$('#i_ad1_div').show();
			        	$('#i_ad1_label').text('Date of Birth');
					}
		
			        if(op!=''){
				        if(fetchStatus=='Yes'){
				        	$('#view_insurance').show();
				        	$('#insurance').hide();
				        }else{
				        	$('#view_insurance').hide();
				        	$('#insurance').show();
				        }
			        }
			        
				});
			});
	</script>
		
</head>
<body>

	<div class="site-wrapper">

		<!-- Header -->
		<%@ include file="../../header.jsp" %>
		<!-- Header / End -->


		<!-- Main -->
		<div class="main" role="main">


			<!-- Page Content -->
			<section class="page-content" style="padding: 2% 5% 5% 5%;background: #fff;border-top:2px solid #ccc;padding-bottom: 5% ">
				<div class="container" style="width:100%;" >				
					
					<div class="row">
	                    <div class="col-md-12">
	                        <div class="white-box">
	                            
	                            <div class="table-responsive">
                                
                                	<div class="col-md-12 form-group"><h3 class="box-title">Utility Payments</h3></div>
                                    <div class="col-md-6">
                                                                        
                                            <div class="tabs">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs">
                                                <li class="active"><a href="#billpay" data-toggle="tab"> Postpaid </a></li>
							                    <li><a href="#LANDLINE" data-toggle="tab"> Landline </a></li>
							                    <li><a href="#BROADBAND" data-toggle="tab"> Broadband </a></li>
							                    <li><a href="#electricity" data-toggle="tab"> Electricity </a></li>
							                    <li><a href="#GAS" data-toggle="tab"> Gas </a></li>
							                    <li><a href="#INSURANCE" data-toggle="tab"> Insurance </a></li>
							                    <li><a href="#WATER" data-toggle="tab"> Water </a></li>
                                            </ul>
                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <input type="hidden" id="REQUEST_ID" />
                                                <input type="hidden" id="reference_id" />
												<div class="tab-pane fade in active" id="billpay">
						                            <form name="MobileBillForm" method="post" action="#">
						                                <div class="form-group">
						                                    <select class="form-control" id="billpayOp" name="billpayOp">
						                                        <option value="" selected="selected">Select Postpaid Mobile Operator</option>
						                                        
						                                        <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${opertor.Service eq 'POSTPAID' }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
						                                       
						                                    </select>
						                                </div>
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Connection Number <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="ConnectionNo" name="ConnectionNo" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
						                                </div>    
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Bill Amount <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="postpaidAmount" name="postpaidAmount" onkeypress="return digitonly(this,event)" maxlength="6" class="form-control" placeholder="Enter Bill Amount">
						                                </div> 
						                                
						                                
						                                <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                            	<button type="button" id="view_mobilebill" style="display: none;" onclick="viewBill('POSTPAID')" class="btn btn-primary btn-inline">Validate</button>
						                                
						                                <button type="button" style="display: none;" id="mobilebill" onclick="payBill('POSTPAID')" class="btn btn-primary btn-inline">Proceed to pay</button>
						                            </form>
						                        </div>
												<input type="hidden" id="reference_id" />						                        
						                        <div class="tab-pane fade  " id="LANDLINE">
						                            <form name="MobileBillForm" method="post" action="#">
						                                <div class="form-group">
						                                    <select class="form-control" id="landlineOp" name="landlineOp">
						                                        <option value="" selected="selected">Select Landline Operator</option>
						                                        
						                                        <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${opertor.Service eq 'LANDLINE' }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
						                                       
						                                    </select>
						                                    
						                                </div>
						                                 
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Landline Number <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="landlineNo" name="landlineNo" onkeypress="return digitonly(this,event)" class="form-control" placeholder="Enter Your Landline Number">
						                                </div>
						                                
						                                <div class="form-group" id="l_ad1_div" style="display: none;">
						                                    <label class="pull-left" id="l_ad1_label">
						                                        STD Code <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="l_ad1" name="l_ad1" onkeypress="return digitonly(this,event)" maxlength="4" class="form-control" placeholder="STD Code">
						                                </div>
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Bill Amount <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="landlineAmount" name="landlineAmount" onkeypress="return digitonly(this,event)" maxlength="6" class="form-control" placeholder="Enter Bill Amount">
						                                </div>
						                                <div class="form-group" id="l_ad2_div" style="display: none;">
						                                    <label class="pull-left" id="l_ad2_label">
						                                        Connection Type <span class="required">*</span>
						                                    </label>
						                                    <select class="form-control" id="l_ad2" name="l_ad2">
						                                    	<option value="LLI" selected="selected">Individual</option>
						                                    	<option value="LLC">Corporate</option>
						                                    </select>                                                 
						                                </div>
						                                
						                                <div class="l_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="l_customerName" name="l_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="l_duedate" name="l_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
						                                
						                                <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                            	<button type="button" id="view_land" style="display: none;" onclick="viewBill('LANDLINE')" class="btn btn-primary btn-inline">View Bill Details</button>
						                                <button type="button" id="land" style="display: none;" onclick="payBill('LANDLINE')" class="btn btn-primary btn-inline">Proceed to pay</button>
						                            </form>
						                        </div>
						                        
						                        <div class="tab-pane fade  " id="BROADBAND">
						                            <form name="MobileBillForm" method="post" action="#">
						                                <div class="form-group">
						                                    <select class="form-control" id="broadbandOp" name="broadbandOp">
						                                        <option value="" selected="selected">Select Broadband Operator</option>
						                                        
						                                        <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${opertor.Service eq 'BROADBAND' }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
						                                       
						                                    </select>
						                                    
						                                </div>
						                                
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Connection Number <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="broadbandNo" name="broadbandNo" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
						                                </div>
						                                
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Bill Amount <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="broadbandAmount" name="broadbandAmount" onkeypress="return digitonly(this,event)" maxlength="6" class="form-control" placeholder="Enter Bill Amount">
						                                </div> 
						                                
						                                <div class="b_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="b_customerName" name="b_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="b_duedate" name="b_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
						                                
						                                <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                            	<button type="button" id="view_broadband" style="display: none;" onclick="viewBill('BROADBAND')" class="btn btn-primary btn-inline">View Bill Details</button>
						                                <button type="button" id="broadband" style="display: none;" onclick="payBill('BROADBAND')" class="btn btn-primary btn-inline">Proceed to pay</button>
						                            </form>
						                        </div>
						                        
						                        <div class="tab-pane fade  " id="GAS">
						                            <form name="MobileBillForm" method="post" action="#">
						                                <div class="form-group">
						                                    <select class="form-control" id="gasOp" name="gasOp">
						                                        <option value="" selected="selected">Select Gas Connection Operator</option>
						                                        
						                                        <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${opertor.Service eq 'GAS' }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
						                                       
						                                    </select>
						                                    
						                                </div>
						                                
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Connection Number <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="gasNo" name="gasNo" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
						                                </div>
						                                <div class="form-group" id="g_ad1_div" style="display: none;">
						                                    <label class="pull-left" id="g_ad1_label">
						                                        Bill Group <span class="required">*</span>
						                                    </label>
						                                    <input type="Text" class="form-control" id="g_ad1" name="g_ad1" onkeypress="return digitonly(this,event)" maxlength="14" class="form-control" placeholder="Enter Your Connection Number">
						                                </div>
						                                
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Bill Amount <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="gasAmount" name="gasAmount" onkeypress="return digitonly(this,event)" maxlength="6" class="form-control" placeholder="Enter Bill Amount">
						                                </div> 
						                                
						                                <div class="g_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="g_customerName" name="g_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="g_duedate" name="g_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
						                                
						                                <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                            	<button type="button" id="view_gas" style="display: none;" onclick="viewBill('GAS')" class="btn btn-primary btn-inline">View Bill Details</button>
						                                <button type="button" id="gas" style="display: none;" onclick="payBill('GAS')" class="btn btn-primary btn-inline">Proceed to pay</button>
						                            </form>
						                        </div>
						                        
						                        <div class="tab-pane fade  " id="WATER">
						                            <form name="MobileBillForm" method="post" action="#">
						                                <div class="form-group">
						                                    <select class="form-control" id="waterOp" name="waterOp">
						                                        <option value="" selected="selected">Select Water Connection Operator</option>
						                                        
						                                        <c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                        	<c:if test="${opertor.Service eq 'WATER' }">
						                                        		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                        	</c:if>
						                                        </c:forEach>
						                                       
						                                    </select>
						                                    
						                                </div>
						                                
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Connection Number <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="waterNo" name="waterNo"  maxlength="14" class="form-control" placeholder="K No">
						                                </div>
						                                <div class="form-group">
						                                    <label class="pull-left">
						                                        Bill Amount <span class="required">*</span>
						                                    </label>                                                 
						                                    <input type="Text" class="form-control" id="waterAmount" name="waterAmount" onkeypress="return digitonly(this,event)" maxlength="6" class="form-control" placeholder="Enter Bill Amount">
						                                </div> 
						                                <div class="form-group" id="w_ad1_div" style="display: none;">
						                                    <label class="pull-left" id="w_ad1_label">
						                                        Mobile Number <span class="required">*</span>
						                                    </label>
						                                    <input type="Text" class="form-control" id="w_ad1" name="w_ad1" onkeypress="return digitonly(this,event)" maxlength="10" class="form-control" placeholder="Enter Your Mobile Number">
						                                </div>
						                                <div class="form-group" id="w_ad2_div" style="display: none;">
						                                    <label class="pull-left" id="w_ad2_label">
						                                        Email Id <span class="required">*</span>
						                                    </label>
						                                    <input type="Text" class="form-control" id="w_ad2" name="w_ad2"  class="form-control" placeholder="Email Id">
						                                </div>
						                                
						                                <div class="w_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="w_customerName" name="e_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="w_duedate" name="e_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
						                                
						                                <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                            	<button type="button" id="view_water" style="display: none;" onclick="viewBill('WATER')" class="btn btn-primary btn-inline">View Bill Details</button>
						                                <button type="button" id="water" style="display: none;" onclick="payBill('WATER')" class="btn btn-primary btn-inline">Proceed to pay</button>
						                            </form>
						                        </div>
						                                                
						                                                
						                         <div class="tab-pane fade" id="INSURANCE">
						                             <form name="InsuranceForm" method="post" action="utilityService.action?param=InsurencePay">
						                                 <div class="form-group">
						                                 	<input type="hidden" name="service" id="service" value="INSURANCE" />
						                                     <select class="form-control" name="insurancePayOperator" id="insurancePayOperator">
						                                     	<option value="" selected="selected">Select Insurance Provider</option>
						                                         
						                                        	<c:forEach var="opertor" items="${sessionScope.opertorList}">
						                                         		<c:if test="${opertor.Service eq 'INSURANCE' }">
						                                         			<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
						                                         		</c:if>
						                                         	</c:forEach>
						                                     </select>
						                                 </div>
						                                 <div class="form-group">
						                                     <label class="pull-left">
						                                         Policy Number <span class="required">*</span>
						                                     </label>                                                 
						                                     <input type="Text" class="form-control" name="policyNumber" id="policyNumber" placeholder="Enter Policy Number">
						                                 </div>
						                                 <div class="form-group " id="i_ad1_div">
						                                     <label class="pull-left" id="i_ad1_label">
						                                         Date of Birth <span class="required">*</span>
						                                     </label>                                                 
						                                     <input type="text" class="form-control" name="i_ad1" id="i_ad1" placeholder="Enter Date of Birth in DD-MM-YYYY Format">
						                                 </div>
						                                 <div class="form-group">
						                                     <label class="pull-left">
						                                         Premium Amount <span class="required">*</span>
						                                     </label>                                                 
						                                     <input type="Text" name="premiumAmount" id="premiumAmount" class="form-control" placeholder="Enter Premium Amount">
						                                 </div>
						                                 
						                                 <div class="i_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="i_customerName" name="i_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="i_duedate" name="i_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
						                                 
						                                 <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
						                            	</a>
						                                 <button type="button" style="display: none;" id="view_insurance" onclick="viewBill('INSURANCE')" class="btn btn-primary btn-inline">View Bill Details</button>
						                                 <button type="button" style="display: none;" id="insurance" onclick="payBill('INSURANCE')" class="btn btn-primary btn-inline">Proceed to Pay</button>
						                             </form>
						                         </div>
							                     <div class="tab-pane fade" id="electricity">
							                         <form name="ElectricityForm" method="post" action="#">
							                             <div class="form-group">
							                                 <select class="form-control" id="ElectrictyOP" name="ElectrictyOP"  >
							                                     <option selected="selected" value="">Select Electricity Provider</option>
							                                     <c:forEach var="opertor" items="${sessionScope.opertorList}">
							                                     	<c:if test="${opertor.Service eq 'ELECTRICITY' }">
							                                     		<option value="${opertor.OperatorName }" opCode="${opertor.OpCode}" viewStatus="${opertor.BillFetch}">${opertor.DisplayName }</option>
							                                     	</c:if>
							                                     </c:forEach> 
							                                 </select>
							                             </div>
							                             <div class="form-group">
							                                 <label class="pull-left">
							                                     Account Number <span class="required">*</span>
							                                 </label>                                                 
							                                 <input type="Text" id="ElectAccountNo" name="ElectAccountNo" maxlength="20" class="form-control" placeholder="Enter Your Account Number">
							                             </div>
							                             <div class="form-group" id="mseb1" style="display: none;">
							                                <label class="pull-left" id="e_ad1_label">
							                                     Cycle Number <span class="required">*</span>
							                                 </label>
							                                <input name="CycleNo" id="CycleNo" maxlength="9"  type="Text" class="form-control" placeholder="Enter Cycle No. Eg. (4631)">
							                             </div> 
							                             <div class="form-group" id="mseb2" style="display: none;">
							                                 <label class="pull-left" id="e_ad2_label">
							                                     Bill Unit <span class="required">*</span>
							                                 </label>
							                                 <input name="BillUnit" id="BillUnit" maxlength="12"  type="Text" class="form-control" placeholder="Enter Bill Unit Eg. (02)">
							                             </div>
							                             <div class="form-group">
							                                 <label class="pull-left">
							                                     Bill Amount <span class="required">*</span>
							                                 </label>                                                 
							                                 <input type="Text" id="ElecBillAmount" name="ElecBillAmount" maxlength="6" onkeypress="return digitonly(this,event)" class="form-control" placeholder="Enter Bill Amount">
							                             </div>
							                             <div class="form-group">
							                                 <label class="pull-left">
							                                     Contact Number <span class="required">*</span>
							                                 </label>                                                 
							                                 <input type="Text" maxlength="12" id="ElecContactNo" name="ElecContactNo" class="form-control" placeholder="Enter Your Contact Number">
							                             </div>
							                             
							                             <div class="e_view_details" style="display: none;">
							                            	<div class="form-group">
								                                <label class="pull-left">
								                                    Customer Name <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="e_customerName" name="e_customerName" class="form-control" placeholder="Enter Your Contact Number">
								                            </div>
								                            
								                            <div class="form-group">
								                                <label class="pull-left">
								                                    Due Date <span class="required">*</span>
								                                </label>                                                 
								                                <input type="Text" readonly="readonly" id="e_duedate" name="e_duedate" class="form-control" placeholder="Enter Your Contact Number">
								                            </div> 
								                            
								                       	</div>
							                             
							                             <a href="utilityService.action?param=UtilityPage"><button type="button" class="btn btn-primary btn-inline">Cancel</button>
							                         	</a>
							                             <button type="button" id="view_ele" style="display: none;" onclick="viewBill('ELECTRICITY')" class="btn btn-primary btn-inline">View Bill Details</button>
							                         	<button type="button" id="ele" style="display: none;" onclick="payBill('ELECTRICITY')" class="btn btn-primary btn-inline">Proceed to Pay Bill</button>
							                         
							                         </form>
							                     </div>
						         		
						         		
						         				<div class="err_msg">
						         			
						         				</div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6" align="right">
                                    
                                        
                                    	<div class="cta-inner">
                                            <div class="cta-txt">
                                                
                                                <h2>Now pay you bill using Bharat Bill Payment System</h2>
                                           		<img src="images/banner-bbps-img.png" alt="BBPS Logo" />
                                           		<br>
                                           		<h3>AEPS Coming Soon</h3>
                                            </div>												
                                        </div>
                                    </div>
                                </div>
                        </div>
					</div>
				</div>
                </div>
         	</section>
         	
         	
	<!-- Page Content / End -->
    
			<!-- Footer -->
			<%@ include file="../../footer.jsp" %>
			<!-- Footer / End -->
			
		</div>
		<!-- Main / End -->
	</div>
	
	
	
	
	
	<!-- Javascript Files
	================================================== -->
	<script src="vendor/jquery-migrate-1.2.1.min.js"></script>
	<script src="vendor/bootstrap.js"></script>
	<script src="vendor/jquery.flexnav.min.js"></script>
	<script src="vendor/jquery.hoverIntent.minified.js"></script>
	<script src="vendor/jquery.flickrfeed.js"></script>
	<script src="vendor/magnific-popup/jquery.magnific-popup.js"></script>
	<script src="vendor/owl-carousel/owl.carousel.min.js"></script>
	<script src="vendor/jquery.fitvids.js"></script>
	<script src="vendor/jquery.appear.js"></script>
	<script src="vendor/jquery.stellar.min.js"></script>
	<script src="vendor/jquery.countTo.js"></script>

	<!-- Newsletter Form -->
	<script src="vendor/jquery.validate.js"></script>
	<script src="js/newsletter.js"></script>

	<script src="js/custom.js"></script>


	
</body>

</html>