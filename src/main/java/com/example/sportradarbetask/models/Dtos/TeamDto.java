package com.example.sportradarbetask.models.Dtos;

public class TeamDto {
    private Long teamId;
    private String teamName;
    private String city;
    private int foundingYear;


    public TeamDto() {
    }

    public TeamDto(Long teamId, String teamName, String city, int foundingYear) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.city = city;
        this.foundingYear = foundingYear;
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

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }
}

