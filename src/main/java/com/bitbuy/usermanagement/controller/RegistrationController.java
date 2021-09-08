package com.bitbuy.usermanagement.controller;

import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.ApiOutput;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.service.RegistrationService;
import com.bitbuy.usermanagement.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegistrationController {
	
	private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@Autowired
	RegistrationService registrationService;

	@PostMapping("/register")
	public ResponseEntity<ApiOutput> register(@Valid @RequestBody User user) throws JsonProcessingException, ValidationException {
		
		logger.info("Calling register() of User Controller");
		
		logger.info("Request {}", JsonUtil.convertJsonToString(user));
		String res = registrationService.register(user);

		ApiOutput apiOutput = new ApiOutput();
		apiOutput.setStatusCode(HttpStatus.CREATED.value());
		apiOutput.setMessage(res);
		
		logger.info("Response {}", JsonUtil.convertJsonToString(apiOutput));

		return new ResponseEntity<ApiOutput>(apiOutput,HttpStatus.CREATED);
	}

}
