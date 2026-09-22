package com.instagram.dao;

import com.instagram.model.Users;

import java.util.List;
import com.instagram.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean addUser(Users users) {

        String sql = "INSERT INTO users (username, email, password_hash) VALUES (?, ?, ?)";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, users.getUsername());
            statement.setString(2, users.getEmail());
            statement.setString(3, users.getPasswordHash());
           // statement.setString(4, user.getStatus());
            //statement.setString(5, user.getRole());

            int rows = statement.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Unable to add user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Users getUserById(int userId) {
        return null;
    }

    @Override
    public Users getUserByUsername(String username) {
        return null;
    }

    @Override
    public Users getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<Users> getAllUsers() {
        return List.of();
    }

    @Override
    public boolean updateUser(Users users) {
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        return false;
    }


}