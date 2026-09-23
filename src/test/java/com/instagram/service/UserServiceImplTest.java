package com.instagram.service;

import com.instagram.model.Users;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void registerUser() {
        UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUsername("Nikitha_M893");
        users.setEmail("Nikitha_M893@gmail.com");
        users.setPasswordHash("password123");

        boolean result = userService.registerUser(users);

        assertTrue(result);
        System.out.println("Inserted Successfully");
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

    @Test
    void getUserByUsername() {


        UserServiceImpl userserver= new UserServiceImpl();
        Users user=userserver.getUserByUsername("Nikitha_M");
        assertNotNull(user);
        assertEquals("Nikitha_M",user.getUsername());
        System.out.println("Fetched details are "+user.getUserId()+" "+user.getUsername()+" "+user.getEmail());


       // assertTrue(result);


    }


    @Test
    void getUserById() {


        UserServiceImpl userserver= new UserServiceImpl();
        Users user=userserver.getUserById(1);
        assertNotNull(user);
        assertEquals(1,user.getUserId());
        System.out.println("Fetched details are "+user.getUserId()+" "+user.getUsername()+" "+user.getEmail());


        // assertTrue(result);


    }

    @Test
    void deleteUser()
    {
        UserService userService = new UserServiceImpl();

        boolean result = userService.deleteUser(13);

        assertTrue(result);

        System.out.println("Deleted SuccessFully");

    }

    @Test
    void getAllUsers() {

        UserService userService = new UserServiceImpl();

        List<Users> usersList = userService.getAllUsers();

        assertNotNull(usersList);

        for (Users user : usersList) {
            System.out.println(
                    user.getUserId() + " " +
                            user.getUsername() + " " +
                            user.getEmail()
            );
        }
    }

    @Test
    void updateUser() {

        UserService userService = new UserServiceImpl();

        Users users = new Users();

        users.setUserId(4);
        users.setUsername("Nikitha_Moola");
        users.setEmail("nikithaMoola@gmail.com");
        users.setPasswordHash("nikithamoola123");

        boolean result = userService.updateUser(users);

        assertTrue(result);

        System.out.println("Updated Successfully");
    }



    @Test
    void login() {

        UserService userService = new UserServiceImpl();

        Users user = userService.login("Nikitha_M", "password123");

        assertNotNull(user);
        assertEquals("Nikitha_M", user.getUsername());
        //assertEquals("Nikitha_M@gmail.com", user.getEmail());

        System.out.println("User logged in successfully");
    }

    @Test
    void loginWithWrongPassword() {

        UserService userService = new UserServiceImpl();

        Users user = userService.login("Nikitha_M", "wrongPassword");

        assertNull(user);
    }
}