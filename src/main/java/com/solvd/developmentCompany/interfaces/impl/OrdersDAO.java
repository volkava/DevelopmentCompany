package com.solvd.developmentCompany.interfaces.impl;

import com.solvd.developmentCompany.models.finance.Orders;
import com.solvd.developmentCompany.interfaces.IOrdersDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO extends AbstractMySQLDAO implements IOrdersDAO {
    private static final Logger logger = LoggerFactory.getLogger(OrdersDAO.class);

    @Override
    public Orders getById(int id) {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Orders order = new Orders();
                    order.setId(rs.getInt("id"));
                    order.setCustomerId(rs.getInt("customerId"));
                    order.setProjectId(rs.getInt("projectId"));
                    return order;
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding order {}", id, e);
        }
        return null;
    }

    @Override
    public void save(Orders order) {
        String query = "INSERT INTO orders (customerId, projectId) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getProjectId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            }
            logger.info("Order saved with ID: {}", order.getId());
        } catch (SQLException e) {
            logger.error("Error saving order", e);
        }
    }

    @Override
    public List<Orders> getAll() {
        List<Orders> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Orders order = new Orders();
                order.setId(rs.getInt("id"));
                order.setCustomerId(rs.getInt("customerId"));
                order.setProjectId(rs.getInt("projectId"));
                orders.add(order);
            }
        } catch (SQLException e) {
            logger.error("Error getting all orders", e);
        }
        return orders;
    }

    @Override
    public void update(Orders order) {
        String query = "UPDATE orders SET customerId = ?, projectId = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getProjectId());
            stmt.setInt(3, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error updating order {}", order.getId(), e);
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM orders WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error deleting order {}", id, e);
        }
    }
}
