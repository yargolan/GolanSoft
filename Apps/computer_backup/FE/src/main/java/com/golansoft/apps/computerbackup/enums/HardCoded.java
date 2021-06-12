package com.golansoft.apps.computerbackup.enums;



public enum HardCoded {
    CREATE_NEW_PROFILE("- Create new -"),
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

