package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.model.UserUpdate;

import java.util.List;

public interface UserService {

	public User getUser(String uuid);

	public String updateUserByUUID(UserUpdate user, String uuid) throws ValidationException;
}
