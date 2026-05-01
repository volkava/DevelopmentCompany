package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.buildings.Project;
import java.util.List;

public interface IProjectMapper {
    void insert(Project project);
    Project getById(Long id);
    List<Project> getAll();
    void update(Project project);
    void delete(Long id);
}
