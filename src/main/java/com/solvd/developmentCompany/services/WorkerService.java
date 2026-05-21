package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.interfaces.IPeopleDAO;
import com.solvd.developmentCompany.interfaces.IWorkersDAO;
import com.solvd.developmentCompany.models.entities.Workers;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class WorkerService {

    private final SqlSessionFactory sqlSessionFactory;

    public WorkerService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void createWorker(Workers worker) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) { // false = manual commit
            try {
                IPeopleDAO peopleDAO = session.getMapper(IPeopleDAO.class);
                IWorkersDAO workersDAO = session.getMapper(IWorkersDAO.class);

                peopleDAO.save(worker);
                worker.setPersonId(worker.getId());
                workersDAO.save(worker);

                session.commit();
            } catch (Exception e) {
                session.rollback();
                throw new RuntimeException("Failed to create worker. Transaction rolled back.", e);
            }
        }
    }

    public void updateWorker(Workers worker) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            try {
                IPeopleDAO peopleDAO = session.getMapper(IPeopleDAO.class);
                IWorkersDAO workersDAO = session.getMapper(IWorkersDAO.class);

                peopleDAO.update(worker);
                workersDAO.update(worker);

                if (worker.getId() != null) {
                    Workers check = workersDAO.getById(worker.getId());
                    if (check == null) {
                        throw new RuntimeException("Update target not found in database.");
                    }
                }

                session.commit();
            } catch (Exception e) {
                session.rollback();

                throw new RuntimeException("Update failed: " + e.getMessage(), e);
            }
        }
    }

    public void deleteWorker(Long workerId, Long personId) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            try {
                IWorkersDAO workersDAO = session.getMapper(IWorkersDAO.class);
                IPeopleDAO peopleDAO = session.getMapper(IPeopleDAO.class);
                workersDAO.delete(workerId);
                peopleDAO.delete(personId);
                session.commit();

            } catch (Exception e) {
                session.rollback();
                throw new RuntimeException("Delete failed. Rolling back to prevent orphan data.", e);
            }
        }
    }
}