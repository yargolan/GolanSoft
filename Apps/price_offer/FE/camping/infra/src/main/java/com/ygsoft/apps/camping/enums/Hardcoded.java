package com.ygsoft.apps.camping.enums;

public enum Hardcoded {

    APPLICATION_VERSION    ("0.0.1"),
    INITIAL_DATA_FOLDER    ("_Data/InitialData"),
    VEHICLES_DATA_FILE     ("Vehicles.json"),
    PARTICIPANTS_DATA_FILE ("Participants.json"),
    EXISTING_TRIPS_FOLDER  ("_Data/ExistingTrips"),

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
