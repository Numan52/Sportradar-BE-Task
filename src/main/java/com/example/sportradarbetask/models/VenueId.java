package com.example.sportradarbetask.models;

import java.io.Serial;
import java.io.Serializable;

public class VenueId implements Serializable {
    private String name;
    private String address;

    public VenueId() {}

    public VenueId(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
