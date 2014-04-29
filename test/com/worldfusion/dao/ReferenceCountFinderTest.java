package com.worldfusion.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.database.RowMapper;
import com.worldfusion.mocks.MockDatabaseConnection;
import com.worldfusion.models.ReferenceCount;

public class ReferenceCountFinderTest {

    @Test
    public void testGetAllCancerReferences() {
        DatabaseConnection mockConnection = MockDatabaseConnection.createConnectionToReturn(new Object[][][] {
                {{"NAME", "cancer2"}, {"CNT_ALL", 23}, {"CNT_YEAR", 22}, {"CNT_MONTH", 21}, {"CNT_RECENT", 20}},     
                {{"NAME", "cancer1"}, {"CNT_ALL", 13}, {"CNT_YEAR", 12}, {"CNT_MONTH", 11}, {"CNT_RECENT", 10}}     
        });
        ReferenceCountFinder referenceCountFinder = new ReferenceCountFinder(mockConnection, "testTable");
        List<ReferenceCount> results = referenceCountFinder.getAllReferenceCounts();
        assertEquals(2, results.size());
        assertEquals("cancer2", results.get(0).getType());
        assertEquals(22, results.get(0).getYearCount());
        
        assertEquals("cancer1", results.get(1).getType());
        assertEquals(10, results.get(1).getRecentCount());
    }

}
