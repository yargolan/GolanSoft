package com.ygsoft.apps.maintenance;

enum HcErrors {
    E_DATE  ("שגיאה או תאריך לא חוקי"),
    E_MAINT ("לא הוכנסו פרטי טיפול/תיקון"),
    E_GARAGE("לא הוכנס שם המוסך"),
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcErrors(String text) {
        this.theText = text;
    }
}
