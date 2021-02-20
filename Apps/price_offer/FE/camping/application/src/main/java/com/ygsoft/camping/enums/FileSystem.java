package com.ygsoft.camping.enums;

public enum FileSystem {

    INITIAL_DATA_FOLDER    ("C:\\YG\\Camping_root"),

    DATA_FOLDER_NAME       ("_DATA_"),
    EXISTING_TRIPS_FOLDER  ("ExistingTrips"),




    // JSON file names.
    VEHICLES_DATA_FILE     ("Vehicles.json"),
    PARTICIPANTS_DATA_FILE ("Participants.json"),
    ;


    private String text;


    public String getText() {
        return this.text;
    }


    FileSystem(String text) {
        this.text = text;
    }
}
