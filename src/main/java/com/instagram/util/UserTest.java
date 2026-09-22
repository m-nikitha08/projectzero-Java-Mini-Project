package com.instagram.util;

import com.instagram.model.Users;
import com.instagram.service.UserService;
import com.instagram.service.UserServiceImpl;

public class UserTest {

    public static void main(String[] args)  {

        UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUsername("Nikitha_M89");
        users.setEmail("Nikitha_M89@gmail.com");
        users.setPasswordHash("password123");
        //user.setStatus("ACTIVE");
        //user.setRole("USER");

        boolean result = userService.registerUser(users);

        if (result) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }
}