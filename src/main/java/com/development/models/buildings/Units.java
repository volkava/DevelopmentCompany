package com.development.models.buildings;

public class Units {
    private int id;
    private int buildingId; // The "Foreign Key"
    private String unitNumber;
    private double sqMeters;
    private String status; // ('Available', 'Sold', 'Rented')

    public Units() {}
}