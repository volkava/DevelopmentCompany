package com.solvd.developmentCompany.models.entities;

public class Teams {
    private Long id;
    private String teamName;
    private String specialty;
    private Long teamLeadId;

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

    public Long getTeamLeadId() {
        return teamLeadId;
    }

    public void setTeamLeadId(Long teamLeadId) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
