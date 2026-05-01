package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.inventory.Machines;
import java.util.List;

public interface IMachinesMapper {
    void insert(Machines machine);
    Machines getById(Long id);
    void update(Machines machine);
    void delete(Long id);
    List<Machines> getAll();
}
