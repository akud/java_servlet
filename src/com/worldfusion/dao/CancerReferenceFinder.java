package com.worldfusion.dao;

import java.util.List;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.Row;
import com.worldfusion.database.RowMapper;
import com.worldfusion.models.CancerReference;

public class CancerReferenceFinder {
    private static final String TABLE_NAME = "CANCER";
    private static final String TYPE_COLUMN = "NAME";
    private static final String ALL_COUNT_COLUMN = "CNT_ALL";
    private static final String YEAR_COUNT_COLUMN = "CNT_YEAR";
    private static final String MONTH_COUNT_COLUMN = "CNT_MONTH";
    private static final String RECENT_COUNT_COLUMN = "CNT_RECENT";
    
    private DatabaseConnection databaseConnection;

    public CancerReferenceFinder(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    
    
    public List<CancerReference> getAllCancerReferences() {
        return databaseConnection.runQuery("select * from " + TABLE_NAME, new RowMapper<CancerReference>() {
            @Override
            public CancerReference mapRow(Row row) {
                CancerReference cancerReference = new CancerReference();
                cancerReference.setType(row.getString(TYPE_COLUMN));
                cancerReference.setAllCount(row.getInt(ALL_COUNT_COLUMN));
                cancerReference.setYearCount(row.getInt(YEAR_COUNT_COLUMN));
                cancerReference.setMonthCount(row.getInt(MONTH_COUNT_COLUMN));
                cancerReference.setRecentCount(row.getInt(RECENT_COUNT_COLUMN));
                return cancerReference;
            }
        });
    }
    
}
