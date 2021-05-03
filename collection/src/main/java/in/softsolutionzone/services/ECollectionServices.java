package in.softsolutionzone.services;

import javax.servlet.ServletContext;

import in.softsolutionzone.model.ICICICollectionEncryptedResponseModel;
import in.softsolutionzone.model.ICICICollectionRequestModel;

public interface ECollectionServices {
	
	ICICICollectionEncryptedResponseModel ecollectionRequest(ICICICollectionRequestModel request, ServletContext context);
}
