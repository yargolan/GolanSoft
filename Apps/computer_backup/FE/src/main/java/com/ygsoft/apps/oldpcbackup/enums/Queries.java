package com.ygsoft.apps.oldpcbackup.enums;

public enum Queries {
    Q_GET_USER_NAME("SELECT * FROM %s WHERE %s='%s';"),
    ;

    private final String query;


    public String getQuery() {
        return this.query;
    }



    Queries(String text) {
        this.query = text;
    }
}

