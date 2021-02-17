package com.ygsoft.apps.backup;



public enum HardCoded {
    ADD                 ("Add"),
    RUN                 ("Run"),
    EDIT                ("Edit"),
    CREATE              ("Create the profile !"),
    SELECT              ("Select ..."),
    BROWSE              ("Browse ..."),
    F_MAIN              ("Backup your computer"),
    PROFILES            ("profiles"),
    F_CREATE_NEW_PROFILE("---  Create new  ---"),

    TITLE_NEW_PROFILE    ("Create a new profile."),
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

