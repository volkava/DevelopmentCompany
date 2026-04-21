package com.solvd.developmentCompany.interfaces.impl;

import com.solvd.developmentCompany.models.inventory.Machines;
import com.solvd.developmentCompany.interfaces.IMachinesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachinesDAO extends AbstractMySQLDAO implements IMachinesDAO {
    private static final Logger logger = LoggerFactory.getLogger(MachinesDAO.class);

    @Override
    public Machines getById(int id) {
        String query = "SELECT * FROM machines WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Machines machine = new Machines();
                    machine.setId(rs.getLong("id"));
                    machine.setMachineName(rs.getString("machineName"));
                    machine.setContractorId(rs.getLong("contractorId"));
                    machine.setCurrentProjectId(rs.getLong("currentProjectId"));
                    machine.setCompanyProperty(rs.getBoolean("isCompanyProperty"));
                    machine.setAssigned(rs.getBoolean("isAssigned"));
                    machine.setPlateNumber(rs.getString("plateNumber"));
                    machine.setSerialNumber(rs.getString("serialNumber"));
                    machine.setValue(rs.getString("value"));
                    return machine;
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching machine with ID: {}", id, e);
        }
        return null;
    }

    @Override
    public List<Machines> getAll() {
        List<Machines> machinesList = new ArrayList<>();
        String query = "SELECT * FROM machines";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Machines machine = new Machines();
                machine.setId((long) rs.getInt("id"));
                machine.setMachineName(rs.getString("machineName"));
                machine.setContractorId((long) rs.getInt("contractorId"));
                machine.setCurrentProjectId((long) rs.getInt("currentProjectId"));
                machine.setCompanyProperty(rs.getBoolean("isCompanyProperty"));
                machine.setAssigned(rs.getBoolean("isAssigned"));
                machine.setPlateNumber(rs.getString("plateNumber"));
                machine.setSerialNumber(rs.getString("serialNumber"));
                machine.setValue(rs.getString("value"));

                machinesList.add(machine);
            }
        } catch (SQLException e) {
            logger.error("Error fetching all machines", e);
        }
        return machinesList;
    }

    @Override
    public void save(Machines machine) {
        String query = "INSERT INTO machines (machineName, contractorId, currentProjectId, " +
                "isCompanyProperty, isAssigned, plateNumber, serialNumber, value) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, machine.getMachineName());
            stmt.setLong(2, machine.getContractorId());
            stmt.setLong(3, machine.getCurrentProjectId());
            stmt.setBoolean(4, machine.isCompanyProperty());
            stmt.setBoolean(5, machine.isAssigned());
            stmt.setString(6, machine.getPlateNumber());
            stmt.setString(7, machine.getSerialNumber());
            stmt.setString(8, machine.getValue());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    machine.setId(rs.getLong(1));
                }
            }
            logger.info("Machine saved: {}", machine.getMachineName());
        } catch (SQLException e) {
            logger.error("Error saving machine", e);
        }
    }

    @Override
    public void update(Machines machine) {
        String query = "UPDATE machines SET machineName = ?, contractorId = ?, currentProjectId = ?, " +
                "isCompanyProperty = ?, isAssigned = ?, plateNumber = ?, serialNumber = ?, value = ? " +
                "WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, machine.getMachineName());
            stmt.setLong(2, machine.getContractorId());
            stmt.setLong(3, machine.getCurrentProjectId());
            stmt.setBoolean(4, machine.isCompanyProperty());
            stmt.setBoolean(5, machine.isAssigned());
            stmt.setString(6, machine.getPlateNumber());
            stmt.setString(7, machine.getSerialNumber());
            stmt.setString(8, machine.getValue());
            stmt.setLong(9, machine.getId());

            stmt.executeUpdate();
            logger.info("Updated machine ID: {}", machine.getId());
        } catch (SQLException e) {
            logger.error("Error updating machine", e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM machines WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            logger.info("Deleted machine ID: {}", id);
        } catch (SQLException e) {
            logger.error("Error deleting machine", e);
        }
    }
}