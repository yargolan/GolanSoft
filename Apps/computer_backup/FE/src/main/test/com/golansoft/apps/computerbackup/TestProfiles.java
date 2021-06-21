package com.golansoft.apps.computerbackup;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;


public class TestProfiles {

    Profile p;

    @Before
    public void setNew() {
        p = new Profile(
                "profile_name",
                "profile description",
                "username",
                "hostname",
                "target_folder",
                new String[]{"item_1", "item_2"}
        );
    }


    @Test
    public void testProfileName() {
        assertNotNull(p.getName());
    }


    @Test
    public void testProfileDescription() {
        assertNotNull(p.getDescription());
    }


    @Test
    public void testProfileHost() {
        assertNotNull(p.getHostname());
    }


    @Test
    public void testProfileUser() {
        assertNotNull(p.getUserName());
    }


    @Test
    public void testProfileTargetFolder() {
        assertNotNull(p.getItemsToBackup());
    }


    @Test
    public void testTargetFolder() {
        assertNotNull(p.getTargetFolder());
    }
}
