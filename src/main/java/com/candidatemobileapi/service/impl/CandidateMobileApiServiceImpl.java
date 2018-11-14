package com.candidatemobileapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidatemobileapi.dao.CandidateMobileApiDao;
import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;
import com.candidatemobileapi.service.CandidateMobileApiService;

@Service
public class CandidateMobileApiServiceImpl implements CandidateMobileApiService{

	@Autowired 
	CandidateMobileApiDao candidateMobileApiDao;
	
	public List<ConsentResponse> getConditions(String country,String brand,String language) {
		
		return candidateMobileApiDao.getConditions( country, brand, language);
	}
	
	public boolean postCheckUnicity(String email,String brand){
		
		return candidateMobileApiDao.postCheckUnicity(email, brand);
	}

}
