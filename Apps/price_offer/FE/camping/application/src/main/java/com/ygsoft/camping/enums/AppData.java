package com.ygsoft.camping.enums;

public enum AppData {
    KEY_ROOT_FOLDER ("k_root_folder"),
    KEY_DATA_FOLDER ("k_data_folder"),
    KEY_TRIPS_FOLDER("k_trips_folder"),
    KEY_IS_UNDER_DEV("k_is_under_dev"),

    KEY_JSON_VEHICLES    ("json_vehicles"),
    KEY_JSON_PARTICIPANTS("json_participants"),

    ;


    private String text;


    public String getText() {
        return this.text;
    }


    AppData(String text) {
        this.text = text;
    }
}
