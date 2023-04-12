package com.example.jfit.domain;

import java.util.List;
import java.util.Objects;

public class Quest extends Entity<Long> {
    private String description;
    private String type;
    private Integer reward;
    private Long idUser;
    private List<Long> idUsers;
    private QuestStatus status;

    public Quest(String description, String type, Integer reward, Long idUser, List<Long> idUsers, QuestStatus status) {
        this.description = description;
        this.type = type;
        this.reward = reward;
        this.idUser = idUser;
        this.idUsers = idUsers;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getReward() {
        return reward;
    }

    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(List<Long> idUsers) {
        this.idUsers = idUsers;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return Objects.equals(description, quest.description) && Objects.equals(type, quest.type) && Objects.equals(reward, quest.reward) && Objects.equals(idUser, quest.idUser) && Objects.equals(idUsers, quest.idUsers) && status == quest.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, reward, idUser, idUsers, status);
    }

    @Override
    public String toString() {
        return "Quest{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", reward=" + reward +
                ", idUser=" + idUser +
                ", idUsers=" + idUsers +
                ", status=" + status +
                '}';
    }
}
