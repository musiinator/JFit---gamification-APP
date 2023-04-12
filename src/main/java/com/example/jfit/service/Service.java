package com.example.jfit.service;

import com.example.jfit.domain.Quest;
import com.example.jfit.domain.User;
import com.example.jfit.domain.UserValidator;
import com.example.jfit.exceptions.ValidationException;
import com.example.jfit.repository.interfaces.QuestRepoInterface;
import com.example.jfit.repository.interfaces.UserRepoInterface;

public class Service {
    private static Service instance;
    private final UserRepoInterface repoUser;
    private final QuestRepoInterface repoQuest;
    private final UserValidator userValidator = new UserValidator();

    private Service(UserRepoInterface repoUser, QuestRepoInterface repoQuest) {
        this.repoUser = repoUser;
        this.repoQuest = repoQuest;
    }

    public static Service getInstance(UserRepoInterface repoUser, QuestRepoInterface repoQuest) {
        if (instance == null) {
            instance = new Service(repoUser, repoQuest);
        }
        return instance;
    }

    //User service
    public User findOneUser(Long id){
        return repoUser.findOne(id);
    }

    public Iterable<User> findAllUsers(){
        return repoUser.findAll();
    }

    public void saveUser(User user) {
        try{
            userValidator.validate(user);
            repoUser.save(user);
        }catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public void updateUser(User user) {
        try{
            userValidator.validate(user);
            repoUser.update(user);
        }catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public User findUserByUsername(String username) {
        return repoUser.findByUsername(username);
    }


    //Quest service
    public Quest findOneQuest(Long id){
        return repoQuest.findOne(id);
    }

    public Iterable<Quest> findAllQuests(){
        return repoQuest.findAll();
    }

    public void saveQuest(Quest quest) {
        repoQuest.save(quest);
    }

    public void updateQuest(Quest quest) {
        repoQuest.update(quest);
    }

}
