package com.candidatemobileapi.util;
/**
* @author riddhi.dilip.vyas
* @date 10/17/2018
*
* @group Accounts
* @group-content 
*
* @description: QueryConstants contains all the queries and column names  
*/
public class QueryConstants {

	public static final String  GET_CONDITIONS = "select sn.name,sn.tr1__consent_email_body_request__c from salesforce.tr1__consent_type__c as sn where sn.tr1__language__c =? ";
	
	public static final String COLUMN_CONSENT_EMAIL_BODY = "tr1__consent_email_body_request__c";
	
	public static final String COLUMN_CONSENT_NAME = "name";
	
	public static final String CHECK_EMAIL_UNICITY = "select Count(Email) as count from salesforce.Contact  where Email = ?";
}
