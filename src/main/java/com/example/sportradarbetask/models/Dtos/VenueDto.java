package com.example.sportradarbetask.models.Dtos;

public class VenueDto {
    private String name;
    private String address;
    private String city;
    private String country;
    private int capacity;

    public VenueDto() {
    }

    public VenueDto(String name, String address, String city, String country, int capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
