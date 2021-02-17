package com.ygsoft.apps.camping.enums;

public enum HolidayNames {
    SUKKOT  ("סוכות"),
    PASSOVER("פסח")
    ;


    private String name;


    public String getName() {
        return this.name;
    }


    HolidayNames(String name) {
        this.name = name;
    }
}
