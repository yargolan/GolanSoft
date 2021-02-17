package com.ygsoft.nav.enums;

public enum TextsEnum {
    ORECH  ("אורך"),
    ROCHAV ("רוחב"),

    M_BANK ("נקודות"),

    // GUI
    F_MAIN ("בנק נקודות ציון"),
    F_POINT_ADD  ("הוספת נקודה"),
    F_POINT_DEL  ("מחיקת נקודה"),
    F_POINT_EDIT ("עריכת נקודה"),

    MI_BANK_POINT_ADD  ("הוסף נקודה"),
    MI_BANK_POINT_DEL  ("הסר נקודה"),
    MI_BANK_POINT_EDIT ("ערוך נקודה"),

    ;


    private String name;


    public String getText() {
        return this.name;
    }


    TextsEnum(String name) {
        this.name = name;
    }
}
