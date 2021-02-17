package com.ygsoft.camping.enums;

public enum Hardcoded {

    // General
    NA ("N/A"),

    //
    KEY_APP_ROOT_FOLDER ("Application_root_folder"),
    INITIAL_DATA_FILE   ("campingApp.properties"),






    // Installation related
    APP_ROOT_FOLDER        ("app_root_folder"),

    // JSON files.
    VEHICLES_DATA_FILE     ("Vehicles.json"),
    PARTICIPANTS_DATA_FILE ("Participants.json"),
    INSTALLATION_DATA_FILE ("CampingApp.json"),


    APPLICATION_VERSION    ("0.0.1"),
    XXINITIAL_DATA_FOLDER    ("_Data/InitialData"),
    XXXEXISTING_TRIPS_FOLDER  ("_Data/ExistingTrips"),

    // Db Related
    TABLE_NAME_PARTICIPANTS   ("tblParticipants"),
    COLUMN_PARTICIPANT_NAME   ("cParticipantName"),
    COLUMN_PARTICIPANT_EMAIL  ("cParticipantEmail"),
    COLUMN_PARTICIPANT_INITIAL("cParticipantInitials"),
    COLUMN_PARTICIPANT_V_INDEX("cParticipantVehicleIndex"),
    ;


    private String text;


    public String getText() {
        return this.text;
    }


    Hardcoded(String text) {
        this.text = text;
    }
}
