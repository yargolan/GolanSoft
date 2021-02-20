package com.ygsoft.apps.enums;

public enum HardcodedEnum {
    DB_FILE ("Yedidim.db"),
    ;

    private final String name;


    public String getText() {
        return this.name;
    }


    HardcodedEnum(String name) {
        this.name = name;
    }
}
