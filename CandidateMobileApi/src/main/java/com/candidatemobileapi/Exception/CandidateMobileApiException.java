package com.candidatemobileapi.Exception;

public class CandidateMobileApiException {
	
	private String errorMessage;

    public CandidateMobileApiException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
