package com.candidatemobileapi.service;

import java.util.List;

import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;

public interface CandidateMobileApiService {
	
	public List<ConsentResponse> getConditions(String country,String brand,String language);
	
	public boolean postCheckUnicity(String email,String brand);

}
