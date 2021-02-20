package com.ygsoft.apps.enums;

import org.apache.commons.lang3.StringUtils;


public enum TextsEnum {
    NO                     ("לא"),
    YES                    ("כן"),
    SEPARATOR              (StringUtils.repeat("-", 70)),

    BTN_NEXT ("הבא"),
    BTN_PREV ("הקודם"),
    BTN_LAST ("האחרון"),
    BTN_FIRST("הראשון"),

    BTN_VOL_ADD("הוסף"),
    BTN_VOL_DEL("מחק"),
    BTN_VOL_EDIT("ערוך"),

    VOL_SERIAL_NO          ("מספר כונן"),
    VOL_TOWN_NAME          ("שם העיר"),
    VOL_NAME_LAST          ("שם משפחה"),
    VOL_HAS_WINCH          ("בעל כננת"),
    VOL_NAME_FIRST         ("שם פרטי"),
    VOL_FIRST_AIDER        ("חובש/מער"),
    VOL_VEHICLE_TYPE       ("סוג רכב"),
    VOL_EMAIL_ADDRESS      ("כתובת דואל"),
    VOL_WEAPON_CARRIER     ("נושא נשק"),
    VOL_TELEPHONE_NUMBER   ("מספר טלפון"),
    VOL_RECOVERY_EQUIPMENT ("ציוד חילוץ"),
    ;

    private final String name;


    public String getText() {
        return this.name;
    }


    TextsEnum(String name) {
        this.name = name;
    }
}
