package com.candidatemobileapi.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.candidatemobileapi.dao.CandidateMobileApiDao;
import com.candidatemobileapi.model.ConsentResponse;
import com.candidatemobileapi.model.ConsentType;
import com.candidatemobileapi.util.QueryConstants;

@Repository
public class CandidateMobileApiDaoImpl extends JdbcDaoSupport implements
		CandidateMobileApiDao {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/*public List<ConsentType> getConditions(String type, String language) {

		String sql = QueryConstants.GET_CONDITIONS;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,new Object[]{type,language});

		List<ConsentType> result = new ArrayList<ConsentType>();
		if (null != rows && rows.size() > 0) {
			for (Map<String, Object> row : rows) {
				if(null != row){
					ConsentType consentType = new ConsentType();
					consentType.setConsetEmailBody(null == row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY) ? "": (String) row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY));
					result.add(consentType);
				}
				
			}
		}
     System.out.println("List of Consent Type - "+result.toString());
		return result;
	}*/
	
	public boolean postCheckUnicity(String email){
		boolean checkUnicityFlag = false;
		
		String sql = QueryConstants.CHECK_EMAIL_UNICITY;
		/*List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql, new Object[]{email});
		if (null != rows && rows.size() > 0) {
			checkUnicityFlag= true;
		}*/
		
		List<String> tempEmailList = new ArrayList();
		tempEmailList.add("riddhi.vyas@accenture.com");
		tempEmailList.add("abc@accenture.com");
		tempEmailList.add("temp@accenture.com");
		
		for(String each:tempEmailList){
			if(each.equals(email)){
				return true;
			}
		}
		
		return false;
	}
	
	public List<ConsentResponse> getConditions(String type, String language) {

		System.out.println("type - "+type+"  Languague- "+language);
		List<ConsentResponse> finalResult = new ArrayList<ConsentResponse>();
		List<ConsentType> result = new ArrayList<ConsentType>();
		
		ConsentType consentType1 = new ConsentType();
		ConsentType consentType2 = new ConsentType();
		
		consentType1.setType("Marketing");
		consentType1.setLaunguage("en");
		consentType1.setConsentBody("Marketing Consent Type Selected.Body will comprise of mrketing info");
			
		consentType2.setType("T & C");
		consentType2.setLaunguage("en");
		consentType2.setConsentBody("Terms and Conditions sleceted. Body will comprise of terms and condirions for the Candidate Mobile App");
				
		result.add(consentType1);
		result.add(consentType2);
		
		for(ConsentType eachType:result){
			System.out.println("EahcType - >"+eachType.toString());
			if(eachType.getType().equalsIgnoreCase(type) && eachType.getLaunguage().equalsIgnoreCase(language)){
				//finalResult.add(eachType);
				ConsentResponse response = new ConsentResponse();
				response.setReponse(eachType.getConsentBody());
				finalResult.add(response);
			}
		}
		
		System.out.println("List of Consent Type - "+result.toString());
		return finalResult;
	}
}
