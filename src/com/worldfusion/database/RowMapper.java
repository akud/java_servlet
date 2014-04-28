package com.worldfusion.database;

public interface RowMapper<T> {

    
    public T mapRow(Row row);
}
