package com.ygsoft.tools.ini;

public enum IniConstants {
    REGEX_SECTION   ("\\[(.*)\\]"),
    REGEX_KEY_VALUE ("(.*)=(.*)"),
    ;

    private final String regex;

    IniConstants (String key) {
        this.regex = key;
    }


    public String getRegex() {
        return regex;
    }
}
