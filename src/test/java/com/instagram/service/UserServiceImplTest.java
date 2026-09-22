package com.instagram.service;

import com.instagram.model.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void registerUser() {
        UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUsername("Nikitha_M892");
        users.setEmail("Nikitha_M89@gmail.com");
        users.setPasswordHash("password123");

        boolean result = userService.registerUser(users);

        assertTrue(result);
    }


    void duplicateUsernameTest() {

        UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUsername("Nikitha_M892");
        users.setEmail("another@gmail.com");
        users.setPasswordHash("password123");

        boolean result = userService.registerUser(users);

        assertFalse(result);
    }
}