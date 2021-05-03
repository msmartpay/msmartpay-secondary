package in.softsolutionzone.controllers;

import javax.servlet.ServletContext;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.softsolutionzone.exception.BadRequestException;
import in.softsolutionzone.model.ICICICollectionEncryptedResponseModel;
import in.softsolutionzone.model.ICICICollectionRequestModel;
import in.softsolutionzone.services.ECollectionServices;
import in.softsolutionzone.util.CustomResponse;
import io.swagger.annotations.Api;


@RestController
@Api(value="ICICI Collection Request Handler",description="ICICI Collection Request Handler")
@RequestMapping("/icici")
public class ICICICollectionRequestHandler {

	private static final Logger logger = LoggerFactory.getLogger(ICICICollectionRequestHandler.class);
	
	@Autowired
	ECollectionServices eCollectionServices;
	
	@Autowired
	ServletContext context;
	
	@PostMapping("/collection")
	public ICICICollectionEncryptedResponseModel validateUser(@RequestBody ICICICollectionRequestModel request) throws BadRequestException{
		logger.debug("Balance request received");
		return eCollectionServices.ecollectionRequest(request,context);
		
	}
	
	@PostMapping("/cibcallback")
	public ResponseEntity<?> cibcallback(JSONObject request) throws BadRequestException{
		logger.debug("cibcallback request received : "+request);
		return new ResponseEntity<>(new CustomResponse.CustomResponseBuilder<>()
				.setStatus(0)
				.build(),HttpStatus.OK);	
	}
}
