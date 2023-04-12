package com.example.jfit.domain;

import java.util.Objects;

public class RankDTO {
    private Integer score;
    private String username;

    public RankDTO(Integer score, String username) {
        this.score = score;
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankDTO rankDTO = (RankDTO) o;
        return Objects.equals(score, rankDTO.score) && Objects.equals(username, rankDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, username);
    }

    @Override
    public String toString() {
        return "RankDTO{" +
                "score=" + score +
                ", username='" + username + '\'' +
                '}';
    }
}
