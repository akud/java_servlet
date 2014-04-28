package com.worldfusion.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MysqlDatabaseConnection implements DatabaseConnection {
    
    private Connection connection;
    
    public MysqlDatabaseConnection(String host, String database, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(formatDatabaseUrl(host, database, user));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    private String formatDatabaseUrl(String host, String database, String user) {
        return "jdbc:mysql://" + host + "/" + database + "?user=" + user;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> runQuery(String query, RowMapper<T> rowMapper) {
        List<T> results = new LinkedList<T>();
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next()) {
                results.add(rowMapper.mapRow(new JdbcResultSetRow(resultSet)));
            }
            return results;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
