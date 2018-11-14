package com.candidatemobileapi.model;

public class ConsentType {

	private String type;
	private String launguage;
	private String consentBody;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLaunguage() {
		return launguage;
	}

	public void setLaunguage(String launguage) {
		this.launguage = launguage;
	}

	public String getConsentBody() {
		return consentBody;
	}

	public void setConsentBody(String consentBody) {
		this.consentBody = consentBody;
	}

	@Override
	public String toString() {
		return "ConsentType [type=" + type + ", launguage=" + launguage
				+ ", consentBody=" + consentBody + "]";
	}

	

	

}
