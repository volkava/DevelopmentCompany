package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.entities.People;
import java.util.List;

public interface IPeopleMapper {
    void insert(People person);
    People getById(Long id);
    List<People> getAll();
    void update(People person);
    void delete(Long id);
}