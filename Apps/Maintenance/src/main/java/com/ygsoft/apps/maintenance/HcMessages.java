package com.ygsoft.apps.maintenance;

enum HcMessages {

    E_NO_GARAGE_NAME    ("לא הוכנס שם המוסך"),
    E_NO_GARAGE_PHONE   ("לא הוכנס מספר טלפון"),
    E_NO_GARAGE_CONTACT ("לא הוכנס איש קשר"),
    E_NO_GARAGE_LOCATION("לא הוכנס מיקום המוסך"),

    I_ADDED_SUCCESSFULLY("נוסף בהצלחה"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcMessages(String text) {
        this.theText = text;
    }
}
