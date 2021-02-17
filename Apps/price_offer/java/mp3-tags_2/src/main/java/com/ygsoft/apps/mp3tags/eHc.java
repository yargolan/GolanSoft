package com.ygsoft.apps.mp3tags;

enum eHc {
    SIGN_NEXT       ("->"),
    SIGN_LAST       ("|->>" ),
    SIGN_FIRST      ("<<-|"),
    SIGN_PREVIOUS   ("<-"),

    ACTION_UPDATE ("Update !"),
    ;

    private final String buttonPressed;


    String getText() {
        return this.buttonPressed;
    }



    eHc (String button) {
        this.buttonPressed = button;
    }
}

