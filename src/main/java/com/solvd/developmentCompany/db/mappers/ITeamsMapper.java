package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.entities.Teams;
import java.util.List;

public interface ITeamsMapper {
    void insert(Teams team);
    Teams getById(Long id);
    List<Teams> getAll();
    void update(Teams team);
    void delete(Long id);
}