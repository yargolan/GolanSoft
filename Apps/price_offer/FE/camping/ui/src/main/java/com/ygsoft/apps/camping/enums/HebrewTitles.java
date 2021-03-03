package com.ygsoft.apps.camping.enums;

public enum  HebrewTitles {

    F_MAIN_UI      ("טיולי שטח, גרסא : "),
    F_TRIP_SELECT  (" בחירת שם הטיול "),
    F_PARTICIPANT_ADD  (" הוסף משתתף "),
    F_PARTICIPANT_DEL  (" מחק משתתף "),
    F_PARTICIPANT_VIEW (" הצגת משתתפים "),

    L_TRIP_NAME        (" שם הטיול "),
    L_PARTICIPANT_NAME (" שם המשתתף "),

    M_TRIP_NAMES        (" שם הטיול "),
    M_TRIP_PARTICIPANTS (" משתתפים "),

    B_GEN_SELECT (" הוסף "),
    B_TRIP_SELECT(" בחר "),

    MI_TRIP_NAMES_EXIT   (" יאללה, ביי ... "),
    MI_TRIP_NAMES_SELECT (" בחר את שם הטיול "),
    MI_PARTICIPANTS_ADD  (" הוסף משתתפים "),
    MI_PARTICIPANTS_DEL  (" הסר משתתפים "),
    MI_PARTICIPANTS_VIEW (" הצג משתתפים "),

    T_GENERATE_NEW (" -- יצירת טיול חדש -- "),

    M_NO_PARTICIPANTS_YET     ("אין משתתפים רשומים עדיין."),
    M_USER_ADDED_SUCCESSFULLY ("המשתתף '%s' נוסף בהצלחה."),
    ;


    private String text;


    public String getText() {
        return this.text;
    }


    HebrewTitles(String text) {
        this.text = text;
    }
}
