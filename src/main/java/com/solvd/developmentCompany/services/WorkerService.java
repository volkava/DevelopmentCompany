package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.db.mappers.IPeopleMapper;
import com.solvd.developmentCompany.db.mappers.IWorkersMapper;
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
                IPeopleMapper peopleMapper = session.getMapper(IPeopleMapper.class);
                IWorkersMapper workersMapper = session.getMapper(IWorkersMapper.class);
                peopleMapper.insert(worker);
                worker.setPersonId(worker.getId());
                workersMapper.insert(worker);
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
                IPeopleMapper peopleMapper = session.getMapper(IPeopleMapper.class);
                IWorkersMapper workersMapper = session.getMapper(IWorkersMapper.class);
                peopleMapper.update(worker);
                workersMapper.update(worker);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                throw new RuntimeException("Update failed. Data remains unchanged.", e);
            }
        }
    }

    public void deleteWorker(Long workerId, Long personId) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            try {
                IWorkersMapper workersMapper = session.getMapper(IWorkersMapper.class);
                IPeopleMapper peopleMapper = session.getMapper(IPeopleMapper.class);
                workersMapper.delete(workerId);
                peopleMapper.delete(personId);
                session.commit();

            } catch (Exception e) {
                session.rollback();
                throw new RuntimeException("Delete failed. Rolling back to prevent orphan data.", e);
            }
        }
    }
}