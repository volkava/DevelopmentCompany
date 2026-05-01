package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.entities.Workers;

import java.util.List;

public interface IWorkersMapper {
    void insert(Workers worker);
    Workers getById(Long id);
    List<Workers> getAll();
    void update(Workers worker);
    void delete(Long id);
}
