package com.solvd.developmentCompany.models.entities;

public class Workers extends People {
    private Long id;
    private Long personId;
    private String jobTitle;
    private double salary;
    private int teamId;


    public Workers() {
        super();
    }

    @Override
    public String toString() {
        return "Worker{" +
                "jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                "} " + super.toString();
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
        super.setId(personId);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

}
