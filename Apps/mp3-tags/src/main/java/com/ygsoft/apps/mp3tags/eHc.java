package com.ygsoft.apps.mp3tags;

enum eHc {
    SIGN_NEXT       ("->"),
    SIGN_LAST       ("|->>" ),
    SIGN_FIRST      ("<<-|"),
    SIGN_PREVIOUS   ("<-"),

    ACTION_NEXT     ("Next song"),
    ACTION_LAST     ("Last song" ),
    ACTION_FIRST    ("First song"),
    ACTION_PREVIOUS ("Previous song"),

    ACTION_UPDATE ("Update !"),
    ;

    private String buttonPressed;


    String getText() {
        return this.buttonPressed;
    }



    eHc (String button) {
        this.buttonPressed = button;
    }
}

