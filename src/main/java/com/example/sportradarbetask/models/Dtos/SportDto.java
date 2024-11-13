package com.example.sportradarbetask.models.Dtos;

public class SportDto {
    private String name;

    public SportDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
