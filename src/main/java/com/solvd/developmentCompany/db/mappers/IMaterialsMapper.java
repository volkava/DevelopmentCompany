package com.solvd.developmentCompany.db.mappers;

import com.solvd.developmentCompany.models.inventory.Materials;
import java.util.List;

public interface IMaterialsMapper {
    void insert(Materials material);
    Materials getById(Long id);
    List<Materials> getAll();
    void update(Materials material);
    void delete(Long id);
}