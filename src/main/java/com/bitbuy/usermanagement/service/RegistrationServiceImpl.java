package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.dao.RegistrationRepository;
import com.bitbuy.usermanagement.dao.UserRepository;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    @Autowired
    RegistrationRepository registrationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String register(User user) throws ValidationException {

        try {

            if (user == null) {
                throw new ValidationException(AppConstant.VALIDATION_ERROR);
            }

            logger.info("User to be saved...{} ", user);

            Optional<User> userInDB = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

            if (!userInDB.isEmpty()) {
                throw new ValidationException(AppConstant.UNIQUE_USER);
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setUuid(UUID.randomUUID().toString());
            registrationRepository.save(user);

        } catch (DuplicateKeyException e) {
            throw new ValidationException(AppConstant.UNIQUE_USER);
        }

        return AppConstant.USER_REGISTRATION_SUCCESS + user.getUuid();
    }
}
