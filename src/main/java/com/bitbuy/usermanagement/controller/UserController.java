package com.bitbuy.usermanagement.controller;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.ApiOutput;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.model.UserUpdate;
import com.bitbuy.usermanagement.service.UserService;
import com.bitbuy.usermanagement.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@GetMapping("/{uuid}")
	public ResponseEntity<String> getUser(@PathVariable(value = "uuid") String uuid) throws JsonProcessingException{

		logger.info("Calling getUser() of User Controller");

		logger.info("Username {}", uuid);

		Optional<User> user = Optional.ofNullable(userService.getUser(uuid));

		if(user.isEmpty()) {
			throw new NoSuchElementException(AppConstant.NOT_AVAILABLE);
		}

		String res = JsonUtil.convertJsonToString(user.get());

		logger.info("UserController::getUser: Response {}", res);

		return new ResponseEntity<String>(res,HttpStatus.OK);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<ApiOutput> updateUser(@PathVariable("uuid") String uuid, @Valid @RequestBody UserUpdate user) throws ValidationException {

		String res = userService.updateUserByUUID(user,uuid);

		ApiOutput apiOutput = new ApiOutput();
		apiOutput.setStatusCode(HttpStatus.OK.value());
		apiOutput.setMessage(res);

		return new ResponseEntity<ApiOutput>(apiOutput,HttpStatus.OK);
	}

	@PostMapping("/{uuid}")
	public ResponseEntity<ApiOutput> updateUser1(@PathVariable("uuid") String uuid, @Valid @RequestBody UserUpdate user) throws ValidationException {

		String res = userService.updateUserByUUID(user,uuid);

		ApiOutput apiOutput = new ApiOutput();
		apiOutput.setStatusCode(HttpStatus.OK.value());
		apiOutput.setMessage(res);

		return new ResponseEntity<ApiOutput>(apiOutput,HttpStatus.OK);
	}
}
