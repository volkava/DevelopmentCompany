package com.solvd.development.models.entities;

public class Contractors {
    private int id;
    private String companyName;
    private String specialty;
    private int repPersonId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRepPersonId() {
        return repPersonId;
    }

    public void setRepPersonId(int repPersonId) {
        this.repPersonId = repPersonId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}