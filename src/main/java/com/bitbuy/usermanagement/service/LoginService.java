package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;

public interface LoginService {

	public String login(User user);

}
