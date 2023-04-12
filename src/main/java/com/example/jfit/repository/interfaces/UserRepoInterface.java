package com.example.jfit.repository.interfaces;

import com.example.jfit.domain.User;
import com.example.jfit.repository.Repository;
import com.example.jfit.domain.User;
import com.example.jfit.repository.Repository;

public interface UserRepoInterface extends Repository<Long, User> {
    User findByUsername(String username);
}
