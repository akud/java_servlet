package com.worldfusion.database;

import java.sql.Date;

public interface Row {
    
    public String getString(String columnName);
    public int getInt(String columnName);
    public Date getDate(String columnName);
}
