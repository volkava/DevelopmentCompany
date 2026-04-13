package com.development.models.buildings;

import java.util.Date;

public class Project {
    private int id;
    private String projectName;
    private int buildingId;
    private Date startDate;
    private Date estimatedFinishDate;
    private int projectLeadId;
    private String status;
    private Date completionDate;

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    public void setEstimatedFinishDate(Date estimatedFinishDate) {
        this.estimatedFinishDate = estimatedFinishDate;
    }

    public int getProjectLeadId() {
        return projectLeadId;
    }

    public void setProjectLeadId(int projectLeadId) {
        this.projectLeadId = projectLeadId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}