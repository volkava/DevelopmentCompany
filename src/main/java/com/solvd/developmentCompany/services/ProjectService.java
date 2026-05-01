package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.db.mappers.IProjectMapper;
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
            IProjectMapper mapper = session.getMapper(IProjectMapper.class);
            mapper.insert(project);
        }
    }

    public Project getProjectById(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProjectMapper mapper = session.getMapper(IProjectMapper.class);
            return mapper.getById(id);
        }
    }

    public List<Project> getAllProjects() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IProjectMapper mapper = session.getMapper(IProjectMapper.class);
            return mapper.getAll();
        }
    }
}