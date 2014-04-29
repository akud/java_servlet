package com.worldfusion.dao;

import java.util.List;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.Row;
import com.worldfusion.database.RowMapper;
import com.worldfusion.models.ReferenceCount;
import com.worldfusion.models.ReferenceCountType;

public class ReferenceCountFinder {
    private static final String TYPE_COLUMN = "NAME";
    private static final String ALL_COUNT_COLUMN = "CNT_ALL";
    private static final String YEAR_COUNT_COLUMN = "CNT_YEAR";
    private static final String MONTH_COUNT_COLUMN = "CNT_MONTH";
    private static final String RECENT_COUNT_COLUMN = "CNT_RECENT";
    
    private DatabaseConnection databaseConnection;
    private final String tableName;

    public ReferenceCountFinder(DatabaseConnection databaseConnection, ReferenceCountType type) {
        this.databaseConnection = databaseConnection;
        this.tableName = tableNameForType(type);
    }
    
    
    private String tableNameForType(ReferenceCountType type) {
        switch (type) {
        case CANCER:
            return "CANCER";
        case ANTI_CANCER:
            return "ANTICANCER_AGENT";
        default:
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }


    public List<ReferenceCount> getAllReferenceCounts() {
        return databaseConnection.runQuery("select * from " + tableName, new RowMapper<ReferenceCount>() {
            @Override
            public ReferenceCount mapRow(Row row) {
                ReferenceCount referenceCount = new ReferenceCount();
                referenceCount.setType(row.getString(TYPE_COLUMN));
                referenceCount.setAllCount(row.getInt(ALL_COUNT_COLUMN));
                referenceCount.setYearCount(row.getInt(YEAR_COUNT_COLUMN));
                referenceCount.setMonthCount(row.getInt(MONTH_COUNT_COLUMN));
                referenceCount.setRecentCount(row.getInt(RECENT_COUNT_COLUMN));
                return referenceCount;
            }
        });
    }
    
}
