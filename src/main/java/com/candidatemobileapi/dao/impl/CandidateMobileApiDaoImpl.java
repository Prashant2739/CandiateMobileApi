package com.candidatemobileapi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.candidatemobileapi.dao.CandidateMobileApiDao;
import com.candidatemobileapi.model.ConsentResponse;
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

	public List<ConsentResponse> getConditions(String country,String brand,String language) {

		String sql = QueryConstants.GET_CONDITIONS;
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql,new Object[]{language});
		List<ConsentResponse> result = new ArrayList<ConsentResponse>();
		if (null != rows && rows.size() > 0) {
			for (Map<String, Object> row : rows) {
				if(null != row){
					ConsentResponse consentType = new ConsentResponse();
					consentType.setContent((null == row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY) ? "": (String) row.get(QueryConstants.COLUMN_CONSENT_EMAIL_BODY)));
					consentType.setTitle((null == row.get(QueryConstants.COLUMN_CONSENT_NAME)? "": (String) row.get(QueryConstants.COLUMN_CONSENT_NAME)));
					result.add(consentType);
				}
				
			}
		}
     logger.info("List of Consent Type - "+result.toString());
		return result;
	}
	
	public boolean postCheckUnicity(String email,String brand){
		
		String sql = QueryConstants.CHECK_EMAIL_UNICITY;
		Integer count = getJdbcTemplate().queryForObject(
                sql, new Object[] { email }, Integer.class);

              if(count>0){
            	  logger.info("Email already exists");
            	  return false;
              }
              logger.info("Email does not exists");        
		return true;
	}
	
	/*public List<ConsentResponse> getConditions(String country,String brand,String language) {

		System.out.println("country - "+country+"  Languague- "+language);
		List<ConsentResponse> finalResult = new ArrayList<ConsentResponse>();
		List<ConsentType> result = new ArrayList<ConsentType>();
		
		ConsentType consentType1 = new ConsentType();
		ConsentType consentType2 = new ConsentType();
		
		consentType1.setBrand("Adecco");
		consentType1.setLaunguage("en");
		consentType1.setCountry("USA");
			
		consentType2.setBrand("Adecco");
		consentType2.setLaunguage("fr");
		consentType2.setCountry("France");
		
		result.add(consentType1);
		result.add(consentType2);
		
		for(ConsentType eachType:result){
			System.out.println("EahcType - >"+eachType.toString());
			if(eachType.getCountry().equalsIgnoreCase(country) && eachType.getLaunguage().equalsIgnoreCase(language)&& eachType.getBrand().equalsIgnoreCase(brand)){
				//finalResult.add(eachType);
				ConsentResponse response = new ConsentResponse();
				response.setContent("During your registration bla bla bla");
				response.setTitle("Marketing");
				finalResult.add(response);
			}
		}
		
		System.out.println("List of Consent Type - "+result.toString());
		return finalResult;
	}*/
}
