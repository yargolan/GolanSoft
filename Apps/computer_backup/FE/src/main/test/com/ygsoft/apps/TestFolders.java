package com.ygsoft.apps;

import com.ygsoft.apps.computerbackup.DataSingleton;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;


public class TestFolders {

    @Test
    public void testRootFolder() {
        DataSingleton ds = DataSingleton.getInstance();
        assertNotEquals(".", ds.getRootDir().getAbsolutePath());
    }

    @Test
    public void testBeRootFolder() {
        DataSingleton ds = DataSingleton.getInstance();
        assertNotEquals(".", ds.getBeRootDir().getAbsolutePath());
    }
}
