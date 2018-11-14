package com.candidatemobileapi.service;

import java.util.List;

import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;

public interface CandidateMobileApiService {
	
	public List<ConsentResponse> getConditions(String type,String language);
	
	public boolean postCheckUnicity(String email);

}
