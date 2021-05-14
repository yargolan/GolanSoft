package com.ygsoft.apps.b2.enums;

public enum HardCoded {
    TABLE_NAME_USERS("tblUsers"),

    DEFAULT_ADMIN_NAME    ("admin"),
    DEFAULT_ADMIN_PASSWORD("qwerty123456"),

    COLUMN_NAME_USERNAME  ("cUser"),
    COLUMN_NAME_PASSWORD  ("cPassword"),
    ;

    private final String text;


    public String getText() {
        return this.text;
    }



    HardCoded(String text) {
        this.text = text;
    }
}

