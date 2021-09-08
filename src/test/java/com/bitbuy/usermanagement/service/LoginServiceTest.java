package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void shouldBeAbleToLoginSuccessfully_login() throws ValidationException {


        Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);

        String expectedResponse = AppConstant.LOGIN_SUCCESS;

        String actualServiceResponse = loginService.login(getMockUser());

        assertNotNull(actualServiceResponse);
        assertTrue(!actualServiceResponse.isEmpty());
        assertEquals(expectedResponse, actualServiceResponse);
    }

    @Test
    public void shouldThrowAnErrorWhenInvalidData_login() {

        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(BadCredentialsException.class);

        assertThrows(BadCredentialsException.class, () -> {

            loginService.login(getMockUser());
        });
    }

    private User getMockUser() {

        User user = new User("test", "test");
        user.setEmail("test@abc.com");
        user.setName("xyz");
        user.setPhone(1234567890);
        return user;
    }

}
