package com.ygsoft.apps.maintenance;

enum HcGeneral {

    FRAME_TITLE_MAIN      ("ריכוז הוצאות רכב"),
    FRAME_TITLE_MAINT_ADD ("רישום טיפול"),

    MENU_TITLE_REPORTS    ("דוחות"),
    MENU_TITLE_MAINTENANCE("טיפולים"),

    MENU_ITEM_TITLE_NEW ("חדש"),
    MENU_ITEM_TITLE_GET ("חיפוש"),
    MENU_ITEM_TITLE_EXIT("יציאה"),

    B_CANCEL ("ביטול"),
    B_APPROVE("אישור"),


    MESSAGE_R_U_SURE("האם את/ה בטוח/ה ?")
    ;

    private final String theText;


    String getText() {
        return this.theText;
    }



    HcGeneral(String text) {
        this.theText = text;
    }
}
