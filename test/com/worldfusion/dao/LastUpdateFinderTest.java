package com.worldfusion.dao;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import com.worldfusion.database.DatabaseConnection;
import com.worldfusion.mocks.MockDatabaseConnection;

public class LastUpdateFinderTest {

    @Test
    public void test() {
        DatabaseConnection connection = MockDatabaseConnection.createConnectionToReturn(new Object[][][] {
                {{"UPDATE_DATE", "2014-06-13"}}
        });
        LastUpdateFinder lastUpdateFinder = new LastUpdateFinder(connection);
        Calendar date = Calendar.getInstance();
        date.setTime(lastUpdateFinder.getLastUpdate());
        assertNotNull(date);
        assertEquals(2014, date.get(Calendar.YEAR));
        assertEquals(05, date.get(Calendar.MONTH));
        assertEquals(13, date.get(Calendar.DATE));
    }

}
