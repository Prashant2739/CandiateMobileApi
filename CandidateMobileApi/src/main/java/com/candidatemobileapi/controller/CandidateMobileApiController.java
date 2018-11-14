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
import java.util.List;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.candidatemobileapi.Exception.CandidateMobileApiException;
import com.candidatemobileapi.model.CheckUnicityResponse;
import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;
import com.candidatemobileapi.service.CandidateMobileApiService;
import com.candidatemobileapi.util.CandidateMobileApiConstants;



@RestController
public class CandidateMobileApiController {

	@Autowired
	CandidateMobileApiService candidateMobileApiService;
	
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
	@RequestMapping(value="/GetConsentType",method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getConsentType(@RequestParam(value="type",required = true) String type,@RequestParam(value="language",required = true) String language){
		
		List<ConsentResponse> listConsentType = candidateMobileApiService.getConditions(type,language);
		
		
		if (listConsentType ==null || listConsentType.isEmpty()) {
			System.out.println("No Records found");
		 return new ResponseEntity(new CandidateMobileApiException(CandidateMobileApiConstants.GET_CONDITIONS_NOT_FOUND),
					HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<ConsentResponse>>(listConsentType, HttpStatus.OK);
	}
	
	 /* @description This method searches if the candidate's email id is already present
	    * @param email  Email Id of the candidate
	    * @return String Email Exists/ Doesn't exists 
	    */
	@ApiOperation(value = CandidateMobileApiConstants.GET_CONDITIONS_DESC, response = ResponseEntity.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = CandidateMobileApiConstants.CHECK_UNICITY_200_RESPONSE),
	        @ApiResponse(code = 400, message = CandidateMobileApiConstants.GET_400_RESPONSE),
	        @ApiResponse(code = 404, message = CandidateMobileApiConstants.GET_404_RESPONSE),
	        @ApiResponse(code = 503, message = CandidateMobileApiConstants.GET_503_RESPONSE),
	        @ApiResponse(code = 500, message = CandidateMobileApiConstants.GET_500_RESPONSE)
	}
	)
	@RequestMapping(value="/CheckEmailUnicity",method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> checkEmailUnicity(@RequestParam(value="email",required = true) String email){
		
		System.out.println("checkEmailUnicity Started");
		boolean emailUnicityFlag = candidateMobileApiService.postCheckUnicity(email);
		System.out.println("emailUnicityFlag in the controller.");
		CheckUnicityResponse unicityResponseOnj = new CheckUnicityResponse();
		
		if(!emailUnicityFlag){
			System.out.println("Returning 200 status.Email doesnt not exists");
			unicityResponseOnj.setStatus(200);
			unicityResponseOnj.setDescription(CandidateMobileApiConstants.CHECK_UNICITY_200_RESPONSE);
			return new ResponseEntity<CheckUnicityResponse>(unicityResponseOnj, HttpStatus.OK);
		}else{
			System.out.println("Returning 404. Email Exists");
			return new ResponseEntity(new CandidateMobileApiException(CandidateMobileApiConstants.CHECK_UNICITY_EXISTS_404),
					HttpStatus.NOT_FOUND);
		}
		
		
		
	}
}
