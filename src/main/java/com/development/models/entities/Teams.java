package com.development.models.entities;

public class Teams {
    private int id;
    private String teamName;
    private String specialty;
    private int teamLeadId;

    public Teams() {
    }

    @Override
    public String toString() {
        return "Teams{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", teamLeadId=" + teamLeadId +
                '}';
    }

    public int getTeamLeadId() {
        return teamLeadId;
    }

    public void setTeamLeadId(int teamLeadId) {
        this.teamLeadId = teamLeadId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
