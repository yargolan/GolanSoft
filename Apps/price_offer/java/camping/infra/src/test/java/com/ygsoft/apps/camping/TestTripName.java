package com.ygsoft.apps.camping;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;



public class TestTripName {

    TripName tripName = new TripName();


    @Test
    public void testValidCurrentMonth() {
        assert (tripName.getCurrentMonth() >=1 && tripName.getCurrentMonth() <= 12);
    }



    @Test
    public void testValidCurrentYear() {
        assert (tripName.getCurrentYear() >= 2020);
    }



    @Test
    public void testValidTripName() {

        int getCurrentYear = tripName.getCurrentYear();

        for (int i = 1; i <= 12; i++) {
            String currentTripName = tripName.guessNextTripName(i, getCurrentYear);
            System.out.println(String.format("%2d = %s", i, currentTripName));
            assertNotNull(currentTripName);
        }
    }



    @Test
    public void testNoExistingPreviousTrips() {

        List<File> existingTripsNames = tripName.getExistingTripsNames(new File (""));
        assert(existingTripsNames.size() == 0);
    }
}
