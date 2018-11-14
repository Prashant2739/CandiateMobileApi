package com.candidatemobileapi.util;

public class QueryConstants {

	public static final String  GET_CONDITIONS = "select sn.tr1__consent_email_body_request__c from salesforce.tr1__consent_type__c as sn where UPPER(sn.tr1__language__c) like UPPER('?%') and UPPER(sn.name) like UPPER('?%')";
	
	public static final String COLUMN_CONSENT_EMAIL_BODY = "tr1__consent_email_body_request__c";
	
	public static final String CHECK_EMAIL_UNICITY = "select email from salesforce.contact where email = ?";
}
