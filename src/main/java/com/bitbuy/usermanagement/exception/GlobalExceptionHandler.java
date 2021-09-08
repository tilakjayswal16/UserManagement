package com.bitbuy.usermanagement.exception;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = { ValidationException.class,DuplicateKeyException.class })
    public ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
 
    	String message = AppConstant.VALIDATION_ERROR;
    	if(ex.getErrorMessage()!=null) {
    		message = ex.getErrorMessage();
    	}
       	ApiError error= new ApiError(HttpStatus.BAD_REQUEST.value(),message);
    	logger.error("Exception : \n");
    	ex.printStackTrace();
        return new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiError> handleException(Exception ex) {
    	
       	ApiError error= new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),AppConstant.SERVER_ERROR);
    	logger.error("Exception : \n");
    	ex.printStackTrace();
        return new ResponseEntity<ApiError>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = { NoSuchElementException.class })
    public ResponseEntity<ApiError> handleNoSuchElementException(Exception ex) {
    	
       	ApiError error= new ApiError(HttpStatus.NOT_FOUND.value(),AppConstant.NOT_AVAILABLE);
    	logger.error("Exception : \n");
    	ex.printStackTrace();
        return new ResponseEntity<ApiError>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { BadCredentialsException.class})
    public ResponseEntity<ApiError> handleBadCredentialsException(BadCredentialsException ex) {

        String message = AppConstant.VALIDATION_ERROR;
        if(ex.getMessage()!=null) {
            message = ex.getMessage();
        }
        ApiError error= new ApiError(HttpStatus.UNAUTHORIZED.value(),message);
        logger.error("Exception : \n");
        ex.printStackTrace();
        return new ResponseEntity<ApiError>(error,HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	
    	ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST.value(),AppConstant.VALIDATION_ERROR);
    	
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        if(!errors.isEmpty()) {
        	apiError.setErrors(errors);
        }
        
        return new ResponseEntity<Object>(apiError,HttpStatus.BAD_REQUEST);
    }
    
}
