package org.example.dao;
import com.development.models.entities.Workers;
import org.example.interfaces.IWorkersDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkersDAO extends AbstractMySQLDAO implements IWorkersDAO {
    private static final Logger logger = LoggerFactory.getLogger(WorkersDAO.class);

    @Override
    public Workers getById(int id) {
        String query = "SELECT p.id, p.first_name, p.last_name, p.email, w.jobTitle, w.salary, w.teamId " +
                "FROM people p " +
                "JOIN workers w ON p.id = w.id " +
                "WHERE w.id = ?";


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Workers worker = new Workers();
                    worker.setId(resultSet.getInt("id"));
                    worker.setFirstName(resultSet.getString("first_name"));
                    worker.setLastName(resultSet.getString("last_name"));
                    worker.setEmail(resultSet.getString("email"));
                    worker.setJobTitle(resultSet.getString("jobTitle"));
                    worker.setSalary(resultSet.getDouble("salary"));
                    worker.setTeamId(resultSet.getInt("teamId"));

                    return worker;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching worker by ID: {}", id, e);
        }
        return null;
    }

    @Override
    public List<Workers> getAll() {
        List<Workers> workersList = new ArrayList<>();
        String query = "SELECT p.id, p.first_name, p.last_name, p.email, w.jobTitle, w.salary, w.teamId " +
                "FROM people p " +
                "JOIN workers w ON p.id = w.id";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Workers worker = new Workers();
                worker.setId(resultSet.getInt("id"));
                worker.setFirstName(resultSet.getString("first_name"));
                worker.setLastName(resultSet.getString("last_name"));
                worker.setEmail(resultSet.getString("email"));
                worker.setJobTitle(resultSet.getString("jobTitle"));
                worker.setSalary(resultSet.getDouble("salary"));
                worker.setTeamId(resultSet.getInt("teamId"));

                workersList.add(worker);
            }
            logger.info("Retrieved {} workers from the database.", workersList.size());

        } catch (SQLException e) {
            logger.error("Error fetching all workers", e);
        }
        return workersList;
    }

    @Override
    public void save(Workers worker) {
        String insertPerson = "INSERT INTO people (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        String insertWorker = "INSERT INTO workers (id, jobTitle, salary, teamId) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement psPerson = conn.prepareStatement(insertPerson, Statement.RETURN_GENERATED_KEYS)) {
                psPerson.setString(1, worker.getFirstName());
                psPerson.setString(2, worker.getLastName());
                psPerson.setString(3, worker.getEmail());
                psPerson.setString(4, worker.getPhone());
                psPerson.executeUpdate();

                try (ResultSet rs = psPerson.getGeneratedKeys()) {
                    if (rs.next()) {
                        int personId = rs.getInt(1);
                        worker.setId(personId);

                        try (PreparedStatement psWorker = conn.prepareStatement(insertWorker)) {
                            psWorker.setInt(1, personId);
                            psWorker.setString(2, worker.getJobTitle());
                            psWorker.setDouble(3, worker.getSalary());
                            psWorker.setInt(4, worker.getTeamId());
                            psWorker.executeUpdate();
                        }
                    }
                }
                conn.commit();
                logger.info("Successfully saved worker: {}", worker.getLastName());
            } catch (SQLException e) {
                conn.rollback();
                logger.error("Transaction failed. Rolling back.", e);
            }
        } catch (SQLException e) {
            logger.error("Database connection error", e);
        }
    }

    @Override
    public void update(Workers worker) {
        String updatePerson = "UPDATE people SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        String updateWorker = "UPDATE workers SET jobTitle = ?, salary = ?, teamId = ? WHERE id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psP = conn.prepareStatement(updatePerson);
                 PreparedStatement psW = conn.prepareStatement(updateWorker)) {

                psP.setString(1, worker.getFirstName());
                psP.setString(2, worker.getLastName());
                psP.setString(3, worker.getEmail());
                psP.setInt(4, worker.getId());
                psP.executeUpdate();

                psW.setString(1, worker.getJobTitle());
                psW.setDouble(2, worker.getSalary());
                psW.setInt(3, worker.getTeamId());
                psW.setInt(4, worker.getId());
                psW.executeUpdate();

                conn.commit();
                logger.info("Updated worker ID: {}", worker.getId());
            } catch (SQLException e) {
                conn.rollback();
                logger.error("Update failed for ID: {}", worker.getId(), e);
            }
        } catch (SQLException e) {
            logger.error("Connection error during update", e);
        }
    }

    @Override
    public void delete(int id) {
        String deleteWorker = "DELETE FROM workers WHERE id = ?";
        String deletePerson = "DELETE FROM people WHERE id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psW = conn.prepareStatement(deleteWorker);
                 PreparedStatement psP = conn.prepareStatement(deletePerson)) {

                psW.setInt(1, id);
                psW.executeUpdate();

                psP.setInt(1, id);
                psP.executeUpdate();

                conn.commit();
                logger.info("Deleted worker and person record for ID: {}", id);
            } catch (SQLException e) {
                conn.rollback();
                logger.error("Delete failed for ID: {}", id, e);
            }
        } catch (SQLException e) {
            logger.error("Connection error during delete", e);
        }
    }
}
