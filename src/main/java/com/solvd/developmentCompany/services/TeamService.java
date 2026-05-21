package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.utils.ConnectionFactory;
import com.solvd.developmentCompany.interfaces.ITeamsDAO;
import com.solvd.developmentCompany.interfaces.IWorkersDAO;
import com.solvd.developmentCompany.models.entities.Teams;
import com.solvd.developmentCompany.models.entities.Workers;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class TeamService {

    private final SqlSessionFactory sqlSessionFactory;

    public TeamService() {
        this.sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
    }

    public void createTeamWithLeader(Teams team, Long leaderWorkerId) {
        try (SqlSession session = sqlSessionFactory.openSession(false)) {
            try {
                ITeamsDAO teamsDAO = session.getMapper(ITeamsDAO.class);
                IWorkersDAO workersDAO = session.getMapper(IWorkersDAO.class);
                team.setTeamLeadId(leaderWorkerId);
                teamsDAO.save(team);
                Workers leader = workersDAO.getById(leaderWorkerId);
                if (leader != null) {
                    leader.setTeamId(team.getId());
                    workersDAO.update(leader);
                }

                session.commit();
            } catch (Exception e) {
                session.rollback();
                throw new RuntimeException("Could not create team: " + e.getMessage(), e);
            }
        }
    }

    public Teams getTeamDetails(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            return session.getMapper(ITeamsDAO.class).getById(id);
        }
    }
}