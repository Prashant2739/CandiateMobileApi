package com.candidatemobileapi.controller;

/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidateMobileApiController is a controller class which maps the uri to the appropriate handler methods
*/
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidatemobileapi.Exception.ConsentTypeNotFoundException;
import com.candidatemobileapi.Exception.EmailAlreadyExistsException;
import com.candidatemobileapi.model.CheckUnicityRequest;
import com.candidatemobileapi.model.CheckUnicityResponse;
import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.service.CandidateMobileApiService;
import com.candidatemobileapi.util.CandidateMobileApiConstants;



@RestController
public class CandidateMobileApiController {

	@Autowired
	CandidateMobileApiService candidateMobileApiService;
	
	 @Autowired
	 Logger logger;
	
   /* @description Returns Consent Type Description based on the the type and language provided
    * @param type  This is the consent type - marketing/terms and conditions
    * @param language This parameter searches the consent type for a particular language en/fr
    * @return List<ConsentType> based on the parameters selected
    */
	@ApiOperation(value = CandidateMobileApiConstants.GET_CONDITIONS_DESC, response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = CandidateMobileApiConstants.GET_CONDITIONS_200_RESPONSE),
	        @ApiResponse(code = 400, message = CandidateMobileApiConstants.GET_400_RESPONSE),
	        @ApiResponse(code = 404, message = CandidateMobileApiConstants.GET_404_RESPONSE),
	        @ApiResponse(code = 503, message = CandidateMobileApiConstants.GET_503_RESPONSE),
	        @ApiResponse(code = 500, message = CandidateMobileApiConstants.GET_500_RESPONSE)
	}
	)
	@RequestMapping(value="/v1.0/conditions/{country}/{brand}/{language}",method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getConsentType(@Valid @PathVariable(value="country") String country,@PathVariable(value="brand") String brand,@PathVariable(value="language") String language){
		
		logger.info("GetConsentType started");
		List<ConsentResponse> listConsentType = candidateMobileApiService.getConditions(country,brand,language);
		
		
		if (listConsentType ==null || listConsentType.isEmpty()) {
			logger.info("No Records found");
		throw new ConsentTypeNotFoundException(CandidateMobileApiConstants.CONSENT_TYPE_NOT_FOUND);
		}
		
		return new ResponseEntity<List<ConsentResponse>>(listConsentType, HttpStatus.OK);
	}
	
	 /* @description This method searches if the candidate's email id is already present
	    * @param email  Email Id of the candidate
	    * @return String Email Exists/ Doesn't exists 
	    */
	@ApiOperation(value = CandidateMobileApiConstants.CHECK_UNICITY_DESC, response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = CandidateMobileApiConstants.CHECK_UNICITY_200_RESPONSE),
	        @ApiResponse(code = 400, message = CandidateMobileApiConstants.GET_400_RESPONSE),
	        @ApiResponse(code = 404, message = CandidateMobileApiConstants.GET_404_RESPONSE),
	        @ApiResponse(code = 503, message = CandidateMobileApiConstants.GET_503_RESPONSE),
	        @ApiResponse(code = 500, message = CandidateMobileApiConstants.GET_500_RESPONSE)
	}
	)
	@RequestMapping(value="/v1.0/email/checkUnicity/{brand}",method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> checkEmailUnicity(@Valid @RequestBody CheckUnicityRequest checkUnicityRequest,@PathVariable(value="brand") String brand){
		
		logger.info("CheckEmailUnicity Started");
		boolean emailUnicityFlag = candidateMobileApiService.postCheckUnicity(checkUnicityRequest.getEmail(),brand);
		logger.info("emailUnicityFlag in the controller.");
		
		if(!emailUnicityFlag){
			logger.info("Email already exists.Throwing custom EmailAlreadyExistsException");
			throw new EmailAlreadyExistsException(CandidateMobileApiConstants.EMAIL_EXISTS_MESSAGE);
		}
		CheckUnicityResponse unicityResponseOnj = new CheckUnicityResponse();
		unicityResponseOnj.setSuccess(emailUnicityFlag);
		
		return new ResponseEntity<CheckUnicityResponse>(unicityResponseOnj, HttpStatus.OK);
		
		
		
	}
}
