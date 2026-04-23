package com.solvd.developmentCompany.models.inventory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "machines")
@XmlAccessorType(XmlAccessType.FIELD)
public class MachinesListWrapper {

    @XmlElement(name = "machine")
    private List<Machines> machines;
    public List<Machines> getMachines() { return machines; }
    public void setMachines(List<Machines> machines) { this.machines = machines; }
}
