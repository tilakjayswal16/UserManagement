package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.dao.UserRepository;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.model.UserUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User getUser(String uuid){

		if(uuid == null || uuid.isEmpty())
		{
			throw new NoSuchElementException(AppConstant.NOT_AVAILABLE);
		}

		logger.info("Find User...{} ", uuid);

		return userRepository.findByUuid(uuid);
	}

	public String updateUserByUUID(UserUpdate user, String uuid) throws ValidationException {

		Optional<User> user1 = Optional.ofNullable(getUser(uuid));

		if(user1.isEmpty())
		{
			throw new NoSuchElementException(AppConstant.NOT_AVAILABLE);
		}

		userRepository.updateUserByUUID(user.getName(),user.getEmail(),user.getPhone(),uuid);

		return AppConstant.USER_UPDATE_SUCCESS + uuid;
	}
}
