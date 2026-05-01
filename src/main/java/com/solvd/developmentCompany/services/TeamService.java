package com.solvd.developmentCompany.services;

import com.solvd.developmentCompany.utils.ConnectionFactory;
import com.solvd.developmentCompany.db.mappers.ITeamsMapper;
import com.solvd.developmentCompany.db.mappers.IWorkersMapper;
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
                ITeamsMapper teamsMapper = session.getMapper(ITeamsMapper.class);
                IWorkersMapper workersMapper = session.getMapper(IWorkersMapper.class);
                team.setTeamLeadId(leaderWorkerId);
                teamsMapper.insert(team);
                Workers leader = workersMapper.getById(leaderWorkerId);
                if (leader != null) {
                    leader.setTeamId(team.getId());
                    workersMapper.update(leader);
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
            return session.getMapper(ITeamsMapper.class).getById(id);
        }
    }
}