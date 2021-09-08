package com.bitbuy.usermanagement.service;

import com.bitbuy.usermanagement.constant.AppConstant;
import com.bitbuy.usermanagement.dao.UserRepository;
import com.bitbuy.usermanagement.exception.ValidationException;
import com.bitbuy.usermanagement.model.User;
import com.bitbuy.usermanagement.model.UserUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;


    @Test
    public void shouldGiveValidUserObject_getUser() {

        Mockito.when(userRepository.findByUuid(getMockUUID())).thenReturn(getMockUser());
        User expectedUser = userService.getUser(getMockUUID());

        assertNotNull(expectedUser);
        assertEquals(expectedUser.getUsername(), getMockUser().getUsername());
        assertEquals(expectedUser.getPassword(), getMockUser().getPassword());
    }

    @Test
    public void shouldThrowExceptionForInvalidUUID_getUser() {

        assertThrows(NoSuchElementException.class, () -> {

            userService.getUser(null);
        });
    }

    @Test
    public void shouldUpdateUserSuccessfully_updateUserByUUID() throws ValidationException {

        Mockito.when(userRepository.findByUuid(getMockUUID())).thenReturn(getMockUser());

        UserUpdate userToBeUpdated = new UserUpdate("changed@abc.com", "changedName",1234567890);

        String expectedServiceResponse = AppConstant.USER_UPDATE_SUCCESS + getMockUUID();

        String actualServiceResponse = userService.updateUserByUUID(userToBeUpdated, getMockUUID());

        assertNotNull(actualServiceResponse);
        assertTrue(!actualServiceResponse.isEmpty());
        assertEquals(expectedServiceResponse, actualServiceResponse);
    }

    @Test
    public void shouldThrowAnErrorForInvalidUser_updateUserByUUID() {

        assertThrows(NoSuchElementException.class, () -> {

            userService.updateUserByUUID(null, getMockUUID());
        });

    }

    private User getMockUser() {

        User user = new User("test", "test");
        user.setEmail("test@abc.com");
        user.setName("xyz");
        user.setPhone(1234567890);
        return user;
    }

    private String getMockUUID() {

        return new String("ABCD");
    }
}
