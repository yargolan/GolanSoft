package com.ygsoft.nav.enums;

public enum HardcodedEnum {

    DB_FILE  ("Nav_game.db"),

    // Database issues
    TABLE_NAME        ("T_DATA_BANK"),
    COL_POINT_H       ("col_point_height"),
    COL_POINT_W       ("col_point_width"),
    COL_POINT_ID      ("col_point_id"),
    COL_POINT_DATE    ("col_point_date"),
    COL_POINT_AREA    ("col_point_area"),
    COL_POINT_DESC    ("col_point_description"),
    COL_POINT_ENABLED ("col_point_enabled"),

    ;


    private String name;


    public String getText() {
        return this.name;
    }


    HardcodedEnum(String name) {
        this.name = name;
    }
}
