package com.ygsoft.apps.utilsandwrappers;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestDateAndTime {

    @Test
    public void testNoSuchFormat() {
        String dateAndTime = DateAndTime.getCurrentTime(99);
        assertNull(dateAndTime);
    }



    @Test
    public void testFormat1() {
        String dateAndTime = DateAndTime.getCurrentTime(0);
        assertNotNull(dateAndTime);
        String regex = "[0-9]{8}";
        assertTrue(dateAndTime.matches(regex));
    }



    @Test
    public void testFormat2() {
        String dateAndTime = DateAndTime.getCurrentTime(1);
        assertNotNull(dateAndTime);
        String regex = "[0-9]{4}/[0-9]{2}/[0-9]{2}_[0-9]{2}:[0-9]{2}:[0-9]{2}";
        assertTrue(dateAndTime.matches(regex));
    }



    @Test
    public void testFormat3() {
        String dateAndTime = DateAndTime.getCurrentTime(2);
        assertNotNull(dateAndTime);
        String regex = "[0-9]{8}:[0-9]{6}";
        assertTrue(dateAndTime.matches(regex));
    }



    @Test
    public void testFormat4() {
        String dateAndTime = DateAndTime.getCurrentTime(3);
        assertNotNull(dateAndTime);
        String regex = "[0-9]{8}_[0-9]{6}";
        assertTrue(dateAndTime.matches(regex));
    }
}
