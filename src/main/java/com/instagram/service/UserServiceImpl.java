package com.instagram.service;

import com.instagram.dao.UserDAO;
import com.instagram.dao.UserDAOImpl;
import com.instagram.model.Users;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public boolean registerUser(Users users) {
        // TODO: Add validation
        return userDAO.addUser(users);
    }

    @Override
    public Users login(String username, String password) {
        // TODO: Implement login validation
        return null;
    }

    @Override
    public Users getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public Users getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }

    @Override
    public List<Users> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean updateUser(Users users) {
        // TODO: Add validation
        return userDAO.updateUser(users);
    }

    @Override
    public boolean deactivateUser(int userId) {
        // TODO: Implement deactivation
        return false;
    }
}