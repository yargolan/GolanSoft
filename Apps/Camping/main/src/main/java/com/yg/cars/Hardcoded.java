package com.yg.cars;

public enum Hardcoded {

    // Main configuration file for the app
    APP_DATA_DIR          ("data"),
    APP_CONFIG_DIR        ("config"),
    CONFIG_FILE_NAME_PROD ("app_config.json"),
    CONFIG_FILE_NAME_DEBUG("app_config.json"),


    // Data files.
    DATA_FILE_DRIVERS("drivers.json"),


    // Trip names
    TRIP_NAME_PASSOVER  ("Passover"),
    TRIP_NAME_SUKKOT    ("Sukkot"),
    TRIP_NAME_SHAVUOT   ("Shavuot"),
    TRIP_NAME_ELECTIONS ("Elections"),
    ;


    private final String name;


    public String getText() {
        return this.name;
    }


    Hardcoded(String name) {
        this.name = name;
    }
}
