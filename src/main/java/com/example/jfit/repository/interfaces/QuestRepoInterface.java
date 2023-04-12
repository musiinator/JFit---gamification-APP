package com.example.jfit.repository.interfaces;

import com.example.jfit.domain.Quest;
import com.example.jfit.repository.Repository;

public interface QuestRepoInterface extends Repository<Long, Quest> {
    Iterable<Quest> findByType(String type);
}
