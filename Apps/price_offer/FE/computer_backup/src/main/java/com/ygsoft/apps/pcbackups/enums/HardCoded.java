package com.ygsoft.apps.pcbackups.enums;



public enum HardCoded {
    PROFILES            ("profiles"),
    DIR_NAME_PROFILES   ("Profiles"),
    F_CREATE_NEW_PROFILE("Create a new profile."),
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

