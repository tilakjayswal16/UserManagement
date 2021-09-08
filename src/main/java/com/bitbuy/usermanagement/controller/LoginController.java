package com.bitbuy.usermanagement.controller;

import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.ApiOutput;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.service.LoginService;
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
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<ApiOutput> login(@Valid @RequestBody User user) throws JsonProcessingException, ValidationException {
		
		logger.info("Calling login() of Login Controller");
		
		logger.info("Request {}", JsonUtil.convertJsonToString(user));
		ApiOutput apiOutput = new ApiOutput();
		
		String res = loginService.login(user);

		apiOutput.setMessage(res);

		apiOutput.setStatusCode(HttpStatus.OK.value());

		logger.info("Response {}", JsonUtil.convertJsonToString(apiOutput));

		return new ResponseEntity<ApiOutput>(apiOutput,HttpStatus.OK);
	}

}
