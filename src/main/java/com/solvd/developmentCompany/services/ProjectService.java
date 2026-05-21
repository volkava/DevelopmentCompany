package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.interfaces.IProjectDAO;
import com.solvd.developmentCompany.utils.ConnectionFactory;
import com.solvd.developmentCompany.models.buildings.Project;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

public class ProjectService {
    private final SqlSessionFactory sqlSessionFactory;

    public ProjectService() {
        this.sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    public void createProject(Project project) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            projectDAO.save(project);
        }
    }

    public Project getProjectById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            return projectDAO.getById(id);
        }
    }

    public List<Project> getAllProjects() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            return projectDAO.getAll();
        }
    }

    public void updateProject(Project project) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            projectDAO.update(project);

            if (project.getId() != null) {
                Project check = projectDAO.getById(project.getId());
                if (check == null) {
                    throw new RuntimeException("Update target project not found.");
                }
            }
        }
    }

    public void deleteProject(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            IProjectDAO projectDAO = session.getMapper(IProjectDAO.class);
            projectDAO.delete(id);
        }
    }
}
