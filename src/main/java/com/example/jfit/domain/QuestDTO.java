package com.example.jfit.domain;

import java.util.Objects;

public class QuestDTO {
    private String description;
    private String type;
    private Integer reward;
    private QuestStatus status;

    public QuestDTO(String description, String type, Integer reward, QuestStatus status) {
        this.description = description;
        this.type = type;
        this.reward = reward;
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
        QuestDTO questDTO = (QuestDTO) o;
        return Objects.equals(description, questDTO.description) && Objects.equals(type, questDTO.type) && Objects.equals(reward, questDTO.reward) && status == questDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, type, reward, status);
    }

    @Override
    public String toString() {
        return "QuestDTO{" +
                "description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", reward=" + reward +
                ", status=" + status +
                '}';
    }
}
