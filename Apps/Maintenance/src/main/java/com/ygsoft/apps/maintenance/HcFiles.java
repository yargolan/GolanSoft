package com.ygsoft.apps.maintenance;

enum HcFiles {
    DIR_DATA("Data"),
    FILE_GARAGES("Data/garages.json"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcFiles(String text) {
        this.theText = text;
    }
}
