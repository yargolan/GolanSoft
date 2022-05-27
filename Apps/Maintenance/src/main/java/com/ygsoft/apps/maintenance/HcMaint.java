package com.ygsoft.apps.maintenance;

enum HcMaint {

    B_TODAY("היום"),

    T_DAY  ("יום"),
    T_YEAR ("שנה"),
    T_MONTH("חודש"),

    TR_OTHER      ("אחר"),
    TR_MECHANIC   ("מכונאות"),
    TR_ELECTRICITY("חשמל"),

    F_TITLE_GARAGE_NEW("הוספת מוסך לרשימה"),

    L_GARAGE_NAME        ("שם המוסך"),
    L_GARAGE_CONTACT     ("איש קשר"),
    L_GARAGE_LOCATION    ("מיקום המוסך"),
    L_GARAGE_TELEPHONE   ("טלפון"),
    L_MAINTENANCE_TYPE   ("סוג הטיפול"),
    L_MAINTENANCE_DATE   ("תאריך הטיפול"),
    L_MAINTENANCE_DETAILS("פירוט הטיפול"),

     B_ADD_GARAGE     ("הוסף מוסך"),
     B_ADD_MAINTENANCE("הוספה"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcMaint(String text) {
        this.theText = text;
    }
}
