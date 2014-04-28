package com.worldfusion.database;

import java.util.List;

public interface DatabaseConnection {
    
    public void close();
    public <T> List<T> runQuery(String query, RowMapper<T> rowMapper);
}
