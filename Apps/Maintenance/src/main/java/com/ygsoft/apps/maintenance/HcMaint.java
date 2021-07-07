package com.ygsoft.apps.maintenance;

enum HcMaint {

    B_TODAY           ("היום"),

    F_TITLE_GARAGE_NEW("הוספת מוסך לרשימה"),

    L_GARAGE_NAME     ("שם המוסך"),
    L_GARAGE_CONTACT  ("איש קשר"),
    L_GARAGE_LOCATION ("מיקום המוסך"),
    L_GARAGE_TELEPHONE("טלפון"),

    L_MAINTENANCE_DATE("תאריך הטיפול"),

     B_ADD_NEW("הוסף חדש"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcMaint(String text) {
        this.theText = text;
    }
}
