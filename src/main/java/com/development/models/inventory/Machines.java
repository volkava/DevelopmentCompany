package com.development.models.inventory;

public class Machines {
    private int id;
    private String machineName;
    private int contractorId;
    private int currentProjectId;
    private boolean isCompanyProperty;
    private boolean isAssigned;
    private String plateNumber;
    private String serialNumber;
    private String value;

    public Machines() {}

    @Override
    public String toString() {
        return "Machines{" +
                "id=" + id +
                ", machineName='" + machineName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public int getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(int currentProjectId) {
        this.currentProjectId = currentProjectId;
    }

    public boolean isCompanyProperty() {
        return isCompanyProperty;
    }

    public void setCompanyProperty(boolean companyProperty) {
        isCompanyProperty = companyProperty;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
