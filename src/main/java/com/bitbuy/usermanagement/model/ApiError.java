package com.bitbuy.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"statusCode", "message","errors"})
public class ApiError {

	private int statusCode;
    private String message;
    private Map<String,String> errors;

    public ApiError(String message) {
		super();
		this.message = message;
	}

    public ApiError(int statusCode, String message) {
    	super();
    	this.statusCode = statusCode;
    	this.message = message;
    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	@Override
	public String toString() {
		return "ApiError [statusCode=" + statusCode + ", message=" + message + ", errors=" + errors + "]";
	}

}
