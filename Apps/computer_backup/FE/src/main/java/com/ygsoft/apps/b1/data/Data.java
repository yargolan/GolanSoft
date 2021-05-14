package com.ygsoft.apps.b1.data;



public enum Data {
    ITEMS       ("items"),
    USER_NAME   ("user_name"),
    HOST_NAME   ("host_name"),
    TARGET_DIR  ("target_dir"),
    PROFILES_DIR("root_dir"),
    ;


    private final String text;


    public String getText() {
        return this.text;
    }



    Data(String text) {
        this.text = text;
    }
}

