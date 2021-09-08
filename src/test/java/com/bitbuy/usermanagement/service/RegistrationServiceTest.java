package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.dao.RegistrationRepository;
import com.bitbuy.usermanagement.dao.UserRepository;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void shouldBeAbleToRegisterSuccessfully_register() throws ValidationException {

        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(null);

        Mockito.when(registrationRepository.save(Mockito.any())).thenReturn(getMockUser());

        String serviceResponse = registrationService.register(getMockUser());

        assertNotNull(serviceResponse);
        assertTrue(!serviceResponse.isEmpty());
        assertTrue(serviceResponse.contains(AppConstant.USER_REGISTRATION_SUCCESS));

        String[] tokens = serviceResponse.split(AppConstant.USER_REGISTRATION_SUCCESS);

        String UUID = tokens[1];

        assertTrue(UUID.length() == 36);

    }

    @Test
    public void shouldNotBeAbleToRegisterWhenUserAlreadyExist_register() throws ValidationException {

        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(getMockUser());

        assertThrows(ValidationException.class, () -> {

            registrationService.register(getMockUser());
        });


    }

    @Test
    public void shouldThrowAnErrorWhenInvalidData_register() {

        assertThrows(ValidationException.class, () -> {

            registrationService.register(null);
        });
    }

    private User getMockUser() {

        User user = new User("test", "test");
        user.setEmail("test@abc.com");
        user.setName("xyz");
        user.setPhone(1234567890);
        user.setUuid(getMockUUID());
        return user;
    }

    private String getMockUUID() {

        return UUID.randomUUID().toString();
    }
}
