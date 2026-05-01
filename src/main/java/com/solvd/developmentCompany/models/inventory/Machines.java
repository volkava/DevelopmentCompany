package com.solvd.developmentCompany.models.inventory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.*;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@XmlRootElement(name = "machine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Machines {
    @XmlAttribute
    @JsonProperty("id")
    private Long id;
    @XmlElement
    @JsonProperty("machine_name")
    private String machineName;
    @JsonProperty("contractor_id")
    private Long contractorId;
    private Long currentProjectId;
    private boolean isCompanyProperty;
    private boolean isAssigned;
    private String plateNumber;
    private String serialNumber;
    private String value;

    public Machines() {}

    public Machines(Long id, String machineName, Long contractorId, Long currentProjectId,
                    boolean isCompanyProperty, boolean isAssigned, String plateNumber,
                    String serialNumber, String value) {
        this.id = id;
        this.machineName = machineName;
        this.contractorId = contractorId;
        this.currentProjectId = currentProjectId;
        this.isCompanyProperty = isCompanyProperty;
        this.isAssigned = isAssigned;
        this.plateNumber = plateNumber;
        this.serialNumber = serialNumber;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Machines{" +
                "id=" + id +
                ", machineName='" + machineName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Long getContractorId() {
        return contractorId;
    }

    public void setContractorId(Long contractorId) {
        this.contractorId = contractorId;
    }

    public Long getCurrentProjectId() {
        return currentProjectId;
    }

    public void setCurrentProjectId(Long currentProjectId) {
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
