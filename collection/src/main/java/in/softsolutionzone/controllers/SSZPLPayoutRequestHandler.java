package in.softsolutionzone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.softsolutionzone.exception.BadRequestException;
import in.softsolutionzone.payout.model.PayoutRequestModel;
import in.softsolutionzone.services.PayoutServices;
import io.swagger.annotations.Api;

@RestController
@Api(value="SSZPL Payout Request Handler",description="SSZPL Payout Request Handler")
@RequestMapping("/payouts")
public class SSZPLPayoutRequestHandler {
	
	@Autowired
	PayoutServices payoutServices;

	@PostMapping("/direct")
	public ResponseEntity<?> payout(@RequestBody PayoutRequestModel payoutRequestModel) throws BadRequestException{
		
		return payoutServices.payouts(payoutRequestModel);	
	}
	
	@PostMapping("/registration")
	public ResponseEntity<?> registration() throws BadRequestException{
		
		return payoutServices.registration();
	}
	@GetMapping("/registration-status/{UNIQUEID}")
	public ResponseEntity<?> registrationStatus(@PathVariable("UNIQUEID") String UNIQUEID) throws BadRequestException{
		
		return payoutServices.registrationStatus(UNIQUEID);
	}
	
	@GetMapping("/transaction-status/{UNIQUEID}")
	public ResponseEntity<?> transactionStatus(@PathVariable("UNIQUEID") String UNIQUEID) throws BadRequestException{
		
		return payoutServices.transactionStatus(UNIQUEID);
	}
	
	@GetMapping("/check-balance")
	public ResponseEntity<?> getBalance() throws BadRequestException{
		
		return payoutServices.getBalance();
	}
	
	@GetMapping("/statement")
	public ResponseEntity<?> getStatement(@RequestParam("fromDate") String fromDate,@RequestParam("toDate") String toDate) throws BadRequestException{
		
		return payoutServices.getStatement(fromDate, toDate);
	}
}
