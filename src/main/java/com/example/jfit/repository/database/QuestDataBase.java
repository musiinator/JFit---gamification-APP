package com.example.jfit.repository.database;

import com.example.jfit.domain.Quest;
import com.example.jfit.domain.QuestStatus;
import com.example.jfit.domain.User;
import com.example.jfit.repository.interfaces.QuestRepoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestDataBase implements QuestRepoInterface {

    private String url;
    private String username;
    private String password;

    public QuestDataBase(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Quest findOne(Long id) {
        String idS = id.toString();
        String sql = "select * from quests where id ='" + idS + "'" ;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long ID = resultSet.getLong("id");
            String description = resultSet.getString("description");
            String type = resultSet.getString("type");
            Integer reward = resultSet.getInt("reward");
            Long idUser = resultSet.getLong("id_user");
            String idUsersString = resultSet.getString("users");
            List<Long> idUsers = new ArrayList<>();
            if(idUsersString != null)
            {
                String[] idUsersStringArray = idUsersString.split(",");
                for(String idUserString : idUsersStringArray)
                {
                    idUsers.add(Long.parseLong(idUserString));
                }
            }
            QuestStatus status = QuestStatus.valueOf(resultSet.getString("status"));
            Quest quest = new Quest(description, type, reward, idUser, idUsers, status);
            quest.setId(ID);
            return quest;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Quest> findAll() {
        Set<Quest> quests = new HashSet<>();
        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("select * from quests");
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long ID = resultSet.getLong("id");
                String description = resultSet.getString("description");
                String type = resultSet.getString("type");
                Integer reward = resultSet.getInt("reward");
                Long idUser = resultSet.getLong("id_user");
                String idUsersString = resultSet.getString("users");
                List<Long> idUsers = new ArrayList<>();
                if(idUsersString != null && idUsersString.length() > 0)
                {
                    String[] idUsersStringArray = idUsersString.split(",");
                    if(idUsersStringArray.length > 0) {
                        for (String idUserString : idUsersStringArray) {
                            idUsers.add(Long.parseLong(idUserString));
                        }
                    }
                }
                QuestStatus status = QuestStatus.valueOf(resultSet.getString("status"));
                Quest quest = new Quest(description, type, reward, idUser, idUsers, status);
                quest.setId(ID);
                quests.add(quest);
            }
            return quests;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quests;
    }

    @Override
    public Quest save(Quest entity) {
        String sql = "insert into quests (description, type, reward, id_user, users, status) values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getDescription());
            statement.setString(2, entity.getType());
            statement.setInt(3, entity.getReward());
            statement.setLong(4, entity.getIdUser());
            String idUsersString = "";
            if(entity.getIdUsers() != null && !entity.getIdUsers().isEmpty()) {
                for (Long idUser : entity.getIdUsers()) {
                    idUsersString += idUser.toString() + ",";
                }
            }
            statement.setString(5, idUsersString);
            statement.setString(6, entity.getStatus().toString());
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Quest delete(Long id) {
        return null;
    }

    @Override
    public Quest update(Quest entity) {
        String sql = "update quests set description = ?, type = ?, reward = ?, id_user = ?, users = ?, status = ? where id = " + entity.getId().toString();
        try(Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getDescription());
            statement.setString(2, entity.getType());
            statement.setInt(3, entity.getReward());
            statement.setLong(4, entity.getIdUser());
            String idUsersString = "";
            if(!entity.getIdUsers().isEmpty()) {
                for (Long idUser : entity.getIdUsers()) {
                    idUsersString += idUser.toString() + ",";
                }
            }
            statement.setString(5, idUsersString);
            statement.setString(6, entity.getStatus().toString());
            statement.executeUpdate();
            return entity;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Quest> findByType(String type) {
        Set<Quest> quests = new HashSet<>();
        String sql = "select * from quests where type ='" + type + "'" ;
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Long ID = resultSet.getLong("id");
                String description = resultSet.getString("description");
                Integer reward = resultSet.getInt("reward");
                Long idUser = resultSet.getLong("id_user");
                String idUsersString = resultSet.getString("users");
                List<Long> idUsers = new ArrayList<>();
                if (idUsersString != null) {
                    String[] idUsersStringArray = idUsersString.split(",");
                    for (String idUserString : idUsersStringArray) {
                        idUsers.add(Long.parseLong(idUserString));
                    }
                }
                QuestStatus status = QuestStatus.valueOf(resultSet.getString("status"));
                Quest quest = new Quest(description, type, reward, idUser, idUsers, status);
                quest.setId(ID);
                quests.add(quest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quests;
    }
}
