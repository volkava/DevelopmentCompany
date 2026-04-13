package org.example.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractMySQLDAO {
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/dev_company_db");
        config.setUsername("root");
        config.setPassword("");

        dataSource = new HikariDataSource(config);
    }

    protected Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    protected void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
}