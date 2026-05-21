package com.solvd.developmentCompany.interfaces;

import com.solvd.developmentCompany.models.inventory.Machines;

public interface IMachinesDAO extends IBaseDAO<Machines> {

    Machines getByName(String machineName);
}