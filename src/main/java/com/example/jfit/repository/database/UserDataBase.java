package com.example.jfit.repository.database;


import com.example.jfit.domain.Quest;
import com.example.jfit.domain.User;
import com.example.jfit.repository.interfaces.UserRepoInterface;

import java.sql.*;
import java.util.*;

public class UserDataBase implements UserRepoInterface {

    private String url;
    private String username;
    private String password;


    public UserDataBase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public User findOne(Long id) {
        String idS = id.toString();
        String sql = "select * from users where id ='" + idS + "'" ;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long ID = resultSet.getLong("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String badges = resultSet.getString("badges");
            Integer tokens = resultSet.getInt("tokens");
            User user = new User(username, password, badges, tokens);
            user.setId(ID);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        Set<User> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("select * from users");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long ID = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String badges = resultSet.getString("badges");
                Integer tokens = resultSet.getInt("tokens");
                User user = new User(username, password, badges, tokens);
                user.setId(ID);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User save(User entity) {
        String sql = "insert into users (username, password, tokens) values (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
             statement.setString(1, entity.getUsername());
             statement.setString(2, entity.getPassword());
             statement.setInt(3, 1000);
             statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User delete(Long id) {
        String idS = id.toString();
        String sql = "delete from users where id ='" + idS + "'" ;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User entity) {
        String sql = "update users set badges = ?, tokens = ? where id = " + entity.getId();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getBadges());
            statement.setInt(2, entity.getTokens());
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User findByUsername(String username1) {
        String sql = "select * from users where username = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username1);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long ID = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String badges = resultSet.getString("badges");
                Integer tokens = resultSet.getInt("tokens");
                User user = new User(username, password, badges, tokens);
                user.setId(ID);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
