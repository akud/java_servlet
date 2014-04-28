package com.worldfusion.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcResultSetRow implements Row {
    private ResultSet resultSet;

    public JdbcResultSetRow(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public String getString(String columnName) {
        try {
            return resultSet.getString(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getInt(String columnName) {
        try {
            return resultSet.getInt(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Date getDate(String columnName) {
        try {
            return resultSet.getDate(columnName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}