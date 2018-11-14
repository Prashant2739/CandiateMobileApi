package com.candidatemobileapi.dao;
/**
* @author riddhi.dilip.vyas
* @date 10/12/2018
*
* @group Accounts
* @group-content 
*
* @description: CandidateMobileApiDao is an interface 
*/
import java.util.List;

import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;

public interface CandidateMobileApiDao {

	public List<ConsentResponse> getConditions(String type,String language);
	
	public boolean postCheckUnicity(String email);
}
