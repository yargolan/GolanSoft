package com.yg.apps.cars;

public enum Hardcoded {

    // Main configuration file for the app
    APP_DATA_DIR    ("data"),


    // Data files.
    DATA_FILE_GARAGES("garages.json"),

    ;


    private final String name;


    public String getText() {
        return this.name;
    }


    Hardcoded(String name) {
        this.name = name;
    }
}
