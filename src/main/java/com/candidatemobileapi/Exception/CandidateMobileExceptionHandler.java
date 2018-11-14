package com.candidatemobileapi.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.slf4j.Logger;

import com.candidatemobileapi.util.CandidateMobileApiConstants;

@ControllerAdvice
public class CandidateMobileExceptionHandler {
	
	@Autowired
	 Logger logger;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(exception.getMessage());
		error.setError_code("Not Found");

		return error;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {
        logger.info("Exception occured  - "+exception.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidateMobileApiConstants.BAD_REQUEST_ERROR);
		error.setError_code(CandidateMobileApiConstants.BAD_REQUEST);

		return error;
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		   logger.info("Exception occured  handleMethodArgumentNotValid- "+ex.getMessage());
		ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidateMobileApiConstants.BAD_REQUEST_ERROR);
		error.setError_code(status.BAD_REQUEST.name());

		return error;
	}


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleException(MethodArgumentNotValidException exception) {

    	 logger.info("Exception occured  handleMethodArgumentNotValid- "+exception.getMessage());
        ExceptionResponse error = new ExceptionResponse();
		error.setError_message(CandidateMobileApiConstants.BAD_REQUEST_ERROR);
		error.setError_code(CandidateMobileApiConstants.BAD_REQUEST);

		return error;
    }
    
    @ExceptionHandler(ConsentTypeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse>  handleConsentTypeNotFoundException(ConsentTypeNotFoundException ex) {
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(ex.getMessage());
		error.setError_code(CandidateMobileApiConstants.GET_404_RESPONSE);

		return new ResponseEntity(error, HttpStatus.NOT_FOUND);	
    }
    
    
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse>  handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
    	logger.info("Email Already exists");
    	ExceptionResponse error = new ExceptionResponse();
		error.setError_message(ex.getMessage());
		error.setError_code(CandidateMobileApiConstants.EMAIL_EXISTS);

		return new ResponseEntity(error, HttpStatus.NOT_FOUND);	
    }

}
