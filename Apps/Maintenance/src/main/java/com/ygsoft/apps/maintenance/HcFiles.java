package com.ygsoft.apps.maintenance;

enum HcFiles {
    FILE_GARAGES     ("Data/garages.json"),
    FILE_MAINTENANCES("Data/maintenances.json"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcFiles(String text) {
        this.theText = text;
    }
}
