package com.worldfusion.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.Row;
import com.worldfusion.database.RowMapper;

public class LastUpdateFinder {
    private final DatabaseConnection databaseConnection;

    public LastUpdateFinder(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    public Date getLastUpdate() {
        return databaseConnection.runQuery("select * from `UPDATE_DATE` order by UPDATE_DATE desc limit 1", new RowMapper<Date>() {
            @Override
            public Date mapRow(Row row) {
                try {
                    return new SimpleDateFormat("yyyy-MM-dd").parse(row.getString("UPDATE_DATE"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }).get(0);
    }
}
