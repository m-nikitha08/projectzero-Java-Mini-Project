package com.instagram.dao;

import com.instagram.model.Users;

import java.sql.ResultSet;
import java.util.ArrayList;
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
    public Users login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Users users = new Users();

                users.setUserId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(resultSet.getString("email"));
                users.setPasswordHash(resultSet.getString("password_hash"));

                return users;
            }

        } catch (SQLException e) {
            System.out.println("Unable to login: " + e.getMessage());
        }

        return null;
    }

    @Override
    public Users getUserById(int userId) {

        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Users user = new Users();

                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                //user.setPasswordHash(resultSet.getString("password_hash"));


                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Users user = new Users();

                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                //user.setPasswordHash(resultSet.getString("password_hash"));


                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

   // @Override
   // public Users getUserByEmail(String email) {
    //    return null;
    //}

    @Override
    public List<Users> getAllUsers() {

        String sql = "SELECT * FROM users";
        List<Users> usersList = new ArrayList<>();

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Users users = new Users();

                users.setUserId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setEmail(resultSet.getString("email"));
                users.setPasswordHash(resultSet.getString("password_hash"));

                usersList.add(users);
            }

            return usersList;

        } catch (SQLException e) {
            System.out.println("Unable to get users: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public boolean updateUser(Users users) {

        String sql = "UPDATE users SET username = ?, email = ?, password_hash = ? WHERE user_id = ?";

        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, users.getUsername());
            statement.setString(2, users.getEmail());
            statement.setString(3, users.getPasswordHash());
            statement.setInt(4, users.getUserId());

            int updatedRows = statement.executeUpdate();

            return updatedRows > 0;

        } catch (SQLException e) {
            System.out.println("Unable to update user: " + e.getMessage());
            return false;
        }


    }

    @Override
    public boolean deleteUser(int userId) {

        String sql = "delete FROM users WHERE user_id = ?";
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            int deletedRow = statement.executeUpdate();
            return deletedRow > 0;


        } catch (SQLException e) {
            System.out.println("Unable to delete user: " + e.getMessage());
            return false;
        }
    }





    }