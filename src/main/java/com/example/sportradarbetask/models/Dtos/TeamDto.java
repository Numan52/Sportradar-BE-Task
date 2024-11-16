package com.example.sportradarbetask.models.Dtos;

import com.fasterxml.jackson.annotation.JsonInclude;


public class TeamDto {
    private Long teamId;
    private String teamName;
    private String city;
    private Integer foundingYear;


    public TeamDto() {
    }

    public TeamDto(Long teamId, String teamName, String city, Integer foundingYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.city = city;
        this.foundingYear = foundingYear;
    }

    public TeamDto(Long teamId, String teamName) {
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }
}

