package com.solvd.developmentCompany.interfaces;

import java.util.List;

public interface IBaseDAO<T> {
    T getById(int id);
    List<T> getAll();
    void save(T entity);
    void update(T entity);
    void delete(int id);
}