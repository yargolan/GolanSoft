package com.ygsoft.apps.camping;

import java.util.*;
import java.io.File;
import com.ygsoft.apps.camping.enums.HolidayNames;



public class TripName {

    int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }



    int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }



    String guessNextTripName(int currentMonth, int currentYear) {

        String tripName;

        if (currentMonth <= 3) {
            tripName = String.format("%s_%s", HolidayNames.PASSOVER.getName(), currentYear);
        }
        else if (currentMonth <= 9) {
            tripName = String.format("%s_%s", HolidayNames.SUKKOT.getName(), currentYear);
        }
        else {
            currentYear++;
            tripName = String.format("%s_%s", HolidayNames.PASSOVER.getName(), currentYear);
        }

        return tripName;
    }



    /**
     * Read the list of existing trip files.
     * @param existingTripsFolder The folder where you expect to find the trip files.
     * @return Sorted list of trip DB files, if any. null if there are none.
     */
    List<File> getExistingTripsNames(File existingTripsFolder) {

        List<File> list = new ArrayList<>();

        File[] existingTrips = existingTripsFolder.listFiles(
                (dir, name) -> name.toLowerCase().endsWith(".db")
        );

        if (existingTrips != null && existingTrips.length > 0) {

            // Sort the list.
            Arrays.sort(existingTrips);

            // Convert the array into list.
            list = Arrays.asList(existingTrips);
        }

        return list;
    }
}



