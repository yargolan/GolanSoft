package com.ygsoft.nav.enums;

public enum GuiEnum {
    L_POINT_NAME      ("שם הנקודה"),
    L_POINT_AREA      ("אזור גיאוגרפי"),
    L_POINT_DESC      ("תיאור הנקודה"),
    L_POINT_LOCATION  ("מיקום הנקודה"),
    L_POINT_SEPARATOR ("/"),

    TF_POINT_H ("אורך"),
    TF_POINT_W ("רוחב"),

    B_ADD     ("הוסף"),
    B_CANCEL  ("בטל"),
    B_CONFIRM ("אשר"),

    DD_NEW    ("--  מיקום חדש  --");

    ;


    private String name;


    public String getText() {
        return this.name;
    }


    GuiEnum(String name) {
        this.name = name;
    }
}
