package com.ygsoft.apps.b2.enums;

public enum TextsEnum {

    // General
    FOLDER_NAME_PROFILES("Profiles"),

    // Titles
    T_CREATE_NEW       ("--- Create a new zzProfile ---"),
    T_MAIN_FRAME_TITLE ("Backup my computer."),

    // Labels
    L_CANCEL        ("Cancel"),
    L_CONFIRM       ("Confirm"),
    L_ADD_ITEM      ("Add item"),
    L_PROFILE_NAME  ("ZzProfile name :"),
    L_TARGET_FOLDER ("Target folder :"),
    L_SELECT_FOLDER ("Select folder"),

    L_PROFILE_DELETE("Delete zzProfile"),
    L_PROFILE_SELECT("Select ..."),
    L_PROFILE_RUN_BACKUP("Run b1"),

    // Messages
    M_INTERNAL_ERROR("An internal error occurred."),


    T_NO_PROFILE_YET("No zzProfile chosen yet"),
    T_PROFILE_CREATE("ZzProfile creation"),


    KEY_TARGET_FOLDER("target_folder"),
    KEY_ITEM_PREFIX  ("item_")
    ;


    private String text;


    public String getText() {
        return this.text;
    }



    TextsEnum(String text) {
        this.text = text;
    }
}
