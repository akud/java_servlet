package com.worldfusion.mocks;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.Row;
import com.worldfusion.database.RowMapper;

public abstract class MockDatabaseConnection implements DatabaseConnection {

    @Override
    public void close() {
    }

    public static DatabaseConnection createConnectionToReturn(
            final Object[][][] rows) {
        return new MockDatabaseConnection() {
            @Override
            public <T> List<T> runQuery(String query, RowMapper<T> rowMapper) {
                List<T> list = new ArrayList<T>();
                for (Object[][] row : rows) {
                    list.add(rowMapper.mapRow(new MockRow(row)));
                }
                return list;
            }
        };
    }

    private static class MockRow implements Row {
        private Object[][] row;

        public MockRow(Object[][] row) {
            this.row = row;
        }
        private Object findValueByColumnName(String columnName) {
            for(Object[] pair : row) {
                if(columnName.equals(pair[0])) {
                    return pair[1];
                }
            }
            return null;
        }

        @Override
        public String getString(String columnName) {
            return (String) findValueByColumnName(columnName);
        }

        @Override
        public int getInt(String columnName) {
            return ((Integer) findValueByColumnName(columnName)).intValue();
        }

        @Override
        public Date getDate(String columnName) {
            return (Date) findValueByColumnName(columnName);
        }

    }
}
